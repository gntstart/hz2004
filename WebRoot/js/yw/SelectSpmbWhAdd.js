Gnt.ux.SelectSpmbWhAdd= Ext.extend(Ext.Window, {
	title:'审批模板增加',
	closeAction:'hide',
	modal:true,
	width:500,
	height:200,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(){
		Gnt.ux.SelectSpmbWhAdd.superclass.show.call(this);
	},
	setSelRes:function(){
		this.fs.getForm().setValues({
			mbmc: '',
			mbdj: ''
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
									name:'mbmc',
									allowBlank:false,
									fieldLabel:'模板名称'
								}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype : "combo",
			            		store : [['1','一级'],['2','二级'],['3','三级'],['4','四级']],
		            			border:false,
		            			frame:false,
		            			id:'addmbdj',
		            			name:'mbdj',
		            			fieldLabel:"模板等级",
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
	    	 				var rootWin = this.findParentByType("addspmbWindow");
	    	 				var form = rootWin.items.get(0);
	    	 				if(!form.getForm().isValid()){
	    						showErr("信息必须填写！");
	    						return;
	    					}
	    	 				var lhdz = form.getForm().getValues();
	    	 				lhdz.mbdj = Ext.getCmp('addmbdj').getValue();
	    	 				if(rootWin.callback){
								rootWin.callback('lh', lhdz);
							}
							rootWin.hide();
	    	 			}
	    	 		},{
	    	 			text:'取消',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var win = this.findParentByType("addspmbWindow");
	    	 				win.hide();
	    	 			}
	    	 		}
	    	 	]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.SelectSpmbWhAdd.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('addspmbWindow', Gnt.ux.SelectSpmbWhAdd);
