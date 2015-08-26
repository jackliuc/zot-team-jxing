<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html class="no-js">
<%@include file="/assets/header.jsp"%>
<%@ page import="com.zot.view.contorler.PrefixService" %>
<body>
 
	<div class="admin-content">

	<%@include file="/assets/statistic.jsp"%>
	
		<hr data-am-widget="divider" style=""
			class="am-divider am-divider-default" />
		<div id="tab2">
			<form class="am-form"
				action="<%=request.getContextPath()%>/subscribe/subscribe-result.jsp"
				method="post">
				<div class="am-g am-margin-top">
					<div class="am-u-sm-4 am-u-md-2 am-text-right">服务类型</div>
					<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
						<select name="subtype">
							<option value="10001">洗车</option>
							<option value="10002">做漆</option>
							<option value="10003">钣金</option>
							<option value="10006">装潢</option>
						</select>
					</div>
				</div>
				<div class="am-g am-margin-top">
					<div class="am-u-sm-4 am-u-md-2 am-text-right">最佳到店时间</div>
					<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
						<input name="ordertime" type="text" class="am-input-sm"
							readonly="readonly">
					</div>
				</div>

				<div class="am-g am-margin-top">
					<div class="am-u-sm-4 am-u-md-2 am-text-right">预计完成时间</div>
					<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
						<input name="overtime" type="text" class="am-input-sm"
							readonly="readonly">
					</div>
				</div>

				<div class="am-g am-margin-top">
					<div class="am-u-sm-4 am-u-md-2 am-text-right">服务价格</div>
					<div class="am-u-sm-8 am-u-md-4">
						<input name="price" type="text" class="am-input-sm"
							readonly="readonly">
					</div>
					<div class="am-hide-sm-only am-u-md-6">已享受折扣</div>

				</div>
				<div class="am-g am-margin-top">
					<div class="am-margin">
						<button type="submit" class="am-btn am-btn-primary am-btn-xs">确认</button>
					</div>
					
				</div>
			</form>
		</div>
	</div>
	<!-- Navbar -->
	<div data-am-widget="navbar" class="am-navbar am-cf am-navbar-default "
		id="">
		<ul class="am-navbar-nav am-cf am-avg-sm-4">
			<li><a href="tel:18602552086"> <span class="am-icon-phone"></span>
					<span class="am-navbar-label">呼叫</span>
			</a></li>
			<li><a
				href="<%=request.getContextPath()%>/subscribe/self-subscribe.jsp">
					<span class="am-icon-qrcode"></span> <span class="am-navbar-label">自助选择</span>
			</a></li>
			<li data-am-navbar-share><a href="###"> <span
					class="am-icon-share-square-o"></span> <span
					class="am-navbar-label">导航</span>
			</a></li>
			<li data-am-navbar-share><a href="###"> <span
					class="am-icon-share-square-o"></span> <span
					class="am-navbar-label">分享</span>
			</a></li>
		</ul>
	</div>
<button id="testajax" type="button" >test</button>
</body>
<script type="text/javascript">
alert(<%=request.getParameter("subtype")%>);
var data = new Object();
data.serviceAction = "SubscribeResultServiceImpl";
data.requestD = "<%=request.getParameterMap()%>";


$("#testajax").click(function(){
	 $.post("<%=request.getContextPath()%>/busdata",
			 data,
			 function(result){
		 alert(result);
	 },"json");
});



</script>
</html>