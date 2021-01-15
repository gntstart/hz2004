//add by zjm 20200518 评价器方法  
var xhr;
function pjq(cmd,url,hzywid){
	 var address = url;//$("#ipinput").val();
	  var url = "";
	  if(address != "")
	  {
		  url = "http://"+address+":10791/inf/"+cmd;
		  netUrl = "http://"+address+":10791/inf/CK";
	  }
	  if(url == "")
	  {
		  alert("错误的IP地址");
		  return;
	  }
	$.post(url,'{}',function(data){	
//		data = "{rst:'OK',code:1,data:4}";
		var updateData = eval("("+data+")");
		if(updateData&&updateData.rst){
			if(updateData.rst=='OK'){
				if(updateData.code=='1'){
					//alert(hzywid+updateData.data);
					Gnt.updateHzywPj(hzywid,updateData.data);
					alert("评价成功!")
				}else{
					alert("错误码为："+updateData.code);
				}
			}else if(updateData.rst=='err'){
				alert(updateData.text);
			}
		}
	});
}

function funCMD(cmd)
  {
	  var address = '127.0.0.1';//$("#ipinput").val();
	  var url = "";
	  if(address != "")
	  {
		  url = "http://"+address+":10791/inf/"+cmd;
	  }
	  if(url == "")
	  {
		  alert("错误的IP地址");
		  return;
	  }
	  
      //var prgs = $(".PRG_"+cmd).find("input");  //获取用户输入的参数
      var obj = {};	//准备参数
	  switch(cmd)
	  {//填写参数
		 case "SI": //签到A指令参数获取
		     obj.num = prgs.eq(0).val();
			 if(obj.num != "")
			 {
				
			 }
			 else{
				 alert("请输入工号")
			 }
		     break
		 case "SS":
	         obj.num = prgs.eq(0).val();
			 if(obj.num != "")
			 {
				 obj.name = prgs.eq(1).val();
				 obj.pos = prgs.eq(2).val();
				 obj.org = prgs.eq(3).val();
				 obj.equ = prgs.eq(4).val();
				 obj.star = prgs.eq(5).val();
				 obj.inf = prgs.eq(6).val();
			 }
			 else{
				 alert("请输入工号") 
			 }
			 break;	
		case "MS":
		case "VB":
		     obj.msg = prgs.eq(0).val();
			 if(obj.msg != "")
			 {
				
			 }
			 else{
				 alert("请输入内容")
			 }
		     break;
		case "SD":
		case "AD":
		     obj.file = prgs.eq(0).val();
			 if(obj.file != "")
			 {
				
			 }
			 else{
				 alert("请输入文件名")
			 }
		     break;
		case "SO":
		case "EV":
		case "ES":
		case "GS":
		case "PS":
		case "WT":
		case "HN":
		case "CK":
		     //无参数
		     break;		 				  			 			 		     
	  }
//		Gnt.submit(
//				{}, 
//				url, 
//				"正在处理迁入业务信息，请稍后...", 
//				function(jsonData,params){
//					alert(11);
//				},function(jsonData,params){
//					alert(22);
//				})
	  
	  //Ext.util.JSON.decode
	  postCmd(url,{}); 
//	  postCmd(url,JSON.stringify(obj));  //执行指令发送
//	  $.ajax({
//	        url: url,
//	        contentType:'json',
//	        data: {},
//	        success: function (data) {
//	        	alert(11);
//	        },
//	        error: function (data) {
//	        	alert(22);
//	        }
//	    });
	  
  }
  
  function postCmd(url,prgstr)
  {
	   //注意，为简化起见，此处未使用timeout参数，客户程序中有关评价等需要较长时间的指令请自行配置该参数
	   $.post(url,prgstr,function(data){		  	   
			 // alert("返回："+data)
	   });
  }
