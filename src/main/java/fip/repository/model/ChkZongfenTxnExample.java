package fip.repository.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ChkZongfenTxnExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table FIP.CHK_ZONGFEN_TXN
     *
     * @mbggenerated Fri Jul 27 14:51:12 CST 2012
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table FIP.CHK_ZONGFEN_TXN
     *
     * @mbggenerated Fri Jul 27 14:51:12 CST 2012
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table FIP.CHK_ZONGFEN_TXN
     *
     * @mbggenerated Fri Jul 27 14:51:12 CST 2012
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.CHK_ZONGFEN_TXN
     *
     * @mbggenerated Fri Jul 27 14:51:12 CST 2012
     */
    public ChkZongfenTxnExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.CHK_ZONGFEN_TXN
     *
     * @mbggenerated Fri Jul 27 14:51:12 CST 2012
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.CHK_ZONGFEN_TXN
     *
     * @mbggenerated Fri Jul 27 14:51:12 CST 2012
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.CHK_ZONGFEN_TXN
     *
     * @mbggenerated Fri Jul 27 14:51:12 CST 2012
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.CHK_ZONGFEN_TXN
     *
     * @mbggenerated Fri Jul 27 14:51:12 CST 2012
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.CHK_ZONGFEN_TXN
     *
     * @mbggenerated Fri Jul 27 14:51:12 CST 2012
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.CHK_ZONGFEN_TXN
     *
     * @mbggenerated Fri Jul 27 14:51:12 CST 2012
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.CHK_ZONGFEN_TXN
     *
     * @mbggenerated Fri Jul 27 14:51:12 CST 2012
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.CHK_ZONGFEN_TXN
     *
     * @mbggenerated Fri Jul 27 14:51:12 CST 2012
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.CHK_ZONGFEN_TXN
     *
     * @mbggenerated Fri Jul 27 14:51:12 CST 2012
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.CHK_ZONGFEN_TXN
     *
     * @mbggenerated Fri Jul 27 14:51:12 CST 2012
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table FIP.CHK_ZONGFEN_TXN
     *
     * @mbggenerated Fri Jul 27 14:51:12 CST 2012
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andPkidIsNull() {
            addCriterion("PKID is null");
            return (Criteria) this;
        }

        public Criteria andPkidIsNotNull() {
            addCriterion("PKID is not null");
            return (Criteria) this;
        }

        public Criteria andPkidEqualTo(String value) {
            addCriterion("PKID =", value, "pkid");
            return (Criteria) this;
        }

        public Criteria andPkidNotEqualTo(String value) {
            addCriterion("PKID <>", value, "pkid");
            return (Criteria) this;
        }

        public Criteria andPkidGreaterThan(String value) {
            addCriterion("PKID >", value, "pkid");
            return (Criteria) this;
        }

        public Criteria andPkidGreaterThanOrEqualTo(String value) {
            addCriterion("PKID >=", value, "pkid");
            return (Criteria) this;
        }

        public Criteria andPkidLessThan(String value) {
            addCriterion("PKID <", value, "pkid");
            return (Criteria) this;
        }

        public Criteria andPkidLessThanOrEqualTo(String value) {
            addCriterion("PKID <=", value, "pkid");
            return (Criteria) this;
        }

        public Criteria andPkidLike(String value) {
            addCriterion("PKID like", value, "pkid");
            return (Criteria) this;
        }

        public Criteria andPkidNotLike(String value) {
            addCriterion("PKID not like", value, "pkid");
            return (Criteria) this;
        }

        public Criteria andPkidIn(List<String> values) {
            addCriterion("PKID in", values, "pkid");
            return (Criteria) this;
        }

        public Criteria andPkidNotIn(List<String> values) {
            addCriterion("PKID not in", values, "pkid");
            return (Criteria) this;
        }

        public Criteria andPkidBetween(String value1, String value2) {
            addCriterion("PKID between", value1, value2, "pkid");
            return (Criteria) this;
        }

        public Criteria andPkidNotBetween(String value1, String value2) {
            addCriterion("PKID not between", value1, value2, "pkid");
            return (Criteria) this;
        }

        public Criteria andTxnDateIsNull() {
            addCriterion("TXN_DATE is null");
            return (Criteria) this;
        }

        public Criteria andTxnDateIsNotNull() {
            addCriterion("TXN_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andTxnDateEqualTo(String value) {
            addCriterion("TXN_DATE =", value, "txnDate");
            return (Criteria) this;
        }

        public Criteria andTxnDateNotEqualTo(String value) {
            addCriterion("TXN_DATE <>", value, "txnDate");
            return (Criteria) this;
        }

        public Criteria andTxnDateGreaterThan(String value) {
            addCriterion("TXN_DATE >", value, "txnDate");
            return (Criteria) this;
        }

        public Criteria andTxnDateGreaterThanOrEqualTo(String value) {
            addCriterion("TXN_DATE >=", value, "txnDate");
            return (Criteria) this;
        }

        public Criteria andTxnDateLessThan(String value) {
            addCriterion("TXN_DATE <", value, "txnDate");
            return (Criteria) this;
        }

        public Criteria andTxnDateLessThanOrEqualTo(String value) {
            addCriterion("TXN_DATE <=", value, "txnDate");
            return (Criteria) this;
        }

        public Criteria andTxnDateLike(String value) {
            addCriterion("TXN_DATE like", value, "txnDate");
            return (Criteria) this;
        }

        public Criteria andTxnDateNotLike(String value) {
            addCriterion("TXN_DATE not like", value, "txnDate");
            return (Criteria) this;
        }

        public Criteria andTxnDateIn(List<String> values) {
            addCriterion("TXN_DATE in", values, "txnDate");
            return (Criteria) this;
        }

        public Criteria andTxnDateNotIn(List<String> values) {
            addCriterion("TXN_DATE not in", values, "txnDate");
            return (Criteria) this;
        }

        public Criteria andTxnDateBetween(String value1, String value2) {
            addCriterion("TXN_DATE between", value1, value2, "txnDate");
            return (Criteria) this;
        }

        public Criteria andTxnDateNotBetween(String value1, String value2) {
            addCriterion("TXN_DATE not between", value1, value2, "txnDate");
            return (Criteria) this;
        }

        public Criteria andSendSysIdIsNull() {
            addCriterion("SEND_SYS_ID is null");
            return (Criteria) this;
        }

        public Criteria andSendSysIdIsNotNull() {
            addCriterion("SEND_SYS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSendSysIdEqualTo(String value) {
            addCriterion("SEND_SYS_ID =", value, "sendSysId");
            return (Criteria) this;
        }

        public Criteria andSendSysIdNotEqualTo(String value) {
            addCriterion("SEND_SYS_ID <>", value, "sendSysId");
            return (Criteria) this;
        }

        public Criteria andSendSysIdGreaterThan(String value) {
            addCriterion("SEND_SYS_ID >", value, "sendSysId");
            return (Criteria) this;
        }

        public Criteria andSendSysIdGreaterThanOrEqualTo(String value) {
            addCriterion("SEND_SYS_ID >=", value, "sendSysId");
            return (Criteria) this;
        }

        public Criteria andSendSysIdLessThan(String value) {
            addCriterion("SEND_SYS_ID <", value, "sendSysId");
            return (Criteria) this;
        }

        public Criteria andSendSysIdLessThanOrEqualTo(String value) {
            addCriterion("SEND_SYS_ID <=", value, "sendSysId");
            return (Criteria) this;
        }

        public Criteria andSendSysIdLike(String value) {
            addCriterion("SEND_SYS_ID like", value, "sendSysId");
            return (Criteria) this;
        }

        public Criteria andSendSysIdNotLike(String value) {
            addCriterion("SEND_SYS_ID not like", value, "sendSysId");
            return (Criteria) this;
        }

        public Criteria andSendSysIdIn(List<String> values) {
            addCriterion("SEND_SYS_ID in", values, "sendSysId");
            return (Criteria) this;
        }

        public Criteria andSendSysIdNotIn(List<String> values) {
            addCriterion("SEND_SYS_ID not in", values, "sendSysId");
            return (Criteria) this;
        }

        public Criteria andSendSysIdBetween(String value1, String value2) {
            addCriterion("SEND_SYS_ID between", value1, value2, "sendSysId");
            return (Criteria) this;
        }

        public Criteria andSendSysIdNotBetween(String value1, String value2) {
            addCriterion("SEND_SYS_ID not between", value1, value2, "sendSysId");
            return (Criteria) this;
        }

        public Criteria andActnoIsNull() {
            addCriterion("ACTNO is null");
            return (Criteria) this;
        }

        public Criteria andActnoIsNotNull() {
            addCriterion("ACTNO is not null");
            return (Criteria) this;
        }

        public Criteria andActnoEqualTo(String value) {
            addCriterion("ACTNO =", value, "actno");
            return (Criteria) this;
        }

        public Criteria andActnoNotEqualTo(String value) {
            addCriterion("ACTNO <>", value, "actno");
            return (Criteria) this;
        }

        public Criteria andActnoGreaterThan(String value) {
            addCriterion("ACTNO >", value, "actno");
            return (Criteria) this;
        }

        public Criteria andActnoGreaterThanOrEqualTo(String value) {
            addCriterion("ACTNO >=", value, "actno");
            return (Criteria) this;
        }

        public Criteria andActnoLessThan(String value) {
            addCriterion("ACTNO <", value, "actno");
            return (Criteria) this;
        }

        public Criteria andActnoLessThanOrEqualTo(String value) {
            addCriterion("ACTNO <=", value, "actno");
            return (Criteria) this;
        }

        public Criteria andActnoLike(String value) {
            addCriterion("ACTNO like", value, "actno");
            return (Criteria) this;
        }

        public Criteria andActnoNotLike(String value) {
            addCriterion("ACTNO not like", value, "actno");
            return (Criteria) this;
        }

        public Criteria andActnoIn(List<String> values) {
            addCriterion("ACTNO in", values, "actno");
            return (Criteria) this;
        }

        public Criteria andActnoNotIn(List<String> values) {
            addCriterion("ACTNO not in", values, "actno");
            return (Criteria) this;
        }

        public Criteria andActnoBetween(String value1, String value2) {
            addCriterion("ACTNO between", value1, value2, "actno");
            return (Criteria) this;
        }

        public Criteria andActnoNotBetween(String value1, String value2) {
            addCriterion("ACTNO not between", value1, value2, "actno");
            return (Criteria) this;
        }

        public Criteria andTxnamtIsNull() {
            addCriterion("TXNAMT is null");
            return (Criteria) this;
        }

        public Criteria andTxnamtIsNotNull() {
            addCriterion("TXNAMT is not null");
            return (Criteria) this;
        }

        public Criteria andTxnamtEqualTo(BigDecimal value) {
            addCriterion("TXNAMT =", value, "txnamt");
            return (Criteria) this;
        }

        public Criteria andTxnamtNotEqualTo(BigDecimal value) {
            addCriterion("TXNAMT <>", value, "txnamt");
            return (Criteria) this;
        }

        public Criteria andTxnamtGreaterThan(BigDecimal value) {
            addCriterion("TXNAMT >", value, "txnamt");
            return (Criteria) this;
        }

        public Criteria andTxnamtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TXNAMT >=", value, "txnamt");
            return (Criteria) this;
        }

        public Criteria andTxnamtLessThan(BigDecimal value) {
            addCriterion("TXNAMT <", value, "txnamt");
            return (Criteria) this;
        }

        public Criteria andTxnamtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TXNAMT <=", value, "txnamt");
            return (Criteria) this;
        }

        public Criteria andTxnamtIn(List<BigDecimal> values) {
            addCriterion("TXNAMT in", values, "txnamt");
            return (Criteria) this;
        }

        public Criteria andTxnamtNotIn(List<BigDecimal> values) {
            addCriterion("TXNAMT not in", values, "txnamt");
            return (Criteria) this;
        }

        public Criteria andTxnamtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TXNAMT between", value1, value2, "txnamt");
            return (Criteria) this;
        }

        public Criteria andTxnamtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TXNAMT not between", value1, value2, "txnamt");
            return (Criteria) this;
        }

        public Criteria andDcFlagIsNull() {
            addCriterion("DC_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andDcFlagIsNotNull() {
            addCriterion("DC_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andDcFlagEqualTo(String value) {
            addCriterion("DC_FLAG =", value, "dcFlag");
            return (Criteria) this;
        }

        public Criteria andDcFlagNotEqualTo(String value) {
            addCriterion("DC_FLAG <>", value, "dcFlag");
            return (Criteria) this;
        }

        public Criteria andDcFlagGreaterThan(String value) {
            addCriterion("DC_FLAG >", value, "dcFlag");
            return (Criteria) this;
        }

        public Criteria andDcFlagGreaterThanOrEqualTo(String value) {
            addCriterion("DC_FLAG >=", value, "dcFlag");
            return (Criteria) this;
        }

        public Criteria andDcFlagLessThan(String value) {
            addCriterion("DC_FLAG <", value, "dcFlag");
            return (Criteria) this;
        }

        public Criteria andDcFlagLessThanOrEqualTo(String value) {
            addCriterion("DC_FLAG <=", value, "dcFlag");
            return (Criteria) this;
        }

        public Criteria andDcFlagLike(String value) {
            addCriterion("DC_FLAG like", value, "dcFlag");
            return (Criteria) this;
        }

        public Criteria andDcFlagNotLike(String value) {
            addCriterion("DC_FLAG not like", value, "dcFlag");
            return (Criteria) this;
        }

        public Criteria andDcFlagIn(List<String> values) {
            addCriterion("DC_FLAG in", values, "dcFlag");
            return (Criteria) this;
        }

        public Criteria andDcFlagNotIn(List<String> values) {
            addCriterion("DC_FLAG not in", values, "dcFlag");
            return (Criteria) this;
        }

        public Criteria andDcFlagBetween(String value1, String value2) {
            addCriterion("DC_FLAG between", value1, value2, "dcFlag");
            return (Criteria) this;
        }

        public Criteria andDcFlagNotBetween(String value1, String value2) {
            addCriterion("DC_FLAG not between", value1, value2, "dcFlag");
            return (Criteria) this;
        }

        public Criteria andMsgSnIsNull() {
            addCriterion("MSG_SN is null");
            return (Criteria) this;
        }

        public Criteria andMsgSnIsNotNull() {
            addCriterion("MSG_SN is not null");
            return (Criteria) this;
        }

        public Criteria andMsgSnEqualTo(String value) {
            addCriterion("MSG_SN =", value, "msgSn");
            return (Criteria) this;
        }

        public Criteria andMsgSnNotEqualTo(String value) {
            addCriterion("MSG_SN <>", value, "msgSn");
            return (Criteria) this;
        }

        public Criteria andMsgSnGreaterThan(String value) {
            addCriterion("MSG_SN >", value, "msgSn");
            return (Criteria) this;
        }

        public Criteria andMsgSnGreaterThanOrEqualTo(String value) {
            addCriterion("MSG_SN >=", value, "msgSn");
            return (Criteria) this;
        }

        public Criteria andMsgSnLessThan(String value) {
            addCriterion("MSG_SN <", value, "msgSn");
            return (Criteria) this;
        }

        public Criteria andMsgSnLessThanOrEqualTo(String value) {
            addCriterion("MSG_SN <=", value, "msgSn");
            return (Criteria) this;
        }

        public Criteria andMsgSnLike(String value) {
            addCriterion("MSG_SN like", value, "msgSn");
            return (Criteria) this;
        }

        public Criteria andMsgSnNotLike(String value) {
            addCriterion("MSG_SN not like", value, "msgSn");
            return (Criteria) this;
        }

        public Criteria andMsgSnIn(List<String> values) {
            addCriterion("MSG_SN in", values, "msgSn");
            return (Criteria) this;
        }

        public Criteria andMsgSnNotIn(List<String> values) {
            addCriterion("MSG_SN not in", values, "msgSn");
            return (Criteria) this;
        }

        public Criteria andMsgSnBetween(String value1, String value2) {
            addCriterion("MSG_SN between", value1, value2, "msgSn");
            return (Criteria) this;
        }

        public Criteria andMsgSnNotBetween(String value1, String value2) {
            addCriterion("MSG_SN not between", value1, value2, "msgSn");
            return (Criteria) this;
        }

        public Criteria andChkstsIsNull() {
            addCriterion("CHKSTS is null");
            return (Criteria) this;
        }

        public Criteria andChkstsIsNotNull() {
            addCriterion("CHKSTS is not null");
            return (Criteria) this;
        }

        public Criteria andChkstsEqualTo(String value) {
            addCriterion("CHKSTS =", value, "chksts");
            return (Criteria) this;
        }

        public Criteria andChkstsNotEqualTo(String value) {
            addCriterion("CHKSTS <>", value, "chksts");
            return (Criteria) this;
        }

        public Criteria andChkstsGreaterThan(String value) {
            addCriterion("CHKSTS >", value, "chksts");
            return (Criteria) this;
        }

        public Criteria andChkstsGreaterThanOrEqualTo(String value) {
            addCriterion("CHKSTS >=", value, "chksts");
            return (Criteria) this;
        }

        public Criteria andChkstsLessThan(String value) {
            addCriterion("CHKSTS <", value, "chksts");
            return (Criteria) this;
        }

        public Criteria andChkstsLessThanOrEqualTo(String value) {
            addCriterion("CHKSTS <=", value, "chksts");
            return (Criteria) this;
        }

        public Criteria andChkstsLike(String value) {
            addCriterion("CHKSTS like", value, "chksts");
            return (Criteria) this;
        }

        public Criteria andChkstsNotLike(String value) {
            addCriterion("CHKSTS not like", value, "chksts");
            return (Criteria) this;
        }

        public Criteria andChkstsIn(List<String> values) {
            addCriterion("CHKSTS in", values, "chksts");
            return (Criteria) this;
        }

        public Criteria andChkstsNotIn(List<String> values) {
            addCriterion("CHKSTS not in", values, "chksts");
            return (Criteria) this;
        }

        public Criteria andChkstsBetween(String value1, String value2) {
            addCriterion("CHKSTS between", value1, value2, "chksts");
            return (Criteria) this;
        }

        public Criteria andChkstsNotBetween(String value1, String value2) {
            addCriterion("CHKSTS not between", value1, value2, "chksts");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table FIP.CHK_ZONGFEN_TXN
     *
     * @mbggenerated do_not_delete_during_merge Fri Jul 27 14:51:12 CST 2012
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table FIP.CHK_ZONGFEN_TXN
     *
     * @mbggenerated Fri Jul 27 14:51:12 CST 2012
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}