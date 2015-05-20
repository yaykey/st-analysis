package com.st.framework.module.stock.example;

import com.st.framework.module.example.BaseExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CircleChatExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CircleChatExample() {
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

        protected void addCriterionForJDBCTime(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Time(value.getTime()), property);
        }

        protected void addCriterionForJDBCTime(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Time> timeList = new ArrayList<java.sql.Time>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                timeList.add(new java.sql.Time(iter.next().getTime()));
            }
            addCriterion(condition, timeList, property);
        }

        protected void addCriterionForJDBCTime(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Time(value1.getTime()), new java.sql.Time(value2.getTime()), property);
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

        public Criteria andRoomeIdIsNull() {
            addCriterion("ROOME_ID is null");
            return (Criteria) this;
        }

        public Criteria andRoomeIdIsNotNull() {
            addCriterion("ROOME_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRoomeIdEqualTo(Integer value) {
            addCriterion("ROOME_ID =", value, "roomeId");
            return (Criteria) this;
        }

        public Criteria andRoomeIdNotEqualTo(Integer value) {
            addCriterion("ROOME_ID <>", value, "roomeId");
            return (Criteria) this;
        }

        public Criteria andRoomeIdGreaterThan(Integer value) {
            addCriterion("ROOME_ID >", value, "roomeId");
            return (Criteria) this;
        }

        public Criteria andRoomeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ROOME_ID >=", value, "roomeId");
            return (Criteria) this;
        }

        public Criteria andRoomeIdLessThan(Integer value) {
            addCriterion("ROOME_ID <", value, "roomeId");
            return (Criteria) this;
        }

        public Criteria andRoomeIdLessThanOrEqualTo(Integer value) {
            addCriterion("ROOME_ID <=", value, "roomeId");
            return (Criteria) this;
        }

        public Criteria andRoomeIdIn(List<Integer> values) {
            addCriterion("ROOME_ID in", values, "roomeId");
            return (Criteria) this;
        }

        public Criteria andRoomeIdNotIn(List<Integer> values) {
            addCriterion("ROOME_ID not in", values, "roomeId");
            return (Criteria) this;
        }

        public Criteria andRoomeIdBetween(Integer value1, Integer value2) {
            addCriterion("ROOME_ID between", value1, value2, "roomeId");
            return (Criteria) this;
        }

        public Criteria andRoomeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ROOME_ID not between", value1, value2, "roomeId");
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

        public Criteria andDateIdEqualTo(Date value) {
            addCriterionForJDBCDate("DATE_ID =", value, "dateId");
            return (Criteria) this;
        }

        public Criteria andDateIdNotEqualTo(Date value) {
            addCriterionForJDBCDate("DATE_ID <>", value, "dateId");
            return (Criteria) this;
        }

        public Criteria andDateIdGreaterThan(Date value) {
            addCriterionForJDBCDate("DATE_ID >", value, "dateId");
            return (Criteria) this;
        }

        public Criteria andDateIdGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("DATE_ID >=", value, "dateId");
            return (Criteria) this;
        }

        public Criteria andDateIdLessThan(Date value) {
            addCriterionForJDBCDate("DATE_ID <", value, "dateId");
            return (Criteria) this;
        }

        public Criteria andDateIdLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("DATE_ID <=", value, "dateId");
            return (Criteria) this;
        }

        public Criteria andDateIdIn(List<Date> values) {
            addCriterionForJDBCDate("DATE_ID in", values, "dateId");
            return (Criteria) this;
        }

        public Criteria andDateIdNotIn(List<Date> values) {
            addCriterionForJDBCDate("DATE_ID not in", values, "dateId");
            return (Criteria) this;
        }

        public Criteria andDateIdBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("DATE_ID between", value1, value2, "dateId");
            return (Criteria) this;
        }

        public Criteria andDateIdNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("DATE_ID not between", value1, value2, "dateId");
            return (Criteria) this;
        }

        public Criteria andTimeIdIsNull() {
            addCriterion("TIME_ID is null");
            return (Criteria) this;
        }

        public Criteria andTimeIdIsNotNull() {
            addCriterion("TIME_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTimeIdEqualTo(Date value) {
            addCriterionForJDBCTime("TIME_ID =", value, "timeId");
            return (Criteria) this;
        }

        public Criteria andTimeIdNotEqualTo(Date value) {
            addCriterionForJDBCTime("TIME_ID <>", value, "timeId");
            return (Criteria) this;
        }

        public Criteria andTimeIdGreaterThan(Date value) {
            addCriterionForJDBCTime("TIME_ID >", value, "timeId");
            return (Criteria) this;
        }

        public Criteria andTimeIdGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("TIME_ID >=", value, "timeId");
            return (Criteria) this;
        }

        public Criteria andTimeIdLessThan(Date value) {
            addCriterionForJDBCTime("TIME_ID <", value, "timeId");
            return (Criteria) this;
        }

        public Criteria andTimeIdLessThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("TIME_ID <=", value, "timeId");
            return (Criteria) this;
        }

        public Criteria andTimeIdIn(List<Date> values) {
            addCriterionForJDBCTime("TIME_ID in", values, "timeId");
            return (Criteria) this;
        }

        public Criteria andTimeIdNotIn(List<Date> values) {
            addCriterionForJDBCTime("TIME_ID not in", values, "timeId");
            return (Criteria) this;
        }

        public Criteria andTimeIdBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("TIME_ID between", value1, value2, "timeId");
            return (Criteria) this;
        }

        public Criteria andTimeIdNotBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("TIME_ID not between", value1, value2, "timeId");
            return (Criteria) this;
        }

        public Criteria andMd5CodeIsNull() {
            addCriterion("MD5_CODE is null");
            return (Criteria) this;
        }

        public Criteria andMd5CodeIsNotNull() {
            addCriterion("MD5_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andMd5CodeEqualTo(String value) {
            addCriterion("MD5_CODE =", value, "md5Code");
            return (Criteria) this;
        }

        public Criteria andMd5CodeNotEqualTo(String value) {
            addCriterion("MD5_CODE <>", value, "md5Code");
            return (Criteria) this;
        }

        public Criteria andMd5CodeGreaterThan(String value) {
            addCriterion("MD5_CODE >", value, "md5Code");
            return (Criteria) this;
        }

        public Criteria andMd5CodeGreaterThanOrEqualTo(String value) {
            addCriterion("MD5_CODE >=", value, "md5Code");
            return (Criteria) this;
        }

        public Criteria andMd5CodeLessThan(String value) {
            addCriterion("MD5_CODE <", value, "md5Code");
            return (Criteria) this;
        }

        public Criteria andMd5CodeLessThanOrEqualTo(String value) {
            addCriterion("MD5_CODE <=", value, "md5Code");
            return (Criteria) this;
        }

        public Criteria andMd5CodeLike(String value) {
            addCriterion("MD5_CODE like", value, "md5Code");
            return (Criteria) this;
        }

        public Criteria andMd5CodeNotLike(String value) {
            addCriterion("MD5_CODE not like", value, "md5Code");
            return (Criteria) this;
        }

        public Criteria andMd5CodeIn(List<String> values) {
            addCriterion("MD5_CODE in", values, "md5Code");
            return (Criteria) this;
        }

        public Criteria andMd5CodeNotIn(List<String> values) {
            addCriterion("MD5_CODE not in", values, "md5Code");
            return (Criteria) this;
        }

        public Criteria andMd5CodeBetween(String value1, String value2) {
            addCriterion("MD5_CODE between", value1, value2, "md5Code");
            return (Criteria) this;
        }

        public Criteria andMd5CodeNotBetween(String value1, String value2) {
            addCriterion("MD5_CODE not between", value1, value2, "md5Code");
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