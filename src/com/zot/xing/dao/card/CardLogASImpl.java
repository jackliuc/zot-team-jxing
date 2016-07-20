package com.zot.xing.dao.card;

import java.util.ArrayList;
import java.util.List;

import com.zot.db.JDBCTemplate;
import com.zot.util.DateAS;

public class CardLogASImpl implements CardLogAS {

	private static final String ADD_CARD_LOG_SQL = "insert into t_zot_card_log(id, card_no, cust_id, create_time, "
			+ "oper_id, log_type, amount, before_balance, after_balance, remark) values (?,?,?,?,?,?,?,?,?,?))";
	
	@Override
	public void addCardLog(CardLogBO cardLog) 
	{
		JDBCTemplate<Object> sqlTemplate = new JDBCTemplate<Object>();
		
		List<Object> params = new ArrayList<Object>();
		params.add(cardLog.getId());
		params.add(cardLog.getCardNo());
		params.add(cardLog.getCustId());
		params.add(DateAS.getSQLTimestamp(cardLog.getCreateTime()));
		params.add(cardLog.getOperId());
		params.add(cardLog.getLogType());
		params.add(cardLog.getAmount());
		params.add(cardLog.getBeforeAmt());
		params.add(cardLog.getAfterAmt());
		params.add(cardLog.getRemark());
		
		sqlTemplate.execute(ADD_CARD_LOG_SQL, params);

	}

	@Override
	public List<CardLogBO> queryCardLogs(CardLogBO cardLogCond) 
	{
		// TODO Auto-generated method stub
		return null;
	}

}
