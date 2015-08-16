/**
 * 
 */
package com.zot.view.contorler;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zot.xing.view.subscribe.SubscribeResultServiceImpl;

/**
 * @author jack 
 *
 * 作为ajax的控制器，作为ajax的数据分发和数据返回
 */
public class AjaxContorler extends HttpServlet {
	
	private static String REQUEST_SERVICE_CLASS = "serviceAction";
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,String[]> values = request.getParameterMap();
		String clazz = values.get(REQUEST_SERVICE_CLASS)[0];
		PrefixService service = new SubscribeResultServiceImpl();
//		service.setContext(values.get("data"));
		  String dataModel = service.action();
	}
}
