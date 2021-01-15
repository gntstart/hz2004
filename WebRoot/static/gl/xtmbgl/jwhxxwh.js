var selectYw = 1;
var queryFlag = null;
var qybz = 1;
var jlxqybz = 1;
var selectedjwhdm =null;
var selectedSelRes=null;
var selectedJlxSelRes=null;
var param=null;
var length=0;;
Ext.onReady(function(){
	Ext.QuickTips.init();
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10019,20021",function(){}))
		return;
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});

	var csm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var colModel = new Ext.grid.ColumnModel([
		{
			header: "单位代码",
	        dataIndex: "dwdm",
	        sortable: true,
			width: 120
		},{
	        header: "单位名称",
	        dataIndex: "dwmc",
	        sortable: true,
			width: 120
	    },{
			header: "乡镇街道代码",
	        dataIndex: "xzjddm",
	        sortable: true,
			width: 120
		},{
			header: "乡镇街道名称",
	        dataIndex: "xzjdmc",
	        sortable: true,
			width: 120
		},{
			header: "居委会代码",
	        dataIndex: "dm",
	        sortable: true,
			width: 120
		},{
			header: "居委会名称",
	        dataIndex: "mc",
	        sortable: true,
			width: 120
		},{
	        header: "中文拼音",
	        dataIndex: "zwpy",
	        sortable: true,
			width: 120
	    },{
	        header: "五笔拼音",
	        dataIndex: "wbpy",	
	        sortable: true,
			width:120
	    },{
	        header: "备注",
	        dataIndex: "bz",	
	        sortable: true,
			width: 120,
			renderer:function(value){
				return value;
			}
	    },{
	        header: "启用标志",
	        dataIndex: "qybz",	
	        hidden:true,
	        sortable: true,
			width: 120,
			renderer:function(value){
				return value;
			}
	    },{
	        header: "启用标志",
	        dataIndex: "qybzmc",	
	        sortable: true,
			width: 120,
			renderer:function(value){
				return value;
			}
	    },{
	        header: "城乡分类代码",
	        dataIndex: "cxfldm",	
	        sortable: true,
			width: 120,
			renderer:function(value){
				return value;
			}
	    },{
	        header: "城乡分类名称",
	        dataIndex: "cxflmc",	
	        sortable: true,
			width: 120,
			renderer:function(value){
				return value;
			}
	    },{
	        header: "统计代码",
	        dataIndex: "tjdm",	
	        sortable: true,
			width: 120,
			renderer:function(value){
				return value;
			}
	    },{
	        header: "统计名称",
	        dataIndex: "tjmc",	
	        sortable: true,
			width: 120,
			renderer:function(value){
				return value;
			}
	    },{
	        header: "居委会新代码",
	        dataIndex: "xdm",	
	        sortable: true,
			width: 120,
			renderer:function(value){
				return value;
			}
	    },{
	        header: "居委会类型",
	        dataIndex: "dzys",	
	        sortable: true,
			width: 120,
			renderer:function(value){
				return value;
			}
	    },{
	        header: "居委会类型名称",
	        dataIndex: "dzysmc",	
	        sortable: true,
			width: 120,
			renderer:function(value){
				return value;
			}
	    },{
			header: "变动类型",
	        dataIndex: "bdlx",
	        sortable: true,
			width: 120,
			hidden:true
		},{
			header: "变动时间",
	        dataIndex: "bdsj",
	        sortable: true,
			width: 120,
			hidden:true
		}
	]);
 	var myuserStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'gl/xtmbgl/jwhxxwh/getJwhxxInfo',
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        totalProperty:'totalCount',
        fields: [
        	"dwdm",
			"dwmc",
			"xzjddm",
			"xzjdmc",
			"dm",
			"mc",
			"zwpy",
			"wbpy",
			"bz",
			"qybz",
			"qybzmc",
			"cxfldm",
			"cxflmc",
			"tjdm",
			"tjmc",
			"xdm",
			"dzys",
			"dzysmc",
			"bdlx",
			"bdsj"
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
 	
	var jwhxxGrid = new Ext.grid.GridPanel({
        store: myuserStore,
        region: 'north',
        view:new Ext.grid.GridView({
				//forceFit:true,
				//autoFill:true,
				enableRowBody:true
		}),
		stripeRows: true,
        cm: colModel,
        sm:csm,
        height:270,
		loadMask: {msg:'正在加载数据，请稍侯……'},
		bodyStyle:'width:100%',
        border:true,
        anchor:'100% 100%',
	    margins: '0 0 0 0',
		cmargins: '0 0 0 0',        
        frame:false,
		iconCls:'icon-grid',
        title:'',
        listeners:{
        	rowclick:function(g, rowIndex, e){
        		selectedSelRes = g.store.getAt(rowIndex);
				var jlxstore = jlxxxGrid.store;
				selectedjwhdm = selectedSelRes.data.dm;
//				alert(selectedSelRes.data.dm);
//				alert(qybz);
				jlxstore.baseParams = {
						jwhdm:selectedjwhdm,
						qybz:jlxqybz
				};
				param = jlxstore.baseParams;
				jlxstore.load({params:{start:0, limit:20}});
				jlxstore.on("load",function(store) { 
					length= jlxstore.totalLength;
					if(length > 0){
						jlxxxGrid.fireEvent("rowclick",jlxxxGrid,curIndex);
						jlxxxGrid.getSelectionModel().selectRange(curIndex,curIndex);
					}
					
				},jlxxxGrid);
        	},
			rowdblclick:function(g, rowIndex, e){
				selectedSelRes = g.store.getAt(rowIndex);
				var jlxstore = jlxxxGrid.store;
				selectedjwhdm = selectedSelRes.data.dm;
				jlxstore.baseParams = {
						jwhdm:selectedjwhdm,
						qybz:jlxqybz
				};
				param = jlxstore.baseParams;
				jlxstore.load({params:{start:0, limit:20}});
				jlxstore.on("load",function(store) {  
					length= jlxstore.data.length;
					if(length > 0){
						jlxxxGrid.fireEvent("rowclick",jlxxxGrid,curIndex);
						jlxxxGrid.getSelectionModel().selectRange(curIndex,curIndex);
					}
					
				},jlxxxGrid);
			}
		},
        bbar: new Ext.PagingToolbar({
				pageSize: 20,
				store: myuserStore,
				displayInfo: true
		})
    });
	var jlxcsm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var jlxcolModel = new Ext.grid.ColumnModel([
		{
			header: "街路巷代码",
	        dataIndex: "jlxdm",
	        sortable: true,
			width: 120
		},{
	        header: "街路巷名称",
	        dataIndex: "jlxmc",
	        sortable: true,
			width: 120
	    },{
			header: "居委会代码",
	        dataIndex: "jwhdm",
	        sortable: true,
			width: 120
		},{
			header: "居委会名称",
	        dataIndex: "jwhmc",
	        sortable: true,
			width: 120
		},{
			header: "街路巷类型",
	        dataIndex: "jlxhlx",
	        hidden:true,
	        sortable: true,
			width: 120
		},{
			header: "街路巷类型",
	        dataIndex: "jlxhlxmc",
	        sortable: true,
			width: 120
		},{
			header: "街路巷号",
	        dataIndex: "jlxh",
	        sortable: true,
			width: 120
		},{
	        header: "启用标志",
	        dataIndex: "qybz",	
	        hidden:true,
	        sortable: true,
			width: 120,
			renderer:function(value){
				return value;
			}
	    },{
	        header: "启用标志",
	        dataIndex: "qybzmc",	
	        sortable: true,
			width: 120,
			renderer:function(value){
				return value;
			}
	    }
	]);
 	var jlxxxStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'gl/xtmbgl/jwhxxwh/getJlxByJwhdm',
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        totalProperty:'totalCount',
        fields: [
        	"jlxdm",
			"jlxmc",
			"jwhdm",
			"jwhmc",
			"jlxhlx",
			"jlxhlxmc",
			"jlxh",
			"qybz",
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
	var jlxxxGrid = new Ext.grid.GridPanel({
        store: jlxxxStore,
        region: 'center',
        view:new Ext.grid.GridView({
				//forceFit:true,
				//autoFill:true,
				enableRowBody:true
		}),
		stripeRows: true,
        cm: jlxcolModel,
        sm:jlxcsm,
		loadMask: {msg:'正在加载数据，请稍侯……'},
		bodyStyle:'width:80%',
        border:true,
        anchor:'100% 100%',
	    margins: '0 0 0 0',
		cmargins: '0 0 0 0',        
        frame:false,
		iconCls:'icon-grid',
        title:'',
        listeners:{
        	rowclick:function(g, rowIndex, e){
        		selectedJlxSelRes = g.store.getAt(rowIndex);
        	},
			rowdblclick:function(g, rowIndex, e){
				selectedJlxSelRes = g.store.getAt(rowIndex);
			}
		},
        bbar: new Ext.PagingToolbar({
				pageSize: 20,
				store: jlxxxStore,
				displayInfo: true
		})
    });

	var modifyJwhxxwh_window = new Gnt.ux.SelectJwhxxWhModify({
		//选择立户信息回调
		callback: function(optype, xzjdxxModify){//提交数据
			Gnt.submit(
					xzjdxxModify, 
					"gl/xtmbgl/jwhxxwh/modifyJwh", 
					"正在修改居委会信息，请稍后...", 
					function(jsonData,params){
						showInfo("居委会信息修改成功！");
						jwhxxGrid.store.reload(); 
					}
			);
		}
	});
	
	var addJwhxxwh_window = new Gnt.ux.SelectJwhxxWhAdd({
		//选择立户信息回调
		callback: function(optype, xzjdxxAdd){
			//提交数据
			Gnt.submit(
					xzjdxxAdd, 
					"gl/xtmbgl/jwhxxwh/addJwh", 
					"正在新增居委会信息，请稍后...", 
					function(jsonData,params){
						showInfo("居委会信息新增成功！");
						jwhxxGrid.store.reload(); 
					}
			);
		}
	});
	var jlxJwh_window = new Gnt.ux.JlxJwhWh({
		//选择立户信息回调
		callback: function(optype, jlxJwh){
			if(optype==1){
				//提交数据
				Gnt.submit(
						jlxJwh, 
						"gl/xtmbgl/jwhxxwh/addJlx", 
						"正在增加街路巷信息，请稍后...", 
						function(jsonData,params){
							showInfo("街路巷信息增加成功！");
							jlxxxGrid.store.reload(); 
							//	this.jwhxx1Grid.store.reload(); 
						}
				);
			}else if(optype==2){
				//提交数据
				Gnt.submit(
						jlxJwh, 
						"gl/xtmbgl/jwhxxwh/modifyjlx", 
						"正在新增居委会信息，请稍后...", 
						function(jsonData,params){
							showInfo("街路巷信息修改成功！");
							jlxxxGrid.store.reload(); 
						}
				);
				
			}

		}
	});
	
	var xtmbgl_window=new Gnt.ux.xtmbglwin({
		callback: function(){
		
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
				width: 70,
				html:'单位代码',
				border:false,
				frame: false
			},{
				xtype:'search_combox',
				anchor:'100%',
				id:'qhdm',
				name:'qhdm',
				width: 150,
				searchUrl : "dict/utils/searchXxb?visiontype=DWXXB&whflg=1"
			},{
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
    		},{
				width: 100,
				html:'居委会代码',
				border:false,
				frame: false
			},{
				xtype:'search_combox',
				anchor:'100%',
				id:'dm',
				name:'dm',
				width: 150,
				searchUrl : "dict/utils/searchXxb?visiontype=JWHXXB&optype=eq"
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
    		    	id:'fzId',
        			text:'查询',
        			minWidth:80,
        			handler:function(){
						var store = jwhxxGrid.store;
						store.baseParams = {
								dwdm:Ext.getCmp('qhdm').getValue(),
								dm:Ext.getCmp('dm').getValue(),
								qybz:qybz
						};
						store.load({params:{start:0, limit:20}});	
						store.on("load",function(store) {  
							if(store.data.length > 0){
								Ext.getCmp('totalCount').body.update("居委会总数"+store.totalLength);
								curIndex = 0;
								jwhxxGrid.fireEvent("rowclick",jwhxxGrid,curIndex);
								jwhxxGrid.getSelectionModel().selectRange(curIndex,curIndex);
							}
						},jwhxxGrid); 
					}
        		})]
    		},{
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
    		},{
				width: 120,
				id:'totalCount',
				html:'居委会总数0',
				border:false,
				frame: false
			
			}]
    });
	var p1_3 = new Ext.Panel({
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
    		},
    		{
            	id:'c21',
            	xtype:'checkbox',
            	columnWidth: .35,
            	boxLabel: '只显示不启用的居委会',
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
            			var store = jwhxxGrid.store;
        				store.baseParams = {
        						qhdm:Ext.getCmp('qhdm').getValue(),
								dm:Ext.getCmp('dm').getValue(),
        						qybz:qybz
        					};
        				store.load({params:{start:0, limit:20}});
        				store.on("load",function(store) {  
							if(store.data.length > 0){
								Ext.getCmp('totalCount').body.update("居委会总数"+store.totalLength);
								curIndex = 0;
								jwhxxGrid.fireEvent("rowclick",jwhxxGrid,curIndex);
								jwhxxGrid.getSelectionModel().selectRange(curIndex,curIndex);
							}
						},jwhxxGrid);
            		}
            	}
            }, {
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
    		}, {
            	id:'c22',
            	xtype:'checkbox',
            	columnWidth: .15,
            	boxLabel: '只显示启用街路巷',
            	name:'jlxqybz',
            	checked:true,
            	width: 180,
            	inputValue : "0",
            	listeners:{
            		'check':function (ck, checked){
            			if (checked) {
            				jlxqybz = 1;
            				var jlxstore = jlxxxGrid.store;
            				jlxstore.baseParams = {
            						jwhdm:selectedjwhdm,
            						qybz:jlxqybz
            				};
            				jlxstore.load({params:{start:0, limit:20}});
//            				jlxstore.on("load",function(store) {  
//            					if(store.data.length > 0){
//            						jlxxxGrid.fireEvent("rowclick",jlxxxGrid,curIndex);
//            						jlxxxGrid.getSelectionModel().selectRange(curIndex,curIndex);
//            					}
//            					
//            				},jlxxxGrid);
            			}else{
            				var jlxstore = jlxxxGrid.store;
            				jlxstore.baseParams = {
            						jwhdm:selectedjwhdm
            				};
            				jlxstore.load({params:{start:0, limit:20}});
            			}
            		}
            	}
            }, {
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
    		        	   text:'保存',
							minWidth:80,
							handler:function(){
								var data=jwhxxGrid.store.data.items;
								xtmbgl_window.show();
								xtmbgl_window.setSelRes(data,'jwhxx');
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
						text:'街路巷维护',
						minWidth:80,
						handler:function(){
							jlxJwh_window.show();
							jlxJwh_window.setSelRes(selectedSelRes,param,length);
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
    		        	   text:'增加',
							minWidth:80,
							id:'addBtn',
							handler:function(){
								addJwhxxwh_window.show();
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
    		        	   text:'修改',
							id:'modifyBtn',
							minWidth:80,
							handler:function(){
								if(selectedSelRes){
									modifyJwhxxwh_window.show(selectedSelRes);
									modifyJwhxxwh_window.setSelRes(selectedSelRes);
								}else{
									alert("请选中一条有效的数据，再点击修改！");
								}
								
							}
    		           })
    		    ]
    		},{
				border:false,
				frame: false,
    		    items:[
    		           new Ext.Button({
							text:'恢复',
							id:'resumeBtn',
							hidden:true,	
							minWidth:80,
							handler:function(){
								Gnt.submit(
										{
											dm:selectedSelRes.data.dm,
											qybz:1}, 
											"gl/xtmbgl/jwhxxwh/resumeJwh", 
											"正在 恢复居委会，请稍后...", 
											function(jsonData,params){
												showInfo("居委会恢复成功！");
												selectedSelRes = null;
												jwhxxGrid.store.reload(); 
										}
									);
								}
    		           })
    		    ]
    		}, {
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
							text:'删除',
							id:'delBtn',
							minWidth:80,
							disabled: !user.isadmin?true:false,
							handler:function(){
								if(window.confirm('是否确定要删除【'+selectedSelRes.data.dm+'】【'+selectedSelRes.data.mc+'】?')){
									Gnt.submit(
									{
										dm:selectedSelRes.data.dm}, 
										"gl/xtmbgl/jwhxxwh/delJwh", 
										"正在删除居委会，请稍后...", 
										function(jsonData,params){
											showInfo("居委会删除成功！");
											jwhxxGrid.store.reload(); 
										}
									);
								}

							}
    		           })
    		    ]
    		}, {
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
    		        	   text:'关闭',
							minWidth:80,
							handler:function(){
								window.parent.closeWorkSpace();
							}
    		           })
    		    ]
    		}
		]
    });
	var p1 = new Ext.Panel({
		layout:'border',
		items:[{
			region:'north',
			height:350,
            border:false,
            frame:false,
            items:[p1_1,jwhxxGrid]
		},jlxxxGrid,p1_3]
	});
	//定义分页面板
	
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
});