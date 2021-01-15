var zpid=null;
var selRes =null;
var selectedSbxxid = null;
var queryFlag = 1;
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("30027","30008","30017",function(){}))
		return;
	
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_EDZSLZT','CS_EDZSLYY','CS_EDZLZFS','CS_EDZZZLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	var form30027 = new Gnt.ux.SjpzForm({
		pzlb: '30027',//30037
		title:'',
		height:80,
		region:'north',
		labelWidth:120,
		columnWidth: .9,
		cols:2
	});	
	var form30008 = new Gnt.ux.SjpzForm({
		pzlb: '30008',//30037
		title:'',
		height:80,
		region:'north',
		columnWidth: .9,
		cols:2,
		formType:'query'
	});
	var p1_1 = new Ext.Panel({
		xtype: 'panel',
		border:false,
		frame: false,
		title:'手工录入',
    	region:'north',
    	//height: 35,
    	//bodyStyle: 'padding:5px',
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
				width: 70,
				html:'姓名',
				border:false,
				frame: false
			},{
				xtype:'textfield',
				anchor:'100%',
				id:'xmQuery',
				width: 150,
				fieldLabel:'',
				hiddenName:'',
				listeners:{
					 'change':function(){        
						 if(Ext.getCmp('xmQuery').getValue()&&Ext.getCmp('gmsfhmQuery').getValue()){
		                	  Ext.getCmp('fzId').enable();
		                  }
		              }  
				}
			},{
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
    		},{
				width: 70,
				html:'身份号码',
				border:false,
				frame: false
			},{
				anchor:'100%',
				xtype:'textfield',
				anchor:'100%',
				id:'gmsfhmQuery',
				name:'',
				width: 150,
				listeners:{
					 'change':function(){        
		                  if(Ext.getCmp('xmQuery').getValue()&&Ext.getCmp('gmsfhmQuery').getValue()){
		                	  Ext.getCmp('fzId').enable();
		                  }
		              }  
				}
			},{
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
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
        			text:'受理信息',
        			disabled:true,
        			minWidth:80,
        			handler:function(){
        				var store = slxxGrid.store;
						store.baseParams = {
								xm:Ext.getCmp('xmQuery').getValue(),
								gmsfhm:Ext.getCmp('gmsfhmQuery').getValue(),
								queryFlag:queryFlag
						};
						store.load({params:{start:0, limit:20}});	
						store.on("load",function(store) {  
							if(store.data.length > 0){
								curIndex = 0;
							}
						},slxxGrid); 
        			}
        		})]
    		},{
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:420
    		},{
				border:false,
				frame: false,
    		    items:[{
	    	    	xtype: 'DkButton',
	    	    	form:form30027,
	    	    	minWidth:80,
	    	    	callback: function(){
	    	    		//Ext.getCmp("queryId").handler();
	    	    	}
	    	    }]
    		}]
    });
	var form30017 = new Gnt.ux.SjpzForm({
		pzlb: '30017',
		title:'受理信息',
		labelWidth:120,
		region:'center',
		cols:2
	});		
	var slxxGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		region:'center',
		url:'yw/edzzjgl/zjys/queryslxx',
		pzlb: '30017',
		title: '',
		height:100,
		showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				slzt = selRes.data.slzt;
				selectedSbxxid = selRes.data.sbxxid;
				if(selRes){
					Ext.getCmp('zjlb').setValue(Gnt.ux.Dict.getDictLable('CS_ZJLB', selRes.data.zjlb));
					var zpid = selRes.data.zpid;
					if(zpid &&　zpid != 0){
						Ext.getCmp('picImage').body.update("<IMG SRC='yw/common/img/render/" + zpid + "' width='100%' height='100%' />");
					}else{
						Ext.getCmp('picImage').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
					}
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
	    height: 150,
	    items:[{
	    	border:false,
	 	    frame:false,
	    	layout:'column',
		    cmargins: '0 0 0 0',
		    items:[{
			    title: '对比信息',
		    	border:false,
		 	    frame:false,
			    cmargins: '0 0 0 0',
			    items:[{
			    	id:'result',
			    	html:'对比结果'
			    }]
		    }]
	    },{
		    title: '验收信息',
	    	border:false,
	 	    frame:false,
		    cmargins: '0 0 0 0',
		    items:[form30008,{
    	    	frame:false,
				border:false,
				id:'_READ_CARD_ID',
				xtype:'panel',
				html:'',
				width:10
				
			}]
	    }]
	},{
	    title: '读卡信息',
	    collapsible: false,
	    region:'north',
	    border:false,
	    frame:false,
	    //layout:'border',
	    height: 200,
	    //margins: '5 0 0 0',
	    //cmargins: '5 0 0 0',
	    items:[{
	    	border:false,
	    	frame:false,
	    	region:'center',
	    	height:100,
	    	layout:'column',
	    	items:[form30027,{
		    	border:false,
		    	frame:false,
		    	columnWidth: .1,
		    	region:'east',
		    	//layout:'form',
		    	bodyStyle:'padding:0px 0px 0px 10px',
			    columnWidth: .1,
		    	items:[{
					id:'dkImage',
					title: '',
					//height:'100%',
					html: '照片',
					width:80,
					height:80,
					rowspan: 1,
					colspan:1
				}]
		    }]
	    },p1_1]
	},{
	    title: '',
	    collapsible: false,
	    region:'center',
	    layout:'border',
	    border:false,
	    frame:false,
	    margins: '0 0 0 0',
	    height:100,
	    items:[{
	    	region:'north',
	    	title:'',
	    	height:30,
	    	layout:'table',
			layoutConfig: {
	        	columns: 10
	        },
	        items:[
				{
					frame:false,
					border:false,
					xtype:'panel',
					html:'',
					width:10
	    		},
	    		{
	            	id:'c21',
	            	xtype:'checkbox',
	            	boxLabel: '包括已验收的受理信息',
	            	name:'qybz',
	            	inputValue : "0",
	            	listeners:{
	            		'check':function (ck, checked){
	            			if (checked) {
	            				queryFlag = 2;
	            			}else{
	            				queryFlag = 1;
	            			}
//	            			var store = dwxxGrid.store;
//	        				store.baseParams = {
//	        						xzqhbdm:Ext.getCmp('qhdmQuery').getValue(),
//									dm:Ext.getCmp('dwdmQuery').getValue(),
//	        						qybz:qybz
//	        					};
//	        				store.load({params:{start:0, limit:20}});
	            		}
	            	}
	            }]
	    },form30017,{
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
				html: '无照片',
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
				text:'常口信息',
				minWidth:100,
				disabled:true,
				handler:function(){
					var data = form10016.getForm().getValues();
   					if(Gnt.util.isEmpty(data)){
   						showInfo("至少输入一个查询条件！");
   						return;
   					}else{
						czr={
								ryid:selRes.data.ryid,
								rynbid:selRes.data.rynbid,
								hhnbid:selRes.data.hhnbid
						}
						Gnt.toRyxx(czr);  						
   					}
				}
		    }),{
		    	height:3,
		    	border:false,
		    	frame:false
		    },new Ext.Button({
				text:'保存',
				minWidth:100,
				handler:function(){
					
				}
		    }),{
		    	height:3,
		    	border:false,
		    	frame:false
		    },new Ext.Button({
				text:'关闭',
				minWidth:100,
				handler:function(){
					window.parent.closeWorkSpace();
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
//	alert("证件领取跳转证件验收!")
	//Ext.getCmp("queryBtn").handler();
}
	
});