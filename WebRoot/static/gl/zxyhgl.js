var qybz = 1;
var queryFlag = null;
var rynbid = null;
var hideFlag = true;
var xPosition=0;
var xPosition1=0;
var selectedSelRes=null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	var csm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var colModel = new Ext.grid.ColumnModel([
		{
			header: "用户回话Id",
	        dataIndex: "yhhhid",
	        sortable: true,
	        hidden:true,
			width: 120
		},{
			header: "用户姓名",
	        dataIndex: "yhxm",
	        sortable: true,
			width: 120
		},{
			header: "用户ip",
	        dataIndex: "yhip",
	        sortable: true,
			width: 120
		},{
			header: "单位代码",
	        dataIndex: "dwmc",
	        sortable: true,
			width: 120
		},{
	        header: "最后访问时间",
	        dataIndex: "zhfwsj",
	        sortable: true,
			width: 120,
			renderer:function(value){
//				if(value&&value.length == 14){
//					return value.substr(0,4)+"-"+value.substr(4,2)+"-"+value.substr(6,2)+" "+value.substr(8,2)+":"+value.substr(10,2)+":"+value.substr(12,2)
//				}
				return value;
			}
	    },{
	        header: "版本号",
	        dataIndex: "khdbb",
	        sortable: true,
			width: 120,
			renderer:function(value){
				if(value&&value.length>0){
					return "客户端登录";	
				}else{
					return "网页版登录";
				}
				return value;
			}
	    },{
	        header: "用户职务",
	        dataIndex: "yhzw",	
	        sortable: true,
			width:120
	    },{
	        header: "用户状态",
	        dataIndex: "yhzt",	
	        sortable: true,
			width: 60,
			renderer:function(value){
				if(value=='0'){
					return "正常";	
				}else if(value=='1'){
					return "冻结";
				}else if(value=='2'){
					return "注销";
				}
				return value;
			}
	    },{
	        header: "用户性别",
	        dataIndex: "yhxb",	
	        sortable: true,
	        hidden:true,
			width:120
	    },{
	        header: "用户登录名",
	        dataIndex: "yhdlm",	
	        sortable: true,
			width:120
	    },{
	        header: "警员号",
	        dataIndex: "jyh",	
	        sortable: true,
			width: 80
	    }
	]);
 	var myuserStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'gl/zxyhgl/queryZxyh',
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        totalProperty:'totalCount',
        fields: [
        	"yhhhid",
        	"yhxm",
			"yhip",
			"dwmc",
			"zhfwsj",
			"khdbb",
			"yhzw",
			"yhzt",
			"yhxb",
			"yhdlm",
			"jyh"
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
	var zxyhglGrid = new Ext.grid.GridPanel({
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
		//bodyStyle:'width:50%',
        border:true,
        frame:false,
        //anchor:'50% 50%',
	    margins: '0 0 0 0',
		cmargins: '0 0 0 0',        
		iconCls:'icon-grid',
        title:'',
        listeners:{
        	rowclick:function(g, rowIndex, e){
        		selectedSelRes = g.store.getAt(rowIndex);
        	},
			rowdblclick:function(g, rowIndex, e){
				selectedSelRes = g.store.getAt(rowIndex);
			}
		},
        bbar: new Ext.PagingToolbar({
				pageSize: 20,
				store: myuserStore,
				displayInfo: true
		})
    });
	var xzlxcsm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var xzlxcolModel = new Ext.grid.ColumnModel([
		{
			header: "限制类型",
	        dataIndex: "dm",
	        sortable: true,
			width: 100
		},{
			header: "类型名称",
	        dataIndex: "mc",
	        sortable: true,
			width: 150
		}
	   
	]);
 	var xzlxStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'gl/xtkzgl/ywblxzwh/getXtywbllxInfo',
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        totalProperty:'totalCount',
        fields: [
        	"dm",
			"mc"
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
	var xzlxGrid = new Ext.grid.GridPanel({
        store: xzlxStore,
        region: 'center',
        width:300,
		stripeRows: true,
        cm: xzlxcolModel,
        sm:xzlxcsm,
		loadMask: {msg:'正在加载数据，请稍侯……'},
//		bodyStyle:'width:80%',
        border:true,
        frame:true,
//        anchor:'100% 100%',
//	    margins: '0 0 0 0',
//		cmargins: '0 0 0 0',
		iconCls:'icon-grid',
        title:'',
        listeners:{
        	rowclick:function(g, rowIndex, e){
        		selectedSelRes = g.store.getAt(rowIndex);
        		var store = xzxxGrid.store;
        		store.baseParams = {
        			xzywlx:selectedSelRes.data.dm,
        			qybz:qybz
        		};
        		xPosition = xzxxGrid.x;
        		store.load({params:{start:0, limit:20}});	
        		store.on("load",function(store) {  
        			if(store.data.length > 0){
        				xzxxGrid.fireEvent("rowclick",xzxxGrid,0);
        				xzxxGrid.getSelectionModel().selectRange(0,0);
        				
        			}
        		},xzxxGrid);         		
        	},
			rowdblclick:function(g, rowIndex, e){
        		selectedSelRes = g.store.getAt(rowIndex);
        		var store = xzxxGrid.store;
        		store.baseParams = {
        			xzywlx:selectedSelRes.data.dm,
        			qybz:qybz
        		};
        		xPosition = xzxxGrid.x;
        		store.load({params:{start:0, limit:20}});	
        		store.on("load",function(store) {  
        			if(store.data.length > 0){
        				xzxxGrid.fireEvent("rowclick",xzxxGrid,0);
        				xzxxGrid.getSelectionModel().selectRange(0,0);
        				
        			}
        		},xzxxGrid);         		
        	}
		}/*,
        bbar: new Ext.PagingToolbar({
			pageSize: 9999,
			store: xzlxStore,
			displayInfo: true
	})*/
    });		
	var p1_1 = new Ext.Panel({
		xtype: 'panel',
		border:false,
		frame: false,
    	region:'north',
    	height: 35,
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
				border:false,
				frame: false,
				items:[new Ext.Button({
            		text:'刷新',
					minWidth:80,
					handler:function(){
						zxyhglGrid.store.reload();
					}
            	})]
			},{
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
    		},{
    			frame:false,
				border:false,
				items:[new Ext.Button({
            		text:'停止用户会话',
					minWidth:80,
					handler:function(){
						if(!selectedSelRes){
							alert("请至少选择一条有效的数据!");
							return;
						}else{
							if(selectedSelRes.data.yhdlm ==user.usercode){
								alert("不能杀掉、停止自己的回话");
								return;
							}else{
								Gnt.submit(
										{
											yhhhid:selectedSelRes.data.yhhhid}, 
											"gl/zxyhgl/delYhhhid", 
											"正在停止用户对话，请稍后...", 
											function(jsonData,params){
												showInfo("用户对话停止成功！");
												zxyhglGrid.store.reload(); 
											}
										);
						   }
						}

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
            		id:'modifyBtn',
					minWidth:80,
					handler:function(){
						window.parent.closeWorkSpace();
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
					text:'发布消息',
					minWidth:80,
					handler:function(){
						if(hideFlag){
							xPosition1 = zxyhglGrid.x;
							Ext.getCmp('weastPart').hide();
							zxyhglGrid.setPosition(0,zxyhglGrid.y);
						}else{
							Ext.getCmp('weastPart').show();
							zxyhglGrid.setPosition(xPosition1,zxyhglGrid.y);
						}
						hideFlag = !hideFlag;
					}
				})]
    		}]
    });
	var p1_2 = new Ext.Panel({
		xtype: 'panel',
		border:false,
		frame: false,
		region: 'center',
    	width: 350,
    	//bodyStyle: 'padding:5px',
		layout:'border',
		layoutConfig: {
        	columns: 30
        },
        items:[
			{
				xtype: 'fieldset',
				autoHeight: true,
				height:480,
				border:false,
				frame: false,
				bodyStyle:'padding:20px',
		        layout:'column',
				items:[{
	            	title: '日期',
	            	region:'center',
		            xtype: 'fieldset',
		            layout:'column',
		            width:'49%',
					height:60,
            		bodyStyle : 'padding:0 0 0 0',
					defaults:{
		            	columnWidth: 1,
		            	fieldLabel: '',
		            	name: 'fs'
		            },
		            items: [{
		    	    	title: '',
		            	region:'center',
			            layout:'table',
			            border:false,
						frame: false,
		            	items:[{
		            		html: '请选择打印月份',
							height: 25,
							border:false,
							frame: false,
							region:'north',
							bodyStyle:'padding:5px'
		            	},{
							xtype:'datefield',
							width:120,
							format:'Ym',
							id:'dyyf',
							name:'dyyf',
							fieldLabel:'打印月份',
							listeners:{
								'change':function(){
									if(((Ext.getCmp('pcs').getValue()&&Ext.getCmp('pcs').getValue()!="")||(Ext.getCmp('jwh').getValue()&&Ext.getCmp('jwh').getValue()!=""))&&Ext.getCmp('dyyf').getValue()&&Ext.getCmp('dyyf').getValue()!=""){
	                        			 Ext.getCmp('queryId').enable();
	                        		 }else{
	                        			 Ext.getCmp('queryId').disable();
	                        		 }
								}
							}
						
		            	}]
		            }]
				}]
			}]
    });
	var p1 = new Ext.Panel({
		layout:'border',
		items:[p1_1,{
			region: 'east',
			layout:'border',
			id:'weastPart',
			border:false,
            frame:false,
            width:'20%',
    	    margins: '0 0 0 0',
    		cmargins: '0 0 0 0',  
    		title:'',
    		items:[{
    			region:'center',
//            	height:400,
    			border:false,
				frame: false,
    			items:[{
	            	id:'fwId',
	            	title: '',
		            xtype: 'fieldset',
		            //height:100,
		            autoHeight: true,
		            layout:'column',
		            defaults:{
		            	xtype:'radio',
		            	columnWidth: .5,
		            	fieldLabel: '',
		            	name: 'fw'
		            },
		            items: [{
		            	id:'r11',
		            	boxLabel: '单用户',
		            	name:'fw',
		            	checked:true,
		            	listeners:{
		            		'check':function (ck, checked){
		            			if (checked) {
		            				Ext.getCmp('dname').show();
		            				Ext.getCmp('dvalue').show();
		            				Ext.getCmp('aname').hide();
		            				Ext.getCmp('avalue').hide();
		            			}
		            		}
		            	}
		            },{
		            	id:'r12',
		            	boxLabel: '所有用户',
		            	name:'fw',
		            	listeners:{
		            		'check':function (ck, checked){
		            			if (checked) {
		            				Ext.getCmp('dname').hide();
		            				Ext.getCmp('dvalue').hide();
		            				Ext.getCmp('aname').show();
		            				Ext.getCmp('avalue').show();
		            			}
		            		}
		            	}
		            }]
				},{
	            	title: '',
//					columnWidth: 3,
		            xtype: 'fieldset',
		            autoHeight: true,
		            layout:'column',
		            defaults:{
		            	columnWidth: 1.,
		            	fieldLabel: '',
		            	name: 'zt'
		            },
		            items: [{
		            	id:'dname',
						border:false,
						frame: false,
						html:'目标用户'
					},{
//						border:false,
//						frame: false,
//						id:'dvalue',
//						xtype : "combo",
//			    		store : [[1,'用户1'],[2,'用户2']],
//			    		value:'1',
//						fieldLabel:"",
//						triggerAction:"all",
//						maxHeight : 80
						xtype:'search_combox',
						searchUrl:'dict/utils/searchYhxx?optype=eq',
						fields:["code","name","pyt"],
						valueField: "pyt",
						displayField: "name",
						allowBlank:true,
						fieldLabel:'用户',
						id:'dvalue',
						hiddenName:'mbyh'
							
					},{
						border:false,
						frame: false,
						id:'aname',
						html:'发送单位限制'
					},{
//						border:false,
//						frame: false,
//						id:'avalue',
//						xtype : "combo",
//			    		store : [[1,'单位1'],[2,'单位2']],
//						fieldLabel:"",
//						triggerAction:"all",
//						maxHeight : 80
						xtype:'search_combox',
						anchor:'100%',
						id:'avalue',
						searchUrl:'dict/utils/searchXxb?visiontype=DWXXB',
						fields:["code","name"],
						valueField: "code",
						displayField: "name",
						allowBlank:false,
						fieldLabel:'单位代码',
						hiddenName:'dwdm'
					}]
				},{
	            	title: '',
		            xtype: 'fieldset',
		            autoHeight: true,
		            layout:'column',
		            defaults:{
		            	columnWidth: 1.,
		            	fieldLabel: '',
		            	name: 'zt'
		            },
		            items: [{
						border:false,
						frame: false,
						html:'发送信息'
					},{
						border:false,
						frame: false,
						xtype:'htmleditor',
						height:200,
						id:'message',
						width:150,
						name:'message'
					}],
		    		buttons:[new Ext.Button({
						text:'发送',
						minWidth:100,
						width:150,
						handler:function(){
							var data1={
									type:1,
									fsryhid:user.yhid
							}
							var TypeValue ="";//单用户  赋值1， 所有用户  赋值 2
							if(Ext.getCmp("r11").checked&&!Ext.getCmp("r12").checked){
								TypeValue=1;
								if(!Ext.getCmp("dvalue").getValue()){
									alert("请先选择一个有效的用户先！")
										return;	
								}
							}else if(!Ext.getCmp("r11").checked&&Ext.getCmp("r12").checked){
								TypeValue=2;
							};
							var data={
									type:TypeValue,
									fsryhid:user.yhid,
									fsryhxm:user.yhxm,
									message:Ext.getCmp("message").getValue(),
									jsryhid:Ext.getCmp("dvalue").getValue(),
									jsryhxm:Ext.getCmp("dvalue").lastSelectionText,
									fsdw:Ext.getCmp("avalue").getValue()
							}
							Gnt.submit(
									data, 
										"gl/zxyhgl/fsxx", 
										"正在发送信息，请稍后...", 
										function(jsonData,params){
											showInfo("消息发送成功！",function(){
												 
											});
										}
							);
							
						}
			         }),new Ext.Button({
							text:'清空',
							minWidth:100,
							width:150,
							handler:function(){
								Ext.getCmp('message').setValue("");
							}
			         })]
				}]
    		}/*,{
    			region:'center',
    			width:300
    		}*/
    		]
		},zxyhglGrid]
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
	
	var csstore = zxyhglGrid.store;
//	csstore.baseParams = {
//			cslb:1030
//	};
	csstore.load({params:{start:0, limit:9999}});	
	csstore.on("load",function(store) {  
		if(csstore.data.length > 0){
			curIndex = 0;
			zxyhglGrid.fireEvent("rowclick",zxyhglGrid,curIndex);
			zxyhglGrid.getSelectionModel().selectRange(curIndex,curIndex);
			
		}
	},zxyhglGrid); 	
	Ext.getCmp('aname').hide();
	Ext.getCmp('avalue').hide();
});