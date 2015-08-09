/**
 * 
 */
package com.zot.xing.dao.subscribe;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.zot.db.JDBCTemplate;
import com.zot.db.ResultSetHandler;

/**
 * @author jack
 *
 */
public class StatisticSerivceASImpl implements StatisticSerivceAS {

	/* (non-Javadoc)
	 * @see com.zot.xing.dao.subscribe.StatisticSerivceAS#computeCntCurrentService(java.lang.String)
	 */
	public int computeCntCurrentService(String serviceType) {
		String sql = "SELECT count(id) from t_zot_work_order where over_time is null order_type = " + serviceType;
		
		JDBCTemplate<Integer> jdbcTmp = new JDBCTemplate<Integer>();
		return jdbcTmp.query(sql, null, new ResultSetHandler<Integer>(){
			@Override
			public Integer rsHandler(ResultSet rs) throws SQLException {
				if(rs.next())
				{
					return rs.getInt(1);
				}
				
				return null;
			}
			
		});
	}

	/* (non-Javadoc)
	 * @see com.zot.xing.dao.subscribe.StatisticSerivceAS#computeCntAllCurrentService()
	 */
	@Override
	public int computeCntAllCurrentService() {
		String sql = "SELECT count(id) from t_zot_work_order where over_time is not null ";
		
		JDBCTemplate<Integer> jdbcTmp = new JDBCTemplate<Integer>();
		return jdbcTmp.query(sql, null, new ResultSetHandler<Integer>(){
			@Override
			public Integer rsHandler(ResultSet rs) throws SQLException {
				if(rs.next())
				{
					return rs.getInt(1);
				}
				
				return null;
			}
			
		});
	}

}
