<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>

<!-- content start -->
<div class="admin-content">
  <div class="admin-content-body">
    <div class="am-cf am-padding am-padding-bottom-0">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">系统设置</strong> / <small>客户管理</small></div>
    </div>

    <hr>

    <div class="am-g am-form">
      <div class="am-u-sm-4">
          <div class="am-u-sm-4">手机号</div>
          <div class="am-u-sm-8">
            <input id="qry_phoneno" type="text" class="am-form-field">
          </div>
      </div>
      <div class="am-u-sm-4">
          <div class="am-u-sm-4">车牌号</div>
          <div class="am-u-sm-8">
            <input id="qry_carno" type="text" value="苏A" class="am-form-field">
          </div>
      </div>
      <div class="am-u-sm-4">
         <span class="am-input-group-btn">
          	<button id="qryCustBtn" class="am-btn am-btn-primary am-btn-block" type="button">查询</button>
         </span>
      </div>
    </div>
    
    <p/>
    
    <div class="am-g">
      <div class="am-u-sm-12 am-u-md-6">
          <div class="am-btn-toolbar">
            <div class="am-btn-group am-btn-group-sm">
              <button id="add_cust_btn" type="button" class="am-btn am-btn-primary">
              	<span class="am-icon-plus"></span>新增
              </button>
            </div>
          </div>
      </div>
      <div class="am-u-sm-12">
        <form class="am-form">
          <table class="am-table am-table-striped am-table-hover table-main">
            
            <thead>
            <tr>
              <th class="table-date">姓名</th>
              <th class="table-title">性别</th>
              <th class="table-date">手机号</th>
              <th class="table-title">车牌号</th>
              <th class="table-title">会员等级</th>
              <th class="table-title">服务折扣</th>
              <th class="table-title">产品折扣</th>
              <th class="table-title">操作</th>
            </tr>
            </thead>
            
            <tbody id="tby">
            </tbody>
            
          </table>
        </form>
      </div>
    </div>
    
    <!-- 新增，编辑区域 -->
    <div class="am-g" id="edit_cust_div" style="display:none">
    	<div class="am-g am-form">
	      <div class="am-u-sm-6">
	          <div class="am-u-sm-4">姓名</div>
	          <div class="am-u-sm-8">
	            <input type="text" id="edit_cust_name" class="am-input-sm">
	          </div>
	      </div>
	      <div class="am-u-sm-6">
	          <div class="am-u-sm-4">性别</div>
	          <div class="am-u-sm-8">
	            <label class="am-radio-inline">
	            	<input name="edit_cust_sex" type="radio" checked="checked" value="1">男 
	            </label>
	            <label class="am-radio-inline">
	            	<input name="edit_cust_sex" type="radio" value="2">女
	            </label>
	          </div>
	      </div>
	    </div>
	    <p/>
	    
    	<div class="am-g am-form">
	      <div class="am-u-sm-6">
	          <div class="am-u-sm-4">手机号</div>
	          <div class="am-u-sm-8">
	            <input type="text" id="edit_cust_phoneno" class="am-input-sm">
	          </div>
	      </div>
	      <div class="am-u-sm-6">
	          <div class="am-u-sm-4">车牌号</div>
	          <div class="am-u-sm-8">
	            <input type="text" id="edit_cust_carno" class="am-input-sm">
	          </div>
	      </div>
	    </div>
	    <p/>
	    
	    <div class="am-g am-form">
	      <div class="am-u-sm-6">
	          <div class="am-u-sm-4">车品牌</div>
	          <div class="am-u-sm-8">
	            <select id="edit_cust_car_brand">
	            	<option value="500000001" selected>大众</option>
	              	<option value="500000002">福特</option>
	              	<option value="500000003">奔驰</option>
	              	<option value="500000004">宝马</option>
	              	<option value="500000005">奥迪</option>
	            </select>
	          </div>
	      </div>
	      <div class="am-u-sm-6">
	          <div class="am-u-sm-4">地址</div>
	          <div class="am-u-sm-8">
	            <input type="text" id="edit_cust_address" class="am-input-sm">
	          </div>
	      </div>
	    </div>
	    <p/>
	    
	    <div class="am-g am-form">
	      <div class="am-u-sm-6">
	          <div class="am-u-sm-4">会员等级</div>
	          <div class="am-u-sm-8">
	            <select id="edit_cust_class">
	            	<option value="-1" selected>无</option>
	            	<option value="9001">普卡会员</option>
	              	<option value="9002">金卡会员</option>
	              	<option value="9002">钻石会员</option>
	            </select>
	          </div>
	      </div>
	      <div class="am-u-sm-6">
	          <div class="am-u-sm-4">充值金额</div>
	          <div class="am-u-sm-8">
	            <input type="text" id="edit_cust_recharge_amt" class="am-input-sm">
	          </div>
	      </div>
	    </div>
	    <p/>
	    
	    <div class="am-g am-form">
	      <div class="am-u-sm-6">
	          <div class="am-u-sm-4">特殊说明</div>
	          <div class="am-u-sm-8">
	            <input type="text" id="edit_cust_remark" class="am-input-sm">
	          </div>
	      </div>
	      <div class="am-u-sm-6">
	      </div>
	    </div>
	    
		<div class="am-margin">
      		<button type="button" id="edit_save" class="am-btn am-btn-primary am-btn-sm">保存</button>
      		<button type="button" id="edit_cancel" class="am-btn am-btn-primary am-btn-sm">取消</button>
    	</div>
    </div>
    
  </div>
