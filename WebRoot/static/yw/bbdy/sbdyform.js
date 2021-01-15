Gnt.ux.sbdywin= Ext.extend(Ext.Window, {
	title:'打印',
	closeAction:'hide',
	modal:true,
	width:600,
	height:400,
	margins:'0',
	layout:'border',
	html:'',
	show:function(){
		Gnt.ux.sbdywin.superclass.show.call(this);
	},

	setSelRes:function(data,type,zdKeyArray,zdValueArray,dictMap){
//		var dictMap = new Map();
//		dictMap.set('yhzgx','CS_JTGX');
//		dictMap.set('csdssxq','XZQHB');
//		dictMap.set('pcs','DWXXB');
//		dictMap.set('qclb','CS_QCZXLB');
//		dictMap.set('qwdgjdq','CS_GJ');
//		dictMap.set('qwdssxq','XZQHB');
//		dictMap.set('cxbz','CS_CXBZ');
//		dictMap.set('xb','CS_XB');
//		dictMap.set('cxfldm','CS_CXFLDM');
//		dictMap.set('jcwh','JWHXXB');
//		dictMap.set('swzxlb','CS_SWZXLB');
//		dictMap.set('zzbdlb','CS_ZZBDLB');
//		dictMap.set('ssxq_q','XZQHB');
//		dictMap.set('jlx_q','JLXXXB');
//		dictMap.set('ssxq','XZQHB');
//		dictMap.set('jlx','JLXXXB');
//		dictMap.set('slrid','YHXXB');
//		dictMap.set('sldw','DWXXB');
//		dictMap.set('mz','CS_MZ');
//		dictMap.set('zrq','JWZRQXXB');
//		dictMap.set('xzjd','XZJDXXB');
//		dictMap.set('hbbglb','CS_BDYY');
//		dictMap.set('bgqhb','CS_HB');
//		dictMap.set('bghhb','CS_HB');
//		dictMap.set('bdfw','CS_BDFW');
//		dictMap.set('csdxz','XZQHB');
//		dictMap.set('byzk','CS_BYZK');
//		dictMap.set('qrqhb','CS_HB');
//		dictMap.set('qrlb','CS_QRLB');
//		dictMap.set('nyzyrklhczyydm','CS_NYZYRKLHCZYY');
//		dictMap.set('zyjszc_pdbz','CS_SFBZ');
//		dictMap.set('jndj_pdbz','CS_SFBZ');
//		dictMap.set('ncjdzzyxbys_pdbz','CS_SFBZ');
//		dictMap.set('jjqx_pdbz','CS_SFBZ');
//		dictMap.set('zczjyhjzwnys_pdbz','CS_SFBZ');
//		dictMap.set('ncjsbtcxy_pdbz','CS_SFBZ');
//		dictMap.set('ywlx','CS_YWLX');
//		dictMap.set('csdjlb','CS_CSDJLB');
//		dictMap.set('hb','CS_HB');
//		dictMap.set('hjbllb','CS_HJBLLB');
//		dictMap.set('hjsclb','CS_HJSCLB');
//		dictMap.set('clbz','CS_QCCLBZ');
//		dictMap.set('czlx','CS_QCCLCZLX');
//		dictMap.set('jcwh_h','JWHXXB');
//		dictMap.set('jcwh_q','JWHXXB');
//		dictMap.set('jlx_h','JLXXXB');
//		dictMap.set('jlx_q','JLXXXB');
//		dictMap.set('pcs_h','DWXXB');
//		dictMap.set('pcs_q','DWXXB');
//		dictMap.set('ssxq_h','XZQHB');
//		dictMap.set('ssxq_q','XZQHB');
//		dictMap.set('xzjd_h','XZJDXXB');
//		dictMap.set('xzjd_q','XZJDXXB');
//		dictMap.set('ywbz','CS_YWBZ');
//		dictMap.set('zrq_h','JWZRQXXB');
//		dictMap.set('zrq_q','JWZRQXXB');
//		dictMap.set('qcdssxq','XZQHB');
//		dictMap.set('cxrid','YHXXB');
//		
//		dictMap.set('qtssxq','XZQHB');
//		dictMap.set('jgssxq','XZQHB');
//		dictMap.set('cxrid','YHXXB');
//		dictMap = new Map([['yhzgx','CS_JTGX'],['csdssxq','XZQHB'],['pcs','DWXXB'],['qclb','CS_QCZXLB'],['qwdgjdq','CS_GJ'],
//				['qwdssxq','XZQHB'],['cxbz','CS_CXBZ'],['xb','CS_XB'],['cxfldm','CS_CXFLDM'],['jcwh','JWHXXB'],
//				['swzxlb','CS_SWZXLB'],['zzbdlb','CS_ZZBDLB'],['ssxq_q','XZQHB'],['jlx_q','JLXXXB'],['ssxq','XZQHB'],
//				['jlx','JLXXXB'],['slrid','YHXXB'],['sldw','DWXXB'],['mz','CS_MZ'],['zrq','JWZRQXXB'],['xzjd','XZJDXXB'],
//				['hbbglb','CS_BDYY'],['bgqhb','CS_HB'],['bghhb','CS_HB'],['bdfw','CS_BDFW'],['csdxz','XZQHB'],
//				['byzk','CS_BYZK'],['qrqhb','CS_HB'],['qrlb','CS_QRLB'],['nyzyrklhczyydm','CS_NYZYRKLHCZYY'],
//				['zyjszc_pdbz','CS_SFBZ'],['jndj_pdbz','CS_SFBZ'],['ncjdzzyxbys_pdbz','CS_SFBZ'],['jjqx_pdbz','CS_SFBZ'],
//				['zczjyhjzwnys_pdbz','CS_SFBZ'],['ncjsbtcxy_pdbz','CS_SFBZ'],['ywlx','CS_YWLX'],['csdjlb','CS_CSDJLB'],
//				['hb','CS_HB'],['hjbllb','CS_HJBLLB'],['hjsclb','CS_HJSCLB']]);
		
//		var cell = document.getElementById("Celllist");
		Gnt.initCellWeb('cellCtrl','Celllist');
		cell.ResetContent();
		//cell.Login("浙江金铖华元信息技术有限公司自定义报表-WEB版","","13040351","1340-1275-0110-0004");
		var index=cell.GetSheetIndex("第1页");
		cell.SetCols(33,index);
		//cell.MergeCells(2,1,3,1);
		for(var c=1;c<30;c++){
			cell.SetColWidth(1,150,c,index);
		}
		if(data.length+3>cell.GetRows(0)){
			cell.SetRows(data.length+3,0);
		}
		if(type=="qc"){
			cell.SetCellString(2,1,index,"迁出");
			var qcArray = null;
			if(zdKeyArray&&zdValueArray){
				qcArray= zdValueArray;
			}else{
				qcArray= new Array("姓名","公民身份号码","与户主关系","出生地省市县(区)","派出所","迁出注销类别",
						"迁出日期","迁往地国家(地区)","迁往地省市县(区)","迁往地详址","准迁证编号","迁移证编号","冲销标志","户主姓名","性别","历史城乡代码","户号");
				zdKeyArray = new Array("xm","gmsfhm","yhzgx","csdssxq","pcs","qclb",
						"qcrq","qwdgjdq","qwdssxq","qwdxz","zqzbh","qyzbh","cxbz","hzxm","xb","cxfldm","hhid");
			}
			for(var qc=0;qc<qcArray.length;qc++){
				cell.SetCellString(qc+1,2,index,qcArray[qc]);
			}
			for(var i=0;i<data.length;i++){
				var obj=data[i];
				for(var j=1;j<=zdKeyArray.length&&j<=33;j++){
					if(dictMap.get(zdKeyArray[(j-1)])){
						cell.SetCellString(j,i+3,index,Gnt.ux.Dict.getDictLable(dictMap.get(zdKeyArray[(j-1)]).toUpperCase(), obj[zdKeyArray[(j-1)]]));
					}else{
						cell.SetCellString(j,i+3,index,obj[zdKeyArray[(j-1)]]);
					}
					
				}
			}
		}
		
		if(type=="sw"){
			cell.SetCellString(2,1,index,"死亡");
			var swArray = null;
			if(zdKeyArray&&zdValueArray){
				swArray= zdValueArray;
			}else{
				swArray= new Array("姓名","性别","与户主关系","死亡日期","公民身份号码","派出所","居委会","死亡证明编号","死亡注销类别","冲销标志","户主姓名");
				zdKeyArray = new Array("xm","xb","yhzgx","swrq","gmsfhm","pcs","jcwh","swzmbh","swzxlb","cxbz","hzxm");
			}
			for(var sw=0;sw<swArray.length;sw++){
				cell.SetCellString(sw+1,2,index,swArray[sw]);
			}
			for(var i=0;i<data.length;i++){
				var obj=data[i];
				for(var j=1;j<=zdKeyArray.length&&j<=33;j++){
					if(dictMap.get(zdKeyArray[(j-1)])){
						cell.SetCellString(j,i+3,index,Gnt.ux.Dict.getDictLable(dictMap.get(zdKeyArray[(j-1)]).toUpperCase(), obj[zdKeyArray[(j-1)]]));
					}else{
						cell.SetCellString(j,i+3,index,obj[zdKeyArray[(j-1)]]);
					}
					
				}
			}
		}
		
		if(type=="zzbd"){
			cell.SetCellString(2,1,index,"住址变动");
			var zzbdArray = null;
			if(zdKeyArray&&zdValueArray){
				zzbdArray= zdValueArray;
			}else{
				zzbdArray= new Array("姓名","公民身份号码","性别","与户主关系","迁移证编号",
						"住址变动日期","住址变动类别","(变动前)户号","(变动前)省市县(区)",
						"(变动前)街路巷","(变动后)户号","(变动后)省市县(区)","(变动后)街路巷",
						"申报人姓名","申报人公民身份号码","受理人姓名","受理时间","受理单位","冲销标志");
				zdKeyArray = new Array("xm","gmsfhm","xb","yhzgx","qyzbh",
						"zzbdrq","zzbdlb","hh_q","ssxq_q",
						"jlx_q","hh","ssxq","jlx",
						"sbryxm","sbrgmsfhm","slrid","slsj","sldw","cxbz");
			}
			for(var zzbd=0;zzbd<zzbdArray.length;zzbd++){
				cell.SetCellString(zzbd+1,2,index,zzbdArray[zzbd]);
			}
			for(var i=0;i<data.length;i++){
				var obj=data[i];
				for(var j=1;j<=zdKeyArray.length&&j<=33;j++){
					if(dictMap.get(zdKeyArray[(j-1)])){
						cell.SetCellString(j,i+3,index,Gnt.ux.Dict.getDictLable(dictMap.get(zdKeyArray[(j-1)]).toUpperCase(), obj[zdKeyArray[(j-1)]]));
					}else{
						cell.SetCellString(j,i+3,index,obj[zdKeyArray[(j-1)]]);
					}
					
				}
			}
		}
		
		
		if(type=="hbbg"){
			cell.SetCellString(2,1,index,"户别变更");
			var hbbgArray = null;
			if(zdKeyArray&&zdValueArray){
				hbbgArray= zdValueArray;
			}else{
				hbbgArray= new Array("公民身份号码","姓名","性别","名族","出生日期","与户主关系",
						"出生时间","出生地省市县(区)","省市县(区)","派出所","街路巷","责任区","乡镇(街道)",
						"居委会","户别变更类别","变更前户别","变更后户别","变动范围","受理人姓名","申报人姓名",
						"申报人公民身份号码","受理时间","受理单位","冲销标志","户别变更日期");
				zdKeyArray = new Array("gmsfhm","xm","xb","mz","csrq","yhzgx",
						"cssj","csdssxq","ssxq","pcs","jlx","zrq","xzjd",
						"jcwh","hbbglb","bgqhb","bghhb","bdfw","slrid","sbryxm",
						"sbrgmsfhm","slsj","sldw","cxbz","hbbgrq");
			}
			for(var hbbg=0;hbbg<hbbgArray.length;hbbg++){
				cell.SetCellString(hbbg+1,2,index,hbbgArray[hbbg]);
			}
			for(var i=0;i<data.length;i++){
				var obj=data[i];
				for(var j=1;j<=zdKeyArray.length&&j<=33;j++){
					if(dictMap.get(zdKeyArray[(j-1)])){
						cell.SetCellString(j,i+3,index,Gnt.ux.Dict.getDictLable(dictMap.get(zdKeyArray[(j-1)]).toUpperCase(), obj[zdKeyArray[(j-1)]]));
					}else{
						cell.SetCellString(j,i+3,index,obj[zdKeyArray[(j-1)]]);
					}
					
				}
			}
		}
		
		if(type=="qr"){
			cell.SetCellString(2,1,index,"迁入");
			var qrArray = null;
			if(zdKeyArray&&zdValueArray){
				qrArray= zdValueArray;
			}else{
				qrArray= new Array("姓名","性别","名族","出生日期","公民身份号码","出生地省市县","出生地详址","居(村)委会","兵役状况","电话号码",
						"迁入前户别","迁入类别","迁入日期","迁出详址","变动范围","冲销标志","户主姓名","户号","历史城乡代码","落户城镇原因",
						"具有专业职称落户城镇","具有技能等级落户城镇","高等院校毕业落户城镇","举家迁徙落户城镇",
						"就业和居住五年落户城镇","退出现役落户城镇","派出所","业务类型","操作数目");
				zdKeyArray = new Array("xm","xb","mz","csrq","gmsfhm","csdssxq","csdxz","jcwh","byzk","dhhm",
						"qrqhb","qrlb","qrrq","qcdxz","bdfw","cxbz","hzxm","hhid","cxfldm","nyzyrklhczyydm",
						"zyjszc_pdbz","jndj_pdbz","ncjdzzyxbys_pdbz","jjqx_pdbz",
						"zczjyhjzwnys_pdbz","ncjsbtcxy_pdbz","pcs","ywlx","czsm");
			}
			for(var qr=0;qr<qrArray.length;qr++){
				cell.SetCellString(qr+1,2,index,qrArray[qr]);
			}
			for(var i=0;i<data.length;i++){
				var obj=data[i];
				for(var j=1;j<=zdKeyArray.length&&j<=33;j++){
					if(dictMap.get(zdKeyArray[(j-1)])){
						cell.SetCellString(j,i+3,index,Gnt.ux.Dict.getDictLable(dictMap.get(zdKeyArray[(j-1)]).toUpperCase(), obj[zdKeyArray[(j-1)]]));
					}else{
						cell.SetCellString(j,i+3,index,obj[zdKeyArray[(j-1)]]);
					}
					
				}
			}
		}
		
		if(type=="cs"){
			cell.SetCellString(2,1,index,"出生");
			var csArray = null;
			if(zdKeyArray&&zdValueArray){
				csArray= zdValueArray;
			}else{
				csArray= new Array("公民身份号码","姓名","性别","名族","与户主关系","出生日期","出生时间","出生证明编号","出生登记类别",
						"派出所","街路巷","乡镇(街道)","局(村)委会","出生地省市县","责任区","申报人姓名","申报人公民身份号码","受理人姓名","受理时间",
						"受理单位","冲销标志","户主姓名","户号");
				zdKeyArray = new Array("gmsfhm","xm","xb","mz","yhzgx","csrq","cssj","cszmbh","csdjlb","pcs",
						"jlx","xzjd","jcwh","csdssxq","zrq","sbryxm","sbrgmsfhm","slrid","slsj","sldw",
						"cxbz","hzxm","hh");
			}
			for(var cs=0;cs<csArray.length;cs++){
				cell.SetCellString(cs+1,2,index,csArray[cs]);
			}
			for(var i=0;i<data.length;i++){
				var obj=data[i];
				for(var j=1;j<=zdKeyArray.length&&j<=33;j++){
					if(dictMap.get(zdKeyArray[(j-1)])){
						cell.SetCellString(j,i+3,index,Gnt.ux.Dict.getDictLable(dictMap.get(zdKeyArray[(j-1)]).toUpperCase(), obj[zdKeyArray[(j-1)]]));
					}else{
						cell.SetCellString(j,i+3,index,obj[zdKeyArray[(j-1)]]);
					}
					
				}
			}
		}
		
		if(type=="sjbl"){
			cell.SetCellString(2,1,index,"数据补录");
			var sjblArray = null;
			if(zdKeyArray&&zdValueArray){
				sjblArray= zdValueArray;
			}else{
				sjblArray= new Array("公民身份号码","姓名","性别","名族","出生日期","与户主关系","户别","出生地省市县(区)","省市县(区)",
						"派出所","街路巷","责任区","乡镇(街道)","居(村)委会","户籍补录类别","户主姓名","受理人姓名","申报人姓名","申报人公民身份号码",
						"受理时间","受理单位","冲销标志");
				zdKeyArray = new Array("gmsfhm","xm","xb","mz","csrq","yhzgx","hb","csdssxq","ssxq",
						"pcs","jlx","zrq","xzjd","jcwh","hjbllb","hzxm","slrid","sbryxm","sbrgmsfhm",
						"slsj","sldw","cxbz");
			}
			for(var sjbl=0;sjbl<sjblArray.length;sjbl++){
				cell.SetCellString(sjbl+1,2,index,sjblArray[sjbl]);
			}
			for(var i=0;i<data.length;i++){
				var obj=data[i];
				for(var j=1;j<=zdKeyArray.length&&j<=33;j++){
					if(dictMap.get(zdKeyArray[(j-1)])){
						cell.SetCellString(j,i+3,index,Gnt.ux.Dict.getDictLable(dictMap.get(zdKeyArray[(j-1)]).toUpperCase(), obj[zdKeyArray[(j-1)]]));
					}else{
						cell.SetCellString(j,i+3,index,obj[zdKeyArray[(j-1)]]);
					}
					
				}
			}
		}
		
		if(type=="sjsc"){
			cell.SetCellString(2,1,index,"数据删除");
			var sjscArray = null;
			if(zdKeyArray&&zdValueArray){
				sjscArray= zdValueArray;
			}else{
				sjscArray= new Array("公民身份号码","姓名","性别","名族","出生日期","与户主关系","户别","出生地省市县(区)","省市县(区)",
						"派出所","街路巷","责任区","乡镇(街道)","居(村)委会","户籍补录类别","户主姓名","受理人姓名","申报人姓名","申报人公民身份号码",
						"受理时间","受理单位","冲销标志");
				zdKeyArray = new Array("gmsfhm","xm","xb","mz","csrq","yhzgx","hb","csdssxq","ssxq",
						"pcs","jlx","zrq","xzjd","jcwh","hjbllb","hzxm","slrid","sbryxm","sbrgmsfhm",
						"slsj","sldw","cxbz");
			}
			for(var sjsc=0;sjsc<sjscArray.length;sjsc++){
				cell.SetCellString(sjsc+1,2,index,sjscArray[sjsc]);
			}
			for(var i=0;i<data.length;i++){
				var obj=data[i];
				for(var j=1;j<=zdKeyArray.length&&j<=33;j++){
					if(dictMap.get(zdKeyArray[(j-1)])){
						cell.SetCellString(j,i+3,index,Gnt.ux.Dict.getDictLable(dictMap.get(zdKeyArray[(j-1)]).toUpperCase(), obj[zdKeyArray[(j-1)]]));
					}else{
						cell.SetCellString(j,i+3,index,obj[zdKeyArray[(j-1)]]);
					}
					
				}
			}
		}
		if(type=="qccl"){
			var sjscArray = null;
			if(zdKeyArray&&zdValueArray){
				sjscArray= zdValueArray;
			}else{
				sjscArray= new Array("姓名","性别","公民身份号码","操作类型","处理标志","迁出省市县（区）","迁出街路巷","迁出门（楼）牌号",
						"迁出门（楼）详址","迁出派出所","迁出责任区","迁出乡镇（街道）","迁出居（村）委会","迁入省市县（区）","迁入街路巷","迁入门（楼）牌号",
						"迁入门（楼）详址","迁入派出所","迁入责任区","迁入乡镇（街道）","迁入居（村）委会","存根打印日期","申报人公民身份号码","申报人姓名",
						"受理时间","受理单位","受理人姓名","出生地省县","迁出排序号","业务标志","业务类型","*民族");
				zdKeyArray = new Array("xm","xb","gmsfhm","czlx","clbz","ssxq_q","jlx_q","mlph_q",
						"mlxz_q","pcs_q","zrq_q","xzjd_q","jcwh_q","ssxq_h","jlx_h","mlph_h",
						"mlxz_h","pcs_h","zrq_h","xzjd_h","jcwh_h","cgdyrq","sbrgmsfhm","sbryxm",
						"slsj","sldw","slrid","csdssxq","pxh_q","ywbz","ywlx","mz");
			}
			for(var sjsc=0;sjsc<sjscArray.length;sjsc++){
				cell.SetCellString(sjsc+1,1,index,sjscArray[sjsc]);
			}
			for(var i=0;i<data.length;i++){
				var obj=data[i];
				for(var j=1;j<=zdKeyArray.length&&j<=33;j++){
					if(dictMap.get(zdKeyArray[(j-1)])){
						cell.SetCellString(j,i+2,index,Gnt.ux.Dict.getDictLable(dictMap.get(zdKeyArray[(j-1)]).toUpperCase(), obj[zdKeyArray[(j-1)]]));
					}else{
						cell.SetCellString(j,i+2,index,obj[zdKeyArray[(j-1)]]);
					}
					
				}
			}
		}
	
	},
	resetData:function(){
		this.fs.getForm().reset();
	},
	initComponent : function(){
		
		var fs = new Ext.form.FormPanel({
			 id:'formsb',
	         region : 'center',  
	         labelWidth : 100,  
	         frame : false,  
	         bodyStyle : 'padding:0px', 
	         border : false,  
	         layout:'column',
	         items : [{
	           border:false,
	           frame:true,
	           layout:'table',
	           buttonAlign:'left',
	           buttons:[
	                    new Ext.Button({		     
			        		text:'打印设置',
			        		minWidth:60,
			        		handler:function(){
//			        			var cell = document.getElementById("Celllist");			       
			        			cell.PrintPageSetup();
			        		}
			        	}),
			        	 new Ext.Button({		     
				        		text:'字体设置',
				        		minWidth:60,
				        		handler:function(){
//				        			var cell = document.getElementById("Celllist");
				        			cell.Login("浙江金铖华元信息技术有限公司自定义报表-WEB版","","13040351","1340-1275-0110-0004");
				        			

				        			cell.SetChartFont("隶书", 48, 0, 0);
				        		}
				        	}),
		                new Ext.Button({		     
		        		text:'打印预览',
		        		minWidth:60,
		        		handler:function(){
//		        			var cell = document.getElementById("Celllist");
		        			cell.Login("浙江金铖华元信息技术有限公司自定义报表-WEB版","","13040351","1340-1275-0110-0004");
		        			var index=cell.GetSheetIndex("第1页");
		        			cell.PrintPreview(1,index);
		        		}
		        	}),new Ext.Button({		       
		        		text:'保存',
		        		minWidth:60,
		        		handler:function(){
//		        			var cell = document.getElementById("Celllist");			     			   
		        			var isok=cell.SaveFile();
		        			
		        			if(isok==1){
		        				alert("保存成功");
		        			}
		        		}
		        	}),new Ext.Button({		 
		        		text:'打印',
		        		minWidth:60,
		        		handler:function(){
//		        			var cell = document.getElementById("Celllist");	     			   
		        			cell.PrintSheet(1,0);
		        		}
		        	}),new Ext.Button({		 
		        		text:'关闭',
		        		minWidth:60,
		        		handler:function(){
		        			var win = this.findParentByType("sbdy_window");
			            	win.hide();
		        		}
		        	})]
	         },
	         {	      
					frame:false,
					border:false,
					id:'cellCtrl',
					html:"<OBJECT id=\"Celllist\"  WIDTH=1000 HEIGHT=320    classid=\"clsid:3F166327-8030-4881-8BD2-EA25350E574A\" />"							      	 		
	         }
				]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.sbdywin.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('sbdy_window', Gnt.ux.sbdywin);
function Map() {

    this.keys = new Array();
    this.data = new Object();

    this.set = function(key, value) {
        if (this.data[key] == null) {
            if (this.keys.indexOf(key) == -1) {
                this.keys.push(key);
            }
        }
        this.data[key] = value;
    }

    this.get = function(key) {
        return this.data[key];
    }
}
