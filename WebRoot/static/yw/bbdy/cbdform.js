Gnt.ux.cbdwin= Ext.extend(Ext.Window, {
	title:'证件催办信息输入',
	closeAction:'hide',
	modal:true,
	width:500,
	height:360,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(type){
		this.type = type;
		Gnt.ux.cbdwin.superclass.show.call(this);
	},
	setSelRes:function(type){
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
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textarea',
								anchor:'100%',
								name:'bzsj',
								allowBlank:true,
								disabled:false,
								fieldLabel:'办证时间'
							}]
						},{
							columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 15',
		    	           	style:'color:red',
							html:'如:2015年1月1号上午至2016年3月3号上午'
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textarea',
								anchor:'100%',
								name:'bzdd',
								allowBlank:true,
								disabled:false,
								fieldLabel:'办证地点'
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'lxdh',
								disabled:false,
								//allowBlank:false,
								fieldLabel:'联系电话',
			                    listeners: {
			                    }
							}]
						}
				]
			}],
	    	buttons:[
	    	 		{
	    	 			text:'确定',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var rootWin = this.findParentByType("cbd_window");
	    	 				var form = rootWin.items.get(0);
	    	 				/*if(!form.getForm().isValid()){
	    						
	    						return;
	    					}*/
	    	 				var content = form.getForm().getValues();
	    	 				if(rootWin.callback){
								rootWin.callback(rootWin.type, content);
							}
							rootWin.hide();
	    	 			}
	    	 		},{
	    	 			text:'关闭',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var win = this.findParentByType("cbd_window");
	    	 				win.hide();
	    	 			}
	    	 		}
	    	 	]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.cbdwin.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('cbd_window', Gnt.ux.cbdwin);
