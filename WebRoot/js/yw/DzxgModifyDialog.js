var myField = null;
var myForm = null;
/**
 * 标准地址选择,必须先加载commFrames.js
 */
Gnt.ux.DzxgModifyDialog = Ext.extend(Ext.Window, {
	title:'地址信息输入',
	closeAction:'hide',
	closable:false,
	modal:true,
	width:500,
	height:420,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(record){
		this.record = record;
		Gnt.ux.DzxgModifyDialog.superclass.show.call(this);
		if(record){
			var that = this;
			Gnt.submit(
					{jcwh:record.data.jcwh}, 
					"yw/dzgl/dzwh/searchJlxByJwh.json", //searchJwhPcsXzjd
					"正在查询街路巷信息，请稍后...", 
					function(data,params){
						var f = that.fs.getForm().findField("jlx");
						f.setValue("");
						f.store.removeAll();
						if(data.list && data.list.length>0){
							var list = new Array();
							for(var i=0;i<data.list.length;i++){
								list[i] = new Array();
								list[i][0]=data.list[i].dm;
								list[i][1]=data.list[i].mc;
								list[i][2]=data.list[i];
							}
							
							f.store.loadData(list);
							f.setValue(record.data.jlx); 
						}
					}
			);
			this.fs.getForm().setValues({
				mlph: record.data.mlph,
				mlxz: record.data.mlxz,
				ssxqmc: Gnt.ux.Dict.getDictLable('XZQHB',record.data.ssxq),
				xzjdmc:Gnt.ux.Dict.getDictLable('XZJDXXB',record.data.xzjd),
				pcs: record.data.pcs,
				jcwh:record.data.jcwh,
				//jlx:record.data.jlx,
				zrq:record.data.zrq
			});
		}
		
	},
	resetData:function(){
		this.fs.getForm().reset();
	},
	initComponent : function(){
		var flag = this.flag ; 
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
										searchUrl:'dict/utils/searchJwhPcsXzjd',
										fields:["mc","dm","dwdm","dwdm_name","dwdm_bz","xzjddm","xzjddm_name","jlxList"],
										valueField: "dm",
										displayField: "mc",
										anchor:'100%',
										name:'jcwh',
										disabled:true,
										maxLength:40,
										fieldLabel:'居（村）委会',
										allowBlank:false,
										hiddenName:'jcwh',
										listeners:{
											select:function(combo, res, eOpts ){
												var data = res.data;
												var f = combo.findParentByType("form").getForm().findField("jlx");
												f.setValue("");
												f.store.removeAll();
												var f1 = combo.findParentByType("form").getForm().findField("ssxqmc");
												f1.setValue(data.dwdm_bz);
												var f2 = combo.findParentByType("form").getForm().findField("xzjdmc");
												f2.setValue(data.xzjddm_name);
												var f3 = combo.findParentByType("form").getForm().findField("pcs");
												f3.setValue(data.dwdm);
												//alert(res.data)
												if(data.jlxList && data.jlxList.length>0){
													var list = new Array();
													for(var i=0;i<data.jlxList.length;i++){
														list[i] = new Array();
														list[i][0]=data.jlxList[i].dm;
														list[i][1]=data.jlxList[i].mc;
														list[i][2]=data.jlxList[i];
													}
													
													f.store.loadData(list);
													f.setValue(list[0][0]); 
													//combo.findParentByType("form").setMore(list[0][2], data);
												}
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
										name:'jlxmc',
										maxLength:40,
										fieldLabel:'街路巷',
										allowBlank:false,
										valueField: "code",
										displayField: "name",
										hiddenName:'jlx'
									}]
								},{
					                columnWidth:1,
				    	           	layout: 'form',
				    	           	bodyStyle:'padding:5 0 0 0',
				        	       	items: [{
										xtype:'textfield',
										anchor:'100%',
										name:'mlph',
										//allowBlank:false,
										fieldLabel:'门楼牌号',
										enableKeyEvents: true,
					                    listeners: {
					                        blur:function(field){
												field.setValue(Gnt.ToDBC(field.getValue(),false));
												/**
													2018.11.02
													新增	刁杰
													客户端只能输入十位,超过自动将焦点移动到门楼详址
												 */
												if(field.getValue().length > 10){
													field.setValue(field.getValue().substr(0,10));
												}
											},
											focus:function(field){
												myField = field;
											},
											keyup: function(src, evt){
					                        	var win = this.findParentByType("dzxgModify_window");
					                        	if(src.getValue()){
					                        		//13:回车(Enter)	32:空格(Space)
					                        		if (event.keyCode == 13 || event.keyCode == 32){
					                        			win.fs.getForm().findField("mlph").setValue(win.fs.getForm().findField("mlph").getValue().substr(0,10));
					                        			win.fs.getForm().findField("mlxz").focus();
					                        		}
					                        	}else{
					                        		
					                        	}
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
										name:'mlxz',
										//allowBlank:false,
										fieldLabel:'门楼详址',
										enableKeyEvents: true,
										listeners:{
					                        blur:function(field){
												field.setValue(Gnt.ToDBC(field.getValue(),false));
												if(field.getValue().length > 100){
													field.setValue(field.getValue().substr(0,100));
												}
											},
											focus:function(field){
												myField = field;
											},
											keyup: function(src, evt){
					                        	var win = this.findParentByType("dzxgModify_window");
					                        	if(src.getValue()){
					                        		if (event.keyCode == 13 || event.keyCode == 32){
					                        			win.fs.getForm().findField("mlxz").setValue(win.fs.getForm().findField("mlxz").getValue().substr(0,100));
					                        			win.fs.getForm().findField("ssxqmc").focus();
					                        		}
					                        	}else{
					                        		
					                        	}
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
										name:'ssxqmc',
										allowBlank:false,
										disabled:true,
										fieldLabel:'省市县（区）',
										disabled:true,
										listeners:{
											select:function(combo, res, eOpts){
//												Ext.getCmp('tzqjwhId').store.searchUrl = "dict/utils/searchXxb?visiontype=aaaaaa&ssxq=340808";
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
										disabled:true,
										fieldLabel:'乡镇（街道）'
									}]
								},{
					                columnWidth:1,
				    	           	layout: 'form',
				    	           	bodyStyle:'padding:5 0 0 0',
				        	       	items: [{
				        	       		xtype:'search_combox',
										searchUrl:'dict/utils/searchPcs.json',
										fields:["mc","dm"],
										valueField: "dm",
										displayField: "mc",
										hiddenName:'pcs',
										anchor:'100%',
										disabled:true,
										fieldLabel:'派出所'
									}]
								},{
					                columnWidth:1,
				    	           	layout: 'form',
				    	           	bodyStyle:'padding:5 0 0 0',
				        	       	items: [{
				        	       		fieldLabel:'责任区',
				        	       		xtype:'search_combox',
				        				anchor:'100%',
				        				name:'zrq',
										disabled:true,
				        				hiddenName:'zrq',
				        				searchUrl : "dict/utils/searchXxb?visiontype=JWZRQXXB&optype=eq"
									}]
								}
				]
			},{
            	title: '说明',
//				columnWidth: 3,
	            xtype: 'fieldset',
	            autoHeight: true,
	            layout:'column',
	            defaults:{
	            	columnWidth: .2,
	            	fieldLabel: ''
	            },
	            items: [{
	            	columnWidth: 1,
	            	html: '<div class="text" style="text-align:left;">地址输入快捷方式:</div>'
	            },{
	            	html: 'F2: 小区'
	            },{
	            	html: 'F3: 组'
	            },{
	            	html: 'F4: 弄'
	            },{
	            	html: 'F5: 号'
	            },{
	            	html: 'F6: 幢'
	            },{
	            	html: 'F7: 单元'
	            },{
	            	html: 'F8: 室'
	            },{
	            	html: 'F9: 宿舍'
	            },{
	            	html: 'F10: 公司'
	            }]
			}],
	    	buttons:[
	    	 		{
	    	 			text:'保存',
	    	 			id:'modifySave',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var rootWin = this.findParentByType("dzxgModify_window");
	    	 				var form = rootWin.items.get(0);
	    	 				var lhdz = form.getForm().getValues();
	    	 				if(!form.getForm().isValid()){
	    						showErr("地址信息必须填写！");
	    						return;
	    					}
	    	 				lhdz.zrq = rootWin.record.data.zrq;
	    	 				lhdz.ssxq = rootWin.record.data.ssxq;
	    	 				lhdz.xzjd = rootWin.record.data.xzjd;
	    	 				lhdz.pcs = rootWin.record.data.pcs;
	    	 				lhdz.jcwh = rootWin.record.data.jcwh;
	    	 				lhdz.mlpnbid = rootWin.record.data.mlpnbid;
							if(rootWin.callback){
								rootWin.callback('lh', lhdz);
							}
							
							rootWin.hide();
	    	 			}
	    	 		},{
	    	 			text:'关闭',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var rootWin = this.findParentByType("dzxgModify_window");
	    	 				var oldDate = rootWin.record.data;
	    	 				var form = rootWin.items.get(0);
	    	 				var lhdz = form.getForm().getValues();
	    	 				if(lhdz.jlx!=oldDate.jlx||lhdz.mlph!=oldDate.mlph||lhdz.mlxz!=oldDate.mlxz){
	    	 					if(window.confirm("地址信息已经被修改,尚未保存,是否确定返回?")){
	    	 			    		rootWin.hide();
	    	 			    	}else{
	    	 			    		return;
	    	 			    	}
	    	 				}else{
	    	 					rootWin.hide();
	    	 				}
	    	 			}
	    	 		}
	    	 	]
		});
		
		myForm = fs;
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.DzxgModifyDialog.superclass.initComponent.call(this);
	},tools:[{ 
	    type:'close', 
	    qtip: '关闭窗口', 
	    handler: function(event, toolEl, panel){ 
	        // 关闭窗口的操作 
	    	var rootWin = panel.fs.findParentByType("dzxgModify_window");
	    	var oldDate = rootWin.record.data;
			var form = rootWin.items.get(0);
			var lhdz = form.getForm().getValues();
			if(lhdz.jlx!=oldDate.jlx||lhdz.mlph!=oldDate.mlph||lhdz.mlxz!=oldDate.mlxz){
				if(window.confirm("地址信息已经被修改,尚未保存,是否确定返回?")){
		    		rootWin.hide();
		    	}else{
		    		return;
		    	}
			}else{
				rootWin.hide();
			}
	    	
	    } 
	} 
	]
	
	
});
Ext.reg('dzxgModify_window', Gnt.ux.DzxgModifyDialog);	

