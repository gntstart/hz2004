//最终户别：审批入户=主迁入户别；入户=户主户别；立户=户主选择户别；
var end_hb = null;
var lhHlx = null;
var selRes = null;
var curIndex = -1;
var spywid = null;
var ywid = 0;
var FRhHb  = null;
var Hhnbid = null;
var select_lhdz = null;
var select_optype_index = -1;
var xt_bssqb_list = null;
var form_sbr = null;//业务表单
var form_yw = null;//申报人表单
var ywGrid = null;
var hcyGrid = null;//户成员表单
var bggzWin = null;//变更更正对话框
var select_optype_index = -1;

var form_hcy = null;
var zqz_check = false;
var qyz_check = false;
var testCount = 0;
var hzywjo_list;
var hzywjo;

var rhFlag = -1;
var hb = null;
var new_ssxq = "";
var selectHhnbid=null;
var selectRynbid=null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	var hzywid = getQueryParam("hzywid");
	var pch = getQueryParam("pch");
	
	//本业务需要加载的配置
	//10019 Hj_户成员信息
	//10023 Hj_迁入登记信息
	//10018 Hj_户成员关系调整
	//10010 Hj_人户分离信息
	//10011 Hj_重号处理
	//10002 Hj_申报人信息
	//10017 Hj_地信息
	if(!Gnt.loadSjpzb("10019,10023,10018,10010,10011,10002,10017,20000",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	var old_hxx = null;
	var old_pcs = null, new_pcs = null;
	var old_jcwh = null, new_jcwh = null;
	var old_ssxq = null, new_ssxq = null;	
	//市区配置信息
	Gnt.yw.queryHbbgsp({
		callback:function(json){
			if(json.list && json.list.length>0){
				xt_bssqb_list = json.list;
			}
		}
	});
	function initYw(){
		new_ssxq = "";
		select_optype_index = -1;
		select_lhdz = null;
		selRes = null;
		curIndex = -1;
		spywid = null;
		ywid = 0;
		FRhHb  = null;
		Hhnbid = null;
		//两个表弟清空
		form_sbr.getForm().reset();
		form_yw.getForm().reset();
		
		//家庭成员清空
		hcyGrid.store.removeAll();
		//迁入登记清空
		ywGrid.store.removeAll();
		//户地信息
		wsCdsdxx.removeAll();
		
		SetReadOnly(form_yw, 'xm', false);
		SetReadOnly(form_yw, 'xb', false);
		SetReadOnly(form_yw, 'csrq', false);
		SetReadOnly(form_yw, 'zqzbh', false);
		
		// 20190918 add by zjm 名族 身份证号  曾用名  
		SetReadOnly(form_yw, 'mz', false);
		SetReadOnly(form_yw, 'gmsfhm', false);
		SetReadOnly(form_yw, 'cym', false);
		zqz_check = false;
		qyz_check = false;
		
		Ext.getCmp('bbtnAdd').enable();
//		Ext.getCmp('bbtnDelete').enable();
		
		/**
				有效期起始日期默认失效
				选择了出生日期后才可以选择
				并且出生日期/有效期限起始日期不能大于当前日期
		 */
		SetReadOnly(form_yw, 'yxqxqsrq', true);
		form_yw.getForm().findField("csrq").setMaxValue(Ext.util.Format.date(new Date(),'Ymd'));
		form_yw.getForm().findField("yxqxqsrq").setMaxValue(Ext.util.Format.date(new Date(),'Ymd'));
		
	}
	
	function delRyxx(){
		//删除人口
		
		var r = ywGrid.getSelectionModel().getSelected();
		if(r){
			ywGrid.store.remove(r);
			
			var index = ywGrid.store.getCount() - 1;
			ywGrid.fireEvent("rowclick",ywGrid,index);
			ywGrid.getSelectionModel().selectRange(index,index);
			
			if(ywGrid.store.getCount() == 1){
				Ext.getCmp('bbtnDelete').setDisabled(true);
			}
			
		}
	}
	
	function addRyxx(){
		ywid++;
		var data = {ywid: ywid};

		var old = form_yw.getForm().getValues();

	    var FQcdSx = old.qcdssxq;
	    var FQcdXz = old.qcdxz;
	    var FQrlb = old.qrlb;
	    var FQyzBh = old.qyzbh;
	    var FZqzbh = old.zqzbh;
	    var FHb = old.hb;
	    var FBdfw = old.bdfw;
	    var FQrqHb = old.qrqhb;
	    
	    /**
			设置户别
	     */
		/*if (Hhnbid !=null && FRhHb !=null)
			data.hb = FRhHb;
		else
			data.hb = FHb;
		*/
		data.hb = hb;
		data.mz = '01'; //默认是汉族
		data.xxjb = '5';
		data.rylb = '1';
		data.qcdssxq = FQcdSx;
		data.qcdxz = FQcdXz;
		data.qrlb =  FQrlb;
		data.qyzbh = FQyzBh;
		data.zqzbh = FZqzbh;
		data.bdfw = FBdfw;
		data.qrqhb = FQrqHb;
		
		var rr = new ywGrid.store.reader.recordType(data, ywid);
		rr.set('_sel', '1');
		ywGrid.store.add([rr]);
		
		var index = ywGrid.store.getCount() - 1;
		ywGrid.fireEvent("rowclick",ywGrid,index);
		ywGrid.getSelectionModel().selectRange(index,index);
		
		if(ywid > 1){
			Ext.getCmp('bbtnDelete').setDisabled(false);
		}else{
			Ext.getCmp('bbtnDelete').setDisabled(true);
		}
		
	}
	
	function initSqry(qrywType){
		//初始化随迁人员
		//新增一个随迁人员
		if(hzywjo_list && hzywjo_list.length>0){
			Ext.each(hzywjo_list, function(data){
				ywid++;
				var item = null;
				if(qrywType=="rhWindow"){
					item = {
							ywid: ywid,
							xm: data.bsqrxm,
							cym: data.cym,
							mz: data.mz,
							xb: data.xb,
							csrq: data.csrq,
							csdssxq:data.csdssxq,
							jhryxm:data.jhryxm,
							jhrygmsfhm:data.jhrygmsfhm,
							jhryjhgx:data.jhryjhgx,
							gmsfhm:data.bsqrsfz,
							dhhm: data.dhhm,
							jgssxq:data.jgssxq,
							yhzgx: data.yhzgx,
							zqzbh: data.zqzbh,
							xxjb: '5',
							rylb: '1',
							qcdssxq:data.qcdssxq,
							sg:data.sg,
							zy:data.zy,
							zylb:data.zylb,
							fwcs:data.fwcs,
							whcd:data.whcd,
							qrqhb:data.qrqhb,
							cxfldm:data.cxfldm,
							nyzyrklhczyydm:data.nyzyrklhczyydm,
							zyjszc_pdbz:data.zyjszc_pdbz,
							jndj_pdbz:data.jndj_pdbz,
							ncjdzzyxbys_pdbz:data.ncjdzzyxbys_pdbz,
							jjqx_pdbz:data.jjqx_pdbz,
							zczjyhjzwnys_pdbz:data.zczjyhjzwnys_pdbz,
							ncjsbtcxy_pdbz:data.ncjsbtcxy_pdbz,
							qyldyy:data.qyldyy,
							qyzbh:data.qyzbh,
							qrlb:data.qrlb,
							qcdxz:data.qcdxz
					};
				}else{
					item = {
							ywid: ywid,
							xm: data.bsqrxm,
							cym: data.cym,
							mz: data.mz,
							xb: data.xb,
							csrq: data.csrq,
							dhhm: data.dhhm,
							gmsfhm: data.bsqrsfz,
							yhzgx: data.yhzgx,
							zqzbh: data.zqzbh,
							xxjb: '5',
							rylb: '1',
							qrlb:data.qrlb,
							/**
								2018.10.23
								新增	刁杰
								迁出地省县 qcdssxq
								姓名 bsqrxm
								,民族bsqrmz
								性别 BSQRXB
								出生日期 BSQRcsrq 
								身高sg
								职业 zy
								职业类别 zylb
								服务处所 fwcs
								文化程度 whcd
								电话号码  BSQRSJHM
								迁入前户别 qrqhb
								历史城乡代码 cxfldm
								落户城镇原因 NYZYRKLHCZYYDM
								具有专业职称落户城镇 ZYJSZC_PDBZ
								具有技能等级落户城镇 JNDJ_PDBZ
								高等院校毕业落户城镇 NCJDZZYXBYS_PDBZ
								举家迁徙落户城镇 JJQX_PDBZ
								就业和居住五年落户城镇 ZCZJYHJZWNYS_PDBZ
								退出现役落户城镇 NCJSBTCXY_PDBZ
								迁移（流动）原因 QYLDYY
								准迁证编号 zqzbh
								迁移证编号 QYZBH
								线上要求新增字段,原有字段不变
							 */
							qcdssxq:data.qcdssxq,
							csdssxq:data.csdssxq,
							jgssxq:data.jgssxq,
							sg:data.sg,
							zy:data.zy,
							zylb:data.zylb,
							fwcs:data.fwcs,
							whcd:data.whcd,
							qrqhb:data.qrqhb,
							cxfldm:data.cxfldm,
							nyzyrklhczyydm:data.nyzyrklhczyydm,
							zyjszc_pdbz:data.zyjszc_pdbz,
							jndj_pdbz:data.jndj_pdbz,
							ncjdzzyxbys_pdbz:data.ncjdzzyxbys_pdbz,
							jjqx_pdbz:data.jjqx_pdbz,
							zczjyhjzwnys_pdbz:data.zczjyhjzwnys_pdbz,
							ncjsbtcxy_pdbz:data.ncjsbtcxy_pdbz,
							qyldyy:data.qyldyy,
							qyzbh:data.qyzbh,
							qcdxz:data.qcdxz
					};
				}
				/**
				 * add by zjm 201907122中间表增加字段 
				 * 宗教信仰、婚姻状况、兵役情况、血型、签发机关、证件类别、有效期限起始日期、有效期限截止日期
				 * 
				 */
				if(!Gnt.util.isEmpty(data.bdfw)){
					item.bdfw = data.bdfw;
				}
				if(!Gnt.util.isEmpty(data.zjxy)){
					item.zjxy = data.zjxy;
				}
				if(!Gnt.util.isEmpty(data.hyzk)){
					item.hyzk = data.hyzk;
				}
				if(!Gnt.util.isEmpty(data.byzk)){
					item.byzk = data.byzk;
				}
				if(!Gnt.util.isEmpty(data.xx)){
					item.xx = data.xx;
				}
				if(!Gnt.util.isEmpty(data.qfjg)){
					item.qfjg = data.qfjg;
				}
				if(!Gnt.util.isEmpty(data.zjlb)){
					item.zjlb = data.zjlb;
				}
				if(!Gnt.util.isEmpty(data.yxqxqsrq)){
					item.yxqxqsrq = data.yxqxqsrq;
				}
				if(!Gnt.util.isEmpty(data.yxqxjzrq)){
					item.yxqxjzrq = data.yxqxjzrq;
				}
				/*
				 * add by zjm 20190912 
				 * 户政跳转迁入，户籍补录，出生业务时，中间表新增csdxz，xxjb，rylb，bz
				 * 
				 */
				if(!Gnt.util.isEmpty(data.csdxz)){
					item.csdxz = data.csdxz;
				}
				if(!Gnt.util.isEmpty(data.xxjb)){
					item.xxjb = data.xxjb;
				}
				if(!Gnt.util.isEmpty(data.rylb)){
					item.rylb = data.rylb;
				}
				if(!Gnt.util.isEmpty(data.bz)){
					item.bz = data.bz;
				}
				//add by zjm 20190927  增加中间表   集体户种类 带值
				if(!Gnt.util.isEmpty(data.jthzl)){
					item.jthzl = data.jthzl;
				}
				if(!Gnt.util.isEmpty(data.rkzp)){
					item.zp = data.rkzpBase64;
					rkzpMap[data.rynbid] = data.rkzpBase64;
				}
				/*add by zjm 20200709 
				 * 监护人一证件类型、监护人一证件号码、监护人二身份证、监护二证件类型、监护人二证件号码、监护人二监护关系、父亲证件类型、
				 * 父亲证件号码、母亲证件类型、母亲证件号码、父亲外文姓、父亲外文名、母亲外文姓、母亲外文名、监护人一外文姓、
				 * 监护人一外文名、监护人二外文姓、监护人二外文名。需代值。
				*/
				if(!Gnt.util.isEmpty(data.jhryzjlb)){
					item.jhryzjzl = data.jhryzjlb;
				}
				if(!Gnt.util.isEmpty(data.jhryzjhm)){
					item.jhryzjhm = data.jhryzjhm;
				}
				if(!Gnt.util.isEmpty(data.jhregmsfhm)){
					item.jhregmsfhm = data.jhregmsfhm;
				}
				if(!Gnt.util.isEmpty(data.jhrezjlb)){
					item.jhrezjzl = data.jhrezjlb;
				}
				if(!Gnt.util.isEmpty(data.jhrezjhm)){
					item.jhrezjhm = data.jhrezjhm;
				}
				if(!Gnt.util.isEmpty(data.fqzjlb)){
					item.fqzjzl = data.fqzjlb;
				}
				if(!Gnt.util.isEmpty(data.fqzjhm)){
					item.fqzjhm = data.fqzjhm;
				}	
				if(!Gnt.util.isEmpty(data.mqzjlb)){
					item.mqzjzl = data.mqzjlb;
				}
				if(!Gnt.util.isEmpty(data.mqzjhm)){
					item.mqzjhm = data.mqzjhm;
				}
				if(!Gnt.util.isEmpty(data.mqwwx)){
					item.mqwwx = data.mqwwx;
				}
				if(!Gnt.util.isEmpty(data.mqwwm)){
					item.mqwwm = data.mqwwm;
				}
				if(!Gnt.util.isEmpty(data.fqwwx)){
					item.fqwwx = data.fqwwx;
				}
				if(!Gnt.util.isEmpty(data.fqwwm)){
					item.fqwwm = data.fqwwm;
				}
				if(!Gnt.util.isEmpty(data.jhrywwx)){
					item.jhrywwx = data.jhrywwx;
				}
				if(!Gnt.util.isEmpty(data.jhrywwm)){
					item.jhrywwm = data.jhrywwm;
				}
				if(!Gnt.util.isEmpty(data.jhrewwx)){
					item.jhrewwx = data.jhrewwx;
				}
				if(!Gnt.util.isEmpty(data.jhrewwm)){
					item.jhrewwm = data.jhrewwm;
				}
				if(!Gnt.util.isEmpty(data.jhrejhgx)){
					item.jhrejhgx = data.jhrejhgx;
				}
				if(!Gnt.util.isEmpty(hzywjo.jhrexm)){
					item.jhrexm = data.jhrexm;
				}
				var rr = new ywGrid.store.reader.recordType(item, item.ywid);
				if(rhFlag == 1){
					rr.set("hb",hb);
					SetReadOnly(form_yw, 'hb', true);
				}else if(rhFlag == 2){//立户的时候，户别从中间表带值
					if(!Gnt.util.isEmpty(data.hb)){
						rr.set("hb",data.hb);
						SetReadOnly(form_yw, 'hb', true);
					}
				}
				rr.set('_sel', '1');
				ywGrid.store.add([rr]);
				
				if(rr.data.csrq){
	    			SetReadOnly(form_yw, 'yxqxqsrq', false);
	    		}
				
			});
			if(rhFlag != 0&&user.flag!='1'){
				Ext.getCmp('saveId').enable();
			}
			
		}else{
			ywid++;
			var rr = new ywGrid.store.reader.recordType({ywid: ywid}, ywid);
			
			/**
				2018.07.17
				新增	刁杰
				选择无需准迁证入户时:
				户别默认入户户别
				信息级别默认五级
			 */
			if(rhFlag == 1){
				rr.set("hb",hb);
				SetReadOnly(form_yw, 'hb', true);
			}
			rr.set("mz","01");
			rr.set("xxjb",'5');
			rr.set("rylb",'1');
			
			/**
			
			 */
			if(rhFlag != 0&&user.flag!='1'){
				rr.set("zqzbh","皖");
				Ext.getCmp('saveId').enable();
				
			}
			
			rr.set('_sel', '1');
			ywGrid.store.add([rr]);
		}
	}
	
	function initSbr(){
		//form_sbr
		if(hzywjo){
			form_sbr.getForm().setValues({
				sbryxm: hzywjo.sqrxm,
				sbrgmsfhm: hzywjo.sqrsfz
			});
		}
	}
	
	var rhWindow = new Gnt.ux.SelectRh({
		returnTitleText : '迁入入户——户地信息查询',
		qydjFlag:true,
		callback: function(optype, hcyList, selectHcy, hxx,jsonList,hzywjo){
			initYw();
			initSqry("rhWindow");
			initSbr();
			new_ssxq = hxx.ssxq;
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
					Hhnbid = data.hhnbid;
			}
			// 20190918 add by zjm 姓名 性别 出生日期 名族 身份证号  曾用名  
			if(hzywjo){
				SetReadOnly(form_yw, 'xm', true);
				SetReadOnly(form_yw, 'xb', true);
				SetReadOnly(form_yw, 'csrq', true);
				SetReadOnly(form_yw, 'mz', true);
				SetReadOnly(form_yw, 'gmsfhm', true);
				//SetReadOnly(form_yw, 'cym', true);
			}
			(function(){
				ywGrid.fireEvent("rowclick",ywGrid,0);
				ywGrid.getSelectionModel().selectRange(0,0);
			}).defer(100);
		},
		rowclickBack : function(optype, data){
			hb = data.hb;
		}
	});
	
	var rhbhWindow = new Gnt.ux.SelectRhByHu({
		returnTitleText : '迁入入户——集体户地信息查询',
		select_type : 1,		//是否获取选择人员的信息,不开启默认选择户主,没有户主时选择户成员中第一人
		callback: function(optype, hcyList, selectHcy, hxx){
			
			initYw();
			initSqry();
			initSbr();
			
			new_ssxq = hxx.ssxq;

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
					Hhnbid = data.hhnbid;
			}
			// 20190918 add by zjm 姓名 性别 出生日期 名族 身份证号  曾用名  
			if(hzywjo){
				SetReadOnly(form_yw, 'xm', true);
				SetReadOnly(form_yw, 'xb', true);
				SetReadOnly(form_yw, 'csrq', true);
				SetReadOnly(form_yw, 'mz', true);
				SetReadOnly(form_yw, 'gmsfhm', true);
				//SetReadOnly(form_yw, 'cym', true);
			}
			(function(){
				ywGrid.fireEvent("rowclick",ywGrid,0);
				ywGrid.getSelectionModel().selectRange(0,0);
			}).defer(100);
			
			
		},
		rowclickBack : function(optype, data){
			/**
				2019.01.18
				修改	刁杰
				户别强制居民集体户(7)
			 */
			hb = "7";
		}
	});
	
	var lhWindow = new Gnt.ux.SelectBzdz({
		//选择立户信息回调
		callback: function(optype, lhdz){
			
			initYw();
			initSqry();
			initSbr();
			new_ssxq = lhdz.ssxq;
			select_optype_index = 2;
			//点击确认迁出后，执行此方法
			Ext.getCmp('card').getLayout().setActiveItem(1);
			v.doLayout();
			select_lhdz = lhdz;
			select_optype_index = 2;
			select_lhdz = lhdz;
			// 20190918 add by zjm 姓名 性别 出生日期 名族 身份证号  曾用名  
			if(hzywjo){
				SetReadOnly(form_yw, 'xm', true);
				SetReadOnly(form_yw, 'xb', true);
				SetReadOnly(form_yw, 'csrq', true);
				SetReadOnly(form_yw, 'mz', true);
				SetReadOnly(form_yw, 'gmsfhm', true);
				//SetReadOnly(form_yw, 'cym', true);
			}
			
			(function(){
				ywGrid.fireEvent("rowclick",ywGrid,0);
				ywGrid.getSelectionModel().selectRange(0,0);
			}).defer(500);
		}
	});
	
	var sprhWindow = new Gnt.ux.SelectSprh({
		qydjFlag:true,
		kdqqy: '1',
		callback:function(optype, qrryList, spyw,lhFlag){
			initYw();
			select_optype_index = 0;
			
			var data = {
					ssxq: spyw.qrdqx,
				    xzjd: spyw.qrdxzjd,
				    pcs: spyw.qrdpcs,
				    jlx: spyw.qrdjlx,
				    mlph: spyw.qrdmlph,
				    mlxz: spyw.qrdz,
				    jcwh: spyw.qrdjwh,
				    hlx: spyw.qrdhlx,
				    bzid: spyw.bzdz,
					bzdzid: spyw.bzdzid,
					bzdzx: spyw.bzdzx,
					bzdzy: spyw.bzdzy,
					bzdzst: spyw.bzdzst
			};
			
			if(lhFlag){
				//入户
				select_lhdz = null;
			}else{
				//立户
				select_lhdz = data;
			}
			
			new_ssxq = spyw.qrdqx;
			
			var rr = new wsCdsdxx.reader.recordType(data, data[wsCdsdxx.pkname]);
			wsCdsdxx.add([rr]);
		    var sXm = spyw.xm;
		    var sGmsfhm = spyw.gmsfhm;
			
			//初始化申报人
			form_sbr.getForm().setValues({
				sbsj: spyw.djsj,
				sbryxm: spyw.sqrxm,
				sbrgmsfhm: spyw.sqrgmsfhm
			});

			//初始化迁出人信息
			var sKdsqy_qyz =null;
			
			curIndex = 0;
			ywid = 0;
			spywid = spyw.spywid;
			Hhnbid = spyw.qrdhhid;
			//点击确认迁出后，执行此方法
			Ext.getCmp('card').getLayout().setActiveItem(1);
			v.doLayout();
			
			//户成员初始化
			hcyGrid.store.removeAll()
			if(Hhnbid){
				var store = hcyGrid.store;
				store.baseParams = {
						pzlb: store.pzlb,
						hhnbid: Hhnbid
				};
				store.load({params:{start:0, limit:20}});
			}
			
			var store = ywGrid.store;
			store.removeAll();
			//人员选择回调：审批业务，迁入人员
			for(var i=0;i<qrryList.length;i++){
				var data = qrryList[i];
				
				data._sel = "1";
				data.yhzgx = qrryList[i].ysqrgx;
				data.qcdssxq = qrryList[i].yhkqx;
				data.qcdxz = qrryList[i].yhkszd;
				data.qrqhb = qrryList[i].hkxz;
				data.hb = qrryList[i].qrhhb;
				SetReadOnly(form_yw, 'hb', true);
				data.zqzbh = qrryList[i].zjbh;
				data.spywid = qrryList[i].spywid;
				data.qyzbh = qrryList[i].kdqqy_qyzbh;
				data.cxfldm = qrryList[i].kdqqy_lscxfldm;
				data.qyldyy = qrryList[i].kdqqy_qyldyy;
				data.nyzyrklhczyydm = qrryList[i].nyzyrklhczyydm;
				if(hzywjo_list && hzywjo_list.length>0){
					Ext.each(hzywjo_list, function(temp){
						if(qrryList[i].gmsfhm==temp.bsqrsfz){
							if(!Gnt.util.isEmpty(temp.csdxz)){
								data.csdxz = temp.csdxz;
							}
							if(!Gnt.util.isEmpty(temp.xxjb)){
								data.xxjb = temp.xxjb;
							}else{
								data.xxjb = '5';
							}
							if(!Gnt.util.isEmpty(temp.rylb)){
								data.rylb = temp.rylb;
							}else{
								data.rylb = '1';
							}
							if(!Gnt.util.isEmpty(temp.bz)){
								data.bz = temp.bz;
							}
							if(!Gnt.util.isEmpty(temp.jthzl)){
								data.jthzl = temp.jthzl;
							}
							if(!Gnt.util.isEmpty(temp.qcdxz)){
								data.qcdxz = temp.qcdxz;
							}
							if(!Gnt.util.isEmpty(temp.qrlb)){
								data.qrlb = temp.qrlb;
							}
						}else{
							data.xxjb = '5';
							data.rylb = '1';
						}
					});
				}else{
					/**
					信息级别默认:5
					人员类别默认:1
					 */
					data.xxjb = '5';
					data.rylb = '1';
				}
				
				//处理人员ID
				data.ryid = qrryList[i].ryid;
				if(data.ryid=="0" || data.ryid==0)
					data.ryid = null;
				
				sKdsqy_qyz =qrryList[i].kdqqy_qyzbh;
				
				//只有主迁人员才填入迁入类别
//				if (qrryList[i].xm == sXm && qrryList[i].gmsfhm == sGmsfhm)
//					data.qrlb = spyw.sqqrly;
				//data.qrlb = spyw.sqqrly;//modify by zjm 20190823 改为所有人的迁入类别都填
				data.ywid = ywid;
				/**
					BugFree 384
					户政平台跳转的迁入业务,随迁人复制粘贴了主迁人的信息
					
				 */
				//if(hzywjo){
				if(hzywjo_list && hzywjo_list.length > 0){
					Ext.each(hzywjo_list, function(hzywjo){
						//alert("身份证号码: " + data.gmsfhm + " 中间表: " + hzywjo.gmsfhm);
						if(data.gmsfhm == hzywjo.bsqrsfz){
							if(!hzywjo.qcdssxq.startWith(sysQcdq)){
								data.qyzbh = hzywjo.qyzbh;
							}
							if(!Gnt.util.isEmpty(hzywjo.jhrexm)){
								data.jhrexm = hzywjo.jhrexm;
							}
							if(!Gnt.util.isEmpty(hzywjo.jhryjhgx)){
								data.jhryjhgx = hzywjo.jhryjhgx;
							}
							if(!Gnt.util.isEmpty(hzywjo.jhrygmsfhm)){
								data.jhrygmsfhm = hzywjo.jhrygmsfhm;
							}
							if(!Gnt.util.isEmpty(hzywjo.fwcs)){
								data.fwcs = hzywjo.fwcs;
							}
							if(!Gnt.util.isEmpty(hzywjo.whcd)){
								data.whcd = hzywjo.whcd;
							}
							if(!Gnt.util.isEmpty(hzywjo.zy)){
								data.zy = hzywjo.zy;
							}
							if(!Gnt.util.isEmpty(hzywjo.zylb)){
								data.zylb = hzywjo.zylb;
							}
							if(!Gnt.util.isEmpty(hzywjo.sg)){
								data.sg = hzywjo.sg;
							}
							if(!Gnt.util.isEmpty(hzywjo.dhhm)){
								data.dhhm = hzywjo.dhhm;
							}
							if(!Gnt.util.isEmpty(hzywjo.qyldyy)){
								data.qyldyy = hzywjo.qyldyy;
							}
							if(!Gnt.util.isEmpty(hzywjo.cxfldm)){
								data.cxfldm = hzywjo.cxfldm;
							}
							if(!Gnt.util.isEmpty(hzywjo.nyzyrklhczyydm)){
								data.nyzyrklhczyydm = hzywjo.nyzyrklhczyydm;
							}
							if(!Gnt.util.isEmpty(hzywjo.zyjszc_pdbz)){
								data.zyjszc_pdbz = hzywjo.zyjszc_pdbz;
							}
							if(!Gnt.util.isEmpty(hzywjo.jndj_pdbz)){
								data.jndj_pdbz = hzywjo.jndj_pdbz;
							}
							if(!Gnt.util.isEmpty(hzywjo.ncjdzzyxbys_pdbz)){
								data.ncjdzzyxbys_pdbz = hzywjo.ncjdzzyxbys_pdbz;
							}
							if(!Gnt.util.isEmpty(hzywjo.jjqx_pdbz)){
								data.jjqx_pdbz = hzywjo.jjqx_pdbz;
							}
							if(!Gnt.util.isEmpty(hzywjo.zczjyhjzwnys_pdbz)){
								data.zczjyhjzwnys_pdbz = hzywjo.zczjyhjzwnys_pdbz;
							}
							if(!Gnt.util.isEmpty(hzywjo.ncjsbtcxy_pdbz)){
								data.ncjsbtcxy_pdbz = hzywjo.ncjsbtcxy_pdbz;
							}
							if(!Gnt.util.isEmpty(hzywjo.csdssxq)){
								data.csdssxq = hzywjo.csdssxq;
							}
							if(!Gnt.util.isEmpty(hzywjo.jgssxq)){
								data.jgssxq = hzywjo.jgssxq;
							}
							if(!Gnt.util.isEmpty(hzywjo.jhryxm)){
								data.jhryxm = hzywjo.jhryxm;
							}
							/**
							 * add by zjm 201907122中间表增加字段 
							 * 宗教信仰、婚姻状况、兵役情况、血型、签发机关、证件类别、有效期限起始日期、有效期限截止日期
							 * 
							 */
							if(!Gnt.util.isEmpty(hzywjo.bdfw)){
								data.bdfw = hzywjo.bdfw;
							}
							if(!Gnt.util.isEmpty(hzywjo.zjxy)){
								data.zjxy = hzywjo.zjxy;
							}
							if(!Gnt.util.isEmpty(hzywjo.hyzk)){
								data.hyzk = hzywjo.hyzk;
							}
							if(!Gnt.util.isEmpty(hzywjo.byzk)){
								data.byzk = hzywjo.byzk;
							}
							if(!Gnt.util.isEmpty(hzywjo.xx)){
								data.xx = hzywjo.xx;
							}						
							if(!Gnt.util.isEmpty(hzywjo.qfjg)){
								data.qfjg = hzywjo.qfjg;
							}						
							if(!Gnt.util.isEmpty(hzywjo.zjlb)){
								data.zjlb = hzywjo.zjlb;
							}			
							if(!Gnt.util.isEmpty(hzywjo.yxqxqsrq)){
								data.yxqxqsrq = hzywjo.yxqxqsrq;
							}
							if(!Gnt.util.isEmpty(hzywjo.yxqxjzrq)){
								data.yxqxjzrq = hzywjo.yxqxjzrq;
							}
							if(!Gnt.util.isEmpty(hzywjo.yhzgx)){
								data.yhzgx = hzywjo.yhzgx;
							}
							if(!Gnt.util.isEmpty(hzywjo.cym)){
								data.cym = hzywjo.cym;
							}
						}
						
					});
				}

				var rr = new store.reader.recordType(data, ywid);
				/**
					有出生日期则可以选择有效期限起始日期
				 */
				if(rr.data.csrq){
	    			SetReadOnly(form_yw, 'yxqxqsrq', false);
	    		}
				/**
					准迁证/电子准迁证区分
				 */
				if(data.kdqqy_qcdqbm&&user.flag!='1'){
					/**
						电子准迁证
						不让核验,并且可以直接保存
					 */
					Ext.getCmp('saveId').enable();
					//Ext.getCmp('ZqzButtonId').disable();
				}else{
					
				}
				
				store.add([rr]);
				ywid++;
			}
			
			(function(){
				SetReadOnly(form_yw, 'xm', true);
				SetReadOnly(form_yw, 'xb', true);
				SetReadOnly(form_yw, 'csrq', true);
				SetReadOnly(form_yw, 'zqzbh', true);
				// 20190918 add by zjm 名族 身份证号  曾用名  
				if(hzywjo){
					SetReadOnly(form_yw, 'mz', true);
					SetReadOnly(form_yw, 'gmsfhm', true);
					//SetReadOnly(form_yw, 'cym', true);
				}
				
				/**
					2018.10.23
					修改	刁杰
					线上要求 迁出地省县 不可修改
				 */
				SetReadOnly(form_yw, 'qcdssxq', true);
				
				Ext.getCmp('bbtnAdd').disable();
				Ext.getCmp('bbtnDelete').disable();
				ywGrid.fireEvent("rowclick",ywGrid,0);
				ywGrid.getSelectionModel().selectRange(0,0);
				
			}).defer(200);
		}
	});

	var rhfl_Window = new Gnt.ux.RhflWindow({
		returnTitleText : '人户分离信息编辑',
		callback: function(optype, lhdz){
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
								text:'已有准迁证入户（审批入户）',
								id:'sprhBtn',
								minWidth:75,
								handler:function(){
									rhFlag = 0;
									sprhWindow.show();
									sprhWindow.setHzyw(hzywjo);
								}
							}),
							new Ext.Button({
								text:'无需准迁证迁入（入户）',
								id:'qrrhBtn',
								minWidth:75,
								handler:function(){
									rhFlag = 1;
									rhWindow.show();
									rhWindow.setHzyw(hzywjo);
								}
							}),
							new Ext.Button({
								text:'无需准迁证迁入集体户（入户）',
								id:'qrjthrhBtn',
								disabled:true,
								minWidth:75,
								handler:function(){
									rhFlag = 1;
									rhbhWindow.show();
									rhbhWindow.setHzyw(hzywjo);
								}
							}),
							new Ext.Button({
								text:'无需准迁证迁入（立户）',
								id:'lhrhBtn',
								minWidth:75,
								handler:function(){
									rhFlag = 2;
									lhWindow.show();
									lhWindow.setHzyw(hzywjo);
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
	
	ywGrid = new Gnt.ux.SjpzGrid({
		pkname: 'ywid',
		pzlb: '10023',
		region:'north',
    	title: '迁入人员列表',
    	height:200,
    	showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){
	   			selRes = g.store.getAt(rowIndex);
    			curIndex = rowIndex;
    		
    			if(editPanelCard.getLayout().activeItem.id=="c2"){
        			editPanelCard.getLayout().setActiveItem(0);
        			editPanelCard.doLayout();
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
    			initBdfw(selRes);
    			var validBdfw = form_yw.getForm().findField("bdfw").store.data;
				var bdfwFlag = false
				if(selRes&&selRes.data.bdfw){
					Ext.each(validBdfw.keys, function(data){
						if(data==selRes.data.bdfw){
							bdfwFlag = true;
							//newlist.push(data);
						}
					});
					if(!bdfwFlag){
						form_yw.getForm().setValues({bdfw:""});
						selRes.data.bdfw="";
					}else{
						form_yw.getForm().setValues({bdfw:selRes.data.bdfw});
					}
				}
    		}
		}
	});
	
	form_yw = new Gnt.ux.SjpzForm({
		title: '迁入信息登记',
		closable: false,
		region:'center',
		height:330,
		pzlb: '10023',
		labelWidth :  160,
		cols:2,
		//选择户成员用
//		bindViewGridHCY:hcyGrid,
		bindStore:ywGrid.store,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:ywGrid,
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
				Gnt.validpoxb(form_yw,selRes,res,true);
				//add by zjm 20190710 配偶下拉框选中人员后，配偶身份证自动带入
				this.getForm().setValues({pogmsfhm:res.data.data});
				selRes.set("pogmsfhm",res.data.data);
			}
			
			return;
		},
        fieldFocus:function(field){
        	var f = field.findParentByType("sjpz_form");
        	if(field.name=='bdfw'){
        		if(xt_bssqb_list==null){
        			showInfo("没有找到市区配置信息，无法过滤变动范围！");
        			return;
        		}
        		var qrlb = f.getForm().findField("qrlb");
        		var qcdssxq = f.getForm().findField("qcdssxq");

    			
    			var old_ssxq = qcdssxq.getValue();
    			
    			var fwflag = true;
    			Ext.each(xt_bssqb_list, function(data){
    				if(data.qhdm==old_ssxq || data.qhdm==new_ssxq){
    					if(data.sqbz=='0')
    						fwflag = false;
    				}
    			});
    			
    			field.filter =function(data){
    				if(qrlb){
    					//1、	迁入类别为 0344 0345 0346 0347 0371 0381   变动范围是 51省外乡村、 52省外城镇、 53省外城市 （此规则优先于下面其他规则）
    					var v = qrlb.getValue();
    					if(v=='0344' || v=='0345' || v=='0346' || v=='0347' || v=='0371' || v=="0381"){
    						if(data[0]=='51' || data[0]=='52' || data[0]=='53')
    							return true;
    						else
    							return false;
    					}
    				}
    				
    				if(qcdssxq){
    					//2同市同区县内迁移变动范围是 21 区(县)内他所(乡) 
    					if(old_ssxq==new_ssxq){
    						if(data[0]=='21')
    							return true;
    						else
    							return false;
    					}
    					
    					//同市，跨区县 sqbz(市区标志)都为1的情况下变动范围是31市内城镇32	市内郊区，否则为 33	市外乡村34市外城镇
    					if(old_ssxq.length>4 && new_ssxq.substring(0,4)==old_ssxq.substring(0,4) && old_ssxq!=new_ssxq){
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
    					}
    				}
    				
    				//7、	迁出地为710000 台湾省 变动范围62台湾地区
    				if(old_ssxq.length>4 && old_ssxq=='710000'){
    					if(data[0]=='62'){
    						return true;
    					}else{
    						return false;
    					}
    				}
    				
    				//8、	迁出地为 810000 820000 变动范围为 61港、澳地区
    				if(old_ssxq.length>4 && (old_ssxq=='810000' || old_ssxq=='820000') ){
    					if(data[0]=='61'){
    						return true;
    					}else{
    						return false;
    					}
    				}
    				
    				//4、	迁出地省市县区为省内市外的 变动范围是 41省内乡村、42省内城镇、43省内城市
    				if(old_ssxq.length>4 && new_ssxq.substring(0,2)==old_ssxq.substring(0,2) && new_ssxq.substring(0,4)!=old_ssxq.substring(0,4)){
    					if(data[0]=='41' || data[0]=='42' ||  data[0]=='43'){
    						return true;
    					}else{
    						return false;
    					}
    				}
    				
    				//5、	迁出地省市县区为国内省外的（不包括港澳台） 变动范围是 51省外乡村、 52省外城镇、 53省外城市
    				if(old_ssxq.length>4 && old_ssxq.substring(0,3)!='010' && new_ssxq.substring(0,2)!=old_ssxq.substring(0,2)){
    					if(data[0]=='51' || data[0]=='52' ||  data[0]=='53'){
    						return true;
    					}else{
    						return false;
    					}
    				}
    				
    				//6、	迁出地为国外的 如010004阿富汗 变动范围为71国外
    				if(old_ssxq.length>4 && old_ssxq.substring(0,3)=='010'){
    					if(data[0]=='71'){
    						return true;
    					}else{
    						return false;
    					}
    				}
    				
    				return true;
    			}
    			field.reloadDict();
    		
        	}
        	
        	Gnt.filterField(field);
        	
        },
        fieldBlur:function(field){
        	var form = form_yw.getForm();
        	if(field.name=='csrq'){
        		if(form.findField("csrq").getValue()){
        			SetReadOnly(form_yw, 'yxqxqsrq', false);
        		}else{
        			SetReadOnly(form_yw, 'yxqxqsrq', true);
        			/*
        			form.findField("yxqxqsrq").setValue("");
        			form.findField("yxqxjzrq").setValue("");
        			*/
        			form_yw.fieldSetValue(form.findField("yxqxqsrq"),"");
        			form_yw.fieldSetValue(form.findField("yxqxjzrq"),"");
        			
        		}
        	}
        	
        	if(field.name=='yxqxqsrq'){
        		if(field.value && form.findField("csrq").getValue()){
        			
        			if(form.findField("csrq").getValue() > form.findField("yxqxqsrq").getValue()){
        				showInfo("有效期限起始日期不能早于出生日期！");
        				form.findField("yxqxqsrq").setValue("");
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
        	
        	if(field.name=='zjlb'){
        		if(form.findField("csrq").getValue()){
        			var csrq = form.findField("csrq").value;
        			var nl = Gnt.date.jsGetAge(csrq);
        			
        			if(nl < 16 && form.findField("zjlb").getValue() == 1){
        				showInfo("16周岁以下没有一代证！");
        				form.findField("zjlb").setValue("");
        			}
        		}
        	}
        	
        	//if(field.name=='hyzk' && r.isModified("hyzk")){
        	if(field.name=='hyzk'){
        		Gnt.validpo(form_yw,field);
        	}
        	//if(field.name=='pogmsfhm' && r.isModified("pogmsfhm")){
        	if(field.name=='pogmsfhm'){
        		Gnt.validposfzh(form_yw,field,true);
        	}
        },
        fieldChange:function(field){
        	var f = field.findParentByType("sjpz_form");
        	
        	if(field.name=='qrlb'){
        		if(f.getForm().findField("bdfw").getValue()){
        			f.getForm().findField("bdfw").setValue('');
        		}
        	}else if(field.name=='qcdssxq'){
        		if(f.getForm().findField("bdfw").getValue()){
        			f.getForm().findField("bdfw").setValue('');
        		}
        	}
        	
        	if(field.name=='qyzbh'){
        		/**
	    			2018.10.18
	    			修改	刁杰
	    			编号长度2.0版 线上要求准迁证编号长度11位
	    			一中文(三位) 八位数字
	    		 */
        		if(Gnt.ux.getStrLength(field.getValue()) > 11){
        			showErr("【迁移证编号】长度超出限制！",function(){
        				f.getForm().findField("qyzbh").setValue('');
        			});
        		}
        		/*
        		var reg=/^[\u4e00-\u9fa5]+\d{8}$/;
				if(!reg.test(field.getValue())){
					showErr("【迁移证编号】格式不正确！",function(){
						var qwdssxq = f.getForm().findField("qwdssxq").getValue();
						getXZqhJx(qwdssxq,f,true);
					});
				}
        		 */
        	}
        	
        	if(field.name=='zqzbh'){
        		
        		if(Gnt.ux.getStrLength(field.getValue()) > 11){
        			showErr("【准迁证编号】长度超出限制！",function(){
        				f.getForm().findField("zqzbh").setValue('皖');
					});
        		}
        		/*
        		var reg=/^[\u4e00-\u9fa5]+\d{8}$/;
				if(!reg.test(field.getValue())){
					showErr("【准迁证编号】格式不正确！",function(){
						var qwdssxq = f.getForm().findField("qwdssxq").getValue();
						getXZqhJx(qwdssxq,f,true);
					});
				}
				*/
			}
        	
        },
        getSelectRy:function(){
        	
			var rylist = new Array();
			
			if(ywGrid.store!=null){
				ywGrid.store.each(
					function(rec){
						if(rec.data.xm && rec.data.xm!=""){
							var len = rylist.length;
							
							rylist[len] = new Array();
							rylist[len][0] = rec.data.xm;
							rylist[len][1] = rec.data.xm;
							rylist[len][2] = rec.data.gmsfhm;
						}
					},	ywGrid
				);
			}
			
			if(hcyGrid.store!=null){
				hcyGrid.store.each(
					function(rec){
						if(rec.data.xm && rec.data.xm!=""){
							var len = rylist.length;
							
							rylist[len] = new Array();
							rylist[len][0] = rec.data.xm;
							rylist[len][1] = rec.data.xm;
							rylist[len][2] = rec.data.gmsfhm;
						}
					},	hcyGrid
				);
			}
			
			return rylist;
		}
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
    			Gnt.photo.setPhoto(selRes, false);
    		}
		}
	});
	
	form_hcy = new Gnt.ux.SjpzForm({
		title: '原户成员信息',
		closable: false,
		region:'center',
		height:330,
		pzlb: '10019',
		labelWidth : 140,
		cols:2,
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
			}
			
			return;
		},
        fieldBlur:function(field){
        	var r = hcyGrid.getSelectionModel().getSelected();
        	
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
	
	form_sbr = new Gnt.ux.SjpzForm({
		closable: false,
		region:'south',
		height:40,
		cols:2,
		pzlb: '10002',
		labelWidth :  120,
		//选择人的来源
//		bindSelectRyStore: ywGrid.store,
		changeDictCust:function(cmb,res){
			//当选择人后，为其它域赋值
			this.getForm().setValues({sbrgmsfhm:res.data.data});
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
			var store = null;
			
			if(rhFlag == 2){
				store = ywGrid.store;
			}else{
				store = hcyGrid.store;
			}
			
			if(store!=null){
				store.each(
						function(rec){
							if(rec.data.xm && rec.data.xm!=""){
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
        title: '申报人信息'
	});
	
	var editPanelCard = new Ext.Panel({
    	layout:'card',
    	region:'center',
    	border:false,
    	id:'editPanelCard',
    	frame:false,
    	activeItem: 0,
    	xtype: 'panel',
    	items:[{
	    	   region:'center',
	    	   layout: 'border',
	    	   id: 'c1',
	    	   items:[
	    	          form_yw,form_sbr
	    	   ]
	       },{
	    	   region:'center',
	    	   layout: 'border',
	    	   id: 'c2',
	    	   items:[
	    	          form_hcy
	    	   ]
	       }]
    });
	
	var p2 = new Ext.Panel({
		layout: 'border',
		items:[
		       {
		    	   region:'west',
		    	   width:200,
		    	   layout:'border',
		    	   items:[
						hcyGrid,ywGrid
		    	   ]
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
					    	    },{
					    	    	height:3,
					    	    	border:false,
					    	    	frame:false
					    	    },new Ext.Button({
										text:'照片采集',
										minWidth:100,
										id: 'zpcjImgBtn',
										width:150,
										handler:function(){
											Gnt.photo.OpenEdit();
										}
									}),{
						    	    	height:3,
						    	    	border:false,
						    	    	frame:false
						    	    },new Ext.Button({
										text:'照片删除',
										id: 'zpscImgBtn',
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
						    	    	xtype: 'QyzButton',
						    	    	form: form_yw,
						    	    	beforeCall:function(){
						    	    		qyz_check = true;
						    	    		if(qyz_check && zqz_check&&user.flag!='1'){
						    	    			Ext.MessageBox.alert("迁移证核验消息","经与***联系，核验结果***，此证真实有效");
						    	    			Ext.getCmp('saveId').enable();
						    	    		}
						    	    	}
						    	    },{
						    	    	height:3,
						    	    	border:false,
						    	    	frame:false
						    	    },{
						    	    	id:"ZqzButtonId",
						    	    	xtype: 'ZqzButton',
						    	    	form: form_yw,
						    	    	beforeCall:function(){
						    	    		zqz_check = true;
						    	    		if(qyz_check && zqz_check&&user.flag!='1'){
						    	    			Ext.getCmp('saveId').enable();
						    	    		}
						    	    	}
						    	    },{
						    	    	height:30,
						    	    	border:false,
						    	    	frame:false
						    	    },new Ext.Button({
										text:'人户分离',
										minWidth:100,
										handler:function(){
											rhfl_Window.show();
											rhfl_Window.initData(selRes);
										}
									}),{
						    	    	height:3,
						    	    	border:false,
						    	    	frame:false
						    	    },new Ext.Button({
										text:'增加人口',
										minWidth:100,
										id:'bbtnAdd',
										handler:function(){
											addRyxx();
										}
						    	    }),{
						    	    	height:3,
						    	    	border:false,
						    	    	frame:false
						    	    },new Ext.Button({
										text:'删除人口',
										disabled:true,
										minWidth:100,
										id:'bbtnDelete',
										handler:function(){
											delRyxx();
										}
						    	    }),{
						    	    	height:3,
						    	    	border:false,
						    	    	frame:false
						    	    },{
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
						    	    	disabled:true,//user.flag>0?true:false,
						    	    	id:'saveId',
						    	    	callback: function(){

											
											/**
												立户时,户主未满18周岁
												提示’户主未满18周岁,是否继续保存?’
											 */
											if(rhFlag == 2){
												ywGrid.store.each(function(r){
													var data = r.data;
													
													if(data.yhzgx=="01" || data.yhzgx=="02" || data.yhzgx=="03"){
														var nl = Gnt.date.getNl(data.gmsfhm, data.csrq);
														if(nl < 18){
															if(window.confirm("户主未满18周岁,是否继续保存?")){
																
															}else{
																return ;
															}
														}
													}
												});
											}
											var bdfw =form_yw.getForm().getValues().bdfw;
											if(!bdfw){
												alert('变动范围必填项');
												return;	
											}
											//alert(form_yw.getForm().findField("bdfw").getValue());
											Gnt.yw.save(3, false, "1", ywGrid);
										
						    	    	}
						    	    }/*,new Ext.Button({
						    	    	id:'saveId',
										text:'保存',
										minWidth:100,
										disabled:true,
										handler:function(){
											
											*//**
												立户时,户主未满18周岁
												提示’户主未满18周岁,是否继续保存?’
											 *//*
											if(rhFlag == 2){
												ywGrid.store.each(function(r){
													var data = r.data;
													
													if(data.yhzgx=="01" || data.yhzgx=="02" || data.yhzgx=="03"){
														var nl = Gnt.date.getNl(data.gmsfhm, data.csrq);
														if(nl < 18){
															if(window.confirm("户主未满18周岁,是否继续保存?")){
																
															}else{
																return ;
															}
														}
													}
												});
											}
											
											Gnt.yw.save(3, false, "1", ywGrid);
										}
						    	    })*/,{
						    	    	height:3,
						    	    	border:false,
						    	    	frame:false
						    	    },new Ext.Button({
										text:'新办理',
										disabled:hzywid?true:false,
										minWidth:100,
										handler:function(){
											showQuestion("数据未保存，确定放弃办理吗？", function(btnType){
												if(btnType=="yes"){
													window.location.reload();
												}
											});
										}
						    	    }),{
						    	    	height:3,
						    	    	border:false,
						    	    	frame:false
						    	    },new Ext.Button({
										text:'继续办理',
										minWidth:100,
										disabled:hzywid?true:false,
										handler:function(){
											Ext.getCmp('card').getLayout().setActiveItem(0);
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
		    	   html: '<center><br/><br/><br/><br/><h2>迁入业务办理完成！</center></h2>'
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
									/*Ext.getCmp('card').getLayout().setActiveItem(0);
									v.doLayout();
									initYw();
									*/
									window.location.reload();
								}
		    	            }),new Ext.Button({
								text:'户籍打印',
								minWidth:100,
								width:150,
								handler:function(){
									printRk(3,selectHhnbid,selectRynbid,[true,true,true,true,true],[true,true,false,false,false],[true,true,true,true,true]);
								}
		    	            }),new Ext.Button({
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
	
	bggzWin = new Gnt.ux.BggzDialog({
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
        	items:[p1, p2, p3]
        }
    });
	
	//户政业务处理
	if(hzywid && hzywid!=""){
		Gnt.makeHzyw({
			hzywid: hzywid,
			pch:pch,
			//ywlx:'3',
			cmpids: ['sprhBtn', 'qrrhBtn', 'lhrhBtn'],
			callback:function(jo, jolist){
				//成功处理返回，合并数据
				hzywjo_list =[];
				hzywjo_list.push(jo);
				Ext.each(jolist,function(d){
					hzywjo_list.push(d);
				});
				
				hzywjo = jo;
				
				if(hzywjo.lhbs=='0'){
					//审批入户
					Ext.getCmp("sprhBtn").handler();
				}else if(hzywjo.lhbs=='1'){
					//迁入入户
					Ext.getCmp("qrrhBtn").handler();
				}else{
					//迁入立户
					Ext.getCmp("lhrhBtn").handler();
				}
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
	function initBdfw(selRes){
    	var f = form_yw.getForm().findField("bdfw");
		if(xt_bssqb_list==null){
			showInfo("没有找到市区配置信息，无法过滤变动范围！");
			return;
		}
		var formValues = form_yw.getForm().getValues();
		var qrlb = formValues.qrlb;
		var qcdssxq = selRes.data.qcdssxq;
		var old_ssxq = qcdssxq;
		var fwflag = true;
		Ext.each(xt_bssqb_list, function(data){
			if(data.qhdm==old_ssxq || data.qhdm==new_ssxq){
				if(data.sqbz=='0')
					fwflag = false;
			}
		});
		
		f.filter =function(data){
			if(qrlb){
				//1、	迁入类别为 0344 0345 0346 0347 0371 0381   变动范围是 51省外乡村、 52省外城镇、 53省外城市 （此规则优先于下面其他规则）
				var v = qrlb;
				if(v=='0344' || v=='0345' || v=='0346' || v=='0347' || v=='0371' || v=="0381"){
					if(data[0]=='51' || data[0]=='52' || data[0]=='53')
						return true;
					else
						return false;
				}
			}
			if(qcdssxq){
				//2同市同区县内迁移变动范围是 21 区(县)内他所(乡) 
				if(old_ssxq==new_ssxq){
					if(data[0]=='21')
						return true;
					else
						return false;
				}
				//同市，跨区县 sqbz(市区标志)都为1的情况下变动范围是31市内城镇32	市内郊区，否则为 33	市外乡村34市外城镇
				if(old_ssxq.length>4 && new_ssxq.substring(0,4)==old_ssxq.substring(0,4) && old_ssxq!=new_ssxq){
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
				}
			}
			
			//7、	迁出地为710000 台湾省 变动范围62台湾地区
			if(old_ssxq&&old_ssxq.length>4 && old_ssxq=='710000'){
				if(data[0]=='62'){
					return true;
				}else{
					return false;
				}
			}
			
			//8、	迁出地为 810000 820000 变动范围为 61港、澳地区
			if(old_ssxq&&old_ssxq.length>4 && (old_ssxq=='810000' || old_ssxq=='820000') ){
				if(data[0]=='61'){
					return true;
				}else{
					return false;
				}
			}
			
			//4、	迁出地省市县区为省内市外的 变动范围是 41省内乡村、42省内城镇、43省内城市
			if(old_ssxq&&old_ssxq.length>4 && new_ssxq.substring(0,2)==old_ssxq.substring(0,2) && new_ssxq.substring(0,4)!=old_ssxq.substring(0,4)){
				if(data[0]=='41' || data[0]=='42' ||  data[0]=='43'){
					return true;
				}else{
					return false;
				}
			}
			
			//5、	迁出地省市县区为国内省外的（不包括港澳台） 变动范围是 51省外乡村、 52省外城镇、 53省外城市
			if(old_ssxq&&old_ssxq.length>4 && old_ssxq.substring(0,3)!='010' && new_ssxq.substring(0,2)!=old_ssxq.substring(0,2)){
				if(data[0]=='51' || data[0]=='52' ||  data[0]=='53'){
					return true;
				}else{
					return false;
				}
			}
			
			//6、	迁出地为国外的 如010004阿富汗 变动范围为71国外
			if(old_ssxq&&old_ssxq.length>4 && old_ssxq.substring(0,3)=='010'){
				if(data[0]=='71'){
					return true;
				}else{
					return false;
				}
			}
			return true;
		}
		f.reloadDict();
    }
});

//提交数据
function submitData(bggzList){
	/*
	   * @param voLhhdxx - 立户户地信息
	   * @param voRhhdxx - 入户户地信息
	   * @param voSbjbxx - 申报基本信息
	   * @param voQrdjxx[] - 迁入登记信息
	   * @param voChxx[] - 重号信息
	   * @param voBggzxx[] - 变更更正信息
	*/
	var subdata = {
		voLhhdxx: select_lhdz,
		voRhhdxx:{
			hhnbid: Hhnbid
		},
		voSbjbxx: form_sbr.getForm().getValues(),
		voQrdjxx: new Array(),
		voChxx: new Array(),
		voBggzxxEx: bggzList
	};

	if(select_lhdz){
		lhHlx = select_lhdz.hlx;
	}
	//关键，户政业务ID
	if(hzywjo){
		subdata.voSbjbxx.hzywid = hzywjo.id;
	}
	
	//人员内部id和上笔户籍业务的关系
	var map = {};
	hcyGrid.store.each(
			function(rec){
				var data = rec.data;
				map[rec.data.rynbid] = data.sbhjywid;
			},	hcyGrid
	);
	
	//迁入登记信息
	for(var index=0;index<ywGrid.store.getCount();index++){
		var data = ywGrid.store.getAt(index).data;
		data.sbhjywid = map[data.rynbid];
		//add by zjm 20191227 迁入，csdgjdq要取csdssxq后三位
		if(data.csdssxq&&(data.csdssxq).substr(0,3)=='010'){
			var csdssxq = data.csdssxq;
			data.csdgjdq = csdssxq.substring(csdssxq.length-3);
		}
		//add by zjm 20200102 迁入，jggjdq要取jgssxq后三位
		if(data.jgssxq&&(data.jgssxq).substr(0,3)=='010'){
			var jgssxq = data.jgssxq;
			data.jggjdq = jgssxq.substring(jgssxq.length-3);
		}
		if(lhHlx){//add by zjm 20200310 增加户类型和户别匹配验证
			if((lhHlx==1&&data.hb==7)||(lhHlx==2&&data.hb==6)){
				alert("户类型和户别不匹配!");
				return;
			}
		}
		subdata.voQrdjxx.push(data);
	}
	for(o in subdata){
		if(subdata[o]){
			subdata[o] = Ext.encode(subdata[o]);
		}
	}
	log_code = "F5006";
	Gnt.submit(
		subdata, 
		"yw/hjyw/qryw/processQrdjyw", 
		"正在处理迁入业务信息，请稍后...", 
		function(jsonData,params){
			showInfo("迁入业务登记成功！",function(){
				
				Ext.getCmp('card').getLayout().setActiveItem(2);
				Ext.getCmp('vp').doLayout();
				
				if(bggzWin.isVisible())
					bggzWin.hide();
				
				/**
					户口簿打印无法一次弹出多个窗口
					暂时改为全户打印
				 */
				selectRynbid="";
				
				if(jsonData && jsonData.list[0] && jsonData.list[0].voQrdjfhxx){
					selectHhnbid=jsonData.list[0].hhnbid;
					for(var i=0; i < jsonData.list[0].voQrdjfhxx.length; i++){
						selectRynbid += jsonData.list[0].voQrdjfhxx[i].rynbid + ",";
					}
					
					if(selectRynbid.length > 0){
						selectRynbid = selectRynbid.substr(0,selectRynbid.length - 1);
					}
					selectHhnbid = jsonData.list[0].hhnbid;
					if(hzywjo&&hzywjo.id){
						Gnt.ux.Dict.getKzcs('10036', function(pz, user) {
							if(pz.kzz==1){
								pjq('ES',user.ip,hzywjo.id);
							}
						});
					}
					printRk(3,jsonData.list[0].hhnbid,selectRynbid,[true,true,true,true,true],[true,true,false,false,false],[true,true,true,true,true]);
				}
				
				
				//关闭当前窗口
				/*Ext.getCmp('card').getLayout().setActiveItem(0);
				v.doLayout();
				p1.newYewu();
				*/
				
				//window.location.reload();
			});
		}
	);
}
