package com.st.framework.utils.excel;



public class ExcelCellException extends Exception {

	private static final long serialVersionUID = 1L;

	private int row;
	private int column;
	private Exception e;

	public ExcelCellException(int row, int column, Exception e) {
		super(e);
		this.row = row;
		this.column = column;
		this.e = e;
	}

	public final int getRow() {
		return row;
	}

	public final int getColumn() {
		return column;
	}

	public final Exception getE() {
		return e;
	}
	
	public String toString(){
		return "读取或写入单元(" + row + ", " + column + ")出错：" + e.getMessage();
	}

}
