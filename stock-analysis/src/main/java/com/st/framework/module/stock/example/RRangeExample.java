package com.st.framework.module.stock.example;

import com.st.framework.module.example.BaseExample;
import java.util.ArrayList;
import java.util.List;

public class RRangeExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RRangeExample() {
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

        public Criteria andRptIdIsNull() {
            addCriterion("RPT_ID is null");
            return (Criteria) this;
        }

        public Criteria andRptIdIsNotNull() {
            addCriterion("RPT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRptIdEqualTo(Integer value) {
            addCriterion("RPT_ID =", value, "rptId");
            return (Criteria) this;
        }

        public Criteria andRptIdNotEqualTo(Integer value) {
            addCriterion("RPT_ID <>", value, "rptId");
            return (Criteria) this;
        }

        public Criteria andRptIdGreaterThan(Integer value) {
            addCriterion("RPT_ID >", value, "rptId");
            return (Criteria) this;
        }

        public Criteria andRptIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("RPT_ID >=", value, "rptId");
            return (Criteria) this;
        }

        public Criteria andRptIdLessThan(Integer value) {
            addCriterion("RPT_ID <", value, "rptId");
            return (Criteria) this;
        }

        public Criteria andRptIdLessThanOrEqualTo(Integer value) {
            addCriterion("RPT_ID <=", value, "rptId");
            return (Criteria) this;
        }

        public Criteria andRptIdIn(List<Integer> values) {
            addCriterion("RPT_ID in", values, "rptId");
            return (Criteria) this;
        }

        public Criteria andRptIdNotIn(List<Integer> values) {
            addCriterion("RPT_ID not in", values, "rptId");
            return (Criteria) this;
        }

        public Criteria andRptIdBetween(Integer value1, Integer value2) {
            addCriterion("RPT_ID between", value1, value2, "rptId");
            return (Criteria) this;
        }

        public Criteria andRptIdNotBetween(Integer value1, Integer value2) {
            addCriterion("RPT_ID not between", value1, value2, "rptId");
            return (Criteria) this;
        }

        public Criteria andIndexIdIsNull() {
            addCriterion("INDEX_ID is null");
            return (Criteria) this;
        }

        public Criteria andIndexIdIsNotNull() {
            addCriterion("INDEX_ID is not null");
            return (Criteria) this;
        }

        public Criteria andIndexIdEqualTo(Integer value) {
            addCriterion("INDEX_ID =", value, "indexId");
            return (Criteria) this;
        }

        public Criteria andIndexIdNotEqualTo(Integer value) {
            addCriterion("INDEX_ID <>", value, "indexId");
            return (Criteria) this;
        }

        public Criteria andIndexIdGreaterThan(Integer value) {
            addCriterion("INDEX_ID >", value, "indexId");
            return (Criteria) this;
        }

