package com.st.framework.utils.excel.impl;



import java.io.IOException;
import java.io.OutputStream;

public interface Workbook {

	public Sheet getSheetAt(int i);
	
	public Sheet createSheet(String name);
	
	public void write(OutputStream out)throws IOException;

	public int getSheetSize();
}
