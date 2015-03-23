package com.st.framework.utils.excel.impl.ooxml;


import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.st.framework.utils.excel.impl.Row;
import com.st.framework.utils.excel.impl.Sheet;



public class OoxmlSheet implements Sheet{
	
	private final XSSFSheet sheet;
	
	public OoxmlSheet(XSSFSheet sheet){
		super();
		this.sheet = sheet;
	}

	@Override
	public Row getRow(int i) {
		XSSFRow row = sheet.getRow(i);
		if(row == null){
			return null;
		}
		return new OoxmlRow(row);
	}

	@Override
	public Row createRow(int i) {
		XSSFRow row = sheet.createRow(i);
		if(row == null){
			return null;
		}
		return new OoxmlRow(row);
	}

}
