/**
 * 服务提供能力
 */
package com.zot.xing.dao.subscribe;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.zot.db.JDBCTemplate;
import com.zot.db.ResultSetHandler;

/**
 * @author jack
 *
 */
public class ServiceUtils {
	
	private static Map<String,ServiceBO> services = null;
	
	private static Lock lock = new ReentrantLock();
	
	public static void init()
	{
		if(services == null)
		{
			lock.lock();
			if(services == null)
			{
				services = queryServices();
			}
			lock.unlock();
		}
	}
	
	public static List<ServiceBO> queryAllServices()
	{
		if(services == null)
		{
			init();
		}
		
		return new ArrayList<ServiceBO>(services.values());
	}
	
	/**
	 * 通过serviceKey查询服务信息，初始化的时候只需要把services 值为null
	 * @param key
	 * @return
	 */
	public static ServiceBO queryServiceBySKey(String key)
	{
		if(services == null)
		{
			init();
		}
		
		return services.get(key);
	}
	
	private static Map<String,ServiceBO> queryServices()
	{
		String sql = "SELECT service_id, service_name, service_des, cost_time, current_cnt,price  "
				+ "FROM t_zot_service order by service_id";
		JDBCTemplate<Map<String,ServiceBO>> jdbcTmp = new JDBCTemplate<Map<String,ServiceBO>>();
		return jdbcTmp.query(sql, null, new ResultSetHandler<Map<String,ServiceBO>>(){
			@Override
			public Map<String,ServiceBO> rsHandler(ResultSet rs) throws SQLException {
				Map<String,ServiceBO> tmpService = new LinkedHashMap<String,ServiceBO>();
				while(rs.next())
				{
					
					ServiceBO bo = new ServiceBO();
					String serviceID = rs.getString("service_id").trim();
					bo.setService_id(serviceID);
					bo.setService_des(rs.getString("service_des"));
					bo.setService_name(rs.getString("service_name"));
					
					bo.setCost_time(rs.getLong("cost_time"));
					bo.setCurrent_cnt(rs.getLong("current_cnt"));
					bo.setPrice(rs.getLong("price"));
					tmpService.put(serviceID, bo);
				}
				
				return tmpService;
			}
			
		});
	}
}
