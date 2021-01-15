var selRes = null;
var curIndex = -1;
var selectRynbid = null;
var buttonEnableFlag = true;
var ywlb = null;
Ext.onReady(function(){
	
	Ext.QuickTips.init();
	
	var hzywid = getQueryParam("hzywid");
	ywlb = getQueryParam("ywlb");
	var gmsfhm = getQueryParam("gmsfhm");
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("20000",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	var rkxxGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		//region:'north',
		pzlb: '20000',
		title: '',
		url: 'cx/hjjbxx/ckxx/getXxxx.json',
		showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				selectHhnbid = selRes.data.hhnbid;
				selectRynbid = selRes.data.rynbid;
			}
		}
	});
	
	var form20000 = new Gnt.ux.SjpzForm({
		closable: false,
		region:'center',
		/*height:500,*/
		pzlb: '20000',
		labelWidth : 120,
		cols:2,
//		bindStore:xblryGrid.store,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:rkxxGrid,
		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
		changeDictCust:function(cmb,res){
			
			return;
		},
		title: ''
	});
	
	var zpWindow = new Gnt.ux.SelectZpAll({
		ryid:getQueryParam("ryid")
	});
	
	var jsryz_dialog = new Gnt.ux.JsryzlDialog({
		callback: function(type,data){
			//解锁后续操作
			var subdata = {
        			rynbid:getQueryParam("rynbid"),
        			rysdzt:"0",
        			jssdyy:data.jsryzlyy
        		};
        		
        		Gnt.submit(
        			subdata, 
        			"cx/hjjbxx/ckxx/processCzrkzt", 
        			"正在解锁人员资料，请稍后...", 
        			function(jsonData,params){
        				showInfo("人员(ID=" + getQueryParam("ryid") + ",身份证=" + getQueryParam("gmsfhm") + ") 成功解锁!");
        			}
        		);
		}
	});	
	var sdryz_dialog = new Gnt.ux.SdryzlDialog({
		callback: function(type,data){
			//锁定后续操作
			var subdata = {
        			rynbid:getQueryParam("rynbid"),
        			rysdzt:"6",
        			jssdyy:data.sdryzlyy
        		};
        		
        		Gnt.submit(
        			subdata, 
        			"cx/hjjbxx/ckxx/processCzrkzt", 
        			"正在锁定人员资料，请稍后...", 
        			function(jsonData,params){
        				showInfo("人员(ID=" + getQueryParam("ryid") + ",身份证=" + getQueryParam("gmsfhm") + ") 成功锁定!");
        			}
        		);
		}
	});	
	
	var p3 = new Ext.Panel({
		layout:'border',
		items:[form20000,{
	    	region:'east',
	    	 width:180,
	    	 layout:'table',
	    	 layoutConfig: {
	    		columns: 1
	    	 },
	    	 items: [{
	    		 		id:'ryztId',
		 		 		title: '',
		 		 		bodyStyle:'padding:10px 25px 0px 20px',
		 		 		html: '人员状态<br />正常',
				 		height:'100%',
						rowspan: 1,
						colspan:1
			    	},{
						id:'p3Img',
	    		 		title: '',
	    		 		height:'100%',
	    		 		bodyStyle:'padding:10px 10px 10px 10px',
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
		   	    	 	items:[
			   	    	 	new Ext.Button({
								text:'所有照片',
								minWidth:100,
								handler:function(){
									zpWindow.show();
								}
							}),{
				    	    	height:3,
				    	    	border:false,
				    	    	frame:false
				    	    },new Ext.Button({
								text:'注销证明',
								id:'zxzm',
								minWidth:100,
								handler:function(){
									var array=[];
									var o={};
            						//selectRynbid=getQueryParam("rynbid");
									o.rynbid=selectRynbid;
									if(!Gnt.util.isEmpty(selRes.data.swzxlb)){
										o.directTable="swzxzm";
									}else if(!Gnt.util.isEmpty(selRes.data.qczxlb)){
										if(!Gnt.util.isEmpty(selRes.data.qwdssxq)&&(selRes.data.qwdssxq == "710000" || selRes.data.qwdssxq == "810000" || selRes.data.qwdssxq == "820000")){
											o.directTable="gatdjzxzm";
										}else if(!Gnt.util.isEmpty(selRes.data.qwdssxq)&&((selRes.data.qwdssxq).indexOf("0")==0)){
											o.directTable="gwdjzxzm";
										}else{
											o.directTable="hkzxzm";
											o.fromPage="rkxx";
										}
									}else{
										o.directTable="hkzxzm";
									}
									array.push(o);
									printfunction(0,array);
								}
				    	    }),{
				    	    	height:3,
				    	    	border:false,
				    	    	frame:false
				    	    },new Ext.Button({
								text:'人口信息',
								minWidth:100,
								id:'rkxx',
								handler:function(){
									var array=[];
									var o={};
            						//selectRynbid=getQueryParam("rynbid");
									o.rynbid=selectRynbid;
									o.xm = getQueryParam("xm");
									o.directTable="rkxx";
									array.push(o);
									printfunction(0,array);
								}
				    	    }),{
				    	    	height:3,
				    	    	border:false,
				    	    	frame:false
				    	    },new Ext.Button({
								text:'常表户口',
								id:'cbhk',
								minWidth:100,
								handler:function(){
									var pcs = selRes.data.pcs;
//									if((xzqhfw.indexOf(pcs.substring(0, 6)) == -1&&pcsfw.indexOf(pcs) == -1)&&!eval(user.isadmin.toLowerCase())){
//										alert("非本派出所成员，没有打印的权利！");
//										return;
//									}
									hkxzzmFlag =false;
            						hkzmlsFlag = false;
//            						selectHhnbid=getQueryParam("hhnbid");
//            						selectRynbid=getQueryParam("rynbid");
            						hkxzzmFlag =false;
            						var hzywjo ={
            							ywlb:ywlb,
            							hzywid:hzywid,
            							gmsfhm:gmsfhm
            						}
            						if(Ext.getCmp("c11").getValue()){
                						printRk(1,selectHhnbid,selectRynbid,[true,true,true,true,true],[true,true,false,false,false],[true,true,true,true,true],hzywjo);
            						}else{
            							printRk(2,selectHhnbid,selectRynbid,[true,true,true,true,true],[true,true,false,false,false],[true,true,true,true,true],hzywjo);
            						}
            						if(hzywid&&ywlb=='10'){
            							Gnt.ux.Dict.getKzcs('10036', function(pz, user) {
            								if(pz.kzz==1){
            									pjq('ES',user.ip,hzywid);
            								}
            							});
            						}
	            						
								}
				    	    }),{
				    	    	height:3,
				    	    	border:false,
				    	    	frame:false
				    	    },new Ext.Button({
								text:'户籍证明',
								minWidth:100,
								id:'hjzm',
								handler:function(){
//									selectHhnbid=getQueryParam("hhnbid");
//            						selectRynbid=getQueryParam("rynbid");
            						hkxzzmFlag =false;
            						hkzmlsFlag = false;
            						var hzywjo ={
                							ywlb:ywlb,
                							hzywid:hzywid,
                							gmsfhm:gmsfhm	
                					}
            						if(Ext.getCmp("c11").getValue()){
                						printRk(1,selectHhnbid,selectRynbid,[false,false,true,false,false],[false,false,true,false,false],[false,false,true,false,false],hzywjo);
            						}else{
            							printRk(2,selectHhnbid,selectRynbid,[false,false,true,false,false],[false,false,true,false,false],[false,false,true,false,false],hzywjo);
            						}
            						if(hzywid&&ywlb=='13'){
            							Gnt.ux.Dict.getKzcs('10036', function(pz, user) {
            								if(pz.kzz==1){
            									pjq('ES',user.ip,hzywid);
            								}
            							});
            						}
								}
				    	    }),{
				    	    	height:3,
				    	    	border:false,
				    	    	frame:false
				    	    },new Ext.Button({
								text:'户口性质证明',
								minWidth:100,
								id:'hkxzzmBtn',
								handler:function(){
//									selectHhnbid=getQueryParam("hhnbid");
//            						selectRynbid=getQueryParam("rynbid");
            						hkxzzmFlag =true;
            						var hzywjo ={
                							ywlb:ywlb,
                							hzywid:hzywid,
                							gmsfhm:gmsfhm	
                					}
            						if(Ext.getCmp("c11").getValue()){
            							printRk(1,selectHhnbid,selectRynbid,[false,false,true,false,false],[false,false,true,false,false],[false,false,true,false,false],hzywjo);
            						}else{
            							printRk(2,selectHhnbid,selectRynbid,[false,false,true,false,false],[false,false,true,false,false],[false,false,true,false,false],hzywjo);
            						}
            						if(hzywid&&ywlb=='25'){
            							Gnt.ux.Dict.getKzcs('10036', function(pz, user) {
            								if(pz.kzz==1){
            									pjq('ES',user.ip,hzywid);
            								}
            							});
            						}
								}
							}),{
				    	    	height:3,
				    	    	border:false,
				    	    	frame:false
				    	    },new Ext.Button({
								text:'户籍证明（律师用）',
								id:'hjzmx',
//								disabled:true,// 户籍证明律师用按钮隐藏，新开个单独链接处理  add by zjm 20200817
								minWidth:100,
								handler:function(){
//									selectHhnbid=getQueryParam("hhnbid");
//            						selectRynbid=getQueryParam("rynbid");
            						hkxzzmFlag =false;
            						hkzmlsFlag = true;
            						var hzywjo ={
                							ywlb:ywlb,
                							hzywid:hzywid,
                							gmsfhm:gmsfhm	
                					}
            						if(Ext.getCmp("c11").getValue()){
                						printRk(1,selectHhnbid,selectRynbid,[false,false,true,false,false],[false,false,true,false,false],[false,false,true,false,false],hzywjo);
            						}else{
            							printRk(2,selectHhnbid,selectRynbid,[false,false,true,false,false],[false,false,true,false,false],[false,false,true,false,false],hzywjo);
            						}
            						if(hzywid&&ywlb=='13'){
            							Gnt.ux.Dict.getKzcs('10036', function(pz, user) {
            								if(pz.kzz==1){
            									pjq('ES',user.ip,hzywid);
            								}
            							});
            						}
								}
				    	    }),{
				    	    	height:3,
				    	    	border:false,
				    	    	frame:false
				    	    },{
				    	    	id:'c11',
				            	xtype:'checkbox',
				            	boxLabel: '全户打印',
				            	checked:false,
				            	name:'qhdy'
//								xtype:'checkboxgroup',
//								name:'qhdy',
//								boxLabel: '全户打印',
//								minWidth:100,
//								id: 'boxId',
//								items: [
//									{
//										boxLabel: '全户打印',
//										name: 'dy',
//										id : 'dyId',
//										inputValue: '1'
//									}
//								]
				    	    }
			    	 ]
	    	    }]
	    },{

			region:'south',
			width: '100%', 
	        border:false,
	        frame:false,
	        buttons:[
	        	new Ext.Button({
		        	text:'标准地址',
		        	minWidth:100,
		        	//disabled:hzywid != "null"?true:false,
		        	handler:function(){
		        		if(getQueryParam("bzdzFlag")){
		        			Gnt.submit(
		        					{}, 
		        					"cx/hffcxxcx/getMdFive.json",
		        					"查询md5信息，请稍后...", 
		        					function(jsonData,params){
		        						if(jsonData&&jsonData.message){
		        							Gnt.ux.Dict.getKzcs('10016', function(config, user, kzdata){
		        								var url = config.bz;
		        								url += "?id=" +  encodeURI(jsonData.message);
		        								url += "&dlmj=" + encodeURI(user.yhdlm);
		        								url += "&yhsfzh=" + encodeURI(user.gmsfhm);
		        								window.open(url);
		        							});
		        						}
		        					}
		        			);
		        		}else{
		        			alert("门楼牌没有标准地址信息!");
		        		}
		        	}
		        }),new Ext.Button({
        			text:'解锁人员资料',
        			minWidth:80,
        			disabled:ryxxjsFlag>0?false:true,
        			handler:function(){
        				jsryz_dialog.show();
        			}
        		}),
		        new Ext.Button({
		        	text:'解锁人员资料',
		        	minWidth:100,
		        	hidden:true,
		        	disabled:ryxxjsFlag>0?false:true,
		        	handler:function(){
		        		var subdata = {
			        			rynbid:getQueryParam("rynbid"),
			        			rysdzt:"0"
			        		};
			        		
			        		Gnt.submit(
			        			subdata, 
			        			"cx/hjjbxx/ckxx/processCzrkzt", 
			        			"正在解锁人员资料，请稍后...", 
			        			function(jsonData,params){
			        				showInfo("人员(ID=" + rkxxGrid.store.getAt(0).data.ryid + ",身份证=" + rkxxGrid.store.getAt(0).data.gmsfhm + ") 成功解锁!");
			        			}
			        		);
			        		
			        	}
		        }),new Ext.Button({
        			text:'锁定人员资料',
        			minWidth:80,
        			disabled:ryxxjsFlag>0?false:true,
        			handler:function(){
        				sdryz_dialog.show();
        			}
        		}),
		        new Ext.Button({
		        	text:'锁定人员资料',
		        	minWidth:100,
		        	hidden:true,
		        	disabled:ryxxsdFlag>0?false:true,
		        	handler:function(){
		        		var subdata = {
		        			rynbid:getQueryParam("rynbid"),
		        			rysdzt:"1"
		        		};
		        		
		        		Gnt.submit(
		        			subdata, 
		        			"cx/hjjbxx/ckxx/processCzrkzt", 
		        			"正在锁定人员资料，请稍后...", 
		        			function(jsonData,params){
		        				showInfo("人员(ID=" + rkxxGrid.store.getAt(0).data.ryid + ",身份证=" + rkxxGrid.store.getAt(0).data.gmsfhm + ") 成功锁定!");
		        			}
		        		);
		        		
		        	}
		        }),
		        new Ext.Button({
		        	text:'关闭',
		        	minWidth:100,
		        	handler:function(){
		        		if(hzywid && hzywid != "null"){
		        			window.parent.close();
		        		}else{
		        			window.parent.parent.closeWorkSpace();
		        		}
		        	}
		        }),
		        new Ext.Button({
		        	text:'返回',
		        	minWidth:100,
		        	//disabled:(hzywid && hzywid != "null")?true:false,
		        	handler:function(){
		        		parent.Ext.getCmp('card').getLayout().setActiveItem(1);
		        	}
		        })
	        ]
	    
	    }]
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
    	    //bodyStyle: 'padding:2px',
        	items:[p3]
        }
    });
	Ext.Msg.wait("正在执行查询，请稍后...");
	var store = rkxxGrid.store;
	if(getQueryParam("ryid")!=null&&getQueryParam("ryid")!=""){
		store.baseParams = {
			rynbid:getQueryParam("rynbid"),
			ryid:getQueryParam("ryid"),
			config_key:'queryPoHJXX_CZRKJBXXB4',
			ryzl2:getQueryParam("ryzl2"),
			start:0,
			limit:200
		};
		
		if(getQueryParam("mlpnbid")){
			Ext.apply(store.baseParams, {"mlpnbid":getQueryParam("mlpnbid")});
		}
		store.on("load",function(store) {  
			if(store.data.length > 0){
				curIndex = 0;
				rkxxGrid.fireEvent("rowclick",rkxxGrid,curIndex);
			}
		},rkxxGrid); 
	}else{
		store.baseParams = {
			rynbid:getQueryParam("rynbid"),
			config_key:'queryPoHJXX_CZRKJBXXB4',
			ryzl2:getQueryParam("ryzl2"),
			start:0,
			limit:200
		};
		
		if(getQueryParam("mlpnbid")){
			Ext.apply(store.baseParams, {"mlpnbid":getQueryParam("mlpnbid")});
		}
		store.on("load",function(store) {  
			if(store.data.length > 0){
				curIndex = 0;
				rkxxGrid.fireEvent("rowclick",rkxxGrid,curIndex);
			}
		},rkxxGrid); 
	}
