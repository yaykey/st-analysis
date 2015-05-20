package com.st.framework.module.stock.example;

import com.st.framework.module.example.BaseExample;
import java.util.ArrayList;
import java.util.List;

public class PMapExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PMapExample() {
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

        public Criteria andDimtypeIdIsNull() {
            addCriterion("DIMTYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andDimtypeIdIsNotNull() {
            addCriterion("DIMTYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDimtypeIdEqualTo(Integer value) {
            addCriterion("DIMTYPE_ID =", value, "dimtypeId");
            return (Criteria) this;
        }

        public Criteria andDimtypeIdNotEqualTo(Integer value) {
            addCriterion("DIMTYPE_ID <>", value, "dimtypeId");
            return (Criteria) this;
        }

        public Criteria andDimtypeIdGreaterThan(Integer value) {
            addCriterion("DIMTYPE_ID >", value, "dimtypeId");
            return (Criteria) this;
        }

        public Criteria andDimtypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("DIMTYPE_ID >=", value, "dimtypeId");
            return (Criteria) this;
        }

        public Criteria andDimtypeIdLessThan(Integer value) {
            addCriterion("DIMTYPE_ID <", value, "dimtypeId");
            return (Criteria) this;
        }

        public Criteria andDimtypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("DIMTYPE_ID <=", value, "dimtypeId");
            return (Criteria) this;
        }

        public Criteria andDimtypeIdIn(List<Integer> values) {
            addCriterion("DIMTYPE_ID in", values, "dimtypeId");
            return (Criteria) this;
        }

        public Criteria andDimtypeIdNotIn(List<Integer> values) {
            addCriterion("DIMTYPE_ID not in", values, "dimtypeId");
            return (Criteria) this;
        }

        public Criteria andDimtypeIdBetween(Integer value1, Integer value2) {
            addCriterion("DIMTYPE_ID between", value1, value2, "dimtypeId");
            return (Criteria) this;
        }

        public Criteria andDimtypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("DIMTYPE_ID not between", value1, value2, "dimtypeId");
            return (Criteria) this;
        }

        public Criteria andDimIdIsNull() {
            addCriterion("DIM_ID is null");
            return (Criteria) this;
        }

        public Criteria andDimIdIsNotNull() {
            addCriterion("DIM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDimIdEqualTo(Integer value) {
            addCriterion("DIM_ID =", value, "dimId");
            return (Criteria) this;
        }

        public Criteria andDimIdNotEqualTo(Integer value) {
            addCriterion("DIM_ID <>", value, "dimId");
            return (Criteria) this;
        }

        public Criteria andDimIdGreaterThan(Integer value) {
            addCriterion("DIM_ID >", value, "dimId");
            return (Criteria) this;
        }

        public Criteria andDimIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("DIM_ID >=", value, "dimId");
            return (Criteria) this;
        }

        public Criteria andDimIdLessThan(Integer value) {
            addCriterion("DIM_ID <", value, "dimId");
            return (Criteria) this;
        }

        public Criteria andDimIdLessThanOrEqualTo(Integer value) {
            addCriterion("DIM_ID <=", value, "dimId");
            return (Criteria) this;
        }

        public Criteria andDimIdIn(List<Integer> values) {
            addCriterion("DIM_ID in", values, "dimId");
            return (Criteria) this;
        }

        public Criteria andDimIdNotIn(List<Integer> values) {
            addCriterion("DIM_ID not in", values, "dimId");
            return (Criteria) this;
        }

        public Criteria andDimIdBetween(Integer value1, Integer value2) {
            addCriterion("DIM_ID between", value1, value2, "dimId");
            return (Criteria) this;
        }

        public Criteria andDimIdNotBetween(Integer value1, Integer value2) {
            addCriterion("DIM_ID not between", value1, value2, "dimId");
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