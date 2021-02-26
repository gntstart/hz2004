var log_code = ""; // 系统日志类型编码，必须和XT_XTGNB对应，否则不存储
var rkzpMap = {};//储存 中间表rkzp
var cell ="";
if (Ext.isChrome == true) {
	var chromeDatePickerCSS = ".x-date-picker {border-color: #1b376c;background-color:#fff;position: relative;width: 185px;}";
	Ext.util.CSS.createStyleSheet(chromeDatePickerCSS, 'chromeDatePickerStyle');
}

// 本地字典加载
var _dictKey = "_dict_";
var _dictTempKey = "_dictTmp_";
var _sjpzKey = "_sjpz_";
var _userinfo = null;
var tabIndex = 1;

// 下列字段都必须二次加载
var _nodict = ",DWXXB,XZJDXXB,JWZRQXXB,JWHXXB,JLXXXB,YHXXB,_BLANK,JTCY,";

// 缓存类型：mem 无缓存，sessionStorage 缓存到会话，localStorage 缓存到本地
var _cacheType = "mem";
if (window.sessionStorage) {
	_cacheType = "sessionStorage";
}
if (window.localStorage) {
	// _cacheType = "localStorage";
}

_sys_catch_dictLocalData = {};
_sys_catch_sjpzData = {};
_sys_dictTempData = {};
_sys_catch_kzcsData = {};

// 利用H5存储localStorage

// 下拉字典
Gnt = {};
Gnt.Constants = {};
Gnt.ux = {};
Gnt.ux.Dict = {};
Gnt.ux.Dict.IgnoreLabel = "请从列表中选择";
Gnt.ux.yw = {};
Gnt.util = {};
Gnt.store = {};

Gnt.util.copyHzywToRyzl = function(rr, jo) {
	// 户政数据拷贝到人员资料，必须这个费事拷贝，否则不会出现在变更更正中
	if (jo.nbgxm && rr.data.xm != jo.nbgxm) {
		rr.set("xm", jo.nbgxm);
	}
	if (jo.bsqrsfz && rr.data.gmsfhm != jo.bsqrsfz) {
		rr.set("gmsfhm", jo.bsqrsfz);
	}
	if (jo.bsqrxb && rr.data.xb != jo.bsqrxb) {
		rr.set("xb", jo.bsqrxb);
	}
	if (jo.bsqrmz && rr.data.mz != jo.bsqrmz) {
		rr.set("mz", jo.bsqrmz);
	}
	if (jo.bsqrcsrq && rr.data.csrq != jo.bsqrcsrq) {
		rr.set("csrq", jo.bsqrcsrq);
	}
	if (jo.cym && rr.data.cym != jo.cym) {
		rr.set("cym", jo.cym);
	}
	if (jo.bsqrsjhm && rr.data.dhhm != jo.bsqrsjhm) {
		rr.set("dhhm", jo.bsqrsjhm);
	}
	if (jo.zbmap) {
		for ( var fname in jo.zbmap) {
			if (jo.zbmap[fname] != rr.data[fname]) {
				rr.set(fname, jo.zbmap[fname]);
			}
		}
	}
}

Gnt.util.trim = function(str) {
	if (!str)
		return str;

	return (str + "").replace(/^\s+|\s+$/gm, '');
}

Gnt.util.isEmpty = function(obj) {
	if (obj == undefined || obj == "" || Gnt.util.trim(obj) == "")
		return true;

	if (Ext.isArray(obj)) {
		if (obj.length == 0)
			return true;
	}

	var count = 0;
	for (o in obj) {
		if (obj[o] != "") {
			count++;
		}
	}
	if (count == 0)
		return true;

	return false;
}
Gnt.util.endWith = function(str,reg) {
	var d = str.length - reg.length;
	return (d >= 0 && str.lastIndexOf(reg) == d);
}
String.prototype.endWith = function(str) {
	var d = this.length - str.length;
	return (d >= 0 && this.lastIndexOf(str) == d);
	// if(str==null || str=="" || this.length == 0 ||str.length > this.length){
	// return false;
	// }
	// if(this.substring(this.length - str.length)){
	// return true;
	// }else{
	// return false;
	// }
	// return true;
}

String.prototype.startWith = function(str) {
	if (str == null || str == "" || this.length == 0
			|| str.length > this.length) {
		return false;
	}
	if (this.substr(0, str.length) == str) {
		return true;
	} else {
		return false;
	}
	return true;
}

/**
 * 户政业务处理 return jo:主记录 sqrlist:随迁人集合
 */
Gnt.makeHzyw = function(config) {
	// 户政业务处理
	if (config.hzywid && config.hzywid != "") {
		var subdata = {
			config_key : 'queryPoHZ_ZJ_SB',
			clbs : config.clbs?config.clbs:'0'
		};
		if (config.pch && config.pch != "") {
			subdata.pch = config.pch;
		} else {
			subdata.hzywid = config.hzywid;
		}

		var jo = null;
		Gnt
				.submit(
						subdata,
						"yw/common/queryHzywAndZB.json",
						"请稍后...",
						function(jsonData, params) {
							if (jsonData.list && jsonData.list.length > 0) {
								jo = jsonData.list[0];

								var sqrlist = [];
								for (var index = 0; index < jsonData.list.length; index++) {
									var data = jsonData.list[index];
									/**
									 * 2018.10.22 修改 刁杰 使用sfzqr字段判断主迁人
									 * 事实上可能点击了随迁人的户政业务记录,导致主迁人获取错误
									 * 
									 * 2018.10.30 修改 刁杰 新增业务类型(ywlx)字段
									 */
									if (config.ywlx && config.ywlx == '3') {
										/**
										 * 迁入业务特殊处理 使用sfzqr字段获取主信息
										 */
										if (data.sfzqr == "1") {
											jo = data;
										} else {
											sqrlist.push(data);
										}
									} else {
										if (data.id == config.hzywid) {
											jo = data;
										} else {
											sqrlist.push(data);
										}
									}
								}

								if (jo) {
									if (config.callback) {
										config.callback(jo, sqrlist);
									}
								} else {
									showInfo("没有找到户政业务，或者已经被处理！");
									if (config.cmpids) {
										Ext.each(config.cmpids, function(id) {
											Ext.getCmp(id).disable();
										});
									}
								}
							} else {
								showInfo("没有找到户政业务，或者已经被处理！");
								if (config.cmpids) {
									Ext.each(config.cmpids, function(id) {
										Ext.getCmp(id).disable();
									});
								}
							}
						}, function() {
							if (config.cmpids) {
								Ext.each(config.cmpids, function(id) {
									Ext.getCmp(id).disable();
								});
							}
						});
	}
}

/**
 * 提交数据
 */
Gnt.submit = function(data, url, waitmsg, success_callback, error_callback,
		showinfo) {
	if (data) {
		data.log_code = log_code;
	} else {
		data = {
			log_code : log_code
		}
	}

	if (showinfo == undefined)
		showinfo = true;

	if (waitmsg != undefined && waitmsg != null && waitmsg != "") {
		if (showinfo)
			Ext.Msg.wait(waitmsg, "等待");
	} else {
		if (showinfo)
			Ext.Msg.wait("操作正在执行中，请稍后...", "等待");
	}

	Ext.Ajax.request({
		url : url,
		headers : {
			'accept' : 'json'
		},
		method : 'POST',
		params : data,
		success : function(result, request) {
			if (showinfo)
				Ext.Msg.hide();
			var jsonData = Ext.util.JSON.decode(result.responseText);
			// alert(result.responseText);
			if (jsonData.success) {
				if (success_callback) {
					success_callback(jsonData, data);
				} else {
					if (jsonData.message && jsonData.message.length > 0) {
						Ext.Msg.alert("提示", jsonData.message);
					} else if (jsonData.messages
							&& jsonData.messages.length > 0) {
						Ext.Msg.alert("提示", jsonData.messages[0]);
					} else {
						Ext.Msg.alert("提示", "操作执行成功！");
					}
				}
			} else {
				if (error_callback) {
					error_callback(jsonData, data);
				} else {
					if (jsonData.message && jsonData.message.length > 0) {
						showErr(jsonData.message);
					} else if (jsonData.messages
							&& jsonData.messages.length > 0) {
						showErr(jsonData.messages[0]);
					} else {
						showErr("执行失败！");
					}
				}
			}
		},
		failure : function(result, request) {
			Ext.Msg.hide();
			if (error_callback) {
				error_callback({
					message : result.responseText
				}, {});
			} else {
				showErr(result.responseText);
			}
		}
	});
}

Gnt.loadSjpzb = function(pzlb, error) {
	var su = false;
	var ary = pzlb.split(",");
	var list = new Array();

	for (var i = 0; i < ary.length; i++) {
		if (!Gnt.ux.Dict.existsPzlbData(ary[i])) {
			list[list.length] = ary[i];
		}
	}

	if (list.length == 0)
		return true;

	pzlb = list.join(",");

	Gnt.noAsyncAjax({
		url : "dict/utils/querySjpzb?pzlb=" + pzlb
	}, function(json) {
		su = true;
		if (json.list && json.list.length > 0) {
			var data = json.list[0];
			if (data.dictMap) {
				Gnt.ux.Dict.saveDictData(data.dictMap);
			}
			if (data.pzMap) {
				Gnt.ux.Dict.saveSjpzData(data.pzMap);
			}
		}
	}, error);

	return su;
}

//add by zjm 20191202 在原有基础上，增加个新变量flag，1为显示字段，2为不显示字段
Gnt.loadSjpzbGz = function(pzlb,flag,error) {
	if(!(flag=='1'||flag=='2')){
		alert(" 数据配置类别查询类别，只能为1或2或为空！")
		return ;
	}
//	var ary = pzlb.split(",");
//	var list = new Array();
//
//	for (var i = 0; i < ary.length; i++) {
//		if (!Gnt.ux.Dict.existsPzlbData(ary[i])) {
//			list[list.length] = ary[i];
//		}
//	}
//
//	if (list.length == 0)
//		return true;
//
//	pzlb = list.join(",");
	Gnt.noAsyncAjax({
		url : "dict/utils/querySjpzbGz?pzlb=" + pzlb+"&flag="+flag
	}, function(json) {
		if (json.list && json.list.length > 0) {
			data = json.list;
			return data 
		}
	}, error);

}
// 同步AJAX
Gnt.noAsyncAjax = function(config, success, error) {
	var conn = Ext.lib.Ajax.getConnectionObject().conn;
	conn.open("POST", config.url, false);
	conn.send(null);
	Ext.Msg.hide();

	var dict = {};

	var json = Ext.decode(conn.responseText);
	if (json && !json.success) {
		if (json.message) {
			err = json.messages;
		} else {
			err = "操作失败！";
		}

		if (error)
			error(err);
		else
			alert(err);
	} else {
		if (success) {
			success(json, config.me);
		}
	}
}

// 从本地加载字典
Gnt.ux.Dict.getDictLocalData = function(visiontype) {
	var key = _dictKey + visiontype;
	if (_cacheType == "localStorage") {
		var str = localStorage.getItem(key);
		if (str)
			return Ext.decode(str);
		else
			return;
	} else if (_cacheType == "sessionStorage") {
		var str = sessionStorage.getItem(key);
		if (str)
			return Ext.decode(str);
		else
			return;
	} else {
		return _sys_catch_dictLocalData[key]
	}
}

// 判断字典是否存在
Gnt.ux.Dict.existsDictLocalData = function(visiontype) {
	var key = _dictKey + visiontype;
	if (_cacheType == "localStorage") {
		if (localStorage.getItem(key))
			return true;
	} else if (_cacheType == "sessionStorage") {
		if (sessionStorage.getItem(key))
			return true;
	} else {
		if (_sys_catch_dictLocalData[key])
			return true;
	}

	return false;
}

// 保存远程字典数据
Gnt.ux.Dict.saveDictData = function(dictData) {
	for (o in dictData) {
		var key = _dictKey + o;
		if (_cacheType == "sessionStorage")
			sessionStorage.setItem(key, Ext.encode(dictData[o]));
		else if (_cacheType == "localStorage")
			localStorage.setItem(key, Ext.encode(dictData[o]));
		else
			_sys_catch_dictLocalData[key] = dictData[o];
	}
}

/**
 * 缓存数据配置
 */
Gnt.ux.Dict.saveSjpzData = function(pzData) {
	for (o in pzData) {
		var key = _sjpzKey + o;

		if (_cacheType == "sessionStorage")
			sessionStorage.setItem(key, Ext.encode(pzData[o]));
		else if (_cacheType == "localStorage")
			localStorage.setItem(key, Ext.encode(pzData[o]));
		else
			_sys_catch_sjpzData[key] = pzData[o];
	}
}

Gnt.ux.Dict.existsPzlbData = function(pzlb) {
	var key = _sjpzKey + pzlb;

	if (_cacheType == "sessionStorage") {
		if (sessionStorage.getItem(key)) {
			return true;
		}
	} else if (_cacheType == "localStorage") {
		if (localStorage.getItem(key))
			return true;
	} else {
		if (_sys_catch_sjpzData[key])
			return true;
	}

	return false;
}

/**
 * 依据配置类别，获取配置数组
 */
Gnt.ux.Dict.getSjpzData = function(pzlb, flag) {
	var key = _sjpzKey + pzlb;

	if (_cacheType == "sessionStorage") {
		var str = sessionStorage.getItem(key)
		if (str)
			return Ext.decode(str);
	} else if (_cacheType == "localStorage") {
		var str = localStorage.getItem(key)
		if (str)
			return Ext.decode(str);
	} else {
		if (_sys_catch_sjpzData[key])
			return _sys_catch_sjpzData[key];
	}

	// 如果没有找到单个配置，那么重新远程加载
	if (!flag || flag == false) {
		// 第一次，重新读取
		if (!Gnt.loadSjpzb(pzlb, function() {
		}))
			return null;

		return Gnt.ux.Dict.getSjpzData(pzlb, true);
	}

	return null;
}

// 保存字典临时翻译
Gnt.ux.Dict.saveDictLable = function(visiontype, code, codename) {
	var key = _dictTempKey + visiontype + "_" + code;

	if (_cacheType == "sessionStorage") {
		sessionStorage.setItem(key, codename);
	} else if (_cacheType == "localStorage")
		localStorage.setItem(key, codename);
	else
		_sys_dictTempData[key] = codename;
}

// 判断字典临时翻译是否存在
Gnt.ux.Dict.existsDictLable = function(visiontype, code) {
	var key = _dictTempKey + visiontype + "_" + code;
	if (_cacheType == "sessionStorage") {
		if (sessionStorage.getItem(key))
			return true;
	} else if (_cacheType == "localStorage") {
		if (localStorage.getItem(key))
			return true;
	} else {
		if (_sys_dictTempData[key])
			return true;
	}

	return false;
}

// 获取用户信息
Gnt.ux.Dict.getUser = function(callback) {
	if (_userinfo) {
		if (callback)
			callback(_userinfo);

		return;
	}

	Gnt.submit({}, "yw/common/getUserInfo", "获取用户信息，请稍后...", function(jsonData,
			params) {
		_userinfo = jsonData.opmap;
		if (!_userinfo) {
			showErr("用户已经过期！");
			return;
		}

		if (callback) {
			callback(_userinfo);
		}
	});
}

Gnt.ux.Dict.getKzcs = function(kzlb, callback, s) {
	// 字符串处理
	kzlb = kzlb + "";

	var keylist = kzlb.split(",");
	var kzdata = {};
	var str = "";
	for (var i = 0; i < keylist.length; i++) {
		var cslx = keylist[i];
		var key = "_xt_xtkzcsb_" + cslx;

		var val = "";
		if (_cacheType == "sessionStorage") {
			val = sessionStorage.getItem(key);
		} else if (_cacheType == "localStorage") {
			val = localStorage.getItem(key);
		} else {
			if (_sys_catch_kzcsData[key])
				val = _sys_catch_kzcsData[key];
		}

		if (val && val != "") {
			if (_sys_catch_kzcsData[key])
				kzdata[cslx] = val;
			else
				kzdata[cslx] = Ext.decode(val);
		} else {
			kzdata[cslx] = "";
			str += cslx + ",";
		}
	}

	if (str == "") {
		// 所有配置都找到了
		if (callback) {
			if (_userinfo) {
				callback(kzdata[keylist[0]], _userinfo, kzdata, s);
			} else {
				Gnt.ux.Dict.getUser(function(user) {
					if (callback) {
						callback(kzdata[keylist[0]], user, kzdata, s);
					}
				});
			}
		}

		return val;
	}

	Gnt.submit({
		kzcs : kzlb
	}, "yw/common/queryKzcsb", "获取控制参数，请稍后...", function(jsonData, params) {
		var list = jsonData.list;
		for (var i = 0; i < list.length; i++) {
			var data = list[i];
			var key = "_xt_xtkzcsb_" + data.kzlb;

			if (_cacheType == "sessionStorage")
				sessionStorage.setItem(key, Ext.encode(data));
			else if (_cacheType == "localStorage")
				localStorage.setItem(key, Ext.encode(data));
			else
				_sys_catch_kzcsData[key] = data;

			if (kzdata[data.kzlb] == "") {
				kzdata[data.kzlb] = data;
			}
		}

		_userinfo = jsonData.opmap;
		if (!_userinfo) {
			showErr("用户已经过期！");
			return;
		}

		if (callback) {
			callback(kzdata[keylist[0]], _userinfo, kzdata, s);
		}
	});

	return null;
}

// 翻译字典
Gnt.ux.Dict.getDictLable = function(visiontype, code, cellmeta, record,
		rowIndex, columnIndex, store) {
	
	var reg=new RegExp("[\\u4E00-\\u9FFF]+","g");;
	if(reg.test(code)){
		return code;
	}
	
	var key = _dictTempKey + visiontype + "_" + code;
	if (_cacheType == "sessionStorage") {
		var name = sessionStorage.getItem(key);
		if (name)
			return name;
	} else if (_cacheType == "localStorage") {
		var name = localStorage.getItem(key);
		if (name)
			return name;
	} else {
		if (_sys_dictTempData[key])
			return _sys_dictTempData[key];
	}
	
	if (visiontype.indexOf("CS_") < 0) {
		if (!code || code == "")
			return code;

		// 特殊字典，本地不缓存，远程翻译
		var item = null;
		Gnt.ux.Dict.getDictRemoteLabel(visiontype, code, function(json) {
			if (json && json.list && json.list.length > 0) {
				item = json.list[0];
				Gnt.ux.Dict.saveDictLable(visiontype, code, item.codename);
			}
		});

		if (item)
			return item.codename;

		return code;
	} else {
		var codelist = Gnt.ux.Dict.getDictLocalData(visiontype);
		if (codelist && codelist.length > 0) {
			for (var i = 0; i < codelist.length; i++) {
				if (code == codelist[i].code) {
					Gnt.ux.Dict.saveDictLable(visiontype, code,
							codelist[i].codename);
					return codelist[i].codename;
				}
			}
		}

		return code;
	}
}

/*
 * 普通业务通用save代码，必须自己实现submitData方法 对业务表单，申报人表单，等都有要求
 */
Gnt.yw = {};

// 通用：查询户地信息
Gnt.yw.queryHdxx = function(config) {
	Gnt.submit({
		hhnbid : config.hhnbid
	}, "yw/common/queryHdxx", "查询户地信息，请稍后...", function(jsonData, params) {
		if (config.callback) {
			config.callback(jsonData.list[0], jsonData.list[1]);
		}
	});
};

// 通用：查询本市市区配置信息
Gnt.yw.queryHbbgsp = function(config) {
	Gnt.submit({}, "yw/common/queryXt_bssqb", "查询本市市区配置信息，请稍后...", function(
			jsonData, params) {
		if (config.callback) {
			config.callback(jsonData);
		}
	});
};

/**
 * 组装变更更正数据 grid 户成员数据集 bggzstore 变更更正store
 */
Gnt.yw.getBggzSubmitdata = function(grid, bggzstore) {
	if (grid == null || grid == undefined) {
		showErr("未知户成员数据集，变更更正数据无法完成！");
		return;
	}

	var voBggzxx = [];

	var map = {};
	grid.store.each(function(rec) {
		var data = rec.data;
		map[rec.data.rynbid] = data.sbhjywid;
	}, grid);

	// 变更更正信息
	var bggzmap = {};
	if (bggzstore.getCount() > 0) {
		for (var index = 0; index < bggzstore.getCount(); index++) {
			var bggzdata = bggzstore.getAt(index).data;
			bggzdata.sbhjywid = map[bggzdata.rynbid];
			bggzdata.flag = '1';

			if (!bggzmap[bggzdata.rynbid]) {
				bggzmap[bggzdata.rynbid] = {
					rynbid : bggzdata.rynbid,
					sbhjywid : bggzdata.sbhjywid,
					bggzxxList : new Array()
				}
			}

			bggzmap[bggzdata.rynbid].bggzxxList.push(bggzdata);
		}

		for (rynbid in bggzmap) {
			voBggzxx.push(bggzmap[rynbid]);
		}
	}

	return voBggzxx;
}

/**
 * 初始化变更更正数据： ywgrid 户成员数据集 ywform 户成员数据集对应的form bggzstore 变更更正store
 */
Gnt.yw.getBggzlb = function(fname) {
	if (fname == "xxjb")
		return "81";
	if (fname == "jgssxq")
		return "71";
	if (fname == "csdssxq")
		return "61";
	if (fname == "mz")
		return "51";
	if (fname == "csrq")
		return "41";
	if (fname == "xb")
		return "32";// 默认改为32 20190926
	if (fname == "cym")
		return "23";
	if (fname == "xm")
		return "21";
	if (fname == "gmsfhm")
		return "12";// 默认改为12 20190926

	return "91";
}

Gnt.yw.initBggzStore = function(ywgrid, bggzstore, removeFlag, bgValig,pzlb) {
	var rs = ywgrid.store.getModifiedRecords();

	if (removeFlag == false) {
		// 不清空
	} else {
		// 清空
		bggzstore.removeAll();
	}
	var pzlbData = Gnt.ux.Dict.getSjpzData(pzlb);
	var dictMap = new Map();
	Ext.each(pzlbData,function(r){
		if(r.dsname != undefined&&r.dsname){
			dictMap.set(r.fieldname,r.dsname);
		}else{
			dictMap.set(r.fieldname,'');
		}
	});
	if (rs != null && rs.length > 0) {
		for (var i = 0; i < rs.length; i++) {
			var obj = rs[i].getChanges();
			for (f in obj) {
				// 特殊属性，不是变动范围（框架代码其它如果不是使用此公用方法，可能也需要变动，请检查）
				if (f == "_sel" || f == "_sel_2")
					continue;
				
				var data = '';
				if(f=='zp'){
					data = {
							rynbid : rs[i].data.rynbid,
							xm : rs[i].data.xm,
							bggzxm : f,
							bggzxm_mc : ywgrid.getFieldLabel(f),
							bggzqnr : rs[i].modified[f],
							bggzqnrTrans:rs[i].modified[f]?'(BLOB)':'',
							bggzhnr : obj[f],
							bggzhnrTrans:obj[f]?'ZpNewData(BLOB)':'',
							bggzlb : Gnt.yw.getBggzlb(f)
						};
				}else{
					data = {
							rynbid : rs[i].data.rynbid,
							xm : rs[i].data.xm,
							bggzxm : f,
							bggzxm_mc : ywgrid.getFieldLabel(f),
							bggzqnr : rs[i].modified[f],
							bggzqnrTrans:Gnt.ux.Dict.getDictLable(dictMap.get(f)?dictMap.get(f).toUpperCase():"",rs[i].modified[f]),
							bggzhnr : obj[f],
							bggzhnrTrans:Gnt.ux.Dict.getDictLable(dictMap.get(f)?dictMap.get(f).toUpperCase():"",obj[f]),
							bggzlb : Gnt.yw.getBggzlb(f)
					};
				}
//				var data = {
//					rynbid : rs[i].data.rynbid,
//					xm : rs[i].data.xm,
//					bggzxm : f,
//					bggzxm_mc : ywgrid.getFieldLabel(f),
//					bggzqnr : rs[i].modified[f],
//					bggzqnrTrans:bggzqnrTrans,
//					bggzhnr : obj[f],
//					bggzhnrTrans:bggzhnrTrans,
//					bggzlb : Gnt.yw.getBggzlb(f)
//				};

				if (Gnt.util.isEmpty(data.bggzqnr)) {
					data.bggzqnr = null;
				}
				if (Gnt.util.isEmpty(data.bggzhnr)) {
					data.bggzhnr = null;
				}

				if (data.bggzhnr == data.bggzqnr) {
					continue;
				}
				if (rs[i].data._sel == '1' && bgValig) {
					continue;
				}
				var r = new bggzstore.reader.recordType(data);
				var sfAddFlag = true; 
				
				bggzstore.each(function(r){
					var eachdata = r.data;
					if(data.rynbid==eachdata.rynbid&&data.bggzxm==eachdata.bggzxm){
						sfAddFlag =false;
					}
				});
				if(sfAddFlag){
					bggzstore.add([ r ]);
				}
			}
		}
	}
}

Gnt.yw.checkCh = function(sfzh, xm, callback) {
	if (!sfzh || sfzh == "" || !xm || xm == "") {
		callback();
		return;
	}

	Gnt.submit({
		sfzh : sfzh,
		xm : xm
	}, "yw/spgl/qrsp/checkQrspdjyw", "校验数据，请稍后...", function(jsonData, params) {
		var data = jsonData.list[0];

		// 组装重号信息
		var chListxm = [];
		if (data.chMap) {
			for (sfzh in data.chMap) {
				var xm = data.xmMap[sfzh];
				chListxm.push({
					xm : xm,
					gmsfhm : sfzh
				});
			}
		}

		if (chListxm.length > 0) {
			var d = new Gnt.ux.QrspChDialog({
				issp : "0",
				callback : function(re, reMap) {
					callback(re, reMap);
				}
			});
			d.setData({
				chMap : data.chMap,
				chListxm : chListxm
			});
			d.show();
		} else {
			callback();
		}
	});
};

/**
 * chCheckGrid 重号检查grid
 */
Gnt.yw.save = function(type, emptyFlag, checkType, chCheckGrid) {
	if (!form_yw.checkInput(emptyFlag, checkType, checkType == '7' ? false
			: true))
		return;

	if (!form_sbr.getForm().isValid()) {
		showErr("申报人信息必须填写！");
		return;
	}

	if (!Gnt.validHz(type, form_hcy, form_yw)) {
		return;
	}

	// 判断非迁出户必须存在户主
	var exists = false;
	var icount = 0;
	hcyGrid.store.each(function(r) {
		var data = r.data;
		if (data._sel == "1") {
			// 迁出成员
		} else {
			icount++;
			if (data.yhzgx == "01" || data.yhzgx == "02" || data.yhzgx == "03")
				exists = true;
		}
	});

	/**
	 * 新增判断户成员关系及业务列表判断关系 防止判断户主出错
	 */
	ywGrid.store
			.each(function(r) {
				var data = r.data;
				icount++;
				if (data.yhzgx
						&& (data.yhzgx == "01" || data.yhzgx == "02" || data.yhzgx == "03"))
					exists = true;
			});

	/**
	 * BugFree 371 迁入业务,与户主关系为''01本人',保存时,却提示没有户主 迁入业务的户主判断在户成员列表中,但是只提交了业务列表
	 * 导致判断出错
	 */
//	if (!exists && icount > 0) {
//		showErr("没有户主，业务无法完成！");
//		return;
//	}

	if (chCheckGrid) {
		var sfzh = "", xm = "";
		ywGrid.store.each(function(r) {
			var data = r.data;
			if (data.gmsfhm && data.gmsfhm != "") {
				sfzh += "," + data.gmsfhm;
				xm += "," + data.xm;
			}
		});

		Gnt.yw.checkCh(sfzh, xm, function(re, reMap) {
			if (re == undefined) {
				// 没有匹配重号信息
			} else {
				// 匹配重号信息
				chCheckGrid.store.each(function(r) {
					if (reMap[r.data.gmsfhm] != undefined) {
						r.data.ryid = reMap[r.data.gmsfhm];
						if (r.data.ryid == "0" || r.data.ryid == 0)
							r.data.ryid = null;
					}
				});
			}

			Gnt.yw.saveNext();
		});

		return;
	}
	Gnt.yw.saveNext();
}

Gnt.yw.saveNext = function() {
	// 初始化变更更正
	Gnt.yw.initBggzStore(hcyGrid, bggzWin.grid.store,null,null,hcyGrid.pzlb,callback);
	var store = bggzWin.grid.store;
	if (store.getCount() > 0) {
		bggzWin.show();
	} else {
		submitData();
	}
}

// EXT3.0之后被AJAX被干掉同步配置async: false,4.0之后又放开
Ext.lib.Ajax.getConnectionObject = function() {
	var activeX = [ 'MSXML2.XMLHTTP.3.0', 'MSXML2.XMLHTTP', 'Microsoft.XMLHTTP' ];
	function createXhrObject(transactionId) {
		var http;
		try {
			http = new XMLHttpRequest();
		} catch (e) {
			for (var i = 0; i < activeX.length; ++i) {
				try {
					http = new ActiveXObject(activeX[i]);
					break;
				} catch (e) {
				}
			}
		} finally {
			return {
				conn : http,
				tId : transactionId
			};
		}
	}

	var o;
	try {
		if (o = createXhrObject(Ext.lib.Ajax.transactionId)) {
			Ext.lib.Ajax.transactionId++;
		}
	} catch (e) {
	} finally {
		return o;
	}
};

// 定义ajax，简化操作
var ajax_lastconfig = {};
var ajax = function(config) {
	ajax_lastconfig = config;

	Ext.Msg.wait(config.wait_msg ? config.wait_msg : "操作正在执行中，请稍后...",
			config.wait_title ? config.wait_title : "请稍后");

	Ext.Ajax.request({
		async : false,
		url : config.url, // 请求地址
		params : config.params, // 请求参数
		method : 'POST',
		contentType: "application/json;charset=utf-8",//add by zjm 20200715 记住加上charset=utf-8，否则Ajax请求有可能会报SCRIPT7002错误
		callback : function(options, success, response) {
			// 调用回调函数
			Ext.MessageBox.hide();
			var json = response.responseText;

			if (success) {
				var json = Ext.decode(response.responseText);

				if (config.success && json.success) {
					if (json.messages) {
						showInfo(json.messages[0]);
					}
					return config.success(response, options, json)
				} else {
					if (json.messages) {
						err = json.messages[0];
					} else {
						err = "操作失败！";
					}

					if (json.success) {
						showInfo(err);
						return;
					}
				}
			}

			if (config.error) {
				config.error(json);
			}
			showErr(err);
		}
	});
	return false;
};

Gnt.ux.IFrameComponent = Ext.extend(Ext.BoxComponent, {
	onRender : function(ct, position) {
		this.el = ct.createChild({
			tag : 'iframe',
			id : 'iframe-' + this.id,
			frameBorder : 0,
			src : this.url
		});
	}
});

// 加载字典
Gnt.ux.Dict.loadDict = function(visiontypes, callback) {
	var dicts = new Array();

	if (Ext.isArray(visiontypes)) {
		for (var i = 0; i < visiontypes.length; i++) {
			if (!Gnt.ux.Dict.existsDictLocalData(visiontypes[i]))
				dicts.push(visiontypes[i]);
		}
	} else {
		for (name in visiontypes) {
			if (!Gnt.ux.Dict.existsDictLocalData(name))
				dicts.push(name);
		}
	}

	if (dicts.length <= 0) {
		if (callback)
			callback();
		return;
	}

	Gnt.noAsyncAjax({
		url : 'dict/utils/loadDcit?visiontypes=' + dicts.join(",")
	}, function(json) {
		if (json && json.dictMap) {
			Gnt.ux.Dict.saveDictData(json.dictMap);

			if (callback)
				callback(json.dictMap);

			return;
		}

		if (callback)
			callback({});
	});
}

/**
 * 服务器翻译字典
 */
Gnt.ux.Dict.getDictRemoteLabel = function(visiontype, code, callback) {
	Gnt.noAsyncAjax({
		url : 'dict/utils/getDictRemoteLabel?visiontype=' + visiontype
				+ '&code=' + code
	}, function(json) {
		if (callback)
			callback(json);
	});
}

Gnt.isAllowBlank = function(pz, config) {
	// 隐藏的不能作为必须录入
	if (pz.visibletype && pz.visibletype.indexOf("000") == 0)
		return true;

	if (config.formType == 'query' || config.formType == 'view')
		return true;

	// 显示
	if (pz.displayname && pz.displayname != "")
		return pz.displayname.charAt(0) == "*" ? false : true;

	return true;
}

Gnt.isMust = function(pz, config) {
	// 隐藏的不能作为必须录入
	if (pz.visibletype && pz.visibletype.indexOf("11") == 0)
		return 1;
	if (pz.visibletype && pz.visibletype.indexOf("10") == 0)
		return 2;
	if (pz.visibletype && pz.visibletype.indexOf("01") == 0)
		return 3;

	return true;
}
/**
 * 依据数据配置表记录获取Ext对象
 */
Gnt.getExtField = function(pz, config) {
	var formType = config.formType;

	var f = {};
	f.name = pz.fieldname;
	f.fieldLabel = pz.displayname;
	f.columnWidth = (config.cols ? (1 / config.cols) : 0.25);
	f.anchor = "100%";
	f.xtype = "textfield";
	f.labelWidth = 120;
	/*
	 * 虽然设置了长度但是仍然可以保存 if("zqzbh" == pz.fieldname){ if(pz.fieldlength){
	 * f.maxLength = pz.fieldlength; } }
	 */
	if (pz.readonly == "1") {
		f.readOnly = true;
//		f.disabled = true;
//		f.typeAhead=false;
//		f.editable=false;
		f.bodyStyle = 'background-color:#F0F0F0';
	} else {
		// 校验规则
		if (pz.vtype && pz.vtype != "") {
			// alert(pz.vtype);
		}
		if (pz.vtype && pz.vtype != "" && Ext.form.VTypes[pz.vtype]) {
			f.vtype = pz.vtype;
		}
	}

	if (pz.displayname && pz.displayname != "")
		f.allowBlank = Gnt.isAllowBlank(pz, config);

	var isHidden = true;
	if (pz.visibletype) {
		// VISIBLETYPE:显示类型，由5位构成，从左向由，
		// 第一位: 设置查询条件是否显示，
		// 第二位: 设置数据输入是否显示
		if (formType == "edit" || formType == "view") {
			// 编辑
			if (pz.visibletype.length >= 2
					&& pz.visibletype.substring(1, 2) == '1') {
				isHidden = false;
			}
		} else {
			// 查询
			if (pz.visibletype.length >= 1
					&& pz.visibletype.substring(0, 1) == '1') {
				isHidden = false;
			}
		}
	}

	if (isHidden) {
		f.xtype = "hidden";
	} else {
		if (!pz.fieldtypename || pz.fieldtypename == null) {
			;
		} else if (pz.fieldtypename == "dateedit") {
			// 日期
			// f.xtype = "datefield";
			// f.altFormats = "Ymd|Y-m-d|Y/m/d";
			// f.format = "YmdHis";
			if (pz.displayfieldlength >= 50) {
				f.xtype = "datefield";
				f.altFormats = "Ymd|Y-m-d|Y/m/d";//
				f.format = "YmdHis";// H:i:s
			} else if(pz.fieldlength==20){
				f.xtype = "datefield";
				f.altFormats = "Ymd|Y-m-d|Y/m/d";
				f.format = "YmdHis";
			}else {
				f.xtype = "datefield";
				f.altFormats = "Ymd|Y-m-d|Y/m/d";
				f.format = "Ymd";
			}
			if (pz.fieldname == "cssj") {
				// 特殊字段
				f.xtype = "timefield";
				f.format = "H:i:s";
				f.altFormats = "His|H:i:s";
			}
		} else if (pz.fieldtypename == "codeedit" && pz.dsname
				&& pz.dsname != "") {
			f.dsname = pz.dsname.toUpperCase();
			f.xtype = "dict_combox";
			f.dict = "VisionType=" + f.dsname;
			f.hiddenName = pz.fieldname;

			// 行政区划
			if (f.dsname.indexOf("XZQHB") == 0) {
				f.xtype = "tree_comboBox";
				f.searchUrl = "dict/utils/searchXzqh";
				f.treeUrl = "dict/utils/searchTreeXzqh";

				if (f.dsname.indexOf("XZQHBNEW") == 0) {
					f.searchUrl += "?isnew=1";
					f.treeUrl += "?isnew=1";
					// alert(f.treeUrl);
				}
			} else if ("DWXXB,XZJDXXB,JWZRQXXB,JWHXXB,JLXXXB,YHXXB"
					.indexOf(f.dsname) >= 0) {
				f.xtype = "search_combox";
				f.searchUrl = "dict/utils/searchXxb?visiontype=" + f.dsname;
				f.valueField = "code";
				f.displayField = "name";
			} else if (f.dsname.indexOf("CS_") != 0) {
				// 可选可输入
				f.forceSelection = false;
				f.selectOnFocus = false;
				f.xtype = "dict_combox_cust";
			} else {
				;
			}

			if (pz.allowselfinput == '1') {
				// 辅助录入
				f.editable = true;
				f.allowselfinput = '1';
				f.forceSelection = false;
				f.selectOnFocus = false;
			}

			if (f.dsname == "JTCY") {
				// 家庭成员，特殊
				f.forceSelection = false;
				f.typeAhead = false;
			}
		}
	}
	// 监视器
	f.listeners = {};

	f.listeners.change = config.fieldValueChange ? config.fieldValueChange
			: function() {
			};
	f.listeners.blur = function(field) {
		var f = field.findParentByType("sjpz_form");
		if (field.allowselfinput && field.allowselfinput == "1") {
			if (f.fieldValueChange) {
				v = field.getRawValue();
				f.fieldValueChange(field, v, null);
			}
		}
		if (f.fieldBlur) {
			f.fieldBlur(field);
		}
	}
	f.listeners.focus = function(field) {
		var f = field.findParentByType("sjpz_form");
		if (f) {
			f.lastFocusField = field;
			if (f.fieldFocus)
				f.fieldFocus(field);
		}
	};

	if (formType == "query"
			&& (pz.conditionoperator == "<" || pz.conditionoperator == ">")) {
		// 范围条件
		var a = new Array();
		if(pz.fieldname=='csrq'&&pz.displayname=='年龄'){
			f.name = "_start_" + pz.fieldname+"edit";
		}else{
			f.name = "_start_" + pz.fieldname;
		}
		f.fieldLabel = pz.displayname + "(起)";
		a.push(f);

		// 拷贝对象
		var f2 = {};
		for (o in f) {
			f2[o] = f[o];
		}
		if(pz.fieldname=='csrq'&&pz.displayname=='年龄'){
			f2.name = "_end_" + pz.fieldname+"edit";
		}else{
			f2.name = "_end_" + pz.fieldname;
		}
		f2.fieldLabel = pz.displayname + "(至)";
		a.push(f2);

		return a;
	}
	if (formType == "view" && f.xtype != "hidden") {
		f.hideTrigger = true;
		f.readOnly = true;
		f.disabled = true;
	}

	return f;
}

// 仅仅获取Field对象（组合查询用）
Gnt.getExtField2 = function(pz) {
	var f = {};
	f.name = pz.fieldname;
	f.fieldLabel = pz.displayname;
	f.columnWidth = 0.25;
	f.anchor = "100%";
	f.xtype = "textfield";
	f.labelWidth = 120;

	// 校验规则
	if (pz.vtype && pz.vtype != "") {
		// alert(pz.vtype);
	}
	if (pz.vtype && pz.vtype != "" && Ext.form.VTypes[pz.vtype]) {
		f.vtype = pz.vtype;
	}

	var isHidden = false;
	if (isHidden) {
		f.xtype = "hidden";
	} else {
		if (!pz.fieldtypename || pz.fieldtypename == null) {
			;
		} else if (pz.fieldtypename == "dateedit") {
			// 日期
			f.xtype = "datefield";
			f.altFormats = "Ymd|Y-m-d|Y/m/d";
			f.format = "Ymd";
			if (pz.fieldname == "cssj") {
				// 特殊字段
				f.xtype = "timefield";
				f.format = "H:i:s";
				f.altFormats = "His|H:i:s";
			}
		} else if (pz.fieldtypename == "codeedit" && pz.dsname
				&& pz.dsname != "") {
			f.dsname = pz.dsname.toUpperCase();
			f.xtype = "dict_combox";
			f.dict = "VisionType=" + f.dsname;
			f.hiddenName = pz.fieldname;

			// 行政区划
			if (f.dsname.indexOf("XZQHB") == 0) {
				f.xtype = "tree_comboBox";
				f.searchUrl = "dict/utils/searchXzqh";
				f.treeUrl = "dict/utils/searchTreeXzqh";
			} else if ("DWXXB,XZJDXXB,JWZRQXXB,JWHXXB,JLXXXB,YHXXB"
					.indexOf(f.dsname) >= 0) {
				f.xtype = "search_combox";
				f.searchUrl = "dict/utils/searchXxb?visiontype=" + f.dsname;
				f.valueField = "code";
				f.displayField = "name";
			} else if (f.dsname.indexOf("CS_") != 0) {
				// 可选可输入
				f.forceSelection = false;
				f.selectOnFocus = false;
				f.xtype = "dict_combox_cust";
			} else {
				;
			}

			if (pz.allowselfinput == '1') {
				// 辅助录入
				f.editable = true;
				f.allowselfinput = '1';
				f.forceSelection = false;
				f.selectOnFocus = false;
			}

			if (f.dsname == "JTCY") {
				// 家庭成员，特殊
				f.forceSelection = false;
				f.typeAhead = false;
			}
		}
	}

	return f;
}

/**
 * 依据数据配置表记录获取Store的cols对象
 */
Gnt.getRecordField = function(pz, config) {
	var types = Ext.data.Types;

	var f = {
		name : pz.fieldname,
		type : "string"
	};

	if (pz.visibletype && pz.visibletype.indexOf("000") == 0) {
	} else {
		if (!pz.fieldtypename || pz.fieldtypename == null) {
		} else if (pz.fieldtypename == "dateedit") {
		} else if (pz.fieldtypename == "codeedit") {
		}
	}

	return f;
}

/**
 * 依据数据配置表获取form的items
 */
Gnt.getFormItems = function(config) {
	// Ext.Msg.wait("操作正在执行中，请稍后...", "请稍后");
	var pzlb = config.pzlb;
	var ary = Gnt.ux.Dict.getSjpzData(pzlb);
	var items = new Array();
	var hidden_items = new Array();

	if (ary instanceof Array) {
		for (var i = 0; i < ary.length; i++) {
			var data = Gnt.getExtField(ary[i], config);
			if (data.xtype == 'hidden') {
				hidden_items.push(data);
			} else {
				if (data instanceof Array) {
					// 查询区域范围字段，返回2个数据，否则返回1个
					for (var j = 0; j < data.length; j++) {
						items.push(data[j]);
					}
				} else {
					items.push(data);
				}
			}
		}

		// 重新组合
		items = Gnt.toFormSjpz(items);
		for (var i = 0; i < hidden_items.length; i++) {
			items.push(hidden_items[i]);
		}
	} else {
		alert("配置" + pzlb + "本地缓存不存在！");
		items.push({
			xtype : 'hidden'
		});
	}

	return items;
}

// 设置表单只读
Gnt.toFormReadyonly = function(form) {
	var ary = Gnt.ux.Dict.getSjpzData(form.pzlb);
	for (var i = 0; i < ary.length; i++) {
		var fname = ary[i].fieldname;
		SetReadOnly(form, fname, true);
	}
}

// 设置表单编辑
Gnt.toFormEdit = function(form) {
	var ary = Gnt.ux.Dict.getSjpzData(form.pzlb);
	for (var i = 0; i < ary.length; i++) {
		var fname = ary[i].fieldname;
		SetReadOnly(form, fname, false);
	}
}

// 设置记录同第一人
Gnt.setFirst = function(store, rowindex, record, data) {
	var ary = Gnt.ux.Dict.getSjpzData(store.pzlb);
	var rec = store.getAt(rowindex);
	for (var i = 0; i < ary.length; i++) {
		var fname = ary[i].fieldname;
		if (ary[i].displayname.search("ID") == -1) {
			if (store.pzlb == '10024' || store.pzlb == '10015') {
				/**
				 * 个性化 10024:迁出信息 10015:住址变动
				 */
				if ("sbrjtgx" == fname) {
					// 不替换 *与持证人关系
				} else {
					rec.set(fname, record.get(fname));
				}
			} else {
				rec.set(fname, record.get(fname));
			}
		} else {
			rec.set(fname, data.get(fname));
			/*
			 * if(ary[i].fieldname == "rynbid"){ rec.set(fname,data.get(fname)); }
			 */
		}
	}
	return rec;
}

// 设置所有记录同第一人
Gnt.sameFirst = function(store, grid, form) {

	var record = store.getAt(0);
	var toRec = null;

	/**
	 * 所有业务记录同第一人
	 */
	for (var i = 1; i < grid.store.getCount(); i++) {
		var data = grid.store.getAt(i).data;

		if (data._sel == '1') {
			toRec = Gnt.setFirst(store, i, record, grid.store.getAt(i));
		}

		form.getForm().loadRecord(toRec);
	}

	/**
	 * 单一记录设置为同第一人 var selections = grid.getSelectionModel().getSelections();
	 * var store = grid.getStore(); var selectData = selections[0]; var
	 * dataIndex = store.indexOf(selectData);
	 * 
	 * Gnt.setFirst(store,dataIndex,record);
	 * 
	 * form.getForm().loadRecord(record);
	 */

}

/**
 * txtstring:需要转全角的字符串 flag:是否将拼音,符号转全角
 */
Gnt.ToDBC = function(txtstring, flag) {
	var tmp = "";
	for (var i = 0; i < txtstring.length; i++) {

		/**
		 * 空格
		 */
		if (txtstring.charCodeAt(i) == 32) {
			tmp += String.fromCharCode(12288);
		}

		var tempVal = txtstring.substr(i, 1);
		if (flag) {
			/**
			 * 数字,拼音,符号
			 */
			if (txtstring.charCodeAt(i) < 127) {
				tmp += String.fromCharCode(txtstring.charCodeAt(i) + 65248);
			}

			/**
			 * 无法转换全角 例如中文字符
			 */
			if (txtstring.charCodeAt(i) != 32 && txtstring.charCodeAt(i) >= 127) {
				tmp += tempVal;
			}
		} else {
			if (Gnt.isNumber(tempVal)) {
				if (txtstring.charCodeAt(i) < 127) {
					tmp += String.fromCharCode(txtstring.charCodeAt(i) + 65248);
				}
			} else {
				tmp += tempVal;
			}
		}
	}
	return tmp;
}

/**
 * txtstring:需要转半角的字符串 flag:是否将拼音,符号转半角
 */
Gnt.ToCBD = function(txtstring, flag) {
	var tmp = "";
	for (var i = 0; i < txtstring.length; i++) {
		if (txtstring.charCodeAt(i) > 65248 && txtstring.charCodeAt(i) < 65375) {
			tmp += String.fromCharCode(txtstring.charCodeAt(i) - 65248);
		} else {
			tmp += String.fromCharCode(txtstring.charCodeAt(i));
		}
	}
	return tmp;
}

/**
 * 是否为数字
 */
Gnt.isNumber = function(val) {
	var regPos = /^\d+(\.\d+)?$/; // 非负浮点数
	var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/; // 负浮点数
	if (regPos.test(val) || regNeg.test(val)) {
		return true;
	} else {
		return false;
	}
}

Gnt.dealHzyw = function(hzywId) {

	var store = new Gnt.store.SjpzStore({
		pzlb : '20000',
		pkname : 'rynbid',
		url : 'yw/hjyw/hzywcl/dealHzyw.json'
	});

	store.baseParams = {
		hzywid : hzywId
	};

	store.load();
}

Gnt.ux.Dict.getStoreList = function(smap, json) {
	var i = 0;
	var list = new Array();
	if (smap.ignore == undefined) {
		smap.ignore = "true";
		smap.ignoretext = "请选择";
	}

	if (smap && smap.ignore != undefined
			&& (smap.ignore == "true" || smap.ignore == true)) {
		list[0] = new Array();
		if (smap.ignoretext)
			list[0][1] = smap.ignoretext;
		else
			list[0][1] = Gnt.ux.Dict.IgnoreLabel;

		list[0][0] = "";
		i++;
	}

	if (smap.exclude)
		smap.exclude = "," + smap.exclude + ",";

	if (smap.vals)
		smap.vals = "," + smap.vals + ",";
	if (json && json.list) {
		var codelist = json.list;
		if (codelist && codelist.length > 0) {
			for (var i = 0; i < codelist.length; i++) {
				// 排除
				if (smap.exclude)
					if (smap.exclude.indexOf("," + codelist[i].code + ",") >= 0)
						continue;

				// 固定值
				if (smap.vals) {
					if (smap.vals.indexOf("," + codelist[i].code + ",") < 0)
						continue;
				}

				var len = list.length;
				list[len] = new Array();
				if (smap.allowselfinput && smap.allowselfinput == '1') {
					list[len][1] = codelist[i].codename;
					list[len][0] = codelist[i].codename;
				} else {
					list[len][1] = codelist[i].code + " "
							+ codelist[i].codename+" "+codelist[i].pyt;
					list[len][0] = codelist[i].code;
				}
			}
		}
	} else {
		var codelist = Gnt.ux.Dict.getDictLocalData(smap.VisionType);

		if (codelist && codelist.length > 0) {
			for (var i = 0; i < codelist.length; i++) {
				if (smap.exclude)
					if (smap.exclude.indexOf("," + codelist[i].code + ",") >= 0)
						continue;

				// 固定值
				if (smap.vals) {
					if (smap.vals.indexOf("," + codelist[i].code + ",") < 0)
						continue;
				}

				var len = list.length;
				list[len] = new Array();

				if (smap.allowselfinput && smap.allowselfinput == '1') {
					list[len][1] = codelist[i].codename;
					list[len][0] = codelist[i].codename;
				} else {
					list[len][1] = codelist[i].code + " "
							+ codelist[i].codename+" "+codelist[i].pyt;
					list[len][0] = codelist[i].code;
				}
			}
		} else {
			if (smap.VisionType != 'JTCY') {
				Gnt.ux.Dict.loadDict([ smap.VisionType ], function(dictmap) {
					if (dictmap && dictmap[smap.VisionType]) {
						var jsondata = {
							list : dictmap[smap.VisionType]
						};
						return Gnt.ux.Dict.getStoreList(smap, jsondata);
					} else {
						// DWXXB,XZJDXXB,JWZRQXXB,JWHXXB,JLXXXB,YHXXB,
						if (smap.VisionType != "_BLANK"
								&& smap.VisionType.indexOf("CS_") == 0)
							;// alert("字典[" + smap.VisionType + "]数据未加载！");
					}
				});
			}
		}
	}

	return list;
}

// 获取字符串字符数
Gnt.ux.getStrLength = function(Str) {
	var i, len, code;
	if (Str == null || Str == "")
		return 0;
	len = Str.length;
	for (i = 0; i < Str.length; i++) {
		code = Str.charCodeAt(i);
		if (code > 255) {
			// 一个汉字默认三个字符
			len += 2;
		}
	}
	return len;
}

// 普通下拉字典
// dict:"VisionType=CS_XB&def=4&ignore=false",
// dict:"methodName=listYwDqbm&def=4&ignore=false",
Gnt.ux.DictComboBox = Ext
		.extend(
				Ext.form.ComboBox,
				{
					fieldLabel : '处理状态',
					mode : 'local',
					triggerAction : 'all',
					valueField : "code",
					displayField : "name",
					emptyText : '请选择',
					typeAhead : true,
					selectOnFocus : true,
					editable : true,
					forceSelection : true,
					blankText : '请选择',
					lazyRender : true,
					enableKeyEvents : true,
					constructor : function(c) {
						if (!c.listeners)
							c.listeners = {};

						c.listeners.keydown = function(field, e) {
							if (e.keyCode == Ext.EventObject.BACKSPACE) {
								// 退格
								(function() {
									var v = field.getRawValue();
									if (v == "") {
										var f = field
												.findParentByType("sjpz_form");
										if (f) {
											field.setValue("");
											if (f.bindStore) {
												f.fieldValueChange(field, "",
														field.getValue());
											}
										}
									}
								}).defer(200);
							}
						}

						c.listeners.render = function(cmp, eOpts) {
							if (!cmp.editable) {
								new Ext.KeyMap(cmp.el.dom, [ {
									key : Ext.EventObject.BACKSPACE,
									fn : function(key, e) {
										e.stopEvent();
									}
								} ]);
							}
						};
						c.listeners.beforequery = function(e) {
							var combo = e.combo;     
					        if(!e.forceAll){     
					            var value = e.query;     
					            combo.store.filterBy(function(record, id){     
					                var text = record.get(combo.displayField);     
					                return (text.indexOf(value)!=-1);     
					            });  
					            combo.expand();     
					            return false;     
					        }
						};
						Ext.apply(this, c);

						Gnt.ux.DictComboBox.superclass.constructor
								.call(this, c);
					},
					/*
					 * filter: function(data){ return true; },
					 */
					reloadDict : function() {
						var me = this;

						this.store.removeAll();
						if (this.dict.methodName) {
							var url = 'dict/utils/' + this.dict.methodName
									+ "?" + Ext.urlEncode(this.dict);
							// 如果是调用方法，那么每次都动态请求
							Gnt.noAsyncAjax({
								url : url,
								me : this
							}, function(json, me) {
								var data = Gnt.ux.Dict.getStoreList(me.dict,
										json);
								me.store.loadData(data);
							});
						} else {
							var list = Gnt.ux.Dict.getStoreList(this.dict);
							if (this.filter) {
								var newlist = [];
								Ext.each(list, function(data) {
									var re = me.filter(data);
									if (re == true) {
										newlist.push(data);
									}
								});
								this.store.loadData(newlist);
							} else {
								this.store.loadData(list);
							}
						}
					},
					initComponent : function() {
						if (typeof this.dict == "string") {
							this.dict = Ext.urlDecode(this.dict);
						}
						if (this.allowselfinput && this.allowselfinput == '1') {
							this.dict.allowselfinput = '1';
						}
						var clbsStore = new Ext.data.SimpleStore({
							id : 0,
							fields : [ {
								name : 'code',
								mapping : 0
							}, {
								name : 'name',
								mapping : 1
							}, {
								name : 'data',
								mapping : 2
							}],
							data : []
						});
						this.store = clbsStore;

						// 特殊字典不需要二次加载
						if (_nodict.indexOf("," + this.dict.visionType + ",") < 0
								|| _nodict.indexOf("," + this.dict.VisionType
										+ ",") < 0) {
							this.reloadDict();
						}

						Gnt.ux.DictComboBox.superclass.initComponent.call(this);
					}
				});
Ext.reg('dict_combox', Gnt.ux.DictComboBox);

// 自定义可编辑下拉框
Gnt.ux.DictComboBoxCust = Ext.extend(Gnt.ux.DictComboBox, {
	selectOnFocus : false,
	editable : true,
	forceSelection : true,
	constructor : function(c) {
		if (!c.listeners)
			c.listeners = {};

		c.listeners.select = function(combo, record, index) {
			var f = this.findParentByType("sjpz_form");
			if (f && f.changeDictCust) {
				f.changeDictCust(combo, record);
			}
		};

		c.listeners.focus = function(event) {
			var f = this.findParentByType("sjpz_form");
			if (f && f.getDictCust) {
				var list = f.getDictCust(this, this.dsname);
				if (list) {
					this.store.removeAll();
					this.store.loadData(list);
				}

				(function() {

				}).defer(100, this);
			} else {
				this.expand();
			}
		};

		Ext.apply(this, c);
		Gnt.ux.DictComboBoxCust.superclass.constructor.call(this, c);
	},
	initComponent : function(config) {
		Gnt.ux.DictComboBoxCust.superclass.initComponent.call(this);
	}
});
Ext.reg('dict_combox_cust', Gnt.ux.DictComboBoxCust);

// 检索字典
Gnt.ux.SearchComboBox = Ext
		.extend(
				Ext.form.ComboBox,
				{
					fieldLabel : '检索',
					typeAhead : false,
					hideTrigger : false,
					queryParam : 'search_code',
					displayField : 'name',
					valueField : 'code',
					minChars : 0,//修改模糊匹配最小的位数
					forceSelection : true, // 必须从搜索出来的下拉列表里面选择数据,不能自己随意输
					queryDelay : 600,
					matchFieldWidth : true,
					emptyText : "",
					tpl : '<tpl for="."><div class="x-combo-list-item">{code}{dm}\t{name}{mc}</div></tpl>',
					storeLoad : function(res, setFlag) {
						var v = this.getValue();

						this.suspendEvents();
						this.setValue(v, setFlag);
						this.resumeEvents();

						// 缓存
						for (var i = 0; i < res.length; i++) {
							Gnt.ux.Dict.saveDictLable(this.dsname,
									res[i].data[this.valueField],
									res[i].data[this.displayField]);
						}
					},
					setValue : function(v, setFlag) {
						if (v && v != "") {
							if (!this.store.getById(v)) {
								// 判断缓存是否存在
								if (Gnt.ux.Dict.existsDictLable(this.dsname, v)) {
									var label = Gnt.ux.Dict.getDictLable(
											this.dsname, v);
									var data = {};
									data[this.valueField] = v;
									data[this.displayField] = label;

									var data = {
										"totalCount" : 1,
										"success" : true,
										"list" : [ data ]
									};
									this.store.suspendEvents();
									this.store.loadData(data);
									this.store.resumeEvents();
								} else {
									// 如果后台查询没有返回数据，进行二次加载的时候，防止循环调用
									if (setFlag == "1")
										;
									else
										this.store.load({
											params : {
												search_code : v,
												optype : 'eq'
											}
										});
								}
							}
						}
						return Gnt.ux.SearchComboBox.superclass.setValue.call(
								this, v);
					},
					initComponent : function() {
						this.store = new Ext.data.JsonStore(
								{
									url : this.searchUrl,
									combox : this,
									root : 'list',
									id : this.valueField,
									totalProperty : 'totalCount',
									fields : this.fields ? this.fields
											: [ this.valueField,
													this.displayField ],
									listeners : {
										beforeload : function(store, operation,
												eOpts) {
											store.removeAll();
											store.combox.setValue("");
											if (store.combox
													&& store.combox.getPostParams) {
												var params = store.combox
														.getPostParams();
												for (name in params)
													store.baseParams[name] = params[name];
											}
											return true;
										},
										loadexception : function(mystore,
												options, response, error) {
											if (error) {
												var json = Ext
														.decode(response.responseText);
												if (json.messages)
													Ext.Msg.alert("提示",
															json.messages[0]);
												else
													Ext.Msg.alert("提示",
															error.message);
											} else {
												Ext.Msg.alert("提示",
														response.responseText);
											}
										},
										load : function(store, res) {
											if (store.combox
													&& store.combox.storeLoad) {
												// 第二次加载，防止后台没有返回数据进入死循环
												store.combox
														.storeLoad(res, "1");
											}
										}
									}
								});
						Gnt.ux.SearchComboBox.superclass.initComponent
								.call(this);
					}
				});
Ext.reg('search_combox', Gnt.ux.SearchComboBox);

// 地区树结构
Gnt.ux.TreeComboBox = Ext.extend(Gnt.ux.SearchComboBox, {
	onTriggerClick : function(event) {
		if (!this.treeUrl)
			return;

		if (this.disabled || this.readOnly)
			return;

		if (!this.tree) {
			var root = new Ext.tree.AsyncTreeNode({
				text : '全国'
			});
			// 定义树加载器，当点击leaf为false，但是又没有提供children
			// 属性的节点时，将利用该树加载器查询节点信息
			var myTreeLoader = new Ext.tree.TreeLoader({
				dataUrl : this.treeUrl,
				listeners : {
					loadexception : function(tree, node, response) {
						Ext.Msg.alert("加载失败", response.responseText);
					}
				}
			});

			myTreeLoader.on("beforeload", function(treeLoader, node) {
				// 在beforeload事件中每次动态传递点击的节点的value属性作为参数
				if (node.attributes.xzqh && node.attributes.xzqh.dm)
					this.baseParams.pid = node.attributes.xzqh.dm;
				else
					this.baseParams.pflid = "";
			}, myTreeLoader);

			var tree = new Ext.tree.TreePanel({
				autoScroll : true,
				rootVisible : false,
				field : this,
				region : 'center',
				loader : myTreeLoader,
				width : 300,
				height : 400,
				setCheck : function(node, checked) {
					var tree = node.getOwnerTree();
					var field = tree.field;
					if (checked) {
						// 如果选择，那么判断
						if (field.selMode == undefined
								|| field.selMode == 'single') {
							// 单选
							var nodes = tree.getChecked();
							for (var i = 0; i < nodes.length; i++) {
								if (nodes[i] != node) {
									// 取消之前的选择
									var ui = nodes[i].getUI();
									if (ui.isChecked()) {
										ui.toggleCheck(false);
									}
								}
							}
						}
					}
				},
				listeners : {
					click : function(node, e) {
						var tree = node.getOwnerTree();
						var ui = node.getUI();
						ui.toggleCheck(!ui.isChecked());

						tree.setCheck(node, ui.isChecked());
					},
					checkchange : function(node, checked) {
						var tree = node.getOwnerTree();
						tree.setCheck(node, checked);
					}
				}
			});

			tree.setRootNode(root);
			tree.getRootNode().expand();

			this.tree = tree;

			var treeWindow = new Ext.Window({
				title : '选择地区',
				closeAction : 'hide',
				modal : true,
				width : 420,
				height : 400,
				layout : 'fit',
				items : tree,
				buttons : [ {
					text : '确定',
					handler : function(b, e) {
						var win = b.findParentByType("window");
						var tree = win.items.get(0);
						var field = tree.field;

						var nodes = tree.getChecked();
						if (!field.allowBlank) {
							if (nodes.length == 0) {
								showInfo(field.fieldLabel + "不能为空！");
								return;
							}
						}

						var text = "", value = "";
						for (var i = 0; i < nodes.length; i++) {
							if (text != "") {
								text += ",";
								value += ",";
							}
							text += nodes[i].attributes.text;
							value += nodes[i].attributes.codevalue;
						}
						var store = field.store;
						store.removeAll();

						var data = {};
						data[field.valueField] = value;
						data[field.displayField] = text;

						var res = {
							"totalCount" : 1,
							"success" : true,
							"list" : [ data ]
						};
						store.loadData(res);
						var oldvalue = field.getValue();

						field.setValue(value);
						field.fireEvent("change", field, value, oldvalue);

						win.hide();
					}
				}, {
					text : '取消',
					handler : function(b, e) {
						var win = b.findParentByType("window");
						win.hide();
					}
				} ]
			});

			this.win = treeWindow;
		}
		this.win.show();
	}
});
Ext.reg('tree_comboBox', Gnt.ux.TreeComboBox);

function showErr(err, callback) {
	var obj = {
		msg : err,
		fn : callback ? callback : function() {
		}
	};

	(function() {
		Ext.MessageBox.show({
			title : '错误提示',
			msg : this.msg,
			buttons : Ext.MessageBox.OK,
			icon : Ext.MessageBox.ERROR,
			fn : this.fn
		});
	}).defer(100, obj);
}

// 消息显示窗口
function showInfo(msg, callback) {
	var obj = {
		msg : msg,
		fn : callback
	};

	(function() {
		Ext.MessageBox.show({
			title : '提示',
			msg : this.msg,
			buttons : Ext.MessageBox.OK,
			icon : Ext.MessageBox.INFO,
			fn : this.fn
		});
	}).defer(100, obj);
}

// 警告显示窗口
function showWarn(msg, callback) {
	var obj = {
		msg : msg,
		fn : callback
	};

	(function() {
		Ext.MessageBox.show({
			title : '警告',
			msg : this.msg,
			buttons : Ext.MessageBox.OK,
			icon : Ext.MessageBox.Warn,
			fn : this.fn
		});
	}).defer(100, obj);
}

function showInput(msg, def, callback) {
	Ext.MessageBox.prompt('提示', msg, function(btnType, value) {
		if (btnType == "ok") {
			callback(value);
		}
	}, window, 100, def);
}

function showQuestion(msg, callback) {
	var obj = {
		msg : msg,
		fn : callback
	};

	Ext.Msg.show({
		title : '确认?',
		msg : msg,
		buttons : Ext.Msg.YESNO,
		icon : Ext.Msg.QUESTION,
		fn : callback
	});
}

function showObject(obj) {
	var str = "";
	for (o in obj)
		str += "o=" + obj[o] + ";";

	showInfo(str);
}

function getQueryParam(name) {
	var regex = RegExp('[?&]' + name + '=([^&]*)');

	var match = regex.exec(location.search);
	return match && decodeURIComponent(match[1]);
}

function SetReadOnly(form, fieldname, isReadOnly) {
	var f = form.getForm().findField(fieldname);
	if (f) {
		if (isReadOnly) {
			//f.disable();
//			f.fieldClass="x-form-field x-item-readonly";
			f.addClass("x-item-readonly");
			f.el.dom.readOnly=true; 
//			f.el.dom.typeAhead=false;
//			f.el.dom.editable=false;
			f.typeAhead=false;
			f.editable=false;
			f.disabled = true
		} else {
			f.el.dom.readOnly=false; 
			f.enable();
		}
	} else {
		alert("字段" + fieldname + "没找到！")
	}
}

Gnt.date = {
	isIDCard : function(editorValue) {
		var num = editorValue;
		var len = num.length;
		var re = null;

		if (len == 15) {
			if (isNaN(num)) {
				return "输入的身份证号不是数字！";
			}
			re = new RegExp(/^(\d{6})()?(\d{2})(\d{2})(\d{2})(\d{3})$/);
		} else if (len == 18) {
			if (isNaN(num)) {
				if (isNaN(num.substr(0, 17))) {
					return "输入的身份证号不是数字！";
				}
			}
			re = new RegExp(/^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\d)$/);
		} else {
			return "输入的身份证号数字位数不对！";
		}

		var a = num.match(re);
		if (a != null) {
			if (len == 15) {
				var D = new Date("19" + a[3] + "/" + a[4] + "/" + a[5]);
				var B = D.getFullYear() == a[3] && (D.getMonth() + 1) == a[4]
						&& D.getDate() == a[5];
			} else {
				var D = new Date(a[3] + "/" + a[4] + "/" + a[5]);
				var B = D.getFullYear() == a[3] && (D.getMonth() + 1) == a[4]
						&& D.getDate() == a[5];

			}
			if (!B) {
				return "输入的身份证号 " + a[0] + " 里出生日期不对！";
			}
		}

		if (len == 18) {
			if (!this.isCheckCode(num)) {
				return "输入的身份证号与校验码不符！ ";
			}
		}
		return true;
	},
	isCheckCode : function(num) {
		var checkCodes = new Array("1", "0", "X", "9", "8", "7", "6", "5", "4",
				"3", "2");
		var mulFactors = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5,
				8, 4, 2, 1);
		var iPtr = null;

		var i = 0;

		for (i = 0; i < 17; i++) {
			iPtr = iPtr + parseInt(num.substr(i, 1)) * parseInt(mulFactors[i]);
		}
		iPtr = iPtr % 11;
		if (checkCodes[iPtr] != num.substr(17, 1)) {
			showInfo("身份证号码校验位错误！<br />应该是:" + checkCodes[iPtr] + " !");
			return false;
		}

		return true;
	},
	checkIDCardSex : function(IdCardNo, Sex) {
		var vSex = "";

		switch (IdCardNo.length) {
		case 15: {
			vSex = IdCardNo.substring(IdCardNo.length - 1);
			break;
		}
		case 18: {
			vSex = IdCardNo.substring(IdCardNo.length - 2, IdCardNo.length - 1);
			break;
		}
		default: {
			return "身份证号码格式不正确!";
		}
		}
		if ((vSex % 2) == 0)
			vSex = 2;
		else
			vSex = 1;

		if (vSex != Sex) {
			return "身份证号码与性别不匹配!"
		}

		return true;
	},
	checkIDCardBirth : function(IdCardNo, Birth) {
		var vBirth = "";

		var vDay = Birth.substring(6, 8);// 日
		var vMonth = Birth.substring(4, 6);
		var vYear = Birth.substring(0, 4);
		if (!this.isValidDate(vDay, vMonth, vYear)) {
			return "出生日期格式不正确!"
		}
		switch (IdCardNo.length) {
		case 15: {
			vBirth = "19" + IdCardNo.substring(6, 12);
			break;
		}
		case 18: {
			vBirth = IdCardNo.substring(6, 14);
			break;
		}
		default: {
			return "身份证号码长度不正确!"
		}
		}
		if (vBirth != Birth) {
			return "身份证号码与出生日期不匹配!";
		}

		return true;
	},
	isValidDate : function(day, month, year) {
		if (day.length < 1 && month.length < 1 && year.length < 1)
			return true;

		if (month < 1 || month > 12) {
			return false;
		}
		if (day < 1 || day > 31) {
			return false;
		}
		if ((month == 4 || month == 6 || month == 9 || month == 11)
				&& (day == 31)) {
			return false;
		}
		if (month == 2) {
			var leap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
			if (day > 29 || (day == 29 && !leap)) {
				return false;
			}
		}
		return true;
	},
	// 身份证校验
	validateCard : function(strIdCard, strSexCode, strBirthday) {
		tmp = this.isIDCard(strIdCard);
		if (tmp != true) {
			// 返回错误内容
			return tmp;
		}

		if (strSexCode && (strSexCode == "1" || strSexCode == "2")) {
			tmp = this.checkIDCardSex(strIdCard, strSexCode)
			if (tmp != true) {
				// 返回错误内容提示
				return tmp;
			}
		}

		if (strBirthday && strBirthday != null) {
			tmp = this.checkIDCardBirth(strIdCard, strBirthday);
			if (tmp != true) {
				// 返回错误提示
				return tmp;
			}
		}

		return true;
	},
	/* 根据出生日期算出年龄 */
	getNl : function(sfz, csrq) {
		var csrq2 = null;

		if (sfz && (sfz.length == 15 || sfz.length == 18)) {
			csrq2 = this.getBirth(sfz);
			return this.jsGetAge(csrq2);
		}

		if (csrq && csrq.length >= 8) {
			return this.jsGetAge(csrq);
		}

		return -1;
	},
	jsGetAge : function(strBirthday) {
		var returnAge;
		var birthYear = strBirthday.substring(0, 4);
		var birthMonth = strBirthday.substring(4, 6);
		var birthDay = strBirthday.substring(6, 8);

//		d = new Date();
//		var nowYear = d.getFullYear();
//		var nowMonth = d.getMonth() + 1;
//		var nowDay = d.getDate();
		var nowYear = sysDate.substr(0,4);
		var nowMonth = sysDate.substr(4,2);
		var nowDay = sysDate.substr(6,2);

		if (nowYear == birthYear) {
			returnAge = 0;// 同年 则为0岁
		} else {
			var ageDiff = nowYear - birthYear; // 年之差
			if (ageDiff > 0) {
				if (nowMonth == birthMonth) {
					var dayDiff = nowDay - birthDay;// 日之差
					if (dayDiff < 0) {
						returnAge = ageDiff - 1;
					} else {
						returnAge = ageDiff;
					}
					// alert(returnAge);
				} else {
					var monthDiff = nowMonth - birthMonth;// 月之差
					if (monthDiff < 0) {
						returnAge = ageDiff - 1;
					} else {
						returnAge = ageDiff;
					}
				}
			} else {
				returnAge = -1;// 返回-1 表示出生日期输入错误 晚于今天
			}
		}
		return returnAge;// 返回周岁年龄
	},
	getNlc : function(starDate, endDate) {
		var returnAge;
		var birthYear = starDate.substring(0, 4);
		var birthMonth = starDate.substring(4, 6);
		var birthDay = starDate.substring(6, 8);

		var nowYear = endDate.substring(0, 4);
		var nowMonth = endDate.substring(4, 6);
		var nowDay = endDate.substring(6, 8);

		if (nowYear == birthYear) {
			returnAge = 0;// 同年 则为0岁
		} else {
			var ageDiff = nowYear - birthYear; // 年之差
			if (ageDiff > 0) {
				if (nowMonth == birthMonth) {
					var dayDiff = nowDay - birthDay;// 日之差
					if (dayDiff < 0) {
						returnAge = ageDiff - 1;
					} else {
						returnAge = ageDiff;
					}
					// alert(returnAge);
				} else {
					var monthDiff = nowMonth - birthMonth;// 月之差
					if (monthDiff < 0) {
						returnAge = ageDiff - 1;
					} else {
						returnAge = ageDiff;
					}
				}
			} else {
				returnAge = -1;// 返回-1 表示出生日期输入错误 晚于今天
			}
		}
		return returnAge;// 返回周岁年龄
	},
	getBirth : function(sfz) {
		IdCardNo = sfz;
		vBirth = "";
		switch (IdCardNo.length) {
		case 15: {
			vBirth = "19" + IdCardNo.substring(6, 12);
			break;
		}
		case 18: {
			vBirth = IdCardNo.substring(6, 14);
			break;
		}
		}

		return vBirth;
	},
	getCheckCode : function(num) {

		if (num.length == 17) {

			var checkCodes = new Array("1", "0", "X", "9", "8", "7", "6", "5",
					"4", "3", "2");
			var mulFactors = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10,
					5, 8, 4, 2, 1);
			var iPtr = null;

			var i = 0;

			for (i = 0; i < 17; i++) {
				iPtr = iPtr + parseInt(num.substr(i, 1))
						* parseInt(mulFactors[i]);
			}
			iPtr = iPtr % 11;
			// alert(checkCodes[iPtr]);

			return checkCodes[iPtr];

		}

	},
	getSexCode : function(IdCardNo) {
		var vSex = "";

		switch (IdCardNo.length) {
		case 15: {
			vSex = IdCardNo.substring(IdCardNo.length - 1);
			break;
		}
		case 18: {
			vSex = IdCardNo.substring(IdCardNo.length - 2, IdCardNo.length - 1);
			break;
		}
		default: {

		}
		}
		if ((vSex % 2) == 0)
			vSex = 2;
		else
			vSex = 1;

		return vSex;
	},
	compareDate : function(checkDateStartStr, checkDateEndStr, type) {
		/**
		 * type:是否比较当前日期 sysDate来源:config.jsp
		 */
		if (type) {
			if (checkDateEndStr && checkDateEndStr.length == 8
					&& sysDate.length == 8) {
				var nowDate = new Date(sysDate.substr(0, 4), Number(sysDate
						.substr(4, 2)) - 1, sysDate.substr(6, 2));
				var checkDate = new Date(checkDateEndStr.substr(0, 4),
						Number(checkDateEndStr.substr(4, 2)) - 1,
						checkDateEndStr.substr(6, 2));
				// alert("当前日期: " + sysDate.substr(0,4) + "年 " +
				// sysDate.substr(4,2) + "月 " + sysDate.substr(6,2) + "日");
				// alert("比较日期: " + checkDateEndStr.substr(0,4) + "年 " +
				// checkDateEndStr.substr(4,2) + "月 " +
				// checkDateEndStr.substr(6,2) + "日");
				return (checkDate > nowDate);
			} else {
				return false;
			}
		} else {
			if (checkDateStartStr && checkDateEndStr
					&& checkDateStartStr.length == 8
					&& checkDateEndStr.length == 8) {
				var startDate = new Date(checkDateStartStr.substr(0, 4),
						Number(checkDateStartStr.substr(4, 2)) - 1,
						checkDateStartStr.substr(6, 2));
				var checkDate = new Date(checkDateEndStr.substr(0, 4),
						Number(checkDateEndStr.substr(4, 2)) - 1,
						checkDateEndStr.substr(6, 2));
				// alert("当前日期: " + checkDateStartStr.substr(0,4) + "年 " +
				// checkDateStartStr.substr(4,2) + "月 " +
				// checkDateStartStr.substr(6,2) + "日");
				// alert("比较日期: " + checkDateEndStr.substr(0,4) + "年 " +
				// checkDateEndStr.substr(4,2) + "月 " +
				// checkDateEndStr.substr(6,2) + "日");
				return (checkDate >= startDate);
			} else {
				return false;
			}
		}
	},
	string2Date : function(DateStr, type) {
		if (type) {
			return new Date();
		} else {
			if (DateStr) {
				if (DateStr.length == 14) {
					// 带时分秒
					var date = new Date(DateStr.substr(0, 4), Number(DateStr
							.substr(4, 2)) - 1, DateStr.substr(6, 2));
					date.setHours(DateStr.substr(8, 2), DateStr.substr(10, 2),
							DateStr.substr(12, 2));
					return date;
				} else if (DateStr.length == 8) {
					return new Date(DateStr.substr(0, 4), Number(DateStr
							.substr(4, 2)) - 1, DateStr.substr(6, 2));
				}
			}
		}
	}
}

// 制作错误校验提示
Gnt.markInvalid = function(obj, fname, ferror, form) {
	if (form) {
		// 如果不为空，需要校验字段
		var f = form.getForm().findField(fname);
		// 如果字段不存在，或者字段
		if (!f/* || f.disabled */) {// 注释掉disabled属性，只读的也验证
			return obj;
		}
	}

	if (!obj) {
		obj = {}
	}

	obj[fname] = ferror;

	return obj;
}

// 小于某值，不能填写
Gnt.markInvalidBNT = function(data, markInvalid, nl, nlz, form, field,
		fieldname) {
	var f = form.getForm().findField(field);
	// 婚姻状况
	if (f && !f.disabled) {
		if (nl <= nlz) {
			if (data[field] && data[field] != "") {
				var txt = (nlz + 1) + "周岁以及以下，【" + fieldname + "】不能填写！";
				markInvalid = Gnt.markInvalid(markInvalid, field, txt);
			}
		}
	}

	return markInvalid;
}

// VTYPE校验
Gnt.markInvalidVTYPE = function(data, markInvalid, form, pz) {
	var field = form.getForm().findField(pz.fieldname);

	// 文化程度
	if (field && !field.disabled) {
		if (field.vtype && field.vtype != "" && data[pz.fieldname]
				&& data[pz.fieldname] != "") {
			if (!Ext.form.VTypes[field.vtype](data[pz.fieldname], field)) {
				var msg = Ext.form.VTypes[field.vtype + "Text"];
				if (!markInvalid) {
					markInvalid = {};
				}
				markInvalid[pz.fieldname] = "【" + pz.displayname + "】" + msg;
			}
		}
	}

	return markInvalid;
}

// 小于某值，必须填写
Gnt.markInvalidBXTXY = function(data, markInvalid, nl, nlz, form, field,
		fieldname) {
	var f = form.getForm().findField(field);
	// 文化程度
	if (f && !f.disabled) {
		if (nl <= nlz) {
			// 超过16岁以下字段必填，文化程度
			if (!data[field] || data[field] == "") {
				var txt = "小于" + (nlz + 1) + "周岁【" + fieldname + "】必须填写！";
				markInvalid = Gnt.markInvalid(markInvalid, field, txt);
			}
		}
	}

	return markInvalid;
}

// 大于某值，必须填写
Gnt.markInvalidBXT = function(data, markInvalid, nl, nlz, form, field,
		fieldname) {
	var f = form.getForm().findField(field);
	// 文化程度
	if (f && !f.disabled) {
		if (nl >= nlz) {
			// 超过16岁以下字段必填，文化程度
			if (!data[field] || data[field] == "") {
				var txt = "超过" + nlz + "周岁【" + fieldname + "】必须填写！";
				markInvalid = Gnt.markInvalid(markInvalid, field, txt);
			}
		}
	}

	return markInvalid;
}

Gnt.validatePhone = function(val) {
	if (val) {
		// ^(130|131|132|133|134|135|136|137|138|139)\d{8}$
		var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;// 座机
		var isMob = /^(1)\d{10}$/;// 手机号码
		if (isMob.test(val) || isPhone.test(val)) {
			return true;
		} else {
			return false;
		}
	} else {
		/**
		 * 2018.10.23 修改 刁杰 值为空时不进行校验
		 */
		return true;
	}
}

// 电话号码校验
Gnt.markDHHM = function(data, markInvalid, form, field, fieldname) {
	var f = form.getForm().findField(field);
	if (f && !f.disabled) {
		if (data[field] && data[field] != "") {
			if (!Gnt.validatePhone(data[field])) {
				markInvalid = Gnt.markInvalid(markInvalid, field, "【"
						+ fieldname + "】格式不正确！");
			}
		}
	}

	return markInvalid;
}
// alert(Gnt.date.getNl("430521197306235736","20080722"));

/**
 * 基本校验规则
 */
Ext.apply(Ext.form.VTypes, {
	// 扩展验证方法，并且通过返回true|false表示验证是否通过
	Chinese : function(value, field) {
		if (value.length <= 1)
			return false;

		/*
		 * var re =
		 * /^[\u0100-\u9fa5]+([\u0100-\u9fa5]|·)*[\u0100-\u9fa5]+$/.test(value);
		 */
		var re = true;
		for (var i = 0; i < value.length; i++) {
			if (value.charCodeAt(i) > 127 || value.charCodeAt(i) == 94
					|| value.charCodeAt(i) == 46) {
				// 宽字符，或者.
			} else {
				// ascii字符
				re = false;
				break;
			}
		}

		if (re) {
			if (value.substring(0, 1) == "·"
					|| value.substring(value.length - 1, 1) == "·"
					|| value.indexOf("··") >= 0)
				re = false;
		}

		return re;
	},
	ChineseText : '请输入不少于2个汉字内容！',
	Sg : function(value, field) {
		var re = /^\d+$/.test(value);
		if (!re)
			return false;

		var num = parseInt(value);
		if (num <= 10 || num >= 300)
			return false;

		return true;
	},
	SgText : '必须输入10-300之间的身高值！',
	Sfzh : function(value, field) {
		if (value) {
			var str = Gnt.date.isIDCard(value);
			if (str == true || str == false)
				return str;
			else
				return false;
		}
		return true;
	},
	SfzhText : "身份证号码格式错误！",
	Swzmbh : function(value, field) {
		var re = /^\d+$/.test(value);
		if (!re)
			return false;

		var num = parseInt(value);
		if(Gnt.ux.getStrLength(value)>20){
			return false;
		}
//		if (num <= 99999 || num >= 0)
//			return false;

		return true;
	},
	SwzmbhText : '必须输入20位以内数字！'
	
	
	/*
							 * , Dhhm : function(value,field) { if (value) {
							 * if(!Gnt.validatePhone(value)){ return false; } }
							 * return true; }, DhhmText : "电话号码格式错误！"
							 */
});

// 调整form的配置
Gnt.toFormSjpz = function(list) {
	var map = {};

	// 一半
	var i1 = (list.length - list.length % 2) / 2;
	if (list.length % 2 != 0)
		i1++;

	var list2 = new Array();
	for (var i = 0; i < i1; i++) {
		// 偶数位置
		if (i * 2 < list.length)
			list2[i * 2] = list[i];

		list2[i * 2].tabIndex = tabIndex;
		map[tabIndex] = list2[i * 2].name;
		tabIndex++;
	}

	for (var i = i1; i < list.length; i++) {
		// 奇数位置
		var x = (i - i1) * 2 + 1;
		if (x < list.length)
			list2[x] = list[i];

		list2[x].tabIndex = tabIndex;
		map[tabIndex] = list2[x].name;
		tabIndex++;
	}

	for (var i = 0; i < list2.length; i++) {
		var index = list2[i].tabIndex + 1;
		if (map[index]) {
			list2[i].next_fieldname = map[index];
		}
	}

	return list2;
}

// 查询人员信息
Gnt.toRyxx = function(czr) {

	var param = "";
	if (czr.ryid) {
		param += "&ryid=" + czr.ryid;
	}
	if (czr.rynbid) {
		param += "&rynbid=" + czr.rynbid;
	}
	if (czr.hhnbid) {
		param += "&hhnbid=" + czr.hhnbid;
	}
	if (czr.hhid) {
		param += "&hhid=" + czr.hhid;
	}
	if (czr.mlpnbid) {
		param += "&mlpnbid=" + czr.mlpnbid;
	}

	var url = xmdz + "cx/hjjbxx/ckxx";
	if (czr.type && czr.type == "dztz") {
		/**
		 * 2018.11.09 新增 刁杰 区划调整 - 地址调整 -
		 */
		url += "?ai=1&jumpToCzrkcx=1" + param;
		parent.parent.openWorkSpace(url, "常口信息查询", "ckxx");
	} else {
		url += "?ai=2&jumpToCzrkcx=1" + param;
		if (parent && parent.openWorkSpace) {
			parent.openWorkSpace(url, "人口基本信息查询", "ckxx");
		} else {
			window.location.href = url;
		}
	}

	/*
	 * var tabs = parent.Ext.getCmp("tabs"); var p = null; //利用iframe显示页面 p =
	 * new parent.Ext.ux.IFrameComponent({ xtype:'panel', id:"ckxxcx",
	 * url:"cx/hjjbxx/ckxx?ai=2" + param, frame:false, iconCls:'otherfile',
	 * title: "人口基本信息查询", tabTip: "常口信息查询" }); //添加分页，并且设置为活动分页 tabs.add(p);
	 * 
	 * tabs.setActiveTab("ckxxcx");
	 */
}

Gnt.ux.URLDialog = Ext.extend(Ext.Window, {
	title : '浏览器',
	closeAction : 'close',
	modal : true,
	width : 500,
	height : 320,
	margins : '5',
	layout : 'fit',
	buttons : [ {
		text : '关闭',
		xtype : "button",
		minWidth : 75,
		handler : function() {
			var win = this.findParentByType("url_dialog");
			if (win.callback) {
				var re = win.callback();
				if (re == false) {
					return;
				}

				win.hide();
			} else {
				win.hide();
			}
		}
	} /*
		 * , { text:'取消', xtype:"button", minWidth:75, handler:function(){ var
		 * win = this.findParentByType("url_dialog"); win.hide(); } }
		 */
	],
	openUrl : function(config) {
		var me = this;

		(function() {
			var mgr = new Ext.Updater(me.panel.body, true);
			mgr.loadScripts = true;
			mgr.update({
				url : config.url,
				text : "正在准备内容，请等待...",
				params : config.params,
				method : 'POST'
			});
		}).defer(200);
	},
	initComponent : function() {
		var p = new Gnt.ux.IFrameComponent({
			id : new Date().getTime() + '_url_panel_',
			title : '',
			url : this.url
		});

		this.panel = p;
		this.items = [ p ];

		Gnt.ux.URLDialog.superclass.initComponent.call(this);
	}
});
Ext.reg('url_dialog', Gnt.ux.URLDialog);

Gnt.ux.URLDialog1 = Ext.extend(Ext.Window, {
	title : '浏览器',
	closeAction : 'close',
	modal : true,
	width : 500,
	height : 320,
	closable:false,
	margins : '5',
	layout : 'fit',
	buttons : [ {
		text : '保存',
		xtype : "button",
		minWidth : 75,
		handler : function() {
			var win = this.findParentByType("url_dialog1");
			if (win.callback) {
				var re = win.callback();
				if (re == false) {
					return;
				}

				win.hide();
			} else {
				win.hide();
			}
		}
	} /*
		 * , { text:'取消', xtype:"button", minWidth:75, handler:function(){ var
		 * win = this.findParentByType("url_dialog"); win.hide(); } }
		 */
	],
	openUrl : function(config) {
		var me = this;

		(function() {
			var mgr = new Ext.Updater(me.panel.body, true);
			mgr.loadScripts = true;
			mgr.update({
				url : config.url,
				text : "正在准备内容，请等待...",
				params : config.params,
				method : 'POST'
			});
		}).defer(200);
	},
	initComponent : function() {
		var p = new Gnt.ux.IFrameComponent({
			id : new Date().getTime() + '_url_panel_',
			title : '',
			url : this.url
		});

		this.panel = p;
		this.items = [ p ];

		Gnt.ux.URLDialog.superclass.initComponent.call(this);
	}
});
Ext.reg('url_dialog1', Gnt.ux.URLDialog1);
// 身份证核验按钮
Gnt.ux.SfzhyButton = Ext.extend(Ext.Button, {
	text : '核查重号',
	minWidth : 100,
	handler : function() {
		var form = this.form;
		if (!form) {
			showErr("必须使用form配置指定表单！");
			return;
		}
		var fieldname = this.fieldname;
		if (!fieldname)
			fieldname = "gmsfhm";

		var f = form.getForm().findField(fieldname);
		if (!f) {
			showErr("没有找到" + fieldname + "输入域！");
			return;
		}

		var lab = form.fieldMap[fieldname];
		var bh = f.getValue();
		if (!bh || bh == "") {
			showErr(lab + "不能为空！", function() {
				f.focus(true, 100);
			});

			return;
		}

		Gnt.ux.Dict.getKzcs('10022', function(config, user, kzdata) {
			var url = config.bz;
			if (url.indexOf("?") < 0)
				url += "?";

			var url2 = url + "&sfzh=" + bh + "&dlyhxm=" + user.glyxm
					+ "&dlmjdlm=" + user.yhdlm + "&dlmjsfzh=" + user.sfzh
					+ "&dlmjuid=" + user.yhid + "&dlmdw=" + user.dwdm;
			url += "&sfzh=" + bh + "&dlyhxm=" + user.glyxm + "&optype=count";

			// alert(url2);
			Gnt.submit({
				url : url
			}, "yw/common/executeRmoterJSON", "操作正在执行中，请稍后...", function(
					jsonData, params) {
				if (jsonData.list && jsonData.list.length > 0) {
					var str = jsonData.list[0];
					var data = Ext.decode(str);
					if (data.success) {
						if (parseInt(data.count) > 0) {
							// 身份证重复，提交重复信息
							var w = new Gnt.ux.URLDialog({
								title : '重号核验',
								width : 900,
								height : 500,
								url : url2
							});
							w.show();
							showInfo("身份证号码重复，请录入重复信息！");
						} else {
							showInfo("身份证无重复！");
						}
					} else {
						showErr(data.message);
					}
				}
			}, function(jsonData, params) {
				if (jsonData && jsonData.message) {
					showErr(jsonData.message);
				} else {
					showErr("核验重号失败！");
				}
			});
		});
	},
	constructor : function(c) {
		Ext.apply(this, c);
		Gnt.ux.SfzhyButton.superclass.constructor.call(this, c);
	},
	initComponent : function(config) {
		Gnt.ux.SfzhyButton.superclass.initComponent.call(this);
	}
});
Ext.reg('SfzhyButton', Gnt.ux.SfzhyButton);

// 采集档案
Gnt.ux.CjdaButton = Ext.extend(Ext.Button, {
	text : '采集档案',
	minWidth : 100,
	kzlb : '10001',
	// ywcode：
	// 迁入业务：105；迁入业务：101；迁出业务：106；死亡业务：102；住址变动：103；
	// 变更更正：107；户别变更：110；户籍补录：108；户籍删除：109；迁入审批：105
	handler : function() {
		var me = this;
		var kzlb = me.kzlb;
		if (!this.form) {
			showInfo("必须提供form参数指定业务表单，以获取身份证，准迁证，迁入地等信息！");
			return null;
		}
		var data = {};
		if (this.form.bindViewGrid || this.form.bindStore) {
			var store = null;
			if (this.form.bindStore) {
				store = this.form.bindStore;
			} else {
				store = this.form.bindViewGrid.store;
			}

			// 如果绑定了grid
			var pkfield = this.form.getForm().findField(
					store.pkname ? store.pkname : store.id);
			var pkvalue = pkfield.getValue();
			var re = store.getById(pkvalue);
			if (re) {
				data = re.data;
			}
		} else {
			data = this.form.getForm().getValues();
		}
		Gnt.ux.Dict.getKzcs(kzlb, function(config, user, kzdata) {
			var url = config.bz;
			if (url.indexOf("?") < 0)
				url += "?";
			else
				url += "&";
			var ryid = data.ryid ? data.ryid : "";
			var gmsfzh = data.gmsfhm ? data.gmsfhm : "";
			var id = me.cjhjywid ? me.cjhjywid : "";
			var sbrsfzh = me.sbrgmsfhm ? me.sbrgmsfhm : "";
			var s_spywid = me.spywid ? me.spywid : "";
			var ywcode = me.ywcode ? me.ywcode : "";

			var hzywid = getQueryParam("hzywid");

			url += 'spywid=' + s_spywid + '&id=' + id + '&ywcode=' + ywcode
					+ '&ryid=' + ryid + '&sfz=' + gmsfzh + '&sbrsfzh='
					+ sbrsfzh + '&dlmj=' + user.yhdlm + '&yhsfzh='
					+ user.gmsfhm;
			if (hzywid && hzywid != "") {
				url += "&hzywid=" + hzywid;
			}

			window.open(url);
		});
	},
	constructor : function(c) {
		Ext.apply(this, c);
		Gnt.ux.CjdaButton.superclass.constructor.call(this, c);
	},
	initComponent : function(config) {
		Gnt.ux.CjdaButton.superclass.initComponent.call(this);
	}
});
Ext.reg('CjdaButton', Gnt.ux.CjdaButton);

// 查阅档案
Gnt.ux.CydaButton = Ext.extend(Gnt.ux.CjdaButton, {
	text : '查阅档案',
	kzlb : '10002',
	minWidth : 100
});
Ext.reg('CydaButton', Gnt.ux.CydaButton);

// 通用URL对话框按钮
Gnt.ux.UrlDialogButton = Ext.extend(Ext.Button, {
	text : '按钮文字',
	minWidth : 100,
	// ywcode：
	// 迁入业务：105；迁入业务：101；迁出业务：106；死亡业务：102；住址变动：103；
	// 变更更正：107；户别变更：110；户籍补录：108；户籍删除：109；迁入审批：105
	handler : function() {
		var me = this;
		var kzlb = me.kzlb;
		if (!kzlb || kzlb == "") {
			showInfo("必须提供kzlb参数！");
			return;
		}

		if (me.beforeCall) {
			var re = me.beforeCall();
			if (re && re == false) {
				return;
			}
		}

		// 回调
		var params = null;
		if (me.getParams) {
			params = me.getParams();
			if (params == null) {
				// 如果参数回调返回空，那么表示取消下一步操作
				return;
			}
		} else {
			params = {};
		}

		Gnt.ux.Dict.getKzcs(kzlb, function(config, user, kzdata) {
			var url = config.bz;
			if (url.indexOf("?") < 0)
				url += "?";

			// 注意，需要进行2次编码，解决GET乱码问题，后台获取参数，使用 java.net.URLDecoder.decode(qyzbh,
			// "UTF-8");
			for (o in params) {
				if (params[o] && params[o] != "") {
					var val = params[o];
					if (val.indexOf("user.") == 0) {
						val = user[val.substring(5)];
						url += '&' + o + "=" + encodeURI(encodeURI(val))
								+ "&version=2";
					} else {
						url += '&' + o + "=" + encodeURI(encodeURI(params[o]))
								+ "&version=2";
					}
				}
			}

			var w = new Gnt.ux.URLDialog({
				title : (me.title ? me.title : me.text),
				width : 900,
				height : 500,
				url : url
			});
			w.show();

			if (me.callback) {
				me.callback();
			}
		});
	},
	constructor : function(c) {
		Ext.apply(this, c);
		Gnt.ux.UrlDialogButton.superclass.constructor.call(this, c);
	},
	initComponent : function(config) {
		Gnt.ux.UrlDialogButton.superclass.initComponent.call(this);
	}
});

Ext.reg('UrlDialogButton', Gnt.ux.UrlDialogButton);

// 迁移证
Gnt.ux.QyzButton = Ext.extend(Gnt.ux.UrlDialogButton, {
	text : '迁移证核查',
	kzlb : '10012',
	minWidth : 100,
	getParams : function() {
		if (!this.form) {
			showInfo("必须提供form参数指定业务表单，以获取身份证，迁移证，迁出地等信息！");
			return null;
		}

		var data = {};
		if (this.form.bindViewGrid || this.form.bindStore) {
			var store = null;
			if (this.form.bindStore) {
				store = this.form.bindStore;
			} else {
				store = this.form.bindViewGrid.store;
			}
			// 如果绑定了grid
			var pkfield = this.form.getForm().findField(
					store.pkname ? store.pkname : store.id);
			var pkvalue = pkfield.getValue();
			var re = store.getById(pkvalue);
			if (re) {
				data = re.data;
			}
		} else {
			data = this.form.getForm().getValues();
		}

		if (!data.gmsfhm || data.gmsfhm == "") {
			showInfo("公民身份号码不能为空！");
			return null;
		}
		if (!data.qyzbh || data.qyzbh == "") {
			showInfo("迁移证编号不能为空！");
			return null;
		}

		return {
			gmsfzh : data.gmsfhm,
			qyzbh : data.qyzbh,
			sjgsdwdm : data.qcdssxq
		};
	}
});
Ext.reg('QyzButton', Gnt.ux.QyzButton);

// 查阅档案
Gnt.ux.ZqzButton = Ext.extend(Gnt.ux.UrlDialogButton, {
	text : '准迁证核查',
	kzlb : '10011',
	minWidth : 100,
	getParams : function() {
		if (!this.form) {
			showInfo("必须提供form参数指定业务表单，以获取身份证，准迁证，迁入地等信息！");
			return null;
		}

		var data = {};
		if (this.form.bindViewGrid || this.form.bindStore) {
			var store = null;
			if (this.form.bindStore) {
				store = this.form.bindStore;
			} else {
				store = this.form.bindViewGrid.store;
			}

			// 如果绑定了grid
			var pkfield = this.form.getForm().findField(
					store.pkname ? store.pkname : store.id);
			var pkvalue = pkfield.getValue();
			var re = store.getById(pkvalue);
			if (re) {
				data = re.data;
			}
		} else {
			data = this.form.getForm().getValues();
		}

		if (!data.gmsfhm || data.gmsfhm == "") {
			// 迁出业务，身份证号码为空
			// showInfo("公民身份号码不能为空！");
			// return null;
			/**
			 * 2018.08.18 新增 刁杰 以迁出注销为例 bindStore: qcdjStore,
			 * //绑定bindViewGrid，当bindViewGrid点击的时候，触发记录移动 bindViewGrid: hcyGrid,
			 * 业务数据集与选择的不是一个数据集
			 */
			var store = null;
			var grid = this.form.bindViewGrid;
			if (grid) {
				store = grid.store;
			} else {
				grid = this.form.bindViewGridHCY;
				if (grid) {
					store = grid.store;
				}
			}
			if (store) {
				// 如果绑定了grid
				var pkfield = this.form.getForm().findField(
						store.pkname ? store.pkname : store.id);
				var pkvalue = pkfield.getValue();
				var re = store.getById(pkvalue);
				if (re) {
					data.gmsfhm = re.data.gmsfhm;
				}
			} else {
				showInfo("公民身份号码不能为空！");
				return null;
			}
		}
		if ((this.form.pzlb=='10024'&&data.qclb&&data.qclb.substring(0,3) != "048")&&(!data.zqzbh || data.zqzbh == "")) {
			showInfo("准迁证编号不能为空！");
			return null;
		}

		var returnData = {
			gmsfzh : data.gmsfhm,
			zqzbh : data.zqzbh,
			/**
			 * 2018.10.26 迁出注销
			 */
			sjgsdwdm : data.qwdssxq
		// 返回特殊格式
		};

		/**
		 * 迁入
		 */
		if (this.form.pzlb == "10023") {
			returnData.sjgsdwdm = user.dwCode;
		}

		return returnData;
	}
});
Ext.reg('ZqzButton', Gnt.ux.ZqzButton);

new Ext.KeyMap(Ext.getBody().dom, [ {
	key : Ext.EventObject.BACKSPACE,
	fn : function(key, e) {
		var obj = e.target || e.srcElement;
		if (obj.nodeName == 'INPUT' || obj.nodeName == 'TEXTAREA'
				|| obj.nodeName == 'SELECT') {
			return;
		}
		e.stopEvent();
	}
} ]);

Gnt.photo = {};
Gnt.photo.cur_zp_res = null;
Gnt.photo.tmp = {};
Gnt.photo.can = true;

// 人像比对地址
Gnt.photo.rxbd_url = "";
// 获取照片地址
Gnt.photo.queryxp_url = "";

Gnt.photo.setPhoto = function(res, canEdit, ydflag) {
	if (Gnt.photo.can == false)
		return;

	if (Gnt.photo.rxbd_url == "" || Gnt.photo.queryxp_url == "") {
		Gnt.ux.Dict.getKzcs("10013,10010", function(data, user, kzdata) {
			if (kzdata['10010'])
				Gnt.photo.rxbd_url = kzdata['10010'].bz;

			if (kzdata['10013']) {
				Gnt.photo.queryxp_url = kzdata['10013'].bz;
				if (Gnt.photo.queryxp_url.indexOf("?") < 0) {
					Gnt.photo.queryxp_url += "?";
				} else {
					Gnt.photo.queryxp_url += "&";
				}
			}
		});
	}

	if (!canEdit)
		canEdit = false;

	var zpcjImgBtn = Ext.getCmp("zpcjImgBtn");
	var zpscImgBtn = Ext.getCmp("zpscImgBtn");
	var zphqImgBtn = Ext.getCmp("zphqImgBtn");
	if (canEdit) {
		if (zpcjImgBtn)
			zpcjImgBtn.enable();
		if (zpscImgBtn)
			zpscImgBtn.enable();
		if (zphqImgBtn)
			zphqImgBtn.enable();
	} else {
		if (zpcjImgBtn)
			zpcjImgBtn.disable();
		if (zpscImgBtn)
			zpscImgBtn.disable();
		if (zphqImgBtn)
			zphqImgBtn.disable();
	}

	var dom = document.getElementById("ImageCtrl");
	if (!dom) {
		var dom = document.getElementById("_PHOTO_ID");
		if (dom) {
			// 如果DOM树存在特殊标记
			var html = '<OBJECT ID="ImageCtrl" WIDTH=130 HEIGHT=160 '
					+ ' classid="CLSID:A4E862CA-1750-453B-A2FE-97006D5246D4" '
					+ ' codebase="js/lodop/ImageCtl.CAB#version=1,0,0,13">'// 测试注释自动下载
					+ '<PARAM NAME="_Version" VALUE="65536">'
					+ '<PARAM NAME="_ExtentX" VALUE="12806">'
					+ '<PARAM NAME="_ExtentY" VALUE="1747">'
					+ '<PARAM NAME="_StockProps" VALUE="0">' + '</OBJECT>';
			dom.innerHTML = html;

			// if(!ImageCtrl ||
			// !ImageCtrl.RegisterFunction(Gnt.photo.changeImageCallback)){
			if (ImageCtrl) {
				try {
					ImageCtrl.RegisterFunction(Gnt.photo.changeImageCallback);
					// 注册人像比对回调
					ImageCtrl.RegisterCompareFunction(compareFunc);
				} catch (err) {
					// 控件不对
					html = ' <a href="zptest" target="_blank">下载照片控件</a>';
					dom.innerHTML = html;
					Gnt.photo.can = false;
					return;
				}
			} else {
				// 控件不对
				html = ' <a href="zptest" target="_blank">下载照片控件</a>';
				dom.innerHTML = html;
				Gnt.photo.can = false;
				return;
			}

		}else{
			return;
		}
	}

	Gnt.photo.cur_zp_res = res;
	if (!ImageCtrl) {
		return;
	}

	if (!res) {
		ImageCtrl.SetImageData("");
		return;
	}

	if (res.data && res.data.zp) {
		ImageCtrl.SetImageData(res.data.zp);
	} else if (res && res.data && res.data.zpid && res.data.zpid > 0 && ydflag) {
		// 缓存照片
		// if(Gnt.photo.tmp[res.data.zpid]){
		// ImageCtrl.SetImageData(Gnt.photo.tmp[res.data.zpid]);
		// return;
		// }

		// ajax加载照片
		// 先清空
		ImageCtrl.SetImageData("");
		Gnt.submit({
			zpid : res.data.zpid
		}, "yw/common/getYdZp", "获取照片，请稍后...", function(jsonData, params) {
			if (jsonData && jsonData.list && jsonData.list.length > 0) {
				var obj = jsonData.list[0];
				if (obj.zpstr) {
					ImageCtrl.SetImageData(obj.zpstr);
					Gnt.photo.temp = obj.zpstr;
					// 缓存
					// Gnt.photo.tmp[res.data.zpid] = obj.zpstr;
				}
			}
		}, function() {

		}, false);
	} else if (res && res.data && res.data.zpid && res.data.zpid > 0) {
		// 缓存照片
		// if(Gnt.photo.tmp[res.data.zpid]){
		// ImageCtrl.SetImageData(Gnt.photo.tmp[res.data.zpid]);
		// return;
		// }

		// ajax加载照片
		// 先清空
		ImageCtrl.SetImageData("");
		Gnt.submit({
			zpid : res.data.zpid
		}, "yw/common/getZp", "获取照片，请稍后...", function(jsonData, params) {
			if (jsonData && jsonData.list && jsonData.list.length > 0) {
				var obj = jsonData.list[0];
				if (obj.zpstr) {
					ImageCtrl.SetImageData(obj.zpstr);

					// 缓存
					// Gnt.photo.tmp[res.data.zpid] = obj.zpstr;
				}
			}
		}, function() {

		}, false);
	} else {
		if (ImageCtrl) {
			ImageCtrl.SetImageData("");
		} else {
			alert("照片控件无法初始化！");
		}
	}
}

// 点击人像比对，POST数据到比对地址
function compareFunc(compare_base64_data) {
	var temp_form = document.createElement("form");
	temp_form.action = Gnt.photo.rxbd_url;
	temp_form.target = "_blank";
	temp_form.method = "post";
	temp_form.style.display = "none";
	var PARAMS = {
		zp : compare_base64_data,
		x : '1',
		y : '2'
	};
	for ( var x in PARAMS) {
		var opt = document.createElement("textarea");
		opt.name = x;
		opt.value = PARAMS[x];
		temp_form.appendChild(opt);
	}
	document.body.appendChild(temp_form);
	temp_form.submit();
	document.body.removeChild(temp_form);
}

// 业务需要覆盖此方法
Gnt.photo.changeImageCallback = function(base64_img) {
	if (Gnt.photo.cur_zp_res) {
		Gnt.photo.cur_zp_res.set("zp", base64_img);
	} else {
		Gnt.photo.temp = base64_img;
	}

	if (!base64_img || base64_img == "") {
		if (ImageCtrl)
			ImageCtrl.SetImageData("");
	}
}

Gnt.photo.OpenEdit = function() {
	if (ImageCtrl) {
		if (Gnt.photo.rxbd_url != "" && Gnt.photo.queryxp_url != "") {
			
			var sfz = null;
			if (Gnt.photo.cur_zp_res && Gnt.photo.cur_zp_res.data
					&& Gnt.photo.cur_zp_res.data.gmsfhm) {
				sfz = Gnt.photo.cur_zp_res.data.gmsfhm;
			}

			if (sfz && sfz.length >= 15) {
				var url = Gnt.photo.queryxp_url + "version=2&sfzh=" + sfz;

				ImageCtrl.SetRemotePhotoUrl(url);
			} else {
				// 无身份证，不允许获取照片
				ImageCtrl.SetRemotePhotoUrl("");
			}
		} else {
			alert("没有找到人像比对地址或者查询照片地址设置！");
		}

		ImageCtrl.ImageCollect();
	}
}
Gnt.photo.Huoqu = function() {
	if (ImageCtrl) {
		if (Gnt.photo.queryxp_url != "") {
			var sfz = null;
			if (Gnt.photo.cur_zp_res && Gnt.photo.cur_zp_res.data
					&& Gnt.photo.cur_zp_res.data.gmsfhm) {
				sfz = Gnt.photo.cur_zp_res.data.gmsfhm;
			}
			if (sfz && sfz.length >= 15) {
				var url = Gnt.photo.queryxp_url + "version=2&sfzh=" + sfz;
//				Gnt.noAsyncAjax({
//					url : url
//				}, function(json) {
//					su = true;
//					if (json.list && json.list.length > 0) {
//						var data = json.list[0];
//						var firstIndex=data.indexOf("<XP>");
//						var secondIndex=data.indexOf("</XP>");
//						var zpBase64 = data.substring(start+4,stop);
//						ImageCtrl.SetImageData(zpBase64);
//					}
//				});
				Gnt.submit({
					url : url
				}, "yw/common/executeRmoterJSON", "操作正在执行中，请稍后...", function(
						jsonData, params) {
					if (jsonData.list && jsonData.list.length > 0) {
//						var data = jsonData.list[0];
//						var firstIndex=data.indexOf("<XP>");
//						var secondIndex=data.indexOf("</XP>");
//						var zpBase64 = data.substring(start+4,stop);
//						ImageCtrl.SetImageData(zpBase64);
						var str = jsonData.list[0];
						if(str){
							ImageCtrl.SetImageData(str);
							Gnt.photo.cur_zp_res.set("zp", str);
						}else{
							showErr("没有照片数据");
						}
//						var data = Ext.decode(str);
//						if (data.success) {
//							if (parseInt(data.count) > 0) {
//								ImageCtrl.SetImageData(data.xp);
//							} 
//						} else {
//							showErr(data.message);
//						}
					}
				}, function(jsonData, params) {
					if (jsonData && jsonData.message) {
						showErr(jsonData.message);
					} else {
						showErr("没有找到查询照片！");
					}
				});
				
			} else {
				// 无身份证，不允许获取照片
				alert("无身份证，不允许获取照片!");
			}
		} else {
			alert("没有找到查询照片地址设置！");
		}
	}
}
var pinyin = (function() {
	var Pinyin = function(ops) {
		this.initialize(ops);
	},

	options = {
		checkPolyphone : false,
		charcase : 'default'
	};

	Pinyin.fn = Pinyin.prototype = {
		init : function(ops) {
			this.options = extend(options, ops);
		},

		initialize : function(ops) {
			this.init(ops);
			this.char_dict = "YDYQSXMWZSSXJBYMGCCZQPSSQBYCDSCDQLDYLYBSSJGYZZJJFKCCLZDHWDWZJLJPFYYNWJJTMYHZWZHFLZPPQHGSCYYYNJQYXXGJHHSDSJNKKTMOMLCRXYPSNQSECCQZGGLLYJLMYZZSECYKYYHQWJSSGGYXYZYJWWKDJHYCHMYXJTLXJYQBYXZLDWRDJRWYSRLDZJPCBZJJBRCFTLECZSTZFXXZHTRQHYBDLYCZSSYMMRFMYQZPWWJJYFCRWFDFZQPYDDWYXKYJAWJFFXYPSFTZYHHYZYSWCJYXSCLCXXWZZXNBGNNXBXLZSZSBSGPYSYZDHMDZBQBZCWDZZYYTZHBTSYYBZGNTNXQYWQSKBPHHLXGYBFMJEBJHHGQTJCYSXSTKZHLYCKGLYSMZXYALMELDCCXGZYRJXSDLTYZCQKCNNJWHJTZZCQLJSTSTBNXBTYXCEQXGKWJYFLZQLYHYXSPSFXLMPBYSXXXYDJCZYLLLSJXFHJXPJBTFFYABYXBHZZBJYZLWLCZGGBTSSMDTJZXPTHYQTGLJSCQFZKJZJQNLZWLSLHDZBWJNCJZYZSQQYCQYRZCJJWYBRTWPYFTWEXCSKDZCTBZHYZZYYJXZCFFZZMJYXXSDZZOTTBZLQWFCKSZSXFYRLNYJMBDTHJXSQQCCSBXYYTSYFBXDZTGBCNSLCYZZPSAZYZZSCJCSHZQYDXLBPJLLMQXTYDZXSQJTZPXLCGLQTZWJBHCTSYJSFXYEJJTLBGXSXJMYJQQPFZASYJNTYDJXKJCDJSZCBARTDCLYJQMWNQNCLLLKBYBZZSYHQQLTWLCCXTXLLZNTYLNEWYZYXCZXXGRKRMTCNDNJTSYYSSDQDGHSDBJGHRWRQLYBGLXHLGTGXBQJDZPYJSJYJCTMRNYMGRZJCZGJMZMGXMPRYXKJNYMSGMZJYMKMFXMLDTGFBHCJHKYLPFMDXLQJJSMTQGZSJLQDLDGJYCALCMZCSDJLLNXDJFFFFJCZFMZFFPFKHKGDPSXKTACJDHHZDDCRRCFQYJKQCCWJDXHWJLYLLZGCFCQDSMLZPBJJPLSBCJGGDCKKDEZSQCCKJGCGKDJTJDLZYCXKLQSCGJCLTFPCQCZGWPJDQYZJJBYJHSJDZWGFSJGZKQCCZLLPSPKJGQJHZZLJPLGJGJJTHJJYJZCZMLZLYQBGJWMLJKXZDZNJQSYZMLJLLJKYWXMKJLHSKJGBMCLYYMKXJQLBMLLKMDXXKWYXYSLMLPSJQQJQXYXFJTJDXMXXLLCXQBSYJBGWYMBGGBCYXPJYGPEPFGDJGBHBNSQJYZJKJKHXQFGQZKFHYGKHDKLLSDJQXPQYKYBNQSXQNSZSWHBSXWHXWBZZXDMNSJBSBKBBZKLYLXGWXDRWYQZMYWSJQLCJXXJXKJEQXSCYETLZHLYYYSDZPAQYZCMTLSHTZCFYZYXYLJSDCJQAGYSLCQLYYYSHMRQQKLDXZSCSSSYDYCJYSFSJBFRSSZQSBXXPXJYSDRCKGJLGDKZJZBDKTCSYQPYHSTCLDJDHMXMCGXYZHJDDTMHLTXZXYLYMOHYJCLTYFBQQXPFBDFHHTKSQHZYYWCNXXCRWHOWGYJLEGWDQCWGFJYCSNTMYTOLBYGWQWESJPWNMLRYDZSZTXYQPZGCWXHNGPYXSHMYQJXZTDPPBFYHZHTJYFDZWKGKZBLDNTSXHQEEGZZYLZMMZYJZGXZXKHKSTXNXXWYLYAPSTHXDWHZYMPXAGKYDXBHNHXKDPJNMYHYLPMGOCSLNZHKXXLPZZLBMLSFBHHGYGYYGGBHSCYAQTYWLXTZQCEZYDQDQMMHTKLLSZHLSJZWFYHQSWSCWLQAZYNYTLSXTHAZNKZZSZZLAXXZWWCTGQQTDDYZTCCHYQZFLXPSLZYGPZSZNGLNDQTBDLXGTCTAJDKYWNSYZLJHHZZCWNYYZYWMHYCHHYXHJKZWSXHZYXLYSKQYSPSLYZWMYPPKBYGLKZHTYXAXQSYSHXASMCHKDSCRSWJPWXSGZJLWWSCHSJHSQNHCSEGNDAQTBAALZZMSSTDQJCJKTSCJAXPLGGXHHGXXZCXPDMMHLDGTYBYSJMXHMRCPXXJZCKZXSHMLQXXTTHXWZFKHCCZDYTCJYXQHLXDHYPJQXYLSYYDZOZJNYXQEZYSQYAYXWYPDGXDDXSPPYZNDLTWRHXYDXZZJHTCXMCZLHPYYYYMHZLLHNXMYLLLMDCPPXHMXDKYCYRDLTXJCHHZZXZLCCLYLNZSHZJZZLNNRLWHYQSNJHXYNTTTKYJPYCHHYEGKCTTWLGQRLGGTGTYGYHPYHYLQYQGCWYQKPYYYTTTTLHYHLLTYTTSPLKYZXGZWGPYDSSZZDQXSKCQNMJJZZBXYQMJRTFFBTKHZKBXLJJKDXJTLBWFZPPTKQTZTGPDGNTPJYFALQMKGXBDCLZFHZCLLLLADPMXDJHLCCLGYHDZFGYDDGCYYFGYDXKSSEBDHYKDKDKHNAXXYBPBYYHXZQGAFFQYJXDMLJCSQZLLPCHBSXGJYNDYBYQSPZWJLZKSDDTACTBXZDYZYPJZQSJNKKTKNJDJGYYPGTLFYQKASDNTCYHBLWDZHBBYDWJRYGKZYHEYYFJMSDTYFZJJHGCXPLXHLDWXXJKYTCYKSSSMTWCTTQZLPBSZDZWZXGZAGYKTYWXLHLSPBCLLOQMMZSSLCMBJCSZZKYDCZJGQQDSMCYTZQQLWZQZXSSFPTTFQMDDZDSHDTDWFHTDYZJYQJQKYPBDJYYXTLJHDRQXXXHAYDHRJLKLYTWHLLRLLRCXYLBWSRSZZSYMKZZHHKYHXKSMDSYDYCJPBZBSQLFCXXXNXKXWYWSDZYQOGGQMMYHCDZTTFJYYBGSTTTYBYKJDHKYXBELHTYPJQNFXFDYKZHQKZBYJTZBXHFDXKDASWTAWAJLDYJSFHBLDNNTNQJTJNCHXFJSRFWHZFMDRYJYJWZPDJKZYJYMPCYZNYNXFBYTFYFWYGDBNZZZDNYTXZEMMQBSQEHXFZMBMFLZZSRXYMJGSXWZJSPRYDJSJGXHJJGLJJYNZZJXHGXKYMLPYYYCXYTWQZSWHWLYRJLPXSLSXMFSWWKLCTNXNYNPSJSZHDZEPTXMYYWXYYSYWLXJQZQXZDCLEEELMCPJPCLWBXSQHFWWTFFJTNQJHJQDXHWLBYZNFJLALKYYJLDXHHYCSTYYWNRJYXYWTRMDRQHWQCMFJDYZMHMYYXJWMYZQZXTLMRSPWWCHAQBXYGZYPXYYRRCLMPYMGKSJSZYSRMYJSNXTPLNBAPPYPYLXYYZKYNLDZYJZCZNNLMZHHARQMPGWQTZMXXMLLHGDZXYHXKYXYCJMFFYYHJFSBSSQLXXNDYCANNMTCJCYPRRNYTYQNYYMBMSXNDLYLYSLJRLXYSXQMLLYZLZJJJKYZZCSFBZXXMSTBJGNXYZHLXNMCWSCYZYFZLXBRNNNYLBNRTGZQYSATSWRYHYJZMZDHZGZDWYBSSCSKXSYHYTXXGCQGXZZSHYXJSCRHMKKBXCZJYJYMKQHZJFNBHMQHYSNJNZYBKNQMCLGQHWLZNZSWXKHLJHYYBQLBFCDSXDLDSPFZPSKJYZWZXZDDXJSMMEGJSCSSMGCLXXKYYYLNYPWWWGYDKZJGGGZGGSYCKNJWNJPCXBJJTQTJWDSSPJXZXNZXUMELPXFSXTLLXCLJXJJLJZXCTPSWXLYDHLYQRWHSYCSQYYBYAYWJJJQFWQCQQCJQGXALDBZZYJGKGXPLTZYFXJLTPADKYQHPMATLCPDCKBMTXYBHKLENXDLEEGQDYMSAWHZMLJTWYGXLYQZLJEEYYBQQFFNLYXRDSCTGJGXYYNKLLYQKCCTLHJLQMKKZGCYYGLLLJDZGYDHZWXPYSJBZKDZGYZZHYWYFQYTYZSZYEZZLYMHJJHTSMQWYZLKYYWZCSRKQYTLTDXWCTYJKLWSQZWBDCQYNCJSRSZJLKCDCDTLZZZACQQZZDDXYPLXZBQJYLZLLLQDDZQJYJYJZYXNYYYNYJXKXDAZWYRDLJYYYRJLXLLDYXJCYWYWNQCCLDDNYYYNYCKCZHXXCCLGZQJGKWPPCQQJYSBZZXYJSQPXJPZBSBDSFNSFPZXHDWZTDWPPTFLZZBZDMYYPQJRSDZSQZSQXBDGCPZSWDWCSQZGMDHZXMWWFYBPDGPHTMJTHZSMMBGZMBZJCFZWFZBBZMQCFMBDMCJXLGPNJBBXGYHYYJGPTZGZMQBQTCGYXJXLWZKYDPDYMGCFTPFXYZTZXDZXTGKMTYBBCLBJASKYTSSQYYMSZXFJEWLXLLSZBQJJJAKLYLXLYCCTSXMCWFKKKBSXLLLLJYXTYLTJYYTDPJHNHNNKBYQNFQYYZBYYESSESSGDYHFHWTCJBSDZZTFDMXHCNJZYMQWSRYJDZJQPDQBBSTJGGFBKJBXTGQHNGWJXJGDLLTHZHHYYYYYYSXWTYYYCCBDBPYPZYCCZYJPZYWCBDLFWZCWJDXXHYHLHWZZXJTCZLCDPXUJCZZZLYXJJTXPHFXWPYWXZPTDZZBDZCYHJHMLXBQXSBYLRDTGJRRCTTTHYTCZWMXFYTWWZCWJWXJYWCSKYBZSCCTZQNHXNWXXKHKFHTSWOCCJYBCMPZZYKBNNZPBZHHZDLSYDDYTYFJPXYNGFXBYQXCBHXCPSXTYZDMKYSNXSXLHKMZXLYHDHKWHXXSSKQYHHCJYXGLHZXCSNHEKDTGZXQYPKDHEXTYKCNYMYYYPKQYYYKXZLTHJQTBYQHXBMYHSQCKWWYLLHCYYLNNEQXQWMCFBDCCMLJGGXDQKTLXKGNQCDGZJWYJJLYHHQTTTNWCHMXCXWHWSZJYDJCCDBQCDGDNYXZTHCQRXCBHZTQCBXWGQWYYBXHMBYMYQTYEXMQKYAQYRGYZSLFYKKQHYSSQYSHJGJCNXKZYCXSBXYXHYYLSTYCXQTHYSMGSCPMMGCCCCCMTZTASMGQZJHKLOSQYLSWTMXSYQKDZLJQQYPLSYCZTCQQPBBQJZCLPKHQZYYXXDTDDTSJCXFFLLCHQXMJLWCJCXTSPYCXNDTJSHJWXDQQJSKXYAMYLSJHMLALYKXCYYDMNMDQMXMCZNNCYBZKKYFLMCHCMLHXRCJJHSYLNMTJZGZGYWJXSRXCWJGJQHQZDQJDCJJZKJKGDZQGJJYJYLXZXXCDQHHHEYTMHLFSBDJSYYSHFYSTCZQLPBDRFRZTZYKYWHSZYQKWDQZRKMSYNBCRXQBJYFAZPZZEDZCJYWBCJWHYJBQSZYWRYSZPTDKZPFPBNZTKLQYHBBZPNPPTYZZYBQNYDCPJMMCYCQMCYFZZDCMNLFPBPLNGQJTBTTNJZPZBBZNJKLJQYLNBZQHKSJZNGGQSZZKYXSHPZSNBCGZKDDZQANZHJKDRTLZLSWJLJZLYWTJNDJZJHXYAYNCBGTZCSSQMNJPJYTYSWXZFKWJQTKHTZPLBHSNJZSYZBWZZZZLSYLSBJHDWWQPSLMMFBJDWAQYZTCJTBNNWZXQXCDSLQGDSDPDZHJTQQPSWLYYJZLGYXYZLCTCBJTKTYCZJTQKBSJLGMGZDMCSGPYNJZYQYYKNXRPWSZXMTNCSZZYXYBYHYZAXYWQCJTLLCKJJTJHGDXDXYQYZZBYWDLWQCGLZGJGQRQZCZSSBCRPCSKYDZNXJSQGXSSJMYDNSTZTPBDLTKZWXQWQTZEXNQCZGWEZKSSBYBRTSSSLCCGBPSZQSZLCCGLLLZXHZQTHCZMQGYZQZNMCOCSZJMMZSQPJYGQLJYJPPLDXRGZYXCCSXHSHGTZNLZWZKJCXTCFCJXLBMQBCZZWPQDNHXLJCTHYZLGYLNLSZZPCXDSCQQHJQKSXZPBAJYEMSMJTZDXLCJYRYYNWJBNGZZTMJXLTBSLYRZPYLSSCNXPHLLHYLLQQZQLXYMRSYCXZLMMCZLTZSDWTJJLLNZGGQXPFSKYGYGHBFZPDKMWGHCXMSGDXJMCJZDYCABXJDLNBCDQYGSKYDQTXDJJYXMSZQAZDZFSLQXYJSJZYLBTXXWXQQZBJZUFBBLYLWDSLJHXJYZJWTDJCZFQZQZZDZSXZZQLZCDZFJHYSPYMPQZMLPPLFFXJJNZZYLSJEYQZFPFZKSYWJJJHRDJZZXTXXGLGHYDXCSKYSWMMZCWYBAZBJKSHFHJCXMHFQHYXXYZFTSJYZFXYXPZLCHMZMBXHZZSXYFYMNCWDABAZLXKTCSHHXKXJJZJSTHYGXSXYYHHHJWXKZXSSBZZWHHHCWTZZZPJXSNXQQJGZYZYWLLCWXZFXXYXYHXMKYYSWSQMNLNAYCYSPMJKHWCQHYLAJJMZXHMMCNZHBHXCLXTJPLTXYJHDYYLTTXFSZHYXXSJBJYAYRSMXYPLCKDUYHLXRLNLLSTYZYYQYGYHHSCCSMZCTZQXKYQFPYYRPFFLKQUNTSZLLZMWWTCQQYZWTLLMLMPWMBZSSTZRBPDDTLQJJBXZCSRZQQYGWCSXFWZLXCCRSZDZMCYGGDZQSGTJSWLJMYMMZYHFBJDGYXCCPSHXNZCSBSJYJGJMPPWAFFYFNXHYZXZYLREMZGZCYZSSZDLLJCSQFNXZKPTXZGXJJGFMYYYSNBTYLBNLHPFZDCYFBMGQRRSSSZXYSGTZRNYDZZCDGPJAFJFZKNZBLCZSZPSGCYCJSZLMLRSZBZZLDLSLLYSXSQZQLYXZLSKKBRXBRBZCYCXZZZEEYFGKLZLYYHGZSGZLFJHGTGWKRAAJYZKZQTSSHJJXDCYZUYJLZYRZDQQHGJZXSSZBYKJPBFRTJXLLFQWJHYLQTYMBLPZDXTZYGBDHZZRBGXHWNJTJXLKSCFSMWLSDQYSJTXKZSCFWJLBXFTZLLJZLLQBLSQMQQCGCZFPBPHZCZJLPYYGGDTGWDCFCZQYYYQYSSCLXZSKLZZZGFFCQNWGLHQYZJJCZLQZZYJPJZZBPDCCMHJGXDQDGDLZQMFGPSYTSDYFWWDJZJYSXYYCZCYHZWPBYKXRYLYBHKJKSFXTZJMMCKHLLTNYYMSYXYZPYJQYCSYCWMTJJKQYRHLLQXPSGTLYYCLJSCPXJYZFNMLRGJJTYZBXYZMSJYJHHFZQMSYXRSZCWTLRTQZSSTKXGQKGSPTGCZNJSJCQCXHMXGGZTQYDJKZDLBZSXJLHYQGGGTHQSZPYHJHHGYYGKGGCWJZZYLCZLXQSFTGZSLLLMLJSKCTBLLZZSZMMNYTPZSXQHJCJYQXYZXZQZCPSHKZZYSXCDFGMWQRLLQXRFZTLYSTCTMJCXJJXHJNXTNRZTZFQYHQGLLGCXSZSJDJLJCYDSJTLNYXHSZXCGJZYQPYLFHDJSBPCCZHJJJQZJQDYBSSLLCMYTTMQTBHJQNNYGKYRQYQMZGCJKPDCGMYZHQLLSLLCLMHOLZGDYYFZSLJCQZLYLZQJESHNYLLJXGJXLYSYYYXNBZLJSSZCQQCJYLLZLTJYLLZLLBNYLGQCHXYYXOXCXQKYJXXXYKLXSXXYQXCYKQXQCSGYXXYQXYGYTQOHXHXPYXXXULCYEYCHZZCBWQBBWJQZSCSZSSLZYLKDESJZWMYMCYTSDSXXSCJPQQSQYLYYZYCMDJDZYWCBTJSYDJKCYDDJLBDJJSODZYSYXQQYXDHHGQQYQHDYXWGMMMAJDYBBBPPBCMUUPLJZSMTXERXJMHQNUTPJDCBSSMSSSTKJTSSMMTRCPLZSZMLQDSDMJMQPNQDXCFYNBFSDQXYXHYAYKQYDDLQYYYSSZBYDSLNTFQTZQPZMCHDHCZCWFDXTMYQSPHQYYXSRGJCWTJTZZQMGWJJTJHTQJBBHWZPXXHYQFXXQYWYYHYSCDYDHHQMNMTMWCPBSZPPZZGLMZFOLLCFWHMMSJZTTDHZZYFFYTZZGZYSKYJXQYJZQBHMBZZLYGHGFMSHPZFZSNCLPBQSNJXZSLXXFPMTYJYGBXLLDLXPZJYZJYHHZCYWHJYLSJEXFSZZYWXKZJLUYDTMLYMQJPWXYHXSKTQJEZRPXXZHHMHWQPWQLYJJQJJZSZCPHJLCHHNXJLQWZJHBMZYXBDHHYPZLHLHLGFWLCHYYTLHJXCJMSCPXSTKPNHQXSRTYXXTESYJCTLSSLSTDLLLWWYHDHRJZSFGXTSYCZYNYHTDHWJSLHTZDQDJZXXQHGYLTZPHCSQFCLNJTCLZPFSTPDYNYLGMJLLYCQHYSSHCHYLHQYQTMZYPBYWRFQYKQSYSLZDQJMPXYYSSRHZJNYWTQDFZBWWTWWRXCWHGYHXMKMYYYQMSMZHNGCEPMLQQMTCWCTMMPXJPJJHFXYYZSXZHTYBMSTSYJTTQQQYYLHYNPYQZLCYZHZWSMYLKFJXLWGXYPJYTYSYXYMZCKTTWLKSMZSYLMPWLZWXWQZSSAQSYXYRHSSNTSRAPXCPWCMGDXHXZDZYFJHGZTTSBJHGYZSZYSMYCLLLXBTYXHBBZJKSSDMALXHYCFYGMQYPJYCQXJLLLJGSLZGQLYCJCCZOTYXMTMTTLLWTGPXYMZMKLPSZZZXHKQYSXCTYJZYHXSHYXZKXLZWPSQPYHJWPJPWXQQYLXSDHMRSLZZYZWTTCYXYSZZSHBSCCSTPLWSSCJCHNLCGCHSSPHYLHFHHXJSXYLLNYLSZDHZXYLSXLWZYKCLDYAXZCMDDYSPJTQJZLNWQPSSSWCTSTSZLBLNXSMNYYMJQBQHRZWTYYDCHQLXKPZWBGQYBKFCMZWPZLLYYLSZYDWHXPSBCMLJBSCGBHXLQHYRLJXYSWXWXZSLDFHLSLYNJLZYFLYJYCDRJLFSYZFSLLCQYQFGJYHYXZLYLMSTDJCYHBZLLNWLXXYGYYHSMGDHXXHHLZZJZXCZZZCYQZFNGWPYLCPKPYYPMCLQKDGXZGGWQBDXZZKZFBXXLZXJTPJPTTBYTSZZDWSLCHZHSLTYXHQLHYXXXYYZYSWTXZKHLXZXZPYHGCHKCFSYHUTJRLXFJXPTZTWHPLYXFCRHXSHXKYXXYHZQDXQWULHYHMJTBFLKHTXCWHJFWJCFPQRYQXCYYYQYGRPYWSGSUNGWCHKZDXYFLXXHJJBYZWTSXXNCYJJYMSWZJQRMHXZWFQSYLZJZGBHYNSLBGTTCSYBYXXWXYHXYYXNSQYXMQYWRGYQLXBBZLJSYLPSYTJZYHYZAWLRORJMKSCZJXXXYXCHDYXRYXXJDTSQFXLYLTSFFYXLMTYJMJUYYYXLTZCSXQZQHZXLYYXZHDNBRXXXJCTYHLBRLMBRLLAXKYLLLJLYXXLYCRYLCJTGJCMTLZLLCYZZPZPCYAWHJJFYBDYYZSMPCKZDQYQPBPCJPDCYZMDPBCYYDYCNNPLMTMLRMFMMGWYZBSJGYGSMZQQQZTXMKQWGXLLPJGZBQCDJJJFPKJKCXBLJMSWMDTQJXLDLPPBXCWRCQFBFQJCZAHZGMYKPHYYHZYKNDKZMBPJYXPXYHLFPNYYGXJDBKXNXHJMZJXSTRSTLDXSKZYSYBZXJLXYSLBZYSLHXJPFXPQNBYLLJQKYGZMCYZZYMCCSLCLHZFWFWYXZMWSXTYNXJHPYYMCYSPMHYSMYDYSHQYZCHMJJMZCAAGCFJBBHPLYZYLXXSDJGXDHKXXTXXNBHRMLYJSLTXMRHNLXQJXYZLLYSWQGDLBJHDCGJYQYCMHWFMJYBMBYJYJWYMDPWHXQLDYGPDFXXBCGJSPCKRSSYZJMSLBZZJFLJJJLGXZGYXYXLSZQYXBEXYXHGCXBPLDYHWETTWWCJMBTXCHXYQXLLXFLYXLLJLSSFWDPZSMYJCLMWYTCZPCHQEKCQBWLCQYDPLQPPQZQFJQDJHYMMCXTXDRMJWRHXCJZYLQXDYYNHYYHRSLSRSYWWZJYMTLTLLGTQCJZYABTCKZCJYCCQLJZQXALMZYHYWLWDXZXQDLLQSHGPJFJLJHJABCQZDJGTKHSSTCYJLPSWZLXZXRWGLDLZRLZXTGSLLLLZLYXXWGDZYGBDPHZPBRLWSXQBPFDWOFMWHLYPCBJCCLDMBZPBZZLCYQXLDOMZBLZWPDWYYGDSTTHCSQSCCRSSSYSLFYBFNTYJSZDFNDPDHDZZMBBLSLCMYFFGTJJQWFTMTPJWFNLBZCMMJTGBDZLQLPYFHYYMJYLSDCHDZJWJCCTLJCLDTLJJCPDDSQDSSZYBNDBJLGGJZXSXNLYCYBJXQYCBYLZCFZPPGKCXZDZFZTJJFJSJXZBNZYJQTTYJYHTYCZHYMDJXTTMPXSPLZCDWSLSHXYPZGTFMLCJTYCBPMGDKWYCYZCDSZZYHFLYCTYGWHKJYYLSJCXGYWJCBLLCSNDDBTZBSCLYZCZZSSQDLLMQYYHFSLQLLXFTYHABXGWNYWYYPLLSDLDLLBJCYXJZMLHLJDXYYQYTDLLLBUGBFDFBBQJZZMDPJHGCLGMJJPGAEHHBWCQXAXHHHZCHXYPHJAXHLPHJPGPZJQCQZGJJZZUZDMQYYBZZPHYHYBWHAZYJHYKFGDPFQSDLZMLJXKXGALXZDAGLMDGXMWZQYXXDXXPFDMMSSYMPFMDMMKXKSYZYSHDZKXSYSMMZZZMSYDNZZCZXFPLSTMZDNMXCKJMZTYYMZMZZMSXHHDCZJEMXXKLJSTLWLSQLYJZLLZJSSDPPMHNLZJCZYHMXXHGZCJMDHXTKGRMXFWMCGMWKDTKSXQMMMFZZYDKMSCLCMPCGMHSPXQPZDSSLCXKYXTWLWJYAHZJGZQMCSNXYYMMPMLKJXMHLMLQMXCTKZMJQYSZJSYSZHSYJZJCDAJZYBSDQJZGWZQQXFKDMSDJLFWEHKZQKJPEYPZYSZCDWYJFFMZZYLTTDZZEFMZLBNPPLPLPEPSZALLTYLKCKQZKGENQLWAGYXYDPXLHSXQQWQCQXQCLHYXXMLYCCWLYMQYSKGCHLCJNSZKPYZKCQZQLJPDMDZHLASXLBYDWQLWDNBQCRYDDZTJYBKBWSZDXDTNPJDTCTQDFXQQMGNXECLTTBKPWSLCTYQLPWYZZKLPYGZCQQPLLKCCYLPQMZCZQCLJSLQZDJXLDDHPZQDLJJXZQDXYZQKZLJCYQDYJPPYPQYKJYRMPCBYMCXKLLZLLFQPYLLLMBSGLCYSSLRSYSQTMXYXZQZFDZUYSYZTFFMZZSMZQHZSSCCMLYXWTPZGXZJGZGSJSGKDDHTQGGZLLBJDZLCBCHYXYZHZFYWXYZYMSDBZZYJGTSMTFXQYXQSTDGSLNXDLRYZZLRYYLXQHTXSRTZNGZXBNQQZFMYKMZJBZYMKBPNLYZPBLMCNQYZZZSJZHJCTZKHYZZJRDYZHNPXGLFZTLKGJTCTSSYLLGZRZBBQZZKLPKLCZYSSUYXBJFPNJZZXCDWXZYJXZZDJJKGGRSRJKMSMZJLSJYWQSKYHQJSXPJZZZLSNSHRNYPZTWCHKLPSRZLZXYJQXQKYSJYCZTLQZYBBYBWZPQDWWYZCYTJCJXCKCWDKKZXSGKDZXWWYYJQYYTCYTDLLXWKCZKKLCCLZCQQDZLQLCSFQCHQHSFSMQZZLNBJJZBSJHTSZDYSJQJPDLZCDCWJKJZZLPYCGMZWDJJBSJQZSYZYHHXJPBJYDSSXDZNCGLQMBTSFSBPDZDLZNFGFJGFSMPXJQLMBLGQCYYXBQKDJJQYRFKZTJDHCZKLBSDZCFJTPLLJGXHYXZCSSZZXSTJYGKGCKGYOQXJPLZPBPGTGYJZGHZQZZLBJLSQFZGKQQJZGYCZBZQTLDXRJXBSXXPZXHYZYCLWDXJJHXMFDZPFZHQHQMQGKSLYHTYCGFRZGNQXCLPDLBZCSCZQLLJBLHBZCYPZZPPDYMZZSGYHCKCPZJGSLJLNSCDSLDLXBMSTLDDFJMKDJDHZLZXLSZQPQPGJLLYBDSZGQLBZLSLKYYHZTTNTJYQTZZPSZQZTLLJTYYLLQLLQYZQLBDZLSLYYZYMDFSZSNHLXZNCZQZPBWSKRFBSYZMTHBLGJPMCZZLSTLXSHTCSYZLZBLFEQHLXFLCJLYLJQCBZLZJHHSSTBRMHXZHJZCLXFNBGXGTQJCZTMSFZKJMSSNXLJKBHSJXNTNLZDNTLMSJXGZJYJCZXYJYJWRWWQNZTNFJSZPZSHZJFYRDJSFSZJZBJFZQZZHZLXFYSBZQLZSGYFTZDCSZXZJBQMSZKJRHYJZCKMJKHCHGTXKXQGLXPXFXTRTYLXJXHDTSJXHJZJXZWZLCQSBTXWXGXTXXHXFTSDKFJHZYJFJXRZSDLLLTQSQQZQWZXSYQTWGWBZCGZLLYZBCLMQQTZHZXZXLJFRMYZFLXYSQXXJKXRMQDZDMMYYBSQBHGZMWFWXGMXLZPYYTGZYCCDXYZXYWGSYJYZNBHPZJSQSYXSXRTFYZGRHZTXSZZTHCBFCLSYXZLZQMZLMPLMXZJXSFLBYZMYQHXJSXRXSQZZZSSLYFRCZJRCRXHHZXQYDYHXSJJHZCXZBTYNSYSXJBQLPXZQPYMLXZKYXLXCJLCYSXXZZLXDLLLJJYHZXGYJWKJRWYHCPSGNRZLFZWFZZNSXGXFLZSXZZZBFCSYJDBRJKRDHHGXJLJJTGXJXXSTJTJXLYXQFCSGSWMSBCTLQZZWLZZKXJMLTMJYHSDDBXGZHDLBMYJFRZFSGCLYJBPMLYSMSXLSZJQQHJZFXGFQFQBPXZGYYQXGZTCQWYLTLGWSGWHRLFSFGZJMGMGBGTJFSYZZGZYZAFLSSPMLPFLCWBJZCLJJMZLPJJLYMQDMYYYFBGYGYZMLYZDXQYXRQQQHSYYYQXYLJTYXFSFSLLGNQCYHYCWFHCCCFXPYLYPLLZYXXXXXKQHHXSHJZCFZSCZJXCPZWHHHHHAPYLQALPQAFYHXDYLUKMZQGGGDDESRNNZLTZGCHYPPYSQJJHCLLJTOLNJPZLJLHYMHEYDYDSQYCDDHGZUNDZCLZYZLLZNTNYZGSLHSLPJJBDGWXPCDUTJCKLKCLWKLLCASSTKZZDNQNTTLYYZSSYSSZZRYLJQKCQDHHCRXRZYDGRGCWCGZQFFFPPJFZYNAKRGYWYQPQXXFKJTSZZXSWZDDFBBXTBGTZKZNPZZPZXZPJSZBMQHKCYXYLDKLJNYPKYGHGDZJXXEAHPNZKZTZCMXCXMMJXNKSZQNMNLWBWWXJKYHCPSTMCSQTZJYXTPCTPDTNNPGLLLZSJLSPBLPLQHDTNJNLYYRSZFFJFQWDPHZDWMRZCCLODAXNSSNYZRESTYJWJYJDBCFXNMWTTBYLWSTSZGYBLJPXGLBOCLHPCBJLTMXZLJYLZXCLTPNCLCKXTPZJSWCYXSFYSZDKNTLBYJCYJLLSTGQCBXRYZXBXKLYLHZLQZLNZCXWJZLJZJNCJHXMNZZGJZZXTZJXYCYYCXXJYYXJJXSSSJSTSSTTPPGQTCSXWZDCSYFPTFBFHFBBLZJCLZZDBXGCXLQPXKFZFLSYLTUWBMQJHSZBMDDBCYSCCLDXYCDDQLYJJWMQLLCSGLJJSYFPYYCCYLTJANTJJPWYCMMGQYYSXDXQMZHSZXPFTWWZQSWQRFKJLZJQQYFBRXJHHFWJJZYQAZMYFRHCYYBYQWLPEXCCZSTYRLTTDMQLYKMBBGMYYJPRKZNPBSXYXBHYZDJDNGHPMFSGMWFZMFQMMBCMZZCJJLCNUXYQLMLRYGQZCYXZLWJGCJCGGMCJNFYZZJHYCPRRCMTZQZXHFQGTJXCCJEAQCRJYHPLQLSZDJRBCQHQDYRHYLYXJSYMHZYDWLDFRYHBPYDTSSCNWBXGLPZMLZZTQSSCPJMXXYCSJYTYCGHYCJWYRXXLFEMWJNMKLLSWTXHYYYNCMMCWJDQDJZGLLJWJRKHPZGGFLCCSCZMCBLTBHBQJXQDSPDJZZGKGLFQYWBZYZJLTSTDHQHCTCBCHFLQMPWDSHYYTQWCNZZJTLBYMBPDYYYXSQKXWYYFLXXNCWCXYPMAELYKKJMZZZBRXYYQJFLJPFHHHYTZZXSGQQMHSPGDZQWBWPJHZJDYSCQWZKTXXSQLZYYMYSDZGRXCKKUJLWPYSYSCSYZLRMLQSYLJXBCXTLWDQZPCYCYKPPPNSXFYZJJRCEMHSZMSXLXGLRWGCSTLRSXBZGBZGZTCPLUJLSLYLYMTXMTZPALZXPXJTJWTCYYZLBLXBZLQMYLXPGHDSLSSDMXMBDZZSXWHAMLCZCPJMCNHJYSNSYGCHSKQMZZQDLLKABLWJXSFMOCDXJRRLYQZKJMYBYQLYHETFJZFRFKSRYXFJTWDSXXSYSQJYSLYXWJHSNLXYYXHBHAWHHJZXWMYLJCSSLKYDZTXBZSYFDXGXZJKHSXXYBSSXDPYNZWRPTQZCZENYGCXQFJYKJBZMLJCMQQXUOXSLYXXLYLLJDZBTYMHPFSTTQQWLHOKYBLZZALZXQLHZWRRQHLSTMYPYXJJXMQSJFNBXYXYJXXYQYLTHYLQYFMLKLJTMLLHSZWKZHLJMLHLJKLJSTLQXYLMBHHLNLZXQJHXCFXXLHYHJJGBYZZKBXSCQDJQDSUJZYYHZHHMGSXCSYMXFEBCQWWRBPYYJQTYZCYQYQQZYHMWFFHGZFRJFCDPXNTQYZPDYKHJLFRZXPPXZDBBGZQSTLGDGYLCQMLCHHMFYWLZYXKJLYPQHSYWMQQGQZMLZJNSQXJQSYJYCBEHSXFSZPXZWFLLBCYYJDYTDTHWZSFJMQQYJLMQXXLLDTTKHHYBFPWTYYSQQWNQWLGWDEBZWCMYGCULKJXTMXMYJSXHYBRWFYMWFRXYQMXYSZTZZTFYKMLDHQDXWYYNLCRYJBLPSXCXYWLSPRRJWXHQYPHTYDNXHHMMYWYTZCSQMTSSCCDALWZTCPQPYJLLQZYJSWXMZZMMYLMXCLMXCZMXMZSQTZPPQQBLPGXQZHFLJJHYTJSRXWZXSCCDLXTYJDCQJXSLQYCLZXLZZXMXQRJMHRHZJBHMFLJLMLCLQNLDXZLLLPYPSYJYSXCQQDCMQJZZXHNPNXZMEKMXHYKYQLXSXTXJYYHWDCWDZHQYYBGYBCYSCFGPSJNZDYZZJZXRZRQJJYMCANYRJTLDPPYZBSTJKXXZYPFDWFGZZRPYMTNGXZQBYXNBUFNQKRJQZMJEGRZGYCLKXZDSKKNSXKCLJSPJYYZLQQJYBZSSQLLLKJXTBKTYLCCDDBLSPPFYLGYDTZJYQGGKQTTFZXBDKTYYHYBBFYTYYBCLPDYTGDHRYRNJSPTCSNYJQHKLLLZSLYDXXWBCJQSPXBPJZJCJDZFFXXBRMLAZHCSNDLBJDSZBLPRZTSWSBXBCLLXXLZDJZSJPYLYXXYFTFFFBHJJXGBYXJPMMMPSSJZJMTLYZJXSWXTYLEDQPJMYGQZJGDJLQJWJQLLSJGJGYGMSCLJJXDTYGJQJQJCJZCJGDZZSXQGSJGGCXHQXSNQLZZBXHSGZXCXYLJXYXYYDFQQJHJFXDHCTXJYRXYSQTJXYEFYYSSYYJXNCYZXFXMSYSZXYYSCHSHXZZZGZZZGFJDLTYLNPZGYJYZYYQZPBXQBDZTZCZYXXYHHSQXSHDHGQHJHGYWSZTMZMLHYXGEBTYLZKQWYTJZRCLEKYSTDBCYKQQSAYXCJXWWGSBHJYZYDHCSJKQCXSWXFLTYNYZPZCCZJQTZWJQDZZZQZLJJXLSBHPYXXPSXSHHEZTXFPTLQYZZXHYTXNCFZYYHXGNXMYWXTZSJPTHHGYMXMXQZXTSBCZYJYXXTYYZYPCQLMMSZMJZZLLZXGXZAAJZYXJMZXWDXZSXZDZXLEYJJZQBHZWZZZQTZPSXZTDSXJJJZNYAZPHXYYSRNQDTHZHYYKYJHDZXZLSWCLYBZYECWCYCRYLCXNHZYDZYDYJDFRJJHTRSQTXYXJRJHOJYNXELXSFSFJZGHPZSXZSZDZCQZBYYKLSGSJHCZSHDGQGXYZGXCHXZJWYQWGYHKSSEQZZNDZFKWYSSTCLZSTSYMCDHJXXYWEYXCZAYDMPXMDSXYBSQMJMZJMTZQLPJYQZCGQHXJHHLXXHLHDLDJQCLDWBSXFZZYYSCHTYTYYBHECXHYKGJPXHHYZJFXHWHBDZFYZBCAPNPGNYDMSXHMMMMAMYNBYJTMPXYYMCTHJBZYFCGTYHWPHFTWZZEZSBZEGPFMTSKFTYCMHFLLHGPZJXZJGZJYXZSBBQSCZZLZCCSTPGXMJSFTCCZJZDJXCYBZLFCJSYZFGSZLYBCWZZBYZDZYPSWYJZXZBDSYUXLZZBZFYGCZXBZHZFTPBGZGEJBSTGKDMFHYZZJHZLLZZGJQZLSFDJSSCBZGPDLFZFZSZYZYZSYGCXSNXXCHCZXTZZLJFZGQSQYXZJQDCCZTQCDXZJYQJQCHXZTDLGSCXZSYQJQTZWLQDQZTQCHQQJZYEZZZPBWKDJFCJPZTYPQYQTTYNLMBDKTJZPQZQZZFPZSBNJLGYJDXJDZZKZGQKXDLPZJTCJDQBXDJQJSTCKNXBXZMSLYJCQMTJQWWCJQNJNLLLHJCWQTBZQYDZCZPZZDZYDDCYZZZCCJTTJFZDPRRTZTJDCQTQZDTJNPLZBCLLCTZSXKJZQZPZLBZRBTJDCXFCZDBCCJJLTQQPLDCGZDBBZJCQDCJWYNLLZYZCCDWLLXWZLXRXNTQQCZXKQLSGDFQTDDGLRLAJJTKUYMKQLLTZYTDYYCZGJWYXDXFRSKSTQTENQMRKQZHHQKDLDAZFKYPBGGPZREBZZYKZZSPEGJXGYKQZZZSLYSYYYZWFQZYLZZLZHWCHKYPQGNPGBLPLRRJYXCCSYYHSFZFYBZYYTGZXYLXCZWXXZJZBLFFLGSKHYJZEYJHLPLLLLCZGXDRZELRHGKLZZYHZLYQSZZJZQLJZFLNBHGWLCZCFJYSPYXZLZLXGCCPZBLLCYBBBBUBBCBPCRNNZCZYRBFSRLDCGQYYQXYGMQZWTZYTYJXYFWTEHZZJYWLCCNTZYJJZDEDPZDZTSYQJHDYMBJNYJZLXTSSTPHNDJXXBYXQTZQDDTJTDYYTGWSCSZQFLSHLGLBCZPHDLYZJYCKWTYTYLBNYTSDSYCCTYSZYYEBHEXHQDTWNYGYCLXTSZYSTQMYGZAZCCSZZDSLZCLZRQXYYELJSBYMXSXZTEMBBLLYYLLYTDQYSHYMRQWKFKBFXNXSBYCHXBWJYHTQBPBSBWDZYLKGZSKYHXQZJXHXJXGNLJKZLYYCDXLFYFGHLJGJYBXQLYBXQPQGZTZPLNCYPXDJYQYDYMRBESJYYHKXXSTMXRCZZYWXYQYBMCLLYZHQYZWQXDBXBZWZMSLPDMYSKFMZKLZCYQYCZLQXFZZYDQZPZYGYJYZMZXDZFYFYTTQTZHGSPCZMLCCYTZXJCYTJMKSLPZHYSNZLLYTPZCTZZCKTXDHXXTQCYFKSMQCCYYAZHTJPCYLZLYJBJXTPNYLJYYNRXSYLMMNXJSMYBCSYSYLZYLXJJQYLDZLPQBFZZBLFNDXQKCZFYWHGQMRDSXYCYTXNQQJZYYPFZXDYZFPRXEJDGYQBXRCNFYYQPGHYJDYZXGRHTKYLNWDZNTSMPKLBTHBPYSZBZTJZSZZJTYYXZPHSSZZBZCZPTQFZMYFLYPYBBJQXZMXXDJMTSYSKKBJZXHJCKLPSMKYJZCXTMLJYXRZZQSLXXQPYZXMKYXXXJCLJPRMYYGADYSKQLSNDHYZKQXZYZTCGHZTLMLWZYBWSYCTBHJHJFCWZTXWYTKZLXQSHLYJZJXTMPLPYCGLTBZZTLZJCYJGDTCLKLPLLQPJMZPAPXYZLKKTKDZCZZBNZDYDYQZJYJGMCTXLTGXSZLMLHBGLKFWNWZHDXUHLFMKYSLGXDTWWFRJEJZTZHYDXYKSHWFZCQSHKTMQQHTZHYMJDJSKHXZJZBZZXYMPAGQMSTPXLSKLZYNWRTSQLSZBPSPSGZWYHTLKSSSWHZZLYYTNXJGMJSZSUFWNLSOZTXGXLSAMMLBWLDSZYLAKQCQCTMYCFJBSLXCLZZCLXXKSBZQCLHJPSQPLSXXCKSLNHPSFQQYTXYJZLQLDXZQJZDYYDJNZPTUZDSKJFSLJHYLZSQZLBTXYDGTQFDBYAZXDZHZJNHHQBYKNXJJQCZMLLJZKSPLDYCLBBLXKLELXJLBQYCXJXGCNLCQPLZLZYJTZLJGYZDZPLTQCSXFDMNYCXGBTJDCZNBGBQYQJWGKFHTNPYQZQGBKPBBYZMTJDYTBLSQMPSXTBNPDXKLEMYYCJYNZCTLDYKZZXDDXHQSHDGMZSJYCCTAYRZLPYLTLKXSLZCGGEXCLFXLKJRTLQJAQZNCMBYDKKCXGLCZJZXJHPTDJJMZQYKQSECQZDSHHADMLZFMMZBGNTJNNLGBYJBRBTMLBYJDZXLCJLPLDLPCQDHLXZLYCBLCXZZJADJLNZMMSSSMYBHBSQKBHRSXXJMXSDZNZPXLGBRHWGGFCXGMSKLLTSJYYCQLTSKYWYYHYWXBXQYWPYWYKQLSQPTNTKHQCWDQKTWPXXHCPTHTWUMSSYHBWCRWXHJMKMZNGWTMLKFGHKJYLSYYCXWHYECLQHKQHTTQKHFZLDXQWYZYYDESBPKYRZPJFYYZJCEQDZZDLATZBBFJLLCXDLMJSSXEGYGSJQXCWBXSSZPDYZCXDNYXPPZYDLYJCZPLTXLSXYZYRXCYYYDYLWWNZSAHJSYQYHGYWWAXTJZDAXYSRLTDPSSYYFNEJDXYZHLXLLLZQZSJNYQYQQXYJGHZGZCYJCHZLYCDSHWSHJZYJXCLLNXZJJYYXNFXMWFPYLCYLLABWDDHWDXJMCXZTZPMLQZHSFHZYNZTLLDYWLSLXHYMMYLMBWWKYXYADTXYLLDJPYBPWUXJMWMLLSAFDLLYFLBHHHBQQLTZJCQJLDJTFFKMMMBYTHYGDCQRDDWRQJXNBYSNWZDBYYTBJHPYBYTTJXAAHGQDQTMYSTQXKBTZPKJLZRBEQQSSMJJBDJOTGTBXPGBKTLHQXJJJCTHXQDWJLWRFWQGWSHCKRYSWGFTGYGBXSDWDWRFHWYTJJXXXJYZYSLPYYYPAYXHYDQKXSHXYXGSKQHYWFDDDPPLCJLQQEEWXKSYYKDYPLTJTHKJLTCYYHHJTTPLTZZCDLTHQKZXQYSTEEYWYYZYXXYYSTTJKLLPZMCYHQGXYHSRMBXPLLNQYDQHXSXXWGDQBSHYLLPJJJTHYJKYPPTHYYKTYEZYENMDSHLCRPQFDGFXZPSFTLJXXJBSWYYSKSFLXLPPLBBBLBSFXFYZBSJSSYLPBBFFFFSSCJDSTZSXZRYYSYFFSYZYZBJTBCTSBSDHRTJJBYTCXYJEYLXCBNEBJDSYXYKGSJZBXBYTFZWGENYHHTHZHHXFWGCSTBGXKLSXYWMTMBYXJSTZSCDYQRCYTWXZFHMYMCXLZNSDJTTTXRYCFYJSBSDYERXJLJXBBDEYNJGHXGCKGSCYMBLXJMSZNSKGXFBNBPTHFJAAFXYXFPXMYPQDTZCXZZPXRSYWZDLYBBKTYQPQJPZYPZJZNJPZJLZZFYSBTTSLMPTZRTDXQSJEHBZYLZDHLJSQMLHTXTJECXSLZZSPKTLZKQQYFSYGYWPCPQFHQHYTQXZKRSGTTSQCZLPTXCDYYZXSQZSLXLZMYCPCQBZYXHBSXLZDLTCDXTYLZJYYZPZYZLTXJSJXHLPMYTXCQRBLZSSFJZZTNJYTXMYJHLHPPLCYXQJQQKZZSCPZKSWALQSBLCCZJSXGWWWYGYKTJBBZTDKHXHKGTGPBKQYSLPXPJCKBMLLXDZSTBKLGGQKQLSBKKTFXRMDKBFTPZFRTBBRFERQGXYJPZSSTLBZTPSZQZSJDHLJQLZBPMSMMSXLQQNHKNBLRDDNXXDHDDJCYYGYLXGZLXSYGMQQGKHBPMXYXLYTQWLWGCPBMQXCYZYDRJBHTDJYHQSHTMJSBYPLWHLZFFNYPMHXXHPLTBQPFBJWQDBYGPNZTPFZJGSDDTQSHZEAWZZYLLTYYBWJKXXGHLFKXDJTMSZSQYNZGGSWQSPHTLSSKMCLZXYSZQZXNCJDQGZDLFNYKLJCJLLZLMZZNHYDSSHTHZZLZZBBHQZWWYCRZHLYQQJBEYFXXXWHSRXWQHWPSLMSSKZTTYGYQQWRSLALHMJTQJSMXQBJJZJXZYZKXBYQXBJXSHZTSFJLXMXZXFGHKZSZGGYLCLSARJYHSLLLMZXELGLXYDJYTLFBHBPNLYZFBBHPTGJKWETZHKJJXZXXGLLJLSTGSHJJYQLQZFKCGNNDJSSZFDBCTWWSEQFHQJBSAQTGYPQLBXBMMYWXGSLZHGLZGQYFLZBYFZJFRYSFMBYZHQGFWZSYFYJJPHZBYYZFFWODGRLMFTWLBZGYCQXCDJYGZYYYYTYTYDWEGAZYHXJLZYYHLRMGRXXZCLHNELJJTJTPWJYBJJBXJJTJTEEKHWSLJPLPSFYZPQQBDLQJJTYYQLYZKDKSQJYYQZLDQTGJQYZJSUCMRYQTHTEJMFCTYHYPKMHYZWJDQFHYYXWSHCTXRLJHQXHCCYYYJLTKTTYTMXGTCJTZAYYOCZLYLBSZYWJYTSJYHBYSHFJLYGJXXTMZYYLTXXYPZLXYJZYZYYPNHMYMDYYLBLHLSYYQQLLNJJYMSOYQBZGDLYXYLCQYXTSZEGXHZGLHWBLJHEYXTWQMAKBPQCGYSHHEGQCMWYYWLJYJHYYZLLJJYLHZYHMGSLJLJXCJJYCLYCJPCPZJZJMMYLCQLNQLJQJSXYJMLSZLJQLYCMMHCFMMFPQQMFYLQMCFFQMMMMHMZNFHHJGTTHHKHSLNCHHYQDXTMMQDCYZYXYQMYQYLTDCYYYZAZZCYMZYDLZFFFMMYCQZWZZMABTBYZTDMNZZGGDFTYPCGQYTTSSFFWFDTZQSSYSTWXJHXYTSXXYLBYQHWWKXHZXWZNNZZJZJJQJCCCHYYXBZXZCYZTLLCQXYNJYCYYCYNZZQYYYEWYCZDCJYCCHYJLBTZYYCQWMPWPYMLGKDLDLGKQQBGYCHJXY";
			this.full_dict = {
				"a" : "\u554a\u963f\u9515",
				"ai" : "\u57c3\u6328\u54ce\u5509\u54c0\u7691\u764c\u853c\u77ee\u827e\u788d\u7231\u9698\u8bf6\u6371\u55f3\u55cc\u5ad2\u7477\u66a7\u7839\u953f\u972d",
				"an" : "\u978d\u6c28\u5b89\u4ffa\u6309\u6697\u5cb8\u80fa\u6848\u8c19\u57ef\u63de\u72b4\u5eb5\u6849\u94f5\u9e4c\u9878\u9eef",
				"ang" : "\u80ae\u6602\u76ce",
				"ao" : "\u51f9\u6556\u71ac\u7ff1\u8884\u50b2\u5965\u61ca\u6fb3\u5773\u62d7\u55f7\u5662\u5c99\u5ed2\u9068\u5aaa\u9a9c\u8071\u87af\u93ca\u9ccc\u93d6",
				"ba" : "\u82ad\u634c\u6252\u53ed\u5427\u7b06\u516b\u75a4\u5df4\u62d4\u8dcb\u9776\u628a\u8019\u575d\u9738\u7f62\u7238\u8307\u83dd\u8406\u636d\u5c9c\u705e\u6777\u94af\u7c91\u9c85\u9b43",
				"bai" : "\u767d\u67cf\u767e\u6446\u4f70\u8d25\u62dc\u7a17\u859c\u63b0\u97b4",
				"ban" : "\u6591\u73ed\u642c\u6273\u822c\u9881\u677f\u7248\u626e\u62cc\u4f34\u74e3\u534a\u529e\u7eca\u962a\u5742\u8c73\u94a3\u7622\u764d\u8228",
				"bang" : "\u90a6\u5e2e\u6886\u699c\u8180\u7ed1\u68d2\u78c5\u868c\u9551\u508d\u8c24\u84a1\u8783",
				"bao" : "\u82de\u80de\u5305\u8912\u96f9\u4fdd\u5821\u9971\u5b9d\u62b1\u62a5\u66b4\u8c79\u9c8d\u7206\u52f9\u8446\u5b80\u5b62\u7172\u9e28\u8913\u8db5\u9f85",
				"bo" : "\u5265\u8584\u73bb\u83e0\u64ad\u62e8\u94b5\u6ce2\u535a\u52c3\u640f\u94c2\u7b94\u4f2f\u5e1b\u8236\u8116\u818a\u6e24\u6cca\u9a73\u4eb3\u8543\u5575\u997d\u6a97\u64d8\u7934\u94b9\u9e41\u7c38\u8ddb",
				"bei" : "\u676f\u7891\u60b2\u5351\u5317\u8f88\u80cc\u8d1d\u94a1\u500d\u72c8\u5907\u60eb\u7119\u88ab\u5b5b\u9642\u90b6\u57e4\u84d3\u5457\u602b\u6096\u789a\u9e4e\u8919\u943e",
				"ben" : "\u5954\u82ef\u672c\u7b28\u755a\u574c\u951b",
				"beng" : "\u5d29\u7ef7\u752d\u6cf5\u8e66\u8ff8\u552a\u5623\u750f",
				"bi" : "\u903c\u9f3b\u6bd4\u9119\u7b14\u5f7c\u78a7\u84d6\u853d\u6bd5\u6bd9\u6bd6\u5e01\u5e87\u75f9\u95ed\u655d\u5f0a\u5fc5\u8f9f\u58c1\u81c2\u907f\u965b\u5315\u4ef3\u4ffe\u8298\u835c\u8378\u5421\u54d4\u72f4\u5eb3\u610e\u6ed7\u6fde\u5f3c\u59a3\u5a62\u5b16\u74a7\u8d32\u7540\u94cb\u79d5\u88e8\u7b5a\u7b85\u7be6\u822d\u895e\u8df8\u9ac0",
				"bian" : "\u97ad\u8fb9\u7f16\u8d2c\u6241\u4fbf\u53d8\u535e\u8fa8\u8fa9\u8fab\u904d\u533e\u5f01\u82c4\u5fed\u6c74\u7f0f\u7178\u782d\u78a5\u7a39\u7a86\u8759\u7b3e\u9cca",
				"biao" : "\u6807\u5f6a\u8198\u8868\u5a4a\u9aa0\u98d1\u98d9\u98da\u706c\u9556\u9573\u762d\u88f1\u9cd4",
				"bie" : "\u9cd6\u618b\u522b\u762a\u8e69\u9cd8",
				"bin" : "\u5f6c\u658c\u6fd2\u6ee8\u5bbe\u6448\u50a7\u6d5c\u7f24\u73a2\u6ba1\u8191\u9554\u9acc\u9b13",
				"bing" : "\u5175\u51b0\u67c4\u4e19\u79c9\u997c\u70b3\u75c5\u5e76\u7980\u90b4\u6452\u7ee0\u678b\u69df\u71f9",
				"bu" : "\u6355\u535c\u54fa\u8865\u57e0\u4e0d\u5e03\u6b65\u7c3f\u90e8\u6016\u62ca\u535f\u900b\u74ff\u6661\u949a\u91ad",
				"ca" : "\u64e6\u5693\u7924",
				"cai" : "\u731c\u88c1\u6750\u624d\u8d22\u776c\u8e29\u91c7\u5f69\u83dc\u8521",
				"can" : "\u9910\u53c2\u8695\u6b8b\u60ed\u60e8\u707f\u9a96\u74a8\u7cb2\u9eea",
				"cang" : "\u82cd\u8231\u4ed3\u6ca7\u85cf\u4f27",
				"cao" : "\u64cd\u7cd9\u69fd\u66f9\u8349\u8279\u5608\u6f15\u87ac\u825a",
				"ce" : "\u5395\u7b56\u4fa7\u518c\u6d4b\u5202\u5e3b\u607b",
				"ceng" : "\u5c42\u8e6d\u564c",
				"cha" : "\u63d2\u53c9\u832c\u8336\u67e5\u78b4\u643d\u5bdf\u5c94\u5dee\u8be7\u7339\u9987\u6c4a\u59f9\u6748\u6942\u69ce\u6aab\u9497\u9538\u9572\u8869",
				"chai" : "\u62c6\u67f4\u8c7a\u4faa\u8308\u7625\u867f\u9f87",
				"chan" : "\u6400\u63ba\u8749\u998b\u8c17\u7f20\u94f2\u4ea7\u9610\u98a4\u5181\u8c04\u8c36\u8487\u5edb\u5fcf\u6f7a\u6fb6\u5b71\u7fbc\u5a75\u5b17\u9aa3\u89c7\u7985\u9561\u88e3\u87fe\u8e94",
				"chang" : "\u660c\u7316\u573a\u5c1d\u5e38\u957f\u507f\u80a0\u5382\u655e\u7545\u5531\u5021\u4f25\u9b2f\u82cc\u83d6\u5f9c\u6005\u60dd\u960a\u5a3c\u5ae6\u6636\u6c05\u9cb3",
				"chao" : "\u8d85\u6284\u949e\u671d\u5632\u6f6e\u5de2\u5435\u7092\u600a\u7ec9\u6641\u8016",
				"che" : "\u8f66\u626f\u64a4\u63a3\u5f7b\u6f88\u577c\u5c6e\u7817",
				"chen" : "\u90f4\u81e3\u8fb0\u5c18\u6668\u5ff1\u6c89\u9648\u8d81\u886c\u79f0\u8c0c\u62bb\u55d4\u5bb8\u741b\u6987\u809c\u80c2\u789c\u9f80",
				"cheng" : "\u6491\u57ce\u6a59\u6210\u5448\u4e58\u7a0b\u60e9\u6f84\u8bda\u627f\u901e\u9a8b\u79e4\u57d5\u5d4a\u5fb5\u6d48\u67a8\u67fd\u6a18\u665f\u584d\u77a0\u94d6\u88ce\u86cf\u9172",
				"chi" : "\u5403\u75f4\u6301\u5319\u6c60\u8fdf\u5f1b\u9a70\u803b\u9f7f\u4f88\u5c3a\u8d64\u7fc5\u65a5\u70bd\u50ba\u5880\u82aa\u830c\u640b\u53f1\u54e7\u557b\u55e4\u5f73\u996c\u6cb2\u5ab8\u6555\u80dd\u7719\u7735\u9e31\u761b\u892b\u86a9\u87ad\u7b1e\u7bea\u8c49\u8e05\u8e1f\u9b51",
				"chong" : "\u5145\u51b2\u866b\u5d07\u5ba0\u833a\u5fe1\u61a7\u94f3\u825f",
				"chou" : "\u62bd\u916c\u7574\u8e0c\u7a20\u6101\u7b79\u4ec7\u7ef8\u7785\u4e11\u4fe6\u5733\u5e31\u60c6\u6eb4\u59af\u7633\u96e0\u9c8b",
				"chu" : "\u81ed\u521d\u51fa\u6a71\u53a8\u8e87\u9504\u96cf\u6ec1\u9664\u695a\u7840\u50a8\u77d7\u6410\u89e6\u5904\u4e8d\u520d\u61b7\u7ecc\u6775\u696e\u6a17\u870d\u8e70\u9edc",
				"chuan" : "\u63e3\u5ddd\u7a7f\u693d\u4f20\u8239\u5598\u4e32\u63be\u821b\u60f4\u9044\u5ddb\u6c1a\u948f\u9569\u8221",
				"chuang" : "\u75ae\u7a97\u5e62\u5e8a\u95ef\u521b\u6006",
				"chui" : "\u5439\u708a\u6376\u9524\u5782\u9672\u68f0\u69cc",
				"chun" : "\u6625\u693f\u9187\u5507\u6df3\u7eaf\u8822\u4fc3\u83bc\u6c8c\u80ab\u6710\u9e51\u877d",
				"chuo" : "\u6233\u7ef0\u851f\u8fb6\u8f8d\u955e\u8e14\u9f8a",
				"ci" : "\u75b5\u8328\u78c1\u96cc\u8f9e\u6148\u74f7\u8bcd\u6b64\u523a\u8d50\u6b21\u8360\u5472\u5d6f\u9e5a\u8785\u7ccd\u8d91",
				"cong" : "\u806a\u8471\u56f1\u5306\u4ece\u4e1b\u506c\u82c1\u6dd9\u9aa2\u742e\u7481\u679e",
				"cu" : "\u51d1\u7c97\u918b\u7c07\u731d\u6b82\u8e59",
				"cuan" : "\u8e7f\u7be1\u7a9c\u6c46\u64ba\u6615\u7228",
				"cui" : "\u6467\u5d14\u50ac\u8106\u7601\u7cb9\u6dec\u7fe0\u8403\u60b4\u7480\u69b1\u96b9",
				"cun" : "\u6751\u5b58\u5bf8\u78cb\u5fd6\u76b4",
				"cuo" : "\u64ae\u6413\u63aa\u632b\u9519\u539d\u811e\u9509\u77ec\u75e4\u9e7e\u8e49\u8e9c",
				"da" : "\u642d\u8fbe\u7b54\u7629\u6253\u5927\u8037\u54d2\u55d2\u601b\u59b2\u75b8\u8921\u7b2a\u977c\u9791",
				"dai" : "\u5446\u6b79\u50a3\u6234\u5e26\u6b86\u4ee3\u8d37\u888b\u5f85\u902e\u6020\u57ed\u7519\u5454\u5cb1\u8fe8\u902f\u9a80\u7ed0\u73b3\u9edb",
				"dan" : "\u803d\u62c5\u4e39\u5355\u90f8\u63b8\u80c6\u65e6\u6c2e\u4f46\u60ee\u6de1\u8bde\u5f39\u86cb\u4ebb\u510b\u5369\u840f\u5556\u6fb9\u6a90\u6b9a\u8d55\u7708\u7605\u8043\u7baa",
				"dang" : "\u5f53\u6321\u515a\u8361\u6863\u8c20\u51fc\u83ea\u5b95\u7800\u94db\u88c6",
				"dao" : "\u5200\u6363\u8e48\u5012\u5c9b\u7977\u5bfc\u5230\u7a3b\u60bc\u9053\u76d7\u53e8\u5541\u5fc9\u6d2e\u6c18\u7118\u5fd1\u7e9b",
				"de" : "\u5fb7\u5f97\u7684\u951d",
				"deng" : "\u8e6c\u706f\u767b\u7b49\u77aa\u51f3\u9093\u5654\u5d9d\u6225\u78f4\u956b\u7c26",
				"di" : "\u5824\u4f4e\u6ef4\u8fea\u654c\u7b1b\u72c4\u6da4\u7fdf\u5ae1\u62b5\u5e95\u5730\u8482\u7b2c\u5e1d\u5f1f\u9012\u7f14\u6c10\u7c74\u8bcb\u8c1b\u90b8\u577b\u839c\u837b\u5600\u5a23\u67e2\u68e3\u89cc\u7825\u78b2\u7747\u955d\u7f9d\u9ab6",
				"dian" : "\u98a0\u6382\u6ec7\u7898\u70b9\u5178\u975b\u57ab\u7535\u4f43\u7538\u5e97\u60e6\u5960\u6dc0\u6bbf\u4e36\u963d\u576b\u57dd\u5dc5\u73b7\u765c\u766b\u7c1f\u8e2e",
				"diao" : "\u7889\u53fc\u96d5\u51cb\u5201\u6389\u540a\u9493\u8c03\u8f7a\u94de\u8729\u7c9c\u8c82",
				"die" : "\u8dcc\u7239\u789f\u8776\u8fed\u8c0d\u53e0\u4f5a\u57a4\u581e\u63f2\u558b\u6e2b\u8f76\u7252\u74de\u8936\u800b\u8e40\u9cbd\u9cce",
				"ding" : "\u4e01\u76ef\u53ee\u9489\u9876\u9f0e\u952d\u5b9a\u8ba2\u4e22\u4ec3\u5576\u738e\u815a\u7887\u753a\u94e4\u7594\u8035\u914a",
				"dong" : "\u4e1c\u51ac\u8463\u61c2\u52a8\u680b\u4f97\u606b\u51bb\u6d1e\u578c\u549a\u5cbd\u5cd2\u5902\u6c21\u80e8\u80f4\u7850\u9e2b",
				"dou" : "\u515c\u6296\u6597\u9661\u8c46\u9017\u75d8\u8538\u94ad\u7aa6\u7aac\u86aa\u7bfc\u9161",
				"du" : "\u90fd\u7763\u6bd2\u728a\u72ec\u8bfb\u5835\u7779\u8d4c\u675c\u9540\u809a\u5ea6\u6e21\u5992\u828f\u561f\u6e0e\u691f\u6a50\u724d\u8839\u7b03\u9ad1\u9ee9",
				"duan" : "\u7aef\u77ed\u953b\u6bb5\u65ad\u7f0e\u5f56\u6934\u7145\u7c16",
				"dui" : "\u5806\u5151\u961f\u5bf9\u603c\u619d\u7893",
				"dun" : "\u58a9\u5428\u8e72\u6566\u987f\u56e4\u949d\u76fe\u9041\u7096\u7818\u7905\u76f9\u9566\u8db8",
				"duo" : "\u6387\u54c6\u591a\u593a\u579b\u8eb2\u6735\u8dfa\u8235\u5241\u60f0\u5815\u5484\u54da\u7f0d\u67c1\u94ce\u88f0\u8e31",
				"e" : "\u86fe\u5ce8\u9e45\u4fc4\u989d\u8bb9\u5a25\u6076\u5384\u627c\u904f\u9102\u997f\u5669\u8c14\u57a9\u57ad\u82ca\u83aa\u843c\u5443\u6115\u5c59\u5a40\u8f6d\u66f7\u816d\u786a\u9507\u9537\u9e57\u989a\u9cc4",
				"en" : "\u6069\u84bd\u6441\u5514\u55ef",
				"er" : "\u800c\u513f\u8033\u5c14\u9975\u6d31\u4e8c\u8d30\u8fe9\u73e5\u94d2\u9e38\u9c95",
				"fa" : "\u53d1\u7f5a\u7b4f\u4f10\u4e4f\u9600\u6cd5\u73d0\u57a1\u781d",
				"fan" : "\u85e9\u5e06\u756a\u7ffb\u6a0a\u77fe\u9492\u7e41\u51e1\u70e6\u53cd\u8fd4\u8303\u8d29\u72af\u996d\u6cdb\u8629\u5e61\u72ad\u68b5\u6535\u71d4\u7548\u8e6f",
				"fang" : "\u574a\u82b3\u65b9\u80aa\u623f\u9632\u59a8\u4eff\u8bbf\u7eba\u653e\u531a\u90a1\u5f77\u94ab\u822b\u9c82",
				"fei" : "\u83f2\u975e\u5561\u98de\u80a5\u532a\u8bfd\u5420\u80ba\u5e9f\u6cb8\u8d39\u82be\u72d2\u60b1\u6ddd\u5983\u7ecb\u7eef\u69a7\u8153\u6590\u6249\u7953\u7829\u9544\u75f1\u871a\u7bda\u7fe1\u970f\u9cb1",
				"fen" : "\u82ac\u915a\u5429\u6c1b\u5206\u7eb7\u575f\u711a\u6c7e\u7c89\u594b\u4efd\u5fff\u6124\u7caa\u507e\u7035\u68fc\u610d\u9cbc\u9f22",
				"feng" : "\u4e30\u5c01\u67ab\u8702\u5cf0\u950b\u98ce\u75af\u70fd\u9022\u51af\u7f1d\u8bbd\u5949\u51e4\u4ff8\u9146\u8451\u6ca3\u781c",
				"fu" : "\u4f5b\u5426\u592b\u6577\u80a4\u5b75\u6276\u62c2\u8f90\u5e45\u6c1f\u7b26\u4f0f\u4fd8\u670d\u6d6e\u6daa\u798f\u88b1\u5f17\u752b\u629a\u8f85\u4fef\u91dc\u65a7\u812f\u8151\u5e9c\u8150\u8d74\u526f\u8986\u8d4b\u590d\u5085\u4ed8\u961c\u7236\u8179\u8d1f\u5bcc\u8ba3\u9644\u5987\u7f1a\u5490\u5310\u51eb\u90db\u8299\u82fb\u832f\u83a9\u83d4\u544b\u5e5e\u6ecf\u8274\u5b5a\u9a78\u7ec2\u6874\u8d59\u9efb\u9efc\u7f58\u7a03\u99a5\u864d\u86a8\u8709\u8760\u876e\u9eb8\u8dba\u8dd7\u9cc6",
				"ga" : "\u5676\u560e\u86e4\u5c2c\u5477\u5c15\u5c1c\u65ee\u9486",
				"gai" : "\u8be5\u6539\u6982\u9499\u76d6\u6e89\u4e10\u9654\u5793\u6224\u8d45\u80f2",
				"gan" : "\u5e72\u7518\u6746\u67d1\u7aff\u809d\u8d76\u611f\u79c6\u6562\u8d63\u5769\u82f7\u5c34\u64c0\u6cd4\u6de6\u6f89\u7ec0\u6a44\u65f0\u77f8\u75b3\u9150",
				"gang" : "\u5188\u521a\u94a2\u7f38\u809b\u7eb2\u5c97\u6e2f\u6206\u7f61\u9883\u7b7b",
				"gong" : "\u6760\u5de5\u653b\u529f\u606d\u9f9a\u4f9b\u8eac\u516c\u5bab\u5f13\u5de9\u6c5e\u62f1\u8d21\u5171\u857b\u5efe\u54a3\u73d9\u80b1\u86a3\u86e9\u89e5",
				"gao" : "\u7bd9\u768b\u9ad8\u818f\u7f94\u7cd5\u641e\u9550\u7a3f\u544a\u777e\u8bf0\u90dc\u84bf\u85c1\u7f1f\u69d4\u69c1\u6772\u9506",
				"ge" : "\u54e5\u6b4c\u6401\u6208\u9e3d\u80f3\u7599\u5272\u9769\u845b\u683c\u9601\u9694\u94ec\u4e2a\u5404\u9b32\u4ee1\u54ff\u5865\u55dd\u7ea5\u643f\u8188\u784c\u94ea\u9549\u88bc\u988c\u867c\u8238\u9abc\u9ac2",
				"gei" : "\u7ed9",
				"gen" : "\u6839\u8ddf\u4e98\u831b\u54cf\u826e",
				"geng" : "\u8015\u66f4\u5e9a\u7fb9\u57c2\u803f\u6897\u54fd\u8d53\u9ca0",
				"gou" : "\u94a9\u52fe\u6c9f\u82df\u72d7\u57a2\u6784\u8d2d\u591f\u4f5d\u8bdf\u5ca3\u9058\u5abe\u7f11\u89cf\u5f40\u9e32\u7b31\u7bdd\u97b2",
				"gu" : "\u8f9c\u83c7\u5495\u7b8d\u4f30\u6cbd\u5b64\u59d1\u9f13\u53e4\u86ca\u9aa8\u8c37\u80a1\u6545\u987e\u56fa\u96c7\u560f\u8bc2\u83f0\u54cc\u5d2e\u6c69\u688f\u8f71\u726f\u727f\u80cd\u81cc\u6bc2\u77bd\u7f5f\u94b4\u9522\u74e0\u9e2a\u9e44\u75fc\u86c4\u9164\u89da\u9cb4\u9ab0\u9e58",
				"gua" : "\u522e\u74dc\u5250\u5be1\u6302\u8902\u5366\u8bd6\u5471\u681d\u9e39",
				"guai" : "\u4e56\u62d0\u602a\u54d9",
				"guan" : "\u68fa\u5173\u5b98\u51a0\u89c2\u7ba1\u9986\u7f50\u60ef\u704c\u8d2f\u500c\u839e\u63bc\u6dab\u76e5\u9e73\u9ccf",
				"guang" : "\u5149\u5e7f\u901b\u72b7\u6844\u80f1\u7592",
				"gui" : "\u7470\u89c4\u572d\u7845\u5f52\u9f9f\u95fa\u8f68\u9b3c\u8be1\u7678\u6842\u67dc\u8dea\u8d35\u523d\u5326\u523f\u5e8b\u5b84\u59ab\u6867\u7085\u6677\u7688\u7c0b\u9c91\u9cdc",
				"gun" : "\u8f8a\u6eda\u68cd\u4e28\u886e\u7ef2\u78d9\u9ca7",
				"guo" : "\u9505\u90ed\u56fd\u679c\u88f9\u8fc7\u9998\u8803\u57da\u63b4\u5459\u56d7\u5e3c\u5d1e\u7313\u6901\u8662\u951e\u8052\u872e\u873e\u8748",
				"ha" : "\u54c8",
				"hai" : "\u9ab8\u5b69\u6d77\u6c26\u4ea5\u5bb3\u9a87\u54b4\u55e8\u988f\u91a2",
				"han" : "\u9163\u61a8\u90af\u97e9\u542b\u6db5\u5bd2\u51fd\u558a\u7f55\u7ff0\u64bc\u634d\u65f1\u61be\u608d\u710a\u6c57\u6c49\u9097\u83e1\u6496\u961a\u701a\u6657\u7113\u9894\u86b6\u9f3e",
				"hen" : "\u592f\u75d5\u5f88\u72e0\u6068",
				"hang" : "\u676d\u822a\u6c86\u7ed7\u73e9\u6841",
				"hao" : "\u58d5\u568e\u8c6a\u6beb\u90dd\u597d\u8017\u53f7\u6d69\u8585\u55e5\u5686\u6fe0\u704f\u660a\u7693\u98a2\u869d",
				"he" : "\u5475\u559d\u8377\u83cf\u6838\u79be\u548c\u4f55\u5408\u76d2\u8c89\u9602\u6cb3\u6db8\u8d6b\u8910\u9e64\u8d3a\u8bc3\u52be\u58d1\u85ff\u55d1\u55ec\u9616\u76cd\u86b5\u7fee",
				"hei" : "\u563f\u9ed1",
				"heng" : "\u54fc\u4ea8\u6a2a\u8861\u6052\u8a07\u8605",
				"hong" : "\u8f70\u54c4\u70d8\u8679\u9e3f\u6d2a\u5b8f\u5f18\u7ea2\u9ec9\u8ba7\u836d\u85a8\u95f3\u6cd3",
				"hou" : "\u5589\u4faf\u7334\u543c\u539a\u5019\u540e\u5820\u5f8c\u9005\u760a\u7bcc\u7cc7\u9c8e\u9aba",
				"hu" : "\u547c\u4e4e\u5ffd\u745a\u58f6\u846b\u80e1\u8774\u72d0\u7cca\u6e56\u5f27\u864e\u552c\u62a4\u4e92\u6caa\u6237\u51b1\u553f\u56eb\u5cb5\u7322\u6019\u60da\u6d52\u6ef9\u7425\u69f2\u8f77\u89f3\u70c0\u7173\u623d\u6248\u795c\u9e55\u9e71\u7b0f\u9190\u659b",
				"hua" : "\u82b1\u54d7\u534e\u733e\u6ed1\u753b\u5212\u5316\u8bdd\u5290\u6d4d\u9a85\u6866\u94e7\u7a1e",
				"huai" : "\u69d0\u5f8a\u6000\u6dee\u574f\u8fd8\u8e1d",
				"huan" : "\u6b22\u73af\u6853\u7f13\u6362\u60a3\u5524\u75ea\u8c62\u7115\u6da3\u5ba6\u5e7b\u90c7\u5942\u57b8\u64d0\u571c\u6d39\u6d63\u6f36\u5bf0\u902d\u7f33\u953e\u9ca9\u9b1f",
				"huang" : "\u8352\u614c\u9ec4\u78fa\u8757\u7c27\u7687\u51f0\u60f6\u714c\u6643\u5e4c\u604d\u8c0e\u968d\u5fa8\u6e5f\u6f62\u9051\u749c\u8093\u7640\u87e5\u7bc1\u9cc7",
				"hui" : "\u7070\u6325\u8f89\u5fbd\u6062\u86d4\u56de\u6bc1\u6094\u6167\u5349\u60e0\u6666\u8d3f\u79fd\u4f1a\u70e9\u6c47\u8bb3\u8bf2\u7ed8\u8bd9\u8334\u835f\u8559\u54d5\u5599\u96b3\u6d04\u5f57\u7f0b\u73f2\u6656\u605a\u867a\u87ea\u9ebe",
				"hun" : "\u8364\u660f\u5a5a\u9b42\u6d51\u6df7\u8be8\u9984\u960d\u6eb7\u7f17",
				"huo" : "\u8c41\u6d3b\u4f19\u706b\u83b7\u6216\u60d1\u970d\u8d27\u7978\u6509\u56af\u5925\u94ac\u952a\u956c\u8020\u8816",
				"ji" : "\u51fb\u573e\u57fa\u673a\u7578\u7a3d\u79ef\u7b95\u808c\u9965\u8ff9\u6fc0\u8ba5\u9e21\u59ec\u7ee9\u7f09\u5409\u6781\u68d8\u8f91\u7c4d\u96c6\u53ca\u6025\u75be\u6c72\u5373\u5ac9\u7ea7\u6324\u51e0\u810a\u5df1\u84df\u6280\u5180\u5b63\u4f0e\u796d\u5242\u60b8\u6d4e\u5bc4\u5bc2\u8ba1\u8bb0\u65e2\u5fcc\u9645\u5993\u7ee7\u7eaa\u5c45\u4e0c\u4e69\u525e\u4f76\u4f74\u8114\u58bc\u82a8\u82b0\u8401\u84ba\u857a\u638e\u53fd\u54ad\u54dc\u5527\u5c8c\u5d74\u6d0e\u5f50\u5c50\u9aa5\u757f\u7391\u696b\u6b9b\u621f\u6222\u8d4d\u89ca\u7284\u9f51\u77f6\u7f81\u5d47\u7a37\u7620\u7635\u866e\u7b08\u7b04\u66a8\u8dfb\u8dfd\u9701\u9c9a\u9cab\u9afb\u9e82",
				"jia" : "\u5609\u67b7\u5939\u4f73\u5bb6\u52a0\u835a\u988a\u8d3e\u7532\u94be\u5047\u7a3c\u4ef7\u67b6\u9a7e\u5ac1\u4f3d\u90cf\u62ee\u5cac\u6d43\u8fe6\u73c8\u621b\u80db\u605d\u94d7\u9553\u75c2\u86f1\u7b33\u8888\u8dcf",
				"jian" : "\u6b7c\u76d1\u575a\u5c16\u7b3a\u95f4\u714e\u517c\u80a9\u8270\u5978\u7f04\u8327\u68c0\u67ec\u78b1\u7877\u62e3\u6361\u7b80\u4fed\u526a\u51cf\u8350\u69db\u9274\u8df5\u8d31\u89c1\u952e\u7bad\u4ef6\u5065\u8230\u5251\u996f\u6e10\u6e85\u6da7\u5efa\u50ed\u8c0f\u8c2b\u83c5\u84b9\u641b\u56dd\u6e54\u8e47\u8b07\u7f23\u67a7\u67d9\u6957\u620b\u622c\u726e\u728d\u6bfd\u8171\u7751\u950f\u9e63\u88e5\u7b15\u7bb4\u7fe6\u8dbc\u8e3a\u9ca3\u97af",
				"jiang" : "\u50f5\u59dc\u5c06\u6d46\u6c5f\u7586\u848b\u6868\u5956\u8bb2\u5320\u9171\u964d\u8333\u6d1a\u7edb\u7f30\u729f\u7913\u8029\u7ce8\u8c47",
				"jiao" : "\u8549\u6912\u7901\u7126\u80f6\u4ea4\u90ca\u6d47\u9a84\u5a07\u56bc\u6405\u94f0\u77eb\u4fa5\u811a\u72e1\u89d2\u997a\u7f34\u7ede\u527f\u6559\u9175\u8f7f\u8f83\u53eb\u4f7c\u50ec\u832d\u6322\u564d\u5ce4\u5fbc\u59e3\u7e9f\u656b\u768e\u9e6a\u86df\u91ae\u8de4\u9c9b",
				"jie" : "\u7a96\u63ed\u63a5\u7686\u79f8\u8857\u9636\u622a\u52ab\u8282\u6854\u6770\u6377\u776b\u7aed\u6d01\u7ed3\u89e3\u59d0\u6212\u85c9\u82a5\u754c\u501f\u4ecb\u75a5\u8beb\u5c4a\u5048\u8ba6\u8bd8\u5588\u55df\u736c\u5a55\u5b51\u6840\u7352\u78a3\u9534\u7596\u88b7\u9889\u86a7\u7faf\u9c92\u9ab1\u9aeb",
				"jin" : "\u5dfe\u7b4b\u65a4\u91d1\u4eca\u6d25\u895f\u7d27\u9526\u4ec5\u8c28\u8fdb\u9773\u664b\u7981\u8fd1\u70ec\u6d78\u5c3d\u537a\u8369\u5807\u5664\u9991\u5ed1\u5997\u7f19\u747e\u69ff\u8d46\u89d0\u9485\u9513\u887f\u77dc",
				"jing" : "\u52b2\u8346\u5162\u830e\u775b\u6676\u9cb8\u4eac\u60ca\u7cbe\u7cb3\u7ecf\u4e95\u8b66\u666f\u9888\u9759\u5883\u656c\u955c\u5f84\u75c9\u9756\u7adf\u7ade\u51c0\u522d\u5106\u9631\u83c1\u734d\u61ac\u6cfe\u8ff3\u5f2a\u5a67\u80bc\u80eb\u8148\u65cc",
				"jiong" : "\u70af\u7a98\u5182\u8fe5\u6243",
				"jiu" : "\u63ea\u7a76\u7ea0\u7396\u97ed\u4e45\u7078\u4e5d\u9152\u53a9\u6551\u65e7\u81fc\u8205\u548e\u5c31\u759a\u50e6\u557e\u9604\u67e9\u6855\u9e6b\u8d73\u9b0f",
				"ju" : "\u97a0\u62d8\u72d9\u75bd\u9a79\u83ca\u5c40\u5480\u77e9\u4e3e\u6cae\u805a\u62d2\u636e\u5de8\u5177\u8ddd\u8e1e\u952f\u4ff1\u53e5\u60e7\u70ac\u5267\u5028\u8bb5\u82e3\u82f4\u8392\u63ac\u907d\u5c66\u741a\u67b8\u6910\u6998\u6989\u6a58\u728b\u98d3\u949c\u9514\u7aad\u88fe\u8d84\u91b5\u8e3d\u9f83\u96ce\u97ab",
				"juan" : "\u6350\u9e43\u5a1f\u5026\u7737\u5377\u7ee2\u9104\u72f7\u6d93\u684a\u8832\u9529\u954c\u96bd",
				"jue" : "\u6485\u652b\u6289\u6398\u5014\u7235\u89c9\u51b3\u8bc0\u7edd\u53a5\u5282\u8c32\u77cd\u8568\u5658\u5d1b\u7357\u5b53\u73cf\u6877\u6a5b\u721d\u9562\u8e76\u89d6",
				"jun" : "\u5747\u83cc\u94a7\u519b\u541b\u5cfb\u4fca\u7ae3\u6d5a\u90e1\u9a8f\u6343\u72fb\u76b2\u7b60\u9e87",
				"ka" : "\u5580\u5496\u5361\u4f67\u5494\u80e9",
				"ke" : "\u54af\u5777\u82db\u67ef\u68f5\u78d5\u9897\u79d1\u58f3\u54b3\u53ef\u6e34\u514b\u523b\u5ba2\u8bfe\u5ca2\u606a\u6e98\u9a92\u7f02\u73c2\u8f72\u6c2a\u778c\u94b6\u75b4\u7aa0\u874c\u9ac1",
				"kai" : "\u5f00\u63e9\u6977\u51ef\u6168\u5240\u57b2\u8488\u5ffe\u607a\u94e0\u950e",
				"kan" : "\u520a\u582a\u52d8\u574e\u780d\u770b\u4f83\u51f5\u83b0\u83b6\u6221\u9f9b\u77b0",
				"kang" : "\u5eb7\u6177\u7ce0\u625b\u6297\u4ea2\u7095\u5751\u4f09\u95f6\u94aa",
				"kao" : "\u8003\u62f7\u70e4\u9760\u5c3b\u6832\u7292\u94d0",
				"ken" : "\u80af\u5543\u57a6\u6073\u57a0\u88c9\u9880",
				"keng" : "\u542d\u5fd0\u94ff",
				"kong" : "\u7a7a\u6050\u5b54\u63a7\u5025\u5d06\u7b9c",
				"kou" : "\u62a0\u53e3\u6263\u5bc7\u82a4\u853b\u53e9\u770d\u7b58",
				"ku" : "\u67af\u54ed\u7a9f\u82e6\u9177\u5e93\u88e4\u5233\u5800\u55be\u7ed4\u9ab7",
				"kua" : "\u5938\u57ae\u630e\u8de8\u80ef\u4f89",
				"kuai" : "\u5757\u7b77\u4fa9\u5feb\u84af\u90d0\u8489\u72ef\u810d",
				"kuan" : "\u5bbd\u6b3e\u9acb",
				"kuang" : "\u5321\u7b50\u72c2\u6846\u77ff\u7736\u65f7\u51b5\u8bd3\u8bf3\u909d\u5739\u593c\u54d0\u7ea9\u8d36",
				"kui" : "\u4e8f\u76d4\u5cbf\u7aa5\u8475\u594e\u9b41\u5080\u9988\u6127\u6e83\u9997\u532e\u5914\u9697\u63c6\u55b9\u559f\u609d\u6126\u9615\u9035\u668c\u777d\u8069\u8770\u7bd1\u81fe\u8dec",
				"kun" : "\u5764\u6606\u6346\u56f0\u6083\u9603\u7428\u951f\u918c\u9cb2\u9ae1",
				"kuo" : "\u62ec\u6269\u5ed3\u9614\u86de",
				"la" : "\u5783\u62c9\u5587\u8721\u814a\u8fa3\u5566\u524c\u647a\u908b\u65ef\u782c\u760c",
				"lai" : "\u83b1\u6765\u8d56\u5d03\u5f95\u6d9e\u6fd1\u8d49\u7750\u94fc\u765e\u7c41",
				"lan" : "\u84dd\u5a6a\u680f\u62e6\u7bee\u9611\u5170\u6f9c\u8c30\u63fd\u89c8\u61d2\u7f06\u70c2\u6ee5\u5549\u5c9a\u61d4\u6f24\u6984\u6593\u7f71\u9567\u8934",
				"lang" : "\u7405\u6994\u72fc\u5eca\u90ce\u6717\u6d6a\u83a8\u8497\u5577\u9606\u9512\u7a02\u8782",
				"lao" : "\u635e\u52b3\u7262\u8001\u4f6c\u59e5\u916a\u70d9\u6d9d\u5520\u5d02\u6833\u94d1\u94f9\u75e8\u91aa",
				"le" : "\u52d2\u4e50\u808b\u4ec2\u53fb\u561e\u6cd0\u9cd3",
				"lei" : "\u96f7\u956d\u857e\u78ca\u7d2f\u5121\u5792\u64c2\u7c7b\u6cea\u7fb8\u8bd4\u837d\u54a7\u6f2f\u5ad8\u7f27\u6a91\u8012\u9179",
				"ling" : "\u68f1\u51b7\u62ce\u73b2\u83f1\u96f6\u9f84\u94c3\u4f36\u7f9a\u51cc\u7075\u9675\u5cad\u9886\u53e6\u4ee4\u9143\u5844\u82d3\u5464\u56f9\u6ce0\u7eeb\u67c3\u68c2\u74f4\u8046\u86c9\u7fce\u9cae",
				"leng" : "\u695e\u6123",
				"li" : "\u5398\u68a8\u7281\u9ece\u7bf1\u72f8\u79bb\u6f13\u7406\u674e\u91cc\u9ca4\u793c\u8389\u8354\u540f\u6817\u4e3d\u5389\u52b1\u783e\u5386\u5229\u5088\u4f8b\u4fd0\u75e2\u7acb\u7c92\u6ca5\u96b6\u529b\u7483\u54e9\u4fea\u4fda\u90e6\u575c\u82c8\u8385\u84e0\u85dc\u6369\u5456\u5533\u55b1\u7301\u6ea7\u6fa7\u9026\u5a0c\u5ae0\u9a8a\u7f21\u73de\u67a5\u680e\u8f79\u623e\u783a\u8a48\u7f79\u9502\u9e42\u75a0\u75ac\u86ce\u870a\u8821\u7b20\u7be5\u7c9d\u91b4\u8dde\u96f3\u9ca1\u9ce2\u9ee7",
				"lian" : "\u4fe9\u8054\u83b2\u8fde\u9570\u5ec9\u601c\u6d9f\u5e18\u655b\u8138\u94fe\u604b\u70bc\u7ec3\u631b\u8539\u5941\u6f4b\u6fc2\u5a08\u740f\u695d\u6b93\u81c1\u81a6\u88e2\u880a\u9ca2",
				"liang" : "\u7cae\u51c9\u6881\u7cb1\u826f\u4e24\u8f86\u91cf\u667e\u4eae\u8c05\u589a\u690b\u8e09\u9753\u9b49",
				"liao" : "\u64a9\u804a\u50da\u7597\u71ce\u5be5\u8fbd\u6f66\u4e86\u6482\u9563\u5ed6\u6599\u84fc\u5c25\u5639\u7360\u5bee\u7f2d\u948c\u9e69\u8022",
				"lie" : "\u5217\u88c2\u70c8\u52a3\u730e\u51bd\u57d2\u6d0c\u8d94\u8e90\u9b23",
				"lin" : "\u7433\u6797\u78f7\u9716\u4e34\u90bb\u9cde\u6dcb\u51db\u8d41\u541d\u853a\u5d99\u5eea\u9074\u6aa9\u8f9a\u77b5\u7cbc\u8e8f\u9e9f",
				"liu" : "\u6e9c\u7409\u69b4\u786b\u998f\u7559\u5218\u7624\u6d41\u67f3\u516d\u62a1\u507b\u848c\u6cd6\u6d4f\u905b\u9a9d\u7efa\u65d2\u7198\u950d\u954f\u9e68\u938f",
				"long" : "\u9f99\u804b\u5499\u7b3c\u7abf\u9686\u5784\u62e2\u9647\u5f04\u5785\u830f\u6cf7\u73d1\u680a\u80e7\u783b\u7643",
				"lou" : "\u697c\u5a04\u6402\u7bd3\u6f0f\u964b\u55bd\u5d5d\u9542\u7618\u8027\u877c\u9ac5",
				"lu" : "\u82a6\u5362\u9885\u5e90\u7089\u63b3\u5364\u864f\u9c81\u9e93\u788c\u9732\u8def\u8d42\u9e7f\u6f5e\u7984\u5f55\u9646\u622e\u5786\u6445\u64b8\u565c\u6cf8\u6e0c\u6f09\u7490\u680c\u6a79\u8f73\u8f82\u8f98\u6c07\u80ea\u9565\u9e2c\u9e6d\u7c0f\u823b\u9c88",
				"lv" : "\u9a74\u5415\u94dd\u4fa3\u65c5\u5c65\u5c61\u7f15\u8651\u6c2f\u5f8b\u7387\u6ee4\u7eff\u634b\u95fe\u6988\u8182\u7a06\u891b",
				"luan" : "\u5ce6\u5b6a\u6ee6\u5375\u4e71\u683e\u9e3e\u92ae",
				"lue" : "\u63a0\u7565\u950a",
				"lun" : "\u8f6e\u4f26\u4ed1\u6ca6\u7eb6\u8bba\u56f5",
				"luo" : "\u841d\u87ba\u7f57\u903b\u9523\u7ba9\u9aa1\u88f8\u843d\u6d1b\u9a86\u7edc\u502e\u8366\u645e\u7321\u6cfa\u6924\u8136\u9559\u7630\u96d2",
				"ma" : "\u5988\u9ebb\u739b\u7801\u8682\u9a6c\u9a82\u561b\u5417\u551b\u72b8\u5b37\u6769\u9ebd",
				"mai" : "\u57cb\u4e70\u9ea6\u5356\u8fc8\u8109\u52a2\u836c\u54aa\u973e",
				"man" : "\u7792\u9992\u86ee\u6ee1\u8513\u66fc\u6162\u6f2b\u8c29\u5881\u5e54\u7f26\u71b3\u9558\u989f\u87a8\u9cd7\u9794",
				"mang" : "\u8292\u832b\u76f2\u5fd9\u83bd\u9099\u6f2d\u6726\u786d\u87d2",
				"meng" : "\u6c13\u840c\u8499\u6aac\u76df\u9530\u731b\u68a6\u5b5f\u52d0\u750d\u77a2\u61f5\u791e\u867b\u8722\u8813\u824b\u8268\u9efe",
				"miao" : "\u732b\u82d7\u63cf\u7784\u85d0\u79d2\u6e3a\u5e99\u5999\u55b5\u9088\u7f08\u7f2a\u676a\u6dfc\u7707\u9e4b\u8731",
				"mao" : "\u8305\u951a\u6bdb\u77db\u94c6\u536f\u8302\u5192\u5e3d\u8c8c\u8d38\u4f94\u88a4\u52d6\u8306\u5cc1\u7441\u6634\u7266\u8004\u65c4\u61cb\u7780\u86d1\u8765\u87ca\u9ae6",
				"me" : "\u4e48",
				"mei" : "\u73ab\u679a\u6885\u9176\u9709\u7164\u6ca1\u7709\u5a92\u9541\u6bcf\u7f8e\u6627\u5bd0\u59b9\u5a9a\u5776\u8393\u5d4b\u7338\u6d7c\u6e44\u6963\u9545\u9e5b\u8882\u9b45",
				"men" : "\u95e8\u95f7\u4eec\u626a\u739f\u7116\u61d1\u9494",
				"mi" : "\u772f\u919a\u9761\u7cdc\u8ff7\u8c1c\u5f25\u7c73\u79d8\u89c5\u6ccc\u871c\u5bc6\u5e42\u8288\u5196\u8c27\u863c\u5627\u7315\u736f\u6c68\u5b93\u5f2d\u8112\u6549\u7cf8\u7e3b\u9e8b",
				"mian" : "\u68c9\u7720\u7ef5\u5195\u514d\u52c9\u5a29\u7f05\u9762\u6c94\u6e4e\u817c\u7704",
				"mie" : "\u8511\u706d\u54a9\u881b\u7bfe",
				"min" : "\u6c11\u62bf\u76bf\u654f\u60af\u95fd\u82e0\u5cb7\u95f5\u6cef\u73c9",
				"ming" : "\u660e\u879f\u9e23\u94ed\u540d\u547d\u51a5\u8317\u6e9f\u669d\u7791\u9169",
				"miu" : "\u8c2c",
				"mo" : "\u6478\u6479\u8611\u6a21\u819c\u78e8\u6469\u9b54\u62b9\u672b\u83ab\u58a8\u9ed8\u6cab\u6f20\u5bde\u964c\u8c1f\u8309\u84e6\u998d\u5aeb\u9546\u79e3\u763c\u8031\u87c6\u8c8a\u8c98",
				"mou" : "\u8c0b\u725f\u67d0\u53b6\u54de\u5a7a\u7738\u936a",
				"mu" : "\u62c7\u7261\u4ea9\u59c6\u6bcd\u5893\u66ae\u5e55\u52df\u6155\u6728\u76ee\u7766\u7267\u7a46\u4eeb\u82dc\u5452\u6c90\u6bea\u94bc",
				"na" : "\u62ff\u54ea\u5450\u94a0\u90a3\u5a1c\u7eb3\u5185\u637a\u80ad\u954e\u8872\u7bac",
				"nai" : "\u6c16\u4e43\u5976\u8010\u5948\u9f10\u827f\u8418\u67f0",
				"nan" : "\u5357\u7537\u96be\u56ca\u5583\u56e1\u6960\u8169\u877b\u8d67",
				"nao" : "\u6320\u8111\u607c\u95f9\u5b6c\u57b4\u7331\u7459\u7847\u94d9\u86f2",
				"ne" : "\u6dd6\u5462\u8bb7",
				"nei" : "\u9981",
				"nen" : "\u5ae9\u80fd\u6798\u6041",
				"ni" : "\u59ae\u9713\u502a\u6ce5\u5c3c\u62df\u4f60\u533f\u817b\u9006\u6eba\u4f32\u576d\u730a\u6029\u6ee0\u6635\u65ce\u7962\u615d\u7768\u94cc\u9cb5",
				"nian" : "\u852b\u62c8\u5e74\u78be\u64b5\u637b\u5ff5\u5eff\u8f87\u9ecf\u9c87\u9cb6",
				"niang" : "\u5a18\u917f",
				"niao" : "\u9e1f\u5c3f\u8311\u5b32\u8132\u8885",
				"nie" : "\u634f\u8042\u5b7d\u556e\u954a\u954d\u6d85\u4e5c\u9667\u8616\u55eb\u8080\u989e\u81ec\u8e51",
				"nin" : "\u60a8\u67e0",
				"ning" : "\u72de\u51dd\u5b81\u62e7\u6cde\u4f5e\u84e5\u549b\u752f\u804d",
				"niu" : "\u725b\u626d\u94ae\u7ebd\u72c3\u5ff8\u599e\u86b4",
				"nong" : "\u8113\u6d53\u519c\u4fac",
				"nu" : "\u5974\u52aa\u6012\u5476\u5e11\u5f29\u80ec\u5b65\u9a7d",
				"nv" : "\u5973\u6067\u9495\u8844",
				"nuan" : "\u6696",
				"nuenue" : "\u8650",
				"nue" : "\u759f\u8c11",
				"nuo" : "\u632a\u61e6\u7cef\u8bfa\u50a9\u6426\u558f\u9518",
				"ou" : "\u54e6\u6b27\u9e25\u6bb4\u85d5\u5455\u5076\u6ca4\u6004\u74ef\u8026",
				"pa" : "\u556a\u8db4\u722c\u5e15\u6015\u7436\u8469\u7b62",
				"pai" : "\u62cd\u6392\u724c\u5f98\u6e43\u6d3e\u4ff3\u848e",
				"pan" : "\u6500\u6f58\u76d8\u78d0\u76fc\u7554\u5224\u53db\u723f\u6cee\u88a2\u897b\u87e0\u8e52",
				"pang" : "\u4e53\u5e9e\u65c1\u802a\u80d6\u6ec2\u9004",
				"pao" : "\u629b\u5486\u5228\u70ae\u888d\u8dd1\u6ce1\u530f\u72cd\u5e96\u812c\u75b1",
				"pei" : "\u5478\u80da\u57f9\u88f4\u8d54\u966a\u914d\u4f69\u6c9b\u638a\u8f94\u5e14\u6de0\u65c6\u952b\u9185\u9708",
				"pen" : "\u55b7\u76c6\u6e53",
				"peng" : "\u7830\u62a8\u70f9\u6f8e\u5f6d\u84ec\u68da\u787c\u7bf7\u81a8\u670b\u9e4f\u6367\u78b0\u576f\u580b\u562d\u6026\u87db",
				"pi" : "\u7812\u9739\u6279\u62ab\u5288\u7435\u6bd7\u5564\u813e\u75b2\u76ae\u5339\u75de\u50fb\u5c41\u8b6c\u4e15\u9674\u90b3\u90eb\u572e\u9f19\u64d7\u567c\u5e80\u5ab2\u7eb0\u6787\u7513\u7765\u7f74\u94cd\u75e6\u7656\u758b\u868d\u8c94",
				"pian" : "\u7bc7\u504f\u7247\u9a97\u8c1d\u9a88\u728f\u80fc\u890a\u7fe9\u8e41",
				"piao" : "\u98d8\u6f02\u74e2\u7968\u527d\u560c\u5ad6\u7f25\u6b8d\u779f\u87b5",
				"pie" : "\u6487\u77a5\u4e3f\u82e4\u6c15",
				"pin" : "\u62fc\u9891\u8d2b\u54c1\u8058\u62da\u59d8\u5ad4\u6980\u725d\u98a6",
				"ping" : "\u4e52\u576a\u82f9\u840d\u5e73\u51ed\u74f6\u8bc4\u5c4f\u4fdc\u5a09\u67b0\u9c86",
				"po" : "\u5761\u6cfc\u9887\u5a46\u7834\u9b44\u8feb\u7c95\u53f5\u9131\u6ea5\u73c0\u948b\u94b7\u76a4\u7b38",
				"pou" : "\u5256\u88d2\u8e23",
				"pu" : "\u6251\u94fa\u4ec6\u8386\u8461\u83e9\u84b2\u57d4\u6734\u5703\u666e\u6d66\u8c31\u66dd\u7011\u530d\u5657\u6fee\u749e\u6c06\u9564\u9568\u8e7c",
				"qi" : "\u671f\u6b3a\u6816\u621a\u59bb\u4e03\u51c4\u6f06\u67d2\u6c8f\u5176\u68cb\u5947\u6b67\u7566\u5d0e\u8110\u9f50\u65d7\u7948\u7941\u9a91\u8d77\u5c82\u4e5e\u4f01\u542f\u5951\u780c\u5668\u6c14\u8fc4\u5f03\u6c7d\u6ce3\u8bab\u4e9f\u4e93\u573b\u8291\u840b\u847a\u5601\u5c7a\u5c90\u6c54\u6dc7\u9a90\u7eee\u742a\u7426\u675e\u6864\u69ed\u6b39\u797a\u61a9\u789b\u86f4\u871e\u7da6\u7dae\u8dbf\u8e4a\u9ccd\u9e92",
				"qia" : "\u6390\u6070\u6d3d\u845c",
				"qian" : "\u7275\u6266\u948e\u94c5\u5343\u8fc1\u7b7e\u4edf\u8c26\u4e7e\u9ed4\u94b1\u94b3\u524d\u6f5c\u9063\u6d45\u8c34\u5811\u5d4c\u6b20\u6b49\u4f65\u9621\u828a\u82a1\u8368\u63ae\u5c8d\u60ad\u614a\u9a9e\u6434\u8930\u7f31\u6920\u80b7\u6106\u94a4\u8654\u7b9d",
				"qiang" : "\u67aa\u545b\u8154\u7f8c\u5899\u8537\u5f3a\u62a2\u5af1\u6a2f\u6217\u709d\u9516\u9535\u956a\u8941\u8723\u7f9f\u8deb\u8dc4",
				"qiao" : "\u6a47\u9539\u6572\u6084\u6865\u77a7\u4e54\u4fa8\u5de7\u9798\u64ac\u7fd8\u5ced\u4fcf\u7a8d\u5281\u8bee\u8c2f\u835e\u6100\u6194\u7f32\u6a35\u6bf3\u7857\u8df7\u9792",
				"qie" : "\u5207\u8304\u4e14\u602f\u7a83\u90c4\u553c\u60ec\u59be\u6308\u9532\u7ba7",
				"qin" : "\u94a6\u4fb5\u4eb2\u79e6\u7434\u52e4\u82b9\u64d2\u79bd\u5bdd\u6c81\u82a9\u84c1\u8572\u63ff\u5423\u55ea\u5659\u6eb1\u6a8e\u8793\u887e",
				"qing" : "\u9752\u8f7b\u6c22\u503e\u537f\u6e05\u64ce\u6674\u6c30\u60c5\u9877\u8bf7\u5e86\u5029\u82d8\u570a\u6aa0\u78ec\u873b\u7f44\u7b90\u8b26\u9cad\u9ee5",
				"qiong" : "\u743c\u7a77\u909b\u8315\u7a79\u7b47\u928e",
				"qiu" : "\u79cb\u4e18\u90b1\u7403\u6c42\u56da\u914b\u6cc5\u4fc5\u6c3d\u5def\u827d\u72b0\u6e6b\u9011\u9052\u6978\u8d47\u9e20\u866c\u86af\u8764\u88d8\u7cd7\u9cc5\u9f3d",
				"qu" : "\u8d8b\u533a\u86c6\u66f2\u8eaf\u5c48\u9a71\u6e20\u53d6\u5a36\u9f8b\u8da3\u53bb\u8bce\u52ac\u8556\u8627\u5c96\u8862\u9612\u74a9\u89d1\u6c0d\u795b\u78f2\u766f\u86d0\u883c\u9eb4\u77bf\u9ee2",
				"quan" : "\u5708\u98a7\u6743\u919b\u6cc9\u5168\u75ca\u62f3\u72ac\u5238\u529d\u8be0\u8343\u737e\u609b\u7efb\u8f81\u754e\u94e8\u8737\u7b4c\u9b08",
				"que" : "\u7f3a\u7094\u7638\u5374\u9e4a\u69b7\u786e\u96c0\u9619\u60ab",
				"qun" : "\u88d9\u7fa4\u9021",
				"ran" : "\u7136\u71c3\u5189\u67d3\u82d2\u9aef",
				"rang" : "\u74e4\u58e4\u6518\u56b7\u8ba9\u79b3\u7a70",
				"rao" : "\u9976\u6270\u7ed5\u835b\u5a06\u6861",
				"ruo" : "\u60f9\u82e5\u5f31",
				"re" : "\u70ed\u504c",
				"ren" : "\u58ec\u4ec1\u4eba\u5fcd\u97e7\u4efb\u8ba4\u5203\u598a\u7eab\u4ede\u834f\u845a\u996a\u8f6b\u7a14\u887d",
				"reng" : "\u6254\u4ecd",
				"ri" : "\u65e5",
				"rong" : "\u620e\u8338\u84c9\u8363\u878d\u7194\u6eb6\u5bb9\u7ed2\u5197\u5d58\u72e8\u7f1b\u6995\u877e",
				"rou" : "\u63c9\u67d4\u8089\u7cc5\u8e42\u97a3",
				"ru" : "\u8339\u8815\u5112\u5b7a\u5982\u8fb1\u4e73\u6c5d\u5165\u8925\u84d0\u85b7\u5685\u6d33\u6ebd\u6fe1\u94f7\u8966\u98a5",
				"ruan" : "\u8f6f\u962e\u670a",
				"rui" : "\u854a\u745e\u9510\u82ae\u8564\u777f\u868b",
				"run" : "\u95f0\u6da6",
				"sa" : "\u6492\u6d12\u8428\u5345\u4ee8\u6332\u98d2",
				"sai" : "\u816e\u9cc3\u585e\u8d5b\u567b",
				"san" : "\u4e09\u53c1\u4f1e\u6563\u5f61\u9993\u6c35\u6bf5\u7cc1\u9730",
				"sang" : "\u6851\u55d3\u4e27\u6421\u78c9\u98a1",
				"sao" : "\u6414\u9a9a\u626b\u5ac2\u57fd\u81ca\u7619\u9ccb",
				"se" : "\u745f\u8272\u6da9\u556c\u94e9\u94ef\u7a51",
				"sen" : "\u68ee",
				"seng" : "\u50e7",
				"sha" : "\u838e\u7802\u6740\u5239\u6c99\u7eb1\u50bb\u5565\u715e\u810e\u6b43\u75e7\u88df\u970e\u9ca8",
				"shai" : "\u7b5b\u6652\u917e",
				"shan" : "\u73ca\u82eb\u6749\u5c71\u5220\u717d\u886b\u95ea\u9655\u64c5\u8d61\u81b3\u5584\u6c55\u6247\u7f2e\u5261\u8baa\u912f\u57cf\u829f\u6f78\u59d7\u9a9f\u81bb\u9490\u759d\u87ee\u8222\u8dda\u9cdd",
				"shang" : "\u5892\u4f24\u5546\u8d4f\u664c\u4e0a\u5c1a\u88f3\u57a7\u7ef1\u6b87\u71b5\u89de",
				"shao" : "\u68a2\u634e\u7a0d\u70e7\u828d\u52fa\u97f6\u5c11\u54e8\u90b5\u7ecd\u52ad\u82d5\u6f72\u86f8\u7b24\u7b72\u8244",
				"she" : "\u5962\u8d4a\u86c7\u820c\u820d\u8d66\u6444\u5c04\u6151\u6d89\u793e\u8bbe\u538d\u4f58\u731e\u7572\u9e9d",
				"shen" : "\u7837\u7533\u547b\u4f38\u8eab\u6df1\u5a20\u7ec5\u795e\u6c88\u5ba1\u5a76\u751a\u80be\u614e\u6e17\u8bdc\u8c02\u5432\u54c2\u6e16\u6939\u77e7\u8703",
				"sheng" : "\u58f0\u751f\u7525\u7272\u5347\u7ef3\u7701\u76db\u5269\u80dc\u5723\u4e1e\u6e11\u5ab5\u771a\u7b19",
				"shi" : "\u5e08\u5931\u72ee\u65bd\u6e7f\u8bd7\u5c38\u8671\u5341\u77f3\u62fe\u65f6\u4ec0\u98df\u8680\u5b9e\u8bc6\u53f2\u77e2\u4f7f\u5c4e\u9a76\u59cb\u5f0f\u793a\u58eb\u4e16\u67ff\u4e8b\u62ed\u8a93\u901d\u52bf\u662f\u55dc\u566c\u9002\u4ed5\u4f8d\u91ca\u9970\u6c0f\u5e02\u6043\u5ba4\u89c6\u8bd5\u8c25\u57d8\u83b3\u84cd\u5f11\u5511\u9963\u8f7c\u8006\u8d33\u70bb\u793b\u94c8\u94ca\u87ab\u8210\u7b6e\u8c55\u9ca5\u9cba",
				"shou" : "\u6536\u624b\u9996\u5b88\u5bff\u6388\u552e\u53d7\u7626\u517d\u624c\u72e9\u7ef6\u824f",
				"shu" : "\u852c\u67a2\u68b3\u6b8a\u6292\u8f93\u53d4\u8212\u6dd1\u758f\u4e66\u8d4e\u5b70\u719f\u85af\u6691\u66d9\u7f72\u8700\u9ecd\u9f20\u5c5e\u672f\u8ff0\u6811\u675f\u620d\u7ad6\u5885\u5eb6\u6570\u6f31\u6055\u500f\u587e\u83fd\u5fc4\u6cad\u6d91\u6f8d\u59dd\u7ebe\u6bf9\u8167\u6bb3\u956f\u79eb\u9e6c",
				"shua" : "\u5237\u800d\u5530\u6dae",
				"shuai" : "\u6454\u8870\u7529\u5e05\u87c0",
				"shuan" : "\u6813\u62f4\u95e9",
				"shuang" : "\u971c\u53cc\u723d\u5b40",
				"shui" : "\u8c01\u6c34\u7761\u7a0e",
				"shun" : "\u542e\u77ac\u987a\u821c\u6042",
				"shuo" : "\u8bf4\u7855\u6714\u70c1\u84b4\u6420\u55cd\u6fef\u5981\u69ca\u94c4",
				"si" : "\u65af\u6495\u5636\u601d\u79c1\u53f8\u4e1d\u6b7b\u8086\u5bfa\u55e3\u56db\u4f3a\u4f3c\u9972\u5df3\u53ae\u4fdf\u5155\u83e5\u549d\u6c5c\u6cd7\u6f8c\u59d2\u9a77\u7f0c\u7940\u7960\u9536\u9e36\u801c\u86f3\u7b25",
				"song" : "\u677e\u8038\u6002\u9882\u9001\u5b8b\u8bbc\u8bf5\u51c7\u83d8\u5d27\u5d69\u5fea\u609a\u6dde\u7ae6",
				"sou" : "\u641c\u8258\u64de\u55fd\u53df\u55d6\u55fe\u998a\u6eb2\u98d5\u778d\u953c\u878b",
				"su" : "\u82cf\u9165\u4fd7\u7d20\u901f\u7c9f\u50f3\u5851\u6eaf\u5bbf\u8bc9\u8083\u5919\u8c21\u850c\u55c9\u612b\u7c0c\u89eb\u7a23",
				"suan" : "\u9178\u849c\u7b97",
				"sui" : "\u867d\u968b\u968f\u7ee5\u9ad3\u788e\u5c81\u7a57\u9042\u96a7\u795f\u84d1\u51ab\u8c07\u6fc9\u9083\u71e7\u772d\u7762",
				"sun" : "\u5b59\u635f\u7b0b\u836a\u72f2\u98e7\u69ab\u8de3\u96bc",
				"suo" : "\u68ad\u5506\u7f29\u7410\u7d22\u9501\u6240\u5522\u55e6\u5a11\u686b\u7743\u7fa7",
				"ta" : "\u584c\u4ed6\u5b83\u5979\u5854\u736d\u631e\u8e4b\u8e0f\u95fc\u6ebb\u9062\u69bb\u6c93",
				"tai" : "\u80ce\u82d4\u62ac\u53f0\u6cf0\u915e\u592a\u6001\u6c70\u90b0\u85b9\u80bd\u70b1\u949b\u8dc6\u9c90",
				"tan" : "\u574d\u644a\u8d2a\u762b\u6ee9\u575b\u6a80\u75f0\u6f6d\u8c2d\u8c08\u5766\u6bef\u8892\u78b3\u63a2\u53f9\u70ad\u90ef\u8548\u6619\u94bd\u952c\u8983",
				"tang" : "\u6c64\u5858\u642a\u5802\u68e0\u819b\u5510\u7cd6\u50a5\u9967\u6e8f\u746d\u94f4\u9557\u8025\u8797\u87b3\u7fb0\u91a3",
				"thang" : "\u5018\u8eba\u6dcc",
				"theng" : "\u8d9f\u70eb",
				"tao" : "\u638f\u6d9b\u6ed4\u7ee6\u8404\u6843\u9003\u6dd8\u9676\u8ba8\u5957\u6311\u9f17\u5555\u97ec\u9955",
				"te" : "\u7279",
				"teng" : "\u85e4\u817e\u75bc\u8a8a\u6ed5",
				"ti" : "\u68af\u5254\u8e22\u9511\u63d0\u9898\u8e44\u557c\u4f53\u66ff\u568f\u60d5\u6d95\u5243\u5c49\u8351\u608c\u9016\u7ee8\u7f07\u9e48\u88fc\u918d",
				"tian" : "\u5929\u6dfb\u586b\u7530\u751c\u606c\u8214\u8146\u63ad\u5fdd\u9617\u6b84\u754b\u94bf\u86ba",
				"tiao" : "\u6761\u8fe2\u773a\u8df3\u4f7b\u7967\u94eb\u7a95\u9f86\u9ca6",
				"tie" : "\u8d34\u94c1\u5e16\u841c\u992e",
				"ting" : "\u5385\u542c\u70c3\u6c40\u5ef7\u505c\u4ead\u5ead\u633a\u8247\u839b\u8476\u5a77\u6883\u8713\u9706",
				"tong" : "\u901a\u6850\u916e\u77b3\u540c\u94dc\u5f64\u7ae5\u6876\u6345\u7b52\u7edf\u75db\u4f5f\u50ee\u4edd\u833c\u55f5\u6078\u6f7c\u783c",
				"tou" : "\u5077\u6295\u5934\u900f\u4ea0",
				"tu" : "\u51f8\u79c3\u7a81\u56fe\u5f92\u9014\u6d82\u5c60\u571f\u5410\u5154\u580d\u837c\u83df\u948d\u9174",
				"tuan" : "\u6e4d\u56e2\u7583",
				"tui" : "\u63a8\u9893\u817f\u8715\u892a\u9000\u5fd2\u717a",
				"tun" : "\u541e\u5c6f\u81c0\u9968\u66be\u8c5a\u7a80",
				"tuo" : "\u62d6\u6258\u8131\u9e35\u9640\u9a6e\u9a7c\u692d\u59a5\u62d3\u553e\u4e47\u4f57\u5768\u5eb9\u6cb1\u67dd\u7823\u7ba8\u8204\u8dce\u9f0d",
				"wa" : "\u6316\u54c7\u86d9\u6d3c\u5a03\u74e6\u889c\u4f64\u5a32\u817d",
				"wai" : "\u6b6a\u5916",
				"wan" : "\u8c4c\u5f2f\u6e7e\u73a9\u987d\u4e38\u70f7\u5b8c\u7897\u633d\u665a\u7696\u60cb\u5b9b\u5a49\u4e07\u8155\u525c\u8284\u82cb\u83c0\u7ea8\u7efe\u742c\u8118\u7579\u873f\u7ba2",
				"wang" : "\u6c6a\u738b\u4ea1\u6789\u7f51\u5f80\u65fa\u671b\u5fd8\u5984\u7f54\u5c22\u60d8\u8f8b\u9b4d",
				"wei" : "\u5a01\u5dcd\u5fae\u5371\u97e6\u8fdd\u6845\u56f4\u552f\u60df\u4e3a\u6f4d\u7ef4\u82c7\u840e\u59d4\u4f1f\u4f2a\u5c3e\u7eac\u672a\u851a\u5473\u754f\u80c3\u5582\u9b4f\u4f4d\u6e2d\u8c13\u5c09\u6170\u536b\u502d\u504e\u8bff\u9688\u8473\u8587\u5e0f\u5e37\u5d34\u5d6c\u7325\u732c\u95f1\u6ca9\u6d27\u6da0\u9036\u5a13\u73ae\u97ea\u8ece\u709c\u7168\u71a8\u75ff\u8249\u9c94",
				"wen" : "\u761f\u6e29\u868a\u6587\u95fb\u7eb9\u543b\u7a33\u7d0a\u95ee\u520e\u6120\u960c\u6c76\u74ba\u97eb\u6b81\u96ef",
				"weng" : "\u55e1\u7fc1\u74ee\u84ca\u8579",
				"wo" : "\u631d\u8717\u6da1\u7a9d\u6211\u65a1\u5367\u63e1\u6c83\u83b4\u5e44\u6e25\u674c\u809f\u9f8c",
				"wu" : "\u5deb\u545c\u94a8\u4e4c\u6c61\u8bec\u5c4b\u65e0\u829c\u68a7\u543e\u5434\u6bcb\u6b66\u4e94\u6342\u5348\u821e\u4f0d\u4fae\u575e\u620a\u96fe\u6664\u7269\u52ff\u52a1\u609f\u8bef\u5140\u4ef5\u9622\u90ac\u572c\u82b4\u5e91\u6003\u5fe4\u6d6f\u5be4\u8fd5\u59a9\u9a9b\u727e\u7110\u9e49\u9e5c\u8708\u92c8\u9f2f",
				"xi" : "\u6614\u7199\u6790\u897f\u7852\u77fd\u6670\u563b\u5438\u9521\u727a\u7a00\u606f\u5e0c\u6089\u819d\u5915\u60dc\u7184\u70ef\u6eaa\u6c50\u7280\u6a84\u88ad\u5e2d\u4e60\u5ab3\u559c\u94e3\u6d17\u7cfb\u9699\u620f\u7ec6\u50d6\u516e\u96b0\u90d7\u831c\u8478\u84f0\u595a\u550f\u5f99\u9969\u960b\u6d60\u6dc5\u5c63\u5b09\u73ba\u6a28\u66e6\u89cb\u6b37\u71b9\u798a\u79a7\u94b8\u7699\u7a78\u8725\u87cb\u823e\u7fb2\u7c9e\u7fd5\u91af\u9f37",
				"xia" : "\u778e\u867e\u5323\u971e\u8f96\u6687\u5ce1\u4fa0\u72ed\u4e0b\u53a6\u590f\u5413\u6380\u846d\u55c4\u72ce\u9050\u7455\u7856\u7615\u7f45\u9ee0",
				"xian" : "\u9528\u5148\u4ed9\u9c9c\u7ea4\u54b8\u8d24\u8854\u8237\u95f2\u6d8e\u5f26\u5acc\u663e\u9669\u73b0\u732e\u53bf\u817a\u9985\u7fa1\u5baa\u9677\u9650\u7ebf\u51bc\u85d3\u5c98\u7303\u66b9\u5a34\u6c19\u7946\u9e47\u75eb\u86ac\u7b45\u7c7c\u9170\u8df9",
				"xiang" : "\u76f8\u53a2\u9576\u9999\u7bb1\u8944\u6e58\u4e61\u7fd4\u7965\u8be6\u60f3\u54cd\u4eab\u9879\u5df7\u6a61\u50cf\u5411\u8c61\u8297\u8459\u9977\u5ea0\u9aa7\u7f03\u87d3\u9c9e\u98e8",
				"xiao" : "\u8427\u785d\u9704\u524a\u54ee\u56a3\u9500\u6d88\u5bb5\u6dc6\u6653\u5c0f\u5b5d\u6821\u8096\u5578\u7b11\u6548\u54d3\u54bb\u5d24\u6f47\u900d\u9a81\u7ee1\u67ad\u67b5\u7b71\u7bab\u9b48",
				"xie" : "\u6954\u4e9b\u6b47\u874e\u978b\u534f\u631f\u643a\u90aa\u659c\u80c1\u8c10\u5199\u68b0\u5378\u87f9\u61c8\u6cc4\u6cfb\u8c22\u5c51\u5055\u4eb5\u52f0\u71ee\u85a4\u64b7\u5ee8\u7023\u9082\u7ec1\u7f2c\u69ad\u698d\u6b59\u8e9e",
				"xin" : "\u85aa\u82af\u950c\u6b23\u8f9b\u65b0\u5ffb\u5fc3\u4fe1\u8845\u56df\u99a8\u8398\u6b46\u94fd\u946b",
				"xing" : "\u661f\u8165\u7329\u60fa\u5174\u5211\u578b\u5f62\u90a2\u884c\u9192\u5e78\u674f\u6027\u59d3\u9649\u8347\u8365\u64e4\u60bb\u784e",
				"xiong" : "\u5144\u51f6\u80f8\u5308\u6c79\u96c4\u718a\u828e",
				"xiu" : "\u4f11\u4fee\u7f9e\u673d\u55c5\u9508\u79c0\u8896\u7ee3\u83a0\u5cab\u9990\u5ea5\u9e3a\u8c85\u9af9",
				"xu" : "\u589f\u620c\u9700\u865a\u5618\u987b\u5f90\u8bb8\u84c4\u9157\u53d9\u65ed\u5e8f\u755c\u6064\u7d6e\u5a7f\u7eea\u7eed\u8bb4\u8be9\u5729\u84ff\u6035\u6d2b\u6e86\u987c\u6829\u7166\u7809\u76f1\u80e5\u7cc8\u9191",
				"xuan" : "\u8f69\u55a7\u5ba3\u60ac\u65cb\u7384\u9009\u7663\u7729\u7eda\u5107\u8c16\u8431\u63ce\u9994\u6ceb\u6d35\u6e32\u6f29\u7487\u6966\u6684\u70ab\u714a\u78b9\u94c9\u955f\u75c3",
				"xue" : "\u9774\u859b\u5b66\u7a74\u96ea\u8840\u5671\u6cf6\u9cd5",
				"xun" : "\u52cb\u718f\u5faa\u65ec\u8be2\u5bfb\u9a6f\u5de1\u6b89\u6c5b\u8bad\u8baf\u900a\u8fc5\u5dfd\u57d9\u8340\u85b0\u5ccb\u5f87\u6d54\u66db\u7aa8\u91ba\u9c9f",
				"ya" : "\u538b\u62bc\u9e26\u9e2d\u5440\u4e2b\u82bd\u7259\u869c\u5d16\u8859\u6daf\u96c5\u54d1\u4e9a\u8bb6\u4f22\u63e0\u5416\u5c88\u8fd3\u5a05\u740a\u6860\u6c29\u7811\u775a\u75d6",
				"yan" : "\u7109\u54bd\u9609\u70df\u6df9\u76d0\u4e25\u7814\u8712\u5ca9\u5ef6\u8a00\u989c\u960e\u708e\u6cbf\u5944\u63a9\u773c\u884d\u6f14\u8273\u5830\u71d5\u538c\u781a\u96c1\u5501\u5f66\u7130\u5bb4\u8c1a\u9a8c\u53a3\u9765\u8d5d\u4fe8\u5043\u5156\u8ba0\u8c33\u90fe\u9122\u82ab\u83f8\u5d26\u6079\u95eb\u960f\u6d07\u6e6e\u6edf\u598d\u5ae3\u7430\u664f\u80ed\u814c\u7131\u7f68\u7b75\u917d\u9b47\u990d\u9f39",
				"yang" : "\u6b83\u592e\u9e2f\u79e7\u6768\u626c\u4f6f\u75a1\u7f8a\u6d0b\u9633\u6c27\u4ef0\u75d2\u517b\u6837\u6f3e\u5f89\u600f\u6cf1\u7080\u70ca\u6059\u86d8\u9785",
				"yao" : "\u9080\u8170\u5996\u7476\u6447\u5c27\u9065\u7a91\u8c23\u59da\u54ac\u8200\u836f\u8981\u8000\u592d\u723b\u5406\u5d3e\u5fad\u7039\u5e7a\u73e7\u6773\u66dc\u80b4\u9e5e\u7a88\u7e47\u9cd0",
				"ye" : "\u6930\u564e\u8036\u7237\u91ce\u51b6\u4e5f\u9875\u6396\u4e1a\u53f6\u66f3\u814b\u591c\u6db2\u8c12\u90ba\u63f6\u9980\u6654\u70e8\u94d8",
				"yi" : "\u4e00\u58f9\u533b\u63d6\u94f1\u4f9d\u4f0a\u8863\u9890\u5937\u9057\u79fb\u4eea\u80f0\u7591\u6c82\u5b9c\u59e8\u5f5d\u6905\u8681\u501a\u5df2\u4e59\u77e3\u4ee5\u827a\u6291\u6613\u9091\u5c79\u4ebf\u5f79\u81c6\u9038\u8084\u75ab\u4ea6\u88d4\u610f\u6bc5\u5fc6\u4e49\u76ca\u6ea2\u8be3\u8bae\u8c0a\u8bd1\u5f02\u7ffc\u7fcc\u7ece\u5208\u5293\u4f7e\u8bd2\u572a\u572f\u57f8\u61ff\u82e1\u858f\u5f08\u5955\u6339\u5f0b\u5453\u54a6\u54bf\u566b\u5cc4\u5db7\u7317\u9974\u603f\u6021\u6092\u6f2a\u8fe4\u9a7f\u7f22\u6baa\u8d3b\u65d6\u71a0\u9487\u9552\u9571\u75cd\u7617\u7654\u7fca\u8864\u8734\u8223\u7fbf\u7ff3\u914f\u9edf",
				"yin" : "\u8335\u836b\u56e0\u6bb7\u97f3\u9634\u59fb\u541f\u94f6\u6deb\u5bc5\u996e\u5c39\u5f15\u9690\u5370\u80e4\u911e\u5819\u831a\u5591\u72fa\u5924\u6c24\u94df\u763e\u8693\u972a\u9f88",
				"ying" : "\u82f1\u6a31\u5a74\u9e70\u5e94\u7f28\u83b9\u8424\u8425\u8367\u8747\u8fce\u8d62\u76c8\u5f71\u9896\u786c\u6620\u5b34\u90e2\u8314\u83ba\u8426\u6484\u5624\u81ba\u6ee2\u6f46\u701b\u745b\u748e\u6979\u9e66\u763f\u988d\u7f42",
				"yo" : "\u54df\u5537",
				"yong" : "\u62e5\u4f63\u81c3\u75c8\u5eb8\u96cd\u8e0a\u86f9\u548f\u6cf3\u6d8c\u6c38\u607f\u52c7\u7528\u4fd1\u58c5\u5889\u6175\u9095\u955b\u752c\u9cd9\u9954",
				"you" : "\u5e7d\u4f18\u60a0\u5fe7\u5c24\u7531\u90ae\u94c0\u72b9\u6cb9\u6e38\u9149\u6709\u53cb\u53f3\u4f51\u91c9\u8bf1\u53c8\u5e7c\u5363\u6538\u4f91\u83b8\u5466\u56ff\u5ba5\u67da\u7337\u7256\u94d5\u75a3\u8763\u9c7f\u9edd\u9f2c",
				"yu" : "\u8fc2\u6de4\u4e8e\u76c2\u6986\u865e\u611a\u8206\u4f59\u4fde\u903e\u9c7c\u6109\u6e1d\u6e14\u9685\u4e88\u5a31\u96e8\u4e0e\u5c7f\u79b9\u5b87\u8bed\u7fbd\u7389\u57df\u828b\u90c1\u5401\u9047\u55bb\u5cea\u5fa1\u6108\u6b32\u72f1\u80b2\u8a89\u6d74\u5bd3\u88d5\u9884\u8c6b\u9a6d\u79ba\u6bd3\u4f1b\u4fe3\u8c00\u8c15\u8438\u84e3\u63c4\u5581\u5704\u5709\u5d5b\u72f3\u996b\u5ebe\u9608\u59aa\u59a4\u7ea1\u745c\u6631\u89ce\u8174\u6b24\u65bc\u715c\u71e0\u807f\u94b0\u9e46\u7610\u7600\u7ab3\u8753\u7afd\u8201\u96e9\u9f89",
				"yuan" : "\u9e33\u6e0a\u51a4\u5143\u57a3\u8881\u539f\u63f4\u8f95\u56ed\u5458\u5706\u733f\u6e90\u7f18\u8fdc\u82d1\u613f\u6028\u9662\u586c\u6c85\u5a9b\u7457\u6a7c\u7230\u7722\u9e22\u8788\u9f0b",
				"yue" : "\u66f0\u7ea6\u8d8a\u8dc3\u94a5\u5cb3\u7ca4\u6708\u60a6\u9605\u9fa0\u6a3e\u5216\u94ba",
				"yun" : "\u8018\u4e91\u90e7\u5300\u9668\u5141\u8fd0\u8574\u915d\u6655\u97f5\u5b55\u90d3\u82b8\u72c1\u607d\u7ead\u6b92\u6600\u6c32",
				"za" : "\u531d\u7838\u6742\u62f6\u5482",
				"zai" : "\u683d\u54c9\u707e\u5bb0\u8f7d\u518d\u5728\u54b1\u5d3d\u753e",
				"zan" : "\u6512\u6682\u8d5e\u74d2\u661d\u7c2a\u7ccc\u8db1\u933e",
				"zang" : "\u8d43\u810f\u846c\u5958\u6215\u81e7",
				"zao" : "\u906d\u7cdf\u51ff\u85fb\u67a3\u65e9\u6fa1\u86a4\u8e81\u566a\u9020\u7682\u7076\u71e5\u5523\u7f2b",
				"ze" : "\u8d23\u62e9\u5219\u6cfd\u4ec4\u8d5c\u5567\u8fee\u6603\u7b2e\u7ba6\u8234",
				"zei" : "\u8d3c",
				"zen" : "\u600e\u8c2e",
				"zeng" : "\u589e\u618e\u66fe\u8d60\u7f2f\u7511\u7f7e\u9503",
				"zha" : "\u624e\u55b3\u6e23\u672d\u8f67\u94e1\u95f8\u7728\u6805\u69a8\u548b\u4e4d\u70b8\u8bc8\u63f8\u5412\u54a4\u54f3\u600d\u781f\u75c4\u86b1\u9f44",
				"zhai" : "\u6458\u658b\u5b85\u7a84\u503a\u5be8\u7826",
				"zhan" : "\u77bb\u6be1\u8a79\u7c98\u6cbe\u76cf\u65a9\u8f97\u5d2d\u5c55\u8638\u6808\u5360\u6218\u7ad9\u6e5b\u7efd\u8c35\u640c\u65c3",
				"zhang" : "\u6a1f\u7ae0\u5f70\u6f33\u5f20\u638c\u6da8\u6756\u4e08\u5e10\u8d26\u4ed7\u80c0\u7634\u969c\u4ec9\u9123\u5e5b\u5d82\u7350\u5adc\u748b\u87d1",
				"zhao" : "\u62db\u662d\u627e\u6cbc\u8d75\u7167\u7f69\u5146\u8087\u53ec\u722a\u8bcf\u68f9\u948a\u7b0a",
				"zhe" : "\u906e\u6298\u54f2\u86f0\u8f99\u8005\u9517\u8517\u8fd9\u6d59\u8c2a\u966c\u67d8\u8f84\u78d4\u9e67\u891a\u8707\u8d6d",
				"zhen" : "\u73cd\u659f\u771f\u7504\u7827\u81fb\u8d1e\u9488\u4fa6\u6795\u75b9\u8bca\u9707\u632f\u9547\u9635\u7f1c\u6862\u699b\u8f78\u8d48\u80d7\u6715\u796f\u755b\u9e29",
				"zheng" : "\u84b8\u6323\u7741\u5f81\u72f0\u4e89\u6014\u6574\u62ef\u6b63\u653f\u5e27\u75c7\u90d1\u8bc1\u8be4\u5ce5\u94b2\u94ee\u7b5d",
				"zhi" : "\u829d\u679d\u652f\u5431\u8718\u77e5\u80a2\u8102\u6c41\u4e4b\u7ec7\u804c\u76f4\u690d\u6b96\u6267\u503c\u4f84\u5740\u6307\u6b62\u8dbe\u53ea\u65e8\u7eb8\u5fd7\u631a\u63b7\u81f3\u81f4\u7f6e\u5e1c\u5cd9\u5236\u667a\u79e9\u7a1a\u8d28\u7099\u75d4\u6ede\u6cbb\u7a92\u536e\u965f\u90c5\u57f4\u82b7\u646d\u5e19\u5fee\u5f58\u54ab\u9a98\u6809\u67b3\u6800\u684e\u8f75\u8f7e\u6534\u8d3d\u81a3\u7949\u7957\u9ef9\u96c9\u9e37\u75e3\u86ed\u7d77\u916f\u8dd6\u8e2c\u8e2f\u8c78\u89ef",
				"zhong" : "\u4e2d\u76c5\u5fe0\u949f\u8877\u7ec8\u79cd\u80bf\u91cd\u4ef2\u4f17\u51a2\u953a\u87bd\u8202\u822f\u8e35",
				"zhou" : "\u821f\u5468\u5dde\u6d32\u8bcc\u7ca5\u8f74\u8098\u5e1a\u5492\u76b1\u5b99\u663c\u9aa4\u5544\u7740\u501c\u8bf9\u836e\u9b3b\u7ea3\u80c4\u78a1\u7c40\u8233\u914e\u9cb7",
				"zhu" : "\u73e0\u682a\u86db\u6731\u732a\u8bf8\u8bdb\u9010\u7af9\u70db\u716e\u62c4\u77a9\u5631\u4e3b\u8457\u67f1\u52a9\u86c0\u8d2e\u94f8\u7b51\u4f4f\u6ce8\u795d\u9a7b\u4f2b\u4f8f\u90be\u82ce\u8331\u6d19\u6e1a\u6f74\u9a7a\u677c\u69e0\u6a65\u70b7\u94e2\u75b0\u7603\u86b0\u7afa\u7bb8\u7fe5\u8e85\u9e88",
				"zhua" : "\u6293",
				"zhuai" : "\u62fd",
				"zhuan" : "\u4e13\u7816\u8f6c\u64b0\u8d5a\u7bc6\u629f\u556d\u989b",
				"zhuang" : "\u6869\u5e84\u88c5\u5986\u649e\u58ee\u72b6\u4e2c",
				"zhui" : "\u690e\u9525\u8ffd\u8d58\u5760\u7f00\u8411\u9a93\u7f12",
				"zhun" : "\u8c06\u51c6",
				"zhuo" : "\u6349\u62d9\u5353\u684c\u7422\u8301\u914c\u707c\u6d4a\u502c\u8bfc\u5ef4\u855e\u64e2\u555c\u6d5e\u6dbf\u6753\u712f\u799a\u65ab",
				"zi" : "\u5179\u54a8\u8d44\u59ff\u6ecb\u6dc4\u5b5c\u7d2b\u4ed4\u7c7d\u6ed3\u5b50\u81ea\u6e0d\u5b57\u8c18\u5d6b\u59ca\u5b73\u7f01\u6893\u8f8e\u8d40\u6063\u7726\u9531\u79ed\u8014\u7b2b\u7ca2\u89dc\u8a3e\u9cbb\u9aed",
				"zong" : "\u9b03\u68d5\u8e2a\u5b97\u7efc\u603b\u7eb5\u8159\u7cbd",
				"zou" : "\u90b9\u8d70\u594f\u63cd\u9139\u9cb0",
				"zu" : "\u79df\u8db3\u5352\u65cf\u7956\u8bc5\u963b\u7ec4\u4fce\u83f9\u5550\u5f82\u9a75\u8e74",
				"zuan" : "\u94bb\u7e82\u6525\u7f35",
				"zui" : "\u5634\u9189\u6700\u7f6a",
				"zun" : "\u5c0a\u9075\u6499\u6a3d\u9cdf",
				"zuo" : "\u6628\u5de6\u4f50\u67de\u505a\u4f5c\u5750\u5ea7\u961d\u963c\u80d9\u795a\u9162",
				"cou" : "\u85ae\u6971\u8f8f\u8160",
				"nang" : "\u652e\u54dd\u56d4\u9995\u66e9",
				"o" : "\u5594",
				"dia" : "\u55f2",
				"chuai" : "\u562c\u81aa\u8e39",
				"cen" : "\u5c91\u6d94",
				"diu" : "\u94e5",
				"nou" : "\u8028",
				"fou" : "\u7f36",
				"bia" : "\u9adf"
			};
			this.polyphone = {
				"19969" : "DZ",
				"19975" : "WM",
				"19988" : "QJ",
				"20048" : "YL",
				"20056" : "SC",
				"20060" : "NM",
				"20094" : "QG",
				"20127" : "QJ",
				"20167" : "QC",
				"20193" : "YG",
				"20250" : "KH",
				"20256" : "ZC",
				"20282" : "SC",
				"20285" : "QJG",
				"20291" : "TD",
				"20314" : "YD",
				"20340" : "NE",
				"20375" : "TD",
				"20389" : "YJ",
				"20391" : "CZ",
				"20415" : "PB",
				"20446" : "YS",
				"20447" : "SQ",
				"20504" : "TC",
				"20608" : "KG",
				"20854" : "QJ",
				"20857" : "ZC",
				"20911" : "PF",
				"20504" : "TC",
				"20608" : "KG",
				"20854" : "QJ",
				"20857" : "ZC",
				"20911" : "PF",
				"20985" : "AW",
				"21032" : "PB",
				"21048" : "XQ",
				"21049" : "SC",
				"21089" : "YS",
				"21119" : "JC",
				"21242" : "SB",
				"21273" : "SC",
				"21305" : "YP",
				"21306" : "QO",
				"21330" : "ZC",
				"21333" : "SDC",
				"21345" : "QK",
				"21378" : "CA",
				"21397" : "SC",
				"21414" : "XS",
				"21442" : "SC",
				"21477" : "JG",
				"21480" : "TD",
				"21484" : "ZS",
				"21494" : "YX",
				"21505" : "YX",
				"21512" : "HG",
				"21523" : "XH",
				"21537" : "PB",
				"21542" : "PF",
				"21549" : "KH",
				"21571" : "E",
				"21574" : "DA",
				"21588" : "TD",
				"21589" : "O",
				"21618" : "ZC",
				"21621" : "KHA",
				"21632" : "ZJ",
				"21654" : "KG",
				"21679" : "LKG",
				"21683" : "KH",
				"21710" : "A",
				"21719" : "YH",
				"21734" : "WOE",
				"21769" : "A",
				"21780" : "WN",
				"21804" : "XH",
				"21834" : "A",
				"21899" : "ZD",
				"21903" : "RN",
				"21908" : "WO",
				"21939" : "ZC",
				"21956" : "SA",
				"21964" : "YA",
				"21970" : "TD",
				"22003" : "A",
				"22031" : "JG",
				"22040" : "XS",
				"22060" : "ZC",
				"22066" : "ZC",
				"22079" : "MH",
				"22129" : "XJ",
				"22179" : "XA",
				"22237" : "NJ",
				"22244" : "TD",
				"22280" : "JQ",
				"22300" : "YH",
				"22313" : "XW",
				"22331" : "YQ",
				"22343" : "YJ",
				"22351" : "PH",
				"22395" : "DC",
				"22412" : "TD",
				"22484" : "PB",
				"22500" : "PB",
				"22534" : "ZD",
				"22549" : "DH",
				"22561" : "PB",
				"22612" : "TD",
				"22771" : "KQ",
				"22831" : "HB",
				"22841" : "JG",
				"22855" : "QJ",
				"22865" : "XQ",
				"23013" : "ML",
				"23081" : "WM",
				"23487" : "SX",
				"23558" : "QJ",
				"23561" : "YW",
				"23586" : "YW",
				"23614" : "YW",
				"23615" : "SN",
				"23631" : "PB",
				"23646" : "ZS",
				"23663" : "ZT",
				"23673" : "YG",
				"23762" : "TD",
				"23769" : "ZS",
				"23780" : "QJ",
				"23884" : "QK",
				"24055" : "XH",
				"24113" : "DC",
				"24162" : "ZC",
				"24191" : "GA",
				"24273" : "QJ",
				"24324" : "NL",
				"24377" : "TD",
				"24378" : "QJ",
				"24439" : "PF",
				"24554" : "ZS",
				"24683" : "TD",
				"24694" : "WE",
				"24733" : "LK",
				"24925" : "TN",
				"25094" : "ZG",
				"25100" : "XQ",
				"25103" : "XH",
				"25153" : "PB",
				"25170" : "PB",
				"25179" : "KG",
				"25203" : "PB",
				"25240" : "ZS",
				"25282" : "FB",
				"25303" : "NA",
				"25324" : "KG",
				"25341" : "ZY",
				"25373" : "WZ",
				"25375" : "XJ",
				"25384" : "A",
				"25457" : "A",
				"25528" : "SD",
				"25530" : "SC",
				"25552" : "TD",
				"25774" : "ZC",
				"25874" : "ZC",
				"26044" : "YW",
				"26080" : "WM",
				"26292" : "PB",
				"26333" : "PB",
				"26355" : "ZY",
				"26366" : "CZ",
				"26397" : "ZC",
				"26399" : "QJ",
				"26415" : "ZS",
				"26451" : "SB",
				"26526" : "ZC",
				"26552" : "JG",
				"26561" : "TD",
				"26588" : "JG",
				"26597" : "CZ",
				"26629" : "ZS",
				"26638" : "YL",
				"26646" : "XQ",
				"26653" : "KG",
				"26657" : "XJ",
				"26727" : "HG",
				"26894" : "ZC",
				"26937" : "ZS",
				"26946" : "ZC",
				"26999" : "KJ",
				"27099" : "KJ",
				"27449" : "YQ",
				"27481" : "XS",
				"27542" : "ZS",
				"27663" : "ZS",
				"27748" : "TS",
				"27784" : "SC",
				"27788" : "ZD",
				"27795" : "TD",
				"27812" : "O",
				"27850" : "PB",
				"27852" : "MB",
				"27895" : "SL",
				"27898" : "PL",
				"27973" : "QJ",
				"27981" : "KH",
				"27986" : "HX",
				"27994" : "XJ",
				"28044" : "YC",
				"28065" : "WG",
				"28177" : "SM",
				"28267" : "QJ",
				"28291" : "KH",
				"28337" : "ZQ",
				"28463" : "TL",
				"28548" : "DC",
				"28601" : "TD",
				"28689" : "PB",
				"28805" : "JG",
				"28820" : "QG",
				"28846" : "PB",
				"28952" : "TD",
				"28975" : "ZC",
				"29100" : "A",
				"29325" : "QJ",
				"29575" : "SL",
				"29602" : "FB",
				"30010" : "TD",
				"30044" : "CX",
				"30058" : "PF",
				"30091" : "YSP",
				"30111" : "YN",
				"30229" : "XJ",
				"30427" : "SC",
				"30465" : "SX",
				"30631" : "YQ",
				"30655" : "QJ",
				"30684" : "QJG",
				"30707" : "SD",
				"30729" : "XH",
				"30796" : "LG",
				"30917" : "PB",
				"31074" : "NM",
				"31085" : "JZ",
				"31109" : "SC",
				"31181" : "ZC",
				"31192" : "MLB",
				"31293" : "JQ",
				"31400" : "YX",
				"31584" : "YJ",
				"31896" : "ZN",
				"31909" : "ZY",
				"31995" : "XJ",
				"32321" : "PF",
				"32327" : "ZY",
				"32418" : "HG",
				"32420" : "XQ",
				"32421" : "HG",
				"32438" : "LG",
				"32473" : "GJ",
				"32488" : "TD",
				"32521" : "QJ",
				"32527" : "PB",
				"32562" : "ZSQ",
				"32564" : "JZ",
				"32735" : "ZD",
				"32793" : "PB",
				"33071" : "PF",
				"33098" : "XL",
				"33100" : "YA",
				"33152" : "PB",
				"33261" : "CX",
				"33324" : "BP",
				"33333" : "TD",
				"33406" : "YA",
				"33426" : "WM",
				"33432" : "PB",
				"33445" : "JG",
				"33486" : "ZN",
				"33493" : "TS",
				"33507" : "QJ",
				"33540" : "QJ",
				"33544" : "ZC",
				"33564" : "XQ",
				"33617" : "YT",
				"33632" : "QJ",
				"33636" : "XH",
				"33637" : "YX",
				"33694" : "WG",
				"33705" : "PF",
				"33728" : "YW",
				"33882" : "SR",
				"34067" : "WM",
				"34074" : "YW",
				"34121" : "QJ",
				"34255" : "ZC",
				"34259" : "XL",
				"34425" : "JH",
				"34430" : "XH",
				"34485" : "KH",
				"34503" : "YS",
				"34532" : "HG",
				"34552" : "XS",
				"34558" : "YE",
				"34593" : "ZL",
				"34660" : "YQ",
				"34892" : "XH",
				"34928" : "SC",
				"34999" : "QJ",
				"35048" : "PB",
				"35059" : "SC",
				"35098" : "ZC",
				"35203" : "TQ",
				"35265" : "JX",
				"35299" : "JX",
				"35782" : "SZ",
				"35828" : "YS",
				"35830" : "E",
				"35843" : "TD",
				"35895" : "YG",
				"35977" : "MH",
				"36158" : "JG",
				"36228" : "QJ",
				"36426" : "XQ",
				"36466" : "DC",
				"36710" : "JC",
				"36711" : "ZYG",
				"36767" : "PB",
				"36866" : "SK",
				"36951" : "YW",
				"37034" : "YX",
				"37063" : "XH",
				"37218" : "ZC",
				"37325" : "ZC",
				"38063" : "PB",
				"38079" : "TD",
				"38085" : "QY",
				"38107" : "DC",
				"38116" : "TD",
				"38123" : "YD",
				"38224" : "HG",
				"38241" : "XTC",
				"38271" : "ZC",
				"38415" : "YE",
				"38426" : "KH",
				"38461" : "YD",
				"38463" : "AE",
				"38466" : "PB",
				"38477" : "XJ",
				"38518" : "YT",
				"38551" : "WK",
				"38585" : "ZC",
				"38704" : "XS",
				"38739" : "LJ",
				"38761" : "GJ",
				"38808" : "SQ",
				"39048" : "JG",
				"39049" : "XJ",
				"39052" : "HG",
				"39076" : "CZ",
				"39271" : "XT",
				"39534" : "TD",
				"39552" : "TD",
				"39584" : "PB",
				"39647" : "SB",
				"39730" : "LG",
				"39748" : "TPB",
				"40109" : "ZQ",
				"40479" : "ND",
				"40516" : "HG",
				"40536" : "HG",
				"40583" : "QJ",
				"40765" : "YQ",
				"40784" : "QJ",
				"40840" : "YK",
				"40863" : "QJG"
			};
		},

		// 提取拼音, 返回首字母大写形式
		getFullChars : function(str) {
			var result = '', name;
			var reg = new RegExp('[a-zA-Z0-9\- ]');
			for (var i = 0, len = str.length; i < len; i++) {
				var ch = str.substr(i, 1), unicode = ch.charCodeAt(0);
				if (unicode > 40869 || unicode < 19968) {
					result += ch;
				} else {
					name = this._getFullChar(ch);
					if (name !== false) {
						result += name;
					}
				}
			}
			return result;
		},

		// 提取首字母，返回大写形式
		getCamelChars : function(str) {
			if (typeof (str) !== 'string')
				throw new Error(-1, "函数getFisrt需要字符串类型参数!");
			var chars = []; // 保存中间结果的数组
			for (var i = 0, len = str.length; i < len; i++) {
				// 获得unicode码
				var ch = str.charAt(i);
				// 检查该unicode码是否在处理范围之内,在则返回该码对映汉字的拼音首字母,不在则调用其它函数处理
				chars.push(this._getChar(ch));
			}
			// 处理arrResult,返回所有可能的拼音首字母串数组
			return this._getResult(chars);
		},

		// 提取拼音
		_getFullChar : function(str) {
			for ( var key in this.full_dict) {
				if (-1 !== this.full_dict[key].indexOf(str)) {
					return this._capitalize(key);
					break;
				}
			}
			return false;
		},

		// 首字母大写
		_capitalize : function(str) {
			if (str.length > 0) {
				var first = str.substr(0, 1).toUpperCase();
				var spare = str.substr(1, str.length);
				return first + spare;
			}
		},

		_getChar : function(ch) {
			var unicode = ch.charCodeAt(0);
			// 如果不在汉字处理范围之内,返回原字符,也可以调用自己的处理函数
			if (unicode > 40869 || unicode < 19968)
				return ch; // dealWithOthers(ch);
			// 检查是否是多音字,是按多音字处理,不是就直接在strChineseFirstPY字符串中找对应的首字母
			if (!this.options.checkPolyphone)
				return this.char_dict.charAt(unicode - 19968);
			return this.polyphone[unicode] ? this.polyphone[unicode]
					: this.char_dict.charAt(unicode - 19968);
		},

		_getResult : function(chars) {
			if (!this.options.checkPolyphone)
				return chars.join('');
			var result = [ '' ];
			for (var i = 0, len = chars.length; i < len; i++) {
				var str = chars[i], strlen = str.length;
				if (strlen == 1) {
					for (var j = 0; j < result.length; j++) {
						result[k] += str;
					}
				} else {
					var swap1 = result.slice(0);
					result = [];
					for (var j = 0; j < strlen; j++) {
						// 复制一个相同的arrRslt
						var swap2 = swap1.slice(0);
						// 把当前字符str[k]添加到每个元素末尾
						for (var k = 0; k < swap2.length; k++) {
							swap2[k] += str.charAt(j);
						}
						// 把复制并修改后的数组连接到arrRslt上
						result = result.concat(swap2);
					}
				}
			}
			return result;
		}

	};

	var extend = function(dst, src) {
		for ( var property in src) {
			dst[property] = src[property];
		}
		return dst;
	};

	return new Pinyin(arguments);
})();

Gnt.ux.ZhcxDialog = Ext.extend(Ext.Window, {
	title : '综合查询',
	closeAction : 'hide',
	modal : true,
	width : 800,
	height : 500,
	margins : '5',
	layout : 'border',
	reset : function() {
		this.mxfs.getForm().reset();
	},
	initComponent : function() {
		if (!Gnt.loadSjpzb(this.pzlb, function() {
		}))
			return;

		var aryTemp = Gnt.ux.Dict.getSjpzData(this.pzlb);
		var ary =new Array();
		for (var i = 0; i < aryTemp.length; i++) {
			var visibletype = aryTemp[i].visibletype;
			if(visibletype>0){
				ary.push(aryTemp[i]); 
			}
		}
		var data = {
			list : ary,
			totalCount : ary.length,
			success : true
		};

		var store = new Ext.data.JsonStore({
			root : 'list',
			id : 'sjpzid',
			data : data,
			autoLoad : false,
			totalProperty : 'totalCount',
			fields : [ "sjpzid", "pzlb", "pzmc", "id", "fieldname",
					"displayname", "fieldtype", "fieldtypename", "dsname",
					"readonly", "visibletype", "fieldlength",
					"displayfieldlength", "ischinese", "mbmod",
					"allowselfinput", "codefield", "pyfield", "namefield",
					"partmask", "lsfield", "showls", "enablecopy",
					"enabledefaultfilter", "enterdropdown", "enteruniqueexit",
					"usetablefiltered", "useforsfz", "conditionoperator",
					"conditionfield", "conditiontype", "groups", "bdlx",
					"vtype", ]
		});

		var tpl = new Ext.XTemplate('<tpl for=".">',
				'<div class="dataview-thumb-wrap" id="_sjpz_zhcx_{sjpzid}">',
				'<span class="x-editable">{displayname}</span>', '</div>',
				'</tpl>');

		var dataview = new Ext.DataView({
			region : 'left',
			store : store,
			tpl : tpl,
			multiSelect : true,
			autoHeight : true,
			simpleSelect : true,
			// 一个CSS的样式类，每个元素onmouseover和onmouseout时生效
			overClass : 'dataview-x-view-over',
			//selectedClass : 'dataview-x-view-selected',
			// DomQuery选择符告知该类究竟如何分辨出每个item
			itemSelector : 'div.dataview-thumb-wrap',
			emptyText : "没有数据",
			deferEmptyText : true,
			loadingText : '正在加载数据，请等待...',
			listeners : {
				selectionchange : function(view, nodes) {
					return;
				},
				click : function(view, index, node, e) {
					var data = view.getRecord(node).data;
					var win = this.findParentByType("zhcx_dialog");
					var c = win.mxfs.items.get(0);

					var it = Gnt.getExtField2(data);
					it.hideLabel = true;
					var index = c.items.getCount() / 4;
					c.add({
						columnWidth : 0.1,
						layout : 'form',
						bodyStyle : 'padding:0 0 5 0',
						items : [ {
							xtype : 'label',
							name : it.name + ".index_",
							text : index
						} ]
					});

					c.add({
						columnWidth : 0.2,
						layout : 'form',
						bodyStyle : 'padding:0 0 5 0',
						items : [ {
							name : it.name + ".label_",
							xtype : 'label',
							text : it.fieldLabel
						} ]
					});

					c.add({
						columnWidth : 0.3,
						layout : 'form',
						bodyStyle : 'padding:0 5 5 0',
						items : [ {
							name : it.name + ".op_",
							anchor : '100%',
							xtype : 'combo',
							fieldLabel : '操作符',
							hideLabel : true,
							mode : 'local',
							triggerAction : 'all',
							valueField : "code",
							displayField : "name",
							selectOnFocus : true,
							emptyText : '请选择',
							typeAhead : true,
							forceSelection : true,
							forceSelection : true,
							blankText : '请选择',
							lazyRender : true,
							value : '=',
							store : it.xtype=='textfield'?win.opstore:win.opstore1
						} ]
					});

					c.add({
						columnWidth : 0.4,
						layout : 'form',
						bodyStyle : 'padding:0 15 5 0',
						items : [ it ]
					});

					c.doLayout();

	  			      var o = Ext.getDom('bds'); //The text box to be inserted
	  			      o.focus();
	  			      //var s = document.selection.createRange();//以先保存选区的方式用于在IE中实现插入
	  			        
	  			      var value = (index=='1'?'':'*')+index;
	  			      if (document.selection) {//用于IE
	  			          //s.text = value;
//	                    document.selection.empty();
//	                    s.text = value;
//	                    s.collapse();
//	                    s.select();
	                    o.value = o.value.substr(0, o.selectionStart) + value /*+ o.value.substr(o.selectionEnd)*/;
	  			      }
	  			      else {//用于FF
	  			          o.value = o.value.substr(0, o.selectionStart) + value + o.value.substr(o.selectionEnd);
	  			      } 
  			
					if (view.isSelected(node)) {
						
					} else {
//						// 取消
//						var index = 1;
//						c.items.each(function(p) {
//							var f = p.items.get(0);
//							if (f.name == data.fieldname
//									|| (f.name && f.name.indexOf(data.fieldname
//											+ ".") >= 0)) {
//								c.remove(p);
//							} else {
//								if (f.name && f.name.indexOf(".index_") >= 0) {
//									f.setText(index);
//									index++;
//								}
//							}
//							c.doLayout();
//						});
					}
				},
				dblclick : function(view, index, node, e) {

				}
			}
		});

		var mxfs = new Ext.form.FormPanel({
			title : '自定义查询条件',
			renderTo : Ext.getBody(),
			bodyStyle : 'padding:5 5 5 5',
			width : 500,
			height: 400,
			autoHeight : true,
			frame : false,
			border : false,
			layout : 'form',
			labelWidth : 60,
			items : [ {
				layout : 'column',
				id : 'myfieldset',
				frame : false,
				border : false,
				defaults : {
					border : false,
					frame : false
				},
				items : [ {
					columnWidth : 0.1,
					layout : 'form',
					bodyStyle : 'padding:5 0 5 0',
					items : [ {
						xtype : 'label',
						html : '<b>序号</b>'
					} ]
				}, {
					columnWidth : 0.2,
					layout : 'form',
					bodyStyle : 'padding:5 0 5 0',
					items : [ {
						xtype : 'label',
						anchor : '99%',
						html : '<b>条件字段</b>'
					} ]
				}, {
					columnWidth : 0.3,
					layout : 'form',
					bodyStyle : 'padding:5 0 5 0',
					items : [ {
						xtype : 'label',
						anchor : '99%',
						html : '<b>操作符</b>'
					} ]
				}, {
					columnWidth : 0.4,
					layout : 'form',
					bodyStyle : 'padding:5 0 5 0',
					items : [ {
						xtype : 'label',
						anchor : '99%',
						html : '<b>条件内容</b>'
					} ]
				} ]
			} ]
		});
		var tjbds = new Ext.form.FormPanel({
			title : '',
			renderTo : Ext.getBody(),
			bodyStyle : 'padding:5 5 5 5',
			width : 500,
			autoHeight : true,
			frame : false,
			border : false,
			layout : 'form',
			items : [ {
				layout : 'column',
				frame : false,
				border : false,
				defaults : {
					border : false,
					frame : false
				},
				items : [ {
					height:30,
					frame:false,
					border:true,
					layout:'table',
					layoutConfig: {
			        	columns: 15
			        },
					items:[{
						text:'*',
						xtype:'button',
	        			minWidth:10,
	        			handler:function(){
	        			      var o = Ext.getDom('bds'); //The text box to be inserted
	        			      o.focus();

	        			      var value = this.text;
	        			      if (document.selection) {//用于IE
                                  var s = document.selection.createRange();//以先保存选区的方式用于在IE中实现插入
	        			          s.text = value;
	        			      }
	        			      else {//用于FF
	        			          o.value = o.value.substr(0, o.selectionStart) + value + o.value.substr(o.selectionEnd);
	        			      } 
	        			}
					},{
						text:'+',
						xtype:'button',
	        			minWidth:10,
	        			handler:function(){
	        			      var o = Ext.getDom('bds'); //The text box to be inserted
	        			      o.focus();

	        			        
	        			      var value = this.text;
	        			      if (document.selection) {//用于IE
                                  var s = document.selection.createRange();//以先保存选区的方式用于在IE中实现插入
	        			          s.text = value;
	        			      }
	        			      else {//用于FF
	        			          o.value = o.value.substr(0, o.selectionStart) + value + o.value.substr(o.selectionEnd);
	        			      } 
	        			}
					},{
						text:'!',
						xtype:'button',
	        			minWidth:10,
	        			handler:function(){
	        			      var o = Ext.getDom('bds'); //The text box to be inserted
	        			      o.focus();

	        			      var value = this.text;
	        			      if (document.selection) {//用于IE
                                  var s = document.selection.createRange();//以先保存选区的方式用于在IE中实现插入
	        			          s.text = value;
	        			      }
	        			      else {//用于FF
	        			          o.value = o.value.substr(0, o.selectionStart) + value + o.value.substr(o.selectionEnd);
	        			      } 
	        			}
					},{
	        			text:'(',
	        			xtype:'button',
	        			minWidth:10,
	        			handler:function(){
	        			      var o = Ext.getDom('bds'); //The text box to be inserted
	        			      o.focus();

	        			      var value = this.text;
	        			      if (document.selection) {//用于IE
                                  var s = document.selection.createRange();//以先保存选区的方式用于在IE中实现插入
                                  s.text = value;
	        			      }
	        			      else {//用于FF
	        			          o.value = o.value.substr(0, o.selectionStart) + value + o.value.substr(o.selectionEnd);
	        			      } 
	        			}
	        		},{
	        			text:')',
	        			xtype:'button',
	        			minWidth:10,
	        			handler:function(){
	        			      var o = Ext.getDom('bds'); //The text box to be inserted
	        			      o.focus();

	        			      var value = this.text;
	        			      if (document.selection) {//用于IE
                                  var s = document.selection.createRange();//以先保存选区的方式用于在IE中实现插入
                                  s.text = value;
	        			      }
	        			      else {//用于FF
	        			          o.value = o.value.substr(0, o.selectionStart) + value + o.value.substr(o.selectionEnd);
	        			      } 
	        			}
	        		},{
	        			text:'1',
	        			xtype:'button',
	        			minWidth:10,
	        			handler:function(){
	        			      var o = Ext.getDom('bds'); //The text box to be inserted
	        			      o.focus();

	        			      var value = this.text;
	        			      if (document.selection) {//用于IE
                                  var s = document.selection.createRange();//以先保存选区的方式用于在IE中实现插入
                                  s.text = value;
	        			      }
	        			      else {//用于FF
	        			          o.value = o.value.substr(0, o.selectionStart) + value + o.value.substr(o.selectionEnd);
	        			      } 
	        			}
	        		},{
	        			text:'2',
	        			xtype:'button',
	        			minWidth:10,
	        			handler:function(){
	        			      var o = Ext.getDom('bds'); //The text box to be inserted
	        			      o.focus();

	        			      var value = this.text;
	        			      if (document.selection) {//用于IE
                                  var s = document.selection.createRange();//以先保存选区的方式用于在IE中实现插入
                                  s.text = value;
	        			      }
	        			      else {//用于FF
	        			          o.value = o.value.substr(0, o.selectionStart) + value + o.value.substr(o.selectionEnd);
	        			      } 
	        			}
	        		},{
	        			text:'3',
	        			xtype:'button',
	        			minWidth:10,
	        			handler:function(){
	        			      var o = Ext.getDom('bds'); //The text box to be inserted
	        			      o.focus();

	        			      var value = this.text;
	        			      if (document.selection) {//用于IE
                                  var s = document.selection.createRange();//以先保存选区的方式用于在IE中实现插入
                                  s.text = value;
	        			      }
	        			      else {//用于FF
	        			          o.value = o.value.substr(0, o.selectionStart) + value + o.value.substr(o.selectionEnd);
	        			      } 
	        			}
	        		},{
	        			text:'4',
	        			xtype:'button',
	        			minWidth:10,
	        			handler:function(){
	        			      var o = Ext.getDom('bds'); //The text box to be inserted
	        			      o.focus();

	        			      var value = this.text;
	        			      if (document.selection) {//用于IE
                                  var s = document.selection.createRange();//以先保存选区的方式用于在IE中实现插入
                                  s.text = value;
	        			      }
	        			      else {//用于FF
	        			          o.value = o.value.substr(0, o.selectionStart) + value + o.value.substr(o.selectionEnd);
	        			      } 
	        			}
	        		},{
	        			text:'5',
	        			xtype:'button',
	        			minWidth:10,
	        			handler:function(){
	        			      var o = Ext.getDom('bds'); //The text box to be inserted
	        			      o.focus();

	        			      var value = this.text;
	        			      if (document.selection) {//用于IE
                                  var s = document.selection.createRange();//以先保存选区的方式用于在IE中实现插入
                                  s.text = value;
	        			      }
	        			      else {//用于FF
	        			          o.value = o.value.substr(0, o.selectionStart) + value + o.value.substr(o.selectionEnd);
	        			      } 
	        			}
	        		},{
	        			text:'6',
	        			xtype:'button',
	        			minWidth:10,
	        			handler:function(){
	        			      var o = Ext.getDom('bds'); //The text box to be inserted
	        			      o.focus();

	        			      var value = this.text;
	        			      if (document.selection) {//用于IE
                                  var s = document.selection.createRange();//以先保存选区的方式用于在IE中实现插入
                                  s.text = value;
	        			      }
	        			      else {//用于FF
	        			          o.value = o.value.substr(0, o.selectionStart) + value + o.value.substr(o.selectionEnd);
	        			      } 
	        			}
	        		},{
	        			text:'7',
	        			xtype:'button',
	        			minWidth:10,
	        			handler:function(){
	        			      var o = Ext.getDom('bds'); //The text box to be inserted
	        			      o.focus();

	        			      var value = this.text;
	        			      if (document.selection) {//用于IE
                                  var s = document.selection.createRange();//以先保存选区的方式用于在IE中实现插入
                                  s.text = value;
	        			      }
	        			      else {//用于FF
	        			          o.value = o.value.substr(0, o.selectionStart) + value + o.value.substr(o.selectionEnd);
	        			      } 
	        			}
	        		},{
	        			text:'8',
	        			xtype:'button',
	        			minWidth:10,
	        			handler:function(){
	        			      var o = Ext.getDom('bds'); //The text box to be inserted
	        			      o.focus();

	        			      var value = this.text;
	        			      if (document.selection) {//用于IE
                                  var s = document.selection.createRange();//以先保存选区的方式用于在IE中实现插入
                                  s.text = value;
	        			      }
	        			      else {//用于FF
	        			          o.value = o.value.substr(0, o.selectionStart) + value + o.value.substr(o.selectionEnd);
	        			      } 
	        			}
	        		},{
	        			text:'9',
	        			xtype:'button',
	        			minWidth:10,
	        			handler:function(){
	        			      var o = Ext.getDom('bds'); //The text box to be inserted
	        			      o.focus();

	        			      var value = this.text;
	        			      if (document.selection) {//用于IE
                                  var s = document.selection.createRange();//以先保存选区的方式用于在IE中实现插入
                                  s.text = value;
	        			      }
	        			      else {//用于FF
	        			          o.value = o.value.substr(0, o.selectionStart) + value + o.value.substr(o.selectionEnd);
	        			      } 
	        			}
	        		},{
	        			text:'0',
	        			xtype:'button',
	        			minWidth:10,
	        			handler:function(){
	        			      var o = Ext.getDom('bds'); //The text box to be inserted
	        			      o.focus();

	        			      var value = this.text;
	        			      if (document.selection) {//用于IE
                                  var s = document.selection.createRange();//以先保存选区的方式用于在IE中实现插入
                                  s.text = value;
	        			      }
	        			      else {//用于FF
	        			          o.value = o.value.substr(0, o.selectionStart) + value + o.value.substr(o.selectionEnd);
	        			      } 
	        			}
	        		}]
	            },{
	            	xtype:'textfield',
	            	id:'bds',
	            	bodyStyle : 'padding:5 5 5 5',
	            	width:'100%',
	            	fieldLabel: ''
	            }]
			} ]
		});
		this.mxfs = mxfs;
		this.tjbds = tjbds;
		// this.mxfs.autoloadcontrol = autoloadcontrol;
		this.dataview = dataview;

		this.opstore = new Ext.data.SimpleStore({
			id : 0,
			fields : [ {
				name : 'code',
				mapping : 0
			}, {
				name : 'name',
				mapping : 1
			} ],
			data : [ [ 'null', '空' ], [ '左%', '左包含' ], [ '%', '包含' ], [ '右%', '右包含' ], [ '=', '=' ] ]
			//data : [ [ '>', '>' ], [ '<', '<' ], [ '=', '=' ] ]
		});
		this.opstore1 = new Ext.data.SimpleStore({
			id : 0,
			fields : [ {
				name : 'code',
				mapping : 0
			}, {
				name : 'name',
				mapping : 1
			} ],
			data : [ [ '>', '>' ], [ '<', '<' ], [ '=', '=' ] ,[ '>=', '>=' ],[ '<=', '<=' ],[ '<>', '<>' ],[ 'null', '空' ]]
		});
		var h1= 150;
		var h2= this.height-h1;
		this.items = [
				{
//					layout : 'fit',
					region : 'west',
					width : 270,
					bodyStyle : 'overflow-y:auto;overflow-x:hidden',
					items : [ dataview ]
				},
				{
//					layout : 'fit',
					region : 'center',
					//bodyStyle : 'overflow-y:auto;overflow-x:hidden',
					items : [ {
						region : 'center',
//						layout : 'fit',
						height:h2,
						bodyStyle : 'overflow-y:auto;overflow-x:hidden',
						items : [ mxfs]
					},{
						region : 'south',
//						layout : 'fit',
						height:h1,
						//bodyStyle : 'overflow-y:auto;overflow-x:hidden',
						items : [ tjbds]
					}],
					buttons : [
							new Ext.Button({
								text : '确定',
								minWidth : 75,
								handler : function() {
									var win = this
											.findParentByType("zhcx_dialog");
									var c = win.mxfs.items.get(0);

									if (!win.mxfs.getForm().isValid()) {
										return;
									}
									var order_list = [];
									var isValid = true;
									c.items.each(function(p) {
										var f = p.items.get(0);
										if (f.name) {
											var seek = f.name
													.indexOf(".index_");
											if (seek > 0) {
												order_list.push(f.name
														.substring(0, seek));
											} else {
												if (f.name.indexOf(".") < 0) {
													isValid = isValid
															|| f.isValid();
												}
											}
										}
									});

									if (!isValid) {
										showInfo("数据校验没有通过！");
										return;
									}
									var fdata = win.mxfs.getForm().getValues();
									var where = "";
									var msg = "";
									var opValue ="";
									var fnameArray = [];
									var fnameOpValueArray = [];
									Ext.each(order_list, function(fname) {
										var val = fdata[fname];
										var op = fdata[fname + ".op_"];
										if (((val && val != "")||op=="空")&&fnameArray.indexOf(fname)==-1) {
											fnameArray.push(fname);
											if(typeof val == 'object'){
												for(var i = 0;i<val.length;i++){
													var s = val[i].toUpperCase();
													if (s.indexOf("'") >= 0
															|| s.indexOf("AND") >= 0
															|| s.indexOf("OR") >= 0
															|| s.indexOf("DELETE") >= 0
															|| s.indexOf("INSERT") >= 0
															|| s.indexOf("DROP") >= 0) {
														msg += fname + "内容包含非法字符！"
													}
													if (where != "")
														where += " and ";
													if(op[i]=='='||op[i]=='>'||op[i]=='>='||op[i]=='<'||op[i]=='<='||op[i]=='<>'){
														if(fname=='bggzxm'){
															opValue=op[i] + "'" + Gnt.ux.Dict.getDictLable('CS_BGGZXM', val[i]) + "'";
														}else{
															opValue=op[i] + "'" + val[i] + "'";
														}
													}else if(op[i]=='空'){
														opValue= " is null ";
													}else if(op[i]=='右包含'){
														if(fname=='bggzxm'){
															opValue= " like '%" + Gnt.ux.Dict.getDictLable('CS_BGGZXM', val[i]) + "'";
														}else{
															opValue= " like '%" + val[i] + "'";
														}
													}else if(op[i]=='包含'){
														if(fname=='bggzxm'){
															opValue= " like '%" + Gnt.ux.Dict.getDictLable('CS_BGGZXM', val[i]) + "%'";
														}else{
															opValue= " like '%" + val[i] + "%'";
														}
													}else if(op[i]=='左包含'){
														if(fname=='bggzxm'){
															opValue= " like '" + Gnt.ux.Dict.getDictLable('CS_BGGZXM', val[i]) + "%'";
														}else{
															opValue= " like '" + val[i] + "%'";
														}
													}
													where += fname + opValue;
													fnameOpValueArray.push(fname + opValue);
//													where += fname + op[i] + "'" + val[i]
//															+ "'";
												}
											}else if(typeof val == 'string'){
												var s = val.toUpperCase();
												if (s.indexOf("'") >= 0
														|| s.indexOf("AND") >= 0
														|| s.indexOf("OR") >= 0
														|| s.indexOf("DELETE") >= 0
														|| s.indexOf("INSERT") >= 0
														|| s.indexOf("DROP") >= 0) {
													msg += fname + "内容包含非法字符！"
												}
												if (where != "")
													where += " and ";
												if(op=='='||op=='>'||op=='>='||op=='<'||op=='<='||op=='<>'){
													if(fname=='bggzxm'){
														opValue=op + "'" + Gnt.ux.Dict.getDictLable('CS_BGGZXM', val) + "'";
													}else{
														opValue=op + "'" + val + "'";
													}
												}else if(op=='空'){
													opValue= " is null ";
												}else if(op=='右包含'){
													if(fname=='bggzxm'){
														opValue= " like '%" + Gnt.ux.Dict.getDictLable('CS_BGGZXM', val) + "'";
													}else{
														opValue= " like '%" + val + "'";
													}
													
												}else if(op=='包含'){
													if(fname=='bggzxm'){
														opValue= " like '%" + Gnt.ux.Dict.getDictLable('CS_BGGZXM', val) + "%'";
													}else{
														opValue= " like '%" + val + "%'";
													}
													
												}else if(op=='左包含'){
													if(fname=='bggzxm'){
														opValue= " like '" + Gnt.ux.Dict.getDictLable('CS_BGGZXM', val) + "%'";
													}else{
														opValue= " like '" + val + "%'";
													}
													
												}
												where += fname + opValue;
												fnameOpValueArray.push(fname + opValue);
											}
											
										}
									});

									if (msg != "") {
										showErr(msg);
										return;
									}

									if (where == "") {
										showInfo("至少输入一个查询条件！");
										return;
									}
									var bdsValue = win.tjbds.items.items[0].items.items[1].getValue();
									var myreg = /\d+/g;
									var blsepArray =[];
									var whereEnd ="";
									var lastIndex = 0;
								    while(res = myreg.exec(bdsValue)){
								    	
								    	if(res.index>0){
								    		for(var i =lastIndex;i<res.index;i++){
								    			if(bdsValue.charAt(i)=='*'){
								    				whereEnd += ' and ';
                                                    lastIndex ++;
								    			}else if(bdsValue.charAt(i)=='!'){
								    				whereEnd += ' not ';
                                                    lastIndex ++;
								    			}else if(bdsValue.charAt(i)=='+'){
								    				whereEnd += ' or ';
                                                    lastIndex ++;
								    			}else if(bdsValue.charAt(i)=='('){
								    				whereEnd += ' ( ';
                                                    lastIndex ++;
								    			}else if(bdsValue.charAt(i)==')'){
								    				whereEnd += ' ) ';
                                                    lastIndex ++;
								    			}
								    		}
                                            lastIndex += res[0].length;
								    	}else{
                                            lastIndex ++;
                                        }
								    	if(res[0]>fnameOpValueArray.length){
								    		alert("条件序号："+res[0]+" 设置不正确。");
								    		return;
								    	}

								    	whereEnd += fnameOpValueArray[(res[0]-1)];
								    	var a = RegExp.rightContext;
								    	if(!RegExp.rightContext.match(/\d/g)&&lastIndex<bdsValue.length){

								    		for(var i =lastIndex;i<bdsValue.length;i++){
								    			if(bdsValue.charAt(i)=='*'){
								    				whereEnd += ' and ';
                                                    lastIndex = lastIndex ++;
                                                }else if(bdsValue.charAt(i)=='!'){
								    				whereEnd += ' not ';
                                                    lastIndex = lastIndex ++;
								    			}else if(bdsValue.charAt(i)=='+'){
								    				whereEnd += ' or ';
                                                    lastIndex = lastIndex ++;
								    			}else if(bdsValue.charAt(i)=='('){
								    				whereEnd += ' ( ';
                                                    lastIndex = lastIndex ++;
								    			}else if(bdsValue.charAt(i)==')'){
								    				whereEnd += ' ) ';
                                                    lastIndex = lastIndex ++;
								    			}
								    		}
								    	}
								    	//blsepArray.push(res[0]);
								    	//符合条件的值   起始位置   结束位置
								    	//res[0]  res.index   res.lastIndex
								    }
									win.hide();
									if (win.callback) {
										win.callback(" ( "+whereEnd+" ) ");
									}
								}
							}),
							new Ext.Button({
								text : '取消',
								minWidth : 75,
								handler : function() {
									var win = this
											.findParentByType("zhcx_dialog");
									win.hide();
								}
							}) ]
				}];

		Gnt.ux.ZhcxDialog.superclass.initComponent.call(this);
	}
});
Ext.reg('zhcx_dialog', Gnt.ux.ZhcxDialog);
//Gnt.ux.ZdszDialog = Ext.extend(Ext.Window, {
//	title : '字段显示编辑窗口',
//	closeAction : 'hide',
//	modal : true,
//	width : 500,
//	height : 500,
//	margins : '5',
//	layout : 'border',
//	reset : function() {
//		this.mxfs.getForm().reset();
//	},
//	initComponent : function() {
//		if (!Gnt.loadSjpzb(this.pzlb, function() {
//		}))
//			return;
//
//		var ary = Gnt.ux.Dict.getSjpzData(this.pzlb);
//		var data = {
//			list : ary,
//			totalCount : ary.length,
//			success : true
//		};
//
//		var store = new Ext.data.JsonStore({
//			root : 'list',
//			id : 'sjpzid',
//			data : data,
//			autoLoad : false,
//			totalProperty : 'totalCount',
//			fields : [ "sjpzid", "pzlb", "pzmc", "id", "fieldname",
//					"displayname", "fieldtype", "fieldtypename", "dsname",
//					"readonly", "visibletype", "fieldlength",
//					"displayfieldlength", "ischinese", "mbmod",
//					"allowselfinput", "codefield", "pyfield", "namefield",
//					"partmask", "lsfield", "showls", "enablecopy",
//					"enabledefaultfilter", "enterdropdown", "enteruniqueexit",
//					"usetablefiltered", "useforsfz", "conditionoperator",
//					"conditionfield", "conditiontype", "groups", "bdlx",
//					"vtype", ]
//		});
//
//		var tpl = new Ext.XTemplate('<tpl for=".">',
//				'<div class="dataview-thumb-wrap" id="_sjpz_zhcx_{sjpzid}">',
//				'<span class="x-editable">{displayname}</span>', '</div>',
//				'</tpl>');
//
//		var dataview = new Ext.DataView({
//			region : 'left',
//			store : store,
//			tpl : tpl,
//			multiSelect : true,
//			autoHeight : true,
//			simpleSelect : true,
//			// 一个CSS的样式类，每个元素onmouseover和onmouseout时生效
//			overClass : 'dataview-x-view-over',
//			selectedClass : 'dataview-x-view-selected',
//			// DomQuery选择符告知该类究竟如何分辨出每个item
//			itemSelector : 'div.dataview-thumb-wrap',
//			emptyText : "没有数据",
//			deferEmptyText : true,
//			loadingText : '正在加载数据，请等待...',
//			listeners : {
//				selectionchange : function(view, nodes) {
//					return;
//				},
//				click : function(view, index, node, e) {
//					var data = view.getRecord(node).data;
//					var win = this.findParentByType("zdsz_dialog");
//					var c = win.mxfs.items.get(0);
//
//					if (view.isSelected(node)) {
//						var it = Gnt.getExtField2(data);
//						it.hideLabel = true;
//						var index = c.items.getCount() / 2;
//						c.add({
//							columnWidth : 0.3,
//							layout : 'form',
//							bodyStyle : 'padding:0 0 5 0',
//							items : [ {
//								xtype : 'label',
//								name : it.name + ".index_",
//								text : index
//							} ]
//						});
//
//						c.add({
//							columnWidth : 0.7,
//							layout : 'form',
//							bodyStyle : 'padding:0 0 5 0',
//							items : [ {
//								name : it.name + ".label_",
//								xtype : 'label',
//								text : it.fieldLabel
//							} ]
//						});
//						c.doLayout();
//					} else {
//						// 取消
//						var index = 1;
//						c.items.each(function(p) {
//							var f = p.items.get(0);
//							if (f.name == data.fieldname
//									|| (f.name && f.name.indexOf(data.fieldname
//											+ ".") >= 0)) {
//								c.remove(p);
//							} else {
//								if (f.name && f.name.indexOf(".index_") >= 0) {
//									f.setText(index);
//									index++;
//								}
//							}
//							c.doLayout();
//						});
//					}
//				},
//				dblclick : function(view, index, node, e) {
//
//				}
//			}
//		});
//
//		var mxfs = new Ext.form.FormPanel({
//			title : '自定义设置打印字段',
//			renderTo : Ext.getBody(),
//			bodyStyle : 'padding:5 5 5 5',
//			width : 300,
//			autoHeight : true,
//			frame : false,
//			border : false,
//			layout : 'form',
//			labelWidth : 60,
//			items : [ {
//				layout : 'column',
//				id : 'myfieldset',
//				frame : false,
//				border : false,
//				defaults : {
//					border : false,
//					frame : false
//				},
//				items : [ {
//					columnWidth : 0.3,
//					layout : 'form',
//					bodyStyle : 'padding:5 0 5 0',
//					items : [ {
//						xtype : 'label',
//						html : '<b>序号</b>'
//					} ]
//				}, {
//					columnWidth : 0.7,
//					layout : 'form',
//					bodyStyle : 'padding:5 0 5 0',
//					items : [ {
//						xtype : 'label',
//						anchor : '99%',
//						html : '<b>条件字段</b>'
//					} ]
//				} ]
//			} ]
//		});
//
//		this.mxfs = mxfs;
//		// this.mxfs.autoloadcontrol = autoloadcontrol;
//		this.dataview = dataview;
//
//		this.opstore = new Ext.data.SimpleStore({
//			id : 0,
//			fields : [ {
//				name : 'code',
//				mapping : 0
//			}, {
//				name : 'name',
//				mapping : 1
//			} ],
//			data : [ [ '>', '>' ], [ '<', '<' ], [ '=', '=' ] ]
//		});
//
//		this.items = [ {
//			layout : 'fit',
//			region : 'west',
//			width : 250,
//			bodyStyle : 'overflow-y:auto;overflow-x:hidden',
//			items : [ dataview ]
//		}, {
//			layout : 'fit',
//			region : 'center',
//			bodyStyle : 'overflow-y:auto;overflow-x:hidden',
//			items : [ mxfs ],
//			buttons : [ new Ext.Button({
//				text : '确定',
//				minWidth : 75,
//				handler : function() {
//					var win = this.findParentByType("zdsz_dialog");
//					var c = win.mxfs.items.get(0);
//
//					if (!win.mxfs.getForm().isValid()) {
//						return;
//					}
//
//					var zdKeyArray = new Array();
//					var zdValueArray = new Array();
//					var isValid = true;
//					c.items.each(function(p) {
//						var f = p.items.get(0);
//						if (f.name) {
//							var seek = f.name.indexOf(".index_");
//							if (seek > 0) {
//								zdKeyArray.push(f.name.substring(0, seek));
//							} else {
//								if (f.name.indexOf(".") < 0) {
//									isValid = isValid || f.isValid();
//								}
//							}
//							var seekLabel = f.name.indexOf(".label_");
//							if (seekLabel > 0) {
//								zdValueArray.push(f.text);
//							} else {
//								if (f.name.indexOf(".") < 0) {
//									isValid = isValid || f.isValid();
//								}
//							}
//						}
//					});
//					if (!isValid) {
//						showInfo("数据校验没有通过！");
//						return;
//					}
//					if (zdValueArray.length == 0) {
//						showInfo("至少输入一个打印字段！");
//						return;
//					}
//					win.hide();
//					if (win.callback) {
//						win.callback(zdKeyArray, zdValueArray);
//					}
//				}
//			}), new Ext.Button({
//				text : '取消',
//				minWidth : 75,
//				handler : function() {
//					var win = this.findParentByType("zdsz_dialog");
//					win.hide();
//				}
//			}) ]
//		} ];
//
//		Gnt.ux.ZdszDialog.superclass.initComponent.call(this);
//	}
//});
Gnt.ux.ZdszDialog = Ext.extend(Ext.Window, {
	title : '字段显示编辑窗口',
	closeAction : 'hide',
	modal : true,
	width : 500,
	height : 500,
//	margins : '5',
	layout : 'border',
	reset : function() {
		this.mxfs.getForm().reset();
	},
	initComponent : function() {
		var that = this;
		var bxszdcsm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
		var bxszdcolModel = new Ext.grid.ColumnModel([
			{
				header: "数据配置id",
		        dataIndex: "sjpzid",
		        sortable: true,
		        hidden:true,
				width: 40
			},{
				header: "",
		        dataIndex: "pzlb",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "pzmc",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "id",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "fieldname",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "所有字段",
		        dataIndex: "displayname",
		        sortable: true,
				width: 150
			},{
				header: "",
		        dataIndex: "fieldtype",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "fieldtypename",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "dsname",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "readonly",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "visibletype",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "fieldlength",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "displayfieldlength",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "ischinese",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "mbmod",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "allowselfinput",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "codefield",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "pyfield",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "namefield",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "partmask",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "lsfield",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "showls",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "enablecopy",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "enabledefaultfilter",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "enterdropdown",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "enteruniqueexit",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "usetablefiltered",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "useforsfz",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "conditionoperator",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "conditionfield",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "conditiontype",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "groups",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "bdlx",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "vtype",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "pxid",
		        sortable: true,
				width: 40,
				hidden:true
			}
		]);
		var bxszdStore = new Ext.data.JsonStore({
	 		proxy: new Ext.data.HttpProxy({
	 			url: "dict/utils/querySjpzbGz?flag=" +1,
				method: "POST",
				disableCaching: true
		    }),
	        root: 'list',
	        id:'sjpzid',
	        totalProperty:'totalCount',
	        fields: [ "sjpzid", "pzlb", "pzmc", "id", "fieldname",
				"displayname", "fieldtype", "fieldtypename", "dsname",
				"readonly", "visibletype", "fieldlength",
				"displayfieldlength", "ischinese", "mbmod",
				"allowselfinput", "codefield", "pyfield", "namefield",
				"partmask", "lsfield", "showls", "enablecopy",
				"enabledefaultfilter", "enterdropdown", "enteruniqueexit",
				"usetablefiltered", "useforsfz", "conditionoperator",
				"conditionfield", "conditiontype", "groups", "bdlx",
				"vtype","pxid" ],
	        listeners:{
				loadexception:function(mystore,options,response,error){
					if(error){
						var json = Ext.decode(response.responseText);
						Ext.Msg.alert("提示",json.messages[0]);
					}else{
						Ext.Msg.alert("提示",response.responseText);
					}
				}
	        }
	    });
		that.bxszdGrid =new Ext.grid.GridPanel({
	        store: bxszdStore,
	        region: 'center',
	        view:new Ext.grid.GridView({
					//forceFit:true,
					//autoFill:true,
					enableRowBody:true
			}),
			stripeRows: true,
	        cm: bxszdcolModel,
	        sm:bxszdcsm,
			loadMask: {msg:'正在加载数据，请稍侯……'},
			bodyStyle:'width:80%',
	        border:true,
	        anchor:'100% 100%',
		    margins: '0 0 0 0',
			cmargins: '0 0 0 0',        
	        frame:false,
			iconCls:'icon-grid',
	        title:'',
	        listeners:{
	        	rowclick:function(g, rowIndex, e){
	        		selBxszd = g.store.getAt(rowIndex);
	        		selBxszdRow= rowIndex;
	        		Ext.getCmp('ydsjBtn1').enable();
	        	},
				rowdblclick:function(g, rowIndex, e){
					selBxszd = g.store.getAt(rowIndex);
					selBxszdRow= rowIndex;
					Ext.getCmp('ydsjBtn1').enable();
					var win = this.findParentByType("zdsz_dialog");
					var xszdStore = win.items.items[1].items.items[0].items.items[0].items.items[0].store;
					xszdStore.add(selBxszd);
					bxszdStore.remove(selBxszd);
				}
			}
	    });
		
		var xszdcsm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
		var xszdcolModel = new Ext.grid.ColumnModel([
			{
				header: "数据配置id",
		        dataIndex: "sjpzid",
		        sortable: true,
		        hidden:true,
				width: 40
			},{
				header: "",
		        dataIndex: "pzlb",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "pzmc",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "id",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "fieldname",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "显示字段",
		        dataIndex: "displayname",
		        sortable: true,
				width: 150
			},{
				header: "",
		        dataIndex: "fieldtype",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "fieldtypename",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "dsname",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "readonly",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "visibletype",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "fieldlength",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "displayfieldlength",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "ischinese",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "mbmod",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "allowselfinput",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "codefield",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "pyfield",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "namefield",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "partmask",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "lsfield",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "showls",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "enablecopy",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "enabledefaultfilter",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "enterdropdown",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "enteruniqueexit",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "usetablefiltered",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "useforsfz",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "conditionoperator",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "conditionfield",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "conditiontype",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "groups",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "bdlx",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "vtype",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "pxid",
		        sortable: true,
				width: 40,
				hidden:true
			}
		]);
		var xszdStore = new Ext.data.JsonStore({
	 		proxy: new Ext.data.HttpProxy({
	 			url: "dict/utils/querySjpzbGz?flag=" +2,
				method: "POST",
				disableCaching: true
		    }),
	        root: 'list',
	        id:'pxid',
	        totalProperty:'totalCount',
	        fields: [ "sjpzid", "pzlb", "pzmc", "id", "fieldname",
				"displayname", "fieldtype", "fieldtypename", "dsname",
				"readonly", "visibletype", "fieldlength",
				"displayfieldlength", "ischinese", "mbmod",
				"allowselfinput", "codefield", "pyfield", "namefield",
				"partmask", "lsfield", "showls", "enablecopy",
				"enabledefaultfilter", "enterdropdown", "enteruniqueexit",
				"usetablefiltered", "useforsfz", "conditionoperator",
				"conditionfield", "conditiontype", "groups", "bdlx",
				"vtype","pxid" ],
	        listeners:{
				loadexception:function(mystore,options,response,error){
					if(error){
						var json = Ext.decode(response.responseText);
						Ext.Msg.alert("提示",json.messages[0]);
					}else{
						Ext.Msg.alert("提示",response.responseText);
					}
				}
	        }
	    });
		that.xszdGrid =new Ext.grid.GridPanel({
	        store: xszdStore,
	        region: 'center',
	        view:new Ext.grid.GridView({
					//forceFit:true,
					//autoFill:true,
					enableRowBody:true
			}),
			stripeRows: true,
	        cm: xszdcolModel,
	        sm:xszdcsm,
			loadMask: {msg:'正在加载数据，请稍侯……'},
			bodyStyle:'width:80%',
	        border:true,
	        anchor:'100% 100%',
		    margins: '0 0 0 0',
			cmargins: '0 0 0 0',        
	        frame:false,
			iconCls:'icon-grid',
	        title:'',
	        listeners:{
	        	rowclick:function(g, rowIndex, e){
	        		selXszd = g.store.getAt(rowIndex);
	        		selXszdRow = rowIndex;
					Ext.getCmp('ydsjBtn3').enable();
					Ext.getCmp('ydsjBtn8').enable();
					if(rowIndex==0){
						Ext.getCmp('ydsjBtn10').disable();
						Ext.getCmp('ydsjBtn11').enable();
					}else if((rowIndex+1)==g.store.data.length){
						Ext.getCmp('ydsjBtn10').enable();
						Ext.getCmp('ydsjBtn11').disable();
					}else{
						Ext.getCmp('ydsjBtn10').enable();
						Ext.getCmp('ydsjBtn11').enable();
					}
	        	},
				rowdblclick:function(g, rowIndex, e){
					selXszd = g.store.getAt(rowIndex);
					selXszdRow = rowIndex;
					Ext.getCmp('ydsjBtn3').enable();
					Ext.getCmp('ydsjBtn8').enable();
					var win = this.findParentByType("zdsz_dialog");
					var xszdStore = win.items.items[1].items.items[0].items.items[0].items.items[0].store;
					var bxszdStore = win.items.items[0].items.items[0].items.items[0].store;//win.bxszdGrid.store;
					bxszdStore.add(selXszd);
					xszdStore.remove(selXszd);
				}
			}
	    });
		bxszdStore.baseParams = {
				pzlb:that.pzlb,
				flag: 1
		};
		bxszdStore.load({params:{start:0, limit:9999}});
		xszdStore.baseParams = {
				pzlb:that.pzlb,
				flag: 2
		};
		xszdStore.load({params:{start:0, limit:9999}});
		
		var pxzdcsm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
		var pxzdcolModel = new Ext.grid.ColumnModel([
			{
				header: "数据配置id",
		        dataIndex: "sjpzid",
		        sortable: true,
		        hidden:true,
				width: 40
			},{
				header: "",
		        dataIndex: "pzlb",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "pzmc",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "id",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "fieldname",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "排序字段",
		        dataIndex: "displayname",
		        sortable: true,
				width: 150
			},{
				header: "",
		        dataIndex: "fieldtype",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "fieldtypename",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "dsname",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "readonly",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "visibletype",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "fieldlength",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "displayfieldlength",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "ischinese",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "mbmod",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "allowselfinput",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "codefield",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "pyfield",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "namefield",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "partmask",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "lsfield",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "showls",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "enablecopy",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "enabledefaultfilter",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "enterdropdown",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "enteruniqueexit",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "usetablefiltered",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "useforsfz",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "conditionoperator",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "conditionfield",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "conditiontype",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "groups",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "bdlx",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "vtype",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "pxid",
		        sortable: true,
				width: 40,
				hidden:true
			}
		]);
		var pxzdStore = new Ext.data.JsonStore({
	 		proxy: new Ext.data.HttpProxy({
	 			url: "dict/utils/querySjpzbGz?flag=" +3,
				method: "POST",
				disableCaching: true
		    }),
	        root: 'list',
	        id:'sjpzid',
	        totalProperty:'totalCount',
	        fields: [ "sjpzid", "pzlb", "pzmc", "id", "fieldname",
				"displayname", "fieldtype", "fieldtypename", "dsname",
				"readonly", "visibletype", "fieldlength",
				"displayfieldlength", "ischinese", "mbmod",
				"allowselfinput", "codefield", "pyfield", "namefield",
				"partmask", "lsfield", "showls", "enablecopy",
				"enabledefaultfilter", "enterdropdown", "enteruniqueexit",
				"usetablefiltered", "useforsfz", "conditionoperator",
				"conditionfield", "conditiontype", "groups", "bdlx",
				"vtype","pxid" ],
	        listeners:{
				loadexception:function(mystore,options,response,error){
					if(error){
						var json = Ext.decode(response.responseText);
						Ext.Msg.alert("提示",json.messages[0]);
					}else{
						Ext.Msg.alert("提示",response.responseText);
					}
				}
	        }
	    });

		that.pxzdGrid =new Ext.grid.GridPanel({
	        store:pxzdStore,
	        region:'center',
	        view:new Ext.grid.GridView({
					//forceFit:true,
					//autoFill:true,
					enableRowBody:true
			}),
			stripeRows: true,
	        cm:pxzdcolModel,
	        sm:pxzdcsm,
			loadMask: {msg:'正在加载数据，请稍侯……'},
			bodyStyle:'width:80%',
	        border:true,
	        anchor:'100% 100%',
		    margins: '0 0 0 0',
			cmargins: '0 0 0 0',        
	        frame:false,
			iconCls:'icon-grid',
	        title:'',
	        listeners:{
	        	rowclick:function(g, rowIndex, e){
	        		selPxzd = g.store.getAt(rowIndex);
					selPxzdRow = rowIndex;
					Ext.getCmp('ydsjBtn9').enable();
					if(rowIndex==0){
						Ext.getCmp('ydsjBtn12').disable();
						Ext.getCmp('ydsjBtn13').enable();
					}else if((rowIndex+1)==g.store.data.length){
						Ext.getCmp('ydsjBtn12').enable();
						Ext.getCmp('ydsjBtn13').disable();
					}else{
						Ext.getCmp('ydsjBtn12').enable();
						Ext.getCmp('ydsjBtn13').enable();
					}
	        	},
				rowdblclick:function(g, rowIndex, e){
					
				}
			}
	    });
		that.items = [
		        {
		        	layout:'border',
		        	region:'center',
		        	items:[
		        		{
		        			layout : 'fit',
	  		        		region:'west',
	  		        		width:'70%',
	  		        		height:'80%',
//	  		        		anchor: '100% 80%',
	  		        		title:'',
	  		        		bodyStyle : 'overflow-y:auto;overflow-x:hidden',
	  		        		items:[
	  		        		        that.bxszdGrid
	  		        		]
	        	      },{
	  		        		region:'center',
	  		        		width:'20%',
	  		        		layout:'table',
	  				    	 align:'center',
	  				    	 layoutConfig: {
	  				    		columns: 1
	  				    	 },
	  				    	bodyStyle:'padding:15px',
	  		        		items:[{
					    	    	height:20,
					    	    	border:false,
					    	    	frame:false
	  		        			},new Ext.Button({
	  		        				text:'>',
	  		        				id:'ydsjBtn1',
	  		        				minWidth:40,
	  		        				handler:function(){
	  		        					if(selBxszd){
	  		        						var win = this.findParentByType("zdsz_dialog");
		  									var xszdStore = win.items.items[1].items.items[0].items.items[0].items.items[0].store;
		  									if(!xszdStore.getById(selBxszd.data.sjpzid)){
		  										var rr = new xszdStore.reader.recordType(selBxszd.data,selBxszd.data.sjpzid);
		  										var tempRowIndex = bxszdStore.data.length;
		  										xszdStore.add([rr]);
		  										bxszdStore.remove(selBxszd);
		  										if(tempRowIndex==(selBxszdRow+1)){
		  											win.bxszdGrid.fireEvent("rowclick",win.bxszdGrid,selBxszdRow-1);
		  											win.bxszdGrid.getSelectionModel().selectRange(selBxszdRow,selBxszdRow);
		  										}else{
		  											win.bxszdGrid.fireEvent("rowclick",win.bxszdGrid,selBxszdRow);
			  										win.bxszdGrid.getSelectionModel().selectRange(selBxszdRow,selBxszdRow);
		  										}
		  									}
	  		        					}
	  		        				}
	  		        			}),{
					    	    	height:10,
					    	    	border:false,
					    	    	frame:false
					    	    },new Ext.Button({
	  		        				text:'>>',
	  		        				id:'ydsjBtn2',
	  		        				minWidth:40,
	  		        				handler:function(){
	  		        					var win = this.findParentByType("zdsz_dialog");
	  									var xszdStore = win.items.items[1].items.items[0].items.items[0].items.items[0].store;
	  									var bxszdStore = win.items.items[0].items.items[0].items.items[0].store;
	  									bxszdStore.each(function(r){
	  										xszdStore.add(r);
	  										bxszdStore.remove(r);
	  		        					});
	  									Ext.getCmp('ydsjBtn1').disable();
	  									Ext.getCmp('ydsjBtn2').disable();
	  									Ext.getCmp('ydsjBtn3').disable();
	  									Ext.getCmp('ydsjBtn4').enable();
	  		        				}
	  		        			}),{
					    	    	height:10,
					    	    	border:false,
					    	    	frame:false
					    	    },
	  		        			new Ext.Button({
	  		        				text:'<',
	  		        				id:'ydsjBtn3',
	  		        				minWidth:40,
	  		        				handler:function(){
	  		        					if(selXszd){
	  		        						var win = this.findParentByType("zdsz_dialog");
	  		        						var xszdStore = win.items.items[1].items.items[0].items.items[0].items.items[0].store;
		  									var bxszdStore = win.items.items[0].items.items[0].items.items[0].store;//win.bxszdGrid.store;
		  									if(!bxszdStore.getById(selXszd.data.sjpzid)){
		  										var rr = new xszdStore.reader.recordType(selXszd.data,selXszd.data.sjpzid);
		  										var tempRowIndex = xszdStore.data.length;
		  										bxszdStore.add([rr]);
		  										xszdStore.remove(selXszd);
		  										bxszdStore.sort('sjpzid', 'ASC');
		  										if(tempRowIndex==(selXszdRow+1)){
		  											win.xszdGrid.fireEvent("rowclick",win.xszdGrid,selXszdRow-1);
		  											win.xszdGrid.getSelectionModel().selectRange(selXszdRow,selXszdRow);
		  										}else{
		  											win.xszdGrid.fireEvent("rowclick",win.xszdGrid,selXszdRow);
			  										win.xszdGrid.getSelectionModel().selectRange(selXszdRow,selXszdRow);
		  										}
		  									}
	  		        					}
	  		        				}
		  		        		}),{
					    	    	height:10,
					    	    	border:false,
					    	    	frame:false
					    	    },
		  		        		new Ext.Button({
	  		        				text:'<<',
	  		        				id:'ydsjBtn4',
	  		        				minWidth:40,
	  		        				handler:function(){
	  		        					var win = this.findParentByType("zdsz_dialog");
	  									var bxszdStore = win.items.items[0].items.items[0].items.items[0].store;//win.bxszdGrid.store;
	  									var xszdStore = win.items.items[1].items.items[0].items.items[0].items.items[0].store;
	  									xszdStore.each(function(r){
	  										bxszdStore.add(r);
	  										xszdStore.remove(r);
	  		        					});
	  									bxszdStore.sort('sjpzid', 'ASC');
	  									Ext.getCmp('ydsjBtn1').disable();
	  									Ext.getCmp('ydsjBtn2').enable();
	  									Ext.getCmp('ydsjBtn3').disable();
	  									Ext.getCmp('ydsjBtn4').disable();
	  		        				}
		  		        		}),
		  		        		{
					    	    	height:120,
					    	    	border:false,
					    	    	frame:false
	  		        			}
	  		        		]
		 			    }
		        	]
		        },{
 			    	layout:'border',
		        	region:'east',
		        	border:false,
	    	    	frame:false,
		        	width:'50%',
	        		items:[{
	        			layout:'anchor',
	        			region:'center',
        				items:[
        					{
		 			    	layout:'fit',
	  		        		region:'north',
	  		        		//height:'40%',
	  		        		anchor: '100% 50%',
	  		        		title:'',
	  		        		bodyStyle : 'overflow-y:auto;overflow-x:hidden',
	  		        		items:[that.xszdGrid]
		 			    },{
		 			    	layout:'border',
	  		        		region:'center',
	  		        		layout:'table',
	  		        		//height:'20%',
	  		        		anchor: '100% 10%',
	  				    	layoutConfig: {
	  				    		columns: 5
	  				    	},
	  				    	bodyStyle:'padding:10px',
	  		        		items:[{
				    	    	//width:80,
				    	    	border:false,
				    	    	frame:false,
				    	    	html:''
  		        			},
	  		        			new Ext.Button({
	  		        				text:'↓',
	  		        				id:'ydsjBtn8',
	  		        				minWidth:40,
	  		        				disabled:true,
	  		        				handler:function(){
	  		        					if(selXszd){
	  		        						var win = this.findParentByType("zdsz_dialog");
		  									var pxzdstore = win.items.items[1].items.items[0].items.items[2].items.items[0].store;
		  									if(!pxzdstore.getById(selXszd.data.sjpzid)){
		  										var rr = new pxzdStore.reader.recordType(selXszd.data,selXszd.data.sjpzid);
		  										pxzdstore.add([rr]);
		  									}
	  		        					}
	  		        				}
	  		        			}),{
					    	    	width:10,
					    	    	border:false,
					    	    	frame:false
	  		        			},new Ext.Button({
	  		        				text:'↑',
	  		        				id:'ydsjBtn9',
	  		        				disabled:true,
	  		        				minWidth:40,
	  		        				handler:function(){
	  		        					if(selPxzd){
	  		        						var win = this.findParentByType("zdsz_dialog");
		  									var pxzdstore = win.items.items[1].items.items[0].items.items[2].items.items[0].store;
		  									if(pxzdstore.getById(selPxzd.data.sjpzid)){
		  										var removeRow = pxzdstore.getAt(selPxzdRow);
		  										pxzdstore.remove(removeRow);
		  									}
		  									if(pxzdstore.data.length==0){
		  										Ext.getCmp('ydsjBtn9').disable();
		  									}
	  		        					}
	  		        				}
	  		        			})
	  		        		]
		 			    },{
		 			    	layout:'fit',
	  		        		region:'south',
	  		        		border:false,
			    	    	frame:false,
	  		        		anchor: '100% 40%',
	  		        		bodyStyle : 'overflow-y:auto;overflow-x:hidden',
	  		        		items:[
	  		        			that.pxzdGrid
	  		        		]
		 			    }
	        		]
	        		},{
					layout:'fit',
					region:'east',
					width:'40%',
					items:[{
	 			    	layout:'fit',
  		        		region:'center',
  		        		layout:'table',
  		        		bodyStyle:'padding:10px',
  				    	layoutConfig: {
  				    		columns: 1
  				    	},
  				    	
  		        		items:[
  		        			{
				    	    	height:40,
				    	    	border:false,
				    	    	frame:false
				    	    },
				    	    new Ext.Button({
  		        				text:'↑',
  		        				id:'ydsjBtn10',
  		        				disabled:true,
  		        				minWidth:40,
  		        				handler:function(){
  		        					var win = this.findParentByType("zdsz_dialog");
	        						var xszdStore = win.items.items[1].items.items[0].items.items[0].items.items[0].store;
  									if(selXszd&&selXszdRow>0){
  										dqSelRes = xszdStore.getAt(selXszdRow);
  										preSelRes = xszdStore.getAt(selXszdRow-1);
  										var tempPxid = null;
  										if(dqSelRes&&preSelRes){
  											tempPxid = dqSelRes.data.pxid;
  	  										dqSelRes.data.pxid=preSelRes.data.pxid;
  	  										preSelRes.data.pxid=tempPxid;
  	  										xszdStore.sort('pxid', 'ASC');
  	  										win.xszdGrid.getSelectionModel().selectRange(selXszdRow-1,selXszdRow-1);
  	  										win.xszdGrid.fireEvent("rowclick",win.xszdGrid,selXszdRow-1)
  										}
  									}
  		        				}
  		        			}),	
  		        			{
				    	    	height:10,
				    	    	border:false,
				    	    	frame:false
				    	    },
				    	    new Ext.Button({
  		        				text:'↓',
  		        				id:'ydsjBtn11',
  		        				disabled:true,
  		        				minWidth:40,
  		        				handler:function(){
  		        					var win = this.findParentByType("zdsz_dialog");
	        						var xszdStore = win.items.items[1].items.items[0].items.items[0].items.items[0].store;
  									if(selXszd&&selXszdRow<xszdStore.data.length){
  										dqSelRes = xszdStore.getAt(selXszdRow);
  										nextSelRes = xszdStore.getAt(selXszdRow+1);
  										var tempPxid = null;
  										tempPxid = nextSelRes.data.pxid;
  										nextSelRes.data.pxid=dqSelRes.data.pxid;
  										dqSelRes.data.pxid=tempPxid;
  										xszdStore.sort('pxid', 'ASC');
  										win.xszdGrid.getSelectionModel().selectRange(selXszdRow+1,selXszdRow+1);
  										win.xszdGrid.fireEvent("rowclick",win.xszdGrid,selXszdRow+1)
  									}
  		        				}
  		        			}),{
				    	    	height:200,
				    	    	border:false,
				    	    	frame:false
				    	    },
				    	    new Ext.Button({
  		        				text:'↑',
  		        				id:'ydsjBtn12',
  		        				disabled:true,
  		        				minWidth:40,
  		        				handler:function(){
  		        					var win = this.findParentByType("zdsz_dialog");
	        						var pxzdStore = win.pxzdGrid.store;
  									if(selPxzd&&selPxzdRow>0){
  										dqSelRes = pxzdStore.getAt(selPxzdRow);
  										preSelRes = pxzdStore.getAt(selPxzdRow-1);
  										var tempPxid = null;
  										tempPxid = dqSelRes.data.pxid;
  										dqSelRes.data.pxid=preSelRes.data.pxid;
  										preSelRes.data.pxid=tempPxid;
  										pxzdStore.sort('pxid', 'ASC');
  										win.pxzdGrid.getSelectionModel().selectRange(selPxzdRow-1,selPxzdRow-1);
  										win.pxzdGrid.fireEvent("rowclick",win.pxzdGrid,selPxzdRow-1)
  									}
  		        				
  		        				}
  		        			}),
  		        			{
				    	    	height:10,
				    	    	border:false,
				    	    	frame:false
				    	    },
				    	    new Ext.Button({
  		        				text:'↓',
  		        				id:'ydsjBtn13',
  		        				disabled:true,
  		        				minWidth:40,
  		        				handler:function(){
  		        					var win = this.findParentByType("zdsz_dialog");
	        						var pxzdStore = win.pxzdGrid.store;
  									if(selPxzd&&selPxzdRow<pxzdStore.data.length){
  										dqSelRes = pxzdStore.getAt(selPxzdRow);
  										nextSelRes = pxzdStore.getAt(selPxzdRow+1);
  										var tempPxid = null;
  										tempPxid = nextSelRes.data.pxid;
  										nextSelRes.data.pxid=dqSelRes.data.pxid;
  										dqSelRes.data.pxid=tempPxid;
  										pxzdStore.sort('pxid', 'ASC');
  										win.pxzdGrid.getSelectionModel().selectRange(selPxzdRow+1,selPxzdRow+1);
  										win.pxzdGrid.fireEvent("rowclick",win.pxzdGrid,selPxzdRow+1)
  									}
  		        				}
  		        			}),
  		        			{
				    	    	height:5,
				    	    	border:false,
				    	    	frame:false
				    	    },
				    	    new Ext.Button({
  		        				text:'确定',
  		        				minWidth:40,
  		        				handler:function(){
  		        					var win = this.findParentByType("zdsz_dialog");
  		        					//win.items.items[1].items.items[0].items.items[0].items.items[0].store;
  		        					var store = win.items.items[1].items.items[0].items.items[0].items.items[0].store;
									if (store.data.length==0) {
										return;
									}
									var zdKeyArray = new Array();
									var zdValueArray = new Array();
									store.each(function(p) {
										if(p.data){
											zdKeyArray.push(p.data.fieldname);
											zdValueArray.push(p.data.displayname);
										}
									});
									if (zdValueArray.length == 0) {
										showInfo("至少输入一个打印字段！");
										return;
									}
									var pxstore = win.items.items[1].items.items[0].items.items[2].items.items[0].store;
									var pxzdValue = "";
									pxstore.each(function(p) {
										if(p.data){
											pxzdValue = pxzdValue + "," + p.data.fieldname;
										}
									});
									win.hide();
									if (win.callback) {
										win.callback(zdKeyArray, zdValueArray,pxzdValue);
									}
  		        				}
  		        			}),
  		        			{
				    	    	height:5,
				    	    	border:false,
				    	    	frame:false
				    	    },
				    	    new Ext.Button({
  		        				text:'取消',
  		        				minWidth:40,
  		        				handler:function(){
  		        					var win = this.findParentByType("zdsz_dialog");
  		        					win.hide();
  		        				}
  		        			})
  		        		]
	 			    }]
				}
	        			
	        ]
 		},{
 			layout:'fit',
 			region:'south',
        	border:false,
	    	frame:false,
	    	height:40
	    	
 		}];
		
		Gnt.ux.ZdszDialog.superclass.initComponent.call(this);
	}
});
Ext.reg('zdsz_dialog', Gnt.ux.ZdszDialog);

// 读卡
Gnt.ux.DkButton = Ext.extend(Ext.Button, {
	text : '读卡',
	minWidth : 80,
	handler : function() {
		if (!this.form) {
			showInfo("必须提供form参数指定业务表单，以获取身份证，准迁证，迁入地等信息！");
			return null;
		}
		this.form.getForm().reset();
		initReadCard();
		checkOcx(0);
		data = readCard(0);
		if (data.lastError != "") {
			showInfo(data.lastError);
		} else {
			var frm = this.form.getForm();
			if (frm.findField('xb')) {
				frm.findField('xb').setValue(data.Sex);
			}
			if (frm.findField('xm')) {
				frm.findField('xm').setValue(data.Name);
			}
			if (frm.findField('mz')) {
				frm.findField('mz').setValue(data.Nation);
			}
			if (frm.findField('csrq')) {
				frm.findField('csrq').setValue(data.Born);
			}
			if (frm.findField('gmsfhm')) {
				frm.findField('gmsfhm').setValue(data.CardNo);
			}
			this.callback(data);
		}
	},
	constructor : function(c) {
		Ext.apply(this, c);
		Gnt.ux.DkButton.superclass.constructor.call(this, c);
	},
	initComponent : function(config) {
		Gnt.ux.DkButton.superclass.initComponent.call(this);
	}
});
Ext.reg('DkButton', Gnt.ux.DkButton);
/**
 * 查看档案 add by zjm 20190812
 * 
 */
Gnt.ux.CkdaButton = Ext.extend(Ext.Button, {
	text : '查看档案',
	minWidth : 100,
	handler : function() {
		if (!this.hzywid) {
			showInfo("户政平台中间表对象不能为空！");
			return null;
		}
		var sbid = "";
		Gnt.makeHzyw({
			hzywid : this.hzywid,
			clbs:this.clbs,
			callback : function(jo, jolist) {
				Gnt.ux.Dict.getKzcs('10029', function(pz, user) {
					var url = pz.bz;
					if (url.indexOf("?") < 0)
						url += "?";
					else
						url += "&";
					url += "yhsfz=" + user.gmsfhm + "&tokey=" + user.tokey
							+ "&sbid=" + jo.sbid;
					window.open(url);
				});
			}
		});

	},
	constructor : function(c) {
		Ext.apply(this, c);
		Gnt.ux.DkButton.superclass.constructor.call(this, c);
	},
	initComponent : function(config) {
		Gnt.ux.DkButton.superclass.initComponent.call(this);
	}
});
Ext.reg('CkdaButton', Gnt.ux.CkdaButton);
/**
 * 保存提交前，验证中间表clbs字段是否为0 20190812 add by zjm
 * 
 */
Gnt.ux.CheckClbsBeforeSaveButton = Ext.extend(Ext.Button, {
	text : '保存',
	minWidth : 100,
	handler : function() {
		that = this;
		if (that.hzywid) {// hzywid不为空 说明是从户政平台过来的业务
			var subdata = {
				config_key : 'queryPoHZ_ZJ_SB',
				hzywid : this.hzywid
			};
			Gnt.submit(subdata, "yw/common/queryHzywAndZB.json", "请稍后...",
					function(jsonData, params) {
						if (jsonData.list && jsonData.list.length > 0) {
							var hzFlag = false;
							for (var i = 0; i < jsonData.list.length; i++) {
								var jo = jsonData.list[i];

								if (jo.clbs != 0) {
									hzFlag = true;
									break;
								}
							}
							if (hzFlag) {
								showInfo("户政业务受理环节未处理完成");
								return;
							} else {
								that.callback();
							}
						} else {
							showInfo("没有找到户政业务，或者已经被处理！");
							return;
						}
					});
		} else {// hzywid 为空 说明不是从户政平台过来的业务
			this.callback();
		}

	},
	callbackA : function() {

	},
	constructor : function(c) {
		Ext.apply(this, c);
		Gnt.ux.CheckClbsBeforeSaveButton.superclass.constructor.call(this, c);
	},
	initComponent : function(config) {
		Gnt.ux.CheckClbsBeforeSaveButton.superclass.initComponent.call(this);
	}
});
Ext.reg('CheckClbsBeforeSaveButton', Gnt.ux.CheckClbsBeforeSaveButton);

// 解锁按钮 add by zjm 20191021
Gnt.ux.JsryzlDialog = Ext.extend(Ext.Window, {
	title : '解锁人员资料',
	closeAction : 'hide',
	modal : true,
	width : 400,
	height : 200,
	margins : '5',
	layout : 'fit',
	reset : function() {
		this.fs.getForm().reset();
	},
	initComponent : function() {
		var fs = new Ext.form.FormPanel({
			height : 80,
			region : 'north',
			anchor : '100% 100%',
			buttonAlign : 'right',
			labelAlign : 'right',
			frame : true,
			border : false,
			fileUpload : true,
			margins : '0',
			layout : 'form',
			labelWidth : 60,
			items : [ {
				layout : 'column',
				frame : false,
				border : false,
				defaults : {
					border : false,
					frame : false
				},
				items : [ {
					layout : 'column',
					frame : false,
					border : false,
					defaults : {
						border : false,
						frame : false
					},
					items : [ {
						columnWidth : 1,
						layout : 'form',
						labelWidth : 120,
						bodyStyle : 'padding:0 0 0 0',
						items : [ {
							xtype : 'textarea',
							height : 60,
							anchor : '100%',
							name : 'jsryzlyy',
							allowBlank : false,
							fieldLabel : '解锁人员资料原因'
						} ]
					} ]
				} ]
			} ],
			buttons : [ {
				text : '确定',
				minWidth : 75,
				handler : function() {
					var rootWin = this.findParentByType("jsryz_dialog");
					var formdata = rootWin.items.get(0);
					if (!formdata.getForm().isValid()) {
						showErr("解锁原因必须填写！");
						return;
					}
					if (rootWin.callback) {
						rootWin.callback('js', formdata.getForm().getValues());
					}
					rootWin.hide();
				}
			}, {
				text : '取消',
				minWidth : 75,
				handler : function() {
					var win = this.findParentByType("jsryz_dialog");
					win.hide();
				}
			} ]
		});
		this.items = [ fs ];

		Gnt.ux.JsryzlDialog.superclass.initComponent.call(this);
	}
});
Ext.reg('jsryz_dialog', Gnt.ux.JsryzlDialog);
// 锁定按钮 add by zjm 20191021
Gnt.ux.SdryzlDialog = Ext.extend(Ext.Window, {
	title : '锁定人员资料',
	closeAction : 'hide',
	modal : true,
	width : 400,
	height : 200,
	margins : '5',
	layout : 'fit',
	reset : function() {
		this.fs.getForm().reset();
	},
	initComponent : function() {
		var fs = new Ext.form.FormPanel({
			height : 80,
			region : 'north',
			anchor : '100% 100%',
			buttonAlign : 'right',
			labelAlign : 'right',
			frame : true,
			border : false,
			fileUpload : true,
			margins : '0',
			layout : 'form',
			labelWidth : 60,
			items : [ {
				layout : 'column',
				frame : false,
				border : false,
				defaults : {
					border : false,
					frame : false
				},
				items : [ {
					layout : 'column',
					frame : false,
					border : false,
					defaults : {
						border : false,
						frame : false
					},
					items : [ {
						columnWidth : 1,
						layout : 'form',
						labelWidth : 120,
						bodyStyle : 'padding:0 0 0 0',
						items : [ {
							xtype : 'textarea',
							height : 60,
							anchor : '100%',
							// labelWidth:220,
							name : 'sdryzlyy',
							allowBlank : false,
							fieldLabel : '锁定人员资料原因'
						} ]
					} ]
				} ]
			} ],
			buttons : [ {
				text : '确定',
				minWidth : 75,
				handler : function() {
					var rootWin = this.findParentByType("sdryz_dialog");
					var formdata = rootWin.items.get(0);
					if (!formdata.getForm().isValid()) {
						showErr("锁定原因必须填写！");
						return;
					}
					if (rootWin.callback) {
						rootWin.callback('sd', formdata.getForm().getValues());
					}
					rootWin.hide();
				}
			}, {
				text : '取消',
				minWidth : 75,
				handler : function() {
					var win = this.findParentByType("sdryz_dialog");
					win.hide();
				}
			} ]
		});
		this.items = [ fs ];
		Gnt.ux.SdryzlDialog.superclass.initComponent.call(this);
	}
});
Ext.reg('sdryz_dialog', Gnt.ux.SdryzlDialog);

Gnt.ux.BjfDialog = Ext.extend(Ext.Window, {
	title : '作废原因选择框',
	closeAction : 'hide',
	modal : true,
	width : 400,
	height : 120,
	margins : '5',
	layout : 'fit',
	reset : function() {
		this.fs.getForm().reset();
	},
	initComponent : function() {
		var form = new Gnt.ux.SjpzForm({
			closable : false,
			region : 'south',
			height : 40,
			cols : 1,
			pzlb : '50004',
			labelWidth : 120,
			// 选择人的来源
			// bindSelectRyStore: ywGrid.store,
			changeDictCust : function(cmb, res) {
			},
			buttons : [ {
				text : '确定',
				minWidth : 75,
				handler : function() {
					var rootWin = this.findParentByType("bjf_dialog");
					var formdata = rootWin.form.getForm().getValues();
					if (!formdata.bzxjfyy) {
						showErr("不在线缴费原因必须填写！");
						return;
					}
					if (rootWin.callback) {
						rootWin.callback('bzxjf', formdata);
					}
					rootWin.hide();
				}
			}, {
				text : '取消',
				minWidth : 75,
				handler : function() {
					var win = this.findParentByType("bjf_dialog");
					win.hide();
				}
			} ],
			title : ''
		});
		this.form = form;
		this.items = form;

		Gnt.ux.BjfDialog.superclass.initComponent.call(this);
	}
});
Ext.reg('bjf_dialog', Gnt.ux.BjfDialog);
Gnt.ux.ZxjfDialog = Ext.extend(Ext.Window, {
	title : '在线缴费',
	closeAction : 'hide',
	modal : true,
	width : 400,
	height : 300,
	margins : '5',
	layout : 'fit',
	reset : function() {
		this.fs.getForm().reset();
	},
	initComponent : function() {

		this.sfEwmPanel = new Ext.Panel({
			frame : false,
			border : false,
			region : 'center',
			layout : 'column',
			title : '',
			margins : '5',
			height : 100,
			columnWidth : 1,
			defaults : {
				frame : false,
				border : false,
				bodyStyle : 'padding:5px 5px 0px 5px'
			},
			items : [ {
				layout : 'form',
				items : [ {
					title : '',
					height : 480,
					html : 111 + '<div ><img src="data:image/jpg;base64,'
							+ "rec.zp" + '" width="100%" /></DIV>'
				} ]
			} ],
			buttons : [ {
				text : '确定',
				minWidth : 75,
				handler : function() {
					var rootWin = this.findParentByType("zxjf_form");
					var formdata = rootWin.form.getForm().getValues();
					if (!formdata.bzxjfyy) {
						showErr("不在线缴费原因必须填写！");
						return;
					}
					if (rootWin.callback) {
						rootWin.callback('zxjf', formdata);
					}
					rootWin.hide();
				}
			}, {
				text : '取消',
				minWidth : 75,
				handler : function() {
					var win = this.findParentByType("zxjf_form");
					win.hide();
				}
			} ]
		});
		var form = new Gnt.ux.SjpzForm({
			closable : false,
			region : 'south',
			height : 40,
			cols : 1,
			pzlb : '50004',
			labelWidth : 120,
			// 选择人的来源
			changeDictCust : function(cmb, res) {
			},
			buttons : [ {
				text : '确定',
				minWidth : 75,
				handler : function() {
					var rootWin = this.findParentByType("zxjf_form");
					var formdata = rootWin.form.getForm().getValues();
					if (!formdata.bzxjfyy) {
						showErr("不在线缴费原因必须填写！");
						return;
					}
					if (rootWin.callback) {
						rootWin.callback('zxjf', formdata);
					}
					rootWin.hide();
				}
			}, {
				text : '取消',
				minWidth : 75,
				handler : function() {
					var win = this.findParentByType("zxjf_form");
					win.hide();
				}
			} ],
			title : ''
		});
		this.items = [ this.sfEwmPanel ];
		Gnt.ux.ZxjfDialog.superclass.initComponent.call(this);
	}
});
Ext.reg('zxjf_form', Gnt.ux.ZxjfDialog);
Gnt.ux.SffsConfirmForm = Ext.extend(Ext.form.FormPanel, {
	buttonAlign : 'right',
	labelAlign : 'right',
	layout : 'form',
	labelWidth : 80,
	region : 'center',
	reset : function(je, num, array, data) {
//		Ext.getCmp('je').setValue(je);
		this.dyObj = array[num];
		this.array = array;
		this.data = data;
		this.num = num;
	},
	initComponent : function() {
		this.items = [ {
			layout : 'column',
			frame : false,
			border : false,
			// 奇怪，重点所在，否则columnWidth不起作用
			bodyStyle : 'padding:5px 20px 0px 0px',
			defaults : {
				frame : false,
				border : false,
				labelWidth : this.labelWidth ? this.labelWidth : 100,
				bodyStyle : 'padding:0px 0px 0px 0px'
			},
			items : [ {
				layout : 'form',
				columnWidth : 1,
				defaultType : 'textfield',
				bodyStyle : 'width:100%',
				items : [ {
					id : 'je',
					name : 'je',
					anchor : '99%',
					xtype : 'numberfield',
					readOnly:true,
					value:6,
					autoCreate : {
						tag : 'input',
						type : 'text',
						size : '20',
						autocomplete : 'off',
						maxlength : '8'
					},
					fieldLabel : '打印户口簿首页收费共计'
				} ]
			}/*,{
				columnWidth : 1,
				layout : 'form',
				bodyStyle : 'width:100%',
				items : [ {
					frame : false,
					border : false,
					bodyStyle : 'padding:0px 0px 0px 40px',
					html : '是否在线收费？'
				} ]
			}*/],
			buttons : [
					{
						id : 'btn-query',
						name : 'btn-query',
						disabled:true,
						text : '非现金',
						handler : function() {
							var rootWin = this.findParentByType("sffs_form");
							goDelete("yes", Ext.getCmp('je').getValue(),
									rootWin.num, rootWin.dyObj, rootWin.array,
									rootWin.data)
							sffsWin.hide();
						}
					},
					{
						id : 'btn-query',
						name : 'btn-query',
						text : '现金',
						handler : function() {
							var rootWin = this.findParentByType("sffs_form");
							goDelete("no",6,
									rootWin.num, rootWin.dyObj, rootWin.array,
									rootWin.data);
							sffsWin.hide();

						}
					} ]
		} ];

		Gnt.ux.SffsConfirmForm.superclass.initComponent.call(this);
	}
});
Ext.reg('sffs_form', Gnt.ux.SffsConfirmForm);
var sffs_form = new Gnt.ux.SffsConfirmForm({
	callback : function(/* zjlx,zqzbh,array */) {
	}
});
var sffsWin = new Ext.Window({
	title : '提示',
	closeAction : 'hide',
	modal : true,
	width : 300,
	height : 120,
	layout : 'fit',
	items : sffs_form,
	listeners : {
		show : function() {
		}
	}
});
function goDelete(btn, je, num, dyObj, array, data) {
	// alert("the pressed button is '"+btn+"'");
	if (btn == "yes") {
		var zxjf_form = new Gnt.ux.ZxjfDialog({
			callback : function(type) {
				// 不缴费后续操作
				updateSfxxb(sfxxbid, 'zx');
				// printfunction(0,arrayTemp,data);
				if (window.confirm('是否要打印[' + dyObj.xm + ']的户口簿首页？')) {
					CreateFormPage1(dyObj.directTable, Ext.util.JSON
							.encode(data), dyObj.rynbid, num, array, data);
				} else {
					num++;
					return printfunction(num, array, data);
				}
			}
		});
		zxjf_form.show();
		// 收费表插入一条记录
		var subdata = {
			sfxxb : {
				sffs : 0,// 0 在线收费
				dylb : dyObj.directTable,
				je : je,
				gmsfhm : dyObj.gmsfhm,
				xm : dyObj.xm
			}
		};
		subdata.sfxxb = Ext.encode(subdata.sfxxb);
		Gnt.submit(subdata, "yw/common/insertSfxxb", "收费信息表插入记录中，请稍后...",
				function(jsonData, params) {
					if (jsonData.list && jsonData.list[0]) {
						sfxxbid = jsonData.list[0].sfxxbid;
						zxjf_form.show();
					}
				});
	} else if (btn == "no") {
//		var bjf_form = new Gnt.ux.BjfDialog({
//			callback : function(type, bzxdata) {
//				// 不缴费后续操作
//				//updateSfxxb(sfxxbid, 'bzx', bzxdata.bzxjfyy);
//				// printfunction(0,arrayTemp,data);
//
//				// 收费表插入一条记录
//				var subdata = {
//					sfxxb : {
//						sffs : 1,// 1 不在线收费
//						dylb : dyObj.directTable,
//						je : je,
//						gmsfhm : dyObj.gmsfhm,
//						xm : dyObj.xm,
//						bzxjfyy:bzxdata.bzxjfyy
//					}
//				};
//				subdata.sfxxb = Ext.encode(subdata.sfxxb);
//				Gnt.submit(subdata, "yw/common/insertSfxxb", "收费信息表插入记录中，请稍后...",
//						function(jsonData, params) {
//							if (jsonData.list && jsonData.list[0]) {
////								sfxxbid = jsonData.list[0].sfxxbid;
////								bjf_form.show();
//							}
//						});
//				
//				if (window.confirm('是否要打印[' + dyObj.xm + ']的户口簿首页？')) {
//					CreateFormPage1(dyObj.directTable, Ext.util.JSON
//							.encode(data), dyObj.rynbid, num, array, data);
//				} else {
//					num++;
//					return printfunction(num, array, data);
//				}
//			}
//		});
//		bjf_form.show();
		// 收费表插入一条记录
		var subdata = {
			sfxxb : {
				sffs : 1,// 1 不在线收费
				dylb : dyObj.directTable,
				je : je,
				gmsfhm : dyObj.gmsfhm,
				xm : dyObj.xm,
				bzxjfyy:4
			}
		};
		subdata.sfxxb = Ext.encode(subdata.sfxxb);
		Gnt.submit(subdata, "yw/common/insertSfxxb", "收费信息表插入记录中，请稍后...",
				function(jsonData, params) {
					if (jsonData.list && jsonData.list[0]) {
					}
				});
		
		if (window.confirm('是否要打印[' + dyObj.xm + ']的户口簿首页？')) {
			CreateFormPage1(dyObj.directTable, Ext.util.JSON
					.encode(data), dyObj.rynbid, num, array, data);
		} else {
			num++;
			return printfunction(num, array, data);
		}

	}
};
//自定义grid 列
Gnt.ZdyColumn = function(ary) {
	
	var cm = new Array();
	if(ary instanceof Array){
		for(var i=0;i<ary.length;i++){
			var data = ary[i].data;
			var visibletype = data.visibletype;
			var dsname = data.dsname;

			var item = null;
			var size = parseInt(data.fieldlength);
			if(size<40) size = 40;
			size = size *2;
			if(data.fieldname.indexOf("gmsfhm")>=0){
				size = 180;
			}
			if(data.fieldname.indexOf("hh")>=0){
				size = 100;
			}
			
			item = {
					 header: data.displayname,
					 dataIndex: data.fieldname,	
					 sortable: true,
					 width: size
			}
			
			if(dsname && dsname!=""){
				var renderer = "item.renderer=function(value, cellmeta, record, rowIndex,columnIndex, store){"
					+ "if(store.bind_grid && store.bind_grid.callCellmeta){"
					+	"	var css = store.bind_grid.callCellmeta(record);"
					+ "	if(css && css.length!=''){"
					+ "		cellmeta.attr = \"style='color:\" + css + \";'\";"
					+ "	}"
					+ "}"
					+ "if(record.data._sel == '1') cellmeta.css='changeplus';"
					+ "if(record.data._sel_2 == '1') cellmeta.css='changeplusGreen';"
					+ "	return Gnt.ux.Dict.getDictLable('" + dsname.toUpperCase() + "', value, cellmeta, record, rowIndex,columnIndex, store);"
					+ "}";
				
				eval(renderer);
			}else{
				item.renderer=function(value, cellmeta, record, rowIndex,columnIndex, store){
					if(store.bind_grid && store.bind_grid.callCellmeta){
						var css = store.bind_grid.callCellmeta(record);
						if(css && css.length!=""){
							//cellmeta.css = css; 这里设置背景，下面设置字体
							cellmeta.attr = "style='color:" + css + ";'";
						}
					}
					
					if(record.data._sel == '1')
			    		cellmeta.css='changeplus';
					
					if(record.data._sel_2 == '1')
			    		cellmeta.css='changeplusGreen';
					
					return value;
				};
				if(data.fieldname=='csrq'&&data.displayname=='年龄'){//年龄特殊处理
					item.renderer=function(value, cellmeta, record, rowIndex,columnIndex, store){
						if(store.bind_grid && store.bind_grid.callCellmeta){
							var css = store.bind_grid.callCellmeta(record);
							if(css && css.length!=""){
								//cellmeta.css = css; 这里设置背景，下面设置字体
								cellmeta.attr = "style='color:" + css + ";'";
							}
						}
						
						if(record.data._sel == '1')
				    		cellmeta.css='changeplus';
						
						if(record.data._sel_2 == '1')
				    		cellmeta.css='changeplusGreen';
						
						return Gnt.date.getNl(record.data.gmsfhm,value);
					};
				}
			};
			
			cm.push(item);
		
		}
	}
	return cm;
}
//自定义grid 列
Gnt.zdyValueKey = function(ary) {
	var cm = {};
	if(ary instanceof Array){
		for(var i=0;i<ary.length;i++){
			var data = ary[i].data;
			var dsname = data.dsname;
			if(dsname){
				cm[data.fieldname]=dsname
			}
			
		}
	}
	return cm;
}
Gnt.zdyValueKeyTemp = function(ary) {
	var cm = {};
	if(ary instanceof Array){
		for(var i=0;i<ary.length;i++){
			var data = ary[i];
			var dsname = data.dsname;
			if(dsname){
				cm[data.fieldname]=dsname
			}
			
		}
	}
	return cm;
}
Gnt.initCellWeb = function(id1,id2) {//区域外部id，区域内部控件id
	//alert(1);
	cell = document.getElementById(id2);
	var dom = document.getElementById(id1);
	if (cell) {
		if('GetSheetIndex' in cell){
			return;
		}else{
			html = ' <a href="zptest" target="_blank">下载报表控件</a>';
			dom.innerHTML = html;
			return;
		}
	}/*else{
		html = ' <a href="zptest" target="_blank">下载报表控件</a>';
		dom.innerHTML = html;
		return;
	}*/
};
//add by zjm 20200428 增加冻结土地和集体户验证
Gnt.ux.bengboduyou = function(hhid,hhnbid,gmsfhm,qydjFlag,callback){
	Gnt.ux.Dict.getKzcs("10033,10034", function(config, user, kzdata, s){
		var flag = true;
		var djkz = kzdata['10033'].kzz;
		var jttdkz = kzdata['10034'].kzz;
		//集体土地和户有关系    冻结和具体人有关系
		if((hhid||hhnbid)&&(djkz==1||djkz==2||jttdkz==1)&&qydjFlag){//1冻结只提示，2，冻结不予办理  1集体户提示  
			Gnt.submit(
					{
						hhid:hhid,
						hhnbid:hhnbid,
						gmsfhm:gmsfhm
					}, 
					"yw/common/checkDjJth", 
					"正在查询，请稍后...", 
					function(jsonData,params){
						if(jsonData&&jsonData.list){
							var kzCheck = jsonData.list[0];
							if(kzCheck.djzt==1||kzCheck.djzt==3){//modify by zjm 20200619 增加冻结种类判断
								if(djkz==2){//不予办理
									if(kzCheck.djzt==1){
										alert("该区域已拆迁冻结，请确认");
									}else if(kzCheck.djzt==3){
										alert("已拆迁，请确认");
									}
									flag =false;
									return;
								}else if(djkz==1){
									if(kzCheck.djzt==1){
										alert("该区域已拆迁冻结，请确认");
									}else if(kzCheck.djzt==3){
										alert("已拆迁，请确认");
									}
									if(jttdkz==1){
										if(kzCheck.jttdbz==2){
											alert("当前户为集体土地！");
										}else if(kzCheck.jttdbz==1){
											alert("当前户为公共户！");
										}
									}
								}
							}else{
								if(jttdkz==1){
									if(kzCheck.jttdbz==2){
										alert("当前户为集体土地！");
									}else if(kzCheck.jttdbz==1){
										alert("当前户为公共户！");
									}
									
								}
							}
						}
						if(callback&&flag){
							callback(flag);
						}
					},
					function(jsonData,params){
						alert(jsonData.message);
					}
			);
			return;
		}
		if(callback&&flag){
			callback(flag);
		}
	}, this);
} 

Gnt.ux.zdydywin= Ext.extend(Ext.Window, {
	title:'打印',
	closeAction:'hide',
	modal:true,
	width:600,
	height:400,
	margins:'0',
	layout:'border',
	html:'',
	show:function(){
		Gnt.ux.zdydywin.superclass.show.call(this);
	},
	setSelRes:function(header,shuxing,zdyValueKey,datalist,pxary,rowStart,pageSize){
//		var cell = document.getElementById("Celllist");
		Gnt.initCellWeb('cellCtrl','Celllist');
		cell.Login("浙江金铖华元信息技术有限公司自定义报表-WEB版","","13040351","1340-1275-0110-0004");
		if(rowStart==0){
			cell.ClearArea(1,1, cell.GetCols(0),cell.GetRows(0),0,32);
		}
		//cell.ResetContent();
		var index=cell.GetSheetIndex("第1页");
		var hang = rowStart*pageSize;
		if(hang+2+pageSize>=cell.GetRows(0)){
			cell.SetRows(hang+2+datalist.length,0);
		}else{//恢复默认的50行
			cell.SetRows(51,0);
		}
		if(shuxing.length>=20){
			cell.SetCols(shuxing.length+1, 0);
		}else{//恢复默认的20列
			cell.SetCols(21, 0);
		}
		
		for(var c=1;c<30;c++){
			cell.SetColWidth(1,150,c,index);
		}
		for(var headerIndex=0;headerIndex<header.length;headerIndex++){
			cell.SetCellString(headerIndex+1,1,index,header[headerIndex]);
		}
		//initPrintfunction(0,datalist,index,shuxing,zdyValueKey,header)
//		Ext.Msg.wait("正在导出第"+(hang+1)+"条至"+(hang+1+datalist.length)+"条数据信息，请稍后...");
		for(var i=0;i<datalist.length;i++){
			var obj=datalist[i];
			for(var j=1;j<=shuxing.length&&j<=shuxing.length+1;j++){
				if(!Gnt.util.isEmpty(zdyValueKey[shuxing[(j-1)]])){
					cell.SetCellString(j,hang+2+i,index,Gnt.ux.Dict.getDictLable(zdyValueKey[shuxing[(j-1)]].toUpperCase(), obj[shuxing[(j-1)]]));
				}else{
					cell.SetCellString(j,hang+2+i,index,obj[shuxing[(j-1)]]);
					if(header[(j-1)]=='年龄'){//年龄要做特殊处理
						cell.SetCellString(j,hang+2+i,index,Gnt.date.getNl(obj['gmsfhm'],obj[shuxing[(j-1)]]));
					}
				}
				
			}
		}
		if(pxary){
			Ext.each(pxary, function(pxObj){
				var yswzIndex = shuxing.indexOf(pxObj.data.fieldname);
				cell.SortCol(1,yswzIndex+1,yswzIndex+1,2,yswzIndex+1,datalist.length+1);
			});	
		}
		
	
	},
	resetData:function(){
		this.fs.getForm().reset();
	},
	initComponent : function(){
		
		var fs = new Ext.form.FormPanel({
			 id:'formsb',
	         region : 'center',  
	         labelWidth : 100,  
	         frame : false,  
	         bodyStyle : 'padding:0px', 
	         border : false,  
	         layout:'column',
	         items : [{
	           border:false,
	           frame:true,
	           layout:'table',
	           buttonAlign:'left',
	           buttons:[
	                    new Ext.Button({		     
			        		text:'打印设置',
			        		minWidth:60,
			        		handler:function(){
			        			//var cell = document.getElementById("Celllist");			       
			        			cell.PrintPageSetup();
			        		}
			        	}),
			        	 new Ext.Button({		     
				        		text:'字体设置',
				        		minWidth:60,
				        		handler:function(){
//				        			var cell = document.getElementById("Celllist");
				        			cell.Login("浙江金铖华元信息技术有限公司自定义报表-WEB版","","13040351","1340-1275-0110-0004");
				        			var fontIndex = cell.FindFontIndex("方正宋体-人口信息",1);
				        			var fontSize = cell.GetDefaultFontSize();
				        			cell.SetDefaultFont(fontIndex,fontSize);
				        			//cell.SetChartFont("隶书", 48, 0, 0);
				        		}
				        	}),
		                new Ext.Button({		     
		        		text:'打印预览',
		        		minWidth:60,
		        		handler:function(){
//		        			var cell = document.getElementById("Celllist");
		        			cell.Login("浙江金铖华元信息技术有限公司自定义报表-WEB版","","13040351","1340-1275-0110-0004");
		        			var index=cell.GetSheetIndex("第1页");
		        			cell.PrintPreview(1,index);
		        		}
		        	}),new Ext.Button({		       
		        		text:'保存',
		        		minWidth:60,
		        		handler:function(){
//		        			var cell = document.getElementById("Celllist");			     			   
		        			var isok=cell.SaveFile();
		        			
		        			if(isok==1){
		        				alert("保存成功");
		        			}
		        		}
		        	}),new Ext.Button({		 
		        		text:'打印',
		        		minWidth:60,
		        		handler:function(){
//		        			var cell = document.getElementById("Celllist");	     			   
		        			cell.PrintSheet(1,0);
		        		}
		        	}),new Ext.Button({		 
		        		text:'关闭',
		        		minWidth:60,
		        		handler:function(){
		        			var win = this.findParentByType("zdydy_window");
			            	win.hide();
		        		}
		        	})]
	         },
	         {	      
					frame:false,
					border:false,
					id:'cellCtrl',
					html:"<OBJECT id=\"Celllist\"  WIDTH=605 HEIGHT=320    classid=\"clsid:3F166327-8030-4881-8BD2-EA25350E574A\" />"							      	 		
	         }
				]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.zdydywin.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('zdydy_window', Gnt.ux.zdydywin);
var zdydy_window=new Gnt.ux.zdydywin({
	callback: function(){
	
	}
});
Gnt.updateHzywPj = function(hzywId,pj) {
	var store = new Gnt.store.SjpzStore({
		pzlb : '20000',
		pkname : 'rynbid',
		url : 'yw/hjyw/hzywcl/updateHzywPj.json'
	});
	store.baseParams = {
		hzywid : hzywId,
		pj:pj
	};
	store.load();
}
/**
 * add by zjm 20200812 
 * 依据数据配置表获取uploadform的items
 */
Gnt.getUploadformFormItems = function(config) {
	// Ext.Msg.wait("操作正在执行中，请稍后...", "请稍后");
	var pzlb = config.pzlb;
	var ary = Gnt.ux.Dict.getSjpzData(pzlb);
	var items = new Array();
	var hidden_items = new Array();

	if (ary instanceof Array) {
		for (var i = 0; i < ary.length; i++) {
			var data = Gnt.getUploadExtField(ary[i], config);
			if (data.xtype == 'hidden') {
				hidden_items.push(data);
			} else {
				if (data instanceof Array) {
					// 查询区域范围字段，返回2个数据，否则返回1个
					for (var j = 0; j < data.length; j++) {
						items.push(data[j]);
					}
				} else {
					items.push(data);
				}
			}
		}

		// 重新组合
//		items = Gnt.toFormSjpz(items);
		for (var i = 0; i < hidden_items.length; i++) {
			items.push(hidden_items[i]);
		}
	} else {
		alert("配置" + pzlb + "本地缓存不存在！");
		items.push({
			xtype : 'hidden'
		});
	}

	return items;
}
/**
 * 依据数据配置表记录获取Ext对象
 */
Gnt.getUploadExtField = function(pz, config) {
	var formType = config.formType;

	var f = {};
	f.name = pz.fieldname;
//	f.fieldLabel = pz.displayname;
//	f.columnWidth = (config.cols ? (1 / config.cols) : 0.75);
//	f.labelWidth = 120;
	/*
	 * 虽然设置了长度但是仍然可以保存 if("zqzbh" == pz.fieldname){ if(pz.fieldlength){
	 * f.maxLength = pz.fieldlength; } }
	 */
	if (pz.readonly == "1") {
		f.readOnly = true;
//		f.disabled = true;
//		f.typeAhead=false;
//		f.editable=false;
		f.bodyStyle = 'background-color:#F0F0F0';
	} else {
		// 校验规则
		if (pz.vtype && pz.vtype != "") {
			// alert(pz.vtype);
		}
		if (pz.vtype && pz.vtype != "" && Ext.form.VTypes[pz.vtype]) {
			f.vtype = pz.vtype;
		}
	}

	if (pz.displayname && pz.displayname != ""){
		f.isMust = Gnt.isMust(pz, config);
		f.allowBlank = Gnt.isAllowBlank(pz, config);
	}

	var isHidden = true;
	if (pz.visibletype) {
		// VISIBLETYPE:显示类型，由5位构成，从左向由，
		// 第一位: 设置查询条件是否显示，
		// 第二位: 设置数据输入是否显示
		if (formType == "edit" || formType == "view") {
			// 编辑
			if (pz.visibletype.length >= 2
					&& pz.visibletype.substring(0, 2) == '00') {
				isHidden = false;
			}
		} else {
			// 查询
			if (pz.visibletype.length >= 1
					&& pz.visibletype.substring(0, 1) == '1') {
				isHidden = false;
			}
		}
	}

	
	// 监视器
	f.listeners = {};

	f.listeners.change = config.fieldValueChange ? config.fieldValueChange
			: function() {
			};
	f.listeners.blur = function(field) {
		var f = field.findParentByType("sjpz_uploadform");
		if (field.allowselfinput && field.allowselfinput == "1") {
			if (f.fieldValueChange) {
				v = field.getRawValue();
				f.fieldValueChange(field, v, null);
			}
		}

		if (f.fieldBlur) {
			f.fieldBlur(field);
		}
	}
	f.listeners.focus = function(field) {
		var f = field.findParentByType("sjpz_uploadform");
		if (f) {
			f.lastFocusField = field;
			if (f.fieldFocus)
				f.fieldFocus(field);
		}
	};
	if (pz.fieldtypename == "upload") {
//		f.inputType= 'file';
		f.readonly= true;
	}

	if (pz.fieldtypename == "upload") {
		// 范围条件
		var a = new Array();
		// 拷贝对象
		var f2 = {};
		var f3 = {};
		var f4 = {};
		var f5 = {};
		for (o in f) {
			f4[o] = f[o];
		}
		f.anchor = "100%";
//		f.xtype = "textfield";
		f.columnWidth = 0.8;
		f.labelWidth = 120;
		f.html = pz.displayname;
		a.push(f);
		
		f4.xtype = 'statusbar',
		f4.height = '100%',
		f4.items = []; 
		f4.columnWidth = 0.2;
		f2.xtype = "button";
		//f2.anchor = "100%";
		f2.iconCls='icon-gpy';
		f2.text = "高拍仪";
		//f2.fieldLabel = pz.fieldname+"高拍仪";
		f2.inputType= '';
		f2.buttonAlign = 'center';
		for (o in f2) {
			f3[o] = f2[o];
		}
		f3.text = "上传";
		f3.iconCls='icon-view';
		// 监视器
		f3.listeners = {};

		f3.listeners.click = function(field) {
			var f = field.findParentByType("sjpz_uploadform");
			if (f) {
				f.lastFocusField = field;
				if (f.fieldFocus)
					f.fieldFocus(field);
			}
			var uploadZp_Dialog = new Gnt.ux.UploadZpDialog({
				returnTitleText : '图片上传窗口',
				callback: function(optype, dwxxAdd){
					//提交数据
//					Gnt.submit(
//							dwxxAdd, 
//							"gl/xtmbgl/dwxxwh/addDwDm", 
//							"正在新增单位信息，请稍后...", 
//							function(jsonData,params){
//								showInfo("单位信息新增成功！");
//								errorFlag = true;
//								dwxxGrid.store.reload(); 
//							},
//							function(jsonData,params){
//								alert(jsonData.message);
//								errorFlag = false;
//							}
//					);
				}
			});
			uploadZp_Dialog.show();
		};
		f5.xtype = "checkbox";
		f5.name = pz.fieldname+'checkbox';
		f5.boxLabel = '是否有实物';
		f5.checked = true;
		
		
		f4.items.push(f5);
		f4.items.push(f2);
		f4.items.push(f3);
		a.push(f4);
//		a.push(f5);
//		a.push(f2);
//		a.push(f3);
		return a;
	}
	if (formType == "view" && f.xtype != "hidden") {
		f.hideTrigger = true;
		f.readOnly = true;
		f.disabled = true;
	}

	return f;
}
// 文件上传按钮
Gnt.getUploadButtonExtField = function(pz, config) {
	var formType = config.formType;
	var f = {};
	f.name = pz.fieldname;
	if (pz.readonly == "1") {
		f.readOnly = true;
		f.bodyStyle = 'background-color:#F0F0F0';
	} else {
		// 校验规则
		if (pz.vtype && pz.vtype != "") {
		}
		if (pz.vtype && pz.vtype != "" && Ext.form.VTypes[pz.vtype]) {
			f.vtype = pz.vtype;
		}
	}


	var isHidden = true;
	if (pz.visibletype) {
		// VISIBLETYPE:显示类型，由5位构成，从左向由，
		// 第一位: 设置查询条件是否显示，
		// 第二位: 设置数据输入是否显示
		if (formType == "edit" || formType == "view") {
			// 编辑
			if (pz.visibletype.length >= 2
					&& pz.visibletype.substring(0, 2) == '00') {
				isHidden = false;
			}
		} else {
			// 查询
			if (pz.visibletype.length >= 1
					&& pz.visibletype.substring(0, 1) == '1') {
				isHidden = false;
			}
		}
	}

	// 监视器
	f.listeners = {};

	f.listeners.change = config.fieldValueChange ? config.fieldValueChange
			: function() {
			};
	f.listeners.blur = function(field) {
		var f = field.findParentByType("sjpz_uploadform");
		if (field.allowselfinput && field.allowselfinput == "1") {
			if (f.fieldValueChange) {
				v = field.getRawValue();
				f.fieldValueChange(field, v, null);
			}
		}

		if (f.fieldBlur) {
			f.fieldBlur(field);
		}
	}
	f.listeners.focus = function(field) {
		var f = field.findParentByType("sjpz_uploadform");
		if (f) {
			f.lastFocusField = field;
			if (f.fieldFocus)
				f.fieldFocus(field);
		}
	};
	if (pz.fieldtypename == "upload") {
		f.inputType= 'file';
		f.readonly= true;
	}

	if (pz.fieldtypename == "upload") {
		// 范围条件
		var a = new Array();
		// 拷贝对象
		var f2 = {};
		var f3 = {};
		var f4 = {};
		var f5 = {};
		for (o in f) {
			f4[o] = f[o];
		}
		f.anchor = "100%";
		f.xtype = "textfield";
		f.columnWidth = 1;
		f.labelWidth = 120;
		f.fieldLabel = pz.displayname;
		a.push(f);
		
		f4.xtype = 'toolbar',
		f4.height = '100%',
		f4.items = []; 
//		f4.columnWidth = 0.1;
		
		f2.xtype = "button";
		//f2.anchor = "100%";
		f2.iconCls='icon-gpy';
		f2.text = pz.fieldname+"高拍仪";
		//f2.fieldLabel = pz.fieldname+"高拍仪";
		
		f2.inputType= '';
		f2.buttonAlign = 'center';
		for (o in f2) {
			f3[o] = f2[o];
		}
		f3.text = pz.fieldname+"预览";
		f3.iconCls='icon-view';
		// 监视器
		f3.listeners = {};

		f3.listeners.click = function(field) {
			var f = field.findParentByType("sjpz_uploadform");
			if (f) {
				f.lastFocusField = field;
				if (f.fieldFocus)
					f.fieldFocus(field);
			}
		};
		f5.xtype = "checkbox";
		f5.name = pz.fieldname+'checkbox';
		f5.boxLabel = '是否有实物';
		f5.checked = true;
		
		f4.items.push(f5);
		f4.items.push(f2);
		f4.items.push(f3);
		a.push(f4);
		
		return a;
	}
	if (formType == "view" && f.xtype != "hidden") {
		f.hideTrigger = true;
		f.readOnly = true;
		f.disabled = true;
	}

	return f;
}
function Map() {

    this.keys = new Array();
    this.data = new Object();

    this.set = function(key, value) {
        if (this.data[key] == null) {
            if (this.keys.indexOf(key) == -1) {
                this.keys.push(key);
            }
        }
        this.data[key] = value;
    }

    this.get = function(key) {
        return this.data[key];
    }
};
/**
 * 通用上传文件浏览
 */
//Gnt.ux.UploadPanel = Ext.extend(Ext.form.FormPanel, {
//	labelAlign : 'right',
//	layout : 'form',
//	labelWidth : 80,
//	region:'south',
//	autoScroll: true,
//	initComponent : function(){
//		var that = this;
//		if(that.pzlb){
//			that.fields = Gnt.ux.Dict.getSjpzData(that.pzlb);;
//		}
//		if(that.fields && !that.items){
//			var items = [{
//					layout : 'column',
//					//xtype : 'fieldset',
//					title : '',
//					margins:'0',
//					bodyStyle : 'padding:5px 20px 0px 0px',
//					defaults : {
//						frame:false,
//						border:false,
//						bodyStyle : 'padding:0px 0px 0px 0px'
//					},
//					items:[]
//			}];
//
//			for(var i=0;i<that.fields.length;i++){
//				var item = that.fields[i];
//				item.isMust = Gnt.isMust(item);
//				item.collapsed = false;
//				var items1 = [{
//					layout : 'column',
//					title : '',
//					margins:'0',
//					bodyStyle : 'padding:5px 20px 0px 0px',
//					defaults : {
//						frame:false,
//						border:false,
//						bodyStyle : 'padding:0px 0px 0px 0px'
//					},
//					items:[
//							{html:111}
//					]
//			}];
//				item.items=[{
//					layout : 'column',
//					//xtype : 'fieldset',
//					title : '',
//					margins:'0',
//					bodyStyle : 'padding:5px 20px 0px 0px',
//					defaults : {
//						frame:false,
//						border:false,
////						labelWidth : me.labelWidth?me.labelWidth:100,
//						bodyStyle : 'padding:0px 0px 0px 0px'
//					},
//					items:[
//						{html:111},{html:222},{html:444}
//					]
//			}];
////				alert(1111);
//				if(item.isMust){
//					if(item.isMust==1){
//						item.title = '<span style="color:red">*' + item.displayname + "</span>";
//					}else if(item.isMust==2){
//						item.title = '<span style="color:blue">*' + item.displayname + "</span>";
//					}else if(item.isMust==3){
//						item.title = '<span style="color:black">' + item.displayname + "</span>";
//					}
//					
//				}
//				items[items.length-1].items.push({
//					layout : 'form',
//					columnWidth : 1,
////					collapsed:true,	
//					listeners: {
//		                render: function(c) {
//		                c.body.on('click', function() { 
//		                   //TODO 添加点击事件功能            
////		                	alert(55555);
////		                	c.collapsed=true;
//		                	c.titleCollapse = true;
//		                	c.doLayout();
////		                	c.hide();
//		                		zpItems = c.items.items[0].items.items[0].items.items;
//			                	for(var i = 0; i < zpItems.length; i++){
//			                        var zpItem = zpItems[i];
//			                        if(zpItem.hidden){
//			                        	zpItem.show();
//			                        }else{
//			                        	zpItem.hide();
//			                        }
//			                    }
//		                	
//		                    });
//		                c.body.on('contextmenu',function(e){
//		                    e.preventDefault();//阻止浏览器默认右键菜单
//		                    customMenu.showAt(e.getXY());//展示自定义菜单
//		                    });
//		                },
//		                scope: this
//		            },
//					items : [item]
//				});
//			}
//			
//			this.items = items;
//		}
//		
//		Gnt.ux.UploadPanel.superclass.initComponent.call(this);
//	}
//});
//Ext.reg('sppz_upload_view', Gnt.ux.UploadPanel);
//var spdj_form = new Gnt.ux.UploadPanel({
//	callback: function(zjlx,zqzbh,array){
//	}
//});
//var zdyWin = new Ext.Window({
//	title:'查询报表打印',
//	closeAction:'hide',
//	modal:true,
//	width:300,
//	height:120,
//	layout:'fit',
//	items:spdj_form,
//	listeners:{
//		show:function(){
//		}
//	}
//});
Gnt.ux.UploadZpDialog= Ext.extend(Ext.Window, {
	title:'',
	closeAction:'hide',
	modal:true,
	width:300,
	height:200,
	margins:'5',
	autoScroll : true,	
	layout:'border',
	html:'',
	show:function(){
		Gnt.ux.UploadZpDialog.superclass.show.call(this);
	},
	resetData:function(){
		this.fs.getForm().reset();
	},
	initComponent : function(){
		this.setTitle(this.returnTitleText);
		
		this.zpformpanel = new Ext.form.FormPanel({
			region: 'center',
		    title: '',
		    width: 600,
		    height: 180,
		    x: 5,
		    y: 5,
		    autoScroll : true,	
		    layout: "form",
		    bodyPadding: "5",
		    defaultType: "textfield",
		    allowBlank:false,
		    fileUpload: true,
		    fieldDefaults: {labelAlign: "left", labelWidth: 55},
		    items: [
		        {
		            xtype: 'textfield',
		            id: 'logoFile',
		            name: 'logoFile',
		            inputType: 'file',
		            fieldLabel: '照片地址',
		            width: 80,
		            listeners: {
		                'render': function () {
		                    var logoFileCmp = Ext.get('logoFile');
		                    logoFileCmp.on('change',
		                        function (field, newValue, oldValue) {
		                        var picPath = logoFileCmp.getValue();
		                        if(picPath == ""){
		                            return;
		                        }
		                        var url = 'file:///' + picPath;
		                        var arr = url.split('.');
		                        if (!((arr[arr.length - 1] == 'jpg') || (arr[arr.length - 1] == 'png') || (arr[arr.length - 1] == 'bmp'))) {
		                            alert('文件不合法，请上传后缀为*.jpg;*.png;*.bmp的照片文件!');
		                            return;
		                        }
		                        if (Ext.isIE) {
		                            var image = Ext.get('logoPic').dom;
		                            image.src = Ext.BLANK_IMAGE_URL;
		                            image.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = url;
		                        } else {
		                            //支持FF
		                            var oFReader = new FileReader();
		                            oFReader.readAsDataURL(document.getElementById("logoFile").files[0]);
		                            oFReader.onload = function (oFREvent) {
		                                document.getElementById("logoPic").src = oFREvent.target.result;
		                            }
		                        }
		                    }, this);
		                }
		            }
		        },
		        {
		            xtype: 'box',
		            id: 'logoPic',
		            width: 100,
		            height: 100,
		            autoEl: {
		                tag: 'img',
		                src: '',
		                style: 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);'
		            }
		        }
		    ],
		    buttons: [
		        {
		            text: "确定",
		            handler: function () {
		            	var win = this.findParentByType("uploadZp_Dialog");
		                var photoName = document.getElementById("logoFile").value;
		                	win.zpformpanel.getForm().submit({
		                        url: 'yw/common/tyuploadZp',
		                        method:'post',
		                        params: lhdz,
		                        success: function (form, action) {
		                            showInfo("照片上传成功！");
		                            if(win.callback){
		                            	win.callback('lh', lhdz);
									}
		                            win.hide();
		                        },
		                        failure: function (form, action) {
		                            showInfo(action.response.responseText);
		                        }
		                    });

		            }
		        }, {
		            text: '关闭',
		            handler: function () {
		            	var win = this.findParentByType("uploadZp_Dialog");
		                win.hide();
		            }
		        }
		    ]

		});
		this.items = [this.zpformpanel];
		Gnt.ux.UploadZpDialog.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('uploadZp_Dialog', Gnt.ux.UploadZpDialog);
function initPrintfunction(i,datalist,index,shuxing,zdyValueKey,header){
	if(i < datalist.length){
		var obj=datalist[i];
		for(var j=1;j<=shuxing.length&&j<=shuxing.length+1;j++){
			if(!Gnt.util.isEmpty(zdyValueKey[shuxing[(j-1)]])){
				cell.SetCellString(j,i+2,index,Gnt.ux.Dict.getDictLable(zdyValueKey[shuxing[(j-1)]].toUpperCase(), obj[shuxing[(j-1)]]));
			}else{
				cell.SetCellString(j,i+2,index,obj[shuxing[(j-1)]]);
				if(header[(j-1)]=='年龄'){//年龄要做特殊处理
					cell.SetCellString(j,i+2,index,Gnt.date.getNl(obj['gmsfhm'],obj[shuxing[(j-1)]]));
				}
			}
		}
//		i++;
//		initPrintfunction(i,datalist,index,shuxing,zdyValueKey,header);
	}
}
function initPrintIndexfunction(i,datalist,index,shuxing,zdyValueKey,header){
	if(i < datalist.length){
		var obj=datalist[i];
		for(var j=1;j<=shuxing.length&&j<=shuxing.length+1;j++){
			if(!Gnt.util.isEmpty(zdyValueKey[shuxing[(j-1)]])){
				cell.SetCellString(j,i+2,index,Gnt.ux.Dict.getDictLable(zdyValueKey[shuxing[(j-1)]].toUpperCase(), obj[shuxing[(j-1)]]));
			}else{
				cell.SetCellString(j,i+2,index,obj[shuxing[(j-1)]]);
				if(header[(j-1)]=='年龄'){//年龄要做特殊处理
					cell.SetCellString(j,i+2,index,Gnt.date.getNl(obj['gmsfhm'],obj[shuxing[(j-1)]]));
				}
			}
			
		}
	}
}