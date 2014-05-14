package hfc.common;

import java.util.Hashtable;

/**
 * Created by IntelliJ IDEA.
 * User: zhangxiaobo
 * Date: 11-8-14
 * Time: ����10:58
 * To change this template use File | Settings | File Templates.
 */
public enum CmsAppStatusEnum {

    APP_INVALID("0","��������"),
    NEED_SEND("1", "�����ύ"),
    SEND_SUCCESS("2", "�ϴ��ɹ�"),

    /**
     * /**
     * ����δ�ύ-00
     * ������-01
     * �ѷſ�-02
     * �Ѿܾ�-03
     * ���뵥�Ų�����-A0
     */
    CMS_UNSUBMIT("00", "�ȴ�ǩԼ��"),
    CMS_EXAMINING("01", "������"),
    CMS_EMPLACED("02", "�ѷſ�"),
    CMS_REFUSED("03", "�Ѿܾ�"),
    CMS_NO_APP("A0", "�޴˵���");

    private String code = null;
    private String title = null;
    private static Hashtable<String, CmsAppStatusEnum> aliasEnums;

    CmsAppStatusEnum(String code, String title) {
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

    public static CmsAppStatusEnum valueOfAlias(String alias) {
        return aliasEnums.get(alias);
    }

    public String getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }
}
