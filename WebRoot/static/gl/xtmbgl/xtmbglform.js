Gnt.ux.xtmbglwin= Ext.extend(Ext.Window, {
	title:'',
	closeAction:'hide',
	modal:true,
	width:600,
	height:400,
	margins:'0',
	layout:'border',
	html:'',
	show:function(){
		Gnt.ux.xtmbglwin.superclass.show.call(this);
	},

	setSelRes:function(data,type){
		Gnt.initCellWeb('cellCtrl','Cell_xtmbgl');
//		var cell = document.getElementById("Cell_xtmbgl");
		//cell.ResetContent();
		//cell.Login("浙江金铖华元信息技术有限公司自定义报表-WEB版","","13040351","1340-1275-0110-0004");
		var index=cell.GetSheetIndex("第1页");
		if(type=="sjzdwh"){
			cell.SetColWidth(1,150,1,index);
			cell.SetColWidth(1,150,2,index);
			cell.SetColWidth(1,100,3,index);
			
			cell.SetCellString(1,1,index,"字段名称");
			cell.SetCellString(2,1,index,"字段含义");
			cell.SetCellString(3,1,index,"字段类型");
			cell.SetCellString(4,1,index,"字段长度");
			cell.InsertRow(2,data.length,index);
			for(var i=0;i<data.length;i++){
				var obj=data[i].data;
				cell.SetCellString(1,i+2,index,obj.zdmc);
				cell.SetCellString(2,i+2,index,obj.zdhy);
				cell.SetCellString(3,i+2,index,obj.zdlx);
				cell.SetCellString(4,i+2,index,obj.zdcd);
			}
		}	
		
		if(type=="xzqhwh"){
			cell.SetCellString(1,1,index,"变动类型");
			cell.SetCellString(2,1,index,"五笔拼音");
			cell.SetCellString(3,1,index,"启用标志");
			cell.SetCellString(4,1,index,"区划名称");
			cell.InsertRow(2,data.length,index);
			for(var i=0;i<data.length;i++){
				var obj=data[i].data;
				cell.SetCellString(1,i+2,index,obj.bdlx);
				cell.SetCellString(2,i+2,index,obj.wbpy);
				cell.SetCellString(3,i+2,index,obj.qybz);
				cell.SetCellString(4,i+2,index,obj.mc);
			}
		}
		
		if(type=="dwxxwh"){
			cell.SetCellString(1,1,index,"区划名称");
			cell.SetCellString(2,1,index,"单位级别");
			cell.SetCellString(3,1,index,"单位机关代码");
			cell.SetCellString(4,1,index,"分局机构名称");
			cell.SetCellString(5,1,index,"单位名称");
			cell.SetCellString(6,1,index,"分局机构代码");
			cell.SetCellString(7,1,index,"单位代码");
			cell.SetCellString(8,1,index,"变动时间");
			cell.SetCellString(9,1,index,"备注");
			cell.SetCellString(10,1,index,"变动类型");
			cell.SetCellString(11,1,index,"五笔拼音");
			cell.SetCellString(12,1,index,"单位机构名称");
			cell.SetCellString(13,1,index,"启用标志");
			cell.SetCellString(14,1,index,"区划代码");
			cell.SetCellString(15,1,index,"中文拼音");
			cell.SetCellString(16,1,index,"启用标志名称");
			cell.InsertRow(2,data.length,index);
			for(var i=0;i<data.length;i++){
				var obj=data[i].data;
				cell.SetCellString(1,i+2,index,obj.xzqhbmc);
				cell.SetCellString(2,i+2,index,obj.csb1dm);
				cell.SetCellString(3,i+2,index,obj.dwjgdm);
				cell.SetCellString(4,i+2,index,obj.fjjgmc);
				cell.SetCellString(5,i+2,index,obj.mc);
				cell.SetCellString(6,i+2,index,obj.fjjgdm);
				cell.SetCellString(7,i+2,index,obj.dm);
				cell.SetCellString(8,i+2,index,obj.bdsj);
				cell.SetCellString(9,i+2,index,obj.bz);
				cell.SetCellString(10,i+2,index,obj.bdlx);
				cell.SetCellString(11,i+2,index,obj.wbpy);
				cell.SetCellString(12,i+2,index,obj.csb1mc);
				cell.SetCellString(13,i+2,index,obj.csb2mc=="已启用"?"1":"0");
				cell.SetCellString(14,i+2,index,obj.xzqhbdm);
				cell.SetCellString(15,i+2,index,obj.zwpy);
				cell.SetCellString(16,i+2,index,obj.csb2mc);
			}
		}
		
		if(type=="jwqxxwh"){
			cell.SetCellString(1,1,index,"单位代码");
			cell.SetCellString(2,1,index,"单位名称");
			cell.SetCellString(3,1,index,"警务责任区代码");
			cell.SetCellString(4,1,index,"警务责任区名称");
			cell.SetCellString(5,1,index,"中文拼音");
			cell.SetCellString(6,1,index,"五笔拼音");
			cell.SetCellString(7,1,index,"备注");
			cell.SetCellString(8,1,index,"启用标志");
			cell.SetCellString(9,1,index,"变动类型");
			cell.SetCellString(10,1,index,"变动时间");
			cell.InsertRow(2,data.length,index);
			for(var i=0;i<data.length;i++){
				var obj=data[i].data;
				cell.SetCellString(1,i+2,index,obj.dwdm);
				cell.SetCellString(2,i+2,index,obj.dwdc);
				cell.SetCellString(3,i+2,index,obj.dm);
				cell.SetCellString(4,i+2,index,obj.mc);
				cell.SetCellString(5,i+2,index,obj.zwpy);
				cell.SetCellString(6,i+2,index,obj.wbpy);
				cell.SetCellString(7,i+2,index,obj.bz);
				cell.SetCellString(8,i+2,index,obj.qybzdm);
				cell.SetCellString(9,i+2,index,obj.bdlx);
				cell.SetCellString(10,i+2,index,obj.bdsj);
			}
		}
		
		if(type=="xzjdwh"){
			cell.SetCellString(1,1,index,"区划名称");
			cell.SetCellString(2,1,index,"乡镇街道类型");
			cell.SetCellString(3,1,index,"新代码");
			cell.SetCellString(4,1,index,"乡镇街道名称");
			cell.SetCellString(5,1,index,"乡镇街道代码");
			cell.SetCellString(6,1,index,"变动时间");
			cell.SetCellString(7,1,index,"备注");
			cell.SetCellString(8,1,index,"乡镇街道类型名称");
			cell.SetCellString(9,1,index,"变动类型");
			cell.SetCellString(10,1,index,"五笔拼音");
			cell.SetCellString(11,1,index,"启用标志");
			cell.SetCellString(12,1,index,"区划代码");
			cell.SetCellString(13,1,index,"五笔拼音");
			cell.SetCellString(14,1,index,"启用标志名称");
			cell.InsertRow(2,data.length,index);
			for(var i=0;i<data.length;i++){
				var obj=data[i].data;
				cell.SetCellString(1,i+2,index,obj.xzqhmc);
				cell.SetCellString(2,i+2,index,obj.dzys);
				cell.SetCellString(3,i+2,index,obj.xdm);
				cell.SetCellString(4,i+2,index,obj.mc);
				cell.SetCellString(5,i+2,index,obj.dm);
				cell.SetCellString(6,i+2,index,obj.bdsj);
				cell.SetCellString(7,i+2,index,obj.bz);
				cell.SetCellString(8,i+2,index,obj.dzysmc);
				cell.SetCellString(9,i+2,index,obj.bdlx);
				cell.SetCellString(10,i+2,index,obj.wbpy);
				cell.SetCellString(11,i+2,index,obj.qybzmc=="已启用"?"1":"0");
				cell.SetCellString(12,i+2,index,obj.qhdm);
				cell.SetCellString(13,i+2,index,obj.zwpy);
				cell.SetCellString(14,i+2,index,obj.qybzmc);
			}
		}
		
		if(type=="jwhxx"){
			cell.SetCellString(1,1,index,"居委会类型");
			cell.SetCellString(2,1,index,"居委会新代码");
			cell.SetCellString(3,1,index,"乡镇街道代码");
			cell.SetCellString(4,1,index,"居委会名称");
			cell.SetCellString(5,1,index,"居委会代码");
			cell.SetCellString(6,1,index,"城乡代码名称");
			cell.SetCellString(7,1,index,"变动时间");
			cell.SetCellString(8,1,index,"备注");
			cell.SetCellString(9,1,index,"城乡代码");
			cell.SetCellString(10,1,index,"居委会类型名称");
			cell.SetCellString(11,1,index,"变动类型");
			cell.SetCellString(12,1,index,"单位代码");
			cell.SetCellString(13,1,index,"乡镇街道名称");
			cell.SetCellString(14,1,index,"五笔拼音");
			cell.SetCellString(15,1,index,"启用标识");
			cell.SetCellString(16,1,index,"统计代码");
			cell.SetCellString(17,1,index,"单位名称");
			cell.SetCellString(18,1,index,"中文拼音");
			cell.SetCellString(19,1,index,"启用标识名称");
			cell.SetCellString(20,1,index,"统计名称");
			
			cell.InsertRow(2,data.length,index);
			for(var i=0;i<data.length;i++){
				var obj=data[i].data;
				cell.SetCellString(1,i+2,index,obj.dzys);
				cell.SetCellString(2,i+2,index,obj.xdm);
				cell.SetCellString(3,i+2,index,obj.xzjddm);
				cell.SetCellString(4,i+2,index,obj.mc);
				cell.SetCellString(5,i+2,index,obj.dm);
				cell.SetCellString(6,i+2,index,obj.cxflmc);
				cell.SetCellString(7,i+2,index,obj.bdsj);
				cell.SetCellString(8,i+2,index,obj.bz);
				cell.SetCellString(9,i+2,index,obj.cxfldm);
				cell.SetCellString(10,i+2,index,obj.dzysmc);
				cell.SetCellString(11,i+2,index,obj.bdlx);
				cell.SetCellString(12,i+2,index,obj.dwdm);
				cell.SetCellString(13,i+2,index,obj.xzjdmc);
				cell.SetCellString(14,i+2,index,obj.wbpy);
				cell.SetCellString(15,i+2,index,obj.qybzmc=="已启用"?"1":"0");
				cell.SetCellString(16,i+2,index,obj.tjdm);
				cell.SetCellString(17,i+2,index,obj.dwmc);
				cell.SetCellString(18,i+2,index,obj.zwpy);
				cell.SetCellString(19,i+2,index,obj.qybzmc);
				cell.SetCellString(20,i+2,index,obj.tjmc);
			}
		}
		
		
		if(type=="jlxxxwh"){
			cell.SetCellString(1,1,index,"区划名称");
			cell.SetCellString(2,1,index,"变动类型");
			cell.SetCellString(3,1,index,"五笔拼音");
			cell.SetCellString(4,1,index,"启用标识");
			cell.SetCellString(5,1,index,"街路巷名称");
			cell.SetCellString(6,1,index,"街路巷代码");
			cell.SetCellString(7,1,index,"区划代码");
			cell.SetCellString(8,1,index,"中文拼音");
			cell.SetCellString(9,1,index,"启用标识名称");
			cell.SetCellString(10,1,index,"备注");
			cell.SetCellString(11,1,index,"变动时间");
			
			cell.InsertRow(2,data.length,index);
			for(var i=0;i<data.length;i++){
				var obj=data[i].data;
				cell.SetCellString(1,i+2,index,obj.xzqhmc);
				cell.SetCellString(2,i+2,index,obj.bdlx);
				cell.SetCellString(3,i+2,index,obj.wbpy);
				cell.SetCellString(4,i+2,index,obj.qybzmc=="已启用"?"1":"0");
				cell.SetCellString(5,i+2,index,obj.mc);
				cell.SetCellString(6,i+2,index,obj.dm);
				cell.SetCellString(7,i+2,index,obj.qhdm);
				cell.SetCellString(8,i+2,index,obj.zwpy);
				cell.SetCellString(9,i+2,index,obj.qybzmc);
				cell.SetCellString(10,i+2,index,obj.bz);
				cell.SetCellString(11,i+2,index,obj.bdsj);
				
			}
		}
		
		if(type=="jlxjwh"){
			cell.SetCellString(1,1,index,"街路巷号类型");
			cell.SetCellString(2,1,index,"居委会中文拼音");
			cell.SetCellString(3,1,index,"变动时间");
			cell.SetCellString(4,1,index,"变动类型");
			cell.SetCellString(5,1,index,"街路巷中文拼音");
			cell.SetCellString(6,1,index,"街路巷代码");
			cell.SetCellString(7,1,index,"参照ID");
			cell.SetCellString(8,1,index,"启用标识");
			cell.SetCellString(9,1,index,"居委会五笔拼音");
			cell.SetCellString(10,1,index,"居委会代码");
			cell.SetCellString(11,1,index,"街路巷号类型名称");
			cell.SetCellString(12,1,index,"街路巷五笔拼音");
			cell.SetCellString(13,1,index,"居委会名称");
			cell.SetCellString(14,1,index,"街路巷号");
			cell.SetCellString(15,1,index,"街路巷名称");
			cell.SetCellString(16,1,index,"启用标识名称");
			cell.InsertRow(2,data.length,index);
			for(var i=0;i<data.length;i++){
				var obj=data[i].data;
				cell.SetCellString(1,i+2,index,obj.jlxhlx);
				cell.SetCellString(2,i+2,index,obj.jwhzwpy);
				cell.SetCellString(3,i+2,index,obj.bdsj);
				cell.SetCellString(4,i+2,index,obj.bdlx);
				cell.SetCellString(5,i+2,index,obj.jlxzwpy);
				cell.SetCellString(6,i+2,index,obj.jlxdm);
				cell.SetCellString(7,i+2,index,obj.czid);
				cell.SetCellString(8,i+2,index,obj.qybzmc=="已启用"?"1":"0");
				cell.SetCellString(9,i+2,index,obj.jwhwbpy);
				cell.SetCellString(10,i+2,index,obj.jwhdm);
				cell.SetCellString(11,i+2,index,obj.jlxhlxmc);
				cell.SetCellString(12,i+2,index,obj.jlxwbpy);
				cell.SetCellString(13,i+2,index,obj.jwhmc);
				cell.SetCellString(14,i+2,index,obj.jlxh);
				cell.SetCellString(15,i+2,index,obj.jlxmc);
				cell.SetCellString(16,i+2,index,obj.qybzmc);
			}
		}
		if(type=="jttdwh"){
			cell.SetCellString(1,1,index,"户号");
			cell.SetCellString(2,1,index,"户类型");
			cell.SetCellString(3,1,index,"派出所");
			cell.SetCellString(4,1,index,"乡镇街道");
			cell.SetCellString(5,1,index,"居委会");
			cell.SetCellString(6,1,index,"街路巷");
			cell.SetCellString(7,1,index,"户标识");
			cell.SetCellString(8,1,index,"受理人姓名");
			cell.SetCellString(9,1,index,"受理时间");
			cell.SetCellString(10,1,index,"受理单位");
			cell.SetCellString(11,1,index,"门楼牌号");
			cell.SetCellString(12,1,index,"门口详址");
			cell.InsertRow(2,data.length,index);
			for(var i=0;i<data.length;i++){
				var obj=data[i];
				cell.SetCellString(1,i+2,index,obj.hh);
				cell.SetCellString(2,i+2,index,Gnt.ux.Dict.getDictLable('CS_HLX',obj.hlx));
				cell.SetCellString(3,i+2,index,Gnt.ux.Dict.getDictLable('DWXXB',obj.pcs));
				cell.SetCellString(4,i+2,index,Gnt.ux.Dict.getDictLable('XZJDXXB',obj.xzjd));
				cell.SetCellString(5,i+2,index,Gnt.ux.Dict.getDictLable('JWHXXB',obj.jcwh));
				cell.SetCellString(6,i+2,index,Gnt.ux.Dict.getDictLable('JLXXXB',obj.jlx));
				cell.SetCellString(7,i+2,index,Gnt.ux.Dict.getDictLable('CS_TDBS',obj.jttdbz));
				cell.SetCellString(8,i+2,index,Gnt.ux.Dict.getDictLable('YHXXB',obj.slrid));
				cell.SetCellString(9,i+2,index,Gnt.ux.Dict.getDictLable('DWXXB',obj.sldw));
				cell.SetCellString(10,i+2,index,obj.slsj);
				cell.SetCellString(11,i+2,index,obj.mlph);
				cell.SetCellString(12,i+2,index,obj.mlxz);
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
	           buttons:[
	                    new Ext.Button({		       
		        		text:'保存',
		        		minWidth:60,
		        		handler:function(){
//		        			var cell = document.getElementById("Cell_xtmbgl");			     			   
		        			var isok=cell.SaveFile();
		        			if(isok==1){
		        				alert("保存成功！");
		        				var win = this.findParentByType("xtmbgl_window");
				            	win.hide();
		        			}else{
		        				alert("保存文件失败！");
		        			}
		        		}
		        	}),new Ext.Button({		 
		        		text:'关闭',
		        		minWidth:60,
		        		handler:function(){
		        			var win = this.findParentByType("xtmbgl_window");
			            	win.hide();
		        		}
		        	})]
	         },
	         {	      
					frame:false,
					border:false,
					id:'cellCtrl',
					html:"<OBJECT id=\"Cell_xtmbgl\"  WIDTH=605 HEIGHT=320    classid=\"clsid:3F166327-8030-4881-8BD2-EA25350E574A\" />"							      	 		
	         }
				]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.xtmbglwin.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('xtmbgl_window', Gnt.ux.xtmbglwin);