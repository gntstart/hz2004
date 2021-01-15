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

	var csm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var colModel = new Ext.grid.ColumnModel([
		{
			header: "区划代码",
	        dataIndex: "qhdm",
	        sortable: true,
			width: 120
		},{
	        header: "区划名称",
	        dataIndex: "xzqhmc",
	        sortable: true,
			width: 120
	    },{
			header: "乡镇街道代码",
	        dataIndex: "dm",
	        sortable: true,
			width: 120
		},{
			header: "乡镇街道名称",
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
	        dataIndex: "qybzmc",	
	        sortable: true,
			width: 120,
			renderer:function(value){
				return value;
			}
	    },{
	        header: "新代码",
	        dataIndex: "xdm",	
	        sortable: true,
			width: 120,
			renderer:function(value){
				return value;
			}
	    },{
	        header: "乡镇街道类型代码",
	        dataIndex: "dzys",	
	        sortable: true,
			width: 120,
			renderer:function(value){
				return value;
			}
	    },{
	        header: "乡镇街道类型代码名称",
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
 			url: 'gl/xtmbgl/xzjdwh/getXzjdInfo',
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        id:'id',
        totalProperty:'totalCount',
        fields: [
        	"qhdm",
			"xzqhmc",
			"dm",
			"mc",
			"zwpy",
			"wbpy",
			"bz",
			"qybzmc",
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
	var xzjdxxGrid = new Ext.grid.GridPanel({
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
				pageSize: 20,
				store: myuserStore,
				displayInfo: true
		})
    });
	var modifyxzjdwh_window = new Gnt.ux.SelectXzjdWhModify({
		//选择立户信息回调
		callback: function(optype, xzjdxxModify){
			//提交数据
			Gnt.submit(
					xzjdxxModify, 
					"gl/xtmbgl/xzjdwh/modifyXzjd", 
					"正在修改乡镇街道信息，请稍后...", 
					function(jsonData,params){
						showInfo("乡镇街道信息修改成功！");
						xzjdxxGrid.store.reload(); 
					}
			);
		}
	});
	
	var addxzjdxxwh_window = new Gnt.ux.SelectXzjdWhAdd({
		//选择立户信息回调
		callback: function(optype, xzjdxxAdd){
			//提交数据
			Gnt.submit(
					xzjdxxAdd, 
					"gl/xtmbgl/xzjdwh/addXzjd", 
					"正在新增乡镇街道信息，请稍后...", 
					function(jsonData,params){
						showInfo("乡镇街道信息新增成功！");
						xzjdxxGrid.store.reload(); 
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
				width: 70,
				html:'区划代码',
				border:false,
				frame: false
			},{
				anchor:'100%',
				xtype:'search_combox',
				anchor:'100%',
				id:'qhdm',
				name:'qhdm',
				width: 150,
				searchUrl : "dict/utils/searchXzqh.json?qybz=1"
			},{
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
    		},{
				width: 100,
				html:'乡镇街道代码',
				border:false,
				frame: false
			},{anchor:'100%',
				xtype:'search_combox',
				anchor:'100%',
				id:'dm',
				name:'dm',
				width: 150,
				searchUrl : "dict/utils/searchXxb?visiontype=XZJDXXB&optype=eq"
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
						var store = xzjdxxGrid.store;
						store.baseParams = {
								qhdm:Ext.getCmp('qhdm').getValue(),
								dm:Ext.getCmp('dm').getValue(),
								qybz:qybz
						};
						store.load({params:{start:0, limit:20}});	
						store.on("load",function(store) {  
							if(store.data.length > 0){
								curIndex = 0;
								xzjdxxGrid.fireEvent("rowclick",xzjdxxGrid,curIndex);
								xzjdxxGrid.getSelectionModel().selectRange(curIndex,curIndex);
							}
						},xzjdxxGrid); 
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
            			var store = xzjdxxGrid.store;
        				store.baseParams = {
        						qhdm:Ext.getCmp('qhdm').getValue(),
								dm:Ext.getCmp('dm').getValue(),
        						qybz:qybz
        					};
        				store.load({params:{start:0, limit:20}});
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
								var data=xzjdxxGrid.store.data.items;
								xtmbgl_window.show();
								xtmbgl_window.setSelRes(data,'xzjdwh');
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
								addxzjdxxwh_window.show();
								addxzjdxxwh_window.setSelRes();
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
									modifyxzjdwh_window.show(selectedSelRes);
									modifyxzjdwh_window.setSelRes(selectedSelRes);
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
											"gl/xtmbgl/xzjdwh/resumeXzjd", 
											"正在 恢复乡镇街道，请稍后...", 
											function(jsonData,params){
												showInfo("乡镇街道恢复成功！");
												selectedSelRes=null;
												xzjdxxGrid.store.reload(); 
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
										"gl/xtmbgl/xzjdwh/delXzjd", 
										"正在删除乡镇街道信息，请稍后...", 
										function(jsonData,params){
											showInfo("乡镇街道删除成功！");
											xzjdxxGrid.store.reload(); 
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
		items:[p1_1,xzjdxxGrid,p1_3]
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