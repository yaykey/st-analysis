package com.st.framework.utils.excel.impl;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.st.framework.utils.excel.CellLocation;
import com.st.framework.utils.excel.CellType;
import com.st.framework.utils.excel.ExcelCellException;
import com.st.framework.utils.excel.ExcelObject;
import com.st.framework.utils.excel.FieldInfo;
import com.st.framework.utils.excel.impl.ole2.Ole2Workbook;
import com.st.framework.utils.excel.impl.ooxml.OoxmlWorkbook;



public class ExcelObjectImpl implements ExcelObject {
	
	private static final Logger log = LoggerFactory.getLogger(ExcelObjectImpl.class);

	protected final CellLocation location;
	protected final List<FieldInfo> fields;
	protected final int top, left, type;
	
	protected Workbook workbook = null;
	private Workbook getWorkbook(){
		if(workbook == null){
			switch (type) {
			case ExcelObject.TYPE_OFFICE_2003:
				workbook = new Ole2Workbook(new HSSFWorkbook());
				break;
			case ExcelObject.TYPE_OFFICE_2007:
				workbook = new OoxmlWorkbook(new XSSFWorkbook());
				break;
			default:
				throw new RuntimeException("unknow excel type: " + type);
			}
		}
		return workbook;
	}
	
	protected Sheet sheet = null;
	private Sheet getSheet(){
		if(sheet == null){
			int l = location.getSheet();
			int size = getWorkbook().getSheetSize();
			if(l < size){
				sheet = getWorkbook().getSheetAt(l);
			}else{
				for (int i = size; i <= l; i++) {
					getWorkbook().createSheet("sheet" + (i + 1));
				}
				sheet = workbook.getSheetAt(l);
			}
		}
		return sheet;
	}
	
	public ExcelObjectImpl(CellLocation location, List<FieldInfo> fields, int type){
		super();
		this.location = location;
		this.fields = fields;
		
		this.top = location.getRow();
		this.left = location.getColumn();
		this.type = type;
	}
	
	@Override
	public void load(InputStream in) throws IOException {
		if(in == null){
			throw new NullPointerException();
		}
		if(log.isDebugEnabled()){
			log.debug("开始解析excel");
		}
		
		switch (type) {
		case ExcelObject.TYPE_OFFICE_2003:
			workbook = new Ole2Workbook(new HSSFWorkbook(new POIFSFileSystem(in)));
			break;
		case ExcelObject.TYPE_OFFICE_2007:
			workbook = new OoxmlWorkbook(new XSSFWorkbook(in));
			break;
		default:
			throw new RuntimeException("unknow excel type: " + type);
		}
		if (workbook == null) {
			throw new IOException("加载excel失败！");
		}
		if(log.isDebugEnabled()){
			log.debug("解析excel成功");
		}
	}
	
	@Override
	public void save(OutputStream out) throws IOException{
		if(out != null && getWorkbook() != null && getSheet() != null){
			getWorkbook().write(out);
		}
	}
	
	@Override
	public List<Map<String, Object>> getAll() throws ExcelCellException {
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		int i = 1; // 从第1行开始读，第0行用于存放标题。
		Map<String, Object> data = null;
		do {
			data = readRow(i);
			if (data != null) {
				datas.add(data);
			}
			i++;
		} while (data != null);
		return datas;
	}
	
	@Override
	public Map<String, Object> readRow(int row) throws ExcelCellException {
		Map<String, Object> line = new HashMap<String, Object>();
		for (int i = 0; i < fields.size(); i++) {
			try {
				FieldInfo field = fields.get(i);
				String fieldName = field.getId();
				Object value = readCell(row, i);
				if (value != null) {
					line.put(fieldName, value);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new ExcelCellException(top + row, left + i, e);
			}
		}
		if(line.size() == 0){
			return null;
		}
		return line;
	}
	
	@Override
	public Object readCell(int row, int column){
		CellType type = fields.get(column).getType();
		Cell cell = getCell(row, column);
		if (cell == null) {
			return null;
		}
		Object value = type.getValue(cell);
		return value;
	}
	
	@Override
	public void saveAll(List<Map<String, Object>> list) throws ExcelCellException {
		if (list != null && list.size() > 0) {
			writeTitle();
			// 从第一行开始写，第0行用于存放标题。
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> bean = list.get(i);
				writeRow(i + 1, bean);
			}
		}
	}
	
	public void writeTitle() throws ExcelCellException{
		int row = 0;
		for (int column = 0; column < fields.size(); column++) {
			try {
				FieldInfo field = fields.get(column);
				String name = field.getName();
				Cell cell = getCell(row, column);
				if(cell == null){
					cell = createCell(row, column);
				}
				CellType.STRING.setValue(cell, name);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ExcelCellException(top + row, left + column, e);
			}
		}
	}
	
	@Override
	public void writeRow(int row, Map<String, Object> data) throws ExcelCellException{
		for (int i = 0; i < fields.size(); i++) {
			try {
				FieldInfo field = fields.get(i);
				String key = field.getId();
				Object value = data.get(key);
				writeCell(row, i, value);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ExcelCellException(top + row, left + i, e);
			}
		}
	}
	
	@Override
	public void writeCell(int row, int column, Object value) {
		CellType type = fields.get(column).getType();
		Cell cell = getCell(row, column);
		if(cell == null){
			cell = createCell(row, column);
		}
		type.setValue(cell, value);
	}
	
	private Cell getCell(int row, int column) {
		int r = top + row;
		int c = left + column;
		if(log.isDebugEnabled()){
			log.debug("will get cell: (" + r + ", " + c + ")");
		}
		Row hRow = getSheet().getRow(r);
		if (hRow == null) {
			if(log.isDebugEnabled()){
				log.debug("not found row: " + r);
			}
			return null;
		}
		
		Cell cell = hRow.getCell(c);
		if (cell == null) {
			if(log.isDebugEnabled()){
				log.debug("not found cell: " + r + ", " + c + ")");
			}
			return null;
		}
		return cell;
	}
	
	private Cell createCell(int row, int column) {
		int r = top + row;
		int c = left + column;
		if(log.isDebugEnabled()){
			log.debug("will get cell: (" + r + ", " + c + ")");
		}
		
		Row hRow = getSheet().getRow(r);
		if (hRow == null) {
			hRow = getSheet().createRow(r);
			if(log.isDebugEnabled()){
				log.debug("create row: " + r);
			}
		}
		
		Cell cell = hRow.getCell(c);
		if (cell == null) {
			cell = hRow.createCell(c);
			if(log.isDebugEnabled()){
				log.debug("create cell: " + r + ", " + c + ")");
			}
		}
		return cell;
	}

	public final CellLocation getLocation() {
		return location;
	}

	public final FieldInfo[] getFields() {
		return fields.toArray(new FieldInfo[fields.size()]);
	}

}