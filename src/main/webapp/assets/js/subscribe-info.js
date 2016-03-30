;$(function() {
	var serviceMap = {
			'10001': '洗车',
			'10002': '做漆',
			'10003': '钣金',
			'10006': '装潢'		
	};
	
	$.get(zot.contextPath + '/api/order/T20160331003/2')
		.then(function(data) {
			var subscriptionInfoTemplate = Handlebars.compile($('#subscription-info-template').html());
			var html = '您还没有预约任何服务。';
			if (data) {
				html = subscriptionInfoTemplate({
					serviceType: serviceMap[data.serviceType],
					carNumber: data.carNumber,
					serviceNumber: data.serviceNumber,
					bestArrivalTime: data.bestArrivalTime
				});
			}
			
			var holder = $('#subscription-info').html(html);
			if (data) {
				// render qcode
				jQuery('#qcode-placeholder').qrcode({width:150,height:150,correctLevel:0,text:data.serviceNumber});  
			}
		});
});