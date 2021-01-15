var selectYw = 1;
var queryFlag = null;
var rynbid = null;

var selectedSelRes=null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	var sfbz = [[1,'字段名称'],[2,'字段含义']]
	var csm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var colModel = new Ext.grid.ColumnModel([
		{
			header: "字段控制id",
	        dataIndex: "zdkzid",
	        sortable: true,
	        hidden:true,
			width: 120
		},{
			header: "字段名称",
	        dataIndex: "zdmc",
	        sortable: true,
			width: 120
		},{
	        header: "字段含义",
	        dataIndex: "zdhy",
	        sortable: true,
			width: 120
	    },{
	        header: "审批标志",
	        dataIndex: "spbz",
	        sortable: true,
			width: 120
	    },{
	        header: "审批模板名称",
	        dataIndex: "spmbmc",
	        sortable: true,
			width: 120
	    }
	]);
 	var myuserStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'gl/xtkzgl/bgdykz/getBgdykzInfo',//yw/hjyw/hzywcl/queryHzywcl
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        id:'id',
        totalProperty:'totalCount',
        fields: [
        	"zdkzid",
			"zdmc",
			"zdhy",
			"spbz",
			"spmbmc"
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
	var bgspkzGrid = new Ext.grid.GridPanel({
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
		}/*,
        bbar: new Ext.PagingToolbar({
				pageSize: 9999,
				store: myuserStore,
				displayInfo: true
		})*/
    });
	var modifybgdykz_window = new Gnt.ux.SelectBgdykzModify({
		//选择立户信息回调
		callback: function(optype, bgdykzModify){
			//提交数据
			Gnt.submit(
					bgdykzModify, 
					"gl/xtkzgl/bgdykz/modifyBgdykzInfo", 
					"正在修改变更打印控制信息，请稍后...", 
					function(jsonData,params){
						showInfo("变更打印控制信息修改成功！");
						bgspkzGrid.store.reload(); 
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
				border:false,
				frame: false,
				id:'queryFlag',
	    		xtype : "combo",
	    		store : sfbz,
	    		value:'1',
				fieldLabel:"",
				triggerAction:"all",
				maxHeight : 80
			}, {
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
    		}, {
				id:'queryValue',
    			xtype : 'textfield',
    			border:false,
    			frame:false,
    			fieldLabel:"",
    			columnWidth: 0.1,
    			name:'queryValue'
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
    		        	   text:'查询',
							minWidth:80,
							handler:function(){
								if(!Ext.getCmp('queryValue').getValue()){
									alert("请输入需要查找的相关信息标记!");
									return;
								}
								var store = bgspkzGrid.store;
								if(store.data.length > 0){
									for(var i= 0;i<store.data.length;i++){
										var res = store.getAt(i);
										var b = Ext.getCmp('queryValue').getValue();
										if(Ext.getCmp('queryFlag').getValue()==1){//字段名称
											var a = res.data.zdmc;
											if(a.indexOf(b.toUpperCase())!=-1){
												bgspkzGrid.fireEvent("rowclick",bgspkzGrid,i);
												bgspkzGrid.getSelectionModel().selectRange(i,i);
												bgspkzGrid.getView().focusRow(i);
												return;
											}
										}else if(Ext.getCmp('queryFlag').getValue()==2){//字段含义
											var c = res.data.zdhy;
											if(c.indexOf(b.toUpperCase())!=-1){
												bgspkzGrid.fireEvent("rowclick",bgspkzGrid,i);
												bgspkzGrid.getSelectionModel().selectRange(i,i);
												bgspkzGrid.getView().focusRow(i);
												return;
											}
										}
									}
								}
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
								alert("对不起，没有任何数据可被修改！");
								//modifybgdykz_window.show(selectedSelRes);
								//modifybgdykz_window.setSelRes(selectedSelRes);
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
		items:[bgspkzGrid,p1_3]
	});
	//定义分页面板
	
	
	function getRynbid(rynbid){
		if(rynbid != null){
			return rynbid;
		}else{
			return getQueryParam("rynbid");
		}
	}
	
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
//	var store = bgspkzGrid.store;
//	store.baseParams = {
//			config_key:'querySjzdInfo',
//			start:0,
//			limit:20
//	};
//	store.load({params:{start:0, limit:9999}});	
//	store.on("load",function(store) {  
//		if(store.data.length > 0){
//			curIndex = 0;
//			bgspkzGrid.fireEvent("rowclick",bgspkzGrid,curIndex);
//			bgspkzGrid.getSelectionModel().selectRange(curIndex,curIndex);
//		}
//	},bgspkzGrid); 
});