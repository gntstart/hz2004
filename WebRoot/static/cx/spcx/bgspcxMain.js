
var cxCount = 1;
var rynbid = null;

var selectRynbid = null;
var selectSpywid = null;
var selectHjywid = null;
var selpkvalue = null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	var hzywid = getQueryParam("hzywid");
	var pch = getQueryParam("pch");
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10019,20026,10029,20024,10031",function(){}))
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
	
	var form20026 = new Gnt.ux.SjpzForm({
		pzlb: '20026',
		url: 'yw/queryRyxx',
		title:'',
		region:'center',
		cols:2,
		formType:'query'
	});
	var p1_1 = new Gnt.ux.ZdycxPanel({
		pzlb:form20026.pzlb,
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
						border:false,
						frame: false,
						id: "panelHtmlId" ,
						html: '<div class="text" style="text-align:center;"><br /><h2>变更审批</ h2></div>',
						height: 80,
						region:'north',
						bodyStyle:'padding:15px'
					},
					form20026
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
//						html: '<div class="text" style="text-align:center;"><br /><h2>变更审批</ h2></div>',
//						height: 80,
//						region:'north',
//						bodyStyle:'padding:15px'
//					},
//					form20026
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
		        	columns: 14
		        },
			    items: [
					{
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
						xtype:'panel',
						html:'',
						frame:false,
						border:false,
						width:10
					},{
		    	    	xtype: 'DkButton',
		    	    	form:form20026,
		    	    	callback: function(){
		    	    		//Ext.getCmp("queryId").handler();
		    	    	}
		    	    },/*{
						xtype:'panel',
						html:'',
						frame:false,
						border:false,
						width:20
					},{
		    		    	id:'c11',
			        		xtype:'checkbox',
			        		boxLabel : "显示记录总数",  
			        		name : "jlzs",  
			        		inputValue : "1"  
		    		},*/{
						xtype:'panel',
						html:'',
						frame:false,
						border:false,
						width:20
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
						xtype:'panel',
						html:'',
						frame:false,
						border:false,
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
						xtype:'panel',
						html:'',
						frame:false,
						border:false,
						width:10
					},{
		    				xtype:'button',
		    		    	id:'queryId',
		        			text:'查询',
		        			minWidth:60,
		        			handler:function(){
								//记录日志的操作码
		        				var data = form20026.getForm().getValues();
								if(Gnt.util.isEmpty(data)){
									showInfo("至少输入一个查询条件！");
									return;
								}
								data.log_code = "F5002";
								Ext.Msg.wait("正在执行查询，请稍后...");
								var ary = p1_1.xszdGrid.store.data.items;
								var pxary = p1_1.pxzdGrid.store.data.items;//length 长度;
								if(p1_1.qybz){
									bgspGrid.reconfigure(
											bgspGrid.store,new Ext.grid.ColumnModel(Gnt.ZdyColumn(ary)));
									bgspGrid.pxary = pxary;
								};
								bgspGrid.zdyValueKey = Gnt.zdyValueKey(ary);
								var header=[];
								var shuxing = [];
								var zdyValueKey = Gnt.zdyValueKey(ary);
								bgspGrid.type = 'bgspcx';
		        				var store = bgspGrid.store;
		        				store.removeAll();
		        				store.baseParams = data;
		        				var config=bgspGrid.colModel.config;
								Ext.each(config,function(r){
									header.push(r.header);
									shuxing.push(r.dataIndex);
								});
								store.baseParams.header = encodeURI(header);
								store.baseParams.shuxing = encodeURI(shuxing);
								store.baseParams.zdyValueKey = Ext.encode(zdyValueKey);
								store.baseParams.daochuFlag = 'bgspcx';
		        				applyParam(store);
		        				store.load({params:{start:0, limit:20}});
		        				Ext.each(pxary, function(pxObj){
		        					store.sort(pxObj.data.fieldname, 'ASC');
		    					});
		        				store.on('load',function(s,records){
		        					Ext.Msg.hide();
		        					if(store.getCount() > 0){
		        						Ext.getCmp('card').getLayout().setActiveItem(1);
		        						
		        						bgspGrid.fireEvent("rowclick",bgspGrid,0);
			        					bgspGrid.getSelectionModel().selectRange(0,0);
			        					
			        					if(hzywid != null){
			        						bgspGrid.fireEvent("rowdblclick",bgspGrid,0);
			        					}
		        					}else{
		        						showInfo("未查到有关信息！");
		        					}
		        				});
		        			}
		    		},{
						xtype:'panel',
						html:'',
						frame:false,
						border:false,
						width:10
					},{
		    				xtype:'button',
		        			text:'关闭',
		        			minWidth:60,
		        			handler:function(){
		        				if(window.parent.closeWorkSpace)
		        					window.parent.closeWorkSpace();
		        				else
		        					window.close();
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
					}
		    	]
	        }
		]
	});
	
	var bgspGrid = new Gnt.ux.SjpzGrid({
		pkname: 'spywid',
		pzlb: '20026',
		url:'cx/spcx/bgspcx/getBgsp.json',
		region:'north',
		height:300,
		title: '变更审批信息',
		dcFlag:true,
		showPaging:true,
		callCellmeta:function(r){
			if(r.data.lsbz=='0'){
				return '#C41A11';
			}else if(r.data.lsbz=='1'){
				return '#20BBAF';
			}
		},
		listeners:{
			rowclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				
				selectRynbid = selRes.data.rynbid;
				selectSpywid = selRes.data.spywid;
				selectHjywid = selRes.data.hjywid;
				
				var store = bgspzbGrid.store;
				store.removeAll();
				store.load({params:{start:0, limit:20, spywid:selRes.data.spywid}});
			},
			rowdblclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				bgspForm.getForm().loadRecord(selRes);
				bgspzbForm.getForm().loadRecord(bgspzbGrid.store.getAt(0));
				Ext.getCmp('card').getLayout().setActiveItem(2);
				
				bgspForm.doLayout();
				bgspzbForm.doLayout();
				//var pkvalue = selRes.data[qrspzbForm.bindStore.pkname];
//				if(Gnt.util.isEmpty(selpkvalue)){
//					showInfo("选中一个变更更正信息!");
//					return;
//				}
//				selectRynbid = selRes.data.rynbid;
				selectSpywid = selRes.data.spywid;
//				selectHjywid = selRes.data.hjywid;
				
				Ext.getCmp('zfId').enable();
				if((selRes.data.lsbz && selRes.data.lsbz == 1) || (selRes.data.spjg && selRes.data.spjg == 2)){
					Ext.getCmp('zfId').disable();
				}
				
//				var pkvalue = selRes.data[bgspForm.bindStore.pkname];
//				var re = bgspForm.bindStore.getById(pkvalue);
//				if(re){
//					bgspForm.getForm().loadRecord(re);
//				}
//				re = qrspzbForm.bindStore.getById(selpkvalue);
//				if(re){
//					qrspzbForm.getForm().loadRecord(re);
//				}
			}
		}
	});
	
	var bgspzbGrid = new Gnt.ux.SjpzGrid({
		pkname: 'bgzid',
		pzlb: '10029',
		url:'cx/spcx/bgspcx/getBgspzb.json',
		region:'center',
		title: '变更更正信息',
		showPaging:true,
		listeners:{
			rowclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				selpkvalue = selRes.data[bgspzbForm.bindStore.pkname];
			},
			rowdblclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				selpkvalue = selRes.data[bgspzbForm.bindStore.pkname];
			}
		}
	});

	var zhcx_dialog = new Gnt.ux.ZhcxDialog({
		pzlb : form20026.pzlb,
		grid:bgspGrid,
		callback: function(where){
			var ary = p1_1.xszdGrid.store.data.items;
			var pxary = p1_1.pxzdGrid.store.data.items;//length 长度;
			if(p1_1.qybz){
				bgspGrid.reconfigure(
						bgspGrid.store,new Ext.grid.ColumnModel(Gnt.ZdyColumn(ary)));
				bgspGrid.pxary = pxary;
			};
			bgspGrid.zdyValueKey = Gnt.zdyValueKey(ary);
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
			grid.type = 'bgspcx';
			Ext.each(ary,function(r){
				header.push(r.data.displayname);
				shuxing.push(r.data.fieldname);
			});
			data.header = encodeURI(header);
			data.shuxing = encodeURI(shuxing);
			data.zdyValueKey = Ext.encode(zdyValueKey);
			data.daochuFlag = 'bgspcx';
			
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
		border:false,
		frame:false,
		items:[
		       bgspGrid,
		       bgspzbGrid,
		       {
					bodyStyle:'padding:10px',
					border:false,
					frame: false,
			        	region:'south',
			        	height: 40,
						layout:'table',
			        	layoutConfig: {
			        			columns: 10
			        	},
			    		items:[{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			    		},{
			    				xtype:'button',
			    		    	id:'lcId',
			        			text:'审批流程',
			        			minWidth:80,
			        			handler:function(){
			        				var store = bgsplcGrid.store;
			        				store.removeAll();
			        				store.load({params:{start:0, limit:20, spywid:selectSpywid}});
			        				Ext.getCmp('card').getLayout().setActiveItem(3);
			        			}
			    		},{
			    			xtype:'button',
			        			text:'关闭',
			        			minWidth:60,
			        			handler:function(){
			        				window.parent.closeWorkSpace();
			        			}
			    		},{
			    				xtype:'button',
			        			text:'返回',
			        			minWidth:60,
			        			handler:function(){
			        				Ext.getCmp('card').getLayout().setActiveItem(0);
			        			}
			    		}]
		       }
		]
	});
	
	var bgspForm = new Gnt.ux.SjpzForm({
		closable: false,
		/*region:'north',
		height:500,*/
		pzlb: '20026',
		labelWidth : 160,
    	height: 380,
		cols:2,
//		bindStore:bgspGrid.store,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:bgspGrid,
		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
		changeDictCust:function(cmb,res){
			
			return;
		},
		title: ''
	});
	
	var bgspzbForm = new Gnt.ux.SjpzForm({
		closable: false,
		/*region:'north',
		height:500,*/
		pzlb: '10029',
		labelWidth : 120,
		cols:2,
		bindStore:bgspzbGrid.store,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		//bindViewGrid:bgspzbGrid,
		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
		changeDictCust:function(cmb,res){
			
			return;
		},
		title: '变更内容'
	});
	
	var p3 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[
			{
				id:"p3northId",
				border:false,
				frame: false,
				region:'north',
	        	height: 380,
				items:[bgspForm]
			},{
				id:"p3centerId",
				border:false,
				frame: false,
				region:'center',
	        	height: 220,
				items:[bgspzbForm]
			}
		],
		buttons:[
			{
    			text:'审批流程',
    			minWidth:60,
    			handler:function(){
    				var store = bgsplcGrid.store;
    				
    				store.removeAll();
    				
    				store.load({params:{start:0, limit:20, spywid:selectSpywid}});
    				
    				Ext.getCmp('card').getLayout().setActiveItem(3);
    			}
			},{
		    	id:'zfId',
    			text:'作废',
    			minWidth:60,
    			handler:function(){
    				if(window.confirm("您确定作废此笔审批信息吗?")){
    					var subdata = {
    						spywid: selectSpywid
    					};
    						
    					Gnt.submit(
    						subdata, 
    						"yw/spgl/hjsp/processBgspzfyw", 
    						"正在处理死亡注销信息，请稍后...", 
    						function(jsonData,params){
    							showInfo("作废成功！",function(){
    								
    							});
    						}
    					);
    						
    				}
    			}
			},{
    			text:'关闭',
    			minWidth:60,
    			handler:function(){
    				if(hzywid != null){
	        			window.close();
	        		}else{
	        			window.parent.closeWorkSpace();
	        		}
    			}
    		},{
    			text:'返回',
    			minWidth:60,
	        	disabled:hzywid != null?true:false,
    			handler:function(){
    				Ext.getCmp('card').getLayout().setActiveItem(1);
//    				v.doLayout();
    			}
    		}
		]
	});
	

	var bgsplcGrid = new Gnt.ux.SjpzGrid({
		pkname: 'splsid',
		pzlb: '20024',
		url:'cx/spcx/bgspcx/getBgsplc.json',
//		region:'north',
		height:400,
		title: '',
		showPaging:true,
		listeners:{
			rowclick:function(g, rowIndex, e){
				
			},
			rowdblclick:function(g, rowIndex, e){
				
			}
		}
	});
	
	var p4 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[
			{
				id:"p4CenterId",
				border:false,
				frame: false,
				region:'center',
				width:'100%',
	        	height: 400,
				items:[bgsplcGrid]
			},{
				id:"p4SouthId",
	        	region:'south',
	        	height:120,
	            border:false,
	            frame:false,
	            items:[{
	            	id:'clId',
	            	title: '审批材料',
	            	columnWidth: 3,
	            	xtype: 'fieldset',
	            	autoHeight: true,
	            	layout:'column'/*,
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
	            	}]*/
	            }]
				
			}
		],
		buttons:[
			{
    			text:'关闭',
    			minWidth:60,
    			handler:function(){
    				if(hzywid != null){
	        			window.close();
	        		}else{
	        			window.parent.closeWorkSpace();
	        		}
    			}
    		},{
    			text:'返回',
    			minWidth:60,
	        	disabled:hzywid != null?true:false,
    			handler:function(){
    				Ext.getCmp('card').getLayout().setActiveItem(1);
//    				v.doLayout();
    			}
    		}
		]
	});
	
	function applyParam(store){
		
		/**
			是否显示记录总数
		 */
		var zs = '';
//		if(Ext.getCmp('c11').getValue()){
//			zs = '1';
//		}
		
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
        	items:[p1,p2,p3,p4]
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
				
				form20026.getForm().findField("gmsfhm").setValue(jo.bsqrsfz);
				
				Ext.getCmp("queryId").handler();
				
			}
		});
	}
	
	/**
		按下回车键执行查询操作
		
	var key1 = new Ext.KeyMap(document, {
		key:Ext.EventObject.ENTER,
		fn:function() {
			var data = form20026.getForm().getValues();
			
			if(Gnt.util.isEmpty(data)){
				showInfo("至少输入一个查询条件！");
				return;
			}
			
			var store = bgspGrid.store;
			
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
    			bgspGrid.getView().getRow(girdcount).style.backgroundColor="red";
    		}else{
    			bgspGrid.getView().getRow(girdcount).style.backgroundColor="green";
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