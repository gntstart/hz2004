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
			"bdcqzh",
			"sbzz",
			"zl",
			"qzhj",
			"zsxh",
			"ywh",
			"sfyx",
			"yt",
			"mj",
			"syqx",
			"qlqtzk",
			"sjly",
			"qlr",
			"bdcdyh",
			"qllx",
			"qlxz",
			"zj",
			"zjlx",
			"zjhm",
			"ysbh",
			"gyqk",
			"createdate",
			"etldate",
			"djsj",
			"zxsj",
			"gxsj",
			"crsj"
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
	        header: "不动产权证号",
	        dataIndex: "bdcqzh",
	        sortable: true,
			width: 200
	    },{
	        header: "上报组织",
	        dataIndex: "sbzz",
	        sortable: true,
			width: 180
	    },{
	        header: "坐落",
	        dataIndex: "zl",	
	        sortable: true,
			width:500
	    },{
	        header: "权证附件",
	        dataIndex: "qzhj",	
	        sortable: true,
			width: 60,
			renderer:function(value){
				return value;
			}
	    },{
	        header: "证书序号",
	        dataIndex: "zsxh",
	        sortable: true,
			width: 70,
			renderer:function(value){
				return value;
			}
	    },{
	        header: "业务号",
	        dataIndex: "ywh",	
	        sortable: true,
			width: 100
	    },{
	        header: "是否有效",
	        dataIndex: "sfyx",	
	        sortable: true,
			width: 70
	    },{
	        header: "用途",
	        dataIndex: "yt",
	        sortable: true,
			width: 100
	    },{
	        header: "面积",
	        dataIndex: "mj",	
	        sortable: true,
			width: 100
	    },{
	        header: "使用期限",
	        dataIndex: "syqx",	
	        sortable: true,
			width: 70
	    },{
	        header: "权利其他情况",
	        dataIndex: "qlqtzk",
	        sortable: true,
			width: 100
	    },{
	        header: "数据来源",
	        dataIndex: "sjly",	
	        sortable: true,
			width: 100
	    },{
	        header: "权利人",
	        dataIndex: "qlr",	
	        sortable: true,
			width: 70
	    },{
	        header: "不动产单元号",
	        dataIndex: "bdcdyh",
	        sortable: true,
			width: 100
	    },{
	        header: "权利类型",
	        dataIndex: "qllx",	
	        sortable: true,
			width: 100
	    },{
	        header: "权利性质",
	        dataIndex: "qlxz",	
	        sortable: true,
			width: 50
	    },{
	        header: "证件",
	        dataIndex: "zj",
	        sortable: true,
			width: 70
	    },{
	        header: "证件类型",
	        dataIndex: "zjlx",	
	        sortable: true,
			width: 70
	    },{
	        header: "权利人证件号码",
	        dataIndex: "zjhm",	
	        sortable: true,
			width: 100
	    },{
	        header: "印刷编号",
	        dataIndex: "ysbh",
	        sortable: true,
			width: 50
	    },{
	        header: "共有情况",
	        dataIndex: "gyqk",	
	        sortable: true,
			width: 70
	    },{
	        header: "创建日期",
	        dataIndex: "createdate",	
	        sortable: true,
			width: 100
	    },{
	        header: "ETLDATE",
	        dataIndex: "etldate",
	        sortable: true,
			width: 100
	    },{
	        header: "登记时间",
	        dataIndex: "djsj",	
	        sortable: true,
			width: 100
	    },{
	        header: "注销时间",
	        dataIndex: "zxsj",
	        sortable: true,
			width: 100
	    },{
	        header: "更新时间",
	        dataIndex: "gxsj",	
	        sortable: true,
			width: 100
	    },{
	        header: "插入时间",
	        dataIndex: "crsj",
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
			config_key:'queryBdcxx'
	}
	store.load();
	Ext.Msg.hide();
	
});