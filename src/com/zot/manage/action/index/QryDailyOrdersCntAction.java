package com.zot.manage.action.index;

import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;

import com.zot.view.contorler.PrefixService;
import com.zot.wechat.msg.Constant;
import com.zot.xing.view.service.DailyWorkOrderCntVO;
import com.zot.xing.view.subscribe.WorkOrderService;

public class QryDailyOrdersCntAction implements PrefixService {

	private static Logger log = Logger.getLogger(QryDailyOrdersCntAction.class);

	@Override
	public Object action(Map<String, String> context) {

		try
		{
			//当前仅查询当天的统计信息
			Date currentDay = new Date();
			//Date currentDay = DateAS.getDateFromString("2016-06-04", "yyyy-MM-dd");
			
			DailyWorkOrderCntVO dailyWorkOrderCntVO = WorkOrderService.qryDailyWorkOrderCnt(currentDay);
			return dailyWorkOrderCntVO;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
			
		return Constant.FAIL;
	}
}

