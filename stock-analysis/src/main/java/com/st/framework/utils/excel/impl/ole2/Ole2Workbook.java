package com.st.framework.utils.excel.impl.ole2;



import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.st.framework.utils.excel.impl.Sheet;
import com.st.framework.utils.excel.impl.Workbook;



public class Ole2Workbook implements Workbook{
	
	private final HSSFWorkbook workbook;
	
//	private final Map<Integer, Sheet> sheets = new HashMap<Integer, Sheet>();
	
	public Ole2Workbook(HSSFWorkbook workbook){
		this.workbook = workbook;
	}

	@Override
	public Sheet getSheetAt(int i) {
//		Integer key = new Integer(i);
//		Sheet sheet = sheets.get(key);
//		if(sheet == null){
//			sheet = new Ole2Sheet(workbook.getSheetAt(i));
//			sheets.put(key, sheet);
//		}
		return new Ole2Sheet(this, workbook.getSheetAt(i));
	}

	@Override
	public Sheet createSheet(String name) {
		return new Ole2Sheet(this, workbook.createSheet(name));
	}

	@Override
	public void write(OutputStream out) throws IOException {
		this.workbook.write(out);
	}

	public int getSheetSize(){
		return workbook.getNumberOfSheets();
	}
}
