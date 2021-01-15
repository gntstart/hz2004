Gnt.ux.xtlogwin= Ext.extend(Ext.Window, {
	title:'打印',
	closeAction:'hide',
	modal:true,
	width:600,
	height:400,
	margins:'0',
	layout:'border',
	html:'',
	show:function(){
		Gnt.ux.xtlogwin.superclass.show.call(this);
	},

	setSelRes:function(data,type,zdKeyArray,zdValueArray) {
		var dictMap = new Map();
		dictMap.set('yhzgx', 'CS_JTGX');
		Gnt.initCellWeb('cellCtrl','Celllist');
//		var cell = document.getElementById("Celllist");
		cell.ResetContent();
		//cell.Login("浙江金铖华元信息技术有限公司自定义报表-WEB版","","13040351","1340-1275-0110-0004");
		var index = cell.GetSheetIndex("第1页");
		cell.SetCols(33, index);
		cell.MergeCells(2, 1, 3, 1);
		for (var c = 1; c < 30; c++) {
			cell.SetColWidth(1, 150, c, index);
		}
		if (type == "lg") {
			cell.SetCellString(2, 1, index, "操作log日志");
			var swArray = null;
			if (zdKeyArray && zdValueArray) {
				swArray = zdValueArray;
			} else {
				swArray = new Array("操作开始时间", "操作结束时间", "日志内容", "业务标志", "单位名称", "日志对象名称", "操作员姓名");
				zdKeyArray = new Array("czkssj", "czjssj", "rznr", "ywbz", "organization", "resource_name", "czyxm");
			}
			for (var sw = 0; sw < swArray.length; sw++) {
				cell.SetCellString(sw + 1, 2, index, swArray[sw]);
			}
			for (var i = 0; i < data.length; i++) {
				var obj = data[i];
				for (var j = 1; j <= zdKeyArray.length && j <= 33; j++) {
					if (dictMap.get(zdKeyArray[(j - 1)])) {
						cell.SetCellString(j, i + 3, index, Gnt.ux.Dict.getDictLable(dictMap.get(zdKeyArray[(j - 1)]), obj[zdKeyArray[(j - 1)]]));
					} else {
						cell.SetCellString(j, i + 3, index, obj[zdKeyArray[(j - 1)]]);
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
					html:"<OBJECT id=\"Celllist\"  WIDTH=605 HEIGHT=320    classid=\"clsid:3F166327-8030-4881-8BD2-EA25350E574A\" />"							      	 		
	         }
				]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.xtlogwin.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('log_window', Gnt.ux.xtlogwin);
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
