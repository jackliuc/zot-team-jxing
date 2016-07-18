<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>

<!-- content start -->
<div class="admin-content">
  <div class="admin-content-body">
    <div class="am-cf am-padding am-padding-bottom-0">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">系统设置</strong> / <small>商品管理</small></div>
    </div>

    <hr>

    <div class="am-g am-form">
      <div class="am-u-sm-4">
          <div class="am-u-sm-4">商品类别</div>
          <div class="am-u-sm-8">
            <select id="qry_service_catalog">
              	<option value="-1" selected>所有</option>
            	<option value="1">服务类</option>
            	<option value="2">配件类</option>
            </select>
          </div>
      </div>
      <div class="am-u-sm-4">
          <div class="am-u-sm-4">商品项目</div>
          <div class="am-u-sm-8">
            <select id="qry_serv_type_code">
              	<option value="-1" selected>所有</option>
            </select>
          </div>
      </div>
      <div class="am-u-sm-4">
         <span class="am-input-group-btn">
          	<button id="qryOfferBtn" class="am-btn am-btn-primary am-btn-block" type="button">查询</button>
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
              <th class="table-date">商品类别</th>
              <th class="table-date">商品项目</th>
              <th class="table-title">商品编码</th>
              <th class="table-title">商品名称</th>
              <th class="table-title">售价</th>
              <th class="table-title">提成模式</th>
              <th class="table-title">提成额</th>
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
    <div class="am-g" id="edit_offer_div" style="display:none">
    	<div class="am-g am-form">
	      <div class="am-u-sm-6">
	          <div class="am-u-sm-4">商品类别</div>
	          <div class="am-u-sm-8">
	            <select id="edit_service_catalog">
	              	<option value="1" selected>服务类</option>
            	<option value="2">配件类</option>
	            </select>
	          </div>
	      </div>
	      <div class="am-u-sm-6">
	          <div class="am-u-sm-4">商品项目</div>
	          <div class="am-u-sm-8">
	            <select id="edit_serv_type_code">
	              	<option value="10001" selected>快洗</option>
	              	<option value="10002">精洗</option>
	              	<option value="10003">美容</option>
	              	<option value="10006">喷漆</option>
	            </select>
	          </div>
	      </div>
	    </div>
	    <p>
	    <div class="am-g am-form">
	      <div class="am-u-sm-6">
	          <div class="am-u-sm-4">商品编码</div>
	          <div class="am-u-sm-8">
	            <input type="text" id="edit_offer_code" class="am-input-sm">
	          </div>
	      </div>
	      <div class="am-u-sm-6">
	          <div class="am-u-sm-4">商品名称</div>
	          <div class="am-u-sm-8">
	            <input type="text" id="edit_offer_name" class="am-input-sm">
	          </div>
	      </div>
	    </div>
	    <p>
	    <div class="am-g am-form">
	      <div class="am-u-sm-6">
	          <div class="am-u-sm-4">售价</div>
	          <div class="am-u-sm-8">
	            <input type="text" id="edit_offer_price" class="am-input-sm">
	          </div>
	      </div>
	      <div class="am-u-sm-6">
	          <div class="am-u-sm-4">最低售价</div>
	          <div class="am-u-sm-8">
	            <input type="text" id="edit_offer_min_price" class="am-input-sm">
	          </div>
	      </div>
	    </div>
	    <p>
	    <div class="am-g am-form">
	      <div class="am-u-sm-6">
	          <div class="am-u-sm-4">提成模式</div>
	          <div class="am-u-sm-8">
	            <select id="edit_commission_mode">
	            	<option value="1" selected>固定金额</option>
	              	<option value="2">百分比</option>
	            </select>
	          </div>
	      </div>
	      <div class="am-u-sm-6">
	          <div class="am-u-sm-4">提成额</div>
	          <div class="am-u-sm-8">
	            <input type="text" id="edit_commission_amount" class="am-input-sm">
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
	$(document).ready(function(){
	});
	
	$("#edit_save").click(function(){
		//TODO:需要针对编码进行唯一性校验	
		var offer_code = $("#edit_offer_code").val();
	 	if (!offer_code){
	 		alertMsg("请输入商品编码");
	 		return;
	 	}
	 	var offer_name = $("#edit_offer_name").val();
	 	if (!offer_name){
	 		alertMsg("请输入商品名称");
	 		return;
	 	}
	 	var offer_price = $("#edit_offer_price").val();
	 	if (!offer_price){
	 		alertMsg("请输入商品售价");
	 		return;
	 	}
	 	var offer_min_price = $("#edit_offer_min_price").val();
	 	if (!offer_min_price){
	 		alertMsg("请输入商品最低售价");
	 		return;
	 	}
	 	var cmis_amount = $("#edit_commission_amount").val();
	 	if (!cmis_amount){
	 		alertMsg("请输入提成额");
	 		return;
	 	}
		
	 	var dataD = {};
	 	dataD.offerCode = offer_code;
	 	dataD.offerName = offer_name;
	 	dataD.servCatalog = $("#edit_service_catalog").val();;
	 	dataD.servTypeCode = $("#edit_serv_type_code").val();
	 	dataD.price = offer_price;
	 	dataD.minPrice = offer_min_price;
	 	dataD.cmission_mode = $("#edit_commission_mode").val();;
	 	dataD.cmission_value = cmis_amount;
	 	
	 	var serviceD = new Object();
	 	serviceD.serviceAction = "addOfferAction";
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
	 			$("#edit_offer_div").css("display","none");
	 			$("#qryOfferBtn").click();
	 		}
	 	});
	});
	
	$("#qryOfferBtn").click(function(){
		$("#edit_offer_div").css("display","none");

	 	var dataD = {};
	 	var servCatalog = $("#qry_service_catalog").val();
	 	if (servCatalog && servCatalog != -1){
	 		dataD.servCatalog=servCatalog;	
	 	}
	 	var servTypeCode = $("#qry_serv_type_code").val();
	 	if (servTypeCode && servTypeCode != -1){
	 		dataD.servTypeCode=servTypeCode;	
	 	}
	 	var serviceD = new Object();
	 	serviceD.serviceAction = "qryOffersAction";
	 	serviceD.data = JSON.stringify(dataD);
	 	
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
		$("#edit_offer_div").css("display","none");
	});
	
	$("#add_cost_btn").click(function(){
		$("#edit_offer_div").css("display","block");
	});
	
	function getTBodyHtml(offers)
	{
		var arry = [];
		if (offers && offers.length > 0)
		{
			for(var p in offers)
			{
				arry.push(getTrHtml(offers[p]));
			}
		}
		else
		{
			arry.push("<strong class=\"am-text-danger\">无符合条件的消费记录</strong>");
		}
		
		return arry.join("");
	}
	
	function getTrHtml(offer)
	{
		var arry = [];
      
		arry.push("<tr><td>");
		arry.push(offer.servCatalogName);
		arry.push("</td><td>");
		arry.push(offer.servTypeCodeName);
		arry.push("</td><td>");
		arry.push(offer.offerCode);
		arry.push("</td><td>");
		arry.push(offer.offerName);
		arry.push("</td><td>");
		arry.push(offer.price);
		arry.push("</td><td>");
		arry.push(offer.cmissionModeName);
		arry.push("</td><td>");
		arry.push(offer.cmission_value);
		arry.push("</td><td>");
		arry.push("</td></tr>");

		return arry.join("");
	}
</script>