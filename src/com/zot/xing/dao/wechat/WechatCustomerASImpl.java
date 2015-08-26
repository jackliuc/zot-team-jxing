/**
 * 微信用户订阅更新类
 */
package com.zot.xing.dao.wechat;

import java.util.ArrayList;
import java.util.List;

import com.zot.db.JDBCTemplate;
import com.zot.util.DateAS;

/**
 * @author jack
 *
 */
public class WechatCustomerASImpl implements WechatCustomerAS{

	private static final String INSERT_INTO_T_ZOT_XING_WECHATSUB_SQL = "INSERT INTO t_zot_xing_wechatsub VALUES (?, ?, ?, ?,?)";
	
	@Override
	public void addWechatCustomer(WechatCustomer customer) {
		JDBCTemplate<Object> sqlTemplate = new JDBCTemplate<Object>();
		List<Object> params = new ArrayList<Object>();
		params.add(customer.getWechatNO());
		params.add(customer.getWechatName());
		params.add(customer.getPhoneNo());
		params.add(DateAS.getCurrentSQLTimestamp());
		params.add(null);
		sqlTemplate.execute(INSERT_INTO_T_ZOT_XING_WECHATSUB_SQL, params);
	}

	private String UPDATE_INTO_T_ZOT_XING_WECHATSUB_SQL = "update t_zot_xing_wechatsub set calsubtime = ? where wechatno = ?";
	@Override
	public void updateCalSubCustomer(String wechatNo) {
	
//		updateSql += wechatNo;
		
		JDBCTemplate<Object> sqlTemplate = new JDBCTemplate<Object>();
		List<Object> params = new ArrayList<Object>();
		params.add(DateAS.getCurrentSQLTimestamp());
		params.add(wechatNo);
		sqlTemplate.execute(UPDATE_INTO_T_ZOT_XING_WECHATSUB_SQL, params);
	}
	
}
