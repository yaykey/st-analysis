package com.st.framework.module.stock.example;

import com.st.framework.module.example.BaseExample;
import java.util.ArrayList;
import java.util.List;

public class FactDateHolidayExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FactDateHolidayExample() {
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

        public Criteria andFestivalIsNull() {
            addCriterion("FESTIVAL is null");
            return (Criteria) this;
        }

        public Criteria andFestivalIsNotNull() {
            addCriterion("FESTIVAL is not null");
            return (Criteria) this;
        }

        public Criteria andFestivalEqualTo(String value) {
            addCriterion("FESTIVAL =", value, "festival");
            return (Criteria) this;
        }

        public Criteria andFestivalNotEqualTo(String value) {
            addCriterion("FESTIVAL <>", value, "festival");
            return (Criteria) this;
        }

        public Criteria andFestivalGreaterThan(String value) {
            addCriterion("FESTIVAL >", value, "festival");
            return (Criteria) this;
        }

        public Criteria andFestivalGreaterThanOrEqualTo(String value) {
            addCriterion("FESTIVAL >=", value, "festival");
            return (Criteria) this;
        }

        public Criteria andFestivalLessThan(String value) {
            addCriterion("FESTIVAL <", value, "festival");
            return (Criteria) this;
        }

        public Criteria andFestivalLessThanOrEqualTo(String value) {
            addCriterion("FESTIVAL <=", value, "festival");
            return (Criteria) this;
        }

        public Criteria andFestivalLike(String value) {
            addCriterion("FESTIVAL like", value, "festival");
            return (Criteria) this;
        }

        public Criteria andFestivalNotLike(String value) {
            addCriterion("FESTIVAL not like", value, "festival");
            return (Criteria) this;
        }

        public Criteria andFestivalIn(List<String> values) {
            addCriterion("FESTIVAL in", values, "festival");
            return (Criteria) this;
        }

        public Criteria andFestivalNotIn(List<String> values) {
            addCriterion("FESTIVAL not in", values, "festival");
            return (Criteria) this;
        }

        public Criteria andFestivalBetween(String value1, String value2) {
            addCriterion("FESTIVAL between", value1, value2, "festival");
            return (Criteria) this;
        }

        public Criteria andFestivalNotBetween(String value1, String value2) {
            addCriterion("FESTIVAL not between", value1, value2, "festival");
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

        public Criteria andDescriptionIsNull() {
            addCriterion("DESCRIPTION is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("DESCRIPTION is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("DESCRIPTION =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("DESCRIPTION <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("DESCRIPTION >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("DESCRIPTION >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("DESCRIPTION <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("DESCRIPTION <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("DESCRIPTION like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("DESCRIPTION not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("DESCRIPTION in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("DESCRIPTION not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("DESCRIPTION between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("DESCRIPTION not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andRestIsNull() {
            addCriterion("REST is null");
            return (Criteria) this;
        }

        public Criteria andRestIsNotNull() {
            addCriterion("REST is not null");
            return (Criteria) this;
        }

        public Criteria andRestEqualTo(String value) {
            addCriterion("REST =", value, "rest");
            return (Criteria) this;
        }

        public Criteria andRestNotEqualTo(String value) {
            addCriterion("REST <>", value, "rest");
            return (Criteria) this;
        }

        public Criteria andRestGreaterThan(String value) {
            addCriterion("REST >", value, "rest");
            return (Criteria) this;
        }

        public Criteria andRestGreaterThanOrEqualTo(String value) {
            addCriterion("REST >=", value, "rest");
            return (Criteria) this;
        }

        public Criteria andRestLessThan(String value) {
            addCriterion("REST <", value, "rest");
            return (Criteria) this;
        }

        public Criteria andRestLessThanOrEqualTo(String value) {
            addCriterion("REST <=", value, "rest");
            return (Criteria) this;
        }

        public Criteria andRestLike(String value) {
            addCriterion("REST like", value, "rest");
            return (Criteria) this;
        }

        public Criteria andRestNotLike(String value) {
            addCriterion("REST not like", value, "rest");
            return (Criteria) this;
        }

        public Criteria andRestIn(List<String> values) {
            addCriterion("REST in", values, "rest");
            return (Criteria) this;
        }

        public Criteria andRestNotIn(List<String> values) {
            addCriterion("REST not in", values, "rest");
            return (Criteria) this;
        }

        public Criteria andRestBetween(String value1, String value2) {
            addCriterion("REST between", value1, value2, "rest");
            return (Criteria) this;
        }

        public Criteria andRestNotBetween(String value1, String value2) {
            addCriterion("REST not between", value1, value2, "rest");
            return (Criteria) this;
        }

        public Criteria andListnumbaiduIsNull() {
            addCriterion("LISTNUMBAIDU is null");
            return (Criteria) this;
        }

        public Criteria andListnumbaiduIsNotNull() {
            addCriterion("LISTNUMBAIDU is not null");
            return (Criteria) this;
        }

        public Criteria andListnumbaiduEqualTo(Integer value) {
            addCriterion("LISTNUMBAIDU =", value, "listnumbaidu");
            return (Criteria) this;
        }

        public Criteria andListnumbaiduNotEqualTo(Integer value) {
            addCriterion("LISTNUMBAIDU <>", value, "listnumbaidu");
            return (Criteria) this;
        }

        public Criteria andListnumbaiduGreaterThan(Integer value) {
            addCriterion("LISTNUMBAIDU >", value, "listnumbaidu");
            return (Criteria) this;
        }

        public Criteria andListnumbaiduGreaterThanOrEqualTo(Integer value) {
            addCriterion("LISTNUMBAIDU >=", value, "listnumbaidu");
            return (Criteria) this;
        }

        public Criteria andListnumbaiduLessThan(Integer value) {
            addCriterion("LISTNUMBAIDU <", value, "listnumbaidu");
            return (Criteria) this;
        }

        public Criteria andListnumbaiduLessThanOrEqualTo(Integer value) {
            addCriterion("LISTNUMBAIDU <=", value, "listnumbaidu");
            return (Criteria) this;
        }

        public Criteria andListnumbaiduIn(List<Integer> values) {
            addCriterion("LISTNUMBAIDU in", values, "listnumbaidu");
            return (Criteria) this;
        }

        public Criteria andListnumbaiduNotIn(List<Integer> values) {
            addCriterion("LISTNUMBAIDU not in", values, "listnumbaidu");
            return (Criteria) this;
        }

        public Criteria andListnumbaiduBetween(Integer value1, Integer value2) {
            addCriterion("LISTNUMBAIDU between", value1, value2, "listnumbaidu");
            return (Criteria) this;
        }

        public Criteria andListnumbaiduNotBetween(Integer value1, Integer value2) {
            addCriterion("LISTNUMBAIDU not between", value1, value2, "listnumbaidu");
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