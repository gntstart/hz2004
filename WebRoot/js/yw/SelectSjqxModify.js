Gnt.ux.SelectSjqxModify= Ext.extend(Ext.Window, {
	title:'业务办理限制信息修改',
	closeAction:'hide',
	modal:true,
	width:500,
	height:300,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(selRes,ywqxSelRes){
		this.data1 = selRes.data;
		this.data2 = ywqxSelRes.data;
		Gnt.ux.SelectSjqxModify.superclass.show.call(this);
	},
	setSelRes:function(selRes,ywqxSelRes){
		this.fs.getForm().setValues({
			qxkzid:ywqxSelRes.data.qxkzid,
			ywmc:ywqxSelRes.data.ywmc,
			xqlx:ywqxSelRes.data.xqlx,
			sjfwxz: ywqxSelRes.data.sjfwxz,
			yhdtcx:ywqxSelRes.data.yhdtcx,
			gnlx:selRes.data.dm
		});
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
		            			name:'qxkzid'
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
		            			xtype : 'textfield',
		            			border:false,
		            			frame:false,
		            			fieldLabel:"业务类别",
		            			allowBlank:false,
		            			disabled:true,
		            			anchor : '100%',
		            			name:'gnlx'
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								border:false,
		            			frame:false,
		            			fieldLabel:'业务名称',
								allowBlank:false,
								disabled:true,
								anchor:'100%',
								name:'ywmc'
							}]
						},{
						columnWidth:1,
			           	layout: 'form',
			           	bodyStyle:'padding:5 0 0 0',
		            	items:[{
		            		xtype : "combo",
		            		store : [['1','1-辖区内'],['2','2-辖区外']],
	            			border:false,
	            			frame:false,
	            			name:'xqlx',
	            			id:'xqlxModify',
	            			fieldLabel:"辖区类型",
	            			width:60,
	            			//editable:false,
	            			triggerAction:"all",
	            			maxHeight : 80,
	            			anchor : '100%'}
							]
						},{
							columnWidth:1,
				           	layout: 'form',
				           	bodyStyle:'padding:5 0 0 0',
			            	items:[{
			            		xtype : "combo",
			            		store : [['0','0-不限制'],['1','1-限制']],
		            			border:false,
		            			frame:false,
		            			name:'sjfwxz',
		            			id:'sjfwxzModify',
		            			fieldLabel:"数据范围限制",
		            			width:60,
		            			//editable:false,
		            			triggerAction:"all",
		            			maxHeight : 80,
		            			anchor : '100%'}
								]
							},{
								columnWidth:1,
					           	layout: 'form',
					           	bodyStyle:'padding:5 0 0 0',
				            	items:[{
				            		xtype : "combo",
				            		store : [['0','0-不启用'],['1','1-启用']],
			            			border:false,
			            			frame:false,
			            			name:'yhdtcx',
			            			id:'yhdtcxModify',
			            			fieldLabel:"用户等同查询",
			            			width:60,
			            			//editable:false,
			            			triggerAction:"all",
			            			maxHeight : 80,
			            			anchor : '100%'}
									]
								}
					]
				}]
			}],
	    	buttons:[
	    	 		{
	    	 			text:'确定',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var rootWin = this.findParentByType("modifysjqx_window");
	    	 				var form = rootWin.items.get(0);
	    	 				if(!form.getForm().isValid()){
	    						showErr("业务办理限制信息必须填写！");
	    						return;
	    					}
	    	 				var lhdz = form.getForm().getValues();
	    	 				lhdz.ywmc = rootWin.data2.ywmc;
	    	 				lhdz.xqlx = Ext.getCmp('xqlxModify').getValue();
	    	 				lhdz.sjfwxz = Ext.getCmp('sjfwxzModify').getValue();
	    	 				lhdz.yhdtcx = Ext.getCmp('yhdtcxModify').getValue();
	    	 				lhdz.gnlx = rootWin.data1.dm;
	    	 				if(rootWin.callback){
								rootWin.callback('lh', lhdz);
							}
							rootWin.hide();
	    	 			}
	    	 		},{
	    	 			text:'取消',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var win = this.findParentByType("modifysjqx_window");
	    	 				win.hide();
	    	 			}
	    	 		}
	    	 	]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.SelectSjqxModify.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('modifysjqx_window', Gnt.ux.SelectSjqxModify);
