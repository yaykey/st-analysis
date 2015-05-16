package com.st.framework.module.stock;

import com.st.framework.module.PersistentObject;

public class GLHBSecurities extends PersistentObject{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6019378140326776380L;

	private Long id;

    private String name;
    
    public GLHBSecurities () {
    	
    }
    
    public GLHBSecurities (Long id, String name) {
    	this.id = id;
    	this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
        GLHBSecurities other = (GLHBSecurities) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        return result;
    }
}