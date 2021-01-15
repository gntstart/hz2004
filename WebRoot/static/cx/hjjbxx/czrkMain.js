
var cxCount = 1;
var rynbid = null;
var hhnbid = null;
var ryid = null;
var hhid = null;
var mlpnbid = null;
var bzdz = null;
var queryFlag = false;
var selRes = null;
var ryzl2 = null;
var lsjlzl = null;

var htmlStrStart = '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="cx/hjjbxx/ckxx/queryXxxx?goto=';
var htmlStrEnd = '"></iframe>';
//'?rynbid='+ getRynbid(rynbid) + "&goto=queryHdxx&hhnbid=" + getQueryParam("hhnbid") + '"> </iframe>';
var zpWindow = null;
var selectRynbid = null;
var selectHhnbidselectHhnbid = null;

Ext.onReady(function(){
	Ext.QuickTips.init();
	
	var hzywid = getQueryParam("hzywid");
	var pch = getQueryParam("pch");
	var ywlb = getQueryParam("ywlb");
	var hzywjo = null;
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10019,20000",function(){}))
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
    	title: '',
    	height:100,
		url: 'yw/common/queryRyxx.json',
    	showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){
				
			},rowdblclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				if(selRes && selRes.data){
					var zpid = selRes.data.zpid;
					if(zpid &&　zpid != 0){
						Ext.getCmp('p4Img').body.update("<IMG SRC='yw/common/img/render/" + zpid + "' width='100%' height='100%' />");
					}else{
						Ext.getCmp('p4Img').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
					}
					p4Form20000.getForm().loadRecord(selRes);
					Gnt.toFormReadyonly(p4Form20000);
					Ext.getCmp('card').getLayout().setActiveItem(4);
					Ext.getCmp('p4Title').body.update('<b style="color:red">户成员信息</b>');
					
				}
			}
		}
	});
	
	var form20000 = new Gnt.ux.SjpzForm({
		pzlb: '20000',
		url: 'yw/queryRyxx',
		region:'center',
		title:'',
		cols:2,
//		height:300,
		formType:'query'
	});
	var newNewform20000 = new Gnt.ux.SjpzForm({
		pzlb: '20000',
		url: 'yw/queryRyxx',
		region:'center',
		title:'',
		cols:2,
//		height:300,
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
	var p4Form20000 = new Gnt.ux.SjpzForm({
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
	var cxfw = new Ext.data.SimpleStore({
		id:0,
		fields:[{name: 'code', mapping: 0},{name: 'name', mapping: 1}],
	   	data:[['0','所外'],['1','区县外'],['2','地市外'],['3','所有'],['4','最新']]
	});
	var p1_1 = new Gnt.ux.ZdycxPanel({
		pzlb:form20000.pzlb,
		region:'center',
		layout:'border',
		border:false,
		frame: false,
		height:400
	});	
	var p1 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[
			{
				id:"nextId",
				border:false,
				frame: false,
				region:'center',
				visible:false,
	//        	height: 300,
				items:[hcyGrid]
			},
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
						html: '<div class="text" style="text-align:center;"><br /><h2>常住人口查询</h2></div>',
						height: 80,
						region:'north',
						bodyStyle:'padding:15px'
					},form20000]
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
			{
				id:"southId",
	        	region:'south',
	        	height:250,
	            border:false,
	            frame:false,
	            items:[{
	            	id:'fwId',
	            	title: '选择查询人员的范围',
//					columnWidth: 3,
		            xtype: 'fieldset',
		            autoHeight: true,
		            layout:'column',
		            defaults:{
		            	xtype:'radio',
		            	columnWidth: .3,
		            	fieldLabel: '',
		            	name: 'fw'
		            },
		            items: [{
		            	id:'r11',
		            	boxLabel: '查询所属派出所的人口',
		            	name:'fw',
		            	inputValue : "1",
		            	checked:true
		            },{
		            	id:'r12',
		            	boxLabel: '查询所属区县的人口',
		            	name:'fw',
		            	inputValue : "2"
		            },{
		            	id:'r13',
		            	boxLabel: '查询所有人口',
		            	name:'fw',
		            	inputValue : "3"
		            }]
				},{
	            	title: '选择查询人员的状态',
//					columnWidth: 3,
		            xtype: 'fieldset',
		            autoHeight: true,
		            layout:'column',
		            defaults:{
		            	columnWidth: .2,
		            	fieldLabel: '',
		            	name: 'zt'
		            },
		            items: [{
		            	id:'c11',
		            	xtype:'checkbox',
		            	boxLabel: '查询常住人口信息',
		            	checked:true,
		            	name:'czrkzt',
		            	inputValue : "1",  
        				listeners:{
        					'check': function(obj,checked){
        						if(checked){
        							cxCount ++;
        							
        							if(cxCount > 0){
        								Ext.getCmp('queryId').setDisabled(false);
        							}
        						}else{
        							cxCount --;
        							
        							if(cxCount == 0){
        								Ext.getCmp('queryId').setDisabled(true);
        							}
        						}
        					}
        				}
		            },{
		            	id:'c12',
		            	xtype:'checkbox',
		            	boxLabel: '查询死亡人口信息',
		            	name:'swrkzt',
		            	inputValue : "2",  
        				listeners:{
        					'check': function(obj,checked){
        						if(checked){
        							cxCount ++;
        							
        							if(cxCount > 0){
        								Ext.getCmp('queryId').setDisabled(false);
        							}
        						}else{
        							cxCount --;
        							
        							if(cxCount == 0){
        								Ext.getCmp('queryId').setDisabled(true);
        							}
        						}
        					}
        				}
		            },{
		            	id:'c13',
		            	xtype:'checkbox',
		            	boxLabel: '查询迁出到',
		            	name:'rqrkzt',
		            	inputValue : "3",  
		            	columnWidth: .1,
        				listeners:{
        					'check': function(obj,checked){
        						if(checked){
        							cxCount ++;
        							
        							if(cxCount > 0){
        								Ext.getCmp('queryId').setDisabled(false);
        							}
        						}else{
        							cxCount --;
        							
        							if(cxCount == 0){
        								Ext.getCmp('queryId').setDisabled(true);
        							}
        						}
        					}
        				}
		            },{
		            	columnWidth : 0.05,
	    	           	bodyStyle:'margin:0 0 10 0',
	        	       	items: [{
	        	       		id:'c14',
							hiddenName:'clbs',
							anchor:'100%',
							xtype:'combo',
							mode: 'local',
							width : 60,
	            			triggerAction: 'all',
							valueField:"code",
	      					displayField:"name",
							selectOnFocus:true,
							emptyText : '请选择',
							typeAhead: true,  
							editable:false,
							forceSelection: true,
	    					blankText:'请选择',
	            			lazyRender:true,
	            			value:'4',
	            			store:cxfw
						}]
					},{
						border:false,
						frame: false,
		            	html:'的人口信息'
		            }]
				},{
	            	title: '选择查询人员的种类',
//					columnWidth: 3,
		            xtype: 'fieldset',
		            autoHeight: true,
		            layout:'column',
		            defaults:{
		            	xtype:'checkbox',
		            	columnWidth: .2,
		            	fieldLabel: '',
		            	name: 'zl'
		            },
		            items: [{
		            	id:'c21',
		            	boxLabel: '包括业务冲销的人员',
		            	name:'bkyw',
		            	inputValue : "1"
		            },{
		            	id:'c22',
		            	boxLabel: '包括历史记录',
		            	name:'bkls',
		            	inputValue : "2"
		            }]
				},{
					border:false,
					frame: false,
		        	region:'south',
		        	height: 40,
		        	bodyStyle: 'padding:5px',
		        	items:[{
		        		border:false,
						frame: false,
						layout:'table',
						layoutConfig: {
				        	columns: 15
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
				        			text:'复杂条件',
				        			minWidth:60,
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
				    		},
			    		    {
								border:false,
								frame: false,
				    		    items:[
				    		    	{
					    	    	xtype: 'DkButton',
					    	    	form:form20000,
					    	    	callback: function(){
					    	    		//Ext.getCmp("queryId").handler();
					    	    	}
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
				    		},/*{
								border:false,
								frame: false,
				    		    items:[{
				    		    	id:'c31',
					        		xtype:'checkbox',
					        		boxLabel : "显示记录总数",  
					        		name : "xxzs",  
					        		inputValue : "2"  
					        	}]
				    		},{
				    			columnWidth: .3,
								border:false,
								frame: false
				    		},{
								border:false,
								frame: false,
				    		    items:[new Ext.Button({
				    		    	id:'preId',
				        			text:'上一步',
				        			disabled:true,
				        			minWidth:60,
				        			handler:function(){
				        				Ext.getCmp('preId').setDisabled(true);
				        				Ext.getCmp('fzId').setDisabled(false);
				        				Ext.getCmp('nexId').setDisabled(false);
				        				Ext.getCmp('nextId').setVisible(false);
				        				Ext.getCmp('centerId').setVisible(true);
				        			}
				        		})]
				    		},{
				    			columnWidth: .01,
								border:false,
								frame: false
				    		},{
								border:false,
								frame: false,
				    		    items:[new Ext.Button({
				    		    	id:'nexId',
				        			text:'下一步',
				        			minWidth:60,
				        			handler:function(){
				        				Ext.getCmp('preId').setDisabled(false);
				        				Ext.getCmp('fzId').setDisabled(true);
				        				Ext.getCmp('nexId').setDisabled(true);
				        				Ext.getCmp('nextId').setVisible(true);
				        				Ext.getCmp('centerId').setVisible(false);
				        			}
				        		})]
				    		},{
				    			columnWidth: .01,
								border:false,
								frame: false
				    		},*/{
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
				        				var data = form20000.getForm().getValues();
										
				        				/**
				        					2018.11.09
				        					修改 刁杰
				        					外部跳转查询仅限一次
				        					返回了查询界面后按照原先查询页面逻辑
				        				 */
				        				if(getQueryParam("jumpToCzrkcx") && getQueryParam("jumpToCzrkcx")!="" && queryFlag){
				        					data.ryid = getQueryParam("ryid");
				        					data.ryzl1 = 1;
				        				}else{
				        					if(Gnt.util.isEmpty(data)){
				        						showInfo("至少输入一个查询条件！");
				        						return;
				        					}
				        				}
				        				data.log_code = "F1001";
										Ext.Msg.wait("正在执行查询，请稍后...");
										var ary = p1_1.xszdGrid.store.data.items;
										var pxary = p1_1.pxzdGrid.store.data.items;//length 长度;
										var store = czrkGrid.store;
				        				store.removeAll();
				        				store.baseParams = data;
				        				var header=[];
										var shuxing = [];
										var zdyValueKey = null;
										if(p1_1.qybz){
											czrkGrid.reconfigure(
													czrkGrid.store,new Ext.grid.ColumnModel(Gnt.ZdyColumn(ary)));
											czrkGrid.zdyValueKey = Gnt.zdyValueKey(ary);
											czrkGrid.pxary = pxary;
											zdyValueKey = Gnt.zdyValueKey(ary);
										}else{
											czrkGrid.zdyValueKey = Gnt.zdyValueKey(ary);
											czrkGrid.pxary = pxary;
											
											zdyValueKey = Gnt.zdyValueKey(ary);
										};
										czrkGrid.type = 'queryCzrk';
										var config=czrkGrid.colModel.config;
										Ext.each(config,function(r){
											header.push(r.header);
											shuxing.push(r.dataIndex);
										});
										store.baseParams.header = encodeURI(header);
										store.baseParams.shuxing = encodeURI(shuxing);
										store.baseParams.zdyValueKey = Ext.encode(zdyValueKey);
										store.baseParams.daochuFlag = 'queryCzrk';

				        				if(!getQueryParam("jumpToCzrkcx")){
				        					applyParam(store);
				        				}
				        				
//				        				Ext.apply(store.baseParams, rkfwRadio.items);
				        				store.load({params:{start:0, limit:20}});
				        				Ext.each(pxary, function(pxObj){
				        					store.sort(pxObj.data.fieldname, 'ASC');
				    					});
				        				
				        				/**
				        					改变颜色
				        				 */
				        				store.on('load',function(s,records){
				        					var girdcount = 0;
				        					s.each(function(r){
				        						if(0 == r.get("ryzt")){
				        						}else if(1 == r.get("ryzt")){
//				        							czrkGrid.getView().getRow(girdcount).style.color = "#FF0000";	//字体颜色
				        							//czrkGrid.getView().getRow(girdcount).style.background = "#FF0000";	//背景色
				        						}else{
//				        							czrkGrid.getView().getRow(girdcount).style.color = "#227488";
				        							//czrkGrid.getView().getRow(girdcount).style.background = "#227488";
				        						}
				        						
				        						girdcount ++ ;
				        						
				        					});
				        					
				        					if(records.length>0){
				        						curIndex = 0;
				        						czrkGrid.fireEvent("rowclick",czrkGrid,curIndex);
				        						czrkGrid.getSelectionModel().selectRange(curIndex,curIndex);
				        						if(getQueryParam("jumpToCzrkcx") && getQueryParam("jumpToCzrkcx")!=""){
				        							if(records.length>1){
				        								Ext.getCmp('card').getLayout().setActiveItem(1);
				        							}else{
				        								czrkGrid.fireEvent("rowdblclick",czrkGrid,0);
				        								Ext.getCmp('card').getLayout().setActiveItem(2);
				        							}
				        						}else{
				        							Ext.getCmp('card').getLayout().setActiveItem(1);
				        						}
				        						if(hzywid != null){
				        							czrkGrid.fireEvent("rowdblclick",czrkGrid,0);
				        						}
				        					}else{
				        						showInfo("没有找到相关的人员资料，常住人口无法查询！");
				        					}
				        					Ext.Msg.hide();
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
				    		}
				    		 
				    	]
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
					
					
				}]
	        }
		]
	});
	
	var czrkGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		pzlb: '20000',
		url:'cx/hjjbxx/ckxx/getRkxx.json',
		region:'north',
		height:350,
		title: '',
		dcFlag:true,
		showPaging:true,
		callCellmeta:function(r){
			if(r.data.ryzt=='0'){
				return '#000000';
			}
			else if(r.data.ryzt=='1'){
				return '#C41A11';
			}
			else {
				return '#20BBAF';
			}
		},
		listeners:{
			rowclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				
				selectRynbid = selRes.data.rynbid;
    			selectHhnbid = selRes.data.hhnbid;
    			
				var store1 = hcyGrid.store;
				store1.baseParams = {
						hhnbid:selRes.data.hhnbid,
//						hhnbid:getQueryParam("hhnbid"),
						start:0,
						limit:20
					};
				store1.load();
				
				var store2 = lsxxGrid.store;
				store2.removeAll();
				store2.baseParams = {
						rynbid:selRes.data.rynbid,
//						rynbid:getQueryParam("rynbid"),
						config_key:'queryPoHJXX_CZRKJBXXB2',
						start:0,
						limit:20
					};
				store2.load();
				
				/**
					改变颜色
				 */
				store2.on('load',function(s,records){
					var girdcount=0;
					s.each(function(r){
						
//						alert(r.get("ryzt"));
						/**
							0 : 正常
							1 : 死亡
							2 : 迁出
							3 : 服兵役
							4 : 出国境定居
							6 : 失踪
							7 : 删除
							8 : 注销
							9 : 其它
						 */
						if(0 == r.get("ryzt")){
							
						}else if(1 == r.get("ryzt")){
							lsxxGrid.getView().getRow(girdcount).style.color = "#FF0000";
						}else{
							lsxxGrid.getView().getRow(girdcount).style.color = "#227488";
						}
						
						girdcount ++ ;
					});
				});
				
			},
			rowdblclick:function(grid,row){
				selRes = grid.store.getAt(row);
				
				if(selRes && selRes.data){
					rynbid = selRes.data.rynbid;
					hhnbid = selRes.data.hhnbid;
					ryid = selRes.data.ryid;
					hhid = selRes.data.hhid;
					bzdz = selRes.data.bzdz;
					Ext.Msg.wait("操作正在执行中，请稍后...");
//					tabs.setActiveTab(2);
					tabs.setActiveTab(0);
					/*
					var el = Ext.get("rkxx");
					alert(el);
					el.dom.src = "";
		   			el.dom.src = "cx/hjjbxx/ckxx/queryXxxx?goto=queryRkxx";
		   			*/
					Ext.getCmp('card').getLayout().setActiveItem(2);
					Ext.Msg.hide();
					
				}else{
					
				}
			}
		}
	});

	var zhcx_dialog = new Gnt.ux.ZhcxDialog({
		pzlb : form20000.pzlb,
		grid:czrkGrid,
		applyParam:function(){
			applyParam(this.grid.store);
		},
		callback: function(where){
			var ary = p1_1.xszdGrid.store.data.items;
			var pxary = p1_1.pxzdGrid.store.data.items;//length 长度;
			if(p1_1.qybz){
				czrkGrid.reconfigure(
						czrkGrid.store,new Ext.grid.ColumnModel(Gnt.ZdyColumn(ary)));
				czrkGrid.zdyValueKey = Gnt.zdyValueKey(ary);
				czrkGrid.pxary = pxary;
			}else{
				czrkGrid.zdyValueKey = Gnt.zdyValueKey(ary);
				czrkGrid.pxary = pxary;
			};
//			var config=czrkGrid.colModel.config;
//			Ext.each(config,function(r){
//				header.push(r.header);
//				shuxing.push(r.dataIndex);
//			});
			var grid = this.grid;
			var store = grid.store;
			var length = 0;
			var data = {"where": where};
			store.removeAll();
			data.pzlb = grid.store.pzlb;
			data.config_key = this.config_key;
			data.log_code = "F1001";
			
			//add by zjm 20201223 复杂查询传入列表头 属性 自定义字段，用于导出功能使用
			var header=[];
			var shuxing = [];
			var zdyValueKey = Gnt.zdyValueKey(ary);
			grid.type = 'queryCzrk';
			Ext.each(ary,function(r){
				header.push(r.data.displayname);
				shuxing.push(r.data.fieldname);
			});
			data.header = encodeURI(header);
			data.shuxing = encodeURI(shuxing);
			data.zdyValueKey = Ext.encode(zdyValueKey);
			data.daochuFlag = 'queryCzrk';
			
			store.baseParams = data;
			if(this.applyParam){
				this.applyParam();
			}
//			store.baseParams.header = encodeURI(header);
//			store.baseParams.shuxing = encodeURI(shuxing);
//			store.baseParams.zdyValueKey = Ext.encode(zdyValueKey);

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
	
	var lsxxGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		pzlb: '20000',
    	region:'south',
		url: 'cx/hjjbxx/ckxx/getXxxx.json',
    	title: '',
    	height:100,
    	showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){
    			selRes = g.store.getAt(rowIndex);
    			curIndex = rowIndex;
    		},
			rowdblclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				if(selRes && selRes.data){
					var zpid = selRes.data.zpid;
					if(zpid &&　zpid != 0){
						Ext.getCmp('p4Img').body.update("<IMG SRC='yw/common/img/render/" + zpid + "' width='100%' height='100%' />");
					}else{
						Ext.getCmp('p4Img').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
					}
					p4Form20000.getForm().loadRecord(selRes);
					Gnt.toFormReadyonly(p4Form20000);
					Ext.getCmp('card').getLayout().setActiveItem(4);
					Ext.getCmp('p4Title').body.update('<b style="color:red">历史信息</b>');
					
				}
			}
		}
	});
	
	var p2 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[czrkGrid,
			{
				id:"p2CenterId",
				border:false,
				frame: false,
				region:'center',
				items:[{
					id:"p2Hcy",
	    	        border:false,
	    	        frame:false,
					region:'south',
					hidden:true,
					items:[hcyGrid]
				},
				{
					id:"p2Lsxx",
	    	        border:false,
	    	        frame:false,
					region:'south',
					hidden:true,
					items:[lsxxGrid]
				}]
			},
			{
				id:"p2SouthId",
	        	region:'south',
	        	height:100,
	            border:false,
	            frame:false,
	            items:[{
	            	title: '字体颜色说明',
		            xtype: 'fieldset',
		            autoHeight: true,
		            items: [{
						border:false,
						frame: false,
			        	region:'center',
//			        	height: 40,
			        	//					上	右	下	左
//			        	bodyStyle: 'padding:0px 0px 10px 0px ',
						layout:'table',
						layoutConfig: {
				        	columns: 30
				        },
						items: [
							{
								border:false,
								frame: false,
								width:10
							},{
								width:60,
								height:20,
								bodyStyle:'background:black;'
							},{
								width: 20,
								border:false,
								frame: false
							},{
								border:false,
								frame: false,
								html:'正	常',
								width:100
							},{
								width: 10,
								border:false,
								frame: false
							},{
								width:60,
								height:20,
								bodyStyle:'background:#FF0000;'
							},{
								width: 20,
								border:false,
								frame: false
							},{
								border:false,
								frame: false,
								html:'死	亡',
								width:100
							},{
								width: 10,
								border:false,
								frame: false
							},{
								width:60,
								height:20,
								bodyStyle:'background:#227488;'
							},{
								width: 20,
								border:false,
								frame: false
							},{
								border:false,
								frame: false,
								html:'其	它(迁出,服兵役,出国境定居,删除,注销,其它)',
								width:400
							}
						]
					}]
					}
	            	,{
						title: '',
			            xtype: 'fieldset',
			            border:false,
		            	frame: false,
						height: 50,
//			            autoHeight: true,
			            items: [{
			            	border:false,
			            	frame: false,
			            	region:'south',
			            	layout:'table',
							layoutConfig: {
					        	columns: 20
					        },
							//bodyStyle: 'height:30px;',
//			            	layout:'column',
			            	items: [
			            		{
			            			border:false,
			            			frame: false,
			            			items:[{
			            				xtype:'checkbox',
			            				boxLabel : "户成员信息",  
			            				id : 'c41',  
			            				name : "p2Radio",  
			            				inputValue : "1",  
			            				listeners:{
			            					'check': function(obj,checked){
			            						if(checked){
			            							Ext.getCmp('p2Hcy').setVisible(true);
			            							Ext.getCmp('p2Lsxx').setVisible(false);
			            							Ext.getCmp('c42').setValue(false);
			            						}else{
			            							Ext.getCmp('p2Hcy').setVisible(false);
			            						}
			            					}
			            				}
			            			}]
			            		},{
			            			width: 10,
			            			border:false,
			            			frame: false
			            		},{
			            			border:false,
			            			frame: false,
			            			items:[{
			            				xtype:'checkbox',
			            				boxLabel : "历史信息",  
			            				id : 'c42',  
			            				name : "p2Radio",  
			            				inputValue : "2",  
			            				listeners:{
			            					'check': function(obj,checked){
			            						if(checked){
			            							czrkGrid.height=200;
			            							p2.doLayout();
			            							Ext.getCmp('p2Hcy').setVisible(false);
			            							Ext.getCmp('p2Lsxx').setVisible(true);
			            							Ext.getCmp('c41').setValue(false);
			            						}else{
			            							Ext.getCmp('p2Lsxx').setVisible(false);
			            						}
			            					}
			            				}
			            			}]
			            		},{
			            			width: 200,
			            			border:false,
			            			frame: false
			            		},{
			            			border:false,
			            			frame: false,
			            			items:[new Ext.Button({
			            				id:'cbhkId',
			            				text:'常表户口',
			            				minWidth:60,
			            				handler:function(){
			            					if(czrkGrid.getStore().getCount() > 0&&czrkGrid.getSelectionModel().getSelections().length>0){
				        						var pcs = czrkGrid.getSelectionModel().getSelections()[0].data.pcs;
				        						if((xzqhfw.indexOf(pcs.substring(0, 6)) == -1&&pcsfw.indexOf(pcs) == -1)&&!eval(user.isadmin.toLowerCase())){
				        							alert("非本派出所成员，没有打印的权利！");
				        							return;
				        						}
				        						selectHhnbid=czrkGrid.getSelectionModel().getSelections()[0].data.hhnbid;
				        						selectRynbid=czrkGrid.getSelectionModel().getSelections()[0].data.rynbid;
				        						var hzywObj ={
				            							ywlb:ywlb,
				            							hzywid:hzywid,
				            							gmsfhm:hzywjo?hzywjo[bsqrsfz]:null
				            					};
				            					printRk(2,selectHhnbid,selectRynbid,[true,true,false,false,false],[true,true,false,false,false],[true,true,false,false,false],hzywObj);
				            					
				        					}else{
				        						alert("请先执行查询!");
				        					}
			            				}
			            			})]
			            		},{
			            			width: 10,
			            			border:false,
			            			frame: false
			            		},{
			            			border:false,
			            			frame: false,
			            			items:[new Ext.Button({
			            				id:'hjzmId',
			            				text:'户籍证明',
			            				minWidth:60,
			            				handler:function(){
			            					if(czrkGrid.getStore().getCount() > 0){
				        						selectHhnbid=czrkGrid.getSelectionModel().getSelections()[0].data.hhnbid;
				        						selectRynbid=czrkGrid.getSelectionModel().getSelections()[0].data.rynbid;
				        						var hzywObj ={
			                							ywlb:ywlb,
			                							hzywid:hzywid,
			                							gmsfhm:hzywjo&&hzywjo.bsqrsfz?hzywjo.bsqrsfz:""	
			                					};
				        						printRk(2,selectHhnbid,selectRynbid,[false,false,true,false,false],[false,false,true,false,false],[false,false,true,false,false],hzywObj);
				        					}else{
				        						alert("请先执行查询!");
				        					}
				        				}
			            			})]
			            		},{
			            			width: 10,
			            			border:false,
			            			frame: false
			            		},{
			            			border:false,
			            			frame: false,
			            			items:[new Ext.Button({
			            				id:'xxxxId',
			            				text:'详细信息',
			            				minWidth:60,
			            				handler:function(){
			            					if(czrkGrid.getStore().getCount() > 0){
				        						if(czrkGrid.getSelectionModel().getCount() ==1){
				        							czrkGrid.fireEvent("rowdblclick",czrkGrid,czrkGrid.getSelectionModel().lastActive);
//				        							rynbid = czrkGrid.getSelectionModel().getSelections()[0].data.rynbid;
//				        							hhnbid = czrkGrid.getSelectionModel().getSelections()[0].data.hhnbid;
//				        							tabs.getUpdater().refresh();
//				        							tabs.setActiveTab(0);
//				        							Ext.getCmp('card').getLayout().setActiveItem(2);
				        						}else{
				        							alert("请选择需要查询的人员!");
				        						}
				        					}else{
				        						alert("请先执行查询!");
				        					}			            					
			            				}
			            			})]
			            		},{
			            			width: 10,
			            			border:false,
			            			frame: false
			            		},{
			            			border:false,
			            			frame: false,
			            			items:[new Ext.Button({
			            				id:'rzsId',
			            				text:'人追溯',
			            				minWidth:60,
			            				handler:function(){
			            					if(czrkGrid.getStore().getCount() > 0){
			            						if(czrkGrid.getSelectionModel().getCount() ==1){
			            							
			            							rynbid = czrkGrid.getSelectionModel().getSelections()[0].data.rynbid;
			            							selectRyid = czrkGrid.getSelectionModel().getSelections()[0].data.ryid;
				            						Gnt.toFormReadyonly(newForm20000);
//			            							v.doLayout();
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
//			            									var pkvalue = store4.getAt(0).data[newForm20000.bindStore.pkname];
//			            									var re = newForm20000.bindStore.getById(pkvalue);
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
						            						
			            								}
			            							},newRkxxGrid); 
			            						}else{
			            							alert("请选择需要查询的人员!");
			            						}
			            					}else{
			            						alert("请先执行查询!");
			            					}
			            					
			            				}
			            			})]
			            		},{
			            			width: 10,
			            			border:false,
			            			frame: false
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
			            			width: 10,
			            			border:false,
			            			frame: false
			            		},{
			            			border:false,
			            			frame: false,
			            			items:[new Ext.Button({
			            				text:'返回',
			            				minWidth:60,
			            				handler:function(){
			            					Ext.getCmp('card').getLayout().setActiveItem(0);
			            					//window.location.reload();
			            				}
			            			})]
			            		}
			            		]
			            }]
					}
					
	            ]
				
			}
		]
