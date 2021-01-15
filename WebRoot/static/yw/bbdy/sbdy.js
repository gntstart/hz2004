var cxCount = 0;
var rynbid = null;
var hhnbid = null;
var ryid = null;
var hhid = null;
var qhtz =1;
var selectedSlsj = "";
var selectedPcs ="";
var selectedJwh ="";
var selectedSldw = "";
var selectedSlrid = "";
var htmlStrStart = '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="yw/bbdy/sbdy/querySbxx';
var htmlStrEnd = '"></iframe>';
//'?rynbid='+ getRynbid(rynbid) + "&goto=queryHdxx&hhnbid=" + getQueryParam("hhnbid") + '"> </iframe>';

var selectRynbid = null;
var selectHhnbidselectHhnbid = null;

Ext.onReady(function(){
	Ext.QuickTips.init();
//	if(user.yhid>0){
//		cxCount++;
//	}
//	if(user.dwCode>0){
//		cxCount++;
//	}
	var hzywid = getQueryParam("hzywid");
	var pch = getQueryParam("pch");
	
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
		height:150,
    	title: '',
		url: 'yw/common/queryRyxx.json',
    	showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){
				
			}
		}
	});
	
	var cxfw = new Ext.data.SimpleStore({
		id:0,
		fields:[{name: 'code', mapping: 0},{name: 'name', mapping: 1}],
	   	data:[['0','所外'],['1','区县外'],['2','地市外'],['3','所有'],['4','最新']]
	});
	
	var p1 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[{
				id:"centerId",
				title: '',
	        	region:'center',
	            autoHeight : true, 
	            labelWidth : 280,
	            bodyStyle : 'background-color:white',
		    	items: [{
					id: "panelHtmlId" ,
					border:false,
					frame: false,
					html: '<div class="text" style="text-align:center;"><h2>四变打印</h2></div>',
					height: 80,
					region:'north',
					bodyStyle:'padding:15px'
				},{
//					height:300,
					border:false,
					frame: false,
					bodyStyle:'padding:5px',
					items:[{
						title: '受理条件',
			            xtype: 'fieldset',
			            autoHeight: true,
			            layout:'column',
						defaults:{
			            	columnWidth: 1.,
			            	fieldLabel: '',
			            	bodyStyle:'padding:5px',
			            	name: 'fs'
			            },
			            items: [{
			    	    	title: '',
			            	region:'center',
				            layout:'table',
				            border:false,
							frame: false,
			            	items:[{
			            		html: '请选择打印月份',
								height: 25,
								border:false,
								frame: false,
								region:'north',
								bodyStyle:'padding:5px'
			            	},{
								xtype:'datefield',
								width:120,
								format:'Ym',
								id:'dyyf',
								name:'dyyf',
								fieldLabel:'打印月份'
							
			            	}]
			            },{
			    	    	title: '',
			            	region:'center',
				            layout:'table',
				            border:false,
							frame: false,
			            	items:[{
			            		html: '受理单位',
								height: 25,
								width:120,
								border:false,
								frame: false,
								region:'north',
								bodyStyle:'padding:5px'
			            	},{
								xtype:'search_combox',
								searchUrl:'dict/utils/searchXxb?visiontype=DWXXB',
								name:'sldw',
								//value:user.dwCode,
								id:'sldw',
								listeners:{
									select :{
								　　　　　　fn:function(combo,record,index) {
											if(record&&record.data){
												queryFlag(Ext.getCmp('sldw').getValue(),Ext.getCmp('slrid').getValue(),Ext.getCmp('pcs').getValue(),Ext.getCmp('jwh').getValue());
											}
								　　　　　　},
	                            		blur: function(){
	                                        //失去焦点事件
											queryFlag(Ext.getCmp('sldw').getValue(),Ext.getCmp('slrid').getValue(),Ext.getCmp('pcs').getValue(),Ext.getCmp('jwh').getValue());
	                                    } 

									}
				                //监听事件
					            }
			            	}]
			            },{
			    	    	title: '',
			            	region:'center',
				            layout:'table',
				            border:false,
							frame: false,
			            	items:[{
			            		html: '受理人',
								height: 25,
								width:120,
								border:false,
								frame: false,
								region:'north',
								bodyStyle:'padding:5px'
			            	},{
								xtype:'search_combox',
								searchUrl:'dict/utils/searchXxb?visiontype=YHXXB',
								id:'slrid',
								//value:user.yhid,
								name:'slrid',
								listeners:{
									select :{
								　　　　　　fn:function(combo,record,index) {
											if(record&&record.data){
												queryFlag(Ext.getCmp('sldw').getValue(),Ext.getCmp('slrid').getValue(),Ext.getCmp('pcs').getValue(),Ext.getCmp('jwh').getValue());
											}
								　　　　　　}

									},
                            		blur: function(){
                                        //失去焦点事件
										queryFlag(Ext.getCmp('sldw').getValue(),Ext.getCmp('slrid').getValue(),Ext.getCmp('pcs').getValue(),Ext.getCmp('jwh').getValue());
                                    } 


				                //监听事件
					            }
			            	}]
			            }]
					}]
				},{
					border:false,
					frame: false,
					bodyStyle:'padding:5px',
					items:[{
	            	title: '区域',
	            	region:'center',
		            xtype: 'fieldset',
		            layout:'column',
		            width:'49%',
					height:100,
            		bodyStyle : 'padding:5px',
					defaults:{
		            	columnWidth: 1,
		            	fieldLabel: '',
		            	name: 'fs'
		            },
		            items: [{
		    	    	title: '',
		            	region:'center',
			            layout:'table',
		            	items:[{
		            		id:'s11',
			            	xtype:'radio',
			            	boxLabel: '派出所',
			            	name:'qy',
			            	inputValue : "1",
			            	checked:true,
			            	listeners:{
			            		'check' : function(checkbox, checked){ 
			                         if(checked){//只有在点击时触发
			                        	 if(Ext.getCmp('s11').getValue()){
			                        		 Ext.getCmp('s12').setValue(false);
			                        		 Ext.getCmp('jwh').disable();
			                        		 Ext.getCmp('pcs').enable();
//			                        		 if(Ext.getCmp('pcs').getValue()&&Ext.getCmp('pcs').getValue()!=""&&Ext.getCmp('dyyf').getValue()&&Ext.getCmp('dyyf').getValue()!=""){
//			                        			 Ext.getCmp('queryId').enable();
//			                        		 }else{
//			                        			 Ext.getCmp('queryId').disable();
//			                        		 }
			                        	 }
			                         }  
			                    }
			                //监听事件
				            }},{
		        	       		anchor:'80%',
								xtype:'search_combox',
								searchUrl:'dict/utils/searchPcs.json',
								fields:["mc","dm","qhdm","pcsList"],
								valueField: "dm",
								displayField: "mc",
								hiddenName:'jlx',
								listeners:{
									select:function(){
										queryFlag(Ext.getCmp('sldw').getValue(),Ext.getCmp('slrid').getValue(),Ext.getCmp('pcs').getValue(),Ext.getCmp('jwh').getValue());
//										if(Ext.getCmp('dyyf').getValue()&&Ext.getCmp('dyyf').getValue()!=""){
//											Ext.getCmp('queryId').enable();
//										}else{
//											Ext.getCmp('queryId').disable();
//										}
									},
									blur: function(){
                                        //失去焦点事件
										queryFlag(Ext.getCmp('sldw').getValue(),Ext.getCmp('slrid').getValue(),Ext.getCmp('pcs').getValue(),Ext.getCmp('jwh').getValue());
                                    } 
								},
								id:'pcs',
								name:'pcsmc',
								maxLength:30,
								allowBlank:true,
								fieldLabel:''
							}]
		            },{
		    	    	height:10,
		    	    	border:false,
		    	    	frame:false
		    	    },{
		    	    	title: '',
		            	region:'center',
			            layout:'table',
		            	items:[{
		            		id:'s12',
			            	xtype:'radio',
			            	boxLabel: '居委会',
			            	name:'qy',
			            	inputValue : "2",
			            	listeners:{
			            		'check' : function(checkbox, checked){ 
			                         if(checked){//只有在点击时触发
			                        	 Ext.getCmp('s11').setValue(false);
		                        		 Ext.getCmp('jwh').enable();
		                        		 Ext.getCmp('pcs').disable();
//		                        		 if(Ext.getCmp('jwh').getValue()&&Ext.getCmp('jwh').getValue()!=""&&Ext.getCmp('dyyf').getValue()&&Ext.getCmp('dyyf').getValue()!=""){
//		                        			 Ext.getCmp('queryId').enable();
//		                        		 }{
//		                        			 Ext.getCmp('queryId').disable();
//		                        		 }
			                         }  
			                    }
			                //监听事件
				            }},{
				            	anchor:'80%',
								xtype:'search_combox',
								searchUrl:'dict/utils/searchJwh.json',
								fields:["mc","dm","qhdm","jwhList"],
								id:'jwh',
								valueField: "dm",
								displayField: "mc",
								hiddenName:'jlx',
								listeners:{
									select:function(){
										queryFlag(Ext.getCmp('sldw').getValue(),Ext.getCmp('slrid').getValue(),Ext.getCmp('pcs').getValue(),Ext.getCmp('jwh').getValue());
//										if(Ext.getCmp('dyyf').getValue()&&Ext.getCmp('dyyf').getValue()!=""){
//											Ext.getCmp('queryId').enable();
//										}else{
//											Ext.getCmp('queryId').disable();
//										}
									},
									blur: function(){
                                        //失去焦点事件
										queryFlag(Ext.getCmp('sldw').getValue(),Ext.getCmp('slrid').getValue(),Ext.getCmp('pcs').getValue(),Ext.getCmp('jwh').getValue());
                                    }
								},
								disabled:true,
								name:'jwhmc',
								maxLength:30,
								allowBlank:true,
								fieldLabel:''
							}]
		            }]
				}]
				},{
					autoHeight: true,
					height:480,
					border:false,
					frame: false,
					bodyStyle:'padding:10px',
					items:[{
		            	id:'c21',
		            	xtype:'checkbox',
		            	width:200,
		            	boxLabel: '包括区划调整的四变',
		            	name:'qhtz',
		            	inputValue : "1"
		            }]
				},{
					border:false,
					frame: false,
		        	region:'south',
		        	height: 40,
		        	width:'80%',
		        	bodyStyle: 'padding:5px',
					layout:'table',
//					layoutConfig: {
//			        	columns: 10
//			        },
			    	items: [
						{
						border:false,
						frame: false,
		    		    items:[new Ext.Button({
		    		    	id:'queryId',
		        			text:'查询',
		        			disabled:true,
		        			minWidth:60,
		        			handler:function(){
		        				if(!Ext.getCmp('dyyf').getValue()){
		        					alert("打印月份不能为空!");
		        					return;
		        				}
		        				var data ={};
		        				selectedSlsj = (Ext.getCmp('dyyf').getValue()).format('Ym');
		        				if(Ext.getCmp('c21').getValue()){
		        					qhtz = 2;
		        				}
		        				if(Ext.getCmp('s11').getValue()){
		        					selectedPcs= Ext.getCmp('pcs').getValue();
		        					selectedJwh="";
		        				}
		        				if(Ext.getCmp('s12').getValue()){
			        				selectedPcs="";	
		        					selectedJwh= Ext.getCmp('jwh').getValue();
		        				}
		        				if(Ext.getCmp('sldw').getValue()){
		        					selectedSldw= Ext.getCmp('sldw').getValue();
		        				}else{
		        					selectedSldw = "";
		        				}
		        				if(Ext.getCmp('slrid')){
		        					selectedSlrid= Ext.getCmp('slrid').getValue();
		        				}else{
		        					selectedSlrid = "";
		        				}
//		        				selectedPcs = '340702001';//Ext.getCmp('pcs').getValue();
		        				data.pcs = selectedPcs;//Ext.getCmp('pcs').getValue();
		        				data.jwh = selectedJwh;
		        				data.slsj = Ext.getCmp('dyyf').getValue().format('Ym');
		        				data.qhtz = qhtz;
		        				data.sldw = selectedSldw;
		        				data.slrid = selectedSlrid;
		        				tabs.getUpdater().refresh();
		        				tabs.doLayout();
		        				//tabs.setActiveTab(2);
		        				tabs.setActiveTab(0);
		        				
		        				Ext.getCmp('card').getLayout().setActiveItem(1);
//		        				Ext.Msg.wait("正在执行查询，请稍后...");
//		        				var store = hcyGrid.store;
//	        					store.removeAll();
//	        					store.baseParams = data;
//	        					store.load({params:{start:0, limit:9999}});
//	        					store.on('load',function(s,records){
//	        						hcyGrid.fireEvent("rowclick",hcyGrid,0);
//	        						hcyGrid.getSelectionModel().selectRange(0,0);
//		        				});
//	        					Ext.Msg.hide();
	        					
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
					
				}
	    	]
	        }
		]
	});
	
	var czrkGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		pzlb: '20000',
		url:'cx/hjjbxx/ckxx/getRkxx.json',
		region:'center',
		title: '',
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
					//tabs.load();
					Ext.Msg.wait("操作正在执行中，请稍后...");
					tabs.setActiveTab(2);
					tabs.setActiveTab(0);
					Ext.getCmp('card').getLayout().setActiveItem(1);
					
					Ext.Msg.hide();
					
				}else{
					
				}
			}
		}
	});
	
	var lsxxGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		pzlb: '20000',
    	region:'south',
		height:150,
		url: 'cx/hjjbxx/ckxx/getXxxx.json',
    	title: '',
    	showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){
    			selRes = g.store.getAt(rowIndex);
    			curIndex = rowIndex;
    			
    		}
		}
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
        			Ext.getCmp('qrxx').body.update(htmlStrStart + '?pcs='+ selectedPcs+"&jwh="+selectedJwh
							+"&slsj=" + selectedSlsj+ "&goto=queryQrxx&qhtz=" + qhtz +"&sldw="+selectedSldw+"&slrid="+selectedSlrid+ htmlStrEnd);
					this.getUpdater().refresh();
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
			id : "qrxx",
			title : "迁入",
			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
				activate:function(){
					Ext.getCmp('qrxx').body.update(htmlStrStart + '?pcs='+ selectedPcs+"&jwh="+selectedJwh
							+"&slsj=" + selectedSlsj+ "&goto=queryQrxx&qhtz=" + qhtz +"&sldw="+selectedSldw+"&slrid="+selectedSlrid+ htmlStrEnd);
					this.getUpdater().refresh();
				}
			}
        },{
			id : "csxx",
			title : "出生",
			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
				activate:function(){
					Ext.getCmp('csxx').body.update(htmlStrStart + '?pcs='+ selectedPcs+"&jwh="+selectedJwh
							+"&slsj=" + selectedSlsj+ "&goto=queryCsxx&qhtz=" + qhtz +"&sldw="+selectedSldw+"&slrid="+selectedSlrid+ htmlStrEnd);
					this.getUpdater().refresh();
				}
			}
        },{
			id : "qcxx",
			title : "迁出",
			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
				activate:function(){
					Ext.getCmp('qcxx').body.update(htmlStrStart + '?pcs='+ selectedPcs+"&jwh="+selectedJwh
							+"&slsj=" + selectedSlsj+ "&goto=queryQcxx&qhtz=" + qhtz +"&sldw="+selectedSldw+"&slrid="+selectedSlrid+ htmlStrEnd);
					this.getUpdater().refresh();
				}
			}
        },{
			id : "swxx",
			title : "死亡",
			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
				activate:function(){
					Ext.getCmp('swxx').body.update(htmlStrStart + '?pcs='+ selectedPcs+"&jwh="+selectedJwh
							+"&slsj=" + selectedSlsj+ "&goto=querySwxx&qhtz=" + qhtz +"&sldw="+selectedSldw+"&slrid="+selectedSlrid+ htmlStrEnd);
					this.getUpdater().refresh();
				}
			}
            
        },{
			id : "zzbd",
			title : "住址变动",
			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
				activate:function(){
					Ext.getCmp('zzbd').body.update(htmlStrStart + '?pcs='+ selectedPcs+"&jwh="+selectedJwh
							+"&slsj=" + selectedSlsj+ "&goto=queryZzbd&qhtz=" + qhtz +"&sldw="+selectedSldw+"&slrid="+selectedSlrid+ htmlStrEnd);
					this.getUpdater().refresh();
				}
			}
            
        },{
			id : "hbbg",
			title : "户别变更",
			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
				activate:function(){
					Ext.getCmp('hbbg').body.update(htmlStrStart + '?pcs='+ selectedPcs+"&jwh="+selectedJwh
							+"&slsj=" + selectedSlsj+ "&goto=queryHbbg&qhtz=" + qhtz +"&sldw="+selectedSldw+"&slrid="+selectedSlrid+ htmlStrEnd);
					this.getUpdater().refresh();
				}
			}
            
        },{
			id : "sjbl",
			title : "数据补录",
			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
				activate:function(){
					Ext.getCmp('sjbl').body.update(htmlStrStart + '?pcs='+ selectedPcs+"&jwh="+selectedJwh
							+"&slsj=" + selectedSlsj+ "&goto=querySjbl&qhtz=" + qhtz +"&sldw="+selectedSldw+"&slrid="+selectedSlrid+ htmlStrEnd);
					this.getUpdater().refresh();
				}
			}
            
        },{
			id : "sjsc",
			title : "数据删除",
			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
				activate:function(){
					Ext.getCmp('sjsc').body.update(htmlStrStart + '?pcs='+ selectedPcs+"&jwh="+selectedJwh
							+"&slsj=" + selectedSlsj+ "&goto=querySjsc&qhtz=" + qhtz +"&sldw="+selectedSldw+"&slrid="+selectedSlrid+ htmlStrEnd);
					
					this.getUpdater().refresh();
				}
			}
            
        }]
    });
	
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
        	items:[p1,tabs]
        }
    });
	if(cxCount>0){
		Ext.getCmp('queryId').enable();
	}
	function queryFlag(sldw,slrid,pcs,jwh){
		if(sldw||slrid||pcs||jwh){
			Ext.getCmp('queryId').enable();
		}else{
			Ext.getCmp('queryId').disable();
		}
	}
});