
var cxCount = 1;
var rynbid = null;

var htmlStrStart = '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="cx/hjjbxx/ckxx/queryXxxx';
var htmlStrEnd = '"></iframe>';
//'?rynbid='+ getRynbid(rynbid) + "&goto=queryHdxx&hhnbid=" + getQueryParam("hhnbid") + '"> </iframe>';

var selectRynbid = null;
var selectHhnbid = null;
var selectHjywid = null;
var selectRyid = ""; 
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10019,30022",function(){}))
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
	
	var form30022 = new Gnt.ux.SjpzForm({
		pzlb: '30022',
		url: 'yw/queryRyxx',
		title:'',
		cols:2,
		formType:'query'
	});

	var p1_1 = new Gnt.ux.ZdycxPanel({
		pzlb:form30022.pzlb,
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
						html: '<div class="text" style="text-align:center;">质量反馈信息查询</div>',
						height: 80,
						region:'north',
						bodyStyle:'padding:15px'
					},
					form30022
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
//						html: '<div class="text" style="text-align:center;">质量反馈信息查询</div>',
//						height: 80,
//						region:'north',
//						bodyStyle:'padding:15px'
//					},
//					form30022
//				]
//			},
			{
				id:"southId",
	        	region:'south',
	        	height:40,
	            border:false,
	            frame:false,
	            items:[{
					border:false,
					frame: false,
		        	region:'south',
		        	height: 40,
		        	//bodyStyle: 'padding:10px 0px 0px 0px ',
					layout:'table',
					layoutConfig: {
			        	columns: 14
			        },
			    	items: [
						{
			    			width:10,
							border:false,
							frame: false
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
			    			width:10,
							border:false,
							frame: false
			    		},{
							border:false,
							frame: false,
			    		    items:[{
				    	    	xtype: 'DkButton',
				    	    	form:form30022,
				    	    	callback: function(){
				    	    		//Ext.getCmp("queryId").handler();
				    	    	}
				    	    }]
			    		},/*{
			    			width:10,
							border:false,
							frame: false
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
			    		},*//*{
			    			columnWidth: .01,
							border:false,
							frame: false
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
			    		},*/{
			    			width:10,
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
			        				Ext.getCmp('centerId').show();
			        				Ext.getCmp('p1_1').doLayout();
			        				Ext.getCmp('p1_1').setVisible(false);
			        				Ext.getCmp('preId').setDisabled(true);
			        				Ext.getCmp('nexId').setDisabled(false);
			        			}
			        		})]
			    		},{
			    			width:10,
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
			        				p1_1.qybz = true;
			        				Ext.getCmp('centerId').hide();
			        				Ext.getCmp('p1_1').setVisible(true);
			        				Ext.getCmp('p1_1').doLayout();
			        				Ext.getCmp('preId').setDisabled(false);
			        				Ext.getCmp('nexId').setDisabled(true);
			        			}
			        		})]
			    		},{
			    			width:10,
							border:false,
							frame: false
			    		},{
							border:false,
							frame: false,
			    		    items:[new Ext.Button({
			    		    	id:'queryId',
			        			text:'查询',
			        			minWidth:60,
			        			handler:function(){
			        				var data = form30022.getForm().getValues();

									if(Gnt.util.isEmpty(data)){
										showInfo("至少输入一个查询条件！");
										return;
									}
									//记录日志的操作码
									data.log_code = "F3410";
									Ext.Msg.wait("正在执行查询，请稍后...");
									var ary = p1_1.xszdGrid.store.data.items;
									var pxary = p1_1.pxzdGrid.store.data.items;//length 长度;
									if(p1_1.qybz){
										zlfkGrid.reconfigure(
												zlfkGrid.store,new Ext.grid.ColumnModel(Gnt.ZdyColumn(ary)));
										zlfkGrid.pxary = pxary;
									};
									zlfkGrid.zdyValueKey = Gnt.zdyValueKey(ary);
									var header=[];
									var shuxing = [];
									var zdyValueKey = Gnt.zdyValueKey(ary);
									zlfkGrid.type = 'zlfkcx';
			        				var store = zlfkGrid.store;
			        				store.removeAll();
			        				store.baseParams = data;
			        				var config=zlfkGrid.colModel.config;
									Ext.each(config,function(r){
										header.push(r.header);
										shuxing.push(r.dataIndex);
									});
									store.baseParams.header = encodeURI(header);
									store.baseParams.shuxing = encodeURI(shuxing);
									store.baseParams.zdyValueKey = Ext.encode(zdyValueKey);
									store.baseParams.daochuFlag = 'zlfkcx';
			        				applyParam(store);
//			        				Ext.apply(store.baseParams, rkfwRadio.items);
			        				store.load({params:{start:0, limit:20}});
			        				Ext.each(pxary, function(pxObj){
			        					store.sort(pxObj.data.fieldname, 'ASC');
			    					});
			        				store.on('load',function(s,records){
			        					if(records.length>0){
			        						curIndex = 0;
			        						zlfkGrid.fireEvent("rowclick",zlfkGrid,curIndex);
			        						zlfkGrid.getSelectionModel().selectRange(curIndex,curIndex);
			        					}
			        				});
			        			}
			        		})]
			    		},{
			    			width:10,
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
					
				}]
	        }
		]
	});
	
	var zlfkGrid = new Gnt.ux.SjpzGrid({
		pkname: 'nbslid',
		pzlb: '30022',
		url:'cx/edzjxx/zlfkcx/getZlfkxx.json',
		region:'center',
//		region:'north',
		height:400,
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
				var slzt = selRes.data.slzt;
				if(slzt=='93'||slzt=='97'){
					Ext.getCmp("cxzz1").disable();
					Ext.getCmp("cxzz2").disable();
				}else{
					Ext.getCmp("cxzz1").enable();
					Ext.getCmp("cxzz2").enable();
				}
				
			},
			rowdblclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				
				selectRynbid = selRes.data.rynbid;
				selectHhnbid = selRes.data.hhnbid;
				selectHjywid = selRes.data.hjywid;
				selectRyid = selRes.data.ryid;
				var slzt = selRes.data.slzt;
				if(slzt=='93'||slzt=='97'){
					Ext.getCmp("cxzz1").disable();
					Ext.getCmp("cxzz2").disable();
				}else{
					Ext.getCmp("cxzz1").enable();
					Ext.getCmp("cxzz2").enable();
				}
				var pkvalue = selRes.data[qczxForm.bindStore.pkname];
				var re = qczxForm.bindStore.getById(pkvalue);
				if(re){
					qczxForm.getForm().loadRecord(re);
				}
				Gnt.toFormReadyonly(qczxForm);
    			Ext.getCmp('card').getLayout().setActiveItem(2);
    			
    			v.doLayout();
    			
			}
		}
	});
	
	var zhcx_dialog = new Gnt.ux.ZhcxDialog({
		pzlb : form30022.pzlb,
		grid:zlfkGrid,
		callback: function(where){
			var ary = p1_1.xszdGrid.store.data.items;
			var pxary = p1_1.pxzdGrid.store.data.items;//length 长度;
			if(p1_1.qybz){
				zlfkGrid.reconfigure(
						zlfkGrid.store,new Ext.grid.ColumnModel(Gnt.ZdyColumn(ary)));
				zlfkGrid.pxary = pxary;
			};
			zlfkGrid.zdyValueKey = Gnt.zdyValueKey(ary);
			var grid = this.grid;
			var store = grid.store;
			var length = 0;
			var data = {"where": where};
			store.removeAll();
			data.pzlb = grid.store.pzlb;
			data.config_key = this.config_key;
			//记录日志的操作码
			data.log_code = "F3410";

			//add by zjm 20201223 复杂查询传入列表头 属性 自定义字段，用于导出功能使用
			var header=[];
			var shuxing = [];
			var zdyValueKey = Gnt.zdyValueKey(ary);
			grid.type = 'zlfkcx';
			Ext.each(ary,function(r){
				header.push(r.data.displayname);
				shuxing.push(r.data.fieldname);
			});
			data.header = encodeURI(header);
			data.shuxing = encodeURI(shuxing);
			data.zdyValueKey = Ext.encode(zdyValueKey);
			data.daochuFlag = 'zlfkcx';
			
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
			zlfkGrid
			],
			buttons:[
				new Ext.Button({
					text:'重新制证',
					id:'cxzz1',
					minWidth:80,
					handler:function(){
						if(selRes){
	        				var url = basePath + "yw/edzzjgl/zjsl?jumpToZjsl="+"cxbz"+"&ryid="+selRes.data.ryid;			      
	        				if(parent && parent.openWorkSpace){
	        					parent.openWorkSpace(url,  "证件受理", "zjsl");
	        				}else{
	        					window.location.href = url;
	        				}
	        			
						}else{
							alert("请先选中一条有效的数据!");
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
	

	var qczxForm = new Gnt.ux.SjpzForm({
		closable: false,
		/*region:'north',*/
		height:400,
		pzlb: '30022',
		labelWidth : 120,
		cols:2,
//		bindStore:zlfkGrid.store,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:zlfkGrid,
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
				layout:'column',
		    	items: [
					{
		    			columnWidth: .01,
						border:false,
						frame: false
		    		},{
		    			columnWidth: .01,
						border:false,
						frame: false
		    		},{
		    			columnWidth: .01,
						border:false,
						frame: false
		    		}
		    		 
		    	]
				
			},{
				id:"p3centerId",
				border:false,
				frame: false,
				region:'center',
//	        	height: 400,
				items:[qczxForm]
			},{
				id:"southId",
	        	region:'south',
	        	height:40,
	            border:false,
	            frame:false,
	            items:[{
					border:false,
					frame: false,
		        	region:'south',
		        	height: 40,
		        	bodyStyle: 'padding:10px 0px 0px 0px ',
					layout:'table',
			    	items: [
						{
			    			width:10,
							border:false,
							frame: false
			    		},{
							border:false,
							frame: false,
			    		    items:[new Ext.Button({
			        			text:'受理信息',
			        			minWidth:60,
			        			handler:function(){		   
			        				var url = basePath + "cx/edzjxx/edzslcx?jumpToThrycx="+"1"+"&rynbid="+selectRynbid;			      
			        				if(parent && parent.openWorkSpace){
			        					parent.openWorkSpace(url,  "证件受理信息查询", "edzslcx");
			        				}else{
			        					window.location.href = url;
			        				}
			        			}
			        		})]
			    		},{
			    			width:10,
							border:false,
							frame: false
			    		},{
							border:false,
							frame: false,
			    		    items:[new Ext.Button({
			        			text:'投递信息',
			        			minWidth:60,
			        			handler:function(){
			        							  
			        				var url = basePath + "cx/edzjxx/tdcx?jumpToThrycx="+"1"+"&ryid="+selectRyid;			      
			        				if(parent && parent.openWorkSpace){
			        					parent.openWorkSpace(url,  "投递信息查询", "tdcx");
			        				}else{
			        					window.location.href = url;
			        				}
			        				
			        			}
			        		})]
			    		},{
			    			width:10,
							border:false,
							frame: false
			    		},{
							border:false,
							frame: false,
			    		    items:[new Ext.Button({
			        			text:'重新制证',
			        			id:'cxzz2',			        			
			        			minWidth:60,
			        			handler:function(){
									if(selRes){
				        				var url = basePath + "yw/edzzjgl/zjsl?jumpToZjsl="+"cxbz"+"&ryid="+selRes.data.ryid;			      
				        				if(parent && parent.openWorkSpace){
				        					parent.openWorkSpace(url,  "证件受理", "zjsl");
				        				}else{
				        					window.location.href = url;
				        				}
				        			
									}else{
										alert("请先选中一条有效的数据!");
									}
								
			        			}
			        		})]
			    		},{
			    			width:10,
							border:false,
							frame: false
			    		},{
							border:false,
							frame: false,
			    		    items:[new Ext.Button({
			        			text:'常口信息',
			        			minWidth:60,
			        			handler:function(){
			        				czr={
											ryid:selectRyid,
											hhnbid:selectHhnbid
									}
									Gnt.toRyxx(czr);
			        			}
			        		})]
			    		},{
			    			width:10,
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
			    			width:10,
							border:false,
							frame: false
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
					
				}]
	        }
		]
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
		/*var gd = '';
		if(Ext.getCmp('c12').getValue()){
			gd = '1';
		}*/
		
		Ext.apply(store.baseParams, {xszs:zs});
		//Ext.apply(store.baseParams, {xszs:zs,gdxx:gd});
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
        	items:[p1,p2,p3]
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
			
			var store = zlfkGrid.store;
			
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
    			zlfkGrid.getView().getRow(girdcount).style.backgroundColor="red";
    		}else{
    			zlfkGrid.getView().getRow(girdcount).style.backgroundColor="green";
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