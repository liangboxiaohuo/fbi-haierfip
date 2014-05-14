package hfc.common;

import java.util.Hashtable;

/**
 * Created by IntelliJ IDEA.
 * User: zhangxiaobo
 * Date: 11-8-14
 * Time: ����10:58
 * To change this template use File | Settings | File Templates.
 */

/**
 * CMS���ش�����̬
 */
public enum LoanStatusEnum {
    /**
     * 0-����
     * 1-����
     * 2-����
     * 3-����
     * 4-����
     */
    CMS_NORMAL("0", "����"),
    CMS_TIME_BEYOND("1", "����"),
    CMS_IDLE("2", "����"),
    CMS_LIFELESS("3", "����"),
    CMS_SETTLE("4", "����");
    private String code = null;
    private String title = null;
    private static Hashtable<String, LoanStatusEnum> aliasEnums;

    LoanStatusEnum(String code, String title) {
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

    public static LoanStatusEnum valueOfAlias(String alias) {
        return aliasEnums.get(alias);
    }

    public String getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }
}
