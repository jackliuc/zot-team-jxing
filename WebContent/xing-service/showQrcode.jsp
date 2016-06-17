<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<html class="no-js">
<%@include file="/assets/header.jsp" %>
<body>
  <div class="admin-content">
	<hr data-am-widget="divider" style="" class="am-divider am-divider-default"/>
	
	<div id="resultShow" class="am-u-sm-12  am-text-center" style="display:none">
                           订单二维码
	</div>
	<div id="resultCode" class="am-u-sm-12  am-text-center" style="display:none">
	</div>
	
	<div class="am-vertical-align am-fl" style="height: 60px;">
		<div class="am-vertical-align-middle">
			<a class="am-btn am-btn-link" href="../orders.jsp">回到我的服务</a>
		</div>
	</div>
	
 </div>
<%@include file="/assets/footer.jsp" %>

<script type="text/javascript">

var v_qrcode = "<%=request.getParameter("id")%>";
if (v_qrcode)
{
	$("#resultCode").html("");
	$("#resultCode").qrcode({
		width:128,
		height:128,
		correctLevel:0,
		text:v_qrcode
	});
	$("#resultShow").show();
	$("#resultCode").show();
}

</script>
</body>
</html>