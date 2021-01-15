Gnt.ux.SelectDblkzModify= Ext.extend(Ext.Window, {
	title:'异地制证-打包量控制设置',
	closeAction:'hide',
	modal:true,
	width:300,
	height:200,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(selRes){
		this.data = selRes.data;
		Gnt.ux.SelectDblkzModify.superclass.show.call(this);
	},
	setSelRes:function(selRes){
		this.selRes = selRes;
		if(selRes){
			this.fs.getForm().setValues({
				sbddm:selRes.data.sbddm,
				sbdmc: selRes.data.sbdmc,
				jbdbl:selRes.data.jbdbl
			});
		}
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
	    	           	hidden:true,
	    	           	bodyStyle:'padding:5 0 0 0',
	        	       	items: [{
							xtype:'textfield',
							anchor:'100%',
							name:'sbddm',
							allowBlank:false,
							
							fieldLabel:'申报点代码'
						}]
					},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'sbdmc',
								allowBlank:false,
								disabled:true,
								fieldLabel:'申报点代码'
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'jbdbl',
								allowBlank:false,
								fieldLabel:'打包量设置'
							}]
						}
				]
			}],
	    	buttons:[
	    	 		{
	    	 			text:'确定',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var rootWin = this.findParentByType("modifyDblkz_window");
	    	 				var form = rootWin.items.get(0);
	    	 				if(!form.getForm().isValid()){
	    						showErr("打包量控制信息必须填写！");
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
	    	 				var win = this.findParentByType("modifyDblkz_window");
	    	 				win.hide();
	    	 			}
	    	 		}
	    	 	]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.SelectDblkzModify.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('modifyDblkz_window', Gnt.ux.SelectDblkzModify);
