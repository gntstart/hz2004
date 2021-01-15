var selectedSelRes = null;
var blSelRes = null;
Gnt.ux.SelectNewHz = Ext.extend(Ext.Window, {
	title:'错误!!!',
	closeAction:'hide',
	modal:true,
	width:700,
	height:600,
	margins:'5',
	layout:'border',
	pageSize:30,
	show:function(){
		Gnt.ux.SelectNewHz.superclass.show.call(this);
	},
	resetData:function(){
		
	},
	initComponent : function(){
		
		if(!Gnt.loadSjpzb("10033,10019,20001",function(){}))
			return;
		
		var url = null;
		var pzlb = null;
		if(this.splx){
			if(this.splx == "hjsc"){
				url = "yw/spgl/spcl/queryHjscspxx.json";
				pzlb = "10033";
			}else if(this.splx == "hjbl"){
				url = "yw/spgl/spcl/queryHjblspxx.json";
				pzlb = "10032";
			}
		}
		
		
		this.grid10033 = new Gnt.ux.SjpzGrid({
			title: '变动人员信息',
			region : 'north',
//			url: "yw/spgl/spcl/queryHjscspxx.json",
//			pzlb: '10033',
			url: url,
			pzlb: pzlb,
			pkname: 'spywid',
			loadCallback:function(res, store, bind_grid){
				
			},
			listeners:{
	        	rowclick:function(g, rowIndex, e){
	        		blSelRes = g.store.getAt(rowIndex);
	        		var win = this.findParentByType("hz_window");
	        		win.xhzForm.getForm().reset();
	        		win.xhzForm.getForm().loadRecord(blSelRes);
	        		
	        	},
				rowdblclick:function(g, rowIndex, e){
					
				}
			}
		});
		
		this.grid10019 = new Gnt.ux.SjpzGrid({
			title: '变动户成员信息',
			pzlb: '10019',
			pkname: 'rynbid',
			region : 'center',
			height:120,
			url: 'yw/common/queryRyxx.json',
			pageSize:40,
			loadCallback:function(res, store, bind_grid){
				
			},
	        listeners:{
	        	rowclick:function(g, rowIndex, e){
	        		selectedSelRes = g.store.getAt(rowIndex);
	        		var win = this.findParentByType("hz_window");
	        		
	        		win.xhzForm.getForm().reset();
	        		win.xhzForm.getForm().loadRecord(selectedSelRes);
	        		
	        	},
				rowdblclick:function(g, rowIndex, e){
					
				}
			}
		});
		
		this.xhzForm = new Ext.form.FormPanel({
			margins:'5px 5px 5px 5px',
			region : 'south',
			height:75,
			layout : 'column',
			title : '',
			frame: false,
			border:  false,
			autoScroll : true,
	    	buttonAlign:'right',
	    	labelAlign:'right',
			defaults : {
				frame:false,
				border:false
			},
			items:[{
				layout : 'column',
				title : '',
				margins:'0',
				bodyStyle : 'padding:5px 0px 0px 0px',
				defaults : {
					frame:false,
					border:false,
					labelWidth : 80,
					bodyStyle : 'padding:0px 5px 0px 5px'
				},
				items:[{
    	       		xtype:'hidden',
    	       		name:'rynbid'
    	       	},{
    	       		xtype:'hidden',
    	       		name:'hhnbid'
    	       	},{
	                columnWidth:.5,
		           	layout: 'form',
	    	       	items: [{
	    	       		fieldLabel:'姓名',
						xtype:'textfield',
						anchor:'100%',
						disabled:true,
						name:'xm'
					}]
				},{
	                columnWidth:.5,
		           	layout: 'form',
	    	       	items: [{
	    	       		fieldLabel:'婚姻状况',
	    	       		xtype : 'dict_combox',
	    	       		dict:"VisionType=CS_HYZK",
						anchor:'100%',
						hiddenName:'hyzk'
					}]
				},{
	                columnWidth:.5,
		           	layout: 'form',
	    	       	items: [{
	    	       		fieldLabel:'与户主关系',
	    	       		xtype : 'dict_combox',
	    	       		dict:"VisionType=CS_JTGX&ignore=false",
						anchor:'100%',
						hiddenName:'yhzgx'
					}]
				},{
	                columnWidth:.3
				},{
	                columnWidth:.1,
	    	       	items: [new Ext.Button({
						text:'确定',
						minWidth:60,
						handler:function(){
							var win = this.findParentByType("hz_window");
							if(blSelRes&&!blSelRes.data.rynbid){//新补录业务人员没有rynbid 
								var subdata = {
										xhzxx: win.xhzForm.getForm().getValues()
								};
								if(win.callback){
									try{
										win.callback(subdata.xhzxx,true);
										win.hide();
									}catch(e){
										showInfo(e);
									}
								}
							}else{
								if(selectedSelRes||blSelRes){
									if(win.xhzForm.form.findField("rynbid").getValue() == win.selectHzId){
										showInfo("请选择其它人员为户主！");
										return ;
									}else{
										
										var subdata = {
											xhzxx: win.xhzForm.getForm().getValues()
										};
										
										subdata.xhzxx = Ext.encode(subdata.xhzxx);
										
										Gnt.submit(
											subdata,
											"yw/spgl/spcl/setNewHz",
											"请稍后...",
											function(jsonData,params){
												if(win.callback){
													try{
														win.callback(jsonData);
														win.hide();
													}catch(e){
														showInfo(e);
													}
												}
											}
										);
										
									}
								}else{
									showInfo("请选择变动户成员！");
									return ;
								}
							}
							
							
						}
					})]
				},{
	                columnWidth:.1,
	    	       	items: [new Ext.Button({
						text:'取消',
						minWidth:60,
						handler:function(){
							this.findParentByType("hz_window").hide();
						}
					})]
				},{
					columnWidth:1,
					border:true,
					html: this.msg
				}]
			}]
		});
		
		this.items = [this.grid10033, this.grid10019, this.xhzForm];
		
		
		if(this.selectHzId){
			var grid = this.grid10033;
			grid.store.removeAll();
			
			grid.store.baseParams = {
				rynbid:this.selectHzId
			};
			
			grid.store.load({params:{start:0, limit:grid.store.pageSize}});
			
		}else if(this.selectHh && this.selectXm && this.selectCsrq){
			
			var grid = this.grid10033;
			grid.store.removeAll();
			
			grid.store.baseParams = {
				sshhid:this.hhnbid,
				xm:this.selectXm,
				csrq:this.selectCsrq
			};
			
			grid.store.load({params:{start:0, limit:grid.store.pageSize}});
			grid.store.on("load",function(store) {  
				var length = store.data.length
				if(length > 0){
					curIndex = 0;
					grid.fireEvent("rowclick",grid,curIndex);
					grid.getSelectionModel().selectRange(curIndex,curIndex);
				}else{
				}
			},grid); 
			
		}else{
			
		}
		
		if(this.hhnbid&&this.splx == "hjsc"){
			var gridHcy = this.grid10019;
			var store = gridHcy.store;
			store.baseParams = {
				pzlb: store.pzlb,
				hhnbid: this.hhnbid
			};
			store.load({params:{start:0, limit:40}});
		}else if(this.hhnbid&&this.splx == "hjbl"){
			var gridHcy = this.grid10019;
			var store = gridHcy.store;
			store.baseParams = {
				pzlb: store.pzlb,
				hhid: this.hhnbid
			};
			store.load({params:{start:0, limit:40}});
		}
		
		
		Gnt.ux.SelectNewHz.superclass.initComponent.call(this);
	}
});
Ext.reg('hz_window', Gnt.ux.SelectNewHz);
