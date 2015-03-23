package com.st.framework.module.stock;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class FactDateAlmanac {
    private String date;

    private String suit;

    private String avoid;

    private Integer week;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit == null ? null : suit.trim();
    }

    public String getAvoid() {
        return avoid;
    }

    public void setAvoid(String avoid) {
        this.avoid = avoid == null ? null : avoid.trim();
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        FactDateAlmanac other = (FactDateAlmanac) that;
        return (this.getDate() == null ? other.getDate() == null : this.getDate().equals(other.getDate()))
            && (this.getSuit() == null ? other.getSuit() == null : this.getSuit().equals(other.getSuit()))
            && (this.getAvoid() == null ? other.getAvoid() == null : this.getAvoid().equals(other.getAvoid()))
            && (this.getWeek() == null ? other.getWeek() == null : this.getWeek().equals(other.getWeek()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDate() == null) ? 0 : getDate().hashCode());
        result = prime * result + ((getSuit() == null) ? 0 : getSuit().hashCode());
        result = prime * result + ((getAvoid() == null) ? 0 : getAvoid().hashCode());
        result = prime * result + ((getWeek() == null) ? 0 : getWeek().hashCode());
        return result;
    }
    
    public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}