
var cxCount = 1;
var rynbid = null;

var selectRynbid = null;
var selectHhnbid = null;
var selectHjywid = null;
var selectRynbid1 = null;
var selectHhnbid1 = null;
var selectRyid1 = null;
var selectHhid1 = null;

var changeFlag = false;
var selectQczxId = null;
var zpWindow = null;
var selRes = null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	var hzywid = getQueryParam("hzywid");
	var pch = getQueryParam("pch");
	var ywlb = getQueryParam("ywlb");
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10019,20004",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	
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
	
	var form20004 = new Gnt.ux.SjpzForm({
		pzlb: '20004',
		url: 'yw/queryRyxx',
		title:'',
		cols:2,
		formType:'query'
	});
	var newForm20000 = new Gnt.ux.SjpzForm({
		closable: false,
		region:'center',
		/*height:500,*/
		pzlb: '20000',
		labelWidth : 120,
		cols:2,
//		bindStore:xblryGrid.store,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:newRkxxGrid,
		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
		changeDictCust:function(cmb,res){
			
			return;
		},
		title: ''
	});
	var newRkxxGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		//region:'north',
		pzlb: '20000',
		title: '',
		url: 'cx/hjjbxx/ckxx/getXxxx.json',
		showPaging:false
	});	
	var p1_1 = new Gnt.ux.ZdycxPanel({
		pzlb:form20004.pzlb,
		region:'center',
		layout:'border',
		border:false,
		frame: false,
		height:600
	});	
	var p1 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[
			hcyGrid,
			{
				border:false,
				frame: false,
				region:'center',
				items:[{
					id:"centerId",
	    	        border:false,
	    	        frame:false,
					region:'south',
//					hidden:true,
					items:[{
						id: "panelHtmlId" ,
						border:false,
						frame: false,
						html: '<div class="text" style="text-align:center;"><h2>迁出注销信息查询</h2></div>',
						height: 80,
						region:'north',
						bodyStyle:'padding:15px'
					},
					form20004
				]
				},
				{
					id:"p1_1",
	    	        border:false,
	    	        frame:false,
					region:'south',
					hidden:true,
					items:[p1_1]
				}]
			},
//			{
//				id:"centerId",
//				border:false,
//				frame: false,
//				region:'center',
//				layout:'border',
////	        	height: 300,
//				items:[{
//						id: "panelHtmlId" ,
//						border:false,
//						frame: false,
//						html: '<div class="text" style="text-align:center;"><h2>迁出注销信息查询</h2></div>',
//						height: 80,
//						region:'north',
//						bodyStyle:'padding:15px'
//					},
//					form20004
//				]
//			},
			{
				id:"southId",
	        	region:'south',
	        	height:40,
	            border:false,
	            frame:false,
	            bodyStyle:'padding:10px',
		        layout:'table',
		        layoutConfig: {
		        	columns: 16
		        },
	            items:[{
					frame:false,
					border:false,
					xtype:'panel',
					html:'',
					width:10
	    		},{
	    				xtype:'button',
	    		    	id:'zhId',
	        			text:'组合条件',
	        			minWidth:80,
	        			handler:function(){
	        				zhcx_dialog.show();
	        			}
	    		},{
					frame:false,
					border:false,
					xtype:'panel',
					html:'',
					width:10
	    		},{
	    	    	xtype: 'DkButton',
	    	    	form:form20004,
	    	    	callback: function(){
	    	    		//Ext.getCmp("queryId").handler();
	    	    	}
	    	    },/*{
					frame:false,
					border:false,
					xtype:'panel',
					html:'',
					width:10
	    		},{
	    		    	id:'c11',
		        		xtype:'checkbox',
		        		boxLabel : "显示记录总数",  
		        		name : "jlzs",  
		        		inputValue : "1" 
	    		},*/{
					frame:false,
					border:false,
					xtype:'panel',
					html:'',
					width:10
	    		},{
	    		    	id:'c12',
		        		xtype:'checkbox',
		        		boxLabel : "查询归档信息",  
		        		name : "gdxx",  
		        		inputValue : "2"
	    		},{
					frame:false,
					border:false,
					xtype:'panel',
					html:'',
					width:10
	    		},{
    		    	id:'c13',
	        		xtype:'checkbox',
	        		boxLabel : "查询一站式迁出",  
	        		name : "yzsqcxx",  
	        		inputValue : "3"
    		},{
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
    		},{
	    			xtype:'button',
	    		    	id:'preId',
	        			text:'上一步',
	        			disabled:true,
	        			minWidth:60,
	        			handler:function(){
	        				Ext.getCmp('centerId').show();
	        				Ext.getCmp('p1_1').doLayout();
	        				Ext.getCmp('p1_1').setVisible(false);
	        				Ext.getCmp('preId').setDisabled(true);
	        				Ext.getCmp('nexId').setDisabled(false);
	        			}
	    		},{
					frame:false,
					border:false,
					xtype:'panel',
					html:'',
					width:10
	    		},{
	    			xtype:'button',
	    		    	id:'nexId',
	        			text:'下一步',
	        			minWidth:60,
	        			handler:function(){
	        				p1_1.qybz = true;
	        				Ext.getCmp('centerId').hide();
	        				Ext.getCmp('p1_1').setVisible(true);
	        				Ext.getCmp('p1_1').doLayout();
	        				Ext.getCmp('preId').setDisabled(false);
	        				Ext.getCmp('nexId').setDisabled(true);
	        			}
	    		},{
					frame:false,
					border:false,
					xtype:'panel',
					html:'',
					width:10
	    		},{
	    			xtype:'button',
	    		    	id:'queryId',
	        			text:'查询',
	        			minWidth:60,
	        			handler:function(){
	        				var data = form20004.getForm().getValues();

							if(Gnt.util.isEmpty(data)){
								showInfo("至少输入一个查询条件！");
								return;
							}
							//记录日志的操作码
	        				data.log_code = "F1027";
							Ext.Msg.wait("正在执行查询，请稍后...");
							var ary = p1_1.xszdGrid.store.data.items;
							var pxary = p1_1.pxzdGrid.store.data.items;//length 长度;
							if(p1_1.qybz){
								xxGrid.reconfigure(
										xxGrid.store,new Ext.grid.ColumnModel(Gnt.ZdyColumn(ary)));
								xxGrid.pxary = pxary;
							};
							xxGrid.zdyValueKey = Gnt.zdyValueKey(ary);
							var header=[];
							var shuxing = [];
							var zdyValueKey = Gnt.zdyValueKey(ary);
	        				var store = xxGrid.store;
	        				store.removeAll();
	        				store.baseParams = data;
	        				xxGrid.type = 'qczxcx';
							var config=xxGrid.colModel.config;
							Ext.each(config,function(r){
								header.push(r.header);
								shuxing.push(r.dataIndex);
							});
							store.baseParams.header = encodeURI(header);
							store.baseParams.shuxing = encodeURI(shuxing);
							store.baseParams.zdyValueKey = Ext.encode(zdyValueKey);
							store.baseParams.daochuFlag = 'qczxcx';
	        				applyParam(store);
	        				store.load({params:{start:0, limit:20}});
	        				Ext.each(pxary, function(pxObj){
	        					store.sort(pxObj.data.fieldname, 'ASC');
	    					});
	        				store.on('load',function(s,records){
	        					
	        					if(records.length>0){
	        						Ext.getCmp('card').getLayout().setActiveItem(1);
	        						if(hzywid != null){
	        							xxGrid.fireEvent("rowdblclick",xxGrid,(records.length-1));
	        						}
	        						if(getQueryParam("jumpToQczx")){
	        							if(records.length==1){
	        								xxGrid.fireEvent("rowdblclick",xxGrid,0);
	        							}
	        							
	        						}
	        					}else{
	        						showInfo("查无满足条件的记录!");
	        					}
	        					
	        				});
	        			}
	    		},{
					frame:false,
					border:false,
					xtype:'panel',
					html:'',
					width:10
	    		},{
	    			xtype:'button',
	        			text:'关闭',
	        			minWidth:60,
	        			handler:function(){
	        				window.parent.closeWorkSpace();
	        			}
	    		},{
	        		frame:false,
					border:false,
					xtype:'panel',
					html:'',
					height:40
	        	},{
	    	    	frame:false,
					border:false,
					id:'_READ_CARD_ID',
					xtype:'panel',
					html:'',
					width:10
				}]
	        }
		]
	});
	var rkGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		region:'north',
        height:200,
		pzlb: '20000',
		title: '',
		url: 'cx/hjjbxx/ckxx/getXxxx.json',
		showPaging:false,
		listeners:{
		}
	});	
	var xxGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		pzlb: '20004',
		url:'cx/hjywxx/qczxcx/getQcxx.json',
		region:'center',