</div>
<!-- content end -->

<div class="am-modal am-modal-alert" tabindex="-1" id="alert-msg" style="display:none">
  <div class="am-modal-dialog">
    <div class="am-modal-bd">
    </div>
    <div class="am-modal-footer">
      <span class="am-modal-btn">确定</span>
    </div>
  </div>
</div>

<script>
	$(document).ready(function(){
	});
	
	$("#edit_save").click(function(){
		
		var cost_time = $("#edit_cost_time").val();
	 	if (!cost_time)
	 	{
	 		alertMsg("请输入消费时间");
	 		return;
	 	}
		
	 	var cost_amount = $("#edit_cost_amount").val();
	 	if (!cost_amount)
	 	{
	 		alertMsg("请输入消费金额");
	 		return;
	 	}
		
	 	var dataD = {};
	 	dataD.costType = $("#edit_cost_type").val();;
	 	dataD.costSubType = $("#edit_cost_subtype").val();;
	 	dataD.remark = $("#edit_cost_remark").val();;
	 	dataD.costAmount = cost_amount;
	 	dataD.costOperator = $("#edit_cost_operator").val();;
	 	dataD.costTime = cost_time;
	 	
	 	var serviceD = new Object();
	 	serviceD.serviceAction = "addCostAction";
	 	serviceD.data = JSON.stringify(dataD);
	 	
	 	$.zot.post(serviceD,function(ret){
	 		if (ret == 'F')
	 		{
	 			alertMsg("保存失败，请稍后再试");
	 		}
	 		else
	 		{
	 			//重新查询
	 			alertMsg("保存成功");
	 			$("#edit_cust_div").css("display","none");
	 			$("#qryCostBtn").click();
	 		}
	 	});
	});
	
	$("#qryCostBtn").click(function(){
		$("#edit_cust_div").css("display","none");
		
		var serviceD = new Object();
	 	serviceD.serviceAction = "qryCostAction";
	 	
	 	var operatorId = $("#qry_operator").val();
	 	if (operatorId && operatorId != -1){
	 		serviceD.operatorId=operatorId;	
	 	}
	 	var costType = $("#qry_cost_type").val();
	 	if (costType && costType != -1){
	 		serviceD.costType=costType;	
	 	}
	 	
	 	$.zot.post(serviceD,function(ret){
	 		if (ret == 'F')
	 		{
	 			alertMsg("查询失败，请稍后再试");
	 		}
	 		else
	 		{
	 			//加载数据
	 			var tbodyHtml = getTBodyHtml(ret);
	 			$("#tby").html(tbodyHtml);
	 		}
	 	});
	});
	
	$("#edit_cost_type").change(function(){
		var costType = $("#edit_cost_type").val();
		var subCostTypeObj = $('#edit_cost_subtype');
		subCostTypeObj[0].options.length = 0; 
		if (costType == 1){
			subCostTypeObj.append("<option value='0' selected>取现</option>"); 
		}
		else{
			subCostTypeObj.append("<option value='1' selected>日常餐饮</option>"); 
			subCostTypeObj.append("<option value='2'>服务项目</option>");
			subCostTypeObj.append("<option value='3'>办公用品</option>");
			subCostTypeObj.append("<option value='4'>设备</option>");
			subCostTypeObj.append("<option value='5'>员工福利</option>");
			subCostTypeObj.append("<option value='6'>运费</option>");
			subCostTypeObj.append("<option value='7'>水电费</option>");
			subCostTypeObj.append("<option value='99'>其他</option>");
		}
			
	});
	
	$("#edit_cancel").click(function(){
		$("#edit_cust_div").css("display","none");
	});
	
	$("#add_cust_btn").click(function(){
		$("#edit_cust_div").css("display","block");
	});
	
	function getTBodyHtml(costs)
	{
		var arry = [];
		if (costs && costs.length > 0)
		{
			for(var p in costs)
			{
				arry.push(getTrHtml(costs[p]));
			}
		}
		else
		{
			arry.push("<strong class=\"am-text-danger\">无符合条件的消费记录</strong>");
		}
		
		return arry.join("");
	}
	
	function getTrHtml(cost)
	{
		var arry = [];
      
		arry.push("<tr><td>");
		arry.push(cost.createTime);
		arry.push("</td><td>");
		arry.push(cost.costTime);
		arry.push("</td><td>");
		arry.push(cost.disCostType);
		arry.push("</td><td>");
		arry.push(cost.disCostSubType);
		arry.push("</td><td>");
		arry.push(cost.costAmount);
		arry.push("</td><td>");
		arry.push(cost.costBalance);
		arry.push("</td><td>");
		arry.push(cost.disOperator);
		arry.push("</td></tr>");

		return arry.join("");
	}
</script>