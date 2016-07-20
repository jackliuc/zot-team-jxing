package com.zot.xing.dao.card;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zot.db.JDBCTemplate;
import com.zot.db.ResultSetHandler;
import com.zot.util.DateAS;

public class CardASImpl implements CardAS 
{
	private static final String ADD_CARD_SQL = "insert into t_zot_card(card_no, cust_id, create_time, "
			+ "class_code, balance, remark) values (?,?,?,?,?,?)";
	
	private static final String QRY_CARD_SQL = "select * from t_zot_card";
	
	@Override
	public void addCard(CardBO card) {
		JDBCTemplate<Object> sqlTemplate = new JDBCTemplate<Object>();
		
		List<Object> params = new ArrayList<Object>();
		params.add(card.getCardNo());
		params.add(card.getCustId());
		params.add(DateAS.getSQLTimestamp(card.getCreateTime()));
		params.add(card.getClassCode());
		params.add(card.getBalance());
		params.add(card.getRemark());
		
		sqlTemplate.execute(ADD_CARD_SQL, params);
	}

	@Override
	public List<CardBO> queryCards(CardBO cardCond) 
	{
		//TODO:
		JDBCTemplate<List<CardBO>> jt = new JDBCTemplate<List<CardBO>>();
		List<CardBO> cards = jt.query(QRY_CARD_SQL, null, 
				new ResultSetHandler<List<CardBO>>()
				{
					@Override
					public List<CardBO> rsHandler(ResultSet rs) throws SQLException {
						List<CardBO> cards = new ArrayList<CardBO>();
						CardBO card = null;
						
						while (rs.next())
						{
							card = cvt2Card(rs);
							cards.add(card);
						}
						
						return cards;
					}
				});
		
		return cards;
	}
	
	private CardBO cvt2Card(ResultSet rs)throws SQLException
	{
		CardBO card = new CardBO();
		card.setCardNo(rs.getString("card_no"));
		card.setCustId(rs.getString("cust_id"));
		card.setClassCode(rs.getString("class_code"));
		card.setRemark(rs.getString("remark"));
		card.setCreateTime(rs.getTimestamp("create_time"));
		card.setBalance(rs.getFloat("balance"));
		
		return card;
	}

}
