
var cxCount = 1;
var rynbid = null;

var htmlStrStart = '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="cx/hjjbxx/ckxx/queryXxxx';
var htmlStrEnd = '"></iframe>';
//'?rynbid='+ getRynbid(rynbid) + "&goto=queryHdxx&hhnbid=" + getQueryParam("hhnbid") + '"> </iframe>';

var selectRynbid = "";
var selectHhnbid = null;
var selectHjywid = null;
var selectRyid = "";
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10019,30001",function(){}))
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
	
	var form30001 = new Gnt.ux.SjpzForm({
		pzlb: '30001',
		url: 'yw/queryRyxx',
		title:'',
		cols:2,
		formType:'query'
	});
	
	var p1_1 = new Gnt.ux.ZdycxPanel({
		pzlb:form30001.pzlb,
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
						html: '<div class="text" style="text-align:center;">身份证信息查询</div>',
						height: 80,
						region:'north',
						bodyStyle:'padding:15px'
					},
					form30001
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
//						html: '<div class="text" style="text-align:center;">身份证信息查询</div>',
//						height: 80,
//						region:'north',
//						bodyStyle:'padding:15px'
//					},
//					form30001
//				]
//			},
			{
				id:"southId",
	        	region:'south',
	        	height:50,
	            border:false,
	            frame:false,
	            items:[{
					border:false,
					frame: false,
		        	region:'south',
		        	height: 40,
		        	bodyStyle: 'padding:5px 0px 0px 0px ',
					layout:'table',
					layoutConfig: {
			        	columns: 15
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
							border:false,
							frame: false,
							width:10
			    		},{
							border:false,
							frame: false,
			    		    items:[{
				    	    	xtype: 'DkButton',
				    	    	form:form30001,
				    	    	callback: function(){
				    	    		//Ext.getCmp("queryId").handler();
				    	    	}
				    	    }]
			    		},/*{
							border:false,
							frame: false,
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
							border:false,
							frame: false,
							width:10
			    		}/*,{
							border:false,
							frame: false,
			    		    items:[{
			    		    	id:'c12',
				        		xtype:'checkbox',
				        		boxLabel : "查询归档信息",  
				        		name : "gdxx",  
				        		inputValue : "2"  
				        	}]
			    		}*/,{
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
							border:false,
							frame: false,
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
							border:false,
							frame: false,
							width:10
			    		},{
							border:false,
							frame: false,
			    		    items:[new Ext.Button({
			    		    	id:'queryId',
			        			text:'查询',
			        			minWidth:60,
			        			handler:function(){
			        				var data = form30001.getForm().getValues();
									if(Gnt.util.isEmpty(data)){
										showInfo("至少输入一个查询条件！");
										return;
									}
									//记录日志的操作码
									data.log_code = "F3411";
									Ext.Msg.wait("正在执行查询，请稍后...");
									var ary = p1_1.xszdGrid.store.data.items;
									var pxary = p1_1.pxzdGrid.store.data.items;//length 长度;
									if(p1_1.qybz){
										sfzGrid.reconfigure(
												sfzGrid.store,new Ext.grid.ColumnModel(Gnt.ZdyColumn(ary)));
										sfzGrid.pxary = pxary;
									};
									sfzGrid.zdyValueKey = Gnt.zdyValueKey(ary);
									var header=[];
									var shuxing = [];
									var zdyValueKey = Gnt.zdyValueKey(ary);
									sfzGrid.type = 'sfzcx';
			        				var store = sfzGrid.store;
			        				store.removeAll();
			        				store.baseParams = data;
			        				var config=sfzGrid.colModel.config;
									Ext.each(config,function(r){
										header.push(r.header);
										shuxing.push(r.dataIndex);
									});
									store.baseParams.header = encodeURI(header);
									store.baseParams.shuxing = encodeURI(shuxing);
									store.baseParams.zdyValueKey = Ext.encode(zdyValueKey);
									store.baseParams.daochuFlag = 'sfzcx';
			        				applyParam(store);
			        				store.load({params:{start:0, limit:20}});
			        				Ext.each(pxary, function(pxObj){
			        					store.sort(pxObj.data.fieldname, 'ASC');
			    					});
			        			}
			        		})]
			    		},{
							border:false,
							frame: false,
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
			    	]
					
				}]
	        }
		]
	});
	
	var sfzGrid = new Gnt.ux.SjpzGrid({
		pkname: 'nbsfzid',
		pzlb: '30001',
		url:'cx/hmzjxx/sfzcx/getSfzxx.json',
		region:'center',
		height:400,
		title: '',
		dcFlag:true,
		showPaging:true,
		loadCallback: function(res, store, bind_grid){
			Ext.Msg.hide();
			if(res.length>0){
				if(getQueryParam("jumpToThrycx")&&getQueryParam("jumpToThrycx")!=""){
					var pkvalue = store.data.items[0].data[qczxForm.bindStore.pkname];
					var re = qczxForm.bindStore.getById(pkvalue);
					if(re){
						qczxForm.getForm().loadRecord(re);
					}
					Gnt.toFormReadyonly(qczxForm);
	    			Ext.getCmp('card').getLayout().setActiveItem(2);
	    			
	    			v.doLayout();
					
				}else{
					Ext.getCmp('card').getLayout().setActiveItem(1);
				} 
			}else{
				showInfo("未查到有关信息！");
			}
		},
		listeners:{
			rowclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				
//				selectRynbid = selRes.data.rynbid;
				selectHhnbid = selRes.data.hhnbid;
				selectHjywid = selRes.data.hjywid;
				selectRyid = selRes.data.ryid;
				
			},
			rowdblclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				
//				selectRynbid = selRes.data.rynbid;
				selectHhnbid = selRes.data.hhnbid;
				selectHjywid = selRes.data.hjywid;
				selectRyid = selRes.data.ryid;
				
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
		pzlb : form30001.pzlb,
		grid:sfzGrid,
		callback: function(where){
			var ary = p1_1.xszdGrid.store.data.items;
			var pxary = p1_1.pxzdGrid.store.data.items;//length 长度;
			if(p1_1.qybz){
				sfzGrid.reconfigure(
						sfzGrid.store,new Ext.grid.ColumnModel(Gnt.ZdyColumn(ary)));
				sfzGrid.pxary = pxary;
			};
			sfzGrid.zdyValueKey = Gnt.zdyValueKey(ary);
			var grid = this.grid;
			var store = grid.store;
			var length = 0;
			var data = {"where": where};
			store.removeAll();
			data.pzlb = grid.store.pzlb;
			data.config_key = this.config_key;
			//记录日志的操作码
			data.log_code = "F3411";

			//add by zjm 20201223 复杂查询传入列表头 属性 自定义字段，用于导出功能使用
			var header=[];
			var shuxing = [];
			var zdyValueKey = Gnt.zdyValueKey(ary);
			grid.type = 'sfzcx';
			Ext.each(ary,function(r){
				header.push(r.data.displayname);
				shuxing.push(r.data.fieldname);
			});
			data.header = encodeURI(header);
			data.shuxing = encodeURI(shuxing);
			data.zdyValueKey = Ext.encode(zdyValueKey);
			data.daochuFlag = 'sfzcx';
			
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
			 sfzGrid			
			],
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
	

	var qczxForm = new Gnt.ux.SjpzForm({
		closable: false,
		/*region:'north',*/
		height:500,
		pzlb: '30001',
		labelWidth : 120,
		cols:2,
//		bindStore:sfzGrid.store,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:sfzGrid,
		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
		changeDictCust:function(cmb,res){
			
			return;
		},
		title: ''
	});
	
	var p3 = new Ext.Panel({
		layout:'border',
		items:[{
	    	id:'p3centerId',
	        region:'center',
	        //禁止横向滚动条
	        border:false,
	        frame:false,
	        items:[qczxForm]
	    },{
	    	region:'east',
	    	 width:180,
	    	 layout:'table',
	    	 layoutConfig: {
	    		columns: 1
	    	 },
	    	 items: [{
		 		 		title: '',
				 		height:'100%',
						html: '<div id="_PHOTO_ID">照片</DIV>',
						width:150,
						height:150,
						rowspan: 1,
						colspan:1
			    	}]
	    },{

			region:'south',
			width: '100%', 
	        border:false,
	        frame:false,
	        buttons:[
		        new Ext.Button({
        			text:'常口信息',
        			minWidth:60,
        			handler:function(){
        				czr={
								ryid:selectRyid,
								rynbid:selectRynbid,
								hhnbid:selectHhnbid
						}
						Gnt.toRyxx(czr);
        			}
        		}),
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
		/*var gd = '';
		if(Ext.getCmp('c12').getValue()){
			gd = '1';
		}
		*/
		
		//Ext.apply(store.baseParams, {xszs:zs,gdxx:gd});
		Ext.apply(store.baseParams, {xszs:zs});
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
	
	
	if(getQueryParam("jumpToThrycx")&& getQueryParam("jumpToThrycx")!=""){
		
		var store = sfzGrid.store;
		store.baseParams = {
			gmsfhm:getQueryParam("gmsfhm"),
			start:0,
			limit:20
		};
		store.load();
		Ext.getCmp('card').getLayout().setActiveItem(2);
		v.doLayout();
    }else{
    	
    	Ext.getCmp('nextId').setVisible(false);
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
			
			var store = sfzGrid.store;
			
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
    			sfzGrid.getView().getRow(girdcount).style.backgroundColor="red";
    		}else{
    			sfzGrid.getView().getRow(girdcount).style.backgroundColor="green";
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