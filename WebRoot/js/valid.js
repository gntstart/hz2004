/**
	2018.10.11
	新增	刁杰
	户主性别为男/女时,将同性别的户成员关系改为配偶, 在保存需要做校验,弹框提示’家庭关系与性别不符,重新修正吗?’
	户主婚姻状态为已婚,将未婚状态的户成员关系改为配偶,在保存需要做校验,弹框提示’婚姻状况不符,重新修正吗?
	户成员性别为男,与户主关系选择妻/女/养母继母等,在保存时,需要做校验
	
	2018.10.16
	注释	刁杰
	以变更更正为例变更记录在户主之上,需要先获取户主信息,再对变更成员信息进行校验
	
	2018.10.17
	注释	刁杰
	与户主关系的验证与一般验证不同,先提示是否需要修改,再决定是否跳出
	
	2018.10.23
	新增	刁杰
	将SjpzForm验证户成员代码移植
	以出生为例:有业务表单及原户成员表单,获取了户主后是否还需要在原户成员中
	
*/
Gnt.validHz = function(type,hzForm,hcyForm) {
	
	if(hzForm.bindViewGrid){
		hzForm.bindViewGrid.make_Highlight();
	}
	/*
	if(hcyForm.bindViewGrid){
		hcyForm.bindViewGrid.make_Highlight();
	}
	*/
	var hzXb = null;
	var hzHyzt = null;
	var hzFlag = false;
	var pocount =0;
	var ary1 = Gnt.ux.Dict.getSjpzData(hzForm.bindStore.pzlb);
	for(var index = 0; index < hzForm.bindStore.getCount(); index++){
		//获取记录
		var r = hzForm.bindStore.getAt(index);
		//获取记录数据
		var data = r.data;
		var pkvalue = data[hzForm.bindStore.id];
		
		for(var i = 0; i < ary1.length; i++){
			var fname = ary1[i].fieldname;
			if(data._sel!='1'&&(type == '4'||type == '2'||type == '7')){
				if(data.yhzgx == "01" || data.yhzgx == "02" || data.yhzgx == "03"){
					if(data.xb){
						hzXb = data.xb;
					}
					if(data.hyzk){
						hzHyzt = data.hyzk;
					}
					hzFlag = true;
					break;
				}
			}
			
		}
		if(hzFlag){
			break;
		}
	}
	
	var markInvalid = null;
	var ary2 = Gnt.ux.Dict.getSjpzData(hcyForm.bindStore.pzlb);
	for(var index = 0; index < hcyForm.bindStore.getCount(); index++){
		//获取记录
		var r = hcyForm.bindStore.getAt(index);
		//获取记录数据
		var data = r.data;
		var pkvalue = data[hcyForm.bindStore.id];
		if(type == '4'||type == '2'||type == '7'){
			if(data.yhzgx &&data._sel!='1'&& (data.yhzgx == "10"||(data.yhzgx == "11"&&hzXb=="2")||(data.yhzgx == "12"&&hzXb=="1"))){
				pocount++;
			}
		}else{
			if(data.yhzgx && (data.yhzgx == "10"||(data.yhzgx == "11"&&hzXb=="2")||(data.yhzgx == "12"&&hzXb=="1"))){
				pocount++;
			}
		}
		
		for(var i = 0; i < ary2.length; i++){
			var fname = ary2[i].fieldname;
			/*
			if(ary2[i].vtype && ary2[i].vtype!=""){
				//校验规则
				markInvalid = Gnt.markInvalidVTYPE(data, markInvalid, hcyForm, ary2[i]);
			}
			*/
			/**
				2018.10.23
				修改	刁杰
				选择记录验证,其余不验证
			 */
			//if(data._sel=='1'){
				if(data.yhzgx != "01" || data.yhzgx != "02" || data.yhzgx != "03"){
					
					if(fname=="yhzgx"){
						//户主性别为男/女时,将同性别的户成员关系改为配偶, 在保存需要做校验,弹框提示’家庭关系与性别不符,重新修正吗?’
						if(hzXb){
							if(data._sel!='1'&&data.yhzgx && (data.yhzgx == "10"||data.yhzgx == "11"||data.yhzgx == "12")){
								if(data.xb == hzXb){
//								markInvalid = Gnt.markInvalid(markInvalid, fname,  "家庭关系与性别不符,重新修正吗?", this);
									markInvalid = Gnt.markInvalid(markInvalid, fname,  "配偶性别不符,请修正!", hcyForm);
								}
							}
						}
						if(pocount>1){
							markInvalid = Gnt.markInvalid(markInvalid, fname,  "配偶人数不能有多个,请修正!", hcyForm);
						}
					}
					
					//户主婚姻状态为已婚,将未婚状态的户成员关系改为配偶,在保存需要做校验,弹框提示’婚姻状况不符,重新修正吗?
					if(fname=="hyzk"){
						if(hzHyzt){
							if(data.yhzgx && (data.yhzgx == "10"||(data.yhzgx == "11"&&hzXb=="2")||(data.yhzgx == "12"&&hzXb=="1"))){
								if(hzHyzt == "20" && "10" == data.hyzk){
//								markInvalid = Gnt.markInvalid(markInvalid, fname,  "婚姻状况不符,重新修正吗?", this);
									markInvalid = Gnt.markInvalid(markInvalid, fname,  "配偶婚姻状况不符,请修正!", hcyForm);
								}
							}
						}
					}
					
				}
				
				/**
					2018.10.23
					新增	刁杰
					选择父亲/母亲时,判断选择的人员与性别是否相符
				 */
				var fmq = null;
				if(data.fqxm && fname == "fqxm"){
					fmq = Gnt.getData(hcyForm,data.fqxm,data.fqgmsfhm);
					if(!fmq && hzForm.bindStore.pzlb != hcyForm.bindStore.pzlb){
						fmq = Gnt.getData(hzForm,data.fqxm,data.fqgmsfhm);
					}
					if(fmq && fmq.xb == "2"){
						markInvalid = Gnt.markInvalid(markInvalid, fname,  "父亲身份证号码性别标志位错误:<br />" + data.fqgmsfhm, hcyForm);
					}
				}
				if(data.fqgmsfhm && fname == "fqgmsfhm"){
					/**
						如果是手动填写
						需要根据身份证号码获取性别
					 */
					var sfzXb = Gnt.date.getSexCode(data.fqgmsfhm);
					if(sfzXb && sfzXb == "2"){
						markInvalid = Gnt.markInvalid(markInvalid, fname,  "父亲身份证号码性别标志位错误:<br />" + data.fqgmsfhm, hcyForm);
					}
				}
				if(data.mqxm && fname == "mqxm"){
					fmq = Gnt.getData(hcyForm,data.mqxm,data.mqgmsfhm);
					if(!fmq && hzForm.bindStore.pzlb != hcyForm.bindStore.pzlb){
						fmq = Gnt.getData(hzForm,data.mqxm,data.mqgmsfhm);
					}
					if(data.mqgmsfhm){
						var sfzXb = Gnt.date.getSexCode(data.mqgmsfhm);
						if((fmq && fmq.xb == "1") || (sfzXb && sfzXb == "1")){
							markInvalid = Gnt.markInvalid(markInvalid, fname,  "母亲身份证号码性别标志位错误:<br />" + data.mqgmsfhm, hcyForm);
						}
					}
				}
				if(data.mqgmsfhm && fname == "mqgmsfhm"){
					var sfzXb = Gnt.date.getSexCode(data.mqgmsfhm);
					if(sfzXb && sfzXb == "1"){
						hcyForm.bindViewGrid.make_Highlight(index);
						hcyForm.bindViewGrid.fireEvent("rowclick",hcyForm.bindViewGrid,index);
						hcyForm.bindViewGrid.getSelectionModel().selectRange(index,index);
						markInvalid = Gnt.markInvalid(markInvalid, fname,  "母亲身份证号码性别标志位错误:<br />" + data.mqgmsfhm, hcyForm);
					}
				}
				
				/**
					2018.11.02
					新增	刁杰
					已婚状态下必须选择配偶,并且验证配偶性别
					
					2018.11.05
					修改	刁杰
					只有迁入业务验证配偶,其余只在改变婚姻状况为已婚时验证
					
					
				 */
				//20181210 kqt 暂时屏蔽婚姻和配偶校验
		/*		if(type == 3){
					if(data.hyzk && data.hyzk == "20"){
						if(fname == "poxm"){
							if(data.poxm){
								
								if(data.pogmsfhm){
									var po = Gnt.getData(hcyForm,data.poxm,data.pogmsfhm);
									if(!po && hzForm.bindStore.pzlb != hcyForm.bindStore.pzlb){
										po = Gnt.getData(hzForm,data.poxm,data.pogmsfhm);
									}
									if(po && po.xb == data.xb){
										markInvalid = Gnt.markInvalid(markInvalid, fname,  "配偶身份证号码性别标志位错误:<br />" + data.pogmsfhm, hcyForm);
									}
								}
								
							}else{
								hcyForm.bindViewGrid.make_Highlight(index);
								hcyForm.bindViewGrid.fireEvent("rowclick",hcyForm.bindViewGrid,index);
								hcyForm.bindViewGrid.getSelectionModel().selectRange(index,index);
								markInvalid = Gnt.markInvalid(markInvalid, fname,  "请选择配偶!", hcyForm);
							}
						}
						
						if(fname == "pogmsfhm"){
							if(data.pogmsfhm){
								var sfzXb = Gnt.date.getSexCode(data.pogmsfhm);
								if(sfzXb && sfzXb == data.xb){
									markInvalid = Gnt.markInvalid(markInvalid, fname,  "配偶身份证号码性别标志位错误:<br />" + data.pogmsfhm, hcyForm);
								}
							}else{
//								markInvalid = Gnt.markInvalid(markInvalid, fname,  "请输入配偶身份证号码!", hcyForm);
							}
						}
						
					}
				}else{
					*/
				//20181210 kqt 暂时屏蔽婚姻和配偶校验
				/**
						判断修改了婚姻状况才验证
					 *//*
					if(data.hyzk && data.hyzk == "20" && r.isModified("hyzk")){
						
						if(fname == "poxm"){
							if(data.poxm){
								
								if(data.pogmsfhm){
									var po = Gnt.getData(hcyForm,data.poxm,data.pogmsfhm);
									if(!po && hzForm.bindStore.pzlb != hcyForm.bindStore.pzlb){
										po = Gnt.getData(hzForm,data.poxm,data.pogmsfhm);
									}
									if(po && po.xb == data.xb){
										markInvalid = Gnt.markInvalid(markInvalid, fname,  "配偶身份证号码性别标志位错误:<br />" + data.pogmsfhm, hcyForm);
									}
								}
								
							}else{
								*//**
									高光无效
								 *//*
								hcyForm.bindViewGrid.make_Highlight(index);
								hcyForm.bindViewGrid.fireEvent("rowclick",hcyForm.bindViewGrid,index);
								hcyForm.bindViewGrid.getSelectionModel().selectRange(index,index);
								
								markInvalid = Gnt.markInvalid(markInvalid, fname,  "请选择配偶!", hcyForm);
							}
						}
						
						if(fname == "pogmsfhm"){
							if(data.pogmsfhm){
								var sfzXb = Gnt.date.getSexCode(data.pogmsfhm);
								if(sfzXb && sfzXb == data.xb){
									markInvalid = Gnt.markInvalid(markInvalid, fname,  "配偶身份证号码性别标志位错误:<br />" + data.pogmsfhm, hcyForm);
								}
							}else{
//								markInvalid = Gnt.markInvalid(markInvalid, fname,  "请输入配偶身份证号码!", hcyForm);
							}
						}
						
					}
				}*/
				
			//}
			
			
			if(markInvalid){
				var me = hcyForm;
				
				for(pname in markInvalid){
					Ext.MessageBox.show({
						title:"提示",
						msg:markInvalid[pname],
						buttons:Ext.Msg.OK,
						icon:Ext.Msg.ERROR,
						fn:function(){
							me.getForm().findField(pname).focus();
						}
					});
					break;
				}
				
				return false;
				/**
					2018.10.23
					修改	刁杰
					线上要求强制修改
				if(window.confirm(markInvalid[fname])){
					me.getForm().markInvalid(markInvalid);
					me.getForm().findField(fname).focus();
					return false;
				}
				*/
			}
			
		}
	}
	
	return true;
	
}

