var zpid=null;
var selRes =null;
var selectedSbxxid = null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("30027","10016","30038",function(){}))
		return;
	
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_EDZSLZT','CS_EDZSLYY','CS_EDZLZFS','CS_EDZZZLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	var form30027 = new Gnt.ux.SjpzForm({
		pzlb: '30027',//30037
		title:'',
		region:'north',
		labelWidth:120,
		cols:2
	});	
	var form30001 = new Gnt.ux.SjpzForm({
		closable: false,
		region:'center',
		pzlb: '30001',
		title:'证件信息',
		labelWidth:120,
		cols:2
	});		
	var zpWindow = null;
	var zjlqGrid = new Gnt.ux.SjpzGrid({
		pkname: 'nbsfzid',
		region:'center',
		url:'yw/edzzjgl/zjsl/queryZjslxx',
		pzlb: '30001',
		title: '',
		height:260,
		showPaging:true,
		listeners:{
			rowclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				slzt = selRes.data.slzt;
				selectedSbxxid = selRes.data.sbxxid;
				if(selRes){
					//Ext.getCmp('zjlb').setValue(Gnt.ux.Dict.getDictLable('CS_ZJLB', selRes.data.zjlb));
					var zpid = selRes.data.zpid;
					if(zpid &&　zpid != 0){
						Ext.getCmp('picImage').body.update("<IMG SRC='yw/common/img/render/" + zpid + "' width='100%' height='100%' />");
						//Ext.getCmp('picImage1').body.update("<IMG SRC='yw/common/img/render/" + zpid + "' width='100%' height='100%' />");
					}else{
						Ext.getCmp('picImage').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
						//Ext.getCmp('picImage1').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
					}
					zpWindow = new Gnt.ux.SelectZpAll({
						ryid:selRes.data.ryid
					});
				}
    			
				
    		}
		}
	});

var p2=new Ext.Panel({
	layout:'border',
	items: [{
		region:'east',
		width: '15%',
		height:'100%',
		border:false,
	    frame:false,
	    bodyStyle:'padding:10px 25px 0px 20px',
		items:[{
			border:false,
    	    frame:false,
			items:[{
    	    	columnWidth:0.15,
    	    	items:[{
    	    		title: '',
    				height:'100%',
    				bodyStyle:'padding:10px 10px 10px 10px',
    				html: '<div id="_PHOTO_IDNew">照片</DIV>',
    				width:120,
    				height:120,
    				rowspan: 1,
    				colspan:1 	
    			}]
    	    }]
		},{
			border:false,
    	    frame:false,
			height:10
		},new Ext.Button({
			text:'证件信息',
			minWidth:80,
			disabled:true,
			handler:function(){
				
			}
		}),{
			border:false,
    	    frame:false,
			height:10
		},new Ext.Button({
			text:'常口信息',
			disabled:true,
			minWidth:80,
			handler:function(){
				
			}
		}),{
			border:false,
    	    frame:false,
			height:10
		},new Ext.Button({
			text:'关闭',
			minWidth:80,
			bodyStyle:'padding:10px 25px 0px 20px',
			handler:function(){
				window.parent.closeWorkSpace();
			}
		})]
		
},{
    	    title: '读卡信息',
    	    collapsible: false,
    	    region:'north',
    	    border:false,
    	    frame:false,
    	    height: 210,
    	    layout:'column',
    	    defaults:{
    			border:false,
    			frame:false,
    			columnWidth:0.85
    		},
    	    items:[{
    	    	border:false,
    	    	frame:false,
    	    	region:'center',
    	    	height: 180,
    	    	items:[form30027,{
    	    		xtype: 'panel',
    	    		border:false,
    	    		frame: false,
    	        	region:'north',
    	        	height: 40,
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
    	    				width:30
    	        		},{
    	    				width: 70,
    	    				html:'最新地址:',
    	    				border:false,
    	    				frame: false
    	    			},{
    	    				anchor:'100%',
    	    				xtype:'textfield',
    	    				anchor:'100%',
    	    				name:'zxdz',
    	    				width: 700
    	    			},{
    	    				frame:false,
    	    				border:false,
    	    				xtype:'panel',
    	    				html:'',
    	    				width:10
    	        		},{
    	                	id:'c21',
    	                	xtype:'checkbox',
    	                	boxLabel: '读取最新地址',
    	                	name:'dqFlag',
    	                	inputValue : "0",
    	                	listeners:{
    	                		'check':function (ck, checked){
    	                			if (checked) {
    	                				alert("选中");
    	                			}else{
    	                				alert("取消");
    	                			}
    	                		}
    	                	}
    	                },{
    	    				frame:false,
    	    				border:false,
    	    				xtype:'panel',
    	    				html:'',
    	    				width:10
    	        		}]
    	        }]
    	    },{
    	    	border:false,
    	    	frame:false,
    	    	region:'east',
    	    	layout:'form',
    		    labelAlign :'right',
    		    columnWidth: .15,
    	    	items:[{border:false,frame:false,width:20,height:5},
    	          {
    		    		title: '',
    					height:'100%',
    					bodyStyle:'padding:10px 10px 10px 10px',
    					html: '<div id="_PHOTO_ID">照片</DIV>',
    					width:120,
    					height:120,
    					rowspan: 1,
    					colspan:1 	
    				},{border:false,frame:false,width:20,height:15},{
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
    	    }]
    	    //margins: '5 0 0 0',
    	    //cmargins: '5 0 0 0',
    	},form30001,{
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
	    			    	html:'对比结果:'
	    			    }]}]},{
	    		    title: 'HZ2004',
	    	    	border:false,
	    	 	    frame:false,
	    		    cmargins: '0 0 0 0',
	    		    items:[{
	    		    	html:'共有0条证件信息'
	    		    },{
		    	    	frame:false,
						border:false,
						id:'_READ_CARD_ID',
						xtype:'panel',
						html:'',
						width:10
						
					}]}]
	    	}/*,{
	    		region:'east',
	    		items:[{
	    			region:'north',
	    			html:'aaaaa'
	    		},{
	    			region:'center',
	    			html:'bbbbb'
	    		},{
	    			region:'south',
	    			html:'ccccc'
	    		}]
	    		
	    	}*/
	]
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
	        	items:[p2]
	        }
	    });
	
});