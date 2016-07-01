<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>我的车辆</title>
<meta charset="UTF-8" />
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
    <div class="nav-top" style="position: inherit;">
        <a href="#" class="nav-btn" id="navBtn"><i class="icon iconfont">&#xe611;</i></a>
        <a href="index.html" class="nav-logo">壹号车</a>
        <a href="#" class="nav-right"><i class="icon iconfont">&#xe63e;</i></a>    
    </div>
</header>

<% 
	String orderId = request.getParameter("orderId");
%>

<input type="hidden" id="evalType" value="" />
<input type="hidden" id="orderId" value="<%=orderId%>">

<div class="am-g">
    <div><span class="am-text-primary am-text-default">我的车辆</span></div>
</div>
<div class="am-g">
    <div class="am-u-sm-1"></div>
  	<div id="list_cars_div" class="am-u-sm-11">
  		<span class="am-text-default am-text-sm">亲，没有车辆噢，请新增</span>
    </div>
</div> 

<p/>

<div class="am-g">
    <div><span class="am-text-primary am-text-default">新增车辆</span></div>
</div>
<div class="am-g">
    <div class="am-u-sm-1"></div>
  	<div class="am-u-sm-11">
  		<input id="carno" class="input-xlarge" type="text" value="苏A"/>
    	<button type="button" id="add_car_btn" class="am-btn am-btn-primary am-btn-sm">确定</button>
    </div>
</div> 

<div class="am-modal am-modal-alert" tabindex="-1" id="alert-msg" style="display:none">
  <div class="am-modal-dialog">
    <div class="am-modal-bd">
    </div>
    <div class="am-modal-footer">
      <span id="cust_cars_ok_btn" class="am-modal-btn">确定</span>
    </div>
  </div>
</div>

<!--  
<footer class="footer">
  <a href="index.html" class="footer-nav-item">
<i class="icon iconfont">&#xe63e;</i>
<p>壹号车</p>
  </a>
  <a href="booking.jsp" class="footer-nav-lastitem">
<i class="icon iconfont">&#xe615;</i>
<p>在线预约</p>
  </a>
    <a href="#" class="footer-nav-item">
<i class="icon iconfont">&#xe600;</i>
<p>优惠活动</p>
  </a>
    <a href="orders.jsp" class="footer-nav-item">
<i class="icon iconfont">&#xe65b;</i>
<p>个人中心</p>
  </a>
</footer>
-->

<script type="text/javascript">
	var code = "<%=request.getParameter("code")%>";
	var userId = $.zot.getUserId(code);
	var custcarsMenuURL = $.zot.getAuthMenuURL("10003");
	
	$(document).ready(function(){
		var dataD = new Object();
		dataD.serviceAction = "qryCarsByWechatnoAction";
	 	dataD.wechatno = userId;
	 	$.zot.post(dataD,function(cars){
	 		if (cars && cars.length > 0)
			{
	 			var arry = [];
	 			for(var p in cars)
				{
					arry.push("<button type=\"button\" class=\"am-btn am-btn-sm\">");
					arry.push(cars[p].carno);
					arry.push("</button>");
				}
	 			$("#list_cars_div").html(arry.join(""));
			}
		});
	});
	
	$("#add_car_btn").click(function(){
	 	var carno = $("#carno").val();
	 	if (!carno || carno.length != 7)
	 	{
	 		alertMsg("请输入7位车牌号");	
	 		return;
	 	}
	 	
	 	var dataD = new Object();
		dataD.serviceAction = "addCustCarnoAction";
		dataD.wechatno = userId;
	 	dataD.carno = carno;
		
	 	$.zot.post(dataD,function(ret){
	 		if (ret == 'F'){
	 			$("#cust_cars_ok_btn").click(function(){
	 			});
	 			alertMsg("新增失败，请稍后再试");
	 		}
	 		else{
	 			$("#cust_cars_ok_btn").click(function(){
	 				window.location.href = custcarsMenuURL;
	 			});
	 			alertMsg("新增车辆成功");
	 		}
	 		
	 		return false;
		});
	});
	
</script>

</body>
</html>