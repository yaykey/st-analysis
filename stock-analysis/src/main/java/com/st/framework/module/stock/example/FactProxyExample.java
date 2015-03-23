package com.st.framework.module.stock.example;

import com.st.framework.module.example.BaseExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FactProxyExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FactProxyExample() {
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

        public Criteria andProxyIpIsNull() {
            addCriterion("PROXY_IP is null");
            return (Criteria) this;
        }

        public Criteria andProxyIpIsNotNull() {
            addCriterion("PROXY_IP is not null");
            return (Criteria) this;
        }

        public Criteria andProxyIpEqualTo(String value) {
            addCriterion("PROXY_IP =", value, "proxyIp");
            return (Criteria) this;
        }

        public Criteria andProxyIpNotEqualTo(String value) {
            addCriterion("PROXY_IP <>", value, "proxyIp");
            return (Criteria) this;
        }

        public Criteria andProxyIpGreaterThan(String value) {
            addCriterion("PROXY_IP >", value, "proxyIp");
            return (Criteria) this;
        }

        public Criteria andProxyIpGreaterThanOrEqualTo(String value) {
            addCriterion("PROXY_IP >=", value, "proxyIp");
            return (Criteria) this;
        }

        public Criteria andProxyIpLessThan(String value) {
            addCriterion("PROXY_IP <", value, "proxyIp");
            return (Criteria) this;
        }

        public Criteria andProxyIpLessThanOrEqualTo(String value) {
            addCriterion("PROXY_IP <=", value, "proxyIp");
            return (Criteria) this;
        }

        public Criteria andProxyIpLike(String value) {
            addCriterion("PROXY_IP like", value, "proxyIp");
            return (Criteria) this;
        }

        public Criteria andProxyIpNotLike(String value) {
            addCriterion("PROXY_IP not like", value, "proxyIp");
            return (Criteria) this;
        }

        public Criteria andProxyIpIn(List<String> values) {
            addCriterion("PROXY_IP in", values, "proxyIp");
            return (Criteria) this;
        }

        public Criteria andProxyIpNotIn(List<String> values) {
            addCriterion("PROXY_IP not in", values, "proxyIp");
            return (Criteria) this;
        }

        public Criteria andProxyIpBetween(String value1, String value2) {
            addCriterion("PROXY_IP between", value1, value2, "proxyIp");
            return (Criteria) this;
        }

        public Criteria andProxyIpNotBetween(String value1, String value2) {
            addCriterion("PROXY_IP not between", value1, value2, "proxyIp");
            return (Criteria) this;
        }

        public Criteria andProxyPortIsNull() {
            addCriterion("PROXY_PORT is null");
            return (Criteria) this;
        }

        public Criteria andProxyPortIsNotNull() {
            addCriterion("PROXY_PORT is not null");
            return (Criteria) this;
        }

        public Criteria andProxyPortEqualTo(Integer value) {
            addCriterion("PROXY_PORT =", value, "proxyPort");
            return (Criteria) this;
        }

        public Criteria andProxyPortNotEqualTo(Integer value) {
            addCriterion("PROXY_PORT <>", value, "proxyPort");
            return (Criteria) this;
        }

        public Criteria andProxyPortGreaterThan(Integer value) {
            addCriterion("PROXY_PORT >", value, "proxyPort");
            return (Criteria) this;
        }

        public Criteria andProxyPortGreaterThanOrEqualTo(Integer value) {
            addCriterion("PROXY_PORT >=", value, "proxyPort");
            return (Criteria) this;
        }

        public Criteria andProxyPortLessThan(Integer value) {
            addCriterion("PROXY_PORT <", value, "proxyPort");
            return (Criteria) this;
        }

        public Criteria andProxyPortLessThanOrEqualTo(Integer value) {
            addCriterion("PROXY_PORT <=", value, "proxyPort");
            return (Criteria) this;
        }

        public Criteria andProxyPortIn(List<Integer> values) {
            addCriterion("PROXY_PORT in", values, "proxyPort");
            return (Criteria) this;
        }

        public Criteria andProxyPortNotIn(List<Integer> values) {
            addCriterion("PROXY_PORT not in", values, "proxyPort");
            return (Criteria) this;
        }

        public Criteria andProxyPortBetween(Integer value1, Integer value2) {
            addCriterion("PROXY_PORT between", value1, value2, "proxyPort");
            return (Criteria) this;
        }

        public Criteria andProxyPortNotBetween(Integer value1, Integer value2) {
            addCriterion("PROXY_PORT not between", value1, value2, "proxyPort");
            return (Criteria) this;
        }

        public Criteria andLocalIsNull() {
            addCriterion("LOCAL is null");
            return (Criteria) this;
        }

        public Criteria andLocalIsNotNull() {
            addCriterion("LOCAL is not null");
            return (Criteria) this;
        }

        public Criteria andLocalEqualTo(String value) {
            addCriterion("LOCAL =", value, "local");
            return (Criteria) this;
        }

        public Criteria andLocalNotEqualTo(String value) {
            addCriterion("LOCAL <>", value, "local");
            return (Criteria) this;
        }

        public Criteria andLocalGreaterThan(String value) {
            addCriterion("LOCAL >", value, "local");
            return (Criteria) this;
        }

        public Criteria andLocalGreaterThanOrEqualTo(String value) {
            addCriterion("LOCAL >=", value, "local");
            return (Criteria) this;
        }

        public Criteria andLocalLessThan(String value) {
            addCriterion("LOCAL <", value, "local");
            return (Criteria) this;
        }

        public Criteria andLocalLessThanOrEqualTo(String value) {
            addCriterion("LOCAL <=", value, "local");
            return (Criteria) this;
        }

        public Criteria andLocalLike(String value) {
            addCriterion("LOCAL like", value, "local");
            return (Criteria) this;
        }

        public Criteria andLocalNotLike(String value) {
            addCriterion("LOCAL not like", value, "local");
            return (Criteria) this;
        }

        public Criteria andLocalIn(List<String> values) {
            addCriterion("LOCAL in", values, "local");
            return (Criteria) this;
        }

        public Criteria andLocalNotIn(List<String> values) {
            addCriterion("LOCAL not in", values, "local");
            return (Criteria) this;
        }

        public Criteria andLocalBetween(String value1, String value2) {
            addCriterion("LOCAL between", value1, value2, "local");
            return (Criteria) this;
        }

        public Criteria andLocalNotBetween(String value1, String value2) {
            addCriterion("LOCAL not between", value1, value2, "local");
            return (Criteria) this;
        }

        public Criteria andTestSpeedIsNull() {
            addCriterion("TEST_SPEED is null");
            return (Criteria) this;
        }

        public Criteria andTestSpeedIsNotNull() {
            addCriterion("TEST_SPEED is not null");
            return (Criteria) this;
        }

        public Criteria andTestSpeedEqualTo(Integer value) {
            addCriterion("TEST_SPEED =", value, "testSpeed");
            return (Criteria) this;
        }

        public Criteria andTestSpeedNotEqualTo(Integer value) {
            addCriterion("TEST_SPEED <>", value, "testSpeed");
            return (Criteria) this;
        }

        public Criteria andTestSpeedGreaterThan(Integer value) {
            addCriterion("TEST_SPEED >", value, "testSpeed");
            return (Criteria) this;
        }

        public Criteria andTestSpeedGreaterThanOrEqualTo(Integer value) {
            addCriterion("TEST_SPEED >=", value, "testSpeed");
            return (Criteria) this;
        }

        public Criteria andTestSpeedLessThan(Integer value) {
            addCriterion("TEST_SPEED <", value, "testSpeed");
            return (Criteria) this;
        }

        public Criteria andTestSpeedLessThanOrEqualTo(Integer value) {
            addCriterion("TEST_SPEED <=", value, "testSpeed");
            return (Criteria) this;
        }

        public Criteria andTestSpeedIn(List<Integer> values) {
            addCriterion("TEST_SPEED in", values, "testSpeed");
            return (Criteria) this;
        }

        public Criteria andTestSpeedNotIn(List<Integer> values) {
            addCriterion("TEST_SPEED not in", values, "testSpeed");
            return (Criteria) this;
        }

        public Criteria andTestSpeedBetween(Integer value1, Integer value2) {
            addCriterion("TEST_SPEED between", value1, value2, "testSpeed");
            return (Criteria) this;
        }

        public Criteria andTestSpeedNotBetween(Integer value1, Integer value2) {
            addCriterion("TEST_SPEED not between", value1, value2, "testSpeed");
            return (Criteria) this;
        }

        public Criteria andActiveIsNull() {
            addCriterion("ACTIVE is null");
            return (Criteria) this;
        }

        public Criteria andActiveIsNotNull() {
            addCriterion("ACTIVE is not null");
            return (Criteria) this;
        }

        public Criteria andActiveEqualTo(Boolean value) {
            addCriterion("ACTIVE =", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveNotEqualTo(Boolean value) {
            addCriterion("ACTIVE <>", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveGreaterThan(Boolean value) {
            addCriterion("ACTIVE >", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveGreaterThanOrEqualTo(Boolean value) {
            addCriterion("ACTIVE >=", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveLessThan(Boolean value) {
            addCriterion("ACTIVE <", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveLessThanOrEqualTo(Boolean value) {
            addCriterion("ACTIVE <=", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveIn(List<Boolean> values) {
            addCriterion("ACTIVE in", values, "active");
            return (Criteria) this;
        }

        public Criteria andActiveNotIn(List<Boolean> values) {
            addCriterion("ACTIVE not in", values, "active");
            return (Criteria) this;
        }

        public Criteria andActiveBetween(Boolean value1, Boolean value2) {
            addCriterion("ACTIVE between", value1, value2, "active");
            return (Criteria) this;
        }

        public Criteria andActiveNotBetween(Boolean value1, Boolean value2) {
            addCriterion("ACTIVE not between", value1, value2, "active");
            return (Criteria) this;
        }

        public Criteria andLoseFactorIsNull() {
            addCriterion("LOSE_FACTOR is null");
            return (Criteria) this;
        }

        public Criteria andLoseFactorIsNotNull() {
            addCriterion("LOSE_FACTOR is not null");
            return (Criteria) this;
        }

        public Criteria andLoseFactorEqualTo(Integer value) {
            addCriterion("LOSE_FACTOR =", value, "loseFactor");
            return (Criteria) this;
        }

        public Criteria andLoseFactorNotEqualTo(Integer value) {
            addCriterion("LOSE_FACTOR <>", value, "loseFactor");
            return (Criteria) this;
        }

        public Criteria andLoseFactorGreaterThan(Integer value) {
            addCriterion("LOSE_FACTOR >", value, "loseFactor");
            return (Criteria) this;
        }

        public Criteria andLoseFactorGreaterThanOrEqualTo(Integer value) {
            addCriterion("LOSE_FACTOR >=", value, "loseFactor");
            return (Criteria) this;
        }

        public Criteria andLoseFactorLessThan(Integer value) {
            addCriterion("LOSE_FACTOR <", value, "loseFactor");
            return (Criteria) this;
        }

        public Criteria andLoseFactorLessThanOrEqualTo(Integer value) {
            addCriterion("LOSE_FACTOR <=", value, "loseFactor");
            return (Criteria) this;
        }

        public Criteria andLoseFactorIn(List<Integer> values) {
            addCriterion("LOSE_FACTOR in", values, "loseFactor");
            return (Criteria) this;
        }

        public Criteria andLoseFactorNotIn(List<Integer> values) {
            addCriterion("LOSE_FACTOR not in", values, "loseFactor");
            return (Criteria) this;
        }

        public Criteria andLoseFactorBetween(Integer value1, Integer value2) {
            addCriterion("LOSE_FACTOR between", value1, value2, "loseFactor");
            return (Criteria) this;
        }

        public Criteria andLoseFactorNotBetween(Integer value1, Integer value2) {
            addCriterion("LOSE_FACTOR not between", value1, value2, "loseFactor");
            return (Criteria) this;
        }

        public Criteria andTestDateIsNull() {
            addCriterion("TEST_DATE is null");
            return (Criteria) this;
        }

        public Criteria andTestDateIsNotNull() {
            addCriterion("TEST_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andTestDateEqualTo(Date value) {
            addCriterion("TEST_DATE =", value, "testDate");
            return (Criteria) this;
        }

        public Criteria andTestDateNotEqualTo(Date value) {
            addCriterion("TEST_DATE <>", value, "testDate");
            return (Criteria) this;
        }

        public Criteria andTestDateGreaterThan(Date value) {
            addCriterion("TEST_DATE >", value, "testDate");
            return (Criteria) this;
        }

        public Criteria andTestDateGreaterThanOrEqualTo(Date value) {
            addCriterion("TEST_DATE >=", value, "testDate");
            return (Criteria) this;
        }

        public Criteria andTestDateLessThan(Date value) {
            addCriterion("TEST_DATE <", value, "testDate");
            return (Criteria) this;
        }

        public Criteria andTestDateLessThanOrEqualTo(Date value) {
            addCriterion("TEST_DATE <=", value, "testDate");
            return (Criteria) this;
        }

        public Criteria andTestDateIn(List<Date> values) {
            addCriterion("TEST_DATE in", values, "testDate");
            return (Criteria) this;
        }

        public Criteria andTestDateNotIn(List<Date> values) {
            addCriterion("TEST_DATE not in", values, "testDate");
            return (Criteria) this;
        }

        public Criteria andTestDateBetween(Date value1, Date value2) {
            addCriterion("TEST_DATE between", value1, value2, "testDate");
            return (Criteria) this;
        }

        public Criteria andTestDateNotBetween(Date value1, Date value2) {
            addCriterion("TEST_DATE not between", value1, value2, "testDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("CREATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("CREATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("CREATE_DATE =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("CREATE_DATE <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("CREATE_DATE >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("CREATE_DATE <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("CREATE_DATE in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("CREATE_DATE not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE not between", value1, value2, "createDate");
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