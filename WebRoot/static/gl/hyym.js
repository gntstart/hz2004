var htmlStrStart = '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="gl/hyym/queryZmxx';
var htmlStrEnd = '"></iframe>';

var selectZjbh = "";
var selectXm = "";
var selectSfz = "";
Ext.onReady(function(){
	Ext.QuickTips.init();
	var p1 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[
			{
				id:"centerId",
				border:false,
				frame: false,
				region:'center',
				layout:'border',
//	        	height: 300,
				items:[{
						id: "panelHtmlId" ,
						html: '<div class="text" style="text-align:center;">出生、婚姻、房产查询</div>',
						height: 80,
						region:'north',
						bodyStyle:'padding:15px'
					},
					{
		xtype: 'panel',
		border:false,
		frame: false,
    	region:'center',
    	height: 35,
    	bodyStyle: 'padding:5px',
		layout:'table',
		layoutConfig: {
        	columns: 12
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
				html:'证件编号',
				border:false,
				frame: false
			},{
				xtype:'textfield',
				anchor:'100%',
				id:'zjbh',
				width: 150
			},{
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
    		},{
				width: 50,
				html:'姓名',
				border:false,
				frame: false
			},{
				xtype:'textfield',
				anchor:'100%',
				id:'xm',
				width: 150
			},{
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
    		},{
				width: 100,
				html:'身份证号码',
				border:false,
				frame: false
			},{
				anchor:'100%',
				xtype:'textfield',
				anchor:'100%',
				id:'sfz',
				width: 150
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
						selectZjbh = encodeURI(Ext.getCmp('zjbh').getValue());
						selectXm = encodeURI(Ext.getCmp('xm').getValue());
						selectSfz = Ext.getCmp('sfz').getValue();
						tabs.getUpdater().refresh();
        				tabs.doLayout();
        				Ext.getCmp('card').getLayout().setActiveItem(1);
        				tabs.setActiveTab(2);
        				tabs.setActiveTab(0);
        			}
        		})]
    		}]
    }
				]
			}
		]
	});
	
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
        },
        items:[{
			id : "bdcxx",
			title : "不动产信息",
			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
				activate:function(){
					Ext.getCmp('bdcxx').body.update(htmlStrStart + '?zjbh='+ selectZjbh+"&xm="+selectXm
							+"&sfz=" + selectSfz+ "&goto=queryBdcxx"+ htmlStrEnd);
					this.getUpdater().refresh();
				}
			}
        },{
			id : "csxx",
			title : "出生信息",
			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
				activate:function(){
					Ext.getCmp('csxx').body.update(htmlStrStart + '?zjbh='+ selectZjbh+"&xm="+selectXm
							+"&sfz=" + selectSfz+ "&goto=queryCsxx"+htmlStrEnd);
					this.getUpdater().refresh();
				}
			}
        },{
			id : "hyxx",
			title : "婚姻信息",
			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
				activate:function(){
					Ext.getCmp('hyxx').body.update(htmlStrStart + '?zjbh='+ selectZjbh+"&xm="+selectXm
							+"&sfz=" + selectSfz+ "&goto=queryHyxx"+ htmlStrEnd);
					this.getUpdater().refresh();
				}
			}
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
        	items:[p1,tabs]
        }
    });
    
});