Gnt.ux.SelectYwblxzAdd= Ext.extend(Ext.Window, {
	title:'业务办理限制信息增加',
	closeAction:'hide',
	modal:true,
	width:500,
	height:300,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(){
		Gnt.ux.SelectYwblxzAdd.superclass.show.call(this);
	},
	setSelRes:function(selectedSelRes,store){
		var selectedXzlx =selectedSelRes.data.dm;
		if(selectedXzlx=="F1005"||selectedXzlx=="F1006"||selectedXzlx=="F1007"||selectedXzlx=="F1008"||selectedXzlx=="F1106"){
			Ext.getCmp('spmbidAdd').hide();
		}else if(selectedXzlx=="F1009"||selectedXzlx=="F1010"||selectedXzlx=="F1012"||selectedXzlx=="F1013"||selectedXzlx=="F5006"){
			Ext.getCmp('spmbidAdd').show();
		}else{
			Ext.getCmp('spmbidAdd').show();
		}
		var list = new Array();
		var i = 0
		store.each(function(r){
			var data = r.data;
			list[i] = new Array();
			list[i][0]=data.spmbid;
			list[i][1]=data.mbmc+"-"+data.spmbid;
			list[i][2]=data;
			i++;
		});
		var f=this.fs.getForm().findField("spmbid");
		f.store.loadData(list);
		this.fs.getForm().setValues({
			xzmc:'',
			xzywlx: selectedSelRes.data.dm,
			xzbds:'',
			xzzt:'',
			spmbid:''
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
		            			xtype : 'textfield',
		            			border:false,
		            			frame:false,
		            			fieldLabel:"限制名称",
		            			allowBlank:false,
		            			anchor : '100%',
		            			name:'xzmc'
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'search_combox',
								anchor:'100%',
								searchUrl:'dict/utils/searchXzlx',
								fields:["code","name"],
								valueField: "code",
								displayField: "name",
								allowBlank:false,
								fieldLabel:'限制类型',
								hiddenName:'xzywlx'
							}]
						},{
							columnWidth:1,
				           	layout: 'form',
				           	bodyStyle:'padding:5 0 0 0',
			            	items:[{
		            			xtype : 'textarea',
		            			border:false,
		            			frame:false,
		            			fieldLabel:"限制表达式",
		            			allowBlank:false,
		            			anchor : '100%',
		            			name:'xzbds'
							}
						]
					},{
						columnWidth:1,
			           	layout: 'form',
			           	bodyStyle:'padding:5 0 0 0',
		            	items:[{
		            		xtype : "combo",
		            		store : [['0','0-不允许办理'],['1','1-仍允许办理']],
	            			border:false,
	            			frame:false,
	            			name:'xzzt',
	            			id:'xzztAdd',
	            			allowBlank:false,
	            			fieldLabel:"限制状态",
	            			width:60,
	            			//editable:false,
	            			triggerAction:"all",
	            			maxHeight : 80,
	            			anchor : '100%'}
					]
				},{
					columnWidth:1,
		           	layout: 'form',
		           	id:'spmbidAdd',
		           	bodyStyle:'padding:5 0 0 0',
	            	items:[{
        	       		xtype:'dict_combox',
						dict:'VisionType=_BLANK',
						anchor:'100%',
						name:'spmbmc',
						maxLength:40,
						fieldLabel:'审批模板',
						valueField: "code",
						displayField: "name",
						hiddenName:'spmbid',
						listeners:{
							select:function(combo, res, eOpts ){
								
							}
						}
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
	    	 				var rootWin = this.findParentByType("addywblxz_window");
	    	 				var form = rootWin.items.get(0);
	    	 				if(!form.getForm().isValid()){
	    						showErr("业务办理限制信息必须填写！");
	    						return;
	    					}
	    	 				var lhdz = form.getForm().getValues();
	    	 				lhdz.xzzt=Ext.getCmp('xzztAdd').getValue();
	    	 				//alert(lhdz.xzmc);
	    	 				//alert(lhdz.xzywlx);
	    	 				//alert(lhdz.xzbds);
	    	 				//alert(lhdz.xzzt);
	    	 				//alert(Ext.getCmp('xzztAdd').getValue());
	    	 				if(rootWin.callback){
								rootWin.callback('lh', lhdz);
							}
							rootWin.hide();
	    	 			}
	    	 		},{
	    	 			text:'取消',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var win = this.findParentByType("addywblxz_window");
	    	 				win.hide();
	    	 			}
	    	 		}
	    	 	]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.SelectYwblxzAdd.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('addywblxz_window', Gnt.ux.SelectYwblxzAdd);
