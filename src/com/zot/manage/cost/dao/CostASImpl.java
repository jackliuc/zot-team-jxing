package com.zot.manage.cost.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zot.db.JDBCTemplate;
import com.zot.db.ResultSetHandler;
import com.zot.util.DateAS;
import com.zot.util.IdGen;
import com.zot.util.StringUtils;
import com.zot.wechat.msg.Constant;

public class CostASImpl implements CostAS {
	
	private static final String ADD_COST = "INSERT INTO t_zot_cost(COST_ID,COST_CAR_NO,CREATE_TIME,COST_TIME,"
			+ "COST_TYPE,COST_SUBTYPE,COST_AMOUNT,COST_OPERATOR,BALANCE,REMARK) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String UPDATE_COST = "UPDATE t_zot_cost SET cost_type=?, cost_subtype=?, remark=?"
			+ ", cost_amount=?, cost_operator=?, cost_time=? where cost_id=?";
	
	private static final String QRY_COST =  "select * from t_zot_cost where 1 = 1 "
			+ Constant.SQL_COND
			+ " order by cost_id desc limit 10";
	
	@Override
	public List<Cost> queryCost(Cost c) {
		JDBCTemplate<Cost> sqlTemplate = new JDBCTemplate<Cost>();
		
		//构造查询的where条件和参数
		List<Object> params = new ArrayList<Object>();
		StringBuffer buf = new StringBuffer();
		if (!StringUtils.isEmpty(c.getCostOperator()))
		{
			buf.append(" and cost_operator = ?");
			params.add(c.getCostOperator());
		}
		if (!StringUtils.isEmpty(c.getCostType()))
		{
			buf.append(" and cost_type = ?");
			params.add(c.getCostType());
		}
		String qrySQL = QRY_COST.replaceAll(Constant.SQL_COND, buf.toString());
		
		final List<Cost> result = new ArrayList<>();
		sqlTemplate.query(qrySQL, params, new ResultSetHandler<Cost>(){

			@Override
			public Cost rsHandler(ResultSet rs) throws SQLException {
				while (rs.next()) {
					result.add(cvt2Cost(rs));
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
		params.add(c.getCostCarNo());
		params.add(DateAS.getCurrentSQLTimestamp());
		params.add(DateAS.getDateFromString(c.getCostTime(),"yyyy-MM-dd"));
		params.add(c.getCostType());
		params.add(c.getCostSubType());
		params.add(c.getCostAmount());
		params.add(c.getCostOperator());
		params.add(c.getCostBalance());
		params.add(c.getRemark());

		sqlTemplate.execute(ADD_COST, params);
		
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
		params.add(DateAS.getDateFromString(c.getCostTime(),"yyyy-MM-dd"));
		params.add(c.getCostId());
		sqlTemplate.execute(UPDATE_COST, params);
		
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
	
	private Cost cvt2Cost(ResultSet rs) throws SQLException
	{
		Cost cost = new Cost();
		cost.setCostAmount(rs.getFloat("cost_amount"));
		cost.setCostId(rs.getString("cost_id"));
		cost.setCostOperator(rs.getString("cost_operator"));
		cost.setCostSubType(rs.getString("cost_subtype"));
		cost.setCostTime(DateAS.convertDate2Str(rs.getTimestamp("cost_time"),"yyyy-MM-dd"));
		cost.setCostType(rs.getString("cost_type"));
		cost.setCreateTime(DateAS.convertDate2Str(rs.getTimestamp("create_time"),"yyyy-MM-dd HH:mm:ss"));
		cost.setRemark(rs.getString("remark"));
		cost.setCostCarNo(rs.getString("cost_car_no"));
		cost.setCostBalance(rs.getFloat("balance"));
		
		return cost;
	}

}
