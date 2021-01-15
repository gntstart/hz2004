var selectRynbid = null;
var selectbzslid=null;
var dkdyselRes = null;
var dkqdselRes = null;
var pageFlg = 1;
var selResData = null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10019,30041",function(){}))
		return;
	
	//本业务需要加载的字典
	//Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	
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
			rowclick:function(g, rowIndex, e){}
		}
	});
	
	var form30041 = new Gnt.ux.SjpzForm({
		pzlb: '30041',
		url: 'yw/queryRyxx',
		title:'',
		cols:2,
		formType:'query'
	});

	var p1_1 = new Gnt.ux.ZdycxPanel({
		pzlb:form30041.pzlb,
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
						html: '<div class="text" style="text-align:center;">受理信息查询</div>',
						height: 80,
						region:'north',
						bodyStyle:'padding:15px'
					},
					form30041
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
//				id:"p1_1Id",
//				region:'center',
//				border:false,
//				frame: false,
//				height:600,
//				items:[p1_1]
//			},
//			{
//				id:"centerId",
//				border:false,
//				frame: false,
//				region:'center',
//				layout:'border',
////	        	height: 300,
//				items:[{
//						id: "panelHtmlId" ,
//						html: '<div class="text" style="text-align:center;">受理信息查询</div>',
//						height: 80,
//						region:'north',
//						bodyStyle:'padding:15px'
//					},
//					form30041
//				]
//			},
			{
				id:"southId",
	        	region:'south',
	        	height:50,
	            border:false,
	            frame:false,
		        region:'south',
			    bodyStyle:'padding:10px',
		        layout:'table',
				layoutConfig: {
		        	columns: 15
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
		    		    	id:'zhId',
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
			    	    	form:form30041,
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
		    		    	id:'c11',
			        		xtype:'checkbox',
			        		boxLabel : "显示记录总数",  
			        		name : "jlzs",  
			        		inputValue : "1"  
			        	}]
		    		},*/{
		    			frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
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
		        				var data = form30041.getForm().getValues();
								if(Gnt.util.isEmpty(data)){
									showInfo("至少输入一个查询条件！");
									return;
								}
								//记录日志的操作码
								data.log_code = "F3201";
								Ext.Msg.wait("正在执行查询，请稍后...");
								var ary = p1_1.xszdGrid.store.data.items;
								var pxary1 = p1_1.pxzdGrid.store.data.items;//length 长度;
								if(p1_1.qybz){
									dkdyGrid.reconfigure(
											dkdyGrid.store,new Ext.grid.ColumnModel(Gnt.ZdyColumn(ary)));
									dkdyGrid.pxary = pxary;
								};
								dkdyGrid.zdyValueKey = Gnt.zdyValueKey(ary);
								var header=[];
								var shuxing = [];
								var zdyValueKey = Gnt.zdyValueKey(ary);
								dkdyGrid.type = 'ydzslcx';
								var dkdystore = dkdyGrid.store;
								dkdystore.removeAll();
								dkdystore.baseParams = data;
								var config=dkdyGrid.colModel.config;
								Ext.each(config,function(r){
									header.push(r.header);
									shuxing.push(r.dataIndex);
								});
								dkdystore.baseParams.header = encodeURI(header);
								dkdystore.baseParams.shuxing = encodeURI(shuxing);
								dkdystore.baseParams.zdyValueKey = Ext.encode(zdyValueKey);
								dkdystore.baseParams.daochuFlag = 'ydzslcx';
								dkdystore.load({params:{start:0, limit:20}});
								Ext.each(pxary1, function(pxObj){
									dkdystore.sort(pxObj.data.fieldname, 'ASC');
		    					});
		        				//Ext.getCmp('card').getLayout().setActiveItem(1);
		        				dkdystore.on('load',function(s,records){
		        					if(records.length>0){
		        						dkdyGrid.fireEvent("rowclick",dkdyGrid,0);
			        					dkdyGrid.getSelectionModel().selectRange(0,0);
		        					}
		        				});
//		        				var ary = p1_1.xszdGrid.store.data.items;
//								var pxary2 = p1_1.pxzdGrid.store.data.items;//length 长度;
//								if(p1_1.qybz){
//									dkqdGrid.reconfigure(
//											dkqdGrid.store,new Ext.grid.ColumnModel(Gnt.ZdyColumn(ary)));
//									dkqdGrid.pxary = pxary;
//								};
//								dkqdGrid.zdyValueKey = Gnt.zdyValueKey(ary);
//		        				var dkqdstore = dkqdGrid.store;
//		        				dkqdstore.removeAll();
//		        				dkqdstore.baseParams = data;
//		        				dkqdstore.load({params:{start:0, limit:20}});
//		        				Ext.each(pxary2, function(pxObj){
//		        					dkqdstore.sort(pxObj.data.fieldname, 'ASC');
//		    					});
//		        				dkdystore.on('load',function(s,records){
//		        					if(records.length>0){
//		        						dkqdGrid.fireEvent("rowclick",dkqdGrid,0);
//		        						dkqdGrid.getSelectionModel().selectRange(0,0);
//		        					}
//		        				});
		        				//Ext.Msg.hide();
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
	var dkdyGrid = new Gnt.ux.SjpzGrid({
		pkname: 'bzslid',
		pzlb: '30041',
		url:'cx/ydzzjxx/ydzslcx/getYdzslxx.json',
		region:'center',
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
				dkdyselRes = g.store.getAt(rowIndex);
//				dkdyselRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				selectRynbid = dkdyselRes.data.rynbid;
				var store = czrkGrid.store;
				store.removeAll();
				store.baseParams = {
						rynbid:selectRynbid,
//						ryzl1:'1',
						start:0,
						limit:20
					};
				store.load({params:{start:0, limit:20}});
				store.on('load',function(s,records){
					if(records.length>0){
						selResData = store.getAt(0).data;
					}
				});
			},
			rowdblclick:function(g, rowIndex, e){
				dkdyselRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				pageFlg  = 1;
				selectRynbid = dkdyselRes.data.rynbid;
				var store = czrkGrid.store;
				store.removeAll();
				store.baseParams = {
						rynbid:selectRynbid,
//						ryzl1:'1',
						start:0,
						limit:20
					};
				store.load({params:{start:0, limit:20}});
				store.on('load',function(s,records){
					if(records.length>0){
						selResData = store.getAt(0).data;
					}
				});
				var pkvalue = dkdyselRes.data[zzxxForm.bindStore.pkname];
				var re = zzxxForm.bindStore.getById(pkvalue);
				if(re){
					zzxxForm.getForm().loadRecord(re);
				}
				Gnt.toFormReadyonly(zzxxForm);
    			Ext.getCmp('card').getLayout().setActiveItem(2);
    			p4.doLayout();
			}
		}
	});
	
	var czrkGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		pzlb: '20000',
		url:'cx/hjjbxx/ckxx/getRkxx.json',
		region:'north',
		height:350,
		title: '',
		showPaging:true,
		listeners:{
			rowclick:function(g, rowIndex, e){
				
			},
			rowdblclick:function(grid,row){
				
			}
		}
	});
	
	var zhcx_dialog = new Gnt.ux.ZhcxDialog({
		pzlb : form30041.pzlb,
		grid:dkdyGrid,
		callback: function(where){
			var ary = p1_1.xszdGrid.store.data.items;
			var pxary1 = p1_1.pxzdGrid.store.data.items;//length 长度;
			if(p1_1.qybz){
				dkdyGrid.reconfigure(
						dkdyGrid.store,new Ext.grid.ColumnModel(Gnt.ZdyColumn(ary)));
				dkdyGrid.pxary = pxary;
			};
			dkdyGrid.zdyValueKey = Gnt.zdyValueKey(ary);
			var grid = this.grid;
			var store = grid.store;
			var length = 0;
			var data = {"where": where};
			store.removeAll();
			data.pzlb = grid.store.pzlb;
			data.config_key = this.config_key;
			//记录日志的操作码
			data.log_code = "F3201";

			//add by zjm 20201223 复杂查询传入列表头 属性 自定义字段，用于导出功能使用
			var header=[];
			var shuxing = [];
			var zdyValueKey = Gnt.zdyValueKey(ary);
			grid.type = 'ydzslcx';
			Ext.each(ary,function(r){
				header.push(r.data.displayname);
				shuxing.push(r.data.fieldname);
			});
			data.header = encodeURI(header);
			data.shuxing = encodeURI(shuxing);
			data.zdyValueKey = Ext.encode(zdyValueKey);
			data.daochuFlag = 'ydzslcx';
			
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
			
			var store1 = dkqdGrid.store;
			var data = {"where": where};
			store1.removeAll();
			store1.baseParams = data;
			store1.load({params:{start:0, limit:grid.store.pageSize}});
			store1.on("load",function(store) {  
				if(store.data.length > 0){
					curIndex = 0;
					dkqdGrid.fireEvent("rowclick",dkqdGrid,curIndex);
					dkqdGrid.getSelectionModel().selectRange(curIndex,curIndex);
					Ext.getCmp('card').getLayout().setActiveItem(1);
				}else{
				}
			},dkqdGrid); 
		}
	});
