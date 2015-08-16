/**
 * 
 */
package com.zot.view.contorler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zot.sys.config.SystemInit;
import com.zot.xing.view.subscribe.SubscribeResultAction;

/**
 * @author jack 
 *
 * 作为ajax的控制器，作为ajax的数据分发和数据返回
 */
public class AjaxContorler extends HttpServlet {
	
	private static String REQUEST_SERVICE_CLASS = "serviceAction";
	
	private static String REQUEST_SERVICE_DATA = "requestD";
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String,String[]> values = request.getParameterMap();
		
		String clazzId = values.get(REQUEST_SERVICE_CLASS)[0];
		String clazz = SystemInit.getAppServiceClazz(clazzId);
		
		String data = values.get(REQUEST_SERVICE_DATA)[0];
		
		
		try {
			PrefixService service = (PrefixService)Thread.currentThread().getContextClassLoader().loadClass(clazz).newInstance();
						
			Object returnV = service.action(JSONObject.parseObject(data, HashMap.class));
	
			String returnS = JSONObject.toJSONString(returnV);
			
			OutputStream out = response.getOutputStream();
			try {
				out.write(returnS.getBytes());
				out.flush();
			} finally {
				out.close();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	
	}
}
