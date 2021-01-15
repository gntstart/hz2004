Gnt.ux.SelectYhWhModify= Ext.extend(Ext.Window, {
	title:'用户信息修改',
	closeAction:'hide',
	modal:true,
	width:500,
	height:500,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(selRes){
		this.data = selRes.data;
		Gnt.ux.SelectYhWhModify.superclass.show.call(this);
	},
	setSelRes:function(selRes){
		this.selRes = selRes;
		//alert(selRes.data.dwdm);
		if(selRes){
			var klTemp ='';
			var kllength = selRes.data.dlkl.length;
			for(var i = 0;i<kllength;i++){
				klTemp="*"+klTemp;
    		}
			this.fs.getForm().setValues({
				yhdlm: selRes.data.yhdlm,
				jyh: selRes.data.jyh,
				dwdm:selRes.data.dwdm,
				yhxm: selRes.data.yhxm,
				yhxb: selRes.data.yhxb,
				gmsfhm:selRes.data.gmsfhm,
				yhzw: selRes.data.yhzw,
				dlkl: klTemp,
				qrkl:klTemp,
				yhmj:selRes.data.yhmj
			});
			if(selRes.data.dlkl.substring(0,1)){
				Ext.getCmp('modifyczmj1').setValue(true);
			}
			if(selRes.data.dlkl.substring(1,2)){
				Ext.getCmp('modifyczmj2').setValue(true);
			}
			if(selRes.data.dlkl.substring(2,3)){
				
			}
			Ext.getCmp('modifyczmj3').setValue();
			Ext.getCmp('modifyczmj4').setValue(selRes.data.dlkl.substring(3,4));
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
								name:'yhdlm',
								allowBlank:false,
								fieldLabel:'用户登录名'
							}]
					},{
		                columnWidth:1,
	    	           	layout: 'form',
	    	           	bodyStyle:'padding:5 0 0 0',
	        	       	items: [{
							xtype:'textfield',
							anchor:'100%',
							name:'jyh',
							allowBlank:false,
							fieldLabel:'警员号'
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
//							id:'xzqhbdm',
							allowBlank:false,
							fieldLabel:'单位代码',
							hiddenName:'dwdm'
						}]
					},{
		                columnWidth:1,
	    	           	layout: 'form',
	    	           	bodyStyle:'padding:5 0 0 0',
	        	       	items: [{
							xtype:'textfield',
							anchor:'100%',
							name:'yhxm',
							fieldLabel:'用户姓名'
						}]
					},{
		                columnWidth:1,
	    	           	layout: 'form',
	    	           	bodyStyle:'padding:5 0 0 0',
	        	       	items: [{
	        	       		anchor:'100%',
							xtype:'dict_combox',
							dict:'VisionType=CS_XB',
							name:'xbmc',
							maxLength:40,
							hiddenName:'yhxb',
							allowBlank:false,
							fieldLabel:'用户性别'
						}]
					},{
		                columnWidth:1,
	    	           	layout: 'form',
	    	           	bodyStyle:'padding:5 0 0 0',
	        	       	items: [{
							xtype:'textfield',
							anchor:'100%',
							name:'gmsfhm',
							fieldLabel:'身份号码'
						}]
					},{
		                columnWidth:1,
	    	           	layout: 'form',
	    	           	bodyStyle:'padding:5 0 0 0',
	        	       	items: [{
	        	       		anchor:'100%',
							xtype:'dict_combox',
							dict:'VisionType=CS_YHZW',
							name:'yhzwmc',
							maxLength:40,
							hiddenName:'yhzw',
							allowBlank:false,
							fieldLabel:'用户职务'
						}]
					},{
		                columnWidth:0.5,
	    	           	layout: 'form',
	    	           	bodyStyle:'padding:5 0 0 0',
	        	       	items: [{
	        	       		xtype:'textfield',
							anchor:'100%',
							name:'dlkl',
							allowBlank:false,
							fieldLabel:'登录口令',
							autoCreate: {tag: 'input', type: 'text', size: '20', autocomplete: 'off', maxlength: '8'},
                            enableKeyEvents:true,
                            listeners:{
                                 keyup:function(src, evt){
			                        	var win = this.findParentByType("modifyyhwh_window");
			                        	var lengthT = src.getValue().length;
		                        		var form = win.items.get(0);
		                        		var lhdz = form.getForm().getValues();
		                        		var temp =lhdz.dlkl;
			                        	if(src.getValue()&&evt.keyCode!=8){
			                        		dlkl= dlkl+src.getValue().substring(lengthT-1,lengthT);
			                        		for(var i = 0;i<lengthT;i++){
			                        			temp="*"+temp;
			                        		}
			                        		win.items.items[0].getForm().findField("dlkl").setValue(temp);
			                        	}else if(src.getValue()&&evt.keyCode==8){
			                        		if(lengthT>0){
			                        			temp.substring(0,lengthT-1);
			                        			dlkl= dlkl.substring(0,lengthT);
			                        		}
			                        	}else{
			                        		dlkl='';
			                        	}
                                  }
                            }
						}]
					},{
		                columnWidth:0.5,
	    	           	layout: 'form',
	    	           	bodyStyle:'padding:5 0 0 0',
	        	       	items: [{
							xtype:'textfield',
							anchor:'100%',
							name:'qrkl',
							allowBlank:false,
							autoCreate: {tag: 'input', type: 'text', size: '20', autocomplete: 'off', maxlength: '8'},
							fieldLabel:'口令确认',
                            enableKeyEvents:true,
                            listeners:{
                                 keyup:function(src, evt){
			                        	var win = this.findParentByType("modifyyhwh_window");
			                        	var lengthT = src.getValue().length;
		                        		var temp ="";
		                        		
			                        	if(src.getValue()&&evt.keyCode!=8){
			                        		dlkl= dlkl+src.getValue().substring(lengthT-1,lengthT);
			                        		for(var i = 0;i<lengthT;i++){
			                        			temp="*"+temp;
			                        		}
			                        		win.items.items[0].getForm().findField("qrkl").setValue(temp);
			                        	}else if(src.getValue()&&evt.keyCode==8){
			                        		if(lengthT>0){
			                        			temp.substring(0,lengthT-1);
			                        			dlkl= dlkl.substring(0,lengthT);
			                        		}
			                        	}else{
			                        		dlkl='';
			                        	}
                                  }
                            }
						}]
					},{
		                columnWidth:1,
	    	           	layout: 'form',
	    	           	bodyStyle:'padding:5 0 0 0',
	        	       	items: [{
	        	       		anchor:'100%',
							xtype:'dict_combox',
							dict:'VisionType=CS_YHMJ',
							name:'yhmjmc',
							maxLength:40,
							hiddenName:'yhmj',
							allowBlank:false,
							fieldLabel:'用户密级'
						}]
					},{
		                columnWidth:1,
	    	           	layout: 'form',
	    	           	bodyStyle:'padding:0 0 0 0',
	        	       	items: [{
	        	       		xtype: 'fieldset',
	    					autoHeight: true,
	    					title: '操作密级',
							anchor:'100%',
							layout:'column',
							defaults:{
								xtype:'checkbox',
				            	columnWidth: .5,
				            	fieldLabel: ''
				            },
				            items: [{
				            	id:'modifyczmj1',
				            	boxLabel: '系统管理员',
				            	name:'fw',
				            	inputValue : "1"
				            },{
				            	id:'modifyczmj2',
				            	boxLabel: '上报数据专管员',
				            	name:'fw',
				            	inputValue : "2"
				            },{
				            	id:'modifyczmj3',
				            	boxLabel: '制证专管员',
				            	name:'fw',
				            	inputValue : "3"
				            },{
				            	id:'modifyczmj4',
				            	boxLabel: '一般用户',
				            	name:'fw',
				            	inputValue : "4"
				            }]
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
	    	 				var rootWin = this.findParentByType("modifyyhwh_window");
	    	 				var form = rootWin.items.get(0);
	    	 				if(!form.getForm().isValid()){
	    						showErr("数据字典必须填写！");
	    						return;
	    					}
	    	 				var lhdz = form.getForm().getValues();
//							if(rootWin.moreData){
//	    	 					for(o in rootWin.moreData)
//	    	 						lhdz[o] = rootWin.moreData[o];
//	    	 				}
	    	 				lhdz.xzqhbdm = rootWin.data.xzqhbdm;
	    	 				lhdz.dm = rootWin.data.dm;
	    	 				lhdz.dwjgdm = rootWin.data.dwjgdm;
	    	 				lhdz.dwjb = Ext.getCmp('newmc').getValue();
	    	 				if(rootWin.callback){
								rootWin.callback('lh', lhdz);
							}
							rootWin.hide();
	    	 			}
	    	 		},{
	    	 			text:'取消',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var win = this.findParentByType("modifyyhwh_window");
	    	 				win.hide();
	    	 			}
	    	 		}
	    	 	]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.SelectYhWhModify.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('modifyyhwh_window', Gnt.ux.SelectYhWhModify);
