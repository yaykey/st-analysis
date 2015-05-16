package com.st.framework.module.stock.example;

import com.st.framework.module.example.BaseExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class GLHBTop5Example extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GLHBTop5Example() {
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

        public Criteria andSecIdIsNull() {
            addCriterion("SEC_ID is null");
            return (Criteria) this;
        }

        public Criteria andSecIdIsNotNull() {
            addCriterion("SEC_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSecIdEqualTo(Long value) {
            addCriterion("SEC_ID =", value, "secId");
            return (Criteria) this;
        }

        public Criteria andSecIdNotEqualTo(Long value) {
            addCriterion("SEC_ID <>", value, "secId");
            return (Criteria) this;
        }

        public Criteria andSecIdGreaterThan(Long value) {
            addCriterion("SEC_ID >", value, "secId");
            return (Criteria) this;
        }

        public Criteria andSecIdGreaterThanOrEqualTo(Long value) {
            addCriterion("SEC_ID >=", value, "secId");
            return (Criteria) this;
        }

        public Criteria andSecIdLessThan(Long value) {
            addCriterion("SEC_ID <", value, "secId");
            return (Criteria) this;
        }

        public Criteria andSecIdLessThanOrEqualTo(Long value) {
            addCriterion("SEC_ID <=", value, "secId");
            return (Criteria) this;
        }

        public Criteria andSecIdIn(List<Long> values) {
            addCriterion("SEC_ID in", values, "secId");
            return (Criteria) this;
        }

        public Criteria andSecIdNotIn(List<Long> values) {
            addCriterion("SEC_ID not in", values, "secId");
            return (Criteria) this;
        }

        public Criteria andSecIdBetween(Long value1, Long value2) {
            addCriterion("SEC_ID between", value1, value2, "secId");
            return (Criteria) this;
        }

        public Criteria andSecIdNotBetween(Long value1, Long value2) {
            addCriterion("SEC_ID not between", value1, value2, "secId");
            return (Criteria) this;
        }

        public Criteria andSecNameIsNull() {
            addCriterion("SEC_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSecNameIsNotNull() {
            addCriterion("SEC_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSecNameEqualTo(String value) {
            addCriterion("SEC_NAME =", value, "secName");
            return (Criteria) this;
        }

        public Criteria andSecNameNotEqualTo(String value) {
            addCriterion("SEC_NAME <>", value, "secName");
            return (Criteria) this;
        }

        public Criteria andSecNameGreaterThan(String value) {
            addCriterion("SEC_NAME >", value, "secName");
            return (Criteria) this;
        }

        public Criteria andSecNameGreaterThanOrEqualTo(String value) {
            addCriterion("SEC_NAME >=", value, "secName");
            return (Criteria) this;
        }

        public Criteria andSecNameLessThan(String value) {
            addCriterion("SEC_NAME <", value, "secName");
            return (Criteria) this;
        }

        public Criteria andSecNameLessThanOrEqualTo(String value) {
            addCriterion("SEC_NAME <=", value, "secName");
            return (Criteria) this;
        }

        public Criteria andSecNameLike(String value) {
            addCriterion("SEC_NAME like", value, "secName");
            return (Criteria) this;
        }

        public Criteria andSecNameNotLike(String value) {
            addCriterion("SEC_NAME not like", value, "secName");
            return (Criteria) this;
        }

        public Criteria andSecNameIn(List<String> values) {
            addCriterion("SEC_NAME in", values, "secName");
            return (Criteria) this;
        }

        public Criteria andSecNameNotIn(List<String> values) {
            addCriterion("SEC_NAME not in", values, "secName");
            return (Criteria) this;
        }

        public Criteria andSecNameBetween(String value1, String value2) {
            addCriterion("SEC_NAME between", value1, value2, "secName");
            return (Criteria) this;
        }

        public Criteria andSecNameNotBetween(String value1, String value2) {
            addCriterion("SEC_NAME not between", value1, value2, "secName");
            return (Criteria) this;
        }

        public Criteria andBuyAmountIsNull() {
            addCriterion("BUY_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andBuyAmountIsNotNull() {
            addCriterion("BUY_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andBuyAmountEqualTo(Double value) {
            addCriterion("BUY_AMOUNT =", value, "buyAmount");
            return (Criteria) this;
        }

        public Criteria andBuyAmountNotEqualTo(Double value) {
            addCriterion("BUY_AMOUNT <>", value, "buyAmount");
            return (Criteria) this;
        }

        public Criteria andBuyAmountGreaterThan(Double value) {
            addCriterion("BUY_AMOUNT >", value, "buyAmount");
            return (Criteria) this;
        }

        public Criteria andBuyAmountGreaterThanOrEqualTo(Double value) {
            addCriterion("BUY_AMOUNT >=", value, "buyAmount");
            return (Criteria) this;
        }

        public Criteria andBuyAmountLessThan(Double value) {
            addCriterion("BUY_AMOUNT <", value, "buyAmount");
            return (Criteria) this;
        }

        public Criteria andBuyAmountLessThanOrEqualTo(Double value) {
            addCriterion("BUY_AMOUNT <=", value, "buyAmount");
            return (Criteria) this;
        }

        public Criteria andBuyAmountIn(List<Double> values) {
            addCriterion("BUY_AMOUNT in", values, "buyAmount");
            return (Criteria) this;
        }

        public Criteria andBuyAmountNotIn(List<Double> values) {
            addCriterion("BUY_AMOUNT not in", values, "buyAmount");
            return (Criteria) this;
        }

        public Criteria andBuyAmountBetween(Double value1, Double value2) {
            addCriterion("BUY_AMOUNT between", value1, value2, "buyAmount");
            return (Criteria) this;
        }

        public Criteria andBuyAmountNotBetween(Double value1, Double value2) {
            addCriterion("BUY_AMOUNT not between", value1, value2, "buyAmount");
            return (Criteria) this;
        }

        public Criteria andSellAmountIsNull() {
            addCriterion("SELL_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andSellAmountIsNotNull() {
            addCriterion("SELL_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andSellAmountEqualTo(Double value) {
            addCriterion("SELL_AMOUNT =", value, "sellAmount");
            return (Criteria) this;
        }

        public Criteria andSellAmountNotEqualTo(Double value) {
            addCriterion("SELL_AMOUNT <>", value, "sellAmount");
            return (Criteria) this;
        }

        public Criteria andSellAmountGreaterThan(Double value) {
            addCriterion("SELL_AMOUNT >", value, "sellAmount");
            return (Criteria) this;
        }

        public Criteria andSellAmountGreaterThanOrEqualTo(Double value) {
            addCriterion("SELL_AMOUNT >=", value, "sellAmount");
            return (Criteria) this;
        }

        public Criteria andSellAmountLessThan(Double value) {
            addCriterion("SELL_AMOUNT <", value, "sellAmount");
            return (Criteria) this;
        }

        public Criteria andSellAmountLessThanOrEqualTo(Double value) {
            addCriterion("SELL_AMOUNT <=", value, "sellAmount");
            return (Criteria) this;
        }

        public Criteria andSellAmountIn(List<Double> values) {
            addCriterion("SELL_AMOUNT in", values, "sellAmount");
            return (Criteria) this;
        }

        public Criteria andSellAmountNotIn(List<Double> values) {
            addCriterion("SELL_AMOUNT not in", values, "sellAmount");
            return (Criteria) this;
        }

        public Criteria andSellAmountBetween(Double value1, Double value2) {
            addCriterion("SELL_AMOUNT between", value1, value2, "sellAmount");
            return (Criteria) this;
        }

        public Criteria andSellAmountNotBetween(Double value1, Double value2) {
            addCriterion("SELL_AMOUNT not between", value1, value2, "sellAmount");
            return (Criteria) this;
        }

        public Criteria andNetAmountIsNull() {
            addCriterion("NET_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andNetAmountIsNotNull() {
            addCriterion("NET_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andNetAmountEqualTo(Double value) {
            addCriterion("NET_AMOUNT =", value, "netAmount");
            return (Criteria) this;
        }

        public Criteria andNetAmountNotEqualTo(Double value) {
            addCriterion("NET_AMOUNT <>", value, "netAmount");
            return (Criteria) this;
        }

        public Criteria andNetAmountGreaterThan(Double value) {
            addCriterion("NET_AMOUNT >", value, "netAmount");
            return (Criteria) this;
        }

        public Criteria andNetAmountGreaterThanOrEqualTo(Double value) {
            addCriterion("NET_AMOUNT >=", value, "netAmount");
            return (Criteria) this;
        }

        public Criteria andNetAmountLessThan(Double value) {
            addCriterion("NET_AMOUNT <", value, "netAmount");
            return (Criteria) this;
        }

        public Criteria andNetAmountLessThanOrEqualTo(Double value) {
            addCriterion("NET_AMOUNT <=", value, "netAmount");
            return (Criteria) this;
        }

        public Criteria andNetAmountIn(List<Double> values) {
            addCriterion("NET_AMOUNT in", values, "netAmount");
            return (Criteria) this;
        }

        public Criteria andNetAmountNotIn(List<Double> values) {
            addCriterion("NET_AMOUNT not in", values, "netAmount");
            return (Criteria) this;
        }

        public Criteria andNetAmountBetween(Double value1, Double value2) {
            addCriterion("NET_AMOUNT between", value1, value2, "netAmount");
            return (Criteria) this;
        }

        public Criteria andNetAmountNotBetween(Double value1, Double value2) {
            addCriterion("NET_AMOUNT not between", value1, value2, "netAmount");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("TYPE =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("TYPE <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("TYPE >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("TYPE <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("TYPE <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("TYPE like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("TYPE not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("TYPE in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("TYPE not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("TYPE between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("TYPE not between", value1, value2, "type");
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