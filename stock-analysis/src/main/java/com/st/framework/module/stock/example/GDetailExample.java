package com.st.framework.module.stock.example;

import com.st.framework.module.example.BaseExample;
import java.util.ArrayList;
import java.util.List;

public class GDetailExample extends BaseExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	protected String stockCode;
	
	protected String stockType;

	public GDetailExample() {
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

		protected void addCriterion(String condition, Object value,
				String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1,
				Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andDateIdIsNull() {
			addCriterion("DATE_ID is null");
			return (Criteria) this;
		}

		public Criteria andDateIdIsNotNull() {
			addCriterion("DATE_ID is not null");
			return (Criteria) this;
		}

		public Criteria andDateIdEqualTo(Integer value) {
			addCriterion("DATE_ID =", value, "dateId");
			return (Criteria) this;
		}

		public Criteria andDateIdNotEqualTo(Integer value) {
			addCriterion("DATE_ID <>", value, "dateId");
			return (Criteria) this;
		}

		public Criteria andDateIdGreaterThan(Integer value) {
			addCriterion("DATE_ID >", value, "dateId");
			return (Criteria) this;
		}

		public Criteria andDateIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("DATE_ID >=", value, "dateId");
			return (Criteria) this;
		}

		public Criteria andDateIdLessThan(Integer value) {
			addCriterion("DATE_ID <", value, "dateId");
			return (Criteria) this;
		}

		public Criteria andDateIdLessThanOrEqualTo(Integer value) {
			addCriterion("DATE_ID <=", value, "dateId");
			return (Criteria) this;
		}

		public Criteria andDateIdIn(List<Integer> values) {
			addCriterion("DATE_ID in", values, "dateId");
			return (Criteria) this;
		}

		public Criteria andDateIdNotIn(List<Integer> values) {
			addCriterion("DATE_ID not in", values, "dateId");
			return (Criteria) this;
		}

		public Criteria andDateIdBetween(Integer value1, Integer value2) {
			addCriterion("DATE_ID between", value1, value2, "dateId");
			return (Criteria) this;
		}

		public Criteria andDateIdNotBetween(Integer value1, Integer value2) {
			addCriterion("DATE_ID not between", value1, value2, "dateId");
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

		public Criteria andPriceIsNull() {
			addCriterion("PRICE is null");
			return (Criteria) this;
		}

		public Criteria andPriceIsNotNull() {
			addCriterion("PRICE is not null");
			return (Criteria) this;
		}

		public Criteria andPriceEqualTo(Double value) {
			addCriterion("PRICE =", value, "price");
			return (Criteria) this;
		}

		public Criteria andPriceNotEqualTo(Double value) {
			addCriterion("PRICE <>", value, "price");
			return (Criteria) this;
		}

		public Criteria andPriceGreaterThan(Double value) {
			addCriterion("PRICE >", value, "price");
			return (Criteria) this;
		}

		public Criteria andPriceGreaterThanOrEqualTo(Double value) {
			addCriterion("PRICE >=", value, "price");
			return (Criteria) this;
		}

		public Criteria andPriceLessThan(Double value) {
			addCriterion("PRICE <", value, "price");
			return (Criteria) this;
		}

		public Criteria andPriceLessThanOrEqualTo(Double value) {
			addCriterion("PRICE <=", value, "price");
			return (Criteria) this;
		}

		public Criteria andPriceIn(List<Double> values) {
			addCriterion("PRICE in", values, "price");
			return (Criteria) this;
		}

		public Criteria andPriceNotIn(List<Double> values) {
			addCriterion("PRICE not in", values, "price");
			return (Criteria) this;
		}

		public Criteria andPriceBetween(Double value1, Double value2) {
			addCriterion("PRICE between", value1, value2, "price");
			return (Criteria) this;
		}

		public Criteria andPriceNotBetween(Double value1, Double value2) {
			addCriterion("PRICE not between", value1, value2, "price");
			return (Criteria) this;
		}

		public Criteria andVolIsNull() {
			addCriterion("VOL is null");
			return (Criteria) this;
		}

		public Criteria andVolIsNotNull() {
			addCriterion("VOL is not null");
			return (Criteria) this;
		}

		public Criteria andVolEqualTo(Integer value) {
			addCriterion("VOL =", value, "vol");
			return (Criteria) this;
		}

		public Criteria andVolNotEqualTo(Integer value) {
			addCriterion("VOL <>", value, "vol");
			return (Criteria) this;
		}

		public Criteria andVolGreaterThan(Integer value) {
			addCriterion("VOL >", value, "vol");
			return (Criteria) this;
		}

		public Criteria andVolGreaterThanOrEqualTo(Integer value) {
			addCriterion("VOL >=", value, "vol");
			return (Criteria) this;
		}

		public Criteria andVolLessThan(Integer value) {
			addCriterion("VOL <", value, "vol");
			return (Criteria) this;
		}

		public Criteria andVolLessThanOrEqualTo(Integer value) {
			addCriterion("VOL <=", value, "vol");
			return (Criteria) this;
		}

		public Criteria andVolIn(List<Integer> values) {
			addCriterion("VOL in", values, "vol");
			return (Criteria) this;
		}

		public Criteria andVolNotIn(List<Integer> values) {
			addCriterion("VOL not in", values, "vol");
			return (Criteria) this;
		}

		public Criteria andVolBetween(Integer value1, Integer value2) {
			addCriterion("VOL between", value1, value2, "vol");
			return (Criteria) this;
		}

		public Criteria andVolNotBetween(Integer value1, Integer value2) {
			addCriterion("VOL not between", value1, value2, "vol");
			return (Criteria) this;
		}

		public Criteria andAmoIsNull() {
			addCriterion("AMO is null");
			return (Criteria) this;
		}

		public Criteria andAmoIsNotNull() {
			addCriterion("AMO is not null");
			return (Criteria) this;
		}

		public Criteria andAmoEqualTo(Integer value) {
			addCriterion("AMO =", value, "amo");
			return (Criteria) this;
		}

		public Criteria andAmoNotEqualTo(Integer value) {
			addCriterion("AMO <>", value, "amo");
			return (Criteria) this;
		}

		public Criteria andAmoGreaterThan(Integer value) {
			addCriterion("AMO >", value, "amo");
			return (Criteria) this;
		}

		public Criteria andAmoGreaterThanOrEqualTo(Integer value) {
			addCriterion("AMO >=", value, "amo");
			return (Criteria) this;
		}

		public Criteria andAmoLessThan(Integer value) {
			addCriterion("AMO <", value, "amo");
			return (Criteria) this;
		}

		public Criteria andAmoLessThanOrEqualTo(Integer value) {
			addCriterion("AMO <=", value, "amo");
			return (Criteria) this;
		}

		public Criteria andAmoIn(List<Integer> values) {
			addCriterion("AMO in", values, "amo");
			return (Criteria) this;
		}

		public Criteria andAmoNotIn(List<Integer> values) {
			addCriterion("AMO not in", values, "amo");
			return (Criteria) this;
		}

		public Criteria andAmoBetween(Integer value1, Integer value2) {
			addCriterion("AMO between", value1, value2, "amo");
			return (Criteria) this;
		}

		public Criteria andAmoNotBetween(Integer value1, Integer value2) {
			addCriterion("AMO not between", value1, value2, "amo");
			return (Criteria) this;
		}

		public Criteria andNatureIsNull() {
			addCriterion("NATURE is null");
			return (Criteria) this;
		}

		public Criteria andNatureIsNotNull() {
			addCriterion("NATURE is not null");
			return (Criteria) this;
		}

		public Criteria andNatureEqualTo(String value) {
			addCriterion("NATURE =", value, "nature");
			return (Criteria) this;
		}

		public Criteria andNatureNotEqualTo(String value) {
			addCriterion("NATURE <>", value, "nature");
			return (Criteria) this;
		}

		public Criteria andNatureGreaterThan(String value) {
			addCriterion("NATURE >", value, "nature");
			return (Criteria) this;
		}

		public Criteria andNatureGreaterThanOrEqualTo(String value) {
			addCriterion("NATURE >=", value, "nature");
			return (Criteria) this;
		}

		public Criteria andNatureLessThan(String value) {
			addCriterion("NATURE <", value, "nature");
			return (Criteria) this;
		}

		public Criteria andNatureLessThanOrEqualTo(String value) {
			addCriterion("NATURE <=", value, "nature");
			return (Criteria) this;
		}

		public Criteria andNatureLike(String value) {
			addCriterion("NATURE like", value, "nature");
			return (Criteria) this;
		}

		public Criteria andNatureNotLike(String value) {
			addCriterion("NATURE not like", value, "nature");
			return (Criteria) this;
		}

		public Criteria andNatureIn(List<String> values) {
			addCriterion("NATURE in", values, "nature");
			return (Criteria) this;
		}

		public Criteria andNatureNotIn(List<String> values) {
			addCriterion("NATURE not in", values, "nature");
			return (Criteria) this;
		}

		public Criteria andNatureBetween(String value1, String value2) {
			addCriterion("NATURE between", value1, value2, "nature");
			return (Criteria) this;
		}

		public Criteria andNatureNotBetween(String value1, String value2) {
			addCriterion("NATURE not between", value1, value2, "nature");
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
			addCriterion("PRICE_CHANGES between", value1, value2,
					"priceChanges");
			return (Criteria) this;
		}

		public Criteria andPriceChangesNotBetween(Double value1, Double value2) {
			addCriterion("PRICE_CHANGES not between", value1, value2,
					"priceChanges");
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

		protected Criterion(String condition, Object value, Object secondValue,
				String typeHandler) {
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

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getStockType() {
		return stockType;
	}

	public void setStockType(String stockType) {
		this.stockType = stockType;
	}
}