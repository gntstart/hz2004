Gnt.ux.SelectXzjdWhModify= Ext.extend(Ext.Window, {
	title:'乡镇街道修改',
	closeAction:'hide',
	modal:true,
	width:500,
	height:480,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(selRes){
		this.data = selRes.data;
		Gnt.ux.SelectXzjdWhModify.superclass.show.call(this);
	},
	setSelRes:function(selRes){
		this.selRes = selRes;
		if(selRes){
			this.fs.getForm().setValues({
				qhdm:selRes.data.qhdm,
				dm: selRes.data.dm,
				mc: selRes.data.mc,
				zwpy: selRes.data.zwpy,
				wbpy: selRes.data.wbpy,
				bz:selRes.data.bz,
				qybzdm:selRes.data.qybzdm,
				xdm:selRes.data.xdm,
				dzys:selRes.data.dzys,
				dzysmc:selRes.data.dzysmc
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
							name:'qhdm',
							allowBlank:false,
							disabled:true,
							fieldLabel:'所属区划'
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
								disabled:true,
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
			                    		Ext.getCmp('zwpy2').setValue(pinyin.getCamelChars(newValue));
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
								id:'zwpy2',
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
								xtype:'textfield',
								anchor:'100%',
								name:'xdm',
								allowBlank:false,
								disabled:true,	
								fieldLabel:'乡镇街道新代码',
								listeners:{
								}
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
		            			id:'dzysIdM',
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
	    	 				var rootWin = this.findParentByType("modifyxzjdwh_window");
	    	 				var form = rootWin.items.get(0);
	    	 				if(!form.getForm().isValid()){
	    						showErr("警务责任区信息必须填写！");
	    						return;
	    					}
	    	 				var lhdz = form.getForm().getValues();
//							if(rootWin.moreData){
//	    	 					for(o in rootWin.moreData)
//	    	 						lhdz[o] = rootWin.moreData[o];
//	    	 				}
	    	 				lhdz.qhdm = rootWin.data.qhdm;
	    	 				lhdz.dm = rootWin.data.dm;
	    	 				lhdz.xdm = rootWin.data.xdm;
	    	 				lhdz.dzys = Ext.getCmp('dzysIdM').getValue();
	    	 				if(rootWin.callback){
								rootWin.callback('lh', lhdz);
							}
							rootWin.hide();
	    	 			}
	    	 		},{
	    	 			text:'取消',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var win = this.findParentByType("modifyxzjdwh_window");
	    	 				win.hide();
	    	 			}
	    	 		}
	    	 	]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.SelectXzjdWhModify.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('modifyxzjdwh_window', Gnt.ux.SelectXzjdWhModify);
