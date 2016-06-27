$.ajaxSetup({  
    async : false  
});

jQuery.zot = {    

post:function(data,callback) {   
	var context = $("#contextPath").val();
	var url = context+"/busdata";
	$.post(url,
			 data,
			 function(result){
		callback(result);
	},"json");	      
} ,        
ajax:function(data,successCallBack,errorCallBack){
	var context = $("#contextPath").val();
	var url = context+"/busdata";
	$.ajax({
 		type : "post",
 		async : false,  //同步请求
 		url : url,
 		data : dataD,
 		timeout:1000,
 		success:function(result){
 			$("#resultShow").show();
 		},
 		error: function() {
 	      
 	    }
 	});
},

toUTF8:function(str) {  
	   var out, i, len, c;  
	   out = "";  
	   len = str.length;  
	   for(i = 0; i < len; i++) {  
	   c = str.charCodeAt(i);  
	   if ((c >= 0x0001) && (c <= 0x007F)) {  
	       out += str.charAt(i);  
	   } else if (c > 0x07FF) {  
	        out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));  
	        out += String.fromCharCode(0x80 | ((c >>  6) & 0x3F));  
	        out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));  
	    } else {  
	        out += String.fromCharCode(0xC0 | ((c >>  6) & 0x1F));  
	        out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));  
	    }  
	    }  
	    return out;  
	},
	
getUserId:function(code){
		var dataD = new Object();

		dataD.serviceAction = "qryUserIdAction";
		dataD.code = code;
		var userId;
		$.zot.post(dataD,function(ret){
			userId = ret;
				});	
		return userId;
	},
	
getAuthMenuURL:function(menuId){
		var dataD = new Object();

		dataD.serviceAction = "qryAuthMenuURLAction";
		dataD.menuId = menuId;
		var menuURL;
		$.zot.post(dataD,function(ret){
			menuURL = ret;
				});	
		return menuURL;
	},

getParameter:function(url, name){
		var value;
		var pindex = url.indexOf("?");
	    if (pindex != -1) {
	        var str = url.substr(pindex + 1);
	        var params = str.split("&");
	        for(var i = 0; i < params.length; i++) {
	        	var paramArry = params[i].split("=");
	        	if (paramArry[0] == name){
	        		value = paramArry[1];
	        		break;
	        	}
	        }
	    }
	    return value;
	}
};

function alertMsg(message)
{
	$('.am-modal-bd').text(message);
	var options = new Object();
	options.height = '120px';
	options.width = '300px';
	$('#alert-msg').modal(options);
};
