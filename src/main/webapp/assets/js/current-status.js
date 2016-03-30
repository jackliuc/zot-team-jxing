;(function(){
	'use strict';
	
	var source   = $("#current-status-template").html();
	var $container = $('#current-status-container');
	var $content = $container.find('.current-status-content');
	var template = Handlebars.compile(source);
	var nextCall = call;
	
	call();
	
	function call() {
		$.get(zot.contextPath + '/api/current-status')
			.then(render)
//			.then(zot.util.delay(10 * 1000)) // delay 10s
//			.then(nextCall);	
	}
	
	function render(data) {
		var $mask = $container.find('.loading');
		var html = template({
			inQueue: data.inQueueCars,
			averageServiceTime: data.averageServiceTime,
			finished: data.finishedCars
		});
		$content.html(html);
		
		// Remove loading mask
		if ($mask.length) {
			$mask.remove();
		}
	}
})();

function initHeaderCallBack(result)
{
	result = new Object();
	result.waitCnt = 6;
	result.serviceBiz = 20;
	result.allServiceCnt = 3888;
	$("#waitCnt").html(result.waitCnt);
	$("#serviceBiz").html(result.serviceBiz);
	$("#allServiceCnt").html(result.allServiceCnt);

}

function initHeader()
{
	var data = new Object();
	data.serviceAction = "subscribeAction";
	data.subtype = '<%=request.getParameter("subtype")%>';
}

	initHeaderCallBack();