
var cxCount = 1;
var ywcxCount = 8;
var rynbid = null;
var selRes = null;
var htmlStrStart = '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="cx/hjjbxx/ckxx/queryXxxx';
var htmlStrEnd = '"></iframe>';
//'?rynbid='+ getRynbid(rynbid) + "&goto=queryHdxx&hhnbid=" + getQueryParam("hhnbid") + '"> </iframe>';

var selectRynbid = null;
var selectHhnbid = null;
var selectHjywid = null;
var selectRyid = "";
var zpWindow = null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10019,20021",function(){}))
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
    	visible:false,
		height:150,
    	title: '',
		url: 'yw/common/queryRyxx.json',
    	showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){}
		}
	});
	
	var form20021 = new Gnt.ux.SjpzForm({
		pzlb: '20021',
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
	var cxfw = new Ext.data.SimpleStore({
		id:0,
		fields:[{name: 'code', mapping: 0},{name: 'name', mapping: 1}],
	   	data:[['0','未处理'],['1','已处理'],['2','已删除']]
	});	
	var p1_1 = new Gnt.ux.ZdycxPanel({
		pzlb:form20021.pzlb,
		region:'center',
		layout:'border',
		visible:false,
		border:false,
		frame: false,
		height:370
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
						border:false,
						frame: false,
						id: "panelHtmlId" ,
						html: '<div class="text" style="text-align:center;"><h2>四变查询</h2></div>',
						height: 80,
						region:'north',
						bodyStyle:'padding:15px'
					},
					form20021
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
//				items:[{
//						border:false,
//						frame: false,
//						id: "panelHtmlId" ,
//						html: '<div class="text" style="text-align:center;"><h2>四变查询</h2></div>',
//						height: 80,
//						region:'north',
//						bodyStyle:'padding:15px'
//					},
//					form20021
//				]
//			},
			{
				id:"southId",
	        	region:'south',
	        	height:260,
	        	bodyStyle:'padding:0 0 0 10',
	            border:false,
	            frame:false,
		    	items: [{
	            	title: '选择查询的业务范围',
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
		            	boxLabel: '迁入',
		            	checked:true,
		            	name:'qr',
		            	inputValue : "1", 
		            	columnWidth: .1,
        				listeners:{
        					'check': function(obj,checked){
        						if(checked){
        							ywcxCount ++;
        							if(ywcxCount > 0){
        								Ext.getCmp('queryId').setDisabled(false);
        							}
        						}else{
        							ywcxCount --;
        							
        							if(ywcxCount == 0){
        								Ext.getCmp('queryId').setDisabled(true);
        							}
        						}
        					}
        				}
		            },{
		            	id:'c12',
		            	xtype:'checkbox',
		            	boxLabel: '出生',
		            	checked:true,
		            	name:'cs',
		            	inputValue : "2", 
		            	columnWidth: .1,
        				listeners:{
        					'check': function(obj,checked){
        						if(checked){
        							ywcxCount ++;
        							if(ywcxCount > 0){
        								Ext.getCmp('queryId').setDisabled(false);
        							}
        						}else{
        							ywcxCount --;
        							
        							if(ywcxCount == 0){
        								Ext.getCmp('queryId').setDisabled(true);
        							}
        						}
        					}
        				}
		            },{
		            	id:'c13',
		            	xtype:'checkbox',
		            	boxLabel: '迁出',
		            	checked:true,
		            	name:'qc',
		            	inputValue : "3",  
		            	columnWidth: .1,
        				listeners:{
        					'check': function(obj,checked){
        						if(checked){
        							ywcxCount ++;
        							if(ywcxCount > 0){
        								Ext.getCmp('queryId').setDisabled(false);
        							}
        						}else{
        							ywcxCount --;
        							
        							if(ywcxCount == 0){
        								Ext.getCmp('queryId').setDisabled(true);
        							}
        						}
        					}
        				}
		            },{
		            	id:'c14',
		            	xtype:'checkbox',
		            	boxLabel: '死亡',
		            	checked:true,
		            	name:'sw',
		            	inputValue : "3",  
		            	columnWidth: .1,
        				listeners:{
        					'check': function(obj,checked){
        						if(checked){
        							ywcxCount ++;
        							if(ywcxCount > 0){
        								Ext.getCmp('queryId').setDisabled(false);
        							}
        						}else{
        							ywcxCount --;
        							
        							if(ywcxCount == 0){
        								Ext.getCmp('queryId').setDisabled(true);
        							}
        						}
        					}
        				}
		            },{
		            	id:'c15',
		            	xtype:'checkbox',
		            	boxLabel: '户籍补录',
		            	checked:true,
		            	name:'hjbl',
		            	inputValue : "3",  
		            	columnWidth: .1,
        				listeners:{
        					'check': function(obj,checked){
        						if(checked){
        							ywcxCount ++;
        							if(ywcxCount > 0){
        								Ext.getCmp('queryId').setDisabled(false);
        							}
        						}else{
        							ywcxCount --;
        							
        							if(ywcxCount == 0){
        								Ext.getCmp('queryId').setDisabled(true);
        							}
        						}
        					}
        				}
		            },{
		            	id:'c16',
		            	xtype:'checkbox',
		            	boxLabel: '户籍删除',
		            	checked:true,
		            	name:'hjsc',
		            	inputValue : "3",  
		            	columnWidth: .1,
        				listeners:{
        					'check': function(obj,checked){
        						if(checked){
        							ywcxCount ++;
        							if(ywcxCount > 0){
        								Ext.getCmp('queryId').setDisabled(false);
        							}
        						}else{
        							ywcxCount --;
        							
        							if(ywcxCount == 0){
        								Ext.getCmp('queryId').setDisabled(true);
        							}
        						}
        					}
        				}
		            },{
		            	id:'c17',
		            	xtype:'checkbox',
		            	boxLabel: '户别变更',
		            	checked:true,
		            	name:'hbbg',
		            	inputValue : "3",  
		            	columnWidth: .1,
        				listeners:{
        					'check': function(obj,checked){
        						if(checked){
        							ywcxCount ++;
        							if(ywcxCount > 0){
        								Ext.getCmp('queryId').setDisabled(false);
        							}
        						}else{
        							ywcxCount --;
        							
        							if(ywcxCount == 0){
        								Ext.getCmp('queryId').setDisabled(true);
        							}
        						}
        					}
        				}
		            },{
		            	id:'c18',
		            	xtype:'checkbox',
		            	boxLabel: '其他业务',
		            	checked:true,
		            	name:'qtyw',
		            	inputValue : "3",  
		            	columnWidth: .1,
        				listeners:{
        					'check': function(obj,checked){
        						if(checked){
        							ywcxCount ++;
        							if(ywcxCount > 0){
        								Ext.getCmp('queryId').setDisabled(false);
        							}
        						}else{
        							ywcxCount --;
        							
        							if(ywcxCount == 0){
        								Ext.getCmp('queryId').setDisabled(true);
        							}
        						}
        					}
        				}
		            }]
				},{
	            	id:'fwId',
	            	title: '选择查询的时间范围',
//					columnWidth: 3,
		            xtype: 'fieldset',
		            autoHeight: true,
		            layout:'column',
//		            defaults:{
//		            	xtype:'radio',
//		            	columnWidth: .3,
//		            	fieldLabel: '',
//		            	name: 'sjfw'
//		            },
		            items: [{
		            	id:'r11',
		            	boxLabel: '不限',
		            	name:'fw',
		            	xtype:'radio',
		            	inputValue : "1",
		            	columnWidth: .1
		            },{
		            	id:'r12',
		            	boxLabel: '当日',
		            	name:'fw',
		            	xtype:'radio',
		            	columnWidth: .1,
		            	inputValue : "2",
		            	checked:true
		            },{
		            	id:'r13',
		            	boxLabel: '当月',
		            	name:'fw',
		            	xtype:'radio',
		            	columnWidth: .1,
		            	inputValue : "3"
		            },{
		            	id:'r14',
		            	boxLabel: '自定义范围',
		            	name:'fw',
		            	xtype:'radio',
		            	columnWidth: .1,
		            	inputValue : "3"
		            },{
		            	border:false,
						frame: false,
						columnWidth: 0.03,
		            	html:'从'
		            },{
		            	columnWidth:.1,
						xtype:'datefield',
						format:'Ymd',
						anchor:'100%',
						id:'zdy_start',
						name:'zdy_start',
						fieldLabel:''
					},{
						bodyStyle:'padding:0 0 0 10',
						border:false,
						frame: false,
						columnWidth: 0.03,
		            	html:'至'
		            },{
			           	columnWidth:.1,
						xtype:'datefield',
						format:'Ymd',
						anchor:'100%',
						id:'zdy_end',
						name:'zdy_end',
						fieldLabel:''
					}]
				},{
					xtype: 'fieldset',
					autoHeight: true,
			        layout:'column',
					items:[{
		            	title: '选择查询的数据范围',
		            	width:'30%',
			            xtype: 'fieldset',
			            autoHeight: true,
			            layout:'column',
			            defaults:{
			            	columnWidth: .2,
			            	fieldLabel: '',
			            	name: 'shujufw'
			            },
			            items: [{
			            	id:'s11',
			            	xtype:'checkbox',
			            	boxLabel: '非业务冲销的四变',
			            	checked:true,
			            	name:'qr',
			            	inputValue : "1", 
			            	columnWidth: .5,
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
			            	id:'s12',
			            	xtype:'checkbox',
			            	boxLabel: '业务冲销的四变',
			            	name:'cs',
			            	inputValue : "2", 
			            	columnWidth: .5,
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
			            }]
					},{
		            	border:false,
						frame: false,
						width:'1%'
		            },{
		            	title: '选择查询的变动范围',
		            	width:'69%',
			            xtype: 'fieldset',
			            autoHeight: true,
			            layout:'column',
			            defaults:{
			            	columnWidth: .16,
			            	fieldLabel: '',
			            	name: 'bdfw'
			            },
			            items: [{
			            	id:'t11',
			            	xtype:'checkbox',
			            	boxLabel: '派出所内',
			            	checked:true,
			            	name:'qr',
			            	inputValue : "1", 
			            	columnWidth: .16,
	        				listeners:{
	        					'check': function(obj,checked){}
	        				}
			            },{
			            	id:'t12',
			            	xtype:'checkbox',
			            	boxLabel: '市内',
			            	checked:true,
			            	name:'cs',
			            	inputValue : "2", 
			            	columnWidth: .16,
	        				listeners:{
	        					'check': function(obj,checked){}
	        				}
			            },{
			            	id:'t13',
			            	xtype:'checkbox',
			            	boxLabel: '省内',
			            	checked:true,
			            	name:'cs',
			            	inputValue : "2", 
			            	columnWidth: .16,
	        				listeners:{
	        					'check': function(obj,checked){}
	        				}
			            },{
			            	id:'t14',
			            	xtype:'checkbox',
			            	boxLabel: '国内',
			            	checked:true,
			            	name:'cs',
			            	inputValue : "2", 
			            	columnWidth: .16,
	        				listeners:{
	        					'check': function(obj,checked){}
	        				}
			            },{
			            	id:'t15',
			            	xtype:'checkbox',
			            	boxLabel: '港澳台',
			            	checked:true,
			            	name:'cs',
			            	inputValue : "2", 
			            	columnWidth: .16,
	        				listeners:{
	        					'check': function(obj,checked){}
	        				}
			            },{
			            	id:'t16',
			            	xtype:'checkbox',
			            	boxLabel: '国外其他',
			            	checked:true,
			            	name:'cs',
			            	inputValue : "2", 
			            	columnWidth: .16,
	        				listeners:{
	        					'check': function(obj,checked){}
	        				}
			            }]
					}]
				},{
					border:false,
					frame: false,
		        	region:'south',
		        	height: 40,
		        	width:'80%',
		        	bodyStyle: 'padding:5px',
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
			    		    	id:'fzId',
			        			text:'复杂条件',
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
				    	    	form:form20021,
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
			    			id:'t17',
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
						border:false,
						frame: false,
		    		    items:[new Ext.Button({
		    		    	id:'queryId',
		        			text:'查询',
		        			minWidth:60,
		        			handler:function(){
		        				var data = form20021.getForm().getValues();
		        				
								if(Gnt.util.isEmpty(data)){
									showInfo("至少输入一个查询条件！");
									return;
								}
								Ext.Msg.wait("正在执行查询，请稍后...");
								var ary = p1_1.xszdGrid.store.data.items;
								var pxary = p1_1.pxzdGrid.store.data.items;//length 长度;
								if(p1_1.qybz){
									bdqkGrid.reconfigure(
											bdqkGrid.store,new Ext.grid.ColumnModel(Gnt.ZdyColumn(ary)));
									bdqkGrid.pxary = pxary;
								};
								bdqkGrid.zdyValueKey = Gnt.zdyValueKey(ary);
								var header=[];
								var shuxing = [];
								var zdyValueKey = Gnt.zdyValueKey(ary);
								bdqkGrid.type = 'bdqkcx';
		        				var store = bdqkGrid.store;
		        				store.removeAll();
		        				store.baseParams = data;
		        				var config=bdqkGrid.colModel.config;
								Ext.each(config,function(r){
									header.push(r.header);
									shuxing.push(r.dataIndex);
								});
								store.baseParams.header = encodeURI(header);
								store.baseParams.shuxing = encodeURI(shuxing);
								store.baseParams.zdyValueKey = Ext.encode(zdyValueKey);
								store.baseParams.daochuFlag = 'bdqkcx';
		        				applyParam(store);
		        				store.load({params:{start:0, limit:20}});
		        				Ext.each(pxary, function(pxObj){
		        					store.sort(pxObj.data.fieldname, 'ASC');
		    					});
		        				store.on("load",function(store) {  
		        					if(store.data.length > 0){
		        						curIndex = 0;
		        						bdqkGrid.fireEvent("rowclick",bdqkGrid,curIndex);
		        						bdqkGrid.getSelectionModel().selectRange(curIndex,curIndex);
		        					}
		        				},bdqkGrid);
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
						
					}
			    ]}
	    	]
	        }
		]
	});
	
	var bdqkGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rybdid',
		pzlb: '20021',
		url:'cx/hjywxx/bdqkcx/getBdqkxx.json',
		region:'center',
		height:540,
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
				selectRyid = selRes.data.ryid;
				var ywnr = selRes.data.ywnr;
				if(ywnr=='104'||ywnr=='106'){
					Ext.getCmp("qyz1").enable();
					Ext.getCmp("qyz2").enable();
				}else{
					Ext.getCmp("qyz1").disable();
					Ext.getCmp("qyz2").disable();
				}
				
			},
			rowdblclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				
				selectRynbid = selRes.data.rynbid;
				selectHhnbid = selRes.data.hhnbid;
				selectHjywid = selRes.data.hjywid;
				selectRyid = selRes.data.ryid;
				
				var pkvalue = selRes.data[bdqkForm.bindStore.pkname];
				var re = bdqkForm.bindStore.getById(pkvalue);
				if(re){
					bdqkForm.getForm().loadRecord(re);
					Gnt.toFormReadyonly(bdqkForm);
				}
    			Ext.getCmp('card').getLayout().setActiveItem(2);
    			
    			p3.doLayout();
    			
			}
		}
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
	
	var zhcx_dialog = new Gnt.ux.ZhcxDialog({
		pzlb : form20021.pzlb,
		grid:bdqkGrid,
		applyParam:function(){
			applyParam(this.grid.store);
		},
		callback: function(where){
			var ary = p1_1.xszdGrid.store.data.items;
			var pxary = p1_1.pxzdGrid.store.data.items;//length 长度;
			if(p1_1.qybz){
				bdqkGrid.reconfigure(
						bdqkGrid.store,new Ext.grid.ColumnModel(Gnt.ZdyColumn(ary)));
				bdqkGrid.pxary = pxary;
			};
			bdqkGrid.zdyValueKey = Gnt.zdyValueKey(ary);
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
			grid.type = 'bdqkcx';
			Ext.each(ary,function(r){
				header.push(r.data.displayname);
				shuxing.push(r.data.fieldname);
			});
			data.header = encodeURI(header);
			data.shuxing = encodeURI(shuxing);
			data.zdyValueKey = Ext.encode(zdyValueKey);
			data.daochuFlag = 'bdqkcx';
			
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
		items:[bdqkGrid],
			buttons:[
				new Ext.Button({
					text:'迁移证',
					id:'qyz1',
					minWidth:80,
					handler:function(){
						if(selRes){
							qyzPrint(selRes.data.hjywid);
						}
					}
				}),
				new Ext.Button({
					text:'详细变动',
					minWidth:80,
					handler:function(){
						if(selRes){
							var url = null;
							var name = "";
							var pageId = "";
							var ywnr = selRes.data.ywnr;
							if(ywnr=="101"){//出生登记
								url = basePath + "cx/hjywxx/csdjcx?jumpToCsdj="+"1"+"&ryid="+selRes.data.ryid;
								name = "出生登记信息查询";
								pageId = 'csdjMain';
							}else if(ywnr=="102"){//死亡注销
								url = basePath + "cx/hjywxx/swzxcx?jumpToSwzx="+"1"+"&ryid="+selRes.data.ryid;
								name = "死亡注销信息查询";
								pageId = 'swzxMain';
							}else if(ywnr=="103"||ywnr=="104"){//住址变动
								url = basePath + "cx/hjywxx/zzbdcx?jumpToZzbd="+"1"+"&ryid="+selRes.data.ryid;
								name = "住址变动信息查询";
								pageId = 'zzbdMain';
							}else if(ywnr=="105"){//迁入登记
								url = basePath + "cx/hjywxx/qrdjcx?jumpToQrdj="+"1"+"&ryid="+selRes.data.ryid;
								name = "迁入登记信息查询";
								pageId = 'qrdjMain';
							}else if(ywnr=="106"){//迁出注销
								url = basePath + "cx/hjywxx/qczxcx?jumpToQczx="+"1"+"&ryid="+selRes.data.ryid;
								name = "迁出注销信息查询";
								pageId = 'qcdjMain';
							}else if(ywnr=="107"){//变更更正
								url = basePath + "cx/hjywxx/bggzcx?jumpToBggz="+"1"+"&ryid="+selRes.data.ryid;
								name = "变更更正信息查询";
								pageId = 'bggzMain';
							}else if(ywnr=="108"){//户籍补录
								url = basePath + "cx/hjywxx/hjblcx?jumpToHjbl="+"1"+"&ryid="+selRes.data.ryid;
								name = "户籍补录信息查询";
								pageId = 'hjblMain';
							}else if(ywnr=="109"){//户籍删除
								url = basePath + "cx/hjywxx/hjsccx?jumpToHjsc="+"1"+"&ryid="+selRes.data.ryid;
								name = "户籍删除信息查询";
								pageId = 'hjscMain';
							}else if(ywnr=="110"){//户别变更
								url = basePath + "cx/hjywxx/hbbgcx?jumpToHbbg="+"1"+"&ryid="+selRes.data.ryid;
								name = "户别变更信息查询";
								pageId = 'hbbgMain';
							}else if(ywnr=="400"){
								alert("不能显示相应的详细变动信息！");
								return;
							}else{
								alert("不能显示相应的详细变动信息！");
								return;
							}
	        				//var url = basePath + "cx/hjywxx/zzbdcx?jumpToZzbd="+"1"+"&ryid="+selRes.data.ryid;			      
	        				if(parent && parent.openWorkSpace&&url){
	        					parent.openWorkSpace(url,  name, pageId);
	        				}else{
	        					window.location.href = url;
	        				}
	        			
						}else{
							alert("请先选中一条有效的数据!");
						}
					
					}
				}),
				new Ext.Button({
					text:'人信息',
					minWidth:80,
					handler:function(){
    					if(bdqkGrid.getStore().getCount() > 0){
    						if(bdqkGrid.getSelectionModel().getCount() ==1){
    							
    							rynbid = bdqkGrid.getSelectionModel().getSelections()[0].data.rynbid;
    							selectRyid = bdqkGrid.getSelectionModel().getSelections()[0].data.ryid;
        						Gnt.toFormReadyonly(newForm20000);
//    							v.doLayout();
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
//    									var pkvalue = store4.getAt(0).data[newForm20000.bindStore.pkname];
//    									var re = newForm20000.bindStore.getById(pkvalue);
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
					text:'常口信息',
					minWidth:80,
					handler:function(){
        				czr={
								ryid:selectRyid,
								hhnbid:selectHhnbid
						}
						Gnt.toRyxx(czr);
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
	var bdqkForm = new Gnt.ux.SjpzForm({
		closable: false,
		/*region:'north',*/
		height:500,
		pzlb: '20021',
		labelWidth : 120,
		cols:2,
//		bindStore:bdqkGrid.store,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:bdqkGrid,
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
			bdqkForm,{
				id:"southId",
	        	region:'south',
	        	height:40,
	            border:false,
	            frame:false,
		        layout:'table',
		        layoutConfig: {
		        	columns: 15
		        },
		        bodyStyle: 'padding:10px 0px 0px 0px ',
	            items:[ {
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			            },{
		        				xtype:'button',
			        			text:'业务信息',
			        			minWidth:60,
			        			handler:function(){
			        				var ywxxWindow = new Gnt.ux.SelectYwxx({
			        					returnUrl:'cx/hjywxx/bggzcx/getYwxx.json',
			        					callback: function(type,array,num,index){
			        						
			        					}
			        				});
			        				ywxxWindow.show();
			        				ywxxWindow.setData(selRes.data.hjywid);
			        			}
			    		},{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			            },{
	            				xtype:'button',
			        			text:'迁移证',
			        			id:'qyz2',
			        			minWidth:60,
			        			handler:function(){
			        				if(selRes){
										qyzPrint(selRes.data.hjywid);
									}
			        			}
			    		},{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			            },{
			    			xtype:'button',
			        			text:'详细变动',
			        			minWidth:60,
			        			handler:function(){

									if(selRes){
										var url = null;
										var name = "";
										var pageId = "";
										var ywnr = selRes.data.ywnr;
										if(ywnr=="101"){//出生登记
											url = basePath + "cx/hjywxx/csdjcx?jumpToCsdj="+"1"+"&ryid="+selRes.data.ryid;
											name = "出生登记信息查询";
											pageId = 'csdjMain';
										}else if(ywnr=="102"){//死亡注销
											url = basePath + "cx/hjywxx/swzxcx?jumpToSwzx="+"1"+"&ryid="+selRes.data.ryid;
											name = "死亡注销信息查询";
											pageId = 'swzxMain';
										}else if(ywnr=="103"||ywnr=="104"){//住址变动
											url = basePath + "cx/hjywxx/zzbdcx?jumpToZzbd="+"1"+"&ryid="+selRes.data.ryid;
											name = "住址变动信息查询";
											pageId = 'zzbdMain';
										}else if(ywnr=="105"){//迁入登记
											url = basePath + "cx/hjywxx/qrdjcx?jumpToQrdj="+"1"+"&ryid="+selRes.data.ryid;
											name = "迁入登记信息查询";
											pageId = 'qrdjMain';
										}else if(ywnr=="106"){//迁出注销
											url = basePath + "cx/hjywxx/qczxcx?jumpToQczx="+"1"+"&ryid="+selRes.data.ryid;
											name = "迁出注销信息查询";
											pageId = 'qcdjMain';
										}else if(ywnr=="107"){//变更更正
											url = basePath + "cx/hjywxx/bggzcx?jumpToBggz="+"1"+"&ryid="+selRes.data.ryid;
											name = "变更更正信息查询";
											pageId = 'bggzMain';
										}else if(ywnr=="108"){//户籍补录
											url = basePath + "cx/hjywxx/hjblcx?jumpToHjbl="+"1"+"&ryid="+selRes.data.ryid;
											name = "户籍补录信息查询";
											pageId = 'hjblMain';
										}else if(ywnr=="109"){//户籍删除
											url = basePath + "cx/hjywxx/hjsccx?jumpToHjsc="+"1"+"&ryid="+selRes.data.ryid;
											name = "户籍删除信息查询";
											pageId = 'hjscMain';
										}else if(ywnr=="110"){//户别变更
											url = basePath + "cx/hjywxx/hbbgcx?jumpToHbbg="+"1"+"&ryid="+selRes.data.ryid;
											name = "户别变更信息查询";
											pageId = 'hbbgMain';
										}else if(ywnr=="400"){
											alert("不能显示相应的详细变动信息！");
										}else{
											alert("不能显示相应的详细变动信息！");
										}
				        				//var url = basePath + "cx/hjywxx/zzbdcx?jumpToZzbd="+"1"+"&ryid="+selRes.data.ryid;			      
				        				if(parent && parent.openWorkSpace&&url){
				        					parent.openWorkSpace(url,  name, pageId);
				        				}else{
				        					window.location.href = url;
				        				}
				        			
									}else{
										alert("请先选中一条有效的数据!");
									}
								
								
			        			}
			    		},{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			            },/*{
			    			xtype:'button',
		        			text:'人员恢复/注销',
		        			minWidth:60,
		        			handler:function(){
		        				
		        			}
			    		},*/{
			    			xtype:'button',
		        			text:'常口信息',
		        			minWidth:60,
		        			handler:function(){
		        				czr={
										ryid:selectRyid,
										hhnbid:selectHhnbid
								}
								Gnt.toRyxx(czr);
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
							width:10
			            },{
			    			xtype:'button',
			        			text:'返回',
			        			minWidth:60,
			        			handler:function(){
			        				Ext.getCmp('card').getLayout().setActiveItem(1);
			        			}
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
		业务范围
		 */
		var qrywfw = '';
		if(Ext.getCmp('c11').getValue()){
			qrywfw = '1';
		}
		var csywfw = '';
		if(Ext.getCmp('c12').getValue()){
			csywfw = '1';
		}
		var qcywfw = '';
		if(Ext.getCmp('c13').getValue()){
			qcywfw = '1';
		}
		var swywfw = '';
		if(Ext.getCmp('c14').getValue()){
			swywfw = '1';
		}
		var hjblywfw = '';
		if(Ext.getCmp('c15').getValue()){
			hjblywfw = '1';
		}
		var hjscywfw = '';
		if(Ext.getCmp('c16').getValue()){
			hjscywfw = '1';
		}
		var hbbgywfw = '';
		if(Ext.getCmp('c17').getValue()){
			hbbgywfw = '1';
		}
		var qtywfw = '';
		if(Ext.getCmp('c18').getValue()){
			qtywfw = '1';
		}
		/**
		时间范围
		 */
		var sjfw = '';
		var zdy_start = '';
		var zdy_end ='';
		if(Ext.getCmp('r11').getValue()){
			sjfw = '1';
		}else if(Ext.getCmp('r12').getValue()){
			sjfw = '2';
		}else if(Ext.getCmp('r13').getValue()){
			sjfw = '3';
		}else if(Ext.getCmp('r14').getValue()){
			sjfw = '4';
			zdy_start = Ext.util.Format.date(Ext.getCmp('zdy_start').getValue(),'Ymd');
			zdy_end = Ext.util.Format.date(Ext.getCmp('zdy_end').getValue(),'Ymd');
		}
		/**
		数据范围
		 */
		var fywcx = '';
		if(Ext.getCmp('s11').getValue()){
			fywcx = '1';
		}
		var ywcx = '';
		if(Ext.getCmp('s12').getValue()){
			ywcx = '2';
		}
		/**
		变动范围
		 */
		var pcsnbdfw = '';
		if(Ext.getCmp('t11').getValue()){
			pcsnbdfw='1';
		}
		var shineibdfw = '';
		if(Ext.getCmp('t12').getValue()){
			shineibdfw='1';
		}
		var shengneibdfw = '';
		if(Ext.getCmp('t13').getValue()){
			shengneibdfw='1';
		}
		var guoneibdfw = '';
		if(Ext.getCmp('t14').getValue()){
			guoneibdfw='1';	
		}
		var gangaotaibdfw = '';
		if(Ext.getCmp('t15').getValue()){
			gangaotaibdfw='1';	
		}
		var guowaiqitabdfw = '';
		if(Ext.getCmp('t16').getValue()){
			guowaiqitabdfw='1';	
		}
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
		if(Ext.getCmp('t17').getValue()){
			gd = '1';
		}
		Ext.apply(store.baseParams, {ywfw1:qrywfw,ywfw2:csywfw,ywfw3:qcywfw,ywfw4:swywfw,ywfw5:hjblywfw,ywfw6:hjscywfw,ywfw7:hbbgywfw,ywfw8:qtywfw,shijianfw:sjfw,shijianfw_start:zdy_start,shijianfw_end:zdy_end,
			shujufw1:fywcx,shujufw2:ywcx,bdfw1:pcsnbdfw,bdfw2:shineibdfw,bdfw3:shengneibdfw,bdfw4:guoneibdfw,bdfw5:gangaotaibdfw,bdfw6:guowaiqitabdfw,xszs:zs,gdxx:gd});
	}
	
	function getRynbid(rynbid){
		if(rynbid != null){
			return rynbid;
		}else{
			return getQueryParam("rynbid");
		}
	}
	
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
        	items:[p1,p2,p3,p4]
        }
    });
    
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
			
			var store = bdqkGrid.store;
			
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
    			bdqkGrid.getView().getRow(girdcount).style.backgroundColor="red";
    		}else{
    			bdqkGrid.getView().getRow(girdcount).style.backgroundColor="green";
    		}
    		
    		girdcount ++ ;
    	});
    });
    Ext.getCmp('card').getLayout().setActiveItem(1);
},
scope: this  
});
	 */
	Ext.getCmp('nextId').setVisible(false);
	
});