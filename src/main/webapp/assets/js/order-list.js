;$(function() {
	var serviceMap = {
			'10001': '洗车',
			'10002': '做漆',
			'10003': '钣金',
			'10006': '装潢'		
	};
	
	$.get(zot.contextPath + '/api/order')
		.then(function(data) {
			var serviceInfoTemplate = Handlebars.compile($('#order-list-template').html());
			var html = '您还没有预约过任何服务。';
			if (data) {
				html = serviceInfoTemplate({ orders: data.map(function(order){
					order.serviceType = serviceMap[order.serviceType];
					return order;
				})});
			}
			
			$('#order-list').html(html);
		});
});