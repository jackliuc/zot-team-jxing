<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>服务评价</title>
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
        <a href="#" class="nav-right">
            <i class="icon iconfont">&#xe63e; </i></a>    </div>
</header>

<% 
	String orderId = request.getParameter("orderId");
%>

<input type="hidden" id="evalType" value="" />
<input type="hidden" id="orderId" value="<%=orderId%>">

<div class="h44"> 	
	<div class="am-g am-margin-top">
       <div class="am-u-sm-12 am-u-md-12">
         <div class="am-btn-group" data-am-button>
           <label class="am-btn am-btn-warning am-btn-lg">
             <input type="radio" name="evalType" id="" value="1"> 满 意 
           </label>
           <label class="am-btn am-btn-warning am-btn-lg">
             <input type="radio" name="evalType" id="" value="2"> 一 般
           </label>
           <label class="am-btn am-btn-warning am-btn-lg">
             <input type="radio" name="evalType" id="" value="3"> 不 满 意
           </label>
         </div>
       </div>
     </div>
     
     <hr>
	
	<textarea name="" id="evalDesc" cols="" rows="" placeholder="请留下您对我们的建议或者意见"  
		style="width:89%; margin-right:5%; margin-left:5%; margin-bottom:10px;"></textarea>
	
	<div class="button" id="submitBtn">提 交</div>
	
	<div id="complain-res-div" class="complaint" style="display:none">
		<hr>
		<div>感谢您的反馈  
		<!--<a class="am-btn am-btn-link am-fr" href="orders.jsp">回到我的服务</a>-->
	</div>
</div> 
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
	
	function submitComplainBack(result)
	{
		$("#complain-res-div").show();
	}
	
	$("#submitBtn").click(function(){
		$("#complain-res-div").hide();
		
		var evalType = $("input[name='evalType']:checked").val();;
	 	if (!evalType)
	 	{
	 		alertMsg("亲，你还没评价呢");	
	 		return;
	 	}
	 	var evalDesc = $("#evalDesc").val();
	 	var orderId = $("#orderId").val();
	 	
	 	var dataD = new Object();
		dataD.serviceAction = "complainServiceAction";
	 	dataD.code = code;
	 	dataD.orderId = orderId;
	 	dataD.evalType = evalType;
	 	dataD.evalDesc = evalDesc;
		
		$.zot.post(dataD,submitComplainBack);
	});
	
</script>

</body>
</html>