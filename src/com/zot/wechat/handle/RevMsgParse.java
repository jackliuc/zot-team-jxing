/**
 * 对公众平台发送给公众账号的消息加解密示例代码.
 * 
 * @copyright Copyright (c) 1998-2014 Tencent Inc.
 */

// ------------------------------------------------------------------------

package com.zot.wechat.handle;

import java.io.StringReader;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.zot.wechat.msg.Constant;
import com.zot.wechat.msg.EventWechatMsg;
import com.zot.wechat.msg.TextWechatMsg;
import com.zot.wechat.msg.WechatMsg;
import com.zot.wechat.util.AesException;
import com.zot.wechat.util.WXBizMsgCrypt;

/**
 * XMLParse class
 *
 * 提供提取消息格式中的密文及生成回复消息格式的接口.
 */
public class RevMsgParse {

	private static Logger log = Logger.getLogger(RevMsgParse.class);

	public static WechatMsg xml2Msg(String in) {
		WechatMsg msg = null;

		SAXReader reader = new SAXReader();
		try {

			Document doc = reader.read(new StringReader(in));
			Element root = doc.getRootElement();

			Element child = (Element) root.elements("MsgType").get(0);
			String type = child.getStringValue();
			switch (type) {
			case Constant.WECHAT_MSG_TEXT_TYPE:
				msg = initTextMsg(root);
				break;
			case Constant.WECHAT_MSG_EVENT_TYPE:
				msg = initEventMsg(root);
				break;
			default:
				break;
			}

		} catch (DocumentException e) {
			log.debug(e);
		}

		return msg;
	}

	/**
	 * 初始化text类型的对象
	 */
	private static WechatMsg initTextMsg(Element root) {
		TextWechatMsg msg = new TextWechatMsg();
		commonMSG(root, msg);
		msg.setContent(((Element) root.elements("Content").get(0)).getStringValue());
		return msg;
	}

	/**
	 * 初始化text类型的对象
	 */
	private static WechatMsg initEventMsg(Element root) {
		EventWechatMsg msg = new EventWechatMsg();
		commonMSG(root, msg);
		msg.setEvent(((Element) root.elements("Event").get(0)).getStringValue());
		msg.setEventKey(((Element) root.elements("EventKey").get(0)).getStringValue());
		return msg;
	}

	/**
	 * 初始化消息的公共部分，如msgtype，msgid等
	 * 
	 * @param ele
	 */
	private static void commonMSG(Element root, WechatMsg msg) {
		List t = root.elements("ToUserName");

		if (t != null && t.size() != 0) {
			msg.setToUserName(((Element) t.get(0)).getStringValue());
		}

		t = root.elements("FromUserName");

		if (t != null && t.size() != 0) {
			msg.setFromUserName(((Element) t.get(0)).getStringValue());
		}

		t = root.elements("CreateTime");

		if (t != null && t.size() != 0) {
			msg.setCreateTime(((Element) t.get(0)).getStringValue());
		}

		t = root.elements("MsgType");

		if (t != null && t.size() != 0) {
			msg.setMsgType(((Element) t.get(0)).getStringValue());
		}

		t = root.elements("MsgId");

		if (t != null && t.size() != 0) {
			msg.setMsgId(((Element) t.get(0)).getStringValue());
		}

		t = root.elements("AgentID");

		if (t != null && t.size() != 0) {
			msg.setAgentID(((Element) t.get(0)).getStringValue());
		}

	}

	/**
	 * 生成xml消息
	 * 
	 * @param encrypt
	 *            加密后的消息密文
	 * @param signature
	 *            安全签名
	 * @param timestamp
	 *            时间戳
	 * @param nonce
	 *            随机字符串
	 * @return 生成的xml字符串
	 */
	public static String generate(String encrypt, String signature, String timestamp, String nonce) {

		String format = "<xml>\n" + "<Encrypt><![CDATA[%1$s]]></Encrypt>\n"
				+ "<MsgSignature><![CDATA[%2$s]]></MsgSignature>\n" + "<TimeStamp>%3$s</TimeStamp>\n"
				+ "<Nonce><![CDATA[%4$s]]></Nonce>\n" + "</xml>";
		return String.format(format, encrypt, signature, timestamp, nonce);

	}

	public static void main(String[] args) {
		try {
			// InputStream in = new
			// FileInputStream("C:\\1.Project\\workspace\\zot\\build\\classes\\msg.xml");
			// xml2Msg(in);

			String enc = "ZjJEoUKKB6Wq5E7pHBE3Mre7c13iOvk4o/47+3+jWMw1qSe5Pp6n9e2PamqZbicZsOi59wKvAi3klyZa8znZs9sAsCLxnRgFICOB8/vYqCUnNQ2H5P4bGgvl812xhcyiis0YKa7vMSzl9c7lyqcGeYIxGAgnW+Ox5gSzjLU9nfy4jPy9Cn+LQCTu9Fv06p0qIrk4JHe2a7bMIOEI5YPGncTz6LUGA8eunEX3c/Z+c5znTAHgKkQt/IRqSxN4I605W9ycquwh+79zP046Zp4yVOAjcWqmh0VHt2McWPRMXg4CFymqlRPe5t+TCZftddSLPRkEcxFWT7zPq3Ggpc0f8gGmkcv5E9QFfygXXBv4k/5NpvEye7bQtkFaL5DB/Ae3Kj8+61lXpsyl5tx1CMPMP7yATqG4/iX5UxDuMa7esTdJQWNrz2vDPIU9pjCskOi1RB3vLeYUAepuemyD/La9Ww==";

			WXBizMsgCrypt wxcpt = null;
			try {
				wxcpt = new WXBizMsgCrypt(Constant.sToken, Constant.sEncodingAESKey, Constant.sCorpID);
			} catch (AesException e2) {

				e2.printStackTrace();
			}

			String t = wxcpt.DecryptMsg("c4df126ef9414bef9a9043e227d5c3c05f6f7d8d", "1437293021", "1772230247", enc);

			System.out.println(t);

		} catch (AesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
