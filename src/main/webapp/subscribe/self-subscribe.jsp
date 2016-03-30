<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("page_title", "预约");
%>
<!doctype html>
<html lang="zh_CN">
<%@include file="/component/common-head.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/subscribe.css">
<body>
	<%@include file="/component/header.jsp"%>
	<div class="am-container">
		<%@include file="/component/current-status.jsp"%>
		<%@include file="subscribe-form.jsp" %>
		<%@include file="has-subscribed-popup.jsp" %>
	</div>
	<%@include file="/component/footer.jsp"%>
	<%@include file="/component/common-scripts.jsp"%>
	<script src="<%=request.getContextPath()%>/assets/js/subscribe.js"></script>
</body>
</html>