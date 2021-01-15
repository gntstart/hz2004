var selRes = null;
var curIndex = -1;
var hzywjo_list;
var hzywjo;
var selectRynbid = null;
var selectHhnbid = null;
var selectRyid = null;
var qhbghhnbid = null;
var bggzarray=[];
var store1 = null;
var changeType = 1;//add by zjm 20181023   1 非全户变更     2 全户变更
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	var hzywid = getQueryParam("hzywid");
	var pch = getQueryParam("pch");
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10020,10019,10002,20000",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	var bggzWindow = new Gnt.ux.selectBggzDialog({
		//选择立户信息回调
		callback: function(arrayTemp,data){
			printfunction(0,arrayTemp,data);
		}
	});	
	var bgspzbxxGrid = new Gnt.ux.SjpzGrid({
		pkname: 'bgzid',
		pzlb: '10029',
		url:'cx/spcx/bgspcx/getBgspzb.json',
		region:'south',
		title: '',
		showPaging:true,
		height:150,
		listeners:{
			rowclick:function(g, rowIndex, e){
			},
			rowdblclick:function(g, rowIndex, e){
			}
		}
	});
	//人员选择
	var p1 = new Gnt.ux.SelectRyxxPanel({
		qhbgShow:true,
		returnBtnText:'确定',
		returnBtnMsg:'必须选择变更人员',
		qhbgCallback:function(jsonData){
			changeType = 2;
			//全户变更成功，这里处理代码
			if(jsonData.list&&jsonData.list[0].voQhbgfhxx[0].hhnbid){
				qhbghhnbid = jsonData.list[0].voQhbgfhxx[0].hhnbid;
				printRk(5,qhbghhnbid,selectRynbid,[true,true,true,true,true],[true,true,false,false,false],[true,true,true,true,true]);
			}
			Ext.getCmp('card').getLayout().setActiveItem(2);
		},
		select:function(list){
			curIndex = 0;
			
			//点击确认后，执行此方法
			Ext.getCmp('card').getLayout().setActiveItem(1);
			//界面更新到下一步
			//v.remove(v.getComponent(0));
			//v.add(p2);
			v.doLayout();
			
			//户成员初始化
			var store = hcyGrid.store;
			store.removeAll();
			
			for(var i=0;i<list.length;i++){
				var data = list[i];
				
				//先初始化户成员
				var rr = new store.reader.recordType(data, data.rynbid);
				
				store.add([rr]);
				
				if(data._sel=='1'){
					var pk = data[hcyGrid.store.pkname];
					
					//从户政业务过来，那么修改户成员的属性
					if(hzywjo_list){
						Ext.each(hzywjo_list, function(jo){
							if(jo.bsqrsfz==data.gmsfhm){
								/**
									2018.08.16
									修改	刁杰
									需要变更姓名
									将拟变更姓名填入姓名
									将原先姓名转移到曾用名
									有曾用名：按照传入的值
									没有曾用名：将原先姓名字段转移到曾用名
								 */
								//实际上这些内容都可以放到Gnt.util.copyHzywToRyz里面统一处理
								if(jo.nbgxm){
									if(jo.cym){
										rr.set("cym", jo.cym);
									}else{
										rr.set("cym", rr.get("xm"));
									}
									rr.set("xm", jo.nbgxm);
								}else if(jo.cym){
									rr.set("cym", jo.cym);
								}
								if(!Gnt.util.isEmpty(jo.whcd)){
									rr.set("whcd", jo.whcd);
								}
								//将户政业务，以及户政业务字表的属性，拷贝到当前户成员
								Gnt.util.copyHzywToRyzl(rr,  jo);
								/*
								 * 20190912 add by zjm 
								 * 拟变更身份证
								 */
								if(!Gnt.util.isEmpty(jo.nbggmsfhm)){
									rr.set("gmsfhm",jo.nbggmsfhm);
								}
								/*
								 * add by zjm 20190927
								 * 中间表带值    
								 * 宗教信仰   文化程度   婚姻状况   兵役情况    身高    职业    职业类别    服务处所    电话号码
								 */
								
								if(!Gnt.util.isEmpty(jo.zjxy)){
									rr.set("zjxy",jo.zjxy);
								}
								if(!Gnt.util.isEmpty(jo.hyzk)){
									rr.set("hyzk",jo.hyzk);
								}
								if(!Gnt.util.isEmpty(jo.byzk)){
									rr.set("byzk",jo.byzk);
								}
								if(!Gnt.util.isEmpty(jo.sg)){
									rr.set("sg",jo.sg);
								}
								if(!Gnt.util.isEmpty(jo.zy)){
									rr.set("zy",jo.zy);
								}
								if(!Gnt.util.isEmpty(jo.zylb)){
									rr.set("zylb",jo.zylb);
								}
								if(!Gnt.util.isEmpty(jo.fwcs)){
									rr.set("fwcs",jo.fwcs);
								}
								if(!Gnt.util.isEmpty(jo.dhhm)){
									rr.set("dhhm",jo.dhhm);
								}
								if(!Gnt.util.isEmpty(jo.rkzp)){
									rr.set("zp",jo.rkzpBase64);
									rkzpMap[data.rynbid] = jo.rkzpBase64;
								}
							}
						});
					}
				}
			}
			
			SetReadOnly(form_yw, 'hb', true);
			
			if(hzywjo){
				form10002.getForm().setValues({
					sbryxm: hzywjo.sqrxm,
					sbrgmsfhm: hzywjo.sqrsfz
				});
			}
			
			(function(){
				hcyGrid.fireEvent("rowclick",hcyGrid,0);
				hcyGrid.getSelectionModel().selectRange(0,0);
			}).defer(200);
			
			if(list.length==1){
				Ext.getCmp('prvBtn').disable();
				Ext.getCmp('nextBtn').disable();
			}
		},
		rowdblclickBack:function(data){
			selectHhnbid = data.hhnbid;
		}
	});
	
	var hcyGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		pzlb: '10020',
		region:'west',
    	title: '户成员列表',
    	width:200,
    	showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){
    			selRes = g.store.getAt(rowIndex);
    			curIndex = rowIndex;
    			
    			selectRynbid = selRes.data.rynbid;
    			selectHhnbid = selRes.data.hhnbid;
    			
				if(curIndex==(hcyGrid.store.getCount()-1)){
					Ext.getCmp('nextBtn').disable();
				}else{
					Ext.getCmp('nextBtn').enable();
				}
				
				if(curIndex==0){
					Ext.getCmp('prvBtn').disable();
				}else{
					Ext.getCmp('prvBtn').enable();
				}
    			//人员基本资料更新
				form_yw.getForm().reset();
				form_yw.getForm().loadRecord(selRes);
    			
				if(rkzpMap[selRes.data.rynbid]){
					selRes.data.zp=rkzpMap[selRes.data.rynbid];
					Gnt.photo.setPhoto(null, true);
    				Gnt.photo.setPhoto(selRes, true);
				}else{
					Gnt.photo.setPhoto(null, true);
    				Gnt.photo.setPhoto(selRes, true);
				}
    			if(hzywjo){
    				
    			}
    			
    		}
		}
	});
	
	//典型：bindStore和bindViewGrid不一样
	var form_yw = new Gnt.ux.SjpzForm({
		title: '户成员信息',
		closable: false,
		pzlb: '10020',
		labelWidth : 180,
		cols:2,
		trackResetOnLoad:true,
		//绑定bindViewGrid，当bindViewGrid点击的时候，触发记录移动
		bindViewGrid: hcyGrid,
		changeDictCust:function(cmb,res){
			//当选择人后，为其它域赋值
			if(cmb.name=="fqxm"){
				this.getForm().setValues({fqgmsfhm:res.data.data});
				selRes.set("fqgmsfhm",res.data.data);
			}
			if(cmb.name=="mqxm"){
				this.getForm().setValues({mqgmsfhm:res.data.data});
				selRes.set("mqgmsfhm",res.data.data);
			}
			if(cmb.name=="jhryxm"){
				this.getForm().setValues({jhrygmsfhm:res.data.data});
				selRes.set("jhrygmsfhm",res.data.data);
			}
			if(cmb.name=="jhrexm"){
				this.getForm().setValues({jhregmsfhm:res.data.data});
				selRes.set("jhregmsfhm",res.data.data);
			}
			if(cmb.name=="jhrexm"){
				this.getForm().setValues({jhregmsfhm:res.data.data});
				selRes.set("jhregmsfhm",res.data.data);
			}
			if(cmb.name=="poxm"){
				Gnt.validpoxb(form_yw,selRes,res,true);
				//add by zjm 20190710 配偶下拉框选中人员后，配偶身份证自动带入
				this.getForm().setValues({pogmsfhm:res.data.data});
				selRes.set("pogmsfhm",res.data.data);
				
			}
			
			return;
		},
		isModify:function(flag){
			if(flag){
				Ext.getCmp("cancelUpdId").setDisabled(false);
			}else{
				Ext.getCmp("cancelUpdId").setDisabled(true);
			}
		},
		fieldChange:function(field, newValue, oldValue){
    		
    		var form = form_yw.getForm();
    		
    		/**
    			暂时只修改性别时分配身份证信息
    			修改了性别后获取身份证号特别特别慢
    		 */
    		if(field.name=="xb"　/*||　field.name=="xm" || field.name=="csrq"*/){
    			/*
    			var store = sfzGrid.store;
				store.baseParams = {
					rynbid:selectRynbid,
					ryxm:form.findField("xm").value,
					ryxb:form.findField("xb").value,
					csrq:form.findField("csrq").value
				};
				store.load();
				
				store.on("load",function(store) {
					if(store.data.length > 0){
						curIndex = 0;
						//form.setValues({gmsfhm:store.getAt(0).data.gmsfhm});
						//必须这样调用赋值，否则无法赋值到store中，看到的都是假的。
						form_yw.fieldSetValue("gmsfhm", store.getAt(0).data.gmsfhm);
					}else{
						showInfo("未获取到新身份证信息!");
					}
				},sfzGrid);
				*/
    		}
    		
    		/**
    			变更姓名时自动填充曾用名
    		 */
    		if(field.name=="xm"){
    			form.setValues({cym:oldValue});
    			hcyGrid.getSelectionModel().getSelected().set("cym",oldValue);
    		}
    		
    	},
        fieldBlur:function(field){
        	var form = form_yw.getForm();
        	var r = hcyGrid.getSelectionModel().getSelected();
        	
        	if(field.name=='yxqxqsrq'){
        		if(field.value && form.findField("csrq").getValue()){
        			
        			if(form.findField("csrq").getValue() > form.findField("yxqxqsrq").getValue()){
        				showInfo("有效期限起始日期不能早于出生日期！");
        				//form.findField("yxqxqsrq").setValue("");
        				
        				form_yw.fieldSetValue(form.findField("yxqxqsrq"),"");
        				
        				
        				return false;
        			}
        			
        			var csrq = form.findField("csrq").value;
        			var yxqxqsrq = form.findField("yxqxqsrq").value;
        			
        			var nl = Number(Gnt.date.getNlc(csrq,yxqxqsrq));
        			
//        			var nl = Number(Gnt.date.jsGetAge(csrq)) - Number(Gnt.date.jsGetAge(yxqxqsrq));
        			
        			var vDay = field.value.substring(6,8);//日
        		    var vMonth = field.value.substring(4,6);//月
        		    var vYear = field.value.substring(0,4);//年
        			
        			if(nl < 16){
        				vYear = Number(vYear) + 5;
        				
        				//form.findField("yxqxjzrq").setValue(vYear + vMonth + vDay);
        				
        				form_yw.fieldSetValue(form.findField("yxqxjzrq"),vYear + vMonth + vDay);
        				
        			}else if(nl >= 16 && nl <= 25){
        				vYear = Number(vYear) + 10;
        				
//        				form.findField("yxqxjzrq").setValue(vYear + vMonth + vDay);
        				form_yw.fieldSetValue(form.findField("yxqxjzrq"),vYear + vMonth + vDay);
        			}else if(nl >= 26 && nl <= 45){
        				vYear = Number(vYear) + 20;
        				
//        				form.findField("yxqxjzrq").setValue(vYear + vMonth + vDay);
        				form_yw.fieldSetValue(form.findField("yxqxjzrq"),vYear + vMonth + vDay);
        			}else if(nl >= 46){
        				
        				form.findField("yxqxjzrq").setValue("");
        			}
        			
        		}
        	}
        	
        	if(field.name=='hyzk' && r.isModified("hyzk")){
        		Gnt.validpo(form_yw,field);
        	}
        	
        	if(field.name=='pogmsfhm' && r.isModified("pogmsfhm")){
        		Gnt.validposfzh(form_yw,field,true);
        	}
    		
        }
    	,fieldFocus:function(field){
			Gnt.filterField(field);
		}
	});
	
	var form10002 = new Gnt.ux.SjpzForm({
		closable: false,
		/*region:'center',
		height:60,*/
		pzlb: '10002',
		labelWidth :  120,
		cols:2,
		//选择人的来源
		bindSelectRyStore: hcyGrid.store,
		changeDictCust:function(cmb,res){
			//当选择人后，为其它域赋值
			this.getForm().setValues({sbrgmsfhm:res.data.data});
			return;
		},
        title: '申报人信息'
	});
	
	var sfzGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		pzlb: '20000',
		title: '',
		url: 'yw/hjyw/bggz/getSfz.json'
	});
	
	var p2 = new Ext.Panel({
		layout:'border',
		items:[hcyGrid,{
	        region:'center',
	        //禁止横向滚动条
	        layout:'border',
	        border:false,
	        frame:false,
	        items:[form_yw,{
	        	 layout:'border',
	        	 region:'south',
	        	 height:60,
	             border:false,
	             frame:false,
	             items:[form10002]
	        }]
	    },{
	    	 region:'east',
	    	 width:150,
	    	 layout:'table',
	    	 align:'center',
	    	 layoutConfig: {
	    		columns: 1
	    	 },
	    	 margins:'5',
	    	 defaults: {
	    			width:'100%',
	    			margins:'15'
	    	 },
	    	 items: [{
					title: '',
					height:'100%',
					bodyStyle:'padding:10px 10px 10px 10px',
					html: '<div id="_PHOTO_ID">照片</DIV>',
					width:150,
					height:180,
					rowspan: 1,
					colspan:1
				},{
	    	    		html:'',
	    	    		bodyStyle:'padding:10px 25px 0px 20px',
		   	    	 	layout:'table',
		   	    	 	align:'center',
		   	    	 	border:false,
		   	    	 	frame:false,
		   	    	 	layoutConfig: {
		   	    	 		columns: 1
		   	    	 	},
		   	    	 	items:[new Ext.Button({
							text:'获取照片',
							minWidth:100,
							id: 'zphqImgBtn',
							width:150,
							handler:function(){
								Gnt.photo.Huoqu();
							}
						}),{
			    	    	height:3,
			    	    	border:false,
			    	    	frame:false
			    	    },new Ext.Button({
								id: 'zpcjImgBtn',
								text:'照片采集',
								minWidth:100,
								width:150,
								handler:function(){
									Gnt.photo.OpenEdit();
								}
							}),{
				    	    	height:3,
				    	    	border:false,
				    	    	frame:false
				    	    },new Ext.Button({
				    	    	id: 'zpscImgBtn',
								text:'照片删除',
								disabled:true,
								minWidth:100,
								handler:function(){
									Gnt.photo.changeImageCallback("");
								}
							}),{
				    	    	height:3,
				    	    	border:false,
				    	    	frame:false
				    	    },new Ext.Button({
								text:'详细信息',
								minWidth:100,
								handler:function(){
									czr={
										ryid:selectRyid,
										rynbid:selectRynbid,
										hhnbid:selectHhnbid
									}
									Gnt.toRyxx(czr);
								}
				    	    }),{
				    	    	height:3,
				    	    	border:false,
				    	    	frame:false
				    	    },new Ext.Button({
				    	    	id:'cancelUpdId',
								text:'取消修改',
								disabled:true,
								minWidth:100,
								handler:function(){
									
									hcyGrid.store.rejectChanges();
									
									form_yw.getForm().reset();
									
									Ext.getCmp("cancelUpdId").setDisabled(true);
									
								}
				    	    }),{
				    	    	height:3,
				    	    	border:false,
				    	    	frame:false
				    	    }/*,new Ext.Button({
								text:'同第一人',
								minWidth:100,
								handler:function(){
									
									Gnt.sameFirst(hcyGrid.store,hcyGrid,form_yw);
									
								}
				    	    })*/,{
				    	    	height:3,
				    	    	border:false,
				    	    	frame:false
				    	    },new Ext.Button({
								text:'下一个',
								minWidth:100,
								id:'nextBtn',
								handler:function(){
									curIndex++;
									hcyGrid.fireEvent("rowclick",hcyGrid,curIndex);
									hcyGrid.getSelectionModel().selectRange(curIndex,curIndex);
								}
				    	    }),{
				    	    	height:3,
				    	    	border:false,
				    	    	frame:false
				    	    },new Ext.Button({
								text:'上一个',
								disabled:true,
								minWidth:100,
								id:'prvBtn',
								handler:function(){
									curIndex--;
									hcyGrid.fireEvent("rowclick",hcyGrid,curIndex);
									hcyGrid.getSelectionModel().selectRange(curIndex,curIndex);
								}
				    	    }),{
				    	    	height:3,
				    	    	border:false,
				    	    	frame:false
				    	    }/*,new Ext.Button({
								text:'常表户口簿打印',
								minWidth:100,
								handler:function(){
									if(hcyGrid.getStore().getCount() > 0){
	            						selectHhnbid=hcyGrid.getSelectionModel().getSelections()[0].data.hhnbid;
	            						selectRynbid=hcyGrid.getSelectionModel().getSelections()[0].data.rynbid;
		            					
	            						printRk(2,selectHhnbid,selectRynbid,[true,true,false,false,false],[true,true,false,false,false],[true,true,false,false,false]);
	            						
	            					}else{
	            						alert("请先执行查询!");
	            					}
									
								}
				    	    }),{
				    	    	height:50,
				    	    	border:false,
				    	    	frame:false
				    	    }*/
				    	    ,{
				    	    	xtype: 'SfzhyButton',
				    	    	form: form_yw
				    	    },{
				    	    	height:50,
				    	    	border:false,
				    	    	frame:false
				    	    },{
				    	    	xtype:'CkdaButton',
				    	    	hzywid:getQueryParam("hzywid")
				    	    },{
				    	    	height:3,
				    	    	border:false,
				    	    	frame:false
				    	    },{
				    	    	xtype: 'CheckClbsBeforeSaveButton',
				    	    	hzywid:getQueryParam("hzywid"),
				    	    	disabled:user.flag>0?true:false,
				    	    	callback: function(){
				    	    		save();
				    	    	}
				    	    }/*,new Ext.Button({
								text:'保存',
								minWidth:100,
								handler:function(){
									save();
								}
				    	    })*/,{
				    	    	height:3,
				    	    	border:false,
				    	    	frame:false
				    	    },new Ext.Button({
								text:'新办理',
								minWidth:100,
								disabled:hzywid?true:false,
								handler:function(){
									showQuestion("数据未保存，确定放弃办理吗？", function(btnType){
										if(btnType=="yes"){
											//重要，否则变更更在具有原来的数据，并且必须在removeAll之前
											/*hcyGrid.store.commitChanges();
											
											hcyGrid.store.removeAll();
											
											Ext.getCmp('card').getLayout().setActiveItem(0);
											v.doLayout();
											p1.newYewu();
											*/
											
											window.location.reload();
											
										}
									});
								}
				    	    }),{
				    	    	height:3,
				    	    	border:false,
				    	    	frame:false
				    	    },new Ext.Button({
								text:'关闭',
								minWidth:100,
								handler:function(){
									showQuestion("数据未保存，确定关闭吗？", function(btnType){
										if(btnType=="yes"){
											if(window.parent.closeWorkSpace){
												window.parent.closeWorkSpace();
											}
										}
									});
								}
				    	    })
			    	 ]
	    	    }
	    	 ]
	    }]
	});
	
	var p3 = new Ext.Panel({
		layout: 'border',
		frame:false,
		borde:false,
		items:[
				{
					   region:'north',
					   height:100,
					   html: '',
					   frame:false,
					   borde:false
				}, {
					id:'wcHtmlId',
		    	   region:'center',
		    	   title:'',
		    	   frame:false,
				   borde:false,
		    	   html: '<center><br/><br/><br/><br/><br/><br/><br/><br/><h2>变更更正业务办理完成！</h2></center>'
		       },{
				   region:'south',
				   height:100,
				   html: '',
				   frame:false,
				   borde:false,
		    	   buttons:[
		    	            new Ext.Button({
								text:'新办理',
								minWidth:100,
								width:150,
								disabled:hzywid?true:false,
								handler:function(){
									window.location.reload();
								}
		    	            }),new Ext.Button({
								text:'常表户口簿打印',
								id:'cbPrint',
								minWidth:100,
								width:150,
								handler:function(){
									if(changeType ==1){
										printRk(3,selectHhnbid,selectRynbid,[true,true,true,true,true],[true,true,false,false,false],[true,true,true,true,true]);
									}else if(changeType ==2){
										printRk(5,qhbghhnbid,selectRynbid,[true,true,true,true,true],[true,true,false,false,false],[true,true,true,true,true]);								}
									}
		    	            }),new Ext.Button({
								text:'变更更正审批表打印',
								id:'bgspbPrint',
								minWidth:100,
								disabled:true,
								width:150,
								handler:function(){
									bggzWindow.show();
									bggzWindow.setDataStore(store1);
									//printfunction(0,bggzarray);
								}
		    	            }),new Ext.Button({
								text:'关闭',
								minWidth:100,
								width:150,
								handler:function(){
									if(parent && parent.closeWorkSpace){
										parent.closeWorkSpace();
									}else{
										window.close();
									}
								}
		    	            })
		    	   ]
		       }
		]
	});
	
	function save(){
//		Ext.Msg.wait("变更更正信息验证中，请稍后...", "等待");
		/**
			户政业务处理过来的数据不进行业务校验
			直接办理的数据修改后进行校验，包括业务
		 */
			if(!form_yw.checkInput(false,"6", false, hcyGrid))
				return;
//			if(!form_yw.checkInput(false,'4', true, hcyGrid))
//				return;
			if(!form10002.getForm().isValid()){
				showErr("申报人信息必须填写！");
				return;
			}
			
			if(!Gnt.validHz(5,form_yw,form_yw)){
				return ;
			}
			//判断非户必须存在户主
			var exists = false;
			var cnFlag = true;
			var icount = 0;
			hcyGrid.store.each(function(r){
				var data = r.data;
				
				icount++;
				if(data.yhzgx == "01" || data.yhzgx =="02" || data.yhzgx == "03"){
					exists = true;
					
					var nl = Gnt.date.getNl(data.gmsfhm, data.csrq);
					if(nl < 18){
						if(window.confirm("户主未满18周岁,是否继续保存?")){
							cnFlag = true ;
						}else{
							cnFlag = false ;
						}
					}
				}
			});
			
			if(!cnFlag){
				return ;
			}
			
			if(!exists && icount>0){
				showErr("户主已迁出，请选择新户主！");
				return;
			}
			
			//初始化变更更正数据
			Gnt.yw.initBggzStore(hcyGrid, bggzWin.grid.store,null,null,hcyGrid.pzlb);
			
			if(bggzWin.grid.store.getCount()>0){
				//如果存在变更更正，那么弹出对话框进行确认
				bggzWin.show();
			}else{
				//如果未存在变更更正，那么视业务具体流程，是否继续进行保存操作还是中断操作
				//submitData();
				showInfo("数据未有变动，不能提交变更更正业务！");
			}
	}
	
	//变更更正提交数据
	function submitData(bggzxx){
		var subdata = {
			bggzxx: bggzxx,
			sbjbxx:{},
			ryList:new Array()
		};
		
		//申报人信息
		subdata.sbjbxx = form10002.getForm().getValues();
		if(hzywjo){
			subdata.sbjbxx.hzywid = hzywjo.id;
		}
		
		subdata.sbjbxx = Ext.encode(subdata.sbjbxx);
		subdata.bggzxx = Ext.encode(subdata.bggzxx);
		
		//记录日志的操作码
		log_code = "F1013";
		
		///yw/hjyw/swzx
		Gnt.submit(
			subdata, 
			"yw/hjyw/bggz/processBggzyw", 
			"正在处理变更更正信息，请稍后...", 
			function(jsonData,params){
				showInfo("变更更正成功！",function(){
					Ext.getCmp('card').getLayout().setActiveItem(2);
					Ext.getCmp('vp').doLayout();
					
					if(bggzWin.isVisible())
						bggzWin.hide();
					
					var formValues=form_yw.getForm().getValues();
					selectHhnbid="";
					selectRynbid="";
					if(jsonData && jsonData.list[0] && jsonData.list[0].voBggzfhxx){
						for(var i=0; i < jsonData.list[0].voBggzfhxx.length; i++){
							selectRynbid += jsonData.list[0].voBggzfhxx[i].rynbid + ",";
							selectHhnbid = jsonData.list[0].voBggzfhxx[0].hhnbid;
						}
						for(var i=0; i < jsonData.list[0].voHcygxtzfhxx.length; i++){
							selectRynbid += jsonData.list[0].voHcygxtzfhxx[i].rynbid + ",";
							selectHhnbid = jsonData.list[0].voHcygxtzfhxx[0].hhnbid;
						}
						if(selectRynbid.length > 0){
							selectRynbid = selectRynbid.substr(0,selectRynbid.length - 1);
						}
						printRk(3,selectHhnbid,selectRynbid,[true,true,true,true,true],[true,true,false,false,false],[true,true,true,true,true]);
						if(hzywjo&&hzywjo.id){
							Gnt.ux.Dict.getKzcs('10036', function(pz, user) {
								if(pz.kzz==1){
									pjq('ES',user.ip,hzywjo.id);
								}
							});
						}
						
					}
					//printMore(true,3,selectHhnbid,hcyGrid.store,[true,true,true,true,true],[true,true,false,false,false],[true,true,true,true,true]);
					
					//关闭当前窗口
					if(parent.closeActiveWorkSpace)
						parent.closeActiveWorkSpace(jsonData);
				});
			},
			function(json){
				if(json.message){
					if(json.errcode && json.errcode==508){
						//需要审批，调用审批模板
						new Gnt.ux.SpclDialog({
							callback:function(data){
								var spmbid = json.spmbid;
								data.spmbid = spmbid;
								if(data.cl) data.cl = Ext.encode(data.cl);
								data.sbjbxx = subdata.sbjbxx;
								data.bggzxx = subdata.bggzxx;
								
								Gnt.submit(
										data, 
										"yw/hjyw/bggz/processBgspdjyw", 
										"正在处理变更更正审批登记信息，请稍后...", 
										function(jsonData,params){
											showInfo("变更更正审批信息保存成功！",function(){
//												bggzarray=[];
//												for(var i=0; i < jsonData.list[0].voBgspdjfhxx.length; i++){
//													var o={};
//													o.spywid=jsonData.list[0].voBgspdjfhxx[i].spywid;
////													o.directTable="bgspb";
//													bggzarray.push(jsonData.list[0].voBgspdjfhxx[i]);
//												}
												if(jsonData.list[0].voBgspdjfhxx.length>0){
													store1 = bgspzbxxGrid.store;
													store1.removeAll();
													store1.load({params:{start:0, limit:20, spywid:jsonData.list[0].voBgspdjfhxx[0].spywid}});
													store1.on('load',function(s,records){
														bggzWindow.show();
														bggzWindow.setDataStore(s);
													});
												}
												if(hzywjo&&hzywjo.id){
													Gnt.ux.Dict.getKzcs('10036', function(pz, user) {
														if(pz.kzz==1){
															pjq('ES',user.ip,hzywjo.id);
														}
													});
												}
//												printfunction(0,bggzarray);
												Ext.getCmp('wcHtmlId').body.update("<center><br/><br/><br/><br/><br/><br/><br/><br/><h2>变更更正审批信息保存成功！</h2></center>");
												
												Ext.getCmp('bgspbPrint').enable();
												Ext.getCmp('cbPrint').disable();
												Ext.getCmp('card').getLayout().setActiveItem(2);
												Ext.getCmp('vp').doLayout();
											});
										});
							}
						}).show();
					}else{
						showErr(json.message);
					}
				}
			}
		);
	}
	
	//变更更正对话框
	bggzWin = new Gnt.ux.BggzDialog({
		hcyGrid:hcyGrid, //变更更正户成员数据集
		submitData:submitData,	//变更更正确认回调方法
		title:'确认变更更正项目'
	});
	
	var v = new Ext.Viewport({
        layout:'fit',
        id:'vp',
        border:false,
        items: {
        	layout:'card',
        	border:false,
        	id:'card',
        	frame:false,
        	activeItem: 0,
        	xtype: 'panel',
    	    //bodyStyle: 'padding:2px',
        	items:[p1,p2,p3]
        }
    });
	
	//户政业务处理
	if(hzywid && hzywid!=""){
		Gnt.makeHzyw({
			hzywid: hzywid,
			pch:pch,
			cmpids: [],
			callback:function(jo, jolist){
				//成功处理返回，合并数据
				hzywjo_list =[];
				hzywjo_list.push(jo);
				Ext.each(jolist,function(d){
					hzywjo_list.push(d);
				});
				hzywjo = jo;
				
				p1.setHzyw(hzywjo, hzywjo_list);
			}
		});
	}
	if(getQueryParam("jumpToBggz")){
		p1.setZjHzyw(getQueryParam("gmsfhm"),getQueryParam("jumpToBggz"));
	}
	/*
	function getHzywJo(gmsfhm){
		Ext.each(hzywjo_list, function(jo){
			if(jo.bsqrsfz==gmsfhm){
				return jo;
			}
		});
	}
	*/
	
	/**
		点击回车时查询
	 */
	new Ext.KeyMap(document, {
		key:Ext.EventObject.ENTER,
		fn:function() {
			p1.form10016.buttons[0].handler();
		},
		scope: this  
	});
	
	
});