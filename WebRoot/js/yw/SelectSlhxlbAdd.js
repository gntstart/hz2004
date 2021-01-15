Gnt.ux.SelectSlhxlbAdd= Ext.extend(Ext.Window, {
	title:'受理号序列增加',
	closeAction:'hide',
	modal:true,
	width:300,
	height:200,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(){
		Gnt.ux.SelectSlhxlbAdd.superclass.show.call(this);
	},
	setSelRes:function(){
		this.fs.getForm().setValues({
			dwdm: '',
			slxlid: ''
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
								searchUrl:'dict/utils/searchXxb?visiontype=DWXXB&whflg=1',
								fields:["code","name"],
								valueField: "code",
								displayField: "name",
//								id:'xzqhbdm',
								allowBlank:false,
								fieldLabel:'单位代码',
								hiddenName:'dwdm'
							}]
						},{
							columnWidth:1,
				           	layout: 'form',
				           	bodyStyle:'padding:5 0 0 0',
			            	items:[{
		            			xtype : 'datefield',
		            			border:false,
		            			frame:false,
		            			fieldLabel:"受理日期",
		            			value:Ext.util.Format.date(new Date(),"Y-m-d"),	
		            			allowBlank:false,
		            			anchor : '100%',
		            			format:'Ymd',
		            			name:'slrq'
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
		            			autoCreate: {tag: 'input', type: 'text', size: '20', autocomplete: 'on',maxlength: '5'},
		            			fieldLabel:"当前户号序列号",
		            			allowBlank:false,
		            			anchor : '100%',
		            			name:'slxlid'
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
	    	 				var rootWin = this.findParentByType("addslhxlb_window");
	    	 				var form = rootWin.items.get(0);
	    	 				if(!form.getForm().isValid()){
	    						showErr("受理号序列信息必须填写！");
	    						return;
	    					}
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
	    	 				var win = this.findParentByType("addslhxlb_window");
	    	 				win.hide();
	    	 			}
	    	 		}
	    	 	]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.SelectSlhxlbAdd.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('addslhxlb_window', Gnt.ux.SelectSlhxlbAdd);
