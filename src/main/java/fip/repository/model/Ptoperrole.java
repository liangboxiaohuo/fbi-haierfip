package fip.repository.model;

public class Ptoperrole {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PTOPERROLE.ROLEID
     *
     * @mbggenerated Fri Jul 22 13:16:43 CST 2011
     */
    private String roleid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PTOPERROLE.OPERID
     *
     * @mbggenerated Fri Jul 22 13:16:43 CST 2011
     */
    private String operid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PTOPERROLE.ROLEID
     *
     * @return the value of PTOPERROLE.ROLEID
     *
     * @mbggenerated Fri Jul 22 13:16:43 CST 2011
     */
    public String getRoleid() {
        return roleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PTOPERROLE.ROLEID
     *
     * @param roleid the value for PTOPERROLE.ROLEID
     *
     * @mbggenerated Fri Jul 22 13:16:43 CST 2011
     */
    public void setRoleid(String roleid) {
        this.roleid = roleid == null ? null : roleid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PTOPERROLE.OPERID
     *
     * @return the value of PTOPERROLE.OPERID
     *
     * @mbggenerated Fri Jul 22 13:16:43 CST 2011
     */
    public String getOperid() {
        return operid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PTOPERROLE.OPERID
     *
     * @param operid the value for PTOPERROLE.OPERID
     *
     * @mbggenerated Fri Jul 22 13:16:43 CST 2011
     */
    public void setOperid(String operid) {
        this.operid = operid == null ? null : operid.trim();
    }
}