package com.st.framework.module.stock.example;

import com.st.framework.module.example.BaseExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class GDividendSchemeExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GDividendSchemeExample() {
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

        public Criteria andAnnouncementDateIsNull() {
            addCriterion("ANNOUNCEMENT_DATE is null");
            return (Criteria) this;
        }

        public Criteria andAnnouncementDateIsNotNull() {
            addCriterion("ANNOUNCEMENT_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andAnnouncementDateEqualTo(Date value) {
            addCriterionForJDBCDate("ANNOUNCEMENT_DATE =", value, "announcementDate");
            return (Criteria) this;
        }

        public Criteria andAnnouncementDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("ANNOUNCEMENT_DATE <>", value, "announcementDate");
            return (Criteria) this;
        }

        public Criteria andAnnouncementDateGreaterThan(Date value) {
            addCriterionForJDBCDate("ANNOUNCEMENT_DATE >", value, "announcementDate");
            return (Criteria) this;
        }

        public Criteria andAnnouncementDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ANNOUNCEMENT_DATE >=", value, "announcementDate");
            return (Criteria) this;
        }

        public Criteria andAnnouncementDateLessThan(Date value) {
            addCriterionForJDBCDate("ANNOUNCEMENT_DATE <", value, "announcementDate");
            return (Criteria) this;
        }

        public Criteria andAnnouncementDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ANNOUNCEMENT_DATE <=", value, "announcementDate");
            return (Criteria) this;
        }

        public Criteria andAnnouncementDateIn(List<Date> values) {
            addCriterionForJDBCDate("ANNOUNCEMENT_DATE in", values, "announcementDate");
            return (Criteria) this;
        }

        public Criteria andAnnouncementDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("ANNOUNCEMENT_DATE not in", values, "announcementDate");
            return (Criteria) this;
        }

        public Criteria andAnnouncementDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ANNOUNCEMENT_DATE between", value1, value2, "announcementDate");
            return (Criteria) this;
        }

        public Criteria andAnnouncementDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ANNOUNCEMENT_DATE not between", value1, value2, "announcementDate");
            return (Criteria) this;
        }

        public Criteria andSecuritiesIsNull() {
            addCriterion("SECURITIES is null");
            return (Criteria) this;
        }

        public Criteria andSecuritiesIsNotNull() {
            addCriterion("SECURITIES is not null");
            return (Criteria) this;
        }

        public Criteria andSecuritiesEqualTo(String value) {
            addCriterion("SECURITIES =", value, "securities");
            return (Criteria) this;
        }

        public Criteria andSecuritiesNotEqualTo(String value) {
            addCriterion("SECURITIES <>", value, "securities");
            return (Criteria) this;
        }

        public Criteria andSecuritiesGreaterThan(String value) {
            addCriterion("SECURITIES >", value, "securities");
            return (Criteria) this;
        }

        public Criteria andSecuritiesGreaterThanOrEqualTo(String value) {
            addCriterion("SECURITIES >=", value, "securities");
            return (Criteria) this;
        }

        public Criteria andSecuritiesLessThan(String value) {
            addCriterion("SECURITIES <", value, "securities");
            return (Criteria) this;
        }

        public Criteria andSecuritiesLessThanOrEqualTo(String value) {
            addCriterion("SECURITIES <=", value, "securities");
            return (Criteria) this;
        }

        public Criteria andSecuritiesLike(String value) {
            addCriterion("SECURITIES like", value, "securities");
            return (Criteria) this;
        }

        public Criteria andSecuritiesNotLike(String value) {
            addCriterion("SECURITIES not like", value, "securities");
            return (Criteria) this;
        }

        public Criteria andSecuritiesIn(List<String> values) {
            addCriterion("SECURITIES in", values, "securities");
            return (Criteria) this;
        }

        public Criteria andSecuritiesNotIn(List<String> values) {
            addCriterion("SECURITIES not in", values, "securities");
            return (Criteria) this;
        }

        public Criteria andSecuritiesBetween(String value1, String value2) {
            addCriterion("SECURITIES between", value1, value2, "securities");
            return (Criteria) this;
        }

        public Criteria andSecuritiesNotBetween(String value1, String value2) {
            addCriterion("SECURITIES not between", value1, value2, "securities");
            return (Criteria) this;
        }

        public Criteria andDeliveringIsNull() {
            addCriterion("DELIVERING is null");
            return (Criteria) this;
        }

        public Criteria andDeliveringIsNotNull() {
            addCriterion("DELIVERING is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveringEqualTo(Double value) {
            addCriterion("DELIVERING =", value, "delivering");
            return (Criteria) this;
        }

        public Criteria andDeliveringNotEqualTo(Double value) {
            addCriterion("DELIVERING <>", value, "delivering");
            return (Criteria) this;
        }

        public Criteria andDeliveringGreaterThan(Double value) {
            addCriterion("DELIVERING >", value, "delivering");
            return (Criteria) this;
        }

        public Criteria andDeliveringGreaterThanOrEqualTo(Double value) {
            addCriterion("DELIVERING >=", value, "delivering");
            return (Criteria) this;
        }

        public Criteria andDeliveringLessThan(Double value) {
            addCriterion("DELIVERING <", value, "delivering");
            return (Criteria) this;
        }

        public Criteria andDeliveringLessThanOrEqualTo(Double value) {
            addCriterion("DELIVERING <=", value, "delivering");
            return (Criteria) this;
        }

        public Criteria andDeliveringIn(List<Double> values) {
            addCriterion("DELIVERING in", values, "delivering");
            return (Criteria) this;
        }

        public Criteria andDeliveringNotIn(List<Double> values) {
            addCriterion("DELIVERING not in", values, "delivering");
            return (Criteria) this;
        }

        public Criteria andDeliveringBetween(Double value1, Double value2) {
            addCriterion("DELIVERING between", value1, value2, "delivering");
            return (Criteria) this;
        }

        public Criteria andDeliveringNotBetween(Double value1, Double value2) {
            addCriterion("DELIVERING not between", value1, value2, "delivering");
            return (Criteria) this;
        }

        public Criteria andTransferIsNull() {
            addCriterion("TRANSFER is null");
            return (Criteria) this;
        }

        public Criteria andTransferIsNotNull() {
            addCriterion("TRANSFER is not null");
            return (Criteria) this;
        }

        public Criteria andTransferEqualTo(Double value) {
            addCriterion("TRANSFER =", value, "transfer");
            return (Criteria) this;
        }

        public Criteria andTransferNotEqualTo(Double value) {
            addCriterion("TRANSFER <>", value, "transfer");
            return (Criteria) this;
        }

        public Criteria andTransferGreaterThan(Double value) {
            addCriterion("TRANSFER >", value, "transfer");
            return (Criteria) this;
        }

        public Criteria andTransferGreaterThanOrEqualTo(Double value) {
            addCriterion("TRANSFER >=", value, "transfer");
            return (Criteria) this;
        }

        public Criteria andTransferLessThan(Double value) {
            addCriterion("TRANSFER <", value, "transfer");
            return (Criteria) this;
        }

        public Criteria andTransferLessThanOrEqualTo(Double value) {
            addCriterion("TRANSFER <=", value, "transfer");
            return (Criteria) this;
        }

        public Criteria andTransferIn(List<Double> values) {
            addCriterion("TRANSFER in", values, "transfer");
            return (Criteria) this;
        }

        public Criteria andTransferNotIn(List<Double> values) {
            addCriterion("TRANSFER not in", values, "transfer");
            return (Criteria) this;
        }

        public Criteria andTransferBetween(Double value1, Double value2) {
            addCriterion("TRANSFER between", value1, value2, "transfer");
            return (Criteria) this;
        }

        public Criteria andTransferNotBetween(Double value1, Double value2) {
            addCriterion("TRANSFER not between", value1, value2, "transfer");
            return (Criteria) this;
        }

        public Criteria andDividendIsNull() {
            addCriterion("DIVIDEND is null");
            return (Criteria) this;
        }

        public Criteria andDividendIsNotNull() {
            addCriterion("DIVIDEND is not null");
            return (Criteria) this;
        }

        public Criteria andDividendEqualTo(Double value) {
            addCriterion("DIVIDEND =", value, "dividend");
            return (Criteria) this;
        }

        public Criteria andDividendNotEqualTo(Double value) {
            addCriterion("DIVIDEND <>", value, "dividend");
            return (Criteria) this;
        }

        public Criteria andDividendGreaterThan(Double value) {
            addCriterion("DIVIDEND >", value, "dividend");
            return (Criteria) this;
        }

        public Criteria andDividendGreaterThanOrEqualTo(Double value) {
            addCriterion("DIVIDEND >=", value, "dividend");
            return (Criteria) this;
        }

        public Criteria andDividendLessThan(Double value) {
            addCriterion("DIVIDEND <", value, "dividend");
            return (Criteria) this;
        }

        public Criteria andDividendLessThanOrEqualTo(Double value) {
            addCriterion("DIVIDEND <=", value, "dividend");
            return (Criteria) this;
        }

        public Criteria andDividendIn(List<Double> values) {
            addCriterion("DIVIDEND in", values, "dividend");
            return (Criteria) this;
        }

        public Criteria andDividendNotIn(List<Double> values) {
            addCriterion("DIVIDEND not in", values, "dividend");
            return (Criteria) this;
        }

        public Criteria andDividendBetween(Double value1, Double value2) {
            addCriterion("DIVIDEND between", value1, value2, "dividend");
            return (Criteria) this;
        }

        public Criteria andDividendNotBetween(Double value1, Double value2) {
            addCriterion("DIVIDEND not between", value1, value2, "dividend");
            return (Criteria) this;
        }

        public Criteria andScheduleIsNull() {
            addCriterion("SCHEDULE is null");
            return (Criteria) this;
        }

        public Criteria andScheduleIsNotNull() {
            addCriterion("SCHEDULE is not null");
            return (Criteria) this;
        }

        public Criteria andScheduleEqualTo(String value) {
            addCriterion("SCHEDULE =", value, "schedule");
            return (Criteria) this;
        }

        public Criteria andScheduleNotEqualTo(String value) {
            addCriterion("SCHEDULE <>", value, "schedule");
            return (Criteria) this;
        }

        public Criteria andScheduleGreaterThan(String value) {
            addCriterion("SCHEDULE >", value, "schedule");
            return (Criteria) this;
        }

        public Criteria andScheduleGreaterThanOrEqualTo(String value) {
            addCriterion("SCHEDULE >=", value, "schedule");
            return (Criteria) this;
        }

        public Criteria andScheduleLessThan(String value) {
            addCriterion("SCHEDULE <", value, "schedule");
            return (Criteria) this;
        }

        public Criteria andScheduleLessThanOrEqualTo(String value) {
            addCriterion("SCHEDULE <=", value, "schedule");
            return (Criteria) this;
        }

        public Criteria andScheduleLike(String value) {
            addCriterion("SCHEDULE like", value, "schedule");
            return (Criteria) this;
        }

        public Criteria andScheduleNotLike(String value) {
            addCriterion("SCHEDULE not like", value, "schedule");
            return (Criteria) this;
        }

        public Criteria andScheduleIn(List<String> values) {
            addCriterion("SCHEDULE in", values, "schedule");
            return (Criteria) this;
        }

        public Criteria andScheduleNotIn(List<String> values) {
            addCriterion("SCHEDULE not in", values, "schedule");
            return (Criteria) this;
        }

        public Criteria andScheduleBetween(String value1, String value2) {
            addCriterion("SCHEDULE between", value1, value2, "schedule");
            return (Criteria) this;
        }

        public Criteria andScheduleNotBetween(String value1, String value2) {
            addCriterion("SCHEDULE not between", value1, value2, "schedule");
            return (Criteria) this;
        }

        public Criteria andExDividendDateIsNull() {
            addCriterion("EX_DIVIDEND_DATE is null");
            return (Criteria) this;
        }

        public Criteria andExDividendDateIsNotNull() {
            addCriterion("EX_DIVIDEND_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andExDividendDateEqualTo(Date value) {
            addCriterionForJDBCDate("EX_DIVIDEND_DATE =", value, "exDividendDate");
            return (Criteria) this;
        }

        public Criteria andExDividendDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("EX_DIVIDEND_DATE <>", value, "exDividendDate");
            return (Criteria) this;
        }

        public Criteria andExDividendDateGreaterThan(Date value) {
            addCriterionForJDBCDate("EX_DIVIDEND_DATE >", value, "exDividendDate");
            return (Criteria) this;
        }

        public Criteria andExDividendDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("EX_DIVIDEND_DATE >=", value, "exDividendDate");
            return (Criteria) this;
        }

        public Criteria andExDividendDateLessThan(Date value) {
            addCriterionForJDBCDate("EX_DIVIDEND_DATE <", value, "exDividendDate");
            return (Criteria) this;
        }

        public Criteria andExDividendDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("EX_DIVIDEND_DATE <=", value, "exDividendDate");
            return (Criteria) this;
        }

        public Criteria andExDividendDateIn(List<Date> values) {
            addCriterionForJDBCDate("EX_DIVIDEND_DATE in", values, "exDividendDate");
            return (Criteria) this;
        }

        public Criteria andExDividendDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("EX_DIVIDEND_DATE not in", values, "exDividendDate");
            return (Criteria) this;
        }

        public Criteria andExDividendDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("EX_DIVIDEND_DATE between", value1, value2, "exDividendDate");
            return (Criteria) this;
        }

        public Criteria andExDividendDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("EX_DIVIDEND_DATE not between", value1, value2, "exDividendDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateIsNull() {
            addCriterion("RECORD_DATE is null");
            return (Criteria) this;
        }

        public Criteria andRecordDateIsNotNull() {
            addCriterion("RECORD_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andRecordDateEqualTo(Date value) {
            addCriterionForJDBCDate("RECORD_DATE =", value, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("RECORD_DATE <>", value, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateGreaterThan(Date value) {
            addCriterionForJDBCDate("RECORD_DATE >", value, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("RECORD_DATE >=", value, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateLessThan(Date value) {
            addCriterionForJDBCDate("RECORD_DATE <", value, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("RECORD_DATE <=", value, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateIn(List<Date> values) {
            addCriterionForJDBCDate("RECORD_DATE in", values, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("RECORD_DATE not in", values, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("RECORD_DATE between", value1, value2, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("RECORD_DATE not between", value1, value2, "recordDate");
            return (Criteria) this;
        }

        public Criteria andListingDateIsNull() {
            addCriterion("LISTING_DATE is null");
            return (Criteria) this;
        }

        public Criteria andListingDateIsNotNull() {
            addCriterion("LISTING_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andListingDateEqualTo(Date value) {
            addCriterionForJDBCDate("LISTING_DATE =", value, "listingDate");
            return (Criteria) this;
        }

        public Criteria andListingDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("LISTING_DATE <>", value, "listingDate");
            return (Criteria) this;
        }

        public Criteria andListingDateGreaterThan(Date value) {
            addCriterionForJDBCDate("LISTING_DATE >", value, "listingDate");
            return (Criteria) this;
        }

        public Criteria andListingDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("LISTING_DATE >=", value, "listingDate");
            return (Criteria) this;
        }

        public Criteria andListingDateLessThan(Date value) {
            addCriterionForJDBCDate("LISTING_DATE <", value, "listingDate");
            return (Criteria) this;
        }

        public Criteria andListingDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("LISTING_DATE <=", value, "listingDate");
            return (Criteria) this;
        }

        public Criteria andListingDateIn(List<Date> values) {
            addCriterionForJDBCDate("LISTING_DATE in", values, "listingDate");
            return (Criteria) this;
        }

        public Criteria andListingDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("LISTING_DATE not in", values, "listingDate");
            return (Criteria) this;
        }

        public Criteria andListingDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("LISTING_DATE between", value1, value2, "listingDate");
            return (Criteria) this;
        }

        public Criteria andListingDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("LISTING_DATE not between", value1, value2, "listingDate");
            return (Criteria) this;
        }

        public Criteria andPreTaxBonusIsNull() {
            addCriterion("PRE_TAX_BONUS is null");
            return (Criteria) this;
        }

        public Criteria andPreTaxBonusIsNotNull() {
            addCriterion("PRE_TAX_BONUS is not null");
            return (Criteria) this;
        }

        public Criteria andPreTaxBonusEqualTo(String value) {
            addCriterion("PRE_TAX_BONUS =", value, "preTaxBonus");
            return (Criteria) this;
        }

        public Criteria andPreTaxBonusNotEqualTo(String value) {
            addCriterion("PRE_TAX_BONUS <>", value, "preTaxBonus");
            return (Criteria) this;
        }

        public Criteria andPreTaxBonusGreaterThan(String value) {
            addCriterion("PRE_TAX_BONUS >", value, "preTaxBonus");
            return (Criteria) this;
        }

        public Criteria andPreTaxBonusGreaterThanOrEqualTo(String value) {
            addCriterion("PRE_TAX_BONUS >=", value, "preTaxBonus");
            return (Criteria) this;
        }

        public Criteria andPreTaxBonusLessThan(String value) {
            addCriterion("PRE_TAX_BONUS <", value, "preTaxBonus");
            return (Criteria) this;
        }

        public Criteria andPreTaxBonusLessThanOrEqualTo(String value) {
            addCriterion("PRE_TAX_BONUS <=", value, "preTaxBonus");
            return (Criteria) this;
        }

        public Criteria andPreTaxBonusLike(String value) {
            addCriterion("PRE_TAX_BONUS like", value, "preTaxBonus");
            return (Criteria) this;
        }

        public Criteria andPreTaxBonusNotLike(String value) {
            addCriterion("PRE_TAX_BONUS not like", value, "preTaxBonus");
            return (Criteria) this;
        }

        public Criteria andPreTaxBonusIn(List<String> values) {
            addCriterion("PRE_TAX_BONUS in", values, "preTaxBonus");
            return (Criteria) this;
        }

        public Criteria andPreTaxBonusNotIn(List<String> values) {
            addCriterion("PRE_TAX_BONUS not in", values, "preTaxBonus");
            return (Criteria) this;
        }

        public Criteria andPreTaxBonusBetween(String value1, String value2) {
            addCriterion("PRE_TAX_BONUS between", value1, value2, "preTaxBonus");
            return (Criteria) this;
        }

        public Criteria andPreTaxBonusNotBetween(String value1, String value2) {
            addCriterion("PRE_TAX_BONUS not between", value1, value2, "preTaxBonus");
            return (Criteria) this;
        }

        public Criteria andNonGappEarningIsNull() {
            addCriterion("NON_GAPP_EARNING is null");
            return (Criteria) this;
        }

        public Criteria andNonGappEarningIsNotNull() {
            addCriterion("NON_GAPP_EARNING is not null");
            return (Criteria) this;
        }

        public Criteria andNonGappEarningEqualTo(String value) {
            addCriterion("NON_GAPP_EARNING =", value, "nonGappEarning");
            return (Criteria) this;
        }

        public Criteria andNonGappEarningNotEqualTo(String value) {
            addCriterion("NON_GAPP_EARNING <>", value, "nonGappEarning");
            return (Criteria) this;
        }

        public Criteria andNonGappEarningGreaterThan(String value) {
            addCriterion("NON_GAPP_EARNING >", value, "nonGappEarning");
            return (Criteria) this;
        }

        public Criteria andNonGappEarningGreaterThanOrEqualTo(String value) {
            addCriterion("NON_GAPP_EARNING >=", value, "nonGappEarning");
            return (Criteria) this;
        }

        public Criteria andNonGappEarningLessThan(String value) {
            addCriterion("NON_GAPP_EARNING <", value, "nonGappEarning");
            return (Criteria) this;
        }

        public Criteria andNonGappEarningLessThanOrEqualTo(String value) {
            addCriterion("NON_GAPP_EARNING <=", value, "nonGappEarning");
            return (Criteria) this;
        }

        public Criteria andNonGappEarningLike(String value) {
            addCriterion("NON_GAPP_EARNING like", value, "nonGappEarning");
            return (Criteria) this;
        }

        public Criteria andNonGappEarningNotLike(String value) {
            addCriterion("NON_GAPP_EARNING not like", value, "nonGappEarning");
            return (Criteria) this;
        }

        public Criteria andNonGappEarningIn(List<String> values) {
            addCriterion("NON_GAPP_EARNING in", values, "nonGappEarning");
            return (Criteria) this;
        }

        public Criteria andNonGappEarningNotIn(List<String> values) {
            addCriterion("NON_GAPP_EARNING not in", values, "nonGappEarning");
            return (Criteria) this;
        }

        public Criteria andNonGappEarningBetween(String value1, String value2) {
            addCriterion("NON_GAPP_EARNING between", value1, value2, "nonGappEarning");
            return (Criteria) this;
        }

        public Criteria andNonGappEarningNotBetween(String value1, String value2) {
            addCriterion("NON_GAPP_EARNING not between", value1, value2, "nonGappEarning");
            return (Criteria) this;
        }

        public Criteria andBhSharesBeforeTaxDividendIsNull() {
            addCriterion("BH_SHARES_BEFORE_TAX_DIVIDEND is null");
            return (Criteria) this;
        }

        public Criteria andBhSharesBeforeTaxDividendIsNotNull() {
            addCriterion("BH_SHARES_BEFORE_TAX_DIVIDEND is not null");
            return (Criteria) this;
        }

        public Criteria andBhSharesBeforeTaxDividendEqualTo(String value) {
            addCriterion("BH_SHARES_BEFORE_TAX_DIVIDEND =", value, "bhSharesBeforeTaxDividend");
            return (Criteria) this;
        }

        public Criteria andBhSharesBeforeTaxDividendNotEqualTo(String value) {
            addCriterion("BH_SHARES_BEFORE_TAX_DIVIDEND <>", value, "bhSharesBeforeTaxDividend");
            return (Criteria) this;
        }

        public Criteria andBhSharesBeforeTaxDividendGreaterThan(String value) {
            addCriterion("BH_SHARES_BEFORE_TAX_DIVIDEND >", value, "bhSharesBeforeTaxDividend");
            return (Criteria) this;
        }

        public Criteria andBhSharesBeforeTaxDividendGreaterThanOrEqualTo(String value) {
            addCriterion("BH_SHARES_BEFORE_TAX_DIVIDEND >=", value, "bhSharesBeforeTaxDividend");
            return (Criteria) this;
        }

        public Criteria andBhSharesBeforeTaxDividendLessThan(String value) {
            addCriterion("BH_SHARES_BEFORE_TAX_DIVIDEND <", value, "bhSharesBeforeTaxDividend");
            return (Criteria) this;
        }

        public Criteria andBhSharesBeforeTaxDividendLessThanOrEqualTo(String value) {
            addCriterion("BH_SHARES_BEFORE_TAX_DIVIDEND <=", value, "bhSharesBeforeTaxDividend");
            return (Criteria) this;
        }

        public Criteria andBhSharesBeforeTaxDividendLike(String value) {
            addCriterion("BH_SHARES_BEFORE_TAX_DIVIDEND like", value, "bhSharesBeforeTaxDividend");
            return (Criteria) this;
        }

        public Criteria andBhSharesBeforeTaxDividendNotLike(String value) {
            addCriterion("BH_SHARES_BEFORE_TAX_DIVIDEND not like", value, "bhSharesBeforeTaxDividend");
            return (Criteria) this;
        }

        public Criteria andBhSharesBeforeTaxDividendIn(List<String> values) {
            addCriterion("BH_SHARES_BEFORE_TAX_DIVIDEND in", values, "bhSharesBeforeTaxDividend");
            return (Criteria) this;
        }

        public Criteria andBhSharesBeforeTaxDividendNotIn(List<String> values) {
            addCriterion("BH_SHARES_BEFORE_TAX_DIVIDEND not in", values, "bhSharesBeforeTaxDividend");
            return (Criteria) this;
        }

        public Criteria andBhSharesBeforeTaxDividendBetween(String value1, String value2) {
            addCriterion("BH_SHARES_BEFORE_TAX_DIVIDEND between", value1, value2, "bhSharesBeforeTaxDividend");
            return (Criteria) this;
        }

        public Criteria andBhSharesBeforeTaxDividendNotBetween(String value1, String value2) {
            addCriterion("BH_SHARES_BEFORE_TAX_DIVIDEND not between", value1, value2, "bhSharesBeforeTaxDividend");
            return (Criteria) this;
        }

        public Criteria andBhSharesAfterTaxDividendIsNull() {
            addCriterion("BH_SHARES_AFTER_TAX_DIVIDEND is null");
            return (Criteria) this;
        }

        public Criteria andBhSharesAfterTaxDividendIsNotNull() {
            addCriterion("BH_SHARES_AFTER_TAX_DIVIDEND is not null");
            return (Criteria) this;
        }

        public Criteria andBhSharesAfterTaxDividendEqualTo(String value) {
            addCriterion("BH_SHARES_AFTER_TAX_DIVIDEND =", value, "bhSharesAfterTaxDividend");
            return (Criteria) this;
        }

        public Criteria andBhSharesAfterTaxDividendNotEqualTo(String value) {
            addCriterion("BH_SHARES_AFTER_TAX_DIVIDEND <>", value, "bhSharesAfterTaxDividend");
            return (Criteria) this;
        }

        public Criteria andBhSharesAfterTaxDividendGreaterThan(String value) {
            addCriterion("BH_SHARES_AFTER_TAX_DIVIDEND >", value, "bhSharesAfterTaxDividend");
            return (Criteria) this;
        }

        public Criteria andBhSharesAfterTaxDividendGreaterThanOrEqualTo(String value) {
            addCriterion("BH_SHARES_AFTER_TAX_DIVIDEND >=", value, "bhSharesAfterTaxDividend");
            return (Criteria) this;
        }

        public Criteria andBhSharesAfterTaxDividendLessThan(String value) {
            addCriterion("BH_SHARES_AFTER_TAX_DIVIDEND <", value, "bhSharesAfterTaxDividend");
            return (Criteria) this;
        }

        public Criteria andBhSharesAfterTaxDividendLessThanOrEqualTo(String value) {
            addCriterion("BH_SHARES_AFTER_TAX_DIVIDEND <=", value, "bhSharesAfterTaxDividend");
            return (Criteria) this;
        }

        public Criteria andBhSharesAfterTaxDividendLike(String value) {
            addCriterion("BH_SHARES_AFTER_TAX_DIVIDEND like", value, "bhSharesAfterTaxDividend");
            return (Criteria) this;
        }

        public Criteria andBhSharesAfterTaxDividendNotLike(String value) {
            addCriterion("BH_SHARES_AFTER_TAX_DIVIDEND not like", value, "bhSharesAfterTaxDividend");
            return (Criteria) this;
        }

        public Criteria andBhSharesAfterTaxDividendIn(List<String> values) {
            addCriterion("BH_SHARES_AFTER_TAX_DIVIDEND in", values, "bhSharesAfterTaxDividend");
            return (Criteria) this;
        }

        public Criteria andBhSharesAfterTaxDividendNotIn(List<String> values) {
            addCriterion("BH_SHARES_AFTER_TAX_DIVIDEND not in", values, "bhSharesAfterTaxDividend");
            return (Criteria) this;
        }

        public Criteria andBhSharesAfterTaxDividendBetween(String value1, String value2) {
            addCriterion("BH_SHARES_AFTER_TAX_DIVIDEND between", value1, value2, "bhSharesAfterTaxDividend");
            return (Criteria) this;
        }

        public Criteria andBhSharesAfterTaxDividendNotBetween(String value1, String value2) {
            addCriterion("BH_SHARES_AFTER_TAX_DIVIDEND not between", value1, value2, "bhSharesAfterTaxDividend");
            return (Criteria) this;
        }

        public Criteria andProportionBonusIsNull() {
            addCriterion("PROPORTION_BONUS is null");
            return (Criteria) this;
        }

        public Criteria andProportionBonusIsNotNull() {
            addCriterion("PROPORTION_BONUS is not null");
            return (Criteria) this;
        }

        public Criteria andProportionBonusEqualTo(String value) {
            addCriterion("PROPORTION_BONUS =", value, "proportionBonus");
            return (Criteria) this;
        }

        public Criteria andProportionBonusNotEqualTo(String value) {
            addCriterion("PROPORTION_BONUS <>", value, "proportionBonus");
            return (Criteria) this;
        }

        public Criteria andProportionBonusGreaterThan(String value) {
            addCriterion("PROPORTION_BONUS >", value, "proportionBonus");
            return (Criteria) this;
        }

        public Criteria andProportionBonusGreaterThanOrEqualTo(String value) {
            addCriterion("PROPORTION_BONUS >=", value, "proportionBonus");
            return (Criteria) this;
        }

        public Criteria andProportionBonusLessThan(String value) {
            addCriterion("PROPORTION_BONUS <", value, "proportionBonus");
            return (Criteria) this;
        }

        public Criteria andProportionBonusLessThanOrEqualTo(String value) {
            addCriterion("PROPORTION_BONUS <=", value, "proportionBonus");
            return (Criteria) this;
        }

        public Criteria andProportionBonusLike(String value) {
            addCriterion("PROPORTION_BONUS like", value, "proportionBonus");
            return (Criteria) this;
        }

        public Criteria andProportionBonusNotLike(String value) {
            addCriterion("PROPORTION_BONUS not like", value, "proportionBonus");
            return (Criteria) this;
        }

        public Criteria andProportionBonusIn(List<String> values) {
            addCriterion("PROPORTION_BONUS in", values, "proportionBonus");
            return (Criteria) this;
        }

        public Criteria andProportionBonusNotIn(List<String> values) {
            addCriterion("PROPORTION_BONUS not in", values, "proportionBonus");
            return (Criteria) this;
        }

        public Criteria andProportionBonusBetween(String value1, String value2) {
            addCriterion("PROPORTION_BONUS between", value1, value2, "proportionBonus");
            return (Criteria) this;
        }

        public Criteria andProportionBonusNotBetween(String value1, String value2) {
            addCriterion("PROPORTION_BONUS not between", value1, value2, "proportionBonus");
            return (Criteria) this;
        }

        public Criteria andCapitalizationRatioIsNull() {
            addCriterion("CAPITALIZATION_RATIO is null");
            return (Criteria) this;
        }

        public Criteria andCapitalizationRatioIsNotNull() {
            addCriterion("CAPITALIZATION_RATIO is not null");
            return (Criteria) this;
        }

        public Criteria andCapitalizationRatioEqualTo(String value) {
            addCriterion("CAPITALIZATION_RATIO =", value, "capitalizationRatio");
            return (Criteria) this;
        }

        public Criteria andCapitalizationRatioNotEqualTo(String value) {
            addCriterion("CAPITALIZATION_RATIO <>", value, "capitalizationRatio");
            return (Criteria) this;
        }

        public Criteria andCapitalizationRatioGreaterThan(String value) {
            addCriterion("CAPITALIZATION_RATIO >", value, "capitalizationRatio");
            return (Criteria) this;
        }

        public Criteria andCapitalizationRatioGreaterThanOrEqualTo(String value) {
            addCriterion("CAPITALIZATION_RATIO >=", value, "capitalizationRatio");
            return (Criteria) this;
        }

        public Criteria andCapitalizationRatioLessThan(String value) {
            addCriterion("CAPITALIZATION_RATIO <", value, "capitalizationRatio");
            return (Criteria) this;
        }

        public Criteria andCapitalizationRatioLessThanOrEqualTo(String value) {
            addCriterion("CAPITALIZATION_RATIO <=", value, "capitalizationRatio");
            return (Criteria) this;
        }

        public Criteria andCapitalizationRatioLike(String value) {
            addCriterion("CAPITALIZATION_RATIO like", value, "capitalizationRatio");
            return (Criteria) this;
        }

        public Criteria andCapitalizationRatioNotLike(String value) {
            addCriterion("CAPITALIZATION_RATIO not like", value, "capitalizationRatio");
            return (Criteria) this;
        }

        public Criteria andCapitalizationRatioIn(List<String> values) {
            addCriterion("CAPITALIZATION_RATIO in", values, "capitalizationRatio");
            return (Criteria) this;
        }

        public Criteria andCapitalizationRatioNotIn(List<String> values) {
            addCriterion("CAPITALIZATION_RATIO not in", values, "capitalizationRatio");
            return (Criteria) this;
        }

        public Criteria andCapitalizationRatioBetween(String value1, String value2) {
            addCriterion("CAPITALIZATION_RATIO between", value1, value2, "capitalizationRatio");
            return (Criteria) this;
        }

        public Criteria andCapitalizationRatioNotBetween(String value1, String value2) {
            addCriterion("CAPITALIZATION_RATIO not between", value1, value2, "capitalizationRatio");
            return (Criteria) this;
        }

        public Criteria andSurplusReserveRatioIsNull() {
            addCriterion("SURPLUS_RESERVE_RATIO is null");
            return (Criteria) this;
        }

        public Criteria andSurplusReserveRatioIsNotNull() {
            addCriterion("SURPLUS_RESERVE_RATIO is not null");
            return (Criteria) this;
        }

        public Criteria andSurplusReserveRatioEqualTo(String value) {
            addCriterion("SURPLUS_RESERVE_RATIO =", value, "surplusReserveRatio");
            return (Criteria) this;
        }

        public Criteria andSurplusReserveRatioNotEqualTo(String value) {
            addCriterion("SURPLUS_RESERVE_RATIO <>", value, "surplusReserveRatio");
            return (Criteria) this;
        }

        public Criteria andSurplusReserveRatioGreaterThan(String value) {
            addCriterion("SURPLUS_RESERVE_RATIO >", value, "surplusReserveRatio");
            return (Criteria) this;
        }

        public Criteria andSurplusReserveRatioGreaterThanOrEqualTo(String value) {
            addCriterion("SURPLUS_RESERVE_RATIO >=", value, "surplusReserveRatio");
            return (Criteria) this;
        }

        public Criteria andSurplusReserveRatioLessThan(String value) {
            addCriterion("SURPLUS_RESERVE_RATIO <", value, "surplusReserveRatio");
            return (Criteria) this;
        }

        public Criteria andSurplusReserveRatioLessThanOrEqualTo(String value) {
            addCriterion("SURPLUS_RESERVE_RATIO <=", value, "surplusReserveRatio");
            return (Criteria) this;
        }

        public Criteria andSurplusReserveRatioLike(String value) {
            addCriterion("SURPLUS_RESERVE_RATIO like", value, "surplusReserveRatio");
            return (Criteria) this;
        }

        public Criteria andSurplusReserveRatioNotLike(String value) {
            addCriterion("SURPLUS_RESERVE_RATIO not like", value, "surplusReserveRatio");
            return (Criteria) this;
        }

        public Criteria andSurplusReserveRatioIn(List<String> values) {
            addCriterion("SURPLUS_RESERVE_RATIO in", values, "surplusReserveRatio");
            return (Criteria) this;
        }

        public Criteria andSurplusReserveRatioNotIn(List<String> values) {
            addCriterion("SURPLUS_RESERVE_RATIO not in", values, "surplusReserveRatio");
            return (Criteria) this;
        }

        public Criteria andSurplusReserveRatioBetween(String value1, String value2) {
            addCriterion("SURPLUS_RESERVE_RATIO between", value1, value2, "surplusReserveRatio");
            return (Criteria) this;
        }

        public Criteria andSurplusReserveRatioNotBetween(String value1, String value2) {
            addCriterion("SURPLUS_RESERVE_RATIO not between", value1, value2, "surplusReserveRatio");
            return (Criteria) this;
        }

        public Criteria andCapitalReserveRatioIsNull() {
            addCriterion("CAPITAL_RESERVE_RATIO is null");
            return (Criteria) this;
        }

        public Criteria andCapitalReserveRatioIsNotNull() {
            addCriterion("CAPITAL_RESERVE_RATIO is not null");
            return (Criteria) this;
        }

        public Criteria andCapitalReserveRatioEqualTo(String value) {
            addCriterion("CAPITAL_RESERVE_RATIO =", value, "capitalReserveRatio");
            return (Criteria) this;
        }

        public Criteria andCapitalReserveRatioNotEqualTo(String value) {
            addCriterion("CAPITAL_RESERVE_RATIO <>", value, "capitalReserveRatio");
            return (Criteria) this;
        }

        public Criteria andCapitalReserveRatioGreaterThan(String value) {
            addCriterion("CAPITAL_RESERVE_RATIO >", value, "capitalReserveRatio");
            return (Criteria) this;
        }

        public Criteria andCapitalReserveRatioGreaterThanOrEqualTo(String value) {
            addCriterion("CAPITAL_RESERVE_RATIO >=", value, "capitalReserveRatio");
            return (Criteria) this;
        }

        public Criteria andCapitalReserveRatioLessThan(String value) {
            addCriterion("CAPITAL_RESERVE_RATIO <", value, "capitalReserveRatio");
            return (Criteria) this;
        }

        public Criteria andCapitalReserveRatioLessThanOrEqualTo(String value) {
            addCriterion("CAPITAL_RESERVE_RATIO <=", value, "capitalReserveRatio");
            return (Criteria) this;
        }

        public Criteria andCapitalReserveRatioLike(String value) {
            addCriterion("CAPITAL_RESERVE_RATIO like", value, "capitalReserveRatio");
            return (Criteria) this;
        }

        public Criteria andCapitalReserveRatioNotLike(String value) {
            addCriterion("CAPITAL_RESERVE_RATIO not like", value, "capitalReserveRatio");
            return (Criteria) this;
        }

        public Criteria andCapitalReserveRatioIn(List<String> values) {
            addCriterion("CAPITAL_RESERVE_RATIO in", values, "capitalReserveRatio");
            return (Criteria) this;
        }

        public Criteria andCapitalReserveRatioNotIn(List<String> values) {
            addCriterion("CAPITAL_RESERVE_RATIO not in", values, "capitalReserveRatio");
            return (Criteria) this;
        }

        public Criteria andCapitalReserveRatioBetween(String value1, String value2) {
            addCriterion("CAPITAL_RESERVE_RATIO between", value1, value2, "capitalReserveRatio");
            return (Criteria) this;
        }

        public Criteria andCapitalReserveRatioNotBetween(String value1, String value2) {
            addCriterion("CAPITAL_RESERVE_RATIO not between", value1, value2, "capitalReserveRatio");
            return (Criteria) this;
        }

        public Criteria andIssuanceTargetIsNull() {
            addCriterion("ISSUANCE_TARGET is null");
            return (Criteria) this;
        }

        public Criteria andIssuanceTargetIsNotNull() {
            addCriterion("ISSUANCE_TARGET is not null");
            return (Criteria) this;
        }

        public Criteria andIssuanceTargetEqualTo(String value) {
            addCriterion("ISSUANCE_TARGET =", value, "issuanceTarget");
            return (Criteria) this;
        }

        public Criteria andIssuanceTargetNotEqualTo(String value) {
            addCriterion("ISSUANCE_TARGET <>", value, "issuanceTarget");
            return (Criteria) this;
        }

        public Criteria andIssuanceTargetGreaterThan(String value) {
            addCriterion("ISSUANCE_TARGET >", value, "issuanceTarget");
            return (Criteria) this;
        }

        public Criteria andIssuanceTargetGreaterThanOrEqualTo(String value) {
            addCriterion("ISSUANCE_TARGET >=", value, "issuanceTarget");
            return (Criteria) this;
        }

        public Criteria andIssuanceTargetLessThan(String value) {
            addCriterion("ISSUANCE_TARGET <", value, "issuanceTarget");
            return (Criteria) this;
        }

        public Criteria andIssuanceTargetLessThanOrEqualTo(String value) {
            addCriterion("ISSUANCE_TARGET <=", value, "issuanceTarget");
            return (Criteria) this;
        }

        public Criteria andIssuanceTargetLike(String value) {
            addCriterion("ISSUANCE_TARGET like", value, "issuanceTarget");
            return (Criteria) this;
        }

        public Criteria andIssuanceTargetNotLike(String value) {
            addCriterion("ISSUANCE_TARGET not like", value, "issuanceTarget");
            return (Criteria) this;
        }

        public Criteria andIssuanceTargetIn(List<String> values) {
            addCriterion("ISSUANCE_TARGET in", values, "issuanceTarget");
            return (Criteria) this;
        }

        public Criteria andIssuanceTargetNotIn(List<String> values) {
            addCriterion("ISSUANCE_TARGET not in", values, "issuanceTarget");
            return (Criteria) this;
        }

        public Criteria andIssuanceTargetBetween(String value1, String value2) {
            addCriterion("ISSUANCE_TARGET between", value1, value2, "issuanceTarget");
            return (Criteria) this;
        }

        public Criteria andIssuanceTargetNotBetween(String value1, String value2) {
            addCriterion("ISSUANCE_TARGET not between", value1, value2, "issuanceTarget");
            return (Criteria) this;
        }

        public Criteria andEquityReferenceDayIsNull() {
            addCriterion("EQUITY_REFERENCE_DAY is null");
            return (Criteria) this;
        }

        public Criteria andEquityReferenceDayIsNotNull() {
            addCriterion("EQUITY_REFERENCE_DAY is not null");
            return (Criteria) this;
        }

        public Criteria andEquityReferenceDayEqualTo(String value) {
            addCriterion("EQUITY_REFERENCE_DAY =", value, "equityReferenceDay");
            return (Criteria) this;
        }

        public Criteria andEquityReferenceDayNotEqualTo(String value) {
            addCriterion("EQUITY_REFERENCE_DAY <>", value, "equityReferenceDay");
            return (Criteria) this;
        }

        public Criteria andEquityReferenceDayGreaterThan(String value) {
            addCriterion("EQUITY_REFERENCE_DAY >", value, "equityReferenceDay");
            return (Criteria) this;
        }

        public Criteria andEquityReferenceDayGreaterThanOrEqualTo(String value) {
            addCriterion("EQUITY_REFERENCE_DAY >=", value, "equityReferenceDay");
            return (Criteria) this;
        }

        public Criteria andEquityReferenceDayLessThan(String value) {
            addCriterion("EQUITY_REFERENCE_DAY <", value, "equityReferenceDay");
            return (Criteria) this;
        }

        public Criteria andEquityReferenceDayLessThanOrEqualTo(String value) {
            addCriterion("EQUITY_REFERENCE_DAY <=", value, "equityReferenceDay");
            return (Criteria) this;
        }

        public Criteria andEquityReferenceDayLike(String value) {
            addCriterion("EQUITY_REFERENCE_DAY like", value, "equityReferenceDay");
            return (Criteria) this;
        }

        public Criteria andEquityReferenceDayNotLike(String value) {
            addCriterion("EQUITY_REFERENCE_DAY not like", value, "equityReferenceDay");
            return (Criteria) this;
        }

        public Criteria andEquityReferenceDayIn(List<String> values) {
            addCriterion("EQUITY_REFERENCE_DAY in", values, "equityReferenceDay");
            return (Criteria) this;
        }

        public Criteria andEquityReferenceDayNotIn(List<String> values) {
            addCriterion("EQUITY_REFERENCE_DAY not in", values, "equityReferenceDay");
            return (Criteria) this;
        }

        public Criteria andEquityReferenceDayBetween(String value1, String value2) {
            addCriterion("EQUITY_REFERENCE_DAY between", value1, value2, "equityReferenceDay");
            return (Criteria) this;
        }

        public Criteria andEquityReferenceDayNotBetween(String value1, String value2) {
            addCriterion("EQUITY_REFERENCE_DAY not between", value1, value2, "equityReferenceDay");
            return (Criteria) this;
        }

        public Criteria andLastTradingDayIsNull() {
            addCriterion("LAST_TRADING_DAY is null");
            return (Criteria) this;
        }

        public Criteria andLastTradingDayIsNotNull() {
            addCriterion("LAST_TRADING_DAY is not null");
            return (Criteria) this;
        }

        public Criteria andLastTradingDayEqualTo(String value) {
            addCriterion("LAST_TRADING_DAY =", value, "lastTradingDay");
            return (Criteria) this;
        }

        public Criteria andLastTradingDayNotEqualTo(String value) {
            addCriterion("LAST_TRADING_DAY <>", value, "lastTradingDay");
            return (Criteria) this;
        }

        public Criteria andLastTradingDayGreaterThan(String value) {
            addCriterion("LAST_TRADING_DAY >", value, "lastTradingDay");
            return (Criteria) this;
        }

        public Criteria andLastTradingDayGreaterThanOrEqualTo(String value) {
            addCriterion("LAST_TRADING_DAY >=", value, "lastTradingDay");
            return (Criteria) this;
        }

        public Criteria andLastTradingDayLessThan(String value) {
            addCriterion("LAST_TRADING_DAY <", value, "lastTradingDay");
            return (Criteria) this;
        }

        public Criteria andLastTradingDayLessThanOrEqualTo(String value) {
            addCriterion("LAST_TRADING_DAY <=", value, "lastTradingDay");
            return (Criteria) this;
        }

        public Criteria andLastTradingDayLike(String value) {
            addCriterion("LAST_TRADING_DAY like", value, "lastTradingDay");
            return (Criteria) this;
        }

        public Criteria andLastTradingDayNotLike(String value) {
            addCriterion("LAST_TRADING_DAY not like", value, "lastTradingDay");
            return (Criteria) this;
        }

        public Criteria andLastTradingDayIn(List<String> values) {
            addCriterion("LAST_TRADING_DAY in", values, "lastTradingDay");
            return (Criteria) this;
        }

        public Criteria andLastTradingDayNotIn(List<String> values) {
            addCriterion("LAST_TRADING_DAY not in", values, "lastTradingDay");
            return (Criteria) this;
        }

        public Criteria andLastTradingDayBetween(String value1, String value2) {
            addCriterion("LAST_TRADING_DAY between", value1, value2, "lastTradingDay");
            return (Criteria) this;
        }

        public Criteria andLastTradingDayNotBetween(String value1, String value2) {
            addCriterion("LAST_TRADING_DAY not between", value1, value2, "lastTradingDay");
            return (Criteria) this;
        }

        public Criteria andArrivalDateIsNull() {
            addCriterion("ARRIVAL_DATE is null");
            return (Criteria) this;
        }

        public Criteria andArrivalDateIsNotNull() {
            addCriterion("ARRIVAL_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andArrivalDateEqualTo(String value) {
            addCriterion("ARRIVAL_DATE =", value, "arrivalDate");
            return (Criteria) this;
        }

        public Criteria andArrivalDateNotEqualTo(String value) {
            addCriterion("ARRIVAL_DATE <>", value, "arrivalDate");
            return (Criteria) this;
        }

        public Criteria andArrivalDateGreaterThan(String value) {
            addCriterion("ARRIVAL_DATE >", value, "arrivalDate");
            return (Criteria) this;
        }

        public Criteria andArrivalDateGreaterThanOrEqualTo(String value) {
            addCriterion("ARRIVAL_DATE >=", value, "arrivalDate");
            return (Criteria) this;
        }

        public Criteria andArrivalDateLessThan(String value) {
            addCriterion("ARRIVAL_DATE <", value, "arrivalDate");
            return (Criteria) this;
        }

        public Criteria andArrivalDateLessThanOrEqualTo(String value) {
            addCriterion("ARRIVAL_DATE <=", value, "arrivalDate");
            return (Criteria) this;
        }

        public Criteria andArrivalDateLike(String value) {
            addCriterion("ARRIVAL_DATE like", value, "arrivalDate");
            return (Criteria) this;
        }

        public Criteria andArrivalDateNotLike(String value) {
            addCriterion("ARRIVAL_DATE not like", value, "arrivalDate");
            return (Criteria) this;
        }

        public Criteria andArrivalDateIn(List<String> values) {
            addCriterion("ARRIVAL_DATE in", values, "arrivalDate");
            return (Criteria) this;
        }

        public Criteria andArrivalDateNotIn(List<String> values) {
            addCriterion("ARRIVAL_DATE not in", values, "arrivalDate");
            return (Criteria) this;
        }

        public Criteria andArrivalDateBetween(String value1, String value2) {
            addCriterion("ARRIVAL_DATE between", value1, value2, "arrivalDate");
            return (Criteria) this;
        }

        public Criteria andArrivalDateNotBetween(String value1, String value2) {
            addCriterion("ARRIVAL_DATE not between", value1, value2, "arrivalDate");
            return (Criteria) this;
        }

        public Criteria andDividendDateOfTerminationIsNull() {
            addCriterion("DIVIDEND_DATE_OF_TERMINATION is null");
            return (Criteria) this;
        }

        public Criteria andDividendDateOfTerminationIsNotNull() {
            addCriterion("DIVIDEND_DATE_OF_TERMINATION is not null");
            return (Criteria) this;
        }

        public Criteria andDividendDateOfTerminationEqualTo(String value) {
            addCriterion("DIVIDEND_DATE_OF_TERMINATION =", value, "dividendDateOfTermination");
            return (Criteria) this;
        }

        public Criteria andDividendDateOfTerminationNotEqualTo(String value) {
            addCriterion("DIVIDEND_DATE_OF_TERMINATION <>", value, "dividendDateOfTermination");
            return (Criteria) this;
        }

        public Criteria andDividendDateOfTerminationGreaterThan(String value) {
            addCriterion("DIVIDEND_DATE_OF_TERMINATION >", value, "dividendDateOfTermination");
            return (Criteria) this;
        }

        public Criteria andDividendDateOfTerminationGreaterThanOrEqualTo(String value) {
            addCriterion("DIVIDEND_DATE_OF_TERMINATION >=", value, "dividendDateOfTermination");
            return (Criteria) this;
        }

        public Criteria andDividendDateOfTerminationLessThan(String value) {
            addCriterion("DIVIDEND_DATE_OF_TERMINATION <", value, "dividendDateOfTermination");
            return (Criteria) this;
        }

        public Criteria andDividendDateOfTerminationLessThanOrEqualTo(String value) {
            addCriterion("DIVIDEND_DATE_OF_TERMINATION <=", value, "dividendDateOfTermination");
            return (Criteria) this;
        }

        public Criteria andDividendDateOfTerminationLike(String value) {
            addCriterion("DIVIDEND_DATE_OF_TERMINATION like", value, "dividendDateOfTermination");
            return (Criteria) this;
        }

        public Criteria andDividendDateOfTerminationNotLike(String value) {
            addCriterion("DIVIDEND_DATE_OF_TERMINATION not like", value, "dividendDateOfTermination");
            return (Criteria) this;
        }

        public Criteria andDividendDateOfTerminationIn(List<String> values) {
            addCriterion("DIVIDEND_DATE_OF_TERMINATION in", values, "dividendDateOfTermination");
            return (Criteria) this;
        }

        public Criteria andDividendDateOfTerminationNotIn(List<String> values) {
            addCriterion("DIVIDEND_DATE_OF_TERMINATION not in", values, "dividendDateOfTermination");
            return (Criteria) this;
        }

        public Criteria andDividendDateOfTerminationBetween(String value1, String value2) {
            addCriterion("DIVIDEND_DATE_OF_TERMINATION between", value1, value2, "dividendDateOfTermination");
            return (Criteria) this;
        }

        public Criteria andDividendDateOfTerminationNotBetween(String value1, String value2) {
            addCriterion("DIVIDEND_DATE_OF_TERMINATION not between", value1, value2, "dividendDateOfTermination");
            return (Criteria) this;
        }

        public Criteria andMeetingAnnouncementDateIsNull() {
            addCriterion("MEETING_ANNOUNCEMENT_DATE is null");
            return (Criteria) this;
        }

        public Criteria andMeetingAnnouncementDateIsNotNull() {
            addCriterion("MEETING_ANNOUNCEMENT_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andMeetingAnnouncementDateEqualTo(String value) {
            addCriterion("MEETING_ANNOUNCEMENT_DATE =", value, "meetingAnnouncementDate");
            return (Criteria) this;
        }

        public Criteria andMeetingAnnouncementDateNotEqualTo(String value) {
            addCriterion("MEETING_ANNOUNCEMENT_DATE <>", value, "meetingAnnouncementDate");
            return (Criteria) this;
        }

        public Criteria andMeetingAnnouncementDateGreaterThan(String value) {
            addCriterion("MEETING_ANNOUNCEMENT_DATE >", value, "meetingAnnouncementDate");
            return (Criteria) this;
        }

        public Criteria andMeetingAnnouncementDateGreaterThanOrEqualTo(String value) {
            addCriterion("MEETING_ANNOUNCEMENT_DATE >=", value, "meetingAnnouncementDate");
            return (Criteria) this;
        }

        public Criteria andMeetingAnnouncementDateLessThan(String value) {
            addCriterion("MEETING_ANNOUNCEMENT_DATE <", value, "meetingAnnouncementDate");
            return (Criteria) this;
        }

        public Criteria andMeetingAnnouncementDateLessThanOrEqualTo(String value) {
            addCriterion("MEETING_ANNOUNCEMENT_DATE <=", value, "meetingAnnouncementDate");
            return (Criteria) this;
        }

        public Criteria andMeetingAnnouncementDateLike(String value) {
            addCriterion("MEETING_ANNOUNCEMENT_DATE like", value, "meetingAnnouncementDate");
            return (Criteria) this;
        }

        public Criteria andMeetingAnnouncementDateNotLike(String value) {
            addCriterion("MEETING_ANNOUNCEMENT_DATE not like", value, "meetingAnnouncementDate");
            return (Criteria) this;
        }

        public Criteria andMeetingAnnouncementDateIn(List<String> values) {
            addCriterion("MEETING_ANNOUNCEMENT_DATE in", values, "meetingAnnouncementDate");
            return (Criteria) this;
        }

        public Criteria andMeetingAnnouncementDateNotIn(List<String> values) {
            addCriterion("MEETING_ANNOUNCEMENT_DATE not in", values, "meetingAnnouncementDate");
            return (Criteria) this;
        }

        public Criteria andMeetingAnnouncementDateBetween(String value1, String value2) {
            addCriterion("MEETING_ANNOUNCEMENT_DATE between", value1, value2, "meetingAnnouncementDate");
            return (Criteria) this;
        }

        public Criteria andMeetingAnnouncementDateNotBetween(String value1, String value2) {
            addCriterion("MEETING_ANNOUNCEMENT_DATE not between", value1, value2, "meetingAnnouncementDate");
            return (Criteria) this;
        }

        public Criteria andRightsDeadlineIsNull() {
            addCriterion("RIGHTS_DEADLINE is null");
            return (Criteria) this;
        }

        public Criteria andRightsDeadlineIsNotNull() {
            addCriterion("RIGHTS_DEADLINE is not null");
            return (Criteria) this;
        }

        public Criteria andRightsDeadlineEqualTo(String value) {
            addCriterion("RIGHTS_DEADLINE =", value, "rightsDeadline");
            return (Criteria) this;
        }

        public Criteria andRightsDeadlineNotEqualTo(String value) {
            addCriterion("RIGHTS_DEADLINE <>", value, "rightsDeadline");
            return (Criteria) this;
        }

        public Criteria andRightsDeadlineGreaterThan(String value) {
            addCriterion("RIGHTS_DEADLINE >", value, "rightsDeadline");
            return (Criteria) this;
        }

        public Criteria andRightsDeadlineGreaterThanOrEqualTo(String value) {
            addCriterion("RIGHTS_DEADLINE >=", value, "rightsDeadline");
            return (Criteria) this;
        }

        public Criteria andRightsDeadlineLessThan(String value) {
            addCriterion("RIGHTS_DEADLINE <", value, "rightsDeadline");
            return (Criteria) this;
        }

        public Criteria andRightsDeadlineLessThanOrEqualTo(String value) {
            addCriterion("RIGHTS_DEADLINE <=", value, "rightsDeadline");
            return (Criteria) this;
        }

        public Criteria andRightsDeadlineLike(String value) {
            addCriterion("RIGHTS_DEADLINE like", value, "rightsDeadline");
            return (Criteria) this;
        }

        public Criteria andRightsDeadlineNotLike(String value) {
            addCriterion("RIGHTS_DEADLINE not like", value, "rightsDeadline");
            return (Criteria) this;
        }

        public Criteria andRightsDeadlineIn(List<String> values) {
            addCriterion("RIGHTS_DEADLINE in", values, "rightsDeadline");
            return (Criteria) this;
        }

        public Criteria andRightsDeadlineNotIn(List<String> values) {
            addCriterion("RIGHTS_DEADLINE not in", values, "rightsDeadline");
            return (Criteria) this;
        }

        public Criteria andRightsDeadlineBetween(String value1, String value2) {
            addCriterion("RIGHTS_DEADLINE between", value1, value2, "rightsDeadline");
            return (Criteria) this;
        }

        public Criteria andRightsDeadlineNotBetween(String value1, String value2) {
            addCriterion("RIGHTS_DEADLINE not between", value1, value2, "rightsDeadline");
            return (Criteria) this;
        }

        public Criteria andAllotmentRatioIsNull() {
            addCriterion("ALLOTMENT_RATIO is null");
            return (Criteria) this;
        }

        public Criteria andAllotmentRatioIsNotNull() {
            addCriterion("ALLOTMENT_RATIO is not null");
            return (Criteria) this;
        }

        public Criteria andAllotmentRatioEqualTo(String value) {
            addCriterion("ALLOTMENT_RATIO =", value, "allotmentRatio");
            return (Criteria) this;
        }

        public Criteria andAllotmentRatioNotEqualTo(String value) {
            addCriterion("ALLOTMENT_RATIO <>", value, "allotmentRatio");
            return (Criteria) this;
        }

        public Criteria andAllotmentRatioGreaterThan(String value) {
            addCriterion("ALLOTMENT_RATIO >", value, "allotmentRatio");
            return (Criteria) this;
        }

        public Criteria andAllotmentRatioGreaterThanOrEqualTo(String value) {
            addCriterion("ALLOTMENT_RATIO >=", value, "allotmentRatio");
            return (Criteria) this;
        }

        public Criteria andAllotmentRatioLessThan(String value) {
            addCriterion("ALLOTMENT_RATIO <", value, "allotmentRatio");
            return (Criteria) this;
        }

        public Criteria andAllotmentRatioLessThanOrEqualTo(String value) {
            addCriterion("ALLOTMENT_RATIO <=", value, "allotmentRatio");
            return (Criteria) this;
        }

        public Criteria andAllotmentRatioLike(String value) {
            addCriterion("ALLOTMENT_RATIO like", value, "allotmentRatio");
            return (Criteria) this;
        }

        public Criteria andAllotmentRatioNotLike(String value) {
            addCriterion("ALLOTMENT_RATIO not like", value, "allotmentRatio");
            return (Criteria) this;
        }

        public Criteria andAllotmentRatioIn(List<String> values) {
            addCriterion("ALLOTMENT_RATIO in", values, "allotmentRatio");
            return (Criteria) this;
        }

        public Criteria andAllotmentRatioNotIn(List<String> values) {
            addCriterion("ALLOTMENT_RATIO not in", values, "allotmentRatio");
            return (Criteria) this;
        }

        public Criteria andAllotmentRatioBetween(String value1, String value2) {
            addCriterion("ALLOTMENT_RATIO between", value1, value2, "allotmentRatio");
            return (Criteria) this;
        }

        public Criteria andAllotmentRatioNotBetween(String value1, String value2) {
            addCriterion("ALLOTMENT_RATIO not between", value1, value2, "allotmentRatio");
            return (Criteria) this;
        }

        public Criteria andShsPlacingIsNull() {
            addCriterion("SHS_PLACING is null");
            return (Criteria) this;
        }

        public Criteria andShsPlacingIsNotNull() {
            addCriterion("SHS_PLACING is not null");
            return (Criteria) this;
        }

        public Criteria andShsPlacingEqualTo(String value) {
            addCriterion("SHS_PLACING =", value, "shsPlacing");
            return (Criteria) this;
        }

        public Criteria andShsPlacingNotEqualTo(String value) {
            addCriterion("SHS_PLACING <>", value, "shsPlacing");
            return (Criteria) this;
        }

        public Criteria andShsPlacingGreaterThan(String value) {
            addCriterion("SHS_PLACING >", value, "shsPlacing");
            return (Criteria) this;
        }

        public Criteria andShsPlacingGreaterThanOrEqualTo(String value) {
            addCriterion("SHS_PLACING >=", value, "shsPlacing");
            return (Criteria) this;
        }

        public Criteria andShsPlacingLessThan(String value) {
            addCriterion("SHS_PLACING <", value, "shsPlacing");
            return (Criteria) this;
        }

        public Criteria andShsPlacingLessThanOrEqualTo(String value) {
            addCriterion("SHS_PLACING <=", value, "shsPlacing");
            return (Criteria) this;
        }

        public Criteria andShsPlacingLike(String value) {
            addCriterion("SHS_PLACING like", value, "shsPlacing");
            return (Criteria) this;
        }

        public Criteria andShsPlacingNotLike(String value) {
            addCriterion("SHS_PLACING not like", value, "shsPlacing");
            return (Criteria) this;
        }

        public Criteria andShsPlacingIn(List<String> values) {
            addCriterion("SHS_PLACING in", values, "shsPlacing");
            return (Criteria) this;
        }

        public Criteria andShsPlacingNotIn(List<String> values) {
            addCriterion("SHS_PLACING not in", values, "shsPlacing");
            return (Criteria) this;
        }

        public Criteria andShsPlacingBetween(String value1, String value2) {
            addCriterion("SHS_PLACING between", value1, value2, "shsPlacing");
            return (Criteria) this;
        }

        public Criteria andShsPlacingNotBetween(String value1, String value2) {
            addCriterion("SHS_PLACING not between", value1, value2, "shsPlacing");
            return (Criteria) this;
        }

        public Criteria andTransferredRatioIsNull() {
            addCriterion("TRANSFERRED_RATIO is null");
            return (Criteria) this;
        }

        public Criteria andTransferredRatioIsNotNull() {
            addCriterion("TRANSFERRED_RATIO is not null");
            return (Criteria) this;
        }

        public Criteria andTransferredRatioEqualTo(String value) {
            addCriterion("TRANSFERRED_RATIO =", value, "transferredRatio");
            return (Criteria) this;
        }

        public Criteria andTransferredRatioNotEqualTo(String value) {
            addCriterion("TRANSFERRED_RATIO <>", value, "transferredRatio");
            return (Criteria) this;
        }

        public Criteria andTransferredRatioGreaterThan(String value) {
            addCriterion("TRANSFERRED_RATIO >", value, "transferredRatio");
            return (Criteria) this;
        }

        public Criteria andTransferredRatioGreaterThanOrEqualTo(String value) {
            addCriterion("TRANSFERRED_RATIO >=", value, "transferredRatio");
            return (Criteria) this;
        }

        public Criteria andTransferredRatioLessThan(String value) {
            addCriterion("TRANSFERRED_RATIO <", value, "transferredRatio");
            return (Criteria) this;
        }

        public Criteria andTransferredRatioLessThanOrEqualTo(String value) {
            addCriterion("TRANSFERRED_RATIO <=", value, "transferredRatio");
            return (Criteria) this;
        }

        public Criteria andTransferredRatioLike(String value) {
            addCriterion("TRANSFERRED_RATIO like", value, "transferredRatio");
            return (Criteria) this;
        }

        public Criteria andTransferredRatioNotLike(String value) {
            addCriterion("TRANSFERRED_RATIO not like", value, "transferredRatio");
            return (Criteria) this;
        }

        public Criteria andTransferredRatioIn(List<String> values) {
            addCriterion("TRANSFERRED_RATIO in", values, "transferredRatio");
            return (Criteria) this;
        }

        public Criteria andTransferredRatioNotIn(List<String> values) {
            addCriterion("TRANSFERRED_RATIO not in", values, "transferredRatio");
            return (Criteria) this;
        }

        public Criteria andTransferredRatioBetween(String value1, String value2) {
            addCriterion("TRANSFERRED_RATIO between", value1, value2, "transferredRatio");
            return (Criteria) this;
        }

        public Criteria andTransferredRatioNotBetween(String value1, String value2) {
            addCriterion("TRANSFERRED_RATIO not between", value1, value2, "transferredRatio");
            return (Criteria) this;
        }

        public Criteria andTurnValenceIsNull() {
            addCriterion("TURN_VALENCE is null");
            return (Criteria) this;
        }

        public Criteria andTurnValenceIsNotNull() {
            addCriterion("TURN_VALENCE is not null");
            return (Criteria) this;
        }

        public Criteria andTurnValenceEqualTo(String value) {
            addCriterion("TURN_VALENCE =", value, "turnValence");
            return (Criteria) this;
        }

        public Criteria andTurnValenceNotEqualTo(String value) {
            addCriterion("TURN_VALENCE <>", value, "turnValence");
            return (Criteria) this;
        }

        public Criteria andTurnValenceGreaterThan(String value) {
            addCriterion("TURN_VALENCE >", value, "turnValence");
            return (Criteria) this;
        }

        public Criteria andTurnValenceGreaterThanOrEqualTo(String value) {
            addCriterion("TURN_VALENCE >=", value, "turnValence");
            return (Criteria) this;
        }

        public Criteria andTurnValenceLessThan(String value) {
            addCriterion("TURN_VALENCE <", value, "turnValence");
            return (Criteria) this;
        }

        public Criteria andTurnValenceLessThanOrEqualTo(String value) {
            addCriterion("TURN_VALENCE <=", value, "turnValence");
            return (Criteria) this;
        }

        public Criteria andTurnValenceLike(String value) {
            addCriterion("TURN_VALENCE like", value, "turnValence");
            return (Criteria) this;
        }

        public Criteria andTurnValenceNotLike(String value) {
            addCriterion("TURN_VALENCE not like", value, "turnValence");
            return (Criteria) this;
        }

        public Criteria andTurnValenceIn(List<String> values) {
            addCriterion("TURN_VALENCE in", values, "turnValence");
            return (Criteria) this;
        }

        public Criteria andTurnValenceNotIn(List<String> values) {
            addCriterion("TURN_VALENCE not in", values, "turnValence");
            return (Criteria) this;
        }

        public Criteria andTurnValenceBetween(String value1, String value2) {
            addCriterion("TURN_VALENCE between", value1, value2, "turnValence");
            return (Criteria) this;
        }

        public Criteria andTurnValenceNotBetween(String value1, String value2) {
            addCriterion("TURN_VALENCE not between", value1, value2, "turnValence");
            return (Criteria) this;
        }

        public Criteria andValidAllotmentIsNull() {
            addCriterion("VALID_ALLOTMENT is null");
            return (Criteria) this;
        }

        public Criteria andValidAllotmentIsNotNull() {
            addCriterion("VALID_ALLOTMENT is not null");
            return (Criteria) this;
        }

        public Criteria andValidAllotmentEqualTo(String value) {
            addCriterion("VALID_ALLOTMENT =", value, "validAllotment");
            return (Criteria) this;
        }

        public Criteria andValidAllotmentNotEqualTo(String value) {
            addCriterion("VALID_ALLOTMENT <>", value, "validAllotment");
            return (Criteria) this;
        }

        public Criteria andValidAllotmentGreaterThan(String value) {
            addCriterion("VALID_ALLOTMENT >", value, "validAllotment");
            return (Criteria) this;
        }

        public Criteria andValidAllotmentGreaterThanOrEqualTo(String value) {
            addCriterion("VALID_ALLOTMENT >=", value, "validAllotment");
            return (Criteria) this;
        }

        public Criteria andValidAllotmentLessThan(String value) {
            addCriterion("VALID_ALLOTMENT <", value, "validAllotment");
            return (Criteria) this;
        }

        public Criteria andValidAllotmentLessThanOrEqualTo(String value) {
            addCriterion("VALID_ALLOTMENT <=", value, "validAllotment");
            return (Criteria) this;
        }

        public Criteria andValidAllotmentLike(String value) {
            addCriterion("VALID_ALLOTMENT like", value, "validAllotment");
            return (Criteria) this;
        }

        public Criteria andValidAllotmentNotLike(String value) {
            addCriterion("VALID_ALLOTMENT not like", value, "validAllotment");
            return (Criteria) this;
        }

        public Criteria andValidAllotmentIn(List<String> values) {
            addCriterion("VALID_ALLOTMENT in", values, "validAllotment");
            return (Criteria) this;
        }

        public Criteria andValidAllotmentNotIn(List<String> values) {
            addCriterion("VALID_ALLOTMENT not in", values, "validAllotment");
            return (Criteria) this;
        }

        public Criteria andValidAllotmentBetween(String value1, String value2) {
            addCriterion("VALID_ALLOTMENT between", value1, value2, "validAllotment");
            return (Criteria) this;
        }

        public Criteria andValidAllotmentNotBetween(String value1, String value2) {
            addCriterion("VALID_ALLOTMENT not between", value1, value2, "validAllotment");
            return (Criteria) this;
        }

        public Criteria andActualSharesNumberIsNull() {
            addCriterion("ACTUAL_SHARES_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andActualSharesNumberIsNotNull() {
            addCriterion("ACTUAL_SHARES_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andActualSharesNumberEqualTo(String value) {
            addCriterion("ACTUAL_SHARES_NUMBER =", value, "actualSharesNumber");
            return (Criteria) this;
        }

        public Criteria andActualSharesNumberNotEqualTo(String value) {
            addCriterion("ACTUAL_SHARES_NUMBER <>", value, "actualSharesNumber");
            return (Criteria) this;
        }

        public Criteria andActualSharesNumberGreaterThan(String value) {
            addCriterion("ACTUAL_SHARES_NUMBER >", value, "actualSharesNumber");
            return (Criteria) this;
        }

        public Criteria andActualSharesNumberGreaterThanOrEqualTo(String value) {
            addCriterion("ACTUAL_SHARES_NUMBER >=", value, "actualSharesNumber");
            return (Criteria) this;
        }

        public Criteria andActualSharesNumberLessThan(String value) {
            addCriterion("ACTUAL_SHARES_NUMBER <", value, "actualSharesNumber");
            return (Criteria) this;
        }

        public Criteria andActualSharesNumberLessThanOrEqualTo(String value) {
            addCriterion("ACTUAL_SHARES_NUMBER <=", value, "actualSharesNumber");
            return (Criteria) this;
        }

        public Criteria andActualSharesNumberLike(String value) {
            addCriterion("ACTUAL_SHARES_NUMBER like", value, "actualSharesNumber");
            return (Criteria) this;
        }

        public Criteria andActualSharesNumberNotLike(String value) {
            addCriterion("ACTUAL_SHARES_NUMBER not like", value, "actualSharesNumber");
            return (Criteria) this;
        }

        public Criteria andActualSharesNumberIn(List<String> values) {
            addCriterion("ACTUAL_SHARES_NUMBER in", values, "actualSharesNumber");
            return (Criteria) this;
        }

        public Criteria andActualSharesNumberNotIn(List<String> values) {
            addCriterion("ACTUAL_SHARES_NUMBER not in", values, "actualSharesNumber");
            return (Criteria) this;
        }

        public Criteria andActualSharesNumberBetween(String value1, String value2) {
            addCriterion("ACTUAL_SHARES_NUMBER between", value1, value2, "actualSharesNumber");
            return (Criteria) this;
        }

        public Criteria andActualSharesNumberNotBetween(String value1, String value2) {
            addCriterion("ACTUAL_SHARES_NUMBER not between", value1, value2, "actualSharesNumber");
            return (Criteria) this;
        }

        public Criteria andSharesBeforIssueIsNull() {
            addCriterion("SHARES_BEFOR_ISSUE is null");
            return (Criteria) this;
        }

        public Criteria andSharesBeforIssueIsNotNull() {
            addCriterion("SHARES_BEFOR_ISSUE is not null");
            return (Criteria) this;
        }

        public Criteria andSharesBeforIssueEqualTo(String value) {
            addCriterion("SHARES_BEFOR_ISSUE =", value, "sharesBeforIssue");
            return (Criteria) this;
        }

        public Criteria andSharesBeforIssueNotEqualTo(String value) {
            addCriterion("SHARES_BEFOR_ISSUE <>", value, "sharesBeforIssue");
            return (Criteria) this;
        }

        public Criteria andSharesBeforIssueGreaterThan(String value) {
            addCriterion("SHARES_BEFOR_ISSUE >", value, "sharesBeforIssue");
            return (Criteria) this;
        }

        public Criteria andSharesBeforIssueGreaterThanOrEqualTo(String value) {
            addCriterion("SHARES_BEFOR_ISSUE >=", value, "sharesBeforIssue");
            return (Criteria) this;
        }

        public Criteria andSharesBeforIssueLessThan(String value) {
            addCriterion("SHARES_BEFOR_ISSUE <", value, "sharesBeforIssue");
            return (Criteria) this;
        }

        public Criteria andSharesBeforIssueLessThanOrEqualTo(String value) {
            addCriterion("SHARES_BEFOR_ISSUE <=", value, "sharesBeforIssue");
            return (Criteria) this;
        }

        public Criteria andSharesBeforIssueLike(String value) {
            addCriterion("SHARES_BEFOR_ISSUE like", value, "sharesBeforIssue");
            return (Criteria) this;
        }

        public Criteria andSharesBeforIssueNotLike(String value) {
            addCriterion("SHARES_BEFOR_ISSUE not like", value, "sharesBeforIssue");
            return (Criteria) this;
        }

        public Criteria andSharesBeforIssueIn(List<String> values) {
            addCriterion("SHARES_BEFOR_ISSUE in", values, "sharesBeforIssue");
            return (Criteria) this;
        }

        public Criteria andSharesBeforIssueNotIn(List<String> values) {
            addCriterion("SHARES_BEFOR_ISSUE not in", values, "sharesBeforIssue");
            return (Criteria) this;
        }

        public Criteria andSharesBeforIssueBetween(String value1, String value2) {
            addCriterion("SHARES_BEFOR_ISSUE between", value1, value2, "sharesBeforIssue");
            return (Criteria) this;
        }

        public Criteria andSharesBeforIssueNotBetween(String value1, String value2) {
            addCriterion("SHARES_BEFOR_ISSUE not between", value1, value2, "sharesBeforIssue");
            return (Criteria) this;
        }

        public Criteria andActualRatioIsNull() {
            addCriterion("ACTUAL_RATIO is null");
            return (Criteria) this;
        }

        public Criteria andActualRatioIsNotNull() {
            addCriterion("ACTUAL_RATIO is not null");
            return (Criteria) this;
        }

        public Criteria andActualRatioEqualTo(String value) {
            addCriterion("ACTUAL_RATIO =", value, "actualRatio");
            return (Criteria) this;
        }

        public Criteria andActualRatioNotEqualTo(String value) {
            addCriterion("ACTUAL_RATIO <>", value, "actualRatio");
            return (Criteria) this;
        }

        public Criteria andActualRatioGreaterThan(String value) {
            addCriterion("ACTUAL_RATIO >", value, "actualRatio");
            return (Criteria) this;
        }

        public Criteria andActualRatioGreaterThanOrEqualTo(String value) {
            addCriterion("ACTUAL_RATIO >=", value, "actualRatio");
            return (Criteria) this;
        }

        public Criteria andActualRatioLessThan(String value) {
            addCriterion("ACTUAL_RATIO <", value, "actualRatio");
            return (Criteria) this;
        }

        public Criteria andActualRatioLessThanOrEqualTo(String value) {
            addCriterion("ACTUAL_RATIO <=", value, "actualRatio");
            return (Criteria) this;
        }

        public Criteria andActualRatioLike(String value) {
            addCriterion("ACTUAL_RATIO like", value, "actualRatio");
            return (Criteria) this;
        }

        public Criteria andActualRatioNotLike(String value) {
            addCriterion("ACTUAL_RATIO not like", value, "actualRatio");
            return (Criteria) this;
        }

        public Criteria andActualRatioIn(List<String> values) {
            addCriterion("ACTUAL_RATIO in", values, "actualRatio");
            return (Criteria) this;
        }

        public Criteria andActualRatioNotIn(List<String> values) {
            addCriterion("ACTUAL_RATIO not in", values, "actualRatio");
            return (Criteria) this;
        }

        public Criteria andActualRatioBetween(String value1, String value2) {
            addCriterion("ACTUAL_RATIO between", value1, value2, "actualRatio");
            return (Criteria) this;
        }

        public Criteria andActualRatioNotBetween(String value1, String value2) {
            addCriterion("ACTUAL_RATIO not between", value1, value2, "actualRatio");
            return (Criteria) this;
        }

        public Criteria andShareSplitCountIsNull() {
            addCriterion("SHARE_SPLIT_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andShareSplitCountIsNotNull() {
            addCriterion("SHARE_SPLIT_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andShareSplitCountEqualTo(String value) {
            addCriterion("SHARE_SPLIT_COUNT =", value, "shareSplitCount");
            return (Criteria) this;
        }

        public Criteria andShareSplitCountNotEqualTo(String value) {
            addCriterion("SHARE_SPLIT_COUNT <>", value, "shareSplitCount");
            return (Criteria) this;
        }

        public Criteria andShareSplitCountGreaterThan(String value) {
            addCriterion("SHARE_SPLIT_COUNT >", value, "shareSplitCount");
            return (Criteria) this;
        }

        public Criteria andShareSplitCountGreaterThanOrEqualTo(String value) {
            addCriterion("SHARE_SPLIT_COUNT >=", value, "shareSplitCount");
            return (Criteria) this;
        }

        public Criteria andShareSplitCountLessThan(String value) {
            addCriterion("SHARE_SPLIT_COUNT <", value, "shareSplitCount");
            return (Criteria) this;
        }

        public Criteria andShareSplitCountLessThanOrEqualTo(String value) {
            addCriterion("SHARE_SPLIT_COUNT <=", value, "shareSplitCount");
            return (Criteria) this;
        }

        public Criteria andShareSplitCountLike(String value) {
            addCriterion("SHARE_SPLIT_COUNT like", value, "shareSplitCount");
            return (Criteria) this;
        }

        public Criteria andShareSplitCountNotLike(String value) {
            addCriterion("SHARE_SPLIT_COUNT not like", value, "shareSplitCount");
            return (Criteria) this;
        }

        public Criteria andShareSplitCountIn(List<String> values) {
            addCriterion("SHARE_SPLIT_COUNT in", values, "shareSplitCount");
            return (Criteria) this;
        }

        public Criteria andShareSplitCountNotIn(List<String> values) {
            addCriterion("SHARE_SPLIT_COUNT not in", values, "shareSplitCount");
            return (Criteria) this;
        }

        public Criteria andShareSplitCountBetween(String value1, String value2) {
            addCriterion("SHARE_SPLIT_COUNT between", value1, value2, "shareSplitCount");
            return (Criteria) this;
        }

        public Criteria andShareSplitCountNotBetween(String value1, String value2) {
            addCriterion("SHARE_SPLIT_COUNT not between", value1, value2, "shareSplitCount");
            return (Criteria) this;
        }

        public Criteria andForeignCurrencyExchangeRateIsNull() {
            addCriterion("FOREIGN_CURRENCY_EXCHANGE_RATE is null");
            return (Criteria) this;
        }

        public Criteria andForeignCurrencyExchangeRateIsNotNull() {
            addCriterion("FOREIGN_CURRENCY_EXCHANGE_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andForeignCurrencyExchangeRateEqualTo(String value) {
            addCriterion("FOREIGN_CURRENCY_EXCHANGE_RATE =", value, "foreignCurrencyExchangeRate");
            return (Criteria) this;
        }

        public Criteria andForeignCurrencyExchangeRateNotEqualTo(String value) {
            addCriterion("FOREIGN_CURRENCY_EXCHANGE_RATE <>", value, "foreignCurrencyExchangeRate");
            return (Criteria) this;
        }

        public Criteria andForeignCurrencyExchangeRateGreaterThan(String value) {
            addCriterion("FOREIGN_CURRENCY_EXCHANGE_RATE >", value, "foreignCurrencyExchangeRate");
            return (Criteria) this;
        }

        public Criteria andForeignCurrencyExchangeRateGreaterThanOrEqualTo(String value) {
            addCriterion("FOREIGN_CURRENCY_EXCHANGE_RATE >=", value, "foreignCurrencyExchangeRate");
            return (Criteria) this;
        }

        public Criteria andForeignCurrencyExchangeRateLessThan(String value) {
            addCriterion("FOREIGN_CURRENCY_EXCHANGE_RATE <", value, "foreignCurrencyExchangeRate");
            return (Criteria) this;
        }

        public Criteria andForeignCurrencyExchangeRateLessThanOrEqualTo(String value) {
            addCriterion("FOREIGN_CURRENCY_EXCHANGE_RATE <=", value, "foreignCurrencyExchangeRate");
            return (Criteria) this;
        }

        public Criteria andForeignCurrencyExchangeRateLike(String value) {
            addCriterion("FOREIGN_CURRENCY_EXCHANGE_RATE like", value, "foreignCurrencyExchangeRate");
            return (Criteria) this;
        }

        public Criteria andForeignCurrencyExchangeRateNotLike(String value) {
            addCriterion("FOREIGN_CURRENCY_EXCHANGE_RATE not like", value, "foreignCurrencyExchangeRate");
            return (Criteria) this;
        }

        public Criteria andForeignCurrencyExchangeRateIn(List<String> values) {
            addCriterion("FOREIGN_CURRENCY_EXCHANGE_RATE in", values, "foreignCurrencyExchangeRate");
            return (Criteria) this;
        }

        public Criteria andForeignCurrencyExchangeRateNotIn(List<String> values) {
            addCriterion("FOREIGN_CURRENCY_EXCHANGE_RATE not in", values, "foreignCurrencyExchangeRate");
            return (Criteria) this;
        }

        public Criteria andForeignCurrencyExchangeRateBetween(String value1, String value2) {
            addCriterion("FOREIGN_CURRENCY_EXCHANGE_RATE between", value1, value2, "foreignCurrencyExchangeRate");
            return (Criteria) this;
        }

        public Criteria andForeignCurrencyExchangeRateNotBetween(String value1, String value2) {
            addCriterion("FOREIGN_CURRENCY_EXCHANGE_RATE not between", value1, value2, "foreignCurrencyExchangeRate");
            return (Criteria) this;
        }

        public Criteria andWeightNoteIsNull() {
            addCriterion("WEIGHT_NOTE is null");
            return (Criteria) this;
        }

        public Criteria andWeightNoteIsNotNull() {
            addCriterion("WEIGHT_NOTE is not null");
            return (Criteria) this;
        }

        public Criteria andWeightNoteEqualTo(String value) {
            addCriterion("WEIGHT_NOTE =", value, "weightNote");
            return (Criteria) this;
        }

        public Criteria andWeightNoteNotEqualTo(String value) {
            addCriterion("WEIGHT_NOTE <>", value, "weightNote");
            return (Criteria) this;
        }

        public Criteria andWeightNoteGreaterThan(String value) {
            addCriterion("WEIGHT_NOTE >", value, "weightNote");
            return (Criteria) this;
        }

        public Criteria andWeightNoteGreaterThanOrEqualTo(String value) {
            addCriterion("WEIGHT_NOTE >=", value, "weightNote");
            return (Criteria) this;
        }

        public Criteria andWeightNoteLessThan(String value) {
            addCriterion("WEIGHT_NOTE <", value, "weightNote");
            return (Criteria) this;
        }

        public Criteria andWeightNoteLessThanOrEqualTo(String value) {
            addCriterion("WEIGHT_NOTE <=", value, "weightNote");
            return (Criteria) this;
        }

        public Criteria andWeightNoteLike(String value) {
            addCriterion("WEIGHT_NOTE like", value, "weightNote");
            return (Criteria) this;
        }

        public Criteria andWeightNoteNotLike(String value) {
            addCriterion("WEIGHT_NOTE not like", value, "weightNote");
            return (Criteria) this;
        }

        public Criteria andWeightNoteIn(List<String> values) {
            addCriterion("WEIGHT_NOTE in", values, "weightNote");
            return (Criteria) this;
        }

        public Criteria andWeightNoteNotIn(List<String> values) {
            addCriterion("WEIGHT_NOTE not in", values, "weightNote");
            return (Criteria) this;
        }

        public Criteria andWeightNoteBetween(String value1, String value2) {
            addCriterion("WEIGHT_NOTE between", value1, value2, "weightNote");
            return (Criteria) this;
        }

        public Criteria andWeightNoteNotBetween(String value1, String value2) {
            addCriterion("WEIGHT_NOTE not between", value1, value2, "weightNote");
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