//		height:540,
		title: '',
		dcFlag:true,
		showPaging:true,
		loadCallback: function(res, store, bind_grid){
			Ext.Msg.hide();
			
			if(res.length>0){
				Ext.getCmp('card').getLayout().setActiveItem(1);
			}else{
				showInfo("未查到有关信息！");
			}
		},
		listeners:{
			rowclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				
				selectRynbid = selRes.data.rynbid;
				selectHhnbid = selRes.data.hhnbid;
				selectHjywid = selRes.data.hjywid;
				selectQczxId = selRes.data.qczxid;
				/*
				 * add by zjm  20191009 
				 * 当迁出地是港澳台或国外的时候，将对应的按钮高亮
				 */
				if(selRes.data.qwdssxq == "710000" || selRes.data.qwdssxq == "810000" || selRes.data.qwdssxq == "820000"){//掐面采集港澳台
					Ext.getCmp('gatzxhkzm1').enable();
					Ext.getCmp('gwdjzxhkzm1').disable();
					Ext.getCmp('gatzxhkzm2').enable();
					Ext.getCmp('gwdjzxhkzm2').disable();
					
				}else if((selRes.data.qwdssxq).indexOf("0")==0){//迁出国外
					Ext.getCmp('gatzxhkzm1').disable();
					Ext.getCmp('gwdjzxhkzm1').enable();
					Ext.getCmp('gatzxhkzm2').disable();
					Ext.getCmp('gwdjzxhkzm2').enable();
				}else{
					Ext.getCmp('gatzxhkzm1').disable();
					Ext.getCmp('gwdjzxhkzm1').disable();
					Ext.getCmp('gatzxhkzm2').disable();
					Ext.getCmp('gwdjzxhkzm2').disable();
				}
				
			},
			rowdblclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				selectRynbid = selRes.data.rynbid;
				selectHhnbid = selRes.data.hhnbid;
				selectHjywid = selRes.data.hjywid;
				selectQczxId = selRes.data.qczxid;
				/*
				 * add by zjm  20191009 
				 * 当迁出地是港澳台或国外的时候，将对应的按钮高亮
				 */
				if(selRes.data.qwdssxq == "710000" || selRes.data.qwdssxq == "810000" || selRes.data.qwdssxq == "820000"){//掐面采集港澳台
					Ext.getCmp('gatzxhkzm1').enable();
					Ext.getCmp('gwdjzxhkzm1').disable();
					Ext.getCmp('gatzxhkzm2').enable();
					Ext.getCmp('gwdjzxhkzm2').disable();
				}else if((selRes.data.qwdssxq).indexOf("0")==0){//迁出国外
					Ext.getCmp('gatzxhkzm1').disable();
					Ext.getCmp('gwdjzxhkzm1').enable();
					Ext.getCmp('gatzxhkzm2').disable();
					Ext.getCmp('gwdjzxhkzm2').enable();
				}else{
					Ext.getCmp('gatzxhkzm1').disable();
					Ext.getCmp('gwdjzxhkzm1').disable();
					Ext.getCmp('gatzxhkzm2').disable();
					Ext.getCmp('gwdjzxhkzm2').disable();
				}
				Ext.Msg.wait("正在执行查询，请稍后...");
				var store = rkGrid.store;
				store.baseParams = {
						ryid:selRes.data.ryid,
						config_key:'queryPoHJXX_CZRKJBXXB4',
						start:0,
						limit:20
				};
				store.load();
				store.on("load",function(store) {
					if(store.data.length > 0){
						selectRyid1 = store.getAt(0).data.ryid;
						selectRynbid1 = store.getAt(0).data.rynbid;
						selectHhnbid1 = store.getAt(0).data.hhnbid;
						selectHhid1 = store.getAt(0).data.hhid;
						var pkvalue = selRes.data[xxForm.bindStore.pkname];
						var re = xxForm.bindStore.getById(pkvalue);
						if(re){
							xxForm.getForm().loadRecord(re);
						}
					}
					Ext.Msg.hide();
				},rkGrid); 
    			Ext.getCmp('card').getLayout().setActiveItem(2);
    			xxForm.doLayout();
    			Gnt.toFormReadyonly(xxForm);
    			
    			
			}
		}
	});
	
	var zhcx_dialog = new Gnt.ux.ZhcxDialog({
		pzlb : form20004.pzlb,
		grid:xxGrid,
		callback: function(where){
			var ary = p1_1.xszdGrid.store.data.items;
			var pxary = p1_1.pxzdGrid.store.data.items;//length 长度;
			if(p1_1.qybz){
				xxGrid.reconfigure(
						xxGrid.store,new Ext.grid.ColumnModel(Gnt.ZdyColumn(ary)));
				xxGrid.pxary = pxary;
			};
			xxGrid.zdyValueKey = Gnt.zdyValueKey(ary);
			var grid = this.grid;
			var store = grid.store;
			var length = 0;
			var data = {"where": where};
			store.removeAll();
			data.pzlb = grid.store.pzlb;
			data.config_key = this.config_key;
			
			//add by zjm 20201223 复杂查询传入列表头 属性 自定义字段，用于导出功能使用
			var header=[];
			var shuxing = [];
			var zdyValueKey = Gnt.zdyValueKey(ary);
			grid.type = 'qczxcx';
			Ext.each(ary,function(r){
				header.push(r.data.displayname);
				shuxing.push(r.data.fieldname);
			});
			data.header = encodeURI(header);
			data.shuxing = encodeURI(shuxing);
			data.zdyValueKey = Ext.encode(zdyValueKey);
			data.daochuFlag = 'qczxcx';
			
			store.baseParams = data;
			if(this.applyParam){
				this.applyParam();
			}
			Ext.Msg.wait("正在执行查询，请稍后...");
			store.load({params:{start:0, limit:grid.store.pageSize}});
			store.on("load",function(store) {  
				length = store.data.length
				if(length > 0){
					curIndex = 0;
					grid.fireEvent("rowclick",grid,curIndex);
					grid.getSelectionModel().selectRange(curIndex,curIndex);
					Ext.getCmp('card').getLayout().setActiveItem(1);
				}else{
					showInfo("没有找到符合条件的数据！");
				}
				Ext.Msg.hide();
			},grid); 			        						
		}
	});
	
	var p2 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[xxGrid],
			buttons:[
				new Ext.Button({
					text:'港澳台定居注销户口证明',
					id:'gatzxhkzm1',
					disabled:true,
					minWidth:80,
					handler:function(){
						if(xxGrid.getStore().getCount() > 0){
							if(xxGrid.getSelectionModel().getCount() ==1){
								var array=[];
								var o={};
								o.rynbid=selRes.data.rynbid;
								o.qczxid=selRes.data.qczxid;
								o.directTable="gatdjzxzm";
								o.hzywid = hzywid;
								array.push(o);
								printfunction(0,array);
								if(hzywid&&ywlb=='20'){
        							Gnt.ux.Dict.getKzcs('10036', function(pz, user) {
        								if(pz.kzz==1){
        									pjq('ES',user.ip,hzywid);
        								}
        							});
        						}
							}else{
								alert("请选择需要打印的人!");
							}
						}else{
							alert("请先执行查询!");
						}
						
					}
				}),new Ext.Button({
					text:'国外定居注销户口证明',
					id:'gwdjzxhkzm1',
					disabled:true,
					minWidth:80,
					handler:function(){
						if(xxGrid.getStore().getCount() > 0){
							if(xxGrid.getSelectionModel().getCount() ==1){
								var array=[];
								var o={};
								o.rynbid=selRes.data.rynbid;
								o.qczxid=selRes.data.qczxid;
								o.directTable="gwdjzxzm";
								o.hzywid = hzywid;
								array.push(o);
								printfunction(0,array);
								if(hzywid&&ywlb=='21'){
        							Gnt.ux.Dict.getKzcs('10036', function(pz, user) {
        								if(pz.kzz==1){
        									pjq('ES',user.ip,hzywid);
        								}
        							});
        						}
							}else{
								alert("请选择需要打印的人!");
							}
						}else{
							alert("请先执行查询!");
						}
						
					}
				}),new Ext.Button({
					text:'迁出注销证明',
					id:'qczxzm',
					minWidth:80,
					handler:function(){
						if(xxGrid.getStore().getCount() > 0){
							if(xxGrid.getSelectionModel().getCount() ==1){
								var array=[];
								var o={};
								o.rynbid=selRes.data.rynbid;
								o.qczxid=selRes.data.qczxid;
								o.directTable="hkzxzm";
								array.push(o);
								printfunction(0,array);
								if(hzywid&&ywlb=='24'){
        							Gnt.ux.Dict.getKzcs('10036', function(pz, user) {
        								if(pz.kzz==1){
        									pjq('ES',user.ip,hzywid);
        								}
        							});
        						}
							}else{
								alert("请选择需要打印的人!");
							}
						}else{
							alert("请先执行查询!");
						}
						
					}
				}),new Ext.Button({
					text:'戶追溯',
					minWidth:80,
					handler:function(){
						if(xxGrid.getStore().getCount() > 0){
							if(xxGrid.getSelectionModel().getCount() ==1){
								var store = hxxGrid.store;
		        				store.removeAll();
		        				store.baseParams = {
		        						hhnbid:xxGrid.getSelectionModel().getSelections()[0].data.hhnbid,
		        						start:0,
		        						limit:20
		        				};
		        				store.load();
		        				store.on("load",function(store) {
		        					if(store.data.length > 0){
		        						var pkvalue = selRes.data[NewForm20020.bindStore.pkname];
		        						var re = NewForm20020.bindStore.getById(pkvalue);
		        						if(re){
		        							NewForm20020.getForm().loadRecord(re);
		        							Ext.getCmp('card').getLayout().setActiveItem(4);
		    		        				Gnt.toFormReadyonly(NewForm20020);
		        						}
		        						p5.doLayout();
		        					}
		        				},hxxGrid); 
				 				
							}else{
								alert("请选择需要查询的户!");
							}
						}else{
							alert("请先执行查询!");
						}
						
					}
				}),
				new Ext.Button({
					text:'人追溯',
					minWidth:80,
					handler:function(){
    					if(xxGrid.getStore().getCount() > 0){
    						if(xxGrid.getSelectionModel().getCount() ==1){
    							rynbid = xxGrid.getSelectionModel().getSelections()[0].data.rynbid;
    							selectRyid = xxGrid.getSelectionModel().getSelections()[0].data.ryid;
    							Gnt.toFormReadyonly(newForm20000);
    							var store4 = newRkxxGrid.store;
    							store4.removeAll();
    							store4.baseParams = {
    									rynbid:rynbid,
    									config_key:'queryPoHJXX_CZRKJBXXB7',
    									start:0,
    									limit:20
    								};
    							store4.load();
    							store4.on("load",function(store) {
    								if(store.data.length > 0){
    									var re = store.getAt(0);
    									/**
    										往表单设值
    									 */
    									if(re){
    										newForm20000.getForm().loadRecord(re);
    									}
    									var zpid = re.data.zpid;
    									if(zpid &&　zpid != 0){
    										Ext.getCmp('p3Img').body.update("<IMG SRC='yw/common/img/render/" + zpid + "' width='100%' height='100%' />");
    									}else{
    										Ext.getCmp('p3Img').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
    									}
    									zpWindow = new Gnt.ux.SelectZpAll({
    										ryid:selRes.data.ryid
    									});
	            						Ext.getCmp('card').getLayout().setActiveItem(3);
	            						newForm20000.doLayout();
    								}else{
    									newForm20000.getForm().reset();
    									Ext.getCmp('card').getLayout().setActiveItem(3);
	            						newForm20000.doLayout();
    								}
    							},newRkxxGrid); 
    						}else{
    							alert("请选择需要查询的人员!");
    						}
    					}else{
    						alert("请先执行查询!");
    					}
					}
				}),
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
	

	var xxForm = new Gnt.ux.SjpzForm({
		closable: false,
		region:'center',
		/*height:500,*/
		pzlb: '20004',
		labelWidth : 120,
		cols:2,
//		bindStore:xxGrid.store,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:xxGrid,
		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
		changeDictCust:function(cmb,res){
			
			return;
		},
		title: '',
		fieldChange:function(field){
			
			if(field.name=='qclb' || field.name=='qcrq' || field.name=='qwdssxq' || field.name=='qwdxz' || 
					field.name=='bdfw' || field.name=='zqzbh' || field.name=='qyzbh'){
				changeFlag = true;
				
				Ext.getCmp('bcId').enable();
				
			}
        }
	});
	
	var p3 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[
			{
				region:'north',
				border:false,
				frame: false,
	        	height: 40,
	        	bodyStyle: 'padding:10px 0px 0px 0px ',
		        layout:'table',
		        layoutConfig: {
		        	columns: 10
		        },
		    	items: [
					{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
					},{
						border:false,
						frame: false,
		    		    items:[new Ext.Button({
		    		    	id:'ryId',
		        			text:'人员基本信息',
		        			minWidth:80,
//				        	disabled:hzywid != null?true:false,
		        			handler:function(){
		        				if(hzywid != null){
		        					
				        		}else{
				        			czr={
										ryid:selectRyid1,
										rynbid:selectRynbid1,
										hhnbid:selectHhnbid1,
										hhid:selectHhid1
									}
									Gnt.toRyxx(czr);
				        		}
		        				
		        			}
		        		})]
		    		},{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
					},{
						border:false,
						frame: false,
		    		    items:[new Ext.Button({
		    		    	id:'hId',
		        			text:'户信息',
		        			minWidth:60,
//				        	disabled:hzywid != null?true:false,
		        			handler:function(){
		        				var url = basePath + "cx/hjjbxx/hxxcx?jumpToHxxcx="+"1"+"&hhnbid=" + selectHhnbid;
		    					if(parent && parent.openWorkSpace){
		    						parent.openWorkSpace(url,  "户信息查询", "hxxcxFromQczx" + id);
		    					}else{
		    						window.location.href = url;
		    					}
		        			}
		        		})]
		    		},{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
					},{
						border:false,
						frame: false,
		    		    items:[new Ext.Button({
		    		    	id:'hcybdId',
		        			text:'户成员变动信息',
		        			minWidth:60,
//				        	disabled:hzywid != null?true:false,
		        			handler:function(){
		        				var url = basePath + "cx/hjywxx/hcybdcx?jumpToHcybdcx="+"1"+"&hhnbid=" + selectHhnbid;
		    					if(parent && parent.openWorkSpace){
		    						parent.openWorkSpace(url,  "户成员变动情况信息查询", "hcybdqkxxcxFromQczx" + id);
		    					}else{
		    						window.location.href = url;
		    					}
		        			}
		        		})]
		    		}
		    		 
		    	]
				
			},xxForm,{
				id:"southId",
	        	region:'south',
	        	height:40,
	            border:false,
	            frame:false,
		        layout:'table',
		        layoutConfig: {
		        	columns: 22
		        },
		        bodyStyle: 'padding:10px 0px 0px 0px ',
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
		    		    items:[new Ext.Button({
		    		    	id:'xgId',
		        			text:'修改迁往地详址',
		        			minWidth:80,
				        	disabled:hzywid != null?true:false,
		        			handler:function(){
		        				//SetReadOnly(xxForm, 'qclb', false);
		        				//SetReadOnly(xxForm, 'qcrq', false);
		        				//SetReadOnly(xxForm, 'qwdssxq', false);
		        				SetReadOnly(xxForm, 'qwdxz', false);
		        				//SetReadOnly(xxForm, 'bdfw', false);
		        				//SetReadOnly(xxForm, 'zqzbh', false);
		        				//SetReadOnly(xxForm, 'qyzbh', false);
		        				Ext.getCmp("bcId").enable();
		        			}
		        		})]
		    		},{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		            },{
						border:false,
						frame: false,
		    		    items:[new Ext.Button({
		    		    	id:'bcId',
		        			text:'保存',
		        			minWidth:80,
				        	disabled:true,
		        			handler:function(){
		        				
		        				updateQczx();
		        				
		        			}
		        		})]
		    		},{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		            },{
						border:false,
						frame: false,
		    		    items:[new Ext.Button({
		        			text:'查阅档案',
		        			minWidth:60,
				        	disabled:hzywid != null?true:false,
		        			handler:function(){
	   							Gnt.ux.Dict.getKzcs('10002',function(pz, user){
	   								var url = pz.bz;
	   								if(url.indexOf("?")<0)
	   									url += "?";
	   								else
	   									url += "&";
	   								
	   								url += "id=" +  selRes.data.hjywid;
	   								url += "&ywcode=106&ryid=" +  selRes.data.ryid;
	   								url += "&sfz=" +  selRes.data.gmsfhm;
	   								url += "&sbrsfzh=" +  selRes.data.sbrgmsfhm;
	   								url += "&dlmj=" + user.yhdlm;
	   								url += "&yhsfzh=" + user.gmsfhm;
	   								
	   								window.open(url);
	   							});
	   						}
		        		})]
		    		},{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		            },{
						border:false,
						frame: false,
		    		    items:[{
			    	    	xtype: 'CjdaButton',
			    	    	form: xxForm,
			    	    	disabled:true,
			    	    	ywcode:'106'
			    	    }]
		    		},{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		            },{
						border:false,
						frame: false,
		    		    items:[new Ext.Button({
		    		    	id:'gatzxhkzm2',
		        			text:'港澳台定居注销户口证明',
		        			minWidth:60,
		        			handler:function(){
		        				var array=[];
								var o={};
								o.rynbid=selRes.data.rynbid;
								o.qczxid=selRes.data.qczxid;
								o.directTable="gatdjzxzm";
								o.hzywid = hzywid;
								array.push(o);
								printfunction(0,array);
								if(hzywid&&ywlb=='20'){
        							Gnt.ux.Dict.getKzcs('10036', function(pz, user) {
        								if(pz.kzz==1){
        									pjq('ES',user.ip,hzywid);
        								}
        							});
        						}
		        			}
		        		})]
		    		},{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		            },{
						border:false,
						frame: false,
		    		    items:[new Ext.Button({
		    		    	id:'gwdjzxhkzm2',
		        			text:'国外定居注销户口证明',
		        			minWidth:60,
		        			handler:function(){
		        				var array=[];
								var o={};
								o.rynbid=selRes.data.rynbid;
								o.qczxid=selRes.data.qczxid;
								o.directTable="gwdjzxzm";
								o.hzywid = hzywid;
								array.push(o);
								printfunction(0,array);
								if(hzywid&&ywlb=='21'){
        							Gnt.ux.Dict.getKzcs('10036', function(pz, user) {
        								if(pz.kzz==1){
        									pjq('ES',user.ip,hzywid);
        								}
        							});
        						}
		        			}
		        		})]
		    		},{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		            },{
						border:false,
						frame: false,
		    		    items:[new Ext.Button({
		        			text:'迁出注销证明',
		        			minWidth:60,
		        			handler:function(){
		        				var array=[];
								var o={};
								o.rynbid=selRes.data.rynbid;
								o.qczxid=selRes.data.qczxid;
								o.directTable="hkzxzm";
								if(hzywid){
									o.hzywid = hzywid;
									o.ywlb = ywlb;
								}
								array.push(o);
								printfunction(0,array);
								if(hzywid&&ywlb=='24'){
        							Gnt.ux.Dict.getKzcs('10036', function(pz, user) {
        								if(pz.kzz==1){
        									pjq('ES',user.ip,hzywid);
        								}
        							});
        						}
		        			}
		        		})]
		    		},{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		            },{
						border:false,
						frame: false,
		    		    items:[new Ext.Button({
		    		    	id:'qyzdyId',
		        			text:'迁移证打印',
		        			minWidth:60,
		        			handler:function(){
		        				qyzPrint(selectHjywid,true);//增加个参数，跳转补扫迁移证 add by zjm 20210202 
		        				if(hzywid&&ywlb=='12'){
        							Gnt.ux.Dict.getKzcs('10036', function(pz, user) {
        								if(pz.kzz==1){
        									pjq('ES',user.ip,hzywid);
        								}
        							});
        						}
		        			}
		        		})]
		    		},{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		            },{
						border:false,
						frame: false,
		    		    items:[new Ext.Button({
		        			text:'迁移证存根',
		        			minWidth:60,
		        			handler:function(){
		        				if(selRes){
		    						var arrayTemp=[];
		    	               	 	var o={};
		    						o.directTable="qyzcg";
		    						o.hjywid = selRes.data.hjywid;
		    						o.qyzbh= selRes.data.qyzbh;
		    						o.gmsfhm = selRes.data.gmsfhm;
		    						arrayTemp.push(o);
		    						printfunction(0,arrayTemp);
		    					}else{
		    						alert("请至少选择一条数据！");
		    					}
		        			}
		        		})]
		    		},{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		            },{
						border:false,
						frame: false,
		    		    items:[new Ext.Button({
		        			text:'关闭',
		        			minWidth:60,
		        			handler:function(){
		        				if(hzywid != null){
				        			window.close();
				        		}else{
				        			window.parent.closeWorkSpace();
				        		}
		        			}
		        		})]
		    		},{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		            },{
						border:false,
						frame: false,
		    		    items:[new Ext.Button({
		        			text:'返回',
		        			minWidth:60,
				        	disabled:hzywid != null?true:false,
		        			handler:function(){
		        				
		        				/**
		        					如果修改了表单,返回时需要提示
		        				 */
		        				if(changeFlag){
		        					if(window.confirm("信息已经被修改,尚未保存,是否确定保存?")){
		        						updateQczx();
		        					}else{
		        						Ext.getCmp('card').getLayout().setActiveItem(1);
		        						
		        						/**
		        							已经做了修改,但是不保存时需要还原
		        						 */
		        						
		        						xxGrid.store.rejectChanges();
		        						
		        					}
		        				}else{
		        					Ext.getCmp('card').getLayout().setActiveItem(1);
		        				}
		        				
		        				Ext.getCmp('bcId').disable();
		        				
		        			}
		        		})]
		    		}
		    		 
		    	]
	        }
		]
	});
	
	function updateQczx(){
		
		/**
			保存时需要确认变动范围不为空(客户端暂时只验证了这一项)
		 */
		var bdfw = xxForm.getForm().findField("bdfw").getValue();
		
		if(!bdfw){
			showInfo("变动范围不能为空！",function(){
				
			});
		}else{
			
			var subdata = {
					qczxid : selectQczxId,
					qczxxx : xxForm.getForm().getValues()
			};
			
			subdata.qczxxx = Ext.encode(subdata.qczxxx);
			
			Gnt.submit(
					subdata,
					"yw/hjyw/qczx/updateQczx",
					"正在保存信息，请稍后...",
					function(jsonData,params){
						showInfo("修改成功！",function(){
							changeFlag = false;
							SetReadOnly(xxForm, 'qwdxz', true);							
							Ext.getCmp('bcId').disable();
							
						});
					}
			);
			
		}
		
	}
	var p4 = new Ext.Panel({
		layout:'border',
		items:[newForm20000,{
			id:"p3WestId",
        	region:'west',
        	width:180,
            border:false,
            frame:false,
            items: [{
            	id:"p3WestCenterId",
            	region:'center',
            	height:300,
                border:false,
                frame:false
            },{
    			region:'south',
    			layout:'table',
    			border:false,
                frame:false,
    			layoutConfig: {
    				columns: 1
    			},
    			items: [{
					id:'p3Img',
    		 		title: '',
    		 		height:'100%',
    		 		bodyStyle:'padding:10px 10px 10px 10px',
    				width:150,
    				height:180,
    				rowspan: 1,
    				colspan:1
				},{
    				html:'',
    				bodyStyle:'padding:10px 25px 0px 20px',
    				layout:'table',
    				align:'center',
    				border:false,
    				frame:false,
    				layoutConfig: {
    					columns: 1
    				},
    				items:[
    					new Ext.Button({
    						text:'所有照片',
    						id:'syzp',
    						minWidth:100,
    						handler:function(){
    							zpWindow.show();
    						}
    					}),{
    						height:3,
    						border:false,
    						frame:false
    					},new Ext.Button({
    						text:'不显示照片',
    						id:'bxszp',
    						minWidth:100,
    						handler:function(){
    							Ext.getCmp('p3Img').hide();
    							Ext.getCmp('syzp').hide();
    							Ext.getCmp('bxszp').hide();
    							Ext.getCmp('xszp').show();
    						}
    					}),{
    						height:3,
    						border:false,
    						frame:false
    					},new Ext.Button({
    						text:'显示照片',
    						id:'xszp',
    						hidden:true,
    						minWidth:100,
    						handler:function(){
    							Ext.getCmp('p3Img').show();
    							Ext.getCmp('syzp').show();
    							Ext.getCmp('xszp').hide();
    							Ext.getCmp('bxszp').show();
    						}
    					})
    				]
    			}]
    		}]
		},{
			region:'south',
			width: '100%', 
	        border:false,
	        frame:false,
	        buttons:[
		        new Ext.Button({
		        	text:'关闭',
		        	minWidth:100,
		        	handler:function(){
		        		window.parent.parent.closeWorkSpace();
		        	}
		        }),
		        new Ext.Button({
		        	text:'返回',
		        	minWidth:100,
		        	handler:function(){
		        		Ext.getCmp('card').getLayout().setActiveItem(1);
		        	}
		        })
	        ]
	    
	    }]
	});	
	//定义分页面板
	
	function applyParam(store){
		
		/**
			是否显示记录总数
		 */
		var zs = '';
//		if(Ext.getCmp('c11').getValue()){
//			zs = '1';
//		}
		
		/**
			查询归档信息
		 */
		var gd = '';
		if(Ext.getCmp('c12').getValue()){
			gd = '1';
		}
		var yzsqc = '';
		if(Ext.getCmp('c13').getValue()){
			yzsqc = '1';
		}
		Ext.apply(store.baseParams, {xszs:zs,gdxx:gd,yzsqc:yzsqc});
	}
	
	function getRynbid(rynbid){
		if(rynbid != null){
			return rynbid;
		}else{
			return getQueryParam("rynbid");
		}
	}
	
	var hxxGrid = new Gnt.ux.SjpzGrid({
		pkname: 'hhnbid',
		pzlb: '20001',
		url:'yw/common/queryHjxx.json',
		region:'center',
		height:500,
		title: '',
		showPaging:true
	});
	var NewForm20020 = new Gnt.ux.SjpzForm({
		closable: false,
		region:'center',
		/*height:500,*/
		pzlb: '20020',
		labelWidth : 120,
		cols:2,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:hxxGrid,
		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
		changeDictCust:function(cmb,res){
			
			return;
		},
		title: ''
	});
	var p5 = new Ext.Panel({
		layout:'border',
		items:[NewForm20020,{
			region:'south',
			width: '100%', 
	        border:false,
	        frame:false,
	        buttons:[
		        new Ext.Button({
		        	text:'关闭',
		        	minWidth:100,
		        	handler:function(){
		        		window.parent.parent.closeWorkSpace();
		        	}
		        }),
		        new Ext.Button({
		        	text:'返回',
		        	minWidth:100,
		        	handler:function(){
		        		Ext.getCmp('card').getLayout().setActiveItem(1);
		        	}
		        })
	        ]
	    
	    }]
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
        	items:[p1,p2,p3,p4,p5]
        }
    });
    
	//户政业务处理
	if(hzywid && hzywid!=""){
		Gnt.makeHzyw({
			hzywid: hzywid,
			pch:pch,
			cmpids: ['queryId'],
			callback:function(jo, jolist){
				//成功处理返回，合并数据
				hzywjo_list =[];
				hzywjo_list.push(jo);
				Ext.each(jolist,function(d){
					hzywjo_list.push(d);
				});
				hzywjo = jo;
				
				form20004.getForm().findField("gmsfhm").setValue(jo.bsqrsfz);
				
				Ext.getCmp("queryId").handler();
				
			}
		});
	}

	if(getQueryParam("jumpToQczx")){
		form20004.getForm().findField("ryid").setValue(getQueryParam("ryid"));
		form20004.getForm().findField("qczxid").setValue(getQueryParam("qczxid"));
		Ext.getCmp("queryId").handler();
	} 
	
	/**
		按下回车键执行查询操作
		
	var key1 = new Ext.KeyMap(document, {
		key:Ext.EventObject.ENTER,
		fn:function() {
			var data = form20004.getForm().getValues();
			
			if(Gnt.util.isEmpty(data)){
				showInfo("至少输入一个查询条件！");
				return;
			}
			
			var store = xxGrid.store;
			
			store.removeAll();
			
			store.baseParams = data;
			
			applyParam(store);
			
			store.load({params:{start:0, limit:20}});
			
			//改变颜色
    store.on('load',function(s,records){
    	var girdcount=0;
    	s.each(function(r){
    		
    		if(0 == r.get("ryzt")){
    			
    		}else if(1 == r.get("ryzt")){
    			xxGrid.getView().getRow(girdcount).style.backgroundColor="red";
    		}else{
    			xxGrid.getView().getRow(girdcount).style.backgroundColor="green";
    		}
    		
    		girdcount ++ ;
    	});
    });
    Ext.getCmp('card').getLayout().setActiveItem(1);
},
scope: this  
});
	 */
	
	
});