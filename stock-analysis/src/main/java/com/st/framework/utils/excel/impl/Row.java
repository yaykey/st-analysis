package com.st.framework.utils.excel.impl;



public interface Row {

	public Cell getCell(int i);
	
	public Cell createCell(int i);
	
	public void removeCell(Cell cell);
	
	public int getRowNum();
}
