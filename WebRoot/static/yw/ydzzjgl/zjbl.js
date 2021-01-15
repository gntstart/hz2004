var zpid=null;
var selRes =null;
var lczjslselRes = null;
var selectedSbxxid = null;
var kzflag = false;
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10016",function(){}))
		return;
	
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_EDZSLZT','CS_EDZSLYY','CS_EDZLZFS','CS_EDZZZLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	var form10016 = new Gnt.ux.SjpzForm({
		pzlb: '10016',//30037
		title:'',
		region:'north',
		cols:2,
		formType:'query'
	});	
	var zpWindow = null;
	var zjslGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		region:'center',
		url:'yw/edzzjgl/zjsl/queryZjslxx',
		pzlb: '20000',
		title: '',
		height:100,
		showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				slzt = selRes.data.slzt;
				selectedSbxxid = selRes.data.sbxxid;
				if(selRes){
					var zpid = selRes.data.zpid;
					if(zpid &&　zpid != 0){
						Ext.getCmp('picImage').body.update("<div id='_PHOTO_ID'><IMG SRC='yw/common/img/render/" + zpid + "' width='100%' height='100%' /></DIV>");
						//Ext.getCmp('pic2Image').body.update("<div id='_PHOTO_ID'><IMG SRC='yw/common/img/render/" + zpid + "' width='100%' height='100%' /></DIV>");
						//Ext.getCmp('picImage1').body.update("<IMG SRC='yw/common/img/render/" + zpid + "' width='100%' height='100%' />");
					}else{
						Ext.getCmp('picImage').body.update("<div id='_PHOTO_ID'><IMG SRC='images/no_pic.gif' width='100%' height='100%' /></DIV>");
						//Ext.getCmp('pic2Image').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
					}
					zpWindow = new Gnt.ux.SelectZpAll({
						ryid:selRes.data.ryid
					});
				}
    		}
		}
	});
	
	var zhcx_dialog = new Gnt.ux.ZhcxDialog({
		pzlb : '20000',
		grid:zjslGrid,
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
				}else{
					showInfo("没有找到符合条件的数据！");
				}
				Ext.Msg.hide();
			},grid); 			        						
		}
	});	
	
	var sfzdy_window = new Gnt.ux.SfzdyDialog({
		callback: function(optype, fhdata){
			//提交数据
//			Gnt.submit(
//					dwxxAdd, 
//					"gl/xtmbgl/dwxxwh/addDwDm", 
//					"正在新增单位信息，请稍后...", 
//					function(jsonData,params){
//						showInfo("单位信息新增成功！");
//						errorFlag = true;
//						dwxxGrid.store.reload(); 
//					},
//					function(jsonData,params){
//						alert(jsonData.message);
//						errorFlag = false;
//					}
//			);
		}
	});
	var zjblOne_window = new Gnt.ux.ZjblOneDialog({
		callback: function(optype, fhdata){
			var csrq = fhdata.csrq;
			var nl = Gnt.date.jsGetAge(csrq);
			if(nl < 16){
				showInfo("未满16岁，不能办证！");
				return;
			}else{
				log_code = "F3101";
				//提交数据
				Gnt.submit(
						fhdata, 
						"yw/ydzzjgl/zjbl/saveZjbl", 
						"正在证件办理，请稍后...", 
						function(jsonData,params){
							fhdata.bzslid=jsonData.list[0].voYdzZjslfhxx[0].bzslid;
							sfzdy_window.show();
							sfzdy_window.setSelRes(fhdata);
						},
						function(jsonData,params){
							alert(jsonData.message);
							errorFlag = false;
						}
				);
			}
			
		}
	});
	var lczjslGrid = new Gnt.ux.SjpzGrid({
		pkname: 'nbslid',
		region:'center',
		url:'yw/ydzzjgl/zjbl/queryLsZjblxx',
		pzlb: '30017',
		title: '',
		showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){
				lczjslselRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				slzt = lczjslselRes.data.slzt;
				selectedSbxxid = lczjslselRes.data.sbxxid;
				if(lczjslselRes){
					var zpid = lczjslselRes.data.zpid;
					if(zpid &&　zpid != 0){
						Ext.getCmp('picImage').body.update("<IMG SRC='yw/common/img/render/" + zpid + "' width='100%' height='100%' />");
					}else{
						Ext.getCmp('picImage').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
					}
//					zpWindow = new Gnt.ux.SelectZpAll({
//						ryid:lczjslselRes.data.ryid
//					});
				}

				
    		}
		}
	});

