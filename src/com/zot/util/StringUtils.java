package com.zot.util;

public class StringUtils {
	public static boolean isEmpty(String str)
	{
		return (str == null || str.trim().length() == 0);
	}
}