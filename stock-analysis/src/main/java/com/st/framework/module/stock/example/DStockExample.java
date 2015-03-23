package com.st.framework.module.stock.example;

import com.st.framework.module.example.BaseExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DStockExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DStockExample() {
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

        public Criteria andStockTypeCodeIsNull() {
            addCriterion("STOCK_TYPE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andStockTypeCodeIsNotNull() {
            addCriterion("STOCK_TYPE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andStockTypeCodeEqualTo(String value) {
            addCriterion("STOCK_TYPE_CODE =", value, "stockTypeCode");
            return (Criteria) this;
        }

        public Criteria andStockTypeCodeNotEqualTo(String value) {
            addCriterion("STOCK_TYPE_CODE <>", value, "stockTypeCode");
            return (Criteria) this;
        }

        public Criteria andStockTypeCodeGreaterThan(String value) {
            addCriterion("STOCK_TYPE_CODE >", value, "stockTypeCode");
            return (Criteria) this;
        }

        public Criteria andStockTypeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("STOCK_TYPE_CODE >=", value, "stockTypeCode");
            return (Criteria) this;
        }

        public Criteria andStockTypeCodeLessThan(String value) {
            addCriterion("STOCK_TYPE_CODE <", value, "stockTypeCode");
            return (Criteria) this;
        }

        public Criteria andStockTypeCodeLessThanOrEqualTo(String value) {
            addCriterion("STOCK_TYPE_CODE <=", value, "stockTypeCode");
            return (Criteria) this;
        }

        public Criteria andStockTypeCodeLike(String value) {
            addCriterion("STOCK_TYPE_CODE like", value, "stockTypeCode");
            return (Criteria) this;
        }

        public Criteria andStockTypeCodeNotLike(String value) {
            addCriterion("STOCK_TYPE_CODE not like", value, "stockTypeCode");
            return (Criteria) this;
        }

        public Criteria andStockTypeCodeIn(List<String> values) {
            addCriterion("STOCK_TYPE_CODE in", values, "stockTypeCode");
            return (Criteria) this;
        }

        public Criteria andStockTypeCodeNotIn(List<String> values) {
            addCriterion("STOCK_TYPE_CODE not in", values, "stockTypeCode");
            return (Criteria) this;
        }

        public Criteria andStockTypeCodeBetween(String value1, String value2) {
            addCriterion("STOCK_TYPE_CODE between", value1, value2, "stockTypeCode");
            return (Criteria) this;
        }

        public Criteria andStockTypeCodeNotBetween(String value1, String value2) {
            addCriterion("STOCK_TYPE_CODE not between", value1, value2, "stockTypeCode");
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

        public Criteria andCompanyNameIsNull() {
            addCriterion("COMPANY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNotNull() {
            addCriterion("COMPANY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameEqualTo(String value) {
            addCriterion("COMPANY_NAME =", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotEqualTo(String value) {
            addCriterion("COMPANY_NAME <>", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThan(String value) {
            addCriterion("COMPANY_NAME >", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("COMPANY_NAME >=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThan(String value) {
            addCriterion("COMPANY_NAME <", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("COMPANY_NAME <=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLike(String value) {
            addCriterion("COMPANY_NAME like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotLike(String value) {
            addCriterion("COMPANY_NAME not like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIn(List<String> values) {
            addCriterion("COMPANY_NAME in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotIn(List<String> values) {
            addCriterion("COMPANY_NAME not in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameBetween(String value1, String value2) {
            addCriterion("COMPANY_NAME between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotBetween(String value1, String value2) {
            addCriterion("COMPANY_NAME not between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyEnNameIsNull() {
            addCriterion("COMPANY_EN_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCompanyEnNameIsNotNull() {
            addCriterion("COMPANY_EN_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyEnNameEqualTo(String value) {
            addCriterion("COMPANY_EN_NAME =", value, "companyEnName");
            return (Criteria) this;
        }

        public Criteria andCompanyEnNameNotEqualTo(String value) {
            addCriterion("COMPANY_EN_NAME <>", value, "companyEnName");
            return (Criteria) this;
        }

        public Criteria andCompanyEnNameGreaterThan(String value) {
            addCriterion("COMPANY_EN_NAME >", value, "companyEnName");
            return (Criteria) this;
        }

        public Criteria andCompanyEnNameGreaterThanOrEqualTo(String value) {
            addCriterion("COMPANY_EN_NAME >=", value, "companyEnName");
            return (Criteria) this;
        }

        public Criteria andCompanyEnNameLessThan(String value) {
            addCriterion("COMPANY_EN_NAME <", value, "companyEnName");
            return (Criteria) this;
        }

        public Criteria andCompanyEnNameLessThanOrEqualTo(String value) {
            addCriterion("COMPANY_EN_NAME <=", value, "companyEnName");
            return (Criteria) this;
        }

        public Criteria andCompanyEnNameLike(String value) {
            addCriterion("COMPANY_EN_NAME like", value, "companyEnName");
            return (Criteria) this;
        }

        public Criteria andCompanyEnNameNotLike(String value) {
            addCriterion("COMPANY_EN_NAME not like", value, "companyEnName");
            return (Criteria) this;
        }

        public Criteria andCompanyEnNameIn(List<String> values) {
            addCriterion("COMPANY_EN_NAME in", values, "companyEnName");
            return (Criteria) this;
        }

        public Criteria andCompanyEnNameNotIn(List<String> values) {
            addCriterion("COMPANY_EN_NAME not in", values, "companyEnName");
            return (Criteria) this;
        }

        public Criteria andCompanyEnNameBetween(String value1, String value2) {
            addCriterion("COMPANY_EN_NAME between", value1, value2, "companyEnName");
            return (Criteria) this;
        }

        public Criteria andCompanyEnNameNotBetween(String value1, String value2) {
            addCriterion("COMPANY_EN_NAME not between", value1, value2, "companyEnName");
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

        public Criteria andFirstDayOpeningPriceIsNull() {
            addCriterion("FIRST_DAY_OPENING_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andFirstDayOpeningPriceIsNotNull() {
            addCriterion("FIRST_DAY_OPENING_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andFirstDayOpeningPriceEqualTo(Double value) {
            addCriterion("FIRST_DAY_OPENING_PRICE =", value, "firstDayOpeningPrice");
            return (Criteria) this;
        }

        public Criteria andFirstDayOpeningPriceNotEqualTo(Double value) {
            addCriterion("FIRST_DAY_OPENING_PRICE <>", value, "firstDayOpeningPrice");
            return (Criteria) this;
        }

        public Criteria andFirstDayOpeningPriceGreaterThan(Double value) {
            addCriterion("FIRST_DAY_OPENING_PRICE >", value, "firstDayOpeningPrice");
            return (Criteria) this;
        }

        public Criteria andFirstDayOpeningPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("FIRST_DAY_OPENING_PRICE >=", value, "firstDayOpeningPrice");
            return (Criteria) this;
        }

        public Criteria andFirstDayOpeningPriceLessThan(Double value) {
            addCriterion("FIRST_DAY_OPENING_PRICE <", value, "firstDayOpeningPrice");
            return (Criteria) this;
        }

        public Criteria andFirstDayOpeningPriceLessThanOrEqualTo(Double value) {
            addCriterion("FIRST_DAY_OPENING_PRICE <=", value, "firstDayOpeningPrice");
            return (Criteria) this;
        }

        public Criteria andFirstDayOpeningPriceIn(List<Double> values) {
            addCriterion("FIRST_DAY_OPENING_PRICE in", values, "firstDayOpeningPrice");
            return (Criteria) this;
        }

        public Criteria andFirstDayOpeningPriceNotIn(List<Double> values) {
            addCriterion("FIRST_DAY_OPENING_PRICE not in", values, "firstDayOpeningPrice");
            return (Criteria) this;
        }

        public Criteria andFirstDayOpeningPriceBetween(Double value1, Double value2) {
            addCriterion("FIRST_DAY_OPENING_PRICE between", value1, value2, "firstDayOpeningPrice");
            return (Criteria) this;
        }

        public Criteria andFirstDayOpeningPriceNotBetween(Double value1, Double value2) {
            addCriterion("FIRST_DAY_OPENING_PRICE not between", value1, value2, "firstDayOpeningPrice");
            return (Criteria) this;
        }

        public Criteria andFirstDayClosingPriceIsNull() {
            addCriterion("FIRST_DAY_CLOSING_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andFirstDayClosingPriceIsNotNull() {
            addCriterion("FIRST_DAY_CLOSING_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andFirstDayClosingPriceEqualTo(Double value) {
            addCriterion("FIRST_DAY_CLOSING_PRICE =", value, "firstDayClosingPrice");
            return (Criteria) this;
        }

        public Criteria andFirstDayClosingPriceNotEqualTo(Double value) {
            addCriterion("FIRST_DAY_CLOSING_PRICE <>", value, "firstDayClosingPrice");
            return (Criteria) this;
        }

        public Criteria andFirstDayClosingPriceGreaterThan(Double value) {
            addCriterion("FIRST_DAY_CLOSING_PRICE >", value, "firstDayClosingPrice");
            return (Criteria) this;
        }

        public Criteria andFirstDayClosingPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("FIRST_DAY_CLOSING_PRICE >=", value, "firstDayClosingPrice");
            return (Criteria) this;
        }

        public Criteria andFirstDayClosingPriceLessThan(Double value) {
            addCriterion("FIRST_DAY_CLOSING_PRICE <", value, "firstDayClosingPrice");
            return (Criteria) this;
        }

        public Criteria andFirstDayClosingPriceLessThanOrEqualTo(Double value) {
            addCriterion("FIRST_DAY_CLOSING_PRICE <=", value, "firstDayClosingPrice");
            return (Criteria) this;
        }

        public Criteria andFirstDayClosingPriceIn(List<Double> values) {
            addCriterion("FIRST_DAY_CLOSING_PRICE in", values, "firstDayClosingPrice");
            return (Criteria) this;
        }

        public Criteria andFirstDayClosingPriceNotIn(List<Double> values) {
            addCriterion("FIRST_DAY_CLOSING_PRICE not in", values, "firstDayClosingPrice");
            return (Criteria) this;
        }

        public Criteria andFirstDayClosingPriceBetween(Double value1, Double value2) {
            addCriterion("FIRST_DAY_CLOSING_PRICE between", value1, value2, "firstDayClosingPrice");
            return (Criteria) this;
        }

        public Criteria andFirstDayClosingPriceNotBetween(Double value1, Double value2) {
            addCriterion("FIRST_DAY_CLOSING_PRICE not between", value1, value2, "firstDayClosingPrice");
            return (Criteria) this;
        }

        public Criteria andRegisteredAddressesIsNull() {
            addCriterion("REGISTERED_ADDRESSES is null");
            return (Criteria) this;
        }

        public Criteria andRegisteredAddressesIsNotNull() {
            addCriterion("REGISTERED_ADDRESSES is not null");
            return (Criteria) this;
        }

        public Criteria andRegisteredAddressesEqualTo(String value) {
            addCriterion("REGISTERED_ADDRESSES =", value, "registeredAddresses");
            return (Criteria) this;
        }

        public Criteria andRegisteredAddressesNotEqualTo(String value) {
            addCriterion("REGISTERED_ADDRESSES <>", value, "registeredAddresses");
            return (Criteria) this;
        }

        public Criteria andRegisteredAddressesGreaterThan(String value) {
            addCriterion("REGISTERED_ADDRESSES >", value, "registeredAddresses");
            return (Criteria) this;
        }

        public Criteria andRegisteredAddressesGreaterThanOrEqualTo(String value) {
            addCriterion("REGISTERED_ADDRESSES >=", value, "registeredAddresses");
            return (Criteria) this;
        }

        public Criteria andRegisteredAddressesLessThan(String value) {
            addCriterion("REGISTERED_ADDRESSES <", value, "registeredAddresses");
            return (Criteria) this;
        }

        public Criteria andRegisteredAddressesLessThanOrEqualTo(String value) {
            addCriterion("REGISTERED_ADDRESSES <=", value, "registeredAddresses");
            return (Criteria) this;
        }

        public Criteria andRegisteredAddressesLike(String value) {
            addCriterion("REGISTERED_ADDRESSES like", value, "registeredAddresses");
            return (Criteria) this;
        }

        public Criteria andRegisteredAddressesNotLike(String value) {
            addCriterion("REGISTERED_ADDRESSES not like", value, "registeredAddresses");
            return (Criteria) this;
        }

        public Criteria andRegisteredAddressesIn(List<String> values) {
            addCriterion("REGISTERED_ADDRESSES in", values, "registeredAddresses");
            return (Criteria) this;
        }

        public Criteria andRegisteredAddressesNotIn(List<String> values) {
            addCriterion("REGISTERED_ADDRESSES not in", values, "registeredAddresses");
            return (Criteria) this;
        }

        public Criteria andRegisteredAddressesBetween(String value1, String value2) {
            addCriterion("REGISTERED_ADDRESSES between", value1, value2, "registeredAddresses");
            return (Criteria) this;
        }

        public Criteria andRegisteredAddressesNotBetween(String value1, String value2) {
            addCriterion("REGISTERED_ADDRESSES not between", value1, value2, "registeredAddresses");
            return (Criteria) this;
        }

        public Criteria andOfficeAddressIsNull() {
            addCriterion("OFFICE_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andOfficeAddressIsNotNull() {
            addCriterion("OFFICE_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andOfficeAddressEqualTo(String value) {
            addCriterion("OFFICE_ADDRESS =", value, "officeAddress");
            return (Criteria) this;
        }

        public Criteria andOfficeAddressNotEqualTo(String value) {
            addCriterion("OFFICE_ADDRESS <>", value, "officeAddress");
            return (Criteria) this;
        }

        public Criteria andOfficeAddressGreaterThan(String value) {
            addCriterion("OFFICE_ADDRESS >", value, "officeAddress");
            return (Criteria) this;
        }

        public Criteria andOfficeAddressGreaterThanOrEqualTo(String value) {
            addCriterion("OFFICE_ADDRESS >=", value, "officeAddress");
            return (Criteria) this;
        }

        public Criteria andOfficeAddressLessThan(String value) {
            addCriterion("OFFICE_ADDRESS <", value, "officeAddress");
            return (Criteria) this;
        }

        public Criteria andOfficeAddressLessThanOrEqualTo(String value) {
            addCriterion("OFFICE_ADDRESS <=", value, "officeAddress");
            return (Criteria) this;
        }

        public Criteria andOfficeAddressLike(String value) {
            addCriterion("OFFICE_ADDRESS like", value, "officeAddress");
            return (Criteria) this;
        }

        public Criteria andOfficeAddressNotLike(String value) {
            addCriterion("OFFICE_ADDRESS not like", value, "officeAddress");
            return (Criteria) this;
        }

        public Criteria andOfficeAddressIn(List<String> values) {
            addCriterion("OFFICE_ADDRESS in", values, "officeAddress");
            return (Criteria) this;
        }

        public Criteria andOfficeAddressNotIn(List<String> values) {
            addCriterion("OFFICE_ADDRESS not in", values, "officeAddress");
            return (Criteria) this;
        }

        public Criteria andOfficeAddressBetween(String value1, String value2) {
            addCriterion("OFFICE_ADDRESS between", value1, value2, "officeAddress");
            return (Criteria) this;
        }

        public Criteria andOfficeAddressNotBetween(String value1, String value2) {
            addCriterion("OFFICE_ADDRESS not between", value1, value2, "officeAddress");
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

        public Criteria andMarketCapitalizationIsNull() {
            addCriterion("MARKET_CAPITALIZATION is null");
            return (Criteria) this;
        }

        public Criteria andMarketCapitalizationIsNotNull() {
            addCriterion("MARKET_CAPITALIZATION is not null");
            return (Criteria) this;
        }

        public Criteria andMarketCapitalizationEqualTo(Double value) {
            addCriterion("MARKET_CAPITALIZATION =", value, "marketCapitalization");
            return (Criteria) this;
        }

        public Criteria andMarketCapitalizationNotEqualTo(Double value) {
            addCriterion("MARKET_CAPITALIZATION <>", value, "marketCapitalization");
            return (Criteria) this;
        }

        public Criteria andMarketCapitalizationGreaterThan(Double value) {
            addCriterion("MARKET_CAPITALIZATION >", value, "marketCapitalization");
            return (Criteria) this;
        }

        public Criteria andMarketCapitalizationGreaterThanOrEqualTo(Double value) {
            addCriterion("MARKET_CAPITALIZATION >=", value, "marketCapitalization");
            return (Criteria) this;
        }

        public Criteria andMarketCapitalizationLessThan(Double value) {
            addCriterion("MARKET_CAPITALIZATION <", value, "marketCapitalization");
            return (Criteria) this;
        }

        public Criteria andMarketCapitalizationLessThanOrEqualTo(Double value) {
            addCriterion("MARKET_CAPITALIZATION <=", value, "marketCapitalization");
            return (Criteria) this;
        }

        public Criteria andMarketCapitalizationIn(List<Double> values) {
            addCriterion("MARKET_CAPITALIZATION in", values, "marketCapitalization");
            return (Criteria) this;
        }

        public Criteria andMarketCapitalizationNotIn(List<Double> values) {
            addCriterion("MARKET_CAPITALIZATION not in", values, "marketCapitalization");
            return (Criteria) this;
        }

        public Criteria andMarketCapitalizationBetween(Double value1, Double value2) {
            addCriterion("MARKET_CAPITALIZATION between", value1, value2, "marketCapitalization");
            return (Criteria) this;
        }

        public Criteria andMarketCapitalizationNotBetween(Double value1, Double value2) {
            addCriterion("MARKET_CAPITALIZATION not between", value1, value2, "marketCapitalization");
            return (Criteria) this;
        }

        public Criteria andCirculatedStockValueIsNull() {
            addCriterion("CIRCULATED_STOCK_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andCirculatedStockValueIsNotNull() {
            addCriterion("CIRCULATED_STOCK_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andCirculatedStockValueEqualTo(Double value) {
            addCriterion("CIRCULATED_STOCK_VALUE =", value, "circulatedStockValue");
            return (Criteria) this;
        }

        public Criteria andCirculatedStockValueNotEqualTo(Double value) {
            addCriterion("CIRCULATED_STOCK_VALUE <>", value, "circulatedStockValue");
            return (Criteria) this;
        }

        public Criteria andCirculatedStockValueGreaterThan(Double value) {
            addCriterion("CIRCULATED_STOCK_VALUE >", value, "circulatedStockValue");
            return (Criteria) this;
        }

        public Criteria andCirculatedStockValueGreaterThanOrEqualTo(Double value) {
            addCriterion("CIRCULATED_STOCK_VALUE >=", value, "circulatedStockValue");
            return (Criteria) this;
        }

        public Criteria andCirculatedStockValueLessThan(Double value) {
            addCriterion("CIRCULATED_STOCK_VALUE <", value, "circulatedStockValue");
            return (Criteria) this;
        }

        public Criteria andCirculatedStockValueLessThanOrEqualTo(Double value) {
            addCriterion("CIRCULATED_STOCK_VALUE <=", value, "circulatedStockValue");
            return (Criteria) this;
        }

        public Criteria andCirculatedStockValueIn(List<Double> values) {
            addCriterion("CIRCULATED_STOCK_VALUE in", values, "circulatedStockValue");
            return (Criteria) this;
        }

        public Criteria andCirculatedStockValueNotIn(List<Double> values) {
            addCriterion("CIRCULATED_STOCK_VALUE not in", values, "circulatedStockValue");
            return (Criteria) this;
        }

        public Criteria andCirculatedStockValueBetween(Double value1, Double value2) {
            addCriterion("CIRCULATED_STOCK_VALUE between", value1, value2, "circulatedStockValue");
            return (Criteria) this;
        }

        public Criteria andCirculatedStockValueNotBetween(Double value1, Double value2) {
            addCriterion("CIRCULATED_STOCK_VALUE not between", value1, value2, "circulatedStockValue");
            return (Criteria) this;
        }

        public Criteria andTotalCapitalIsNull() {
            addCriterion("TOTAL_CAPITAL is null");
            return (Criteria) this;
        }

        public Criteria andTotalCapitalIsNotNull() {
            addCriterion("TOTAL_CAPITAL is not null");
            return (Criteria) this;
        }

        public Criteria andTotalCapitalEqualTo(Double value) {
            addCriterion("TOTAL_CAPITAL =", value, "totalCapital");
            return (Criteria) this;
        }

        public Criteria andTotalCapitalNotEqualTo(Double value) {
            addCriterion("TOTAL_CAPITAL <>", value, "totalCapital");
            return (Criteria) this;
        }

        public Criteria andTotalCapitalGreaterThan(Double value) {
            addCriterion("TOTAL_CAPITAL >", value, "totalCapital");
            return (Criteria) this;
        }

        public Criteria andTotalCapitalGreaterThanOrEqualTo(Double value) {
            addCriterion("TOTAL_CAPITAL >=", value, "totalCapital");
            return (Criteria) this;
        }

        public Criteria andTotalCapitalLessThan(Double value) {
            addCriterion("TOTAL_CAPITAL <", value, "totalCapital");
            return (Criteria) this;
        }

        public Criteria andTotalCapitalLessThanOrEqualTo(Double value) {
            addCriterion("TOTAL_CAPITAL <=", value, "totalCapital");
            return (Criteria) this;
        }

        public Criteria andTotalCapitalIn(List<Double> values) {
            addCriterion("TOTAL_CAPITAL in", values, "totalCapital");
            return (Criteria) this;
        }

        public Criteria andTotalCapitalNotIn(List<Double> values) {
            addCriterion("TOTAL_CAPITAL not in", values, "totalCapital");
            return (Criteria) this;
        }

        public Criteria andTotalCapitalBetween(Double value1, Double value2) {
            addCriterion("TOTAL_CAPITAL between", value1, value2, "totalCapital");
            return (Criteria) this;
        }

        public Criteria andTotalCapitalNotBetween(Double value1, Double value2) {
            addCriterion("TOTAL_CAPITAL not between", value1, value2, "totalCapital");
            return (Criteria) this;
        }

        public Criteria andCurrCapitalIsNull() {
            addCriterion("CURR_CAPITAL is null");
            return (Criteria) this;
        }

        public Criteria andCurrCapitalIsNotNull() {
            addCriterion("CURR_CAPITAL is not null");
            return (Criteria) this;
        }

        public Criteria andCurrCapitalEqualTo(Double value) {
            addCriterion("CURR_CAPITAL =", value, "currCapital");
            return (Criteria) this;
        }

        public Criteria andCurrCapitalNotEqualTo(Double value) {
            addCriterion("CURR_CAPITAL <>", value, "currCapital");
            return (Criteria) this;
        }

        public Criteria andCurrCapitalGreaterThan(Double value) {
            addCriterion("CURR_CAPITAL >", value, "currCapital");
            return (Criteria) this;
        }

        public Criteria andCurrCapitalGreaterThanOrEqualTo(Double value) {
            addCriterion("CURR_CAPITAL >=", value, "currCapital");
            return (Criteria) this;
        }

        public Criteria andCurrCapitalLessThan(Double value) {
            addCriterion("CURR_CAPITAL <", value, "currCapital");
            return (Criteria) this;
        }

        public Criteria andCurrCapitalLessThanOrEqualTo(Double value) {
            addCriterion("CURR_CAPITAL <=", value, "currCapital");
            return (Criteria) this;
        }

        public Criteria andCurrCapitalIn(List<Double> values) {
            addCriterion("CURR_CAPITAL in", values, "currCapital");
            return (Criteria) this;
        }

        public Criteria andCurrCapitalNotIn(List<Double> values) {
            addCriterion("CURR_CAPITAL not in", values, "currCapital");
            return (Criteria) this;
        }

        public Criteria andCurrCapitalBetween(Double value1, Double value2) {
            addCriterion("CURR_CAPITAL between", value1, value2, "currCapital");
            return (Criteria) this;
        }

        public Criteria andCurrCapitalNotBetween(Double value1, Double value2) {
            addCriterion("CURR_CAPITAL not between", value1, value2, "currCapital");
            return (Criteria) this;
        }

        public Criteria andListedMarketIsNull() {
            addCriterion("LISTED_MARKET is null");
            return (Criteria) this;
        }

        public Criteria andListedMarketIsNotNull() {
            addCriterion("LISTED_MARKET is not null");
            return (Criteria) this;
        }

        public Criteria andListedMarketEqualTo(String value) {
            addCriterion("LISTED_MARKET =", value, "listedMarket");
            return (Criteria) this;
        }

        public Criteria andListedMarketNotEqualTo(String value) {
            addCriterion("LISTED_MARKET <>", value, "listedMarket");
            return (Criteria) this;
        }

        public Criteria andListedMarketGreaterThan(String value) {
            addCriterion("LISTED_MARKET >", value, "listedMarket");
            return (Criteria) this;
        }

        public Criteria andListedMarketGreaterThanOrEqualTo(String value) {
            addCriterion("LISTED_MARKET >=", value, "listedMarket");
            return (Criteria) this;
        }

        public Criteria andListedMarketLessThan(String value) {
            addCriterion("LISTED_MARKET <", value, "listedMarket");
            return (Criteria) this;
        }

        public Criteria andListedMarketLessThanOrEqualTo(String value) {
            addCriterion("LISTED_MARKET <=", value, "listedMarket");
            return (Criteria) this;
        }

        public Criteria andListedMarketLike(String value) {
            addCriterion("LISTED_MARKET like", value, "listedMarket");
            return (Criteria) this;
        }

        public Criteria andListedMarketNotLike(String value) {
            addCriterion("LISTED_MARKET not like", value, "listedMarket");
            return (Criteria) this;
        }

        public Criteria andListedMarketIn(List<String> values) {
            addCriterion("LISTED_MARKET in", values, "listedMarket");
            return (Criteria) this;
        }

        public Criteria andListedMarketNotIn(List<String> values) {
            addCriterion("LISTED_MARKET not in", values, "listedMarket");
            return (Criteria) this;
        }

        public Criteria andListedMarketBetween(String value1, String value2) {
            addCriterion("LISTED_MARKET between", value1, value2, "listedMarket");
            return (Criteria) this;
        }

        public Criteria andListedMarketNotBetween(String value1, String value2) {
            addCriterion("LISTED_MARKET not between", value1, value2, "listedMarket");
            return (Criteria) this;
        }

        public Criteria andLeadUnderwriterIsNull() {
            addCriterion("LEAD_UNDERWRITER is null");
            return (Criteria) this;
        }

        public Criteria andLeadUnderwriterIsNotNull() {
            addCriterion("LEAD_UNDERWRITER is not null");
            return (Criteria) this;
        }

        public Criteria andLeadUnderwriterEqualTo(String value) {
            addCriterion("LEAD_UNDERWRITER =", value, "leadUnderwriter");
            return (Criteria) this;
        }

        public Criteria andLeadUnderwriterNotEqualTo(String value) {
            addCriterion("LEAD_UNDERWRITER <>", value, "leadUnderwriter");
            return (Criteria) this;
        }

        public Criteria andLeadUnderwriterGreaterThan(String value) {
            addCriterion("LEAD_UNDERWRITER >", value, "leadUnderwriter");
            return (Criteria) this;
        }

        public Criteria andLeadUnderwriterGreaterThanOrEqualTo(String value) {
            addCriterion("LEAD_UNDERWRITER >=", value, "leadUnderwriter");
            return (Criteria) this;
        }

        public Criteria andLeadUnderwriterLessThan(String value) {
            addCriterion("LEAD_UNDERWRITER <", value, "leadUnderwriter");
            return (Criteria) this;
        }

        public Criteria andLeadUnderwriterLessThanOrEqualTo(String value) {
            addCriterion("LEAD_UNDERWRITER <=", value, "leadUnderwriter");
            return (Criteria) this;
        }

        public Criteria andLeadUnderwriterLike(String value) {
            addCriterion("LEAD_UNDERWRITER like", value, "leadUnderwriter");
            return (Criteria) this;
        }

        public Criteria andLeadUnderwriterNotLike(String value) {
            addCriterion("LEAD_UNDERWRITER not like", value, "leadUnderwriter");
            return (Criteria) this;
        }

        public Criteria andLeadUnderwriterIn(List<String> values) {
            addCriterion("LEAD_UNDERWRITER in", values, "leadUnderwriter");
            return (Criteria) this;
        }

        public Criteria andLeadUnderwriterNotIn(List<String> values) {
            addCriterion("LEAD_UNDERWRITER not in", values, "leadUnderwriter");
            return (Criteria) this;
        }

        public Criteria andLeadUnderwriterBetween(String value1, String value2) {
            addCriterion("LEAD_UNDERWRITER between", value1, value2, "leadUnderwriter");
            return (Criteria) this;
        }

        public Criteria andLeadUnderwriterNotBetween(String value1, String value2) {
            addCriterion("LEAD_UNDERWRITER not between", value1, value2, "leadUnderwriter");
            return (Criteria) this;
        }

        public Criteria andEstablishDateIsNull() {
            addCriterion("ESTABLISH_DATE is null");
            return (Criteria) this;
        }

        public Criteria andEstablishDateIsNotNull() {
            addCriterion("ESTABLISH_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andEstablishDateEqualTo(Date value) {
            addCriterionForJDBCDate("ESTABLISH_DATE =", value, "establishDate");
            return (Criteria) this;
        }

        public Criteria andEstablishDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("ESTABLISH_DATE <>", value, "establishDate");
            return (Criteria) this;
        }

        public Criteria andEstablishDateGreaterThan(Date value) {
            addCriterionForJDBCDate("ESTABLISH_DATE >", value, "establishDate");
            return (Criteria) this;
        }

        public Criteria andEstablishDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ESTABLISH_DATE >=", value, "establishDate");
            return (Criteria) this;
        }

        public Criteria andEstablishDateLessThan(Date value) {
            addCriterionForJDBCDate("ESTABLISH_DATE <", value, "establishDate");
            return (Criteria) this;
        }

        public Criteria andEstablishDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ESTABLISH_DATE <=", value, "establishDate");
            return (Criteria) this;
        }

        public Criteria andEstablishDateIn(List<Date> values) {
            addCriterionForJDBCDate("ESTABLISH_DATE in", values, "establishDate");
            return (Criteria) this;
        }

        public Criteria andEstablishDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("ESTABLISH_DATE not in", values, "establishDate");
            return (Criteria) this;
        }

        public Criteria andEstablishDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ESTABLISH_DATE between", value1, value2, "establishDate");
            return (Criteria) this;
        }

        public Criteria andEstablishDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ESTABLISH_DATE not between", value1, value2, "establishDate");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalIsNull() {
            addCriterion("REGISTERED_CAPITAL is null");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalIsNotNull() {
            addCriterion("REGISTERED_CAPITAL is not null");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalEqualTo(Integer value) {
            addCriterion("REGISTERED_CAPITAL =", value, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalNotEqualTo(Integer value) {
            addCriterion("REGISTERED_CAPITAL <>", value, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalGreaterThan(Integer value) {
            addCriterion("REGISTERED_CAPITAL >", value, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalGreaterThanOrEqualTo(Integer value) {
            addCriterion("REGISTERED_CAPITAL >=", value, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalLessThan(Integer value) {
            addCriterion("REGISTERED_CAPITAL <", value, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalLessThanOrEqualTo(Integer value) {
            addCriterion("REGISTERED_CAPITAL <=", value, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalIn(List<Integer> values) {
            addCriterion("REGISTERED_CAPITAL in", values, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalNotIn(List<Integer> values) {
            addCriterion("REGISTERED_CAPITAL not in", values, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalBetween(Integer value1, Integer value2) {
            addCriterion("REGISTERED_CAPITAL between", value1, value2, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalNotBetween(Integer value1, Integer value2) {
            addCriterion("REGISTERED_CAPITAL not between", value1, value2, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andOrganizationIsNull() {
            addCriterion("ORGANIZATION is null");
            return (Criteria) this;
        }

        public Criteria andOrganizationIsNotNull() {
            addCriterion("ORGANIZATION is not null");
            return (Criteria) this;
        }

        public Criteria andOrganizationEqualTo(String value) {
            addCriterion("ORGANIZATION =", value, "organization");
            return (Criteria) this;
        }

        public Criteria andOrganizationNotEqualTo(String value) {
            addCriterion("ORGANIZATION <>", value, "organization");
            return (Criteria) this;
        }

        public Criteria andOrganizationGreaterThan(String value) {
            addCriterion("ORGANIZATION >", value, "organization");
            return (Criteria) this;
        }

        public Criteria andOrganizationGreaterThanOrEqualTo(String value) {
            addCriterion("ORGANIZATION >=", value, "organization");
            return (Criteria) this;
        }

        public Criteria andOrganizationLessThan(String value) {
            addCriterion("ORGANIZATION <", value, "organization");
            return (Criteria) this;
        }

        public Criteria andOrganizationLessThanOrEqualTo(String value) {
            addCriterion("ORGANIZATION <=", value, "organization");
            return (Criteria) this;
        }

        public Criteria andOrganizationLike(String value) {
            addCriterion("ORGANIZATION like", value, "organization");
            return (Criteria) this;
        }

        public Criteria andOrganizationNotLike(String value) {
            addCriterion("ORGANIZATION not like", value, "organization");
            return (Criteria) this;
        }

        public Criteria andOrganizationIn(List<String> values) {
            addCriterion("ORGANIZATION in", values, "organization");
            return (Criteria) this;
        }

        public Criteria andOrganizationNotIn(List<String> values) {
            addCriterion("ORGANIZATION not in", values, "organization");
            return (Criteria) this;
        }

        public Criteria andOrganizationBetween(String value1, String value2) {
            addCriterion("ORGANIZATION between", value1, value2, "organization");
            return (Criteria) this;
        }

        public Criteria andOrganizationNotBetween(String value1, String value2) {
            addCriterion("ORGANIZATION not between", value1, value2, "organization");
            return (Criteria) this;
        }

        public Criteria andIntroductionIsNull() {
            addCriterion("INTRODUCTION is null");
            return (Criteria) this;
        }

        public Criteria andIntroductionIsNotNull() {
            addCriterion("INTRODUCTION is not null");
            return (Criteria) this;
        }

        public Criteria andIntroductionEqualTo(String value) {
            addCriterion("INTRODUCTION =", value, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionNotEqualTo(String value) {
            addCriterion("INTRODUCTION <>", value, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionGreaterThan(String value) {
            addCriterion("INTRODUCTION >", value, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionGreaterThanOrEqualTo(String value) {
            addCriterion("INTRODUCTION >=", value, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionLessThan(String value) {
            addCriterion("INTRODUCTION <", value, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionLessThanOrEqualTo(String value) {
            addCriterion("INTRODUCTION <=", value, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionLike(String value) {
            addCriterion("INTRODUCTION like", value, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionNotLike(String value) {
            addCriterion("INTRODUCTION not like", value, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionIn(List<String> values) {
            addCriterion("INTRODUCTION in", values, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionNotIn(List<String> values) {
            addCriterion("INTRODUCTION not in", values, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionBetween(String value1, String value2) {
            addCriterion("INTRODUCTION between", value1, value2, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionNotBetween(String value1, String value2) {
            addCriterion("INTRODUCTION not between", value1, value2, "introduction");
            return (Criteria) this;
        }

        public Criteria andScopeIsNull() {
            addCriterion("SCOPE is null");
            return (Criteria) this;
        }

        public Criteria andScopeIsNotNull() {
            addCriterion("SCOPE is not null");
            return (Criteria) this;
        }

        public Criteria andScopeEqualTo(String value) {
            addCriterion("SCOPE =", value, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeNotEqualTo(String value) {
            addCriterion("SCOPE <>", value, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeGreaterThan(String value) {
            addCriterion("SCOPE >", value, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeGreaterThanOrEqualTo(String value) {
            addCriterion("SCOPE >=", value, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeLessThan(String value) {
            addCriterion("SCOPE <", value, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeLessThanOrEqualTo(String value) {
            addCriterion("SCOPE <=", value, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeLike(String value) {
            addCriterion("SCOPE like", value, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeNotLike(String value) {
            addCriterion("SCOPE not like", value, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeIn(List<String> values) {
            addCriterion("SCOPE in", values, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeNotIn(List<String> values) {
            addCriterion("SCOPE not in", values, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeBetween(String value1, String value2) {
            addCriterion("SCOPE between", value1, value2, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeNotBetween(String value1, String value2) {
            addCriterion("SCOPE not between", value1, value2, "scope");
            return (Criteria) this;
        }

        public Criteria andStockStateIsNull() {
            addCriterion("STOCK_STATE is null");
            return (Criteria) this;
        }

        public Criteria andStockStateIsNotNull() {
            addCriterion("STOCK_STATE is not null");
            return (Criteria) this;
        }

        public Criteria andStockStateEqualTo(Byte value) {
            addCriterion("STOCK_STATE =", value, "stockState");
            return (Criteria) this;
        }

        public Criteria andStockStateNotEqualTo(Byte value) {
            addCriterion("STOCK_STATE <>", value, "stockState");
            return (Criteria) this;
        }

        public Criteria andStockStateGreaterThan(Byte value) {
            addCriterion("STOCK_STATE >", value, "stockState");
            return (Criteria) this;
        }

        public Criteria andStockStateGreaterThanOrEqualTo(Byte value) {
            addCriterion("STOCK_STATE >=", value, "stockState");
            return (Criteria) this;
        }

        public Criteria andStockStateLessThan(Byte value) {
            addCriterion("STOCK_STATE <", value, "stockState");
            return (Criteria) this;
        }

        public Criteria andStockStateLessThanOrEqualTo(Byte value) {
            addCriterion("STOCK_STATE <=", value, "stockState");
            return (Criteria) this;
        }

        public Criteria andStockStateIn(List<Byte> values) {
            addCriterion("STOCK_STATE in", values, "stockState");
            return (Criteria) this;
        }

        public Criteria andStockStateNotIn(List<Byte> values) {
            addCriterion("STOCK_STATE not in", values, "stockState");
            return (Criteria) this;
        }

        public Criteria andStockStateBetween(Byte value1, Byte value2) {
            addCriterion("STOCK_STATE between", value1, value2, "stockState");
            return (Criteria) this;
        }

        public Criteria andStockStateNotBetween(Byte value1, Byte value2) {
            addCriterion("STOCK_STATE not between", value1, value2, "stockState");
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