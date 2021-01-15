var myField = null;
var jwhData = null;
var ishzDataChange = false;
/**
 * 标准地址选择,必须先加载commFrames.js
 */
Gnt.ux.SelectBzdz = Ext.extend(Ext.Window, {
	title:'地址信息输入',
	closeAction:'hide',
	modal:true,
	width:500,
	height:470,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(){
		this.bzdz = null;
		Gnt.ux.SelectBzdz.superclass.show.call(this);
	},
	setHzyw:function(hzywjo){
		this.hzywjo = hzywjo;
		if(hzywjo){
			this.fs.getForm().setValues({
				hlx:Gnt.util.isEmpty(hzywjo.hlx)?'1':hzywjo.hlx,
				mlxz: Gnt.util.isEmpty(hzywjo.lhdz)?'':Gnt.ToDBC(hzywjo.lhdz),
				jlx:hzywjo.jlx,
//				jcwh:hzywjo.jcwh,
				mlph:Gnt.util.isEmpty(hzywjo.mlph)?'':Gnt.ToDBC(hzywjo.mlph)
			});
			var newcombox = this.fs.items.items[0].items.items[1].items.items[0];
			if(hzywjo.jlx){
				Gnt.submit(
						{search_code:hzywjo.jlx}, 
						"dict/utils/searchJlxfromSumbit", 
						"居委会信息初始化中", 
						function(jsonData,params){
							if(jsonData.list){
								jwhData=jsonData.list[0];
								ishzDataChange = true;
								newcombox.fireEvent('select',newcombox); 
							}
						}
				);	
			}
					
		}
	},
	resetData:function(){
		this.fs.getForm().reset();
	},
	initComponent : function(){
		Gnt.ux.Dict.getKzcs("10006,100050", function(config, user, kzdata, s){
				var flag = kzdata['100050'].kzz;
				var url = kzdata['10006'].bz;
				s.kzz10005 = flag;
				s.kzz10006 = url;
		}, this);
		
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
	    	getDictTextValue:function(field){
	    		//获取字典组件文字内容
	    		var f = this.getForm().findField(field);
	    		if(f)
	    			return f.getRawValue();
	    		else
	    			return "";
	    	},
	        setMore:function(jwh, jlx){
	        	var win = this.findParentByType("bzdz_window");
	        	
	        	var f1 = this.getForm().findField("ssxqmc");
	        	var f2 = this.getForm().findField("xzjdmc");
	        	var f3 = this.getForm().findField("pcsmc");
	        	var f4 = this.getForm().findField("ssxq");
				var f5 = this.getForm().findField("ssxq_mc");

	        	f1.setValue(jwh.dwdm_bz);
	        	f2.setValue(jwh.xzjddm_name);
	        	f3.setValue(jwh.dwdm_name);
	        	f4.setValue(jwh.qhdm2);
				f5.setValue(jwh.qhdm2_mc);
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
	        	       	},{
	        	       		xtype:'hidden',
	        	       		name:'ssxq_mc'
	        	       	},{
	        	       					anchor:'100%',
										xtype:'dict_combox',
										dict:'VisionType=CS_HLX',
										value:"1",
										name:'hlxmc',
										maxLength:40,
										hiddenName:'hlx',
										allowBlank:false,
										fieldLabel:'户类型'
									}]
								},{
					                columnWidth:1,
				    	           	layout: 'form',
				    	           	bodyStyle:'padding:5 0 0 0',
				        	       	items: [{
				        	       		anchor:'100%',
										xtype:'search_combox',
										searchUrl:'dict/utils/searchJlx.json',
										fields:["mc","dm","qhdm","qhdm2","qhdm2_mc","jwhList"],
										valueField: "dm",
										displayField: "mc",
										hiddenName:'jlx',
										listeners:{
											select:function(combo, res, eOpts ){
												var win = this.findParentByType("bzdz_window");
												var data = null;
												if(hzywjo&&hzywjo.jlx&&ishzDataChange){
													data=jwhData
													ishzDataChange =false;
												}else if(hzywjo&&!hzywjo.jlx&&ishzDataChange){
													ishzDataChange =false;
													return;
												}else{
													data = res.data;
												}
												//填充居委会数据
												
												var f = combo.findParentByType("form").getForm().findField("jcwh");
												f.setValue("");
												f.store.removeAll();
												combo.findParentByType("form").getForm().findField("ssxqmc").setValue("");
												combo.findParentByType("form").getForm().findField("xzjdmc").setValue("");
												combo.findParentByType("form").getForm().findField("pcsmc").setValue("");
									        	
												if(data.jwhList && data.jwhList.length>0){
													var list = new Array();
													var jcwhIndex = -1;
													for(var i=0;i<data.jwhList.length;i++){
														list[i] = new Array();
														list[i][0]=data.jwhList[i].dm;
														list[i][1]=data.jwhList[i].mc;
														list[i][2]=data.jwhList[i];
														if(hzywjo&&hzywjo.jlx&&hzywjo.jcwh&&(data.jwhList[i].dm==hzywjo.jcwh)){
															jcwhIndex=i;
														}
													}
													
													f.store.loadData(list);
													if(hzywjo&&hzywjo.jlx&&hzywjo.jcwh){
														if(jcwhIndex>-1){
															f.setValue(list[jcwhIndex][0]);
														}
													}
													if(jcwhIndex>-1){
														f.setValue(list[jcwhIndex][0]);
														combo.findParentByType("form").setMore(list[jcwhIndex][2]);
														(function(){
															if(!hzywjo.fjid){
																win.fs.buttons[1].handler();
															}
														}).defer(200)
													}
													//f.setValue(list[0][0]); //modify by zjm 20190402 周晨晨应现场要求，将原先街路巷默认首个，现在置空
													//combo.findParentByType("form").setMore(list[0][2], data);
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
									html: '<div class="text" style="text-align:center;color:black;">请使用街路巷名称、街路巷代码或者街路巷拼音头搜索街路巷</div>'
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
					                    listeners: {
					                        blur:function(field){
												field.setValue(Gnt.ToDBC(field.getValue(),false));
											},
											focus:function(field){
												myField = field;
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
										listeners:{
					                        blur:function(field){
												field.setValue(Gnt.ToDBC(field.getValue(),false));
											},
											focus:function(field){
												myField = field;
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
										name:'ssxqmc',
										allowBlank:false,
										disabled:true,
										fieldLabel:'省市县（区）'
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
	    	 			text:'匹配标准地址',
	    	 			minWidth:75,
	    	 			handler:function(b){
	    	 				var me = this;
	    	 				
	    	 				var win = this.findParentByType("bzdz_window");
	    	 				
	    	 				var data = win.items;
	    	 				var f = data.items[0];
	    	 				var params = f.getForm().getValues();

	    	 				var ssxq_allname = f.getDictTextValue("ssxqmc");
	    	 				
							var jlx_text = f.getDictTextValue("jlx");
							var jcwh_text = f.getDictTextValue("jcwh");
							
							params.ssxq = ssxq_allname + params.ssxq_mc;
							params.jlx = jlx_text;
							params.jcwh = jcwh_text;
							params.ssxq_mc = "";
							
	    	 				//Gnt.ux.Dict.getKzcs("10006,10005", function(config, user, kzdata){
	    	 					var flag = win.kzz10005;
	    	 					var url = win.kzz10006;
	    	 					if(url==undefined || url==""){
	    	 						showErr("请配置标准地址参数！");
	    	 						return;
	    	 					}
	    	 					
	    	 					if(url.indexOf("?")<0)
	    	 						url += "?";
	    	 					
	    	 					for(o in params){
	    	 						if(params[o] && params[o]!=""){
	    	 							var val = params[o];
	    	 							if(val){
		    	 							if(val.indexOf("user.")==0){
		    	 								val = user[val.substring(5)];
		    	 								url += '&' + o + "=" + encodeURI(val);
		    	 							}else{
		    	 								url += '&' + o + "=" + encodeURI(params[o]);
		    	 							}
	    	 							}
	    	 						}
	    	 					}
	    	 					
	    	 					var w = new Gnt.ux.URLDialog1({
	    	 						title: (me.title?me.title:me.text),
	    	 						width:900,
	    	 						height:500,
	    	 						closable:false,
	    	 						url: url,
	    	 						callback:function(){
	    	 							var str1=Gnt.util.trim(window.clipboardData.getData("text"));
	 			    	 				if(!str1 || str1==""){
	 			    	 					showErr("剪贴板没有可拷贝的内容！");
	 			    	 					return false;
	 			    	 				}
	 			    	 				
	 			    	 				var bzdz = null;
	 			    	 				try{
	 			    	 					bzdz = Ext.decode(str1);
	 			    	 				}catch(e){
	 			    	 					showErr("剪贴板内容非法：" + str1);
	 			    	 					return false;
	 			    	 				}
	 			    	 				
	 			    	 				if(!bzdz){
	 			    	 					showErr("无法解析标准地址！");
	 			    	 					return false;
	 			    	 				}
	 			    	 				
	 			    	 				if(!bzdz.uuid){
	 			    	 					if(bzdz.id) bzdz.uuid = bzdz.id;
	 			    	 					if(bzdz.addr) bzdz.bzdz = bzdz.addr;
	 			    	 				}
	 			    	 				
	 			    	 				if(!bzdz.uuid){
	 			    	 					showErr("无效标准地址：" + str1);
	 			    	 					return false;
	 			    	 				}
	 			    	 				
	 			    	 				win.bzdz = bzdz;
	 			    	 				
	 			    	 				return true;
	    	 						}
	    	 					});
	    	 					w.show();
	    	 				//});
	    	 			}
	    	 		},{
	    	 			text:'保存',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var rootWin = this.findParentByType("bzdz_window");
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
	    	 				if(!lhdz.ssxq || lhdz.ssxq==""){
	    	 					showErr("此居委会对应乡镇街道代码的辖区无数据！");
	    	 					return;
	    	 				}
	    	 				
	    	 				if(rootWin.bzdz){
	    	 					//立户，VoLhhdxx对象属性赋值
	    	 					if(rootWin.bzdz.uuid) lhdz.bzdzid = rootWin.bzdz.uuid;
	    	 					if(rootWin.bzdz.bzdz) lhdz.bzdz = rootWin.bzdz.bzdz;
	    	 					if(rootWin.bzdz.x) lhdz.bzdzx = rootWin.bzdz.x;
	    	 					if(rootWin.bzdz.y) lhdz.bzdzy = rootWin.bzdz.y;
	    	 					if(rootWin.bzdz.st) lhdz.bzdzst = rootWin.bzdz.st;
	    	 					
	    	 					/**
	    	 						
	    	 						标准地址匹配后的门楼牌号(mlph)/门楼详址(mlxz)不进行替换
	    	 					 */
//	    	 					if(rootWin.bzdz.mlph && rootWin.bzdz.mlph!="") lhdz.mlph = rootWin.bzdz.mlph;
//	    	 					if(rootWin.bzdz.mlxz && rootWin.bzdz.mlxz!="") lhdz.mlxz = rootWin.bzdz.mlxz;
	    	 				}else{
	    	 					if(rootWin.kzz10005=="1"){
	    	 						showErr("请匹配标准地址后,再点击保存！");
	    	 						return;
	    	 					}
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
	    	 				var win = this.findParentByType("bzdz_window");
	    	 				win.hide();
	    	 			}
	    	 		}
	    	 	]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.SelectBzdz.superclass.initComponent.call(this);
	}
});

Ext.reg('bzdz_window', Gnt.ux.SelectBzdz);

//禁止用F2~F10键 
document.onkeydown=function(){
//	alert(event.keyCode);
	if (event.keyCode==113){//F2
		event.keyCode = 0;
		if(myField){
			myField.setValue(myField.getValue() + "小区");
		}
		return false;
	}
	if (event.keyCode==114){//F3
		event.keyCode = 0;
		if(myField){
			myField.setValue(myField.getValue() + "组");
		}
		return false;
	}
	if (event.keyCode==115){//F4
		event.keyCode = 0;
		if(myField){
			myField.setValue(myField.getValue() + "弄");
		}
		return false;
	}
	if (event.keyCode==116){//F5
		event.keyCode = 0;
//		event.cancelBubble = true;
		if(myField){
			myField.setValue(myField.getValue() + "号");
		}
		return false;
	}
	if (event.keyCode==117){//F6
		event.keyCode = 0;
//		event.cancelBubble = true;
		if(myField){
			myField.setValue(myField.getValue() + "幢");
		}
		return false;
	}
	if (event.keyCode==118){//F7
		event.keyCode = 0;
//		event.cancelBubble = true;
		if(myField){
			myField.setValue(myField.getValue() + "单元");
		}
		return false;
	}
	if (event.keyCode==119){//F8
		event.keyCode = 0;
//		event.cancelBubble = true;
		if(myField){
			myField.setValue(myField.getValue() + "室");
		}
		return false;
	}
	if (event.keyCode==120){//F9
		event.keyCode = 0;
//		event.cancelBubble = true;
		if(myField){
			myField.setValue(myField.getValue() + "宿舍");
		}
		return false;
	}
	if (event.keyCode==121){//F10
		event.keyCode = 0;
//		event.cancelBubble = true;
		if(myField){
			myField.setValue(myField.getValue() + "公司");
		}
		return false;
	}
} 
