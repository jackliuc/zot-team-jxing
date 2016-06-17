package com.zot.wechat.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.zot.util.StringUtils;
import com.zot.wechat.msg.Constant;

public class WXAppOpenApi {
	private static Logger logger = Logger.getLogger(WXAppOpenApi.class);
	
	private String corpID = null;
	private String corpSecret = null;
	//private String appID = null;
	
	private static Map<String, AccessToken> tokenMap = new ConcurrentHashMap<String, AccessToken>();
	private static Map<String, AccessToken> authTokenMap = new ConcurrentHashMap<String, AccessToken>();
	private static Map<String, JsTicket> ticketMap = new ConcurrentHashMap<String, JsTicket>();
	
	
	//微信接口调用url常量
	//https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
	//https://qyapi.weixin.qq.com/cgi-bin/gettoken
	private static final String I_GET_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
	
	private static final String I_GET_AUTH_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?grant_type=authorization_code";
	
	//https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
	//https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo
	private static final String I_GET_USERID = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	//https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi
	//https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket
	private static final String I_GET_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
	
	
	
	public WXAppOpenApi(String corpID, String corpSecret, String appID)
	{
		this.corpID = corpID;
		this.corpSecret = corpSecret;
		//this.appID = appID;
	}
	
	/**
	 * 通过basic方式获取用户Id
	 * 获取userId
	 * @return
	 */
	public String getUserId(String code)
	{
		logger.info("code:" + code);
		//appid=APPID&secret=SECRET&code=CODE
		String url = I_GET_AUTH_TOKEN + "&appid=" + this.corpID + "&secret=" + this.corpSecret + "&code=" + code;
		String ret = this.callSimpleGet(url);
		String userId = null;
		if (ret != null && ret.trim().length() > 0)
		{
			JSONObject jsonObject = JSONObject.parseObject(ret);
			String errCode = jsonObject.getString("errcode");
			if (StringUtils.isEmpty(errCode))//成功
			{
				userId = jsonObject.getString("openid");
			}
			else//失败
			{
				String errmsg = jsonObject.getString("errmsg");
				logger.error("get userId failed, errorCode:" + errCode + ",errMsg:" + errmsg);
			}
		}
		
		//特殊处理，仅用于调测
		if (StringUtils.isEmpty(userId))
		{
			userId = "debug_userId";
		}
		
		logger.info("return userid is :" + userId);
		return userId;
	}
	
	public String getAdvUserId(String code)
	{
		String token = getAuthToken(code);
		//https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
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
		
		//如果无法获取微信用户id，则使用调测用户Id
		if (userId == null)
		{
			userId = "debug_userId";
			logger.debug("use debug userId debug_userId.");
		}
		
		return userId;
	}
	
	public String getJsTicket()
	{
		String key = this.corpID + "|" + this.corpSecret;
		JsTicket jsTicket = ticketMap.get(key);
		
		if (jsTicket == null || jsTicket.isExpire())//首次或已失效，则进行重新获取
		{
			String token = getToken();
			String url = I_GET_TICKET + "?access_token=" + token + "&type=jsapi";
			String ret = this.callSimpleGet(url);
			
			if (ret != null && ret.trim().length() > 0)
			{
				JSONObject jsonObject = JSONObject.parseObject(ret);
				String errCode = jsonObject.getString("errcode");
				if ("0".equals(errCode))//成功
				{
					long maxExpTime = Long.parseLong(jsonObject.getString("expires_in"));
					String ret_jsticket = jsonObject.getString("ticket");					
					jsTicket = new JsTicket(ret_jsticket, maxExpTime);					
					//缓存ticket
					ticketMap.put(key, jsTicket);					
				}
				else
				{
					String errMsg = jsonObject.getString("errmsg");
					logger.error("get js ticket failed, errcode:" + errCode + ",errMsg:" + errMsg);
				}			
			}
		}
				
		String ticket = null;
		if (jsTicket != null)
		{
			ticket = jsTicket.getTicket();
		}
		else
		{
			logger.error("Get js ticket failed, call http get return empty.");
		}
		
		logger.error("return js ticket is :" + ticket);
		return ticket;
	}
	
	private String getAuthToken(String code)
	{
		String key = this.corpID + "|" + this.corpSecret + "|" + code;
		//TODO:对于这个authToken的有效期，需要根据Code以及Token的有效期综合判断
		AccessToken accessToken = authTokenMap.get(key);
		
		if (accessToken == null || accessToken.isExpire())
		{
			//appid=APPID&secret=SECRET&code=CODE
			String url = I_GET_AUTH_TOKEN + "&appid=" + this.corpID + "&secret=" + this.corpSecret + "&code=" + code;
			String ret = this.callSimpleGet(url);
			if (ret != null && ret.trim().length() > 0)
			{
				JSONObject jsonObject = JSONObject.parseObject(ret);
				String errCode = jsonObject.getString("errcode");
				if (errCode == null || errCode.isEmpty())//成功
				{
					long maxExpTime = Long.parseLong(jsonObject.getString("expires_in"));
					String token = jsonObject.getString("access_token");
					
					accessToken = new AccessToken(token, maxExpTime);
					
					//缓存
					authTokenMap.put(key, accessToken);
				}
				else//失败
				{
					String errmsg = jsonObject.getString("errmsg");
					logger.error("get auth token failed, errorCode:" + errCode + ",errMsg:" + errmsg);
				}
			}		
		}

		String token = null;
		if (accessToken != null)
		{
			token = accessToken.getToken();
		}
		else
		{
			logger.error("Get auth token failed, call http get return empty.");
		}
		
		logger.error("return auth token is :" + token);
		return token;
	}
	
	private String getToken()
	{
		String key = this.corpID + "|" + this.corpSecret;
		AccessToken accessToken = tokenMap.get(key);
		
		if (accessToken == null || accessToken.isExpire())
		{
			String url = I_GET_TOKEN + "&appid=" + this.corpID + "&secret=" + this.corpSecret;
			String ret = this.callSimpleGet(url);
			if (ret != null && ret.trim().length() > 0)
			{
				JSONObject jsonObject = JSONObject.parseObject(ret);
				String errCode = jsonObject.getString("errcode");
				if (errCode == null || errCode.isEmpty())//成功
				{
					long maxExpTime = Long.parseLong(jsonObject.getString("expires_in"));
					String token = jsonObject.getString("access_token");
					
					accessToken = new AccessToken(token, maxExpTime);
					
					//缓存
					tokenMap.put(key, accessToken);
				}
				else//失败
				{
					String errmsg = jsonObject.getString("errmsg");
					logger.error("get token failed, errorCode:" + errCode + ",errMsg:" + errmsg);
				}
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
		
		logger.info("return token is :" + token);
		return token;
	}
	
	/*
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
	*/
	
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
		
		//String userId = api.getUserId("69fc12b8406c289290d8a6dd049caaaa");
		
		String ticket = api.getUserId("0416QPWk1d0Zcy0bY5Vk1SHKWk16QPWH");
		System.out.println(ticket);
	}
	
}
