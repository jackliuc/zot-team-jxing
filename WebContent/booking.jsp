<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>壹号车微官网首页</title>
<meta name="Description" content="壹号车壹号车壹号车壹号车壹号车壹号车壹号车壹号车"/>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<link rel="stylesheet" href="css/stely.css" type="text/css" />

<link rel="stylesheet" href="assets/css/amazeui.min.css">

<input type="hidden" id="contextPath" value="<%= request.getContextPath()%>" /> 
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/amazeui.min.js"></script>
<script src="assets/util/zot.js"></script>

</head>
<body>
      
<header class="nav" id="nav">
    <div class="nav-top">
        <a href="#" class="nav-btn" id="navBtn"><i class="icon iconfont">&#xe611;</i></a>
        <a href="index.html" class="nav-logo">壹号车</a>
        
        <a href="#" class="nav-right">
            <i class="icon iconfont">&#xe63e; </i>       </a>    </div>
</header>

<input type="hidden" id="orderType" value="" />
<input type="hidden" id="userId" value="" />
<div class="fenlei" id="orderTypeDiv">
  <a href="#" onclick="getBookingInfo(this)" id="10001">
	<div class="tu" style="background:url(assets/image/kx.jpg) no-repeat;background-size:100% 90px;position:relative">
		<div style="width:100%; height:33px; font-size:16px; color:#fff; text-align:center; 
		background:url(images/yuyue_12.png); margin-top:45px; line-height:30px;">快 洗</div>
		<i></i>
	</div>
  </a>
  <a href="#" onclick="getBookingInfo(this)" id="10002">
  	<div class="tu" style="background:url(assets/image/jx.jpg) no-repeat;background-size:100% 90px;position:relative">
  		<div style="width:100%; height:33px; font-size:16px; color:#fff; text-align:center; 
  		background:url(images/yuyue_12.png); margin-top:45px; line-height:30px;">精 洗</div>
  		<i></i>
  	</div>
  </a>

  <a href="#" onclick="getBookingInfo(this)" id="10003">
  	<div class="tu" style="background:url(assets/image/mr.jpg) no-repeat;background-size:100% 90px;position:relative">
		<div style="width:100%; height:33px; font-size:16px; color:#fff; text-align:center; 
		background:url(images/yuyue_12.png); margin-top:45px; line-height:30px;">美 容</div>
		<i></i>
	</div>
  </a>

  <a href="#" onclick="getBookingInfo(this)" id="10006">
  	<div class="tu" style="background:url(assets/image/yq.jpg) no-repeat;background-size:100% 90px;position:relative">
  		<div style="width:100%; height:33px; font-size:16px; color:#fff; text-align:center; 
  		background:url(images/yuyue_12.png); margin-top:45px; line-height:30px;">喷 漆</div>
  		<i></i>
  	</div>
  </a>
</div>

<!--预约信息-->
<div class="ziduan" id="detailDiv">
	<div class="title" id="bookName"></div>
	<ul>
		<li class="left">预约数</li>
		<li class="right" id="bookingNumC"></li>
		<li class="left">到店数</li>
		<li class="right" id="arrivedNumC"></li>
		<li class="left">建议预约时间</li>
		<li class="right" id="suggestTimeC"></li>
		<li class="left">车牌号</li>
		<li class="right"><input id="carno" class="co" type="text" maxlength="7" value="苏A" required/></li>
		<li class="left">手机号</li>
		<li class="right"><input id="phoneno" class="co" type="text" maxlength="11" value="" required/></li>
		<li>
			 <img src="./images/yuyue_23.png" style="width: 25px; margin-left: 10px"> 
			 <input id="ordertime" class="yy" type="datetime-local"/>
		</li>
	</ul>
</div>

<div class="button" id="confirmButton">预约</div>
<div id="resultCode" style=" width:50%; margin:25px auto auto 25%; display:none"></div>
<div id="resultShow" class="prompt" style="display:none">预约成功，请凭二维码到店服务</div>

<div class="am-modal am-modal-alert" tabindex="-1" id="alert-msg" style="display:none">
  <div class="am-modal-dialog">
    <div class="am-modal-bd">
    </div>
    <div class="am-modal-footer">
      <span class="am-modal-btn">确定</span>
    </div>
  </div>
</div>


<script type="text/javascript">

var code = "<%=request.getParameter("code")%>";
var userId = $.zot.getUserId(code);

function resultCallBack(result)
{
	var v_qrcode = (result && result.orderId) ? result.orderId : 0;
	if (v_qrcode != 0)
	{
		$("#resultCode").html("");
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
		alertMsg("系统异常，请稍后重试");
	}
}

function getBookingInfo(aobj)
{
	var orderType = aobj.id;
	$("#orderType").val(orderType);
	$(".fenlei .tu").each(function(){
		var d=$(this);
		d.find("i").css("display","none");
	})
	$(aobj).find("div>i").css("display","block");
	
	var dataD = new Object();
	
 	dataD.serviceAction = "orderTypeSelectAction";
 	dataD.userId = userId;
 	dataD.orderType = orderType;
	
	$.zot.post(dataD,function(ret){
		$("#bookName").text(ret.bookName);
		$("#bookingNumC").text(ret.bookingNum);
		$("#arrivedNumC").text(ret.arrivedNum);
		$("#suggestTimeC").text(ret.disSuggestTime);
	});
}

function getSubscribeInfo()
{
	var dataD = new Object();
	
 	dataD.serviceAction = "qrySubscribeInfoAction";
 	dataD.userId = userId;
	
	$.zot.post(dataD,function(ret){
		if (ret.carno && ret.carno.length == 7)
		{
			$("#carno").val(ret.carno);	
		}
		if (ret.phoneno && ret.phoneno.length == 11)
		{
			$("#phoneno").val(ret.phoneno);	
		}
	});
}

$("#confirmButton").click(function(){
	$("#resultShow").hide();
	$("#resultCode").hide();
	
	var orderType = $("#orderType").val();
 	if (!orderType)
 	{
 		alertMsg("请预约服务");
 		return;
 	}
	
 	var orderTime = $("#ordertime").val();
 	if (!orderTime)
 	{
 		alertMsg("请选择预约时间");
 		return;
 	}
 	
 	var carno = $("#carno").val();
 	if (!carno || carno.length != 7)
 	{
 		alertMsg("请输入7位车牌号");
 		return;
 	}
 	
 	var phoneno = $("#phoneno").val();
 	if (!phoneno || phoneno.length != 11)
 	{
 		alertMsg("请输入11位手机号码");
 		return;
 	}
		
 	var dataD = {};
 	dataD.wechatno = userId;
 	dataD.orderType = orderType;
 	dataD.orderTime = orderTime;
 	dataD.carno = carno;
 	dataD.phoneno = phoneno;
 	
 	var serviceD = new Object();
 	serviceD.data = JSON.stringify(dataD);
	
 	//检查预约的合法性
 	serviceD.serviceAction = "checkSubscribeAction";
 	$.zot.post(serviceD,function(ret){
 		if (ret == 'S')
 		{
 		 	//提交预约
 		 	serviceD.serviceAction = "subscribeAction";
 			$.zot.post(serviceD,resultCallBack);
 		}
 		else
 		{
 			alertMsg("你已预约，请勿重复预约");
 		}
 	});
});

getSubscribeInfo();

</script>

</body>
</html>