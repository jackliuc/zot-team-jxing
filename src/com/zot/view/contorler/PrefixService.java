/**
 * 
 */
package com.zot.view.contorler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * @author jack
 * 
 */
public abstract class PrefixService {
	
	private static Logger log = Logger.getLogger(PrefixService.class);
	
	private Map<String,String[]> context = null;
	
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
		if(null == context)
		{
			log.error("request context can not be null");
			return null;
		}
		
		return context.get(key);
	}

	public void setContext(Map<String, String[]> context) {
		this.context = context;
	}


	
}
