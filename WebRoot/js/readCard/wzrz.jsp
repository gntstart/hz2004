<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ page import=" java.util.Date "%>

<%@include file = "/config.jsp"%>

<%
	Date curdate = new Date();
	String cur_gettime = String.valueOf(curdate.getTime());
	String v_lkbm = request.getParameter("lkbm")==null?"":request.getParameter("lkbm");
	String v_edzlx = u.getEdzlx();
	String v_rxbdlx = u.getRxbdflag();//微信无证入住/orc识别/人证核验
	if(v_edzlx == null || "".equals(v_edzlx)){
		v_edzlx ="1";//为空默认为 神思
	}
	String ENABLED_XD = com.gnt.lgy.base.SystemConfig.getSystemConfig("ENABLED_XD", "1");//是否启用西点核验设备开关
	String REQUESTTYPE = com.gnt.lgy.base.SystemConfig.getSystemConfig("REQUESTTYPE", "1");
	String SMSB_ISOPEN = com.gnt.lgy.base.SystemConfig.getSystemConfig("SMSB_ISOPEN", "1");
	String LKRZCWFDZ = com.gnt.lgy.base.SystemConfig.getSystemConfig("LKRZCWFDZ", "1");
	String LKZPISNULL = com.gnt.lgy.base.SystemConfig.getSystemConfig("LKZPISNULL", "0");
	String rzyzurl = com.gnt.lgy.base.SystemConfig.getSystemConfig("rzyzurl", "http://127.0.0.1:8080/rxbd/wzrz?method=zzyz&tokey=wRkz/sL69U40El80Vrn0OA==");
	String xd_hyurl = "wzrz?method=xd_query&tokey=wRkz/sL69U40El80Vrn0OA==";
	LgadminService lgadminService1 = (LgadminService) SpringContainer
				.getObject("lgadminService");
	Lg lg=	lgadminService1.queryLgById(u.getLgbm());
		String url="";
			if(lg!=null){
				url=lg.getDhsrbh();
			}
			
	
%>
<!DOCTYPE HTML>
<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
  <base href="<%=baseurl%>">
	<link rel="stylesheet" type="text/css" href="<%=baseurl%>css/report_style.css">
  <link rel="stylesheet" type="text/css" href="<%=baseurl%>js/ext/css/ext-all.css">
  <link rel="stylesheet" type="text/css" href="<%=baseurl%><%=SystemConfig.getSystemConfig("default_css") %>">
  
</head>
	<body>
	
	<div id="gui" style="width:100%;height:100%"> </div>
	</body>
    <script type="text/javascript" src="<%=baseurl%>js/ext/ext.js"></script>
    <script type="text/javascript" src="<%=baseurl%>js/dict.js"></script> 
    <script type="text/javascript" src="<%=baseurl%>js/qtcommon.js"></script> 
    <script type="text/javascript" src="<%=baseurl%>js/sfz.js"></script> 
    
<script type="text/javascript">
var lgbm = "<%=u.getLgbm()%>";
var lgmc = "<%=u.getLgmc()%>";
var lgsn="<%=url%>";//旅馆设备号
var requestType="<%=REQUESTTYPE%>";
var ENABLEDXD="<%=ENABLED_XD%>";
var SMSB_ISOPEN="<%=SMSB_ISOPEN%>";
Gnt.Auth = function() {};
		//旅店，洗浴
		Gnt.Auth.LGTYPE_XY = '2';
		lt="";
		
		//读卡器类型
		lgEdz="<%=v_edzlx%>";
		var lgRxbdflag="<%=v_rxbdlx%>";
		//修改页面的旅客编码参数
		v_lkbm="<%=v_lkbm%>";
		var rzyzurl="<%=rzyzurl%>";
		var xd_hyurl="<%=url%>";
		//登记时刷新入住时间
		var cur_date = new Date();
		cur_date.setTime(<%=cur_gettime%>);
		var cur_datetime = cur_date.format('YmdHi');
		function DateTimeRefresh(step){
 			cur_date.setTime(cur_date.getTime()+step);
 			cur_datetime = cur_date.format('YmdHi');
 		}
		setInterval("DateTimeRefresh(6000)",6000);
   
    var v_CWFDZ ="<%=LKRZCWFDZ%>";
    var v_ZPNULL ="<%=LKZPISNULL%>";
	try{
　　top.location.hostname;
　　if (top.location.hostname != window.location.hostname) {
　　　　top.location.href =window.location.href;
　　}

}
catch(e){
　　top.location.href = window.location.href;
}

function toJson(str){
 var json = (new Function("return " + str))();
 return json;
}
	</script>
	<script type="text/javascript">
function sendAjax1(){
 $.ajax({
	      url :  'http://192.168.0.31:55532/Ver10/Face_Instruct?callback=successCallback', 
	      type : 'post',
		  data : '{"instructType": 10}',
	      success : function(resp){
	         // console.log(resp);
	    	  alert(resp.idCode);
	      }
 
	  });
}

