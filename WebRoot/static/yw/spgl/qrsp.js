var selRes = null;
var curIndex = -1;
var spywid = null;
var ywid = 0;
var FRhHb  = null;
var Hhnbid = null;
var zqrRynbid = null;
var select_lhdz = null;
var select_optype_index = -1;
var returnObj = null;
var form_sbr = null;//业务表单
var form_yw = null;//申报人表单
var ywGrid = null;
var hcyGrid = null;//户成员表单
var bggzWin = null;//变更更正对话框
var select_lhdz = null;
var select_optype_index = -1;
var form_hcy = null;
var spcl = null;
var hzywjo = null; //户政业务
var hzywjo_list = null; //户主业务同批次
var selectSpywid = null;
var qsry_win = null;
var kds_jo = null;
var rhFlag = false;
var hb = null;
var newHzywid = null;
var lhHlx = null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	var hzywid = getQueryParam("hzywid");
	var pch = getQueryParam("pch");
	
	//本业务需要加载的配置
	//10030 Hj_迁入审批信息
	//10031 Hj_迁入审批子信息
	//20016 Qry_户成员信息
	//10019 Hj_户成员信息
	//10022 Hj_户地信息
	//10017 Hj_地信息
	//20017 Qry_人口基本信息查询
	//10032 Hj_户籍补录审批登记信息
	if(!Gnt.loadSjpzb("10030,10031,20016,10019,10022,10017,20017,10032",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	
	function initYw(){
			spcl = null;
			select_optype_index = -1;
			select_lhdz = null;
			selRes = null;
			curIndex = -1;
			spywid = null;
			ywid = 0;
			FRhHb  = null;
			Hhnbid = null;
			returnObj = null;
			form_yw.getForm().reset();
			hcyGrid.store.removeAll();
			yw_store.removeAll();
			
			//户地信息
			wsCdsdxx.removeAll();
			
			if(qsry_win!=null){
				qsry_win.resetData();
			}
	}
	
	var sqrWindow = null;
	
	function getUpdateDxx(hdxx,type){
		var data = {
				spywid: "-1",
				qrdhhid: hdxx.hhnbid,
				qrdhlx: hdxx.hlx,
				qrdqx: hdxx.ssxq,
				qrdpcs: hdxx.pcs,
				qrdxzjd: hdxx.xzjd,
				qrdjwh: hdxx.jcwh,
				qrdjlx: hdxx.jlx,
				qrdmlph: hdxx.mlph,
				qrdz: hdxx.mlxz,
				qrdhkdjjg: hdxx.pcs
//				,sqrzzssxq: hdxx.ssxq
		};
		
		/**
			主迁人,并没有使用'是否主迁人(sfzqr)'标识判断
			commFrames.js L:165
			户政业务过来的数据暂时没有sfzqr,选择的数据为主迁人
		 */
		if(hzywjo){
			if(hzywjo.qcdssxq.startWith(sysQcdq)){
				/**
					姓名，、性别、民族、出生日期、迁入前户别、迁入后户别（入户的话默认和原户成员一致）户口所在区县
					不带值
				 */
//				data.xm = hzywjo.bsqrxm;
//				data.xb = hzywjo.bsqrxb;
//				data.csrq = hzywjo.bsqrcsrq;
//				data.hb = hzywjo.qrqhb;
//				data.zzssxq = hzywjo.hkszqx;
				data.gmsfhm = hzywjo.bsqrsfz;
				/**
					2019.03.04
					修改	刁杰
					改成中间表dhhm有值,就取中间表的dhhm,没值就取常口dhhm
				 */
				data.lxdh = hzywjo.dhhm;
				data.sqqrly = hzywjo.qrlb;
				data.whcd = hzywjo.whcd;
//				data.ysqrgx = hzywjo.btkrgx;
				
				//20190312 省内带入五个申请人的值 
				data.sqrxm = hzywjo.sqrxm;
				data.sqrgmsfhm = hzywjo.sqrsfz;
				data.sqrzzssxq = hzywjo.sqrzzssxq;
				data.sqrzzxz = hzywjo.sqrlxdz;
				data.sqrhkdjjg = hzywjo.sqrhkdjjg;
				//add by zjm 20190729 中间表带值
				if(type&&type=="lh"){
					data.sqrxm = hzywjo.sqrxm;
					data.sqrgmsfhm = hzywjo.sqrsfz;
					data.sqrzzssxq = hzywjo.sqrzzssxq;
					data.sqrzzxz = hzywjo.sqrlxdz;
					data.sqrhkdjjg = hzywjo.sqrhkdjjg;
				}
				data.qrqhb = hzywjo.qrqhb;
				Ext.getCmp("all_qsrk_btn").handler();
			}else{
				/**
					省外
					主迁人
				 */
				data.xm = hzywjo.bsqrxm;
//				data.xb = hzywjo.bsqrxb;
//				data.csrq = hzywjo.bsqrcsrq;
				// 
				data.xb = hzywjo.xb;
				data.csrq = hzywjo.csrq;
				data.mz = hzywjo.mz;
				data.sqrzzxz = hzywjo.sqrlxdz;
				data.hb = hzywjo.qrqhb;
//				data.zzssxq = hzywjo.hkszqx;
				data.gmsfhm = hzywjo.bsqrsfz;
				data.lxdh = hzywjo.dhhm;
				data.sqqrly = hzywjo.qrlb;
				data.whcd = hzywjo.whcd;
				data.ysqrgx = hzywjo.btkrgx;
				if(type&&type=="lh"){
					data.sqrxm = hzywjo.sqrxm;
					data.sqrsfz = hzywjo.sqrsfz;
					data.sqrzzssxq = hzywjo.sqrzzssxq;
					data.sqrlxdz = hzywjo.sqrlxdz;
					data.sqrhkdjjg = hzywjo.sqrhkdjjg;
				}
			}
			//add by zjm 20190228 迁入登记业务需要带值中间表带值  省内省外都要
			data.splx = hzywjo.splx;
			data.zzssxq = hzywjo.hkszqx;
			//data.hkszqx = hzywjo.hkszqx;
			//add by zjm 20190719 中间表带入hkdjjg字段
			if(!Gnt.util.isEmpty(hzywjo.hkdjjg)){
				data.hkszddjjg = hzywjo.hkdjjg;
			}
			if(!Gnt.util.isEmpty(hzywjo.nyzyrklhczyydm)){
				data.nyzyrklhczyydm = hzywjo.nyzyrklhczyydm;
			}
			if(!Gnt.util.isEmpty(hzywjo.nyzyrklhczyydm)){
				data.nyzyrklhczyydm = hzywjo.nyzyrklhczyydm;
			}
			if(!Gnt.util.isEmpty(hzywjo.zzxz)){
				data.zzxz = hzywjo.zzxz;
			}
			if(!Gnt.util.isEmpty(hzywjo.ysqrgx)){
				data.ysqrgx = hzywjo.ysqrgx;
			}
			if(!Gnt.util.isEmpty(hzywjo.cym)){
				data.cym = hzywjo.cym;
			}
		}else{
			Ext.getCmp("all_qsrk_btn").handler();
		}
		
		return data;
	}
	//data.mz = hzywjo.bsqrmz;
	//add by zjm 增加个类型，用来区分立户还是入户
	function initSqry(type){
		//Gnt.toFormReadyonly(sqryForm);
		
		//初始化随迁人员
		//新增一个随迁人员
		ywid++;
		if(hzywjo_list && hzywjo_list.length > 0){
			Ext.each(hzywjo_list,function(jo){
				if(jo.qcdssxq.startWith(sysQcdq)){
					/**
		              	2018.10.15
		              	修改	刁杰
		              	28. 户政平台办理迁入登记,随迁人员信息中的’’与申请人关系’’内容默认清空,由办事民警手动填写
		              	
		              	2018.10.22
		              	姓名，、性别、民族、出生日期、迁入前户别、迁入后户别 不带值
		              	
		              	2019.07.29  zjm 与申请人关系带值
					 */
					var data = null;
					if(type&&type=="rh"){
						data = {
								spsqzid: ywid,
//								xm: jo.bsqrxm,
								ysqrgx: jo.btkrgx,
//								xb: jo.bsqrxb,
//								mz: jo.bsqrmz,
//								csrq: jo.bsqrcsrq,
								qrhhb:jo.bdhhb,
								gmsfhm: jo.bsqrsfz,
								ysqrgx:jo.ysqrgx
						};
					}else if(type&&type=="lh"){
						data = {
								spsqzid: ywid,
//								xm: jo.bsqrxm,
								ysqrgx: jo.btkrgx,
//								xb: jo.bsqrxb,
//								mz: jo.bsqrmz,
//								csrq: jo.bsqrcsrq,
								gmsfhm: jo.bsqrsfz,
								ysqrgx:jo.ysqrgx,
								qrhhb:jo.bdhhb
						};
					}
					var rr = new sqryGrid.store.reader.recordType(data, data.spsqzid);
					sqryGrid.store.add([rr]);
					ywid++;
				}else{
					/**
						2018.10.22
						新增	刁杰
						省外:直接带值
					 */
					// 20190729 add by zjm 与申请人关系带值
					var data = null;
					if(type&&type=="rh"){
						data = {
								spsqzid: ywid,
								xm: jo.bsqrxm,
								xb: jo.xb,
								mz: jo.mz,
								csrq: jo.csrq,
								gmsfhm: jo.bsqrsfz,
								qrhhb : hb,
								hkxz:jo.qrqhb,
								yhkqx:hzywjo.hkszqx,
								ysqrgx:jo.ysqrgx
						};
					}else if(type&&type=="lh"){
						//add by zjm 20190729  性别 xb、民族 mz 、出生日期  csrq 、与申请人关系 ysqrgx 
						data = {
								spsqzid: ywid,
								xm: jo.bsqrxm,
								xb: jo.xb,
								mz: jo.mz,
								csrq: jo.csrq,
								gmsfhm: jo.bsqrsfz,
								qrhhb:jo.bdhhb,
								hkxz:jo.qrqhb,
								yhkqx:hzywjo.hkszqx,
								ysqrgx:jo.ysqrgx
						};
					}
					
//					if(jo.lhbs=='1'){
//						data.qrhhb = hb;
//					}else{
//						//data.qrhhb = jo.hb; 要求立户迁入后户别改成变动后户别
//						data.qrhhb = jo.bdhhb
//					}
					
					var rr = new sqryGrid.store.reader.recordType(data, data.spsqzid);
					sqryGrid.store.add([rr]);
					ywid++;
				}
			});
			
			/**
				2018.10.22
				修改	刁杰
				虽然会有随迁人集合,但是不一定都是省外的,省内人员信息从
			 */
			sqryGrid.fireEvent("rowclick",sqryGrid,0);
			sqryGrid.getSelectionModel().selectRange(0,0);
			
			//Gnt.toFormEdit(sqryForm);
			
		}else{
//			var rr = new sqryGrid.store.reader.recordType({spsqzid: ywid}, ywid);
//			sqryGrid.store.add([rr]);
		}
	}
	
	var rhWindow = new Gnt.ux.SelectRh({
		qydjFlag:true,
		callback: function(optype, hcyList, selectHcy, hxx, moreInfo){
				initYw();
			
				returnObj = {
					optype: optype, 
					hcyList: hcyList, 
					selectHcy: selectHcy, 
					hxx: hxx, 
					moreInfo: moreInfo,
					select_optype_index: 1
				};
				
				//点击确认迁出后，执行此方法
				select_optype_index = 1;
			
				Ext.getCmp('card').getLayout().setActiveItem(1);
				v.doLayout();
			
				//户成员初始化
				var store = hcyGrid.store;
				//人员选择回调：审批业务，迁入人员
				for(var i=0;i<hcyList.length;i++){
					var data = hcyList[i];
					data._sel = "0";
					var rr = new store.reader.recordType(data, data[store.pkname]);
					store.add([rr]);
					if(Hhnbid==null)
						Hhnbid = hxx.hhid;
				}
				returnObj.hhnbid = Hhnbid;
				
				//初始化业务
				(function(){
						//新增一个随迁人员
						Gnt.yw.queryHdxx({
							callback:function(hdxx,moreInfo){
								returnObj.hdxx = hdxx;
								returnObj.moreInfo = moreInfo;
								var data = getUpdateDxx(returnObj.hdxx,"rh");
								/**
									2019.01.18
									修改	刁杰
									户政业务跳转时 申请人姓名/申请人身份证号码 使用中间表申请人姓名(SQRXM)/申请人身份证(SQRSFZ)字段	其余申请人信息留空
									手动申请迁入业务时申请人信息默认户主信息
									
									2019.01.21
									修改	刁杰
									这5个字段值,如果户政平台没有值,那么就默认区迁入户户主信息;
									如果户政平台填了姓名,身份号码,那么就取中间表的值,下面3个字段,空着,让民警手动填写
									默认设置户主的五个值,再从户政业务判断是否有申请人姓名/身份证号码
									有:设置申请人姓名/身份证号码,清空其余三项
									无:使用户主的五个值
									
									只要申请人姓名/身份证号码有值,则不带另外三项值
								 */
								var flag = true;
								if(hzywjo){
									if(hzywjo.sqrxm){
										data.sqrxm = hzywjo.sqrxm;
										flag = false;
									}
									if(hzywjo.sqrsfz){
										data.sqrgmsfhm = hzywjo.sqrsfz;
										flag = false;
									}
//									if(hzywjo.qcdssxq.startWith(sysQcdq)){
//										data.hkszddjjg = hzywjo.hkdjjg;
//									}
								}
								
								if(flag){
									data.sqrhkdjjg = returnObj.moreInfo.pcsmc;
									//data.sqrzzxz = returnObj.moreInfo.xxdz;
									data.sqrzzssxq = returnObj.hxx.ssxq;
									data.sqrzzxz = returnObj.moreInfo.xxdz;
									data.sqrxm = returnObj.selectHcy.xm;
									data.sqrgmsfhm = returnObj.selectHcy.gmsfhm;
								}
								//中间表字段优先 add by zjm 20190729
								if(hzywjo){
									if(hzywjo.sqrzzssxq){
										data.sqrzzssxq = hzywjo.sqrzzssxq;
									}
									if(hzywjo.sqrhkdjjg){
										data.sqrhkdjjg = hzywjo.sqrhkdjjg;
									}
									if(hzywjo.sqrxm){
										data.sqrxm = hzywjo.sqrxm;
									}
									if(hzywjo.sqrsfz){
										data.sqrgmsfhm = hzywjo.sqrsfz;
									}
									if(hzywjo.sqrlxdz){
										data.sqrzzxz = hzywjo.sqrlxdz;
									}
									SetReadOnly(form_yw, 'xm', true);
									SetReadOnly(form_yw, 'xb', true);
									SetReadOnly(form_yw, 'mz', true);
									SetReadOnly(form_yw, 'gmsfhm', true);
									SetReadOnly(form_yw, 'csrq', true);
								}
								data.qrhhb = hb;
								SetReadOnly(form_yw, 'qrhhb', true);
								
								var r = new yw_store.reader.recordType(data, "-1");
								yw_store.add([r]);
								form_yw.getForm().setValues(r.data);
								initSqry("rh");
								
							},
							hhnbid: returnObj.hxx.hhnbid
					});
				}).defer(200);
				//SetReadOnly(form_yw, 'gmsfhm', true);
		},
		rowclickBack : function(optype, data){
			hb = data.hb;
		}
	});
	
	var lhWindow = new Gnt.ux.SelectBzdz({
		//选择立户信息回调
		callback: function(optype, lhdz){
			initYw();
			//add by zjm 20200227 立户时，将hhnbid设为0
			lhdz.hhnbid=0;
			returnObj = {
				optype: optype, 
				lhdz: lhdz, 
				select_optype_index: 2
			};
			lhHlx = lhdz.hlx; 
			//初始化业务
			(function(){
				var data = getUpdateDxx(returnObj.lhdz,"lh");
				data.qrdhkdjjg = returnObj.lhdz.dwdm;
				data.sqrzzssxq = returnObj.lhdz.dwdm.substring(0,6);
				data.sqrhkdjjg = returnObj.lhdz.dwdm_name;
				data.sqrzzxz = returnObj.lhdz.dwdm_bz + returnObj.lhdz.dwdm_name + returnObj.lhdz.mc + returnObj.lhdz.mlph + returnObj.lhdz.mlxz;
				//中间表字段优先 add by zjm 20190729
				if(hzywjo){
					if(hzywjo.sqrzzssxq){
						data.sqrzzssxq = hzywjo.sqrzzssxq;
					}
					if(hzywjo.sqrhkdjjg){
						data.sqrhkdjjg = hzywjo.sqrhkdjjg;
					}
					if(hzywjo.sqrsfz){
						data.sqrgmsfhm = hzywjo.sqrsfz;
					}
					if(hzywjo.sqrxm){
						data.sqrxm = hzywjo.sqrxm;
					}
					if(hzywjo.bdhhb){
						data.qrhhb = hzywjo.bdhhb;
					}
					if(hzywjo.sqrlxdz){
						data.sqrzzxz = hzywjo.sqrlxdz;
					}
					//add by zjm 20190920 主迁人姓名 性别 名族 公民身份号码 出生日期 只读
					SetReadOnly(form_yw, 'xm', true);
					SetReadOnly(form_yw, 'xb', true);
					SetReadOnly(form_yw, 'mz', true);
					SetReadOnly(form_yw, 'gmsfhm', true);
					SetReadOnly(form_yw, 'csrq', true);
					
					
				}
				
				var r = new yw_store.reader.recordType(data, "-1");
				yw_store.add([r]);
				
				form_yw.getForm().setValues(data);
				initSqry("lh");
				
			}).defer(200);
			
			//点击确认后，执行此方法
			Ext.getCmp('card').getLayout().setActiveItem(1);
			v.doLayout();
			
		}
	});
	
	var p1 = new Ext.Panel({
		layout: 'border',
		newYewu: function(){
			//初始化
		},
		items:[
		   	{
				title: '业务办理方式选择',
				html:'',
				region:'north',
				height:40,
				border:false,
				frame:true,
				buttonAlign:'left',
				buttons:[
							new Ext.Button({
								text:'入户方式',
								id: 'rh_btn',
								minWidth:75,
								handler:function(){
									rhFlag = true;
									rhWindow.show();
									rhWindow.setHzyw(hzywjo, hzywjo_list);
								}
							}),
							new Ext.Button({
								text:'立户方式',
								id: 'lh_btn',
								minWidth:75,
								handler:function(){
									rhFlag = false;
									lhWindow.show();
									lhWindow.setHzyw(hzywjo, hzywjo_list);
								}
							})
				]
			},{
				html: '请选择业务办理方式！',
				region:'center',
				border:false,
				frame:false,
				bodyStyle:'padding:15px'
			}
		]
	});
	
	//Hj_地信息
	var wsCdsdxx = new Gnt.store.SjpzStore({
		pzlb: '10017',
		pkname: 'jcwh'
	});
	
	hcyGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		pzlb: '10019',
		region:'center',
    	title: '原户成员列表',
    	showPaging:false,
    	url: 'yw/common/queryRyxx.json',
		listeners:{
			rowclick:function(g, rowIndex, e){
	   			selRes = g.store.getAt(rowIndex);
    			curIndex = rowIndex;
    		
    			if(editPanelCard.getLayout().activeItem.id=="c1"){
        			editPanelCard.getLayout().setActiveItem(1);
        			editPanelCard.doLayout();
    			}

    			//人员基本资料更新
    			form_hcy.getForm().reset();
    			form_hcy.getForm().loadRecord(selRes);
    		}
		}
	});
	
	var yw_store = new Gnt.store.SjpzStore({
		pzlb: '10030',
		pkname: 'spywid'
	});
	
	form_yw = new Gnt.ux.SjpzForm({
		title: '主迁人员信息',
		closable: false,
		region:'center',
		height:370,
		pzlb: '10030',
		labelWidth :  180,
		cols:2,
		bindViewGridHCY: hcyGrid,
		bindStore: yw_store,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		//bindViewGrid: ywGrid,
		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
		changeDictCust:function(cmb,res){
			return;
		}/*,
    	fieldBlur:function(field){
    		*//**
    			输入了身份证号并且出生日期未选择
    			自动设置出生日期
    			hcyGrid设置不上（保存时出生日期会自动清空），可能用的数据集不是这个
    		 *//*
        	if(field.name=='gmsfhm'){
        		var r = hcyGrid.getSelectionModel().getSelected();
        		if(field.getValue() && !form_yw.getForm().findField("csrq").getValue()){
        			if(field.getValue().length == 15){
        				if(r){
        					r.data.csrq = "19" + field.getValue().substr(6,6);
        				}
        				form_yw.getForm().findField("csrq").setValue("19" + field.getValue().substr(6,6));
        			}else if(field.getValue().length == 18){
        				if(r){
        					r.data.csrq = field.getValue().substr(6,8);
        				}
        				form_yw.getForm().findField("csrq").setValue(field.getValue().substr(6,8));
        			}
        		}
        	}
    	}*/
		,
		fieldFocus:function(field){
			var f = field.findParentByType("sjpz_form");        	
        	if(field.name=='splx'){
        		field.filter =function(data){
        			if(data[0]=='' || data[0]=='1' || data[0]=='2'){
						return true;
					}
        		};
    			field.reloadDict();
        	}
		}/*,
        fieldChange:function(field){
        	if(field.name=='gmsfhm'){
        		if(form_yw.getForm().findField("gmsfhm").getValue().length == 6){
        			alert(1);
        		}
        	}
        }*/
	});
	
	sqryGrid = new Gnt.ux.SjpzGrid({
		pkname: 'spsqzid',
		pzlb: '10031',
		region:'west',
    	title: '随迁人员列表',
    	showPaging:false,
    	width: 200,
    	url: 'yw/common/queryRyxx.json',
		listeners:{
			rowclick:function(g, rowIndex, e){
	   			selRes = g.store.getAt(rowIndex);
    			curIndex = rowIndex;
    			//人员基本资料更新
    			sqryForm.getForm().reset();
    			sqryForm.getForm().loadRecord(selRes);
    			//add by zjm 20190920 主迁人姓名 性别 名族 公民身份号码 出生日期 只读
    			SetReadOnly(sqryForm, 'xm', true);
    			SetReadOnly(sqryForm, 'xb', true);
    			SetReadOnly(sqryForm, 'mz', true);
    			SetReadOnly(sqryForm, 'gmsfhm', true);
    			SetReadOnly(sqryForm, 'csrq', true);
    		}
		}
	});
	
	sqryForm = new Gnt.ux.SjpzForm({
		title: '随迁人员信息',
		closable: false,
		pzlb: '10031',
		region:'center',
		labelWidth : 120,
		cols:2,
		bindViewGrid:sqryGrid,
		bindViewGridHCY: hcyGrid,
		changeDictCust:function(cmb,res){
			return;
		},
    	fieldBlur:function(field){
    		/**
    			输入了身份证号并且出生日期未选择
    			自动设置出生日期
    		 */
        	if(field.name=='gmsfhm'){
        		var r = sqryGrid.getSelectionModel().getSelected();
        		if(field.getValue() && !sqryForm.getForm().findField("csrq").getValue()){
        			if(field.getValue().length == 15){
        				if(r){
        					r.data.csrq = "19" + field.getValue().substr(6,6);
        				}
        				sqryForm.getForm().findField("csrq").setValue("19" + field.getValue().substr(6,6));
        			}else if(field.getValue().length == 18){
        				if(r){
        					r.data.csrq = field.getValue().substr(6,8);
        				}
        				sqryForm.getForm().findField("csrq").setValue(field.getValue().substr(6,8));
        			}
        		}
        	}
    	}
	});
	
	
	var p2 = new Ext.Panel({
		layout: 'border',
		items:[
		       form_yw,
		       {
		    	   region:'south',
		    	   height:200,
		    	   layout:'border',
		    	   buttonAlign:'right',
//		    	   buttons:[
//		    	            new Ext.Button({
//								text:'新办理',
//								minWidth:100,
//								width:150,
//								disabled:hzywid?true:false,
//								handler:function(){
//									showQuestion("数据未保存，确定放弃办理吗？", function(btnType){
//										if(btnType=="yes"){
//											/**
//												重新天赚到业务页面后户成员及随迁人员表单丢失
//											Ext.getCmp('card').getLayout().setActiveItem(0);
//											v.doLayout();
//											initYw();
//											*/
//											window.location.reload();
//										}
//									});
//								}
//		    	            }),{
//				    	    	xtype:'CkdaButton',
//				    	    	html:'sssss',
//				    	    	hzywid:getQueryParam("hzywid")
//				    	    }/*,{
//				    	    	xtype:'CkdaButton',
//				    	    	hzywid:getQueryParam("hzywid")
//				    	    },{
//				    	    	height:3,
//				    	    	border:false,
//				    	    	frame:false
//				    	    },{
//				    	    	xtype: 'CheckClbsBeforeSaveButton',
//				    	    	hzywid:getQueryParam("hzywid"),
//				    	    	callback: function(){
//				    	    		save();
//				    	    	}
//				    	    }*/,new Ext.Button({
//								text:'保存',
//								minWidth:100,
//								width:150,
//								handler:function(){
////									print();
//									save();
//								}
//		    	            }),new Ext.Button({
//								text:'关闭',
//								minWidth:100,
//								width:150,
//								handler:function(){
//									showQuestion("数据未保存，确定放弃办理吗？", function(btnType){
//										if(btnType=="yes"){
//											if(parent && parent.closeWorkSpace)
//												parent.closeWorkSpace();
//											else
//												window.close();
//										}
//									});
//								}
//		    	            })
//		    	   ],
		    	   items:[
		    	          sqryGrid,
		    	          sqryForm,
		    	          {
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
		 			    	    		html:'',
		 			    	    		bodyStyle:'padding:10px 25px 0px 20px',
		 				   	    	 	layout:'table',
		 				   	    	 	align:'center',
		 				   	    	 	border:false,
		 				   	    	 	frame:false,
		 				   	    	 	layoutConfig: {
		 				   	    	 		columns: 1
		 				   	    	 	},
		 				   	    	 	items:[
		 									new Ext.Button({
		 										text:'全省人口查询',
		 										id: 'all_qsrk_btn',
		 										minWidth:100,
		 										width:150,
		 										handler:function(){
		 											if(qsry_win==null){
		 												qsry_win = new Gnt.ux.SelectQsryxx({
		 													selectCallback:function(rylist, kdsjo){
		 														kds_jo = kdsjo;
		 														
		 														sqryGrid.store.removeAll();
		 														
		 														/**
		 															姓名，、性别、民族、出生日期、迁入前户别、迁入后户别（入户的话默认和原户成员一致）户口所在区县
		 															值不替换
		 														 */
		 														for(var index=0;index<rylist.length;index++){
		 															var data = rylist[index];
		 															if(hzywjo){
		 																/**
		 																	2018.11.09
		 																	修改	刁杰
		 																	户政业务根据主迁人身份证号码设置
		 																	普通业务根据第一条记录设置
		 																 */
		 																if(hzywjo.bsqrsfz == data['gmsfhm']){
		 																	//主迁移人
		 																	setZrq(yw_store,data);
		 																}else{
		 																	//随迁人
		 																	setSqr(data);
		 																}
		 															}else{
		 																if(index==0){
		 																	//主迁移人
		 																	setZrq(yw_store,data);
		 																}else{
		 																	//随迁人
		 																	setSqr(data);
		 																}
		 															}
		 														}
		 														
		 														/**
		 															获取主迁人 户口所在地登记机关
		 														 */
		 														if(yw_store.getCount() > 0){
		 															var r = yw_store.getAt(0);
		 															var subdata = {
		 																rynbid:data['rynbid'],
		 																config_key:'queryQrspPcs'
		 															};
		 															
		 															Gnt.submit(
		 																subdata, 
		 																"cx/hjjbxx/ckxx/getXxxx.json", 
		 																"", 
		 																function(jsonData,params){
		 																	if(jsonData && jsonData.list[0] && jsonData.list[0].mc){
		 																		//modify by zjm 20190318 我们不做处理  都从户政平台带入   
		 																		//r.set('hkszddjjg', jsonData.list[0].mc);
		 																		form_yw.getForm().setValues(r.data);
		 																	}
		 																}
		 															);
		 														}
		 														
		 														if(rylist.length >= 2){
		 															Ext.getCmp('bbtnAdd').disable();
	 					 											Ext.getCmp('bbtnDelete').enable();
	 					 											sqryGrid.fireEvent("rowclick",sqryGrid,0);
	 					 											sqryGrid.getSelectionModel().selectRange(0,0);
	 					 											
	 					 											//Gnt.toFormEdit(sqryForm);
	 					 											
		 														}
		 													}
		 												});
		 											}
	 												if(hzywjo){
	 													qsry_win.setHzyw(hzywjo, hzywjo_list);
	 												}
	 												
		 											qsry_win.show();
		 										}
		 									}),{
		 						    	    	height:5,
		 						    	    	border:false,
		 						    	    	frame:false
		 						    	    },new Ext.Button({
		 										text:'申请人查询',
		 										minWidth:100,
		 										handler:function(){
		 											if(sqrWindow==null){
		 												sqrWindow = new Gnt.ux.SelectRh({
		 													qydjFlag:true,
		 													select_type: '1',
		 													callback: function(optype, hcyList, selectHcy, hxx, moreInfo){
		 														var data = {
		 																sqrxm: selectHcy.xm,
		 																sqrgmsfhm: selectHcy.gmsfhm,
		 																sqrzzssxq: hxx.ssxq,
		 																sqrzzxz: moreInfo.xxdz,
		 																sqrhkdjjg: moreInfo.pcsmc
		 														};
		 														form_yw.getForm().setValues(data);
		 													}
		 												});
		 											}
		 											
		 											//sqrWindow.resetData();
		 											sqrWindow.show();
		 										}
		 									}),{
		 						    	    	height:5,
		 						    	    	border:false,
		 						    	    	frame:false
		 						    	    },new Ext.Button({
		 										text:'申请人同主迁人',
		 										minWidth:100,
		 										handler:function(){
		 											var data = null;
		 											/*
		 											 * 获取前，先将需要获取的姓名和公民身份证号码只读去掉，这样才能取到值 
		 											 * 获取后，再将需要获取的姓名和公民身份证号码只读加上 
		 											 * add by zjm 20190918
		 											 */
		 											if(hzywjo){
		 												SetReadOnly(form_yw, 'xm', false);
		 												SetReadOnly(form_yw, 'gmsfhm', false);
		 												data = form_yw.getForm().getValues();
		 												SetReadOnly(form_yw, 'xm', true);
		 												SetReadOnly(form_yw, 'gmsfhm', true);
		 											}else{
		 												data = form_yw.getForm().getValues();
		 											}
		 											/*
		 											var data_new = {
		 													sqrxm: data.xm,
		 													sqrgmsfhm: data.gmsfhm,
		 													sqrzzssxq: data.zzssxq,
		 													sqrzzxz: data.zzxz,
		 													sqrhkdjjg: data.hkszddjjg
		 											};
		 											form_yw.getForm().setValues(data_new);
		 											*/
		 											//所有绑定到store的表单，修改其它字段值，必须调用fieldSetValue方法，否则无法更新store
		 											//重点，重点
		 											form_yw.fieldSetValue("sqrxm", data.xm);
		 											form_yw.fieldSetValue("sqrgmsfhm", data.gmsfhm);
		 											form_yw.fieldSetValue("sqrzzssxq", data.zzssxq);
		 											form_yw.fieldSetValue("sqrzzxz", data.zzxz);
		 											form_yw.fieldSetValue("sqrhkdjjg", data.hkszddjjg);
		 										}
		 						    	    }),{
		 						    	    	height:13,
		 						    	    	border:false,
		 						    	    	frame:false
		 						    	    },new Ext.Button({
		 						    	    	id:'bbtnAdd',
		 										text:'增加随迁人员',
		 										minWidth:100,
		 										handler:function(){
		 											ywid++;
		 											var rr = new sqryGrid.store.reader.recordType({spsqzid: ywid}, ywid);
		 											sqryGrid.store.add([rr]);
		 											
		 											var index = sqryGrid.store.getCount() - 1;
		 											
		 											Ext.getCmp('bbtnDelete').enable();
			 											
		 											sqryGrid.fireEvent("rowclick",sqryGrid,index);
		 											sqryGrid.getSelectionModel().selectRange(index,index);
		 											
		 											Gnt.toFormEdit(sqryForm);
		 											
		 										}
		 						    	    }),{
		 						    	    	height:5,
		 						    	    	border:false,
		 						    	    	frame:false
		 						    	    },new Ext.Button({
		 						    	    	id:'bbtnDelete',
		 										text:'删除随迁人员',
		 										minWidth:100,
		 										handler:function(){
		 											var r = sqryGrid.getSelectionModel().getSelected();
		 											if(r){
		 												var msg = "确定删除随迁人员[" + (r.data.xm?r.data.xm:"") + "]的信息吗？"
		 												showQuestion(msg, function(btnType){
		 													if(btnType=="yes"){
		 														sqryGrid.store.remove(r);
		 														
		 														var index = sqryGrid.store.getCount() - 1;
		 														if(index<0){
		 				 											index=-1;
		 				 											
		 				 											Ext.getCmp('bbtnDelete').disable();
		 				 											
		 				 											Gnt.toFormReadyonly(sqryForm);
		 				 											
		 														}else{
		 															sqryGrid.fireEvent("rowclick",sqryGrid,index);
		 															sqryGrid.getSelectionModel().selectRange(index,index);
		 														}
		 														
		 													}
		 												});
		 											}
		 										}
		 						    	    })
		 					    	 ]
		 			    	    }
		 			    	 ]
		 			    },{
		 			    	xtype: 'panel',
		 					border:false,
		 					frame: false,
		 			    	region:'south',
		 			    	height: 30,
		 			    	align:'center',
		 			    	bodyStyle: 'padding:5px',
		 					layout:'table',
		 					layoutConfig: {
		 			        	columns: 10
		 			        },
		 			    	items:[

			    	            new Ext.Button({
									text:'新办理',
									minWidth:100,
									width:150,
									disabled:hzywid?true:false,
									handler:function(){
										showQuestion("数据未保存，确定放弃办理吗？", function(btnType){
											if(btnType=="yes"){
												/**
													重新天赚到业务页面后户成员及随迁人员表单丢失
												Ext.getCmp('card').getLayout().setActiveItem(0);
												v.doLayout();
												initYw();
												*/
												window.location.reload();
											}
										});
									}
			    	            }),{
					    	    	width:10,
					    	    	border:false,
					    	    	frame:false
					    	    },{
					    	    	xtype:'CkdaButton',
					    	    	hzywid:getQueryParam("hzywid")
					    	    },{
					    	    	width:10,
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
									width:150,
									handler:function(){
//										print();
										save();
									}
			    	            })*/,{
					    	    	width:10,
					    	    	border:false,
					    	    	frame:false
					    	    },new Ext.Button({
									text:'关闭',
									minWidth:100,
									width:150,
									handler:function(){
										showQuestion("数据未保存，确定放弃办理吗？", function(btnType){
											if(btnType=="yes"){
												if(parent && parent.closeWorkSpace)
													parent.closeWorkSpace();
												else
													window.close();
											}
										});
									}
			    	            })
			    	   
		 			    	]
		 			    }
		    	   ]
		       }
		]
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
		    	   region:'center',
		    	   title:'',
		    	   frame:false,
				   borde:false,
		    	   html: '<center><br/><br/><br/><br/><h2>迁入审批业务办理完成！</center></h2>'
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
		    			},new Ext.Button({
							text:'一站式迁入',
							minWidth:100,
							width:150,
							handler:function(){
								if(!newHzywid){
									alert("没有生产新的中间表数据！")
									return;
								}
								Gnt.makeHzyw({
									hzywid: newHzywid,
									pch:pch,
									ywlx:'3',
									cmpids: ['rh_btn', 'lh_btn'],
									callback:function(jo, jolist){
										if(jo){
											//迁入业务
											var url = basePath +  'yw/hjyw/qryw?hzywid=' + jo.id + "&pch=" + jo.pch;
											if(parent && parent.openWorkSpace){
												parent.openWorkSpace(url,jo.ywlb + "【" + jo.bsqrxm + "】", "_hzywcl_" + jo.id);
											}else{
												window.open(url);
											}	
										}
										
									}
								});
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
			    	    	ywcode:'105'
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
	   								url += "spywid=" + selRes.data.spywid+"&ywcode=105&ryid="+selRes.data.ryid;
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
		    			},new Ext.Button({
							text:'准迁（移）证打印',
							minWidth:100,
							width:150,
							handler:function(){
								zqzPrint(selectSpywid);
							}
		    			}),{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
		    			},new Ext.Button({
							text:'一站式异地迁出',
							minWidth:100,
							width:150,
							handler:function(){
								var url = basePath+"yw/hjyw/yzsydqc";
		//						if(hzywid && hzywid!=""){
		//							url = "../../yw/hjyw/yzsydqc";
		//						}else{
		//							url = "yw/hjyw/yzsydqc";
		//						}
								if(parent && parent.openWorkSpace){
									parent.openWorkSpace(url,  "一站式异地迁出", "yzsqcId");
								}else{
									window.open(url);
								}
							}
		    			}),{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
		    			},new Ext.Button({
							text:'新办理',
							minWidth:100,
							width:150,
							disabled:hzywid?true:false,
							handler:function(){
									Ext.getCmp('card').getLayout().setActiveItem(0);
									v.doLayout();
									initYw();
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
								if(parent && parent.closeWorkSpace)
									parent.closeWorkSpace();
								else
									window.close();
							}
			           })
				]
		       }
		]
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
        	items:[p1,p2,p3]
        }
    });
	
	/**
		2018.11.09
		新增	刁杰
		普通迁入登记 - 全省人口设置主迁人
	 */
	function setZrq(store,data){
		var r = store.getAt(0);
         r.set('xm', data['xm']);
         r.set('xb', data['xb']);
         r.set('mz', data['mz']);
         r.set('csrq', data['csrq']);
         r.set('gmsfhm', data['gmsfhm']);
         
         r.set('hb', data['hb']);
         r.set('zzssxq', data['ssxq']);   //jgssxq
         //户口所在地登记机关取中间表数据  add by zjm 20190401
         if(hzywjo && hzywjo.hkdjjg&&!Gnt.util.isEmpty(hzywjo.hkdjjg)){
        	 r.set('hkszddjjg', hzywjo.hkdjjg);
         }else{
        	 //20190904 周晨晨要求中间表字段值没有就空着，不取常表的qfjg了
        	 //r.set('hkszddjjg', data['qfjg']);
         }
         /**
        		2018.10.16
        		新增	刁杰
        		通过人员内部地(rynbid)查找迁出注销信息表(HJYW_CZRKJBXXB)的派出所(PCS)中文
        		用以填充 户口所在地登记机关
          */
        zqrRynbid = data['rynbid'];
        
//			r.set('hkszddjjg', data['qfjg']);	//无此字段
		
        /**
			BugFree 450
			中间表dhhm有值,就取中间表的dhhm,没值就取常口dhhm
         */
        if(hzywjo && hzywjo.dhhm){
        	r.set('lxdh', hzywjo.dhhm);
        }else{
        	r.set('lxdh', data['dhhm']);
        }
        
         r.set('whcd', data['whcd']);
         
         /**
        		立户时:根据选择的户类型设置迁入后户别
        		1 : 居民家庭户 : 6
        		2 : 居民集体户 : 7
          */
         if(rhFlag == false){
         	var lhData = lhWindow.fs.getForm().getValues();
         	lhHlx = lhData.hlx; 
         	if(lhData.hlx == 1){
         		r.set('qrhhb', "6");
         	}else if(lhData.hlx == 2){
         		r.set('qrhhb', "7");
         	}
         }
         //add by zjm 20190730 中间表bdhhb有值，就要替换掉表单上
        if(hzywjo && hzywjo.bdhhb){
      		r.set('qrhhb', hzywjo.bdhhb);
      	}
        form_yw.getForm().reset();
        form_yw.getForm().setValues(r.data);
	}
	
	/**
		2018.11.09
		新增	刁杰
		普通迁入登记 - 全省人口设置随迁人
	 */
	function setSqr(data){
		ywid++;
		var rr = new sqryGrid.store.reader.recordType({spsqzid: ywid}, ywid);
		
		rr.set('xm',data['xm']);
		rr.set('xb',data['xb']);
		rr.set('mz',data['mz']);
		rr.set('csrq',data['csrq']);
		rr.set('gmsfhm',data['gmsfhm']);
		rr.set('hkxz',data['hb']);
		rr.set('yhkqx',data['ssxq']);
		if(hzywjo_list && hzywjo_list.length > 0){
			Ext.each(hzywjo_list,function(jo){
				if(data['gmsfhm']==jo.bsqrsfz){
					rr.set('ysqrgx', jo.ysqrgx);
				}
			});
		}
		//rr.set('ysqrgx', data['yhzgx']); 要求全身人口这个字段不覆盖
		rr.set('qrhhb', yw_store.getAt(0).get('qrhhb'));
		sqryGrid.store.add([rr]);
		
	}
	
	//审批信息提示，chkCh返回组装
	function chkSpinfo(spinfo){
		if(spinfo!=""){
			showQuestion(spinfo,function(btntype){
				if(btntype=="yes"){
					selectSpcl();
				}
			});
		}else{
			selectSpcl();
		}
	}
	
	//重号，重人判断
	function chkCh(){
		var sfzh = "", xm = "";
		var data = null;
		/*
		 * 获取前，先将需要获取的姓名和公民身份证号码只读去掉，这样才能取到值 
		 * 获取后，再将需要获取的姓名和公民身份证号码只读加上 
		 * add by zjm 20190918
		 */
		if(hzywjo){
			SetReadOnly(form_yw, 'xm', false);
			SetReadOnly(form_yw, 'gmsfhm', false);
			data = form_yw.getForm().getValues();
			SetReadOnly(form_yw, 'xm', true);
			SetReadOnly(form_yw, 'gmsfhm', true);
		}else{
			data = form_yw.getForm().getValues();
		}
		if(data.gmsfhm!=""){
			sfzh = data.gmsfhm;
			xm = data.xm;
		}
		var res = sqryGrid.store.each(function(r){
			if(r.data.gmsfhm && r.data.gmsfhm!=""){
				sfzh += "," + r.data.gmsfhm;
				xm += "," + r.data.xm;
			}
		});
		
		Gnt.submit(
			{
				sfzh: sfzh, 	//"342501197709294225x,340702192004281013x,340702192308171024x",
				xm: xm		 	//"测试,测试2,测试3"
			}, 
			"yw/spgl/qrsp/checkQrspdjyw",
			"校验数据，请稍后...", 
			function(jsonData,params){
				var data = jsonData.list[0];
				
				//组装重号信息
				var chListxm = [];
				if(data.chMap){
					for(sfzh in data.chMap){
						var xm = data.xmMap[sfzh];
						chListxm.push({
							xm: xm,
							gmsfhm: sfzh
						});
					}
				}
				
				//组装重复审批信息
				var spinfo = "";
				if(data.spMap){
					for(sfzh in data.spMap){
						spinfo += "[" + sfzh + "]已经存在迁入审批；<br/>";
					}
					if(spinfo!=""){
						spinfo += "是否继续？";
					}
				}
				//判断
				if(chListxm.length>0){
					var d = new Gnt.ux.QrspChDialog({
						callback:function(re, reMap){
							yw_store.each(function(r){
								if(reMap[r.data.gmsfhm]!=undefined){
									r.set("ryid", reMap[r.data.gmsfhm]);
									//alert("主迁入" + reMap[r.data.gmsfhm]);
								}
							});
							
							sqryGrid.store.each(function(r){
								if(reMap[r.data.gmsfhm]!=undefined){
									r.set("ryid", reMap[r.data.gmsfhm]);
									//alert("随迁人" + reMap[r.data.gmsfhm]);
								}
							});
							
							chkSpinfo(spinfo);
						}
					});
					
					d.setData({
						chMap: data.chMap,
						chListxm: chListxm
					});
					d.show();
				}else{
					chkSpinfo(spinfo);
				}
			}
		);
	}
	
	//材料选择
	var clWin = null;
	function selectSpcl(){
		spcl = null;
		if(clWin==null){
			clWin = new Gnt.ux.SpclDialog({
				callback:function(data){
					spcl = data;
					saveYW();
				}
			})
		}
		
		clWin.reset();
		clWin.show();
	}
	
	function save(){
		if(!form_yw.getForm().isValid()){
			showErr("主迁人信息必须填写！");
			return;
		}
		
		if(!form_yw.checkInput(false, "1", true))
			return;
		
		if(!sqryForm.checkInput(false, "1", true))
			return;
		
		//重号校验，最后调起saveYW()方法
		chkCh();
	}
	
	function saveYW(){
		var submit_data = {
			spsm: spcl.fy,
			voQrspdjxx: yw_store.getAt(0).data,
			voQrspdjzxx:[],
			voSpfdclxx:[]
		};

		//submit_data.voQrspdjxx.hhnbid_rh = Hhnbid;
		submit_data.voQrspdjxx.qrdhhid = Hhnbid;
		//跨地市过来
	    if (kds_jo && kds_jo.dqbm){
	    	//初始化迁出地区编码
	    	submit_data.voQrspdjxx.kdqqy_qcdqbm = kds_jo.dqbm;
	    }
	    //户政业务过来
	    if(hzywjo && hzywjo.id){
	    	submit_data.voQrspdjxx.hzywid = hzywjo.id;
	    }
	    
	    for(var index=0;index<sqryGrid.store.getCount();index++){
			var data = sqryGrid.store.getAt(index).data;
			if(lhHlx){//add by zjm 20200312 增加户类型和户别匹配验证
				if((lhHlx==1&&data.qrhhb==7)||(lhHlx==2&&data.qrhhb==6)){
					alert("随迁人户类型和户别不匹配!");
					return;
				}
			}
			submit_data.voQrspdjzxx.push(data);
		}
	    
//		var store = sqryGrid.store;
//		if(store.getCount()>0){
//			store.each(function(r){
//				
//				if(lhHlx){//add by zjm 20200312 增加随迁人户类型和户别匹配验证
//					if((lhHlx==1&&r.data.qrhhb==7)||(lhHlx==2&&r.data.qrhhb==6)){
//						alert("随迁人户类型和户别不匹配!");
//						return false;
//					}
//				}
//				submit_data.voQrspdjzxx.push(r.data);
//			});
//		}
		var cllist = spcl.cl;
		if(cllist){
			Ext.each(cllist,function(cl){
				submit_data.voSpfdclxx.push({
					clbh: cl,
					hzywid: ''
				});
			});
		}
		
		for(o in submit_data){
			if(submit_data[o]){
				if(o!='spsm')
					submit_data[o] = Ext.encode(submit_data[o]);
			}
		}
		
		Gnt.submit(
				submit_data, 
				"yw/spgl/qrsp/processQrspdjyw", 
				"正在处理迁入审批登记信息，请稍后...", 
				function(jsonData,params){
					showInfo("迁入审批登记成功！",function(){
						kds_jo = null;
						Ext.getCmp('card').getLayout().setActiveItem(2);
						Ext.getCmp('vp').doLayout();
						if(hzywjo&&hzywjo.id){
							Gnt.ux.Dict.getKzcs('10036', function(pz, user) {
								if(pz.kzz==1){
									pjq('ES',user.ip,hzywjo.id);
								}
							});
						}
						selectSpywid = jsonData.list[0].spywid;
						if(!jsonData.list[0].spFlag){
							zqzPrint(selectSpywid);
						}
						if(jsonData.list[0].newHzywid){
							newHzywid = jsonData.list[0].newHzywid;
						}
					});
				},
				function(json){
					if(json.message){
						if(json.errcode && json.errcode==512){
							
						}else{
							showErr(json.message);
						}
					}
				}
		);
	}
	
	//户政业务处理
	if(hzywid && hzywid!=""){
		Gnt.makeHzyw({
			hzywid: hzywid,
			pch:pch,
			ywlx:'3',
			cmpids: ['rh_btn', 'lh_btn'],
			callback:function(jo, jolist){
				//成功处理返回
				hzywjo_list = jolist;
				hzywjo = jo;
				
				if(hzywjo.lhbs=='1'){
					//审批入户
					Ext.getCmp("rh_btn").handler();
				}else{
					//审批立户
					Ext.getCmp("lh_btn").handler();
				}
			}
		});
	}
});