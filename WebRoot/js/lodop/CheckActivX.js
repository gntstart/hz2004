var CreatedOKLodop7766=null;
var 	LODOP;
function myAddHtml() {       
	LODOP=getLodop(); 
	return true;
	//	LODOP.PRINT_INIT("");		            
	//LODOP.ADD_PRINT_HTM(10,55,"100%","100%",document.getElementById("textarea01").value);	       
};
//====判断是否需要安装CLodop云打印服务器:====
function needCLodop(){
    try{
	var ua=navigator.userAgent;
	if (ua.match(/Windows\sPhone/i) !=null) return true;
	if (ua.match(/iPhone|iPod/i) != null) return true;
	if (ua.match(/Android/i) != null) return true;
	if (ua.match(/Edge\D?\d+/i) != null) return true;
	
	var verTrident=ua.match(/Trident\D?\d+/i);
	var verIE=ua.match(/MSIE\D?\d+/i);
	var verOPR=ua.match(/OPR\D?\d+/i);
	var verFF=ua.match(/Firefox\D?\d+/i);
	var x64=ua.match(/x64/i);
	if ((verTrident==null)&&(verIE==null)&&(x64!==null)) 
		return true; else
	if ( verFF !== null) {
		verFF = verFF[0].match(/\d+/);
		if ((verFF[0]>= 41)||(x64!==null)) return true;
	} else 
	if ( verOPR !== null) {
		verOPR = verOPR[0].match(/\d+/);
		if ( verOPR[0] >= 32 ) return true;
	} else 
	if ((verTrident==null)&&(verIE==null)) {
		var verChrome=ua.match(/Chrome\D?\d+/i);		
		if ( verChrome !== null ) {
			verChrome = verChrome[0].match(/\d+/);
			if (verChrome[0]>=41) return true;
		};
	};
        return false;
    } catch(err) {return true;};
};

//====页面引用CLodop云打印必须的JS文件：====
if (needCLodop()) {
	var head = document.head || document.getElementsByTagName("head")[0] || document.documentElement;
	var oscript = document.createElement("script");
	oscript.src ="http://localhost:8000/CLodopfuncs.js?priority=1";
	head.insertBefore( oscript,head.firstChild );

	//引用双端口(8000和18000）避免其中某个被占用：
	oscript = document.createElement("script");
	oscript.src ="http://localhost:18000/CLodopfuncs.js?priority=0";
	head.insertBefore( oscript,head.firstChild );
};

