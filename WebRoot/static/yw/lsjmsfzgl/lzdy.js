
var isdy=0;
var htmlStrStart = '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="cx/hjjbxx/ckxx/queryXxxx?goto=';
var htmlStrEnd = '"></iframe>';
//'?rynbid='+ getRynbid(rynbid) + "&goto=queryHdxx&hhnbid=" + getQueryParam("hhnbid") + '"> </iframe>';
var dyfs=null;
var dyfw=null;
var selectRynbid = null;
var selectRyid = null;
var isImage=1;
var zpid=null;
var lssfzkh=null;
var sfzhm=null;
var dytype=null;
var dyzt=null;
Ext.onReady(function(){
	Ext.QuickTips.init();

	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("30032",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	
	/**
		点击下一步后显示的内容
		暂时用户成员信息代替
	 */
/*	var hcyGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		pzlb: '10019',
    	region:'south',
		height:150,
    	title: '',
		url: 'yw/common/queryRyxx.json',
    	showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){
				
			}
		}
	});*/
	

	
	var colModel=new Ext.grid.ColumnModel([	                                       
           {
             header: "姓名",
             //hidden:true,
             dataIndex: "xm",
             sortable: true,
             width: 120
           },
           {
               header: "公民身份号码",
               //hidden:true,
               dataIndex: "gmsfhm",
               sortable: true,
               width: 120
             },
             {
                 header: "临时身份证卡号",
                 //hidden:true,
                 dataIndex: "lsjmsfzkh",
                 sortable: true,
                 width: 120
               },{
                   header: "临时证受理编号",
                   //hidden:true,
                   dataIndex: "lsslid",
                   sortable: true,
                   width: 120
                 }
   	   ]);
   	   
	var data = [
	            
	       ];
	       // ArrayReader
	       var ds = new Ext.data.Store({
	            proxy: new Ext.data.MemoryProxy(data),
	            reader: new Ext.data.ArrayReader({}, [
	               {name: 'xm',mapping: 0},
	                {name: 'gmsfhm',mapping: 1},
	                {name: 'lsjmsfzkh',mapping: 2},
	                {name: 'lsslid',mapping: 3}
	            ])
	       });
	       ds.load();
   	   
   	
   	var dylistGrid = new Ext.grid.GridPanel({
           store: ds,
           region: 'center',
           height:300,
           //hideHeaders : Boolean,
           view:new Ext.grid.GridView({
   				enableRowBody:true
   		}),
   		stripeRows: true,
   		//ds:ds,
        cm: colModel,
   		loadMask: {msg:'正在加载数据，请稍侯……'},
           border:true,
           frame:false,
           title:'',
           listeners:{
           	rowclick:function(g, rowIndex, e){
           		selectedSelRes = g.store.getAt(rowIndex);
           		
           				
           	},
   			rowdblclick:function(g, rowIndex, e){
   				selectedSelRes = g.store.getAt(rowIndex);
   			
   			}
   		}
       });
	
	
	var form30032 = new Gnt.ux.SjpzForm({
		pzlb: '30032',
		url: 'yw/lsjmsfzgl/querylzdy',
		title:'',
		cols:2,
		formType:'query'
	});
	
	var newForm30032 = new Gnt.ux.SjpzForm({
		closable: false,
		region:'center',
		/*height:500,*/
		pzlb: '30032',
		labelWidth : 120,
		cols:2,
		changeDictCust:function(cmb,res){
			return;
		},
		title: ''
	});
	
	
	var p1 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[
			{
				id:"nextId",
				border:false,
				frame: false,
				region:'center',
				visible:false
	//        	height: 300,
				//items:[hcyGrid]
			},
			{
				id:"centerId",
				border:false,
				frame: false,
				region:'center',
				layout:'border',
//	        	height: 300,
				items:[{
						id: "panelHtmlId" ,
						html: '<div class="text" style="text-align:center;"><br /><h2>临时身份证打印</h2></div>',
						height: 80,
						region:'north',
						bodyStyle:'padding:15px'
					},
					form30032
				]
			},
			{
				id:"southId",
	        	region:'south',
	        	height:40,
	            border:false,
	            frame:false,
	            items:[{
					border:false,
					frame: false,
		        	region:'south',
		        	height: 40,
		        	bodyStyle: 'padding:5px',
					layout:'table',
					layoutConfig: {
			        	columns: 16
			        },
			    	items: [
						{
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
			        			text:'组合条件',
			        			minWidth:80,
			        			handler:function(){
			        				zhcx_dialog.show();
			        			}
			        		})]
			    		},{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			    		},{
							border:false,
							frame: false,
			    		    items:[{
				    	    	xtype: 'DkButton',
				    	    	form:form30032,
				    	    	callback: function(){
				    	    		//Ext.getCmp("queryId").handler();
				    	    	}
				    	    }]
			    		},/*{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
//							colspan:1
							width:10
			    		},{
							border:false,
							frame: false,
			    		    items:[{
			    		    	id:'c31',
				        		xtype:'checkbox',
				        		boxLabel : "显示记录总数",  
				        		name : "xxzs",  
				        		inputValue : "2"  
				        	}]
			    		},*/{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
//							colspan:1
							width:10
			    		},{
							border:false,
							frame: false,
			    		    items:[{
				        		xtype:'checkbox',
				        		boxLabel : "查询已打印的受理信息",
				        		listeners:{
				    				'check': function(obj,checked){
				    					if(checked){
				    						isdy=1;
				    					}else{
				    						isdy=0;
				    					}
				    				}
				    			}
				        	}]
			    		},{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
//							colspan:1
							width:200
			    		},{
							border:false,
							frame: false,
			    		    items:[new Ext.Button({
			    		    	id:'preId',
			        			text:'上一步',
			        			disabled:true,
			        			minWidth:60,
			        			handler:function(){
			        				Ext.getCmp('preId').setDisabled(true);
			        				Ext.getCmp('fzId').setDisabled(false);
			        				Ext.getCmp('nexId').setDisabled(false);
			        				Ext.getCmp('nextId').setVisible(false);
			        				Ext.getCmp('centerId').setVisible(true);
			        			}
			        		})]
			    		},{
			    			width: 10,
							border:false,
							frame: false
			    		},{
							border:false,
							frame: false,
			    		    items:[new Ext.Button({
			    		    	id:'nexId',
			        			text:'下一步',
			        			minWidth:60,
			        			handler:function(){
			        				/*Ext.getCmp('preId').setDisabled(false);
			        				Ext.getCmp('fzId').setDisabled(true);
			        				Ext.getCmp('nexId').setDisabled(true);
			        				Ext.getCmp('nextId').setVisible(true);
			        				Ext.getCmp('centerId').setVisible(false);*/
			        			}
			        		})]
			    		},{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
//							colspan:3
//							columns:10
			    		},{
							border:false,
							frame: false,
			    		    items:[new Ext.Button({
			    		    	id:'queryId',
			        			text:'查询',
			        			minWidth:60,
			        			handler:function(){
			        				var data = form30032.getForm().getValues();
			        				
			        					if(Gnt.util.isEmpty(data)){
			        						showInfo("至少输入一个查询条件！");
			        						return;
			        					}
			        				
									Ext.Msg.wait("正在执行查询，请稍后...");
			        				
			        				var store = lzdyGrid.store;
			        				
			        				//store.removeAll();
			        				
			        				store.baseParams = data;
			        				
			        				applyParam(store);
			        				
			        				
			        				store.load({params:{start:0, limit:20}});
			        				store.on("load",function(store){
			         					if(store.data.length>0){
			         						curIndex = 0;
			         						lzdyGrid.fireEvent("rowclick",lzdyGrid,curIndex);
			         						lzdyGrid.getSelectionModel().selectRange(curIndex,curIndex);
			         						if(dylistGrid.getStore().getCount()>0){
			         							Ext.getCmp("scdy").setDisabled(false);
				         						 Ext.getCmp("cpdy").setDisabled(false);
			         						}else{
			         							Ext.getCmp("scdy").setDisabled(true);
				         						 Ext.getCmp("cpdy").setDisabled(true);
			         						}
			         						
			         						 zpid=store.data.items[0].data.zpid;
			         						 sfzhm=store.data.items[0].data.gmsfhm;
			         	        			 lssfzkh=store.data.items[0].data.lsjmsfzkh;
			         	        			 dyzt=store.data.items[0].data.dybz;
			         						if(zpid &&　zpid != 0){
			    	   	 						
			    		   	 					  Ext.getCmp('picImage').body.update("<IMG SRC='yw/common/img/render/" + zpid + "' width='100%' onerror='javascript:isImage=0;' height='100%' />");
			    			   	 				}else{
			    			   	 				
			    			   	 					Ext.getCmp('picImage').body.update("<IMG SRC='images/no_pic.gif' width='100%' onerror='javascript:isImage=0;' height='100%' />");
			    			   	 				}
			         					}else{
			         						
			         					}
			         				});
			        				
			        				
			        			}
			        		})]
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
			        			text:'关闭',
			        			minWidth:60,
			        			handler:function(){
			        				window.parent.closeWorkSpace();
			        			}
			        		})]
			    		},{
			        		frame:false,
							border:false,
							xtype:'panel',
							html:'',
							height:40
			        	},{
			    	    	frame:false,
							border:false,
							id:'_READ_CARD_ID',
							xtype:'panel',
							html:'',
							width:10
						}
			    		 
			    	]
					
				}]
	        }
		]
	});
	
	function applyParam(store){
		
		
		
		
		/**
			是否显示记录总数
		 */
		var zs = '';
//		if(Ext.getCmp('c31').getValue()){
//			zs = '1';
//		}
		
		
		Ext.apply(store.baseParams, {isdy:isdy});
		
		
		
	}
	

 			
 	var lzdyGrid = new Gnt.ux.SjpzGrid({
 		pkname: 'rynbid',
		region:'center',
        height:500,
		pzlb: '30032',
		title: '',
		url: 'yw/lsjmsfzgl/querylzdy',
		loadCallback: function(res, store, bind_grid){
			Ext.Msg.hide();
			if(res.length>0){
				Ext.getCmp('card').getLayout().setActiveItem(1);
				Ext.Msg.hide();
			}else{
				showInfo("未查到有关信息！");
				return;
			}
		},
		showPaging:true,
         listeners:{
        		rowclick:function(g, rowIndex, e){
        			selRes = g.store.getAt(rowIndex);
        			curIndex = rowIndex;
        			
        			selectRynbid = selRes.data.rynbid;
        			selectRyid=selRes.data.ryid;
        			 zpid=selRes.data.zpid;
        			 sfzhm=selRes.data.gmsfhm;
        			 dyzt=selRes.data.dybz;
        			 //lssfzkh=selRes.data.lsjmsfzkh;
        			 isImage=1;
        			if(zpid &&　zpid != 0){		
    	 					  Ext.getCmp('picImage').body.update("<IMG SRC='yw/common/img/render/" + zpid + "' width='100%' onerror='javascript:isImage=0;' height='100%' />");
     	 				}else{
     	 					
     	 					Ext.getCmp('picImage').body.update("<IMG SRC='images/no_pic.gif' width='100%' onerror='javascript:isImage=0;' height='100%' />");
     	 				}
        			
        			
        		},rowdblclick:function(g, rowIndex, e){
        			selRes = g.store.getAt(rowIndex);
        			curIndex = rowIndex;
        			
        			selectRynbid = selRes.data.rynbid;
        			selectRyid=selRes.data.ryid;
        			 zpid=selRes.data.zpid;
        			 sfzhm=selRes.data.gmsfhm;
        			 lssfzkh=selRes.data.lsjmsfzkh;
        			 isImage=1;
        			if(zpid &&　zpid != 0){		
        				Ext.getCmp('picImage').body.update("<IMG SRC='yw/common/img/render/" + zpid + "' width='100%' onerror='javascript:isImage=0;' height='100%' />");
        				Ext.getCmp('p3Img').body.update("<IMG SRC='yw/common/img/render/" + zpid + "' width='100%' onerror='javascript:isImage=0;' height='100%' />");
 	 				}else{
 	 					Ext.getCmp('picImage').body.update("<IMG SRC='images/no_pic.gif' width='100%' onerror='javascript:isImage=0;' height='100%' />");
 	 					Ext.getCmp('p3Img').body.update("<IMG SRC='images/no_pic.gif' width='100%' onerror='javascript:isImage=0;' height='100%' />");
 	 				}
        			Ext.getCmp('card').getLayout().setActiveItem(2);
    				//v.doLayout();
    				Gnt.toFormReadyonly(newForm30032);
    				p3.doLayout();
    				newForm30032.getForm().loadRecord(selRes);
        			
       			}
 		}
     });
	
 	var lssfzdy_win=new Gnt.ux.lssfzdywin({//新增弹窗
		callback: function(){
			lssfzdy_win.hide();
			var n=dylistGrid.getStore().getCount();//获取打印列表总行数
			var recode=lzdyGrid.getSelectionModel().getSelections();
			
			var array=new Array(4);
			array[0]=recode[0].data.xm;
			array[1]=recode[0].data.gmsfhm;
			array[2]=recode[0].data.lsjmsfzkh;
			array[3]=recode[0].data.lsslid;
			for(var i=0;i<data.length;i++){
				if(data[i][1]==recode[0].data.gmsfhm){
					alert(""+recode[0].data.gmsfhm+"此人已经存在打印列表中了,不能重复添加!");
					return;
				}
			}
			var length=data.length;
			if(length==0){
				data[length]=array;
			}else{
				if(data[length-1]=" "){
					
					data[length-1]=array;
				}else{
					data[length]=array;
				}
			}
			
			
			ds.load();
			
			dylistGrid.store.load();
			dylistGrid.fireEvent("rowclick",dylistGrid,0);
			dylistGrid.getSelectionModel().selectRange(0,0);
			if(dylistGrid.getStore().getCount()>0){
				 Ext.getCmp("scdy").setDisabled(false);
				 Ext.getCmp("cpdy").setDisabled(false);
			}
			  
			   lzdyGrid.fireEvent("rowclick",lzdyGrid,curIndex+1);
			   lzdyGrid.getSelectionModel().selectRange(curIndex,curIndex);
			//提交数据
		/*	Gnt.submit(
					{bbmc:zsbbmc,bblb:zsbblb,bbmb:zsbbmbcontent}, 
					"gl/zsbb/addzsbbmbxx",
					"正在新增制式报表模板，请稍后...", 
					function(jsonData,params){
						showInfo("");
						
					}
			);*/
		}
	});
	
	var zhcx_dialog = new Gnt.ux.ZhcxDialog({
		pzlb : form30032.pzlb,
		grid:lzdyGrid,
		applyParam:function(){
			applyParam(this.grid.store);
		},
		callback: function(where){
			var grid = this.grid;
			var store = grid.store;
			var length = 0;
			var data = {"where": where};
			store.removeAll();
			data.pzlb = grid.store.pzlb;
			data.config_key = this.config_key;
			store.baseParams = data;
			if(this.applyParam){
				this.applyParam();
			}
			Ext.Msg.wait("正在执行查询，请稍后...");
			store.load({params:{start:0, limit:grid.store.pageSize}});
			store.on("load",function(store) {  
				length = store.data.length
				if(length > 0){
					curIndex = 0;
					grid.fireEvent("rowclick",grid,curIndex);
					grid.getSelectionModel().selectRange(curIndex,curIndex);
					Ext.getCmp('card').getLayout().setActiveItem(1);
				}else{
					showInfo("没有找到符合条件的数据！");
				}
				Ext.Msg.hide();
			},grid); 			        						
		}
	});
	
    	var p2 = new Ext.Panel({
    		layout:'border',
    		 defaults: {
                 bodyStyle: "padding:0px",
                 frame: false
             },
    		  items: [
    	                 //{ region: 'west', width:500, title: '',border:false,frame:false, items:[lzdyGrid]},
    	                 { region: 'east', width: 320, title: '', collapsible: false,
    	                	 items:[{
    	                		 border:false,
    	                		 frame:false,
    	                		 layout:'column',
    	                		 bodyStyle: "padding:0px",
    	                		 layoutConfig: {
    	             	    		columns: 1
    	             	    	 },
    	                		 items:[{
    	                			 width: 160,
    	                			 height:500,
    	                			 layout:'form',
    	                			 bodyStyle: "padding:5px",
    	                			 items:[
										{
											id:'picImage',
											title: '',
											height:'100%',								
											html: '照片',
											width:150,
											height:180,
											rowspan: 1,
											colspan:1
										},{height:30,border:false,frame:false},
										{
									    	// id:'item2',
									    	 title:'打印方式',
									    	 xtype: 'fieldset',
									    	 layout : 'table',
									    	 
									    	 layoutConfig: {
				    	             	    		columns: 1
				    	             	    	 },
									    	 bodyStyle : 'padding:3 0 0 0',
									    	 border:true,
									    	 hidden:false,
									    	 width:150,
									    	 height:80,
									    	 items:[{
									            	xtype:'radio',
									            	labelSeparator: '',
									            	checked:true,
									            	boxLabel: '正常打印',
									            	name:'dyfs',
									            	inputValue : "1",
									            	listeners:{
									            		'check' : function(checkbox, checked){ 
									                         if(checked){
									                        	//save_type="1";
									                         }  
									                    }
										            }
									            	},{
										            	xtype:'radio',
										            	labelSeparator: '',
										            	name:'dyfs',
										            	boxLabel: '冷僻字',
										            	inputValue : "2",
										            	listeners:{
										            		'check' : function(checkbox, checked){ 
										                         if(checked){
										                        	//save_type="2";
										                         }  
										                    }
											            }
										            	}]
									     },{height:80,border:false,frame:false},
									     {
										    	// id:'item2',
										    	 title:'打印方位',
										    	 xtype: 'fieldset',
										    	 layout : 'table',
										    	 
										    	 layoutConfig: {
					    	             	    		columns: 1
					    	             	    	 },
										    	 bodyStyle : 'padding:5 5 5 5',
										    	 border:true,
										    	 hidden:false,
										    	 width:150,
										    	 height:80,
										    	 items:[{
										            	xtype:'radio',
										            	labelSeparator: '',
										            	checked:true,
										            	boxLabel: '左边',
										            	name:'dyfw',
										            	inputValue : "1",
										            	listeners:{
										            		'check' : function(checkbox, checked){ 
										                         if(checked){
										                        	//save_type="1";
										                         }  
										                    }
											            }
										            	},{
											            	xtype:'radio',
											            	labelSeparator: '',
											            	name:'dyfw',
											            	boxLabel: '右边',
											            	inputValue : "2",
											            	listeners:{
											            		'check' : function(checkbox, checked){ 
											                         if(checked){
											                        	//save_type="2";
											                         }  
											                    }
												            }
											            	}]
										     }
    	                			  ]
    	                		 },{
    	                			 width: 150,height:500,//layout:'form',
    	                			 
    	                			 items:[
    	                			        {
    	                			        	width: 150,height:300,layout:'form',
    	                			        	bodyStyle:'overflow-x:scroll;height:300px',
    	                			        	
    	                			        	border:false,
    	                			        	frame:false,
    	                			        	items:[dylistGrid]
    	                			        },
    	                			      
                                         //dylistGrid,
                                         {height:30,border:false,frame:false},
                                         {border:false,frame:false,layout:'form',
                                        	 bodyStyle : 'padding:10',
                                        	 items:[new Ext.Button({
                              					text:'添加打印',
                             					minWidth:100,
                             					handler:function(){
                             						if(isImage==0){
        			            						alert("没有找到照片信息,不能打印!");
        			            						dylistGrid.getColumnModel().setHidden(0,false);
        			            						dylistGrid.fireEvent("rowclick",dylistGrid,0);
        			            						dylistGrid.getSelectionModel().selectRange(0,0);
        			            						return;
        			            					}else{
        			            						
        			            						if(dyzt==1){
        			            							if(confirm(" 身份号码为["+sfzhm+"]的受理信息已经被打印过,是否确定重新打印吗?")){
        			            								dytype="adddy";
        	    			            						lssfzdy_win.show();
        	    			            						lssfzdy_win.setSelRes(sfzhm,zpid);
        			            							}
        			            						}else{
        			            							dytype="adddy";
            			            						lssfzdy_win.show();
            			            						lssfzdy_win.setSelRes(sfzhm,zpid);
        			            						}
        			            						
        			            						
        			            						
        			            					}
                             					}
                             				}),{height:5,border:false,frame:false},
                             				 new Ext.Button({
                             					 id:'scdy',
                              					text:'删除打印',
                              					minWidth:100,
                              					handler:function(){
                              						if(dylistGrid.getSelectionModel().getCount()>=1){
                              							
                              							var record=dylistGrid.getSelectionModel().getSelected(); 
                              							var index = dylistGrid.getStore().indexOf(record);
                              							dylistGrid.store.remove(record);
                              							data[index]="";
                              							ds.load();
                              							//dylistGrid.store.removeAt(dyindex);//参数是行数,移除该行 
                              							if(dylistGrid.getStore().getCount()>0){
       			            							   Ext.getCmp("scdy").setDisabled(false);
       					         						   Ext.getCmp("cpdy").setDisabled(false);
       			            						    }else{
       			            						       Ext.getCmp("scdy").setDisabled(true);
          					         					   Ext.getCmp("cpdy").setDisabled(true);
       			            						    }
                              						}else{
                              							alert("请先选择要删除的打印记录!");
                              							return;
                              						}
                              					}
                              				}),{height:5,border:false,frame:false},
                              				 new Ext.Button({
                              					 id:'cpdy',
                              					text:'成批打印',
                              					minWidth:100,
                              					handler:function(){
                              						
                              					}
                              				})]}
                             
    	                			       ]
    	                		 }]
    	                	 }]
    	                	
    	                 },
    	                 { region: 'north', height: 30, title:'' , collapsible:false },
    	                 lzdyGrid,
    	                 { region: 'south',title:"",split: false, border: false, 
    	                   collapsible: true, 
    	                   height: 10,
    	                   buttons:[{
			               				text:'单张打印',
			            				minWidth:60,
			            				handler:function(){
			            					if(isImage==0){
			            						alert("没有找到照片信息,不能打印!");
			            						return;
			            					}else{
			            						if(dyzt==1){
			            							if(confirm(" 身份号码为["+sfzhm+"]的受理信息已经被打印过,是否确定重新打印吗?")){
			            								dytype="dzdy";
	    			            						lssfzdy_win.show();
	    			            						lssfzdy_win.setSelRes(sfzhm,zpid);
			            							}
			            						}else{
			            							dytype="dzdy";
    			            						lssfzdy_win.show();
    			            						lssfzdy_win.setSelRes(sfzhm,zpid);
			            						}
			            					}
			            				}
			            			},
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
    	                            ]}

    	             ]
    		
    	});
 	

	
	
	//释放iframe占用资源
	function fixIFrame(o, p){
		var iFrame = p.getEl().dom; 
		if (iFrame.src) {
			iFrame.src = "javascript:false"; 
		}
	}

	var p3 = new Ext.Panel({
		layout:'border',
		items:[newForm30032,{
			id:"p3EastId",
        	region:'east',
        	width:180,
            border:false,
            frame:false,
            items: [{
            	id:"p3WestCenterId",
            	region:'center',
            	height:10,
                border:false,
                frame:false
            },{
    			region:'south',
    			layout:'table',
    			border:false,
                frame:false,
    			layoutConfig: {
    				columns: 1
    			},
    			items: [{
					id:'p3Img',
    		 		title: '',
    		 		height:'100%',
    		 		bodyStyle:'padding:10px 10px 10px 10px',
    				width:150,
    				height:180,
    				rowspan: 1,
    				colspan:1
				},{
    				html:'',
    				bodyStyle:'padding:10px 25px 0px 20px',
    				layout:'table',
    				align:'center',
    				border:false,
    				frame:false,
    				layoutConfig: {
    					columns: 1
    				}
    			}]
    		}]
		},{
			region:'south',
			width: '100%', 
	        border:false,
	        frame:false,
	        buttons:[
		        new Ext.Button({
		        	text:'关闭',
		        	minWidth:100,
		        	handler:function(){
		        		window.parent.parent.closeWorkSpace();
		        	}
		        }),
		        new Ext.Button({
		        	text:'返回',
		        	minWidth:100,
		        	handler:function(){
		        		Ext.getCmp('card').getLayout().setActiveItem(1);
		        	}
		        })
	        ]
	    
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
        	items:[p1,p2,p3]
        }
    });
	
 
    
	
	
	
});