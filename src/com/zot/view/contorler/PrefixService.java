/**
 * 
 */
package com.zot.view.contorler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.zot.wechat.handle.RevMsgParse;

/**
 * @author jack
 * 
 */
public abstract class PrefixService {
	
	private static Logger log = Logger.getLogger(PrefixService.class);
	
	private HttpServletRequest request = null;
	
	private HttpServletResponse response = null;
	
	/**
	 * 逻辑架构入口，返回的对象为json对象格式，值为前台页面的DataModel
	 * @return
	 */
	public abstract String action();
	
	/**
	 * 根据输入的key值返回request 请求中信息
	 * 
	 * @param tag
	 * @return
	 */
	protected Object getValue(String key)
	{
		if(null == request)
		{
			log.error("request can not be null");
			
			return null;
		}
		
		return request.getParameter(key);
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	
}
