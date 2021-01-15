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
			header: "单位代码",
	        dataIndex: "dwdm",
	        sortable: true,
			width: 120
		},{
	        header: "单位名称",
	        dataIndex: "dwdc",
	        sortable: true,
			width: 120
	    },{
			header: "警务责任区代码",
	        dataIndex: "dm",
	        sortable: true,
			width: 120
		},{
			header: "警务责任区名称",
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
	        dataIndex: "qybzdm",	
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
 			url: 'gl/xtmbgl/jwzrqxxwh/getJwzrqxxInfo',//yw/hjyw/hzywcl/queryHzywcl
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        id:'id',
        totalProperty:'totalCount',
        fields: [
        	"dwdm",
			"dwdc",
			"dm",
			"mc",
			"zwpy",
			"wbpy",
			"bz",
			"qybzdm",
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
	var jwzrqxxGrid = new Ext.grid.GridPanel({
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
	var modifyjwzrqxxwh_window = new Gnt.ux.SelectJwzrqxxWhModify({
		//选择立户信息回调
		callback: function(optype, jwzrqxxModify){
			//提交数据
			Gnt.submit(
					jwzrqxxModify, 
					"gl/xtmbgl/jwzrqxxwh/modifyJwzrq", 
					"正在修改警务区信息，请稍后...", 
					function(jsonData,params){
						showInfo("警务区信息修改成功！");
						jwzrqxxGrid.store.reload(); 
					}
			);
		}
	});
	
	var addjwzrqxxwh_window = new Gnt.ux.SelectJwzrqxxWhAdd({
		//选择立户信息回调
		callback: function(optype, jwzrqxxAdd){
			//提交数据
			Gnt.submit(
					jwzrqxxAdd, 
					"gl/xtmbgl/jwzrqxxwh/addJwzrq", 
					"正在新增警务区信息，请稍后...", 
					function(jsonData,params){
						showInfo("警务区信息新增成功！");
						jwzrqxxGrid.store.reload(); 
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
				width: 70,
				html:'单位代码',
				border:false,
				frame: false
			},{
//				xtype:'search_combox',
				anchor:'100%',
				xtype:'search_combox',
				anchor:'100%',
				id:'dwdmQuery',
				name:'dwdm',
				width: 150,
				searchUrl : "dict/utils/searchXxb?visiontype=DWXXB&whflg=1"
			},{
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
    		},{
				width: 120,
				html:'警务责任区代码',
				border:false,
				frame: false
			},{anchor:'100%',
				xtype:'search_combox',
				anchor:'100%',
				id:'jwzrqdmQuery',
				name:'jwzrqdm',
				width: 150,
				searchUrl : "dict/utils/searchXxb?visiontype=JWZRQXXB&optype=eq"
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
						var store = jwzrqxxGrid.store;
						store.baseParams = {
								dwdm:Ext.getCmp('dwdmQuery').getValue(),
								dm:Ext.getCmp('jwzrqdmQuery').getValue(),
								qybz:qybz
						};
						store.load({params:{start:0, limit:20}});	
						store.on("load",function(store) {  
							if(store.data.length > 0){
								curIndex = 0;
								jwzrqxxGrid.fireEvent("rowclick",jwzrqxxGrid,curIndex);
								jwzrqxxGrid.getSelectionModel().selectRange(curIndex,curIndex);
							}
						},jwzrqxxGrid); 
					}
        		})]
    		}]
    });
	
	var xtmbgl_window=new Gnt.ux.xtmbglwin({
		callback: function(){
		
		}
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
            			var store = jwzrqxxGrid.store;
        				store.baseParams = {
        						dwdm:Ext.getCmp('dwdmQuery').getValue(),
								dm:Ext.getCmp('jwzrqdmQuery').getValue(),
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
								var data=jwzrqxxGrid.store.data.items;
								xtmbgl_window.show();
								xtmbgl_window.setSelRes(data,'jwqxxwh');
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
								addjwzrqxxwh_window.show();
								addjwzrqxxwh_window.setSelRes();
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
									modifyjwzrqxxwh_window.show(selectedSelRes);
									modifyjwzrqxxwh_window.setSelRes(selectedSelRes);
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
											"gl/xtmbgl/jwzrqxxwh/resumeJwzrq", 
											"正在 恢复警务责任区，请稍后...", 
											function(jsonData,params){
												showInfo("警务责任区恢复成功！");
												selectedSelRes = null;
												jwzrqxxGrid.store.reload(); 
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
										"gl/xtmbgl/jwzrqxxwh/delJwzrq", 
										"正在删除警务责任区信息，请稍后...", 
										function(jsonData,params){
											showInfo("警务责任区删除成功！");
											jwzrqxxGrid.store.reload(); 
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
		items:[p1_1,jwzrqxxGrid,p1_3]
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