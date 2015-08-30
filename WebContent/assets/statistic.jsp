<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<ul class="am-avg-sm-3 am-margin am-padding am-text-center admin-content-list ">
      <li><a href="#" class="am-text-success"><span class="am-icon-btn am-icon-file-text"></span><br/>排队数<br/>
      <label id="waitCnt"></label></a>
      </li>
      <li><a href="#" class="am-text-warning"><span class="am-icon-btn am-icon-briefcase"></span><br/>出车速度<br/>
      <label id="serviceBiz"></label>Min</a></li>
      <li><a href="#" class="am-text-danger"><span class="am-icon-btn am-icon-recycle"></span><br/>已服务数<br/>
      <label id="allServiceCnt"></label></a></li>
 </ul>
 
 <script type="text/javascript">

function initHeaderCallBack(result)
{
	result = new Object();
	result.waitCnt = 6;
	result.serviceBiz = 20;
	result.allServiceCnt = 3888;
	$("#waitCnt").html(result.waitCnt);
	$("#serviceBiz").html(result.serviceBiz);
	$("#allServiceCnt").html(result.allServiceCnt);

}

function initHeader()
{
	var data = new Object();
	data.serviceAction = "subscribeAction";
	data.subtype = '<%=request.getParameter("subtype")%>';

	//$.zot.post(data,initHeaderCallBack);
}

initHeaderCallBack();




</script>