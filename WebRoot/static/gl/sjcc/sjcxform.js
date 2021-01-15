Gnt.ux.sjcxwin= Ext.extend(Ext.Window, {
	title:'',
	closeAction:'hide',
	modal:true,
	width:600,
	height:400,
	margins:'0',
	layout:'border',
	html:'',
	show:function(){
		Gnt.ux.sjcxwin.superclass.show.call(this);
	},

	setSelRes:function(data,type){
		Gnt.initCellWeb('cellCtrl','Cell_sjcx');
//		var cell = document.getElementById("Cell_sjcx");
		//cell.ResetContent();
		//cell.Login("浙江金铖华元信息技术有限公司自定义报表-WEB版","","13040351","1340-1275-0110-0004");
		var index=cell.GetSheetIndex("第1页");
		if(type=="yrwh"){//有人无户
			cell.SetColWidth(1,150,1,index);
			cell.SetColWidth(1,150,2,index);
			cell.SetColWidth(1,100,3,index);
			
			cell.SetCellString(1,1,index,"姓名");
			cell.SetCellString(2,1,index,"公民身份号码");
			cell.SetCellString(3,1,index,"性别");
			cell.SetCellString(4,1,index,"与户主关系");
			cell.SetCellString(5,1,index,"人员状态");
			cell.SetCellString(6,1,index,"户别");
			cell.SetCellString(7,1,index,"出生日期");
			cell.SetCellString(8,1,index,"集体户种类");
			cell.SetCellString(9,1,index,"民族");
			cell.SetCellString(10,1,index,"职业");
			cell.SetCellString(11,1,index,"服务处所");
			cell.SetCellString(12,1,index,"备注");
			cell.SetCellString(13,1,index,"照片id");
			cell.SetCellString(14,1,index,"原户口性质名");
			cell.InsertRow(2,data.length,index);
			for(var i=0;i<data.length;i++){
				var obj=data[i].data;
				cell.SetCellString(1,i+2,index,obj.xm);
				cell.SetCellString(2,i+2,index,obj.gmsfhm);
				cell.SetCellString(3,i+2,index,Gnt.ux.Dict.getDictLable('CS_XB', obj.xb));
				cell.SetCellString(4,i+2,index,Gnt.ux.Dict.getDictLable('CS_JTGX', obj.yhzgx));
				cell.SetCellString(5,i+2,index,Gnt.ux.Dict.getDictLable('CS_RYZT', obj.ryzt));
				cell.SetCellString(6,i+2,index,Gnt.ux.Dict.getDictLable('CS_HB', obj.hb));
				cell.SetCellString(7,i+2,index,obj.csrq);
				cell.SetCellString(8,i+2,index,Gnt.ux.Dict.getDictLable('CS_JTHZL', obj.jthzl));
				cell.SetCellString(9,i+2,index,Gnt.ux.Dict.getDictLable('CS_MZ', obj.mz));
				cell.SetCellString(10,i+2,index,Gnt.ux.Dict.getDictLable('CS_ZY', obj.zy));
				cell.SetCellString(11,i+2,index,obj.fwcs);
				cell.SetCellString(12,i+2,index,obj.bz);
				cell.SetCellString(13,i+2,index,obj.zpid);
				cell.SetCellString(14,i+2,index,obj.yhkxzmc);





			}
		}	
		
		if(type=="yhwd"){//有户无地
			cell.SetCellString(1,1,index,"户号");
			cell.SetCellString(2,1,index,"户类型");
			cell.SetCellString(3,1,index,"户号状态");
			cell.SetCellString(4,1,index,"建户类别");
			cell.SetCellString(5,1,index,"撤户类别");
			cell.SetCellString(6,1,index,"记录标志");
			cell.InsertRow(2,data.length,index);
			for(var i=0;i<data.length;i++){
				var obj=data[i].data;
				cell.SetCellString(1,i+2,index,obj.hh);
				cell.SetCellString(2,i+2,index,Gnt.ux.Dict.getDictLable('CS_HLX', obj.hlx));
				cell.SetCellString(3,i+2,index,Gnt.ux.Dict.getDictLable('CS_HHZT', obj.hhzt));
				cell.SetCellString(4,i+2,index,Gnt.ux.Dict.getDictLable('CS_HJLLB', obj.jhlb));
				cell.SetCellString(5,i+2,index,Gnt.ux.Dict.getDictLable('CS_HCXLB', obj.chlb));
				cell.SetCellString(6,i+2,index,Gnt.ux.Dict.getDictLable('CS_JLBZ', obj.jlbz));

			}
		}
		
		if(type=="yhdghz" ||type=="qszp" ||type=="czryxx" ||type=="chcx" ||type=="bczryxx"){
			cell.SetCellString(1,1,index,"姓名");
			cell.SetCellString(2,1,index,"公民身份号码");
			cell.SetCellString(3,1,index,"居委会");
			cell.SetCellString(4,1,index,"街路巷");
			cell.SetCellString(5,1,index,"门楼牌号");
			cell.SetCellString(6,1,index,"门楼详址");
			cell.SetCellString(7,1,index,"户别");
			cell.SetCellString(8,1,index,"派出所");
			cell.SetCellString(9,1,index,"与户主关系");
			cell.SetCellString(10,1,index,"性别");
			cell.SetCellString(11,1,index,"民族");
			cell.SetCellString(12,1,index,"集体户种类");
			cell.SetCellString(13,1,index,"出生日期");
			cell.SetCellString(14,1,index,"户号");
			cell.SetCellString(15,1,index,"人员状态");
			cell.SetCellString(16,1,index,"职业");
			cell.SetCellString(17,1,index,"证件类别");
			cell.SetCellString(18,1,index,"迁移(流动)原因");
			cell.SetCellString(19,1,index,"原户口性质名");
			cell.InsertRow(2,data.length,index);
			for(var i=0;i<data.length;i++){
				var obj=data[i].data;
				cell.SetCellString(1,i+2,index,obj.xm);
				cell.SetCellString(2,i+2,index,obj.gmsfhm);
				cell.SetCellString(3,i+2,index,Gnt.ux.Dict.getDictLable('JWHXXB', obj.jcwh));
				cell.SetCellString(4,i+2,index,Gnt.ux.Dict.getDictLable('JLXXXB', obj.jlx));
				cell.SetCellString(5,i+2,index,obj.mlph);
				cell.SetCellString(6,i+2,index,obj.mlxz);
				cell.SetCellString(7,i+2,index,Gnt.ux.Dict.getDictLable('CS_HB', obj.hb));
				cell.SetCellString(8,i+2,index,Gnt.ux.Dict.getDictLable('DWXXB', obj.pcs));
				cell.SetCellString(9,i+2,index,Gnt.ux.Dict.getDictLable('CS_JTGX', obj.yhzgx));
				cell.SetCellString(10,i+2,index,Gnt.ux.Dict.getDictLable('CS_XB', obj.xb));
				cell.SetCellString(11,i+2,index,Gnt.ux.Dict.getDictLable('CS_MZ', obj.mz));
				cell.SetCellString(12,i+2,index,Gnt.ux.Dict.getDictLable('CS_JTHZL', obj.jthzl));
				cell.SetCellString(13,i+2,index,obj.csrq);
				cell.SetCellString(14,i+2,index,obj.hh);
				cell.SetCellString(15,i+2,index,Gnt.ux.Dict.getDictLable('CS_RYZT', obj.ryzt));
				cell.SetCellString(16,i+2,index,Gnt.ux.Dict.getDictLable('CS_ZY', obj.zy));
				cell.SetCellString(17,i+2,index,Gnt.ux.Dict.getDictLable('CS_ZJLB', obj.zjlb));
				cell.SetCellString(18,i+2,index,Gnt.ux.Dict.getDictLable('CS_QYLDYY', obj.qyldyy));
				cell.SetCellString(19,i+2,index,obj.yhkxzmc);

			}
		}
		
		if(type=="qshz" || type=="czhxx" ||type=="bczhxx"){
			cell.SetCellString(1,1,index,"户号");
			cell.SetCellString(2,1,index,"户类型");
			cell.SetCellString(3,1,index,"派出所");
			cell.SetCellString(4,1,index,"责任区");
			cell.SetCellString(5,1,index,"乡镇街道");
			cell.SetCellString(6,1,index,"居委会");
			cell.SetCellString(7,1,index,"街路巷");
			cell.SetCellString(8,1,index,"门楼牌号");
			cell.SetCellString(9,1,index,"门楼详址");
			cell.SetCellString(10,1,index,"冲销标志");
			cell.SetCellString(11,1,index,"变动原因");
			cell.SetCellString(12,1,index,"标志地址");
			cell.SetCellString(13,1,index,"标志地址id");
			cell.InsertRow(2,data.length,index);
			for(var i=0;i<data.length;i++){
				var obj=data[i].data;
				cell.SetCellString(1,i+2,index,obj.hh);
				cell.SetCellString(2,i+2,index,Gnt.ux.Dict.getDictLable('CS_HLX', obj.hlx));
				cell.SetCellString(3,i+2,index,Gnt.ux.Dict.getDictLable('DWXXB', obj.pcs));
				cell.SetCellString(4,i+2,index,Gnt.ux.Dict.getDictLable('JWZRQXXB', obj.zrq));
				cell.SetCellString(5,i+2,index,Gnt.ux.Dict.getDictLable('XZJDXXB', obj.xzjd));
				cell.SetCellString(6,i+2,index,Gnt.ux.Dict.getDictLable('JWHXXB', obj.jcwh));
				cell.SetCellString(7,i+2,index,Gnt.ux.Dict.getDictLable('JLXXXB', obj.jlx));
				cell.SetCellString(8,i+2,index,obj.mlph);
				cell.SetCellString(9,i+2,index,obj.mlxz);
				cell.SetCellString(10,i+2,index,Gnt.ux.Dict.getDictLable('CS_CXBZ', obj.cxbz));
				cell.SetCellString(11,i+2,index,Gnt.ux.Dict.getDictLable('CS_BDYY', obj.bdyy));
				cell.SetCellString(12,i+2,index,obj.bzdz);
				cell.SetCellString(13,i+2,index,obj.bzdzid);
			}
		}
		
		if(type=="czdxx" ||type=="bczdxx"){
			cell.SetCellString(1,1,index,"省市县");
			cell.SetCellString(2,1,index,"派出所");
			cell.SetCellString(3,1,index,"警务责任区");
			cell.SetCellString(4,1,index,"乡镇(街道)");
			cell.SetCellString(5,1,index,"居村委会");
			cell.SetCellString(6,1,index,"街路巷");
			cell.SetCellString(7,1,index,"门楼牌号");
			cell.SetCellString(8,1,index,"门楼详址");
			cell.SetCellString(9,1,index,"门楼牌状态");
			cell.SetCellString(10,1,index,"建地类别");
			cell.SetCellString(11,1,index,"撤地类别");
			cell.SetCellString(12,1,index,"建地时间");
			cell.InsertRow(2,data.length,index);
			for(var i=0;i<data.length;i++){
				var obj=data[i].data;
				cell.SetCellString(1,i+2,index,Gnt.ux.Dict.getDictLable('XZQHB', obj.ssxq));
				cell.SetCellString(2,i+2,index,Gnt.ux.Dict.getDictLable('DWXXB', obj.pcs));
				cell.SetCellString(3,i+2,index,Gnt.ux.Dict.getDictLable('JWZRQXXB', obj.zrq));
				cell.SetCellString(4,i+2,index,Gnt.ux.Dict.getDictLable('XZJDXXB', obj.xzjd));
				cell.SetCellString(5,i+2,index,Gnt.ux.Dict.getDictLable('JWHXXB', obj.jcwh));
				cell.SetCellString(6,i+2,index,Gnt.ux.Dict.getDictLable('JLXXXB', obj.jlx));
				cell.SetCellString(7,i+2,index,obj.mplh);
				cell.SetCellString(8,i+2,index,obj.mlxz);
				cell.SetCellString(9,i+2,index,Gnt.ux.Dict.getDictLable('CS_MLPZT', obj.mlpzt));
				cell.SetCellString(10,i+2,index,Gnt.ux.Dict.getDictLable('CS_MLPJLLB', obj.jdlb));
				cell.SetCellString(11,i+2,index,Gnt.ux.Dict.getDictLable('CS_MLPCXLB', obj.cdlb));
				cell.SetCellString(12,i+2,index,obj.jdsj);
			}
		}
		
	
		
		
	
	
	
	},
	resetData:function(){
		this.fs.getForm().reset();
	},
	initComponent : function(){
		
		var fs = new Ext.form.FormPanel({
			 id:'formbc',
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
	           buttons:[new Ext.Button({		     
	        		text:'打印设置',
	        		minWidth:60,
	        		handler:function(){
//	        			var cell = document.getElementById("Cell_sjcx");			       
	        			cell.PrintPageSetup();
	        		}
	        	}),
	        	 new Ext.Button({		     
		        		text:'字体设置',
		        		minWidth:60,
		        		handler:function(){
//		        			var cell = document.getElementById("Cell_sjcx");
		        			cell.Login("浙江金铖华元信息技术有限公司自定义报表-WEB版","","13040351","1340-1275-0110-0004");
		        			

		        			cell.SetChartFont("隶书", 48, 0, 0);
		        		}
		        	}),
                new Ext.Button({		     
        		text:'打印预览',
        		minWidth:60,
        		handler:function(){
//        			var cell = document.getElementById("Cell_sjcx");
        			cell.Login("浙江金铖华元信息技术有限公司自定义报表-WEB版","","13040351","1340-1275-0110-0004");
        			var index=cell.GetSheetIndex("第1页");
        			cell.PrintPreview(1,index);
        		}
        	}),
	                    new Ext.Button({		       
		        		text:'保存',
		        		minWidth:60,
		        		handler:function(){
//		        			var cell = document.getElementById("Cell_sjcx");			     			   
		        			var isok=cell.SaveFile();
		        			
		        			if(isok==1){
		        				alert("保存成功");
		        			}
		        		}
		        	}),new Ext.Button({		 
		        		text:'关闭',
		        		minWidth:60,
		        		handler:function(){
		        			var win = this.findParentByType("sjcx_window");
			            	win.hide();
		        		}
		        	})]
	         },
	         {	      
					frame:false,
					border:false,
					id:'cellCtrl',
					html:"<OBJECT id=\"Cell_sjcx\"  WIDTH=605 HEIGHT=320    classid=\"clsid:3F166327-8030-4881-8BD2-EA25350E574A\" />"							      	 		
	         }
				]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.sjcxwin.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('sjcx_window', Gnt.ux.sjcxwin);