var selectedSelRes=null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	var jscsm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var jscolModel = new Ext.grid.ColumnModel([
		{
			header: "jsID",
	        dataIndex: "jsid",
	        sortable: true,
	        hidden:true,
			width: 120
		},{
			header: "角色名称",
	        dataIndex: "jsmc",
	        sortable: true,
			width: 120
		},{
			header: "角色描述",
	        dataIndex: "ms",
	        sortable: true,
			width: 300
		}
	]);
 	var jsStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'gl/jsgl/queryJs',
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        totalProperty:'totalCount',
        fields: [
        	"jsid",
        	"jsmc",
			"ms"
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
	var jsGrid = new Ext.grid.GridPanel({
        store: jsStore,
        region: 'center',
        view:new Ext.grid.GridView({
				//forceFit:true,
				//autoFill:true,
				enableRowBody:true
		}),
		stripeRows: true,
        cm: jscolModel,
        sm:jscsm,
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
				store: jsStore,
				displayInfo: true
		})
    });
	var modifyjsWindow = new Gnt.ux.SelectJsWhModify({
		//选择立户信息回调
		callback: function(optype, jsxxModify){
			//提交数据
			Gnt.submit(
					jsxxModify, 
					"gl/jsgl/modifyJs", 
					"正在修改角色信息，请稍后...", 
					function(jsonData,params){
						showInfo("角色信息修改成功！");
						jsGrid.store.reload(); 
					}
			);
		}
	});
	
	var addjsWindow = new Gnt.ux.SelectJsWhAdd({
		//选择立户信息回调
		callback: function(optype, jsxxAdd){
			//提交数据
			Gnt.submit(
					jsxxAdd, 
					"gl/jsgl/addJs", 
					"正在新增角色信息，请稍后...", 
					function(jsonData,params){
						showInfo("角色信息新增成功！");
						jsGrid.store.reload(); 
					}
			);
		}
	});
	var p2_1 = new Ext.Panel({
		xtype: 'panel',
		border:false,
		frame: false,
    	region:'north',
    	height: 45,
    	bodyStyle: 'padding:5px',
    	layout:'column',
    	defaults:{
    		columnWidth: .2,
			border:false,
			frame:false
		},
    	items: [
			{
				layout: 'form',
				columnWidth:.12,
				items:[{
					frame:false,
					border:false,
					xtype:'panel',
					html:'当前选中模板名称'
				}]
			},
			{
				layout: 'form',
				columnWidth:.25,
				items:[{
					id:'selectedMbmc2',
	    			xtype : 'textfield',
	    			labelSeparator: '',
	    			border:false,
	    			frame:false,
	    			fieldLabel:"",
	    			name:'selectedMbmc2'
				}]
			},
			{
				layout: 'form',
				columnWidth:.1,
				items:[
					new Ext.Button({
						text:'保存操作',							
						width:80,
						handler:function(){
						}
					})
					]
			},{
				layout: 'form',
				columnWidth:.1,
				items:[
					new Ext.Button({
						text:'取消操作',							
						width:80,
						handler:function(){
						}
					})
					]
			}
			]
    });
	  var tabs = new Ext.TabPanel({   		  
          region: 'center',
          margins: '3 3 3 0',   
          activeTab: 0,   
          defaults: {   
             autoScroll: false   
          },  
          items: [{   
              title: '角色信息',   
              region: 'center',
	            collapsible: false,
	            layout:'border',
				border:false,
	            frame:false,
	            width:300,
	            anchor:'100% 100%',
	    	    margins: '0 0 0 0',
	    		cmargins: '0 0 0 0',
	            items:[jsGrid]  
          }, {   
              title: '角色权限信息',   
	          collapsible: false,
	          layout:'border',
		      border:false,
	          frame:false,
	          anchor:'100% 100%',
              items:[p2_1,{
          		region:'center',
        		layout:'column',
        		autoHeight : true, 
        		items:[{
        			title: '角色已分配权限',
                	region:'center',
                    xtype: 'fieldset',
                    frame:false,
                    columnWidth: .25,
                    autoHeight : true, 
        			items:[{
        				html:'ssssss'
        				//items:[mbdzGrid]
        				
        			},{
        	        	buttonAlign : 'right',
        		    	buttons: [new Ext.Button({
        	    		    	id:'fzId',
        	        			text:'动作维护',
        	        			minWidth:80,
        	        			handler:function(){
        	        				var url = basePath + "gl/xtkzgl/spdzwh?jumpToSpdzwh="+"1";
        	    					if(parent && parent.openWorkSpace){
        	    						parent.openWorkSpace(url,  "审批动作维护", "_spdzwh_");
        	    					}else{
        	    						window.location.href = url;
        	    					}
        	        			}
        	        		})
        	    		]
                }]
        		},{
        			title: '角色未分配的权限',
                	region:'center',
                    xtype: 'fieldset',
                    frame:false,
                    columnWidth: .75,
                    autoHeight : true, 
                    style:'margin-left:10px',
                    items:[{
        				anchor:'100% 100%'/*,
        				items:[mbspGrid]*/
        				
        			}
                    ]
        			
        		}]
        	
        		}]
          }]   
     });
		var p1_1 = new Ext.Panel({
			xtype: 'panel',
			border:false,
			frame: false,
	    	region:'north',
	    	height: 45,
	    	bodyStyle: 'padding:10px',
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
	            		text:'增加角色',
						minWidth:80,
						handler:function(){
							if(selectedSelRes){
								addjsWindow.show(selectedSelRes);
								addjsWindow.setSelRes(selectedSelRes);
							}else{
								alert("请选中一条有效的数据，再点击修改！");
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
	    			frame:false,
					border:false,
					items:[new Ext.Button({
	            		text:'修改角色',
						minWidth:80,
						handler:function(){
							if(selectedSelRes){
								modifyjsWindow.show(selectedSelRes);
								modifyjsWindow.setSelRes(selectedSelRes);
							}else{
								alert("请选中一条有效的数据，再点击修改！");
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
	            		text:'删除角色',
						minWidth:80,
						handler:function(){
							if(!selectedSelRes){
								alert("请至少选择一条有效的数据!");
								return;
							}else{
								if(window.confirm('是否确定要删除角色名为【'+selectedSelRes.data.jsmc+'】的用户信息?')){
									Gnt.submit(
									{
										jsid:selectedSelRes.data.jsid}, 
										"gl/jsgl/delJs", 
										"正在删除角色信息，请稍后...", 
										function(jsonData,params){
											showInfo("角色信息删除成功！");
											jsGrid.store.reload(); 
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
	            		text:'关闭窗体',
						minWidth:80,
						handler:function(){
							window.parent.closeWorkSpace();
						}
	            	})]
	    		}]
	    });	  
	 	
	 var borderPanel = new Ext.Panel({
	        layout: 'border',
	        tltle: '',
	        //width: 1000,
	       // height: 800,
	        defaults: {
	            collapsible: true, // 支持该区域的展开和折叠
	            split: false, // 支持用户拖放改变该区域的大小
	            bodyStyle: 'padding:15px'
	        },
	        items: [p1_1, {
	            title: '',
	            region: 'center',
	            collapsible: false,
	            layout:'border',
				border:false,
	            frame:false,	      
	            items:[tabs]	            
	        }]
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
	        	items:[borderPanel]
	        }
	    });
	 
	 var csstore = jsGrid.store;
//	 csstore.baseParams = {
//			 cslb:9023
//	 };
		csstore.load();	
		csstore.on("load",function(store) {  
			if(csstore.data.length > 0){
				curIndex = 0;
				jsGrid.fireEvent("rowclick",jsGrid,curIndex);
				jsGrid.getSelectionModel().selectRange(curIndex,curIndex);
				
			}
		},jsGrid);
});