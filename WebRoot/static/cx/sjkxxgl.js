var selRes = null;
var selectedSelRes = null;
var selecteRowIndex = null;
Ext.onReady(function(){
	Ext.QuickTips.init();
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
	
	var form50006 = new Gnt.ux.SjpzForm({
		pzlb: '50006',
		url: 'yw/queryRyxx',
		title:'',
		cols:2,
		formType:'query'
	});
	
	var csm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var colModel = new Ext.grid.ColumnModel([
		{
			header: "受理机构",
	        dataIndex: "dwdm",
	        sortable: true,
			width: 150,
			renderer:function(value, cellmeta, record, rowIndex,columnIndex, store){
				if(value=='合计'){
					return value;
				}
				return "<a style='color:blue'>"+Gnt.ux.Dict.getDictLable("DWXXB", value, cellmeta, record, rowIndex,columnIndex, store)+"</a>";
			}
		},{
	        header: "应缴款总金额",
	        dataIndex: "yingjkje",
	        sortable: true,
			width: 100,
			renderer:function(value, cellmeta, record, rowIndex,columnIndex, store){
				if(record.data.dwdm=='合计'){
					return value;
				}else{
					return "<a style='color:blue'>"+value+"</a>";
				}
				
			}
	    },{
	        header: "已缴款总金额",
	        dataIndex: "yijkje",
	        sortable: true,
			width: 100,
			renderer:function(value, cellmeta, record, rowIndex,columnIndex, store){
				if(record.data.dwdm=='合计'){
					return value;
				}else{
					return "<a style='color:blue'>"+value+"</a>";
				}
				
			}
	    }
	]);
 	var myuserStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'cx/sjkxxgl/querySjkxx.json',
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        id:'id',
        totalProperty:'totalCount',
        fields: [
        	"dwdm",
			"yingjkje",
			"yijkje"
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
 	
	var sfxxbGrid = new Ext.grid.GridPanel({
        store: myuserStore,
        region: 'center',
        view:new Ext.grid.GridView({
				//forceFit:true,
				//autoFill:true,
				enableRowBody:true
		}),
		stripeRows: true,
        cm: colModel,
        sm:csm,
		loadMask: {msg:'正在加载数据，请稍侯……'},
		bodyStyle:'width:80%',
        border:true,
        anchor:'100% 100%',
	    margins: '0 0 0 0',
		cmargins: '0 0 0 0',        
        frame:false,
		iconCls:'icon-grid',
        title:'',
        listeners:{
			cellclick:function(g, rowIndex,cellIndex,e){
				selRes = g.store.getAt(rowIndex);
				var store = sfxxbInfoGrid.store;
				store.removeAll();
				store.baseParams =fs.getForm().getValues();
				var cm = {};
				var arr = Gnt.ux.Dict.getSjpzData(sfxxbInfoGrid.pzlb);
				if(arr instanceof Array){
					for(var i=0;i<arr.length;i++){
						var data = arr[i];
						var dsname = data.dsname;
						cm[data.fieldname]=dsname
					}
				}
				var header=[];
				var shuxing = [];
				sfxxbInfoGrid.type = 'fxjsktjcx';
				var config=sfxxbInfoGrid.colModel.config;
				Ext.each(config,function(r){
					header.push(r.header);
					shuxing.push(r.dataIndex);
				});
				store.baseParams.header = encodeURI(header);
				store.baseParams.shuxing = encodeURI(shuxing);
				store.baseParams.zdyValueKey = Ext.encode(cm);
				store.baseParams.daochuFlag = 'fxjsktjcx';
				store.baseParams.sffs =selRes.data.sffs;
				
				store.baseParams.dwdm =selRes.data.dwdm;
				store.baseParams.sffs =1;
				store.baseParams.jksj=selRes.data.jkrq;
				if(selRes.data.dwdm=="合计"){
					return;
				}
				//点击受理机构   
				if(cellIndex==0){
					//参数： 单位代码
					if(Gnt.util.endWith(selRes.data.dwdm,'000')){
						sfxxbGrid.store.baseParams.xzFlag = 1;
						sfxxbGrid.store.baseParams.dwdm =selRes.data.dwdm;
						sfxxbGrid.store.load({params:{start:0, limit:20}});
						return;
					}else{
						store.baseParams.dwdm =selRes.data.dwdm;
						store.load({params:{start:0, limit:20}});
					}
				}
				//点击应缴款总金额
				if(cellIndex==1){
					store.baseParams.yjk=1;
					store.load({params:{start:0, limit:20}});
				}
				//点击已缴款总金额
				if(cellIndex==2){
					store.baseParams.sjk=1;
					store.load({params:{start:0, limit:20}});
				}
				return;
				
			}
		},
        bbar: new Ext.PagingToolbar({
				pageSize: 20,
				store: myuserStore,
				displayInfo: true
		})
    });

	var sfxxbInfoGrid = new Gnt.ux.SjpzGrid({
		pkname: 'sfxxbid',
		pzlb: '50005',
		url:'cx/fxjsktj/queryFxjsktjInfo.json',
		region:'center',
		title: '',
		height:300,
		dcFlag:true,
		showPaging:true,
		loadCallback: function(res, store, bind_grid){
			Ext.Msg.hide();
			
			if(res.length>0){
				Ext.getCmp('card').getLayout().setActiveItem(1);
			}else{
				showInfo("未查到有关信息！");
			}
		},
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
				selectedSelRes = g.store.getAt(rowIndex);
				selecteRowIndex = rowIndex;
				var sfxxbid = selectedSelRes.data.sfxxbid;
				if(sfxxbid){
					Ext.getCmp('jfqdImg').body.update("<IMG SRC='yw/common/img/jfRender/" + sfxxbid + "' width='100%' height='100%' />");
				}else{
					Ext.getCmp('jfqdImg').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
				}
			},
			rowdblclick:function(g, rowIndex, e){
//				selectedSelRes = g.store.getAt(rowIndex);
//				selecteRowIndex = rowIndex;
//				var sfxxbid = selectedSelRes.data.sfxxbid;
//				var pkvalue = selectedSelRes.data[jssdForm.bindStore.pkname];
//				var re = jssdForm.bindStore.getById(pkvalue);
//				if(re){
//					jssdForm.getForm().loadRecord(re);
//					if(sfxxbid){
//						Ext.getCmp('sfqdInfoImg').body.update("<IMG SRC='yw/common/img/sfRender/" + sfxxbid + "' width='100%' height='100%' />");
//					}else{
//						Ext.getCmp('sfqdInfoImg').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
//					}
//				}
//				Gnt.toFormReadyonly(jssdForm);
			}
		}
	});	
	var fs = new Ext.form.FormPanel({
		id:'form1',
    	title:'',
    	region:'center',
    	anchor:'100% 100%',
    	buttonAlign:'right',
    	labelAlign:'right',
    	frame:false,
    	border:false,
        layout:'form',
        labelWidth:75,
       	items:[{
        		layout:'column',
    			frame:false,
    			border:false,
        		defaults:{
        			border:false,
        			frame:false
        		},
            	items:[{
		           	columnWidth:.5,
	    	        layout: 'form',
	    	        bodyStyle:'padding:0 0 0 0',
	        	    items: [{
	        	    	anchor:'100%',
	    				xtype:'search_combox',
	    				name:'dwdm',
	    				width: 150,
	    				fieldLabel:'受理单位',
	    				hiddenName:'dwdm',
	    				searchUrl : "dict/utils/searchXxb?visiontype=DWXXB"
					}]
				},{
		           	columnWidth:.5,
	    	        layout: 'form',
	    	        bodyStyle:'padding:0 0 0 0',
	        	    items: [{
	        	    	anchor:'100%',
	        	    	xtype:'search_combox',
	        	    	name:'jkyid',
	        	    	width: 150,
	        	    	fieldLabel:'缴款人',
	        	    	hiddenName:'jkyid',
	    				searchUrl:'dict/utils/searchXxb?visiontype=YHXXB'
	    				
					}]
				},{
		           	columnWidth:.5,
	    	        layout: 'form',
	    	        bodyStyle:'padding:0 0 0 0',
	        	    items: [{
						xtype:'datefield',
						anchor:'100%',
						format:'YmdHis',
						name:'jksj_start',
						fieldLabel:'缴款时间起'
					}]
				},{
		           	columnWidth:.5,
	    	        layout: 'form',
	    	        bodyStyle:'padding:0 0 0 0',
	        	    items: [{
						xtype:'datefield',
						format:'YmdHis',
						anchor:'100%',
						name:'jksj_end',
						fieldLabel:'缴款时间止'
					}]
				}]
		}]
	});
	var p1 = new Ext.Panel({
		layout:'border',
		items:[
			{
				border:false,
				frame: false,
				region:'north',
	        	height: 80,
	        	bodyStyle:'padding:10px',
				items:[/*{
					id: "panelHtmlId" ,
					html: '<div class="text" style="text-align:center;">非现金收款统计查询</div>',
					height: 80,
					region:'north',
					bodyStyle:'padding:15px'
				},*/{
					title: '',
				    collapsible: false,
				    region:'center',
				    layout:'border',
				    border:false,
				    frame:false,
				    margins: '0 0 0 0',
				    height:70,
		            items:[fs,{
		    		    title: '',
		    		    collapsible: false,
		    		    region:'east',
		    		    height:100,
		    		    border:false,
		    		    frame:false,
		    		    width:"15%",
		    		    margins: '0 0 0 0',
		    		    bodyStyle:'padding:10px 10px 10px 10px',
		    		    layout:'table',
		    		    layoutConfig: {
		    	    		columns: 1
		    	    	 },
		    		    labelWidth:40,
		    		    items:[new Ext.Button({
		    		    	id:'queryId',
		        			text:'查询',
		        			minWidth:100,
		        			handler:function(){
		        				var data = fs.getForm().getValues();
								if(Gnt.util.isEmpty(data)){
									showInfo("至少输入一个查询条件！");
									return;
								}
								var header=[];
								var shuxing = [];
		        				var store = sfxxbGrid.store;
		        				store.removeAll();
		        				store.baseParams = data;
		        				var config=sfxxbGrid.colModel.config;
								Ext.each(config,function(r){
									header.push(r.header);
									shuxing.push(r.dataIndex);
								});
//								var cm = {};
//								var arr = Gnt.ux.Dict.getSjpzData(sfxxbGrid.pzlb);
//								if(arr instanceof Array){
//									for(var i=0;i<arr.length;i++){
//										var data = arr[i];
//										var dsname = data.dsname;
//										cm[data.fieldname]=dsname
//									}
//								}
								store.baseParams.header = encodeURI(header);
								store.baseParams.shuxing = encodeURI(shuxing);
								store.baseParams.zdyValueKey = Ext.encode({'dwdm':'dwxxb','sffs':'cs_sffs','jkyid':'yhxxb'});
								store.baseParams.daochuFlag = 'querySjkxxgl';
		        				store.load({params:{start:0, limit:20}});
//		        				store.on("load",function(store) {  
//									if(store.data.length > 0){
//										//Ext.getCmp('card').getLayout().setActiveItem(1);
//										curIndex = 0;
//										sfxxbGrid.fireEvent("rowclick",sfxxbGrid,curIndex);
//										sfxxbGrid.getSelectionModel().selectRange(curIndex,curIndex);
//									}else{
//										alert(111);
//									}
//								},sfxxbGrid); 
		        			}
		        		}),{
		    		    	height:3,
		    		    	border:false,
		    		    	frame:false
		    		    },new Ext.Button({
		    				text:'导出',
		    				minWidth:100,
		    				handler:function(){
								if(sfxxbGrid.store.data.items){
									urlLink = "yw/common/downExcelZip?type=querySjkxxgl";
									Ext.Msg.wait("正在查询导出信息，请稍后...");
									 var elemIF = document.createElement("iframe");
				        		        elemIF.src = urlLink;//"yw/common/downZp?dcParams="+Ext.encode('111');
				        		        elemIF.style.display = "none";
				        		        elemIF.setAttribute('async', false);
				        		        document.body.appendChild(elemIF);
				        		        var timer = setInterval(function () {
				        		            var iframeDoc = elemIF.contentDocument || elemIF.contentWindow.document;
				        		            // Check if loading is complete
				        		            if (iframeDoc.readyState == 'complete' || iframeDoc.readyState == 'interactive') {
				        		                // do something
				        		            	Ext.Msg.hide();
				        		                clearInterval(timer);
				        		                return;
				        		            }
				        		        }, 500);
								}else{
									alert("没有符合条件的数据！");
								}
							}
		    		    }),{
		    		    	height:3,
		    		    	border:false,
		    		    	frame:false
		    		    }]
		    		}]
		        }
				]
			},
			{
				border:false,
				frame: false,
				region:'center',
				layout:'border',
//	        	height: 300,
				items:[sfxxbGrid
				]
			}
		]
	});
	
	var p2 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[
			sfxxbInfoGrid,{
			    title: '',
			    collapsible: false,
			    region:'east',
			    height:100,
			    border:false,
			    frame:false,
			    width:"20%",
			    margins: '0 0 0 0',
			    bodyStyle:'padding:10px 10px 10px 10px',
			    layout:'table',
			    layoutConfig: {
		    		columns: 1
		    	 },
			    labelWidth:40,
			    items:[{
			    	height:10,
			    	border:false,
			    	frame:false
			    },{
			    	id:'jfqdImg',
    		 		title: '',
    		 		height:'100%',
    		 		bodyStyle:'padding:10px 10px 10px 10px',
    				width:180,
    				height:180,
    				rowspan: 1,
    				colspan:1,
    				listeners: {
    	                render: function(c) {
    	                c.body.on('click', function() { 
    	                	if(selectedSelRes){
    	                		var sfqdZpWindow = new Gnt.ux.SelectSfpz({
    	                			returnTitleText:'缴费清单凭证'
    	                		});	
    	                		sfqdZpWindow.setData(selectedSelRes.data.sfxxbid,2);
    	                		sfqdZpWindow.show();
    	                	}
    	                	
    	                    });
    	                c.body.on('contextmenu',function(e){
    	                    e.preventDefault();//阻止浏览器默认右键菜单
    	                    customMenu.showAt(e.getXY());//展示自定义菜单
    	                    });
    	                },
    	                scope: this
    	            }
				},{
			    	height:10,
			    	border:false,
			    	frame:false
			    },{
			    	height:10,
			    	border:false,
			    	frame:false
			    }]
			}
			],
			buttons:[
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
	

	var jssdForm = new Gnt.ux.SjpzForm({
		closable: false,
		/*region:'north',*/
		height:500,
		pzlb: '50005',
		labelWidth : 120,
		cols:2,
//		bindStore:sfxxbGrid.store,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:sfxxbInfoGrid,
		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
		changeDictCust:function(cmb,res){
			
			return;
		},
		title: ''
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
        	items:[p1,p2]
        }
    });

});