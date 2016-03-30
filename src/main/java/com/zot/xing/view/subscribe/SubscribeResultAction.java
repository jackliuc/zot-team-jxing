/**
 * 
 */
package com.zot.xing.view.subscribe;

import java.util.Map;

import com.zot.util.DateAS;
import com.zot.view.contoller.PrefixService;
import com.zot.xing.dao.subscribe.ServiceBO;
import com.zot.xing.dao.subscribe.ServiceUtils;

/**
 * @author jack
 *
 */
public class SubscribeResultAction implements PrefixService {
	
	private SubscribeVO vo = new SubscribeVO();
	
	public SubscribeVO action(Map<String,String> context) {
		String subtype = context.get("subtype");
		
		ServiceBO service = ServiceUtils.queryServiceByKey(subtype);
		vo.setSubType(subtype);
		vo.setSubTime(DateAS.getCurrentDateYHM());
		vo.setOverTime(DateAS.addMinsWithCurrentDate(service.getCost_time()));
		vo.setPrice(service.getPrice());
		return vo; 
	}

}
