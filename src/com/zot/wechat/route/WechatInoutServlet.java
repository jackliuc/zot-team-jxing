package com.zot.wechat.route;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.zot.wechat.handle.EventHandleAS;
import com.zot.wechat.handle.RevMsgParse;
import com.zot.wechat.handle.TextMsgHandleAS;
import com.zot.wechat.handle.WechatHandle;
import com.zot.wechat.msg.Constant;
import com.zot.wechat.msg.WechatMsg;
import com.zot.wechat.util.AesException;
import com.zot.wechat.util.WXBizMsgCrypt;

public class WechatInoutServlet extends HttpServlet {

	private static final long serialVersionUID = -1847238807216447030L;

	/**
	 * 消息体签名(msg_signature)
	 */
	private static String msg_signature = "signature";

	/**
	 * 时间戳(timestamp)
	 */
	private static String timestamp = "timestamp";

	/**
	 * 随机数字串(nonce)
	 */
	private static String nonce = "nonce";

	/**
	 * 公众平台推送过来的随机加密字符串(echostr)
	 */
	private static String echostr = "echostr";

	private WXBizMsgCrypt wxcpt = null;

	private Logger logger = null;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();

		logger = Logger.getLogger(WechatInoutServlet.class);

		// 初始化加解密系统
		try {
			wxcpt = new WXBizMsgCrypt(Constant.sToken, Constant.sEncodingAESKey, Constant.sCorpID);
		} catch (AesException e) {
			logger.debug(e);
		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String sEchoStr = null; // 需要返回的明文
		try {

			sEchoStr = wxcpt.VerifyURL(request.getParameter(msg_signature), request.getParameter(timestamp),
					request.getParameter(nonce), request.getParameter(echostr));

		} catch (AesException e) {
			logger.debug(e);
		}

		if (sEchoStr != null) {

			OutputStream out = response.getOutputStream();
			try {
				out.write(sEchoStr.getBytes());
				out.flush();
			} finally {
				out.close();
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

			OutputStream out = resp.getOutputStream();
			try {
				String decContent = desRevMsgContent(request);
				String returnMsg = "";

					WechatMsg msg = RevMsgParse.xml2Msg(decContent);
					WechatHandle handle = null;
					switch (msg.getMsgType()) {
					case Constant.WECHAT_MSG_TEXT_TYPE:
						handle = new TextMsgHandleAS();

						returnMsg = handle.handle(msg);

						break;
					case Constant.WECHAT_MSG_EVENT_TYPE:
						handle = new EventHandleAS();

						returnMsg = handle.handle(msg);
						break;
					default:
						break;
					}
				logger.debug("res msg content:" + returnMsg);
				
				String rStr = wxcpt.EncryptMsg(returnMsg, "", request.getParameter(nonce));
				
				logger.debug("res msg:" + rStr);
				out.write(rStr.getBytes());
				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug(e);
			} finally {
				out.close();
			}
		} 


		

	/**
	 * 接受微信消息，并把消息体解密,如下：
	 * <xml>
<ToUserName><![CDATA[wx10a2f3c2019b7ceb]]></ToUserName>
<Encrypt><![CDATA[ZjJEoUKKB6Wq5E7pHBE3Mre7c13iOvk4o/47+3+jW
Mw1qSe5Pp6n9e2PamqZbicZsOi59wKvAi3klyZa8znZs9sAsCLxnRgFICOB8/vYqCUnNQ2H
5P4bGgvl812xhcyiis0YKa7vMSzl9c7lyqcGeYIxGAgnW+Ox5gSzjLU9nfy4jPy9Cn+LQCT
u9Fv06p0qIrk4JHe2a7bMIOEI5YPGncTz6LUGA8eunEX3c/Z+c5znTAHgKkQt/IRqSxN4I605W9yc
quwh+79zP046Zp4yVOAjcWqmh0VHt2McWPRMXg4CFymqlRPe5t+TCZftddSLPRkEcxFWT7zPq3Ggpc
0f8gGmkcv5E9QFfygXXBv4k/5NpvEye7bQtkFaL5DB/Ae3Kj8+61lXpsyl5tx1CMPMP7yATqG4/iX5UxD
uMa7esTdJQWNrz2vDPIU9pjCskOi1RB3vLeYUAepuemyD/La9Ww==]]></Encrypt>
<AgentID><![CDATA[1]]></AgentID>
</xml>
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 * @throws AesException
	 */
	private String desRevMsgContent(HttpServletRequest request) throws IOException, DocumentException, AesException {

		InputStream in = null;
		String s;
		try {
			in = request.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(in);
			// 字节流转换成InputStreamReader
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			StringBuilder sReqData = new StringBuilder();
			while ((s = bufferedReader.readLine()) != null) {
				sReqData.append(s);
			}

			logger.debug("rev mes :" + sReqData.toString());
			SAXReader reader = new SAXReader();
			Document doc = reader.read(new StringReader(sReqData.toString()));
			Element root = doc.getRootElement();
			String content = ((Element) (root.elements("Encrypt").get(0))).getStringValue();

			String msgSignV = request.getParameter(msg_signature);
			String timesV = request.getParameter(timestamp);
			String nonceV = request.getParameter(nonce);

			String decContent = wxcpt.DecryptMsg(msgSignV, timesV, nonceV, content);

			logger.debug("rev decMes :" + decContent);
			return decContent;
		} catch (IOException e) {
			logger.debug(e);
			throw e;
		} finally {
			if (null != in) {
				in.close();
			}
		}

	}

}
