var fhds = null; 
var inithcq=null;
var inithcqm=null;
var pageSize = 20;
var sxm=null;
var sbtype=0;
var sn=null;
var showHtml=null;
	var ssfzh=null;
	var sxb=null;
	var smz=null;
	var sdz=null;
	var szjlx=null;
	var scsrq=null;
	var szp=null;
	var slxdh=null;
	var transactionID=null;
	var hytype=null;
var t_cphs="警";//"京津冀晋蒙辽吉黑沪苏浙皖闽赣鲁豫鄂湘粤桂琼川渝云藏陕甘军青海空宁北新沈兰济南广成贵使";
var t_zpisnull = true;
var lgRxbdflag2=null;
var  flag="1";
//alert(lgRxbdflag);
lgRxbdflag2=lgRxbdflag;
var tztgStore = null;
var tztgListWin = null;
Ext.onReady( function() {
	
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';
    initLink = "images/no_pic.gif";
    var queryString = window.location.search.substring(1);
	var params = Ext.urlDecode(queryString);
	 sxm=params.xm;
	 ssfzh=params.sfzh;
	 sxb=params.xb;
	 smz=params.mz;
	 sdz=params.dz;
	 szjlx=params.zjlx;
	 scsrq=params.csrq;
	 szp=params.zp;
	  slxdh=params.lxdh;
	//alert(sxm);
  //  var flag="0";
  // flag=params.flag;
   // alert(flag);
    //initLink = "c:\\zp.bmp";
    ///全角空格为12288，半角空格为32 
///其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248 
//半角转换为全角函数 
function ToDBC(txtstring) 
{ 
var tmp = ""; 
for(var i=0;i<txtstring.length;i++) 
{ 
if(txtstring.charCodeAt(i)==32) 
{ 
tmp= tmp+ String.fromCharCode(12288); 
} 
if(txtstring.charCodeAt(i)<127) 
{ 
tmp=tmp+String.fromCharCode(txtstring.charCodeAt(i)+65248); 
} 
} 
return tmp; 
} 

function csrqgsh(csrq){

var str =csrq; //1978-9-8
if(csrq.indexOf('-')>-1){
var kk = str.split("-");//以-作为分隔字符串 

var n=kk[0];//年

var y=kk[1];//月

var r=kk[2];//日
if(y<10){y='0'+y;}
if(r<10){r='0'+r;}
csrq=n+y+r;
}
return csrq
}
//全角转换为半角函数 
function ToCDB(str) 
{ 
var tmp = ""; 
for(var i=0;i<str.length;i++) 
{ 
if(str.charCodeAt(i)>65248&&str.charCodeAt(i)<65375) 
{ 
tmp += String.fromCharCode(str.charCodeAt(i)-65248); 
} 
else 
{ 
tmp += String.fromCharCode(str.charCodeAt(i)); 
} 
} 
return tmp 
}

	zpHtml = '<div align="center">照片</div>'+
	           '<div align="center"><img id="zpImg" align="center" height=100 width=90 src="' + initLink + '" alt="无内容"></img></div>'; 
	zpHtml += '<div align="center">照片2</div>'+
				'<div align="center">'+
					'<img id="hzImg" align="center" frameborder=0 height=100 width=90 src="' + initLink + '" alt="无内容" ondblclick=showzp("'+v_lkbm+'","lkhz")></img>'+
				'</div>';  
	var temp_fjztnb = "";
	var temp_fjztwb = "";
	var temp_fjzt = "";
	var temp_rzfj = "";
	var temp_fjrzrsnb = "";
	var temp_fjrzrswb = "";
	var label_fjzt = new Ext.form.Label({
         id:"labelID",
         text:temp_fjzt,
         autoShow:true,//默认false
         autoWidth:true,//默认false
         autoHeight:true,//默认false
         hideMode:"offsets",//默认display,可以取值：display，offsets，visibility
         html:""
  });
  
 
  
    
  
  
  
  /*
     var lgbmStore = new Ext.data.JsonStore({
       		url: 'nwb.do?method=getLgbm',
        	id:'lgbmstroe',
            totalproperty: "results",
            root:"root", 
        
        	listeners:{
        	
        		load:function(store,records){
        		    alert("11111222");
					//var jsonData = Ext.util.JSON.decode(result.responseText);
						
						//  alert(jsonData.other);
						
        		},
        		loadexception:function(){
        		   alert("error!!!");
        		} 
        	}
    }); 
	lgbmStore.load({});
  */
  
  
   //使用模板来显示组合域的记录，样式cls1必须在页面中定义
    var resultTpl = new Ext.XTemplate(
        '<tpl for=".">',
          '<span onclick="dd();">{fh}</span><br>',
        '</tpl>'
    );
  
  
 
    
	Ext.Ajax.request({
				url:'nwb.do?method=getLgbm&_dc=' + (new Date()).getTime(),
				method:'POST', 
				success:function(result,request){ 
					mask.hide();
					var jsonData = Ext.util.JSON.decode(result.responseText);
					if(jsonData.success){
						if(jsonData.success && jsonData.other){
						     if(jsonData.other!=null&&jsonData.other!=""){
						         var lgmcbms=jsonData.other;
						         var lgmcbm=lgmcbms.split(",");
						          inithcq=lgmcbm[0];
						          inithcqm=lgmcbm[1];
						         
						         lkForm.getForm().findField("hcqm").setValue(lgmcbm[1]);
						         lkForm.getForm().findField("hcq").setValue(lgmcbm[0]);
						          //初始化房号
						         var ur=window.location.href;
						         fh=ur.split("fh=");
						         if(fh[1]!=null&&fh[1]!=""){
						             lkForm.getForm().findField("rzfh").setValue(fh[1]);
						         }
								 
						     }
						     
						 
										
						 }
					}
				}, 
				failure: function ( result, request) {
					mask.hide();
					Ext.MessageBox.alert('查询地址',result.responseText); 
				} 
		}); 
		
		//旅馆管理员是否允许录入内外宾信息：1 允许（默认）0 禁止
		Ext.Ajax.request({
				url:'nwb.do?method=getLgglylrbz&_dc=' + (new Date()).getTime(),
				method:'POST', 
				success:function(result,request){ 
					mask.hide();
					var jsonData = Ext.util.JSON.decode(result.responseText);
					if(jsonData.success){
						if(jsonData.success && jsonData.other){
						     if(jsonData.other!=null&&jsonData.other!=""){
						         var lgglylrbz=jsonData.other;
						         if("0"==lgglylrbz){
						           Ext.getCmp('btn-save').disable();
											 Ext.getCmp('btn-new').disable();
											 Ext.getCmp('btn-edz').disable();
						         	}
						     }
						 }
					}
				}, 
				failure: function ( result, request) {
					mask.hide();
					Ext.MessageBox.alert('查询配置信息',result.responseText); 
				} 
		}); 
		
	//20170314add民族翻译
		var mzStore = new Ext.data.SimpleStore({
				id : 'id_ckztStore',
				fields : ["code", "name"],
				data : [['01', '汉'], ['02', '蒙古'], ['03', '回'], ['04', '藏'], ['05', '维吾尔'], ['06', '苗'], ['07', '彝'], ['08', '壮'], ['09', '布依'],
				 ['10', '朝鲜'], ['11', '满'], ['12', '侗'], ['13', '瑶'], ['14', '白'], ['15', '土家'], ['16', '哈尼'], ['17', '哈萨克'], ['18', '傣'], ['19', '黎'],
				 ['20', '傈僳'], ['21', '佤'], ['22', '畲'], ['23', '高山'], ['24', '拉祜'], ['25', '水'], ['26', '东乡'], ['27', '纳西'], ['28', '景颇'], ['29', '柯尔克孜']
				 , ['30', '土'], ['31', '达斡尔'], ['32', '仫佬'], ['33', '羌'], ['34', '布朗'], ['35', '撒拉'], ['36', '毛南'], ['37', '仡佬'], ['38', '锡伯'], ['39', '阿昌']
				 , ['40', '普米'], ['41', '塔吉克'], ['42', '怒'], ['43', '乌兹别克'], ['44', '俄罗斯'], ['45', '鄂温克'], ['46', '德昂'], ['47', '保安'], ['48', '裕固'], ['49', '京']
				 , ['50', '塔塔尔'], ['51', '独龙'], ['52', '鄂伦春'], ['53', '赫哲'], ['54', '门巴'], ['55', '珞巴'], ['56', '基诺'], ['57', '外国血统中国籍人士'], ['97', '其他']
				]
			});
	function getMzcode(value) {
		//var d = record.data;
		var logindex = (mzStore.find("name", value));
		var logrecord = mzStore.getAt(logindex);
		if (logrecord)
			return logrecord.data.code;
		else
			return "01";
	}
	
	
	//js
	 
	  var lkStore = new Ext.data.JsonStore({
    		fields: [{
				    name: 'lkbm'	
	            }, {
	                name: 'xm'
	            }, {
	                name: 'xb'
	            }, {
	                name: 'xbm'
	            }, {
	                name: 'mz'
							}, {
	                name: 'mzm'
			    		}, {
	                name: 'csrq'
	            }, {
	                name: 'zjlx'
	            }, {
	                name: 'zjlxm'
	            }, {
	                name: 'zjhm'
	            }, {
	                name: 'ssxq'
	            }, {id:'ssxqm',
	                name: 'ssxqm'
				},
				{
	                name: 'xz'
	            },{
	                name: 'rzsj'
	            },{
	                name: 'rzfh'
	            },{
	                name: 'tfsj'
	            },{
	                name: 'xyklx'
	            },{
	                name: 'xykhm'
	            },{
	         				name: 'zy'
	        		}, {
	        				name: 'zym'
	        		}, {
	                name: 'hcl'
	            }, {id:'hclm',
	                name: 'hclm'
	            }, {
	                name: 'hcq'
	            }, {id:'hcqm',
	                name: 'hcqm'
							}, {
	                name: 'lcsy'
	            }, {
	                name: 'lcsym'
	            }, {
	                name: 'bz'
	            }, {
	                name: 'lg'
	            } , {id:'cphm',
	                name: 'cphm'
	            }	, {
	                name: 'lxdh'
	            }	, {
	                name: 'cpszm'
	            }, {
	                name: 'cpsz'
	            }
	    		],
    		root: "list",
    		totalProperty: "totalCount",
    		id: 'id_lkStore',  	
    		
    		proxy: new Ext.data.HttpProxy({
    			url: "nwb.do?method=queryNb&_dc=" + (new Date()).getTime(),
					method: "POST", 
					disableCaching: true
    		})
    });
    //根据身份证带入系统内旅客信息
    var repStore = new Ext.data.JsonStore({
    		fields: [
		    			{
					name: 'lkbm'	
	            }, {
	                name: 'xm'
	            }, {
	                name: 'xb'
	            }, {
	                name: 'xbm'
	            }, {
	                name: 'mz'
							}, {
	                name: 'mzm'
			    		}, {
	                name: 'csrq'
	            }, {
	                name: 'zjlx'
	            }, {
	                name: 'zjlxm'
	            }, {
	                name: 'zjhm'
	            }, {
	                name: 'ssxq'
	            }, {id:'ssxqm',
	                name: 'ssxqm'
							}, {
	                name: 'xz'
	            },{
	                name: 'xyklx'
	            },{
	                name: 'xykhm'
	            },{
	         				name: 'zy'
	        		}, {
	        				name: 'zym'
	        		}, {
	                name: 'hcl'
	            }, {id:'hclm',
	                name: 'hclm'
	            }, {
	                name: 'hcq'
	            }, {id:'hcqm',
	                name: 'hcqm'
							}, {
	                name: 'lcsy'
	            }, {
	                name: 'lcsym'
	            }, {
	                name: 'bz'
	            } , {id:'cphm',
	                name: 'cphm'
	            } , {
	                name: 'lxdh'
	            }	, {
	                name: 'cpsz'
	            }	, {
	                name: 'cpszm'
	            }	
	    		],
    		root: "list",
    		totalProperty: "totalCount",
    		id: 'lkbm',  	
    		
    		proxy: new Ext.data.HttpProxy({
    			url: "nwb.do?method=queryNbBySfz&_dc=" + (new Date()).getTime(),
					method: "POST", 
					disableCaching: false
				})
    });	
      //20170310kqt add根据旅馆编码带入人像比对-内旅客信息
    var rxbdStore = new Ext.data.JsonStore({
    		fields: [
	    		{name: "count"},
	    		{name: 'id'},{name: 'name'},{name: 'sex'},{name: 'nation'},{name: 'sfzh'},{name: 'csrq'},
	    		{name: 'dz'},{name: 'fzjg'},{name: 'lgbm'}
	    		],
    		root: "list",
    		totalProperty: "totalCount",
    		id: 'id',
    		proxy: new Ext.data.HttpProxy({
    			url: "nwb.do?method=queryNbBylgRxbd&_dc=" + (new Date()).getTime(),
					method: "POST", 
					disableCaching: true
    		})
    });
    //同一身份证不能在同个旅馆多次在住
    var nbzzStore = new Ext.data.JsonStore({
    		fields: [
		    			{
					name: 'lkbm'	
	            }, {
	                name: 'xm'
	            }, {
	                name: 'xb'
	            }, {
	                name: 'xbm'
	            }, {
	                name: 'mz'
							}, {
	                name: 'mzm'
			    		}, {
	                name: 'csrq'
	            }, {
	                name: 'zjlx'
	            }, {
	                name: 'zjlxm'
	            }, {
	                name: 'zjhm'
	            } 			
	    		],
    		root: "list",
    		totalProperty: "totalCount",
    		id: 'lkbm',  	
    		
    		proxy: new Ext.data.HttpProxy({
    			url: "nwb.do?method=queryZznb&_dc=" + (new Date()).getTime(),
					method: "POST", 
					disableCaching: false
				})
    });	
    //通过客户端集成ocx控件实现集成
        function rxbd(flag){
        //alert(lgRxbdflag);
        
          if(lgRxbdflag=='0'||lgRxbdflag=='5'){//0是延展人证核验设备5是延展核验设备和中安护照识别设备
        	//初始化设备
        	SocketToPcSerOcx = document.getElementById("SocketToPcSerOcx");
        	//alert(SocketToPcSerOcx);
          SocketToPcSerOcx.SDKInitIP_1('0.0.0.0',30141,'c:/',60);
      var bdzt= SocketToPcSerOcx.SDKGetDeviceIsOnline_1();
      //  alert(bdzt);
        
     if(bdzt){
        //获取人证核验数据
   var ret=null;
         ret= SocketToPcSerOcx.SDKGetCallBackData_1();
        //测试数据
        // ret = '{"Address":"北京市朝阳区五道口","Birth":"20000102","CertNo":"32050519871127181X","Department":"北京市朝阳区","EffectData":"20100102","Expire":"20200102","Name":"阿斯顿","Nation":"汉","PhotoPath1":"D:\\1.JPG","PhotoPath2":"C:\\File\\20170515094654852.bmp","Resemble":"511","Result":0,"Sex":"男"}'; 

    if(ret!=null&&ret.length > 0)
   {
   	var obj = toJson(ret); 
//alert(obj["content"]);
var  obj66=eval("("+obj["content"]+")");
    // alert(obj66.Address);


    	 //赋值
						 var name = obj66.Name;
						 var sex = obj66.Sex;
						 var nation = obj66.Nation;
						 var csrq = obj66.Birth;
						 var dz = obj66.Address;
						  var sfzh = obj66.CertNo;
						  var zp1 = obj66.PhotoPath1;
						   var zp2 = obj66.PhotoPath2;
						    //var bm = rxbdStore.getAt(0).data.lgbm;
						 if(sex=="男"){sex="1";}else{sex="2"}
						 //if(nation=="汉"){nation="01";}else{}
						 nation=getMzcode(nation);
						//alert(nation);
						 
						var frm = lkForm.getForm();
						       // frm.reset();
								
		    					frm.findField('xm').setValue(name);
		    					frm.findField('xb').setValue(sex);
		    					frm.findField('mz').setValue(nation);
		    					frm.findField('csrq').setValue(csrq);
		    					frm.findField('zjhm').setValue(sfzh);
		    					frm.findField('xz').setValue(dz);
		    					frm.findField("zjlx").setValue('11');
            	                //frm.findField("mz").setValue('01');
		    					
		                        frm.findField("hcqm").setValue(inithcqm);
		                        frm.findField("hcq").setValue(inithcq);
		                        		//同证件未退房不能登记
			    	           /* var lkbm2 = frm.findField("lkbm").getValue();
			    	
			    	            if(lkbm2==null||lkbm2==""){
			    	            var zjhm2 = frm.findField("zjhm").getValue();
		    					nbzzStore.removeAll();
				    			nbzzStore.load({
							    	params: {zjlx:'11',zjhm: zjhm2,_dc2:(new Date()).getTime()},
							    	callback: function() {
							    		
							    	  if (nbzzStore.getCount()>0) {
							    	   // Ext.Msg.alert("提示","身份证:"+zjhm2+"还没有退房，不能重复登记，请检查！");	
							    	    //Ext.getCmp("btn-new").fireEvent('click');
							    	    Gnt.MsgBox.showError("身份证:"+zjhm2+"还没有退房，不能重复登记，请检查！");
							    	    frm.reset();
							    	    Ext.getCmp("btn-new").fireEvent('click');
							    	    return false;
							    	  }		
							    	}
							    });
			    	            }*///js
		                        //读取照片
					    		var imgsrc = '';
								    imgsrc = initLink;
								    Ext.fly("zpImg").dom.src = imgsrc;
								    operElement("imgReader","clear","","f");
									lkForm.getForm().findField("rzsj").setValue(cur_datetime);
									document.getElementById("imgReader").loadFile(zp1);
		    					//operElement("imgReader","loadFile","","f",ci.PhotoPath);
		    				    t_zpisnull=false;
		    				    v_lkbm="rx";
		                          //设置住址1
							    	  var t_hjd = sfzh.substr(0,6);
		    						Ext.Ajax.request({
												url:'nwb.do?method=zhQhdz&_dc=' + (new Date()).getTime(),
												method:'POST', 
												params:{qhbm:t_hjd},
												success:function(result,request){ 
													mask.hide();
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
													mask.hide();
													Ext.MessageBox.alert('查询地址出错',result.responseText); 
												} 
											});
		    				
			}else
          {
            ret='没有获取到人证核验数据！';
            alert(ret);
            //document.getElementById("result").innerHTML=ret;
          }	
     }else{
     alert("初始化人证核验设备失败，请确认是否连接正常！");
     }
     }else if(lgRxbdflag=='3'||lgRxbdflag=='4'){//3是西点核验设备4是洗点设备和中安orc设备
     
        var frm = lkForm.getForm();
    		mask.show();
    		//alert(flag);
    		if(flag=='10'){
    		  sendAjax2(flag);
    		}else{
    		  sendAjax3(flag);
    		}
         
            mask.hide();
            //西点结束
     }else{
     alert("没有配置或没有找到人证核验设备！");
     }
    };
    
    
    
    //通过客户端集成ocx控件实现集成-实名宝无证人员入住
        function wzrz(){
        
        	//初始化设备
      var  SynCardOcx1 = document.getElementById("SynCardOcx1");
      var nRet;
	// 读取卡信息
  	nRet = SynCardOcx1.SmbReadMsg();
  	if(nRet==1)
  	{
  
    	 //赋值
						 var name = SynCardOcx1.szName;
						 var sex = SynCardOcx1.szSex;
						 var nation = SynCardOcx1.szNation;
						 var csrq = SynCardOcx1.szBirthday;
						 var dz = SynCardOcx1.szAddress;
						  var sfzh = SynCardOcx1.szCertifiCode;
						  var zp1 = SynCardOcx1.szImageSource;
						   var zp2 = SynCardOcx1.szImageVerify;
						    //var bm = rxbdStore.getAt(0).data.lgbm;
						 //if(sex=="1"){sex="1";}else{sex="2"}
						 //if(nation=="汉"){nation="01";}else{}
						 nation=getMzcode(nation);
						//alert(nation);
						 
						var frm = lkForm.getForm();
						       // frm.reset();
								
		    					frm.findField('xm').setValue(name);
		    					frm.findField('xb').setValue(sex);
		    					frm.findField('mz').setValue(nation);
		    					frm.findField('csrq').setValue(csrq);
		    					frm.findField('zjhm').setValue(sfzh);
		    					frm.findField('xz').setValue(dz);
		    					frm.findField("zjlx").setValue('11');
            	                frm.findField("zp").setValue(zp1);
		    					
		                        frm.findField("hcqm").setValue(inithcqm);
		                        frm.findField("hcq").setValue(inithcq);
		                        		//同证件未退房不能登记
			    	           /* var lkbm2 = frm.findField("lkbm").getValue();
			    	
			    	            if(lkbm2==null||lkbm2==""){
			    	            var zjhm2 = frm.findField("zjhm").getValue();
		    					nbzzStore.removeAll();
				    			nbzzStore.load({
							    	params: {zjlx:'11',zjhm: zjhm2,_dc2:(new Date()).getTime()},
							    	callback: function() {
							    		
							    	  if (nbzzStore.getCount()>0) {
							    	   // Ext.Msg.alert("提示","身份证:"+zjhm2+"还没有退房，不能重复登记，请检查！");	
							    	    //Ext.getCmp("btn-new").fireEvent('click');
							    	    Gnt.MsgBox.showError("身份证:"+zjhm2+"还没有退房，不能重复登记，请检查！");
							    	    frm.reset();
							    	    Ext.getCmp("btn-new").fireEvent('click');
							    	    return false;
							    	  }		
							    	}
							    });
			    	            }*///js
		                        //读取照片
					    		var imgsrc = '';
								    imgsrc = initLink;
								       //imgsrc="data:image/jpeg;base64,"+szp;
								   imgsrc="C:\\EIDPicture\\image.jpg?d="+(new Date()).getTime();
								    Ext.fly("zpImg").dom.src = imgsrc;
								    
								   // operElement("imgReader","clear","","f");
									lkForm.getForm().findField("rzsj").setValue(cur_datetime);
									//document.getElementById("imgReader").loadFile(szp);
									document.getElementById("imgReader").loadFile("C://EIDPicture//image.jpg");	
		    					//operElement("imgReader","loadFile","","f",ci.PhotoPath);
		    				    t_zpisnull=false;
		    				    v_lkbm="rx";
		                          //设置住址1
							    	  var t_hjd = sfzh.substr(0,6);
		    						Ext.Ajax.request({
												url:'nwb.do?method=zhQhdz&_dc=' + (new Date()).getTime(),
												method:'POST', 
												params:{qhbm:t_hjd},
												success:function(result,request){ 
													mask.hide();
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
													mask.hide();
													Ext.MessageBox.alert('查询地址出错',result.responseText); 
												} 
											});
		    				
			}else
          {
            ret='没有获取到人证核验数据！';
            alert(ret);
            //document.getElementById("result").innerHTML=ret;
          }	
     
    };
    //无证入住微信
    //通过查询后台比对数据接口
    //未阅读的通知通告消息
     tztgStore = new Ext.data.JsonStore({
    		fields: [
	    		{name: "bdsj"},
	    		{name: 'id'},{name: 'name'},{name: 'sex'},{name: 'nation'},{name: 'sfzh'},{name: 'csrq'},
	    		{name: 'dz'},{name: 'fzjg'},{name: 'lgbm'},{name: 'zppic'}
	    		],
    		root: "list",
    		totalProperty: "totalCount",
    		id: 'id',
    		proxy: new Ext.data.HttpProxy({
    			url: "nwb.do?method=queryNbBylgRxbd&_dc=" + (new Date()).getTime(),
					method: "POST", 
					disableCaching: true
    		})
    });
	
	var tztgModel = new Ext.grid.ColumnModel([
		{
	        header: "姓名",
	        dataIndex: "id",	
	        sortable: true,
			width: 60,
			renderer:function(id){
				var r = tztgStore.getById(id);
				var value = r.data.name;
				
				
					return value;
				
			}
	    },{
	        header: "身份证号",
	        dataIndex: "id",	
	        sortable: true,
			width: 560,
			renderer:function(id){
				var r = tztgStore.getById(id);
				var value = r.data.sfzh;
				
				
					return value;
				
			}
	    },{
	        header: "比对时间",
	        dataIndex: "id",	
	        sortable: true,
			width: 100,
			renderer:function(id){
				var r = tztgStore.getById(id);
				var value = r.data.bdsj;
				
				var str = value.substr(0,4) + "-" + value.substr(4,2) + "-" + value.substr(6,2);
				if(value.length>8)
					str += " " + value.substr(8,2);
				if(value.length>10)
					str += ":" + value.substr(10,2);
				if(value.length>12)
					str += ":" + value.substr(12,2);
				
				if(r.data.read && r.data.read=='1'){
					return str;
				}else{
					return "<B>"+str + "</B>";
				}
			}
	    }
	]);
    var tztgGrid = new Ext.grid.GridPanel({
        store: tztgStore,
        region: 'center',
        view:new Ext.grid.GridView({
				forceFit:true,
				autoFill:true,
				enableRowBody:true
		}),
		stripeRows: true,
        cm: tztgModel,
		loadMask: {msg:'正在加载数据，请稍侯……'},
		bodyStyle:'width:100%',
        border:true,
        anchor:'100% 100%',
	    margins: '0 0 0 0',
		cmargins: '0 0 0 0',        
        frame:false,
		iconCls:'icon-grid',
        title:'',
        bbar: new Ext.PagingToolbar({
			pageSize: 20,
			store: tztgStore,
			displayInfo: true
		}),
        listeners:{
			rowclick:function(g, rowIndex, e){
				selRecord = g.store.getAt(rowIndex);
				wzrzwx();//旅客付钱接口
			}
		}
   	});
    tztgListWin = new Ext.Window({
		title:'无证比对方式2通过结果',
		closeAction:'hide',
		modal:true,
		width:650,
		height:300,
		layout:'fit',
		closable:true,
		items:tztgGrid,
		listeners:{
			show:function(){
				 tztgStore.load({});	
			}
		}
	});
	//旅客付费接口

    function wzrzwx(){
    	var data = selRecord.data;
		    					
    
						 //赋值
						 var wzid = data.id;
						  var name = data.name;
						 var sex = data.sex;
						 var nation = data.nation;
						 var csrq = data.csrq;
						 var dz = data.dz;
						  var sfzh = data.sfzh;
						    var bm = data.lgbm;
						     var zp = data.zppic;
						    // alert(zp);
						 if(sex=="男"){sex="1";}else{sex="2"}
						 //if(nation=="汉"){nation="01";}else{}
						 nation=getMzcode(nation);
						//alert(nation);
						 
						var frm = lkForm.getForm();
						       // frm.reset();
					
		    					frm.findField('xm').setValue(name);
		    					frm.findField('wzid').setValue(wzid);
		    					frm.findField('xb').setValue(sex);
		    					frm.findField('mz').setValue(nation);
		    					frm.findField('csrq').setValue(csrq);
		    					frm.findField('zjhm').setValue(sfzh);
		    					frm.findField('xz').setValue(dz);
		    					frm.findField("zjlx").setValue('11');
            	                //frm.findField("mz").setValue('01');   
        	                    frm.findField("zp").setValue(zp);
		                        frm.findField("hcqm").setValue(inithcqm);
		                        frm.findField("hcq").setValue(inithcq);
		                        		//同证件未退房不能登记
			    	            var lkbm2 = frm.findField("lkbm").getValue();
			    	
			    	         
		                        //读取照片
					    		var imgsrc = '';
								    imgsrc = 'util/upfile?type=lgyrxpic&id='+bm+'&zjhm=' + sfzh + '&_dc=' + (new Date()).getTime()
								    Ext.fly("zpImg").dom.src = imgsrc;
									lkForm.getForm().findField("rzsj").setValue(cur_datetime);
		    				  
		    				     t_zpisnull=false;
		    				    v_lkbm="rx";
		                          //设置住址1
							    	  var t_hjd = sfzh.substr(0,6);
		    						Ext.Ajax.request({
												url:'nwb.do?method=zhQhdz&_dc=' + (new Date()).getTime(),
												method:'POST', 
												params:{qhbm:t_hjd},
												success:function(result,request){ 
													mask.hide();
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
													mask.hide();
													Ext.MessageBox.alert('查询地址出错',result.responseText); 
												} 
											});
		    				
							    
		    		tztgListWin.hide();
    };
    
    //通过查询后台比对数据接口
    function rxbd_bak(){
    	
		    					
      rxbdStore.load({
					params: {limit:1,start:0},
					callback : function(){
					    // alert(rxbdStore.getAt(0).data.name);
						 //alert(fjzzrs);
															
						 if (rxbdStore.getCount()>0  ) {
						 //赋值
						 var name = rxbdStore.getAt(0).data.name;
						 var sex = rxbdStore.getAt(0).data.sex;
						 var nation = rxbdStore.getAt(0).data.nation;
						 var csrq = rxbdStore.getAt(0).data.csrq;
						 var dz = rxbdStore.getAt(0).data.dz;
						  var sfzh = rxbdStore.getAt(0).data.sfzh;
						    var bm = rxbdStore.getAt(0).data.lgbm;
						 if(sex=="男"){sex="1";}else{sex="2"}
						 //if(nation=="汉"){nation="01";}else{}
						 nation=getMzcode(nation);
						//alert(nation);
						 
						var frm = lkForm.getForm();
						       // frm.reset();
					
		    					frm.findField('xm').setValue(name);
		    					frm.findField('xb').setValue(sex);
		    					frm.findField('mz').setValue(nation);
		    					frm.findField('csrq').setValue(csrq);
		    					frm.findField('zjhm').setValue(sfzh);
		    					frm.findField('xz').setValue(dz);
		    					frm.findField("zjlx").setValue('11');
            	                //frm.findField("mz").setValue('01');   
        	  
		                        frm.findField("hcqm").setValue(inithcqm);
		                        frm.findField("hcq").setValue(inithcq);
		                        		//同证件未退房不能登记
			    	            var lkbm2 = frm.findField("lkbm").getValue();
			    	
			    	            if(lkbm2==null||lkbm2==""){
			    	            var zjhm2 = frm.findField("zjhm").getValue();
		    					nbzzStore.removeAll();
				    			nbzzStore.load({
							    	params: {zjlx:'11',zjhm: zjhm2,_dc2:(new Date()).getTime()},
							    	callback: function() {
							    		
							    	  if (nbzzStore.getCount()>0) {
							    	   // Ext.Msg.alert("提示","身份证:"+zjhm2+"还没有退房，不能重复登记，请检查！");	
							    	    //Ext.getCmp("btn-new").fireEvent('click');
							    	    Gnt.MsgBox.showError("身份证:"+zjhm2+"还没有退房，不能重复登记，请检查！");
							    	    frm.reset();
							    	    Ext.getCmp("btn-new").fireEvent('click');
							    	    return false;
							    	  }		
							    	}
							    });
			    	            }//js
		                        //读取照片
					    		var imgsrc = '';
								    imgsrc = 'util/upfile?type=lgyrxpic&id='+bm+'&zjhm=' + sfzh + '&_dc=' + (new Date()).getTime()
								    Ext.fly("zpImg").dom.src = imgsrc;
									lkForm.getForm().findField("rzsj").setValue(cur_datetime);
		    				    t_zpisnull=false;
		    				    v_lkbm="rx";
		                          //设置住址1
							    	  var t_hjd = sfzh.substr(0,6);
		    						Ext.Ajax.request({
												url:'nwb.do?method=zhQhdz&_dc=' + (new Date()).getTime(),
												method:'POST', 
												params:{qhbm:t_hjd},
												success:function(result,request){ 
													mask.hide();
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
													mask.hide();
													Ext.MessageBox.alert('查询地址出错',result.responseText); 
												} 
											});
		    				
							    
		    					
						 //js
												  		}else{
												  		alert("没有查询到人像比对数据！");
												  		}
												  		
											 }
					 });
    
    };
    
    
    //获取房号，旅馆编号参数从内存中获取
   var  fhStore = new Ext.data.JsonStore({
    		fields: [
	    		{name: "fh"}
	    		],
	    		root: "list",
	    		totalProperty: "totalCount",
	    		id: 'fh',  	
    		
    			proxy: new Ext.data.HttpProxy({
	    			url: "nwb.do?method=queryLgFh&_dc=" + (new Date()).getTime(),
						method: "POST", 
						disableCaching: true
    		})
    });
    
    //联想查询房号
    var  fhds = new Ext.data.JsonStore({
    		fields: [
	    		{name: "fh"}
	    		],
	    		root: "list",
	    		totalProperty: "totalCount",
	    		id: 'fh',  	
    		
    			proxy: new Ext.data.HttpProxy({
	    			url: "nwb.do?method=queryLgFhByfh&_dc=" + (new Date()).getTime(),
						method: "POST", 
						disableCaching: true
    		})
    });
    
    var  snds = new Ext.data.JsonStore({
		fields: [
    		{name: "sn"}
    		],
    		root: "list",
    		totalProperty: "totalCount",
    		id: 'sn',  	
		
			proxy: new Ext.data.HttpProxy({
    			url: "nwb.do?method=querysn&lgbm=" + lgbm,
					method: "POST", 
					disableCaching: true
		})
});
    
    
  
    var cfwStore = new Ext.data.JsonStore({
    		fields: [
	    		{name: "count"},
	    		{name: 'nbrs'},{name: 'cws'},{name: 'zzrs'},{name: 'wbrs'}
	    		],
    		root: "list",
    		totalProperty: "totalCount",
    		id: 'id_fjrsnb',
    		proxy: new Ext.data.HttpProxy({
    			url: "nwb.do?method=queryLgFh&_dc=" + (new Date()).getTime(),
					method: "POST", 
					disableCaching: true
    		})
    });
    var fjrsnbStore = new Ext.data.JsonStore({
    		fields: [
	    		{name: 'lkbm'}
	    		],
    		root: "list",
    		totalProperty: "totalCount",
    		id: 'id_fjrsnb',
    		proxy: new Ext.data.HttpProxy({
    			url: "nwb.do?method=queryZznb&_dc=" + (new Date()).getTime(),
					method: "POST", 
					disableCaching: true
    		})
    });
    var fjrswbStore = new Ext.data.JsonStore({	    		    		
    		fields: [
	    		{name: "count"},
	    		{name: 'wbrs'},{name: 'cws'},{name: 'zzrs'}
	    		],
    		root: "list",
    		id: 'id_fjrswb',
    		totalProperty: "totalCount",
    		proxy: new Ext.data.HttpProxy({
    			url: "nwb.do?method=queryZzwb&_dc=" + (new Date()).getTime(),
					method: "POST", 
					disableCaching: true
    		})
    });
    
    //转换区划地址字典
      var qhdzStore = new Ext.data.JsonStore({	    		    		
    		fields: [
	    		{name: "qhbm"}
	    		
	    		],
    		root: "list",
    		id: 'id_qhdz',
    		totalProperty: "totalCount",
    		proxy: new Ext.data.HttpProxy({
    			url: "nwb.do?method=zhQhdz&_dc=" + (new Date()).getTime(),
					method: "POST", 
					disableCaching: true
    		})
    });
    
    
  
    //
    var lkForm = new Ext.FormPanel({
        id: 'lk-form-panel',
        frame: true,
        //labelAlign: 'right',
        title: '内宾信息维护',
		    //columnWidth: 1,
		    //width:780,
		    //height: 300,
		    anchor:'100% 100%',
		    autoWidth:true,
		    labelWidth: 80,
		    layout:'column',
				items: [{
					layout: 'form',
		            //autoHeight: true,
		            columnWidth: 0.28,
		            defaultType: 'textfield',
		            defaults: {disabledClass: ''},
		            items: [ 
		                    {
			            		name:'rzbdid',
			            		id:'rzbdid',
			            		xtype:'hidden'
	            	        },
	            			{
			            		name:'wzid',
			            		id:'wzid',
			            		xtype:'hidden'
			            	},{
			            		name:'lkbm',
			            		id:'lkbm',
			            		xtype:'hidden'
			            	},{
			            		name:'zjlxm',
			            		id:'zjlxm',
			            		xtype:'hidden'
			            	},{
			            		name:'xbm',
			            		id:'xbm',
			            		xtype:'hidden'
			            	},{
			            		name:'mzm',
			            		id:'mzm',
			            		xtype:'hidden'
			            	},{
			            		name:'ssxq',
			            		id:'ssxq',
			            		xtype:'hidden'
			            	},{
			            		name:'hcl',
			            		id:'hcl',
			            		xtype:'hidden'
			            	},{
			            		name:'hcq',
			            		id:'hcq',
			            		xtype:'hidden'
			            	},{
			            		name:'lcsym',
			            		id:'lcsym',
			            		xtype:'hidden'
			            	},{
			            		name:'zym',
			            		id:'zym',
			            		xtype:'hidden'
			            	},{
			            		name:'cpsz',
			            		id:'cpsz',
			            		xtype:'hidden'
			            	},{
			            		name:'zp',
			            		id:'zp',
			            		xtype:'hidden'
			            	},{
			            		name:'hz',
			            		id:'hz',
			            		xtype:'hidden'
			            	},{
	                name: 'lkbm2',
	                xtype:'hidden',
	                disabled:true,
	                tabIndex: 1,
									anchor:'90%',
	                fieldLabel: lt==Gnt.Auth.LGTYPE_XY ? '浴客编码':'旅客编码'                
	            }, {
	                name: 'xm',
	                id:'xm',
	                allowBlank: false,         
	                tabIndex: 1,
					anchor:'90%',
					maxLength: 50,
					 regex: /^[\u0100-\u9fa5]+([\u0100-\u9fa5]|·)*[\u0100-\u9fa5]+$/,
					 regexText:'只能输入汉字',
	                fieldLabel: '姓名'
	            }, {
	                name: 'csrq',
	                allowBlank: false,
	                tabIndex: 4,
									anchor:'90%',
	                xtype: 'gnt.datefield',
	                fieldLabel: '出生日期'                
	            }, {
	                name: 'rzsj',
	                allowBlank: false,
	                editable:false,
	                tabIndex: 7,
	                anchor:'90%',
	                xtype: 'gnt.datetimefield',
	                fieldLabel: '入住时间'
	            } , 
	            
	            {
		            name: 'rzfh',
		            id:'rzfh',
	                allowBlank: false,
	                tabIndex: 10,
					anchor:'90%',
	                fieldLabel: lt==Gnt.Auth.LGTYPE_XY ? '手牌号':'入住房号',
	                xtype: 'combo',
			        store: fhds,
			        displayField:'fh',
			        valueField: 'fh',			        
			        queryParam: 'fh',
 					minChars: 1, 			        
    				pageSize: 20,
    				listWidth:240,
			        forceSelection: true,
			        typeAhead: true,
			        triggerAction: 'all',
			        emptyText:'',
			        loadingText: '正在执行搜素，请等待...',
			        selectOnFocus:true,
    				//editable:true,
    				//tpl: resultTpl,
    				//applyTo:'input',
    				mode:'remote',
    			    listeners: {
			    			'select': function() {
			    				temp_rzfj = this.getRawValue();
			    				
			    				fjrsnbStore.load({
			    					params:{rzfh: temp_rzfj,limit:999,start:0,_dc2:(new Date()).getTime()},
										callback : function(){
											temp_tsxx="<font color='red'>提示：</font>"+temp_rzfj+"房间已有在住";
											tsxx="";
											//if (fjrsnbStore.getCount()>0 && fjrsnbStore.getAt(0).data.nbrs >0) {
												 //temp_fjrzrsnb = fjrsnbStore.getAt(0).data.nbrs;
											temp_fjrzrsnb = 0;
											if (fjrsnbStore.getCount()>0) {
												temp_fjrzrsnb = fjrsnbStore.getCount();
												 temp_fjztnb = "&nbsp内宾<a herf=\"#\" style=\"cursor:hand\" title=\"点击查看详情\" onclick=\"parent.openWorkSpace('nwb/zznbcx.jsp?rzfh=" + temp_rzfj+"','在住内宾查询','LKZZBNCX_"+temp_rzfj+"');\"><font color='red'><u>"+temp_fjrzrsnb+"人</u></font></a>";
										  }else{
										  	temp_fjztnb="";	
										  }
										  if(""!=temp_fjztnb){
										    tsxx=temp_tsxx+temp_fjztnb
										  }
										  label_fjzt.el.dom.innerHTML=tsxx;
										  
										  //alert(temp_fjrzrsnb);
										  fjrswbStore.load({
											  params: {rzfh: temp_rzfj,limit:999,start:0},
												callback : function(){
													temp_fjrzrswb = 0;
													if (fjrswbStore.getCount()>0 ) {
														 temp_fjrzrswb = fjrswbStore.getCount();
														 temp_fjztwb = "&nbsp外宾<a herf=\"#\" style=\"cursor:hand\" title=\"点击查看详情\" onclick=\"parent.openWorkSpace('nwb/zzwbcx.jsp?rzfh=" + temp_rzfj+"','在住外宾查询','LKZZWNCX_"+temp_rzfj+"');\"><font color='red'><u>"+temp_fjrzrswb+"人</u></font></a>";
												  }else{
												  	temp_fjztwb="";	
												  }
												  if(""!=temp_fjztwb){
												    tsxx=temp_tsxx+temp_fjztnb+temp_fjztwb;
												    label_fjzt.el.dom.innerHTML=tsxx;
												  }
												  
												  var fjzzrs = parseInt(temp_fjrzrsnb)+parseInt(temp_fjrzrswb);
												  cfwStore.load({
													  params: {fh: temp_rzfj,limit:1,start:0},
														callback : function(){
															//alert(cfwStore.getAt(0).data.cws);
															//alert(fjzzrs);
															
															if (cfwStore.getCount()>0 && fjzzrs >0 ) {
													    	var fjcws = cfwStore.getAt(0).data.cws;
													    	if(""==fjcws){
													    		fjcws = 0;
													    	}
													    	//alert(parseInt(fjcws)+parseInt(v_CWFDZ));
													    	if(fjzzrs >(parseInt(fjcws)+parseInt(v_CWFDZ))){
													    	  alert(temp_rzfj+"房间已登记入住 "+fjzzrs+" 人，大于房间床位数，请先退房后再登记入住。");
													    	  lkForm.getForm().findField("rzfh").setValue('');
													    	}
												  		}
														}
													});
										  
												}
											});
										
										  
										  if (fjrsnbStore.getCount()>0 && fjrsnbStore.getAt(0).data.wbrs >0 ) {
													temp_fjrzrswb = fjrsnbStore.getAt(0).data.wbrs;
													temp_fjztwb = "&nbsp外宾<a herf=\"#\" style=\"cursor:hand\" title=\"点击查看详情\" onclick=\"parent.openWorkSpace('nwb/zzwbcx.jsp?rzfh=" + temp_rzfj+"','在住外宾查询','LKZZWNCX_"+temp_rzfj+"');\"><font color='red'><u>"+temp_fjrzrswb+"人</u></font></a>";
											}else{
											 	  temp_fjztwb="";	
										  }
											if(""!=temp_fjztwb){
											    tsxx=temp_tsxx+temp_fjztnb+temp_fjztwb;
												  label_fjzt.el.dom.innerHTML=tsxx;
											}
											
										  if (fjrsnbStore.getCount()>0 && fjrsnbStore.getAt(0).data.zzrs >0 ) {
										    	var fjzzrs = fjrsnbStore.getAt(0).data.zzrs;
										    	var fjcws = fjrsnbStore.getAt(0).data.cws;
										    	if(""==fjcws){
										    		fjcws = 0;
										    	}
										    	if(fjzzrs >(parseInt(fjcws)+parseInt(v_CWFDZ))){
										    	  alert(temp_rzfj+"房间已登记入住 "+fjzzrs+" 人，大于房间床位数，请先退房后再登记入住。");
										    	  lkForm.getForm().findField("rzfh").setValue('');
										    	}
										  }
										  
										  
										}
									});
									
								}
			    	  }
				
	             },
	             {
			            name: 'sbh',		         
			            id:'sbh',
		                allowBlank: true,
		                tabIndex: 10,
						anchor:'90%',
		                fieldLabel: '设备号',
		                xtype: 'combo',
				        store: snds,
				        displayField:'sn',
				        valueField: 'sn',			        
				        queryParam: 'sn',
	 					//minChars: 1, 			        
	    				//pageSize: 20,
	    				//listWidth:240,
				        forceSelection: true,
				        typeAhead: true,
				        triggerAction: 'all',
				        emptyText:'',
				        loadingText: '正在执行搜素，请等待...',
				        selectOnFocus:true,
	    				//editable:false,
	    				//tpl: resultTpl,
	    				//applyTo:'input',
	    				//mode:'remote',
	    			    listeners: {
				    			'select': function() {
				    				sn = this.getRawValue();
				    					
									}
				    	  }
					
		             },
	            {id:'hclm',
	                name: 'hclm',
	                hiddenName: 'hclm',
	                tabIndex: 13,
									anchor:'90%',
	                fieldLabel: '何处来',
	                xtype: 'DictTextField',
					        dict:{
										vtype:'xzqhgj',
										postDetail: 'xzqh',
										ignore: false,
										tid: 'hclm',
										vid: 'hcl'
									}
	            }, {
	                name: 'zy',
	                hiddenName: 'zy',
	                tabIndex: 16,
									anchor:'90%',
	                fieldLabel: '职业',	                
	                xtype: 'DictComboBox',
					        dict:{
										vtype:'zy',
										ignore: true
									}
	            },{id:'cpszm',
	                name: 'cpszm',
	                hiddenName: 'cpszm',
	                tabIndex: 19,
									anchor:'90%',
	                fieldLabel: '车牌首字',
	                xtype: 'DictTextField',
					        dict:{
										vtype:'cpsz',
										ignore: false,
										tid: 'cpszm',
										vid: 'cpsz'
									}
	            },{
	                name: 'bz',
	                xtype: 'textarea',
	                tabIndex: 22,
									anchor:'90%',
									height:40,
									maxLength: 200,					
	                fieldLabel: '备注'                
	            }]
			     }, {
					layout: 'form',
			        //autoHeight: true,
			        columnWidth: 0.28,
			        defaultType: 'textfield',
			        defaults: {disabledClass: ''},
			        items: [ {
	                name: 'zjlx',
	                allowBlank: false,
	                hiddenName: 'zjlx',
	                anchor:'90%',
	                tabIndex: 2,
	                fieldLabel: '证件类型',	                
	                xtype: 'DictComboBox',
					dict:{
						def:'11',
						vtype:'zjlx',
						ignore: false
					},
					listeners:{
			                	blur:function(f){
			                	  var lx= lkForm.getForm().findField("zjlx").getValue();
				                	  if(lx=='98'){ //无证人员自动给编号
					                	   Ext.Ajax.request({
													url:'nwb.do?method=getWzrybh&_dc=' + (new Date()).getTime(),
													method:'POST', 
													success:function(result,request){ 
														//mask.hide();
														var jsonData = Ext.util.JSON.decode(result.responseText);
														if(jsonData.success){
															if(jsonData.success && jsonData.other){
																/**
																	2018.04.17
																	删除	刁杰
																	选择无证人员后不自动设置证件号码
																 */
															 	//lkForm.getForm().findField("zjhm").setValue( jsonData.other);
															  
															}
														}
													}, 
													failure: function ( result, request) {
														//mask.hide();
														Ext.MessageBox.alert('设置编号出错',result.responseText); 
													}
												});
				                	  }
			                	}
			        }
	            }, {
	                name: 'xb',
	                allowBlank: false,
	                hiddenName: 'xb',
	                tabIndex: 5,
					anchor:'90%',
	                fieldLabel: '性别',	                
	                xtype: 'DictComboBox',
					        dict:{
										vtype:'xb',
										ignore: false
									}
	            }, {id:'ssxqm',
	                name: 'ssxqm',
	                allowBlank: false,
	                tabIndex: 8,
					anchor:'90%',
	                fieldLabel: '户籍区划',
	                xtype: 'DictTextField',
					        dict:{
										vtype:'xzqhgj',
										postDetail: 'xzqh',
										ignore: false,
										tid: 'ssxqm',
										vid: 'ssxq'
									}
					    },
					  /*  
					    {
	                name: 'rzfh',
	                allowBlank: false,
	                tabIndex: 11,
									anchor:'90%',
	                fieldLabel: lt==Gnt.Auth.LGTYPE_XY ? '手牌号':'入住房号',
	                xtype: 'combo',
			        store: fhStore,
			        displayField:'fh',
			        valueField: 'fh',			        
			        queryParam: 'fh',
 							minChars: 2, 			        
    					pageSize: 20,
    					listWidth:240,
			        forceSelection: true,
			        typeAhead: true,
			        triggerAction: 'all',
			        emptyText:'请选择...',
			        selectOnFocus:true,
			        listeners: {
			    			'select': function() {
			    				temp_rzfj = this.getRawValue();
			    				fjrsnbStore.load({
			    					params: {fh: temp_rzfj,limit:1,start:0},
										callback : function(){
											temp_tsxx="<font color='red'>提示：</font>"+temp_rzfj+"房间已有在住";
											tsxx="";
											if (fjrsnbStore.getCount()>0 && fjrsnbStore.getAt(0).data.nbrs >0) {
												 temp_fjrzrsnb = fjrsnbStore.getAt(0).data.nbrs;
												 temp_fjztnb = "&nbsp内宾<a herf=\"#\" style=\"cursor:hand\" title=\"点击查看详情\" onclick=\"parent.openWorkSpace('nwb/zznbcx.jsp?rzfh=" + temp_rzfj+"','在住内宾查询','LKZZBNCX_"+temp_rzfj+"');\"><font color='red'><u>"+temp_fjrzrsnb+"人</u></font></a>";
										  }else{
										  	temp_fjztnb="";	
										  }
										  if(""!=temp_fjztnb){
										    tsxx=temp_tsxx+temp_fjztnb
										  }
										  label_fjzt.el.dom.innerHTML=tsxx;
										  
										  
										  fjrswbStore.load({
											  params: {fh: temp_rzfj,limit:1,start:0},
												callback : function(){
													if (fjrswbStore.getCount()>0 && fjrswbStore.getAt(0).data.wbrs >0 ) {
														 temp_fjrzrswb = fjrswbStore.getAt(0).data.wbrs;
														 temp_fjztwb = "&nbsp外宾<a herf=\"#\" style=\"cursor:hand\" title=\"点击查看详情\" onclick=\"parent.openWorkSpace('nwb/zzwbcx.jsp?rzfh=" + temp_rzfj+"','在住外宾查询','LKZZWNCX_"+temp_rzfj+"');\"><font color='red'><u>"+temp_fjrzrswb+"人</u></font></a>";
												  }else{
												  	temp_fjztwb="";	
												  }
												  if(""!=temp_fjztwb){
												    tsxx=temp_tsxx+temp_fjztnb+temp_fjztwb;
												    label_fjzt.el.dom.innerHTML=tsxx;
												  }
												}
										});
										}
									});
									
			    			}
	    				}
	            },*/
	            {
	                name: 'lxdh',
	                tabIndex: 11,
					anchor:'90%',
					maxLength: 20,
	                fieldLabel: '联系电话'
	            }
	             ,
	             {id:'hcqm',
	                name: 'hcqm',
	                hiddenName: 'hcqm',
	                tabIndex: 14,
					anchor:'90%',
	                fieldLabel: '何处去',
	                xtype: 'DictTextField',
					        dict:{
										vtype:'xzqhgj',
										postDetail: 'xzqh',
										ignore: false,
										tid: 'hcqm',
										vid: 'hcq'
									}
	            }, {
	                name: 'xyklx',
	                tabIndex: 17,
					anchor:'90%',
					maxLength: 10,
	                fieldLabel: '信用卡类型'
	            },{
	                name: 'cphm',
	                tabIndex: 20,
					anchor:'90%',
					maxLength: 20,
	                fieldLabel: '车牌号码'                
	            },{
	            	name: 'sftd',
	            	xtype: 'checkbox',
	            	checked: false,
	            	tabIndex: 23,
					//anchor:'90%',
	            	fieldLabel: '团队登记'
	            }]
	        }, {
				layout: 'form',
				//autoHeight: true,
	            columnWidth: 0.28,
	            defaultType: 'textfield',
	            defaults: {disabledClass: ''},
	            items: [ {
	                name: 'zjhm',
	                id:'zjhm',
	                allowBlank: false,
	                tabIndex: 3,
					anchor:'90%',
					maxLength: 18,					
	                fieldLabel: '证件号码'
	                /*
	                listeners:{
			                	blur:function(f){
			                		var hz = f.getValue();
			                		var tt_zjlx = lkForm.getForm().findField("zjlx");
    											if (tt_zjlx.getValue()=='11') {
			                			if(hz!=""){
			                				hz=hz.substring(0,6);
			                				//查询拼音头
																Ext.Ajax.request({
																	url:'nwb.do?method=zhQhdz&_dc=' + (new Date()).getTime(),
																	method:'POST', 
																	params:{qhbm:hz},
																	success:function(result,request){ 
																		mask.hide();
																		var jsonData = Ext.util.JSON.decode(result.responseText);
																		if(jsonData.success){
																			if(jsonData.success && jsonData.other){
																			   // Ext.getCmp("ssxqm").setValue(jsonData.other);
																				lkForm.getForm().findField("ssxqm").setValue(jsonData.other);
																				lkForm.getForm().findField("ssxq").setValue(hz);
																				lkForm.getForm().findField("hclm").setValue(jsonData.other);
																				lkForm.getForm().findField("hcl").setValue(hz);
																			}
																		}
																	}, 
																	failure: function ( result, request) {
																		mask.hide();
																		Ext.MessageBox.alert('查询地址出错',result.responseText); 
																	} 
																}); 	
			                			}
			                		}
			                		frm.findField("rzsj").setValue(cur_datetime);
			                		
			                	}
			                }
			                */
	            },{
	                name: 'mz',
	                allowBlank: false,
	                hiddenName: 'mz',
	                tabIndex: 6,
									anchor:'90%',
	                fieldLabel: '民族',	                
	                xtype: 'DictComboBox',
					        dict:{
										vtype:'mz',
										def: '01',
										ignore: false
									}
	            },{
	                name: 'xz',
	                allowBlank: false,
	                tabIndex: 9,
									anchor:'90%',
									maxLength: 50,
	                fieldLabel: '详细地址' 
	                ,
	                listeners:{
			                	blur:function(f){
			                		var qj = f.getValue();
			                		lkForm.getForm().findField("xz").setValue(ToCDB(qj));
			                		//frm.findField("rzsj").setValue(cur_datetime);
			                	}
	                }
	            },{
	                name: 'tfsj',
	                tabIndex: 12,
					anchor:'90%',
	                xtype: 'gnt.datetimefield',	                
	                //maxValue:'', 
	                fieldLabel: '退房时间',
	                menuListeners: {
				    				select: function(m, d) {
				    					  var tfsj_nyr = d.format('Ymd');
				    					  tfsj_nyr = tfsj_nyr+ cur_datetime.substring(8,12);
				    					  this.setValue(tfsj_nyr);
				    					}
				    			}
	            },{
	                name: 'lcsy',
	                hiddenName: 'lcsy',
	                tabIndex: 15,
									anchor:'90%',
	                fieldLabel: '来此事由',	                
	                xtype: 'DictComboBox',
					        dict:{
										vtype:'lcsy',
										ignore: true
									}
	            },{
	                name: 'xykhm',
	                tabIndex: 18,
									anchor:'90%',
									maxLength: 19,					
	                fieldLabel: '信用卡号码'                
	            },{
	                name: 'sfzhsw',
	                tabIndex: 18,
									anchor:'90%',
									maxLength: 19,					
	                fieldLabel: '<font color=red>身份证后四位</font>'                
	            },{
	                name: 'wzyzm',
	                tabIndex: 18,
									anchor:'90%',
									maxLength: 19,					
	                fieldLabel: '<font color=red>身份识别码</font>'                
	            },{
	                name: 'xykhm',
	                tabIndex: 18,
	                xtype: 'button',
	                text:'引用网证身份信息',
				
					listeners: {
						click: function() {
							querywz();
						}
					}             
	            },
	            label_fjzt]
	        },{
							layout: 'form',
	            columnWidth: 0.14,
	            id: 'zpPanel',
							html: zpHtml
	        }
	       
        ],        
		buttons: [{
					id: 'btn-save',
					name: 'btn-save',
					text: '保存(F9)',
					listeners: {
						click: function() {
							check();
						}
					}
        },{
	        id: 'btn-new',
			name: 'btn-new',
            text: '新增',
            listeners: {
		        	click: function() {
            	//是否团队登记 ，团队登记保留部分信息
            	sftd = frm.findField("sftd").getValue();
            	if (sftd) {
            		zjlx = frm.findField("zjlx").getValue();
            		zjlxm = frm.findField("zjlxm").getValue();
								hcl = frm.findField("hcl").getValue();
								hclm = frm.findField("hclm").getValue();
								hcq = frm.findField("hcq").getValue();
								hcqm = frm.findField("hcqm").getValue();
								lcsy = frm.findField("lcsy").getValue();
								lcsym = frm.findField("lcsym").getValue();
								xyklx = frm.findField("xyklx").getValue();
								bz = frm.findField("bz").getValue();					
								
								frm = lkForm.getForm().reset();
								frm.findField("sftd").setValue(sftd);
					
	        			frm.findField("hcl").setValue(hcl);
	        			frm.findField("hclm").setValue(hclm);
	        			frm.findField("hcq").setValue(hcq);
	        			frm.findField("hcqm").setValue(hcqm);
	        			frm.findField("lcsy").setValue(lcsy);
	        			frm.findField("lcsym").setValue(lcsym);
	        			frm.findField("xyklx").setValue(xyklx);
	        			frm.findField("bz").setValue(bz);      
	        			
	        			
            	} else {
            		frm = lkForm.getForm().reset();
            		lkForm.getForm().findField("ssxqm").setValue("");
								lkForm.getForm().findField("ssxq").setValue("");

            	}
            	Ext.getCmp("btn-save").enable();
            	frm.findField("rzsj").setValue(cur_datetime);
            	frm.findField("zjlx").setValue('11');
            	frm.findField("mz").setValue('01');   
        	  
		          lkForm.getForm().findField("hcqm").setValue(inithcqm);
		          lkForm.getForm().findField("hcq").setValue(inithcq);
	       			frm.findField("zjhm").enable();
	       			frm.findField("zjlx").enable();
	       			//frm.findField("rzsj").enable();
	       			if(Ext.getCmp('btn-edz')){
	       			 Ext.getCmp('btn-edz').enable();
		        	}
            	operElement("imgReader","clear","","f");
            	Ext.fly("zpImg").dom.src=initLink+ '?_dc=' + (new Date()).getTime();
            	v_lkbm ="";
            	t_zpisnull=true;
            }}
        },{
	        	id: 'btn-new4',
				name: 'btn-new4',
                text: '引用微信核验通过旅客',
                listeners: {
		        	click: function() {
            	//人证核验
		       	
            	tztgListWin.show();
            }}
        }/*,{
					id: 'btn-save',
					name: 'btn-save',
					text: '扫描身份证',
					listeners: {
						click: function() {
							RecognizeImg("nb",2,lkForm.getForm(),mzStore);
	    				
	    				//getbase64(lkForm.getForm());
						}
					}
        },{
				id: 'btn-save12',
			name: 'btn-save12',
            text: "无证入住二维码",
            handler: function() {
            	parent.openwork('zfewm.jsp?lgbm='+lgbm+'&lgmc='+lgmc,'无证入住二维码','ewm');
            }
			}*/],
   
		method: 'POST'
    });
  
		
    var mask = new Ext.LoadMask(Ext.getBody(), {msg:"操作正在执行中，请等待..."});
    //保存提交
    function saveNb(param){
    	mask.show();
    	
    	lkForm.getForm().submit({
    	//Ext.Ajax.request({
				url:'nwb.do?method=saveNb&_dc=' + (new Date()).getTime(),
				method:'POST', 
				//params:param,
				success:function(form,action){
				//success:function(result,request){
					
					mask.hide();
					var jsonData = Ext.util.JSON.decode(action.response.responseText);
					
					if(jsonData.success){
								//回显照片
								if (v_lkbm!="") {		
									var imgsrc = "";
									var imgsrc2 = "";//'util/upfile?type=lkzp&lkbm=' + jsonData.list[0].lkbm + '&_dc=' + (new Date()).getTime();
									imgsrc = 'util/upfile?type=lkzp&lkbm=' + v_lkbm + '&zjlx='+lkForm.getForm().findField("zjlx").getValue()+'&zjhm=' + lkForm.getForm().findField("zjhm").getValue() + '&_dc=' + (new Date()).getTime();
									
									Ext.fly("zpImg").dom.src = imgsrc;
									
									//operElement("imgReader","zpChanged","0");
									
									//frm.findField("zp").setValue("");
									
								}
								
								//信息保存后证件号码和入住时间不能修改,不让读取二代证
								//lkForm.getForm().findField("zjhm").disable();
								//lkForm.getForm().findField("zjlx").disable();
			   				lkForm.getForm().findField("rzsj").disable();
                            
			   				if(Ext.getCmp('btn-edz')){
			   				   Ext.getCmp('btn-edz').disable();
					        }
			   				//旅客编码
			   				lkForm.getForm().findField("lkbm").setValue(jsonData.list[0].lkbm);
			   				lkForm.getForm().findField("lkbm2").setValue(jsonData.list[0].lkbm);
			   				//退房时间 已退房不让修改
			   				tfsj = lkForm.getForm().findField("tfsj").getValue();
			   				if ( tfsj && tfsj != "") {
									Ext.getCmp('btn-save').disable();
								}
								//提示信息
								if(jsonData.messages && jsonData.messages.length>0)
									Ext.Msg.alert("提示",jsonData.messages[0]);
								
								//保存后新增
								if(v_lkbm ==""||v_lkbm =="rx"){
									Ext.getCmp("btn-new").fireEvent('click');
								}
								return;
					}else if(jsonData.errors){
						
					}
					//错误信息提示
					show("错误",jsonData.messages[0],Ext.MessageBox.ERROR);
				}, 
				failure: function ( form,action) {
					mask.hide();
					var jsonData = Ext.util.JSON.decode(action.response.responseText);
					var errmessage="保存出错，请联系管理员。"
					//提示信息
					if(jsonData.messages && jsonData.messages.length>0){
					  errmessage = jsonData.messages[0]
					}
					Ext.MessageBox.alert('错误',errmessage); 
				} 
			}); 
		}
		//检查是否有汉字
	function  isHz(s) 
	
	{
	
   if (escape(s).indexOf("%u")<0)
    {
      // alert( "没有包含中文" );
       return false;
    } else {
       //alert( "包含中文" );
       return true;
     }
	}
		//读取二代证按钮
    	if (lgEdz=='1' || lgEdz == '2' || lgEdz == '3' || lgEdz == '4' || lgEdz == '5') {
	    	lkForm.addButton({
	    		id: 'btn-edz',
	    		name: 'btn-edz', 
	    		text: '读取二代证 (F8)',
	    		listeners: {
	    			'click': function() {
	    				//先清空内容 
	    				Ext.getCmp('btn-new').fireEvent('click');
	    				//
	    				checkOcx(0);
	    				ci = readCard(0);
	    				if (ci.lastError != "") {
	    					Gnt.MsgBox.showError(ci.lastError);
	    				} else {
	    					
	    					if (ci.Name != "") {
		    					frm = lkForm.getForm();
		    					var mz=ci.Nation;
		    					if(isHz(mz)){mz=getMzcode(mz);}
		    					var sex=ci.Sex;
		    					//if(mz<9){mz='0'+mz;}
		    					if(sex=='男'){sex='1';}else if(sex=='女'){sex='2';}
		    					frm.findField('xb').setValue(sex);
		    					frm.findField('xm').setValue(ci.Name);
		    					frm.findField('mz').setValue(mz);
		    					frm.findField('zjlx').setValue('11');
		    					frm.findField('csrq').setValue(csrqgsh(ci.Born));
		    					frm.findField('zjhm').setValue(ci.CardNo);
		    					frm.findField('xz').setValue(ToCDB(ci.Address));
		    					//frm.findField('zp').setValue(ci.picbase64);
		    					//同证件未退房不能登记
		    					nbzzStore.removeAll();
				    			nbzzStore.load({
							    	params: {zjlx:'11',zjhm: ci.CardNo,_dc2:(new Date()).getTime()},
							    	callback: function() {
							    		
							    	  if (nbzzStore.getCount()>0) {
							    	    Ext.Msg.alert("提示","身份证:"+ci.CardNo+"还没有退房，不能重复登记，请检查！");	
							    	    Ext.getCmp("btn-new").fireEvent('click');
							    	    return;
							    	  }
							    	  
							    	  //设置住址
							    	  var t_hjd = ci.CardNo.substr(0,6);
		    						Ext.Ajax.request({
												url:'nwb.do?method=zhQhdz&_dc=' + (new Date()).getTime(),
												method:'POST', 
												params:{qhbm:t_hjd},
												success:function(result,request){ 
													mask.hide();
													var jsonData = Ext.util.JSON.decode(result.responseText);
													if(jsonData.success){
														if(jsonData.success && jsonData.other){
														   // Ext.getCmp("ssxqm").setValue(jsonData.other);
															lkForm.getForm().findField("ssxqm").setValue(jsonData.other);
															lkForm.getForm().findField("ssxq").setValue(t_hjd);
															lkForm.getForm().findField("hclm").setValue(jsonData.other);
															lkForm.getForm().findField("hcl").setValue(t_hjd);
														}
													}
												}, 
												failure: function ( result, request) {
													mask.hide();
													Ext.MessageBox.alert('查询地址出错',result.responseText); 
												} 
											}); 
											
							    	}
							    })
		    					lkForm.getForm().findField("rzsj").setValue(cur_datetime);
		    					document.getElementById("imgReader").loadFile(ci.PhotoPath);
		    					var str = operElement("imgReader","getAotoPicture","","f");
		                		
		                		if (str!="") {
		                			//operElement("zpImgShower","showImgFromBase64","","f",str);
	                				lkForm.getForm().findField("zp").setValue(str);
	                				}
		    					//document.getElementById("zpImgShower").loadFile(ci.PhotoPath);
		    					//operElement("imgReader","loadFile","","f",ci.PhotoPath);
		    				}
		    				//initLink='data:image/jpeg;base64,'+str;
		    				//Ext.fly("zpImg").dom.src=initLink+'?_dc=' + (new Date()).getTime();
		    				//Ext.fly("zpImg").dom.src=ci.PhotoPath+"?d="+(new Date()).getTime();
		    				//Ext.fly("zpImg").dom.src="c:\\zp.bmp?d="+(new Date()).getTime();
		    				//Ext.fly("zpImg").dom.src=ci.PhotoPath+"?d="+(new Date()).getTime();
		    				
		    				/**
		    					2018.04.08
		    					新增	刁杰
		    					F8读取完证件信息后光标跳转到入住房号
		    				 */
		    				Ext.get("rzfh").dom.focus();
		    				
	    				}
	    			}
	    		}
	    	});
    	}
    //读取居住证按钮
    	if (lgEdz == '2') {
	    	lkForm.addButton({
	    		id: 'btn-edz2',
	    		name: 'btn-edz2', 
	    		text: '读取港澳台居民居住证',
	    		listeners: {
	    			'click': function() {
	    				//先清空内容 
	    				Ext.getCmp('btn-new').fireEvent('click');
	    				//
	    				checkOcx(1);
	    				ci = readCard(1);
	    				if (ci.lastError != "") {
	    					Gnt.MsgBox.showError(ci.lastError);
	    				} else {
	    					
	    					if (ci.Name != "") {
		    					frm = lkForm.getForm();
		    					var mz=ci.NationL;
		    					var sex=ci.Sex;
		    					if(ci.NationL<9){mz='0'+ci.NationL;}
		    					if(sex=='男'){sex='1';}else if(sex=='女'){sex='2';}
		    					frm.findField('xb').setValue(sex);
		    					frm.findField('xm').setValue(ci.Name);
		    					frm.findField('mz').setValue('01');
		    					frm.findField('csrq').setValue(csrqgsh(ci.Born));
		    					frm.findField('zjhm').setValue(ci.CardNo);
		    					frm.findField('zjlx').setValue('95');
		    					frm.findField('xz').setValue(ToCDB(ci.Address));
		    					//frm.findField('zp').setValue(ci.picbase64);
		    					//同证件未退房不能登记
		    					nbzzStore.removeAll();
				    			nbzzStore.load({
							    	params: {zjlx:'11',zjhm: ci.CardNo,_dc2:(new Date()).getTime()},
							    	callback: function() {
							    		
							    	  if (nbzzStore.getCount()>0) {
							    	    Ext.Msg.alert("提示","身份证:"+ci.CardNo+"还没有退房，不能重复登记，请检查！");	
							    	    Ext.getCmp("btn-new").fireEvent('click');
							    	    return;
							    	  }
							    	  
							    	  //设置住址
							    	  var t_hjd = ci.CardNo.substr(0,6);
		    						Ext.Ajax.request({
												url:'nwb.do?method=zhQhdz&_dc=' + (new Date()).getTime(),
												method:'POST', 
												params:{qhbm:t_hjd},
												success:function(result,request){ 
													mask.hide();
													var jsonData = Ext.util.JSON.decode(result.responseText);
													if(jsonData.success){
														if(jsonData.success && jsonData.other){
														   // Ext.getCmp("ssxqm").setValue(jsonData.other);
															lkForm.getForm().findField("ssxqm").setValue(jsonData.other);
															lkForm.getForm().findField("ssxq").setValue(t_hjd);
															lkForm.getForm().findField("hclm").setValue(jsonData.other);
															lkForm.getForm().findField("hcl").setValue(t_hjd);
														}
													}
												}, 
												failure: function ( result, request) {
													mask.hide();
													Ext.MessageBox.alert('查询地址出错',result.responseText); 
												} 
											}); 
											
							    	}
							    })
		    					
								lkForm.getForm().findField("rzsj").setValue(cur_datetime);
		    					document.getElementById("imgReader").loadFile(ci.PhotoPath);
		    					var str = operElement("imgReader","getAotoPicture","","f");
		                		
		                		if (str!="") {
		                			//operElement("zpImgShower","showImgFromBase64","","f",str);
	                				lkForm.getForm().findField("zp").setValue(str);
	                				}
		    					//document.getElementById("zpImgShower").loadFile(ci.PhotoPath);
		    					//operElement("imgReader","loadFile","","f",ci.PhotoPath);
		    				}
		    				//initLink='data:image/jpeg;base64,'+str;
		    				//Ext.fly("zpImg").dom.src=initLink+'?_dc=' + (new Date()).getTime();
		    				//Ext.fly("zpImg").dom.src=ci.PhotoPath+"?d="+(new Date()).getTime();
		    				//Ext.fly("zpImg").dom.src="c:\\zp.bmp?d="+(new Date()).getTime();
		    				
		    				/**
		    					2018.04.08
		    					新增	刁杰
		    					F8读取完证件信息后光标跳转到入住房号
		    				 */
		    				Ext.get("rzfh").dom.focus();
		    				
	    				}
	    			}
	    		}
	    	});
    	}
        //延展公司无证核验按钮
    	if (lgRxbdflag == '0') {
	    	lkForm.addButton({
	    		id: 'btn-edz33',
	    		name: 'btn-edz23', 
	    		text: '人证一致核验(F7)',
	    		listeners: {
	    			'click': function() {
	    				//先清空内容 
	    				Ext.getCmp('btn-new').fireEvent('click');
	    				//
	    				rxbd();
		    				
	    				}
	    			
	    		}
	    	});
    	} //洗点无证核验按钮
    	if (lgRxbdflag == '3') {
	    	lkForm.addButton({
	    		id: 'btn-edz33',
	    		name: 'btn-edz23', 
	    		text: '无证核验',
	    		listeners: {
	    			'click': function() {
	    				//先清空内容 
	    				Ext.getCmp('btn-new').fireEvent('click');
	    				//
	    				rxbd(20);
		    				
	    				}
	    			
	    		}
	    	});
    	}
    	//洗点人证一致核验
    	if (lgRxbdflag == '3') {
	    	lkForm.addButton({
	    		id: 'btn-edz33',
	    		name: 'btn-edz23', 
	    		text: '人证一致核验',
	    		listeners: {
	    			'click': function() {
	    				//先清空内容 
	    				Ext.getCmp('btn-new').fireEvent('click');
	    				//
	    				rxbd(10);
		    				
	    				}
	    			
	    		}
	    	});
    	}//马鞍山无证核验
    	//池州加载3417 
    	if(lgRxbdflag == '3' && ENABLEDXD == '2' && v_lkbm ==""){
    		Ext.getCmp("xm").readOnly=true;
    	}else{
    		Ext.getCmp("xm").readOnly=false;
    	}
    	
    	
    	if(SMSB_ISOPEN=='1'){
    		lkForm.addButton({
        		id: 'btn-edz33',
        		name: 'btn-edz23', 
        		text: '身份证识别',
        		listeners: {
        			'click': function() {
        				//先清空内容 
        				//Ext.getCmp('btn-new').fireEvent('click');
        				if (operElement("imgReader","zpChanged") != "0" && operElement("imgReader","zpChanged") != null) {
    		    			
        			     var base64zp=operElement("imgReader","getAotoPicture","","f");
    						Ext.Ajax.request({
            					url:'nwb.do?method=querysfzsb',
            					method:'POST', 
            					params:{base64zp:base64zp},
            					success:function(result,request){
            						if(result.responseText!=""){
            							
            						
            						var jsonData = Ext.util.JSON.decode(result.responseText);
            						
        						 if(jsonData.success){
        							var data=Ext.util.JSON.decode(jsonData.list[0]);
        							if(data!=null){
        								var sfzhm=data.zjhm;
            							var mz=data.mzm;
            							var sex=data.xbm
            							var xm=data.xm;
            							var dz=data.xz;
            							//var sgnisuBrnchNm=data.sgnisuBrnchNm;//签发机构
            							var csrq=data.csrq;
            							//var credEffDate=data.credEffDate;//证件生效日期
            							//var credInvldDate=data.credInvldDate;//证件过期日期
            							lkForm.getForm().findField("xm").setValue(xm);
            							lkForm.getForm().findField("zjhm").setValue(sfzhm);
            							lkForm.getForm().findField("csrq").setValue(csrq);
            							lkForm.getForm().findField("bz").setValue("身份证扫描识别结果");
            							
            							lkForm.getForm().findField("xz").setValue(dz);
            							if(sex=='男'){sex='1';}else if(sex=='女'){sex='2';}
            							lkForm.getForm().findField('xb').setValue(sex);
            							
            							var imgsrc = "data:image/jpeg;base64,"+data.tx+"" ;
            							var imgsrc2 = "data:image/jpeg;base64,"+data.tx+"" ;
        							    Ext.fly("zpImg").dom.src = imgsrc;
        							    Ext.fly("hzImg").dom.src = imgsrc2;
            							lkForm.getForm().findField('zp').setValue(data.tx);
            							lkForm.getForm().findField('hz').setValue(data.tx);
            							//Ext.fly("zpImg").dom.src = 'util/upfile?type=rzzp&sfzhm='+sfzhm+ '&_dc=' + (new Date()).getTime();
            							if(csrq==null){
            								
            								xb = getXb(sfzhm);
                			    			csrq = getCsrq(sfzhm).format('Ymd');
                			    			lkForm.getForm().findField("csrq").setValue(csrq);
                			    			lkForm.getForm().findField("xb").setValue(xb);
            							}
            							
            							
            							//设置住址
        						    	  var t_hjd = sfzhm.substr(0,6);
        	    						Ext.Ajax.request({
        											url:'nwb.do?method=zhQhdz&_dc=' + (new Date()).getTime(),
        											method:'POST', 
        											params:{qhbm:t_hjd},
        											success:function(result,request){ 
        												mask.hide();
        												var jsonData = Ext.util.JSON.decode(result.responseText);
        												if(jsonData.success){
        													if(jsonData.success && jsonData.other){
        													   // Ext.getCmp("ssxqm").setValue(jsonData.other);
        														lkForm.getForm().findField("ssxqm").setValue(jsonData.other);
        														lkForm.getForm().findField("ssxq").setValue(t_hjd);
        														lkForm.getForm().findField("hclm").setValue(jsonData.other);
        														lkForm.getForm().findField("hcl").setValue(t_hjd);
        													}
        												}
        											}, 
        											failure: function ( result, request) {
        												mask.hide();
        												Ext.MessageBox.alert('查询地址出错',result.responseText); 
        											} 
        										}); 
        							
        							}else{
        								alert("无最新数据!");
        							}
        							
        						}
            					
            					}else{
            						alert("无最新数据!");
            					}
            					}, 
            					failure: function ( result, request) {
            						
            						Ext.MessageBox.alert('查询人证比对数据出错',result.responseText); 
            					} 
            				});
    						
    						
        			}else{
        				alert("请先选择需要扫描的身份证照片!");
        				return;
        			}
    						
    						
        				}
        			
        		}
        	});
    	}
    	
    	
    	if (lgRxbdflag == '10') {//沃德一体机
    		
    		lkForm.addButton({
        		id: 'btn-edz33',
        		name: 'btn-edz23', 
        		text: '带入数据',
        		listeners: {
        			'click': function() {
        				
                         var url="nwb.do?method=queryWDRzbd";//地市库
                         if(requestType=='1'){//远程库
                        	 url="drsj";
                         }
        		    	Ext.Ajax.request({
        					url:url,
        					method:'POST', 
        					params:{lgbm:lgbm},
        					success:function(result,request){
        						if(result.responseText!=""){
        							
        						
        						var jsonData = Ext.util.JSON.decode(result.responseText);
        						
    						 if(jsonData.success){
    							var data=jsonData.list[0];
    							if(data!=null){
    								var sfzhm=data.idNo;
        							var mz=data.ethnicNmp;
        							var sex=data.genCd
        							var xm=data.idDocName;
        							var dz=data.idDocAddr;
        							var sgnisuBrnchNm=data.sgnisuBrnchNm;//签发机构
        							var csrq=data.birthDate;
        							var credEffDate=data.credEffDate;//证件生效日期
        							var credInvldDate=data.credInvldDate;//证件过期日期
        							lkForm.getForm().findField("xm").setValue(xm);
        							lkForm.getForm().findField("zjhm").setValue(sfzhm);
        							lkForm.getForm().findField("csrq").setValue(csrq);
        							lkForm.getForm().findField("bz").setValue("沃德一体机核验结果");
        							
        							lkForm.getForm().findField("xz").setValue(dz);
        							if(sex=='男'){sex='1';}else if(sex=='女'){sex='2';}
        							lkForm.getForm().findField('xb').setValue(sex);
        							
        							var imgsrc = "data:image/jpeg;base64,"+data.base64zp+"" ;
        							var imgsrc2 = "data:image/jpeg;base64,"+data.base64zp2+"" ;
    							    Ext.fly("zpImg").dom.src = imgsrc;
    							    Ext.fly("hzImg").dom.src = imgsrc2;
        							lkForm.getForm().findField('zp').setValue(data.base64zp);
        							lkForm.getForm().findField('hz').setValue(data.base64zp2);
        							//Ext.fly("zpImg").dom.src = 'util/upfile?type=rzzp&sfzhm='+sfzhm+ '&_dc=' + (new Date()).getTime();
        							if(csrq==null){
        								
        								xb = getXb(sfzhm);
            			    			csrq = getCsrq(sfzhm).format('Ymd');
            			    			lkForm.getForm().findField("csrq").setValue(csrq);
            			    			lkForm.getForm().findField("xb").setValue(xb);
        							}
        							
        							
        							//设置住址
    						    	  var t_hjd = sfzhm.substr(0,6);
    	    						Ext.Ajax.request({
    											url:'nwb.do?method=zhQhdz&_dc=' + (new Date()).getTime(),
    											method:'POST', 
    											params:{qhbm:t_hjd},
    											success:function(result,request){ 
    												mask.hide();
    												var jsonData = Ext.util.JSON.decode(result.responseText);
    												if(jsonData.success){
    													if(jsonData.success && jsonData.other){
    													   // Ext.getCmp("ssxqm").setValue(jsonData.other);
    														lkForm.getForm().findField("ssxqm").setValue(jsonData.other);
    														lkForm.getForm().findField("ssxq").setValue(t_hjd);
    														lkForm.getForm().findField("hclm").setValue(jsonData.other);
    														lkForm.getForm().findField("hcl").setValue(t_hjd);
    													}
    												}
    											}, 
    											failure: function ( result, request) {
    												mask.hide();
    												Ext.MessageBox.alert('查询地址出错',result.responseText); 
    											} 
    										}); 
    							
    							}else{
    								alert("无最新数据!");
    							}
    							
    						}
        					
        					}else{
        						alert("无最新数据!");
        					}
        					}, 
        					failure: function ( result, request) {
        						
        						Ext.MessageBox.alert('查询人证比对数据出错',result.responseText); 
        					} 
        				});
        				}
        			
        		}
        	});
    		
    		lkForm.addButton({
	    		id: 'btn-edz33',
	    		name: 'btn-edz23', 
	    		text: '核验清单',
	    		listeners: {
	    			'click': function() {
	    				//先清空内容 
	    				//Ext.getCmp('btn-new').fireEvent('click');
	    				if (parent.openWorkSpace) {
							cfWin.show();
							hyStore.removeAll();
							hyStore.baseParams = {
								lgbm : lgbm
							};
							hyStore.load({
										params : {
											start : 0,
											limit : pageSize,
											op : "query"
										}
									})
						}
		    				
	    				}
	    			
	    		}
	    	});
    	}
    	
    	var hyStore = new Ext.data.JsonStore({
			url : 'nwb.do?method=queryWDRzbdList',
			root : 'list',
			id : 'id',
			totalProperty : 'totalCount',
			fields : ["id", "idNo", "detailReason", "idDocName", "createDate",
			          "genCd","base64zp","base64zp2","birthDate","idDocAddr","ethnicNmp"
			],
			listeners : {
				load : function(mystore, res) {

				},
				loadexception : function(mystore, options, response, error) {
					if (error) {
						var json = Ext.decode(response.responseText);
						Ext.Msg.alert("提示", json.messages[0]);
					} else {
						Ext.Msg.alert("提示", response.responseText);
					}
				}
			}
		});
    	
    	var colhyModel = new Ext.grid.ColumnModel([
    	                               		{
    	                               		header : "姓名",
    	                               		width : 80,
    	                               		sortable : true,
    	                               		dataIndex : 'idDocName'
    	                               	}, {
    	                               		header : "身份证号码",
    	                               		width : 100,
    	                               		sortable : true,
    	                               		locked : false,
    	                               		dataIndex : 'idNo'
    	                               	},{
    	                               		header : "核验时间",
    	                               		width : 80,
    	                               		sortable : true,
    	                               		locked : false,
    	                               		dataIndex : 'createDate'
    	                               	}, {
    	                               		header : "核验结果",
    	                               		width : 100,
    	                               		sortable : true,
    	                               		locked : false,
    	                               		dataIndex : 'detailReason'
    	                               	}]);
    	
    	var grid = new Ext.grid.GridPanel({
    		id:'hygrid',
			store : hyStore,
			region : 'center',
			view : new Ext.grid.GridView({
						forceFit : true,
						autoFill : true,
						enableRowBody : true
					}),
			stripeRows : true,
			cm : colhyModel,
			loadMask : {
				msg : '正在加载数据，请稍侯……'
			},
			bodyStyle : 'width:100%',
			border : true,
			anchor : '100% 100%',
			margins : '0 0 0 0',
			cmargins : '0 0 0 0',
			frame : false,
			iconCls : 'icon-grid',
			title : '',
			bbar : new Ext.PagingToolbar({
						pageSize : 20,
						store : hyStore,
						displayInfo : true
					}),listeners : {
						dblclick : function() {
							//
							var r = Ext.getCmp("hygrid").getSelectionModel().getSelected();
							
                        if (r) {
                        	lkForm.getForm().findField("rzbdid").setValue(r.data.id);
                        	var sfzhm=r.data.idNo;
							var mz=r.data.ethnicNmp;
							var sex=r.data.genCd
							var xm=r.data.idDocName;
							var dz=r.data.idDocAddr;
							var sgnisuBrnchNm=r.data.sgnisuBrnchNm;//签发机构
							var csrq=r.data.birthDate;
                        	lkForm.getForm().findField("xm").setValue(xm);
                        	lkForm.getForm().findField("zjhm").setValue(sfzhm);
							lkForm.getForm().findField("csrq").setValue(csrq);
							lkForm.getForm().findField("bz").setValue("沃德一体机核验结果");
							
							lkForm.getForm().findField("xz").setValue(dz);
							
							if(sex=='男'){sex='1';}else if(sex=='女'){sex='2';}
							lkForm.getForm().findField('xb').setValue(sex);
							
							var imgsrc = "data:image/jpeg;base64,"+r.data.base64zp+"" ;
							var imgsrc2 = "data:image/jpeg;base64,"+r.data.base64zp2+"" ;
						    Ext.fly("zpImg").dom.src = imgsrc;
						    Ext.fly("hzImg").dom.src = imgsrc2;
							lkForm.getForm().findField('zp').setValue(r.data.base64zp);
							lkForm.getForm().findField('hz').setValue(r.data.base64zp2);
							
							if(csrq==null){
								
								xb = getXb(sfzhm);
    			    			csrq = getCsrq(sfzhm).format('Ymd');
    			    			lkForm.getForm().findField("csrq").setValue(csrq);
    			    			lkForm.getForm().findField("xb").setValue(xb);
							}
							
							
							//设置住址
					    	  var t_hjd = sfzhm.substr(0,6);
    						Ext.Ajax.request({
										url:'nwb.do?method=zhQhdz&_dc=' + (new Date()).getTime(),
										method:'POST', 
										params:{qhbm:t_hjd},
										success:function(result,request){ 
											mask.hide();
											var jsonData = Ext.util.JSON.decode(result.responseText);
											if(jsonData.success){
												if(jsonData.success && jsonData.other){
												   // Ext.getCmp("ssxqm").setValue(jsonData.other);
													lkForm.getForm().findField("ssxqm").setValue(jsonData.other);
													lkForm.getForm().findField("ssxq").setValue(t_hjd);
													lkForm.getForm().findField("hclm").setValue(jsonData.other);
													lkForm.getForm().findField("hcl").setValue(t_hjd);
												}
											}
										}, 
										failure: function ( result, request) {
											mask.hide();
											Ext.MessageBox.alert('查询地址出错',result.responseText); 
										} 
									});
                              cfWin.hide();
                              }
							//
						}
					}
		});
    	
    	var cfWin = new Ext.Window({
			title : '核验信息',
			closeAction : 'hide',
			modal : true,
			width : 700,
			height : 300,
			x:200,
			y:10,
			layout : 'fit',
			items : [grid],
			listeners : {
				show : function() {
				}
			}
		});
    	
    	if (lgRxbdflag == '6'||lgRxbdflag=='9') {
    		
    	lkForm.addButton({
    		id: 'btn-edz33',
    		name: 'btn-edz23', 
    		text: '人证核验',
    		listeners: {
    			'click': function() {
    				if(lgsn.indexOf(",")!=-1){
    					if(sn==null){
    						alert("请先选择核验的设备号");
        					Ext.get("sbh").dom.focus();
        					return;
    					}
    					
    				}
    				hytype="onceCompare";//人证核验
    				Ext.getCmp("btn-new").fireEvent('click');
    				Ext.getCmp("sbh").setValue(sn);
    				Ext.Ajax.request({
    					url:'nwb.do?method=rzbd',
    					method:'POST',
    					params:{lgsn:sn,hytype:hytype},
    					success:function(result,request){ 
    						
    						var jsonData = Ext.util.JSON.decode(result.responseText);
    						if(jsonData.success){
    							var msg=Ext.util.JSON.decode(jsonData.messages[0]);
    							if(msg.code=="0000"){
    								transactionID=msg.transactionID;
    								
    							}else{
    								alert(msg.message);
    							}
    						}
    					}, 
    					failure: function ( result, request) {
    						
    						Ext.MessageBox.alert('查询地址出错',result.responseText); 
    					} 
    				});
    				}
    			
    		}
    	});
    	
    	lkForm.addButton({
    		id: 'btn-edz33',
    		name: 'btn-edz23', 
    		text: '无证核验',
    		listeners: {
    			'click': function() {
    				if(lgsn.indexOf(",")!=-1){
    					if(sn==null){
    						alert("请先选择核验的设备号");
        					Ext.get("sbh").dom.focus();
        					return;
    					}
    					
    				}
    	
    				hytype="noIdCardCompare";//无证核验
    				Ext.getCmp("btn-new").fireEvent('click');
    				Ext.getCmp("sbh").setValue(sn);

    				Ext.Ajax.request({
    					url:'nwb.do?method=rzbd',
    					method:'POST',
    					params:{lgsn:sn,hytype:hytype},
    					success:function(result,request){ 
    						
    						var jsonData = Ext.util.JSON.decode(result.responseText);
    						if(jsonData.success){
    							var msg=Ext.util.JSON.decode(jsonData.messages[0]);
    							if(msg.code=="0000"){
    								transactionID=msg.transactionID;
    								
    							}else{
    								alert(msg.message);
    							}
    						}
    					}, 
    					failure: function ( result, request) {
    						
    						Ext.MessageBox.alert('查询地址出错',result.responseText); 
    					} 
    				});
    				}
    			
    		}
    	});
    	
    	//本地
    	lkForm.addButton({
    		id: 'btn-edz33',
    		name: 'btn-edz23', 
    		text: '带入数据',
    		listeners: {
    			'click': function() {
    				if(lgsn.indexOf(",")!=-1){
    					if(sn==null){
    						alert("请先选择核验的设备号");
        					Ext.get("sbh").dom.focus();
        					return;
    					}
    					
    				}
                     var url="nwb.do?method=queryRzbd";//地市库
                     if(requestType=='1'){//远程库
                    	 url="drsj";
                     }
    		    	Ext.Ajax.request({
    					url:url,
    					method:'POST', 
    					params:{lgsn:sn},
    					success:function(result,request){
    						if(result.responseText!=""){
    							
    						
    						var jsonData = Ext.util.JSON.decode(result.responseText);
    						if(requestType=='1'){
    							if(jsonData!=null){
    								var sfzhm=jsonData.idNo;
        							var mz=jsonData.ethnicNmp;
        							var sex=jsonData.genCd
        							var xm=jsonData.idDocName;
        							var dz=jsonData.idDocAddr;
        							var sgnisuBrnchNm=jsonData.sgnisuBrnchNm;//签发机构
        							var csrq=jsonData.birthDate;
        							var credEffDate=jsonData.credEffDate;//证件生效日期
        							var credInvldDate=jsonData.credInvldDate;//证件过期日期
        							lkForm.getForm().findField("xm").setValue(xm);
        							lkForm.getForm().findField("zjhm").setValue(sfzhm);
        							lkForm.getForm().findField("csrq").setValue(csrq);
        							lkForm.getForm().findField("xz").setValue(dz);
        							if(sex=='男'){sex='1';}else if(sex=='女'){sex='2';}
        							lkForm.getForm().findField('xb').setValue(sex);
        							
        							var imgsrc = "data:image/jpeg;base64,"+jsonData.base64zp+"" ;
								    Ext.fly("zpImg").dom.src = imgsrc;
								    lkForm.getForm().findField('zp').setValue(jsonData.base64zp);
        							//Ext.fly("zpImg").dom.src = 'util/upfile?type=rzzp&sfzhm='+sfzhm+ '&_dc=' + (new Date()).getTime();
        							//if(csrq==null){
        								
        								xb = getXb(sfzhm);
            			    			csrq = getCsrq(sfzhm).format('Ymd');
            			    			lkForm.getForm().findField("csrq").setValue(csrq);
            			    			lkForm.getForm().findField("xb").setValue(xb);
        							//}
        							
        							
        							//设置住址
							    	  var t_hjd = sfzhm.substr(0,6);
		    						Ext.Ajax.request({
												url:'nwb.do?method=zhQhdz&_dc=' + (new Date()).getTime(),
												method:'POST', 
												params:{qhbm:t_hjd},
												success:function(result,request){ 
													mask.hide();
													var jsonData = Ext.util.JSON.decode(result.responseText);
													if(jsonData.success){
														if(jsonData.success && jsonData.other){
														   // Ext.getCmp("ssxqm").setValue(jsonData.other);
															lkForm.getForm().findField("ssxqm").setValue(jsonData.other);
															lkForm.getForm().findField("ssxq").setValue(t_hjd);
															lkForm.getForm().findField("hclm").setValue(jsonData.other);
															lkForm.getForm().findField("hcl").setValue(t_hjd);
														}
													}
												}, 
												failure: function ( result, request) {
													mask.hide();
													Ext.MessageBox.alert('查询地址出错',result.responseText); 
												} 
											}); 
    							
    							}else{
    								alert("无最新数据!");
    							}
    						}else{
						 if(jsonData.success){
							var data=jsonData.list[0];
							if(data!=null){
								var sfzhm=data.idNo;
    							var mz=data.ethnicNmp;
    							var sex=data.genCd
    							var xm=data.idDocName;
    							var dz=data.idDocAddr;
    							var sgnisuBrnchNm=data.sgnisuBrnchNm;//签发机构
    							var csrq=data.birthDate;
    							var credEffDate=data.credEffDate;//证件生效日期
    							var credInvldDate=data.credInvldDate;//证件过期日期
    							lkForm.getForm().findField("xm").setValue(xm);
    							lkForm.getForm().findField("zjhm").setValue(sfzhm);
    							lkForm.getForm().findField("csrq").setValue(csrq);
    							lkForm.getForm().findField("bz").setValue("中移核验设备号为:"+sn);
    							
    							lkForm.getForm().findField("xz").setValue(dz);
    							if(sex=='男'){sex='1';}else if(sex=='女'){sex='2';}
    							lkForm.getForm().findField('xb').setValue(sex);
    							
    							var imgsrc = "data:image/jpeg;base64,"+data.base64zp+"" ;
    							var imgsrc2 = "data:image/jpeg;base64,"+data.base64zp2+"" ;
							    Ext.fly("zpImg").dom.src = imgsrc;
							    Ext.fly("hzImg").dom.src = imgsrc2;
    							lkForm.getForm().findField('zp').setValue(data.base64zp);
    							lkForm.getForm().findField('hz').setValue(data.base64zp2);
    							//Ext.fly("zpImg").dom.src = 'util/upfile?type=rzzp&sfzhm='+sfzhm+ '&_dc=' + (new Date()).getTime();
    							if(csrq==null){
    								
    								xb = getXb(sfzhm);
        			    			csrq = getCsrq(sfzhm).format('Ymd');
        			    			lkForm.getForm().findField("csrq").setValue(csrq);
        			    			lkForm.getForm().findField("xb").setValue(xb);
    							}
    							
    							
    							//设置住址
						    	  var t_hjd = sfzhm.substr(0,6);
	    						Ext.Ajax.request({
											url:'nwb.do?method=zhQhdz&_dc=' + (new Date()).getTime(),
											method:'POST', 
											params:{qhbm:t_hjd},
											success:function(result,request){ 
												mask.hide();
												var jsonData = Ext.util.JSON.decode(result.responseText);
												if(jsonData.success){
													if(jsonData.success && jsonData.other){
													   // Ext.getCmp("ssxqm").setValue(jsonData.other);
														lkForm.getForm().findField("ssxqm").setValue(jsonData.other);
														lkForm.getForm().findField("ssxq").setValue(t_hjd);
														lkForm.getForm().findField("hclm").setValue(jsonData.other);
														lkForm.getForm().findField("hcl").setValue(t_hjd);
													}
												}
											}, 
											failure: function ( result, request) {
												mask.hide();
												Ext.MessageBox.alert('查询地址出错',result.responseText); 
											} 
										}); 
							
							}else{
								alert("无最新数据!");
							}
							
						}
    					}
    					}else{
    						alert("无最新数据!");
    					}
    					}, 
    					failure: function ( result, request) {
    						
    						Ext.MessageBox.alert('查询人证比对数据出错',result.responseText); 
    					} 
    				});
    				}
    			
    		}
    	});
    	
    	/*lkForm.addButton({//宿州
    		id: 'btn-edz33',
    		name: 'btn-edz23', 
    		text: '带入数据',
    		listeners: {
    			'click': function() {

    		    	Ext.Ajax.request({
    					//url:'nwb.do?method=queryRzbd',
    		    		url:'drsj',
    					method:'POST', 
    					params:{lgsn:lgsn},
    					success:function(result,request){ 
    						var data = Ext.util.JSON.decode(result.responseText);
    						//var jsonData = Ext.util.JSON.decode(result.responseText);
    						//if(jsonData.success){
    							//var data=jsonData.list[0];
    							if(data!=null){
    								var sfzhm=data.idNo;
        							var mz=data.ethnicNmp;
        							var sex=data.genCd
        							var xm=data.idDocName;
        							var dz=data.idDocAddr;
        							var sgnisuBrnchNm=data.sgnisuBrnchNm;//签发机构
        							var csrq=data.birthDate;
        							var credEffDate=data.credEffDate;//证件生效日期
        							var credInvldDate=data.credInvldDate;//证件过期日期
        							lkForm.getForm().findField("xm").setValue(xm);
        							lkForm.getForm().findField("zjhm").setValue(sfzhm);
        							lkForm.getForm().findField("csrq").setValue(csrq);
        							lkForm.getForm().findField("xz").setValue(dz);
        							if(sex=='男'){sex='1';}else if(sex=='女'){sex='2';}
        							lkForm.getForm().findField('xb').setValue(sex);
        							
        							var imgsrc = "data:image/jpeg;base64,"+data.base64zp+"" ;
								    Ext.fly("zpImg").dom.src = imgsrc;
        							//Ext.fly("zpImg").dom.src = 'util/upfile?type=rzzp&sfzhm='+sfzhm+ '&_dc=' + (new Date()).getTime();
        							//if(csrq==null){
        								
        								xb = getXb(sfzhm);
            			    			csrq = getCsrq(sfzhm).format('Ymd');
            			    			lkForm.getForm().findField("csrq").setValue(csrq);
            			    			lkForm.getForm().findField("xb").setValue(xb);
        							//}
        							
        							
        							//设置住址
							    	  var t_hjd = sfzhm.substr(0,6);
		    						Ext.Ajax.request({
												url:'nwb.do?method=zhQhdz&_dc=' + (new Date()).getTime(),
												method:'POST', 
												params:{qhbm:t_hjd},
												success:function(result,request){ 
													mask.hide();
													var jsonData = Ext.util.JSON.decode(result.responseText);
													if(jsonData.success){
														if(jsonData.success && jsonData.other){
														   // Ext.getCmp("ssxqm").setValue(jsonData.other);
															lkForm.getForm().findField("ssxqm").setValue(jsonData.other);
															lkForm.getForm().findField("ssxq").setValue(t_hjd);
															lkForm.getForm().findField("hclm").setValue(jsonData.other);
															lkForm.getForm().findField("hcl").setValue(t_hjd);
														}
													}
												}, 
												failure: function ( result, request) {
													mask.hide();
													Ext.MessageBox.alert('查询地址出错',result.responseText); 
												} 
											}); 
    							
    							}else{
    								alert("无最新数据!");
    							}
    							
    						//}
    					}, 
    					failure: function ( result, request) {
    						
    						Ext.MessageBox.alert('查询人证比对数据出错',result.responseText); 
    					} 
    				});
    				}
    			
    		}
    	});*/
    	 
}
    	
    	
    	var ds=lgbm.substring(0,4);
		if(ds=='3417'||ds=='3402'){ 
			lkForm.addButton({
	    		id: 'btn-edz33smb',
	    		name: 'btn-edz33smb', 
	    		text: 'smb无证入住',
	    		listeners: {
	    			'click': function() {
	    				//先清空内容 
	    				Ext.getCmp('btn-new').fireEvent('click');
	    				//
	    				wzrz();
		    				
	    				}
	    			
	    		}
	    	});	
		}
	//结束
    var CardReadType = lgEdz;
		var rdcard;
		var CVR_IDCard;
		var JL_IDCard;
		var XZX_IDCard;
		var SocketToPcSerOcx;
		/**
			<OBJECT ID="imgReader" CLASSID="CLSID:25014AF5-A059-4484-96D1-FB9F607D1079" CODEBASE="JcImg.CAB#version=1,2,0,1" width="900" height="500">
		 */
    scanHtml = '<div id="zp-scan" align="center">'+
				'<OBJECT ID="imgReader" CLASSID="CLSID:25014AF5-A059-4484-96D1-FB9F607D1079" width="1000" height="500"><param name="Hz" value="0"></OBJECT>'+
			
				'</div>';
				var ds=lgbm.substring(0,4);
				if(ds=='3417'||ds=='3402'){//池州加载3417  
				scanHtml +='<object classid="clsid:FF9877D4-3461-48CC-B53A-F0BF4EBA0E44" id="SynCardOcx1" width="0" height="0" ></object>';//实名宝无证入住
   
				}

    if (lgEdz=='1') {
    	scanHtml += '<DIV style="display=\'none\'"><OBJECT id="RD_OBJ" name="RD_OBJ" classid="clsid:F1317711-6BDE-4658-ABAA-39E31D3704D3" width="100" height="100"></OBJECT></DIV>';
    } else if (lgEdz == '2') {
    	scanHtml += '<DIV style="display=\'none\'"><OBJECT id="CVR_OBJ" name="CVR_OBJ" classid="clsid:10946843-7507-44FE-ACE8-2B3483D179B7" width="100" height="100"></OBJECT></DIV>';
    } else if (lgEdz == '5') {
    	scanHtml += '<DIV style="display=\'none\'"><OBJECT id="JL_OBJ" name="JL_OBJ" classid="clsid:5EB842AE-5C49-4FD8-8CE9-77D4AF9FD4FF" width="100" height="100"></OBJECT></DIV>';
    } else if (lgEdz == '4') {
    	scanHtml += '<DIV style="display=\'true\'"><OBJECT id="XZX_OBJ" name="XZX_OBJ" classid="clsid:4B3CB088-9A00-4D24-87AA-F65C58531039" width="0" height="0" border="0"></OBJECT></DIV>';
    }
    if(lgRxbdflag2=='0'){
    	scanHtml += '<DIV style="display=\'true\'"><OBJECT id="SocketToPcSerOcx" name="SocketToPcSerOcx" classid="clsid:4B2B326D-152A-4946-9EE1-BB4A7AC41B01"  width="0"  height="0"  border="0"></OBJECT></DIV>';
    }
    

    var scanForm = new Ext.FormPanel({
      id: 'scanPanel',
			title: '照片扫描区',
	    frame: true,
	    labelAlign: 'right',			
	    columnWidth: 1,
	    //width:780,
	    //height:600,
	    autoWidth:true,
			hidden: false,
			items: [{
				html: scanHtml
			}]
		});
		
   
			
    var allForm = new Ext.Panel({
      id: 'id_allForm',
	    frame: true,
	    labelAlign: 'right',
	    //columnWidth: 1,
	    autoScroll:true,
	    //autoWidth:true,
	    defaults: {
		    collapsible: true
		  },
	    //height:600,
	    bodyStyle:'width:100%;overflow-x:hidden;overflow-y:auto',
	    items: [
				lkForm,
				scanForm
			]
		})
		
    vp = new Ext.Viewport({
    	id: 'lk-viewport',
			layout: 'fit',
			anchor:'90%',
			items: [
				allForm
			],
		  renderTo: 'gui'
    });    

	//字典初始化
	initDict();
		
    frm = lkForm.getForm();
    if((lgsn.indexOf(",")!=-1 ||lgsn.indexOf("，")!=-1)&&(lgRxbdflag=='6'||lgRxbdflag=='9')){
    	 Ext.getCmp("sbh").getEl().up('.x-form-item').setDisplayed(true); 
    }else{
    		sn=lgsn;
        	Ext.getCmp("sbh").getEl().up('.x-form-item').setDisplayed(false);
    	
    	
    }
    //登记和修改时，入住时间都不让修改
    frm.findField("rzsj").disable();
    //初始化入住时间
    frm.findField("rzsj").setValue(cur_datetime);
    //输入身份证后验证带入系统内旅客信息
    frm.findField("zjhm").addListener('blur',sfz_load, frm);
    frm.findField("zjlx").addListener('blur',sfz_load, frm);
    function sfz_load(){
    		zjlx = lkForm.getForm().findField("zjlx");
    		zjhm = lkForm.getForm().findField("zjhm");
    		
    		if(zjlx.getValue() == "")return;
    		if(zjhm.getValue() == "")return;
    		hm = zjhm.getValue();
    		zjlx_z = zjlx.getValue();
    		
    		/**
    			2018.04.17
    			新增	刁杰
    			身份证及无证人员填写了证件号码后自动获取相关信息
    			 || zjlx.getValue()=='98'
    		 */
    		if (zjlx.getValue()=='11' || zjlx.getValue()=='98') {
	    		tmp = isIDCard(zjhm.getValue());
	    		if (tmp!=true) {
	    			Gnt.MsgBox.showWarn(tmp);
	    			return;
	    		} else {
	    			zjhm.setValue(id15to18(zjhm.getValue()));
	    			xb = getXb(hm);
	    			csrq = getCsrq(hm).format('Ymd');
	    			lkForm.getForm().findField("csrq").setValue(csrq);
	    			lkForm.getForm().findField("xb").setValue(xb);
	    		}
    		}
    		
    		//根据证件类型和号码，到数据库查询数据	
	    			repStore.removeAll();
	    			repStore.load({
	    				params: {zjlx:zjlx_z,zjhm: hm,_dc2:(new Date()).getTime()},
				    	callback: function() {
				    		tmpLkbm =  lkForm.getForm().findField("lkbm").getValue();
				    		//alert(repStore.getCount());
				    		if (repStore.getCount()>0) {
				    			lkForm.getForm().loadRecord(repStore.getAt(0));
				    		  t_zpisnull = false;
					    		oldLkbm = lkForm.getForm().findField("lkbm").getValue();
					    		
					    		Ext.fly("zpImg").dom.src = 'util/upfile?type=lkzp&zjlx='+zjlx_z+'&zjhm=' + hm + '&_dc=' + (new Date()).getTime();
					    		lkForm.getForm().findField("lkbm").setValue(tmpLkbm);
					    		
					    		nbzzStore.removeAll();
				    			nbzzStore.load({
							    	params: {zjlx:zjlx_z,zjhm: hm,_dc2:(new Date()).getTime()},
							    	callback: function() {
							    		//alert(nbzzStore.getCount());
							    	  if (nbzzStore.getCount()>0 && v_lkbm =="") {
							    	  	Ext.Msg.alert("提示",""+zjlx.getRawValue()+":"+hm+" 还没有退房，不能重复登记，请检查！");	
							    	    Ext.getCmp("btn-new").fireEvent('click');
							    	  }
							    	  lkForm.enable();
							    	}
							    })
						   }else{
						   	 t_zpisnull = true;
						   	 Ext.fly("zpImg").dom.src =initLink+ '?_dc=' + (new Date()).getTime();
						   	 lkForm.enable();
						   }
						   //设置何处来、住址区划
						   if (zjlx.getValue()=='11') {
			                			if(hm!="" & ((lkForm.getForm().findField("ssxq").getValue()=="")||(lkForm.getForm().findField("hcl").getValue()==""))){
			                				qh=hm.substring(0,6);
			                				//查询拼音头
																Ext.Ajax.request({
																	url:'nwb.do?method=zhQhdz&_dc=' + (new Date()).getTime(),
																	method:'POST', 
																	params:{qhbm:qh},
																	success:function(result,request){ 
																		mask.hide();
																		var jsonData = Ext.util.JSON.decode(result.responseText);
																		if(jsonData.success){
																			if(jsonData.success && jsonData.other){
																				if(lkForm.getForm().findField("ssxq").getValue()==""){
																			  	lkForm.getForm().findField("ssxqm").setValue(jsonData.other);
																					lkForm.getForm().findField("ssxq").setValue(qh);
																			  }
																			  if(lkForm.getForm().findField("hcl").getValue()==""){
																					lkForm.getForm().findField("hclm").setValue(jsonData.other);
																					lkForm.getForm().findField("hcl").setValue(qh);
																				}
																			}
																		}
																	}, 
																	failure: function ( result, request) {
																		mask.hide();
																		Ext.MessageBox.alert('查询地址出错',result.responseText); 
																	} 
																}); 	
			                			}
			           }
			                		
				    	}
	    				
	    			});
	    			
	    			lkForm.disable();
	    			//lkForm.getForm().findField("ssxqm").setValue(zjhm.getValue().substring(0,6));
	    			
    }
    //字典初始化后回调
  	window.onReadyDict=function(){
		  //修改页面字段初始化
	    if(v_lkbm !=""&&v_lkbm !="rx" ){
	    	lkStore.load({
					    	params: {lkbm: v_lkbm},
					    	callback: function() {
					    		//tmpLkbm =  lkForm.getForm().findField("lkbm").getValue();
					    		if (lkStore.getCount()>0) {
					    			//lkForm.getForm().findField("zjhm").disable();
					    			//lkForm.getForm().findField("zjlx").disable();
									lkForm.getForm().findField("rzsj").disable();
								    
									if(Ext.getCmp('btn-edz')){
								    	 Ext.getCmp('btn-edz').disable();
								     }
									
					    		  lkForm.getForm().loadRecord(lkStore.getAt(0));
					    			lkForm.getForm().findField("lkbm2").setValue(v_lkbm);
					    			
					    			//已经退房的不让修改
					    			if (lkForm.getForm().findField("tfsj").getValue() != null && lkForm.getForm().findField("tfsj").getValue() != "") {
											Ext.getCmp('btn-save').disable();
										}
					    		}
					    		//读取照片
					    		var imgsrc = '';//'util/upfile?type=lkzp&lkbm=' + v_lkbm + '&_dc=' + (new Date()).getTime();
							    imgsrc = 'util/upfile?type=lkzp&lkbm=' + v_lkbm + '&zjlx='+lkForm.getForm().findField("zjlx").getValue()+'&zjhm=' + lkForm.getForm().findField("zjhm").getValue() + '&_dc=' + (new Date()).getTime()
								      
					    		Ext.fly("zpImg").dom.src = imgsrc;
					    			
					    		
					    		
					    		
					    	}
		    			})
	    }
    
  }
    
		//读卡器类型
    if (lgEdz=='1') {
    	rdcard = document.getElementById("RD_OBJ");
    } else if (lgEdz == '2') {
    	CVR_IDCard = document.getElementById("CVR_OBJ");
    }if (lgEdz == '5') {
    	JL_IDCard = document.getElementById("JL_OBJ");
    }if (lgEdz == '4') {
    	XZX_IDCard = document.getElementById("XZX_OBJ");
    }
    if(lgRxbdflag2=='0'){
    	SocketToPcSerOcx = document.getElementById("SocketToPcSerOcx");
    }
   
  //验证读卡器类型  
	function checkOcx(flag){
	  CardReadType=1;
	  try{
	  		CVR_IDCard.DoStopRead;
	  		ClearIDCard(flag);
	  		CardReadType=2;
	   }catch(e){
		   	try{
			   	rdcard.EndRead();
			   	rdcard.GetState(); 
				 	CardReadType=1;
			 }catch(a){
			 	try{
			 		XZX_IDCard.FindReader();
			 		CardReadType=4;
			 	}catch(a){}
			 }
	  }
	}
	//二代证对象
	function IdCard(){
		this.Name="";
		this.NameL="";
		this.Sex ="";
		this.SexL="";
		this.Nation="";
		this.NationL="";
		this.Born="";
		this.BornL="";
		this.CardNo ="";
		this.Address="";
		this.Police="";
		this.PhotoPath ="c:\\";     
		this.Ret=0;
		this.lastError="";
		this.picbase64="";
	}
	//二代证读取
	function readCard(flag){
		var rt=new IdCard();
		if(CardReadType==1){//神思
			rt.Ret=rdcard.ReadCard();
			if(rdcard.bHaveCard){
				rt.Name=trim(rdcard.NameS);
				rt.NameL=trim(rdcard.NameL);
				rt.Sex=trim(rdcard.Sex);
				rt.SexL=trim(rdcard.SexL);
				rt.Nation=trim(rdcard.Nation);
				rt.NationL=trim(rdcard.NationL);
				rt.Born=trim(rdcard.Born);
				rt.BornL=trim(rdcard.BornL);
				rt.CardNo=trim(rdcard.CardNo);
				rt.Address=trim(rdcard.Address);
				rt.Police=trim(rdcard.Police);
				rt.PhotoPath =rdcard.PhotoPath;
			}else{
				if(rt.Ret==-1)rt.lastError= '相片解码错误';
				else if(rt.Ret==-2)rt.lastError= 'wlt文件后缀错误';
				else if(rt.Ret==-3)rt.lastError= 'wlt文件打开错误';
				else if(rt.Ret==-4)rt.lastError= 'wlt文件格式错误';
				else if(rt.Ret==-5)rt.lastError= '软件授权文件错误或没有授权文件';
				else if(rt.Ret==-6)rt.lastError= '设备连接错误';
				else if(rt.Ret==-8)rt.lastError= '文件存储失败';
				else if(rt.Ret==-10)rt.lastError= '端口操作失败';
				else if(rt.Ret==-11)rt.lastError= '解码失败';
				else if(rt.Ret==2)rt.lastError= '接收数据超时';
			}
		}else if(CardReadType==2){//华视
			CVR_IDCard.PhotoPath=rt.PhotoPath;
			CVR_IDCard.TimeOut=3;
			rt.Ret= CVR_IDCard.ReadCard;
			
			if(rt.Ret==0){
			  rt.Name=trim(CVR_IDCard.Name);
				rt.NameL=trim(CVR_IDCard.NameL);
				rt.Sex=trim(CVR_IDCard.Sex);
				rt.SexL=trim(CVR_IDCard.SexL);
				rt.Nation=trim(CVR_IDCard.Nation);
				rt.NationL=trim(CVR_IDCard.NationL);
				rt.Born=trim(CVR_IDCard.Born);
				rt.BornL=trim(CVR_IDCard.BornL);
				rt.CardNo=trim(CVR_IDCard.CardNo);
				rt.Address=trim(CVR_IDCard.Address);
				rt.Police=trim(CVR_IDCard.Police);
				//alert(rt.Nation);//新版返回汉字，老板是代码
				if(flag==0){
				if(isHz(rt.Nation)){
				rt.PhotoPath =CVR_IDCard.PhotoPath;
				}else{
				rt.PhotoPath ="c:\\zp.bmp";
				}
				
				}
				if(flag==1){
				rt.PhotoPath =CVR_IDCard.PhotoPath;
				 }
				rt.picbase64=CVR_IDCard.Picture;
			}else{
				if(rt.Ret==-1)rt.lastError= '未连接机具';
				else if(rt.Ret==-2)rt.lastError= '放卡超时';
				else if(rt.Ret==-3)rt.lastError= '用户已取消读卡';
				else if(rt.Ret==-4)rt.lastError= '读基本信息出错';
				else if(rt.Ret==-5)rt.lastError= '照片创建失败';
			}
		}else if(CardReadType==5){//精伦
			rt.Ret= JL_IDCard.ReadCard("1001",rt.PhotoPath);
			if(rt.Ret==1){
			  rt.Name=trim(JL_IDCard.GetName());
				rt.Sex=trim(JL_IDCard.GetSex());
				rt.Nation=trim(JL_IDCard.GetFolk());
				rt.Born=trim(JL_IDCard.GetBirthYear() + "年" + JL_IDCard.GetBirthMonth() + "月" + JL_IDCard.GetBirthDay() +  "日");
				rt.CardNo=trim(JL_IDCard.GetCode());
				rt.Address=trim(JL_IDCard.GetAddress());
				rt.Police=trim(JL_IDCard.GetAgency());
				rt.PhotoPath ="c:\\zp.bmp";
			}else{
				if(rt.Ret==-1)rt.lastError= '端口初始化失败';
				else if(rt.Ret==-2)rt.lastError= '卡认证失败（请重新将卡放到读卡器）';
				else if(rt.Ret==-3)rt.lastError= '读取数据失败';
				else if(rt.Ret==-4)rt.lastError= '生成照片文件失败（请检查设定路径和磁盘空间）';
			}
			//关闭接口
			JL_IDCard.CloseComm();
		}else if(CardReadType==4){//新中新
			//alert("1111111111111");
			
			var t_dkh= XZX_IDCard.FindReader();//自动寻找读卡器
			if(t_dkh> 0){
				XZX_IDCard.SetPhotoPath(0,"");//设置照片保存目录为C盘根目录
				XZX_IDCard.SetReadType(0);//手动读卡
	  			rt.Ret = XZX_IDCard.ReadCardMsg();
	  			if(rt.Ret==0){
	  				rt.Name=trim(XZX_IDCard.NameA);
						rt.Sex=trim(XZX_IDCard.Sex);
						rt.Nation=trim(XZX_IDCard.Nation);
						rt.Born=trim(XZX_IDCard.Born);
						rt.CardNo=trim(XZX_IDCard.CardNo);
						rt.Address=trim(XZX_IDCard.Address);
						rt.Police=trim(XZX_IDCard.Police);
						rt.PhotoPath =XZX_IDCard.PhotoName;
	  			}
	  		}else{
				rt.lastError= '没有找到读卡器';
			}
			
		}
		else{
	 	  rt.Ret=-1;	
	 	  rt.lastError="请先正确安装读卡器!";
		}
		return rt;
	}    
	//
	function ClearIDCard(flag) {
	   CVR_IDCard.Name="";
	   CVR_IDCard.NameL="";
	   CVR_IDCard.Sex="";   
	   CVR_IDCard.Nation="";
	   CVR_IDCard.Born="";
	   CVR_IDCard.Address="";
	   CVR_IDCard.CardNo="";
	   CVR_IDCard.Police="";
	   CVR_IDCard.Activity="";
	   CVR_IDCard.NewAddr="";
	   CVR_IDCard.PhotoPath="";
	   CVR_IDCard.Picture="";
	  
	   return true;
	}
	
	//信息提示
	function show(title,msg,icon){
		Ext.Msg.show({
			title:title,
			msg:msg,
			buttons:Ext.Msg.OK,
			icon:icon?icon:Ext.MessageBox.INFO
		});
	}
	
	function trim(str){  //删除左右两端的空格   
		return str.replace(/(^\s*)|(　)|(\s*$)/g, "");   
	}
	
	//Ext.get("xm").dom.focus();
	
	/**
		2018.04.08
		新增	刁杰
		手工输入房号后，只需要回车一下，就可保存。
	 */

	var key4 = new Ext.KeyMap(document, {
	        key:Ext.EventObject.ENTER,
	        fn:function() {
	        	 sbtype=1;
	        	check();
	        },
	        scope: this  
	});
	//F8按钮读取二代证
	var key3 = new Ext.KeyMap(document, {
	        key:Ext.EventObject.F8,
	        fn:function() {
	        	if(Ext.getCmp('btn-edz') && !(Ext.getCmp('btn-edz').disabled)){
	        		Ext.getCmp('btn-edz').fireEvent('click');
				}
	        },
	        scope: this  
	});
	
	//F9按钮读取人证核验
	/*var key2 = new Ext.KeyMap(document, {
		key:Ext.EventObject.F7,
		fn:function() {
			if(Ext.getCmp('btn-new2') && !(Ext.getCmp('btn-new2').disabled)){
				Ext.getCmp('btn-new2').fireEvent('click');
			}
		},
		scope: this  
	});
	*/
	/**
		2018.04.08
		新增	刁杰
		F9按钮提交保存
	 */
	var key1 = new Ext.KeyMap(document, {
	        key:Ext.EventObject.F9,
	        fn:function() {
	        	sbtype=0;
	        	if(Ext.getCmp('btn-save') && !(Ext.getCmp('btn-save').disabled)){
	        		Ext.getCmp('btn-save').fireEvent('click');
				}
	        },
	        scope: this  
	});
	//smb
	function smb(szName, szCertifiType, szCertifiCode, szNation, szExpiredDate, szkTime, szPhone, szMac, szImei, szImsi, szSex, szBirthday, szAddress, szOrgan, szBackup, szCertifiSource, szImageSource, szImageVerify){
	
		if(szName!=null&&szName.length > 0)
   {
   
    	 //赋值
						 var name = szName;
						 var sex = szSex;
						 var nation = szNation;
						 var csrq = szBirthday;
						 var dz = szAddress;
						  var sfzh = szCertifiCode;
						  var zp1 = szImageSource;//base64
						
						    //var bm = rxbdStore.getAt(0).data.lgbm;
						// if(sex=="男"){sex="1";}else{sex="2"}
						 //if(nation=="汉"){nation="01";}else{}
						// nation=getMzcode(nation);
						//alert(nation);
						 
						var frm = lkForm.getForm();
						       // frm.reset();
								
		    					frm.findField('xm').setValue(name);
		    					frm.findField('xb').setValue(sex);
		    					frm.findField('mz').setValue(nation);
		    					frm.findField('csrq').setValue(csrq);
		    					frm.findField('zjhm').setValue(sfzh);
		    					frm.findField('xz').setValue(dz);
		    					frm.findField("zjlx").setValue('11');
            	                frm.findField("zp").setValue(zp1);
		    					
		                        frm.findField("hcqm").setValue(inithcqm);
		                        frm.findField("hcq").setValue(inithcq);
		                        		//同证件未退房不能登记
			    	          
		                        //读取照片
					    		var imgsrc = '';
								    imgsrc = "data:image/jpeg;base64,"+szImageSource+"" ;
								    Ext.fly("zpImg").dom.src = imgsrc;
								    operElement("imgReader","clear","","f");
									lkForm.getForm().findField("rzsj").setValue(cur_datetime);
									document.getElementById("imgReader").loadFile(zp1);
		    					//operElement("imgReader","loadFile","","f",ci.PhotoPath);
		    				    t_zpisnull=false;
		    				    v_lkbm="rx";
		                          //设置住址1
							    	  var t_hjd = sfzh.substr(0,6);
		    						Ext.Ajax.request({
												url:'nwb.do?method=zhQhdz&_dc=' + (new Date()).getTime(),
												method:'POST', 
												params:{qhbm:t_hjd},
												success:function(result,request){ 
													mask.hide();
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
													mask.hide();
													Ext.MessageBox.alert('查询地址出错',result.responseText); 
												} 
											});
		    				
			}else
          {
            ret='没有获取到核验数据！';
            alert(ret);
            //document.getElementById("result").innerHTML=ret;
          }
	}
	//smb
	function check(){
		
		if(v_lkbm==""){
		//同证件未退房不能登记
		var sfzhm = Ext.getCmp("zjhm").getValue();
		zjlx = frm.findField("zjlx").getValue();
		zjlxm = frm.findField("zjlxm").getValue();
		nbzzStore.removeAll();
		nbzzStore.load({
	    	params: {zjlx:'11',zjhm:sfzhm,_dc2:(new Date()).getTime()},
	    	callback: function() {
	    		
	    	  if (nbzzStore.getCount()>0) {
	    		 // Ext.Msg.alert("提示","身份证:"+sfzhm+"还没有退房，不能重复登记，请检查！");
	    		  alert("身份证:"+sfzhm+"还没有退房，不能重复登记，请检查！");
	    		  if(sbtype==1){	  
	    		  }else{	
	  	    	    Ext.getCmp("btn-new").fireEvent('click');
	    		  }
	    		  return;
	    	  }else{
	    			if(sbtype==1){
	    				var ssfh=Ext.get("rzfh").dom.value;
	    				Ext.Ajax.request({
	    					url:'nwb.do?method=queryfh',
	    					method:'POST',
	    					params:{ssfh:ssfh,lgbm:lgbm},
	    					success:function(result,request){ 
	    						var jsonData = Ext.util.JSON.decode(result.responseText);
	    						if(jsonData.success){
	    							if(jsonData.messages=="false"){			
	    							    alert("房号不存在!");
	    							    lkForm.getForm().findField("rzfh").setValue('');
	    							   Ext.get("rzfh").dom.focus();
	    							   return false;
	    							}else{
	    								//车牌验证
	    								var t_cphm = frm.findField("cphm").getValue();
	    								var v_cphm = t_cphm;
	    								if(t_cphm != ''){
	    									if(checkIsChinese(t_cphm)){
	    									  var len = t_cphm.length;
	    										for(var i=0;i<len;i++){
	    											var t_seek = t_cphs.indexOf(t_cphm.charAt(i));
	    											  if(t_seek>=0){
	    											  	v_cphm = t_cphm.substring(0,i)+t_cphm.substring(i+1,len);
	    											  }
	    										}
	    									}
	    									if(checkIsChinese(v_cphm)){
	    										alert('请规范输入车牌号码。先填写"车牌首字"项目，"车牌号码"项目不应该包含汉字');
	    										return;
	    									}
	    								}
	    								
	    								if(!lkForm.getForm().isValid()){
	    									Ext.Msg.alert("提示","数据校验没有通过，请检查！");
	    									return;
	    								}
	    								var t_rzsj = lkForm.getForm().findField('rzsj').getValue();
	    								var t_tfsj = lkForm.getForm().findField('tfsj').getValue();
	    								if(t_rzsj !="" && t_rzsj.format("YmdHi") > cur_datetime){
	    									Ext.Msg.alert("提示","入住时间不能大于当前时间！");
	    									return;
	    								}
	    								/**
	    									2018.04.17
	    									删除	刁杰
	    									退房时间不得大于当前时间的逻辑删除
	    								if(t_tfsj !="" && t_tfsj.format("YmdHi") > cur_datetime){
	    									Ext.Msg.alert("提示","退房时间不能大于当前时间！");
	    									return;
	    								}
	    								*/
	    								if(t_tfsj !="" && t_tfsj.format("YmdHi") < t_rzsj.format("YmdHi")){
	    									Ext.Msg.alert("提示","退房时间不能小于入住时间！");
	    									return;
	    								}

	    								try{
	    									//设置下拉字典name值
	    									var v_dict = lkForm.getForm().findField('xb').getValue();
	    									if(v_dict != ''){
	    										lkForm.getForm().findField('xbm').setValue(lkForm.getForm().findField('xb').store.getById(v_dict).data.name);
	    									}
	    									v_dict = lkForm.getForm().findField('zjlx').getValue();
	    									if(v_dict != ''){
	    										lkForm.getForm().findField('zjlxm').setValue(lkForm.getForm().findField('zjlx').store.getById(v_dict).data.name);
	    									}
	    									v_dict = lkForm.getForm().findField('mz').getValue();
	    									if(v_dict != ''){
	    										lkForm.getForm().findField('mzm').setValue(lkForm.getForm().findField('mz').store.getById(v_dict).data.name);
	    									}
	    									v_dict = lkForm.getForm().findField('lcsy').getValue();
	    									if(v_dict != ''){
	    										lkForm.getForm().findField('lcsym').setValue(lkForm.getForm().findField('lcsy').store.getById(v_dict).data.name);
	    									}
	    									v_dict = lkForm.getForm().findField('zy').getValue();
	    									if(v_dict != ''){
	    										lkForm.getForm().findField('zym').setValue(lkForm.getForm().findField('zy').store.getById(v_dict).data.name);
	    									}
	    								}catch(e){}
	    									//身份证验证
	    									zjlx = frm.findField("zjlx");
	    								if (zjlx.getValue()=='11') {
	    									xb = frm.findField("xb").getValue();
	    										csrq = frm.findField("csrq").getValue();
	    									
	    						    		zjhm = frm.findField("zjhm");
	    						    		tmp = isIDCard(zjhm.getValue());
	    						    		if (tmp!=true) {
	    						    			Gnt.MsgBox.showError(tmp);
	    						    			return false;
	    						    		} else {
	    						    			zjhm.setValue(id15to18(zjhm.getValue()));
	    						    		}
	    						    		/*tmp = validateCard(zjhm.getValue(), xb, csrq);
	    						    		if (tmp != true) {
	    											Gnt.MsgBox.showError(tmp);
	    											return false;
	    										}
	    										*/
	    								} 
	    								//设置照片值
	    						    	if (operElement("imgReader","zpChanged") != "0" && operElement("imgReader","zpChanged") != null) {
	    						    			//alert(document.getElementById("imgReader").acqIDBase64().length);
	    						    			//alert(operElement("imgReader","acqIDBase64","","f"));
	    						    		frm.findField("zp").setValue(operElement("imgReader","getAotoPicture","","f"));
	    						    	}
	    						       var xm=frm.findField("xm").getValue();
	    						       //alert(xm.length);
	    									if(xm.length<2){
	    						    		
	    						    			Gnt.MsgBox.showError("姓名不能为空，并且不能少于2个字。");
	    											return false;	
	    										}
	    						    		//新增时照片必填
	    						    		var cjzp=frm.findField("zp").getValue();
	    						    	//	alert(cjzp);
	    						    		if(v_lkbm =="" && "0"==v_ZPNULL&&cjzp==""){
	    						    		
	    						    			Gnt.MsgBox.showError("照片不能为空，请上传照片。");
	    											return false;	
	    										}
	    						    	
	    									
	    									//提交
	    									//var p = lkForm.getForm().getValues();
	    									//var data = {data:Ext.encode(p)};
	    								saveNb(null);
	    								//operElement("imgReader","clear","","f");
	    							    Ext.fly("zpImg").dom.src=initLink+ '?_dc=' + (new Date()).getTime();
	    							    // Ext.fly("hzImg").dom.src=initLink+ '?_dc=' + (new Date()).getTime();
	    							    v_lkbm ="";
	    							    t_zpisnull=true;
	    							}
	    						}
	    					}, 
	    					failure: function ( result, request) {
	    						
	    						Ext.MessageBox.alert('查询地址出错',result.responseText); 
	    					} 
	    				});
	    			}else{
	    				//车牌验证
	    				var t_cphm = frm.findField("cphm").getValue();
	    				var v_cphm = t_cphm;
	    				if(t_cphm != ''){
	    					if(checkIsChinese(t_cphm)){
	    					  var len = t_cphm.length;
	    						for(var i=0;i<len;i++){
	    							var t_seek = t_cphs.indexOf(t_cphm.charAt(i));
	    							  if(t_seek>=0){
	    							  	v_cphm = t_cphm.substring(0,i)+t_cphm.substring(i+1,len);
	    							  }
	    						}
	    					}
	    					if(checkIsChinese(v_cphm)){
	    						alert('请规范输入车牌号码。先填写"车牌首字"项目，"车牌号码"项目不应该包含汉字');
	    						return;
	    					}
	    				}
	    				
	    				if(!lkForm.getForm().isValid()){
	    					Ext.Msg.alert("提示","数据校验没有通过，请检查！");
	    					return;
	    				}
	    				var t_rzsj = lkForm.getForm().findField('rzsj').getValue();
	    				var t_tfsj = lkForm.getForm().findField('tfsj').getValue();
	    				if(t_rzsj !="" && t_rzsj.format("YmdHi") > cur_datetime){
	    					Ext.Msg.alert("提示","入住时间不能大于当前时间！");
	    					return;
	    				}
	    				/**
	    					2018.04.17
	    					删除	刁杰
	    					退房时间不得大于当前时间的逻辑删除
	    				if(t_tfsj !="" && t_tfsj.format("YmdHi") > cur_datetime){
	    					Ext.Msg.alert("提示","退房时间不能大于当前时间！");
	    					return;
	    				}
	    				*/
	    				if(t_tfsj !="" && t_tfsj.format("YmdHi") < t_rzsj.format("YmdHi")){
	    					Ext.Msg.alert("提示","退房时间不能小于入住时间！");
	    					return;
	    				}

	    				try{
	    					//设置下拉字典name值
	    					var v_dict = lkForm.getForm().findField('xb').getValue();
	    					if(v_dict != ''){
	    						lkForm.getForm().findField('xbm').setValue(lkForm.getForm().findField('xb').store.getById(v_dict).data.name);
	    					}
	    					v_dict = lkForm.getForm().findField('zjlx').getValue();
	    					if(v_dict != ''){
	    						lkForm.getForm().findField('zjlxm').setValue(lkForm.getForm().findField('zjlx').store.getById(v_dict).data.name);
	    					}
	    					v_dict = lkForm.getForm().findField('mz').getValue();
	    					if(v_dict != ''){
	    						lkForm.getForm().findField('mzm').setValue(lkForm.getForm().findField('mz').store.getById(v_dict).data.name);
	    					}
	    					v_dict = lkForm.getForm().findField('lcsy').getValue();
	    					if(v_dict != ''){
	    						lkForm.getForm().findField('lcsym').setValue(lkForm.getForm().findField('lcsy').store.getById(v_dict).data.name);
	    					}
	    					v_dict = lkForm.getForm().findField('zy').getValue();
	    					if(v_dict != ''){
	    						lkForm.getForm().findField('zym').setValue(lkForm.getForm().findField('zy').store.getById(v_dict).data.name);
	    					}
	    				}catch(e){}
	    					//身份证验证
	    					zjlx = frm.findField("zjlx");
	    				if (zjlx.getValue()=='11') {
	    					xb = frm.findField("xb").getValue();
	    						csrq = frm.findField("csrq").getValue();
	    					
	    		    		zjhm = frm.findField("zjhm");
	    		    		tmp = isIDCard(zjhm.getValue());
	    		    		if (tmp!=true) {
	    		    			Gnt.MsgBox.showError(tmp);
	    		    			return false;
	    		    		} else {
	    		    			zjhm.setValue(id15to18(zjhm.getValue()));
	    		    		}
	    		    		/*tmp = validateCard(zjhm.getValue(), xb, csrq);
	    		    		if (tmp != true) {
	    							Gnt.MsgBox.showError(tmp);
	    							return false;
	    						}
	    						*/
	    				} 
	    				//设置照片值
	    		    	if (operElement("imgReader","zpChanged") != "0" && operElement("imgReader","zpChanged") != null) {
	    		    			//alert(document.getElementById("imgReader").acqIDBase64().length);
	    		    			//alert(operElement("imgReader","acqIDBase64","","f"));
	    		    		frm.findField("zp").setValue(operElement("imgReader","getAotoPicture","","f"));
	    		    	}
	    		       var xm=frm.findField("xm").getValue();
	    		       //alert(xm.length);
	    					if(xm.length<2){
	    		    		
	    		    			Gnt.MsgBox.showError("姓名不能为空，并且不能少于2个字。");
	    							return false;	
	    						}
	    		    		//新增时照片必填
	    		    		var cjzp=frm.findField("zp").getValue();
	    		    	//	alert(cjzp);
	    		    		if(v_lkbm =="" && "0"==v_ZPNULL&&cjzp==""){
	    		    		
	    		    			Gnt.MsgBox.showError("照片不能为空，请上传照片。");
	    							return false;	
	    						}
	    		    	
	    					
	    					//提交
	    					//var p = lkForm.getForm().getValues();
	    					//var data = {data:Ext.encode(p)};
	    				saveNb(null);
	    				//operElement("imgReader","clear","","f");
	    			    Ext.fly("zpImg").dom.src=initLink+ '?_dc=' + (new Date()).getTime();
	    			    // Ext.fly("hzImg").dom.src=initLink+ '?_dc=' + (new Date()).getTime();
	    			    v_lkbm ="";
	    			    t_zpisnull=true;
	    			}
	    	  }
	    	   
					
	    	}
	    })
	
	}else{

		if(sbtype==1){
			var ssfh=Ext.get("rzfh").dom.value;
			Ext.Ajax.request({
				url:'nwb.do?method=queryfh',
				method:'POST',
				params:{ssfh:ssfh,lgbm:lgbm},
				success:function(result,request){ 
					var jsonData = Ext.util.JSON.decode(result.responseText);
					if(jsonData.success){
						if(jsonData.messages=="false"){			
						    alert("房号不存在!");
						    lkForm.getForm().findField("rzfh").setValue('');
						   Ext.get("rzfh").dom.focus();
						   return false;
						}else{
							//车牌验证
							var t_cphm = frm.findField("cphm").getValue();
							var v_cphm = t_cphm;
							if(t_cphm != ''){
								if(checkIsChinese(t_cphm)){
								  var len = t_cphm.length;
									for(var i=0;i<len;i++){
										var t_seek = t_cphs.indexOf(t_cphm.charAt(i));
										  if(t_seek>=0){
										  	v_cphm = t_cphm.substring(0,i)+t_cphm.substring(i+1,len);
										  }
									}
								}
								if(checkIsChinese(v_cphm)){
									alert('请规范输入车牌号码。先填写"车牌首字"项目，"车牌号码"项目不应该包含汉字');
									return;
								}
							}
							
							if(!lkForm.getForm().isValid()){
								Ext.Msg.alert("提示","数据校验没有通过，请检查！");
								return;
							}
							var t_rzsj = lkForm.getForm().findField('rzsj').getValue();
							var t_tfsj = lkForm.getForm().findField('tfsj').getValue();
							if(t_rzsj !="" && t_rzsj.format("YmdHi") > cur_datetime){
								Ext.Msg.alert("提示","入住时间不能大于当前时间！");
								return;
							}
							/**
								2018.04.17
								删除	刁杰
								退房时间不得大于当前时间的逻辑删除
							if(t_tfsj !="" && t_tfsj.format("YmdHi") > cur_datetime){
								Ext.Msg.alert("提示","退房时间不能大于当前时间！");
								return;
							}
							*/
							if(t_tfsj !="" && t_tfsj.format("YmdHi") < t_rzsj.format("YmdHi")){
								Ext.Msg.alert("提示","退房时间不能小于入住时间！");
								return;
							}

							try{
								//设置下拉字典name值
								var v_dict = lkForm.getForm().findField('xb').getValue();
								if(v_dict != ''){
									lkForm.getForm().findField('xbm').setValue(lkForm.getForm().findField('xb').store.getById(v_dict).data.name);
								}
								v_dict = lkForm.getForm().findField('zjlx').getValue();
								if(v_dict != ''){
									lkForm.getForm().findField('zjlxm').setValue(lkForm.getForm().findField('zjlx').store.getById(v_dict).data.name);
								}
								v_dict = lkForm.getForm().findField('mz').getValue();
								if(v_dict != ''){
									lkForm.getForm().findField('mzm').setValue(lkForm.getForm().findField('mz').store.getById(v_dict).data.name);
								}
								v_dict = lkForm.getForm().findField('lcsy').getValue();
								if(v_dict != ''){
									lkForm.getForm().findField('lcsym').setValue(lkForm.getForm().findField('lcsy').store.getById(v_dict).data.name);
								}
								v_dict = lkForm.getForm().findField('zy').getValue();
								if(v_dict != ''){
									lkForm.getForm().findField('zym').setValue(lkForm.getForm().findField('zy').store.getById(v_dict).data.name);
								}
							}catch(e){}
								//身份证验证
								zjlx = frm.findField("zjlx");
							if (zjlx.getValue()=='11') {
								xb = frm.findField("xb").getValue();
									csrq = frm.findField("csrq").getValue();
								
					    		zjhm = frm.findField("zjhm");
					    		tmp = isIDCard(zjhm.getValue());
					    		if (tmp!=true) {
					    			Gnt.MsgBox.showError(tmp);
					    			return false;
					    		} else {
					    			zjhm.setValue(id15to18(zjhm.getValue()));
					    		}
					    		/*tmp = validateCard(zjhm.getValue(), xb, csrq);
					    		if (tmp != true) {
										Gnt.MsgBox.showError(tmp);
										return false;
									}
									*/
							} 
							//设置照片值
					    	if (operElement("imgReader","zpChanged") != "0" && operElement("imgReader","zpChanged") != null) {
					    			//alert(document.getElementById("imgReader").acqIDBase64().length);
					    			//alert(operElement("imgReader","acqIDBase64","","f"));
					    		frm.findField("zp").setValue(operElement("imgReader","getAotoPicture","","f"));
					    	}
					       var xm=frm.findField("xm").getValue();
					       //alert(xm.length);
								if(xm.length<2){
					    		
					    			Gnt.MsgBox.showError("姓名不能为空，并且不能少于2个字。");
										return false;	
									}
					    		//新增时照片必填
					    		var cjzp=frm.findField("zp").getValue();
					    	//	alert(cjzp);
					    		if(v_lkbm =="" && "0"==v_ZPNULL&&cjzp==""){
					    		
					    			Gnt.MsgBox.showError("照片不能为空，请上传照片。");
										return false;	
									}
					    	
								
								//提交
								//var p = lkForm.getForm().getValues();
								//var data = {data:Ext.encode(p)};
							saveNb(null);
							//operElement("imgReader","clear","","f");
						    Ext.fly("zpImg").dom.src=initLink+ '?_dc=' + (new Date()).getTime();
						    // Ext.fly("hzImg").dom.src=initLink+ '?_dc=' + (new Date()).getTime();
						    v_lkbm ="";
						    t_zpisnull=true;
						}
					}
				}, 
				failure: function ( result, request) {
					
					Ext.MessageBox.alert('查询地址出错',result.responseText); 
				} 
			});
		}else{
			//车牌验证
			var t_cphm = frm.findField("cphm").getValue();
			var v_cphm = t_cphm;
			if(t_cphm != ''){
				if(checkIsChinese(t_cphm)){
				  var len = t_cphm.length;
					for(var i=0;i<len;i++){
						var t_seek = t_cphs.indexOf(t_cphm.charAt(i));
						  if(t_seek>=0){
						  	v_cphm = t_cphm.substring(0,i)+t_cphm.substring(i+1,len);
						  }
					}
				}
				if(checkIsChinese(v_cphm)){
					alert('请规范输入车牌号码。先填写"车牌首字"项目，"车牌号码"项目不应该包含汉字');
					return;
				}
			}
			
			if(!lkForm.getForm().isValid()){
				Ext.Msg.alert("提示","数据校验没有通过，请检查！");
				return;
			}
			var t_rzsj = lkForm.getForm().findField('rzsj').getValue();
			var t_tfsj = lkForm.getForm().findField('tfsj').getValue();
			if(t_rzsj !="" && t_rzsj.format("YmdHi") > cur_datetime){
				Ext.Msg.alert("提示","入住时间不能大于当前时间！");
				return;
			}
			/**
				2018.04.17
				删除	刁杰
				退房时间不得大于当前时间的逻辑删除
			if(t_tfsj !="" && t_tfsj.format("YmdHi") > cur_datetime){
				Ext.Msg.alert("提示","退房时间不能大于当前时间！");
				return;
			}
			*/
			if(t_tfsj !="" && t_tfsj.format("YmdHi") < t_rzsj.format("YmdHi")){
				Ext.Msg.alert("提示","退房时间不能小于入住时间！");
				return;
			}

			try{
				//设置下拉字典name值
				var v_dict = lkForm.getForm().findField('xb').getValue();
				if(v_dict != ''){
					lkForm.getForm().findField('xbm').setValue(lkForm.getForm().findField('xb').store.getById(v_dict).data.name);
				}
				v_dict = lkForm.getForm().findField('zjlx').getValue();
				if(v_dict != ''){
					lkForm.getForm().findField('zjlxm').setValue(lkForm.getForm().findField('zjlx').store.getById(v_dict).data.name);
				}
				v_dict = lkForm.getForm().findField('mz').getValue();
				if(v_dict != ''){
					lkForm.getForm().findField('mzm').setValue(lkForm.getForm().findField('mz').store.getById(v_dict).data.name);
				}
				v_dict = lkForm.getForm().findField('lcsy').getValue();
				if(v_dict != ''){
					lkForm.getForm().findField('lcsym').setValue(lkForm.getForm().findField('lcsy').store.getById(v_dict).data.name);
				}
				v_dict = lkForm.getForm().findField('zy').getValue();
				if(v_dict != ''){
					lkForm.getForm().findField('zym').setValue(lkForm.getForm().findField('zy').store.getById(v_dict).data.name);
				}
			}catch(e){}
				//身份证验证
				zjlx = frm.findField("zjlx");
			if (zjlx.getValue()=='11') {
				xb = frm.findField("xb").getValue();
					csrq = frm.findField("csrq").getValue();
				
	    		zjhm = frm.findField("zjhm");
	    		tmp = isIDCard(zjhm.getValue());
	    		if (tmp!=true) {
	    			Gnt.MsgBox.showError(tmp);
	    			return false;
	    		} else {
	    			zjhm.setValue(id15to18(zjhm.getValue()));
	    		}
	    		/*tmp = validateCard(zjhm.getValue(), xb, csrq);
	    		if (tmp != true) {
						Gnt.MsgBox.showError(tmp);
						return false;
					}
					*/
			} 
			//设置照片值
	    	if (operElement("imgReader","zpChanged") != "0" && operElement("imgReader","zpChanged") != null) {
	    			//alert(document.getElementById("imgReader").acqIDBase64().length);
	    			//alert(operElement("imgReader","acqIDBase64","","f"));
	    		frm.findField("zp").setValue(operElement("imgReader","getAotoPicture","","f"));
	    	}
	       var xm=frm.findField("xm").getValue();
	       //alert(xm.length);
				if(xm.length<2){
	    		
	    			Gnt.MsgBox.showError("姓名不能为空，并且不能少于2个字。");
						return false;	
					}
	    		//新增时照片必填
	    		var cjzp=frm.findField("zp").getValue();
	    	//	alert(cjzp);
	    		if(v_lkbm =="" && "0"==v_ZPNULL&&cjzp==""){
	    		
	    			Gnt.MsgBox.showError("照片不能为空，请上传照片。");
						return false;	
					}
	    	
				
				//提交
				//var p = lkForm.getForm().getValues();
				//var data = {data:Ext.encode(p)};
			saveNb(null);
			//operElement("imgReader","clear","","f");
		    Ext.fly("zpImg").dom.src=initLink+ '?_dc=' + (new Date()).getTime();
		    // Ext.fly("hzImg").dom.src=initLink+ '?_dc=' + (new Date()).getTime();
		    v_lkbm ="";
		    t_zpisnull=true;
		}
  
	}
		
	
	}
//人证一致比对
function rzhy(){
var zp1=null;
var zp2=null;
zp1=lkForm.getForm().findField("zp").getValue();
zp2=lkForm.getForm().findField("hz").getValue();

//alert(zp1);
//alert(zp2);
if(zp1 =="" || zp2==""){
    			Gnt.MsgBox.showError("证件照片和现场照片不能为空，请上传照片。");
					return false;	
				}
	Ext.Ajax.request({
						url:rzyzurl,
						method:'POST',
						params:{zp1:zp1,zp2:zp2}, 
	                   // data: {"lisenceId":"5b160de325d32d4d80e1b12a","serialId": "9e0ae64c-d2a4-4a33-b91e-a331a0f42d40","accessToken": "ba3519b99f2f304607292f813945978b","idcard": "{"address":"上海市青浦区界泾港新村43号104室","birth":"1997.01.30","bitmap64":"+zp1+","sex":"女","sign_office":"上海市公安局青浦分局","useful_e":"2027.07.21","useful_s":"2017.07.21"}","picture": "+zp2+"},
				      	success:function(result,request){ 
																		mask.hide();
																		var jsonData = Ext.util.JSON.decode(result.responseText);
																		//alert(jsonData);
																		if(jsonData.code=='0'){
																		alert("人证一致，请办理入住手续");	
																		}
																		else if(jsonData.code=='1'){
																		alert("系统无法判定为同人");	
																		}
																		else if(jsonData.code=='2'){
																		alert("人证不一致");	
																		}else{
																		
																		alert("比对不通过，可能原因："+jsonData.info);
																		}
																	}, 
																	failure: function ( result, request) {
																		mask.hide();
																		Ext.MessageBox.alert('比对失败出错',result.responseText); 
																	}
												
				}); 
			
		}
//结束

//20180816kqt 增加网证信息查询和带入
function querywz(){
    
     var frm = lkForm.getForm();
     var sfzhsw=frm.findField('sfzhsw').getValue();		
     var wzyzm=frm.findField('wzyzm').getValue();
     if(sfzhsw==""){
        alert("请输入身份证后四位！");
        Ext.get("sfzhsw").dom.focus();
        return ;
     }	
     if(wzyzm==""){
     alert("请输入网证验证码！");
     Ext.get("wzyzm").dom.focus();
     return;
     }				      
	//查询
		mask.show();
	Ext.Ajax.request({
												url:'nwb.do?method=querywz&_dc=' + (new Date()).getTime(),
												method:'POST', 
												params:{sfzhsw:sfzhsw,wzyzm:wzyzm},
												success:function(result,request){ 
												
												
													var jsonData = Ext.util.JSON.decode(result.responseText);
												
	                                             if(jsonData.success){
	                                                   if(jsonData.other1!='1'){
												
														 var userName=jsonData.other1;
														 //alert(userName);
														 var identityId=jsonData.other2;
														 //alert(identityId);
														 var photo=jsonData.other3;
														// alert(photo);
														  var t_hjd = identityId.substr(0,6);
														  var csrq=identityId.substr(6,8);
														  var xb=setXb(identityId);
														 
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
		    					
		    					                           frm.findField("zjlx").setValue('94');
            	                                           frm.findField("zp").setValue(wzzp);
		    					
		                                                    frm.findField("hcqm").setValue(inithcqm);
		                                                    frm.findField("hcq").setValue(inithcq);
		                                                   frm.findField("bz").setValue('通过可信网证办理无证入住');		
		                                                  //读取照片
					    		                        var imgsrc = '';
								                            imgsrc = initLink;
								                            imgsrc="data:image/jpeg;base64,"+wzzp;
								                            Ext.fly("zpImg").dom.src = imgsrc;
								    
								 
									                        lkForm.getForm().findField("rzsj").setValue(cur_datetime);
								
		    				                               t_zpisnull=false;
		    				                                 v_lkbm="rx";
		                                                     //设置住址1
		                                                       var t_hjd = identityId.substr(0,6);
		    						            Ext.Ajax.request({
												url:'nwb.do?method=zhQhdz&_dc=' + (new Date()).getTime(),
												method:'POST', 
												params:{qhbm:t_hjd},
												success:function(result,request){ 
													mask.hide();
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
													mask.hide();
													Ext.MessageBox.alert('查询地址出错',result.responseText); 
												} 
											});
		                                       //住址结束
														
													}else{
														mask.hide();
	                                                   	          // result.responseText                                       
													Ext.MessageBox.alert('没有查询到网证信息！','没有查询到网证信息,请确认输入是否正确或验证码过期！'); 
	                                                   }
	                                                }
												}, 
												failure: function ( result, request) {
													mask.hide();
													Ext.MessageBox.alert('查询网证出错',result.responseText); 
												} 
											});

							    	
		    						
}


//网证查询结束
});
