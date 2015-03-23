package com.st.framework.utils.excel.impl.ole2;



import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;

import com.st.framework.utils.excel.impl.Cell;
import com.st.framework.utils.excel.impl.Row;
import com.st.framework.utils.excel.impl.Sheet;



public class Ole2Row implements Row{
	
	private final Sheet sheet;
	
	private final HSSFRow row;
	
//	private final Map<Integer, Cell> cells = new HashMap<Integer, Cell>();
	
	public Ole2Row(Sheet sheet, HSSFRow row){
		this.sheet = sheet;
		this.row = row;
	}

	@Override
	public Cell getCell(int i) {
//		Integer key = new Integer(i);
//		Cell cell = cells.get(key);
//		if(cell == null){
//			cell = new Ole2Cell(this, row.getCell(i));
//			cells.put(key, cell);
//		}
		HSSFCell cell = row.getCell(i);
		if(cell == null){
			return null;
		}
		return new Ole2Cell(this, cell);
	}

	@Override
	public Cell createCell(int i) {
		HSSFCell cell = row.createCell(i);
		if(cell == null){
			return null;
		}
		return new Ole2Cell(this, cell);
	}

	@Override
	public void removeCell(Cell cell) {
		row.removeCell(((Ole2Cell)cell).getCell());
	}

	public int getRowNum(){
		return row.getRowNum();
	}

	public final Sheet getSheet() {
		return sheet;
	}
	
}
