Ext.onReady(function(){
	
	Ext.QuickTips.init();
	
	//本业务需要加载的字典
	var myuserStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'gl/hyym/getZmxxxx',//yw/hjyw/hzywcl/queryHzywcl
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        id:'id',
        totalProperty:'totalCount',
        fields: [
			"mxm",
			"mzjhm",
			"mzjyzh",
			"gxm",
			"gzjhm",
			"gzjyzh",
			"djrq",
			"lx"
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
	
	var csm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var colModel = new Ext.grid.ColumnModel([
		{
	        header: "夫姓名",
	        dataIndex: "mxm",
	        sortable: true,
			width: 70
	    },{
	        header: "夫证件号码",
	        dataIndex: "mzjhm",
	        sortable: true,
			width: 120
	    },{
	        header: "夫证件编号",
	        dataIndex: "mzjyzh",	
	        sortable: true,
			width:250
	    },{
	        header: "妻姓名",
	        dataIndex: "gxm",	
	        sortable: true,
			width: 70,
			renderer:function(value){
				return value;
			}
	    },{
	        header: "妻证件号码",
	        dataIndex: "gzjhm",
	        sortable: true,
			width: 120,
			renderer:function(value){
				return value;
			}
	    },{
	        header: "妻证件编码",
	        dataIndex: "gzjyzh",	
	        sortable: true,
			width: 250
	    },{
	        header: "登记日期",
	        dataIndex: "djrq",	
	        sortable: true,
			width: 70
	    },{
	        header: "证件类型",
	        dataIndex: "lx",
	        sortable: true,
			width: 100
	    }
	]);
	
	var bdcxxGrid = new Ext.grid.GridPanel({
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
		bodyStyle:'width:100%',
        border:true,
        anchor:'100% 100%',
	    margins: '0 0 0 0',
		cmargins: '0 0 0 0',        
        frame:false,
		iconCls:'icon-grid',
        title:'',
        listeners:{
        	rowclick:function(g, rowIndex, e){
        	},
			rowdblclick:function(g, rowIndex, e){
				
			}
		},
        bbar: new Ext.PagingToolbar({
				pageSize: 20,
				store: myuserStore,
				displayInfo: true
		})
    });	
	var p3 = new Ext.Panel({
		layout:'border',
		items:[bdcxxGrid],
		buttons:[
			{
				text:'返回',
				minWidth:60,
				handler:function(){
					parent.Ext.getCmp('card').getLayout().setActiveItem(0);
				}
			},{
				text:'关闭',
				minWidth:60,
				handler:function(){
					window.parent.parent.closeWorkSpace();
				}
			}
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
    	    //bodyStyle: 'padding:2px',
        	items:[p3]
        }
    });
	Ext.Msg.wait("正在执行查询，请稍后...");
	var store = bdcxxGrid.store;
	store.baseParams = {
			zjbh:decodeURI(getQueryParam("zjbh")),
			xm:decodeURI(getQueryParam("xm")),
			sfz:getQueryParam("sfz"),
			config_key:'queryHyxx'
	}
	store.load();
	Ext.Msg.hide();
	
});