var zpid=null;
var selRes =null;
var lczjslselRes = null;
var selectedSbxxid = null;
var kzflag = false;
var selyxqxzjrq = null;
var selRynbid = null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10016","30038",function(){}))
		return;
	
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_EDZSLZT','CS_EDZSLYY','CS_EDZLZFS','CS_EDZZZLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	var form10016 = new Gnt.ux.SjpzForm({
		pzlb: '10016',//30037
		title:'',
		region:'north',
		cols:2,
		formType:'query'
	});	
	var form30038 = new Gnt.ux.SjpzForm({
		pzlb: '30038',
		title:'',
		labelWidth:120,
		region:'north',
		cols:2,
		formType:'query'
	});		
	var form30003 = new Gnt.ux.SjpzForm({
		pzlb: '30003',
		title:'',
		labelWidth:120,
		region:'north',
		cols:2,
	    fieldChange:function(field){
	    	var f = field.findParentByType("sjpz_form");
	    	var yxqxqsrq=f.getForm().findField("yxqxqsrq").getValue();	
	    	var yxqxjzrq=f.getForm().findField("yxqxjzrq").getValue();	
	    	var slyy=f.getForm().findField("slyy").getValue();	
	    	var zzlx=f.getForm().findField("zzlx").getValue();	
	    	var lqfs=f.getForm().findField("lqfs").getValue();	
	    	var sflx=f.getForm().findField("sflx").getValue();	
	    	var sfje=f.getForm().findField("sfje").getValue();
	    	if(yxqxqsrq!=""&&yxqxjzrq!=""&slyy!=""&&zzlx!=""&&lqfs!=""&&sflx!=""&&sfje!=""){
	    		Ext.getCmp('bc').enable();
	    	}else{
	    		Ext.getCmp('bc').disable();
	    	}
	    	
	   }
	});
	var form30011 = new Gnt.ux.SjpzForm({
		pzlb: '30011',
		title:'',
		labelWidth:120,
		region:'north',
		cols:2
	});
	var zpWindow = null;
	var zjslGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		region:'center',
		url:'yw/edzzjgl/zjsl/queryZjslxx',
		pzlb: '20000',
		title: '',
		height:100,
		showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				slzt = selRes.data.slzt;
				selectedSbxxid = selRes.data.sbxxid;
				if(selRes){
					Ext.getCmp('zjlb').setValue(Gnt.ux.Dict.getDictLable('CS_ZJLB', selRes.data.zjlb));
					var zpid = selRes.data.zpid;
					if(zpid &&　zpid != 0){
						Ext.getCmp('picImage').body.update("<IMG SRC='yw/common/img/render/" + zpid + "' width='100%' height='100%' />");
						//Ext.getCmp('picImage1').body.update("<IMG SRC='yw/common/img/render/" + zpid + "' width='100%' height='100%' />");
					}else{
						Ext.getCmp('picImage').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
						//Ext.getCmp('picImage1').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
					}
					if(getQueryParam("jumpToZjsl")=="cxbz"){
						zjbl_window.show();
						zjbl_window.setSelRes(selRes);
					}  
					
					zpWindow = new Gnt.ux.SelectZpAll({
						ryid:selRes.data.ryid
					});
					var store = lczjslGrid.store;
					store.removeAll();
					store.baseParams = {
		   					ryid:selRes.data.ryid,
							log_code: "F3401"
					};
					store.load({params:{start:0, limit:20}});
					store.on('load',function(s,records){
						if(records.length>0){
							kzflag = true;
							curIndex = 0;
							Ext.getCmp('tqxx').enable();
							lczjslGrid.fireEvent("rowclick",lczjslGrid,curIndex);
							lczjslGrid.getSelectionModel().selectRange(curIndex,curIndex);
						}else{
							kzflag = false;
							Ext.getCmp('tqxx').disable();
						}
					});
				}
    		}
		}
	});
	
	var zhcx_dialog = new Gnt.ux.ZhcxDialog({
		pzlb : '20000',
		grid:zjslGrid,
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
					//Ext.getCmp('card').getLayout().setActiveItem(1);
				}else{
					showInfo("没有找到符合条件的数据！");
				}
				Ext.Msg.hide();
			},grid); 			        						
		}
	});		
	var lczjslGrid = new Gnt.ux.SjpzGrid({
		pkname: 'nbslid',
		region:'center',
		url:'yw/edzzjgl/zjsl/queryLsZjslxx',
		pzlb: '30017',
		title: '',
		showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){
				lczjslselRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				slzt = lczjslselRes.data.slzt;
				selectedSbxxid = lczjslselRes.data.sbxxid;
				if(lczjslselRes){
					//Ext.getCmp('zjlb').setValue(Gnt.ux.Dict.getDictLable('CS_ZJLB', lczjslselRes.data.zjlb));
					var zpid = lczjslselRes.data.zpid;
					if(zpid &&　zpid != 0){
						Ext.getCmp('picImage3').body.update("<IMG SRC='yw/common/img/render/" + zpid + "' width='100%' height='100%' />");
					}else{
						Ext.getCmp('picImage3').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
					}
//					zpWindow = new Gnt.ux.SelectZpAll({
//						ryid:lczjslselRes.data.ryid
//					});
				}

				
    		}
		}
	});

	var zjbl_window = new Gnt.ux.ZjblDialog({
		//选择立户信息回调
		callback: function(optype, fhdata){
			Ext.getCmp('card').getLayout().setActiveItem(1);
			Ext.getCmp('xm').setValue(selRes.data.xm);
			Ext.getCmp('gmsfhm').setValue(selRes.data.gmsfhm);
			Ext.getCmp('xb').setValue(Gnt.ux.Dict.getDictLable('CS_XB', selRes.data.xb));
			Ext.getCmp('csrq').setValue(selRes.data.csrq);
			Ext.getCmp('mz').setValue(Gnt.ux.Dict.getDictLable('CS_MZ', selRes.data.mz));
			Ext.getCmp('pcs').setValue(Gnt.ux.Dict.getDictLable('DWXXB', selRes.data.pcs));
			var zzTemp = Gnt.ux.Dict.getDictLable('XZQHB', selRes.data.ssxq)+Gnt.ux.Dict.getDictLable('XZJDXXB', selRes.data.xzjd)+Gnt.ux.Dict.getDictLable('JLXXXB', selRes.data.jlx)+selRes.data.mlph+selRes.data.mlxz;
//			alert(Gnt.ux.Dict.getDictLable('XZQHB', selRes.data.ssxq));
//			alert(Gnt.ux.Dict.getDictLable('XZJDXXB', selRes.data.xzjd));
//			alert(Gnt.ux.Dict.getDictLable('JLXXXB', selRes.data.jlx));
			Ext.getCmp('zz').setValue(zzTemp);
			form30003.getForm().reset();
			form30011.getForm().reset();
			
			form30003.fieldSetValue(form30003.getForm().findField("yxqxqsrq"),fhdata.yxqxqsrq);
			form30003.fieldSetValue(form30003.getForm().findField("yxqxjzrq"),fhdata.yxqxjzrq);
			selyxqxzjrq = fhdata.yxqxjzrq;
			selRynbid = selRes.data.rynbid;
			form30011.fieldSetValue(form30011.getForm().findField("sjrxm"),selRes.data.xm);
			form30011.fieldSetValue(form30011.getForm().findField("sjrtxdz"),zzTemp);
			Gnt.photo.setPhoto(null, true);
//			if(!kzflag){
//				form30003.fieldSetValue(form30003.getForm().findField("yxqxqsrq"),fhdata.qfrq);
//				form30003.fieldSetValue(form30003.getForm().findField("yxqxjzrq"),fhdata.qfrq);
//				form30011.fieldSetValue(form30011.getForm().findField("sjrxm"),selRes.data.xm);
//				form30011.fieldSetValue(form30011.getForm().findField("sjrtxdz"),zzTemp);
//			}else{
//				//form30003.getForm().reset();
//				//form30011.getForm().reset();
//				//form30011.remove;
//			}
			p2.doLayout();
			//提交数据
//			Gnt.submit(
//					dwxxAdd, 
//					"gl/xtmbgl/dwxxwh/addDwDm", 
//					"正在新增单位信息，请稍后...", 
//					function(jsonData,params){
//						showInfo("单位信息新增成功！");
//						errorFlag = true;
//						dwxxGrid.store.reload(); 
//					},
//					function(jsonData,params){
//						alert(jsonData.message);
//						errorFlag = false;
//					}
//			);
		}
	});	
	var kzsjr_window = new Gnt.ux.KzsjrSetDialog({
		//选择立户信息回调
		callback: function(optype, fhdata){
			if(kzflag){
				form30011.fieldSetValue(form30011.getForm().findField("sjrxm"),fhdata.pcsmc);
				form30011.fieldSetValue(form30011.getForm().findField("sjryb"),fhdata.pcsyb);
				form30011.fieldSetValue(form30011.getForm().findField("sjrlxdh"),fhdata.lxdh);
				form30011.fieldSetValue(form30011.getForm().findField("sjrtxdz"),fhdata.pcsdz);
			}
		}
	});	
