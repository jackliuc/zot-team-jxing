package com.zot.wechat.util;

public class AccessToken {
	//最大延迟时间，用于程序或网络引起的token延迟，默认为5分钟
	private static final long MAX_DELAY_TIME = 5 * 60 * 1000;
	
	private String token;
	private long createTime;
	private long maxExpTime;

	public AccessToken(String token, long maxExpTime)
	{
		this.token = token;		
		this.maxExpTime = maxExpTime;
		this.createTime = System.currentTimeMillis() - MAX_DELAY_TIME;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	
	public boolean isExpire()
	{
		return System.currentTimeMillis() - this.createTime >= this.maxExpTime * 1000L;
	}
	
	public String toString()
	{
		return "Token:" + this.token + "|IsExpire:" + this.isExpire();
	}
}
