/**
 * 微信用户信息
 */
package com.zot.xing.dao.wechat;

import java.util.Date;

/**
 * @author jack
 *
 */
public class WechatCustomer {
	private String wechatNO = null;
	private String wechatName = null;
	private String phoneNo = null;
	private Date subTime = null;
	private Date calSubtime = null;

	public String getWechatNO() {
		return wechatNO;
	}

	public void setWechatNO(String wechatNO) {
		this.wechatNO = wechatNO;
	}

	public String getWechatName() {
		return wechatName;
	}

	public void setWechatName(String wechatName) {
		this.wechatName = wechatName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Date getSubTime() {
		return subTime;
	}

	public void setSubTime(Date subTime) {
		this.subTime = subTime;
	}

	public Date getCalSubtime() {
		return calSubtime;
	}

	public void setCalSubtime(Date calSubtime) {
		this.calSubtime = calSubtime;
	}



}
