package com.zot.manage.service.card;

import java.util.Date;

import com.zot.util.IdGen;
import com.zot.wechat.msg.Constant;
import com.zot.xing.dao.card.CardAS;
import com.zot.xing.dao.card.CardASImpl;
import com.zot.xing.dao.card.CardBO;
import com.zot.xing.dao.card.CardLogAS;
import com.zot.xing.dao.card.CardLogASImpl;
import com.zot.xing.dao.card.CardLogBO;

public class CardService 
{
	public static void addCard(CardBO card)
	{
		//新增卡，同时自动创建卡充值记录
		//TODO:需要先判断，对应的card对象中的classCode是否存在
		CardAS cardAS = new CardASImpl();
		cardAS.addCard(card);
		
		CardLogBO cardLog = getCardLog(card);
		CardLogAS cardLogAS = new CardLogASImpl();
		cardLogAS.addCardLog(cardLog);
	}
	
	private static CardLogBO getCardLog(CardBO card)
	{
		CardLogBO cardLog = new CardLogBO();
		cardLog.setId(IdGen.genCardLogId());
		cardLog.setCardNo(card.getCardNo());
		cardLog.setCustId(card.getCustId());
		cardLog.setOperId("sysadmin");//TODO:需要从上下文中获取
		
		// 新增卡的充值记录是从0变成充值的金额，即卡上的余额
		cardLog.setAmount(card.getBalance());
		cardLog.setBeforeAmt(Float.valueOf(0));
		cardLog.setAfterAmt(card.getBalance());
		cardLog.setCreateTime(new Date());
		cardLog.setLogType(Constant.RECHARGE_LOG);
		
		return cardLog;
	}
}