//禁止用F2~F10键 
document.onkeydown=function(){
//	alert(event.keyCode);
	if (event.keyCode==113){//F2
		event.keyCode = 0;
		myField.setValue(myField.getValue() + "小区");
		if(myField && myField.name == "mlph" && myField.getValue().length > 10){
			myField.setValue(myField.getValue().substr(0,10));
			myForm.getForm().findField("mlxz").focus();
		}
		if(myField && myField.name == "mlxz" && myField.getValue().length > 100){
			myField.setValue(myField.getValue().substr(0,10));
		}
		return false;
	}
	if (event.keyCode==114){//F3
		event.keyCode = 0;
		myField.setValue(myField.getValue() + "组");
		if(myField && myField.name == "mlph" && myField.getValue().length > 10){
			myField.setValue(myField.getValue().substr(0,10));
			myForm.getForm().findField("mlxz").focus();
		}
		if(myField && myField.name == "mlxz" && myField.getValue().length > 100){
			myField.setValue(myField.getValue().substr(0,10));
		}
		return false;
	}
	if (event.keyCode==115){//F4
		event.keyCode = 0;
		myField.setValue(myField.getValue() + "弄");
		if(myField && myField.name == "mlph" && myField.getValue().length > 10){
			myField.setValue(myField.getValue().substr(0,10));
			myForm.getForm().findField("mlxz").focus();
		}
		if(myField && myField.name == "mlxz" && myField.getValue().length > 100){
			myField.setValue(myField.getValue().substr(0,10));
		}
		return false;
	}
	if (event.keyCode==116){//F5
		event.keyCode = 0;
//		event.cancelBubble = true;
		myField.setValue(myField.getValue() + "号");
		if(myField && myField.name == "mlph" && myField.getValue().length > 10){
			myField.setValue(myField.getValue().substr(0,10));
			myForm.getForm().findField("mlxz").focus();
		}
		if(myField && myField.name == "mlxz" && myField.getValue().length > 100){
			myField.setValue(myField.getValue().substr(0,10));
		}
		return false;
	}
	if (event.keyCode==117){//F6
		event.keyCode = 0;
//		event.cancelBubble = true;
		myField.setValue(myField.getValue() + "幢");
		if(myField && myField.name == "mlph" && myField.getValue().length > 10){
			myField.setValue(myField.getValue().substr(0,10));
			myForm.getForm().findField("mlxz").focus();
		}
		if(myField && myField.name == "mlxz" && myField.getValue().length > 100){
			myField.setValue(myField.getValue().substr(0,10));
		}
		return false;
	}
	if (event.keyCode==118){//F7
		event.keyCode = 0;
//		event.cancelBubble = true;
		myField.setValue(myField.getValue() + "单元");
		if(myField && myField.name == "mlph" && myField.getValue().length > 10){
			myField.setValue(myField.getValue().substr(0,10));
			myForm.getForm().findField("mlxz").focus();
		}
		if(myField && myField.name == "mlxz" && myField.getValue().length > 100){
			myField.setValue(myField.getValue().substr(0,10));
		}
		return false;
	}
	if (event.keyCode==119){//F8
		event.keyCode = 0;
//		event.cancelBubble = true;
		myField.setValue(myField.getValue() + "室");
		if(myField && myField.name == "mlph" && myField.getValue().length > 10){
			myField.setValue(myField.getValue().substr(0,10));
			myForm.getForm().findField("mlxz").focus();
		}
		if(myField && myField.name == "mlxz" && myField.getValue().length > 100){
			myField.setValue(myField.getValue().substr(0,10));
		}
		return false;
	}
	if (event.keyCode==120){//F9
		event.keyCode = 0;
//		event.cancelBubble = true;
		myField.setValue(myField.getValue() + "宿舍");
		if(myField && myField.name == "mlph" && myField.getValue().length > 10){
			myField.setValue(myField.getValue().substr(0,10));
			myForm.getForm().findField("mlxz").focus();
		}
		if(myField && myField.name == "mlxz" && myField.getValue().length > 100){
			myField.setValue(myField.getValue().substr(0,10));
		}
		return false;
	}
	if (event.keyCode==121){//F10
		event.keyCode = 0;
//		event.cancelBubble = true;
		myField.setValue(myField.getValue() + "公司");
		if(myField && myField.name == "mlph" && myField.getValue().length > 10){
			myField.setValue(myField.getValue().substr(0,10));
			myForm.getForm().findField("mlxz").focus();
		}
		if(myField && myField.name == "mlxz" && myField.getValue().length > 100){
			myField.setValue(myField.getValue().substr(0,10));
		}
		return false;
	}
} 