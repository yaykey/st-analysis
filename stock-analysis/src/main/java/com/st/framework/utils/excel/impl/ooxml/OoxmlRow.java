package com.st.framework.utils.excel.impl.ooxml;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import com.st.framework.utils.excel.impl.Cell;
import com.st.framework.utils.excel.impl.Row;



public class OoxmlRow implements Row{
	
	private final XSSFRow row;
	
	public OoxmlRow(XSSFRow row){
		this.row = row;
	}
	
	@Override
	public Cell getCell(int i) {
		XSSFCell cell = row.getCell(i);
		if(cell == null){
			return null;
		}
		return new OoxmlCell(this, cell);
	}

	@Override
	public Cell createCell(int i) {
		XSSFCell cell = row.createCell(i);
		if(cell == null){
			return null;
		}
		return new OoxmlCell(this, cell);
	}

	@Override
	public void removeCell(Cell cell) {
		row.removeCell(((OoxmlCell)cell).getCell());
	}
	
	public int getRowNum(){
		return row.getRowNum();
	}

}
