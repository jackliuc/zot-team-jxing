package com.zot.xing.dao.subscribe;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zot.db.JDBCTemplate;
import com.zot.db.ResultSetHandler;
import com.zot.util.DateAS;

public class CarASImpl implements CarAS {

	private static final String ADD_CAR = 
			"insert into t_zot_car(cust_id, carno, create_time, brand, cartype, color, remark) "
			+ "values (?, ?, ?, ?,?, ?, ?)";
			
	private static final String QRY_CAR_BY_CARNO = 
			"select * from t_zot_car where carno = ?";
	
	private static final String QRY_CAR_BY_CUSTID = 
			"select * from t_zot_car where cust_id = ? order by create_time desc";
	
	@Override
	public void addCar(CarBO carBo) {
		JDBCTemplate<Object> jt = new JDBCTemplate<Object>();
		
		List<Object> params = new ArrayList<Object>();
		params.add(carBo.getCustId());
		params.add(carBo.getCarno());
		params.add(DateAS.getSQLTimestamp(carBo.getCreateTime()));
		params.add(carBo.getBrand());
		params.add(carBo.getCarType());
		params.add(carBo.getColor());
		params.add(carBo.getRemark());
		
		jt.execute(ADD_CAR, params);
	}

	@Override
	public CarBO queryCarByCarno(String carno) {
		JDBCTemplate<CarBO> jt = new JDBCTemplate<CarBO>();
		
		List<Object> params = new ArrayList<Object>();
		params.add(carno);
		
		CarBO car = jt.query(QRY_CAR_BY_CARNO, params, new ResultSetHandler<CarBO>()
				{
					@Override
					public CarBO rsHandler(ResultSet rs) throws SQLException {
						if (rs.next())
						{
							return cvt2CarBO(rs);
						}
						return null;
					}
					
				});
				
		return car;
	}

	@Override
	public List<CarBO> queryCarByCustId(String custId) {
		JDBCTemplate<List<CarBO>> jt = new JDBCTemplate<List<CarBO>>();
		
		List<Object> params = new ArrayList<Object>();
		params.add(custId);
		
		List<CarBO> cars = jt.query(QRY_CAR_BY_CUSTID, params, new ResultSetHandler<List<CarBO>>()
				{
					@Override
					public List<CarBO> rsHandler(ResultSet rs) throws SQLException {
						List<CarBO> carBos = new ArrayList<CarBO>();
						while (rs.next())
						{
							carBos.add(cvt2CarBO(rs));
						}
						return carBos;
					}
					
				});
				
		return cars;
	}
	
	private CarBO cvt2CarBO(ResultSet rs) throws SQLException
	{
		CarBO carBo = new CarBO();
		carBo.setCustId(rs.getString("cust_id"));
		carBo.setCarno(rs.getString("carno"));
		carBo.setCreateTime(rs.getTimestamp("create_time"));
		carBo.setBrand(rs.getString("brand"));
		carBo.setCarType(rs.getString("cartype"));
		carBo.setColor(rs.getString("color"));
		carBo.setRemark(rs.getString("remark"));
		
		return carBo;
	}

}