//====获取LODOP对象的主过程：====
function getLodop(oOBJECT,oEMBED){
	/**
		2018.08.14
		修改	刁杰
		../../js/lodop/install_lodop32.exe	->	../../../js/lodop/install_lodop32.exe
		需要往上三级才能到\WebRoot\js文件夹
		有一个问题，采用相对路径的话其他文件夹可能无法定位到
		
		xmdz 参数定义地址：\jsp\config.jsp
	 */
    var strHtmInstall="<br><font color='#FF00FF'>打印控件未安装!点击这里<a href='" + xmdz + "js/lodop/install_lodop32.exe' target='_self'>执行安装</a>,安装后请刷新页面或重新进入。</font>";
    var strHtmUpdate="<br><font color='#FF00FF'>打印控件需要升级!点击这里<a href='" + xmdz + "js/lodop/install_lodop32.exe' target='_self'>执行升级</a>,升级后请重新进入。</font>";
    var strHtm64_Install="<br><font color='#FF00FF'>打印控件未安装!点击这里<a href='" + xmdz + "js/lodop/install_lodop64.exe' target='_self'>执行安装</a>,安装后请刷新页面或重新进入。</font>";
    var strHtm64_Update="<br><font color='#FF00FF'>打印控件需要升级!点击这里<a href='" + xmdz + "js/lodop/install_lodop64.exe' target='_self'>执行升级</a>,升级后请重新进入。</font>";
    var strHtmFireFox="<br><br><font color='#FF00FF'>（注意：如曾安装过Lodop旧版附件npActiveXPLugin,请在【工具】->【附加组件】->【扩展】中先卸它）</font>";
    var strHtmChrome="<br><br><font color='#FF00FF'>(如果此前正常，仅因浏览器升级或重安装而出问题，需重新执行以上安装）</font>";
    var strCLodopInstall="<br><font color='#FF00FF'>CLodop云打印服务(localhost本地)未安装启动!点击这里<a href='CLodop_Setup_for_Win32NT.exe' target='_self'>执行安装</a>,安装后请刷新页面。</font>";
    var strCLodopUpdate="<br><font color='#FF00FF'>CLodop云打印服务需升级!点击这里<a href='CLodop_Setup_for_Win32NT.exe' target='_self'>执行升级</a>,升级后请刷新页面。</font>";
    var LODOP;
    try{
        var isIE = (navigator.userAgent.indexOf('MSIE')>=0) || (navigator.userAgent.indexOf('Trident')>=0);
        if (needCLodop()) {
            try{ LODOP=getCLodop();} catch(err) {};
	    if (!LODOP && document.readyState!=="complete") {alert("C-Lodop没准备好，请稍后再试！"); return;};
            if (!LODOP) {
		 if (isIE) document.write(strCLodopInstall); else
		 document.body.innerHTML=strCLodopInstall+document.body.innerHTML;
                 return;
            } else {

	         if (CLODOP.CVERSION<"3.0.4.3") { 
			if (isIE) document.write(strCLodopUpdate); else
			document.body.innerHTML=strCLodopUpdate+document.body.innerHTML;
		 };
		 if (oEMBED && oEMBED.parentNode) oEMBED.parentNode.removeChild(oEMBED);
		 if (oOBJECT && oOBJECT.parentNode) oOBJECT.parentNode.removeChild(oOBJECT);	
	    };
        } else {
            var is64IE  = isIE && (navigator.userAgent.indexOf('x64')>=0);
            //=====如果页面有Lodop就直接使用，没有则新建:==========
            if (oOBJECT!=undefined || oEMBED!=undefined) {
                if (isIE) LODOP=oOBJECT; else  LODOP=oEMBED;
            } else if (CreatedOKLodop7766==null){
                LODOP=document.createElement("object");
                LODOP.setAttribute("width",0);
                LODOP.setAttribute("height",0);
                LODOP.setAttribute("style","position:absolute;left:0px;top:-100px;width:0px;height:0px;");
                if (isIE) LODOP.setAttribute("classid","clsid:2105C259-1E0C-4534-8141-A753534CB4CA");
                else LODOP.setAttribute("type","application/x-print-lodop");
                document.documentElement.appendChild(LODOP);
                CreatedOKLodop7766=LODOP;
             } else LODOP=CreatedOKLodop7766;
            //=====Lodop插件未安装时提示下载地址:==========
            if ((LODOP==null)||(typeof(LODOP.VERSION)=="undefined")) {
                 if (navigator.userAgent.indexOf('Chrome')>=0)
                     document.body.innerHTML=strHtmChrome+document.body.innerHTML;
                 if (navigator.userAgent.indexOf('Firefox')>=0)
                     document.body.innerHTML=strHtmFireFox+document.body.innerHTML;
                 if (is64IE) document.write(strHtm64_Install); else
                 if (isIE)   document.write(strHtmInstall);    else
                     document.body.innerHTML=strHtmInstall+document.body.innerHTML;
                 return LODOP;
            };
        };
        if (LODOP.VERSION<"6.2.2.3") {
            if (!needCLodop()){
            	if (is64IE) document.write(strHtm64_Update); else
            	if (isIE) document.write(strHtmUpdate); else
            	document.body.innerHTML=strHtmUpdate+document.body.innerHTML;
	    };
            return LODOP;
        };
        //===如下空白位置适合调用统一功能(如注册语句、语言选择等):===
     LODOP.SET_LICENSES("上海金铖科技发展有限公司","4C0D7C51A93B7FA768D7AC0496DA02C3","上海金鋮科技發展有限公司","BDAE7E01401A7BA5F54E3F905996E2CC");
     LODOP.SET_LICENSES("THIRD LICENSE","","Shanghai Jincheng science and Technology Development Co., Ltd.","D130F3711A68B6EAF75BCD4AF400C603");
        //===========================================================
        return LODOP;
    } catch(err) {alert("getLodop出错:"+err);};
};




/**
 * 根据模板ID替换打印内容
 * @param lodopId	模板ID
 * @param jsonStr	需要替换对象
 * @param flag		套打/全打标识符(是否需要背景图片)
 */
