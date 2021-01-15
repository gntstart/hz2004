
var curIndex = -1;
var cxCount = 1;
var gridId = 0;

var selectRynbid = null;
var selectHhnbid = null;
var selectSsxq = null;
var selectTzqsxName = null;
var selectTzqjwhName = null;
var selectTzhjlxName = null;
var selectTzhzrqName = null;

Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10009",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	
	var ywGrid = new Gnt.ux.JwhGrid({
		url:'yw/hjyw/qccl/queryQcclxx.json',
		listeners:{
			rowclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				
			}
		}
	});
	
	var p1 = new Ext.Panel({
		layout:'border',
		items:[
			ywGrid,{
				id:"eastId",
				region:'east',
				width:'240',
				html:'',
				bodyStyle:'padding:10px 10px 5px 10px',
				layout:'table',
				align:'center',
				border:false,
				frame:false,
				buttonAlign: 'center',
				layoutConfig: {
					columns: 1
				},
				setMore:function(jwh, jlx){
		        	var win = this.findParentByType("bzdz_window");
		        	
		        	var f1 = this.getForm().findField("ssxqmc");
		        	var f2 = this.getForm().findField("xzjdmc");
		        	var f3 = this.getForm().findField("pcsmc");
		        	
		        	f1.setValue(jwh.dwdm_bz);
		        	f2.setValue(jwh.xzjddm_name);
		        	f3.setValue(jwh.dwdm_name);
		        	
		        	win.moreData = jwh;
		        },
				items:[
					{
						title: '',
						html: '调整前省县',
						columnWidth: 1,
						border:false,
						frame:false,
						bodyStyle:'padding:0px 10px 5px 0px'
					}
					,{
						id:'tzqsxId',
		            	layout:'border',
	    	       		anchor:'100%',
						xtype:'tree_comboBox',
						searchUrl:'dict/utils/searchXzqh',
						treeUrl:'dict/utils/searchTreeXzqh',
						name:'tzqsx',
						hiddenName:'tzqsxCode',
//						maxLength:30,
						allowBlank:true,
						fieldLabel:'',
						bodyStyle:'padding:0px 10px 5px 0px',
						listeners:{
							select:function(combo, res, eOpts){
								Gnt.submit(
			    						{dm:res.json.code}, 
			    						"yw/dzgl/qhtz/queryJwhByssxq", 
			    						"居委会加载中...", 
			    						function(jsonData,params){
		    								var f1 = Ext.getCmp("tzqjwhId");
		    								var f2 = Ext.getCmp("tzhjwhId");
		    								f1.setValue("");
											f1.store.removeAll();
											f2.setValue("");
											f2.store.removeAll();
											if(jsonData.list&& jsonData.list.length>0){
												var list = new Array();
												for(var i=0;i<jsonData.list.length;i++){
													list[i] = new Array();
													list[i][0]=jsonData.list[i].code;
													list[i][1]=jsonData.list[i].name;
													list[i][2]=jsonData.list[i];
												}
												
												f1.store.loadData(list);
												f2.store.loadData(list);
												//f.setValue(list[0][0]); 
											}
			    								
			    						}
			    				);
							}
						}
	            	},{
						title: '',
						html: '调整前居委会',
						columnWidth: 1,
						border:false,
						frame:false,
						bodyStyle:'padding:10px 10px 5px 0px'
					}
	            	/**
	            		居委会需要根据省县筛选(有选择省县)
	            	 */
					,{
						id:'tzqjwhId',
		            	layout:'border',
	    	       		anchor:'100%',
	    	       		columnWidth: 1,
	    	       		xtype:'dict_combox',
						dict:'VisionType=_BLANK',
						valueField: "code",
						displayField: "name",
						hiddenName:'tzqjwh',
						name:'tzqjwh',
						maxLength:30,
						allowBlank:true,
						fieldLabel:'',
						listeners:{	
							select:function(combo, res, eOpts){
//								alert(res.date.data.pyt);
							}
						}
//						xtype:'search_combox',
//						lazyRender:false,
//						searchUrl:'dict/utils/searchXxb?visiontype=JWHXXB' ,
//						fields:["name","code","qhdm","jwhList"],
//						valueField: "code",
//						displayField: "name",
//						hiddenName:'jwhcode',
//						listeners:{
//							select:function(){
//								
//							},
//							beforequery:function(event){
//								
//								this.store.removeAll();
//								
//								if(Ext.getCmp('tzqsxId').getValue()){
//									this.store.baseParams["ssxq"] = Ext.getCmp('tzqsxId').getValue();
//								}else{
//									this.store.baseParams["ssxq"] = null;
//								}
//								this.store.baseParams["pageSize"] = 10000;
//							}
//						},
//						name:'tzqjwhId',
//						maxLength:30,
//						allowBlank:true,
//						fieldLabel:''
	            	},{
		    	    	height:10,
		    	    	border:false,
		    	    	frame:false,
		    	    	columnWidth: 1
		    	    },{
		            	id:'tzhId',
		            	title: '调整后信息',
			            xtype: 'fieldset',
			            autoHeight: true,
			            layout:'column',
			            width:'95%',
//						bodyStyle:'padding:5px 10px 5px 0px',
			            defaults:{
//			            	xtype:'radio',
			            	columnWidth: 1,
			            	fieldLabel: ''
			            },
			            items: [{
							title: '',
							html: '调整后居委会',
							columnWidth: 1,
							border:false,
							frame:false,
							bodyStyle:'padding:0px 10px 5px 0px'
						}
						,{
							id:'tzhjwhId',
			            	layout:'border',
		    	       		anchor:'100%',
		    	       		columnWidth: 1,
		    	       		xtype:'dict_combox',
							dict:'VisionType=_BLANK',
							valueField: "code",
							displayField: "name",
							hiddenName:'tzhjwh',
							name:'tzhjwh',
							maxLength:30,
							allowBlank:true,
							fieldLabel:'',
							listeners:{	
								select:function(combo, res, eOpts){
									alert(res.data.data.pyt);
									alert(Gnt.ux.Dict.getDictLable('DWXXB', res.data.data.pyt));
									Ext.getCmp("tzhPcsId").setValue(Gnt.ux.Dict.getDictLable('DWXXB', res.data.data.pyt));
									Ext.getCmp('tzhXzId').setValue(Gnt.ux.Dict.getDictLable('XZJDXXB', res.data.data.pyt));
								}
							}
//							xtype:'search_combox',
//							searchUrl:'dict/utils/searchJwhPcsXzjd',
//							fields:["mc","dm","dwdm","dwdm_name","dwdm_bz","xzjddm","xzjddm_name"],
//							valueField: "dm",
//							displayField: "mc",
//							hiddenName:'tzhjwhcode',
//							listeners:{
//								select:function(combo, res, eOpts){
//									var data = res.data;
//									
//									selectJwh = data;
//									
//									Ext.getCmp('tzhPcsId').setValue(data.dwdm_name);
//									Ext.getCmp('tzhXzId').setValue(data.xzjddm_name);
//									
//								},
//								beforequery:function(event){
//									
//									this.store.removeAll();
//									
//									if(Ext.getCmp('tzqsxId').getValue()){
//										this.store.baseParams["ssxq"] = Ext.getCmp('tzqsxId').getValue();
//									}else{
//										this.store.baseParams["ssxq"] = null;
//									}
//									this.store.baseParams["pageSize"] = 10000;
//								}
//							},
//							name:'tzhjwhmc',
//							maxLength:30,
//							allowBlank:true,
//							fieldLabel:''
		            	},{
			    	    	height:10,
			    	    	border:false,
			    	    	frame:false,
			    	    	columnWidth: 1
			    	    },{
							title: '',
							html: '调整后派出所',
							columnWidth: 1,
							border:false,
							frame:false,
							bodyStyle:'padding:0px 10px 5px 0px'
						}
						,{
							id:'tzhPcsId',
							xtype:'textfield',
							name:'tzhpcsmc',
							hiddenName:'tzhpcs',
							disabled:true
		            	},{
			    	    	height:10,
			    	    	border:false,
			    	    	frame:false,
			    	    	columnWidth: 1
			    	    },{
							title: '',
							html: '调整后乡镇',
							columnWidth: 1,
							border:false,
							frame:false,
							bodyStyle:'padding:0px 10px 5px 0px'
						}
						,{
							id:'tzhXzId',
							xtype:'textfield',
							name:'tzhxzmc',
							hiddenName:'tzhxz',
							disabled:true
		            	},{
			    	    	height:10,
			    	    	border:false,
			    	    	frame:false,
			    	    	columnWidth: 1
			    	    },{
							title: '',
							html: '调整后街路巷',
							columnWidth: 1,
							border:false,
							frame:false,
							bodyStyle:'padding:0px 10px 5px 0px'
						}
						,{
							id:'tzhjlxId',
	        	       		anchor:'100%',
							xtype:'search_combox',
							searchUrl:'dict/utils/searchJlx.json',
							fields:["mc","dm","qhdm","jwhList"],
							valueField: "dm",
							displayField: "mc",
							hiddenName:'jlx',
							listeners:{
								select:function(combo, res, eOpts ){
									
								}
							},
							name:'jlxmc',
							maxLength:40,
							allowBlank:true,
							fieldLabel:''
						},{
			    	    	height:10,
			    	    	border:false,
			    	    	frame:false,
			    	    	columnWidth: 1
			    	    },{
							title: '',
							html: '调整后责任区',
							columnWidth: 1,
							border:false,
							frame:false,
							bodyStyle:'padding:0px 10px 5px 0px'
						}
						,{
							id:'tzhzrqId',
	        	       		anchor:'100%',
							xtype:'search_combox',
							searchUrl:'dict/utils/searchJlx.json',
							fields:["mc","dm","qhdm","jwhList"],
							valueField: "dm",
							displayField: "mc",
							hiddenName:'jlx',
							listeners:{
								select:function(combo, res, eOpts ){
									
								}
							},
							name:'jlxmc',
							maxLength:40,
							allowBlank:true,
							fieldLabel:''
						}]
	            	}
		    	    ,{
		    	    	layout:'table',
//		    	    	buttonAlign: 'center',
//		    	    	align:'center',
						layoutConfig: {
				        	columns: 3
				        },
		    	    	border:false,
		    	    	frame:false,
		    	    	items: [
		    	    		new Ext.Button({
		    	    			text:'增加',
		    	    			minWidth:60,
		    	    			handler:function(){
		    	    				
		    	    				/**
		    	    					需要验证信息是否填写/选择完整
		    	    					
		    	    					客户端只验证了调整前居委会
		    	    				 */
		    	    				/*
		    	    				if(!Ext.getCmp('tzqsxId').getValue()){
		    	    					Ext.getCmp('tzqsxId').focus();
		    	    					return ;
		    	    				}
		    	    				*/
		    	    				if(!Ext.getCmp('tzqjwhId').getValue()){
		    	    					Ext.getCmp('tzqjwhId').focus();
		    	    					return ;
		    	    				}
		    	    				/*
		    	    				if(!Ext.getCmp('tzhjwhId').getValue()){
		    	    					Ext.getCmp('tzhjwhId').focus();
		    	    					return ;
		    	    				}
		    	    				if(!Ext.getCmp('tzhjlxId').getValue()){
		    	    					Ext.getCmp('tzhjlxId').focus();
		    	    					return ;
		    	    				}
		    	    				if(!Ext.getCmp('tzhzrqId').getValue()){
		    	    					Ext.getCmp('tzhzrqId').focus();
		    	    					return ;
		    	    				}
		    	    				*/
		    	    				
		    	    				gridId ++;
									var rr = new ywGrid.store.reader.recordType({id: gridId}, gridId);
									
									/**
										"id", "tzqsx", "tzqsxCode", "tzqjwh", "tzqjwhCode", "tzhjwh", "tzhjwhCode", "tzhjlx", "tzhjlxCode"
											, "tzhpcs", "tzhpcsCode" , "tzhxzjd", "tzhxzjdCode", "tzhzrq", "tzhzrqCode"
									 */
									rr.set("tzqsx", Ext.getCmp('tzqsxId').getRawValue());
									rr.set("tzqsxCode", Ext.getCmp('tzqsxId').getValue());
									rr.set("tzqjwh", Ext.getCmp('tzqjwhId').getRawValue());
									rr.set("tzqjwhCode", Ext.getCmp('tzqjwhId').getValue());
									
									rr.set("tzhjwh", Ext.getCmp('tzhjwhId').getRawValue());
									rr.set("tzhjwhCode", Ext.getCmp('tzhjwhId').getValue());
									
									rr.set("tzhjlx", Ext.getCmp('tzhjlxId').getRawValue());
									rr.set("tzhjlxCode", Ext.getCmp('tzhjlxId').getValue());
									
									rr.set("tzhpcs", Ext.getCmp('tzhPcsId').getRawValue());
									rr.set("tzhpcsCode", Ext.getCmp('tzhPcsId').getValue());
									
									rr.set("tzhxzjd", Ext.getCmp('tzhXzId').getRawValue());
									rr.set("tzhxzjdCode", Ext.getCmp('tzhXzId').getValue());
									
									rr.set("tzhzrq", Ext.getCmp('tzhzrqId').getRawValue());
									rr.set("tzhzrqCode", Ext.getCmp('tzhzrqId').getValue());
									
									ywGrid.store.add([rr]);
									
		    	    			}
		    	    		})
		    	    		,{
								frame:false,
								border:false,
								xtype:'panel',
								html:'',
								width:10
				    		}
		    	    		,new Ext.Button({
		    	    			id:'scId',
		    	    			text:'删除',
		    	    			minWidth:60,
		    	    			handler:function(){
									var r = ywGrid.getSelectionModel().getSelected();
									if(r){
										
										ywGrid.store.remove(r);
										
										/*
										var msg = "确定删除随迁人员[" + (r.data.xm?r.data.xm:"") + "]的信息吗？"
										showQuestion(msg, function(btnType){
											if(btnType=="yes"){
												ywGrid.store.remove(r);
												
												var index = ywGrid.store.getCount() - 1;
												if(index<0){
				 									index=-1;
				 									
				 									Ext.getCmp('scId').disable();
				 									
												}else{
													ywGrid.fireEvent("rowclick",ywGrid,index);
													ywGrid.getSelectionModel().selectRange(index,index);
												}
												
											}
										});
										*/
									}else{
										showInfo("请选择需要删除的记录！");
									}
								}
		    	    		})
		    	    		]
		    	    },{
		    	    	height:5,
		    	    	border:false,
		    	    	frame:false,
		    	    	columnWidth: 1
//		    	    	rowspan: 2
		    	    },{
		            	id:'c11',
		            	boxLabel: '同时调整街路巷',
		            	xtype:'checkbox',
		            	name:'tstzjlx',
		            	inputValue : "1"
		            },{
		            	id:'c12',
		            	boxLabel: '同时调整责任区',
		            	xtype:'checkbox',
		            	name:'tstzzrq',
		            	inputValue : "1"
		            },{
		            	id:'c13',
		            	boxLabel: '生成街路巷居委会对照表',
		            	xtype:'checkbox',
		            	name:'scdzb',
		            	inputValue : "1"
		            },{
		    	    	height:5,
		    	    	border:false,
		    	    	frame:false,
		    	    	columnWidth: 1
//		    	    	rowspan: 2
		    	    },new Ext.Button({
    	    			text:'保存',
    	    			minWidth:60,
    	    			handler:function(){
    	    				window.parent.parent.closeWorkSpace();
    	    			}
    	    		})
				]
			},{
				region:'south',
				title: '',
				bodyStyle:'padding:5px 0px 0px 10px',
//				bodyStyle:'margin:0 0 0 10',
				html: '<font color="red">提示：本功能可以按照居委会成批调整省县、派出所、乡镇、居委会、街路巷、责任区，可以同时调整多个居委会'+
						'<br />&emsp;&emsp;&emsp;如果不是居委会成批调整，请使用‘地址调整’页面的功能，查询要调整的地址，再进行调整</font>',
				columnWidth: 1,
				border:false,
				frame:false
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
        	items:[p1]
        }
    });
	
	function onLoad(store){
		if(store.getCount() > 0){
			
			showInfo("(" + selectSfzh + ")已被常住人员使用，不能作删除操作，身份号码分配删除业务无法完成。");
			return ;
		}else{
			
			/**
				物理删除
			 */
			var subdata = {
				fpid: ywGrid.getSelectionModel().getSelected().data.fpid
			};
			
			if(flag){
				delXx(subdata);
			}
			
			flag = false;
			return ;
		}
	}
	
	/**
		删除所选记录
	 */
	function delXx(subdata){
		Gnt.submit(
				subdata, 
				"yw/hjyw/sfhfpxxbl/processSfhmfpscyw", 
				"正在处理身份号分配信息补录信息，请稍后...", 
				function(jsonData,params){
					
					showInfo("身份号分配信息补录删除成功！",function(){
						/**
							删除选中记录
						 */
						ywGrid.store.remove(ywGrid.getSelectionModel().getSelected());
					});
					
					return ;
				}
			);
			
	}
    
	function applyParam(store){
		
		/**
			查询类别
		 */
		var lb = '';
		if(Ext.getCmp('r11').getValue()){
			lb = '1';
		}else if(Ext.getCmp('r12').getValue()){
			lb = '2';
		}else if(Ext.getCmp('r13').getValue()){
			lb = '3';
		}
		
		var ycl = '';
		if(Ext.getCmp('c11').getValue()){
			ycl = '1';
		}
		
		var wcl = '';
		if(Ext.getCmp('c21').getValue()){
			wcl = '1';
		}
		
		var clbz = '';
		if(ycl || wcl){
			clbz = '1';
		}
		
		Ext.apply(store.baseParams, {czlx:lb,clbz:clbz,ycl:ycl,wcl:wcl});
		
	}
	
	/**
		按下回车键执行查询操作
		
	var key1 = new Ext.KeyMap(document, {
		key:Ext.EventObject.ENTER,
		fn:function() {
			var data = form20000.getForm().getValues();
			
			if(Gnt.util.isEmpty(data)){
				showInfo("至少输入一个查询条件！");
				return;
			}
			
			var store = rkjbxxGrid.store;
			
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
    			rkjbxxGrid.getView().getRow(girdcount).style.backgroundColor="red";
    		}else{
    			rkjbxxGrid.getView().getRow(girdcount).style.backgroundColor="green";
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