        public Criteria andIndexIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("INDEX_ID >=", value, "indexId");
            return (Criteria) this;
        }

        public Criteria andIndexIdLessThan(Integer value) {
            addCriterion("INDEX_ID <", value, "indexId");
            return (Criteria) this;
        }

        public Criteria andIndexIdLessThanOrEqualTo(Integer value) {
            addCriterion("INDEX_ID <=", value, "indexId");
            return (Criteria) this;
        }

        public Criteria andIndexIdIn(List<Integer> values) {
            addCriterion("INDEX_ID in", values, "indexId");
            return (Criteria) this;
        }

        public Criteria andIndexIdNotIn(List<Integer> values) {
            addCriterion("INDEX_ID not in", values, "indexId");
            return (Criteria) this;
        }

        public Criteria andIndexIdBetween(Integer value1, Integer value2) {
            addCriterion("INDEX_ID between", value1, value2, "indexId");
            return (Criteria) this;
        }

        public Criteria andIndexIdNotBetween(Integer value1, Integer value2) {
            addCriterion("INDEX_ID not between", value1, value2, "indexId");
            return (Criteria) this;
        }

        public Criteria andVolDimtypeIdIsNull() {
            addCriterion("VOL_DIMTYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andVolDimtypeIdIsNotNull() {
            addCriterion("VOL_DIMTYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andVolDimtypeIdEqualTo(Integer value) {
            addCriterion("VOL_DIMTYPE_ID =", value, "volDimtypeId");
            return (Criteria) this;
        }

        public Criteria andVolDimtypeIdNotEqualTo(Integer value) {
            addCriterion("VOL_DIMTYPE_ID <>", value, "volDimtypeId");
            return (Criteria) this;
        }

        public Criteria andVolDimtypeIdGreaterThan(Integer value) {
            addCriterion("VOL_DIMTYPE_ID >", value, "volDimtypeId");
            return (Criteria) this;
        }

        public Criteria andVolDimtypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("VOL_DIMTYPE_ID >=", value, "volDimtypeId");
            return (Criteria) this;
        }

        public Criteria andVolDimtypeIdLessThan(Integer value) {
            addCriterion("VOL_DIMTYPE_ID <", value, "volDimtypeId");
            return (Criteria) this;
        }

        public Criteria andVolDimtypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("VOL_DIMTYPE_ID <=", value, "volDimtypeId");
            return (Criteria) this;
        }

        public Criteria andVolDimtypeIdIn(List<Integer> values) {
            addCriterion("VOL_DIMTYPE_ID in", values, "volDimtypeId");
            return (Criteria) this;
        }

        public Criteria andVolDimtypeIdNotIn(List<Integer> values) {
            addCriterion("VOL_DIMTYPE_ID not in", values, "volDimtypeId");
            return (Criteria) this;
        }

        public Criteria andVolDimtypeIdBetween(Integer value1, Integer value2) {
            addCriterion("VOL_DIMTYPE_ID between", value1, value2, "volDimtypeId");
            return (Criteria) this;
        }

        public Criteria andVolDimtypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("VOL_DIMTYPE_ID not between", value1, value2, "volDimtypeId");
            return (Criteria) this;
        }

        public Criteria andVolDimIdIsNull() {
            addCriterion("VOL_DIM_ID is null");
            return (Criteria) this;
        }

        public Criteria andVolDimIdIsNotNull() {
            addCriterion("VOL_DIM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andVolDimIdEqualTo(Integer value) {
            addCriterion("VOL_DIM_ID =", value, "volDimId");
            return (Criteria) this;
        }

        public Criteria andVolDimIdNotEqualTo(Integer value) {
            addCriterion("VOL_DIM_ID <>", value, "volDimId");
            return (Criteria) this;
        }

        public Criteria andVolDimIdGreaterThan(Integer value) {
            addCriterion("VOL_DIM_ID >", value, "volDimId");
            return (Criteria) this;
        }

        public Criteria andVolDimIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("VOL_DIM_ID >=", value, "volDimId");
            return (Criteria) this;
        }

        public Criteria andVolDimIdLessThan(Integer value) {
            addCriterion("VOL_DIM_ID <", value, "volDimId");
            return (Criteria) this;
        }

        public Criteria andVolDimIdLessThanOrEqualTo(Integer value) {
            addCriterion("VOL_DIM_ID <=", value, "volDimId");
            return (Criteria) this;
        }

        public Criteria andVolDimIdIn(List<Integer> values) {
            addCriterion("VOL_DIM_ID in", values, "volDimId");
            return (Criteria) this;
        }

        public Criteria andVolDimIdNotIn(List<Integer> values) {
            addCriterion("VOL_DIM_ID not in", values, "volDimId");
            return (Criteria) this;
        }

        public Criteria andVolDimIdBetween(Integer value1, Integer value2) {
            addCriterion("VOL_DIM_ID between", value1, value2, "volDimId");
            return (Criteria) this;
        }

        public Criteria andVolDimIdNotBetween(Integer value1, Integer value2) {
            addCriterion("VOL_DIM_ID not between", value1, value2, "volDimId");
            return (Criteria) this;
        }

        public Criteria andDateIdIsNull() {
            addCriterion("DATE_ID is null");
            return (Criteria) this;
        }

        public Criteria andDateIdIsNotNull() {
            addCriterion("DATE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDateIdEqualTo(Integer value) {
            addCriterion("DATE_ID =", value, "dateId");
            return (Criteria) this;
        }

        public Criteria andDateIdNotEqualTo(Integer value) {
            addCriterion("DATE_ID <>", value, "dateId");
            return (Criteria) this;
        }

        public Criteria andDateIdGreaterThan(Integer value) {
            addCriterion("DATE_ID >", value, "dateId");
            return (Criteria) this;
        }

        public Criteria andDateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("DATE_ID >=", value, "dateId");
            return (Criteria) this;
        }

        public Criteria andDateIdLessThan(Integer value) {
            addCriterion("DATE_ID <", value, "dateId");
            return (Criteria) this;
        }

        public Criteria andDateIdLessThanOrEqualTo(Integer value) {
            addCriterion("DATE_ID <=", value, "dateId");
            return (Criteria) this;
        }

        public Criteria andDateIdIn(List<Integer> values) {
            addCriterion("DATE_ID in", values, "dateId");
            return (Criteria) this;
        }

        public Criteria andDateIdNotIn(List<Integer> values) {
            addCriterion("DATE_ID not in", values, "dateId");
            return (Criteria) this;
        }

        public Criteria andDateIdBetween(Integer value1, Integer value2) {
            addCriterion("DATE_ID between", value1, value2, "dateId");
            return (Criteria) this;
        }

        public Criteria andDateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("DATE_ID not between", value1, value2, "dateId");
            return (Criteria) this;
        }

        public Criteria andStockCodeIsNull() {
            addCriterion("STOCK_CODE is null");
            return (Criteria) this;
        }

        public Criteria andStockCodeIsNotNull() {
            addCriterion("STOCK_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andStockCodeEqualTo(Integer value) {
            addCriterion("STOCK_CODE =", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeNotEqualTo(Integer value) {
            addCriterion("STOCK_CODE <>", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeGreaterThan(Integer value) {
            addCriterion("STOCK_CODE >", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("STOCK_CODE >=", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeLessThan(Integer value) {
            addCriterion("STOCK_CODE <", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeLessThanOrEqualTo(Integer value) {
            addCriterion("STOCK_CODE <=", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeIn(List<Integer> values) {
            addCriterion("STOCK_CODE in", values, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeNotIn(List<Integer> values) {
            addCriterion("STOCK_CODE not in", values, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeBetween(Integer value1, Integer value2) {
            addCriterion("STOCK_CODE between", value1, value2, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("STOCK_CODE not between", value1, value2, "stockCode");
            return (Criteria) this;
        }

        public Criteria andTimeDimtypeIdIsNull() {
            addCriterion("TIME_DIMTYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andTimeDimtypeIdIsNotNull() {
            addCriterion("TIME_DIMTYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTimeDimtypeIdEqualTo(Integer value) {
            addCriterion("TIME_DIMTYPE_ID =", value, "timeDimtypeId");
            return (Criteria) this;
        }

        public Criteria andTimeDimtypeIdNotEqualTo(Integer value) {
            addCriterion("TIME_DIMTYPE_ID <>", value, "timeDimtypeId");
            return (Criteria) this;
        }

        public Criteria andTimeDimtypeIdGreaterThan(Integer value) {
            addCriterion("TIME_DIMTYPE_ID >", value, "timeDimtypeId");
            return (Criteria) this;
        }

        public Criteria andTimeDimtypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("TIME_DIMTYPE_ID >=", value, "timeDimtypeId");
            return (Criteria) this;
        }

        public Criteria andTimeDimtypeIdLessThan(Integer value) {
            addCriterion("TIME_DIMTYPE_ID <", value, "timeDimtypeId");
            return (Criteria) this;
        }

        public Criteria andTimeDimtypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("TIME_DIMTYPE_ID <=", value, "timeDimtypeId");
            return (Criteria) this;
        }

        public Criteria andTimeDimtypeIdIn(List<Integer> values) {
            addCriterion("TIME_DIMTYPE_ID in", values, "timeDimtypeId");
            return (Criteria) this;
        }

        public Criteria andTimeDimtypeIdNotIn(List<Integer> values) {
            addCriterion("TIME_DIMTYPE_ID not in", values, "timeDimtypeId");
            return (Criteria) this;
        }

        public Criteria andTimeDimtypeIdBetween(Integer value1, Integer value2) {
            addCriterion("TIME_DIMTYPE_ID between", value1, value2, "timeDimtypeId");
            return (Criteria) this;
        }

        public Criteria andTimeDimtypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("TIME_DIMTYPE_ID not between", value1, value2, "timeDimtypeId");
            return (Criteria) this;
        }

        public Criteria andTimeDimIdIsNull() {
            addCriterion("TIME_DIM_ID is null");
            return (Criteria) this;
        }

        public Criteria andTimeDimIdIsNotNull() {
            addCriterion("TIME_DIM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTimeDimIdEqualTo(Integer value) {
            addCriterion("TIME_DIM_ID =", value, "timeDimId");
            return (Criteria) this;
        }

        public Criteria andTimeDimIdNotEqualTo(Integer value) {
            addCriterion("TIME_DIM_ID <>", value, "timeDimId");
            return (Criteria) this;
        }

        public Criteria andTimeDimIdGreaterThan(Integer value) {
            addCriterion("TIME_DIM_ID >", value, "timeDimId");
            return (Criteria) this;
        }

        public Criteria andTimeDimIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("TIME_DIM_ID >=", value, "timeDimId");
            return (Criteria) this;
        }

        public Criteria andTimeDimIdLessThan(Integer value) {
            addCriterion("TIME_DIM_ID <", value, "timeDimId");
            return (Criteria) this;
        }

        public Criteria andTimeDimIdLessThanOrEqualTo(Integer value) {
            addCriterion("TIME_DIM_ID <=", value, "timeDimId");
            return (Criteria) this;
        }

        public Criteria andTimeDimIdIn(List<Integer> values) {
            addCriterion("TIME_DIM_ID in", values, "timeDimId");
            return (Criteria) this;
        }

        public Criteria andTimeDimIdNotIn(List<Integer> values) {
            addCriterion("TIME_DIM_ID not in", values, "timeDimId");
            return (Criteria) this;
        }

        public Criteria andTimeDimIdBetween(Integer value1, Integer value2) {
            addCriterion("TIME_DIM_ID between", value1, value2, "timeDimId");
            return (Criteria) this;
        }

        public Criteria andTimeDimIdNotBetween(Integer value1, Integer value2) {
            addCriterion("TIME_DIM_ID not between", value1, value2, "timeDimId");
            return (Criteria) this;
        }

        public Criteria andVolValueIsNull() {
            addCriterion("VOL_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andVolValueIsNotNull() {
            addCriterion("VOL_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andVolValueEqualTo(Integer value) {
            addCriterion("VOL_VALUE =", value, "volValue");
            return (Criteria) this;
        }

        public Criteria andVolValueNotEqualTo(Integer value) {
            addCriterion("VOL_VALUE <>", value, "volValue");
            return (Criteria) this;
        }

        public Criteria andVolValueGreaterThan(Integer value) {
            addCriterion("VOL_VALUE >", value, "volValue");
            return (Criteria) this;
        }

        public Criteria andVolValueGreaterThanOrEqualTo(Integer value) {
            addCriterion("VOL_VALUE >=", value, "volValue");
            return (Criteria) this;
        }

        public Criteria andVolValueLessThan(Integer value) {
            addCriterion("VOL_VALUE <", value, "volValue");
            return (Criteria) this;
        }

        public Criteria andVolValueLessThanOrEqualTo(Integer value) {
            addCriterion("VOL_VALUE <=", value, "volValue");
            return (Criteria) this;
        }

        public Criteria andVolValueIn(List<Integer> values) {
            addCriterion("VOL_VALUE in", values, "volValue");
            return (Criteria) this;
        }

        public Criteria andVolValueNotIn(List<Integer> values) {
            addCriterion("VOL_VALUE not in", values, "volValue");
            return (Criteria) this;
        }

        public Criteria andVolValueBetween(Integer value1, Integer value2) {
            addCriterion("VOL_VALUE between", value1, value2, "volValue");
            return (Criteria) this;
        }

        public Criteria andVolValueNotBetween(Integer value1, Integer value2) {
            addCriterion("VOL_VALUE not between", value1, value2, "volValue");
            return (Criteria) this;
        }

        public Criteria andAmoValueIsNull() {
            addCriterion("AMO_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andAmoValueIsNotNull() {
            addCriterion("AMO_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andAmoValueEqualTo(Integer value) {
            addCriterion("AMO_VALUE =", value, "amoValue");
            return (Criteria) this;
        }

        public Criteria andAmoValueNotEqualTo(Integer value) {
            addCriterion("AMO_VALUE <>", value, "amoValue");
            return (Criteria) this;
        }

        public Criteria andAmoValueGreaterThan(Integer value) {
            addCriterion("AMO_VALUE >", value, "amoValue");
            return (Criteria) this;
        }

        public Criteria andAmoValueGreaterThanOrEqualTo(Integer value) {
            addCriterion("AMO_VALUE >=", value, "amoValue");
            return (Criteria) this;
        }

        public Criteria andAmoValueLessThan(Integer value) {
            addCriterion("AMO_VALUE <", value, "amoValue");
            return (Criteria) this;
        }

        public Criteria andAmoValueLessThanOrEqualTo(Integer value) {
            addCriterion("AMO_VALUE <=", value, "amoValue");
            return (Criteria) this;
        }

        public Criteria andAmoValueIn(List<Integer> values) {
            addCriterion("AMO_VALUE in", values, "amoValue");
            return (Criteria) this;
        }

        public Criteria andAmoValueNotIn(List<Integer> values) {
            addCriterion("AMO_VALUE not in", values, "amoValue");
            return (Criteria) this;
        }

        public Criteria andAmoValueBetween(Integer value1, Integer value2) {
            addCriterion("AMO_VALUE between", value1, value2, "amoValue");
            return (Criteria) this;
        }

        public Criteria andAmoValueNotBetween(Integer value1, Integer value2) {
            addCriterion("AMO_VALUE not between", value1, value2, "amoValue");
            return (Criteria) this;
        }

        public Criteria andCjbsIsNull() {
            addCriterion("CJBS is null");
            return (Criteria) this;
        }

        public Criteria andCjbsIsNotNull() {
            addCriterion("CJBS is not null");
            return (Criteria) this;
        }

        public Criteria andCjbsEqualTo(Integer value) {
            addCriterion("CJBS =", value, "cjbs");
            return (Criteria) this;
        }

        public Criteria andCjbsNotEqualTo(Integer value) {
            addCriterion("CJBS <>", value, "cjbs");
            return (Criteria) this;
        }

        public Criteria andCjbsGreaterThan(Integer value) {
            addCriterion("CJBS >", value, "cjbs");
            return (Criteria) this;
        }

        public Criteria andCjbsGreaterThanOrEqualTo(Integer value) {
            addCriterion("CJBS >=", value, "cjbs");
            return (Criteria) this;
        }

        public Criteria andCjbsLessThan(Integer value) {
            addCriterion("CJBS <", value, "cjbs");
            return (Criteria) this;
        }

        public Criteria andCjbsLessThanOrEqualTo(Integer value) {
            addCriterion("CJBS <=", value, "cjbs");
            return (Criteria) this;
        }

        public Criteria andCjbsIn(List<Integer> values) {
            addCriterion("CJBS in", values, "cjbs");
            return (Criteria) this;
        }

        public Criteria andCjbsNotIn(List<Integer> values) {
            addCriterion("CJBS not in", values, "cjbs");
            return (Criteria) this;
        }

        public Criteria andCjbsBetween(Integer value1, Integer value2) {
            addCriterion("CJBS between", value1, value2, "cjbs");
            return (Criteria) this;
        }

        public Criteria andCjbsNotBetween(Integer value1, Integer value2) {
            addCriterion("CJBS not between", value1, value2, "cjbs");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("PRICE is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(Double value) {
            addCriterion("PRICE =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(Double value) {
            addCriterion("PRICE <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(Double value) {
            addCriterion("PRICE >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("PRICE >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(Double value) {
            addCriterion("PRICE <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(Double value) {
            addCriterion("PRICE <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<Double> values) {
            addCriterion("PRICE in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<Double> values) {
            addCriterion("PRICE not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(Double value1, Double value2) {
            addCriterion("PRICE between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(Double value1, Double value2) {
            addCriterion("PRICE not between", value1, value2, "price");
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