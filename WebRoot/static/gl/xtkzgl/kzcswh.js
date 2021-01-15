var selectYw = 1;
var queryFlag = null;
var rynbid = null;

var selectedSelRes=null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10019,20021",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	var sfbz = [[1,'控制类别'],[2,'控制名称']]

	var csm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var colModel = new Ext.grid.ColumnModel([
		{
			header: "参数Id",
	        dataIndex: "csid",
	        sortable: true,
	        hidden:true,
			width: 120
		},{
			header: "控制类别",
	        dataIndex: "kzlb",
	        sortable: true,
//	        hidden:true,
			width: 120
		},{
			header: "控制名称",
	        dataIndex: "kzmc",
	        sortable: true,
			width: 120
		},{
	        header: "控制值",
	        dataIndex: "kzz",
	        sortable: true,
			width: 120
	    },{
	        header: "默认值",
	        dataIndex: "mrz",
	        sortable: true,
			width: 120
	    },{
	        header: "备注",
	        dataIndex: "bz",	
	        sortable: true,
			width:250
	    }
	]);
 	var myuserStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'gl/xtkzgl/kzcswh/getKzcsInfo',//yw/hjyw/hzywcl/queryHzywcl
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        id:'id',
        totalProperty:'totalCount',
        fields: [
        	"csid",
        	"kzlb",
			"kzmc",
			"kzz",
			"mrz",
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
	var kzcsGrid = new Ext.grid.GridPanel({
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
				pageSize: 9999,
				store: myuserStore,
				displayInfo: true
		})
    });
	var modifykzcswh_window = new Gnt.ux.SelectKzcsWhModify({
		//选择立户信息回调
		callback: function(optype, kzcsModify){
			//提交数据
			log_code = "F9008";
			Gnt.submit(
					kzcsModify, 
					"gl/xtkzgl/kzcswh/modifyKzcs", 
					"正在修改控制参数，请稍后...", 
					function(jsonData,params){
						showInfo("控制参数修改成功！");
						kzcsGrid.store.reload(); 
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
    			name:'queryValue'
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
    		        	   text:'查询',
							minWidth:80,
							id:'addBtn',
							handler:function(){
								if(!Ext.getCmp('queryValue').getValue()){
									alert("请输入需要查找的相关信息标记!");
									return;
								}
								var store = kzcsGrid.store;
								if(store.data.length > 0){
									for(var i= 0;i<store.data.length;i++){
										var res = store.getAt(i);
										var b = Ext.getCmp('queryValue').getValue();
										if(Ext.getCmp('queryFlag').getValue()==1){//控制类别
											var a = res.data.kzlb;
											if(a.indexOf(b.toUpperCase())!=-1){
												kzcsGrid.fireEvent("rowclick",kzcsGrid,i);
												kzcsGrid.getSelectionModel().selectRange(i,i);
												kzcsGrid.getView().focusRow(i);
												return;
											}
										}else if(Ext.getCmp('queryFlag').getValue()==2){//控制名称
											var c = res.data.kzmc;
											if(c.indexOf(b.toUpperCase())!=-1){
												kzcsGrid.fireEvent("rowclick",kzcsGrid,i);
												kzcsGrid.getSelectionModel().selectRange(i,i);
												kzcsGrid.getView().focusRow(i);
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
							id:'modifyBtn',
							minWidth:80,
							handler:function(){
								modifykzcswh_window.show(selectedSelRes);
								modifykzcswh_window.setSelRes(selectedSelRes);
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
		items:[kzcsGrid,p1_3]/*,
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
	var store = kzcsGrid.store;
//	store.baseParams = {
//			config_key:'querySjzdInfo',
//			start:0,
//			limit:20
//	};
	store.load({params:{start:0, limit:9999}});
	store.on("load",function(store) {  
		if(store.data.length > 0){
			curIndex = 0;
			kzcsGrid.fireEvent("rowclick",kzcsGrid,curIndex);
			kzcsGrid.getSelectionModel().selectRange(curIndex,curIndex);
		}
	},kzcsGrid); 
});