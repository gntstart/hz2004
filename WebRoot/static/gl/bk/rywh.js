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

	function save(method, param) {
		Ext.Msg.wait("操作进行中...", "等待");

		Ext.Ajax.request({
					url : 'gl/bk/' + method + ".json",
					method : 'POST',
					params :param,
					success : function(result, request) {
						Ext.Msg.hide();
						var jsonData = Ext.util.JSON.decode(result.responseText);
						if (jsonData.success) {
							store.load({
								params : {
									start : 0,
									limit : 20
								}
							});
							win.hide();
						} else if (jsonData.message) {
							showErr(jsonData.message);
						}
					},
					failure : function(result, request) {
						Ext.Msg.hide();
						Ext.MessageBox.alert('发生意外错误', result.responseText);
					}
				});
	}
	
	 store = new Ext.data.JsonStore({
				url : 'gl/bk/queryRybk',
				root : 'list',
				id : 'ywid',
				totalProperty : 'totalCount',
				fields : ["ywid",	"xm","gmsfhm","bklx","bklxmc",	"bktx",	"bksj",	"bkmjxm"],
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
					columnWidth : .33,
					layout : 'form',
					bodyStyle : 'padding:0 0 0 0',
					items : [{
								xtype : 'textfield',
								anchor : '100%',
								name : 'xm',
								fieldLabel : '姓名'
							}]
				}, {
					columnWidth : .33,
					layout : 'form',
					bodyStyle : 'padding:0 0 0 0',
					items : [{
								xtype : 'textfield',
								anchor : '100%',
								name : 'gmsfhm',
								fieldLabel : '身份证号码'
							}]
				},{
					columnWidth : .33,
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
							columnWidth : .33,
							layout : 'form',
							bodyStyle : 'padding:0 0 0 0',
							items : [{
										xtype : 'datefield',
										anchor : '100%',
										format : 'Ymd',
										name : 'bksj_start',
										fieldLabel : '布控时间起'
									}]
						}, {
							columnWidth : .33,
							layout : 'form',
							bodyStyle : 'padding:0 0 0 0',
							items : [{
										xtype : 'datefield',
										format : 'Ymd',
										anchor : '100%',
										name : 'bksj_end',
										fieldLabel : '布控时间止'
									}]
						}
	   		]
	});

	var bkmbUploadWindow = new Gnt.ux.SelectBkMbUpload({
		//选择立户信息回调
		callback: function(optype, jttdInfo){
			Ext.getCmp("query").handler();
		}
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
					id:'query',
					text : '查询',
					minWidth : 75,
					handler : function() {
						var p = fs.getForm().getValues();
						store.baseParams = p;
						store.load({
									params : {
										start : 0,
										limit : 20
									}
								})
					}
				}),new Ext.Button({
					text : '新增布控',
					minWidth : 75,
					handler : function() {
						win.show();
						mxfs.getForm().reset();
					}
				}),new Ext.Button({
					text : '修改布控',
					minWidth : 75,
					handler : function() {
						if(selRecord){
							var data = selRecord.data;
							win.show();
							mxfs.getForm().reset();
							mxfs.getForm().setValues(data);							
						}else{
							alert("必须先选中药修改的某条数据!");
							return;
						}
					}
				}),new Ext.Button({
					text : '删除布控',
					minWidth : 75,
					handler : function() {
						var res = csm.getSelections();
						if (res.length == 0) {
							Ext.Msg.alert("提示", "必须选择需要删除的布控记录！");
							return;
						}

						Ext.Msg.show({
									title : '提示',
									msg : '确认要删除选择的布控记录吗？',
									buttons : Ext.Msg.YESNO,
									fn : function(btn, text) {
										if (btn == "yes") {
											var p = "";
											for (var i = 0; i < res.length; i++) {
												if (p == "")
													p = res[i].data.ywid;
												else
													p += "," + res[i].data.ywid;
											}

											save('delRybk', {
													ids : p
											});
										}
									},
									icon : Ext.MessageBox.QUESTION
								});
					}
				}),new Ext.Button({
					text : '下载模板',
					minWidth : 75,
					handler : function() {
						var urlLink = "yw/common/downBkMbZip?type=queryFxjsktj";
						Ext.Msg.wait("正在下载模板，请稍后...");
						 var elemIF = document.createElement("iframe");
	        		        elemIF.src = urlLink;//"yw/common/downZp?dcParams="+Ext.encode('111');
	        		        elemIF.style.display = "none";
	        		        elemIF.setAttribute('async', false);
	        		        document.body.appendChild(elemIF);
	        		        var timer = setInterval(function () {
	        		            var iframeDoc = elemIF.contentDocument || elemIF.contentWindow.document;
	        		            // Check if loading is complete
	        		            if (iframeDoc.readyState == 'complete' || iframeDoc.readyState == 'interactive') {
	        		                // do something
	        		            	Ext.Msg.hide();
	        		                clearInterval(timer);
	        		                return;
	        		            }
	        		        }, 500);
					}
				}),new Ext.Button({
					text : '批量上传',
					minWidth : 75,
					handler : function() {
						bkmbUploadWindow.show();
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
				header : "布控提醒内容",
				dataIndex : "bktx",
				sortable : true,
				width : 200
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
				listeners : {
					rowclick : function(g, rowIndex, e) {
						selRecord = g.store.getAt(rowIndex);
					},
					rowdblclick : function(g, rowIndex, e) {
						selRecord = g.store.getAt(rowIndex);
						var data = selRecord.data;
						url = xmdz + "gl/bk/rygjcx"+"?jumpToRygjcx=1&gmsfhm=" + data.gmsfhm;
						if (parent && parent.openWorkSpace) {
							parent.openWorkSpace(url, "人员轨迹查询", "rygjcx");
						} else {
							window.location.href = url;
						}
//						win.show();
//						mxfs.getForm().reset();
//						mxfs.getForm().setValues(data);
						
						return;

					}
				},
				title : '',
				bbar : new Ext.PagingToolbar({
							pageSize : 15,
							store : store,
							displayInfo : true
						})
			});

	 mxfs = new Ext.form.FormPanel({
	    	region: 'center',
	    	buttonAlign:'right',
	    	labelAlign:'right',
	    	frame:true,
	    	height:'100%',
	    	border:true,
	        layout:'form',
	        labelWidth:95,
	       	items:[{
	       			xtype:'hidden',
	       			name:'ywid'
	       		},{
	        		layout:'column',
	    			frame:false,
	    			border:false,
	        		defaults:{
	        			border:false,
	        			frame:false
	        		},
	            	items:[{
		                columnWidth:1,
	    	           	layout: 'form',
	    	           	bodyStyle:'padding:0 0 0 0',
	        	       	items: [{
							xtype:'textfield',
							anchor:'100%',
							name:'xm',
							allowBlank: false,
							fieldLabel:'姓名<span style="color:red">*</span>'
						}]
					},{
		                columnWidth:1,
	    	           	layout: 'form',
	    	           	bodyStyle:'padding:0 0 0 0',
	        	       	items: [{
							xtype:'textfield',
							anchor:'100%',
							name:'gmsfhm',
							allowBlank: false,
							vtype: 'Sfzh',
							fieldLabel:'身份证号码<span style="color:red">*</span>'
						}]
					},{
		                columnWidth:1,
	    	           	layout: 'form',
	    	           	bodyStyle:'padding:0 0 0 0',
	        	       	items: [{
							hiddenName : 'bklx',
							name: 'bklx',
							anchor : '100%',
							xtype : 'dict_combox',
							dict: 'VisionType=CS_BKLX',
							allowBlank: false,
							fieldLabel : '布控类型<span style="color:red">*</span>'
						}]
					},{
		                columnWidth:1,
	    	           	layout: 'form',
	    	           	bodyStyle:'padding:0 0 0 0',
	        	       	items: [{
							xtype:'textarea',
							anchor:'100%',
							height: 80,
							name:'bktx',
							allowBlank: false,
							fieldLabel:'布控提醒<span style="color:red">*</span>'
						}]
					}
				]
			}],
			buttons:[
				new Ext.Button({
					text:'布控',
					minWidth:75,
					handler:function(uel){
						if(!mxfs.getForm().isValid()){
							showErr("数据校验没有通过！");
							return;
						}
						
						var data = mxfs.getForm().getValues();
						
						Ext.Msg.show({
									title : '提示',
									msg : '确认对['+data.xm + " " + data.gmsfhm+']进行布控吗？',
									buttons : Ext.Msg.YESNO,
									fn : function(btn, text) {
										if (btn == "yes") {
											save('saveRybk', data);
										}
									},
									icon : Ext.MessageBox.QUESTION
								});
					}
				}),{
					text:'取消',
					handler:function(){
						win.hide();
					}
				}
			]
		});
	win = new Ext.Window({
		title:'布控信息',
		closeAction:'hide',
		modal:true,
		width:400,
		height:260,
		layout: 'border',
		items:[
		       mxfs
		],
		listeners:{
			show:function(){
			}
		}
	});
	new Ext.Viewport({
				layout : 'border',
				items : [{
							height : 120,
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
});