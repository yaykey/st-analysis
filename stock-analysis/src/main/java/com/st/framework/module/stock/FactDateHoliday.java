package com.st.framework.module.stock;

public class FactDateHoliday {
    private String festival;

    private String name;

    private String description;

    private String rest;

    private Integer listnumbaidu;

    public String getFestival() {
        return festival;
    }

    public void setFestival(String festival) {
        this.festival = festival == null ? null : festival.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getRest() {
        return rest;
    }

    public void setRest(String rest) {
        this.rest = rest == null ? null : rest.trim();
    }

    public Integer getListnumbaidu() {
        return listnumbaidu;
    }

    public void setListnumbaidu(Integer listnumbaidu) {
        this.listnumbaidu = listnumbaidu;
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
        FactDateHoliday other = (FactDateHoliday) that;
        return (this.getFestival() == null ? other.getFestival() == null : this.getFestival().equals(other.getFestival()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getRest() == null ? other.getRest() == null : this.getRest().equals(other.getRest()))
            && (this.getListnumbaidu() == null ? other.getListnumbaidu() == null : this.getListnumbaidu().equals(other.getListnumbaidu()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFestival() == null) ? 0 : getFestival().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getRest() == null) ? 0 : getRest().hashCode());
        result = prime * result + ((getListnumbaidu() == null) ? 0 : getListnumbaidu().hashCode());
        return result;
    }
}