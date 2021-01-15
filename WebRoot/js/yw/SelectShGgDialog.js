Gnt.ux.SelectShGgDialog= Ext.extend(Ext.Window, {
	title:'审核结果选择',
	closeAction:'hide',
	modal:true,
	width:300,
	height:250,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(){
		Gnt.ux.SelectShGgDialog.superclass.show.call(this);
	},
	setSelRes:function(){
		this.fs.getForm().setValues({
			ksh: '1',
			jsh: '80'
		});
	},
	resetData:function(){
		this.fs.getForm().reset();
	},
	initComponent : function(){
		
		var fs = new Ext.form.FormPanel({
	    	height: 280,
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
        		region:'north',
        		layout : 'column',
	            title: '选择全部审核结果',
        		xtype: 'fieldset',
        		defaults : {
        			xtype:'radio',
	            	columnWidth: .5,
	            	fieldLabel: '',
	            	bodyStyle:'padding:5 0 0 0',
	            	name: 'qbshjg'
				},
				items:[{
					boxLabel: '合格',
	            	name:'qbshjg',
	            	inputValue : "1",
	            	checked:true
				},{
					boxLabel: '不合格',
	            	name:'qbshjg',
	            	inputValue : "2"
				}]
        	},{
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
		        			columnWidth:0.5,
		        			labelWidth:80,
		        			frame:false
		        		},
		            	items:[{
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
		        	       		anchor:'100%',
								name:'ksh',
			        			xtype:'numberfield',
								maxLength:40,
								fieldLabel:'开始行',
								listeners: {
			                    	change: function (me, newValue, oldValue, eOpts) {
			                    		if(newValue){
			                    			Ext.getCmp('qdBtn').enable();
			                    		}else{
			                    			Ext.getCmp('qdBtn').disable();
			                    		}
							        }
			                    }
							}]
						},{
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								anchor:'100%',
			        			xtype:'numberfield',
								name:'jsh',
								fieldLabel:'结束行'
							}]
						}
					]
				}]
			},{
        		region:'south',
        		layout : 'column',
	            title: '已审核信息是否重新审核',
        		xtype: 'fieldset',
        		defaults : {
        			xtype:'radio',
	            	columnWidth: .5,
	            	fieldLabel: '',
	            	bodyStyle:'padding:5 0 0 0',
	            	name: 'cxsh'
				},
				items:[{
					boxLabel: '不重新审核',
	            	name:'cxsh',
	            	inputValue : "1",
	            	checked:true
				},{
					boxLabel: '重新审核',
	            	name:'cxsh',
	            	inputValue : "2"
				}]
        	}],
	    	buttons:[
	    	 		{
	    	 			text:'确定',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var rootWin = this.findParentByType("shGgDialog");
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
	    	 				var win = this.findParentByType("shGgDialog");
	    	 				win.hide();
	    	 			}
	    	 		}
	    	 	]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.SelectShGgDialog.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('shGgDialog', Gnt.ux.SelectShGgDialog);
