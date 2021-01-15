Gnt.ux.SelectSpdzWhAdd= Ext.extend(Ext.Window, {
	title:'审批动作新增',
	closeAction:'hide',
	modal:true,
	width:400,
	height:300,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(){
		Gnt.ux.SelectSpdzWhAdd.superclass.show.call(this);
	},
	setSelRes:function(){
		this.fs.getForm().setValues({
			dzmc: '',
			dzdj: '',
			ms:''
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
									xtype:'textfield',
									anchor:'100%',
									name:'dzmc',
									allowBlank:false,
									fieldLabel:'动作名称'
								}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype : "combo",
			            		store : [['10','本派出所内'],['11','本派出所外'],['20','本区县内'],['21','本区县外'],['30','本地市内'],['31','本地市外'],['99','全部范围']],
		            			border:false,
		            			frame:false,
		            			id:'fwjbAdd',
		            			name:'fwjb',
		            			fieldLabel:"范围级别",
		            			width:60,
		            			allowBlank:false,
		            			triggerAction:"all",
		            			maxHeight : 80,
		            			anchor : '100%'
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textarea',
								anchor:'100%',
								name:'ms',
								allowBlank:false,
								fieldLabel:'动作描述'
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
	    	 				var rootWin = this.findParentByType("addspdzWindow");
	    	 				var form = rootWin.items.get(0);
	    	 				if(!form.getForm().isValid()){
	    						showErr("审批动作信息必须填写！");
	    						return;
	    					}
	    	 				var lhdz = form.getForm().getValues();
	    	 				lhdz.fwjb = Ext.getCmp('fwjbAdd').getValue();
	    	 				if(rootWin.callback){
								rootWin.callback('lh', lhdz);
							}
							rootWin.hide();
	    	 			}
	    	 		},{
	    	 			text:'取消',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var win = this.findParentByType("addspdzWindow");
	    	 				win.hide();
	    	 			}
	    	 		}
	    	 	]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.SelectSpdzWhAdd.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('addspdzWindow', Gnt.ux.SelectSpdzWhAdd);
