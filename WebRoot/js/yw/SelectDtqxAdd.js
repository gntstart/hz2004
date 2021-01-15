Gnt.ux.SelectDtqxAdd= Ext.extend(Ext.Window, {
	title:'等同权限增加',
	closeAction:'hide',
	modal:true,
	width:500,
	height:300,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(type,sels){
		this.type = type;
		if(type=='2'){
			Ext.getCmp('yhidDtqx').hide();
			this.yhid = sels.data.yhid;
		}else if(type=='1'){
			Ext.getCmp('yhidDtqx').show();
		}
		Gnt.ux.SelectDtqxAdd.superclass.show.call(this);
	},
	setSelRes:function(){
		this.fs.getForm().setValues({
			yhid:'',
			dtyhid: ''
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
		    	           	id:'yhidDtqx',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'search_combox',
								anchor:'100%',
								searchUrl:'dict/utils/searchYhxx?optype=eq',
								fields:["code","name","pyt"],
								valueField: "pyt",
								displayField: "name",
								fieldLabel:'用户ID',
								hiddenName:'yhid'
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'search_combox',
								anchor:'100%',
								searchUrl:'dict/utils/searchYhxx?optype=eq',
								fields:["code","name","pyt"],
								valueField: "pyt",
								displayField: "name",
								allowBlank:false,
								fieldLabel:'等同用户ID',
								hiddenName:'dtyhid'
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
	    	 				var rootWin = this.findParentByType("adddtqx_window");
	    	 				var form = rootWin.items.get(0);
	    	 				if(!form.getForm().isValid()){
	    						showErr("业务办理限制信息必须填写！");
	    						return;
	    					}
	    	 				var lhdz = form.getForm().getValues();
	    	 				if(rootWin.type=='2'){
	    	 					lhdz.yhid=rootWin.yhid
	    	 				}
	    	 				if(lhdz.yhid==lhdz.dtyhid){
	    	 					alert("不允许用户ID与等同用户ID一致!")
	    	 					return;
	    	 				}
	    	 				if(rootWin.callback){
								rootWin.callback('lh', lhdz);
							}
							rootWin.hide();
	    	 			}
	    	 		},{
	    	 			text:'取消',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var win = this.findParentByType("adddtqx_window");
	    	 				win.hide();
	    	 			}
	    	 		}
	    	 	]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.SelectDtqxAdd.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('adddtqx_window', Gnt.ux.SelectDtqxAdd);
