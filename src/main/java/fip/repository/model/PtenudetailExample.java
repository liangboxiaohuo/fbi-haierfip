package fip.repository.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PtenudetailExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table PTENUDETAIL
     *
     * @mbggenerated Fri Jul 22 13:16:43 CST 2011
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table PTENUDETAIL
     *
     * @mbggenerated Fri Jul 22 13:16:43 CST 2011
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table PTENUDETAIL
     *
     * @mbggenerated Fri Jul 22 13:16:43 CST 2011
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTENUDETAIL
     *
     * @mbggenerated Fri Jul 22 13:16:43 CST 2011
     */
    public PtenudetailExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTENUDETAIL
     *
     * @mbggenerated Fri Jul 22 13:16:43 CST 2011
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTENUDETAIL
     *
     * @mbggenerated Fri Jul 22 13:16:43 CST 2011
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTENUDETAIL
     *
     * @mbggenerated Fri Jul 22 13:16:43 CST 2011
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTENUDETAIL
     *
     * @mbggenerated Fri Jul 22 13:16:43 CST 2011
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTENUDETAIL
     *
     * @mbggenerated Fri Jul 22 13:16:43 CST 2011
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTENUDETAIL
     *
     * @mbggenerated Fri Jul 22 13:16:43 CST 2011
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTENUDETAIL
     *
     * @mbggenerated Fri Jul 22 13:16:43 CST 2011
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTENUDETAIL
     *
     * @mbggenerated Fri Jul 22 13:16:43 CST 2011
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
     * This method corresponds to the database table PTENUDETAIL
     *
     * @mbggenerated Fri Jul 22 13:16:43 CST 2011
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTENUDETAIL
     *
     * @mbggenerated Fri Jul 22 13:16:43 CST 2011
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table PTENUDETAIL
     *
     * @mbggenerated Fri Jul 22 13:16:43 CST 2011
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

        public Criteria andEnuitemvalueIsNull() {
            addCriterion("ENUITEMVALUE is null");
            return (Criteria) this;
        }

        public Criteria andEnuitemvalueIsNotNull() {
            addCriterion("ENUITEMVALUE is not null");
            return (Criteria) this;
        }

        public Criteria andEnuitemvalueEqualTo(String value) {
            addCriterion("ENUITEMVALUE =", value, "enuitemvalue");
            return (Criteria) this;
        }

        public Criteria andEnuitemvalueNotEqualTo(String value) {
            addCriterion("ENUITEMVALUE <>", value, "enuitemvalue");
            return (Criteria) this;
        }

        public Criteria andEnuitemvalueGreaterThan(String value) {
            addCriterion("ENUITEMVALUE >", value, "enuitemvalue");
            return (Criteria) this;
        }

        public Criteria andEnuitemvalueGreaterThanOrEqualTo(String value) {
            addCriterion("ENUITEMVALUE >=", value, "enuitemvalue");
            return (Criteria) this;
        }

        public Criteria andEnuitemvalueLessThan(String value) {
            addCriterion("ENUITEMVALUE <", value, "enuitemvalue");
            return (Criteria) this;
        }

        public Criteria andEnuitemvalueLessThanOrEqualTo(String value) {
            addCriterion("ENUITEMVALUE <=", value, "enuitemvalue");
            return (Criteria) this;
        }

        public Criteria andEnuitemvalueLike(String value) {
            addCriterion("ENUITEMVALUE like", value, "enuitemvalue");
            return (Criteria) this;
        }

        public Criteria andEnuitemvalueNotLike(String value) {
            addCriterion("ENUITEMVALUE not like", value, "enuitemvalue");
            return (Criteria) this;
        }

        public Criteria andEnuitemvalueIn(List<String> values) {
            addCriterion("ENUITEMVALUE in", values, "enuitemvalue");
            return (Criteria) this;
        }

        public Criteria andEnuitemvalueNotIn(List<String> values) {
            addCriterion("ENUITEMVALUE not in", values, "enuitemvalue");
            return (Criteria) this;
        }

        public Criteria andEnuitemvalueBetween(String value1, String value2) {
            addCriterion("ENUITEMVALUE between", value1, value2, "enuitemvalue");
            return (Criteria) this;
        }

        public Criteria andEnuitemvalueNotBetween(String value1, String value2) {
            addCriterion("ENUITEMVALUE not between", value1, value2, "enuitemvalue");
            return (Criteria) this;
        }

        public Criteria andEnutypeIsNull() {
            addCriterion("ENUTYPE is null");
            return (Criteria) this;
        }

        public Criteria andEnutypeIsNotNull() {
            addCriterion("ENUTYPE is not null");
            return (Criteria) this;
        }

        public Criteria andEnutypeEqualTo(String value) {
            addCriterion("ENUTYPE =", value, "enutype");
            return (Criteria) this;
        }

        public Criteria andEnutypeNotEqualTo(String value) {
            addCriterion("ENUTYPE <>", value, "enutype");
            return (Criteria) this;
        }

        public Criteria andEnutypeGreaterThan(String value) {
            addCriterion("ENUTYPE >", value, "enutype");
            return (Criteria) this;
        }

        public Criteria andEnutypeGreaterThanOrEqualTo(String value) {
            addCriterion("ENUTYPE >=", value, "enutype");
            return (Criteria) this;
        }

        public Criteria andEnutypeLessThan(String value) {
            addCriterion("ENUTYPE <", value, "enutype");
            return (Criteria) this;
        }

        public Criteria andEnutypeLessThanOrEqualTo(String value) {
            addCriterion("ENUTYPE <=", value, "enutype");
            return (Criteria) this;
        }

        public Criteria andEnutypeLike(String value) {
            addCriterion("ENUTYPE like", value, "enutype");
            return (Criteria) this;
        }

        public Criteria andEnutypeNotLike(String value) {
            addCriterion("ENUTYPE not like", value, "enutype");
            return (Criteria) this;
        }

        public Criteria andEnutypeIn(List<String> values) {
            addCriterion("ENUTYPE in", values, "enutype");
            return (Criteria) this;
        }

        public Criteria andEnutypeNotIn(List<String> values) {
            addCriterion("ENUTYPE not in", values, "enutype");
            return (Criteria) this;
        }

        public Criteria andEnutypeBetween(String value1, String value2) {
            addCriterion("ENUTYPE between", value1, value2, "enutype");
            return (Criteria) this;
        }

        public Criteria andEnutypeNotBetween(String value1, String value2) {
            addCriterion("ENUTYPE not between", value1, value2, "enutype");
            return (Criteria) this;
        }

        public Criteria andEnuitemlabelIsNull() {
            addCriterion("ENUITEMLABEL is null");
            return (Criteria) this;
        }

        public Criteria andEnuitemlabelIsNotNull() {
            addCriterion("ENUITEMLABEL is not null");
            return (Criteria) this;
        }

        public Criteria andEnuitemlabelEqualTo(String value) {
            addCriterion("ENUITEMLABEL =", value, "enuitemlabel");
            return (Criteria) this;
        }

        public Criteria andEnuitemlabelNotEqualTo(String value) {
            addCriterion("ENUITEMLABEL <>", value, "enuitemlabel");
            return (Criteria) this;
        }

        public Criteria andEnuitemlabelGreaterThan(String value) {
            addCriterion("ENUITEMLABEL >", value, "enuitemlabel");
            return (Criteria) this;
        }

        public Criteria andEnuitemlabelGreaterThanOrEqualTo(String value) {
            addCriterion("ENUITEMLABEL >=", value, "enuitemlabel");
            return (Criteria) this;
        }

        public Criteria andEnuitemlabelLessThan(String value) {
            addCriterion("ENUITEMLABEL <", value, "enuitemlabel");
            return (Criteria) this;
        }

        public Criteria andEnuitemlabelLessThanOrEqualTo(String value) {
            addCriterion("ENUITEMLABEL <=", value, "enuitemlabel");
            return (Criteria) this;
        }

        public Criteria andEnuitemlabelLike(String value) {
            addCriterion("ENUITEMLABEL like", value, "enuitemlabel");
            return (Criteria) this;
        }

        public Criteria andEnuitemlabelNotLike(String value) {
            addCriterion("ENUITEMLABEL not like", value, "enuitemlabel");
            return (Criteria) this;
        }

        public Criteria andEnuitemlabelIn(List<String> values) {
            addCriterion("ENUITEMLABEL in", values, "enuitemlabel");
            return (Criteria) this;
        }

        public Criteria andEnuitemlabelNotIn(List<String> values) {
            addCriterion("ENUITEMLABEL not in", values, "enuitemlabel");
            return (Criteria) this;
        }

        public Criteria andEnuitemlabelBetween(String value1, String value2) {
            addCriterion("ENUITEMLABEL between", value1, value2, "enuitemlabel");
            return (Criteria) this;
        }

        public Criteria andEnuitemlabelNotBetween(String value1, String value2) {
            addCriterion("ENUITEMLABEL not between", value1, value2, "enuitemlabel");
            return (Criteria) this;
        }

        public Criteria andEnuitemdescIsNull() {
            addCriterion("ENUITEMDESC is null");
            return (Criteria) this;
        }

        public Criteria andEnuitemdescIsNotNull() {
            addCriterion("ENUITEMDESC is not null");
            return (Criteria) this;
        }

        public Criteria andEnuitemdescEqualTo(String value) {
            addCriterion("ENUITEMDESC =", value, "enuitemdesc");
            return (Criteria) this;
        }

        public Criteria andEnuitemdescNotEqualTo(String value) {
            addCriterion("ENUITEMDESC <>", value, "enuitemdesc");
            return (Criteria) this;
        }

        public Criteria andEnuitemdescGreaterThan(String value) {
            addCriterion("ENUITEMDESC >", value, "enuitemdesc");
            return (Criteria) this;
        }

        public Criteria andEnuitemdescGreaterThanOrEqualTo(String value) {
            addCriterion("ENUITEMDESC >=", value, "enuitemdesc");
            return (Criteria) this;
        }

        public Criteria andEnuitemdescLessThan(String value) {
            addCriterion("ENUITEMDESC <", value, "enuitemdesc");
            return (Criteria) this;
        }

        public Criteria andEnuitemdescLessThanOrEqualTo(String value) {
            addCriterion("ENUITEMDESC <=", value, "enuitemdesc");
            return (Criteria) this;
        }

        public Criteria andEnuitemdescLike(String value) {
            addCriterion("ENUITEMDESC like", value, "enuitemdesc");
            return (Criteria) this;
        }

        public Criteria andEnuitemdescNotLike(String value) {
            addCriterion("ENUITEMDESC not like", value, "enuitemdesc");
            return (Criteria) this;
        }

        public Criteria andEnuitemdescIn(List<String> values) {
            addCriterion("ENUITEMDESC in", values, "enuitemdesc");
            return (Criteria) this;
        }

        public Criteria andEnuitemdescNotIn(List<String> values) {
            addCriterion("ENUITEMDESC not in", values, "enuitemdesc");
            return (Criteria) this;
        }

        public Criteria andEnuitemdescBetween(String value1, String value2) {
            addCriterion("ENUITEMDESC between", value1, value2, "enuitemdesc");
            return (Criteria) this;
        }

        public Criteria andEnuitemdescNotBetween(String value1, String value2) {
            addCriterion("ENUITEMDESC not between", value1, value2, "enuitemdesc");
            return (Criteria) this;
        }

        public Criteria andDispnoIsNull() {
            addCriterion("DISPNO is null");
            return (Criteria) this;
        }

        public Criteria andDispnoIsNotNull() {
            addCriterion("DISPNO is not null");
            return (Criteria) this;
        }

        public Criteria andDispnoEqualTo(BigDecimal value) {
            addCriterion("DISPNO =", value, "dispno");
            return (Criteria) this;
        }

        public Criteria andDispnoNotEqualTo(BigDecimal value) {
            addCriterion("DISPNO <>", value, "dispno");
            return (Criteria) this;
        }

        public Criteria andDispnoGreaterThan(BigDecimal value) {
            addCriterion("DISPNO >", value, "dispno");
            return (Criteria) this;
        }

        public Criteria andDispnoGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("DISPNO >=", value, "dispno");
            return (Criteria) this;
        }

        public Criteria andDispnoLessThan(BigDecimal value) {
            addCriterion("DISPNO <", value, "dispno");
            return (Criteria) this;
        }

        public Criteria andDispnoLessThanOrEqualTo(BigDecimal value) {
            addCriterion("DISPNO <=", value, "dispno");
            return (Criteria) this;
        }

        public Criteria andDispnoIn(List<BigDecimal> values) {
            addCriterion("DISPNO in", values, "dispno");
            return (Criteria) this;
        }

        public Criteria andDispnoNotIn(List<BigDecimal> values) {
            addCriterion("DISPNO not in", values, "dispno");
            return (Criteria) this;
        }

        public Criteria andDispnoBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DISPNO between", value1, value2, "dispno");
            return (Criteria) this;
        }

        public Criteria andDispnoNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DISPNO not between", value1, value2, "dispno");
            return (Criteria) this;
        }

        public Criteria andEnuitemexpandIsNull() {
            addCriterion("ENUITEMEXPAND is null");
            return (Criteria) this;
        }

        public Criteria andEnuitemexpandIsNotNull() {
            addCriterion("ENUITEMEXPAND is not null");
            return (Criteria) this;
        }

        public Criteria andEnuitemexpandEqualTo(String value) {
            addCriterion("ENUITEMEXPAND =", value, "enuitemexpand");
            return (Criteria) this;
        }

        public Criteria andEnuitemexpandNotEqualTo(String value) {
            addCriterion("ENUITEMEXPAND <>", value, "enuitemexpand");
            return (Criteria) this;
        }

        public Criteria andEnuitemexpandGreaterThan(String value) {
            addCriterion("ENUITEMEXPAND >", value, "enuitemexpand");
            return (Criteria) this;
        }

        public Criteria andEnuitemexpandGreaterThanOrEqualTo(String value) {
            addCriterion("ENUITEMEXPAND >=", value, "enuitemexpand");
            return (Criteria) this;
        }

        public Criteria andEnuitemexpandLessThan(String value) {
            addCriterion("ENUITEMEXPAND <", value, "enuitemexpand");
            return (Criteria) this;
        }

        public Criteria andEnuitemexpandLessThanOrEqualTo(String value) {
            addCriterion("ENUITEMEXPAND <=", value, "enuitemexpand");
            return (Criteria) this;
        }

        public Criteria andEnuitemexpandLike(String value) {
            addCriterion("ENUITEMEXPAND like", value, "enuitemexpand");
            return (Criteria) this;
        }

        public Criteria andEnuitemexpandNotLike(String value) {
            addCriterion("ENUITEMEXPAND not like", value, "enuitemexpand");
            return (Criteria) this;
        }

        public Criteria andEnuitemexpandIn(List<String> values) {
            addCriterion("ENUITEMEXPAND in", values, "enuitemexpand");
            return (Criteria) this;
        }

        public Criteria andEnuitemexpandNotIn(List<String> values) {
            addCriterion("ENUITEMEXPAND not in", values, "enuitemexpand");
            return (Criteria) this;
        }

        public Criteria andEnuitemexpandBetween(String value1, String value2) {
            addCriterion("ENUITEMEXPAND between", value1, value2, "enuitemexpand");
            return (Criteria) this;
        }

        public Criteria andEnuitemexpandNotBetween(String value1, String value2) {
            addCriterion("ENUITEMEXPAND not between", value1, value2, "enuitemexpand");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table PTENUDETAIL
     *
     * @mbggenerated do_not_delete_during_merge Fri Jul 22 13:16:43 CST 2011
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table PTENUDETAIL
     *
     * @mbggenerated Fri Jul 22 13:16:43 CST 2011
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