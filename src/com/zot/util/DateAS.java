/**
 * 日期处理类
 */
package com.zot.util;

import java.text.SimpleDateFormat;
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
	
	public static String getCurrentDateYHM()
	{
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(getCurrentDate());
	}
	
    public static String addMinsWithCurrentDate(Long min)
    {
    	long times = getCurrentDate().getTime()+min*60*1000;
    	
    	return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(times));
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
