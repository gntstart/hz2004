var selectRynbid = null;
var selectHhid = null;
var selectGlhhid = null;
var selectHglRes = null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10019,20030",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	
	var glhWindow = new Gnt.ux.SelectGlh({
		returnTitleText : '户信息查询',
		callback: function( hcyList, selectHcy, hxx){
			
			//户成员初始化
			var store = glGrid.store;
			store.removeAll();
			//人员选择回调：审批业务，迁入人员
			for(var i=0;i<hcyList.length;i++){
				var data = hcyList[i];
				data._sel = "0";
				var rr = new store.reader.recordType(data, data[store.pkname]);
				store.add([rr]);
			}
			
			glGrid.fireEvent("rowclick",glGrid,0);
			glGrid.getSelectionModel().selectRange(0,0);
			
			Ext.getCmp('glckxxId').enable();
			
			/**
				判断被关联户信息是否有值
				有:查询户关联信息
			 */
			if(bglGrid && bglGrid.store && bglGrid.store.getCount() > 0){
				queryGlgx(selectHhid,selectGlhhid);
			}
			
		},
		rowclickBack : function(data){
			selectHhid = data.hhid;
		}
	});
	
	
	var glGrid = new Gnt.ux.SjpzGrid({
		title: '查询关联的户信息',
		pkname: 'rynbid',
		pzlb: '10019',
		region:'north',
//		height:300,
		url: 'yw/common/queryRyxx.json',
		showPaging:true,
		listeners:{
			rowclick:function(g, rowIndex, e){
				Ext.getCmp('glckxxId').enable();
				
//				var data = g.store.getAt(rowIndex).data;
				
			},
			rowdblclick:function(g, rowIndex, e){
				
			}
		},
		buttons:[
			new Ext.Button({
				id:'glckxxId',
				text:'常口信息',
				minWidth:100,
				width:150,
				disabled:true,
				handler:function(){
					//Gnt.toRyxx(glGrid.getSelectionModel().getSelected().data.ryid,glGrid.getSelectionModel().getSelected().data.rynbid,null,null);
					
					czr={
							ryid:glGrid.getSelectionModel().getSelected().data.ryid,
							rynbid:glGrid.getSelectionModel().getSelected().data.rynbid
						}
					Gnt.toRyxx(czr);
				}
			}),new Ext.Button({
				id:'glhcxId',
				text:'关联户查询',
				minWidth:100,
				width:150,
				handler:function(){
					
					Ext.getCmp('glckxxId').disable();
					
					glhWindow.show();
				}
			})
		]
	});
	
	var bglhWindow = new Gnt.ux.SelectGlh({
		returnTitleText : '户信息查询',
		callback: function( hcyList, selectHcy, hxx){
			
			//户成员初始化
			var store = bglGrid.store;
			store.removeAll();
			//人员选择回调：审批业务，迁入人员
			for(var i=0;i<hcyList.length;i++){
				var data = hcyList[i];
				data._sel = "0";
				var rr = new store.reader.recordType(data, data[store.pkname]);
				store.add([rr]);
			}
			
			bglGrid.fireEvent("rowclick",bglGrid,0);
			bglGrid.getSelectionModel().selectRange(0,0);
			
			Ext.getCmp('bglckxxId').enable();
			
			/**
				判断关联户信息是否有值
				有:查询户关联信息
			 */
			if(glGrid && glGrid.store && glGrid.store.getCount() > 0){
				queryGlgx(selectHhid,selectGlhhid);
			}
			
		},
		rowclickBack : function(data){
			selectGlhhid = data.hhid;
		}
	});
	
	var bglGrid = new Gnt.ux.SjpzGrid({
		title: '查询被关联的户信息',
		pkname: 'rynbid',
		pzlb: '10019',
		region:'center',
//		height:300,
		url: 'yw/common/queryRyxx.json',
		showPaging:true,
		listeners:{
			rowclick:function(g, rowIndex, e){
				Ext.getCmp('bglckxxId').enable();
			},
			rowdblclick:function(g, rowIndex, e){
				
			}
		},
		buttons:[
			new Ext.Button({
				id:'bglckxxId',
				text:'常口信息',
				minWidth:100,
				width:150,
				disabled:true,
				handler:function(){
					czr={
							ryid:bglGrid.getSelectionModel().getSelected().data.ryid,
							rynbid:bglGrid.getSelectionModel().getSelected().data.rynbid,
							hhnbid:null
						}
					Gnt.toRyxx(czr);
				}
			}),new Ext.Button({
				id:'bglhcxId',
				text:'被关联户查询',
				minWidth:100,
				width:150,
				handler:function(){
					
					Ext.getCmp('bglckxxId').disable();
					
					bglhWindow.show();
				}
			})
		]
	});
	
	var glgxWindow = new Gnt.ux.SelectHglgx({
		callback: function(glgx){
			var subdata = {
				hhid: selectHhid,
				glhhid: selectGlhhid,
				hglgx: glgx
			};
			log_code = "F2010";
			Gnt.submit(
				subdata, 
				"yw/hjyw/hglgxdj/processHgxzjyw", 
				"正在处理户关联关系信息，请稍后...", 
				function(jsonData,params){
					queryGlgx(selectHhid,selectGlhhid);
				}
			);
		}
	});
	
	var hglGrid = new Gnt.ux.SjpzGrid({
		title: '户关联信息',
		pkname: 'glid',
		pzlb: '20030',
		region:'south',
		url: 'cx/hjjbxx/ckxx/getXxxx.json',
		showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){
				selectHglRes = g.store.getAt(rowIndex);
			},
			rowdblclick:function(g, rowIndex, e){
				selectHglRes = g.store.getAt(rowIndex);
			}
		},
		buttons:[
			new Ext.Button({
				id:'xzglId',
				text:'新增关联',
				minWidth:100,
				width:150,
				disabled:true,
				handler:function(){
					glgxWindow.show();
				}
			}),new Ext.Button({
				id:'scglId',
				text:'删除关联',
				minWidth:100,
				width:150,
				disabled:true,
				handler:function(){
					if(!selectHglRes){
						alert("请选中要删除的户关联数据！") ;
						return;
					}else{
						if(window.confirm("是否确定删除此条户关联信息?")){
							var subdata = {
									glid: hglGrid.getSelectionModel().getSelected().data.glid,
									hhid: selectHhid,
									glhhid: selectGlhhid
							};
							log_code = "F2011";
							Gnt.submit(
									subdata, 
									"yw/hjyw/hglgxdj/processHgxscyw", 
									"正在删除户关联关系信息，请稍后...", 
									function(jsonData,params){
										queryGlgx(selectHhid,selectGlhhid);
									}
							);
						}
					}
					
					
				}
			}),new Ext.Button({
				id:'gbId',
				text:'关闭',
				minWidth:100,
				width:150,
				handler:function(){
					if(parent && parent.closeWorkSpace){
						parent.closeWorkSpace();
					}else{
						window.close();
					}
				}
			})
		]
	});
	
	var p1 = new Ext.Panel({
		layout:'border',
		border:false,
		frame: false,
		items:[
			glGrid,
			bglGrid,
			hglGrid
		]
	});
	
	function queryGlgx(hhid,glhhid){
		var store = hglGrid.store;
		store.removeAll();
		
		store.baseParams = {
			hhid:hhid,
			glhhid:glhhid,
			config_key:'queryPoHJXX_HGLGXB1'
		};	
		
		store.load();
		/*
		store.on('load',function(s,records){
			s.each(function(r){
				store.add([r]);
	    	});
		});
		*/
		
		Ext.getCmp('xzglId').enable();
		Ext.getCmp('scglId').enable();
		
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
    	    //bodyStyle: 'padding:2px',
        	items:[p1]
        }
    });
	
	var hxxGrid = new Gnt.ux.SjpzGrid({
		pkname: 'hhnbid',
		//region:'north',
		pzlb: '20020',
		title: '',
		url: 'cx/hjjbxx/hxxcx/getHxx',
		showPaging:false
	});
	var hxxGrid1 = new Gnt.ux.SjpzGrid({
		pkname: 'hhnbid',
		//region:'north',
		pzlb: '20020',
		title: '',
		url: 'cx/hjjbxx/hxxcx/getHxx',
		showPaging:false
	});	
	/**
		住址变动业务办理完成点击关联户按钮
		自动查询关联户成员信息
	 */
	if(getQueryParam("glhhnbid")&&getQueryParam("bglhhnbid")){
		selectGlhhid =getQueryParam("bglhhnbid");
		selectHhid =getQueryParam("glhhnbid");
		var store = glGrid.store;
		store.baseParams = {
				pzlb: store.pzlb,
				hhnbid: getQueryParam("glhhnbid")
		};
		store.load({params:{start:0, limit:40}});
		
		store.on('load',function(s,records){
			if(s.getCount() > 0){
				
				glGrid.fireEvent("rowclick",glGrid,0);
				glGrid.getSelectionModel().selectRange(0,0);
				
				Ext.getCmp('glckxxId').enable();
			}else{
//				showInfo("未查到有关信息！");
				return false;
			}
			//p2.doLayout();
		});
		var store1 = bglGrid.store;
		store1.baseParams = {
			pzlb: store1.pzlb,
			hhnbid: getQueryParam("bglhhnbid")
		};
		store1.load({params:{start:0, limit:40}});
		
		store1.on('load',function(s,records){
			if(store1.getCount() > 0){
				
				bglGrid.fireEvent("rowclick",bglGrid,0);
				bglGrid.getSelectionModel().selectRange(0,0);
				
				Ext.getCmp('glckxxId').enable();
			}else{
//				showInfo("未查到有关信息！");
				return false;
			}
			//p2.doLayout();
		});
		
		var store2 = hxxGrid.store;
		store2.baseParams = {
			hhnbid: getQueryParam("glhhnbid"),
			queryXx:"queryhxxxx"
		};
		store2.load({params:{start:0, limit:40}});
		store2.on('load',function(s,records){
			if(s.getCount() > 0){
				selectHhid = records[0].data.hhid;
			}
		});
		var store3 = hxxGrid1.store;
		store3.baseParams = {
			hhnbid: getQueryParam("bglhhnbid"),
			queryXx:"queryhxxxx"
		};
		store3.load({params:{start:0, limit:40}});
		store3.on('load',function(s,records){
			if(s.getCount() > 0){
				selectGlhhid = records[0].data.hhid;
			}
		});
		Ext.getCmp('xzglId').enable();
		Ext.getCmp('scglId').enable();
	}
	
//	if(getQueryParam("bglhhnbid")){
//		
//		var store = bglGrid.store;
//		store.baseParams = {
//			pzlb: store.pzlb,
//			hhnbid: getQueryParam("bglhhnbid")
//		};
//		store.load({params:{start:0, limit:40}});
//		
//		store.on('load',function(s,records){
//			if(store.getCount() > 0){
//				
//				bglGrid.fireEvent("rowclick",bglGrid,0);
//				bglGrid.getSelectionModel().selectRange(0,0);
//				
//				Ext.getCmp('glckxxId').enable();
//			}else{
////				showInfo("未查到有关信息！");
//				return false;
//			}
//			
//			
//			//p2.doLayout();
//		});
//		
//		var store1 = hxxGrid.store;
//		store1.baseParams = {
//			hhnbid: getQueryParam("bglhhnbid"),
//			queryXx:"queryhxxxx"
//		};
//		store1.load({params:{start:0, limit:40}});
//		
//		store1.on('load',function(s,records){
//			if(s.getCount() > 0){
//				selectGlhhid = records[0].data.hhid;
//				
//			}
//		});
//		
//	}
	
});