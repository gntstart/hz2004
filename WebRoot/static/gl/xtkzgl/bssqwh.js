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
			header: "市区Id",
	        dataIndex: "sqid",
	        sortable: true,
	        hidden:true,
			width: 120
		},{
			header: "区划代码",
	        dataIndex: "qhdm",
	        sortable: true,
			width: 120
		},{
	        header: "区划名称",
	        dataIndex: "mc",
	        sortable: true,
			width: 120
	    },{
	        header: "市区标志",
	        dataIndex: "sqbz",
	        sortable: true,
	        hidden:true,
			width: 120
	    },{
	        header: "市区标志",
	        dataIndex: "sqbzmc",
	        sortable: true,
			width: 120
	    },{
	        header: "管辖标志",
	        dataIndex: "gxbz",	
	        sortable: true,
	        hidden:true,
			width:120
	    },{
	        header: "管辖标志",
	        dataIndex: "gxbzmc",	
	        sortable: true,
			width:120
	    },{
	        header: "启用标志",
	        dataIndex: "qybzmc",	
	        sortable: true,
			width: 120,
			renderer:function(value){
				return value;
			}
	    }
	]);
 	var myuserStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'gl/xtkzgl/bssqwh/getBssqwhInfo',//yw/hjyw/hzywcl/queryHzywcl
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        id:'id',
        totalProperty:'totalCount',
        fields: [
        	"sqid",
        	"qhdm",
			"mc",
			"sqbz",
			"sqbzmc",
			"gxbz",
			"gxbzmc",
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
	var bssqGrid = new Ext.grid.GridPanel({
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
	var modifybssqWindow = new Gnt.ux.SelectBssqModify({
		//选择立户信息回调
		callback: function(optype, sjzdModify){
			//提交数据
			Gnt.submit(
					sjzdModify, 
					"gl/xtkzgl/bssqwh/modifyBssqwh", 
					"正在修改本市市区，请稍后...", 
					function(jsonData,params){
						showInfo("本市市区修改成功！");
						bssqGrid.store.reload(); 
					}
			);
		}
	});
	
	var addbssqWindow = new Gnt.ux.SelectBssqAdd({
		//选择立户信息回调
		callback: function(optype, bssqObj){
			//提交数据
			Gnt.submit(
					bssqObj, 
					"gl/xtkzgl/bssqwh/addBssqwh", 
					"正在新增本市市区，请稍后...", 
					function(jsonData,params){
						showInfo("本市市区新增成功！");
						bssqGrid.store.reload(); 
					}
			);
		}
	});
	var p1_3 = new Ext.Panel({
		xtype: 'panel',
		border:false,
		frame: false,
    	region:'south',
    	height: 40,
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
				id:'queryFlag',
	    		xtype : "combo",
	    		store : sfbz,
	    		value:'1',
				fieldLabel:"",
				triggerAction:"all"
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
				style:'margin-top:-25px',
				buttons:[
					new Ext.Button({
						text:'查询',
						minWidth:80,
						handler:function(){
							if(!Ext.getCmp('queryValue').getValue()){
								alert("请输入需要查找的相关信息标记!");
								return;
							}
							var store = bssqGrid.store;
							if(store.data.length > 0){
								for(var i= 0;i<store.data.length;i++){
									var res = store.getAt(i);
									var b = Ext.getCmp('queryValue').getValue();
									if(Ext.getCmp('queryFlag').getValue()==1){//字段名称
										var a = res.data.qhdm;
										if(a.indexOf(b.toUpperCase())!=-1){
											bssqGrid.fireEvent("rowclick",bssqGrid,i);
											bssqGrid.getSelectionModel().selectRange(i,i);
											bssqGrid.getView().focusRow(i);
											return;
										}
									}else if(Ext.getCmp('queryFlag').getValue()==2){//字段含义
										var c = res.data.mc;
										if(c.indexOf(b.toUpperCase())!=-1){
											bssqGrid.fireEvent("rowclick",bssqGrid,i);
											bssqGrid.getSelectionModel().selectRange(i,i);
											bssqGrid.getView().focusRow(i);
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
            				Ext.getCmp('modifyBtn').show();
            				Ext.getCmp('delBtn').hide();
            				Ext.getCmp('addBtn').disable();
            				var store = bssqGrid.store;
            				store.baseParams = {
            						qybz:qybz
            					};
            				store.load({params:{start:0, limit:20}});
            			}else{
            				qybz = 1;
            				Ext.getCmp('addBtn').enable();
            				Ext.getCmp('modifyBtn').show();
            				Ext.getCmp('delBtn').show();
            				Ext.getCmp('resumeBtn').hide();
            				var store = bssqGrid.store;
            				store.baseParams = {
            						qybz:qybz
            					};
            				store.load({params:{start:0, limit:20}});
            			}
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
								addbssqWindow.show();
								addbssqWindow.setSelRes();
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
									modifybssqWindow.show(selectedSelRes);
									modifybssqWindow.setSelRes(selectedSelRes);
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
											sqid:selectedSelRes.data.sqid,
											qybz:1}, 
											"gl/xtkzgl/bssqwh/resumeBssqwh", 
											"正在 恢复本市市区，请稍后...", 
											function(jsonData,params){
												showInfo("本市市区恢复成功！");
												selectedSelRes= null;
												bssqGrid.store.reload(); 
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
								if(window.confirm('是否确定要删除【'+selectedSelRes.data.qhdm+'】【'+selectedSelRes.data.mc+'】?')){
									Gnt.submit(
									{
										sqid:selectedSelRes.data.sqid}, 
										"gl/xtkzgl/bssqwh/delBssqwh", 
										"正在修改本市市区，请稍后...", 
										function(jsonData,params){
											showInfo("本市市区删除成功！");
											bssqGrid.store.reload(); 
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
		items:[bssqGrid,p1_3]
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
	var store = bssqGrid.store;
	store.baseParams = {
			qybz:qybz
	};
	store.load({params:{start:0, limit:20}});	
	store.on("load",function(store) {  
		if(store.data.length > 0){
			curIndex = 0;
			bssqGrid.fireEvent("rowclick",bssqGrid,curIndex);
			bssqGrid.getSelectionModel().selectRange(curIndex,curIndex);
		}
	},bssqGrid); 
});