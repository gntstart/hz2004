var selRes = null;
var curIndex = -1;
var spywid = null;
var selectHjywid ="";
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//本业务需要加载的配置
	//10037 Hj_迁入审批信息查询
    //10031 Hj_迁入审批子信息
    
	if(!Gnt.loadSjpzb("10037,10031",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	
	function initYw(){
			qrsp_grid.store.removeAll();
			qrspzxx_grid.store.removeAll();
			spcl_grid.store.removeAll();
			sbxx_form.getForm().reset();
			
			selRes = null;
			curIndex = -1;
			spywid = null;
	}

	/////////////////////////////p1///////////////////////////////////
	var form10037_query = new Gnt.ux.SjpzForm({
		pzlb: '10037',
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
					qrsp_grid.store.removeAll();
					qrspzxx_grid.store.removeAll();
					
					data.pzlb = qrsp_grid.pzlb;
					data.config_key = "queryPoHJSP_HJSPSQB";
					
					qrsp_grid.store.baseParams = data;
					qrsp_grid.store.load({params:{start:0, limit:20}})
				}
			})
		]
	});
	
	var qrsp_grid = new Gnt.ux.SjpzGrid({
		pkname: 'spywid',
		pzlb: '10037',
		region:'center',
    	title: '迁入审批信息',
    	url: 'yw/spgl/hjsp/queryQrspxxByUser.json',
		listeners:{
			rowclick:function(g, rowIndex, e){
	   			selRes = g.store.getAt(rowIndex);
    			curIndex = rowIndex;
    		
    			var data = selRes.data;
    			var store = qrspzxx_grid.store;
				store.baseParams = {
						pzlb: store.pzlb,
						spywid: data.spywid
				};
				store.load({params:{start:0, limit:20}})
    		}
		}
	});
	
	var qrspzxx_grid = new Gnt.ux.SjpzGrid({
		pkname: 'spsqzid',
		pzlb: '10031',
		height: 190,
		region:'south',
    	title: '迁入审批子信息',
    	showPaging:false,
    	url: 'yw/common/queryHjspywzb.json',
		listeners:{
			rowclick:function(g, rowIndex, e){

    		}
		}, 
		buttons:[
		         new Ext.Button({
						text:'确定',
						minWidth:100,
						width:150,
						handler:function(){
							if(qrspzxx_grid.store.getCount()<=0){
								showInfo("请查询并选择审批信息！");
								return;
							}
							
							//点击确认迁出后，执行此方法
							Ext.getCmp('card').getLayout().setActiveItem(1);
							p2.doLayout();
							
							(function(){
								qrspzxx_grid2.store.removeAll();
								qrspzxx_grid.store.each(function(r){
									var rr = new qrspzxx_grid2.store.reader.recordType(r.data, r.data[qrspzxx_grid.pkname]);
									qrspzxx_grid2.store.add([rr]);
								});
								
								sbxx_form.getForm().setValues(selRes.data);
								
								spcl_grid.loadData(selRes.data.splx, selRes.data.spywid);
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
	
	var p1 = new Ext.Panel({
		layout: 'border',
		items:[
		       form10037_query,
		       {
		    	   region:'center',
		    	   layout:'border',
		    	   items:[
		    	          qrsp_grid, qrspzxx_grid
		    	   ]
		       }
		]
	});
	
	////////////////////////////p2////////////////////////////////////////
	var qrspzxx_grid2 = new Gnt.ux.SjpzGrid({
		pkname: 'spsqzid',
		pzlb: '10031',
		width: 300,
		region:'west',
    	title: '迁入审批子信息',
    	showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){

    		}
		}
	});
	
	var sbxx_form = new Gnt.ux.SjpzForm({
		closable: false,
		region:'center',
		pzlb: '10037',
		labelWidth :  180,
		cols:2,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		//bindViewGrid: ywGrid,
		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
		changeDictCust:function(cmb,res){
			return;
		},
        title: '申报信息'
	});
	
	var spcl_grid = new Gnt.ux.SpclGrid({
		region:'west',
		width: 200
	});
	
	var ywForm =new  Gnt.ux.SpdjForm({
		title:'审批结果'
	});
	
	var p2 = new Ext.Panel({
		layout: 'border',
		border:false,
		frame:false,
		items:[
		       sbxx_form,
		       {
		    	   	height: 200,
		    	   	region:'south',
		    	   	layout:'border',
		   			border:false,
		   			frame:false,
		   			items:[
		    	          qrspzxx_grid2, {
		    	        	  region:'center',
		    	        	  layout: 'border',
		    	        	  items:[
		    	        	         spcl_grid,ywForm
		    	        	  ]
		    	          }
		    	    ],
		    	    buttons:[
		   		         new Ext.Button({
		   						text:'审批流程',
		   						minWidth:100,
		   						width:150,
		   						handler:function(){
		   							if(selRes){
		   								var store = splcGrid.store;
			   							store.removeAll();
			   							store.baseParams = {
			   									spywid:selRes.data.spywid,
			   									splx:2,
            									limit:20
            							};
			   							store.load();
			   							Ext.getCmp('card').getLayout().setActiveItem(3);
		   							}else{
		   								alert("请选择有效的数据!")
		   							}
		   							
		   						}
		       	         }),new Ext.Button({
		 					text:'查看档案',
							minWidth:100,
							width:150,
							hzywid:getQueryParam("hzywid"),
							handler:function(){

//								if (!this.hzywid) {
//									showInfo("户政平台中间表对象不能为空！");
//									return null;
//								}
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
		   								
		   								url += "spywid=" + selRes.data.spywid+"&ywcode=105&ryid="+selRes.data.ryid;
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
		   							if(!ywForm.getForm().isValid()){
		   								showErr("审批信息必须填写！");
		   								return;
		   							}
		   							
		   							var sub_data = ywForm.getForm().getValues();
		   							sub_data.dzid = selRes.data.xydzid;
		   							sub_data.spywid = selRes.data.spywid;
		   							//add by zjm 20200225 审批结果字典取第一位
		   							if(sub_data.czjg){
		   								var czjgTemp = sub_data.czjg;
		   								sub_data.czjg = czjgTemp.substring(0,1);
		   							}
		   							Gnt.submit(
		   									{
		   										voQrspspxx: Ext.encode([sub_data])
		   									}, 
		   									"yw/spgl/hjsp/processQrspspyw", 
		   									"正在处理迁入审批登记信息，请稍后...", 
		   									function(jsonData,params){
		   										showInfo("操作成功！",function(){
		   											qrsp_grid.store.remove(qrsp_grid.store.getById(selRes.data.spywid));
		   											qrspzxx_grid.store.removeAll();
		   											
		   											if(jsonData && jsonData.list[0] && jsonData.list[0].voQrspspfhxx){
		   												selectHjywid=jsonData.list[0].voQrspspfhxx[0].spywid;
		   											}
		   											zqzPrint(selectHjywid);
		   											Ext.getCmp('card').getLayout().setActiveItem(2);
		   										});
		   									}
		   							);
		   						}
		       	         }),new Ext.Button({
	   						text:'返回',
	   						minWidth:100,
	   						width:150,
	   						handler:function(){
	   							Ext.getCmp('card').getLayout().setActiveItem(0);
	   							v.doLayout();
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
		       }
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
		    	   html: '<center><br/><br/><br/><br/><h2>迁入审批业务完成！</center></h2>',
			   		buttons:[
			 	            new Ext.Button({
			 					text:'准迁（移）证打印',
			 					minWidth:100,
			 					width:150,
			 					handler:function(){
			 						zqzPrint(selectHjywid);
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
	var splcGrid = new Gnt.ux.SjpzGrid({
		title: '已审批流程',
		pkname: 'splsid',
		pzlb: '20024',
		url:'cx/spcx/qrspcx/getQrsplc.json',
		region:'center',
		showPaging:true
	});
	
	var p4 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[splcGrid],
		buttons:[
			{
    			text:'返回',
    			minWidth:60,
    			handler:function(){
    				Ext.getCmp('card').getLayout().setActiveItem(1);
//    				v.doLayout();
    			}
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
        	items:[p1, p2, p3,p4]
        }
    });
});