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
	}  
};  
