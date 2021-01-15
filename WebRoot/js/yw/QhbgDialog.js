Gnt.ux.QhbgDialog = Ext.extend(Ext.Window, {
	title:'全户变更',
	closeAction:'close',
	modal:true,
	width:950,
	height:400,
	margins:'5',
	layout:'border',
	initHxx: function(hxx,hzywjo){
		this.hxx = hxx;
		this.hzywjo = hzywjo;
		//add modify 20190927 全户变更，中间表带值
		if(hzywjo){
			if(!Gnt.util.isEmpty(hzywjo.hlx)){
				hxx.hlx=hzywjo.hlx;
			}
			if(!Gnt.util.isEmpty(hzywjo.jlx)){
				hxx.jlx=hzywjo.jlx;
			}
			if(!Gnt.util.isEmpty(hzywjo.mlph)){
				hxx.mlph=hzywjo.mlph;
			}
			if(!Gnt.util.isEmpty(hzywjo.lhdz)){
				hxx.mlxz=hzywjo.lhdz;
			}
			if(!Gnt.util.isEmpty(hzywjo.jcwh)){
				hxx.jcwh=hzywjo.jcwh;
			}
		}
		if(hxx.mlph){
			hxx.mlph = ToDBC(hxx.mlph);
		}
		if(hxx.mlxz){
			hxx.mlxz = ToDBC(hxx.mlxz);
		}
		this.bggzForm.getForm().setValues(hxx);
	},
	initComponent : function(){
		//10025 Hj_全户变更信息表
		//10002 Hj_申报人信息
		if(!Gnt.loadSjpzb("10025,10002",function(){}))
			return;

		var bggzForm = new Gnt.ux.SjpzForm({
			region:'center',
			pkname: 'hhnbid',
			closable: false,
			pzlb: '10025',
			labelWidth : 160,
			cols:2,
			title:'变更信息',
			fieldBlur:function(field){
				var form = bggzForm.getForm();
				if(field.name=='mlph'){
					bggzForm.fieldSetValue(form.findField("mlph"),ToDBC(form.findField("mlph").getValue()));
				}
				if(field.name=='mlxz'){
					bggzForm.fieldSetValue(form.findField("mlxz"),ToDBC(form.findField("mlxz").getValue()));
				}
			}
		});
		this.bggzForm = bggzForm;
		
		var sbrForm = new Gnt.ux.SjpzForm({
			region:'south',
			pkname: 'hh',
			closable: false,
			pzlb: '10002',
			height:100,
			labelWidth : 160,
			cols:2,
			title:'申报人信息',
			//选择人的来源
			bindSelectRyStore: this.ryxxPanel.grid10019_2.store,
			changeDictCust:function(cmb,res){
				//当选择人后，为其它域赋值
				this.getForm().setValues({sbrgmsfhm:res.data.data});
				return;
			}
		});
		this.sbrForm = sbrForm;
		
		this.items = [
		      bggzForm, sbrForm
		]
		
		//不要用new Button，否则多次弹出可能存在按钮无法显示的BUG
		this.buttons = [{
							text:'保存',
							minWidth:75,
							handler:function(){
								var win = this.findParentByType("qhbg_dialog");
								var sbrForm = win.sbrForm;
								var bggzForm = win.bggzForm;
								
								if(!bggzForm.getForm().isValid()){
									showErr("变更信息必须填写！");
									return;
								}
								
								if(!sbrForm.getForm().isValid()){
									showErr("申报人信息必须填写！");
									return;
								}
								
								var bgdata = bggzForm.getForm().getValues();
								bgdata.hhnbid = win.hxx.hhnbid;
								var sbrdata = sbrForm.getForm().getValues();
								if(win.hzywjo){
									sbrdata.hzywid = win.hzywjo.id;
									bgdata.jcwh = win.hzywjo.jcwh;
									var subdata = {
											config_key: 'queryPoHZ_ZJ_SB',
											hzywid:win.hzywjo.id
									};
									Gnt.submit(
											subdata, 
											"yw/common/queryHzywAndZB.json", 
											"请稍后...", 
											function(jsonData,params){
												if(jsonData.list && jsonData.list.length>0){
													var hzFlag = false;
													for(var i = 0; i < jsonData.list.length; i++){
														var jo = jsonData.list[i];
														if(jo.clbs !=0){
															hzFlag = true;
															break;
														}
													}
													if(hzFlag){
														showInfo("户政业务受理环节未处理完成");
														return;
													}else{
														var data = {
																sbjbxx: Ext.encode(sbrdata),
																hbbgxx: Ext.encode([bgdata])
															};
															Gnt.submit(
																data, 
																"yw/hjyw/bggz/processQhbgyw", 
																"正在进行全户变更，请稍后...", 
																function(jsonData,params){
																	if(win.hzywjo){
								            							Gnt.ux.Dict.getKzcs('10036', function(pz, user) {
								            								if(pz.kzz==1){
								            									pjq('ES',user.ip,win.hzywjo.id);
								            								}
								            							});
								            						}
																	if(win.qhbgCallback){
																		win.qhbgCallback(jsonData);
																	}
																	
																	win.hide();
																}
															);
													}
												}else{
													showInfo("没有找到户政业务，或者已经被处理！");
													return;
												}
											}
										);
								}else{
									bgdata.jcwh = win.hxx.jcwh;
									var data = {
											sbjbxx: Ext.encode(sbrdata),
											hbbgxx: Ext.encode([bgdata])
										};
										Gnt.submit(
											data, 
											"yw/hjyw/bggz/processQhbgyw", 
											"正在进行全户变更，请稍后...", 
											function(jsonData,params){
												if(win.qhbgCallback){
													win.qhbgCallback(jsonData);
												}
												
												win.hide();
											}
										);
								}
								
							}
					},{
						text:'关闭',
						minWidth:75,
						handler:function(){
							var win = this.findParentByType("qhbg_dialog");
							win.hide();
						}
				}
		];
		      
		Gnt.ux.QhbgDialog.superclass.initComponent.call(this);
	}
});
Ext.reg('qhbg_dialog', Gnt.ux.QhbgDialog);
//半角转全角
function ToDBC(str) {
	  var result = '';
	  for(var i=0; i < str.length; i++){
	    code = str.charCodeAt(i);
	    if(code >= 33 && code <= 126){
	      result += String.fromCharCode(str.charCodeAt(i) + 65248);
	    }else if (code == 32){
	      result += String.fromCharCode(str.charCodeAt(i) + 12288 - 32);
	    }else{
	      result += str.charAt(i);
	    }
	  }
	  return result 
} 
