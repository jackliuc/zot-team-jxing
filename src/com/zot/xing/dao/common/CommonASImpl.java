package com.zot.xing.dao.common;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zot.db.JDBCTemplate;
import com.zot.db.ResultSetHandler;

public class CommonASImpl implements CommonAS{

	@Override
	public List<IdAmount> qryIdAmounts(String sql) 
	{
		return qryIdAmounts(sql, null);
	}
	
	@Override
	public List<IdAmount> qryIdAmounts(String sql, List<Object> params) 
	{
		JDBCTemplate<List<IdAmount>> jt = new JDBCTemplate<List<IdAmount>>();
		
		List<IdAmount> idAmounts = jt.query(sql, params, new ResultSetHandler<List<IdAmount>>()
				{
					@Override
					public List<IdAmount> rsHandler(ResultSet rs) throws SQLException {
						List<IdAmount> idAmountList = new ArrayList<IdAmount>();
						IdAmount idAmount = null;
						
						while (rs.next())
						{
							idAmount = new IdAmount();
							idAmount.setId(rs.getString(1));
							idAmount.setAmount(rs.getFloat(2));
							
							idAmountList.add(idAmount);
						}
						
						return idAmountList;
					}
				});
		
		return idAmounts;
	}
}
