package com.zot.manage.cost.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zot.db.JDBCTemplate;
import com.zot.db.ResultSetHandler;
import com.zot.util.DateAS;

public class CostBalASImpl implements CostBalAS {

	private static final String QRY_COST_BAL = "select * from t_zot_cost_bal";
	
	private static final String UPDATE_COST_BAL = "update t_zot_cost_bal set cost_balance = ?"
			+ ", LAST_UPDATE_DATE = ?";
	
	@Override
	public CostBal queryCostBal() {
		JDBCTemplate<CostBal> jt = new JDBCTemplate<CostBal>();
				
		CostBal costBal = jt.query(QRY_COST_BAL, null, new ResultSetHandler<CostBal>()
				{
					@Override
					public CostBal rsHandler(ResultSet rs) throws SQLException {
						CostBal costBal = null;
						
						if (rs.next())
						{
							costBal = cvt2CostBal(rs);
						}
						
						return costBal;
					}
				});
		
		return costBal;
	}

	@Override
	public void updateCostBal(CostBal costBal) {
		JDBCTemplate<Object> sqlTemplate = new JDBCTemplate<Object>();
		List<Object> params = new ArrayList<Object>();	
		
		params.add(Float.valueOf(costBal.getCostBalance()));
		params.add(DateAS.getSQLTimestamp(costBal.getLastUpdDate()));
		
		sqlTemplate.execute(UPDATE_COST_BAL, params);

	}
	
	private CostBal cvt2CostBal(ResultSet rs) throws SQLException
	{
		CostBal costBal = new CostBal();
		costBal.setCostCarNo(rs.getString("COST_CAR_NO"));
		costBal.setCostBalance(rs.getFloat("COST_BALANCE"));
		
		return costBal;
	}

}
