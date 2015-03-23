package com.st.framework.utils.excel.impl;



import java.util.Calendar;
import java.util.Date;

public interface Cell {
	
	public static final int CELL_TYPE_NUMERIC = 0;
	public static final int CELL_TYPE_STRING = 1;
	public static final int CELL_TYPE_FORMULA = 2;
	public static final int CELL_TYPE_BLANK = 3;
	public static final int CELL_TYPE_BOOLEAN = 4;
	public static final int CELL_TYPE_ERROR = 5;

	public Row getRow();
	
	public int getRowIndex();
	
	public int getColumnIndex();
	
	public void setCellValue(String value);
	
	public double getNumericCellValue();
	
	public boolean getBooleanCellValue();
	
	public String getStringCellValue();
	
	public Date getDateCellValue();
	
	public int getCellType();
	
	public void setCellValue(boolean value);
	
	public void setCellValue(double value);
	
	public void setCellValue(long value);
	
	public void setCellValue(int value);
	
	public void setCellValue(Calendar value);
	
	public void setCellStyle(String stype);
	
}
