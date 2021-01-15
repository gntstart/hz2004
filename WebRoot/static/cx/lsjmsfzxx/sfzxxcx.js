
var cxCount = 1;
var rynbid = null;

var htmlStrStart = '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="cx/lzcx/querySfzxxcx';
var htmlStrEnd = '"></iframe>';
//'?rynbid='+ getRynbid(rynbid) + "&goto=queryHdxx&hhnbid=" + getQueryParam("hhnbid") + '"> </iframe>';

var selectPzrzid = null;
var selectHhnbid = null;
 var selectLsslid =null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("30032,20020",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	
	/**
		点击下一步后显示的内容
		暂时用户成员信息代替
	 */
	var hcyGrid = new Gnt.ux.SjpzGrid({
		pkname: 'lsslid',
		pzlb: '30032',
    	region:'south',
		height:150,
    	title: '',
		url: 'cx/lzcx/querySfzxxcx',
    	showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){}
		}
	});
	
	var form30032 = new Gnt.ux.SjpzForm({
		pzlb: '30032',
		url: 'cx/lzcx/querySfzxxcx',
		title:'',
		cols:2,
		formType:'query'
	});
//	var hxxGrid = new Gnt.ux.SjpzGrid({
//		pkname: 'lsslid',
//		pzlb: '30032',
//		title: '',
//		url: 'cx/lzcx/querySfzxxcx',
//		showPaging:false
//	});

	
	
	var upshzGrid = new Gnt.ux.SjpzGrid({
		pkname: 'lsslid',
		pzlb: '30032',
		title: '',
		url: 'cx/lzcx/updateSfzxxcx',
		showPaging:false
	});
	
	var form20020 = new Gnt.ux.SjpzForm({
		pkname: 'hhnbid',
		closable: false,
		/*region:'north',
		height:500,*/
		pzlb: '20001',
		labelWidth : 120,
		cols:2,
//		bindStore:xblryGrid.store,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:hxxGrid,
		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
		changeDictCust:function(cmb,res){
			
			return;
		},
		title: ''
	});
	
	var NewForm30032 = new Gnt.ux.SjpzForm({
		closable: false,
		region:'center',
		/*height:500,*/
		pzlb: '30032',
		labelWidth : 120,
		cols:2,
//		bindStore:xblryGrid.store,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:hxxGrid,
		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
		changeDictCust:function(cmb,res){
			
			return;
		},
		title: '详情信息',
		buttons:[new Ext.Button({
			id:'close',
			text:'关闭',
			minWidth:60,
			handler:function(){
				window.parent.closeWorkSpace();
			}
		}),
		new Ext.Button({
			id:'return',
			text:'返回',
			minWidth:60,
			handler:function(){
				Ext.getCmp('card').getLayout().setActiveItem(1);
			}
		})]
	});	
	var p1_1 = new Gnt.ux.ZdycxPanel({
		pzlb:form30032.pzlb,
		region:'center',
		layout:'border',
		border:false,
		frame: false,
		height:600
	});
	var p1 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[
			{
				id:"nextId",
				border:false,
				frame: false,
				region:'center',
				visible:false,
	//        	height: 300,
				items:[hcyGrid]
			},
			{
				border:false,
				frame: false,
				region:'center',
				items:[{
					id:"centerId",
	    	        border:false,
	    	        frame:false,
					region:'south',
//					hidden:true,
					items:[{
						id: "panelHtmlId" ,
						html: '<div class="text" style="text-align:center;">临时身份证查询</div>',
						height: 80,
						region:'north',
						bodyStyle:'padding:15px'
					},
					form30032
				]
				},
				{
					id:"p1_1",
	    	        border:false,
	    	        frame:false,
					region:'south',
					hidden:true,
					items:[p1_1]
				}]
			},
//			{
//				id:"centerId",
//				border:false,
//				frame: false,
//				region:'center',
//				layout:'border',
//	        	height: 300,
//				items:[{
//						id: "panelHtmlId" ,
//						html: '<div class="text" style="text-align:center;">临时身份证查询</div>',
//						height: 80,
//						region:'north',
//						bodyStyle:'padding:15px'
//					},
//					form30032
//				]
//			},
			{
				border:false,
				frame: false,
	        	region:'south',
	        	height: 40,
	        	bodyStyle: 'padding:5px',
				layout:'table',
				layoutConfig: {
		        	columns: 14
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
		    		    items:[new Ext.Button({
		    		    	id:'fzId',
		        			text:'复杂条件',
		        			minWidth:80,
		        			handler:function(){
		        				zhcx_dialog.show();
		        			}
		        		})]
		    		},{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		    		},{
						border:false,
						frame: false,
		    		    items:[{
			    	    	xtype: 'DkButton',
			    	    	form:form30032,
			    	    	callback: function(){
			    	    		//Ext.getCmp("queryId").handler();
			    	    	}
			    	    }]
		    		},/*{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		    		},{
						border:false,
						frame: false,
		    		    items:[{
		    		    	id:'c31',
			        		xtype:'checkbox',
			        		boxLabel : "显示记录总数",  
			        		name : "xxzs",  
			        		inputValue : "2"  
			        	}]
		    		},*/{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		    		},{
						border:false,
						frame: false,
		    		    items:[new Ext.Button({
		    		    	id:'preId',
		        			text:'上一步',
		        			disabled:true,
		        			minWidth:60,
		        			handler:function(){
		        				Ext.getCmp('centerId').show();
		        				Ext.getCmp('p1_1').doLayout();
		        				Ext.getCmp('p1_1').setVisible(false);
		        				Ext.getCmp('preId').setDisabled(true);
		        				Ext.getCmp('nexId').setDisabled(false);
		        			}
		        		})]
		    		},{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		    		},{
						border:false,
						frame: false,
		    		    items:[new Ext.Button({
		    		    	id:'nexId',
		        			text:'下一步',
		        			minWidth:60,
		        			handler:function(){
		        				p1_1.qybz = true;
		        				Ext.getCmp('centerId').hide();
		        				Ext.getCmp('p1_1').setVisible(true);
		        				Ext.getCmp('p1_1').doLayout();
		        				Ext.getCmp('preId').setDisabled(false);
		        				Ext.getCmp('nexId').setDisabled(true);
		        			}
		        		})]
		    		},{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		    		},{
						border:false,
						frame: false,
		    		    items:[new Ext.Button({
		    		    	id:'queryId',
		        			text:'查询',
		        			minWidth:60,
		        			handler:function(){
		        				var data = form30032.getForm().getValues();
								//记录日志的操作码
								data.log_code = "F7002";
								if(Gnt.util.isEmpty(data)){
									showInfo("请设置查询条件！");
									return;
								}
								Ext.Msg.wait("正在执行查询，请稍后...");
								var ary = p1_1.xszdGrid.store.data.items;
								var pxary = p1_1.pxzdGrid.store.data.items;//length 长度;
								if(p1_1.qybz){
									hxxGrid.reconfigure(
											hxxGrid.store,new Ext.grid.ColumnModel(Gnt.ZdyColumn(ary)));
									hxxGrid.pxary = pxary;
								};
								hxxGrid.zdyValueKey = Gnt.zdyValueKey(ary);
								var header=[];
								var shuxing = [];
								var zdyValueKey = Gnt.zdyValueKey(ary);
								hxxGrid.type = 'sfzxxcx';
		        				var store = hxxGrid.store;
		        				store.removeAll();
		        				store.baseParams = data;
		        				var config=hxxGrid.colModel.config;
								Ext.each(config,function(r){
									header.push(r.header);
									shuxing.push(r.dataIndex);
								});
								store.baseParams.header = encodeURI(header);
								store.baseParams.shuxing = encodeURI(shuxing);
								store.baseParams.zdyValueKey = Ext.encode(zdyValueKey);
								store.baseParams.daochuFlag = 'sfzxxcx';
		        				store.load({params:{start:0, limit:20}});
		        				Ext.each(pxary, function(pxObj){
		        					store.sort(pxObj.data.fieldname, 'ASC');
		    					});
		        				//Ext.getCmp('card').getLayout().setActiveItem(1);
		        			
		        			}
		        		})]
		    		},{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		    		},{
						border:false,
						frame: false,
		    		    items:[new Ext.Button({
		        			text:'关闭',
		        			minWidth:60,
		        			handler:function(){
		        				window.parent.closeWorkSpace();
		        			}
		        		})]
		    		},{
		        		frame:false,
						border:false,
						xtype:'panel',
						html:'',
						height:40
		        	},{
		    	    	frame:false,
						border:false,
						id:'_READ_CARD_ID',
						xtype:'panel',
						html:'',
						width:10
					}
		    		 
		    	]
	        }
		]
	});
	
	var hxxGrid = new Gnt.ux.SjpzGrid({
		pkname: 'lsslid',
		pzlb: '30032',
		url:'cx/lzcx/querySfzxxcx',
		region:'center',
		height:500,
		title: '',
		dcFlag:true,
		showPaging:true,
		loadCallback: function(res, store, bind_grid){
			Ext.Msg.hide();
			if(res.length>0){
				Ext.getCmp('card').getLayout().setActiveItem(1);
			}else{
				showInfo("未查到有关信息！");
			}
		},
		listeners:{
			rowclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				selectLsslid = selRes.data.lsslid; 			
			},
			rowdblclick:function(grid,row){
				selRes = grid.store.getAt(row);
				selectLsslid = selRes.data.lsslid;
				NewForm30032.getForm().loadRecord(selRes);
				Gnt.toFormReadyonly(NewForm30032);
 				Ext.getCmp('card').getLayout().setActiveItem(2);
 				v.doLayout();
			}
		}
	});
	
	var zhcx_dialog = new Gnt.ux.ZhcxDialog({
		pzlb : form30032.pzlb,
		grid:hxxGrid,
		callback: function(where){
			var ary = p1_1.xszdGrid.store.data.items;
			var pxary = p1_1.pxzdGrid.store.data.items;//length 长度;
			if(p1_1.qybz){
				hxxGrid.reconfigure(
						hxxGrid.store,new Ext.grid.ColumnModel(Gnt.ZdyColumn(ary)));
				hxxGrid.pxary = pxary;
			};
			hxxGrid.zdyValueKey = Gnt.zdyValueKey(ary);
			var grid = this.grid;
			var store = grid.store;
			var length = 0;
			var data = {"where": where};
			store.removeAll();
			data.pzlb = grid.store.pzlb;
			data.config_key = this.config_key;
			//记录日志的操作码
			data.log_code = "F7002";
			
			//add by zjm 20201223 复杂查询传入列表头 属性 自定义字段，用于导出功能使用
			var header=[];
			var shuxing = [];
			var zdyValueKey = Gnt.zdyValueKey(ary);
			grid.type = 'sfzxxcx';
			Ext.each(ary,function(r){
				header.push(r.data.displayname);
				shuxing.push(r.data.fieldname);
			});
			data.header = encodeURI(header);
			data.shuxing = encodeURI(shuxing);
			data.zdyValueKey = Ext.encode(zdyValueKey);
			data.daochuFlag = 'sfzxxcx';
			
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
					Ext.getCmp('card').getLayout().setActiveItem(1);
				}else{
					showInfo("没有找到符合条件的数据！");
				}
				Ext.Msg.hide();
			},grid); 			        						
		}
	});
	
	 var lssfz_window = new Gnt.ux.SelectLssfz({
		callback: function(optype, data){
			hxxGrid.store.reload(); 
		}
	});
	
	var p2 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[
			hxxGrid
		],
		buttons:[
			new Ext.Button({
				id:'huInfo',
				text:'证件作废',
				minWidth:60,
				handler:function(){
					if(hxxGrid.getStore().getCount() > 0){
						
						if(hxxGrid.getSelectionModel().getCount() ==1){
							selectLsslid = hxxGrid.getSelectionModel().getSelections()[0].data.lsslid;
							var sfzhm=hxxGrid.getSelectionModel().getSelections()[0].data.gmsfhm;
							if(confirm("作废的身份号码为["+sfzhm+"],确定吗?")){
								Gnt.submit(
										{lsslid:selectLsslid}, 
										"cx/lzcx/updateSfzxxcx", 
										"作废证件信息，请稍后...", 
										function(jsonData,params){
											showInfo("作废证件信息成功！");
											hxxGrid.store.reload();
										},
										function(jsonData,params){
											alert(jsonData.message);
										}
								);
//								var store = upshzGrid.store;
//		        				store.baseParams = {
//		        						lsslid:selectLsslid,
//		         						config_key:'updateSfzxxcx',
//		         						start:0,
//		         						limit:20
//		        					};
//		        				store.load();
		        				Ext.getCmp("queryId").handler();
							}
						
						}else{
							alert("请选择需要作废的数据!");
						}
					}else{
						alert("请先执行查询!");
					}
				}
			}),
			new Ext.Button({
				id:'huInfo',
				text:'卡号修改',
				minWidth:60,
				handler:function(){
					if(hxxGrid.getStore().getCount() > 0){
						
						if(hxxGrid.getSelectionModel().getCount() ==1){
							var data = hxxGrid.getSelectionModel().getSelections()[0].data;
//							selectLsslid = hxxGrid.getSelectionModel().getSelections()[0].data.lsslid;
//							var sfz=hxxGrid.getSelectionModel().getSelections()[0].data.gmsfhm;
//							var lsjmsfzkh=hxxGrid.getSelectionModel().getSelections()[0].data.lsjmsfzkh;
							var dybz=hxxGrid.getSelectionModel().getSelections()[0].data.dybz;
							if(dybz==0){
								Ext.MessageBox.alert("提示","受理信息没有打印过,不能修改卡号!.");
							}else{
								lssfz_window.setSelRes(data);
								lssfz_window.show();
							}
						
						}else{
							alert("请选择需要作废的数据!");
						}
					}else{
						alert("请先执行查询!");
					}
				}
			}),
			new Ext.Button({
				id:'close',
				text:'关闭',
				minWidth:60,
				handler:function(){
					window.parent.closeWorkSpace();
				}
			}),
			new Ext.Button({
				id:'return',
				text:'返回',
				minWidth:60,
				handler:function(){
					Ext.getCmp('card').getLayout().setActiveItem(0);
				}
			})
		]
	});
	
	var p4 = new Ext.Panel({
		layout:'border',
		items:[NewForm30032]
	});	
	//释放iframe占用资源
	function fixIFrame(o, p){
		var iFrame = p.getEl().dom; 
		if (iFrame.src) {
			iFrame.src = "javascript:false"; 
		}
	}

	function applyParam(store){
		
		/**
			是否显示记录总数
		 */
		var zs = '';
//		if(Ext.getCmp('c31').getValue()){
//			zs = '1';
//		}
		
		
		//Ext.apply(store.baseParams, {ryfw:'0',rkzt1:'',rkzt2:'',rkzt3:'',rkzt4:'',ryzl1:'',ryzl2:'',xszs:zs});
	}
	
	
	
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
        	items:[p1,p2,p4]
        }
    });
	

		
	
});