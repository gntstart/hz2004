
var queryFlag = false;
var htmlStrStart = '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="gl/sjcc/querycwXx?goto=';
var htmlStrEnd = '"></iframe>';
//'?rynbid='+ getRynbid(rynbid) + "&goto=queryHdxx&hhnbid=" + getQueryParam("hhnbid") + '"> </iframe>';


var array=new Array();
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("20038",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	
	
	var p1 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items: [{
		    title: '',
		    region: 'south',
		    collapsible: false,
		    height: 80,
		    border:false,
		    frame:false,
		    cmargins: '5 0 0 0',
		    bodyStyle: 'padding:5px 5px',
		    layout:'column',
		    buttonAlign:'right',
	    	labelAlign:'right',
	    	items:[{
	    		layout:'table',
	    		border:false,
	    		frame:false,
	    		items:[{
	    			layout:'form',
	    			border:false,
	    			//bodyStyle: 'padding:12 0 0 0 ',
	    			frame:false,
	    			labelAlign :'right',
	    			items:[{
						anchor:'98%',
						xtype:'search_combox',
						searchUrl:'dict/utils/searchPcs.json',
						fields:["mc","dm","qhdm","pcsList"],
						valueField: "dm",
						displayField: "mc",
						hiddenName:'jlx',
						id:'pcs',
						name:'pcsmc',
						maxLength:30,
						
						allowBlank:true,
						fieldLabel:'选择查错派出所'
	    			    }
	    			    ]
					
		        	},{
		    			width:620,
						border:false,
						frame: false
		    		},{
						border:false,
						frame: false,
						//bodyStyle: 'padding:10',
		    		    items:[new Ext.Button({
		        			text:'查询',
		        			minWidth:60,
		        			handler:function(){
		        		
		        				if(array.length==0){
		        					
		        					Ext.getCmp('card').getLayout().setActiveItem(2);
		        				}else{
		        					Ext.getCmp('card').getLayout().setActiveItem(1);
		        				}
		        				for(var c=0;c<12;c++){//目前固定错误列表12个
		        					tabs.hideTabStripItem(c);
		        				}
		        				var num=0;
		        				for(var i=0;i<array.length;i++){
		        					
		        					if(array[i]){
		        						if(array[i]=="cw1"){
		        							tabs.unhideTabStripItem(0);
		        							if(num==0){
		        								tabs.setActiveTab(0);
		        								num++;
		        							}		        									        							
		
		        						}
		        						if(array[i]=="cw2"){
		        							tabs.unhideTabStripItem(1);
		        							if(num==0){
		        								tabs.setActiveTab(1);
		        								num++;
		        							}
		        						}
		        						if(array[i]=="cw3"){
		        							tabs.unhideTabStripItem(2);
		        							if(num==0){
		        								tabs.setActiveTab(2);
		        								num++;
		        							}
		        						}
		        						if(array[i]=="cw4"){
		        							tabs.unhideTabStripItem(3);
		        							if(num==0){
		        								tabs.setActiveTab(3);
		        								num++;
		        							}
		        						}
		        						if(array[i]=="cw5"){
		        							tabs.unhideTabStripItem(4);
		        							if(num==0){
		        								tabs.setActiveTab(4);
		        								num++;
		        							}
		        						}
		        						if(array[i]=="cw6"){
		        							tabs.unhideTabStripItem(5);
		        							if(num==0){
		        								tabs.setActiveTab(5);
		        								num++;
		        							}
		        						}
		        						if(array[i]=="cw7"){
		        							tabs.unhideTabStripItem(6);
		        							if(num==0){
		        								tabs.setActiveTab(6);
		        								num++;
		        							}
		        						}
		        						if(array[i]=="cw8"){
		        							tabs.unhideTabStripItem(7);
		        							if(num==0){
		        								tabs.setActiveTab(7);
		        								num++;
		        							}
		        						}
		        						if(array[i]=="cw9"){
		        							tabs.unhideTabStripItem(8);
		        							if(num==0){
		        								tabs.setActiveTab(8);
		        								num++;
		        							}
		        						}
		        						if(array[i]=="cw10"){
		        							tabs.unhideTabStripItem(9);
		        							if(num==0){
		        								tabs.setActiveTab(9);
		        								num++;
		        							}
		        						}
		        						if(array[i]=="cw11"){
		        							tabs.unhideTabStripItem(10);
		        							if(num==0){
		        								tabs.setActiveTab(10);
		        								num++;
		        							}
		        						}
		        						if(array[i]=="cw12"){
		        							tabs.unhideTabStripItem(11);
		        							if(num==0){
		        								tabs.setActiveTab(11);
		        								num++;
		        							}
		        						}
		        					}
		        				}
		        					
		        				}
		        				
		        			
		        		})]
		    		}]
		        	
	    	}],
		    buttons:[new Ext.Button({
    			text:'关闭',
    			minWidth:60,
    			handler:function(){
    				window.parent.closeWorkSpace();
    			}
    		})
		             ]
		},{
		    title: '错误查询',
		    border:true,
		    frame:false,
		    region:'west',
		    collapsible: false,
		    margins: '5 0 0 0',
		    cmargins: '5 5 0 0',
		    width: 300,
		    layout:'table',
		    layoutConfig: {columns: 1},
		     items:[{
		    	 layout:'form',
		            	id:'c1',
		            	xtype:'checkbox',
		            	labelWidth:90,
		            	border:false,
		            	frame:false,
		            	labelSeparator: '',
		            	boxLabel: '一户多个户主',
		            	checked:false,
	    				listeners:{
	    					'check': function(obj,checked){
	    						if(checked){
	    							array[0]="cw1";
	    						}else{
	    							array[0]="";
	    						}
	    					}
	    				}
		            
		     },
		     {
		    	 layout:'form',
		            	id:'c2',
		            	xtype:'checkbox',
		            	labelWidth:90,
		            	border:false,
		            	frame:false,
		            	labelSeparator: '',
		            	boxLabel: '缺少户主',
		            	checked:false,
	    				listeners:{
	    					'check': function(obj,checked){
	    						if(checked){
	    							array[1]="cw2";
	    						}else{
	    							array[1]="";
	    						}
	    					}
	    				}
		            
					     }, {
					    	 layout:'form',
				            	id:'c3',
				            	xtype:'checkbox',
				            	labelWidth:90,
				            	border:false,
				            	frame:false,
				            	labelSeparator: '',
				            	boxLabel: '人户校验(查有人无户信息)',
				            	checked:false,
			 				listeners:{
			 					'check': function(obj,checked){
			 						if(checked){
			 							array[2]="cw3";
			 						}else{
			 							array[2]="";
			 					}
			 				}
			 				}
				            
						     },{
						    	 layout:'form',
					         	id:'c4',
					         	xtype:'checkbox',
					         	labelWidth:90,
					         	border:false,
					         	frame:false,
					         	labelSeparator: '',
					         	boxLabel: '户地校验(查有户无地信息)',
					         	checked:false,
								listeners:{
									'check': function(obj,checked){
										if(checked){
											array[3]="cw4";
										}else{
											array[3]="";
										}
									}
								}
					         
					  },{
					    	 layout:'form',
					         	id:'c5',
					         	xtype:'checkbox',
					         	labelWidth:90,
					         	border:false,
					         	frame:false,
					         	labelSeparator: '',
					         	boxLabel: '缺少照片',
					         	checked:false,
								listeners:{
									'check': function(obj,checked){
										if(checked){
											array[4]="cw5";
										}else{
											array[4]="";
										}
									}
								}
					         
					  },{
					    	 layout:'form',
					         	id:'c6',
					         	xtype:'checkbox',
					         	labelWidth:90,
					         	border:false,
					         	frame:false,
					         	labelSeparator: '',
					         	boxLabel: '查询存在多条最新记录的人员信息',
					         	checked:false,
								listeners:{
									'check': function(obj,checked){
										if(checked){
											array[5]="cw6";
										}else{
											array[5]="";
										}
									}
								}
					         
		  },{
		    	 layout:'form',
		         	id:'c7',
		         	xtype:'checkbox',
		         	labelWidth:90,
		         	border:false,
		         	frame:false,
		         	labelSeparator: '',
		         	boxLabel: '查询存在多条最新记录的户信息',
		         	checked:false,
					listeners:{
						'check': function(obj,checked){
							if(checked){
								array[6]="cw7";
							}else{
								array[6]="";
							}
						}
					}
		         
		  },{
		    	 layout:'form',
		         	id:'c8',
		         	xtype:'checkbox',
		         	labelWidth:90,
		         	border:false,
		         	frame:false,
		         	labelSeparator: '',
		         	boxLabel: '查询存在多条最新记录的地信息',
		         	checked:false,
					listeners:{
						'check': function(obj,checked){
							if(checked){
								array[7]="cw8";
							}else{
								array[7]="";
							}
						}
					}
		         
		  },{
		    	 layout:'form',
		         	id:'c9',
		         	xtype:'checkbox',
		         	labelWidth:90,
		         	border:false,
		         	frame:false,
		         	labelSeparator: '',
		         	boxLabel: '查询不存在多条最新记录的人员信息',
		         	checked:false,
					listeners:{
						'check': function(obj,checked){
							if(checked){
								array[8]="cw9";
							}else{
								array[8]="";
							}
						}
					}
		         
		  },{
		    	 layout:'form',
		         	id:'c10',
		         	xtype:'checkbox',
		         	labelWidth:90,
		         	border:false,
		         	frame:false,
		         	labelSeparator: '',
		         	boxLabel: '查询不存在多条最新记录的户信息',
		         	checked:false,
					listeners:{
						'check': function(obj,checked){
							if(checked){
								array[9]="cw10";
							}else{
								array[9]="";
							}
						}
					}
		         
		  },{
		    	 layout:'form',
		         	id:'c11',
		         	xtype:'checkbox',
		         	labelWidth:90,
		         	border:false,
		         	frame:false,
		         	labelSeparator: '',
		         	boxLabel: '查询不存在多条最新记录的地信息',
		         	checked:false,
					listeners:{
						'check': function(obj,checked){
							if(checked){
								array[10]="cw11";
							}else{
								array[10]="";
							}
						}
					}
		         
		  },{
		    	 layout:'form',
		         	id:'c12',
		         	xtype:'checkbox',
		         	labelWidth:90,
		         	border:false,
		         	frame:false,
		         	labelSeparator: '',
		         	boxLabel: '重号查询',
		         	checked:false,
					listeners:{
						'check': function(obj,checked){
							if(checked){
								array[11]="cw12";
							}else{
								array[11]="";
							}
						}
					}
		         
		  }]
		},{
		     title: '',
		     collapsible: false,
		     region:'center',
		     margins: '5 0 0 0',
		     bodyStyle :'overflow-x:hidden;overflow-y:scroll', 
		    // layout:'table',
		     //layoutConfig: {columns: 1},
		     items:[{
		            border:true,
		            frame:false,
		            layout:'form',
		            height:500,
		            labelAlign :'right',
		            xtype: 'fieldset',
		            title:'说明',
		            autoHeight: true, 
		            items:[
                       {border:false,frame:false,html:'一户多个户主: 从人员信息表中查询相同的户号，<br/>存在多个户主的情况。<br/><br/>'},               
                       {border:false,frame:false,html:'缺少户主: 从人员信息表中查询相同的户号，不存<br/>在户主的情况。<br/><br/>'},
                       {border:false,frame:false,html:'人户校验(查有人无户信息): 从人员信息表中查<br/>询户号在户信息表中不存在相应的记录的情况。<br/><br/>'},
                       {border:false,frame:false,html:'户地校验(查有户无地信息): 从户信息表中查询<br/>地址编码在门楼牌信息表中不存在相应的记录的情况。<br/><br/>'},
                       {border:false,frame:false,html:'缺少照片: 从人员信息表中查询16岁以上的无照片人员。<br/><br/>'},
                       {border:false,frame:false,html:'查询存在多条最新记录的人员信息: 从人员信息表<br/>中查询存在多条最新记录的情况。<br/><br/>'},
                       {border:false,frame:false,html:'查询存在多条最新记录的户信息: 从户信息表中查<br/>询存在多条最新记录的情况。<br/><br/>'},
                       {border:false,frame:false,html:'查询存在多条最新记录的地信息: 从地信息表中查<br/>询存在多条最新记录的情况。<br/><br/>'},
                       {border:false,frame:false,html:'查询不存在最新记录的人员信息: 从人员信息表中<br/>查询不存在最新记录的情况。<br/><br/>'},
                       {border:false,frame:false,html:'查询不存在最新记录的户信息: 从户信息表中查询<br/>不存在最新记录的情况。<br/><br/>'},
                       {border:false,frame:false,html:'查询不存在最新记录的地信息: 从地信息表中查询<br/>不存在最新记录的情况。<br/><br/>'},
                       {border:false,frame:false,html:'重号查询: 查询身份证重号的信息。<br/><br/>'}
		            ]
		            
		          
		 }]
		 }]
	});
	
	
	
	
	

    	var p2 = new Ext.Panel({
    		layout:'border',
    		newYewu: function(){
    			
    		},
    		items:[
    			{
				    title: '',
				    collapsible: false,
				    border:false,
				    region:'center',
				    margins: '5 0 0 0'
              },{
            	    title: '',
            	    border:false,
            	    frame:false,
            	    region: 'south',
            	    height: 40,
                    buttons:[
            	    			{
            	    				text:'关闭',
            	    				minWidth:60,
            	    				handler:function(){
            	    					window.parent.closeWorkSpace();
            	    				}
            	    			},{
            	    				text:'返回',
            	    				minWidth:60,
            	    				handler:function(){
            	    					Ext.getCmp('card').getLayout().setActiveItem(0);
            	    				}
            	    			}
            	    		]
            	}
    		]
    	
    	});
    	
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
//            activeTab: 0,
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
            	
            },
            /*buttons:[ new Ext.Button({		     
        		text:'打印',
        		minWidth:60,
        		handler:function(){
        			//var cell = document.getElementById("Celllist");			       
        			//cell.PrintPageSetup();
        		}
        	})],*/
            items:[{
    			id : "yhdghz",
    			title : "一户多个户主",
//    			html:' <iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'+'cx/hjjbxx/ckxx/queryXxxx?rynbid='+ getRynbid(rynbid) + "&goto=queryRkxx&hhnbid=" + getHhnbid(hhnbid) + '"> </iframe>',
    			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
    				activate:function(){
    					
    					Ext.getCmp('yhdghz').body.update(htmlStrStart + "queryYhdghz" +'&pcs='+Ext.getCmp('pcs').getValue()+ htmlStrEnd);
    					this.getUpdater().refresh();
    					
    				}
    			}
            },{
    			id : "qshz",
//    			url : "cx/hjjbxx/ckxx",
    			//tabTip : "常口信息查询",
    			title : "缺少户主",
//    			html:' <iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'+'cx/hjjbxx/ckxx/queryXxxx?rynbid='+ getRynbid(rynbid) + "&goto=queryBggz&hhnbid=" + getHhnbid(hhnbid) + '"> </iframe>',
    			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
    				activate:function(){
    					Ext.getCmp('qshz').body.update(htmlStrStart + "queryQshz" +'&pcs='+Ext.getCmp('pcs').getValue() + htmlStrEnd);
    					this.getUpdater().refresh();
    				}
    			}
            },{
    			id : "yrwh",
//    			url : "cx/hjjbxx/ckxx",
    			//tabTip : "常口信息查询",
    			title : "有人无户信息",
//    			html:' <iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'+'cx/hjjbxx/ckxx/queryXxxx?rynbid='+ getRynbid(rynbid) + "&goto=queryHdxx&hhnbid=" + getHhnbid(hhnbid) + '"> </iframe>',
    			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
    				activate:function(){
    					Ext.getCmp('yrwh').body.update(htmlStrStart + "queryYrwh" +'&pcs='+Ext.getCmp('pcs').getValue() + htmlStrEnd);
    					this.getUpdater().refresh();
    				}
    			}
                
            },{
    			id : "yhwd",
//    			url : "cx/hjjbxx/ckxx",
    			//tabTip : "常口信息查询",
    			title : "有户无地信息",
    			
//    			html:' <iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'+'cx/hjjbxx/ckxx/queryXxxx?rynbid='+ getRynbid(rynbid) + "&goto=queryHcybd&hhnbid=" + getHhnbid(hhnbid) + '"> </iframe>',
    			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
    				activate:function(){
    					Ext.getCmp('yhwd').body.update(htmlStrStart + "queryYhwd" +'&pcs='+Ext.getCmp('pcs').getValue() + htmlStrEnd);
    					this.getUpdater().refresh();
    				}
    			}
                
            },{
    			id : "qszp",
//    			url : "cx/hjjbxx/ckxx",
    			//tabTip : "常口信息查询",
    			title : "缺少照片",
//    			html:' <iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'+'cx/hjjbxx/ckxx/queryXxxx?rynbid='+ getRynbid(rynbid) + "&goto=queryZjxx&hhnbid=" + getHhnbid(hhnbid) + '"> </iframe>',
    			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
    				activate:function(){
    					Ext.getCmp('qszp').body.update(htmlStrStart + "queryQszp" +'&pcs='+Ext.getCmp('pcs').getValue() + htmlStrEnd);
    					this.getUpdater().refresh();
    				}
    			}
                
            },{
    			id : "czryxx",
//    			url : "cx/hjjbxx/ckxx",
    			//tabTip : "常口信息查询",
    			title : "存在多条最新记录的人员信息",
//    			html:' <iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'+'cx/hjjbxx/ckxx/queryXxxx?rynbid='+ getRynbid(rynbid) + "&goto=querySlxx&hhnbid=" + getHhnbid(hhnbid) + '"> </iframe>',
    			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
    				activate:function(){
    					Ext.getCmp('czryxx').body.update(htmlStrStart + "queryCzryxx" +'&pcs='+Ext.getCmp('pcs').getValue() + htmlStrEnd);
    					this.getUpdater().refresh();
    				}
    			}
                
            },{
    			id : "czhxx",
//    			url : "cx/hjjbxx/ckxx",
    			//tabTip : "常口信息查询",
    			title : "存在多条最新记录的户信息",
//    			html:' <iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'+'cx/hjjbxx/ckxx/queryXxxx?rynbid='+ getRynbid(rynbid) + "&goto=queryHgl&hhnbid=" + getHhnbid(hhnbid) + '"> </iframe>',
    			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
    				activate:function(){
    					
    					Ext.getCmp('czhxx').body.update(htmlStrStart + "queryCzhxx" +'&pcs='+Ext.getCmp('pcs').getValue() + htmlStrEnd);
    					this.getUpdater().refresh();
    				}
    			}
                
            },{
    			id : "czdxx",
//    			url : "cx/hjjbxx/ckxx",
    			//tabTip : "常口信息查询",
    			title : "存在多条最新记录的地信息",
//    			html:' <iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'+'cx/hjjbxx/ckxx/queryXxxx?rynbid='+ getRynbid(rynbid) + "&goto=queryHcy&hhnbid=" + getHhnbid(hhnbid) + '"> </iframe>',
    			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
    				activate:function(){
    					Ext.getCmp('czdxx').body.update(htmlStrStart + "queryCzdxx" +'&pcs='+Ext.getCmp('pcs').getValue() + htmlStrEnd);
    					this.getUpdater().refresh();
    				}
    			}
                
            },{
    			id : "bczryxx",
//    			url : "cx/hjjbxx/ckxx",
    			//tabTip : "常口信息查询",
    			title : "不存在最新记录的人员信息",
//    			html:' <iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'+'cx/hjjbxx/ckxx/queryXxxx?rynbid='+ getRynbid(rynbid) + "&goto=queryHcy&hhnbid=" + getHhnbid(hhnbid) + '"> </iframe>',
    			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
    				activate:function(){
    					Ext.getCmp('bczryxx').body.update(htmlStrStart + "queryBczryxx" +'&pcs='+Ext.getCmp('pcs').getValue() + htmlStrEnd);
    					this.getUpdater().refresh();
    				}
    			}
                
            },{
    			id : "bczhxx",
//    			url : "cx/hjjbxx/ckxx",
    			//tabTip : "常口信息查询",
    			title : "不存在最新记录的户信息",
//    			html:' <iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'+'cx/hjjbxx/ckxx/queryXxxx?rynbid='+ getRynbid(rynbid) + "&goto=queryHcy&hhnbid=" + getHhnbid(hhnbid) + '"> </iframe>',
    			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
    				activate:function(){
    					Ext.getCmp('bczhxx').body.update(htmlStrStart + "queryBczhxx" +'&pcs='+Ext.getCmp('pcs').getValue() + htmlStrEnd);
    					this.getUpdater().refresh();
    				}
    			}
                
            },{
    			id : "bczdxx",
//    			url : "cx/hjjbxx/ckxx",
    			//tabTip : "常口信息查询",
    			title : "不存在最新记录的地信息",
//    			html:' <iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'+'cx/hjjbxx/ckxx/queryXxxx?rynbid='+ getRynbid(rynbid) + "&goto=queryHcy&hhnbid=" + getHhnbid(hhnbid) + '"> </iframe>',
    			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
    				activate:function(){
    					Ext.getCmp('bczdxx').body.update(htmlStrStart + "queryBczdxx" +'&pcs='+Ext.getCmp('pcs').getValue() + htmlStrEnd);
    					this.getUpdater().refresh();
    				}
    			}
                
            },{
    			id : "chcx",
//    			url : "cx/hjjbxx/ckxx",
    			//tabTip : "常口信息查询",
    			title : "身份证重号",
//    			html:' <iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'+'cx/hjjbxx/ckxx/queryXxxx?rynbid='+ getRynbid(rynbid) + "&goto=queryHcy&hhnbid=" + getHhnbid(hhnbid) + '"> </iframe>',
    			listeners:{                   // 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
    				activate:function(){
    					Ext.getCmp('chcx').body.update(htmlStrStart + "queryChcx" +'&pcs='+Ext.getCmp('pcs').getValue() + htmlStrEnd);
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
        	items:[p1,tabs,p2]
        }
    });
	

    
	
	
	
});