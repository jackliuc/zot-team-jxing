package com.zot.xing.view.common;

public class IdVO {
	private int idType; //0:微信号；1：手机号；2：车牌号
	private String id;
	
	public IdVO()
	{
	}
	
	public IdVO(int idType, String id)
	{
		this.idType = idType;
		this.id = id;
	}
	
	public int getIdType() {
		return idType;
	}
	public void setIdType(int idType) {
		this.idType = idType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
