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
	

	var p2 = new Ext.Panel({
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
						id: "panelHtmlId" ,
						html: '<div class="text" style="text-align:center;"><br /><h2>零散办证</h2></div>',
						height: 80,
						region:'north',
						bodyStyle:'padding:15px'
					},
					{
						id:"southId",
			        	region:'center',
			        	height:250,
			            border:false,
			            frame:false,
			            layout:'form',
			            items:[{
			            	title: '',
				            xtype: 'fieldset',
				            autoHeight: true,
				            layout:'column',
				            defaults:{
			        			border:false,
			        			labelWidth:160,
			        			frame:false
			        		},
			        		items:[{
				                columnWidth:1,
			    	           	layout: 'form',
			    	           	bodyStyle:'padding:5 0 0 0',
			        	       	items: [{
					            	id:'g1',
					            	xtype:'checkbox',
					            	fieldLabel: '打印居民身份证申请表',
					            	name:'dyjmsfzsqb',
					            	checked:user.personSet.dyjmsfzsqb_lsbz==1?true:false
								}]
			        		},{
				                columnWidth:1,
			    	           	layout: 'form',
			    	           	bodyStyle:'padding:5 0 0 0',
			        	       	items: [{
					            	id:'g2',
					            	anchor:'99%',
					            	fieldLabel:'身份证领取单相关内容',
					            	xtype:'textarea',
					            	name:'sfzlqdxgnr',
					            	value:user.personSet.sfzlqdxgnr_lsbz
								}]
			        		}]
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
					        					dyjmsfzsqb_lsbz:Ext.getCmp('g1').checked?1:0,
					        					sfzlqdxgnr_lsbz:Ext.getCmp('g2').getValue()
					        				}
					        				Gnt.submit(
														data, 
														"gl/lsbzsz/saveDysz", 
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
        	items:[p2]
        }
    });
	Ext.getCmp('nextId').setVisible(false);
});