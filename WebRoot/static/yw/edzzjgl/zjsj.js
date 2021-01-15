var zpid=null;
var selRes =null;
var selectedSbxxid = null;
var gmsfhm=null;
var ysdd=null;	
var nbsfzid=null;
var gsrgmsfhm=null;
var ysrq=null;
var xm=null;
var gsrxm=null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("30005","30007","30001",function(){}))
		return;
	
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_EDZSLZT','CS_EDZSLYY','CS_EDZLZFS','CS_EDZZZLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	var form30005 = new Gnt.ux.SjpzForm({
		pzlb: '30005',//30037
		title:'',
		region:'north',
		cols:2,
		labelWidth:140,
		formType:'query'
	});	
	var form30007 = new Gnt.ux.SjpzForm({
		pzlb: '30007',
		title:'',
		//labelWidth:120,
		region:'center',
		cols:2,
	    fieldChange:function(field){
	    	var f = field.findParentByType("sjpz_form");
	    	sjyy=f.getForm().findField("sjyy").getValue();	
	    	xm=f.getForm().findField("xm").getValue();
	    	gmsfhm=f.getForm().findField("gmsfhm").getValue();
	    	if(sjyy!=""&&xm!=""&&gmsfhm!=""){
	    		Ext.getCmp('bc').enable();
	    	}else{
	    		Ext.getCmp('bc').disable();
	    	}
	    	
	   }
		
		//formType:''
	});		
	var zpWindow = null;
	var zjsjGrid = new Gnt.ux.SjpzGrid({
		pkname: 'nbsfzid',
		region:'center',
		url:'yw/edzjgl/queryzjcx',
		pzlb: '30001',
		title: '',
		height:100,
		showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				gmsfhm=selRes.data.gmsfhm;
    			
				nbsfzid=selRes.data.nbsfzid;
				
    		}
		}
	});

	var zhcx_dialog = new Gnt.ux.ZhcxDialog({
		pzlb : '30001',
		grid:zjsjGrid,
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
	
var p1=new Ext.Panel({
	layout:'border',
	defaults: {
	    collapsible: true,
	    split: true,
	    bodyStyle: 'padding:15px'
	},
	items: [{
	    title: '填写证件收交信息',
	    collapsible: false,
	    region: 'south',
	    border:false,
	    frame:false,
	    height: 110,
	    layout:'column',
	    items:[{
	    	border:false,
	    	frame:false,
	    	region:'center',
	    	columnWidth: .9,
	    	items:[form30007]
	    },{
	    	border:false,
	    	frame:false,
	    	region:'east',
	    	layout:'form',
		    labelAlign :'right',
		    columnWidth: .1,
	    	items:[{border:false,frame:false,width:20,height:10},
		        new Ext.Button({
	                text:'保存',
	                id:'bc',
	                minWidth:80,
	                disabled:true,
	               handler:function(){
	            	   var data = form30007.getForm().getValues();
	            	   data.nbsfzid = selRes.data.nbsfzid;
	            	   data.gmsfhm = selRes.data.gmsfhm;
	            	   data.xm = selRes.data.xm;
						Ext.Msg.wait("正在执行查询，请稍后...");
					    log_code = "F3306";
						Gnt.submit(
		   	 					data, 
		   	 					"yw/edzzjgl/zjsj/zjsjsave",
		   	 					"正在加载，请稍后...", 
		   	 					function(jsonData,params){
		   	 						showInfo("保存成功!");
		   	 					zjsjGrid.store.reload(); 		   	 						
		   	 					if(jsonData.list && jsonData.list.length>0){
		   	 						
		   	 					 }
		   	 					}
		   	 			    );
	               }
	          }),
	          {border:false,frame:false,width:20,height:15},
		        new Ext.Button({
	                text:'关闭',
	                minWidth:80,
	               handler:function(){
	            	   window.parent.closeWorkSpace();
	               }
	          })]
	    },{
	    	frame:false,
			border:false,
			columnWidth: 1.0,
			region:'south',
			id:'_READ_CARD_ID',
			xtype:'panel',
			html:'',
			width:10
		}]
	},{
	    title: '证件查询',
	    collapsible: false,
	    region:'north',
	    border:false,
	    frame:false,
	    layout:'column',
	    height: 145,
	    //margins: '5 0 0 0',
	    //cmargins: '5 0 0 0',
	    items:[{
	    	border:false,
	    	frame:false,
	    	region:'center',
	    	columnWidth: .9,
	    	items:[form30005]
	    },{
	    	border:false,
	    	frame:false,
	    	region:'east',
	    	layout:'form',
		    labelAlign :'right',
		    columnWidth: .1,
	    	items:[
		        new Ext.Button({
	                text:'查询',
	                id:'queryBtn',
	                minWidth:80,
	               handler:function(){
	            	   var data = form30005.getForm().getValues();
	            	   
	            	   if(getQueryParam("zjlqJumpTo")&& getQueryParam("zjlqJumpTo")!=""){
	            	    	data.ryid = getQueryParam("ryid");
		               }
	   					if(Gnt.util.isEmpty(data)){
	   						showInfo("至少输入一个查询条件！");
	   						return;
	   					}
	   					data.log_code = "F3402";
						Ext.Msg.wait("正在执行查询，请稍后...");
       				
	       				var store = zjsjGrid.store;
	       				store.removeAll();
	       				store.baseParams = data;
	       				store.load({params:{start:0, limit:20}});
	       				store.on('load',function(s,records){
	       					if(records.length>0){
								curIndex = 0;
								zjsjGrid.fireEvent("rowclick",zjsjGrid,curIndex);
								zjsjGrid.getSelectionModel().selectRange(curIndex,curIndex);
	       						//Ext.getCmp('card').getLayout().setActiveItem(1);
								Ext.getCmp('ckxx').enable();
								Ext.getCmp('zjxx').enable();
								Ext.getCmp('sjzj').enable();
								//Ext.getCmp('bc').enable();
	       					}else{
	       						Ext.getCmp('ckxx').disable();
								Ext.getCmp('zjxx').disable();
								Ext.getCmp('sjzj').disable();
	       						Ext.getCmp('bc').disable();
	       					}
	       					Ext.Msg.hide();
	       				});
	               }
	          }),
	          {border:false,frame:false,width:20,height:5},
	          {
	    	    	xtype: 'DkButton',
	    	    	form:form30005,
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
	    title: '证件信息',
	    collapsible: false,
	    region:'center',
	    layout:'border',
	    border:false,
	    frame:false,
	    margins: '0 0 0 0',
	    height:100,
	    items:[zjsjGrid,{
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
		    	height:70,
		    	border:false,
		    	frame:false
		    },new Ext.Button({
		    	id:'ckxx',
		    	disabled:true,
				text:'常口信息',
				minWidth:100,
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
		    }),{
		    	height:10,
		    	border:false,
		    	frame:false
		    }, new Ext.Button({
	        	   id:'zjxx',
	        	   text:'证件信息',
					minWidth:100,
					disabled:true,
					handler:function(){
					  var url = basePath + "cx/hmzjxx/sfzcx?jumpToThrycx="+"2"+"&gmsfhm="+gmsfhm;			      
        				if(parent && parent.openWorkSpace){
        					
        					parent.openWorkSpace(url,  "身份证信息查询", "sfzcx");
        				}else{
        					window.location.href = url;
        				}
					}
	           }),{
		    	height:10,
		    	border:false,
		    	frame:false
		    },new Ext.Button({
				text:'收交证件',
				id:'sjzj',
				disabled:true,
				minWidth:100,
				handler:function(){
					if(selRes){
	        			form30007.fieldSetValue(form30007.getForm().findField("xm"),selRes.data.xm);
	        			form30007.fieldSetValue(form30007.getForm().findField("gmsfhm"),selRes.data.gmsfhm);
	        			var data = form30007.getForm().getValues();
	        			sjyy=data.sjyy;	
	        	    	xm=data.xm;
	        	    	gmsfhm=data.gmsfhm;
	        	    	if(sjyy!=""&&xm!=""&&gmsfhm!=""){
	        	    		Ext.getCmp('bc').enable();
	        	    	}else{
	        	    		Ext.getCmp('bc').disable();
	        	    	}
	        		}
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
if(getQueryParam("zjlqJumpTo")&& getQueryParam("zjlqJumpTo")!=""){
	Ext.getCmp("queryBtn").handler();
}
});