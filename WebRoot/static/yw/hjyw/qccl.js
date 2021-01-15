
var curIndex = -1;
var cxCount = 1;
var rynbid = null;
var selectRyid = null;
var selectRynbid = null;
var selectHhnbid = null;
var selRes = null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10009",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	
	var cxForm = new Gnt.ux.SjpzForm({
		pzlb: '10009',
		title:'查询条件',
		region:'center',
		cols:2,
		formType:'query',
		labelWidth :  140/*,
//		height:180,
		buttons:[
			new Ext.Button({
				id:'queryId',
    			text:'查询',
    			minWidth:60,
    			handler:function(){
    				var data = cxForm.getForm().getValues();
    				
					if(Gnt.util.isEmpty(data)){
						showInfo("至少输入一个查询条件！");
						return;
					}
    				
    				var sfzstore = ywGrid.store;
    				
    				sfzstore.removeAll();
    				
    				sfzstore.baseParams = data;
    				
    				applyParam(sfzstore);
    				
    				Ext.apply(sfzstore.baseParams, {
						config_key:'queryPoV_HJ_QCCLXXB'
    				});
    				
    				sfzstore.load();
    				
    				sfzstore.on("load",function(store) {
    					if(store.getCount() > 0){
//    						Ext.getCmp("fpscId").setDisabled(false);
    						
    						*//**
    							有数据,按钮启用
    						 *//*
    						Ext.getCmp('cbdyId').setDisabled(false);
    						Ext.getCmp('cbcxId').setDisabled(false);
    						Ext.getCmp('bgcxId').setDisabled(false);
    						Ext.getCmp('sjdcId').setDisabled(false);
    						
    						Ext.getCmp('qcdyId').setDisabled(false);
    						Ext.getCmp('cgdyId').setDisabled(false);
    						Ext.getCmp('dyAllId').setDisabled(false);
    						Ext.getCmp('clId').setDisabled(false);
    						Ext.getCmp('clAllId').setDisabled(false);
    						
    						ywGrid.fireEvent("rowclick",ywGrid,0);
    						ywGrid.getSelectionModel().selectRange(0,0);
    						
    					}else{
//    						Ext.getCmp("fpscId").setDisabled(true);
    					}
    				});
    				
    				
    				
    			}
			}),
			new Ext.Button({
				id:'zhId',
    			text:'组合查询',
    			minWidth:60,
    			disabled:true,
    			handler:function(){
    				
    			}
			})
		]*/
	});
	
	var ywGrid = new Gnt.ux.SjpzGrid({
		pkname: 'qcclid',
		pzlb: '10009',
		title: '查询结果',
		region:'center',
		url: 'yw/hjyw/qccl/queryQcclxx.json',
		listeners:{
			rowclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				/**
					是否要按照处理标识设置人员内部ID?
					
				 */
//				alert(selRes.data.qhrynbid);
				
				selectRynbid = selRes.data.qhrynbid;
				selectRyid = selRes.data.ryid;
				
			}
		}
	});
	var p1_1 = new Gnt.ux.ZdycxPanel({
		pzlb:ywGrid.pzlb,
		region:'center',
		layout:'border',
		visible:false,
		border:false,
		frame: false,
		height:400
	});
	var sbdy_window=new Gnt.ux.sbdywin({
		callback: function(){
		
		}
	});
	
	var p1 = new Ext.Panel({
		layout:'border',
		items:[
			{
				id:"northId",
				border:false,
				frame: false,
				region:'north',
				layout:'border',
	        	height: 235,
				items:[{
					region:'west',
					width:180,
					layout:'table',
					align:'center',
					bodyStyle:'padding: 10px 10px 10px 10px;',
					layoutConfig: {
						columns: 1
					},
					defaults: {
						width:'100%',
						margins:'15'
					},
					items: [{
						id:'lbId',
						title: '查询类别',
//						columnWidth: 3,
						xtype: 'fieldset',
						autoHeight: true,
						layout:'column',
						defaults:{
							columnWidth: 1,
							fieldLabel: '',
							name: 'lb'
						},
						items: [{
							id:'r11',
							xtype:'radio',
							boxLabel: '住址变动查询',
							inputValue : "1",
							checked:true
						},{
			    	    	height:10,
			    	    	border:false,
			    	    	frame:false
			    	    },{
							id:'r12',
							xtype:'radio',
							boxLabel: '变更更正查询',
							inputValue : "2"
						},{
			    	    	height:10,
			    	    	border:false,
			    	    	frame:false
			    	    },{
							id:'r13',
							xtype:'radio',
							boxLabel: '业务恢复查询',
							inputValue : "3"
						},{
			    	    	height:10,
			    	    	border:false,
			    	    	frame:false
			    	    },{
							id:'r14',
							xtype:'radio',
							boxLabel: '省内迁出查询',
							inputValue : "4"
						}]
					},{
						id:'c11',
						xtype:'checkbox',
						boxLabel: '查询已处理事项',
						name:'yclsx',
						inputValue : "1",  
						listeners:{
							'check': function(obj,checked){
								if(checked){
									
								}else{
									
								}
							}
						}
					},{
						id:'c21',
						xtype:'checkbox',
						boxLabel: '查询未处理事项',
						name:'wclsx',
						inputValue : "1",
						checked:true,
						listeners:{
							'check': function(obj,checked){
								if(checked){
									
								}else{
									
								}
							}
						}
					}]
				},{
					border:false,
					frame: false,
					region:'center',
					items:[
						cxForm,{
							region:'south',
							border:false,
							frame: false,
							layout:'table',
							height: 40,
				        	bodyStyle: 'padding:5px',
							layoutConfig: {
					        	columns: 6
					        },
							items:[
								{
									frame:false,
									border:false,
									xtype:'panel',
									html:'',
									width:10
					    		},new Ext.Button({
									id:'queryId',
					    			text:'查询',
					    			minWidth:60,
					    			handler:function(){
					    				var data = cxForm.getForm().getValues();
					    				data.log_code = "F1026";
					    				/*
										if(Gnt.util.isEmpty(data)){
											showInfo("至少输入一个查询条件！");
											return;
										}
					    				*/
					    				var header=[];
										var shuxing = [];
										
										
					    				var sfzstore = ywGrid.store;
					    				var zdyValueKey = Gnt.zdyValueKey(p1_1.xszdGrid.store.data.items);
					    				sfzstore.removeAll();
					    				sfzstore.baseParams = data;
					    				var config=ywGrid.colModel.config;
										Ext.each(config,function(r){
											header.push(r.header);
											shuxing.push(r.dataIndex);
										});
										sfzstore.baseParams.header = encodeURI(header);
										sfzstore.baseParams.shuxing = encodeURI(shuxing);
										sfzstore.baseParams.zdyValueKey = Ext.encode(zdyValueKey);
										sfzstore.baseParams.daochuFlag = 'queryQcclxx';
					    				applyParam(sfzstore);
					    				
					    				Ext.apply(sfzstore.baseParams, {
											config_key:'queryPoV_HJ_QCCLXXB'
					    				});
					    				
					    				sfzstore.load();
					    				
					    				sfzstore.on("load",function(store) {
					    					if(store.getCount() > 0){
//					    						Ext.getCmp("fpscId").setDisabled(false);
					    						
					    						/**
					    							有数据,按钮启用
					    						 */
					    						Ext.getCmp('cbdyId').setDisabled(false);
					    						Ext.getCmp('cbcxId').setDisabled(false);
					    						Ext.getCmp('bgcxId').setDisabled(false);
					    						Ext.getCmp('sjdcId').setDisabled(false);
					    						
					    						Ext.getCmp('qcdyId').setDisabled(false);
					    						Ext.getCmp('cgdyId').setDisabled(false);
					    						Ext.getCmp('dyAllId').setDisabled(false);
					    						Ext.getCmp('clId').setDisabled(false);
					    						Ext.getCmp('clAllId').setDisabled(false);
					    						
					    						ywGrid.fireEvent("rowclick",ywGrid,0);
					    						ywGrid.getSelectionModel().selectRange(0,0);
					    						
					    					}else{
//					    						Ext.getCmp("fpscId").setDisabled(true);
					    						Ext.getCmp('cbdyId').setDisabled(true);
					    						Ext.getCmp('cbcxId').setDisabled(true);
					    						Ext.getCmp('bgcxId').setDisabled(true);
					    						Ext.getCmp('sjdcId').setDisabled(true);
					    						
					    						Ext.getCmp('qcdyId').setDisabled(true);
					    						Ext.getCmp('cgdyId').setDisabled(true);
					    						Ext.getCmp('dyAllId').setDisabled(true);
					    						Ext.getCmp('clId').setDisabled(true);
					    						Ext.getCmp('clAllId').setDisabled(true);
					    					}
					    				});
					    				
					    				
					    				
					    			}
								}),{
									frame:false,
									border:false,
									xtype:'panel',
									html:'',
									width:10
					    		},
								{
					    	    	xtype: 'DkButton',
					    	    	form:cxForm,
					    	    	callback: function(){
					    	    		//Ext.getCmp("queryId").handler();
					    	    	}
					    	    },{
									frame:false,
									border:false,
									xtype:'panel',
									html:'',
									width:10
					    		},
								new Ext.Button({
									id:'zhId',
					    			text:'组合查询',
					    			minWidth:60,
					    			disabled:true,
					    			handler:function(){
					    				
					    			}
								}),{
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
							]
						}
					]
				}]
			},ywGrid,{
				id:"eastId",
				region:'east',
				width:'80',
				html:'',
				bodyStyle:'padding:10px 5px 10px 5px',
				layout:'table',
				align:'center',
				border:false,
				frame:false,
				buttonAlign: 'center',
				layoutConfig: {
					columns: 1
				},
				items:[
					new Ext.Button({
						id: 'cbdyId',
						text:'常表打印',
						minWidth:60,
						disabled:true,
						handler:function(){
							printRk(3,null,selectRynbid,[true,true,true,true,true],[true,true,false,false,false],[true,true,true,true,true]);
						}
					}),{
						height:10,
						border:false,
						frame:false
					},
					new Ext.Button({
						id: 'cbcxId',
						text:'常口查询',
						minWidth:60,
						disabled:true,
						handler:function(){
							czr={
									ryid:selectRyid,
									rynbid:null,
									hhnbid:selectHhnbid
								}
							Gnt.toRyxx(czr);
						}
					}),{
						height:10,
						border:false,
						frame:false
					},
					new Ext.Button({
						id: 'bgcxId',
						text:'变更查询',
						minWidth:60,
						disabled:true,
						handler:function(){
							
							var url = basePath + "cx/hjywxx/bggzcx?gmsfhm=" 
										+ ywGrid.getSelectionModel().getSelections()[0].data.gmsfhm + "&ai=2";
							
							if(parent && parent.openWorkSpace){
	    						parent.openWorkSpace(url,  "变更更正信息查询", "bggzcx");
	    					}else{
	    						window.location.href = url;
	    					}
							/**
								调用的是父类的标签页
							 */
							/*var tabs = parent.Ext.getCmp("tabs");
							var p = null;
						   	 //利用iframe显示页面
						   	 p = new parent.Ext.ux.IFrameComponent({
						   	 		xtype:'panel',
						   	 		id:"ckxxcx",
						   	 		url:"cx/hjywxx/bggzcx?gmsfhm=" + ywGrid.getSelectionModel().getSelections()[0].data.gmsfhm + "&ai=2",
						   	 		frame:false,
						   	 		iconCls:'otherfile',
							   		title: "变更更正信息查询"
							});
						   	//添加分页，并且设置为活动分页
						    tabs.add(p);
						    
						    tabs.setActiveTab("ckxxcx");
						    */
						}
					}),{
						height:10,
						border:false,
						frame:false
					},
					new Ext.Button({
						id: 'sjdcId',
						text:'数据导出',
						minWidth:60,
						disabled:true,
						handler:function(){
							if(ywGrid.store.data.items){
								urlLink = "yw/common/downExcelZip?type=queryQcclxx";
								Ext.Msg.wait("正在查询导出信息，请稍后...");
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
//								var pzlbData = Gnt.ux.Dict.getSjpzData(ywGrid.pzlb);
//								var dictMap = new Map();
//								Ext.each(pzlbData,function(r){
//									if(r.dsname){
//										dictMap.set(r.fieldname,r.dsname);
//									}
//								});
//								var param = cxForm.getForm().getValues();
//								applyParamDc(param);
//			    				param.config_key='queryPoV_HJ_QCCLXXB';
//			    				Gnt.submit(
//			    						param, 
//			    						"yw/hjyw/qccl/queryQcclxxDaochu.json", 
//			    						"导出数据准备中，请稍后...", 
//			    						function(jsonData,params){
//			    							var dcdata = jsonData.list;
//			    							sbdy_window.show();
//			    							sbdy_window.setSelRes(dcdata,"qccl",null,null,dictMap);
//			    						}
//			    				);
							}else{
								alert("没有符合条件的数据！");
							}
							
//							var data=ywGrid.store.data.items;
//							sbdy_window.show();
//							sbdy_window.setSelRes(data,"qccl");
						}
					})
				]
			}
		],
		buttons:[
			new Ext.Button({
				id:'qcdyId',
				text:'常表迁出打印',
				minWidth:60,
				disabled:true,
				handler:function(){
					if(selRes){
						var arrayTemp=[];
	               	 	var o={};
						o.directTable="cbqc";
						o.hjywid = selRes.data.hjywid;
						o.gmsfhm =selRes.data.gmsfhm;
						o.qcclid = selRes.data.qcclid;
						arrayTemp.push(o);
						printfunction(0,arrayTemp);
						ywGrid.store.reload(); 
					}else{
						alert("请至少选择一条数据！");
					}
				}
			}),
			new Ext.Button({
				id:'cgdyId',
				text:'存根打印',
				minWidth:60,
				disabled:true,
				handler:function(){
					if(selRes){
						var arrayTemp=[];
	               	 	var o={};
						o.directTable="cg";
						o.hjywid = selRes.data.hjywid;
						o.gmsfhm =selRes.data.gmsfhm;
						o.qcclid = selRes.data.qcclid;
						arrayTemp.push(o);
						printfunction(0,arrayTemp);
						ywGrid.store.reload(); 
					}else{
						alert("请至少选择一条数据！");
					}
				}
			}),
			new Ext.Button({
				id:'dyAllId',
				text:'全部打印',
				minWidth:60,
				disabled:true,
				handler:function(){
					var arrayTemp=[];
					var qcclParams = {
					};
					Ext.apply(qcclParams, ywGrid.store.baseParams);
					Ext.apply(qcclParams, {pageSize:ywGrid.store.totalLength});
					Gnt.submit(
							qcclParams, 
							ywGrid.store.url, 
							"正在全部处理信息，请稍后...", 
							function(jsonData,params){
        						var list= jsonData.list;
        						for(var index=0;index<list.length;index++){
        							var data = list[index];
        							data.clbz = 1;
        							var o={};
        							o.directTable="cg";
        							o.hjywid = data.hjywid;
        							o.gmsfhm =	data.gmsfhm;  
        							o.qcclid = data.qcclid;
        							arrayTemp.push(o);
        						}
        						printfunction(0,arrayTemp);
        		               	ywGrid.store.reload(); 
							}
					);
					
//					var arrayTemp=[];
//					ywGrid.store.each(function(record) {
//						var o={};
//						o.directTable="cg";
//						o.hjywid = record.get('hjywid');
//						o.gmsfhm =	record.get('gmsfhm');
//						o.qcclid = record.get('qcclid');
//						arrayTemp.push(o);
//	               	}); 
//	               	printfunction(0,arrayTemp);
//	               	ywGrid.store.reload(); 
				}
			}),
			new Ext.Button({
				id:'clId',
				text:'处理',
				minWidth:60,
				disabled:true,
				handler:function(){
					var data = ywGrid.getSelectionModel().getSelections()[0].data;
					if(data.clbz==1){
						alert("该条数据已处理！")
						return;
					}
					var subdata = {
						voQcclxx: new Array()
					};
					ywGrid.store.getAt(curIndex).set('clbz',1);
					subdata.voQcclxx.push(data);
					subdata.voQcclxx = Ext.encode(subdata.voQcclxx);
					log_code = "F1011";
					Gnt.submit(
						subdata, 
						"yw/hjyw/qccl/processQcclyw", 
						"正在迁出处理操作，请稍后...", 
						function(jsonData,params){
							showInfo("处理成功！",function(){
								var re = ywGrid.getSelectionModel().getSelections()[0]
								re.data.clbz = 1;
								
							});
						}
					);
					
				}
			}),
			new Ext.Button({
				id:'clAllId',
				text:'全部处理',
				minWidth:60,
				disabled:true,
				handler:function(){
					var subdata = {
							voQcclxx: new Array()
					};
//						
//						for(var index=0;index<ywGrid.store.getCount();index++){
//							var data = ywGrid.store.getAt(index).data;
//							if(data.clbz == 1){
//								alert(data.xm+"的数据已处理，自动跳转下一条！")
//								continue;
//							}else if(data.clbz == 0){
//								ywGrid.store.getAt(index).set('clbz',1);
//							}
//							//data.clbz = 1;
//							subdata.voQcclxx.push(data);
//						}
						var qcclParams = {
						};
						Ext.apply(qcclParams, ywGrid.store.baseParams);
						Ext.apply(qcclParams, {pageSize:ywGrid.store.totalLength});
						Gnt.submit(
								qcclParams, 
								ywGrid.store.url, 
								"正在全部处理信息，请稍后...", 
								function(jsonData,params){
	        						var list= jsonData.list;
	        						for(var index=0;index<list.length;index++){
	        							var data = list[index];
//	        							if(data.clbz == 1){
//	        								alert(data.xm+"的数据已处理，自动跳转下一条！")
//	        								continue;
//	        							}else if(data.clbz == 0){
//	        								ywGrid.store.getAt(index).set('clbz',1);
//	        							}
	        							data.clbz = 1;
	        							subdata.voQcclxx.push(data);
	        						}
        							subdata.voQcclxx = Ext.encode(subdata.voQcclxx);
        							log_code = "F1011";
        							Gnt.submit(
        								subdata, 
        								"yw/hjyw/qccl/processQcclyw", 
        								"正在全部迁出处理操作，请稍后...", 
        								function(jsonData,params){
        									showInfo("全部处理成功！",function(){
        										ywGrid.store.reload(); 
        									});
        								}
        							);
								}
						);
						
						
					}
			}),
			new Ext.Button({
				id:'closeId',
				text:'关闭',
				minWidth:60,
				handler:function(){
					window.parent.closeWorkSpace();
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
		}else if(Ext.getCmp('r14').getValue()){
			lb = '4';
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
function applyParamDc(params){
		
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
		}else if(Ext.getCmp('r14').getValue()){
			lb = '4';
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
		
		Ext.apply(params, {czlx:lb,clbz:clbz,ycl:ycl,wcl:wcl});
		
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