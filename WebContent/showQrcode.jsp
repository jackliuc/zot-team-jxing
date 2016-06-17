<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>我的订单</title>
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
    <div class="nav-top">
        <a href="#" class="nav-btn" id="navBtn"><i class="icon iconfont">&#xe611;</i></a>
        <a href="index.html" class="nav-logo">壹号车</a>
        <a href="#" class="nav-right"><i class="icon iconfont">&#xe63e;</i></a>
    </div>
</header>

<div class="am-vertical-align" style="height: 360px;">
	<div class="am-vertical-align-middle">
		<div id="resultShow" class="am-u-sm-12  am-text-center" style="display:none">
	                           订单二维码
		</div>
		<div id="resultCode" class="am-u-sm-12  am-text-center" style="display:none">
		</div>
		
		<div class="am-vertical-align am-fl" style="height: 60px;">
			<div class="am-vertical-align-middle">
				<!-- <a class="am-btn am-btn-link" href="orders.jsp">回到我的服务</a> -->
			</div>
		</div>
	</div>
</div>  

<div class="am-modal am-modal-alert" tabindex="-1" id="alert-msg">
  <div class="am-modal-dialog">
    <div class="am-modal-bd">
    </div>
    <div class="am-modal-footer">
      <span class="am-modal-btn">确定</span>
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

var v_qrcode = "<%=request.getParameter("id")%>";
if (v_qrcode)
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

</script>

</body>
</html>