
var cxCount = 1;
var rynbid = null;

var htmlStrStart = '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="cx/bdcx/queryxkxxrzcx';
var htmlStrEnd = '"></iframe>';
//'?rynbid='+ getRynbid(rynbid) + "&goto=queryHdxx&hhnbid=" + getQueryParam("hhnbid") + '"> </iframe>';

var selectdkrzid = null;

Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("40001",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	
	/**
		点击下一步后显示的内容
		暂时用户成员信息代替
	 */
	var hcyGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		pzlb: '40001',
    	region:'south',
		height:150,
    	title: '',
		url: 'cx/bdcx/queryxkxxrzcx',
    	showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){}
		}
	});
	
	var form40001 = new Gnt.ux.SjpzForm({
		pzlb: '40001',
		url: 'cx/bdcx/queryxkxxrzcx',
		title:'',
		cols:2,
		formType:'query'
	});
	var hxxGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		pzlb: '40001',
		title: '',
		url: 'cx/bdcx/queryxkxxrzcx',
		showPaging:false
	});
	
	var NewForm40001 = new Gnt.ux.SjpzForm({
		closable: false,
		region:'center',
		/*height:500,*/
		pzlb: '40001',
		labelWidth : 120,
		cols:2,
//		bindStore:xblryGrid.store,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:hxxGrid,
		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
		changeDictCust:function(cmb,res){
			
			return;
		},
		title: '详情信息',
		buttons:[new Ext.Button({
			id:'close',
			text:'关闭',
			minWidth:60,
			handler:function(){
				window.parent.closeWorkSpace();
			}
		}),
		new Ext.Button({
			id:'return',
			text:'返回',
			minWidth:60,
			handler:function(){
				Ext.getCmp('card').getLayout().setActiveItem(1);
			}
		})]
	});	
	var p1 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[
			{
				id:"nextId",
				border:false,
				frame: false,
				region:'center',
				visible:false,
	//        	height: 300,
				items:[hcyGrid]
			},
			{
				id:"centerId",
				border:false,
				frame: false,
				region:'center',
				layout:'border',
	        	height: 300,
				items:[{
						id: "panelHtmlId" ,
						html: '<div class="text" style="text-align:center;">写卡信息日志查询</div>',
						height: 80,
						region:'north',
						bodyStyle:'padding:15px'
					},
					form40001
				]
			},
			{
				border:false,
				frame: false,
	        	region:'south',
	        	height: 40,
	        	bodyStyle: 'padding:5px',
				layout:'table',
				layoutConfig: {
		        	columns: 14
		        },
	            items:[
					{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		    		},{
						border:false,
						frame: false,
		    		    items:[new Ext.Button({
		    		    	id:'fzId',
		        			text:'复杂条件',
		        			minWidth:80,
		        			handler:function(){
		        				
		        			}
		        		})]
		    		},{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		    		},{
						border:false,
						frame: false,
		    		    items:[{
			    	    	xtype: 'DkButton',
			    	    	form:form40001,
			    	    	callback: function(){
			    	    		//Ext.getCmp("queryId").handler();
			    	    	}
			    	    }]
		    		}/*,{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		    		},{
						border:false,
						frame: false,
		    		    items:[{
		    		    	id:'c31',
			        		xtype:'checkbox',
			        		boxLabel : "显示记录总数",  
			        		name : "xxzs",  
			        		inputValue : "2"  
			        	}]
		    		}*/,{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		    		},{
						border:false,
						frame: false,
		    		    items:[new Ext.Button({
		    		    	id:'preId',
		        			text:'上一步',
		        			disabled:true,
		        			minWidth:60,
		        			handler:function(){
		        				Ext.getCmp('preId').setDisabled(true);
		        				Ext.getCmp('fzId').setDisabled(false);
		        				Ext.getCmp('nexId').setDisabled(false);
		        				Ext.getCmp('nextId').setVisible(false);
		        				Ext.getCmp('centerId').setVisible(true);
		        			}
		        		})]
		    		},{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		    		},{
						border:false,
						frame: false,
		    		    items:[new Ext.Button({
		    		    	id:'nexId',
		        			text:'下一步',
		        			minWidth:60,
		        			handler:function(){
		        				Ext.getCmp('preId').setDisabled(false);
		        				Ext.getCmp('fzId').setDisabled(true);
		        				Ext.getCmp('nexId').setDisabled(true);
		        				Ext.getCmp('nextId').setVisible(true);
		        				Ext.getCmp('centerId').setVisible(true);
		        			}
		        		})]
		    		},{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		    		},{
						border:false,
						frame: false,
		    		    items:[new Ext.Button({
		    		    	id:'queryId',
		        			text:'查询',
		        			minWidth:60,
		        			handler:function(){

		        				var data = form40001.getForm().getValues();
								if(Gnt.util.isEmpty(data)){
									showInfo("至少输入一个查询条件！");
									return;
								}
								Ext.Msg.wait("正在执行查询，请稍后...");
		        				var store = hxxGrid.store;
		        				store.removeAll();
		        				store.baseParams = data;
		        				store.load({params:{start:0, limit:40}});
		        				

		        				//Ext.getCmp('card').getLayout().setActiveItem(1);
		        			
		        			}
		        		})]
		    		},{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		    		},{
						border:false,
						frame: false,
		    		    items:[new Ext.Button({
		        			text:'关闭',
		        			minWidth:60,
		        			handler:function(){
		        				window.parent.closeWorkSpace();
		        			}
		        		})]
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
	
	var hxxGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		pzlb: '40001',
		url:'cx/bdcx/queryxkxxrzcx',
		region:'center',
		height:500,
		title: '',
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
				selectdkrzid = selRes.data.xkrzid; 			
			},
			rowdblclick:function(grid,row){
				selRes = grid.store.getAt(row);
				selectdkrzid = selRes.data.xkrzid;
				NewForm40001.getForm().loadRecord(selRes);
				Gnt.toFormReadyonly(NewForm40001);
 				Ext.getCmp('card').getLayout().setActiveItem(2);
 				v.doLayout();
			}
		}
	});
	
	var p2 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[
			hxxGrid
		],
		buttons:[
			new Ext.Button({
				id:'close',
				text:'关闭',
				minWidth:60,
				handler:function(){
					window.parent.closeWorkSpace();
				}
			}),
			new Ext.Button({
				id:'return',
				text:'返回',
				minWidth:60,
				handler:function(){
					Ext.getCmp('card').getLayout().setActiveItem(0);
				}
			})
		]
	});
	
	var p4 = new Ext.Panel({
		layout:'border',
		items:[NewForm40001]
	});	
	//释放iframe占用资源
	function fixIFrame(o, p){
		var iFrame = p.getEl().dom; 
		if (iFrame.src) {
			iFrame.src = "javascript:false"; 
		}
	}

	function applyParam(store){
		
		/**
			是否显示记录总数
		 */
		var zs = '';
//		if(Ext.getCmp('c31').getValue()){
//			zs = '1';
//		}
		
		
		//Ext.apply(store.baseParams, {ryfw:'0',rkzt1:'',rkzt2:'',rkzt3:'',rkzt4:'',ryzl1:'',ryzl2:'',xszs:zs});
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
        	items:[p1,p2,p4]
        }
    });
	

		
	
});