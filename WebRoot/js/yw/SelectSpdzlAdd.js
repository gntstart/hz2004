Gnt.ux.SelectSpdzlAdd= Ext.extend(Ext.Window, {
	title:'流动作设置',
	closeAction:'hide',
	modal:true,
	width:500,
	height:200,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(selRes,xygdzArray){
		this.data = selRes.data;
		Gnt.ux.SelectSpdzlAdd.superclass.show.call(this);
	},
	setSelRes:function(selRes,selMbRes,xygdzArray){
		this.selRes = selRes;
		if(selRes){
			this.fs.getForm().setValues({
				spmbid: selMbRes.data.spmbid,
				dzid: selRes.data.dzid,
				dzmc: selRes.data.dzmc
			});
		}
		var f = this.fs.getForm().findField("xgdzid");
		f.setValue("");
		f.store.removeAll();
		var list = new Array();
		for(var i=0;i<xygdzArray.length;i++){
			var map =xygdzArray[i];
			list[i] = new Array();
			list[i][0]=map[0];
			list[i][1]=map[1];
			list[i][2]=map;
		}
		f.store.loadData(list);
	},
	resetData:function(){
		this.fs.getForm().reset();
	},
	initComponent : function(){
		var fs = new Ext.form.FormPanel({
	    	height: 80,
	    	region: 'north',
	    	anchor:'100% 100%',
	    	buttonAlign:'right',
	    	labelAlign:'right',
	    	frame:true,
	    	border:false,
	    	fileUpload: true, 
	    	margins:'0',
	        layout:'form',
	        labelWidth:100,
	       	items:[{
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
		        			frame:false
		        		},
		            	items:[{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	hidden:true,
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
		            			xtype : 'textfield',
		            			border:false,
		            			frame:false,
		            			fieldLabel:"",
		            			allowBlank:false,
		            			anchor : '100%',
		            			name:'spmbid'
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	hidden:true,
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
		            			xtype : 'textfield',
		            			border:false,
		            			frame:false,
		            			fieldLabel:"",
		            			allowBlank:false,
		            			anchor : '100%',
		            			name:'dzid'
							}]
						},{
				                columnWidth:1,
			    	           	layout: 'form',
			    	           	bodyStyle:'padding:5 0 0 0',
			        	       	items: [{
									xtype:'textfield',
									anchor:'100%',
									name:'dzmc',
									disabled:true,
									allowBlank:false,
									fieldLabel:'动作名称'
								}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype : "combo",
			            		store : [['0','0-不同意'],['1','1-同意'],['2','2-无效']],
		            			border:false,
		            			frame:false,
		            			id:'adddzz',
		            			name:'dzz',
		            			fieldLabel:"动作值",
		            			width:60,
		            			allowBlank:false,
		            			//editable:false,
		            			triggerAction:"all",
		            			maxHeight : 80,
		            			anchor : '100%'
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
		        	       		name: 'xgdzid',
		    	                hiddenName: 'xgdzid',
		    	                fieldLabel: '下一个动作',
		    					allowBlank: false,
		    	                xtype: 'dict_combox',
		    	                dict:'VisionType=_BLANK',
		    	                anchor:'100%',
		    			        displayField:'name'/*,
		    			        listeners:{
				            		'change' : function(){
				            			alert();
				            		}
				                //监听事件
					            }*/
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype : "combo",
			            		store : [['0','0-开始步'],['1','1-单一步(含开始与结束)'],['2','2-中间步'],['9','9-结束步']],
		            			border:false,
		            			frame:false,
		            			id:'adddzbz',
		            			name:'dzbz',
		            			fieldLabel:"动作标志",
		            			width:60,
		            			allowBlank:false,
		            			//editable:false,
		            			triggerAction:"all",
		            			maxHeight : 80,
		            			anchor : '100%'
							}]
						}
					]
				}]
			}],
	    	buttons:[
	    	 		{
	    	 			text:'确定',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var rootWin = this.findParentByType("addsplWindow");
	    	 				var form = rootWin.items.get(0);
	    	 				if(!form.getForm().isValid()){
	    						showErr("流动作必须填写！");
	    						return;
	    					}
	    	 				var lhdz = form.getForm().getValues();
	    	 				lhdz.dzz = Ext.getCmp('adddzz').getValue();
	    	 				lhdz.dzbz = Ext.getCmp('adddzbz').getValue();
	    	 				if(rootWin.callback){
								rootWin.callback('lh', lhdz);
							}
							rootWin.hide();
	    	 			}
	    	 		},{
	    	 			text:'取消',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var win = this.findParentByType("addsplWindow");
	    	 				win.hide();
	    	 			}
	    	 		}
	    	 	]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.SelectSpdzlAdd.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('addsplWindow', Gnt.ux.SelectSpdzlAdd);
