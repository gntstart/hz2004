
var cxCount = 1;
var rynbid = null;
var array=[];
var htmlStrStart = '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="cx/hjjbxx/ckxx/queryXxxx';
var htmlStrEnd = '"></iframe>';
//'?rynbid='+ getRynbid(rynbid) + "&goto=queryHdxx&hhnbid=" + getQueryParam("hhnbid") + '"> </iframe>';

var selectRynbid = null;
var selectHhnbid = null;
var selectHjywid = null;
var selectRynbid1 = null;
var selectHhnbid1 = null;
var selectRyid1 = null;
var selectHhid1 = null;
var zpWindow = null;
var selRes = null;
Ext.onReady(function(){
	Ext.QuickTips.init();

	var hzywid = getQueryParam("hzywid");
	var pch = getQueryParam("pch");
	var ywlb = getQueryParam("ywlb");
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10019,20005",function(){}))
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
    	region:'center',
		height:150,
    	title: '',
		url: 'yw/common/queryRyxx.json',
    	showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){}
		}
	});
	
	var form20005 = new Gnt.ux.SjpzForm({
		pzlb: '20005',
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
		pzlb:form20005.pzlb,
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
						html: '<div class="text" style="text-align:center;"><h2>死亡注销信息查询</h2></div>',
						height: 80,
						region:'north',
						bodyStyle:'padding:15px'
					},
					form20005
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
//						html: '<div class="text" style="text-align:center;"><h2>死亡注销信息查询</h2></div>',
//						height: 80,
//						region:'north',
//						bodyStyle:'padding:15px'
//					},
//					form20005
//				]
//			},
			{
				id:"southId",
	        	region:'south',
	        	height:50,
	            border:false,
	            frame:false,
				border:false,
				frame: false,
		        region:'south',
			    bodyStyle:'padding:10px',
		        layout:'table',
		        layoutConfig: {
		        	columns: 16
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
		    		    items:[new Ext.Button({
		    		    	id:'zhId',
		        			text:'组合条件',
		        			minWidth:80,
		        			handler:function(){
		        				zhcx_dialog.show();
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
			    	    	xtype: 'DkButton',
			    	    	form:form20005,
			    	    	callback: function(){
			    	    		//Ext.getCmp("queryId").handler();
			    	    	}
			    	    }]
		    		},/*{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		    		},{
						border:false,
						frame: false,
		    		    items:[{
		    		    	id:'c11',
			        		xtype:'checkbox',
			        		boxLabel : "显示记录总数",  
			        		name : "jlzs",  
			        		inputValue : "1"  
			        	}]
		    		},*/{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		    		},{
						border:false,
						frame: false,
		    		    items:[{
		    		    	id:'c12',
			        		xtype:'checkbox',
			        		boxLabel : "查询归档信息",  
			        		name : "gdxx",  
			        		inputValue : "2"  
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
		    		    	id:'queryId',
		        			text:'查询',
		        			minWidth:60,
		        			handler:function(){
		        				var data = form20005.getForm().getValues();
								if(Gnt.util.isEmpty(data)){
									showInfo("至少输入一个查询条件！");
									return;
								}
								//记录日志的操作码
								data.log_code = "F1030";
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
								xxGrid.type = 'swzxcx';
		        				var store = xxGrid.store;
		        				store.removeAll();
		        				store.baseParams = data;
		        				var config=xxGrid.colModel.config;
								Ext.each(config,function(r){
									header.push(r.header);
									shuxing.push(r.dataIndex);
								});
								store.baseParams.header = encodeURI(header);
								store.baseParams.shuxing = encodeURI(shuxing);
								store.baseParams.zdyValueKey = Ext.encode(zdyValueKey);
								store.baseParams.daochuFlag = 'swzxcx';
		        				applyParam(store);
		        				store.load({params:{start:0, limit:20}});
		        				Ext.each(pxary, function(pxObj){
		        					store.sort(pxObj.data.fieldname, 'ASC');
		    					});
		        				store.on('load',function(s,records){
		        					
		        					if(records.length>0){
		        						Ext.getCmp('card').getLayout().setActiveItem(1);
		        						if(hzywid != null){
		        							xxGrid.fireEvent("rowdblclick",xxGrid,0);
		        						}
		        						if(getQueryParam("jumpToSwzx")){
		        							xxGrid.fireEvent("rowdblclick",xxGrid,0);
		        						}
		        					}else{
		        						showInfo("查无满足条件的记录!");
		        					}
		        					
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
		    		    items:[new Ext.Button({
		        			text:'关闭',
		        			minWidth:60,
		        			handler:function(){
		        				window.parent.closeWorkSpace();
		        			}
		        		})]
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
		pzlb: '20005',
		url:'cx/hjywxx/swzxcx/getSwzxxx.json',
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
				array.splice(0,array.length); 
				var o={};
				o.swzxid= selRes.data.swzxid;
				o.rynbid= selRes.data.selectRynbid;
				o.directTable="swzxzm";
				if(hzywid){
					o.hzywid = hzywid;
					o.ywlb = ywlb;
				}
				array.push(o);
			},
			rowdblclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				
				selectRynbid = selRes.data.rynbid;
				selectHhnbid = selRes.data.hhnbid;
				selectHjywid = selRes.data.hjywid;
				array.splice(0,array.length); 
				var o={};
				o.swzxid= selRes.data.swzxid;
				o.rynbid= selRes.data.selectRynbid;
				o.directTable="swzxzm";
				if(hzywid){
					o.hzywid = hzywid;
					o.ywlb = ywlb;
				}
				array.push(o);
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
		pzlb : form20005.pzlb,
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
			grid.type = 'swzxcx';
			Ext.each(ary,function(r){
				header.push(r.data.displayname);
				shuxing.push(r.data.fieldname);
			});
			data.header = encodeURI(header);
			data.shuxing = encodeURI(shuxing);
			data.zdyValueKey = Ext.encode(zdyValueKey);
			data.daochuFlag = 'swzxcx';
			
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
					text:'死亡注销证明',
					minWidth:80,
					handler:function(){
						if(xxGrid.getStore().getCount() > 0){
							if(xxGrid.getSelectionModel().getCount() ==1){
								printfunction(0,array);
								if(hzywid){
        							Gnt.ux.Dict.getKzcs('10036', function(pz, user) {
        								if(pz.kzz==1){
        									pjq('ES',user.ip,hzywid);
        								}
        							});
        						}
							}else{
								alert("请选择需要查询的人员!");
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
		pzlb: '20005',
		labelWidth : 120,
		cols:2,
//		bindStore:xxGrid.store,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:xxGrid,
		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
		changeDictCust:function(cmb,res){
			
			return;
		},
		title: ''
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
		        			handler:function(){
		        				czr={
										ryid:selectRyid1,
										rynbid:selectRynbid1,
										hhnbid:selectHhnbid1,
										hhid:selectHhid1
								}
								Gnt.toRyxx(czr);
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
		        			handler:function(){
		        				var url = basePath + "cx/hjjbxx/hxxcx?jumpToHxxcx="+"1"+"&hhnbid=" + selectHhnbid;
		    					if(parent && parent.openWorkSpace){
		    						parent.openWorkSpace(url,  "户信息查询", "hxxcxFromSwzx" + id);
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
		        			handler:function(){
		        				var url = basePath + "cx/hjywxx/hcybdcx?jumpToHcybdcx="+"1"+"&hhnbid=" + selectHhnbid;
		    					if(parent && parent.openWorkSpace){
		    						parent.openWorkSpace(url,  "户成员变动情况信息查询", "hcybdqkxxcxFromSwzx" + id);
		    					}else{
		    						window.location.href = url;
		    					}
		        			}
		        		})]
		    		}
		    		 
		    	]
				
			},xxForm,{
				id:"p3SouthId",
	        	region:'south',
	        	height:40,
	            border:false,
	            frame:false,
		        layout:'table',
		        layoutConfig: {
		        	columns: 20
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
	   								url += "&ywcode=102&ryid=" +  selRes.data.ryid;
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
			    	    	ywcode:'102'
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
		        			text:'死亡注销证明',
		        			minWidth:60,
		        			handler:function(){
								printfunction(0,array);
								if(hzywid){
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
		        			handler:function(){
		        				Ext.getCmp('card').getLayout().setActiveItem(1);
		        			}
		        		})]
		    		}
		    		 
		    	]
	        }
		]
	});
	
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
		
		
		Ext.apply(store.baseParams, {xszs:zs,gdxx:gd});
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
				
				form20005.getForm().findField("gmsfhm").setValue(jo.bsqrsfz);
				
				Ext.getCmp("queryId").handler();
				
			}
		});
	}
	if(getQueryParam("jumpToSwzx")){
		form20005.getForm().findField("ryid").setValue(getQueryParam("ryid"));
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