//		,buttons:[
//			{
//				id:'cbhkId',
//				text:'常表户口',
//				minWidth:60,
//				handler:function(){
//					if(czrkGrid.getStore().getCount() > 0&&czrkGrid.getSelectionModel().getSelections().length>0){
//						var pcs = czrkGrid.getSelectionModel().getSelections()[0].data.pcs;
//						if((xzqhfw.indexOf(pcs.substring(0, 6)) == -1&&pcsfw.indexOf(pcs) == -1)&&!eval(user.isadmin.toLowerCase())){
//							alert("非本派出所成员，没有打印的权利！");
//							return;
//						}
//						selectHhnbid=czrkGrid.getSelectionModel().getSelections()[0].data.hhnbid;
//						selectRynbid=czrkGrid.getSelectionModel().getSelections()[0].data.rynbid;
//    					
//    					printRk(2,selectHhnbid,selectRynbid,[true,true,false,false,false],[true,true,false,false,false],[true,true,false,false,false]);
//    					
//					}else{
//						alert("请先执行查询!");
//					}
//				}
//			},{
//				id:'hjzmId',
//				text:'户籍证明',
//				minWidth:60,
//				handler:function(){
//					if(czrkGrid.getStore().getCount() > 0){
//						selectHhnbid=czrkGrid.getSelectionModel().getSelections()[0].data.hhnbid;
//						selectRynbid=czrkGrid.getSelectionModel().getSelections()[0].data.rynbid;
////    					dyWindow.show();
////						dyGrid.store.load({params:{type:2,hhnbid:selectHhnbid, rynbid:selectRynbid}});
//						printRk(2,selectHhnbid,selectRynbid,[false,false,true,false,false],[false,false,true,false,false],[false,false,true,false,false]);
//					}else{
//						alert("请先执行查询!");
//					}
//				}
//			},{
//				id:'xxxxId',
//				text:'详细信息',
//				minWidth:60,
//				handler:function(){
//					
//					if(czrkGrid.getStore().getCount() > 0){
//						
//						if(czrkGrid.getSelectionModel().getCount() ==1){
//							
//							rynbid = czrkGrid.getSelectionModel().getSelections()[0].data.rynbid;
//							hhnbid = czrkGrid.getSelectionModel().getSelections()[0].data.hhnbid;
//							
//							tabs.getUpdater().refresh();
//							
//							tabs.setActiveTab(0);
//							
////							tabs.getActiveTab().loader.load();
////							alert(tabs.getActiveTab());
//							
//							Ext.getCmp('card').getLayout().setActiveItem(2);
//						}else{
//							alert("请选择需要查询的人员!");
//						}
//					}else{
//						alert("请先执行查询!");
//					}
//					
//				}
//			},{
//				id:'rzsId',
//				text:'人追溯',
//				minWidth:60,
//				handler:function(){
//					
//				}
//			},{
//				text:'关闭',
//				minWidth:60,
//				handler:function(){
//					window.parent.closeWorkSpace();
//				}
//			},{
//				text:'返回',
//				minWidth:60,
//				handler:function(){
//					Ext.getCmp('card').getLayout().setActiveItem(0);
//				}
//			}
//		]
	});
	
	//释放iframe占用资源
	function fixIFrame(o, p){
		var iFrame = p.getEl().dom; 
		if (iFrame.src) {
			iFrame.src = "javascript:false"; 
		}
	}

	//定义TabPanel的控制菜单
	Ext.ux.TabCloseMenu = function(){
	    var menu, ctxItem;
	    this.init = function(tabs){
	        tabs.on('contextmenu', onContextMenu);
	    }
	    function onContextMenu(tabs, item, e){
	        if(!menu){
	            menu = new Ext.menu.Menu([{
	                id: tabs.id + '-close',
	                text: '关闭当前页',
	                handler : function(){
	                    tabs.remove(ctxItem);
	                }
	            },{
	                id: tabs.id + '-close-others',
	                text: '关闭其它页',
	                handler : function(){
	                    tabs.items.each(function(item){
	                    	if(item.closable && item != ctxItem){
	                            tabs.remove(item);
	                        }
	                    });
	                }
	            }]);
	        }
	        ctxItem = item;
	        var items = menu.items;
	        
	        //设置【关闭当前页】菜单是否有效和当前页的closable属性一致
	        items.get(tabs.id + '-close').setDisabled(!item.closable);
	        
	        var disableOthers = true; 
	       	//遍历分页面板所有分页，查看除了自己，是否还有能够关闭的分页
	        tabs.items.each(function(){
	            if(this != item && this.closable){
	                disableOthers = false;
	                return false;
	            }
	        });
	        
	        //设置【关闭其它页】菜单是否有效
	        items.get(tabs.id + '-close-others').setDisabled(disableOthers);
	        
	        //在鼠标右击处显示菜单
	        menu.showAt(e.getPoint());
	    }
	};
	
	//定义分页面板
    var tabs = new Ext.TabPanel({
    	id:'tabs',
//        activeTab: 0,
        width:500,
        height:250,
        resizeTabs:false, 
        enableTabScroll:true,
        plain:false,
        deferredRender: false,
        //activeTab: 0,		//默认选中标签
        listeners:{
        	//在关闭分页的时候，调用函数释放iframe占用资源
        	beforeremove:fixIFrame.createDelegate(this),
        	beforeshow:function(){
        			Ext.getCmp('rkxx').body.update(htmlStrStart + getParams("queryRkxx") + htmlStrEnd);
        			Ext.getCmp('rkxx').getUpdater().refresh();
        	}
        },
        plugins: new Ext.ux.TabCloseMenu(),
        defaults:{
        	closable:false,
        	autoScroll: false,
        	frame: false,
        	shim: false,
        	xtype: 'panel',
        	iconCls : 'otherfile'
        },
        refresh:function(){
        	//alert(1111);
        	//this.getUpdater().refresh();
        	//updater.refresh();
        },
        items:[{
			id : "rkxx",
//			url: 'cx/hjjbxx/ckxx/queryRkxx',
			/*autoLoad: {
				url: 'cx/hjjbxx/ckxx/queryRkxx',
				params: {
					rynbid: getQueryParam("rynbid")
				},
				method: 'GET'
			},*/
			title : "人口信息",
//			html:' <iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'+'cx/hjjbxx/ckxx/queryXxxx?rynbid='+ getRynbid(rynbid) + "&goto=queryRkxx&hhnbid=" + getHhnbid(hhnbid) + '"> </iframe>',
			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
				activate:function(){
					//alert(getQueryParam("rynbid"));
					Ext.getCmp('rkxx').body.update(htmlStrStart + getParams("queryRkxx") + htmlStrEnd);
					this.getUpdater().refresh();
					
				}
			}
//        	,tabTip : "人口信息"
        },{
			id : "sbxx",
//			url: 'cx/hjjbxx/ckxx/querySbxx',
//			autoLoad: { url: 'cx/hjjbxx/ckxx/querySbxx', params: { data: "从客户端传入的参数" }, method: 'GET' },
			//tabTip : "常口信息查询",
			title : "四变信息",
//			html:' <iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'+'cx/hjjbxx/ckxx/queryXxxx?rynbid='+ getRynbid(rynbid) + "&goto=querySbxx&hhnbid=" + getHhnbid(hhnbid) + '"> </iframe>',
			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
				activate:function(){
					/*
					if(hzywid != null){
						*//**
							户政业务处理自动跳转时不允许使用其他业务
							暂时不生效
						 *//*
					}else{
						Ext.getCmp('sbxx').body.update(htmlStrStart + '?rynbid='+ getRynbid(rynbid) + "&goto=querySbxx&hhnbid=" + getHhnbid(hhnbid) + "&hzywid=" + hzywid  + htmlStrEnd);
						
						this.getUpdater().refresh();
					}
					*/
					Ext.getCmp('sbxx').body.update(htmlStrStart + getParams("querySbxx") + htmlStrEnd);
					
					this.getUpdater().refresh();
				}
			}
        },{
			id : "bggz",
//			url : "cx/hjjbxx/ckxx",
			//tabTip : "常口信息查询",
			title : "变更更正",
//			html:' <iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'+'cx/hjjbxx/ckxx/queryXxxx?rynbid='+ getRynbid(rynbid) + "&goto=queryBggz&hhnbid=" + getHhnbid(hhnbid) + '"> </iframe>',
			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
				activate:function(){
					Ext.getCmp('bggz').body.update(htmlStrStart + getParams("queryBggz") + htmlStrEnd);
					
					this.getUpdater().refresh();
				}
			}
        },{
			id : "hdxx",
//			url : "cx/hjjbxx/ckxx",
			//tabTip : "常口信息查询",
			title : "户地信息",
//			html:' <iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'+'cx/hjjbxx/ckxx/queryXxxx?rynbid='+ getRynbid(rynbid) + "&goto=queryHdxx&hhnbid=" + getHhnbid(hhnbid) + '"> </iframe>',
			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
				activate:function(){
					//alert(getQueryParam("rynbid"));
					Ext.getCmp('hdxx').body.update(htmlStrStart + getParams("queryHdxx") + htmlStrEnd);
					
					this.getUpdater().refresh();
				}
			}
            
        },{
			id : "hcybd",
//			url : "cx/hjjbxx/ckxx",
			//tabTip : "常口信息查询",
			title : "户成员变动",
			
//			html:' <iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'+'cx/hjjbxx/ckxx/queryXxxx?rynbid='+ getRynbid(rynbid) + "&goto=queryHcybd&hhnbid=" + getHhnbid(hhnbid) + '"> </iframe>',
			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
				activate:function(){
					//alert(getQueryParam("rynbid"));
					Ext.getCmp('hcybd').body.update(htmlStrStart + getParams("queryHcybd") + htmlStrEnd);
					
					this.getUpdater().refresh();
				}
			}
            
        },{
			id : "zjxx",
//			url : "cx/hjjbxx/ckxx",
			//tabTip : "常口信息查询",
			title : "证件信息",
//			html:' <iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'+'cx/hjjbxx/ckxx/queryXxxx?rynbid='+ getRynbid(rynbid) + "&goto=queryZjxx&hhnbid=" + getHhnbid(hhnbid) + '"> </iframe>',
			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
				activate:function(){
					//alert(getQueryParam("rynbid"));
					Ext.getCmp('zjxx').body.update(htmlStrStart + getParams("queryZjxx") + htmlStrEnd);
					
					this.getUpdater().refresh();
				}
			}
            
        },{
			id : "slxx",
//			url : "cx/hjjbxx/ckxx",
			//tabTip : "常口信息查询",
			title : "受理信息",
//			html:' <iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'+'cx/hjjbxx/ckxx/queryXxxx?rynbid='+ getRynbid(rynbid) + "&goto=querySlxx&hhnbid=" + getHhnbid(hhnbid) + '"> </iframe>',
			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
				activate:function(){
					//alert(getQueryParam("rynbid"));
					Ext.getCmp('slxx').body.update(htmlStrStart + getParams("querySlxx") + htmlStrEnd);
					
					this.getUpdater().refresh();
				}
			}
            
        },{
			id : "hgl",
//			url : "cx/hjjbxx/ckxx",
			//tabTip : "常口信息查询",
			title : "户关联",
//			html:' <iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'+'cx/hjjbxx/ckxx/queryXxxx?rynbid='+ getRynbid(rynbid) + "&goto=queryHgl&hhnbid=" + getHhnbid(hhnbid) + '"> </iframe>',
			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
				activate:function(){
					//alert(getQueryParam("rynbid"));
					Ext.getCmp('hgl').body.update(htmlStrStart + getParams("queryHgl") + htmlStrEnd);
					
					this.getUpdater().refresh();
				}
			}
            
        },{
			id : "hcy",
//			url : "cx/hjjbxx/ckxx",
			//tabTip : "常口信息查询",
			title : "户成员",
//			html:' <iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'+'cx/hjjbxx/ckxx/queryXxxx?rynbid='+ getRynbid(rynbid) + "&goto=queryHcy&hhnbid=" + getHhnbid(hhnbid) + '"> </iframe>',
			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
				activate:function(){
					//alert(getQueryParam("rynbid"));
					Ext.getCmp('hcy').body.update(htmlStrStart + getParams("queryHcy") + htmlStrEnd);
					
					this.getUpdater().refresh();
				}
			}
            
        },{
			id : "xzd",
//			url : "cx/hjjbxx/ckxx",
			//tabTip : "常口信息查询",
			title : "现住地",
//			html:' <iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'+'cx/hjjbxx/ckxx/queryXxxx?rynbid='+ getRynbid(rynbid) + "&goto=queryXzd&hhnbid=" + getHhnbid(hhnbid) + '"> </iframe>',
			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
				activate:function(){
					//alert(getQueryParam("rynbid"));
					Ext.getCmp('xzd').body.update(htmlStrStart + getParams("queryXzd") + htmlStrEnd);
					
					this.getUpdater().refresh();
				}
			}
            
        },{
			id : "dyxx",
//			url : "cx/hjjbxx/ckxx",
			//tabTip : "常口信息查询",
			title : "打印信息",
//			html:' <iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'+'cx/hjjbxx/ckxx/queryXxxx?rynbid='+ getRynbid(rynbid) + "&goto=queryDyxx&hhnbid=" + getHhnbid(hhnbid) + '"> </iframe>',
			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
				activate:function(){
					//alert(getQueryParam("rynbid"));
					Ext.getCmp('dyxx').body.update(htmlStrStart + getParams("queryDyxx") + htmlStrEnd);
					
					this.getUpdater().refresh();
				}
			}
        }]
    });
	
	function applyParam(store){
		
		/**
			人员范围
		 */
		var fw = '';
		if(Ext.getCmp('r11').getValue()){
			fw = '1';
		}else if(Ext.getCmp('r12').getValue()){
			fw = '2';
		}else if(Ext.getCmp('r13').getValue()){
			fw = '3';
		}
		
		/**
			人员状态
		 */
		var czzt = '';
		if(Ext.getCmp('c11').getValue()){
			czzt = '1';
		}
		var swzt = '';
		if(Ext.getCmp('c12').getValue()){
			swzt = '1';
		}
		var qczt = '';
		if(Ext.getCmp('c13').getValue()){
			qczt = '1';
		}
		var fwzt = '';
		if(Ext.getCmp('c14').getValue()){
			fwzt = Ext.getCmp('c14').getValue();
		}
		
		
		/**
			人员种类
		 */
		var ywcxzl = '';
		if(Ext.getCmp('c21').getValue()){
			ywcxzl = '1';
		}
		lsjlzl = '';
		if(Ext.getCmp('c22').getValue()){
			lsjlzl = '1';
		}
		
		/**
			是否显示记录总数
		 */
		var zs = '';
//		if(Ext.getCmp('c31').getValue()){
//			zs = '1';
//		}
		
		
		Ext.apply(store.baseParams, {ryfw:fw,rkzt1:czzt,rkzt2:swzt,rkzt3:qczt,rkzt4:fwzt,ryzl1:ywcxzl,ryzl2:lsjlzl,xszs:zs});
		
		if(queryFlag){
			Ext.apply(czrkGrid.store.baseParams, {mlpnbid:getQueryParam("mlpnbid")});
			queryFlag = false;
		}
		
	}
	
	function getParams(type){
		
		var result = "";
		//rynbid='+ getRynbid(rynbid) +"&ryid=" + getRyid(ryid)+"&hhid=" + getHhid(hhid)+ "&goto=queryRkxx&hhnbid=" + getHhnbid(hhnbid) + "&hzywid=" + hzywid
		if(type){
			result += type;
		}
		if(getRynbid(rynbid)){
			result += "&rynbid=" + getRynbid(rynbid);
		}
		if(getRyid(ryid)){
			result += "&ryid=" + getRyid(ryid);
		}
		if(getHhid(hhid)){
			result += "&hhid=" + getHhid(hhid);
		}
		if(getHhnbid(hhnbid)){
			result += "&hhnbid=" + getHhnbid(hhnbid);
		}
		if(getMlpnbid(mlpnbid)){
			result += "&mlpnbid=" + getMlpnbid(mlpnbid);
		}
		if(hzywid){
			result += "&hzywid=" + hzywid+"&ywlb="+ywlb+"&gmsfhm="+hzywjo.bsqrsfz;
		}
		if(getBzdz(bzdz)){
			result += "&bzdzFlag=" + true;
		}
		//alert(getGmsfhm(gmsfhm));
		if(selRes&&selRes.data.gmsfhm){
			result += "&gmsfhm=" + selRes.data.gmsfhm;
		}
		if(lsjlzl){
			result += "&ryzl2=" + lsjlzl;
		}
		return result;
	}
	function getRynbid(rynbid){
		if(rynbid != null){
			return rynbid;
		}else{
			return getQueryParam("rynbid");
		}
	}
	
	function getHhnbid(hhnbid){
		if(hhnbid != null){
			return hhnbid;
		}else{
			return getQueryParam("hhnbid");
		}
	}
	function getRyid(ryid){
		if(ryid != null){
			return ryid;
		}else{
			return getQueryParam("ryid");;
		}
	}
	function getHhid(hhid){
		if(hhid != null){
			return hhid;
		}else{
			return getQueryParam("hhid");
		}
	}
	function getMlpnbid(mlpnbid){
		
		if(mlpnbid != null){
			return mlpnbid;
		}else{
			return getQueryParam("mlpnbid");
		}
	}
	function getBzdz(bzdz){
		
		if(bzdz != null){
			return bzdz;
		}else{
			return getQueryParam("bzdz");
		}
	}
	var p3 = new Ext.Panel({
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
	var p4 = new Ext.Panel({
		layout:'border',
		items:[p4Form20000,{
			id:"p4EastId",
        	region:'east',
        	width:180,
            border:false,
            frame:false,
            items: [{
            	id:"p4WestCenterId",
            	region:'center',
                border:false,
                frame:false
            },{
    			region:'north',
    			layout:'table',
    			border:false,
                frame:false,
    			layoutConfig: {
    				columns: 1
    			},
    			items: [{
    				layout:'border',
    				align:'north',
    				bodyStyle:'padding:10px 10px 10px 10px',
    				height:40,
    				border:false,
                    frame:false,
                    id:'p4Title',
    				html:'1111'
    			},{
					id:'p4Img',
    		 		title: '',
    		 		height:'100%',
    		 		bodyStyle:'padding:10px 10px 10px 10px',
    		 		align:'center',
    				width:150,
    				height:180,
    				rowspan: 1,
    				colspan:1
				},{
    				html:'',
    				bodyStyle:'padding:10px 25px 0px 20px',
    				layout:'table',
    				align:'south',
    				border:false,
    				frame:false,
    				layoutConfig: {
    					columns: 1
    				},
    				items:[
    					new Ext.Button({
    						text:'户籍证明',
    						minWidth:100,
    						handler:function(){
    							if(lsxxGrid.getStore().getCount() > 0){
	        						selectHhnbid=lsxxGrid.getSelectionModel().getSelections()[0].data.hhnbid;
	        						selectRynbid=lsxxGrid.getSelectionModel().getSelections()[0].data.rynbid;
	        						printRk(2,selectHhnbid,selectRynbid,[false,false,true,false,false],[false,false,true,false,false],[false,false,true,false,false]);
	        					}else{
	        						alert("请先执行查询!");
	        					}
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
        	items:[p1,p2,tabs,p3,p4]
        }
    });
	
    if(getQueryParam("ai")){
    	if(getQueryParam("jumpToCzrkcx")&& getQueryParam("jumpToCzrkcx")!=""){
    		queryFlag = true;
    		Ext.getCmp("queryId").handler();
    	}  
    	Ext.getCmp('card').getLayout().setActiveItem(getQueryParam("ai"));
    	if(getQueryParam("ai")==1){
    	}else if(getQueryParam("ai")==2){
    		tabs.setActiveTab(0);
    	}
//    	Ext.getCmp('card').getLayout().setActiveItem(getQueryParam("ai"));
    	
    	//tabs.setActiveTab(0);
    }else{
    	Ext.getCmp('nextId').setVisible(false);
    }
	
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
				
				form20000.getForm().findField("gmsfhm").setValue(jo.bsqrsfz);
				
				Ext.getCmp("queryId").handler();
				
			}
		});
	}
});