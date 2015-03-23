package com.st.framework.module.stock.example;

import com.st.framework.module.example.BaseExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FactSinaParamMapExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FactSinaParamMapExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSIIsNull() {
            addCriterion("s_i is null");
            return (Criteria) this;
        }

        public Criteria andSIIsNotNull() {
            addCriterion("s_i is not null");
            return (Criteria) this;
        }

        public Criteria andSIEqualTo(String value) {
            addCriterion("s_i =", value, "sI");
            return (Criteria) this;
        }

        public Criteria andSINotEqualTo(String value) {
            addCriterion("s_i <>", value, "sI");
            return (Criteria) this;
        }

        public Criteria andSIGreaterThan(String value) {
            addCriterion("s_i >", value, "sI");
            return (Criteria) this;
        }

        public Criteria andSIGreaterThanOrEqualTo(String value) {
            addCriterion("s_i >=", value, "sI");
            return (Criteria) this;
        }

        public Criteria andSILessThan(String value) {
            addCriterion("s_i <", value, "sI");
            return (Criteria) this;
        }

        public Criteria andSILessThanOrEqualTo(String value) {
            addCriterion("s_i <=", value, "sI");
            return (Criteria) this;
        }

        public Criteria andSILike(String value) {
            addCriterion("s_i like", value, "sI");
            return (Criteria) this;
        }

        public Criteria andSINotLike(String value) {
            addCriterion("s_i not like", value, "sI");
            return (Criteria) this;
        }

        public Criteria andSIIn(List<String> values) {
            addCriterion("s_i in", values, "sI");
            return (Criteria) this;
        }

        public Criteria andSINotIn(List<String> values) {
            addCriterion("s_i not in", values, "sI");
            return (Criteria) this;
        }

        public Criteria andSIBetween(String value1, String value2) {
            addCriterion("s_i between", value1, value2, "sI");
            return (Criteria) this;
        }

        public Criteria andSINotBetween(String value1, String value2) {
            addCriterion("s_i not between", value1, value2, "sI");
            return (Criteria) this;
        }

        public Criteria andSAIsNull() {
            addCriterion("s_a is null");
            return (Criteria) this;
        }

        public Criteria andSAIsNotNull() {
            addCriterion("s_a is not null");
            return (Criteria) this;
        }

        public Criteria andSAEqualTo(String value) {
            addCriterion("s_a =", value, "sA");
            return (Criteria) this;
        }

        public Criteria andSANotEqualTo(String value) {
            addCriterion("s_a <>", value, "sA");
            return (Criteria) this;
        }

        public Criteria andSAGreaterThan(String value) {
            addCriterion("s_a >", value, "sA");
            return (Criteria) this;
        }

        public Criteria andSAGreaterThanOrEqualTo(String value) {
            addCriterion("s_a >=", value, "sA");
            return (Criteria) this;
        }

        public Criteria andSALessThan(String value) {
            addCriterion("s_a <", value, "sA");
            return (Criteria) this;
        }

        public Criteria andSALessThanOrEqualTo(String value) {
            addCriterion("s_a <=", value, "sA");
            return (Criteria) this;
        }

        public Criteria andSALike(String value) {
            addCriterion("s_a like", value, "sA");
            return (Criteria) this;
        }

        public Criteria andSANotLike(String value) {
            addCriterion("s_a not like", value, "sA");
            return (Criteria) this;
        }

        public Criteria andSAIn(List<String> values) {
            addCriterion("s_a in", values, "sA");
            return (Criteria) this;
        }

        public Criteria andSANotIn(List<String> values) {
            addCriterion("s_a not in", values, "sA");
            return (Criteria) this;
        }

        public Criteria andSABetween(String value1, String value2) {
            addCriterion("s_a between", value1, value2, "sA");
            return (Criteria) this;
        }

        public Criteria andSANotBetween(String value1, String value2) {
            addCriterion("s_a not between", value1, value2, "sA");
            return (Criteria) this;
        }

        public Criteria andSCIsNull() {
            addCriterion("s_c is null");
            return (Criteria) this;
        }

        public Criteria andSCIsNotNull() {
            addCriterion("s_c is not null");
            return (Criteria) this;
        }

        public Criteria andSCEqualTo(String value) {
            addCriterion("s_c =", value, "sC");
            return (Criteria) this;
        }

        public Criteria andSCNotEqualTo(String value) {
            addCriterion("s_c <>", value, "sC");
            return (Criteria) this;
        }

        public Criteria andSCGreaterThan(String value) {
            addCriterion("s_c >", value, "sC");
            return (Criteria) this;
        }

        public Criteria andSCGreaterThanOrEqualTo(String value) {
            addCriterion("s_c >=", value, "sC");
            return (Criteria) this;
        }

        public Criteria andSCLessThan(String value) {
            addCriterion("s_c <", value, "sC");
            return (Criteria) this;
        }

        public Criteria andSCLessThanOrEqualTo(String value) {
            addCriterion("s_c <=", value, "sC");
            return (Criteria) this;
        }

        public Criteria andSCLike(String value) {
            addCriterion("s_c like", value, "sC");
            return (Criteria) this;
        }

        public Criteria andSCNotLike(String value) {
            addCriterion("s_c not like", value, "sC");
            return (Criteria) this;
        }

        public Criteria andSCIn(List<String> values) {
            addCriterion("s_c in", values, "sC");
            return (Criteria) this;
        }

        public Criteria andSCNotIn(List<String> values) {
            addCriterion("s_c not in", values, "sC");
            return (Criteria) this;
        }

        public Criteria andSCBetween(String value1, String value2) {
            addCriterion("s_c between", value1, value2, "sC");
            return (Criteria) this;
        }

        public Criteria andSCNotBetween(String value1, String value2) {
            addCriterion("s_c not between", value1, value2, "sC");
            return (Criteria) this;
        }

        public Criteria andSTIsNull() {
            addCriterion("s_t is null");
            return (Criteria) this;
        }

        public Criteria andSTIsNotNull() {
            addCriterion("s_t is not null");
            return (Criteria) this;
        }

        public Criteria andSTEqualTo(String value) {
            addCriterion("s_t =", value, "sT");
            return (Criteria) this;
        }

        public Criteria andSTNotEqualTo(String value) {
            addCriterion("s_t <>", value, "sT");
            return (Criteria) this;
        }

        public Criteria andSTGreaterThan(String value) {
            addCriterion("s_t >", value, "sT");
            return (Criteria) this;
        }

        public Criteria andSTGreaterThanOrEqualTo(String value) {
            addCriterion("s_t >=", value, "sT");
            return (Criteria) this;
        }

        public Criteria andSTLessThan(String value) {
            addCriterion("s_t <", value, "sT");
            return (Criteria) this;
        }

        public Criteria andSTLessThanOrEqualTo(String value) {
            addCriterion("s_t <=", value, "sT");
            return (Criteria) this;
        }

        public Criteria andSTLike(String value) {
            addCriterion("s_t like", value, "sT");
            return (Criteria) this;
        }

        public Criteria andSTNotLike(String value) {
            addCriterion("s_t not like", value, "sT");
            return (Criteria) this;
        }

        public Criteria andSTIn(List<String> values) {
            addCriterion("s_t in", values, "sT");
            return (Criteria) this;
        }

        public Criteria andSTNotIn(List<String> values) {
            addCriterion("s_t not in", values, "sT");
            return (Criteria) this;
        }

        public Criteria andSTBetween(String value1, String value2) {
            addCriterion("s_t between", value1, value2, "sT");
            return (Criteria) this;
        }

        public Criteria andSTNotBetween(String value1, String value2) {
            addCriterion("s_t not between", value1, value2, "sT");
            return (Criteria) this;
        }

        public Criteria andPIsNull() {
            addCriterion("p is null");
            return (Criteria) this;
        }

        public Criteria andPIsNotNull() {
            addCriterion("p is not null");
            return (Criteria) this;
        }

        public Criteria andPEqualTo(Integer value) {
            addCriterion("p =", value, "p");
            return (Criteria) this;
        }

        public Criteria andPNotEqualTo(Integer value) {
            addCriterion("p <>", value, "p");
            return (Criteria) this;
        }

        public Criteria andPGreaterThan(Integer value) {
            addCriterion("p >", value, "p");
            return (Criteria) this;
        }

        public Criteria andPGreaterThanOrEqualTo(Integer value) {
            addCriterion("p >=", value, "p");
            return (Criteria) this;
        }

        public Criteria andPLessThan(Integer value) {
            addCriterion("p <", value, "p");
            return (Criteria) this;
        }

        public Criteria andPLessThanOrEqualTo(Integer value) {
            addCriterion("p <=", value, "p");
            return (Criteria) this;
        }

        public Criteria andPIn(List<Integer> values) {
            addCriterion("p in", values, "p");
            return (Criteria) this;
        }

        public Criteria andPNotIn(List<Integer> values) {
            addCriterion("p not in", values, "p");
            return (Criteria) this;
        }

        public Criteria andPBetween(Integer value1, Integer value2) {
            addCriterion("p between", value1, value2, "p");
            return (Criteria) this;
        }

        public Criteria andPNotBetween(Integer value1, Integer value2) {
            addCriterion("p not between", value1, value2, "p");
            return (Criteria) this;
        }

        public Criteria andIsValidityIsNull() {
            addCriterion("IS_VALIDITY is null");
            return (Criteria) this;
        }

        public Criteria andIsValidityIsNotNull() {
            addCriterion("IS_VALIDITY is not null");
            return (Criteria) this;
        }

        public Criteria andIsValidityEqualTo(Boolean value) {
            addCriterion("IS_VALIDITY =", value, "isValidity");
            return (Criteria) this;
        }

        public Criteria andIsValidityNotEqualTo(Boolean value) {
            addCriterion("IS_VALIDITY <>", value, "isValidity");
            return (Criteria) this;
        }

        public Criteria andIsValidityGreaterThan(Boolean value) {
            addCriterion("IS_VALIDITY >", value, "isValidity");
            return (Criteria) this;
        }

        public Criteria andIsValidityGreaterThanOrEqualTo(Boolean value) {
            addCriterion("IS_VALIDITY >=", value, "isValidity");
            return (Criteria) this;
        }

        public Criteria andIsValidityLessThan(Boolean value) {
            addCriterion("IS_VALIDITY <", value, "isValidity");
            return (Criteria) this;
        }

        public Criteria andIsValidityLessThanOrEqualTo(Boolean value) {
            addCriterion("IS_VALIDITY <=", value, "isValidity");
            return (Criteria) this;
        }

        public Criteria andIsValidityIn(List<Boolean> values) {
            addCriterion("IS_VALIDITY in", values, "isValidity");
            return (Criteria) this;
        }

        public Criteria andIsValidityNotIn(List<Boolean> values) {
            addCriterion("IS_VALIDITY not in", values, "isValidity");
            return (Criteria) this;
        }

        public Criteria andIsValidityBetween(Boolean value1, Boolean value2) {
            addCriterion("IS_VALIDITY between", value1, value2, "isValidity");
            return (Criteria) this;
        }

        public Criteria andIsValidityNotBetween(Boolean value1, Boolean value2) {
            addCriterion("IS_VALIDITY not between", value1, value2, "isValidity");
            return (Criteria) this;
        }

        public Criteria andIsUseIsNull() {
            addCriterion("IS_USE is null");
            return (Criteria) this;
        }

        public Criteria andIsUseIsNotNull() {
            addCriterion("IS_USE is not null");
            return (Criteria) this;
        }

        public Criteria andIsUseEqualTo(Boolean value) {
            addCriterion("IS_USE =", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseNotEqualTo(Boolean value) {
            addCriterion("IS_USE <>", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseGreaterThan(Boolean value) {
            addCriterion("IS_USE >", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseGreaterThanOrEqualTo(Boolean value) {
            addCriterion("IS_USE >=", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseLessThan(Boolean value) {
            addCriterion("IS_USE <", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseLessThanOrEqualTo(Boolean value) {
            addCriterion("IS_USE <=", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseIn(List<Boolean> values) {
            addCriterion("IS_USE in", values, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseNotIn(List<Boolean> values) {
            addCriterion("IS_USE not in", values, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseBetween(Boolean value1, Boolean value2) {
            addCriterion("IS_USE between", value1, value2, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseNotBetween(Boolean value1, Boolean value2) {
            addCriterion("IS_USE not between", value1, value2, "isUse");
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