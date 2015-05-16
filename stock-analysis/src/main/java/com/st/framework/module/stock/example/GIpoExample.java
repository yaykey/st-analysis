package com.st.framework.module.stock.example;

import com.st.framework.module.example.BaseExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class GIpoExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GIpoExample() {
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

        public Criteria andPurchaseCodeIsNull() {
            addCriterion("PURCHASE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeIsNotNull() {
            addCriterion("PURCHASE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeEqualTo(String value) {
            addCriterion("PURCHASE_CODE =", value, "purchaseCode");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeNotEqualTo(String value) {
            addCriterion("PURCHASE_CODE <>", value, "purchaseCode");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeGreaterThan(String value) {
            addCriterion("PURCHASE_CODE >", value, "purchaseCode");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeGreaterThanOrEqualTo(String value) {
            addCriterion("PURCHASE_CODE >=", value, "purchaseCode");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeLessThan(String value) {
            addCriterion("PURCHASE_CODE <", value, "purchaseCode");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeLessThanOrEqualTo(String value) {
            addCriterion("PURCHASE_CODE <=", value, "purchaseCode");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeLike(String value) {
            addCriterion("PURCHASE_CODE like", value, "purchaseCode");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeNotLike(String value) {
            addCriterion("PURCHASE_CODE not like", value, "purchaseCode");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeIn(List<String> values) {
            addCriterion("PURCHASE_CODE in", values, "purchaseCode");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeNotIn(List<String> values) {
            addCriterion("PURCHASE_CODE not in", values, "purchaseCode");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeBetween(String value1, String value2) {
            addCriterion("PURCHASE_CODE between", value1, value2, "purchaseCode");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeNotBetween(String value1, String value2) {
            addCriterion("PURCHASE_CODE not between", value1, value2, "purchaseCode");
            return (Criteria) this;
        }

        public Criteria andStockNameIsNull() {
            addCriterion("STOCK_NAME is null");
            return (Criteria) this;
        }

        public Criteria andStockNameIsNotNull() {
            addCriterion("STOCK_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andStockNameEqualTo(String value) {
            addCriterion("STOCK_NAME =", value, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameNotEqualTo(String value) {
            addCriterion("STOCK_NAME <>", value, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameGreaterThan(String value) {
            addCriterion("STOCK_NAME >", value, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameGreaterThanOrEqualTo(String value) {
            addCriterion("STOCK_NAME >=", value, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameLessThan(String value) {
            addCriterion("STOCK_NAME <", value, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameLessThanOrEqualTo(String value) {
            addCriterion("STOCK_NAME <=", value, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameLike(String value) {
            addCriterion("STOCK_NAME like", value, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameNotLike(String value) {
            addCriterion("STOCK_NAME not like", value, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameIn(List<String> values) {
            addCriterion("STOCK_NAME in", values, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameNotIn(List<String> values) {
            addCriterion("STOCK_NAME not in", values, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameBetween(String value1, String value2) {
            addCriterion("STOCK_NAME between", value1, value2, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameNotBetween(String value1, String value2) {
            addCriterion("STOCK_NAME not between", value1, value2, "stockName");
            return (Criteria) this;
        }

        public Criteria andYearIdIsNull() {
            addCriterion("YEAR_ID is null");
            return (Criteria) this;
        }

        public Criteria andYearIdIsNotNull() {
            addCriterion("YEAR_ID is not null");
            return (Criteria) this;
        }

        public Criteria andYearIdEqualTo(Integer value) {
            addCriterion("YEAR_ID =", value, "yearId");
            return (Criteria) this;
        }

        public Criteria andYearIdNotEqualTo(Integer value) {
            addCriterion("YEAR_ID <>", value, "yearId");
            return (Criteria) this;
        }

        public Criteria andYearIdGreaterThan(Integer value) {
            addCriterion("YEAR_ID >", value, "yearId");
            return (Criteria) this;
        }

        public Criteria andYearIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("YEAR_ID >=", value, "yearId");
            return (Criteria) this;
        }

        public Criteria andYearIdLessThan(Integer value) {
            addCriterion("YEAR_ID <", value, "yearId");
            return (Criteria) this;
        }

        public Criteria andYearIdLessThanOrEqualTo(Integer value) {
            addCriterion("YEAR_ID <=", value, "yearId");
            return (Criteria) this;
        }

        public Criteria andYearIdIn(List<Integer> values) {
            addCriterion("YEAR_ID in", values, "yearId");
            return (Criteria) this;
        }

        public Criteria andYearIdNotIn(List<Integer> values) {
            addCriterion("YEAR_ID not in", values, "yearId");
            return (Criteria) this;
        }

        public Criteria andYearIdBetween(Integer value1, Integer value2) {
            addCriterion("YEAR_ID between", value1, value2, "yearId");
            return (Criteria) this;
        }

        public Criteria andYearIdNotBetween(Integer value1, Integer value2) {
            addCriterion("YEAR_ID not between", value1, value2, "yearId");
            return (Criteria) this;
        }

        public Criteria andOnlineIssueDateIsNull() {
            addCriterion("ONLINE_ISSUE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andOnlineIssueDateIsNotNull() {
            addCriterion("ONLINE_ISSUE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andOnlineIssueDateEqualTo(Date value) {
            addCriterionForJDBCDate("ONLINE_ISSUE_DATE =", value, "onlineIssueDate");
            return (Criteria) this;
        }

        public Criteria andOnlineIssueDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("ONLINE_ISSUE_DATE <>", value, "onlineIssueDate");
            return (Criteria) this;
        }

        public Criteria andOnlineIssueDateGreaterThan(Date value) {
            addCriterionForJDBCDate("ONLINE_ISSUE_DATE >", value, "onlineIssueDate");
            return (Criteria) this;
        }

        public Criteria andOnlineIssueDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ONLINE_ISSUE_DATE >=", value, "onlineIssueDate");
            return (Criteria) this;
        }

        public Criteria andOnlineIssueDateLessThan(Date value) {
            addCriterionForJDBCDate("ONLINE_ISSUE_DATE <", value, "onlineIssueDate");
            return (Criteria) this;
        }

        public Criteria andOnlineIssueDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ONLINE_ISSUE_DATE <=", value, "onlineIssueDate");
            return (Criteria) this;
        }

        public Criteria andOnlineIssueDateIn(List<Date> values) {
            addCriterionForJDBCDate("ONLINE_ISSUE_DATE in", values, "onlineIssueDate");
            return (Criteria) this;
        }

        public Criteria andOnlineIssueDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("ONLINE_ISSUE_DATE not in", values, "onlineIssueDate");
            return (Criteria) this;
        }

        public Criteria andOnlineIssueDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ONLINE_ISSUE_DATE between", value1, value2, "onlineIssueDate");
            return (Criteria) this;
        }

        public Criteria andOnlineIssueDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ONLINE_ISSUE_DATE not between", value1, value2, "onlineIssueDate");
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

        public Criteria andCirculationIsNull() {
            addCriterion("CIRCULATION is null");
            return (Criteria) this;
        }

        public Criteria andCirculationIsNotNull() {
            addCriterion("CIRCULATION is not null");
            return (Criteria) this;
        }

        public Criteria andCirculationEqualTo(Integer value) {
            addCriterion("CIRCULATION =", value, "circulation");
            return (Criteria) this;
        }

        public Criteria andCirculationNotEqualTo(Integer value) {
            addCriterion("CIRCULATION <>", value, "circulation");
            return (Criteria) this;
        }

        public Criteria andCirculationGreaterThan(Integer value) {
            addCriterion("CIRCULATION >", value, "circulation");
            return (Criteria) this;
        }

        public Criteria andCirculationGreaterThanOrEqualTo(Integer value) {
            addCriterion("CIRCULATION >=", value, "circulation");
            return (Criteria) this;
        }

        public Criteria andCirculationLessThan(Integer value) {
            addCriterion("CIRCULATION <", value, "circulation");
            return (Criteria) this;
        }

        public Criteria andCirculationLessThanOrEqualTo(Integer value) {
            addCriterion("CIRCULATION <=", value, "circulation");
            return (Criteria) this;
        }

        public Criteria andCirculationIn(List<Integer> values) {
            addCriterion("CIRCULATION in", values, "circulation");
            return (Criteria) this;
        }

        public Criteria andCirculationNotIn(List<Integer> values) {
            addCriterion("CIRCULATION not in", values, "circulation");
            return (Criteria) this;
        }

        public Criteria andCirculationBetween(Integer value1, Integer value2) {
            addCriterion("CIRCULATION between", value1, value2, "circulation");
            return (Criteria) this;
        }

        public Criteria andCirculationNotBetween(Integer value1, Integer value2) {
            addCriterion("CIRCULATION not between", value1, value2, "circulation");
            return (Criteria) this;
        }

        public Criteria andOnlineCirculationIsNull() {
            addCriterion("ONLINE_CIRCULATION is null");
            return (Criteria) this;
        }

        public Criteria andOnlineCirculationIsNotNull() {
            addCriterion("ONLINE_CIRCULATION is not null");
            return (Criteria) this;
        }

        public Criteria andOnlineCirculationEqualTo(Integer value) {
            addCriterion("ONLINE_CIRCULATION =", value, "onlineCirculation");
            return (Criteria) this;
        }

        public Criteria andOnlineCirculationNotEqualTo(Integer value) {
            addCriterion("ONLINE_CIRCULATION <>", value, "onlineCirculation");
            return (Criteria) this;
        }

        public Criteria andOnlineCirculationGreaterThan(Integer value) {
            addCriterion("ONLINE_CIRCULATION >", value, "onlineCirculation");
            return (Criteria) this;
        }

        public Criteria andOnlineCirculationGreaterThanOrEqualTo(Integer value) {
            addCriterion("ONLINE_CIRCULATION >=", value, "onlineCirculation");
            return (Criteria) this;
        }

        public Criteria andOnlineCirculationLessThan(Integer value) {
            addCriterion("ONLINE_CIRCULATION <", value, "onlineCirculation");
            return (Criteria) this;
        }

        public Criteria andOnlineCirculationLessThanOrEqualTo(Integer value) {
            addCriterion("ONLINE_CIRCULATION <=", value, "onlineCirculation");
            return (Criteria) this;
        }

        public Criteria andOnlineCirculationIn(List<Integer> values) {
            addCriterion("ONLINE_CIRCULATION in", values, "onlineCirculation");
            return (Criteria) this;
        }

        public Criteria andOnlineCirculationNotIn(List<Integer> values) {
            addCriterion("ONLINE_CIRCULATION not in", values, "onlineCirculation");
            return (Criteria) this;
        }

        public Criteria andOnlineCirculationBetween(Integer value1, Integer value2) {
            addCriterion("ONLINE_CIRCULATION between", value1, value2, "onlineCirculation");
            return (Criteria) this;
        }

        public Criteria andOnlineCirculationNotBetween(Integer value1, Integer value2) {
            addCriterion("ONLINE_CIRCULATION not between", value1, value2, "onlineCirculation");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitIsNull() {
            addCriterion("PURCHASE_LIMIT is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitIsNotNull() {
            addCriterion("PURCHASE_LIMIT is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitEqualTo(Double value) {
            addCriterion("PURCHASE_LIMIT =", value, "purchaseLimit");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitNotEqualTo(Double value) {
            addCriterion("PURCHASE_LIMIT <>", value, "purchaseLimit");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitGreaterThan(Double value) {
            addCriterion("PURCHASE_LIMIT >", value, "purchaseLimit");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitGreaterThanOrEqualTo(Double value) {
            addCriterion("PURCHASE_LIMIT >=", value, "purchaseLimit");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitLessThan(Double value) {
            addCriterion("PURCHASE_LIMIT <", value, "purchaseLimit");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitLessThanOrEqualTo(Double value) {
            addCriterion("PURCHASE_LIMIT <=", value, "purchaseLimit");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitIn(List<Double> values) {
            addCriterion("PURCHASE_LIMIT in", values, "purchaseLimit");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitNotIn(List<Double> values) {
            addCriterion("PURCHASE_LIMIT not in", values, "purchaseLimit");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitBetween(Double value1, Double value2) {
            addCriterion("PURCHASE_LIMIT between", value1, value2, "purchaseLimit");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitNotBetween(Double value1, Double value2) {
            addCriterion("PURCHASE_LIMIT not between", value1, value2, "purchaseLimit");
            return (Criteria) this;
        }

        public Criteria andIssuePriceIsNull() {
            addCriterion("ISSUE_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andIssuePriceIsNotNull() {
            addCriterion("ISSUE_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andIssuePriceEqualTo(Double value) {
            addCriterion("ISSUE_PRICE =", value, "issuePrice");
            return (Criteria) this;
        }

        public Criteria andIssuePriceNotEqualTo(Double value) {
            addCriterion("ISSUE_PRICE <>", value, "issuePrice");
            return (Criteria) this;
        }

        public Criteria andIssuePriceGreaterThan(Double value) {
            addCriterion("ISSUE_PRICE >", value, "issuePrice");
            return (Criteria) this;
        }

        public Criteria andIssuePriceGreaterThanOrEqualTo(Double value) {
            addCriterion("ISSUE_PRICE >=", value, "issuePrice");
            return (Criteria) this;
        }

        public Criteria andIssuePriceLessThan(Double value) {
            addCriterion("ISSUE_PRICE <", value, "issuePrice");
            return (Criteria) this;
        }

        public Criteria andIssuePriceLessThanOrEqualTo(Double value) {
            addCriterion("ISSUE_PRICE <=", value, "issuePrice");
            return (Criteria) this;
        }

        public Criteria andIssuePriceIn(List<Double> values) {
            addCriterion("ISSUE_PRICE in", values, "issuePrice");
            return (Criteria) this;
        }

        public Criteria andIssuePriceNotIn(List<Double> values) {
            addCriterion("ISSUE_PRICE not in", values, "issuePrice");
            return (Criteria) this;
        }

        public Criteria andIssuePriceBetween(Double value1, Double value2) {
            addCriterion("ISSUE_PRICE between", value1, value2, "issuePrice");
            return (Criteria) this;
        }

        public Criteria andIssuePriceNotBetween(Double value1, Double value2) {
            addCriterion("ISSUE_PRICE not between", value1, value2, "issuePrice");
            return (Criteria) this;
        }

        public Criteria andPeRatioIsNull() {
            addCriterion("PE_RATIO is null");
            return (Criteria) this;
        }

        public Criteria andPeRatioIsNotNull() {
            addCriterion("PE_RATIO is not null");
            return (Criteria) this;
        }

        public Criteria andPeRatioEqualTo(Double value) {
            addCriterion("PE_RATIO =", value, "peRatio");
            return (Criteria) this;
        }

        public Criteria andPeRatioNotEqualTo(Double value) {
            addCriterion("PE_RATIO <>", value, "peRatio");
            return (Criteria) this;
        }

        public Criteria andPeRatioGreaterThan(Double value) {
            addCriterion("PE_RATIO >", value, "peRatio");
            return (Criteria) this;
        }

        public Criteria andPeRatioGreaterThanOrEqualTo(Double value) {
            addCriterion("PE_RATIO >=", value, "peRatio");
            return (Criteria) this;
        }

        public Criteria andPeRatioLessThan(Double value) {
            addCriterion("PE_RATIO <", value, "peRatio");
            return (Criteria) this;
        }

        public Criteria andPeRatioLessThanOrEqualTo(Double value) {
            addCriterion("PE_RATIO <=", value, "peRatio");
            return (Criteria) this;
        }

        public Criteria andPeRatioIn(List<Double> values) {
            addCriterion("PE_RATIO in", values, "peRatio");
            return (Criteria) this;
        }

        public Criteria andPeRatioNotIn(List<Double> values) {
            addCriterion("PE_RATIO not in", values, "peRatio");
            return (Criteria) this;
        }

        public Criteria andPeRatioBetween(Double value1, Double value2) {
            addCriterion("PE_RATIO between", value1, value2, "peRatio");
            return (Criteria) this;
        }

        public Criteria andPeRatioNotBetween(Double value1, Double value2) {
            addCriterion("PE_RATIO not between", value1, value2, "peRatio");
            return (Criteria) this;
        }

        public Criteria andFreezeFundsIsNull() {
            addCriterion("FREEZE_FUNDS is null");
            return (Criteria) this;
        }

        public Criteria andFreezeFundsIsNotNull() {
            addCriterion("FREEZE_FUNDS is not null");
            return (Criteria) this;
        }

        public Criteria andFreezeFundsEqualTo(Double value) {
            addCriterion("FREEZE_FUNDS =", value, "freezeFunds");
            return (Criteria) this;
        }

        public Criteria andFreezeFundsNotEqualTo(Double value) {
            addCriterion("FREEZE_FUNDS <>", value, "freezeFunds");
            return (Criteria) this;
        }

        public Criteria andFreezeFundsGreaterThan(Double value) {
            addCriterion("FREEZE_FUNDS >", value, "freezeFunds");
            return (Criteria) this;
        }

        public Criteria andFreezeFundsGreaterThanOrEqualTo(Double value) {
            addCriterion("FREEZE_FUNDS >=", value, "freezeFunds");
            return (Criteria) this;
        }

        public Criteria andFreezeFundsLessThan(Double value) {
            addCriterion("FREEZE_FUNDS <", value, "freezeFunds");
            return (Criteria) this;
        }

        public Criteria andFreezeFundsLessThanOrEqualTo(Double value) {
            addCriterion("FREEZE_FUNDS <=", value, "freezeFunds");
            return (Criteria) this;
        }

        public Criteria andFreezeFundsIn(List<Double> values) {
            addCriterion("FREEZE_FUNDS in", values, "freezeFunds");
            return (Criteria) this;
        }

        public Criteria andFreezeFundsNotIn(List<Double> values) {
            addCriterion("FREEZE_FUNDS not in", values, "freezeFunds");
            return (Criteria) this;
        }

        public Criteria andFreezeFundsBetween(Double value1, Double value2) {
            addCriterion("FREEZE_FUNDS between", value1, value2, "freezeFunds");
            return (Criteria) this;
        }

        public Criteria andFreezeFundsNotBetween(Double value1, Double value2) {
            addCriterion("FREEZE_FUNDS not between", value1, value2, "freezeFunds");
            return (Criteria) this;
        }

        public Criteria andSuccessRateIsNull() {
            addCriterion("SUCCESS_RATE is null");
            return (Criteria) this;
        }

        public Criteria andSuccessRateIsNotNull() {
            addCriterion("SUCCESS_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andSuccessRateEqualTo(Double value) {
            addCriterion("SUCCESS_RATE =", value, "successRate");
            return (Criteria) this;
        }

        public Criteria andSuccessRateNotEqualTo(Double value) {
            addCriterion("SUCCESS_RATE <>", value, "successRate");
            return (Criteria) this;
        }

        public Criteria andSuccessRateGreaterThan(Double value) {
            addCriterion("SUCCESS_RATE >", value, "successRate");
            return (Criteria) this;
        }

        public Criteria andSuccessRateGreaterThanOrEqualTo(Double value) {
            addCriterion("SUCCESS_RATE >=", value, "successRate");
            return (Criteria) this;
        }

        public Criteria andSuccessRateLessThan(Double value) {
            addCriterion("SUCCESS_RATE <", value, "successRate");
            return (Criteria) this;
        }

        public Criteria andSuccessRateLessThanOrEqualTo(Double value) {
            addCriterion("SUCCESS_RATE <=", value, "successRate");
            return (Criteria) this;
        }

        public Criteria andSuccessRateIn(List<Double> values) {
            addCriterion("SUCCESS_RATE in", values, "successRate");
            return (Criteria) this;
        }

        public Criteria andSuccessRateNotIn(List<Double> values) {
            addCriterion("SUCCESS_RATE not in", values, "successRate");
            return (Criteria) this;
        }

        public Criteria andSuccessRateBetween(Double value1, Double value2) {
            addCriterion("SUCCESS_RATE between", value1, value2, "successRate");
            return (Criteria) this;
        }

        public Criteria andSuccessRateNotBetween(Double value1, Double value2) {
            addCriterion("SUCCESS_RATE not between", value1, value2, "successRate");
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