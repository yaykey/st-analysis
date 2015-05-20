package com.st.analysis.utils.stock.bean.netease;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.st.framework.module.PersistentObject;

public class StockIndexBean extends PersistentObject{

	//{"CODE":"1399989","NAME":"\u4e2d\u8bc1\u533b\u7597","SYMBOL":"399989","no":1,"zhenfu":false,"VOLUME":null,"TURNOVER":null}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2076327838594629758L;

	@JsonProperty("CODE")
	private String CODE;
	
	@JsonProperty("NAME")
	private String NAME;
	
	@JsonProperty("SYMBOL")
	private String SYMBOL;
	
	private Integer no;
	
	private String zhenfu;
	
	@JsonProperty("VOLUME")
	private String VOLUME;
	
	@JsonProperty("TURNOVER")
	private String TURNOVER;
	
	public String getVOLUME() {
		return VOLUME;
	}

	public void setVOLUME(String vOLUME) {
		VOLUME = vOLUME;
	}

	public String getTURNOVER() {
		return TURNOVER;
	}

	public void setTURNOVER(String tURNOVER) {
		TURNOVER = tURNOVER;
	}

	

	public String getZhenfu() {
		return zhenfu;
	}

	public void setZhenfu(String zhenfu) {
		this.zhenfu = zhenfu;
	}

	public String getCODE() {
		return CODE;
	}

	public void setCODE(String cODE) {
		CODE = cODE;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public String getSYMBOL() {
		return SYMBOL;
	}

	public void setSYMBOL(String sYMBOL) {
		SYMBOL = sYMBOL;
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}
}
