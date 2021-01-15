var zpid=null;
var selRes =null;
var selectedSbxxid = null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("30037","30038",function(){}))
		return;
	
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_EDZSLZT','CS_EDZSLYY','CS_EDZLZFS','CS_EDZZZLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	var form30037 = new Gnt.ux.SjpzForm({
		pzlb: '30037',//30037
		title:'',
		region:'north',
		cols:2,
		formType:'query'
	});	
	var form30038 = new Gnt.ux.SjpzForm({
		pzlb: '30038',
		title:'',
		labelWidth:120,
		region:'north',
		cols:2,
		formType:'query'
	});		
	var ydbzlzGrid = new Gnt.ux.SjpzGrid({
		pkname: 'sbxxid',
		region:'center',
		url:'yw/ydbz/ydbzlz/queryYdbzlzxx',
		pzlb: '30035',
		title: '',
		showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				slzt = selRes.data.slzt;
				selectedSbxxid = selRes.data.sbxxid;
				if(selRes){
					Ext.getCmp('picImage').body.update("<div id='_PHOTO_ID'/><IMG SRC='yw/ydbz/ydbzcx/img/render/" + selRes.data.zpid + "' width='100%' height='100%' />");
				}
				Ext.getCmp('lz').enable();
				Ext.getCmp('save').enable();
    			
				
    		}
		}
	});

	
var p1=new Ext.Panel({
	layout:'border',
	height:1000,
	defaults: {
	    collapsible: true,
	    split: true,
	    bodyStyle: 'padding:10px'
	},
	items: [{
	    title: '填写证件领取日期',
	    collapsible: false,
	    region: 'south',
	    border:false,
	    frame:false,
	    layout:'column',
	    height: 100,
	    cmargins: '0 0 0 0',
	    items:[{
	    	border:false,
	    	frame:false,
	    	region:'center',
	    	columnWidth: .7,
	    	items:[form30038]
	    },{
	    	border:false,
	    	frame:false,
	    	region:'east',
	    	layout:'form',
		    labelAlign :'right',
		    columnWidth: .3,
		    buttons:[ new Ext.Button({
		    	id:'lz',
	        	text:'领证',
	        	minWidth:100,
	        	disabled:true,
	        	handler:function(){
	        		if(selRes){
	        			form30038.fieldSetValue(form30038.getForm().findField("sbxxid"),selRes.data.sbxxid);
	        			form30038.fieldSetValue(form30038.getForm().findField("lzrgmsfhm"),selRes.data.gmsfhm);
	        			form30038.fieldSetValue(form30038.getForm().findField("lzrxm"),selRes.data.xm);
	        			form30038.fieldSetValue(form30038.getForm().findField("lzrdh"),'');
	        		}
	        	}
	        }),new Ext.Button({
	        	id:'save',
	        	text:'保存',
	        	minWidth:100,
	        	disabled:true,
	        	handler:function(){
	        		if(selectedSbxxid){
	        			var data = form30038.getForm().getValues();
	        			data.sbxxid = selRes.data.sbxxid;
						data.log_code = "F1603";
	        			Gnt.submit(
								data, 
									"yw/ydbz/ydbzlz/saveLz", 
									"正在保存该条信息，请稍后...", 
									function(jsonData,params){
										showInfo("信息保存成功！");
										ydbzlzGrid.store.reload(); 
										form30038.getForm().reset();
								}
	        			);
	        		}
	        	}
	        }),new Ext.Button({
	        	text:'关闭',
	        	minWidth:100,
	        	handler:function(){
	        		
	        	}
	        })]
	    },{
	    	frame:false,
			border:false,
			region:'center',
	    	columnWidth: 1.,
			id:'_READ_CARD_ID',
			xtype:'panel',
			html:'',
			width:10
		}]
	},{
	    title: '异地受理信息查询',
	    collapsible: false,
	    region:'north',
	    border:false,
	    frame:false,
	    layout:'column',
	    height: 100,
	    //margins: '5 0 0 0',
	    //cmargins: '5 0 0 0',
	    items:[{
	    	border:false,
	    	frame:false,
	    	region:'center',
	    	columnWidth: .9,
	    	items:[form30037]
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
	                minWidth:80,
	               handler:function(){
	            	   var data = form30037.getForm().getValues();
	            	   data.log_code = "F1602";
	   					if(Gnt.util.isEmpty(data)){
	   						showInfo("至少输入一个查询条件！");
	   						return;
	   					}
	   					data.log_code = "F1602";
						Ext.Msg.wait("正在执行查询，请稍后...");
       				
	       				var store = ydbzlzGrid.store;
	       				store.removeAll();
	       				store.baseParams = data;
	       				store.load({params:{start:0, limit:20}});
	       				store.on('load',function(s,records){
	       					if(records.length>0){
								curIndex = 0;
								ydbzlzGrid.fireEvent("rowclick",ydbzlzGrid,curIndex);
								ydbzlzGrid.getSelectionModel().selectRange(curIndex,curIndex);
	       						//Ext.getCmp('card').getLayout().setActiveItem(1);
	       					}else{
	       						Ext.getCmp('lz').disable();
	       						Ext.getCmp('save').disable();
	       					}
	       					Ext.Msg.hide();
	       				});
	       				form30038.fieldSetValue(form30038.getForm().findField("sbxxid"),'');
	        			form30038.fieldSetValue(form30038.getForm().findField("lzrgmsfhm"),'');
	        			form30038.fieldSetValue(form30038.getForm().findField("lzrxm"),'');
	        			form30038.fieldSetValue(form30038.getForm().findField("lzrdh"),'');
	               }
	          }),
	          {border:false,frame:false,width:20,height:10},
	          {
	    	    	xtype: 'DkButton',
	    	    	form:form30037,
	    	    	callback: function(){
	    	    		//Ext.getCmp("queryId").handler();
	    	    	}
	    	  }]
	    }]
	  
	    
	},{
	    title: '异地受理信息显示',
	    collapsible: false,
	    region:'center',
	    layout:'border',
	    border:false,
	    frame:false,
	    margins: '0 0 0 0',
	    height:150,
	    items:[ydbzlzGrid,{
		    title: '',
		    collapsible: false,
		    region:'east',
		    border:false,
		    frame:false,
		    width:"15%",
		    margins: '0 0 0 0',
		    layout:'table',
		    layoutConfig: {
	    		columns: 1
	    	 },
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
			}]
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