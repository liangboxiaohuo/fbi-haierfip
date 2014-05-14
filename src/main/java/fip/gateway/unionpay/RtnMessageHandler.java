package fip.gateway.unionpay;

import fip.common.constant.TxpkgStatus;
import fip.common.constant.BillStatus;
import fip.common.constant.TxSendFlag;
import fip.gateway.unionpay.domain.T100001Toa;
import fip.gateway.unionpay.domain.T100004Toa;
import fip.gateway.unionpay.domain.T200001Toa;
import fip.repository.dao.FipCutpaybatMapper;
import fip.repository.dao.FipCutpaydetlMapper;
import fip.repository.model.*;
import fip.service.fip.BillManagerService;
import fip.service.fip.JobLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Deprecated
public class RtnMessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(RtnMessageHandler.class);

    @Autowired
    FipCutpaydetlMapper mapper;

    @Autowired
    FipCutpaybatMapper batMapper;

    @Autowired
    BillManagerService billManagerService;

    @Autowired
    private JobLogService jobLogService;

    /**
     * ��������100004ʵʱ�ۿ�׽��
     * ע�⣺����100004���ױ���ı��ĸ�ʽ֧������������Ŀǰ��20110901���˱���ֻ�����ʷ��ʹ���
     * ���ʴ������ˮ�� �� fip_cutpaybat���޹� ��������ʱ����ˮ������������ɣ�BATCH_SN + BATCH_DETL_SN
     *
     * @param message
     */
    public void handle100004Message(String message) {
        logger.info(" ========��ʼ�����ص�100004��Ϣ==========");
        logger.info(message);

        T100004Toa toa = T100004Toa.getToa(message);
        if (toa != null) {
            String retcode_head = toa.INFO.RET_CODE;      //����ͷ������
            String req_sn = toa.INFO.REQ_SN;              //������ˮ��
            String batch_sn = req_sn.substring(0, 11);    //����������ˮ�� �õ����κ�
            String batch_detl_sn = req_sn.substring(11);  //����������ˮ�� �õ������ڵ�˳���
            FipCutpaydetlExample example = new FipCutpaydetlExample();
            example.createCriteria().andBatchSnEqualTo(batch_sn).andBatchDetlSnEqualTo(batch_detl_sn)
                    .andArchiveflagEqualTo("0").andDeletedflagEqualTo("0");
            List<FipCutpaydetl> cutpaydetlList = mapper.selectByExample(example);
            if (cutpaydetlList.size() != 1) {
                logger.error("δ���ҵ���Ӧ�Ŀۿ��¼��" + req_sn);
                throw new RuntimeException("δ���ҵ���Ӧ�Ŀۿ��¼��" + req_sn);
            }
            FipCutpaydetl record = cutpaydetlList.get(0);

            if ("0000".equals(retcode_head)) { //����ͷ��0000�����������
                //�Ѳ��ҵ����ݿ��ж�Ӧ�ļ�¼�����Խ�����־��¼
                T100004Toa.Body.BodyDetail bodyDetail = toa.BODY.RET_DETAILS.get(0);
                String retcode_detl = bodyDetail.RET_CODE;
                if ("0000".equals(retcode_detl)) { //���׳ɹ���Ψһ��־
                    if (bodyDetail.ACCOUNT_NO.equals(record.getBiBankactno())) {
                        long recordAmt = record.getPaybackamt().multiply(new BigDecimal(100)).longValue();
                        long returnAmt = Integer.parseInt(bodyDetail.AMOUNT);
                        if (recordAmt == returnAmt) {
                            record.setBillstatus(BillStatus.CUTPAY_SUCCESS.getCode());
                            record.setDateBankCutpay(new Date());
                        } else {
                            logger.error("���ؽ�ƥ��");
                            appendNewJoblog(record.getPkid(), "fip_cutpaydetl", "��������", "���ؽ�ƥ��:" + returnAmt);
                        }
                    } else {
                        logger.error("�ʺŲ�ƥ��");
                        appendNewJoblog(record.getPkid(), "fip_cutpaydetl", "��������", "�ʺŲ�ƥ��" + bodyDetail.ACCOUNT_NO);
                    }
                } else {  //����ʧ��
                    record.setBillstatus(BillStatus.CUTPAY_FAILED.getCode());
                }
                record.setTxRetcode(String.valueOf(retcode_detl));
                record.setTxRetmsg(bodyDetail.ERR_MSG);
            } else if ("1002".equals(retcode_head)) {//�޷���ѯ���ý��ף������ط�  �ؼ���
                record.setBillstatus(BillStatus.RESEND_PEND.getCode());
                record.setTxRetcode(String.valueOf(retcode_head));
                record.setTxRetmsg(toa.INFO.ERR_MSG);
            } else { //����ѯ
                record.setBillstatus(BillStatus.CUTPAY_QRY_PEND.getCode());
                record.setTxRetcode(String.valueOf(retcode_head));
                record.setTxRetmsg(toa.INFO.ERR_MSG);
            }
            record.setRecversion(record.getRecversion() + 1);
            mapper.updateByPrimaryKey(record);
        } else { //
            throw new RuntimeException("�ñʽ��׼�¼Ϊ�գ������ѱ�ɾ���� " + message);
        }
        logger.debug(" ................. �����ص���Ϣ����........");
    }

    @Deprecated
    public void handle100004Message_bak(String message) {
        logger.info(" ................. ��ʼ�����ص�100004��Ϣ........");
        logger.info(message);

        T100004Toa toa = T100004Toa.getToa(message);
        if (toa != null) {
            String retCode = toa.INFO.RET_CODE;
            FipCutpaydetl record = null;

            FipCutpaydetlExample example = new FipCutpaydetlExample();

            String query_sn = toa.INFO.REQ_SN;
            String batchno = query_sn.substring(0, 11);
            String seqno = query_sn.substring(11, 18);

            example.createCriteria().andBatchSnEqualTo(batchno).andBatchDetlSnEqualTo(seqno);
            List<FipCutpaydetl> recordList = mapper.selectByExample(example);

            if (!recordList.isEmpty()) {
                record = recordList.get(0);
                if (record != null) {
                    //====================== ����Dep������-=============
                    if (Arrays.asList(RtnCodeEnum.UNIONPAY_TRX_CODE_100004_SUCCESS.getValues()).contains(retCode)) {
                        record.setBillstatus(BillStatus.CUTPAY_SUCCESS.getCode());
                    } else if (Arrays.asList(RtnCodeEnum.UNIONPAY_TRX_CODE_100004_FAILE.getValues()).contains(retCode)) {
                        record.setBillstatus(BillStatus.CUTPAY_FAILED.getCode());
                    } else if (Arrays.asList(RtnCodeEnum.UNIONPAY_TRX_CODE_100004_WAIT.getValues()).contains(retCode)) {
                        record.setBillstatus(BillStatus.CUTPAY_QRY_PEND.getCode());
                    } else if (Arrays.asList(RtnCodeEnum.UNIONPAY_TRX_CODE_100004_AGAIN.getValues()).contains(retCode)) {
                        //record.setBillstatus(XFBillStatus.SEND_FAILED.getCode());
                        record.setSendflag(TxSendFlag.UNSEND.getCode());
                    } else {
                        // ���������Ϊ���ͳɹ�������ѯ
                        record.setBillstatus(BillStatus.CUTPAY_QRY_PEND.getCode());
                    }
                    record.setTxRetcode(String.valueOf(retCode));
                    record.setTxRetmsg(toa.INFO.ERR_MSG);
                    record.setRecversion(record.getRecversion() + 1);
                }
            } else {
                logger.error("δ�ҵ���Ӧ�Ľ�����ˮ��:" + query_sn);
                //TODO
                throw new RuntimeException("δ�ҵ���Ӧ�Ľ�����ˮ��:" + query_sn);
            }

            record.setTxRetmsg("[����ͷ��Ϣ]:" + toa.INFO.ERR_MSG);

            if (toa.BODY != null) {
                if (toa.BODY.RET_DETAILS != null && toa.BODY.RET_DETAILS.size() == 1) {
                    T100004Toa.Body.BodyDetail bodyDetail = toa.BODY.RET_DETAILS.get(0);
                    if (record != null) {
                        if (bodyDetail.ACCOUNT_NO.equals(record.getBiBankactno())) {
                            record.setTxRetmsg(record.getTxRetmsg() + " [��������Ϣ]:" + bodyDetail.ERR_MSG);
                        }
                    }
                }
            }

            String log = "������(100004)���:" + record.getTxRetmsg();
            appendNewJoblog(record.getPkid(), "fip_cutpaydetl", "��������", log);

            mapper.updateByPrimaryKey(record);

            logger.debug(" ................. �����ص���Ϣ����........");
        } else {
            throw new RuntimeException("�ñʽ��׼�¼Ϊ�գ������ѱ�ɾ����������ˮ��: " + toa.INFO.REQ_SN);
        }
    }

    public void handle200001BatchMessage(String message) {
        logger.debug("................. ��ʼ�����ص�200001������ѯ����Ϣ........");
        logger.info(message);

        T200001Toa toa = T200001Toa.getToa(message);
        boolean isReset = false;
        boolean isBatchTxOver = true;
        if (toa != null) {
            String msgRetCode = toa.INFO.RET_CODE;
            //  �������
            String txpkgSn = toa.INFO.REQ_SN;
            appendNewJoblog(txpkgSn, "fip_cutpaybat", "��������", "[������:" + msgRetCode + "][��Ϣ:]" + toa.INFO.ERR_MSG);
            if (Arrays.asList(RtnCodeEnum.UNIONPAY_TRX_CODE_200001_WAIT.getValues()).contains(msgRetCode)) {
                isBatchTxOver = false;
            } else if (Arrays.asList(RtnCodeEnum.UNIONPAY_TRX_CODE_200001_AGAIN.getValues()).contains(msgRetCode)) {
                isBatchTxOver = false;
                billManagerService.updateCutpaybatToSendflag(txpkgSn, TxSendFlag.UNSEND.getCode());
            } else if (Arrays.asList(RtnCodeEnum.UNIONPAY_TRX_CODE_200001_FAILE.getValues()).contains(msgRetCode)) {
                isReset = true;  // ���
            } else if (Arrays.asList(RtnCodeEnum.UNIONPAY_TRX_CODE_200001_SUCCESS.getValues()).contains(msgRetCode)) {
                String query_sn = toa.BODY.QUERY_TRANS.QUERY_SN;
                FipCutpaydetlExample example = new FipCutpaydetlExample();
                for (T200001Toa.Body.BodyDetail detail : toa.BODY.RET_DETAILS) {
                    example.clear();
                    String sn = detail.SN;
                    if ("0001".equals(sn)) {  //��������
                        query_sn = query_sn.substring(0, 11);
                        example.createCriteria().andBatchSnEqualTo(query_sn);
                    } else {
                        example.createCriteria().andTxpkgSnEqualTo(query_sn).andTxpkgDetlSnEqualTo(sn);
                    }
                    List<FipCutpaydetl> cutpaydetlList = mapper.selectByExample(example);
                    FipCutpaydetl record = cutpaydetlList.get(0);
                    String retCode = detail.RET_CODE;
                    if (isReset) {
                        record.setTxpkgSn("");
                        record.setTxpkgDetlSn("");
                        record.setSendflag(TxSendFlag.UNSEND.getCode());
                        record.setBillstatus(BillStatus.CUTPAY_QRY_PEND.getCode());
                        record.setTxRetmsg("[ϵͳʧ�ܣ��������]");
                        record.setTxRetcode("");
                    }
                    //  ҵ��ɹ���
                    //String[] values_100001 = RtnCodeEnum.UNIONPAY_TRX_CODE_100001_SUCCESS.getValues();
                    //if (Arrays.asList(values_100001).contains(retCode)) {
                    if ("0000".equals(retCode)) {
                        if (detail.ACCOUNT.endsWith(record.getBiBankactno())) {
                            //TODO �����
                            record.setBillstatus(BillStatus.CUTPAY_SUCCESS.getCode());
                            record.setDateBankCutpay(new Date());
                        } else {
                            logger.error("��¼��ƥ��");
                            throw new RuntimeException("��¼��ƥ��" + record.getClientname());
                        }
                    } else {
                        record.setBillstatus(BillStatus.CUTPAY_FAILED.getCode());
                    }

                    record.setTxRetmsg("[������:" + retCode + "][������Ϣ]:" + detail.ERR_MSG);
                    record.setTxRetcode(String.valueOf(retCode));
                    record.setRecversion(record.getRecversion() + 1);
                    String log = "������������(200001)���: [��������+���:" + record.getTxpkgSn() + record.getTxpkgDetlSn() + "]" + record.getTxRetmsg();
                    appendNewJoblog(record.getPkid(), "fip_cutpaydetl", "��������", log);

                    mapper.updateByPrimaryKey(record);
                }
            }
            if (isBatchTxOver) {
                FipCutpaybat fipCutpaybat = batMapper.selectByPrimaryKey(txpkgSn);
                fipCutpaybat.setTxpkgStatus(TxpkgStatus.DEAL_SUCCESS.getCode());
                fipCutpaybat.setRecversion(fipCutpaybat.getRecversion() + 1);
                batMapper.updateByPrimaryKey(fipCutpaybat);
            }
            logger.debug(" ................. �����ص���Ϣ����........");
        } else {
            logger.error("xml����ת��Ϊ����ʱת������");
            throw new RuntimeException("xml����ת��Ϊ����ʱת������");
        }

    }

    public synchronized void handle200001BatchMessage_bak(String message) {
        logger.debug(" ................. ��ʼ�����ص�200001������ѯ����Ϣ........");
        logger.info(message);
        T200001Toa toa = T200001Toa.getToa(message);
        boolean isReset = false;
        boolean isBatchTxOver = true;
        if (toa != null) {
            String msgRetCode = toa.INFO.RET_CODE;
            //  �������
            String txpkgSn = toa.INFO.REQ_SN;
            if (Arrays.asList(RtnCodeEnum.UNIONPAY_TRX_CODE_200001_WAIT.getValues()).contains(msgRetCode)) {
                isBatchTxOver = false;
                appendNewJoblog(txpkgSn, "fip_cutpaybat", "��������", "������������ٴβ�ѯ��");
            } else if (Arrays.asList(RtnCodeEnum.UNIONPAY_TRX_CODE_200001_AGAIN.getValues()).contains(msgRetCode)) {
                isBatchTxOver = false;
                billManagerService.updateCutpaybatToSendflag(txpkgSn, TxSendFlag.UNSEND.getCode());
            } else if (Arrays.asList(RtnCodeEnum.UNIONPAY_TRX_CODE_200001_FAILE.getValues()).contains(msgRetCode)) {
                isReset = true;  // ���
            } else if (Arrays.asList(RtnCodeEnum.UNIONPAY_TRX_CODE_200001_SUCCESS.getValues()).contains(msgRetCode)) {
                for (T200001Toa.Body.BodyDetail detail : toa.BODY.RET_DETAILS) {
                    FipCutpaydetlExample example = new FipCutpaydetlExample();
                    example.createCriteria().andTxpkgSnEqualTo(txpkgSn).andTxpkgDetlSnEqualTo(detail.SN);
                    FipCutpaydetl record = mapper.selectByExample(example).get(0);
                    String retCode = detail.RET_CODE;
                    if (isReset) {
                        record.setTxpkgSn("");
                        record.setTxpkgDetlSn("");
                        record.setSendflag(TxSendFlag.UNSEND.getCode());
                        record.setBillstatus(BillStatus.CUTPAY_QRY_PEND.getCode());
                        record.setTxRetmsg("[ϵͳʧ�ܣ��������]");
                        record.setTxRetcode("");
                    }
                    //  ҵ��ɹ���
                    if (Arrays.asList(RtnCodeEnum.UNIONPAY_TRX_CODE_100001_SUCCESS.getValues()).contains(retCode)) {
                        record.setBillstatus(BillStatus.CUTPAY_SUCCESS.getCode());
                    } else {
                        record.setBillstatus(BillStatus.CUTPAY_FAILED.getCode());
                    }

                    record.setTxRetmsg("[����ͷ��Ϣ]:" + msgRetCode + toa.INFO.ERR_MSG + " [��������Ϣ]:" + detail.ERR_MSG);
                    record.setTxRetcode(String.valueOf(retCode));
                    record.setRecversion(record.getRecversion() + 1);
                    String log = "������������(200001)���: [��������+���:" + record.getTxpkgSn() + record.getBatchDetlSn() + "]" + record.getTxRetmsg();
                    appendNewJoblog(record.getPkid(), "fip_cutpaydetl", "��������", log);

                    mapper.updateByPrimaryKey(record);
                }
            }
            if (isBatchTxOver) {
                FipCutpaybat fipCutpaybat = batMapper.selectByPrimaryKey(txpkgSn);
                fipCutpaybat.setTxpkgStatus(TxpkgStatus.DEAL_SUCCESS.getCode());
                fipCutpaybat.setRecversion(fipCutpaybat.getRecversion() + 1);
                batMapper.updateByPrimaryKey(fipCutpaybat);
            }
            logger.debug(" ................. �����ص���Ϣ����........");
        } else {
            throw new RuntimeException("xml����ת��Ϊ����ʱת������");
        }

    }

    public synchronized void handle100001Message(String message) {
        logger.debug(" ................. ��ʼ�����ص�100001��Ϣ........");
        logger.info(message);
        T100001Toa toa = T100001Toa.getToa(message);
        if (toa != null) {
            String msgRetCode = toa.INFO.RET_CODE;
            boolean isReset = false;
            if (Arrays.asList(RtnCodeEnum.UNIONPAY_TRX_CODE_100001_FAILE.getValues()).contains(msgRetCode)) {
                // ��� ����
                isReset = true;
            }
            boolean isBatchOver = true;
            String txpkgSn = toa.INFO.REQ_SN;
            if (toa.BODY.RET_DETAILS != null && !toa.BODY.RET_DETAILS.isEmpty()) {
                for (T100001Toa.Body.BodyDetail detail : toa.BODY.RET_DETAILS) {
                    FipCutpaydetlExample example = new FipCutpaydetlExample();
                    example.createCriteria().andTxpkgSnEqualTo(txpkgSn).andTxpkgDetlSnEqualTo(detail.SN);
                    FipCutpaydetl record = mapper.selectByExample(example).get(0);

                    String log = null;

                    if (isReset) {
                        record.setTxpkgSn("");
                        record.setTxpkgDetlSn("");
                        record.setSendflag(TxSendFlag.UNSEND.getCode());
                        record.setBillstatus(BillStatus.CUTPAY_QRY_PEND.getCode());
                        record.setTxRetmsg("[ϵͳʧ�ܣ��������]");
                        record.setTxRetcode("");
                    } else {
                        String retCode = detail.RET_CODE;
                        // ����ʧ��
                        if (Arrays.asList(RtnCodeEnum.UNIONPAY_TRX_CODE_100001_FAILE.getValues()).contains(retCode)) {
                            record.setBillstatus(BillStatus.CUTPAY_FAILED.getCode());
                            record.setTxRetmsg("[����ͷ��Ϣ]:" + msgRetCode + toa.INFO.ERR_MSG + " [��������Ϣ]:" + detail.ERR_MSG);
                            record.setTxRetcode(String.valueOf(retCode));
                        } else {
                            isBatchOver = false;
                            // ���������Ϊ���׽����������ѯ
                            record.setBillstatus(BillStatus.CUTPAY_QRY_PEND.getCode());
                            record.setTxRetmsg("[���ͳɹ������׽������������ѯ��]");
                            record.setTxRetcode(String.valueOf(retCode));
                        }
                    }

                    record.setRecversion(record.getRecversion() + 1);
                    log = "������������(100001)���: [��������+���:" + record.getTxpkgSn() + record.getBatchDetlSn() + "]" + record.getTxRetmsg();
                    appendNewJoblog(record.getPkid(), "fip_cutpaydetl", "��������", log);

                    mapper.updateByPrimaryKey(record);
                }
            }
            if (isBatchOver) {
                FipCutpaybat fipCutpaybat = batMapper.selectByPrimaryKey(txpkgSn);
                fipCutpaybat.setTxpkgStatus(TxpkgStatus.DEAL_SUCCESS.getCode());
                fipCutpaybat.setRecversion(fipCutpaybat.getRecversion() + 1);
                batMapper.updateByPrimaryKey(fipCutpaybat);
            }
            logger.debug(" ................. �����ص���Ϣ����........");
        } else {
            throw new RuntimeException("xml����ת��Ϊ����ʱת������");
        }
    }


    //===============================================================================
    private void appendNewJoblog(String pkid, String tableName, String jobname, String jobdesc) {
        jobLogService.insertNewJoblog(pkid, tableName, jobname, jobdesc, "���ݽ���ƽ̨", "���ݽ���ƽ̨");
    }

}