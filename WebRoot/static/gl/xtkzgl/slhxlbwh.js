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
	        header: "受理日期",
	        dataIndex: "slrq",
	        sortable: true,
			width: 120
	    },{
			header: "户号当前序列号",
	        dataIndex: "slxlid",
	        sortable: true,
			width: 120
		}
	]);
 	var myuserStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'gl/xtkzgl/slhxlbwh/getSlhxlbwhInfo',//yw/hjyw/hzywcl/queryHzywcl
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
			"slrq",
			"slxlid"
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
	var slhxlbwhGrid = new Ext.grid.GridPanel({
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
	var modifyslhxlb_window = new Gnt.ux.SelectSlhxlbModify({
		//选择立户信息回调
		callback: function(optype, slhxlModify){
			//提交数据
			Gnt.submit(
					slhxlModify, 
					"gl/xtkzgl/slhxlbwh/modifySlhxlbwh", 
					"正在修改受理号序列信息，请稍后...", 
					function(jsonData,params){
						showInfo("受理号序列修改成功！");
						slhxlbwhGrid.store.reload(); 
					}
			);
		}
	});
	
	var addslhxlb_window = new Gnt.ux.SelectSlhxlbAdd({
		//选择立户信息回调
		callback: function(optype, slhhxlAdd){
			//提交数据
			Gnt.submit(
					slhhxlAdd, 
					"gl/xtkzgl/slhxlbwh/addSlhxlbwh", 
					"正在新增受理号序列信息，请稍后...", 
					function(jsonData,params){
						showInfo("受理号序列信息新增成功！");
						slhxlbwhGrid.store.reload(); 
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
						id:'dwdmQuery',
						name:'dwdm',
						searchUrl : "dict/utils/searchXxb?visiontype=DWXXB&whflg=1",
						fieldLabel:'单位代码'
					}]
				},{
		           	columnWidth:.20,
	    	        layout: 'form',
	    	        bodyStyle:'padding:0 0 0 0',
	        	    items: [{
						xtype:'datefield',
						anchor:'100%',
						format:'Ymd',
						id:'slrqQuery',
						name:'slrq',
						value:Ext.util.Format.date(new Date(),"Y-m-d"),	
						fieldLabel:'受理日期'
					
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
	    						if(!p.slrq){
	    							alert("受理日期为必填字段，请填写后再查询！");
	    							return ;
	    						}
	    						p.dwdm=Ext.getCmp('dwdmQuery').getValue();
	    						var store = slhxlbwhGrid.store;
	    						store.baseParams =p;
	    						store.load({params:{start:0, limit:20}});	
	    						store.on("load",function(store) {  
	    							if(store.data.length > 0){
	    								curIndex = 0;
	    								slhxlbwhGrid.fireEvent("rowclick",slhxlbwhGrid,curIndex);
	    								slhxlbwhGrid.getSelectionModel().selectRange(curIndex,curIndex);
	    							}
	    						},slhxlbwhGrid); 
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
//							var store = slhxlbwhGrid.store;
//							store.baseParams = {
//									slqr:Ext.getCmp('slrqQuery').getValue(),
//									dwdm:Ext.getCmp('dwdmQuery').getValue(),
//									qybz:qybz
//							};
//							store.load({params:{start:0, limit:20}});	
//							store.on("load",function(store) {  
//								if(store.data.length > 0){
//									curIndex = 0;
//									slhxlbwhGrid.fireEvent("rowclick",slhxlbwhGrid,curIndex);
//									slhxlbwhGrid.getSelectionModel().selectRange(curIndex,curIndex);
//								}
//							},slhxlbwhGrid); 
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
				html:'单位代码',
				border:false,
				frame: false
			},{
				xtype:'search_combox',
				anchor:'100%',
				id:'dwdmQuery',
				width: 150,
				name:'dwdm',
				searchUrl : "dict/utils/searchXxb?visiontype=DWXXB&whflg=1",
				fieldLabel:'单位代码'
			},{
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
    		},{
				width: 70,
				html:'受理日期',
				border:false,
				frame: false
			},{
				xtype:'datefield',
				anchor:'100%',
				format:'Ymd',
				id:'slrqQuery',
				name:'slrq',
				value:Ext.util.Format.date(new Date(),"Y-m-d"),	
				fieldLabel:'受理日期'
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
						if(!Ext.getCmp('slrqQuery').getValue()){
							alert("受理日期为必填字段，请填写后再查询！");
							return ;
						}
						var store = slhxlbwhGrid.store;
						store.baseParams = {
								dwdm:Ext.getCmp('dwdmQuery').getValue(),
								slrq:Ext.getCmp('slrqQuery').value,
								qybz:1
						};
						store.load({params:{start:0, limit:20}});	
						store.on("load",function(store) {  
							if(store.data.length > 0){
								curIndex = 0;
								slhxlbwhGrid.fireEvent("rowclick",slhxlbwhGrid,curIndex);
								slhxlbwhGrid.getSelectionModel().selectRange(curIndex,curIndex);
							}
						},slhxlbwhGrid); 
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
								addslhxlb_window.show();
								addslhxlb_window.setSelRes();
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
								modifyslhxlb_window.show(selectedSelRes);
								modifyslhxlb_window.setSelRes(selectedSelRes);
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
							id:'delBtn',
							minWidth:80,
							disabled: !user.isadmin?true:false,
							handler:function(){
								if(window.confirm('是否确定要删除【'+selectedSelRes.data.dwdm+'】【'+selectedSelRes.data.slrq+'】的受理号序列?')){
									Gnt.submit(
									{
										xlid:selectedSelRes.data.xlid}, 
										"gl/xtkzgl/slhxlbwh/delSlhxlbwh", 
										"正在删除受理号序列，请稍后...", 
										function(jsonData,params){
											showInfo("受理号序列删除成功！");
											slhxlbwhGrid.store.reload(); 
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
		items:[p1_1,slhxlbwhGrid,p1_3]
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
//	var store = slhxlbwhGrid.store;
//	store.baseParams = {
//			config_key:'querySjzdInfo',
//			start:0,
//			limit:20
//	};
//	store.load();	
//	store.on("load",function(store) {  
//		if(store.data.length > 0){
//			curIndex = 0;
//			slhxlbwhGrid.fireEvent("rowclick",slhxlbwhGrid,curIndex);
//			slhxlbwhGrid.getSelectionModel().selectRange(curIndex,curIndex);
//		}
//	},slhxlbwhGrid); 
});