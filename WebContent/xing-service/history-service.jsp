<%@page import="com.zot.wechat.msg.Constant"%>
<%@page import="com.zot.wechat.util.WXAppOpenApi"%>
<%@page import="com.zot.xing.view.service.XingWorkOrderVO"%>
<%@page import="com.sun.xml.internal.fastinfoset.algorithm.BuiltInEncodingAlgorithm.WordListener"%>
<%@page import="com.zot.xing.view.service.XingWorkOrderVO"%>
<%@page import="java.util.List"%>
<%@page import="com.zot.xing.view.common.*"%>
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
		String code = request.getParameter("code");
		WXAppOpenApi api = new WXAppOpenApi(Constant.sCorpID, Constant.sCorpSecret, Constant.sAppID);
		String userId = api.getUserId(code);
		//String subId = "101";
		List<XingWorkOrderVO> orders = ServiceMgrService.queryOrders(new IdVO(0, userId));
	%>
	
	<div data-am-widget="list_news" class="am-list-news am-list-news-default">
	
	  <div class="am-list-news-hd am-cf">
	  
	    <a href="##" class="">
	      <h2>订单列表</h2>
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
	      			out.print("<span class=\"am-list-item-hd \">");
	      			out.print("订单状态：" + order.getStatus());
	      			out.print("</span>");
	      			out.print("<span class=\"am-list-news-more am-fr\">");
	      			out.print("订单号：" + order.getWorkOrderId());
	      			out.print("</span></li>");
	      			
	      			out.print("<li class=\"am-g am-list-item-dated\">");
	      			out.print("<span class=\"am-list-item-hd \">");
	      			out.print("服务项目：" + order.getServiceName());
	      			out.print("</span>");
	      			out.print("<span class=\"am-list-news-more am-fr\">");
	      			out.print("服务时间：");
	      			if (order.getServieTime() != null)
	      			{
	      				out.print(order.getServieTime());	
	      			}     			
	      			out.print("</span></li>");	
	      			
	      			out.print("<li class=\"am-g am-list-item-dated\">");
	      			out.print("<span class=\"am-list-item-hd \">");
	      			out.print("服务人：" + order.getServicePerson());
	      			out.print("</span>");
	      			
	      			if(order.getStatus() == OrderStatus.FINISHED)
	      			{
	      				out.print("<a href=\"complain-service.jsp?workOrderId=");
	      				out.print(order.getWorkOrderId());
	      				out.print("\" class=\"am-list-news-more am-fr\">");      			
		      			out.print("我要评价</a>");	
	      			}
	      			out.print("</li><br>");
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