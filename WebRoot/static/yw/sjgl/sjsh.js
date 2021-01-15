var selRes = null;
var ryidTemp = "";
var zpipTemp = "";
var selectRyid = null;
var selectRynbid = null;
var indexTemp=0;
Ext.onReady(function(){
	Ext.QuickTips.init();
	if(!Gnt.loadSjpzb("10019,30017,10001,10022,10018,10017,10002,20000",function(){}))
		return;
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX','CS_BGGZLB'],function(){});
	/**
	 点击下一步后显示的内容
	 暂时用户成员信息代替
	 */
	var form30002 = new Gnt.ux.SjpzForm({
		columnWidth:0.7,
		pzlb: '30002',
		url: 'yw/queryRyxx',
		title:'',
		cols:2,
//		height:300,
		formType:'query'
	});
	var zpSkim_window = null;
	shBhgDialog = new Gnt.ux.SelectShBhgDialog({
		callback: function(optype, bhgData){
			//提交数据
			var shdata=new Array();
			var data = {};
			data.nbslid = selRes.data.nbslid;
			data.shqk = '2';
			data.zzxxcwlb = bhgData.zzxxcwlb;
			data.cwms = bhgData.cwms;
			shdata.push(data);
			Gnt.submit(
				{
					shdata:Ext.encode(shdata)
				},
				"yw/fjgl/sjsh/processZjshyw",
				"正在进行分局不合格审核，请稍后...",
				function(jsonData,params){
					showInfo("分局不合格审核成功！");
					sjshGrid.store.reload();
				}
			);
		}
	});
	shGgDialog = new Gnt.ux.SelectShGgDialog({
		callback: function(optype, bhgData){
			//提交数据
			var shdata=new Array();
			var ksh = bhgData.ksh;
			var jsh = bhgData.jsh;
			for(var i=ksh-1;i<sjshGrid.store.totalLength&&i<jsh;i++){
				var data = {};
				var griddata = sjshGrid.store.getAt(i).data;
				data.nbslid = griddata.nbslid;
				if(bhgData.qbshjg==1){//合格
					data.shqk = '1';
				}else if(bhgData.qbshjg==2){//不合格
					data.shqk = '2';
				}
				if(bhgData.cxsh==1&&(griddata.slzt==11||griddata.slzt==12)){//不重新审核
					break;
				}else{//重新审核
					shdata.push(data);
				}
			}
			Gnt.submit(
				{
					shdata:Ext.encode(shdata)
				},
				"yw/fjgl/sjsh/processZjshyw",
				"正在进行分局不合格审核，请稍后...",
				function(jsonData,params){
					showInfo("分局不合格审核成功！");
					sjshGrid.store.reload();
				}
			);
		}
	});
	var sjshGrid = new Gnt.ux.SjpzGrid({
		pkname: 'nbslid',
		pzlb: '30017',
		url:'yw/sjgl/sjsh/querySjshxx',
		region:'center',
		bodyStyle:'overflow-y:auto;overflow-x:scroll;height:300px',
		anchor:'100% 100%',
		margins: '0 0 0 0',
		cmargins: '0 0 0 0',
		title: '',
		pageSize:user.dwDySet.ywlimit,
		showPaging:true,
		listeners:{
			rowclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				indexTemp = rowIndex;
				selectRyid = selRes.data.ryid;
				selectRynbid = selRes.data.rynbid;
				zpid = selRes.data.zpid;
				Ext.getCmp("slxxxm").setValue(selRes.data.xm);
				Ext.getCmp("slxxxb").setValue(Gnt.ux.Dict.getDictLable('CS_XB', selRes.data.xb));
				Ext.getCmp("slxxcs").setValue(selRes.data.csrq);
				Ext.getCmp("slxxmz").setValue(Gnt.ux.Dict.getDictLable('CS_MZ', selRes.data.mz));
				Ext.getCmp("slxxhm").setValue(selRes.data.gmsfhm);
				Ext.getCmp("slxxslyy").setValue(Gnt.ux.Dict.getDictLable('CS_EDZSLYY', selRes.data.slyy));
				Ext.getCmp("slxxlz").setValue(Gnt.ux.Dict.getDictLable('CS_EDZLZFS', selRes.data.lqfs));
				Ext.getCmp("slxxyxqxq").setValue(selRes.data.yxqxqsrq);
				Ext.getCmp("slxxyxqxz").setValue(selRes.data.yxqxjzrq);
				Ext.getCmp("slxxxz").setValue(selRes.data.zz);
				if(Ext.getCmp("r12").getValue()){
					if(zpid &&　zpid != 0){
						Ext.getCmp('picImage').body.update("<IMG SRC='yw/fjgl/fjsh/img/render/" + zpid + "' width='100%' height='100%' />");
					}else{
						Ext.getCmp('picImage').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
					}
				}else{
					Ext.getCmp('picImage').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
				}
				if(Ext.getCmp("r13").getValue()){
					Gnt.submit(
						{ryid:selRes.data.ryid},
						"yw/common/queryHcy",
						"正在查询常口信息，请稍后...",
						function(jsonData,params){
							if(jsonData.list&&jsonData.list[0]){
								var tempObj = jsonData.list[0];//rkxxpicImage
								var rkxxzpid = tempObj.zpid
								if(rkxxzpid &&　rkxxzpid != 0){
									Ext.getCmp('rkxxpicImage').body.update("<IMG SRC='yw/common/img/render/" + rkxxzpid + "' width='100%' height='100%' />");
								}else{
									Ext.getCmp('rkxxpicImage').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
								}
								Ext.getCmp("ckxxxm").setValue(tempObj.xm);
								Ext.getCmp("ckxxxb").setValue(Gnt.ux.Dict.getDictLable('CS_XB', tempObj.xb));
								Ext.getCmp("ckxxmz").setValue(Gnt.ux.Dict.getDictLable('CS_MZ', tempObj.mz));
								Ext.getCmp("ckxxcsrq").setValue(tempObj.csrq);
								Ext.getCmp("ckxxsfhm").setValue(tempObj.gmsfhm);
								Ext.getCmp("ckxxpcs").setValue(Gnt.ux.Dict.getDictLable('DWXXB', tempObj.pcs));
								var mlph = "";
								if(tempObj.mlph != undefined){
									mlph = tempObj.mlph;
								}
								Ext.getCmp("ckxxsfzdz").setValue(Gnt.ux.Dict.getDictLable('XZQHB', tempObj.ssxq)+mlph+tempObj.mlxz);
								Ext.getCmp("ckxxxzjd").setValue(Gnt.ux.Dict.getDictLable('XZJDXXB', tempObj.xzjd));
								Ext.getCmp("ckxxjlx").setValue(Gnt.ux.Dict.getDictLable('JLXXXB', tempObj.jlx));
								Ext.getCmp("ckxxmlph").setValue(tempObj.mlph);
								Ext.getCmp("ckxxmlxz").setValue(tempObj.mlxz);
								Ext.getCmp("ckxxssxq").setValue(Gnt.ux.Dict.getDictLable('XZQHB', tempObj.ssxq));
							}
						}
					);
//					if(zpid &&　zpid != 0){
//						Ext.getCmp('picImage').body.update("<IMG SRC='yw/fjgl/fjsh/img/render/" + zpid + "' width='100%' height='100%' />");
//					}else{
//						Ext.getCmp('picImage').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
//					}
				}else{
					Ext.getCmp('rkxxpicImage').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
					Ext.getCmp("ckxxxm").setValue("");
					Ext.getCmp("ckxxxb").setValue("");
					Ext.getCmp("ckxxmz").setValue("");
					Ext.getCmp("ckxxcsrq").setValue("");
					Ext.getCmp("ckxxsfhm").setValue("");
					Ext.getCmp("ckxxpcs").setValue("");
					Ext.getCmp("ckxxsfzdz").setValue("");
					Ext.getCmp("ckxxxzjd").setValue("");
					Ext.getCmp("ckxxjlx").setValue("");
					Ext.getCmp("ckxxmlph").setValue("");
					Ext.getCmp("ckxxmlxz").setValue("");
					Ext.getCmp("ckxxssxq").setValue("");
				}
			},
			rowdblclick:function(grid,row){
				selRes = grid.store.getAt(row);
				if(selRes && selRes.data){

				}else{

				}
			}
		}
	});
	var zhcx_dialog = new Gnt.ux.ZhcxDialog({
		pzlb : '30017',
		grid:sjshGrid,
		applyParam:function(){
			applyParam(this.grid.store);
		},
		callback: function(where){
			var grid = this.grid;
			var store = grid.store;
			var length = 0;
			var data = {"where": where};
			store.removeAll();
			data.pzlb = grid.store.pzlb;
			data.config_key = this.config_key;
			store.baseParams = data;
			if(this.applyParam){
				this.applyParam();
			}
			Ext.Msg.wait("正在执行查询，请稍后...");
			store.load({params:{start:0, limit:grid.store.pageSize}});
			store.on("load",function(store) {
				length = store.data.length
				if(length > 0){
					curIndex = 0;
					grid.fireEvent("rowclick",grid,curIndex);
					grid.getSelectionModel().selectRange(curIndex,curIndex);
				}else{
					showInfo("没有找到符合条件的数据！");
				}
				Ext.Msg.hide();
			},grid);
		}
	});
	var p1 = new Ext.Panel({
		layout:'border',
		newYewu: function(){

		},
		items:[
			{
				border:false,
				frame: false,
				region:'north',
				layout:'border',
				height: 80,
				items:[{
					border:false,
					frame: false,
					region:'center',
					layout:'column',
					items:[form30002,{
						border:false,
						frame: false,
						region:'weast',
						height: 80,
						columnWidth:0.3,
						layout:'border',
						items:[{
							border:false,
							frame: false,
							bodyStyle: 'padding:5px 5px 5px 5px',
							layout:'table',
							region:'center',
							layoutConfig: {
								columns: 10
							},
							items: [
								{
									border:false,
									frame: false,
									items:[new Ext.Button({
										id:'queryId',
										text:'查询',
										minWidth:60,
										handler:function(){
											var data = form30002.getForm().getValues();
											var store = sjshGrid.store;
											store.removeAll();
											store.baseParams = data;
											applyParam(store);
											store.load({params:{start:0, limit:user.dwDySet.ywlimit}});
											store.on('load',function(s,records){
												if(store.data.length > 0){
													curIndex = 0;
													sjshGrid.fireEvent("rowclick",sjshGrid,curIndex);
													sjshGrid.getSelectionModel().selectRange(curIndex,curIndex);
													Ext.getCmp("zpll").enable();
													Ext.getCmp("xxdz").enable();
													Ext.getCmp("hg").enable();
													Ext.getCmp("qbsh").enable();
													Ext.getCmp("bhg").enable();
													Ext.getCmp("ckxx").enable();
												}else{
													Ext.getCmp('picImage').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
													Ext.getCmp("slxxxm").setValue("");
													Ext.getCmp("slxxxb").setValue("");
													Ext.getCmp("slxxcs").setValue("");
													Ext.getCmp("slxxmz").setValue("");
													Ext.getCmp("slxxhm").setValue("");
													Ext.getCmp("slxxslyy").setValue("");
													Ext.getCmp("slxxlz").setValue("");
													Ext.getCmp("slxxyxqxq").setValue("");
													Ext.getCmp("slxxyxqxz").setValue("");
													Ext.getCmp("slxxxz").setValue("");
													Ext.getCmp('rkxxpicImage').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
													Ext.getCmp("ckxxxm").setValue("");
													Ext.getCmp("ckxxxb").setValue("");
													Ext.getCmp("ckxxmz").setValue("");
													Ext.getCmp("ckxxcsrq").setValue("");
													Ext.getCmp("ckxxsfhm").setValue("");
													Ext.getCmp("ckxxpcs").setValue("");
													Ext.getCmp("ckxxsfzdz").setValue("");
													Ext.getCmp("ckxxxzjd").setValue("");
													Ext.getCmp("ckxxjlx").setValue("");
													Ext.getCmp("ckxxmlph").setValue("");
													Ext.getCmp("ckxxmlxz").setValue("");
													Ext.getCmp("ckxxssxq").setValue("");
													Ext.getCmp("zpll").disable();
													Ext.getCmp("xxdz").disable();
													Ext.getCmp("hg").disable();
													Ext.getCmp("qbsh").disable();
													Ext.getCmp("bhg").disable();
													Ext.getCmp("ckxx").disable();

												}
												ryidTemp = "";
												rynbidTemp = "";
												zpipTemp = "";
												gmsfhmTemp ="";
												xmTemp ="";
												for( var i  = 0;i< store.data.length;i++){
													var selResTemp = sjshGrid.store.getAt(i);
													rynbidTemp = rynbidTemp+","+selResTemp.data.rynbid;
													zpipTemp = zpipTemp+","+selResTemp.data.zpid;
													gmsfhmTemp =gmsfhmTemp+","+selResTemp.data.gmsfhm;
													xmTemp =xmTemp+","+selResTemp.data.xm;
												}
												rynbidTemp = rynbidTemp.substring(1,rynbidTemp.length);
												gmsfhmTemp = gmsfhmTemp.substring(1,gmsfhmTemp.length);
												xmTemp = xmTemp.substring(1,xmTemp.length);
												zpidSub = zpipTemp.substring(1,zpipTemp.length);
												if(rynbidTemp&&zpidSub&&xmTemp&&gmsfhmTemp){
													zpSkim_window = new Gnt.ux.SelectZpSkim({
														rynbid:rynbidTemp,
														zpid:zpidSub,
														xm:xmTemp,
														gmsfhm:gmsfhmTemp
													});
												}
											},sjshGrid);
											Ext.Msg.hide();

										}
									})]
								},{
									frame:false,
									border:false,
									xtype:'panel',
									html:'',
									width:10
								},{
									border:false,
									frame: false,
									items:[new Ext.Button({
										id:'fzId',
										text:'复杂条件',
										minWidth:80,
										handler:function(){
											zhcx_dialog.show();
										}
									})]
								},{
									frame:false,
									border:false,
									xtype:'panel',
									html:'',
									width:10
								},{
									border:false,
									frame: false,
									items:[{
										xtype: 'DkButton',
										form:form30002,
										callback: function(){
											//Ext.getCmp("queryId").handler();
										}
									}]
								},{
									frame:false,
									border:false,
									xtype:'panel',
									html:'',
									width:10
								}

							]
						},{
							border:false,
							frame: false,
							bodyStyle: 'padding:10px',
							region:'south',
							autoHeight: true,
							layout:'column',
							defaults:{
								columnWidth: .5,
								fieldLabel: '',
								name: 'zt'
							},
							items: [{
								id:'c11',
								xtype:'checkbox',
								boxLabel: '已审核信息',
								//checked:true,
								name:'yshxx',
								listeners:{
									'check': function(obj,checked){
										if(checked){
										}else{
										}
									}
								}
							},{
								id:'c12',
								xtype:'checkbox',
								boxLabel: '快证信息',
								name:'kzxx',
								listeners:{
									'check': function(obj,checked){
										if(checked){
										}else{
										}
									}
								}
							}]
						}]


					}]
				}
				]
			},{
				title: '查询结果',
				collapsible: false,
				region:'center',
				layout:'border',
				border:false,
				frame:false,
				margins: '0 0 0 0',
				height:400,
				bodyStyle:'overflow-y:auto;overflow-x:scroll;height:300px',
				items:[sjshGrid]
			},{
				title: '',
				collapsible: false,
				region:'east',
				border:false,
				frame:false,
				width:"40%",
				margins: '0 0 0 0',
				layout:'column',
				//height:400,
				bodyStyle:'overflow-y:auto;height:400px',
				//autoHeight:true,
				//labelWidth:55,
				items:[
					{
						//border:false,
						//frame:false,
						region:'center',
						layout:'column',
						bodyStyle:'width:472px;',
						items:[
							{
								region:'east',
								columnWidth:.35,
								id:'picImage',
								title: '',
								bodyStyle:'padding:10px 10px 10px 10px',
								html: '照片',
								width:100,
								height:180,
								rowspan: 1,
								colspan:1
							},{
								region:'center',
								layout : 'column',
								columnWidth:.65,
								title: '受理信息',
								xtype: 'fieldset',
								bodyStyle : 'height: 180px;',
								defaults : {
									frame:false,
									border:false,
									columnWidth:.5,
									bodyStyle : 'padding:5px 5px 0px 5px'
								},
								items:[{
									layout: 'form',
									columnWidth:.65,
									labelWidth : 40,
									items:[{
										fieldLabel:'姓名',
										name:'xm',
										id:'slxxxm',
										anchor:'100%',
										disabled:true,
										xtype : 'textfield'
									}]
								},{
									layout: 'form',
									columnWidth:.35,
									labelWidth : 40,
									items:[{
										fieldLabel:'姓别',
										name:'xb',
										id:'slxxxb',
										anchor:'100%',
										disabled:true,
										xtype : 'textfield'
									}]
								},{
									layout: 'form',
									columnWidth:.65,
									labelWidth : 40,
									items:[{
										fieldLabel:'出生',
										name:'csrq',
										anchor:'100%',
										id:'slxxcs',
										disabled:true,
										xtype : 'textfield'
									}]
								},{
									layout: 'form',
									columnWidth:.35,
									labelWidth : 40,
									items:[{
										fieldLabel:'民族',
										name:'mz',
										anchor:'100%',
										id:'slxxmz',
										disabled:true,
										xtype: 'textfield'
									}]
								},{
									layout: 'form',
									columnWidth:1.,
									labelWidth : 40,
									items:[{
										fieldLabel:'号码',
										name:'dhhm',
										anchor:'100%',
										id:'slxxhm',
										disabled:true,
										xtype : 'textfield'
									}]
								},{
									layout: 'form',
									columnWidth:1.,
									labelWidth : 60,
									items:[{
										fieldLabel:'申领原因',
										name:'slyy',
										anchor:'100%',
										id:'slxxslyy',
										disabled:true,
										xtype : 'textfield'
									}]
								},{
									layout: 'form',
									columnWidth:1.,
									labelWidth : 40,
									items:[{
										fieldLabel:'领证',
										name:'lz',
										anchor:'100%',
										id:'slxxlz',
										disabled:true,
										xtype : 'textfield'
									}]
								}]
							},{

								region:'south',
								border:false,
								frame:false,
								layout : 'column',
								title: '',
								defaults : {
									frame:false,
									border:false,
									columnWidth:.5,
									bodyStyle : 'padding:5px 5px 0px 5px;width:200px'
								},
								items:[
									{
										layout: 'form',
										columnWidth:.5,
										labelWidth : 80,
										items:[{
											fieldLabel:'有效期限起',
											name:'yxqx',
											anchor:'100%',
											id:'slxxyxqxq',
											disabled:true,
											xtype : 'textfield'
										}]
									},{
										layout: 'form',
										columnWidth:.5,
										labelWidth : 80,
										items:[{
											fieldLabel:'有效期限至',
											name:'yxqx',
											labelSeparator: '',
											anchor:'100%',
											id:'slxxyxqxz',
											disabled:true,
											xtype : 'textfield'
										}]
									},{
										layout: 'form',
										columnWidth:1.,
										labelWidth : 40,
										items:[{
											labelSeparator: '',
											name:'dz',
											id:'slxxxz',
											anchor:'100%',
											disabled:true,
											xtype : 'textfield'
										}]
									}
								]
							}
						]
					},{
						border:false,
						//frame:false,
						region:'center',
						layout:'column',
						bodyStyle:'width:472px;',
						items:[
							{
								region:'east',
								columnWidth:.35,
								id:'rkxxpicImage',
								title: '',
								bodyStyle:'padding:10px 10px 10px 10px',
								html: '照片',
								width:100,
								height:180,
								rowspan: 1,
								colspan:1
							},{
								region:'center',
								layout : 'column',
								columnWidth:.65,
								title: '常口信息',
								xtype: 'fieldset',
								bodyStyle : 'height: 400px;',
								defaults : {
									frame:false,
									border:false,
									labelWidth : 70,
									columnWidth:1.,
									bodyStyle : 'padding:5px 5px 0px 5px'
								},
								items:[{
									layout: 'form',
									items:[{
										fieldLabel:'姓名',
										id:'ckxxxm',
										name:'ckxxxm',
										anchor:'100%',
										disabled:true,
										xtype : 'textfield'
									}]
								},{
									layout: 'form',
									items:[{
										fieldLabel:'姓别',
										id:'ckxxxb',
										name:'ckxxxb',
										anchor:'100%',
										disabled:true,
										xtype : 'textfield'
									}]
								},{
									layout: 'form',
									items:[{
										fieldLabel:'民族',
										id:'ckxxmz',
										name:'ckxxmz',
										anchor:'100%',
										disabled:true,
										xtype: 'textfield'
									}]
								},{
									layout: 'form',
									items:[{
										fieldLabel:'出生日期',
										id:'ckxxcsrq',
										name:'ckxxcsrq',
										anchor:'100%',
										disabled:true,
										xtype : 'textfield'
									}]
								},{
									layout: 'form',
									items:[{
										fieldLabel:'身份号码',
										id:'ckxxsfhm',
										name:'ckxxsfhm',
										anchor:'100%',
										disabled:true,
										xtype : 'textfield'
									}]
								},{
									layout: 'form',
									items:[{
										fieldLabel:'派出所',
										id:'ckxxpcs',
										name:'ckxxpcs',
										anchor:'100%',
										disabled:true,
										xtype : 'textfield'
									}]
								},{
									layout: 'form',
									items:[{
										fieldLabel:'身份证地址',
										id:'ckxxsfzdz',
										name:'ckxxsfzdz',
										anchor:'100%',
										disabled:true,
										xtype : 'textfield'
									}]
								},{
									layout: 'form',
									items:[{
										fieldLabel:'乡镇街道',
										id:'ckxxxzjd',
										name:'ckxxxzjd',
										anchor:'100%',
										disabled:true,
										xtype : 'textfield'
									}]
								},{
									layout: 'form',
									items:[{
										fieldLabel:'街路巷',
										id:'ckxxjlx',
										name:'ckxxjlx',
										anchor:'100%',
										disabled:true,
										xtype : 'textfield'
									}]
								},{
									layout: 'form',
									items:[{
										fieldLabel:'门楼牌号',
										id:'ckxxmlph',
										name:'ckxxmlph',
										anchor:'100%',
										disabled:true,
										xtype : 'textfield'
									}]
								},{
									layout: 'form',
									items:[{
										fieldLabel:'门楼详址',
										id:'ckxxmlxz',
										name:'ckxxmlxz',
										anchor:'100%',
										disabled:true,
										xtype : 'textfield'
									}]
								},{
									layout: 'form',
									items:[{
										fieldLabel:'省市县区',
										id:'ckxxssxq',
										name:'ckxxssxq',
										anchor:'100%',
										disabled:true,
										xtype : 'textfield'
									}]
								}]
							}
						]
					}
				]

			},
			{
				region:'south',
				height:200,
				title:'对比信息',
				border:false,
				frame:false,
				items:[{
					title: "对比结果",
					xtype: 'fieldset',
					autoHeight: true,
					layout:'column',
					defaults:{
						columnWidth: .2,
						fieldLabel: '',
						name: 'fw'
					},
					items: [{
						id:'dbjg',
						border:false,
						frame:false,
						html: '<a style="color:red;">对比结果:</a>',//boxLabel: '对比结果',
						//html: '显示历史受理信息',
						name:'dbjg'
					},{
						id:'r11',
						xtype:'checkbox',
						boxLabel: '显示历史受理信息',
						name:'lsslxx',
						checked:true,
						inputValue : "1"
					},{
						id:'r12',
						xtype:'checkbox',
						boxLabel: '显示证件照片',
						name:'zjzp',
						checked:true,
						inputValue : "2"
					},{
						id:'r13',
						xtype:'checkbox',
						boxLabel: '显示常口信息',
						checked:true,
						name:'ckxx',
						inputValue : "3"
					}]
				},{
					border:false,
					frame: false,
					height: 50,
					title:'',
					bodyStyle: 'padding:5px',
					layout:'table',
					layoutConfig: {
						columns: 18
					},
					items: [
						{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
						},{
							border:false,
							frame: false,
							items:[new Ext.Button({
								id:'zpll',
								text:'照片浏览',
								disabled:true,
								minWidth:80,
								handler:function(){
									if(selRes){
										zpSkim_window.show();
									}else{
										alert("请选中有效的数据!");
									}

								}
							})]
						},{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
						},{
							border:false,
							frame: false,
							items:[new Ext.Button({
								text:'详细对照',
								disabled:true,
								id:'xxdz',
								minWidth:60,
								handler:function(){
									var url = basePath + "yw/fjgl/xxdz?index=" +indexTemp;
									if(parent && parent.openWorkSpace){
										parent.openWorkSpace(url,  "分局审核对照", "sjshdz" );
									}else{
										window.location.href = url;
									}
								}
							})]
						},{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
						},{
							border:false,
							frame: false,
							items:[new Ext.Button({
								text:'合格',
								id:'hg',
								disabled:true,
								minWidth:60,
								handler:function(){
									if(selRes){
										var shdata=new Array();
										var data = {};
										data.nbslid = selRes.data.nbslid;
										data.shqk = '1';
										shdata.push(data);
										Gnt.submit(
											{
												shdata:Ext.encode(shdata)
											},
											"yw/fjgl/sjsh/processZjshyw",
											"正在进行分局合格审核，请稍后...",
											function(jsonData,params){
												showInfo("分局合格审核成功！");
												sjshGrid.store.reload();
											}
										);
									}else{
										alert("请先选中一条有效的数据！");
										return;
									}
								}
							})]
						},{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
						},{
							border:false,
							frame: false,
							items:[new Ext.Button({
								text:'全部审核',
								minWidth:60,
								id:'qbsh',
								disabled:true,
								handler:function(){
									if(selRes){
										shGgDialog.show();
										shGgDialog.setSelRes();
									}else{
										alert("请先选中一条有效的数据！");
										return;
									}
								}
							})]
						},{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
						},{
							border:false,
							frame: false,
							items:[new Ext.Button({
								text:'不合格',
								minWidth:60,
								id:'bhg',
								disabled:true,
								handler:function(){
									if(selRes){
										shBhgDialog.show();
										shBhgDialog.setSelRes();
									}else{
										alert("请先选中一条有效的数据！");
										return;
									}
								}
							})]
						},{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
						},{
							border:false,
							frame: false,
							items:[new Ext.Button({
								text:'常口信息',
								id:'ckxx',
								minWidth:60,
								disabled:true,
								handler:function(){
									czr={
										ryid:selectRyid,
										rynbid:selectRynbid
									}
									Gnt.toRyxx(czr);
								}
							})]
						},{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
						},{
							border:false,
							frame: false,
							items:[new Ext.Button({
								text:'受理信息',
								minWidth:60,
								id:'slxx',
								handler:function(){
									if(selRes){
										var url = basePath + "cx/edzjxx/edzslcx?jumpToEdzslMain=edzslMain&ryid=" +selRes.data.ryid;
										if(parent && parent.openWorkSpace){
											parent.openWorkSpace(url,  "受理信息查询", "slxxcx" );
										}else{
											window.location.href = url;
										}
									}else{
										alert("无有效数据!");
									}
								}
							})]
						},{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
						},{
							border:false,
							frame: false,
							items:[new Ext.Button({
								text:'关闭',
								minWidth:60,
								handler:function(){
									window.parent.closeWorkSpace();
								}
							})]
						}

					]

				},{
					title:'注意：',
					border:false,
					frame: false,
					bodyStyle: 'padding:5px',
					layout:'table',
					layoutConfig: {
						columns: 4
					},

					items: [{
						border:false,
						frame: false,
						items:[new Ext.Button({
							text:'刷新',
							minWidth:60,
							handler:function(){
								Ext.getCmp("queryId").handler();
							}
						})]
					},{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
					},{
						frame:false,
						border:false,
						xtype:'panel',
						html:'<a style="color:red;">提示：一次只能审核80条(可设定)受理信息，审核完毕后，请刷新数据，再进行审核</a>',
						width:700
					},{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						height:40
					},{
						frame:false,
						border:false,
						id:'_READ_CARD_ID',
						xtype:'panel',
						html:'',
						width:10
					}]
				}]
			}
		]
	});
	function applyParam(store){
		var yshxx = '';
		if(Ext.getCmp('c11').getValue()){
			yshxx = '1';
		}
		var kzxx = '';
		if(Ext.getCmp('c12').getValue()){
			kzxx = '1';
		}
		Ext.apply(store.baseParams, {yshxx:yshxx,kzxx:kzxx});

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
			items:[p1]
		}
	});
});
