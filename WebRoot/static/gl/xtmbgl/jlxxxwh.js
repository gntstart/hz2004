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
			header: "街路巷代码",
	        dataIndex: "dm",
	        sortable: true,
			width: 120
		},{
			header: "街路巷名称",
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
 			url: 'gl/xtmbgl/jlxxxwh/getJlxxxInfo',//yw/hjyw/hzywcl/queryHzywcl
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
	var jlxxxGrid = new Ext.grid.GridPanel({
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
	var modifyjlxxxwh_window = new Gnt.ux.SelectJlxxxWhModify({
		//选择立户信息回调
		callback: function(optype, jwzrqxxModify){
			//提交数据
			Gnt.submit(
					jwzrqxxModify, 
					"gl/xtmbgl/jlxxxwh/modifyJlxxx", 
					"正在修改街路巷信息，请稍后...", 
					function(jsonData,params){
						showInfo("街路巷信息修改成功！");
						jlxxxGrid.store.reload(); 
					}
			);
		}
	});
	
	var addjlxxxwh_window = new Gnt.ux.SelectJlxxxWhAdd({
		//选择立户信息回调
		callback: function(optype, jwzrqxxAdd){
			//提交数据
			Gnt.submit(
					jwzrqxxAdd, 
					"gl/xtmbgl/jlxxxwh/addJlxxx", 
					"正在新增街路巷信息，请稍后...", 
					function(jsonData,params){
						showInfo("街路巷信息新增成功！");
						jlxxxGrid.store.reload(); 
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
				xtype:'search_combox',
				anchor:'100%',
				searchUrl:'dict/utils/searchXzqh.json?qybz=1',
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
				width: 70,
				html:'街路巷',
				border:false,
				frame: false
			},{anchor:'100%',
				xtype:'search_combox',
				anchor:'100%',
				id:'jlxdmQuery',
				name:'jlxdm',
				width: 150,
				searchUrl : "dict/utils/searchXxb?visiontype=JLXXXB"
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
						var store = jlxxxGrid.store;
						store.baseParams = {
								qhdm:Ext.getCmp('qhdmQuery').getValue(),
								jlxdm:Ext.getCmp('jlxdmQuery').getValue(),
								qybz:qybz
						};
						store.load({params:{start:0, limit:20}});	
						store.on("load",function(store) {  
							if(store.data.length > 0){
								curIndex = 0;
								jlxxxGrid.fireEvent("rowclick",jlxxxGrid,curIndex);
								jlxxxGrid.getSelectionModel().selectRange(curIndex,curIndex);
							}
						},jlxxxGrid); 
					}
        		})]
    		},{
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
    		},{
            	id:'c21',
            	xtype:'checkbox',
            	columnWidth: .2,
            	boxLabel: '只显示不启用的记录',
            	name:'qybz',
            	width: 180,
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
            			var store = jlxxxGrid.store;
        				store.baseParams = {
        						qhdm:Ext.getCmp('qhdmQuery').getValue(),
								jlxdm:Ext.getCmp('jlxdmQuery').getValue(),
								qybz:qybz
        					};
        				store.load({params:{start:0, limit:20}});
            		}
            	}
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
				width: 90,
				html:'街路巷名称',
				border:false,
				frame: false
			},
    		{
				id:'queryValue',
    			xtype : 'textfield',
    			border:false,
    			frame:false,
    			fieldLabel:"",
    			columnWidth: .1,
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
				items:[
					new Ext.Button({
						text:'查找',
						minWidth:80,
						handler:function(){
							if(!Ext.getCmp('queryValue').getValue()){
								alert("请输入需要查找的相关信息标记!");
								return;
							}
							var store = jlxxxGrid.store;
							if(store.data.length > 0){
								for(var i= 0;i<store.data.length;i++){
									var res = store.getAt(i);
									var b = Ext.getCmp('queryValue').getValue();
									var a = res.data.mc;
									if(a.indexOf(b.toUpperCase())!=-1){
										jlxxxGrid.fireEvent("rowclick",jlxxxGrid,i);
										jlxxxGrid.getSelectionModel().selectRange(i,i);
										jlxxxGrid.getView().focusRow(i);
										return;
									}
								}
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
    		        	   text:'保存',
							minWidth:80,
							handler:function(){
								var data=jlxxxGrid.store.data.items;
								xtmbgl_window.show();
								xtmbgl_window.setSelRes(data,'jlxxxwh');
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
								addjlxxxwh_window.show();
								addjlxxxwh_window.setSelRes();
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
									modifyjlxxxwh_window.show(selectedSelRes.data);
									modifyjlxxxwh_window.setSelRes(selectedSelRes.data);
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
											"gl/xtmbgl/jlxxxwh/resumeJlxxx", 
											"正在 恢复街路巷，请稍后...", 
											function(jsonData,params){
												showInfo("街路巷恢复成功！");
												selectedSelRes=null;
												jlxxxGrid.store.reload(); 
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
										"gl/xtmbgl/jlxxxwh/delJlxxx", 
										"正在删除街路巷信息，请稍后...", 
										function(jsonData,params){
											showInfo("街路巷删除成功！");
											jlxxxGrid.store.reload(); 
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
		items:[p1_1,jlxxxGrid,p1_3]/*,
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
});