var selectYw = 1;
var queryFlag = null;
var qybz = 1;
var jlxIndex = 0;
var clickFlag = true;

var selectedSelRes=null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10019,20021",function(){}))
		return;
	
	Gnt.ux.Dict.loadDict(['CS_HLX',"DWXXB","JWZRQXXB","XZJDXXB","JWHXXB"],function(){});
	
	var csm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var colModel = new Ext.grid.ColumnModel([
		{
			header: "户号",
	        dataIndex: "hh",
	        sortable: true,
			width: 80
		},{
	        header: "户类型",
	        dataIndex: "hlx",
	        sortable: true,
			width: 100,
			renderer:function(value, cellmeta, record, rowIndex,columnIndex, store){
				return Gnt.ux.Dict.getDictLable("CS_HLX", value, cellmeta, record, rowIndex,columnIndex, store);
			}
	    },{
			header: "街路巷代码",
	        dataIndex: "jlxxx.dm",
	        sortable: true,
			width: 100
		},{
			header: "街路巷(集体户名称)",
	        dataIndex: "jlxxx.mc",
	        sortable: true,
			width: 120
		},{
	        header: "派出所",
	        dataIndex: "mlpxx.pcs",
	        sortable: true,
			width: 120,
			renderer:function(value, cellmeta, record, rowIndex,columnIndex, store){
				return Gnt.ux.Dict.getDictLable("DWXXB", value, cellmeta, record, rowIndex,columnIndex, store);
			}
	    },{
	        header: "责任区",
	        dataIndex: "mlpxx.zrq",	
	        sortable: true,
			width:120,
			renderer:function(value, cellmeta, record, rowIndex,columnIndex, store){
				return Gnt.ux.Dict.getDictLable("JWZRQXXB", value, cellmeta, record, rowIndex,columnIndex, store);
			}
	    },{
	        header: "乡镇街道",
	        dataIndex: "mlpxx.xzjd",	
	        sortable: true,
			width: 120,
			renderer:function(value, cellmeta, record, rowIndex,columnIndex, store){
				return Gnt.ux.Dict.getDictLable("XZJDXXB", value, cellmeta, record, rowIndex,columnIndex, store);
			}
	    },{
	        header: "居(村)委会",
	        dataIndex: "mlpxx.jcwh",	
	        sortable: true,
			width: 120,
			renderer:function(value, cellmeta, record, rowIndex,columnIndex, store){
				return Gnt.ux.Dict.getDictLable("JWHXXB", value, cellmeta, record, rowIndex,columnIndex, store);
			}
	    },{
	        header: "门楼牌号",
	        dataIndex: "mlpxx.mlph",	
	        sortable: true,
			width: 120
	    },{
	        header: "门楼详址",
	        dataIndex: "mlpxx.mlxz",	
	        sortable: true,
			width: 120
	    }
	]);
 	var myuserStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'cx/hjjbxx/hxxcx/getHxx.json',
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        id:'id',
        totalProperty:'totalCount',
        fields: [
        	"hhnbid",
			"mlpnbid",
			"hh",
			"hlx",
			"jlxxx.dm",
			"jlxxx.mc",
			"mlpxx.pcs",
			"mlpxx.zrq",
			"mlpxx.xzjd",
			"mlpxx.jcwh",
			"mlpxx.mlph",
			"mlpxx.mlxz",
			"hxx",
			"mlpxx",
			"jlxxx"
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
 	
 	var jthGrid = new Ext.grid.GridPanel({
        store: myuserStore,
        region: 'center',
        view:new Ext.grid.GridView({
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
        	rowclick:function(g, rowIndex, e){
        		selectedSelRes = g.store.getAt(rowIndex);
        		
        		if(clickFlag){
        			jlxIndex = 0;
        		}
        		clickFlag = true;
        	},
			rowdblclick:function(g, rowIndex, e){
				//alert(g.store.getAt(rowIndex).data.hhnbid);
				var url = basePath + "cx/hjjbxx/hxxcx?jumpToHxxcx=1&hhnbid=" + g.store.getAt(rowIndex).data.hhnbid + "&queryXx=queryhxx";
				if(parent && parent.openWorkSpace){
					parent.openWorkSpace(url,  "户信息查询", "hxxcx" );
				}else{
					window.location.href = url;
				}
			}
		},
        bbar: new Ext.PagingToolbar({
			pageSize: 100,
			store: myuserStore,
			displayInfo: true
		})
    });
 	
	var modifyjlxxxwh_window = new Gnt.ux.SelectJlxxxWhModify({
		//选择立户信息回调
		callback: function(optype, jwzrqxxModify){
			//提交数据
			Gnt.submit(
				jwzrqxxModify,
				"gl/xtmbgl/jlxxxwh/modifyJlxxx",
				"正在修改街路巷信息，请稍后...",
				function(jsonData,params){
					showInfo("街路巷信息修改成功！");
					jthGrid.store.reload();
				}
			);
		}
	});
	
	var addjlxxxwh_window = new Gnt.ux.SelectJthxxWhAdd({
		//选择立户信息回调
		callback: function(optype, jwzrqxxAdd){
			
			
			//提交数据
			Gnt.submit(
				jwzrqxxAdd,
				"gl/xtmbgl/jlxxxwh/addJlxxx",
				"正在新增街路巷信息，请稍后...",
				function(jsonData,params){
					showInfo("街路巷信息新增成功！");
					jthGrid.store.reload();
				}
			);
			
		}
	});
	
	var p1_1 = new Ext.form.FormPanel({
		margins:'5px 5px 5px 5px',
		region : 'north',
		height:90,
		layout : 'column',
		title : '',
		frame: false,
		border:  false,
		autoScroll : true,
    	buttonAlign:'right',
    	labelAlign:'right',
		defaults : {
			frame:false,
			border:false
		},
		items:[{
			layout : 'column',
			title : '',
			margins:'0',
			bodyStyle : 'padding:5px 0px 0px 0px',
			defaults : {
				frame:false,
				border:false,
				labelWidth : 80,
				bodyStyle : 'padding:0px 5px 0px 5px'
			},
			items:[{
                columnWidth:.25,
	           	layout: 'form',
    	       	items: [{
    	       		fieldLabel:'户号',
					xtype:'textfield',
					anchor:'100%',
					name:'hh'
				}]
			},{
                columnWidth:.25,
	           	layout: 'form',
    	       	items: [{
    	       		anchor:'100%',
					xtype:'dict_combox',
					dict:'VisionType=CS_HLX',
					value:"2",
					name:'hlxmc',
					maxLength:40,
					hiddenName:'hlx',
					readOnly : false,
					editable:false,
					allowBlank:false,
					fieldLabel:'户类型'
				}]
			},{
                columnWidth:.25,
	           	layout: 'form',
    	       	items: [{
    	       		fieldLabel:'集体户名称',
					xtype:'textfield',
					anchor:'100%',
					name:'jthmc'
				}]
			},{
                columnWidth:.25,
	           	layout: 'form',
    	       	items: [{
    	       		fieldLabel:'派出所',
    	       		xtype:'search_combox',
    	       		searchUrl:'dict/utils/searchXxb?visiontype=DWXXB',
					anchor:'100%',
					hiddenName:'pcs'
				}]
			},{
                columnWidth:.25,
	           	layout: 'form',
    	       	items: [{
    	       		fieldLabel:'责任区',
    	       		xtype:'search_combox',
    				anchor:'100%',
    				hiddenName:'zrq',
    				searchUrl : "dict/utils/searchXxb?visiontype=JWZRQXXB"
				}]
			},{
                columnWidth:.25,
	           	layout: 'form',
    	       	items: [{
    	       		fieldLabel:'乡镇街道',
    	       		xtype:'search_combox',
    				anchor:'100%',
    				hiddenName:'xzjd',
    	       		searchUrl : "dict/utils/searchXxb?visiontype=XZJDXXB"
				}]
			},{
                columnWidth:.25,
	           	layout: 'form',
    	       	items: [{
    	       		fieldLabel:'居(村)委会',
    	       		xtype:'search_combox',
    	       		anchor:'100%',
    	       		hiddenName:'jcwh',
    	       		searchUrl:'dict/utils/searchXxb?visiontype=JWHXXB'
				}]
			},{
                columnWidth:.25,
	           	layout: 'form',
    	       	items: [{
    	       		fieldLabel:'街路巷',
    	       		xtype:'search_combox',
    	       		anchor:'100%',
    	       		hiddenName:'jlx',
    	       		searchUrl : "dict/utils/searchXxb?visiontype=JLXXXB"
				}]
			},{
                columnWidth:.25,
	           	layout: 'form',
    	       	items: [{
    	       		fieldLabel:'门楼牌号',
					xtype:'textfield',
					anchor:'100%',
					name:'mlph',
                    listeners: {
                        blur:function(field){
							field.setValue(Gnt.ToDBC(field.getValue(),false));
						}
                    }
				}]
			},{
                columnWidth:.25,
	           	layout: 'form',
    	       	items: [{
    	       		fieldLabel:'门楼详址',
					xtype:'textfield',
					anchor:'100%',
					name:'mlxz',
                    listeners: {
                        blur:function(field){
							field.setValue(Gnt.ToDBC(field.getValue(),false));
						}
                    }
				}]
			},{
                columnWidth:.4,
	           	layout: 'form'
			},{
                columnWidth:.1,
    	       	items: [new Ext.Button({
					text:'查询',
					minWidth:75,
					handler:function(){
						
						if(!p1_1.getForm().isValid()){
							showInfo("数据校验没有通过，请检查！");
							return;
						}
						
						var store = jthGrid.store;
						store.baseParams = p1_1.getForm().getValues();
						Ext.apply(store.baseParams, {hlx:2,hhzt:0,queryXx:"queryhxxxx"});
						store.load({params:{start:0, limit:100}});
						store.on("load",function(store) {
							if(store.data.length > 0){
								curIndex = 0;
								jthGrid.fireEvent("rowclick",jthGrid,curIndex);
								jthGrid.getSelectionModel().selectRange(curIndex,curIndex);
							}
						},jthGrid);
						
						
					}
				})]
			}]
		}]
	});
	/*
	var jthGrid = new Gnt.ux.SjpzGrid({
		title: '户信息列表',
		region : 'center',
		url: 'cx/hjjbxx/hxxcx/getHxx.json',
		pzlb: '20001',
		pkname: 'hhnbid',
		listeners:{
    		rowclick:function(g, rowIndex, e){
    			selectedSelRes = g.store.getAt(rowIndex);
    		}
    	}
	});
	
	jthGrid.colModel.moveColumn(6,2);	//动态移动列的位置
	jthGrid.colModel.setColumnHeader(2,"街路巷(集体户名称)");	//动态修改列名
	jthGrid.colModel.setColumnWidth(2,120);	//动态修改列宽
	jthGrid.colModel.setHidden(9,true);
	jthGrid.colModel.setHidden(10,true);
	jthGrid.colModel.setHidden(11,true);
	jthGrid.colModel.setHidden(12,true);
	*/
	
	var p1_3 = new Ext.Panel({
		xtype: 'panel',
		border:false,
		frame: false,
    	region:'south',
    	height: 30,
    	bodyStyle: 'padding:5px',
		layout:'table',
		layoutConfig: {
        	columns: 30
        },
        items:[
			{
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
    		},{
				width: 70,
				html:'街路巷',
				border:false,
				frame: false
			},
    		{
				id:'queryValue',
    			xtype : 'textfield',
    			border:false,
    			frame:false,
    			fieldLabel:"",
    			columnWidth: .1,
    			name:'queryValue',
                listeners: {
                	change : function(field,newValue,oldValue){
                		jlxIndex = 0;
					}
                }
			},{
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
    		},{
    			border:false,
				frame: false,
				items:[
					new Ext.Button({
						text:'查找',
						minWidth:80,
						handler:function(){
							if(!Ext.getCmp('queryValue').getValue()){
								alert("请输入需要查找的相关信息标记!");
								return;
							}
							var jlxCount = 0;
							var store = jthGrid.store;
							if(store.data.length > 0){
								for(var i= 0;i<store.data.length;i++){
									var res = store.getAt(i);
									var b = Ext.getCmp('queryValue').getValue();
									var a = res.data.jlxxx.mc;
									
									if(a.indexOf(b.toUpperCase())!=-1){
										
										if(jlxCount == jlxIndex){
											clickFlag = false;
											jthGrid.fireEvent("rowclick",jthGrid,i);
											jthGrid.getSelectionModel().selectRange(i,i);
											jthGrid.getView().focusRow(i);
											jlxIndex ++;
											
											return;
										}else{
											jlxCount ++;
										}
										
									}
								}
							}
						}
					})
				]
			
			}, {
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
    		}, {
				border:false,
				frame: false,
    		    items:[
    		           new Ext.Button({
    		        	   text:'保存',
							minWidth:80,
							handler:function(){
								
							}
    		           })
    		    ]
    		},{
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
    		}, {
				border:false,
				frame: false,
    		    items:[
    		           new Ext.Button({
    		        	   text:'增加',
							minWidth:80,
							id:'addBtn',
							handler:function(){
								addjlxxxwh_window.show();
								
								addjlxxxwh_window.setSelRes();
							}
    		           })
    		    ]
    		},{
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
    		}, {
				border:false,
				frame: false,
    		    items:[
    		           new Ext.Button({
    		        	   text:'修改',
							id:'modifyBtn',
							minWidth:80,
							handler:function(){
								
								if(selectedSelRes){
									modifyjlxxxwh_window.show(selectedSelRes.data);
									modifyjlxxxwh_window.setSelRes(selectedSelRes.data.jlxxx);
								}else{
									Ext.Msg.alert("提示","请选择需要修改的记录!");
								}
								
							}
    		           })
    		    ]
    		},{
				border:false,
				frame: false,
    		    items:[
    		           new Ext.Button({
							text:'恢复',
							id:'resumeBtn',
							hidden:true,
							minWidth:80,
							handler:function(){
								Gnt.submit(
									{
										dm:selectedSelRes.data.jlxxx.dm,
										qybz:1
									},
									"gl/xtmbgl/jlxxxwh/resumeJlxxx",
									"正在 恢复街路巷，请稍后...",
									function(jsonData,params){
										showInfo("街路巷恢复成功！");
										jthGrid.store.reload();
									}
								);
							}
    		           })
    		    ]
    		}, {
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
    		}, {
				border:false,
				frame: false,
    		    items:[
    		           new Ext.Button({
							text:'删除',
							id:'delBtn',
							minWidth:80,
							disabled: !user.isadmin?true:false,
							handler:function(){
								if(window.confirm('是否确定要删除【'+selectedSelRes.data.jlxxx.dm+'】【'+selectedSelRes.data.jlxxx.mc+'】?')){
									Gnt.submit(
									{
										dm:selectedSelRes.data.jlxxx.dm},
										"gl/xtmbgl/jlxxxwh/delJlxxx",
										"正在删除街路巷信息，请稍后...",
										function(jsonData,params){
											showInfo("街路巷删除成功！");
											jthGrid.store.reload();
										}
									);
								}
							}
    		           })
    		    ]
    		},{
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
    		},{
				border:false,
				frame: false,
    		    items:[
    		           new Ext.Button({
    		        	   text:'并户',
							minWidth:80,
							handler:function(){
								/**
									并户无法还原
									并且还需要重新指定户主
									还需要考虑是否同一人入了不同的多个户
								 */
								if(selectedSelRes){
									
								}else{
									Ext.Msg.alert("提示","请选择需要合并的记录!");
								}
								
							}
    		           })
    		    ]
    		},{
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
    		},{
				border:false,
				frame: false,
    		    items:[
    		           new Ext.Button({
    		        	   text:'关闭',
							minWidth:80,
							handler:function(){
								window.parent.closeWorkSpace();
							}
    		           })
    		    ]
    		}
		]
    });
	var p1 = new Ext.Panel({
		layout:'border',
		items:[p1_1,jthGrid,p1_3]/*,
			buttons:[
				new Ext.Button({
					text:'保存',
					minWidth:80,
					handler:function(){
						qyzPrint(selectHjywid);
					}
				}),
				new Ext.Button({
					text:'修改',
					minWidth:80,
					handler:function(){
						window.parent.parent.closeWorkSpace();
					}
				}),
				new Ext.Button({
					text:'关闭',
					minWidth:80,
					handler:function(){
						Ext.getCmp('card').getLayout().setActiveItem(0);
					}
				})
				]*/
	});
	//定义分页面板
	
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
});