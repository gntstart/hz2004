Gnt.ux.SelectJsWhAdd= Ext.extend(Ext.Window, {
	title:'角色信息新增',
	closeAction:'hide',
	modal:true,
	width:500,
	height:300,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(){
		Gnt.ux.SelectJsWhAdd.superclass.show.call(this);
	},
	setSelRes:function(){
		this.fs.getForm().setValues({
			jsmc: '',
			ms: ''
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
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'jsmc',
								allowBlank:false,
								fieldLabel:'角色名称'
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textarea',
								height:100,
								anchor:'100%',
								name:'ms',
								allowBlank:false,
								fieldLabel:'描述'
							}]
						}
				]
			}],
	    	buttons:[
	    	 		{
	    	 			text:'确定',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var rootWin = this.findParentByType("addjsWindow");
	    	 				var form = rootWin.items.get(0);
	    	 				if(!form.getForm().isValid()){
	    						showErr("角色信息必须填写！");
	    						return;
	    					}
	    	 				var lhdz = form.getForm().getValues();
//							if(rootWin.moreData){
//	    	 					for(o in rootWin.moreData)
//	    	 						lhdz[o] = rootWin.moreData[o];
//	    	 				}
	    	 				if(rootWin.callback){
								rootWin.callback('lh', lhdz);
							}
							rootWin.hide();
	    	 			}
	    	 		},{
	    	 			text:'取消',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var win = this.findParentByType("addjsWindow");
	    	 				win.hide();
	    	 			}
	    	 		}
	    	 	]
		});
		this.fs = fs;
		this.items = fs;
		Gnt.ux.SelectJsWhAdd.superclass.initComponent.call(this);
	}
});
Ext.reg('addjsWindow', Gnt.ux.SelectJsWhAdd);