//	var dkqdGrid = new Gnt.ux.SjpzGrid({
//		pkname: 'bzslid',
//		pzlb: '30041',
//		url:'cx/ydzzjxx/ydzslcx/getYdzslxx.json',
//		region:'center',
//		title: '',
//		dcFlag:true,
//		showPaging:true,
//		loadCallback: function(res, store, bind_grid){
//			Ext.Msg.hide();
//			if(res.length>0){
//				Ext.getCmp('card').getLayout().setActiveItem(1);
//			}else{
//				showInfo("未查到有关信息！");
//			}
//		},
//		listeners:{
//			rowclick:function(g, rowIndex, e){
//				dkqdselRes = g.store.getAt(rowIndex);
//				dkdyselRes = g.store.getAt(rowIndex);
//				curIndex = rowIndex;
//				selectRynbid = dkqdselRes.data.rynbid;
//			},
//			rowdblclick:function(g, rowIndex, e){
//				dkqdselRes = g.store.getAt(rowIndex);
//				curIndex = rowIndex;
//				pageFlg = 2 ;
//				selectRynbid = dkdyselRes.data.rynbid;
//				var pkvalue = dkqdselRes.data[zzxxForm.bindStore.pkname];
//				var re = zzxxForm.bindStore.getById(pkvalue);
//				if(re){
//					zzxxForm.getForm().loadRecord(re);
//				}
//    			Ext.getCmp('card').getLayout().setActiveItem(3);
//    			p4.doLayout();
//			}
//		}
//	});	
	var dkckdy_window=new Gnt.ux.dkckdyForm({
		callback: function(){
		
		}
	});	
	var p2_3 = new Ext.Panel({
		xtype: 'panel',
		border:false,
		frame: false,
    	region:'south',
    	height: 80,
    	bodyStyle: 'padding:5px',
    	layout:'column',
    	layoutConfig: {
    		border:false,
			frame:false,
			columnWidth:0.5
	 	},
    	items:[{
    		border:false,
    		frame: false,
    		columnWidth:0.5,
    		bodyStyle: 'padding:5px',
    		layout:'table',
    		layoutConfig: {
    			bodyStyle: 'padding:5px',
	    	 	columns: 4
	    	},
            items:[{
				frame:false,
				border:false,
				xtype:'panel',
				html:'打印纸张宽度:',
				width:110
    		}, {
				xtype:'textfield',
				width: 100,
				id:'pageWidth',
				value:'800'
			},{
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
    		},
    		{
            	xtype:'checkbox',
            	boxLabel: '套打底卡',
            	name:'tddk',
            	id:'tddk',
            	checked : true,
            	listeners:{
            		'check':function (ck, checked){
            			if (checked) {
            			}else{
            				
            			}
            		}
            	}
            },{
				frame:false,
				border:false,
				xtype:'panel',
				html:'打印纸张高度:',
				width:110
    		}, {
				xtype:'textfield',
				width: 100,
				id:'pageHeight',
				value:'600'
			},{
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
    		},
    		{
            	xtype:'checkbox',
            	boxLabel: '住址折行',
            	name:'zzzh',
            	id:'zzzh',
            	checked : true,
            	listeners:{
            		'check':function (ck, checked){
            			if (checked) {
            			}else{
            				
            			}
            		}
            	}
            },{
            	frame:false,
				border:false,
				xtype:'panel',
				width: 200,
            	html:'连续打印推荐设置2500*1300'
            }]
    	},{
    		layout:'table',
    		columnWidth:0.5,
    		frame:false,
			border:false,
    		layoutConfig: {
    			frame:false,
				border:false,
            	columns: 10
            },
            items:[{
            	items:[
 		           new Ext.Button({
 		        	   text:'底卡打印',
							minWidth:80,
							handler:function(){
								if(dkdyselRes){
									var arrayTemp=[];
									var o={};
									if(Ext.getCmp("tddk").getValue()){
										o.directTable="dkdyzd";
									}else{
										o.directTable="dkdytd";
									}
									if(Ext.getCmp("pageHeight").getValue()){
										o.pageHeight=Ext.getCmp("pageHeight").getValue();
									}
									if(Ext.getCmp("pageWidth").getValue()){
										o.pageWidth=Ext.getCmp("pageWidth").getValue();
									}
									if(Ext.getCmp("zzzh").getValue()){
										o.zzzh=true;
									}else{
										o.zzzh=false;
									}
									o.bzslid =dkdyselRes.data.bzslid;
									arrayTemp.push(o);
									printfunction(0,arrayTemp);
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
            	items:[
  		           new Ext.Button({
  		        	   text:'删除信息',
 							minWidth:80,
 							handler:function(){
 			                    if(dkdyselRes){
 									if(confirm("是否确定删除【"+dkdyselRes.data.gmsfhm+","+dkdyselRes.data.xm+"】的办证信息？")){
 										selectbzslid = dkdyselRes.data.bzslid;
 										log_code = "F3104";
 										Gnt.submit(
 												{
 													bzslid:selectbzslid}, 
 													"cx/ydzzjxx/ydzslcx/delBbzslid", 
 													"正在删除办证信息，请稍后...", 
 													function(jsonData,params){
 														showInfo("办证信息删除成功！");
 														dkqdGrid.store.reload(); 
 														dkdyGrid.store.reload(); 
 													}
 												);
 									}
 								}else{
									alert("请选中一条数据再操作！");
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
            	items:[
  		           new Ext.Button({
  		        	   text:'底卡清单',
 							minWidth:80,
 							handler:function(){
 								//Ext.getCmp('card').getLayout().setActiveItem(2);
 								var data=dkdyGrid.store.data.items;
 								dkckdy_window.show();
 								dkckdy_window.setSelRes(data,"dkqd");
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
            	items:[
  		           new Ext.Button({
  		        	   text:'关闭',
 							minWidth:80,
 							handler:function(){
 								window.parent.parent.closeWorkSpace();
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
            	items:[
  		           new Ext.Button({
  		        	   text:'返回',
 							minWidth:80,
 							handler:function(){
 								Ext.getCmp('card').getLayout().setActiveItem(0);
 							}
  		           })
  		      ]
             }
    	]}]
    });
	var p2 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[
			dkdyGrid,p2_3
			]
	});
	

	var zzxxForm = new Gnt.ux.SjpzForm({
		closable: false,
		/*region:'north',*/
		height:500,
		pzlb: '30041',
		labelWidth : 120,
		cols:2,
//		bindStore:dkdyGrid.store,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:dkdyGrid,
		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
		changeDictCust:function(cmb,res){
			
			return;
		},
		title: ''
	});
	var p3_3 = new Ext.Panel({
		xtype: 'panel',
		border:false,
		frame: false,
    	region:'south',
    	height: 80,
    	bodyStyle: 'padding:5px',
    	layout:'column',
    	layoutConfig: {
    		border:false,
			frame:false,
			columnWidth:0.5
	 	},
    	items:[{
    		border:false,
    		frame: false,
    		columnWidth:0.5,
    		bodyStyle: 'padding:5px',
    		layout:'table',
    		layoutConfig: {
    			bodyStyle: 'padding:5px',
	    	 	columns: 4
	    	},
            items:[{
				frame:false,
				border:false,
				xtype:'panel',
				html:'打印纸张宽度:',
				width:110
    		}, {
				xtype:'textfield',
				width: 100,
				hiddenName:'pageWidth'
			},{
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
    		},
    		{
            	xtype:'checkbox',
            	boxLabel: '套打底卡',
            	name:'tddk',
            	checked : true,
            	listeners:{
            		'check':function (ck, checked){
            			if (checked) {
            			}else{
            				
            			}
            		}
            	}
            },{
				frame:false,
				border:false,
				xtype:'panel',
				html:'打印纸张高度:',
				width:110
    		}, {
				xtype:'textfield',
				width: 100,
				hiddenName:'pageHeight'
			},{
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
    		},
    		{
            	xtype:'checkbox',
            	boxLabel: '住址折行',
            	name:'zzzh',
            	checked : true,
            	listeners:{
            		'check':function (ck, checked){
            			if (checked) {
            			}else{
            				
            			}
            		}
            	}
            },{
            	frame:false,
				border:false,
				xtype:'panel',
				width: 200,
            	html:'连续打印推荐设置2500*1300'
            }]
    	},{
    		layout:'table',
    		columnWidth:0.5,
    		frame:false,
			border:false,
    		layoutConfig: {
    			frame:false,
				border:false,
            	columns: 10
            },
            items:[{
            	items:[
 		           new Ext.Button({
 		        	   text:'底卡打印',
							minWidth:80,
							handler:function(){
								Ext.getCmp('card').getLayout().setActiveItem(1);
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
            	items:[
  		           new Ext.Button({
  		        	   text:'删除信息',
 							minWidth:80,
 							handler:function(){
 			                    if(dkqdselRes){
 									if(confirm("是否确定删除【"+dkqdselRes.data.gmsfhm+","+dkqdselRes.data.xm+"】的办证信息？")){
 										selectbzslid = dkqdselRes.data.bzslid;
 										Gnt.submit(
 												{
 													bzslid:selectbzslid}, 
 													"cx/ydzzjxx/ydzslcx/delBbzslid", 
 													"正在删除办证信息，请稍后...", 
 													function(jsonData,params){
 														showInfo("办证信息删除成功！");
 														dkqdGrid.store.reload(); 
 														dkdyGrid.store.reload(); 
 													}
 										);
 									}
 								}else{
 									alert("请选中一条数据！");
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
            	items:[
  		           new Ext.Button({
  		        	   text:'底卡清单',
 							minWidth:80,
 							handler:function(){
 								var data=dkqdGrid.store.data.items;
 								dkckdy_window.show();
 								dkckdy_window.setSelRes(data,"dkqd");
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
            	items:[
  		           new Ext.Button({
  		        	   text:'关闭',
 							minWidth:80,
 							handler:function(){
 								window.parent.parent.closeWorkSpace();
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
            	items:[
  		           new Ext.Button({
  		        	   text:'返回',
 							minWidth:80,
 							handler:function(){
 								Ext.getCmp('card').getLayout().setActiveItem(0);
 							}
  		           })
  		      ]
             }
    	]}]
    });
//	var p3 = new Ext.Panel({
//		layout:'border',
//		newYewu: function(){
//			
//		},
//		items:[
//			dkqdGrid,p3_3
//			]
//	});	
	var p4 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[
			{
				region:'north',
				border:false,
				frame: false,
	        	height: 40,
	        	bodyStyle: 'padding:10px 0px 0px 0px ',
				layout:'column',
		    	items: [
					{
		    			columnWidth: .01,
						border:false,
						frame: false
		    		},{
		    			columnWidth: .01,
						border:false,
						frame: false
		    		},{
		    			columnWidth: .01,
						border:false,
						frame: false
		    		}
		    		 
		    	]
				
			},{
				id:"p3centerId",
				border:false,
				frame: false,
				region:'center',
				items:[zzxxForm]
			},{
				id:"southId",
	        	region:'south',
	        	height:40,
	            border:false,
	            frame:false,
	            items:[{
					border:false,
					frame: false,
		        	region:'south',
		        	height: 40,
		        	bodyStyle: 'padding:10px 0px 0px 0px ',
					layout:'table',
			    	items: [
						{
			    			width:20,
							border:false,
							frame: false
			    		},{
							border:false,
							frame: false,
			    		    items:[new Ext.Button({
			        			text:'常口信息',
			        			minWidth:60,
			        			handler:function(){
			        				czr={
											ryid:selResData.ryid
									}
									Gnt.toRyxx(czr);
			        			}
			        		})]
			    		},{
			    			width:10,
							border:false,
							frame: false
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
			    			width:10,
							border:false,
							frame: false
			    		},{
							border:false,
							frame: false,
			    		    items:[new Ext.Button({
			        			text:'返回',
			        			minWidth:60,
			        			handler:function(){
			        				if(pageFlg==1){
			        					Ext.getCmp('card').getLayout().setActiveItem(1);
			        				}else if(pageFlg == 2){
			        					Ext.getCmp('card').getLayout().setActiveItem(2);
			        				}
			        				
			        			}
			        		})]
			    		}
			    		 
			    	]
					
				}]
	        }
		]
	});
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
        	items:[p1,p2/*,p3*/,p4]
        }
    });
});