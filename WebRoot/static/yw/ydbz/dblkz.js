var selectYw = 1;
var queryFlag = null;
var rynbid = null;

var selectedSelRes=null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	var csm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var colModel = new Ext.grid.ColumnModel([
		{
			header: "申办点代码",
	        dataIndex: "sbddm",
	        sortable: true,
			width: 120
		},{
			header: "申办点名称",
	        dataIndex: "sbdmc",
	        sortable: true,
			width: 120
		},{
	        header: "基本打包量",
	        dataIndex: "jbdbl",
	        sortable: true,
			width: 120
	    },{
	        header: "已打包量",
	        dataIndex: "ydbl",
	        sortable: true,
			width: 120
	    },{
	        header: "是否打包完成",
	        dataIndex: "sfdbwc",
	        sortable: true,
			width: 120
	    },{
	        header: "初始化时间",
	        dataIndex: "cshsj",
	        sortable: true,
			width: 120
	    }
	]);
 	var myuserStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'yw/ydbz/dblkz/getDblkzxxInfo',//yw/hjyw/hzywcl/queryHzywcl
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        id:'sbddm',
        totalProperty:'totalCount',
        fields: [
        	"sbddm",
			"sbdmc",
			"jbdbl",
			"ydbl",
			"sfdbwc",
			"cshsj"
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
	var dblkzGrid = new Ext.grid.GridPanel({
        store:myuserStore,
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
	var addDblkz_window = new Gnt.ux.SelectDblkzAdd({
		//选择立户信息回调
		callback: function(optype, dblkzAdd){
			//提交数据
			Gnt.submit(
					dblkzAdd, 
					"yw/ydbz/dblkz/addDblkzxx", 
					"正在增加打包量控制信息，请稍后...", 
					function(jsonData,params){
						showInfo("打包量控制信息增加成功！");
						dblkzGrid.store.reload(); 
					}
			);
		}
	});
	var modifyDblkz_window = new Gnt.ux.SelectDblkzModify({
		//选择立户信息回调
		callback: function(optype, dblkzModify){
			//提交数据
			Gnt.submit(
					dblkzModify, 
					"yw/ydbz/dblkz/modifyDblkzxx", 
					"正在修改打包量控制信息，请稍后...", 
					function(jsonData,params){
						showInfo("打包量控制信息修改成功！");
						dblkzGrid.store.reload(); 
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
    		},{
				border:false,
				frame: false,
    		    items:[
    		           new Ext.Button({
    		        	   text:'增加',
							minWidth:80,
							handler:function(){
								addDblkz_window.show();
								addDblkz_window.setSelRes();
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
								if(selectedSelRes){
									modifyDblkz_window.show(selectedSelRes);
									modifyDblkz_window.setSelRes(selectedSelRes);
								}else{
									alert("当前没有可修改的数据。");
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
    		        	   text:'删除',
							minWidth:80,
							handler:function(){
								if(window.confirm('是否确定删除当前记录?')){
									Gnt.submit(
									{
										sbddm:selectedSelRes.data.sbddm}, 
										"yw/ydbz/dblkz/delDblkzxx", 
										"正在删除打包量控制信息，请稍后...", 
										function(jsonData,params){
											showInfo("打包量控制删除成功！");
											dblkzGrid.store.reload(); 
										}
									);
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
		items:[dblkzGrid,p1_3]
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
	var store = dblkzGrid.store;
	store.baseParams = {
			start:0,
			limit:20
	};
	store.load({params:{start:0, limit:9999}});	
	store.on("load",function(store) {  
		if(store.data.length > 0){
			curIndex = 0;
			dblkzGrid.fireEvent("rowclick",dblkzGrid,curIndex);
			dblkzGrid.getSelectionModel().selectRange(curIndex,curIndex);
		}
	},dblkzGrid); 
});