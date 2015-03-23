package com.st.framework.utils.excel;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;


public interface ExcelObject {
	
	public static final int TYPE_OFFICE_2007 = 1;
	public static final int TYPE_OFFICE_2003 = 2;

	/**
	 * 从一个excel文件的输入流中读取数据到内存中。
	 * 
	 * @param in
	 * @throws IOException
	 */
	public void load(InputStream in) throws IOException;

	/**
	 * 将本对象中的excel数据写入到excel输出流中。
	 * 
	 * @param out
	 * @throws IOException
	 */
	public void save(OutputStream out) throws IOException;

	/**
	 * 获取所有的数据。
	 * 
	 * @return
	 * @throws IOException
	 */
	public List<Map<String, Object>> getAll() throws ExcelCellException;

	/**
	 * 获取一行的数据。
	 * 
	 * @param row
	 * @return
	 * @throws IOException
	 */
	public Map<String, Object> readRow(int row) throws ExcelCellException;

	/**
	 * 获取一个单元格中的数据。
	 * 
	 * @param row
	 * @param column
	 * @return
	 */
	public Object readCell(int row, int column) throws ExcelCellException;

	/**
	 * 写入所有的数据。
	 * 
	 * @param list
	 * @throws IOException
	 */
	public void saveAll(List<Map<String, Object>> list)
			throws ExcelCellException;

	/**
	 * 写入一行数据。
	 * 
	 * @param row
	 * @param data
	 * @throws IOException
	 */
	public void writeRow(int row, Map<String, Object> data)
			throws ExcelCellException;

	/**
	 * 向单元格中写入一个数据值。
	 * 
	 * @param row
	 * @param column
	 * @param value
	 */
	public void writeCell(int row, int column, Object value)
			throws ExcelCellException;
	
	/**
	 * 
	 * @return
	 */
	public CellLocation getLocation();

	/**
	 * 
	 * @return
	 */
	public FieldInfo[] getFields();

}