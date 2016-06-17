<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<html class="no-js">
<%@include file="/assets/header.jsp" %>
<body>
  <div class="admin-content">
	<hr data-am-widget="divider" style="" class="am-divider am-divider-default"/>
	
	<% 
		String orderId = request.getParameter("orderId");
	%>
	
	<form action="" method="post" class="am-form" data-am-validator>
  		<fieldset>
    		<legend>服务评价</legend>    
		    	<div class="am-form-group">
		  		<h3>您觉得本次服务</h3>
		  		<label class="am-radio-inline">
		    		<input type="radio" name="evalType" value="1" data-am-ucheck> 好
		  		</label>
		  		<label class="am-radio-inline">
		    		<input type="radio" name="evalType" value="2" data-am-ucheck> 差
		  		</label>
			</div>
		
		    <div class="am-form-group">
		      <label for="doc-vld-ta-2">评论：</label>
		      <textarea id="doc-vld-ta-2" name="evalDesc" minlength="1" maxlength="100"></textarea>
		    </div>
		    
		    <input type="hidden" id="orderId" value="<%=orderId%>">
		
		    <button class="am-btn am-btn-secondary" type="button" id="submitBtn">提交</button>
		 </fieldset>
	</form>
	
	</div>
	
	<div id="complain-finished" class="am-u-sm-12  am-text-left" style="display:none">               	
		<hr data-am-widget="divider" style="" class="am-divider am-divider-default" />	
		 感谢你的反馈    <a href="history-service.jsp">回到我的服务</a>
	</div>
	
<%@include file="/assets/footer.jsp" %>

<script type="text/javascript">
	
	var code = "<%=request.getParameter("code")%>";

	function submitComplainBack(result)
	{
		$("#complain-finished").show();
	}
	
	$("#submitBtn").click(function(){
		$("#complain-finished").hide();
		
		var evalType = $('input:radio[name="evalType"]:checked').val();
	 	if (!evalType)
	 	{
	 		alert("亲，你还没评价呢");	
	 		return;
	 	}
	 	var evalDesc = $("#doc-vld-ta-2").val();
	 	var orderId = $("#orderId").val();
	 	
	 	var dataD = new Object();
		dataD.serviceAction = "complainServiceAction";
	 	dataD.code = code;
	 	dataD.orderId = orderId;
	 	dataD.evalType = evalType;
	 	dataD.evalDesc = evalDesc;
		
		$.zot.post(dataD,submitComplainBack);
	});
	
</script>

</body>
</html>