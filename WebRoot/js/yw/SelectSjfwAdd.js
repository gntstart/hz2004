Gnt.ux.SelectSjfwAdd= Ext.extend(Ext.Window, {
	title:'数据限制增加',
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
			Ext.getCmp('yhidDisable').hide();
			this.yhid = sels.data.yhid;
		}else if(type=='1'){
			Ext.getCmp('yhidDisable').show();
		}
		
		Gnt.ux.SelectSjfwAdd.superclass.show.call(this);
	},
	setSelRes:function(){
		this.fs.getForm().setValues({
			yhid:'',
			xqlx: 1,
			ssxq:'',
			pcs:'',
			jwh:''
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
		    	           	id:'yhidDisable',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'search_combox',
								anchor:'100%',
								searchUrl:'dict/utils/searchYhxx?optype=eq',
								fields:["code","name","pyt"],
								valueField: "pyt",
								displayField: "name",
								//allowBlank:false,
								fieldLabel:'用户ID',
								hiddenName:'yhid'
							}]
						},{
							columnWidth:1,
				           	layout: 'form',
				           	bodyStyle:'padding:5 0 0 0',
			            	items:[{
			            		xtype : "combo",
			            		store : [['1','1-辖区内'],['2','2-辖区外']],
		            			border:false,
		            			frame:false,
		            			name:'xqlx',
		            			id:'xqlxAdd',
		            			fieldLabel:"辖区类型",
		            			width:60,
		            			triggerAction:"all",
		            			maxHeight : 80,
		            			anchor : '100%'}
							]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								id:'ssxqadd',
				            	layout:'border',
			    	       		anchor:'100%',
								xtype:'tree_comboBox',
								searchUrl:'dict/utils/searchXzqhNew',
								fields:["mc","dm","dwList"],
								valueField: "dm",
								displayField: "mc",
								treeUrl:'dict/utils/searchTreeXzqh',
								name:'ssxq',
//								maxLength:30,
								allowBlank:true,
								fieldLabel:'省市县区',
								listeners:{
									select:function(combo, res, eOpts ){
										var win = this.findParentByType("addsjfw_window");
										var data = res.data;
										var f = combo.findParentByType("form").getForm().findField("pcs");
										f.setValue("");
										f.store.removeAll();
										if(data.dwList && data.dwList.length>0){
										var list = new Array();
											for(var i=0;i<data.dwList.length;i++){
												list[i] = new Array();
												list[i][0]=data.dwList[i].dm;
												list[i][1]=data.dwList[i].mc;
												list[i][2]=data.dwList[i];
											}
											f.store.loadData(list);
										}
									}
								}
		        	       		/*
								xtype:'search_combox',
								anchor:'100%',
								searchUrl:'dict/utils/searchXzqhNew',//searchXzqhNew.json
								fields:["mc","dm","dwList"],
								valueField: "mc",
								displayField: "dm",
								allowBlank:false,
								fieldLabel:'省市县区',
								hiddenName:'ssxq',
								listeners:{
									select:function(combo, res, eOpts ){
										var win = this.findParentByType("addsjfw_window");
										var data = res.data;
										var f = combo.findParentByType("form").getForm().findField("pcs");
										f.setValue("");
										f.store.removeAll();
										if(data.dwList && data.dwList.length>0){
										var list = new Array();
											for(var i=0;i<data.dwList.length;i++){
												list[i] = new Array();
												list[i][0]=data.dwList[i].dm;
												list[i][1]=data.dwList[i].mc;
												list[i][2]=data.dwList[i];
											}
											f.store.loadData(list);
										}
									}
								}
								*/
		        	       	}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
		        	       		id:'zrqId',
		        	       		xtype:'dict_combox',
								dict:'VisionType=_BLANK',//VisionType=_BLANK  //dict/utils/searchPcs
								anchor:'100%',
								name:'pcs',
								hiddenName:'pcs',
								fieldLabel:'派出所',
								listeners:{
									select:function(combo, res, eOpts ){
										var stroe = new Ext.data.JsonStore({
									        url: 'dict/utils/searchXxb?visiontype=JWHXXB',
									        combox:this,
									        root: 'list',
									        id: this.valueField,
									        totalProperty:'totalCount',
									        fields: this.fields?this.fields:[this.valueField,this.displayField]
										});
										
										var f = combo.findParentByType("form").getForm().findField("jwh");
										f.setValue("");
										f.store.removeAll();
										stroe.load({params:{'search_code':res.data.code}});
										
										stroe.on("load",function(store) {  
											if(store.data.length > 0){
												var list = new Array();
												for(var i=0;i<store.data.length;i++){
													var map =store.data.items;
													list[i] = new Array();
													list[i][0]=map[i].data.code;
													list[i][1]=map[i].data.name;
													list[i][2]=map[i];
												}
												f.store.loadData(list);
											}
										});
									}
								}
							}]
						},{
							columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
		        	       		id:'jwhId',
		        	       		xtype:'dict_combox',
								dict:'VisionType=_BLANK',//VisionType=_BLANK  //dict/utils/searchPcs
								//fields:["mc","dm"],
								anchor:'100%',
								name:'jwh',
								hiddenName:'jwh',
								fieldLabel:'居委会'
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
	    	 				var rootWin = this.findParentByType("addsjfw_window");
	    	 				var form = rootWin.items.get(0);
	    	 				if(!form.getForm().isValid()){
	    						showErr("业务办理限制信息必须填写！");
	    						return;
	    					}
	    	 				var lhdz = form.getForm().getValues();
	    	 				lhdz.xqlx = Ext.getCmp('xqlxAdd').getValue();
	    	 				lhdz.sjfw = Ext.getCmp('ssxqadd').getValue()+'|'+lhdz.pcs+'|'+lhdz.jwh+'|';
	    	 				if(rootWin.type=='2'){
	    	 					lhdz.yhid=rootWin.yhid
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
	    	 				var win = this.findParentByType("addsjfw_window");
	    	 				win.hide();
	    	 			}
	    	 		}
	    	 	]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.SelectSjfwAdd.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('addsjfw_window', Gnt.ux.SelectSjfwAdd);
