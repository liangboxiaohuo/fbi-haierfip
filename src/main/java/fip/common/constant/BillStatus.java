package fip.common.constant;

import java.util.Hashtable;

/**
 * Created by IntelliJ IDEA.
 * User: zhanrui
 * Date: 11-7-23
 * Time: ����3:30
 * To change this template use File | Settings | File Templates.
 */
public enum BillStatus implements EnumApp {
    INIT("00", "��ʼ��ȡ"),
    PACKED("01", "�Ѵ��"),
    RESEND_PEND("02", "���ط�"),
    CUTPAY_QRY_PEND("10", "���н������"),
    CUTPAY_FAILED("11", "���д���ʧ��"),
    CUTPAY_SUCCESS("12", "���д���ɹ�"),
    ACCOUNT_PEND("20", "������"),
    ACCOUNT_QRY_PEND("21", "���ʽ������"),
    ACCOUNT_FAILED("22", "����ʧ��"),
    ACCOUNT_SUCCESS("23", "���ʳɹ�"),
    CMS_PEND("30", "����д"),
    CMS_QRY_PEND("31", "��д�������"),
    CMS_FAILED("32", "��дʧ��"),
    CMS_SUCCESS("33", "��д�ɹ�");
    //REFUND_PEND("40", "���˿�"),
    //REFUND_QRY_PEND("41", "�˿�������"),
    //REFUND_FAILED("42", "�˿�ʧ��"),
    //REFUND_SUCCESS("43", "�˿�ɹ�");

    private String code = null;
    private String title = null;
    private static Hashtable<String, BillStatus> aliasEnums;

    BillStatus(String code, String title) {
        this.init(code, title);
    }

    @SuppressWarnings("unchecked")
    private void init(String code, String title) {
        this.code = code;
        this.title = title;
        synchronized (this.getClass()) {
            if (aliasEnums == null) {
                aliasEnums = new Hashtable();
            }
        }
        aliasEnums.put(code, this);
        aliasEnums.put(title, this);
    }

    public static BillStatus valueOfAlias(String alias) {
        return aliasEnums.get(alias);
    }

    public String getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }
}
