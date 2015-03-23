package com.st.framework.utils.excel.impl.ooxml;


import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.st.framework.utils.excel.impl.Sheet;
import com.st.framework.utils.excel.impl.Workbook;

public class OoxmlWorkbook implements Workbook {
	
	private final XSSFWorkbook book;
	
	public OoxmlWorkbook(XSSFWorkbook book){
		super();
		this.book = book;
	}

	@Override
	public Sheet getSheetAt(int i) {
		return new OoxmlSheet(book.getSheetAt(i));
	}

	@Override
	public Sheet createSheet(String name) {
		return new OoxmlSheet(book.createSheet(name));
	}

	@Override
	public void write(OutputStream out) throws IOException {
		book.write(out);
	}

	public int getSheetSize(){
		return book.getNumberOfSheets();
	}
}
