<%@page import="com.zot.xing.view.common.IdVO"%>
<%@page import="com.zot.xing.view.service.XingWorkOrderVO"%>
<%@page import="com.zot.xing.view.service.ServiceMgrService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<html class="no-js">
<%@include file="/assets/header.jsp" %>
<body>
  <div class="admin-content">
    <ul class="am-avg-sm-1 am-avg-md-4 am-margin am-padding am-text-center admin-content-list ">
      <li><a href="#" class="am-text-success"><span class="am-icon-btn am-icon-file-text"></span><br/>排队数<br/>13</a></li>
      <li><a href="#" class="am-text-warning"><span class="am-icon-btn am-icon-briefcase"></span><br/>出车速度<br/>15分钟</a></li>
      <li><a href="#" class="am-text-danger"><span class="am-icon-btn am-icon-recycle"></span><br/>已服务数<br/>80082</a></li>
    </ul>
	<hr data-am-widget="divider" style="" class="am-divider am-divider-default"/>
	
	<% 
		String workOrderId = request.getParameter("workOrderId");
		String userType = request.getParameter("userType");
		int iUserType = userType == null ? 0 : Integer.parseInt(userType);
		String userId = request.getParameter("userId");
		String evalType = request.getParameter("evalType");
		int iEvalType = evalType == null ? 1 : Integer.parseInt(evalType);
		String evalDesc = request.getParameter("evalDesc");
	
		XingWorkOrderVO orderVO = new XingWorkOrderVO();
		orderVO.setEvalType(iEvalType);
		orderVO.setEvalDesc(evalDesc);
		orderVO.setWorkOrderId(workOrderId);

		ServiceMgrService.complainOrder(new IdVO(iUserType, userId), orderVO);
	%>
	
	感谢你的反馈	
	<hr data-am-widget="divider" style="" class="am-divider am-divider-default" />	
	<a href="history-service.jsp">回到服务</a>
	
	</div>
<%@include file="/assets/footer.jsp" %>
</body>
</html>