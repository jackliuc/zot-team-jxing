package com.zot.xing.dao.employ;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zot.db.JDBCTemplate;
import com.zot.db.ResultSetHandler;

public class EmployASImpl implements EmployAS {
	private static final String QRY_ALL = "select * from t_zot_employ order by employ_id";
	
	@Override
	public List<EmployBO> queryAllEmploys() {
		JDBCTemplate<List<EmployBO>> jt = new JDBCTemplate<List<EmployBO>>();
		
		List<EmployBO> employs = jt.query(QRY_ALL, null, new ResultSetHandler<List<EmployBO>>()
				{
					@Override
					public List<EmployBO> rsHandler(ResultSet rs) throws SQLException {
						List<EmployBO> employs = new ArrayList<EmployBO>();
						EmployBO employ = null;
						
						while (rs.next())
						{
							employ = cvt2Employ(rs);

							employs.add(employ);
						}
						
						return employs;
					}
				});
		
		return employs;
	}
	
	private EmployBO cvt2Employ(ResultSet rs) throws SQLException
	{
		EmployBO employ = new EmployBO();
		employ.setEmployId(rs.getString("employ_id"));
		employ.setEmployName(rs.getString("employ_name"));
		employ.setEmployType(rs.getString("employ_type"));
		employ.setPhoneno(rs.getString("phoneno"));
		employ.setSuperEmployId(rs.getString("super_employ_id"));
		
		return employ;
	}
	
}
