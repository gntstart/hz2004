var myField = null;
var myForm = null;
/**
 * 标准地址选择,必须先加载commFrames.js
 */
Gnt.ux.DzxgDialog = Ext.extend(Ext.Window, {
	title:'地址信息输入',
	closeAction:'hide',
	modal:true,
	width:500,
	height:420,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(record){
		this.bzdz = null;
		Gnt.ux.DzxgDialog.superclass.show.call(this);
		
//		this.fs.getForm().loadRecord(record);
		
		var f1 = this.fs.getForm().findField("jcwh");
		var f2 = this.fs.getForm().findField("jlx");
    	var f3 = this.fs.getForm().findField("zrq");
    	
    	f1.setValue(record.data.jcwh);
    	f1.setRawValue("劳动社区");
    	f2.setValue(record.data.jlx);
    	f2.setRawValue("观湖五组");
    	
    	this.fs.getForm().findField("mlph").setValue(record.data.mlph);
    	this.fs.getForm().findField("mlxz").setValue(record.data.mlxz);
    	
    	this.fs.getForm().findField("ssxq").setValue(record.data.ssxq);
    	this.fs.getForm().findField("ssxqmc").setValue(record.data.ssxq);
    	
    	this.fs.getForm().findField("xzjd").setValue(record.data.ssxq);
    	this.fs.getForm().findField("xzjdmc").setValue(record.data.ssxq);
    	
    	this.fs.getForm().findField("pcs").setValue(record.data.ssxq);
    	this.fs.getForm().findField("pcsmc").setValue(record.data.ssxq);
    	
    	f3.setValue(record.data.zrq);
    	f3.setRawValue("观湖五组");
		
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
		        	       	},{
		        	       		xtype:'hidden',
		        	       		name:'xzjd'
		        	       	},{
		        	       		xtype:'hidden',
		        	       		name:'pcs'
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
										disabled:true,
										fieldLabel:'居（村）委会',
										allowBlank:false,
										valueField: "code",
										displayField: "name",
										hiddenName:'jcwh',
										listeners:{
											select:function(combo, res, eOpts ){
												
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
					                        	var win = this.findParentByType("dzxg_window");
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
					                        	var win = this.findParentByType("dzxg_window");
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
				        	       		fieldLabel:'省市县（区）',
										anchor:'100%',
										name:'ssxqmc',
										hiddenName:'ssxqCode',
										xtype:'tree_comboBox',
										searchUrl:'dict/utils/searchXzqh',
										treeUrl:'dict/utils/searchTreeXzqh',
										allowBlank:true,
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
										xtype:'textfield',
										anchor:'100%',
										name:'pcsmc',
										disabled:true,
										fieldLabel:'派出所'
									}]
								},{
					                columnWidth:1,
				    	           	layout: 'form',
				    	           	bodyStyle:'padding:5 0 0 0',
				        	       	items: [{
				        	       		disabled:true,
				        	       		xtype:'dict_combox',
										dict:'VisionType=_BLANK',
										anchor:'100%',
										name:'zrqmc',
										hiddenName:'zrq',
										fieldLabel:'责任区'
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
	    	 			disabled:true,
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var rootWin = this.findParentByType("dzxg_window");
	    	 				var form = rootWin.items.get(0);
	    	 				
	    	 				if(!form.getForm().isValid()){
	    						showErr("地址信息必须填写！");
	    						return;
	    					}
	    	 				
	    	 				var lhdz = form.getForm().getValues();
	    	 				if(rootWin.moreData){
	    	 					for(o in rootWin.moreData)
	    	 						lhdz[o] = rootWin.moreData[o];
	    	 				}
	    	 				lhdz.pcs = lhdz.dwdm;
	    	 				/**
	    	 					2018.08.13
	    	 					新增	刁杰
	    	 					新增乡镇街道代码
	    	 				 */
	    	 				lhdz.xzjd = lhdz.xzjddm;
	    	 				
	    	 				if(rootWin.bzdz){
	    	 					//立户，VoLhhdxx对象属性赋值
	    	 					if(rootWin.bzdz.uuid) lhdz.bzdzid = rootWin.bzdz.uuid;
	    	 					if(rootWin.bzdz.bzdz) lhdz.bzdz = rootWin.bzdz.bzdz;
	    	 					if(rootWin.bzdz.x) lhdz.bzdzx = rootWin.bzdz.x;
	    	 					if(rootWin.bzdz.y) lhdz.bzdzy = rootWin.bzdz.y;
	    	 					if(rootWin.bzdz.st) lhdz.bzdzst = rootWin.bzdz.st;
	    	 					
	    	 					if(rootWin.bzdz.mlph && rootWin.bzdz.mlph!="") lhdz.mlph = rootWin.bzdz.mlph;
	    	 					if(rootWin.bzdz.mlxz && rootWin.bzdz.mlxz!="") lhdz.mlxz = rootWin.bzdz.mlxz;
	    	 				}
	    	 				
							if(rootWin.callback){
								rootWin.callback('lh', lhdz);
							}
							
							rootWin.hide();
	    	 			}
	    	 		},{
	    	 			text:'关闭',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var win = this.findParentByType("dzxg_window");
	    	 				win.hide();
	    	 			}
	    	 		}
	    	 	]
		});
		
		myForm = fs;
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.DzxgDialog.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('dzxg_window', Gnt.ux.DzxgDialog);

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