/**
	通过父亲/母亲姓名及身份证号查询人员信息
 */
Gnt.getData = function(form,xm,sfz) {
	for(var index = 0; index < form.bindStore.getCount(); index++){
		var r = form.bindStore.getAt(index);
		var data = r.data;
		
		if(data.xm == xm && data.gmsfhm == sfz){
			return data;
		}
	}
}

/**
	婚姻状况选择了已婚(20)时判断是否选择了配偶
 */
Gnt.validpo = function(form,field) {
	//20181210 kqt 暂时屏蔽婚姻和配偶校验
	/*var hyzk = form.getForm().findField("hyzk").getValue();
	if(hyzk && hyzk == "20"){
		var poxm = form.getForm().findField("poxm").getValue();
		var pogmsfhm = form.getForm().findField("pogmsfhm").getValue();
		if(poxm){
			
			if(pogmsfhm){
				var po = Gnt.getData(form,poxm,pogmsfhm);
				if(po && po.xb == form.getForm().findField("xb").getValue()){
					showErr("配偶身份证号码性别标志位错误:<br />" + pogmsfhm,function(){
						form.getForm().findField("poxm").focus();
					});
				}
			}
			
			if(pogmsfhm){
				var sfzXb = Gnt.date.getSexCode(pogmsfhm);
				if(sfzXb && sfzXb == form.getForm().findField("xb").getValue()){
					showErr("配偶身份证号码性别标志位错误:<br />" + pogmsfhm,function(){
						form.getForm().findField("pogmsfhm").focus();
					});
				}
			}else{
				
				showErr("请输入配偶身份证号码!",function(){
					form.getForm().findField("pogmsfhm").focus();
				});
				
			}
			
		}else{
			showErr("请选择配偶!",function(){
				form.getForm().findField("poxm").focus();
			});
		}
	}*/
}

