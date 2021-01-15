Gnt.ux.wsbbdywin= Ext.extend(Ext.Window, {
	title:'打印',
	closeAction:'hide',
	modal:true,
	width:600,
	height:400,
	margins:'0',
	layout:'border',
	html:'',
	show:function(){
		Gnt.ux.wsbbdywin.superclass.show.call(this);
	},

	setSelRes:function(data,type){
		Gnt.initCellWeb('cellCtrl','Celllist');
//		var cell = document.getElementById("Celllist");
		cell.ResetContent();
		//cell.Login("浙江金铖华元信息技术有限公司自定义报表-WEB版","","13040351","1340-1275-0110-0004");
		var index=cell.GetSheetIndex("第1页");
		if(type==1){
			cell.ReadFromBase64Str(data);
		}else{
		cell.SetColWidth(1,100,3,index);
		cell.SetColWidth(1,100,5,index);
		cell.SetCellString(1,1,index,"出生日期");
		cell.SetCellString(2,1,index,"顺序号");
		cell.SetCellString(3,1,index,"号码所属人姓名");
		cell.SetCellString(4,1,index,"性别");
		cell.SetCellString(5,1,index,"公民身份证号码");
		cell.SetCellString(6,1,index,"单位");
		for(var i=0;i<data.length;i++){
			var obj=data[i].data;
			cell.SetCellString(1,i+2,index,obj.csrq);
			cell.SetCellString(2,i+2,index,obj.sxh);
			cell.SetCellString(3,i+2,index,obj.xm);
			cell.SetCellString(4,i+2,index,obj.xb);
			cell.SetCellString(5,i+2,index,obj.gmsfhm);
			cell.SetCellString(6,i+2,index,obj.sldw);
		}
	}
	},
	resetData:function(){
		this.fs.getForm().reset();
	},
	initComponent : function(){
		
		var fs = new Ext.form.FormPanel({
			 id:'formws',
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
		        			var win = this.findParentByType("wsbbdy_window");
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
		
		Gnt.ux.wsbbdywin.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('wsbbdy_window', Gnt.ux.wsbbdywin);