package com.zot.xing.view.common;

public enum OrderStatus {
	WAITING("等待"), DOING("服务中"), FINISHED("已完成"), EVALUATED("已评价");
	
	private String name;
	
	private OrderStatus(String name)
	{
		this.name = name;
	}
	
	public String toString()
	{
		return this.name;
	}
}
