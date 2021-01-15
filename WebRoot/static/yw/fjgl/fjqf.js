var kzxx=null;

Ext.onReady(function(){
	Ext.QuickTips.init();
	if(!Gnt.loadSjpzb("10019,30017,10001,10022,10018,10017,10002,20000",function(){}))
		return;	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX','CS_BGGZLB'],function(){});

	var form30002 = new Gnt.ux.SjpzForm({
		columnWidth:0.8,
		pzlb: '30002',
		url: 'yw/queryRyxx',
		title:'',
		cols:2,
//		height:300,
		formType:'query'
	});	
	var fjqfGrid =  new Gnt.ux.SjpzGrid({
		pkname: 'nbslid',
		pzlb: '30017',
		url:'yw/fjgl/fjqf/queryFjqfxx',
		region:'center',
		title: '',
		showPaging:true,
        view:new Ext.grid.GridView({
				enableRowBody:true
		}),
		stripeRows: true,
       
        //sm:csm,
		loadMask: {msg:'正在加载数据，请稍侯……'},
		//bodyStyle:'width:50%',
		bodyStyle:'overflow-y:auto;overflow-x:scroll;height:300px',
        border:true,
        frame:false,
        anchor:'100% 100%',
	    margins: '0 0 0 0',
		cmargins: '0 0 0 0',        
		iconCls:'icon-grid',
        title:'',
        pageSize:user.dwDySet.ywlimit,
        listeners:{
        	rowclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				selectRyid = selRes.data.ryid;
				selectRynbid = selRes.data.rynbid;
				zpid = selRes.data.zpid;
				Ext.getCmp("slxxxm").setValue(selRes.data.xm);
				Ext.getCmp("slxxxb").setValue(Gnt.ux.Dict.getDictLable('CS_XB', selRes.data.xb));
				Ext.getCmp("slxxcs").setValue(selRes.data.csrq);
				Ext.getCmp("slxxmz").setValue(Gnt.ux.Dict.getDictLable('CS_MZ', selRes.data.mz));
				Ext.getCmp("slxxhm").setValue(selRes.data.gmsfhm);
				Ext.getCmp("slxxslyy").setValue(Gnt.ux.Dict.getDictLable('CS_EDZSLYY', selRes.data.slyy));
				Ext.getCmp("slxxlz").setValue(Gnt.ux.Dict.getDictLable('CS_EDZLZFS', selRes.data.lqfs));
				Ext.getCmp("slxxyxqxq").setValue(selRes.data.yxqxqsrq);
				Ext.getCmp("slxxyxqxz").setValue(selRes.data.yxqxjzrq);
				Ext.getCmp("slxxxz").setValue(selRes.data.zz);
				if(Ext.getCmp("r12").getValue()){
					if(zpid &&　zpid != 0){
						Ext.getCmp('picImage').body.update("<IMG SRC='yw/common/img/render/" + zpid + "' width='100%' height='100%' />");
					}else{
						Ext.getCmp('picImage').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
					}
				}
				if(Ext.getCmp("r13").getValue()){
					Gnt.submit(
							{ryid:selRes.data.ryid}, 
							"yw/common/queryHcy", 
							"正在查询常口信息，请稍后...", 
							function(jsonData,params){
								if(jsonData.list&&jsonData.list[0]){
									var tempObj = jsonData.list[0];//rkxxpicImage
									var rkxxzpid = tempObj.zpid
									if(rkxxzpid &&　rkxxzpid != 0){
										Ext.getCmp('rkxxpicImage').body.update("<IMG SRC='yw/fjgl/fjsh/img/render/" + rkxxzpid + "' width='100%' height='100%' />");
									}else{
										Ext.getCmp('rkxxpicImage').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
									}
									Ext.getCmp("ckxxxm").setValue(tempObj.xm);
									Ext.getCmp("ckxxxb").setValue(Gnt.ux.Dict.getDictLable('CS_XB', tempObj.xb));
									Ext.getCmp("ckxxmz").setValue(Gnt.ux.Dict.getDictLable('CS_MZ', tempObj.mz));
									Ext.getCmp("ckxxcsrq").setValue(tempObj.csrq);
									Ext.getCmp("ckxxsfhm").setValue(tempObj.gmsfhm);
									Ext.getCmp("ckxxpcs").setValue(Gnt.ux.Dict.getDictLable('DWXXB', tempObj.pcs));
									var mlph = "";
									if(tempObj.mlph != undefined){
										mlph = tempObj.mlph;
									}
									Ext.getCmp("ckxxsfzdz").setValue(Gnt.ux.Dict.getDictLable('XZQHB', tempObj.ssxq)+mlph+tempObj.mlxz);
									Ext.getCmp("ckxxxzjd").setValue(Gnt.ux.Dict.getDictLable('XZJDXXB', tempObj.xzjd));
									Ext.getCmp("ckxxjlx").setValue(Gnt.ux.Dict.getDictLable('JLXXXB', tempObj.jlx));
									Ext.getCmp("ckxxmlph").setValue(tempObj.mlph);
									Ext.getCmp("ckxxmlxz").setValue(tempObj.mlxz);
									Ext.getCmp("ckxxssxq").setValue(Gnt.ux.Dict.getDictLable('XZQHB', tempObj.ssxq));
								}
							}
					);
				}else{
					Ext.getCmp('rkxxpicImage').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
					Ext.getCmp("ckxxxm").setValue("");
					Ext.getCmp("ckxxxb").setValue("");
					Ext.getCmp("ckxxmz").setValue("");
					Ext.getCmp("ckxxcsrq").setValue("");
					Ext.getCmp("ckxxsfhm").setValue("");
					Ext.getCmp("ckxxpcs").setValue("");
					Ext.getCmp("ckxxsfzdz").setValue("");
					Ext.getCmp("ckxxxzjd").setValue("");
					Ext.getCmp("ckxxjlx").setValue("");
					Ext.getCmp("ckxxmlph").setValue("");
					Ext.getCmp("ckxxmlxz").setValue("");
					Ext.getCmp("ckxxssxq").setValue("");
				}
			},
			rowdblclick:function(g, rowIndex, e){
				selectedSelRes = g.store.getAt(rowIndex);
			}
		}
    });
	
	var zhcx_dialog = new Gnt.ux.ZhcxDialog({
		pzlb : '30017',
		grid:fjqfGrid,
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
	    bodyStyle: 'padding:8px'
	},
	items: [{
	    title: '',
	    region: 'south',
	    border:false,
	    frame:false,
	    height: 200,
	    lableAlign:'right',
	    buttonAlign:'right',
	    cmargins: '0 0 0 0',
	    //layout:'table',
	    items:[{
        	title: "对比结果",//<span style='colore:red'>对比结果<span> 没用
//			columnWidth: 3,
            xtype: 'fieldset',
            autoHeight: true,
            layout:'column',
            defaults:{
            	
            	columnWidth: .2,
            	fieldLabel: '',
            	name: 'fw'
            },
            items: [{
            	id:'dbjg',
            	border:false,
	            frame:false,
            	html: '<a style="color:red;">对比结果:</a>',//boxLabel: '对比结果',
            	//html: '显示历史受理信息',
            	name:'dbjg'
            },{
            	id:'r12',
            	boxLabel: '显示证件照片',
            	xtype:'checkbox',
            	checked:true,
            	name:'fw',
            	inputValue : "1"
            },{
            	id:'r13',
            	boxLabel: '显示常口信息',
            	xtype:'checkbox',
            	checked:true,
            	name:'fw',
            	inputValue : "2"
            }]
		},{
			border:false,
			frame: false,
        	height: 40,
        	title:'HZ2004',
        	//bodyStyle: 'padding:5px',
			layout:'table',
			
	    	buttons: [new Ext.Button({
    			text:'签发',
    			id:'qf',
    			disabled:true,
    			minWidth:60,
    			handler:function(){
    				if(selRes){
    					var shdata=new Array();
						var data = selRes.data;
						shdata.push(data);
    					Gnt.submit(
    							{
        								shdata:Ext.encode(shdata)	
    							}, 
    							"yw/fjgl/fjqf/processZjqfyw", 
    							"正在进行签发，请稍后...", 
    							function(jsonData,params){
    								showInfo("签发成功！");
    								fjqfGrid.store.reload(); 
    							}
    					);
    				}else{
    					alert("请先选中一条有效的数据！");
    					return;
    				}
    			}
    		}),new Ext.Button({
    			text:'全部签发',
    			id:'qbqf',
    			disabled:true,
    			minWidth:60,
    			handler:function(){
    				var shdata=new Array();
    				for(var ii=0;ii<fjqfGrid.store.totalLength;ii++){
    					var record =fjqfGrid.store.getAt(ii);
    					record.set('slzt', 13);
    					var griddata = record.data;
    					shdata.push(griddata);
    				}
    				Gnt.submit(
							{
    								shdata:Ext.encode(shdata)	
							}, 
							"yw/fjgl/fjqf/processZjqfyw", 
							"正在进行签发，请稍后...", 
							function(jsonData,params){
								showInfo("全部签发成功！");
							}
					);
    				
    			}
    		}),new Ext.Button({
    			text:'常口信息',
    			id:'ckxx',
    			disabled:true,
    			minWidth:60,
    			handler:function(){
    				czr={
							ryid:selectRyid,
							rynbid:selectRynbid
					}
					Gnt.toRyxx(czr);
    			}
    		}),new Ext.Button({
    			text:'受理信息',
    			id:'slxx',
    			disabled:true,
    			minWidth:60,
    			handler:function(){
    				var url = basePath + "cx/edzjxx/edzslcx?jumpToEdzslMain=edzslMain&ryid=" +selRes.data.ryid;
    				if(parent && parent.openWorkSpace){
    					parent.openWorkSpace(url,  "受理信息查询", "slxxcx" );
    				}else{
    					window.location.href = url;
    				}
    			}
    		}),new Ext.Button({
    			text:'关闭',
    			minWidth:60,
    			handler:function(){
    				window.parent.closeWorkSpace();
    			}
    		})
			
	    		 
	    	]
			
		},{
			title:'注意：',
			border:false,
			frame: false,
			height:50,
			bodyStyle: 'padding:5px',
			layout:'table',
			layoutConfig: {
	    		columns: 3
	    	 },
	    	items: [{
				border:false,
				frame: false,
    		    items:[new Ext.Button({
        			text:'刷新',
        			minWidth:60,
        			handler:function(){
        				Ext.getCmp("queryId").handler();
        			}
        		})]
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
				html:'<a style="color:red;">提示：一次只能签发80条(可设定)受理信息，审核完毕后，请刷新数据，再进行签发</a>',
				width:700
    		},{
    	    	frame:false,
    			border:false,
    			id:'_READ_CARD_ID',
    			xtype:'panel',
    			html:'',
    			width:10
    		}]
		}]
	  
	},{
	    title: '受理信息查询',
	    collapsible: false,
	    region:'north',
	    border:false,
	    frame:false,
	    height: 100,
	    margins: '5 0 0 0',
	    cmargins: '5 0 0 0',
	    items:[{
			border:false,
			frame: false,
			region:'center',
			layout:'column',
			items:[form30002,{
				border:false,
				frame: false,
	        	region:'weast',
	        	height: 80,
	        	columnWidth:0.2,
	        	layout:'border',
	        	items:[{
	        		border:false,
					frame: false,
	        		bodyStyle: 'padding:5px 5px 5px 5px',
					layout:'table',
					region:'center',
					layoutConfig: {
			        	columns: 8	
			        },
			    	items: [
			    		{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			    		},{
							border:false,
							frame: false,
			    		    items:[new Ext.Button({
			    		    	id:'queryId',
			        			text:'查询',
			        			minWidth:80,
			        			handler:function(){
			        				var data = form30002.getForm().getValues();
									Ext.Msg.wait("正在执行查询，请稍后...");
			        				
			        				var store = fjqfGrid.store;
			        				store.removeAll();
			        				store.baseParams = data;
			        				applyParam(store);
			        				store.load({params:{start:0, limit:user.dwDySet.ywlimit}});
			        				
			        				/**
			        					改变颜色
			        				 */
			        				store.on('load',function(s,records){
			        					if(store.data.length > 0){
				        					curIndex = 0;
				        					fjqfGrid.fireEvent("rowclick",fjqfGrid,curIndex);
				        					fjqfGrid.getSelectionModel().selectRange(curIndex,curIndex);
				        					Ext.getCmp("qf").enable();
				        					Ext.getCmp("qbqf").enable();
				        					Ext.getCmp("ckxx").enable();
				        					Ext.getCmp("slxx").enable();
				        				}
				        			});
			        				Ext.Msg.hide();
			        				
			        			}
			        		})]
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
			        			text:'复杂条件',
			        			minWidth:80,
			        			handler:function(){
			        				zhcx_dialog.show();
			        			}
			        		})]
			    		},{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			    		}
			    		 
			    	]
	        	},{
	        		border:false,
					frame: false,
	        		bodyStyle: 'padding:16px',
					region:'south',
					autoHeight: true,
		            layout:'column',
		            defaults:{
		            	columnWidth: .5,
		            	fieldLabel: '',
		            	name: 'zt'
		            },
			    	items: [{
						border:false,
						frame: false,
		    		    items:[{
			    	    	xtype: 'DkButton',
			    	    	form:form30002,
			    	    	callback: function(){
			    	    		//Ext.getCmp("queryId").handler();
			    	    	}
			    	    }]
		    		},{
		            	id:'c12',
		            	xtype:'checkbox',
		            	boxLabel: '快证信息',
		            	name:'kzxx',
		            	inputValue : "2",  
        				listeners:{
        					'check': function(obj,checked){
        						if(checked){
        						}else{
        						}
        					}
        				}
		            }]
	        	}]
			}]
		}
	]
	  
	  
			  
	    
	},{
	    title: '查询结果',
	    collapsible: false,
	    region:'center',
	    layout:'border',
	    border:false,
	    frame:false,
	    margins: '0 0 0 0',
	    height:400,
	    bodyStyle:'overflow-y:auto;overflow-x:scroll;height:300px',
	    items:[fjqfGrid]
	},{
	    title: '',
	    collapsible: false,
	    region:'east',
	    border:false,
	    frame:false,
	    width:"40%",
	    margins: '0 0 0 0',
	    layout:'column',
	    //height:400,
	    bodyStyle:'overflow-y:auto;height:400px',
	    //autoHeight:true,
	    //labelWidth:55,
	    items:[
			{
				//border:false,
	            //frame:false,
	            region:'center',
	            layout:'column',
				bodyStyle:'width:472px;',
	            items:[
	            	{
	            		region:'east',
    		            columnWidth:.35,
    		            id:'picImage',
    					title: '',
    					bodyStyle:'padding:10px 10px 10px 10px',
    					html: '照片',
    					width:100,
    					height:180,
    					rowspan: 1,
    					colspan:1
	            	},{
	            		region:'center',
	            		layout : 'column',
	            		columnWidth:.65,
    		            title: '受理信息',
	            		xtype: 'fieldset',
						bodyStyle : 'height: 180px;',
	            		defaults : {
							frame:false,
							border:false,
							columnWidth:.5,
							bodyStyle : 'padding:5px 5px 0px 5px'
						},
						items:[{
							layout: 'form',
							columnWidth:.65,
							labelWidth : 40,
							items:[{
								fieldLabel:'姓名',
								name:'xm',
								id:'slxxxm',
								anchor:'100%',
								disabled:true,
								xtype : 'textfield'
							}]
						},{
							layout: 'form',
							columnWidth:.35,
							labelWidth : 40,
							items:[{
								fieldLabel:'姓别',
								name:'xb',
								id:'slxxxb',
								anchor:'100%',
								disabled:true,
								xtype : 'textfield'
							}]
						},{
							layout: 'form',
							columnWidth:.65,
							labelWidth : 40,
							items:[{
								fieldLabel:'出生',
								name:'csrq',
								anchor:'100%',
								id:'slxxcs',
								disabled:true,
								xtype : 'textfield'
							}]
						},{
							layout: 'form',
							columnWidth:.35,
							labelWidth : 40,
							items:[{
								fieldLabel:'民族',
								name:'mz',
								anchor:'100%',
								id:'slxxmz',
								disabled:true,
								xtype: 'textfield'
							}]
						},{
							layout: 'form',
							columnWidth:1.,
							labelWidth : 40,
							items:[{
								fieldLabel:'号码',
								name:'dhhm',
								anchor:'100%',
								id:'slxxhm',
								disabled:true,
								xtype : 'textfield'
							}]
						},{
							layout: 'form',
							columnWidth:1.,
							labelWidth : 60,
							items:[{
								fieldLabel:'申领原因',
								name:'slyy',
								anchor:'100%',
								id:'slxxslyy',
								disabled:true,
								xtype : 'textfield'
							}]
						},{
							layout: 'form',
							columnWidth:1.,
							labelWidth : 40,
							items:[{
								fieldLabel:'领证',
								name:'lz',
								anchor:'100%',
								id:'slxxlz',
								disabled:true,
								xtype : 'textfield'
							}]
						}]
	            	},{

	            		region:'south',
	            		border:false,
    		            frame:false,
    		            layout : 'column',
    		            title: '',
    		            defaults : {
							frame:false,
							border:false,
							columnWidth:.5,
							bodyStyle : 'padding:5px 5px 0px 5px;width:200px'
						},
    		            items:[
    		            	{
								layout: 'form',
								columnWidth:.5,
								labelWidth : 80,
								items:[{
									fieldLabel:'有效期限起',
									name:'yxqx',
									anchor:'100%',
									id:'slxxyxqxq',
									disabled:true,
									xtype : 'textfield'
								}]
							},{
								layout: 'form',
								columnWidth:.5,
								labelWidth : 80,
								items:[{
									fieldLabel:'有效期限至',
									name:'yxqx',
									labelSeparator: '',
									anchor:'100%',
									id:'slxxyxqxz',
									disabled:true,
									xtype : 'textfield'
								}]
							},{
								layout: 'form',
								columnWidth:1.,
								labelWidth : 40,
								items:[{
									labelSeparator: '',
									name:'dz',
									id:'slxxxz',
									anchor:'100%',
									disabled:true,
									xtype : 'textfield'
								}]
							}
    		            ]
	            	}
	            ]
			},{
				border:false,
	            //frame:false,
	            region:'center',
	            layout:'column',
				bodyStyle:'width:472px;',
	            items:[
	            	{
	            		region:'east',
    		            columnWidth:.35,
    		            id:'rkxxpicImage',
    					title: '',
    					bodyStyle:'padding:10px 10px 10px 10px',
    					html: '照片',
    					width:100,
    					height:180,
    					rowspan: 1,
    					colspan:1	
	            	},{
	            		region:'center',
	            		layout : 'column',
	            		columnWidth:.65,
	            		title: '常口信息',
	            		xtype: 'fieldset',
						bodyStyle : 'height: 400px;',
	            		defaults : {
							frame:false,
							border:false,
							labelWidth : 70,
							columnWidth:1.,
							bodyStyle : 'padding:5px 5px 0px 5px'
						},
						items:[{
							layout: 'form',
							items:[{
								fieldLabel:'姓名',
								id:'ckxxxm',
								name:'ckxxxm',
								anchor:'100%',
								disabled:true,
								xtype : 'textfield'
							}]
						},{
							layout: 'form',
							items:[{
								fieldLabel:'姓别',
								id:'ckxxxb',
								name:'ckxxxb',
								anchor:'100%',
								disabled:true,
								xtype : 'textfield'
							}]
						},{
							layout: 'form',
							items:[{
								fieldLabel:'民族',
								id:'ckxxmz',
								name:'ckxxmz',
								anchor:'100%',
								disabled:true,
								xtype: 'textfield'
							}]
						},{
							layout: 'form',
							items:[{
								fieldLabel:'出生日期',
								id:'ckxxcsrq',
								name:'ckxxcsrq',
								anchor:'100%',
								disabled:true,
								xtype : 'textfield'
							}]
						},{
							layout: 'form',
							items:[{
								fieldLabel:'身份号码',
								id:'ckxxsfhm',
								name:'ckxxsfhm',
								anchor:'100%',
								disabled:true,
								xtype : 'textfield'
							}]
						},{
							layout: 'form',
							items:[{
								fieldLabel:'派出所',
								id:'ckxxpcs',
								name:'ckxxpcs',
								anchor:'100%',
								disabled:true,
								xtype : 'textfield'
							}]
						},{
							layout: 'form',
							items:[{
								fieldLabel:'身份证地址',
								id:'ckxxsfzdz',
								name:'ckxxsfzdz',
								anchor:'100%',
								disabled:true,
								xtype : 'textfield'
							}]
						},{
							layout: 'form',
							items:[{
								fieldLabel:'乡镇街道',
								id:'ckxxxzjd',
								name:'ckxxxzjd',
								anchor:'100%',
								disabled:true,
								xtype : 'textfield'
							}]
						},{
							layout: 'form',
							items:[{
								fieldLabel:'街路巷',
								id:'ckxxjlx',
								name:'ckxxjlx',
								anchor:'100%',
								disabled:true,
								xtype : 'textfield'
							}]
						},{
							layout: 'form',
							items:[{
								fieldLabel:'门楼牌号',
								id:'ckxxmlph',
								name:'ckxxmlph',
								anchor:'100%',
								disabled:true,
								xtype : 'textfield'
							}]
						},{
							layout: 'form',
							items:[{
								fieldLabel:'门楼详址',
								id:'ckxxmlxz',
								name:'ckxxmlxz',
								anchor:'100%',
								disabled:true,
								xtype : 'textfield'
							}]
						},{
							layout: 'form',
							items:[{
								fieldLabel:'省市县区',
								id:'ckxxssxq',
								name:'ckxxssxq',
								anchor:'100%',
								disabled:true,
								xtype : 'textfield'
							}]
						}]
	            	}
	            ]
			}
           ]
	 
	}]
});
function applyParam(store){
	var kzxx = '';
	if(Ext.getCmp('c12').getValue()){
		kzxx = '1';
	}
	var yzw = '';
	Ext.apply(store.baseParams, {kzxx:kzxx});
	
}
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