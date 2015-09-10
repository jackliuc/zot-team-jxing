<%@page import="com.zot.xing.view.service.ServiceMgrService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<html class="no-js">
<%@include file="/assets/header.jsp" %>

<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/wxconfig.js"></script>

<body>
  <div class="admin-content">
    <ul class="am-avg-sm-3 am-margin am-padding am-text-center admin-content-list ">
      <li><a href="#" class="am-text-success"><span class="am-icon-btn am-icon-file-text"></span><br/>排队数<br/>13</a></li>
      <li><a href="#" class="am-text-warning"><span class="am-icon-btn am-icon-briefcase"></span><br/>出车速度<br/>15分钟</a></li>
      <li><a href="#" class="am-text-danger"><span class="am-icon-btn am-icon-recycle"></span><br/>已服务数<br/>80082</a></li>
    </ul>
	<hr data-am-widget="divider" style="" class="am-divider am-divider-default"/>
		
	<input type="button" class="am-btn am-btn-secondary btn-service-scan" value="服务扫描" 
	data-am-loading="{loadingText: '努力扫描中...'}" />
		
	</div>
	
	<p>扫描订单号：<span id="scanResult" class="am-text-danger"></span></p>
	
	<%@include file="/assets/footer.jsp" %>

	<script>
		$('.btn-service-scan').click(function () {
		  var $btn = $(this)
		  $btn.button('loading');
		  
		  $('#scanResult').text('ddddd123');
		  wx.scanQRCode();
		  
		  setTimeout(function(){
		      $btn.button('reset');
		  }, 5000);
		});	
		
		wx.scanQRCode({
		    needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
		    scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有		    
		    success: function (res) {
		    var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
		    $('#scanResult').text(result);
			},			
			fail: function (res) {//接口调用失败时执行的回调函数		    	
		    	$('#scanResult').text('failed.');
			},			
			complete: function (res) {//接口调用完成时执行的回调函数，无论成功或失败都会执行。		    	
		    	$('#scanResult').text('complete.');
			},			
			cancel: function (res) {//用户点击取消时的回调函数，仅部分有用户取消操作的api才会用到。		    	
		    	$('#scanResult').text('user canceled.');
			}			
		});
	</script>

</body>
</html>