package com.zot.xing.dao.subscribe;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zot.db.JDBCTemplate;
import com.zot.db.ResultSetHandler;
import com.zot.util.DateAS;

public class CustomerASImpl implements CustomerAS 
{
	private static final String QRY_CUST_BY_CUSTID = 
			"select * from t_zot_customer where cust_id = ?";
	
	private static final String QRY_CUST_BY_WECHATNO = 
			"select * from t_zot_customer where wechatno = ?";
	
	private static final String QRY_CUST_BY_PHONENO = 
			"select * from t_zot_customer where phoneno = ?";
	
	private static final String ADD_CUST = 
			"insert into t_zot_customer(cust_id, cust_name, wechatno, wechatname, phoneno, "
			+ "age, address, email, subtime, calsubtime, remark, sex) values (?, ?, ?,?, ?, ?, ?, ?,?, ?, ?, ?)";
	
	private static final String UPDATE_CUST = 
			"update t_zot_customer set cust_name = ?, wechatno = ?, phoneno = ?, sex = ?, address = ?, remark = ?"
			+ " where cust_id = ?";

	@Override
	public void addCustomer(CustomerBO customer) {
		JDBCTemplate<Object> jt = new JDBCTemplate<Object>();
		
		List<Object> params = new ArrayList<Object>();
		params.add(customer.getCustId());
		params.add(customer.getCustName());
		params.add(customer.getWechatno());
		params.add(customer.getWechatName());
		params.add(customer.getPhoneno());
		params.add(Integer.valueOf(customer.getAge()));
		params.add(customer.getAddress());
		params.add(customer.getEmail());
		params.add(DateAS.getSQLTimestamp(customer.getSubTime()));
		params.add(DateAS.getSQLTimestamp(customer.getCalSubTime()));
		params.add(customer.getRemark());
		params.add(customer.getSex());
		
		jt.execute(ADD_CUST, params);
	}

	@Override
	public void updateCustomer(CustomerBO customer) {
		JDBCTemplate<Object> jt = new JDBCTemplate<Object>();
		
		List<Object> params = new ArrayList<Object>();
		
		params.add(customer.getCustName());
		params.add(customer.getWechatno());
		params.add(customer.getPhoneno());
		params.add(customer.getSex());
		params.add(customer.getAddress());
		params.add(customer.getRemark());
		params.add(customer.getCustId());
		
		jt.execute(UPDATE_CUST, params);
		
	}

	@Override
	public CustomerBO queryCustomerByWechatno(String wechatno) {
		JDBCTemplate<CustomerBO> jt = new JDBCTemplate<CustomerBO>();
		
		List<Object> params = new ArrayList<Object>();
		params.add(wechatno);
		
		CustomerBO customer = jt.query(QRY_CUST_BY_WECHATNO, params, new ResultSetHandler<CustomerBO>()
				{
					@Override
					public CustomerBO rsHandler(ResultSet rs) throws SQLException {
						if (rs.next())
						{
							return cvt2CustomerBO(rs);
						}
						return null;
					}
					
				});
				
		return customer;
	}

	@Override
	public CustomerBO queryCustomerByCustId(String custId) {
		JDBCTemplate<CustomerBO> jt = new JDBCTemplate<CustomerBO>();
		
		List<Object> params = new ArrayList<Object>();
		params.add(custId);
		
		CustomerBO customer = jt.query(QRY_CUST_BY_CUSTID, params, new ResultSetHandler<CustomerBO>()
				{
					@Override
					public CustomerBO rsHandler(ResultSet rs) throws SQLException {
						if (rs.next())
						{
							return cvt2CustomerBO(rs);
						}
						return null;
					}
					
				});
				
		return customer;
	}

	@Override
	public CustomerBO queryCustomerByPhoneno(String phoneno) {
		JDBCTemplate<CustomerBO> jt = new JDBCTemplate<CustomerBO>();
		
		List<Object> params = new ArrayList<Object>();
		params.add(phoneno);
		
		CustomerBO customer = jt.query(QRY_CUST_BY_PHONENO, params, new ResultSetHandler<CustomerBO>()
				{
					@Override
					public CustomerBO rsHandler(ResultSet rs) throws SQLException {
						if (rs.next())
						{
							return cvt2CustomerBO(rs);
						}
						return null;
					}
					
				});
				
		return customer;
	}
	
	private CustomerBO cvt2CustomerBO(ResultSet rs) throws SQLException
	{
		CustomerBO customer = new CustomerBO();
		customer.setCustId(rs.getString("cust_id"));
		customer.setPhoneno(rs.getString("phoneno"));
		customer.setWechatno(rs.getString("wechatno"));
		customer.setCustName(rs.getString("cust_name"));
		customer.setWechatName(rs.getString("wechatname"));
		
		customer.setAge(rs.getInt("age"));
		customer.setSex(rs.getString("sex"));
		customer.setEmail(rs.getString("email"));
		customer.setAddress(rs.getString("address"));
		customer.setSubTime(rs.getTimestamp("subtime"));
		customer.setCalSubTime(rs.getTimestamp("calsubtime"));
		customer.setRemark(rs.getString("remark"));
		
		return customer;
	}

}
