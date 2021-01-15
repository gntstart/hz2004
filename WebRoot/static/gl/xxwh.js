var selectYw = 1;
var queryFlag = null;
var qybz = 1;
var errorFlag =true;
var selectedSelRes=null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10019,20021",function(){}))
		return;
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	var sfbz = [[1,'区划代码'],[2,'区划名称']];

	var csm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var colModel = new Ext.grid.ColumnModel([
		{
			header: "接收时间",
	        dataIndex: "jssj",
	        sortable: true,
			width: 120
		},{
	        header: "发送人姓名",
	        dataIndex: "fsryhxm",
	        sortable: true,
			width: 120
	    },{
	        header: "接收人姓名",
	        dataIndex: "jsryhxm",
	        sortable: true,
			width: 120
	    },{
			header: "是否已读",
	        dataIndex: "readflag",
	        sortable: true,
			width: 120,
			renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
				return value==1?"已读":"未读";
			}
		},{
			header: "消息",
	        dataIndex: "message",
	        sortable: true,
			width: 120
		},{
	        header: "消息Id",
	        dataIndex: "messageid",
	        sortable: true,
	        hidden:true,
			width: 120
	    },{
	    	header: "接收人id",
	        dataIndex: "fsryhid",
	        sortable: true,
	        hidden:true,
			width: 120
	    }
	]);
 	var myuserStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'gl/xxwh/queryXx',//yw/hjyw/hzywcl/queryHzywcl
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        id:'id',
        totalProperty:'totalCount',
        fields: [
        	"jssj",
			"fsryhxm",
			"jsryhxm",
			"readflag",
			"message",
			"messageid",
			"fsryhid"
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
 	
	var xxGrid = new Ext.grid.GridPanel({
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
	var p1_1 = new Ext.form.FormPanel({
		id:'form1',
    	title:'',
    	region:'north',
    	anchor:'100% 100%',
    	buttonAlign:'right',
    	labelAlign:'right',
    	height: 75,
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
	        	    items: [{
						xtype:'datefield',
						anchor:'100%',
						format:'Ymd',
						name:'jssj_start',
						fieldLabel:'接收时间起'
					}]
				},{
		           	columnWidth:.20,
	    	        layout: 'form',
	    	        bodyStyle:'padding:0 0 0 0',
	        	    items: [{
						xtype:'datefield',
						format:'Ymd',
						anchor:'100%',
						name:'jssj_end',
						fieldLabel:'接收时间止'
					}]
				},{
		           	columnWidth:.20,
	    	        layout: 'form',
	    	        bodyStyle:'padding:0 0 0 0',
	        	    items: [{
						xtype:'search_combox',
						anchor:'100%',
						searchUrl:'dict/utils/searchYhxx?optype=eq',
						fields:["code","name","pyt"],
						valueField: "pyt",
						displayField: "name",
						fieldLabel:'接收人',
						hiddenName:'jsryhid'
					}]
				},{
		           	columnWidth:.20,
	    	        layout: 'form',
	    	        bodyStyle:'padding:0 0 0 0',
	        	    items: [{
	        	    	xtype:'search_combox',
						anchor:'100%',
						searchUrl:'dict/utils/searchYhxx?optype=eq',
						fields:["code","name","pyt"],
						valueField: "pyt",
						displayField: "name",
						fieldLabel:'发送人',
						hiddenName:'fsryhid'
					}]
				}]
		}],
		buttons:[
			new Ext.Button({
				text:'条件清空',
				minWidth:100,
				handler:function(){
					Ext.getCmp('form1').form.reset();
				}
			}),
			new Ext.Button({
				text:'查询',
				minWidth:75,
				handler:function(){
					var p =  p1_1.getForm().getValues();
					delete p.zzid;
					myuserStore.baseParams = p;
					myuserStore.load({params:{start:0, limit:50}})
				}
			})
		]
	});	
	var p1_111 = new Ext.Panel({
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
				width: 100,
				html:'消息发送人',
				border:false,
				frame: false
			},{
				xtype:'search_combox',
				anchor:'100%',
				searchUrl:'dict/utils/searchXxb?visiontype=YHXXB',
				id:'qhdmQuery',
				width: 150,
				fieldLabel:'所属区划',
				hiddenName:'xzqhbdm'
			},{
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
    		},{
				width: 100,
				html:'消息接受人',
				border:false,
				frame: false
			},{
				anchor:'100%',
				xtype:'search_combox',
				anchor:'100%',
				id:'dwdmQuery',
				name:'dwdm',
				width: 150,
				searchUrl : "dict/utils/searchXxb?visiontype=YHXXB"
			},{
				width: 70,
				html:'接收时间起',
				border:false,
				frame: false
			},{
				anchor:'100%',
				xtype:'search_combox',
				anchor:'100%',
				id:'dwdmQuery',
				name:'dwdm',
				width: 150,
				searchUrl : "dict/utils/searchXxb?visiontype=DWXXB"
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
        				var store = xxGrid.store;
						store.baseParams = {
								xzqhbdm:Ext.getCmp('qhdmQuery').getValue(),
								dm:Ext.getCmp('dwdmQuery').getValue(),
								qybz:qybz
						};
						store.load({params:{start:0, limit:20}});	
						store.on("load",function(store) {  
							if(store.data.length > 0){
								curIndex = 0;
								xxGrid.fireEvent("rowclick",xxGrid,curIndex);
								xxGrid.getSelectionModel().selectRange(curIndex,curIndex);
							}
						},xxGrid); 
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
            			var store = xxGrid.store;
        				store.baseParams = {
        						xzqhbdm:Ext.getCmp('qhdmQuery').getValue(),
								dm:Ext.getCmp('dwdmQuery').getValue(),
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
								var data=xxGrid.store.data.items;
								xtmbgl_window.show();
								xtmbgl_window.setSelRes(data,'dwxxwh');
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
								adddwxxwh_window.show();
								adddwxxwh_window.setSelRes(errorFlag);
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
									modifydwxxwh_window.show(selectedSelRes);
									modifydwxxwh_window.setSelRes(selectedSelRes);
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
										"gl/xtmbgl/dwxxwh/resumeDwDm", 
										"正在 恢复单位，请稍后...", 
										function(jsonData,params){
											showInfo("单位信息恢复成功！");
											selectedSelRes = null;
											xxGrid.store.reload(); 
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
										"gl/xtmbgl/dwxxwh/delDwDm", 
										"正在删除单位信息，请稍后...", 
										function(jsonData,params){
											showInfo("单位信息删除成功！");
											xxGrid.store.reload(); 
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
		items:[
		       p1_1,
		       xxGrid
		]
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
//	var store = xxGrid.store;
//	store.baseParams = {
//			config_key:'querySjzdInfo',
//			start:0,
//			limit:20
//	};
//	store.load();	
//	store.on("load",function(store) {  
//		if(store.data.length > 0){
//			curIndex = 0;
//			xxGrid.fireEvent("rowclick",xxGrid,curIndex);
//			xxGrid.getSelectionModel().selectRange(curIndex,curIndex);
//		}
//	},xxGrid); 
});