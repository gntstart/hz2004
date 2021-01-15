var selRes = null;
var curIndex = -1;
var resultIndex = -1;
var hzywjo_list;
var hzywjo;
var selectRynbid = "";
var selectHhnbid = "";
var selectHjywid = null;
var selectQczxid = "";
var ckFlag = null;

var new_ssxq = "";
var old_ssxq = "";
var xt_bssqb_list = null;
var hzywidTemp = "";
var gatgwFlag = false;
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	var hzywid = getQueryParam("hzywid");
	var pch = getQueryParam("pch");
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10016,20016,10019,10002,10024",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX','CS_GSJC'],function(){});
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
	function initBdfw(){
		var f = form10024.getForm().findField("bdfw");

		/**
			迁出类别/迁往地省市县区
			必须先选择/填值	才可以选择变动范围
		 */
		var formValues = form10024.getForm().getValues();

		if(xt_bssqb_list==null){
			showInfo("没有找到市区配置信息，无法过滤变动范围！");
			return;
		}
		
		var qclb = formValues.qclb;
		var new_ssxq = formValues.qwdssxq;
		
		var fwflag = true;
		Ext.each(xt_bssqb_list, function(data){
			if(data.qhdm==old_ssxq || data.qhdm==new_ssxq){
				if(data.sqbz=='0')
					fwflag = false;
			}
		});
		
		f.filter =function(data){
			if(qclb=='0481' || qclb=='0471'){
				if(data[0]=='51' || data[0]=='52' || data[0]=='53')
					return true;
				else
					return false;
			}
			
			//2同市同区县内迁移变动范围是 21 区(县)内他所(乡) 
			if(old_ssxq==new_ssxq){
				if(data[0]=='21')
					return true;
				else
					return false;
			}
			
			//同市，跨区县 sqbz(市区标志)都为1的情况下变动范围是31市内城镇32	市内郊区，否则为 33	市外乡村34市外城镇
			if(new_ssxq.length>4 && new_ssxq.substring(0,4)==old_ssxq.substring(0,4) && old_ssxq!=new_ssxq){
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
			
			//7、	迁出地为710000 台湾省 变动范围62台湾地区
			if(new_ssxq.length>4 && new_ssxq=='710000'){
				if(data[0]=='62'){
					return true;
				}else{
					return false;
				}
			}
			
			//8、	迁出地为 810000 820000 变动范围为 61港、澳地区
			if(new_ssxq.length>4 && (new_ssxq=='810000' || new_ssxq=='820000') ){
				if(data[0]=='61'){
					return true;
				}else{
					return false;
				}
			}
			
			//4、	迁出地省市县区为省内市外的 变动范围是 41省内乡村、42省内城镇、43省内城市
			if(new_ssxq.length>4 && new_ssxq.substring(0,2)==old_ssxq.substring(0,2) && new_ssxq.substring(0,4)!=old_ssxq.substring(0,4)){
				if(data[0]=='41' || data[0]=='42' ||  data[0]=='43'){
					return true;
				}else{
					return false;
				}
			}
			
			//5、	迁出地省市县区为国内省外的（不包括港澳台） 变动范围是 51省外乡村、 52省外城镇、 53省外城市
			if(new_ssxq.length>4 && new_ssxq.substring(0,3)!='010' && new_ssxq.substring(0,2)!=old_ssxq.substring(0,2)){
				if(data[0]=='51' || data[0]=='52' ||  data[0]=='53'){
					return true;
				}else{
					return false;
				}
			}
			
			//6、	迁出地为国外的 如010004阿富汗 变动范围为71国外
			if(new_ssxq.length>4 && new_ssxq.substring(0,3)=='010'){
				if(data[0]=='71'){
					return true;
				}else{
					return false;
				}
			}
			
			return true;
		};
		f.reloadDict();
	
	
		
	}	
	//迁出人员选择
	var p1 = new Gnt.ux.SelectRyxxPanel({
		returnBtnText:'确定',
		returnBtnMsg:'必须选择注销人员',
		select:function(list, hxx){
			old_ssxq = hxx.ssxq;
			
			curIndex = 0;
			
			//点击确认迁出后，执行此方法
			Ext.getCmp('card').getLayout().setActiveItem(1);
			//界面更新到下一步
			//v.remove(v.getComponent(0));
			//v.add(p2);
			//v.doLayout();
			
			//户成员初始化
			var store = hcyGrid.store;
			store.removeAll();
			
			//初始化迁出登记store
			var newres = new Array();
			var qcdbdfw = "";
			
			for(var i=0;i<list.length;i++){
				var data = list[i];
				var rr = new store.reader.recordType(data, data.rynbid);
				
				
				
				if(data._sel=='1'){
					//迁出人员，那么初始化迁出登记信息
					if(qcdbdfw=="" && data.qcdbdfw){
						qcdbdfw = data.qcdbdfw;
					}
					
					var pk = data[qcdjStore.pkname];
					var r = new qcdjStore.reader.recordType({}, pk);
					r.set(qcdjStore.pkname, pk);
					//"kdqqy_zqz","kdqqy_qrdz","kdqqy_qyldyy","kdqqy_qczxlb"
					if(data.kdqqy_zqzbh) r.set("zqzbh",data.kdqqy_zqzbh);
					if(data.kdqqy_qrdz) r.set("qwdxz",data.kdqqy_qrdz);
					if(data.kdqqy_qrdqh) r.set("qwdssxq",data.kdqqy_qrdqh);
					if(data.kdqqy_qyldyy) r.set("qyldyy",data.kdqqy_qyldyy);
					if(data.kdqqy_qczxlb) r.set("qclb",data.kdqqy_qczxlb);
					r.set("qcrq",Ext.util.Format.date(new Date(),'Ymd'));
					SetReadOnly(form10024, 'qcrq', true);
					if(qcdbdfw!=""){
						r.set("bdfw",qcdbdfw);
					}
					
					if(hzywjo_list){
						Ext.each(hzywjo_list, function(jo){
							if(jo.bsqrsfz == data.gmsfhm){
								
								r.set("qclb",jo.qclb);
								
								ckFlag = ckCheck(jo.qclb);
								
								r.set("qwdssxq",jo.qwdssxq);
								/**
	    							2018.08.16
	    							修改	刁杰
	    							线上要求变动范围不从户政平台获取
								 */
								r.set("bdfw",jo.bdfw);
								
								r.set("qyldyy",jo.qyldyy);
								r.set("sbrjtgx",jo.sbrjtgx);
								if(!Gnt.util.isEmpty(jo.bsqrsjhm)){// add by zjm 20190627 增加号码带入值设置  户政平台有值就替换过来，否则不变
									rr.set("dhhm",jo.bsqrsjhm);
								}
								/*
								 * 20190912 add by zjm 
								 * 迁往地祥址
								 */
								if(!Gnt.util.isEmpty(jo.qwdxz)){
									r.set("qwdxz",jo.qwdxz);
								}
								if(!Gnt.util.isEmpty(jo.rkzp)){
									r.set("zp",jo.rkzpBase64);
									rkzpMap[data.rynbid] = jo.rkzpBase64;
								}
								/**
									自动分配准迁证编号
									HZ_ZJ_BS 表中 
									zqzbh字段有值:设置准迁证编号
									zqzbh字段无值:根据qwdssxq字段获取简称
								 */
								var qwdssxq = jo.qwdssxq;
								if(qwdssxq && qwdssxq.length >= 6){
									var code = qwdssxq.substring(0,2) + "0000";
									var name = Gnt.ux.Dict.getDictLable('CS_GSJC',code);
									if(code!=name){
										var zqz = jo.zqzbh;
										if(zqz && typeof(zqz) != "undefined" && zqz != 0){
											r.set("zqzbh",jo.zqzbh);
										}else{
											if(name && typeof(name) != "undefined" && name != 0){
												r.set("zqzbh",name);
											}
										}
									}
								}
								hzywidTemp += "," + jo.id;
								Gnt.util.copyHzywToRyzl(r,  jo);
								
							}
						});
					}
					
					newres.push(r);
				}
				store.add([rr]);
			}
			
			if(newres.length>0)
				qcdjStore.add(newres);
			
			if(hzywjo){
				form_sbr.getForm().setValues({
					sbryxm: hzywjo.sqrxm,
					sbrgmsfhm: hzywjo.sqrsfz
				});
			}
            
			(function(){
				hcyGrid.fireEvent("rowclick",hcyGrid,0);
				hcyGrid.getSelectionModel().selectRange(0,0);
			}).defer(200);
			
			/**
				2018.08.17
				修改	刁杰
				迁出日期默认当天，并且不允许修改，设置日期选择范围没有意义
			 */
//			form10024.getForm().findField("qcrq").setMinValue(Ext.util.Format.date(new Date(),'Ymd'));
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
    			SetReadOnly(form_hcy, 'cym', true);
    			//迁出登记信息初始化
    			form10024.getForm().reset();
    			
    			if(selRes.data._sel=="1"){
    				//为迁出登记form赋值
    				form10024.setVisible(true);
    				var pkvalue = selRes.data[form10024.bindStore.pkname];
    				var re = form10024.bindStore.getById(pkvalue);
    				if(re){
    					form10024.getForm().loadRecord(re);
    					initBdfw();
    	    			var validBdfw = form10024.getForm().findField("bdfw").store.data;
    					var bdfwFlag = false
    					if(re&&re.data.bdfw){
    						Ext.each(validBdfw.keys, function(data){
    							if(data==re.data.bdfw){
    								bdfwFlag = true;
    								//newlist.push(data);
    							}
    						});
    						if(!bdfwFlag){
    							form10024.getForm().setValues({bdfw:""});
    							re.data.bdfw="";
    						}else{
    							form10024.getForm().setValues({bdfw:re.data.bdfw});
    						}
    					}
    				}else{
    					alert("警告：迁出登记信息" + pkvalue + "不存在！");
    				}
    				
    				if(rkzpMap[selRes.data.rynbid]){
    					selRes.data.zp=rkzpMap[selRes.data.rynbid];
    					Gnt.photo.setPhoto(null, true);
        				Gnt.photo.setPhoto(selRes, true);
    				}else{
    					Gnt.photo.setPhoto(null, true);
        				Gnt.photo.setPhoto(selRes, true);
    				}
    			}else{
    				//不是迁出人员，隐藏
    				form10024.setVisible(false);
    				
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
        	if(form_hcy.getForm().isDirty()){
        		Ext.getCmp("cancelUpdId").setDisabled(false);
        	}else{
        		Ext.getCmp("cancelUpdId").setDisabled(true);
        	}
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
	var qcdjStore = new Gnt.store.SjpzStore({
		pzlb:'10024',
		pkname:'rynbid'
	});
	
	//典型：bindStore和bindViewGrid不一样
	var form10024 = new Gnt.ux.SjpzForm({
		title: '迁出信息登记',
		closable: false,
		/*region:'center',
		height:60,*/
		pzlb: '10024',
		labelWidth :  160,
		cols:2,
		//绑定到store，输入域改动的时候，自动赋值
		bindStore: qcdjStore,
		//绑定bindViewGrid，当bindViewGrid点击的时候，触发记录移动
		bindViewGrid: hcyGrid,
        fieldFocus:function(field){
        	var f = field.findParentByType("sjpz_form");        	
        	if(field.name=='bdfw'){
        		/**
        			迁出类别/迁往地省市县区
        			必须先选择/填值	才可以选择变动范围
        		 */
        		var formValues = form10024.getForm().getValues();

    			if(xt_bssqb_list==null){
    				showInfo("没有找到市区配置信息，无法过滤变动范围！");
    				return;
    			}
    			
    			var qclb = f.getForm().findField("qclb").getValue();
    			var new_ssxq = f.getForm().findField("qwdssxq").getValue();
    			
    			var fwflag = true;
    			Ext.each(xt_bssqb_list, function(data){
    				if(data.qhdm==old_ssxq || data.qhdm==new_ssxq){
    					if(data.sqbz=='0')
    						fwflag = false;
    				}
    			});
    			
    			field.filter =function(data){
    				if(qclb=='0481' || qclb=='0471'){
    					if(data[0]=='51' || data[0]=='52' || data[0]=='53')
    						return true;
    					else
    						return false;
    				}
    				
    				//2同市同区县内迁移变动范围是 21 区(县)内他所(乡) 
    				if(old_ssxq==new_ssxq){
    					if(data[0]=='21')
    						return true;
    					else
    						return false;
    				}
    				
    				//同市，跨区县 sqbz(市区标志)都为1的情况下变动范围是31市内城镇32	市内郊区，否则为 33	市外乡村34市外城镇
    				if(new_ssxq.length>4 && new_ssxq.substring(0,4)==old_ssxq.substring(0,4) && old_ssxq!=new_ssxq){
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
    				
    				//7、	迁出地为710000 台湾省 变动范围62台湾地区
    				if(new_ssxq.length>4 && new_ssxq=='710000'){
    					if(data[0]=='62'){
    						return true;
    					}else{
    						return false;
    					}
    				}
    				
    				//8、	迁出地为 810000 820000 变动范围为 61港、澳地区
    				if(new_ssxq.length>4 && (new_ssxq=='810000' || new_ssxq=='820000') ){
    					if(data[0]=='61'){
    						return true;
    					}else{
    						return false;
    					}
    				}
    				
    				//4、	迁出地省市县区为省内市外的 变动范围是 41省内乡村、42省内城镇、43省内城市
    				if(new_ssxq.length>4 && new_ssxq.substring(0,2)==old_ssxq.substring(0,2) && new_ssxq.substring(0,4)!=old_ssxq.substring(0,4)){
    					if(data[0]=='41' || data[0]=='42' ||  data[0]=='43'){
    						return true;
    					}else{
    						return false;
    					}
    				}
    				
    				//5、	迁出地省市县区为国内省外的（不包括港澳台） 变动范围是 51省外乡村、 52省外城镇、 53省外城市
    				if(new_ssxq.length>4 && new_ssxq.substring(0,3)!='010' && new_ssxq.substring(0,2)!=old_ssxq.substring(0,2)){
    					if(data[0]=='51' || data[0]=='52' ||  data[0]=='53'){
    						return true;
    					}else{
    						return false;
    					}
    				}
    				
    				//6、	迁出地为国外的 如010004阿富汗 变动范围为71国外
    				if(new_ssxq.length>4 && new_ssxq.substring(0,3)=='010'){
    					if(data[0]=='71'){
    						return true;
    					}else{
    						return false;
    					}
    				}
    				
    				return true;
    			};
    			field.reloadDict();
    		
        	}
        	
            Gnt.filterField(field);
        	
        },
        fieldChange:function(field){
        	var f = field.findParentByType("sjpz_form");
        	
        	if(field.name=="qwdssxq"){
        		var qwdssxq = field.getValue();
        		getXZqhJx(qwdssxq,f,false);
        		
        		if(f.getForm().findField("bdfw").getValue()){
        			f.getForm().findField("bdfw").setValue('');
        		}
        		
        	}

        	if(field.name=='qclb'){
        		if(f.getForm().findField("bdfw").getValue()){
        			f.getForm().findField("bdfw").setValue('');
        		}
        		
        		ckFlag = ckCheck(f.getForm().findField("qclb").getValue());
        		
        	}
        	
        	if(field.name=='zqzbh'){
        		
        		if(Gnt.ux.getStrLength(field.getValue()) > 11){
        			showErr("【准迁证编号】长度超出限制！",function(){
						var qwdssxq = f.getForm().findField("zqzbh").getValue();
						getXZqhJx(qwdssxq,f,true);
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
        	
		}
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
	        	 height:230,
	             border:false,
	             frame:false,
	             items:[form_sbr,{
	     	        region:'south',
	    	        //禁止横向滚动条
	    	        layout:'border',
	    	        height:170,
	    	        border:false,
	    	        frame:false,
	    	        items:[form10024]
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
//									alert(qcdjStore.getAt(0).data.hjywid);
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
				    	    	id:'cancelUpdId',
								text:'取消修改',
								disabled:true,
								minWidth:100,
								handler:function(){
									
									/**
										取消修改,需要将修改过的记录复原
										表单复原的同时原户成员记录一并撤销修改
									 */
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
									
									Gnt.sameFirst(qcdjStore,hcyGrid,form10024);
									
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
				    	    	xtype: 'ZqzButton',
				    	    	form: form10024,
				    	    	callback:function(){
				    	    		if(user.flag!='1'){
				    	    			Ext.getCmp('saveId').enable();
				    	    		}
				    	    	}
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
				    	    		save();
				    	    	}
				    	    },/*new Ext.Button({
				    	    	id:'saveId',
								text:'保存',
								minWidth:100,
								disabled:true,
								handler:function(){
									save();
								}
				    	    }),*/{
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
	
	var qcwcGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		pzlb: '20000',
		title: '',
		url: 'cx/hjjbxx/ckxx/getXxxx.json'
	});
	
	var form20000 = new Gnt.ux.SjpzForm({
		closable: false,
		pzlb: '20000',
		region:'center',
		labelWidth :  120,
		cols:2,
		//绑定bindViewGrid，当bindViewGrid点击的时候，触发记录移动
		bindViewGrid: qcwcGrid,
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
			    	html: '<center><br/><br/><h2>迁出业务办理完成！</h2></center>'
				}, {
			    	   region:'center',
			    	   title:'',
			    	   frame:false,
					   borde:false
		    	        
			       },{
		    	   region:'south',
		    	   layout:'table',
		    	   bodyStyle:'padding:10px 25px 0px 20px',
		    	   title:'',
		    	   frame:false,
				   borde:false,
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
							disabled:hzywid != null?false:true,
							width:150,
							handler:function(){
								Gnt.ux.Dict.getKzcs('10029',function(pz, user){
									var url = pz.bz;
									if(url.indexOf("?")<0)
										url += "?";
									else
										url += "&";
									url += "yhsfz="+user.gmsfhm+"&tokey="+user.tokey+"&sbid=" + getQueryParam("sbid");
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
			    	    	form: form10024,
			    	    	disabled:true,
			    	    	ywcode:'106'
			    	    },{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
		    			},new Ext.Button({
							text:'证件收交',
							minWidth:100,
							width:150,
							handler:function(){
								if(selRes){
									var url = basePath + "yw/edzzjgl/zjsj?zjlqJumpTo="+"1"+"&ryid="+selRes.data.ryid;			      
			        				if(parent && parent.openWorkSpace){
			        					parent.openWorkSpace(url,  "证件收交", "zjsj");
			        				}else{
			        					window.location.href = url;
			        				}
								
								}else{
									alert("请先选中一条有效的数据!");
								}
							}
	    	            }),{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
		    			},new Ext.Button({
							text:'证件挂失',
							minWidth:100,
							width:150,
							handler:function(){
								if(selRes){
									var url = basePath + "yw/edzjgl/zjgs?zjlqJumpTo="+"1"+"&ryid="+selRes.data.ryid;			      
			        				if(parent && parent.openWorkSpace){
			        					parent.openWorkSpace(url,  "证件挂失", "zjgs");
			        				}else{
			        					window.location.href = url;
			        				}
								}else{
									alert("请先选中一条有效的数据!");
								}
							}
	    	            }),{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
		    			},new Ext.Button({
							text:'迁移证打印',
							minWidth:100,
							id:'qyzBtn',
							width:150,
							handler:function(){
								qyzPrint(selectHjywid,true);//增加个参数，用来表示，从业务过去的  add by zjm 20191120 
							}
	    	            }),{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
		    			},new Ext.Button({
							text:'跳转迁出注销查询按钮',
							minWidth:100,
							width:150,
							handler:function(){
								if(selectQczxid){
									var url = basePath + 'cx/hjywxx/qczxcx?jumpToQczx='+"1"+"&qczxid=" + selectQczxid;
									if(parent && parent.openWorkSpace){
										parent.openWorkSpace(url,"迁出注销查询", "qczxcx");
									}else{
										window.location.href = url;
									}
								}else{
									alert("无迁出信息返回，请检查是否迁出成功！");
								}
							}
	    	            }),{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
		    			},new Ext.Button({
							text:'常表户口簿打印',
							minWidth:100,
							width:150,
							handler:function(){
								printRk(3,selectHhnbid,selectRynbid,[true,true,true,true,true],[true,true,false,false,false],[true,true,true,true,true]);
							}
	    	            })
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
					text:'继续办理',
					minWidth:100,
					width:150,
					handler:function(){
						Ext.getCmp('card').getLayout().setActiveItem(0);
						p1.jxbl();
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
	
	function save(){
		if(!form_sbr.getForm().isValid()){
			showErr("申报人信息必须填写！");
			return;
		}
		if(!form10024.checkInput(false, "1", false)){
			return;
		}
		if(!checkQclb(form10024)){
			return;
		}
		
		if(!form_hcy.checkInput(false, "4", false, hcyGrid)){
			return;
		}
		
		if(!Gnt.validHz(2,form_hcy,form_hcy)){
			return ;
		}
		//判断非迁出户必须存在户主
		var exists = false;
		var icount = 0;
		hcyGrid.store.each(function(r){
			var data = r.data;
			/**
				2018.11.13
				修改	刁杰
				整户迁出时不需要判断户主
			 */
			if(data._sel=="1"){
				//迁出成员
			}else{
				icount++;
				if(data.yhzgx=="01" || data.yhzgx=="02" || data.yhzgx=="03"){
					exists = true;
				}
			}
			/*
			icount++;
			if(data.yhzgx=="01" || data.yhzgx=="02" || data.yhzgx=="03")
				exists = true;
			*/
		});
		
		if(!exists && icount > 0){
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
			qczxxx:new Array()
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
		if(hzywjo){
			//subdata.sbjbxx.hzywid = hzywjo.id;
			//改成和死亡注销一样逻辑，避免同一pch的多人不在同一户下，操作一个人其他都更新了业务数据  modify by zjm 20191128
			if(hzywidTemp){
				subdata.sbjbxx.hzywid = hzywidTemp.substr(1);
			}
		}
		//迁出注销信息
		for(var index=0;index<qcdjStore.getCount();index++){
			var data = qcdjStore.getAt(index).data;
			data.sbhjywid = map[data.rynbid];
			//迁往港澳台 国外,业务结束,迁移证按钮置灰 add by zjm 20200417
			if(data.qwdssxq=='810000'||data.qwdssxq=='820000'||data.qwdssxq=='710000'||data.qwdssxq.substring(0,3)=='010'){
				gatgwFlag = true;
			}
			subdata.qczxxx.push(data);
		}
		
		subdata.sbjbxx = Ext.encode(subdata.sbjbxx);
		subdata.bggzxx = Ext.encode(subdata.bggzxx);
		subdata.qczxxx = Ext.encode(subdata.qczxxx);
		///yw/hjyw/qczx
		log_code = "F1007";
		Gnt.submit(
			subdata, 
			"yw/hjyw/qczx/processQczxyw", 
			"正在处理迁出信息，请稍后...", 
			function(jsonData,params){
				showInfo("迁出成功！",function(){
					if(jsonData && jsonData.list[0] && jsonData.list[0].voQczxfhxx){
						selectRynbid="";
						selectHhnbid="";
						selectHjywid = jsonData.list[0].hjywid;
						selectQczxid = "";
						selectHhnbid=jsonData.list[0].voQczxfhxx[0].hhnbid;
						for(var i=0; i < jsonData.list[0].voQczxfhxx.length; i++){
							selectRynbid += jsonData.list[0].voQczxfhxx[i].rynbid + ",";
							selectQczxid += jsonData.list[0].voQczxfhxx[i].qczxid + ",";
						}
						
						if(selectRynbid.length > 0){
							selectRynbid = selectRynbid.substr(0,selectRynbid.length - 1);
							selectQczxid = selectQczxid.substr(0,selectQczxid.length - 1);
						}
						if(gatgwFlag){
							Ext.getCmp('qyzBtn').disable();
						}
						/**
							保存成功后弹出迁移证打印窗口
							迁出类别是 
							0482公职人员出国
							0483留学人员出国
							0484出国定居 
							0485迁往港澳 
							0486迁往台湾 
							0418 参军服兵役 
							这四个理由的迁出业务不要弹出打印迁移证界面，直接弹出打印常表户口的界面
						 */
						//form10024.getForm().findField("qwdssxq").getValue();
						if(ckFlag == null){
							if(ckCheck(form10024.getForm().findField("qwdssxq").getValue())){
								printRk(3,selectHhnbid,selectRynbid,[true,true,true,true,true],[true,true,false,false,false],[true,true,true,true,true]);
							}else{
								qyzPrint(selectHjywid,true);
							}
						}else{
							if(ckFlag){
								printRk(3,selectHhnbid,selectRynbid,[true,true,true,true,true],[true,true,false,false,false],[true,true,true,true,true]);
							}else{
								qyzPrint(selectHjywid,true);
							}
						}
						
					}
					if(hzywid){
						Gnt.ux.Dict.getKzcs('10036', function(pz, user) {
							if(pz.kzz==1){
								pjq('ES',user.ip,hzywid);
							}
						});
					}
					Ext.getCmp('card').getLayout().setActiveItem(2);
					Ext.getCmp('vp').doLayout();
										
//					
//					Gnt.toFormReadyonly(form20000);
//					
//					var store = qcwcGrid.store;
//					store.baseParams = {
//							rynbid:selectRynbid,
//							hhnbid:selectHhnbid,
//							config_key:'queryPoHJXX_CZRKJBXXB4',
//							start:0,
//							limit:20
//						};
//					store.load();
//					
//					store.on("load",function(store) {
//						if(store.data.length > 0){
//							curIndex = 0;
//							
//							/**
//								往表单设值
//							 */
//							var pkvalue = store.getAt(0).data[form20000.bindStore.pkname];
//							var re = form20000.bindStore.getById(pkvalue);
//							if(re){
//								form20000.getForm().loadRecord(re);
//							}
//							
//						}
//					},qcwcGrid);
					
					if(bggzWin.isVisible()){
						bggzWin.hide();
					}
					//关闭当前窗口
					if(parent.closeActiveWorkSpace){
						parent.closeActiveWorkSpace(jsonData);
					}
					
				});
			}
		);
	}
	
	function getXZqhJx(qwdssxq,f,type){
		if(qwdssxq.length>=6){
			var code = qwdssxq.substring(0,2) + "0000";
			var name = Gnt.ux.Dict.getDictLable('CS_GSJC',code);
			if(code!=name){
				var field2 = f.getForm().findField("zqzbh");
				if(field2){
					var v = field2.getValue();
					if(v==""){
						f.fieldSetValue(field2, name);
					}else{
						if(v.substring(0,1)!=name){
							v = name + v.substring(1);
							if (!v && typeof(v)!="undefined" && v!=0){
								f.fieldSetValue(field2, v);
							}else{
								f.fieldSetValue(field2, name);
							}
						}else if(type){
							/**
								2018.10.15
								新增	刁杰
								准迁证编号格式不正确时:设置选择迁往地省市县区简写
							 */
							f.fieldSetValue(field2, name);
						}
					}
        		}
			}
		}
	}
	
	function checkQclb(f){
		var re = f.bindStore.getById(f.getForm().findField("rynbid").getValue());
		var result = true;
		var qclb = f.getForm().findField("qclb").getValue();
		if(qclb){
			var qwd = f.getForm().findField("qwdssxq").getValue();
//			if(!qwd){
//				alert("迁往地省市县区为必填！");
//				return;
//			}
			if(qclb == "0484"||qclb == "0482"||qclb == "0483"){// add by zjm 20190412  增加0482 0483 限制
				/**
					0484 : 出国定居
					迁往地行政区划应为010开头
				 */
				if(qwd && qwd.substring(0,3) != "010"){
//					if(window.confirm("迁出类别和迁移省县不符,重新修正吗?")){
//						f.getForm().findField("qwdssxq").setValue('');
//						result = false;
//					}else{
//						result = true;
//					}
					// 20190412 modify by zjm  周要求改成不符合就不让办理
					showInfo("迁出类别和迁移省县不符，请修改！");
					f.getForm().findField("qwdssxq").setValue('');
					re.set("qwdssxq",'');
					result = false;
				}
			}else if(qclb == "0485"){
				/**
					0485 : 迁往港澳
					迁往地行政区划应为810000和820000
				 */
				if(qwd && qwd != "810000" && qwd != "820000"){
//					if(window.confirm("迁出类别和迁移省县不符,重新修正吗?")){
//						f.getForm().findField("qwdssxq").setValue('');
//						result = false;
//					}else{
//						result = true;
//					}
//					// 20190412 modify by zjm  周要求改成不符合就不让办理
					showInfo("迁出类别和迁移省县不符，请修改！");
					f.getForm().findField("qwdssxq").setValue('');
					re.set("qwdssxq",'');
					result = false;
				}
			}else if(qclb == "0486"){
				/**
					0486 : 迁往台湾
					迁往地行政区划应为710000
				 */
				if(qwd && qwd != "710000"){
//					if(window.confirm("迁出类别和迁移省县不符,重新修正吗?")){
//						f.getForm().findField("qwdssxq").setValue('');
//						result = false;
//					}else{
//						result = true;
//					}
					// 20190412 modify by zjm  周要求改成不符合就不让办理
					showInfo("迁出类别和迁移省县不符，请修改！");
					f.getForm().findField("qwdssxq").setValue();
					re.set("qwdssxq",'');
					result = false;
				}
			}
			///*qclb !="0485"&&qclb !="0486"&&(qwd.substring(2,6)=="0000"&&qwd=="810000")*/
			if((qwd!="810000"&&qwd!="820000"&&qwd!="710000")&&qwd.substring(2,6)=="0000"){//迁往地省市县区，加个限制  港澳台除外
				showInfo("变动省县不能够选择省级，必须为地市级或区县级！")
				result = false;
			}
		}
		
		return result;
		
	}
	
	/**
		0481 参军服兵役
		0484出国定居  
		0485迁往港澳 
		0486迁往台湾 
		0487
		0488
		0489
		0490
	 */
	function ckCheck(val){
		if(val == "0481" ||val == "0482" ||val == "0483" || val == "0484" || val == "0485" || val == "0486"|| val == "0487"|| val == "0488"|| val == "0489"|| val == "0490"){
			// add by zjm 20190412 增加了0482 0483 限制
			// add by zjm 20191127 增加了0487 0488  0489  0490限制
			return true;
		}else{
			return false;
		}
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
});