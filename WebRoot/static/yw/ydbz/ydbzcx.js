var zplsid=null;
var zpxx=null;
var cxCount = 5;
var slzt ='';
var selectedSbxxid ='';
var htmlStrStart = '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="cx/hjjbxx/ckxx/queryXxxx?goto=';
var htmlStrEnd = '"></iframe>';
//'?rynbid='+ getRynbid(rynbid) + "&goto=queryHdxx&hhnbid=" + getQueryParam("hhnbid") + '"> </iframe>';

var selectRynbid = null;
var selectHhnbidselectHhnbid = null;

Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("30037","30035",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX','CS_EDZZZLX'],function(){});
	
	/**
		点击下一步后显示的内容
		暂时用户成员信息代替
	 */
	var hcyGrid = new Gnt.ux.SjpzGrid({
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
	});
	
	
	
	var form30035 = new Gnt.ux.SjpzForm({
		pzlb: '30035',//30035
		//url: 'gl/bbgl/dssj/queryTjbbsjb',
		title:'',
		cols:2,
		formType:'query'
	});
	
	var p1 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[{
			id:"nextId",
			border:false,
			frame: false,
			region:'center',
			visible:false,
//        	height: 300,
			items:[hcyGrid]
		},{
			id:"centerId",
			border:false,
			frame: false,
			region:'center',
			layout:'border',
//        	height: 300,
			items:[{
					id: "panelHtmlId" ,
					html: '<div class="text" style="text-align:center;"><br /><h2>异地申请查询</h2></div>',
					height: 80,
					region:'north',
					bodyStyle:'padding:15px'
				},
				form30035
			]
		},
		{
				id:"southId",
	        	region:'south',
	        	height:100,
	            border:false,
	            frame:false,
	            items:[{
	            	title: '查询范围',
		            xtype: 'fieldset',
		            region:'center',
		            layout:'column',
		            defaults:{
		            	columnWidth: .2,
		            	fieldLabel: '',
		            	name: 'zt'
		            },
		            items: [{
		            	id:'c11',
		            	xtype:'checkbox',
		            	boxLabel: '作废',
		            	checked:true,
		            	inputValue : "1",  
        				listeners:{
        					'check': function(obj,checked){
        						if(checked){
        							cxCount ++;
        							if(cxCount > 0){
        								Ext.getCmp('queryId').setDisabled(false);
        							}
        						}else{
        							cxCount --;
        							
        							if(cxCount == 0){
        								Ext.getCmp('queryId').setDisabled(true);
        							}
        						}
        					}
        				}
		            },{
		            	id:'c12',
		            	xtype:'checkbox',
		            	boxLabel: '受理中（未上报）',
		            	checked:true,
		            	inputValue : "2",  
        				listeners:{
        					'check': function(obj,checked){
        						if(checked){
        							cxCount ++;
        							
        							if(cxCount > 0){
        								Ext.getCmp('queryId').setDisabled(false);
        							}
        						}else{
        							cxCount --;
        							
        							if(cxCount == 0){
        								Ext.getCmp('queryId').setDisabled(true);
        							}
        						}
        					}
        				}
		            },{
		            	id:'c13',
		            	xtype:'checkbox',
		            	boxLabel: '受理中（已上报）',
		            	checked:true,
		            	inputValue : "3",  
        				listeners:{
        					'check': function(obj,checked){
        						if(checked){
        							cxCount ++;
        							
        							if(cxCount > 0){
        								Ext.getCmp('queryId').setDisabled(false);
        							}
        						}else{
        							cxCount --;
        							
        							if(cxCount == 0){
        								Ext.getCmp('queryId').setDisabled(true);
        							}
        						}
        					}
        				}
		            },{
		            	id:'c14',
		            	xtype:'checkbox',
		            	boxLabel: '受理中（不合格证件）',
		            	checked:true,
		            	inputValue : "4",  
        				listeners:{
        					'check': function(obj,checked){
        						if(checked){
        							cxCount ++;
        							
        							if(cxCount > 0){
        								Ext.getCmp('queryId').setDisabled(false);
        							}
        						}else{
        							cxCount --;
        							
        							if(cxCount == 0){
        								Ext.getCmp('queryId').setDisabled(true);
        							}
        						}
        					}
        				}
		            
		            },{
		            	id:'c15',
		            	xtype:'checkbox',
		            	boxLabel: '已领证',
		            	checked:true,
		            	inputValue : "5",  
        				listeners:{
        					'check': function(obj,checked){
        						if(checked){
        							cxCount ++;
        							
        							if(cxCount > 0){
        								Ext.getCmp('queryId').setDisabled(false);
        							}
        						}else{
        							cxCount --;
        							
        							if(cxCount == 0){
        								Ext.getCmp('queryId').setDisabled(true);
        							}
        						}
        					}
        				}
		            
		            
		            }]
				},{
					border:false,
					frame: false,
		        	region:'south',
		        	height: 40,
		        	bodyStyle: 'padding:10px',
					layout:'table',
					layoutConfig: {
			        	columns: 8
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
			        			text:'复杂条件',
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
				    	    	form:form30035,
				    	    	callback: function(){
				    	    		//Ext.getCmp("queryId").handler();
				    	    	}
				    	    }	]
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
			        				var data = form30035.getForm().getValues();
			        				
		        					if(Gnt.util.isEmpty(data)){
		        						showInfo("至少输入一个查询条件！");
		        						return;
		        					}
		        					data.log_code = "F1602";
									Ext.Msg.wait("正在执行查询，请稍后...");
			        				
			        				var store = ydbzcxGrid.store;
			        				store.removeAll();
			        				store.baseParams = data;
			        				applyParam(store);
			        				store.load({params:{start:0, limit:20}});
			        				
			        				/**
			        					改变颜色
			        				 */
			        				store.on('load',function(s,records){
			        					if(records.length>0){
		    								curIndex = 0;
		    								ydbzcxGrid.fireEvent("rowclick",ydbzcxGrid,curIndex);
		    								ydbzcxGrid.getSelectionModel().selectRange(curIndex,curIndex);
			        						Ext.getCmp('card').getLayout().setActiveItem(1);
			        					}else{
			        						showInfo("没有找到相关的人员资料，常住人口无法查询！");
			        					}
			        					Ext.Msg.hide();
			        				});
			        				
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
					
				}]
	        }
		]
	});
	var ydbzcxGrid = new Gnt.ux.SjpzGrid({
		border:false,
		frame:false,
		pkname: 'sbxxid',
		pzlb: '30035',
		url:'yw/ydbz/ydbzcx/queryYdbzxx.json',
		region:'center',
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
				slzt = selRes.data.slzt;
				selectedSbxxid = selRes.data.sbxxid;
				if(slzt=='00'){
					Ext.getCmp('xxzf').enable();
				}else{
					Ext.getCmp('xxzf').disable();
				}
			},
			rowdblclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				slzt = selRes.data.slzt;
				selectedSbxxid = selRes.data.sbxxid;
				if(slzt=='00'){
					Ext.getCmp('xxzf').enable();
					Ext.getCmp('xxzfNew').enable();
				}else{
					Ext.getCmp('xxzf').disable();
					Ext.getCmp('xxzfNew').disable();
				}
				if(selRes){
					Ext.getCmp('pic').items.items[0].body.update("<div id='_PHOTO_ID'/><IMG SRC='yw/ydbz/ydbzcx/img/render/" + selRes.data.zpid + "' width='100%' height='100%' />");
					Gnt.toFormReadyonly(ydbzcxForm);
					ydbzcxForm.getForm().loadRecord(selRes);
					Ext.getCmp('card').getLayout().setActiveItem(2);
					p3.doLayout();
					//p3.doLayout();
				}
    			
    			
			}
		}
	});	
	
	var zhcx_dialog = new Gnt.ux.ZhcxDialog({
		pzlb : form30035.pzlb,
		grid:ydbzcxGrid,
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
			data.log_code = "F1602";
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
		items:[ydbzcxGrid],
			buttons:[
				new Ext.Button({
					text:'证件重办',
					id:'zjcb',
					disabled:true,
					minWidth:80,
					handler:function(){
						
					}
				}),
				new Ext.Button({
					text:'信息作废',
					id:'xxzf',
					disabled:true,
					minWidth:80,
					handler:function(){
						if(selectedSbxxid){
							Gnt.submit(
								{
									sbxxid:selectedSbxxid}, 
									"yw/ydbz/ydbzcx/xxzf", 
									"正在作废该条信息，请稍后...", 
									function(jsonData,params){
										showInfo("信息作废成功！");
										ydbzcxGrid.store.reload(); 
								}
							);
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
	
	var ydbzcxForm = new Gnt.ux.SjpzForm({
		closable: false,
		region:'center',
		pzlb: '30035',
		labelWidth : 120,
		cols:2,
		//formType:'view',
		title:'',
		bindViewGrid:ydbzcxGrid
	});	
	var p3 = new Ext.Panel({
		layout:'border',
		items:[{
			id:'pic',
		    title: '',
		    region:'east',
		    margins: '5 0 0 0',
		    cmargins: '5 5 0 0',
		    width: 200,
		    layout:'table',
	    	layoutConfig: {
	    		columns: 1
	    	 },
			items:[
			{
				//id:'_PHOTO_ID',
				title: '',
				height:'100%',
				bodyStyle:'padding:10px 10px 10px 10px',
				html: '<div id="_PHOTO_ID">照片</DIV>',
				width:150,
				height:180,
				rowspan: 1,
				colspan:1
			},{
			html:'',
			bodyStyle:'padding:10px 25px 0px 20px',
			 	layout:'table',
			 	align:'center',
			 	border:false,
			 	frame:false,
			 	layoutConfig: {
			 		columns: 1
			 	}
		}]
		},{
		    title: '',
		    collapsible: false,
		    border:false,
		    frame:false,
		    region:'center',
		    //margins: '5 0 0 0',
		    items:[ydbzcxForm]
		}],
			buttons:[
				new Ext.Button({
					text:'证件重办',
					disabled:true,
					minWidth:80,
					handler:function(){
						
					}
				}),
				new Ext.Button({
					text:'领取单',
					minWidth:80,
					handler:function(){
						var arrayTemp=[];
						var o={};
						o.directTable="ydslsldjb";
						o.sbxxid =selectedSbxxid;
						arrayTemp.push(o);
						printfunction(0,arrayTemp);
					}
				}),
				new Ext.Button({
					text:'信息作废',
					id:'xxzfNew',
					disabled:true,
					minWidth:80,
					handler:function(){
						if(selectedSbxxid){
							Gnt.submit(
								{
									sbxxid:selectedSbxxid}, 
									"yw/ydbz/ydbzcx/xxzf", 
									"正在作废该条信息，请稍后...", 
									function(jsonData,params){
										showInfo("信息作废成功！");
										ydbzcxGrid.store.reload(); 
								}
							);
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
						Ext.getCmp('card').getLayout().setActiveItem(1);
					}
				})
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
        	items:[p1,p2,p3]//p1,p2,p3
        }
    });
	//form30035.fieldSetValue(form30035.getForm().findField("zzlx"),1);
	
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
	//Gnt.photo.setPhoto(null, true);
	Ext.getCmp('nextId').setVisible(false);
	function applyParam(store){
		
		/**
			申请状态
		 */
		var zfzt = '';
		if(Ext.getCmp('c11').getValue()){
			zfzt = '1';
		}
		var wsbzt = '';
		if(Ext.getCmp('c12').getValue()){
			wsbzt = '1';
		}
		var ysbzt = '';
		if(Ext.getCmp('c13').getValue()){
			ysbzt = '1';
		}
		var bhgzt = '';
		if(Ext.getCmp('c14').getValue()){
			bhgzt = '1';
		}
		var ylzzt = '';
		if(Ext.getCmp('c15').getValue()){
			ylzzt = '1';
		}
		
		Ext.apply(store.baseParams, {sqzt1:zfzt,sqzt2:wsbzt,sqzt3:ysbzt,sqzt4:bhgzt,sqzt5:ylzzt});
	}	
	
});