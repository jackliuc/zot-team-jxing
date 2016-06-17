/**
 * 
 */
package com.zot.xing.view.subscribe;

import java.util.Map;

import com.zot.util.DateAS;
import com.zot.view.contorler.PrefixService;

/**
 * @author jack
 *
 */
public class SubscribeResultAction implements PrefixService {
	
	private SubscribeVO vo = new SubscribeVO();
	
	public SubscribeVO action(Map<String,String> context) {
		String orderType = context.get("orderType");
		
		//ServiceBO service = ServiceUtils.queryServiceBySKey(orderType);
		vo.setOrderType(orderType);
		vo.setOrderTime(DateAS.getCurrentDateYHM());
		return vo; 
	}

}
