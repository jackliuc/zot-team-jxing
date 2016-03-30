<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("page_title", "订单详情");
%>
<!doctype html>
<html lang="zh_CN">
<%@include file="/component/common-head.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/service-info.css">
<body>
	<%@include file="/component/header.jsp"%>
	<div class="am-container">
		<div id="service-info" class="service-info"></div>
	</div>
	<%@include file="/component/footer.jsp"%>
	<%@include file="/component/common-scripts.jsp"%>
	<script src="<%=request.getContextPath()%>/assets/js/order-info.js"></script>
	<script id="service-info-template" type="text/x-handlebars-template">
		<div>
			<span>订单号：</span> <span class="value latin strong">{{serviceNumber}}</span>
		</div>
		<div>
			<span>车牌号码：</span> <span class="value">{{carNumber}}</span>
		</div>
		<div>
			<span>服务状态：</span> <span class="value">已完成</span>
		</div>
		<div>
			<span>服务日期：</span> <span class="value latin">{{serviceDate}}</span>
		</div>
		<div>
			<span>服务时间：</span> <span class="value latin strong am-text-success">{{serviceTime}}</span>
		</div>
	</script>
</body>
</html>