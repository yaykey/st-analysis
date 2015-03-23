package com.st.framework.utils.excel;


import java.util.List;

import com.st.framework.utils.excel.impl.ExcelObjectImpl;




public class ExcelPlugin {
	
	/**
	 * 
	 * @param location
	 * @param fields
	 * @return
	 */
	public static final ExcelObject getExcelObject(CellLocation location,
			List<FieldInfo> fields, int type) {
//		switch (excelType) {
//		case EXCEL_TYPE_OFFICE_2003:
//			return new OLE2ExcelObject(location, fields);
//		case EXCEL_TYPE_OFFICE_2007:
//			return new Ooxml
//		default:
//			break;
//		}
		return new ExcelObjectImpl(location, fields, type);
	}

}
