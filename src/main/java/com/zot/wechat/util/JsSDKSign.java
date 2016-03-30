package com.zot.wechat.util;

import java.security.MessageDigest;
import java.util.Formatter;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.zot.wechat.msg.Constant;

public class JsSDKSign 
{
	private static Logger logger = Logger.getLogger(JsSDKSign.class);
	
    public static JsSDKConfig sign(String jsapi_ticket, String url) 
    {
        JsSDKConfig jsConfig = new JsSDKConfig();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String tmpstr;
        String signature = "";
 
        //注意这里参数名必须全部小写，且必须有序
        tmpstr = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
        //System.out.println(tmpstr);
 
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(tmpstr.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
            //System.out.println(signature);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        } 
        
        jsConfig.setAppId(Constant.sCorpID);
        jsConfig.setNonceStr(nonce_str);
        jsConfig.setTimestamp(timestamp);
        jsConfig.setSignature(signature);
 
        return jsConfig;
    }
 
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
 
    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }
 
    private static String create_timestamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }
    
    public static void main(String[] args) {
    	String jsapi_ticket = "sM4AOVdWfPE4DxkXGEs8VOFkvqrRCJeTBVREaEhkfTbzQplLBl0F5dCT9cJCeXkue7VjGjzgA053fcUFhhqrrw";
    	String url = "http://jzot.sinaapp.com/xing-service/deal-service.jsp?code=8ea974b508337131559862c6e622a9bf&state=jzot";
    	
    	JsSDKConfig jsConfig = JsSDKSign.sign(jsapi_ticket, url);
    	System.out.println(jsConfig);
	}
}
