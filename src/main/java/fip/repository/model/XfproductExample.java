package fip.repository.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XfproductExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table XFPRODUCT
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table XFPRODUCT
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table XFPRODUCT
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table XFPRODUCT
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    public XfproductExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table XFPRODUCT
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table XFPRODUCT
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table XFPRODUCT
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table XFPRODUCT
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table XFPRODUCT
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table XFPRODUCT
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table XFPRODUCT
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table XFPRODUCT
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
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
     * This method corresponds to the database table XFPRODUCT
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table XFPRODUCT
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table XFPRODUCT
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
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

        public Criteria andProductidIsNull() {
            addCriterion("PRODUCTID is null");
            return (Criteria) this;
        }

        public Criteria andProductidIsNotNull() {
            addCriterion("PRODUCTID is not null");
            return (Criteria) this;
        }

        public Criteria andProductidEqualTo(String value) {
            addCriterion("PRODUCTID =", value, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidNotEqualTo(String value) {
            addCriterion("PRODUCTID <>", value, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidGreaterThan(String value) {
            addCriterion("PRODUCTID >", value, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCTID >=", value, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidLessThan(String value) {
            addCriterion("PRODUCTID <", value, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidLessThanOrEqualTo(String value) {
            addCriterion("PRODUCTID <=", value, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidLike(String value) {
            addCriterion("PRODUCTID like", value, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidNotLike(String value) {
            addCriterion("PRODUCTID not like", value, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidIn(List<String> values) {
            addCriterion("PRODUCTID in", values, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidNotIn(List<String> values) {
            addCriterion("PRODUCTID not in", values, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidBetween(String value1, String value2) {
            addCriterion("PRODUCTID between", value1, value2, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidNotBetween(String value1, String value2) {
            addCriterion("PRODUCTID not between", value1, value2, "productid");
            return (Criteria) this;
        }

        public Criteria andProductnameIsNull() {
            addCriterion("PRODUCTNAME is null");
            return (Criteria) this;
        }

        public Criteria andProductnameIsNotNull() {
            addCriterion("PRODUCTNAME is not null");
            return (Criteria) this;
        }

        public Criteria andProductnameEqualTo(String value) {
            addCriterion("PRODUCTNAME =", value, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameNotEqualTo(String value) {
            addCriterion("PRODUCTNAME <>", value, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameGreaterThan(String value) {
            addCriterion("PRODUCTNAME >", value, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCTNAME >=", value, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameLessThan(String value) {
            addCriterion("PRODUCTNAME <", value, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameLessThanOrEqualTo(String value) {
            addCriterion("PRODUCTNAME <=", value, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameLike(String value) {
            addCriterion("PRODUCTNAME like", value, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameNotLike(String value) {
            addCriterion("PRODUCTNAME not like", value, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameIn(List<String> values) {
            addCriterion("PRODUCTNAME in", values, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameNotIn(List<String> values) {
            addCriterion("PRODUCTNAME not in", values, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameBetween(String value1, String value2) {
            addCriterion("PRODUCTNAME between", value1, value2, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameNotBetween(String value1, String value2) {
            addCriterion("PRODUCTNAME not between", value1, value2, "productname");
            return (Criteria) this;
        }

        public Criteria andSourceidIsNull() {
            addCriterion("SOURCEID is null");
            return (Criteria) this;
        }

        public Criteria andSourceidIsNotNull() {
            addCriterion("SOURCEID is not null");
            return (Criteria) this;
        }

        public Criteria andSourceidEqualTo(String value) {
            addCriterion("SOURCEID =", value, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidNotEqualTo(String value) {
            addCriterion("SOURCEID <>", value, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidGreaterThan(String value) {
            addCriterion("SOURCEID >", value, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidGreaterThanOrEqualTo(String value) {
            addCriterion("SOURCEID >=", value, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidLessThan(String value) {
            addCriterion("SOURCEID <", value, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidLessThanOrEqualTo(String value) {
            addCriterion("SOURCEID <=", value, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidLike(String value) {
            addCriterion("SOURCEID like", value, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidNotLike(String value) {
            addCriterion("SOURCEID not like", value, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidIn(List<String> values) {
            addCriterion("SOURCEID in", values, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidNotIn(List<String> values) {
            addCriterion("SOURCEID not in", values, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidBetween(String value1, String value2) {
            addCriterion("SOURCEID between", value1, value2, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidNotBetween(String value1, String value2) {
            addCriterion("SOURCEID not between", value1, value2, "sourceid");
            return (Criteria) this;
        }

        public Criteria andApptypeIsNull() {
            addCriterion("APPTYPE is null");
            return (Criteria) this;
        }

        public Criteria andApptypeIsNotNull() {
            addCriterion("APPTYPE is not null");
            return (Criteria) this;
        }

        public Criteria andApptypeEqualTo(String value) {
            addCriterion("APPTYPE =", value, "apptype");
            return (Criteria) this;
        }

        public Criteria andApptypeNotEqualTo(String value) {
            addCriterion("APPTYPE <>", value, "apptype");
            return (Criteria) this;
        }

        public Criteria andApptypeGreaterThan(String value) {
            addCriterion("APPTYPE >", value, "apptype");
            return (Criteria) this;
        }

        public Criteria andApptypeGreaterThanOrEqualTo(String value) {
            addCriterion("APPTYPE >=", value, "apptype");
            return (Criteria) this;
        }

        public Criteria andApptypeLessThan(String value) {
            addCriterion("APPTYPE <", value, "apptype");
            return (Criteria) this;
        }

        public Criteria andApptypeLessThanOrEqualTo(String value) {
            addCriterion("APPTYPE <=", value, "apptype");
            return (Criteria) this;
        }

        public Criteria andApptypeLike(String value) {
            addCriterion("APPTYPE like", value, "apptype");
            return (Criteria) this;
        }

        public Criteria andApptypeNotLike(String value) {
            addCriterion("APPTYPE not like", value, "apptype");
            return (Criteria) this;
        }

        public Criteria andApptypeIn(List<String> values) {
            addCriterion("APPTYPE in", values, "apptype");
            return (Criteria) this;
        }

        public Criteria andApptypeNotIn(List<String> values) {
            addCriterion("APPTYPE not in", values, "apptype");
            return (Criteria) this;
        }

        public Criteria andApptypeBetween(String value1, String value2) {
            addCriterion("APPTYPE between", value1, value2, "apptype");
            return (Criteria) this;
        }

        public Criteria andApptypeNotBetween(String value1, String value2) {
            addCriterion("APPTYPE not between", value1, value2, "apptype");
            return (Criteria) this;
        }

        public Criteria andClientcdIsNull() {
            addCriterion("CLIENTCD is null");
            return (Criteria) this;
        }

        public Criteria andClientcdIsNotNull() {
            addCriterion("CLIENTCD is not null");
            return (Criteria) this;
        }

        public Criteria andClientcdEqualTo(String value) {
            addCriterion("CLIENTCD =", value, "clientcd");
            return (Criteria) this;
        }

        public Criteria andClientcdNotEqualTo(String value) {
            addCriterion("CLIENTCD <>", value, "clientcd");
            return (Criteria) this;
        }

        public Criteria andClientcdGreaterThan(String value) {
            addCriterion("CLIENTCD >", value, "clientcd");
            return (Criteria) this;
        }

        public Criteria andClientcdGreaterThanOrEqualTo(String value) {
            addCriterion("CLIENTCD >=", value, "clientcd");
            return (Criteria) this;
        }

        public Criteria andClientcdLessThan(String value) {
            addCriterion("CLIENTCD <", value, "clientcd");
            return (Criteria) this;
        }

        public Criteria andClientcdLessThanOrEqualTo(String value) {
            addCriterion("CLIENTCD <=", value, "clientcd");
            return (Criteria) this;
        }

        public Criteria andClientcdLike(String value) {
            addCriterion("CLIENTCD like", value, "clientcd");
            return (Criteria) this;
        }

        public Criteria andClientcdNotLike(String value) {
            addCriterion("CLIENTCD not like", value, "clientcd");
            return (Criteria) this;
        }

        public Criteria andClientcdIn(List<String> values) {
            addCriterion("CLIENTCD in", values, "clientcd");
            return (Criteria) this;
        }

        public Criteria andClientcdNotIn(List<String> values) {
            addCriterion("CLIENTCD not in", values, "clientcd");
            return (Criteria) this;
        }

        public Criteria andClientcdBetween(String value1, String value2) {
            addCriterion("CLIENTCD between", value1, value2, "clientcd");
            return (Criteria) this;
        }

        public Criteria andClientcdNotBetween(String value1, String value2) {
            addCriterion("CLIENTCD not between", value1, value2, "clientcd");
            return (Criteria) this;
        }

        public Criteria andDurationIsNull() {
            addCriterion("DURATION is null");
            return (Criteria) this;
        }

        public Criteria andDurationIsNotNull() {
            addCriterion("DURATION is not null");
            return (Criteria) this;
        }

        public Criteria andDurationEqualTo(BigDecimal value) {
            addCriterion("DURATION =", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotEqualTo(BigDecimal value) {
            addCriterion("DURATION <>", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThan(BigDecimal value) {
            addCriterion("DURATION >", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("DURATION >=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThan(BigDecimal value) {
            addCriterion("DURATION <", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThanOrEqualTo(BigDecimal value) {
            addCriterion("DURATION <=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationIn(List<BigDecimal> values) {
            addCriterion("DURATION in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotIn(List<BigDecimal> values) {
            addCriterion("DURATION not in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DURATION between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DURATION not between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andServicechargeIsNull() {
            addCriterion("SERVICECHARGE is null");
            return (Criteria) this;
        }

        public Criteria andServicechargeIsNotNull() {
            addCriterion("SERVICECHARGE is not null");
            return (Criteria) this;
        }

        public Criteria andServicechargeEqualTo(BigDecimal value) {
            addCriterion("SERVICECHARGE =", value, "servicecharge");
            return (Criteria) this;
        }

        public Criteria andServicechargeNotEqualTo(BigDecimal value) {
            addCriterion("SERVICECHARGE <>", value, "servicecharge");
            return (Criteria) this;
        }

        public Criteria andServicechargeGreaterThan(BigDecimal value) {
            addCriterion("SERVICECHARGE >", value, "servicecharge");
            return (Criteria) this;
        }

        public Criteria andServicechargeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("SERVICECHARGE >=", value, "servicecharge");
            return (Criteria) this;
        }

        public Criteria andServicechargeLessThan(BigDecimal value) {
            addCriterion("SERVICECHARGE <", value, "servicecharge");
            return (Criteria) this;
        }

        public Criteria andServicechargeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("SERVICECHARGE <=", value, "servicecharge");
            return (Criteria) this;
        }

        public Criteria andServicechargeIn(List<BigDecimal> values) {
            addCriterion("SERVICECHARGE in", values, "servicecharge");
            return (Criteria) this;
        }

        public Criteria andServicechargeNotIn(List<BigDecimal> values) {
            addCriterion("SERVICECHARGE not in", values, "servicecharge");
            return (Criteria) this;
        }

        public Criteria andServicechargeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SERVICECHARGE between", value1, value2, "servicecharge");
            return (Criteria) this;
        }

        public Criteria andServicechargeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SERVICECHARGE not between", value1, value2, "servicecharge");
            return (Criteria) this;
        }

        public Criteria andCreatoridIsNull() {
            addCriterion("CREATORID is null");
            return (Criteria) this;
        }

        public Criteria andCreatoridIsNotNull() {
            addCriterion("CREATORID is not null");
            return (Criteria) this;
        }

        public Criteria andCreatoridEqualTo(String value) {
            addCriterion("CREATORID =", value, "creatorid");
            return (Criteria) this;
        }

        public Criteria andCreatoridNotEqualTo(String value) {
            addCriterion("CREATORID <>", value, "creatorid");
            return (Criteria) this;
        }

        public Criteria andCreatoridGreaterThan(String value) {
            addCriterion("CREATORID >", value, "creatorid");
            return (Criteria) this;
        }

        public Criteria andCreatoridGreaterThanOrEqualTo(String value) {
            addCriterion("CREATORID >=", value, "creatorid");
            return (Criteria) this;
        }

        public Criteria andCreatoridLessThan(String value) {
            addCriterion("CREATORID <", value, "creatorid");
            return (Criteria) this;
        }

        public Criteria andCreatoridLessThanOrEqualTo(String value) {
            addCriterion("CREATORID <=", value, "creatorid");
            return (Criteria) this;
        }

        public Criteria andCreatoridLike(String value) {
            addCriterion("CREATORID like", value, "creatorid");
            return (Criteria) this;
        }

        public Criteria andCreatoridNotLike(String value) {
            addCriterion("CREATORID not like", value, "creatorid");
            return (Criteria) this;
        }

        public Criteria andCreatoridIn(List<String> values) {
            addCriterion("CREATORID in", values, "creatorid");
            return (Criteria) this;
        }

        public Criteria andCreatoridNotIn(List<String> values) {
            addCriterion("CREATORID not in", values, "creatorid");
            return (Criteria) this;
        }

        public Criteria andCreatoridBetween(String value1, String value2) {
            addCriterion("CREATORID between", value1, value2, "creatorid");
            return (Criteria) this;
        }

        public Criteria andCreatoridNotBetween(String value1, String value2) {
            addCriterion("CREATORID not between", value1, value2, "creatorid");
            return (Criteria) this;
        }

        public Criteria andCreatedateIsNull() {
            addCriterion("CREATEDATE is null");
            return (Criteria) this;
        }

        public Criteria andCreatedateIsNotNull() {
            addCriterion("CREATEDATE is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedateEqualTo(Date value) {
            addCriterion("CREATEDATE =", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotEqualTo(Date value) {
            addCriterion("CREATEDATE <>", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateGreaterThan(Date value) {
            addCriterion("CREATEDATE >", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATEDATE >=", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateLessThan(Date value) {
            addCriterion("CREATEDATE <", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateLessThanOrEqualTo(Date value) {
            addCriterion("CREATEDATE <=", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateIn(List<Date> values) {
            addCriterion("CREATEDATE in", values, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotIn(List<Date> values) {
            addCriterion("CREATEDATE not in", values, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateBetween(Date value1, Date value2) {
            addCriterion("CREATEDATE between", value1, value2, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotBetween(Date value1, Date value2) {
            addCriterion("CREATEDATE not between", value1, value2, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreateformIsNull() {
            addCriterion("CREATEFORM is null");
            return (Criteria) this;
        }

        public Criteria andCreateformIsNotNull() {
            addCriterion("CREATEFORM is not null");
            return (Criteria) this;
        }

        public Criteria andCreateformEqualTo(String value) {
            addCriterion("CREATEFORM =", value, "createform");
            return (Criteria) this;
        }

        public Criteria andCreateformNotEqualTo(String value) {
            addCriterion("CREATEFORM <>", value, "createform");
            return (Criteria) this;
        }

        public Criteria andCreateformGreaterThan(String value) {
            addCriterion("CREATEFORM >", value, "createform");
            return (Criteria) this;
        }

        public Criteria andCreateformGreaterThanOrEqualTo(String value) {
            addCriterion("CREATEFORM >=", value, "createform");
            return (Criteria) this;
        }

        public Criteria andCreateformLessThan(String value) {
            addCriterion("CREATEFORM <", value, "createform");
            return (Criteria) this;
        }

        public Criteria andCreateformLessThanOrEqualTo(String value) {
            addCriterion("CREATEFORM <=", value, "createform");
            return (Criteria) this;
        }

        public Criteria andCreateformLike(String value) {
            addCriterion("CREATEFORM like", value, "createform");
            return (Criteria) this;
        }

        public Criteria andCreateformNotLike(String value) {
            addCriterion("CREATEFORM not like", value, "createform");
            return (Criteria) this;
        }

        public Criteria andCreateformIn(List<String> values) {
            addCriterion("CREATEFORM in", values, "createform");
            return (Criteria) this;
        }

        public Criteria andCreateformNotIn(List<String> values) {
            addCriterion("CREATEFORM not in", values, "createform");
            return (Criteria) this;
        }

        public Criteria andCreateformBetween(String value1, String value2) {
            addCriterion("CREATEFORM between", value1, value2, "createform");
            return (Criteria) this;
        }

        public Criteria andCreateformNotBetween(String value1, String value2) {
            addCriterion("CREATEFORM not between", value1, value2, "createform");
            return (Criteria) this;
        }

        public Criteria andUpdatoridIsNull() {
            addCriterion("UPDATORID is null");
            return (Criteria) this;
        }

        public Criteria andUpdatoridIsNotNull() {
            addCriterion("UPDATORID is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatoridEqualTo(String value) {
            addCriterion("UPDATORID =", value, "updatorid");
            return (Criteria) this;
        }

        public Criteria andUpdatoridNotEqualTo(String value) {
            addCriterion("UPDATORID <>", value, "updatorid");
            return (Criteria) this;
        }

        public Criteria andUpdatoridGreaterThan(String value) {
            addCriterion("UPDATORID >", value, "updatorid");
            return (Criteria) this;
        }

        public Criteria andUpdatoridGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATORID >=", value, "updatorid");
            return (Criteria) this;
        }

        public Criteria andUpdatoridLessThan(String value) {
            addCriterion("UPDATORID <", value, "updatorid");
            return (Criteria) this;
        }

        public Criteria andUpdatoridLessThanOrEqualTo(String value) {
            addCriterion("UPDATORID <=", value, "updatorid");
            return (Criteria) this;
        }

        public Criteria andUpdatoridLike(String value) {
            addCriterion("UPDATORID like", value, "updatorid");
            return (Criteria) this;
        }

        public Criteria andUpdatoridNotLike(String value) {
            addCriterion("UPDATORID not like", value, "updatorid");
            return (Criteria) this;
        }

        public Criteria andUpdatoridIn(List<String> values) {
            addCriterion("UPDATORID in", values, "updatorid");
            return (Criteria) this;
        }

        public Criteria andUpdatoridNotIn(List<String> values) {
            addCriterion("UPDATORID not in", values, "updatorid");
            return (Criteria) this;
        }

        public Criteria andUpdatoridBetween(String value1, String value2) {
            addCriterion("UPDATORID between", value1, value2, "updatorid");
            return (Criteria) this;
        }

        public Criteria andUpdatoridNotBetween(String value1, String value2) {
            addCriterion("UPDATORID not between", value1, value2, "updatorid");
            return (Criteria) this;
        }

        public Criteria andUpdatedateIsNull() {
            addCriterion("UPDATEDATE is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedateIsNotNull() {
            addCriterion("UPDATEDATE is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedateEqualTo(Date value) {
            addCriterion("UPDATEDATE =", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateNotEqualTo(Date value) {
            addCriterion("UPDATEDATE <>", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateGreaterThan(Date value) {
            addCriterion("UPDATEDATE >", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATEDATE >=", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateLessThan(Date value) {
            addCriterion("UPDATEDATE <", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateLessThanOrEqualTo(Date value) {
            addCriterion("UPDATEDATE <=", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateIn(List<Date> values) {
            addCriterion("UPDATEDATE in", values, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateNotIn(List<Date> values) {
            addCriterion("UPDATEDATE not in", values, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateBetween(Date value1, Date value2) {
            addCriterion("UPDATEDATE between", value1, value2, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateNotBetween(Date value1, Date value2) {
            addCriterion("UPDATEDATE not between", value1, value2, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdateformIsNull() {
            addCriterion("UPDATEFORM is null");
            return (Criteria) this;
        }

        public Criteria andUpdateformIsNotNull() {
            addCriterion("UPDATEFORM is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateformEqualTo(String value) {
            addCriterion("UPDATEFORM =", value, "updateform");
            return (Criteria) this;
        }

        public Criteria andUpdateformNotEqualTo(String value) {
            addCriterion("UPDATEFORM <>", value, "updateform");
            return (Criteria) this;
        }

        public Criteria andUpdateformGreaterThan(String value) {
            addCriterion("UPDATEFORM >", value, "updateform");
            return (Criteria) this;
        }

        public Criteria andUpdateformGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATEFORM >=", value, "updateform");
            return (Criteria) this;
        }

        public Criteria andUpdateformLessThan(String value) {
            addCriterion("UPDATEFORM <", value, "updateform");
            return (Criteria) this;
        }

        public Criteria andUpdateformLessThanOrEqualTo(String value) {
            addCriterion("UPDATEFORM <=", value, "updateform");
            return (Criteria) this;
        }

        public Criteria andUpdateformLike(String value) {
            addCriterion("UPDATEFORM like", value, "updateform");
            return (Criteria) this;
        }

        public Criteria andUpdateformNotLike(String value) {
            addCriterion("UPDATEFORM not like", value, "updateform");
            return (Criteria) this;
        }

        public Criteria andUpdateformIn(List<String> values) {
            addCriterion("UPDATEFORM in", values, "updateform");
            return (Criteria) this;
        }

        public Criteria andUpdateformNotIn(List<String> values) {
            addCriterion("UPDATEFORM not in", values, "updateform");
            return (Criteria) this;
        }

        public Criteria andUpdateformBetween(String value1, String value2) {
            addCriterion("UPDATEFORM between", value1, value2, "updateform");
            return (Criteria) this;
        }

        public Criteria andUpdateformNotBetween(String value1, String value2) {
            addCriterion("UPDATEFORM not between", value1, value2, "updateform");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table XFPRODUCT
     *
     * @mbggenerated do_not_delete_during_merge Fri Jul 22 13:32:19 CST 2011
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table XFPRODUCT
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
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