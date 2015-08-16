<%@page import="com.zot.xing.view.service.XingWorkOrderVO"%>
<%@page import="com.sun.xml.internal.fastinfoset.algorithm.BuiltInEncodingAlgorithm.WordListener"%>
<%@page import="com.zot.xing.view.service.XingWorkOrderVO"%>
<%@page import="java.util.List"%>
<%@page import="com.zot.xing.view.common.IdVO"%>
<%@page import="com.zot.xing.view.service.ServiceMgrService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="com.zot.xing.view.service.ServiceQueryAction" %>
<html class="no-js">
<%@include file="/assets/header.jsp" %>
<body>
  <div class="admin-content">
    <ul class="am-avg-sm-3 am-margin am-padding am-text-center admin-content-list ">
      <li><a href="#" class="am-text-success"><span class="am-icon-btn am-icon-file-text"></span><br/>排队数<br/>13</a></li>
      <li><a href="#" class="am-text-warning"><span class="am-icon-btn am-icon-briefcase"></span><br/>出车速度<br/>15分钟</a></li>
      <li><a href="#" class="am-text-danger"><span class="am-icon-btn am-icon-recycle"></span><br/>已服务数<br/>80082</a></li>
    </ul>
	<hr data-am-widget="divider" style="" class="am-divider am-divider-default"/>
	 
	<% 
		List<XingWorkOrderVO> orders = ServiceMgrService.queryServices(new IdVO());
	%>
	
	<div data-am-widget="list_news" class="am-list-news am-list-news-default">
	
	  <div class="am-list-news-hd am-cf">
	  
	    <a href="##" class="">
	      <h2>服务列表</h2>
	      <span class="am-list-news-more am-fr">更多 &raquo;</span>
	    </a>
	  </div>
	  <div class="am-list-news-bd">
	    <ul class="am-list">
	      
	      <% 
	      	if (orders != null)
	      	{
	      		for (XingWorkOrderVO order : orders)
	      		{
	      			out.print("<li class=\"am-g am-list-item-dated\">");
	      			out.print("<a href=\"##\" class=\"am-list-item-hd \">");
	      			out.print(order.getCustId());
	      			out.print("</a> <span class=\"am-list-date\">");
	      			out.print(order.getCarNo());
	      			out.print("</span></li>");	    	        
	      		}
	      	}
	      %>	      
	    </ul>
	  </div>
	</div>
	
  </div>
<%@include file="/assets/footer.jsp" %>
</body>
</html>