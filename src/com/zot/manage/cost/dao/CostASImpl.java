package com.zot.manage.cost.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zot.db.JDBCTemplate;
import com.zot.db.ResultSetHandler;
import com.zot.util.DateAS;
import com.zot.util.IdGen;

public class CostASImpl implements CostAS {
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	private static String formatDate(Date date) {
		return dateFormat.format(date);
	}
	
	private static Date parseDate(String date) {
		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Cost> listCost(Cost c) {
		JDBCTemplate<Cost> sqlTemplate = new JDBCTemplate<Cost>();
		
		
		final List<Cost> result = new ArrayList<>();
		sqlTemplate.query("select * from t_zot_cost", null, new ResultSetHandler<Cost>(){

			@Override
			public Cost rsHandler(ResultSet rs) throws SQLException {
				while (rs.next()) {
					Cost cost = new Cost();
					cost.setCostAmount(rs.getFloat("cost_amount"));
					cost.setCostId(rs.getString("cost_id"));
					cost.setCostOperator(rs.getString("cost_operator"));
					cost.setCostSubType(rs.getString("cost_subtype"));
					cost.setCostTime(formatDate(rs.getDate("cost_time")));
					cost.setCostType(rs.getString("cost_type"));
					cost.setCreateTime(DateAS.convertDate2Str(rs.getDate("create_time")));
					cost.setRemark(rs.getString("remark"));
					
					result.add(cost);
				}
				return null;
			}
			
		});
		
		return result;
	}

	@Override
	public Cost createCost(Cost c) {
		JDBCTemplate<Object> sqlTemplate = new JDBCTemplate<Object>();
		List<Object> params = new ArrayList<Object>();
		String costId = IdGen.genCostId();
		params.add(costId);
		params.add(c.getCostType());
		params.add(c.getCostSubType());
		params.add(c.getRemark());
		params.add(c.getCostAmount());
		params.add(c.getCostOperator());
		params.add(parseDate(c.getCostTime()));
		params.add(DateAS.getCurrentSQLTimestamp());
		sqlTemplate.execute("INSERT INTO t_zot_cost VALUES (?, ?, ?, ?, ?, ?, ?, ?)", params);
		
		c.setCostId(costId);
		return c;
	}
	
	@Override
	public Cost updateCost(Cost c) {
		JDBCTemplate<Object> sqlTemplate = new JDBCTemplate<Object>();
		List<Object> params = new ArrayList<Object>();
		params.add(c.getCostType());
		params.add(c.getCostSubType());
		params.add(c.getRemark());
		params.add(c.getCostAmount());
		params.add(c.getCostOperator());
		params.add(parseDate(c.getCostTime()));
		params.add(c.getCostId());
		sqlTemplate.execute("UPDATE t_zot_cost SET cost_type=?, cost_subtype=?, remark=?, cost_amount=?, cost_operator=?, cost_time=? where cost_id=?", params);
		
		return c;
	}
	
	@Override
	public List<Cost> saveCosts(List<Cost> costs) {
		// transaction control??
		List<Cost> results = new ArrayList<>();
		
		for (Cost c : costs) {
			Cost result = null;
			String costId = c.getCostId();
			if (costId == null) {
				result = createCost(c);
			} else {
				result = updateCost(c);
			}
			
			results.add(result);
		}
		return results;
	}

}
