package com.zot.xing.dao.subscribe;

import java.util.List;

public interface CarAS {
	void addCar(CarBO carBo);
	
	CarBO queryCarByCarno(String carno);
	
	List<CarBO> queryCarByCustId(String custId);
}
