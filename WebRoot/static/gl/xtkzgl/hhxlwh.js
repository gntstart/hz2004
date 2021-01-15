var qybz = 1;

var selectedSelRes=null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	var csm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var colModel = new Ext.grid.ColumnModel([
		{
			header: "序列Id",
	        dataIndex: "xlid",
	        sortable: true,
	        hidden:true,
			width: 120
		},{
			header: "单位代码",
	        dataIndex: "dwdm",
	        sortable: true,
			width: 120
		},{
	        header: "单位名称",
	        dataIndex: "mc",
	        sortable: true,
			width: 120
	    },{
			header: "户号当前序列号",
	        dataIndex: "hhxlid",
	        sortable: true,
			width: 120
		}
	]);
 	var myuserStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'gl/xtkzgl/hhxlwh/getHhxlwhInfo',//yw/hjyw/hzywcl/queryHzywcl
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        id:'id',
        totalProperty:'totalCount',
        fields: [
        	"xlid",
			"dwdm",
			"mc",
			"hhxlid"
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
	var hhxlwhGrid = new Ext.grid.GridPanel({
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
	var modifyhhxl_window = new Gnt.ux.SelectHhxlModify({
		//选择立户信息回调
		callback: function(optype, hhxlModify){
			//提交数据
			Gnt.submit(
					hhxlModify, 
					"gl/xtkzgl/hhxlwh/modifyHhxlwh", 
					"正在修改户号序列信息，请稍后...", 
					function(jsonData,params){
						showInfo("户号序列修改成功！");
						hhxlwhGrid.store.reload(); 
					}
			);
		}
	});
	
	var addhhxl_window = new Gnt.ux.SelectHhxlAdd({
		//选择立户信息回调
		callback: function(optype, hhxlAdd){
			//提交数据
			Gnt.submit(
					hhxlAdd, 
					"gl/xtkzgl/hhxlwh/addHhxlwh", 
					"正在新增户号序列信息，请稍后...", 
					function(jsonData,params){
						showInfo("户号序列信息新增成功！");
						hhxlwhGrid.store.reload(); 
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
				html:'区划代码',
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
				width: 70,
				html:'单位代码',
				border:false,
				frame: false
			},{
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
				border:false,
				frame: false,
    		    items:[new Ext.Button({
    		    	id:'fzId',
        			text:'查询',
        			minWidth:80,
        			handler:function(){
						var store = hhxlwhGrid.store;
						store.baseParams = {
								qhdm:Ext.getCmp('qhdmQuery').getValue(),
								dwdm:Ext.getCmp('dwdmQuery').getValue(),
								qybz:qybz
						};
						store.load({params:{start:0, limit:20}});	
						store.on("load",function(store) {  
							if(store.data.length > 0){
								curIndex = 0;
								hhxlwhGrid.fireEvent("rowclick",hhxlwhGrid,curIndex);
								hhxlwhGrid.getSelectionModel().selectRange(curIndex,curIndex);
							}
						},hhxlwhGrid); 
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
    		},{
				border:false,
				frame: false,
    		    items:[
    		           new Ext.Button({
    		        	   text:'增加',
							minWidth:80,
							handler:function(){
								addhhxl_window.show();
								addhhxl_window.setSelRes();
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
							minWidth:80,
							handler:function(){
								modifyhhxl_window.show(selectedSelRes);
								modifyhhxl_window.setSelRes(selectedSelRes);
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
							text:'删除',
							minWidth:80,
							disabled: !user.isadmin?true:false,
							handler:function(){
								if(window.confirm('是否确定要删除【'+selectedSelRes.data.mc+'】的户号序列?')){
									Gnt.submit(
									{
										xlid:selectedSelRes.data.xlid}, 
										"gl/xtkzgl/hhxlwh/delHhxlwh", 
										"正在删除户号序列，请稍后...", 
										function(jsonData,params){
											showInfo("户号序列删除成功！");
											hhxlwhGrid.store.reload(); 
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
		items:[p1_1,hhxlwhGrid,p1_3]
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
//	var store = hhxlwhGrid.store;
//	store.baseParams = {
//			config_key:'querySjzdInfo',
//			start:0,
//			limit:20
//	};
//	store.load();	
//	store.on("load",function(store) {  
//		if(store.data.length > 0){
//			curIndex = 0;
//			hhxlwhGrid.fireEvent("rowclick",hhxlwhGrid,curIndex);
//			hhxlwhGrid.getSelectionModel().selectRange(curIndex,curIndex);
//		}
//	},hhxlwhGrid); 
});