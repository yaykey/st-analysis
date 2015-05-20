package com.st.framework.module.stock.example;

import com.st.framework.module.example.BaseExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class GQgqpExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GQgqpExample() {
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

        public Criteria andCodeIsNull() {
            addCriterion("CODE is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("CODE =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("CODE <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("CODE >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CODE >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("CODE <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("CODE <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("CODE like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("CODE not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("CODE in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("CODE not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("CODE between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("CODE not between", value1, value2, "code");
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

        public Criteria andSourceTypeIsNull() {
            addCriterion("SOURCE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andSourceTypeIsNotNull() {
            addCriterion("SOURCE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andSourceTypeEqualTo(String value) {
            addCriterion("SOURCE_TYPE =", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNotEqualTo(String value) {
            addCriterion("SOURCE_TYPE <>", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeGreaterThan(String value) {
            addCriterion("SOURCE_TYPE >", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeGreaterThanOrEqualTo(String value) {
            addCriterion("SOURCE_TYPE >=", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeLessThan(String value) {
            addCriterion("SOURCE_TYPE <", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeLessThanOrEqualTo(String value) {
            addCriterion("SOURCE_TYPE <=", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeLike(String value) {
            addCriterion("SOURCE_TYPE like", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNotLike(String value) {
            addCriterion("SOURCE_TYPE not like", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeIn(List<String> values) {
            addCriterion("SOURCE_TYPE in", values, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNotIn(List<String> values) {
            addCriterion("SOURCE_TYPE not in", values, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeBetween(String value1, String value2) {
            addCriterion("SOURCE_TYPE between", value1, value2, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNotBetween(String value1, String value2) {
            addCriterion("SOURCE_TYPE not between", value1, value2, "sourceType");
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

        public Criteria andCommentIsNull() {
            addCriterion("COMMENT is null");
            return (Criteria) this;
        }

        public Criteria andCommentIsNotNull() {
            addCriterion("COMMENT is not null");
            return (Criteria) this;
        }

        public Criteria andCommentEqualTo(String value) {
            addCriterion("COMMENT =", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotEqualTo(String value) {
            addCriterion("COMMENT <>", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThan(String value) {
            addCriterion("COMMENT >", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThanOrEqualTo(String value) {
            addCriterion("COMMENT >=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThan(String value) {
            addCriterion("COMMENT <", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThanOrEqualTo(String value) {
            addCriterion("COMMENT <=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLike(String value) {
            addCriterion("COMMENT like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotLike(String value) {
            addCriterion("COMMENT not like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentIn(List<String> values) {
            addCriterion("COMMENT in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotIn(List<String> values) {
            addCriterion("COMMENT not in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentBetween(String value1, String value2) {
            addCriterion("COMMENT between", value1, value2, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotBetween(String value1, String value2) {
            addCriterion("COMMENT not between", value1, value2, "comment");
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

        public Criteria andNetChangeIsNull() {
            addCriterion("NET_CHANGE is null");
            return (Criteria) this;
        }

        public Criteria andNetChangeIsNotNull() {
            addCriterion("NET_CHANGE is not null");
            return (Criteria) this;
        }

        public Criteria andNetChangeEqualTo(Double value) {
            addCriterion("NET_CHANGE =", value, "netChange");
            return (Criteria) this;
        }

        public Criteria andNetChangeNotEqualTo(Double value) {
            addCriterion("NET_CHANGE <>", value, "netChange");
            return (Criteria) this;
        }

        public Criteria andNetChangeGreaterThan(Double value) {
            addCriterion("NET_CHANGE >", value, "netChange");
            return (Criteria) this;
        }

        public Criteria andNetChangeGreaterThanOrEqualTo(Double value) {
            addCriterion("NET_CHANGE >=", value, "netChange");
            return (Criteria) this;
        }

        public Criteria andNetChangeLessThan(Double value) {
            addCriterion("NET_CHANGE <", value, "netChange");
            return (Criteria) this;
        }

        public Criteria andNetChangeLessThanOrEqualTo(Double value) {
            addCriterion("NET_CHANGE <=", value, "netChange");
            return (Criteria) this;
        }

        public Criteria andNetChangeIn(List<Double> values) {
            addCriterion("NET_CHANGE in", values, "netChange");
            return (Criteria) this;
        }

        public Criteria andNetChangeNotIn(List<Double> values) {
            addCriterion("NET_CHANGE not in", values, "netChange");
            return (Criteria) this;
        }

        public Criteria andNetChangeBetween(Double value1, Double value2) {
            addCriterion("NET_CHANGE between", value1, value2, "netChange");
            return (Criteria) this;
        }

        public Criteria andNetChangeNotBetween(Double value1, Double value2) {
            addCriterion("NET_CHANGE not between", value1, value2, "netChange");
            return (Criteria) this;
        }

        public Criteria andNetChangePerIsNull() {
            addCriterion("NET_CHANGE_PER is null");
            return (Criteria) this;
        }

        public Criteria andNetChangePerIsNotNull() {
            addCriterion("NET_CHANGE_PER is not null");
            return (Criteria) this;
        }

        public Criteria andNetChangePerEqualTo(String value) {
            addCriterion("NET_CHANGE_PER =", value, "netChangePer");
            return (Criteria) this;
        }

        public Criteria andNetChangePerNotEqualTo(String value) {
            addCriterion("NET_CHANGE_PER <>", value, "netChangePer");
            return (Criteria) this;
        }

        public Criteria andNetChangePerGreaterThan(String value) {
            addCriterion("NET_CHANGE_PER >", value, "netChangePer");
            return (Criteria) this;
        }

        public Criteria andNetChangePerGreaterThanOrEqualTo(String value) {
            addCriterion("NET_CHANGE_PER >=", value, "netChangePer");
            return (Criteria) this;
        }

        public Criteria andNetChangePerLessThan(String value) {
            addCriterion("NET_CHANGE_PER <", value, "netChangePer");
            return (Criteria) this;
        }

        public Criteria andNetChangePerLessThanOrEqualTo(String value) {
            addCriterion("NET_CHANGE_PER <=", value, "netChangePer");
            return (Criteria) this;
        }

        public Criteria andNetChangePerLike(String value) {
            addCriterion("NET_CHANGE_PER like", value, "netChangePer");
            return (Criteria) this;
        }

        public Criteria andNetChangePerNotLike(String value) {
            addCriterion("NET_CHANGE_PER not like", value, "netChangePer");
            return (Criteria) this;
        }

        public Criteria andNetChangePerIn(List<String> values) {
            addCriterion("NET_CHANGE_PER in", values, "netChangePer");
            return (Criteria) this;
        }

        public Criteria andNetChangePerNotIn(List<String> values) {
            addCriterion("NET_CHANGE_PER not in", values, "netChangePer");
            return (Criteria) this;
        }

        public Criteria andNetChangePerBetween(String value1, String value2) {
            addCriterion("NET_CHANGE_PER between", value1, value2, "netChangePer");
            return (Criteria) this;
        }

        public Criteria andNetChangePerNotBetween(String value1, String value2) {
            addCriterion("NET_CHANGE_PER not between", value1, value2, "netChangePer");
            return (Criteria) this;
        }

        public Criteria andPrevCloseIsNull() {
            addCriterion("PREV_CLOSE is null");
            return (Criteria) this;
        }

        public Criteria andPrevCloseIsNotNull() {
            addCriterion("PREV_CLOSE is not null");
            return (Criteria) this;
        }

        public Criteria andPrevCloseEqualTo(Double value) {
            addCriterion("PREV_CLOSE =", value, "prevClose");
            return (Criteria) this;
        }

        public Criteria andPrevCloseNotEqualTo(Double value) {
            addCriterion("PREV_CLOSE <>", value, "prevClose");
            return (Criteria) this;
        }

        public Criteria andPrevCloseGreaterThan(Double value) {
            addCriterion("PREV_CLOSE >", value, "prevClose");
            return (Criteria) this;
        }

        public Criteria andPrevCloseGreaterThanOrEqualTo(Double value) {
            addCriterion("PREV_CLOSE >=", value, "prevClose");
            return (Criteria) this;
        }

        public Criteria andPrevCloseLessThan(Double value) {
            addCriterion("PREV_CLOSE <", value, "prevClose");
            return (Criteria) this;
        }

        public Criteria andPrevCloseLessThanOrEqualTo(Double value) {
            addCriterion("PREV_CLOSE <=", value, "prevClose");
            return (Criteria) this;
        }

        public Criteria andPrevCloseIn(List<Double> values) {
            addCriterion("PREV_CLOSE in", values, "prevClose");
            return (Criteria) this;
        }

        public Criteria andPrevCloseNotIn(List<Double> values) {
            addCriterion("PREV_CLOSE not in", values, "prevClose");
            return (Criteria) this;
        }

        public Criteria andPrevCloseBetween(Double value1, Double value2) {
            addCriterion("PREV_CLOSE between", value1, value2, "prevClose");
            return (Criteria) this;
        }

        public Criteria andPrevCloseNotBetween(Double value1, Double value2) {
            addCriterion("PREV_CLOSE not between", value1, value2, "prevClose");
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