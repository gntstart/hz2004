
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//释放iframe占用资源
	function fixIFrame(o, p){
		var iFrame = p.getEl().dom; 
		if (iFrame.src) {
			iFrame.src = "javascript:false"; 
		}
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
        activeTab: 0,
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
			id : "rkxx",
//			url: 'cx/hjjbxx/ckxx/queryRkxx',
			autoLoad: { url: 'cx/hjjbxx/ckxx/queryRkxx', params: { rynbid: getRequest("rynbid") }, method: 'GET' },
			//tabTip : "人口信息",
			title : "人口信息"
        },{
			id : "sbxx",
//			url: 'cx/hjjbxx/ckxx/querySbxx',
//			autoLoad: { url: 'cx/hjjbxx/ckxx/querySbxx', params: { data: "从客户端传入的参数" }, method: 'GET' },
			//tabTip : "常口信息查询",
			title : "四变信息"
            
        },{
			id : "bggz",
			url : "cx/hjjbxx/ckxx",
			//tabTip : "常口信息查询",
			title : "变更更正"
            
        },{
			id : "hdxx",
			url : "cx/hjjbxx/ckxx",
			//tabTip : "常口信息查询",
			title : "户地信息"
            
        },{
			id : "hcybd",
			url : "cx/hjjbxx/ckxx",
			//tabTip : "常口信息查询",
			title : "户成员变动"
            
        },{
			id : "zjxx",
			url : "cx/hjjbxx/ckxx",
			//tabTip : "常口信息查询",
			title : "证件信息"
            
        },{
			id : "slxx",
			url : "cx/hjjbxx/ckxx",
			//tabTip : "常口信息查询",
			title : "受理信息"
            
        },{
			id : "hgl",
			url : "cx/hjjbxx/ckxx",
			//tabTip : "常口信息查询",
			title : "户关联"
            
        },{
			id : "hcy",
			url : "cx/hjjbxx/ckxx",
			//tabTip : "常口信息查询",
			title : "户成员"
            
        },{
			id : "xzd",
			url : "cx/hjjbxx/ckxx",
			//tabTip : "常口信息查询",
			title : "现住地"
            
        },{
			id : "dyxx",
			url : "cx/hjjbxx/ckxx",
			//tabTip : "常口信息查询",
			title : "打印信息"
        }]
    });
	
	//单击tab4后触发的事件
	function handleActivate(tab) {
		tabs.setActiveTab(tab.id);
		//alert(tab.url);
	}
    
	function getRequest(strParame) {
        var args = new Object();
        var query = location.search.substring(1);

        var pairs = query.split("&"); // Break at ampersand
        for (var i = 0; i < pairs.length; i++) {
            var pos = pairs[i].indexOf('=');
            if (pos == -1) continue;
            var argname = pairs[i].substring(0, pos);
            var value = pairs[i].substring(pos + 1);
            value = decodeURIComponent(value);
            args[argname] = value;
        }
        return args[strParame];
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
        	items:[tabs]
        }
    });
	
	/**
		按下回车键执行查询操作
	 */
	var key1 = new Ext.KeyMap(document, {
		key:Ext.EventObject.ENTER,
		fn:function() {
			var data = form10010.getForm().getValues();
			if(Gnt.util.isEmpty(data)){
				showInfo("至少输入一个查询条件！");
				return;
			}
			
			var store = xzdGrid.store;
			
			store.baseParams = data;
			store.load({params:{start:0, limit:500}})
		},
		scope: this  
	});
	
	
});