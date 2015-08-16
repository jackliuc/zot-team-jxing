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
public interface PrefixService {

	/**
	 * 逻辑架构入口，返回的对象为json对象格式，值为前台页面的DataModel
	 * @return
	 */
	public abstract Object action(Map<String,String> context);
}
