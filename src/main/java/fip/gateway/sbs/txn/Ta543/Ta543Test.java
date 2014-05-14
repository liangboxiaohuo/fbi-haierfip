package fip.gateway.sbs.txn.Ta543;

import fip.common.constant.BizType;
import fip.gateway.sbs.core.SBSRequest;
import fip.gateway.sbs.core.SBSResponse4SingleRecord;
import fip.gateway.sbs.core.SOFDataDetail;
import fip.repository.model.FipCutpaydetl;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * �����
 * User: zhanrui
 * Date: 11-6-14
 * Time: ����3:30
 * To change this template use File | Settings | File Templates.
 */
public class Ta543Test {
    private static Logger logger = LoggerFactory.getLogger(Ta543Test.class);

    public static void main(String[] argv) {
        Ta543Handler handler = new Ta543Handler();

        FipCutpaydetl cutpaydetl = new FipCutpaydetl();
        SBSRequest request = new SBSRequest("a543", assembleTa543Param(cutpaydetl));

        SBSResponse4SingleRecord response = new SBSResponse4SingleRecord();

        SOFDataDetail sofDataDetail = new Ta543SOFDataDetail();

        response.setSofDataDetail(sofDataDetail);

        handler.run(request, response);
        logger.debug("formcode:"+ response.getFormcode());
    }

    private static List<String> assembleTa543Param(FipCutpaydetl cutpaydetl) {
        DecimalFormat df = new DecimalFormat("#############0.00");
        List<String> txnparamList = new ArrayList<String>();

        String txndate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        //String interbankAccount = getInterbankAccount(cutpaydetl);
        String interbankAccount = "801011112222333344";
        cutpaydetl.setSbsInterbankActno(interbankAccount);

        //1 ��������
        txnparamList.add(txndate);

        //2 ��Χϵͳ��ˮ��
        txnparamList.add(cutpaydetl.getBatchSn() + cutpaydetl.getBatchDetlSn());

        //3 �����˺�
        txnparamList.add(cutpaydetl.getClientact());

        //4 ������   S9(13).99
        String amt = df.format(cutpaydetl.getPrincipalamt());
        txnparamList.add("+" + StringUtils.leftPad(amt, 16, '0'));

        //5 ΥԼ�����Ϣ/Ӧ��Ϣ��
        amt = df.format(cutpaydetl.getInterestamt());
        txnparamList.add("+" + StringUtils.leftPad(amt, 16, '0'));

        //6 ���ɽ����Ϣ��
        amt = df.format(cutpaydetl.getPunitiveintamt());
        txnparamList.add("+" + StringUtils.leftPad(amt, 16, '0'));

        //7 �����ѽ�������
        amt = "0.00";
        txnparamList.add("+" + StringUtils.leftPad(amt, 16, '0'));

        //8 ժҪ
        txnparamList.add(StringUtils.leftPad("", 30, ' '));

        //9 ���𻹿��˺�  ���뻹������˺Ż�����ͬҵ�˺�
        txnparamList.add(interbankAccount); //ͬҵ�˺�

        //10 �������� 04��˽���Ѵ��� 05��˽���Ҵ���
        if (cutpaydetl.getOriginBizid().equals(BizType.FD.getCode())) {
            txnparamList.add("05");
        }else if (cutpaydetl.getOriginBizid().equals(BizType.XF.getCode())){
            txnparamList.add("04");
        }

        //11 ��Ϣ�����˺�  ���뻹������˺Ż�����ͬҵ�˺�
        txnparamList.add(interbankAccount); //ͬҵ�˺�

        return txnparamList;
    }

}
