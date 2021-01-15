
var curIndex = -1;
var cxCount = 1;
var rynbid = null;

var selectRynbid = null;
var selectHhnbid = null;
var selectRyid = null;
var zpWindow = null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10019,20017",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	
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
			rowclick:function(g, rowIndex, e){}
		}
	});
	
	var form20017 = new Gnt.ux.SjpzForm({
		closable: false,
		pzlb: '20017',
		url: 'yw/queryRyxx',
		title:'',
		cols:2,
		formType:'query'
	});
	var rkxxGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		//region:'north',
		pzlb: '20000',
		title: '',
		url: 'cx/hjjbxx/ckxx/getXxxx.json',
		showPaging:false
	});	
	var form20000 = new Gnt.ux.SjpzForm({
		closable: false,
		region:'center',
		/*height:500,*/
		pzlb: '20000',
		labelWidth : 120,
		cols:2,
//		bindStore:xblryGrid.store,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:rkxxGrid,
		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
		changeDictCust:function(cmb,res){
			
			return;
		},
		title: ''
	});
	var newForm20000 = new Gnt.ux.SjpzForm({
		closable: false,
		region:'center',
		/*height:500,*/
		pzlb: '20000',
		labelWidth : 120,
		cols:2,
//		bindStore:xblryGrid.store,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:newRkxxGrid,
		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
		changeDictCust:function(cmb,res){
			
			return;
		},
		title: ''
	});
	var newRkxxGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		//region:'north',
		pzlb: '20000',
		title: '',
		url: 'cx/hjjbxx/ckxx/getXxxx.json',
		showPaging:false
	});	

	var p1_1 = new Gnt.ux.ZdycxPanel({
		pzlb:form20017.pzlb,
		region:'center',
		layout:'border',
		border:false,
		frame: false,
		height:600
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
				border:false,
				frame: false,
				region:'center',
				items:[{
					id:"centerId",
	    	        border:false,
	    	        frame:false,
					region:'south',
//					hidden:true,
					items:[{
						id: "panelHtmlId" ,
						html: '<div class="text" style="text-align:center;">人口基本信息查询</div>',
						height: 80,
						region:'north',
						bodyStyle:'padding:15px'
					},
					form20017
				]
				},
				{
					id:"p1_1",
	    	        border:false,
	    	        frame:false,
					region:'south',
					hidden:true,
					items:[p1_1]
				}]
			},
			
//			{
//				id:"centerId",
//				border:false,
//				frame: false,
//				region:'center',
//				layout:'border',
//	        	height: 600,
//				items:[{
//						id: "panelHtmlId" ,
//						html: '<div class="text" style="text-align:center;">人口基本信息查询</div>',
//						height: 80,
//						region:'north',
//						bodyStyle:'padding:15px'
//					},
//					form20017
//				]
//			},
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
			    	    	form:form20017,
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
		        				Ext.getCmp('centerId').show();
		        				Ext.getCmp('p1_1').doLayout();
		        				Ext.getCmp('p1_1').setVisible(false);
		        				Ext.getCmp('preId').setDisabled(true);
		        				Ext.getCmp('nexId').setDisabled(false);
		        				//zdycx_dialog.hide();
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
		        				p1_1.qybz = true;
		        				Ext.getCmp('centerId').hide();
		        				Ext.getCmp('p1_1').setVisible(true);
		        				Ext.getCmp('p1_1').doLayout();
		        				Ext.getCmp('preId').setDisabled(false);
		        				Ext.getCmp('nexId').setDisabled(true);
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
		        				curIndex = 0;
		        				var data = form20017.getForm().getValues();
								if(Gnt.util.isEmpty(data)){
									showInfo("至少输入一个查询条件！");
									return;
								}
								data.log_code = "F1201";
								Ext.Msg.wait("正在执行查询，请稍后...");
								var ary = p1_1.xszdGrid.store.data.items;
								var pxary = p1_1.pxzdGrid.store.data.items;//length 长度;
								if(p1_1.qybz){
									rkjbxxGrid.reconfigure(
											rkjbxxGrid.store,new Ext.grid.ColumnModel(Gnt.ZdyColumn(ary)));
									rkjbxxGrid.pxary = pxary;
								};
								var header=[];
								var shuxing = [];
								var zdyValueKey = Gnt.zdyValueKey(ary);
								rkjbxxGrid.zdyValueKey = Gnt.zdyValueKey(ary);
		        				var store = rkjbxxGrid.store;
		        				store.removeAll();
		        				store.baseParams = data;
		        				rkjbxxGrid.type = 'getRkxx';
								var config=rkjbxxGrid.colModel.config;
								Ext.each(config,function(r){
									header.push(r.header);
									shuxing.push(r.dataIndex);
								});
								store.baseParams.header = encodeURI(header);
								store.baseParams.shuxing = encodeURI(shuxing);
								store.baseParams.zdyValueKey = Ext.encode(zdyValueKey);
								store.baseParams.daochuFlag = 'getRkxx';
		        				applyParam(store,data);
		        				store.load({params:{start:0, limit:20}});
		        				Ext.each(pxary, function(pxObj){
		        					store.sort(pxObj.data.fieldname, 'ASC');
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
	        }
		]
	});
	
	var zpWindow = null;
	var rkjbxxGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		pzlb: '20017',
		url:'cx/hjjbxx/rkjbxxcx/getRkxx.json',
		region:'center',
		dcFlag:true,
		loadCallback: function(res, store, bind_grid){
			Ext.Msg.hide();
			if(res.length>0){
				Ext.getCmp('card').getLayout().setActiveItem(1);
			}else{
				showInfo("未查到有关信息！");
			}
		},
		callCellmeta:function(r){
			if(r.data.ryzt=='0'){
				return '#000000';
			}
			else if(r.data.ryzt=='1'){
				return '#C41A11';
			}
			else {
				return '#20BBAF';
			}
		},
