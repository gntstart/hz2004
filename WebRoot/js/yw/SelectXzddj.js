/**
 * 标准地址选择,必须先加载commFrames.js
 */
Gnt.ux.SelectXzddj = Ext.extend(Ext.Window, {
	title:'现住地信息输入',
	closeAction:'hide',
	modal:true,
	width:500,
	height:280,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(){
		this.bzdz = null;
		Gnt.ux.SelectXzddj.superclass.show.call(this);
	},
	resetData:function(){
		this.fs.getForm().reset();
	},
	initComponent : function(){
		var fs = new Ext.form.FormPanel({
			id:'dzFormId',
	    	height: 100,
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
	        setMore:function(jwh, jlx){
	        	var win = this.findParentByType("xzd_window");
	        	
//	        	var f1 = this.getForm().findField("ssxqmc");
	        	var f2 = this.getForm().findField("xzjdmc");
	        	
//	        	f1.setValue(jwh.dwdm_bz);
	        	f2.setValue(jwh.xzjddm_name);
	        	
	        	win.moreData = jwh;
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
	        	       		xtype:'hidden',
	        	       		name:'ssxq'
	        	       	}]
								},{
					                columnWidth:1,
				    	           	layout: 'form',
				    	           	bodyStyle:'padding:5 0 0 0',
				        	       	items: [{
										xtype:'tree_comboBox',
										anchor:'100%',
										name:'ssxqmc',
										searchUrl : "dict/utils/searchXzqh",
										treeUrl:"dict/utils/searchTreeXzqh",
										allowBlank:false,
										fieldLabel:'省市县（区）',
										listeners:{
											change:function(field, value, oldvalue ){
												var rootWin = this.findParentByType("xzd_window");
						    	 				var form = rootWin.items.get(0);
												var f2 = form.getForm().findField("ssxq");
												f2.setValue(value);
											}
										}
									}]
								},{
					                columnWidth:1,
				    	           	layout: 'form',
				    	           	bodyStyle:'padding:5 0 0 0',
				        	       	items: [{
				        	       		anchor:'100%',
										xtype:'search_combox',
										searchUrl:'dict/utils/searchJlx.json',
										fields:["mc","dm","qhdm","jwhList"],
										valueField: "dm",
										displayField: "mc",
										hiddenName:'jlx',
										listeners:{
											select:function(combo, res, eOpts ){
												var win = this.findParentByType("xzd_window");
												
												//填充居委会数据
												var data = res.data;
												
												var f = combo.findParentByType("form").getForm().findField("jcwh");
												f.setValue("");
												f.store.removeAll();
												
												if(data.jwhList && data.jwhList.length>0){
													var list = new Array();
													for(var i=0;i<data.jwhList.length;i++){
														list[i] = new Array();
														list[i][0]=data.jwhList[i].dm;
														list[i][1]=data.jwhList[i].mc;
														list[i][2]=data.jwhList[i];
													}
													
													f.store.loadData(list);
													f.setValue(list[0][0]);
													combo.findParentByType("form").setMore(list[0][2], data);
													
												}
											}
										},
										name:'jlxmc',
										maxLength:40,
										allowBlank:false,
										fieldLabel:'街路巷'
									}]
								},{
					                columnWidth:1,
				    	           	layout: 'form',
				    	           	bodyStyle:'padding:5 0 0 0',
				        	       	items: [{
										xtype:'textfield',
										anchor:'100%',
										name:'mlxz',
										//allowBlank:false,
										fieldLabel:'详址或服务处所',
										listeners:{
											blur:function(field){
												field.setValue(Gnt.ToDBC(field.getValue(),false));
											},
											focus:function(field){
												
											}
										}
									}]
								},{
					                columnWidth:1,
				    	           	layout: 'form',
				    	           	bodyStyle:'padding:5 0 0 0',
				        	       	items: [{
				        	       		xtype:'dict_combox',
										dict:'VisionType=_BLANK',
										anchor:'100%',
										name:'jcwhmc',
										maxLength:40,
										fieldLabel:'居（村）委会',
										allowBlank:false,
										valueField: "code",
										displayField: "name",
										hiddenName:'jcwh',
										listeners:{
											select:function(combo, res, eOpts ){
												combo.findParentByType("form").setMore(res.data.data);
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
										name:'xzjdmc',
										fieldLabel:'乡镇（街道）'
									}]
								}/*,{
					                columnWidth:1,
				    	           	layout: 'form',
				    	           	bodyStyle:'padding:5 0 0 0',
				        	       	items: [{
										xtype:'search_combox',
										anchor:'100%',
										name:'xzjdmc',
										searchUrl : "dict/utils/searchXxb?visiontype=XZJDXXB&optype=eq",
										fieldLabel:'乡镇（街道）'
									}]
								}*/
				]
			}],
	    	buttons:[
	    	 		{
	    	 			text:'保存',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var rootWin = this.findParentByType("xzd_window");
	    	 				var form = rootWin.items.get(0);
	    	 				
	    	 				if(!form.getForm().isValid()){
	    						showErr("现住地信息必须填写！");
	    						return;
	    					}
	    	 				
	    	 				var xzdz = form.getForm().getValues();
	    	 				if(rootWin.moreData){
	    	 					for(o in rootWin.moreData)
	    	 						xzdz[o] = rootWin.moreData[o];
	    	 				}
	    	 				/**
	    	 					2018.08.13
	    	 					新增	刁杰
	    	 					新增乡镇街道代码
	    	 				 */
	    	 				xzdz.xzjd = xzdz.xzjddm;
	    	 				
	    	 				if(rootWin.bzdz){
	    	 					//立户，VoLhhdxx对象属性赋值
	    	 					if(rootWin.bzdz.uuid) xzdz.bzdzid = rootWin.bzdz.uuid;
	    	 					if(rootWin.bzdz.bzdz) xzdz.bzdz = rootWin.bzdz.bzdz;
	    	 					if(rootWin.bzdz.x) xzdz.bzdzx = rootWin.bzdz.x;
	    	 					if(rootWin.bzdz.y) xzdz.bzdzy = rootWin.bzdz.y;
	    	 					if(rootWin.bzdz.st) xzdz.bzdzst = rootWin.bzdz.st;
	    	 					
	    	 				}
	    	 				
							if(rootWin.callback){
								rootWin.callback(xzdz);
							}
							
							/**
								清空表单
							 */
							form.getForm().reset();
							
							rootWin.hide();
	    	 			}
	    	 		},{
	    	 			text:'关闭',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var rootWin = this.findParentByType("xzd_window");
	    	 				var form = rootWin.items.get(0);
	    	 				
	    	 				form.getForm().reset();
	    	 				
	    	 				rootWin.hide();
	    	 			}
	    	 		}
	    	 	]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.SelectXzddj.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('xzd_window', Gnt.ux.SelectXzddj);

