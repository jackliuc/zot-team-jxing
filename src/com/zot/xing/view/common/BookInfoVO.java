package com.zot.xing.view.common;

import java.util.Date;

/**
 * 预约信息
 * @author Administrator
 *
 */
public class BookInfoVO 
{
	/**
	 * 服务预约类型
	 */
	private int orderType;
	
	/**
	 * 预约名
	 */
	private String bookName;
	/**
	 * 当前总预约数
	 */
	private int bookingNum;
	/**
	 * 当前空闲工位数
	 */
	private int idleNum;
	/**
	 * 当前店内排队数
	 */
	private int arrivedNum;
	
	/**
	 * 出车速度，处理效率
	 */
	private long costTime;
	/**
	 * 建议最佳预约时间
	 */
	private Date suggestTime;
	
	/**
	 * 界面显示的建议预约时间
	 */
	private String disSuggestTime;
	
	public int getOrderType() {
		return orderType;
	}
	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	public int getIdleNum() {
		return idleNum;
	}
	public void setIdleNum(int idleNum) {
		this.idleNum = idleNum;
	}

	public Date getSuggestTime() {
		return suggestTime;
	}
	public void setSuggestTime(Date suggestTime) {
		this.suggestTime = suggestTime;
	}
	public long getCostTime() {
		return costTime;
	}
	public void setCostTime(long costTime) {
		this.costTime = costTime;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getDisSuggestTime() {
		return disSuggestTime;
	}
	public void setDisSuggestTime(String disSuggestTime) {
		this.disSuggestTime = disSuggestTime;
	}
	public int getBookingNum() {
		return bookingNum;
	}
	public void setBookingNum(int bookingNum) {
		this.bookingNum = bookingNum;
	}
	public int getArrivedNum() {
		return arrivedNum;
	}
	public void setArrivedNum(int arrivedNum) {
		this.arrivedNum = arrivedNum;
	}
	
}
