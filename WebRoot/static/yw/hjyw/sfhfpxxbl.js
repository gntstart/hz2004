
var curIndex = -1;
var cxCount = 1;
var rynbid = null;

var selectRynbid = null;
var selectHhnbid = null;

Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10019,10008",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	
	var form10008 = new Gnt.ux.SjpzForm({
		pzlb: '10008',
		url: 'yw/queryRyxx',
		title:'查询条件',
		cols:2,
		formType:'query',
//		height:180,
		buttons:[
			new Ext.Button({
				id:'queryId',
    			text:'查询',
    			minWidth:60,
    			handler:function(){
    				var data = form10008.getForm().getValues();
    				
//					if(Gnt.util.isEmpty(data)){
//						showInfo("至少输入一个查询条件！");
//						return;
//					}
					data.log_code = "F3413";
    				var sfzstore = sfhmfpblGrid.store;
    				
    				sfzstore.removeAll();
    				
    				sfzstore.baseParams = data;
    				
    				Ext.apply(sfzstore.baseParams, {
						config_key:'queryHJYW_GMSFHMSXMFPXXB'
    				});
    				
    				sfzstore.load();
    				
    				sfzstore.on("load",function(store) {
    					if(store.getCount() > 0){
//    						Ext.getCmp("fpscId").setDisabled(false);
    					}else{
    						Ext.getCmp("fpscId").setDisabled(true);
    					}
    				});
    				
    			}
			})
		]
	});
	
	var sfhmfpblGrid = new Gnt.ux.SjpzGrid({
		pkname: 'ryid',
		pzlb: '10008',
		title: '查询结果',
		region:'center',
		url: 'cx/hjjbxx/ckxx/getXxxx.json',
		listeners:{
			rowclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				
				Ext.getCmp("fpscId").setDisabled(false);
			}
		}
	});
	
	var czrkGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		pzlb: '20000',
		url: 'cx/hjjbxx/ckxx/getXxxx.json'
	});
	
	var fpWin = new Gnt.ux.SelectSfhfpbl({
		
	});
	
	var p1 = new Ext.Panel({
		layout:'border',
		items:[
			{
				id:"northId",
				border:false,
				frame: false,
				region:'north',
				layout:'border',
	        	height: 180,
				items:[form10008]
			},{
				id:"centerId",
				border:false,
				frame: false,
				region:'center',
				layout:'border',
				buttonAlign: 'center',//居中
				items:[sfhmfpblGrid,{
					id:"eastId",
		        	region:'east',
		        	bodyStyle:'padding:0px 10px 0px 10px',
		        	width:'90',
		            border:false,
		            frame:false,
        			items:[
        				{
			    	    	height:200,
			    	    	border:false,
			    	    	frame:false
			    	    }
        				,new Ext.Button({
        					id:'fpblId',
        	    			text:'分配补录',
        	    			minWidth:60,
        	    			handler:function(){
        	    				fpWin.show();
        	    			}
        				}),
        				{
			    	    	height:10,
			    	    	border:false,
			    	    	frame:false
			    	    }
        				,new Ext.Button({
        					id:'fpscId',
        	    			text:'分配删除',
        	    			minWidth:60,
							disabled:true,
        	    			handler:function(){
        	    				showQuestion("您确定需要删除所选择的身份号码分配信息吗?", function(btnType){
        	    					if(btnType=="yes"){
        	    						
        	    						/**
        	    							常住人口已有公民使用身份证号码不允许删除
        	    						 */
        	    						var rkstore = czrkGrid.store;
        	    						var selectSfzh = sfhmfpblGrid.getSelectionModel().getSelected().data.gmsfhm;
        	    						rkstore.baseParams = {
        	    							gmsfhm:selectSfzh,
        	    							config_key:'queryPoHJXX_CZRKJBXXB'
        	    						};
        	    						rkstore.load({
        	    							callback:function(records,options,success){
        	    								if(records.length > 0){
            	    								
            	    								showInfo("(" + selectSfzh + ")已被常住人员使用，不能作删除操作，身份号码分配删除业务无法完成。");
            	    								return ;
            	    							}else{
            	    								
            	    								/**
            	    									物理删除
            	    								 */
            	    								var subdata = {
            	    									fpid: sfhmfpblGrid.getSelectionModel().getSelected().data.fpid
            	    								};
            	    								
            	    								delXx(subdata);
            	    								
            	    								return ;
            	    							}
        	    							}
        	    						});
        	    						
        	    						/**
        	    							有选中记录,不失效
        	    							没有,失效
        	    						 */
//        	    						Ext.getCmp("fpscId").setDisabled(true);
        	    					}
        	    				});
        	    			}
        				})
        			]
				}]
			}
		],
		buttons:[
			new Ext.Button({
				id:'closeId',
    			text:'关闭',
    			minWidth:60,
    			handler:function(){
    				window.parent.closeWorkSpace();
    			}
			})
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
	
	function onLoad(store){
		if(store.getCount() > 0){
			
			showInfo("(" + selectSfzh + ")已被常住人员使用，不能作删除操作，身份号码分配删除业务无法完成。");
			return ;
		}else{
			
			/**
				物理删除
			 */
			var subdata = {
				fpid: sfhmfpblGrid.getSelectionModel().getSelected().data.fpid
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
		log_code = "F1015";
		Gnt.submit(
				subdata, 
				"yw/hjyw/sfhfpxxbl/processSfhmfpscyw", 
				"正在处理身份号分配信息补录信息，请稍后...", 
				function(jsonData,params){
					
					showInfo("身份号分配信息补录删除成功！",function(){
						/**
							删除选中记录
						 */
						sfhmfpblGrid.store.remove(sfhmfpblGrid.getSelectionModel().getSelected());
					});
					
					return ;
				}
			);
			
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