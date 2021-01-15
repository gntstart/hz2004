var selRes = null;
var curIndex = -1;
var selectRynbid = "";
var selectHhnbid = "";
var selectRyid ="";
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10016,20016,10019,10002,10005",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	
	//迁出人员选择
	var p1 = new Gnt.ux.SelectRyxxPanel({
		returnBtnText:'确定',
		returnBtnMsg:'必须选择变更人员',
		select:function(list){
			curIndex = 0;
			
			//点击确认迁出后，执行此方法
			Ext.getCmp('card').getLayout().setActiveItem(1);
			//界面更新到下一步
			//v.remove(v.getComponent(0));
			//v.add(p2);
			v.doLayout();
			
			//户成员初始化
			var store = hcyGrid.store;
			store.removeAll();
			
			//初始化迁出登记store
			var newres = new Array();
			var res = [];
			var qcdbdfw = "";
			
			for(var i=0;i<list.length;i++){
				var data = list[i];
				var rr = new store.reader.recordType(data, data.rynbid);
				store.add([rr]);
				
				if(data._sel=='1'){
					//迁出人员，那么初始化户别变更
					if(qcdbdfw=="" && data.qcdbdfw){
						qcdbdfw = data.qcdbdfw;
					}
					
					var pk = data[hbbgStore.pkname];
					var r = new hbbgStore.reader.recordType({}, pk);
					r.set(hbbgStore.pkname, pk);
					//"kdqqy_zqz","kdqqy_qrdz","kdqqy_qyldyy","kdqqy_qczxlb"
					
					r.set("hbbgrq",Ext.util.Format.date(new Date(),'Ymd'));
					if(data.hb){
						r.set("bgqhb",data.hb);
					}
					/**
						设置变更前户别
					 */
					
					
					newres.push(r);
				}
			}

			if(newres.length>0)
				hbbgStore.add(newres);
			
			(function(){
				hcyGrid.fireEvent("rowclick",hcyGrid,0);
				hcyGrid.getSelectionModel().selectRange(0,0);
			}).defer(200);
			
			//form_yw.getForm().findField("hbbgrq").setMinValue(Ext.util.Format.date(new Date(),'Ymd'));
			SetReadOnly(form_yw, 'hbbgrq', true);//add  by zjm 20190926 户别变更日期默认当天不让修改
			if(list.length==1){
				Ext.getCmp('prvBtn').disable();
				Ext.getCmp('nextBtn').disable();
			}
		}
	});
	
	var hcyGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		pzlb: '10019',
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
    			form_hcy.getForm().reset();
    			form_hcy.getForm().loadRecord(selRes);
    			
    			//户别变更初始化
    			form_yw.getForm().reset();
    			
    			if(selRes.data._sel=="1"){
    				//为迁出登记form赋值
    				form_yw.setVisible(true);
    				var pkvalue = selRes.data[form_yw.bindStore.pkname];
    				var re = form_yw.bindStore.getById(pkvalue);
    				if(re){
    					form_yw.getForm().loadRecord(re);
    				}else{
    					alert("警告：户别变更" + pkvalue + "不存在！");
    				}
    				
    				Gnt.photo.setPhoto(null, true);
    				Gnt.photo.setPhoto(selRes, true);
    				
    			}else{
    				//不是迁出人员，隐藏
    				form_yw.setVisible(false);
    				
    				Gnt.photo.setPhoto(selRes, false);
    				
    			}
    			
    		}
		}
	});
	
	var form_hcy = new Gnt.ux.SjpzForm({
		title: '户成员信息',
		closable: false,
		/*region:'north',
		height:330,*/
		pzlb: '10019',
		labelWidth :  120,
		cols:2,
		trackResetOnLoad:true,
		//bindStore:hcyGrid.store,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:hcyGrid,
		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
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
			if(cmb.name=="poxm"){
				Gnt.validpoxb(form_hcy,selRes,res,true);
				//add by zjm 20190710 配偶下拉框选中人员后，配偶身份证自动带入
				this.getForm().setValues({pogmsfhm:res.data.data});
				selRes.set("pogmsfhm",res.data.data);
			}
			
			return;
		},
        isModify:function(flag){
//        	if(form_hcy.getForm().isDirty()){
        	if(flag){
        		Ext.getCmp("cancelUpdId").setDisabled(false);
        	}else{
        		Ext.getCmp("cancelUpdId").setDisabled(true);
        	}
        },
        fieldBlur:function(field){
        	var f = field.findParentByType("sjpz_form");
        	var r = hcyGrid.getSelectionModel().getSelected();
        	/*
        	if(field.name=='hyzk' && r.isModified("hyzk")){
        		Gnt.validpo(form_hcy,field);
        	}
        	
        	if(field.name=='pogmsfhm' && r.isModified("pogmsfhm")){
        		Gnt.validposfzh(form_hcy,field,true);
        	}
    		*/
        }
        ,fieldFocus:function(field){
			Gnt.filterField(field);
		}
	});
	
	var form_sbr = new Gnt.ux.SjpzForm({
		closable: false,
		/*region:'north',
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

	//迁出登记store
	var hbbgStore = new Gnt.store.SjpzStore({
		pzlb:'10005',
		pkname:'rynbid'
	});
	
	//典型：bindStore和bindViewGrid不一样
	var form_yw = new Gnt.ux.SjpzForm({
		title: '户别变更',
		closable: false,
		/*region:'center',
		height:60,*/
		pzlb: '10005',
		labelWidth :  120,
		cols:2,
		//绑定到store，输入域改动的时候，自动赋值
		bindStore: hbbgStore,
		//绑定bindViewGrid，当bindViewGrid点击的时候，触发记录移动
		bindViewGrid: hcyGrid
	});
	
	var p2 = new Ext.Panel({
		layout:'border',
		items:[hcyGrid,{
	        region:'center',
	        //禁止横向滚动条
	        layout:'border',
	        border:false,
	        frame:false,
	        items:[form_hcy,{
	        	 layout:'border',
	        	 region:'south',
	        	 height:180,
	             border:false,
	             frame:false,
	             items:[form_sbr,{
	     	        region:'south',
	    	        //禁止横向滚动条
	    	        layout:'border',
	    	        height:120,
	    	        border:false,
	    	        frame:false,
	    	        items:[form_yw]
	    	    }]
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
									
									form_hcy.getForm().reset();
									
									hcyGrid.store.rejectChanges();
									
									Ext.getCmp("cancelUpdId").setDisabled(true);
									
								}
				    	    }),{
				    	    	height:3,
				    	    	border:false,
				    	    	frame:false
				    	    },new Ext.Button({
								text:'同第一人',
								minWidth:100,
								handler:function(){
									
									Gnt.sameFirst(hbbgStore,hcyGrid,form_yw);
									
								}
				    	    }),{
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
				    	    },{
				    	    	height:50,
				    	    	border:false,
				    	    	frame:false
				    	    },new Ext.Button({
								text:'保存',
								minWidth:100,
								disabled:user.flag>0?true:false,
								handler:function(){
									save();
								}
				    	    }),{
				    	    	height:3,
				    	    	border:false,
				    	    	frame:false
				    	    },new Ext.Button({
								text:'新办理',
								minWidth:100,
								handler:function(){
									showQuestion("数据未保存，确定放弃办理吗？", function(btnType){
										if(btnType=="yes"){
											Ext.getCmp('card').getLayout().setActiveItem(0);
											v.doLayout();
											p1.newYewu();
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
		    	   html: '<center><br/><br/><br/><br/><br/><br/><br/><br/><h2>户别变更业务办理完成！</h2></center>'
		       },{
				   region:'south',
				   layout:'table',
				   height:50,
				   html: '',
				   border:false,
	   	    	   frame:false,
				   layoutConfig: {
	   	    	 		columns:20
	   	    	 	},
				   items:[
					    {
	    	            	frame:false,
	    					border:false,
	    					xtype:'panel',
	    					html:'',
	    					width:10
	    	            },new Ext.Button({
							text:'新办理',
							minWidth:100,
							width:150,
							handler:function(){
								window.location.reload();
							}
	    	            }),{
	    	            	frame:false,
	    					border:false,
	    					xtype:'panel',
	    					html:'',
	    					width:10
	    	            },{
			    	    	xtype: 'CjdaButton',
			    	    	form: form_yw,
			    	    	disabled:true,
			    	    	ywcode:'110'
			    	    },{
			    	    	frame:false,
	    					border:false,
	    					xtype:'panel',
	    					html:'',
	    					width:10
	    	            },{
			    	    	xtype:'CkdaButton',
			    	    	clbs:'1',
			    	    	hzywid:getQueryParam("hzywid")
			    	    },/*new Ext.Button({
							text:'查阅档案',
							minWidth:100,
							width:150,
							handler:function(){
	   							Gnt.ux.Dict.getKzcs('10029',function(pz, user){
	   								var url = pz.bz;
	   								if(url.indexOf("?")<0)
	   									url += "?";
	   								else
	   									url += "&";
	   								
	   								url += "ryid=&ywcode=110&spywid=" + selRes.data.spywid;
	   								url += "&sfz=" +  selRes.data.gmsfhm;
	   								url += "&sbrsfzh=" +  selRes.data.sqrgmsfhm;
	   								url += "&dlmj=" + user.yhdlm;
	   								url += "&yhsfzh=" + user.gmsfhm;
	   								
	   								window.open(url);
	   							});
	   						}
	    	            }),*/{
	    	            	frame:false,
	    					border:false,
	    					xtype:'panel',
	    					html:'',
	    					width:10
	    	            },new Ext.Button({
							text:'变更审批表打印',
							id:'bgspbdy',
							minWidth:100,
							width:150,
							handler:function(){
							}
	    	            }),{
	    	            	frame:false,
	    					border:false,
	    					xtype:'panel',
	    					html:'',
	    					width:10
	    	            },new Ext.Button({
							text:'常表户口簿打印',
							id:"cbPrint",
							minWidth:100,
							width:150,
							handler:function(){
								printRk(3,selectHhnbid,selectRynbid,[true,true,true,true,true],[true,true,false,false,false],[true,true,true,true,true]);
							}
	    	            }),{
	    	            	frame:false,
	    					border:false,
	    					xtype:'panel',
	    					html:'',
	    					width:10
	    	            },new Ext.Button({
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
	    	            }),{
	    	            	frame:false,
	    					border:false,
	    					xtype:'panel',
	    					html:'',
	    					width:10
	    				}
	    	   ]
		       }
		]
	});
	
	function save(){
			/*
			if(!form_hcy.checkInput(false,"6", null, hcyGrid))
				return;
			*/
			if(!form_yw.checkInput(false, "1", true, hcyGrid))
				return;
			
			if(!form_sbr.getForm().isValid()){
				showErr("申报人信息必须填写！");
				return;
			}
			
			if(!Gnt.validHz(null,form_hcy,form_hcy)){
				return ;
			}
			
			//判断非迁出户必须存在户主
			var exists = false;
			var icount = 0;
			hcyGrid.store.each(function(r){
				var data = r.data;
				/*
				if(data._sel=="1"){
					//迁出成员
				}else{
					icount++;
					if(data.yhzgx=="01" || data.yhzgx=="02" || data.yhzgx=="03")
						exists = true;
				}
				*/
				icount++;
				if(data.yhzgx=="01" || data.yhzgx=="02" || data.yhzgx=="03")
					exists = true;
			});
			
			if(!exists && icount>0){
				showErr("户主已迁出，请选择新户主！");
				return;
			}
			
			
			//初始化变更更正数据
			Gnt.yw.initBggzStore(hcyGrid, bggzWin.grid.store,null,null,hcyGrid.pzlb);
			
			if(bggzWin.grid.store.getCount()>0){
				bggzWin.show();
			}else{
				
				submitData();
			}
			
	}
	
	//变更更正对话框
	bggzWin = new Gnt.ux.BggzDialog({
		hcyGrid:hcyGrid, //变更更正户成员数据集
		submitData:submitData,	//变更更正确认回调方法
		title:'确认变更更正项目'
	});
	
	//提交数据
	function submitData(bggzxx){
		var subdata = {
			bggzxx:bggzxx,
			sbjbxx:{},
			ryList:new Array(),
			hbbgxx:new Array()
		};
		
		//人员内部id和上笔户籍业务的关系
		var map = {};
		hcyGrid.store.each(
			function(rec){
				map[rec.data.rynbid] = rec.data.cjhjywid;
			},	hcyGrid
		);
		
		//申报人信息
		subdata.sbjbxx = form_sbr.getForm().getValues();
		
		//迁出注销信息
		for(var index=0;index<hbbgStore.getCount();index++){
			var data = hbbgStore.getAt(index).data;
			data.sbhjywid = map[data.rynbid];
			
			subdata.hbbgxx.push(data);
		}
		
		subdata.sbjbxx = Ext.encode(subdata.sbjbxx);
		subdata.bggzxx = Ext.encode(subdata.bggzxx);
		subdata.hbbgxx = Ext.encode(subdata.hbbgxx);
		
		///yw/hjyw/qczx
		log_code = "F1012";
		Gnt.submit(
			subdata, 
			"yw/hjyw/hbbg/processHbbgyw", 
			"正在处理户别变更信息，请稍后...", 
			function(jsonData,params){
				showInfo("户别变更成功！",function(){
					
					Ext.getCmp('card').getLayout().setActiveItem(2);
					Ext.getCmp('vp').doLayout();
					
					if(bggzWin.isVisible())
						bggzWin.hide();
					if(jsonData && jsonData.list[0] && jsonData.list[0].voHbbgfhxx){
						selectHhnbid = jsonData.list[0].voHbbgfhxx[0].hhnbid;
						selectRynbid ="";
						for(var i=0; i < jsonData.list[0].voHbbgfhxx.length; i++){
							selectRynbid += jsonData.list[0].voHbbgfhxx[i].rynbid + ",";
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
					Ext.getCmp('bgspbdy').hide();
					//关闭当前窗口
					if(parent.closeActiveWorkSpace)
						parent.closeActiveWorkSpace(jsonData);
					
				});
			},
			function(json){
				if(json.message){
					if(json.errcode && json.errcode==514){
						//需要审批，调用审批模板
						new Gnt.ux.SpclDialog({
							callback:function(data){
								var spmbid = json.spmbid;
								data.spmbid = spmbid;
								if(data.cl) data.cl = Ext.encode(data.cl);
								data.sbjbxx = subdata.sbjbxx;
								data.hbbgxx = subdata.hbbgxx;
								Ext.getCmp('bgspbdy').hide();
								selectRynbid = "";
								selectHhnbid = "";								
								Gnt.submit(
									data, 
									"yw/hjyw/hbbg/processHbbgspdjyw", 
									"正在处理户别变更审批登记信息，请稍后...", 
									function(jsonData,params){
										showInfo("户别变更审批信息保存成功！",function(){
											if(hzywjo&&hzywjo.id){
												Gnt.ux.Dict.getKzcs('10036', function(pz, user) {
													if(pz.kzz==1){
														pjq('ES',user.ip,hzywjo.id);
													}
												});
											}
											Ext.getCmp('cbPrint').disable();
											Ext.getCmp('wcHtmlId').body.update("<center><br/><br/><br/><br/><br/><br/><br/><br/><h2>户别变更审批信息保存成功！</h2></center>");
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
});