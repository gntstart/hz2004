
var cxCount = 1;
var rynbid = null;
var hhnbid = null;
var ryid = null;
var hhid = null;
var selRes = null;
var mlpnbid = null;
var queryFlag = false;
var htmlStrStart = '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="cx/hjjbxx/ckxx/queryXxxx?goto=';
var htmlStrEnd = '"></iframe>';
//'?rynbid='+ getRynbid(rynbid) + "&goto=queryHdxx&hhnbid=" + getQueryParam("hhnbid") + '"> </iframe>';

var selectRynbid = null;
var selectHhnbidselectHhnbid = null;

Ext.onReady(function(){
	Ext.QuickTips.init();
	
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
	
	var ckxxGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		pzlb: '20000',
		url:'yw/bbdy/cbddy/querycbddy',
		title: '',
		showPaging:false
	});
	
	var form20000 = new Gnt.ux.SjpzForm({
		pzlb: '20000',
		url: 'yw/bbdy/cbddy/querycbddy',
		title:'',
		cols:2,
		formType:'query'
	});
	
	var cbd_window = new Gnt.ux.cbdwin({
		//选择立户信息回调
		callback: function(type,vals){
			if(type=='1'){
				var arrayTemp=[];
				var o={};
				o.directTable="cbd";
				o.rynbid =selRes.data.rynbid;
				o.json = vals;
				arrayTemp.push(o);
				printfunction(0,arrayTemp);
			}else if(type=='2'){
				var arrayTemp=[];
				cbddyGrid.store.each(function(record) {
           		var o={};
					o.directTable="cbd";
					o.rynbid =record.get('rynbid');
					o.json = vals;
					arrayTemp.push(o);
	           	}); 
	           	printfunction(0,arrayTemp);
			}
			//提交数据
		  //alert(vals.bzsj);
		}
	});
	
	var NewForm20035 = new Gnt.ux.SjpzForm({
		closable: false,
		region:'center',
		/*height:500,*/
		pzlb: '20000',
		labelWidth : 120,
		cols:2,
//		bindStore:xblryGrid.store,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:ckxxGrid,
		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
		changeDictCust:function(cmb,res){
			
			return;
		},
		title: '',
		buttons:[   
		     new Ext.Button({
				text:'催办单',
				minWidth:60,
				handler:function(){
					cbd_window.show(1);
				}
			}),
		   new Ext.Button({
					id:'close',
					text:'关闭',
					minWidth:60,
					handler:function(){
						window.parent.closeWorkSpace();
					}
				}),
				new Ext.Button({
					//id:'return',
					text:'返回',
					minWidth:60,
					handler:function(){
						Ext.getCmp('card').getLayout().setActiveItem(1);
					}
				})]
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
				id:"centerId",
				border:false,
				frame: false,
				region:'center',
				layout:'border',
//	        	height: 300,
				items:[{
						id: "panelHtmlId" ,
						html: '<div class="text" style="text-align:center;"><br /><h2>催办通知单打印</h2></div>',
						height: 80,
						region:'north',
						bodyStyle:'padding:15px'
					},
					form20000
				]
			},
			{
				id:"southId",
	        	region:'south',
	        	height:130,
	            border:false,
	            frame:false,
	            items:[{
	            	id:'fwId',
	            	title: '查询选择',
//					columnWidth: 3,
		            xtype: 'fieldset',
		            autoHeight: true,
		            layout:'column',
		            defaults:{
		            	xtype:'radio',
		            	columnWidth: .25,
		            	fieldLabel: '',
		            	name: 'fw'
		            },
		            items: [{
		            	id:'r11',
		            	boxLabel: '所有',
		            	name:'fw',
		            	inputValue : "1",
		            	checked:true
		            },{
		            	id:'r12',
		            	boxLabel: '年满16周岁需申领',
		            	name:'fw',
		            	inputValue : "2"
		            },{
		            	id:'r13',
		            	boxLabel: '一代证换领二代证',
		            	name:'fw',
		            	inputValue : "3"
		            },{
		            	id:'r14',
		            	boxLabel: '证件到期',
		            	name:'fw',
		            	inputValue : "4"
		            }]
				},{
	            	title: '',
		            //xtype: 'fieldset',
		            autoHeight: true,
		            layout:'table',
		           // border:false,
		            layoutConfig: {columns: 3},
		            bodyStyle:'padding:0 0 0 15',
		            items: [{
		            	layout:'form',
		            	border:false,
		            	frame:false,
		            	labelWidth:60,
		            	items:[{
		            		xtype:'datefield',
							width:120,
							format:'Y/m/d',
							id:'rqsj',
							name:'rqsj',
							value:Ext.util.Format.date(new Date(),"Y/m/d"),
							fieldLabel:'选择日期',
							listeners:{
								'change':function(){
									
								}
							}
		            	}]
						
					
	            	},{width:100,border:false,frame:false},{border:false,frame:false,style:'color:blue',html:'是指年满16岁，一代证到期，二代证到期的计算时间'}]
				},{
					border:false,
					frame: false,
		        	region:'south',
		        	height: 40,
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
				    	    	form:form20000,
				    	    	callback: function(){
				    	    		//Ext.getCmp("queryId").handler();
				    	    	}
				    	    }]
			    		},/*{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
//							colspan:1
							width:10
			    		},{
							border:false,
							frame: false,
			    		    items:[{
			    		    	id:'c31',
				        		xtype:'checkbox',
				        		boxLabel : "显示记录总数",  
				        		name : "xxzs",  
				        		inputValue : "2"  
				        	}]
			    		},*/{
			    			//width: 100,
			    			bodyStyle: 'padding:0 0 0 400',
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
			    			width: 10,
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
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
//							colspan:3
//							columns:10
			    		},{
							border:false,
							frame: false,
			    		    items:[new Ext.Button({
			    		    	id:'queryId',
			        			text:'查询',
			        			minWidth:60,
			        			handler:function(){
			        				var data = form20000.getForm().getValues();
			        				
			        					/*if(Gnt.util.isEmpty(data)){
			        						showInfo("至少输入一个查询条件！");
			        						return;
			        					}*/
			        				
									Ext.Msg.wait("正在执行查询，请稍后...");
			        				var store = cbddyGrid.store;
			        				
			        				store.removeAll();
			        				
			        				store.baseParams = data;
			        				
			        				applyParam(store);
			        				
			        				
			        				store.load({params:{start:0, limit:40}});
			        				store.on("load",function(store){
			         					if(store.data.length>0){
			         						curIndex = 0;
			         						cbddyGrid.fireEvent("rowclick",cbddyGrid,curIndex);
			         						cbddyGrid.getSelectionModel().selectRange(curIndex,curIndex);
			         						
			         					}
			         				});
			        				//Ext.getCmp('card').getLayout().setActiveItem(1);
			        			
			        				
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
					
				}]
	        }
		]
	});
	
	var cbddyGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		pzlb: '20000',
		url:'yw/bbdy/cbddy/querycbddy.json',
		region:'center',
		title: '',
		showPaging:true,
		loadCallback: function(res, store, bind_grid){
			Ext.Msg.hide();
			if(res.length>0){
				Ext.getCmp('card').getLayout().setActiveItem(1);
			}else{
				showInfo("未查到有关信息！");
			}
		},
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
    				
			},rowdblclick:function(grid,row){
				
				selRes = grid.store.getAt(row);
				selectRynbid = selRes.data.rynbid;
				NewForm20035.getForm().loadRecord(selRes);
				Gnt.toFormReadyonly(NewForm20035);
 				Ext.getCmp('card').getLayout().setActiveItem(2);
 				v.doLayout();
			}
			
		}
	});
	
	var zhcx_dialog = new Gnt.ux.ZhcxDialog({
		pzlb : form20000.pzlb,
		grid:cbddyGrid,
		applyParam:function(){
			applyParam(this.grid.store);
		},
		callback: function(where){
			var grid = this.grid;
			var store = grid.store;
			var length = 0;
			var data = {"where": where};
			store.removeAll();
			data.pzlb = grid.store.pzlb;
			data.config_key = this.config_key;
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
		items:[
			cbddyGrid
		]
		,buttons:[
			{
				id:'cbhkId',
				text:'通知单',
				minWidth:60,
				handler:function(){
					if(selRes){
						var arrayTemp=[];
	               	 	var o={};
						o.directTable="tzd";
						o.rynbid =selRes.data.rynbid;
						arrayTemp.push(o);
						printfunction(0,arrayTemp);
					}else{
						alert("请至少选择一条数据！");
					}
					
				}
			},{
				id:'hjzmId',
				text:'全部通知单',
				minWidth:60,
				handler:function(){
					 var arrayTemp=[];
					 cbddyGrid.store.each(function(record) {
                		 var o={};
						 o.directTable="tzd";
						 o.rynbid =record.get('rynbid');
						 arrayTemp.push(o);
                	 }); 
                	 printfunction(0,arrayTemp);
				}
			},{
				id:'xxxxId',
				text:'催办单',
				minWidth:60,
				handler:function(){
					if(selRes){
						cbd_window.show(1);
					}else{
						alert("请至少选择一条数据！");
					}
					
					
				}
			},{
				id:'rzsId',
				text:'全部催办单',
				minWidth:60,
				handler:function(){
					cbd_window.show(2);
				}
			},{
				text:'关闭',
				minWidth:60,
				handler:function(){
					window.parent.closeWorkSpace();
				}
			},{
				text:'返回',
				minWidth:60,
				handler:function(){
					Ext.getCmp('card').getLayout().setActiveItem(0);
					v.doLayout();
				}
			}
		]
	});
	
	var p3 = new Ext.Panel({
		layout:'border',
		items:[NewForm20035,{
	    	frame:false,
			border:false,
			id:'_READ_CARD_ID',
			xtype:'panel',
			html:'',
			width:10
			
		}]
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
        	beforeremove:fixIFrame.createDelegate(this)
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
		}else if(Ext.getCmp('r14').getValue()){
			fw = '4';
		}
		
		var sj=Ext.getCmp('rqsj').getValue();
		sj=Ext.util.Format.date(sj,"Ymd")
		
		/**
			是否显示记录总数
		 */
		var zs = '';
//		if(Ext.getCmp('c31').getValue()){
//			zs = '1';
//		}
		
		
		Ext.apply(store.baseParams, {fw:fw,sj:sj});
		
		
		
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
			result += "&hzywid=" + hzywid;
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
        	items:[p1,p2,p3,tabs]
        }
    });
	
  /*  if(getQueryParam("ai")){
    	Ext.getCmp('card').getLayout().setActiveItem(getQueryParam("ai"));
    	tabs.setActiveTab(0);
    }else{
    	Ext.getCmp('nextId').setVisible(false);
    }
	if(getQueryParam("jumpToCzrkcx")&& getQueryParam("jumpToCzrkcx")!=""){
		queryFlag = true;
		Ext.getCmp("queryId").handler();
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
	}*/
    
	
	
	
});