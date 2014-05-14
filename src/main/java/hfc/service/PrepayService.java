package hfc.service;

import fip.common.SystemService;
import fip.common.constant.BillStatus;
import fip.common.constant.BillType;
import fip.common.constant.BizType;
import fip.common.constant.CutpayChannel;
import fip.repository.dao.FipCutpaydetlMapper;
import fip.repository.dao.FipJoblogMapper;
import fip.repository.dao.XfappMapper;
import fip.repository.dao.XfapprepaymentMapper;
import fip.repository.model.*;
import fip.service.fip.BillManagerService;
import hfc.common.AppPrepayStatus;
import hfc.common.AppStatus;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: zhanrui
 * Date: 11-8-27
 * Time: ����4:31
 * To change this template use File | Settings | File Templates.
 */
@Service
public class PrepayService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BillManagerService billManagerService;
    @Autowired
    private XfappMapper xfappmapper;

    @Autowired
    private FipJoblogMapper fipJoblogMapper;
    @Autowired
    private XfapprepaymentMapper xfapprepaymentMapper;
    @Autowired
    private FipCutpaydetlMapper fipCutpaydetlMapper;


    public List<Xfapp> selectPendCutpayList(AppStatus appStatus, AppPrepayStatus prepayStatus) {
        XfappExample example = new XfappExample();
        example.createCriteria()
                .andAppstatusEqualTo(appStatus.getCode())
                .andPrepayCutpayTypeEqualTo("1")
                .andPrepayStatusEqualTo(prepayStatus.getCode());
        return xfappmapper.selectByExample(example);
    }
    public List<Xfapp> selectPendCutpayList(AppPrepayStatus status) {
        XfappExample example = new XfappExample();
        example.createCriteria()
                .andPrepayStatusEqualTo(status.getCode())
                .andPrepayCutpayTypeEqualTo("1");
        return xfappmapper.selectByExample(example);
    }

    public void updateXfappPrepayStatus(String pkid, AppPrepayStatus status){
        Xfapp record = xfappmapper.selectByPrimaryKey(pkid);
        record.setPrepayStatus(status.getCode());

        xfappmapper.updateByPrimaryKey(record);
    }

    /**
     * ���ݴ��۵Ľ������ ���뵥�� �׸���״̬
     *
     * @param appList
     */
    @Transactional
    public void updateXfappPrepayCutpayStatusToSuccess(List<Xfapp> appList) {
        Date date = new Date();
        String userid = SystemService.getOperatorManager().getOperatorId();
        String username = SystemService.getOperatorManager().getOperatorName();

        for (Xfapp xfapp : appList) {
            xfapp.setPrepayStatus("1");
            xfappmapper.updateByPrimaryKey(xfapp);
            FipJoblog log = new FipJoblog();
            log.setTablename("xfapp");
            log.setRowpkid(xfapp.getPkid());
            log.setJobname("�׸�����");
            log.setJobdesc("�׸����۳ɹ�");
            log.setJobtime(date);
            log.setJobuserid(userid);
            log.setJobusername(username);
            fipJoblogMapper.insert(log);
        }

    }

    public void generateCutpayRecords(List<Xfapp> appList) {
        int count = 0;
        String batchno = billManagerService.generateBatchno();
        int iSeqno = 0;

        Date date = new Date();
        String userid = SystemService.getOperatorManager().getOperatorId();
        String username = SystemService.getOperatorManager().getOperatorName();

        for (Xfapp xfapp : appList) {
            iSeqno++;
            FipCutpaydetl cutpaydetl = assembleCutpayRecord(BizType.XFSF.getCode(),
                    batchno, iSeqno, BillType.NORMAL.getCode(), xfapp);
            if (cutpaydetl == null) {
                continue;
            }
            fipCutpaydetlMapper.insert(cutpaydetl);
            //����XFAPP��״̬
            updateXfappPrepayStatus(xfapp.getPkid(), AppPrepayStatus.CUTPAY_RECORD_GENERATED);

            FipJoblog log = new FipJoblog();
            log.setTablename("xfapp");
            log.setRowpkid(xfapp.getPkid());
            log.setJobname("�׸�����");
            log.setJobdesc("�����׸����ۼ�¼");
            log.setJobtime(date);
            log.setJobuserid(userid);
            log.setJobusername(username);
            fipJoblogMapper.insert(log);
        }
    }

    private FipCutpaydetl assembleCutpayRecord(String bizID,
                                               String batchSn,
                                               int iBatchDetlSn,
                                               String billType,
                                               Xfapp appRecord) {
        FipCutpaydetl cutpaydetl = new FipCutpaydetl();
        cutpaydetl.setOriginBizid(bizID);
        cutpaydetl.setBatchSn(batchSn);
        String seqno = "" + iBatchDetlSn;
        cutpaydetl.setBatchDetlSn(StringUtils.leftPad(seqno, 7, "0"));

        cutpaydetl.setXfappPkid(appRecord.getPkid()); //���뵥��ˮ��
        cutpaydetl.setAppno(appRecord.getAppno()); //���뵥��

        cutpaydetl.setIouno("000000"); //��ݺ�
        cutpaydetl.setPoano("00000");  //�ڴκ�
        cutpaydetl.setContractno("000000"); //��ͬ��
        cutpaydetl.setPaybackdate(new SimpleDateFormat("yyyy-MM-dd").format(new Date())); //�ƻ�������

        //�ͻ���Ϣ
        cutpaydetl.setClientno(appRecord.getClientno().toString());
        cutpaydetl.setClientname(appRecord.getName());

        //SBS������Ϣ
        cutpaydetl.setClientact("111111");   //�����ʺ�

        //��������Ϣ
        cutpaydetl.setPaybackamt(appRecord.getReceiveamt());  //������
        cutpaydetl.setPrincipalamt(new BigDecimal(0)); //�����
        cutpaydetl.setInterestamt(new BigDecimal(0));  //������Ϣ
        cutpaydetl.setPunitiveintamt(new BigDecimal(0));//��Ϣ���
        cutpaydetl.setReserveamt(new BigDecimal(0));  //������

        //����������Ϣ
        Xfapprepayment xfapprepayment = xfapprepaymentMapper.selectByPrimaryKey(cutpaydetl.getAppno());

        String channel = xfapprepayment.getChannel();
        if (StringUtils.isEmpty(channel)) {
            logger.error("���Ŵ���ȡ�ۿ��¼����������Ϊ��." + cutpaydetl.getClientname());
            channel = CutpayChannel.NONE.getCode();
        }
        cutpaydetl.setBiChannel(channel);
        cutpaydetl.setBiActopeningbank(xfapprepayment.getActopeningbank());
        cutpaydetl.setBiBankactno(xfapprepayment.getBankactno());
        cutpaydetl.setBiBankactname(xfapprepayment.getBankactname());
        cutpaydetl.setBiActopeningbankUd(xfapprepayment.getActopeningbankUd());
        cutpaydetl.setBiCustomerCode(xfapprepayment.getCustomerCode());
        cutpaydetl.setBiSignAccountNo(xfapprepayment.getSignAccountNo());
        cutpaydetl.setBiProvince(xfapprepayment.getProvince());
        cutpaydetl.setBiCity(xfapprepayment.getCity());

        //����
        cutpaydetl.setRecversion((long) 0);
        cutpaydetl.setDeletedflag("0");
        cutpaydetl.setArchiveflag("0");
        //�ʵ�״̬
        cutpaydetl.setBillstatus(BillStatus.INIT.getCode());
        cutpaydetl.setSendflag("0");

        //�ʵ�����
        cutpaydetl.setBilltype(billType);

        return cutpaydetl;
    }


}
