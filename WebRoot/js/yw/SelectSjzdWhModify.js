Gnt.ux.SelectSjzdWhModify= Ext.extend(Ext.Window, {
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
		Gnt.ux.SelectSjzdWhModify.superclass.show.call(this);
	},
	setSelRes:function(selRes){
		this.selRes = selRes;
		if(selRes){
			this.fs.getForm().setValues({
				zdid: selRes.data.zdid,
				zdmc: selRes.data.zdmc,
				zdhy: selRes.data.zdhy,
				zdlx: selRes.data.zdlx,
				zdcd:selRes.data.zdcd,
				zdmj: selRes.data.zdmj
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
        	       		name:'zdid'
	            	},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'zdmc',
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
//				        	       		xtype : "combo",
//				        	       		anchor:'100%',
//				        	       		store : [['BLOB','BLOB'],['CHAR','CHAR'],['NUMBER','NUMBER'],['VARCHAR2','VARCHAR2']]
//										name:'zdlx',
//										maxLength:40,
//							 			triggerAction:"all",
//										fieldLabel:'字段类型',
//										allowBlank:false,
										xtype : "combo",
					            		store : [['BLOB','BLOB'],['CHAR','CHAR'],['NUMBER','NUMBER'],['VARCHAR2','VARCHAR2']],
				            			border:false,
				            			frame:false,
				            			name:'zdlx',
				            			fieldLabel:"字段类型",
				            			width:60,
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
										xtype:'textfield',
										anchor:'100%',
										name:'zdcd',
										fieldLabel:'字段长度',
										listeners:{
										}
									}]
								},{
					                columnWidth:1,
				    	           	layout: 'form',
				    	           	bodyStyle:'padding:5 0 0 0',
				        	       	items: [{
										xtype:'textfield',
										anchor:'100%',
										name:'zdmj',
										fieldLabel:'字段密级'
									}]
								}
				]
			}],
	    	buttons:[
	    	 		{
	    	 			text:'保存',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var rootWin = this.findParentByType("modifysjzdwh_window");
	    	 				var form = rootWin.items.get(0);
	    	 				if(!form.getForm().isValid()){
	    						showErr("数据字典必须填写！");
	    						return;
	    					}
	    	 				var lhdz = form.getForm().getValues();
//							if(rootWin.moreData){
//	    	 					for(o in rootWin.moreData)
//	    	 						lhdz[o] = rootWin.moreData[o];
//	    	 				}
	    	 				lhdz.zdmc = rootWin.bzdz;
	    	 				if(rootWin.callback){
								rootWin.callback('lh', lhdz);
							}
							rootWin.hide();
	    	 			}
	    	 		},{
	    	 			text:'关闭',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var win = this.findParentByType("modifysjzdwh_window");
	    	 				win.hide();
	    	 			}
	    	 		}
	    	 	]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.SelectSjzdWhModify.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('modifysjzdwh_window', Gnt.ux.SelectSjzdWhModify);
