Gnt.ux.SelectLssfz= Ext.extend(Ext.Window, {
	title:'临时身份证打印',
	closeAction:'hide',
	modal:true,
	width:400,
	height:200,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(){
		Gnt.ux.SelectLssfz.superclass.show.call(this);
	},
	setSelRes:function(data){
		Ext.getCmp("save").disable();
		if(data){
			this.lsslid = data.lsslid
			var cmp=Ext.getCmp("sfzhm");
			cmp.setValue(data.gmsfhm);
			cmp.setDisabled(true);
			var ls_cmp=Ext.getCmp("lsjmsfzkh");
			ls_cmp.setValue(data.lsjmsfzkh);
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
									id : 'sfzhm',
									name : 'gmsfhm',
									allowBlank:false,
									fieldLabel:'公民身份证号码'
								}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'numberfield',
								anchor:'100%',
								id : 'lsjmsfzkh', 
								name:'lsjmsfzkh',
								autoCreate: {tag: 'input', type: 'text', size: '11', autocomplete: 'off', maxlength: '11'},
								allowBlank:false,
								fieldLabel:'请输入临时身份证卡号',
								listeners: {
			                    	change: function (me, newValue, oldValue, eOpts) {
			                    		if(me.value>9999999999){
			                    			Ext.getCmp("save").enable();
			                    		}
							        },
							        focus:function(){
							        	if(Ext.getCmp("lsjmsfzkh").getValue()>9999999999){
							        		Ext.getCmp("save").enable();
							        	}
							        	
							        }
			                    }
							}]
						}
					]
				}]
			}],
	    	buttons:[
	    	 		{
	    	 			text:'确定',
	    	 			id:'save',
	    	 			minWidth:75,
	    	 			disabled:true,
	    	 			handler:function(){
	    	 				var rootWin = this.findParentByType("lssfz_window");
	    	 				var form = rootWin.items.get(0);
	    	 				if(!form.getForm().isValid()){
	    						showErr("身份证信息必须填写！");
	    						return;
	    					}
	    	 				var lhdz = form.getForm().getValues();
							lhdz.lsjmsfzkh= Ext.getCmp("lsjmsfzkh").getValue();
							lhdz.lsslid = rootWin.lsslid;
							Gnt.submit(
									lhdz, 
									"cx/lzcx/khxg", 
									"保存临时身份证修改信息，请稍后...", 
									function(jsonData,params){
										showInfo("临时身份证修改信息保存成功！");
										if(rootWin.callback){
											rootWin.callback('lh', lhdz);
										}
										rootWin.hide();
									},
									function(jsonData,params){
										alert(jsonData.message);
									}
							);
							
	    	 				
	    	 			}
	    	 		},{
	    	 			text:'取消',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var win = this.findParentByType("lssfz_window");
	    	 				win.hide();
	    	 			}
	    	 		}
	    	 	]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.SelectLssfz.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('lssfz_window', Gnt.ux.SelectLssfz);
//new Ext.FormPanel({  
//    region : 'center',  
//    labelWidth : 100,  
//    frame : true,  
//    bodyStyle : 'padding:5px', 
//    border : true,  
//    layout:'form',
//    items : [{
//        xtype : 'textfield',  
//        fieldLabel : '公民身份证号码',  
//        id : 'sfzhm',  
//        name : 'gmsfhm', 
//        allowBlank : false   
//        //anchor : '90%'  
//    },
//    {  
//        xtype : 'textfield',  
//        fieldLabel : '请输入临时身份证卡号',  
//        id : 'lsjmsfzkh',  
//        name : 'lsjmsfzkh', 
//        allowBlank : false   
//        //anchor : '90%'  
//    }
//		],
//    buttons : [{  
//        text : '确认',  
//        handler : function() { 
//            if (!inputform.form.isValid()) {return;}  
//               var cmp=Ext.getCmp("sfzhm");
//				var ls_cmp=Ext.getCmp("lsjmsfzkh"); 
//				alert(cmp+"-------"+ls_cmp);
//            /*var store = upshzGrid.store;
//			store.baseParams = {
//					lsslid:selectLsslid,
//						config_key:'updateSfzxxcx',
//						start:0,
//						limit:20
//				};
//			store.load();*/
//			Ext.getCmp("queryId").handler();
//			win.close(this);
//        }  
//    }, {  
//        text : '取消',  
//        handler : function() {  
//            win.close(this);  
//        }  
//    }]  
//});
