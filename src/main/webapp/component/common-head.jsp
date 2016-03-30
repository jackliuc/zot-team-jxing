<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="description"
        content="爱行快修">
  <meta name="viewport"
        content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
  <title><%= pageContext.getAttribute("page_title") %></title>
  <!-- Set render engine for 360 browser -->
 <meta name="renderer" content="webkit">

 <!-- No Baidu Siteapp-->
 <meta http-equiv="Cache-Control" content="no-siteapp" />

 <link rel="icon" type="image/png" href="<%= request.getContextPath() %>/assets/i/favicon.png">

 <!-- Add to homescreen -->
 <link rel="manifest" href="<%= request.getContextPath() %>/assets/manifest.json">

 <!-- Fallback to homescreen for Chrome <39 on Android -->
 <meta name="mobile-web-app-capable" content="yes">
 <meta name="application-name" content="Web Starter Kit">
 <link rel="icon" sizes="192x192" href="<%= request.getContextPath() %>/assets/i/touch/chrome-touch-icon-192x192.png">
 <!-- Add to homescreen for Safari on iOS -->
 <meta name="apple-mobile-web-app-capable" content="yes">
 <meta name="apple-mobile-web-app-status-bar-style" content="black">
 <meta name="apple-mobile-web-app-title"
       content="爱行快修">
 <link rel="apple-touch-icon" href="<%= request.getContextPath() %>/assets/i/touch/apple-touch-icon.png">
 <!-- Tile icon for Win8 (144x144 + tile color) -->
 <meta name="msapplication-TileImage"
       content="<%= request.getContextPath() %>/assets/i/touch/ms-touch-icon-144x144-precomposed.png">
 <meta name="msapplication-TileColor" content="#0e90d2">
<link rel="icon" type="<%= request.getContextPath() %>/assets/image/png" 
	href="<%= request.getContextPath() %>/assets/i/favicon.png">
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/amazeui.min.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/common.css">
<script src="<%=request.getContextPath()%>/assets/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/handlebars.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/amazeui.min.js"></script>
<script>
	window.zot = {
			contextPath: '<%= request.getContextPath() %>'
	};
</script>
<script src="<%=request.getContextPath()%>/assets/js/common.js"></script>