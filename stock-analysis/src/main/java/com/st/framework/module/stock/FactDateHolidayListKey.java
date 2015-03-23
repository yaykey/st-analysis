package com.st.framework.module.stock;

public class FactDateHolidayListKey {
    private String festival;

    private String date;

    public String getFestival() {
        return festival;
    }

    public void setFestival(String festival) {
        this.festival = festival == null ? null : festival.trim();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
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
        FactDateHolidayListKey other = (FactDateHolidayListKey) that;
        return (this.getFestival() == null ? other.getFestival() == null : this.getFestival().equals(other.getFestival()))
            && (this.getDate() == null ? other.getDate() == null : this.getDate().equals(other.getDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFestival() == null) ? 0 : getFestival().hashCode());
        result = prime * result + ((getDate() == null) ? 0 : getDate().hashCode());
        return result;
    }
}