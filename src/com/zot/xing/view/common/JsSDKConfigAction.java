package com.zot.xing.view.common;

import java.util.Map;

import org.apache.log4j.Logger;

import com.mysql.jdbc.StringUtils;
import com.zot.util.ZOTException;
import com.zot.view.contorler.PrefixService;
import com.zot.wechat.msg.Constant;
import com.zot.wechat.util.JsSDKConfig;
import com.zot.wechat.util.JsSDKSign;
import com.zot.wechat.util.WXAppOpenApi;

public class JsSDKConfigAction implements PrefixService {
	
	private static Logger logger = Logger.getLogger(JsSDKConfigAction.class);
	
	public JsSDKConfig action(Map<String,String> context) {
		String targetUrl = context.get("targetUrl");
        if (StringUtils.isNullOrEmpty(targetUrl)) {
        	logger.error("The target url is empty.");
            throw new ZOTException("The target url is empty.");            
        }
        
        int index = targetUrl.indexOf("#");
        if (index > 0) {
            targetUrl = targetUrl.substring(0, index);            
            logger.debug("targetUrl：" + targetUrl);
        }
        
        WXAppOpenApi api = new WXAppOpenApi(Constant.sCorpID, Constant.sCorpSecret, Constant.sAppID);
        String ticket = api.getJsTicket();
        
        JsSDKConfig jsSDKConfig = JsSDKSign.sign(ticket, targetUrl);         
        logger.debug(jsSDKConfig.toString());
       
		return jsSDKConfig;
	}

}
