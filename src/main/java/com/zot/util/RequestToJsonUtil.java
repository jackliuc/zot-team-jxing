/**
 * 
 */
package com.zot.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

/**
 * @author jack
 *
 */
public class RequestToJsonUtil {
	
	public static String requestTJson(HttpServletRequest request)
	{
		Map<String,String> params = new HashMap<String,String>();
		Enumeration<String> en = request.getParameterNames();
		while(en.hasMoreElements())
		{
			String k = en.nextElement();
			
			params.put(k, request.getParameter(k));
		}

		return JSONObject.toJSONString(params);
	}
}
