package com.st.framework.utils.excel;

import org.apache.commons.lang3.builder.ToStringBuilder;



/**
 * The Class FieldInfo.
 */
public class FieldInfo {
	/**
	 * 列数
	 */
	private int columnIndex;
	
	/**
	 * 列类型
	 */
	private String id;
	
	/**
	 * 列名称
	 */
	private String name;
	
	/**
	 * 列数据类型
	 */
	private CellType type;
	

	/**
	 * Gets the 列数.
	 *
	 * @return the 列数
	 */
	public int getColumnIndex() {
		return columnIndex;
	}

	/**
	 * Sets the 列数.
	 *
	 * @param columnIndex the new 列数
	 */
	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}

	/**
	 * Gets the 列类型.
	 *
	 * @return the 列类型
	 */
	public final String getId() {
		return id;
	}

	/**
	 * Sets the 列类型.
	 *
	 * @param id the new 列类型
	 */
	public final void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the 列名称.
	 *
	 * @return the 列名称
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Sets the 列名称.
	 *
	 * @param name the new 列名称
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the 列数据类型.
	 *
	 * @return the 列数据类型
	 */
	public final CellType getType() {
		return type;
	}

	/**
	 * Sets the 列数据类型.
	 *
	 * @param type the new 列数据类型
	 */
	public final void setType(CellType type) {
		this.type = type;
	}
	
	public String toString () {
		return ToStringBuilder.reflectionToString(this);
	}
}
