package com.st.framework.utils.excel.impl.ooxml;



import java.util.Calendar;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.st.framework.utils.excel.impl.Cell;
import com.st.framework.utils.excel.impl.Row;


public class OoxmlCell implements Cell{
	
	private final Row row;
	
	private final XSSFCell cell;
	
	public OoxmlCell(Row row, XSSFCell cell){
		this.row = row;
		this.cell = cell;
	}

	@Override
	public Row getRow() {
		return row;
	}
	
	public int getRowIndex(){
		return cell.getRowIndex();
	}
	
	public int getColumnIndex(){
		return cell.getColumnIndex();
	}

	@Override
	public void setCellValue(String value) {
		cell.setCellValue(value);
	}

	@Override
	public double getNumericCellValue() {
		return cell.getNumericCellValue();
	}

	@Override
	public boolean getBooleanCellValue() {
		return cell.getBooleanCellValue();
	}

	@Override
	public String getStringCellValue() {
		return cell.getStringCellValue();
	}

	@Override
	public Date getDateCellValue() {
		return cell.getDateCellValue();
	}

	@Override
	public int getCellType() {
		return cell.getCellType();
	}

	@Override
	public void setCellValue(boolean value) {
		cell.setCellValue(value);
	}

	@Override
	public void setCellValue(double value) {
		cell.setCellValue(value);
	}

	@Override
	public void setCellValue(long value) {
		cell.setCellValue(value);
	}

	@Override
	public void setCellValue(int value) {
		cell.setCellValue(value);
	}

	@Override
	public void setCellValue(Calendar value) {
		cell.setCellValue(value);
	}

	@Override
	public void setCellStyle(String stype) {
		XSSFWorkbook wb = cell.getRow().getSheet().getWorkbook();
		DataFormat dataFormat = wb.getCreationHelper().createDataFormat();
		CellStyle style = wb.createCellStyle();
	    style.setDataFormat(dataFormat.getFormat("yyyy\"年\"m\"月\"d\"日\""));
		cell.setCellStyle(style);
		
	}

	public final XSSFCell getCell() {
		return cell;
	}

}
