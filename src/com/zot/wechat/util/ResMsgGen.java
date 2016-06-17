/**
 * 生成微信返回消息
 */
package com.zot.wechat.util;

import java.util.Date;
import java.util.List;

import com.zot.wechat.msg.ArticlesWechatMsg;
import com.zot.wechat.msg.Constant;
import com.zot.wechat.msg.WechatMsg;

/**
 * @author jack
 *
 */
public class ResMsgGen {
	
	/**
	 * 生成文本消息 
	 * @param msg 
	 * @param content
	 * @return
	 */
	public String genText(WechatMsg msg,String content)
	{
		String str = "<Content><![CDATA["+content+"]]></Content>";
		return genMsgStr(msg,Constant.WECHAT_MSG_TEXT_TYPE,str);
	}
	
	/**
	 * 生成新闻类信息
	 * @param msg
	 * @param content
	 * @return
	 */
	public String genNews(WechatMsg msg,List<ArticlesWechatMsg> content){
		
		String str ="<ArticleCount>"+content.size()+"</ArticleCount><Articles>";
		
		for (ArticlesWechatMsg articlesWechatMsg : content) {
			String tmp = "<item>"+
           "<Title><![CDATA["+articlesWechatMsg.getTitle()+"]]></Title> "+
           "<Description><![CDATA["+articlesWechatMsg.getDescription()+"]]></Description>"+
           "<PicUrl><![CDATA["+articlesWechatMsg.getPicUrl()+"]]></PicUrl>"+
           "<Url><![CDATA["+articlesWechatMsg.getUrl()+"]]></Url>"+
           "</item>";
			
			str += tmp;
		}
		
		str+="</Articles>";
		
		return genMsgStr(msg,Constant.WECHAT_MSG_NEW_TYPE,str);
	}
	
	/**
	 * 生成根节点
	 * @return
	 */
	private String genMsgStr(WechatMsg msg,String msgType,String content)
	{
		String res = "<xml>"+
				"<ToUserName><![CDATA["+msg.getFromUserName()+"]]></ToUserName>"+
				"<FromUserName><![CDATA["+msg.getToUserName()+"]]></FromUserName> "+
				" <CreateTime>"+(new Date()).getTime()+"</CreateTime>"+
				"<MsgType><![CDATA["+msgType+"]]></MsgType>"+
				content+
				"</xml>";
		
		return res;
	}
	
	
	 
}
