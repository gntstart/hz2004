var selectedSelRes=null;
var selecteRowIndex = null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	//本业务需要加载的配置
	var grid5011 = new Gnt.ux.SjpzGrid({
		title: '居委会信息列表',
		region : 'center',
		url: 'gl/jwhhzhqmwh/queryJwhZp.json',
		pzlb: '5011',
		pkname: 'dm',
		listeners:{
    		rowclick:function(g, rowIndex, e){
        		selectedSelRes = g.store.getAt(rowIndex);
				selecteRowIndex = rowIndex;
        		var hzzpid = selectedSelRes.data.hzzpid;
        		var qmzpid = selectedSelRes.data.qmzpid;
				if(hzzpid &&　hzzpid != 0){
					Ext.getCmp('hzImg').body.update("<IMG SRC='yw/common/img/jwhrender/" + hzzpid + "' width='100%' height='100%' />");
				}else{
					Ext.getCmp('hzImg').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
				}
				if(qmzpid &&　qmzpid != 0){
					Ext.getCmp('qmImg').body.update("<IMG SRC='yw/common/img/jwhrender/" + qmzpid + "' width='100%' height='100%' />");
				}else{
					Ext.getCmp('qmImg').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
				}
    		}
    	}
	});
	var p1_1 = new Ext.Panel({
		xtype: 'panel',
		border:false,
		frame: false,
    	region:'north',
    	height: 50,
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
				html:'单位代码',
				border:false,
				frame: false
			},{
				xtype:'search_combox',
				anchor:'100%',
				id:'qhdm',
				name:'qhdm',
				width: 150,
				searchUrl : "dict/utils/searchXxb?visiontype=DWXXB&whflg=1"
			},{
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
    		},{
				width: 100,
				html:'居委会代码',
				border:false,
				frame: false
			},{
				xtype:'search_combox',
				anchor:'100%',
				id:'dm',
				name:'dm',
				width: 150,
				searchUrl : "dict/utils/searchXxb?visiontype=JWHXXB"
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
    		    	id:'fzId',
        			text:'查询',
        			minWidth:80,
        			handler:function(){
						var store = grid5011.store;
						store.baseParams = {
								dwdm:Ext.getCmp('qhdm').getValue(),
								dm:Ext.getCmp('dm').getValue(),
								qybz:1
						};
						store.load({params:{start:0, limit:20}});	
//						store.on("load",function(store) {  
//							if(store.data.length > 0){
//								curIndex = 0;
//								jwhxxGrid.fireEvent("rowclick",jwhxxGrid,curIndex);
//								jwhxxGrid.getSelectionModel().selectRange(curIndex,curIndex);
//							}
//						},jwhxxGrid); 
					}
        		})]
    		},{
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
    		}]
    });

	var p1 = new Ext.Panel({
		layout:'border',
		items:[
		       p1_1,
		       grid5011,{
			    title: '',
			    collapsible: false,
			    region:'east',
			    height:100,
			    border:false,
			    frame:false,
			    width:"12%",
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
					id:'hzImg',
    		 		title: '',
    		 		height:'100%',
    		 		bodyStyle:'padding:10px 10px 10px 10px',
    				width:120,
    				height:100,
    				rowspan: 1,
    				colspan:1,
    				listeners: {
    	                render: function(c) {
    	                c.body.on('click', function() { 
    	                   //TODO 添加点击事件功能           
    	                	if(selectedSelRes){
    	                		var jwhZpWindow = new Gnt.ux.SelectJwhZpAll({
    	                			returnTitleText:'会章照片'
    	                		});	
    	                		jwhZpWindow.setData(selectedSelRes.data.hzzpid);
        	                	jwhZpWindow.show();
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
			    },new Ext.Button({
					text:'会章照片上传',
					minWidth:100,
					handler:function(){
						if(selectedSelRes){
							btnUpload_click('会章照片上传',selectedSelRes.data.dm,selectedSelRes.data.hzzpid,selectedSelRes.data.qmzpid,1,	function callback() {
								grid5011.store.reload();
								grid5011.fireEvent("rowclick",grid5011,selecteRowIndex);
								grid5011.getSelectionModel().selectRange(selecteRowIndex,selecteRowIndex);
							});


						}else{
							alert("请选择一条有效数据！");
							return;
						}
					}
			    }),{
			    	height:10,
			    	border:false,
			    	frame:false
			    },{
					id:'qmImg',
    		 		title: '',
    		 		height:'100%',
    		 		bodyStyle:'padding:10px 10px 10px 10px',
    				width:120,
    				height:100,
    				rowspan: 1,
    				colspan:1,
    				listeners: {
    	                render: function(c) {
    	                c.body.on('click', function() { 
    	                   //TODO 添加点击事件功能           
    	                	if(selectedSelRes){
    	                		var jwhZpWindow = new Gnt.ux.SelectJwhZpAll({
    	                			returnTitleText:'签名照片'
    	                		});	
    	                		jwhZpWindow.setData(selectedSelRes.data.qmzpid);
        	                	jwhZpWindow.show();
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
			    },new Ext.Button({
					text:'签名照片上传',
					minWidth:100,
					handler:function(){
						if(selectedSelRes){
							btnUpload_click('签名照片上传',selectedSelRes.data.dm,selectedSelRes.data.hzzpid,selectedSelRes.data.qmzpid,2,	function callback() {
								grid5011.store.reload();
								grid5011.fireEvent("rowclick",grid5011,selecteRowIndex);
								grid5011.getSelectionModel().selectRange(selecteRowIndex,selecteRowIndex);
							});
						}else{
							alert("请选择一条有效数据！");
							return;
						}
					}
			    })]
			}
		]
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