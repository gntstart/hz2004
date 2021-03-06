var selectYw = 1;
var queryFlag = null;
var selectedSelRes=null;
var selectRynbid ='';
var selectHhnbid ='';
var selectRyid ='';
Ext.onReady(function(){
	Ext.QuickTips.init();
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("30017",function(){}))
		return;
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	var qxzjjsGrid = new Gnt.ux.SjpzGrid({
		pkname: 'nbslid',
		pzlb: '30017',
		url:'yw/fjgl/qxzjjs/getQxzjjsInfo.json',
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
				selectedSelRes = grid.store.getAt(rowIndex);
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
				html:'请输入区县代码',
				border:false,
				frame: false
			},{
				xtype:'search_combox',
				anchor:'100%',
				searchUrl:'dict/utils/searchXzqh.json',
				id:'qhdmQuery',
				width: 150,
				fieldLabel:'所属区划',
				hiddenName:'xzqhbdm'
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
        				var qhdmTemp = Ext.getCmp('qhdmQuery').getValue();
        				if(fjpchTemp.length<16){
        					alert("请输入16位的分检批次号！");
        					return;
        				}
        				if(!qhdmTemp){
        					alert("请输入省市县代码！")
        					return;
        				}
        				var store = qxzjjsGrid.store;
						store.baseParams = {
								fjpch:fjpchTemp,
								ssxq:qhdmTemp
						};
						store.load({params:{start:0, limit:20}});	
						store.on("load",function(store) {  
							if(store.data.length > 0){
								Ext.getCmp('totalNum').body.update('地市已发放的受理信息总数为：'+store.data.length);
								curIndex = 0;
								Ext.getCmp('queryId').enable();
								Ext.getCmp('saveId').enable();
								qxzjjsGrid.fireEvent("rowclick",qxzjjsGrid,curIndex);
								qxzjjsGrid.getSelectionModel().selectRange(curIndex,curIndex);
							}else{
								Ext.getCmp('p2Img').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
								Ext.getCmp('queryId').disable();
								Ext.getCmp('saveId').disable();
							}
						},qxzjjsGrid); 
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
				html:'地市已发放的受理信息总数为：0',
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
    		        	   text:'常口信息',
    		        	   id:'queryId',
    		        	   disabled:true,
							minWidth:80,
							handler:function(){
								czr={
										ryid:selectRyid,
										rynbid:selectRynbid,
										hhnbid:selectHhnbid
								}
								Gnt.toRyxx(czr);
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
											"yw/fjgl/qxzjjs/saveQxzjjsInfo", 
											"正在保存操作，请稍后...", 
											function(jsonData,params){
												showInfo("保存成功！");
												qxzjjsGrid.store.reload(); 
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
		       qxzjjsGrid,
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