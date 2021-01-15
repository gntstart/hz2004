
var cxCount = 1;
var rynbid = null;
var hhnbid = null;
var ryid = null;
var hhid = null;
var mlpnbid = null;
var queryFlag = false;
var htmlStrStart = '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="cx/hjjbxx/ckxx/queryXxxx?goto=';
var htmlStrEnd = '"></iframe>';
//'?rynbid='+ getRynbid(rynbid) + "&goto=queryHdxx&hhnbid=" + getQueryParam("hhnbid") + '"> </iframe>';

var selectRynbid = null;
var selectHhnbidselectHhnbid = null;

Ext.onReady(function(){
	Ext.QuickTips.init();
	
	var hzywid = getQueryParam("hzywid");
	var pch = getQueryParam("pch");
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("30033",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	
	/**
		点击下一步后显示的内容
		暂时用户成员信息代替
	 */
/*	var hcyGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		pzlb: '10019',
    	region:'south',
		height:150,
    	title: '',
		url: 'yw/common/queryRyxx.json',
    	showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){
				
			}
		}
	});*/
	
	
	var form30033 = new Gnt.ux.SjpzForm({
		pzlb: '30033',
		url: 'yw/bbdy/zjfj/queryzjfj',
		title:'',
		cols:2,
		formType:'query'
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
				visible:false
	//        	height: 300,
				//items:[hcyGrid]
			},
			{
				id:"centerId",
				border:false,
				frame: false,
				region:'center',
				layout:'border',
//	        	height: 300,
				items:[{
						id: "panelHtmlId" ,
						html: '<div class="text" style="text-align:center;"><br /><h2>证件分检查询</h2></div>',
						height: 80,
						region:'north',
						bodyStyle:'padding:15px'
					},
					form30033
				]
			},
			{
				id:"southId",
	        	region:'south',
	        	height:40,
	            border:false,
	            frame:false,
	            items:[{
					border:false,
					frame: false,
		        	region:'south',
		        	height: 40,
		        	bodyStyle: 'padding:5px',
					layout:'table',
					layoutConfig: {
			        	columns: 15
			        },
			    	items: [
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
			        			text:'组合条件',
			        			minWidth:80,
			        			handler:function(){
			        				zhcx_dialog.show();	
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
				    	    	form:form30033,
				    	    	callback: function(){
				    	    		//Ext.getCmp("queryId").handler();
				    	    	}
				    	    }]
			    		},/*{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
//							colspan:1
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
			    		},*/{
			    			//width: 100,
			    			bodyStyle: 'padding:0 0 0 400',
							border:false,
							frame: false
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
			    			width: 10,
							border:false,
							frame: false
			    		},{
							border:false,
							frame: false,
			    		    items:[new Ext.Button({
			    		    	id:'nexId',
			        			text:'下一步',
			        			minWidth:60,
			        			handler:function(){
			        				/*Ext.getCmp('preId').setDisabled(false);
			        				Ext.getCmp('fzId').setDisabled(true);
			        				Ext.getCmp('nexId').setDisabled(true);
			        				Ext.getCmp('nextId').setVisible(true);
			        				Ext.getCmp('centerId').setVisible(false);*/
			        			}
			        		})]
			    		},{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
//							colspan:3
//							columns:10
			    		},{
							border:false,
							frame: false,
			    		    items:[new Ext.Button({
			    		    	id:'queryId',
			        			text:'查询',
			        			minWidth:60,
			        			handler:function(){
			        				var data = form30033.getForm().getValues();
			        				
			        					/*if(Gnt.util.isEmpty(data)){
			        						showInfo("至少输入一个查询条件！");
			        						return;
			        					}*/
			        				
									Ext.Msg.wait("正在执行查询，请稍后...");
			        				
			        				var store = zjfjGrid.store;
			        				
			        				store.removeAll();
			        				
			        				store.baseParams = data;
			        				
			        				store.load({params:{start:0, limit:40}});
			        				
			        				Ext.getCmp('card').getLayout().setActiveItem(1);
			        				Ext.Msg.hide();
			        				
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
			    		}
			    		 
			    	]
					
				},{
	    	    	frame:false,
					border:false,
					id:'_READ_CARD_ID',
					xtype:'panel',
					html:'',
					width:10
					
				}]
	        }
		]
	});
	
	var colModel = new Ext.grid.ColumnModel([
	    {
 			header: "槽位号",
 	        dataIndex: "cwh",
 	        sortable: true,
 			width: 120
 		},{
 	        header: "数量",
 	        dataIndex: "num",
 	        sortable: true,
 			width: 200
 	    }
 	]);
 	var myuserStore = new Ext.data.JsonStore({
  		proxy: new Ext.data.HttpProxy({
  			url: 'yw/bbdy/zjfj/queryzjfj',
 			method: "POST",
 			disableCaching: true
 	    }),
         root: 'list',
         id:'id',
         totalProperty:'totalCount',
         fields: [
 			"cwh",
 			"num"
         ],
         listeners:{
 			loadexception:function(mystore,options,response,error){
 				if(error){
 					var json = Ext.decode(response.responseText);
 					Ext.Msg.alert("提示",json.messages[0]);
 				}else{
 					Ext.Msg.alert("提示",response.responseText);
 				}
 			}
         }
     });		
 	var zjfjGrid = new Ext.grid.GridPanel({
         store: myuserStore,
         region: 'center',
         view:new Ext.grid.GridView({
 				//forceFit:true,
 				//autoFill:true,
 				enableRowBody:true
 		}),
 		stripeRows: true,
         cm: colModel,
         //sm:csm,
 		loadMask: {msg:'正在加载数据，请稍侯……'},
 		bodyStyle:'width:100%',
         border:true,
         anchor:'100% 100%',
 	    margins: '0 0 0 0',
 		cmargins: '0 0 0 0',        
         frame:false,
 		iconCls:'icon-grid',
         title:'',
         listeners:{
 			rowdblclick:function(g, rowIndex, e){
 			}
 		},
         bbar: new Ext.PagingToolbar({
 				pageSize: 50,
 				store: myuserStore,
 				displayInfo: true
 		})
     });
	
	var zhcx_dialog = new Gnt.ux.ZhcxDialog({
		pzlb : form30033.pzlb,
		grid:zjfjGrid,
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
    		newYewu: function(){
    			
    		},
    		items:[
    			zjfjGrid
    		]
    		,buttons:[
    			{
    				text:'关闭',
    				minWidth:60,
    				handler:function(){
    					window.parent.closeWorkSpace();
    				}
    			},{
    				text:'返回',
    				minWidth:60,
    				handler:function(){
    					Ext.getCmp('card').getLayout().setActiveItem(0);
    				}
    			}
    		]
    	});
	
	
	
	//释放iframe占用资源
	function fixIFrame(o, p){
		var iFrame = p.getEl().dom; 
		if (iFrame.src) {
			iFrame.src = "javascript:false"; 
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
        	items:[p1,p2]
        }
    });
	
  /*  if(getQueryParam("ai")){
    	Ext.getCmp('card').getLayout().setActiveItem(getQueryParam("ai"));
    	tabs.setActiveTab(0);
    }else{
    	Ext.getCmp('nextId').setVisible(false);
    }
	if(getQueryParam("jumpToCzrkcx")&& getQueryParam("jumpToCzrkcx")!=""){
		queryFlag = true;
		Ext.getCmp("queryId").handler();
	}    
	//户政业务处理
	if(hzywid && hzywid!=""){
		Gnt.makeHzyw({
			hzywid: hzywid,
			pch:pch,
			cmpids: ['queryId'],
			callback:function(jo, jolist){
				//成功处理返回，合并数据
				hzywjo_list =[];
				hzywjo_list.push(jo);
				Ext.each(jolist,function(d){
					hzywjo_list.push(d);
				});
				hzywjo = jo;
				
				form20000.getForm().findField("gmsfhm").setValue(jo.bsqrsfz);
				
				Ext.getCmp("queryId").handler();
				
			}
		});
	}*/
    
	
	
	
});