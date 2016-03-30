package com.zot.wechat.util;

public class JsSDKConfig {
	private String  appId;
    private String  timestamp;
    private String  nonceStr;
    private String  signature;
        
    public String getTimestamp() {
        return timestamp;
    }
 
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
 
    public String getNonceStr() {
        return nonceStr;
    }
 
    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }
 
    public String getSignature() {
        return signature;
    }
 
    public void setSignature(String signature) {
        this.signature = signature;
    }
    
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	@Override
    public String toString() {
        return "appId：" + this.appId + "|timestamp：" + this.timestamp + "|nonceStr：" 
        		+ this.nonceStr + "|signature：" + this.signature;
    }
 
}