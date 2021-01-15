var _s1 = "ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗxｙｚＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ１２３４５６７８９０，；‘“。＊＆％＄＃＠！？《》【】－＝＋（）";
var _s2 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890,;'\".*&%$#@!?<>[]-=+()";

//将半角字符转换为全角字符，放在标题出现半角导致滚动条
function getQj(str){
	var len = str.length;
	var seek = -1;
	
	for(var i=0;i<len;i++){
		seek = _s2.indexOf(str.charAt(i));
		if(seek>=0){
			str = str.substring(0,i) + _s1.charAt(seek) + str.substring(i+1);
		}
	}
	
	return str;
}
function spclInit(){
	if(location&&location.pathname){
		if(location.pathname=='/rkxt/yw/spgl/spcl'){
			openWorkSpace('yw/spgl/spcl/type1','审批处理','170004');
			Ext.getCmp('west-panel').collapse();
		}else if(location.pathname=='/rkxt/yw/spgl/hjsp'){
			openWorkSpace('yw/spgl/hjsp/type1','户籍审批','170002');
			Ext.getCmp('west-panel').collapse();
		}
		
	}
}
//利用IFRAME填充容器
Ext.ux.IFrameComponent = Ext.extend(Ext.BoxComponent, {
	onRender:function(ct, position){
		Ext.getCmp("word-status").showBusy('正在加载功能页面[' + this.title + ']，请等待...');
		//注意：页面文件必须遵循本书规范，否则就可能发生页面不能打开的错误
		var id = 'iframe-'+ this.id;
        this.el = ct.createChild({
        	tag: 'iframe', 
        	id: id, 
        	frameBorder: 0, 
        	src:this.url
        });
        Ext.fly(id).on("load",function(){
        	(function(){
				Ext.getCmp("word-status").clearStatus({useDefaults:true,anim:true});
        	}).defer(500);
        })
    }
});

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

function closeWorkSpace(jsonData, tab_key){
	var tabs = Ext.getCmp("tabs");
	var tab = tab_key?tabs.getItem(tab_key):tabs.getActiveTab();
	if(tab)
		tabs.remove( tab );
}

