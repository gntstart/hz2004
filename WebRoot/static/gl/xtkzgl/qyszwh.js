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
			header: "迁移Id",
	        dataIndex: "qyid",
	        sortable: true,
	        hidden:true,
			width: 120
		},{
			header: "区划代码A",
	        dataIndex: "qhdma",
	        sortable: true,
			width: 120
		},{
	        header: "区划A名称",
	        dataIndex: "qhdmamc",
	        sortable: true,
			width: 120
	    },{
			header: "区划代码B",
	        dataIndex: "qhdmb",
	        sortable: true,
			width: 120
		},{
			header: "区划B名称",
	        dataIndex: "qhdmbmc",
	        sortable: true,
			width: 120
		},{
	        header: "创建时间",
	        dataIndex: "cjsj",
	        sortable: true,
			width: 120
	    },{
	        header: "创建人姓名",
	        dataIndex: "cjrid",	
	        sortable: true,
			width:120
	    },{
			header: "修改时间",
	        dataIndex: "xgsj",
	        sortable: true,
			width: 120
		},{header: "修改人姓名",
	        dataIndex: "xgrid",
	        sortable: true,
	        hidden:true,
			width: 120
		},{
			header: "启用标志",
	        dataIndex: "qybz",
	        sortable: true,
			width: 120
		}
	]);
 	var myuserStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'gl/xtkzgl/qyszwh/getQyszwhInfo',//yw/hjyw/hzywcl/queryHzywcl
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        id:'id',
        totalProperty:'totalCount',
        fields: [
        	"qyid",
			"qhdma",
			"qhdmamc",
			"qhdmb",
			"qhdmbmc",
			"cjsj",
			"cjrid",
			"xgsj",
			"xgrid",
			"qybz"
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
	var qyszGrid = new Ext.grid.GridPanel({
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

	var addqysz_window = new Gnt.ux.SelectQyszAdd({
		//选择立户信息回调
		callback: function(optype, qyszAdd){
			//提交数据
			Gnt.submit(
					qyszAdd, 
					"gl/xtkzgl/qyszwh/addQyszwh", 
					"正在新增迁移设置信息，请稍后...", 
					function(jsonData,params){
						showInfo("迁移设置信息新增成功！");
						qyszGrid.store.reload(); 
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
				width: 140,
				html:'涉及的区划代码',
				border:false,
				frame: false
			},{
				xtype:'search_combox',
				anchor:'100%',
				searchUrl:'dict/utils/searchXzqh.json?qybz=1',
				id:'qhdmQuery',
				width: 150,
				fieldLabel:'',
				hiddenName:'xzqhbdm'
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
						var store = qyszGrid.store;
						store.baseParams = {
								qhdm:Ext.getCmp('qhdmQuery').getValue(),
								qybz:qybz
						};
						store.load({params:{start:0, limit:20}});	
						store.on("load",function(store) {  
							if(store.data.length > 0){
								curIndex = 0;
								qyszGrid.fireEvent("rowclick",qyszGrid,curIndex);
								qyszGrid.getSelectionModel().selectRange(curIndex,curIndex);
							}
						},qyszGrid); 
					}
        		})]
    		}]
    });	
	var p1_3 = new Ext.Panel({
		xtype: 'panel',
		border:false,
		frame: false,
    	region:'south',
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
            				Ext.getCmp('delBtn').hide();
            			}else{
            				qybz = 1;
            				Ext.getCmp('resumeBtn').hide();
            				Ext.getCmp('addBtn').enable();
            				Ext.getCmp('delBtn').show();
            			}
            			var store = qyszGrid.store;
        				store.baseParams = {
        						qhdm:Ext.getCmp('qhdmQuery').getValue(),
        						qybz:qybz
        					};
        				store.load({params:{start:0, limit:20}});
        				store.on("load",function(store) {  
        				if(store.data.length > 0){
        					curIndex = 0;
        					qyszGrid.fireEvent("rowclick",qyszGrid,curIndex);
        					qyszGrid.getSelectionModel().selectRange(curIndex,curIndex);
        				}
        			},qyszGrid); 
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
							text:'增加',
							minWidth:80,
							id:'addBtn',
							handler:function(){
								addqysz_window.show();
								addqysz_window.setSelRes();
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
											qyid:selectedSelRes.data.qyid,
											qybz:1}, 
											"gl/xtkzgl/qyszwh/resumeQyszwh", 
											"正在 恢复迁移设置信息，请稍后...", 
											function(jsonData,params){
												showInfo("移设置信息恢复成功！");
												selectedSelRes=null;
												qyszGrid.store.reload(); 
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
								if(selectedSelRes){
									if(window.confirm('是否确定要删除【'+selectedSelRes.data.qhdmamc+'】【'+selectedSelRes.data.qhdmbmc+'】的迁移设置?')){
										Gnt.submit(
										{
											qyid:selectedSelRes.data.qyid}, 
											"gl/xtkzgl/qyszwh/delQyszwh", 
											"正在修改迁移设置信息，请稍后...", 
											function(jsonData,params){
												showInfo("迁移设置信息删除成功！");
												qyszGrid.store.reload(); 
											}
										);
									}
								}else{
									alert("请选中一条有效的数据，再点击修改！");
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
		items:[p1_1,qyszGrid,p1_3]/*,
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
	var store = qyszGrid.store;
	store.baseParams = {
			qybz:qybz,
			start:0,
			limit:20
	};
});