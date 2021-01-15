var qybz = 1;
var queryFlag = null;
var rynbid = null;
var hideFlag = true;
var xPosition=0;
var xPosition1=0;
var selectedYwzlSelRes=null;
var selecteYwqxSelRes = null;
var selectedUserSelRes = null;
var selectedSjfwSelRes = null;
var selectedUser1SelRes = null;
var selecteddtqxSelRes = null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	Gnt.ux.Dict.loadDict(['CS_XQLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	var sfbz = [[1,'业务类别'],[2,'类别名称']];
	var sfbz1 = [[1,'用户姓名'],[2,'用户登陆名']];
	var csm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var colModel = new Ext.grid.ColumnModel([
		{
			header: "限制信息id",
	        dataIndex: "qxkzid",
	        sortable: true,
	        hidden:true,
			width: 120
		},{
			header: "业务名称",
	        dataIndex: "ywmc",
	        sortable: true,
			width: 200
		},{
			header: "辖区类型",
	        dataIndex: "xqlxmc",
	        sortable: true,
			width: 120,
			renderer:function(value){
				return Gnt.ux.Dict.getDictLable('CS_XQLX', value);
			}
		},{
			header: "辖区类型id",
	        dataIndex: "xqlx",
	        sortable: true,
	        hidden:true,
			width: 120
		},{
	        header: "数据范围限制标志",
	        dataIndex: "sjfwxzmc",
	        sortable: true,
			width: 120
	    },{
	        header: "数据范围限制标志id",
	        dataIndex: "sjfwxz",
	        hidden:true,
	        sortable: true,
			width: 120
	    },{
	        header: "用户等同查询标志",
	        dataIndex: "yhdtcxmc",	
	        sortable: true,
			width:120
	    },{
	        header: "用户等同查询标志id",
	        dataIndex: "yhdtcx",
	        hidden:true,
	        sortable: true,
			width:120
	    }
	]);
 	var myuserStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'gl/xtkzgl/cxsjfwwh/getYwqxInfo',
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        totalProperty:'totalCount',
        fields: [
        	"qxkzid",
			"ywmc",
			"xqlxmc",
			"xqlx",
			"sjfwxzmc",
			"sjfwxz",
			"yhdtcxmc",
			"yhdtcx"
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
	var ywqxInfoGrid = new Ext.grid.GridPanel({
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
		//bodyStyle:'width:50%',
        border:true,
        frame:false,
        //anchor:'50% 50%',
	    margins: '0 0 0 0',
		cmargins: '0 0 0 0',        
		iconCls:'icon-grid',
        title:'',
        listeners:{
        	rowclick:function(g, rowIndex, e){
        		selecteYwqxSelRes = g.store.getAt(rowIndex);
        	},
			rowdblclick:function(g, rowIndex, e){
				selecteYwqxSelRes = g.store.getAt(rowIndex);
			}
		},
        bbar: new Ext.PagingToolbar({
				pageSize: 20,
				store: myuserStore,
				displayInfo: true
		})
    });
	
	
	var ywzlcsm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var ywzlcolModel = new Ext.grid.ColumnModel([
		{
			header: "业务类别",
	        dataIndex: "dm",
	        sortable: true,
			width: 100
		},{
			header: "类别名称",
	        dataIndex: "mc",
	        sortable: true,
			width: 150
		}
	   
	]);
 	var ywzlStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'gl/xtkzgl/ywblxzwh/getXtywbllxInfo',
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        totalProperty:'totalCount',
        fields: [
        	"dm",
			"mc"
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
	var ywzlGrid = new Ext.grid.GridPanel({
        store: ywzlStore,
        region: 'center',
        width:300,
		stripeRows: true,
        cm: ywzlcolModel,
        sm:ywzlcsm,
		loadMask: {msg:'正在加载数据，请稍侯……'},
//		bodyStyle:'width:80%',
        border:true,
        frame:true,
//        anchor:'100% 100%',
//	    margins: '0 0 0 0',
//		cmargins: '0 0 0 0',
		iconCls:'icon-grid',
        title:'',
        listeners:{
        	rowclick:function(g, rowIndex, e){
        		selectedYwzlSelRes = g.store.getAt(rowIndex);
        		var store = ywqxInfoGrid.store;
        		store.baseParams = {
        			gnlx:selectedYwzlSelRes.data.dm,
        			qybz:qybz
        		};
        		xPosition = ywqxInfoGrid.x;
        		store.load({params:{start:0, limit:20}});	
        		store.on("load",function(store) {  
        			if(store.data.length > 0){
        				ywqxInfoGrid.fireEvent("rowclick",ywqxInfoGrid,0);
        				ywqxInfoGrid.getSelectionModel().selectRange(0,0);
        				
        			}
        		},ywqxInfoGrid);         		
        	},
			rowdblclick:function(g, rowIndex, e){
				selectedYwzlSelRes = g.store.getAt(rowIndex);
        		var store = ywqxInfoGrid.store;
        		store.baseParams = {
    				gnlx:selectedYwzlSelRes.data.dm,
        			qybz:qybz
        		};
        		store.load({params:{start:0, limit:20}});	
        		store.on("load",function(store) {  
        			if(store.data.length > 0){
        				ywqxInfoGrid.fireEvent("rowclick",ywqxInfoGrid,0);
        				ywqxInfoGrid.getSelectionModel().selectRange(0,0);
        				
        			}
        		},ywqxInfoGrid); 
			}
		}/*,
        bbar: new Ext.PagingToolbar({
			pageSize: 9999,
			store: xzlxStore,
			displayInfo: true
	})*/
    });	
	var usercsm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var usercolModel = new Ext.grid.ColumnModel([
		{
			header: "用户Id",
	        dataIndex: "yhid",
	        hidden:true,
	        sortable: true,
			width: 100
		},{
			header: "用户姓名",
	        dataIndex: "yhxm",
	        sortable: true,
			width: 100
		},{
			header: "用户登录名",
	        dataIndex: "yhdlm",
	        sortable: true,
			width: 150
		}
	   
	]);
 	var userStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'gl/xtkzgl/cxsjfwwh/getUserInfo',
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        totalProperty:'totalCount',
        fields: [
        	"yhid",
			"yhxm",
			"yhdlm"
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
	var userGrid = new Ext.grid.GridPanel({
        store: userStore,
        region: 'center',
        width:300,
		stripeRows: true,
        cm: usercolModel,
        sm:usercsm,
		loadMask: {msg:'正在加载数据，请稍侯……'},
//		bodyStyle:'width:80%',
        border:true,
        frame:true,
//        anchor:'100% 100%',
//	    margins: '0 0 0 0',
//		cmargins: '0 0 0 0',
		iconCls:'icon-grid',
        title:'',
        listeners:{
        	rowclick:function(g, rowIndex, e){
        		selectedUserSelRes = g.store.getAt(rowIndex);
        		var store = userfwInfoGrid.store;
        		store.baseParams = {
        			yhid:selectedUserSelRes.data.yhid
        		};
        		store.load({params:{start:0, limit:20}});	
        		store.on("load",function(store) {  
        			if(store.data.length > 0){
        				userfwInfoGrid.fireEvent("rowclick",userfwInfoGrid,0);
        				userfwInfoGrid.getSelectionModel().selectRange(0,0);
        				
        			}else{
        				selectedSjfwSelRes=null;
        			}
        		},userfwInfoGrid);         		
        	},
			rowdblclick:function(g, rowIndex, e){
				selectedUserSelRes = g.store.getAt(rowIndex);
        		var store = userfwInfoGrid.store;
        		store.baseParams = {
        			yhid:selectedUserSelRes.data.yhid
        		};
        		store.load({params:{start:0, limit:20}});	
        		store.on("load",function(store) {  
        			if(store.data.length > 0){
        				userfwInfoGrid.fireEvent("rowclick",userfwInfoGrid,0);
        				userfwInfoGrid.getSelectionModel().selectRange(0,0);
        				
        			}
        		},userfwInfoGrid); 
			}
		}/*,
        bbar: new Ext.PagingToolbar({
			pageSize: 9999,
			store: xzlxStore,
			displayInfo: true
	})*/
    });	
	var userfwcsm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var userfwcolModel = new Ext.grid.ColumnModel([
		{
			header: "数据范围ID",
	        dataIndex: "sjfwid",
	        hidden:true,
	        sortable: true,
			width: 120
		},{
			header: "用户登录名",
	        dataIndex: "yhdlm",
	        sortable: true,
			width: 120
		},{
			header: "用户姓名",
	        dataIndex: "yhxm",
	        sortable: true,
			width: 120
		},{
			header: "辖区类型",
	        dataIndex: "xqlx",
	        sortable: true,
			width: 120,
			renderer:function(value){
				return Gnt.ux.Dict.getDictLable('CS_XQLX', value);
			}
		},{
			header: "数据范围",
	        dataIndex: "sjfw",
	        sortable: true,
			width: 250
		}
	   
	]);
 	var userfwStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'gl/xtkzgl/cxsjfwwh/getYhsjfwInfo',
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        totalProperty:'totalCount',
        fields: [
        	"sjfwid",
        	"yhdlm",
        	"yhxm",
        	"xqlx",
			"sjfw"
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
	var userfwInfoGrid = new Ext.grid.GridPanel({
        store: userfwStore,
        region: 'center',
        width:300,
		stripeRows: true,
        cm: userfwcolModel,
        sm:userfwcsm,
		loadMask: {msg:'正在加载数据，请稍侯……'},
//		bodyStyle:'width:80%',
        border:true,
        frame:true,
//        anchor:'100% 100%',
//	    margins: '0 0 0 0',
//		cmargins: '0 0 0 0',
		iconCls:'icon-grid',
        title:'',
        listeners:{
        	rowclick:function(g, rowIndex, e){
        		selectedSjfwSelRes = g.store.getAt(rowIndex);
        	},
			rowdblclick:function(g, rowIndex, e){
				selectedSjfwSelRes = g.store.getAt(rowIndex);
			}
		}/*,
        bbar: new Ext.PagingToolbar({
			pageSize: 9999,
			store: xzlxStore,
			displayInfo: true
	})*/
    });	
	var user1csm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var user1colModel = new Ext.grid.ColumnModel([
		{
			header: "用户Id",
	        dataIndex: "yhid",
	        hidden:true,
	        sortable: true,
			width: 100
		},{
			header: "用户姓名",
	        dataIndex: "yhxm",
	        sortable: true,
			width: 100
		},{
			header: "用户登录名",
	        dataIndex: "yhdlm",
	        sortable: true,
			width: 150
		}
	   
	]);
 	var user1Store = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'gl/xtkzgl/cxsjfwwh/getUserInfo',
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        totalProperty:'totalCount',
        fields: [
        	"yhid",
			"yhxm",
			"yhdlm"
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
	var user1Grid = new Ext.grid.GridPanel({
        store: user1Store,
        region: 'center',
        width:300,
		stripeRows: true,
        cm: user1colModel,
        sm:user1csm,
		loadMask: {msg:'正在加载数据，请稍侯……'},
//		bodyStyle:'width:80%',
        border:true,
        frame:true,
//        anchor:'100% 100%',
//	    margins: '0 0 0 0',
//		cmargins: '0 0 0 0',
		iconCls:'icon-grid',
        title:'',
        listeners:{
        	rowclick:function(g, rowIndex, e){
        		selectedUser1SelRes = g.store.getAt(rowIndex);
        		var store = userdtqxInfoGrid.store;
        		store.baseParams = {
        			yhid:selectedUser1SelRes.data.yhid
        		};
        		store.load({params:{start:0, limit:20}});	
        		store.on("load",function(store) {  
        			if(store.data.length > 0){
        				userdtqxInfoGrid.fireEvent("rowclick",userdtqxInfoGrid,0);
        				userdtqxInfoGrid.getSelectionModel().selectRange(0,0);
        				
        			}
        		},userdtqxInfoGrid);         		
        	},
			rowdblclick:function(g, rowIndex, e){
				selectedUser1SelRes = g.store.getAt(rowIndex);
        		var store = userdtqxInfoGrid.store;
        		store.baseParams = {
        			yhid:selectedUser1SelRes.data.yhid
        		};
        		store.load({params:{start:0, limit:20}});	
        		store.on("load",function(store) {  
        			if(store.data.length > 0){
        				userdtqxInfoGrid.fireEvent("rowclick",userdtqxInfoGrid,0);
        				userdtqxInfoGrid.getSelectionModel().selectRange(0,0);
        				
        			}
        		},userdtqxInfoGrid); 
			}
		}/*,
        bbar: new Ext.PagingToolbar({
			pageSize: 9999,
			store: xzlxStore,
			displayInfo: true
	})*/
    });
	var userdtqxcsm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var userdtqxcolModel = new Ext.grid.ColumnModel([
		{
			header: "等同权限ID",
	        dataIndex: "dtqxid",
	        hidden:true,
	        sortable: true,
			width: 120
		},{
			header: "用户登录名",
	        dataIndex: "yhdlm",
	        sortable: true,
			width: 120
		},{
			header: "用户姓名",
	        dataIndex: "yhxm",
	        sortable: true,
			width: 120
		},{
			header: "等同用户登录名",
	        dataIndex: "dtyhdlm",
	        sortable: true,
			width: 120
		},{
			header: "等同用户姓名",
	        dataIndex: "dtyhxm",
	        sortable: true,
			width: 250
		}
	   
	]);
 	var userdtqxStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'gl/xtkzgl/cxsjfwwh/getYhdtqxInfo',
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        totalProperty:'totalCount',
        fields: [
        	"dtqxid",
        	"yhdlm",
        	"yhxm",
        	"dtyhdlm",
			"dtyhxm"
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
	var userdtqxInfoGrid = new Ext.grid.GridPanel({
        store: userdtqxStore,
        region: 'center',
        width:300,
		stripeRows: true,
        cm: userdtqxcolModel,
        sm:userdtqxcsm,
		loadMask: {msg:'正在加载数据，请稍侯……'},
//		bodyStyle:'width:80%',
        border:true,
        frame:true,
//        anchor:'100% 100%',
//	    margins: '0 0 0 0',
//		cmargins: '0 0 0 0',
		iconCls:'icon-grid',
        title:'',
        listeners:{
        	rowclick:function(g, rowIndex, e){
        		selecteddtqxSelRes = g.store.getAt(rowIndex);
        	},
			rowdblclick:function(g, rowIndex, e){
				selecteddtqxSelRes = g.store.getAt(rowIndex);
			}
		}/*,
        bbar: new Ext.PagingToolbar({
			pageSize: 9999,
			store: xzlxStore,
			displayInfo: true
	})*/
    });
	var modifysjqx_window = new Gnt.ux.SelectSjqxModify({
		//选择立户信息回调
		callback: function(optype, sjqxModify){
			//提交数据
			Gnt.submit(
					sjqxModify, 
					"gl/xtkzgl/cxsjfwwh/modifyYwqxInfo", 
					"正在修改数据权限信息，请稍后...", 
					function(jsonData,params){
						showInfo("数据权限信息修改成功！");
						ywqxInfoGrid.store.reload(); 
					}
			);
		}
	});
	var addsjfw_window = new Gnt.ux.SelectSjfwAdd({
		//选择立户信息回调
		callback: function(optype, ywblxzAdd){
			//提交数据
			Gnt.submit(
					ywblxzAdd, 
					"gl/xtkzgl/cxsjfwwh/addYhsjfwInfo", 
					"正在增加数据范围信息，请稍后...", 
					function(jsonData,params){
						showInfo("数据范围增加成功！");
						userfwInfoGrid.store.reload(); 
					}
			);
		}
	});
	var adddtqx_window = new Gnt.ux.SelectDtqxAdd({
		//选择立户信息回调
		callback: function(optype, dtqxAdd){
			//提交数据
			Gnt.submit(
					dtqxAdd, 
					"gl/xtkzgl/cxsjfwwh/addYhdtqxInfo", 
					"正在增加等同权限信息，请稍后...", 
					function(jsonData,params){
						showInfo("等同权限信息增加成功！");
						userdtqxInfoGrid.store.reload(); 
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
				border:false,
				frame: false,
				items:[new Ext.Button({
            		text:'显示/隐藏',
					minWidth:80,
					handler:function(){
						if(hideFlag){
							Ext.getCmp('weastPartP1').hide();
							ywqxInfoGrid.setPosition(0,ywqxInfoGrid.y);
						}else{
							Ext.getCmp('weastPartP1').show();
							ywqxInfoGrid.setPosition(xPosition,ywqxInfoGrid.y);
						}
						hideFlag = !hideFlag;
					}
            	})]
			},{
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
    		},{
    			frame:false,
				border:false,
				items:[new Ext.Button({
            		text:'查询范围设置',
					minWidth:80,
					handler:function(){
						userStore.load();
						userStore.on("load",function(store) {  
							if(userStore.data.length > 0){
								userGrid.fireEvent("rowclick",userGrid,0);
								userGrid.getSelectionModel().selectRange(0,0);
								
							}
						},userGrid); 
						Ext.getCmp('card').getLayout().setActiveItem(1);
						p2.doLayout();
						//p2.items.items[1].items.items[0].doLayout();
					}
            	})]
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
            		text:'等同权限设置',
					minWidth:80,
					handler:function(){
						user1Store.load();
						user1Store.on("load",function(store) {  
							if(user1Store.data.length > 0){
								user1Grid.fireEvent("rowclick",user1Grid,0);
								user1Grid.getSelectionModel().selectRange(0,0);
								
							}
						},user1Grid); 
						Ext.getCmp('card').getLayout().setActiveItem(2);
						p3.doLayout();
					}
            	})]
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
            		text:'修改权限设置',
					minWidth:80,
					handler:function(){
						modifysjqx_window.show(selectedYwzlSelRes,selecteYwqxSelRes);
						modifysjqx_window.setSelRes(selectedYwzlSelRes,selecteYwqxSelRes);
					}
            	})]
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
					text:'关闭窗体',
					minWidth:80,
					handler:function(){
						window.parent.closeWorkSpace();
					}
				})]
    		}]
    });

	var p1 = new Ext.Panel({
		layout:'border',
		items:[p1_1,{
			region: 'west',
			layout:'border',
			id:'weastPartP1',
			border:false,
            frame:false,
            width:250,
    	    margins: '0 0 0 0',
    		cmargins: '0 0 0 0',  
    		title:'',
    		items:[{
    			region:'north',
            	height:60,
    			border:false,
				frame: false,
    			items:[{
    				autoHeight: true,
		            layout:'column',
		            bodyStyle: 'padding:20px 0px 0px 0px ',
    	            items: [
    	            	{
    	            		columnWidth: .05,
							border:false,
							frame: false
						},{
							border:false,
							frame: false,
							//	bodyStyle: 'padding:10px 0px 0px 0px ',
							columnWidth: .3,
							id:'queryFlag',
				    		xtype : "combo",
				    		store : sfbz,
				    		value:'1',
							fieldLabel:"",
							triggerAction:"all",
							maxHeight : 80
						},{
							columnWidth: .05,
							border:false,
							frame: false
						},{
							id:'queryValue',
	            			xtype : 'textfield',
	            			columnWidth: .25,
	            			border:false,
	            			frame:false,
	            			fieldLabel:"",
	            			name:'queryValue'
						},{
							columnWidth: .05,
							border:false,
							frame: false
						},{
							border:false,
							frame: false,
							columnWidth: .25,
							bodyStyle: 'padding:0px 0px 0px 0px ',
							style:'margin-top:-27px',
							buttons:[
								new Ext.Button({
									text:'查询',
									minWidth:40,
									handler:function(){
										if(!Ext.getCmp('queryValue').getValue()){
											alert("请输入需要查找的相关信息标记!");
											return;
										}
										var store = ywzlGrid.store;
										if(store.data.length > 0){
											for(var i= 0;i<store.data.length;i++){
												var res = store.getAt(i);
												var b = Ext.getCmp('queryValue').getValue();
												if(Ext.getCmp('queryFlag').getValue()==1){//字段名称
													var a = res.data.dm;
													if(a.indexOf(b.toUpperCase())!=-1){
														ywzlGrid.fireEvent("rowclick",ywzlGrid,i);
														ywzlGrid.getSelectionModel().selectRange(i,i);
														ywzlGrid.getView().focusRow(i);
														return;
													}
												}else if(Ext.getCmp('queryFlag').getValue()==2){//字段含义
													var c = res.data.mc;
													if(c.indexOf(b.toUpperCase())!=-1){
														ywzlGrid.fireEvent("rowclick",ywzlGrid,i);
														ywzlGrid.getSelectionModel().selectRange(i,i);
														ywzlGrid.getView().focusRow(i);
														return;
													}
												}
											}
										}										
									}
								})
							]
						}
			        ]}]
    			
    		},{
    			width: 20,
				border:false,
				frame: false
    		},ywzlGrid
    		]
		},ywqxInfoGrid
		]
	});
	var p2_1 = new Ext.Panel({
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
				border:false,
				frame: false,
				items:[new Ext.Button({
            		text:'显示/隐藏',
					minWidth:80,
					handler:function(){
						if(hideFlag){
							xPosition1 = userfwInfoGrid.x;
							Ext.getCmp('weastPartP2').hide();
							userfwInfoGrid.setPosition(0,userfwInfoGrid.y);
						}else{
							Ext.getCmp('weastPartP2').show();
							userfwInfoGrid.setPosition(xPosition1,userfwInfoGrid.y);
						}
						hideFlag = !hideFlag;
					}
            	})]
			},{
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
    		},{
    			frame:false,
				border:false,
				items:[new Ext.Button({
            		text:'增加设置',
					minWidth:80,
					handler:function(){
						addsjfw_window.show(1);//和用户管理页面上IP增加区分开
						addsjfw_window.setSelRes(selectedYwzlSelRes);
					}
            	})]
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
            		text:'删除设置',
					minWidth:80,
					handler:function(){
						if(!selectedSjfwSelRes){
							alert("对不起，没有任何数据可被删除！");
							return;
						}
						if(window.confirm('是否确定要删除【'+selectedSjfwSelRes.data.yhxm+'】【'+selectedSjfwSelRes.data.sjfw+'】?')){
							Gnt.submit(
							{
								sjfwid:selectedSjfwSelRes.data.sjfwid}, 
								"gl/xtkzgl/cxsjfwwh/delYhsjfwInfo", 
								"正在删除数据范围，请稍后...", 
								function(jsonData,params){
									showInfo("数据范围删除成功！");
									userfwInfoGrid.store.reload(); 
								}
							);
						}
					}
            	})]
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
					text:'关闭窗体',
					minWidth:80,
					handler:function(){
						Ext.getCmp('card').getLayout().setActiveItem(0);
					}
				})]
    		}]
    });
	
	var p2 = new Ext.Panel({
		layout:'border',
		items:[p2_1,{
			region: 'west',
			layout:'border',
			id:'weastPartP2',
			border:false,
            frame:false,
            width:250,
    	    margins: '0 0 0 0',
    		cmargins: '0 0 0 0',  
    		title:'',
    		items:[{
    			
    			region:'north',
            	height:60,
            	border:false,
            	frame: false,
            	layout:'table',
				align:'center',
				bodyStyle:'padding: 10px 10px 10px 10px;',
				layoutConfig: {
					columns: 5
				},
				defaults: {
					colspan: 1,
					border:false,
					frame: false
				},
				items: [
					{
						//bodyStyle: 'margin:0px 0px 0px 10px ',
						id:'queryUserflag',
						xtype : "combo",
						store : sfbz1,
						fieldLabel:"",
						value:'1',
						width:70,
						triggerAction:"all"
					},{
						width:20
					},
					{
						id:'queryUserValue',
						width:70,
            			xtype : 'textfield',
            			name:'queryUserValue'
					},{
						width:20
					},
					new Ext.Button({
						text:'查询',
						width:40,
						//minWidth:80,
						handler:function(){
							if(!Ext.getCmp('queryUserValue').getValue()){
								alert("请输入需要查找的相关信息标记!");
								return;
							}
							var store = userGrid.store;
							if(store.data.length > 0){
								for(var i= 0;i<store.data.length;i++){
									var res = store.getAt(i);
									var b = Ext.getCmp('queryUserValue').getValue();
									if(Ext.getCmp('queryUserflag').getValue()==1){//字段名称
										var a = res.data.yhxm;
										a = a.toUpperCase();
										if(a.indexOf(b.toUpperCase())!=-1){
											userGrid.fireEvent("rowclick",userGrid,i);
											userGrid.getSelectionModel().selectRange(i,i);
											userGrid.getView().focusRow(i);
											return;
										}
									}else if(Ext.getCmp('queryUserflag').getValue()==2){//字段含义
										var c = res.data.yhdlm;
										if(c.indexOf(b.toUpperCase())!=-1){
											userGrid.fireEvent("rowclick",userGrid,i);
											userGrid.getSelectionModel().selectRange(i,i);
											userGrid.getView().focusRow(i);
											return;
										}
									}
								}
							}										
						}
					})
				]
				
				
    			
    		},userGrid
    		]
		},userfwInfoGrid
		]
	});	
	var p3_1 = new Ext.Panel({
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
				border:false,
				frame: false,
				items:[new Ext.Button({
            		text:'显示/隐藏',
					minWidth:80,
					handler:function(){
						if(hideFlag){
							xPosition1 = userdtqxInfoGrid.x;
							Ext.getCmp('weastPartP3').hide();
							userdtqxInfoGrid.setPosition(0,userdtqxInfoGrid.y);
						}else{
							Ext.getCmp('weastPartP3').show();
							userdtqxInfoGrid.setPosition(xPosition1,userdtqxInfoGrid.y);
						}
						hideFlag = !hideFlag;
					}
            	})]
			},{
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
    		},{
    			frame:false,
				border:false,
				items:[new Ext.Button({
            		text:'增加设置',
					minWidth:80,
					handler:function(){
						adddtqx_window.show(1);
						adddtqx_window.setSelRes();
					}
            	})]
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
            		text:'删除设置',
					minWidth:80,
					handler:function(){
						if(window.confirm('是否确定要删除【'+selecteddtqxSelRes.data.yhxm+'】的等同用户【'+selecteddtqxSelRes.data.dtyhxm+'】?')){
							Gnt.submit(
							{
								dtqxid:selecteddtqxSelRes.data.dtqxid,
								yhid:selecteddtqxSelRes.data.yhid}, 
								"gl/xtkzgl/cxsjfwwh/delYhdtqxInfo", 
								"正在删除等同用户信息，请稍后...", 
								function(jsonData,params){
									showInfo("等同用户信息删除成功！");
									userdtqxInfoGrid.store.reload(); 
								}
							);
						}
					}
            	})]
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
					text:'关闭窗体',
					minWidth:80,
					handler:function(){
						Ext.getCmp('card').getLayout().setActiveItem(0);
					}
				})]
    		}]
    });

	var p3 = new Ext.Panel({
		layout:'border',
		items:[p3_1,{
			region: 'west',
			layout:'border',
			id:'weastPartP3',
			border:false,
            frame:false,
            width:250,
    	    margins: '0 0 0 0',
    		cmargins: '0 0 0 0',  
    		title:'',
    		items:[{
    			region:'north',
            	height:60,
            	border:false,
            	frame: false,
            	layout:'table',
				align:'center',
				bodyStyle:'padding: 10px 10px 10px 10px;',
				layoutConfig: {
					columns: 5
				},
				defaults: {
					colspan: 1,
					border:false,
					frame: false
				},
				items: [
					{
						//bodyStyle: 'margin:0px 0px 0px 10px ',
						id:'queryUser1flag',
						xtype : "combo",
						store : sfbz1,
						fieldLabel:"",
						value:'1',
						width:70,
						triggerAction:"all"
					},{
						width:20
					},
					{
						id:'queryUser1Value',
						width:70,
            			xtype : 'textfield',
            			name:'queryUser1Value'
					},{
						width:20
					},
					new Ext.Button({
						text:'查询',
						width:40,
						//minWidth:80,
						handler:function(){
							if(!Ext.getCmp('queryUser1Value').getValue()){
								alert("请输入需要查找的相关信息标记!");
								return;
							}
							var store = userGrid.store;
							if(store.data.length > 0){
								for(var i= 0;i<store.data.length;i++){
									var res = store.getAt(i);
									var b = Ext.getCmp('queryUser1Value').getValue();
									if(Ext.getCmp('queryUser1flag').getValue()==1){//字段名称
										var a = res.data.yhxm;
										a = a.toUpperCase();
										if(a.indexOf(b.toUpperCase())!=-1){
											userGrid.fireEvent("rowclick",userGrid,i);
											userGrid.getSelectionModel().selectRange(i,i);
											userGrid.getView().focusRow(i);
											return;
										}
									}else if(Ext.getCmp('queryUser1flag').getValue()==2){//字段含义
										var c = res.data.yhdlm;
										if(c.indexOf(b.toUpperCase())!=-1){
											userGrid.fireEvent("rowclick",userGrid,i);
											userGrid.getSelectionModel().selectRange(i,i);
											userGrid.getView().focusRow(i);
											return;
										}
									}
								}
							}										
						}
					})
				]
				
				
    			
    		},user1Grid
    		]
		},userdtqxInfoGrid
		]
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
        	items:[p1,p2,p3]
        }
    });	
	var csstore = ywzlGrid.store;
	csstore.baseParams = {};
	csstore.load({params:{cslb:'9025',start:0, limit:9999}});	
	csstore.on("load",function(store) {  
		if(csstore.data.length > 0){
			curIndex = 0;
			ywzlGrid.fireEvent("rowclick",ywzlGrid,curIndex);
			ywzlGrid.getSelectionModel().selectRange(curIndex,curIndex);
			
		}
	},ywzlGrid); 	
});