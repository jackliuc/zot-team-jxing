package com.zot.xing.dao.card;

import java.util.List;

public interface CardAS {
	void addCard(CardBO card);
	
	List<CardBO> queryCards(CardBO cardCond);
}
