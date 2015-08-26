<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html class="no-js">
<%@include file="/assets/header.jsp"%>
<body>
 
	<div class="admin-content">

	<%@include file="/assets/statistic.jsp"%>
	
		<hr data-am-widget="divider" class="am-divider am-divider-default" />
		<div id="tab2">
			<div class="am-g am-margin-top">
					<div class="am-u-sm-4 am-u-md-2 am-text-right">服务类型</div>
					<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
						<select id="subtype" readonly="readonly">
							<option value="10001">洗车</option>
							<option value="10002">做漆</option>
							<option value="10003">钣金</option>
							<option value="10006">装潢</option>
						</select>
					</div>
				</div>
				<div class="am-g am-margin-top">
					<div class="am-u-sm-4 am-u-md-2 am-text-right">最佳到店时间</div>
					<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
						<input id="ordertime" type="text" class="am-input-sm"
							readonly="readonly">
					</div>
				</div>

				<div class="am-g am-margin-top">
					<div class="am-u-sm-4 am-u-md-2 am-text-right">做工时间</div>
					<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
						<input id="overtime" type="text" class="am-input-sm"
							readonly="readonly">
					</div>
					<div class="am-hide-sm-only am-u-md-6">分钟</div>
				</div>

				<div class="am-g am-margin-top">
					<div class="am-u-sm-4 am-u-md-2 am-text-right">服务价格</div>
					<div class="am-u-sm-8 am-u-md-4">
						<input id="price" type="text" class="am-input-sm"
							readonly="readonly">
					</div>
					<div class="am-hide-sm-only am-u-md-6">已享受折扣</div>

				</div>
				<div class="am-g am-margin-top">
					<button id="confirmButton" class="am-btn-block">确认</button>
				</div>
			</div>
	</div>
	
	 <div id="resultShow" class="am-u-sm-12  am-text-center" style="display:none">
                                                        预约成功，请凭借微信条形码到店服务
     </div>
	<!-- Navbar -->
	<div data-am-widget="navbar" class="am-navbar am-cf am-navbar-default "
		id="">
		<ul class="am-navbar-nav am-cf am-avg-sm-4">
			<li><a href="tel:18602552086"> <span class="am-icon-phone"></span>
					<span class="am-navbar-label">呼叫</span>
			</a></li>
			<li><a
				href="<%=request.getContextPath()%>/subscribe/self-subscribe.jsp?subtype=<%=request.getParameter("subtype")%>">
					<span class="am-icon-qrcode"></span> <span class="am-navbar-label">自助选择</span>
			</a></li>
			<li data-am-navbar-share><a href="###"> <span
					class="am-icon-share-square-o"></span> <span
					class="am-navbar-label">导航</span>
			</a></li>
			<li data-am-navbar-share><a href="###"> <span
					class="am-icon-share-square-o"></span> <span
					class="am-navbar-label">分享</span>
			</a></li>
		</ul>
	</div>
</body>

<script type="text/javascript">

function initCallback(result)
{
	$("#subtype").val(result.subType);
	$("#ordertime").val(result.subTime);
	$("#overtime").val(result.overTime);
	$("#price").val(result.price);	
}

function init()
{
	var data = new Object();
	data.serviceAction = "subscribeAction";
	data.subtype = '<%=request.getParameter("subtype")%>';

	$.zot.post(data,initCallback);
}

init();

function resultCallBack()
{
	$("#resultShow").show();
}

$("#confirmButton").click(function(){
	
 	var dataD = new Object();
		
 	dataD.serviceAction = "subscribeResultAction";
 	dataD.subtype = $("#subtype").val();
 	dataD.subtime = $("#ordertime").val();
 	dataD.overtime = $("#overtime").val();
 	dataD.price = $("#price").val();
	
	$.zot.post(dataD,resultCallBack);	
});

</script>
</html>