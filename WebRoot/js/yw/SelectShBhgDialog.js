Gnt.ux.SelectShBhgDialog= Ext.extend(Ext.Window, {
	title:'填写审核不通过原因',
	closeAction:'hide',
	modal:true,
	width:500,
	height:150,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(){
		Gnt.ux.SelectShBhgDialog.superclass.show.call(this);
	},
	setSelRes:function(){
		this.fs.getForm().setValues({
			zzxxcwlb: '',
			cwms: ''
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
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
		        	       		anchor:'100%',
								xtype:'dict_combox',
								dict:'VisionType=CS_ZZXXCWLB',
								name:'zzxxcwlb',
								maxLength:40,
								hiddenName:'zzxxcwlb',
								allowBlank:false,
								fieldLabel:'错误类型',
								listeners: {
			                    	change: function (me, newValue, oldValue, eOpts) {
			                    		if(newValue){
			                    			Ext.getCmp('qdBtn').enable();
			                    		}else{
			                    			Ext.getCmp('qdBtn').disable();
			                    		}
							        }
			                    }
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'cwms',
								fieldLabel:'错误描述'
							}]
						}
					]
				}]
			}],
	    	buttons:[
	    	 		{
	    	 			text:'确定',
	    	 			id:'qdBtn',
	    	 			disabled:true,
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var rootWin = this.findParentByType("shBhgDialog");
	    	 				var form = rootWin.items.get(0);
	    	 				if(!form.getForm().isValid()){
	    						showErr("错误类型必须填写！");
	    						return;
	    					}
	    	 				var lhdz = form.getForm().getValues();
	    	 				if(rootWin.callback){
								rootWin.callback('lh', lhdz);
							}
							rootWin.hide();
	    	 			}
	    	 		},{
	    	 			text:'取消',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var win = this.findParentByType("shBhgDialog");
	    	 				win.hide();
	    	 			}
	    	 		}
	    	 	]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.SelectShBhgDialog.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('shBhgDialog', Gnt.ux.SelectShBhgDialog);
