<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
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
	
	<div id="resultShow" class="am-u-sm-12  am-text-center" style="display:none">
                           订单二维码
	</div>
	<div id="resultCode" class="am-u-sm-12  am-text-center" style="display:none">
	
	</div>
	
	<hr data-am-widget="divider" style="" class="am-divider am-divider-default" />	
	<a href="history-service.jsp">回到服务</a>
	
 </div>
<%@include file="/assets/footer.jsp" %>

<script type="text/javascript">

var v_qrcode = "<%=request.getParameter("id")%>";
if (v_qrcode)
{
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