<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/current-status.css">
<div id="current-status-container" class="am-container current-status-container">
<ul class="am-avg-sm-3 current-status-content">
	<li class="in-queue">
		<span class="icon icon-waiting-count " ></span>
		<div class="label">排队中</div>
		<div class="value">
			6<span class="unit">辆</span>
		</div></li>
	<li class="average-service-time">
		<span class="icon icon-average-time" ></span>
		<div class="label">出车速度</div>
		<div class="value">
			32<span class="unit">分/辆</span>
		</div></li>
	<li class="finished">
		<span class="icon icon-finished-count" ></span>
		<div class="label">已服务</div>
		<div class="value">
			6<span class="unit">辆</span>
		</div></li>
</ul>
<%@include file="/component/loading.jsp"  %>
</div>
<script id="current-status-template" type="text/x-handlebars-template">
	<li class="in-queue">
		<span class="icon icon-waiting-count " ></span>
		<div class="label">排队中</div>
		<div class="value">
			{{inQueue}}<span class="unit">辆</span>
		</div></li>
	<li class="average-service-time">
		<span class="icon icon-average-time" ></span>
		<div class="label">出车速度</div>
		<div class="value">
			{{averageServiceTime}}<span class="unit">分/辆</span>
		</div></li>
	<li class="finished">
		<span class="icon icon-finished-count" ></span>
		<div class="label">已服务</div>
		<div class="value">
			{{finished}}<span class="unit">辆</span>
		</div></li>
</script>
<script src="<%= request.getContextPath() %>/assets/js/current-status.js" ></script>