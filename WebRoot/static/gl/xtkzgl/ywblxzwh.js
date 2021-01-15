var qybz = 1;
var queryFlag = null;
var rynbid = null;
var hideFlag = true;
var xPosition=0;
var selectedSelRes=null;
var selectedxzxxSelRes = null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	var sfbz = [[1,'限制类型'],[2,'类型名称']]
	
	var csm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var colModel = new Ext.grid.ColumnModel([
		{
			header: "限制信息id",
	        dataIndex: "xzxxid",
	        sortable: true,
	        hidden:true,
			width: 120
		},{
			header: "限制名称",
	        dataIndex: "xzmc",
	        sortable: true,
			width: 120
		},{
			header: "限制表达式",
	        dataIndex: "xzbds",
	        sortable: true,
			width: 120
		},{
	        header: "创建人姓名",
	        dataIndex: "cjrxm",
	        sortable: true,
			width: 120
	    },{
	        header: "创建时间",
	        dataIndex: "cjsj",
	        sortable: true,
			width: 120,
			renderer:function(value){
				if(value&&value.length == 14){
					return value.substr(0,4)+"-"+value.substr(4,2)+"-"+value.substr(6,2)+" "+value.substr(8,2)+":"+value.substr(10,2)+":"+value.substr(12,2)
				}
				return value;
			}
	    },{
	        header: "修改人姓名",
	        dataIndex: "xgrxm",	
	        sortable: true,
			width:120
	    },{
	        header: "修改时间",
	        dataIndex: "xgsj",	
	        sortable: true,
			width: 120,
			renderer:function(value){
				if(value&&value.length == 14){
					return value.substr(0,4)+"-"+value.substr(4,2)+"-"+value.substr(6,2)+" "+value.substr(8,2)+":"+value.substr(10,2)+":"+value.substr(12,2)
				}
				return value;
			}
	    },{
	        header: "审批模板id",
	        dataIndex: "spmbid",	
	        sortable: true,
	        hidden:true,
			width:120
	    },{
	        header: "审批模板名称",
	        dataIndex: "mbmc",	
	        sortable: true,
			width:120
	    },{
	        header: "启用标志",
	        dataIndex: "qybz",	
	        sortable: true,
			width: 80
	    },{
	        header: "限制状态",
	        dataIndex: "xzzt",	
	        sortable: true,
			width: 80
	    }
	]);
 	var myuserStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'gl/xtkzgl/ywblxzwh/getYwblxzwhInfo',
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        totalProperty:'totalCount',
        fields: [
        	"xzxxid",
			"xzmc",
			"xzbds",
			"cjrxm",
			"cjsj",
			"xgrxm",
			"xgsj",
			"spmbid",
			"mbmc",
			"qybz",
			"xzzt"
        ],
        listeners:{
			loadexception:function(mystore,options,response,error){
				if(error){
					var json = Ext.decode(response.responseText);
					Ext.Msg.alert("提示",json.messages[0]);
				}else{
					Ext.Msg.alert("提示",response.responseText);
				}
			}
        }
    });
	var xzxxGrid = new Ext.grid.GridPanel({
        store: myuserStore,
        region: 'center',
        view:new Ext.grid.GridView({
				//forceFit:true,
				//autoFill:true,
				enableRowBody:true
		}),
		stripeRows: true,
        cm: colModel,
        sm:csm,
		loadMask: {msg:'正在加载数据，请稍侯……'},
		//bodyStyle:'width:50%',
        border:true,
        frame:false,
        //anchor:'50% 50%',
	    margins: '0 0 0 0',
		cmargins: '0 0 0 0',        
		iconCls:'icon-grid',
        title:'',
        listeners:{
        	rowclick:function(g, rowIndex, e){
        		selectedxzxxSelRes = g.store.getAt(rowIndex);
        	},
			rowdblclick:function(g, rowIndex, e){
				selectedxzxxSelRes = g.store.getAt(rowIndex);
			}
		},
        bbar: new Ext.PagingToolbar({
				pageSize: 20,
				store: myuserStore,
				displayInfo: true
		})
    });
	
	
	var xzlxcsm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var xzlxcolModel = new Ext.grid.ColumnModel([
		{
			header: "限制类型",
	        dataIndex: "dm",
	        sortable: true,
			width: 100
		},{
			header: "类型名称",
	        dataIndex: "mc",
	        sortable: true,
			width: 150
		}
	   
	]);
 	var xzlxStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'gl/xtkzgl/ywblxzwh/getXtywbllxInfo',
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        totalProperty:'totalCount',
        fields: [
        	"dm",
			"mc"
        ],
        listeners:{
			loadexception:function(mystore,options,response,error){
				if(error){
					var json = Ext.decode(response.responseText);
					Ext.Msg.alert("提示",json.messages[0]);
				}else{
					Ext.Msg.alert("提示",response.responseText);
				}
			}
        }
    });	
	var xzlxGrid = new Ext.grid.GridPanel({
        store: xzlxStore,
        region: 'center',
        width:300,
		stripeRows: true,
        cm: xzlxcolModel,
        sm:xzlxcsm,
		loadMask: {msg:'正在加载数据，请稍侯……'},
//		bodyStyle:'width:80%',
        border:true,
        frame:true,
//        anchor:'100% 100%',
//	    margins: '0 0 0 0',
//		cmargins: '0 0 0 0',
		iconCls:'icon-grid',
        title:'',
        listeners:{
        	rowclick:function(g, rowIndex, e){
        		selectedSelRes = g.store.getAt(rowIndex);
        		var store = xzxxGrid.store;
        		store.removeAll();
        		selectedxzxxSelRes = null;
        		store.baseParams = {
        			xzywlx:selectedSelRes.data.dm,
        			qybz:qybz
        		};
        		xPosition = xzxxGrid.x;
        		store.load({params:{start:0, limit:20}});	
        		store.on("load",function(store) {  
        			if(store.data.length > 0){
        				xzxxGrid.fireEvent("rowclick",xzxxGrid,0);
        				xzxxGrid.getSelectionModel().selectRange(0,0);
        				
        			}
        		},xzxxGrid);         		
        	},
			rowdblclick:function(g, rowIndex, e){
        		selectedSelRes = g.store.getAt(rowIndex);
        		var store = xzxxGrid.store;
        		store.removeAll();
        		selectedxzxxSelRes = null;
        		store.baseParams = {
        			xzywlx:selectedSelRes.data.dm,
        			qybz:qybz
        		};
        		xPosition = xzxxGrid.x;
        		store.load({params:{start:0, limit:20}});	
        		store.on("load",function(store) {  
        			if(store.data.length > 0){
        				xzxxGrid.fireEvent("rowclick",xzxxGrid,0);
        				xzxxGrid.getSelectionModel().selectRange(0,0);
        				
        			}
        		},xzxxGrid);         		
        	}
		}/*,
        bbar: new Ext.PagingToolbar({
			pageSize: 9999,
			store: xzlxStore,
			displayInfo: true
	})*/
    });	
	
	var mbcsm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var mbcolModel = new Ext.grid.ColumnModel([
		{
			header: "模板id",
	        dataIndex: "spmbid",
	        sortable: true,
	        hidden:true,
			width: 120
		},{
			header: "模板名称",
	        dataIndex: "mbmc",
	        sortable: true,
//	        hidden:true,
			width: 120
		},{
			header: "模板等级",
	        dataIndex: "mbdj",
	        sortable: true,
	        hidden:true,
			width: 120
		},{
			header: "模板等级",
	        dataIndex: "mbdjmc",
	        sortable: true,
//	        hidden:true,
			width: 120
		},{
			header: "创建人姓名",
	        dataIndex: "cjryhxm",
	        sortable: true,
			width: 120
		},{
	        header: "创建时间",
	        dataIndex: "cjsj",
	        sortable: true,
			width: 120
	    },{
	        header: "修改人姓名",
	        dataIndex: "xgryhxm",
	        sortable: true,
			width: 120
	    },{
	        header: "修改时间",
	        dataIndex: "xgsj",	
	        sortable: true,
			width:120
	    },{
	        header: "当前使用数",
	        dataIndex: "dqsys",
	        sortable: true,
			width: 120
	    },{
	        header: "启用标志",
	        dataIndex: "qybzmc",	
	        sortable: true,
			width:120
	    }
	]);
 	var mbmyuserStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'gl/xtkzgl/splwh/getSpmbInfo',//yw/hjyw/hzywcl/queryHzywcl
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        id:'id',
        totalProperty:'totalCount',
        fields: [
        	"spmbid",
        	"mbmc",
        	"mbdj",
        	"mbdjmc",
			"cjryhxm",
			"cjsj",
			"xgryhxm",
			"xgsj",
			"dqsys",
			"qybzmc"
        ],
        listeners:{
			loadexception:function(mystore,options,response,error){
				if(error){
					var json = Ext.decode(response.responseText);
					Ext.Msg.alert("提示",json.messages[0]);
				}else{
					Ext.Msg.alert("提示",response.responseText);
				}
			}
        }
    });	
	var mbGrid = new Ext.grid.GridPanel({
        store: mbmyuserStore,
        region: 'center',
        view:new Ext.grid.GridView({
				//forceFit:true,
				//autoFill:true,
				enableRowBody:true
		}),
		stripeRows: true,
        cm: mbcolModel,
        sm:mbcsm,
        autoHeight:true,
		loadMask: {msg:'正在加载数据，请稍侯……'},
		bodyStyle:'width:100% ',
        border:true,
        anchor:'50% 50%',
	    margins: '0 0 0 0',
		cmargins: '0 0 0 0',        
        frame:false,
		iconCls:'icon-grid',
        title:'',
        listeners:{
        	rowclick:function(g, rowIndex, e){
        		
        	},
			rowdblclick:function(g, rowIndex, e){
				
			}
		},
        bbar: new Ext.PagingToolbar({
				pageSize: 999,
				store: mbmyuserStore,
				displayInfo: true
		})
    });
	var mbstore = mbGrid.store;
	mbstore.baseParams = {
			qybz:1,
			start:0,
			limit:999
		};
	mbstore.load();
	var modifyywblxz_window = new Gnt.ux.SelectYwblxzModify({
		//选择立户信息回调
		callback: function(optype, ywblxzModify){
			//提交数据
			Gnt.submit(
					ywblxzModify, 
					"gl/xtkzgl/ywblxzwh/modifyYwblxzwhInfo", 
					"正在修改业务办理限制信息，请稍后...", 
					function(jsonData,params){
						showInfo("业务办理限制信息修改成功！");
						xzxxGrid.store.reload(); 
					}
			);
		}
	});
	var addywblxz_window = new Gnt.ux.SelectYwblxzAdd({
		//选择立户信息回调
		callback: function(optype, ywblxzAdd){
			//提交数据
			Gnt.submit(
					ywblxzAdd, 
					"gl/xtkzgl/ywblxzwh/addYwblxzwhInfo", 
					"正在增加业务办理限制信息，请稍后...", 
					function(jsonData,params){
						showInfo("业务办理限制信息增加成功！");
						xzxxGrid.store.reload(); 
					}
			);
		}
	});
	var p1_1 = new Ext.Panel({
		xtype: 'panel',
		border:false,
		frame: false,
    	region:'north',
    	height: 35,
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
				items:[new Ext.Button({
            		text:'显示/隐藏',
					minWidth:80,
					handler:function(){
						if(hideFlag){
							Ext.getCmp('weastPart').hide();
							xzxxGrid.setPosition(0,xzxxGrid.y);
						}else{
							Ext.getCmp('weastPart').show();
							xzxxGrid.setPosition(xPosition,xzxxGrid.y);
						}
						hideFlag = !hideFlag;
					}
            	})]
			},{
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
    		},{
    			frame:false,
				border:false,
				items:[new Ext.Button({
            		text:'增加限制',
            		id:'addBtn',
					minWidth:80,
					handler:function(){
						addywblxz_window.show();
						addywblxz_window.setSelRes(selectedSelRes,mbstore);
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
            		text:'修改限制',
            		id:'modifyBtn',
					minWidth:80,
					handler:function(){
						if(selectedxzxxSelRes){
							modifyywblxz_window.show(selectedxzxxSelRes);
							modifyywblxz_window.setSelRes(selectedSelRes,selectedxzxxSelRes,mbstore);
						}else{
							alert("当前没有可以修改的数据！");
						}
						
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
            		text:'恢复限制',
            		id:'resumeBtn',
            		hidden:true,
					minWidth:80,
					handler:function(){
						Gnt.submit(
								{
									xzxxid:selectedxzxxSelRes.data.xzxxid,
									qybz:1}, 
									"gl/xtkzgl/ywblxzwh/resumeYwblxzwhInfo", 
									"正在 恢复业务办理限制，请稍后...", 
									function(jsonData,params){
										showInfo("业务办理限制恢复成功！");
										selectedxzxxSelRes = null;
										xzxxGrid.store.reload(); 
								}
							);
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
					text:'删除限制',
					id:'delBtn',
					minWidth:80,
					handler:function(){
						if(selectedxzxxSelRes){
							if(window.confirm('是否确定要删除【'+selectedxzxxSelRes.data.xzmc+'】?')){
								Gnt.submit(
										{
											xzxxid:selectedxzxxSelRes.data.xzxxid}, 
											"gl/xtkzgl/ywblxzwh/delYwblxzwhInfo", 
											"正在删除业务办理限制，请稍后...", 
											function(jsonData,params){
												showInfo("业务办理限制删除成功！");
												xzxxGrid.store.reload(); 
											}
								);
							}
						}else{
							alert("请选中一条有效的数据，再点击修改！");
						}
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
					text:'关闭窗体',
					minWidth:80,
					handler:function(){
						window.parent.closeWorkSpace();
					}
				})]
    		}]
    });
	var p1 = new Ext.Panel({
		layout:'border',
		items:[p1_1,{
			region: 'west',
			layout:'border',
			id:'weastPart',
			border:false,
            frame:false,
            width:350,
    	    margins: '0 0 0 0',
    		cmargins: '0 0 0 0',  
    		title:'',
    		items:[{
    			region:'north',
            	height:60,
    			border:false,
				frame: false,
    			items:[{
    				autoHeight: true,
		            layout:'column',
		            bodyStyle: 'padding:20px 0px 0px 0px ',
    	            items: [
    	            	{
    	            		columnWidth: .05,
							border:false,
							frame: false
						},{
							border:false,
							frame: false,
							//	bodyStyle: 'padding:10px 0px 0px 0px ',
							columnWidth: .3,
							id:'queryFlag',
				    		xtype : "combo",
				    		store : sfbz,
				    		value:'1',
							fieldLabel:"",
							triggerAction:"all",
							maxHeight : 80
						},{
							columnWidth: .05,
							border:false,
							frame: false
						},{
							id:'queryValue',
	            			xtype : 'textfield',
	            			columnWidth: .25,
	            			border:false,
	            			frame:false,
	            			fieldLabel:"",
	            			name:'queryValue'
						},{
							columnWidth: .05,
							border:false,
							frame: false
						},{
							border:false,
							frame: false,
							columnWidth: .25,
							bodyStyle: 'padding:0px 0px 0px 0px ',
							style:'margin-top:-27px',
							buttons:[
								new Ext.Button({
									text:'查询',
									minWidth:80,
									handler:function(){
										if(!Ext.getCmp('queryValue').getValue()){
											alert("请输入需要查找的相关信息标记!");
											return;
										}
										var store = xzlxGrid.store;
										if(store.data.length > 0){
											for(var i= 0;i<store.data.length;i++){
												var res = store.getAt(i);
												var b = Ext.getCmp('queryValue').getValue();
												if(Ext.getCmp('queryFlag').getValue()==1){//字段名称
													var a = res.data.dm;
													if(a.indexOf(b.toUpperCase())!=-1){
														xzlxGrid.fireEvent("rowclick",xzlxGrid,i);
														xzlxGrid.getSelectionModel().selectRange(i,i);
														xzlxGrid.getView().focusRow(i);
														return;
													}
												}else if(Ext.getCmp('queryFlag').getValue()==2){//字段含义
													var c = res.data.mc;
													if(c.indexOf(b.toUpperCase())!=-1){
														xzlxGrid.fireEvent("rowclick",xzlxGrid,i);
														xzlxGrid.getSelectionModel().selectRange(i,i);
														xzlxGrid.getView().focusRow(i);
														return;
													}
												}
											}
										}										
									}
								})
							]
						}
			        ]}]
    			
    		},{
    			width: 20,
				border:false,
				frame: false
    		},xzlxGrid,{
    			id:"p1SouthId",
            	region:'south',
            	height:60,
                border:false,
                frame:false,
                layoutConfig: {
		        	columns: 30
		        },
                items:[
					{
						columnWidth: .05,
						border:false,
						frame: false
					},{
	            	id:'c21',
	            	xtype:'checkbox',
	            	columnWidth: .95,
	            	boxLabel: '只显示不启用的记录',
	            	name:'qybz',
	            	inputValue : "0",
	            	listeners:{
	            		'check':function (ck, checked){
	            			if (checked) {
	            				qybz = 0;
	            				Ext.getCmp('resumeBtn').show();
	            				Ext.getCmp('addBtn').disable();
	            				Ext.getCmp('modifyBtn').hide();
	            				Ext.getCmp('delBtn').disable();
	            			}else{
	            				qybz = 1;
	            				Ext.getCmp('resumeBtn').hide();
	            				Ext.getCmp('addBtn').enable();
	            				Ext.getCmp('modifyBtn').show();
	            				Ext.getCmp('delBtn').enable();
	            			}
	            			var store = xzxxGrid.store;
            				store.baseParams = {
            						xzywlx:selectedSelRes.data.dm,
            						qybz:qybz
            					};
            				store.load({params:{start:0, limit:20}});
	            		}
	            	}
	            }
				]
    			
    		}
    		]
		},xzxxGrid
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
	var csstore = xzlxGrid.store;
	csstore.baseParams = {
			cslb:1030
	};
	csstore.load({params:{start:0, limit:9999}});	
	csstore.on("load",function(store) {  
		if(csstore.data.length > 0){
			curIndex = 0;
			xzlxGrid.fireEvent("rowclick",xzlxGrid,curIndex);
			xzlxGrid.getSelectionModel().selectRange(curIndex,curIndex);
			
		}
	},xzlxGrid); 	
});