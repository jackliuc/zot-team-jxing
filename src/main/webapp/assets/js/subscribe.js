;$(function() {
	'use strict';
	
	var $serviceType = $('#service-type');
	var $subscribeForm = $('#subscribe-form');
	
	checkSubscription()
		.then(function() {
			// init styles for each inputs
			init();
			initEvents();
		});
	
   function fetchServices() {
	   var deferred = $.Deferred();
	   $.get(zot.contextPath + '/api/service')
		.then(function(data) {
			if (null != data) {
				var serviceOptionListTemplate = Handlebars.compile($('#service-option-list-template').html());
				var html = serviceOptionListTemplate({services: data});
				$('#service-type').html(html);
			} 
			deferred.resolve();
		});
	
	return deferred.promise();
   }
	
	function init() {
		
		fetchServices().then(function() {
			$serviceType.selected({
				placeholder: '服务类型'
			}).on('change', function() {
				$serviceType.parent('.am-form-group').removeClass('am-form-error');
			});
		});
		fetchCarNumber();
}
	
	function validate() {
		if (!$serviceType.val()) {
			return false;
		} else if (!findCarNumber()) {
			return false;
		} 
		return true;
	}
	
	function jumpToInfoPage() {
		location.href = zot.contextPath + '/subscribe/subscribe-info.jsp';
	}
	
	function checkSubscription() {
		var deferred = $.Deferred();
		
		$.get(zot.contextPath + '/api/order/latest')
			.then(function(data) {
				// TODO: ...
				deferred.resolve();
				if (null != data) {
					// popup subscription information
//					$('#has-subscribed-popup').modal({});
					jumpToInfoPage();
				} else {
					deferred.resolve();
				}
			});
		
		return deferred.promise();
	}
	
	function fetchCarNumber() {
		var deferred = $.Deferred();
		$.get(zot.contextPath + '/api/customer/cars')
			.then(function(data) {
				var $group = $('#select-car-group'); 
				var carOptionListTemplate = Handlebars.compile($('#car-option-list-template').html());
				var html = carOptionListTemplate({
					firstCar: data && data[0],
					lt3: data.length < 3,
					cars: data && data.slice(1)
					});
				
				$group.html(html);
				$group.find('input[type=radio]').uCheck();
				if (data.length) {
					$subscribeForm.find('button[type=submit]').removeAttr('disabled');
				}
				
				$group.on('change', 'input[type="radio"]', function() {
					if (!$group.find('input.other-car').length) {
						return;
					}
					
					var isOtherCar = $group.find('input.other-car')[0].checked;
					if (isOtherCar) {
						$group.find('.other-car-input').show();
					} else {
						$group.find('.other-car-input').hide();
					}
				});
			});
		
		return deferred.promise();
	}
	
	function findCarNumber() {
		var $carGroup = $('#select-car-group'); 
		var $selectedCar = $carGroup.find('input[type="radio"]:checked');
		var $carInput = $carGroup.find('input.car-input');
		var car = $selectedCar.val();
		
		if ($carInput.length) {
			car = $carInput.val();
		} else if ($selectedCar.is('.other-car')) {
			car = $carGroup.find('.other-car-input').val();
		}
		if (car.length === 7) {
			return car;			
		} else {
			return null;
		}
}
	
	function subscribeSuccessfully() {
		// if subscribe successfully, go to subscription page
		jumpToInfoPage();
	}
	
	function subscribeFailed() {
		
	}
	
	function confirmSubmit(carNumber) {
		var deferred = $.Deferred();
		// popup confirm dialog
		var subscribeInfoTemplate = Handlebars.compile($('#subscribe-info-template').html());
		var html = subscribeInfoTemplate({
			carNumber: carNumber,
			serviceType: $('#service-type').find(':selected').html()
		});
		
		$('#confirm-submit').find(".am-modal-bd").html(html);
		$('#confirm-submit').modal({
			onConfirm: function() {
				deferred.resolve(carNumber);
			}
		});
		return deferred.promise();
	}
	
	function submit(carNumber) {
		var serviceType = $serviceType.val();
		$.post(zot.contextPath + '/api/order/create', {
			serviceType: serviceType,
			carNumber: carNumber
		}).then(subscribeSuccessfully, subscribeFailed);
	}
	
	function onSubmit(jEvent) {
		jEvent.preventDefault();
		jEvent.stopPropagation();
		
		checkSubscription()
			.then(findCarNumber)
			.then(confirmSubmit)
			.then(submit);
	}
	
	function initEvents() {
		$subscribeForm.submit(onSubmit);
		
		$subscribeForm.on('change input', ':input', function(evt) {
			if (validate()) {
				$subscribeForm.find('button[type=submit]').removeAttr('disabled');
			} else {
				$subscribeForm.find('button[type=submit]').attr('disabled', 'disabled');
			}
		});
	}
});