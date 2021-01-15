var zpid=null;
var selectRynbid=null;
var selectRyid=null;
var selRes = null;
var sfzhm=null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("20000",function(){}))
		return;
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_EDZSLZT','CS_EDZSLYY','CS_EDZLZFS','CS_EDZZZLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	
	var form10016 = new Gnt.ux.SjpzForm({
		pzlb: '10016',
		url: 'yw/queryRyxx',
		title: '',
		cols:2,
		formType:'query'
	});
	
	var lzblGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		region:'center',
        height:430,
		pzlb: '20000',
		title: '',
		url: 'yw/lsjmsfzgl/querylzbl',
		showPaging:true,
		listeners:{
			rowclick:function(g, rowIndex, e){
    			selRes = g.store.getAt(rowIndex);
    			curIndex = rowIndex;
    			
    			selectRynbid = selRes.data.rynbid;
    			selectRyid=selRes.data.ryid;
    			 zpid=selRes.data.zpid;
    			 sfzhm=selRes.data.gmsfhm;
    			if(zpid &&　zpid != 0){		
 					 Ext.getCmp('picImage').body.update("<IMG SRC='yw/common/img/render/" + zpid + "' width='100%' height='100%' />");
 				}else{
 					
 					Ext.getCmp('picImage').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
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
	    region: 'south',
	    border:false,
	    frame:false,
	    height: 70,
	    cmargins: '0 0 0 0',
	  //  layout:'table',
	    items:[{
	    	title: '证件业务',
            xtype: 'fieldset',
            autoHeight: true,
            layout:'table',
            buttonAlign:'right',
	  	    buttons:[
	  	    	new Ext.Button({
	                  id:'zjbl',
	                  text:'证件办理',
	                  minWidth:100,
	                 handler:function(){
	                	 if(zpid &&　zpid != 0){	
	                		 if(confirm("办理的身份证号码为["+sfzhm+"],确定吗?")){
	                		 	 log_code = "F7001";
	                			 Gnt.submit(
	 	             					{ryid:selRes.data.ryid}, 
	 	             					"yw/lsjmsfzgl/lssfzSlyw", 
	 	             					"正在进行证件办理，请稍后...", 
	 	             					function(jsonData,params){
//	 	             						if(jsonData.list.length>0){
//	 	            	                		
//	 	             						}else{
//	 	             							alert("没有查询到受理信息！")
//	 	             						}
	 	             					}
	 	             			);
	                		 }
	                	 }else{
	                		 alert("没有照片信息,不鞥能办理临时身份证!");
	                		 return;
	                	 }
	                  }
	            }),new Ext.Button({
	                  id:'sld',
	                  text:'受理单',
	                  minWidth:100,
	                 handler:function(){
	                	 if(selRes){
	                	 	 log_code = "F7002";
	                		 Gnt.submit(
	             					{ryid:selRes.data.ryid}, 
	             					"yw/lsjmsfzgl/checkslxx", 
	             					"正在查询受理信息，请稍后...", 
	             					function(jsonData,params){
	             						if(jsonData.list.length>0){
	            	                		var arrayTemp=[];
	            							var o={};
	            							o.directTable="lsjmsfzsldjb";
	            							o.ryid =selRes.data.ryid;
	            							o.rynbid =selRes.data.rynbid;
	            							arrayTemp.push(o);
	            							printfunction(0,arrayTemp); 
	             						}else{
	             							alert("没有查询到受理信息！")
	             						}
	             					}
	             			);

	                	 }
	                 }
	            }),
	            new Ext.Button({
        			text:'关闭',
        			minWidth:100,
        			handler:function(){
        				window.parent.closeWorkSpace();
        			}
        		})
	  	    ]
	    }]
	},{
		 title: '查询办证人员',
	    collapsible: false,
	    region:'north',
	    border:false,
	    frame:false,
	    layout:'column',
	    height: 120,
	    items:[{
	    	border:false,
	    	frame:false,
	    	region:'center',
	    	columnWidth: .85,
	    	items:[form10016]
	    },{
	    	border:false,
	    	frame:false,
	    	region:'east',
	    	layout:'form',
		    labelAlign :'right',
		    //margins: '0 0 0 0',
		    bodyStyle:'padding:20px 10px 10px 60px',
		    columnWidth: .15,
		    items:[
		        new Ext.Button({
	            id:'cx',
	            text:'查询',
	            minWidth:60,
	            handler:function(){
	            	var data = form10016.getForm().getValues();
	            	Ext.Msg.wait("正在执行查询，请稍后...");
	            	if(getQueryParam("zjslJumpTo")&& getQueryParam("zjslJumpTo")!=""){
						data.ryid=getQueryParam("ryid");
					}else{
						if(Gnt.util.isEmpty(data)){
	   						showInfo("至少输入一个查询条件！");
	   						return;
	   					}
					}
	            	data.log_code='F7002';
	            	var store = lzblGrid.store;
	   				store.removeAll();
	   				store.baseParams = data;
	 				store.load({params:{start:0, limit:20}});
	 				store.on("load",function(store){
	 					if(store.data.length>0){
	 						curIndex = 0;
	 						lzblGrid.fireEvent("rowclick",lzblGrid,curIndex);
	 						lzblGrid.getSelectionModel().selectRange(curIndex,curIndex);
	 						Ext.getCmp("zjbl").setDisabled(false);
	 						
	 					}else{
	 						
	 					}
	 					Ext.Msg.hide();
	 				});
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
	    height:400,
	    items:[lzblGrid
	           ]
	},{
	    title: '',
	    collapsible: false,
	    region:'east',
	    border:false,
	    frame:false,
	    width:"18%",
	    margins: '0 0 0 0',
	    layout:'table',
	    layoutConfig: {
    		columns: 1
    	 },
	    height:400,
	    labelWidth:40,
	    items:[{
			id:'picImage',
			title: '',
			height:'100%',
			bodyStyle:'padding:10px 10px 10px 10px',
			html: '无照片',
			width:150,
			height:150,
			rowspan: 1,
			colspan:1
		},{
	    	height:130,
	    	border:false,
	    	frame:false
	    },{		 
	      	//id:'c1',
	    	xtype:'checkbox',
	    	labelSeparator: '',
	    	boxLabel: '打印证件',
	    	checked:false,
			listeners:{
				'check': function(obj,checked){
					if(checked){
						///slzt="92";
					}else{
						///slzt="90";
					}
				}
			}
	    }]
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
		
	 
	
	// shxxStore.load({params:{slzt:"90",start:0, limit:50}});
		Ext.getCmp("zjbl").setDisabled(true);
		if(getQueryParam("zjslJumpTo")&& getQueryParam("zjslJumpTo")!=""){
			Ext.getCmp("cx").handler();
		}	
		
	
});