function CreateFormPage(lodopId,id){
	
	Ext.Ajax.request({
		url: 'yw/lodop/lodop.json',
		method:'POST',
		params: {
			lodopId : lodopId,
			id : id
		},
		success: function(result,resp){
			var jsonData = Ext.util.JSON.decode(result.responseText);
			if(jsonData&&jsonData.message){
				Ext.getCmp('modulNr').setValue(jsonData.message);
			}else{
				Ext.getCmp('modulNr').setValue("");
			}
			
			//alert(jsonData.data);
			
			/**
				将String转换成前台js代码
			 */
			//eval(jsonData.data);
			
			/**
				添加了背景图片后打印预览不显示,打印后也没有
			 */
			//if(flag){
				//LODOP.ADD_PRINT_SETUP_BKIMG("<img border='0' src='../images/lodop/hukouben.jpg' width='500' height='380' />");
			//}
			
			
			//LODOP.PRINT_DESIGN();
			
			//LODOP.PRINT_SETUP();
			
			//LODOP.PREVIEW();
			
			
		},
		failure: function(){
			//Gnt.MsgBox.showError("获取打印信息出错!");
			alert("获取打印模板内容出错!");
		}
	});
	
}
/**
 * 根据模板ID替换打印内容
 * @param lodopId	模板ID
 * @param jsonStr	需要替换对象
 * @param flag		套打/全打标识符(是否需要背景图片)
 */

