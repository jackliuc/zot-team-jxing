package com.zot.xing.dao.offer;

import java.util.List;

public interface OfferAS {
	void addOffer(OfferBO offer);
	
	List<OfferBO> queryOffers(OfferBO offerCond);
	
	void updateOffer(OfferBO offer);
}
