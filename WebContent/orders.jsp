<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>我的订单</title>
<meta charset="UTF-8" />
<meta name="Description" content="壹号车壹号车壹号车壹号车壹号车壹号车壹号车壹号车"/>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<link rel="stylesheet" href="css/stely.css" type="text/css" />
 
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
           <i class="icon iconfont">&#xe63e;</i></a>
    </div>
</header>

<div class="h44"> 
</div> 

<div class="am-modal am-modal-alert" tabindex="-1" id="alert-msg" style="display:none">
  <div class="am-modal-dialog">
    <div class="am-modal-bd">
    </div>
    <div class="am-modal-footer">
      <span class="am-modal-btn">确定</span>
    </div>
  </div>
</div>


<input type="hidden" id="userId" value="" />
 
<script type="text/javascript">
	var ordersMenuURL = $.zot.getAuthMenuURL("10002");
	var code = "<%=request.getParameter("code")%>";
	var userId = $.zot.getUserId(code);
	var dataD = new Object();

	dataD.serviceAction = "serviceQueryAction";
	dataD.userId = userId;
	$.zot.post(dataD,dealOrders);	

	function dealOrders(orders)
	{
		var arry = [];
		if (orders && orders.length > 0)
		{
			for(var p in orders)
			{
				arry.push(getOrderHtml(orders[p]));
			}
		}
		else
		{
			arry.push("亲，还没有订单噢");
		}
		$(".h44").html(arry.join(""));
	}
	
	function getOrderHtml(order)
	{
		var arry = [];
		arry.push("<div class=\"list\"><div class=\"title\">");
		arry.push(order.serviceName);
		arry.push("<samp>");
		arry.push(order.statusDes);
		arry.push("</samp></div><div class=\"content\"><img src='");
		arry.push(order.serviceImg);
		arry.push("' /><ul><li><a class=\"am-btn am-btn-link\" href='showQrcode.jsp?id=");
		arry.push(order.workOrderId);
		arry.push("'>订单号二维码</a></li><li>订单日期：");
		arry.push(order.disCreateTime);
		arry.push("</li><li>车牌号：");
		arry.push(order.carNo);
		arry.push("</li><li>订单服务人：" );
		arry.push(order.employId);
		arry.push("</li></ul></div><div class=\"paid\">实付款 ： ￥");
		arry.push(order.amount);
		arry.push("</div>");
		if (order.isDisplayEval == 1)
		{
			arry.push("<div class=\"evaluate\"><a href=\"complaint.jsp?orderId=");
			arry.push(order.workOrderId);
			arry.push("\">评 价</a></div>");
		}
		if (order.canCancel == 1)
		{
			arry.push("<div class=\"evaluate\"><a href=\"javascript:cancelOrder('");
			arry.push(order.workOrderId);
			arry.push("')\">取消预约</a></div>");
		}
		arry.push("</div>");
		
		return arry.join("");
	}
	
	function cancelOrder(orderId)
	{
		var dataD = new Object();

		dataD.serviceAction = "cancelWorkOrderAction";
		dataD.orderId = orderId;
		$.zot.post(dataD,function(ret){
			if (ret == 'S')
			{
				alert('成功取消预约');
				window.location.href = ordersMenuURL;
				//window.location.reload();
			}
			else
			{
				alert('取消预约失败，请稍后再试');	
			}
		});	
	}
	
</script>            
 
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

</body>
</html>