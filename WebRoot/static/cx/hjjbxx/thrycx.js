
var cxCount = 1;
var rynbid = null;
var hhnbid = null;
var ryid = null;
var gmsfhm = null;
var htmlStrStart = '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="cx/hjjbxx/ckxx/queryXxxx';
var htmlStrEnd = '"></iframe>';
//'?rynbid='+ getRynbid(rynbid) + "&goto=queryHdxx&hhnbid=" + getQueryParam("hhnbid") + '"> </iframe>';

var selectRynbid = null;
var selectHhnbidselectHhnbid = null;
var zpWindow = null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	var hzywid = getQueryParam("hzywid");
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10019,20000",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	
	/**
		点击下一步后显示的内容
		暂时用户成员信息代替
	 */
	var hcyGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		pzlb: '20000',
    	region:'center',
//		height:150,
    	title: '',
		url: 'yw/common/queryHcy.json',
    	showPaging:false,
    	callCellmeta:function(r){
			if(r.data.ryzt=='0'){
				return '#000000';
			}
			else if(r.data.ryzt=='1'){
				return '#C41A11';
			}
			else {
				return '#20BBAF';
			}
		},
		listeners:{
			rowclick:function(g, rowIndex, e){
				rynbid = selRes.data.rynbid;
				hhnbid = selRes.data.hhnbid;
				ryid = selRes.data.ryid;
				gmsfhm = selRes.data.gmsfhm;
			},
			rowdblclick:function(grid,row){
				selRes = grid.store.getAt(row);
				
				if(selRes && selRes.data){
					rynbid = selRes.data.rynbid;
					hhnbid = selRes.data.hhnbid;
					ryid = selRes.data.ryid;
					gmsfhm = selRes.data.gmsfhm;
					tabs.setActiveTab(1);
					tabs.setActiveTab(0);
					
					Ext.getCmp('card').getLayout().setActiveItem(2);
					
				}else{
					
				}
			}
		}
	});
	
	var form20000 = new Gnt.ux.SjpzForm({
		pzlb: '20000',
		url: 'yw/queryRyxx',
		title:'',
		cols:2,
//		height:300,
		formType:'query'
	});
	var newForm20000 = new Gnt.ux.SjpzForm({
		closable: false,
		region:'center',
		/*height:500,*/
		pzlb: '20000',
		labelWidth : 120,
		cols:2,
//		bindStore:xblryGrid.store,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:newRkxxGrid,
		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
		changeDictCust:function(cmb,res){
			
			return;
		},
		title: ''
	});
	var newRkxxGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		//region:'north',
		pzlb: '20000',
		title: '',
		url: 'cx/hjjbxx/ckxx/getXxxx.json',
		showPaging:false
	});		
	var cxfw = new Ext.data.SimpleStore({
		id:0,
		fields:[{name: 'code', mapping: 0},{name: 'name', mapping: 1}],
	   	data:[['0','所外'],['1','区县外'],['2','地市外'],['3','所有'],['4','最新']]
	});
	var p1_1 = new Gnt.ux.ZdycxPanel({
		pzlb:form20000.pzlb,
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
						html: '<div class="text" style="text-align:center;"><br /><h2>同户人员查询</h2></div>',
						height: 80,
						region:'north',
						bodyStyle:'padding:15px'
					},
					form20000
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
////	        	height: 300,
//				items:[{
//						id: "panelHtmlId" ,
//						html: '<div class="text" style="text-align:center;"><br /><h2>同户人员查询</h2></div>',
//						height: 80,
//						region:'north',
//						bodyStyle:'padding:15px'
//					},
//					form20000
//				]
//			},
			{
				id:'southId',
				border:false,
				frame: false,
	        	region:'south',
	        	height: 40,
	        	bodyStyle: 'padding:5px',
				layout:'table',
				layoutConfig: {
		        	columns: 12
		        },
		    	items: [
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
		        			text:'组合条件',
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
			    	    	form:form20000,
			    	    	callback: function(){
			    	    		//Ext.getCmp("queryId").handler();
			    	    	}
			    	    }]
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
		    		},/*{
						border:false,
						frame: false,
		    		    items:[{
		    		    	id:'c31',
			        		xtype:'checkbox',
			        		boxLabel : "显示记录总数",  
			        		name : "xxzs",  
			        		inputValue : "2"  
			        	}]
		    		},{
		    			columnWidth: .3,
						border:false,
						frame: false
		    		},{
						border:false,
						frame: false,
		    		    items:[new Ext.Button({
		    		    	id:'preId',
		        			text:'上一步',
		        			disabled:true,
		        			minWidth:60,
		        			handler:function(){
		        				Ext.getCmp('preId').setDisabled(true);
		        				Ext.getCmp('fzId').setDisabled(false);
		        				Ext.getCmp('nexId').setDisabled(false);
		        				Ext.getCmp('nextId').setVisible(false);
		        				Ext.getCmp('centerId').setVisible(true);
		        			}
		        		})]
		    		},{
		    			columnWidth: .01,
						border:false,
						frame: false
		    		},{
						border:false,
						frame: false,
		    		    items:[new Ext.Button({
		    		    	id:'nexId',
		        			text:'下一步',
		        			minWidth:60,
		        			handler:function(){
		        				Ext.getCmp('preId').setDisabled(false);
		        				Ext.getCmp('fzId').setDisabled(true);
		        				Ext.getCmp('nexId').setDisabled(true);
		        				Ext.getCmp('nextId').setVisible(true);
		        				Ext.getCmp('centerId').setVisible(false);
		        			}
		        		})]
		    		},{
		    			columnWidth: .01,
						border:false,
						frame: false
		    		},*/{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
//						colspan:3
//						columns:10
		    		},{
						border:false,
						frame: false,
		    		    items:[new Ext.Button({
		    		    	id:'queryId',
		        			text:'查询',
		        			minWidth:60,
		        			handler:function(){
		        				var data = form20000.getForm().getValues();
								if(Gnt.util.isEmpty(data)){
									showInfo("至少输入一个查询条件！");
									return;
								}
								data.log_code = "F1004";
								data.ryzl1 = 1;
								Ext.Msg.wait("正在执行查询，请稍后...");
								var ary = p1_1.xszdGrid.store.data.items;
								var pxary = p1_1.pxzdGrid.store.data.items;//length 长度;
								if(p1_1.qybz){
									czrkGrid.reconfigure(
											czrkGrid.store,new Ext.grid.ColumnModel(Gnt.ZdyColumn(ary)));
									czrkGrid.pxary = pxary;
								};
								czrkGrid.zdyValueKey = Gnt.zdyValueKey(ary);
								var header=[];
								var shuxing = [];
								var zdyValueKey = Gnt.zdyValueKey(ary);
		        				var store = czrkGrid.store;
		        				store.removeAll();
		        				store.baseParams = data;
		        				czrkGrid.type = 'queryThry';
								var config=czrkGrid.colModel.config;
								Ext.each(config,function(r){
									header.push(r.header);
									shuxing.push(r.dataIndex);
								});
								store.baseParams.header = encodeURI(header);
								store.baseParams.shuxing = encodeURI(shuxing);
								store.baseParams.zdyValueKey = Ext.encode(zdyValueKey);
								store.baseParams.daochuFlag = 'queryThry';
		        				Ext.apply(store.baseParams, {nosjfw:'1'});
//		        				Ext.apply(store.baseParams, rkfwRadio.items);
		        				store.load({params:{start:0, limit:20}});
		        				Ext.each(pxary, function(pxObj){
		        					store.sort(pxObj.data.fieldname, 'ASC');
			    				});	
		        				/**
		        					改变颜色
		        				 */
		        				store.on('load',function(s,records){
		        					var girdcount = 0;
		        					s.each(function(r){
		        						
		        						if(0 == r.get("ryzt")){
		        							
		        						}else if(1 == r.get("ryzt")){
		        							czrkGrid.getView().getRow(girdcount).style.color = "#FF0000";
		        						}else{
		        							czrkGrid.getView().getRow(girdcount).style.color = "#227488";
		        						}
		        						
		        						girdcount ++ ;
		        						
		        					});
		        					if(records.length>0){
		        						czrkGrid.fireEvent("rowclick",czrkGrid,0);
		        						czrkGrid.getSelectionModel().selectRange(0,0);
		        						
		        					}
		        					
		        				});
		        				
		        				
		        				/*
		        				var store = hcyGrid.store;
		        				store.baseParams = {
		        						hhnbid:getQueryParam("hhnbid"),
		        						start:0,
		        						limit:20
		        					};
		        				store.load();
		        				
								*/
		        				
//		        				p2.getUpdater().refresh();
//		        				Ext.getCmp('card').doLayout();
		        				
		        				
		        				
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
	
	var czrkGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		pzlb: '20000',
		url:'cx/hjjbxx/ckxx/getRkxx.json',
		region:'center',
//		height:480,
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
		callCellmeta:function(r){
			if(r.data.ryzt=='0'){
				return '#000000';
			}
			else if(r.data.ryzt=='1'){
				return '#C41A11';
			}
			else {
				return '#20BBAF';
			}
		},
//		style:'color:red;background:blue;',
		listeners:{
			rowclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				
				selectRynbid = selRes.data.rynbid;
    			selectHhnbid = selRes.data.hhnbid;

				var store1 = hcyGrid.store;
				store1.baseParams = {
						hhnbid:selRes.data.hhnbid,
//						hhnbid:getQueryParam("hhnbid"),
						start:0,
						limit:20
					};
				store1.load();
				store1.on("load",function(store1) {  
				if(store1.data.length > 0){
					curIndex = 0;
					hcyGrid.fireEvent("rowclick",hcyGrid,curIndex);
					hcyGrid.getSelectionModel().selectRange(curIndex,curIndex);
				}
			},hcyGrid); 
				
				
			},
			rowdblclick:function(grid,row){
				selRes = grid.store.getAt(row);
				
				if(selRes && selRes.data){
					rynbid = selRes.data.rynbid;
					hhnbid = selRes.data.hhnbid;
					gmsfhm = selRes.data.gmsfhm;
//					var store1 = hcyGrid.store;
//					store1.baseParams = {
//							hhnbid:selRes.data.hhnbid,
////							hhnbid:getQueryParam("hhnbid"),
//							start:0,
//							limit:20
//						};
//					store1.load();
//					store1.on("load",function(store1) {  
//						if(store1.data.length > 0){
//							curIndex = 0;
//							hcyGrid.fireEvent("rowclick",hcyGrid,curIndex);
//							hcyGrid.getSelectionModel().selectRange(curIndex,curIndex);
//						}
//					})					
					tabs.setActiveTab(1);
					tabs.setActiveTab(0);
					
					Ext.getCmp('card').getLayout().setActiveItem(2);
					
				}else{
					
				}
			}
		}
	});
	var zhcx_dialog = new Gnt.ux.ZhcxDialog({
		pzlb : form20000.pzlb,
		grid:czrkGrid,
		callback: function(where){
			var ary = p1_1.xszdGrid.store.data.items;
			var pxary = p1_1.pxzdGrid.store.data.items;//length 长度;
			if(p1_1.qybz){
				czrkGrid.reconfigure(
						czrkGrid.store,new Ext.grid.ColumnModel(Gnt.ZdyColumn(ary)));
				czrkGrid.pxary = pxary;
			};
			czrkGrid.zdyValueKey = Gnt.zdyValueKey(ary);
			var grid = this.grid;
			var store = grid.store;
			var length = 0;
			var data = {"where": where};
			store.removeAll();
			data.pzlb = grid.store.pzlb;
			data.config_key = this.config_key;
			data.log_code = "F1004";
			
			//add by zjm 20201223 复杂查询传入列表头 属性 自定义字段，用于导出功能使用
			var header=[];
			var shuxing = [];
			var zdyValueKey = Gnt.zdyValueKey(ary);
			grid.type = 'queryThry';
			Ext.each(ary,function(r){
				header.push(r.data.displayname);
				shuxing.push(r.data.fieldname);
			});
			data.header = encodeURI(header);
			data.shuxing = encodeURI(shuxing);
			data.zdyValueKey = Ext.encode(zdyValueKey);
			data.daochuFlag = 'queryThry';
			
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
	var p2 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[
			czrkGrid,
			{
				id:"p2SouthId",
	        	region:'south',
//	        	height:120,
//	        	height:60,
	            border:false,
	            frame:false,
	            items:[{
	     	        region:'center',
	    	        //禁止横向滚动条
//	    	        height:160,
	    	        border:false,
	    	        frame:false,
	    	        items:[{
						id:"p2Hcy",
		    	        border:false,
		    	        frame:false,
//						region:'south',
//						hidden:true,
						items:[hcyGrid]
					}]
				
	            },{
	            	title: '字体颜色说明',
		            xtype: 'fieldset',
		            autoHeight: true,
		        	height:60,
		            items: [{
						border:false,
						frame: false,
			        	region:'center',
//			        	height: 40,
			        	//					上	右	下	左
//			        	bodyStyle: 'padding:0px 0px 10px 0px ',
						layout:'table',
						layoutConfig: {
				        	columns: 30
				        },
						items: [
							{
								border:false,
								frame: false,
								width:10
							},{
								width:60,
								bodyStyle:'background:black;'
							},{
								width: 20,
								border:false,
								frame: false
							},{
								border:false,
								frame: false,
								html:'正	常',
								width:100
							},{
								width: 10,
								border:false,
								frame: false
							},{
								width:60,
								bodyStyle:'background:#FF0000;'
							},{
								width: 20,
								border:false,
								frame: false
							},{
								border:false,
								frame: false,
								html:'死	亡',
								width:100
							},{
								width: 10,
								border:false,
								frame: false
							},{
								width:60,
								bodyStyle:'background:#227488;'
							},{
								width: 20,
								border:false,
								frame: false
							},{
								border:false,
								frame: false,
								html:'其	它(迁出,服兵役,出国境定居,删除,注销,其它)',
								width:400
							}
						]
					}]
					}
	            ]
				
			}
		]
		,buttons:[
			{
				id:'cbhkId',
				text:'常表户口',
				minWidth:60,
				handler:function(){
					if(czrkGrid.getStore().getCount() > 0&&czrkGrid.getSelectionModel().getSelections().length>0){
						selectHhnbid=czrkGrid.getSelectionModel().getSelections()[0].data.hhnbid;
						selectRynbid=czrkGrid.getSelectionModel().getSelections()[0].data.rynbid;
    					printRk(2,selectHhnbid,selectRynbid,[true,true,false,false,false],[true,true,false,false,false],[true,true,false,false,false]);
    					
					}else{
						alert("请先执行查询!");
					}
				}
			},{
				id:'hjzmId',
				text:'户籍证明',
				minWidth:60,
				handler:function(){
					if(czrkGrid.getStore().getCount() > 0){
						selectHhnbid=czrkGrid.getSelectionModel().getSelections()[0].data.hhnbid;
						selectRynbid=czrkGrid.getSelectionModel().getSelections()[0].data.rynbid;
    					//dyWindow.show();
						//dyGrid.store.load({params:{type:2,hhnbid:selectHhnbid, rynbid:selectRynbid}});
						printRk(2,selectHhnbid,selectRynbid,[false,false,true,false,false],[false,false,true,false,false],[false,false,true,false,false]);
					}else{
						alert("请先执行查询!");
					}
				}
			},{
				id:'xxxxId',
				text:'详细信息',
				minWidth:60,
				handler:function(){
					
					if(czrkGrid.getStore().getCount() > 0){
						
						if(czrkGrid.getSelectionModel().getCount() ==1){
							
							rynbid = czrkGrid.getSelectionModel().getSelections()[0].data.rynbid;
							hhnbid = czrkGrid.getSelectionModel().getSelections()[0].data.hhnbid;
							
							tabs.getUpdater().refresh();
							
							tabs.setActiveTab(0);
							
//							tabs.getActiveTab().loader.load();
//							alert(tabs.getActiveTab());
							
							Ext.getCmp('card').getLayout().setActiveItem(2);
						}else{
							alert("请选择需要查询的人员!");
						}
					}else{
						alert("请先执行查询!");
					}
					
				}
			},{
				id:'rzsId',
				text:'人追溯',
				minWidth:60,
				handler:function(){
					if(czrkGrid.getStore().getCount() > 0){
						if(czrkGrid.getSelectionModel().getCount() ==1){
							
							rynbid = czrkGrid.getSelectionModel().getSelections()[0].data.rynbid;
							selectRyid = czrkGrid.getSelectionModel().getSelections()[0].data.ryid;
    						
//							v.doLayout();
							var store4 = newRkxxGrid.store;
							store4.removeAll();
							store4.baseParams = {
									rynbid:rynbid,
									config_key:'queryPoHJXX_CZRKJBXXB7',
									start:0,
									limit:20
								};
							store4.load();
							
							store4.on("load",function(store) {
								if(store.data.length > 0){
									
									var re = store.getAt(0);
									/**
										往表单设值
									 */
//									var pkvalue = store4.getAt(0).data[newForm20000.bindStore.pkname];
//									var re = newForm20000.bindStore.getById(pkvalue);
									if(re){
										newForm20000.getForm().loadRecord(re);
										Gnt.toFormReadyonly(newForm20000);
										var zpid = re.data.zpid;
										if(zpid &&　zpid != 0){
											Ext.getCmp('p3Img').body.update("<IMG SRC='yw/common/img/render/" + zpid + "' width='100%' height='100%' />");
										}else{
											Ext.getCmp('p3Img').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
										}
									}
									zpWindow = new Gnt.ux.SelectZpAll({
										ryid:selRes.data.ryid
									});									
									
            						Ext.getCmp('card').getLayout().setActiveItem(3);
            						newForm20000.doLayout();
            						
								}
							},newRkxxGrid); 
						}else{
							alert("请选择需要查询的人员!");
						}
					}else{
						alert("请先执行查询!");
					}
					
				}
			},{
				text:'关闭',
				minWidth:60,
				handler:function(){
					window.parent.closeWorkSpace();
				}
			},{
				text:'返回',
				minWidth:60,
				handler:function(){
					Ext.getCmp('card').getLayout().setActiveItem(0);
				}
			}
		]
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
			id : "rkxx",
//			url: 'cx/hjjbxx/ckxx/queryRkxx',
			/*autoLoad: {
				url: 'cx/hjjbxx/ckxx/queryRkxx',
				params: {
					rynbid: getQueryParam("rynbid")
				},
				method: 'GET'
			},*/
			title : "人口信息",
//			html:' <iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'+'cx/hjjbxx/ckxx/queryXxxx?rynbid='+ getRynbid(rynbid) + "&goto=queryRkxx&hhnbid=" + getHhnbid(hhnbid) + '"> </iframe>',
			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
				activate:function(){
					//alert(getQueryParam("rynbid"));
					Ext.getCmp('rkxx').body.update(htmlStrStart + '?rynbid='+ getRynbid(rynbid) +"&ryid=" + getRyid(ryid) +"&gmsfhm=" + getGmsfhm(gmsfhm)+ "&goto=queryRkxx&hhnbid=" + getHhnbid(hhnbid) + "&hzywid=" + hzywid + htmlStrEnd);
					
					this.getUpdater().refresh();
				}
			}
//        	,tabTip : "人口信息"
        },{
			id : "sbxx",
//			url: 'cx/hjjbxx/ckxx/querySbxx',
//			autoLoad: { url: 'cx/hjjbxx/ckxx/querySbxx', params: { data: "从客户端传入的参数" }, method: 'GET' },
			//tabTip : "常口信息查询",
			title : "四变信息",
//			html:' <iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'+'cx/hjjbxx/ckxx/queryXxxx?rynbid='+ getRynbid(rynbid) + "&goto=querySbxx&hhnbid=" + getHhnbid(hhnbid) + '"> </iframe>',
			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
				activate:function(){
					/*
					if(hzywid != null){
						*//**
							户政业务处理自动跳转时不允许使用其他业务
							暂时不生效
						 *//*
					}else{
						Ext.getCmp('sbxx').body.update(htmlStrStart + '?rynbid='+ getRynbid(rynbid) + "&goto=querySbxx&hhnbid=" + getHhnbid(hhnbid) + "&hzywid=" + hzywid  + htmlStrEnd);
						
						this.getUpdater().refresh();
					}
					*/
					Ext.getCmp('sbxx').body.update(htmlStrStart + '?rynbid='+ getRynbid(rynbid) +"&ryid=" + getRyid(ryid) +"&gmsfhm=" + getGmsfhm(gmsfhm)+ "&goto=querySbxx&hhnbid=" + getHhnbid(hhnbid) + "&hzywid=" + hzywid  + htmlStrEnd);
					
					this.getUpdater().refresh();
				}
			}
        },{
			id : "bggz",
//			url : "cx/hjjbxx/ckxx",
			//tabTip : "常口信息查询",
			title : "变更更正",
//			html:' <iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'+'cx/hjjbxx/ckxx/queryXxxx?rynbid='+ getRynbid(rynbid) + "&goto=queryBggz&hhnbid=" + getHhnbid(hhnbid) + '"> </iframe>',
			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
				activate:function(){
					//alert(getQueryParam("rynbid"));
					Ext.getCmp('bggz').body.update(htmlStrStart + '?rynbid='+ getRynbid(rynbid) +"&ryid=" + getRyid(ryid) +"&gmsfhm=" + getGmsfhm(gmsfhm)+ "&goto=queryBggz&hhnbid=" + getHhnbid(hhnbid) + "&hzywid=" + hzywid  + htmlStrEnd);
					
					this.getUpdater().refresh();
				}
			}
        },{
			id : "hdxx",
//			url : "cx/hjjbxx/ckxx",
			//tabTip : "常口信息查询",
			title : "户地信息",
//			html:' <iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'+'cx/hjjbxx/ckxx/queryXxxx?rynbid='+ getRynbid(rynbid) + "&goto=queryHdxx&hhnbid=" + getHhnbid(hhnbid) + '"> </iframe>',
			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
				activate:function(){
					//alert(getQueryParam("rynbid"));
					Ext.getCmp('hdxx').body.update(htmlStrStart + '?rynbid='+ getRynbid(rynbid) +"&ryid=" + getRyid(ryid) +"&gmsfhm=" + getGmsfhm(gmsfhm)+ "&goto=queryHdxx&hhnbid=" + getHhnbid(hhnbid) + "&hzywid=" + hzywid  + htmlStrEnd);
					
					this.getUpdater().refresh();
				}
			}
            
        },{
			id : "hcybd",
//			url : "cx/hjjbxx/ckxx",
			//tabTip : "常口信息查询",
			title : "户成员变动",
			
//			html:' <iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'+'cx/hjjbxx/ckxx/queryXxxx?rynbid='+ getRynbid(rynbid) + "&goto=queryHcybd&hhnbid=" + getHhnbid(hhnbid) + '"> </iframe>',
			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
				activate:function(){
					//alert(getQueryParam("rynbid"));
					Ext.getCmp('hcybd').body.update(htmlStrStart + '?rynbid='+ getRynbid(rynbid) +"&ryid=" + getRyid(ryid) +"&gmsfhm=" + getGmsfhm(gmsfhm)+ "&goto=queryHcybd&hhnbid=" + getHhnbid(hhnbid) + "&hzywid=" + hzywid  + htmlStrEnd);
					
					this.getUpdater().refresh();
				}
			}
            
        },{
			id : "zjxx",
//			url : "cx/hjjbxx/ckxx",
			//tabTip : "常口信息查询",
			title : "证件信息",
//			html:' <iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'+'cx/hjjbxx/ckxx/queryXxxx?rynbid='+ getRynbid(rynbid) + "&goto=queryZjxx&hhnbid=" + getHhnbid(hhnbid) + '"> </iframe>',
			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
				activate:function(){
					//alert(getQueryParam("rynbid"));
					Ext.getCmp('zjxx').body.update(htmlStrStart + '?rynbid='+ getRynbid(rynbid) +"&ryid=" + getRyid(ryid) +"&gmsfhm=" + getGmsfhm(gmsfhm)+ "&goto=queryZjxx&hhnbid=" + getHhnbid(hhnbid) + "&hzywid=" + hzywid  + htmlStrEnd);
					
					this.getUpdater().refresh();
				}
			}
            
        },{
			id : "slxx",
//			url : "cx/hjjbxx/ckxx",
			//tabTip : "常口信息查询",
			title : "受理信息",
//			html:' <iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'+'cx/hjjbxx/ckxx/queryXxxx?rynbid='+ getRynbid(rynbid) + "&goto=querySlxx&hhnbid=" + getHhnbid(hhnbid) + '"> </iframe>',
			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
				activate:function(){
					//alert(getQueryParam("rynbid"));
					Ext.getCmp('slxx').body.update(htmlStrStart + '?rynbid='+ getRynbid(rynbid) +"&ryid=" + getRyid(ryid) +"&gmsfhm=" + getGmsfhm(gmsfhm)+ "&goto=querySlxx&hhnbid=" + getHhnbid(hhnbid) + "&hzywid=" + hzywid  + htmlStrEnd);
					
					this.getUpdater().refresh();
				}
			}
            
        },{
			id : "hgl",
//			url : "cx/hjjbxx/ckxx",
			//tabTip : "常口信息查询",
			title : "户关联",
//			html:' <iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'+'cx/hjjbxx/ckxx/queryXxxx?rynbid='+ getRynbid(rynbid) + "&goto=queryHgl&hhnbid=" + getHhnbid(hhnbid) + '"> </iframe>',
			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
				activate:function(){
					//alert(getQueryParam("rynbid"));
					Ext.getCmp('hgl').body.update(htmlStrStart + '?rynbid='+ getRynbid(rynbid) +"&ryid=" + getRyid(ryid) +"&gmsfhm=" + getGmsfhm(gmsfhm)+ "&goto=queryHgl&hhnbid=" + getHhnbid(hhnbid) + "&hzywid=" + hzywid  + htmlStrEnd);
					
					this.getUpdater().refresh();
				}
			}
            
        },{
			id : "hcy",
//			url : "cx/hjjbxx/ckxx",
			//tabTip : "常口信息查询",
			title : "户成员",
//			html:' <iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'+'cx/hjjbxx/ckxx/queryXxxx?rynbid='+ getRynbid(rynbid) + "&goto=queryHcy&hhnbid=" + getHhnbid(hhnbid) + '"> </iframe>',
			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
				activate:function(){
					//alert(getQueryParam("rynbid"));
					Ext.getCmp('hcy').body.update(htmlStrStart + '?rynbid='+ getRynbid(rynbid) +"&ryid=" + getRyid(ryid) +"&gmsfhm=" + getGmsfhm(gmsfhm)+ "&goto=queryHcy&hhnbid=" + getHhnbid(hhnbid) + "&hzywid=" + hzywid  + htmlStrEnd);
					
					this.getUpdater().refresh();
				}
			}
            
        },{
			id : "xzd",
//			url : "cx/hjjbxx/ckxx",
			//tabTip : "常口信息查询",
			title : "现住地",
//			html:' <iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'+'cx/hjjbxx/ckxx/queryXxxx?rynbid='+ getRynbid(rynbid) + "&goto=queryXzd&hhnbid=" + getHhnbid(hhnbid) + '"> </iframe>',
			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
				activate:function(){
					//alert(getQueryParam("rynbid"));
					Ext.getCmp('xzd').body.update(htmlStrStart + '?rynbid='+ getRynbid(rynbid) +"&ryid=" + getRyid(ryid) +"&gmsfhm=" + getGmsfhm(gmsfhm)+ "&goto=queryXzd&hhnbid=" + getHhnbid(hhnbid) + "&hzywid=" + hzywid  + htmlStrEnd);
					
					this.getUpdater().refresh();
				}
			}
            
        },{
			id : "dyxx",
//			url : "cx/hjjbxx/ckxx",
			//tabTip : "常口信息查询",
			title : "打印信息",
//			html:' <iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'+'cx/hjjbxx/ckxx/queryXxxx?rynbid='+ getRynbid(rynbid) + "&goto=queryDyxx&hhnbid=" + getHhnbid(hhnbid) + '"> </iframe>',
			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
				activate:function(){
					//alert(getQueryParam("rynbid"));
					Ext.getCmp('dyxx').body.update(htmlStrStart + '?rynbid='+ getRynbid(rynbid) +"&ryid=" + getRyid(ryid) +"&gmsfhm=" + getGmsfhm(gmsfhm)+ "&goto=queryDyxx&hhnbid=" + getHhnbid(hhnbid) + "&hzywid=" + hzywid  + htmlStrEnd);
					
					this.getUpdater().refresh();
				}
			}
        }]
    });
	
	
	function getRynbid(rynbid){
		
		if(rynbid != null){
			return rynbid;
		}else{
			return getQueryParam("rynbid");
		}
	}
	
	function getHhnbid(hhnbid){
		if(hhnbid != null){
			return hhnbid;
		}else{
			return getQueryParam("hhnbid");
		}
	}
	function getRyid(ryid){
		if(ryid != null){
			return ryid;
		}else{
			return getQueryParam("ryid");
		}
	}
	function getGmsfhm(gmsfhm){
		if(gmsfhm != null){
			return gmsfhm;
		}else{
			return getQueryParam("gmsfhm");
		}
	}
	var p3 = new Ext.Panel({
		layout:'border',
		items:[newForm20000,{
			id:"p3WestId",
        	region:'west',
        	width:180,
            border:false,
            frame:false,
            items: [{
            	id:"p3WestCenterId",
            	region:'center',
            	height:300,
                border:false,
                frame:false
            },{
    			region:'south',
    			layout:'table',
    			border:false,
                frame:false,
    			layoutConfig: {
    				columns: 1
    			},
    			items: [{
					id:'p3Img',
    		 		title: '',
    		 		height:'100%',
    		 		bodyStyle:'padding:10px 10px 10px 10px',
    				width:150,
    				height:180,
    				rowspan: 1,
    				colspan:1
				},{
    				html:'',
    				bodyStyle:'padding:10px 25px 0px 20px',
    				layout:'table',
    				align:'center',
    				border:false,
    				frame:false,
    				layoutConfig: {
    					columns: 1
    				},
    				items:[
    					new Ext.Button({
    						text:'所有照片',
    						id:'syzp',
    						minWidth:100,
    						handler:function(){
    							zpWindow.show();
    						}
    					}),{
    						height:3,
    						border:false,
    						frame:false
    					},new Ext.Button({
    						text:'不显示照片',
    						id:'bxszp',
    						minWidth:100,
    						handler:function(){
    							Ext.getCmp('p3Img').hide();
    							Ext.getCmp('syzp').hide();
    							Ext.getCmp('bxszp').hide();
    							Ext.getCmp('xszp').show();
    						}
    					}),{
    						height:3,
    						border:false,
    						frame:false
    					},new Ext.Button({
    						text:'显示照片',
    						id:'xszp',
    						hidden:true,
    						minWidth:100,
    						handler:function(){
    							Ext.getCmp('p3Img').show();
    							Ext.getCmp('syzp').show();
    							Ext.getCmp('xszp').hide();
    							Ext.getCmp('bxszp').show();
    						}
    					})
    				]
    			}]
    		}]
		},{
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
        	items:[p1,p2,tabs,p3]
        }
    });
	
    if(getQueryParam("ai")){
    	Ext.getCmp('card').getLayout().setActiveItem(getQueryParam("ai"));
    	tabs.setActiveTab(0);
    }else{
    	Ext.getCmp('nextId').setVisible(false);
    }
	if(getQueryParam("jumpToThrycx")&& getQueryParam("jumpToThrycx")!=""){
		form20000.getForm().findField("hhnbid").setValue(getQueryParam("hhnbid"));
		
		Ext.getCmp("queryId").handler();
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
			
			var store = czrkGrid.store;
			
			store.removeAll();
			
			store.baseParams = data;
			
			store.load({params:{start:0, limit:20}});
			
			//改变颜色
    store.on('load',function(s,records){
    	var girdcount=0;
    	s.each(function(r){
    		
    		if(0 == r.get("ryzt")){
    			
    		}else if(1 == r.get("ryzt")){
    			czrkGrid.getView().getRow(girdcount).style.backgroundColor="red";
    		}else{
    			czrkGrid.getView().getRow(girdcount).style.backgroundColor="green";
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