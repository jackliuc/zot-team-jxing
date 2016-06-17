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
	
	private String errMsg = null;

	public ZOTException() {
		super();
	}
	
	public ZOTException(String message)
	{
		this.errMsg = message;
	}

	public ZOTException(Throwable e) {
		super(e);
	}
	
	public ZOTException(Throwable e, String message) {
		super(e);
		this.errMsg = message;
	}
	
	public String getMessage()
	{
		if (this.errMsg == null)
		{
			return super.getMessage();
		}
		
		return this.errMsg;
	}
}
