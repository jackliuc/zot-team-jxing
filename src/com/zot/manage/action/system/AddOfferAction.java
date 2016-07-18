package com.zot.manage.action.system;

import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.zot.view.contorler.PrefixService;
import com.zot.wechat.msg.Constant;
import com.zot.xing.dao.offer.OfferAS;
import com.zot.xing.dao.offer.OfferASImpl;

public class AddOfferAction implements PrefixService {

	private static Logger log = Logger.getLogger(AddOfferAction.class);

	@Override
	public Object action(Map<String, String> context) {

		try
		{
			String data = context.get("data");
			OfferVO offer = JSONObject.parseObject(data, OfferVO.class);
			setOfferBO(offer);
			
			OfferAS offerAs = new OfferASImpl();
			offerAs.addOffer(offer);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			log.error(ex.getMessage());
			return Constant.FAIL;
		}
			
		return Constant.SUCC;
	}
	
	/**
	 * 界面传入时，无论什么提成模式，提成额都设置到了提成金额字段Cmission_value上，需要根据不同的模式调整
	 * 
	 * @param offer
	 */
	private void setOfferBO(OfferVO offer)
	{
		String amount = offer.getCmission_value();
		if (Constant.CMIS_AMOUNT_MODE == offer.getCmission_mode())//固定金额
		{
			offer.setCmission_amount(Float.parseFloat(amount));
			offer.setCmissin_percent(null);
		}
		else//按百分比
		{
			offer.setCmission_amount(null);
			offer.setCmissin_percent(Integer.parseInt(amount));
		}
	}
}


