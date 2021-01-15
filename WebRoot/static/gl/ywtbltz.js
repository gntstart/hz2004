var all_qxglurl = null;
var tabs = null;
var u = null;
Ext.onReady(function(){
	Ext.QuickTips.init();

	Gnt.ux.Dict.getUser(function(user){
		u = user;
		dqbm = user.dwdm.substr(0,4);
	});
	//利用IFRAME填充容器
	Ext.ux.IFrameComponent = Ext.extend(Ext.BoxComponent, {
		initComponent : function(){
			Ext.BoxComponent.superclass.initComponent.call(this);
		},
		onRender:function(ct, position){
			//注意：页面文件必须遵循本书规范，否则就可能发生页面不能打开的错误
			var id = 'iframe-'+ this.id;
	        this.el = ct.createChild({
	        	tag: 'iframe',
	        	closable:true,
	        	id: id,
	        	frameBorder: 0,
	        	src:this.url
	        });
	        Ext.fly(id).on("load",function(){
	        	(function(){

	        	}).defer(500);
	        })
	    }
	});

	//打开指定工作区
	function createWorkSpace(isclose, url, name, key, desc, parm){
		   p = new Ext.ux.IFrameComponent({
		   	 		xtype:'panel',
		   	 		id:key,
		   	 		url:url,
		   			closable:isclose,
		   	 		frame:false,
		   	 		iconCls:'otherfile',
			   		title: name,
				    tabTip: desc?desc:name
		});

	   	return p;
	}
	function openWorkSpaceBeforeClose(tabs, isclose, url, name, key, desc, parm){
		//var tabs = Ext.getCmp("tabs");
		//如果文件已经打开，那么设置为活动分页，并返回
	   	if(tabs.findById(key)){
	   		tabs.remove(tabs.findById(key));
	   	}

		var p = createWorkSpace(isclose, url, name, key, desc, parm);
		//添加分页，并且设置为活动分页
		tabs.add(p);
		tabs.setActiveTab(p);
		return;
	}
	
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
//        activeTab: 0,
        width:500,
        height:250,
        resizeTabs:false, 
        enableTabScroll:true,
        plain:false,
        deferredRender: false,
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
        refresh:function(){
        	//alert(1111);
        	//this.getUpdater().refresh();
        	//updater.refresh();
        }
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
        	items:[tabs]
        }
    });
	var kds_config = null;
	Gnt.submit(
			{},
			"util/other/service/queryConfig",
			"获取配置，请稍后...",
			function(jsonData,params){
				if(jsonData && jsonData.list && jsonData.list.length>0){
					kds_config = jsonData.list[0];
					all_qxglurl = kds_config.all_qxglurl;
					if(all_qxglurl.substring(all_qxglurl.length-1,all_qxglurl.length)!="/"){
						all_qxglurl += "/";
					}
					var params = "authToken=" + u.authToken + "&sfzh=" + u.gmsfhm + "&yhxm=" + encodeURIComponent(u.xm) + "&yhid=" + u.yhid + "&dwdm=" + u.dwdm + "&yhdlm=" + u.yhdlm;
					var url = all_qxglurl +"yw/ywtbxx.jsp?"+params;
					// var url = "http://127.0.0.1:8080/hz2004qxgl/yw/ywtbxx.jsp?"+params;
					openWorkSpaceBeforeClose(tabs, true, url, "一网通办理页面", "kdsqccl");
				}else{
					showErr("获取跨地市配置失败！");
				}
			},function(){
			},
			false
	);

});