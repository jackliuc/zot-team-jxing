/**
 * 
 */
package com.zot.view.contorler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.zot.sys.config.SystemInit;

/**
 * @author jack
 *
 *         作为ajax的控制器，作为ajax的数据分发和数据返回
 */
public class AjaxContorler extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static String REQUEST_SERVICE_CLASS = "serviceAction";

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String clazzId = request.getParameter(REQUEST_SERVICE_CLASS);
		String clazz = SystemInit.getAppServiceClazz(clazzId);
		Map<String, String> context = new HashMap<String, String>();
		Enumeration<String> en = request.getParameterNames();
		while (en.hasMoreElements()) {
			String elem = en.nextElement();
			if (!elem.equals(REQUEST_SERVICE_CLASS)) {
				context.put(elem, request.getParameter(elem));
			}
		}

		try {
			PrefixService service = (PrefixService) Thread.currentThread().getContextClassLoader().loadClass(clazz)
					.newInstance();

			Object returnV = service.action(context);

			if (null != returnV) {
				String returnS = JSONObject.toJSONString(returnV);

				OutputStream out = response.getOutputStream();
				try {
					out.write(returnS.getBytes());
					out.flush();
				} finally {
					out.close();
				}
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
