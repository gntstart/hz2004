var selRes = null;
var selectedHhnbid = null;
var plQueryData = null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	//本业务需要加载的配置
	var form5010 = new Gnt.ux.SjpzForm({
		pzlb: '5010',
		region : 'north',
		url: 'yw/queryRyxx',
		title:'',
		cols:2,
//		height:300,
		formType:'query'
	});	
	
	var grid5010 = new Gnt.ux.SjpzGrid({
		title: '人员列表',
		region : 'center',
		pageSize: 50,
		url: 'gl/qydjwh/queryQydjRy.json',
		pzlb: '5010',
		pkname: 'rynbid',
		dcFlag:true,
		listeners:{
    		rowclick:function(g, rowIndex, e){
    			selRes = g.store.getAt(rowIndex);
    			selectedHhnbid=selRes.data.hhnbid;
    		}
    	}
	});
	var p1_1 = new Gnt.ux.ZdycxPanel({
		pzlb:form5010.pzlb,
		region:'center',
		layout:'border',
		visible:false,
		border:false,
		frame: false,
		height:400
	});	
	var zhcx_dialog = new Gnt.ux.ZhcxDialog({
		pzlb : form5010.pzlb,
		grid:grid5010,
		callback: function(where){
			var grid = this.grid;
			var store = grid.store;
			var length = 0;
			var data = {"where": where};
			var ary = p1_1.xszdGrid.store.data.items;
			var pxary = p1_1.pxzdGrid.store.data.items;//length 长度;
			grid5010.zdyValueKey = Gnt.zdyValueKey(ary);
			grid5010.pxary = pxary;
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
	var modifyQydjwh_window = new Gnt.ux.SelectQydjWhModify({
		//选择立户信息回调
		callback: function(optype, jttdInfo){
			if(optype=='single'){
				//提交数据
				Gnt.submit(
						jttdInfo, 
						"gl/qydjwh/updateDjzt1", 
						"正在修改冻结状态，请稍后...", 
						function(jsonData,params){
							showInfo("冻结状态修改成功！");
							grid5010.store.reload(); 
						},
						function(jsonData,params){
							alert(jsonData.message);
						}
				);
			}else if(optype=='piliang'){
				//提交数据
				Gnt.submit(
						jttdInfo, 
						"gl/qydjwh/updateDjzt2", 
						"正在批量修改冻结状态，请稍后...", 
						function(jsonData,params){
							showInfo("冻结状态批量修改成功！");
							grid5010.store.reload(); 
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
		    height: 140,
		    //margins: '5 0 0 0',
		    //cmargins: '5 0 0 0',
		    items:[{
		    	border:false,
		    	frame:false,
		    	region:'center',
		    	columnWidth: .9,
		    	items:[form5010]
		    },{
		    	border:false,
		    	frame:false,
		    	region:'east',
		    	layout:'form',
			    labelAlign :'right',
			    columnWidth: .1,
		    	items:[
		    		{border:false,frame:false,width:20,height:40},
			        new Ext.Button({
			        	id:'queryId',
		                text:'查询',
		                minWidth:80,
		               handler:function(){
		            	   var data = form5010.getForm().getValues();
	   					
		            	   Ext.Msg.wait("正在执行查询，请稍后...");
							if(Gnt.util.isEmpty(data)){
	       						showInfo("至少输入一个查询条件！");
	       						return;
	       					}
							var header=[];
							var shuxing = [];
							var zdyValueKey = null;
							var ary = p1_1.xszdGrid.store.data.items;
							var pxary = p1_1.pxzdGrid.store.data.items;//length 长度;
							
							grid5010.zdyValueKey = Gnt.zdyValueKey(ary);
							grid5010.pxary = pxary;
	       				var store = grid5010.store;
	       				store.removeAll();
	       				store.baseParams = data;
	       				grid5010.type = 'queryQydjRy';
						var config=grid5010.colModel.config;
						Ext.each(config,function(r){
							header.push(r.header);
							shuxing.push(r.dataIndex);
						});
						store.baseParams.header = encodeURI(header);
						store.baseParams.shuxing = encodeURI(shuxing);
						store.baseParams.zdyValueKey = Ext.encode(grid5010.zdyValueKey);
						store.baseParams.daochuFlag = 'queryQydjRy';
	       				plQueryData = data;
	       				store.load({params:{start:0, limit:50}});
	       				store.on('load',function(s,records){
	       					if(records.length>0){
								curIndex = 0;
								grid5010.fireEvent("rowclick",grid5010,curIndex);
								grid5010.getSelectionModel().selectRange(curIndex,curIndex);
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
		    	    	form:form5010,
		    	    	callback: function(){
		    	    		//Ext.getCmp("queryId").handler();
		    	    	}
		    	  },
		          {border:false,frame:false,width:20,height:5}/*,
			        new Ext.Button({
		                text:'组合查询',
		                minWidth:80,
		               handler:function(){
		            	   zhcx_dialog.show();
		               }
		          })*/]
		    }]
		  
		    
		},{
		    collapsible: false,
		    region:'center',
		    layout:'border',
		    border:false,
		    frame:false,
		    margins: '0 0 0 0',
		    items:[grid5010,{
			    title: '',
			    collapsible: false,
			    region:'east',
			    height:100,
			    border:false,
			    frame:false,
			    width:"10%",
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
			    },/*new Ext.Button({
					text:'修改冻结状态',
					minWidth:100,
					handler:function(){
						if(selRes){
							modifyQydjwh_window.show(selRes,'single');
							modifyQydjwh_window.setSelRes(selRes,'single');
						}else{
							alert("请选择一条有效数据！");
							return;
						}
					}
			    }),*/{
			    	height:3,
			    	border:false,
			    	frame:false
			    },new Ext.Button({
					text:'修改冻结状态',
					minWidth:100,
					handler:function(){
						modifyQydjwh_window.show(selectedHhnbid,'piliang');
						modifyQydjwh_window.setSelRes(plQueryData,'piliang');
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