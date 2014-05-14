package fip.service.zyqd;

import fip.common.SystemService;
import fip.gateway.sbs.txn.Taa56.Taa56Handler;
import fip.repository.dao.FipJoblogMapper;
import fip.repository.model.FipJoblog;
import fip.repository.model.fip.UnipayQryResult;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhanrui on 14-1-17.
 */

@Service
public class ZyqdSbsService {
    private static final Logger logger = LoggerFactory.getLogger(ZyqdSbsService.class);

    @Autowired
    private FipJoblogMapper fipJoblogMapper;


    /**
     * ����  aa56����  (����ͬҵ�ʻ����̻��ʻ�֮���ת��)
     * һ��һ�ύ�� �������ݿ�������
     */
    public void account_aa56(List<UnipayQryResult> detlList, String outActno, String inActno) {
        String userid = SystemService.getOperatorManager().getOperatorId();
        String username = SystemService.getOperatorManager().getOperatorName();
        FipJoblog joblog = new FipJoblog();

        //��������
        Taa56Handler handler = new Taa56Handler();

/*
        for (UnipayQryResult detl : detlList) {
            List<String> txnparamList = assemble_aa56(detl, outActno, inActno);

            SBSRequest request = new SBSRequest("aa56", txnparamList);
            SBSResponse4SingleRecord response = new SBSResponse4SingleRecord();
            Taa56SOFDataDetail sofDataDetail = new Taa56SOFDataDetail();
            response.setSofDataDetail(sofDataDetail);

            handler.run(request, response);

            String formcode = response.getFormcode();
            logger.debug("formcode:" + formcode);
            if (!formcode.equals("T531")) {     //�쳣�������
                detl.setBillstatus(BillStatus.ACCOUNT_FAILED.getCode());
                joblog.setJobdesc("SBS����ʧ�ܣ�FORMCODE=" + formcode);
            } else {
                if (sofDataDetail.getSECNUM().trim().equals(detl.getBatchSn() + detl.getBatchDetlSn())) {
                    detl.setBillstatus(BillStatus.ACCOUNT_SUCCESS.getCode());
                    detl.setDateSbsAct(new Date());
                    joblog.setJobdesc("SBS���ʳɹ���FORMCODE=" + formcode + " ͬҵ�ʺ�:" + detl.getSbsInterbankActno());
                } else {
                    logger.error("SBS���ʳɹ�,�����ص���ˮ�ų������ѯ��" + detl.getBatchSn() + detl.getBatchDetlSn());
                    joblog.setJobdesc("SBS���ʳɹ�,�����ص���ˮ�ų������ѯ��" + sofDataDetail.getSECNUM());
                    detl.setBillstatus(BillStatus.ACCOUNT_SUCCESS.getCode());
                    detl.setDateSbsAct(new Date());
                }
            }
            joblog.setTablename("fip_cutpaydetl");
            joblog.setRowpkid(detl.getPkid());
            joblog.setJobname("SBS����");
            joblog.setJobtime(new Date());
            joblog.setJobuserid(userid);
            joblog.setJobusername(username);
            fipJoblogMapper.insert(joblog);
            fipCutpaydetlMapper.updateByPrimaryKey(detl);
        }
*/
    }

    private List<String> assemble_aa56(UnipayQryResult cutpaydetl, String outActno, String inActno) {
        DecimalFormat df = new DecimalFormat("#############0.00");
        List<String> txnparamList = new ArrayList<String>();

        String txndate = new SimpleDateFormat("yyyyMMdd").format(new Date());

        //ת���ʻ�����
        txnparamList.add("01");

        //ת���ʻ�
        txnparamList.add(outActno); //ͬҵ�˺�

        //ȡ�ʽ
        txnparamList.add("3");
        //ת���ʻ�����
        txnparamList.add(StringUtils.leftPad("", 72, ' '));
        //ȡ������
        txnparamList.add(StringUtils.leftPad("", 6, ' '));
        //֤������
        txnparamList.add("N");

        //��Χϵͳ��ˮ��
        txnparamList.add(cutpaydetl.getSN().substring(4));      //������ˮ��, ȥ��ǰ��λ����

        //֧Ʊ����
        txnparamList.add(" ");
        //֧Ʊ��
        txnparamList.add(StringUtils.leftPad("", 10, ' '));
        //֧Ʊ����
        txnparamList.add(StringUtils.leftPad("", 12, ' '));

        //ǩ������
        txnparamList.add(StringUtils.leftPad("", 8, ' '));
        //���۱�ʶ
        txnparamList.add("3");
        //�����ֶ�
        txnparamList.add(StringUtils.leftPad("", 8, ' '));
        //�����ֶ�
        txnparamList.add(StringUtils.leftPad("", 4, ' '));

        //���׽��
        String amt = df.format(cutpaydetl.getAMOUNT());
        txnparamList.add("+" + StringUtils.leftPad(amt, 16, '0'));   //���

        //ת���ʻ�����
        txnparamList.add("01");

        //ת���ʻ� (�̻��ʺ�)
        String account = StringUtils.rightPad(inActno, 22, ' ');
        txnparamList.add(account);

        //ת���ʻ�����
        txnparamList.add(StringUtils.leftPad("", 72, ' '));
        //���۱�ʶ
        txnparamList.add(" ");
        //��������
        txnparamList.add(txndate);

        //ժҪ(��ˮ��)
        txnparamList.add(StringUtils.leftPad(cutpaydetl.getSN(), 30, ' '));
        //��Ʒ��
        txnparamList.add(StringUtils.leftPad("", 4, ' '));
        //MAGFL1
        txnparamList.add(" ");
        //MAGFL2
        txnparamList.add(" ");
        //��������
        txnparamList.add("   ");
        return txnparamList;
    }

}
