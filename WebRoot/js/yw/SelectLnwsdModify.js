Gnt.ux.SelectLnwsdModify= Ext.extend(Ext.Window, {
	title:'历年尾数段修改',
	closeAction:'hide',
	modal:true,
	width:500,
	height:400,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(selRes){
		this.data = selRes.data;
		Gnt.ux.SelectLnwsdModify.superclass.show.call(this);
	},
	setSelRes:function(selRes){
		this.fs.getForm().setValues({
			qhdm: selRes.data.qhdm,
			dwdm: selRes.data.dwdm,
			xzjd:selRes.data.xzjd,
			ksd:selRes.data.ksd,
			jsd:selRes.data.jsd,
			qyrq: selRes.data.qyrq,
			bz:selRes.data.bz
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
								fieldLabel:'区划代码',
								hiddenName:'qhdm'
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'search_combox',
								anchor:'100%',
								searchUrl:'dict/utils/searchXxb?visiontype=DWXXB&whflg=1',
								fields:["code","name"],
								valueField: "code",
								displayField: "name",
								disabled:true,
								allowBlank:false,
								fieldLabel:'单位代码',
								hiddenName:'dwdm'
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'search_combox',
								anchor:'100%',
								searchUrl:'dict/utils/searchXxb?visiontype=XZJDXXB&optype=eq',
								fields:["code","name"],
								valueField: "code",
								displayField: "name",
								disabled:true,
								allowBlank:false,
								fieldLabel:'乡镇街道',
								hiddenName:'xzjd'
							}]
						},{
							columnWidth:1,
				           	layout: 'form',
				           	bodyStyle:'padding:5 0 0 0',
			            	items:[{
		            			xtype : 'numberfield',
		            			border:false,
		            			frame:false,
		            			autoCreate: {tag: 'input', type: 'text', size: '20', autocomplete: 'on',maxlength: '3'},
		            			fieldLabel:"开始段值",
		            			allowBlank:false,
		            			anchor : '100%',
		            			name:'ksd'
							}
						]
					},{
						columnWidth:1,
			           	layout: 'form',
			           	bodyStyle:'padding:5 0 0 0',
		            	items:[{
	            			xtype : 'numberfield',
	            			border:false,
	            			frame:false,
	            			autoCreate: {tag: 'input', type: 'text', size: '20', autocomplete: 'on',maxlength: '3'},
	            			fieldLabel:"结束段值",
	            			allowBlank:false,
	            			anchor : '100%',
	            			name:'jsd'
						}
					]
				},{
							columnWidth:1,
				           	layout: 'form',
				           	bodyStyle:'padding:5 0 0 0',
			            	items:[{
		            			xtype : 'datefield',
		            			border:false,
		            			frame:false,
		            			fieldLabel:"启用日期",
		            			value:Ext.util.Format.date(new Date(),"Y-m-d"),	
		            			allowBlank:false,
		            			disabled:true,
		            			anchor : '100%',
		            			format:'Ymd',
		            			name:'qyrq'
							}
						]
					},{
							columnWidth:1,
				           	layout: 'form',
				           	bodyStyle:'padding:5 0 0 0',
			            	items:[{
		            			xtype : 'textarea',
		            			border:false,
		            			frame:false,
		            			fieldLabel:"备注",
		            			allowBlank:false,
		            			anchor : '100%',
		            			name:'bz'
							}
						]
					}
					]
				}]
			}],
	    	buttons:[
	    	 		{
	    	 			text:'确定',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var rootWin = this.findParentByType("modifylnwsd_window");
	    	 				var form = rootWin.items.get(0);
	    	 				if(!form.getForm().isValid()){
	    						showErr("历年尾数段信息必须填写！");
	    						return;
	    					}
	    	 				var lhdz = form.getForm().getValues();
	    	 				lhdz.wsdid = rootWin.data.wsdid;
	    	 				lhdz.dwdm = rootWin.data.dwdm;
	    	 				lhdz.xzjd = rootWin.data.xzjd;
	    	 				lhdz.qyrq = rootWin.data.qyrq;
	    	 				if(rootWin.callback){
								rootWin.callback('lh', lhdz);
							}
							rootWin.hide();
	    	 			}
	    	 		},{
	    	 			text:'取消',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var win = this.findParentByType("modifylnwsd_window");
	    	 				win.hide();
	    	 			}
	    	 		}
	    	 	]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.SelectLnwsdModify.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('modifylnwsd_window', Gnt.ux.SelectLnwsdModify);