/**
	验证配偶的性别是否与本人一致
 */
Gnt.validpoxb = function(form,selectRecord,record,type) {
	//20181210 kqt 暂时屏蔽婚姻和配偶校验
	/*if(type){
		var pogmsfhm = record.data.data;
		var poxm = form.getForm().findField("poxm").getValue();
		var po = Gnt.getData(form,poxm,pogmsfhm);
		var sfzXb = null;
		if(pogmsfhm){
			sfzXb = Gnt.date.getSexCode(pogmsfhm);
		}
		if(form.getForm().findField("xb").getValue()){
			if((po && po.xb == form.getForm().findField("xb").getValue()) || (sfzXb && sfzXb == form.getForm().findField("xb").getValue())){
				
				showErr("配偶身份证号码性别标志位错误:<br />" + pogmsfhm,function(){
					//清空飘柔姓名,身份证号码字段
					form.fieldSetValue(form.getForm().findField("poxm"),null);
					form.fieldSetValue(form.getForm().findField("pogmsfhm"),null);
					form.getForm().findField("poxm").focus();
				});
				
			}else{
				form.getForm().setValues({pogmsfhm:record.data.data});
				selectRecord.set("pogmsfhm",record.data.data);
			}
		}else{
			showErr("请选择性别!",function(){
				form.fieldSetValue(form.getForm().findField("poxm"),null);
				form.getForm().findField("xb").focus();
			});
		}
	}else{
		form.getForm().setValues({pogmsfhm:record.data.data});
		selectRecord.set("pogmsfhm",record.data.data);
	}*/
}

