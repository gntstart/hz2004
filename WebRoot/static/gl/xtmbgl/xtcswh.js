var selectYw = 1;
var queryFlag = null;
var rynbid = null;
var hideFlag = true;
var xPosition=0;
var selectedSelRes=null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10019,20021",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	var sfbz = [[1,'参数类别'],[2,'参数名称']]
	
	var csm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var colModel = new Ext.grid.ColumnModel([
		{
			header: "参数子类别",
	        dataIndex: "dm",
	        sortable: true,
			width: 120
		},{
			header: "参数名称",
	        dataIndex: "mc",
	        sortable: true,
			width: 120
		},{
	        header: "名称中文拼音",
	        dataIndex: "zwpy",
	        sortable: true,
			width: 120
	    },{
	        header: "",
	        dataIndex: "",
	        sortable: true,
			width: 120
	    },{
	        header: "",
	        dataIndex: "",	
	        sortable: true,
			width:120
	    },{
	        header: "",
	        dataIndex: "",	
	        sortable: true,
			width: 120,
			renderer:function(value){
				return value;
			}
	    }
	]);
 	var myuserStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'gl/xtmbgl/xtcswh/getXtcsInfoByCslb',//yw/hjyw/hzywcl/queryHzywcl
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        totalProperty:'totalCount',
        fields: [
        	"dm",
			"mc",
			"zwpy",
			"",
			"",
			""
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
	var sjzdGrid = new Ext.grid.GridPanel({
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
		bodyStyle:'width:50%',
        border:true,
        frame:false,
        anchor:'50% 50%',
	    margins: '0 0 0 0',
		cmargins: '0 0 0 0',        
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
	
	
	var xtcsm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var xtcscolModel = new Ext.grid.ColumnModel([
		{
			header: "参数类别",
	        dataIndex: "dm",
	        sortable: true,
			width: 100
		},{
			header: "参数名称",
	        dataIndex: "mc",
	        sortable: true,
			width: 150
		}
	   
	]);
 	var xtcsStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'gl/xtmbgl/xtcswh/getXtcswhInfo',
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
	var xtcsGrid = new Ext.grid.GridPanel({
        store: xtcsStore,
        region: 'center',
        width:300,
		stripeRows: true,
        cm: xtcscolModel,
        sm:xtcsm,
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
        		selectedSelRes = g.store.getAt(rowIndex);
        		xPosition = sjzdGrid.x;
        		var store = sjzdGrid.store;
        		store.baseParams = {
        			cslb:selectedSelRes.data.dm
        		};
        		store.load({params:{start:0, limit:20}});	
        	},
			rowdblclick:function(g, rowIndex, e){
				selectedSelRes = g.store.getAt(rowIndex);
        		var store = sjzdGrid.store;
        		store.baseParams = {
        			cslb:selectedSelRes.data.dm
        		};
        		store.load({params:{start:0, limit:20}});	
			}
		}/*,
        bbar: new Ext.PagingToolbar({
			pageSize: 9999,
			store: xtcsStore,
			displayInfo: true
	})*/
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
							sjzdGrid.setPosition(0,sjzdGrid.y);
						}else{
							Ext.getCmp('weastPartP1').show();
							sjzdGrid.setPosition(xPosition,sjzdGrid.y);
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
            		text:'增加参数',
					minWidth:80,
					disabled:true,
					handler:function(){
						
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
            		text:'修改参数',
					minWidth:80,
					disabled:true,
					handler:function(){
						
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
            		text:'删除参数',
					minWidth:80,
					disabled:true,
					handler:function(){
						
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
				//	bodyStyle: 'padding:10px 0px 0px 0px ',
				id:'queryFlag',
	    		xtype : "combo",
	    		store : sfbz,
	    		width:80,
	    		value:'1',
				fieldLabel:"",
				triggerAction:"all",
				maxHeight : 80
			},{
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
    		},{
				id:'queryValue',
    			xtype : 'textfield',
    			border:false,
    			frame:false,
    			width:80,
    			fieldLabel:"",
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
    		    items:[new Ext.Button({
    		    	id:'fzId',
        			text:'查询',
        			minWidth:80,
        			handler:function(){
						if(!Ext.getCmp('queryValue').getValue()){
							alert("请输入需要查找的相关信息标记!");
							return;
						}
						var store = xtcsGrid.store;
						if(store.data.length > 0){
							for(var i= 0;i<store.data.length;i++){
								var res = store.getAt(i);
								var b = Ext.getCmp('queryValue').getValue();
								if(Ext.getCmp('queryFlag').getValue()==1){//字段名称
									var a = res.data.dm;
									if(a.indexOf(b.toUpperCase())!=-1){
										xtcsGrid.fireEvent("rowclick",xtcsGrid,i);
										xtcsGrid.getSelectionModel().selectRange(i,i);
										xtcsGrid.getView().focusRow(i);
										return;
									}
								}else if(Ext.getCmp('queryFlag').getValue()==2){//字段含义
									var c = res.data.mc;
									if(c.indexOf(b.toUpperCase())!=-1){
										xtcsGrid.fireEvent("rowclick",xtcsGrid,i);
										xtcsGrid.getSelectionModel().selectRange(i,i);
										xtcsGrid.getView().focusRow(i);
										return;
									}
								}
							}
						}										
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
			//border:false,
            frame:false,
            width:'25%',
            anchor:'100% 100%',
    	    margins: '0 0 0 0',
    		cmargins: '0 0 0 0',  
    		title:'',
    		items:[p2_1,{
    			width: 20,
				border:false,
				frame: false
    		},xtcsGrid
    		]
		},sjzdGrid
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
        	items:[p1]
        }
    });	
	var csstore = xtcsGrid.store;
	csstore.baseParams = {
			cslb:'9999',
			log_code: ''
	};
	csstore.load({params:{start:0, limit:9999}});	
	csstore.on("load",function(store) {  
		if(csstore.data.length > 0){
			curIndex = 0;
			xtcsGrid.fireEvent("rowclick",xtcsGrid,curIndex);
			xtcsGrid.getSelectionModel().selectRange(curIndex,curIndex);
			
		}
	},xtcsGrid); 	
});