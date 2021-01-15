
var cxCount = 1;
var rynbid = null;
var selectRow ="";
var htmlStrStart = '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="cx/hjjbxx/ckxx/queryXxxx';
var htmlStrEnd = '"></iframe>';
//'?rynbid='+ getRynbid(rynbid) + "&goto=queryHdxx&hhnbid=" + getQueryParam("hhnbid") + '"> </iframe>';

var selectMlpnbid = null;
var selectHhnbid = null;
var selRes = null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10019,20002,10017",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	
	/**
		点击下一步后显示的内容
		暂时用户成员信息代替
	 */
	var hcyGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		pzlb: '10019',
    	region:'south',
		height:150,
    	title: '',
		url: 'yw/common/queryRyxx.json',
    	showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){
			}
		}
	});
	
	var form20002 = new Gnt.ux.SjpzForm({
		pzlb: '20002',
		url: 'yw/queryRyxx',
		title:'',
		cols:2,
		formType:'query',
		listeners:{
			rowclick:function(g, rowIndex, e){
				//alert(1111);
			}
		}
	});
	
	var cxfw = new Ext.data.SimpleStore({
		id:0,
		fields:[{name: 'code', mapping: 0},{name: 'name', mapping: 1}],
	   	data:[['0','未处理'],['1','已处理'],['2','已删除']]
	});
	var p1_1 = new Gnt.ux.ZdycxPanel({
		pzlb:form20002.pzlb,
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
						html: '<div class="text" style="text-align:center;">门楼牌信息查询</div>',
						height: 80,
						region:'north',
						bodyStyle:'padding:15px'
					},
					form20002
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
//						html: '<div class="text" style="text-align:center;">门楼牌信息查询</div>',
//						height: 80,
//						region:'north',
//						bodyStyle:'padding:15px'
//					},
//					form20002
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
			    	    	form:form20002,
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
		        				var data = form20002.getForm().getValues();
								if(Gnt.util.isEmpty(data)){
									showInfo("至少输入一个查询条件！");
									return;
								}
								//操作日志
		        				data.log_code = "F2008";
								Ext.Msg.wait("正在执行查询，请稍后...");
								var ary = p1_1.xszdGrid.store.data.items;
								var pxary = p1_1.pxzdGrid.store.data.items;//length 长度;
								if(p1_1.qybz){
									mlpxxGrid.reconfigure(
											mlpxxGrid.store,new Ext.grid.ColumnModel(Gnt.ZdyColumn(ary)));
									mlpxxGrid.pxary = pxary;
								};
								mlpxxGrid.zdyValueKey = Gnt.zdyValueKey(ary);
								var header=[];
								var shuxing = [];
								var zdyValueKey = Gnt.zdyValueKey(ary);
		        				var store = mlpxxGrid.store;
		        				store.removeAll();
		        				store.baseParams = data;
		        				mlpxxGrid.type = 'queryMlpxxcx';
								var config=mlpxxGrid.colModel.config;
								Ext.each(config,function(r){
									header.push(r.header);
									shuxing.push(r.dataIndex);
								});
								store.baseParams.header = encodeURI(header);
								store.baseParams.shuxing = encodeURI(shuxing);
								store.baseParams.zdyValueKey = Ext.encode(zdyValueKey);
								store.baseParams.daochuFlag = 'queryMlpxxcx';
		        				applyParam(store);
//		        				Ext.apply(store.baseParams, rkfwRadio.items);
		        				store.load({params:{start:0, limit:20}});
		        				Ext.each(pxary, function(pxObj){
		        					store.sort(pxObj.data.fieldname, 'ASC');
		    					});

		        				/**
		        					改变颜色
		        				 */
		        				store.on('load',function(s,records){
		        					var girdcount=0;
		        					s.each(function(r){
		        						
		        						if(0 == r.get("ryzt")){
		        							
		        						}else if(1 == r.get("ryzt")){
		        							//mlpxxGrid.getView().getRow(girdcount).style.backgroundColor="red";
		        						}else{
		        							//mlpxxGrid.getView().getRow(girdcount).style.backgroundColor="green";
		        						}
		        						
		        						girdcount ++ ;
		        					});
		        				});
		        				
		        				
		        				/*
		        				var store = hcyGrid.store;
		        				store.baseParams = {
		        						hhnbid:getQueryParam("hhnbid"),
		        						start:0,
		        						limit:20
		        					};
		        				store.load();
		        				
		        				var store = lsxxGrid.store;
								store.baseParams = {
										rynbid:getQueryParam("rynbid"),
										config_key:'queryPoHJXX_CZRKJBXXB2',
										start:0,
										limit:20
									};
								store.load();
								*/
		        				
//		        				p2.getUpdater().refresh();
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
	
	var mlpxxGrid = new Gnt.ux.SjpzGrid({
		pkname: 'mlpnbid',
		pzlb: '20002',
		url:'cx/hjjbxx/mlpxxcx/queryMlpxxcx.json',
		region:'center',
//		height:450,
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
				selectRow = rowIndex;
			},
			rowdblclick:function(grid, row){
				selRes = grid.store.getAt(row);
				selectRow = row;
				selectMlpnbid = selRes.data.mlpnbid;
				Ext.getCmp('card').getLayout().setActiveItem(2);
				//v.doLayout();
				Gnt.toFormReadyonly(newForm10017);
				newForm10017.doLayout();
				newForm10017.getForm().loadRecord(selRes);
//				var store = mlpxxInfoGrid.store;
//				store.baseParams = {
//						mlpnbid:selectMlpnbid,
// 						config_key:'queryMlpxx',
// 						start:0,
// 						limit:20
//					};
//				store.load();
//				store.on("load",function(store) {
// 					/**
//	 					往表单设值
//	 				 */
// 					var re = store.getAt(0);
// 					
//	 				if(re){
//	 					newForm10017.getForm().loadRecord(re);
//	 				}
// 				});
			}
		}
	});
	var zhcx_dialog = new Gnt.ux.ZhcxDialog({
		pzlb : form20002.pzlb,
		grid:mlpxxGrid,
		callback: function(where){
			var ary = p1_1.xszdGrid.store.data.items;
			var pxary = p1_1.pxzdGrid.store.data.items;//length 长度;
			if(p1_1.qybz){
				mlpxxGrid.reconfigure(
						mlpxxGrid.store,new Ext.grid.ColumnModel(Gnt.ZdyColumn(ary)));
				mlpxxGrid.pxary = pxary;
			};
			mlpxxGrid.zdyValueKey = Gnt.zdyValueKey(ary);
			var grid = this.grid;
			var store = grid.store;
			var length = 0;
			var data = {"where": where};
			store.removeAll();
			data.pzlb = grid.store.pzlb;
			data.config_key = this.config_key;
			//操作日志
			data.log_code = "F2008";

			//add by zjm 20201223 复杂查询传入列表头 属性 自定义字段，用于导出功能使用
			var header=[];
			var shuxing = [];
			var zdyValueKey = Gnt.zdyValueKey(ary);
			grid.type = 'queryMlpxxcx';
			Ext.each(ary,function(r){
				header.push(r.data.displayname);
				shuxing.push(r.data.fieldname);
			});
			data.header = encodeURI(header);
			data.shuxing = encodeURI(shuxing);
			data.zdyValueKey = Ext.encode(zdyValueKey);
			data.daochuFlag = 'queryMlpxxcx';
			
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
	var lsxxGrid = new Gnt.ux.SjpzGrid({
		pkname: 'mlpnbid',
		pzlb: '20002',
    	region:'south',
		height:150,
		url: 'cx/hjjbxx/ckxx/getXxxx.json',
    	title: '',
    	showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){
    			selRes = g.store.getAt(rowIndex);
    			curIndex = rowIndex;
    			
    		}
		}
	});
	var mlpxxInfoGrid = new Gnt.ux.SjpzGrid({
		pkname: 'ssxq',
		//region:'north',
		pzlb: '10017',
		title: '',
		url: 'cx/hjjbxx/mlpxxcx/queryMlpxxcx',
		showPaging:false
	});
	var form10017 = new Gnt.ux.SjpzForm({
		closable: false,
		region:'center',
		/*height:500,*/
		pzlb: '10017',
		labelWidth : 120,
		cols:2,
//		bindStore:xblryGrid.store,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:mlpxxInfoGrid,
		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
		changeDictCust:function(cmb,res){
			
			return;
		},
		title: '地信息'
	});
	var newForm10017 = new Gnt.ux.SjpzForm({
		closable: false,
		region:'center',
		/*height:500,*/
		pzlb: '10017',
		labelWidth : 120,
		cols:2,
//		bindStore:xblryGrid.store,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:mlpxxInfoGrid,
		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
		changeDictCust:function(cmb,res){
			
			return;
		},
		title: ''
	});
	
	var p2 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[
			mlpxxGrid
			/*{
				id:"p2CenterId",
				border:false,
				frame: false,
				region:'center',
				width:'100%',
//	        	height: 600,
				items:[
					mlpxxGrid,
					{
		     	        region:'south',
		    	        //禁止横向滚动条
//		    	        height:160,
		    	        border:false,
		    	        frame:false,
		    	        items:[{
							id:"p2Hcy",
			    	        border:false,
			    	        frame:false,
//							region:'south',
							hidden:true,
							items:[hcyGrid]
						},
						{
							id:"p2Lsxx",
			    	        border:false,
			    	        frame:false,
//							region:'south',
							hidden:true,
							items:[lsxxGrid]
						}]
					}
				]
			}*/
		],
		buttons:[
			new Ext.Button({
				id:'renzhuisu',
				text:'地追溯',
				minWidth:60,
				handler:function(){
					if(mlpxxGrid.getStore().getCount() > 0){
						if(mlpxxGrid.getSelectionModel().getCount() ==1){
							selectMlpnbid = mlpxxGrid.getSelectionModel().getSelections()[0].data.mlpnbid;
							Ext.getCmp('card').getLayout().setActiveItem(3);
							v.doLayout();
							Gnt.toFormReadyonly(form10017);
							form10017.getForm().loadRecord(mlpxxGrid.store.getAt(selectRow));
//							var store = mlpxxInfoGrid.store;
//	        				store.baseParams = {
//	        						mlpnbid:selectMlpnbid,
//	         						config_key:'queryMlpxx',
//	         						start:0,
//	         						limit:20
//	        					};
//	        				store.load();
//	        				store.on("load",function(store) {
//	         					/**
//	        	 					往表单设值
//	        	 				 */
//	         					var re = store.getAt(0);
//	        	 				if(re){
//	        	 					form10017.getForm().loadRecord(re);
//	        	 				}
//	         				});
						}else{
							alert("请选择需要查询的人员!");
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
	var p3 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[newForm10017],
		buttons:[
			new Ext.Button({
				id:'huzhuisu',
				text:'戶追溯',
				minWidth:60,
				handler:function(){
					if(mlpxxGrid.getStore().getCount() > 0){
						if(mlpxxGrid.getSelectionModel().getCount() ==1){
							var store = hxxGrid.store;
	        				store.removeAll();
	        				store.baseParams = {
	        						mlpnbid:mlpxxGrid.getSelectionModel().getSelections()[0].data.mlpnbid,
	        						start:0,
	        						limit:20
	        				};
	        				store.load();
	        				store.on("load",function(store) {
	        					if(store.data.length > 0){
	        						var data = store.getAt(0);
	        						if(data){
	        							NewForm20020.getForm().loadRecord(data);
	        							Ext.getCmp('card').getLayout().setActiveItem(4);
	    		        				Gnt.toFormReadyonly(NewForm20020);
	        						}
	        						NewForm20020.doLayout();
	        					}
	        				},hxxGrid); 
			 				
						}else{
							alert("请选择需要查询的户!");
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
					Ext.getCmp('card').getLayout().setActiveItem(1);
				}
			})
		]
	});	
	var p4 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[form10017,{
			region:'south',
        	buttonAlign : 'right',
	    	buttons: [
	    		new Ext.Button({
	    			text:'返回',
	    			minWidth:60,
	    			handler:function(){
	    				Ext.getCmp('card').getLayout().setActiveItem(1);
	    			}
	    		})
	    	]
		}]
		
	});
	//释放iframe占用资源
	function fixIFrame(o, p){
		var iFrame = p.getEl().dom; 
		if (iFrame.src) {
			iFrame.src = "javascript:false"; 
		}
	}

	//定义TabPanel的控制菜单
	Ext.ux.TabCloseMenu = function(){
	    var menu, ctxItem;
	    this.init = function(tabs){
	        tabs.on('contextmenu', onContextMenu);
	    }
	    function onContextMenu(tabs, item, e){
	        if(!menu){
	            menu = new Ext.menu.Menu([{
	                id: tabs.id + '-close',
	                text: '关闭当前页',
	                handler : function(){
	                    tabs.remove(ctxItem);
	                }
	            },{
	                id: tabs.id + '-close-others',
	                text: '关闭其它页',
	                handler : function(){
	                    tabs.items.each(function(item){
	                    	if(item.closable && item != ctxItem){
	                            tabs.remove(item);
	                        }
	                    });
	                }
	            }]);
	        }
	        ctxItem = item;
	        var items = menu.items;
	        
	        //设置【关闭当前页】菜单是否有效和当前页的closable属性一致
	        items.get(tabs.id + '-close').setDisabled(!item.closable);
	        
	        var disableOthers = true; 
	       	//遍历分页面板所有分页，查看除了自己，是否还有能够关闭的分页
	        tabs.items.each(function(){
	            if(this != item && this.closable){
	                disableOthers = false;
	                return false;
	            }
	        });
	        
	        //设置【关闭其它页】菜单是否有效
	        items.get(tabs.id + '-close-others').setDisabled(disableOthers);
	        
	        //在鼠标右击处显示菜单
	        menu.showAt(e.getPoint());
	    }
	};
	
	//定义分页面板
    var tabs = new Ext.TabPanel({
    	id:'tabs',
//        activeTab: 0,
        width:500,
        height:250,
        resizeTabs:false, 
        enableTabScroll:true,
        plain:false,
        //activeTab: 0,		//默认选中标签
        listeners:{
        	//在关闭分页的时候，调用函数释放iframe占用资源
        	beforeremove:fixIFrame.createDelegate(this)
        },
        plugins: new Ext.ux.TabCloseMenu(),
        defaults:{
        	closable:false,
        	autoScroll: false,
        	frame: false,
        	shim: false,
        	xtype: 'panel',
        	iconCls : 'otherfile'
        },
        items:[{
			id : "mlpxx",
//			url: 'cx/hjjbxx/ckxx/queryRkxx',
			/*autoLoad: {
				url: 'cx/hjjbxx/ckxx/queryRkxx',
				params: {
					rynbid: getQueryParam("rynbid")
				},
				method: 'GET'
			},*/
			title : "门楼牌信息",
//			html:' <iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'+'cx/hjjbxx/ckxx/queryXxxx?rynbid='+ getRynbid(rynbid) + "&goto=queryRkxx&hhnbid=" + getQueryParam("hhnbid") + '"> </iframe>',
			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
				activate:function(){
					//alert(getQueryParam("rynbid"));
					
//					alert(1);
					//alert(selectMlpnbid);
					//alert(getQueryParam("mlpnbid"));
					Ext.getCmp('mlpxx').body.update(htmlStrStart + '?rynbid='+ getRynbid(rynbid)+"&ryid=" + selectMlpnbid + "&goto=queryMlpxx&mlpnbid=" + selectMlpnbid + htmlStrEnd);
					
					this.getUpdater().refresh();
				}
			}
			//tabTip : "人口信息",
        }]
    });
	
	function applyParam(store){
		
		
		
		
		/**
			是否显示记录总数
		 */
		var zs = '';
//		if(Ext.getCmp('c31').getValue()){
//			zs = '1';
//		}
		
		
		Ext.apply(store.baseParams, {ryfw:'0',rkzt1:'',rkzt2:'',rkzt3:'',rkzt4:'',ryzl1:'',ryzl2:'',xszs:zs});
	}
	
	function getRynbid(rynbid){
		if(rynbid != null){
			return rynbid;
		}else{
			return getQueryParam("rynbid");
		}
	}
	var hxxGrid = new Gnt.ux.SjpzGrid({
		pkname: 'hhnbid',
		pzlb: '20001',
		url:'yw/common/queryHjxx.json',
		region:'center',
		height:500,
		title: '',
		showPaging:true
	});
	var NewForm20020 = new Gnt.ux.SjpzForm({
		closable: false,
		region:'center',
		/*height:500,*/
		pzlb: '20020',
		labelWidth : 120,
		cols:2,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:hxxGrid,
		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
		changeDictCust:function(cmb,res){
			
			return;
		},
		title: ''
	});
	var p5 = new Ext.Panel({
		layout:'border',
		items:[NewForm20020,{
			region:'south',
			width: '100%', 
	        border:false,
	        frame:false,
	        buttons:[
		        new Ext.Button({
		        	text:'关闭',
		        	minWidth:100,
		        	handler:function(){
		        		window.parent.parent.closeWorkSpace();
		        	}
		        }),
		        new Ext.Button({
		        	text:'返回',
		        	minWidth:100,
		        	handler:function(){
		        		Ext.getCmp('card').getLayout().setActiveItem(1);
		        	}
		        })
	        ]
	    
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
        	items:[p1,p2,p3,p4,p5]
        }
    });
	
    if(getQueryParam("ai")){
    	Ext.getCmp('card').getLayout().setActiveItem(2);
    	tabs.setActiveTab(0);
    }else{
    	Ext.getCmp('nextId').setVisible(false);
    }
    
	/**
		按下回车键执行查询操作
		
	var key1 = new Ext.KeyMap(document, {
		key:Ext.EventObject.ENTER,
		fn:function() {
			var data = form20000.getForm().getValues();
			
			if(Gnt.util.isEmpty(data)){
				showInfo("至少输入一个查询条件！");
				return;
			}
			
			var store = mlpxxGrid.store;
			
			store.removeAll();
			
			store.baseParams = data;
			
			applyParam(store);
			
			store.load({params:{start:0, limit:20}});
			
			//改变颜色
    store.on('load',function(s,records){
    	var girdcount=0;
    	s.each(function(r){
    		
    		if(0 == r.get("ryzt")){
    			
    		}else if(1 == r.get("ryzt")){
    			mlpxxGrid.getView().getRow(girdcount).style.backgroundColor="red";
    		}else{
    			mlpxxGrid.getView().getRow(girdcount).style.backgroundColor="green";
    		}
    		
    		girdcount ++ ;
    	});
    });
    Ext.getCmp('card').getLayout().setActiveItem(1);
},
scope: this  
});
	 */
	
	
});