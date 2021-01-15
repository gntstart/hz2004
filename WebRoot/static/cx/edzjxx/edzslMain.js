var selRes = null;
var rynbid = null;
var queryCount =4;
var htmlStrStart = '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="cx/edzjxx/edzslcx/querycdxx';
var htmlStrEnd = '"></iframe>';

var selectRynbid = null;
var selectHhnbid = null;
var selectHjywid = null;
var slh=null;
var nbslid = null;
var curIndex = 0;
Ext.onReady(function(){
	Ext.QuickTips.init();
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10019,30017",function(){}))
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
	
	var form30017 = new Gnt.ux.SjpzForm({
		pzlb: '30017',
		url: 'yw/queryRyxx',
		title:'',
		cols:2,
		formType:'query'
	});
	
	var numberFiled = new Ext.form.NumberField({
        width: 3,
        name: 'numberField',
        maxValue: 10000,
        minValue: 1,
        minText:'最小值不能小于1',
        nanText:'输入格式错误',
        hideTrigger: false,
        keyNavEnabled: false,
        allowDecimals:false
     
    });

	var p1_1 = new Gnt.ux.ZdycxPanel({
		pzlb:form30017.pzlb,
		region:'center',
		layout:'border',
		border:false,
		frame: false,
		height:410
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
						html: '<div class="text" style="text-align:center;">受理信息查询</div>',
						height: 80,
						region:'north',
						bodyStyle:'padding:15px'
					},
					form30017
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
			{
				id:"southId",
	        	region:'south',
	        	height:240,
	            border:false,
	            frame:false,
	            bodyStyle:'padding:30 0 0 10',
	            items:[{
	            	title: '查询范围',
//					columnWidth: 3,
		            xtype: 'fieldset',
		            autoHeight: true,
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
		            	name:'qr',
		            	inputValue : "1", 
		            	columnWidth: .1,
        				listeners:{
        					'check': function(obj,checked){
        						if(checked){
        							queryCount ++;
        							if(queryCount > 0){
        								Ext.getCmp('queryId').setDisabled(false);
        							}
        						}else{
        							queryCount --;
        							if(queryCount == 0){
        								Ext.getCmp('queryId').setDisabled(true);
        							}
        						}
        					}
        				}
		            },{
		            	id:'c12',
		            	xtype:'checkbox',
		            	boxLabel: '受理中(合格证件)',
		            	checked:true,
		            	name:'cs',
		            	inputValue : "2", 
		            	columnWidth: .2,
        				listeners:{
        					'check': function(obj,checked){
        						if(checked){
        							queryCount ++;
        							if(queryCount > 0){
        								Ext.getCmp('queryId').setDisabled(false);
        							}
        						}else{
        							queryCount --;
        							if(queryCount == 0){
        								Ext.getCmp('queryId').setDisabled(true);
        							}
        						}
        					}
        				}
		            },{
		            	id:'c13',
		            	xtype:'checkbox',
		            	boxLabel: '受理中(不合格证件)',
		            	checked:true,
		            	name:'qc',
		            	inputValue : "3",  
		            	columnWidth: .2,
        				listeners:{
        					'check': function(obj,checked){
        						if(checked){
        							queryCount ++;
        							if(queryCount > 0){
        								Ext.getCmp('queryId').setDisabled(false);
        							}
        						}else{
        							queryCount --;
        							if(queryCount == 0){
        								Ext.getCmp('queryId').setDisabled(true);
        							}
        						}
        					}
        				}
		            },{
		            	id:'c14',
		            	xtype:'checkbox',
		            	boxLabel: '已领证',
		            	checked:true,
		            	name:'sw',
		            	inputValue : "3",  
		            	columnWidth: .3,
        				listeners:{
        					'check': function(obj,checked){
        						if(checked){
        							queryCount ++;
        							if(queryCount > 0){
        								Ext.getCmp('queryId').setDisabled(false);
        							}
        						}else{
        							queryCount --;
        							if(queryCount == 0){
        								Ext.getCmp('queryId').setDisabled(true);
        							}
        						}
        					}
        				}
		            }]
				},{
		            xtype: 'fieldset',
		            autoHeight: true,
		            layout:'column',
		            defaults:{
		            	columnWidth: .2,
		            	fieldLabel: '',
		            	name: 'zt'
		            },
		            items: [{
		            	id:'c15',
		            	xtype:'checkbox',
		            	boxLabel: '查询尚未重办的不合格受理信息,包括的受理状态(10,12,17,23,25,30,32,62,72,82,91)',
		            	checked:false,
		            	name:'qr',
		            	inputValue : "2", 
		            	columnWidth: 5,
        				listeners:{
        					'check': function(obj,checked){
        					}
        				}
		            }]
				},{
		            xtype: 'fieldset',
		            autoHeight: true,
		            layout:'table',
		            defaults:{
		            	columnWidth: .2,
		            	fieldLabel: '',
		            	name: 'zt'
		            },
		            items: [{
		            	id:'c16',
		            	xtype:'checkbox',
		            	boxLabel: '查询(快证',
		            	checked:false,
		            	name:'qr',
		            	inputValue : "1", 
		            	columnWidth: 0.6,
        				listeners:{
        					'check': function(obj,checked){
        					}
        				}
		            },{
						title: "",
						width:80,				   
					    border: false,
					    items: [
					        {
					            //控件类型为numberfield
					            xtype: "numberfield",
					            //字段名称，绑定和获取数据的时候用到
					            id: "c17",
					            name: "c11",
					            //显示的标签
					            fieldLabel: "",
					            //控件的值
					            value: 7,
					            //能否为空，true为必填项，false为可以为空
					            allowBlank: false,
					            //最小值
					            minValue: 1,
					            //获得焦点时选中输入的内容
					            selectOnFocus: true,
					            //是否只读，true为只读，false为可编辑
					            readOnly: false,
					            //是否可用，true为不可用，false为可用
					            disabled: false,
					            //是否隐藏上下调节按钮
					            hideTrigger: false,
					            //键盘导航是否可用，启用后可以通过键盘的上下箭头调整数值
					            keyNavEnabled: true,
					            //鼠标滚轮是否可用，启用后可以通过滚动鼠标滚轮调整数值
					            mouseWheelEnabled: true,
					            //通过调节按钮、键盘、鼠标滚轮调节数值时的大小
					            step: 2				          
					        }
					    ],
					    listeners: {
					        change: function (me, newValue, oldValue, eOpts) {
					            Ext.MessageBox.alert("提示", newValue);
					        }}
					},{
						
						html: '天之内,慢证',
						width: 90,
						border: false
					},{
						title: "",
						width:80,				   
					    border: false,
					    items: [
					        {
					            //控件类型为numberfield
					            xtype: "numberfield",
					            //字段名称，绑定和获取数据的时候用到
					            id: "c18",
					            name: "c11",
					            //显示的标签
					            fieldLabel: "",
					            //控件的值
					            value: 20,
					            autoCreate: {tag: 'input', type: 'text', size: '20', autocomplete: 'off', maxlength: '2'},
					            //能否为空，true为必填项，false为可以为空
					            allowBlank: false,
					            //最小值
					            minValue: 1,
					            //获得焦点时选中输入的内容
					            selectOnFocus: true,
					            //是否只读，true为只读，false为可编辑
					            readOnly: false,
					            //是否可用，true为不可用，false为可用
					            disabled: false,
					            //是否隐藏上下调节按钮
					            hideTrigger: false,
					            //键盘导航是否可用，启用后可以通过键盘的上下箭头调整数值
					            keyNavEnabled: true,
					            //鼠标滚轮是否可用，启用后可以通过滚动鼠标滚轮调整数值
					            mouseWheelEnabled: true,
					            //通过调节按钮、键盘、鼠标滚轮调节数值时的大小
					            step: 2				          
					        }
					    ],
					    listeners: {
					        change: function (me, newValue, oldValue, eOpts) {
					            Ext.MessageBox.alert("提示", newValue);
					        }}
					},{						
						html: '天之内)要领证而证件尚未进行验收操作的受理信息',
						width: 380,
						border: false
					}]
				},{
					border:false,
					frame: false,
		        	region:'south',
		        	height: 40,
		        	bodyStyle: 'padding:10px 0px 0px 0px ',
					layout:'table',
					layoutConfig: {
			        	columns: 14
			        },
			    	items: [
						{
							border:false,
							frame: false,
			    		    items:[new Ext.Button({
			    		    	id:'zhId',
			        			text:'组合条件',
			        			minWidth:80,
			        			handler:function(){
			        				zhcx_dialog.show();
			        			}
			        		})]
			    		},{
			    			width:10,
							border:false,
							frame: false
			    		},{
							border:false,
							frame: false,
			    		    items:[{
				    	    	xtype: 'DkButton',
				    	    	form:form30017,
				    	    	callback: function(){
				    	    		//Ext.getCmp("queryId").handler();
				    	    	}
				    	    }]
			    		}/*,{
			    			width:10,
							border:false,
							frame: false
			    		},{
							border:false,
							frame: false,
			    		    items:[{
			    		    	id:'c19',
				        		xtype:'checkbox',
				        		boxLabel : "显示记录总数",  
				        		name : "jlzs",  
				        		inputValue : "1"  
				        	}]
			    		}*/,{
			    			width:10,
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
			        				Ext.getCmp('centerId').show();
			        				Ext.getCmp('p1_1').doLayout();
			        				Ext.getCmp('p1_1').setVisible(false);
			        				Ext.getCmp('preId').setDisabled(true);
			        				Ext.getCmp('nexId').setDisabled(false);
			        			}
			        		})]
			    		},{
			    			width:10,
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
			        				p1_1.qybz = true;
			        				Ext.getCmp('centerId').hide();
			        				Ext.getCmp('p1_1').setVisible(true);
			        				Ext.getCmp('p1_1').doLayout();
			        				Ext.getCmp('preId').setDisabled(false);
			        				Ext.getCmp('nexId').setDisabled(true);
			        			}
			        		})]
			    		},{
			    			width:10,
							border:false,
							frame: false
			    		},{
							border:false,
							frame: false,
			    		    items:[new Ext.Button({
			    		    	id:'queryId',
			        			text:'查询',
			        			minWidth:60,
			        			handler:function(){
			        				var data = form30017.getForm().getValues();

									if(Gnt.util.isEmpty(data)){
										showInfo("至少输入一个查询条件！");
										return;
									}
									//记录日志的操作码
									data.log_code = "F3401";
									if(Ext.getCmp('c16').getValue()){
										if(!Ext.getCmp('c17').getValue()||!Ext.getCmp('c18').getValue()){
											alert("快证和慢证天数都必填，请重新填写！");
											return;
										}
									}
									Ext.Msg.wait("正在执行查询，请稍后...");
									var ary = p1_1.xszdGrid.store.data.items;
									var pxary = p1_1.pxzdGrid.store.data.items;//length 长度;
									if(p1_1.qybz){
										slxxGrid.reconfigure(
												slxxGrid.store,new Ext.grid.ColumnModel(Gnt.ZdyColumn(ary)));
										slxxGrid.pxary = pxary;
									};
									slxxGrid.zdyValueKey = Gnt.zdyValueKey(ary);
									var header=[];
									var shuxing = [];
									var zdyValueKey = Gnt.zdyValueKey(ary);
									slxxGrid.type = 'edzslcx';
			        				var store = slxxGrid.store;
			        				store.removeAll();
			        				store.baseParams = data;
			        				var config=slxxGrid.colModel.config;
									Ext.each(config,function(r){
										header.push(r.header);
										shuxing.push(r.dataIndex);
									});
									store.baseParams.header = encodeURI(header);
									store.baseParams.shuxing = encodeURI(shuxing);
									store.baseParams.zdyValueKey = Ext.encode(zdyValueKey);
									store.baseParams.daochuFlag = 'edzslcx';
			        				applyParam(store);
			        				store.load({params:{start:0, limit:20}});
			        				Ext.each(pxary, function(pxObj){
			        					store.sort(pxObj.data.fieldname, 'ASC');
			    					});
			        				store.on("load",function(store) {  
			        					if(store.data.length > 0){
			        						curIndex = 0;
			        						slxxGrid.fireEvent("rowclick",slxxGrid,curIndex);
			        						slxxGrid.getSelectionModel().selectRange(curIndex,curIndex);
			        					}
			        				},slxxGrid); 
			        			}
			        		})]
			    		},{
			    			width:10,
							border:false,
							frame: false
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
	
	var slxxGrid = new Gnt.ux.SjpzGrid({
		pkname: 'nbslid',
		pzlb: '30017',
		url:'cx/edzjxx/edzslcx/getEdzslxx.json',
		region:'center',
		height:400,
		title: '',
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
		listeners:{
			rowclick:function(g, rowIndex, e){
				
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				
				selectRynbid = selRes.data.rynbid;
				selectHhnbid = selRes.data.hhnbid;
				selectHjywid = selRes.data.hjywid;
				
				var slzt = selRes.data.slzt;
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
				
				selectRynbid = selRes.data.rynbid;
				selectHhnbid = selRes.data.hhnbid;
				selectHjywid = selRes.data.hjywid;
				slh=selRes.data.slh;
				nbslid = selRes.data.nbslid;
				tabs.setActiveTab(1)
				tabs.setActiveTab(0);
				Ext.getCmp('card').getLayout().setActiveItem(2);
				
				/*var pkvalue = selRes.data[qczxForm.bindStore.pkname];
				var re = qczxForm.bindStore.getById(pkvalue);
				if(re){
					qczxForm.getForm().loadRecord(re);
				}
				Gnt.toFormReadyonly(qczxForm);
    			Ext.getCmp('card').getLayout().setActiveItem(2);*/
    			
    			v.doLayout();
    			
			}
		}
	});
	
	var zhcx_dialog = new Gnt.ux.ZhcxDialog({
		pzlb : form30017.pzlb,
		grid:slxxGrid,
		applyParam:function(){
			applyParam(this.grid.store);
		},
		callback: function(where){
			var ary = p1_1.xszdGrid.store.data.items;
			var pxary = p1_1.pxzdGrid.store.data.items;//length 长度;
			if(p1_1.qybz){
				slxxGrid.reconfigure(
						slxxGrid.store,new Ext.grid.ColumnModel(Gnt.ZdyColumn(ary)));
				slxxGrid.pxary = pxary;
			};
			slxxGrid.zdyValueKey = Gnt.zdyValueKey(ary);
			var grid = this.grid;
			var store = grid.store;
			var length = 0;
			var data = {"where": where};
			store.removeAll();
			data.pzlb = grid.store.pzlb;
			data.config_key = this.config_key;
			//记录日志的操作码
			data.log_code = "F3401";

			//add by zjm 20201223 复杂查询传入列表头 属性 自定义字段，用于导出功能使用
			var header=[];
			var shuxing = [];
			var zdyValueKey = Gnt.zdyValueKey(ary);
			grid.type = 'edzslcx';
			Ext.each(ary,function(r){
				header.push(r.data.displayname);
				shuxing.push(r.data.fieldname);
			});
			data.header = encodeURI(header);
			data.shuxing = encodeURI(shuxing);
			data.zdyValueKey = Ext.encode(zdyValueKey);
			data.daochuFlag = 'edzslcx';
			
			store.baseParams = data;
			if(this.applyParam){
				this.applyParam();
			}
			store.load({params:{start:0, limit:grid.store.pageSize}});
			Ext.Msg.wait("正在执行查询，请稍后...");
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
	var bzls_window = new Gnt.ux.BzlsDialog({
		//选择立户信息回调
		callback: function(optype, dwxxModify){
			
		}
	});
	var p2 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[
			slxxGrid
			],
			buttons:[
				new Ext.Button({
					id:'bbhg',
					text:'包不合格',
					minWidth:80,
					disabled:true,
					handler:function(){
						if(selRes){
							Gnt.submit(
									selRes.data,//{slh:selRes.data.slh}, 
									"cx/edzjxx/edzslcx/zjdbzf.json", 
									"正在证件打包作废业务，请稍后...", 
									function(jsonData,params){
										if(jsonData.list){
											showInfo("证件打包作废业务成功！");
											slxxGrid.store.reload(); 
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
					minWidth:80,
					disabled:true,
					handler:function(){
						if(selRes){
							Gnt.submit(
									{nbslid:selRes.data.nbslid}, 
									"cx/edzjxx/edzslcx/zjslzf.json", 
									"正在证件打包作废业务，请稍后...", 
									function(jsonData,params){
										if(jsonData.list){
											showInfo("证件打包作废业务成功！");
											slxxGrid.store.reload(); 
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
					minWidth:80,
					disabled:true,
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
						Ext.getCmp('card').getLayout().setActiveItem(0);
						p1.doLayout();
					}
				})
				]
	});
	

	var qczxForm = new Gnt.ux.SjpzForm({
		closable: false,
		/*region:'north',*/
		height:500,
		pzlb: '30017',
		labelWidth : 120,
		cols:2,
//		bindStore:slxxGrid.store,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:slxxGrid,
		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
		changeDictCust:function(cmb,res){
			
			return;
		},
		title: ''
	});
	
	var p3 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[
			{
				region:'north',
				border:false,
				frame: false,
	        	height: 40,
	        	bodyStyle: 'padding:10px 0px 0px 0px ',
				layout:'column',
		    	items: [
					{
		    			columnWidth: .01,
						border:false,
						frame: false
		    		},{
						border:false,
						frame: false,
		    		    items:[new Ext.Button({
		    		    	id:'ryId',
		        			text:'人员基本信息',
		        			minWidth:80,
		        			handler:function(){
		        				czr={
										ryid:null,
										rynbid:selectRynbid,
										hhnbid:selectHhnbid
									}
								Gnt.toRyxx(czr);
		        			}
		        		})]
		    		},{
		    			columnWidth: .01,
						border:false,
						frame: false
		    		},{
		    			columnWidth: .01,
						border:false,
						frame: false
		    		}
		    		 
		    	]
				
			},{
				id:"p3centerId",
				border:false,
				frame: false,
				region:'center',
//	        	height: 600,
				items:[qczxForm]
			},{
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
		        	bodyStyle: 'padding:10px 0px 0px 0px ',
					layout:'column',
			    	items: [
						{
			    			columnWidth: .01,
							border:false,
							frame: false
			    		},{
							border:false,
							frame: false,
			    		    items:[new Ext.Button({
			        			text:'包不合格',
			        			minWidth:60,
			        			handler:function(){
			        				
			        			}
			        		})]
			    		},{
			    			columnWidth: .01,
							border:false,
							frame: false
			    		},{
							border:false,
							frame: false,
			    		    items:[new Ext.Button({
			        			text:'信息作废',
			        			minWidth:60,
			        			handler:function(){
			        				
			        			}
			        		})]
			    		},{
			    			columnWidth: .9,
							border:false,
							frame: false
			    		},{
							border:false,
							frame: false,
			    		    items:[new Ext.Button({
			        			text:'重新办证',
			        			minWidth:60,
			        			handler:function(){if(selRes){
			        				var url = basePath + "yw/edzzjgl/zjsl?jumpToZjsl="+"cxbz"+"&ryid="+selRes.data.ryid;			      
			        				if(parent && parent.openWorkSpace){
			        					parent.openWorkSpace(url,  "证件受理", "zjsl");
			        				}else{
			        					window.location.href = url;
			        				}
			        			
								}else{
									alert("请先选中一条有效的数据!");
								}}
			        		})]
			    		},{
			    			columnWidth: .01,
							border:false,
							frame: false
			    		},{
							border:false,
							frame: false,
			    		    items:[new Ext.Button({
			        			text:'办证历史',
			        			minWidth:60,
			        			handler:function(){
			        				
			        			}
			        		})]
			    		},{
			    			columnWidth: .01,
							border:false,
							frame: false
			    		},{
			    			columnWidth: .01,
							border:false,
							frame: false
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
			    			columnWidth: .01,
							border:false,
							frame: false
			    		},{
							border:false,
							frame: false,
			    		    items:[new Ext.Button({
			        			text:'返回',
			        			minWidth:60,
			        			handler:function(){
			        				Ext.getCmp('card').getLayout().setActiveItem(1);
			        			}
			        		})]
			    		}
			    		 
			    	]
					
				}]
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

    var menu, ctxItem;
    this.init = function(tabs){
        tabs.on('contextmenu', onContextMenu);
    }
    function onContextMenu(tabs, item, e){
        if(!menu){
            menu = new Ext.menu.Menu([{
                id: tabs.id + '-close',
                text: '关闭当前页',
                handler : function(){
                    tabs.remove(ctxItem);
                }
            },{
                id: tabs.id + '-close-others',
                text: '关闭其它页',
                handler : function(){
                    tabs.items.each(function(item){
                    	if(item.closable && item != ctxItem){
                            tabs.remove(item);
                        }
                    });
                }
            }]);
        }
        ctxItem = item;
        var items = menu.items;
        
        //设置【关闭当前页】菜单是否有效和当前页的closable属性一致
        items.get(tabs.id + '-close').setDisabled(!item.closable);
        
        var disableOthers = true; 
       	//遍历分页面板所有分页，查看除了自己，是否还有能够关闭的分页
        tabs.items.each(function(){
            if(this != item && this.closable){
                disableOthers = false;
                return false;
            }
        });
        
        //设置【关闭其它页】菜单是否有效
        items.get(tabs.id + '-close-others').setDisabled(disableOthers);
        
        //在鼠标右击处显示菜单
        menu.showAt(e.getPoint());
    }
	//定义TabPanel的控制菜单
	Ext.ux.TabCloseMenu = function(){
	    var menu, ctxItem;
	    this.init = function(tabs){
	        tabs.on('contextmenu', onContextMenu);
	    }
	    function onContextMenu(tabs, item, e){
	        if(!menu){
	            menu = new Ext.menu.Menu([{
	                id: tabs.id + '-close',
	                text: '关闭当前页',
	                handler : function(){
	                    tabs.remove(ctxItem);
	                }
	            },{
	                id: tabs.id + '-close-others',
	                text: '关闭其它页',
	                handler : function(){
	                    tabs.items.each(function(item){
	                    	if(item.closable && item != ctxItem){
	                            tabs.remove(item);
	                        }
	                    });
	                }
	            }]);
	        }
	        ctxItem = item;
	        var items = menu.items;
	        
	        //设置【关闭当前页】菜单是否有效和当前页的closable属性一致
	        items.get(tabs.id + '-close').setDisabled(!item.closable);
	        
	        var disableOthers = true; 
	       	//遍历分页面板所有分页，查看除了自己，是否还有能够关闭的分页
	        tabs.items.each(function(){
	            if(this != item && this.closable){
	                disableOthers = false;
	                return false;
	            }
	        });
	        
	        //设置【关闭其它页】菜单是否有效
	        items.get(tabs.id + '-close-others').setDisabled(disableOthers);
	        
	        //在鼠标右击处显示菜单
	        menu.showAt(e.getPoint());
	    }
	};
	//定义分页面板
    var tabs = new Ext.TabPanel({
    	id:'tabs',
//        activeTab: 0,
        width:500,
        height:250,
        resizeTabs:false, 
        enableTabScroll:true,
        plain:false,
        //activeTab: 0,		//默认选中标签
        listeners:{
        	//在关闭分页的时候，调用函数释放iframe占用资源
        	beforeremove:fixIFrame.createDelegate(this)
        },
        plugins: new Ext.ux.TabCloseMenu(),
        defaults:{
        	closable:false,
        	autoScroll: false,
        	frame: false,
        	shim: false,
        	xtype: 'panel',
        	iconCls : 'otherfile'
        },
        items:[{
			id : "slxx",
			title : "详细信息",
			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
				activate:function(){					
					Ext.getCmp('slxx').body.update(htmlStrStart + '?rynbid='+ selectRynbid + '&slh='+ slh +'&nbslid='+nbslid+"&goto=querycdxx"  + htmlStrEnd);					
					this.getUpdater().refresh();
				}
			}
        },{
			id : "rzxx",
			title : "查询日志",
				listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
				activate:function(){				
					Ext.getCmp('rzxx').body.update(htmlStrStart + '?slh='+ slh +'&nbslid='+nbslid+ "&goto=queryrzxx" + htmlStrEnd);					
					this.getUpdater().refresh();
				}
			}
        }]
    });	

	//定义分页面板
	
	function applyParam(store){
		/**
			是否显示记录总数
		 */
		var zs = '';
//		if(Ext.getCmp('c19').getValue()){
//			zs = '1';
//		}
		var fw1 = '';
		if(Ext.getCmp('c11').getValue()){
			fw1 = '1';
		}
		var fw2 = '';
		if(Ext.getCmp('c12').getValue()){
			fw2 = '1';
		}
		var fw3 = '';
		if(Ext.getCmp('c13').getValue()){
			fw3 = '1';
		}
		var fw4 = '';
		if(Ext.getCmp('c14').getValue()){
			fw4 = '1';
		}
		var fw5 = '';
		if(Ext.getCmp('c15').getValue()){
			fw5 = '1';
		}
		var fw6 = '';
		var fw7 = '';
		var fw8 = '';
		if(Ext.getCmp('c16').getValue()){
			
			if(Ext.getCmp('c17').getValue()&&Ext.getCmp('c18').getValue()){
				fw7 = Ext.getCmp('c17').getValue();
				fw8 = Ext.getCmp('c18').getValue();
				Ext.apply(store.baseParams, {fw1:fw1,fw2:fw2,fw3:fw3,fw4:fw4,fw5:fw5,fw7:fw7,fw8:fw8,xszs:zs});
			}
		}else{
			Ext.apply(store.baseParams, {fw1:fw1,fw2:fw2,fw3:fw3,fw4:fw4,fw5:fw5,xszs:zs});
		}
		//Ext.apply(store.baseParams, {xszs:zs,gdxx:gd});
		
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
        	items:[p1,p2,tabs]
        }
    });
	if(getQueryParam("jumpToThrycx")&& getQueryParam("jumpToThrycx")!=""){
		var store = slxxGrid.store;
		store.baseParams = {
			rynbid:getQueryParam("rynbid"),
			start:0,
			limit:20
		};
		store.load();
		store.on("load",function(store) {  
			if(store.data.length > 0){
				curIndex = 0;
				slxxGrid.fireEvent("rowclick",slxxGrid,curIndex);
				slxxGrid.getSelectionModel().selectRange(curIndex,curIndex);
			}
		},slxxGrid); 
		Ext.getCmp('card').getLayout().setActiveItem(1);
		v.doLayout();
    }else{
    	Ext.getCmp('nextId').setVisible(false);
    }
	if(getQueryParam("zjlqJumpTo")&& getQueryParam("zjlqJumpTo")!=""){
		var store = slxxGrid.store;
		store.baseParams = {
			rynbid:getQueryParam("rynbid"),
			start:0,
			limit:20
		};
		store.load();
		store.on("load",function(store) {  
			if(store.data.length > 0){
				curIndex = 0;
				if(getQueryParam("slzt")&&getQueryParam("slzt")>30){
					slxxGrid.fireEvent("rowdblclick",slxxGrid,curIndex);
				}else{
					slxxGrid.fireEvent("rowclick",slxxGrid,curIndex);
				}
				slxxGrid.getSelectionModel().selectRange(curIndex,curIndex);
			}
		},slxxGrid); 
//		alert(getQueryParam("slzt"));
//		if(getQueryParam("slzt")&&getQueryParam("slzt")>30){
//			Ext.getCmp('card').getLayout().setActiveItem(1);
//		}
		
		v.doLayout();
    }
    if(getQueryParam("ai")){
    	Ext.getCmp('card').getLayout().setActiveItem(getQueryParam("ai"));
    	tabs.setActiveTab(0);
    }else{
    	Ext.getCmp('nextId').setVisible(false);
    }  
    if(getQueryParam("jumpToEdzslMain")&& getQueryParam("jumpToEdzslMain")!=""){
		var store = slxxGrid.store;
		store.baseParams = {
			ryid:getQueryParam("ryid"),
			start:0,
			limit:20
		};
		store.load();
		store.on("load",function(store) {  
			if(store.data.length > 1){
				curIndex = 0;
				slxxGrid.fireEvent("rowclick",slxxGrid,curIndex);
				slxxGrid.getSelectionModel().selectRange(curIndex,curIndex);
			}else{
				slxxGrid.fireEvent("rowdblclick",slxxGrid,curIndex);
			}
		},slxxGrid); 
		Ext.getCmp('card').getLayout().setActiveItem(1);
		v.doLayout();
    }else{
    	Ext.getCmp('nextId').setVisible(false);
    }
});