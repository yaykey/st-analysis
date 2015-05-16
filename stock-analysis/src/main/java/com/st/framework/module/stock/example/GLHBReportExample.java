package com.st.framework.module.stock.example;

import com.st.framework.module.example.BaseExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class GLHBReportExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GLHBReportExample() {
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

        public Criteria andStockCodeIsNull() {
            addCriterion("STOCK_CODE is null");
            return (Criteria) this;
        }

        public Criteria andStockCodeIsNotNull() {
            addCriterion("STOCK_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andStockCodeEqualTo(String value) {
            addCriterion("STOCK_CODE =", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeNotEqualTo(String value) {
            addCriterion("STOCK_CODE <>", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeGreaterThan(String value) {
            addCriterion("STOCK_CODE >", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeGreaterThanOrEqualTo(String value) {
            addCriterion("STOCK_CODE >=", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeLessThan(String value) {
            addCriterion("STOCK_CODE <", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeLessThanOrEqualTo(String value) {
            addCriterion("STOCK_CODE <=", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeLike(String value) {
            addCriterion("STOCK_CODE like", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeNotLike(String value) {
            addCriterion("STOCK_CODE not like", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeIn(List<String> values) {
            addCriterion("STOCK_CODE in", values, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeNotIn(List<String> values) {
            addCriterion("STOCK_CODE not in", values, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeBetween(String value1, String value2) {
            addCriterion("STOCK_CODE between", value1, value2, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeNotBetween(String value1, String value2) {
            addCriterion("STOCK_CODE not between", value1, value2, "stockCode");
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

        public Criteria andReasonsIsNull() {
            addCriterion("REASONS is null");
            return (Criteria) this;
        }

        public Criteria andReasonsIsNotNull() {
            addCriterion("REASONS is not null");
            return (Criteria) this;
        }

        public Criteria andReasonsEqualTo(String value) {
            addCriterion("REASONS =", value, "reasons");
            return (Criteria) this;
        }

        public Criteria andReasonsNotEqualTo(String value) {
            addCriterion("REASONS <>", value, "reasons");
            return (Criteria) this;
        }

        public Criteria andReasonsGreaterThan(String value) {
            addCriterion("REASONS >", value, "reasons");
            return (Criteria) this;
        }

        public Criteria andReasonsGreaterThanOrEqualTo(String value) {
            addCriterion("REASONS >=", value, "reasons");
            return (Criteria) this;
        }

        public Criteria andReasonsLessThan(String value) {
            addCriterion("REASONS <", value, "reasons");
            return (Criteria) this;
        }

        public Criteria andReasonsLessThanOrEqualTo(String value) {
            addCriterion("REASONS <=", value, "reasons");
            return (Criteria) this;
        }

        public Criteria andReasonsLike(String value) {
            addCriterion("REASONS like", value, "reasons");
            return (Criteria) this;
        }

        public Criteria andReasonsNotLike(String value) {
            addCriterion("REASONS not like", value, "reasons");
            return (Criteria) this;
        }

        public Criteria andReasonsIn(List<String> values) {
            addCriterion("REASONS in", values, "reasons");
            return (Criteria) this;
        }

        public Criteria andReasonsNotIn(List<String> values) {
            addCriterion("REASONS not in", values, "reasons");
            return (Criteria) this;
        }

        public Criteria andReasonsBetween(String value1, String value2) {
            addCriterion("REASONS between", value1, value2, "reasons");
            return (Criteria) this;
        }

        public Criteria andReasonsNotBetween(String value1, String value2) {
            addCriterion("REASONS not between", value1, value2, "reasons");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIsNull() {
            addCriterion("TOTAL_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIsNotNull() {
            addCriterion("TOTAL_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andTotalAmountEqualTo(Double value) {
            addCriterion("TOTAL_AMOUNT =", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotEqualTo(Double value) {
            addCriterion("TOTAL_AMOUNT <>", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountGreaterThan(Double value) {
            addCriterion("TOTAL_AMOUNT >", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountGreaterThanOrEqualTo(Double value) {
            addCriterion("TOTAL_AMOUNT >=", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountLessThan(Double value) {
            addCriterion("TOTAL_AMOUNT <", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountLessThanOrEqualTo(Double value) {
            addCriterion("TOTAL_AMOUNT <=", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIn(List<Double> values) {
            addCriterion("TOTAL_AMOUNT in", values, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotIn(List<Double> values) {
            addCriterion("TOTAL_AMOUNT not in", values, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountBetween(Double value1, Double value2) {
            addCriterion("TOTAL_AMOUNT between", value1, value2, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotBetween(Double value1, Double value2) {
            addCriterion("TOTAL_AMOUNT not between", value1, value2, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeIsNull() {
            addCriterion("TOTAL_VOLUME is null");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeIsNotNull() {
            addCriterion("TOTAL_VOLUME is not null");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeEqualTo(Double value) {
            addCriterion("TOTAL_VOLUME =", value, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeNotEqualTo(Double value) {
            addCriterion("TOTAL_VOLUME <>", value, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeGreaterThan(Double value) {
            addCriterion("TOTAL_VOLUME >", value, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeGreaterThanOrEqualTo(Double value) {
            addCriterion("TOTAL_VOLUME >=", value, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeLessThan(Double value) {
            addCriterion("TOTAL_VOLUME <", value, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeLessThanOrEqualTo(Double value) {
            addCriterion("TOTAL_VOLUME <=", value, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeIn(List<Double> values) {
            addCriterion("TOTAL_VOLUME in", values, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeNotIn(List<Double> values) {
            addCriterion("TOTAL_VOLUME not in", values, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeBetween(Double value1, Double value2) {
            addCriterion("TOTAL_VOLUME between", value1, value2, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeNotBetween(Double value1, Double value2) {
            addCriterion("TOTAL_VOLUME not between", value1, value2, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalCapitalPerIsNull() {
            addCriterion("TOTAL_CAPITAL_PER is null");
            return (Criteria) this;
        }

        public Criteria andTotalCapitalPerIsNotNull() {
            addCriterion("TOTAL_CAPITAL_PER is not null");
            return (Criteria) this;
        }

        public Criteria andTotalCapitalPerEqualTo(Double value) {
            addCriterion("TOTAL_CAPITAL_PER =", value, "totalCapitalPer");
            return (Criteria) this;
        }

        public Criteria andTotalCapitalPerNotEqualTo(Double value) {
            addCriterion("TOTAL_CAPITAL_PER <>", value, "totalCapitalPer");
            return (Criteria) this;
        }

        public Criteria andTotalCapitalPerGreaterThan(Double value) {
            addCriterion("TOTAL_CAPITAL_PER >", value, "totalCapitalPer");
            return (Criteria) this;
        }

        public Criteria andTotalCapitalPerGreaterThanOrEqualTo(Double value) {
            addCriterion("TOTAL_CAPITAL_PER >=", value, "totalCapitalPer");
            return (Criteria) this;
        }

        public Criteria andTotalCapitalPerLessThan(Double value) {
            addCriterion("TOTAL_CAPITAL_PER <", value, "totalCapitalPer");
            return (Criteria) this;
        }

        public Criteria andTotalCapitalPerLessThanOrEqualTo(Double value) {
            addCriterion("TOTAL_CAPITAL_PER <=", value, "totalCapitalPer");
            return (Criteria) this;
        }

        public Criteria andTotalCapitalPerIn(List<Double> values) {
            addCriterion("TOTAL_CAPITAL_PER in", values, "totalCapitalPer");
            return (Criteria) this;
        }

        public Criteria andTotalCapitalPerNotIn(List<Double> values) {
            addCriterion("TOTAL_CAPITAL_PER not in", values, "totalCapitalPer");
            return (Criteria) this;
        }

        public Criteria andTotalCapitalPerBetween(Double value1, Double value2) {
            addCriterion("TOTAL_CAPITAL_PER between", value1, value2, "totalCapitalPer");
            return (Criteria) this;
        }

        public Criteria andTotalCapitalPerNotBetween(Double value1, Double value2) {
            addCriterion("TOTAL_CAPITAL_PER not between", value1, value2, "totalCapitalPer");
            return (Criteria) this;
        }

        public Criteria andASharePerIsNull() {
            addCriterion("A_SHARE_PER is null");
            return (Criteria) this;
        }

        public Criteria andASharePerIsNotNull() {
            addCriterion("A_SHARE_PER is not null");
            return (Criteria) this;
        }

        public Criteria andASharePerEqualTo(Double value) {
            addCriterion("A_SHARE_PER =", value, "aSharePer");
            return (Criteria) this;
        }

        public Criteria andASharePerNotEqualTo(Double value) {
            addCriterion("A_SHARE_PER <>", value, "aSharePer");
            return (Criteria) this;
        }

        public Criteria andASharePerGreaterThan(Double value) {
            addCriterion("A_SHARE_PER >", value, "aSharePer");
            return (Criteria) this;
        }

        public Criteria andASharePerGreaterThanOrEqualTo(Double value) {
            addCriterion("A_SHARE_PER >=", value, "aSharePer");
            return (Criteria) this;
        }

        public Criteria andASharePerLessThan(Double value) {
            addCriterion("A_SHARE_PER <", value, "aSharePer");
            return (Criteria) this;
        }

        public Criteria andASharePerLessThanOrEqualTo(Double value) {
            addCriterion("A_SHARE_PER <=", value, "aSharePer");
            return (Criteria) this;
        }

        public Criteria andASharePerIn(List<Double> values) {
            addCriterion("A_SHARE_PER in", values, "aSharePer");
            return (Criteria) this;
        }

        public Criteria andASharePerNotIn(List<Double> values) {
            addCriterion("A_SHARE_PER not in", values, "aSharePer");
            return (Criteria) this;
        }

        public Criteria andASharePerBetween(Double value1, Double value2) {
            addCriterion("A_SHARE_PER between", value1, value2, "aSharePer");
            return (Criteria) this;
        }

        public Criteria andASharePerNotBetween(Double value1, Double value2) {
            addCriterion("A_SHARE_PER not between", value1, value2, "aSharePer");
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