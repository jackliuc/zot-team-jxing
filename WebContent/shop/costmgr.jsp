<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>

<!-- content start -->
<div class="admin-content">
  <div class="admin-content-body">
    <div class="am-cf am-padding am-padding-bottom-0">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">门店专区</strong> / <small>消费</small></div>
    </div>

    <hr>

    <div class="am-g am-form">
      <div class="am-u-sm-4">
          <div class="am-u-sm-4">消费类别</div>
          <div class="am-u-sm-8">
            <select id="qry_cost_type">
              	<option value="-1" selected>所有</option>
            	<option value="0">支出</option>
            	<option value="1">收入</option>
            </select>
          </div>
      </div>
      <div class="am-u-sm-4">
          <div class="am-u-sm-4">经办人</div>
          <div class="am-u-sm-8">
            <select id="qry_operator">
              	<option value="-1" selected>所有</option>
              	<option value="600000002">陈雪桃</option>
	            <option value="600000001">李金龙</option>
            </select>
          </div>
      </div>
      <div class="am-u-sm-4">
         <span class="am-input-group-btn">
          	<button id="qryCostBtn" class="am-btn am-btn-primary am-btn-block" type="button">查询</button>
         </span>
      </div>
    </div>
    
    <p/>
    
    <div class="am-g">
      <div class="am-u-sm-12 am-u-md-6">
          <div class="am-btn-toolbar">
            <div class="am-btn-group am-btn-group-sm">
              <button id="add_cost_btn" type="button" class="am-btn am-btn-primary">
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
              <th class="table-date">创建时间</th>
              <th class="table-date">消费时间</th>
              <th class="table-title">消费类别</th>
              <th class="table-title">消费细类</th>
              <th class="table-title">消费金额</th>
              <th class="table-title">消费后余额</th>
              <th class="table-title">经办人</th>
            </tr>
            </thead>
            
            <tbody id="tby">
            </tbody>
            
          </table>
        </form>
      </div>
    </div>
    
    <!-- 新增，编辑区域 -->
    <div class="am-g" id="edit_cost_div" style="display:none">
    	<div class="am-g am-form">
	      <div class="am-u-sm-6">
	          <div class="am-u-sm-4">消费类别</div>
	          <div class="am-u-sm-8">
	            <select id="edit_cost_type">
	              	<option value="0" selected>支出</option>
	              	<option value="1">收入</option>
	            </select>
	          </div>
	      </div>
	      <div class="am-u-sm-6">
	          <div class="am-u-sm-4">消费细类</div>
	          <div class="am-u-sm-8">
	            <select id="edit_cost_subtype">
	              	<option value="1" selected>日常餐饮</option>
	              	<option value="2">服务项目</option>
	              	<option value="3">办公用品</option>
	              	<option value="4">设备</option>
	              	<option value="5">员工福利</option>
	              	<option value="6">运费</option>
	              	<option value="7">水电费</option>
	              	<option value="99">其他</option>
	            </select>
	          </div>
	      </div>
	    </div>
	    <p/>
	    <div class="am-g am-form">
	      <div class="am-u-sm-6">
	          <div class="am-u-sm-4">消费时间</div>
	          <div class="am-u-sm-8">
	            <div class="am-form-group am-form-icon">
                  <i class="am-icon-calendar"></i>
                  <input id="edit_cost_time" type="date" class="am-form-field am-input-sm" placeholder="消费日期">
                </div>
	          </div>
	      </div>
	      <div class="am-u-sm-6">
	          <div class="am-u-sm-4">消费金额</div>
	          <div class="am-u-sm-8">
	            <input type="text" id="edit_cost_amount" class="am-input-sm">
	          </div>
	      </div>
	    </div>
	    
	    <div class="am-g am-form">
	      <div class="am-u-sm-6">
	          <div class="am-u-sm-4">经办人</div>
	          <div class="am-u-sm-8">
	            <select id="edit_cost_operator">
	            	<option value="600000002" selected>陈雪桃</option>
	              	<option value="600000001">李金龙</option>
	            </select>
	          </div>
	      </div>
	      <div class="am-u-sm-6">
	          <div class="am-u-sm-4">备注</div>
	          <div class="am-u-sm-8">
	            <input type="text" id="edit_cost_remark" class="am-input-sm">
	          </div>
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
	 			$("#edit_cost_div").css("display","none");
	 			$("#qryCostBtn").click();
	 		}
	 	});
	});
	
	$("#qryCostBtn").click(function(){
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
		$("#edit_cost_div").css("display","none");
	});
	
	$("#add_cost_btn").click(function(){
		$("#edit_cost_div").css("display","block");
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