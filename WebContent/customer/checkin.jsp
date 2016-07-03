<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>

<!--script src="<%=request.getContextPath()%>/assets/js/wxconfig.js"></script>-->

<div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">客户专区</strong> / <small>登记</small></div>
</div>

  <div class="am-tabs am-margin" data-am-tabs>
    <ul class="am-tabs-nav am-nav am-nav-tabs">
      <li class="am-active"><a href="#tab1">客户信息</a></li>
      <li><a href="#tab2">服务信息</a></li>
      <li><a href="#tab3">车况信息</a></li>
    </ul>

    <div class="am-tabs-bd">
      <div class="am-tab-panel am-fade am-in am-active" id="tab1">
        <form class="am-form"  >
          
        <div class="control-group">
          <label class="control-label" for="carId">车牌号</label>
          <div class="controls">
          	<input id="carId" class="input-xlarge" type="text" value="苏A" required="required"/>
          	<div class="am-cf">
				<button id="queryInfoBtn" class="am-btn am-btn-primary am-btn-sm am-fl">提权信息</button>
				<!--button id="scanBtn" class="am-btn am-btn-primary am-btn-secondary am-btn-sm am-fr"
							data-am-loading="{loadingText: '努力扫描中...'}">扫描二维码</button>-->
			</div>  
		  </div>
		</div> 
    	
    	<div class="control-group">
          <!-- Text input-->
          <label class="control-label" for="input01">手机号</label>
          <div class="controls">
            <input id="phoneNum" class="input-xlarge" type="text">
          </div>
        </div>

    	<div class="control-group">
          <!-- Text input-->
          <label class="control-label" for="input01">微信号</label>
          <div class="controls">
            <input id="wechatno" class="input-xlarge" type="text">
          </div>
        </div>

    <div class="control-group">

          <!-- Text input-->
          <label class="control-label" for="input01">姓名</label>
          <div class="controls">
            <input id="customerName" class="input-xlarge" type="text">
          
          </div>
        </div>

    	<div class="control-group">
          <label class="control-label">性别</label>
          <div class="controls">
            <!-- Inline Radios -->
            <label class="am-radio-inline">
              <input name="sex_group" type="radio" checked="checked" value="1">男        
            </label>
            <label class="am-radio-inline">
              <input name="sex_group" type="radio" value="2">女
            </label>
          </div>
        </div>

    	<div class="control-group">
          <!-- Textarea -->
          <label class="control-label">地址</label>
          <div class="controls">
            <div class="textarea">
            <input id="address" class="input-xlarge" type="text">
               
            </div>
          </div>
        </div>
        
         <div class="control-group">
          <!-- Textarea -->
          <label class="control-label">特殊说明</label>
          <div class="controls">
            <div class="textarea">
                  <input id="otherCus" class="input-xlarge" type="text">
            </div>
          </div>
        </div>
        
