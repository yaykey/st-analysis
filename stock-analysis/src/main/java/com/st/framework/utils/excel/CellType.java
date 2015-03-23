package com.st.framework.utils.excel;



import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.st.framework.utils.excel.impl.Cell;



/**
 * excel中单元格中数据的类型。
 */
public enum CellType {

	BOOLEAN(Boolean.class, "boolean") {
		@Override
		public Object _getValue(Cell cell) {
			return new Boolean(cell.getBooleanCellValue());
		}

		@Override
		protected void _setValue(Cell cell, Object value) {
			if (value instanceof Boolean) {
				Boolean booleanValue = (Boolean) value;
				cell.setCellValue(booleanValue);
				return;
			}
			try{
				boolean booleanValue = Boolean.parseBoolean(value.toString());
				cell.setCellValue(booleanValue);
				return;
			}catch (Exception e) {
			}
			throw new RuntimeException("给Cell设值出错：值{" + value + "}不是" + this.clazz.getName() + "类型的。");
		}
	},
	
	DATE(Date.class) {
		@Override
		public Object _getValue(Cell cell) {
			return cell.getDateCellValue();
		}
		
		@Override
		protected void _setValue(Cell cell, Object value) {
			if (value instanceof Date) {
				Date date = (Date)value;
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				cell.setCellValue(calendar);
			    cell.setCellStyle("yyyy\"年\"m\"月\"d\"日\"");
				return;
			}
			throw new RuntimeException("给Cell设值出错：值{" + value + "}不是" + this.clazz.getName() + "类型的。");
		}
	},
	
	INT(Integer.class, "int") {
		@Override
		public Object _getValue(Cell cell) {
			return new Integer((int) cell.getNumericCellValue());
		}
		
		@Override
		protected void _setValue(Cell cell, Object value) {
			if (value instanceof Integer) {
				Integer booleanValue = (Integer) value;
				cell.setCellValue(booleanValue);
				return;
			}
			try{
				int booleanValue = Integer.parseInt(value.toString());
				cell.setCellValue(booleanValue);
				return;
			}catch (Exception e) {
			}
			throw new RuntimeException("给Cell设值出错：值{" + value + "}不是" + this.clazz.getName() + "类型的。");
		}
	},
	
	LONG(Long.class, "long") {
		@Override
		public Object _getValue(Cell cell) {
			return new Long((long) cell.getNumericCellValue());
		}
		
		@Override
		protected void _setValue(Cell cell, Object value) {
			if (value instanceof Long) {
				Long booleanValue = (Long) value;
				cell.setCellValue(booleanValue);
				return;
			}
			try{
				long booleanValue = Long.parseLong(value.toString());
				cell.setCellValue(booleanValue);
				return;
			}catch (Exception e) {
			}
			throw new RuntimeException("给Cell设值出错：值{" + value + "}不是" + this.clazz.getName() + "类型的。");
		}
	},
	
	DOUBLE(Double.class, "double") {
		@Override
		public Object _getValue(Cell cell) {
			return new Double(cell.getNumericCellValue());
		}
		
		@Override
		protected void _setValue(Cell cell, Object value) {
			if (value instanceof Double) {
				Double booleanValue = (Double) value;
				cell.setCellValue(booleanValue);
				return;
			}
			try{
				double booleanValue = Double.parseDouble(value.toString());
				cell.setCellValue(booleanValue);
				return;
			}catch (Exception e) {
			}
			throw new RuntimeException("给Cell设值出错：值{" + value + "}不是" + this.clazz.getName() + "类型的。");
		}
	},
	
	STRING(String.class) {
		@Override
		public Object _getValue(Cell cell) {
			int type = cell.getCellType();
			if(Cell.CELL_TYPE_NUMERIC == type){
				double value = 0;
				try{
					value = cell.getNumericCellValue();
				}catch (Exception e) {
				}
				long num = (long)value;
				if((value - num) == 0){
					return num + "";
				}else{
					return value + "";
				}
			}
			if(Cell.CELL_TYPE_BOOLEAN == type){
				return cell.getBooleanCellValue() + "";
			}
			return cell.getStringCellValue();
		}
		
		@Override
		protected void _setValue(Cell cell, Object value) {
			if (value instanceof String) {
				String stringValue = (String) value;
				cell.setCellValue(stringValue);
				return;
			}
			throw new RuntimeException("给Cell设值出错：值{" + value + "}不是" + this.clazz.getName() + "类型的。");
		}
	};

	private static final Map<String, CellType> map = new HashMap<String, CellType>();
	static{
		CellType[] types = CellType.values();
		for(CellType type : types){
			map.put(type.clazz.getName(), type);
			if(type.baseClassName != null){
				map.put(type.baseClassName, type);
			}
		}
	}
	public static CellType getExcelCellType(Class<?> clazz){
		if(clazz == null){
			return null;
		}
		return map.get(clazz.getName());
	}

	public final Object getValue(Cell cell) {
		if (cell == null) {
			return null;
		}
		return _getValue(cell);
	}
	
	public final void setValue(Cell cell, Object value) {
		if(cell == null){
			return;
		}
		
		if (value == null) {
			setNullValue(cell);
			return;
		}
		_setValue(cell, value);
	}
	
	public final void setNullValue(Cell cell) {
		cell.getRow().removeCell(cell);
	}

	protected Class<?> clazz = null;
	protected String baseClassName = null;

	private CellType(Class<?> clazz) {
		this.clazz = clazz;
	}
	
	private CellType(Class<?> clazz, String baseClassName) {
		this.clazz = clazz;
		this.baseClassName = baseClassName;
	}

	protected abstract Object _getValue(Cell cell);
	protected abstract void _setValue(Cell cell, Object value);

}
