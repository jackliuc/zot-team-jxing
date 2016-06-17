<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html class="no-js">
<%@include file="/assets/header.jsp" %>
<body>
	<hr data-am-widget="divider" style="" class="am-divider am-divider-default"/>
	<div class="admin-content">
	    <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">
              	服务类型
            </div>
            <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
               <select id="orderTypeSelect">
				  <option value="10001" selected>快洗</option>
				  <option value="10002">精洗</option>
				  <option value="10003">美容</option>
				  <option value="10006">油漆</option>
				</select>
            </div>
        </div>
        
        <div id="bookinfo" class="am-g am-margin-top">
        	<div class="am-g am-margin-top">
        		<div class="am-u-sm-4 am-u-md-2 am-text-right">
	              	<span id="bookName"></span> 
	            </div>
	        </div>
	        <hr data-am-widget="divider" style="" class="am-divider am-divider-default"/>
	        <div class="am-g am-margin-top">
	            <div class="am-u-sm-4 am-u-md-2 am-text-right">
	              	排队数
	            </div>
	            <div id="waitingNum" class="am-u-sm-8 am-u-md-4 am-u-end col-end">
	               <span id="waitingNumC"></span> 
	            </div>
	        </div>
	        <div class="am-g am-margin-top">
	            <div class="am-u-sm-4 am-u-md-2 am-text-right">
	              	建议时间
	            </div>
	            <div id="suggestTime" class="am-u-sm-8 am-u-md-4 am-u-end col-end">
	               <span id="suggestTimeC"></span> 
	            </div>
	        </div>
        </div>
	    
	    <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">
            	预约时间
            </div>
             <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
              <input id="ordertime" type="datetime-local"/>
            </div>
          </div>
          
         <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">
            	车牌号
            </div>
             <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
              <input id="carno" type="text" maxlength="7" value="苏A" required/>
            </div>
          </div>
	   
		 <div class="am-g am-margin-top">
				<button id="confirmButton" class="am-btn-block">确认</button>
		 </div>

    </div>
<div id="resultShow" class="am-u-sm-12  am-text-center" style="display:none">
                                                        预约成功，请凭借微信条形码到店服务
</div>
<div id="resultCode" class="am-u-sm-12  am-text-center" style="display:none">

</div>

<%@include file="/assets/footer.jsp" %>

<script type="text/javascript">

var code = "<%=request.getParameter("code")%>";

function resultCallBack(result)
{
	var v_qrcode = result.orderId ? result.orderId : 0;
	if (v_qrcode != 0)
	{
		$("#resultCode").qrcode({
			width:128,
			height:128,
			correctLevel:0,
			text:v_qrcode
		});
		$("#resultShow").show();
		$("#resultCode").show();
	}
	else
	{
		alert("系统异常，请稍后重试");
	}
}

function bookingInfoBack(bookinfo)
{
	$("#bookName").html(bookinfo.bookName+"排队信息");
	$("#waitingNumC").html(bookinfo.waitingNum);
	$("#suggestTimeC").html(bookinfo.disSuggestTime);
}

function getBookingInfo(orderType)
{
	var dataD = new Object();
	
 	dataD.serviceAction = "orderTypeSelectAction";
 	dataD.code = code;
 	dataD.orderType = orderType;
	
	$.zot.post(dataD,bookingInfoBack);
}

function getCarnoBack(carno)
{
	if (carno && carno.length == 7)
	{
		$("#carno").val(carno);	
	}
}

function getCarno()
{
	var dataD = new Object();
	
 	dataD.serviceAction = "queryCarnoAction";
 	dataD.code = code;
	
	$.zot.post(dataD,getCarnoBack);
}

$("#confirmButton").click(function(){
	$("#resultShow").hide();
	$("#resultCode").hide();
	
	var dataD = new Object();
 	
 	var orderTime = $("#ordertime").val();
 	if (!orderTime)
 	{
 		alert("请选择预约时间");
 		return;
 	}
 	
 	var carno = $("#carno").val();
 	if (!carno || carno.length != 7)
 	{
 		alert("请输入7位车牌号");
 		return;
 	}
		
 	dataD.serviceAction = "subscribeAction";
 	dataD.code = code;
 	dataD.orderType = $("#orderTypeSelect").val();
 	dataD.orderTime = orderTime;
 	dataD.carno = carno;
	
	$.zot.post(dataD,resultCallBack);	
});

$("#orderTypeSelect").change(function(){
	var orderType = $("#orderTypeSelect").val();
	getBookingInfo(orderType);
});

var orderType = $("#orderTypeSelect").val();
getBookingInfo(orderType);
getCarno();

</script>
</body>
</html>