package com.zot.wechat.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.zot.wechat.msg.Constant;

public class WXAppOpenApi {
	private static Logger logger = Logger.getLogger(WXAppOpenApi.class);
	
	private String corpID = null;
	private String corpSecret = null;
	private String appID = null;
	
	private static Map<String, AccessToken> tokenMap = new ConcurrentHashMap<String, AccessToken>();
	
	//微信接口调用url常量
	private static final String I_GET_TOKEN = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
	private static final String I_GET_USERID = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo";
	
	public WXAppOpenApi(String corpID, String corpSecret, String appID)
	{
		this.corpID = corpID;
		this.corpSecret = corpSecret;
		this.appID = appID;
	}
	
	/**
	 * 获取userId
	 * @return
	 */
	public String getUserId(String code)
	{
		String token = getToken();
		String url = I_GET_USERID + "?access_token=" + token + "&code=" + code;		
		String ret = this.callSimpleGet(url);
		
		String userId = null;
		if (ret != null && ret.trim().length() > 0)
		{
			JSONObject jsonObject = JSONObject.parseObject(ret);
			String errCode = jsonObject.getString("errcode");
			if (errCode != null)
			{
				logger.error("getUserId failed, message:" + jsonObject.getString("errmsg"));
			}
			else
			{
				userId = jsonObject.getString("UserId");
			}			
		}
		
		logger.error("return userId is :" + userId);
		
		return userId;
	}
	
	private String getToken()
	{
		String key = this.corpID + "|" + this.corpSecret;
		AccessToken accessToken = tokenMap.get(key);
		
		if (accessToken == null || accessToken.isExpire())
		{
			String url = I_GET_TOKEN + "?corpid=" + this.corpID + "&corpsecret=" + this.corpSecret;
			String ret = this.callSimpleGet(url);
			if (ret != null && ret.trim().length() > 0)
			{
				JSONObject jsonObject = JSONObject.parseObject(ret);
				long maxExpTime = Long.parseLong(jsonObject.getString("expires_in"));
				String token = jsonObject.getString("access_token");
				
				accessToken = new AccessToken(token, maxExpTime);
				
				//缓存
				tokenMap.put(key, accessToken);
			}		
		}

		String token = null;
		if (accessToken != null)
		{
			token = accessToken.getToken();
		}
		else
		{
			logger.error("Get token failed, call http get return empty.");
		}
		
		logger.error("return token is :" + token);
		return token;
	}
	
	private String callHttpGet(String url)
	{
		CloseableHttpClient httpclient = HttpClients.createDefault(); 
		CloseableHttpResponse response = null;
		String retContent = null;
		
		try 
		{     
            HttpGet httpget = new HttpGet(url); 
            response = httpclient.execute(httpget);  
            HttpEntity entity = response.getEntity();  
                        
            if (entity != null && response.getStatusLine() != null 
            		&& response.getStatusLine().getStatusCode() == 200)
            {  
                retContent = EntityUtils.toString(entity);
            }              
        } 
		catch (Exception e) 
		{  
            e.printStackTrace();   
            logger.error(e.getMessage(), e);
        }
		finally 
		{  
            if (response != null)
            {
            	try 
            	{
					response.close();
				} 
            	catch (IOException e) {				
					e.printStackTrace();
				}
            }        	
            try 
            {  
                httpclient.close();  
            } 
            catch (IOException e)
            {  
                e.printStackTrace();  
            }  
        } 
		
		return retContent;
	}
	
	/**
	 * 新浪云上httpclient jar冲突，临时使用URLConnection进行调测
	 * @param url
	 * @return
	 */
	private String callSimpleGet(String url) 
	{  
        StringBuffer buf = new StringBuffer();  
        BufferedReader in = null;  
        //URLConnection conn = null;
        try {  
            URL realUrl = new URL(url);  
            
            TrustManager[] tm = {new MyX509TrustManager ()}; 
            SSLContext sslContext = SSLContext.getInstance("SSL"); 
            sslContext.init(null, tm, new java.security.SecureRandom()); 

            //从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            //创建HttpsURLConnection对象，并设置其SSLSocketFactory对象
            HttpsURLConnection httpsConn = (HttpsURLConnection)realUrl.openConnection();
            httpsConn.setSSLSocketFactory(ssf);
            
            // 打开和URL之间的连接  
            //conn = realUrl.openConnection();  
            // 设置通用的请求属性  
            //conn.setRequestProperty("accept", "*/*");  
            //conn.setRequestProperty("connection", "Keep-Alive");  
            //conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");  
            // 建立实际的连接  
            httpsConn.connect();            
            // 定义BufferedReader输入流来读取URL的响应  
            in = new BufferedReader(new InputStreamReader(httpsConn.getInputStream()));  
            String line;  
            while ((line = in.readLine()) != null) {  
            	buf.append(line);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
            logger.error(e.getMessage(), e);
        }  
        // 使用finally块来关闭输入流  
        finally {  
            try {  
                if (in != null) {  
                    in.close();  
                }  
            } catch (IOException ex) {  
                ex.printStackTrace();  
            }  
        }  
        return buf.toString();  
    }  
	
	public static void main(String[] args) {
		WXAppOpenApi api = new WXAppOpenApi(Constant.sCorpID, Constant.sCorpSecret, Constant.sAppID);
		String userId = api.getUserId("69fc12b8406c289290d8a6dd049caaaa");
		
		System.out.println(userId);
	}
	
}
