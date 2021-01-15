Gnt.ux.tjdywin= Ext.extend(Ext.Window, {
	title:'打印',
	closeAction:'hide',
	modal:true,
	width:600,
	height:400,
	margins:'0',
	layout:'border',
	html:'',
	show:function(){
		Gnt.ux.tjdywin.superclass.show.call(this);
	},

	setSelRes:function(data,type){
		
//		var cell = document.getElementById("Celllist");
		Gnt.initCellWeb('cellCtrl','Celllist');
		//cell.ResetContent();
		//cell.Login("浙江金铖华元信息技术有限公司自定义报表-WEB版","","13040351","1340-1275-0110-0004");
		var index=cell.GetSheetIndex("第1页");
		
		cell.SetColWidth(1,100,1,index);
		cell.SetColWidth(1,100,2,index);
		cell.SetColWidth(1,150,14,index);
		cell.SetColWidth(1,150,15,index);
		cell.SetColWidth(1,150,16,index);
		cell.SetCellString(1,1,index,"单位");
		cell.SetCellString(2,1,index,"未打印");
		cell.SetCellString(3,1,index,"已打印");
		cell.SetCellString(4,1,index,"作废");
		cell.SetCellString(5,1,index,"合计");
		
		for(var i=0;i<data.length;i++){
			var obj=data[i].data;
			if(type="ssxq"){
				cell.SetCellString(1,i+2,index,Gnt.ux.Dict.getDictLable('XZQHB', obj.dwxx));
			}
			 if(type="pcs"){
				
     	    	cell.SetCellString(1,i+2,index,Gnt.ux.Dict.getDictLable('DWXXB', obj.dwxx));
     	    }
     	    if(type="jcwh"){
     	    	cell.SetCellString(1,i+2,index,Gnt.ux.Dict.getDictLable('JWHXXB', obj.dwxx));

     	    }
     	    if(type="xzjd"){
     	    	cell.SetCellString(1,i+2,index,Gnt.ux.Dict.getDictLable('XZJDXXB', obj.dwxx));
     	    }
			cell.SetCellString(2,i+2,index,obj.wdy);
			cell.SetCellString(3,i+2,index,obj.ydy);
			cell.SetCellString(4,i+2,index,obj.zf);
			cell.SetCellString(5,i+2,index,obj.num);
			
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
//			        			var cell = document.getElementById("Celllist");			       
			        			cell.PrintPageSetup();
			        		}
			        	}),
			        	 new Ext.Button({		     
				        		text:'打印设置',
				        		minWidth:60,
				        		id:'dysz',
				        		handler:function(){
//				        			var cell = document.getElementById("Celllist");
				        			cell.PrintPageSetup();
				        		}
				        	}),
			        	 new Ext.Button({		     
				        		text:'字体设置',
				        		minWidth:60,
				        		id:'ztsz',
				        		handler:function(){
//				        			var cell = document.getElementById("Celllist");
				        			cell.Login("浙江金铖华元信息技术有限公司自定义报表-WEB版","","13040351","1340-1275-0110-0004");
				        			

				        			cell.SetChartFont("隶书", 48, 0, 0);
				        		}
				        	}),
		                new Ext.Button({		     
		        		text:'打印预览',
		        		minWidth:60,
		        		id:'dyyl',
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
		        			var cell = document.getElementById("Celllist");	     			   
		        			cell.PrintSheet(1,0);
		        		}
		        	}),new Ext.Button({		 
		        		text:'关闭',
		        		minWidth:60,
		        		handler:function(){
		        			var win = this.findParentByType("tjdy_window");
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
		
		Gnt.ux.tjdywin.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('tjdy_window', Gnt.ux.tjdywin);