</form>
      </div>

      <div class="am-tab-panel am-fade" id="tab2">
        <form class="am-form"  >
        
        <div class="control-group">
          <label class="control-label">服务项目</label>
          <!-- Multiple Checkboxes -->
          <div class="controls">
		      <!-- Inline Radios -->
		      <label class="am-radio inline">
		        <input name="serviceType" type="radio" value="0">无服务
		      </label>
		      <label class="am-radio inline">
		        <input name="serviceType" type="radio" value="10001">快洗
		      </label>
		      <label class="am-radio inline">
		        <input name="serviceType" type="radio" value="10002">精洗
		      </label>
		      <label class="am-radio inline">
		        <input name="serviceType" type="radio" value="10003">美容
		      </label>
		      <label class="am-radio inline">
		        <input name="serviceType" type="radio" value="10006">喷漆
		      </label>
		      <label class="am-radio inline">
		        <input name="serviceType" type="radio" value="10008">保养
		      </label>
		      <label class="am-radio inline">
		        <input name="serviceType" type="radio" value="10009">装饰
		      </label>
		      <label class="am-radio inline">
		        <input name="serviceType" type="radio" value="10010">其他
		      </label>
		      <label class="am-radio inline">
		        <input name="serviceType" type="radio" value="18001">充值
		      </label>
		      <label class="am-radio inline">
		        <input name="serviceType" type="radio" value="19001">保险
		      </label>
		  </div>
        </div>

        <div class="control-group">
          <!-- Textarea -->
          <label class="control-label">服务位置</label>
          <div class="controls">
            <div class="textarea">
                  <textarea id="carLocal" > </textarea>
            </div>
          </div>
        </div>
        
        </form>
      </div>

      <div class="am-tab-panel am-fade" id="tab3">
        <form class="am-form" >
          <div class="control-group">

          <!-- Search input-->
          <label class="control-label">车型</label>
          <div class="controls">
            <input id="carBrand" class="input-xlarge search-query" type="text" />
            <p class="help-block"></p>
          </div>

        </div>
        
      	<div class="control-group">
          <!-- Textarea -->
          <label class="control-label">其他</label>
          <div class="controls">
            <div class="textarea">
                  <textarea id="carDesc"> </textarea>
            </div>
          </div>
        </div>
         
        </form>
      </div>
    </div>
  </div>

  <div class="am-margin">
    <button type="button" id="saveButton" class="am-btn am-btn-primary am-btn-sm">提交保存</button>
  </div>
  
  <input type="hidden" id="custId" value="">
  <input type="hidden" id="orderId" value="">
  
  <script type="text/javascript">
  //是否注册客户
  var isCustomer = false;
  //是否注册汽车
  var isCar = false;
  //是否在网上预约
  var isSubscribe = false;
  
  $("#queryInfoBtn").click(function(){
	 //根据车牌号找寻客户信息
	 var carno = $("#carId").val();
	 if (!carno || carno.length != 7)
	 {
	  	alertMsg("请输入7位车牌号");
	  	return false;
	 }
	
	 debugger;
	
	 var dataD = new Object();
 	 dataD.serviceAction = "qryCustomerInfoByCarnoAction";
 	 dataD.carno = carno;
	 $.zot.post(dataD, function(customerInfo){
		 if (!customerInfo || !customerInfo.custId){
			 resetTbls();
			 alertMsg("客户不存在");
			 return;	 
	     }
		 
		 $("#custId").val(customerInfo.custId);
		 $("#phoneNum").val(customerInfo.phoneno);
		 $("#wechatno").val(customerInfo.wechatno);
	 	 $("#customerName").val(customerInfo.custname);
	
	 	 debugger;
	 	 if (customerInfo && customerInfo.sex)
	 	 {
	 		 $("input:radio[name='sex_group'][value=" + customerInfo.sex + "]").attr("checked",true);
	 	 }
	 	 
	 	 $("#address").val(customerInfo.address);
	 	 $("#otherCus").val(customerInfo.remark);
	 	
	 	 debugger;
	 	 if (customerInfo.orderId && customerInfo.orderId.length > 0)
	 	 {
 			$("#orderId").val(customerInfo.orderId);
 			$("input:radio[name='serviceType'][value=" + customerInfo.serviceType + "]").attr("checked",true);
	     }
	 });
	 
	 return false;
  });
  
  $("#saveButton").click(function(){
		var carno = $("#carId").val();
		if (!carno || carno.length != 7)
	 	{
		 	alertMsg("请输入7位车牌号");
		 	return false;	 
     	}
		
		var phoneno = $("#phoneNum").val();
		if (!phoneno || phoneno.length != 11)
	 	{
		 	alertMsg("请输入11位手机号码");
		 	return false;	 
     	}

		var serviceType = $('input:radio[name="serviceType"]:checked').val();
		if (!serviceType)
		{
			alertMsg("请选择服务项目");
		 	return false;
		}
	  
	 	var dataD = new Object();
	 	dataD.serviceAction = "saveCustomerInfoAction";
	 
	 	dataD.custId = $("#custId").val();	
	 	dataD.orderId = $("#orderId").val();
	 	
	 	dataD.carno = carno;

	 	dataD.phoneNum = phoneno;
	 	dataD.wechatno = $("#wechatno").val();
 		dataD.customerName = $("#customerName").val();
 		dataD.sex = $('input:radio[name="sex_group"]:checked').val();
 		dataD.address = $("#address").val();
 		dataD.otherCus = $("#otherCus").val();

 		//dataD.serviceType = $("#serviceType").val();
 		//dataD.carLocal = $("#carLocal").val();
 		/*
 		$("input[name='serviceType'][checked]").each(function(){
			if (true == $(this).attr("checked")) {
				dataD.serviceType = $(this).attr('value');
			}
 		})
 		*/
 		if (serviceType != 0){
 			dataD.serviceType = serviceType;
 		}
 	
	 	$.zot.post(dataD,function(ret)
		{
	 		if (ret == "S")
	 		{
	 			alertMsg("保存成功");	
	 		}
	 		else
	 		{
	 			alertMsg("保存失败，请重试");	
	 		}
		});
	 	
	 	return false;
	});
  
  $("#scanBtn").click(function(){
	  if (!jsconfigReady)
	  {
		  alertMsg("扫描初始化中，请稍后");
		  return false;
	  }
		
	  var $btn = $(this)
	  $btn.button('loading');

	  wx.scanQRCode({
		    needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
		    scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有		    
		    success: function (res) {
		    	var v_qrcode = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
		    	alertMsg("二维码：" + v_qrcode);
			},			
			fail: function (res) {//接口调用失败时执行的回调函数		    	
				alertMsg("扫描二维码失败，请稍后重试");
			},			
			complete: function (res) {//接口调用完成时执行的回调函数，无论成功或失败都会执行。		    						
			},			
			cancel: function (res) {//用户点击取消时的回调函数，仅部分有用户取消操作的api才会用到。		    	
				alertMsg("取消二维码扫描");
			}			
		});
	  
	  setTimeout(function(){
	      $btn.button('reset');
	  }, 3000);
	  
	  return false;
  });
  
  function resetTbls()
  {
	 $("#custId").val("");
	 $("#phoneNum").val("");
	 $("#wechatno").val("");
 	 $("#customerName").val("");
 	 $("input:radio[name='sex_group'][value=1]").attr("checked",true);
 	 $("#address").val("");
 	 $("#otherCus").val("");
 	 $("#orderId").val("");
	 $("input:radio[name='serviceType']").attr("checked",false);
  }
  
  </script>