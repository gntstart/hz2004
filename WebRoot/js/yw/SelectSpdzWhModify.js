Gnt.ux.SelectSpdzWhModify= Ext.extend(Ext.Window, {
	title:'审批动作修改',
	closeAction:'hide',
	modal:true,
	width:400,
	height:300,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(selRes){
		this.data = selRes.data;
		Gnt.ux.SelectSpdzWhModify.superclass.show.call(this);
	},
	setSelRes:function(selRes){
		this.selRes = selRes;
		if(selRes){
			this.fs.getForm().setValues({
				dzmc: selRes.data.dzmc,
				fwjb: selRes.data.fwjb,
				ms:selRes.data.ms
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
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'dzmc',
								allowBlank:false,
								//disabled:true,
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
				            			id:'fwjbmodify',
				            			name:'fwjb',
				            			fieldLabel:"范围级别",
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
										xtype:'textarea',
										anchor:'100%',
										name:'ms',
										allowBlank:false,
										//disabled:true,
										fieldLabel:'动作描述'
									}]
								}
				]
			}],
	    	buttons:[
	    	 		{
	    	 			text:'确定',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var rootWin = this.findParentByType("modifyspdzWindow");
	    	 				var form = rootWin.items.get(0);
	    	 				if(!form.getForm().isValid()){
	    						showErr("审批动作必须填写！");
	    						return;
	    					}
	    	 				var lhdz = form.getForm().getValues();
//							if(rootWin.moreData){
//	    	 					for(o in rootWin.moreData)
//	    	 						lhdz[o] = rootWin.moreData[o];
//	    	 				}
	    	 				lhdz.dzid = rootWin.data.dzid;
	    	 				lhdz.fwjb = Ext.getCmp('fwjbmodify').getValue();
	    	 				if(rootWin.callback){
								rootWin.callback('lh', lhdz);
							}
							rootWin.hide();
	    	 			}
	    	 		},{
	    	 			text:'取消',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var win = this.findParentByType("modifyspdzWindow");
	    	 				win.hide();
	    	 			}
	    	 		}
	    	 	]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.SelectSpdzWhModify.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('modifyspdzWindow', Gnt.ux.SelectSpdzWhModify);
