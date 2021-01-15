var zpid=null;
var selRes =null;
var selectedSbxxid = null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10016","30038",function(){}))
		return;
	
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_EDZSLZT','CS_EDZSLYY','CS_EDZLZFS','CS_EDZZZLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	var form30002 = new Gnt.ux.SjpzForm({
		pzlb: '30002',//30037
		title:'',
		region:'north',
		cols:2,
		formType:'query'
	});	
	var form30004 = new Gnt.ux.SjpzForm({
		pzlb: '30004',
		title:'',
		labelWidth:120,
		region:'north',
		cols:2
	});		
	var zpWindow = null;
	var zjlqGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		region:'center',
		url:'yw/edzzjgl/zjlq/queryZjlqxx',
		pzlb: '30017',
		title: '',
		height:260,
		showPaging:true,
		listeners:{
			rowclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				slzt = selRes.data.slzt;
				selectedSbxxid = selRes.data.sbxxid;
				if(selRes){
					//Ext.getCmp('zjlb').setValue(Gnt.ux.Dict.getDictLable('CS_ZJLB', selRes.data.zjlb));
					var zpid = selRes.data.zpid;
					if(zpid &&　zpid != 0){
						Ext.getCmp('picImage').body.update("<IMG SRC='yw/common/img/render/" + zpid + "' width='100%' height='100%' />");
						//Ext.getCmp('picImage1').body.update("<IMG SRC='yw/common/img/render/" + zpid + "' width='100%' height='100%' />");
					}else{
						Ext.getCmp('picImage').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
						//Ext.getCmp('picImage1').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
					}
					zpWindow = new Gnt.ux.SelectZpAll({
						ryid:selRes.data.ryid
					});
				}
    			
				
    		}
		}
	});
	
	var zhcx_dialog = new Gnt.ux.ZhcxDialog({
		pzlb : '30017',
		grid:zjlqGrid,
		callback: function(where){
			var grid = this.grid;
			var store = grid.store;
			var length = 0;
			var data = {"where": where};
			store.removeAll();
			data.pzlb = grid.store.pzlb;
			data.config_key = this.config_key;
			data.log_code = "F3404";
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
	    region:'center',
	    border:false,
	    frame:false,
	    height:50,
	    margins: '0 0 0 0',
	    items:[{
	    	items:[{
	    	    title: '受理信息查询',
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
	    	    	items:[form30002]
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
	    	            	var data = form30002.getForm().getValues();
	       					if(Gnt.util.isEmpty(data)){
	       						showInfo("至少输入一个查询条件！");
	       						return;
	       					}
	       					data.log_code = "F3404";
	    					Ext.Msg.wait("正在执行查询，请稍后...");
	           				
	           				var store = zjlqGrid.store;
	           				store.removeAll();
	           				store.baseParams = data;
	           				store.load({params:{start:0, limit:20}});
	           				store.on('load',function(s,records){
	           					if(records.length>0){
	    							curIndex = 0;
	    							zjlqGrid.fireEvent("rowclick",zjlqGrid,curIndex);
	    							zjlqGrid.getSelectionModel().selectRange(curIndex,curIndex);
	           						//Ext.getCmp('card').getLayout().setActiveItem(1);
	    							Ext.getCmp('ckxx').enable();
	    							Ext.getCmp('slxx').enable();
	    							Ext.getCmp('ckxx').enable();
	    							Ext.getCmp('ydcjxx').enable();
	    							Ext.getCmp('zjgs').enable();
	    							Ext.getCmp('zjsj').enable();
	    							Ext.getCmp('lz').enable();
	           					}else{
	           					}
	           					Ext.Msg.hide();
	           				});
	    	               }
	    	          }),
	    	          {border:false,frame:false,width:20,height:5},
	    	          {
			    	    	xtype: 'DkButton',
			    	    	form:form30002,
			    	    	minWidth:80,
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
	    	  
	    	    
	    	},{
	    	    title: '受理信息显示',
	    	    collapsible: false,
	    	    region:'center',
	    	    layout:'column',
	    	    border:false,
	    	    frame:false,
	    	    height:300,
	    	    margins: '0 0 0 0',
	    	    items:[{
	    	    	columnWidth: .85,
	    	    	items:[zjlqGrid]
	    	    },{
	    		    title: '',
	    		    collapsible: false,
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
	    		    items:[{
	    				id:'picImage',
	    				title: '',
	    				//height:'100%',
	    				bodyStyle:'padding:10px 10px 10px 10px',
	    				html: '无照片',
	    				width:150,
	    				height:150,
	    				rowspan: 1,
	    				colspan:1
	    			}]
	    		}
	    	           ]
	    	},{
			    title: '填写证件领取信息',
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
		    	    	items:[form30004]
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
		    				text:'常口信息',
		    				id:'ckxx',
		    				minWidth:100,
		    				disabled:true,
		    				handler:function(){
		    					if(selRes){
		    						czr={
		    								ryid:selRes.data.ryid,
		    								hhnbid:selRes.data.hhnbid
		    						}
		    						Gnt.toRyxx(czr);
		    					}else{
		    						alert("请选择一条有效数据！");
		    						return;
		    					}
		    					
		    				}
		    		    }),{
		    		    	height:3,
		    		    	border:false,
		    		    	frame:false
		    		    },new Ext.Button({
		    				text:'受理信息',
		    				id:'slxx',
		    				minWidth:100,
		    				disabled:true,
		    				handler:function(){
		    					var url = basePath + "cx/edzjxx/edzslcx?zjlqJumpTo="+"1"+"&rynbid="+selRes.data.rynbid+"&slzt="+selRes.data.slzt;			      
		        				if(parent && parent.openWorkSpace){
		        					parent.openWorkSpace(url,  "证件受理信息查询", "edzslcx");
		        				}else{
		        					window.location.href = url;
		        				}
		    				}
		    		    }),{
		    		    	height:3,
		    		    	border:false,
		    		    	frame:false
		    		    },new Ext.Button({
		    				text:'保存',
		    				minWidth:100,
		    				id:'save',
		    				disabled:true,
		    				handler:function(){
		 	            	   var data = form30004.getForm().getValues();
		 	            	   log_code = "F3304";
								Gnt.submit(
										data, 
				   	 					"yw/edzzjgl/zjlq/saveZjlq",
				   	 					"正在加载，请稍后...", 
				   	 					function(jsonData,params){
				   	 						showInfo("保存成功!");
				   	 					zjlqGrid.store.reload();
				   	 					if(jsonData.list && jsonData.list.length>0){
				   	 						
				   	 					 }
				   	 					}
				   	 			    );
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
			    },{
	    	    	frame:false,
					border:false,
					id:'_READ_CARD_ID',
					xtype:'panel',
					html:'',
					width:10
					
				}]}]	
	    	}
	]},{
				xtype: 'panel',
				border:false,
				frame: false,
		    	region:'south',
		    	height: 50,
		    	bodyStyle: 'padding:5px',
				layout:'table',
				layoutConfig: {
		        	columns: 30
		        },
		        items:[{
					xtype: 'panel',
					border:false,
					frame: false,
			    	region:'south',
			    	height: 30,
			    	bodyStyle: 'padding:5px',
					layout:'table',
					layoutConfig: {
			        	columns: 30
			        },
			        items:[
						{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			    		},{
							border:false,
							frame: false,
			    		    items:[
			    		           new Ext.Button({
			    		        	   text:'异地采集信息',
			    		        	   id:'ydcjxx',
										minWidth:80,
										disabled:true,
										handler:function(){
											if(selRes){
												//无法建立与省库连接
											}else{
												alert("请先选中一条有效的数据!");
											}
										}
			    		           })
			    		    ]
			    		},{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			    		}, {
							border:false,
							frame: false,
			    		    items:[
			    		           new Ext.Button({
			    		        	   text:'证件挂失',
			    		        	   id:'zjgs',
										minWidth:80,
										disabled:true,
										handler:function(){
											if(selRes){
												var url = basePath + "yw/edzjgl/zjgs?zjlqJumpTo="+"1"+"&ryid="+selRes.data.ryid;			      
						        				if(parent && parent.openWorkSpace){
						        					parent.openWorkSpace(url,  "证件挂失", "zjgs");
						        				}else{
						        					window.location.href = url;
						        				}
											}else{
												alert("请先选中一条有效的数据!");
											}
										}
			    		           })
			    		    ]
			    		},{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			    		}, {
							border:false,
							frame: false,
			    		    items:[
			    		           new Ext.Button({
			    		        	   text:'证件收交',
			    		        	   id:'zjsj',
										minWidth:80,
										disabled:true,
										handler:function(){
											if(selRes){
												var url = basePath + "yw/edzzjgl/zjsj?zjlqJumpTo="+"1"+"&ryid="+selRes.data.ryid;			      
						        				if(parent && parent.openWorkSpace){
						        					parent.openWorkSpace(url,  "证件收交", "zjsj");
						        				}else{
						        					window.location.href = url;
						        				}
											}else{
												alert("请先选中一条有效的数据!");
											}
										}
			    		           })
			    		    ]
			    		},{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			    		},{
							border:false,
							frame: false,
			    		    items:[
			    		           new Ext.Button({
										text:'证件验收',
										id:'zjys',
										minWidth:80,
										handler:function(){
											if(selRes){
												var url = basePath + "yw/edzzjgl/zjys?zjlqJumpTo="+"1"+"&ryid="+selRes.data.ryid;			      
						        				if(parent && parent.openWorkSpace){
						        					parent.openWorkSpace(url,  "证件验收", "zjys");
						        				}else{
						        					window.location.href = url;
						        				}
											}else{
												alert("请先选中一条有效的数据!");
											}
										}
			    		           })
			    		    ]
			    		},{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			    		}, {
							border:false,
							frame: false,
			    		    items:[
			    		           new Ext.Button({
			    		        	   text:'领证',
			    		        	   id:'lz',
										minWidth:80,
										disabled:true,
										handler:function(){
											form30004.fieldSetValue(form30004.getForm().findField("nbslid"),selRes.data.nbslid);
											form30004.fieldSetValue(form30004.getForm().findField("lqfs"),selRes.data.lqfs);
											form30004.fieldSetValue(form30004.getForm().findField("lqrq"),Ext.util.Format.date(new Date(),"Ymd"));
											form30004.fieldSetValue(form30004.getForm().findField("lqrxm"),selRes.data.xm);
											form30004.fieldSetValue(form30004.getForm().findField("lqrsfhm"),selRes.data.gmsfhm);
											form30004.fieldSetValue(form30004.getForm().findField("zjddrq"),"");
											form30004.fieldSetValue(form30004.getForm().findField("blqrxm"),selRes.data.xm);
											form30004.fieldSetValue(form30004.getForm().findField("blqrsfhm"),selRes.data.gmsfhm);
											Ext.getCmp('save').enable();
										}
			    		           })
			    		    ]
			    		}
					]
			    }]
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