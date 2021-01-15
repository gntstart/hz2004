var selRes = null;
var plQueryData = null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	//本业务需要加载的配置
	var form5009 = new Gnt.ux.SjpzForm({
		pzlb: '5009',
		region : 'north',
		url: 'yw/queryRyxx',
		title:'',
		cols:2,
//		height:300,
		formType:'query'
	});	
	
	var grid5009 = new Gnt.ux.SjpzGrid({
		title: '户信息列表',
		region : 'center',
		pageSize: 50,
		url: 'gl/jttdwh/queryJttdHxx.json',
		pzlb: '5009',
		pkname: 'hhnbid',
		listeners:{
    		rowclick:function(g, rowIndex, e){
    			selRes = g.store.getAt(rowIndex);
    		}
    	}
	});
	var p1_1 = new Gnt.ux.ZdycxPanel({
		pzlb:form5009.pzlb,
		region:'center',
		layout:'border',
		visible:false,
		border:false,
		frame: false,
		height:400
	});	
	var xtmbgl_window=new Gnt.ux.xtmbglwin({
		callback: function(){
		
		}
	});
	var modifyGghwh_window = new Gnt.ux.SelectGghWhModify({
		//选择立户信息回调
		callback: function(optype, jttdInfo){
			if(optype=='single'){
				//提交数据
				Gnt.submit(
						jttdInfo, 
						"gl/jttdwh/updateJttdbz1", 
						"正在修改土地标识，请稍后...", 
						function(jsonData,params){
							showInfo("土地标识修改成功！");
							grid5009.store.reload(); 
						},
						function(jsonData,params){
							alert(jsonData.message);
						}
				);
			}else if(optype=='piliang'){
				//提交数据
				Gnt.submit(
						jttdInfo, 
						"gl/jttdwh/updateJttdbz2", 
						"正在批量修改土地标识，请稍后...", 
						function(jsonData,params){
							showInfo("土地标识批量修改成功！");
							grid5009.store.reload(); 
						},
						function(jsonData,params){
							alert(jsonData.message);
						}
				);
			}else{
				alert("类型错误!");
			}
			
		}
	});
	var p1 = new Ext.Panel({
		layout:'border',
		items:[{
		    title: '',
		    collapsible: false,
		    region:'north',
		    border:true,
		    frame:false,
		    layout:'column',
		    height: 170,
		    //margins: '5 0 0 0',
		    //cmargins: '5 0 0 0',
		    items:[{
		    	border:false,
		    	frame:false,
		    	region:'center',
		    	columnWidth: .9,
		    	items:[form5009]
		    },{
		    	border:false,
		    	frame:false,
		    	region:'east',
		    	layout:'form',
			    labelAlign :'right',
			    columnWidth: .1,
		    	items:[
		    		{border:false,frame:false,width:20,height:20},
			        new Ext.Button({
			        	id:'queryId',
		                text:'查询',
		                minWidth:80,
		               handler:function(){
		            	   var data = form5009.getForm().getValues();
	   					
		            	   Ext.Msg.wait("正在执行查询，请稍后...");
							if(Gnt.util.isEmpty(data)){
	       						showInfo("至少输入一个查询条件！");
	       						return;
	       					}
							var ary = p1_1.xszdGrid.store.data.items;
							var pxary = p1_1.pxzdGrid.store.data.items;//length 长度;
							grid5009.zdyValueKey = Gnt.zdyValueKey(ary);
							grid5009.pxary = pxary;
	       				var store = grid5009.store;
	       				store.removeAll();
	       				store.baseParams = data;
	       				plQueryData = data;
	       				store.load({params:{start:0, limit:50}});
	       				store.on('load',function(s,records){
	       					if(records.length>0){
								curIndex = 0;
								grid5009.fireEvent("rowclick",grid5009,curIndex);
								grid5009.getSelectionModel().selectRange(curIndex,curIndex);
								//Ext.getCmp('fxbg').enable();
	       					}else{
	       						//Ext.getCmp('fxbg').disable();
	       					}
	       					Ext.Msg.hide();
	       				});
		               }
		          }),
		          {border:false,frame:false,width:20,height:5},
		          {
		    	    	xtype: 'DkButton',
		    	    	minWidth:80,
		    	    	form:form5009,
		    	    	callback: function(){
		    	    		//Ext.getCmp("queryId").handler();
		    	    	}
		    	  },
		          {border:false,frame:false,width:20,height:5}
			       ]
		    }]
		  
		    
		},{
		    collapsible: false,
		    region:'center',
		    layout:'border',
		    border:false,
		    frame:false,
		    margins: '0 0 0 0',
		    items:[grid5009,{
			    title: '',
			    collapsible: false,
			    region:'east',
			    height:100,
			    border:false,
			    frame:false,
			    width:"12%",
			    margins: '0 0 0 0',
			    bodyStyle:'padding:10px 10px 10px 10px',
			    layout:'table',
			    layoutConfig: {
		    		columns: 1
		    	 },
			    labelWidth:40,
			    items:[{
			    	height:150,
			    	border:false,
			    	frame:false
			    },new Ext.Button({
					text:'修改土地标识',
					minWidth:100,
					handler:function(){
						if(selRes){
							modifyGghwh_window.show(selRes,'single');
							modifyGghwh_window.setSelRes(selRes,'single');
						}else{
							alert("请选择一条有效数据！");
							return;
						}
					}
			    }),{
			    	height:3,
			    	border:false,
			    	frame:false
			    },new Ext.Button({
					text:'批量修改土地标识',
					minWidth:100,
					handler:function(){
						var temp = {};
						temp.data =plQueryData;
						modifyGghwh_window.show(temp,'piliang');
						modifyGghwh_window.setSelRes(plQueryData,'piliang');
					}
			    }),{
			    	height:3,
			    	border:false,
			    	frame:false
			    },new Ext.Button({
		        	   text:'导出',
						minWidth:80,
						handler:function(){
							var param = grid5009.store.baseParams;
							Gnt.submit(
								param, 
								"gl/jttdwh/queryJttdHxxDaochu", 
								"导出数据准备中，请稍后...", 
								function(jsonData,params){
									var dcdata = jsonData.list;
									xtmbgl_window.show();
									xtmbgl_window.setSelRes(dcdata,"jttdwh");
								}
							);
						}
		           })]
			},{
		    	frame:false,
				border:false,
				region:'south',
				id:'_READ_CARD_ID',
				xtype:'panel',
				html:''
				
			}]
		}]
	});
	//定义分页面板
	
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