package com.zot.xing.dao.common;

import java.util.List;

public interface CommonAS {
	List<IdAmount> qryIdAmounts(String sql);
	
	List<IdAmount> qryIdAmounts(String sql, List<Object> params);
}
