<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>

<div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">首页</strong> / <small>常用统计</small></div>
</div>
<br>

<div class="am-g">
  <div class="am-u-md-6">
	<!-- 每日服务项目统计 -->
	<div class="am-panel am-panel-default">
      <div class="am-panel-hd am-cf" data-am-collapse="{target: '#collapse-panel-serv'}">
      	<span id="dailyServTitle">每日服务项目统计</span><span class="am-icon-chevron-down am-fr" ></span>
      </div>
      <div id="collapse-panel-serv" class="am-in">
      </div>
	</div>
  </div>

  <!-- 每日收入统计 -->
  <div class="am-u-md-6">
	<div class="am-panel am-panel-default">
      <div class="am-panel-hd am-cf" data-am-collapse="{target: '#collapse-panel-amount'}">
      	<span id="dailyInComeTitle">每日收入统计</span><span class="am-icon-chevron-down am-fr" ></span>
      </div>
      <div id="collapse-panel-amount" class="am-in">
      </div>
	</div>
  </div>
</div>


<div class="am-g">
  <div class="am-u-md-6">
	<!-- 每日支出统计 -->
	<div class="am-panel am-panel-default">
      <div class="am-panel-hd am-cf" data-am-collapse="{target: '#collapse-panel-3'}">每日支出统计<span class="am-icon-chevron-down am-fr" ></span></div>
      <div id="collapse-panel-3" class="am-in">
        
      </div>
	</div>
  </div>

<!-- 每月收支统计 -->
  <div class="am-u-md-6">
	<div class="am-panel am-panel-default">
      <div class="am-panel-hd am-cf" data-am-collapse="{target: '#collapse-panel-4'}">每月收支统计<span class="am-icon-chevron-down am-fr" ></span></div>
      <div id="collapse-panel-4" class="am-in">
        
      </div>
	</div>
  </div>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		var dataD = new Object();
	 	dataD.serviceAction = "qryDailyOrdersCntAction";
		$.zot.post(dataD, function(dailyOrderCnt){
			if (dailyOrderCnt == "F"){
	 			return;
	 		}
			
			//动态输出每日服务统计信息
			var dailyServCnt = dailyOrderCnt.dailyServiceCnt;
			$("#dailyServTitle").text(dailyServCnt.title);
			var dailyServTblsHtml = getDailyServTbls(dailyServCnt);
			$("#collapse-panel-serv").html(dailyServTblsHtml);
			
			//动态输出每日收入统计信息
			var dailyAmountCnt = dailyOrderCnt.dailyAmountCnt;
			$("#dailyInComeTitle").text(dailyAmountCnt.title);
			var dailyAmountTblsHtml = getDailyAmountTbls(dailyAmountCnt);
			$("#collapse-panel-amount").html(dailyAmountTblsHtml);
			
		});
	});
	
	function getDailyServTbls(dailyServCnt)
	{
		var arry = [];
		arry.push("<table class=\"am-table am-table-bd am-table-bdrs am-table-striped am-table-hover\">");
		arry.push("<tbody><tr><th>服务项目</th><th>订单数</th><th>金额</th></tr>");
		$.each(dailyServCnt.services,function(n,serv) {   
			arry.push("<tr><td>");
			arry.push(serv.serviceName);
			arry.push("</td><td>");
			arry.push(serv.serviceCnt);
			arry.push("</td><td>");
			arry.push(serv.amount);
			arry.push("</td></tr>");
		});
		arry.push("</tbody></table>");
		
		return arry.join("");
	}
	
	function getDailyAmountTbls(dailyAmountCnt)
	{
		var arry = [];
		arry.push("<table class=\"am-table am-table-bd am-table-bdrs am-table-striped am-table-hover\">");
		arry.push("<tbody><tr><th>收入类别</th><th>金额</th></tr>");
		$.each(dailyAmountCnt.amounts,function(n,amount) {   
			arry.push("<tr><td>");
			arry.push(amount.payTypeName);
			arry.push("</td><td>");
			arry.push(amount.amount);
			arry.push("</td></tr>");
		});
		arry.push("</tbody></table>");
		
		return arry.join("");
	}
</script>