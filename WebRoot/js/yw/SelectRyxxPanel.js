
var clickFlag = true;
var selGmsfhm = null;
var zjflag = null;
var jxblIndex = null;
//查询户信息 -》查询人信息 -》选择人信息
Gnt.ux.SelectRyxxPanel = Ext.extend(Ext.Panel, {
	height:250,
	width:600,
	layout: 'border',
	qhbgShow: false,
	stripeRows: true,
    border:true,
    margins:'0 0 0 0',
	frame:false,
    border:true,
    frame:true,
	buttonAlign:'left',
    setHzyw: function(hzywjo, hzywjo_list){
    	this.hzywjo = hzywjo;
    	this.hzywjo_list = hzywjo_list;
    	/*
    	this.form10016.getForm().setValues({
    		gmsfhm: hzywjo.bsqrsfz
//    		,xm: hzywjo.bsqrxm
    	});
    	*/
    	this.form10016.fieldSetValue(this.form10016.getForm().findField("gmsfhm"),hzywjo.bsqrsfz);
    	
    	this.form10016.buttons[0].handler();
    },
    setZjHzyw: function(gmsfhm,flag){
    	selGmsfhm = gmsfhm;
    	zjflag = flag;
    	this.form10016.fieldSetValue(this.form10016.getForm().findField("gmsfhm"),gmsfhm);
    	this.form10016.buttons[0].handler();
    },
    newYewu:function(){
    	//新业务
    	this.grid10019_2.store.removeAll();
    	this.grid10019_3.store.removeAll();
    	this.grid10019.store.removeAll();
    },
    jxbl:function(){
    	//继续办理
    	this.grid10019_2.store.removeAll();
    	this.grid10019_3.store.removeAll();
    	this.grid10019.store.removeAll();
    	this.grid20016.fireEvent("rowdblclick",this.grid20016,jxblIndex);
    },
    cxjgRowdblclick:function(res){
		var data = res.data;
		
		var p = this;
		p.data = data;
		
		p.grid10019.store.removeAll();
		p.grid10019_2.store.removeAll();

		var jqBox = p.filterForm.items.items[0].items.items[2].items.items[0];
		if(jqBox.checked){
			jqBox.setValue(0);
		}

		p.grid10019_2.setTitle("户成员");
		p.grid10019.setTitle("已选择人员");
		
		var store = p.grid10019_2.store;
		var store1 = p.grid10019_3.store;
		data.pzlb = store.pzlb;
		var params = {
				pzlb: store.pzlb,
				hhnbid: data.hhnbid,
				log_code: 'F1004'
		};
		
		if(p.hzywjo_list||zjflag=="zjBggz"||zjflag=="zjFxbg"){
			var allsfz = "";
			if(p.hzywjo_list){
				Ext.each(p.hzywjo_list, function(jo){
					allsfz += "," + jo.bsqrsfz;
				});
			}else if(zjflag=="zjBggz"||zjflag=="zjFxbg"){
				allsfz += "," +selGmsfhm;
			}
			params.allsfz = allsfz;
			params.hlx = data.hlx;
		}
		
		store.baseParams = params;
		store.load({params:{start:0, limit:40}});
//		if(zjflag=="zjBggz"||zjflag=="zjFxbg"){
//			alert(1111);
//			store.on('load',function(s,records){
//				var curIndex = 0;
//				for(var i = 0;i<records.length;i++){
//					var selRes = p.grid10019_2.store.getAt(i);
//					if(selRes.data.gmsfhm==selGmsfhm){
//						curIndex = i;
//						p.grid10019_2.fireEvent("rowdblclick",p.grid10019_2,curIndex);
//						p.grid10019_2.getSelectionModel().selectRange(curIndex,curIndex);
//						
//						
//					}
//				}
//			});
//		}
		store1.baseParams = params;
		store1.load({params:{start:0, limit:40}});
		
		store1.on('load',function(s,records){
			p.grid10019_2.setTitle("户成员列表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;户成员总数&nbsp;:&nbsp;" + s.getCount());
		});
		
		/**
			住址变动业务需要
		 */
		if(p.rowdblclickBack){
			p.rowdblclickBack(data);
		}
	},
	initComponent: function() {
		if(!Gnt.loadSjpzb("10016,10019,20016",function(){}))
			return;
		
		var returnBtnText = this.returnBtnText;
		var returnBtnMsg = this.returnBtnMsg;
		if(!returnBtnText || returnBtnText=="") returnBtnText = "确定迁出";
		if(!returnBtnMsg || returnBtnMsg=="") returnBtnMsg = "必须选择需要迁出的人员！";
		
		this.returnBtnText = returnBtnText;
		this.returnBtnMsg = returnBtnMsg;

		this.form10016 = new Gnt.ux.SjpzForm({
			pzlb: '10016',
			url: 'yw/common/queryRyxx.json',
			title:'查询条件',
			region:'north',
			height:100,
			formType:'query',
			buttons:[
				new Ext.Button({
					text:'查询',
					minWidth:75,
					handler:function(){
						var data = this.findParentByType("sjpz_form").getForm().getValues();
						if(Gnt.util.isEmpty(data)){
							showInfo("至少输入一个查询条件！");
							return;
						}
						
						var p = this.findParentByType("select_ryxx_panel");
						
						p.data = null;
						p.grid20016.store.removeAll();
						p.grid10019.store.removeAll();
						p.grid10019_2.store.removeAll();
						
						var store = p.grid20016.store;
						
						data.pzlb = store.pzlb;
						//删除综合查询条件
						data.where = "";
						data.log_code = "F1027";
						store.baseParams = data;
						store.load({params:{start:0, limit: 20}})
//						if(zjflag=="zjBggz"||zjflag=="zjFxbg"){
//							store.on('load',function(s,records){
//	       					if(records.length>0){
//								curIndex = 0;
//								p.grid20016.fireEvent("rowdblclick",p.grid20016,curIndex);
//								p.grid20016.getSelectionModel().selectRange(curIndex,curIndex);
//	       					}
//	       				});
//						}

					}
				}),
				new Ext.Button({
					text:'综合查询',
					minWidth:75,
					handler:function(){
						var win = this.findParentByType("select_ryxx_panel");
						if(!win.ZhcxDialog){
							win.ZhcxDialog = new Gnt.ux.ZhcxDialog({
								pzlb: '20016',
								callback:function(where){
									var p = this.me.findParentByType("select_ryxx_panel");
									data = {
											"where": where
									};
									
									p.data = null;
									p.grid20016.store.removeAll();
									p.grid10019.store.removeAll();
									p.grid10019_2.store.removeAll();
									
									var store = p.grid20016.store;
									data.log_code = "F1027";
									data.pzlb = store.pzlb;
									store.baseParams = data;
									Ext.Msg.wait("正在执行查询，请稍后...");
									store.load({params:{start:0, limit: 20}})
									store.on("load",function(store) {  
										Ext.Msg.hide();
									},p.grid20016);
//									if(zjflag=="zjBggz"||zjflag=="zjFxbg"){
//										store.on('load',function(s,records){
//					       					if(records.length>0){
//												curIndex = 0;
//												p.grid20016.fireEvent("rowdblclick",p.grid20016,curIndex);
//												p.grid20016.getSelectionModel().selectRange(curIndex,curIndex);
//					       					}
//					       				});
//									}
								}
							});
							win.ZhcxDialog.me = this;
						}
						win.ZhcxDialog.show();
					}
				})
			]
		});
		
		this.grid20016 = new Gnt.ux.SjpzGrid({
			pkname: 'rynbid',
			pzlb: '20016',
			region:'center',
			url: 'yw/common/queryRyxx.json',
			title:'查询结果',
			loadCallback:function(res, store, bind_grid){
				var win = bind_grid.findParentByType("select_ryxx_panel");
				if(win.hzywjo||zjflag=="zjBggz"||zjflag=="zjFxbg"){
					//如果存在户政业务，那么自动化处理
					if(res.length>0){
						win.cxjgRowdblclick(res[0]);
					}else{
						showInfo("没有找到相关的人员资料，户政业务无法处理！");
					}
				}
			},
			listeners:{
				rowclick:function(g, rowIndex, e){
					
	    		},
	    		rowdblclick:function(g, rowIndex, e){
	    			jxblIndex = rowIndex;
	    			var res = g.store.getAt(rowIndex);
	    			var p = this.findParentByType("select_ryxx_panel");
	    			p.cxjgRowdblclick(res);
	    		}
			},
			height:500
		});
		
		this.filterForm = new Ext.form.FormPanel({
	    	title:'',
	    	region:'north',
	    	height: 30,
	    	anchor:'100% 100%',
	    	buttonAlign:'right',
	    	labelAlign:'right',
	    	frame:false,
	    	margins:'10 10 0 0',
	    	border:false,
	        layout:'form',
	        labelWidth:75,
	       	items:[{
	        		layout:'column',
	    			frame:false,
	    			border:false,
	        		defaults:{
	        			border:false,
	        			frame:false
	        		},
	            	items:[{
		                columnWidth:.50,
	    	           	layout: 'form',
	    	           	bodyStyle:'padding:0 0 0 0',
	        	       	items: [{
	        	       		id:'hcyxmId',
							xtype:'textfield',
							anchor:'100%',
							width: 200,
							name:'xm',
							fieldLabel:'按姓名查询'
						}]
					},{
			           	columnWidth:.25,
		    	        layout: 'form',
		    	        bodyStyle:'padding:0 0 0 0',
		        	    items: [{
		        	    	//anchor:'100%',
				     		xtype:'button',
							text:'查询',
							handler:function(){
								var data = this.findParentByType("form").getForm().getValues();
	
								var p = this.findParentByType("select_ryxx_panel");
								var store = p.grid10019_2.store;
								
								if(store.baseParams.xm)
									delete store.baseParams.xm;
								
								if(store.baseParams.jqcx)
									delete store.baseParams.jqcx;
								
								Ext.apply(store.baseParams, data);
								
								if(Ext.getCmp('jqId').checked){
									var cxdata = this.findParentByType("select_ryxx_panel").form10016.getForm().getValues();
									Ext.apply(store.baseParams, cxdata);
								}
								store.baseParams.hhnbid = p.data.hhnbid;
								
								store.load({params:{start:0, limit: 40 }});
							}
						}]
					},{
			           	columnWidth:.25,
		    	        layout: 'form',
		    	        bodyStyle:'padding:0 0 0 0',
		        	    items: [{
		        	    	id:'jqId',
							xtype:'checkbox',
							inputValue:'1',
					    	name:'jqcx',
							fieldLabel:'精确查询',
	        				listeners:{
	        					'check': function(obj,checked){
	        						var p = this.findParentByType("select_ryxx_panel");
	        						/**
	        							按姓名查询表单值
	        						 */
	        						var data = this.findParentByType("form").getForm().getValues();
	        						
	        						var store = p.grid10019_2.store;
	        						store.baseParams = data;
	        						
	        						if(!p.grid20016.getSelectionModel().getSelected()){
	        							return;
	        						}
	        						
	        						Ext.apply(store.baseParams, {hhnbid: p.grid20016.getSelectionModel().getSelected().data.hhnbid});
    								
	        						if(checked){
	        							if(p.grid20016.store.getCount() > 0){
	        								
	        								clickFlag = true;
	        								
	        								/**
	        									查询条件 查询值
	        								 */
	        								var cxdata = this.findParentByType("select_ryxx_panel").form10016.getForm().getValues();
	        								Ext.apply(store.baseParams, cxdata);
	        								var res = p.grid20016.getSelectionModel().getSelected().data;
	        								Ext.apply(store.baseParams, {jqgmsfhm:res.gmsfhm, jqcx:1});
	        								
	        								store.load({params:{start:0, limit: 40 }});
	        							}else{
	        								alert("请先执行查询!");
	        								clickFlag = false;
	        								Ext.getCmp('jqId').setValue(false);
	        							}
	        						}else{
	        							if(clickFlag){
	        								store.load({params:{start:0, limit: 40 }});
	        							}
        								clickFlag = true;
	        						}
	        					}
	        				}
						}]
					}
				]
			}]
		});
		
		this.grid10019_2 = new Gnt.ux.SjpzGrid({
			pkname: 'rynbid',
			pzlb: '10020',
			region:'center',
			url: 'yw/common/queryRyxx.json',
			title:'户成员',
			pageSize:40,
			beforeload:function(store, options){
				var p = this.findParentByType("select_ryxx_panel");
				if(p.data)
					return true;
				
				showErr("请先查询，并且选择户信息！");
				return false;
			},
			//加载成功，回调函数
			loadCallback:function(res, store, grid){
				var p = grid.findParentByType("select_ryxx_panel");
				var store = p.grid10019.store;
				
				if(store.getCount()>0){
					for(var i=0;i<res.length;i++){
						var r =res[i];
						var to_r = store.getById(r.data.rynbid);
						if(to_r){
							r.set('_sel' , '1');
						}
					}
				}
				
				if(p.hzywjo){
					var selobj = {
							
					};
					Ext.each(p.hzywjo_list,function(jo){
						selobj[jo.bsqrsfz] = '1';
					});
					if(p.hzywjo&&p.hzywjo.ywlb&&p.hzywjo.ywlb==16){
						grid.findParentByType("select_ryxx_panel").buttons[0].handler();
					}else if(p.hzywjo){
						for(var i=0;i<res.length;i++){
							var gmsfhm = res[i].data.gmsfhm;
							if(selobj[gmsfhm]){
								//从事件驱动的异步方式，修改为函数驱动的方式，具有更小的延迟
								grid.addSelectData(i);
								//grid.fireEvent("rowdblclick",grid, i);
							}
							//使用函数替代事件去掉，防止延迟
							if(p.select){
								var dict = {};
								var list1 = new Array();
								var list2 = new Array();
								var list3 = new Array();
								
								for(var i=0;i<res.length;i++){
									var data = res[i].data;
									var gmsfhm = data.gmsfhm;
									if(selobj[gmsfhm]){
										//选择
										data._sel = '1';
										list1.push(data);
									}else{
										//不选择
										data._sel = '0';
										list2.push(data);
									}
								}
								list3 = list1.concat(list2);
								
								(function(){
									p.select(list3, p.data);
								}).defer(200);
								
								return;
							}
						}
					}
				}
				if(zjflag=="zjBggz"||zjflag=="zjFxbg"){
					var selobj = {
							
					};
					selobj[selGmsfhm] = '1';
					for(var i=0;i<res.length;i++){
						var gmsfhm = res[i].data.gmsfhm;
						if(selobj[gmsfhm]){
							//从事件驱动的异步方式，修改为函数驱动的方式，具有更小的延迟
							grid.addSelectData(i);
							//grid.fireEvent("rowdblclick",grid, i);
						}
						//使用函数替代事件去掉，防止延迟
						if(p.select){
							var dict = {};
							var list1 = new Array();
							var list2 = new Array();
							var list3 = new Array();
							
							for(var i=0;i<res.length;i++){
								var data = res[i].data;
								var gmsfhm = data.gmsfhm;
								if(selobj[gmsfhm]){
									//选择
									data._sel = '1';
									list1.push(data);
								}else{
									//不选择
									data._sel = '0';
									list2.push(data);
								}
							}
							list3 = list1.concat(list2);
							
							(function(){
								p.select(list3, p.data);
							}).defer(200);
							
							return;
						}
					}
				
				}
			},
			addSelectData:function(rowIndex){
				//函数方式解决
				var res = this.store.getAt(rowIndex);
				var data = res.data;
				
				var p = this.findParentByType("select_ryxx_panel");
				var store = p.grid10019.store;
				if(store.getById(data.rynbid)==null){
					var r = new store.reader.recordType(data, data.rynbid);
					res.set('_sel' , '1');
					p.grid10019.setTitle("已选择人数&nbsp;:&nbsp;" + (p.grid10019.getStore().getCount() + 1));
					store.add([r]);
//					if(zjflag=="zjBggz"||zjflag=="zjFxbg"){
//						p.grid10019.buttons[0].handler();
//					}
					return r;
				}
				
				return null;
			},
			listeners:{
				rowclick:function(g, rowIndex, e){
				},
				rowdblclick:function(g, rowIndex, e){
					g.addSelectData(rowIndex);
					
				}
			}
		});
		
		/**
			防止双击选择了户后按姓名查找再次筛选后业务页面显示的户成员丢失
		 */
		this.grid10019_3 = new Gnt.ux.SjpzGrid({
			pkname: 'rynbid',
			pzlb: '10019',
			region:'center',
			title:'',
			url: 'yw/common/queryRyxx.json'
		});
		
		var buttons = [
		         						{
		        							text: returnBtnText,
		        							minWidth:75,
		        							handler:function(){
		        								var p = this.findParentByType("select_ryxx_panel");
		        								var store = p.grid10019.store;
		        								if(store.getCount()<=0){
		        									showErr(p.returnBtnMsg);
		        									return;
		        								}
		        								
		        								if(p.select){
		        									var dict = {};
		        									
		        									var list = new Array();
		        									store.each(function(r){
		        										var data = r.data;
		        										data._sel = '1';
		        										dict[data.rynbid]='1';
		        										
		        										list.push(data);
		        									});
		        									
		        									var store2 = p.grid10019_3.store;
		        									store2.each(function(r){
		        										var data = r.data;
		        										var rynbid = data.rynbid;
		        										
		        										if(!dict[rynbid]){
		        											data._sel = '0';
		        											list.push(data);
		        										}
		        									});
		        									
		        									p.select(list, p.data);
		        								}
		        							}
		        						},{
		        							text:'关闭',
		    		        				minWidth:75,
		    		        				handler:function(){
		    		        						if(window.parent.closeWorkSpace){
		    		        							window.parent.closeWorkSpace();
		    		        						}
		    		        				}
		    		        			
		        						}];
		
		this.grid10019 = new Gnt.ux.SjpzGrid({
			pkname: 'rynbid',
			pzlb: '10020',
			region:'center',
			url: '',
			title:'已选择人员',
			showPaging:false,
			buttons: buttons,
			listeners:{
				rowclick:function(g, rowIndex, e){
					
	    		},
	    		rowdblclick:function(g, rowIndex, e){
	    			var res = g.store.getAt(rowIndex);
	    			g.store.remove(res);
	    			
	    			var p = this.findParentByType("select_ryxx_panel");
	    			
	    			if(p.grid10019.getStore().getCount() == 0){
	    				p.grid10019.setTitle("已选择人员");
	    			}else{
	    				p.grid10019.setTitle("已选择人数&nbsp;:&nbsp;" + g.store.getCount());
	    			}
	    			var store = p.grid10019_2.store;
	    			var r = store.getById(res.data.rynbid);
	    			if(r){
	    				r.set('_sel', '0');
	    			}
	    		}
			}
		});
		
		this.items = [
		        {
		        	layout:'border',
		        	region:'center',
		        	items:[
		        	       this.form10016,
		        	       this.grid20016
		        	]
		        },{
		        	layout:'border',
		        	region:'south',
		        	height: 260,
		        	items:[
		        		{
	        	    	  	layout:'border',
	  		        		region:'west',
	  		        		width:'50%',
	  		        		height: 300,
	  		        		items:[
	  		        		       this.filterForm,  this.grid10019_2
	  		        		]
	        	      },{
	  		        		region:'center',
	  		        		width:'5%',
	  		        		height: 300,
	  		        		layout:'table',
	  				    	 align:'center',
	  				    	 layoutConfig: {
	  				    		columns: 1
	  				    	 },
	  				    	 margins:'5',
	  				    	 defaults: {
	  				    		width:'100%',
	  				    		margins:'5'
	  				    	 },
	  		        		items:[{
					    	    	height:90,
					    	    	border:false,
					    	    	frame:false
	  		        			},
	  		        			new Ext.Button({
	  		        				text:'>>',
	  		        				minWidth:40,
	  		        				handler:function(){
	  		        					var p = this.findParentByType("select_ryxx_panel");
	  		        					var store = p.grid10019.store;
	  		        					store.removeAll();
	  		        					
	  		        					var formstore = p.grid10019_2.store;
	  		        					formstore.each(function(r){
	  		        						r.set('_sel', '1');
	  		        						var newr = new store.reader.recordType(r.data, r.data.rynbid);
	  		        						
	  		        						store.add([newr]);
	  		        					});
	  		        					
	  		        					p.grid10019.setTitle("已选择人数&nbsp;:&nbsp;" + p.grid10019.getStore().getCount());
	  		        					
	  		        				}
	  		        			}),
	  		        			{
					    	    	height:5,
					    	    	border:false,
					    	    	frame:false
					    	    },
					    	    new Ext.Button({
	  		        				text:'<<',
	  		        				minWidth:40,
	  		        				handler:function(){
	  		        					var p = this.findParentByType("select_ryxx_panel");
	  		        					var store = p.grid10019.store;
	  		        					store.removeAll();
	  		        					
	  		        					var store2 = p.grid10019_2.store;
	  		        					store2.each(function(r){
	  		        						r.set('_sel' , '0');
	  		        					});
	  		        					
	  		        					p.grid10019.setTitle("已选择人员");
	  		        					
	  		        				}
	  		        			}),
	  		        			{
					    	    	height:10,
					    	    	border:false,
					    	    	frame:false
					    	    },
					    	    new Ext.Button({
	  		        				text:'>',
	  		        				minWidth:40,
	  		        				handler:function(){
        				    			var p = this.findParentByType("select_ryxx_panel");
        				    			var res = p.grid10019_2.getSelectionModel().getSelected();

        				    			if(!res){
        				    				alert("请选择人员!");
        				    			}else{
        				    				var store = p.grid10019.store;
        				    				if(store.getById(res.data.rynbid)==null){
        				    					var r = new store.reader.recordType(res.data, res.data.rynbid);
        				    					res.set('_sel' , '1');
        				    					p.grid10019.setTitle("已选择人数&nbsp;:&nbsp;" + (p.grid10019.getStore().getCount() + 1));
        				    					store.add([r]);
        				    				}
        				    			}
        								
        							}
	  		        			}),
	  		        			{
					    	    	height:5,
					    	    	border:false,
					    	    	frame:false
					    	    },
					    	    new Ext.Button({
									text:'<',
									minWidth:40,
									handler:function(){
        				    			var p = this.findParentByType("select_ryxx_panel");
        				    			var res = p.grid10019.getSelectionModel().getSelected();
        				    			
        				    			if(!res){
        				    				alert("请选择人员!");
        				    			}else{
        				    				
        				    				var r = p.grid10019_2.store.getById(res.data.rynbid);
        				    				if(r){
        				    					r.set('_sel', '0');
        				    				}
        				    				
        				    				p.grid10019.store.remove(res);
        				    				
        				    				if(p.grid10019.getStore().getCount() == 0){
        					    				p.grid10019.setTitle("已选择人员");
        					    			}else{
        					    				p.grid10019.setTitle("已选择人数&nbsp;:&nbsp;" + p.grid10019.store.getCount());
        					    			}
        				    				
        				    			}
        								
        							}
								})
	  		        		]
		 			    },{
		 			    	layout:'border',
	  		        		region:'east',
	  		        		width:'45%',
	  		        		height: 300,
	  		        		items:[
	  		        			this.grid10019
	  		        		]
		 			    }
		        	]
		        }
		];
		
		if(this.qhbgShow){
			this.buttons = [
				{
					text:'全户变更',
					minWidth:75,
					disabled:user.flag>0?true:false,
					handler:function(){
						if(Gnt.ux.QhbgDialog){
							var p = this.findParentByType("select_ryxx_panel");
							
							if(p.grid10019_2.store.getCount()<=0){
								showErr("必须查询并且选择户信息！");
								return;
							}
	    					Gnt.submit(
	    							{
	    								hhnbid: p.data.hhnbid
	    							}, 
	    							"yw/common/queryHxx.json", 
	    							"请稍后...", 
	    							function(jsonData,params){
	    								Ext.Msg.hide();
	    								if(jsonData.list && jsonData.list.length>0){
	    									var hxx = jsonData.list[0];
	    									
	    									var d = new Gnt.ux.QhbgDialog({
	    										ryxxPanel: p,
	    										qhbgCallback: p.qhbgCallback
	    									});
	    									d.show();
	    									if(p.hzywjo&&p.hzywjo.ywlb==16){
	    										d.initHxx(hxx,p.hzywjo);
	    									}else{
	    										d.initHxx(hxx);
	    									}
	    									
	    								}else{
	    									showInfo("户信息没有找到！");
	    								}
	    							}
	    					);
						}else{
							showErr("缺少Gnt.ux.QhbgDialog对象！");
						}
					}
				}];
		}
		
		
		Gnt.ux.SelectRyxxPanel.superclass.initComponent.call(this);
	}
});

Ext.reg('select_ryxx_panel', Gnt.ux.SelectRyxxPanel);