package fip.common.constant;

import java.util.Hashtable;

/**
 * Created by IntelliJ IDEA.
 * User: zhanrui
 * Date: 11-7-23
 * Time: ����3:30
 * To change this template use File | Settings | File Templates.
 */
public enum TxpkgStatus implements EnumApp {

    SEND_PEND("0", "������"),
    QRY_PEND("1", "����ѯ"),
    DEAL_SUCCESS("2", "����ɹ�"),
    DEAL_FAIL("3", "����ʧ��");

    private String code = null;
    private String title = null;
    private static Hashtable<String, TxpkgStatus> aliasEnums;

    TxpkgStatus(String code, String title) {
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

    public static TxpkgStatus valueOfAlias(String alias) {
        return aliasEnums.get(alias);
    }

    public String getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }
}
