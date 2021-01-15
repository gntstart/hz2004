Gnt.ux.SelectXzjdWhAdd= Ext.extend(Ext.Window, {
	title:'乡镇街道新增',
	closeAction:'hide',
	modal:true,
	width:500,
	height:500,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(){
		Gnt.ux.SelectXzjdWhAdd.superclass.show.call(this);
	},
	setSelRes:function(){
		this.fs.getForm().setValues({
			qhdm:'',
			dm: '',
			mc: '',
			zwpy: '',
			wbpy: '',
			bz:'',
			qybzdm:'',
			xdm:'',
			dzys:'',
			dzysmc:''
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
								xtype:'search_combox',
								anchor:'100%',
								searchUrl:'dict/utils/searchXzqh.json?qybz=1',
								fields:["code","name"],
								valueField: "code",
								displayField: "name",
//								id:'xzqhbdm',
								allowBlank:false,
								fieldLabel:'所属区划',
								id:'qhdmAdd',
								hiddenName:'qhdm'
//								hiddenName:'xzqhbdm',
//								name:'xzqhbmc'
							}]
						},{
				                columnWidth:1,
			    	           	layout: 'form',
			    	           	bodyStyle:'padding:5 0 0 0',
			        	       	items: [{
									xtype:'textfield',
									anchor:'100%',
									name:'dm',
									allowBlank:false,
									fieldLabel:'乡镇街道代码'
								}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'mc',
								allowBlank:false,
								fieldLabel:'乡镇街道名称',
			                    listeners: {
			                    	change: function (me, newValue, oldValue, eOpts) {
			                    		Ext.getCmp('zwpy1').setValue(pinyin.getCamelChars(newValue));
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
								id:'zwpy1',
								name:'zwpy',
								allowBlank:false,
								fieldLabel:'乡镇街道中文拼音',
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
								fieldLabel:'乡镇街道五笔拼音'
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
		        	       		xtype : "combo",
			            		store : [['21','乡'],['22','镇'],['23','办事处(街道、地区)'],['29','其他']],
		            			border:false,
		            			frame:false,
		            			id:'dzysIdA',
		            			name:'dzys',
		            			fieldLabel:"乡镇街道类型",
		            			triggerAction:"all",
		            			maxHeight : 80,
		            			anchor : '100%'
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
//								allowBlank:false,
								fieldLabel:'备注'
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
	    	 				var rootWin = this.findParentByType("addxzjdxxwh_window");
	    	 				var form = rootWin.items.get(0);
	    	 				if(!form.getForm().isValid()){
	    						showErr("单位信息必须填写！");
	    						return;
	    					}
	    	 				var lhdz = form.getForm().getValues();
	    	 				lhdz.qhdm = Ext.getCmp('qhdmAdd').getValue();
	    	 				lhdz.dzys = Ext.getCmp('dzysIdA').getValue();
	    	 				if(rootWin.callback){
								rootWin.callback('lh', lhdz);
							}
							rootWin.hide();
	    	 			}
	    	 		},{
	    	 			text:'取消',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var win = this.findParentByType("addxzjdxxwh_window");
	    	 				win.hide();
	    	 			}
	    	 		}
	    	 	]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.SelectXzjdWhAdd.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('addxzjdxxwh_window', Gnt.ux.SelectXzjdWhAdd);
