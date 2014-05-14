package fip.repository.model;

import java.util.Date;

public class Xfopinion extends XfopinionKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column XFOPINION.OPINIONTP
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    private String opiniontp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column XFOPINION.OPINION
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    private String opinion;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column XFOPINION.GRADE
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    private String grade;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column XFOPINION.OPERATOR
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    private String operator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column XFOPINION.LASTMODIFIED
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    private Date lastmodified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column XFOPINION.AUTOGRADE
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    private String autograde;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column XFOPINION.OPINIONTP
     *
     * @return the value of XFOPINION.OPINIONTP
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    public String getOpiniontp() {
        return opiniontp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column XFOPINION.OPINIONTP
     *
     * @param opiniontp the value for XFOPINION.OPINIONTP
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    public void setOpiniontp(String opiniontp) {
        this.opiniontp = opiniontp == null ? null : opiniontp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column XFOPINION.OPINION
     *
     * @return the value of XFOPINION.OPINION
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    public String getOpinion() {
        return opinion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column XFOPINION.OPINION
     *
     * @param opinion the value for XFOPINION.OPINION
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    public void setOpinion(String opinion) {
        this.opinion = opinion == null ? null : opinion.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column XFOPINION.GRADE
     *
     * @return the value of XFOPINION.GRADE
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    public String getGrade() {
        return grade;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column XFOPINION.GRADE
     *
     * @param grade the value for XFOPINION.GRADE
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column XFOPINION.OPERATOR
     *
     * @return the value of XFOPINION.OPERATOR
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    public String getOperator() {
        return operator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column XFOPINION.OPERATOR
     *
     * @param operator the value for XFOPINION.OPERATOR
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column XFOPINION.LASTMODIFIED
     *
     * @return the value of XFOPINION.LASTMODIFIED
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    public Date getLastmodified() {
        return lastmodified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column XFOPINION.LASTMODIFIED
     *
     * @param lastmodified the value for XFOPINION.LASTMODIFIED
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    public void setLastmodified(Date lastmodified) {
        this.lastmodified = lastmodified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column XFOPINION.AUTOGRADE
     *
     * @return the value of XFOPINION.AUTOGRADE
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    public String getAutograde() {
        return autograde;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column XFOPINION.AUTOGRADE
     *
     * @param autograde the value for XFOPINION.AUTOGRADE
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    public void setAutograde(String autograde) {
        this.autograde = autograde == null ? null : autograde.trim();
    }
}