function CreateFormPage1(lodopId,jsonStr,flag,num,array,data,index){
	Ext.Ajax.request({
		url: 'yw/lodop/lodop1.json',
		method:'POST',
		//async:false, 
		params: {
			lodopId : lodopId,
			jsonStr : jsonStr,
			flag : flag,
			index:index
		},
		success: function(result,resp){
			var jsonData = Ext.util.JSON.decode(result.responseText);
			//alert(jsonData);
			if(jsonData.success){
				if(jsonData && jsonData.message&&jsonData.message.substring(0,4)=="next"){
					num ++ ;
					return printfunction(num,array,data);
				}else if(jsonData &&jsonData.message&&jsonData.message.substring(0,8)=="continue"){
					index ++ ;
					jsonData.message = jsonData.message.substring(8,jsonData.message.length);
					//callback(lodopId,jsonData,num,array,data);
					
					//continuePrint(lodopId,jsonData,num,array,data).then(CreateFormPage1(lodopId,jsonStr,flag,num,array,data,index));
					continuePrint1(jsonData,lodopId,jsonStr,flag,num,array,data,index,continuePrint2);
				}else{
					callback(lodopId,jsonData,num,array,data);
				}
			}else{
				//Ext.Msg.alert("提示",jsonData.message);
				alert(jsonData.message);
				num ++ ;
				return printfunction(num,array,data);
			}
			
		},
		failure: function(){
			//Gnt.MsgBox.showError("获取打印信息出错!");
			return false;
			alert("获取打印信息出错!");
		}
	});
	
}
function jmhkb_dycsfun(lodopId,jsonStr,flag,num,array,data,index){
	Ext.Ajax.request({
		url: 'yw/lodop/lodop1.json',
		method:'POST',
		//async:false, 
		params: {
			lodopId : lodopId,
			jsonStr : jsonStr,
			flag : flag,
			index:index
		},
		success: function(result,resp){
			num ++ ;
			return printfunction(num,array,data);
		},
		failure: function(){
			//Gnt.MsgBox.showError("获取打印信息出错!");
			alert("获取打印信息出错!");
		}
	});
	
}
function printfunction(num,array,data){
	if(num < array.length){
		var directTable=array[num].directTable
		if(directTable=="czrkdjb"){
			if(window.confirm('是否要打印['+array[num].xm+']的常住人口登记表？')){
				CreateFormPage1(directTable,Ext.util.JSON.encode(data),array[num].rynbid,num,array,data);
			}else{
				num ++ ;
				return printfunction(num,array,data);
			}
		}
		if(directTable=="jmhkb_dycs"){
			jmhkb_dycsfun(directTable,Ext.util.JSON.encode(data),array[num].rynbid,num,array,data);
		}
		if(directTable=="jmhkb_sy"){
			//是否收费标志
			Gnt.ux.Dict.getKzcs('10030', function(config, userObj, kzdata){
				if(config&&config.kzz=='1'){
					//查询户打印次数
				 	   Gnt.submit(
				 	 			 {rynbid:array[num].rynbid}, 
				 					"yw/common/queryDycsByhh", 
				 					"按户号查询打印次数，请稍后...", 
				 					function(jsonData,params){
					 	 				if(jsonData.list){
					 	 					if(jsonData.list[0]>0){
				 	 							var je=parseInt(user.personSet.hjsysf);
				 	 							sffsWin.show();
				 	 							sffs_form.reset(je,num,array,data);
				 	 							
					 	 					}else{
					 	 						if(window.confirm('是否要打印['+array[num].xm+']的户口簿首页？')){
					 	 							CreateFormPage1(directTable,Ext.util.JSON.encode(data),array[num].rynbid,num,array,data);
					 	 						}else{
					 	 							num ++ ;
					 	 							return printfunction(num,array,data);
					 	 						}
					 	 					}
					 					}
					 	 			}
				 	   );
				}else{
					if(window.confirm('是否要打印['+array[num].xm+']的户口簿首页？')){
						CreateFormPage1(directTable,Ext.util.JSON.encode(data),array[num].rynbid,num,array,data);
					}else{
						num ++ ;
						return printfunction(num,array,data);
					}
				}
			});
			
//			if(window.confirm('是否要打印['+array[num].xm+']的户口簿首页？')){
//				CreateFormPage1(directTable,Ext.util.JSON.encode(data),array[num].rynbid,num,array,data);
//			}else{
//				num ++ ;
//				return printfunction(num,array,data);
//			}
		}		
		if(directTable=="jmhkb_bm"){
			Ext.MessageBox.prompt("提示", "是否要打印"+array[num].xm+"的户口簿背面？请输入打印的行位置！", function(id, text){
				var reg = /^[1-9]\d*$/;
				if (id == "ok") {
					if (!reg.test(text)) {
						alert("请输入数字")
						return false;
					}
					CreateFormPage1(directTable,Ext.util.JSON.encode(data),array[num].rynbid,num,array,data,text);					
				}else{
					num ++ ;
					return printfunction(num,array,data);
					
				}
			});			
		}
		if(directTable=="jmhkb_jthfs_sy"){
			if(window.confirm('是否要打印['+array[num].xm+']的集体户方式户口首页？')){
				CreateFormPage1(directTable,Ext.util.JSON.encode(data),array[num].rynbid,num,array,data);
			}else{
				num ++ ;
				return printfunction(num,array,data);
			}
		}
		if(directTable=="jmhkb_ny"){
			if(window.confirm('是否要打印['+array[num].xm+']的户口簿内页？')){
				CreateFormPage1(directTable,Ext.util.JSON.encode(data),array[num].rynbid,num,array,data,Ext.util.JSON.encode(array[num].hzywjo));
			}else{
				num ++ ;
				return printfunction(num,array,data);
			}
		}
		if(directTable=="hjzm"){
			if(window.confirm('是否要打印['+array[num].xm+']的户籍证明？')){
				CreateFormPage1(directTable,Ext.util.JSON.encode(data),array[num].rynbid,num,array,data,Ext.util.JSON.encode(array[num].hzywjo));
			}else{
				num ++ ;
				return printfunction(num,array,data);
			}
		}
		if(directTable=="hjzmx"){
			if(window.confirm('是否要打印['+array[num].xm+']的户籍证明(律师用)？')){
				CreateFormPage1(directTable,Ext.util.JSON.encode(data),array[num].rynbid,num,array,data,Ext.util.JSON.encode(array[num].hzywjo));
			}else{
				num ++ ;
				return printfunction(num,array,data);
			}
		}
		if(directTable=="hjzmNew"){
			if(window.confirm('是否要打印['+array[num].xm+']的户籍证明(律师用)？')){
				CreateFormPage1(directTable,Ext.util.JSON.encode(array[num]),array[num].ryid,num,array,data);
			}else{
				num ++ ;
				return printfunction(num,array,data);
			}
		}
		if(directTable=="hkxzzm"){
			if(window.confirm('是否要打印['+array[num].xm+']的户口性质证明？')){
				CreateFormPage1(directTable,Ext.util.JSON.encode(data),array[num].rynbid,num,array,data,Ext.util.JSON.encode(array[num].hzywjo));
			}else{
				num ++ ;
				return printfunction(num,array,data);
			}
		}
		if(directTable=="djksyb"){
			Ext.MessageBox.prompt("提示", "是否要打印所选人员的常住人口登记索引表？请输入打印信息所在的起始！", function(id, text){
				var reg = /^[1-9]\d*$/;
				if (id == "ok") {
					if (!reg.test(text)) {
						alert("请输入数字")
						return false;
					}
					if((parseInt(text)+parseInt(array[num].rynbid))>10){
						alert("选择的个数，从索引位置开始打印，将会超过页面显示上限，请重新输")
						return false;
					}
					CreateFormPage1(directTable,Ext.util.JSON.encode(data),array[num].xm,num,array,data,text);//array[num].xm 存放的是选择的人的id拼接字段				
				}else{
					num ++ ;
					return printfunction(num,array,data);
					
				}
			});	
		}
		if(directTable=="qyz1"){
			validFunction(array[num].yznf,array[num].qyzbh,1,array[num],num,array);
//			if(window.confirm('是否要打印['+array[num].xm+']的迁移证？')){
//				CreateFormPage1(directTable,Ext.encode(array[num]),"qyz1",num,array,num);
//			}else{
//				num ++ ;
//				return printfunction(num,array,data);
//			}
		}
		if(directTable=="qyz2"){
			validFunction(array[num].yznf,array[num].qyzbh,2,array[num].data,num,array);
		}
		//lodopId,jsonStr,flag,num,array,data,index
		if(directTable=="rkxx"){
			CreateFormPage1(directTable,Ext.util.JSON.encode(data),array[num].rynbid,num,array,data);
		}
		if(directTable=="hjblspb"){
			CreateFormPage1(directTable,Ext.util.JSON.encode(array[num]),array[num].flag,num,array,data);
		}
		if(directTable=="hjscspb"){
			CreateFormPage1(directTable,Ext.util.JSON.encode(data),array[num].rynbid,num,array,data);
		}
		if(directTable=="swzxzm"){
			CreateFormPage1(directTable,Ext.util.JSON.encode(array[num]),array[num].swzxid,num,array,data);
		}
		if(directTable=="zqz"){
			//if(num==0){
			array[num].zqzbh=Ext.getCmp('zqzbh').getValue();
				validzqzbh(directTable,array[num].zjlx,Ext.util.JSON.encode(array[num].obj),num,array,array[num].zqzbh,array[num].obj.spywid);
			//}else if(num>0){
//				Ext.MessageBox.prompt("提示", "请重新输入新的打印编号！", function(id, text){
//					var reg = /^\d{8}$/;
//					if (id == "ok") {
//						if (!reg.test(text)) {
//							alert("请输入数字")
//							return false;
//						}
//						Ext.getCmp('zqzbh').setValue(text);
//						if(parseInt(text)<=9999999||parseInt(text)>=100000000){
//							alert("只能是八位纯数字!")
//							
//							return false;
//						}
//						validzqzbh(directTable,array[num].zjlx,Ext.util.JSON.encode(array[num].obj),num,array,text,array[num].obj.spywid);
//					}else{
//						num ++ ;
//						return printfunction(num,array,data);
//						
//					}
//				}, this, false, addPreZero(parseInt(parseInt(array[num-1].zqzbh)+1)));
			//}
	}
	if(directTable=="zdydy"){
		if(window.confirm('是否要打印['+array[num].xm+']的自定义报表-'+array[num].modulValue)){
			CreateFormPage1(directTable,Ext.util.JSON.encode(data),array[num].rynbid,num,array,data,array[num].modulId);
		}else{
			num ++ ;
			return printfunction(num,array,data);
		}
	}
	if(directTable=="czrkdjbyhybdy"){
		if(array[num].dysx==2){
			var config ={  
				    title:"系统提示",  
				    msg:array[num].hh+'户号是否要打印?',  
				    width:400,  
				    multiline:false,  
				    closable:false,  
				    buttons:Ext.MessageBox.YESNOCANCEL,  
				    icon:Ext.MessageBox.QUESTION,  
				    fn:  function(btn,txt){  
				    	if(btn=='yes'){
				    		CreateFormPage1(directTable,Ext.util.JSON.encode(array[num]),array[num].rynbid,num,array,data,0);
				    	}else if(btn=='no'){
							num ++ ;
							return printfunction(num,array,data);
				    	}else if(btn=='cancel'){
				    		return;
				    	}
			}};
			Ext.MessageBox.show(config);
//			if(window.confirm(array[num].hh+'户号是否要打印?')){
//				CreateFormPage1(directTable,Ext.util.JSON.encode(array[num]),array[num].rynbid,num,array,data,0);
//			}else{
//				num ++ ;
//				return printfunction(num,array,data);
//			}
		}else if(array[num].dysx==1){
			CreateFormPage1(directTable,Ext.util.JSON.encode(array[num]),array[num].rynbid,num,array,data,0);
		}
		
	}
	if(directTable=="czrkhddjbyhybdy"){
		if(array[num].dysx==2){
			var config ={  
				    title:"系统提示",  
				    msg:array[num].hh+'户号是否要打印?',  
				    width:400,  
				    multiline:false,  
				    closable:false,  
				    buttons:Ext.MessageBox.YESNOCANCEL,  
				    icon:Ext.MessageBox.QUESTION,  
				    fn:  function(btn,txt){  
				    	if(btn=='yes'){
							CreateFormPage1(directTable,Ext.util.JSON.encode(array[num]),array[num].rynbid,num,array,data,0);
				    	}else if(btn=='no'){
							num ++ ;
							return printfunction(num,array,data);
				    	}else if(btn=='cancel'){
				    		return;
				    	}
			}};
			Ext.MessageBox.show(config);
//			if(window.confirm(array[num].hh+'户号是否要打印?')){
//				CreateFormPage1(directTable,Ext.util.JSON.encode(array[num]),array[num].rynbid,num,array,data,0);
//			}else{
//				num ++ ;
//				return printfunction(num,array,data);
//			}
		}else if(array[num].dysx==1){
			CreateFormPage1(directTable,Ext.util.JSON.encode(array[num]),array[num].rynbid,num,array,data,0);
		}		
	}
	if(directTable=="cbtaoda"){
		if(array[num].dysx==2){
			if(window.confirm(array[num].gmsfhm+'是否要打印?')){
				CreateFormPage1(directTable,Ext.util.JSON.encode(array[num]),array[num].rynbid,num,array,data,0);
			}else{
				num ++ ;
				return printfunction(num,array,data);
			}
		}else if(array[num].dysx==1){
			CreateFormPage1(directTable,Ext.util.JSON.encode(array[num]),array[num].rynbid,num,array,data,0);
		}
		
	}
	if(directTable=="cbzhengda"){
		if(array[num].dysx==2){
			if(window.confirm(array[num].gmsfhm+'是否要打印?')){
				CreateFormPage1(directTable,Ext.util.JSON.encode(array[num]),array[num].rynbid,num,array,data,0);
			}else{
				num ++ ;
				return printfunction(num,array,data);
			}
		}else if(array[num].dysx==1){
			CreateFormPage1(directTable,Ext.util.JSON.encode(array[num]),array[num].rynbid,num,array,data,0);
		}		
	}
	if(directTable=="bgspb"){
		CreateFormPage1(directTable,Ext.util.JSON.encode(array[num]),array[num].spywid,num,array,data,num);
	}
	if(directTable=="tzd"){
		CreateFormPage1(directTable,Ext.util.JSON.encode(data),array[num].rynbid,num,array,data);
	}
	if(directTable=="cbd"){
		CreateFormPage1(directTable,Ext.util.JSON.encode(array[num].json),array[num].rynbid,num,array,data);
	}
	if(directTable=="cld"){
		CreateFormPage1(directTable,Ext.util.JSON.encode(data),array[num].nbslid,num,array,data);
	}
	if(directTable=="wts"){
		CreateFormPage1(directTable,Ext.util.JSON.encode(data),array[num].nbslid,num,array,data);
	}
	if(directTable=="fafd"){
		CreateFormPage1(directTable,Ext.util.JSON.encode(array[num]),null,num,array,data);
	}
	if(directTable=="fenfd"){
		CreateFormPage1(directTable,Ext.util.JSON.encode(array[num]),null,num,array,data);
	}
	if(directTable=="sldjb"){
		CreateFormPage1(directTable,Ext.util.JSON.encode(data),array[num].nbslid,num,array,data);
	}
	if(directTable=="ydslsldjb"){
		CreateFormPage1(directTable,Ext.util.JSON.encode(data),array[num].sbxxid,num,array,data);
	}
	if(directTable=="jmsfzsldjb"){//jmsfzsldjb  居民身份证申领登记表模板 
		CreateFormPage1(directTable,Ext.util.JSON.encode(data),array[num].rynbid,num,array,data);
	}
	if(directTable=="gmsfzsfd"){//jmsfzsldjb  居民身份证申领登记表模板 
		CreateFormPage1(directTable,Ext.util.JSON.encode(data),array[num].rynbid,num,array,data);
	}
	if(directTable=="dkdyzd"||directTable=="dkdytd"){//底卡打印整打套打
		CreateFormPage1(directTable,Ext.util.JSON.encode(array[num]),array[num].bzslid,num,array,data);
	}
	if(directTable=="kzjsx"){//快证介绍信
		CreateFormPage1(directTable,Ext.util.JSON.encode(array[num]),array[num].bzslid,num,array,data);
	}
	if(directTable=="lsjmsfzsldjb"){//受理单
		CreateFormPage1(directTable,Ext.util.JSON.encode(array[num]),array[num].ryid,num,array,data);
	}
	if(directTable=="cbqc"){//常表迁出打印
		CreateFormPage1(directTable,Ext.util.JSON.encode(array[num]),array[num].hjywid,num,array,data);
	}
	if(directTable=="cg"){//存根打印
		CreateFormPage1(directTable,Ext.util.JSON.encode(array[num]),array[num].ryid,num,array,data);
	}
	if(directTable=="zxqsgxzm"){//直系亲属关系证明打印
		CreateFormPage1(directTable,Ext.util.JSON.encode(array[num]),array[num].ryid,num,array,data);
	}
	if(directTable=="hkzxzm"){//户口注销证明打印
		CreateFormPage1(directTable,Ext.util.JSON.encode(array[num]),array[num].rynbid,num,array,data);
	}
	if(directTable=="wdjhk"){//当事人未登记户口的证明出具
		CreateFormPage1(directTable,Ext.util.JSON.encode(array[num]),array[num].rynbid,num,array,data);
	}
	if(directTable=="fhzm"){//分户证明出具
		CreateFormPage1(directTable,Ext.util.JSON.encode(array[num]),array[num].rynbid,num,array,data);
	}
	if(directTable=="bggzxmzm"){//户口登记项目内容变更更正证明
		CreateFormPage1(directTable,Ext.util.JSON.encode(data),array[num].bggzid,num,array,data,data);
	}
	if(directTable=="gatdjzxzm"){//香港、澳门、台湾定居注销户口证明
		CreateFormPage1(directTable,Ext.util.JSON.encode(array[num]),array[num].rynbid,num,array,data,array[num].qczxid);
	}
	if(directTable=="gwdjzxzm"){//国外定居注销户口证明
		CreateFormPage1(directTable,Ext.util.JSON.encode(array[num]),array[num].rynbid,num,array,data,array[num].qczxid);
	}
	if(directTable=="sftyrxzhczm"){//公民是否同一人的协助核查证明
		CreateFormPage1(directTable,Ext.util.JSON.encode(array[num]),array[num].ryid,num,array,data);
	}
	}else{
		
		/**
			户口打印点击确定后将户政业务设置为已处理
		 */
//		var hzywid = getQueryParam("hzywid");
//		try{
//			if(hzywid){
//				Gnt.dealHzyw(hzywid);
//			}
//		}catch(err){
//			//在这里处理错误
//		}
		
		return ;
	}
	
}

