Gnt.ux.SelectJwhxxWhModify= Ext.extend(Ext.Window, {
	title:'居委会修改',
	closeAction:'hide',
	modal:true,
	width:500,
	height:580,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(selRes){
		this.data = selRes.data;
		Gnt.ux.SelectJwhxxWhModify.superclass.show.call(this);
	},
	setSelRes:function(selRes){
		this.selRes = selRes;
		if(selRes){
			this.fs.getForm().setValues({
				xzjddm:selRes.data.xzjddm,
				dwdm: selRes.data.dwdm,
				dm:selRes.data.dm,
				mc: selRes.data.mc,
				zwpy: selRes.data.zwpy,
				wbpy: selRes.data.wbpy,
				cxfldm: selRes.data.cxfldm,
				tjdm: selRes.data.tjdm,
				tjmc:selRes.data.tjmc,
				xdm:selRes.data.xdm,
				dzys:selRes.data.dzys,
				bz:selRes.data.bz
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
							xtype:'search_combox',
							anchor:'100%',
							searchUrl:'dict/utils/searchXxb?visiontype=XZJDXXB&optype=eq',
							fields:["code","name"],
							valueField: "code",
							displayField: "name",
//							id:'xzqhbdm',
							allowBlank:false,
							fieldLabel:'所属街道',
							id:'xzjddmM',
							hiddenName:'xzjddm'
//							hiddenName:'xzqhbdm',
//							name:'xzqhbmc'
						}]
					},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'dwdm',
								allowBlank:false,
								disabled:true,
								fieldLabel:'所属单位'
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
								fieldLabel:'居委会代码'
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
								fieldLabel:'居委会名称',
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
								fieldLabel:'居委会中文拼音',
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
								fieldLabel:'居委会五笔拼音'
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
		        	       		xtype : "combo",
			            		store : [['111','主城区'],['112','城乡结合区'],['121','镇中心区'],['122','镇乡结合区'],['123','特殊区域'],['210','乡中心区'],['220','村庄']],
		            			border:false,
		            			frame:false,
		            			id:'cxfldmM',
		            			name:'cxfldm',
		            			fieldLabel:"城乡代码",
		            			triggerAction:"all",
		            			maxHeight : 80,
		            			anchor : '100%'
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'tjdm',
								fieldLabel:'统计代码'
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'tjmc',
								fieldLabel:'统计名称'
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'xdm',
								fieldLabel:'居委会新代码'
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
		        	       		xtype : "combo",
			            		store : [['31','社区'],['32','居委会'],['33','村'],['34','组(屯)']],
		            			border:false,
		            			frame:false,
		            			id:'dzysIdM',
		            			name:'dzys',
		            			fieldLabel:"居委会类型",
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
	    	 				var rootWin = this.findParentByType("modifyJwhxxwh_window");
	    	 				var form = rootWin.items.get(0);
	    	 				if(!form.getForm().isValid()){
	    						showErr("居委会任区信息必须填写！");
	    						return;
	    					}
	    	 				var lhdz = form.getForm().getValues();
//							if(rootWin.moreData){
//	    	 					for(o in rootWin.moreData)
//	    	 						lhdz[o] = rootWin.moreData[o];
//	    	 				}
	    	 				lhdz.xzjddm = Ext.getCmp('xzjddmM').getValue();
	    	 				lhdz.dwdm = rootWin.data.dwdm;
	    	 				lhdz.dm = rootWin.data.dm;
	    	 				lhdz.xdm = rootWin.data.xdm;
	    	 				lhdz.cxflmc = lhdz.cxfldm;
	    	 				lhdz.cxfldm = Ext.getCmp('cxfldmM').getValue();
	    	 				lhdz.dzysmc = lhdz.dzys;
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
	    	 				var win = this.findParentByType("modifyJwhxxwh_window");
	    	 				win.hide();
	    	 			}
	    	 		}
	    	 	]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.SelectJwhxxWhModify.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('modifyJwhxxwh_window', Gnt.ux.SelectJwhxxWhModify);
