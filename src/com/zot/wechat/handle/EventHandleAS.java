/**
 * 
 */
package com.zot.wechat.handle;

import java.util.ArrayList;
import java.util.List;

import com.zot.util.view.engine.yb.SubscribeService;
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
		String desc = "服务类型：";
		switch (key) {
		case "10001":
			desc += "洗车";
			break;
		case "10002":
			desc += "做漆";		
			break;
		case "10003":
			desc += "钣金";
			break;
		case "10004":
			desc += "装潢";
			break;

		default:
			break;
		}
		desc+="  ";
		desc+="最快服务时间：";
		String time = SubscribeService.computeServiceTime(key);
		desc+=time;
		//当前正在服务的数量
		ArticlesWechatMsg msg = new ArticlesWechatMsg();
		msg.setTitle("");
		msg.setDescription("简行快修，让汽车出行简单体面！");
		msg.setPicUrl(Constant.SERVER_URL+"assets/image/webcome3-s.png");
		msg.setUrl(Constant.SERVER_URL+"index.jsp");
		lr.add(msg);
		
		ArticlesWechatMsg subscribeURL = new ArticlesWechatMsg();
		subscribeURL.setTitle("请点击系统推荐结果");
		subscribeURL.setDescription(desc);
		subscribeURL.setUrl(Constant.SERVER_URL+"subscribe/subscribesure.jsp?subtype="+key);
		lr.add(subscribeURL);
		
		ArticlesWechatMsg selfURL = new ArticlesWechatMsg();
		selfURL.setTitle("自助选择");
		selfURL.setDescription("可以通过简行快修自助选择页面选择心仪时间");
		selfURL.setUrl(Constant.SERVER_URL+"subscribe/self-subscribe.jsp?subtype="+key);
		lr.add(selfURL);
		
		return lr;
	}
	
	
}
