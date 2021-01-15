var selRes = null;
var cxCount = 1;
var rynbid = null;

var selectRynbid = null;
var selectSpywid = null;
var selectHjywid = null;

Ext.onReady(function(){
	Ext.QuickTips.init();
	
	var hzywid = getQueryParam("hzywid");
	var pch = getQueryParam("pch");
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10019,20025,20024,10031",function(){}))
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
	
	var form20025 = new Gnt.ux.SjpzForm({
		pzlb: '20025',
		url: 'yw/queryRyxx',
		title:'',
		region:'center',
		cols:2,
		formType:'query'
	});
	var p1_1 = new Gnt.ux.ZdycxPanel({
		pzlb:form20025.pzlb,
		region:'center',
		layout:'border',
		border:false,
		frame: false,
		height:600
	});	
	var p1 = new Ext.Panel({
		layout:'border',
		items:[
			{
				id:"nextId",
				border:false,
				frame: false,
				region:'center',
				visible:false,
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
						html: '<div class="text" style="text-align:center;"><br /><h2>迁入审批</ h2></div>',
						height: 80,
						region:'north',
						bodyStyle:'padding:15px'
					},
					form20025
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
//						html: '<div class="text" style="text-align:center;"><br /><h2>迁入审批</ h2></div>',
//						height: 80,
//						region:'north',
//						bodyStyle:'padding:15px'
//					},
//					form20025
//				]
//			},
			{
				id:"southId",
	        	region:'south',
	        	height:50,
	           border:false,
	           frame:false,
	           bodyStyle:'padding:10px',
         	   layout:'table',
        	   layoutConfig: {
        			columns: 14
        	   },
	           items:[
					{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
					},{
						id:'zhId',
						xtype:'button',
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
		    	    	form:form20025,
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
				    	id:'nexId',
				    	xtype:'button',
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
				    	id:'queryId',
				    	xtype:'button',
		    			text:'查询',
		    			minWidth:60,
		    			handler:function(){
							//记录日志的操作码
		    				var data = form20025.getForm().getValues();
		    				data.log_code = "F5007";
//											if(Gnt.util.isEmpty(data)){
//												showInfo("至少输入一个查询条件！");
//												return;
//											}
		    				Ext.Msg.wait("正在执行查询，请稍后...");
		    				var ary = p1_1.xszdGrid.store.data.items;
							var pxary = p1_1.pxzdGrid.store.data.items;//length 长度;
							if(p1_1.qybz){
								qrspGrid.reconfigure(
										qrspGrid.store,new Ext.grid.ColumnModel(Gnt.ZdyColumn(ary)));
								qrspGrid.pxary = pxary;
							};
							qrspGrid.zdyValueKey = Gnt.zdyValueKey(ary);
							var header=[];
							var shuxing = [];
							var zdyValueKey = Gnt.zdyValueKey(ary);
							qrspGrid.type = 'qrspcx';
		    				var store = qrspGrid.store;
		    				store.removeAll();
		    				store.baseParams = data;
		    				var config=qrspGrid.colModel.config;
							Ext.each(config,function(r){
								header.push(r.header);
								shuxing.push(r.dataIndex);
							});
							store.baseParams.header = encodeURI(header);
							store.baseParams.shuxing = encodeURI(shuxing);
							store.baseParams.zdyValueKey = Ext.encode(zdyValueKey);
							store.baseParams.daochuFlag = 'qrspcx';
		    				applyParam(store);
		    				
		    				store.load({params:{start:0, limit:20}});
		    				Ext.each(pxary, function(pxObj){
	        					store.sort(pxObj.data.fieldname, 'ASC');
	    					});
		    				store.on('load',function(s,records){
		    					Ext.Msg.hide();
		    					if(store.getCount() > 0){
		    						Ext.getCmp('card').getLayout().setActiveItem(1);
		    						if(hzywid != null){
		    							qrspGrid.fireEvent("rowdblclick",qrspGrid,(store.getCount()-1));
		    						}else{
		    							qrspGrid.fireEvent("rowclick",qrspGrid,0);
			    						qrspGrid.getSelectionModel().selectRange(0,0);
		    						}
		    					}else{
		    						showInfo("未查到有关信息！");
		    						return false;
		    					}
		    					
		    					
		    					//p2.doLayout();
		    				});
		    			}
		    		},{
						xtype:'panel',
						html:'',
						frame:false,
						border:false,
						width:10
					},{
		    			text:'关闭',
		    			xtype:'button',
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
	
	var qrspGrid = new Gnt.ux.SjpzGrid({
		title: '迁入审批信息',
		pkname: 'rynbid',
		pzlb: '20025',
		region:'north',
		height:300,
		url:'cx/spcx/qrspcx/getQrsp.json',
		dcFlag:true,
		showPaging:true,
		callCellmeta:function(r){
			if(r.data.lsbz=='0'||r.data.spjg=='2'){
				return '#20BBAF';
			}else {
				return '#000000';
			}
		},
		listeners:{
			rowclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				
				if(selRes && selRes.data){
					selectRynbid = selRes.data.rynbid;
					selectSpywid = selRes.data.spywid;
					selectHjywid = selRes.data.hjywid;
				}
				
				var store = qrspzbGrid.store;
				store.removeAll();
				store.load({params:{start:0, limit:20, spywid:selectSpywid}});
			},
			rowdblclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				if(selRes && selRes.data){
					selectRynbid = selRes.data.rynbid;
					selectSpywid = selRes.data.spywid;
					selectHjywid = selRes.data.hjywid;
				}
				
				var store = qrspzbGrid.store;
				store.removeAll();
				store.load({params:{start:0, limit:20, spywid:selectSpywid}});
				store.on('load',function(s,records){
					if(records.length>0){
						qrspForm.getForm().loadRecord(selRes);
						qrspzbForm.getForm().loadRecord(qrspzbGrid.store.getAt(0));
						Ext.getCmp('card').getLayout().setActiveItem(2);
						/**
						判断选择记录是否已落实
						是:不许作废
						否:可以作废
						
						判断审批结果是否已作废
						是:不许作废
						否:可以作废
					 */
					
					if((selRes.data.lsbz && selRes.data.lsbz == 1) || (selRes.data.spjg && selRes.data.spjg == 2)){
						Ext.getCmp('zfId').disable();
					}else{
						Ext.getCmp('zfId').enable();
					}
					
					qrspForm.doLayout();
					qrspzbForm.doLayout();
					}
				});
				
//				if(selRes && selRes.data){
//					selectRynbid = selRes.data.rynbid;
//					selectSpywid = selRes.data.spywid;
//					selectHjywid = selRes.data.hjywid;
//					var pkvalue = selRes.data[qrspForm.bindStore.pkname];
//					var re = qrspForm.bindStore.getById(pkvalue);
//					if(re){
//						qrspForm.getForm().loadRecord(re);
//					}
//				}
				
				
			}
		}
	});
	
	var qrspzbGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		pzlb: '10031',
		region:'center',
		url:'cx/spcx/qrspcx/getQrspzb.json',
		title: '变动人员信息',
		showPaging:true,
		listeners:{
			rowclick:function(g, rowIndex, e){
				
			},
			rowdblclick:function(g, rowIndex, e){
				
			}
		}
	});

	var zhcx_dialog = new Gnt.ux.ZhcxDialog({
		pzlb : form20025.pzlb,
		grid:qrspGrid,
		callback: function(where){
			var ary = p1_1.xszdGrid.store.data.items;
			var pxary = p1_1.pxzdGrid.store.data.items;//length 长度;
			if(p1_1.qybz){
				qrspGrid.reconfigure(
						qrspGrid.store,new Ext.grid.ColumnModel(Gnt.ZdyColumn(ary)));
				qrspGrid.pxary = pxary;
			};
			qrspGrid.zdyValueKey = Gnt.zdyValueKey(ary);
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
			grid.type = 'qrspcx';
			Ext.each(ary,function(r){
				header.push(r.data.displayname);
				shuxing.push(r.data.fieldname);
			});
			data.header = encodeURI(header);
			data.shuxing = encodeURI(shuxing);
			data.zdyValueKey = Ext.encode(zdyValueKey);
			data.daochuFlag = 'qrspcx';
			
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
		frame: false,
		items:[
			qrspGrid,
			qrspzbGrid,
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
	        	items: [{
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
			        				var store = qrsplcGrid.store;
			        				store.removeAll();
			        				store.load({params:{start:0, limit:20, spywid:selectSpywid}});
			        				Ext.getCmp('card').getLayout().setActiveItem(3);
			        			}
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
			    				xtype:'button',
			        			text:'返回',
			        			minWidth:60,
			        			handler:function(){
			        				Ext.getCmp('card').getLayout().setActiveItem(0);
			        			}
			    		}
					]
			}
		]
	});
	
	var qrspForm = new Gnt.ux.SjpzForm({
		closable: false,
		/*region:'north',
		height:500,*/
		pzlb: '20025',
		labelWidth : 160,
    	height: 380,
		cols:2,
//		bindStore:qrspGrid.store,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:qrspGrid,
		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
		changeDictCust:function(cmb,res){
			
			return;
		},
		title: ''
	});
	
	var qrspzbForm = new Gnt.ux.SjpzForm({
		closable: false,
		/*region:'north',
		height:500,*/
		pzlb: '10031',
		labelWidth : 120,
		cols:2,
//		bindStore:qrspGrid.store,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:qrspzbGrid,
		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
		changeDictCust:function(cmb,res){
			
			return;
		},
		title: '变动人员信息'
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
				items:[qrspForm]
			},{
				id:"p3centerId",
				border:false,
				frame: false,
				region:'center',
	        	height: 220,
				items:[qrspzbForm]
			}
		],
		buttons:[
			{
		    	id:'qyzdyId',
    			text:'准迁证打印',
    			minWidth:60,
    			handler:function(){
    				zqzPrint(selectSpywid);
    				if(hzywid){
						Gnt.ux.Dict.getKzcs('10036', function(pz, user) {
							if(pz.kzz==1){
								pjq('ES',user.ip,hzywid);
							}
						});
					}
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
    						"yw/spgl/hjsp/processQrspzfyw", 
    						"正在进行作废操作，请稍后...", 
    						function(jsonData,params){
    							showInfo("作废成功！",function(){
    								//qrspGrid.store.reload();
    								selRes.set("spjg",'2');
    								Ext.getCmp('zfId').disable();
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
	

	var qrsplcGrid = new Gnt.ux.SjpzGrid({
		pkname: 'splsid',
		pzlb: '20024',
		url:'cx/spcx/qrspcx/getQrsplc.json',
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
				items:[qrsplcGrid]
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
				
				form20025.getForm().findField('gmsfhm').setValue(jo.bsqrsfz);
//				form20025.getForm().findField('gmsfhm').setValue('340702200306015017');
				
				Ext.getCmp('queryId').handler();
				
			}
		});
	}
	
	Gnt.toFormReadyonly(qrspForm);
	Gnt.toFormReadyonly(qrspzbForm);
	
	/**
		按下回车键执行查询操作
		
	var key1 = new Ext.KeyMap(document, {
		key:Ext.EventObject.ENTER,
		fn:function() {
			var data = form20025.getForm().getValues();
			
			if(Gnt.util.isEmpty(data)){
				showInfo("至少输入一个查询条件！");
				return;
			}
			
			var store = qrspGrid.store;
			
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
    			qrspGrid.getView().getRow(girdcount).style.backgroundColor="red";
    		}else{
    			qrspGrid.getView().getRow(girdcount).style.backgroundColor="green";
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