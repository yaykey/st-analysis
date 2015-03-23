package com.st.framework.module.stock.example;

import com.st.framework.module.example.BaseExample;
import java.util.ArrayList;
import java.util.List;

public class FactDateAlmanacExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FactDateAlmanacExample() {
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

        public Criteria andDateIsNull() {
            addCriterion("DATE is null");
            return (Criteria) this;
        }

        public Criteria andDateIsNotNull() {
            addCriterion("DATE is not null");
            return (Criteria) this;
        }

        public Criteria andDateEqualTo(String value) {
            addCriterion("DATE =", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotEqualTo(String value) {
            addCriterion("DATE <>", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThan(String value) {
            addCriterion("DATE >", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThanOrEqualTo(String value) {
            addCriterion("DATE >=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThan(String value) {
            addCriterion("DATE <", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThanOrEqualTo(String value) {
            addCriterion("DATE <=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLike(String value) {
            addCriterion("DATE like", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotLike(String value) {
            addCriterion("DATE not like", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateIn(List<String> values) {
            addCriterion("DATE in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotIn(List<String> values) {
            addCriterion("DATE not in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateBetween(String value1, String value2) {
            addCriterion("DATE between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotBetween(String value1, String value2) {
            addCriterion("DATE not between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andSuitIsNull() {
            addCriterion("SUIT is null");
            return (Criteria) this;
        }

        public Criteria andSuitIsNotNull() {
            addCriterion("SUIT is not null");
            return (Criteria) this;
        }

        public Criteria andSuitEqualTo(String value) {
            addCriterion("SUIT =", value, "suit");
            return (Criteria) this;
        }

        public Criteria andSuitNotEqualTo(String value) {
            addCriterion("SUIT <>", value, "suit");
            return (Criteria) this;
        }

        public Criteria andSuitGreaterThan(String value) {
            addCriterion("SUIT >", value, "suit");
            return (Criteria) this;
        }

        public Criteria andSuitGreaterThanOrEqualTo(String value) {
            addCriterion("SUIT >=", value, "suit");
            return (Criteria) this;
        }

        public Criteria andSuitLessThan(String value) {
            addCriterion("SUIT <", value, "suit");
            return (Criteria) this;
        }

        public Criteria andSuitLessThanOrEqualTo(String value) {
            addCriterion("SUIT <=", value, "suit");
            return (Criteria) this;
        }

        public Criteria andSuitLike(String value) {
            addCriterion("SUIT like", value, "suit");
            return (Criteria) this;
        }

        public Criteria andSuitNotLike(String value) {
            addCriterion("SUIT not like", value, "suit");
            return (Criteria) this;
        }

        public Criteria andSuitIn(List<String> values) {
            addCriterion("SUIT in", values, "suit");
            return (Criteria) this;
        }

        public Criteria andSuitNotIn(List<String> values) {
            addCriterion("SUIT not in", values, "suit");
            return (Criteria) this;
        }

        public Criteria andSuitBetween(String value1, String value2) {
            addCriterion("SUIT between", value1, value2, "suit");
            return (Criteria) this;
        }

        public Criteria andSuitNotBetween(String value1, String value2) {
            addCriterion("SUIT not between", value1, value2, "suit");
            return (Criteria) this;
        }

        public Criteria andAvoidIsNull() {
            addCriterion("AVOID is null");
            return (Criteria) this;
        }

        public Criteria andAvoidIsNotNull() {
            addCriterion("AVOID is not null");
            return (Criteria) this;
        }

        public Criteria andAvoidEqualTo(String value) {
            addCriterion("AVOID =", value, "avoid");
            return (Criteria) this;
        }

        public Criteria andAvoidNotEqualTo(String value) {
            addCriterion("AVOID <>", value, "avoid");
            return (Criteria) this;
        }

        public Criteria andAvoidGreaterThan(String value) {
            addCriterion("AVOID >", value, "avoid");
            return (Criteria) this;
        }

        public Criteria andAvoidGreaterThanOrEqualTo(String value) {
            addCriterion("AVOID >=", value, "avoid");
            return (Criteria) this;
        }

        public Criteria andAvoidLessThan(String value) {
            addCriterion("AVOID <", value, "avoid");
            return (Criteria) this;
        }

        public Criteria andAvoidLessThanOrEqualTo(String value) {
            addCriterion("AVOID <=", value, "avoid");
            return (Criteria) this;
        }

        public Criteria andAvoidLike(String value) {
            addCriterion("AVOID like", value, "avoid");
            return (Criteria) this;
        }

        public Criteria andAvoidNotLike(String value) {
            addCriterion("AVOID not like", value, "avoid");
            return (Criteria) this;
        }

        public Criteria andAvoidIn(List<String> values) {
            addCriterion("AVOID in", values, "avoid");
            return (Criteria) this;
        }

        public Criteria andAvoidNotIn(List<String> values) {
            addCriterion("AVOID not in", values, "avoid");
            return (Criteria) this;
        }

        public Criteria andAvoidBetween(String value1, String value2) {
            addCriterion("AVOID between", value1, value2, "avoid");
            return (Criteria) this;
        }

        public Criteria andAvoidNotBetween(String value1, String value2) {
            addCriterion("AVOID not between", value1, value2, "avoid");
            return (Criteria) this;
        }

        public Criteria andWeekIsNull() {
            addCriterion("WEEK is null");
            return (Criteria) this;
        }

        public Criteria andWeekIsNotNull() {
            addCriterion("WEEK is not null");
            return (Criteria) this;
        }

        public Criteria andWeekEqualTo(Integer value) {
            addCriterion("WEEK =", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekNotEqualTo(Integer value) {
            addCriterion("WEEK <>", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekGreaterThan(Integer value) {
            addCriterion("WEEK >", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekGreaterThanOrEqualTo(Integer value) {
            addCriterion("WEEK >=", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekLessThan(Integer value) {
            addCriterion("WEEK <", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekLessThanOrEqualTo(Integer value) {
            addCriterion("WEEK <=", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekIn(List<Integer> values) {
            addCriterion("WEEK in", values, "week");
            return (Criteria) this;
        }

        public Criteria andWeekNotIn(List<Integer> values) {
            addCriterion("WEEK not in", values, "week");
            return (Criteria) this;
        }

        public Criteria andWeekBetween(Integer value1, Integer value2) {
            addCriterion("WEEK between", value1, value2, "week");
            return (Criteria) this;
        }

        public Criteria andWeekNotBetween(Integer value1, Integer value2) {
            addCriterion("WEEK not between", value1, value2, "week");
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