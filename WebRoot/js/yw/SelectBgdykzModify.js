Gnt.ux.SelectBgdykzModify= Ext.extend(Ext.Window, {
	title:'数据字典修改',
	closeAction:'hide',
	modal:true,
	width:500,
	height:300,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(selRes){
		this.bzdz = selRes.data.zdmc;
		Gnt.ux.SelectBgdykzModify.superclass.show.call(this);
	},
	setSelRes:function(selRes){
		this.selRes = selRes;
		if(selRes){
			this.fs.getForm().setValues({
				zdkzid: selRes.data.zdkzid,
				zdmc: selRes.data.zdmc,
				zdhy: selRes.data.zdhy,
				kzbz: selRes.data.kzbz
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
        	       		xtype:'hidden',
        	       		name:'zdkzid'
	            	},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'zdmc',
								id:'zdmcModify',
								allowBlank:false,
								disabled:true,
								fieldLabel:'字段名称'
							}]
						},{
					                columnWidth:1,
				    	           	layout: 'form',
				    	           	bodyStyle:'padding:5 0 0 0',
				        	       	items: [{
										xtype:'textfield',
										anchor:'100%',
										name:'zdhy',
										//id:'zdhyModify',
										//allowBlank:false,
										fieldLabel:'字段含义',
					                    listeners: {
					                    }
									}]
								},{
					                columnWidth:1,
				    	           	layout: 'form',
				    	           	bodyStyle:'padding:5 0 0 0',
				        	       	items: [{
										xtype : "combo",
					            		store : [['0','0-不控制'],['1','1-控制']],
				            			border:false,
				            			frame:false,
				            			name:'kzbz',
				            			id:'kzbzmodify',
				            			fieldLabel:"控制标志",
				            			width:60,
				            			//editable:false,
				            			triggerAction:"all",
				            			maxHeight : 80,
				            			anchor : '100%'
									}]
								}
				]
			}],
	    	buttons:[
	    	 		{
	    	 			text:'保存',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var rootWin = this.findParentByType("modifybgdykz_window");
	    	 				var form = rootWin.items.get(0);
	    	 				if(!form.getForm().isValid()){
	    						showErr("变更打印控制必须填写！");
	    						return;
	    					}
	    	 				var lhdz = form.getForm().getValues();
	    	 				lhdz.zdmc = Ext.getCmp('zdmcModify').getValue();
	    	 				lhdz.kzbz =	Ext.getCmp('kzbzmodify').getValue();
	    	 				
	    	 				if(rootWin.callback){
								rootWin.callback('lh', lhdz);
							}
							rootWin.hide();
	    	 			}
	    	 		},{
	    	 			text:'关闭',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var win = this.findParentByType("modifybgdykz_window");
	    	 				win.hide();
	    	 			}
	    	 		}
	    	 	]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.SelectBgdykzModify.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('modifybgdykz_window', Gnt.ux.SelectBgdykzModify);
