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
				id:'nextId',
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
						html: '<div class="text" style="text-align:center;"><br /><h2>二代证</h2></div>',
						height: 80,
						region:'north',
						bodyStyle:'padding:15px'
					},
					{
			        	region:'center',
			        	height:250,
			            border:false,
			            frame:false,
			            layout:'form',
			            items:[{
			            	title: '',
				            xtype: 'fieldset',
				            autoHeight: true,
				            border:false,
				            frame:false,
				            layout:'column',
				            defaults:{
			        			border:false,
			        			frame:false
			        		},
			        		items:[{
				                columnWidth:1,
			    	           	layout: 'form',
			    	           	bodyStyle:'padding:5 0 0 0',
			        	       	items: [{
					            	id:'h1',
					            	xtype:'textfield',
					            	fieldLabel: '重办通知单',
					            	name:'cbtzd',
					            	value:user.dwDySet.cbtzd
								}]
			        		},{
				                columnWidth:0.5,
			    	           	layout: 'form',
			    	           	bodyStyle:'padding:5 0 0 0',
			        	       	items: [{
					            	id:'h2',
					            	xtype:'textfield',
					            	fieldLabel: '快证领证日期',
					            	name:'kzlzrq',
					            	value:user.dwDySet.kzlzrq
								}]
			        		},{
				                columnWidth:0.5,
			    	           	layout: 'form',
			    	           	bodyStyle:'padding:5 0 0 0',
			        	       	items: [{
					            	id:'h3',
					            	xtype:'textfield',
					            	fieldLabel: '慢证领证日期',
					            	name:'mzlzrq',
					            	value:user.dwDySet.mzlzrq
								}]
			        		},{
				                columnWidth:0.33,
			    	           	layout: 'form',
			    	           	bodyStyle:'padding:5 0 0 0',
			        	       	items: [{
					            	id:'h4',
					            	xtype:'textfield',
					            	fieldLabel: '派出所联系电话',
					            	name:'pcslxdh',
					            	value:user.dwDySet.pcslxdh
								}]
			        		},{
				                columnWidth:0.33,
			    	           	layout: 'form',
			    	           	bodyStyle:'padding:5 0 0 0',
			        	       	items: [{
					            	id:'h5',
					            	xtype:'numberfield',
					            	fieldLabel: '读卡器串口号',
					            	value:user.dwDySet.dkqckh,
					            	name:'dkqckh'
					            	
								}]
			        		},{
				                columnWidth:0.33,
			    	           	layout: 'form',
			    	           	bodyStyle:'padding:5 0 0 0',
			        	       	items: [{
					            	id:'h6',
					            	xtype:'checkbox',
					            	fieldLabel: '模拟读卡',
					            	name:'mndk',
					            	checked:user.dwDySet.mndk==1?true:false
								}]
			        		},{
				                columnWidth:0.4,
			    	           	layout: 'form',
			    	           	labelWidth:240,
			    	           	bodyStyle:'padding:5 0 0 0',
			        	       	items: [{
					            	id:'h7',
					            	xtype:'numberfield',
					            	fieldLabel: '每次进行审核、签发的数据数量(40-1000)',
					            	name:'ywlimit',
					            	value:user.dwDySet.ywlimit
								}]
			        		},{
				                columnWidth:0.3,
			    	           	layout: 'form',
			    	           	labelWidth:110,
			    	           	bodyStyle:'padding:5 0 0 0',
			        	       	items: [{
			        	       		id:'h8',
			        	       		xtype:'checkbox',
					            	fieldLabel: '使用写卡机具读卡',
					            	name:'syxkjjdk',
					            	checked:user.dwDySet.xkjdk==1?true:false
								}]
			        		}]
						},{
			            	title: '派出所快证接收信息配置',
				            xtype: 'fieldset',
				            autoHeight: true,
				            layout:'column',
				            defaults:{
			        			border:false,
			        			frame:false
			        		},
			        		items:[{
				                columnWidth:0.3,
			    	           	layout: 'form',
			    	           	bodyStyle:'padding:5 0 0 0',
			        	       	items: [{
					            	id:'h9',
					            	xtype:'textfield',
					            	fieldLabel: '派出所名称',
					            	name:'pcsmc',
					            	value:user.dwDySet.pcsmc
								}]
			        		},{
				                columnWidth:0.3,
			    	           	layout: 'form',
			    	           	bodyStyle:'padding:5 0 0 0',
			        	       	items: [{
					            	id:'h10',
					            	xtype:'numberfield',
					            	fieldLabel: '派出所邮编',
					            	name:'pcsyb',
					            	value:user.dwDySet.pcsyb
								}]
			        		},{
				                columnWidth:1,
			    	           	layout: 'form',
			    	           	bodyStyle:'padding:5 0 0 0',
			        	       	items: [{
					            	id:'h11',
					            	xtype:'textfield',
					            	fieldLabel: '派出所地址',
					            	name:'pcsdz',
					            	anchor :"50%",
					            	value:user.dwDySet.pcsdz
								}]
			        		},{
				                columnWidth:1,
			    	           	layout: 'form',
			    	           	bodyStyle:'padding:5 0 0 0',
			        	       	items: [{
					            	id:'h12',
					            	xtype:'textfield',
					            	fieldLabel: '联系电话',
					            	anchor :"50%",
					            	name:'pcsdh',
					            	value:user.dwDySet.pcsdh
								}]
			        		}]
						},{
			            	title: '异地办证点信息配置',
				            xtype: 'fieldset',
				            autoHeight: true,
				            layout:'column',
				            defaults:{
			        			border:false,
			        			frame:false
			        		},
			        		items:[{
				                columnWidth:0.3,
			    	           	layout: 'form',
			    	           	bodyStyle:'padding:5 0 0 0',
			        	       	items: [{
					            	id:'h13',
					            	xtype:'textfield',
					            	fieldLabel: '联系电话',
					            	name:'lxdh_ydbz',
					            	value:user.dwDySet.lxdh_ydbz
								}]
			        		},{
				                columnWidth:0.3,
			    	           	layout: 'form',
			    	           	bodyStyle:'padding:5 0 0 0',
			        	       	items: [{
					            	id:'h14',
					            	xtype:'textfield',
					            	fieldLabel: '领证日期',
					            	name:'lzrq_ydbz',
					            	value:user.dwDySet.lzrq_ydbz
								}]
			        		}]
						},{
							border:false,
							frame: false,
				        	region:'center',
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
					        			text:'保存',
					        			minWidth:60,
					        			handler:function(){
					        				var data = {
					        					cbtzd:Ext.getCmp('h1').getValue(),
					        					kzlzrq:Ext.getCmp('h2').getValue(),
					        					mzlzrq:Ext.getCmp('h3').getValue(),
					        					pcslxdh:Ext.getCmp('h4').getValue(),
					        					dkqckh:Ext.getCmp('h5').getValue(),
					        					mndk:Ext.getCmp('h6').checked?1:0,
					        					ywlimit:Ext.getCmp('h7').getValue(),
					        					xkjdk:Ext.getCmp('h8').checked?1:0,
					        					pcsmc:Ext.getCmp('h9').getValue(),
					        					pcsyb:Ext.getCmp('h10').getValue(),
					        					pcsdz:Ext.getCmp('h11').getValue(),
					        					pcsdh:Ext.getCmp('h12').getValue(),
					        					lxdh_ydbz:Ext.getCmp('h13').getValue(),
					        					lzrq_ydbz:Ext.getCmp('h14').getValue()
					        				}
					        				Gnt.submit(
														data, 
														"gl/edzsz/saveEdzDysz", 
														"保存二代证打印设置，请稍后...", 
														function(jsonData,params){
															showInfo("二代证打印设置保存成功,下次登录会生效！");
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
							
						}]
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