var dataD = new Object();		
dataD.serviceAction = "jsSDKConfigAction";
dataD.targetUrl = window.location.href;	
$.zot.post(dataD,resultCallBack);

var jsconfigReady = false;
function resultCallBack(jsConfig)
{
	wx.config({
		    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
		    appId: jsConfig.appId, // 必填，公众号的唯一标识
		    timestamp: jsConfig.timestamp, // 必填，生成签名的时间戳
		    nonceStr: jsConfig.nonceStr, // 必填，生成签名的随机串
		    signature: jsConfig.signature,// 必填，签名，见附录1
		    jsApiList: [ // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
		                'checkJsApi',    
		     			'onMenuShareTimeline',    
		     			'onMenuShareAppMessage',    
		     			'onMenuShareQQ',    
		     			'onMenuShareWeibo',    
		     			'hideMenuItems',    
		     			'showMenuItems',    
		     			'hideAllNonBaseMenuItem',    
		     			'showAllNonBaseMenuItem',    
		     			'translateVoice',    
		     			'startRecord',    
		     			'stopRecord',    
		     			'onRecordEnd',    
		     			'playVoice',    
		     			'pauseVoice',    
		     			'stopVoice',    
		     			'uploadVoice',    
		     			'downloadVoice',    
		     			'chooseImage',    
		     			'previewImage',    
		     			'uploadImage',    
		     			'downloadImage',    
		     			'getNetworkType',    
		     			'openLocation',    
		     			'getLocation',    
		     			'hideOptionMenu',    
		     			'showOptionMenu',    
		     			'closeWindow',    
		     			'scanQRCode',    
		     			'chooseWXPay',    
		     			'openProductSpecificView',    
		     			'addCard',    
		     			'chooseCard',    
		     			'openCard'    
		    ] 
		});

	wx.ready(function(){
		//alert('js config ready.')
		jsconfigReady = true;
	    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
	});

	wx.error(function(res){
		// alert('js config error.');	
	    // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
	});
}

