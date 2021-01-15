Ext.onReady(function(){
	Ext.QuickTips.init();
	//gotohy.jsp？flag=1&tokey=&dlr=
	var flag = getQueryParam("flag");
	var tokey = null;
	var dlr = null;
	var type = null;//1代表准迁证核验2代表迁移证核验3出生证核验4重人重号核验5从公安部获取照片
	var tokey = getQueryParam("tokey");
	var url2=null;
	var title =" ";
	var params={};
	if(!tokey&&flag!=6){
		alert("参数不完整,缺少tokey,请检查！");
	}
	if(flag){
		if(flag == 1){
			type = '10011';
			title='准迁证核验';
			params={
					gmsfzh: getQueryParam("gmsfzh"),
					zqzbh: decodeURI(getQueryParam("bh")),
					sjgsdwdm: getQueryParam("sjgsdwdm")//data.qwdssxq //返回特殊格式
				}
    	}else if(flag == 2){
    		type = '10012';
    		title='迁移证核验';
    		params={
    			gmsfzh: getQueryParam("gmsfzh"),
    			qyzbh: decodeURI(getQueryParam("bh")),
    			sjgsdwdm: getQueryParam("sjgsdwdm")//data.qcdssxq
    		};
    	}else if(flag == 3){
    		type = '10014';
    		title='出生证核验';
    	}else if(flag == 4){
    		type = '10022';
    		title='重人重号核验';
    	}else if(flag == 5){
    		type = '10013';
    		title='';
    	}else if(flag == 6){
    		type = '';
    		title='派出所公章';
    	}else{
    		alert("没有合适的转发url");
    		return;
    	}
		transUrl(type,title,flag);
	};
	function transUrl(type,title,flag){
		if(type){
			Gnt.ux.Dict.getKzcs(type, function(config, user, kzdata){
				var url = config.bz;
				//注意，需要进行2次编码，解决GET乱码问题，后台获取参数，使用 java.net.URLDecoder.decode(qyzbh, "UTF-8");
				if(flag==1||flag==2){
					if(url.indexOf("?")<0)
						url += "?";
					for(o in params){
						if(params[o] && params[o]!=""){
							var val = params[o];
							if(val.indexOf("user.")==0){
								val = user[val.substring(5)];
								url += '&' + o + "=" + encodeURI(encodeURI(val))+"&version=2";
							}else{
								url += '&' + o + "=" + encodeURI(encodeURI(params[o]))+"&version=2";
							}
						}
					}
				}else if(flag==3){
					if(url.indexOf("?")<0)
						url += "?";
					url += "&cszmbh=" + encodeURI(decodeURI(getQueryParam("bh"))) 
					+ "&yh=" + user.yhdlm 
					+ "&yhxm=" + encodeURI(user.xm) 
					+ "&yhdw=" + user.dwdm 
					+ "&yhdwmc=" + encodeURI(user.dwmc);
				}else if(flag==4){
					var url = config.bz;
					if(url.indexOf("?")<0)
						url += "?";
					url2 = url + "&sfzh=" + getQueryParam("gmsfzh")	+ "&dlyhxm=" + user.glyxm+"&dlmjdlm="+user.yhdlm+"&dlmjsfzh="+user.sfzh+"&dlmjuid="+user.yhid+"&dlmdw="+user.dwdm;
				    url += "&sfzh=" + getQueryParam("gmsfzh") + "&dlyhxm=" + user.glyxm + "&optype=count";
				}else if(flag==5){
					if(url.indexOf("?")<0){
						url += "?";
					}else{
						url += "&";
					}
					url= url +"version=2&sfzh=" + getQueryParam("gmsfzh");
				}

				//yw/common/img/dwrender/
				openYz(flag,url);
			});
		}else{
			if(flag==6){
				url= basePath+'yw/common/img/dwrender/'+getQueryParam("dwdm");
			}
			openYz(flag,url);
		}
		
	};
	function openYz(flag,url){
		if(flag==1||flag==2||flag==3||flag==6){
			var w = new Gnt.ux.URLDialog({
				title: title,
				width:900,
				height:500,
				url: url
			});
			w.show();
		}else if(flag==4){
			Gnt.submit(
					{
						url: url
					}, 
					"yw/common/executeRmoterJSON",
					"操作正在执行中，请稍后...", 
					function(jsonData,params){
						if(jsonData.list && jsonData.list.length>0){
							var str = jsonData.list[0];
							var data = Ext.decode(str);
							if(data.success){
								if(parseInt(data.count)>0){
									//身份证重复，提交重复信息
									var w = new Gnt.ux.URLDialog({
										title:'重号核验',
										width:900,
										height:500,
										url: url2
									});
									w.show();
									showInfo("身份证号码重复，请录入重复信息！");
								}else{
									showInfo("身份证无重复！");
								}
							}else{
								showErr(data.message);
							}
						}
					},
					function(jsonData,params){
						if(jsonData && jsonData.message){
							showErr(jsonData.message);
						}else{
							showErr("核验重号失败！");
						}
					}
			);
		}else if(flag==5){
			window.location.href=url;
//			var w = new Gnt.ux.URLDialog({
//				title: title,
//				width:900,
//				height:500,
//				url: url
//			});
//			w.show();
		}
		//保存日志 以G开头 
		log_code = "G000"+flag;
		Gnt.submit(
				{gmsfzh:getQueryParam("gmsfzh"),
					bh:getQueryParam("bh")}, 
					"gl/ggUrlTrans/saveGgUrlTrans", 
					"正在保存操作日志，请稍后...", 
					function(jsonData,params){
				}
			);
		
	}
	
});