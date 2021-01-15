var selectYw = 1;
var queryFlag = null;
var qybz = 1;

var selectedSelRes=null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	var csm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var colModel = new Ext.grid.ColumnModel([
		{
			header: "动作id",
	        dataIndex: "dzid",
	        sortable: true,
	        hidden:true,
			width: 120
		},{
	        header: "动作名称",
	        dataIndex: "dzmc",
	        sortable: true,
			width: 120
	    },{
	        header: "动作描述",
	        dataIndex: "ms",
	        sortable: true,
			width: 250
	    },{
	        header: "范围级别",
	        dataIndex: "fwjb",	
	        sortable: true,
			width:120
	    },{
	        header: "启用标志",
	        dataIndex: "qybz",	
	        sortable: true,
			width: 120,
			renderer:function(value){
				return value;
			}
	    }
	]);
 	var myuserStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'gl/xtkzgl/spdzwh/getSpdzInfo',
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        totalProperty:'totalCount',
        fields: [
        	"dzid",
			"dzmc",
			"ms",
			"fwjb",
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
	var spdzGrid = new Ext.grid.GridPanel({
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
	var modifyspdzWindow = new Gnt.ux.SelectSpdzWhModify({
		//选择立户信息回调
		callback: function(optype, sjzdModify){
			//提交数据
			Gnt.submit(
					sjzdModify, 
					"gl/xtkzgl/spdzwh/modifySpdz", 
					"正在修改审批动作，请稍后...", 
					function(jsonData,params){
						showInfo("审批动作修改成功！");
						spdzGrid.store.reload(); 
					}
			);
		}
	});
	
	var addspdzWindow = new Gnt.ux.SelectSpdzWhAdd({
		//选择立户信息回调
		callback: function(optype, sjzdAdd){
			//提交数据
			Gnt.submit(
					sjzdAdd, 
					"gl/xtkzgl/spdzwh/addSpdz", 
					"正在新增审批动作，请稍后...", 
					function(jsonData,params){
						showInfo("审批动作新增成功！");
						spdzGrid.store.reload(); 
					}
			);
		}
	});
	var p1 = new Ext.Panel({
		layout:'border',
		items:[spdzGrid,			{
			id:"p2SouthId",
        	region:'south',
//        	height:120,
        	height:60,
            border:false,
            frame:false,
            items:[{
	            autoHeight: true,
	            items: [{
					border:false,
					frame: false,
		        	region:'center',
		        	//					上	右	下	左
		        	bodyStyle: 'padding:10px 0px 10px 0px ',
					layout:'column',
					layoutConfig: {
			        	columns: 30
			        },
					items: [
						{
							columnWidth: 0.05,
							border:false,
							frame: false
						},{
		            	id:'c21',
		            	xtype:'checkbox',
		            	columnWidth: 0.15,
		            	boxLabel: '只显示不启用的记录',
		            	name:'qybz',
		            	inputValue : "0",
		            	listeners:{
		            		'check':function (ck, checked){
		            			if (checked) {
		            				qybz = 0;
		            				Ext.getCmp('addBtn').disable();
		            				Ext.getCmp('modifyBtn').hide();
		            				Ext.getCmp('resumeBtn').show();
		            				Ext.getCmp('delBtn').disable();
		            			}else{
		            				qybz = 1;
		            				Ext.getCmp('resumeBtn').show();
		            				Ext.getCmp('addBtn').enable();
		            				Ext.getCmp('modifyBtn').hide();
		            				Ext.getCmp('delBtn').enable();
		            			}
		            			var store = spdzGrid.store;
	            				store.baseParams = {
	            						qybz:qybz
	            					};
	            				store.load({params:{start:0, limit:20}});
		            		}
		            	}
		            },{
		            		columnWidth: 0.4,
							border:false,
							frame: false
						},{
							border:false,
							frame: false,
							style:'margin-top:-25px',
							columnWidth: 0.4,
//							bodyStyle: 'padding:0px 0px 0px 0px ',
							buttons:[
								new Ext.Button({
									text:'增加',
									id:'addBtn',
									minWidth:80,
									handler:function(){
										addspdzWindow.show();
										addspdzWindow.setSelRes();
									}
								}),new Ext.Button({
									text:'恢复',
									id:'resumeBtn',
									hidden:true,
									minWidth:80,
									handler:function(){
										Gnt.submit(
											{
												dzid:selectedSelRes.data.dzid,
												qybz:1}, 
												"gl/xtkzgl/spdzwh/resumeSpdz", 
												"正在 恢复审批动作，请稍后...", 
												function(jsonData,params){
													showInfo("审批动作恢复成功！");
													selectedSelRes=null;
													spdzGrid.store.reload(); 
											}
										);
									}
								}),
								new Ext.Button({
									text:'修改',
									id:'modifyBtn',
									minWidth:80,
									handler:function(){
										modifyspdzWindow.show(selectedSelRes);
										modifyspdzWindow.setSelRes(selectedSelRes);
									}
								}),
								new Ext.Button({
									text:'删除',
									id:'delBtn',
									minWidth:80,
									disabled: !user.isadmin?true:false,
									handler:function(){
										if(selectedSelRes){
											if(window.confirm('是否确定要删除【'+selectedSelRes.data.dzmc+'】?')){
												Gnt.submit(
												{
													dzid:selectedSelRes.data.dzid}, 
													"gl/xtkzgl/spdzwh/deleteSpzd", 
													"正在删除审批动作，请稍后...", 
													function(jsonData,params){
														showInfo("审批动作删除成功！");
														spdzGrid.store.reload(); 
													}
												);
											}
										}else{
											alert("请选中一条有效的数据，再点击修改！");
										}
									}
								}),
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
				}]
				}
            ]
			
		}]/*,
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
	var store = spdzGrid.store;
	store.baseParams = {
			qybz:qybz
	};
	store.load({params:{start:0, limit:20}});	
	store.on("load",function(store) {  
		if(store.data.length > 0){
			curIndex = 0;
			spdzGrid.fireEvent("rowclick",spdzGrid,curIndex);
			spdzGrid.getSelectionModel().selectRange(curIndex,curIndex);
		}
	},spdzGrid); 
});