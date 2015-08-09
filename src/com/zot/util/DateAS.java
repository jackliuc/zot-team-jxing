/**
 * 日期处理类
 */
package com.zot.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @author jack
 *
 */
public class DateAS {

	public static Date getCurrentDate()
	{
		return Calendar.getInstance().getTime();
	}
	
    public static java.sql.Date getCurrentSQLDate()
    {
    	return new java.sql.Date(DateAS.getCurrentDate().getTime());
    }
}
