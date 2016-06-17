<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html class="no-js">
<%@include file="/assets/header.jsp" %>
<body>
	 
	  <div class="admin-content">

   <%@include file="/assets/statistic.jsp"%>
	
	<hr data-am-widget="divider" style="" class="am-divider am-divider-default"/>
	
	    <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">
              	服务类型
            </div>
            <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
               <select id="subtype">
				  <option value="10001" selected>快洗</option>
				  <option value="10002">精洗</option>
				  <option value="10003">美容</option>
				  <option value="10006">油漆</option>
				</select>
            </div>
          </div>
	    
	    <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">
            	预约时间
            </div>
             <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
              <input id="ordertime" type="datetime-local"/>
            </div>
          </div>
	   
		 <div class="am-g am-margin-top">
				<button id="confirmButton" class="am-btn-block">确认</button>
		 </div>

    </div>
<div id="resultShow" class="am-u-sm-12  am-text-center" style="display:none">
                                                        预约成功，请凭借微信条形码到店服务
</div>
<div id="resultCode" class="am-u-sm-12  am-text-center" style="display:none">

</div>

<%@include file="/assets/footer.jsp" %>

<script type="text/javascript">

$("#subtype").val("<%=request.getParameter("subtype")%>");
var code = "<%=request.getParameter("code")%>";

function resultCallBack(result)
{
	var v_qrcode = result.id ? result.id : 0;
	$("#resultCode").qrcode({
		width:128,
		height:128,
		correctLevel:0,
		text:v_qrcode
	});
	$("#resultShow").show();
	$("#resultCode").show();
}

$("#confirmButton").click(function(){
	
 	var dataD = new Object();
		
 	dataD.serviceAction = "subscribeResultAction";
 	dataD.code = code;
 	dataD.subtype = $("#subtype").val();
 	dataD.subtime = $("#ordertime").val();
	
	$.zot.post(dataD,resultCallBack);	
});

</script>
</body>
</html>