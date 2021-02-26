var selRes = null;
var curIndex = -1;
var spywid = null;
var g_sHhnbid = null;
var selectRynbid = null;
var selectHhnbid = null;
var hjblidArray=[];
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//本业务需要加载的配置
	//10032 Hj_户籍补录审批登记信息
	if(!Gnt.loadSjpzb("10032",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	
	function initYw(){
			spcl_grid.store.removeAll();
			spxx_grid.store.removeAll();
			spzxx_grid.store.removeAll();
			sbxx_form.getForm().reset();
			
			selRes = null;
			curIndex = -1;
			spywid = null;
			g_sHhnbid = null;
	}

	/////////////////////////////p1///////////////////////////////////
	var form_query = new Gnt.ux.SjpzForm({
		pzlb: '10032',
		url: 'yw/queryRyxx',
		title:'查询条件',
		region:'north',
		height:130,
		cols:2,
		formType:'query',
		buttons:[
			new Ext.Button({
				text:'查询',
				minWidth:75,
				handler:function(){
					var data = this.findParentByType("sjpz_form").getForm().getValues();
					if(data["_end_djsj"]){
						if(data["_end_djsj"]>Ext.util.Format.date(new Date(),'Ymd')){
							alert("输入日期不得大于当前日期！");
							return;
						}
					}
					//组装查询
					spxx_grid.store.removeAll();
					data.pzlb = spxx_grid.pzlb;
					
					spxx_grid.store.baseParams = data;
					spxx_grid.store.load({params:{start:0, limit:20}})
				}
			})
		]
	});
	
	var spxx_grid = new Gnt.ux.SjpzGrid({
		pkname: 'spywid',
		pzlb: '10032',
		region:'center',
    	title: '查询结果',
    	url: 'yw/spgl/spcl/queryHjblspxx.json',
		listeners:{
			rowclick:function(g, rowIndex, e){
	   			selRes = g.store.getAt(rowIndex);
    			curIndex = rowIndex;
    			
    			var data = selRes.data;
    			var store = spzxx_grid.store;
				store.baseParams = {
						pzlb: store.pzlb,
						ywxl: data.ywxl
				};
				store.load({params:{start:0, limit:20}})
    		}
		}
	});
	
	var spzxx_grid = new Gnt.ux.SjpzGrid({
		pkname: 'spywid',
		pzlb: '10032',
		height: 190,
		region:'south',
    	title: '审批子信息',
    	showPaging:false,
    	url: 'yw/spgl/spcl/queryHjblspxx.json',
		listeners:{
			rowclick:function(g, rowIndex, e){

    		}
		}
	});
	
	var p1 = new Ext.Panel({
		layout: 'border',
		broder:false,
		frame:false,
		items:[
		       form_query,
		       {
		    	   	region:'center',
		    	   	layout:'border',
			   		broder:false,
					frame:false,
					items:[
		    	          spxx_grid, spzxx_grid
		    	    ]
		       }
		],
		buttons:[
		         new Ext.Button({
						text:'确定',
						minWidth:100,
						width:150,
						handler:function(){
							if(spzxx_grid.store.getCount()<=0){
								showInfo("请查询并选择审批信息！");
								return;
							}
							
							//点击确认迁出后，执行此方法
							Ext.getCmp('card').getLayout().setActiveItem(1);
							v.doLayout();
							
							(function(){
								sbxx_form.getForm().setValues(selRes.data);
							}).defer(200);
						}
    	         }),
    	         new Ext.Button({
						text:'关闭',
						minWidth:100,
						width:150,
						handler:function(){
							if(parent.closeWorkSpace)
								parent.closeWorkSpace();
							else
								window.close();
						}
    	         })
    	 ]
	});
	
	////////////////////////////p2////////////////////////////////////////
	var sbxx_form = new Gnt.ux.SjpzForm({
		closable: false,
		region:'center',
		pzlb: '10032',
		labelWidth :  180,
		formType:'view',
		cols:2,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		//bindViewGrid: ywGrid,
		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
		changeDictCust:function(cmb,res){
			return;
		},
        title: '审批处理信息（变更前）'
	});
	
	var p2 = new Ext.Panel({
		layout: 'fit',
		border:false,
		frame:false,
		items: sbxx_form,
		buttons:[new Ext.Button({
			text:'查看档案',
			minWidth:100,
			width:150,
			hzywid:getQueryParam("hzywid"),
			handler:function(){

//				if (!this.hzywid) {
//					showInfo("户政平台中间表对象不能为空！");
//					return null;
//				}
				var sbid = "";
				Gnt.makeHzyw({
					hzywid : this.hzywid,
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
			}
     }),
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
		   								
		   								url += "spywid=" + selRes.data.spywid+"&ywcode=108&ryid="+selRes.data.ryid;
										url += "&sfz=" +  selRes.data.gmsfhm;
										url += "&sbrsfzh=" +  selRes.data.sbrgmsfhm;
										url += "&dlmj=" + user.yhdlm;
										url += "&yhsfzh=" + user.gmsfhm;
		   								
		   								window.open(url);
		   							});
		   						}
		       	         }),*/new Ext.Button({
		   						text:'保存',
		   						minWidth:100,
		   						width:150,
		   						handler:function(){
		   							
		   							var subdata = {};
		   							subdata.blList = [];
		   							
		   							var gmsfhm = "", xm = "";
		   							
		   							spzxx_grid.store.each(function(r){
		   								subdata.blList.push(r.data);
		   								if(r.data.gmsfhm && r.data.gmsfhm!=""){
		   									if(gmsfhm!=""){
		   										gmsfhm += ",";
		   										xm += ",";
		   									}
		   									gmsfhm += r.data.gmsfhm;
		   									xm +=  r.data.xm;
		   								}
		   							});
		   							
		   							subdata.blList = Ext.encode(subdata.blList);
		   							
		   							var spyw = selRes.data;

		   							if(spyw.sshhid && spyw.sshhid>0){
		   								//入户
		   								subdata.rhhdxx = Ext.encode({hhnbid: spyw.sshhid});
		   							}else{
		   								//立户
		   								subdata.lhhdxx = Ext.encode({
			   									ssxq: spyw.ssssxq,
			   								    xzjd: spyw.ssxzjd,
			   								    pcs: spyw.sspcs,
			   								    jlx: spyw.ssjlx,
			   								    mlph: spyw.ssmlph,
			   								    mlxz: spyw.ssxz,
			   								    jcwh: spyw.ssjwh,
			   								    hlx: spyw.sshlx,
			   								    zrq: spyw.ssjwzrq,
			   								    bzid: spyw.bzdz,
			   								    bzdzid: spyw.bzdzid,
			   								    bzdzx: spyw.bzdzx,
			   								    bzdzy: spyw.bzdzy,
			   								    bzdzst: spyw.bzdzst
			   							});
		   							}
		   							//审批申报人从审批信息里面获取
		   							subdata.sbjbxx= null;
		   							
		   							Gnt.yw.checkCh(gmsfhm, xm, function(re, reMap){
		   								subdata.chList = (re==null?"":Ext.encode(re));
		   								if(re && reMap){
		   									//处理ryid
		   									var list = Ext.decode(subdata.blList);
		   									Ext.each(list, function(r){
		   										if(reMap[r.gmsfhm]){
		   											r.ryid = reMap[r.gmsfhm];
		   											if(r.ryid=="0" || r.ryid==0)
		   												r.ryid = null;
		   										}
		   									});
		   									subdata.blList = Ext.encode(list);
		   								}
		   								
										Gnt.submit(
												subdata, 
												"yw/hjyw/hjbl/processHjblyw", 
												"正在处理户籍补录审批，请稍后...", 
												function(jsonData,params){
													selectRynbid = "";
													if(jsonData && jsonData.list[0] && jsonData.list[0].voHjblfhxx){
														selectHhnbid = jsonData.list[0].voHjblfhxx[0].hhnbid;
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
													}
													printRk(3,selectHhnbid,selectRynbid,[true,true,true,true,true],[true,true,false,false,false],[true,true,true,true,true]);
													Ext.getCmp('card').getLayout().setActiveItem(2);
													//alert("户籍补录业务审批成功！");
													//这里需要刁杰补充打印信息，和户籍补录一样
													
//													if(!jsonData.list[0].spFlag){
//														zqzPrint(jsonData.list[0].hjywid);
//													}
													
												},
			   									function(json){
													
													/**
														户籍补录没有rynbid/hhnbid,无法获取
													 */
			   										if(json.message){
			   											if(json.errcode && json.errcode == 509){
			   												//需要选择新户主，调用窗口
			   												new Gnt.ux.SelectNewHz({
			   													splx:"hjbl",
			   													selectHh:selRes.data.sshh,
			   													selectXm:selRes.data.xm,
			   													selectCsrq:selRes.data.csrq,
			   													hhnbid:selRes.data.sshhid,
			   													msg:json.message,
			   													callback:function(jo,flag){
			   														if(flag){
			   															var list = Ext.decode(subdata.blList);
			   															Ext.each(list, function(r){
			   						   										r.rynbid=jo.rynbid;
			   						   										r.hyzk=jo.hyzk;
			   						   										r.yhzgx=jo.yhzgx;
			   						   									});
			   						   									subdata.blList = Ext.encode(list);
			   														}
			   														Gnt.submit(
			   															subdata, 
			   															"yw/hjyw/hjbl/processHjblyw", 
			   															"正在处理户籍补录审批，请稍后...", 
			   															function(jsonData,params){
			   																selectRynbid = "";
			   																if(jsonData && jsonData.list[0] && jsonData.list[0].voHjblfhxx){
			   																	selectHhnbid = jsonData.list[0].voHjblfhxx[0].hhnbid;
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
			   																}
			   																printRk(3,selectHhnbid,selectRynbid,[true,true,true,true,true],[true,true,false,false,false],[true,true,true,true,true]);
			   																Ext.getCmp('card').getLayout().setActiveItem(2);
			   																
//			   																if(!jsonData.list[0].spFlag){
//			   																	zqzPrint(jsonData.list[0].hjywid);
//			   																}
			   																
			   															}
			   														);
			   													}
			   												}).show();;
			   											}else{
			   												showErr(json.message);
			   											}
			   										}
			   										
			   									}
										);
		   							});
		   						}
		       	         }),new Ext.Button({
	   						text:'返回',
	   						minWidth:100,
	   						width:150,
	   						handler:function(){
	   							Ext.getCmp('card').getLayout().setActiveItem(0);
	   						}
	       	         }),
		       	         new Ext.Button({
		   						text:'关闭',
		   						minWidth:100,
		   						width:150,
		   						handler:function(){
		   							if(parent.closeWorkSpace)
		   								parent.closeWorkSpace();
		   							else
		   								window.close();
		   						}
		       	         })
		]
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
					   borde:false
				}, {
		    	   region:'center',
		    	   title:'',
		    	   frame:false,
				   borde:false,
		    	   html: '<center><br/><br/><br/><br/><h2>户籍补录业务完成！</center></h2>',
			   		buttons:[
				   			new Ext.Button({
								text:'户籍打印',
								minWidth:100,
								width:150,
								handler:function(){
									printRk(3,selectHhnbid,selectRynbid,[true,true,true,true,true],[true,true,false,false,false],[true,true,true,true,true]);
								}
		    	            }),
			 	           new Ext.Button({
								text:'新办理',
								minWidth:100,
								width:150,
								handler:function(){
										Ext.getCmp('card').getLayout().setActiveItem(0);
										initYw();
								}
		    	            }),
			 	            new Ext.Button({
								text:'继续办理',
								minWidth:100,
								width:150,
								handler:function(){
										Ext.getCmp('card').getLayout().setActiveItem(0);
								}
		    	            }),new Ext.Button({
								text:'关闭',
								minWidth:100,
								width:150,
								handler:function(){
									if(parent && parent.closeWorkSpace)
										parent.closeWorkSpace();
									else
										window.close();
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
        	items:[ p1, p2, p3]
        }
    });
});