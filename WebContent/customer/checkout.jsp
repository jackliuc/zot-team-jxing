<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>

<div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">客户专区</strong> / <small>结账</small></div>
</div>
 <form class="am-form">
 
    <div class="control-group">

          <!-- Search input-->
          <label class="control-label">车牌号</label>
          <div class="controls">
            <input id="carno" class="input-xlarge search-query" type="text" value="苏A" placeholder="">
            <input id="qryInfoBtn" type="button" value="提取信息"/> 
            <p class="help-block"></p>
          </div>
          
          <div class="control-group">
          	<!-- Text input-->
          	<label class="control-label">姓名</label>
          	<span id="custName"></span>
          </div> 
          
          <div class="control-group">
          	<!-- Text input-->
          	<label class="control-label">手机号</label>
          	<span id="phoneno"></span>
          </div>
          
          <div class="control-group">
          	<!-- Text input-->
          	<label class="control-label">服务项目</label>
          	<span id="serviceName"></span>
          </div>
         
          <p class="help-block"></p>      

        </div>
        
        <div class="control-group">
          <!-- Text input-->
          <label class="control-label" for="input01">员工号</label>
          <div class="controls">
            <input id="employId" class="input-xlarge" type="text" placeholder="">
            <p class="help-block"></p>
          </div>
        </div>
        
        <div class="control-group">
          <!-- Text input-->
          <label class="control-label" for="input01">实收金额</label>
          <div class="controls">
            <input id="payAmount" class="input-xlarge" type="text" placeholder="">
            <p class="help-block"></p>
          </div>
        </div>
        
        <div class="control-group">
<label class="control-label" for="input01">支付方式</label>
<div class="controls">
          <label class="radio inline">
              <input id="costtype" name="paytype" type="radio" checked="checked" value="C">
              现金
            </label>
            <label class="radio inline">
              <input id="costtype" name="paytype" type="radio" value="A">
            支付宝
            </label>
            <label class="radio inline">
              <input id="costtype" name="paytype" type="radio" value="W">
             微信
            </label>
            <label class="radio inline">
              <input id="costtype" name="paytype" type="radio" value="P">
    POS机
            </label>
          	<label class="radio inline">
              <input id="costtype" name="paytype" type="radio" value="M">
             会员卡
            </label>
            </div>
        </div>
        
        <div class="control-group">

          <!-- Textarea -->
          <label class="control-label">其他</label>
          <div class="controls">
            <div class="textarea">
                  <textarea id="remark" type=""> </textarea>
            </div>
          </div>
        </div>

  </form>
  
   <div class="am-margin">
    <button type="button" id="saveBtn" class="am-btn am-btn-primary am-btn-xs">提交保存</button>
  </div>
  
   <input type="hidden" id="custId" value="">
   <input type="hidden" id="orderId" value="">
   <input type="hidden" id="orderType" value="">
   
   <script type="text/javascript">
   $("#qryInfoBtn").click(function(){
	 	  //根据车牌号找寻客户信息
	 	  //debugger;
		  var carno = $("#carno").val();
		  if (!carno || carno.length != 7)
		  {
		  	alertMsg("请输入7位车牌号");
		  	return;
		  }

		 var dataD = new Object();
	 	 dataD.serviceAction = "qryCustomerInfoByCarnoAction";
	 	 dataD.carno = carno;
		 $.zot.post(dataD, function(customerInfo){
			 if (!customerInfo || !customerInfo.orderId)
			 {
				 alertMsg("该车牌号当前无待付款订单");
				 return;	 
		     }
			 
			 $("#custId").val(customerInfo.custId);
			 $("#orderId").val(customerInfo.orderId);
			 $("#orderType").val(customerInfo.serviceType);
			 
			 $("#phoneno").text(customerInfo.phoneno);
		 	 $("#custName").text(customerInfo.custname);
		 	 $("#serviceName").text(customerInfo.serviceName);
		 });
   });
   
	$("#saveBtn").click(function(){
		var orderId = $("#orderId").val();
		if (!orderId || orderId.length <= 0)
		{
			 alertMsg("该车牌号当前无待付款订单");
			 return;	 
	    }
		
		var employId = $("#employId").val();
		var payAmount = $("#payAmount").val();
		//TODO:校验金额，不能为字符
		if (!employId || !payAmount)
	 	{
		 	alertMsg("请输入员工号和实收金额");
		 	return;	 
     	}

	 	var dataD = new Object();
	 	dataD.serviceAction = "saveWorkOrderInfoAction";
	 
	 	dataD.custId = $("#custId").val();	
	 	dataD.orderId = orderId;
	 	dataD.orderType = $("#orderType").val();
	 	
	 	dataD.carno = $("#carno").val();
	 	dataD.employId = employId;
	 	dataD.paytype = $('input:radio[name="paytype"]:checked').val();;
	 	dataD.amount = $("#payAmount").val();
 		dataD.remark = $("#remark").val();
 		
 		$.zot.post(dataD,function(ret)
		{
	 		if (ret == "S")
	 		{
	 			$("#custId").val("");
				$("#orderId").val("");
				$("#orderType").val("");
				$("#phoneno").text("");
			 	$("#custName").text("");
			 	$("#serviceName").text("");
			 	
			 	alertMsg("保存成功");
	 		}
	 		else
	 		{
	 			alertMsg("保存失败，请重试");	
	 		}
		});	
   });
	
   $(document).ready(function(){
	   //debugger;
	   var g_carno = $("#global_carno").val();
	   if (g_carno && g_carno.length == 7){
		   $("#carno").val(g_carno);
		   $("#qryInfoBtn").trigger("click");
	   }
	});
   </script>