var p1=new Ext.Panel({
	layout:'border',
	defaults: {
	    collapsible: true,
	    split: true,
	    bodyStyle: 'padding:15px'
	},
	items: [{
	    title: '',
	    collapsible: false,
	    region: 'south',
	    border:false,
	    frame:false,
	    height: 80,
	    items:[{
	    	border:false,
	 	    frame:false,
	    	layout:'column',
		    cmargins: '0 0 0 0',
		    items:[{
			    title: 'HZ2004',
		    	border:false,
		 	    frame:false,
			    cmargins: '0 0 0 0',
			    items:[{
					xtype: 'panel',
					border:false,
					frame: false,
			    	region:'south',
			    	height: 30,
			    	bodyStyle: 'padding:5px',
					layout:'table',
					layoutConfig: {
			        	columns: 30
			        },
			        items:[
						{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			    		},{
							border:false,
							frame: false,
			    		    items:[
			    		           new Ext.Button({
			    		        	   id:'ckxx',
			    		        	   text:'常口信息',
										minWidth:80,
										disabled:true,
										handler:function(){
											if(selRes){
												czr={
														ryid:selRes.data.ryid,
														rynbid:selRes.data.rynbid,
														hhnbid:selRes.data.hhnbid
												}
												Gnt.toRyxx(czr);
											}else{
												alert("请选择一条有效数据！");
												return;
											}
											
										}
			    		           })
			    		    ]
			    		},{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			    		}, {
							border:false,
							frame: false,
			    		    items:[
			    		           new Ext.Button({
			    		        	   id:'ckbg',
			    		        	   text:'常口变更',
			    		        	   disabled:true,
										minWidth:80,
										handler:function(){
											if(selRes){
						        				var url = basePath + "yw/hjyw/bggz?jumpToBggz="+"zjBggz"+"&gmsfhm="+selRes.data.gmsfhm;			      
						        				if(parent && parent.openWorkSpace){
						        					parent.openWorkSpace(url,  "变更更正", "bggz");
						        				}else{
						        					window.location.href = url;
						        				}
						        			
											}else{
												alert("请先选中一条有效的数据!");
											}
										}
			    		           })
			    		    ]
			    		},{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			    		},{
							border:false,
							frame: false,
			    		    items:[
			    		           new Ext.Button({
			    		        	   id:'zjgs',
			    		        	   text:'证件挂失',
			    		        	   disabled:true,
										minWidth:80,
										handler:function(){
											if(selRes){
												var url = basePath + "yw/edzjgl/zjgs?zjlqJumpTo="+"1"+"&ryid="+selRes.data.ryid;			      
						        				if(parent && parent.openWorkSpace){
						        					parent.openWorkSpace(url,  "证件挂失", "zjgs");
						        				}else{
						        					window.location.href = url;
						        				}
											}else{
												alert("请先选中一条有效的数据!");
											}
										}
			    		           })
			    		    ]
			    		},{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			    		},{
							border:false,
							frame: false,
			    		    items:[
			    		           new Ext.Button({
			    		        	   id:'zjsj',
			    		        	   text:'证件收交',
			    		        	   disabled:true,
										minWidth:80,
										handler:function(){
											if(selRes){
												var url = basePath + "yw/edzzjgl/zjsj?zjlqJumpTo="+"1"+"&ryid="+selRes.data.ryid;			      
						        				if(parent && parent.openWorkSpace){
						        					parent.openWorkSpace(url,  "证件收交", "zjsj");
						        				}else{
						        					window.location.href = url;
						        				}
											
											}else{
												alert("请先选中一条有效的数据!");
											}
										}
			    		           })
			    		    ]
			    		},{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			    		},{
							border:false,
							frame: false,
			    		    items:[
			    		           new Ext.Button({
			    		        	   id:'txcj',
			    		        	   text:'图像采集',
			    		        	   disabled:true,
										minWidth:80,
										handler:function(){
											if(selRes){
												Gnt.photo.setPhoto(null, true);
												Gnt.photo.OpenEdit();
											}else{
												alert("请先选中一条有效的数据!");
											}
										}
			    		           })
			    		    ]
			    		},{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			    		},{
							border:false,
							frame: false,
			    		    items:[
			    		           new Ext.Button({
			    		        	   id:'zjbl',
			    		        	   text:'证件办理',
			    		        	   disabled:true,
										minWidth:80,
										handler:function(){
											if(selRes){
												zjblOne_window.show();
												zjblOne_window.setSelRes(selRes);
											}else{
												alert("请先选中一条有效的数据!");
											}
											
										}
			    		           })
			    		    ]
			    		},{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			    		},{
							border:false,
							frame: false,
			    		    items:[
			    		           new Ext.Button({
			    		        	   text:'关闭',
										minWidth:80,
										handler:function(){
											window.parent.closeWorkSpace();
										}
			    		           })
			    		    ]
			    		}
					]
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
				}]}]}]
	},{
	    title: '户籍查询',
	    collapsible: false,
	    region:'north',
	    border:false,
	    frame:false,
	    layout:'column',
	    height: 120,
	    //margins: '5 0 0 0',
	    //cmargins: '5 0 0 0',
	    items:[{
	    	border:false,
	    	frame:false,
	    	region:'center',
	    	columnWidth: .8,
	    	items:[form10016]
	    },{
	    	border:false,
	    	frame:false,
	    	region:'east',
	    	layout:'form',
		    labelAlign :'right',
		    columnWidth: .2,
	    	items:[
		        new Ext.Button({
	                text:'查询',
	                minWidth:80,
	               handler:function(){
	            	var data = form10016.getForm().getValues();
	            	
   					if(Gnt.util.isEmpty(data)){
   						showInfo("至少输入一个查询条件！");
   						return;
   					}
   					data.log_code = "F3201";
					Ext.Msg.wait("正在执行查询，请稍后...");
       				var store = zjslGrid.store;
       				store.removeAll();
       				store.baseParams = data;
       				store.load({params:{start:0, limit:20}});
       				store.on('load',function(s,records){
       					if(records.length>0){
							curIndex = 0;
							Ext.Msg.hide();
							zjslGrid.fireEvent("rowclick",zjslGrid,curIndex);
							zjslGrid.getSelectionModel().selectRange(curIndex,curIndex);
							Ext.getCmp('ckxx').enable();
							Ext.getCmp('ckbg').enable();
							Ext.getCmp('zjgs').enable();
							Ext.getCmp('zjsj').enable();
							Ext.getCmp('txcj').enable();
							Ext.getCmp('zjbl').enable();
       					}else{
       						Ext.getCmp('ckxx').disable();
       						Ext.getCmp('ckbg').disable();
       						Ext.getCmp('zjgs').disable();
       						Ext.getCmp('zjsj').disable();
       						Ext.getCmp('txcj').disable();
       						Ext.getCmp('zjbl').disable();
       					}
       					Ext.Msg.hide();
       				});
	               }
	          }),
	          {border:false,frame:false,width:20,height:5},
	          {
	    	    	xtype: 'DkButton',
	    	    	form:form10016,
	    	    	callback: function(){
	    	    		//Ext.getCmp("queryId").handler();
	    	    	}
	    	   },
	          {border:false,frame:false,width:20,height:5},
		        new Ext.Button({
	                text:'组合查询',
	                minWidth:80,
	               handler:function(){
	            	   zhcx_dialog.show();	
	               }
	          })]
	    }]
	  
	    
	},{
	    title: '查询结果',
	    collapsible: false,
	    region:'center',
	    layout:'border',
	    border:false,
	    frame:false,
	    margins: '0 0 0 0',
	    height:100,
	    items:[zjslGrid,{
		    title: '',
		    collapsible: false,
		    region:'east',
		    height:100,
		    border:false,
		    frame:false,
		    width:"15%",
		    margins: '0 0 0 0',
		    bodyStyle:'padding:10px 10px 10px 10px',
		    layout:'table',
		    layoutConfig: {
	    		columns: 1
	    	 },
		    labelWidth:40,
		    items:[{
				id:'picImage',
				title: '',
				//height:'100%',
				bodyStyle:'padding:10px 10px 10px 10px',
				html: '<div id="_PHOTO_ID">照片</DIV>',
				width:150,
				height:150,
				rowspan: 1,
				colspan:1
			},{
		    	height:3,
		    	border:false,
		    	frame:false
		    },{
		    	height:15,
		    	border:false,
		    	frame:false
		    },new Ext.Button({
				text:'所有照片',
				minWidth:100,
				handler:function(){
					zpWindow.show();
				}
		    })]
		}
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
	        	items:[p1]
	        }
	    });
	
});