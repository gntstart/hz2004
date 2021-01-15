var selRes = null;
var curIndex = -1;
var ywid = 0;
var rynbId = "";
var hzywjo_list;
var hzywjo;
var lhHlx = null;
var selectRynbid = null;
var selectHhnbid = null;
var selectGlHhnbid = null;
var selectHjywid = null;
var checkFlag = false;
var bgValig = true;//户政平台更新过原迁出户成员信息标志
var rhFlag = 0;
var hb = null;
var initlist, inithxx;
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	var hzywid = getQueryParam("hzywid");
	var pch = getQueryParam("pch");
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10016,20016,10019,10002,10015,20001,10017",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	var old_hxx = null;
	var old_pcs = null, new_pcs = null;
	var old_jcwh = null, new_jcwh = null;
	var old_ssxq = null, new_ssxq = null;
	
	/**
		入户时迁入/迁出所有人员
	 */
	var allStore = new Gnt.store.SjpzStore({
		pkname: 'rynbid',
		pzlb: '10019'
	});

	//迁出人员选择
	var p1 = new Gnt.ux.SelectRyxxPanel({
		returnBtnText:'确定',
		returnBtnMsg:'必须选择变动人员',
		select:function(list, hxx){
			curIndex = 0;
			
			//保存原来辖区和居委会
			old_hxx = hxx;
			old_pcs = old_hxx.pcs;
			old_jcwh = old_hxx.jcwh;
			old_ssxq = old_hxx.ssxq;
			
			//点击确认后，执行此方法
			Ext.getCmp('card').getLayout().setActiveItem(1);
			//界面更新到下一步
			//v.remove(v.getComponent(0));
			//v.add(p2);
			v.doLayout();
			/**
				原户成员列表
			yhcyGrid.store.load({ params: { hhnbid: list[0].hhnbid} });
			 */
			
//			Ext.getCmp("qrlhId").setVisible(false);
			initlist = list;
			inithxx = hxx;
			if(hzywjo && hzywjo_list.length>0){
				if(hzywjo.lhbs=='1'){
					Ext.getCmp("rhBtn").handler();
				}else if(hzywjo.lhbs=='2'){
					Ext.getCmp("lhBtn").handler();
				}else if(hzywjo.lhbs=='3'){
					Ext.getCmp("tzfhBtn").handler();
				}
			}
		},
		rowdblclickBack : function(data){
			hb = data.hb;
		}
	});
	function initData(list, hxx){
		//户成员初始化
		var storeYqc = yqchcyGrid.store;
		var storeZzbd = ywGrid.store;
		storeYqc.removeAll();
		storeZzbd.removeAll();
		
		//初始化迁出登记store
		var newres = new Array();
		var res = [];
		var qcdbdfw = "";
		
		for(var i=0;i<list.length;i++){
			var data = list[i];
			
			var rr = new storeYqc.reader.recordType(data, data.rynbid);
			
			/**
				原迁出户成员列表 增加记录
			 */
			storeYqc.add([rr]);
			
			allStore.add([new allStore.reader.recordType(data, data.rynbid)]);
			
			rynbId = data.rynbid;
			
			if(data._sel=='1'){
				if(!selectGlHhnbid){
					selectGlHhnbid = data.hhnbid;
				}
				
				/**
					住址变动人员列表	增加记录
				 */
				
//				storeZzbd.add([rr]);
				
				var pk = data[zzbdStore.pkname];
				var r = new zzbdStore.reader.recordType({}, pk);
				r.set(zzbdStore.pkname,pk);
				
				/**
					立户时可能立集体户
				 */
//				r.set("zzbdhhb","6");
				r.set("zzbdrq",Ext.util.Format.date(new Date(),'Ymd'));
				
				/**
					设置变动后户别及变动日期只读
				 */
				SetReadOnly(form_yw, 'zzbdhhb', true);
				SetReadOnly(form_yw, 'zzbdrq', true);
				
				if(hzywjo_list){
					Ext.each(hzywjo_list, function(jo){
						if(jo.bsqrsfz == data.gmsfhm){
							
							r.set("zzbdlb",jo.zzbdlb);
							
							/**
    							2018.08.15
    							修改	刁杰
    							线上要求变动范围不从户政平台获取
    							2019.07.12 by zjm
    							又要求变动范围不从户政平台获取
							 */
//							if(jo.bdfw){
//								if(Gnt.ux.Dict.getDictLable('CS_BDFW', jo.bdfw)=jo.bdfw){
//									r.set("bdfw",jo.bdfw);
//								}
//							}
							if(jo.bdfw){
								r.set("bdfw",setBdfw(jo.bdfw));
							}
//							r.set("jthzl",jo.jthzl);
							r.set("nyzyrklhczyydm",jo.nyzyrklhczyydm);
							r.set("jjqx_pdbz",jo.jjqx_pdbz);
							r.set("zczjyhjzwnys_pdbz",jo.zczjyhjzwnys_pdbz);
							//add by zjm 增加变动后集体户中间表带值
							if(rhFlag != 2&&hb=='7'&&!Gnt.util.isEmpty(jo.bdhjth)&&(jo.bdhjth=='1'||jo.bdhjth=='2'||jo.bdhjth=='3')){
								r.set("jthzl",jo.bdhjth);
							}
							//add by zjm 20190705 曾用名置灰 不可修改
							//SetReadOnly(form_hcy, 'cym', true);
							Gnt.util.copyHzywToRyzl(r,  jo);
							// add by zjm 20190719 中间表带入值
							if(!Gnt.util.isEmpty(jo.jgssxq)){
								rr.set("jgssxq",jo.jgssxq);
							}
							if(!Gnt.util.isEmpty(jo.csdssxq)){
								rr.set("csdssxq",jo.csdssxq);
							}
							if(!Gnt.util.isEmpty(jo.sg)){
								rr.set("sg",jo.sg);
							}
							if(!Gnt.util.isEmpty(jo.whcd)){
								rr.set("whcd",jo.whcd);
							}
							if(!Gnt.util.isEmpty(jo.zy)){
								rr.set("zy",jo.zy);
							}
							if(!Gnt.util.isEmpty(jo.zylb)){
								rr.set("zylb",jo.zylb);
							}
							//modify by zjm 20190812 监护人姓名 公民身份号码 关系都不为空，才替换
							if(!Gnt.util.isEmpty(jo.jhryjhgx)&&!Gnt.util.isEmpty(jo.jhrygmsfhm)&&!Gnt.util.isEmpty(jo.jhryxm)){
								rr.set("jhryjhgx",jo.jhryjhgx);
								rr.set("jhrygmsfhm",jo.jhrygmsfhm);
								rr.set("jhryxm",jo.jhryxm);
							}
							if(!Gnt.util.isEmpty(jo.jhrejhgx)&&!Gnt.util.isEmpty(jo.jhregmsfhm)&&!Gnt.util.isEmpty(jo.jhrexm)){
								rr.set("jhrejhgx",jo.jhrejhgx);
								rr.set("jhregmsfhm",jo.jhregmsfhm);
								rr.set("jhrexm",jo.jhrexm);
							}
							if(!Gnt.util.isEmpty(jo.zjxy)){
								rr.set("zjxy",jo.zjxy);
							}
							if(!Gnt.util.isEmpty(jo.hyzk)){
								rr.set("hyzk",jo.hyzk);
							}
							if(!Gnt.util.isEmpty(jo.byzk)){
								rr.set("byzk",jo.byzk);
							}
							if(!Gnt.util.isEmpty(jo.xx)){
								rr.set("xx",jo.xx);
							}
							//add by zjm 20190927 中间标带值 与户主关系 信息级别 人员类别 服务处所 电话号码 备注
							if(!Gnt.util.isEmpty(jo.yhzgx)){
								rr.set("yhzgx",jo.yhzgx);
							}
							if(!Gnt.util.isEmpty(jo.xxjb)){
								rr.set("xxjb",jo.xxjb);
							}
							if(!Gnt.util.isEmpty(jo.rylb)){
								rr.set("rylb",jo.rylb);
							}
							if(!Gnt.util.isEmpty(jo.fwcs)){
								rr.set("fwcs",jo.fwcs);
							}
							if(!Gnt.util.isEmpty(jo.dhhm)){
								rr.set("dhhm",jo.dhhm);
							}
							if(!Gnt.util.isEmpty(jo.bz)){
								rr.set("bz",jo.bz);
							}
							if(!Gnt.util.isEmpty(jo.rkzp)){
								rr.set("zp",jo.rkzpBase64);
								rkzpMap[data.rynbid] = jo.rkzpBase64;
							}
							bgValig =false;
						}
					});
				}
				ywGrid.store.add([rr]);
//				r.set("zzbdlb","");
				SetReadOnly(form_hcy, 'cym', true);
				newres.push(r);
				
			}
		}
		
		if(newres.length>0){
			zzbdStore.add(newres);
		}
		
		if(hzywjo){
			form_sbr.getForm().setValues({
				sbryxm: hzywjo.sqrxm,
				sbrgmsfhm: hzywjo.sqrsfz
			});
		}
		
		/**
			默认先全部点击一次
			否则提交时验证所有记录却只在第一条数据停留
		 */
		//当入到同一户报错后，改其他方式就报错 ，所以注释掉 20200520
//		if(ywGrid.store.getCount() > 0){
//			for(var index=0;index<ywGrid.store.getCount();index++){
//				ywGrid.fireEvent("rowclick",ywGrid,index);
//				ywGrid.getSelectionModel().selectRange(index,index);
//			}
//		}
		
		(function(){
			if(ywGrid.store.getCount() > 0){
				ywGrid.fireEvent("rowclick",ywGrid,0);
				ywGrid.getSelectionModel().selectRange(0,0);
			}
			
			checkFlag = true;
		}).defer(200);
		
		
		if(list.length==1){
			Ext.getCmp('prvBtn').disable();
			Ext.getCmp('nextBtn').disable();
		}
	}
	var qrhdxxGrid = new Gnt.ux.SjpzGrid({
		pkname: 'hhnbid',
		pzlb: '20001',
		title: '',
		url: 'yw/common/queryHjxx.json',
		showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){
				
			}
		}
	});
	
	var hxxGrid = new Gnt.ux.SjpzGrid({
		pkname: 'hlx',
		pzlb: '10017',
		url: 'yw/common/queryHjxx.json',
		title: '',
		showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){
				
			}
		}
	});
	
	var formQrhdxx = new Gnt.ux.SjpzForm({
		closable: false,
		pzlb: '20001',
		labelWidth :  200,
		cols:2,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:qrhdxxGrid,
		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
		title: '迁入户地信息',
		getSelectRy:function(rylist){
			
		}
	});
	
	var qrlhxxGrid = new Gnt.ux.SjpzGrid({
		pkname: 'hlx',
		pzlb: '10017',
		title: '',
		showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){
				
			}
		}
	});
	
	var formQrlhxx = new Gnt.ux.SjpzForm({
		closable: false,
		pzlb: '10017',
		labelWidth :  200,
		cols:2,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:qrlhxxGrid,
		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
		title: '迁入立户信息',
		getSelectRy:function(rylist){
			
		}
	});
	
	var yhcyGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		pzlb: '10019',
		region:'center',
		url: 'yw/common/queryRyxx.json',
		title:'原户成员列表',
		//加载成功，回调函数
		loadCallback:function(res, store, grid){
		},
		listeners:{
			rowclick:function(g, rowIndex, e){
				
    		},
    		rowdblclick:function(g, rowIndex, e){
    			
    		}
		}
	});
	
	var rhWindow = new Gnt.ux.SelectRh({
		returnTitleText : '住址变动 ——迁入户信息查询',
		qydjFlag:true,
		callback : function(optype, hcyList, selectHcy, hxx){
			new_pcs = hxx.pcs;
			new_jcwh = hxx.jcwh;
			new_ssxq = hxx.ssxq;
			initData(initlist, inithxx);
			//户成员初始化
			var store = yqrhcyGrid.store;
//			console.log(yqchcyGrid.store.getAt(0).data.rynbid);
			//人员选择回调：审批业务，迁入人员
			if(hcyList != null && hcyList.length > 0){
				
				/**
					迁入迁出不能是同一户
				 */
				if(hcyList[0].hhnbid == yqchcyGrid.store.getAt(0).data.hhnbid){
					
					formQrhdxx.getForm().reset();
					yhcyGrid.store.removeAll();
					
					showInfo("不能在同户内住址变动！");
					
					return ;
				}
				
				for(var i=0;i<hcyList.length;i++){
					var data = hcyList[i];
					//data._sel = "0";
					var rr = new store.reader.recordType(data, data[store.pkname]);
					store.add([rr]);
					
					allStore.add([new allStore.reader.recordType(data, data.rynbid)]);
				}
				
				initYw();
				
				//点击确认迁出后，执行此方法
				Ext.getCmp('card').getLayout().setActiveItem(2);
				
				v.doLayout();
			}
		},
		rowclickBack : function(optype, data){
			hb = data.hb;
			
			var store = yhcyGrid.store;
			store.baseParams = {
				pzlb: store.pzlb,
				hhnbid: data.hhnbid
			};
			store.load({params:{start:0, limit:200}});
			
			var store1 = qrhdxxGrid.store;
			store1.baseParams = {
				pzlb: store1.pzlb,
				hhnbid: data.hhnbid
			};
			store1.load({
				params:{start:0, limit:200},
				callback : function(records, options, success){
					//alert(records[0].data.hlx);
					formQrhdxx.getForm().reset();
					formQrhdxx.getForm().loadRecord(records[0]);
				}
			});
			
		},
		closeWin : function(optype){
			
			formQrhdxx.getForm().reset();
			qrhdxxGrid.store.removeAll();
			
			yhcyGrid.store.removeAll();
			
		}
	});
	
	function initYw(){
		

		if(ywGrid.store.getCount() > 0){
			(function(){
				ywGrid.fireEvent("rowclick",ywGrid,0);
				ywGrid.getSelectionModel().selectRange(0,0);
				//initBdfw();
			}).defer(200);
		}
		
		zzbdStore.each(function(re){
			var data = re.data;
			
			if(rhFlag != 2){
//			if(rhFlag == 1){
//				form_yw.getForm().findField("zzbdhhb").setValue(hb);
				re.set("zzbdhhb",hb);
    		}
			else{
    			re.set("zzbdhhb","");
    		}
			if(rhFlag != 1){
				SetReadOnly(form_yw, 'zzbdhhb', false);
			}else{
				SetReadOnly(form_yw, 'zzbdhhb', true);
			}
			
			if(hzywjo){
				/**
					入户/同址分户
					默认入户户别，不允许修改
					户政业务处理没有带zzbdhhb字段
					导致zzbdhhb没值又无法修改
					
					2018.08.15
					修改	刁杰
					线上要求不带入户政平台变动后户别
					入户/同址分户默认入户户别（Line:593）
					立户可以选择变动后户别
					
					2018.08.16
					修改	刁杰
					线上要求入户/同址分户使用常口系统入户户别
					立户使用户政平台的参数
				 */
				if(rhFlag == 2){
					re.set("zzbdhhb",hzywjo.bdhhb);
				}
			}
			
			form_yw.getForm().loadRecord(re);
			
		});
		
	}
	
	var lhWindow = new Gnt.ux.SelectBzdz({
		//选择立户信息回调
		callback: function(optype, lhdz){
			new_pcs = lhdz.pcs;
			new_jcwh = lhdz.jcwh;
			new_ssxq = lhdz.ssxq;
			initData(initlist, inithxx);
			formQrlhxx.getForm().reset();
			//formQrlhxx.getForm().loadRecord(lhdz);
			formQrlhxx.getForm().findField("hlx").setValue(lhdz.hlx);
			formQrlhxx.getForm().findField("jlx").setValue(lhdz.jlx);
			formQrlhxx.getForm().findField("mlph").setValue(lhdz.mlph);
			formQrlhxx.getForm().findField("mlxz").setValue(lhdz.mlxz);
			formQrlhxx.getForm().findField("jcwh").setValue(lhdz.jcwh);
			var ssqx = lhdz.dm.substring(0,6)
			formQrlhxx.getForm().findField("ssxq").setValue(ssqx);
			formQrlhxx.getForm().findField("xzjd").setValue(lhdz.xzjddm);
			formQrlhxx.getForm().findField("pcs").setValue(lhdz.dwdm);
			formQrlhxx.getForm().findField("zrq").setValue(lhdz.zrq);
			
			initYw();
			
			Ext.getCmp('card').getLayout().setActiveItem(2);
			v.doLayout();
		}
	});
	
	var tzfhxxGrid = new Gnt.ux.SjpzGrid({
		pkname: 'hlx',
		pzlb: '10017',
		url:'yw/hjyw/zzbd/queryHxx.json',
		title: '',
		showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){
				
			}
		}
	});
	
	var p2 = new Ext.Panel({
		buttonAlign:'left',
		newYewu: function(){
			
		},
		items:[
		   	{
				title: '业务办理方式选择',
				html:'',
				region:'north',
				height:10,
				border:false,
				frame:true,
				buttonAlign:'left',
				buttons:[
							new Ext.Button({
								text:'入户方式',
								minWidth:75,
								id:'rhBtn',
								handler:function(){
									
									rhFlag = 1;
									/*
									Ext.getCmp("panelHtmlId").body.update("<div class=\"text\" style=\" text-align:center;\">入户的户地及原成员信息</div>");
									Ext.getCmp("qrhdId").setVisible(true);
									Ext.getCmp("yhcyId").setVisible(true);
									Ext.getCmp("qrlhId").setVisible(false);
									*/
									rhWindow.show();
									if(hzywjo){
										rhWindow.setHzyw(hzywjo);
									}
								}
							}),
							new Ext.Button({
								text:'立户方式',
								id:'lhBtn',
								minWidth:75,
								handler:function(){
									rhFlag = 2;
									/*
									Ext.getCmp("panelHtmlId").body.update("<div class=\"text\" style=\" text-align:center;\">立户的地址信息及户类型</div>");
									Ext.getCmp("qrhdId").setVisible(false);
									Ext.getCmp("yhcyId").setVisible(false);
									Ext.getCmp("qrlhId").setVisible(true);
									*/
									lhWindow.show();
									if(hzywjo){
										lhWindow.setHzyw(hzywjo);
										
										if(hzywjo.hlx){
											formQrlhxx.getForm().findField("hlx").setValue(hzywjo.hlx);
										}
										
									}
								}
							}),
							new Ext.Button({
								text:'同址分户',
								id:'tzfhBtn',
								minWidth:75,
								handler:function(){
									new_pcs = old_pcs;
									new_jcwh = old_jcwh;
									new_ssxq = old_ssxq;
									initData(initlist, inithxx);
									rhFlag = 3;
									/**
										需要手动将户信息复制
									 */
									var store = tzfhxxGrid.store;
									store.baseParams = {
										rynbid:rynbId,
										start:0,
										limit:200
									};
									store.load();
									
									store.on("load",function(store) {
										
										if(store.data.length > 0){
											var r = store.getAt(0);
											
											formQrlhxx.getForm().findField("hlx").setValue(r.get("hlx"));
											formQrlhxx.getForm().findField("jlx").setValue(r.get("jlx"));
											formQrlhxx.getForm().findField("mlph").setValue(r.get("mlph"));
											formQrlhxx.getForm().findField("mlxz").setValue(r.get("mlxz"));
											formQrlhxx.getForm().findField("jcwh").setValue(r.get("jcwh"));
											formQrlhxx.getForm().findField("ssxq").setValue(r.get("ssxq"));
											formQrlhxx.getForm().findField("xzjd").setValue(r.get("xzjd"));
											formQrlhxx.getForm().findField("pcs").setValue(r.get("pcs"));
											formQrlhxx.getForm().findField("zrq").setValue(r.get("zrq"));
											
											initYw();
											
											Ext.getCmp('card').getLayout().setActiveItem(2);
											v.doLayout();
										}
									});
								}
							})
				]
			},{
				html: '请选择业务办理方式！',
				region:'center',
				border:false,
				frame:false,
				bodyStyle:'padding:15px'
			}/*,{
				id: "panelHtmlId" ,
				html: '<div class="text" style=" text-align:center;">入户的户地及原成员信息</div>',
				region:'center',
				bodyStyle:'padding:15px'
			}*/,{
				id:"qrhdId",
				border:false,
				hidden:true,
	        	region:'center',
	        	height: 300,
	        	items:[
	        		formQrhdxx
	        	]
			},{
				id:"yhcyId",
				border:false,
				hidden:true,
	        	region:'south',
	        	height: 150,
	        	items:[
	        		yhcyGrid
	        	]
			}
			,{
				id:"qrlhId",
				border:false,
				hidden:true,
	        	region:'center',
	        	height: 300,
	        	items:[
	        		formQrlhxx
	        	]
			}/*,new Ext.Button({
				text:'新办理',
				disabled:hzywid?true:false,
				minWidth:100,
				width:150,
				handler:function(){
					showQuestion("数据未保存，确定放弃办理吗？", function(btnType){
						if(btnType=="yes"){
							window.location.reload();
						}
					});
				}
			})*/
		]
		,buttons:[
			{
				text:'新办理',
				disabled:hzywid?true:false,
				minWidth:100,
				width:150,
				handler:function(){
					showQuestion("数据未保存，确定放弃办理吗？", function(btnType){
						if(btnType=="yes"){
							window.location.reload();
						}
					});
				}
			}
		]
	});
	
	var ywGrid = new Gnt.ux.SjpzGrid({
		title: '住址变动人员列表',
		pkname: 'rynbid',
		pzlb: '10019',
		showPaging:false,
//		trackResetOnLoad:true,
		listeners:{
			rowclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				
				selectRynbid = selRes.data.rynbid;
    			selectHhnbid = selRes.data.hhnbid;
				
				//人员基本资料更新
				form_hcy.getForm().reset();
				form_hcy.getForm().loadRecord(selRes);
				
				//住址变动信息初始化
//				form_yw.doLayout();
				form_yw.getForm().reset();
//				zzbdStore.reload();
				/**
					全部清空，包括原始值
				 */
//				form_yw.getForm().getEl().dom.reset();
				if(selRes.data._sel=="1"){
					
					editPanelCard.getLayout().setActiveItem(0);
//					editPanelCard.doLayout();
//					v.doLayout();
	    			
//					Ext.getCmp('zzbdFormId').setVisible(true);
					
					var pkvalue = selRes.data[zzbdStore.pkname];
					var re = zzbdStore.getById(pkvalue);
					
//					re.set('rynbid', pkvalue);
					
					if(re){
						form_yw.getForm().loadRecord(re);
					}else{
						showInfo("警告：住址变动信息" + pkvalue + "不存在！");
					}
					
	    			p3.items.items[2].items.items[1].items.items[0].setVisible(true);
	    			p3.items.items[2].items.items[1].items.items[1].setVisible(true);
	    			p3.items.items[2].items.items[1].items.items[2].setVisible(true);
	    			p3.items.items[2].items.items[1].items.items[3].setVisible(true);
	    			p3.items.items[2].items.items[1].items.items[4].setVisible(true);
	    			p3.items.items[2].items.items[1].items.items[5].setVisible(true);
	    			p3.items.items[2].items.items[1].items.items[6].setVisible(true);
	    			p3.items.items[2].items.items[1].items.items[7].setVisible(true);
	    			p3.items.items[2].items.items[1].items.items[8].setVisible(true);
	    			p3.items.items[2].items.items[1].items.items[9].setVisible(true);
	    			p3.items.items[2].items.items[1].items.items[10].setVisible(true);
	    			p3.items.items[2].items.items[1].items.items[11].setVisible(true);
	    			p3.items.items[2].items.items[1].items.items[12].setVisible(true);
	    			p3.items.items[2].items.items[1].items.items[13].setVisible(true);
	    			p3.items.items[2].items.items[1].items.items[14].setVisible(true);
	    			
	    			if(checkFlag){
	    				if(rkzpMap[selRes.data.rynbid]){
	    					selRes.data.zp=rkzpMap[selRes.data.rynbid];
	    					Gnt.photo.setPhoto(null, true);
	        				Gnt.photo.setPhoto(selRes, true);
	    				}else{
	    					Gnt.photo.setPhoto(null, true);
	        				Gnt.photo.setPhoto(selRes, true);
	    				}
	    			}
	    			
				}else{
					//不是迁出人员，隐藏
					form_yw.setVisible(false);
				}
				
				initBdfw();
				//form_yw.getForm().setValues({bdfw:21});
				//alert(Gnt.ux.Dict.getDictLable('CS_BDFW', 11));	
				var validBdfw = form_yw.getForm().findField("bdfw").store.data;
				var bdfwFlag = false
				if(re&&re.data.bdfw){
					Ext.each(validBdfw.keys, function(data){
						if(data==re.data.bdfw){
							bdfwFlag = true;
							//newlist.push(data);
						}
					});
					if(!bdfwFlag){
						form_yw.getForm().setValues({bdfw:""});
						selRes.data.bdfw="";
					}else{
						form_yw.getForm().setValues({bdfw:re.data.bdfw});
					}
				}
			}
		}
	});
	
	//住址变动store
	var zzbdStore = new Gnt.store.SjpzStore({
		pzlb:'10015',
		pkname:'rynbid'
	});
	
	//典型：bindStore和bindViewGrid不一样
	var form_yw = new Gnt.ux.SjpzForm({
		title: '住址变动信息登记',
		closable: false,
		pzlb: '10015',
		labelWidth : 160,
		cols:2,
//		trackResetOnLoad:true,
		//绑定到store，输入域改动的时候，自动赋值
		bindStore: zzbdStore,
		//绑定bindViewGrid，当bindViewGrid点击的时候，触发记录移动
		bindViewGrid: ywGrid
	});
	
	var yqrhcyGrid = new Gnt.ux.SjpzGrid({
		title: '原迁入户成员列表',
		pkname: 'rynbid',
		pzlb: '10019',
    	showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){
				
//				form_yw.bindSelectRyStore = yqrhcyGrid.strore;
				
    			selRes = g.store.getAt(rowIndex);
    			curIndex = rowIndex;
    			
    			//人员基本资料更新
    			form_yqrhcy.getForm().reset();
    			form_yqrhcy.getForm().loadRecord(selRes);
				
    			editPanelCard.getLayout().setActiveItem(1);
    			form_yqrhcy.doLayout();
//    			editPanelCard.doLayout();
    			
    			p3.items.items[2].items.items[1].items.items[0].setVisible(false);
    			p3.items.items[2].items.items[1].items.items[1].setVisible(false);
    			p3.items.items[2].items.items[1].items.items[2].setVisible(false);
    			p3.items.items[2].items.items[1].items.items[3].setVisible(false);
    			p3.items.items[2].items.items[1].items.items[4].setVisible(false);
    			p3.items.items[2].items.items[1].items.items[5].setVisible(false);
    			p3.items.items[2].items.items[1].items.items[6].setVisible(true);
    			p3.items.items[2].items.items[1].items.items[7].setVisible(true);
    			p3.items.items[2].items.items[1].items.items[8].setVisible(false);
    			p3.items.items[2].items.items[1].items.items[9].setVisible(false);
    			p3.items.items[2].items.items[1].items.items[10].setVisible(false);
    			p3.items.items[2].items.items[1].items.items[11].setVisible(false);
    			p3.items.items[2].items.items[1].items.items[12].setVisible(false);
    			p3.items.items[2].items.items[1].items.items[13].setVisible(false);
    			p3.items.items[2].items.items[1].items.items[14].setVisible(false);
    			
    			Gnt.photo.setPhoto(selRes, false);
    			
    		}
		}
	});
	var form_yqrhcy = new Gnt.ux.SjpzForm({
		title: '户成员信息',
		closable: false,
		/*region:'north',
		height:330,*/
		pzlb: '10019',
		labelWidth :  120,
		cols:2,
		bindViewGrid:yqrhcyGrid,
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
				Gnt.validpoxb(form_yqrhcy,selRes,res,true);
				//add by zjm 20190710 配偶下拉框选中人员后，配偶身份证自动带入
				this.getForm().setValues({pogmsfhm:res.data.data});
				selRes.set("pogmsfhm",res.data.data);
			}
			
			return;
		},
		getSelectRy:function(){
			/**
				2018.07.20
				新增	刁杰
				根据选择的办理方式申报人来源变动
				入户：原户成员列表
				立户：出生登记信息
			 */
			var rylist = new Array();
			var store = allStore;
			/*
			var store = null;
			if(rhFlag != 1){
				store = ywGrid.store;
			}else{
				store = allStore;
				*//**
					2018.11.02
					新增	刁杰
					将原迁入户成员加入到申请人列表中
					不过有些缺陷
				store.add(yqrhcyGrid.store.getRange(0,yqrhcyGrid.store.getCount()));
				 *//*
			}
			*/
			if(store!=null){
				store.each(
					function(rec){
						if(rec.data && rec.data.xm && rec.data.xm!=""){
							var len = rylist.length;
							
							rylist[len] = new Array();
							rylist[len][0] = rec.data.xm;
							rylist[len][1] = rec.data.xm;
							rylist[len][2] = rec.data.gmsfhm;
						}
					},	this.bindViewGrid
				);
			}
		
			return rylist;
		},
		fieldBlur:function(field){
        	var f = field.findParentByType("sjpz_form");
        	var r = yqrhcyGrid.getSelectionModel().getSelected();
        	
        	if(field.name=='hyzk' && r.isModified("hyzk")){
        		Gnt.validpo(form_yqrhcy,field);
        	}
        	
        	if(field.name=='pogmsfhm' && r.isModified("pogmsfhm")){
        		Gnt.validposfzh(form_yqrhcy,field,true);
        	}
    		
        }
		,fieldFocus:function(field){
			Gnt.filterField(field);
		}
	});
	
	var yqchcyGrid = new Gnt.ux.SjpzGrid({
		title: '原迁出户成员列表',
		pkname: 'rynbid',
		pzlb: '10019',
		showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){
				
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				
				//人员基本资料更新
				form_yqchcy.getForm().reset();
				form_yqchcy.getForm().loadRecord(selRes);
    			
				editPanelCard.getLayout().setActiveItem(2);
				form_yqchcy.doLayout();
//    			editPanelCard.doLayout();
    			
    			p3.items.items[2].items.items[1].items.items[0].setVisible(false);
    			p3.items.items[2].items.items[1].items.items[1].setVisible(false);
    			p3.items.items[2].items.items[1].items.items[2].setVisible(false);
    			p3.items.items[2].items.items[1].items.items[3].setVisible(false);
    			p3.items.items[2].items.items[1].items.items[4].setVisible(false);
    			p3.items.items[2].items.items[1].items.items[5].setVisible(false);
    			p3.items.items[2].items.items[1].items.items[6].setVisible(true);
    			p3.items.items[2].items.items[1].items.items[7].setVisible(true);
    			p3.items.items[2].items.items[1].items.items[8].setVisible(false);
    			p3.items.items[2].items.items[1].items.items[9].setVisible(false);
    			p3.items.items[2].items.items[1].items.items[10].setVisible(false);
    			p3.items.items[2].items.items[1].items.items[11].setVisible(false);
    			p3.items.items[2].items.items[1].items.items[12].setVisible(false);
    			p3.items.items[2].items.items[1].items.items[13].setVisible(false);
    			p3.items.items[2].items.items[1].items.items[14].setVisible(false);
    			
    			Gnt.photo.setPhoto(selRes, false);
    			
			}
		}
	});
	
	var form_yqchcy = new Gnt.ux.SjpzForm({
		title: '户成员信息',
		closable: false,
		/*region:'north',
		height:330,*/
		pzlb: '10019',
		labelWidth :  120,
		cols:2,
		bindViewGrid:yqchcyGrid,
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
				Gnt.validpoxb(form_yqchcy,selRes,res,true);
			}
			
			return;
		},
		getSelectRy:function(){
			/**
				2018.07.20
				新增	刁杰
				根据选择的办理方式申报人来源变动
				入户：原户成员列表
				立户：出生登记信息
			 */
			var rylist = new Array();
			var store = allStore;
			/*
			var store = null;
			if(rhFlag != 1){
				store = ywGrid.store;
			}else{
				store = allStore;
				*//**
					2018.11.02
					新增	刁杰
					将原迁入户成员加入到申请人列表中
					不过有些缺陷
				store.add(yqrhcyGrid.store.getRange(0,yqrhcyGrid.store.getCount()));
				 *//*
			}
			*/
			if(store!=null){
				store.each(
					function(rec){
						if(rec.data && rec.data.xm && rec.data.xm!=""){
							var len = rylist.length;
							
							rylist[len] = new Array();
							rylist[len][0] = rec.data.xm;
							rylist[len][1] = rec.data.xm;
							rylist[len][2] = rec.data.gmsfhm;
						}
					},	this.bindViewGrid
				);
			}
		
			return rylist;
		},
		fieldBlur:function(field){
        	var f = field.findParentByType("sjpz_form");
        	var r = yqchcyGrid.getSelectionModel().getSelected();
        	
        	if(field.name=='hyzk' && r.isModified("hyzk")){
        		Gnt.validpo(form_yqchcy,field);
        	}
        	
        	if(field.name=='pogmsfhm' && r.isModified("pogmsfhm")){
        		Gnt.validposfzh(form_yqchcy,field,true);
        	}
    		
        }
		,fieldFocus:function(field){
			Gnt.filterField(field);
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
//		bindViewGridHCY:yqchcyGrid,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:ywGrid,
		getSelectRy:function(){
			/**
				2018.07.20
				新增	刁杰
				根据选择的办理方式申报人来源变动
				入户：原户成员列表
				立户：出生登记信息
			 */
			var rylist = new Array();
			var store = allStore;
			/*
			var store = null;
			if(rhFlag != 1){
				store = ywGrid.store;
			}else{
				store = allStore;
				*//**
					2018.11.02
					新增	刁杰
					将原迁入户成员加入到申请人列表中
					不过有些缺陷
				store.add(yqrhcyGrid.store.getRange(0,yqrhcyGrid.store.getCount()));
				 *//*
			}
			*/
			if(store!=null){
				store.each(
					function(rec){
						if(rec.data && rec.data.xm && rec.data.xm!=""){
							var len = rylist.length;
							
							rylist[len] = new Array();
							rylist[len][0] = rec.data.xm;
							rylist[len][1] = rec.data.xm;
							rylist[len][2] = rec.data.gmsfhm;
						}
					},	this.bindViewGrid
				);
			}
		
			return rylist;
		},
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
				//add by zjm 20190805 配偶下拉框选中人员后，配偶身份证自动带入
				this.getForm().setValues({pogmsfhm:res.data.data});
				selRes.set("pogmsfhm",res.data.data);
			}
			
			return;
		},
		fieldBlur:function(field){
        	var f = field.findParentByType("sjpz_form");
        	var r = ywGrid.getSelectionModel().getSelected();
        	
        	if(field.name=='hyzk' && r.isModified("hyzk")){
        		Gnt.validpo(form_hcy,field);
        	}
        	
        	if(field.name=='pogmsfhm' && r.isModified("pogmsfhm")){
        		Gnt.validposfzh(form_hcy,field,true);
        	}
    		
        }
		,fieldFocus:function(field){
			Gnt.filterField(field);
		}
	});
	
	var form_sbr = new Gnt.ux.SjpzForm({
		title: '申报人信息',
		closable: false,
		/*region:'north',
		height:60,*/
		pzlb: '10002',
		labelWidth :  120,
		cols:2,
		getSelectRy:function(){
			/**
				2018.07.20
				新增	刁杰
				根据选择的办理方式申报人来源变动
				入户：原户成员列表
				立户：出生登记信息
			 */
			var rylist = new Array();
			var store = allStore;
			/*
			var store = null;
			if(rhFlag != 1){
				store = ywGrid.store;
			}else{
				store = allStore;
				*//**
					2018.11.02
					新增	刁杰
					将原迁入户成员加入到申请人列表中
					不过有些缺陷
				store.add(yqrhcyGrid.store.getRange(0,yqrhcyGrid.store.getCount()));
				 *//*
			}
			*/
			if(store!=null){
				store.each(
					function(rec){
						if(rec.data && rec.data.xm && rec.data.xm!=""){
							var len = rylist.length;
							
							rylist[len] = new Array();
							rylist[len][0] = rec.data.xm;
							rylist[len][1] = rec.data.xm;
							rylist[len][2] = rec.data.gmsfhm;
						}
					},	this.bindViewGrid
				);
			}
		
			return rylist;
		},
		changeDictCust:function(cmb,res){
			//当选择人后，为其它域赋值
			this.getForm().setValues({sbrgmsfhm:res.data.data});
			return;
		}
	});
	
	var editPanelCard = new Ext.Panel({
		id:'editPanelCard',
    	layout:'card',
    	xtype: 'panel',
    	region:'center',
    	border:false,
    	frame:false,
    	activeItem: 0,
    	items:[{
	    		id:'c1',
		        region:'center',
		        //禁止横向滚动条
		        layout:'border',
		        border:false,
		        frame:false,
		        items:[form_hcy,{
		        	 layout:'border',
		        	 region:'south',
		        	 height:230,
		             border:false,
		             frame:false,
		             items:[form_sbr,{
		            	id:'zzbdFormId',
		     	        region:'south',
		    	        //禁止横向滚动条
		    	        layout:'border',
		    	        height:170,
		    	        border:false,
		    	        frame:false,
		    	        items:[form_yw]
		    	    }]
		        }]
		    },{
	    	   region:'center',
	    	   layout: 'border',
	    	   id: 'c2',
	    	   items:[
	    		   form_yqrhcy
	    	   ]
	       },{
	    	   region:'center',
	    	   layout: 'border',
	    	   id: 'c3',
	    	   items:[
	    		   form_yqchcy
	    	   ]
	       }]
    });
	
	var p3 = new Ext.Panel({
		layout:'border',
		items:[{
			id:'westId',
			region:'west',
			layout: 'accordion',
			width: 200, 
			minWidth: 0,
			split: true,		//是否可用拖动(宽度)
            collapsible: true,
//	        border:false,
//	        frame:false,
	        items:[ywGrid,yqrhcyGrid,yqchcyGrid]
	    },editPanelCard,{
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
				    	    },{
				    	    	xtype: 'DkButton',
				    	    	form:form_yw,
				    	    	minWidth:100,
				    	    	callback: function(){
				    	    		//Ext.getCmp("queryId").handler();
				    	    	}
				    	    },{
				    	    	height:3,
				    	    	border:false,
				    	    	frame:false
				    	    },new Ext.Button({
								text:'详细信息',
								minWidth:100,
								handler:function(){
									czr={
											ryid:null,
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
								text:'同第一人',
								minWidth:100,
								handler:function(){
									
									Gnt.sameFirst(zzbdStore,ywGrid,form_yw);
									
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
									ywGrid.fireEvent("rowclick",ywGrid,curIndex);
									ywGrid.getSelectionModel().selectRange(curIndex,curIndex);
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
									ywGrid.fireEvent("rowclick",ywGrid,curIndex);
									ywGrid.getSelectionModel().selectRange(curIndex,curIndex);
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
								text:'新办理',
								disabled:hzywid?true:false,
								minWidth:100,
								handler:function(){
									showQuestion("数据未保存，确定放弃办理吗？", function(btnType){
										if(btnType=="yes"){
											/*
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
				    	    }),{
				    	    	height:3,
				    	    	border:false,
				    	    	frame:false
				    	    },new Ext.Button({
								text:'返回',
								minWidth:100,
								handler:function(){
									
									/**
										需要清空入户/立户/同址分户的信息
									 */
									rhWindow.resetData();
									lhWindow.resetData();
									
									selectRynbid = null;
									selectHhnbid = null;
									
									qrhdxxGrid.store.removeAll();
									hxxGrid.store.removeAll();
									qrlhxxGrid.store.removeAll();
									yhcyGrid.store.removeAll();
									tzfhxxGrid.store.removeAll();
									
									Ext.getCmp('card').getLayout().setActiveItem(1);
//									v.doLayout();
									
								}
				    	    })
			    	 ]
	    	    }
	    	 ]
	    }]
	});
	
	var p4 = new Ext.Panel({
		layout: 'border',
		frame:false,
		borde:false,
		items:[
				{
					   region:'north',
					   height:100,
					   html: '',
					   frame:false,
					   borde:false,
			    	   html: '<center><br/><br/><h2>住址变动业务办理完成！</h2></center>'
				}, {
			    	   region:'center',
			    	   title:'',
			    	   frame:false,
					   borde:false
			    },{
					   region:'south',
					   height:100,
					   html: '',
					   frame:false,
					   borde:false,
					   layout:'table',
					   items:[{
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
		   								
		   								url += "id=" +  selRes.data.hjywid;
		   								url += "&ywcode=103&ryid=" +  selRes.data.ryid;
		   								url += "&sfz=" +  selRes.data.gmsfhm;
		   								url += "&sbrsfzh=" +  selRes.data.sbrgmsfhm;
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
			    			},{
				    	    	xtype: 'CjdaButton',
				    	    	form: form_yw,
				    	    	disabled:true,
				    	    	ywcode:(rhFlag=='1'||rhFlag=='2')?'103':'104'
				    	    },{
								frame:false,
								border:false,
								xtype:'panel',
								html:'',
								width:10
			    			},new Ext.Button({
								text:'添加户关联关系',
								minWidth:100,
								width:150,
								handler:function(){
									
									var url = basePath + "yw/hjyw/hglgxdj?glhhnbid=" + selectGlHhnbid ;
									if(rhFlag != 2){
										url += "&bglhhnbid=" + selectHhnbid
									}
									
									if(parent && parent.openWorkSpace){
			    						parent.openWorkSpace(url,  "户关联关系登记", "hglgxdj");
			    					}else{
			    						window.location.href = url;
			    					}
									/*
									var tabs = parent.Ext.getCmp("tabs");
									var p = null;
								   	 //利用iframe显示页面
								   	 p = new parent.Ext.ux.IFrameComponent({
								   	 	xtype:'panel',
								   	 	id:"ckxxcx",
								   	 	url:url,
								   	 	frame:false,
								   	 	iconCls:'otherfile',
									   	title: "户关联关系登记"
									});
								   	//添加分页，并且设置为活动分页
								    tabs.add(p);
								    
								    tabs.setActiveTab("ckxxcx");
									*/
								}
		    	            }),{
								frame:false,
								border:false,
								xtype:'panel',
								html:'',
								width:10
			    			},new Ext.Button({
								text:'迁入户常表户口打印',
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
								text:'住址追加',
								minWidth:100,
								disabled:true,
								width:150,
								handler:function(){
									
								}
		    	            })
		    	       ]
			       }
		],buttons:[
	           new Ext.Button({
					text:'新办理',
					minWidth:100,
					width:150,
					disabled:hzywid?true:false,
					handler:function(){
						window.location.reload();
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
	});
	
	var bggzGrid = new Gnt.ux.BggzGrid({
		title:''
	});
	var bggzWin = new Ext.Window({
		title:'确认变更更正项目',
		closeAction:'hide',
		modal:true,
		width:600,
		height:300,
		margins:'5',
		layout:'fit',
		items:bggzGrid,
		buttons:[
					new Ext.Button({
							text:'确定',
							minWidth:75,
							handler:function(){
								submitData();
							}
					}),
					new Ext.Button({
						text:'取消',
						minWidth:75,
						handler:function(){
							bggzWin.hide();
						}
				})
			]
	});
	
	function save(){
			if(!form_hcy.checkInput(false, "4", false, ywGrid)){
				return;
			}
			if(!form_sbr.getForm().isValid()){
				showErr("申报人信息必须填写！");
				return;
			}
			if(!form_yw.getForm().isValid()){
				showErr("住址变动登记信息必须填写！");
				return;
			}
//			if(!form_yw.checkInput(false, "7", false)){
//				return;
//			}
			if(!form_yw.checkInput(false, "1", false, ywGrid)){
				return;
			}
			
			if(!Gnt.validHz(8,form_yqrhcy,form_hcy)){
				return ;
			}
			
			/**
				入户时应判断原迁入户成员及原迁出户成员户主是否
			 */
			var exists = false;
			var icount = 0;
			var hcycount = 0;
			if(rhFlag == 1){
				/**
					判断住址变动人员与原迁入户成员是否多个户主
				 */
				yqrhcyGrid.store.each(function(r){
					var data = r.data;
					
					hcycount ++;
					if(data.yhzgx=="01" || data.yhzgx=="02" || data.yhzgx=="03"){
						icount++;
						exists = true;
					}
					
				});
				
				ywGrid.store.each(function(r){
					var data = r.data;
					
					hcycount ++;
					if(data.yhzgx=="01" || data.yhzgx=="02" || data.yhzgx=="03"){
						icount++;
						exists = true;
					}
				});
				
				if(!exists){
					showErr("原迁入户成员户主已迁出，请选择新户主！");
					
					Ext.getCmp('westId').items.items[1].expand();
					
					return;
				}else{
					if(icount > 1){
						showErr("原迁入户存在多个户主，请选择新户主！");
						
						Ext.getCmp('westId').items.items[0].expand();
						
						return;
					}else{
						exists = false;
						icount = 0;
						hcycount = 0;
					}
				}
				
			}else{
				/**
					判断新户中是否有户主
				 */
				var cnFlag = true;
				ywGrid.store.each(function(r){
					var data = r.data;
					
					icount++;
					if(data.yhzgx=="01" || data.yhzgx=="02" || data.yhzgx=="03"){
						exists = true;
						
						/**
							立户时,户主未满18周岁
							提示’户主未满18周岁,是否继续保存?’
						 */
						if(rhFlag == 2){
							var nl = Gnt.date.getNl(data.gmsfhm, data.csrq);
							if(nl < 18){
								if(window.confirm("户主未满18周岁,是否继续保存?")){
									cnFlag = true ;
								}else{
									cnFlag = false ;
								}
							}
						}
						
					}
				});
				
				if(!cnFlag){
					return ;
				}
				
				if(!exists && icount > 0){
					showErr("新户没有户主，请选择户主！");
					
					Ext.getCmp('westId').items.items[0].expand();
					
					return;
				}else{
					exists = false;
					icount = 0;
				}
			}
			
			/**
				判断原迁出户成员是否户主迁出
			 */
			yqchcyGrid.store.each(function(r){
				var data = r.data;
				
				if(data._sel=="1"){
					//迁出成员
				}else{
					hcycount ++;
					if(data.yhzgx=="01" || data.yhzgx=="02" || data.yhzgx=="03"){
						icount++;
						exists = true;
					}
				}
				
			});
			
			if(!exists){
				if(hcycount > 0){
					showErr("原迁出户成员户主已迁出，请选择新户主！");
					
					Ext.getCmp('westId').items.items[2].expand();
					
					return;
				}
			}else{
				if(icount > 1){
					showErr("原迁出户存在多个户主，请选择新户主！");
					
					Ext.getCmp('westId').items.items[2].expand();
					
					return;
				}
			}
			
			/**
				住址变动暂时没办法使用Gnt.ux.BggzDialog封装的方法
			 */
			var store = bggzGrid.store;
			store.removeAll();
//			if(!bgValig){//modify by zjm 20190726 如果中间平台数据改过ywgrid数据，同步影响了yqchcygrid
//				Gnt.yw.initBggzStore(ywGrid, store, false);
//			}
			Gnt.yw.initBggzStore(ywGrid, store, true,null,ywGrid.pzlb);
			Gnt.yw.initBggzStore(yqchcyGrid, store , false,null,yqchcyGrid.pzlb);
			Gnt.yw.initBggzStore(yqrhcyGrid, store, false,null,yqrhcyGrid.pzlb);
			/*
			var rs1 = ywGrid.store.getModifiedRecords();
			if(rs1 != null && rs1.length>0){
//				str = "";
				for(var i=0;i<rs1.length;i++){
					var obj = rs1[i].getChanges();
					//str += "记录ID:" + rs[i].id + ":<BR>";
					//"tmpid", "rynbid","xm","bggzxm_mc","bggzxm", "bggzqnr","bggzhnr","bggzlb"
					for(f in obj){
						if(rs1[i].modified[f] != obj[f]){
							var data = {
									rynbid: rs1[i].data.rynbid,
									xm: rs1[i].data.xm,
									bggzxm:f,
									bggzxm_mc: form_hcy.getFieldLabel(f),
									bggzqnr: rs1[i].modified[f],
									bggzhnr:obj[f],
									bggzlb:Gnt.yw.getBggzlb(f)
							};
							var r = new store.reader.recordType(data);
							store.add([r]);
						}
					}
				}
			}
			*/
			
			
			/*
			var rs2 = yqrhcyGrid.store.getModifiedRecords();
			if(rs2 != null && rs2.length>0){
//				str = "";
				for(var i=0;i<rs2.length;i++){
					var obj = rs2[i].getChanges();
					for(f in obj){
						if(rs1[i].modified[f] != obj[f]){
							var data = {
									rynbid: rs2[i].data.rynbid,
									xm: rs2[i].data.xm,
									bggzxm:f,
									bggzxm_mc: form_yqrhcy.getFieldLabel(f),
									bggzqnr: rs2[i].modified[f],
									bggzhnr:obj[f],
									bggzlb:Gnt.yw.getBggzlb(f)
							};
							var r = new store.reader.recordType(data);
							store.add([r]);
						}
					}
				}
			}
			*/
			
			/*
			var rs3 = yqchcyGrid.store.getModifiedRecords();
			if(rs3 != null && rs3.length>0){
					for(var i=0;i<rs3.length;i++){
						var obj = rs3[i].getChanges();
						for(f in obj){
							if(rs1[i].modified[f] != obj[f]){
								var data = {
										rynbid: rs3[i].data.rynbid,
										xm: rs3[i].data.xm,
										bggzxm:f,
										bggzxm_mc: form_yqchcy.getFieldLabel(f),
										bggzqnr: rs3[i].modified[f],
										bggzhnr:obj[f],
										bggzlb:Gnt.yw.getBggzlb(f)
								};
								var r = new store.reader.recordType(data);
								store.add([r]);
							}
						}
					}
			}
			*/
			if(store.getCount()>0){
				bggzWin.show();
			}else{
				submitData();
			}
			
			return;
			
	}
	
	//提交数据
	function submitData(){
		var subdata = {
			lhhdxx:{},
			rhhdxx:{},
			bggzxx:new Array(),
			sbjbxx:{},
			ryList:new Array(),
			zzbdxx:new Array()
		};
		
		//人员内部id和上笔户籍业务的关系
		var map = {};
		var mapYhzgx = {};
		ywGrid.store.each(
			function(rec){
				map[rec.data.rynbid] = rec.data.cjhjywid;
				mapYhzgx[rec.data.rynbid] = rec.data.yhzgx;
			},	ywGrid
		);
		yqrhcyGrid.store.each(
			function(rec){
				map[rec.data.rynbid] = rec.data.cjhjywid;
			},	yqrhcyGrid
		);
		yqchcyGrid.store.each(
			function(rec){
				map[rec.data.rynbid] = rec.data.cjhjywid;
			},	yqchcyGrid
		);
		
		//变更更正信息
		var bggzmap = {};
		var store = bggzGrid.store;
		if(store.getCount()>0){
			for(var index=0;index<store.getCount();index++){
				var bggzdata = store.getAt(index).data;
				bggzdata.sbhjywid = map[bggzdata.rynbid];
				bggzdata.flag='1';

				/**
					变更更正前/后内容为空转化时报错
				 */
				if(!bggzdata.bggzqnr){
//					alert("没有变更更正前内容");
					bggzdata.bggzqnr = null;
				}
				if(!bggzdata.bggzhnr){
//					alert("没有变更更正后内容");
					bggzdata.bggzhnr = null;
				}
				
				if(!bggzmap[bggzdata.rynbid]){
					bggzmap[bggzdata.rynbid] = {
						rynbid: bggzdata.rynbid,
						sbhjywid: bggzdata.sbhjywid,
						bggzxxList:new Array()
					}
				}
				bggzmap[bggzdata.rynbid].bggzxxList.push(bggzdata);
			}
			
			for(rynbid in bggzmap){
				subdata.bggzxx.push(bggzmap[rynbid]);
			}
		}
		
		//申报人信息
		subdata.sbjbxx = form_sbr.getForm().getValues();
		if(hzywjo){
			subdata.sbjbxx.hzywid = hzywjo.id;
		}
		
		//迁入立户信息
		subdata.lhhdxx = formQrlhxx.getForm().getValues();
		
		subdata.lhhdxx.ssxq = formQrlhxx.getForm().findField("ssxq").getValue();
		subdata.lhhdxx.xzjd = formQrlhxx.getForm().findField("xzjd").getValue();
		subdata.lhhdxx.pcs = formQrlhxx.getForm().findField("pcs").getValue();
		subdata.lhhdxx.zrq = formQrlhxx.getForm().findField("zrq").getValue();
		if(subdata.lhhdxx){
			lhHlx = subdata.lhhdxx.hlx;
		}
		//迁入户地信息
		subdata.rhhdxx = formQrhdxx.getForm().getValues();
		
		//住址变动信息
		for(var index=0;index<zzbdStore.getCount();index++){
			var data = zzbdStore.getAt(index).data;
			data.sbhjywid = map[data.rynbid];
			data.yhzgx = mapYhzgx[data.rynbid];
			if(lhHlx){//add by zjm 20200312 增加户类型和户别匹配验证
				if((lhHlx==1&&data.zzbdhhb==7)||(lhHlx==2&&data.zzbdhhb==6)){
					alert("户类型和户别不匹配!");
					return;
				}
			}
			subdata.zzbdxx.push(data);
		}
		
		//关键，户政业务ID
		if(hzywjo){
			subdata.sbjbxx.hzywid = hzywjo.id;
		}
		
		subdata.sbjbxx = Ext.encode(subdata.sbjbxx);
		subdata.lhhdxx = Ext.encode(subdata.lhhdxx);
		subdata.rhhdxx = Ext.encode(subdata.rhhdxx);
		subdata.bggzxx = Ext.encode(subdata.bggzxx);
		subdata.zzbdxx = Ext.encode(subdata.zzbdxx);
		
		///yw/hjyw/qczx
		log_code = "F1106";
		Gnt.submit(
			subdata, 
			"yw/hjyw/zzbd/processZzbdyw", 
			"正在处理住址变动信息，请稍后...", 
			function(jsonData,params){
				showInfo("住址变动成功！",function(){
					
					Ext.getCmp('card').getLayout().setActiveItem(3);
//					Ext.getCmp('vp').doLayout();
					selectRynbid="";
					if(jsonData && jsonData.list[0] && jsonData.list[0].voZzbdfhxx){
						for(var i=0; i < jsonData.list[0].voZzbdfhxx.length; i++){
							selectRynbid += jsonData.list[0].voZzbdfhxx[i].rynbid + ",";
						}
						
						if(selectRynbid.length > 0){
							selectRynbid = selectRynbid.substr(0,selectRynbid.length - 1);
						}
						selectHhnbid = jsonData.list[0].hhnbid;
						selectHjywid = jsonData.list[0].hjywid;
						if(hzywjo&&hzywjo.id){
							Gnt.ux.Dict.getKzcs('10036', function(pz, user) {
								if(pz.kzz==1){
									pjq('ES',user.ip,hzywjo.id);
								}
							});
						}
						printRk(3,selectHhnbid,selectRynbid,[true,true,true,true,true],[true,true,false,false,false],[true,true,true,true,true]);
					}
					
					if(bggzWin.isVisible())
						bggzWin.hide();
					
					//关闭当前窗口
					if(parent.closeActiveWorkSpace)
						parent.closeActiveWorkSpace(jsonData);
					
				});
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
        	items:[p1,p2,p3,p4]
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
	
	//市区配置信息
	var xt_bssqb_list = null;
	Gnt.yw.queryHbbgsp({
		callback:function(json){
			if(json.list && json.list.length>0){
				xt_bssqb_list = json.list;
			}
		}
	});
	
	//初始化变动范围，需要数据：old_jcwh, old_ssxq, new_jcwh, new_ssxq
	function initBdfw(){
		if(xt_bssqb_list==null){
			showInfo("没有找到市区配置信息，无法过滤变动范围！");
			return;
		}
		
		var f = form_yw.getForm().findField("bdfw");
		if(f){
			var fwflag = true;
			Ext.each(xt_bssqb_list, function(data){
				if(data.qhdm==old_ssxq || data.qhdm==new_ssxq){
					if(data.sqbz=='0')
						fwflag = false;
				}
			});
			
			f.filter =function(data){
				//1、	变动前后区县内同一个居委会(jcwh)变动范围是【11-15】包括11和15。
//				if(new_jcwh==old_jcwh){
				if(new_pcs==old_pcs){
					if(data[0]=='11' || data[0]=='12'  || data[0]=='13' || data[0]=='14'  || data[0]=='15')
						return true;
					else
						return false;
				}
				//2、	变动前后在同一个区县内但是不同的居委会（jcwh）是 21 区(县)内他所(乡) 
				if(old_ssxq==new_ssxq && new_pcs!=old_pcs){
					if(data[0]=='21')
						return true;
					else
						return false;
				}
				
				if(fwflag){
					//sqbz(市区标志)都为1的情况下变动范围是31市内城镇32	市内郊区，否则为 33	市外乡村34市外城镇
					if(data[0]=='31' || data[0]=='32')
						return true;
					else
						return false;
				}else{
					if(data[0]=='33' || data[0]=='34')
						return true;
					else
						return false;
				}

				return false;
			};
			f.reloadDict();
		}
	}
	function setBdfw(bdfw){
		if(xt_bssqb_list==null){
			showInfo("没有找到市区配置信息，无法过滤变动范围！");
			return '';
		}
		var fwflag = true;
		Ext.each(xt_bssqb_list, function(data){
			if(data.qhdm==old_ssxq || data.qhdm==new_ssxq){
				if(data.sqbz=='0')
					fwflag = false;
			}
		});
		//1、	变动前后区县内同一个居委会(jcwh)变动范围是【11-15】包括11和15。
//		if(new_jcwh==old_jcwh){
		if(new_pcs==old_pcs){
			if(bdfw=='11' || bdfw=='12'  || bdfw=='13' || bdfw=='14'  || bdfw=='15')
				return bdfw;
			else
				return '';
		}
		//2、	变动前后在同一个区县内但是不同的居委会（jcwh）是 21 区(县)内他所(乡) 
		if(old_ssxq==new_ssxq && new_pcs!=old_pcs){
			if(bdfw=='21')
				return bdfw;
			else
				return '';
		}
		
		if(fwflag){
			//sqbz(市区标志)都为1的情况下变动范围是31市内城镇32	市内郊区，否则为 33	市外乡村34市外城镇
			if(bdfw=='31' || bdfw=='32')
				return bdfw;
			else
				return '';
		}else{
			if(bdfw=='33' || bdfw=='34')
				return bdfw;
			else
				return '';
		}

	
	}
});
