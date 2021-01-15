var selectYw = 1;
var queryFlag = null;
var rynbid = null;
var curIndex = 0;
var selectRynbid = "";
var selectHhnbid = "";
var selectHjywid = null;
var selectMlpnbid ="";
var hhrange = 1;
var dysx = 2;
var dygs ="cbzhengda";
var selRes = null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("20000",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX','CS_JCWH'],function(){});
	
	/**
		点击下一步后显示的内容
		暂时用户成员信息代替
	 */
	var czrkGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		pzlb: '20000',
		url: 'yw/bbdy/cbcpdy/getCbcpList.json',
    	region:'center',
		height:300,
    	title: '',
		//url: 'yw/common/queryRyxx.json',
    	showPaging:true,
		listeners:{
			rowclick:function(grid, rowIndex, e){
				selRes = grid.store.getAt(rowIndex);
				curIndex = rowIndex;
				if(selRes && selRes.data){
					selectRynbid = selRes.data.rynbid;
					selectHhnbid = selRes.data.hhnbid;
					selectMlpnbid = selRes.data.mlpnbid;
				}
    			
			},
			rowdblclick:function(grid,row){
				selRes = grid.store.getAt(row);
				curIndex = row;
				if(selRes && selRes.data){
					selectRynbid = selRes.data.rynbid;
					selectHhnbid = selRes.data.hhnbid;
					selectMlpnbid = selRes.data.mlpnbid;
				}
			}
		}
	});
	var p1 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[{
				id:"centerId",
				title: '',
	        	region:'center',
	        	xtype: 'fieldset',
	            autoHeight : true, 
	            labelWidth : 280,
	            bodyStyle : 'background-color:white',
		    	items: [{
					id: "panelHtmlId" ,
					html: '<div class="text" style="text-align:center;"><h2>常表户口打印</h2></div>',
					height: 80,
					region:'north',
					bodyStyle:'padding:15px'
				},{
					xtype: 'fieldset',
					autoHeight: true,
					height:480,
					bodyStyle:'padding:30px',
			        layout:'column',
					items:[{
		            	title: '区域',
		            	region:'center',
			            xtype: 'fieldset',
			            layout:'column',
			            width:'49%',
						height:100,
	            		bodyStyle : 'padding:0 0 0 20',
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
				                        		 if(Ext.getCmp('pcs').getValue()&&Ext.getCmp('pcs').getValue()!=""){
				                        			 Ext.getCmp('queryId').enable();
				                        		 }else{
				                        			 Ext.getCmp('queryId').disable();
				                        		 }
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
											Ext.getCmp('queryId').setDisabled(false);
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
				                        	 if(Ext.getCmp('s12').getValue()){
				                        		 Ext.getCmp('s11').setValue(false);
				                        		 Ext.getCmp('jwh').enable();
				                        		 Ext.getCmp('pcs').disable();
				                        		 if(Ext.getCmp('jwh').getValue()&&Ext.getCmp('jwh').getValue()!=""){
				                        			 Ext.getCmp('queryId').enable();
				                        		 }{
				                        			 Ext.getCmp('queryId').disable();
				                        		 }
				                        	 }
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
											Ext.getCmp('queryId').setDisabled(false);
										}
									},
									disabled:true,
									name:'jwhmc',
									maxLength:30,
									allowBlank:true,
									fieldLabel:''
								}]
			            }]
					},{
		            	border:false,
						frame: false,
						width:'2%'
		            },{
		            	title: '户号',
		            	region:'center',
			            xtype: 'fieldset',
			            layout:'column',
			            width:'49%',
						height:100,
	            		bodyStyle : 'padding:0 0 0 10',
			            items: [{title: '',
			            	region:'center',
				            //xtype: 'fieldset',
				            layout:'table',
				            items:[{
				            	id:'r11',
				            	xtype:'radio',
				            	boxLabel: '精确',
				            	name:'hh',
				            	inputValue : "1",
				            	checked:true,
				            	listeners:{
				            		'check' : function(checkbox, checked){ 
				                         if(checked){//只有在点击时触发
				                        	 if(Ext.getCmp('r11').getValue()){
				                        		 Ext.getCmp('hhto').disable();
				                        		 hhrange = 1;
				                        	 }
				                         }  
				                    }
				                //监听事件
					            }
				            },{
				    	    	height:10,
				    	    	border:false,
				    	    	width:80,
				    	    	frame:false
				    	    },{
				            	id:'r12',
				            	xtype:'radio',
				            	boxLabel: '范围',
				            	name:'hh',
				            	inputValue : "2",
				            	listeners:{
				            		'check' : function(checkbox, checked){ 
				                         if(checked){//只有在点击时触发
				                        	 if(Ext.getCmp('r12').getValue()){
				                        		 Ext.getCmp('hhto').enable();
				                        		 hhrange = 2;
				                        	 }
				                         }  
				                    }
				                //监听事件
					            }
				            }]},{
				            	title: '',
				            	region:'center',
					            //xtype: 'fieldset',
					            layout:'column',
					            
					            items:[{
				            		title:'',
				            		id:'hhfrom',
				            		columnWidth: .4,
			            			xtype : 'textfield',
			            			fieldLabel:"",
			            			name:'hhfrom'
					            },{
					            	html: "至",
					            	columnWidth: .2
								},{
				            		title:'',
				            		id:'hhto',
				            		columnWidth: .4,
				            		//bodyStyle : 'padding:15px',
				        			xtype : 'textfield',
				        			disabled:true,
				        			fieldLabel:"",
				        			name:'hhto'
								}]
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
		        				var data ={};
		        				if(Ext.getCmp('s11').getValue()){
		        					data.pcs= Ext.getCmp('pcs').getValue();
		        				}
		        				if(Ext.getCmp('s12').getValue()){
		        					data.pcs= Ext.getCmp('jwh').getValue();
		        				}
		        				data.hhfrom = Ext.getCmp('hhfrom').getValue();
		        				data.hhto = Ext.getCmp('hhto').getValue();
		        				data.hhrange = hhrange;
		        				//data.xm = Ext.getCmp('name').getValue();
		        				Ext.Msg.wait("正在执行查询，请稍后...");
		        				var store = czrkGrid.store;
	        					store.removeAll();
	        					store.baseParams = data;
	        					store.load({params:{start:0, limit:9999}});
	        					store.on('load',function(s,records){
	        						czrkGrid.fireEvent("rowclick",czrkGrid,0);
	        						czrkGrid.getSelectionModel().selectRange(0,0);
		        				});
	        					Ext.Msg.hide();
	        					Ext.getCmp('card').getLayout().setActiveItem(1);
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
//	var czrkGrid = new Gnt.ux.SjpzGrid({
//		pkname: 'hhnbid',
//		pzlb: '20001',
//		url: 'yw/bbdy/yhybdy/getYhybList.json',
////		region	:'center',
////		height:540,
//		title: '',
//		showPaging:false,
//		listeners:{
//			rowclick:function(g, rowIndex, e){}
//		}
//	});
	var p2 = new Ext.Panel({
		layout:'border',
		items:[{
			region: "north",
			title: '',
//			height:300,
			items:[czrkGrid] 
		},{ region: "east",
			width: 150,
			height:300,
			title: '',
			border:false,
	    	frame:false,
			items:[{
		    	region:'south',
		    	 width:180,
		    	 layout:'table',
		    	 layoutConfig: {
		    		columns: 1
		    	 },
		    	 items: [{
    	    		html:'',
    	    		bodyStyle:'padding:10px 25px 10px 20px',
	   	    	 	layout:'table',
	   	    	 	align:'east',
	   	    	 	border:false,
	   	    	 	frame:false,
	   	    	 	layoutConfig: {
	   	    	 		columns: 1
	   	    	 	},
	   	    	 	items:[{
			    	    	height:3,
			    	    	border:false,
			    	    	frame:false
			    	    },new Ext.Button({
							text:'人口信息',
							minWidth:100,
							handler:function(){
								if(selRes){
									czr={
											ryid:selRes.data.ryid,
											rynbid:selRes.data.rynbid,
											hhnbid:selRes.data.hhnbid
									}
									Gnt.toRyxx(czr);
								}else{
									alert("请选择一条有效数据！");
									return;
								}
//		        				var url = basePath + "cx/hjjbxx/ckxx?jumpToCzrkcx="+"1"+"&mlpnbid=" + selectMlpnbid;
//		    					if(parent && parent.openWorkSpace){
//		    						parent.openWorkSpace(url,  "常住人口查询", "_czrkcl_");
//		    					}else{
//		    						window.location.href = url;
//		    					}
							}
			    	    }),{
			    	    	height:3,
			    	    	border:false,
			    	    	frame:false
			    	    },new Ext.Button({
							text:'打印',
							minWidth:100,
							handler:function(){
								var store = czrkGrid.store;
								var data=store.data;
								var length=data.length;
								var array =[];
								for (var i=curIndex;i<length;i++){
									var o ={};
			        				o.dysx = dysx;
			        				o.gmsfhm = store.getAt(i).get('gmsfhm');
			        				o.rynbid = store.getAt(i).get('rynbid');
			        				o.directTable = dygs;
									array.push(o);
								}
//		        				if(dysx==2){
//		        					alert(1111);
//		        				}else if(dysx==1){
//		       					alert(2222);
//		        				}
								//alert(array.length);
		        				printfunction(0,array);
						}
			    	    }),{
			    	    	height:3,
			    	    	border:false,
			    	    	frame:false
			    	    },new Ext.Button({
							text:'关闭',
							minWidth:100,
							handler:function(){
								window.parent.closeWorkSpace();
							}
			    	    }),{
			    	    	height:3,
			    	    	border:false,
			    	    	frame:false
			    	    },new Ext.Button({
							text:'返回',
							minWidth:100,
							handler:function(){
								Ext.getCmp('card').getLayout().setActiveItem(0);
							}
						})
			    	 ]
		    	    }]
		    }]

		},{ 
				region: "west",
				width:200, 
				bodyStyle : 'background-color:white',
				bodyStyle : 'padding:10 10 10 10',
				title: '',
				items:[{
	            	title: '打印顺序',
	            	region:'west',
		            xtype: 'fieldset',
		            layout:'border',
		            width:'80%',
		            height:'30%',
            		bodyStyle : 'padding:10 10 10 10',
					defaults:{
		            	columnWidth: 1,
		            	fieldLabel: '',
		            	name: 'fs'
		            },
		            items: [{
		            	id:'a11',
		            	xtype:'radio',
		            	boxLabel: '连续打印',
		            	name:'fs1',
		            	inputValue : "1",
		            	listeners:{
		            		'check' : function(checkbox, checked){ 
		                         if(checked){//只有在点击时触发
		                        	 if(Ext.getCmp('a11').getValue()){
		                        		 dysx = 1;
		                        	 }
		                         }  
		                    }
		                //监听事件
			            }
		            },{
		    	    	height:10,
		    	    	border:false,
		    	    	frame:false
		    	    },{
		            	id:'a12',
		            	xtype:'radio',
		            	boxLabel: '逐人打印',
		            	name:'fs1',
		            	checked:true,
		            	inputValue : "2",
		            	listeners:{
		            		'check' : function(checkbox, checked){ 
		                         if(checked){//只有在点击时触发
		                        	 if(Ext.getCmp('a12').getValue()){
		                        		 dysx = 2;
		                        	 }
		                         }  
		                    }
		                //监听事件
			            }
		            }]
				},{
	            	title: '打印格式',
	            	region:'west',
		            xtype: 'fieldset',
		            layout:'border',
		            width:'80%',
		            height:'35%',
            		bodyStyle : 'padding:10 10 10 10',
					defaults:{
		            	columnWidth: 1,
		            	fieldLabel: '',
		            	name: 'fs2'
		            },
		            items: [{
		            	id:'b11',
		            	xtype:'radio',
		            	boxLabel: '套打常表',
		            	name:'fs2',
		            	inputValue : "1",
		            	listeners:{
		            		'check' : function(checkbox, checked){ 
		                         if(checked){//只有在点击时触发
		                        	 if(Ext.getCmp('b11').getValue()){
		                        		 dygs = 'cbtaoda';
		                        	 }
		                         }  
		                    }
		                //监听事件
			            }
		            },{
		    	    	height:10,
		    	    	border:false,
		    	    	frame:false
		    	    },{
		            	id:'b12',
		            	xtype:'radio',
		            	boxLabel: '整打常表',
		            	name:'fs2',
		            	checked:true,
		            	inputValue : "2",
		            	listeners:{
		            		'check' : function(checkbox, checked){ 
		                         if(checked){//只有在点击时触发
		                        	 if(Ext.getCmp('b12').getValue()){
		                        		 dygs = 'cbzhengda';
		                        	 }
		                         }  
		                    }
		                //监听事件
			            }
		            }]
				}]
			},{ region: "center", 
				split: true,
				border: true, 
				title:'',
				items:[{
					id: "" ,
					html: '<div class="text" style="text-align:left;">本打印按户号排序，如果<br>打印中断，可选择相应的<br>户号和人员，重新打印</div>',
					height: 80,
					border: false,
					region:'east',
					bodyStyle:'padding:25px'
				
				}] }	
            ]
	});
	//定义分页面板
	
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
        	items:[p1,p2]
        }
    });		
});