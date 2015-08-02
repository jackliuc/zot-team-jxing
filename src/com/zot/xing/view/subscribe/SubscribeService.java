/**
 * 
 */
package com.zot.xing.view.subscribe;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jack
 * 计算服务时间
 */
public class SubscribeService {
	
	public static String computeServiceTime(String key)
	{
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
	}
	
}
