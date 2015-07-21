/**
 * 
 */
package com.zot.wechat.handle;

import java.util.ArrayList;
import java.util.List;

import com.zot.wechat.msg.ArticlesWechatMsg;
import com.zot.wechat.msg.Constant;
import com.zot.wechat.msg.EventWechatMsg;
import com.zot.wechat.msg.WechatMsg;
import com.zot.wechat.util.ResMsgGen;

/**
 * @author jack
 *
 */
public class EventHandleAS implements WechatHandle {
	
	/* (non-Javadoc)
	 * @see com.zot.wechat.handle.WechatHandle#handle(com.jxing.wechat.msg.WechatMsg)
	 */
	@Override
	public String handle(WechatMsg msg) {
		
		String rStr = null;
		ResMsgGen gen = new ResMsgGen();
		EventWechatMsg eMsg = (EventWechatMsg)msg;
		String event = eMsg.getEvent(); 
		
		switch (event) {
		case "click":
			String key = eMsg.getEventKey();
			List<ArticlesWechatMsg> content = getYueBa(key);
			rStr = gen.genNews(eMsg, content);
			break;
		case "subscribe":
			
			break;
		case "unsubscribe":
			
			break;

		default:
			break;
		}
		
		return rStr;
	}

	/**
	 * 约吧的处理逻辑,1、返回当前正在服务的个数，2、提供最近最快的服务时间、地点、时长，3、可以选择其他服务时间的链接
	 * 
	 * 洗车：10001
	 * 做漆：10002
	 * 钣金：10003
	 * 装潢：10004
	 * 其他：10005
	 * @param key
	 * @return
	 */
	public List<ArticlesWechatMsg> getYueBa(String key)
	{
		List<ArticlesWechatMsg> lr = new ArrayList<ArticlesWechatMsg>();
		
		//当前正在服务的数量
		ArticlesWechatMsg msg = new ArticlesWechatMsg();
		msg.setTitle("预约结果：");
		msg.setDescription("点击推荐时间段自动确认该时间段，或者通过自助链接选择适合自己的时间。");
		msg.setUrl(Constant.SERVER_URL+"yueba/yuemakesure.jsp");
		lr.add(msg);
		
		ArticlesWechatMsg selfURL = new ArticlesWechatMsg();
		selfURL.setTitle("自助选择服务时间：");
		selfURL.setDescription("通过自助链接选择适合自己的时间。");
		selfURL.setUrl(Constant.SERVER_URL+"yueba/selfchoose.jsp");
		lr.add(selfURL);
		
		return lr;
	}
}
