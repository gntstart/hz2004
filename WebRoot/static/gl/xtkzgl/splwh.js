var selectYw = 1;
var queryFlag = null;
var rynbid = null;
var qybz =1;
var selectedSelRes=null;
var selectedMbdzSelRes=null;
var mbspselectedSelRes=null;
var xygdzArray = [];
Ext.onReady(function(){
	Ext.QuickTips.init();
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10019,20021",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	var sfbz = [[1,'控制类别'],[2,'控制名称']]
	var mbspcsm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var mbspcolModel = new Ext.grid.ColumnModel([

		{
			header: "",
	        dataIndex: "spmbid",
	        sortable: true,
	        hidden:true,
			width: 100
		},{
			header: "",
	        dataIndex: "mblcid",
	        sortable: true,
	        hidden:true,
			width: 100
		},{
			header: "",
	        dataIndex: "dzid",
	        sortable: true,
	        hidden:true,
			width: 100
		},{
			header: "动作名称",
	        dataIndex: "dzmc",
	        sortable: true,
//	        hidden:true,
			width: 100
		},{
			header: "动作值",
	        dataIndex: "dzz",
	        sortable: true,
//	        hidden:true,
			width: 100
		},{
			header: "下个动作名称",
	        dataIndex: "xgdzmc",
	        sortable: true,
//	        hidden:true,
			width: 100
		},{
			header: "动作标志",
	        dataIndex: "dzbz",
	        sortable: true,
//	        hidden:true,
			width: 100
		}
	]);
 	var mbspStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'gl/xtkzgl/splwh/getMbsplcInfo',//yw/hjyw/hzywcl/queryHzywcl
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        id:'id',
        totalProperty:'totalCount',
        fields: [
        	"spmbid",
        	"mblcid",
        	"dzid",
        	"dzmc",
        	"dzz",
        	"xgdzmc",
        	"dzbz"
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
	var mbspGrid = new Ext.grid.GridPanel({
        store: mbspStore,
        region: 'center',
        view:new Ext.grid.GridView({
				//forceFit:true,
				//autoFill:true,
				enableRowBody:true
		}),
		stripeRows: true,
        cm: mbspcolModel,
        sm:mbspcsm,
        height:400,
		loadMask: {msg:'正在加载数据，请稍侯……'},
		bodyStyle:'width:90% ',
        border:true,
        anchor:'50% 50%',
	    margins: '0 0 0 0',
		cmargins: '0 0 0 0',        
        frame:false,
		iconCls:'icon-grid',
        title:'',
        listeners:{
        	rowclick:function(g, rowIndex, e){
        		mbspselectedSelRes = g.store.getAt(rowIndex);
        	},
			rowdblclick:function(g, rowIndex, e){
				mbspselectedSelRes = g.store.getAt(rowIndex);
			}
		}
    });	
	var csm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var colModel = new Ext.grid.ColumnModel([
		{
			header: "模板id",
	        dataIndex: "spmbid",
	        sortable: true,
	        hidden:true,
			width: 120
		},{
			header: "模板名称",
	        dataIndex: "mbmc",
	        sortable: true,
//	        hidden:true,
			width: 120
		},{
			header: "模板等级",
	        dataIndex: "mbdj",
	        sortable: true,
	        hidden:true,
			width: 120
		},{
			header: "模板等级",
	        dataIndex: "mbdjmc",
	        sortable: true,
//	        hidden:true,
			width: 120
		},{
			header: "创建人姓名",
	        dataIndex: "cjryhxm",
	        sortable: true,
			width: 120
		},{
	        header: "创建时间",
	        dataIndex: "cjsj",
	        sortable: true,
			width: 120
	    },{
	        header: "修改人姓名",
	        dataIndex: "xgryhxm",
	        sortable: true,
			width: 120
	    },{
	        header: "修改时间",
	        dataIndex: "xgsj",	
	        sortable: true,
			width:120
	    },{
	        header: "当前使用数",
	        dataIndex: "dqsys",
	        sortable: true,
			width: 120
	    },{
	        header: "启用标志",
	        dataIndex: "qybzmc",	
	        sortable: true,
			width:120
	    }
	]);
 	var myuserStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'gl/xtkzgl/splwh/getSpmbInfo',//yw/hjyw/hzywcl/queryHzywcl
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        id:'id',
        totalProperty:'totalCount',
        fields: [
        	"spmbid",
        	"mbmc",
        	"mbdj",
        	"mbdjmc",
			"cjryhxm",
			"cjsj",
			"xgryhxm",
			"xgsj",
			"dqsys",
			"qybzmc"
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
	var splGrid = new Ext.grid.GridPanel({
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
        autoHeight:true,
		loadMask: {msg:'正在加载数据，请稍侯……'},
		bodyStyle:'width:100% ',
        border:true,
        anchor:'50% 50%',
	    margins: '0 0 0 0',
		cmargins: '0 0 0 0',        
        frame:false,
		iconCls:'icon-grid',
        title:'',
        listeners:{
        	rowclick:function(g, rowIndex, e){
        		selectedSelRes = g.store.getAt(rowIndex);
        		Ext.getCmp('selectedMbmc').setValue(selectedSelRes.data.mbmc);
        		Ext.getCmp('selectedMbmc2').setValue(selectedSelRes.data.mbmc);
        		var store = mbspGrid.store;
        		store.baseParams = {
        				spmbid:selectedSelRes.data.spmbid
        		};
        		store.load({params:{start:0, limit:20}});	
        		store.on("load",function(store) {  
        			if(store.data.length > 0){
        				mbspGrid.fireEvent("rowclick",mbspGrid,0);
        				mbspGrid.getSelectionModel().selectRange(0,0);
        			}
        		},mbspGrid); 
        	},
			rowdblclick:function(g, rowIndex, e){
				selectedSelRes = g.store.getAt(rowIndex);
				Ext.getCmp('selectedMbmc').setValue(selectedSelRes.data.mbmc);
				Ext.getCmp('selectedMbmc2').setValue(selectedSelRes.data.mbmc);
			}
		},
        bbar: new Ext.PagingToolbar({
				pageSize: 20,
				store: myuserStore,
				displayInfo: true
		})
    });
	var mbdzcsm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var mbdzcolModel = new Ext.grid.ColumnModel([
		{
			header: "动作id",
	        dataIndex: "dzid",
	        sortable: true,
	        hidden:true
		},{
			header: "动作名称",
	        dataIndex: "dzmc",
	        sortable: true,
//	        hidden:true,
			width: '50%'
		},{
			header: "动作描述",
	        dataIndex: "ms",
	        sortable: true,
//	        hidden:true,
			width: '50%'
		}
	]);
 	var mbdzStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'gl/xtkzgl/splwh/getSpdzInfo',//yw/hjyw/hzywcl/queryHzywcl
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        totalProperty:'totalCount',
        fields: [
        	"dzid",
        	"dzmc",
        	"ms"
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
	var mbdzGrid = new Ext.grid.GridPanel({
        store: mbdzStore,
        region: 'center',
		stripeRows: true,
        cm: mbdzcolModel,
        sm:mbdzcsm,
        height:400,
		loadMask: {msg:'正在加载数据，请稍侯……'},
		bodyStyle:'width:90% ',
        border:true,
        anchor:'50% 50%',
	    margins: '0 0 0 0',
		cmargins: '0 0 0 0',        
        frame:false,
		iconCls:'icon-grid',
        title:'',
        listeners:{
        	rowclick:function(g, rowIndex, e){
        		selectedMbdzSelRes = g.store.getAt(rowIndex);
        	},
			rowdblclick:function(g, rowIndex, e){
				selectedMbdzSelRes = g.store.getAt(rowIndex);
			}
		}
    });
	var modifyspmbWindow = new Gnt.ux.SelectSpmbWhModify({
		//选择立户信息回调
		callback: function(optype, spmbModify){
			//提交数据
			Gnt.submit(
					spmbModify, 
					"gl/xtkzgl/splwh/modifySpmb", 
					"正在修改审批模板，请稍后...", 
					function(jsonData,params){
						showInfo("审批模板修改成功！");
						splGrid.store.reload(); 
					}
			);
		}
	});
	var addspmbWindow = new Gnt.ux.SelectSpmbWhAdd({
		//选择立户信息回调
		callback: function(optype, spmbModify){
			//提交数据
			Gnt.submit(
					spmbModify, 
					"gl/xtkzgl/splwh/addSpmb", 
					"正在增加审批模板，请稍后...", 
					function(jsonData,params){
						showInfo("审批模板增加成功！");
						splGrid.store.reload(); 
					}
			);
		}
	});
	var addsplWindow = new Gnt.ux.SelectSpdzlAdd({
		//选择立户信息回调
		callback: function(optype, spmbModify){
			//提交数据
			Gnt.submit(
					spmbModify, 
					"gl/xtkzgl/splwh/addSpdzl", 
					"正在增加动作流，请稍后...", 
					function(jsonData,params){
						showInfo("动作流增加成功！");
						mbspGrid.store.reload(); 
					}
			);
		}
	});
	var p1_1 = new Ext.Panel({
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
				html:'当前选中模板名称',
				width:150
    		},{
				id:'selectedMbmc',
    			xtype : 'textfield',
    			border:false,
    			frame:false,
    			fieldLabel:"",
    			name:'selectedMbmc'
			},{
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
    		},
    		{
            	id:'c21',
            	xtype:'checkbox',
            	boxLabel: '只显示不启用的记录',
            	name:'qybz',
            	inputValue : "0",
            	listeners:{
            		'check':function (ck, checked){
            			if (checked) {
            				qybz = 0;
            				Ext.getCmp('addBtn').disable();
            				Ext.getCmp('modifyBtn').hide();
            				Ext.getCmp('forbitBtn').disable();
            				Ext.getCmp('resumeBtn').show();
            			}else{
            				qybz = 1;
            				Ext.getCmp('resumeBtn').hide();
            				Ext.getCmp('addBtn').enable();
            				Ext.getCmp('modifyBtn').show();
            				Ext.getCmp('forbitBtn').enable();
            			}
            			var store = splGrid.store;
        				store.baseParams = {
        						qybz:qybz,
        						start:0,
        						limit:20
        					};
        				store.load();
            		}
            	}
            }, {
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
								addspmbWindow.show();
								addspmbWindow.setSelRes();
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
								modifyspmbWindow.show(selectedSelRes);
								modifyspmbWindow.setSelRes(selectedSelRes);
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
											spmbid:selectedSelRes.data.spmbid,
											qybz:1}, 
											"gl/xtkzgl/splwh/resumeSpmb", 
											"正在 恢复审批模板，请稍后...", 
											function(jsonData,params){
												showInfo("审批模板恢复成功！");
												selectedSelRes = null;
												splGrid.store.reload(); 
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
							text:'禁用',
							id:'forbitBtn',
							minWidth:80,
							//hidden:true,
							disabled:user.usercode!="HZADMIN"?true:false,
							handler:function(){
								if(selectedSelRes){
									if(window.confirm('是否确定要禁用【'+selectedSelRes.data.mbmc+'】?')){
										Gnt.submit(
										{
											spmbid:selectedSelRes.data.spmbid,qybz:0}, 
											"gl/xtkzgl/splwh/forbitSpmb", 
											"正在禁用审批模板，请稍后...", 
											function(jsonData,params){
												showInfo("审批模板禁用成功！");
												splGrid.store.reload(); 
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
    	id:'spmbxx',
    	title :"审批模板信息",
    	items:[splGrid,p1_1]
    });
	var p2_1 = new Ext.Panel({
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
				html:'当前选中模板名称',
				width:150
    		},{
				id:'selectedMbmc2',
    			xtype : 'textfield',
    			border:false,
    			frame:false,
    			fieldLabel:"",
    			name:'selectedMbmc2'
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
	var p2 = new Ext.Panel({
		id:'mblcxx',
		//height:'100%',
		layout:'border',
    	title : "模板流程信息",
		items:[p2_1,{
		region:'center',
		layout:'column',
		autoHeight : true, 
		items:[{
			title: '审批动作列表',
        	region:'center',
            xtype: 'fieldset',
            frame:false,
            columnWidth: .2,
            autoHeight : true, 
			items:[{
				
				items:[mbdzGrid]
				
			},{
	        	buttonAlign : 'right',
		    	buttons: [new Ext.Button({
	    		    	id:'fzId',
	        			text:'动作维护',
	        			minWidth:80,
	        			handler:function(){
	        				var url = basePath + "gl/xtkzgl/spdzwh?jumpToSpdzwh="+"1";
	    					if(parent && parent.openWorkSpace){
	    						parent.openWorkSpace(url,  "审批动作维护", "_spdzwh_");
	    					}else{
	    						window.location.href = url;
	    					}
	        			}
	        		})
	    		]
        }]
		},{
			border:false,
            frame:false,
            columnWidth: .05,
            autoHeight : true, 
    		title:'',
            items:[{
				title: '',
				border:false,
				frame: false,
				layout:'column',
				items:[{
    	    		html:'',
    	    		bodyStyle:'padding:10px 10px 10px 10px',
	   	    	 	layout:'table',
	   	    	 	align:'east',
	   	    	 	border:false,
	   	    	 	frame:false,
	   	    	 	layoutConfig: {
	   	    	 		columns: 1
	   	    	 	},
	   	    	 	items:[{
			    	    	height:30,
			    	    	border:false,
			    	    	frame:false
			    	    },new Ext.Button({
							text:'>',
							minWidth:40,
							handler:function(){
								addsplWindow.show(selectedSelRes,xygdzArray);
								addsplWindow.setSelRes(selectedMbdzSelRes,selectedSelRes,xygdzArray);
							}
			    	    }),{
			    	    	height:3,
			    	    	border:false,
			    	    	frame:false
			    	    },new Ext.Button({
							text:'<',
							minWidth:40,
							handler:function(){
								if(window.confirm('是否确定要删除【'+mbspselectedSelRes.data.dzmc+'】?')){
									Gnt.submit(
											{
												mblcid:mbspselectedSelRes.data.mblcid,
												spmbid:mbspselectedSelRes.data.spmbid}, 
											"gl/xtkzgl/splwh/removeSpdzl", 
											"正在删除动作流，请稍后...", 
											function(jsonData,params){
												showInfo("动作流删除成功！");
												mbspGrid.store.reload(); 
											}
									);
								}
							}
			    	    }),{
			    	    	height:3,
			    	    	border:false,
			    	    	frame:false
			    	    },new Ext.Button({
							text:'<<',
							minWidth:40,
							handler:function(){
								if(window.confirm('是否确定要删除【'+Ext.getCmp('selectedMbmc').getValue()+'】的全部动作?')){
									Gnt.submit(
											{spmbid:mbspselectedSelRes.data.spmbid}, 
											"gl/xtkzgl/splwh/removeAllSpdzl", 
											"正在删除审批模板下的动作流，请稍后...", 
											function(jsonData,params){
												showInfo("删除审批模板下的所有动作流成功！");
												mbspGrid.store.reload(); 
											}
									);
								}
								
							}
			    	    }),{
			    	    	height:3,
			    	    	border:false,
			    	    	frame:false
			    	    }
			    	 ]
		    	    }]
			}
            ]
			
		},{
			title: '模板审批流程',
        	region:'center',
            xtype: 'fieldset',
            frame:false,
            columnWidth: .75,
            autoHeight : true, 
            style:'margin-left:10px',
            items:[{
				anchor:'100% 100%',
				items:[mbspGrid]
				
			}
            ]
			
		}]
	
		}]
	});
	//定义分页面板
	//释放iframe占用资源
	function fixIFrame(o, p){
		var iFrame = p.getEl().dom; 
		if (iFrame.src) {
			iFrame.src = "javascript:false"; 
		}
	}

	//定义TabPanel的控制菜单
	Ext.ux.TabCloseMenu = function(){
	    var menu, ctxItem;
	    this.init = function(tabs){
	        tabs.on('contextmenu', onContextMenu);
	    }
	    function onContextMenu(tabs, item, e){
	        if(!menu){
	            menu = new Ext.menu.Menu([{
	                id: tabs.id + '-close',
	                text: '关闭当前页',
	                handler : function(){
	                    tabs.remove(ctxItem);
	                }
	            },{
	                id: tabs.id + '-close-others',
	                text: '关闭其它页',
	                handler : function(){
	                    tabs.items.each(function(item){
	                    	if(item.closable && item != ctxItem){
	                            tabs.remove(item);
	                        }
	                    });
	                }
	            }]);
	        }
	        ctxItem = item;
	        var items = menu.items;
	        
	        //设置【关闭当前页】菜单是否有效和当前页的closable属性一致
	        items.get(tabs.id + '-close').setDisabled(!item.closable);
	        
	        var disableOthers = true; 
	       	//遍历分页面板所有分页，查看除了自己，是否还有能够关闭的分页
	        tabs.items.each(function(){
	            if(this != item && this.closable){
	                disableOthers = false;
	                return false;
	            }
	        });
	        
	        //设置【关闭其它页】菜单是否有效
	        items.get(tabs.id + '-close-others').setDisabled(disableOthers);
	        
	        //在鼠标右击处显示菜单
	        menu.showAt(e.getPoint());
	    }
	};	
	   var tabs = new Ext.TabPanel({
	    	id:'tabs',
//	        activeTab: 0,
//	        width:500,
//	        height:500,
	        resizeTabs:false, 
	        enableTabScroll:true,
	        plain:false,
	        deferredRender: false,
	        //activeTab: 0,		//默认选中标签
	        listeners:{
	        	//在关闭分页的时候，调用函数释放iframe占用资源
	        	beforeremove:fixIFrame.createDelegate(this)
	        },
	        plugins: new Ext.ux.TabCloseMenu(),
	        defaults:{
	        	closable:false,
	        	autoScroll: false,
	        	frame: false,
	        	shim: false,
	        	xtype: 'panel',
	        	iconCls : 'otherfile'
	        },
	        refresh:function(){
	        	//alert(1111);
	        	//this.getUpdater().refresh();
	        	//updater.refresh();
	        },
	        items:[p1,p2]
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
        	items:[tabs]
        }
    });	
	
	var store = splGrid.store;
	store.baseParams = {
			qybz:1
	};
	store.load({params:{start:0, limit:20}});	
	store.on("load",function(store) {  
		if(store.data.length > 0){
			curIndex = 0;
			splGrid.fireEvent("rowclick",splGrid,curIndex);
			splGrid.getSelectionModel().selectRange(curIndex,curIndex);
		}
	},splGrid); 
	var spdzstore = mbdzGrid.store;
	spdzstore.baseParams = {
			qybz:1
	};
	spdzstore.load({params:{start:0, limit:20}});
	spdzstore.on("load",function(s,records) {
		var girdcount = 0;
		s.each(function(r){
			//r.get("dzmc");
			//r.get("dzid");
			//alert(r.get("dzid"));
			var obj = [r.get("dzid"),(r.get("dzid")+"-"+r.get("dzmc"))];
			xygdzArray.push(obj)
			girdcount ++ ;
			
		});
		if(records.length > 0){
			curIndex = 0;
			mbdzGrid.fireEvent("rowclick",mbdzGrid,curIndex);
			mbdzGrid.getSelectionModel().selectRange(curIndex,curIndex);
		}
	},mbdzGrid); 
	tabs.setActiveTab(0);
	tabs.setActiveTab(1);
});