function callback(lodopId,jsonData,num,array,data){
	if(myAddHtml()){
		if(jsonData && jsonData.message&& jsonData.message!=""){
			eval(jsonData.message);
			
			if((lodopId=="czrkdjb"||lodopId=="czrkdjbq"||lodopId=="jmhkb_sy"||lodopId=="jmhkb_bm"||lodopId=="jmhkb_jthfs_sy"
				||lodopId=="jmhkb_ny"||lodopId=="hjzm"||lodopId=="djksyb"||lodopId=="czrkdjbqt"||lodopId=="czrkdjbt"
				||lodopId=="czrkdjbbmtd"||lodopId=="czrkdjbbmtdnv"||lodopId=="czrkdjbbmtdnan"||lodopId=="czrkdjbbmzd")){
				
				if(dyWindow.printset.printset_1.checked||dyWindow.printset.printset_2.checked){
					LODOP.PRINT_SETUP();
					//LODOP.PRINT();
				}/*else if(dyWindow.printset.printset_2.checked){
					LODOP.SET_PREVIEW_WINDOW(0,1,0,0,0,"");//打印前弹出选择打印机的对话框	
					LODOP.SET_PRINT_MODE("AUTO_CLOSE_PREWINDOW",1);//打印后自动关闭预览窗口
					LODOP.PREVIEW();
				}*/else{
					LODOP.PRINT();
				}
				
			}else{
				LODOP.PRINT_SETUP();
			}
		}else{
			showInfo("出错！");
		}
	}
	num ++ ;
	return printfunction(num,array,data);
	
}
function previeInsertwModul(modulNr)
{
	var exp = Ext.getCmp('lodopId').getValue();
	var exp1 = Ext.getCmp('lodopName').getValue();
	var reg =/^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){1,19}$/;
	var reg1 =/^[A-Za-z0-9\u4e00-\u9fa5]+$/;
	if (!exp && typeof(exp)!="undefined" && exp=="")
	{
		alert("请输入模板Id");
		return;
	}else if(!reg.test(exp)){
		alert("模板名称必须以字母开头，后面带20位内字母,数字,下划线,逗号组成！");
		return;
	}
	if (!exp1 && typeof(exp1)!="undefined" && exp1=="")
	{
		alert("请输入模板名称");
		return;
	}else if(!reg1.test(exp1)){
		alert("模板名称必须是汉字数字字母组成！");
		return;
	}
	LODOP=getLodop(); 
	eval(modulNr);
	var nrTemp=LODOP.PRINT_DESIGN();
	var index = nrTemp.indexOf(";");
	var temp= nrTemp.substring(0,index);
	Ext.getCmp('modulNr').setValue(nrTemp);
}
function previeQuerywModul(modulNr)
{
	LODOP=getLodop(); 
	eval(modulNr);
	var nrTemp=LODOP.PRINT_DESIGN();
	Ext.getCmp('modulNr').setValue(nrTemp);
}

