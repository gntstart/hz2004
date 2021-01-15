var selRes = null;
var curIndex = -1;
var resultIndex = -1;
var spywid = null;
var ywid = 0;
var FRhHb  = null;
var Hhnbid = null;
var Rynbid = null;
var form_sbr = null;//业务表单
var form_yw = null;//申报人表单
var form_hcy = null;
var ywGrid = null;
var hcyGrid = null;//户成员表单
var selectRynbid = "";
var selectHhnbid = "";
var lhHlx = null;

var bggzWin = null;//变更更正对话框
var select_lhdz = null;
var select_optype_index = -1;
var hzywjo_list;
var hzywjo;

var rhFlag = false;
var hb = null;
var ssxq = null;

Ext.onReady(function(){
	Ext.QuickTips.init();
	
	var hzywid = getQueryParam("hzywid");
	var pch = getQueryParam("pch");
	
	//本业务需要加载的配置
	//10010 Hj_人户分离信息
	//10019 Hj_户成员信息
	//10001 Hj_出生登记信息
	//10022 Hj_户地信息
	//10018 Hj_户成员关系调整
	//10017 Hj_地信息
	//10002 Hj_申报人信息
	if(!Gnt.loadSjpzb("10010,10019,10001,10022,10018,10017,10002,20000",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX','CS_BGGZLB'],function(){});
	
	function initYw(){
		selRes = null;
		curIndex = -1;
		spywid = null;
		ywid = 0;
		FRhHb  = null;
		Hhnbid = null;
		Rynbid = null;
		//两个表弟清空
		form_yw.getForm().reset();
		form_sbr.getForm().reset();

		//家庭成员清空
		hcyGrid.store.removeAll();
		ywGrid.store.removeAll();
		//户地信息
		wsCdsdxx.removeAll();
		
		form_yw.getForm().findField("csrq").setMaxValue(Ext.util.Format.date(new Date(),'Ymd'));
		
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

		if (Hhnbid !=null && FRhHb !=null)
		    data.hb = FRhHb;
		else
			data.hb = FHb;
		    
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
		
		data.csdssxq = ssxq;
		data.jgssxq = ssxq;
		
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
	
	function initSqry(){
		//初始化随迁人员
		//新增一个随迁人员
		if(hzywjo && hzywjo_list.length>0){
			Ext.each(hzywjo_list, function(data){
				ywid++;
				
				var item = null;
				
				if(hzywjo.lhbs=='1'){
					//入户
					item = {
							ywid: ywid,
							csdjlb: data.csdjlb,
							cszmbh: data.cszmbh,
							yhzgx: data.yhzgx,
							csrq: data.csrq,
							xm: data.bsqrxm,
							xb: data.xb,
							mz: data.mz,
							jhryjhgx: data.jhryjhgx,
							jhrygmsfhm: data.jhrygmsfhm,
							jhryxm: data.jhryxm,
							csdssxq: data.csdssxq,
							jgssxq: data.jgssxq
					};
				}else{
					//立户
					item = {
							ywid: ywid,
							csdjlb: data.csdjlb,
							yhzgx: data.yhzgx,
							csrq: data.csrq,
							cszmbh: data.cszmbh,
							xm: data.bsqrxm,
							xb: data.xb,
							mz: data.mz,
							jhryxm: data.jhryxm,
							jhryjhgx: data.jhryjhgx,
							jhrygmsfhm: data.jhrygmsfhm,
							fqxm: data.fqxm,
							csdssxq: data.csdssxq,
							jgssxq: data.jgssxq
					};
				}
				// add by zjm 20190228 所有申请人的相关信息均取自中间表
				if(!Gnt.util.isEmpty(data.fqxm)){// add by zjm 20190325 增加父亲姓名
					item.fqxm = data.fqxm;
				}
				if(!Gnt.util.isEmpty(data.fqgmsfhm)){
					item.fqgmsfhm = data.fqgmsfhm;
				}
				if(!Gnt.util.isEmpty(data.mqxm)){
					item.mqxm = data.mqxm;
				}
				if(!Gnt.util.isEmpty(data.mqgmsfhm)){
					item.mqgmsfhm = data.mqgmsfhm;
				}
				if(!Gnt.util.isEmpty(data.jhrejhgx)){
					item.jhrejhgx = data.jhrejhgx;
				}
				if(!Gnt.util.isEmpty(data.jhrexm)){
					item.jhrexm = data.jhrexm;
				}
				if(!Gnt.util.isEmpty(data.jhregmsfhm)){
					item.jhregmsfhm = data.jhregmsfhm;
				}
//				if(data.bsqrsjhm){// add by zjm 20190627 增加号码带入值设置
//					item.dhhm = data.bsqrsjhm;
//				}
				/**
				 * add by zjm 
				 * 20190712 
				 * 增加cssj,cszqfsj,bsqrsjhm3个字段值带入值设置
				 * 改成增加cssj,cszqfsj,dhhm,cym3个字段值带入值设置
				 */
				if(!Gnt.util.isEmpty(data.cssj)){
					item.cssj = data.cssj;
				}
				if(!Gnt.util.isEmpty(data.cszqfrq)){
					item.cszqfrq = data.cszqfrq;
				}
				if(!Gnt.util.isEmpty(data.dhhm)){
					item.dhhm = data.dhhm;
				}
				// add by zjm 20190627 户政平台中间表带入曾用名
				if(!Gnt.util.isEmpty(data.cym)){
					item.cym = data.cym;
				}
				/*
				 * add by zjm 20190916  
				 * 户政平台中间表带入  出生地详址  信息级别   人员类别   备注
				 */
				if(!Gnt.util.isEmpty(data.csdxz)){
					item.csdxz = data.csdxz;
				}
				if(!Gnt.util.isEmpty(data.xxjb)){
					item.xxjb = data.xxjb;
				}else{
					item.xxjb = '5';
				}
				if(!Gnt.util.isEmpty(data.rylb)){
					item.rylb = data.rylb;
				}else{
					item.rylb = '1';
				}
				if(!Gnt.util.isEmpty(data.bz)){
					item.bz = data.bz;
				}
				//add by zjm 20190927  申报人家庭关系 
				if(!Gnt.util.isEmpty(data.sbrjtgx)){
					item.sbrjtgx = data.sbrjtgx;
				}
				if(!Gnt.util.isEmpty(data.rkzp)){
					item.zp = data.rkzpBase64;
					rkzpMap[data.rynbid] = data.rkzpBase64;
				}
				/*add by zjm 20200709 
				 * 父亲证件种类、父亲证件号码、母亲证件种类、母亲证件号码、监护人一证件类型、监护人一证件号码、
				 * 监护人二证件类型、监护人二证件号码、父亲外文姓、父亲外文名、母亲外文姓、母亲外文名、监护人一外文姓、监护人一外文名、
				 * 监护人二外文姓、监护人二外文名。需代值
				*/
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
				if(!Gnt.util.isEmpty(data.jhryzjlb)){
					item.jhryzjzl = data.jhryzjlb;
				}
				if(!Gnt.util.isEmpty(data.jhryzjhm)){
					item.jhryzjhm = data.jhryzjhm;
				}
				if(!Gnt.util.isEmpty(data.jhrezjlb)){
					item.jhrezjzl = data.jhrezjlb;
				}
				if(!Gnt.util.isEmpty(data.jhrezjhm)){
					item.jhrezjhm = data.jhrezjhm;
				}
				if(!Gnt.util.isEmpty(data.fqwwx)){
					item.fqwwx = data.fqwwx;
				}
				if(!Gnt.util.isEmpty(data.fqwwm)){
					item.fqwwm = data.fqwwm;
				}
				if(!Gnt.util.isEmpty(data.mqwwx)){
					item.mqwwx = data.mqwwx;
				}
				if(!Gnt.util.isEmpty(data.mqwwm)){
					item.mqwwm = data.mqwwm;
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
				var rr = new ywGrid.store.reader.recordType(item, item.ywid);
				ywGrid.store.add([rr]);
			});
		}else{
			ywid++;
			var rr = new ywGrid.store.reader.recordType({ywid: ywid}, ywid);
			rr.set('_sel', '1');
			
			/**
				设置默认值
				民族:汉
				出生地省县:
				籍贯省县:
				add by zjm  20190916
				信息级别：
				人员类别：
			 */
			rr.set('mz', '01');
			rr.set('csdssxq', ssxq);
			rr.set('jgssxq', ssxq);
			
			rr.set('xxjb', '5');
			rr.set('rylb', '1');
			
			ywGrid.store.add([rr]);
		}
	}
	
	function initSbr(){
		//form_sbr
		if(hzywjo && hzywjo_list.length>0){
//			form_sbr.fieldSetValue("sbryxm":hzywjo.sqrxm);
//			form_sbr.fieldSetValue("sbrgmsfhm":hzywjo.sqrsfz);
			form_sbr.getForm().setValues({
				sbryxm: hzywjo.sqrxm,
				sbrgmsfhm: hzywjo.sqrsfz
			});
		}
	}
	
	var rhWindow = new Gnt.ux.SelectRh({
		returnTitleText : '出生入户 ——户地信息查询',
		callback: function(optype, hcyList){
			/*
			var cms=hcyGrid.columns;
			alert(cms);
			for(var i=0;i<cms.length;i++){
				alert(cms[i].text+'\n'+cms[i].dataIndex);
			}
			*/
			
			
			//点击确认迁出后，执行此方法
			Ext.getCmp('card').getLayout().setActiveItem(1);
			v.doLayout();
			
			initYw();
			
			select_lhdz = null;
			select_optype_index = 0;
			
			initSqry();
			initSbr();
		
			//户成员初始化
			var store = hcyGrid.store;
			//人员选择回调：审批业务，迁入人员
			for(var i=0;i<hcyList.length;i++){
				var data = hcyList[i];
				data._sel = "0";
				var rr = new store.reader.recordType(data, data[store.pkname]);
				store.add([rr]);
				
				if(Hhnbid==null){
					/**
						获取不到Hhnbid
						导致保存时执行立户逻辑
					Hhnbid = data.Hhnbid;
					 */
					Hhnbid = data.hhnbid;
					Rynbid = data.rynbid;
				}
				
			}
			
			(function(){
				ywGrid.fireEvent("rowclick",ywGrid,0);
				ywGrid.getSelectionModel().selectRange(0,0);
			}).defer(200);
			
		},
		rowclickBack : function(optype, data){
			hb = data.hb;
			ssxq = data.ssxq;
		}
	});
	
	var lhWindow = new Gnt.ux.SelectBzdz({
		//选择立户信息回调
		callback: function(optype, lhdz){
			//点击确认后，执行此方法
			Ext.getCmp('card').getLayout().setActiveItem(1);
			v.doLayout();
			
			initYw();
			select_lhdz = lhdz;
			select_optype_index = 1;
			//add by zjm 20201110 获取ssxq字段用于出生业务身份证拼接
			ssxq = lhdz.ssxq;
			initSqry();
			initSbr();

			(function(){
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
								text:'入户方式',
								minWidth:75,
								id:'rhBtn',
								handler:function(){
									
									rhFlag = true;
									
									rhWindow.show();
									rhWindow.setHzyw(hzywjo);
								}
							}),
							new Ext.Button({
								text:'立户方式',
								minWidth:75,
								id:'lhBtn',
								handler:function(){
									rhFlag = false;
									lhWindow.show();
									lhWindow.setHzyw(hzywjo);
									
									if(hzywjo){
										lhWindow.fs.getForm().setValues({
											hlx: hzywjo.hlx
										});
									}
									
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
		listeners:{
			rowclick:function(g, rowIndex, e){
	   			selRes = g.store.getAt(rowIndex);
    			curIndex = rowIndex;
    			
//    			selectRynbid = selRes.data.rynbid;
    			selectHhnbid = selRes.data.hhnbid;
    			
    			if(editPanelCard.getLayout().activeItem.id=="c1"){
        			editPanelCard.getLayout().setActiveItem(1);
        			form_hcy.doLayout();
//        			editPanelCard.doLayout();
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
		labelWidth :  140,
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
				//add by zjm 20190710 配偶下拉框选中人员后，配偶身份证自动带入
				this.getForm().setValues({pogmsfhm:res.data.data});
				selRes.set("pogmsfhm",res.data.data);
			}
			
			return;
		},
        fieldBlur:function(field){
        	var f = field.findParentByType("sjpz_form");
        	
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
	
	ywGrid = new Gnt.ux.SjpzGrid({
		pkname: 'ywid',
		pzlb: '10001',
		region:'north',
    	title: '出生人口列表',
    	height:200,
    	showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){
	   			selRes = g.store.getAt(rowIndex);
    			curIndex = rowIndex;
    		
    			if(editPanelCard.getLayout().activeItem.id=="c2"){
        			editPanelCard.getLayout().setActiveItem(0);
//        			editPanelCard.doLayout();
    			}
    			
    			if(rhFlag){
    				selRes.set("hb",hb);
    			}
    			selRes.set("_sel",1);
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
    		}
		}
	});
	
	form_yw = new Gnt.ux.SjpzForm({
        title: '出生信息登记',
		closable: false,
		region:'center',
		height:330,
		pzlb: '10001',
		labelWidth :  140,
		cols:2,
//		bindStore:hcyGrid.store,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:ywGrid,
		//绑定原户成员显示数据
		bindViewGridHCY: hcyGrid,
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
				//add by zjm 20190805 配偶下拉框选中人员后，配偶身份证自动带入
				this.getForm().setValues({pogmsfhm:res.data.data});
				selRes.set("pogmsfhm",res.data.data);
			}
			
			return;
		},
    	fieldBlur:function(field){
    		var f = field.findParentByType("sjpz_form");
    		var r = ywGrid.getSelectionModel().getSelected();
    		if(field.name=='gmsfhm'&&field.getValue().length <= 14){
    			if(r.data.csrq){
    				if(r.data.csrq){
    					field.setValue(ssxq + r.data.csrq) ;
    				}
    			}
    		}
//        	if(field.name=='gmsfhm'){
//        		if(field.getValue()){
//        			if(field.getValue().length == 6){
//        				if(r.data.csrq){
//        					field.setValue(field.getValue() + r.data.csrq) ;
//        				}
//        			}
//        			
//        			/**
//        				自动将最后一位补上
//        			 */
//        			if(field.getValue().length == 17){
//        				if(r){
//        					r.data.gmsfhm = field.getValue() + Gnt.date.getCheckCode(field.getValue());
//        				}
//        				field.setValue(field.getValue() + Gnt.date.getCheckCode(field.getValue())) ;
//        			}
//        			
//        			/**
//        				设置出生日期
//        			 */
//        			if(!form_yw.getForm().findField("csrq").getValue()){
//        				if(r){
//        					r.data.csrq = Gnt.date.getBirth(field.getValue());
//        				}
//        				form_yw.getForm().findField("csrq").setValue(Gnt.date.getBirth(field.getValue()));
//        			}
//        			
//        			/**
//	    				设置性别
//	    			 */
//        			if(!form_yw.getForm().findField("xb").getValue()){
//        				if(r){
//        					r.data.xb = Gnt.date.getSexCode(field.getValue());
//        				}
//        				form_yw.getForm().findField("xb").setValue(Gnt.date.getSexCode(field.getValue()));
//        			}
//        			
//        		}
//        		
//        	}
        	/*
    		if(field.name=="cszmbh"){
    			Ext.getCmp("cszhyBtn").handler();
    		}
    		*/
    		
    		if(field.name=='hyzk' && r.isModified("hyzk")){
        		Gnt.validpo(form_yw,field);
        	}
        	
        	if(field.name=='pogmsfhm' && r.isModified("pogmsfhm")){
        		Gnt.validposfzh(form_yw,field,true);
        	}
    		
    		
    	},
        fieldChange:function(field){
        	var f = field.findParentByType("sjpz_form");
        	if(field.name=='cszmbh'){
        		
//        		if(field.getValue().length > 20){
        		if(Gnt.ux.getStrLength(field.getValue()) > 20){
        			showErr("【出生证明编号】长度超出限制！",function(){
        				form_yw.getForm().findField("cszmbh").setValue('');
        			});
        		}else{
        			Ext.getCmp("cszhyBtn").handler();
        		}
        		
        		/*
        		var reg=/^[\u4e00-\u9fa5]+\d{8}$/;
        		if(!reg.test(field.getValue())){
        			showErr("【出生证明编号】格式不正确！");
        			
        			form_yw.getForm().findField("cszmbh").setValue('');
        		}
        		*/
        	}
        	
        }
    	,fieldFocus:function(field){
			Gnt.filterField(field);
		}
	});
	
	form_sbr = new Gnt.ux.SjpzForm({
		closable: false,
		region:'south',
		height:35,
		cols:2,
		pzlb: '10002',
		labelWidth :  120,
		bindViewGridHCY: hcyGrid,
		//选择人的来源
		bindSelectRyStore: ywGrid.store,
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
				入户：圆弧成员列表
				立户：出生登记信息
			 */
			var rylist = new Array();
			var store = null;
			
			if(rhFlag){
				store = hcyGrid.store;
			}else{
				store = ywGrid.store;
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
		    		   ywGrid,hcyGrid
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
											if(ywid > 1){
												showQuestion("数据未保存，确定删除人口吗？", function(btnType){
													if(btnType=="yes"){
														ywGrid.store.remove(ywGrid.store.getAt(ywid - 1));
														ywid--;
														
														/**
															2018.07.22
															新增	刁杰
															删除人口后出生登记信息表单内的内容应更改为上一个人员或者第一条记录信息
														 */
														var index = ywGrid.store.getCount() - 1;
														ywGrid.fireEvent("rowclick",ywGrid,index);
														ywGrid.getSelectionModel().selectRange(index,index);
														
														if(ywid == 1){
															Ext.getCmp('bbtnDelete').setDisabled(true);
														}
														
													}
												});
											}
										}
						    	    }),{
						    	    	height:3,
						    	    	border:false,
						    	    	frame:false
						    	    },new Ext.Button({
										text:'出生证核验',
										minWidth:100,
										id: 'cszhyBtn',
										handler:function(){
											var f = form_yw.getForm().findField("cszmbh");
											var bh = f.getValue();
											if(!bh || bh==""){
												showErr("出生证编号不能为空！");
												return;
											}
											
											Gnt.ux.Dict.getKzcs('10014', function(config, user, kzdata){
													var url = config.bz;
													if(url.indexOf("?")<0)
														url += "?";
													
													url += "&cszmbh=" + encodeURI(bh) 
														+ "&yh=" + user.yhdlm 
														+ "&yhxm=" + encodeURI(user.xm) 
														+ "&yhdw=" + user.dwdm 
														+ "&yhdwmc=" + encodeURI(user.dwmc);
													
													var w = new Gnt.ux.URLDialog({
														title:'出生证核验',
														width:900,
														height:500,
														url: url
													});
													w.show();
													if(user.flag!='1'){
														Ext.getCmp('saveId').setDisabled(false);
								    	    		}
													
											});
										}
						    	    }),{
						    	    	height:3,
						    	    	border:false,
						    	    	frame:false
						    	    },{
						    	    	xtype:'SfzhyButton',
						    	    	fieldname:'gmsfhm',
						    	    	form:form_yw
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
						    	    	id:'saveId',
						    	    	disabled:true,
						    	    	hzywid:getQueryParam("hzywid"),
						    	    	callback: function(){
						    	    		Gnt.yw.save(1,false, "1");
						    	    	}
						    	    }/*,new Ext.Button({
						    	    	id:'saveId',
										text:'保存',
										minWidth:100,
										//disabled:true,
										handler:function(){
											alert(Gnt.yw.checkClbsBeforeSave(getQueryParam("hzywid")));
											if(Gnt.yw.checkClbsBeforeSave(getQueryParam("hzywid"))){
												alert(111);
											};
											//Gnt.yw.save(1,false, "1");
										}
						    	    })*/,{
						    	    	height:3,
						    	    	border:false,
						    	    	frame:false
						    	    },new Ext.Button({
						    	    	id:'newId',
										text:'新办理',
										disabled:hzywid?true:false,
										minWidth:100,
										handler:function(){
											showQuestion("数据未保存，确定放弃办理吗？", function(btnType){
												if(btnType=="yes"){
													/*Ext.getCmp('card').getLayout().setActiveItem(0);
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
						    	    }/*,new Ext.Button({
										text:'继续办理',
										disabled:hzywid?true:false,
										minWidth:100,
										handler:function(){
											Ext.getCmp('card').getLayout().setActiveItem(0);
											v.doLayout();
											p1.newYewu();
										}
						    	    })*/
					    	 ]
			    	    }
			    	 ]
			    }
		]
	});
	
	var cswcGrid = new Gnt.ux.SjpzGrid({
		id:'wcGridId',
		pkname: 'rynbid',
		pzlb: '20000',
		title: '',
		url: 'cx/hjjbxx/ckxx/getXxxx.json'
	});
	
	var form20000 = new Gnt.ux.SjpzForm({
		id:'wcFormId',
		closable: false,
		pzlb: '20000',
		region:'center',
		labelWidth :  120,
		cols:2,
		//绑定bindViewGrid，当bindViewGrid点击的时候，触发记录移动
		bindViewGrid: cswcGrid,
		title: ''
	});
	
	var tb = new Ext.Toolbar({
	 	frame:false,
	 	border:false,
//    	width : 300,
    	items : ['人员信息',{
	        text: '<<',
        	tooltip :'第一条记录',
        	handler : function(dp, date){
        		rkjbxxGrid.fireEvent("rowclick",rkjbxxGrid,0);
        		rkjbxxGrid.getSelectionModel().selectRange(0,0);
			}
	    },{
	        text: '<',
        	tooltip :'上一条记录',
        	handler : function(dp, date){
        		if(curIndex > 0){
        			curIndex--;
        			rkjbxxGrid.fireEvent("rowclick",rkjbxxGrid,curIndex);
        			rkjbxxGrid.getSelectionModel().selectRange(curIndex,curIndex);
        		}
			}
	    },{
	        text: '>',
        	tooltip :'下一条记录',
        	handler : function(dp, date){
        		
        		resultIndex ++ ;
        		/*
        		if(curIndex < (rkjbxxGrid,rkjbxxGrid.store.getCount() - 1)){
        			curIndex++;
        			rkjbxxGrid.fireEvent("rowclick",rkjbxxGrid,curIndex);
        			rkjbxxGrid.getSelectionModel().selectRange(curIndex,curIndex);
        		}
        		*/
			}
    	},{
	        text: '>>',
        	tooltip :'最后一条记录',
        	handler : function(dp, date){
        		rkjbxxGrid.fireEvent("rowclick",rkjbxxGrid,rkjbxxGrid.store.getCount() - 1);
        		rkjbxxGrid.getSelectionModel().selectRange(rkjbxxGrid.store.getCount() - 1,rkjbxxGrid.store.getCount() - 1);
			}
    	}/*,'	共' + resultCount + '条	第' + resultIndex + '条'*/]
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
					   borde:false,
			    	   html: '<center><br/><br/><h2>出生业务办理完成！</h2></center>'
				}, {
		    	   region:'center',
		    	   title:'',
		    	   frame:false,
				   borde:false,
	    	        items:[tb,form20000]
		       },{
				   //height:100,
				   html: '',
				   bodyStyle:'padding:10px 25px 0px 20px',
				   layout:'table',
				   region:'south',
				   frame:false,
				   borde:false,
				   layoutConfig: {
	   	    	 		columns: 20
	   	    	   },
	   	    	   items:[{
		    	    	xtype:'CkdaButton',
		    	    	clbs:'1',
		    	    	hzywid:getQueryParam("hzywid")
		    	    },
		    		   /*new Ext.Button({
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
	   								url += "&ywcode=101&ryid=" +  selRes.data.ryid;
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
			    	    	ywcode:'101'
			    	    },{
			    	    	frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			    	    },new Ext.Button({
							text:'一代证办理',
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
							text:'户籍打印',
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
							text:'二代证办理',
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
			    	    }
	    	       ]
		       }
		],
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
			cmpids: ['rhBtn', 'lhBtn'],
			callback:function(jo, jolist){
				//成功处理返回，合并数据
				hzywjo_list =[];
				hzywjo_list.push(jo);
				Ext.each(jolist,function(d){
					hzywjo_list.push(d);
				});
				hzywjo = jo;
				
				if(hzywjo.lhbs=='1'){
					//入户
					Ext.getCmp("rhBtn").handler();
				}else{
					//立户
					Ext.getCmp("lhBtn").handler();
				}
			}
		});
	}
});

bggzWin = new Gnt.ux.BggzDialog({
	hcyGrid:hcyGrid,
	title:'确认变更更正项目'
});

//提交数据
function submitData(bggzList){
	/*
	   * @param voLhhdxx - 立户户地信息
	   * @param voRhhdxx - 入户户地信息
	   * @param voSbjbxx - 申报基本信息
	   * @param voCsdjxx[] - 迁入登记信息
	   * @param voChxx[] - 重号信息
	   * @param voBggzxx[] - 变更更正信息
	*/
	var subdata = {
			voLhhdxx: select_lhdz,
			voRhhdxx:{
				hhnbid: Hhnbid
			},
			voSbjbxx: form_sbr.getForm().getValues(),
			voCsdjxx: new Array(),
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
		if(lhHlx){//add by zjm 20200310 增加户类型和户别匹配验证
			if((lhHlx==1&&data.hb==7)||(lhHlx==2&&data.hb==6)){
				alert("户类型和户别不匹配!");
				return;
			}
		}
		subdata.voCsdjxx.push(data);
	}
	
	for(o in subdata){
		if(subdata[o]){
			subdata[o] = Ext.encode(subdata[o]);
		}
	}
	//modify by zjm 20190711 pch encode方法转换后带“”后台更新不到
	if(hzywjo){
		subdata.pch = hzywjo.pch;
	}
	log_code = "F1005";
	Gnt.submit(
		subdata, 
		"yw/hjyw/csyw/processCsdjyw", 
		"正在处理出生登记信息，请稍后...", 
		function(jsonData,params){
			showInfo("出生登记成功！",function(){
				
				Ext.getCmp('saveId').setDisabled(true);
				
				var form = Ext.getCmp('wcFormId');
				
				var grid = Ext.getCmp('wcGridId');
				
				var store = grid.store;
				store.baseParams = {
					rynbid:jsonData.list[0].voCsdjfhxx[0].rynbid,
					ryid:jsonData.list[0].voCsdjfhxx[0].ryid,
					hhnbid:jsonData.list[0].voCsdjfhxx[0].hhnbid,
					//hhnbid:selectHhnbid,
					config_key:'queryPoHJXX_CZRKJBXXB4',
					start:0,
					limit:20
				};
				store.load();
				
				store.on("load",function(store) {
					if(store.data.length > 0){
						curIndex = 0;
						
						Gnt.toFormReadyonly(form);
						
						/**
							往表单设值
						 */
						var pkvalue = store.getAt(0).data[form.bindStore.pkname];
						var re = form.bindStore.getById(pkvalue);
						if(re){
							form.getForm().loadRecord(re);
						}
						
					}
				},grid);
				
				Ext.getCmp('card').getLayout().setActiveItem(2);
				Ext.getCmp('vp').doLayout();
//				form.doLayout();

				selectRynbid="";
				if(jsonData && jsonData.list[0] && jsonData.list[0].voCsdjfhxx){
					
					selectHhnbid = jsonData.list[0].hhnbid;
					
					for(var i=0; i < jsonData.list[0].voCsdjfhxx.length; i++){
						selectRynbid += jsonData.list[0].voCsdjfhxx[i].rynbid + ",";
					}
					
					if(selectRynbid.length > 0){
						selectRynbid = selectRynbid.substr(0,selectRynbid.length - 1);
					}
					
					if(hzywjo&&hzywjo.id){
						var hzywidTemp = hzywjo.id;
						Gnt.ux.Dict.getKzcs('10036', function(pz, user) {
							if(pz.kzz==1){
								pjq('ES',user.ip,hzywidTemp);
							}
						});
					}
					printRk(3,selectHhnbid,selectRynbid,[true,true,true,true,true],[true,true,false,false,false],[true,true,true,true,true]);
				}
				
				if(bggzWin.isVisible()){
					bggzWin.hide();
				}
				
				
				//关闭当前窗口
//				p1.newYewu();
				
			});
		}
	);
}