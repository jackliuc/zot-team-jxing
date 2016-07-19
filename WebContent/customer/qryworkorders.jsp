<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>

<!-- content start -->
<div class="admin-content">
  <div class="admin-content-body">
    <div class="am-cf am-padding am-padding-bottom-0">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">客户专区</strong> / <small>查询</small></div>
    </div>

    <hr>

    <div class="am-g am-form">
      <div class="am-u-sm-4">
          <div class="am-u-sm-4">订单状态</div>
          <div class="am-u-sm-8">
            <select id="statusSel">
              <option value="-1">所有</option>
            	<option value="1" selected>排队</option>
            	<option value="4">待评价</option>
            	<option value="6">已取消</option>
            </select>
          </div>
      </div>
      <div class="am-u-sm-4">
          <div class="am-u-sm-4">手机号码</div>
          <div class="am-u-sm-8">
            <input id="phoneno" type="text" class="am-form-field">
          </div>
      </div>
      <div class="am-u-sm-4">
        <div class="am-u-sm-4">车牌号</div>
        <div class="am-u-sm-8">
          <input id="carno" type="text" class="am-form-field" value="苏A">
        </div>
      </div>
    </div>
    
    <p/>
    <div class="am-g ">
    	<span class="am-input-group-btn">
          <button id="qryBtn" class="am-btn am-btn-primary am-btn-block" type="button">查询</button>
      </span>
    </div>

    <div class="am-g">
      <div class="am-u-sm-12">
        <form class="am-form">
          <table class="am-table am-table-striped am-table-hover table-main">
            
            <thead>
            <tr>
              <th class="table-title">订单号</th>
              <th class="table-date">创建时间</th>
              <th class="table-date">预约时间</th>
              <th class="table-title">状态</th>
              <th class="table-title">手机号</th>
              <th class="table-title">车牌号</th>
              <th class="table-title">服务项目</th>
              <th class="table-title">支付方式</th>
              <th class="table-title">实收金额</th>
              <th class="table-set">操作</th>
            </tr>
            </thead>
            
            <tbody id="tby">
            </tbody>
            
          </table>
        </form>
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
		serviceD.serviceAction = "qryWorkOrdersAction";
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
		arry.push(order.payTypeName);
		arry.push("</td><td>");
		arry.push(order.amount);
		arry.push("</td><td>");
		if (order.status == 1)
		{
			arry.push("<div class=\"am-btn-toolbar\"><div class=\"am-btn-group am-btn-group-xs\">");
			arry.push("<button onclick=\"go2checkout('");
			arry.push(order.carNo);
			arry.push("')\" class=\"am-btn am-btn-default am-btn-xs am-text-secondary \"><span class=\"am-icon-pencil-square-o\"></span>结账</button>");
			arry.push("<button onclick=\"cancelOrder('");
			arry.push(order.workOrderId);
			arry.push("')\" class=\"am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only\"><span class=\"am-icon-trash-o\"></span>取消预约</button>");
			arry.push("</div></div>");
		}
		arry.push("</td></tr>");

		return arry.join("");
	}
	
	function cancelOrder(orderId)
	{
		//debugger;
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
		
		return false;
	}
	
	function go2checkout(carno)
	{
		//将需要传递的参数值，放到主框架页面adminMain.jsp中
		$("#global_carno").val(carno);
		$("#optContent").load("customer/checkout.jsp");
		
		return false;
	}
</script>