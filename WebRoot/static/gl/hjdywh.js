Ext.onReady(function(){
	Ext.QuickTips.init();
	
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
						html: '<div class="text" style="text-align:center;"><br /><h2>个性化设置</h2></div>',
						height: 60,
						region:'north',
						bodyStyle:'padding:10px'
					},
					{
						id:"southId",
			        	region:'center',
			            border:false,
			            frame:false,
			            autoScroll:true, 
			            items:[{
			            	title: '打印设置',
				            xtype: 'fieldset',
				            autoHeight: true,
				            layout:'column',
				            defaults:{
				            	xtype:'checkbox',
				            	columnWidth: .5,
				            	fieldLabel: '',
				            	name: 'dysz'
				            },
				            items: [{
				            	id:'a1',
				            	boxLabel: '需要进行打印预览',
				            	name:'xyjxdyyl',
				            	checked:user.personSet.dyyl_dysz==1?true:false
				            },{
				            	id:'a2',
				            	boxLabel: '打印时弹出打印设置',
				            	name:'dystcdysz',
				            	checked:user.personSet.tcdysz_dysz==1?true:false
				            }]
						},{
			            	title: '常表设置',
//							columnWidth: 3,
				            xtype: 'fieldset',
				            autoHeight: true,
				            layout:'column',
				            defaults:{
				            	columnWidth: 1.,
				            	fieldLabel: '',
				            	name: 'cbsz'
				            },
				            items: [{
				            	id:'b1',
				            	xtype:'checkbox',
				            	boxLabel: '打印照片',
				            	checked:true,
				            	name:'dyzp',
				            	checked:user.personSet.dyzp_cbsz==1?true:false,  
		        				listeners:{
		        					'check': function(obj,checked){
		        						if(checked){
		        						}else{
		        						}
		        					}
		        				}
				            }]
						},{
			            	title: '索引卡设置',
//							columnWidth: 3,
				            xtype: 'fieldset',
				            autoHeight: true,
				            layout:'column',
				            defaults:{
				            	xtype:'checkbox',
				            	columnWidth: 1.,
				            	fieldLabel: '',
				            	name: 'syksz'
				            },
				            items: [{
				            	id:'c1',
				            	boxLabel: '集体户',
				            	name:'jth',
				            	checked:user.personSet.jth_syksz==1?true:false
				            }]
						},{
			            	title: '户口簿设置',
//							columnWidth: 3,
				            xtype: 'fieldset',
				            autoHeight: true,
				            layout:'column',
				            defaults:{
				            	xtype:'checkbox',
				            	columnWidth: .2,
				            	fieldLabel: '',
				            	name: 'hkbsz'
				            },
				            items: [{
				            	id:'d1',
				            	boxLabel: '打印户口薄首页',
				            	name:'dyhkbsy',
				            	checked:user.personSet.hkbsy_hkbsz==1?true:false
				            },{
				            	id:'d2',
				            	boxLabel: '打印户口薄背面',
				            	name:'dyhkbbm',
				            	checked:user.personSet.hkbbm_hkbsz==1?true:false
				            },{
				            	id:'d3',
				            	boxLabel: '集体户方式户口首页',
				            	name:'jthfshksy',
				            	checked:user.personSet.jthfshksy_hkbsz==1?true:false
				            },{
				            	id:'d4',
				            	boxLabel: '集体户方式户口页',
				            	name:'jthfshky',
				            	checked:user.personSet.jthfshky_hkbsz==1?true:false
				            },{
				            	id:'d5',
				            	boxLabel: '打印出生原因',
				            	name:'bkls',
				            	checked:user.personSet.csyy_hkbsz==1?true:false
				            }]
						},{
			            	title: '户籍证明设置',
				            xtype: 'fieldset',
				            autoHeight: true,
				            layout:'column',
				            defaults:{
				            	xtype:'checkbox',
				            	columnWidth: .2,
				            	fieldLabel: '',
				            	name: 'hjzmsz'
				            },
				            items: [{
				            	id:'e1',
				            	boxLabel: '打印照片',
				            	name:'dyzp',
				            	checked:user.personSet.dyzp_hjzmsz==1?true:false
				            },{
				            	id:'e2',
				            	boxLabel: '打印户成员信息',
				            	name:'dyhcyxx',
				            	checked:user.personSet.hcyxx_hjzmsz==1?true:false
				            },{
				            	id:'e3',
				            	boxLabel: '打印变动原因',
				            	name:'dybdyy',
				            	checked:user.personSet.bdyy_hjzmsz==1?true:false
				            },{
				            	id:'e4',
				            	boxLabel: '打印变动信息',
				            	name:'dybdxx',
				            	checked:user.personSet.bdxx_hjzmsz==1?true:false
				            },{
				            	id:'e5',
				            	boxLabel: '打印注销人员信息',
				            	name:'dyzxryxx',
				            	checked:user.personSet.zxryxx_hjzmsz==1?true:false
				            },{
				            	id:'e6',
				            	boxLabel: '打印单位',
				            	name:'dydw',
				            	checked:user.personSet.dydw_hjzmsz==1?true:false
				            },{
				            	id:'e7',
				            	boxLabel: '打印户号',
				            	name:'dyhh',
				            	checked:user.personSet.dyhh_hjzmsz==1?true:false
				            },{
				            	id:'e8',
				            	boxLabel: '打印婚姻状况',
				            	name:'dyhyzk',
				            	checked:user.personSet.dyhyzk_hjzmsz==1?true:false
				            },{
				            	id:'e9',
				            	boxLabel: '打印兵役情况',
				            	name:'dybyqk',
				            	checked:user.personSet.dybyqk_hjzmsz==1?true:false
				            },{
				            	id:'e10',
				            	boxLabel: '打印文化程度',
				            	name:'dywhcd',
				            	checked:user.personSet.dywhcd_hjzmsz==1?true:false
				            }]
						},{
			            	title: '读卡器设置',
//							columnWidth: 3,
				            xtype: 'fieldset',
				            autoHeight: true,
				            layout:'column',
				            items: [{
								hiddenName:'zxbz',
								id:'dkqlx',
								anchor:'100%',
								xtype:'combo',
								fieldLabel:'状态',
								mode: 'local',
		            			triggerAction: 'all',
								valueField:"code",
		      					displayField:"name",
								selectOnFocus:true,
								emptyText : '请选择',
								typeAhead: true,  
								editable:false,
								forceSelection: true,
					    		forceSelection: true, 
		    					blankText:'请选择',
		            			lazyRender:true,
		            			value:user.personSet.dkqlx,
		            			store:new Ext.data.SimpleStore({
		            				id:0,
		            				fields:[{name: 'code', mapping: 0},{name: 'name', mapping: 1}],
		            			   	data:[['1','神思'],['2','华视'],['4','新中新'],['5','精伦']]
		            			})
							}]
						},{
			            	title: '有效天数',
//							columnWidth: 3,
				            xtype: 'fieldset',
				            autoHeight: true,
				            layout:'column',
				            defaults:{
				            	xtype:'numberfield',
				            	columnWidth: 0.15,
				            	fieldLabel: ''
				            },
				            items: [{
				            	id:'f1',
				            	boxLabel: '',
				            	name:'yxts',
				            	value:user.personSet.yxts
				            }]
						},{
			            	title: '打印收费标准设置',
//							columnWidth: 3,
				            xtype: 'fieldset',
				            autoHeight: true,
				            layout:'column',
				            items: [{
				        		layout:'column',
				    			frame:false,
				    			border:false,
				        		defaults:{
				        			border:false,
				        			frame:false
				        		},
				            	items:[{
					        		layout:'column',
					    			frame:false,
					    			border:false,
					        		defaults:{
					        			border:false,
					        			frame:false,
						            	columnWidth: 0.33
					        		},
					            	items:[{
						    	           	layout: 'form',
						    	           	bodyStyle:'padding:5 0 0 0',
						        	       	items: [{
												xtype:'numberfield',
												anchor:'100%',
												id:'g1',
												name:'hjsysf',
												allowBlank:false,
												fieldLabel:'户籍首页收费',
												value:user.personSet.hjsysf
											}]
									},{
					    	           	layout: 'form',
					    	           	bodyStyle:'padding:5 0 0 0',
					        	       	items: [{
											xtype:'numberfield',
											anchor:'100%',
											id:'g2',
											name:'zqzsf',
											allowBlank:false,
											fieldLabel:'准迁证收费',
											value:user.personSet.zqzsf
										}]
									},{
					    	           	layout: 'form',
					    	           	bodyStyle:'padding:5 0 0 0',
					        	       	items: [{
											xtype:'textfield',
											anchor:'100%',
											id:'g3',
											name:'qyzsf',
											allowBlank:false,
											fieldLabel:'迁移证收费',
											value:user.personSet.qyzsf
										}]
									}
								]
							}]
						}]
						}]
			        }
				]
			},{
				border:false,
				frame: false,
	        	region:'south',
	        	height: 40,
	        	bodyStyle: 'padding:5px',
				layout:'table',
				layoutConfig: {
		        	columns: 10
		        },
		    	items: [
					{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		    		},{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		    		},{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
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
		    		    	id:'queryId',
		        			text:'保存',
		        			minWidth:60,
		        			handler:function(){
		        				var data = {
		        					dyyl_dysz:Ext.getCmp('a1').checked?1:0,
		        					tcdysz_dysz:Ext.getCmp('a2').checked?1:0,
		        					dyzp_cbsz:Ext.getCmp('b1').checked?1:0,	
		        					jth_syksz:Ext.getCmp('c1').checked?1:0,	
		        					hkbsy_hkbsz:Ext.getCmp('d1').checked?1:0,	
		        					hkbbm_hkbsz:Ext.getCmp('d2').checked?1:0,	
		        					jthfshksy_hkbsz:Ext.getCmp('d3').checked?1:0,	
		        					jthfshky_hkbsz:Ext.getCmp('d4').checked?1:0,	
		        					csyy_hkbsz:Ext.getCmp('d5').checked?1:0,	
		        					dyzp_hjzmsz:Ext.getCmp('e1').checked?1:0,	
		        					hcyxx_hjzmsz:Ext.getCmp('e2').checked?1:0,	
		        					bdyy_hjzmsz:Ext.getCmp('e3').checked?1:0,	
		        					bdxx_hjzmsz:Ext.getCmp('e4').checked?1:0,	
		        					zxryxx_hjzmsz:Ext.getCmp('e5').checked?1:0,	
		        					dydw_hjzmsz:Ext.getCmp('e6').checked?1:0,	
		        					dyhh_hjzmsz:Ext.getCmp('e7').checked?1:0,
		        					dyhyzk_hjzmsz:Ext.getCmp('e8').checked?1:0,	
		        					dybyqk_hjzmsz:Ext.getCmp('e9').checked?1:0,	
		        					dywhcd_hjzmsz:Ext.getCmp('e10').checked?1:0,
				        			yxts:Ext.getCmp('f1').getValue(),
				        			dkqlx:Ext.getCmp('dkqlx').getValue(),
				        			hjsysf:Ext.getCmp('g1').getValue(),
				        			zqzsf:Ext.getCmp('g2').getValue(),
				        			qyzsf:Ext.getCmp('g3').getValue()
		        				}
		        				Gnt.submit(
											data, 
											"gl/hjdywh/saveDysz", 
											"保存新的打印设置，请稍后...", 
											function(jsonData,params){
												showInfo("打印设置保存成功,下次登录会生效！");
										}
									);
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
	Ext.getCmp('nextId').setVisible(false);
});