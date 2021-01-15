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
			"etxm",
			"etxb",
			"csrq",
			"lrsj",
			"mqxm",
			"mqsfzh",
			"fqxm",
			"fqsfzh",
			"qfrq",
			"cszbh",
			"etldate",
			"createdate"
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
	        header: "儿童姓名",
	        dataIndex: "etxm",
	        sortable: true,
			width: 70
	    },{
	        header: "儿童性别",
	        dataIndex: "etxb",
	        sortable: true,
			width: 50
	    },{
	        header: "出生日期",
	        dataIndex: "csrq",	
	        sortable: true,
			width:140
	    },{
	        header: "录入时间",
	        dataIndex: "lrsj",	
	        sortable: true,
			width: 140,
			renderer:function(value){
				return value;
			}
	    },{
	        header: "母亲姓名",
	        dataIndex: "mqxm",
	        sortable: true,
			width: 70,
			renderer:function(value){
				return value;
			}
	    },{
	        header: "母亲身份证号",
	        dataIndex: "mqsfzh",	
	        sortable: true,
			width: 120
	    },{
	        header: "父亲姓名",
	        dataIndex: "fqxm",	
	        sortable: true,
			width: 70
	    },{
	        header: "父亲身份证号",
	        dataIndex: "fqsfzh",
	        sortable: true,
			width: 120
	    },{
	        header: "签发日期",
	        dataIndex: "qfrq",	
	        sortable: true,
			width: 120
	    },{
	        header: "出生证编号",
	        dataIndex: "cszbh",	
	        sortable: true,
			width: 100
	    },{
	        header: "etldate",
	        dataIndex: "etldate",
	        sortable: true,
			width: 100
	    },{
	        header: "创建时间",
	        dataIndex: "createdate",	
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
			config_key:'queryCsxx'
	}
	store.load();
	Ext.Msg.hide();
	
});