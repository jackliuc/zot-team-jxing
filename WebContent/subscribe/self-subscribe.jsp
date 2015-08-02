<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html class="no-js">
<%@include file="/assets/header.jsp" %>
<body>
	
	  <div class="admin-content">

    <ul class="am-avg-sm-1 am-avg-md-4 am-margin am-padding am-text-center admin-content-list ">
      <li><a href="#" class="am-text-success"><span class="am-icon-btn am-icon-file-text"></span><br/>排队数<br/>13</a></li>
      <li><a href="#" class="am-text-warning"><span class="am-icon-btn am-icon-briefcase"></span><br/>出车速度<br/>15分钟</a></li>
      <li><a href="#" class="am-text-danger"><span class="am-icon-btn am-icon-recycle"></span><br/>已服务数<br/>80082</a></li>
    </ul>
	
	<hr data-am-widget="divider" style="" class="am-divider am-divider-default"/>
	
	   <form class="am-form" action="subscribe-result.jsp">
	   
	    <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">
             预约时间
            </div>
             <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
              <input name="ordertime" type="datetime-local"/>
            </div>
          </div>

          <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">
              服务类型
            </div>
            <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
               <select>
				  <option value="1">洗车</option>
				  <option value="2">做漆</option>
				  <option value="3">钣金</option>
				  <option value="4">装潢</option>
				</select>
            </div>
          </div>

	   
		  <div class="am-margin">
		    <button type="submit" class="am-btn am-btn-primary am-btn-xs">确认</button>
		  </div>
        </form>
    </div>

<%@include file="/assets/footer.jsp" %>

</body>
</html>