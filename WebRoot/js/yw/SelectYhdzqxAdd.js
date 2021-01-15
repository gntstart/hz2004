Gnt.ux.SelectYhdzqxAdd= Ext.extend(Ext.Window, {
	title:'用户动作权限增加',
	closeAction:'hide',
	modal:true,
	width:500,
	height:200,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(selRes){
		this.data = selRes.data;
		Gnt.ux.SelectYhdzqxAdd.superclass.show.call(this);
	},
	setSelRes:function(selRes){
		this.selRes = selRes;
		if(selRes){
			this.fs.getForm().setValues({
				yhid: selRes.data.yhid,
				yhxm: selRes.data.yhxm,
				dzid:''
			});
		}
		var dzStore = new Ext.data.JsonStore({
			url : 'dict/utils/searchXxb?visiontype=SPDZB',
			combox:this,
		    root: 'list',
		    totalProperty:'totalCount',
		    fields: this.fields?this.fields:[this.valueField,this.displayField]
		});
		var f = this.fs.getForm().findField("dzid");
		f.setValue("");
		f.store.removeAll();
		dzStore.load();
		dzStore.on("load",function(store) {  
			if(dzStore.data.length > 0){
				var list = new Array();
				for(var i=0;i<dzStore.data.length;i++){
					var map =store.data.items;
					list[i] = new Array();
					list[i][0]=map[i].json.code;
					list[i][1]=map[i].json.code+'-'+map[i].json.name;
					list[i][2]=map[i];
				}
				f.store.loadData(list);
			}
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
		    	           	hidden:true,
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
		            			xtype : 'textfield',
		            			border:false,
		            			frame:false,
		            			fieldLabel:"",
		            			allowBlank:false,
		            			anchor : '100%',
		            			name:'yhid'
							}]
						},{
		            		columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
		            			xtype : 'textfield',
		            			border:false,
		            			frame:false,
		            			fieldLabel:"用户",
		            			disabled:true,
		            			allowBlank:false,
		            			anchor : '100%',
		            			name:'yhxm'
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
		        	       		name: 'dzid',
		    	                hiddenName: 'dzid',
		    	                fieldLabel: '用户动作',
		    					allowBlank: false,
		    	                xtype: 'dict_combox',
		    	                dict:'VisionType=_BLANK',
		    	                anchor:'100%',
		    			        displayField:'name'
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
	    	 				var rootWin = this.findParentByType("addyhdzqxWindow");
	    	 				var form = rootWin.items.get(0);
	    	 				if(!form.getForm().isValid()){
	    						showErr("流动作必须填写！");
	    						return;
	    					}
	    	 				var lhdz = form.getForm().getValues();
	    	 				lhdz.yhxm = rootWin.data.yhxm;
	    	 				if(rootWin.callback){
								rootWin.callback('lh', lhdz);
							}
							rootWin.hide();
	    	 			}
	    	 		},{
	    	 			text:'取消',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var win = this.findParentByType("addyhdzqxWindow");
	    	 				win.hide();
	    	 			}
	    	 		}
	    	 	]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.SelectYhdzqxAdd.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('addyhdzqxWindow', Gnt.ux.SelectYhdzqxAdd);
