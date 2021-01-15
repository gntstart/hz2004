var selectYw = 1;
var queryFlag = null;
var qybz = 1;

var selectedSelRes=null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10019,20021",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	var sfbz = [[1,'区划代码'],[2,'区划名称']]
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
	var csm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var colModel = new Ext.grid.ColumnModel([
		{
			header: "区划代码",
	        dataIndex: "dm",
	        sortable: true,
			width: 120
		},{
	        header: "区划名称",
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
	    }
	]);
 	var myuserStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'gl/xtmbgl/xzqhwh/getXzqhInfo',//yw/hjyw/hzywcl/queryHzywcl
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        id:'id',
        totalProperty:'totalCount',
        fields: [
        	"dm",
			"mc",
			"zwpy",
			"wbpy",
			"bz",
			"qybz",
			"bdlx"
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
	var xzqhGrid = new Ext.grid.GridPanel({
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
        		selectedSelRes = g.store.getAt(rowIndex);
        	},
			rowdblclick:function(g, rowIndex, e){
				selectedSelRes = g.store.getAt(rowIndex);
			}
		},
        bbar: new Ext.PagingToolbar({
				pageSize: 99999,
				store: myuserStore,
				displayInfo: true
		})
    });
	var modifyxzqhWindow = new Gnt.ux.SelectXzqhWhModify({
		//选择立户信息回调
		callback: function(optype, sjzdModify){
			log_code = "F9017";
			//提交数据
			Gnt.submit(
					sjzdModify, 
					"gl/xtmbgl/xzqhwh/modifyXzqhDm", 
					"正在修改行政区划，请稍后...", 
					function(jsonData,params){
						showInfo("行政区修改成功！");
						xzqhGrid.store.reload(); 
					}
			);
		}
	});
	
	var addxzqhWindow = new Gnt.ux.SelectXzqhWhAdd({
		//选择立户信息回调
		callback: function(optype, sjzdAdd){
			log_code = "F9016";
			//提交数据
			Gnt.submit(
					sjzdAdd, 
					"gl/xtmbgl/xzqhwh/addXzqhDm", 
					"正在新增行政区划，请稍后...", 
					function(jsonData,params){
						showInfo("行政区新增成功！");
						xzqhGrid.store.reload(); 
					}
			);
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
				border:false,
				frame: false,
				id:'queryFlag',
	    		xtype : "combo",
	    		store : sfbz,
	    		value:'1',
				fieldLabel:"",
				triggerAction:"all"
			},{
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
    		},{
				id:'queryValue',
    			xtype : 'textfield',
    			border:false,
    			frame:false,
    			fieldLabel:"",
    			name:'queryValue'
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
        			text:'查询',
        			minWidth:80,
        			handler:function(){
						if(!Ext.getCmp('queryValue').getValue()){
							alert("请输入需要查找的相关信息标记!");
							return;
						}
//						xzqhGrid.fireEvent("rowclick",xzqhGrid,2);
//						xzqhGrid.getSelectionModel().selectRange(2,2);
						var store = xzqhGrid.store;
//						var rowIndex =0;
						if(store.data.length > 0){
							for(var i= 0;i<store.data.length;i++){
//								alert(store.getAt(0).data.zdmc);
								var res = store.getAt(i);
								var b = Ext.getCmp('queryValue').getValue();
								if(Ext.getCmp('queryFlag').getValue()==1){//字段名称
									var a = res.data.dm;
									if(a.indexOf(b.toUpperCase())!=-1){
										xzqhGrid.fireEvent("rowclick",xzqhGrid,i);
										xzqhGrid.getSelectionModel().selectRange(i,i);
										xzqhGrid.getView().focusRow(i);
										return;
									}
								}else if(Ext.getCmp('queryFlag').getValue()==2){//字段含义
									var c = res.data.mc;
									if(c.indexOf(b.toUpperCase())!=-1){
										xzqhGrid.fireEvent("rowclick",xzqhGrid,i);
										xzqhGrid.getSelectionModel().selectRange(i,i);
										xzqhGrid.getView().focusRow(i);
										return;
									}
								}
							}
						}
					}
        		})]
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
            	boxLabel: '只显示不启用的记录',
            	name:'qybz',
            	inputValue : "0",
            	listeners:{
            		'check':function (ck, checked){
            			if (checked) {
            				qybz = 0;
            				Ext.getCmp('resume').show();
            				Ext.getCmp('modify').hide();
            				var store = xzqhGrid.store;
            				store.baseParams = {
            						qybz:qybz
            					};
            				store.load({params:{start:0, limit:9999}});
            			}else{
            				qybz = 1;
            				Ext.getCmp('resume').hide();
            				Ext.getCmp('modify').show();
            				var store = xzqhGrid.store;
            				store.baseParams = {
            						qybz:qybz
            					};
            				store.load({params:{start:0, limit:9999}});
            			}
            		}
            	}
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
    		        	   text:'保存',
							minWidth:80,
							handler:function(){
								var data=xzqhGrid.store.data.items;
								xtmbgl_window.show();
								xtmbgl_window.setSelRes(data,'xzqhwh');
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
								if(selectedSelRes){
									addxzqhWindow.show();
									addxzqhWindow.setSelRes();	
								}else{
									alert("请选中一条有效的数据，再点击修改！");
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
						text:'恢复',
						id:'resume',
						hidden:true,
						minWidth:80,
						handler:function(){
							Gnt.submit(
								{
									dm:selectedSelRes.data.dm,
									qybz:1}, 
									"gl/xtmbgl/xzqhwh/resumeXzqhDm", 
									"正在 恢复行政区，请稍后...", 
									function(jsonData,params){
										showInfo("行政区恢复成功！");
										selectedSelRes = null;
										xzqhGrid.store.reload(); 
								}
							);
						}
					})
    			]
    		},{
				border:false,
				frame: false,
    		    items:[
    		    	new Ext.Button({
						text:'修改',
						id:'modify',
						minWidth:80,
						handler:function(){
							modifyxzqhWindow.show(selectedSelRes);
							modifyxzqhWindow.setSelRes(selectedSelRes);
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
						minWidth:80,
						disabled: !user.isadmin?true:false,
						handler:function(){
							if(window.confirm('是否确定要删除【'+selectedSelRes.data.dm+'】【'+selectedSelRes.data.mc+'】?')){
								log_code = "F9018";
								Gnt.submit(
								{
									dm:selectedSelRes.data.dm}, 
									"gl/xtmbgl/xzqhwh/delXzqhDm", 
									"正在修改数据字典，请稍后...", 
									function(jsonData,params){
										showInfo("行政区删除成功！");
										xzqhGrid.store.reload(); 
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
		items:[xzqhGrid,			{
			id:"p2SouthId",
        	region:'south',
//        	height:120,
        	height:60,
            border:false,
            frame:false,
            items:[{
	            autoHeight: true,
	            items: [{
					border:false,
					frame: false,
		        	region:'center',
		        	//					上	右	下	左
		        	bodyStyle: 'padding:10px 0px 10px 0px ',
					layout:'column',
					layoutConfig: {
			        	columns: 30
			        },
					items: [
						{
							border:false,
							frame: false,
							columnWidth: 0.45,
							items:[p1_1]
						},{
							border:false,
							frame: false,
							columnWidth: 0.55,
							items:[p1_3]
						}
					]
				}]
				}
            ]
			
		}]/*,
			buttons:[
				new Ext.Button({
					text:'保存',
					minWidth:80,
					handler:function(){
						qyzPrint(selectHjywid);
					}
				}),
				new Ext.Button({
					text:'修改',
					minWidth:80,
					handler:function(){
						window.parent.parent.closeWorkSpace();
					}
				}),
				new Ext.Button({
					text:'关闭',
					minWidth:80,
					handler:function(){
						Ext.getCmp('card').getLayout().setActiveItem(0);
					}
				})
				]*/
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
	var store = xzqhGrid.store;
	store.baseParams = {
			qybz:qybz
	};
	store.load({params:{start:0, limit:9999}});	
	store.on("load",function(store) {  
		if(store.data.length > 0){
			curIndex = 0;
			xzqhGrid.fireEvent("rowclick",xzqhGrid,curIndex);
			xzqhGrid.getSelectionModel().selectRange(curIndex,curIndex);
		}
	},xzqhGrid); 
});