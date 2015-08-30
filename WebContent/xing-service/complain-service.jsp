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
		String userId = request.getParameter("userId");
	%>
	
	<form action="complain-finished.jsp" method="post" class="am-form" data-am-validator>
  		<fieldset>
    		<legend>服务评价</legend>    
		    	<div class="am-form-group">
		  		<h3>您觉得本次服务</h3>
		  		<label class="am-radio-inline">
		    		<input type="radio" name="evalType" value="1" checked data-am-ucheck> 好
		  		</label>
		  		<label class="am-radio-inline">
		    		<input type="radio" name="evalType" value="2" data-am-ucheck> 差
		  		</label>
			</div>
		
		    <div class="am-form-group">
		      <label for="doc-vld-ta-2">评论：</label>
		      <textarea id="doc-vld-ta-2" name="evalDesc" minlength="1" maxlength="100"></textarea>
		    </div>
		    
		    <input type="hidden" name="workOrderId" value="<%=workOrderId%>">
		    <!-- 
		    <input type="hidden" name="userType" value="<%=userType%>">
		    <input type="hidden" name="userId" value="<%=userId%>">
		     -->
		
		    <button class="am-btn am-btn-secondary" type="submit">提交</button>
		 </fieldset>
	</form>
	
	</div>
<%@include file="/assets/footer.jsp" %>
</body>
</html>