package com.st.framework.utils.excel;



/**
 * 一个excel文件中一个单元格的位置。
 */
public class CellLocation {

	private int sheet = 0;
	
	private int row = 0;

	private int column = 0;

	public CellLocation(){
		super();
	}
	
	public CellLocation(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}
	
	public CellLocation(int sheet, int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}

	public final int getRow() {
		return row;
	}

	public final int getColumn() {
		return column;
	}

	/**
	 * 所操作的Sheet的位置。从 0 开始。
	 * @return
	 */
	public final int getSheet() {
		return sheet;
	}

}
