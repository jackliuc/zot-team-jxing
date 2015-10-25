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
	 				
	<div data-am-widget="list_news" class="am-list-news am-list-news-default">	
	  <div class="am-list-news-hd am-cf">	  
	    <a href="##" class="">
	      <h2>订单列表</h2>
	      <span class="am-list-news-more am-fr">更多 &raquo;</span>
	    </a>
	  </div>
	  
	  <div id="table_div">
	  </div>
	</div>
	
  </div>
<%@include file="/assets/footer.jsp" %>

<script type="text/javascript">
	var code = "<%=request.getParameter("code")%>";
	var dataD = new Object();

	dataD.serviceAction = "serviceQueryAction";
	dataD.code = code;
	$.zot.post(dataD,dealOrders);	

	function dealOrders(orders)
	{
		var innerHtml = "";
		if (orders)
		{
			for(var p in orders)
			{
				innerHtml = innerHtml + getOrderHtml(orders[p]);	
			}
		}
		else
		{
			innerHtml = "没有订单噢";	
		}
		document.getElementById("table_div").innerHTML = innerHtml;
	}
	
	function getOrderHtml(order)
	{
		var tblHtml = "<hr data-am-widget=\"divider\" class=\"am-divider am-divider-default\"/><table>";
		tblHtml = tblHtml + "<tr><td>" + order.statusDes + "</td><td>" 
					+ order.disCreateTime + "</td></tr>"; 					
		tblHtml = tblHtml + "<tr><td>订单号：" + order.workOrderId + "</td>" 
							+"<td><a href='showQrcode.jsp?id=" + order.workOrderId + "'>二维码</a>";
		tblHtml = tblHtml + "<tr><td>" + order.serviceName + "</td><td><img width='100' height='120' src='" 
					+ order.serviceImg + "' /></td></tr>";
		if (order.isDisplayEval == 1)
		{
			tblHtml = tblHtml + "<tr><td cols=2><a href='complain-service.jsp?workOrderId=" + order.workOrderId + "'>我要评价</a>";
			tblHtml = tblHtml + "<tr><td>" + order.disEvalType + "</td><td>" + order.evalDesc + "</td><td></tr>"; 			
		}
		tblHtml = tblHtml + "</table>";
		
		return tblHtml;
	}
</script>

</body>
</html>