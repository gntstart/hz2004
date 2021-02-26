var selRecord = null;
var store = null;
var selCmp = null;
var mxfs = null;
var win = null;


Ext.onReady(function() {
	Ext.QuickTips.init();

	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_BKLX'],function(){});
	
	var myMask = new Ext.LoadMask(Ext.getBody(), {
				msg : "正在执行操作，请等待..."
	});

	 store = new Ext.data.JsonStore({
				url : 'gl/bk/queryRybkgjxx',
				root : 'list',
				id : 'gjid',
				totalProperty : 'totalCount',
				fields : [
					"gjid",
					"xm",
					"gmsfhm",
					"bklx",
					"bklxmc",
					"bktx",
					"bksj",
					"bkmjxm",
					"hjywlx",
					"hjywlxmc",
					"hjywsj",
					"hjywmj",
					"hjywmjxm"],
				listeners : {
					load : function(mystore, res) {
						myMask.hide();
					},
					loadexception : function(mystore, options, response, error) {
						myMask.hide();
						if (error) {
							var json = Ext.decode(response.responseText);
							Ext.Msg.alert("提示", json.messages[0]);
						} else {
							Ext.Msg.alert("提示", response.responseText);
						}
					}
				}
			});
	 
		var  baseset = new Ext.form.FieldSet({
	   		title:'',
	   		columnWidth:.1,
	   		height:290,
	   		layout:'column',
	   		border:false,
	   		frame:false,
	   		anchor:'100%',
	   		labelWidth:97,
	   		checkboxToggle:false,
	    	collapsed: false,
	   		items:[
	   			{
					columnWidth : .25,
					layout : 'form',
					bodyStyle : 'padding:0 0 0 0',
					items : [{
								xtype : 'textfield',
								anchor : '100%',
								name : 'xm',
								fieldLabel : '姓名'
							}]
				}, {
					columnWidth : .25,
					layout : 'form',
					bodyStyle : 'padding:0 0 0 0',
					items : [{
								xtype : 'textfield',
								anchor : '100%',
								name : 'gmsfhm',
								id:'gmsfhm',
								fieldLabel : '身份证号码'
							}]
				},{
					columnWidth : .25,
					layout : 'form',
					bodyStyle : 'padding:0 0 0 0',
					items : [{
								hiddenName : 'bklx',
								name: 'bklx',
								anchor : '100%',
								xtype : 'dict_combox',
								dict: 'VisionType=CS_BKLX',
								fieldLabel : '布控类型'
							}]
				}, {
							columnWidth : .25,
							layout : 'form',
							bodyStyle : 'padding:0 0 0 0',
							items : [{
										xtype : 'datefield',
										anchor : '100%',
										format : 'Ymd',
										name : 'bjsj_start',
										fieldLabel : '报警时间起'
									}]
						}, {
							columnWidth : .25,
							layout : 'form',
							bodyStyle : 'padding:0 0 0 0',
							items : [{
										xtype : 'datefield',
										format : 'Ymd',
										anchor : '100%',
										name : 'bjsj_end',
										fieldLabel : '报警时间止'
									}]
						}
	   		]
	});
	
	var fs = new Ext.form.FormPanel({
		id : 'form1',
		title : '',
		anchor : '100% 100%',
		buttonAlign : 'right',
		labelAlign : 'right',
		frame : true,
		border : false,
		layout : 'form',
		labelWidth : 95,
		items : [baseset],
		buttons : [new Ext.Button({
					text : '查询',
					id:'queryId',
					minWidth : 75,
					handler : function() {
						var p = fs.getForm().getValues();
//						if(getQueryParam("jumpToRygjcx")&& getQueryParam("jumpToRygjcx")!=""){
//				    		p.gmsfhm=getQueryParam("gmsfhm");
//				    	}
						store.baseParams = p;
						store.load({
									params : {
										start : 0,
										limit : 20
									}
								})
					}
				})]
	});


	var csm = new Ext.grid.CheckboxSelectionModel({
				singleSelect : false
			});
	var colModel = new Ext.grid.ColumnModel([csm, {
				header : "姓名",
				dataIndex : "xm",
				sortable : true,
				width : 80
			}, {
				header : "身份证号码",
				dataIndex : "gmsfhm",
				sortable : true,
				width : 100
			},{
				header : "布控类型",
				dataIndex : "bklxmc",
				sortable : true,
				width : 100
			},{
				header : "布控时间",
				dataIndex : "bksj",
				sortable : true,
				width : 100
			},{
				header : "布控民警",
				dataIndex : "bkmjxm",
				sortable : true,
				width : 80
			},{
				header : "告警内容",
				dataIndex : "bktx",
				sortable : true,
				width : 200
			},{
				header : "告警业务名称",
				dataIndex : "hjywlxmc",
				sortable : true,
				width : 80
			},{
				header : "告警民警",
				dataIndex : "hjywmj",
				sortable : true,
				width : 80
			},{
				header : "告警时间",
				dataIndex : "hjywsj",
				sortable : true,
				width : 80
			}]);

	var grid = new Ext.grid.GridPanel({
				store : store,
				region : 'center',
				view : new Ext.grid.GridView({
							forceFit : true,
							autoFill : true,
							enableRowBody : true
						}),
				stripeRows : true,
				id : 'grid1',
				cm : colModel,
				sm : csm,
				loadMask : {
					msg : '加载中...'
				},
				bodyStyle : 'width:100%',
				border : true,
				anchor : '100% 100%',
				margins : '0 0 0 0',
				cmargins : '0 0 0 0',
				frame : false,
				iconCls : 'icon-grid',
				title : '',
				bbar : new Ext.PagingToolbar({
							pageSize : 15,
							store : store,
							displayInfo : true
						})
			});

	
	new Ext.Viewport({
				layout : 'border',
				items : [{
							height : 130,
							region : 'north',
							border : false,
							closable : true,
							margins : '5 5 5 5',
							layout : 'fit',
							items : [fs]
						}, {
							region : 'center',
							html : '',
							width : 200,
							border : false,
							closable : true,
							layout : 'fit',
							margins : '0 5 0 5',
							items : [grid]
						}]
			});
	
    	if(getQueryParam("jumpToRygjcx")&& getQueryParam("jumpToRygjcx")!=""){
    		Ext.getCmp('gmsfhm').setValue(getQueryParam("gmsfhm"));
    		Ext.getCmp("queryId").handler();
    	}  
})