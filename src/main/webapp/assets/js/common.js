;(function() {
	'use strict';
	var zot = window.zot = (window.zot || {});
	
	zot.util = {
			delay: function(delayTime) {
				var args = arguments;
				return function(data) {
					var deferred = $.Deferred();
					setTimeout(function() {
						deferred.resolve(args);
					}, delayTime);
					
					return deferred.promise();
				};
			}
	};
})();