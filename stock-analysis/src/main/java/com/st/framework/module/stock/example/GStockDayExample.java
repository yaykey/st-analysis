package com.st.framework.module.stock.example;

import com.st.framework.module.example.BaseExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class GStockDayExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GStockDayExample() {
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

        public Criteria andStockIsNull() {
            addCriterion("STOCK is null");
            return (Criteria) this;
        }

        public Criteria andStockIsNotNull() {
            addCriterion("STOCK is not null");
            return (Criteria) this;
        }

        public Criteria andStockEqualTo(String value) {
            addCriterion("STOCK =", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockNotEqualTo(String value) {
            addCriterion("STOCK <>", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockGreaterThan(String value) {
            addCriterion("STOCK >", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockGreaterThanOrEqualTo(String value) {
            addCriterion("STOCK >=", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockLessThan(String value) {
            addCriterion("STOCK <", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockLessThanOrEqualTo(String value) {
            addCriterion("STOCK <=", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockLike(String value) {
            addCriterion("STOCK like", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockNotLike(String value) {
            addCriterion("STOCK not like", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockIn(List<String> values) {
            addCriterion("STOCK in", values, "stock");
            return (Criteria) this;
        }

        public Criteria andStockNotIn(List<String> values) {
            addCriterion("STOCK not in", values, "stock");
            return (Criteria) this;
        }

        public Criteria andStockBetween(String value1, String value2) {
            addCriterion("STOCK between", value1, value2, "stock");
            return (Criteria) this;
        }

        public Criteria andStockNotBetween(String value1, String value2) {
            addCriterion("STOCK not between", value1, value2, "stock");
            return (Criteria) this;
        }

        public Criteria andDateIsNull() {
            addCriterion("DATE is null");
            return (Criteria) this;
        }

        public Criteria andDateIsNotNull() {
            addCriterion("DATE is not null");
            return (Criteria) this;
        }

        public Criteria andDateEqualTo(Date value) {
            addCriterionForJDBCDate("DATE =", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("DATE <>", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThan(Date value) {
            addCriterionForJDBCDate("DATE >", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("DATE >=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThan(Date value) {
            addCriterionForJDBCDate("DATE <", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("DATE <=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateIn(List<Date> values) {
            addCriterionForJDBCDate("DATE in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("DATE not in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("DATE between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("DATE not between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andOpenIsNull() {
            addCriterion("OPEN is null");
            return (Criteria) this;
        }

        public Criteria andOpenIsNotNull() {
            addCriterion("OPEN is not null");
            return (Criteria) this;
        }

        public Criteria andOpenEqualTo(Double value) {
            addCriterion("OPEN =", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenNotEqualTo(Double value) {
            addCriterion("OPEN <>", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenGreaterThan(Double value) {
            addCriterion("OPEN >", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenGreaterThanOrEqualTo(Double value) {
            addCriterion("OPEN >=", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenLessThan(Double value) {
            addCriterion("OPEN <", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenLessThanOrEqualTo(Double value) {
            addCriterion("OPEN <=", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenIn(List<Double> values) {
            addCriterion("OPEN in", values, "open");
            return (Criteria) this;
        }

        public Criteria andOpenNotIn(List<Double> values) {
            addCriterion("OPEN not in", values, "open");
            return (Criteria) this;
        }

        public Criteria andOpenBetween(Double value1, Double value2) {
            addCriterion("OPEN between", value1, value2, "open");
            return (Criteria) this;
        }

        public Criteria andOpenNotBetween(Double value1, Double value2) {
            addCriterion("OPEN not between", value1, value2, "open");
            return (Criteria) this;
        }

        public Criteria andHighIsNull() {
            addCriterion("HIGH is null");
            return (Criteria) this;
        }

        public Criteria andHighIsNotNull() {
            addCriterion("HIGH is not null");
            return (Criteria) this;
        }

        public Criteria andHighEqualTo(Double value) {
            addCriterion("HIGH =", value, "high");
            return (Criteria) this;
        }

        public Criteria andHighNotEqualTo(Double value) {
            addCriterion("HIGH <>", value, "high");
            return (Criteria) this;
        }

        public Criteria andHighGreaterThan(Double value) {
            addCriterion("HIGH >", value, "high");
            return (Criteria) this;
        }

        public Criteria andHighGreaterThanOrEqualTo(Double value) {
            addCriterion("HIGH >=", value, "high");
            return (Criteria) this;
        }

        public Criteria andHighLessThan(Double value) {
            addCriterion("HIGH <", value, "high");
            return (Criteria) this;
        }

        public Criteria andHighLessThanOrEqualTo(Double value) {
            addCriterion("HIGH <=", value, "high");
            return (Criteria) this;
        }

        public Criteria andHighIn(List<Double> values) {
            addCriterion("HIGH in", values, "high");
            return (Criteria) this;
        }

        public Criteria andHighNotIn(List<Double> values) {
            addCriterion("HIGH not in", values, "high");
            return (Criteria) this;
        }

        public Criteria andHighBetween(Double value1, Double value2) {
            addCriterion("HIGH between", value1, value2, "high");
            return (Criteria) this;
        }

        public Criteria andHighNotBetween(Double value1, Double value2) {
            addCriterion("HIGH not between", value1, value2, "high");
            return (Criteria) this;
        }

        public Criteria andLowIsNull() {
            addCriterion("LOW is null");
            return (Criteria) this;
        }

        public Criteria andLowIsNotNull() {
            addCriterion("LOW is not null");
            return (Criteria) this;
        }

        public Criteria andLowEqualTo(Double value) {
            addCriterion("LOW =", value, "low");
            return (Criteria) this;
        }

        public Criteria andLowNotEqualTo(Double value) {
            addCriterion("LOW <>", value, "low");
            return (Criteria) this;
        }

        public Criteria andLowGreaterThan(Double value) {
            addCriterion("LOW >", value, "low");
            return (Criteria) this;
        }

        public Criteria andLowGreaterThanOrEqualTo(Double value) {
            addCriterion("LOW >=", value, "low");
            return (Criteria) this;
        }

        public Criteria andLowLessThan(Double value) {
            addCriterion("LOW <", value, "low");
            return (Criteria) this;
        }

        public Criteria andLowLessThanOrEqualTo(Double value) {
            addCriterion("LOW <=", value, "low");
            return (Criteria) this;
        }

        public Criteria andLowIn(List<Double> values) {
            addCriterion("LOW in", values, "low");
            return (Criteria) this;
        }

        public Criteria andLowNotIn(List<Double> values) {
            addCriterion("LOW not in", values, "low");
            return (Criteria) this;
        }

        public Criteria andLowBetween(Double value1, Double value2) {
            addCriterion("LOW between", value1, value2, "low");
            return (Criteria) this;
        }

        public Criteria andLowNotBetween(Double value1, Double value2) {
            addCriterion("LOW not between", value1, value2, "low");
            return (Criteria) this;
        }

        public Criteria andCloseIsNull() {
            addCriterion("CLOSE is null");
            return (Criteria) this;
        }

        public Criteria andCloseIsNotNull() {
            addCriterion("CLOSE is not null");
            return (Criteria) this;
        }

        public Criteria andCloseEqualTo(Double value) {
            addCriterion("CLOSE =", value, "close");
            return (Criteria) this;
        }

        public Criteria andCloseNotEqualTo(Double value) {
            addCriterion("CLOSE <>", value, "close");
            return (Criteria) this;
        }

        public Criteria andCloseGreaterThan(Double value) {
            addCriterion("CLOSE >", value, "close");
            return (Criteria) this;
        }

        public Criteria andCloseGreaterThanOrEqualTo(Double value) {
            addCriterion("CLOSE >=", value, "close");
            return (Criteria) this;
        }

        public Criteria andCloseLessThan(Double value) {
            addCriterion("CLOSE <", value, "close");
            return (Criteria) this;
        }

        public Criteria andCloseLessThanOrEqualTo(Double value) {
            addCriterion("CLOSE <=", value, "close");
            return (Criteria) this;
        }

        public Criteria andCloseIn(List<Double> values) {
            addCriterion("CLOSE in", values, "close");
            return (Criteria) this;
        }

        public Criteria andCloseNotIn(List<Double> values) {
            addCriterion("CLOSE not in", values, "close");
            return (Criteria) this;
        }

        public Criteria andCloseBetween(Double value1, Double value2) {
            addCriterion("CLOSE between", value1, value2, "close");
            return (Criteria) this;
        }

        public Criteria andCloseNotBetween(Double value1, Double value2) {
            addCriterion("CLOSE not between", value1, value2, "close");
            return (Criteria) this;
        }

        public Criteria andVolumeIsNull() {
            addCriterion("VOLUME is null");
            return (Criteria) this;
        }

        public Criteria andVolumeIsNotNull() {
            addCriterion("VOLUME is not null");
            return (Criteria) this;
        }

        public Criteria andVolumeEqualTo(Double value) {
            addCriterion("VOLUME =", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotEqualTo(Double value) {
            addCriterion("VOLUME <>", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeGreaterThan(Double value) {
            addCriterion("VOLUME >", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeGreaterThanOrEqualTo(Double value) {
            addCriterion("VOLUME >=", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeLessThan(Double value) {
            addCriterion("VOLUME <", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeLessThanOrEqualTo(Double value) {
            addCriterion("VOLUME <=", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeIn(List<Double> values) {
            addCriterion("VOLUME in", values, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotIn(List<Double> values) {
            addCriterion("VOLUME not in", values, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeBetween(Double value1, Double value2) {
            addCriterion("VOLUME between", value1, value2, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotBetween(Double value1, Double value2) {
            addCriterion("VOLUME not between", value1, value2, "volume");
            return (Criteria) this;
        }

        public Criteria andAdjCloseIsNull() {
            addCriterion("ADJ_CLOSE is null");
            return (Criteria) this;
        }

        public Criteria andAdjCloseIsNotNull() {
            addCriterion("ADJ_CLOSE is not null");
            return (Criteria) this;
        }

        public Criteria andAdjCloseEqualTo(Double value) {
            addCriterion("ADJ_CLOSE =", value, "adjClose");
            return (Criteria) this;
        }

        public Criteria andAdjCloseNotEqualTo(Double value) {
            addCriterion("ADJ_CLOSE <>", value, "adjClose");
            return (Criteria) this;
        }

        public Criteria andAdjCloseGreaterThan(Double value) {
            addCriterion("ADJ_CLOSE >", value, "adjClose");
            return (Criteria) this;
        }

        public Criteria andAdjCloseGreaterThanOrEqualTo(Double value) {
            addCriterion("ADJ_CLOSE >=", value, "adjClose");
            return (Criteria) this;
        }

        public Criteria andAdjCloseLessThan(Double value) {
            addCriterion("ADJ_CLOSE <", value, "adjClose");
            return (Criteria) this;
        }

        public Criteria andAdjCloseLessThanOrEqualTo(Double value) {
            addCriterion("ADJ_CLOSE <=", value, "adjClose");
            return (Criteria) this;
        }

        public Criteria andAdjCloseIn(List<Double> values) {
            addCriterion("ADJ_CLOSE in", values, "adjClose");
            return (Criteria) this;
        }

        public Criteria andAdjCloseNotIn(List<Double> values) {
            addCriterion("ADJ_CLOSE not in", values, "adjClose");
            return (Criteria) this;
        }

        public Criteria andAdjCloseBetween(Double value1, Double value2) {
            addCriterion("ADJ_CLOSE between", value1, value2, "adjClose");
            return (Criteria) this;
        }

        public Criteria andAdjCloseNotBetween(Double value1, Double value2) {
            addCriterion("ADJ_CLOSE not between", value1, value2, "adjClose");
            return (Criteria) this;
        }

        public Criteria andPriceChangesIsNull() {
            addCriterion("PRICE_CHANGES is null");
            return (Criteria) this;
        }

        public Criteria andPriceChangesIsNotNull() {
            addCriterion("PRICE_CHANGES is not null");
            return (Criteria) this;
        }

        public Criteria andPriceChangesEqualTo(Double value) {
            addCriterion("PRICE_CHANGES =", value, "priceChanges");
            return (Criteria) this;
        }

        public Criteria andPriceChangesNotEqualTo(Double value) {
            addCriterion("PRICE_CHANGES <>", value, "priceChanges");
            return (Criteria) this;
        }

        public Criteria andPriceChangesGreaterThan(Double value) {
            addCriterion("PRICE_CHANGES >", value, "priceChanges");
            return (Criteria) this;
        }

        public Criteria andPriceChangesGreaterThanOrEqualTo(Double value) {
            addCriterion("PRICE_CHANGES >=", value, "priceChanges");
            return (Criteria) this;
        }

        public Criteria andPriceChangesLessThan(Double value) {
            addCriterion("PRICE_CHANGES <", value, "priceChanges");
            return (Criteria) this;
        }

        public Criteria andPriceChangesLessThanOrEqualTo(Double value) {
            addCriterion("PRICE_CHANGES <=", value, "priceChanges");
            return (Criteria) this;
        }

        public Criteria andPriceChangesIn(List<Double> values) {
            addCriterion("PRICE_CHANGES in", values, "priceChanges");
            return (Criteria) this;
        }

        public Criteria andPriceChangesNotIn(List<Double> values) {
            addCriterion("PRICE_CHANGES not in", values, "priceChanges");
            return (Criteria) this;
        }

        public Criteria andPriceChangesBetween(Double value1, Double value2) {
            addCriterion("PRICE_CHANGES between", value1, value2, "priceChanges");
            return (Criteria) this;
        }

        public Criteria andPriceChangesNotBetween(Double value1, Double value2) {
            addCriterion("PRICE_CHANGES not between", value1, value2, "priceChanges");
            return (Criteria) this;
        }

        public Criteria andHighTimeIdIsNull() {
            addCriterion("HIGH_TIME_ID is null");
            return (Criteria) this;
        }

        public Criteria andHighTimeIdIsNotNull() {
            addCriterion("HIGH_TIME_ID is not null");
            return (Criteria) this;
        }

        public Criteria andHighTimeIdEqualTo(String value) {
            addCriterion("HIGH_TIME_ID =", value, "highTimeId");
            return (Criteria) this;
        }

        public Criteria andHighTimeIdNotEqualTo(String value) {
            addCriterion("HIGH_TIME_ID <>", value, "highTimeId");
            return (Criteria) this;
        }

        public Criteria andHighTimeIdGreaterThan(String value) {
            addCriterion("HIGH_TIME_ID >", value, "highTimeId");
            return (Criteria) this;
        }

        public Criteria andHighTimeIdGreaterThanOrEqualTo(String value) {
            addCriterion("HIGH_TIME_ID >=", value, "highTimeId");
            return (Criteria) this;
        }

        public Criteria andHighTimeIdLessThan(String value) {
            addCriterion("HIGH_TIME_ID <", value, "highTimeId");
            return (Criteria) this;
        }

        public Criteria andHighTimeIdLessThanOrEqualTo(String value) {
            addCriterion("HIGH_TIME_ID <=", value, "highTimeId");
            return (Criteria) this;
        }

        public Criteria andHighTimeIdLike(String value) {
            addCriterion("HIGH_TIME_ID like", value, "highTimeId");
            return (Criteria) this;
        }

        public Criteria andHighTimeIdNotLike(String value) {
            addCriterion("HIGH_TIME_ID not like", value, "highTimeId");
            return (Criteria) this;
        }

        public Criteria andHighTimeIdIn(List<String> values) {
            addCriterion("HIGH_TIME_ID in", values, "highTimeId");
            return (Criteria) this;
        }

        public Criteria andHighTimeIdNotIn(List<String> values) {
            addCriterion("HIGH_TIME_ID not in", values, "highTimeId");
            return (Criteria) this;
        }

        public Criteria andHighTimeIdBetween(String value1, String value2) {
            addCriterion("HIGH_TIME_ID between", value1, value2, "highTimeId");
            return (Criteria) this;
        }

        public Criteria andHighTimeIdNotBetween(String value1, String value2) {
            addCriterion("HIGH_TIME_ID not between", value1, value2, "highTimeId");
            return (Criteria) this;
        }

        public Criteria andLowTimeIdIsNull() {
            addCriterion("LOW_TIME_ID is null");
            return (Criteria) this;
        }

        public Criteria andLowTimeIdIsNotNull() {
            addCriterion("LOW_TIME_ID is not null");
            return (Criteria) this;
        }

        public Criteria andLowTimeIdEqualTo(String value) {
            addCriterion("LOW_TIME_ID =", value, "lowTimeId");
            return (Criteria) this;
        }

        public Criteria andLowTimeIdNotEqualTo(String value) {
            addCriterion("LOW_TIME_ID <>", value, "lowTimeId");
            return (Criteria) this;
        }

        public Criteria andLowTimeIdGreaterThan(String value) {
            addCriterion("LOW_TIME_ID >", value, "lowTimeId");
            return (Criteria) this;
        }

        public Criteria andLowTimeIdGreaterThanOrEqualTo(String value) {
            addCriterion("LOW_TIME_ID >=", value, "lowTimeId");
            return (Criteria) this;
        }

        public Criteria andLowTimeIdLessThan(String value) {
            addCriterion("LOW_TIME_ID <", value, "lowTimeId");
            return (Criteria) this;
        }

        public Criteria andLowTimeIdLessThanOrEqualTo(String value) {
            addCriterion("LOW_TIME_ID <=", value, "lowTimeId");
            return (Criteria) this;
        }

        public Criteria andLowTimeIdLike(String value) {
            addCriterion("LOW_TIME_ID like", value, "lowTimeId");
            return (Criteria) this;
        }

        public Criteria andLowTimeIdNotLike(String value) {
            addCriterion("LOW_TIME_ID not like", value, "lowTimeId");
            return (Criteria) this;
        }

        public Criteria andLowTimeIdIn(List<String> values) {
            addCriterion("LOW_TIME_ID in", values, "lowTimeId");
            return (Criteria) this;
        }

        public Criteria andLowTimeIdNotIn(List<String> values) {
            addCriterion("LOW_TIME_ID not in", values, "lowTimeId");
            return (Criteria) this;
        }

        public Criteria andLowTimeIdBetween(String value1, String value2) {
            addCriterion("LOW_TIME_ID between", value1, value2, "lowTimeId");
            return (Criteria) this;
        }

        public Criteria andLowTimeIdNotBetween(String value1, String value2) {
            addCriterion("LOW_TIME_ID not between", value1, value2, "lowTimeId");
            return (Criteria) this;
        }

        public Criteria andAmplitudeIsNull() {
            addCriterion("AMPLITUDE is null");
            return (Criteria) this;
        }

        public Criteria andAmplitudeIsNotNull() {
            addCriterion("AMPLITUDE is not null");
            return (Criteria) this;
        }

        public Criteria andAmplitudeEqualTo(Double value) {
            addCriterion("AMPLITUDE =", value, "amplitude");
            return (Criteria) this;
        }

        public Criteria andAmplitudeNotEqualTo(Double value) {
            addCriterion("AMPLITUDE <>", value, "amplitude");
            return (Criteria) this;
        }

        public Criteria andAmplitudeGreaterThan(Double value) {
            addCriterion("AMPLITUDE >", value, "amplitude");
            return (Criteria) this;
        }

        public Criteria andAmplitudeGreaterThanOrEqualTo(Double value) {
            addCriterion("AMPLITUDE >=", value, "amplitude");
            return (Criteria) this;
        }

        public Criteria andAmplitudeLessThan(Double value) {
            addCriterion("AMPLITUDE <", value, "amplitude");
            return (Criteria) this;
        }

        public Criteria andAmplitudeLessThanOrEqualTo(Double value) {
            addCriterion("AMPLITUDE <=", value, "amplitude");
            return (Criteria) this;
        }

        public Criteria andAmplitudeIn(List<Double> values) {
            addCriterion("AMPLITUDE in", values, "amplitude");
            return (Criteria) this;
        }

        public Criteria andAmplitudeNotIn(List<Double> values) {
            addCriterion("AMPLITUDE not in", values, "amplitude");
            return (Criteria) this;
        }

        public Criteria andAmplitudeBetween(Double value1, Double value2) {
            addCriterion("AMPLITUDE between", value1, value2, "amplitude");
            return (Criteria) this;
        }

        public Criteria andAmplitudeNotBetween(Double value1, Double value2) {
            addCriterion("AMPLITUDE not between", value1, value2, "amplitude");
            return (Criteria) this;
        }

        public Criteria andHighPerIsNull() {
            addCriterion("HIGH_PER is null");
            return (Criteria) this;
        }

        public Criteria andHighPerIsNotNull() {
            addCriterion("HIGH_PER is not null");
            return (Criteria) this;
        }

        public Criteria andHighPerEqualTo(Double value) {
            addCriterion("HIGH_PER =", value, "highPer");
            return (Criteria) this;
        }

        public Criteria andHighPerNotEqualTo(Double value) {
            addCriterion("HIGH_PER <>", value, "highPer");
            return (Criteria) this;
        }

        public Criteria andHighPerGreaterThan(Double value) {
            addCriterion("HIGH_PER >", value, "highPer");
            return (Criteria) this;
        }

        public Criteria andHighPerGreaterThanOrEqualTo(Double value) {
            addCriterion("HIGH_PER >=", value, "highPer");
            return (Criteria) this;
        }

        public Criteria andHighPerLessThan(Double value) {
            addCriterion("HIGH_PER <", value, "highPer");
            return (Criteria) this;
        }

        public Criteria andHighPerLessThanOrEqualTo(Double value) {
            addCriterion("HIGH_PER <=", value, "highPer");
            return (Criteria) this;
        }

        public Criteria andHighPerIn(List<Double> values) {
            addCriterion("HIGH_PER in", values, "highPer");
            return (Criteria) this;
        }

        public Criteria andHighPerNotIn(List<Double> values) {
            addCriterion("HIGH_PER not in", values, "highPer");
            return (Criteria) this;
        }

        public Criteria andHighPerBetween(Double value1, Double value2) {
            addCriterion("HIGH_PER between", value1, value2, "highPer");
            return (Criteria) this;
        }

        public Criteria andHighPerNotBetween(Double value1, Double value2) {
            addCriterion("HIGH_PER not between", value1, value2, "highPer");
            return (Criteria) this;
        }

        public Criteria andLowPerIsNull() {
            addCriterion("LOW_PER is null");
            return (Criteria) this;
        }

        public Criteria andLowPerIsNotNull() {
            addCriterion("LOW_PER is not null");
            return (Criteria) this;
        }

        public Criteria andLowPerEqualTo(Double value) {
            addCriterion("LOW_PER =", value, "lowPer");
            return (Criteria) this;
        }

        public Criteria andLowPerNotEqualTo(Double value) {
            addCriterion("LOW_PER <>", value, "lowPer");
            return (Criteria) this;
        }

        public Criteria andLowPerGreaterThan(Double value) {
            addCriterion("LOW_PER >", value, "lowPer");
            return (Criteria) this;
        }

        public Criteria andLowPerGreaterThanOrEqualTo(Double value) {
            addCriterion("LOW_PER >=", value, "lowPer");
            return (Criteria) this;
        }

        public Criteria andLowPerLessThan(Double value) {
            addCriterion("LOW_PER <", value, "lowPer");
            return (Criteria) this;
        }

        public Criteria andLowPerLessThanOrEqualTo(Double value) {
            addCriterion("LOW_PER <=", value, "lowPer");
            return (Criteria) this;
        }

        public Criteria andLowPerIn(List<Double> values) {
            addCriterion("LOW_PER in", values, "lowPer");
            return (Criteria) this;
        }

        public Criteria andLowPerNotIn(List<Double> values) {
            addCriterion("LOW_PER not in", values, "lowPer");
            return (Criteria) this;
        }

        public Criteria andLowPerBetween(Double value1, Double value2) {
            addCriterion("LOW_PER between", value1, value2, "lowPer");
            return (Criteria) this;
        }

        public Criteria andLowPerNotBetween(Double value1, Double value2) {
            addCriterion("LOW_PER not between", value1, value2, "lowPer");
            return (Criteria) this;
        }

        public Criteria andClosePerIsNull() {
            addCriterion("CLOSE_PER is null");
            return (Criteria) this;
        }

        public Criteria andClosePerIsNotNull() {
            addCriterion("CLOSE_PER is not null");
            return (Criteria) this;
        }

        public Criteria andClosePerEqualTo(Double value) {
            addCriterion("CLOSE_PER =", value, "closePer");
            return (Criteria) this;
        }

        public Criteria andClosePerNotEqualTo(Double value) {
            addCriterion("CLOSE_PER <>", value, "closePer");
            return (Criteria) this;
        }

        public Criteria andClosePerGreaterThan(Double value) {
            addCriterion("CLOSE_PER >", value, "closePer");
            return (Criteria) this;
        }

        public Criteria andClosePerGreaterThanOrEqualTo(Double value) {
            addCriterion("CLOSE_PER >=", value, "closePer");
            return (Criteria) this;
        }

        public Criteria andClosePerLessThan(Double value) {
            addCriterion("CLOSE_PER <", value, "closePer");
            return (Criteria) this;
        }

        public Criteria andClosePerLessThanOrEqualTo(Double value) {
            addCriterion("CLOSE_PER <=", value, "closePer");
            return (Criteria) this;
        }

        public Criteria andClosePerIn(List<Double> values) {
            addCriterion("CLOSE_PER in", values, "closePer");
            return (Criteria) this;
        }

        public Criteria andClosePerNotIn(List<Double> values) {
            addCriterion("CLOSE_PER not in", values, "closePer");
            return (Criteria) this;
        }

        public Criteria andClosePerBetween(Double value1, Double value2) {
            addCriterion("CLOSE_PER between", value1, value2, "closePer");
            return (Criteria) this;
        }

        public Criteria andClosePerNotBetween(Double value1, Double value2) {
            addCriterion("CLOSE_PER not between", value1, value2, "closePer");
            return (Criteria) this;
        }

        public Criteria andOpenPerIsNull() {
            addCriterion("OPEN_PER is null");
            return (Criteria) this;
        }

        public Criteria andOpenPerIsNotNull() {
            addCriterion("OPEN_PER is not null");
            return (Criteria) this;
        }

        public Criteria andOpenPerEqualTo(Double value) {
            addCriterion("OPEN_PER =", value, "openPer");
            return (Criteria) this;
        }

        public Criteria andOpenPerNotEqualTo(Double value) {
            addCriterion("OPEN_PER <>", value, "openPer");
            return (Criteria) this;
        }

        public Criteria andOpenPerGreaterThan(Double value) {
            addCriterion("OPEN_PER >", value, "openPer");
            return (Criteria) this;
        }

        public Criteria andOpenPerGreaterThanOrEqualTo(Double value) {
            addCriterion("OPEN_PER >=", value, "openPer");
            return (Criteria) this;
        }

        public Criteria andOpenPerLessThan(Double value) {
            addCriterion("OPEN_PER <", value, "openPer");
            return (Criteria) this;
        }

        public Criteria andOpenPerLessThanOrEqualTo(Double value) {
            addCriterion("OPEN_PER <=", value, "openPer");
            return (Criteria) this;
        }

        public Criteria andOpenPerIn(List<Double> values) {
            addCriterion("OPEN_PER in", values, "openPer");
            return (Criteria) this;
        }

        public Criteria andOpenPerNotIn(List<Double> values) {
            addCriterion("OPEN_PER not in", values, "openPer");
            return (Criteria) this;
        }

        public Criteria andOpenPerBetween(Double value1, Double value2) {
            addCriterion("OPEN_PER between", value1, value2, "openPer");
            return (Criteria) this;
        }

        public Criteria andOpenPerNotBetween(Double value1, Double value2) {
            addCriterion("OPEN_PER not between", value1, value2, "openPer");
            return (Criteria) this;
        }

        public Criteria andTurnVolumeIsNull() {
            addCriterion("TURN_VOLUME is null");
            return (Criteria) this;
        }

        public Criteria andTurnVolumeIsNotNull() {
            addCriterion("TURN_VOLUME is not null");
            return (Criteria) this;
        }

        public Criteria andTurnVolumeEqualTo(Double value) {
            addCriterion("TURN_VOLUME =", value, "turnVolume");
            return (Criteria) this;
        }

        public Criteria andTurnVolumeNotEqualTo(Double value) {
            addCriterion("TURN_VOLUME <>", value, "turnVolume");
            return (Criteria) this;
        }

        public Criteria andTurnVolumeGreaterThan(Double value) {
            addCriterion("TURN_VOLUME >", value, "turnVolume");
            return (Criteria) this;
        }

        public Criteria andTurnVolumeGreaterThanOrEqualTo(Double value) {
            addCriterion("TURN_VOLUME >=", value, "turnVolume");
            return (Criteria) this;
        }

        public Criteria andTurnVolumeLessThan(Double value) {
            addCriterion("TURN_VOLUME <", value, "turnVolume");
            return (Criteria) this;
        }

        public Criteria andTurnVolumeLessThanOrEqualTo(Double value) {
            addCriterion("TURN_VOLUME <=", value, "turnVolume");
            return (Criteria) this;
        }

        public Criteria andTurnVolumeIn(List<Double> values) {
            addCriterion("TURN_VOLUME in", values, "turnVolume");
            return (Criteria) this;
        }

        public Criteria andTurnVolumeNotIn(List<Double> values) {
            addCriterion("TURN_VOLUME not in", values, "turnVolume");
            return (Criteria) this;
        }

        public Criteria andTurnVolumeBetween(Double value1, Double value2) {
            addCriterion("TURN_VOLUME between", value1, value2, "turnVolume");
            return (Criteria) this;
        }

        public Criteria andTurnVolumeNotBetween(Double value1, Double value2) {
            addCriterion("TURN_VOLUME not between", value1, value2, "turnVolume");
            return (Criteria) this;
        }

        public Criteria andTurnoverRateIsNull() {
            addCriterion("TURNOVER_RATE is null");
            return (Criteria) this;
        }

        public Criteria andTurnoverRateIsNotNull() {
            addCriterion("TURNOVER_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andTurnoverRateEqualTo(Double value) {
            addCriterion("TURNOVER_RATE =", value, "turnoverRate");
            return (Criteria) this;
        }

        public Criteria andTurnoverRateNotEqualTo(Double value) {
            addCriterion("TURNOVER_RATE <>", value, "turnoverRate");
            return (Criteria) this;
        }

        public Criteria andTurnoverRateGreaterThan(Double value) {
            addCriterion("TURNOVER_RATE >", value, "turnoverRate");
            return (Criteria) this;
        }

        public Criteria andTurnoverRateGreaterThanOrEqualTo(Double value) {
            addCriterion("TURNOVER_RATE >=", value, "turnoverRate");
            return (Criteria) this;
        }

        public Criteria andTurnoverRateLessThan(Double value) {
            addCriterion("TURNOVER_RATE <", value, "turnoverRate");
            return (Criteria) this;
        }

        public Criteria andTurnoverRateLessThanOrEqualTo(Double value) {
            addCriterion("TURNOVER_RATE <=", value, "turnoverRate");
            return (Criteria) this;
        }

        public Criteria andTurnoverRateIn(List<Double> values) {
            addCriterion("TURNOVER_RATE in", values, "turnoverRate");
            return (Criteria) this;
        }

        public Criteria andTurnoverRateNotIn(List<Double> values) {
            addCriterion("TURNOVER_RATE not in", values, "turnoverRate");
            return (Criteria) this;
        }

        public Criteria andTurnoverRateBetween(Double value1, Double value2) {
            addCriterion("TURNOVER_RATE between", value1, value2, "turnoverRate");
            return (Criteria) this;
        }

        public Criteria andTurnoverRateNotBetween(Double value1, Double value2) {
            addCriterion("TURNOVER_RATE not between", value1, value2, "turnoverRate");
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