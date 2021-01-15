var selRes = null;
Ext.onReady(function(){
	Ext.QuickTips.init();	
	if(!Gnt.loadSjpzb("10019,30017,10001,10022,10018,10017,10002,20000",function(){}))
		return;	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX','CS_BGGZLB'],function(){});
	var lsslxxGrid = new Gnt.ux.SjpzGrid({
		pkname: 'nbslid',
		title:'此人历史受理信息',
		pzlb: '30017',
		url:'yw/fjgl/fjsh/queryLsshxx',
		region:'south',
		height:300,
		title: '',
		showPaging:true,
		listeners:{
			rowclick:function(g, rowIndex, e){
				
			},
			rowdblclick:function(grid,row){
				
			}
		}
	});
	var fjshGrid = new Gnt.ux.SjpzGrid({
		pkname: 'nbslid',
		pzlb: '30017',
		url:'yw/fjgl/fjsh/queryFjshxx',
		region:'center',
		height:300,
		title: '受理信息',
		showPaging:true,
		listeners:{
			rowclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				zpid = selRes.data.zpid;
				Ext.getCmp("slxxxm").setValue(selRes.data.xm);
				Ext.getCmp("slxxxb").setValue(Gnt.ux.Dict.getDictLable('CS_XB', selRes.data.xb));
				Ext.getCmp("slxxcs").setValue(selRes.data.csrq);
				Ext.getCmp("slxxmz").setValue(Gnt.ux.Dict.getDictLable('CS_MZ', selRes.data.mz));
				Ext.getCmp("slxxhm").setValue(selRes.data.gmsfhm);
				Ext.getCmp("slxxslyy").setValue(Gnt.ux.Dict.getDictLable('CS_EDZSLYY', selRes.data.slyy));
				Ext.getCmp("slxxlz").setValue(Gnt.ux.Dict.getDictLable('CS_EDZLZFS', selRes.data.lqfs));
				Ext.getCmp("slxxyxqxq").setValue(selRes.data.yxqxqsrq);
				Ext.getCmp("slxxyxqxz").setValue(selRes.data.yxqxjzrq);
				Ext.getCmp("slxxxz").setValue(selRes.data.zz);
				if(zpid &&　zpid != 0){
					Ext.getCmp('picImage').body.update("<IMG SRC='yw/fjgl/fjsh/img/render/" + zpid + "' width='100%' height='100%' />");
				}else{
					Ext.getCmp('picImage').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
				}
				Gnt.submit(
						{ryid:selRes.data.ryid}, 
						"yw/common/queryHcy", 
						"正在查询常口信息，请稍后...", 
						function(jsonData,params){
							if(jsonData.list&&jsonData.list[0]){
								var tempObj = jsonData.list[0];//rkxxpicImage
								var rkxxzpid = tempObj.zpid
								if(rkxxzpid &&　rkxxzpid != 0){
									Ext.getCmp('rkxxpicImage').body.update("<IMG SRC='yw/common/img/render/" + rkxxzpid + "' width='100%' height='100%' />");
								}else{
									Ext.getCmp('rkxxpicImage').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
								}
								Ext.getCmp("ckxxxm").setValue(tempObj.xm);
								Ext.getCmp("ckxxxb").setValue(Gnt.ux.Dict.getDictLable('CS_XB', tempObj.xb));
								Ext.getCmp("ckxxmz").setValue(Gnt.ux.Dict.getDictLable('CS_MZ', tempObj.mz));
								Ext.getCmp("ckxxcsrq").setValue(tempObj.csrq);
								Ext.getCmp("ckxxsfhm").setValue(tempObj.gmsfhm);
								Ext.getCmp("ckxxpcs").setValue(Gnt.ux.Dict.getDictLable('DWXXB', tempObj.pcs));
								Ext.getCmp("ckxxsfzdz").setValue(Gnt.ux.Dict.getDictLable('XZQHB', tempObj.ssxq)+tempObj.mlph+tempObj.mlxz);
								Ext.getCmp("ckxxxzjd").setValue(Gnt.ux.Dict.getDictLable('XZJDXXB', tempObj.xzjd));
								Ext.getCmp("ckxxjlx").setValue(Gnt.ux.Dict.getDictLable('JLXXXB', tempObj.jlx));
								Ext.getCmp("ckxxmlph").setValue(tempObj.mlph);
								Ext.getCmp("ckxxmlxz").setValue(tempObj.mlxz);
								Ext.getCmp("ckxxssxq").setValue(Gnt.ux.Dict.getDictLable('XZQHB', tempObj.ssxq));
							}
						}
				);
				//历史信息查询
				var store = lsslxxGrid.store;
				store.baseParams = {
						ryid:selRes.data.ryid,
						nbslid:selRes.data.nbslid
				};
				store.load({params:{start:0, limit:20}});
				store.on('load',function(s,records){
					if(store.data.length > 0){
						lsslxxGrid.fireEvent("rowclick",lsslxxGrid,0);
						lsslxxGrid.getSelectionModel().selectRange(0,0);
					}
				},lsslxxGrid); 
			},
			rowdblclick:function(grid,row){
				selRes = grid.store.getAt(row);
				
				if(selRes && selRes.data){
					rynbid = selRes.data.rynbid;
					hhnbid = selRes.data.hhnbid;
					ryid = selRes.data.ryid;
					hhid = selRes.data.hhid;
				}else{
					
				}
			}
		}
	});
	var p1 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[{
				region:'center',
				title:'受理信息',
				border:false,
	            frame:false,
	            defaults:{
	            	border:false,
		            frame:false
	            },
	            items:[{
	            		region:'south',
	            		//border:false,
	            	    //frame:false,
	            	    margins: '0 0 0 0',
	            	    bodyStyle:'overflow-y:auto;height:270px',
	            	    //height:400,
	            		items:[
	            			{
	            				//border:false,
	        		            //frame:false,
	        		            region:'center',
	        		            layout:'column',
	        		            items:[
	        		            	{
	        		            		region:'east',
	    	        		            columnWidth:.35,
	    	        		            id:'picImage',
	    	        					title: '',
	    	        					bodyStyle:'padding:10px 10px 10px 10px',
	    	        					html: '照片',
	    	        					width:100,
	    	        					height:180,
	    	        					rowspan: 1,
	    	        					colspan:1
	        		            	},{
	        		            		region:'center',
	        		            		layout : 'column',
	        		            		columnWidth:.65,
	    	        		            title: '',
	        		            		xtype: 'fieldset',
	        		            		defaults : {
	        								frame:false,
	        								border:false,
	        								columnWidth:.5,
	        								bodyStyle : 'padding:5px 5px 0px 5px'
	        							},
	        							items:[{
	    									layout: 'form',
	    									columnWidth:.65,
	    									labelWidth : 40,
	    									items:[{
	    										fieldLabel:'姓名',
	    										name:'xm',
	    										id:'slxxxm',
	    										anchor:'100%',
	    										disabled:true,
	    										xtype : 'textfield'
	    									}]
	    								},{
	    									layout: 'form',
	    									columnWidth:.35,
	    									labelWidth : 40,
	    									items:[{
	    										fieldLabel:'姓别',
	    										name:'xb',
	    										id:'slxxxb',
	    										anchor:'100%',
	    										disabled:true,
	    										xtype : 'textfield'
	    									}]
	    								},{
	    									layout: 'form',
	    									columnWidth:.65,
	    									labelWidth : 40,
	    									items:[{
	    										fieldLabel:'出生',
	    										name:'csrq',
	    										anchor:'100%',
	    										id:'slxxcs',
	    										disabled:true,
	    										xtype : 'textfield'
	    									}]
	    								},{
	    									layout: 'form',
	    									columnWidth:.35,
	    									labelWidth : 40,
	    									items:[{
	    										fieldLabel:'民族',
	    										name:'mz',
	    										anchor:'100%',
	    										id:'slxxmz',
	    										disabled:true,
	    										xtype: 'textfield'
	    									}]
	    								},{
	    									layout: 'form',
	    									columnWidth:1.,
	    									labelWidth : 40,
	    									items:[{
	    										fieldLabel:'号码',
	    										name:'dhhm',
	    										anchor:'100%',
	    										id:'slxxhm',
	    										disabled:true,
	    										xtype : 'textfield'
	    									}]
	    								},{
	    									layout: 'form',
	    									columnWidth:1.,
	    									labelWidth : 60,
	    									items:[{
	    										fieldLabel:'申领原因',
	    										name:'slyy',
	    										anchor:'100%',
	    										id:'slxxslyy',
	    										disabled:true,
	    										xtype : 'textfield'
	    									}]
	    								},{
	    									layout: 'form',
	    									columnWidth:1.,
	    									labelWidth : 40,
	    									items:[{
	    										fieldLabel:'领证',
	    										name:'lz',
	    										anchor:'100%',
	    										id:'slxxlz',
	    										disabled:true,
	    										xtype : 'textfield'
	    									}]
	    								}]
	        		            	},{

	        		            		region:'south',
	        		            		border:false,
	    	        		            frame:false,
	    	        		            layout : 'column',
	    	        		            title: '',
	    	        		            defaults : {
	        								frame:false,
	        								border:false,
	        								columnWidth:.5,
	        								bodyStyle : 'padding:5px 5px 0px 5px'
	        							},
	    	        		            items:[
	    	        		            	{
		    									layout: 'form',
		    									columnWidth:.5,
		    									labelWidth : 80,
		    									items:[{
		    										fieldLabel:'有效期限起',
		    										name:'yxqx',
		    										anchor:'100%',
		    										id:'slxxyxqxq',
		    										disabled:true,
		    										xtype : 'textfield'
		    									}]
		    								},{
		    									layout: 'form',
		    									columnWidth:.5,
		    									labelWidth : 80,
		    									items:[{
		    										fieldLabel:'有效期限至',
		    										name:'yxqx',
		    										labelSeparator: '',
		    										anchor:'100%',
		    										id:'slxxyxqxz',
		    										disabled:true,
		    										xtype : 'textfield'
		    									}]
		    								},{
		    									layout: 'form',
		    									columnWidth:1.,
		    									labelWidth : 40,
		    									items:[{
		    										labelSeparator: '',
		    										name:'dz',
		    										id:'slxxxz',
		    										anchor:'100%',
		    										disabled:true,
		    										xtype : 'textfield'
		    									}]
		    								}
	    	        		            ]
	        		            	}
	        		            ]
	            			}
	            			]
	            	},
	            	fjshGrid
	            ]
			},{
        		region:'east',
        		//border:false,
        	    //frame:false,
        	    margins: '0 0 0 0',
        	    title:'常口信息',
        	    width:'38%',
        	    bodyStyle:'overflow-y:auto;height:100px',
        	    //height:400,
        		items:[{
            		region:'south',
		            id:'rkxxpicImage',
					title: '',
					bodyStyle:'padding:10px 10px 10px 10px',
					html: '照片',
					width:180,
					height:180,
					rowspan: 1,
					colspan:1	
            	},{
        				border:false,
    		            //frame:false,
    		            region:'center',
    		            layout:'column',
    		            items:[
    		            	{
    		            		region:'center',
    		            		layout : 'column',
    		            		columnWidth:.9,
    		            		title: '',
    		            		xtype: 'fieldset',
    		            		defaults : {
    								frame:false,
    								border:false,
    								labelWidth : 70,
    								columnWidth:0.5,
    								bodyStyle : 'padding:5px 5px 0px 5px'
    							},
    							items:[{
									layout: 'form',
									items:[{
										fieldLabel:'姓名',
										id:'ckxxxm',
										name:'ckxxxm',
										anchor:'100%',
										disabled:true,
										xtype : 'textfield'
									}]
								},{
									layout: 'form',
									items:[{
										fieldLabel:'姓别',
										id:'ckxxxb',
										name:'ckxxxb',
										anchor:'100%',
										disabled:true,
										xtype : 'textfield'
									}]
								},{
									layout: 'form',
									items:[{
										fieldLabel:'民族',
										id:'ckxxmz',
										name:'ckxxmz',
										anchor:'100%',
										disabled:true,
										xtype: 'textfield'
									}]
								},{
									layout: 'form',
									items:[{
										fieldLabel:'出生日期',
										id:'ckxxcsrq',
										name:'ckxxcsrq',
										anchor:'100%',
										disabled:true,
										xtype : 'textfield'
									}]
								},{
									layout: 'form',
									items:[{
										fieldLabel:'身份号码',
										id:'ckxxsfhm',
										name:'ckxxsfhm',
										anchor:'100%',
										disabled:true,
										xtype : 'textfield'
									}]
								},{
									layout: 'form',
									items:[{
										fieldLabel:'派出所',
										id:'ckxxpcs',
										name:'ckxxpcs',
										anchor:'100%',
										disabled:true,
										xtype : 'textfield'
									}]
								},{
									layout: 'form',
									items:[{
										fieldLabel:'身份证地址',
										id:'ckxxsfzdz',
										name:'ckxxsfzdz',
										anchor:'100%',
										disabled:true,
										xtype : 'textfield'
									}]
								},{
									layout: 'form',
									items:[{
										fieldLabel:'乡镇街道',
										id:'ckxxxzjd',
										name:'ckxxxzjd',
										anchor:'100%',
										disabled:true,
										xtype : 'textfield'
									}]
								},{
									layout: 'form',
									items:[{
										fieldLabel:'街路巷',
										id:'ckxxjlx',
										name:'ckxxjlx',
										anchor:'100%',
										disabled:true,
										xtype : 'textfield'
									}]
								},{
									layout: 'form',
									items:[{
										fieldLabel:'门楼牌号',
										id:'ckxxmlph',
										name:'ckxxmlph',
										anchor:'100%',
										disabled:true,
										xtype : 'textfield'
									}]
								},{
									layout: 'form',
									items:[{
										fieldLabel:'门楼详址',
										id:'ckxxmlxz',
										name:'ckxxmlxz',
										anchor:'100%',
										disabled:true,
										xtype : 'textfield'
									}]
								},{
									layout: 'form',
									items:[{
										fieldLabel:'省市县区',
										id:'ckxxssxq',
										name:'ckxxssxq',
										anchor:'100%',
										disabled:true,
										xtype : 'textfield'
									}]
								}]
    		            	}
    		            ]
        			},lsslxxGrid
        			]
	            	},{
	        	region:'south',
	        	height:60,
	        	title:'对比信息',
	            border:false,
	            frame:false,
	            items:[{
					border:false,
					frame: false,
		        	height: 50,
		        	title:'',
		        	bodyStyle: 'padding:5px',
					layout:'table',
					layoutConfig: {
			        	columns: 18
			        },
			    	items: [{
		            	id:'dbjg',
		            	border:false,
			            frame:false,
		            	html: '<a style="color:red;">对比结果:</a>',//boxLabel: '对比结果',
		            	name:'dbjg'
		            },{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		    	},new Ext.Button({
	        			text:'刷新',
	        			minWidth:60,
	        			handler:function(){
	        				var store  = fjshGrid.store;
	        				store.load({params:{start:0, limit:20}});
	        				store.on('load',function(s,records){
	        					if(store.data.length > 0){
	        						var curIndex = getQueryParam("index");
	        						if(getQueryParam("index")){
	        							fjshGrid.fireEvent("rowclick",fjshGrid,curIndex);
	        							fjshGrid.getSelectionModel().selectRange(curIndex,curIndex);
	        						}
	        					}
	        				},fjshGrid); 
	        			}
	        		}),{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			    	},{
							border:false,
							frame: false,
			    		    items:[new Ext.Button({
			        			text:'合格',
			        			id:'hg',
			        			disabled:true,
			        			minWidth:60,
			        			handler:function(){
			        				if(selRes){
			        					var shdata=new Array();
		        						var data = {};
		        						data.nbslid = selRes.data.nbslid;
		        						data.shqk = '1';
		        						shdata.push(data);
			        					Gnt.submit(
			        							{
				        								shdata:Ext.encode(shdata)	
			        							}, 
			        							"yw/fjgl/fjsh/processZjshyw", 
			        							"正在进行分局合格审核，请稍后...", 
			        							function(jsonData,params){
			        								showInfo("分局合格审核成功！");
			        								fjshGrid.store.reload(); 
			        							}
			        					);
			        				}else{
			        					alert("请先选中一条有效的数据！");
			        					return;
			        				}
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
			        			text:'全部审核',
			        			minWidth:60,
			        			id:'qbsh',
			        			disabled:true,
			        			handler:function(){
			        				if(selRes){
			        					shGgDialog.show();
			        					shGgDialog.setSelRes();
			        				}else{
			        					alert("请先选中一条有效的数据！");
			        					return;
			        				}
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
			        			text:'不合格',
			        			minWidth:60,
			        			id:'bhg',
			        			disabled:true,
			        			handler:function(){
			        				if(selRes){
			        					shBhgDialog.show();
			        					shBhgDialog.setSelRes();
			        				}else{
			        					alert("请先选中一条有效的数据！");
			        					return;
			        				}
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
	var store  = fjshGrid.store;
	store.load({params:{start:0, limit:20}});
	store.on('load',function(s,records){
		if(store.data.length > 0){
			var curIndex = getQueryParam("index");
			if(getQueryParam("index")){
				fjshGrid.fireEvent("rowclick",fjshGrid,curIndex);
				fjshGrid.getSelectionModel().selectRange(curIndex,curIndex);
			}
		}
	},fjshGrid); 
});
