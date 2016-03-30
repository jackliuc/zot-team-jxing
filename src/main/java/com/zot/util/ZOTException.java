/**
 * 
 */
package com.zot.util;

/**
 * @author jack
 * 
 */
public class ZOTException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ZOTException() {
		super();
	}
	
	public ZOTException(String message)
	{
		super(message);
	}

	public ZOTException(Throwable e) {
		super(e);
	}
}
