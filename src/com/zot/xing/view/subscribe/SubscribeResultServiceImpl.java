/**
 * 
 */
package com.zot.xing.view.subscribe;

import com.zot.view.contorler.PrefixService;

/**
 * @author jack
 *
 */
public class SubscribeResultServiceImpl extends PrefixService {
	
	private SubscribeVO vo = new SubscribeVO();
	
	@Override
	public String action() {
		String subtype = (String)getValue("subtype");
		vo.setSubType(subtype);
		vo.setSubTime(SubscribeService.computeServiceTime(subtype));
		
		
		return null; 
	}

}
