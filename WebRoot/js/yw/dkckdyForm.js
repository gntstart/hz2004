Gnt.ux.dkckdyForm= Ext.extend(Ext.Window, {
	title:'打印',
	closeAction:'hide',
	modal:true,
	width:600,
	height:400,
	margins:'0',
	layout:'border',
	html:'',
	show:function(){
		Gnt.ux.dkckdyForm.superclass.show.call(this);
	},

	setSelRes:function(data,type){
		Gnt.initCellWeb('cellCtrl','Celllist');
//		var cell = document.getElementById("Celllist");
		cell.ResetContent();
		//cell.Login("浙江金铖华元信息技术有限公司自定义报表-WEB版","","13040351","1340-1275-0110-0004");
		var index=cell.GetSheetIndex("第1页");
		cell.SetCols(33,index);
		//cell.MergeCells(2,1,3,1);
		if(data.length+2>cell.GetRows(0)){
			cell.SetRows(data.length+2,0);
		}
		for(var c=1;c<30;c++){
			cell.SetColWidth(1,120,c,index);
		}
		
		if(type=="dkqd"){
			var qcArray=new Array("姓名","性别","出生日期","公民身份号码","住址2","住址3","czsj");
			for(var qc=0;qc<qcArray.length;qc++){
				cell.SetCellString(qc+1,1,index,qcArray[qc]);
			}
			for(var i=0;i<data.length;i++){
				var obj=data[i].data;
				cell.SetCellString(1,i+2,index,obj.xm);
				cell.SetCellString(2,i+2,index,Gnt.ux.Dict.getDictLable('CS_XB', obj.xb));
				cell.SetCellString(3,i+2,index,obj.csrq);
				cell.SetCellString(4,i+2,index,obj.gmsfhm);
				cell.SetCellString(5,i+2,index,obj.zz2);
				cell.SetCellString(6,i+2,index,obj.zz3);
				cell.SetCellString(7,i+2,index,obj.czsj);
			}
		}
	},
	resetData:function(){
		this.fs.getForm().reset();
	},
	initComponent : function(){
		
		var fs = new Ext.form.FormPanel({
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
		        			var win = this.findParentByType("dkckdy_window");
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
		
		Gnt.ux.dkckdyForm.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('dkckdy_window', Gnt.ux.dkckdyForm);