<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>

<div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">店面专区</strong> / <small>查询</small></div>
</div>

<script src="assets/js/handsontable.full.min.js"></script>
<link href="assets/css/handsontable.full.min.css" type="text/css" rel="stylesheet">
<script type="text/javascript" class="init">	 	

    var tableData;
    var changedRows = [];
    
    $("#hot").handsontable({
  	  data: tableData, // see the Data tab
  	  rowHeaders: true,
  	  colHeaders: true,
  	  filters:true,
  	  dropdownMenu: ['filter_by_value'],
  	  stretchH: "all",
  	  width: 1000,
  	  autoWrapRow: true,
  	  height: 441,
  	  maxRows: 22,
  	  colHeaders: ['id', '日期', '类型', '费用类型', '开销金额', '经手人', '备注'],
  	  columns: [
  	      {
  	          data: "costId",
  	          width: 1,
  	          renderer: idRenderer
  	      },
  	      {
  	          data: "costTime",
  	          type: "date",
  	          dateFormat: 'YYYY-MM-DD',
  	          correctFormat: true,
  	          width: 40
  	      },
  	      {
  	          data: "costType",
  	       	  type: "dropdown",
  	       	  source: ['支出', '收入'],
  	          width: 30
  	      },
  	      {
  	          data: "costSubType",
  	       	  type: "text",
  	          width: 50
  	      },
  	      {
  	          data: "costAmount",
  	          type: "numeric",
  	          format: '0,0.00',
  	          width: 40
  	      },
  	      {
  	          data: "costOperator",
  	          type: "text",
  	          width: 30
  	      },
  	      {
  	          data: "remark",
  	          type: "text",
  	          width: 100
  	      }
  	  ],
  	  afterChange: function(changes, source) {
  		//console.log(changes);
  		//console.log(source);
  	  	if (changes) {
  	  		var added = false;
  	  		for (var i=0; i<changedRows.length; i++) {
  	  			if (changedRows[i] === changes[0][0]) {
  	  				added = true;
  	  			}
  	  		}
  	  		if (!added) {
	    		  changedRows.push(changes[0][0]);
  	  		}
  	  	}
  	  }
  	});
    
    
	function idRenderer(instance, td, row, col, prop, value, cellProperties) {
		//var escaped = Handsontable.helper.stringify(value);
		td.innerHTML = "<input type='hidden' value='"+value+"'>";

		return td;
	}

	var hot = $("#hot").handsontable('getInstance');

	function afterLoadData(result) {
		console.log(result);
		tableData = result;

		hot.loadData(tableData);
		hot.loadData(tableData);
		hot.render();
	}

	function afterSaveData(result) {
		changedRows = [];

		tableData = result;
		hot.loadData(tableData);
		hot.loadData(tableData);
		hot.render();
	}

	$("#saveChanges").click(function() {
		//$("#saveChanges").attr('disabled', 'disabled');

		console.log(changedRows);

		var changes = [];
		for (var i = 0; i < changedRows.length; i++) {
			changes.push(arrayToObject(hot.getDataAtRow(changedRows[i])));
		}

		//console.log(changes);

		var data = new Object();
		data.serviceAction = "addCostAction";
		data.data = JSON.stringify(changes);
		$.zot.post(data, afterSaveData);
	});

	function arrayToObject(rowData) {
		var index = 0;
		var rowObj = {};
		rowObj.costId = rowData[index++];
		rowObj.costTime = rowData[index++];
		rowObj.costType = rowData[index++];
		rowObj.costSubType = rowData[index++];
		rowObj.costAmount = rowData[index++];
		rowObj.costOperator = rowData[index++];
		rowObj.remark = rowData[index++];

		return rowObj;
	}

	function loadData() {
		var data = new Object();

		data.serviceAction = "listCostAction";
		$.zot.post(data, afterLoadData);
	}

	$("#createCost").click(function() {
		//console.log(tableData);
		tableData.push({});
		hot.loadData(tableData);
		hot.loadData(tableData);
		hot.render();
	});

	$(function() {
		loadData();
	});
</script>
	<div>
		<button id="createCost">新增记录</button>
		<button id="saveChanges">保存</button>
	</div>
	<div id="hot" class="hot handsontable htRowHeaders htColumnHeaders"></div>
	