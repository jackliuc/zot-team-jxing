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
            <select id="statusSel">
              	<option value="-1">所有</option>
            	<option value="0" selected>支出</option>
            	<option value="1">收入</option>
            </select>
          </div>
      </div>
      <div class="am-u-sm-4">
          <div class="am-u-sm-4">经办人</div>
          <div class="am-u-sm-8">
            <select id="employsSel">
              	<option value="-1">所有</option>
            </select>
          </div>
      </div>
      <div class="am-u-sm-4">
         <span class="am-input-group-btn">
          	<button id="qryBtn" class="am-btn am-btn-primary am-btn-block" type="button">查询</button>
         </span>
      </div>
    </div>
    
    <p/>
    
    <div class="am-g">
      <div class="am-u-sm-12 am-u-md-6">
          <div class="am-btn-toolbar">
            <div class="am-btn-group am-btn-group-sm">
              <button type="button" class="am-btn am-btn-default"><span class="am-icon-plus"></span>新增</button>
            </div>
          </div>
      </div>
      <div class="am-u-sm-12">
        <form class="am-form">
          <table class="am-table am-table-striped am-table-hover table-main">
            
            <thead>
            <tr>
              <th class="table-date">消费时间</th>
              <th class="table-date">创建时间</th>
              <th class="table-title">消费类别</th>
              <th class="table-title">消费细类</th>
              <th class="table-title">消费金额</th>
              <th class="table-title">消费后余额</th>
              <th class="table-title">经办人</th>
              <th class="table-title">操作</th>
            </tr>
            </thead>
            
            <tbody id="tby">
              <tr>
                <td>2016-06-18</td>
                <td>2016-06-18</td>
                <td>支出</td>
                <td>日常餐饮</td>
                <td>219.8</td>
                <td>3280.6</td>
                <td>陈雪桃</td>
                <td>
                  <div class="am-btn-toolbar">
                    <div class="am-btn-group am-btn-group-xs">
                      <input type="hidden" value="list_cost_id"/>
                      <button class="am-btn am-btn-default am-btn-xs am-text-secondary">
                      	<span class="am-icon-pencil-square-o"></span>编辑
                      </button>
                      <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only">
                      	<span class="am-icon-trash-o"></span>删除
                      </button>
                    </div>
                  </div>
                </td>
              </tr>
            </tbody>
            
          </table>
        </form>
      </div>
    </div>
    
    <!-- 新增，编辑区域 -->
    <div class="am-g" style="display:block">
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
	              	<option value="7">其他</option>
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
	            	<option value="1" selected>李金龙</option>
	              	<option value="2">陈雪桃</option>
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

<script>
	$("#qryBtn").click(function(){
		
		var status = $("#statusSel").val();
		
		var carno = $("#carno").val();
	 	if (carno 
	 		&& carno.length > 2
	 		&& carno.length != 7)
	 	{
	 		alertMsg("请输入7位车牌号");
	 		return;
	 	}
		
	 	var phoneno = $("#phoneno").val();
	 	if (phoneno && phoneno.length != 11)
	 	{
	 		alertMsg("请输入11位手机号码");
	 		return;
	 	}
		
		var serviceD = new Object();
		serviceD.serviceAction = "qryBookingOrdersAction";
	 	serviceD.status = status;
	 	if (phoneno && phoneno.length == 11)
	 	{
	 		serviceD.phoneno = phoneno;
	 	}
	 	if (carno && carno.length == 7)
	 	{
	 		serviceD.carno = carno;
	 	}
	 	
	 	$.zot.post(serviceD,function(ret){
	 		if (ret == 'F')
	 		{
	 			alertMsg("查询失败，请稍后再试");
	 		}
	 		else
	 		{
	 			//动态构造表格行
	 			$("#tby").html(getTBodyHtml(ret));
	 		}
	 	});
	});
	
	function getTBodyHtml(orders)
	{
		var arry = [];
		if (orders && orders.length > 0)
		{
			for(var p in orders)
			{
				arry.push(getTrHtml(orders[p]));
			}
		}
		else
		{
			arry.push("<strong class=\"am-text-danger\">无符合条件的订单</strong>");
		}
		
		return arry.join("");
	}
	
	function getTrHtml(order)
	{
		var arry = [];
      
		arry.push("<tr><td>");
		arry.push(order.workOrderId);
		arry.push("</td><td class=\"am-hide-sm-only\">");
		arry.push(order.disCreateTime);
		arry.push("</td><td class=\"am-hide-sm-only\">");
		arry.push(order.disOrderTime);
		arry.push("</td><td>");
		arry.push(order.statusDes);
		arry.push("</td><td class=\"am-hide-sm-only\">");
		arry.push(order.phoneno);
		arry.push("</td><td class=\"am-hide-sm-only\">");
		arry.push(order.carNo);
		arry.push("</td><td class=\"am-hide-sm-only\">");
		arry.push(order.serviceName);
		arry.push("</td><td>");
		if (order.status == 1)
		{
			arry.push("<div class=\"am-btn-toolbar\"><div class=\"am-btn-group am-btn-group-xs\"><button onclick=\"cancelOrder('");
			arry.push(order.workOrderId);
			arry.push("')\" class=\"am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only\"><span class=\"am-icon-trash-o\"></span>取消预约</button></div></div>");
		}
		arry.push("</td></tr>");

		return arry.join("");
	}
	
	function cancelOrder(orderId)
	{
		debugger;
		var dataD = new Object();

		dataD.serviceAction = "cancelWorkOrderAction";
		dataD.orderId = orderId;
		$.zot.post(dataD,function(ret){
			if (ret == 'S')
			{
				alertMsg('成功取消预约');
				$("#qryBtn").click();
			}
			else
			{
				alertMsg('取消预约失败，请稍后再试');	
			}
		});	
	}
</script>