function validzqzbh(directTable,zjlx,obj,num,array,zqzbh,spywid){
	Ext.Ajax.request({
	url: 'yw/lodop/validZqzbh.json',
	method:'POST',
	//async:false, 
	params: {
		spywid : spywid,
		zqzbh : zqzbh
	},
	success: function(result,resp){
		var jsonData = Ext.util.JSON.decode(result.responseText);
		if(jsonData.message>0){
			if(window.confirm('此准迁证编号已经存在，继续打印吗？')){
				CreateFormPage1(directTable,zjlx,obj,num,array,0,zqzbh);
			}else{
				zqzValid(directTable,zqzbh,array,num,data);
				//validzqzbh(directTable,array[num].zjlx,Ext.util.JSON.encode(array[num].obj),num,array,text,array[num].obj.spywid);
//				Ext.getCmp('zqzbh').setValue(parseInt(array[num].zqzbh)+1);
//					var tips="请重新输入新的打印编号！";
//					if(num>0){
//						tips="要换另外一张准迁证，请重新输入新的打印编号！";
//					}
//					Ext.MessageBox.prompt("提示",tips, function(id, text){	
//					var reg = /^\d{8}$/;
//					if (id == "ok") {
//						if (!reg.test(text)) {
//							alert("请输入数字")
//							return false;
//						}
//						Ext.getCmp('zqzbh').setValue(text);
//						if(parseInt(text)<=9999999||parseInt(text)>=100000000){
//							alert("只能是八位纯数字!")
//							
//							return false;
//						}
//						array[num].zqzbh = text;
//						validzqzbh(directTable,array[num].zjlx,Ext.util.JSON.encode(array[num].obj),num,array,text,array[num].obj.spywid);
//					}else{
//						Ext.getCmp('zqzbh').setValue(text);
//						validzqzbh(directTable,array[num].zjlx,Ext.util.JSON.encode(array[num].obj),num,array,text,array[num].obj.spywid);
//						//num ++ ;
//						//return printfunction(num,array,data);
//						
//					}
//				}, this, false, parseInt(array[num].zqzbh)+1);
			}
		}else{
			CreateFormPage1(directTable,zjlx,obj,num,array,0,zqzbh);
		}
		
		
	},
	failure: function(){
		alert("验证准迁证编码方法报错啦！");
		return;
	}
});	
}
function addPreZero(num){  return ('0000000'+num).slice(-8); } 
//function continuePrint(lodopId,jsonData,num,array,data){
//	var dfd = $.Deferred();
//　　　　setTimeout(function () {
//
//　　　　　　callback(lodopId,jsonData,num,array,data);
//
//　　　　　　dfd.resolve();
//
//　　　　}, 1000);
//	return dfd.promise;
//
//　　}

function continuePrint1(jsonData,lodopId,jsonStr,flag,num,array,data,index,continuePrint2){
	setTimeout(function () {
		callback(lodopId,jsonData,num,array,data);
		continuePrint2(jsonData,lodopId,jsonStr,flag,num,array,data,index);
	}, 0);
	
	
}
function continuePrint2(jsonData,lodopId,jsonStr,flag,num,array,data,index){
	//callback(lodopId,jsonData,num,array,data);
	CreateFormPage1(lodopId,jsonStr,flag,num,array,data,index);
}
