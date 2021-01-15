var selectYw = 1;
var queryFlag = null;
var selectedSelRes=null;
var selectRynbid ='';
var selectHhnbid ='';
var selectRyid ='';
var num = 0;
Ext.onReady(function(){
	Ext.QuickTips.init();
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("30017",function(){}))
		return;
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	var qxzjffGrid = new Gnt.ux.SjpzGrid({
		pkname: 'nbslid',
		pzlb: '30017',
		url:'yw/fjgl/qxzjff/getQxzjffInfo.json',
		region:'center',
//		height:500,
		title: '查询结果',
//		showPaging:true,
		listeners:{
			rowclick:function(g, rowIndex, e){
				selectedSelRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				selectRyid = selectedSelRes.data.ryid;
				selectRynbid = selectedSelRes.data.rynbid;
    			selectHhnbid = selectedSelRes.data.hhnbid;
    			var zpid = selectedSelRes.data.zpid;
    			//alert(Ext.getCmp("showZp").getValue());//showZp
				if(zpid &&　zpid != 0&&Ext.getCmp("showZp").getValue()){
					Ext.getCmp('p2Img').body.update("<IMG SRC='yw/common/img/render/" + zpid + "' width='100%' height='100%' />");
				}else{
					Ext.getCmp('p2Img').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
				}
			},
			rowdblclick:function(grid,row){
				selectedSelRes = g.store.getAt(rowIndex);
				selectRynbid = selectedSelRes.data.rynbid;
    			selectHhnbid = selectedSelRes.data.hhnbid;
			}
		}
	});	
	var p1_1 = new Ext.Panel({
		xtype: 'panel',
		border:false,
		frame: false,
    	region:'north',
    	height: 35,
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
				width: 140,
				html:'请输入分检批次号',
				border:false,
				frame: false
			},{
				xtype:'textfield',
				anchor:'100%',
				id:'fjpch',
    			autoCreate: {tag: 'input', type: 'text', size: '20', autocomplete: 'off', maxlength: '16'},
				width: 150
			},{
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
    		},{
				width: 140,
				html:'请输入派出所代码',
				border:false,
				frame: false
			},{
				anchor:'100%',
				xtype:'search_combox',
				anchor:'100%',
				id:'dwdmQuery',
				name:'dwdm',
				width: 150,
				searchUrl : "dict/utils/searchXxb?visiontype=DWXXB&whflg=1"
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
        			text:'查询',
        			minWidth:80,
        			handler:function(){
        				var fjpchTemp = Ext.getCmp('fjpch').getValue();
        				var dwdmTemp = Ext.getCmp('dwdmQuery').getValue();
        				if(fjpchTemp.length<16){
        					alert("请输入16位的分检批次号！");
        					return;
        				}
        				if(!dwdmTemp){
        					alert("请输入派出所代码！")
        					return;
        				}
        				var store = qxzjffGrid.store;
						store.baseParams = {
								fjpch:fjpchTemp,
								pcs:dwdmTemp
						};
						store.load({params:{start:0, limit:20}});	
						store.on("load",function(store) {  
							if(store.data.length > 0){
								num= store.data.length;
								Ext.getCmp('totalNum').body.update('区县已接收的受理信息总数为：'+num);
								curIndex = 0;
								Ext.getCmp('saveId').enable();
								qxzjffGrid.fireEvent("rowclick",qxzjffGrid,curIndex);
								qxzjffGrid.getSelectionModel().selectRange(curIndex,curIndex);
							}else{
								Ext.getCmp('p2Img').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
								Ext.getCmp('saveId').disable();
							}
						},qxzjffGrid); 
        			}
        		})]
    		}]
    });
    
	var p1_3 = new Ext.Panel({
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
				id:'totalNum',
				html:'区县已接收的受理信息总数为：0',
				width:250
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
    		        	   text:'发放单',
    		        	   id:'queryId',
						   minWidth:80,
							handler:function(){
								var arrayTemp=[];
								var o={};
								o.directTable="fafd";
								o.fjpch=Ext.getCmp('fjpch').getValue();
								o.num =num;
								arrayTemp.push(o);
								printfunction(0,arrayTemp);
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
    		        	   text:'保存',
    		        	   id:'saveId',
    		        	   disabled:true,
							minWidth:80,
							handler:function(){
								Gnt.submit(
										{
											nbslid:selectedSelRes.data.nbslid}, 
											"yw/fjgl/qxzjff/saveQxzjffInfo", 
											"正在保存操作，请稍后...", 
											function(jsonData,params){
												showInfo("保存成功！");
												qxzjffGrid.store.reload(); 
											}
										);
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
    });
	
	var p1 = new Ext.Panel({
		layout:'border',
		items:[
		       p1_1,
		       qxzjffGrid,
		       p1_3,{
			    title: '',
			    region:'east',
			    margins: '5 0 0 0',
			    cmargins: '5 5 0 0',
			    width: 175,
			    minSize: 100,
			    maxSize: 250,
			    items: [
					{
						id:'p2Img',
	    		 		title: '',
	    		 		height:'100%',
	    		 		bodyStyle:'padding:10px 10px 10px 10px',
	    				width:150,
	    				height:180,
	    				rowspan: 1,
	    				colspan:1
					},{
		    			columnWidth: .01,
						border:false,
						frame: false
		    		},{
            			border:false,
            			frame: false,
            			id:'showZp',
            			xtype:'checkbox',
		            	boxLabel: '显示证件照片',
		            	checked:true,
		            	name:'showZp'
            		}]
			}
		]
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