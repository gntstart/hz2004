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

	var csm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var colModel = new Ext.grid.ColumnModel([
		{
	        header: "IP允许id",
	        dataIndex: "ipyxid",
	        sortable: true,
	        hidden:true,
			width: 120
	    },{
	        header: "公共允许IP地址",
	        dataIndex: "ipdz",	
	        sortable: true,
			width:120
	    },{
	        header: "创建人姓名",
	        dataIndex: "cjrxm",
	        sortable: true,
			width: 120
	    },{
	        header: "创建时间",
	        dataIndex: "cjsj",	
	        sortable: true,
			width:120
	    }
	]);
 	var myuserStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'gl/xtkzgl/ggyxipset/getGgyxipsetInfo',//yw/hjyw/hzywcl/queryHzywcl
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        id:'id',
        totalProperty:'totalCount',
        fields: [
        	"ipyxid",
        	"ipdz",
        	"cjrxm",
        	"cjsj"
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
	var ggyxipGrid = new Ext.grid.GridPanel({
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
        border:true,
        height:450,
        anchor:'50% 80%',
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
//	var modifyggyxipWindow = new Gnt.ux.SelectGgyxipModify({
//		//选择立户信息回调
//		callback: function(optype, spmbModify){
//			//提交数据
//			Gnt.submit(
//					spmbModify, 
//					"gl/xtkzgl/ggyxipset/modifyGgyxip", 
//					"正在修改公共允许IP，请稍后...", 
//					function(jsonData,params){
//						showInfo("公共允许IP修改成功！");
//						ggyxipGrid.store.reload(); 
//					}
//			);
//		}
//	});
	var addggyxipWindow = new Gnt.ux.SelectGgyxipAdd({
		//选择立户信息回调
		callback: function(optype, ipdzArray){
			//提交数据
			Gnt.submit(
					{ipdzArray:ipdzArray}, 
					"gl/xtkzgl/ggyxipset/addGgyxip", 
					"正在增加公共允许IP，请稍后...", 
					function(jsonData,params){
						showInfo("公共允许IP增加成功！");
						ggyxipGrid.store.reload(); 
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
    		}, {
				border:false,
				frame: false,
    		    items:[
    		           new Ext.Button({
    		        	   text:'增加',
							minWidth:80,
							id:'addBtn',
							handler:function(){
								addggyxipWindow.show(1);//和用户管理页面上IP增加区分开
								addggyxipWindow.setSelRes();
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
								if(window.confirm('是否确定要删除【'+selectedSelRes.data.ipdz+'】?')){
									Gnt.submit(
									{
										ipyxid:selectedSelRes.data.ipyxid}, 
										"gl/xtkzgl/ggyxipset/delGgyxip", 
										"正在删除公共允许IP，请稍后...", 
										function(jsonData,params){
											showInfo("公共允许IP删除成功！");
											ggyxipGrid.store.reload(); 
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
    	id:'',
    	title :"",
    	items:[ggyxipGrid,p1_3]
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
	
	var store = ggyxipGrid.store;
	store.baseParams = {
			yhid:user.yhid
	};
	store.load({params:{start:0, limit:20}});	
	store.on("load",function(store) {  
		if(store.data.length > 0){
			curIndex = 0;
			ggyxipGrid.fireEvent("rowclick",ggyxipGrid,curIndex);
			ggyxipGrid.getSelectionModel().selectRange(curIndex,curIndex);
		}
	},ggyxipGrid); 
});