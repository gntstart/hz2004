Gnt.ux.SelectXzqhWhModify= Ext.extend(Ext.Window, {
	title:'行政区划修改',
	closeAction:'hide',
	modal:true,
	width:500,
	height:300,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(selRes){
		this.dm = selRes.data.dm;
		Gnt.ux.SelectXzqhWhModify.superclass.show.call(this);
	},
	setSelRes:function(selRes){
		this.selRes = selRes;
		if(selRes){
			this.fs.getForm().setValues({
				dm: selRes.data.dm,
				mc: selRes.data.mc,
				zwpy: selRes.data.zwpy,
				wbpy: selRes.data.wbpy,
				bz:selRes.data.bz
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
								name:'dm',
								allowBlank:false,
								disabled:true,
								fieldLabel:'区域代码'
							}]
						},{
					                columnWidth:1,
				    	           	layout: 'form',
				    	           	bodyStyle:'padding:5 0 0 0',
				        	       	items: [{
										xtype:'textfield',
										anchor:'100%',
										name:'mc',
										//allowBlank:false,
										fieldLabel:'区域名称',
					                    listeners: {
					                    }
									}]
								},{
					                columnWidth:1,
				    	           	layout: 'form',
				    	           	bodyStyle:'padding:5 0 0 0',
				        	       	items: [{
										xtype:'textfield',
										anchor:'100%',
										name:'zwpy',
										fieldLabel:'中文拼音',
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
										name:'wbpy',
										fieldLabel:'五笔拼音'
									}]
								},{
					                columnWidth:1,
				    	           	layout: 'form',
				    	           	bodyStyle:'padding:0 0 0 0',
				        	       	items: [{
										xtype:'textarea',
										height:100,
										anchor:'100%',
										name:'bz',
										fieldLabel:'备注'
									}]
								}
				]
			}],
	    	buttons:[
	    	 		{
	    	 			text:'确定',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var rootWin = this.findParentByType("modifyxzqhWindow");
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
	    	 				lhdz.dm = rootWin.dm;
	    	 				if(rootWin.callback){
								rootWin.callback('lh', lhdz);
							}
							rootWin.hide();
	    	 			}
	    	 		},{
	    	 			text:'取消',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var win = this.findParentByType("modifyxzqhWindow");
	    	 				win.hide();
	    	 			}
	    	 		}
	    	 	]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.SelectXzqhWhModify.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('modifyxzqhWindow', Gnt.ux.SelectXzqhWhModify);
