var selRes = null;
var selectedSelRes = null;
var selecteRowIndex = null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10019,50005",function(){}))
		return;
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	var form50005 = new Gnt.ux.SjpzForm({
		pzlb: '50005',
		url: 'yw/queryRyxx',
		title:'',
		cols:2,
		formType:'query'
	});
	

	var sfxxbInfoGrid = new Gnt.ux.SjpzGrid({
		pkname: 'sfxxbid',
		pzlb: '50005',
		url:'gl/sjkfjgl/querySjkfjgl.json',
//		region:'center',
		title: '',
		height:document.body.clientHeight-120,
		dcFlag:true,
		checkboxFlag : true,
		showPaging:true,
		loadCallback: function(res, store, bind_grid){
			Ext.Msg.hide();
			
			if(res.length>0){
				//Ext.getCmp('card').getLayout().setActiveItem(1);
			}else{
				showInfo("未查到有关信息！");
			}
		},
		listeners:{
			rowclick:function(g, rowIndex, e){
				selectedSelRes = g.store.getAt(rowIndex);
				selecteRowIndex = rowIndex;
				var sfxxbid = selectedSelRes.data.sfxxbid;
				if(sfxxbid){
					Ext.getCmp('sfqdImg').body.update("<IMG SRC='yw/common/img/sfRender/" + sfxxbid + "' width='100%' height='100%' />");
					Ext.getCmp('jfqdImg').body.update("<IMG SRC='yw/common/img/jfRender/" + sfxxbid + "' width='100%' height='100%' />");
					
				}else{
					Ext.getCmp('sfqdImg').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
					Ext.getCmp('jfqdImg').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
				}
			},
			rowdblclick:function(g, rowIndex, e){
				selectedSelRes = g.store.getAt(rowIndex);
				selecteRowIndex = rowIndex;
				var sfxxbid = selectedSelRes.data.sfxxbid;
				var pkvalue = selectedSelRes.data[jssdForm.bindStore.pkname];
				var re = jssdForm.bindStore.getById(pkvalue);
				if(re){
					jssdForm.getForm().loadRecord(re);
					if(sfxxbid){
						Ext.getCmp('sfqdInfoImg').body.update("<IMG SRC='yw/common/img/sfRender/" + sfxxbid + "' width='100%' height='100%' />");
						Ext.getCmp('jfqdInfoImg').body.update("<IMG SRC='yw/common/img/jfRender/" + sfxxbid + "' width='100%' height='100%' />");
					}else{
						Ext.getCmp('sfqdInfoImg').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
						Ext.getCmp('jfqdInfoImg').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
					}
				}
				Gnt.toFormReadyonly(jssdForm);
    			Ext.getCmp('card').getLayout().setActiveItem(1);
    			
    			p2.doLayout();
    			
			}
		}
	});	

	var p1 = new Ext.Panel({
		layout:'border',
		items:[
             {
				    title: '',
				    collapsible: false,
				    region:'east',
//				    height:100,
				    width:"20%",
				    border:true,
				    frame:false,
				    width:"20%",
				    margins: '0 0 0 0',
				    bodyStyle:'padding:10px 10px 10px 10px',
				    layout:'table',
				    layoutConfig: {
			    		columns: 1
			    	 },
				    labelWidth:40,
				    items:[{
				    	height:10,
				    	border:false,
				    	frame:false
				    },{
				    	id:'sfqdImg',
	    		 		title: '',
	    		 		height:'100%',
	    		 		bodyStyle:'padding:10px 10px 10px 10px',
	    				width:180,
	    				height:180,
	    				rowspan: 1,
	    				colspan:1,
	    				listeners: {
	    	                render: function(c) {
	    	                c.body.on('click', function() { 
	    	                	if(selectedSelRes){
	    	                		var sfqdZpWindow = new Gnt.ux.SelectSfpz({
	    	                			returnTitleText:'收费凭证'
	    	                		});	
	    	                		sfqdZpWindow.setData(selectedSelRes.data.sfxxbid,1);
	    	                		sfqdZpWindow.show();
	    	                	}
	    	                	
	    	                    });
	    	                c.body.on('contextmenu',function(e){
	    	                    e.preventDefault();//阻止浏览器默认右键菜单
	    	                    customMenu.showAt(e.getXY());//展示自定义菜单
	    	                    });
	    	                },
	    	                scope: this
	    	            }
					},{
				    	height:10,
				    	border:false,
				    	frame:false
				    },new Ext.Button({
						text:'收费凭证上传',
						minWidth:100,
						handler:function(){
							if(selectedSelRes){
								if(sfxxbInfoGrid.getSelectionModel().getCount()==1){
									btnUpload_click('收费凭证上传',1,selectedSelRes.data.sfxxbid,selectedSelRes.data.sfxxbid,function callback() {
										sfxxbInfoGrid.store.reload();
										sfxxbInfoGrid.fireEvent("rowclick",sfxxbInfoGrid,selecteRowIndex);
										sfxxbInfoGrid.getSelectionModel().selectRange(selecteRowIndex,selecteRowIndex);
									});
								}else{
									alert("收费清单只支持给单个收费信息表附件的上传！");
									return;
								}
							}else{
								alert("请选择一条有效数据！");
								return;
							}
						}
				    }),{
				    	height:10,
				    	border:false,
				    	frame:false
				    },{
				    	id:'jfqdImg',
	    		 		title: '',
	    		 		height:'100%',
	    		 		bodyStyle:'padding:10px 10px 10px 10px',
	    				width:180,
	    				height:180,
	    				rowspan: 1,
	    				colspan:1,
	    				listeners: {
	    	                render: function(c) {
	    	                c.body.on('click', function() { 
	    	                	if(selectedSelRes){
	    	                		var sfqdZpWindow = new Gnt.ux.SelectSfpz({
	    	                			returnTitleText:'缴费凭证'
	    	                		});	
	    	                		sfqdZpWindow.setData(selectedSelRes.data.sfxxbid,2);
	    	                		sfqdZpWindow.show();
	    	                	}
	    	                	
	    	                    });
	    	                c.body.on('contextmenu',function(e){
	    	                    e.preventDefault();//阻止浏览器默认右键菜单
	    	                    customMenu.showAt(e.getXY());//展示自定义菜单
	    	                    });
	    	                },
	    	                scope: this
	    	            }
					},{
				    	height:10,
				    	border:false,
				    	frame:false
				    },new Ext.Button({
						text:'缴费凭证上传',
						minWidth:100,
						handler:function(){
							if(sfxxbInfoGrid.getSelectionModel().getCount()>0){
								var records=sfxxbInfoGrid.getSelectionModel().getSelections();
								var sfxxbidTemp = '';
								for(var i=0;i<records.length;i++){
									sfxxbidTemp =sfxxbidTemp+ records[i].data.sfxxbid+',';
						        };
								btnUpload_click('缴费凭证上传',2,selectedSelRes.data.sfxxbid,sfxxbidTemp,function callback() {
									sfxxbInfoGrid.store.reload();
									sfxxbInfoGrid.fireEvent("rowclick",sfxxbInfoGrid,selecteRowIndex);
									sfxxbInfoGrid.getSelectionModel().selectRange(selecteRowIndex,selecteRowIndex);
								});

							}else{
								alert("请选择一条有效数据！");
								return;
							}
						}
				    })]
				}
             ,{
				border:false,
				frame: false,
				region:'north',
				layout:'border',
				height: 120,
				items:[form50005],
				buttons:[
					new Ext.Button({
						text:'查询',
						minWidth:80,
						handler:function(){
		    				var data = form50005.getForm().getValues();
							Ext.Msg.wait("正在执行查询，请稍后...");
							var ary = sfxxbInfoGrid.store.data.items;
							var header=[];
							var shuxing = [];
							var zdyValueKey = Gnt.zdyValueKey(ary);
		    				var store = sfxxbInfoGrid.store;
		    				store.removeAll();
		    				store.baseParams = data;
		    				sfxxbInfoGrid.type = 'querysjkfjglxxcx';
							var config=sfxxbInfoGrid.colModel.config;
							Ext.each(config,function(r){
								if(r.header.indexOf('x-grid')==-1){
									header.push(r.header);
									shuxing.push(r.dataIndex);
								}
							});
//							store.baseParams.dwdm = user.dwCode;
							store.baseParams.header = encodeURI(header);
							store.baseParams.shuxing = encodeURI(shuxing);
							store.baseParams.zdyValueKey = Ext.encode(zdyValueKey);
							store.baseParams.daochuFlag = 'querysjkfjglxxcx';
		    				store.load({params:{start:0, limit:20}});
		    			
						}
					}),
					new Ext.Button({
						text:'关闭',
						minWidth:80,
						handler:function(){
							window.parent.parent.closeWorkSpace();
						}
					})
					]
			},{
				region:'center',
			    border:false,
			    frame:false,
//			    split: true,
				autoHeight:true,	
//			    collapsible: true,
//				    height:'100%',
				    items:[sfxxbInfoGrid]}
           ]
	});
	

	var jssdForm = new Gnt.ux.SjpzForm({
		closable: false,
		/*region:'north',*/
		height:500,
		pzlb: '50005',
		labelWidth : 120,
		cols:2,
//		bindStore:sfxxbGrid.store,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:sfxxbInfoGrid,
		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
		changeDictCust:function(cmb,res){
			
			return;
		},
		title: ''
	});
	
	var p2 = new Ext.Panel({
		layout:'border',
		items:[jssdForm,{
		    title: '',
		    collapsible: false,
		    region:'east',
		    height:100,
		    border:false,
		    frame:false,
		    width:"20%",
		    margins: '0 0 0 0',
		    bodyStyle:'padding:10px 10px 10px 10px',
		    layout:'table',
		    layoutConfig: {
	    		columns: 1
	    	 },
		    labelWidth:40,
		    items:[{
		    	height:10,
		    	border:false,
		    	frame:false
		    },{
		    	id:'sfqdInfoImg',
		 		title: '',
		 		height:'100%',
		 		bodyStyle:'padding:10px 10px 10px 10px',
				width:180,
				height:180,
				rowspan: 1,
				colspan:1,
				listeners: {
	                render: function(c) {
	                c.body.on('click', function() { 
	                	if(selectedSelRes){
	                		var sfqdZpWindow = new Gnt.ux.SelectSfpz({
	                			returnTitleText:'收费清单凭证'
	                		});	
	                		sfqdZpWindow.setData(selectedSelRes.data.sfxxbid,1);
	                		sfqdZpWindow.show();
	                	}
	                	
	                    });
	                c.body.on('contextmenu',function(e){
	                    e.preventDefault();//阻止浏览器默认右键菜单
	                    customMenu.showAt(e.getXY());//展示自定义菜单
	                    });
	                },
	                scope: this
	            }
			},{
		    	height:10,
		    	border:false,
		    	frame:false
		    },{
		    	id:'jfqdInfoImg',
		 		title: '',
		 		height:'100%',
		 		bodyStyle:'padding:10px 10px 10px 10px',
				width:180,
				height:180,
				rowspan: 1,
				colspan:1,
				listeners: {
	                render: function(c) {
	                c.body.on('click', function() { 
	                	if(selectedSelRes){
	                		var sfqdZpWindow = new Gnt.ux.SelectSfpz({
	                			returnTitleText:'缴费清单凭证'
	                		});	
	                		sfqdZpWindow.setData(selectedSelRes.data.sfxxbid,1);
	                		sfqdZpWindow.show();
	                	}
	                	
	                    });
	                c.body.on('contextmenu',function(e){
	                    e.preventDefault();//阻止浏览器默认右键菜单
	                    customMenu.showAt(e.getXY());//展示自定义菜单
	                    });
	                },
	                scope: this
	            }
			}]
		}],
		buttons:[
			new Ext.Button({
				text:'关闭',
				minWidth:80,
				handler:function(){
					window.parent.parent.closeWorkSpace();
				}
			}),
			new Ext.Button({
				text:'返回',
				minWidth:80,
				handler:function(){
					Ext.getCmp('card').getLayout().setActiveItem(0);
				}
			})
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
        	items:[p1,p2]
        }
    });

});