var slzt="90";
var selectRynbid=null;
var selectRyid=null;

Ext.onReady(function(){
	Ext.QuickTips.init();
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_EDZSLZT','CS_EDZSLYY','CS_EDZLZFS','CS_EDZZZLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	var form30002 = new Gnt.ux.SjpzForm({
		pzlb: '30002',//30037
		title:'',
		region:'north',
		cols:2,
		formType:'query'
	});	
	
	var shxxStore = new Ext.data.JsonStore({
		proxy: new Ext.data.HttpProxy({
			url : 'yw/bbdy/clddy/queryslxx',
			method: "POST",
			disableCaching: true
	    }),
		root : 'list',
		id : 'nbslid',
		totalProperty : 'totalCount',
		fields : [
		           "nbslid",
					"slh",
					"ryid",
					"rynbid",
					"zpid",
					"qfjg",
					"yxqxqsrq",
					"yxqxjzrq",
					"zz",
					"slyy",
					"zzlx",
					"lqfs",
					"sflx",
					"sfje",
					"sjblsh",
					"slzt",
					"zjywid",
					"cxbz",
					"cxsj",
					"cxrid",
					"cxzjywid",
					"tbbz",
					"gmsfhm",
					"nbsfzid",
					"xm",
					"xb",
					"mz",
					"csrq",
					"csdssxq",
					"mlpnbid",
					"ssxq",
					"jlx",
					"mlph",
					"mlxz",
					"pcs",
					"zrq",
					"xzjd",
					"jcwh",
					"pxh",
					"ywbz",
					"czyid",
					"czsj",
					"dwdm",
					"sjrxm",
					"sjrlxdh",
					"sjryb",
					"sjrtxdz",
					"zzxxcwlb",
					"cwms",
					"jydw",
					"jyrxm",
					"jyrq",
					"cldw",
					"clqk",
					"clrq",
					"zlhkzt",
					"hksj",
					"bwbha",
					"bwbhb",
					"shrq",
					"fjpch",
					"stjssj",
					"bwbhd"
		          ],
		listeners : {			
			beforeload : function(store, options) {	
				
			},
			load : function() {
			
			},
			loadexception : function(mystore, options, response, error) {
				if (error) {
					var json = Ext.decode(response.responseText);
					if (json.messages)
						Ext.Msg.alert("提示", json.messages[0]);
				} else {
					Ext.Msg.alert("提示", response.responseText);
				}
			}
		}
	});
	
	
	
	
	var slModel = new Ext.grid.ColumnModel([
	         {
	            header: "姓名",
	            dataIndex: "xm",
	            sortable: true,
	            width: 120
	          },{
	            header: "性别",
	            dataIndex: "xb",
	            sortable: true,
	            width: 120,
	            renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
					return Gnt.ux.Dict.getDictLable('CS_XB', value);
				}
	          },{
		         header: "受理状态",
		         dataIndex: "slzt",
		         sortable: true,
		         width: 120,
		         renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
						return Gnt.ux.Dict.getDictLable('CS_EDZSLZT', value);
					}
		      },{
			      header: "受理时间",
			      dataIndex: "czsj",
			      sortable: true,
			      width: 120
			   },{
				  header: "派出所",
				  dataIndex: "pcs",
				  sortable: true,
				  width: 120,
				  renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
						return Gnt.ux.Dict.getDictLable('DWXXB', value);
					}
				 },{
					  header: "名族",
					  dataIndex: "mz",
					  sortable: true,
					  width: 120,
					  renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
							return Gnt.ux.Dict.getDictLable('CS_MZ', value);
						}
				},{
					  header: "出生日期",
					  dataIndex: "csrq",
					  sortable: true,
					  width: 120
				},{
					  header: "公民身份号码",
					  dataIndex: "gmsfhm",
					  sortable: true,
					  width: 120
				},{
					  header: "省市县区",
					  dataIndex: "ssxq",
					  sortable: true,
					  width: 120,
					  renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
							return Gnt.ux.Dict.getDictLable('XZQHB', value);
						}
				},{
					  header: "居委会",
					  dataIndex: "jcwh",
					  sortable: true,
					  width: 120,
					  renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
							return Gnt.ux.Dict.getDictLable('JWHXXB', value);
						}
				},{
					  header: "受理号",
					  dataIndex: "slh",
					  sortable: true,
					  width: 120
				},{
					  header: "受理原因",
					  dataIndex: "slyy",
					  sortable: true,
					  width: 120,
					  renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
							return Gnt.ux.Dict.getDictLable('CS_EDZSLYY', value);
						}
				},{
					  header: "有效期限起始日期",
					  dataIndex: "yxqxqsrq",
					  sortable: true,
					  width: 120
				},{
					  header: "制证类型",
					  dataIndex: "zzlx",
					  sortable: true,
					  width: 120,
					  renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
							return Gnt.ux.Dict.getDictLable('CS_EDZZZLX', value);
						}
				},{
					  header: "领取方式",
					  dataIndex: "lqfs",
					  sortable: true,
					  width: 120,
					  renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
							return Gnt.ux.Dict.getDictLable('CS_EDZLZFS', value);
						}
				},{
					  header: "受理人",
					  dataIndex: "czyid",
					  sortable: true,
					  width: 120,
					  renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
							return Gnt.ux.Dict.getDictLable('YHXXB', value);
						}
				},{
					  header: "收件人姓名",
					  dataIndex: "sjrxm",
					  sortable: true,
					  width: 120
				},{
					  header: "收件人联系电话",
					  dataIndex: "sjrlxdh",
					  sortable: true,
					  width: 120
				},{
					  header: "收件人邮编",
					  dataIndex: "sjryb",
					  sortable: true,
					  width: 120
				},{
					  header: "收件人通讯地址",
					  dataIndex: "sjrtxdz",
					  sortable: true,
					  width: 120
				}
	     ]);
	
	
	
	
	var slxxGrid = new Ext.grid.GridPanel({
        store: shxxStore,
        region: 'center',
        view:new Ext.grid.GridView({
				enableRowBody:true
		}),
		stripeRows: true,
        cm: slModel,
        //sm:csm,
		loadMask: {msg:'正在加载数据，请稍侯……'},
		//bodyStyle:'width:50%',
        border:true,
        frame:false,
        anchor:'100% 100%',
	    margins: '0 0 0 0',
		cmargins: '0 0 0 0',        
		iconCls:'icon-grid',
        title:'',
        listeners:{
        	rowclick:function(g, rowIndex, e){
        		selectedSelRes = g.store.getAt(rowIndex);
        		selectRynbid=selectedSelRes.data.rynbid;
        		selectRyid=selectedSelRes.data.ryid;
        		var xm=selectedSelRes.data.xm;
        		var xb=Gnt.ux.Dict.getDictLable('CS_XB', selectedSelRes.data.xb);
        		var mz=Gnt.ux.Dict.getDictLable('CS_MZ', selectedSelRes.data.mz);
        		var csrq=selectedSelRes.data.csrq;
        		var qsrq=selectedSelRes.data.yxqxqsrq;
        		var jzrq=selectedSelRes.data.yxqxjzrq;
        		var sfhm=selectedSelRes.data.gmsfhm;
        		var slh=selectedSelRes.data.slh;
        		var txdz=selectedSelRes.data.sjrtxdz;
        		
        			Ext.getCmp("c11").setValue(xm);
            		Ext.getCmp("c2").setValue(xb);
            		Ext.getCmp("c3").setValue(mz);
            		Ext.getCmp("c4").setValue(csrq);
            		Ext.getCmp("c5").setValue(qsrq);
            		Ext.getCmp("c6").setValue(jzrq);
            		Ext.getCmp("c7").setValue(sfhm);
            		Ext.getCmp("c8").setValue(slh);
            		Ext.getCmp("c9").setValue(txdz);
            		var zpid = selectedSelRes.data.zpid;
        			//alert(Ext.getCmp("showZp").getValue());//showZp
    				if(zpid &&　zpid != 0&&Ext.getCmp("showZp").getValue()){
    					Ext.getCmp('picImage').body.update("<IMG SRC='yw/common/img/render/" + zpid + "' width='100%' height='100%' />");
    				}else{
    					Ext.getCmp('picImage').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
    				}
        		
        	},
			rowdblclick:function(g, rowIndex, e){
				selectedSelRes = g.store.getAt(rowIndex);
			}
		}/*,
        bbar: new Ext.PagingToolbar({
				pageSize: 20,
				store: shxxStore,
				displayInfo: true
		})*/
    });
	
	var zhcx_dialog = new Gnt.ux.ZhcxDialog({
		pzlb : '30017',
		grid:slxxGrid,
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
	    title: '',
	    region: 'south',
	    border:false,
	    frame:false,
	    height: 40,
	    cmargins: '0 0 0 0',
	    layout:'table',
	    /*items:[{
	    	border:false,
	    	frame:false,*/
	    	buttonAlign:'right',
	  	    buttons:[
	  	    	new Ext.Button({
	                  id:'cld',
	                  text:'催领单',
	                  minWidth:80,
	                 handler:function(){
	                	 if(selectedSelRes){
	                		 var arrayTemp=[];
		                	 var o={};
							 o.directTable="cld";
							 o.nbslid =selectedSelRes.data.nbslid;
							 arrayTemp.push(o);
							 printfunction(0,arrayTemp);
							 selectedSelRes.set('slzt', '92'); 
	                	 }else{
	                		 alert("请先选中要打印的条目，再点击打印按钮!");
	                	 }
	                	
	                  }
	            }),new Ext.Button({
	                  id:'cpdy',
	                  text:'成批打印',
	                  minWidth:80,
	                 handler:function(){
	                	 var arrayTemp=[];
	                	 shxxStore.each(function(record) {
	                		 var o={};
							 o.directTable="cld";
							 o.nbslid =record.get('nbslid');
							 arrayTemp.push(o);
	                	 }); 
	                	 printfunction(0,arrayTemp);
	                	 shxxStore.each(function(record) {
							 record.set('slzt', '92');
	                	 });
	                 }
	            }),new Ext.Button({
	                  id:'ckxx',
	                  text:'常口信息',
	                  minWidth:80,
	                 handler:function(){
	                	czr={
							ryid:selectRyid
							//rynbid:selectRynbid
							//hhnbid:selectHhnbid
							}
							Gnt.toRyxx(czr);
	                  }
	            }),new Ext.Button({
	                  id:'slxx',
	                  text:'受理信息',
	                  minWidth:80,
	                 handler:function(){
	                	 var url = basePath + "cx/edzjxx/edzslcx?jumpToThrycx="+"1"+"&rynbid="+selectRynbid;			      
	        				if(parent && parent.openWorkSpace){
	        					parent.openWorkSpace(url,  "证件受理信息查询", "edzslcx");
	        				}else{
	        					window.location.href = url;
	        				}
	                  }
	            }),
	            new Ext.Button({
        			text:'关闭',
        			minWidth:60,
        			handler:function(){
        				window.parent.closeWorkSpace();
        			}
        		})
	  	    ],
	  	    items:[{
    	    	frame:false,
				border:false,
				id:'_READ_CARD_ID',
				xtype:'panel',
				html:'',
				width:10
				
			}]
	   // }]
	  
	},{
	    title: '受理信息查询',
	    collapsible: false,
	    region:'north',
	    border:false,
	    frame:false,
	    layout:'column',
	    height: 120,
	    //margins: '5 0 0 0',
	    //cmargins: '5 0 0 0',
	    items:[{
	    	border:false,
	    	frame:false,
	    	columnWidth: .85,
	    	region:'center',
	    	items:[form30002]
	    },{
	    	border:false,
	    	frame:false,
	    	region:'east',
	    	layout:'column',
		    labelAlign :'right',
		    columnWidth: .15,
	    	items:[{
	    		layout:'column',
    			frame:false,
    			border:false,
        		defaults:{
        			border:false,
        			frame:false,
        			bodyStyle:'padding:5 5 5 5',
        			columnWidth:0.5
        		},
        		items:[{columnWidth:0.5,
    	           	layout: 'form',
    	           	items:[new Ext.Button({
    	                id:'cx',
    	                text:'查询',
    	                minWidth:80,
    	               handler:function(){
    	            	   var data = form30002.getForm().getValues();
//	       					if(Gnt.util.isEmpty(data)){
//	       						showInfo("至少输入一个查询条件！");
//	       						return;
//	       					}
	    					Ext.Msg.wait("正在执行查询，请稍后...");
	    					var store = slxxGrid.store;
	    					store.baseParams = data;
	    					store.load({params:{start:0, limit:20}});
    	      				store.on("load",function(store){
    	      					if(store.data.length>0){
    	      						curIndex = 0;
    	      						slxxGrid.fireEvent("rowclick",slxxGrid,curIndex);
    	      						slxxGrid.getSelectionModel().selectRange(curIndex,curIndex);
    	      						Ext.getCmp("cld").setDisabled(false);
    	      						Ext.getCmp("cpdy").setDisabled(false);
    	      						Ext.getCmp("slxx").setDisabled(false);
    	      						Ext.getCmp("ckxx").setDisabled(false);
    	      					}else{
    	      						Ext.getCmp("cld").setDisabled(true);
    	      						Ext.getCmp("cpdy").setDisabled(true);
    	      						Ext.getCmp("slxx").setDisabled(true);
    	      						Ext.getCmp("ckxx").setDisabled(true);
    	      						Ext.getCmp("c11").setValue("");
    	      	            		Ext.getCmp("c2").setValue("");
    	      	            		Ext.getCmp("c3").setValue("");
    	      	            		Ext.getCmp("c4").setValue("");
    	      	            		Ext.getCmp("c5").setValue("");
    	      	            		Ext.getCmp("c6").setValue("");
    	      	            		Ext.getCmp("c7").setValue("");
    	      	            		Ext.getCmp("c8").setValue("");
    	      	            		Ext.getCmp("c9").setValue("");
    	      					}
    	      				});
    	      				Ext.Msg.hide();
    	                 }
    	          })]},{columnWidth:0.5,
    	           	layout: 'form',
    	           	items:[new Ext.Button({
    	                id:'zhcx',
    	                text:'组合查询',
    	                minWidth:60,
    	               handler:function(){
    	            	   zhcx_dialog.show();	
    	                }
    	          })]},{columnWidth:0.5,
    	           	layout: 'form',
    	           	items:[{
		    	    	xtype: 'DkButton',
		    	    	form:form30002,
		    	    	minWidth:80,
		    	    	callback: function(){
		    	    		//Ext.getCmp("queryId").handler();
		    	    	}
		    	    }]},{columnWidth:0.5,
    	           	items:[{		 
    	              	//id:'c1',
    	            	xtype:'checkbox',
    	            	labelSeparator: '',
    	            	boxLabel: '已打印',
    	            	checked:false,
    	            	name:'qr',
    	        		listeners:{
    	        			'check': function(obj,checked){
    	        				if(checked){
    	        					slzt="92";
    	        				}else{
    	        					slzt="90";
    	        				}
    	        			}
    	        		}
    	            }]}]
	    	}]
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
	    items:[slxxGrid]
	},{
	    title: '',
	    collapsible: false,
	    region:'east',
	    border:false,
	    frame:false,
	    width:"35%",
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
	            items:[
	            	{
	            		region:'center',
	            		layout : 'column',
	            		columnWidth:.65,
	            		defaults : {
							frame:false,
							border:false,
							columnWidth:.5,
							bodyStyle : 'padding:5px 5px 0px 5px'
						},
						items:[{
							layout: 'form',
							columnWidth:1.,
							labelWidth : 60,
							items:[{
								fieldLabel:'姓 名',
						    	id:'c11',
						    	readOnly: true,
						    	disabled: true,
						    	anchor:'100%',
						    	xtype: 'textfield'
							}]
						},{
							layout: 'form',
							columnWidth:1.,
							labelWidth : 60,
							items:[{
								fieldLabel:'姓别',
								name:'xb',
								id:'c2',
								readOnly: true,
						    	disabled: true,
						    	anchor:'100%',
								xtype : 'textfield'
							}]
						},{
							layout: 'form',
							columnWidth:1.,
							labelWidth : 60,
							items:[{
								fieldLabel:'民 族',
						    	id:'c3',
						    	readOnly: true,
						    	border:false,
						    	disabled: true,
						    	anchor:'100%',
						    	xtype: 'textfield'
							}]
						},{
							layout: 'form',
							columnWidth:1.,
							labelWidth : 60,
							items:[{
								fieldLabel:'出生日期',
						    	labelWidth:80,
						    	id:'c4',
						    	readOnly: true,
						    	anchor:'100%',
						    	border:false,
						    	disabled: true,
						    	xtype: 'textfield'
							}]
						},{
							layout: 'form',
							columnWidth:1.,
							labelWidth : 60,
							items:[{
								fieldLabel:'起始日期',
						    	id:'c5',
						    	readOnly: true,
						    	border:false,
						    	anchor:'100%',
						    	disabled: true,
						    	xtype: 'textfield'
							}]
						},{
							layout: 'form',
							columnWidth:1.,
							labelWidth : 60,
							items:[{
								fieldLabel:'截止日期',
						    	id:'c6',
						    	readOnly: true,
						    	border:false,
						    	disabled: true,
						    	anchor:'100%',
						    	xtype: 'textfield'
							}]
						},{
							layout: 'form',
							columnWidth:1.,
							labelWidth : 60,
							items:[{
								fieldLabel:'身份号码',
						    	id:'c7',
						    	readOnly: true,
						    	border:false,
						    	disabled: true,
						    	anchor:'100%',
						    	xtype: 'textfield'
							}]
						},{
							layout: 'form',
							columnWidth:1.,
							labelWidth : 60,
							items:[{
								fieldLabel:'受理号',
						    	id:'c8',
						    	readOnly: true,
						    	border:false,
						    	disabled: true,
						    	anchor:'100%',
						    	xtype: 'textfield'
							}]
						}]
	            	},{
	            		region:'west',
    		            columnWidth:.35,
    		            items:[{
    		            	id:'picImage',
	    					title: '',
	    					bodyStyle:'padding:10px 10px 10px 10px',
	    					html: '照片',
	    					width:140,
	    					height:150,
	    					rowspan: 1,
	    					colspan:1
    		            },{		 
        	            	xtype:'checkbox',
        	            	labelSeparator: '',
        	            	boxLabel: '显示证件照片',
        	            	id:'showZp',
        	            	checked:false,
        	        		listeners:{
        	        			'check': function(obj,checked){
        	        				if(checked){
        	        				}else{
        	        				}
        	        			}
        	        		}
        	            }]
	            	}
	            ]
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
					bodyStyle : 'padding:5px 5px 0px 5px'
				},
	            items:[
	            	{
						layout: 'form',
						columnWidth:1.,
						labelWidth : 40,
						items:[{
//							labelSeparator: '',
//							name:'dz',
//							id:'slxxxz',
//							anchor:'100%',
//							disabled:true,
//							xtype : 'textfield'
								

							id:'c9',
					    	//readOnly: true,
					    	border:false,
					    	labelSeparator: '',
					    	anchor:'100%',
					    	disabled: true,
					    	xtype: 'textfield'
							
						}]
					}
	            ]
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
		
	 
	
	// shxxStore.load({params:{slzt:"90",start:0, limit:50}});
		Ext.getCmp("cld").setDisabled(true);
		Ext.getCmp("cpdy").setDisabled(true);
		Ext.getCmp("slxx").setDisabled(true);
		Ext.getCmp("ckxx").setDisabled(true);
		
		
	
});