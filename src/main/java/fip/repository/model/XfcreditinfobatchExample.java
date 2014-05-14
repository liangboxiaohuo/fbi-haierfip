package fip.repository.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class XfcreditinfobatchExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table FIP.XFCREDITINFOBATCH
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table FIP.XFCREDITINFOBATCH
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table FIP.XFCREDITINFOBATCH
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.XFCREDITINFOBATCH
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    public XfcreditinfobatchExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.XFCREDITINFOBATCH
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.XFCREDITINFOBATCH
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.XFCREDITINFOBATCH
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.XFCREDITINFOBATCH
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.XFCREDITINFOBATCH
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.XFCREDITINFOBATCH
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.XFCREDITINFOBATCH
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.XFCREDITINFOBATCH
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
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
     * This method corresponds to the database table FIP.XFCREDITINFOBATCH
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.XFCREDITINFOBATCH
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table FIP.XFCREDITINFOBATCH
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
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

        public Criteria andAppnoIsNull() {
            addCriterion("APPNO is null");
            return (Criteria) this;
        }

        public Criteria andAppnoIsNotNull() {
            addCriterion("APPNO is not null");
            return (Criteria) this;
        }

        public Criteria andAppnoEqualTo(String value) {
            addCriterion("APPNO =", value, "appno");
            return (Criteria) this;
        }

        public Criteria andAppnoNotEqualTo(String value) {
            addCriterion("APPNO <>", value, "appno");
            return (Criteria) this;
        }

        public Criteria andAppnoGreaterThan(String value) {
            addCriterion("APPNO >", value, "appno");
            return (Criteria) this;
        }

        public Criteria andAppnoGreaterThanOrEqualTo(String value) {
            addCriterion("APPNO >=", value, "appno");
            return (Criteria) this;
        }

        public Criteria andAppnoLessThan(String value) {
            addCriterion("APPNO <", value, "appno");
            return (Criteria) this;
        }

        public Criteria andAppnoLessThanOrEqualTo(String value) {
            addCriterion("APPNO <=", value, "appno");
            return (Criteria) this;
        }

        public Criteria andAppnoLike(String value) {
            addCriterion("APPNO like", value, "appno");
            return (Criteria) this;
        }

        public Criteria andAppnoNotLike(String value) {
            addCriterion("APPNO not like", value, "appno");
            return (Criteria) this;
        }

        public Criteria andAppnoIn(List<String> values) {
            addCriterion("APPNO in", values, "appno");
            return (Criteria) this;
        }

        public Criteria andAppnoNotIn(List<String> values) {
            addCriterion("APPNO not in", values, "appno");
            return (Criteria) this;
        }

        public Criteria andAppnoBetween(String value1, String value2) {
            addCriterion("APPNO between", value1, value2, "appno");
            return (Criteria) this;
        }

        public Criteria andAppnoNotBetween(String value1, String value2) {
            addCriterion("APPNO not between", value1, value2, "appno");
            return (Criteria) this;
        }

        public Criteria andCccd1IsNull() {
            addCriterion("CCCD1 is null");
            return (Criteria) this;
        }

        public Criteria andCccd1IsNotNull() {
            addCriterion("CCCD1 is not null");
            return (Criteria) this;
        }

        public Criteria andCccd1EqualTo(BigDecimal value) {
            addCriterion("CCCD1 =", value, "cccd1");
            return (Criteria) this;
        }

        public Criteria andCccd1NotEqualTo(BigDecimal value) {
            addCriterion("CCCD1 <>", value, "cccd1");
            return (Criteria) this;
        }

        public Criteria andCccd1GreaterThan(BigDecimal value) {
            addCriterion("CCCD1 >", value, "cccd1");
            return (Criteria) this;
        }

        public Criteria andCccd1GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("CCCD1 >=", value, "cccd1");
            return (Criteria) this;
        }

        public Criteria andCccd1LessThan(BigDecimal value) {
            addCriterion("CCCD1 <", value, "cccd1");
            return (Criteria) this;
        }

        public Criteria andCccd1LessThanOrEqualTo(BigDecimal value) {
            addCriterion("CCCD1 <=", value, "cccd1");
            return (Criteria) this;
        }

        public Criteria andCccd1In(List<BigDecimal> values) {
            addCriterion("CCCD1 in", values, "cccd1");
            return (Criteria) this;
        }

        public Criteria andCccd1NotIn(List<BigDecimal> values) {
            addCriterion("CCCD1 not in", values, "cccd1");
            return (Criteria) this;
        }

        public Criteria andCccd1Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("CCCD1 between", value1, value2, "cccd1");
            return (Criteria) this;
        }

        public Criteria andCccd1NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CCCD1 not between", value1, value2, "cccd1");
            return (Criteria) this;
        }

        public Criteria andCccd2IsNull() {
            addCriterion("CCCD2 is null");
            return (Criteria) this;
        }

        public Criteria andCccd2IsNotNull() {
            addCriterion("CCCD2 is not null");
            return (Criteria) this;
        }

        public Criteria andCccd2EqualTo(String value) {
            addCriterion("CCCD2 =", value, "cccd2");
            return (Criteria) this;
        }

        public Criteria andCccd2NotEqualTo(String value) {
            addCriterion("CCCD2 <>", value, "cccd2");
            return (Criteria) this;
        }

        public Criteria andCccd2GreaterThan(String value) {
            addCriterion("CCCD2 >", value, "cccd2");
            return (Criteria) this;
        }

        public Criteria andCccd2GreaterThanOrEqualTo(String value) {
            addCriterion("CCCD2 >=", value, "cccd2");
            return (Criteria) this;
        }

        public Criteria andCccd2LessThan(String value) {
            addCriterion("CCCD2 <", value, "cccd2");
            return (Criteria) this;
        }

        public Criteria andCccd2LessThanOrEqualTo(String value) {
            addCriterion("CCCD2 <=", value, "cccd2");
            return (Criteria) this;
        }

        public Criteria andCccd2Like(String value) {
            addCriterion("CCCD2 like", value, "cccd2");
            return (Criteria) this;
        }

        public Criteria andCccd2NotLike(String value) {
            addCriterion("CCCD2 not like", value, "cccd2");
            return (Criteria) this;
        }

        public Criteria andCccd2In(List<String> values) {
            addCriterion("CCCD2 in", values, "cccd2");
            return (Criteria) this;
        }

        public Criteria andCccd2NotIn(List<String> values) {
            addCriterion("CCCD2 not in", values, "cccd2");
            return (Criteria) this;
        }

        public Criteria andCccd2Between(String value1, String value2) {
            addCriterion("CCCD2 between", value1, value2, "cccd2");
            return (Criteria) this;
        }

        public Criteria andCccd2NotBetween(String value1, String value2) {
            addCriterion("CCCD2 not between", value1, value2, "cccd2");
            return (Criteria) this;
        }

        public Criteria andCcd60timeIsNull() {
            addCriterion("CCD60TIME is null");
            return (Criteria) this;
        }

        public Criteria andCcd60timeIsNotNull() {
            addCriterion("CCD60TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCcd60timeEqualTo(Long value) {
            addCriterion("CCD60TIME =", value, "ccd60time");
            return (Criteria) this;
        }

        public Criteria andCcd60timeNotEqualTo(Long value) {
            addCriterion("CCD60TIME <>", value, "ccd60time");
            return (Criteria) this;
        }

        public Criteria andCcd60timeGreaterThan(Long value) {
            addCriterion("CCD60TIME >", value, "ccd60time");
            return (Criteria) this;
        }

        public Criteria andCcd60timeGreaterThanOrEqualTo(Long value) {
            addCriterion("CCD60TIME >=", value, "ccd60time");
            return (Criteria) this;
        }

        public Criteria andCcd60timeLessThan(Long value) {
            addCriterion("CCD60TIME <", value, "ccd60time");
            return (Criteria) this;
        }

        public Criteria andCcd60timeLessThanOrEqualTo(Long value) {
            addCriterion("CCD60TIME <=", value, "ccd60time");
            return (Criteria) this;
        }

        public Criteria andCcd60timeIn(List<Long> values) {
            addCriterion("CCD60TIME in", values, "ccd60time");
            return (Criteria) this;
        }

        public Criteria andCcd60timeNotIn(List<Long> values) {
            addCriterion("CCD60TIME not in", values, "ccd60time");
            return (Criteria) this;
        }

        public Criteria andCcd60timeBetween(Long value1, Long value2) {
            addCriterion("CCD60TIME between", value1, value2, "ccd60time");
            return (Criteria) this;
        }

        public Criteria andCcd60timeNotBetween(Long value1, Long value2) {
            addCriterion("CCD60TIME not between", value1, value2, "ccd60time");
            return (Criteria) this;
        }

        public Criteria andCcd90timeIsNull() {
            addCriterion("CCD90TIME is null");
            return (Criteria) this;
        }

        public Criteria andCcd90timeIsNotNull() {
            addCriterion("CCD90TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCcd90timeEqualTo(Long value) {
            addCriterion("CCD90TIME =", value, "ccd90time");
            return (Criteria) this;
        }

        public Criteria andCcd90timeNotEqualTo(Long value) {
            addCriterion("CCD90TIME <>", value, "ccd90time");
            return (Criteria) this;
        }

        public Criteria andCcd90timeGreaterThan(Long value) {
            addCriterion("CCD90TIME >", value, "ccd90time");
            return (Criteria) this;
        }

        public Criteria andCcd90timeGreaterThanOrEqualTo(Long value) {
            addCriterion("CCD90TIME >=", value, "ccd90time");
            return (Criteria) this;
        }

        public Criteria andCcd90timeLessThan(Long value) {
            addCriterion("CCD90TIME <", value, "ccd90time");
            return (Criteria) this;
        }

        public Criteria andCcd90timeLessThanOrEqualTo(Long value) {
            addCriterion("CCD90TIME <=", value, "ccd90time");
            return (Criteria) this;
        }

        public Criteria andCcd90timeIn(List<Long> values) {
            addCriterion("CCD90TIME in", values, "ccd90time");
            return (Criteria) this;
        }

        public Criteria andCcd90timeNotIn(List<Long> values) {
            addCriterion("CCD90TIME not in", values, "ccd90time");
            return (Criteria) this;
        }

        public Criteria andCcd90timeBetween(Long value1, Long value2) {
            addCriterion("CCD90TIME between", value1, value2, "ccd90time");
            return (Criteria) this;
        }

        public Criteria andCcd90timeNotBetween(Long value1, Long value2) {
            addCriterion("CCD90TIME not between", value1, value2, "ccd90time");
            return (Criteria) this;
        }

        public Criteria andCcd30timeIsNull() {
            addCriterion("CCD30TIME is null");
            return (Criteria) this;
        }

        public Criteria andCcd30timeIsNotNull() {
            addCriterion("CCD30TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCcd30timeEqualTo(Long value) {
            addCriterion("CCD30TIME =", value, "ccd30time");
            return (Criteria) this;
        }

        public Criteria andCcd30timeNotEqualTo(Long value) {
            addCriterion("CCD30TIME <>", value, "ccd30time");
            return (Criteria) this;
        }

        public Criteria andCcd30timeGreaterThan(Long value) {
            addCriterion("CCD30TIME >", value, "ccd30time");
            return (Criteria) this;
        }

        public Criteria andCcd30timeGreaterThanOrEqualTo(Long value) {
            addCriterion("CCD30TIME >=", value, "ccd30time");
            return (Criteria) this;
        }

        public Criteria andCcd30timeLessThan(Long value) {
            addCriterion("CCD30TIME <", value, "ccd30time");
            return (Criteria) this;
        }

        public Criteria andCcd30timeLessThanOrEqualTo(Long value) {
            addCriterion("CCD30TIME <=", value, "ccd30time");
            return (Criteria) this;
        }

        public Criteria andCcd30timeIn(List<Long> values) {
            addCriterion("CCD30TIME in", values, "ccd30time");
            return (Criteria) this;
        }

        public Criteria andCcd30timeNotIn(List<Long> values) {
            addCriterion("CCD30TIME not in", values, "ccd30time");
            return (Criteria) this;
        }

        public Criteria andCcd30timeBetween(Long value1, Long value2) {
            addCriterion("CCD30TIME between", value1, value2, "ccd30time");
            return (Criteria) this;
        }

        public Criteria andCcd30timeNotBetween(Long value1, Long value2) {
            addCriterion("CCD30TIME not between", value1, value2, "ccd30time");
            return (Criteria) this;
        }

        public Criteria andCcd30time2IsNull() {
            addCriterion("CCD30TIME2 is null");
            return (Criteria) this;
        }

        public Criteria andCcd30time2IsNotNull() {
            addCriterion("CCD30TIME2 is not null");
            return (Criteria) this;
        }

        public Criteria andCcd30time2EqualTo(Long value) {
            addCriterion("CCD30TIME2 =", value, "ccd30time2");
            return (Criteria) this;
        }

        public Criteria andCcd30time2NotEqualTo(Long value) {
            addCriterion("CCD30TIME2 <>", value, "ccd30time2");
            return (Criteria) this;
        }

        public Criteria andCcd30time2GreaterThan(Long value) {
            addCriterion("CCD30TIME2 >", value, "ccd30time2");
            return (Criteria) this;
        }

        public Criteria andCcd30time2GreaterThanOrEqualTo(Long value) {
            addCriterion("CCD30TIME2 >=", value, "ccd30time2");
            return (Criteria) this;
        }

        public Criteria andCcd30time2LessThan(Long value) {
            addCriterion("CCD30TIME2 <", value, "ccd30time2");
            return (Criteria) this;
        }

        public Criteria andCcd30time2LessThanOrEqualTo(Long value) {
            addCriterion("CCD30TIME2 <=", value, "ccd30time2");
            return (Criteria) this;
        }

        public Criteria andCcd30time2In(List<Long> values) {
            addCriterion("CCD30TIME2 in", values, "ccd30time2");
            return (Criteria) this;
        }

        public Criteria andCcd30time2NotIn(List<Long> values) {
            addCriterion("CCD30TIME2 not in", values, "ccd30time2");
            return (Criteria) this;
        }

        public Criteria andCcd30time2Between(Long value1, Long value2) {
            addCriterion("CCD30TIME2 between", value1, value2, "ccd30time2");
            return (Criteria) this;
        }

        public Criteria andCcd30time2NotBetween(Long value1, Long value2) {
            addCriterion("CCD30TIME2 not between", value1, value2, "ccd30time2");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table FIP.XFCREDITINFOBATCH
     *
     * @mbggenerated do_not_delete_during_merge Sat Aug 13 21:38:48 CST 2011
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table FIP.XFCREDITINFOBATCH
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
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