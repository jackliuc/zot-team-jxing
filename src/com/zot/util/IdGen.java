package com.zot.util;

import java.util.UUID;

public class IdGen {
	private static final String ORDER_ID_PRE = "D";
	private static final String CUST_ID_PRE = "C_";
	private static final String COST_ID_PRE = "P_";
	private static final String CARD_LOG_ID_PRE = "CL_";
	private static final String CARD_ID_PRE = "E";
	
	public static String genOrderId()
	{
		return ORDER_ID_PRE + System.currentTimeMillis();
	}
	
	public static String genCardId()
	{
		return CARD_ID_PRE + System.currentTimeMillis();
	}
	
	public static String genCustId()
	{
		return CUST_ID_PRE + UUID.randomUUID().toString();
	}
	
	public static String genCostId()
	{
		return COST_ID_PRE + System.currentTimeMillis();
	}
	
	public static String genCardLogId()
	{
		return CARD_LOG_ID_PRE + UUID.randomUUID().toString();
	}
}
