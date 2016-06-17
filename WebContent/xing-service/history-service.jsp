<%@page import="com.zot.wechat.msg.Constant"%>
<%@page import="com.zot.wechat.util.WXAppOpenApi"%>
<%@page import="com.sun.xml.internal.fastinfoset.algorithm.BuiltInEncodingAlgorithm.WordListener"%>
<%@page import="java.util.List"%>
<%@page import="com.zot.xing.view.common.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="com.zot.xing.view.service.ServiceQueryAction" %>
<html class="no-js">
<%@include file="/assets/header.jsp" %>
<body>
  <div class="admin-content">
	<hr data-am-widget="divider" style="" class="am-divider am-divider-default"/>
	 				
	<div data-am-widget="list_news" class="am-list-news am-list-news-default">	
	  <div class="am-list-news-hd am-cf">	  
	    <a href="##" class="">
	      <h2>我的服务</h2>
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
			innerHtml = "亲，还没有订单噢";	
		}
		document.getElementById("table_div").innerHTML = innerHtml;
	}
	
	function getOrderHtml(order)
	{
		var tblHtml = "<hr data-am-widget=\"divider\" class=\"am-divider am-divider-default\"/><table width=90%>";
		tblHtml = tblHtml + "<tr><td>" + order.statusDes + "</td><td align=right>" 
					+ order.disCreateTime + "</td></tr>"; 					
		tblHtml = tblHtml + "<tr><td>" + order.workOrderId + "</td>" 
							+"<td align=right><a href='showQrcode.jsp?id=" + order.workOrderId + "'>二维码</a></td></tr>";
		tblHtml = tblHtml + "<tr><td><img width='100px' height='75px' src='" 
					+ order.serviceImg + "' /></td><td align=right>" + order.serviceName + "</td></tr>";
		if (order.isDisplayEval == 1)
		{
			tblHtml = tblHtml + "<tr><td></td><td align='right'><a href='complain-service.jsp?orderId=" + order.workOrderId + "'>我要评价</a>";
			//tblHtml = tblHtml + "<tr><td>" + order.disEvalType + "</td><td>" + order.evalDesc + "</td><td></tr>"; 			
		}
		tblHtml = tblHtml + "</table>";
		
		return tblHtml;
	}
</script>

</body>
</html>