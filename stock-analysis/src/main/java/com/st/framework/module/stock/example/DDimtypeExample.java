package com.st.framework.module.stock.example;

import com.st.framework.module.example.BaseExample;
import java.util.ArrayList;
import java.util.List;

public class DDimtypeExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DDimtypeExample() {
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

        public Criteria andDimtypeNameIsNull() {
            addCriterion("DIMTYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDimtypeNameIsNotNull() {
            addCriterion("DIMTYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDimtypeNameEqualTo(String value) {
            addCriterion("DIMTYPE_NAME =", value, "dimtypeName");
            return (Criteria) this;
        }

        public Criteria andDimtypeNameNotEqualTo(String value) {
            addCriterion("DIMTYPE_NAME <>", value, "dimtypeName");
            return (Criteria) this;
        }

        public Criteria andDimtypeNameGreaterThan(String value) {
            addCriterion("DIMTYPE_NAME >", value, "dimtypeName");
            return (Criteria) this;
        }

        public Criteria andDimtypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("DIMTYPE_NAME >=", value, "dimtypeName");
            return (Criteria) this;
        }

        public Criteria andDimtypeNameLessThan(String value) {
            addCriterion("DIMTYPE_NAME <", value, "dimtypeName");
            return (Criteria) this;
        }

        public Criteria andDimtypeNameLessThanOrEqualTo(String value) {
            addCriterion("DIMTYPE_NAME <=", value, "dimtypeName");
            return (Criteria) this;
        }

        public Criteria andDimtypeNameLike(String value) {
            addCriterion("DIMTYPE_NAME like", value, "dimtypeName");
            return (Criteria) this;
        }

        public Criteria andDimtypeNameNotLike(String value) {
            addCriterion("DIMTYPE_NAME not like", value, "dimtypeName");
            return (Criteria) this;
        }

        public Criteria andDimtypeNameIn(List<String> values) {
            addCriterion("DIMTYPE_NAME in", values, "dimtypeName");
            return (Criteria) this;
        }

        public Criteria andDimtypeNameNotIn(List<String> values) {
            addCriterion("DIMTYPE_NAME not in", values, "dimtypeName");
            return (Criteria) this;
        }

        public Criteria andDimtypeNameBetween(String value1, String value2) {
            addCriterion("DIMTYPE_NAME between", value1, value2, "dimtypeName");
            return (Criteria) this;
        }

        public Criteria andDimtypeNameNotBetween(String value1, String value2) {
            addCriterion("DIMTYPE_NAME not between", value1, value2, "dimtypeName");
            return (Criteria) this;
        }

        public Criteria andDimtypeCodeIsNull() {
            addCriterion("DIMTYPE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andDimtypeCodeIsNotNull() {
            addCriterion("DIMTYPE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andDimtypeCodeEqualTo(String value) {
            addCriterion("DIMTYPE_CODE =", value, "dimtypeCode");
            return (Criteria) this;
        }

        public Criteria andDimtypeCodeNotEqualTo(String value) {
            addCriterion("DIMTYPE_CODE <>", value, "dimtypeCode");
            return (Criteria) this;
        }

        public Criteria andDimtypeCodeGreaterThan(String value) {
            addCriterion("DIMTYPE_CODE >", value, "dimtypeCode");
            return (Criteria) this;
        }

        public Criteria andDimtypeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("DIMTYPE_CODE >=", value, "dimtypeCode");
            return (Criteria) this;
        }

        public Criteria andDimtypeCodeLessThan(String value) {
            addCriterion("DIMTYPE_CODE <", value, "dimtypeCode");
            return (Criteria) this;
        }

        public Criteria andDimtypeCodeLessThanOrEqualTo(String value) {
            addCriterion("DIMTYPE_CODE <=", value, "dimtypeCode");
            return (Criteria) this;
        }

        public Criteria andDimtypeCodeLike(String value) {
            addCriterion("DIMTYPE_CODE like", value, "dimtypeCode");
            return (Criteria) this;
        }

        public Criteria andDimtypeCodeNotLike(String value) {
            addCriterion("DIMTYPE_CODE not like", value, "dimtypeCode");
            return (Criteria) this;
        }

        public Criteria andDimtypeCodeIn(List<String> values) {
            addCriterion("DIMTYPE_CODE in", values, "dimtypeCode");
            return (Criteria) this;
        }

        public Criteria andDimtypeCodeNotIn(List<String> values) {
            addCriterion("DIMTYPE_CODE not in", values, "dimtypeCode");
            return (Criteria) this;
        }

        public Criteria andDimtypeCodeBetween(String value1, String value2) {
            addCriterion("DIMTYPE_CODE between", value1, value2, "dimtypeCode");
            return (Criteria) this;
        }

        public Criteria andDimtypeCodeNotBetween(String value1, String value2) {
            addCriterion("DIMTYPE_CODE not between", value1, value2, "dimtypeCode");
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