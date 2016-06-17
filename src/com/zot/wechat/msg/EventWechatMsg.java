/**
 * 
 */
package com.zot.wechat.msg;

/**
 * @author jack
 *
 */
public class EventWechatMsg extends WechatMsg {
	private String event;
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getEventKey() {
		return eventKey;
	}
	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}
	private String eventKey;
	
	
}
