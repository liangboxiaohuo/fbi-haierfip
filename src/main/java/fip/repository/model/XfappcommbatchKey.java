package fip.repository.model;

public class XfappcommbatchKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column FIP.XFAPPCOMMBATCH.APPNO
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    private String appno;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column FIP.XFAPPCOMMBATCH.COMMNO
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    private String commno;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column FIP.XFAPPCOMMBATCH.APPNO
     *
     * @return the value of FIP.XFAPPCOMMBATCH.APPNO
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    public String getAppno() {
        return appno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column FIP.XFAPPCOMMBATCH.APPNO
     *
     * @param appno the value for FIP.XFAPPCOMMBATCH.APPNO
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    public void setAppno(String appno) {
        this.appno = appno == null ? null : appno.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column FIP.XFAPPCOMMBATCH.COMMNO
     *
     * @return the value of FIP.XFAPPCOMMBATCH.COMMNO
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    public String getCommno() {
        return commno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column FIP.XFAPPCOMMBATCH.COMMNO
     *
     * @param commno the value for FIP.XFAPPCOMMBATCH.COMMNO
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    public void setCommno(String commno) {
        this.commno = commno == null ? null : commno.trim();
    }
}