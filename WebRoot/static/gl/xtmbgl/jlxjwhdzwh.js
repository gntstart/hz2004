var selectYw = 1;
var queryFlag = null;
var qybz = 1;
var selValue=1;
var selectedSelRes=null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10019,20021",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	var sfbz = [[1,'1—街路巷代码'],[2,'2—居委会代码']]

	var csm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var colModel = new Ext.grid.ColumnModel([
		{
			header: "",
	        dataIndex: "czid",
	        sortable: true,
	        hidden:true,
			width: 120
		},{
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
	        sortable: true,
	        hidden:true,
			width: 120
	    },{
	        header: "街路巷类型",
	        dataIndex: "jlxhlxmc",
	        sortable: true,
			width: 120
	    },{
	        header: "门口牌号",
	        dataIndex: "jlxh",	
	        sortable: true,
			width:120
	    },{
			header: "启用标志",
	        dataIndex: "qybzmc",
	        sortable: true,
			width: 120
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
		},{
			header: "居委会中文拼音",
	        dataIndex: "jwhzwpy",
	        sortable: true,
			width: 120,
			hidden:true
		},{
			header: "街路巷中文拼音",
	        dataIndex: "jlxzwpy",
	        sortable: true,
			width: 120,
			hidden:true
		}
		,{
			header: "居委会五笔拼音",
	        dataIndex: "jwhwbpy",
	        sortable: true,
			width: 120,
			hidden:true
		},{
			header: "街路巷五笔拼音",
	        dataIndex: "jlxwbpy",
	        sortable: true,
			width: 120,
			hidden:true
		}
	]);
 	var myuserStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'gl/xtmbgl/jlxjwhdzwh/getJlxjwhdzInfo',//yw/hjyw/hzywcl/queryHzywcl
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        id:'czid',
        totalProperty:'totalCount',
        fields: [
        	"czid",
			"jlxdm",
			"jlxmc",
			"jwhdm",
			"jwhmc",
			"jlxhlx",
			"jlxhlxmc",
			"jlxh",
			"qybzmc",
			"bdlx",
			"bdsj",
			"jwhzwpy",
			"jlxzwpy",
			"jwhwbpy",
			"jlxwbpy"
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
	var jlxjwhdzGrid = new Ext.grid.GridPanel({
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
	
	var xtmbgl_window=new Gnt.ux.xtmbglwin({
		callback: function(){
		
		}
	});
	
	var modifyjlxjwhdzwh_window = new Gnt.ux.SelectJlxjwhdzWhModify({
		//选择立户信息回调
		callback: function(optype, jlxjwhdzxxModify){
			//提交数据
			Gnt.submit(
					jlxjwhdzxxModify, 
					"gl/xtmbgl/jlxjwhdzwh/modifyJlxjwhdz", 
					"正在修改单位信息，请稍后...", 
					function(jsonData,params){
						showInfo("单位信息修改成功！");
						jlxjwhdzGrid.store.reload(); 
					}
			);
		}
	});
	
	var addjlxjwhdzwh_window = new Gnt.ux.SelectJlxJwhdzWhAdd({
		//选择立户信息回调
		callback: function(optype, jlxjwhdzxxAdd){
			//提交数据
			Gnt.submit(
					jlxjwhdzxxAdd, 
					"gl/xtmbgl/jlxjwhdzwh/addJlxjwhdz", 
					"正在新增单位信息，请稍后...", 
					function(jsonData,params){
						showInfo("单位信息新增成功！");
						jlxjwhdzGrid.store.reload(); 
					}
			);
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
            			var store = jlxjwhdzGrid.store;
        				store.baseParams = {
        						jlxdm:Ext.getCmp('jlxdmQuery').getValue(),
								jwhdm:Ext.getCmp('jwhdmQuery').getValue(),
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
								var data=jlxjwhdzGrid.store.data.items;
								xtmbgl_window.show();
								xtmbgl_window.setSelRes(data,'jlxjwh');
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
								addjlxjwhdzwh_window.show();
								addjlxjwhdzwh_window.setSelRes();
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
									modifyjlxjwhdzwh_window.show(selectedSelRes);
									modifyjlxjwhdzwh_window.setSelRes(selectedSelRes);
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
											czid:selectedSelRes.data.czid,
											qybz:1}, 
											"gl/xtmbgl/jlxjwhdzwh/resumeJlxjwhdz", 
											"正在 恢复街路巷居委会对照，请稍后...", 
											function(jsonData,params){
												showInfo("街路巷居委会对照恢复成功！");
												selectedSelRes = null;
												jlxjwhdzGrid.store.reload(); 
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
							if(window.confirm('是否确定要删除【'+selectedSelRes.data.jlxdm+'】【'+selectedSelRes.data.jwhdm+'】?')){
								Gnt.submit(
								{
									czid:selectedSelRes.data.czid}, 
									"gl/xtmbgl/jlxjwhdzwh/delJlxjwhdz", 
									"正在删除街路巷居委会对照,请稍后...", 
									function(jsonData,params){
										showInfo("街路巷居委会对照删除成功！");
										jlxjwhdzGrid.store.reload(); 
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
			height:60,
            border:false,
            frame:false,
            items:[{
				border:false,
				frame: false,
	        	region:'center',
	        	//					上	右	下	左
	        	bodyStyle: 'padding:10px 0px 10px 0px ',
				layout:'table',
				layoutConfig: {
		        	columns: 30
		        },
				items: [
					{
						border:false,
						frame: false,
						width:10
					},{
						width: 170,
						html:'请选择并输入查询范围',
						border:false,
						frame: false
					},{
							border:false,
							frame: false,
							width:120,
							id:'queryFlag',
				    		xtype : "combo",
				    		store : sfbz,
				    		value:'1',
							fieldLabel:"",
							triggerAction:"all",
							maxHeight : 80,
							listeners:{
								select: function (a, b){
			            			selValue = b.data.value;
			            			if(selValue==1){
			            				Ext.getCmp('jlxdmQuery').show();
			            				Ext.getCmp('jwhdmQuery').hide();
			            			}else if(selValue==2){
			            				Ext.getCmp('jlxdmQuery').hide();
			            				Ext.getCmp('jwhdmQuery').show();
			            			}
			            		}
			            	}
					},{
						border:false,
						frame: false,
						width:10
					},{
//						xtype:'search_combox',
						anchor:'100%',
						xtype:'search_combox',
						anchor:'100%',
						id:'jlxdmQuery',
						name:'jlxdm',
						height:100,
						width: 150,
						searchUrl : "dict/utils/searchXxb?visiontype=JLXXXB"
					},{
						xtype:'search_combox',
						anchor:'100%',
						searchUrl:'dict/utils/searchXxb?visiontype=JWHXXB',
//						fields:["code","name"],
//						valueField: "code",
//						displayField: "name",
						id:'jwhdmQuery',
						hidden:true,
						width: 150,
						fieldLabel:'所属区划',
						hiddenName:'jwhdm'
					},{
						width: 10,
						border:false,
						frame: false
					},{
						border:false,
						frame: false,
						width:100,
						style:'margin-top:-17px',
//						bodyStyle: 'padding:0px 0px 0px 0px ',
						buttons:[
							new Ext.Button({
								text:'查询',
								minWidth:80,
								handler:function(){
									var myuserStore = jlxjwhdzGrid.store;
									myuserStore.baseParams = {
											jlxdm:Ext.getCmp('jlxdmQuery').getValue(),
											jwhdm:Ext.getCmp('jwhdmQuery').getValue(),
											qybz:qybz
									};
									myuserStore.load({params:{start:0, limit:20}});	
									myuserStore.on("load",function(store) {  
										if(store.data.length > 0){
											curIndex = 0;
											jlxjwhdzGrid.fireEvent("rowclick",jlxjwhdzGrid,curIndex);
											jlxjwhdzGrid.getSelectionModel().selectRange(curIndex,curIndex);
										}
									},jlxjwhdzGrid); 
								}
							})
						]
					},{
						width: 10,
						border:false,
						frame: false
					}
				]
			}]
		},jlxjwhdzGrid,p1_3]/*,
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