/**
	验证配偶身份证号码与性别是否一致
 */
Gnt.validposfzh = function(form,field,type) {
	//20181210 kqt 暂时屏蔽婚姻和配偶校验
	/*if(type){
		var hyzk = form.getForm().findField("hyzk").getValue();
		if(hyzk && hyzk == "20"){
			var pogmsfhm = form.getForm().findField("pogmsfhm").getValue();
			if(pogmsfhm){
				var sfzXb = Gnt.date.getSexCode(pogmsfhm);
				if(sfzXb && sfzXb == form.getForm().findField("xb").getValue()){
					showErr("配偶身份证号码性别标志位错误:<br />" + pogmsfhm,function(){
						form.fieldSetValue(form.getForm().findField("pogmsfhm"),null);
						form.getForm().findField("pogmsfhm").focus();
					});
				}
			}else{
				
				showErr("请输入配偶身份证号码!",function(){
					form.getForm().findField("pogmsfhm").focus();
				});
				
			}
		}
	}else{
		
	}*/
}


Gnt.filterField = function(field) {
	
	/**
		2018.11.15
		新增	刁杰
		与户主关系	屏蔽 01 本人选项
	 */
	if(field.name == 'yhzgx'){
		field.filter =function(data){
			if(data[0] != '01'){
				return true;
			}
		};
		field.reloadDict();
	}
	
	/**
		2018.12.18
		新增	刁杰
		测试:迁出业务,迁出类别字段,将'0471 退伍转业   0473留学人员回国',这两个值屏蔽掉
		modify 0473留学人员回国' 再放开
	 */
	if(field.name == 'qclb'){
		field.filter =function(data){
			if(data[0] != '0471'/* && data[0] != '0473'*/){
				return true;
			}
		};
		field.reloadDict();
	}
	
}
