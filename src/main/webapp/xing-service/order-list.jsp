<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("page_title", "订单历史");
%>
<!doctype html>
<html lang="zh_CN">
<%@include file="/component/common-head.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/order-list.css">
<body>
	<%@include file="/component/header.jsp"%>
	<div class="am-container">
		<div id="order-list" class="order-list"></div>
	</div>
	<%@include file="/component/footer.jsp"%>
	<%@include file="/component/common-scripts.jsp"%>
	<script src="<%=request.getContextPath()%>/assets/js/order-list.js"></script>
	<script id="order-list-template" type="text/x-handlebars-template">
		{{#each orders}}
		<a class="order" href="<%=request.getContextPath()%>/xing-service/order-info.jsp?orderId={{serviceNumber}}">
			<div class="icon"></div>
			<div class="content">
				<div class="order-number">{{serviceNumber}}</div>
				<div>
					<span class="service-date">{{serviceDate}}</span>
					<span class="service-type">{{serviceType}}</span>
					<span class="best-arrival-time">最佳到店时间<span class="time">{{bestArrivalTime}}</span></span>
				</div>
			</div>
		</a>
		{{/each}}
	</script>
</body>
</html>