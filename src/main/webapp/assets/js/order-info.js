;$(function() {
	var serviceMap = {
			'10001': '洗车',
			'10002': '做漆',
			'10003': '钣金',
			'10006': '装潢'		
	};
	
	$.get(zot.contextPath + '/api/order/T20160331003/2')
		.then(function(data) {
			var serviceInfoTemplate = Handlebars.compile($('#service-info-template').html());
			var html = '您还没有预约过任何服务。';
			if (data) {
				html = serviceInfoTemplate({
					serviceType: serviceMap[data.serviceType],
					carNumber: data.carNumber,
					serviceNumber: data.serviceNumber,
					serviceDate: data.serviceDate,
					serviceTime: data.serviceTime
				});
			}
			
			var holder = $('#service-info').html(html);
		});
});