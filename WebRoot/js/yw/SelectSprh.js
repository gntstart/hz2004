/**
 * 标准地址选择,必须先加载commFrames.js
 */
var hhidTemp =null;
var canClick = true;//用来控制连续点击触发多少单击事件  add by zjm 20200707
var bbClick = true;//用来控制连续点击触发多少单击事件  add by zjm 20200707
Gnt.ux.SelectSprh = Ext.extend(Ext.Window, {
	title:'迁入审批信息查询',
	closeAction:'hide',
	modal:true,
	width:800,
	height:600,
	margins:'5',
	layout:'border',
	pageSize:30,
	setHzyw:function(hzywjo){
		this.hzywjo = hzywjo;
		
		if(hzywjo!=null){
			//存在户政业务处理，那么初始化
			this.form10030.getForm().setValues({
				gmsfhm: hzywjo.bsqrsfz
			});
			this.form10030.buttons[0].handler();
		}
	},
	initComponent : function(){
		//10030 Hj_迁入审批信息；10031 Hj_迁入审批子信息；
		if(!Gnt.loadSjpzb("10030,10031",function(){}))
			return;
		
		var returnTitleText = this.returnTitleText;
		if(!returnTitleText || returnTitleText=="") returnTitleText = "户信息查询";
		
		this.returnTitleText = returnTitleText;
		
		this.setTitle(returnTitleText);
		
		
		this.form10030 = new Gnt.ux.SjpzForm({
			closable: false,
			region : 'north',
			pzlb: '10030',
			formType:'query',
			labelWidth: 100,
			cols:2,
			height:100,
			//labelWidth :  80,
			getDictCust:function(cmb,visiontype){
				return;
			},
			changeDictCust:function(cmb,res){
				return;
			},
	        title: '',
	        buttons:[{
	            text:'查询',
				minWidth:75,
				handler:function(){
					var win = this.findParentByType("sprh_window");

					var form = win.form10030;
					var grid10030 = win.grid10030;
					var grid10031 = win.grid10031;
					
					if(!form.getForm().isValid()){
						showInfo("数据校验没有通过，请检查！");
						return;
					}
					
					var data = form.getForm().getValues();

					grid10030.store.removeAll();
					grid10031.store.removeAll();
					win.spywData = null;
					
					data.pzlb = grid10030.store.pzlb;
					//删除综合查询条件
					data.where = "";
					data.log_code = "F5007";
					data.kdqqy = win.kdqqy;
					
					grid10030.store.baseParams = data;
					grid10030.store.load({params:{start:0, limit:grid10030.store.pageSize}});
				}
	        },{
	            text:'综合查询',
				minWidth:75,
				handler:function(){
					var win = this.findParentByType("sprh_window");
					if(!win.ZhcxDialog){
						win.ZhcxDialog = new Gnt.ux.ZhcxDialog({
							pzlb: '10030',
							callback:function(where){
								var win = this.me.findParentByType("sprh_window");
								
								var grid10030 = win.grid10030;
								var grid10031 = win.grid10031;

								var data = {"where": where};

								grid10030.store.removeAll();
								grid10031.store.removeAll();
								win.spywData = null;
								
								data.pzlb = grid10030.store.pzlb;
								data.kdqqy = win.kdqqy;

								data.log_code = "F5007";
								grid10030.store.baseParams = data;
								Ext.Msg.wait("正在执行查询，请稍后...");
								grid10030.store.load({params:{start:0, limit:grid10030.store.pageSize}});
								grid10030.store.on("load",function(store) {  
									Ext.Msg.hide();
								},grid10030);
							}
						});
						win.ZhcxDialog.me = this;
					}
					win.ZhcxDialog.show();
				}
	        }]
		});
		this.hcyGrid = new Gnt.ux.SjpzGrid({
			pkname: 'hhnbid',
			pzlb: '20001',
	    	region:'south',
			height:150,
	    	title: '',
			url: 'yw/common/queryRyxx.json',
	    	showPaging:false,
			listeners:{
				rowclick:function(g, rowIndex, e){}
			}
		});
		this.grid10030 = new Gnt.ux.SjpzGrid({
			title: '迁入审批信息',
			region : 'center',
			url: 'yw/common/queryHjspyw.json',
			pzlb: '10030',
			pkname: 'spywid',
			loadCallback: function(res, store, bind_grid){
				var win = bind_grid.findParentByType("sprh_window");
				if(win.hzywjo){
					//如果存在户政业务，那么自动化处理
					if(res.length>0){
						bind_grid.fireEvent("rowclick",bind_grid,0);
					}else{
						showInfo("没有找到相关的人员资料，户政业务无法处理！");
					}
				}
			},
			listeners:{
	    		rowclick:function(g, rowIndex, e){
	    			if(canClick&&bbClick){
	    				canClick =false;
	    				bbClick = false;
	    				var win = this.findParentByType("sprh_window");
		    			var grid10031 = win.grid10031;
		    			
		    			var data = g.store.getAt(rowIndex).data;
		    			if(data.qrdhhid>0){
		    				var gethhnbidStore = win.hcyGrid.store;
							gethhnbidStore.baseParams = {
									hhid: data.qrdhhid,
									pzlb: gethhnbidStore.pzlb
							};
							gethhnbidStore.load({params:{start:0, limit:20}});
							gethhnbidStore.on('load',function(s,records){
		    					if(records.length>0){
		    						hhidTemp=records[0].data.hhnbid;
		    					}else{
		    						hhidTemp = data.qrdhhid;
		    					}
		    				});
		    			}else{
		    				hhidTemp = data.qrdhhid;
		    			}
		    			win.spywData = data;
		    			Gnt.ux.bengboduyou(data.qrdhhid,null,data.gmsfhm,win.qydjFlag,function(flag){
		    				bbClick = true;
		    				var store = grid10031.store;
							store.baseParams = {
									kdqqy: win.kdqqy,
									pzlb: store.pzlb,
									spywid: data.spywid,
									log_code: 'F5008'
							};
							store.load({params:{start:0, limit:20}});
		    			});
	    			}
					
	    		}
	    	}
		});
		
		this.grid10031 = new Gnt.ux.SjpzGrid({
			title: '迁入人员列表',
			region : 'south',
			height:180,
			url: 'yw/common/queryHjspywzb.json',
			pzlb: '10031',
			loadCallback:function(res, store, bind_grid){
				var win = bind_grid.findParentByType("sprh_window");
				if(store.data.length){
					var objData = store.getAt(0).data;
					Gnt.submit(
							{
								kdqqy: win.kdqqy,
								pzlb: store.pzlb,
								spywid: objData.spywid
						}, 
							"yw/common/checkHjspywzb.json", 
							"正在核验是否反馈，请稍后...", 
							function(jsonData,params){
								var rtnChkData = jsonData.list[0];
								if(rtnChkData.kdqqy_fkzt&&rtnChkData.kdqqy_qcdqbm&&(rtnChkData.kdqqy_fkzt==1||rtnChkData.kdqqy_fkzt==0)){
									store.removeAll();
									if(window.confirm("跨地区迁移未反馈，无法迁入，是否先进行一站式迁出业务？")){
										var url = basePath + "yw/hjyw/yzsydqc?jumpyzsydqc=1&spywid="+rtnChkData.spywid;			      
										canClick=true;
				        				if(parent && parent.openWorkSpace){
				        					parent.openWorkSpace(url,  "一站式异地迁出", "yzsydqc");
				        				}else{
				        					window.location.href = url;
				        				}
									}
									canClick=true;
								}else{
									canClick=true;
									if(win.hzywjo){
										//如果存在户政业务，那么自动化处理
										if(res.length>0){
											(function(){
												win.buttons[0].handler();
											}).defer(200);
										}
									}
								}
							}
					);
				}else{
					canClick=true;
				}
				
				
			},
			pkname: 'spsqzid'
		});
		
		this.items = [this.form10030, this.grid10030, this.grid10031];
		
		Gnt.ux.SelectSprh.superclass.initComponent.call(this);
	},
    buttons:[{
        text:'确定',
		minWidth:75,
		handler:function(){
			var win = this.findParentByType("sprh_window");
			var grid = win.grid10031;
			//var grid = win.items.get(2);
			if(grid.store.getCount()<=0){
				showErr("请先查询并选择迁入审批信息！");
				return;
			}
			win.hide();

			var spywData = win.spywData;
			var qrryList = new Array();
			grid.store.each(function(r){
				qrryList.push(r.data);
			});
			
			if(spywData.qrdhhid>0){
				Gnt.submit(
						{hhid:spywData.qrdhhid}, 
						"yw/common/validHhid.json", 
						"正在验证户号信息，请稍后...", 
						function(jsonData){
							if(jsonData&&jsonData.list&&jsonData.list.length>0){
								spywData.qrdhhid = hhidTemp;
								if(win.callback)
									win.callback('sprh', qrryList, spywData,true);
							
							}else{
								alert("查询户成员信息出错！");
								return; 
							}
						}
				)
			}else{
				if(win.callback)
					win.callback('sprh', qrryList, spywData,false);
			}
			
		}
    },{
        text:'关闭',
		minWidth:75,
		handler:function(){
			var win = this.findParentByType("sprh_window");
			win.hide();
		}
    }]
});
Ext.reg('sprh_window', Gnt.ux.SelectSprh);
