Gnt.ux.KzsjrSetDialog= Ext.extend(Ext.Window, {
	title:'快证收件人设置',
	closeAction:'hide',
	modal:true,
	width:300,
	height:220,
	margins:'5',
	layout:'border',
	show:function(){
		Gnt.ux.KzsjrSetDialog.superclass.show.call(this);
	},
	setSelRes:function(sel){
//		this.fs.getForm().setValues({
//			csrq: sel.data.csrq,//sel.data.csrq,
//			age:jsGetAge(sel.data.csrq),
//			qfrq: CurentTime(0,1),
//			qx:CurentTime(5,0)
//		});
		
	},
	resetData:function(){
		this.fs.getForm().reset();
	},
	initComponent : function(){
		
		var returnTitleText = this.returnTitleText;
		if(!returnTitleText || returnTitleText=="") returnTitleText = "设置快证的收件人信息";
		
		this.returnTitleText = returnTitleText;
		
		this.setTitle(returnTitleText);
		
		
		this.fs = new Ext.form.FormPanel({
	    	region: 'center',
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
									name:'pcsmc',
									fieldLabel:'派出所名称',
									value:user.dwDySet.pcsmc
								}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'pcsyb',
								fieldLabel:'派出所邮编',
								value:user.dwDySet.pcsyb,
			                    listeners: {
			                    	change: function (me, newValue, oldValue, eOpts) {
			                    		
							        }
			                    }
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'pcsdz',
								fieldLabel:'派出所地址',
								value:user.dwDySet.pcsdz,
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
								name:'lxdh',
								fieldLabel:'联系电话',
								value:user.dwDySet.pcsdh
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
    	 				var rootWin = this.findParentByType("kzsjr_window");
    	 				var form = rootWin.items.get(0);
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
    	 				var win = this.findParentByType("kzsjr_window");
    	 				win.hide();
    	 			}
    	 		}
    	 	]
		});
		this.items = [this.fs];
		
		Gnt.ux.KzsjrSetDialog.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('kzsjr_window', Gnt.ux.KzsjrSetDialog);