package fip.gateway.unionpay;

import fip.common.SystemService;
import fip.common.constant.BizType;
import fip.gateway.unionpay.core.MerchantInfoConstant;
import fip.gateway.unionpay.domain.*;
import fip.repository.model.FipCutpaybat;
import fip.repository.model.FipCutpaydetl;
import fip.repository.model.FipRefunddetl;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: zhangxiaobo
 * Date: 11-8-7
 * Time: ����10:36
 * To change this template use File | Settings | File Templates.
 */
public class CreateMessageHandler {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CreateMessageHandler.class);

    private CreateMessageHandler() {
    }

    public static CreateMessageHandler getInstance() {
        return new CreateMessageHandler();
    }

    /**
     * �������۱��ģ��첽����
     * @param cutpaybat
     * @param cutpaydetlList
     * @return
     */
    @Deprecated
    public  String create100001Msg(FipCutpaybat cutpaybat, List<FipCutpaydetl> cutpaydetlList) {
        T100001Tia tia = new T100001Tia();
        tia.INFO.TRX_CODE = "100001";
        tia.INFO.LEVEL = "5";
        tia.INFO.REQ_SN = cutpaybat.getTxpkgSn();// �������ż�Ϊ������ˮ��
        //�̻�Э��ѡ�� ���������Ի�����
        setMerchantInfo4T100001(cutpaybat.getOriginBizid(), tia);

        tia.BODY.TRANS_SUM.SUBMIT_TIME = SystemService.getDatetime14();
        tia.BODY.TRANS_SUM.TOTAL_ITEM = String.valueOf(cutpaydetlList.size());
        BigDecimal totalAmt = new BigDecimal(0);
        // TODO �ܽ�� /��
        for (FipCutpaydetl record : cutpaydetlList) {
            T100001Tia.Body.BodyDetail detail = new T100001Tia.Body.BodyDetail();
            detail.SN = record.getTxpkgDetlSn();
            detail.BANK_CODE = record.getBiActopeningbank();
            // TODO �˺����� ���п������   detail.ACCOUNT_TYPE = record.
            detail.ACCOUNT_NO = record.getBiBankactno();
            detail.ACCOUNT_NAME = record.getBiBankactname();
            detail.PROVINCE = record.getBiProvince();
            detail.CITY = record.getBiCity();
            detail.BANK_NAME = record.getBiActopeningbankUd();
            detail.ACCOUNT_PROP = "0"; // ����
            detail.AMOUNT = String.valueOf(record.getPaybackamt().multiply(new BigDecimal("100")).longValue());
            totalAmt = totalAmt.add(record.getPaybackamt());
            detail.ID_TYPE = record.getClientidtype();
            detail.ID = record.getClientid();
            // TODO �����ǿպ���
            tia.BODY.TRANS_DETAILS.add(detail);
        }
        tia.BODY.TRANS_SUM.TOTAL_SUM = String.valueOf(totalAmt.multiply(new BigDecimal("100")).longValue());

        return tia.toString();
    }

    private void setMerchantInfo4T100001(String bizid, T100001Tia tia) {
        if (bizid.equals(BizType.FD.getCode())) {          //����
            tia.INFO.USER_NAME = MerchantInfoConstant.FD_USER_NAME;
            tia.INFO.USER_PASS = MerchantInfoConstant.FD_USER_PASS;
            tia.BODY.TRANS_SUM.BUSINESS_CODE = MerchantInfoConstant.FD_BUSINESS_CODE;
            tia.BODY.TRANS_SUM.MERCHANT_ID = MerchantInfoConstant.FD_MERCHANT_ID;
        } else if (bizid.equals(BizType.XF.getCode())) {    //�����Ŵ�
            tia.INFO.USER_NAME = MerchantInfoConstant.XF_USER_NAME;
            tia.INFO.USER_PASS = MerchantInfoConstant.XF_USER_PASS;
            tia.BODY.TRANS_SUM.BUSINESS_CODE = MerchantInfoConstant.XF_BUSINESS_CODE;
            tia.BODY.TRANS_SUM.MERCHANT_ID = MerchantInfoConstant.XF_MERCHANT_ID;
        } else if (bizid.equals(BizType.XFSF.getCode())) {  //�����Ŵ��׸�
            tia.INFO.USER_NAME = MerchantInfoConstant.XFSF_USER_NAME;
            tia.INFO.USER_PASS = MerchantInfoConstant.XFSF_USER_PASS;
            tia.BODY.TRANS_SUM.BUSINESS_CODE = MerchantInfoConstant.XFSF_BUSINESS_CODE;
            tia.BODY.TRANS_SUM.MERCHANT_ID = MerchantInfoConstant.XFSF_MERCHANT_ID;
        } else {  //����
            tia.INFO.USER_NAME = MerchantInfoConstant.TEST_USER_NAME;
            tia.INFO.USER_PASS = MerchantInfoConstant.TEST_USER_PASS;
            tia.BODY.TRANS_SUM.BUSINESS_CODE = MerchantInfoConstant.TEST_BUSINESS_CODE;
            tia.BODY.TRANS_SUM.MERCHANT_ID = MerchantInfoConstant.TEST_MERCHANT_ID;
        }
    }

    /**
     * ʵʱ���ձ��� 10004  ���ʱ���
     * @param record
     * @return
     */
    @Deprecated
    public  String create100004Msg(FipCutpaydetl record) {
        T100004Tia tia = new T100004Tia();
        tia.INFO = new T100004Tia.TiaHeader();
        tia.INFO.TRX_CODE = "100004";
        tia.INFO.REQ_SN = record.getBatchSn() + record.getBatchDetlSn();

        tia.BODY = new T100004Tia.Body();
        tia.BODY.TRANS_SUM = new T100004Tia.Body.BodyHeader();

        //�̻�Э��ѡ�� ���������Ի�����
        setMerchantInfo4T100004(record.getOriginBizid(), tia);

        tia.BODY.TRANS_SUM.SUBMIT_TIME = SystemService.getDatetime14();
        long amt = record.getPaybackamt().multiply(new BigDecimal("100")).longValue();
        tia.BODY.TRANS_SUM.TOTAL_SUM = String.valueOf(amt);
        tia.BODY.TRANS_DETAILS = new ArrayList<T100004Tia.Body.BodyDetail>();
        T100004Tia.Body.BodyDetail detail = new T100004Tia.Body.BodyDetail();
        detail.SN = "0001";
        detail.ACCOUNT_NO = record.getBiBankactno();
        detail.ACCOUNT_NAME = record.getBiBankactname();
        detail.AMOUNT = String.valueOf(amt);
        detail.BANK_CODE = record.getBiActopeningbank();
        detail.RESERVE1 = String.valueOf(record.getRecversion());
        tia.BODY.TRANS_DETAILS.add(detail);
        return tia.toString();
    }

    /**
     * ʵʱ���۱���  ͬ��  ���
     * @param cutpaybat
     * @param cutpaydetlList
     * @return
     */
    public  String create100004Msg(FipCutpaybat cutpaybat, List<FipCutpaydetl> cutpaydetlList) {
        T100004Tia tia = new T100004Tia();
        tia.INFO.TRX_CODE = "100004";
        tia.INFO.LEVEL = "5";
        tia.INFO.REQ_SN = cutpaybat.getTxpkgSn();// �������ż�Ϊ������ˮ��
        //�̻�Э��ѡ�� ���������Ի�����
        setMerchantInfo4T100004(cutpaybat.getOriginBizid(), tia);

        tia.BODY.TRANS_SUM.SUBMIT_TIME = SystemService.getDatetime14();
        tia.BODY.TRANS_SUM.TOTAL_ITEM = String.valueOf(cutpaydetlList.size());
        BigDecimal totalAmt = new BigDecimal(0);
        // TODO �ܽ�� /��
        for (FipCutpaydetl record : cutpaydetlList) {
            T100004Tia.Body.BodyDetail detail = new T100004Tia.Body.BodyDetail();
            detail.SN = record.getTxpkgDetlSn();
            detail.BANK_CODE = record.getBiActopeningbank();
            // TODO �˺����� ���п������   detail.ACCOUNT_TYPE = record.
            detail.ACCOUNT_NO = record.getBiBankactno();
            detail.ACCOUNT_NAME = record.getBiBankactname();
            //detail.PROVINCE = record.getBiProvince();
            //detail.CITY = record.getBiCity();
            //detail.BANK_NAME = record.getBiActopeningbankUd();
            detail.ACCOUNT_PROP = "0"; // ����
            detail.AMOUNT = String.valueOf(record.getPaybackamt().multiply(new BigDecimal("100")).longValue());
            totalAmt = totalAmt.add(record.getPaybackamt());
            //detail.ID_TYPE = record.getClientidtype();
            //detail.ID = record.getClientid();
            // TODO �����ǿպ���
            tia.BODY.TRANS_DETAILS.add(detail);
        }
        tia.BODY.TRANS_SUM.TOTAL_SUM = String.valueOf(totalAmt.multiply(new BigDecimal("100")).longValue());

        return tia.toString();
    }


    /**
     * ����ҵ������ѡ���̻���Ϣ
     *
     * @param bizid
     * @param tia
     */
    private void setMerchantInfo4T100004(String bizid, T100004Tia tia) {
        if (bizid.equals(BizType.FD.getCode())) {          //����
            tia.INFO.USER_NAME = MerchantInfoConstant.FD_USER_NAME;
            tia.INFO.USER_PASS = MerchantInfoConstant.FD_USER_PASS;
            tia.BODY.TRANS_SUM.BUSINESS_CODE = MerchantInfoConstant.FD_BUSINESS_CODE;
            tia.BODY.TRANS_SUM.MERCHANT_ID = MerchantInfoConstant.FD_MERCHANT_ID;
        } else if (bizid.equals(BizType.XF.getCode())) {    //�����Ŵ�
            tia.INFO.USER_NAME = MerchantInfoConstant.XF_USER_NAME;
            tia.INFO.USER_PASS = MerchantInfoConstant.XF_USER_PASS;
            tia.BODY.TRANS_SUM.BUSINESS_CODE = MerchantInfoConstant.XF_BUSINESS_CODE;
            tia.BODY.TRANS_SUM.MERCHANT_ID = MerchantInfoConstant.XF_MERCHANT_ID;
        } else if (bizid.equals(BizType.XFSF.getCode())) {  //�����Ŵ��׸�
            tia.INFO.USER_NAME = MerchantInfoConstant.XFSF_USER_NAME;
            tia.INFO.USER_PASS = MerchantInfoConstant.XFSF_USER_PASS;
            tia.BODY.TRANS_SUM.BUSINESS_CODE = MerchantInfoConstant.XFSF_BUSINESS_CODE;
            tia.BODY.TRANS_SUM.MERCHANT_ID = MerchantInfoConstant.XFSF_MERCHANT_ID;
        } else {  //����
            tia.INFO.USER_NAME = MerchantInfoConstant.TEST_USER_NAME;
            tia.INFO.USER_PASS = MerchantInfoConstant.TEST_USER_PASS;
            tia.BODY.TRANS_SUM.BUSINESS_CODE = MerchantInfoConstant.TEST_BUSINESS_CODE;
            tia.BODY.TRANS_SUM.MERCHANT_ID = MerchantInfoConstant.TEST_MERCHANT_ID;
        }
    }

    /**
     * ʵʱ���Ĳ�ѯ
     * @param record
     * @return
     */
    public  String create200001Msg(FipCutpaydetl record) {
        T200001Tia tia = new T200001Tia();
        tia.INFO = new T200001Tia.TiaHeader();
        tia.INFO.TRX_CODE = "200001";
        tia.INFO.REQ_SN = System.currentTimeMillis() + "";

        setMerchantInfo4T200001(record.getOriginBizid(), tia);

        tia.BODY = new T200001Tia.Body();
        tia.INFO.VERSION = "03";
        tia.BODY.QUERY_TRANS = new T200001Tia.Body.BodyHeader();

        // TODO
        tia.BODY.QUERY_TRANS.QUERY_REMARK = "1";
        tia.BODY.QUERY_TRANS.QUERY_SN = record.getBatchSn() + record.getBatchDetlSn();

        return tia.toString();
    }

    /**
     * �������Ĳ�ѯ
     * @param record
     * @return
     */

    public synchronized String create200001Msg(FipCutpaybat record) {

        T200001Tia tia = new T200001Tia();
        tia.INFO = new T200001Tia.TiaHeader();
        tia.INFO.TRX_CODE = "200001";

        //TODO
        tia.INFO.REQ_SN = record.getTxpkgSn();
//        tia.INFO.REQ_SN = System.currentTimeMillis() + "";

        setMerchantInfo4T200001(record.getOriginBizid(), tia);

        tia.BODY = new T200001Tia.Body();
        tia.INFO.VERSION = "03";
        tia.BODY.QUERY_TRANS = new T200001Tia.Body.BodyHeader();

        tia.BODY.QUERY_TRANS.QUERY_REMARK = "2";
        tia.BODY.QUERY_TRANS.QUERY_SN = record.getTxpkgSn();

        return tia.toString();
    }
    private void setMerchantInfo4T200001(String bizid, T200001Tia tia) {
        if (bizid.equals(BizType.FD.getCode())) {          //����
            tia.INFO.USER_NAME = MerchantInfoConstant.FD_USER_NAME;
            tia.INFO.USER_PASS = MerchantInfoConstant.FD_USER_PASS;
        } else if (bizid.equals(BizType.XF.getCode())) {    //�����Ŵ�
            tia.INFO.USER_NAME = MerchantInfoConstant.XF_USER_NAME;
            tia.INFO.USER_PASS = MerchantInfoConstant.XF_USER_PASS;
        } else if (bizid.equals(BizType.XFSF.getCode())) {  //�����Ŵ��׸�
            tia.INFO.USER_NAME = MerchantInfoConstant.XFSF_USER_NAME;
            tia.INFO.USER_PASS = MerchantInfoConstant.XFSF_USER_PASS;
        } else {  //����
            tia.INFO.USER_NAME = MerchantInfoConstant.TEST_USER_NAME;
            tia.INFO.USER_PASS = MerchantInfoConstant.TEST_USER_PASS;
        }
    }
    /**
     * ��ʷ���׽����ѯ����
     * @param record
     * @return
     */


    /**
     * ������������  T100002   ���ʴ���ģʽ
     * @param record
     * @return
     * @throws ParseException
     */
    public synchronized String create100002Msg(FipRefunddetl record) {
        T100002Tia tia = new T100002Tia();
        tia.INFO = new T100002Tia.TiaHeader();
        tia.INFO.TRX_CODE = "100002";
        tia.INFO.REQ_SN = record.getBatchSn() + record.getBatchDetlSn();

        tia.BODY = new T100002Tia.Body();
        tia.BODY.TRANS_SUM = new T100002Tia.Body.BodyHeader();

        //�̻�Э��ѡ�� ���������Ի�����
        setMerchantInfo4T100002(record.getOriginBizid(), tia);

        tia.BODY.TRANS_SUM.SUBMIT_TIME = SystemService.getDatetime14();
        long amt = record.getPayamt().multiply(new BigDecimal("100")).longValue();
        tia.BODY.TRANS_SUM.TOTAL_SUM = String.valueOf(amt);
        tia.BODY.TRANS_DETAILS = new ArrayList<T100002Tia.Body.BodyDetail>();
        T100002Tia.Body.BodyDetail detail = new T100002Tia.Body.BodyDetail();
        detail.SN = "0001";
        detail.ACCOUNT_NO = record.getBiBankactno();
        detail.ACCOUNT_NAME = record.getClientname();
        detail.AMOUNT = String.valueOf(amt);
        detail.BANK_CODE = record.getBiActopeningbank();
        detail.RESERVE1 = String.valueOf(record.getRecversion());
        tia.BODY.TRANS_DETAILS.add(detail);
        return tia.toString();
    }

    /**
     * ����ҵ������ѡ���̻���Ϣ     T100002
     *
     * @param bizid
     * @param tia
     */
    private void setMerchantInfo4T100002(String bizid, T100002Tia tia) {
        if (bizid.equals(BizType.FD.getCode())) {          //����
            tia.INFO.USER_NAME = MerchantInfoConstant.FD_USER_NAME;
            tia.INFO.USER_PASS = MerchantInfoConstant.FD_USER_PASS;
            tia.BODY.TRANS_SUM.BUSINESS_CODE = MerchantInfoConstant.FD_BUSINESS_CODE;
            tia.BODY.TRANS_SUM.MERCHANT_ID = MerchantInfoConstant.FD_MERCHANT_ID;
        } else if (bizid.equals(BizType.XF.getCode())) {    //�����Ŵ�
            tia.INFO.USER_NAME = MerchantInfoConstant.XF_USER_NAME;
            tia.INFO.USER_PASS = MerchantInfoConstant.XF_USER_PASS;
            tia.BODY.TRANS_SUM.BUSINESS_CODE = MerchantInfoConstant.XF_BUSINESS_CODE;
            tia.BODY.TRANS_SUM.MERCHANT_ID = MerchantInfoConstant.XF_MERCHANT_ID;
        } else if (bizid.equals(BizType.XFSF.getCode())) {  //�����Ŵ��׸�
            tia.INFO.USER_NAME = MerchantInfoConstant.XFSF_USER_NAME;
            tia.INFO.USER_PASS = MerchantInfoConstant.XFSF_USER_PASS;
            tia.BODY.TRANS_SUM.BUSINESS_CODE = MerchantInfoConstant.XFSF_BUSINESS_CODE;
            tia.BODY.TRANS_SUM.MERCHANT_ID = MerchantInfoConstant.XFSF_MERCHANT_ID;
        } else {  //����
            tia.INFO.USER_NAME = MerchantInfoConstant.TEST_USER_NAME;
            tia.INFO.USER_PASS = MerchantInfoConstant.TEST_USER_PASS;
            tia.BODY.TRANS_SUM.BUSINESS_CODE = MerchantInfoConstant.TEST_BUSINESS_CODE;
            tia.BODY.TRANS_SUM.MERCHANT_ID = MerchantInfoConstant.TEST_MERCHANT_ID;
        }
    }

    /**
     * ʵʱ��������
     * @param record
     * @return
     * @throws ParseException
     */
    public synchronized String create100005Msg(FipRefunddetl record) {
        T100005Tia tia = new T100005Tia();
        tia.INFO = new T100005Tia.TiaHeader();
        tia.INFO.TRX_CODE = "100005";
        tia.INFO.REQ_SN = record.getBatchSn() + record.getBatchDetlSn();

        tia.BODY = new T100005Tia.Body();
        tia.BODY.TRANS_SUM = new T100005Tia.Body.BodyHeader();

        //�̻�Э��ѡ�� ���������Ի�����
        setMerchantInfo4T100005(record.getOriginBizid(), tia);

        tia.BODY.TRANS_SUM.SUBMIT_TIME = SystemService.getDatetime14();
        long amt = record.getPayamt().multiply(new BigDecimal("100")).longValue();
        tia.BODY.TRANS_SUM.TOTAL_SUM = String.valueOf(amt);
        tia.BODY.TRANS_DETAILS = new ArrayList<T100005Tia.Body.BodyDetail>();
        T100005Tia.Body.BodyDetail detail = new T100005Tia.Body.BodyDetail();
        detail.SN = "0001";
        detail.ACCOUNT_NO = record.getBiBankactno();
        detail.ACCOUNT_NAME = record.getClientname();
        detail.AMOUNT = String.valueOf(amt);
        detail.BANK_CODE = record.getBiActopeningbank();
        detail.RESERVE1 = String.valueOf(record.getRecversion());
        tia.BODY.TRANS_DETAILS.add(detail);
        return tia.toString();
    }

    /**
     * ����ҵ������ѡ���̻���Ϣ     T100005
     *
     * @param bizid
     * @param tia
     */
    private void setMerchantInfo4T100005(String bizid, T100005Tia tia) {
        if (bizid.equals(BizType.FD.getCode())) {          //����
            tia.INFO.USER_NAME = MerchantInfoConstant.FD_USER_NAME;
            tia.INFO.USER_PASS = MerchantInfoConstant.FD_USER_PASS;
            tia.BODY.TRANS_SUM.BUSINESS_CODE = MerchantInfoConstant.FD_BUSINESS_CODE;
            tia.BODY.TRANS_SUM.MERCHANT_ID = MerchantInfoConstant.FD_MERCHANT_ID;
        } else if (bizid.equals(BizType.XF.getCode())) {    //�����Ŵ�
            tia.INFO.USER_NAME = MerchantInfoConstant.XF_USER_NAME;
            tia.INFO.USER_PASS = MerchantInfoConstant.XF_USER_PASS;
            tia.BODY.TRANS_SUM.BUSINESS_CODE = MerchantInfoConstant.XF_BUSINESS_CODE;
            tia.BODY.TRANS_SUM.MERCHANT_ID = MerchantInfoConstant.XF_MERCHANT_ID;
        } else if (bizid.equals(BizType.XFSF.getCode())) {  //�����Ŵ��׸�
            tia.INFO.USER_NAME = MerchantInfoConstant.XFSF_USER_NAME;
            tia.INFO.USER_PASS = MerchantInfoConstant.XFSF_USER_PASS;
            tia.BODY.TRANS_SUM.BUSINESS_CODE = MerchantInfoConstant.XFSF_BUSINESS_CODE;
            tia.BODY.TRANS_SUM.MERCHANT_ID = MerchantInfoConstant.XFSF_MERCHANT_ID;
        } else {  //����
            tia.INFO.USER_NAME = MerchantInfoConstant.TEST_USER_NAME;
            tia.INFO.USER_PASS = MerchantInfoConstant.TEST_USER_PASS;
            tia.BODY.TRANS_SUM.BUSINESS_CODE = MerchantInfoConstant.TEST_BUSINESS_CODE;
            tia.BODY.TRANS_SUM.MERCHANT_ID = MerchantInfoConstant.TEST_MERCHANT_ID;
        }
    }

}
