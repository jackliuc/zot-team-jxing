package com.zot.xing.dao.subscribe;

import java.util.Date;

public class CustomerBO {
	private String custId;
	private String custName;
	private String wechatno;
	private String wechatName;
	private String phoneno;
	
	private int age;
	private String sex;
	private String email;
	private String address;
	private Date subTime;
	private Date calSubTime;
	private String remark;
	
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getWechatno() {
		return wechatno;
	}
	public void setWechatno(String wechatno) {
		this.wechatno = wechatno;
	}
	public String getWechatName() {
		return wechatName;
	}
	public void setWechatName(String wechatName) {
		this.wechatName = wechatName;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getSubTime() {
		return subTime;
	}
	public void setSubTime(Date subTime) {
		this.subTime = subTime;
	}
	public Date getCalSubTime() {
		return calSubTime;
	}
	public void setCalSubTime(Date calSubTime) {
		this.calSubTime = calSubTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
}
