package com.zot.xing.dao.card;

import java.util.List;

public interface CardLogAS {
	void addCardLog(CardLogBO cardLog);
	
	List<CardLogBO> queryCardLogs(CardLogBO cardLogCond);
}
