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
	var sfbz = [[1,'字段名称'],[2,'字段含义']]
	/**
		点击下一步后显示的内容
		暂时用户成员信息代替
	 */
	var hcyGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		pzlb: '10019',
    	region:'south',
		height:150,
    	title: '',
		url: 'yw/common/queryRyxx.json',
    	showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){}
		}
	});
	var csm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var colModel = new Ext.grid.ColumnModel([
		{
			header: "字典id",
	        dataIndex: "zdid",
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
	        header: "字段类型",
	        dataIndex: "zdlx",
	        sortable: true,
			width: 120
	    },{
	        header: "字段长度",
	        dataIndex: "zdcd",	
	        sortable: true,
			width:120
	    },{
	        header: "字段密级",
	        dataIndex: "zdmj",	
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
	    }
	]);
 	var myuserStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'gl/xtmbgl/sjzdwh/getSjzdInfo',//yw/hjyw/hzywcl/queryHzywcl
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        id:'id',
        totalProperty:'totalCount',
        fields: [
        	"zdid",
			"zdmc",
			"zdhy",
			"zdlx",
			"zdcd",
			"zdmj",
			"bdlx"
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
	var modifysjzdWindow = new Gnt.ux.SelectSjzdWhModify({
		//选择立户信息回调
		callback: function(optype, sjzdModify){
			log_code = "F9012";
			//提交数据
			Gnt.submit(
					sjzdModify, 
					"gl/xtmbgl/sjzdwh/modifySizd", 
					"正在修改数据字典，请稍后...", 
					function(jsonData,params){
						showInfo("数据字典修改成功！");
						sjzdGrid.store.reload(); 
					}
			);
		}
	});
	
	var addsjzdwh_window = new Gnt.ux.SelectSjzdWhAdd({
		//选择立户信息回调
		callback: function(optype, sjzdAdd){
			log_code = "F9011";
			//提交数据
			Gnt.submit(
					sjzdAdd, 
					"gl/xtmbgl/sjzdwh/addSizd", 
					"正在新增数据字典，请稍后...", 
					function(jsonData,params){
						showInfo("数据字典新增成功！");
						sjzdGrid.store.reload(); 
					}
			);
		}
	});
	
	var xtmbgl_window=new Gnt.ux.xtmbglwin({
		callback: function(){
		
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
    		},{
    			frame:false,
    			border:false,
    			id:'queryFlag',
	    		xtype : "combo",
	    		store : sfbz,
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
    			frame:false,
    			border:false,
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
    		},
    		{
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
//						sjzdGrid.fireEvent("rowclick",sjzdGrid,2);
//						sjzdGrid.getSelectionModel().selectRange(2,2);
						var store = sjzdGrid.store;
//						var rowIndex =0;
						if(store.data.length > 0){
							for(var i= 0;i<store.data.length;i++){
//								alert(store.getAt(0).data.zdmc);
								var res = store.getAt(i);
								var b = Ext.getCmp('queryValue').getValue();
								if(Ext.getCmp('queryFlag').getValue()==1){//字段名称
									var a = res.data.zdmc;
									if(a.indexOf(b.toUpperCase())!=-1){
										sjzdGrid.fireEvent("rowclick",sjzdGrid,i);
										sjzdGrid.getSelectionModel().selectRange(i,i);
										sjzdGrid.getView().focusRow(i);
										return;
									}
								}else if(Ext.getCmp('queryFlag').getValue()==2){//字段含义
									var c = res.data.zdhy;
									if(c.indexOf(b.toUpperCase())!=-1){
										sjzdGrid.fireEvent("rowclick",sjzdGrid,i);
										sjzdGrid.getSelectionModel().selectRange(i,i);
										sjzdGrid.getView().focusRow(i);
										return;
									}
								}
							}
						}
					}
        		})]
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
								//Ext.Msg.wait("正在加载数据，请稍后...");
								var data=sjzdGrid.store.data.items;
								xtmbgl_window.show();
								xtmbgl_window.setSelRes(data,'sjzdwh');
							}
    		           })
    		    ]
    		}/*,{
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
								addsjzdwh_window.show();
								addsjzdwh_window.setSelRes();
							}
    		           })
    		    ]
    		}*/,{
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
								modifysjzdWindow.show(selectedSelRes);
								modifysjzdWindow.setSelRes(selectedSelRes);
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
							text:'删除',
							id:'delBtn',
							minWidth:80,
							disabled: !user.isadmin?true:false,
							handler:function(){
								if(window.confirm('是否要删除当前选中的'+selectedSelRes.data.zdmc+'数据字典项?')){
									log_code = "F9013";
									Gnt.submit(
									{
										zdid:selectedSelRes.data.zdid},
										"gl/xtmbgl/sjzdwh/delSizd", 
										"正在删除数据字典，请稍后...",
										function(jsonData,params){
											showInfo("数据字典删除成功！");
											sjzdGrid.store.reload(); 
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
		items:[sjzdGrid,p1_3]
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
	var store = sjzdGrid.store;
//	store.baseParams = {
//			config_key:'querySjzdInfo',
//			start:0,
//			limit:20
//	};
	store.load({params:{start:0, limit:9999}});	
	store.on("load",function(store) {  
		if(store.data.length > 0){
			curIndex = 0;
			sjzdGrid.fireEvent("rowclick",sjzdGrid,curIndex);
			sjzdGrid.getSelectionModel().selectRange(curIndex,curIndex);
		}
	},sjzdGrid); 
});