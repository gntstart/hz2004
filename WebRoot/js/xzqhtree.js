	/****************************选择行政区划-派出所-居委会的树**********************************/
	var _SJFWDWDM = "";
	
	Ext.ux.CellContextMenu = function(){
	    //实现插件的init方法
	    this.init = function(tree){
	        tree.on('contextmenu', onContextMenu);
	        mytree = tree;
	    }
		
		//可用参数分别是：节点、事件
	    function onContextMenu(node,e){
	    	if(mytree._TreeOnContextMenu){
	    		mytree._TreeOnContextMenu(node,e);
	    	}
	    }
	};

	var _XZQHTreeLoader = new Ext.tree.TreeLoader({
		dataUrl		: 'util/dict?method=queryXZQHTree',
		listeners	:{
			loadexception:function(tree,node,response){
				Ext.Msg.alert("加载失败",response.responseText);
			}
		}
	});

	_XZQHTreeLoader.on("beforeload", function(treeLoader, node) {
			if(node.attributes.dw){
		       		this.baseParams = {pcs: node.attributes.dw.dm,dwdm:_SJFWDWDM};
		       	}else if(node.attributes.xzqh){
		       		//展开行政区划
		       		this.baseParams = {xzqh:node.attributes.xzqh.dm,dwdm:_SJFWDWDM}; 
		       	}else if(node.attributes.jwh){
		       		this.baseParams = {dwdm:_SJFWDWDM}; 
		       	}else{
		       		this.baseParams = {dwdm:_SJFWDWDM}; 
		       	}
		}, 
		_XZQHTreeLoader
	);

	_XZQHTreeLoader.on("load", function(treeLoader, node, response) {
			//在beforeload事件中每次动态传递点击的节点的value属性作为参数
			var o = Ext.decode(response.responseText);
			if(o.messages && o.messages.length>0){
				alert(o.messages[0]);
			}
		}, 
		_XZQHTreeLoader
	);
	
	_XZQHTreeLoader.on("loadexception", function(treeLoader, node, response) {
			alert(response.innerHTML);
		}, 
		_XZQHTreeLoader
	);
	
	var _XZQHTreeLoaderRoot = new Ext.tree.AsyncTreeNode({
		text:'根'
	});
	
	var _XZQHTree = new Ext.tree.TreePanel({
	   	autoScroll:true,
	   	plugins : Ext.ux.CellContextMenu?new Ext.ux.CellContextMenu():null,
	   	root			:_XZQHTreeLoaderRoot,
	   	rootVisible 	:false,
	   	border:true,
	   	frame:false,
	   	region          :'center',
	   	loader			:_XZQHTreeLoader,
	   	width			:300,
	   	height			:500,
	   	listeners		:{
	    		checkchange:function(node,checked){
	    			node.getOwnerTree().suspendEvents();
				if(checked){
					Ext.each(_XZQHTree.getChecked(),function(item){
						item.getUI().toggleCheck(false);
					});
					node.getUI().toggleCheck(true);
				}
				node.getOwnerTree().resumeEvents();
	    		}
	    	}
	});
	
	var _XZQHForm = new Ext.form.FormPanel({
		title:'',
	    	height: 65,
	    	region          :'north',
	    	region: 'north',
	    	anchor:'100% 100%',
	    	buttonAlign:'right',
	    	labelAlign:'right',
	    	frame:false,
	    	border:false,
	    	margins:'0',
	        layout:'form',
	        labelWidth:60,
	       	items:[{
        		layout:'column',
    			frame:false,
    			border:false,
        		defaults:{
        			border:false,
        			frame:false
        		},
            		items:[{
		                columnWidth:1,
	    	           	layout: 'form',
	    	           	bodyStyle:'padding:10 10 10 10',
	        	       	items: [{
						xtype:'combo',
						anchor:'100%',
						name:'xqlx',
						mode:'local',
						hiddenName:'xqlx',
						valueField:'code',
				    		displayField:'name',
						fieldLabel:'辖区类型',
						triggerAction:'all', 
						value:'1',
						store:new Ext.data.SimpleStore({
							fields:[
								{name: 'name', mapping: 1},
								{name:'code',mapping:0}
							],
					    		data:[
								['1','辖区内'],
								['2','辖区外']
							]
						})
					}]
				},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:0 10 10 10',
		        	       	items: [{
		        	       		xtype:'label',
						text:'从下面选择一个具体区域范围'
					}]
				}
			]
		}]
	});
	
	
	var _XZQHWin = new Ext.Window({
		title:'数据范围设置',
		closeAction:'hide',
		modal:true,
		width:400,
		height:550,
		margins:'5',
		layout:'border',
		items:[_XZQHForm, _XZQHTree],
		listeners:{
			show:function(){
				Ext.each(_XZQHTree.getChecked(),function(item){
					item.getUI().toggleCheck(false);
				});
			}
		},
		buttons:[
			new Ext.Button({
				text:'确定',
				minWidth:75,
				handler:function(){
					var node = null;
					Ext.each(_XZQHTree.getChecked(),function(item){
						node = item;
					});
					if(node==null){
						Ext.Msg.alert("提示","必须选择一个数据项！");
						return;
					}
					
					var xqlx = _XZQHForm.getForm().getValues().xqlx;
					
					var sjfw = "";
					if(node.attributes.jwh)
						sjfw = node.attributes.jwh.dm;
					else if(node.attributes.xzqh)
						sjfw = node.attributes.xzqh.dm;
					else if(node.attributes.dw)
						sjfw = node.attributes.dw.dm;
		       	
					_XZQHWin.hide();
					if(_XZQHWin.callback){
						_XZQHWin.callback(xqlx, sjfw);
					}
				}
			}),
			new Ext.Button({
				text:'取消',
				minWidth:75,
				handler:function(){
					_XZQHWin.hide();
				}
			})
		]
	});
	
	function _selectSjfw(dwdm, callback){
		var isnew = false;
		if(_SJFWDWDM=="" || _SJFWDWDM.substring(0,4) != dwdm.substring(0,4))
			isnew = true;
		
		_SJFWDWDM = dwdm;
		_XZQHWin.callback = callback;
		_XZQHWin.show();

		if(isnew)
			_XZQHTreeLoaderRoot.reload();
	}
