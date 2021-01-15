/**
 * 通用form
 */
Gnt.ux.SjpzForm = Ext.extend(Ext.form.FormPanel, {
	margins:'5px 5px 5px 5px',
	region : 'center',
	autoScroll : true,
	title : '查询条件',
	buttonAlign : 'bouth',
	labelAlign : 'right',
	frame: false,
	border:  false,
	//cols:2,
	defaults : {
		frame:false,
		border:false
	},
    loadData:function(data){
    	this.store.baseParams = data;
		this.store.load({params:{start:0, limit:this.pagesize}});
    },
    getHb:function(){
    	var f = this.getForm().findField("hb");
    	if(f){
    		return f.getValue();
    	}else{
    		return "";
    	}
    },
    isHz:function(){
    	//判断当前编辑人，是不是户主
    	var f = this.getForm().findField("yhzgx");
    	if(f){
    		var v = f.getValue();
    		if(v=="01" || v=="02" || v=="03")
    			return true;
    	}
    	
    	return false;
    },
	getDictCust:function(cmbo,visiontype){
		if(visiontype && visiontype=="JTCY"){
			return this.getSelectRy();
		}
		return;
	},
	changeDictCust:function(){
		//可编辑下拉框改变值的
	},
	//人员选择方法，默认实现，可覆盖
	getSelectRy:function(){
		var rylist = new Array();
		var store = null;
		
		if(this.bindViewGridHCY && this.bindViewGridHCY.store.getCount()>0){
			store = this.bindViewGridHCY.store;
		}else{
			if(this.bindSelectRyStore){
				store = this.bindSelectRyStore;
			}else if(this.bindViewGrid){
				store = this.bindViewGrid.store;
			}
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
	listeners:{
		render:function(f){
			/*
			(function(){
				var all = this.el.query('input[type!=hidden]');
				Ext.each(all, function(o, i, all) {
					Ext.fly(o).addKeyMap({
						key : 13,
						fn : function(){
							event.keyCode = 9;
							return true;
						}
					})
				});
        	}).defer(500, this);
        	*/
		},
		resize:function(f){
			if(f.formType!="query"&&f.formType=="edit"&&typeof(f.layout.layout)=='function'){
				f.doLayout();
			}
		}
	},
	buttonAlign : 'right',
	makeCheckData:function(r, pkvalue, grid){
		if(grid){
			var re = grid.store.getById(pkvalue);
			if(re){
				//找到对应grid记录，选择，并且触发rowclick动作
				var selindex = grid.store.indexOf(re);
				grid.getSelectionModel().selectRange(selindex,selindex);
				grid.rowclick_bak(grid, selindex);
				grid.make_Highlight(selindex);
				return this.getForm().isValid();
			}else{
				alert("出错了，记录没找到！");
			}
		}else{
			//获取绑定grid对应store的记录
			if(this.bindViewGrid){
				//如果绑定了this.bindViewGrid，那么除非点击方法
				var bindGridRecord = this.bindViewGrid.store.getById(pkvalue);
				if(bindGridRecord){
					//找到对应grid记录，选择，并且触发rowclick动作
					var selindex = this.bindViewGrid.store.indexOf(bindGridRecord);
					this.bindViewGrid.getSelectionModel().selectRange(selindex,selindex);
					this.bindViewGrid.make_Highlight(selindex);
				}
			}
			
			this.getForm().reset();
			this.getForm().loadRecord(r);
	
			return this.getForm().isValid();
		}
	},
	//校验表单绑定的数据集合
	isValid:function(){
		if(this.bindViewGrid){
			var count = this.bindViewGrid.store.getCount();
			for(var i=0;i<count;i++){
				var r = this.bindViewGrid.store.getAt(i);
				
				this.bindViewGrid.getSelectionModel().selectRange(i,i);
				this.getForm().reset();
				this.getForm().loadRecord(r);
				if(!this.getForm().isValid())
					return false;
			}
			
			return true;
		}else{
			return this.getForm().isValid();
		}
	},
	//判断关联store输入是否满足
	//当emptyFlag==true时，如果store只有一条记录，那么判断是否为空，如果为空，那么忽略此记录
	//如果grid不为空，那么通过grid的点击事件来定位校验数据，达到定位多个表单的目的
	checkInput:function(emptyFlag, check_type, check16, grid){
		if(this.bindViewGrid){
			this.bindViewGrid.make_Highlight();
		}
		
		if(grid){
			grid.make_Highlight();
		}
		
		var title = this.title;
		
		if(!emptyFlag || emptyFlag==undefined)
			emptyFlag = false;
		
		/*
		校验我改进下，通过参数控制：
		所有：
		1、校验所有数据，包括非空和所有业务。
		2、校验所有数据，只校验非空。
		3、校验所有数据，校验所有非空，以及修改过的数据的业务校验。
		
		修改过的：
		4、只校验修改过的数据，包括非空和业务字段。
		5、只校验修过过的数据，只校验非空。
		
		选择标志：
		6、只校验选择记录的非空和业务。
		7、只校验选择记录的非空。
		 */
		if(!check_type || check_type==""){
			check_type = "1";
		}

		/**
		 * 判断比较复杂，this.bindStore不一定具有GRID，所以这里还必须通过this.bindViewGrid来绑定
		 * 一个GRID, this.bindStore和this.bindViewGrid，必须具有相同的pk值
		 */
		if(!this.pzlb){
			alert("没有通过pzlb参数指定配置类别！")
			return false;
		}
		
		//获取字典配置
		var ary = Gnt.ux.Dict.getSjpzData(this.pzlb);
		if(!ary || ary.length==0){
			alert(this.pzlb + "配置没有找到！");
			return;
		}
		
		var oldhb = "";
		if(this.bindStore){
			var count = this.bindStore.getCount();
			if(count==1){
				if(emptyFlag && emptyFlag==true){
					var modiList = this.bindStore.getModifiedRecords();
					if(modiList.length==0){
						return true;
					}
				}
			}
			
			//收集修改的记录ID
			var modiMap = {};
			var modiList = this.bindStore.getModifiedRecords();
			
			//获取修改的记录，标记哪些记录被修改过
			if(modiList && modiList.length>0){
				for(var index=0;index<modiList.length;index++){
					var r = modiList[index];
					var pkvalue = r.data[this.bindStore.id];
					modiMap[pkvalue] = '1';
				}
			}
			
			//遍历所有绑定的记录
			for(var index=0;index<count;index++){
				//获取记录
				var r = this.bindStore.getAt(index);
				//获取记录数据
				var data = r.data;
				var pkvalue = data[this.bindStore.id];
				
				//遍历记录字段
				for(var i=0;i<ary.length;i++){
					//通用业务判断
					var fname = ary[i].fieldname;
					var markInvalid = null;
					var markInvalidText = null;
					
					if(ary[i].vtype && ary[i].vtype!=""){
						//校验规则
						markInvalid = Gnt.markInvalidVTYPE(data, markInvalid, this, ary[i]);
					}
					
					//校验身份证号码
					if(fname=="gmsfhm"){
						//身份证，姓名，出生日期校验
						var xb = data.xb;
						var csrq = data.csrq;
						var sfz = data.gmsfhm;
						if(sfz && sfz!=""&&data._sel=="1"){
							var str = Gnt.date.validateCard(sfz, xb, csrq);
							if(str==true)
								continue;
							
							//markInvalidText = str;
							//markInvalid = {gmsfhm: str};
							markInvalid = Gnt.markInvalid(markInvalid, "gmsfhm", str, this);
							/*
								2018.10.24
								删除	刁杰
								应该在最后统一提示
								
								2019.01.07
								修改	刁杰
								户政业务 - 变更更正 变更了出生日期后校验了身份证但是没有提示
								这里需要特殊处理
							*/
							this.makeCheckData(r, pkvalue, grid);
							this.getForm().markInvalid(markInvalid);
							
							var me = this;
							for(pname in markInvalid){
								Ext.MessageBox.show({
									title:"提示",
									msg:markInvalid[pname],
									buttons:Ext.Msg.OK,
									icon:Ext.Msg.ERROR,
									fn:function(){
										me.getForm().findField(pname).focus();
										Gnt.photo.setPhoto(null, true);
						    			Gnt.photo.setPhoto(r, true);
									}
								});
								break;
							}
							return false;
							
						}
					}
					
					/**
						2018.10.25
						新增	刁杰
						户别变更	取消所有年龄验证
					 */
					//出生日期，获取年龄
					if((fname=="gmsfhm" || fname=="csrq") && check16){
						var csrq = data.csrq;
						var sfz = data.gmsfhm;
						/**
							2018.11.21
							注释	刁杰
							线上反应 迁入业务 点击保存报错
							可能是身份证号码/出生日期获取不对或者没有获取到导致的
							是否添加判断消除隐患?
						 */
						var nl = Gnt.date.getNl(sfz, csrq);
						if(nl>=0){
							
							if(nl >= 1){
//								if(this.pzlb=="10001" && data.csdjlb!='0109'){
								if(this.pzlb=="10001" && data.csdjlb=='0101'){
									markInvalid = Gnt.markInvalid(markInvalid, "csdjlb",  "超过1周岁，出生登记类别不能为出生登记！", this);
								}
							}
							
							if(nl >= 14){
								if(this.pzlb == "10001"){
									showInfo("超过14周岁的,不允许办理出生业务！");
									return false;
								}
							}
							
							//小于16岁，必须填写监护人
							if(this.pzlb != "10001"){//出生不需要判断，其他地方有限制填监护人姓名   add by zjm 20200410
								markInvalid = Gnt.markInvalidBXTXY(data, markInvalid, nl, 15, this, "jhryxm", "监护人一姓名");
								markInvalid = Gnt.markInvalidBXTXY(data, markInvalid, nl, 15, this, "jhrygmsfhm", "监护人一身份证号码");
								markInvalid = Gnt.markInvalidBXTXY(data, markInvalid, nl, 15, this, "jhryjhgx", "监护人一监护关系");
							}
							//小于6岁，不能填写的数据
							markInvalid = Gnt.markInvalidBNT(data, markInvalid, nl, 5, this, "whcd", "文化程度");
							markInvalid = Gnt.markInvalidBNT(data, markInvalid, nl, 5, this, "sg", "身高");
							markInvalid = Gnt.markInvalidBNT(data, markInvalid, nl, 5, this, "zy", "职业");
							markInvalid = Gnt.markInvalidBNT(data, markInvalid, nl, 5, this, "zylb", "职业类别");
							markInvalid = Gnt.markInvalidBNT(data, markInvalid, nl, 5, this, "fwcs", "服务处所");
							
							/**
								2018.09.30
								修改	刁杰
								户籍删除业务、住址变动业务、迁出业务、户别变更 取消16周岁以上信息必填检验
								
								2018.10.25
								新增	刁杰
								变更更正/辅项变更/全项变更	取消16周岁以上信息必填检验
							 */
							if(check16){
								//大于16岁，必须填写的数据
								if(this.pzlb != "10023"){//迁入业务不验证服务处所  职业类别 职业add by zjm 20191101
//									markInvalid = Gnt.markInvalidBXT(data, markInvalid, nl, 18, this, "fwcs", "服务处所");
//									markInvalid = Gnt.markInvalidBXT(data, markInvalid, nl, 18, this, "zylb", "职业类别");
									markInvalid = Gnt.markInvalidBXT(data, markInvalid, nl, 18, this, "zy", "职业");
								}
//								markInvalid = Gnt.markInvalidBXT(data, markInvalid, nl, 18, this, "sg", "身高");
								
								/**
									迁入登记年满16周岁文化程度必填校验去除
								 */
								if(this.pzlb != "10030"){
									markInvalid = Gnt.markInvalidBXT(data, markInvalid, nl, 18, this, "whcd", "文化程度");
								}
							}
							
							
							//小于18岁，不能填写的数据
//							markInvalid = Gnt.markInvalidBNT(data, markInvalid, nl, 17, this, "hyzk", "婚姻状况");
//							markInvalid = Gnt.markInvalidBNT(data, markInvalid, nl, 17, this, "byzk", "兵役状况");
							
							//迁入业务和出生业务，判断照片:10001
							if(nl>=16 && (this.pzlb=="10023" || data.pzlb=='10001')){
								var f1 = this.getForm().findField("zpid");
								var f2 = this.getForm().findField("zp");
								
								//照片问题，特殊
//								if(f1 && !f1.disabled && f2 && !f2.disabled && ImageCtrl){
								if(f1 && !f1.disabled && f2 && !f2.disabled /*&& Gnt.photo.can*/){
									if(Gnt.util.isEmpty(data.zp) && Gnt.util.isEmpty(data.zpid)){
										//如果记录没有被修改，那么不管
										if(!modiMap[pkvalue]){
											continue;
										}
										
										this.makeCheckData(r, pkvalue, grid);
						    			showInfo("年满16周岁，必须采集照片！");
						    			return false;
									}
								}
							}
							
							
						}
					}
					
					//校验电话号码
					/*markInvalid = Gnt.markDHHM(data, markInvalid, this, "sjrlxdh", "收件人联系电话");
					markInvalid = Gnt.markDHHM(data, markInvalid, this, "lzrdh", "领证人电话");
					markInvalid = Gnt.markDHHM(data, markInvalid, this, "lxdh", "联系电话");
					markInvalid = Gnt.markDHHM(data, markInvalid, this, "dhhm", "电话号码");
					markInvalid = Gnt.markDHHM(data, markInvalid, this, "dhhm2", "其他电话号码");
					markInvalid = Gnt.markDHHM(data, markInvalid, this, "jhrylxdh", "监护人一联系电话");
					markInvalid = Gnt.markDHHM(data, markInvalid, this, "jhrelxdh", "监护人二联系电话");
					*/
					//户成员性别为男,与户主关系选择妻/女/养母继母等,在保存时,需要做校验
					var men = ['11','20','21','22','23','24','25','26','27','28','29','41','43','46','47','51','53','55','57','61'
						,'63','66','71','73','76','78','81','83','85','87','89','93','95'];
					var women = ['12','30','31','32','33','34','35','36','37','38','39','42','44','45','48','52','54','56','58','62'
						,'64','67','72','74','75','77','82','84','86','88','90','94','96'];
					if(data.xb == "1"){
						for(var j=0;j<women.length;j++){
							var gxW = women[j];
							if(data.yhzgx == gxW){
//							markInvalid = Gnt.markInvalid(markInvalid, fname,  "家庭关系与性别不符,重新修正吗?", this);
								markInvalid = Gnt.markInvalid(markInvalid, fname,  "家庭关系与性别不符,请修正!", this);
							}
						}
					}else if(data.xb == "2"){
						for(var j=0;j<men.length;j++){
							var gxM = men[j];
							if(data.yhzgx == gxM){
//							markInvalid = Gnt.markInvalid(markInvalid, fname,  "家庭关系与性别不符,重新修正吗?", this);
								markInvalid = Gnt.markInvalid(markInvalid, fname,  "家庭关系与性别不符,请修正!", this);
							}
						}
					}
					//集体户种类
					var hb_name = (this.pzlb=="10030" || this.pzlb=="10031")?"qrhhb": "hb";
					var f1 = this.getForm().findField(hb_name);
					var f2 = this.getForm().findField("jthzl");

					if(this.pzlb=="10001"){
						//出生登记和户籍补录 户籍地、出生地、监护人信息 必填校验
						var s1 = data.jhryxm;
						var s2 = data.jhrygmsfhm;
						var s3 = data.jhryjhgx;
						
						var s4 = data.jhrexm;
						var s5 = data.jhregmsfhm;
						var s6 = data.jhrejhgx;
						
						var jhrCount = 0;
//						if(s1 && s2 && s3 && s1!="" && s2!="" && s3!="") jhrCount++;
//						if(s4 && s5 && s6 && s4!="" && s5!="" && s6!="") jhrCount++;
						//modify by zjm 20200408 改成两个监护人只要填一个姓名就可以
						if(s1 && s1!="") jhrCount++;
						if(s4 && s4!="") jhrCount++;
						if(jhrCount==0){
							markInvalid = Gnt.markInvalid(markInvalid, "jhryxm",  "必须填写监护人1或者监护人2姓名！", this);
						}
					}
					
					if(f1 && f2&&this.pzlb!="10019"){
						if(data[hb_name]=="7"){
							if(!data.jthzl || data.jthzl==""){
								markInvalid = Gnt.markInvalid(markInvalid, "jthzl",  "居民集体户，必须选择集体户种类！", this);
							}
						}
					}
					
					//同一户，户别必须一直校验
					if(f1){
						if(oldhb==""){
							oldhb = data[hb_name];
						}else{
							if(oldhb!=data[hb_name]){
								markInvalid = Gnt.markInvalid(markInvalid, hb_name,  "户成员，户别必须一致！", this);
							}
						}
						
						if(this.bindViewGridHCY && this.bindViewGridHCY.store.getCount()>0){
							var hcyhb = this.bindViewGridHCY.store.getAt(0).data["hb"];
							if(hcyhb && hcyhb!=""){
								if(hcyhb!=oldhb)
									markInvalid = Gnt.markInvalid(markInvalid, hb_name,  "户别必须和入户户成员户别[" + hcyhb + "]一致！", this);
							}
						}
					}
					
					/**
						变更更正/迁入业务/户籍补录/出生业务（add by zjm 20190718增加）
						身份证号码清空后提示是否自动分配
					 */
					if(this.pzlb=="10020" || this.pzlb=="10023" || this.pzlb=="10007"|| this.pzlb=="10001"){
						if(!data.gmsfhm && fname=="gmsfhm"){
							if(window.confirm("需要为\""+data.xm+"\"分配身份证号码吗?")){
								
							}else{
								return false ;
							}
						}
					}
					
					/**
						2018.08.12
						新增	刁杰
						出生业务必须填写父亲或母亲姓名/身份证号
					 */
					if(this.pzlb=="10001"){
						if(data.csdjlb != "0108"){
							/**
								出生登记类别为收养时:不需要父亲/母亲信息
							 */
							var s1 = data.fqxm;
							var s2 = data.fqgmsfhm;
							var s3 = data.mqxm;
							var s4 = data.mqgmsfhm;
							
							var fmqCount = 0;
							var sfzCount = 0;
							if((s1 || s3) && (s1!="" || s3!="")) jhrCount++;
							if(jhrCount == 0){
								markInvalid = Gnt.markInvalid(markInvalid, "fqxm",  "请选择父亲或母亲姓名！", this);
							}else{
								if((s1 && s1!="" && s2 && s2!="") || (s3 && s3!="" && s4 && s4!="")){
									
								}else{
									
									/*
									var fqFlag = false;
									var mqFlag = false;
									if(s1 && s1!=""){
										if(s2 && s2!=""){
											fqFlag = true;
											fmFlag = 0;
										}
									}
									if(s3 && s3!=""){
										if(s4 && s4!=""){
											mqFlag = true;
											fmFlag = 0;
										}
									}
									
									if(fqFlag == true || mqFlag == true){
										
									}else{
										if(!fqFlag){
											markInvalid = Gnt.markInvalid(markInvalid, "fqgmsfhm",  "请输入父亲身份号码！", this);
										}
										if(!mqFlag){
											markInvalid = Gnt.markInvalid(markInvalid, "mqgmsfhm",  "请输入母亲身份号码！", this);
										}
									}
									*/
									
									var fmFlag = 0;
									if(s1 && s1!=""){
										if(s2 && s2!=""){
											fmFlag = 0;
										}else{
	//									markInvalid = Gnt.markInvalid(markInvalid, "fqgmsfhm",  "请输入父亲身份号码！", this);
											fmFlag = 1;
										}
									}else if(s3 && s3!=""){
										if(s4 && s4!=""){
											fmFlag = 0;
										}else{
	//									markInvalid = Gnt.markInvalid(markInvalid, "mqgmsfhm",  "请输入母亲身份号码！", this);
											fmFlag = 2;
										}
									}
									if(fmFlag == 1){
										markInvalid = Gnt.markInvalid(markInvalid, "fqgmsfhm",  "请输入父亲身份号码！", this);
									}else if(fmFlag == 2){
										markInvalid = Gnt.markInvalid(markInvalid, "mqgmsfhm",  "请输入母亲身份号码！", this);
									}
								}
								
							}
						}
						
						/**
							出生登记业务
							出生证签发日期不得大于当前日期
							出生证签发日期不能小于出生日期
						 */
						if(fname=="cszqfrq" && data.cszqfrq){
							if(Gnt.date.compareDate(null,data.cszqfrq,true)){
								markInvalid = Gnt.markInvalid(markInvalid, fname,  "出生证签发日期不得大于当前日期！", this);
							}
							
							if(data.csrq){
								if(!Gnt.date.compareDate(data.csrq,data.cszqfrq,false)){
									markInvalid = Gnt.markInvalid(markInvalid, fname,  "出生证签发日期不能小于出生日期！", this);
								}
							}
						}
						
						/**
							出生地省县选择国外时:自动设置CSDGJDQ 出生地国家（地区）
						 */
						if(fname=="csdssxq"){
							if(data.csdssxq && data.csdssxq.startWith('010')){
								data.csdgjdq = data.csdssxq.substring(3,6);
							}
						}
						/**
							籍贯省县选择国外时:自动设置JGDGJDQ 出生地国家（地区）
						 */
						if(fname=="jgssxq"){
							if(data.jgssxq && data.jgssxq.startWith('010')){
								data.jggjdq = data.jgssxq.substring(3,6);
							}
						}
						/**
							2018.10.16
							新增	刁杰
							出生证明编号长度不得大于九位
						 */
						if(fname=="cszmbh"){
							if(Gnt.ux.getStrLength(data.cszmbh) > 20){
								markInvalid = Gnt.markInvalid(markInvalid, fname,  "【出生证明编号】长度超出限制！", this);
							}
						}
						
						/**
							2018.11.12
							新增	刁杰
							出生日期不得大于当前日期
						 */
						if(fname=="csrq" && data.csrq && Gnt.date.compareDate(null,data.csrq,true)){
							markInvalid = Gnt.markInvalid(markInvalid, fname,  "出生日期不得大于当前日期！", this);
						}
						
					}
					
					if(this.pzlb == "10005"){
						/**
							2018.11.15
							新增	刁杰
							BugFree 272
							户别选择居民集体户的话,应该要校验'集体户种类'是否填写
						 */
						if(fname == "bghhb" && data.bghhb == "7"){
							if(!data.jthzl){
								markInvalid = Gnt.markInvalid(markInvalid, "jthzl",  "请选择集体户种类！", this);
							}
						}
						
					}
					/*
					 * add by zjm 20191030
					 * 户籍补录验证
					 */
					if(this.pzlb == "10007"){
						var csrq = data.csrq;
						var sfz = data.gmsfhm;
						var nl = Gnt.date.getNl(sfz, csrq);
						if(nl>=0){
							//小于16岁，必须填写监护人
							markInvalid = Gnt.markInvalidBXTXY(data, markInvalid, nl, 15, this, "jhryxm", "监护人一姓名");
							markInvalid = Gnt.markInvalidBXTXY(data, markInvalid, nl, 15, this, "jhrygmsfhm", "监护人一身份证号码");
							markInvalid = Gnt.markInvalidBXTXY(data, markInvalid, nl, 15, this, "jhryjhgx", "监护人一监护关系");
						}
					}
					if(this.pzlb == "10014"){
						/**
							死亡注销业务
							死亡日期不得大于当前日期
							死亡日期不能大小出生日期
						 */
						if(fname=="swrq" && data.swrq && Gnt.date.compareDate(null,data.swrq,true)){
							markInvalid = Gnt.markInvalid(markInvalid, fname,  "死亡日期不得大于当前日期！", this);
						}
						/**
							业务表单无法获取人员出生日期
							
						if(Gnt.date.compareDate(null,data.swrq,false)){
							showErr("死亡日期不能小于出生日期！");
							return false;
						}
						 */
						
						if(fname=="swzmbh"){
							if(Gnt.ux.getStrLength(data.swzmbh) > 20){
								markInvalid = Gnt.markInvalid(markInvalid, fname,  "【死亡证明编号】长度超出限制！", this);
							}
						}
						
					}
					if(this.pzlb == "10015"){
						/**
							2019.10.17
							add by zjm
							变动后户别居民集体户的话,应该要校验'集体户种类'是否填写
						 */
						if(fname == "zzbdhhb" && data.zzbdhhb == "7"){
							if(!data.jthzl){
								markInvalid = Gnt.markInvalid(markInvalid, "jthzl",  "请选择集体户种类！", this);
							}
						}
						
					}
					if(this.pzlb=="10023"){
						//迁入业务
						if(data.qcdssxq && data.qcdssxq.length>=6){
							/**
								2018.10.15
								修改	刁杰
								’迁出地省县’ 内容为’台湾’/’香港特别行政区’/’澳门特别行政区’这三项时,不需要判断省级代码
							 */
							if(data.qcdssxq == "710000" || data.qcdssxq == "810000" || data.qcdssxq == "820000"){
								
							}else if(data.qcdssxq.substring(2)=="0000"){
								markInvalid = Gnt.markInvalid(markInvalid, "qcdssxq",  "【迁出地省县】不允许选择省级代码！", this);
							}
						}
						if(fname=="qrlb"&&data.qrlb=='0333'){
							if(data.hyzk=='10'){
								if(window.confirm('变动原因与婚姻状况不符合！,要重新修正吗？')){
									return;
								}
							}
						}
						/*
						 * 20200226
						 * add by zjm
						 * 配偶性别不能和本人一致
						 */
						if(fname=="pozjhm"){
							if(Gnt.ux.getStrLength(data.pogmsfhm) > 17) {
								var sexValue = data.pogmsfhm.substr(16,1);
								if((sexValue%2 ==0)?1:2!=data.xb){
									markInvalid = Gnt.markInvalid(markInvalid, "pogmsfhm",  "【配偶身份号码】身份号码性别标志位不能和本人一致！", this);
								}
								
							}
						}
						if(fname=="qyzbh"){
							if(Gnt.ux.getStrLength(data.qyzbh) > 11){
								markInvalid = Gnt.markInvalid(markInvalid, fname, "【迁移证编号】长度超出限制！", this);
							}
						}
						
						if(fname=="zqzbh"){
							if(Gnt.ux.getStrLength(data.zqzbh) > 11){
								markInvalid = Gnt.markInvalid(markInvalid, fname, "【准迁证编号】长度超出限制！", this);
							}
						}
						
						
					}
					
					if(this.pzlb=="10030" || this.pzlb=="10031"){
						//迁入审批登记
						if(data.zzssxq && data.zzssxq.length>=6){
							if(data.zzssxq.substring(2)=="0000"){
								markInvalid = Gnt.markInvalid(markInvalid, "zzssxq",  "【户口所在区县】不允许选择省级代码！", this);
							}
						}
						//add by zjm 20200310 增加户类型和户别对应验证
						if((data.qrdhlx=="1"&&data.qrhhb=="7")||(data.qrdhlx=="2"&&data.qrhhb=="6")){
							markInvalid = Gnt.markInvalid(markInvalid, "qrhhb",  "【迁入后户别】和户类型不匹配！", this);
						}
					}
					
					if(this.pzlb=="10024"){
						//迁出注销
						if(fname=="zqzbh"){
							
							if(Gnt.ux.getStrLength(data.zqzbh) > 11){
								markInvalid = Gnt.markInvalid(markInvalid, "zqzbh",  "【准迁证编号】长度超出限制！", this);
							}
							
							/**
								以文字开头 八位数字
							 */
							/*var reg=/^[\u4e00-\u9fa5]+\d{8}$/;
							if(!reg.test(data.zqzbh)){
								markInvalid = Gnt.markInvalid(markInvalid, "zqzbh",  "【准迁证编号】格式不正确！", this);
							}
							*/
						}
					}
					
					//业务校验规则
					if(markInvalid){
						if(check_type=="1" || check_type=="3" || check_type=="4" || check_type=="6"|| check_type=="7"){
							var chkflag = false;
							//1、校验所有数据，包括非空和所有业务。
							if(check_type=="1") chkflag = true;
							if(check_type=="3" || check_type=="4"){
								//3、校验所有数据，校验所有非空，以及修改过的数据的业务校验。
								//4、只校验修改过的数据，包括非空和业务字段。
								if(modiMap[pkvalue])
									chkflag = true;
							}
							//6、只校验选择记录的非空和业务。
							if(check_type=="6"&& data._sel=="1")
								chkflag = true;
							if(check_type=="7"){
								chkflag = true;
//								if(fname=="hb"||fname == "zzbdhhb"){
//									if(data[fname]=="7"){
//										chkflag = true;
//									}
//									
//								}
							}
								
							if(chkflag){
								this.makeCheckData(r, pkvalue, grid);
								this.getForm().markInvalid(markInvalid);
								
								var me = this;
								for(pname in markInvalid){
									Ext.MessageBox.show({
											title:"提示",
											msg:markInvalid[pname],
											buttons:Ext.Msg.OK,
											icon:Ext.Msg.ERROR,
											fn:function(){
												me.getForm().findField(pname).focus();
												Gnt.photo.setPhoto(null, true);
								    			Gnt.photo.setPhoto(r, true);
											}
									});
									break;
								}
								
								return false;
							}
						}
					}
					
					//当前字段非空校验
					var isAllowBlank = Gnt.isAllowBlank(ary[i], ary);
					if(isAllowBlank){
						continue;
					}
					
					var chkflag = false;
					//校验所有数据非空
					if(check_type=="1" || check_type=="2" || check_type=="3") 
						chkflag = true;
					
					//校验修改过的非空
					if(check_type=="4" || check_type=="5"){
						if(modiMap[pkvalue])
							chkflag = true;
					}
					
					//只校验选择记录
					if(check_type=="6" || check_type=="7"){
						if(data._sel=="1")
							chkflag = true;
					}
					
					var name = ary[i].fieldname;
					//必须录入，判断是否为空
					if(chkflag && (data[name]==undefined || data[name]=="") ){
						this.makeCheckData(r, pkvalue, grid);
						var bindViewGrid = this.bindViewGrid;
						bindViewGrid.fireEvent("rowclick",bindViewGrid,index);
		    			var msg = this.title + "【" +  ary[i].displayname + "】不能为空！";
		    			var field = this.getForm().findField(name);
						Ext.MessageBox.show({
								title:"提示",
								msg:msg,
								buttons:Ext.Msg.OK,
								icon:Ext.Msg.ERROR,
								fn:function(){
									field.focus(true, 100);
								}
						});
						
						//返回
						return false;
					}
				}
				
				//如果记录正常返回，那么普通校验
				//if(!this.makeCheckData(r, pkvalue)){
				//	showErr(title + "数据校验未通过！");
				//	return false;
				//}
				
			}
			
		}else{
			if(!this.getForm().isValid()){
				showErr(title + "数据校验未通过！");
				return false;
			}
			
			return true;
		}
		
		return true;
	},
	fieldSetValue:function(field, value){
		if(typeof field == "string"){
			field = this.getForm().findField(field);
		}
		
		if(!field){
			showErr("字段不存在！")
			return;
		}
		
		//保存数据到store，仅仅field不能持久化
		var store = this.bindStore;
		field.setValue(value);
		if(store){
			var pkfield = this.getForm().findField(store.pkname?store.pkname:store.id);
			if(pkfield){
				var re = store.getById(pkfield.getValue());
				if(re){
					re.set(field.name, value);
				}else{
					showErr("数据绑定错误，未找到数据集记录！");
				}
			}else{
				showErr("数据绑定错误，未找到主键字段！");
			}
		}
	},
	fieldBlur:function(field){
		
	},
	fieldFocus:function(field){
		if(field.name=="gmsfhm"){
		}
		field.focus();
	},
	//值改变的时候给store赋值
	fieldValueChange:function(field ,  newValue,  oldValue){
		var f = field.findParentByType("sjpz_form");
		var store = f.bindStore;
		var isfocus = false;
		
		if(store){
			//编辑事件，自动填充store
			var pkfield = f.getForm().findField(store.pkname?store.pkname:store.id);
			var pkvalue = pkfield.getValue();
			var re = store.getById(pkvalue);
			if(!re){
				re = new store.reader.recordType({}, pkvalue);
				store.add([re]);
			}
			
			if(Ext.isDate(newValue)){
				newValue = Ext.util.Format.date(newValue,"Ymd");
			}
			if(field.name=="cssj" && newValue!=""){
				//出生时间，特殊处理
				newValue = newValue.replace(/:/g, "");
			}
			//由于具有计算，并且会刷新grid，会导致表单失去焦点
			re.set(field.name, newValue);
			
			//处理下一个焦点问题   20190823 鼠标焦点切换屏蔽	
//			if(f.lastFocusField && field.name!=f.lastFocusField.name){
//				//f.lastFocusField.focus();
//			}else{
//				if(field.next_fieldname){
//					var next = field.next_fieldname;
//					var count = 0;
//					while(true){
//						var nextfield = f.getForm().findField(next);
//						if(!nextfield)
//							break;
//						
//						if(nextfield.isVisible() && !nextfield.disabled && nextfield.xtype!="hidden"){
//							isfocus = true;
//							(function(){
//								nextfield.focus();
//							}).defer(100);
//							
//							break;
//						}
//						
//						if(!nextfield.next_fieldname)
//							break;
//						
//						next = nextfield.next_fieldname;
//					}
//				}
//			}
		}else{
			;//field.setValue(newValue);
		}
		
		if(f.isModify){
			f.isModify(true);
		}
		
		if(f.fieldChange){
			f.fieldChange(field, newValue, oldValue);
		}
		
		//放最后，防止isModify，fieldChange改变焦点
		if(f.lastFocusField && field.name!=f.lastFocusField.name && !isfocus){
			(function(){
				f.lastFocusField.focus();
			}).defer(100);
		}
	},
	initComponent : function(){
		var me = this;
		var cols = me.cols;
		var formType = me.formType;	//表单类型：query 查询表单；edit 编辑表单（默认）
		if(!formType){
			formType = "edit";
			me.formType = formType;
		}

		if(me.pzlb){
			me.fields = Gnt.getFormItems(me, formType);
		}

		var fieldMap = {};
		
		if(me.fields && !me.items){
			var items = [{
					layout : 'column',
					//xtype : 'fieldset',
					title : '',
					margins:'0',
					bodyStyle : 'padding:5px 20px 0px 0px',
					defaults : {
						frame:false,
						border:false,
						labelWidth : me.labelWidth?me.labelWidth:100,
						bodyStyle : 'padding:0px 0px 0px 0px'
					},
					items:[]
			}];

			for(var i=0;i<me.fields.length;i++){
				var item = me.fields[i];
				
				fieldMap[item.name] = item.fieldLabel;
				
				if(item.title!=undefined){
					if(i!=0){
						items.push({
							layout : 'column',
							xtype:'fieldset',
							title : item.groupTitle,
							checkboxToggle:true,
							bodyStyle : 'padding:5px 20px 0px 0px',
							defaults : {
								frame:false,
								border:false,
								labelWidth : me.labelWidth?me.labelWidth:100,
								bodyStyle : 'padding:1px 1px 1px 1px'
							},
							items:[]
						});
					}
					
					item.checkboxToggle = true;
					item.title = '<span style="color:blue">' + item.title + '<span>'
							
					Ext.apply(items[items.length-1], item);

					continue;
				}
				
				//只读
				if(item.readOnly!=undefined && item.readOnly){
//					if(item.readOnly){
//						item.fieldClass="x-form-field x-item-readonly"
//					}
//					item.fieldClass="x-item-readonly";
					if(item.xtype=='dict_combox'){
						item.typeAhead=false;
						item.editable=false;
//						item.readOnly=true;
						item.forceSelection = true;
						item.selectOnFocus = false;
						
//						item.onbeforeactivate = function(){return false;};
//						item.onfocus = function(){item.blur();};
//						item.onmouseover = function(){item.setCapture();};
//						item.onmouseout = function(){item.releaseCapture();};
					}
					if(!item.listeners){
						item.listeners={};
					}
//					item.typeAhead=false;
//					item.editable=false;
//					item.readOnly=true;
					item.disabled = true;
				}

				//隐藏
				if(item.xtype=='hidden'){
					items[items.length-1].items.push(item);
					continue;
				}
				//anchor:'100%',
				if(!item.anchor) item.anchor = "100%";
				if(!item.xtype) item.xtype = 'textfield';
				
				if(item.labelWidth)
					;
				else
					item.labelWidth = me.labelWidth?me.labelWidth:100;
				
				//formType=="view"
					
				if(item.allowBlank!=undefined && !item.allowBlank && item.fieldLabel){
					item.fieldLabel = '<span style="color:red">' + item.fieldLabel + "</span>";
				}

				items[items.length-1].items.push({
					layout : 'form',
					//bodyStyle:'padding:0 0 0 0',
					columnWidth : me.fields[i].columnWidth,
					items : [item]
				});
			}
			
			this.items = items;
		}

		this.fieldMap = fieldMap;
		if(this.bindViewGrid && !this.bindStore){
			this.bindStore = this.bindViewGrid.store;
		}
		
		Gnt.ux.SjpzForm.superclass.initComponent.call(this);
	},
	//获取指定域中文名称
	getFieldLabel:function(field){
		if(this.fieldMap && this.fieldMap[field])
			return this.fieldMap[field];
		return field;
	},
	isModify:function(flag){
	},
	fieldChange:function(){
	},
	getDictTextValue:function(field){
		//获取字典组件文字内容
		var f = f.getForm().findField(field);
		if(f)
			return f.getRawValue();
		else
			return "";
	}
});
Ext.reg('sjpz_form', Gnt.ux.SjpzForm);