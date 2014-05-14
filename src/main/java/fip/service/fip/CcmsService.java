package fip.service.fip;

import fip.common.SystemService;
import fip.common.constant.BillStatus;
import fip.common.constant.BillType;
import fip.common.constant.BizType;
import fip.common.constant.CutpayChannel;
import fip.gateway.ccms.domain.T100101.T100101ResponseRecord;
import fip.gateway.ccms.domain.T100102.T100102Request;
import fip.gateway.ccms.domain.T200101.T200101ResponseRecord;
import fip.gateway.ccms.domain.T200102.T200102Request;
import fip.gateway.ccms.txn.T100101Handler;
import fip.gateway.ccms.txn.T100102Handler;
import fip.gateway.ccms.txn.T200101Handler;
import fip.gateway.ccms.txn.T200102Handler;
import fip.repository.dao.*;
import fip.repository.model.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pub.platform.security.OperatorManager;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * �����Ŵ����������.
 * User: zhanrui
 * Date: 11-8-13
 * Time: ����3:10
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CcmsService {
    private static final Logger logger = LoggerFactory.getLogger(CcmsService.class);

    @Autowired
    private BillManagerService billManagerService;

    @Autowired
    private FipCutpaydetlMapper fipCutpaydetlMapper;
    @Autowired
    private FipRefunddetlMapper fipRefunddetlMapper;
    @Autowired
    private FipJoblogMapper fipJoblogMapper;
    @Autowired
    private XfappMapper xfappMapper;

    @Autowired
    private XfapprepaymentMapper xfapprepaymentMapper;

    /**
     * ��ѯ�����Ŵ�ϵͳ�Ĵ��ۼ�¼���ɹ�ѡ���Ի�ȡ��    (��������Ϣ�ʵ�)
     *
     * @param bizType
     * @param billType
     * @return
     */
    public List<FipCutpaydetl> doQueryCcmsBills(BizType bizType, BillType billType) {

        List<T100101ResponseRecord> recvedList = getCcmsResponseRecords(bizType);
        List<FipCutpaydetl> cutpaydetlList = new ArrayList<FipCutpaydetl>();
        int iSeqno = 0;
        for (T100101ResponseRecord responseBean : recvedList) {
            iSeqno++;
            FipCutpaydetl cutpaydetl = assembleCutpayRecord(bizType, "000000", iSeqno, billType, responseBean);
            if (cutpaydetl == null) {
                continue;
            }
            cutpaydetl.setPkid(UUID.randomUUID().toString());
            cutpaydetlList.add(cutpaydetl);
        }
        return cutpaydetlList;
    }

    //��ѯ�������Ŵ�ϵͳ�� ������¼
    public List<FipRefunddetl> doQueryCcmsRefundBills(BizType bizType, BillType billType) {

        List<T200101ResponseRecord> recvedList = getCcmsRefundResponseRecords(bizType);
        List<FipRefunddetl> detlList = new ArrayList<FipRefunddetl>();
        int iSeqno = 0;
        for (T200101ResponseRecord responseBean : recvedList) {
            iSeqno++;
            FipRefunddetl refunddetl = assembleRefundRecord(bizType, "000000", iSeqno, responseBean);
            if (refunddetl == null) {
                continue;
            }
            refunddetl.setPkid(UUID.randomUUID().toString());
            detlList.add(refunddetl);
        }
        return detlList;
    }

    /**
     * ��ȡȫ���Ŵ�ϵͳ��¼  (��������Ϣ�ʵ�)
     *
     * @return
     */
    @Transactional
    public int doObtainCcmsBills(BizType bizType, BillType billType, List<String> returnMsgs) {
        List<T100101ResponseRecord> recvedList = getCcmsResponseRecords(bizType);
        if (recvedList.size() == 0) {
            return 0;
        }

        int count = 0;
        String batchno = billManagerService.generateBatchno();
        int iSeqno = 0;
        for (T100101ResponseRecord responseBean : recvedList) {
            iSeqno++;
            FipCutpaydetl cutpaydetl = assembleCutpayRecord(bizType, batchno, iSeqno, billType, responseBean);
            if (cutpaydetl == null) {
                continue;
            }
            //TODO �ж�ҵ�������Ƿ��ظ�   ע�� �޸�IOUNO����ʱ��Ҫͬ���޸�commonmapper�е�SQL
            boolean isNotRepeated = billManagerService.checkNoRepeatedBizkeyRecords4Ccms(cutpaydetl.getIouno().substring(0, 20), cutpaydetl.getPoano(), cutpaydetl.getBilltype());
            if (isNotRepeated) {
                cutpaydetl.setDateCmsGet(new Date());
                fipCutpaydetlMapper.insert(cutpaydetl);
                count++;
            } else {
                returnMsgs.add("�ظ���¼��" + cutpaydetl.getIouno() + cutpaydetl.getClientname());
                logger.error("��ȡ�������Ŵ�����ʱ�����ظ���¼��" + cutpaydetl.getIouno() + cutpaydetl.getClientname());
            }
        }

        //��־
        batchInsertLogByBatchno(batchno);
        return count;
    }

    @Transactional
    public int doMultiObtainCcmsBills(BizType bizType, BillType billType, FipCutpaydetl[] selectedCutpaydetls, List<String> returnMsgs) {
        List<T100101ResponseRecord> recvedList = getCcmsResponseRecords(bizType);
        if (recvedList.size() == 0) {
            return 0;
        }

        int count = 0;
        String batchno = billManagerService.generateBatchno();
        int iSeqno = 0;
        for (T100101ResponseRecord responseBean : recvedList) {
            //�ж��Ƿ��ڻ�ȡ��Χ��
            for (FipCutpaydetl selectedCutpaydetl : selectedCutpaydetls) {
                if (responseBean.getStdjjh().equals(selectedCutpaydetl.getIouno())
                        && responseBean.getStdqch().equals(selectedCutpaydetl.getPoano())) {
                    iSeqno++;
                    FipCutpaydetl cutpaydetl = assembleCutpayRecord(bizType, batchno, iSeqno, billType, responseBean);
                    if (cutpaydetl == null) {
                        continue;
                    }
                    //TODO �ж�ҵ�������Ƿ��ظ�   ע�� �޸�IOUNO����ʱ��Ҫͬ���޸�commonmapper�е�SQL
                    //boolean isNotRepeated = billManagerService.checkNoRepeatedBizkeyRecords(cutpaydetl.getIouno().substring(0, 14), cutpaydetl.getPoano(), cutpaydetl.getBilltype());
                    //boolean isNotRepeated = billManagerService.checkNoRepeatedBizkeyRecords4Ccms(cutpaydetl.getIouno().substring(0, 14), cutpaydetl.getPoano(), cutpaydetl.getBilltype());
                    boolean isNotRepeated = billManagerService.checkNoRepeatedBizkeyRecords4Ccms(cutpaydetl.getIouno().substring(0, 20), cutpaydetl.getPoano(), cutpaydetl.getBilltype());
                    if (isNotRepeated) {
                        cutpaydetl.setDateCmsGet(new Date());
                        fipCutpaydetlMapper.insert(cutpaydetl);
                        count++;
                    } else {
                        returnMsgs.add("�ظ���¼��" + cutpaydetl.getIouno() + cutpaydetl.getClientname());
                        logger.info("��ȡ�������Ŵ�����ʱ�����ظ���¼��" + cutpaydetl.getIouno() + cutpaydetl.getClientname());
                    }

                }
            }
        }
        //��־
        batchInsertLogByBatchno(batchno);
        return count;
    }

    //������¼
    @Transactional
    public int doObtainCcmsRefundBills(BizType bizType, BillType billType) {
        List<T200101ResponseRecord> recvedList = getCcmsRefundResponseRecords(bizType);
        if (recvedList.size() == 0) {
            return 0;
        }

        int count = 0;
        String batchno = billManagerService.generateBatchno4Refund();
        int iSeqno = 0;
        for (T200101ResponseRecord responseBean : recvedList) {
            iSeqno++;
            FipRefunddetl refunddetl = assembleRefundRecord(bizType, batchno, iSeqno, responseBean);
            if (refunddetl == null) {
                continue;
            }
            //TODO �ж�ҵ�������Ƿ��ظ�  ע�� �޸�IOUNO����ʱ��Ҫͬ���޸�commonmapper�е�SQL
            boolean isNotRepeated = billManagerService.checkNoRepeatedBizkeyRecords4CcmsRefund(refunddetl.getIouno().substring(0, 20), refunddetl.getPoano());
            if (isNotRepeated) {
                refunddetl.setDateInit(new Date());
                fipRefunddetlMapper.insert(refunddetl);
                count++;
            } else {
                logger.error("��ȡ�������Ŵ�����ʱ�����ظ���¼��" + refunddetl.getIouno() + refunddetl.getClientname());
            }
        }

        //��־
        batchInsertLogByBatchno4Refund(batchno);
        return count;
    }

    //������¼
    @Transactional
    public int doMultiObtainCcmsRefundBills(BizType bizType, BillType billType, FipRefunddetl[] selecteddetls) {
        List<T200101ResponseRecord> recvedList = getCcmsRefundResponseRecords(bizType);
        if (recvedList.size() == 0) {
            return 0;
        }

        int count = 0;
        String batchno = billManagerService.generateBatchno4Refund();
        int iSeqno = 0;
        for (T200101ResponseRecord responseBean : recvedList) {
            //�ж��Ƿ��ڻ�ȡ��Χ��
            for (FipRefunddetl selectedDetl : selecteddetls) {
                if (responseBean.getStdjjh().equals(selectedDetl.getIouno())) {
                    iSeqno++;
                    FipRefunddetl refunddetl = assembleRefundRecord(bizType, batchno, iSeqno, responseBean);
                    if (refunddetl == null) {
                        continue;
                    }
                    //TODO �ж�ҵ�������Ƿ��ظ�     ע�� �޸�IOUNO����ʱ��Ҫͬ���޸�commonmapper�е�SQL
                    boolean isNotRepeated = billManagerService.checkNoRepeatedBizkeyRecords4CcmsRefund(refunddetl.getIouno().substring(0, 20), refunddetl.getPoano());
                    if (isNotRepeated) {
                        refunddetl.setDateInit(new Date());
                        fipRefunddetlMapper.insert(refunddetl);
                        count++;
                    } else {
                        logger.info("��ȡ�������Ŵ�����ʱ�����ظ���¼��" + refunddetl.getIouno() + refunddetl.getClientname());
                    }

                }
            }
        }
        //��־
        batchInsertLogByBatchno4Refund(batchno);
        return count;
    }


    //====================
    private List<T100101ResponseRecord> getCcmsResponseRecords(BizType bizType) {
        //��ȡ���Ŵ�����LIST
        T100101Handler ctl;
        ctl = new T100101Handler();

        String biztype = "";
        if (bizType.equals(BizType.FD)) {
            biztype = "1";
        } else if (bizType.equals(BizType.XFNEW)) {
            biztype = "2";
        }
        //��ѯ ����/�����Ŵ���1/2�� ����
        return ctl.start(biztype);
    }

    private List<T200101ResponseRecord> getCcmsRefundResponseRecords(BizType bizType) {
        //��ȡ���Ŵ�����LIST
        T200101Handler ctl;
        ctl = new T200101Handler();

        String biztype = "";
        if (bizType.equals(BizType.FD)) {
            biztype = "1";
        } else if (bizType.equals(BizType.XFNEW)) {
            biztype = "2";
        }
        //��ѯ ����/�����Ŵ���1/2�� ����
        return ctl.start(biztype);
    }


    /**
     * ������ ��Ϣ�ʵ����� ������
     *
     * @param bizType
     * @param batchSn
     * @param iBatchDetlSn
     * @param billType
     * @param responseBean
     * @return
     */

    private FipCutpaydetl assembleCutpayRecord(BizType bizType,
                                               String batchSn,
                                               int iBatchDetlSn,
                                               BillType billType,
                                               T100101ResponseRecord responseBean) {
        FipCutpaydetl cutpaydetl = new FipCutpaydetl();
        cutpaydetl.setOriginBizid(bizType.getCode());
        cutpaydetl.setBatchSn(batchSn);
        String seqno = "" + iBatchDetlSn;
        cutpaydetl.setBatchDetlSn(StringUtils.leftPad(seqno, 7, "0"));

        cutpaydetl.setIouno(responseBean.getStdjjh()); //��ݺ�

        String poano = responseBean.getStdqch();
        if (StringUtils.isEmpty(poano)) {
            poano = "0";
        }
        cutpaydetl.setPoano(poano);  //�ڴκ�

        cutpaydetl.setContractno(responseBean.getStdhth()); //��ͬ��
        cutpaydetl.setPaybackdate(responseBean.getStdjyrq()); //��������
//        cutpaydetl.setPaybackdate(responseBean.getStdjhhkr()); //�ƻ�������
        cutpaydetl.setPaybackdate(responseBean.getStdjyrq()); //�ƻ������� ��ʹ�ý�������

        //�ͻ���Ϣ
        cutpaydetl.setClientno(responseBean.getStdkhh());
        cutpaydetl.setClientname(responseBean.getStdkhmc());
        cutpaydetl.setClientid(responseBean.getStdkhsfz()); //����֤�� 20130712 zr

        //SBS������Ϣ
        cutpaydetl.setClientact(responseBean.getStddkzh());   //�����ʺ�

        //��������Ϣ
        cutpaydetl.setPaybackamt(new BigDecimal(responseBean.getStdhkje()));  //������
        cutpaydetl.setPrincipalamt(new BigDecimal(responseBean.getStdhkbj())); //�����
        cutpaydetl.setInterestamt(new BigDecimal(responseBean.getStdhklx()));  //������Ϣ
        cutpaydetl.setPunitiveintamt(new BigDecimal(responseBean.getStdfxje()));//��Ϣ���
        //TODO
        cutpaydetl.setBreakamt(new BigDecimal("0.00"));//ΥԼ����
        cutpaydetl.setCompoundintamt(new BigDecimal("0.00"));//��Ϣ�������

        cutpaydetl.setReserveamt(new BigDecimal(responseBean.getStdryje()));  //������

        //�ʵ�����
        cutpaydetl.setBilltype(billType.getCode());

        //����������Ϣ
        if (bizType.equals(BizType.XFNEW)) {
            cutpaydetl.setBiChannel(CutpayChannel.UNIPAY.getCode()); //Ĭ��Ϊ����
            cutpaydetl.setBiBankactno(responseBean.getStdhkzh());
            cutpaydetl.setBiBankactname(responseBean.getStdkhmc());

            cutpaydetl.setBiActopeningbank(responseBean.getStdyhh());
            cutpaydetl.setBiProvince(responseBean.getStdyhsf());
//            cutpaydetl.setBiCity(xfapprepayment.getCity());
        } else {
            throw new RuntimeException("��������");
        }


        //����
        cutpaydetl.setRecversion((long) 0);
        cutpaydetl.setDeletedflag("0");
        cutpaydetl.setArchiveflag("0");
        cutpaydetl.setWritebackflag("0");
        cutpaydetl.setAccountflag("0");
        //�ʵ�״̬
        cutpaydetl.setBillstatus(BillStatus.INIT.getCode());
        cutpaydetl.setSendflag("0");

        //zhanrui 20120305  ��ʶ�����Ŵ�������Դ���Ŵ�ϵͳ �����дʱ������Դϵͳ
        cutpaydetl.setRemark3("XF-CCMS");

        cutpaydetl.setDateCmsGet(new Date());

        return cutpaydetl;
    }

    /**
     * ������ ��Ϣ�ʵ����� ������
     * ���ֽ���ֱ�������汾  �ݲ���   20120912  zhanrui
     */

    private FipCutpaydetl assembleCutpayRecordNew4CcbDirect(BizType bizType,
                                               String batchSn,
                                               int iBatchDetlSn,
                                               BillType billType,
                                               T100101ResponseRecord responseBean) {
        FipCutpaydetl cutpaydetl = new FipCutpaydetl();
        cutpaydetl.setOriginBizid(bizType.getCode());
        cutpaydetl.setBatchSn(batchSn);
        String seqno = "" + iBatchDetlSn;
        cutpaydetl.setBatchDetlSn(StringUtils.leftPad(seqno, 7, "0"));

        cutpaydetl.setIouno(responseBean.getStdjjh()); //��ݺ�

        String poano = responseBean.getStdqch();
        if (StringUtils.isEmpty(poano)) {
            poano = "0";
        }
        cutpaydetl.setPoano(poano);  //�ڴκ�

        cutpaydetl.setContractno(responseBean.getStdhth()); //��ͬ��
        cutpaydetl.setPaybackdate(responseBean.getStdjyrq()); //��������
//        cutpaydetl.setPaybackdate(responseBean.getStdjhhkr()); //�ƻ�������
        cutpaydetl.setPaybackdate(responseBean.getStdjyrq()); //�ƻ������� ��ʹ�ý�������

        //�ͻ���Ϣ
        cutpaydetl.setClientno(responseBean.getStdkhh());
        cutpaydetl.setClientname(responseBean.getStdkhmc());

        //SBS������Ϣ
        cutpaydetl.setClientact(responseBean.getStddkzh());   //�����ʺ�

        //��������Ϣ
        cutpaydetl.setPaybackamt(new BigDecimal(responseBean.getStdhkje()));  //������
        cutpaydetl.setPrincipalamt(new BigDecimal(responseBean.getStdhkbj())); //�����
        cutpaydetl.setInterestamt(new BigDecimal(responseBean.getStdhklx()));  //������Ϣ
        cutpaydetl.setPunitiveintamt(new BigDecimal(responseBean.getStdfxje()));//��Ϣ���
        //TODO
        cutpaydetl.setBreakamt(new BigDecimal("0.00"));//ΥԼ����
        cutpaydetl.setCompoundintamt(new BigDecimal("0.00"));//��Ϣ�������

        cutpaydetl.setReserveamt(new BigDecimal(responseBean.getStdryje()));  //������

        //�ʵ�����
        cutpaydetl.setBilltype(billType.getCode());

        //����������Ϣ
        if (bizType.equals(BizType.XFNEW)) {
            cutpaydetl.setBiBankactno(responseBean.getStdhkzh());
            cutpaydetl.setBiBankactname(responseBean.getStdkhmc());

            String bankid = responseBean.getStdyhh();
            cutpaydetl.setBiActopeningbank(bankid);
            cutpaydetl.setBiProvince(responseBean.getStdyhsf());
//            cutpaydetl.setBiCity(xfapprepayment.getCity());
            if ("105".equals(bankid)) {
                cutpaydetl.setBiChannel(CutpayChannel.NONE.getCode());
            }else{
                cutpaydetl.setBiChannel(CutpayChannel.UNIPAY.getCode()); //Ĭ��Ϊ����
            }
        } else {
            throw new RuntimeException("��������");
        }


        //����
        cutpaydetl.setRecversion((long) 0);
        cutpaydetl.setDeletedflag("0");
        cutpaydetl.setArchiveflag("0");
        cutpaydetl.setWritebackflag("0");
        cutpaydetl.setAccountflag("0");
        //�ʵ�״̬
        cutpaydetl.setBillstatus(BillStatus.INIT.getCode());
        cutpaydetl.setSendflag("0");

        //zhanrui 20120305  ��ʶ�����Ŵ�������Դ���Ŵ�ϵͳ �����дʱ������Դϵͳ
        cutpaydetl.setRemark3("XF-CCMS");

        cutpaydetl.setDateCmsGet(new Date());

        return cutpaydetl;
    }

    //������¼
    private FipRefunddetl assembleRefundRecord(BizType bizType,
                                               String batchSn,
                                               int iBatchDetlSn,
                                               T200101ResponseRecord responseBean) {
        FipRefunddetl refunddetl = new FipRefunddetl();
        refunddetl.setOriginBizid(bizType.getCode());
        refunddetl.setBatchSn(batchSn);
        String seqno = "" + iBatchDetlSn;
        refunddetl.setBatchDetlSn(StringUtils.leftPad(seqno, 7, "0"));

        refunddetl.setIouno(responseBean.getStdjjh()); //��ݺ�

        String poano = responseBean.getStdqch();
        if (StringUtils.isEmpty(poano)) {
            poano = "0";
        }
        refunddetl.setPoano(poano);  //�ڴκ�

        refunddetl.setContractno(responseBean.getStdhth()); //��ͬ��
        //refunddetl.setPaybackdate(responseBean.getStdjyrq()); //��������
        //refunddetl.setPaybackdate(responseBean.getStdjyrq()); //�ƻ������� ��ʹ�ý�������

        //�ͻ���Ϣ
        refunddetl.setClientno(responseBean.getStdkhh());
        refunddetl.setClientname(responseBean.getStdkhmc());

        //SBS������Ϣ
        //refunddetl.setClientact(responseBean.getStddkzh());   //�����ʺ�

        refunddetl.setPayamt(new BigDecimal(responseBean.getStdhkje()));  //������
        //��������Ϣ
        //refunddetl.setPaybackamt(new BigDecimal(responseBean.getStdhkje()));  //������
        //refunddetl.setPrincipalamt(new BigDecimal(responseBean.getStdhkbj())); //�����
        //refunddetl.setInterestamt(new BigDecimal(responseBean.getStdhklx()));  //������Ϣ
        //refunddetl.setPunitiveintamt(new BigDecimal(responseBean.getStdfxje()));//��Ϣ���
        //refunddetl.setBreakamt(new BigDecimal("0.00"));//ΥԼ����
        //refunddetl.setCompoundintamt(new BigDecimal("0.00"));//��Ϣ�������

        //refunddetl.setReserveamt(new BigDecimal(responseBean.getStdryje()));  //������

        //�ʵ�����
        //refunddetl.setBilltype(billType.getCode());

        //����������Ϣ
        if (bizType.equals(BizType.XFNEW)) {
            refunddetl.setBiChannel(CutpayChannel.UNIPAY.getCode()); //Ĭ��Ϊ����
            refunddetl.setBiBankactno(responseBean.getStdhkzh());
            refunddetl.setBiBankactname(responseBean.getStdkhmc());

            refunddetl.setBiActopeningbank(responseBean.getStdyhh());
            refunddetl.setBiProvince(responseBean.getStdyhsf());
            //refunddetl.setBiCity(xfapprepayment.getCity());
        } else {
            throw new RuntimeException("��������");
        }


        //����
        refunddetl.setRecversion((long) 0);
        refunddetl.setDeletedflag("0");
        refunddetl.setArchiveflag("0");
        refunddetl.setWritebackflag("0");
        refunddetl.setAccountflag("0");
        //�ʵ�״̬
        refunddetl.setBillstatus(BillStatus.INIT.getCode());
        //refunddetl.setSendflag("0");

        //zhanrui 20120305  ��ʶ�����Ŵ�������Դ���Ŵ�ϵͳ �����дʱ������Դϵͳ
        //refunddetl.setRemark3("XF-CCMS");
        refunddetl.setDateInit(new Date());

        return refunddetl;
    }

    //============================================


    /**
     * ��鱾�ر��мȴ��¼��״̬ ��������
     * 1��״̬�����ļ�¼
     * 2��δ���͵ļ�¼
     * 3�����ͳɹ��ļ�¼�����ͳɹ��ı������ʻ�д��
     *
     * @return
     */
    private boolean checkLocalBillsStatus() {
        return true;
    }

    //TODO  ����
    private void batchInsertLogByBatchno(String batchno) {
        FipCutpaydetlExample example = new FipCutpaydetlExample();
        example.createCriteria().andBatchSnEqualTo(batchno);
        List<FipCutpaydetl> fipCutpaydetlList = fipCutpaydetlMapper.selectByExample(example);

        Date date = new Date();

        OperatorManager operatorManager = SystemService.getOperatorManager();
        String userid;
        String username;
        if (operatorManager == null) {
            userid = "9999";
            username = "BATCH";
        }else{
            userid = operatorManager.getOperatorId();
            username = operatorManager.getOperatorName();
        }

        for (FipCutpaydetl fipCutpaydetl : fipCutpaydetlList) {
            FipJoblog log = new FipJoblog();
            log.setTablename("fip_cutpaydetl");
            log.setRowpkid(fipCutpaydetl.getPkid());
            log.setJobname("�½���¼");
            log.setJobdesc("�»�ȡ�������Ŵ�ϵͳ���ۼ�¼");
            log.setJobtime(date);
            log.setJobuserid(userid);
            log.setJobusername(username);
            fipJoblogMapper.insert(log);
        }
    }

    //������¼������־
    private void batchInsertLogByBatchno4Refund(String batchno) {
        FipRefunddetlExample example = new FipRefunddetlExample();
        example.createCriteria().andBatchSnEqualTo(batchno);
        List<FipRefunddetl> detlList = fipRefunddetlMapper.selectByExample(example);

        Date date = new Date();
        OperatorManager operatorManager = SystemService.getOperatorManager();
        String userid;
        String username;
        if (operatorManager == null) {
            userid = "9999";
            username = "BATCH";
        }else{
            userid = operatorManager.getOperatorId();
            username = operatorManager.getOperatorName();
        }
        for (FipRefunddetl detl : detlList) {
            FipJoblog log = new FipJoblog();
            log.setTablename("fip_refunddetl");
            log.setRowpkid(detl.getPkid());
            log.setJobname("�½���¼");
            log.setJobdesc("�»�ȡ�������Ŵ�ϵͳ���ۼ�¼");
            log.setJobtime(date);
            log.setJobuserid(userid);
            log.setJobusername(username);
            fipJoblogMapper.insert(log);
        }
    }

    /**
     * ��д��Ϣϵͳ�������ʵ�����ǰ�����ʵ���
     */
    //@Transactional ��дʱ��������������
    public int writebackCutPayRecord2CCMS(List<FipCutpaydetl> cutpaydetlList, boolean isArchive) {
        int count = 0;
        T100102Handler t100102ctl = new T100102Handler();

        for (FipCutpaydetl detl : cutpaydetlList) {
            boolean txResult = false;
            if (detl.getBilltype().equals(BillType.NORMAL.getCode())) { //��������
                FipCutpaydetl dbRecord = fipCutpaydetlMapper.selectByPrimaryKey(detl.getPkid());
                if (!detl.getRecversion().equals(dbRecord.getRecversion())) {
                    throw new RuntimeException("�������󣺰汾�Ų�ͬ " + detl.getClientname() + detl.getPkid());
                }
                T100102Request recordT102 = new T100102Request();
                recordT102.setStdjjh(detl.getIouno());
                recordT102.setStdjyrq(detl.getPaybackdate());//TODO
                recordT102.setStdjhkkr(detl.getPaybackdate());
                recordT102.setStdcgkkje(detl.getPaybackamt().toString());//TODO bigdecimal
                recordT102.setStddkzh(detl.getClientact());
                recordT102.setStdhth(detl.getContractno());

                String billStatus = detl.getBillstatus();
                if (BillStatus.CUTPAY_SUCCESS.getCode().equals(billStatus)) {
                    recordT102.setStdkkjg("1");   //���д����ɹ�
                } else if (BillStatus.CUTPAY_FAILED.getCode().equals(billStatus)) {
                    recordT102.setStdkkjg("2");   //���д���ʧ��
                } else if (BillStatus.CUTPAY_QRY_PEND.getCode().equals(billStatus)) {
                    recordT102.setStdkkjg("3");   //״̬����
                } else {
                    logger.error("��д��¼ʱ���ִ����¼��");
                }

                //���ʷ��ʹ���
                txResult = t100102ctl.start(recordT102);
            } else {
                logger.error("��д�������Ŵ�ϵͳ�������ʵ����Ͳ�֧��");
                throw new RuntimeException("��д�������Ŵ�ϵͳ�������ʵ����Ͳ�֧��");
            }

            Date date = new Date();
            OperatorManager operatorManager = SystemService.getOperatorManager();
            String userid;
            String username;
            if (operatorManager == null) {
                userid = "9999";
                username = "BATCH";
            }else{
                userid = operatorManager.getOperatorId();
                username = operatorManager.getOperatorName();
            }
            FipJoblog log = new FipJoblog();
            log.setTablename("fip_cutpaydetl");
            log.setRowpkid(detl.getPkid());
            log.setJobname("�������Ŵ���д����");
            log.setJobtime(date);
            log.setJobuserid(userid);
            log.setJobusername(username);

            if (txResult) { //��д�ɹ�
                detl.setWritebackflag("1");
                if (isArchive) {
                    detl.setArchiveflag("1");   //��д��� ���浵����
                }
                detl.setDateCmsPut(new Date());
                log.setJobdesc("�������Ŵ���д�����ɹ�");
                count++;
            } else {
                detl.setWritebackflag("0");
                log.setJobdesc("�������Ŵ���д����ʧ��");
            }
            fipJoblogMapper.insert(log);
            detl.setRecversion(detl.getRecversion() + 1);
            fipCutpaydetlMapper.updateByPrimaryKey(detl);
        }
        return count;
    }

    public int writebackRefundRecord2CCMS(List<FipRefunddetl> detlList, boolean isArchive) {
        int count = 0;
        T200102Handler ctl = new T200102Handler();

        for (FipRefunddetl detl : detlList) {
            boolean txResult = false;

            FipRefunddetl dbRecord = fipRefunddetlMapper.selectByPrimaryKey(detl.getPkid());
            if (!detl.getRecversion().equals(dbRecord.getRecversion())) {
                throw new RuntimeException("�������󣺰汾�Ų�ͬ" + detl.getPkid());
            }

            T200102Request recordT102 = new T200102Request();
            recordT102.setStdjjh(detl.getIouno());
            recordT102.setStdjyrq(new SimpleDateFormat("yyyy-MM-dd").format(detl.getDateBankPay()));//TODO
            recordT102.setStdjhkkr(detl.getStartdate());
            recordT102.setStdcgkkje(detl.getPayamt().toString());
            recordT102.setStddkzh("");
            recordT102.setStdhth(detl.getContractno());
            //1-�ɹ� 2-ʧ��
            //��дʧ�ܼ�¼ʱ��Ҫע���ʱcutpaydetlList�п��ܺ����Ŵ�ϵͳ�еļ�¼
            //if (isSuccess) {
            //    recordT102.setStdkkjg("1");
            //} else {
            //    recordT102.setStdkkjg("2");
            //}

            String billStatus = detl.getBillstatus();
            if (BillStatus.CUTPAY_SUCCESS.getCode().equals(billStatus)) {
                recordT102.setStdkkjg("1");   //���д����ɹ�
            } else if (BillStatus.CUTPAY_FAILED.getCode().equals(billStatus)) {
                recordT102.setStdkkjg("2");   //���д���ʧ��
            } else if (BillStatus.CUTPAY_QRY_PEND.getCode().equals(billStatus)) {
                recordT102.setStdkkjg("3");   //״̬����
            } else {
                logger.error("��д��¼ʱ���ִ����¼��");
            }

            //���ʷ��ʹ���
            txResult = ctl.start(recordT102);

            Date date = new Date();
            OperatorManager operatorManager = SystemService.getOperatorManager();
            String userid;
            String username;
            if (operatorManager == null) {
                userid = "9999";
                username = "BATCH";
            }else{
                userid = operatorManager.getOperatorId();
                username = operatorManager.getOperatorName();
            }
            FipJoblog log = new FipJoblog();
            log.setTablename("fip_refunddetl");
            log.setRowpkid(detl.getPkid());
            log.setJobname("�������Ŵ���д����");
            log.setJobtime(date);
            log.setJobuserid(userid);
            log.setJobusername(username);

            if (txResult) {
                detl.setWritebackflag("1");
                if (isArchive) {
                    detl.setArchiveflag("1");   //��д��� ���浵����
                }
                detl.setDateCmsPut(new Date());
                log.setJobdesc("�������Ŵ���д�����ɹ�");
                count++;
            } else {
                detl.setWritebackflag("0");
                log.setJobdesc("�������Ŵ���д����ʧ��");
            }
            fipJoblogMapper.insert(log);
            detl.setRecversion(detl.getRecversion() + 1);
            fipRefunddetlMapper.updateByPrimaryKey(detl);
        }
        return count;
    }
}