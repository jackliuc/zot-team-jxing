<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("page_title", "预约信息");
%>
<!doctype html>
<html lang="zh_CN">
<%@include file="/component/common-head.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/subscribe-info.css">
<body>
	<%@include file="/component/header.jsp"%>
	<div class="am-container">
		<div id="subscription-info" class="subscription-info"></div>
	</div>
	<%@include file="/component/footer.jsp"%>
	<%@include file="/component/common-scripts.jsp"%>
	<script src="<%=request.getContextPath()%>/assets/js/subscribe-info.js"></script>
	<script id="subscription-info-template" type="text/x-handlebars-template">
		<div>
			<span>订单号：</span> <span class="value latin strong">{{serviceNumber}}</span>
		</div>
		<div>
			<span>车牌号码：</span> <span class="value">{{carNumber}}</span>
		</div>
		<div>
			<span>预约服务：</span> <span class="value">{{serviceType}}</span>
		</div>
		<div>
			<span>最佳到店时间：</span> <span class="value latin strong am-text-success">{{bestArrivalTime}}</span>
		</div>
		<p class="qcode" id="qcode-placeholder" data-qcode="{{qcode}}"></p>
	</script>
</body>
</html>