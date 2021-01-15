/**
 */
var changeFlag = false;
var zpCount = 0;

Gnt.ux.SelectZpAll = Ext.extend(Ext.Window, {
	title:'照片显示保存',
	closeAction:'hide',
	closable: false,
	resizable:false,
	modal:true,
	width:450,
	height:600,
	margins:'5',
	layout:'border',
	pageSize:30,
//	autoScroll:true,
	show:function(){
		if(zpCount == 0){
			showInfo("此人无照片信息！");
		}else{
			Gnt.ux.SelectZpAll.superclass.show.call(this);
		}
	},
	resetData:function(){
		
	},
	initComponent : function(){
		
		var win = this;
		zpCount = 0;
		
		var tb = new Ext.Toolbar({
			frame:false,
		 	border:false,
			region : 'north',
			items:[
				{
			        iconCls:'icon-close',
			    	tooltip :'关闭',
		        	handler : function(dp, date){
		        		
		        		for(var i = 0; i < zpCount; i++){
		        			win.zpPanel.items.get(i).items.get(5).items.get(0).setHeight(480);
		        			win.zpPanel.items.get(i).items.get(5).items.get(0).setWidth("100%");
		        		}
		    			
		    			changeFlag = false;
		    			
    	 				win.hide();
		        	}
			    },"-"
			    ,{
			    	iconCls:'icon-save',
			    	tooltip :'保存所有照片',
			    	handler : function(dp, date){
//			    		chart.save({
//				            type: 'image/png',
//				            filename:'testfile'
//				        });
	        			try {
		        			var gmsfhm = win.zpPanel.items.get(0).items.get(5).items.get(0).initialConfig.gmsfhm;
		        			fileName =gmsfhm+'.png';
        		        var elemIF = document.createElement("iframe");
        		        elemIF.src = "yw/common/downZp?fileName="+encodeURI(encodeURI(fileName))+"&ryid="+win.ryid;
        		        elemIF.style.display = "none";
        		        elemIF.setAttribute('async', false);
        		        document.body.appendChild(elemIF);
//        		        var timer = setInterval(function () {
//        		            var iframeDoc = elemIF.contentDocument || elemIF.contentWindow.document;
//        		            // Check if loading is complete
//        		            if (iframeDoc.readyState == 'complete' || iframeDoc.readyState == 'interactive') {
//        		            	downLoad(num+1,zpCount,win);
//        		                clearInterval(timer);
//        		                return;
//        		            }
//        		        }, 4000);
        		      } catch (e) {	
        		        alert("下载异常！");
        		      }finally{
        		      }
//			    		for(var i = 0; i < zpCount; i++){
//		        			var zpid = win.zpPanel.items.get(i).items.get(5).items.get(0).initialConfig.id;
//		        			var gmsfhm = win.zpPanel.items.get(i).items.get(5).items.get(0).initialConfig.gmsfhm;
//		        			if(zpCount>1){
//		        				xh = i+1;
//		        				fileName =gmsfhm+''+xh+'.png';
//		        			}else{
//		        				fileName =gmsfhm+'.png';
//		        			}
//		        			if(zpCount>1){
//		        				xh = i+1;
//		        				fileName =gmsfhm+''+xh+'.png';
//		        			}else{
//		        				fileName =gmsfhm+'.png';
//		        			}
//		        			try {
//		        		        var elemIF = document.createElement("iframe");
//		        		        elemIF.src = "yw/common/downZp?fileName="+encodeURI(encodeURI(fileName))+"&zpid="+zpid;
//		        		        elemIF.style.display = "none";
//		        		        elemIF.setAttribute('async', false);
//		        		        document.body.appendChild(elemIF);
//		        		        var timer = setInterval(function () {
//		        		            var iframeDoc = elemIF.contentDocument || elemIF.contentWindow.document;
//		        		            // Check if loading is complete
//		        		            if (iframeDoc.readyState == 'complete' || iframeDoc.readyState == 'interactive') {
//		        		            	downLoad(num+1,zpCount,win);
//		        		                clearInterval(timer);
//		        		                return;
//		        		            }
//		        		        }, 4000);
//		        		      } catch (e) {	
//		        		        alert("下载异常！");
//		        		      }finally{
//		        		      }
//		        		}
			    	}
			    },"-"
			    ,{
			    	iconCls:'icon-change',
			    	tooltip :'相片大小切换',
			    	handler : function(dp, date){
			    		
			    		if(changeFlag){
			    			
			        		for(var i = 0; i < zpCount; i++){
			        			win.zpPanel.items.get(i).items.get(5).items.get(0).setHeight(480);
			        			win.zpPanel.items.get(i).items.get(5).items.get(0).setWidth("100%");
			        		}
			    			
			    			changeFlag = false;
			    		}else{
			    			
			    			for(var i = 0; i < zpCount; i++){
			    				win.zpPanel.items.get(i).items.get(5).items.get(0).setHeight(150);
			    				win.zpPanel.items.get(i).items.get(5).items.get(0).setWidth("30%");
			    			}
			    			win.doLayout();
			    			
			    			changeFlag = true;
			    		}
			    	}
			    }
			]
		});
		
		this.zpPanel = new Ext.Panel({
			region : 'center',
			tbar: tb,
//			autoScroll:true,
			bodyStyle :'overflow-x:hidden;overflow-y:scroll',//只显示垂直滚动条
//			width:'100%',
			layout : 'column',
			//xtype : 'fieldset',
			title : '',
			margins:'5'
		});
		
		/**
		 * 查询出照片后动态加载代码
		 * @param id
		 * @returns
		 */
		var ryid = this.ryid;
		if(!ryid || ryid==""){
			
		}
		this.ryid = ryid;
		/*
		this.zpGrid = new Gnt.ux.SjpzGrid({
			url: "cx/hjjbxx/ckxx/queryZp",
			pzlb: '10021',
			pkname: 'zpid'
		});
		*/
		var myStore = new Ext.data.JsonStore({
	 		proxy: new Ext.data.HttpProxy({
	 			url: 'cx/hjjbxx/ckxx/queryZp',
				method: "POST",
				disableCaching: true
		    }),
	        root: 'list',
	        id:'id',
	        totalProperty:'totalCount',
	        fields: ["zpid","ryid","gmsfhm","xm","zp","lrrq"]
	    });
		
		var subdata = {
			ryid: this.ryid
		};
		
		Gnt.submit(
			subdata, 
			"cx/hjjbxx/ckxx/queryZp", 
			"正在处理，请稍后...", 
			function(jsonData){
				if(jsonData && jsonData.list && jsonData.list.length > 0){
					for(var i = 0; i < jsonData.list.length; i++){
						var rec = jsonData.list[i];
						
						win.zpPanel.add({
							frame:false,
							border:false,
							layout : 'column',
							columnWidth:1,
							defaults : {
								frame:false,
								border:false,
								columnWidth:.5,
								bodyStyle : 'padding:5px 5px 0px 5px'
							},
							items:[
								{
									xtype:'hidden',
									name:'zpId',
									value:rec.zpid
				    	       	}
								,{
									layout: 'form',
									columnWidth:.35,
									labelWidth : 40,
									items:[{
										fieldLabel:'姓名',
										value:rec.xm,
										anchor:'100%',
										disabled:true,
										xtype : 'textfield'
									}]
								}
								,{
									layout: 'form',
									columnWidth:.6,
									labelWidth : 80,
									items:[{
										fieldLabel:'公民身份号码',
										value:rec.gmsfhm,
										anchor:'100%',
										disabled:true,
										xtype : 'textfield'
									}]
								}
								,{
									layout: 'form',
									labelWidth : 60,
									columnWidth:.75,
									items:[{
										fieldLabel:'扫描时间',
										xtype : 'datefield',
										value: Gnt.date.string2Date(rec.lrrq,false),
										format:'Y年m月d日 H时i分s秒',
										anchor:'100%',
										disabled:true
									}]
								}
								,{
									layout: 'form',
									columnWidth:.25,
									items:[{
										xtype : 'button',
										text : '删除此照片',
										minWidth:90,
										width:100,
										handler : function() {
											
											var item = this;
											
											if(window.confirm("您确认删除此照片信息吗？")){
												/**
													怎么获取点击照片
												 */
												var subdata = {
													zpid: this.ownerCt.ownerCt.items.get(0).getValue()
												};
												
												Gnt.submit(
													subdata, 
													"cx/hjjbxx/ckxx/processZpscyw", 
													"正在删除照片，请稍后...", 
													function(jsonData){
														
														for(var i = 0;i < zpCount;i++){
											       			win.zpPanel.items.get(i).items.get(5).items.get(0).setHeight(480);
											       			win.zpPanel.items.get(i).items.get(5).items.get(0).setWidth("100%");
											       		}
											    		
											    		changeFlag = false;
											    		
											    		win.zpPanel.remove(item.ownerCt.ownerCt);
									    	 			
											    		zpCount --;
											    		
											    		win.hide();
									    	 			
													}
												);
												
											}
											
										}
									}]
								}
								,{
									layout: 'form',
									columnWidth:.96,
									items:[{
										title: '',
										height:480,
										gmsfhm:rec.gmsfhm,
//										bodyStyle:'padding:10px 10px 10px 10px',
										html: '<div ><img src="data:image/jpg;base64,' + rec.zp + '" width="100%" /></DIV>'
									}]
								}
							]
						});
						
						zpCount ++;
						
					}
				}
				
				var autoShow = win.autoShow;
				if(autoShow){
					win.show();
				}
				
				
				
			}
		);
		
		/*
		for(var i = 0; i < 5; i++){
			this.zpPanel.add(item);
			zpCount ++;
		}
		*/
		
		this.items = [this.zpPanel];
		
		Gnt.ux.SelectZpAll.superclass.initComponent.call(this);
		
	}
});
Ext.reg('zp_window', Gnt.ux.SelectZpAll);

function deletePhoto(zpid){
	
};
