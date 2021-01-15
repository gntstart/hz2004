	/****************************选择单位，警员的公共树**********************************/
	Ext.ux.CellContextMenu = function(){
	    //实现插件的init方法
	    this.init = function(tree){
	        tree.on('contextmenu', onContextMenu);
	        this.mytree = tree;
	    }
		
		//可用参数分别是：节点、事件
	    function onContextMenu(node,e){
	    	if(this.mytree._TreeOnContextMenu){
	    		this.mytree._TreeOnContextMenu(node,e);
	    	}
	    }
	};

	var _dict = "VisionType=DW&mselect=false&showchecked=false&serviceName=zzjyManagerService&serviceMethod=queryDwxxTree";
	if(window._isAdmin && window._isAdmin!="1"){
		_dict += "&sjfw=" + window._sjfw;
	}
	
	var _UserTreeLoader = new Ext.tree.TreeLoader({
		dataUrl		: 'util/dict?method=queryTree',
		baseParams	:{dict:_dict},
		listeners	:{
			loadexception:function(tree,node,response){
				Ext.Msg.alert("加载失败",response.responseText);
			}
		}
	});

	_UserTreeLoader.on("beforeload", function(treeLoader, node) {
			//在beforeload事件中每次动态传递点击的节点的value属性作为参数
		if(node.attributes.dw){
	       		this.baseParams.dict = this.baseParams.dict + "&postDetail=" + node.attributes.dw.dwjgdm;
	       	}else if(node.attributes.js){
	       		this.baseParams.dict = this.baseParams.dict + "&postDetail=" + node.attributes.js.xmbm; 
	       	}else if(node.attributes.code){
	       		this.baseParams.dict = this.baseParams.dict + "&postDetail=" + node.attributes.code.code;
	       	}else{
	       		;
	       	}
		}, 
		_UserTreeLoader
	);

	_UserTreeLoader.on("load", function(treeLoader, node, response) {
			//在beforeload事件中每次动态传递点击的节点的value属性作为参数
			var o = Ext.decode(response.responseText);
			if(o.messages && o.messages.length>0){
				alert(o.messages[0]);
			}
		}, 
		_UserTreeLoader
	);
	
	_UserTreeLoader.on("loadexception", function(treeLoader, node, response) {
			//alert(response.innerHTML);
			alert("xxx");
		}, 
		_UserTreeLoader
	);
	
	var _UserTreeLoaderRoot = new Ext.tree.AsyncTreeNode({
		text:'根'
	});
	
	var _UserTree = new Ext.tree.TreePanel({
	   	autoScroll:true,
	   	plugins : Ext.ux.CellContextMenu?new Ext.ux.CellContextMenu():null,
	   	root			:_UserTreeLoaderRoot,
	   	rootVisible 	:false,
	   	region          :'west',
	   	loader			:_UserTreeLoader,
	   	width			:300,
	   	height			:400
	});
	
	/*
		要绑定事件，请使用如下方式：
		1、checkchange:function(node,checked);
	    2、dblclick:function(node,e);
	    3、click:function(node,e);
	    		
		_UserTree.on("click", function(node,e) {
				alert('click');
			}, 
			_UserTree
		);
	*/
