//全省人员信息查询
/**
 * 标准地址选择,必须先加载commFrames.js
 */
Gnt.ux.SelectQsryxx = Ext.extend(Ext.Window, {
	title:'全省人口信息查询',
	closeAction:'hide',
	modal:true,
	width:700,
	height:600,
	margins:'5',
	layout:'border',
	pageSize:30,
	setHzyw:function(hzywjo, hzywjo_list){
		this.hzywjo_list = hzywjo_list;
		this.hzywjo = hzywjo;
		
		//判断主迁入
		var zqr = hzywjo.bsqrsfz;
		Ext.each(hzywjo_list, function(data){
			if(data.sfzqr=="1"){
				zqr = data.bsqrsfz;
			}
		});
		
		this.hzywjo_zqr = zqr;
	},
	resetData:function(){
		this.qrForm.getForm().reset();
		this.grid10019.store.removeAll();
		this.grid10019_2.store.removeAll();
		this.grid20016.store.removeAll();
		
		this.hxx = null;
	},
	selectRes: [],
	selectJtcy:function(res, removeFlag){
		var win = this;
		var store2 = win.grid10019_2.store;
		
		var r2 = store2.getById(res.data.rynbid);
		if(r2){
			if(removeFlag=="1"){
				if(win.hzywjo){
					res.data["_sel"] = "0";
				}else{
					res.set("_sel", "0");
				}
				
				store2.remove(r2);
				var tmp = new Array();
				Ext.each(win.selectRes, function(data){
					if(data.rynbid!=res.data.rynbid){
						tmp.push(data);
					}
				});
				win.selectRes = tmp;
			}
		}else{
			var r = new store2.reader.recordType(res.data, res.data.rynbid);
			r.set("_sel", "0");
			store2.add([r]);
			
			if(win.hzywjo){
				res.data["_sel"] = "1";
			}else{
				res.set("_sel", "1");
			}
			win.selectRes.push(r.data);
		}

		if(store2.getCount()>0){
			win.grid10019_2.setTitle("选择迁入户成员（主迁人【" + store2.getAt(0).data.xm +  "】）");
		}else{
			win.grid10019_2.setTitle("选择迁入户成员");
		}
	},
	queryData:function(){
		var win = this;

		var form = win.qrForm;
		var dqmcField = form.getForm().findField("dqmc");
		
		var gridHcy = win.grid10019;
		var grid = win.grid20016;
		
		if(!form.getForm().isValid()){
			showInfo("数据校验没有通过，请检查！");
			return;
		}
		
		var sfz = form.getForm().getValues().gmsfhm;
		
		/**
			2018.10.15
			新增	刁杰
			身份证号码不是本省的不予查询
			
			2018.10.16
			线上表示不能单从身份证号码区分是否本省人口,并且是过期需求,不做修改
		 */
		/*
		if(!sfz.startWith('34')){
			showInfo("此身份证不属于本省户籍！请关闭此窗口直接录入！");
			return;
		}
		*/
		dqmcField.setValue("查询中...");
		grid.store.removeAll();
		gridHcy.store.removeAll();
		win.selectRes = [];
		win.grid10019_2.store.removeAll();
		win.grid10019_2.setTitle("选择迁入户成员");

		//先获取身份证所在地区，然后对此地区发起查询
		Gnt.submit(
				{
					gmsfhm: sfz
				}, 
				"yw/common/getKDSQY_dqbmAndHxx.json", 
				"请稍后...", 
				function(jsonData,params){
					Ext.Msg.hide();
					if(jsonData.list && jsonData.list.length>0){
						grid.store.loadData(jsonData);
						dqmcField.setValue(jsonData.dqmc);
						win.lastHcyData = jsonData;
						
						//加载户成员
						(function(){
							grid.fireEvent("rowclick",grid, 0);
						}).defer(200);
					}else{
						if(win.hzywjo){
							win.hide();
						}else{
							showInfo("此身份证不属于本省户籍！请关闭此窗口直接录入！");
						}
					}
				}
		);
	},
	show:function(){
		Gnt.ux.SelectQsryxx.superclass.show.call(this);
		var me = this;
		
		if(this.hzywjo){
			(function(){
				//存在户政业务处理，那么初始化
				me.qrForm.getForm().setValues({
					gmsfhm: me.hzywjo.bsqrsfz
				});
				me.queryData();
			}).defer(200);
		}else{
			(function(){
				me.qrForm.getForm().findField("gmsfhm").focus();
			}).defer(200);
		}
	},
	initComponent : function(c){
		if(!Gnt.loadSjpzb("10016,10019,20016",function(){}))
			return;
		
		var returnTitleText = this.returnTitleText;
		if(!returnTitleText || returnTitleText=="") returnTitleText = "全省人口信息查询";
		
		this.returnTitleText = returnTitleText;
		
		this.setTitle(returnTitleText);
		
		var queryFs = new Ext.form.FormPanel({
	    	height: 50,
	    	region: 'north',
	    	anchor:'100% 100%',
	    	buttonAlign:'right',
	    	labelAlign:'right',
	    	frame:true,
	    	border:false,
	    	fileUpload: true, 
	    	margins:'0',
	        layout:'form',
	        labelWidth:70,
	       	items:[{
	        		layout:'column',
	    			frame:false,
	    			border:false,
	        		defaults:{
	        			border:false,
	        			frame:false
	        		},
	            	items:[{
					                columnWidth:0.40,
				    	           	layout: 'form',
				    	           	bodyStyle:'padding:8 0 0 20',
				        	       	items: [{
										xtype:'textfield',
										anchor:'100%',
										disabled:true,
										name:'dqmc',
										labelWidth:60,
										fieldLabel:'人口地区'
									}]
								},{
					                columnWidth:0.40,
				    	           	layout: 'form',
				    	           	bodyStyle:'padding:5 0 0 0',
				        	       	items: [{
										xtype:'textfield',
										anchor:'100%',
										name:'gmsfhm',
										allowBlank:false,
										vtype: 'Sfzh',
										fieldLabel:'身份证号'
									}]
								}, {
								 	columnWidth:0.20,
				    	           	layout: 'fit',
				    	           	bodyStyle:'padding:5 0 0 0',
				        	       	items: [{
										xtype:'panel',
										items:[{
											xtype:'button',
											minWidth: 80,
											text: '查询人员',
											handler:function(){
												var win = this.findParentByType("select_qsryxx_window");
												win.queryData();
						    	 			}
										}]
									}]
							}
				]
			}]
		});
		
		this.qrForm = queryFs;
		
		var rccxForm = new Ext.form.FormPanel({
	    	height: 45,
	    	region: 'south',
	    	anchor:'100% 100%',
	    	buttonAlign:'right',
	    	labelAlign:'right',
	    	frame:true,
	    	border:false,
	    	fileUpload: true, 
	    	margins:'0',
	        layout:'form',
	        labelWidth:70,
	       	items:[{
	        		layout:'column',
	    			frame:false,
	    			border:false,
	        		defaults:{
	        			border:false,
	        			frame:false
	        		},
	            	items:[{
					                columnWidth:0.40,
				    	           	layout: 'form',
				    	           	bodyStyle:'padding:8 0 0 20',
				        	       	items: [{
										xtype:'textfield',
										anchor:'100%',
										name:'xm',
										labelWidth:60,
										fieldLabel:'姓名'
									}]
								},{
					                columnWidth:0.40,
				    	           	layout: 'form',
				    	           	bodyStyle:'padding:5 0 0 0',
				        	       	items: [{
										xtype:'textfield',
										anchor:'100%',
										name:'gmsfhm',
										vtype: 'Sfzh',
										fieldLabel:'身份证号'
									}]
								}, {
								 	columnWidth:0.20,
				    	           	layout: 'fit',
				    	           	bodyStyle:'padding:5 0 0 0',
				        	       	items: [{
										xtype:'panel',
										items:[{
											xtype:'button',
											minWidth: 80,
											text: '户内精确查询',
											handler:function(){
						    	 				var win = this.findParentByType("select_qsryxx_window");

						    					var form = win.rccxForm;
						    					var gridHcy = win.grid10019;
						    					var grid = win.grid20016;
		
						    					if(grid.store.getCount()<=0){
					    							showErr("必须先查询户信息！");
					    						}
						    					
						    					var xm = form.getForm().getValues().xm;
						    					var sfz = form.getForm().getValues().gmsfhm;
						    					
						    					if(Gnt.util.isEmpty(xm) && Gnt.util.isEmpty(sfz)){
						    						//showInfo("至少输入一个二次查询条件！");
						    						//return;
						    					}
						    					
					    						gridHcy.store.removeAll();
					    						var store = gridHcy.store;
					    						store.baseParams = {
					    								jccz: (Gnt.util.isEmpty(xm) && Gnt.util.isEmpty(sfz))?'0':'1',
					    								pzlb: store.pzlb,
					    								dqbm: win.lastHcyData.dqbm,
					    								kdq_gmsfhm: sfz,
					    								xm: xm,
					    								hhnbid: win.hxx.hhnbid
					    						};
					    						
					    						store.load({params:{start:0, limit:40}})
						    	 			}
										}]
									}]
							}
				]
			}]
		});
		
		this.rccxForm = rccxForm;
		
		this.grid20016 = new Gnt.ux.SjpzGrid({
			title: '户信息列表',
			region : 'center',
			pzlb: '20016',
			showPaging: false,
			pkname: 'rynbid',
			listeners:{
	    		rowclick:function(g, rowIndex, e){
	    			var win = this.findParentByType("select_qsryxx_window");
	    			
	    			var gridHcy = win.grid10019;
	    			var data = g.store.getAt(rowIndex).data;
	    			win.hxx = data;
	    			
	    			win.grid10019_2.store.removeAll();
	    			win.selectRes = [];
	    			
	    			var store = gridHcy.store;
	    			var allsfz = "";
	    			
	    			//this.hzywjo_list = hzywjo_list;
	    			//this.hzywjo = hzywjo;
	    			
	    			if(win.hzywjo){
	    				allsfz = win.hzywjo.bsqrsfz + "," + win.hzywjo.sqrsfz;
	    				if(win.hzywjo_list){
		    				Ext.each(hzywjo_list, function(data){
		    					if(data.bsqrsfz)
		    						allsfz += "," + data.bsqrsfz;
		    				});
	    				}
	    			}
	    			
	    			store.removeAll();
					store.baseParams = {
							pzlb: store.pzlb,
							jccz: (allsfz==""?"0":"1"),
							dqbm: win.lastHcyData.dqbm,
							kdq_gmsfhm: (allsfz==""?win.lastHcyData.list[0].gmsfhm:allsfz),
							hhnbid: data.hhnbid
					};
					store.load({params:{start:0, limit:100000}})
	    		}
	    	}
		});
		
		this.grid10019 = new Gnt.ux.SjpzGrid({
			title: '户成员列表',
			region : 'center',
			url: 'yw/common/getKDSQY_dqbmAndHcy.json',
			pzlb: '10019',
			pageSize: 100000,
			pkname: 'rynbid',
			loadCallback:function(res, store, bind_grid){
				var win = bind_grid.findParentByType("select_qsryxx_window");
				var store2 = win.grid10019_2.store;
				if(store2.getCount()>0){
					Ext.each(res, function(r){
						var r2 = store2.getById(r.data.rynbid);
		    			if(r2){
		    				r.set("_sel", "1");
		    			}
					});
				}
				
    			if(win.hzywjo){
    				var map = {};
    				map[win.hzywjo.bsqrsfz] = '1';
    				if(win.hzywjo_list){
	    				Ext.each(hzywjo_list, function(data){
	    					if(data.bsqrsfz)
	    						map[data.bsqrsfz]  = '1';
	    				});
    				}
    				
    				//进行初始化选择
    				for(i=0;i<res.length;i++){
    					//主迁人
    					if(map[res[i].data.gmsfhm] && map[res[i].data.gmsfhm]==win.hzywjo_zqr){
    						res[i].data["_sel"] =  "1";
    						win.selectJtcy(res[i], "0");
    					}
    				}
    				
    				for(i=0;i<res.length;i++){
    					//随迁人
    					if(map[res[i].data.gmsfhm] && map[res[i].data.gmsfhm]!=win.hzywjo_zqr){
    						res[i].data["_sel"] =  "1";
    						win.selectJtcy(res[i], "0");
    					}
    				}

    				win.buttons[0].handler();
    			}
			},
			listeners:{
	    		rowdblclick:function(g, rowIndex, e){
	    			var win = this.findParentByType("select_qsryxx_window");
	    			var res = g.store.getAt(rowIndex);
	    			
	    			win.selectJtcy(res, "1");
	    		}
	    	}
		});
		
		this.grid10019_2 = new Gnt.ux.SjpzGrid({
			title: '选择迁入户成员',
			region : 'south',
			height:140,
			pzlb: '10019',
			showPaging: false,
			pkname: 'rynbid',
			listeners:{
	    		rowdblclick:function(g, rowIndex, e){
	    			var win = this.findParentByType("select_qsryxx_window");
	    			
	    			var gridHcy = win.grid10019;
	    			var res = g.store.getAt(rowIndex);
	    			g.store.remove(res);
	    			
	    			var r2 = gridHcy.store.getById(res.data.rynbid);
	    			if(r2){
	    				r2.set("_sel", "0");
	    			}
	    		}
	    	}
		});
		
		this.items = [this.qrForm, this.grid20016, {
			xtype:'panel',
			layout: 'border',
			region : 'south',
			height:380,
			items:[
				{
					xtype:'panel',
					layout: 'border',
					region : 'center',
					items:[
					      this.grid10019,
					      this.rccxForm
					]
				},
				this.grid10019_2
			]
		}];
		
		Gnt.ux.SelectQsryxx.superclass.initComponent.call(this);
	},
    buttons:[{
        text:'确定',
		minWidth:75,
		handler:function(){
			var win = this.findParentByType("select_qsryxx_window");
			var grid = win.grid10019_2;
			
			if(win.selectRes.length<=0){
				showErr("请先查询户信息，并选择迁入人员！");
				return;
			}
			
			//var hcyList = new Array();
			//grid.store.each(function(r){
			//	hcyList.push(r.data);
			//});
			
			win.hide();
			
			var kds_jo = {
					dqbm: win.lastHcyData.dqbm,
					dqmc: win.lastHcyData.dqmc
			};
			
			if(win.selectCallback){
				win.selectCallback(win.selectRes, kds_jo);
			}
		}
    },{
        text:'关闭',
		minWidth:75,
		handler:function(){
			var win = this.findParentByType("select_qsryxx_window");
			
			/**
				住址变动业务需要
				关闭后清空信息
			 */
			if(win.closeWin)
				win.closeWin('rh');
			
			win.hide();
		}
    }]
});
Ext.reg('select_qsryxx_window', Gnt.ux.SelectQsryxx);
