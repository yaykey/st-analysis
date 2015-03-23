package com.st.framework.module.stock.example;

import com.st.framework.module.example.BaseExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FactDownloadFileConfigExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FactDownloadFileConfigExample() {
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

        public Criteria andStCodeIsNull() {
            addCriterion("ST_CODE is null");
            return (Criteria) this;
        }

        public Criteria andStCodeIsNotNull() {
            addCriterion("ST_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andStCodeEqualTo(String value) {
            addCriterion("ST_CODE =", value, "stCode");
            return (Criteria) this;
        }

        public Criteria andStCodeNotEqualTo(String value) {
            addCriterion("ST_CODE <>", value, "stCode");
            return (Criteria) this;
        }

        public Criteria andStCodeGreaterThan(String value) {
            addCriterion("ST_CODE >", value, "stCode");
            return (Criteria) this;
        }

        public Criteria andStCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ST_CODE >=", value, "stCode");
            return (Criteria) this;
        }

        public Criteria andStCodeLessThan(String value) {
            addCriterion("ST_CODE <", value, "stCode");
            return (Criteria) this;
        }

        public Criteria andStCodeLessThanOrEqualTo(String value) {
            addCriterion("ST_CODE <=", value, "stCode");
            return (Criteria) this;
        }

        public Criteria andStCodeLike(String value) {
            addCriterion("ST_CODE like", value, "stCode");
            return (Criteria) this;
        }

        public Criteria andStCodeNotLike(String value) {
            addCriterion("ST_CODE not like", value, "stCode");
            return (Criteria) this;
        }

        public Criteria andStCodeIn(List<String> values) {
            addCriterion("ST_CODE in", values, "stCode");
            return (Criteria) this;
        }

        public Criteria andStCodeNotIn(List<String> values) {
            addCriterion("ST_CODE not in", values, "stCode");
            return (Criteria) this;
        }

        public Criteria andStCodeBetween(String value1, String value2) {
            addCriterion("ST_CODE between", value1, value2, "stCode");
            return (Criteria) this;
        }

        public Criteria andStCodeNotBetween(String value1, String value2) {
            addCriterion("ST_CODE not between", value1, value2, "stCode");
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

        public Criteria andTimeIdEqualTo(String value) {
            addCriterion("TIME_ID =", value, "timeId");
            return (Criteria) this;
        }

        public Criteria andTimeIdNotEqualTo(String value) {
            addCriterion("TIME_ID <>", value, "timeId");
            return (Criteria) this;
        }

        public Criteria andTimeIdGreaterThan(String value) {
            addCriterion("TIME_ID >", value, "timeId");
            return (Criteria) this;
        }

        public Criteria andTimeIdGreaterThanOrEqualTo(String value) {
            addCriterion("TIME_ID >=", value, "timeId");
            return (Criteria) this;
        }

        public Criteria andTimeIdLessThan(String value) {
            addCriterion("TIME_ID <", value, "timeId");
            return (Criteria) this;
        }

        public Criteria andTimeIdLessThanOrEqualTo(String value) {
            addCriterion("TIME_ID <=", value, "timeId");
            return (Criteria) this;
        }

        public Criteria andTimeIdLike(String value) {
            addCriterion("TIME_ID like", value, "timeId");
            return (Criteria) this;
        }

        public Criteria andTimeIdNotLike(String value) {
            addCriterion("TIME_ID not like", value, "timeId");
            return (Criteria) this;
        }

        public Criteria andTimeIdIn(List<String> values) {
            addCriterion("TIME_ID in", values, "timeId");
            return (Criteria) this;
        }

        public Criteria andTimeIdNotIn(List<String> values) {
            addCriterion("TIME_ID not in", values, "timeId");
            return (Criteria) this;
        }

        public Criteria andTimeIdBetween(String value1, String value2) {
            addCriterion("TIME_ID between", value1, value2, "timeId");
            return (Criteria) this;
        }

        public Criteria andTimeIdNotBetween(String value1, String value2) {
            addCriterion("TIME_ID not between", value1, value2, "timeId");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNull() {
            addCriterion("FILE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNotNull() {
            addCriterion("FILE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFileNameEqualTo(String value) {
            addCriterion("FILE_NAME =", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotEqualTo(String value) {
            addCriterion("FILE_NAME <>", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThan(String value) {
            addCriterion("FILE_NAME >", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThanOrEqualTo(String value) {
            addCriterion("FILE_NAME >=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThan(String value) {
            addCriterion("FILE_NAME <", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThanOrEqualTo(String value) {
            addCriterion("FILE_NAME <=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLike(String value) {
            addCriterion("FILE_NAME like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotLike(String value) {
            addCriterion("FILE_NAME not like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameIn(List<String> values) {
            addCriterion("FILE_NAME in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotIn(List<String> values) {
            addCriterion("FILE_NAME not in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameBetween(String value1, String value2) {
            addCriterion("FILE_NAME between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotBetween(String value1, String value2) {
            addCriterion("FILE_NAME not between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFilePathIsNull() {
            addCriterion("FILE_PATH is null");
            return (Criteria) this;
        }

        public Criteria andFilePathIsNotNull() {
            addCriterion("FILE_PATH is not null");
            return (Criteria) this;
        }

        public Criteria andFilePathEqualTo(String value) {
            addCriterion("FILE_PATH =", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotEqualTo(String value) {
            addCriterion("FILE_PATH <>", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathGreaterThan(String value) {
            addCriterion("FILE_PATH >", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathGreaterThanOrEqualTo(String value) {
            addCriterion("FILE_PATH >=", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLessThan(String value) {
            addCriterion("FILE_PATH <", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLessThanOrEqualTo(String value) {
            addCriterion("FILE_PATH <=", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLike(String value) {
            addCriterion("FILE_PATH like", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotLike(String value) {
            addCriterion("FILE_PATH not like", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathIn(List<String> values) {
            addCriterion("FILE_PATH in", values, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotIn(List<String> values) {
            addCriterion("FILE_PATH not in", values, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathBetween(String value1, String value2) {
            addCriterion("FILE_PATH between", value1, value2, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotBetween(String value1, String value2) {
            addCriterion("FILE_PATH not between", value1, value2, "filePath");
            return (Criteria) this;
        }

        public Criteria andFailIsNull() {
            addCriterion("FAIL is null");
            return (Criteria) this;
        }

        public Criteria andFailIsNotNull() {
            addCriterion("FAIL is not null");
            return (Criteria) this;
        }

        public Criteria andFailEqualTo(Boolean value) {
            addCriterion("FAIL =", value, "fail");
            return (Criteria) this;
        }

        public Criteria andFailNotEqualTo(Boolean value) {
            addCriterion("FAIL <>", value, "fail");
            return (Criteria) this;
        }

        public Criteria andFailGreaterThan(Boolean value) {
            addCriterion("FAIL >", value, "fail");
            return (Criteria) this;
        }

        public Criteria andFailGreaterThanOrEqualTo(Boolean value) {
            addCriterion("FAIL >=", value, "fail");
            return (Criteria) this;
        }

        public Criteria andFailLessThan(Boolean value) {
            addCriterion("FAIL <", value, "fail");
            return (Criteria) this;
        }

        public Criteria andFailLessThanOrEqualTo(Boolean value) {
            addCriterion("FAIL <=", value, "fail");
            return (Criteria) this;
        }

        public Criteria andFailIn(List<Boolean> values) {
            addCriterion("FAIL in", values, "fail");
            return (Criteria) this;
        }

        public Criteria andFailNotIn(List<Boolean> values) {
            addCriterion("FAIL not in", values, "fail");
            return (Criteria) this;
        }

        public Criteria andFailBetween(Boolean value1, Boolean value2) {
            addCriterion("FAIL between", value1, value2, "fail");
            return (Criteria) this;
        }

        public Criteria andFailNotBetween(Boolean value1, Boolean value2) {
            addCriterion("FAIL not between", value1, value2, "fail");
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