function sendAjax2(flag){
 $.ajax({
	      url :  'http://'+xd_hyurl+':55532/Ver10/Face_Instruct?instructType=10', 
	      type : 'get',
	      data : {instructType: 10},
	      dataType : 'jsonp',
	      jsonp : "callback",
	      jsonpCallback : 'xxxxx',
	       success : function(resp){
	         //console.log(resp);
	      	//  alert(resp.idCode);
	       }
	  });
}

function xxxxx(data){
   // console.log(data);
    //alert('身份证号：'+ data.idCode);
    xdfz(data);
    
}
 function xdfz(resdata){
    if ( resdata.checkFlag == '1') {
				                                    var xccjzp = 'data:image/png;base64,' + resdata.faceImg;
				                                
				                                
				                                  var mzm=resdata.nation;//民族
				                                   var userName=resdata.name;//姓名
													 var identityId=resdata.idCode;//身份证号
													 var photo=resdata.faceImg;//现场照片base
													  var photo2=resdata.pic;//证件照片base
														// alert(photo);
														  var t_hjd = identityId.substr(0,6);//户籍地
														  var csrq=identityId.substr(6,8);//出生日期
														  var xb=setXb(identityId);//性别
														  var xz=resdata.address;//地址
														  var xbm="";
														  if(xb=='1'){xbm="男";}else{xbm="女";}
															frm.findField("ssxq").setValue(t_hjd);
														
															frm.findField("hcl").setValue(t_hjd);
																//赋值					
																	var wzzp=photo;
	                                                    
		    					                           frm.findField('xm').setValue(userName);
		    					                            frm.findField('xb').setValue(xb);
		    				                                   frm.findField('xbm').setValue(xbm);
		    					                                frm.findField('csrq').setValue(csrq);
		    					                            frm.findField('zjhm').setValue(identityId);
		    					                              frm.findField('xz').setValue(xz);
		    					                           frm.findField("zjlx").setValue('11');
		    					                           frm.findField("zp").setValue(wzzp);
		    					                           if(photo2){
		    					                            frm.findField("zp").setValue(photo2);
		    					                           }
            	                                          
		    					                           frm.findField("hz").setValue(wzzp);
		                                                    frm.findField("hcqm").setValue(inithcqm);
		                                                    frm.findField("hcq").setValue(inithcq);
		                                                   frm.findField("bz").setValue('通过人证一致核验入住');		
		                                                  //读取照片
					    		                        var imgsrc = '';
								                            imgsrc = initLink;
								                             imgsrc="data:image/jpeg;base64,"+wzzp;
								                            if(photo2){
								                             imgsrc="data:image/jpeg;base64,"+photo2;
								                            }
								                           
								                            Ext.fly("zpImg").dom.src = imgsrc;
								                      var imgsrc2 = '';
								                            imgsrc2 = initLink;
								                            imgsrc2="data:image/jpeg;base64,"+wzzp;
								                            Ext.fly("hzImg").dom.src = imgsrc2;
								 
									                        frm.findField('rzsj').setValue(cur_datetime);
								
		    				                               t_zpisnull=false;
		    				                                 v_lkbm="rx";
		                                                     //设置住址1
		                                                       var t_hjd = identityId.substr(0,6);
		    						            Ext.Ajax.request({
												url:'nwb.do?method=zhQhdz&_dc=' + (new Date()).getTime(),
												method:'POST', 
												params:{qhbm:t_hjd},
												success:function(result,request){ 
													//mask.hide();
													var jsonData = Ext.util.JSON.decode(result.responseText);
													if(jsonData.success){
														if(jsonData.success && jsonData.other){
														   // Ext.getCmp("ssxqm").setValue(jsonData.other);
															frm.findField("ssxqm").setValue(jsonData.other);
															frm.findField("ssxq").setValue(t_hjd);
															frm.findField("hclm").setValue(jsonData.other);
															frm.findField("hcl").setValue(t_hjd);
														}
													}
												}, 
												failure: function ( result, request) {
													//mask.hide();
													Ext.MessageBox.alert('查询地址出错',result.responseText); 
												} 
											});
		                                       //住址结束
			                                       
												}else{
												
														//mask.hide();
	                                                   	          // result.responseText                                       
													Ext.MessageBox.alert('没有查询到信息！','没有查询到人证核验信息！'); 
	                                                   
												}
    }
function sendAjax3(){
	 $.ajax({
		      url :  'http://'+xd_hyurl+':55532/Ver10/Face_Instruct?instructType=20', 
		      type : 'get',
		      data : {instructType: 20},
		      dataType : 'jsonp',
		      jsonp : "callback",
		      jsonpCallback : 'xxxxx',
		       success : function(resp){
		          console.log(resp);
		      	  //alert(resp.idCode);
		       }
		  });
	}


</script>
 <script  src="<%=baseurl%>nwb/jquery.js"></script>
	<script type="text/javascript" src="<%=baseurl%>nwb/wzrz.js"></script>
	 
	
</html>