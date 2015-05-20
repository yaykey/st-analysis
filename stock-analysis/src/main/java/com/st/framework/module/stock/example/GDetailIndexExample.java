package com.st.framework.module.stock.example;

import com.st.framework.module.example.BaseExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class GDetailIndexExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GDetailIndexExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIcodeIsNull() {
            addCriterion("ICODE is null");
            return (Criteria) this;
        }

        public Criteria andIcodeIsNotNull() {
            addCriterion("ICODE is not null");
            return (Criteria) this;
        }

        public Criteria andIcodeEqualTo(String value) {
            addCriterion("ICODE =", value, "icode");
            return (Criteria) this;
        }

        public Criteria andIcodeNotEqualTo(String value) {
            addCriterion("ICODE <>", value, "icode");
            return (Criteria) this;
        }

        public Criteria andIcodeGreaterThan(String value) {
            addCriterion("ICODE >", value, "icode");
            return (Criteria) this;
        }

        public Criteria andIcodeGreaterThanOrEqualTo(String value) {
            addCriterion("ICODE >=", value, "icode");
            return (Criteria) this;
        }

        public Criteria andIcodeLessThan(String value) {
            addCriterion("ICODE <", value, "icode");
            return (Criteria) this;
        }

        public Criteria andIcodeLessThanOrEqualTo(String value) {
            addCriterion("ICODE <=", value, "icode");
            return (Criteria) this;
        }

        public Criteria andIcodeLike(String value) {
            addCriterion("ICODE like", value, "icode");
            return (Criteria) this;
        }

        public Criteria andIcodeNotLike(String value) {
            addCriterion("ICODE not like", value, "icode");
            return (Criteria) this;
        }

        public Criteria andIcodeIn(List<String> values) {
            addCriterion("ICODE in", values, "icode");
            return (Criteria) this;
        }

        public Criteria andIcodeNotIn(List<String> values) {
            addCriterion("ICODE not in", values, "icode");
            return (Criteria) this;
        }

        public Criteria andIcodeBetween(String value1, String value2) {
            addCriterion("ICODE between", value1, value2, "icode");
            return (Criteria) this;
        }

        public Criteria andIcodeNotBetween(String value1, String value2) {
            addCriterion("ICODE not between", value1, value2, "icode");
            return (Criteria) this;
        }

        public Criteria andDateidIsNull() {
            addCriterion("DATEID is null");
            return (Criteria) this;
        }

        public Criteria andDateidIsNotNull() {
            addCriterion("DATEID is not null");
            return (Criteria) this;
        }

        public Criteria andDateidEqualTo(Date value) {
            addCriterionForJDBCDate("DATEID =", value, "dateid");
            return (Criteria) this;
        }

        public Criteria andDateidNotEqualTo(Date value) {
            addCriterionForJDBCDate("DATEID <>", value, "dateid");
            return (Criteria) this;
        }

        public Criteria andDateidGreaterThan(Date value) {
            addCriterionForJDBCDate("DATEID >", value, "dateid");
            return (Criteria) this;
        }

        public Criteria andDateidGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("DATEID >=", value, "dateid");
            return (Criteria) this;
        }

        public Criteria andDateidLessThan(Date value) {
            addCriterionForJDBCDate("DATEID <", value, "dateid");
            return (Criteria) this;
        }

        public Criteria andDateidLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("DATEID <=", value, "dateid");
            return (Criteria) this;
        }

        public Criteria andDateidIn(List<Date> values) {
            addCriterionForJDBCDate("DATEID in", values, "dateid");
            return (Criteria) this;
        }

        public Criteria andDateidNotIn(List<Date> values) {
            addCriterionForJDBCDate("DATEID not in", values, "dateid");
            return (Criteria) this;
        }

        public Criteria andDateidBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("DATEID between", value1, value2, "dateid");
            return (Criteria) this;
        }

        public Criteria andDateidNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("DATEID not between", value1, value2, "dateid");
            return (Criteria) this;
        }

        public Criteria andInameIsNull() {
            addCriterion("INAME is null");
            return (Criteria) this;
        }

        public Criteria andInameIsNotNull() {
            addCriterion("INAME is not null");
            return (Criteria) this;
        }

        public Criteria andInameEqualTo(String value) {
            addCriterion("INAME =", value, "iname");
            return (Criteria) this;
        }

        public Criteria andInameNotEqualTo(String value) {
            addCriterion("INAME <>", value, "iname");
            return (Criteria) this;
        }

        public Criteria andInameGreaterThan(String value) {
            addCriterion("INAME >", value, "iname");
            return (Criteria) this;
        }

        public Criteria andInameGreaterThanOrEqualTo(String value) {
            addCriterion("INAME >=", value, "iname");
            return (Criteria) this;
        }

        public Criteria andInameLessThan(String value) {
            addCriterion("INAME <", value, "iname");
            return (Criteria) this;
        }

        public Criteria andInameLessThanOrEqualTo(String value) {
            addCriterion("INAME <=", value, "iname");
            return (Criteria) this;
        }

        public Criteria andInameLike(String value) {
            addCriterion("INAME like", value, "iname");
            return (Criteria) this;
        }

        public Criteria andInameNotLike(String value) {
            addCriterion("INAME not like", value, "iname");
            return (Criteria) this;
        }

        public Criteria andInameIn(List<String> values) {
            addCriterion("INAME in", values, "iname");
            return (Criteria) this;
        }

        public Criteria andInameNotIn(List<String> values) {
            addCriterion("INAME not in", values, "iname");
            return (Criteria) this;
        }

        public Criteria andInameBetween(String value1, String value2) {
            addCriterion("INAME between", value1, value2, "iname");
            return (Criteria) this;
        }

        public Criteria andInameNotBetween(String value1, String value2) {
            addCriterion("INAME not between", value1, value2, "iname");
            return (Criteria) this;
        }

        public Criteria andTcloseIsNull() {
            addCriterion("TCLOSE is null");
            return (Criteria) this;
        }

        public Criteria andTcloseIsNotNull() {
            addCriterion("TCLOSE is not null");
            return (Criteria) this;
        }

        public Criteria andTcloseEqualTo(Double value) {
            addCriterion("TCLOSE =", value, "tclose");
            return (Criteria) this;
        }

        public Criteria andTcloseNotEqualTo(Double value) {
            addCriterion("TCLOSE <>", value, "tclose");
            return (Criteria) this;
        }

        public Criteria andTcloseGreaterThan(Double value) {
            addCriterion("TCLOSE >", value, "tclose");
            return (Criteria) this;
        }

        public Criteria andTcloseGreaterThanOrEqualTo(Double value) {
            addCriterion("TCLOSE >=", value, "tclose");
            return (Criteria) this;
        }

        public Criteria andTcloseLessThan(Double value) {
            addCriterion("TCLOSE <", value, "tclose");
            return (Criteria) this;
        }

        public Criteria andTcloseLessThanOrEqualTo(Double value) {
            addCriterion("TCLOSE <=", value, "tclose");
            return (Criteria) this;
        }

        public Criteria andTcloseIn(List<Double> values) {
            addCriterion("TCLOSE in", values, "tclose");
            return (Criteria) this;
        }

        public Criteria andTcloseNotIn(List<Double> values) {
            addCriterion("TCLOSE not in", values, "tclose");
            return (Criteria) this;
        }

        public Criteria andTcloseBetween(Double value1, Double value2) {
            addCriterion("TCLOSE between", value1, value2, "tclose");
            return (Criteria) this;
        }

        public Criteria andTcloseNotBetween(Double value1, Double value2) {
            addCriterion("TCLOSE not between", value1, value2, "tclose");
            return (Criteria) this;
        }

        public Criteria andHighIsNull() {
            addCriterion("HIGH is null");
            return (Criteria) this;
        }

        public Criteria andHighIsNotNull() {
            addCriterion("HIGH is not null");
            return (Criteria) this;
        }

        public Criteria andHighEqualTo(Double value) {
            addCriterion("HIGH =", value, "high");
            return (Criteria) this;
        }

        public Criteria andHighNotEqualTo(Double value) {
            addCriterion("HIGH <>", value, "high");
            return (Criteria) this;
        }

        public Criteria andHighGreaterThan(Double value) {
            addCriterion("HIGH >", value, "high");
            return (Criteria) this;
        }

        public Criteria andHighGreaterThanOrEqualTo(Double value) {
            addCriterion("HIGH >=", value, "high");
            return (Criteria) this;
        }

        public Criteria andHighLessThan(Double value) {
            addCriterion("HIGH <", value, "high");
            return (Criteria) this;
        }

        public Criteria andHighLessThanOrEqualTo(Double value) {
            addCriterion("HIGH <=", value, "high");
            return (Criteria) this;
        }

        public Criteria andHighIn(List<Double> values) {
            addCriterion("HIGH in", values, "high");
            return (Criteria) this;
        }

        public Criteria andHighNotIn(List<Double> values) {
            addCriterion("HIGH not in", values, "high");
            return (Criteria) this;
        }

        public Criteria andHighBetween(Double value1, Double value2) {
            addCriterion("HIGH between", value1, value2, "high");
            return (Criteria) this;
        }

        public Criteria andHighNotBetween(Double value1, Double value2) {
            addCriterion("HIGH not between", value1, value2, "high");
            return (Criteria) this;
        }

        public Criteria andLowIsNull() {
            addCriterion("LOW is null");
            return (Criteria) this;
        }

        public Criteria andLowIsNotNull() {
            addCriterion("LOW is not null");
            return (Criteria) this;
        }

        public Criteria andLowEqualTo(Double value) {
            addCriterion("LOW =", value, "low");
            return (Criteria) this;
        }

        public Criteria andLowNotEqualTo(Double value) {
            addCriterion("LOW <>", value, "low");
            return (Criteria) this;
        }

        public Criteria andLowGreaterThan(Double value) {
            addCriterion("LOW >", value, "low");
            return (Criteria) this;
        }

        public Criteria andLowGreaterThanOrEqualTo(Double value) {
            addCriterion("LOW >=", value, "low");
            return (Criteria) this;
        }

        public Criteria andLowLessThan(Double value) {
            addCriterion("LOW <", value, "low");
            return (Criteria) this;
        }

        public Criteria andLowLessThanOrEqualTo(Double value) {
            addCriterion("LOW <=", value, "low");
            return (Criteria) this;
        }

        public Criteria andLowIn(List<Double> values) {
            addCriterion("LOW in", values, "low");
            return (Criteria) this;
        }

        public Criteria andLowNotIn(List<Double> values) {
            addCriterion("LOW not in", values, "low");
            return (Criteria) this;
        }

        public Criteria andLowBetween(Double value1, Double value2) {
            addCriterion("LOW between", value1, value2, "low");
            return (Criteria) this;
        }

        public Criteria andLowNotBetween(Double value1, Double value2) {
            addCriterion("LOW not between", value1, value2, "low");
            return (Criteria) this;
        }

        public Criteria andTopenIsNull() {
            addCriterion("TOPEN is null");
            return (Criteria) this;
        }

        public Criteria andTopenIsNotNull() {
            addCriterion("TOPEN is not null");
            return (Criteria) this;
        }

        public Criteria andTopenEqualTo(Double value) {
            addCriterion("TOPEN =", value, "topen");
            return (Criteria) this;
        }

        public Criteria andTopenNotEqualTo(Double value) {
            addCriterion("TOPEN <>", value, "topen");
            return (Criteria) this;
        }

        public Criteria andTopenGreaterThan(Double value) {
            addCriterion("TOPEN >", value, "topen");
            return (Criteria) this;
        }

        public Criteria andTopenGreaterThanOrEqualTo(Double value) {
            addCriterion("TOPEN >=", value, "topen");
            return (Criteria) this;
        }

        public Criteria andTopenLessThan(Double value) {
            addCriterion("TOPEN <", value, "topen");
            return (Criteria) this;
        }

        public Criteria andTopenLessThanOrEqualTo(Double value) {
            addCriterion("TOPEN <=", value, "topen");
            return (Criteria) this;
        }

        public Criteria andTopenIn(List<Double> values) {
            addCriterion("TOPEN in", values, "topen");
            return (Criteria) this;
        }

        public Criteria andTopenNotIn(List<Double> values) {
            addCriterion("TOPEN not in", values, "topen");
            return (Criteria) this;
        }

        public Criteria andTopenBetween(Double value1, Double value2) {
            addCriterion("TOPEN between", value1, value2, "topen");
            return (Criteria) this;
        }

        public Criteria andTopenNotBetween(Double value1, Double value2) {
            addCriterion("TOPEN not between", value1, value2, "topen");
            return (Criteria) this;
        }

        public Criteria andLcloseIsNull() {
            addCriterion("LCLOSE is null");
            return (Criteria) this;
        }

        public Criteria andLcloseIsNotNull() {
            addCriterion("LCLOSE is not null");
            return (Criteria) this;
        }

        public Criteria andLcloseEqualTo(Double value) {
            addCriterion("LCLOSE =", value, "lclose");
            return (Criteria) this;
        }

        public Criteria andLcloseNotEqualTo(Double value) {
            addCriterion("LCLOSE <>", value, "lclose");
            return (Criteria) this;
        }

        public Criteria andLcloseGreaterThan(Double value) {
            addCriterion("LCLOSE >", value, "lclose");
            return (Criteria) this;
        }

        public Criteria andLcloseGreaterThanOrEqualTo(Double value) {
            addCriterion("LCLOSE >=", value, "lclose");
            return (Criteria) this;
        }

        public Criteria andLcloseLessThan(Double value) {
            addCriterion("LCLOSE <", value, "lclose");
            return (Criteria) this;
        }

        public Criteria andLcloseLessThanOrEqualTo(Double value) {
            addCriterion("LCLOSE <=", value, "lclose");
            return (Criteria) this;
        }

        public Criteria andLcloseIn(List<Double> values) {
            addCriterion("LCLOSE in", values, "lclose");
            return (Criteria) this;
        }

        public Criteria andLcloseNotIn(List<Double> values) {
            addCriterion("LCLOSE not in", values, "lclose");
            return (Criteria) this;
        }

        public Criteria andLcloseBetween(Double value1, Double value2) {
            addCriterion("LCLOSE between", value1, value2, "lclose");
            return (Criteria) this;
        }

        public Criteria andLcloseNotBetween(Double value1, Double value2) {
            addCriterion("LCLOSE not between", value1, value2, "lclose");
            return (Criteria) this;
        }

        public Criteria andChgIsNull() {
            addCriterion("CHG is null");
            return (Criteria) this;
        }

        public Criteria andChgIsNotNull() {
            addCriterion("CHG is not null");
            return (Criteria) this;
        }

        public Criteria andChgEqualTo(Double value) {
            addCriterion("CHG =", value, "chg");
            return (Criteria) this;
        }

        public Criteria andChgNotEqualTo(Double value) {
            addCriterion("CHG <>", value, "chg");
            return (Criteria) this;
        }

        public Criteria andChgGreaterThan(Double value) {
            addCriterion("CHG >", value, "chg");
            return (Criteria) this;
        }

        public Criteria andChgGreaterThanOrEqualTo(Double value) {
            addCriterion("CHG >=", value, "chg");
            return (Criteria) this;
        }

        public Criteria andChgLessThan(Double value) {
            addCriterion("CHG <", value, "chg");
            return (Criteria) this;
        }

        public Criteria andChgLessThanOrEqualTo(Double value) {
            addCriterion("CHG <=", value, "chg");
            return (Criteria) this;
        }

        public Criteria andChgIn(List<Double> values) {
            addCriterion("CHG in", values, "chg");
            return (Criteria) this;
        }

        public Criteria andChgNotIn(List<Double> values) {
            addCriterion("CHG not in", values, "chg");
            return (Criteria) this;
        }

        public Criteria andChgBetween(Double value1, Double value2) {
            addCriterion("CHG between", value1, value2, "chg");
            return (Criteria) this;
        }

        public Criteria andChgNotBetween(Double value1, Double value2) {
            addCriterion("CHG not between", value1, value2, "chg");
            return (Criteria) this;
        }

        public Criteria andPchgIsNull() {
            addCriterion("PCHG is null");
            return (Criteria) this;
        }

        public Criteria andPchgIsNotNull() {
            addCriterion("PCHG is not null");
            return (Criteria) this;
        }

        public Criteria andPchgEqualTo(Double value) {
            addCriterion("PCHG =", value, "pchg");
            return (Criteria) this;
        }

        public Criteria andPchgNotEqualTo(Double value) {
            addCriterion("PCHG <>", value, "pchg");
            return (Criteria) this;
        }

        public Criteria andPchgGreaterThan(Double value) {
            addCriterion("PCHG >", value, "pchg");
            return (Criteria) this;
        }

        public Criteria andPchgGreaterThanOrEqualTo(Double value) {
            addCriterion("PCHG >=", value, "pchg");
            return (Criteria) this;
        }

        public Criteria andPchgLessThan(Double value) {
            addCriterion("PCHG <", value, "pchg");
            return (Criteria) this;
        }

        public Criteria andPchgLessThanOrEqualTo(Double value) {
            addCriterion("PCHG <=", value, "pchg");
            return (Criteria) this;
        }

        public Criteria andPchgIn(List<Double> values) {
            addCriterion("PCHG in", values, "pchg");
            return (Criteria) this;
        }

        public Criteria andPchgNotIn(List<Double> values) {
            addCriterion("PCHG not in", values, "pchg");
            return (Criteria) this;
        }

        public Criteria andPchgBetween(Double value1, Double value2) {
            addCriterion("PCHG between", value1, value2, "pchg");
            return (Criteria) this;
        }

        public Criteria andPchgNotBetween(Double value1, Double value2) {
            addCriterion("PCHG not between", value1, value2, "pchg");
            return (Criteria) this;
        }

        public Criteria andVoturnoverIsNull() {
            addCriterion("VOTURNOVER is null");
            return (Criteria) this;
        }

        public Criteria andVoturnoverIsNotNull() {
            addCriterion("VOTURNOVER is not null");
            return (Criteria) this;
        }

        public Criteria andVoturnoverEqualTo(Integer value) {
            addCriterion("VOTURNOVER =", value, "voturnover");
            return (Criteria) this;
        }

        public Criteria andVoturnoverNotEqualTo(Integer value) {
            addCriterion("VOTURNOVER <>", value, "voturnover");
            return (Criteria) this;
        }

        public Criteria andVoturnoverGreaterThan(Integer value) {
            addCriterion("VOTURNOVER >", value, "voturnover");
            return (Criteria) this;
        }

        public Criteria andVoturnoverGreaterThanOrEqualTo(Integer value) {
            addCriterion("VOTURNOVER >=", value, "voturnover");
            return (Criteria) this;
        }

        public Criteria andVoturnoverLessThan(Integer value) {
            addCriterion("VOTURNOVER <", value, "voturnover");
            return (Criteria) this;
        }

        public Criteria andVoturnoverLessThanOrEqualTo(Integer value) {
            addCriterion("VOTURNOVER <=", value, "voturnover");
            return (Criteria) this;
        }

        public Criteria andVoturnoverIn(List<Integer> values) {
            addCriterion("VOTURNOVER in", values, "voturnover");
            return (Criteria) this;
        }

        public Criteria andVoturnoverNotIn(List<Integer> values) {
            addCriterion("VOTURNOVER not in", values, "voturnover");
            return (Criteria) this;
        }

        public Criteria andVoturnoverBetween(Integer value1, Integer value2) {
            addCriterion("VOTURNOVER between", value1, value2, "voturnover");
            return (Criteria) this;
        }

        public Criteria andVoturnoverNotBetween(Integer value1, Integer value2) {
            addCriterion("VOTURNOVER not between", value1, value2, "voturnover");
            return (Criteria) this;
        }

        public Criteria andVaturnoverIsNull() {
            addCriterion("VATURNOVER is null");
            return (Criteria) this;
        }

        public Criteria andVaturnoverIsNotNull() {
            addCriterion("VATURNOVER is not null");
            return (Criteria) this;
        }

        public Criteria andVaturnoverEqualTo(Long value) {
            addCriterion("VATURNOVER =", value, "vaturnover");
            return (Criteria) this;
        }

        public Criteria andVaturnoverNotEqualTo(Long value) {
            addCriterion("VATURNOVER <>", value, "vaturnover");
            return (Criteria) this;
        }

        public Criteria andVaturnoverGreaterThan(Long value) {
            addCriterion("VATURNOVER >", value, "vaturnover");
            return (Criteria) this;
        }

        public Criteria andVaturnoverGreaterThanOrEqualTo(Long value) {
            addCriterion("VATURNOVER >=", value, "vaturnover");
            return (Criteria) this;
        }

        public Criteria andVaturnoverLessThan(Long value) {
            addCriterion("VATURNOVER <", value, "vaturnover");
            return (Criteria) this;
        }

        public Criteria andVaturnoverLessThanOrEqualTo(Long value) {
            addCriterion("VATURNOVER <=", value, "vaturnover");
            return (Criteria) this;
        }

        public Criteria andVaturnoverIn(List<Long> values) {
            addCriterion("VATURNOVER in", values, "vaturnover");
            return (Criteria) this;
        }

        public Criteria andVaturnoverNotIn(List<Long> values) {
            addCriterion("VATURNOVER not in", values, "vaturnover");
            return (Criteria) this;
        }

        public Criteria andVaturnoverBetween(Long value1, Long value2) {
            addCriterion("VATURNOVER between", value1, value2, "vaturnover");
            return (Criteria) this;
        }

        public Criteria andVaturnoverNotBetween(Long value1, Long value2) {
            addCriterion("VATURNOVER not between", value1, value2, "vaturnover");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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