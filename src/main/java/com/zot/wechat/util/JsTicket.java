package com.zot.wechat.util;

public class JsTicket {
		//最大延迟时间，用于程序或网络引起的token延迟，默认为5分钟
		private static final long MAX_DELAY_TIME = 5 * 60 * 1000;
		
		private String ticket;
		private long createTime;
		private long maxExpTime;

		public JsTicket(String ticket, long maxExpTime)
		{
			this.ticket = ticket;		
			this.maxExpTime = maxExpTime;
			this.createTime = System.currentTimeMillis() - MAX_DELAY_TIME;
		}
		
		public String getTicket() {
			return ticket;
		}
		public void setTicket(String ticket) {
			this.ticket = ticket;
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
			return "Ticket:" + this.ticket + "|IsExpire:" + this.isExpire();
		}
}
