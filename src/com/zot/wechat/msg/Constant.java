/**
 * 
 */
package com.zot.wechat.msg;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * 
 */
public class Constant {

	public static final String sToken = "WA2nrTIU0F2qf";

	public static final String sEncodingAESKey = "AImBBgSvuUz0uUDPIRvErMuP0PceSgsmyTWRl1qmrjk";
	
	//测试："wx72cde40929f41ff5";//正式：wx23dd85b2877f9d5d
	public static final String sCorpID = "wx23dd85b2877f9d5d";
	
	public static final String SERVER_URL = "http://115.159.102.168/jzot/";
	
	//测试：03564e55d65e276a013d37faa247f2eb，正式：bdc54a7802b445aca0c16b72bc64d066
	public static final String sCorpSecret = "bdc54a7802b445aca0c16b72bc64d066";
	
	public static final String sAppID = "1";
	
	/**
	 * wechat消息类型
	 */
	
	public static final String WECHAT_MSG_TEXT_TYPE = "text";
	
	public static final String WECHAT_MSG_EVENT_TYPE = "event";
	
	public static final String WECHAT_MSG_IMAGE_TYPE = "image";
	
	public static final String WECHAT_MSG_VOICE_TYPE = "voice";
	
	public static final String WECHAT_MSG_VIDEO_TYPE = "video";
	
	public static final String WECHAT_MSG_SHORTVIDEO_TYPE = "shortvideo";
	
	public static final String WECHAT_MSG_LOCATION_TYPE = "location";
	
	public static final String WECHAT_MSG_NEW_TYPE = "news";
	
	/**
	 * 订单状态
	 */
	public static final String ORDER_ALL = "-1";
	
	public static final String ORDER_CREATE = "0";
	
	public static final String ORDER_WAITING = "1";
	
	public static final String ORDER_SERVING = "2";
	
	public static final String ORDER_FINISHED = "3";
	
	public static final String ORDER_EVAING = "4";
	
	public static final String ORDER_CLOSED = "5";
	
	public static final String ORDER_CANCEL = "6";
	
	/**
	 * ID类型
	 */
	public static final int ID_WECHATNO = 0;
	
	public static final int ID_CARNO = 2;
	
	public static final int ID_PHONENO = 1;
	
	public static final Map<String, String> STATUS_MAP = new HashMap<String, String>(){
		private static final long serialVersionUID = 1L;
		{
			put(ORDER_WAITING, "排队");
			put(ORDER_SERVING, "服务");
			put(ORDER_EVAING, "评价");
			put(ORDER_CLOSED, "关闭");
			put(ORDER_CANCEL, "已取消");
		}
	};
	
	public static final String ARRIVED_YES = "Y";
	public static final String ARRIVED_NO = "N";
	
	//OAUTH获取用户信息相关URL
	public static final String MENU_BOOKING = "10001";
	public static final String MENU_ORDERS = "10002";
	public static final String OAUTH2_BASE_URL_PRE = "https://open.weixin.qq.com/connect/oauth2/authorize?" 
													+ "appid=" + sCorpID;
	public static final String OAUTH2_BASE_URL_SUF = "&response_type=code&scope=snsapi_base&state=jzot123"
													+ "#wechat_redirect";
	public static final Map<String, String> AUTH_MENU_MAP = new HashMap<String, String>(){
		private static final long serialVersionUID = 1L;
		{
			put(MENU_BOOKING, OAUTH2_BASE_URL_PRE 
							+ "&redirect_uri=http%3a%2f%2fwww.1hche.com%2fjzot%2fbooking.jsp"
							+ OAUTH2_BASE_URL_SUF);
			put(MENU_ORDERS, OAUTH2_BASE_URL_PRE  
							+ "&redirect_uri=http%3a%2f%2fwww.1hche.com%2fjzot%2forders.jsp"
							+ OAUTH2_BASE_URL_SUF);
		}
	};
	
	/**
	 * Action请求返回码
	 */
	public static final String SUCC = "S";
	public static final String FAIL = "F";
	
	/**
	 * 支付方式
	 */
	public static final String PAY_CASH = "C";
	public static final String PAY_WECHAT = "W";
	public static final String PAY_ALIPAY = "A";
	public static final String PAY_POS = "P";
	public static final String PAY_MEM_CARD = "M";
	
	public static final Map<String, String> PAY_TYPE_MAP = new HashMap<String, String>(){
		private static final long serialVersionUID = 1L;
		{
			put(PAY_CASH, "现金");
			put(PAY_WECHAT, "微信");
			put(PAY_ALIPAY, "支付宝");
			put(PAY_POS, "POS机");
			put(PAY_MEM_CARD, "卡扣");
		}
	};
}