package fip.common.constant;

import java.util.Hashtable;

/**
 * ���׽���0-��ʼ�����գ���10��-SBSN057 ��20��-���͵����� ��30��-SBSN058 ��40��-SBSN059
 */
public enum PayoutBatTxnStep implements EnumApp {
    INIT("00", "��ʼ���ջ����ͨ��"),
    SBSN057("10", "SBSN057"),
    UNIONPAY_TXN_PAYOUT("20", "���͵�����"),
    UNIONPAY_TXN_OVER("32", "�������������ȷ"),
    ALL_TXN_OVER("50", "�������"),
    LAND("99", "��ش����");

    private String code = null;
    private String title = null;
    private static Hashtable<String, PayoutBatTxnStep> aliasEnums;

    PayoutBatTxnStep(String code, String title) {
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

    public static PayoutBatTxnStep valueOfAlias(String alias) {
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
