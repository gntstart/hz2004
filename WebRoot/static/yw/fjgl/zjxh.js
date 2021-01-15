var zpid=null;
var selRes =null;
var selectedSbxxid = null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10016","30038",function(){}))
		return;
	
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_EDZSLZT','CS_EDZSLYY','CS_EDZLZFS','CS_EDZZZLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	var form30005 = new Gnt.ux.SjpzForm({
		pzlb: '30005',
		title:'',
		labelWidth:160,
		region:'north',
		cols:2,
		formType:'query'
	});	
	var form30009 = new Gnt.ux.SjpzForm({
		pzlb: '30009',
		title:'',
		labelWidth:120,
		region:'north',
		cols:2,
		fieldChange:function(field){
			var f = field.findParentByType("sjpz_form");
        	if(field.name=="xhrq"){
        		if(f.getForm().findField("xhrq").getValue()){
        			Ext.getCmp('save').enable();
        		}
        	}
		}
	});		
	var zpWindow = null;
	var zjxhGrid = new Gnt.ux.SjpzGrid({
		pkname: 'nbsfzid',
		region:'center',
		url:'yw/fjgl/zjxh/queryZjxhxx',
		pzlb: '30001',
		title: '',
		height:320,
		showPaging:true,
		listeners:{
			rowclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				slzt = selRes.data.slzt;
				selectedSbxxid = selRes.data.sbxxid;
    		}
		}
	});
	
	var zhcx_dialog = new Gnt.ux.ZhcxDialog({
		pzlb : '30023',
		grid:zjxhGrid,
		callback: function(where){
			var grid = this.grid;
			var store = grid.store;
			var length = 0;
			var data = {"where": where};
			store.removeAll();
			data.pzlb = grid.store.pzlb;
			data.config_key = this.config_key;
			store.baseParams = data;
			if(this.applyParam){
				this.applyParam();
			}
			Ext.Msg.wait("正在执行查询，请稍后...");
			store.load({params:{start:0, limit:grid.store.pageSize}});
			store.on("load",function(store) {  
				length = store.data.length
				if(length > 0){
					curIndex = 0;
					grid.fireEvent("rowclick",grid,curIndex);
					grid.getSelectionModel().selectRange(curIndex,curIndex);
				}else{
					showInfo("没有找到符合条件的数据！");
				}
				Ext.Msg.hide();
			},grid); 			        						
		}
	});
	