//		height:500,
		title: '',
//		showPaging:true,
		listeners:{
			rowclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				//alert(selRes.data.ryzt);
				selectRynbid = selRes.data.rynbid;
    			selectHhnbid = selRes.data.hhnbid;
    			selectRyid = selRes.data.ryid;
				var temp=selRes.data.ryzt;
				setColor(temp);
				//Ext.getCmp('ryzt1').setValue(selRes.data.ryzt);
				//Ext.getCmp('ryzt2').setValue(selRes.data.ryzt);
				/*
				Gnt.photo.setPhoto(null, true);
				Gnt.photo.setPhoto(selRes, true);
				*/
				
				var zpid = selRes.data.zpid;
				if(zpid &&　zpid != 0){
					Ext.getCmp('p2Img').body.update("<IMG SRC='yw/common/img/render/" + zpid + "' width='100%' height='100%' />");
				}else{
					Ext.getCmp('p2Img').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
				}
				
				(function(){
					zpWindow = new Gnt.ux.SelectZpAll({
						ryid:selRes.data.ryid
					});
				}).defer(400);
			},
			rowdblclick:function(grid,row){
				selRes = grid.store.getAt(row);
    			rynbid = selRes.data.rynbid;
			    hhnbid = selRes.data.hhnbid;
			    selectRyid = selRes.data.ryid;
			    Gnt.toFormReadyonly(form20000);
			    
			    var zpid = selRes.data.zpid;
			    if(zpid &&　zpid != 0){
			    	Ext.getCmp('p4Img').body.update("<IMG SRC='yw/common/img/render/" + zpid + "' width='100%' height='100%' />");
			    }else{
			    	Ext.getCmp('p4Img').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
			    }
			    
			    Ext.getCmp('card').getLayout().setActiveItem(3);
			    form20000.doLayout();
			    
//				v.doLayout();
				var store = rkxxGrid.store;
				store.removeAll();
				store.baseParams = {
						rynbid:rynbid,
						ryid:selectRyid,
						config_key:'queryPoHJXX_CZRKJBXXB4',
						start:0,
						limit:20
					};
				store.load();
				store.on("load",function(store) {
					if(store.data.length > 0){
						curIndex = 0;
						/**
							0 : 正常
							1 : 死亡
							2 : 迁出
							3 : 服兵役
							4 : 出国境定居
							6 : 失踪
							7 : 删除
							8 : 注销
							9 : 其它
						 */
						if(store.getCount() > 0){
//							if(rkxxGrid.getSelectionModel().getCount() ==1){
								
								setColor(store.getAt(0).get("ryzt"));
								
//							}
						}
						
						/**
							往表单设值
						 */
						var pkvalue = store.getAt(0).data[form20000.bindStore.pkname];
						var re = form20000.bindStore.getById(pkvalue);
						if(re){
							form20000.getForm().loadRecord(re);
						}
						
					}
				},rkxxGrid); 
				
			}
		}
	});

	var zhcx_dialog = new Gnt.ux.ZhcxDialog({
		pzlb : form20017.pzlb,
		grid:rkjbxxGrid,
		callback: function(where){
			var ary = p1_1.xszdGrid.store.data.items;
			var pxary = p1_1.pxzdGrid.store.data.items;//length 长度;
			if(p1_1.qybz){
				rkjbxxGrid.reconfigure(
						rkjbxxGrid.store,new Ext.grid.ColumnModel(Gnt.ZdyColumn(ary)));
				rkjbxxGrid.pxary = pxary;
			};
			rkjbxxGrid.zdyValueKey = Gnt.zdyValueKey(ary);
			var grid = this.grid;
			var store = grid.store;
			var length = 0;
			var data = {"where": where};
			store.removeAll();
			data.pzlb = grid.store.pzlb;
			data.config_key = this.config_key;
			data.log_code = "F1201";
			
			//add by zjm 20201223 复杂查询传入列表头 属性 自定义字段，用于导出功能使用
			var header=[];
			var shuxing = [];
			var zdyValueKey = Gnt.zdyValueKey(ary);
			grid.type = 'getRkxx';
			Ext.each(ary,function(r){
				header.push(r.data.displayname);
				shuxing.push(r.data.fieldname);
			});
			data.header = encodeURI(header);
			data.shuxing = encodeURI(shuxing);
			data.zdyValueKey = Ext.encode(zdyValueKey);
			data.daochuFlag = 'getRkxx';
			
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
	
	var tb = new Ext.Toolbar({
	 	frame:false,
	 	border:false,
	 	region:'center',
//    	width : 300,
    	items : [{
	        text: '<<',
        	tooltip :'第一条记录',
        	handler : function(dp, date){
        		rkjbxxGrid.fireEvent("rowclick",rkjbxxGrid,0);
        		rkjbxxGrid.getSelectionModel().selectRange(0,0);
			}
	    },{
	        text: '<',
        	tooltip :'上一条记录',
        	handler : function(dp, date){
        		if(curIndex > 0){
        			curIndex--;
        			rkjbxxGrid.fireEvent("rowclick",rkjbxxGrid,curIndex);
        			rkjbxxGrid.getSelectionModel().selectRange(curIndex,curIndex);
        		}
			}
	    },{
	        text: '>',
        	tooltip :'下一条记录',
        	handler : function(dp, date){
        		if(curIndex < (rkjbxxGrid,rkjbxxGrid.store.getCount() - 1)){
        			curIndex++;
        			rkjbxxGrid.fireEvent("rowclick",rkjbxxGrid,curIndex);
        			rkjbxxGrid.getSelectionModel().selectRange(curIndex,curIndex);
        		}
			}
    	},{
	        text: '>>',
        	tooltip :'最后一条记录',
        	handler : function(dp, date){
        		rkjbxxGrid.fireEvent("rowclick",rkjbxxGrid,rkjbxxGrid.store.getCount() - 1);
        		rkjbxxGrid.getSelectionModel().selectRange(rkjbxxGrid.store.getCount() - 1,rkjbxxGrid.store.getCount() - 1);
			}
    	},{
    		iconCls:'icon-exit',
        	tooltip :'保存查询结果',
        	handler : function(dp, date){
				
			}
    	}, 
    	"-",
    	{
	        icon: "images/save.jpg",
	        scale:'large',
	        minWidth:30,
	        width:'100%',
	        height:10,
	        iconAlign: 'bottom',
	        bodyStyle: 'padding:0px 0px 0px 0px ',
        	tooltip :'打印设置',
        	handler : function(dp, date){
				
			}
    	},{
	        icon: "images/save.jpg",
	        scale:'large',
	        minWidth:30,
	        width:'100%',
	        height:10,
	        iconAlign: 'bottom',
	        bodyStyle: 'padding:0px 0px 0px 0px ',
        	tooltip :'打印预览',
        	handler : function(dp, date){
				
			}
    	},{
	        icon: "images/save.jpg",
	        scale:'large',
	        minWidth:30,
	        width:'100%',
	        height:10,
	        iconAlign: 'bottom',
	        bodyStyle: 'padding:0px 0px 0px 0px ',
        	tooltip :'打印',
        	handler : function(dp, date){
				
			}
    	},{
	        icon: "images/save.jpg",
	        scale:'large',
	        minWidth:30,
	        width:'100%',
	        height:10,
	        iconAlign: 'bottom',
	        bodyStyle: 'padding:0px 0px 0px 0px ',
        	tooltip :'修改显示字段',
        	handler : function(dp, date){
				
			}
    	}, 
    	"-",
    	{
	        icon: "images/save.jpg",
	        scale:'large',
	        minWidth:30,
	        width:'100%',
	        height:10,
	        iconAlign: 'bottom',
	        bodyStyle: 'padding:0px 0px 0px 0px ',
        	tooltip :'返回',
        	handler : function(dp, date){
        		Ext.getCmp('card').getLayout().setActiveItem(0);
			}
    	}]
    });

	var p2 = new Ext.Panel({
		layout:'border',
		tbar: tb,
		newYewu: function(){
			
		},
		items:[
			rkjbxxGrid,{
				id:"p2SouthId",
	        	region:'south',
	        	height:40,
	            border:false,
	            frame:false,
	            items:[{
	            	title: '',
		            xtype: 'fieldset',
		            autoHeight: true,
		            items: [{
		            	border:false,
						frame: false,
			        	region:'center',
//			        	height: 40,
			        	//					上	右	下	左
//			        	bodyStyle: 'padding:0px 0px 10px 0px ',
			        	layout:'table',
						layoutConfig: {
				        	columns: 30
				        },
						items: [
							{
								width:110,
								border:false,
								frame: false,
//								color:"#FF0000",
								bodyStyle: 'padding:10px',
								html:'<font color=\'#FF0000\'">人员状态</font>'
							},{
		        				id:'ryzt1',
				 		 		bodyStyle:'padding:5px 30px 5px 0px',
				 		 		html: '正常'
							},{
								border:false,
								frame: false,
								width:10
							},{
		            			border:false,
		            			frame: false,
		            			items:[new Ext.Button({
		            				id:'renzhuisu',
		            				text:'人追溯',
		            				minWidth:60,
		            				handler:function(){
		            					if(rkjbxxGrid.getStore().getCount() > 0){
		            						if(rkjbxxGrid.getSelectionModel().getCount() ==1){
		            							rynbid = rkjbxxGrid.getSelectionModel().getSelections()[0].data.rynbid;
			            						Gnt.toFormReadyonly(newForm20000);
		            							var store4 = newRkxxGrid.store;
		            							store4.removeAll();
		            							store4.baseParams = {
		            									rynbid:rynbid,
		            									config_key:'queryPoHJXX_CZRKJBXXB7',
		            									start:0,
		            									limit:20
		            								};
		            							store4.load();
		            							store4.on("load",function(store) {
		            								if(store.data.length > 0){
		            									var re = store.getAt(0);
		            									/**
		            										往表单设值
		            									 */
		            									if(re){
		            										newForm20000.getForm().loadRecord(re);
		            									}
		            									var zpid = re.data.zpid;
		            									if(zpid &&　zpid != 0){
		            										Ext.getCmp('p3Img').body.update("<IMG SRC='yw/common/img/render/" + zpid + "' width='100%' height='100%' />");
		            									}else{
		            										Ext.getCmp('p3Img').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
		            									}
//		            									zpWindow = new Gnt.ux.SelectZpAll({
//		            										ryid:selRes.data.ryid
//		            									});
					            						Ext.getCmp('card').getLayout().setActiveItem(2);
					            						newForm20000.doLayout();
		            								}
		            							},newRkxxGrid); 
		            						}else{
		            							alert("请选择需要查询的人员!");
		            						}
		            					}else{
		            						alert("请先执行查询!");
		            					}
		            				}
		            			})]
		            		},{
								border:false,
								frame: false,
								width:10
							},{
		            			border:false,
		            			frame: false,
		            			items:[new Ext.Button({
		            				id:'close',
		            				text:'关闭',
		            				minWidth:60,
		            				handler:function(){
		            					window.parent.closeWorkSpace();
		            				}
		            			})]
		            		},{
								border:false,
								frame: false,
								width:10
							},{
		            			border:false,
		            			frame: false,
		            			items:[new Ext.Button({
		            				id:'return',
		            				text:'返回',
		            				minWidth:60,
		            				handler:function(){
		            					Ext.getCmp('card').getLayout().setActiveItem(0);
		            				}
		            			})]
		            		}]
		            	}]
		            }]

			},{
			    title: '',
			    region:'east',
			    margins: '5 0 0 0',
			    cmargins: '5 5 0 0',
			    width: 175,
			    minSize: 100,
			    maxSize: 250,
			    items: [
					{
        				id:'ryzt2',
		 		 		title: '',
		 		 		bodyStyle:'padding:10px 25px 10px 20px',
		 		 		html: '<font color=\'#714439\'">人员状态<br />正常</font>',
				 		height:'100%',
						rowspan: 1,
						colspan:1
					},{
						id:'p2Img',
	    		 		title: '',
	    		 		height:'100%',
	    		 		bodyStyle:'padding:10px 10px 10px 10px',
	    				width:150,
	    				height:180,
	    				rowspan: 1,
	    				colspan:1
					},{
		    			columnWidth: .01,
						border:false,
						frame: false
		    		},{
            			border:false,
            			frame: false,
            			items:[new Ext.Button({
            				id:'allPhoto',
            				text:'所有照片',
            				minWidth:60,
            				handler:function(){
            					if(rkjbxxGrid.getSelectionModel().getCount() ==1){
            						zpWindow.show();
            					}else{
	        						alert("请先选择一条有效数据!");
	        					}
            				}
            			})]
            		}]
			}
		]
	});
	
	var p3 = new Ext.Panel({
		layout:'border',
		items:[newForm20000,{
			id:"p3WestId",
        	region:'west',
        	width:180,
            border:false,
            frame:false,
            items: [{
            	id:"p3WestCenterId",
            	region:'center',
            	height:300,
                border:false,
                frame:false
            },{
    			region:'south',
    			layout:'table',
    			border:false,
                frame:false,
    			layoutConfig: {
    				columns: 1
    			},
    			items: [{
					id:'p3Img',
    		 		title: '',
    		 		height:'100%',
    		 		bodyStyle:'padding:10px 10px 10px 10px',
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
    				},
    				items:[
    					new Ext.Button({
    						text:'所有照片',
    						id:'syzp',
    						minWidth:100,
    						handler:function(){
    							zpWindow.show();
    						}
    					}),{
    						height:3,
    						border:false,
    						frame:false
    					},new Ext.Button({
    						text:'不显示照片',
    						id:'bxszp',
    						minWidth:100,
    						handler:function(){
    							Ext.getCmp('p3Img').hide();
    							Ext.getCmp('syzp').hide();
    							Ext.getCmp('bxszp').hide();
    							Ext.getCmp('xszp').show();
    						}
    					}),{
    						height:3,
    						border:false,
    						frame:false
    					},new Ext.Button({
    						text:'显示照片',
    						id:'xszp',
    						hidden:true,
    						minWidth:100,
    						handler:function(){
    							Ext.getCmp('p3Img').show();
    							Ext.getCmp('syzp').show();
    							Ext.getCmp('xszp').hide();
    							Ext.getCmp('bxszp').show();
    						}
    					})
    				]
    			}]
    		}]
		},{
			region:'south',
			width: '100%', 
	        border:false,
	        frame:false,
	        buttons:[
		        new Ext.Button({
		        	text:'关闭',
		        	minWidth:100,
		        	handler:function(){
		        		window.parent.parent.closeWorkSpace();
		        	}
		        }),
		        new Ext.Button({
		        	text:'返回',
		        	minWidth:100,
		        	handler:function(){
		        		Ext.getCmp('card').getLayout().setActiveItem(1);
		        	}
		        })
	        ]
	    
	    }]
	});
	var jsryz_dialog = new Gnt.ux.JsryzlDialog({
		callback: function(type,data){
			//解锁后续操作
			var subdata = {
        			rynbid:rynbid,
        			rysdzt:"0",
        			jssdyy:data.jsryzlyy
        		};
        		
        		Gnt.submit(
        			subdata, 
        			"cx/hjjbxx/ckxx/processCzrkzt", 
        			"正在解锁人员资料，请稍后...", 
        			function(jsonData,params){
        				showInfo("人员(ID=" + rkxxGrid.store.getAt(0).data.ryid + ",身份证=" + rkxxGrid.store.getAt(0).data.gmsfhm + ") 成功解锁!");
        			}
        		);
		}
	});	
	var sdryz_dialog = new Gnt.ux.SdryzlDialog({
		callback: function(type,data){
			//锁定后续操作
			var subdata = {
        			rynbid:rynbid,
        			rysdzt:"6",
        			jssdyy:data.sdryzlyy
        		};
        		
        		Gnt.submit(
        			subdata, 
        			"cx/hjjbxx/ckxx/processCzrkzt", 
        			"正在锁定人员资料，请稍后...", 
        			function(jsonData,params){
        				showInfo("人员(ID=" + rkxxGrid.store.getAt(0).data.ryid + ",身份证=" + rkxxGrid.store.getAt(0).data.gmsfhm + ") 成功锁定!");
        			}
        		);
		}
	});
	
	var p4 = new Ext.Panel({
		layout:'border',
		items:[form20000,{
	    	region:'east',
	    	 width:180,
	    	 layout:'table',
	    	 layoutConfig: {
	    		columns: 1
	    	 },
	    	 items: [{
	    		 		id:'p4RyztId',
		 		 		title: '',
		 		 		bodyStyle:'padding:10px 25px 10px 20px',
		 		 		html: '人员状态<br />正常',
				 		height:'100%',
						rowspan: 1,
						colspan:1
			    	},{
						id:'p4Img',
	    		 		title: '',
	    		 		height:'100%',
	    		 		bodyStyle:'padding:10px 10px 10px 10px',
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
		   	    	 	},
		   	    	 	items:[
			   	    	 	new Ext.Button({
								text:'所有照片',
								minWidth:100,
								handler:function(){
									zpWindow.show();
								}
							})
			    	 ]
	    	    }]
	    },{

			region:'south',
			width: '100%', 
	        border:false,
	        frame:false,
	        buttons:[
		        new Ext.Button({
		        	text:'解锁人员资料',
		        	minWidth:100,
		        	disabled:ryxxjsFlag>0?false:true,
		        	handler:function(){
		        		jsryz_dialog.show();
		        	}
		        }),
		        new Ext.Button({
		        	text:'锁定人员资料',
		        	minWidth:100,
		        	disabled:ryxxsdFlag>0?false:true,
		        	handler:function(){
		        		sdryz_dialog.show();
		        	}
		        }),
		        new Ext.Button({
		        	text:'关闭',
		        	minWidth:100,
		        	handler:function(){
		        		window.parent.parent.closeWorkSpace();
		        	}
		        }),
		        new Ext.Button({
		        	text:'返回',
		        	minWidth:100,
		        	handler:function(){
		        		Ext.getCmp('card').getLayout().setActiveItem(1);
		        		
		        	}
		        })
	        ]
	    
	    }]
	});
	
	//释放iframe占用资源
	function fixIFrame(o, p){
		var iFrame = p.getEl().dom; 
		if (iFrame.src) {
			iFrame.src = "javascript:false"; 
		}
	}		
	function applyParam(store,data){
		
		/**
			是否显示记录总数
		 */
		var zs = '';
//		if(Ext.getCmp('c31').getValue()){
//			zs = '1';
//		}
		var _start_csrq = null;
		var _start_csrq1 = null;
		var _end_csrq = null;
		var _end_csrq1 = null;
		if(data._start_csrq&&data._start_csrq.length==2){
			//_start_csrq = data._start_csrq[0];
			_start_csrq1 = data._start_csrq[1];
		}
		if(data._end_csrq&&data._end_csrq.length==2){
			//_end_csrq = data._end_csrq[0];
			_end_csrq1 = data._end_csrq[1];
		}
		Ext.apply(store.baseParams, {_start_csrq1:_start_csrq1,_end_csrq1:_end_csrq1,nosjfw:'1',ryfw:'1',rkzt1:'',rkzt2:'',rkzt3:'',rkzt4:'',ryzl1:'',ryzl2:'',xszs:zs});//_start_csrq:start_csrq,_end_csrq:start_csrq,
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
        	items:[p1,p2,p3,p4]
        }
    });
	
    if(getQueryParam("ai")){
    	Ext.getCmp('card').getLayout().setActiveItem(2);
    	tabs.setActiveTab(0);
    }else{
    	Ext.getCmp('nextId').setVisible(false);
    }
    
    function setColor(temp){
    	if(0 == temp){
			Ext.getCmp('ryzt1').body.update("<font color='#FF0000'\"/>正常</font>");
			Ext.getCmp('ryzt2').body.update("<font color='#714439'\"/>人员状态<br />正常</font>");
			Ext.getCmp('p4RyztId').body.update("<font color='#714439'\"/>人员状态<br />正常</font>");
		}else if(1 == temp){
			Ext.getCmp('ryzt1').body.update("<font color='#FF0000'\"/>死亡</font>");
			Ext.getCmp('ryzt2').body.update("<font color='#714439'\"/>人员状态<br />死亡</font>");
			Ext.getCmp('p4RyztId').body.update("<font color='#714439'\"/>人员状态<br />死亡</font>");
		}else if(2 == temp){
			Ext.getCmp('ryzt1').body.update("<font color='#FF0000'\"/>迁出</font>");
			Ext.getCmp('ryzt2').body.update("<font color='#714439'\"/>人员状态<br />迁出</font>");
			Ext.getCmp('p4RyztId').body.update("<font color='#714439'\"/>人员状态<br />迁出</font>");
		}else if(3 == temp){
			Ext.getCmp('ryzt1').body.update("<font color='#FF0000'\"/>服兵役</font>");
			Ext.getCmp('ryzt2').body.update("<font color='#714439'\"/>人员状态<br />服兵役</font>");
			Ext.getCmp('p4RyztId').body.update("<font color='#714439'\"/>人员状态<br />服兵役</font>");
		}else if(4 == temp){
			Ext.getCmp('ryzt1').body.update("<font color='#FF0000'\"/>出国境定居</font>");
			Ext.getCmp('ryzt2').body.update("<font color='#714439'\"/>人员状态<br />出国境定居</font>");
			Ext.getCmp('p4RyztId').body.update("<font color='#714439'\"/>人员状态<br />出国境定居</font>");
		}else if(6 == temp){
			Ext.getCmp('ryzt1').body.update("<font color='#FF0000'\"/>失踪</font>");
			Ext.getCmp('ryzt2').body.update("<font color='#714439'\"/>人员状态<br />失踪</font>");
			Ext.getCmp('p4RyztId').body.update("<font color='#714439'\"/>人员状态<br />失踪</font>");
		}else if(7 == temp){
			Ext.getCmp('ryzt1').body.update("<font color='#FF0000'\"/>删除</font>");
			Ext.getCmp('ryzt2').body.update("<font color='#714439'\"/>人员状态<br />删除</font>");
			Ext.getCmp('p4RyztId').body.update("<font color='#714439'\"/>人员状态<br />删除</font>");
		}else if(8 == temp){
			Ext.getCmp('ryzt1').body.update("<font color='#FF0000'\"/>注销</font>");
			Ext.getCmp('ryzt2').body.update("<font color='#714439'\"/>人员状态<br />注销</font>");
			Ext.getCmp('p4RyztId').body.update("<font color='#714439'\"/>人员状态<br />注销</font>");
		}else if(9 == temp){
			Ext.getCmp('ryzt1').body.update("<font color='#FF0000'\"/>其它</font>");
			Ext.getCmp('ryzt2').body.update("<font color='#714439'\"/>人员状态<br />其它</font>");
			Ext.getCmp('p4RyztId').body.update("<font color='#714439'\"/>人员状态<br />其它</font>");
		}
    }
	
});