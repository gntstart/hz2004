
var curIndex = -1;
var cxCount = 1;
var rynbid = null;

var selectRynbid = null;
var selectHhnbid = null;

Ext.onReady(function(){
	Ext.QuickTips.init();
//	alert(Gnt.ux.Dict.getDictLable('XZQHB', '340702'));
//	alert(Gnt.ux.Dict.getDictLable('XZJDXXB', '340722050'));
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10009",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	
	var cxForm = new Gnt.ux.SjpzForm({
		pzlb: '20002',
		title:'',
//		region:'center',
//		height:180,
		cols:2,
		formType:'query',
		labelWidth :  140
//		height:180,
	});
	
	var ywGrid = new Gnt.ux.SjpzGrid({
		pkname: 'mlpnbid',
		pzlb: '20002',
		title: '查询结果',
		region:'center',
		url: 'yw/dzgl/dzwh/dzwhxxcx.json',
		listeners:{
			rowclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				
				/**
					是否要按照处理标识设置人员内部ID?
					
				 */
//				alert(selRes.data.qhrynbid);
				
				selectRynbid = selRes.data.qhrynbid;
				
			},
			rowdblclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				dzxgModifyWin.show(selRes);
			}
		}
	});
	
	var dzxgAddWin = new Gnt.ux.DzxgAddDialog({
		title : "地址增加",
		callback: function(optype, dzAddxx){
			log_code = "F2003";
			//提交数据
			Gnt.submit(
					dzAddxx, 
					"yw/dzgl/dzwh/insertDzwhxx", 
					"正在增加地址信息，请稍后...", 
					function(jsonData,params){
						showInfo("地址信息增加成功！");
						ywGrid.store.reload(); 
					}
			);
		}
	});
	var dzxgModifyWin = new Gnt.ux.DzxgModifyDialog({
		title : "地址修改",
		callback: function(optype, dzModifyxx){
			log_code = "F2002";
			//提交数据
			Gnt.submit(
					dzModifyxx, 
					"yw/dzgl/dzwh/updateDzwhxx", 
					"正在修改地址信息，请稍后...", 
					function(jsonData,params){
						showInfo("地址信息修改成功！");
						ywGrid.store.reload(); 
					}
			);
		}
	});
	var zhcx_dialog = new Gnt.ux.ZhcxDialog({
		pzlb : cxForm.pzlb,
		grid:ywGrid,
		callback: function(where){
			var grid = this.grid;
			var store = grid.store;
			var length = 0;
			var data = {"where": where};
			store.removeAll();
			data.pzlb = grid.store.pzlb;
			data.config_key = this.config_key;
			store.baseParams = data;
			if(this.applyParam){
				this.applyParam();
			}
			Ext.Msg.wait("正在执行查询，请稍后...");
			store.load({params:{start:0, limit:grid.store.pageSize}});
			store.on("load",function(store) {  
				length = store.data.length
				if(length > 0){
					curIndex = 0;
					grid.fireEvent("rowclick",grid,curIndex);
					grid.getSelectionModel().selectRange(curIndex,curIndex);
				}else{
					showInfo("没有找到符合条件的数据！");
				}
				Ext.Msg.hide();
			},grid); 			        						
		}
	});	
	var p1 = new Ext.Panel({
		layout:'border',
		items:[{
			id:"northId",
			region:'north',
			layout:'border',
			title:'查询条件<span class="text" style="text-align:center;display:inline-block;width:95%;">(无条件输入时查本辖区内所有信息)</span>',
//			width:'100%',
			height:150,
			items:[cxForm,
				{
					id:"northEastId",
					region:'east',
					width:'100',
					html:'',
					bodyStyle:'padding:10px 5px 10px 5px',
					layout:'table',
					align:'center',
					border:false,
					frame:false,
					buttonAlign: 'center',
					layoutConfig: {
						columns: 1
					},
					items:[
						new Ext.Button({
							id:'queryId',
			    			text:'查询',
			    			minWidth:80,
			    			handler:function(){
			    				
			    				var data = cxForm.getForm().getValues();
		        				data.log_code = "F2008";
		        				var store = ywGrid.store;
		        				
		        				store.removeAll();
		        				
		        				store.baseParams = data;
		        				
		        				store.load({params:{start:0, limit:20}});
		        				
		        				Ext.getCmp('dzxgId').setDisabled(false);
		        				
			    			}
						})
						,{
							height:10,
							border:false,
							frame:false
						}
						,new Ext.Button({
							id:'zhId',
			    			text:'组合查询',
			    			minWidth:80,
			    			disabled:true,
			    			handler:function(){
			    				zhcx_dialog.show();
			    			}
						})
						,{
							height:30,
							border:false,
							frame:false
						}
						,new Ext.Button({
							id:'dkId',
			    			text:'地址增加',
			    			minWidth:80,
			    			handler:function(){
			    				dzxgAddWin.show();
			    			}
						})
					]
				}
			]
			
		}
			,ywGrid,{
				id:"eastId",
				region:'east',
				width:'80',
				html:'',
				bodyStyle:'padding:10px 5px 10px 5px',
				layout:'table',
				align:'center',
				border:false,
				frame:false,
				buttonAlign: 'center',
				layoutConfig: {
					columns: 1
				},
				items:[
					{
						height:400,
						border:false,
						frame:false
					}
					,new Ext.Button({
						id: 'dzxgId',
						text:'地址修改',
						minWidth:60,
						disabled:true,
						handler:function(){
							if(ywGrid.getSelectionModel().getSelected()){
								dzxgModifyWin.show(ywGrid.getSelectionModel().getSelected());
							}else{
								showInfo("请选择需要修改的地址！");
        						return;
							}
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
        	items:[p1]
        }
    });
	
	
	ywGrid.colModel.setHidden(8,true);
	ywGrid.colModel.setHidden(9,true);
	ywGrid.colModel.setHidden(10,true);
	ywGrid.colModel.setHidden(11,true);
	
	
	function onLoad(store){
		if(store.getCount() > 0){
			
			showInfo("(" + selectSfzh + ")已被常住人员使用，不能作删除操作，身份号码分配删除业务无法完成。");
			return ;
		}else{
			
			/**
				物理删除
			 */
			var subdata = {
				fpid: ywGrid.getSelectionModel().getSelected().data.fpid
			};
			
			if(flag){
				delXx(subdata);
			}
			
			flag = false;
			return ;
		}
	}
	
	/**
		删除所选记录
	 */
	function delXx(subdata){
		Gnt.submit(
				subdata, 
				"yw/hjyw/sfhfpxxbl/processSfhmfpscyw", 
				"正在处理身份号分配信息补录信息，请稍后...", 
				function(jsonData,params){
					
					showInfo("身份号分配信息补录删除成功！",function(){
						/**
							删除选中记录
						 */
						ywGrid.store.remove(ywGrid.getSelectionModel().getSelected());
					});
					
					return ;
				}
			);
			
	}
    
	function applyParam(store){
		
		/**
			查询类别
		 */
		var lb = '';
		if(Ext.getCmp('r11').getValue()){
			lb = '1';
		}else if(Ext.getCmp('r12').getValue()){
			lb = '2';
		}else if(Ext.getCmp('r13').getValue()){
			lb = '3';
		}
		
		var ycl = '';
		if(Ext.getCmp('c11').getValue()){
			ycl = '1';
		}
		
		var wcl = '';
		if(Ext.getCmp('c21').getValue()){
			wcl = '1';
		}
		
		var clbz = '';
		if(ycl || wcl){
			clbz = '1';
		}
		
		Ext.apply(store.baseParams, {czlx:lb,clbz:clbz,ycl:ycl,wcl:wcl});
		
	}
	
	/**
		按下回车键执行查询操作
		
	var key1 = new Ext.KeyMap(document, {
		key:Ext.EventObject.ENTER,
		fn:function() {
			var data = form20000.getForm().getValues();
			
			if(Gnt.util.isEmpty(data)){
				showInfo("至少输入一个查询条件！");
				return;
			}
			
			var store = rkjbxxGrid.store;
			
			store.removeAll();
			
			store.baseParams = data;
			
			applyParam(store);
			
			store.load({params:{start:0, limit:20}});
			
			//改变颜色
    store.on('load',function(s,records){
    	var girdcount=0;
    	s.each(function(r){
    		
    		if(0 == r.get("ryzt")){
    			
    		}else if(1 == r.get("ryzt")){
    			rkjbxxGrid.getView().getRow(girdcount).style.backgroundColor="red";
    		}else{
    			rkjbxxGrid.getView().getRow(girdcount).style.backgroundColor="green";
    		}
    		
    		girdcount ++ ;
    	});
    });
    Ext.getCmp('card').getLayout().setActiveItem(1);
},
scope: this  
});
	 */
	
	
});