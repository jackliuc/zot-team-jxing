<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form id="subscribe-form" class="am-form subscribe-form">
	<div class="am-form-group">
      <label for="service-type">服务类型</label>
      <select id="service-type"
			class="am-form-field">
		</select>
    </div>
    <div id="select-car-group" class="am-form-group">
    </div>
	<button type="submit" class="am-btn am-btn-primary am-btn-block"  disabled>预约</button>
</form>
<div class="am-modal am-modal-confirm" tabindex="-1" id="confirm-submit">
  <div class="am-modal-dialog">
    <div class="am-modal-bd">
      
    </div>
    <div class="am-modal-footer">
      <span class="am-modal-btn" data-am-modal-cancel>取消</span>
      <span class="am-modal-btn" data-am-modal-confirm>确定</span>
    </div>
  </div>
</div>
<script id="subscribe-info-template" type="text/x-handlebars-template">
您将为<span class="car-number">{{carNumber}}</span>预约<span class="service-type">{{serviceType}}</span>服务。
</script>
<script id="service-option-list-template" type="text/x-handlebars-template">
{{#each services}}
<option value="{{service_id}}">{{service_name}}</option>
{{/each}}
</script>
<script id="car-option-list-template" type="text/x-handlebars-template">
{{#if firstCar}}
<label for="car-input">车牌号码</label>
<label class="am-radio first-car">
	<input type="radio" name="selectedCar" value="{{firstCar}}" data-am-ucheck checked>
	{{firstCar}}
</label>
{{#each cars}}
<label class="am-radio">
   <input type="radio" name="selectedCar" value="{{this}}" data-am-ucheck>
	{{this}}
</label>
{{/each}}
{{#if lt3}}
<label class="am-radio">
	<input type="radio" class="other-car" name="selectedCar" value="other-car" data-am-ucheck>
      其他车牌号码
</label>
<input id="other-car-input" class="other-car-input am-form-field" type="text" name="otherCar" placeholder="输入车牌号码" style="display:none;" >
{{/if}}					
{{else}}
	<div class="am-form-group">
      <label for="car-input">车牌号码</label>	
		<input id="car-input" class="car-input am-form-field" type="text" placeholder="输入车牌号码" name="otherCar" >
    </div>
{{/if}}
</script>