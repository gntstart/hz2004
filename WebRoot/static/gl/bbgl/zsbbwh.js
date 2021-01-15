var selectedSelRes=null;
var zsbbid=null;
var zsbblb=null;
var zsbbmbid=null;
var zsbbmb=null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	var bblbStore = new Ext.data.JsonStore({
		proxy: new Ext.data.HttpProxy({
			url : 'gl/zsbb/queryzsbblb',
			method: "POST",
			disableCaching: true
	    }),
		root : 'list',
		id : 'csid',
		totalProperty : 'totalCount',
		fields : ["csid", "cslb", "dm", "mc"
		],
		listeners : {			
			beforeload : function(store, options) {
			},
			load : function() {
				
			},
			loadexception : function(mystore, options, response, error) {
				if (error) {
					var json = Ext.decode(response.responseText);
					if (json.messages)
						Ext.Msg.alert("提示", json.messages[0]);
				} else {
					Ext.Msg.alert("提示", response.responseText);
				}
			}
		}
	});
	
	
	
	var bblbxxStore = new Ext.data.JsonStore({
		proxy: new Ext.data.HttpProxy({
			url : 'gl/zsbb/queryzsbblbxx',
			method: "POST",
			disableCaching: true
	    }),
		root : 'list',
		totalProperty : 'totalCount',
		fields : ["createName", "createDate", "updateName", "updateDate",
		"bbName","ywbblb","ywbbid","ywbbmb"],
		listeners : {			
			beforeload : function(store, options) {
			},
			load : function() {
				
			},
			loadexception : function(mystore, options, response, error) {
				if (error) {
					var json = Ext.decode(response.responseText);
					if (json.messages)
						Ext.Msg.alert("提示", json.messages[0]);
				} else {
					Ext.Msg.alert("提示", response.responseText);
				}
			}
		}
	});
	
	
	var zsbbStore = new Ext.data.JsonStore({
		proxy: new Ext.data.HttpProxy({
			url : 'gl/zsbb/queryzsbbxx',
			method: "POST",
			disableCaching: true
	    }),
		root : 'list',
		totalProperty : 'totalCount',
		fields : ["zsbbid", "bbmc", "jlsj", "xgsj",
		"scryhxm","xgryhxm","zsbbmbid"],
		listeners : {			
			beforeload : function(store, options) {
			},
			load : function() {
				
			},
			loadexception : function(mystore, options, response, error) {
				if (error) {
					var json = Ext.decode(response.responseText);
					if (json.messages)
						Ext.Msg.alert("提示", json.messages[0]);
				} else {
					Ext.Msg.alert("提示", response.responseText);
				}
			}
		}
	});
	
	var colJFModel = new Ext.grid.ColumnModel([{
		header : "报表类别",
		width : 80,
		sortable : true,
		locked : false,
		dataIndex : 'dm'
	}, {
		header : "类别名称",
		width : 200,
		sortable : true,
		dataIndex : 'mc'
	}]);
	
	
	var colxxModel = new Ext.grid.ColumnModel([{
		header : "制式报表模板名称",
		width : 130,
		sortable : true,
		locked : false,
		dataIndex : 'bbName'
	}, {
		header : "建立时间",
		width : 100,
		sortable : true,
		dataIndex : 'createDate'
	},{
		header : "建立人姓名",
		width : 100,
		sortable : true,
		dataIndex : 'createName'
	},{
		header : "修改时间",
		width : 100,
		sortable : true,
		dataIndex : 'updateDate'
	},{
		header : "修改人姓名",
		width : 100,
		sortable : true,
		dataIndex : 'updateName'
	}]);
	
	var colzsbbModel = new Ext.grid.ColumnModel([{
		header : "制式报表名称",
		width : 100,
		sortable : true,
		locked : false,
		dataIndex : 'bbmc'
	}, {
		header : "创建时间",
		width : 100,
		sortable : true,
		dataIndex : 'jlsj'
	},{
		header : "创建人姓名",
		width : 100,
		sortable : true,
		dataIndex : 'scryhxm'
	},{
		header : "修改时间",
		width : 100,
		sortable : true,
		dataIndex : 'xgsj'
	},{
		header : "修改人姓名",
		width : 100,
		sortable : true,
		dataIndex : 'xgryhxm'
	}]);
	
	var zsbbxxGrid = new Ext.grid.GridPanel({
        store: zsbbStore,
        region: 'center',
        view:new Ext.grid.GridView({
				enableRowBody:true
		}),
		stripeRows: true,
        cm: colzsbbModel,
		loadMask: {msg:'正在加载数据，请稍侯……'},
		//bodyStyle:'width:50%',
        border:true,
        frame:false,
        //anchor:'100% 100%',
	    //margins: '0 0 0 0',
		//cmargins: '0 0 0 0',        
		iconCls:'icon-grid',
        title:'',
        listeners:{
        	rowclick:function(g, rowIndex, e){
        		selectedSelRes = g.store.getAt(rowIndex);
        	},
			rowdblclick:function(g, rowIndex, e){
				selectedSelRes = g.store.getAt(rowIndex);
			}
		},
        bbar: new Ext.PagingToolbar({
				pageSize: 20,
				store: zsbbStore,
				displayInfo: true
		})
    });
	
	
	
	var zsbbmbxxGrid = new Ext.grid.GridPanel({
        store: bblbxxStore,
        region: 'center',
        view:new Ext.grid.GridView({
				enableRowBody:true
		}),
		stripeRows: true,
        cm: colxxModel,
		loadMask: {msg:'正在加载数据，请稍侯……'},
		//bodyStyle:'width:50%',
        border:true,
        frame:false,
        //anchor:'100% 100%',
	    //margins: '0 0 0 0',
		//cmargins: '0 0 0 0',        
		iconCls:'icon-grid',
        title:'',
        listeners:{
        	rowclick:function(g, rowIndex, e){
        		selectedSelRes = g.store.getAt(rowIndex);
        		zsbbmbid=selectedSelRes.data.ywbbid;
        	},
			rowdblclick:function(g, rowIndex, e){
				selectedSelRes = g.store.getAt(rowIndex);
				zsbbmbid=selectedSelRes.data.ywbbid;
			}
		},
        bbar: new Ext.PagingToolbar({
				pageSize: 20,
				store: bblbxxStore,
				displayInfo: true
		})
    });
	
	var grid = new Ext.grid.GridPanel({
		store : bblbStore,
		region : 'center',
		stripeRows : true,
		cm : colJFModel,
		loadMask : {
			msg : '正在加载数据，请稍侯……'
		},
		//bodyStyle : 'width:100%',
		border : true,
		//anchor : '100% 100%',
		//margins : '0 0 0 0',
		//cmargins : '0 0 0 0',
		frame : false,
		iconCls : 'icon-grid',
		title : '',
		   listeners:{
	        	rowclick:function(g, rowIndex, e){	        		
	        		selectedSelRes = g.store.getAt(rowIndex);
	        		zsbblb=selectedSelRes.data.dm;
	        		var store = zsbbmbxxGrid.store;	        		
	        		store.baseParams = {
	        			zsbblb:zsbblb,
	        			start:0,
	        			limit:50
	        		};
	        		store.load();
	        		store.on("load",function(store) {  
	        			if(store.data.length > 0){
	        				curIndex = 0;
	        				zsbbmbxxGrid.fireEvent("rowclick",zsbbmbxxGrid,curIndex);
	        				zsbbmbxxGrid.getSelectionModel().selectRange(curIndex,curIndex);
	        				
	        			}
	        		},zsbbmbxxGrid);
	        		
	        	},
				rowdblclick:function(g, rowIndex, e){
					selectedSelRes = g.store.getAt(rowIndex);
	        		var store = zsbbmbxxGrid.store;
	        		store.baseParams = {
	        		    zsbblb:selectedSelRes.data.dm,
	        			start:0,
	        			limit:50
	        		};
	        		store.load();
	        		store.on("load",function(store) {  
	        			if(store.data.length > 0){
	        				curIndex = 0;
	        				zsbbmbxxGrid.fireEvent("rowclick",zsbbmbxxGrid,curIndex);
	        				zsbbmbxxGrid.getSelectionModel().selectRange(curIndex,curIndex);
	        				
	        			}
	        		},zsbbmbxxGrid);
				}
			}
	});
	
	
	
	var zsbbmbwhadd_win=new Gnt.ux.Selectywbbwhadd({//新增弹窗
		callback: function(zsbbmc,zsbbmbcontent){
			//提交数据
			Gnt.submit(
					{bbmc:zsbbmc,bblb:zsbblb,bbmb:zsbbmbcontent}, 
					"gl/zsbb/addzsbbmbxx",
					"正在新增制式报表模板，请稍后...", 
					function(jsonData,params){
						showInfo("制式报表模板信息新增成功！");
						zsbbmbxxGrid.store.reload(); 
					}
			);
		}
	});
	
	
	var zsbbwhadd_win=new Gnt.ux.Selectzsbbwhadd({//新增弹窗
		callback: function(zsbbmc){
			alert(zsbbmbid);
			//提交数据
			Gnt.submit(
					{bbmc:zsbbmc,bblb:zsbblb,zsbbmbid:zsbbmbid}, 
					"gl/zsbb/addzsbbxx",
					"正在新增制式报表，请稍后...", 
					function(jsonData,params){
						showInfo("制式报表信息新增成功！");
						zsbbxxGrid.store.reload(); 
					}
			);
		}
	});
	
	var zsbbmbwhup_win=new Gnt.ux.Selectywbbwhup({//修改弹窗
		callback: function(zsbbmc,zsbbmbContent){
			//提交数据
			Gnt.submit(
					{bbmc:zsbbmc,bblb:zsbblb,zsbbmbid:zsbbid,bbmb:zsbbmbContent}, 
					"gl/zsbb/updatezsbbmbxx", 
					"正在修改制式报表模板，请稍后...", 
					function(jsonData,params){
						showInfo("制式报表模板信息修改成功！");
						zsbbmbxxGrid.store.reload(); 
					}
			);
		}
	});
	
	var zsbbwhup_win=new Gnt.ux.Selectzsbbwhup({//修改弹窗
		callback: function(zsbbmc){
			//提交数据
			Gnt.submit(
					{bbmc:zsbbmc,bblb:zsbblb,zsbbid:zsbbid}, 
					"gl/zsbb/updatezsbbxx", 
					"正在修改制式报表，请稍后...", 
					function(jsonData,params){
						showInfo("制式报表信息修改成功！");
						zsbbxxGrid.store.reload(); 
					}
			);
		}
	});
	
	  var tabs = new Ext.TabPanel({   		  
          region: 'center',
          margins: '3 3 3 0',   
          activeTab: 0,   
          defaults: {   
             autoScroll: false   
          },  
          items: [{   
              title: '制式报表模板信息',   
              region: 'center',
	            collapsible: false,
	            layout:'border',
				border:false,
	            frame:false,
	            width:300,
	            anchor:'100% 100%',
	    	    margins: '0 0 0 0',
	    		cmargins: '0 0 0 0',
	            items:[zsbbmbxxGrid],
	        	buttons:[
new Ext.Button({
	id:'add',
	text:'增加',
	minWidth:60,	
	handler:function(){
		var cmp=Ext.getCmp("bblb");
		cmp.setValue(zsbblb);
		cmp.setDisabled(true);	
		//ywbbwhadd_win.setSelRes();  
		zsbbmbwhadd_win.title="制式报表模板新增";
		zsbbmbwhadd_win.show();
		
	}
}),
new Ext.Button({
	id:'update',
	text:'修改',
	minWidth:60,	
	handler:function(){
		if(zsbbmbxxGrid.getStore().getCount() > 0){
			var bblbmc=grid.getSelectionModel().getSelections()[0].data.mc;
			 zsbblb=grid.getSelectionModel().getSelections()[0].data.dm;
			if(zsbbmbxxGrid.getSelectionModel().getCount() >=1){
				var bbName=zsbbmbxxGrid.getSelectionModel().getSelections()[0].data.bbName;
				zsbbmb=zsbbmbxxGrid.getSelectionModel().getSelections()[0].data.ywbbmb;
					zsbbid = zsbbmbxxGrid.getSelectionModel().getSelections()[0].data.ywbbid;
					//alert(ywbbid+"--"+bbName+"--"+ywbblb);	
					var cmp=Ext.getCmp("bblbup");
					cmp.setValue(zsbblb);
					var cmp2=Ext.getCmp("bbmcup");
					cmp2.setValue(bbName);
					cmp.setDisabled(true);
					zsbbmbwhup_win.title="制式报表模板修改";
					zsbbmbwhup_win.show();
					zsbbmbwhup_win.setSelRes(zsbblb,zsbbmb,bbName);
				
			}else{
				alert("请选择需要修改的报表!");
			}
		}else{
			alert("请先执行查询!");
		}
		
	}
}),
new Ext.Button({
	id:'del',
	text:'删除',
	minWidth:60,
	handler:function(){
		if(zsbbmbxxGrid.getStore().getCount() > 0){
			var bblbmc=grid.getSelectionModel().getSelections()[0].data.mc;
			 zsbblb=grid.getSelectionModel().getSelections()[0].data.dm;
			if(zsbbmbxxGrid.getSelectionModel().getCount() >=1){
				var bbName=zsbbmbxxGrid.getSelectionModel().getSelections()[0].data.bbName;
				if(confirm("确定要删除["+bblbmc+"]["+bbName+"]?")){
					zsbbid = zsbbmbxxGrid.getSelectionModel().getSelections()[0].data.ywbbid;
					//alert(ywbbid+"--"+bbName+"--"+ywbblb);					
					Gnt.submit(
							{
								zsbbmbid:zsbbid,bblb:zsbblb}, 
								"gl/zsbb/delzsbbmbxx", 
								"正在删除报表模板，请稍后...", 
								function(jsonData,params){
									showInfo("制式报表模板删除成功！");
									zsbbmbxxGrid.store.reload(); 
								}
							);
				}
			}else{
				alert("请选择需要删除的报表模板!");
			}
		}else{
			alert("请先执行查询!");
		}
	}
}),new Ext.Button({
	id:'mbimport',
	text:'模板导入',
	minWidth:60,
	handler:function(){
		dellCell.SaveFile();
	}
}),new Ext.Button({
	id:'mbexport',
	text:'模板导出',
	minWidth:60,
	handler:function(){
		var bbName=zsbbmbxxGrid.getSelectionModel().getSelections()[0].data.bbName;
		zsbbmb=zsbbmbxxGrid.getSelectionModel().getSelections()[0].data.ywbbmb;		
		dellCell.ReadFromBase64Str(zsbbmb);
		dellCell.SaveFile();
		
	}
}),new Ext.Button({
	id:'bbexport',
	text:'报表生成',
	minWidth:60,
	handler:function(){
		dellCell.ExportExcelDlg();
	}
})]   
          }, {   
              title: '制式报表信息',   
	          collapsible: false,
	          layout:'border',
		      border:false,
	          frame:false,
              items:[{  
            	region:'north',
            	frame:false,
  				border:false,
  				layout : 'column',
  				height:60,
  				columnWidth:1,
  				defaults : {
  					frame:false,
  					border:false,
  					columnWidth:.4,
  					bodyStyle : 'padding:0 0 0 0'
  				},
  				items: [
					{
						layout: 'form',
						columnWidth:.4,
						width : 50,
						items:[{
							fieldLabel:'起始时间',
							id:'kssj',
							anchor:'80%',
							disabled:false,
							xtype : 'datefield',
							format: "Ymd"
						}]
					},
					{
						layout: 'form',
						columnWidth:.4,
						width : 50,
						
						items:[{
							fieldLabel:'结束时间',
							id:'jssj',
							anchor:'80%',
							disabled:false,
							xtype : 'datefield',
							format: "Ymd"
						}]
					},{
						layout: 'form',
						items:[
							new Ext.Button({
								text:'查询',							
								width:80,
								handler:function(){
									var store = zsbbxxGrid.store;					
									store.baseParams = {
											kssj:Ext.getCmp('kssj').value,
											jssj:Ext.getCmp('jssj').value,											
											start:0,
											limit:20
									};
									store.load();	
								}
							})
							]
					}
  				]
    		},zsbbxxGrid,{
	            title: '',
	            region: 'south',
	            height: 40,
                
	        	buttons:[
new Ext.Button({
	id:'zsbbadd',
	text:'新增',
	minWidth:60,
	handler:function(){		
		if(zsbbmbxxGrid.getSelectionModel().getCount()>=1){
			var cmp=Ext.getCmp("bblb_zs");
			cmp.setValue(zsbblb);
			cmp.setDisabled(true);
			zsbbwhadd_win.show();
		}else{
			alert("请先选择制式报表模板");
		}
		
	}
}),
   new Ext.Button({
			id:'zsbbupdate',
			text:'修改',
			minWidth:60,
			handler:function(){
				if(zsbbxxGrid.getStore().getCount() > 0){
					var bblbmc=grid.getSelectionModel().getSelections()[0].data.mc;
					 zsbblb=grid.getSelectionModel().getSelections()[0].data.dm;
					if(zsbbxxGrid.getSelectionModel().getCount() >=1){
						var bbName=zsbbxxGrid.getSelectionModel().getSelections()[0].data.bbmc;
						
							zsbbid = zsbbxxGrid.getSelectionModel().getSelections()[0].data.zsbbid;
							//alert(ywbbid+"--"+bbName+"--"+ywbblb);					
							var cmp=Ext.getCmp("bblbup_zs");
							cmp.setValue(zsbblb);
							var cmp2=Ext.getCmp("bbmcup_zs");
							cmp2.setValue(bbName);
							cmp.setDisabled(true);
							//zsbbwhup_win.title="制式报表修改";
							zsbbwhup_win.show();
						
					}else{
						alert("请选择需要修改的报表!");
					}
				}else{
					alert("请先执行查询!");
				}
			}
		}),
		 new Ext.Button({
				id:'zsbbdel',
				text:'删除',
				minWidth:60,
				handler:function(){
					if(zsbbxxGrid.getStore().getCount() > 0){
						var bblbmc=grid.getSelectionModel().getSelections()[0].data.mc;
						 zsbblb=grid.getSelectionModel().getSelections()[0].data.dm;
						if(zsbbxxGrid.getSelectionModel().getCount() >=1){
							var bbName=zsbbxxGrid.getSelectionModel().getSelections()[0].data.bbmc;
							if(confirm("确定要删除["+bblbmc+"]["+bbName+"]?")){
								zsbbid = zsbbxxGrid.getSelectionModel().getSelections()[0].data.zsbbid;
								zsbbmbid=zsbbxxGrid.getSelectionModel().getSelections()[0].data.zsbbmbid;
								//alert(ywbbid+"--"+bbName+"--"+ywbblb);					
								Gnt.submit(
										{
											zsbbmbid:zsbbmbid,zsbbid:zsbbid}, 
											"gl/zsbb/delzsbbxx", 
											"正在删除报表，请稍后...", 
											function(jsonData,params){
												showInfo("制式报表删除成功！");
												zsbbxxGrid.store.reload(); 
											}
										);
							}
						}else{
							alert("请选择需要删除的报表!");
						}
					}else{
						alert("请先执行查询!");
					}
				}
			}),
			new Ext.Button({
				id:'selzsbb',
				text:'查看报表',
				minWidth:60,
				handler:function(){
					
				}
			})]
	        }]
          }]   
     });
	  
	  
	 	
	 var borderPanel = new Ext.Panel({
	        layout: 'border',
	        tltle: '',
	        //width: 1000,
	       // height: 800,
	        defaults: {
	            collapsible: true, // 支持该区域的展开和折叠
	            split: false, // 支持用户拖放改变该区域的大小
	            bodyStyle: 'padding:15px'
	        },
	        items: [{
	            title: '',
	            region: 'south',
	            height: 40,
	            //minSize: 75,
	            //maxSize: 250,
	        	buttons:[
new Ext.Button({
	id:'exportAll',
	text:'模板全部导出',
	minWidth:60,
	handler:function(){
		
	}
}),
   new Ext.Button({
			id:'close',
			text:'关闭',
			minWidth:60,
			handler:function(){
				window.parent.closeWorkSpace();
			}
		})]
	        }, {
	            title: '',
	            region: 'center',
	            collapsible: false,
	            layout:'border',
				border:false,
	            frame:false,	      
	            items:[tabs]	            
	        }, {
	            title: '',
	            region: 'west',
				layout:'border',
				border:false,
	            frame:false,
	            width:300,
	            anchor:'100% 100%',
	    	    margins: '0 0 0 0',
	    		cmargins: '0 0 0 0', 
	            split: true,
	            items:[grid]
	            
	           // minSize: 100,
	            //maxSize: 350
	           
	           // html: '这是西边区域 west'
	        } /*{
	            title: 'North',
	            region: 'north',
	            height: 100,
	            html: '这是北边区域 north'
	        }, {
	            title: 'East',
	            region: 'east',
	            width: 150,
	            html: '这是东边区域 east'
	        }*/]
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
	        	items:[borderPanel]
	        }
	    });
		
	 
	// bblbStore.load({params:{start:0, limit:50}});
	 //bblbxxStore.load({params:{zsbblb:1,cslb:9023,start:0, limit:50}});
	 var csstore = grid.store;
	 csstore.baseParams = {
			 cslb:9023
	 };
		csstore.load();	
		csstore.on("load",function(store) {  
			if(csstore.data.length > 0){
				curIndex = 0;
				grid.fireEvent("rowclick",grid,curIndex);
				grid.getSelectionModel().selectRange(curIndex,curIndex);
				
			}
		},grid);
});