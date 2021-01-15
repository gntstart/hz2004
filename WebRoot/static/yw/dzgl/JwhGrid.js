var myStore = function(url){
	
	return new Ext.data.JsonStore({
		proxy : new Ext.data.HttpProxy({
			url : url,
			method : "POST",
			disableCaching : true
		}),
		root : 'list',
		id : 'id',
		totalProperty : 'totalCount',
		fields : [ "id", "tzqsx", "tzqsxCode", "tzqjwh", "tzqjwhCode", "tzhjwh", "tzhjwhCode", "tzhjlx", "tzhjlxCode"
			, "tzhpcs", "tzhpcsCode" , "tzhxzjd", "tzhxzjdCode", "tzhzrq", "tzhzrqCode"],
			listeners : {
				loadexception : function(mystore, options, response, error) {
					if (error) {
						var json = Ext.decode(response.responseText);
						Ext.Msg.alert("提示", json.messages[0]);
					} else {
						Ext.Msg.alert("提示", response.responseText);
					}
				}
			}
	});
}

var csm = new Ext.grid.CheckboxSelectionModel({
	singleSelect : false
});
var colModel = new Ext.grid.ColumnModel([ csm
, {
	header : "调整前省县",
	dataIndex : "tzqsx",
	sortable : true,
	width : 120
}, {
	header : "调整前省县代码",
	dataIndex : "tzqsxCode",
	sortable : true,
	hidden : true,
	width : 120
}, {
	header : "调整前居委会",
	dataIndex : "tzqjwh",
	sortable : true,
	width : 120
}, {
	header : "调整前居委会代码",
	dataIndex : "tzqjwhCode",
	sortable : true,
	hidden : true,
	width : 120
}, {
	header : "调整后居委会",
	dataIndex : "tzhjwh",
	sortable : true,
	width : 120
}, {
	header : "调整后居委会代码",
	dataIndex : "tzhjwhCode",
	sortable : true,
	hidden : true,
	width : 120
}, {
	header : "调整后街路巷",
	dataIndex : "tzhjlx",
	sortable : true,
	width : 120
}, {
	header : "调整后街路巷代码",
	dataIndex : "tzhjlxCode",
	sortable : true,
	hidden : true,
	width : 120
}, {
	header : "调整后派出所",
	dataIndex : "tzhpcs",
	sortable : true,
	width : 120
}, {
	header : "调整后派出所代码",
	dataIndex : "tzhpcsCode",
	sortable : true,
	hidden : true,
	width : 120
}, {
	header : "调整后乡镇",
	dataIndex : "tzhxzjd",
	sortable : true,
	width : 120
}, {
	header : "调整后乡镇代码",
	dataIndex : "tzhxzjdCode",
	sortable : true,
	hidden : true,
	width : 120
}, {
	header : "调整后责任区",
	dataIndex : "tzhzrq",
	sortable : true,
	width : 120
}, {
	header : "调整后责任区代码",
	dataIndex : "tzhzrqCode",
	sortable : true,
	hidden : true,
	width : 120
} ]);
		
Gnt.ux.JwhGrid = Ext.extend(Ext.grid.GridPanel, {
	title:'',
    region: 'center',
    view:new Ext.grid.GridView({
		//forceFit:true,
		//autoFill:true,
		enableRowBody:true
	}),
	stripeRows: true,
    sm:csm,
	loadMask: {msg:'正在加载数据，请稍侯……'},
	bodyStyle:'width:100%',
    border:true,
    anchor:'100% 100%',
    margins: '0 0 0 0',
	cmargins: '0 0 0 0',        
    frame:false,
	iconCls:'icon-grid',
//	height:200,
	frame:false,
    border:true,
    getFieldLabel:function(colname){
		if(this.store.fieldMap && this.store.fieldMap[colname])
			return this.store.fieldMap[colname];
		
		return colname;
    },
    getPostParams:function(){
    	return {};
    },
    loadData:function(){
    	var data = this.getPostParams();
    	
    	this.store.baseParams = data;
		this.store.load({params:{start:0, limit:20}});
    },
	initComponent: function() {
		
		this.store = new myStore(this.url);
		
		this.cm = colModel;
		
		Gnt.ux.JwhGrid.superclass.initComponent.call(this);
	}
});

Ext.reg('jwh_grid', Gnt.ux.JwhGrid);
