package com.zot.manage.action.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.zot.view.contorler.PrefixService;
import com.zot.wechat.msg.Constant;
import com.zot.xing.dao.offer.OfferAS;
import com.zot.xing.dao.offer.OfferASImpl;
import com.zot.xing.dao.offer.OfferBO;
import com.zot.xing.dao.subscribe.ServiceBO;
import com.zot.xing.dao.subscribe.ServiceUtils;

public class QryOffersAction implements PrefixService {

	private static Logger log = Logger.getLogger(QryOffersAction.class);

	@Override
	public Object action(Map<String, String> context) {

		try
		{
			String data = context.get("data");
			OfferBO offerCond = JSONObject.parseObject(data, OfferBO.class);
			
			OfferAS offerAs = new OfferASImpl();
			List<OfferBO> offers = offerAs.queryOffers(offerCond);
			List<OfferVO> offerVos = getOfferVos(offers);
			
			return offerVos;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			log.error(ex.getMessage());
			return Constant.FAIL;
		}
	}
	
	/**
	 * 调整提成模式中固定金额和百分比，无论何种模式，都设置值到固定金额字段cmission_value
	 * 
	 * @param offer
	 */
	private List<OfferVO> getOfferVos(List<OfferBO> offers)
	{
		List<OfferVO> offerVos = new ArrayList<OfferVO>();
		if (offers != null && offers.size() > 0)
		{
			for (OfferBO offer : offers)
			{
				OfferVO offerVo = new OfferVO();
				offerVo.setOfferCode(offer.getOfferCode());
				offerVo.setOfferName(offer.getOfferName());
				offerVo.setServCatalog(offer.getServCatalog());
				offerVo.setServCatalogName(Constant.SERV_CATA_MAP.get(offer.getServCatalog()));
				offerVo.setServTypeCode(offer.getServTypeCode());
				ServiceBO service = ServiceUtils.queryServiceBySKey(offer.getServTypeCode());
				if (service != null)
				{
					offerVo.setServTypeCodeName(service.getService_name());
				}
				offerVo.setPrice(offer.getPrice());
				offerVo.setMinPrice(offer.getMinPrice());
				offerVo.setCmission_mode(offer.getCmission_mode());
				offerVo.setCmissionModeName(Constant.CMIS_MODE_MAP.get(String.valueOf(offer.getCmission_mode())));
				if (Constant.CMIS_PERCENT_MODE == offer.getCmission_mode())//百分比
				{
					offerVo.setCmission_value(String.valueOf(offer.getCmissin_percent() + "%"));
				}
				else
				{
					offerVo.setCmission_value(String.valueOf(offer.getCmission_amount()));
				}
				offerVos.add(offerVo);
			}
		}
		
		return offerVos;
	}
}
