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
var hjblidArray = [];
var form_hcy = null;
var testCount = 0;
var hzywjo_list;
var hzywjo;
var lhHlx= null;
var rhFlag = -1;
var hb = null;
var new_ssxq = "";
var selectHhnbid=null;
var selectRynbid=null;
var selectRyid = "";
var form20000 = null;
var blwcGrid = null;
var hzCount = 0;
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	var hzywid = getQueryParam("hzywid");
	var pch = getQueryParam("pch");
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10016,20016,10019,10002,20001,10017,10007,10013",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	
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
		//两个表单清空
//		formHdxx.getForm().reset();
		form_hcy.getForm().reset();
		
		form_sbr.getForm().reset();
		form_yw.getForm().reset();
		
		//家庭成员清空
		hcyGrid.store.removeAll();
		//迁入登记清空
		ywGrid.store.removeAll();
		//户地信息
		wsCdsdxx.removeAll();
		
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
		curIndex ++;
		
		var rr = new ywGrid.store.reader.recordType({ywid: ywid}, ywid);
		rr.set('_sel', '1');
		rr.set('xxjb', '5');
		
		if(rhFlag == 1){
			rr.set("hb",hb);
		}
		
		ywGrid.store.add([rr]);
		
		if(ywid > 1){
			Ext.getCmp('bbtnDelete').setDisabled(false);
		}
		
		(function(){
			ywGrid.fireEvent("rowclick",ywGrid,0);
			ywGrid.getSelectionModel().selectRange(0,0);
		}).defer(200);
		
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
//							: data.lhsfz,	//拟入户身份证
							xm: data.bsqrxm,
							yhzgx: data.yhzgx,
							mz: data.mz,
							xb: data.xb,
							csrq: data.csrq,
							csdssxq: data.csdssxq,
							jthzl: data.jthzl,
							jgssxq: data.jgssxq,
							fwcs: data.fwcs,
							whcd: data.whcd,
							zy: data.zy,
							zylb: data.zylb,
							sg: data.sg,
							xxjb: '5',
							hjbllb: data.hjbllb,
							_sel: '1',
							hb:hb
					};
					
					if(data.bsqrsfz){
						item.gmsfhm = data.bsqrsfz;
					}
					
					//SetReadOnly(form_yw, 'hb', true);
				}else{
					//立户
					item = {
							ywid: ywid,
//							: data.lhsfz,	//拟入户身份证
							xm: data.bsqrxm,
							yhzgx: data.yhzgx,
							mz: data.mz,
							xb: data.xb,
							csrq: data.csrq,
							csdssxq: data.csdssxq,
							jgssxq: data.jgssxq,
							hjbllb: data.hjbllb,
							fwcs: data.fwcs,
							whcd: data.whcd,
							zy: data.zy,
							zylb: data.zylb,
							sg: data.sg,
							hb: data.hb,
							xxjb: '5',
							_sel: '1',
							jthzl: data.jthzl
					};
					
					if(data.bsqrsfz){
						item.gmsfhm = data.bsqrsfz;
					}
				}
				
				if(data.zbmap){
					for(var fname in  data.zbmap){
						item[fname] = data.zbmap[fname];
					}
				}
				if(data.bsqrsjhm){// add by zjm 20190628 增加号码带入值设置
					item.dhhm = data.bsqrsjhm;
				}
				/**
				 * add by zjm 20190718中间表增加字段 
				 * 监护人一监护关系、监护人一身份证、监护人一姓名、宗教信仰、婚姻状况、兵役情况、血型、签发机关、证件类别、有效期限起始日期、有效期限截止日期
				 * 
				 */
				if(!Gnt.util.isEmpty(data.jhryjhgx)&&!Gnt.util.isEmpty(data.jhrygmsfhm)&&!Gnt.util.isEmpty(data.jhryxm)){
					item.jhryjhgx = data.jhryjhgx;
					item.jhrygmsfhm = data.jhrygmsfhm;
					item.jhryxm = data.jhryxm;
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
				 * add by zjm 20190916  
				 * 户政平台中间表带入  出生地详址  信息级别   人员类别   备注
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
				if(!Gnt.util.isEmpty(data.rkzp)){
					item.zp = data.rkzpBase64;
					rkzpMap[data.rynbid] = data.rkzpBase64;
				}
				var rr = new ywGrid.store.reader.recordType(item, item.ywid);
				ywGrid.store.add([rr]);
			});
		}else{
			ywid++;
			var rr = new ywGrid.store.reader.recordType({ywid: ywid}, ywid);
			rr.set('_sel', '1');
			rr.set('xxjb', '5');
			
			/**
				2018.10.26
				新增	刁杰
				线上要求户别同入户户别
			 */
			if(rhFlag == 1){
				rr.set("hb",hb);
				//SetReadOnly(form_yw, 'hb', true);
			}
			
			ywGrid.store.add([rr]);
		}
	}
	
	function initSbr(){
		//form_sbr
		if(hzywjo && hzywjo_list.length>0){
			form_sbr.getForm().setValues({
				sbryxm: hzywjo.sqrxm,
				sbrgmsfhm: hzywjo.sqrsfz
			});
		}
	}
	
	var rhWindow = new Gnt.ux.SelectRh({
		returnTitleText : '户籍补录——户信息查询',
		qydjFlag:true,
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
					//Hhnbid = data.hhnbid;
					Hhnbid = hxx.hhid;
				
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
			
			(function(){
				ywGrid.fireEvent("rowclick",ywGrid,0);
				ywGrid.getSelectionModel().selectRange(0,0);
			}).defer(500);
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
								id:'rhBtn',
								minWidth:75,
								handler:function(){
									rhFlag = 1;
									rhWindow.show();
									rhWindow.setHzyw(hzywjo);
								}
							}),
							new Ext.Button({
								text:'立户方式',
								id:'lhBtn',
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
			}/*,{
				id: "panelHtmlId" ,
				html: '<div class="text" style=" text-align:center;">户籍补录入户</div>',
				height: 40,
				region:'center',
				bodyStyle:'padding:15px'
			},{
				id:"qrhdId",
				border:false,
				hidden:true,
	        	region:'center',
	        	height: 300,
	        	items:[
	        		formHdxx
	        	]
			},{
				id:"yhcyId",
				border:false,
				hidden:true,
	        	region:'south',
	        	height: 190,
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
			}*/
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
    			Gnt.photo.setPhoto(selRes, false);
    		}
		}
	});
	
	ywGrid = new Gnt.ux.SjpzGrid({
		pkname: 'ywid',
		pzlb: '10007',
		region:'north',
		title: '新补录人员列表',
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
    		}
		}
	});
	
	form_yw = new Gnt.ux.SjpzForm({
		title: '户籍补录信息登记',
		closable: false,
		region:'center',
		height:330,
		pzlb: '10007',
		labelWidth :  160,
		cols:2,
		//选择户成员用
		bindViewGridHCY:hcyGrid,
		//bindStore:hcyGrid.store,
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
    	fieldBlur:function(field){
    		var form = form_yw.getForm();
    		var r = ywGrid.getSelectionModel().getSelected();
    		/**
    			输入了身份证号并且出生日期未选择
    			自动设置出生日期
    		 */
        	if(field.name=='gmsfhm'){
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
        	
        	if(field.name=='csrq'){
        		if(form.findField("csrq").getValue()){
        			SetReadOnly(form_yw, 'yxqxqsrq', false);
        		}else{
        			SetReadOnly(form_yw, 'yxqxqsrq', true);
        			
        			form.findField("yxqxqsrq").setValue("");
        			form.findField("yxqxjzrq").setValue("");
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
        				
        				form.findField("yxqxjzrq").setValue(vYear + vMonth + vDay);
        			}else if(nl >= 16 && nl <= 25){
        				vYear = Number(vYear) + 10;
        				
        				form.findField("yxqxjzrq").setValue(vYear + vMonth + vDay);
        			}else if(nl >= 26 && nl <= 45){
        				vYear = Number(vYear) + 20;
        				
        				form.findField("yxqxjzrq").setValue(vYear + vMonth + vDay);
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
        	var f = field.findParentByType("sjpz_form");
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
		title: '申报人信息',
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
        fieldChange:function(field,newValue,oldValue){
        	/**
        		手动输入申报人姓名时后台获取不到
        		修改了申报人姓名后调用方法赋值
        	 */
        	if(field.name == "sbryxm"){
        		form_sbr.fieldSetValue(field,newValue);
        	}
        }
        
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
	    	   items:[form_hcy]
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
										text:'人口增加',
										minWidth:100,
										handler:function(){
											addRyxx();
										}
						    	    }),{
						    	    	height:3,
						    	    	border:false,
						    	    	frame:false
						    	    },new Ext.Button({
										text:'人口删除',
										disabled:true,
										minWidth:100,
										id:'bbtnDelete',
										handler:function(){
											
											if(curIndex > 0){
												
												showQuestion("数据未保存，确定删除人口吗？", function(btnType){
													if(btnType=="yes"){
														delRyxx();
													}
												});
											}
											
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
						    	    		Gnt.yw.save(9, false, "7");
						    	    	}
						    	    }/*,new Ext.Button({
										text:'保存',
										minWidth:100,
										handler:function(){
											Gnt.yw.save(9, false, "7");
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
						    	    })
					    	 ]
			    	    }
			    	 ]
			    }
		]
	});
	
	blwcGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		pzlb: '20000',
		title: '',
		url: 'cx/hjjbxx/ckxx/getXxxx.json'
	});
	
	form20000 = new Gnt.ux.SjpzForm({
		closable: false,
		pzlb: '20000',
		region:'center',
		labelWidth :  120,
		cols:2,
		//绑定bindViewGrid，当bindViewGrid点击的时候，触发记录移动
		bindViewGrid: blwcGrid,
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
			    	   html: '<center><br/><br/><h2>户籍补录业务办理完成！</h2></center>'
				}, {
		    	   region:'center',
		    	   title:'',
		    	   frame:false,
				   borde:false,
	    	        items:[tb,form20000]
		       },{
				   region:'south',
				   height:40,
				   html: '',
				   layout:'table',
				   bodyStyle:'padding:10px',
				   frame:false,
				   borde:false,
				   items:[
			           new Ext.Button({
							text:'新办理',
							minWidth:100,
							width:150,
							disabled:hzywid?true:false,
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
				    	ywcode:'108'
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
											
											url += "id=" +  selRes.data.hjywid;
			   								url += "&ywcode=108&ryid=" +  selRes.data.ryid;
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
							text:'户籍补录审批表打印',
							minWidth:100,
							width:150,
							disabled:true,
							handler:function(){
								//printfunction(0,hjblidArray);
							}
			           }),{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		    		},new Ext.Button({
							text:'常表打印',
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
							text:'证件办理',
							minWidth:100,
							width:150,
							disabled:true,
							handler:function(){
								var url = basePath + "yw/edzjgl/zjbl";			      
			    				if(parent && parent.openWorkSpace){
			    					parent.openWorkSpace(url,  "证件办理", "zjbl");
			    				}else{
			    					window.location.href = url;
			    				}
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
			           })
			        ]
		       }
		]
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
			    	   html: '<center><br/><br/><h2>户籍补录审批信息保存成功！</h2></center>'
				}, {
			    	   region:'center',
			    	   title:'',
			    	   frame:false,
					   borde:false
		    	        
			       },{
				   region:'south',
				   height:100,
				   html: '',
				   layout:'table',
				   frame:false,
				   borde:false,
				   layoutConfig: {
	   	    	 		columns:20
	   	    	 	},
				   items:[{
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
			    	    	ywcode:'108'
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
									
									url += "id=" +  selRes.data.hjywid;
	   								url += "&ywcode=108&ryid=" +  selRes.data.ryid;
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
							text:'户籍补录审批表打印',
							//id:'hjblspbPrint',
							minWidth:100,
							width:150,
							handler:function(){
								printfunction(0,hjblidArray);
							}
			           }),{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			    		},new Ext.Button({
							text:'常表打印',
							id:'cbPrint',
							minWidth:100,
							width:150,
							disabled:true,
							handler:function(){
//								printRk(3,selectHhnbid,selectRynbid,[true,true,true,true,true],[true,true,false,false,false],[true,true,true,true,true]);
							}
			           }),{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			    		},new Ext.Button({
							text:'证件办理',
							minWidth:100,
							width:150,
							disabled:true,
							handler:function(){
								var url = basePath + "yw/edzjgl/zjbl";			      
			    				if(parent && parent.openWorkSpace){
			    					parent.openWorkSpace(url,  "证件办理", "zjbl");
			    				}else{
			    					window.location.href = url;
			    				}
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
        	items:[p1, p2, p3, p4]
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
	title:'确认变更更正项目'
});

//提交数据
function submitData(bggzList){
	var subdata = {
		lhhdxx: select_lhdz,
		rhhdxx:{
			hhnbid: Hhnbid
		},
		bggzxx:bggzList,
		sbjbxx:{},
		blList:new Array(),
		chList:new Array()
	};
	if(select_lhdz){
		lhHlx = select_lhdz.hlx;
	}
	hzCount=0;
	//人员内部id和上笔户籍业务的关系
	var map = {};
	hcyGrid.store.each(
		function(rec){
			map[rec.data.rynbid] = rec.data.cjhjywid;
			if(rec.data.yhzgx=='01'||rec.data.yhzgx=='02'||rec.data.yhzgx=='03'){
				hzCount++;
			}
		},	hcyGrid
	);
	
	//补录信息
	for(var index=0;index<ywGrid.store.getCount();index++){
		var data = ywGrid.store.getAt(index).data;
		data.sbhjywid = map[data.rynbid];
		if(lhHlx){//add by zjm 20200310 增加户类型和户别匹配验证
			if((lhHlx==1&&data.hb==7)||(lhHlx==2&&data.hb==6)){
				alert("户类型和户别不匹配!");
				return;
			}
		}
		
//		if(data.yhzgx=='01'||data.yhzgx=='02'||data.yhzgx=='03'){
//			hzCount++;
//		}
		
		subdata.blList.push(data);
	}
//	if(hzCount>1){
//		alert("不能包含多个户主！");
//		return;
//	}
	//申报人信息
	subdata.sbjbxx = form_sbr.getForm().getValues();
	
	//关键，户政业务ID
	if(hzywjo){
		subdata.sbjbxx.hzywid = hzywjo.id;
	}
	
	subdata.sbjbxx = Ext.encode(subdata.sbjbxx);
	subdata.lhhdxx = Ext.encode(subdata.lhhdxx);
	subdata.rhhdxx = Ext.encode(subdata.rhhdxx);
	subdata.bggzxx = Ext.encode(subdata.bggzxx);
	subdata.blList = Ext.encode(subdata.blList);
	
	///yw/hjyw/qczx
	log_code = "F1009";
	Gnt.submit(
		subdata, 
		"yw/hjyw/hjbl/processHjblyw", 
		"正在处理户籍补录信息，请稍后...", 
		function(jsonData,params){
			showInfo("户籍补录成功！",function(){
				var hhnbidTemp = "";
				var rynbidTemp = "";
				var ryidTemp = "";
				Ext.getCmp('card').getLayout().setActiveItem(2);
				Ext.getCmp('vp').doLayout();
				if(jsonData && jsonData.list[0]){
					hhnbidTemp = jsonData.list[0].voHjblfhxx[0].hhnbid;
					rynbidTemp = jsonData.list[0].voHjblfhxx[0].rynbid;
					ryidTemp = jsonData.list[0].voHjblfhxx[0].ryid;;
					selectHhnbid = jsonData.list[0].hhnbid;
					selectRynbid ="";
					if(jsonData.list[0].voHjblfhxx){
						for(var i=0; i < jsonData.list[0].voHjblfhxx.length; i++){
							selectRynbid += jsonData.list[0].voHjblfhxx[i].rynbid + ",";
							var o={};
							o.flag = 1;
							o.hjblid=jsonData.list[0].voHjblfhxx[i].hjblid;
							o.directTable="hjblspb";
							hjblidArray.push(o);
						}
						
						if(selectRynbid.length > 0){
							selectRynbid = selectRynbid.substr(0,selectRynbid.length - 1);
						}
						if(hzywjo&&hzywjo.id){
							Gnt.ux.Dict.getKzcs('10036', function(pz, user) {
								if(pz.kzz==1){
									pjq('ES',user.ip,hzywjo.id);
								}
							});
						}
						//printRk(3,selectHhnbid,selectRynbid,[true,true,true,true,true],[true,true,false,false,false],[true,true,true,true,true]);
					}
				}
				
				
				if(bggzWin.isVisible())
					bggzWin.hide();
				Gnt.toFormReadyonly(form20000);
				
				var store = blwcGrid.store;
				store.baseParams = {
						rynbid:rynbidTemp,
						hhnbid:hhnbidTemp,
						ryid:ryidTemp,
						config_key:'queryPoHJXX_CZRKJBXXB4',
						start:0,
						limit:20
					};
				store.load();
				
				store.on("load",function(store) {
					if(store.data.length > 0){
						curIndex = 0;
						
						/**
							往表单设值
						 */
						var pkvalue = store.getAt(0).data[form20000.bindStore.pkname];
						var re = form20000.bindStore.getById(pkvalue);
						if(re){
							form20000.getForm().loadRecord(re);
						}
						
					}
				},blwcGrid);
				
//				printRk(1,jsonData.list[0].hhnbid,null,[true,true,true,true,true],[true,true,false,false,false],[true,true,true,true,true]);
				
				//关闭当前窗口
				if(parent.closeActiveWorkSpace)
					parent.closeActiveWorkSpace(jsonData);
				
			
			});
		},
		function(json){
			if(json.message){
				if(json.errcode && json.errcode==512){
					//需要审批，调用审批模板
					new Gnt.ux.SpclDialog({
						callback:function(data){
							var spmbid = json.spmbid;
							data.spmbid = spmbid;
							if(data.cl) data.cl = Ext.encode(data.cl);
							data.blList = subdata.blList;
							data.rhhdxx = subdata.rhhdxx;
							data.lhhdxx = subdata.lhhdxx;
							data.sbjbxx = subdata.sbjbxx;
							data.queryXx = "queryhxx";
							
							if(hzywjo){
								data.hzywid = hzywjo.id;
							}
							
							Gnt.submit(
									data, 
									"yw/hjyw/hjbl/processHjblspdjyw", 
									"正在处理户籍补录审批登记信息，请稍后...", 
									function(jsonData,params){
										showInfo("户籍补录审批信息保存成功！",function(){
											if(bggzWin.isVisible())
												bggzWin.hide();
											for(var i=0; i < jsonData.list[0].voHjblspdjfhxx.length; i++){
												var o={};
												o.flag = 1;
												o.spywid=jsonData.list[0].voHjblspdjfhxx[i].spywid;
												o.directTable="hjblspb";
												hjblidArray.push(o);
											}
											if(hzywjo&&hzywjo.id){
												Gnt.ux.Dict.getKzcs('10036', function(pz, user) {
													if(pz.kzz==1){
														pjq('ES',user.ip,hzywjo.id);
													}
												});
											}
//											Gnt.toFormReadyonly(form20000);
//											var store = blwcGrid.store;
//											store.baseParams = {
//												rynbid:selectRynbid,
//												hhnbid:selectHhnbid,
//												config_key:'queryPoHJXX_CZRKJBXXB4',
//												start:0,
//												limit:20
//											};
//											store.load();
//											
//											store.on("load",function(store) {
//												if(store.data.length > 0){
//													curIndex = 0;
//													
//													/**
//														往表单设值
//													 */
//													var pkvalue = store.getAt(0).data[form20000.bindStore.pkname];
//													var re = form20000.bindStore.getById(pkvalue);
//													if(re){
//														form20000.getForm().loadRecord(re);
//													}
//													
//												}
//											},blwcGrid);
											//Ext.getCmp('hjblspbPrint').disable();
											//Ext.getCmp('cbPrint').disable();
											Ext.getCmp('card').getLayout().setActiveItem(3);
											Ext.getCmp('vp').doLayout();
											printfunction(0,hjblidArray);
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
