/**
 * 
 */
package com.zot.wechat.handle;

import com.zot.wechat.msg.WechatMsg;

/**
 * @author jack
 *
 */
public interface WechatHandle {
	
	/**
	 * 微信消息处理接口
	 */
	
	public String handle(WechatMsg msg);
}
