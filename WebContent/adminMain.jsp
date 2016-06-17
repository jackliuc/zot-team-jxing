<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>壹号车服务系统</title>
  <meta name="description" content="这是一个 index 页面">
  <meta name="keywords" content="index">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="icon" type="image/png" href="assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="assets/css/amazeui.min.css"/>
  <link rel="stylesheet" href="assets/css/admin.css">
  <!--[if (gte IE 9)|!(IE)]><!-->
<script src="assets/js/jquery.min.js"></script>
<!--<![endif]-->
<script src="assets/js/amazeui.min.js"></script>
<script src="assets/js/app.js"></script>
<script src="<%=request.getContextPath()%>/assets/util/zot.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>

<script type="text/javascript">
$(document).ready(function(){

  $("#firstpage").click(function(){
  	$("#optContent").load("analyse/storeStatistic.jsp");
   });
  
  $("#checkin").click(function(){
	  	$("#optContent").load("customer/checkin.jsp");
	   });
  
  $("#checkout").click(function(){
	  	$("#optContent").load("customer/checkout.jsp");
	   });
  
  $("#customerinfo").click(function(){
	  	$("#optContent").load("customer/qrybooking.jsp");
	   });
  
  //店面专区
  $("#costdetail").click(function(){
	  	$("#optContent").load("shop/costdetail.jsp");
	   });
  
  
  });

</script>
</head>
<body>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
  以获得更好的体验！</p>
<![endif]-->

<header class="am-topbar admin-header">
  <div class="am-topbar-brand">
    <strong>壹号车</strong> 
  </div>

  <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>

  <div class="am-collapse am-topbar-collapse" id="topbar-collapse">
    <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
      <li class="am-hide-sm-only"><a href="javascript:;" id="admin-fullscreen"><span class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>
    </ul>
  </div>
</header>
<div class="am-cf admin-main">
  <!-- sidebar start -->
  <div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
    <div class="am-offcanvas-bar admin-offcanvas-bar">
      <ul class="am-list admin-sidebar-list">
        <li><a href="#" id="firstpage"><span class="am-icon-home" > 首页</span></a></li>
        <li class="admin-parent">
          <a class="am-cf" data-am-collapse="{target: '#collapse-nav'}"><span class="am-icon-file"></span> 客户专区 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
          <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav">
            <li><a href="#" id="checkin" class="am-cf"><span class="am-icon-check"></span> 登 记 </a></li>
            <li><a href="#" id="checkout"><span class="am-icon-puzzle-piece"></span> 结 账 </a></li>
            <li><a href="#" id="customerinfo"><span class="am-icon-th"></span> 查询  </a></li>
          </ul>
        </li>
          <li class="admin-parent">
          <a class="am-cf" data-am-collapse="{target: '#collapse-nav2'}"><span class="am-icon-file"></span> 门店专区 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
          <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav2">
            <li><a href="#" id="costdetail" class="am-cf"><span class="am-icon-check"></span> 消费 </a></li>
            <li><a href="#" id="employ"><span class="am-icon-puzzle-piece"></span> 员工 </a></li>
         </ul>
        </li>
        <li><a href="admin-form.html"><span class="am-icon-pencil-square-o"></span> 系统日志</a></li>
        <!-- li><a href="#"><span class="am-icon-sign-out"></span> 注销</a></li> -->
      </ul>

      <div class="am-panel am-panel-default admin-sidebar-panel">
        <div class="am-panel-bd">
          <p><span class="am-icon-bookmark"></span> 公告</p>
          <p>专业 体贴 极致 简单</p>
        </div>
      </div>

      <div class="am-panel am-panel-default admin-sidebar-panel">
        <div class="am-panel-bd">
          <p><span class="am-icon-tag"></span> </p>
          <p>欢迎使用壹号车服务系统！</p>
        </div>
      </div>
    </div>
  </div>
  <!-- sidebar end -->

  <!-- content start -->
  <div id="optContent" class="admin-content" style="overflow-y :auto">
        <%@include file="analyse/storeStatistic.jsp" %>
  </div>

  <!-- content end -->

</div>


<a href="#" class="am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}">
  <span class="am-icon-btn am-icon-th-list"></span>
</a>

<footer>
  <hr>
  <p class="am-padding-left">© 2014 AllMobilize, Inc. Licensed under MIT license.</p>
</footer>

<!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="assets/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->

<div class="am-modal am-modal-alert" tabindex="-1" id="alert-msg">
<div class="am-modal-dialog">
  <div class="am-modal-bd">
  </div>
  <div class="am-modal-footer">
    <span class="am-modal-btn">确定</span>
  </div>
</div>
</div>

<input type="hidden" id="contextPath" value="<%= request.getContextPath()%>" /> 
</body>
</html>
