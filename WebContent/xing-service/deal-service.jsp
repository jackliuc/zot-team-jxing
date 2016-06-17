<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<html class="no-js">
<%@include file="/assets/header.jsp" %>

<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/wxconfig.js"></script>

<body>
  <div class="admin-content">
		<hr data-am-widget="divider" style="" class="am-divider am-divider-default"/>
		
		<div class="am-g am-margin-top">
			<input type="button" class="am-btn am-btn-secondary btn-service-scan" value="二维码扫描" 
				data-am-loading="{loadingText: '努力扫描中...'}" />
		</div>
	
		<div class="am-g am-margin-top">
	      <label>订单号：</label>
	      <label id="scanResult" class="am-text-danger"></label>
	    </div>
	
		<div class="am-g am-margin-top">
	      <label for="doc-vld-carno">车牌号：</label>
	      <label id="doc-vld-carno"></label>
	    </div>
	    
	    <div class="am-g am-margin-top">
	    	<input type="button" class="am-btn am-btn-secondary btn-service-go" value="开始服务" />
	    </div>
	    
	    <div class="am-g am-margin-top">	      
	      <label id="goResult"></label>
	    </div>
	</div>
		
	<%@include file="/assets/footer.jsp" %>

	<script>	
		var code = "<%=request.getParameter("code")%>";
		
		function getCarnoByOrderIdBack(carno)
		{
			if (carno && carno.length == 7)
			{
				$("#doc-vld-carno").text(carno);	
			}
		}

		function getCarnoByOrderId(orderId)
		{
			var dataD = new Object();
			
		 	dataD.serviceAction = "queryWorkOrderAction";
		 	dataD.code = code;
		 	dataD.orderId = orderId;
			
			$.zot.post(dataD,getCarnoByOrderIdBack);
		}
		
		$('.btn-service-scan').click(function (){			
		  $('#scanResult').text("");
		  if (!jsconfigReady)
		  {
			  alert("waiting for init......");
			  return;
		  }
			
		  var $btn = $(this)
		  $btn.button('loading');

		  wx.scanQRCode({
			    needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
			    scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有		    
			    success: function (res) {
			    	var v_qrcode = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
			    	$('#scanResult').text(v_qrcode);
			    	getCarnoByOrderId(v_qrcode);//显示车牌号
				},			
				fail: function (res) {//接口调用失败时执行的回调函数		    	
					$('#scanResult').text('scanQRCode failed.');
				},			
				complete: function (res) {//接口调用完成时执行的回调函数，无论成功或失败都会执行。		    						
				},			
				cancel: function (res) {//用户点击取消时的回调函数，仅部分有用户取消操作的api才会用到。		    	
					$('#scanResult').text('user canceled.');
				}			
			});
		  
		  setTimeout(function(){
		      $btn.button('reset');
		  }, 3000);
		});	
		
		$('.btn-service-go').click(function (){					  
		  var $btn = $(this)
		  
		  var carno = $('#doc-vld-carno').text();
		  if (!carno)
		  {
			  alert("请先扫描二维码，获取车牌号");  
			  return;
		  }
		  
		  var orderId = $('#scanResult').text();
		  
		  //服务受理		  
		  var dataD = new Object();		
		  dataD.serviceAction = "serviceDealAction";
		  dataD.code = code;
		  dataD.carNo = carno;
		  dataD.orderId = orderId;
		  $.zot.post(dataD,resultCallBack);
		  
		});
		
		function resultCallBack(result)
		{
			var message = "服务人员:" + result.servicePerson
						+ "<br>受理时间:" + result.disServiceTime
			$('#goResult').html('受理成功。<br>' + message);
		}
	</script>

</body>
</html>