package com.st.framework.module.stock.example;

import com.st.framework.module.example.BaseExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DDimExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DDimExample() {
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

        public Criteria andDimCodeIsNull() {
            addCriterion("DIM_CODE is null");
            return (Criteria) this;
        }

        public Criteria andDimCodeIsNotNull() {
            addCriterion("DIM_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andDimCodeEqualTo(String value) {
            addCriterion("DIM_CODE =", value, "dimCode");
            return (Criteria) this;
        }

        public Criteria andDimCodeNotEqualTo(String value) {
            addCriterion("DIM_CODE <>", value, "dimCode");
            return (Criteria) this;
        }

        public Criteria andDimCodeGreaterThan(String value) {
            addCriterion("DIM_CODE >", value, "dimCode");
            return (Criteria) this;
        }

        public Criteria andDimCodeGreaterThanOrEqualTo(String value) {
            addCriterion("DIM_CODE >=", value, "dimCode");
            return (Criteria) this;
        }

        public Criteria andDimCodeLessThan(String value) {
            addCriterion("DIM_CODE <", value, "dimCode");
            return (Criteria) this;
        }

        public Criteria andDimCodeLessThanOrEqualTo(String value) {
            addCriterion("DIM_CODE <=", value, "dimCode");
            return (Criteria) this;
        }

        public Criteria andDimCodeLike(String value) {
            addCriterion("DIM_CODE like", value, "dimCode");
            return (Criteria) this;
        }

        public Criteria andDimCodeNotLike(String value) {
            addCriterion("DIM_CODE not like", value, "dimCode");
            return (Criteria) this;
        }

        public Criteria andDimCodeIn(List<String> values) {
            addCriterion("DIM_CODE in", values, "dimCode");
            return (Criteria) this;
        }

        public Criteria andDimCodeNotIn(List<String> values) {
            addCriterion("DIM_CODE not in", values, "dimCode");
            return (Criteria) this;
        }

        public Criteria andDimCodeBetween(String value1, String value2) {
            addCriterion("DIM_CODE between", value1, value2, "dimCode");
            return (Criteria) this;
        }

        public Criteria andDimCodeNotBetween(String value1, String value2) {
            addCriterion("DIM_CODE not between", value1, value2, "dimCode");
            return (Criteria) this;
        }

        public Criteria andDimNameIsNull() {
            addCriterion("DIM_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDimNameIsNotNull() {
            addCriterion("DIM_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDimNameEqualTo(String value) {
            addCriterion("DIM_NAME =", value, "dimName");
            return (Criteria) this;
        }

        public Criteria andDimNameNotEqualTo(String value) {
            addCriterion("DIM_NAME <>", value, "dimName");
            return (Criteria) this;
        }

        public Criteria andDimNameGreaterThan(String value) {
            addCriterion("DIM_NAME >", value, "dimName");
            return (Criteria) this;
        }

        public Criteria andDimNameGreaterThanOrEqualTo(String value) {
            addCriterion("DIM_NAME >=", value, "dimName");
            return (Criteria) this;
        }

        public Criteria andDimNameLessThan(String value) {
            addCriterion("DIM_NAME <", value, "dimName");
            return (Criteria) this;
        }

        public Criteria andDimNameLessThanOrEqualTo(String value) {
            addCriterion("DIM_NAME <=", value, "dimName");
            return (Criteria) this;
        }

        public Criteria andDimNameLike(String value) {
            addCriterion("DIM_NAME like", value, "dimName");
            return (Criteria) this;
        }

        public Criteria andDimNameNotLike(String value) {
            addCriterion("DIM_NAME not like", value, "dimName");
            return (Criteria) this;
        }

        public Criteria andDimNameIn(List<String> values) {
            addCriterion("DIM_NAME in", values, "dimName");
            return (Criteria) this;
        }

        public Criteria andDimNameNotIn(List<String> values) {
            addCriterion("DIM_NAME not in", values, "dimName");
            return (Criteria) this;
        }

        public Criteria andDimNameBetween(String value1, String value2) {
            addCriterion("DIM_NAME between", value1, value2, "dimName");
            return (Criteria) this;
        }

        public Criteria andDimNameNotBetween(String value1, String value2) {
            addCriterion("DIM_NAME not between", value1, value2, "dimName");
            return (Criteria) this;
        }

        public Criteria andUpdDateIsNull() {
            addCriterion("UPD_DATE is null");
            return (Criteria) this;
        }

        public Criteria andUpdDateIsNotNull() {
            addCriterion("UPD_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andUpdDateEqualTo(Date value) {
            addCriterion("UPD_DATE =", value, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateNotEqualTo(Date value) {
            addCriterion("UPD_DATE <>", value, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateGreaterThan(Date value) {
            addCriterion("UPD_DATE >", value, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateGreaterThanOrEqualTo(Date value) {
            addCriterion("UPD_DATE >=", value, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateLessThan(Date value) {
            addCriterion("UPD_DATE <", value, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateLessThanOrEqualTo(Date value) {
            addCriterion("UPD_DATE <=", value, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateIn(List<Date> values) {
            addCriterion("UPD_DATE in", values, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateNotIn(List<Date> values) {
            addCriterion("UPD_DATE not in", values, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateBetween(Date value1, Date value2) {
            addCriterion("UPD_DATE between", value1, value2, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateNotBetween(Date value1, Date value2) {
            addCriterion("UPD_DATE not between", value1, value2, "updDate");
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