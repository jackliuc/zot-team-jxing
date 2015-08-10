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
	
	/**
	 * yyyy-MM-dd
	 * @return
	 */
    public static java.sql.Date getCurrentSQLDate()
    {
    	return new java.sql.Date(DateAS.getCurrentDate().getTime());
    }
    
    /**
     * yyyy-MM-dd Hi24:mm:ss.SSS
     * @return
     */
    public static java.sql.Timestamp getCurrentSQLTimestamp()
    {
    	return new java.sql.Timestamp(getCurrentDate().getTime());
    }
}
