var pageSize = 50;
var jsRecord = null;
var xmNode = null;
var isinit = false;
var jsWin = null;
var lastdq = null;
var lastjs = null;

Ext.onReady( function() {
    Ext.QuickTips.init();
	
	function disableBtn(v){
		//Ext.getCmp("b0").setDisabled(v);
		//Ext.getCmp("b1").setDisabled(v);
		//Ext.getCmp("b2").setDisabled(v);
		//Ext.getCmp("b3").setDisabled(v);
		//Ext.getCmp("b4").setDisabled(v);
		
		//Ext.getCmp("a0").setDisabled(v);
		//Ext.getCmp("a1").setDisabled(v);
		//Ext.getCmp("a2").setDisabled(v);
	}
	
//	var projectTreeNode = new Ext.tree.AsyncTreeNode({
//		text:'根'
//	});
	
//	var projectTreeLoader = new Ext.tree.TreeLoader({
//		dataUrl		: 'sq.do?method=queryProjectTree',
//		listeners	:{
//			loadexception:function(tree,node,response){
//				Ext.Msg.alert("提示","项目树加载失败：" + response.responseText);
//			}
//		}
//	});
	
//	projectTreeLoader.on("load", function(treeLoader, node, response) {
//			//在beforeload事件中每次动态传递点击的节点的value属性作为参数
//			var o = Ext.decode(response.responseText);
//			if(o.messages && o.messages.length>0){
//				Ext.Msg.alert("提示",o.messages[0]);
//			}
//		}, 
//		projectTreeLoader
//	);
	
//	var projectTree = new Ext.tree.TreePanel({
//	   	autoScroll:true,
//	   	root			:projectTreeNode,
//	   	rootVisible 	:false,
//	   	title:'角色地区',
//	   	region          :'west',
//	   	loader			:projectTreeLoader,
//	   	width			:300,
//	   	height			:400
//	});
	
	function setXm(){
//		var code = xmNode.attributes.dw;
//		lastdq = xmNode.attributes.dw;
//		lastjs = null;
		
		//依据项目编码，查询该项目下面的所有角色
		jsStore.removeAll();
		//jsStore.baseParams = code;
		jsStore.load({params:{start:0, limit:pageSize}})
				
		disableBtn(true);
	}
	
//	projectTree.on("click", function(node,e) {
//			if(node.attributes.dw){
//				xmNode = node;
//				setXm();
//			}
//		}, 
//		projectTree
//	);
	
	//角色
 	var jsStore = new Ext.data.JsonStore({
	        url: 'gl/jswh/queryJs',
	        root: 'list',
	        id:'jsid',
	        totalProperty:'totalCount',
	        fields: [
				"jsid",
				"jsmc",
				"ms",
				"dqbm"
	        ],
	        listeners:{
	        	load:function(mystore,res){
	
	        	},
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
	var jsCsm = new Ext.grid.CheckboxSelectionModel({singleSelect:true});
	var jsColModel = new Ext.grid.ColumnModel([
		jsCsm,{
	        header: "角色名称",
	        dataIndex: "jsmc",
	        sortable: true,
		width: 100
	    },{
	        header: "角色描述",
	        dataIndex: "ms",	
	        sortable: true,
		width: 200
	    }
	]);

	var jsGrid = new Ext.grid.GridPanel({
	        store: jsStore,
	        region: 'west',
	        view:new Ext.grid.GridView({
				forceFit:true,
				autoFill:true,
				enableRowBody:true
		}),
		stripeRows: true,
        	cm: jsColModel,
        	sm: jsCsm,
		loadMask: {msg:'正在加载数据，请稍侯……'},
		bodyStyle:'width:100%',
        	border:true,
       	 	anchor:'100% 100%',
       		 width:350,
	    	margins: '0 0 0 0',  
        	frame:false,
		iconCls:'icon-grid',
		listeners:{
			rowclick:function(g, rowIndex, e){
				var r = g.store.getAt(rowIndex);
				
				jsRecord = r;
				var data = r.data;
				lastjs = r.data;
				
				//ysqTree.setTitle("【" + lastdq.mc + "】的【"+ lastjs.jsmc + "】角色权限分配");
				ysqTree.setTitle("【"+ lastjs.jsmc + "】角色权限分配");
				funcYsqTreeLoader.baseParams = data;
				funcYsqTreeNode.reload();
			},
			rowdblclick:function(g, rowIndex, e){

			}
		},
	        title:'角色列表',
	        id:'jsGrid',
		bbar:[
			{
				xtype:"button",
				text:"修改角色",
				minWidth:75,
				handler:function(c){
					var res = jsCsm.getSelections();
					if(res.length==0){
						Ext.Msg.alert("提示","必须选择需要修改的角色！");
						return;
					}
					var r = res[0];
					jsWin.show();

					jsWin.setTitle("修改角色");
					jsFs.getForm().setValues(r.data);
					
				}
            		}, {
            			text:"新增角色",
				xtype:"button",
				minWidth:75,
				handler:function(c){
					
					jsWin.show();
					jsWin.setTitle("新增角色");
					jsFs.getForm().reset();
					jsFs.getForm().setValues({dqbm:'214000'});
				}
           		 }, {
            			text:"删除角色",
				xtype:"button",
				minWidth:75,
				handler:function(c){
					var res = jsCsm.getSelections();
					if(res.length==0){
						Ext.Msg.alert("提示","必须选择需要删除的角色！");
						return;
					}
					
					var ids = "";
	   				for(var i=0;i<res.length;i++)
	   					ids += res[i].data.jsid + ",";
	   					
	   				Ext.Msg.show({
					   title:'提示',
					   msg: '确认需要删除选择的角色吗？',
					   buttons: Ext.Msg.YESNO,
					   fn: function(btn,text){
					  		if(btn=="yes"){
					  			save("deleteJs",{ids:ids},"正在删除角色，请稍后...");
					  		}
					  	}
					});
				}
            		}, {
            			text:"导出",
        				xtype:"button",
        				minWidth:75,
        				handler:function(c){
        					window.location.href = basePath+'/gl/jswh/daochu'; 
        				}
                    		}
			]
   	 });	

	var jsFs = new Ext.form.FormPanel({
    	height: 100,
    	region: 'north',
    	anchor:'100% 100%',
    	buttonAlign:'right',
    	labelAlign:'right',
    	frame:true,
    	border:false,
    	fileUpload: true, 
    	margins:'0',
        layout:'form',
        labelWidth:95,
       	items:[{
        		layout:'column',
    			frame:false,
    			border:false,
        		defaults:{
        			border:false,
        			frame:false
        		},
            	items:[{
            		name:'jsid',
            		xtype:'hidden'
            	},{
            		name:'dqbm',
            		xtype:'hidden'
            	},{
	                columnWidth:1,
    	           	layout: 'form',
    	           	bodyStyle:'padding:5 5 0 0 ',
        	       	items: [{
						xtype:'textfield',
						anchor:'100%',
						name:'jsmc',
						allowBlank:false,
						fieldLabel:'<span style="color:red">*</span>角色名称'
					}]
				},{
	                columnWidth:1,
    	           	layout: 'form',
    	           	bodyStyle:'padding:5 5 0 0 ',
        	       	items: [{
						xtype:'textfield',
						anchor:'100%',
						name:'ms',
						fieldLabel:'<span style="color:red">*</span>角色描述'
					}]
				}
			]
		}],
		buttons:[
			new Ext.Button({
				text:'保存',
				minWidth:75,
				id:'saveBtnMove',
				handler:function(){
					//移动操作
					if(!jsFs.getForm().isValid()){
						Ext.Msg.alert("提示","数据校验没有通过，请检查！");
						return;
					}
					
					var data = jsFs.getForm().getValues();
					if(!data.dqbm || data.dqbm==""){
						Ext.Msg.alert("提示","未知地区！");
						return;
					}
					
					save("saveJs",data,"正在保存角色，请稍后...");
				}
			}),
			new Ext.Button({
				text:'关闭',
				minWidth:75,
				id:'closeBtnMove',
				handler:function(){
					jsWin.hide();
				}
			})
		]
	});
    
	var sfbz = new Ext.data.SimpleStore({
		id:0,
		fields:[{name: 'code', mapping: 0},{name: 'name', mapping: 1}],
	   	data:[
	   		['1','是'],
			['0','否']]
	});
	
	Ext.ux.CellContextMenu = function(){
	    var menu,mynode;
	    
	    //实现插件的init方法
	    this.init = function(tree){
	        tree.on('contextmenu', onContextMenu);
	    }
		
		//可用参数分别是：节点、事件
		function onContextMenu(node,e){
	  		mynode = node;
	  		//判断菜单是否创建，已确保只创建一次
	        	if(!menu){
	       	 		menu = new Ext.menu.Menu({
					id:'rightClickCont',
				    	items: [{
				    		id: 'rMenu1',
						handler: function(){
			            			mynode.expand(true);
						},
				        	text: '授权所有'
					},{
				    		id: 'rMenu2',
						handler: function(){
							mynode.collapse(true);
						},
						text: '取消所有'
					},{
				    		id: 'rMenu3',
						handler: function(){
							mynode.collapse(true);
						},
						text: '授权'
					},{
				    		id: 'rMenu4',
						handler: function(){
							mynode.collapse(true);
						},
						text: '取消授权'
					}]
				});
	        	}
	        
	        	//获取菜单的MixedCollection，这里可以对菜单做进一步控制
	      		var items = menu.items;

	       		 //在鼠标右击处显示菜单
	      		if(!mynode.leaf){
	      			items.get(0).setText("授权【" + mynode.attributes.text + "】所有功能");
	      			items.get(1).setText("取消【" + mynode.attributes.text + "】所有功能授权");
	      			items.get(2).setText("授权");
	      			items.get(3).setText("取消");
	      			items.get(0).setDisabled(false);
	      			items.get(1).setDisabled(false);
	      			items.get(2).setDisabled(true);
	      			items.get(3).setDisabled(true);
	      		}else{
	      			items.get(0).setText("授权所有功能");
	      			items.get(1).setText("取消所有功能授权");
	      			
	      			items.get(2).setText("授权【" + mynode.attributes.text + "】");
	      			items.get(3).setText("取消【" + mynode.attributes.text + "】");
	      			
	      			items.get(0).setDisabled(true);
	      			items.get(1).setDisabled(true);
	      			items.get(2).setDisabled(false);
	      			items.get(3).setDisabled(false);
	      		}
	      		menu.showAt(e.getPoint());
	    	}
	};
	
	var funcYsqTreeNode = new Ext.tree.AsyncTreeNode({
		text:'根'
	});
	
	var funcYsqTreeLoader = new Ext.tree.TreeLoader({
		dataUrl		: 'gl/jswh/queryYsqJSTree',
		listeners	:{
			loadexception:function(tree,node,response){
				Ext.Msg.alert("提示","加载失败：" + response.responseText);
			},
			beforeload:  function(treeLoader, node) {
				var suc = (jsRecord==null?false:true);
				if(suc){
					Ext.Msg.wait("加载权限，请稍后...");
				}
				return suc;
			}
		}
	});
	
	funcYsqTreeLoader.on("load", function(treeLoader, node, response) {
			Ext.Msg.hide();
			
			//在beforeload事件中每次动态传递点击的节点的value属性作为参数
			var o = Ext.decode(response.responseText);
			if(o.messages && o.messages.length>0){
				Ext.Msg.alert("提示",o.messages[0]);
			}
		}, 
		funcYsqTreeLoader
	);
	
	var lastnode = null;
	var boolvalue = false;
	var issaveing = false;
	
	var ysqTree = new Ext.tree.TreePanel({
	   	autoScroll:true,
	   	title: '授权情况',
	   	plugins: new Ext.ux.CellContextMenu(),
	   	root			:funcYsqTreeNode,
	   	rootVisible 	:false,
	   	region          :'center',
	   	loader			:funcYsqTreeLoader,
	   	width			:400,
	   	listeners		:{
	    		checkchange:function(node,checked){
	    			if(issaveing==true)
	    				return;
	    			
	    			lastnode = node;
	    			boolvalue = checked;
	    			
	    			var data = null;
	    			if(node.attributes.gncdb){
	    				data = node.attributes.gncdb;
	    			}
	    			
	    			if(node.attributes.gnb){
	    				data = node.attributes.gnb;
	    			}
	    			
	    			if(node.attributes.ywmb){
	    				data = node.attributes.ywmb;
	    			}
	    			
	    			if(data==null){
	    				Ext.Msg.alert("提示","未知功能！");
	    				return;
	    			}
	    			
	    			//保存
				issaveing = true;
				data.optype = (checked?"add":"remove");
				data.jsid = lastjs.jsid;
//				data.dwdm = lastdq.dm;
				
				//alert(Ext.encode(lastjs));
				Gnt.submit(
						data, 
						"gl/jswh/saveJsFunc", 
						"正在进行菜单修改，请稍后...", 
						function(jsonData,params){
							issaveing = false;
							showInfo("角色菜单权限修改成功！");
						},function(){
							lastnode.getUI().toggleCheck(!boolvalue);
							issaveing = false;
							showInfo("角色菜单权限修改失败！");
						}
				);
//				Ext.Msg.wait("正在移动警员，请稍后...", "等待");
//				Ext.Ajax.request({
//					url:'gl/jswh/saveJsFunc',
//					method:'POST', 
//					params:data,
//					success:function(result,request){ 
//						Ext.Msg.hide();
//							
//						var jsonData = Ext.util.JSON.decode(result.responseText);
//						if(jsonData.success){
//							issaveing = false;
//						}else{
//							lastnode.getUI().toggleCheck(!boolvalue);
//							issaveing = false;
//							showError(jsonData.messages[0]);
//						}
//					}, 
//					failure: function ( result, request) {
//						Ext.Msg.hide();
//						lastnode.getUI().toggleCheck(!boolvalue);
//						showError(result.responseText); 
//						issaveing = false;
//					} 
//				});
	    		}
	   	},
	   	height:400
	});
	
	new Ext.Viewport({
	       layout:'border',
	       id:'vp',
	       items:[{
	            region:'center',
	            //禁止横向滚动条
	            layout:'border',
	            border:false,
	            frame:false,
	            margins:'5 5 5 0',
	            autoScroll:true,
	            items:[
	            	jsGrid,
	            	{
			      region:'center',
			       //禁止横向滚动条
			       layout:'border',
			        border:false,
			         frame:false,
			         margins:'0 5 0 5',
			         autoScroll:true,
			        items:[
			           	ysqTree
			         ]
	            	}
	            ]
	        }/*,{
	            region				:'west',
	            layout				:'fit',
	            border				:false,
	            frame				:false,
	           	bodyStyle			:'overflow:scroll;overflow-x:hidden',
	            margins				:'5 5 5 5',
	            autoScroll			:true,
	            collapsible			:true,
           		splitTip			: "拖动来改变尺寸.",	//SplitRegion的构造参数
            	collapsibleSplitTip	:'拖动来改变尺寸. 双击隐藏',
	            split				:false,
		collapseMode		:'mini',//SplitRegion的构造参数
	            width				:200,
	            items				:[projectTree]
	        }*/]
	});
	
	disableBtn(true);
	
	jsWin = new Ext.Window({
		title:'修改',
		closeAction:'hide',
		modal:true,
		width:400,
		height:150,
		margins:'5',
		layout:'fit',
		items:jsFs
	});
	
	function showError(msg,title){
		Ext.MessageBox.show({
			title: title?title:"操作失败",
			msg: msg,
			buttons:Ext.Msg.OK,
			icon:Ext.Msg.ERROR
		});
	}

	function save(method,param,msg){
		Gnt.submit(
				param, 
				"gl/jswh/"+method, 
				msg, 
				function(jsonData,params){
					//成功
					if(method=="deleteJs"){
						var res = jsCsm.getSelections();
						Ext.each(res,function(r){
							jsStore.remove(r);
						});
					}else if(method=="saveJs"){
						jsWin.hide();
						
						//角色
						var newjs = jsonData.list[0];
						var r = jsStore.getById(newjs.jsid);
						if(r){
							for(o in newjs){
								r.set(o, newjs[o]);
							}
						}else{
							var r1 = new jsStore.reader.recordType(newjs,newjs.jsid);
							jsStore.add([r1]);
						}
					}else if(method=="saveJsFunc"){
						funcStore.load({params:{start:0, limit:pageSize}});
					}
					showInfo("操作成功！");
				}
		);
//		if(msg)
//			Ext.Msg.wait(msg, "等待");
//		else
//			Ext.Msg.wait("操作正在执行中，请稍后...", "等待");
			
//		Ext.Ajax.request({
//			url:'sq.do?method=' + method,
//			method:'POST', 
//			params:param,
//			success:function(result,request){ 
//				Ext.Msg.hide();
//				var jsonData = Ext.util.JSON.decode(result.responseText);
//				//alert(result.responseText);
//				if(jsonData.success){
//					if(jsonData.messages && jsonData.messages.length>0){
//						Ext.Msg.alert("提示",jsonData.messages[0]);
//					}else{
//						Ext.Msg.alert("提示","操作执行成功！");
//					}
//					
//					//成功
//					if(method=="deleteJs"){
//						var res = jsCsm.getSelections();
//						Ext.each(res,function(r){
//							jsStore.remove(r);
//						});
//					}else if(method=="saveJs"){
//						jsWin.hide();
//						
//						//角色
//						var newjs = jsonData.list[0];
//						var r = jsStore.getById(newjs.jsid);
//						if(r){
//							for(o in newjs){
//								r.set(o, newjs[o]);
//							}
//						}else{
//							var r1 = new jsStore.reader.recordType(newjs,newjs.jsid);
//							jsStore.add([r1]);
//						}
//					}else if(method=="saveJsFunc"){
//						funcStore.load({params:{start:0, limit:pageSize}});
//					}else if(method="saveZzjyJs"){
//						yhStore.load({params:{start:0, limit:pageSize}});
//					}
//				}else{
//					if(jsonData.messages && jsonData.messages.length>0){
//						showError(jsonData.messages[0]);
//					}else{
//						showError("操作执行失败！");
//					}
//				}
//			}, 
//			failure: function ( result, request) {
//				Ext.Msg.hide();
//				showError(result.responseText); 
//			} 
//		}); 
	}
	setXm();
});