//打开指定工作区
function openWorkSpace(url,name,key,desc,parm){
	name = getQj(name);

	var tabs = Ext.getCmp("tabs");
	
	//如果文件已经打开，那么设置为活动分页，并返回
   	if(!tabs.findById(key)){
	   	 var p = null;
	   	 //利用iframe显示页面
	   	 p = new Ext.ux.IFrameComponent({
	   	 		xtype:'panel',
	   	 		id:key,
	   	 		url:url,
	   	 		frame:false,
	   	 		iconCls:'otherfile',
		   		title: name,
			    tabTip: desc?desc:name
		});
	   	//添加分页，并且设置为活动分页
	    tabs.add(p);
   	}else{
   		var id = 'iframe-'+ key;
   		var el = Ext.get(id);
   	}
   	
    //推荐使用key，而不是使用p来激活页面，否则很可能发生脚本错误
    tabs.setActiveTab(key);
}

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
        listeners:{
        	//在关闭分页的时候，调用函数释放iframe占用资源
        	beforeremove:fixIFrame.createDelegate(this)
        },
        plugins: new Ext.ux.TabCloseMenu(),
        defaults:{
        	closable:true,
        	autoScroll: false,
        	frame: false,
        	shim: false,
        	xtype: 'panel'
        },
        items:[/*{
        	//本页不允许关闭
        	closable: false,
            title: '欢迎使用',
            tabTip:'欢迎使用',
            html: '<br/><h1>&nbsp;&nbsp;欢迎使用管理平台!!!</h1>'
        },*//*{
			id : "echartsZl",
			closable: false,
            title: '欢迎使用',
            tabTip:'欢迎使用',
			title : "人口数据总览",
			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
				activate:function(){
					var htmlStrStart = '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="cx/echartsZl';
					var htmlStrEnd = '"></iframe>';
					Ext.getCmp('echartsZl').body.update(htmlStrStart + htmlStrEnd);
					this.getUpdater().refresh();
					
				}
			}
        },*/{
        	//本页不允许关闭
        	closable: false,
            title: '网上通告',
            tabTip:'网上通告',
            html: '<br/><h1>&nbsp;&nbsp;通告内容</h1>'+
            '&nbsp;&nbsp;<a style="text-decoration:underline">链接1<a><br/>'+
            '&nbsp;&nbsp;<a href="">链接2<a><br/>'
        },{
			id : "echartsZl",
			closable: false,
            title: '欢迎使用',
            tabTip:'欢迎使用',
			title : "人口数据总览",
			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
				activate:function(){
					var htmlStrStart = '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="cx/echartsZl';
					var htmlStrEnd = '"></iframe>';
					Ext.getCmp('echartsZl').body.update(htmlStrStart + htmlStrEnd);
					this.getUpdater().refresh();
					
				}
			}
        }]
    });
    //这里构造一个异步节点作为根，表示数据需要利用树加载器通过AJAX请求获取
    var tree = new Ext.tree.TreePanel({
   	 	autoScroll:true,
   	 	root:root,
   	 	//rootVisible:false,
    	width:200,
    	height:400,
   	 	listeners:{
   	 		//点击叶子节点时，利用新的分页容器构造iframe打开文件
   	 		click:function(node,e){
   	 			//如果是最终节点，那么打开选择的文件
   	 			if(node.leaf){
   	 				//获取文件相对地址
   	 				var url = node.attributes.data.url;
   	 				//以文件路径作为分页的ID
   	 				var key = node.attributes.data.code;
   	 				var name = node.text;
   	 				
   	 				openWorkSpace(url,name,key);
   	 			}
   	 		} 
   	 	}
	});
	
	//对树的节点进行排序
	new Ext.tree.TreeSorter(tree, {
    		folderSort: true,
    		dir: "desc",
    		sortType: function(node) {
    			//没有子节点的节点（文件）位于后面
    		    return node.leaf;
    		}
	});
	
	//定义数过滤器，提供节点过滤功能
	var filter = new Ext.tree.TreeFilter(tree, {
		clearBlank: true,
		autoClear: true
	});
	
	function filterTree(e){
		var text = e.target.value;
		
		//如果没有输入，那么显示所有
		if(!text){
			filter.clear();
			return;
		}
		
		//也可以先展开所有再过滤，以遍历所有可能的节点
		//tree.expandAll();
		
		//过滤树已经加载的节点（这里只过滤文件，目录除外）
		var re = new RegExp('^' + Ext.escapeRe(text), 'i');
		filter.filterBy(function(n){
			if(!n.leaf)
				return true;
			return re.test(n.text);
		});
	}
	var message_dialog = new Gnt.ux.SelectMessageDialog({
		//选择立户信息回调
		callback: function(optype, data){
			//提交数据
		}
	});
	var messagecount = new Ext.Toolbar.TextItem("<marquee>消息内容</marquee>");//<marquee>xxxxxxxxxxxxxx</marquee>
	var messageTitle = new Ext.Toolbar({
		items: [
			{text: '消息:',
			//xtype:'tbtext',	
			listeners:{
				'click':function(){
					
					message_dialog.show(user);
					message_dialog.setSelRes();
				}
			}
				} 
	    ]
	});
	var wordCount = new Ext.Toolbar.TextItem('登陆人：' + user.glyxm);
    var charCount = new Ext.Toolbar.TextItem('登陆时间: '+ user.dlsj);
    var clock = new Ext.Toolbar.TextItem('');
    
	new Ext.Viewport({
        layout:'border',
        id:'vp',
        items:[{
        	 region:'north',
        	 height:52,
        	 margins:'0px',
           	 cmargins:'0px',
           	 bodyStyle:'padding:0px',
        	 frame:false,
        	 border:false,
        	 layout:'fit',
			 id:'indexNorth',
//			 visible:true,
//			 hidden:true,
        	 title:'常住人口管理信息平台',
        	 iconCls:'icon-home',
        	 items:[new Ext.Toolbar({
        	 	frame:false,
        	 	 border:false,
        	 	items:[
        	 		//过滤文本输入，当keydown事件发生时过滤
        	 		new Ext.form.TextField({
        	 			width:210,
	        	 		listeners:{
							render: function(f){
								f.el.on('keydown', filterTree, f, {buffer: 350});
							}
						}
        	 		}),
        	 		{
		                iconCls: 'icon-expand-all',
						tooltip: '展开所有节点',
		                handler: function(){
							tree.expandAll();
		                }
	            	}, '-', {
		                iconCls: 'icon-collapse-all',
		                tooltip: '收缩所有节点',
		                handler: function(){
		               		tree.collapseAll();
		                }
	            	},'->', {
		                tooltip:'隐藏信息',
		                iconCls: 'icon-hide-inherited',
		                enableToggle: true,
		                toggleHandler : function(b, pressed){
		                   //隐藏信息的动作
		                }
		            },'-',{
		                tooltip:'隐藏/显示状态栏',
		                iconCls: 'icon-expand-members',
		                enableToggle: true,
		                toggleHandler : function(b, pressed){
		                    var st = Ext.get("word-status");
		                    if(pressed){
			                    st.setVisibilityMode(Ext.Element.DISPLAY);
			                    st.hide({
			                    	duration:1,
			                    	callback:function(){
			                    		 Ext.getCmp("vp").doLayout();
			                    	}
			                    });
		                    }else{
		                    	st.show(true);
		                    	Ext.getCmp("vp").doLayout();
		                    }
		                }
		            },'-',{
		                tooltip:'退出系统',
		                iconCls: 'icon-exit',
		                handler: function(b, pressed){
		                	Ext.Msg.show({
							   title:'退出系统',
							   msg: '确定要退出系统吗？',
							   buttons: Ext.Msg.YESNO,
							   fn: function(btn, text){
							   		if(btn=="yes"){
//							   			document.location.href = baseurl+"jyzadmin/login.jsp?op=logout"
							   			document.location.href = baseurl+"login/out"
							   		}
							   },
							   animEl: Ext.getBody(),
							   icon: Ext.MessageBox.QUESTION
							});
		                }
		            }
        	 	]
        	 })]
        },{
            region:'west',
            split:true,
            collapseMode:'mini',
            useSplitTips:true,
            splitTip: "拖动来改变尺寸.",
            collapsibleSplitTip:'拖动来改变尺寸. 双击隐藏',
            id:'west-panel',
            title:'选择管理功能',
            margins:{left:0, top: 0, right: 0, bottom:0},
            width: 250,
            layout:'fit',
            collapsible: true,
            items:[tree]
        },{
            region:'center',
            id:'portal_home',
            //禁止横向滚动条
            layout:'fit',
            border:false,
            frame:false,
           	bodyStyle:'overflow:scroll;overflow-x:hidden',
            margins:'0',
            autoScroll:true,
            items:[tabs]
        }, new Ext.StatusBar({
            id: 'word-status',
            region:'south',
            defaultText:'就绪',
            items: [messageTitle,'',messagecount,'',wordCount, ' ', charCount, ' ', clock, ' ']
        })]
    });
    
    //tree.expandAll();
	Ext.fly(messageTitle.getEl()).addClass('x-status-text-panel').createChild({cls:'spacer'});
	Ext.fly(messagecount.getEl().parentNode).addClass('x-status-text-panel').createChild({cls:'spacer'});
	Ext.fly(wordCount.getEl().parentNode).addClass('x-status-text-panel').createChild({cls:'spacer'});
	Ext.fly(charCount.getEl().parentNode).addClass('x-status-text-panel').createChild({cls:'spacer'});
	Ext.fly(clock.getEl().parentNode).addClass('x-status-text-panel').createChild({cls:'spacer'});

	Ext.TaskMgr.start({
		run: function(){
			Ext.fly(clock.getEl()).update(new Date().format('当前时间：g:i:s A'));
		},
		interval: 1000
	});
