var selectedSelRes=null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	var csm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var colModel = new Ext.grid.ColumnModel([
		{
			header: "尾数段Id",
	        dataIndex: "wsdid",
	        sortable: true,
	        hidden:true,
			width: 120
		},{
			header: "区划代码",
	        dataIndex: "qhdm",
	        sortable: true,
			width: 120
		},{
	        header: "区划名称",
	        dataIndex: "qhmc",
	        sortable: true,
			width: 120
	    },{
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
	        header: "乡镇街道",
	        dataIndex: "xzjd",
	        sortable: true,
			width: 120
	    },{
			header: "乡镇街道名称",
	        dataIndex: "xzjdmc",
	        sortable: true,
			width: 120
		},{
	        header: "启用日期",
	        dataIndex: "qyrq",
	        sortable: true,
			width: 120
	    },{
			header: "开始段",
	        dataIndex: "ksd",
	        sortable: true,
			width: 120
		},{
	        header: "结束段",
	        dataIndex: "jsd",
	        sortable: true,
			width: 120
	    },{
			header: "备注",
	        dataIndex: "bz",
	        sortable: true,
			width: 120
		}
	]);
 	var myuserStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'gl/xtkzgl/lnwsdwh/getLnwsdwhInfo',//yw/hjyw/hzywcl/queryHzywcl
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        id:'wsdid',
        totalProperty:'totalCount',
        fields: [
        	"wsdid",
			"qhdm",
			"qhmc",
			"dwdm",
			"dwmc",
			"xzjd",
			"xzjdmc",
			"qyrq",
			"ksd",
			"jsd",
			"bz"
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
	var lnwsdwhGrid = new Ext.grid.GridPanel({
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
	var modifylnwsd_window = new Gnt.ux.SelectLnwsdModify({
		//选择立户信息回调
		callback: function(optype, lnwsdModify){
			//提交数据
			Gnt.submit(
					lnwsdModify, 
					"gl/xtkzgl/lnwsdwh/modifyLnwsdwhInfo", 
					"正在修改历年尾数段信息，请稍后...", 
					function(jsonData,params){
						showInfo("历年尾数段修改成功！");
						lnwsdwhGrid.store.reload(); 
					}
			);
		}
	});
	
	var addlnwsd_window = new Gnt.ux.SelectLnwsdAdd({
		//选择立户信息回调
		callback: function(optype, lnwsdAdd){
			//提交数据
			Gnt.submit(
					lnwsdAdd, 
					"gl/xtkzgl/lnwsdwh/addLnwsdwhInfo", 
					"正在新增历年尾数段信息，请稍后...", 
					function(jsonData,params){
						showInfo("历年尾数段信息新增成功！");
						lnwsdwhGrid.store.reload(); 
					}
			);
		}
	});
	var fs = new Ext.form.FormPanel({
		id:'form1',
    	title:'',
    	anchor:'100% 100%',
    	buttonAlign:'right',
    	labelAlign:'right',
    	frame:true,
    	border:false,
        layout:'form',
        labelWidth:75,
       	items:[{
        		layout:'column',
    			frame:false,
    			border:false,
        		defaults:{
        			border:false,
        			frame:false
        		},
            	items:[{
		           	columnWidth:.20,
	    	        layout: 'form',
	    	        bodyStyle:'padding:0 0 0 0',
	        	    items: [{anchor:'100%',
						xtype:'search_combox',
						anchor:'100%',
						name:'qhdm',
						id:'qhdmQuery',
						searchUrl : "dict/utils/searchXzqh.json?qybz=1",
						fieldLabel:'区划代码'
					}]
				},{
		           	columnWidth:.20,
	    	        layout: 'form',
	    	        bodyStyle:'padding:0 0 0 0',
	        	    items: [{anchor:'100%',
						xtype:'search_combox',
						anchor:'100%',
						name:'dwdm',
						id:'dwdmQuery',
						searchUrl : "dict/utils/searchXxb?visiontype=DWXXB&whflg=1",
						fieldLabel:'单位代码'
					}]
				},{
		           	columnWidth:.40,
	    	        layout: 'form',
	    	        bodyStyle:'padding:0 0 0 0'
				},{
		           	columnWidth:.20,
	    	        layout: 'form',
	    	        bodyStyle:'padding:0 0 0 0',
	    	        items:[
	    	        	new Ext.Button({
	    					text:'查询',
	    					minWidth:75,
	    					handler:function(){
	    						var p =  fs.getForm().getValues();
	    						delete p.zzid;
	    						p.qhdm=Ext.getCmp('qhdmQuery').getValue();
	    						p.dwdm=Ext.getCmp('dwdmQuery').getValue();
	    						var store = lnwsdwhGrid.store;
	    						store.baseParams =p;
	    						store.load({params:{start:0, limit:20}});	
	    						store.on("load",function(store) {  
	    							if(store.data.length > 0){
	    								curIndex = 0;
	    								lnwsdwhGrid.fireEvent("rowclick",lnwsdwhGrid,curIndex);
	    								lnwsdwhGrid.getSelectionModel().selectRange(curIndex,curIndex);
	    							}
	    						},lnwsdwhGrid); 
	    					}
	    				})
	    	        ]
				}]
		}]
	});
//	{
//		border:false,
//		frame: false,
//    	region:'center',
//    	//					上	右	下	左
//    	bodyStyle: 'padding:10px 0px 10px 0px ',
//		layout:'table',
//		layoutConfig: {
//        	columns: 30
//        },
//		items: [
//			{
//				border:false,
//				frame: false,
//				width:10
//			},{
//				width: 70,
//				html:'单位代码',
//				border:false,
//				frame: false
//			},{
////				xtype:'search_combox',
//				anchor:'100%',
//				xtype:'search_combox',
//				anchor:'100%',
//				id:'dwdmQuery',
//				name:'dwdm',
//				width: 150,
//				searchUrl : "dict/utils/searchXxb?visiontype=DWXXB&whflg=1"
//			},{
//				width: 70,
//				html:'受理日期',
//				border:false,
//				frame: false
//			},{
//				xtype:'datefield',
//				anchor:'100%',
//				format:'Ymd',
//				width: 150,
//				id:'slrqQuery',
//				name:'slrq',
//				value:Ext.util.Format.date(new Date(),"Y-m-d"),	
//				fieldLabel:''
//			},{
//				width: 10,
//				border:false,
//				frame: false
//			},{
//				border:false,
//				frame: false,
//				width:100,
//				style:'margin-top:-23px',
////				bodyStyle: 'padding:0px 0px 0px 0px ',
//				buttons:[
//					new Ext.Button({
//						text:'查询',
//						minWidth:80,
//						handler:function(){
//							alert(Ext.getCmp('slrqQuery').getValue());
//							return
//							var store = lnwsdwhGrid.store;
//							store.baseParams = {
//									slqr:Ext.getCmp('slrqQuery').getValue(),
//									dwdm:Ext.getCmp('dwdmQuery').getValue(),
//									qybz:qybz
//							};
//							store.load({params:{start:0, limit:20}});	
//							store.on("load",function(store) {  
//								if(store.data.length > 0){
//									curIndex = 0;
//									lnwsdwhGrid.fireEvent("rowclick",lnwsdwhGrid,curIndex);
//									lnwsdwhGrid.getSelectionModel().selectRange(curIndex,curIndex);
//								}
//							},lnwsdwhGrid); 
//						}
//					})
//				]
//			},{
//				width: 10,
//				border:false,
//				frame: false
//			}
//		]
//	}
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
						var store = lnwsdwhGrid.store;
						store.baseParams = {
								qhdm:Ext.getCmp('qhdmQuery').getValue(),
								dwdm:Ext.getCmp('dwdmQuery').getValue()
						};
						store.load({params:{start:0, limit:20}});	
						store.on("load",function(store) {  
							if(store.data.length > 0){
								curIndex = 0;
								lnwsdwhGrid.fireEvent("rowclick",lnwsdwhGrid,curIndex);
								lnwsdwhGrid.getSelectionModel().selectRange(curIndex,curIndex);
							}
						},lnwsdwhGrid); 
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
							id:'addBtn',
							handler:function(){
								addlnwsd_window.show();
								addlnwsd_window.setSelRes();
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
								modifylnwsd_window.show(selectedSelRes);
								modifylnwsd_window.setSelRes(selectedSelRes);
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
								if(window.confirm('是否确定要删除【'+selectedSelRes.data.dwmc+'】启用日期为'+selectedSelRes.data.qyrq+'【'+selectedSelRes.data.ksd+'】至【'+selectedSelRes.data.jsd+'】的尾数段?')){
									Gnt.submit(
									{
										wsdid:selectedSelRes.data.wsdid}, 
										"gl/xtkzgl/lnwsdwh/delLnwsdwhInfo", 
										"正在删除受理号序列，请稍后...", 
										function(jsonData,params){
											showInfo("受理号序列删除成功！");
											lnwsdwhGrid.store.reload(); 
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
		items:[p1_1,lnwsdwhGrid,p1_3]
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
//	var store = lnwsdwhGrid.store;
//	store.baseParams = {
//			config_key:'querySjzdInfo',
//			start:0,
//			limit:20
//	};
//	store.load();	
//	store.on("load",function(store) {  
//		if(store.data.length > 0){
//			curIndex = 0;
//			lnwsdwhGrid.fireEvent("rowclick",lnwsdwhGrid,curIndex);
//			lnwsdwhGrid.getSelectionModel().selectRange(curIndex,curIndex);
//		}
//	},lnwsdwhGrid); 
});