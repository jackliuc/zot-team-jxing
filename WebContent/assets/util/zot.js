jQuery.zot = {    

post:function(data,callback) {   
	var url = "/zot/busdata";
	$.post(url,
			 data,
			 function(result){
		callback(result);
	},"json");	      
} ,        
ajax:function(data,successCallBack,errorCallBack){
	var url = "/zot/busdata";
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
	
}       
};  
