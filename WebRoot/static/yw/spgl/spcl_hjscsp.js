var selRes = null;
var curIndex = -1;
var spywid = null;
var selectRynbid = "";
var selectHhnbid = "";
var selectedSpywid = null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//本业务需要加载的配置
	//10016 Hj_人信息简单查询
	//10033 Hj_户籍删除审批信息
	if(!Gnt.loadSjpzb("10016,10033",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	
	function initYw(){
			spxx_grid.store.removeAll();
			spcl_grid.store.removeAll();
			sbxx_form.getForm().reset();
			
			selRes = null;
			curIndex = -1;
			spywid = null;
	}
	
	/////////////////////////////p1///////////////////////////////////
	var form_query = new Gnt.ux.SjpzForm({
		pzlb: '10016',
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
		pzlb: '10033',
		region:'center',
    	title: '查询结果',
    	url: 'yw/spgl/spcl/queryHjscspxx.json',
		listeners:{
			rowclick:function(g, rowIndex, e){
	   			selRes = g.store.getAt(rowIndex);
	   			selectedSpywid = selRes.data.spywid
    			curIndex = rowIndex;
    		}
		}
	});
	
	var p1 = new Ext.Panel({
		layout: 'border',
		frame:false,
		border:true,
		items:[
		       form_query,
		       spxx_grid
		],
		buttons:[
				         new Ext.Button({
								text:'确定',
								minWidth:100,
								width:150,
								handler:function(){
									//modify by zjm 20201218 将延时2秒代码注释
									
//									(function(){
										sbxx_form.getForm().reset();
										sbxx_form.getForm().setValues(selRes.data);
										//点击确认迁出后，执行此方法
										Ext.getCmp('card').getLayout().setActiveItem(1);
										v.doLayout();
//									}).defer(200);
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
		pzlb: '10033',
		formType:'view',
		labelWidth :  180,
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
		items:sbxx_form,
		buttons:[
			new Ext.Button({
					text:'查看档案',
					minWidth:100,
					width:150,
					hzywid:getQueryParam("hzywid"),
					handler:function(){

						if (!this.hzywid) {
							showInfo("户政平台中间表对象不能为空！");
							return null;
						}
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
	         }),/*new Ext.Button({
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
		   								
		   								url += "spywid=" + selRes.data.spywid+"&ywcode=109&ryid="+selRes.data.ryid;
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
		   							Gnt.submit(
		   									{
		   										hjscxx: Ext.encode([selRes.data])
		   									}, 
		   									"yw/hjyw/hjsc/processHjscyw", 
		   									"正在处理审批信息，请稍后...", 
		   									function(jsonData,params){
		   										showInfo("操作成功！",function(){
		   											spxx_grid.store.remove(spxx_grid.store.getById(selRes.data.spywid));
		   											if(jsonData && jsonData.list[0] && jsonData.list[0].voHjscfhxx){
		   												selectHhnbid = jsonData.list[0].voHjscfhxx[0].hhnbid;
			   											for(var i=0; i < jsonData.list[0].voHjscfhxx.length; i++){
			   												selectRynbid += jsonData.list[0].voHjscfhxx[i].rynbid + ",";
			   											}
			   											if(selectRynbid.length > 0){
			   												selectRynbid = selectRynbid.substr(0,selectRynbid.length - 1);
			   											}
			   											printRk(3,selectHhnbid,selectRynbid,[true,true,true,true,true],[true,true,false,false,false],[true,true,true,true,true]);
		   										}
		   											Ext.getCmp('card').getLayout().setActiveItem(2);
		   										});
		   									},
		   									function(json){
		   										if(json.message){
		   											if(json.errcode && json.errcode == 509){
		   												//需要选择新户主，调用窗口
		   												new Gnt.ux.SelectNewHz({
		   													splx:"hjsc",
		   													selectHzId:selRes.data.rynbid,
		   													hhnbid:selRes.data.hhnbid,
		   													msg:json.message,
		   													callback:function(jo){
		   														Gnt.submit(
		   							   								{
		   							   									hjscxx: Ext.encode([selRes.data])
		   							   								},
		   							   								"yw/hjyw/hjsc/processHjscyw", 
		   							   								"正在处理审批信息，请稍后...", 
		   							   								function(jsonData,params){
		   							   									showInfo("操作成功！",function(){
		   							   										spxx_grid.store.remove(spxx_grid.store.getById(selRes.data.spywid));
		   							   										Ext.getCmp('card').getLayout().setActiveItem(2);
		   							   									});
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
		    	   html: '<center><br/><br/><br/><br/><h2>户籍删除业务完成！</center></h2>',
			   		buttons:[
				   			new Ext.Button({
		   						text:'户簿打印',
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