var p2=new Ext.Panel({
	layout:'border',
	items: [{
	    title: '',
	    collapsible: false,
	    region:'north',
	    border:false,
	    frame:false,
	    height:120,
	    margins: '0 0 0 0',
	    items:[{
    	    title: '证件查询',
    	    collapsible: false,
    	    region:'north',
    	    border:false,
    	    frame:false,
    	    layout:'column',
    	    height: 120,
    	    //margins: '5 0 0 0',
    	    //cmargins: '5 0 0 0',
    	    items:[{
    	    	border:false,
    	    	frame:false,
    	    	columnWidth: .9,
    	    	region:'center',
    	    	items:[form30005]
    	    },{
    	    	border:false,
    	    	frame:false,
    	    	region:'east',
    	    	layout:'form',
    		    labelAlign :'right',
    		    columnWidth: .1,
    	    	items:[{border:false,frame:false,width:20,height:5},
    		        new Ext.Button({
    	                text:'查询',
    	                minWidth:80,
    	               handler:function(){
    	            	   
    	            	   var data = form30005.getForm().getValues();
    	            	   Ext.Msg.wait("正在执行查询，请稍后...");
           					var store = zjxhGrid.store;
           					store.removeAll();
           					store.baseParams = data;
           					store.load({params:{start:0, limit:20}});
           					store.on('load',function(s,records){
	           					if(records.length>0){
	    							curIndex = 0;
	    							zjxhGrid.fireEvent("rowclick",zjxhGrid,curIndex);
	    							zjxhGrid.getSelectionModel().selectRange(curIndex,curIndex);
	           						//Ext.getCmp('card').getLayout().setActiveItem(1);
	    							Ext.getCmp('ckxx').enable();
	    							Ext.getCmp('xhcz').enable();
	           					}else{
	           					}
           					Ext.Msg.hide();
           				});
    	               }
    	          }),
    	          {border:false,frame:false,width:20,height:5},
    	          {
		    	    	xtype: 'DkButton',
		    	    	form:form30005,
		    	    	callback: function(){
		    	    		//Ext.getCmp("queryId").handler();
		    	    	}
		    	  },
    	          {border:false,frame:false,width:20,height:5},
    		        new Ext.Button({
    	                text:'组合查询',
    	                minWidth:80,
    	               handler:function(){
    	            	   zhcx_dialog.show();
    	               }
    	          })]
    	    }]
    	}
	]},{

	    title: '',
	    collapsible: false,
	    region:'center',
	    border:false,
	    frame:false,
//	    height:50,
	    margins: '0 0 0 0',
	    items:[{
    	    title: '证件信息',
    	    collapsible: false,
    	    region:'center',
    	    layout:'column',
    	    border:false,
    	    frame:false,
    	    margins: '0 0 0 0',
    	    items:[{
    	    	columnWidth: .9,
    	    	items:[zjxhGrid]
    	    },{
    		    title: '',
    		    collapsible: false,
    		    region:'east',
    		    border:false,
    		    frame:false,
    		    columnWidth: .1,
    		    margins: '0 0 0 0',
    		    bodyStyle:'padding:10px 10px 10px 10px',
    		    layout:'table',
    		    layoutConfig: {
    	    		columns: 1
    	    	 },
    		    labelWidth:40,
    		    items:[{
    		    	height:260,
    		    	border:false,
    		    	frame:false
    		    },new Ext.Button({
    				text:'常口信息',
    				id:'ckxx',
    				minWidth:100,
    				disabled:true,
    				handler:function(){
       					if(selRes){
       						czr={
    								ryid:selRes.data.ryid,
    								rynbid:selRes.data.rynbid,
    								hhnbid:selRes.data.hhnbid
    						}
    						Gnt.toRyxx(czr); 
       					}
    				}
    		    }),{
    		    	height:3,
    		    	border:false,
    		    	frame:false
    		    },new Ext.Button({
    				text:'销毁此证',
    				id:'xhcz',
    				disabled:true,
    				minWidth:100,
    				handler:function(){
    					if(selRes){
    						form30009.getForm().findField("xhrq").focus();
//    	        			form30009.fieldSetValue(form30009.getForm().findField("xhrq"),selRes.data.xhrq);
//    	        			form30009.fieldSetValue(form30009.getForm().findField("xhdw"),selRes.data.xhdw);
//    	        			form30009.fieldSetValue(form30009.getForm().findField("xhr"),selRes.data.xhr);
    					}
    				}
    		    })]
    		}
    	           ]
    	}]
	},{
		title: '',
	    collapsible: false,
	    region:'south',
	    border:false,
	    frame:false,
	    height:100,
	    margins: '0 0 0 0',
	    items:[{
			border:false,
			frame: false,
	    	region:'south',
	    	title: '验收信息',
	    	bodyStyle: 'padding:5px',
			
			layoutConfig: {
	        	columns: 30
	        },
	        items:[{
			    
		    	border:false,
		 	    frame:false,
			    cmargins: '0 0 0 0',
			    items:[{
					xtype: 'panel',
					border:false,
					frame: false,
			    	region:'south',
			    	layout:'column',
			        items:[{
			        	columnWidth: .85,
			        	border:false,
						frame: false,
		    	    	items:[form30009]
			        },{
		    		    title: '',
		    		    columnWidth: .15,
		    		    region:'east',
		    		    border:false,
		    		    frame:false,
		    		    columnWidth: .15,
		    		    margins: '0 0 0 0',
		    		    bodyStyle:'padding:10px 10px 10px 10px',
		    		    layout:'table',
		    		    layoutConfig: {
		    	    		columns: 1
		    	    	 },
		    		    labelWidth:40,
		    		    items:[new Ext.Button({
		    				text:'保存',
		    				minWidth:100,
		    				id:'save',
		    				disabled:true,
		    				handler:function(){
		 	            	   var data = form30009.getForm().getValues();
		 	            	  data.nbsfzid = selRes.data.nbsfzid;
		 	            	   if(data.xhrq){
		 	            		  Gnt.submit(
											data, 
					   	 					"yw/fjgl/zjxh/saveZjxhxx",
					   	 					"正在加载，请稍后...", 
					   	 					function(jsonData,params){
					   	 						showInfo("保存成功!");
					   	 					zjxhGrid.store.reload();
					   	 					if(jsonData.list && jsonData.list.length>0){
					   	 						
					   	 					 }
					   	 					}
					   	 		 );
		 	            	   }else{
		 	            		   alert("销毁日期为必填！");
		 	            		   return;
		 	            	   }
			               }
		    		    }),{
		    		    	height:3,
		    		    	border:false,
		    		    	frame:false
		    		    },new Ext.Button({
		    				text:'关闭',
		    				minWidth:100,
		    				handler:function(){
		    					window.parent.closeWorkSpace();
		    				}
		    		    })]
		    		}]
			    }]},{
			    	frame:false,
					border:false,
					id:'_READ_CARD_ID',
					xtype:'panel',
					html:'',
					width:10
				}]}]
				
		    }]
});
	 var v = new Ext.Viewport({
	        layout:'fit',
	        id:'vp',
	        border:false,
	        items: {
	        	layout:'card',
	        	border:false,
	        	id:'card',
	        	frame:false,
	        	activeItem: 0,
	        	xtype: 'panel',
	        	items:[p2]
	        }
	    });
	
});