var p1=new Ext.Panel({
	layout:'border',
	defaults: {
	    collapsible: true,
	    split: true,
	    bodyStyle: 'padding:15px'
	},
	items: [{
	    title: '',
	    collapsible: false,
	    region: 'south',
	    border:false,
	    frame:false,
	    height: 150,
	    items:[{
	    	border:false,
	 	    frame:false,
	    	layout:'column',
		    cmargins: '0 0 0 0',
		    items:[{
			    title: '证件业务',
		    	border:false,
		 	    frame:false,
			    cmargins: '0 0 0 0',
			    items:[{
					xtype: 'panel',
					border:false,
					frame: false,
			    	region:'south',
			    	height: 30,
			    	bodyStyle: 'padding:5px',
					layout:'table',
					layoutConfig: {
			        	columns: 30
			        },
			        items:[
						{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			    		},{
							border:false,
							frame: false,
			    		    items:[
			    		           new Ext.Button({
			    		        	   id:'fxbg',
			    		        	   text:'辅项变更',
										minWidth:80,
										disabled:true,
										handler:function(){
											if(selRes){
												if(selRes){
							        				var url = basePath + "yw/hjyw/fxbg?jumpToFxbg="+"zjFxbg"+"&gmsfhm="+selRes.data.gmsfhm;			      
							        				if(parent && parent.openWorkSpace){
							        					parent.openWorkSpace(url,  "辅项变更", "fxbg");
							        				}else{
							        					window.location.href = url;
							        				}
							        			
												}else{
													alert("请先选中一条有效的数据!");
												}
											
											}else{
												alert("请先选中一条有效的数据!");
											}
										}
			    		           })
			    		    ]
			    		},{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			    		}, {
							border:false,
							frame: false,
			    		    items:[
			    		           new Ext.Button({
			    		        	   text:'常口变更',
										minWidth:80,
										handler:function(){
											if(selRes){
						        				var url = basePath + "yw/hjyw/bggz?jumpToBggz="+"zjBggz"+"&gmsfhm="+selRes.data.gmsfhm;			      
						        				if(parent && parent.openWorkSpace){
						        					parent.openWorkSpace(url,  "变更更正", "bggz");
						        				}else{
						        					window.location.href = url;
						        				}
						        			
											}else{
												alert("请先选中一条有效的数据!");
											}
										}
			    		           })
			    		    ]
			    		},{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			    		}, {
							border:false,
							frame: false,
			    		    items:[
			    		           new Ext.Button({
			    		        	   text:'证件挂失',
										minWidth:80,
										handler:function(){
											if(selRes){
												var url = basePath + "yw/edzjgl/zjgs?zjlqJumpTo="+"1"+"&ryid="+selRes.data.ryid;			      
						        				if(parent && parent.openWorkSpace){
						        					parent.openWorkSpace(url,  "证件挂失", "zjgs");
						        				}else{
						        					window.location.href = url;
						        				}
											}else{
												alert("请先选中一条有效的数据!");
											}
										}
			    		           })
			    		    ]
			    		},{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			    		}, {
							border:false,
							frame: false,
			    		    items:[
			    		           new Ext.Button({
										text:'证件收交',
										minWidth:80,
										handler:function(){
											if(selRes){
												var url = basePath + "yw/edzzjgl/zjsj?zjlqJumpTo="+"1"+"&ryid="+selRes.data.ryid;			      
						        				if(parent && parent.openWorkSpace){
						        					parent.openWorkSpace(url,  "证件收交", "zjsj");
						        				}else{
						        					window.location.href = url;
						        				}
											}else{
												alert("请先选中一条有效的数据!");
											}
										}
			    		           })
			    		    ]
			    		}, {
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			    		}, {
							border:false,
							frame: false,
			    		    items:[
			    		           new Ext.Button({
			    		        	   text:'证件办理',
										minWidth:80,
										handler:function(){
											if(selRes){
												zjbl_window.setSelRes(selRes);
												zjbl_window.show();
											}else{
												alert("请先选中一条有效的数据!");
											}
											
										}
			    		           })
			    		    ]
			    		}
					]
			    }]}]},{
		    title: '报表打印',
	    	border:false,
	 	    frame:false,
		    cmargins: '0 0 0 0',
		    items:[{
				xtype: 'panel',
				border:false,
				frame: false,
		    	region:'south',
		    	height: 30,
		    	bodyStyle: 'padding:5px',
				layout:'table',
				layoutConfig: {
		        	columns: 30
		        },
		        items:[
					{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		    		},{
						border:false,
						frame: false,
		    		    items:[
		    		           new Ext.Button({
		    		        	   text:'受理单',
									minWidth:80,
									handler:function(){
										if(selRes){
											var data = lczjslGrid.store.data;
											if(data.length>0){
												var arrayTemp=[];
												var o={};
												o.directTable="jmsfzsldjb";
												o.rynbid =data.items[0].data.rynbid;
												arrayTemp.push(o);
												printfunction(0,arrayTemp);
											}else{
												alert("没有查找到此人的证件受理信息");
											}
										}else{
											alert("请先选中一条有效的数据!");
										}
									}
		    		           })
		    		    ]
		    		},{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		    		}, {
						border:false,
						frame: false,
		    		    items:[
		    		           new Ext.Button({
		    		        	   text:'收费单',
									minWidth:80,
									handler:function(){
										if(selRes){
											if(lczjslGrid.store.data.length>0){
												var arrayTemp=[];
												var o={};
												o.directTable="gmsfzsfd";
												o.rynbid =selRes.data.rynbid;
												arrayTemp.push(o);
												printfunction(0,arrayTemp);
											}else{
												alert("没有查找到此人的证件受理信息");
											}
										}else{
											alert("请先选中一条有效的数据!");
										}
									}
		    		           })
		    		    ]
		    		},{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		    		}, {
						border:false,
						frame: false,
		    		    items:[
		    		           new Ext.Button({
		    		        	   text:'常表套打',
									minWidth:80,
									handler:function(){
										if(selRes){
					    					printRk(2,selRes.data.hhnbid,selRes.data.rynbid,[true,true,true,true,true],[true,false,false,false,false],[true,true,true,true,true]);
										}else{
											alert("请先选中一条有效的数据!");
										}
									}
		    		           })
		    		    ]
		    		},{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		    		},{
						border:false,
						frame: false,
		    		    items:[
		    		           new Ext.Button({
									text:'常表整打',
									minWidth:80,
									handler:function(){
										if(selRes){
					    					printRk(2,selRes.data.hhnbid,selRes.data.rynbid,[true,true,true,true,true],[false,false,false,false,true],[true,true,true,true,true]);
										}else{
											alert("请先选中一条有效的数据!");
										}
									}
		    		           })
		    		    ]
		    		},{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		    		}, {
						border:false,
						frame: false,
		    		    items:[
		    		           new Ext.Button({
		    		        	   text:'关闭',
									minWidth:80,
									handler:function(){
										window.parent.closeWorkSpace();
									}
		    		           })
		    		    ]
		    		}
				]
		    }]},{
    	    	frame:false,
				border:false,
				id:'_READ_CARD_ID',
				xtype:'panel',
				html:'',
				width:10
				
			}]
	},{
	    title: '查询办证人员',
	    collapsible: false,
	    region:'north',
	    border:false,
	    frame:false,
	    layout:'column',
	    height: 120,
	    //margins: '5 0 0 0',
	    //cmargins: '5 0 0 0',
	    items:[{
	    	border:false,
	    	frame:false,
	    	region:'center',
	    	columnWidth: .8,
	    	items:[form10016]
	    },{
	    	border:false,
	    	frame:false,
	    	region:'east',
	    	layout:'form',
		    labelAlign :'right',
		    columnWidth: .2,
	    	items:[
		        new Ext.Button({
		        	id:'queryId',
	                text:'查询',
	                minWidth:80,
	               handler:function(){
	            	   var data = form10016.getForm().getValues();
   					
						Ext.Msg.wait("正在执行查询，请稍后...");
					if(getQueryParam("jumpToZjsl") =="cxbz"){
						data.ryid=getQueryParam("ryid");
    				}else{
    					if(Gnt.util.isEmpty(data)){
       						showInfo("至少输入一个查询条件！");
       						return;
       					}
    				}
       				var store = zjslGrid.store;
       				store.removeAll();
       				store.baseParams = data;
       				store.load({params:{start:0, limit:20}});
       				store.on('load',function(s,records){
       					if(records.length>0){
							curIndex = 0;
							zjslGrid.fireEvent("rowclick",zjslGrid,curIndex);
							zjslGrid.getSelectionModel().selectRange(curIndex,curIndex);
       						//Ext.getCmp('card').getLayout().setActiveItem(1);
							Ext.getCmp('fxbg').enable();
       					}else{
       						Ext.getCmp('fxbg').disable();
       					}
       					Ext.Msg.hide();
       				});
	               }
	          }),
	          {border:false,frame:false,width:20,height:5},
	          {
	    	    	xtype: 'DkButton',
	    	    	minWidth:80,
	    	    	form:form10016,
	    	    	callback: function(){
	    	    		//Ext.getCmp("queryId").handler();
	    	    	}
	    	  },
	          {border:false,frame:false,width:20,height:5},
		        new Ext.Button({
	                text:'组合查询',
	                minWidth:80,
	               handler:function(){
	            	   zhcx_dialog.show();
	               }
	          })]
	    }]
	  
	    
	},{
	    title: '查询结果',
	    collapsible: false,
	    region:'center',
	    layout:'border',
	    border:false,
	    frame:false,
	    margins: '0 0 0 0',
	    height:100,
	    items:[zjslGrid,{
		    title: '',
		    collapsible: false,
		    region:'east',
		    height:100,
		    border:false,
		    frame:false,
		    width:"15%",
		    margins: '0 0 0 0',
		    bodyStyle:'padding:10px 10px 10px 10px',
		    layout:'table',
		    layoutConfig: {
	    		columns: 1
	    	 },
		    labelWidth:40,
		    items:[{
				id:'picImage',
				title: '',
				//height:'100%',
				bodyStyle:'padding:10px 10px 10px 10px',
				html: '无照片',
				width:150,
				height:150,
				rowspan: 1,
				colspan:1
			},{
		    	height:3,
		    	border:false,
		    	frame:false
		    },{
		    	border:false,
		    	frame:false,
		    	html:'证件类别'
		    },{
		    	border:false,
		    	frame:false,
		    	xtype:'textfield',
		    	id:'zjlb',
				html:'',
				width:100
		    		
		},{
		    	height:15,
		    	border:false,
		    	frame:false
		    },new Ext.Button({
				text:'所有照片',
				minWidth:100,
				handler:function(){
					zpWindow.show();
				}
		    }),{
		    	height:3,
		    	border:false,
		    	frame:false
		    },new Ext.Button({
				text:'常口信息',
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
					
				}
		    })]
		}
	           ]
	}]
});
var p2=new Ext.Panel({
	layout:'border',
	items: [{
	    title: '',
	    collapsible: false,
	    region:'center',
	    layout:'column',
	    border:false,
	    frame:false,
	    height:50,
	    margins: '0 0 0 0',
	    items:[{
	    	columnWidth: .85,
	    	items:[{
	    	    title: '常口信息',
	    	    collapsible: false,
	    	    region:'north',
	    	    border:false,
	    	    frame:false,
	    	    height: 100,
	    	    //margins: '5 0 0 0',
	    	    //cmargins: '5 0 0 0',
	    	    items:[{
	    	    	border:false,
	    	    	frame:false,
	    	    	region:'center',
	    	    	items:[{
	            		layout:'column',
	        			frame:false,
	        			border:false,
	            		defaults:{
	            			border:false,
	            			bodyStyle:'padding:5 0 0 5',
	            			frame:false
	            		},
	                	items:[{
	    	                columnWidth:0.33,
	        	           	layout: 'form',
	        	           	//bodyStyle:'padding:5 0 0 0',
	            	       	items: [{
	    						xtype:'textfield',
	    						anchor:'100%',
	    						id:'xm',
	    						name:'xm',
	    						disabled:true,
	    						fieldLabel:'姓名'
	    					}]
	    				},{
	    		                columnWidth:0.33,
	    	    	           	layout: 'form',
	    	    	           	//bodyStyle:'padding:5 0 0 0',
	    	        	       	items: [{
	    							xtype:'textfield',
	    							anchor:'100%',
	    							id:'gmsfhm',
	    							name:'gmsfhm',
	    							disabled:true,
	    							fieldLabel:'身份号码'
	    						}]
	    				},{
	    	                columnWidth:0.33,
	        	           	layout: 'form',
	        	           	//bodyStyle:'padding:5 0 0 0',
	            	       	items: [{
	    						xtype:'textfield',
	    						anchor:'100%',
	    						id:'xb',
	    						name:'xb',
	    						disabled:true,
	    						fieldLabel:'性别'
	    					}]
	    				},{
	    	                columnWidth:0.33,
	        	           	layout: 'form',
	        	           	//bodyStyle:'padding:5 0 0 0',
	            	       	items: [{
	    						xtype:'textfield',
	    						anchor:'100%',
	    						id:'csrq',
	    						name:'csrq',
	    						disabled:true,
	    						fieldLabel:'出生日期',
	    						listeners:{
	    						}
	    					}]
	    				},{
	    	                columnWidth:0.33,
	        	           	layout: 'form',
	        	           	//bodyStyle:'padding:5 0 0 0',
	            	       	items: [{
	    						xtype:'textfield',
	    						anchor:'100%',
	    						id:'mz',
	    						name:'mz',
	    						disabled:true,
	    						fieldLabel:'民族'
	    					}]
	    				},{
	    	                columnWidth:0.33,
	        	           	layout: 'form',
	        	           	//bodyStyle:'padding:5 0 0 0',
	            	       	items: [{
	    						xtype:'textfield',
	    						anchor:'100%',
	    						id:'pcs',
	    						name:'pcs',
	    						disabled:true,
	    						fieldLabel:'派出所'
	    					}]
	    				},{
	    	                columnWidth:1,
	        	           	layout: 'form',
	        	           	//bodyStyle:'padding:5 0 0 0',
	            	       	items: [{
	    						xtype : "textfield",
	    						anchor:'100%',
	    						id:'zz',
	                			name:'zz',
	                			disabled:true,
	                			fieldLabel:"住址"
	    					}]
	    				}
	    			]
	    		}]
	    	    }]
	    	  
	    	    
	    	},{
	    	    title: '历次受理信息',
	    	    collapsible: false,
	    	    region:'center',
	    	    layout:'border',
	    	    border:false,
	    	    frame:false,
	    	    height:200,
	    	    margins: '0 0 0 0',
	    	    items:[lczjslGrid
	    	           ]
	    	},{
			    title: '受理信息',
		    	border:false,
		 	    frame:false,
			    cmargins: '0 0 0 0',
			    items:[{
					xtype: 'panel',
					border:false,
					frame: false,
			    	region:'south',
			        items:[form30003,form30011]
			    }]}]	
	    	},{
	    	columnWidth: .15,
	    	items:[{
	    		title: '',
				height:'100%',
				bodyStyle:'padding:10px 10px 10px 10px',
				html: '<div id="_PHOTO_ID">照片</DIV>',
				width:150,
				height:180,
				rowspan: 1,
				colspan:1 	
			},{
		    	height:3,
		    	border:false,
		    	frame:false
		    },{
            	xtype:'checkbox',
            	boxLabel: '作为办证照片',
            	name:'qybz1',
            	listeners:{
            		'check':function (ck, checked){
            			if (checked) {
            			}else{
            			}
            		}
            	}
            },{
		    	height:3,
		    	border:false,
		    	frame:false
		    },{
				id:'picImage2',
				title: '',
				//height:'100%',
				bodyStyle:'padding:10px 10px 10px 10px',
				html: '无照片',
				disabled:true,
				width:150,
				height:150,
				rowspan: 1,
				colspan:1
			},{
		    	height:3,
		    	border:false,
		    	frame:false
		    },{
            	xtype:'checkbox',
            	boxLabel: '作为办证照片',
            	name:'qybz2',
            	listeners:{
            		'check':function (ck, checked){
            			if (checked) {
            			}else{
            			}
            		}
            	}
            },{
		    	height:3,
		    	border:false,
		    	frame:false
		    },{
				id:'picImage3',
				title: '',
				//height:'100%',
				bodyStyle:'padding:10px 10px 10px 10px',
				html: '无照片',
				disabled:true,
				width:150,
				height:150,
				rowspan: 1,
				colspan:1
			
		    },{
		    	height:3,
		    	border:false,
		    	frame:false
		    },{
            	xtype:'checkbox',
            	boxLabel: '作为办证照片',
            	name:'qybz3',
            	listeners:{
            		'check':function (ck, checked){
            			if (checked) {
            			}else{
            			}
            		}
            	}
            }]
	    }
	]},{
				xtype: 'panel',
				border:false,
				frame: false,
		    	region:'south',
		    	height: 50,
		    	bodyStyle: 'padding:5px',
				layout:'table',
				layoutConfig: {
		        	columns: 30
		        },
		        items:[
					{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		    		},{
						border:false,
						frame: false,
		    		    items:[
		    		           new Ext.Button({
		    		        	   text:'快证收件人设置',
									minWidth:80,
									handler:function(){
										kzsjr_window.show();
									}
		    		           })
		    		    ]
		    		},{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		    		}, {
						border:false,
						frame: false,
		    		    items:[
		    		           new Ext.Button({
		    		        	   text:'图像采集',
		    		        	   id: 'zpcjImgBtn',
									minWidth:80,
									handler:function(){
										//Gnt.photo.setPhoto(null, true);
										Gnt.photo.OpenEdit();
									}
		    		           })
		    		    ]
		    		},{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		    		}, {
						border:false,
						frame: false,
		    		    items:[
		    		           new Ext.Button({
		    		        	   id:'tqxx',
		    		        	   text:'提取信息',
									minWidth:80,
									handler:function(){
										form30003.getForm().reset();
										form30011.getForm().reset();
										//lczjslGrid   form30003   form30011
										form30003.fieldSetValue(form30003.getForm().findField("yxqxqsrq"),lczjslselRes.data.yxqxqsrq);
										form30003.fieldSetValue(form30003.getForm().findField("yxqxjzrq"),lczjslselRes.data.yxqxjzrq);
										form30003.fieldSetValue(form30003.getForm().findField("slyy"),lczjslselRes.data.slyy);
										form30003.fieldSetValue(form30003.getForm().findField("zzlx"),lczjslselRes.data.zzlx);
										form30003.fieldSetValue(form30003.getForm().findField("lqfs"),lczjslselRes.data.lqfs);
										form30003.fieldSetValue(form30003.getForm().findField("sflx"),lczjslselRes.data.sflx);
										form30003.fieldSetValue(form30003.getForm().findField("sfje"),lczjslselRes.data.sfje);
										
										
										form30011.fieldSetValue(form30011.getForm().findField("sjrxm"),lczjslselRes.data.sjrxm);
										form30011.fieldSetValue(form30011.getForm().findField("sjryb"),lczjslselRes.data.sjryb);
										form30011.fieldSetValue(form30011.getForm().findField("sjrlxdh"),lczjslselRes.data.sjrlxdh);
										form30011.fieldSetValue(form30011.getForm().findField("sjrtxdz"),lczjslselRes.data.sjrtxdz);
										Ext.getCmp('bc').enable();
									}
		    		           })
		    		    ]
		    		},{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		    		},{
						border:false,
						frame: false,
		    		    items:[
		    		           new Ext.Button({
									text:'保存',
									id:'bc',
									disabled:true,
									minWidth:80,
									disabled:true,
									handler:function(){
										var form30003Value = form30003.getForm().getValues();
										var form30011Value = form30011.getForm().getValues();
										//form30003.fieldSetValue(form30003.getForm().findField("rynbid"),selRes.data.rynbid);
										var subdata = {
											nbslid:lczjslselRes?lczjslselRes.data.nbslid:"",
											rynbid:lczjslselRes?lczjslselRes.data.rynbid:selRynbid,
											yxqxqsrq:form30003Value.yxqxqsrq,
											yxqxjzrq:selyxqxzjrq,
											slyy:form30003Value.slyy,
											zzlx:form30003Value.zzlx,
											lqfs:form30003Value.lqfs,
											sflx:form30003Value.sflx,
											sfje:form30003Value.sfje,
											zpid:selRes.data.zpid,
											sjrxm:form30011Value.sjrxm,
											sjrlxdh:form30011Value.sjrlxdh,
											sjryb:form30011Value.sjryb,
											sjrtxdz:form30011Value.sjrtxdz,
											zplylx:0,//暂时先写死0  照片来源类型(0-来源于人员照片表 / 1-来源于照片临时表 / 其它-无照片)
											bwbhb:lczjslselRes.bwbhb
										};
										log_code = "F3301";
										Gnt.submit(
											subdata, 
											"yw/edzzjgl/zjsl/saveZjsl", 
											"正在保存受理信息，请稍后...", 
											function(jsonData,params){
												showInfo("受理信息保存成功！",function(){
													if(jsonData && jsonData.list[0] && jsonData.list[0].voZjslfhxx){
														Ext.getCmp('returnxm').setValue(jsonData.list[0].voZjslfhxx[0].xm);
														Ext.getCmp('returngmsfhm').setValue(jsonData.list[0].voZjslfhxx[0].gmsfhm);
														Ext.getCmp('returnslh').setValue();
														Ext.getCmp('returnslsj').setValue();
														Ext.getCmp('card').getLayout().setActiveItem(2);
													}
													
											});
										});
									}
		    		           })
		    		    ]
		    		},{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		    		}, {
						border:false,
						frame: false,
		    		    items:[
		    		           new Ext.Button({
		    		        	   text:'返回',
									minWidth:80,
									handler:function(){
										if(window.confirm('受理信息尚未保存，是否确定保存？')){
											Ext.getCmp('card').getLayout().setActiveItem(0);
										}else{
											return;
										}
									}
		    		           })
		    		    ]
		    		}
				]
		    }]
});
var p3=new Ext.Panel({
	layout:'border',
	defaults: {
	    collapsible: true,
	    split: true,
	    bodyStyle: 'padding:15px'
	},
	items: [{
	    title: '',
	    collapsible: false,
	    region: 'south',
	    border:false,
	    frame:false,
	    height: 150,
	    items:[{
	    	border:false,
	 	    frame:false,
	    	layout:'column',
		    cmargins: '0 0 0 0',
		    items:[{
			    title: '打印',
		    	border:false,
		 	    frame:false,
			    cmargins: '0 0 0 0',
			    items:[{
					xtype: 'panel',
					border:false,
					frame: false,
			    	region:'south',
			    	height: 30,
			    	bodyStyle: 'padding:5px',
					layout:'table',
					layoutConfig: {
			        	columns: 30
			        },
			        items:[
						{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			    		},{
							border:false,
							frame: false,
			    		    items:[
			    		           new Ext.Button({
			    		        	   text:'临时证',
										minWidth:80,
										handler:function(){
											if(selRes){
												var url = basePath + "yw/lsjmsfzgl/lzbl?zjslJumpTo="+"1"+"&ryid="+selRes.data.ryid;			      
						        				if(parent && parent.openWorkSpace){
						        					parent.openWorkSpace(url,  "临时证", "lzbl");
						        				}else{
						        					window.location.href = url;
						        				}
											}else{
												alert("请先选中一条有效的数据!");
											}
										}
			    		           })
			    		    ]
			    		},{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			    		}, {
							border:false,
							frame: false,
			    		    items:[
			    		           new Ext.Button({
			    		        	   text:'受理单',
										minWidth:80,
										handler:function(){
											if(selRes){
												var arrayTemp=[];
												var o={};
												o.directTable="jmsfzsldjb";
												o.rynbid =selRes.data.rynbid;
												arrayTemp.push(o);
												printfunction(0,arrayTemp);
											}else{
												alert("请先选中一条有效的数据!");
											}
										}
			    		           })
			    		    ]
			    		},{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			    		}, {
							border:false,
							frame: false,
			    		    items:[
			    		           new Ext.Button({
			    		        	   text:'收费单',
										minWidth:80,
										handler:function(){
											if(selRes){
												var arrayTemp=[];
												var o={};
												o.directTable="gmsfzsfd";
												o.rynbid =selRes.data.rynbid;
												arrayTemp.push(o);
												printfunction(0,arrayTemp);
											}else{
												alert("请先选中一条有效的数据!");
											}
										}
			    		           })
			    		    ]
			    		},{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			    		}, {
							border:false,
							frame: false,
			    		    items:[
			    		           new Ext.Button({
										text:'常表套打',
										minWidth:80,
										handler:function(){
											if(selRes){
						    					printRk(2,selRes.data.hhnbid,selRes.data.rynbid,[true,true,true,true,true],[true,false,false,false,false],[true,true,true,true,true]);
											}else{
												alert("请先选中一条有效的数据!");
											}
										}
			    		           })
			    		    ]
			    		}, {
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			    		}, {
							border:false,
							frame: false,
			    		    items:[
			    		           new Ext.Button({
			    		        	   text:'常表整打',
										minWidth:80,
										handler:function(){
											if(selRes){
						    					printRk(2,selRes.data.hhnbid,selRes.data.rynbid,[true,true,true,true,true],[false,false,false,false,true],[true,true,true,true,true]);
											}else{
												alert("请先选中一条有效的数据!");
											}
										}
			    		           })
			    		    ]
			    		}
					]
			    }]}]},{
		    title: 'HZ2004',
	    	border:false,
	 	    frame:false,
		    cmargins: '0 0 0 0',
		    items:[{
				xtype: 'panel',
				border:false,
				frame: false,
		    	region:'south',
		    	height: 30,
		    	bodyStyle: 'padding:5px',
				layout:'table',
				layoutConfig: {
		        	columns: 30
		        },
		        items:[
					{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		    		},{
						border:false,
						frame: false,
		    		    items:[
		    		           new Ext.Button({
		    		        	   text:'新办理',
									minWidth:80,
									handler:function(){
										showQuestion("数据未保存，确定放弃办理吗？", function(btnType){
											if(btnType=="yes"){
												/*Ext.getCmp('card').getLayout().setActiveItem(0);
												v.doLayout();
												p1.newYewu();
												*/
												window.location.reload();
											}
										});
									}
		    		           })
		    		    ]
		    		},{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		    		}, {
						border:false,
						frame: false,
		    		    items:[
		    		           new Ext.Button({
		    		        	   text:'继续办理',
									minWidth:80,
									handler:function(){
										Ext.getCmp('card').getLayout().setActiveItem(0);
									}
		    		           })
		    		    ]
		    		},{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		    		}, {
						border:false,
						frame: false,
		    		    items:[
		    		           new Ext.Button({
		    		        	   text:'证件挂失',
									minWidth:80,
									handler:function(){
										if(selRes){
											var url = basePath + "yw/edzjgl/zjgs?zjlqJumpTo="+"1"+"&ryid="+selRes.data.ryid;			      
					        				if(parent && parent.openWorkSpace){
					        					parent.openWorkSpace(url,  "证件挂失", "zjgs");
					        				}else{
					        					window.location.href = url;
					        				}
										}else{
											alert("请先选中一条有效的数据!");
										}
									}
		    		           })
		    		    ]
		    		},{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		    		},{
						border:false,
						frame: false,
		    		    items:[
		    		           new Ext.Button({
									text:'证件收交',
									minWidth:80,
									handler:function(){
										if(selRes){
											var url = basePath + "yw/edzzjgl/zjsj?zjlqJumpTo="+"1"+"&ryid="+selRes.data.ryid;			      
					        				if(parent && parent.openWorkSpace){
					        					parent.openWorkSpace(url,  "证件收交", "zjsj");
					        				}else{
					        					window.location.href = url;
					        				}
										
										}else{
											alert("请先选中一条有效的数据!");
										}
									}
		    		           })
		    		    ]
		    		},{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		    		}, {
						border:false,
						frame: false,
		    		    items:[
		    		           new Ext.Button({
		    		        	   text:'关闭',
									minWidth:80,
									handler:function(){
										window.parent.closeWorkSpace();
									}
		    		           })
		    		    ]
		    		},{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		    		}, {
						border:false,
						frame: false,
		    		    items:[
		    		           new Ext.Button({
		    		        	   text:'返回',
									minWidth:80,
									handler:function(){
										Ext.getCmp('card').getLayout().setActiveItem(1);
									}
		    		           })
		    		    ]
		    		}
				]
		    }]}]
	},{
	    title: '',
	    collapsible: false,
	    region:'north',
	    border:false,
	    frame:false,
	    height: 180,
	    //margins: '5 0 0 0',
	    //cmargins: '5 0 0 0',
	    items:[{
	    	border:false,
	    	frame:false,
	    	layout:'column',
	    	region:'center',
	    	items:[{
	    		border:false,
		    	frame:false,
	    		 columnWidth:0.2
	    	},{
	    		columnWidth:0.2,
	    		layout:'column',
	    		defaults:{
        			border:false,
        			bodyStyle:'padding:5 0 0 5',
        			frame:false
        		},
	    		items:[{
	                columnWidth:1,
    	           	layout: 'form',
    	           	//bodyStyle:'padding:5 0 0 0',
        	       	items: [{
						xtype:'textfield',
						anchor:'100%',
						name:'xm',
						id:'returnxm',
						disabled:true,
						fieldLabel:'姓名'
					}]
				},{
		                columnWidth:1,
	    	           	layout: 'form',
	    	           	//bodyStyle:'padding:5 0 0 0',
	        	       	items: [{
							xtype:'textfield',
							anchor:'100%',
							id:'returngmsfhm',
							name:'gmsfhm',
							disabled:true,
							fieldLabel:'身份号码'
						}]
				},{
	                columnWidth:1,
    	           	layout: 'form',
    	           	//bodyStyle:'padding:5 0 0 0',
        	       	items: [{
						xtype:'textfield',
						anchor:'100%',
						name:'slh',
						id:'returnslh',
						disabled:true,
						fieldLabel:'受理号'
					}]
				},{
	                columnWidth:1,
    	           	layout: 'form',
    	           	//bodyStyle:'padding:5 0 0 0',
        	       	items: [{
						xtype:'textfield',
						anchor:'100%',
						name:'slsj',
						id:'returnslsj',
						disabled:true,
						fieldLabel:'受理时间',
						listeners:{
						}
					}]
				}]
	    	},{
	    		border:false,
		    	frame:false,
		    	columnWidth:0.6
    	}]
	    }]
	  
	    
	},{
 	   region:'center',
 	   title:'',
 	   frame:false,
		   borde:false,
 	   html: '<center><br/><br/><br/><br/><br/><br/><br/><br/><h2>证件受理业务办理完成！</h2></center>'
    }]
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
	        	items:[p1,p2,p3]
	        }
	    });
 	if(getQueryParam("jumpToZjsl")=="cxbz"){
 		
		Ext.getCmp("queryId").handler();
	}  
	
	
});