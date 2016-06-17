<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>

<div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">客户专区</strong> / <small>查询</small></div>
</div>

<script src="assets/js/handsontable.full.min.js"></script>
<link href="assets/css/handsontable.full.min.css" type="text/css" rel="stylesheet">
<script type="text/javascript" class="init">
	var dataObj = [
	               {
	                   "id": 1,
	                   "flag": "EUR",
	                   "currencyCode": "EUR",
	                   "currency": "Euro",
	                   "level": 0.9033,
	                   "units": "EUR / USD",
	                   "asOf": "08/19/2015",
	                   "onedChng": 0.0026
	               },
	               {
	                   "id": 2,
	                   "flag": "JPY",
	                   "currencyCode": "JPY",
	                   "currency": "Japanese Yen",
	                   "level": 124.387,
	                   "units": "JPY / USD",
	                   "asOf": "08/19/2015",
	                   "onedChng": 0.0001
	               },
	               {
	                   "id": 3,
	                   "flag": "GBP",
	                   "currencyCode": "GBP",
	                   "currency": "Pound Sterling",
	                   "level": 0.6396,
	                   "units": "GBP / USD",
	                   "asOf": "08/19/2015",
	                   "onedChng": 0
	               },
	               {
	                   "id": 4,
	                   "flag": "CHF",
	                   "currencyCode": "CHF",
	                   "currency": "Swiss Franc",
	                   "level": 0.9775,
	                   "units": "CHF / USD",
	                   "asOf": "08/19/2015",
	                   "onedChng": 0.0008
	               },
	               {
	                   "id": 5,
	                   "flag": "CAD",
	                   "currencyCode": "CAD",
	                   "currency": "Canadian Dollar",
	                   "level": 1.3097,
	                   "units": "CAD / USD",
	                   "asOf": "08/19/2015",
	                   "onedChng": -0.0005
	               },
	               {
	                   "id": 6,
	                   "flag": "AUD",
	                   "currencyCode": "AUD",
	                   "currency": "Australian Dollar",
	                   "level": 1.3589,
	                   "units": "AUD / USD",
	                   "asOf": "08/19/2015",
	                   "onedChng": 0.002
	               },
	               {
	                   "id": 7,
	                   "flag": "NZD",
	                   "currencyCode": "NZD",
	                   "currency": "New Zealand Dollar",
	                   "level": 1.5218,
	                   "units": "NZD / USD",
	                   "asOf": "08/19/2015",
	                   "onedChng": -0.0036
	               },
	               {
	                   "id": 8,
	                   "flag": "SEK",
	                   "currencyCode": "SEK",
	                   "currency": "Swedish Krona",
	                   "level": 8.528,
	                   "units": "SEK / USD",
	                   "asOf": "08/19/2015",
	                   "onedChng": 0.0016
	               },
	               {
	                   "id": 9,
	                   "flag": "NOK",
	                   "currencyCode": "NOK",
	                   "currency": "Norwegian Krone",
	                   "level": 8.2433,
	                   "units": "NOK / USD",
	                   "asOf": "08/19/2015",
	                   "onedChng": 0.0008
	               },
	               {
	                   "id": 10,
	                   "flag": "BRL",
	                   "currencyCode": "BRL",
	                   "currency": "Brazilian Real",
	                   "level": 3.4806,
	                   "units": "BRL / USD",
	                   "asOf": "08/19/2015",
	                   "onedChng": -0.0009
	               },
	               {
	                   "id": 11,
	                   "flag": "CNY",
	                   "currencyCode": "CNY",
	                   "currency": "Chinese Yuan",
	                   "level": 6.3961,
	                   "units": "CNY / USD",
	                   "asOf": "08/19/2015",
	                   "onedChng": 0.0004
	               },
	               {
	                   "id": 12,
	                   "flag": "RUB",
	                   "currencyCode": "RUB",
	                   "currency": "Russian Rouble",
	                   "level": 65.598,
	                   "units": "RUB / USD",
	                   "asOf": "08/19/2015",
	                   "onedChng": 0.0059
	               },
	               {
	                   "id": 13,
	                   "flag": "INR",
	                   "currencyCode": "INR",
	                   "currency": "Indian Rupee",
	                   "level": 65.3724,
	                   "units": "INR / USD",
	                   "asOf": "08/19/2015",
	                   "onedChng": 0.0026
	               },
	               {
	                   "id": 14,
	                   "flag": "TRY",
	                   "currencyCode": "TRY",
	                   "currency": "New Turkish Lira",
	                   "level": 2.8689,
	                   "units": "TRY / USD",
	                   "asOf": "08/19/2015",
	                   "onedChng": 0.0092
	               },
	               {
	                   "id": 15,
	                   "flag": "THB",
	                   "currencyCode": "THB",
	                   "currency": "Thai Baht",
	                   "level": 35.5029,
	                   "units": "THB / USD",
	                   "asOf": "08/19/2015",
	                   "onedChng": 0.0044
	               },
	               {
	                   "id": 16,
	                   "flag": "IDR",
	                   "currencyCode": "IDR",
	                   "currency": "Indonesian Rupiah",
	                   "level": 13.83,
	                   "units": "IDR / USD",
	                   "asOf": "08/19/2015",
	                   "onedChng": -0.0009
	               },
	               {
	                   "id": 17,
	                   "flag": "MYR",
	                   "currencyCode": "MYR",
	                   "currency": "Malaysian Ringgit",
	                   "level": 4.0949,
	                   "units": "MYR / USD",
	                   "asOf": "08/19/2015",
	                   "onedChng": 0.001
	               },
	               {
	                   "id": 18,
	                   "flag": "MXN",
	                   "currencyCode": "MXN",
	                   "currency": "Mexican New Peso",
	                   "level": 16.4309,
	                   "units": "MXN / USD",
	                   "asOf": "08/19/2015",
	                   "onedChng": 0.0017
	               },
	               {
	                   "id": 19,
	                   "flag": "ARS",
	                   "currencyCode": "ARS",
	                   "currency": "Argentinian Peso",
	                   "level": 9.2534,
	                   "units": "ARS / USD",
	                   "asOf": "08/19/2015",
	                   "onedChng": 0.0011
	               },
	               {
	                   "id": 20,
	                   "flag": "DKK",
	                   "currencyCode": "DKK",
	                   "currency": "Danish Krone",
	                   "level": 6.7417,
	                   "units": "DKK / USD",
	                   "asOf": "08/19/2015",
	                   "onedChng": 0.0025
	               },
	               {
	                   "id": 21,
	                   "flag": "ILS",
	                   "currencyCode": "ILS",
	                   "currency": "Israeli New Sheqel",
	                   "level": 3.8262,
	                   "units": "ILS / USD",
	                   "asOf": "08/19/2015",
	                   "onedChng": 0.0084
	               },
	               {
	                   "id": 22,
	                   "flag": "PHP",
	                   "currencyCode": "PHP",
	                   "currency": "Philippine Peso",
	                   "level": 46.3108,
	                   "units": "PHP / USD",
	                   "asOf": "08/19/2015",
	                   "onedChng": 0.0012
	               }
	           ];
	           var currencyCodes =[
	               "EUR",
	               "JPY",
	               "GBP",
	               "CHF",
	               "CAD",
	               "AUD",
	               "NZD",
	               "SEK",
	               "NOK",
	               "BRL",
	               "CNY",
	               "RUB",
	               "INR",
	               "TRY",
	               "THB",
	               "IDR",
	               "MYR",
	               "MXN",
	               "ARS",
	               "DKK",
	               "ILS",
	               "PHP"
	           ] 
	         
	        
	             var hotElement = document.getElementById('hot');
	             var hot = new Handsontable(hotElement, {
	               data: dataObj, // see the Data tab
	               rowHeaders: true,
	               colHeaders: true,
	               filters:true,
	               dropdownMenu: ['filter_by_value'],
	               stretchH: "all",
	               width: 1000,
	               autoWrapRow: true,
	               height: 441,
	               maxRows: 22,
	               colHeaders: ['车牌号', '姓名', '手机号','性别',"车型","是否在服务","最近服务时间"],
	               columns: [
	                   {
	                       data: "id",
	                       type: "text",
	                       width: 40,
	                   },
	                   {
	                       data: "flag",
	                   	   type: "text"
	                   },
	                   {
	                       data: "currencyCode",
	                       type: "numeric"
	                   },
	                   {
	                       data: "currency",
	                       type: "text"
	                   },
	                   {
	                       data: "level",
	                       type: "text"
	                       
	                   },
	                   {
	                       data: "units",
	                       type: "text"
	                   },
	                   {
	                       data: "lastdate",
	                       type: "text"
	                   }
	               ]
	              
	             });

	</script>
	
	<div id="hot" class="hot handsontable htRowHeaders htColumnHeaders"></div>
	