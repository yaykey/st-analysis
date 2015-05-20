package com.st.framework.module.stock.example;

import com.st.framework.module.example.BaseExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ThsOwnerExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ThsOwnerExample() {
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

        public Criteria andRoomNameIsNull() {
            addCriterion("ROOM_NAME is null");
            return (Criteria) this;
        }

        public Criteria andRoomNameIsNotNull() {
            addCriterion("ROOM_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andRoomNameEqualTo(String value) {
            addCriterion("ROOM_NAME =", value, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameNotEqualTo(String value) {
            addCriterion("ROOM_NAME <>", value, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameGreaterThan(String value) {
            addCriterion("ROOM_NAME >", value, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameGreaterThanOrEqualTo(String value) {
            addCriterion("ROOM_NAME >=", value, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameLessThan(String value) {
            addCriterion("ROOM_NAME <", value, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameLessThanOrEqualTo(String value) {
            addCriterion("ROOM_NAME <=", value, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameLike(String value) {
            addCriterion("ROOM_NAME like", value, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameNotLike(String value) {
            addCriterion("ROOM_NAME not like", value, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameIn(List<String> values) {
            addCriterion("ROOM_NAME in", values, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameNotIn(List<String> values) {
            addCriterion("ROOM_NAME not in", values, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameBetween(String value1, String value2) {
            addCriterion("ROOM_NAME between", value1, value2, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameNotBetween(String value1, String value2) {
            addCriterion("ROOM_NAME not between", value1, value2, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomUrlIsNull() {
            addCriterion("ROOM_URL is null");
            return (Criteria) this;
        }

        public Criteria andRoomUrlIsNotNull() {
            addCriterion("ROOM_URL is not null");
            return (Criteria) this;
        }

        public Criteria andRoomUrlEqualTo(String value) {
            addCriterion("ROOM_URL =", value, "roomUrl");
            return (Criteria) this;
        }

        public Criteria andRoomUrlNotEqualTo(String value) {
            addCriterion("ROOM_URL <>", value, "roomUrl");
            return (Criteria) this;
        }

        public Criteria andRoomUrlGreaterThan(String value) {
            addCriterion("ROOM_URL >", value, "roomUrl");
            return (Criteria) this;
        }

        public Criteria andRoomUrlGreaterThanOrEqualTo(String value) {
            addCriterion("ROOM_URL >=", value, "roomUrl");
            return (Criteria) this;
        }

        public Criteria andRoomUrlLessThan(String value) {
            addCriterion("ROOM_URL <", value, "roomUrl");
            return (Criteria) this;
        }

        public Criteria andRoomUrlLessThanOrEqualTo(String value) {
            addCriterion("ROOM_URL <=", value, "roomUrl");
            return (Criteria) this;
        }

        public Criteria andRoomUrlLike(String value) {
            addCriterion("ROOM_URL like", value, "roomUrl");
            return (Criteria) this;
        }

        public Criteria andRoomUrlNotLike(String value) {
            addCriterion("ROOM_URL not like", value, "roomUrl");
            return (Criteria) this;
        }

        public Criteria andRoomUrlIn(List<String> values) {
            addCriterion("ROOM_URL in", values, "roomUrl");
            return (Criteria) this;
        }

        public Criteria andRoomUrlNotIn(List<String> values) {
            addCriterion("ROOM_URL not in", values, "roomUrl");
            return (Criteria) this;
        }

        public Criteria andRoomUrlBetween(String value1, String value2) {
            addCriterion("ROOM_URL between", value1, value2, "roomUrl");
            return (Criteria) this;
        }

        public Criteria andRoomUrlNotBetween(String value1, String value2) {
            addCriterion("ROOM_URL not between", value1, value2, "roomUrl");
            return (Criteria) this;
        }

        public Criteria andRoomeTypeIsNull() {
            addCriterion("ROOME_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andRoomeTypeIsNotNull() {
            addCriterion("ROOME_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andRoomeTypeEqualTo(String value) {
            addCriterion("ROOME_TYPE =", value, "roomeType");
            return (Criteria) this;
        }

        public Criteria andRoomeTypeNotEqualTo(String value) {
            addCriterion("ROOME_TYPE <>", value, "roomeType");
            return (Criteria) this;
        }

        public Criteria andRoomeTypeGreaterThan(String value) {
            addCriterion("ROOME_TYPE >", value, "roomeType");
            return (Criteria) this;
        }

        public Criteria andRoomeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ROOME_TYPE >=", value, "roomeType");
            return (Criteria) this;
        }

        public Criteria andRoomeTypeLessThan(String value) {
            addCriterion("ROOME_TYPE <", value, "roomeType");
            return (Criteria) this;
        }

        public Criteria andRoomeTypeLessThanOrEqualTo(String value) {
            addCriterion("ROOME_TYPE <=", value, "roomeType");
            return (Criteria) this;
        }

        public Criteria andRoomeTypeLike(String value) {
            addCriterion("ROOME_TYPE like", value, "roomeType");
            return (Criteria) this;
        }

        public Criteria andRoomeTypeNotLike(String value) {
            addCriterion("ROOME_TYPE not like", value, "roomeType");
            return (Criteria) this;
        }

        public Criteria andRoomeTypeIn(List<String> values) {
            addCriterion("ROOME_TYPE in", values, "roomeType");
            return (Criteria) this;
        }

        public Criteria andRoomeTypeNotIn(List<String> values) {
            addCriterion("ROOME_TYPE not in", values, "roomeType");
            return (Criteria) this;
        }

        public Criteria andRoomeTypeBetween(String value1, String value2) {
            addCriterion("ROOME_TYPE between", value1, value2, "roomeType");
            return (Criteria) this;
        }

        public Criteria andRoomeTypeNotBetween(String value1, String value2) {
            addCriterion("ROOME_TYPE not between", value1, value2, "roomeType");
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