//	store.baseParams = {
//			rynbid:getQueryParam("rynbid"),
////			rynbid:'3407000001001474234',
//			config_key:'queryPoHJXX_CZRKJBXXB4',
//			start:0,
//			limit:200
//		};
	store.load();
	store.on("load",function(store) {
		if(store.data.length > 0){
			curIndex = 0;
			/*
			rkxxGrid.fireEvent("rowclick",rkxxGrid,curIndex);
			rkxxGrid.getSelectionModel().selectRange(curIndex,curIndex);
			*/
			Gnt.toFormReadyonly(form20000);
			rkxxGrid.fireEvent("rowclick",rkxxGrid,0);
			/**
				0 : 正常
				1 : 死亡
				2 : 迁出
				3 : 服兵役
				4 : 出国境定居
				6 : 失踪
				7 : 删除
				8 : 注销
				9 : 其它
			 */
			selRes = store.getAt(0);
			var ryzt = selRes.data.ryzt;
			if(0 == ryzt){
				Ext.getCmp('ryztId').body.update("人员状态<br />正常");
			}else if(1 == ryzt){
				Ext.getCmp('ryztId').body.update("人员状态<br />死亡");
			}else if(2 == ryzt){
				Ext.getCmp('ryztId').body.update("人员状态<br />迁出");
			}else if(3 == ryzt){
				Ext.getCmp('ryztId').body.update("人员状态<br />服兵役");
			}else if(4 == ryzt){
				Ext.getCmp('ryztId').body.update("人员状态<br />出国境定居");
			}else if(6 == ryzt){
				Ext.getCmp('ryztId').body.update("人员状态<br />失踪");
			}else if(7 == ryzt){
				Ext.getCmp('ryztId').body.update("人员状态<br />删除");
			}else if(8 == ryzt){
				Ext.getCmp('ryztId').body.update("人员状态<br />注销");
			}else if(9 == ryzt){
				Ext.getCmp('ryztId').body.update("人员状态<br />其它");
			}
//			alert(store.getAt(0).get("ryzt"));
//			if(store.getCount() > 0){
//					if(0 == store.getAt(0).get("ryzt")){
//						Ext.getCmp('ryztId').body.update("人员状态<br />正常");
//					}else if(1 == store.getAt(0).get("ryzt")){
//						Ext.getCmp('ryztId').body.update("人员状态<br />死亡");
//					}else if(2 == store.getAt(0).get("ryzt")){
//						Ext.getCmp('ryztId').body.update("人员状态<br />迁出");
//					}else if(3 == store.getAt(0).get("ryzt")){
//						Ext.getCmp('ryztId').body.update("人员状态<br />服兵役");
//					}else if(4 == store.getAt(0).get("ryzt")){
//						Ext.getCmp('ryztId').body.update("人员状态<br />出国境定居");
//					}else if(6 == store.getAt(0).get("ryzt")){
//						Ext.getCmp('ryztId').body.update("人员状态<br />失踪");
//					}else if(7 == store.getAt(0).get("ryzt")){
//						Ext.getCmp('ryztId').body.update("人员状态<br />删除");
//					}else if(8 == store.getAt(0).get("ryzt")){
//						Ext.getCmp('ryztId').body.update("人员状态<br />注销");
//					}else if(9 == store.getAt(0).get("ryzt")){
//						Ext.getCmp('ryztId').body.update("人员状态<br />其它");
//					}
//			}
			
			/**
				往表单设值
			 */
//			var pkvalue = store.getAt(0).data[form20000.bindStore.pkname];
//			var re = form20000.bindStore.getById(pkvalue);
			if(selRes){
				form20000.getForm().loadRecord(selRes);
				/**
					调用照片方法报错
				 */
				var zpid = selRes.data.zpid;
				if(zpid &&　zpid != 0){
					Ext.getCmp('p3Img').body.update("<IMG SRC='yw/common/img/render/" + zpid + "' width='100%' height='100%' />");
				}else{
					Ext.getCmp('p3Img').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
				}
				var pcs = selRes.data.pcs;
				buttonEnableFlag = (xzqhfw.indexOf(pcs.substring(0, 6)) == -1&&pcsfw.indexOf(pcs) == -1)&&!eval(user.isadmin.toLowerCase());
				if((selRes.data.ryzt==1||selRes.data.ryzt==2||selRes.data.ryzt==4||selRes.data.ryzt==8)&&!buttonEnableFlag){//add by zjm 20190703 ryzt>0时候，注销按钮显示，否则延迟
					Ext.getCmp('zxzm').enable();
				}else{
					Ext.getCmp('zxzm').disable();
				}
				if(buttonEnableFlag){//add by zjm 20190709 非数据范围内按钮置灰
					Ext.getCmp('rkxx').disable();
					Ext.getCmp('cbhk').disable();
					Ext.getCmp('hjzm').disable();
					Ext.getCmp('hjzmx').disable();
				}else{
					Ext.getCmp('rkxx').enable();
					Ext.getCmp('cbhk').enable();
					Ext.getCmp('hjzm').enable();
					Ext.getCmp('hjzmx').enable();
				}
			}
			
			
			
		}
	},rkxxGrid); 
	Ext.Msg.hide();
	Gnt.ux.Dict.getKzcs('10035', function(pz, user) {
		if(pz.kzz==1){
			var tempGmsfhm = (user&&user.gmsfhm)?user.gmsfhm.substring(0,6)+'********'+user.gmsfhm.substring(14):"无";
			watermark.load({ watermark_txt: '姓名：'+user.xm+'          ip:'+user.ip+'        身份证号：'+ tempGmsfhm});
			PointerEventsPolyfill.initialize({});
		}
		
	});
});