//	Ext.TaskMgr.start({
//		run: function(){
//			Gnt.submit(
//					{
//						jsryhid:user.yhid,
//						dwdm:user.dwdm
//						}, 
//						"yw/common/checkUnReadMessage", 
//						"正在查询是否有未读信息，请稍后...", 
//						function(jsonData,params){
//							if(jsonData.list.length>0){
//								Ext.fly(messagecount.getEl()).update("<marquee style='color:red;'>"+jsonData.list[0].message+"</marquee>");
//							}else{
//								Ext.fly(messagecount.getEl()).update("上海金铖科技发展有限公司");
//							}
//						},function(jsonData,params){},false
//			);	
//		},
//		interval: 20000
//	});	
	
//	spclInit();
	
	new Ext.KeyMap(Ext.getBody().dom, [{
	   	key: Ext.EventObject.BACKSPACE,   
	   	fn: function (key, e) {
	   		 var obj = e.target || e.srcElement;
	   		 if(obj.nodeName=='INPUT' || obj.nodeName=='TEXTAREA' || obj.nodeName=='SELECT'){
	   			 return;
	   		 }
	   		 
	         e.stopEvent(); 
	   	}
	}]);
});


/**
	登录时如果没有用户信息
 */
window.onload = function() {
	if(getQueryParam("dqbm")){
		spclInit();
		Ext.getCmp('indexNorth').hide();
		Ext.getCmp('word-status').hide();
		Ext.getCmp('vp').doLayout();
		resize_window(); 
	}else{
//		Ext.getCmp('indexNorth').hide();
//		Ext.getCmp('word-status').hide();
//		Ext.getCmp('vp').doLayout();
	}
}

function resize_window(){  
	// move the window to 0,0 (X,Y)   
	window.moveTo(0, 0);   
	// resize the window to 800x600   
	window.resizeTo(window.screen.width-window.screenLeft, window.screen.height-window.screenTop-50); 
} 