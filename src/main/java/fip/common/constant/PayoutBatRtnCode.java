package fip.common.constant;

import java.util.Hashtable;

/**
 * ����xml���ո����׷�����
 * 0000 ����׶ν��׽���
 */
public enum PayoutBatRtnCode implements EnumApp {
    TXN_OVER("0000", "�������"),
    TXN_HALFWAY("1000", "���״�����"),

    MSG_SAVE_SUCCESS("0010", "�����ѽ��գ�ϵͳ������"),

    MSG_TIME_OUT("1100", "ͨ�ų�ʱ"),

    MSG_VERIFY_ILLEGAL("2001", "������֤ʧ��"),
    MSG_TRANSFORM_EXCEPTION("2010", "����ת������"),
    MSG_PARSE_FAILED("2020", "���Ľ�������"),
    MSG_CONTENT_FAILED("2030", "������������"),
    MSG_REQSN_EXIST("2200", "������ˮ���Ѵ���"),
    MSG_SBS_ACT_NOT_ALLOWED("2210", "�����к���ϵͳ��������иý��׵�SBS�˺�"),
    MSG_SAVE_ERROR("2300", "���ݱ����쳣"),
    MSG_QRYSN_NOT_EXIST("2310", "û�в�ѯ���ñʽ���"),

    UNKNOWN_EXCEPTION("2900", "����δ֪�쳣");

    private String code = null;
    private String title = null;
    private static Hashtable<String, PayoutBatRtnCode> aliasEnums;

    PayoutBatRtnCode(String code, String title) {
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

    public static PayoutBatRtnCode valueOfAlias(String alias) {
        return aliasEnums.get(alias);
    }

    public String getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }

    public String toRtnMsg() {
        return this.code + "|" + this.title;
    }
}
