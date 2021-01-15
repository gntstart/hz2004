Gnt.ux.dssjcxwin= Ext.extend(Ext.Window, {
	title:'打印',
	closeAction:'hide',
	modal:true,
	width:600,
	height:400,
	margins:'0',
	layout:'border',
	html:'',
	show:function(){
		Gnt.ux.dssjcxwin.superclass.show.call(this);
	},

	setSelRes:function(data){
		Gnt.initCellWeb('cellCtrl','Celllist');
//		var cell = document.getElementById("Celllist");
		//cell.ResetContent();
		//cell.Login("浙江金铖华元信息技术有限公司自定义报表-WEB版","","13040351","1340-1275-0110-0004");
		var index=cell.GetSheetIndex("第1页");
		
		cell.SetColWidth(1,100,1,index);
		cell.SetColWidth(1,100,2,index);
		cell.SetColWidth(1,150,14,index);
		cell.SetColWidth(1,150,15,index);
		cell.SetColWidth(1,150,16,index);
		cell.SetCellString(1,1,index,"居委会");
		cell.SetCellString(2,1,index,"统计年月");
		cell.SetCellString(3,1,index,"户数");
		cell.SetCellString(4,1,index,"人数(男)");
		cell.SetCellString(5,1,index,"人数(女)");
		cell.SetCellString(6,1,index,"非农业人口数");
		cell.SetCellString(7,1,index,"未落常住人数");
		cell.SetCellString(8,1,index,"18岁以下人数");
		cell.SetCellString(9,1,index,"18到35岁人数");
		cell.SetCellString(10,1,index,"35到60岁人数");
		cell.SetCellString(11,1,index,"60岁以上人数");
		cell.SetCellString(12,1,index,"年龄大于100人数(男)");
		cell.SetCellString(13,1,index,"年龄大于100人数(女)");
		cell.SetCellString(14,1,index,"操作时间");
		cell.SetCellString(15,1,index,"操作员id");
		cell.SetCellString(16,1,index,"操作员ip");
		for(var i=0;i<data.length;i++){
			var obj=data[i].data;
			cell.SetCellString(1,i+2,index,obj.jcwh);
			cell.SetCellString(2,i+2,index,obj.tjny);
			cell.SetCellString(3,i+2,index,obj.hs);
			cell.SetCellString(4,i+2,index,obj.rs_nan);
			cell.SetCellString(5,i+2,index,obj.rs_nv);
			cell.SetCellString(6,i+2,index,obj.fnyrks);
			cell.SetCellString(7,i+2,index,obj.wlczrs);
			cell.SetCellString(8,i+2,index,obj.ysbsyxrs);
			cell.SetCellString(9,i+2,index,obj.ysbdsswsrs);
			cell.SetCellString(10,i+2,index,obj.sswdlssrs);
			cell.SetCellString(11,i+2,index,obj.lssysrs);
			cell.SetCellString(12,i+2,index,obj.nldyybrs_nan);
			cell.SetCellString(13,i+2,index,obj.nldyybrs_nv);
			cell.SetCellString(14,i+2,index,obj.czsj);
			cell.SetCellString(15,i+2,index,obj.czyid);
			cell.SetCellString(16,i+2,index,obj.czyip);
		}
	
	},
	resetData:function(){
		this.fs.getForm().reset();
	},
	initComponent : function(){
		
		var fs = new Ext.form.FormPanel({
			 id:'form',
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
			        			var cell = document.getElementById("Celllist");			       
			        			cell.PrintPageSetup();
			        		}
			        	}),
			        	 new Ext.Button({		     
				        		text:'字体设置',
				        		minWidth:60,
				        		handler:function(){
				        			var cell = document.getElementById("Celllist");
				        			cell.Login("浙江金铖华元信息技术有限公司自定义报表-WEB版","","13040351","1340-1275-0110-0004");
				        			

				        			cell.SetChartFont("隶书", 48, 0, 0);
				        		}
				        	}),
		                new Ext.Button({		     
		        		text:'打印预览',
		        		minWidth:60,
		        		handler:function(){
		        			var cell = document.getElementById("Celllist");
		        			cell.Login("浙江金铖华元信息技术有限公司自定义报表-WEB版","","13040351","1340-1275-0110-0004");
		        			var index=cell.GetSheetIndex("第1页");
		        			cell.PrintPreview(1,index);
		        		}
		        	}),new Ext.Button({		       
		        		text:'保存',
		        		minWidth:60,
		        		handler:function(){
		        			var cell = document.getElementById("Celllist");			     			   
		        			var isok=cell.SaveFile();
		        			if(isok==1){
		        				alert("保存成功");
		        			}
		        		}
		        	}),new Ext.Button({		 
		        		text:'打印',
		        		minWidth:60,
		        		handler:function(){
		        			var cell = document.getElementById("Celllist");	     			   
		        			cell.PrintSheet(1,0);
		        		}
		        	}),new Ext.Button({		 
		        		text:'关闭',
		        		minWidth:60,
		        		handler:function(){
		        			var win = this.findParentByType("dssjdy_window");
			            	win.hide();
		        		}
		        	})]
	         },
	         {	      
					frame:false,
					border:false,
					id:'cellCtrl',
					html:"<OBJECT id=\"Celllist\"  WIDTH=605 HEIGHT=320    classid=\"clsid:3F166327-8030-4881-8BD2-EA25350E574A\" />"							      	 		
	         }
				]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.dssjcxwin.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('dssjdy_window', Gnt.ux.dssjcxwin);