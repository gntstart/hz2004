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
//            			var store = dwxxGrid.store;
//        				store.baseParams = {
//        						xzqhbdm:Ext.getCmp('qhdmQuery').getValue(),
//								dm:Ext.getCmp('dwdmQuery').getValue(),
//        						qybz:qybz
//        					};
//        				store.load({params:{start:0, limit:20}});
            		}
            	}
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
								slxxGrid.fireEvent("rowclick",slxxGrid,curIndex);
								Ext.getCmp('ckxx').enable();
							}else{
								selRes = null;
								form30017.getForm().reset();
								form30008.getForm().reset();	
								Ext.getCmp('ckxx').disable();
							}
						},slxxGrid); 
        			}
        		})]
    		},{
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:320
    		},{
				border:false,
				frame: false,
    		    items:[{
	    	    	xtype: 'DkButton',
	    	    	form:form30027,
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
		disabled:true,
		cols:2
	});		
	var slxxGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		region:'center',
		url:'yw/fjgl/fjys/queryfjslxx',
		pzlb: '30017',
		title: '',
		height:100,
		showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				if(selRes){
					form30017.getForm().loadRecord(selRes);
					//form30008.fieldSetValue("zzxxcwlb","00");
					form30008.getForm().setValues({
						 zzxxcwlb:'00',
						 nbslid:selRes.data.nbslid
					});
					//Gnt.toFormReadyonly(form30017);
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
	    height: 120,
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
		    title: '验收结果',
	    	border:false,
	 	    frame:false,
		    cmargins: '0 0 0 0',
		    items:[form30008]
	    },{
	    	frame:false,
			border:false,
			id:'_READ_CARD_ID',
			xtype:'panel',
			html:'',
			width:10
		}]
	},{
	    title: '读卡信息',
	    collapsible: false,
	    region:'north',
	    border:false,
	    frame:false,
	    cmargins: '0 0 0 0',
	    height: 200,
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
	    items:[form30017,{
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
				id:'ckxx',
				disabled:true,
				handler:function(){
					//var data = form30017.getForm().getValues();
					
   					if(Gnt.util.isEmpty(selRes)){
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
					if(selRes){
						var subdata = {
							voZjysxx: form30008.getForm().getValues()
						};
						for(o in subdata){
							if(subdata[o]){
								subdata[o] = Ext.encode(subdata[o]);
							}
						}
						Gnt.submit(
								subdata, 
								"yw/fjgl/fjys/processFjys", 
								"正在处理迁入业务信息，请稍后...", 
								function(jsonData,params){
									showInfo("分局验收保存成功！",function(){
										
									});
								}
							);						
					}else{
						alert("没有数据，无法保存操作！");
					}
				}
		    }),{
		    	height:3,
		    	border:false,
		    	frame:false
		    },new Ext.Button({
				text:'关闭',
				minWidth:100,
				handler:function(){
					
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