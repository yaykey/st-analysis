package com.st.framework.module.stock.example;

import com.st.framework.module.example.BaseExample;
import java.util.ArrayList;
import java.util.List;

public class DimCountryExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DimCountryExample() {
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

        public Criteria andAb2IsNull() {
            addCriterion("AB2 is null");
            return (Criteria) this;
        }

        public Criteria andAb2IsNotNull() {
            addCriterion("AB2 is not null");
            return (Criteria) this;
        }

        public Criteria andAb2EqualTo(String value) {
            addCriterion("AB2 =", value, "ab2");
            return (Criteria) this;
        }

        public Criteria andAb2NotEqualTo(String value) {
            addCriterion("AB2 <>", value, "ab2");
            return (Criteria) this;
        }

        public Criteria andAb2GreaterThan(String value) {
            addCriterion("AB2 >", value, "ab2");
            return (Criteria) this;
        }

        public Criteria andAb2GreaterThanOrEqualTo(String value) {
            addCriterion("AB2 >=", value, "ab2");
            return (Criteria) this;
        }

        public Criteria andAb2LessThan(String value) {
            addCriterion("AB2 <", value, "ab2");
            return (Criteria) this;
        }

        public Criteria andAb2LessThanOrEqualTo(String value) {
            addCriterion("AB2 <=", value, "ab2");
            return (Criteria) this;
        }

        public Criteria andAb2Like(String value) {
            addCriterion("AB2 like", value, "ab2");
            return (Criteria) this;
        }

        public Criteria andAb2NotLike(String value) {
            addCriterion("AB2 not like", value, "ab2");
            return (Criteria) this;
        }

        public Criteria andAb2In(List<String> values) {
            addCriterion("AB2 in", values, "ab2");
            return (Criteria) this;
        }

        public Criteria andAb2NotIn(List<String> values) {
            addCriterion("AB2 not in", values, "ab2");
            return (Criteria) this;
        }

        public Criteria andAb2Between(String value1, String value2) {
            addCriterion("AB2 between", value1, value2, "ab2");
            return (Criteria) this;
        }

        public Criteria andAb2NotBetween(String value1, String value2) {
            addCriterion("AB2 not between", value1, value2, "ab2");
            return (Criteria) this;
        }

        public Criteria andAb3IsNull() {
            addCriterion("AB3 is null");
            return (Criteria) this;
        }

        public Criteria andAb3IsNotNull() {
            addCriterion("AB3 is not null");
            return (Criteria) this;
        }

        public Criteria andAb3EqualTo(String value) {
            addCriterion("AB3 =", value, "ab3");
            return (Criteria) this;
        }

        public Criteria andAb3NotEqualTo(String value) {
            addCriterion("AB3 <>", value, "ab3");
            return (Criteria) this;
        }

        public Criteria andAb3GreaterThan(String value) {
            addCriterion("AB3 >", value, "ab3");
            return (Criteria) this;
        }

        public Criteria andAb3GreaterThanOrEqualTo(String value) {
            addCriterion("AB3 >=", value, "ab3");
            return (Criteria) this;
        }

        public Criteria andAb3LessThan(String value) {
            addCriterion("AB3 <", value, "ab3");
            return (Criteria) this;
        }

        public Criteria andAb3LessThanOrEqualTo(String value) {
            addCriterion("AB3 <=", value, "ab3");
            return (Criteria) this;
        }

        public Criteria andAb3Like(String value) {
            addCriterion("AB3 like", value, "ab3");
            return (Criteria) this;
        }

        public Criteria andAb3NotLike(String value) {
            addCriterion("AB3 not like", value, "ab3");
            return (Criteria) this;
        }

        public Criteria andAb3In(List<String> values) {
            addCriterion("AB3 in", values, "ab3");
            return (Criteria) this;
        }

        public Criteria andAb3NotIn(List<String> values) {
            addCriterion("AB3 not in", values, "ab3");
            return (Criteria) this;
        }

        public Criteria andAb3Between(String value1, String value2) {
            addCriterion("AB3 between", value1, value2, "ab3");
            return (Criteria) this;
        }

        public Criteria andAb3NotBetween(String value1, String value2) {
            addCriterion("AB3 not between", value1, value2, "ab3");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("CODE is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(Integer value) {
            addCriterion("CODE =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(Integer value) {
            addCriterion("CODE <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(Integer value) {
            addCriterion("CODE >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("CODE >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(Integer value) {
            addCriterion("CODE <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(Integer value) {
            addCriterion("CODE <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<Integer> values) {
            addCriterion("CODE in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<Integer> values) {
            addCriterion("CODE not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(Integer value1, Integer value2) {
            addCriterion("CODE between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("CODE not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andAbIsNull() {
            addCriterion("AB is null");
            return (Criteria) this;
        }

        public Criteria andAbIsNotNull() {
            addCriterion("AB is not null");
            return (Criteria) this;
        }

        public Criteria andAbEqualTo(String value) {
            addCriterion("AB =", value, "ab");
            return (Criteria) this;
        }

        public Criteria andAbNotEqualTo(String value) {
            addCriterion("AB <>", value, "ab");
            return (Criteria) this;
        }

        public Criteria andAbGreaterThan(String value) {
            addCriterion("AB >", value, "ab");
            return (Criteria) this;
        }

        public Criteria andAbGreaterThanOrEqualTo(String value) {
            addCriterion("AB >=", value, "ab");
            return (Criteria) this;
        }

        public Criteria andAbLessThan(String value) {
            addCriterion("AB <", value, "ab");
            return (Criteria) this;
        }

        public Criteria andAbLessThanOrEqualTo(String value) {
            addCriterion("AB <=", value, "ab");
            return (Criteria) this;
        }

        public Criteria andAbLike(String value) {
            addCriterion("AB like", value, "ab");
            return (Criteria) this;
        }

        public Criteria andAbNotLike(String value) {
            addCriterion("AB not like", value, "ab");
            return (Criteria) this;
        }

        public Criteria andAbIn(List<String> values) {
            addCriterion("AB in", values, "ab");
            return (Criteria) this;
        }

        public Criteria andAbNotIn(List<String> values) {
            addCriterion("AB not in", values, "ab");
            return (Criteria) this;
        }

        public Criteria andAbBetween(String value1, String value2) {
            addCriterion("AB between", value1, value2, "ab");
            return (Criteria) this;
        }

        public Criteria andAbNotBetween(String value1, String value2) {
            addCriterion("AB not between", value1, value2, "ab");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("NAME is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("NAME =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("NAME <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("NAME >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("NAME >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("NAME <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("NAME <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("NAME like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("NAME not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("NAME in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("NAME not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("NAME between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("NAME not between", value1, value2, "name");
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