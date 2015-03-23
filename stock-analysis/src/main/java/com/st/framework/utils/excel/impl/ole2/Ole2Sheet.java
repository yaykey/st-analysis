package com.st.framework.utils.excel.impl.ole2;



import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.st.framework.utils.excel.impl.Row;
import com.st.framework.utils.excel.impl.Sheet;



public class Ole2Sheet implements Sheet{
	
	private final Ole2Workbook owner;
	
	private final HSSFSheet sheet;
	
//	private final Map<Integer, Row> rows = new HashMap<Integer, Row>();

	public Ole2Sheet(Ole2Workbook owner, HSSFSheet sheet){
		this.owner = owner;
		this.sheet = sheet;
	}
	
	@Override
	public Row getRow(int i) {
//		Row row = rows.get(new Integer(i));
//		if(row == null){
//			row = new Ole2Row();
//		}
		HSSFRow row = sheet.getRow(i);
		if(row == null){
			return null;
		}
		return new Ole2Row(this, row);
	}

	@Override
	public Row createRow(int i) {
		HSSFRow row = sheet.createRow(i);
		if(row == null){
			return null;
		}
		return new Ole2Row(this, row);
	}

	public final Ole2Workbook getOwner() {
		return owner;
	}

}