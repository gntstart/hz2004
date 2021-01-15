
var cxCount = 1;
var rynbid = null;

var selectRynbid = null;
var selectHhnbid = null;

Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10019,20001,20020",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	
	/**
		点击下一步后显示的内容
		暂时用户成员信息代替
	 */
	var hcyGrid = new Gnt.ux.SjpzGrid({
		pkname: 'hhnbid',
		pzlb: '20001',
    	region:'south',
		height:150,
    	title: '',
		url: 'yw/common/queryRyxx.json',
    	showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){}
		}
	});
	
	var form20001 = new Gnt.ux.SjpzForm({
		pzlb: '20001',
		url: 'cx/hjjbxx/hxxcx/getHxx',
		title:'',
		cols:2,
		formType:'query'
	});
	
	var hxxGrid = new Gnt.ux.SjpzGrid({
		pkname: 'hhnbid',
		pzlb: '20001',
		url:'cx/hjjbxx/hxxcx/getHxx.json',
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
				selectRynbid = selRes.data.rynbid;
    			selectHhnbid = selRes.data.hhnbid;
			},
			rowdblclick:function(grid,row){
				selRes = grid.store.getAt(row);
    			selectHhnbid = selRes.data.hhnbid;
    			if(selRes.data.jhsj){
    				selRes.data.jhsj = selRes.data.jhsj.substring(0,8);
    			}
    			//alert(selRes.data.jhsj);
    			
    			formHxx.getForm().loadRecord(selRes);
    			Gnt.toFormReadyonly(formHxx);
// 				var store = hxxGrid.store;
// 				store.baseParams = {
// 					hhnbid:selectHhnbid,
// 					config_key:'queryhxx',
// 					start:0,
// 					limit:20
// 				};
// 				store.load();
// 				store.on("load",function(store) {
// 					/**
//	 					往表单设值
//	 				 */
// 					
////	 				var pkvalue = store.getAt(0).data[formHxx.bindStore.pkname];
////	 				var re = formHxx.bindStore.getById(pkvalue);
// 					var re = store.getAt(0);
//	 				if(re){
//	 					formHxx.getForm().loadRecord(re);
//	 				}
// 				});
 				Ext.getCmp('card').getLayout().setActiveItem(2);
 				v.doLayout();
			}
		}
	});
	var zhcx_dialog = new Gnt.ux.ZhcxDialog({
		pzlb : form20001.pzlb,
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
			data.log_code = "F1301";
			
			//add by zjm 20201223 复杂查询传入列表头 属性 自定义字段，用于导出功能使用
			var header=[];
			var shuxing = [];
			var zdyValueKey = Gnt.zdyValueKey(ary);
			grid.type = 'getHxx';
			Ext.each(ary,function(r){
				header.push(r.data.displayname);
				shuxing.push(r.data.fieldname);
			});
			data.header = encodeURI(header);
			data.shuxing = encodeURI(shuxing);
			data.zdyValueKey = Ext.encode(zdyValueKey);
			data.daochuFlag = 'getHxx';
			
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
	var NewForm20020 = new Gnt.ux.SjpzForm({
		closable: false,
		region:'center',
		/*height:500,*/
		pzlb: '20020',
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
	var p1_1 = new Gnt.ux.ZdycxPanel({
		pzlb:form20001.pzlb,
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
						html: '<div class="text" style="text-align:center;">户信息查询</div>',
						height: 80,
						region:'north',
						bodyStyle:'padding:15px'
					},
					form20001
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
//						html: '<div class="text" style="text-align:center;">户信息查询</div>',
//						height: 80,
//						region:'north',
//						bodyStyle:'padding:15px'
//					},
//					form20001
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
			    	    	form:form20001,
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

		        				var data = form20001.getForm().getValues();
		        				
		        				if(getQueryParam("jumpToHxxcx")&& getQueryParam("jumpToHxxcx")!=""){
		        					
		        				}else{
		        					if(Gnt.util.isEmpty(data)){
		        						showInfo("至少输入一个查询条件！");
		        						return;
		        					}
		        				}
		        				data.log_code = "F1301";
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
		        				var store = hxxGrid.store;
		        				store.removeAll();
		        				store.baseParams = data;
		        				hxxGrid.type = 'getHxx';
								var config=hxxGrid.colModel.config;
								Ext.each(config,function(r){
									header.push(r.header);
									shuxing.push(r.dataIndex);
								});
								store.baseParams.header = encodeURI(header);
								store.baseParams.shuxing = encodeURI(shuxing);
								store.baseParams.zdyValueKey = Ext.encode(zdyValueKey);
								store.baseParams.daochuFlag = 'getHxx';
//		        				Ext.apply(store.baseParams, rkfwRadio.items);
		        				store.load({params:{start:0, limit:40}});
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
		        						}else{
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
	
	var lsxxGrid = new Gnt.ux.SjpzGrid({
		pkname: 'hhnbid',
		pzlb: '20001',
    	region:'south',
		height:150,
		url: 'cx/hjjbxx/hxxcx/getHxx.json',
    	title: '',
    	showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){
    			selRes = g.store.getAt(rowIndex);
    			curIndex = rowIndex;
    			
    		}
		}
	});
	
	var p2 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[
			hxxGrid
			/*{
				id:"p2CenterId",
				border:false,
				frame: false,
				region:'center',
				width:'100%',
//	        	height: 600,
				items:[
					hxxGrid,
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
				id:'huInfo',
				text:'户信息',
				minWidth:60,
				handler:function(){
					if(hxxGrid.getStore().getCount() > 0){
						
						if(hxxGrid.getSelectionModel().getCount() ==1){
							
							var re = hxxGrid.getSelectionModel().getSelected();
							NewForm20020.getForm().loadRecord(re);
							
							Ext.getCmp('card').getLayout().setActiveItem(3);
	        				Gnt.toFormReadyonly(NewForm20020);
			 				v.doLayout();
			 				
							/*
							selectHhnbid = hxxGrid.getSelectionModel().getSelections()[0].data.hhnbid;
							
							var store = NewHxxGrid.store;
	        				store.baseParams = {
	        						hhnbid:selectHhnbid,
	         						config_key:'queryhxx',
	         						start:0,
	         						limit:20
	        					};
	        				store.load();
	        				store.on("load",function(store) {
	         					*//**
	        	 					往表单设值
	        	 				 *//*
	         					var re = store.getAt(0);
	        	 				if(re){
	        	 					NewForm20020.getForm().loadRecord(re);
	        	 				}
	         				});
	        				Ext.getCmp('card').getLayout().setActiveItem(3);
	        				Gnt.toFormReadyonly(NewForm20020);
			 				v.doLayout();
			 				*/
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
					Ext.getCmp('card').getLayout().setActiveItem(0);
					v.doLayout();
				}
			})
		]
	});
	
	var formHxx = new Gnt.ux.SjpzForm({
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
	
	var p3 = new Ext.Panel({
		layout:'border',
		items:[{
	        region:'center',
	        //禁止横向滚动条
	        border:false,
	        frame:false,
	        items:[{
		        	region:'north',
		        	buttonAlign : 'left',
			    	buttons: [new Ext.Button({
		    		    	id:'fzId',
		        			text:'户成员信息',
		        			minWidth:80,
		        			handler:function(){
//		        				var selectHhnbid = formHxx.getForm().getValues().hhnbid;
		        				var selectHhnbid = hxxGrid.getSelectionModel().getSelected().data.hhnbid;
		        				var url = basePath + "cx/hjjbxx/thrycx?jumpToThrycx="+"1"+"&hhnbid=" + selectHhnbid;
		    					if(parent && parent.openWorkSpace){
		    						parent.openWorkSpace(url,  "同户人员信息查询", "thryxxcx",null,null,true);
		    					}else{
		    						window.location.href = url;
		    					}
		        			}
		        		}),new Ext.Button({
		        			text:'户成员变动信息',
		        			minWidth:60,
		        			handler:function(){
//		        				var selectHhnbid = formHxx.getForm().getValues().hhnbid;
		        				var selectHhnbid = hxxGrid.getSelectionModel().getSelected().data.hhnbid;
		        				var url = basePath + "cx/hjywxx/hcybdcx?jumpToHcybdcx="+"1"+"&hhnbid=" + selectHhnbid;
		    					if(parent && parent.openWorkSpace){
		    						parent.openWorkSpace(url,  "户成员变动情况信息查询", "hcybdqkxxcx" + id,null,null,true);
		    					}else{
		    						window.location.href = url;
		    					}
		        			}
		        		})
		    		]
	        },formHxx]
	    }],
		buttons:[
			new Ext.Button({
				id:'close',
				text:'关闭',
				minWidth:60,
				handler:function(){
					window.parent.closeWorkSpace();
				}
			}),
			new Ext.Button({
				text:'返回',
				minWidth:60,
				handler:function(){
					//p2.getUpdater().refresh();
					Ext.getCmp('card').getLayout().setActiveItem(1);
					v.doLayout();
				}
			})
		]
	});
	var p4 = new Ext.Panel({
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
		
		Ext.apply(store.baseParams, {xszs:zs,queryXx:"queryhxx"});
	}
	
	function getRynbid(rynbid){
		if(rynbid != null){
			return rynbid;
		}else{
			return getQueryParam("rynbid");
		}
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
        	items:[p1,p2,p3,p4]
        }
    });
	
    if(getQueryParam("ai")){
    	Ext.getCmp('card').getLayout().setActiveItem(getQueryParam("ai"));
    	
    	p3.doLayout();
    	
    	Gnt.toFormReadyonly(formHxx);
//    	tabs.setActiveTab(0);
    }else{
    	Ext.getCmp('nextId').setVisible(false);
    }
	if(getQueryParam("jumpToHxxcx")&& getQueryParam("jumpToHxxcx")!=""){
			var store = hxxGrid.store;
			store.baseParams = {
				config_key:'queryhxx',
				start:0,
				limit:20
			};
			
			if(getQueryParam("queryXx")){
				Ext.apply(store.baseParams, {queryXx:getQueryParam("queryXx")});
			}
			if(getQueryParam("hhnbid")){
				Ext.apply(store.baseParams, {hhnbid:getQueryParam("hhnbid")});
			}
			if(getQueryParam("mlpnbid")){
				Ext.apply(store.baseParams, {mlpnbid:getQueryParam("mlpnbid")});
			}
			
			store.load();
			store.on("load",function(store) {
				if(store.getCount() > 0){
					if(getQueryParam("ai")==2){
						hxxGrid.fireEvent("rowclick",hxxGrid,0);
					}else{
						hxxGrid.fireEvent("rowdblclick",hxxGrid,0);
					}
					hxxGrid.getSelectionModel().selectRange(0,0);
				}
			});
			
			Gnt.toFormReadyonly(formHxx);
			
//			Ext.getCmp('card').getLayout().setActiveItem(2);
//			v.doLayout();	
	}	
	
});