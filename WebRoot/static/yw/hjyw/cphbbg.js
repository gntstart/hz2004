
var cxCount = 1;
var rynbid = null;

var htmlStrStart = '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="cx/hjjbxx/ckxx/queryXxxx';
var htmlStrEnd = '"></iframe>';
//'?rynbid='+ getRynbid(rynbid) + "&goto=queryHdxx&hhnbid=" + getQueryParam("hhnbid") + '"> </iframe>';

var selectRynbid = null;
var selectHhnbid = null;
var selectHjywid = null;
var selectRyid = null;
var lastQueryParams = null;
var bgfs = null; //"1" 单个，2 批量

Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10019,20003",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX','CS_HBBGLB'],function(){});
	
	/**
		点击下一步后显示的内容
		暂时用户成员信息代替
	 */
	var hcyGrid = new Gnt.ux.SjpzGrid({
		id:'nextId',
		pkname: 'rynbid',
		pzlb: '10019',
    	region:'center',
    	title: '户成员',
		url: 'yw/common/queryRyxx.json',
    	showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){}
		}
	});
	
	var form20000 = new Gnt.ux.SjpzForm({
		pzlb: '20000',
		title:'',
		cols:2,
		formType:'query'
	});
	
	var p1 = new Ext.Panel({
		layout:'border',
		items:[
			hcyGrid,
			{
				id:"centerId",
				border:false,
				frame: false,
				region:'center',
				layout:'border',
				items:[{
						border:false,
						frame: false,
						id: "panelHtmlId" ,
						html: '<div class="text" style="text-align:center;"><h2>成批户别变更</h2></div>',
						height: 80,
						region:'north',
						bodyStyle:'padding:15px'
					},
					form20000
				]
			},{
				id:"southId",
	        	region:'south',
	        	height:40,
	            border:false,
	            frame:false,
				border:false,
				frame: false,
		        region:'south',
			    bodyStyle:'padding:5px',
		        layout:'table',
		        layoutConfig: {
		        	columns: 14
		        },
			    	items: [{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			    		},{
			    				xtype:'button',
			    		    	id:'zhId',
			        			text:'组合条件',
			        			minWidth:80,
			        			handler:function(){
			        				zhcx_dialog.show();	
			        			}
			    		},{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			    		},{
			    	    	xtype: 'DkButton',
			    	    	form:form20000,
			    	    	callback: function(){
			    	    		//Ext.getCmp("queryId").handler();
			    	    	}
			    	    },/*{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			    		},{
			    		    	id:'c11',
				        		xtype:'checkbox',
				        		boxLabel : "显示记录总数",  
				        		name : "jlzs",  
				        		inputValue : "1" 
			    		},*/{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			    		},{
			    			xtype:'button',
			    		    	id:'preId',
			        			text:'上一步',
			        			disabled:true,
			        			minWidth:60,
			        			handler:function(){
			        				Ext.getCmp('preId').setDisabled(true);
			        				Ext.getCmp('zhId').setDisabled(false);
			        				Ext.getCmp('nexId').setDisabled(false);
			        				Ext.getCmp('nextId').setVisible(false);
			        				Ext.getCmp('centerId').setVisible(true);
			        			}
			    		},{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			    		},{
			    			xtype:'button',
			    		    	id:'nexId',
			        			text:'下一步',
			        			disabled:true,
			        			minWidth:60,
			        			handler:function(){
			        				Ext.getCmp('preId').setDisabled(false);
			        				Ext.getCmp('zhId').setDisabled(true);
			        				Ext.getCmp('nexId').setDisabled(true);
			        				Ext.getCmp('nextId').setVisible(true);
			        				Ext.getCmp('centerId').setVisible(false);
			        			}
			    		},{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			    		},{
			    			xtype:'button',
			    		    	id:'queryId',
			        			text:'查询',
			        			minWidth:60,
			        			handler:function(){
			        				var data = form20000.getForm().getValues();
									if(Gnt.util.isEmpty(data)){
										showInfo("至少输入一个查询条件！");
										return;
									}
			        				
									Ext.Msg.wait("正在执行查询，请稍后...");
									
			        				var store = rkxxGrid.store;
			        				store.removeAll();
			        				
			        				store.baseParams = data;
			        				applyParam(store);

			        				//保存查询条件
			        				lastQueryParams = store.baseParams;
			        				
			        				store.load({params:{start:0, limit:20}});
			        				
			        				store.on('load',function(s,records){
			        					if(store.getCount() > 0){
			        						
			        						rkxxGrid.fireEvent("rowclick",rkxxGrid,0);
					        				rkxxGrid.getSelectionModel().selectRange(0,0);
					        				
			        					}else{
//			        						showInfo("未查到有关信息！");
			        						return false;
			        					}
			        					
			        				});
			        				
			        			}
			    		},{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			    		},{
			    			xtype:'button',
			        			text:'关闭',
			        			minWidth:60,
			        			handler:function(){
			        				window.parent.closeWorkSpace();
			        			}
			    		},{
			        		frame:false,
							border:false,
							xtype:'panel',
							html:'',
							height:40
			        	},{
			    	    	frame:false,
							border:false,
							id:'_READ_CARD_ID',
							xtype:'panel',
							html:'',
							width:10
						}
			    		 
			    	]
	        }
		]
	});
	
	var cphbbgWindow = new Gnt.ux.SelectCphbbg({
		callback: function(data){
			var subdata = data;
			subdata.bgfs = bgfs;

			if(bgfs=="1"){
				if(data.bdhhb != rkxxGrid.getSelectionModel().getSelected().data.hb){
					subdata.ryxx = Ext.encode(rkxxGrid.getSelectionModel().getSelected().data);
				}else{
					showErr("户别无变化！");
					return;
				}
			}else{
				if(rkxxGrid.store.getTotalCount()>500){
					showErr("成批户别变更数量不能超过500人！");
					return;
				}
				subdata.ryxxQueryParams = Ext.encode(lastQueryParams);
			}
			log_code = "F1012";
			//提交数据
			Gnt.submit(
					subdata, 
					"yw/hjyw/cphbbg/processHbbgyw", 
					(bgfs=="1"?"正在处理户别变更信息，请稍后...":"正在处理批量户别变更，请稍后..."), 
					function(jsonData,params){
						showInfo("户别变更成功！");
					}
			);
		}
	});
	
	var rkxxGrid = new Gnt.ux.SjpzGrid({
		border:false,
		frame:false,
		pkname: 'rynbid',
		pzlb: '20000',
		url:'cx/hjjbxx/ckxx/getXxxx.json',
		region:'center',
		title: '成批户别变更',
		showPaging:true,
		loadCallback: function(res, store, bind_grid){
			Ext.Msg.hide();
			
			if(res.length>0){
				Ext.getCmp('card').getLayout().setActiveItem(1);
			}else{
				showInfo("未查到有关信息！");
			}
		},
		listeners:{
			rowclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				
				selectRynbid = selRes.data.rynbid;
				selectHhnbid = selRes.data.hhnbid;
				selectHjywid = selRes.data.hjywid;
				selectRyid =selRes.data.ryid;
			},
			rowdblclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				
				selectRynbid = selRes.data.rynbid;
				selectHhnbid = selRes.data.hhnbid;
				selectHjywid = selRes.data.hjywid;
				selectRyid =selRes.data.ryid;
				var pkvalue = selRes.data[rkxxForm.bindStore.pkname];
				var re = rkxxForm.bindStore.getById(pkvalue);
				if(re){
					rkxxForm.getForm().loadRecord(re);
				}
				
    			Ext.getCmp('card').getLayout().setActiveItem(2);
    			
    			v.doLayout();
			}
		}
	});
	
	var zhcx_dialog = new Gnt.ux.ZhcxDialog({
		pzlb : form20000.pzlb,
		grid:rkxxGrid,
		applyParam:function(){
			applyParam(this.grid.store);
		},
		callback: function(where){
			var grid = this.grid;
			var store = grid.store;
			var length = 0;
			var data = {"where": where};
			store.removeAll();
			data.pzlb = grid.store.pzlb;
			data.config_key = this.config_key;
			data.log_code = "F1021";
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
					Ext.getCmp('card').getLayout().setActiveItem(1);
				}else{
					showInfo("没有找到符合条件的数据！");
				}
				Ext.Msg.hide();
			},grid); 			        						
		}
	});	
	
	var p2 = new Ext.Panel({
		layout:'border',
		items:[rkxxGrid],
			buttons:[
				new Ext.Button({
					text:'户别变更',
					minWidth:80,
					disabled:user.flag>0?true:false,
					handler:function(){
						bgfs = "1";
						if(rkxxGrid.getSelectionModel().getSelected()){
							cphbbgWindow.show();
						}else{
							showInfo("请选择需要户别变更的人员！");
						}
					}
				}),
				new Ext.Button({
					text:'成批变更',
					minWidth:80,
					disabled:user.flag>0?true:false,
					handler:function(){
						bgfs = "2";
						
						if(rkxxGrid.store.getCount()>0){
							cphbbgWindow.show();
						}else{
							showInfo("请查询需要成批变更的人员！");
						}
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
						Ext.getCmp('card').getLayout().setActiveItem(0);
					}
				})
				]
	});
	
	var rkxxForm = new Gnt.ux.SjpzForm({
		closable: false,
		region:'center',
		pzlb: '20003',
		labelWidth : 120,
		cols:2,
		formType:'view',
		title:'',
		bindViewGrid:rkxxGrid
	});
	
	var p3 = new Ext.Panel({
		layout:'border',
		items:[rkxxForm],
		buttons:[
			new Ext.Button({
		    	id:'queryId',
    			text:'关闭',
    			minWidth:60,
    			handler:function(){
    				window.parent.parent.closeWorkSpace();
    			}
			}),
			new Ext.Button({
		    	id:'queryId',
    			text:'返回',
    			minWidth:60,
    			handler:function(){
    				Ext.getCmp('card').getLayout().setActiveItem(1);
    			}
			})
		]
	});
	
	
	//定义分页面板
	
	function applyParam(store){
		
		/**
			是否显示记录总数
		 */
		var zs = '';
//		if(Ext.getCmp('c11').getValue()){
//			zs = '1';
//		}
		
		Ext.apply(store.baseParams, {xszs:zs,config_key:'queryCphbbg'});
	}
	
	function getRynbid(rynbid){
		if(rynbid != null){
			return rynbid;
		}else{
			return getQueryParam("rynbid");
		}
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
        	items:[p1,p2,p3]
        }
    });
    
});