/**
 * 
 */
package com.zot.wechat.handle;

import com.zot.wechat.msg.TextWechatMsg;
import com.zot.wechat.msg.WechatMsg;
import com.zot.wechat.util.ResMsgGen;

/**
 * @author jack
 *
 */
public class TextMsgHandleAS implements WechatHandle {

	/* (non-Javadoc)
	 * @see com.jxing.wechat.handle.WechatHandle#handle(com.jxing.wechat.msg.WechatMsg)
	 */
	@Override
	public String handle(WechatMsg msg) {
		
		String content = ((TextWechatMsg)msg).getContent(); 
		ResMsgGen gen = new ResMsgGen();
		return gen.genText(msg, content);
	}

}
