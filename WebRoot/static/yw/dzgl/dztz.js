
var curIndex = -1;
var ggCount = 0;
var queryFlag = false;
var rynbid = null;

var selectRynbid = null;
var selectHhnbid = null;

Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10009",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	
	var cxForm = new Gnt.ux.SjpzForm({
		pzlb: '20002',
		title:'',
//		region:'center',
//		height:180,
		cols:2,
		formType:'query',
		labelWidth :  140
	});
	
	var ywGrid = new Gnt.ux.SjpzGrid({
		pkname: 'mlpnbid',
		pzlb: '20002',
		title: '查询结果',
		region:'center',
//		height: 300,
		url: 'cx/hjjbxx/mlpxxcx/queryMlpxxcx.json',
		listeners:{
			rowclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				
				ggForm.getForm().loadRecord(selRes);
				
//				selectRynbid = selRes.data.qhrynbid;
				
			}
		}
	});
	
	var ggForm = new Gnt.ux.SjpzForm({
		pzlb: '20002',
		title:'',
		region:'center',
//		height:180,
		cols:2,
		formType:'query',
		labelWidth :  120
	});
	
	var dzggWin = new Gnt.ux.DzggDialog({
		callback: function(data){
			var subdata = {
				voQhtzxx:data
			};
			
			subdata.voQhtzxx = Ext.encode(subdata.voQhtzxx);
			log_code = "F2101";
			//提交数据
			Gnt.submit(
				subdata, 
				"yw/dzgl/qhtz/processQhtzyw", 
				"正在保存区划调整信息，请稍后...", 
				function(jsonData,params){
					showInfo("区划调整成功！");
				}
			);
		}
	});
	var zhcx_dialog = new Gnt.ux.ZhcxDialog({
		pzlb : cxForm.pzlb,
		grid:ywGrid,
		callback: function(where){
			var grid = this.grid;
			var store = grid.store;
			var length = 0;
			var data = {"where": where};
			store.removeAll();
			data.pzlb = grid.store.pzlb;
			data.config_key = this.config_key;
			data.log_code = "F2008";
			store.baseParams = data;
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
	var p1 = new Ext.Panel({
		layout:'border',
		items:[{
			id:"northId",
			region:'north',
			layout:'border',
			title:'查询条件<span class="text" style="text-align:center;display:inline-block;width:95%;">(无条件输入时查本辖区内所有信息)</span>',
//			width:'100%',
			height:150,
			items:[cxForm,
				{
					id:"northEastId",
					region:'east',
					width:'100',
					html:'',
					bodyStyle:'padding:10px 5px 10px 5px',
					layout:'table',
					align:'center',
					border:false,
					frame:false,
					buttonAlign: 'center',
					layoutConfig: {
						columns: 1
					},
					items:[
						new Ext.Button({
							id:'queryId',
			    			text:'查询',
			    			minWidth:80,
			    			handler:function(){
		        				var data = cxForm.getForm().getValues();
								data.log_code = "F2008";

								Ext.Msg.wait("正在执行查询，请稍后...");
		        				
		        				var store = ywGrid.store;
		        				
		        				store.removeAll();
		        				
		        				store.baseParams = data;
		        				
		        				Ext.apply(store.baseParams, {"sffw":"1","dztz":"1"});
		        				store.load({params:{start:0, limit:20}});
		        				
		        				store.on('load',function(s,records){
		        					if(records.length>0){
		        						queryFlag = true;
		        						
		        						ywGrid.fireEvent("rowclick",ywGrid,0);
		        						ywGrid.getSelectionModel().selectRange(0,0);
		        						
		        					}
		        				});
		        				
		        				Ext.getCmp('ckId').setDisabled(false);
		        				Ext.getCmp('hdId').setDisabled(false);
		        				
		        				Ext.Msg.hide();
		        				
		        			}
						})
						,{
							height:10,
							border:false,
							frame:false
						}
						,new Ext.Button({
							id:'zhId',
			    			text:'组合查询',
			    			minWidth:80,
			    			//disabled:true,
			    			handler:function(){
			    				zhcx_dialog.show();
			    			}
						})
					]
				}
			]
			
		}
			,ywGrid
			,{
				id:"eastId",
				region:'east',
				width:'90',
				html:'',
				bodyStyle:'padding:10px 5px 10px 5px',
				layout:'table',
				align:'center',
				border:false,
				frame:false,
				buttonAlign: 'center',
				layoutConfig: {
					columns: 1
				},
				items:[
					new Ext.Button({
						id:'ckId',
		    			text:'常口信息',
		    			minWidth:80,
		    			disabled:true,
		    			handler:function(){
		    				
		    				var record = ywGrid.getSelectionModel().getSelected();
		    				
		    				var czr={
		    					mlpnbid:record.data.mlpnbid,
								type:"dztz"
							}
		    				
		    				Gnt.toRyxx(czr);
		    				
		    			}
					})
					,{
						height:10,
						border:false,
						frame:false
					}
					,new Ext.Button({
						id:'hdId',
		    			text:'户地信息',
		    			minWidth:80,
		    			disabled:true,
		    			handler:function(){
		    				
		    				var record = ywGrid.getSelectionModel().getSelected();
		    				
		    				var tabs = parent.parent.Ext.getCmp("tabs");
		    				var param = "&mlpnbid=" + record.data.mlpnbid + "&";
		    				var p = null;
		    			   	 //利用iframe显示页面
		    			   	 p = new parent.parent.Ext.ux.IFrameComponent({
		    			   	 	xtype:'panel',
		    			   	 	id:"hxxcx",
		    			   	 	url:"cx/hjjbxx/hxxcx?ai=2&jumpToHxxcx=1" + param,
		    			   	 	frame:false,
		    			   	 	iconCls:'otherfile',
		    				   	title: "户信息查询"
		    				});
		    			   	//添加分页，并且设置为活动分页
		    			    tabs.add(p);
		    			    
		    			    tabs.setActiveTab("ckxxcx");
		    				
		    			}
					})
					,{
						height:10,
						border:false,
						frame:false
					}
					,new Ext.Button({
						id: 'dtggId',
						text:'单条修改',
						minWidth:80,
						handler:function(){
							
							if(ywGrid.getSelectionModel().getSelected()){
								dzggWin.show(ywGrid.getSelectionModel().getSelected());
							}else{
								showInfo("请选择需要修改的地址！");
        						return;
							}
						}
					})
				]
			},{
				id:"southId",
				region:'south',
				height:215,
				layout:'border',
				width:'100%',
				title:'全部更改选项',
				items:[
					ggForm,
					{
						layout:'column',
						defaults:{
			            	columnWidth: .2,
			            	fieldLabel: ''
			            },
						region:'south',
						border:false,
						frame:false,
						bodyStyle:'padding:5px 5px 5px 15px',
						height:70,
						items:[{
			            	id:'c11',
			            	xtype:'checkbox',
			            	boxLabel: '居委会',
			            	name:'ggjwh',
			            	inputValue : "1",  
	        				listeners:{
	        					'check': function(obj,checked){
	        						if(checked){
	        							ggCount ++ ;
	        							
	        							if(ggCount > 0 && queryFlag){
	        								Ext.getCmp('qbggId').setDisabled(false);
	        							}
	        						}else{
	        							ggCount --;
	        							
	        							if(ggCount == 0){
	        								Ext.getCmp('qbggId').setDisabled(true);
	        							}
	        						}
	        					}
	        				}
			            },{
			            	id:'c12',
			            	xtype:'checkbox',
			            	boxLabel: '警务责任区',
			            	name:'ggzrq',
			            	inputValue : "2",  
	        				listeners:{
	        					'check': function(obj,checked){
	        						if(checked){
	        							ggCount ++ ;
	        							
	        							if(ggCount > 0 && queryFlag){
	        								Ext.getCmp('qbggId').setDisabled(false);
	        							}
	        						}else{
	        							ggCount --;
	        							
	        							if(ggCount == 0){
	        								Ext.getCmp('qbggId').setDisabled(true);
	        							}
	        						}
	        					}
	        				}
			            },{
			            	id:'c13',
			            	xtype:'checkbox',
			            	boxLabel: '街路巷',
			            	name:'ggjlx',
			            	inputValue : "3",  
	        				listeners:{
	        					'check': function(obj,checked){
	        						if(checked){
	        							ggCount ++ ;
	        							
	        							if(ggCount > 0 && queryFlag){
	        								Ext.getCmp('qbggId').setDisabled(false);
	        							}
	        						}else{
	        							ggCount --;
	        							
	        							if(ggCount == 0){
	        								Ext.getCmp('qbggId').setDisabled(true);
	        							}
	        						}
	        					}
	        				}
			            },{
			            	id:'c14',
			            	xtype:'checkbox',
			            	boxLabel: '门楼牌号',
			            	name:'ggmlph',
			            	inputValue : "4",  
	        				listeners:{
	        					'check': function(obj,checked){
	        						if(checked){
	        							ggCount ++ ;
	        							
	        							if(ggCount > 0 && queryFlag){
	        								Ext.getCmp('qbggId').setDisabled(false);
	        							}
	        						}else{
	        							ggCount --;
	        							
	        							if(ggCount == 0){
	        								Ext.getCmp('qbggId').setDisabled(true);
	        							}
	        						}
	        					}
	        				}
			            },{
			            	id:'c15',
			            	xtype:'checkbox',
			            	boxLabel: '门楼详址',
			            	name:'ggmlxz',
			            	inputValue : "5",  
	        				listeners:{
	        					'check': function(obj,checked){
	        						if(checked){
	        							ggCount ++ ;
	        							
	        							if(ggCount > 0 && queryFlag){
	        								Ext.getCmp('qbggId').setDisabled(false);
	        							}
	        						}else{
	        							ggCount --;
	        							
	        							if(ggCount == 0){
	        								Ext.getCmp('qbggId').setDisabled(true);
	        							}
	        						}
	        					}
	        				}
			            },{
							title: '',
							bodyStyle:'padding:5px 0px 0px 0px',
							html: '<font color="red">提示：‘全部更改’可以把所有地址改为调整信息中设定的地址<br />提示：门楼牌号和门楼详址中可以使用地址组合，例如：[[JLX]][[MLPH]]1[[MLXZ]]代表街巷+门牌号+1+详址</font>',
							columnWidth: 1,
							border:false,
							frame:false
						}]
					}
					,{
						id:"southEastId",
						region:'east',
						width:'90',
						html:'',
						bodyStyle:'padding:10px 5px 10px 5px',
						layout:'table',
						align:'center',
						border:false,
						frame:false,
						buttonAlign: 'center',
						layoutConfig: {
							columns: 1
						},
						items:[
							new Ext.Button({
								id:'qbggId',
				    			text:'全部更改',
				    			minWidth:80,
				    			disabled:true,
				    			handler:function(){
				    				var querydata = cxForm.getForm().getValues();
				    				querydata.sffw = "1";
				    				querydata.dztz = "1";
				    				var bgdata = ggForm.getForm().getValues();

				    				var subdata = {
				    						querydata: querydata,
				    						bgdata: bgdata,
				    						c11:Ext.getCmp("c11").getValue()?1:0,
				    						c12:Ext.getCmp("c12").getValue()?1:0,
				    						c13:Ext.getCmp("c13").getValue()?1:0,
				    						c14:Ext.getCmp("c14").getValue()?1:0,
				    						c15:Ext.getCmp("c15").getValue()?1:0
				    				};
				    				for(o in subdata){
				    					if(subdata[o]){
				    						subdata[o] = Ext.encode(subdata[o]);
				    					}
				    				}
									log_code = "F2101";
				    				Gnt.submit(
				    						subdata, 
				    						"yw/dzgl/qhtz/processQhtzywQbgg", 
				    						"正在处理全部更改业务，请稍后...", 
				    						function(jsonData,params){
				    							showInfo("全部更改成功！",function(){
				    								ywGrid.store.reload(); 
				    							});
				    						}
				    					);
				    			}
							})
							,{
								height:10,
								border:false,
								frame:false
							}
							,new Ext.Button({
								id:'gbId',
				    			text:'关闭',
				    			minWidth:80,
				    			handler:function(){
				    				window.parent.parent.closeWorkSpace();
				    			}
							})
						]
					}
				]
			}
		]
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
        	items:[p1]
        }
    });
	
	SetReadOnly(ggForm, 'xzjd', true);
	SetReadOnly(ggForm, 'pcs', true);
	
//	var b = ywGrid.getColumnModel();
//	var a = ywGrid.getColumnModel().config[0].header;
	
	
//	ywGrid.getColumnModel().config.remove(8);
//	ywGrid.getColumnModel().config.remove(9);
//	ywGrid.getColumnModel().config.remove(10);
//    ywGrid.reconfigure(ywGrid.store,ywGrid.colModel.config);
	
	ywGrid.colModel.setHidden(8,true);
	ywGrid.colModel.setHidden(9,true);
	ywGrid.colModel.setHidden(10,true);
	ywGrid.colModel.setHidden(11,true);
	
	
    
	function applyParam(store){
		
		/**
			查询类别
		 */
		var lb = '';
		if(Ext.getCmp('r11').getValue()){
			lb = '1';
		}else if(Ext.getCmp('r12').getValue()){
			lb = '2';
		}else if(Ext.getCmp('r13').getValue()){
			lb = '3';
		}
		
		var ycl = '';
		if(Ext.getCmp('c11').getValue()){
			ycl = '1';
		}
		
		var wcl = '';
		if(Ext.getCmp('c21').getValue()){
			wcl = '1';
		}
		
		var clbz = '';
		if(ycl || wcl){
			clbz = '1';
		}
		
		Ext.apply(store.baseParams, {czlx:lb,clbz:clbz,ycl:ycl,wcl:wcl});
		
	}
	
	new Ext.KeyMap(document, {
		key:Ext.EventObject.ENTER,
		fn:function() {
			Ext.getCmp('queryId').handler();
		},
		scope: this  
	});
	
	
	
});