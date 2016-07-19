package com.zot.xing.dao.offer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zot.db.JDBCTemplate;
import com.zot.db.ResultSetHandler;
import com.zot.util.DateAS;
import com.zot.util.StringUtils;
import com.zot.wechat.msg.Constant;

public class OfferASImpl implements OfferAS {
	private static final String ADD_OFFER_SQL = "insert into t_zot_offer (offer_code, offer_name,"
			+ " serv_catalog, serv_type_code, create_time, last_upd_time, price, min_price, commission_mode, "
			+ " commission_amount, commission_percent, prod_code, remark) values (?, ?, ?, ?, ?, ?, ?, ?, ?,"
			+ " ?, ?, ?, ?)";
	
	private static final String QRY_OFFER_SQL = "select * from t_zot_offer where 1 = 1 " + Constant.SQL_COND;
	
	@Override
	public void addOffer(OfferBO offer) 
	{
		JDBCTemplate<Object> sqlTemplate = new JDBCTemplate<Object>();
		List<Object> params = new ArrayList<Object>();
		params.add(offer.getOfferCode());
		params.add(offer.getOfferName());
		params.add(offer.getServCatalog());
		params.add(offer.getServTypeCode());
		params.add(DateAS.getCurrentSQLTimestamp());
		params.add(DateAS.getCurrentSQLTimestamp());
		params.add(offer.getPrice());
		params.add(offer.getMinPrice());
		params.add(offer.getCmission_mode());
		params.add(offer.getCmission_amount());
		params.add(offer.getCmissin_percent());
		params.add(offer.getProd_code());
		params.add(offer.getRemark());
		
		sqlTemplate.execute(ADD_OFFER_SQL, params);
	}

	@Override
	public List<OfferBO> queryOffers(OfferBO offerCond) 
	{
		JDBCTemplate<List<OfferBO>> jt = new JDBCTemplate<List<OfferBO>>();
		
		//构造查询的where条件和参数
		List<Object> params = new ArrayList<Object>();
		StringBuffer buf = new StringBuffer();
		if (!StringUtils.isEmpty(offerCond.getServCatalog()))
		{
			buf.append(" and serv_catalog = ?");
			params.add(offerCond.getServCatalog());
		}
		if (!StringUtils.isEmpty(offerCond.getServTypeCode()))
		{
			buf.append(" and serv_type_code = ?");
			params.add(offerCond.getServTypeCode());
		}
		
		String qrySQL = QRY_OFFER_SQL.replaceAll(Constant.SQL_COND, buf.toString());
		
		List<OfferBO> offers = jt.query(qrySQL, params, 
				new ResultSetHandler<List<OfferBO>>()
				{
					@Override
					public List<OfferBO> rsHandler(ResultSet rs) throws SQLException {
						List<OfferBO> offers = new ArrayList<OfferBO>();
						OfferBO offer = null;
						
						while (rs.next())
						{
							offer = cvt2Offer(rs);
							offers.add(offer);
						}
						
						return offers;
					}
				});
		
		return offers;
	}

	@Override
	public void updateOffer(OfferBO offer) {

	}
	
	private OfferBO cvt2Offer(ResultSet rs) throws SQLException
	{
		OfferBO offer = new OfferBO();
		offer.setOfferCode(rs.getString("offer_code"));
		offer.setOfferName(rs.getString("offer_name"));
		offer.setServCatalog(rs.getString("serv_catalog"));
		offer.setServTypeCode(rs.getString("serv_type_code"));
		offer.setCreateDate(rs.getTimestamp("create_time"));
		offer.setLstUpdDate(rs.getTimestamp("last_upd_time"));
		offer.setPrice(rs.getFloat("price"));
		offer.setMinPrice(rs.getFloat("min_price"));
		offer.setCmission_mode(rs.getInt("commission_mode"));
		offer.setCmission_amount(rs.getFloat("commission_amount"));
		offer.setCmissin_percent(rs.getInt("commission_percent"));
		offer.setProd_code(rs.getString("prod_code"));
		offer.setRemark(rs.getString("remark"));
		
		return offer;
	}
}
