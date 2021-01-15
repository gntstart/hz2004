var selRes = null;
var cxCount = 1;
var rynbid = null;

var selectRynbid = null;
var selectSpywid = null;
var selectHjywid = null;

var queryForm = null;
var resultGrid = null;
var pageIndex = -1;

Ext.onReady(function(){
	Ext.QuickTips.init();
	
	var hzywid = getQueryParam("hzywid");
	var pch = getQueryParam("pch");
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10019,10037,20024,10031",function(){}))
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
	
	var form1 = new Gnt.ux.SjpzForm({
		pzlb: '10037',
		region:'center',
		title:'',
		cols:2,
		formType:'query'
	});
	
	var form2 = new Gnt.ux.SjpzForm({
		pzlb: '10016',
		title:'',
		cols:2,
		region:'center',
		formType:'query'
	});
	
	var form3 = new Gnt.ux.SjpzForm({
		pzlb: '10032',
		title:'',
		cols:2,
		region:'center',
		formType:'query'
	});
	
	var form4 = new Gnt.ux.SjpzForm({
		pzlb: '10021',
		title:'',
		cols:2,
		region:'center',
		formType:'query'
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
			},{
				id: "panelHtmlId" ,
				html: '<div class="text" style="text-align:center;"><br /><h2>审批查询</h2></div>',
				height: 80,
				region:'north',
				bodyStyle:'padding:15px'
			},
			{
				id:"centerId",
				border:false,
				frame: false,
				region:'center',
				items:[{
					id:"p1form1",
	    	        border:false,
	    	        frame:false,
					region:'south',
//					hidden:true,
					items:[form1]
				},
				{
					id:"p1form2",
	    	        border:false,
	    	        frame:false,
					region:'south',
					hidden:true,
					items:[form2]
				},
				{
					id:"p1form3",
	    	        border:false,
	    	        frame:false,
					region:'south',
					hidden:true,
					items:[form3]
				},
				{
					id:"p1form4",
	    	        border:false,
	    	        frame:false,
					region:'south',
					hidden:true,
					items:[form4]
				}]
			},
//			{
//				id:"centerId",
//				border:false,
//				frame: false,
//				region:'center',
//				layout:'border',
////	        	height: 300,
//				items:[
//					form1,form2,form3,form4
//				]
//			},
			{
				id:"southId",
	        	region:'south',
	        	height:100,
	            border:false,
	            frame:false,
	            items:[{
	            	id:'ywId',
	            	title: '业务类型选择',
//					columnWidth: 3,
		            xtype: 'fieldset',
		            autoHeight: true,
		            layout:'column',
		            defaults:{
		            	xtype:'radio',
		            	columnWidth: .2,
		            	fieldLabel: '',
		            	name: 'ywlx'
		            },
		            items: [{
		            	id:'r11',
		            	boxLabel: '迁入审批',
		            	inputValue : "1",  
        				listeners:{
        					'check': function(obj,checked){
        						if(checked){
        							changeForm('p1form1');
        							queryForm = form1;
        							resultGrid = qrspGrid;
        							pageIndex = 1;
        							
//        							Ext.getCmp("p2NorthId").show();
        							changeGrid(qrspGrid,qrspzbGrid);
        						}
        					}
        				}
		            },{
		            	id:'r12',
		            	boxLabel: '变更更正审批',
		            	inputValue : "2",  
        				listeners:{
        					'check': function(obj,checked){
        						if(checked){
        							changeForm('p1form2');
        							queryForm = form2;
        							resultGrid = bgspGrid;
        							pageIndex = 2;
        							
//        							Ext.getCmp("p2NorthId").show();
        							changeGrid(bgspGrid,bgspzbGrid);
        						}
        					}
        				}
		            },{
		            	id:'r13',
		            	boxLabel: '户别变更审批',
		            	inputValue : "3",  
        				listeners:{
        					'check': function(obj,checked){
        						if(checked){
        							changeForm('p1form2');
        							queryForm = form2;
        							resultGrid = hbbgspGrid;
        							pageIndex = 3;
        							
//        							Ext.getCmp("p2NorthId").hide();
        							
        							changeGrid(hbbgspGrid,null);
        							
        						}
        					}
        				}
		            },{
		            	id:'r14',
		            	boxLabel: '户籍补录审批',
		            	inputValue : "4",  
        				listeners:{
        					'check': function(obj,checked){
        						if(checked){
        							changeForm('p1form3');
        							queryForm = form3;
        							resultGrid = hjblspGrid;
        							pageIndex = 4;
        							
//        							Ext.getCmp("p2NorthId").hide();
        							
        							changeGrid(hjblspGrid,null);
        							
        						}
        					}
        				}
		            },{
		            	id:'r15',
		            	boxLabel: '户籍删除审批',
		            	inputValue : "5",  
        				listeners:{
        					'check': function(obj,checked){
        						if(checked){
        							changeForm('p1form2');
        							queryForm = form2;
        							resultGrid = hjscspGrid;
        							pageIndex = 5;
        							
//        							Ext.getCmp("p2NorthId").hide();
        							
        							changeGrid(hjscspGrid,null);
        							
        						}
        					}
        				}
		            }]
				},{
					border:false,
					frame: false,
		        	region:'south',
		        	height: 40,
		        	bodyStyle: 'padding:5px',
					layout:'table',
					layoutConfig: {
			        	columns: 20
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
			        			text:'读卡',
			        			minWidth:60,
			        			disabled:true,
			        			handler:function(){
			        				
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
			    		    	id:'fzId',
			        			text:'组合查询',
			        			minWidth:80,
			        			handler:function(){
			        				var zhcx_dialog = new Gnt.ux.ZhcxDialog({
			        					pzlb : queryForm.pzlb,
			        					grid:resultGrid,
			        					callback: function(where){
			        						var grid = this.grid;
			        						var store = grid.store;
			        						var length = 0;
			        						var data = {"where": where};
			        						store.removeAll();
											//记录日志的操作码
											if(grid.store.pzlb == '10030'){
												data.log_code = "F5007";
											}
											if(grid.store.pzlb == '10028'){
												data.log_code = "F5002";
											}
											if(grid.store.pzlb == '10034'){
												data.log_code = "F5025";
											}
											if(grid.store.pzlb == '10032'){
												data.log_code = "F5017";
											}
											if(grid.store.pzlb == '10033'){
												data.log_code = "F5023";
											}
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
			        								Ext.getCmp('card').getLayout().setActiveItem(pageIndex);
			        							}else{
			        								showInfo("没有找到符合条件的数据！");
			        							}
			        							Ext.Msg.hide();
			        						},grid); 			        						
			        					}
			        				});
			        				zhcx_dialog.getLayout();
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
			    		    	id:'c11',
				        		xtype:'checkbox',
				        		boxLabel : "只查询当前用户提交的信息",  
				        		name : "dqyh",  
				        		inputValue : "1",  
		        				listeners:{
		        					'check': function(obj,checked){
		        						if(checked){
		        							
		        						}
		        					}
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
			    		    items:[{
			    		    	id:'c21',
				        		xtype:'checkbox',
				        		boxLabel : "只显示未审批信息",  
				        		name : "wspxx",  
				        		inputValue : "1",  
		        				listeners:{
		        					'check': function(obj,checked){
		        						if(checked){
		        							
		        						}
		        					}
		        				}
				        	}]
			    		},{
			    			columnWidth: .3,
							border:false,
							frame: false
			    		},{
			    			columnWidth: .01,
							border:false,
							frame: false
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
									var data = queryForm.getForm().getValues();
									//记录日志的操作码
			        				if(resultGrid.store.pzlb == '10030'){
										data.log_code = "F5007";
									}
									if(resultGrid.store.pzlb == '10028'){
										data.log_code = "F5002";
									}
									if(resultGrid.store.pzlb == '10034'){
										data.log_code = "F5025";
									}
									if(resultGrid.store.pzlb == '10032'){
										data.log_code = "F5017";
									}
									if(resultGrid.store.pzlb == '10033'){
										data.log_code = "F5023";
									}
				    				/*
									if(Gnt.util.isEmpty(data)){
										showInfo("至少输入一个查询条件！");
										return;
									}
				    				*/
				    				var store = resultGrid.store;
				    				store.removeAll();
				    				store.baseParams = data;
				    				applyParam(store);
				    				
				    				Ext.Msg.wait("正在执行查询，请稍后...");
				    				store.load({params:{start:0, limit:20}});
				    				store.on('load',function(s,records){
				    					Ext.Msg.hide();
				    					if(store.getCount() > 0){
				    						
				    						Ext.getCmp('card').getLayout().setActiveItem(pageIndex);
//				    						v.doLayout();
				    						
				    						resultGrid.fireEvent("rowclick",resultGrid,0);
				    						resultGrid.getSelectionModel().selectRange(0,0);
				    						if(hzywid != null){
				    							resultGrid.fireEvent("rowdblclick",resultGrid,0);
				    						}
				    					}else{
				    						showInfo("未查到有关信息！");
				    						return false;
				    					}
				    					
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
					
				}]
	        }
		]
	});
		
	/**
		迁入审批开始
	 */
	var qrspGrid = new Gnt.ux.SjpzGrid({
		pkname: 'spywid',
		pzlb: '10030',
		url:'cx/spcx/qrspcx/getHjspQrsp.json',
		region:'center',
//		height:300,
		title: '',
		showPaging:true,
		callCellmeta:function(r){
			if(r.data.spjg=='2'){
				return '#20BBAF';
			}else{
				return '#000000';
			}
		},
		listeners:{
			rowclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				
				selectRynbid = selRes.data.rynbid;
				selectSpywid = selRes.data.spywid;
				selectHjywid = selRes.data.hjywid;
				
				var store = qrspzbGrid.store;
				
				store.removeAll();
				
				store.load({params:{start:0, limit:20, spywid:selRes.data.spywid}});
				
				var store1 = qrspzbxxGrid.store;
				
				store1.removeAll();
				
				store1.load({params:{start:0, limit:20, spywid:selRes.data.spywid}});
				
			},
			rowdblclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				
				selectRynbid = selRes.data.rynbid;
				selectSpywid = selRes.data.spywid;
				selectHjywid = selRes.data.hjywid;
				
				var pkvalue = selRes.data[qrspForm.bindStore.pkname];
				var re = qrspForm.bindStore.getById(pkvalue);
				if(re){
					qrspForm.getForm().loadRecord(re);
				}
				
				Ext.getCmp('card').getLayout().setActiveItem(6);
				p31.doLayout();
				
				/**
					2018.12.25
					新增	刁杰
					只有迁入没有设置落实后不允许作废
					至于具体原因无从得知
					
				 */
				Ext.getCmp('qrzfId').enable();
				if((selRes.data.lsbz && selRes.data.lsbz == 1) || (selRes.data.spjg && selRes.data.spjg == 2)){
					Ext.getCmp('qrzfId').disable();
				}
			}
		}
	});
	
	var qrspzbGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		pzlb: '10031',
		region:'south',
		url:'cx/spcx/qrspcx/getQrspzb.json',
		title: '',
		height:120,
		showPaging:true,
		listeners:{
			rowclick:function(g, rowIndex, e){
				
			},
			rowdblclick:function(g, rowIndex, e){
				
			}
		}
	});
	
	var p21 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[qrspGrid,qrspzbGrid],
		buttons:[
			new Ext.Button({
				id:'lcId',
				text:'审批流程',
				minWidth:75,
				handler:function(){
					var store = qrsplcGrid.store;
					store.removeAll();
					store.load({params:{start:0, limit:20, spywid:selectSpywid ,splx:2}});
					Ext.getCmp('card').getLayout().setActiveItem(11);
//					p41.doLayout();
				}
			}),
			new Ext.Button({
				id:'fhId',
				text:'返回',
				minWidth:75,
				handler:function(){
					Ext.getCmp('card').getLayout().setActiveItem(0);
				}
			}),
			new Ext.Button({
				id:'gbId',
				text:'关闭',
				minWidth:75,
				handler:function(){
					if(window.parent.closeWorkSpace){
						window.parent.closeWorkSpace();
					}else{
						window.close();
					}
				}
			})
		]
	});
	
	var qrspForm = new Gnt.ux.SjpzForm({
		title: '审批信息',
		closable: false,
		region:'center',
		/*height:500,*/
		pzlb: '10037',
		labelWidth : 160,
//    	height: 380,
		cols:2,
//		bindStore:qrspGrid.store,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:qrspGrid,
		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
		changeDictCust:function(cmb,res){
			
			return;
		}
	});
	
	var qrspzbxxGrid = new Gnt.ux.SjpzGrid({
		title: '审批子信息',
		pkname: 'rynbid',
		pzlb: '10031',
		region:'west',
//		width: 300,
		url:'cx/spcx/qrspcx/getQrspzb.json',
		height: 120,
		showPaging:true,
		listeners:{
			rowclick:function(g, rowIndex, e){
				
			},
			rowdblclick:function(g, rowIndex, e){
				
			}
		}
	});
	
	var p31 = new Ext.Panel({
		layout:'border',
		buttonAlign: 'left',
		newYewu: function(){
			
		},
		items:[qrspForm,{
    	   	region:'south',
    	   	layout:'border',
   			height:150,
   			items:[
   				qrspzbxxGrid, {
   					region:'center',
   		    	   	layout:'border',
   		   			border:false,
   		   			frame:false,
   		   			width:120,
    	        	  items:[{
    		            	id:'fwId',
    		            	title: '选择查询人员的范围',
//    						columnWidth: 3,
    			            xtype: 'fieldset',
    			            autoHeight: true,
    			            layout:'column'
    			            	
    	        	  }]
    	          }
    	    ]
		}/*,{
        	id:'spclId',
        	region:'east',
            border:false,
            frame:false,
        	title: '选择查询人员的范围',
//			columnWidth: 3,
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
            	id:'r21',
            	boxLabel: '查询所属派出所的人口',
            	name:'fw',
            	inputValue : "1",
            	checked:true
            },{
            	id:'r22',
            	boxLabel: '查询所属区县的人口',
            	name:'fw',
            	inputValue : "2"
            },{
            	id:'r23',
            	boxLabel: '查询所有人口',
            	name:'fw',
            	inputValue : "3"
            }]
        }*/
			/*{
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
	        	height: 120,
				items:[qrspzbxxGrid]
			}*/
		],
		buttons:[
			{
    			text:'打印审批表',
    			disabled:true,
    			minWidth:60,
    			handler:function(){
    			}
			},{
				id:'qrzfId',
    			text:'作废',
    			tooltip: '已落实业务无法作废!',
    			//tooltipType:"title",
    			minWidth:60,
    			handler:function(){
    				if(window.confirm("您确定作废此笔审批信息吗?")){
    					
    					var subdata = {
    						spywid: selectSpywid
    					};
    						
    					Gnt.submit(
    						subdata, 
    						"yw/spgl/hjsp/processQrspzfyw", 
    						"正在注销信息，请稍后...", 
    						function(jsonData,params){
    							showInfo("作废成功！",function(){
    								selRes.set("spjg",'2');
    								//qrspGrid.store.reload();
    								Ext.getCmp('qrzfId').disable();
    							});
    						}
    					);
    					
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
		title: '已审批流程',
		pkname: 'splsid',
		pzlb: '20024',
		url:'cx/spcx/qrspcx/getQrsplc.json',
		region:'center',
		showPaging:true
	});
	
	var p41 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[qrsplcGrid],
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
	/**
		迁入审批结束
	 */
	
	/**
		变更更正审批开始
	 */
	var bgspGrid = new Gnt.ux.SjpzGrid({
		title: '',
		pkname: 'spywid',
		pzlb: '10028',
		url:'cx/spcx/bgspcx/getHjspBggzsp.json',
		region:'center',
		showPaging:true,
		callCellmeta:function(r){
			if(r.data.spjg=='2'){
				return '#20BBAF';
			}else{
				return '#000000';
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
				
				var store1 = bgspzbxxGrid.store;
				store1.removeAll();
				store1.load({params:{start:0, limit:20, spywid:selRes.data.spywid}});
				
			},
			rowdblclick:function(g, rowIndex, e){
				
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				
				selectRynbid = selRes.data.rynbid;
				selectSpywid = selRes.data.spywid;
				selectHjywid = selRes.data.hjywid;
				/*
				var pkvalue = selRes.data[bgspForm.bindStore.pkname];
				var re = bgspForm.bindStore.getById(pkvalue);
				if(re){
					bgspForm.getForm().loadRecord(re);
				}
				*/
				bgspForm.getForm().loadRecord(selRes);
				
				Ext.getCmp('card').getLayout().setActiveItem(7);
				p32.doLayout();
				
				Ext.getCmp('bgzfId').enable();
				if((selRes.data.lsbz && selRes.data.lsbz == 1) || (selRes.data.spjg && selRes.data.spjg == 2)){
					Ext.getCmp('bgzfId').disable();
				}
				
			}
		}
	});
	
	var bgspzbGrid = new Gnt.ux.SjpzGrid({
		pkname: 'bgzid',
		pzlb: '10029',
		url:'cx/spcx/bgspcx/getBgspzb.json',
		region:'south',
		title: '',
		showPaging:true,
		height:120,
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
	
	var p22 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[bgspGrid,bgspzbGrid],
		buttons:[
			new Ext.Button({
				id:'lcId',
				text:'审批流程',
				minWidth:75,
				handler:function(){
					var store = bgsplcGrid.store;
					store.removeAll();
					store.load({params:{start:0, limit:20, spywid:selectSpywid ,splx:3}});
					Ext.getCmp('card').getLayout().setActiveItem(12);
				}
			}),
			new Ext.Button({
				id:'fhId',
				text:'返回',
				minWidth:75,
				handler:function(){
					Ext.getCmp('card').getLayout().setActiveItem(0);
				}
			}),
			new Ext.Button({
				id:'gbId',
				text:'关闭',
				minWidth:75,
				handler:function(){
					if(window.parent.closeWorkSpace){
						window.parent.closeWorkSpace();
					}else{
						window.close();
					}
				}
			})
		]
	});
	
	var bgspForm = new Gnt.ux.SjpzForm({
		title: '审批信息',
		closable: false,
		region:'center',
		/*height:500,*/
		pzlb: '10028',
		labelWidth : 160,
//    	height: 380,
		cols:2,
//		bindStore:qrspGrid.store,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:bgspGrid,
		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
		changeDictCust:function(cmb,res){
			
			return;
		}
	});
	var bgspzbxxGrid = new Gnt.ux.SjpzGrid({
		pkname: 'bgzid',
		pzlb: '10029',
		url:'cx/spcx/bgspcx/getBgspzb.json',
		region:'south',
		title: '',
		showPaging:true,
		height:150,
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
	var bggzWindow = new Gnt.ux.selectBggzDialog({
		//选择立户信息回调
		callback: function(arrayTemp,data){
			printfunction(0,arrayTemp,data);
		}
	});	
	var p32 = new Ext.Panel({
		layout:'border',
		buttonAlign: 'left',
		newYewu: function(){
			
		},
		items:[bgspForm,bgspzbxxGrid],
		buttons:[
			{
    			text:'打印审批表',
    			minWidth:60,
    			handler:function(){
    				var spywid = bgspForm.getForm().findField("spywid").getValue();
    				var store = bgspzbxxGrid.store;
    				bggzWindow.show();
    				bggzWindow.setDataStore(store);
    			}
			},{
				id:'bgzfId',
    			text:'作废',
    			tooltip: '已落实业务无法作废!',
    			minWidth:60,
    			handler:function(){
    				if(window.confirm("您确定作废此笔审批信息吗?")){
    					
    					var subdata = {
    						spywid: selectSpywid
    					};
    						
    					Gnt.submit(
    						subdata, 
    						"yw/spgl/hjsp/processBgspzfyw", 
    						"正在注销信息，请稍后...", 
    						function(jsonData,params){
    							showInfo("作废成功！",function(){
    								//bgspGrid.store.reload();
    								selRes.set("spjg",'2');
    								Ext.getCmp('bgzfId').disable();
    							});
    						}
    					);
    						
    				}
    			}
    		},{
    			text:'返回',
    			minWidth:60,
	        	disabled:hzywid != null?true:false,
    			handler:function(){
    				Ext.getCmp('card').getLayout().setActiveItem(2);
//    				v.doLayout();
    			}
    		}
		]
	});
	

	var bgsplcGrid = new Gnt.ux.SjpzGrid({
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
	
	var p42 = new Ext.Panel({
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
				id:"p42SouthId",
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
    				Ext.getCmp('card').getLayout().setActiveItem(2);
//    				v.doLayout();
    			}
    		}
		]
	});
	/**
		变更更正审批结束
	 */
	
	/**
		户别变更开始
	 */
	var hbbgspGrid = new Gnt.ux.SjpzGrid({
		title: '',
		pkname: 'spywid',
		pzlb: '10034',
		url:'cx/spcx/hbbgspcx/getHjspHbbg.json',
		region:'center',
//		height:540,
		showPaging:true,
		callCellmeta:function(r){
			if(r.data.spjg=='2'){
				return '#20BBAF';
			}else{
				return '#000000';
			}
		},
		listeners:{
			rowclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				
				selectRynbid = selRes.data.rynbid;
				selectSpywid = selRes.data.spywid;
				selectHjywid = selRes.data.hjywid;
				
				var store = qrspzbGrid.store;
				store.removeAll();
				store.load({params:{start:0, limit:20, spywid:selRes.data.spywid}});
			},
			rowdblclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				
				selectRynbid = selRes.data.rynbid;
				selectSpywid = selRes.data.spywid;
				selectHjywid = selRes.data.hjywid;
				/*
				var pkvalue = selRes.data[hbbgForm.bindStore.pkname];
				var re = hbbgForm.bindStore.getById(pkvalue);
				if(re){
					hbbgForm.getForm().loadRecord(re);
				}
				*/
				hbbgForm.getForm().loadRecord(selRes);
				
				Ext.getCmp('card').getLayout().setActiveItem(8);
				p33.doLayout();
				
				Ext.getCmp('hbbgzfId').enable();
				if((selRes.data.lsbz && selRes.data.lsbz == 1) || (selRes.data.spjg && selRes.data.spjg == 2)){
					Ext.getCmp('hbbgzfId').disable();
				}
				
			}
		}
	});
	
	var p23 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[hbbgspGrid],
		buttons:[
			new Ext.Button({
				id:'lcId',
				text:'审批流程',
				minWidth:75,
				handler:function(){
					var store = hbbgsplcGrid.store;
					store.removeAll();
					store.load({params:{start:0, limit:20, spywid:selectSpywid ,splx:4}});
					Ext.getCmp('card').getLayout().setActiveItem(13);
				}
			}),
			new Ext.Button({
				id:'fhId',
				text:'返回',
				minWidth:75,
				handler:function(){
					Ext.getCmp('card').getLayout().setActiveItem(0);
				}
			}),
			new Ext.Button({
				id:'gbId',
				text:'关闭',
				minWidth:75,
				handler:function(){
					if(window.parent.closeWorkSpace){
						window.parent.closeWorkSpace();
					}else{
						window.close();
					}
				}
			})
		]
	});
	
	var hbbgForm = new Gnt.ux.SjpzForm({
		closable: false,
		pzlb: '20032',
		labelWidth : 160,
    	height: 380,
		cols:2,
		formType: 'view',
		bindViewGrid:hbbgspGrid,
		title: '户别变更审批信息'
	});
	
	var p33 = new Ext.Panel({
		layout:'fit',
		buttonAlign: 'left',
		items:[hbbgForm],
		buttons:[
			{
    			text:'打印审批表',
    			disabled:true,
    			minWidth:60,
    			handler:function(){
    				
    			}
			},{
		    	id:'hbbgzfId',
    			text:'作废',
    			tooltip: '已落实业务无法作废!',
    			minWidth:60,
    			handler:function(){
    				if(window.confirm("您确定作废此笔审批信息吗?")){
    					var subdata = {
    						spywid: selectSpywid
    					};
    						
    					Gnt.submit(
    						subdata, 
    						"yw/spgl/hjsp/processHbbgspzfyw", 
    						"正在注销信息，请稍后...", 
    						function(jsonData,params){
    							showInfo("作废成功！",function(){
    								//hbbgspGrid.store.reload(); 	
    								selRes.set("spjg",'2');
    								Ext.getCmp('hbbgzfId').disable();
    							});
    						}
    					);
    						
    				}
    			}
			},{
    			text:'返回',
    			minWidth:60,
	        	disabled:hzywid != null?true:false,
    			handler:function(){
    				Ext.getCmp('card').getLayout().setActiveItem(3);
    			}
    		}
		]
	});
	
	var hbbgsplcGrid = new Gnt.ux.SjpzGrid({
		pkname: 'splsid',
		pzlb: '20024',
		url:'cx/spcx/hbbgspcx/getHbbgsplc.json',
		region:'center',
		height:400,
		title: '审批信息',
		showPaging:true,
		listeners:{
			rowclick:function(g, rowIndex, e){
				
			},
			rowdblclick:function(g, rowIndex, e){
				
			}
		}
	});
	
	var p43 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[
			hbbgsplcGrid,{
				id:"p43SouthId",
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
    				Ext.getCmp('card').getLayout().setActiveItem(3);
//    				v.doLayout();
    			}
    		}
		]
	});
	/**
		户别变更结束
	 */
	
	/**
		户籍补录开始
	 */
	var hjblspGrid = new Gnt.ux.SjpzGrid({
		pkname: 'spywid',
		pzlb: '10032',
		url:'cx/spcx/hjblspcx/getHjspHjbl.json',
		region:'center',
//		height:540,
		title: '',
		showPaging:true,
		callCellmeta:function(r){
			if(r.data.spjg=='2'){
				return '#20BBAF';
			}else{
				return '#000000';
			}
		},
		listeners:{
			rowclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				
				selectRynbid = selRes.data.rynbid;
				selectSpywid = selRes.data.spywid;
				selectHjywid = selRes.data.hjywid;
				
			},
			rowdblclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				
				selectRynbid = selRes.data.rynbid;
				selectSpywid = selRes.data.spywid;
				selectHjywid = selRes.data.hjywid;
				
				hjblForm.getForm().loadRecord(selRes);
				
				Ext.getCmp('hjblzfId').enable();
				if((selRes.data.lsbz && selRes.data.lsbz == 1) || (selRes.data.spjg && selRes.data.spjg == 2)){
					Ext.getCmp('hjblzfId').disable();
				}
				
				Ext.getCmp('card').getLayout().setActiveItem(9);
				p34.doLayout();
				
			}
		}
	});
	
	var hjblspzbGrid = new Gnt.ux.SjpzGrid({
		pkname: 'bgzid',
		pzlb: '10029',
		url:'cx/spcx/bgspcx/getBgspzb.json',
		region:'south',
		title: '',
		height:120,
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
	
	var p24 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[hjblspGrid/*,hjblspzbGrid 20181115 */],
		buttons:[
			new Ext.Button({
				id:'lcId',
				text:'审批流程',
				minWidth:75,
				handler:function(){
					var store = hjblsplcGrid.store;
					store.removeAll();
					store.load({params:{start:0, limit:20, spywid:selectSpywid ,splx:5}});
					Ext.getCmp('card').getLayout().setActiveItem(14);
				}
			}),
			new Ext.Button({
				id:'fhId',
				text:'返回',
				minWidth:75,
				handler:function(){
					Ext.getCmp('card').getLayout().setActiveItem(0);
				}
			}),
			new Ext.Button({
				id:'gbId',
				text:'关闭',
				minWidth:75,
				handler:function(){
					if(window.parent.closeWorkSpace){
						window.parent.closeWorkSpace();
					}else{
						window.close();
					}
				}
			})
		]
	});
	
	var hjblForm = new Gnt.ux.SjpzForm({
		closable: false,
		formType: 'view',
		/*region:'north',
		height:500,*/
		pzlb: '10032',
		labelWidth : 160,
    	height: 380,
		cols:2,
//		bindStore:hjblspGrid.store,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:hjblspGrid,
		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
		changeDictCust:function(cmb,res){
			return;
		},
		title: '户籍补录审批信息'
	});
	
	var hjblzbxxForm = new Gnt.ux.SjpzForm({
		closable: false,
		region:'center',
		pzlb: '10031',
		labelWidth : 120,
		cols:2,
		bindViewGrid:qrspzbGrid,
		title: '变动人员信息'
	});
	
	var p34 = new Ext.Panel({
		layout:'border',
		buttonAlign: 'left',
		items:[hjblForm],
		buttons:[
			{
    			text:'打印审批表',
    			minWidth:60,
    			region:'center',
    			handler:function(){
    				var formdata = hjblForm.getForm().getValues();
    				formdata.directTable='hjblspb';
    				formdata.flag='2';
    				hjblidArray=[];
    				hjblidArray.push(formdata);
    				printfunction(0,hjblidArray,hjblidArray);
    			}
			},{
		    	id:'hjblzfId',
    			text:'作废',
    			tooltip: '已落实业务无法作废!',
    			minWidth:60,
    			handler:function(){
    				if(window.confirm("您确定作废此笔审批信息吗?")){
    					var subdata = {
    						spywid: selectSpywid
    					};
    						
    					Gnt.submit(
    						subdata, 
    						"yw/spgl/hjsp/processHjblspzfyw", 
    						"正在注销信息，请稍后...", 
    						function(jsonData,params){
    							showInfo("作废成功！",function(){
    								selRes.set("spjg",'2');
    								//hjblspGrid.store.reload();
    								Ext.getCmp('hjblzfId').disable();
    							});
    						}
    					);
    						
    				}
    			}
			},{
    			text:'返回',
    			minWidth:60,
	        	disabled:hzywid != null?true:false,
    			handler:function(){
    				Ext.getCmp('card').getLayout().setActiveItem(4);
//    				v.doLayout();
    			}
    		}
		]
	});
	

	var hjblsplcGrid = new Gnt.ux.SjpzGrid({
		pkname: 'splsid',
		pzlb: '20024',
		url:'cx/spcx/hjblspcx/getHjblsplc.json',
		region:'center',
		height:400,
		title: '审批信息',
		showPaging:true,
		listeners:{
			rowclick:function(g, rowIndex, e){
				
			},
			rowdblclick:function(g, rowIndex, e){
				
			}
		}
	});
	
	var p44 = new Ext.Panel({
		layout:'border',
		items:[
			hjblsplcGrid,
			{
				id:"p44SouthId",
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
    				Ext.getCmp('card').getLayout().setActiveItem(4);
//    				v.doLayout();
    			}
    		}
		]
	});
	/**
		户籍补录结束
	 */
	
	/**
		户籍删除开始
	 */
	var hjscspGrid = new Gnt.ux.SjpzGrid({
		pkname: 'spywid',
		pzlb: '10033',
		url:'cx/spcx/hjscspcx/getHjspHjsc.json',
		region:'center',
//		height:540,
		title: '',
		showPaging:true,
		callCellmeta:function(r){
			if(r.data.spjg=='2'){
				return '#20BBAF';
			}else{
				return '#000000';
			}
		},
		listeners:{
			rowclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				
				selectRynbid = selRes.data.rynbid;
				selectSpywid = selRes.data.spywid;
				selectHjywid = selRes.data.hjywid;
			},
			rowdblclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				
				selectRynbid = selRes.data.rynbid;
				selectSpywid = selRes.data.spywid;
				selectHjywid = selRes.data.hjywid;
				
				Ext.getCmp('hjsczfId').enable();
				if((selRes.data.lsbz && selRes.data.lsbz == 1) || (selRes.data.spjg && selRes.data.spjg == 2)){
					Ext.getCmp('hjsczfId').disable();
				}
				/*
				var pkvalue = selRes.data[hjscspForm.bindStore.pkname];
				var re = hjscspForm.bindStore.getById(pkvalue);
				if(re){
					hjscspForm.getForm().loadRecord(re);
				}
				*/
				hjscspForm.getForm().loadRecord(selRes);
				
				Ext.getCmp('card').getLayout().setActiveItem(10);
				p35.doLayout();
				
			}
		}
	});
	
	var p25 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[hjscspGrid],
		buttons:[
			new Ext.Button({
				id:'lcId',
				text:'审批流程',
				minWidth:75,
				handler:function(){
					var store = hjscsplcGrid.store;
					store.removeAll();
					store.load({params:{start:0, limit:20, spywid:selectSpywid ,splx:6}});
					Ext.getCmp('card').getLayout().setActiveItem(15);
				}
			}),
			new Ext.Button({
				id:'fhId',
				text:'返回',
				minWidth:75,
				handler:function(){
					Ext.getCmp('card').getLayout().setActiveItem(0);
				}
			}),
			new Ext.Button({
				id:'gbId',
				text:'关闭',
				minWidth:75,
				handler:function(){
					if(window.parent.closeWorkSpace){
						window.parent.closeWorkSpace();
					}else{
						window.close();
					}
				}
			})
		]
	});
	
	var hjscspForm = new Gnt.ux.SjpzForm({
		closable: false,
		/*region:'north',
		height:500,*/
		pzlb: '10033',
		labelWidth : 160,
    	height: 380,
		cols:2,
//		bindStore:hjscspGrid.store,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:hjscspGrid,
		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
		changeDictCust:function(cmb,res){
			
			return;
		},
		title: ''
	});
	
	var p35 = new Ext.Panel({
		layout:'border',
		buttonAlign: 'left',
		newYewu: function(){
			
		},
		items:[hjscspForm
			/*{
				id:"p3northId",
				border:false,
				frame: false,
				region:'north',
	        	height: 480,
				items:[hjscspForm]
			},{
				id:"p3centerId",
				border:false,
				frame: false,
				region:'center',
	        	height: 120
			}*/
		],
		buttons:[
			{
		    	id:'hjscSpbdyId',
    			text:'打印审批表',
    			minWidth:60,
    			handler:function(){
    				var formdata = hjscspForm.getForm().getValues();
    				formdata.directTable='hjscspb';
    				formdata.rynbid=formdata.spywid;
    				arrayTemp=[];
    				arrayTemp.push(formdata);
    				printfunction(0,arrayTemp);
    			}
			},{
		    	id:'hjsczfId',
    			text:'作废',
    			tooltip: '已落实业务无法作废!',
    			minWidth:60,
    			handler:function(){
    				if(window.confirm("您确定作废此笔审批信息吗?")){
    					var subdata = {
    						spywid: selectSpywid
    					};
    						
    					Gnt.submit(
    						subdata, 
    						"yw/spgl/hjsp/processHjscspzfyw", 
    						"正在注销信息，请稍后...", 
    						function(jsonData,params){
    							showInfo("作废成功！",function(){
    								selRes.set("spjg",'2');
    								//hjscspGrid.store.reload();
    								Ext.getCmp('hjsczfId').disable();
    							});
    						}
    					);
    						
    				}
    			}
			},{
    			text:'返回',
    			minWidth:60,
	        	disabled:hzywid != null?true:false,
    			handler:function(){
    				Ext.getCmp('card').getLayout().setActiveItem(5);
//    				v.doLayout();
    			}
    		}
		]
	});
	

	var hjscsplcGrid = new Gnt.ux.SjpzGrid({
		pkname: 'splsid',
		pzlb: '20024',
		url:'cx/spcx/hjscspcx/getHjscsplc.json',
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
	
	var p45 = new Ext.Panel({
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
				items:[hjscsplcGrid]
			},{
				id:"p45SouthId",
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
    				Ext.getCmp('card').getLayout().setActiveItem(5);
//    				v.doLayout();
    			}
    		}
		]
	});
	/**
		户籍删除结束
	 */
	
	
	
	function applyParam(store){
		
		/**
			只查询当前用户提交的信息
		 */
		var yh = '';
		if(Ext.getCmp('c11').getValue()){
			yh = '1';
		}
		
		/**
			只显示未审批信息
		 */
		var wsp = '';
		if(Ext.getCmp('c21').getValue()){
			wsp = '0';
		}
		
		Ext.apply(store.baseParams, {dqyh:yh,wsp:wsp/*,sjfw:" 1 = 1 "*/});
	}
	
	function getRynbid(rynbid){
		if(rynbid != null){
			return rynbid;
		}else{
			return getQueryParam("rynbid");
		}
	}
	
	function changeForm(form){
		
		Ext.getCmp('p1form1').setVisible(false);
		form1.getForm().reset();
		Ext.getCmp('p1form2').setVisible(false);
		form2.getForm().reset();
		Ext.getCmp('p1form3').setVisible(false);
		form3.getForm().reset();
		//form4.setVisible(false);
		//form4.getForm().reset();
		
		//form.setVisible(true);
		Ext.getCmp(form).setVisible(true);
		Ext.getCmp(form).doLayout();
		selectRynbid = null;
		selectSpywid = null;
		selectHjywid = null;
		
	}
	
	function changeGrid(grid,gridzb){
		
		qrspGrid.hide();
		qrspzbGrid.hide();
		
		bgspGrid.hide();
		bgspzbGrid.hide();
		
		hbbgspGrid.hide();
		
		hjblspGrid.hide();
		
		hjscspGrid.hide();
		
		grid.show();
		
		if(gridzb){
			gridzb.show();
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
        	items:[p1,p21,p22,p23,p24,p25,p31,p32,p33,p34,p35,p41,p42,p43,p44,p45]
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
				
				form10037.getForm().findField("gmsfhm").setValue(jo.bsqrsfz);
				
				Ext.getCmp("queryId").handler();
				
			}
		});
	}
	
	Ext.getCmp('r11').setValue(true);

});