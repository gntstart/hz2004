var selRes = null;
var curIndex = -1;
var selectRynbid = null;
var nbslid = null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	nbslid = getQueryParam("nbslid");
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("30028",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	var rzxxGrid = new Gnt.ux.SjpzGrid({
		pkname: 'ywbz',
		//region:'north',
		pzlb: '30028',
		title: '',
		height:500,
		url: 'cx/edzjxx/edzslcx/getEdzslrzxx',
		showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
			}
		}
	});
	var slxxGrid = new Gnt.ux.SjpzGrid({
		pkname: 'nbslid',
		pzlb: '30017',
		url:'cx/edzjxx/edzslcx/getEdzslxx.json',
		region:'center',
		height:400,
		title: '',
		dcFlag:true,
		showPaging:true,
//		loadCallback: function(res, store, bind_grid){
//			Ext.Msg.hide();
//			
//			if(res.length>0){
//				Ext.getCmp('card').getLayout().setActiveItem(1);
//			}else{
//				showInfo("未查到有关信息！");
//			}
//		},
		listeners:{
			rowclick:function(g, rowIndex, e){
				curIndex = rowIndex;
				var slzt = g.store.getAt(rowIndex).data.slzt;
				if(slzt=='11'||slzt=='13'||slzt=='16'||slzt=='31'||slzt=='71'||slzt=='73'||slzt=='89'
					||slzt=='92'||slzt=='93'||slzt=='94'||slzt=='95'||slzt=='96'||slzt=='97'||slzt=='98'){
					Ext.getCmp("bbhg").disable();
					Ext.getCmp("xxzf").disable();
					Ext.getCmp("cxbz").disable();
				}else if(slzt=='00'||slzt=='01'||slzt=='12'||slzt=='17'||slzt=='25'||slzt=='30'){
					Ext.getCmp("bbhg").disable();
					Ext.getCmp("xxzf").enable();
					Ext.getCmp("cxbz").enable();
				}else if(slzt=='02'){
					Ext.getCmp("bbhg").disable();
					Ext.getCmp("xxzf").disable();
					Ext.getCmp("cxbz").enable();
				}else if(slzt=='18'||slzt=='24'){
					Ext.getCmp("bbhg").enable();
					Ext.getCmp("xxzf").disable();
					Ext.getCmp("cxbz").disable();
				}
			},
			rowdblclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
			}
		}
	});
	var form30017= new Gnt.ux.SjpzForm({
		closable: false,
		region:'center',
		pzlb: '30028',
		height:400,
		labelWidth : 120,
		cols:2,
//		bindStore:xblryGrid.store,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:rzxxGrid,
		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
		changeDictCust:function(cmb,res){
			
			return;
		},
		title: ''
	});
	
	var bzls_window = new Gnt.ux.BzlsDialog({
		//选择立户信息回调
		callback: function(optype, dwxxModify){
			
		}
	});
	
	var p1 = new Ext.Panel({		
		items:[rzxxGrid,{
	    	region:'east',	    		    	 
	    	 layout:'table',
	    	 layoutConfig: {
	    		columns: 1
	    	 },
	    	 buttons:[
	  				new Ext.Button({
	  					id:'bbhg',
	  					text:'包不合格',
	  					disabled:true,
	  					minWidth:80,
	  					handler:function(){
							if(selRes){
								Gnt.submit(
										{nbslid:nbslid}, 
										"cx/edzjxx/edzslcx/zjdbzf.json", 
										"正在证件包不合格业务，请稍后...", 
										function(jsonData,params){
											if(jsonData.list){
												showInfo("包不合格业务成功！");
											}
											
										}
								);
							}else{
								alert("请先选中一条有效的数据!");
							}
						}
	  				}),
	  				new Ext.Button({
	  					id:'xxzf',
	  					text:'信息作废',
	  					disabled:true,
	  					minWidth:80,
	  					handler:function(){
							if(selRes){
								Gnt.submit(
										{nbslid:nbslid}, 
										"cx/edzjxx/edzslcx/zjslzf.json", 
										"正在证件信息作废业务，请稍后...", 
										function(jsonData,params){
											if(jsonData.list){
												showInfo("信息作废业务成功！");
												Ext.getCmp("xxzf").disable();
											}
											
										}
								);
							}else{
								alert("请先选中一条有效的数据!");
							}
							
						}
	  				}),new Ext.Button({
	  					id:'cxbz',
	  					text:'重新办证',
	  					disabled:true,
	  					minWidth:80,
	  					handler:function(){
	  						if(selRes){
		        				var url = basePath + "yw/edzzjgl/zjsl?jumpToZjsl="+"cxbz"+"&ryid="+selRes.data.ryid;			      
		        				if(parent && parent.openWorkSpace){
		        					parent.openWorkSpace(url,  "证件受理", "zjsl");
		        				}else{
		        					window.location.href = url;
		        				}
		        			
							}else{
								alert("请先选中一条有效的数据!");
							}
	  					}
	  				}),
	  				new Ext.Button({
	  					id:'bzls',
	  					text:'办证历史',
	  					minWidth:80,
	  					handler:function(){
							bzls_window.show();
							bzls_window.setSelRes(selRes);
						}
	  				}),
	  				new Ext.Button({
	  					text:'关闭',
	  					minWidth:80,
	  					handler:function(){
	  						window.parent.parent.closeWorkSpace();
	  					}
	  				}),
	  				new Ext.Button({
	  					text:'返回',
	  					minWidth:80,
	  					handler:function(){
	  						parent.Ext.getCmp('card').getLayout().setActiveItem(1);
	  						//Ext.getCmp('card').getLayout().setActiveItem(0);
	  					}
	  				})
	  				]
	    
	    }]
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
    	    //bodyStyle: 'padding:2px',
        	items:[p1]
        }
    });
	

	
	if(getQueryParam("slh")&& getQueryParam("slh")!=""){
		var store = rzxxGrid.store;
		store.baseParams = {
			slh:getQueryParam("slh"),
			nbslid:getQueryParam("nbslid"),
			start:0,
			limit:20
		};
		store.load();
		store.on("load",function(store){
			if(store.data.length > 0){
				//rzxxGrid.fireEvent("rowclick",rzxxGrid,0);
			}
		});
		var slxxstore = slxxGrid.store;
		slxxstore.baseParams = {
			slh:getQueryParam("slh"),
			nbslid:getQueryParam("nbslid"),
			start:0,
			limit:20
		};
		slxxstore.load();
		slxxstore.on("load",function(store){
			if(store.data.length > 0){
				slxxGrid.fireEvent("rowclick",slxxGrid,0);
				//var slzt = selRes.data.slzt;
//				if(slzt=='11'||slzt=='13'||slzt=='16'||slzt=='31'||slzt=='71'||slzt=='73'||slzt=='89'
//					||slzt=='92'||slzt=='93'||slzt=='94'||slzt=='95'||slzt=='96'||slzt=='97'||slzt=='98'){
//					Ext.getCmp("bbhg").disable();
//					Ext.getCmp("xxzf").disable();
//					Ext.getCmp("cxbz").disable();
//				}else if(slzt=='00'||slzt=='01'||slzt=='12'||slzt=='17'||slzt=='25'||slzt=='30'){
//					Ext.getCmp("bbhg").disable();
//					Ext.getCmp("xxzf").enable();
//					Ext.getCmp("cxbz").enable();
//				}else if(slzt=='02'){
//					Ext.getCmp("bbhg").disable();
//					Ext.getCmp("xxzf").disable();
//					Ext.getCmp("cxbz").enable();
//				}else if(slzt=='18'||slzt=='24'){
//					Ext.getCmp("bbhg").enable();
//					Ext.getCmp("xxzf").disable();
//					Ext.getCmp("cxbz").disable();
//				}
			}
		});
    }else{
    	Ext.getCmp('nextId').setVisible(false);
    }
	
	
});