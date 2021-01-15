var selectedSelRes=null;
var ywbbid=null;
var ywbblb=null;
var ywbbmb=null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	var bblbStore = new Ext.data.JsonStore({
		proxy: new Ext.data.HttpProxy({
			url : 'gl/ywbb/querybblb',
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
			url : 'gl/ywbb/querybblbxx',
			method: "POST",
			disableCaching: true
	    }),
		root : 'list',
		totalProperty : 'totalCount',
		fields : ["createName", "createDate", "updateName", "updateDate",
		"bbName","ywbblb","ywbbid","bbmb","ywbbmb"],
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
		header : "报表名称",
		width : 100,
		sortable : true,
		locked : false,
		dataIndex : 'bbName'
	}, {
		header : "创建时间",
		width : 100,
		sortable : true,
		dataIndex : 'createDate'
	},{
		header : "创建人姓名",
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
	
	
	var ywbbxxGrid = new Ext.grid.GridPanel({
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
        	},
			rowdblclick:function(g, rowIndex, e){
				selectedSelRes = g.store.getAt(rowIndex);
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
	        		ywbblb=selectedSelRes.data.dm;
	        		var store = ywbbxxGrid.store;	        		
	        		store.baseParams = {
	        			ywbblb:selectedSelRes.data.dm,
	        			start:0,
	        			limit:50
	        		};
	        		store.load();
	        		store.on("load",function(store) {  
	        			if(csstore.data.length > 0){
	        				curIndex = 0;
	        				ywbbxxGrid.fireEvent("rowclick",ywbbxxGrid,0);
	        				ywbbxxGrid.getSelectionModel().selectRange(curIndex,curIndex);
	        				
	        			}
	        		},ywbbxxGrid);
	        	},
				rowdblclick:function(g, rowIndex, e){
					selectedSelRes = g.store.getAt(rowIndex);
	        		var store = ywbbxxGrid.store;
	        		store.baseParams = {
	        		    ywbblb:selectedSelRes.data.dm,
	        			start:0,
	        			limit:50
	        		};
	        		store.load();
	        		store.on("load",function(store) {  
	        			if(csstore.data.length > 0){
	        				curIndex = 0;
	        				ywbbxxGrid.fireEvent("rowclick",ywbbxxGrid,0);
	        				ywbbxxGrid.getSelectionModel().selectRange(curIndex,curIndex);
	        				
	        			}
	        		},ywbbxxGrid);
				}
			}
	});
	
	
	var ywbbwhadd_win=new Gnt.ux.Selectywbbwhadd({//新增弹窗
		callback: function(ywbbmc,bbmbcontent){
			//提交数据
			Gnt.submit(
					{bbmc:ywbbmc,bblb:ywbblb,bbmbcontent:bbmbcontent}, 
					"gl/ywbb/addbblbxx", 
					"正在新增业务报表，请稍后...", 
					function(jsonData,params){
						showInfo("业务报表信息新增成功！");
						ywbbxxGrid.store.reload(); 
					}
			);
		}
	});
	
	var ywbbwhup_win=new Gnt.ux.Selectywbbwhup({//修改弹窗
		callback: function(ywbbmc,bbmbcontent){
			//提交数据
			Gnt.submit(
					{bbmc:ywbbmc,bblb:ywbblb,ywbbid:ywbbid,bbmb:bbmbcontent}, 
					"gl/ywbb/updatebblbxx", 
					"正在修改业务报表，请稍后...", 
					function(jsonData,params){
						showInfo("业务报表信息修改成功！");
						ywbbxxGrid.store.reload(); 
					}
			);
		}
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
		Ext.Msg.wait("操作正在执行中，请稍后...");
		for(var i =0 ;i<ywbbxxGrid.store.data.length;i++){
			var res = ywbbxxGrid.store.getAt(i);
			var bbName=res.data.bbName;
			ywbbmb=res.data.ywbbmb;		
			dellCell.ReadFromBase64Str(ywbbmb);
			dellCell.SaveAsFile(bbName,0);
			if(i==(ywbbxxGrid.store.data.length-1)){
				Ext.Msg.hide();
				alert("模板全部导出完毕！");
			}
		}
	}
}),
new Ext.Button({
	id:'add',
	text:'增加',
	minWidth:60,	
	handler:function(){
		var cmp=Ext.getCmp("bblb");
		cmp.setValue(ywbblb);
		cmp.setDisabled(true);	
		//ywbbwhadd_win.setSelRes();  	    
		ywbbwhadd_win.show();
		
	}
}),
new Ext.Button({
	id:'update',
	text:'修改',
	minWidth:60,	
	handler:function(){
		if(ywbbxxGrid.getStore().getCount() > 0){
			var bblbmc=grid.getSelectionModel().getSelections()[0].data.mc;
			 ywbblb=grid.getSelectionModel().getSelections()[0].data.dm;
			if(ywbbxxGrid.getSelectionModel().getCount() >=1){
				var bbName=ywbbxxGrid.getSelectionModel().getSelections()[0].data.bbName;
				ywbbmb=ywbbxxGrid.getSelectionModel().getSelections()[0].data.ywbbmb;
					ywbbid = ywbbxxGrid.getSelectionModel().getSelections()[0].data.ywbbid;
					
					//alert(ywbbid+"--"+bbName+"--"+ywbblb);					
					var cmp=Ext.getCmp("bblbup");
					cmp.setValue(ywbblb);
					var cmp2=Ext.getCmp("bbmcup");
					cmp2.setValue(bbName);
					cmp.setDisabled(true);
					var cmp3=Ext.getCmp("ywbbmb");
					cmp3.setValue(ywbbmb);
					
					ywbbwhup_win.show();
					ywbbwhup_win.setSelRes(ywbblb,ywbbmb,bbName);
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
		if(ywbbxxGrid.getStore().getCount() > 0){
			var bblbmc=grid.getSelectionModel().getSelections()[0].data.mc;
			 ywbblb=grid.getSelectionModel().getSelections()[0].data.dm;
			if(ywbbxxGrid.getSelectionModel().getCount() >=1){
				var bbName=ywbbxxGrid.getSelectionModel().getSelections()[0].data.bbName;
				if(confirm("确定要删除["+bblbmc+"]["+bbName+"]?")){
					ywbbid = ywbbxxGrid.getSelectionModel().getSelections()[0].data.ywbbid;
					//alert(ywbbid+"--"+bbName+"--"+ywbblb);					
					Gnt.submit(
							{
								ywbbid:ywbbid}, 
								"gl/ywbb/delbblbxx", 
								"正在删除报表，请稍后...", 
								function(jsonData,params){
									showInfo("报表删除成功！");
									ywbbxxGrid.store.reload(); 
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
	id:'export',
	text:'模板导出',
	minWidth:60,
	handler:function(){
		var bbName=ywbbxxGrid.getSelectionModel().getSelections()[0].data.bbName;
		ywbbmb=ywbbxxGrid.getSelectionModel().getSelections()[0].data.ywbbmb;		
		dellCell.ReadFromBase64Str(ywbbmb);
		dellCell.SaveFile();
		
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
	            titlr: '',
	            region: 'center',
	            collapsible: false,
	            layout:'border',
				border:false,
	            frame:false,
	            width:300,
	            anchor:'100% 100%',
	    	    margins: '0 0 0 0',
	    		cmargins: '0 0 0 0',
	            items:[ywbbxxGrid]
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
	 bblbxxStore.load({params:{ywbblb:1,start:0, limit:50}});
	 var csstore = grid.store;
	 csstore.baseParams = {
				cslb:9022
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