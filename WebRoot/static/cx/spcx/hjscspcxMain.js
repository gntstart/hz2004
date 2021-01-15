
var cxCount = 1;
var rynbid = null;

var selectRynbid = null;
var selectSpywid = null;
var selectHjywid = null;

Ext.onReady(function(){
	Ext.QuickTips.init();
	
	var hzywid = getQueryParam("hzywid");
	var pch = getQueryParam("pch");
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10019,20031,20024,10031",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	
	/**
		点击下一步后显示的内容
		暂时用户成员信息代替
	 */
	var hcyGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		pzlb: '10019',
    	region:'south',
		height:150,
    	title: '',
		url: 'yw/common/queryRyxx.json',
    	showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){}
		}
	});
	
	var form20031 = new Gnt.ux.SjpzForm({
		pzlb: '20031',
		url: 'yw/queryRyxx',
		title:'',
		cols:2,
		formType:'query'
	});
	var p1_1 = new Gnt.ux.ZdycxPanel({
		pzlb:form20031.pzlb,
		region:'center',
		layout:'border',
		border:false,
		frame: false,
		height:600
	});
	var p1 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[
			{
				id:"nextId",
				border:false,
				frame: false,
				region:'center',
				visible:false,
	//        	height: 300,
				items:[hcyGrid]
			},
			{
				border:false,
				frame: false,
				region:'center',
				items:[{
					id:"centerId",
	    	        border:false,
	    	        frame:false,
					region:'south',
//					hidden:true,
					items:[{
						id: "panelHtmlId" ,
						html: '<div class="text" style="text-align:center;">户籍删除审批</div>',
						height: 80,
						region:'north',
						bodyStyle:'padding:15px'
					},
					form20031
				]
				},
				{
					id:"p1_1",
	    	        border:false,
	    	        frame:false,
					region:'south',
					hidden:true,
					items:[p1_1]
				}]
			},
//			{
//				id:"centerId",
//				border:false,
//				frame: false,
//				region:'center',
//				layout:'border',
////	        	height: 300,
//				items:[{
//						id: "panelHtmlId" ,
//						html: '<div class="text" style="text-align:center;">户籍删除审批</div>',
//						height: 80,
//						region:'north',
//						bodyStyle:'padding:15px'
//					},
//					form20031
//				]
//			},
			{
				id:"southId",
	        	region:'south',
	        	height:50,
	           border:false,
	           frame:false,
	           bodyStyle:'padding:10px',
         	   layout:'table',
        	   layoutConfig: {
        			columns: 14
        	   },
	            items:[
					{
						border:false,
						frame: false,
						width:10
		    		},{
						border:false,
						frame: false,
		    		    items:[new Ext.Button({
		    		    	id:'zhId',
		        			text:'组合条件',
		        			minWidth:80,
		        			handler:function(){
		        				zhcx_dialog.show();
		        			}
		        		})]
		    		},{
						border:false,
						frame: false,
						width:10
		    		},{
						border:false,
						frame: false,
		    		    items:[{
			    	    	xtype: 'DkButton',
			    	    	form:form20031,
			    	    	callback: function(){
			    	    		//Ext.getCmp("queryId").handler();
			    	    	}
			    	    }]
		    		},/*{
						border:false,
						frame: false,
						width:10
		    		},{
						border:false,
						frame: false,
		    		    items:[{
		    		    	id:'c11',
			        		xtype:'checkbox',
			        		boxLabel : "显示记录总数",  
			        		name : "jlzs",  
			        		inputValue : "1"  
			        	}]
		    		},*/{
						border:false,
						frame: false,
						width:10
		    		},{
						border:false,
						frame: false,
		    		    items:[new Ext.Button({
		    		    	id:'preId',
		        			text:'上一步',
		        			disabled:true,
		        			minWidth:60,
		        			handler:function(){
		        				Ext.getCmp('centerId').show();
		        				Ext.getCmp('p1_1').doLayout();
		        				Ext.getCmp('p1_1').setVisible(false);
		        				Ext.getCmp('preId').setDisabled(true);
		        				Ext.getCmp('nexId').setDisabled(false);
		        			}
		        		})]
		    		},{
						border:false,
						frame: false,
						width:10
		    		},{
						border:false,
						frame: false,
		    		    items:[new Ext.Button({
		    		    	id:'nexId',
		        			text:'下一步',
		        			minWidth:60,
		        			handler:function(){
		        				p1_1.qybz = true;
		        				Ext.getCmp('centerId').hide();
		        				Ext.getCmp('p1_1').setVisible(true);
		        				Ext.getCmp('p1_1').doLayout();
		        				Ext.getCmp('preId').setDisabled(false);
		        				Ext.getCmp('nexId').setDisabled(true);
		        			}
		        		})]
		    		},{
						border:false,
						frame: false,
						width:10
		    		},{
						border:false,
						frame: false,
		    		    items:[new Ext.Button({
		    		    	id:'queryId',
		        			text:'查询',
		        			minWidth:60,
		        			handler:function(){
								//记录日志的操作码
		        				var data = form20031.getForm().getValues();
								if(Gnt.util.isEmpty(data)){
									showInfo("至少输入一个查询条件！");
									return;
								}
								data.log_code = "F5023";
								var ary = p1_1.xszdGrid.store.data.items;
								var pxary = p1_1.pxzdGrid.store.data.items;//length 长度;
								if(p1_1.qybz){
									hjscspGrid.reconfigure(
											hjscspGrid.store,new Ext.grid.ColumnModel(Gnt.ZdyColumn(ary)));
									hjscspGrid.pxary = pxary;
								};
								hjscspGrid.zdyValueKey = Gnt.zdyValueKey(ary);
								var header=[];
								var shuxing = [];
								var zdyValueKey = Gnt.zdyValueKey(ary);
								hjscspGrid.type = 'hjscspcx';
		        				var store = hjscspGrid.store;
		        				store.removeAll();
		        				store.baseParams = data;
		        				var config=hjscspGrid.colModel.config;
								Ext.each(config,function(r){
									header.push(r.header);
									shuxing.push(r.dataIndex);
								});
								store.baseParams.header = encodeURI(header);
								store.baseParams.shuxing = encodeURI(shuxing);
								store.baseParams.zdyValueKey = Ext.encode(zdyValueKey);
								store.baseParams.daochuFlag = 'hjscspcx';
		        				applyParam(store);
		        				Ext.Msg.wait("正在执行查询，请稍后...");
		        				store.load({params:{start:0, limit:20}});
		        				Ext.each(pxary, function(pxObj){
		        					store.sort(pxObj.data.fieldname, 'ASC');
		    					});
		        				store.on('load',function(s,records){
		        					Ext.Msg.hide();
		        					if(store.getCount() > 0){
		        						Ext.getCmp('card').getLayout().setActiveItem(1);
		        						hjscspGrid.fireEvent("rowclick",hjscspGrid,0);
		        						hjscspGrid.getSelectionModel().selectRange(0,0);
			        					if(hzywid != null){
			        						hjscspGrid.fireEvent("rowdblclick",hjblspGrid,0);
			        					}
		        					}else{
		        						showInfo("未查到有关信息！");
		        					}
		        				});
		        			}
		        		})]
		    		},{
						border:false,
						frame: false,
						width:10
		    		},{
						border:false,
						frame: false,
		    		    items:[new Ext.Button({
		        			text:'关闭',
		        			minWidth:60,
		        			handler:function(){
		        				window.parent.closeWorkSpace();
		        			}
		        		})]
		    		},{
		        		frame:false,
						border:false,
						xtype:'panel',
						html:'',
						height:40
		        	},{
		    	    	frame:false,
						border:false,
						id:'_READ_CARD_ID',
						xtype:'panel',
						html:'',
						width:10
					}
		    		
		    	]
	        }
		]
	});
	
	var hjscspGrid = new Gnt.ux.SjpzGrid({
		pkname: 'spywid',
		pzlb: '20031',
		url:'cx/spcx/hjscspcx/getHjscsp.json',
		region:'center',
		height:400,
		title: '',
		dcFlag:true,
		showPaging:true,
		callCellmeta:function(r){
			if(r.data.ryzt=='0'){
				return '#000000';
			}
			else if(r.data.ryzt=='1'){
				return '#C41A11';
			}
			else {
				return '#20BBAF';
			}
		},
		listeners:{
			rowclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
//				selectRynbid = selRes.data.rynbid;
//				selectSpywid = selRes.data.spywid;
//				selectHjywid = selRes.data.hjywid;
			},
			rowdblclick:function(g, rowIndex, e){
				Ext.getCmp('card').getLayout().setActiveItem(2);
				p3.doLayout();
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				
				selectRynbid = selRes.data.rynbid;
				selectSpywid = selRes.data.spywid;
				selectHjywid = selRes.data.hjywid;
				
//				Ext.getCmp('zfId').enable();
//				if((selRes.data.lsbz && selRes.data.lsbz == 1) || (selRes.data.spjg && selRes.data.spjg == 2)){
//					Ext.getCmp('zfId').disable();
//				}
				
				var pkvalue = selRes.data[hjscspForm.bindStore.pkname];
				var re = hjscspForm.bindStore.getById(pkvalue);
				if(re){
					hjscspForm.getForm().loadRecord(re);
				}
				
			}
		}
	});
	
	var zhcx_dialog = new Gnt.ux.ZhcxDialog({
		pzlb : form20031.pzlb,
		grid:hjscspGrid,
		callback: function(where){
			var ary = p1_1.xszdGrid.store.data.items;
			var pxary = p1_1.pxzdGrid.store.data.items;//length 长度;
			if(p1_1.qybz){
				hjscspGrid.reconfigure(
						hjscspGrid.store,new Ext.grid.ColumnModel(Gnt.ZdyColumn(ary)));
				hjscspGrid.pxary = pxary;
			};
			hjscspGrid.zdyValueKey = Gnt.zdyValueKey(ary);
			var grid = this.grid;
			var store = grid.store;
			var length = 0;
			var data = {"where": where};
			store.removeAll();
			data.pzlb = grid.store.pzlb;
			data.config_key = this.config_key;
			
			//add by zjm 20201223 复杂查询传入列表头 属性 自定义字段，用于导出功能使用
			var header=[];
			var shuxing = [];
			var zdyValueKey = Gnt.zdyValueKey(ary);
			grid.type = 'hjscspcx';
			Ext.each(ary,function(r){
				header.push(r.data.displayname);
				shuxing.push(r.data.fieldname);
			});
			data.header = encodeURI(header);
			data.shuxing = encodeURI(shuxing);
			data.zdyValueKey = Ext.encode(zdyValueKey);
			data.daochuFlag = 'hjscspcx';
			
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
					Ext.getCmp('card').getLayout().setActiveItem(1);
				}else{
					showInfo("没有找到符合条件的数据！");
				}
				Ext.Msg.hide();
			},grid); 			        						
		}
	});		
	var p2 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[
			hjscspGrid,{
				bodyStyle:'padding:10px',
				border:false,
				frame: false,
	        	region:'south',
	        	height: 40,
				layout:'table',
	        	layoutConfig: {
	        		columns: 30
	        	},
		    	items: [
					{
						border:false,
						frame: false,
						width:10
		    		},{
						border:false,
						frame: false,
		    		    items:[new Ext.Button({
		    		    	id:'lcId',
		        			text:'审批流程',
		        			minWidth:80,
		        			handler:function(){
		        				
		        				var store = hjscsplcGrid.store;
		        				
		        				store.removeAll();
		        				
		        				store.load({params:{start:0, limit:20, spywid:selectSpywid}});
		        				
		        				Ext.getCmp('card').getLayout().setActiveItem(3);
		        				
		        			}
		        		})]
		    		},{
						border:false,
						frame: false,
						width:10
		    		},{
						border:false,
						frame: false,
		    		    items:[new Ext.Button({
		        			text:'关闭',
		        			minWidth:60,
		        			handler:function(){
		        				window.parent.closeWorkSpace();
		        			}
		        		})]
		    		},{
						border:false,
						frame: false,
						width:10
		    		},{
						border:false,
						frame: false,
		    		    items:[new Ext.Button({
		        			text:'返回',
		        			minWidth:60,
		        			handler:function(){
		        				Ext.getCmp('card').getLayout().setActiveItem(0);
		        			}
		        		})]
		    		}
		    		 
		    	]
				
			}
		]
	});
	
	var hjscspForm = new Gnt.ux.SjpzForm({
		closable: false,
		/*region:'north',
		height:500,*/
		pzlb: '20031',
		labelWidth : 160,
    	height: 380,
		cols:2,
//		bindStore:hjscspGrid.store,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:hjscspGrid,
		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
		changeDictCust:function(cmb,res){
			
			return;
		},
		title: ''
	});
	
	var p3 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[
			{
				id:"p3northId",
				border:false,
				frame: false,
				region:'north',
	        	height: 480,
				items:[hjscspForm]
			},{
				id:"p3centerId",
				border:false,
				frame: false,
				region:'center',
	        	height: 120
			}
		],
		buttons:[
			{
		    	id:'qyzdyId',
    			text:'审批流程',
    			minWidth:60,
    			handler:function(){
    				var store = hjscsplcGrid.store;
    				store.removeAll();
    				store.load({params:{start:0, limit:20, spywid:selectSpywid}});
    				
    				Ext.getCmp('card').getLayout().setActiveItem(3);
    			}
			},/*{
		    	id:'zfId',
    			text:'作废',
    			minWidth:60,
    			handler:function(){
    				if(window.confirm("您确定作废此笔审批信息吗?")){
    					var subdata = {
    						spywid: selectSpywid
    					};
    						
    					Gnt.submit(
    						subdata, 
    						"yw/spgl/hjsp/processHjscspzfyw", 
    						"正在处理死亡注销信息，请稍后...", 
    						function(jsonData,params){
    							showInfo("作废成功！",function(){
    								
    							});
    						}
    					);
    						
    				}
    			}
			},*/{
    			text:'关闭',
    			minWidth:60,
    			handler:function(){
    				if(hzywid != null){
	        			window.close();
	        		}else{
	        			window.parent.closeWorkSpace();
	        		}
    			}
    		},{
    			text:'返回',
    			minWidth:60,
	        	disabled:hzywid != null?true:false,
    			handler:function(){
    				Ext.getCmp('card').getLayout().setActiveItem(1);
//    				v.doLayout();
    			}
    		}
		]
	});
	

	var hjscsplcGrid = new Gnt.ux.SjpzGrid({
		pkname: 'splsid',
		pzlb: '20024',
		url:'cx/spcx/hjscspcx/getHjscsplc.json',
//		region:'north',
		height:400,
		title: '',
		showPaging:true,
		listeners:{
			rowclick:function(g, rowIndex, e){
				
			},
			rowdblclick:function(g, rowIndex, e){
				
			}
		}
	});
	
	var p4 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[
			{
				id:"p4CenterId",
				border:false,
				frame: false,
				region:'center',
				width:'100%',
	        	height: 400,
				items:[hjscsplcGrid]
			},{
				id:"p4SouthId",
	        	region:'south',
	        	height:120,
	            border:false,
	            frame:false,
	            items:[{
	            	id:'clId',
	            	title: '审批材料',
	            	columnWidth: 3,
	            	xtype: 'fieldset',
	            	autoHeight: true,
	            	layout:'column'/*,
	            	defaults:{
	            		xtype:'radio',
	            		columnWidth: .3,
	            		fieldLabel: '',
	            		name: 'fw'
	            	},
	            	items: [{
	            		id:'r11',
	            		boxLabel: '查询所属派出所的人口',
	            		name:'fw',
	            		inputValue : "1",
	            		checked:true
	            	}]*/
	            }]
				
			}
		],
		buttons:[
			{
    			text:'关闭',
    			minWidth:60,
    			handler:function(){
    				if(hzywid != null){
	        			window.close();
	        		}else{
	        			window.parent.closeWorkSpace();
	        		}
    			}
    		},{
    			text:'返回',
    			minWidth:60,
	        	disabled:hzywid != null?true:false,
    			handler:function(){
    				Ext.getCmp('card').getLayout().setActiveItem(1);
//    				v.doLayout();
    			}
    		}
		]
	});
	
	function applyParam(store){
		
		/**
			是否显示记录总数
		 */
		var zs = '';
//		if(Ext.getCmp('c11').getValue()){
//			zs = '1';
//		}
		
		Ext.apply(store.baseParams, {xszs:zs});
	}
	
	function getRynbid(rynbid){
		if(rynbid != null){
			return rynbid;
		}else{
			return getQueryParam("rynbid");
		}
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
        	items:[p1,p2,p3,p4]
        }
    });
    
	//户政业务处理
	if(hzywid && hzywid!=""){
		Gnt.makeHzyw({
			hzywid: hzywid,
			pch:pch,
			cmpids: ['queryId'],
			callback:function(jo, jolist){
				//成功处理返回，合并数据
				hzywjo_list =[];
				hzywjo_list.push(jo);
				Ext.each(jolist,function(d){
					hzywjo_list.push(d);
				});
				hzywjo = jo;
				
				form20031.getForm().findField("gmsfhm").setValue(jo.bsqrsfz);
				
				Ext.getCmp("queryId").handler();
				
			}
		});
	}
	
	/**
		按下回车键执行查询操作
		
	var key1 = new Ext.KeyMap(document, {
		key:Ext.EventObject.ENTER,
		fn:function() {
			var data = form20031.getForm().getValues();
			
			if(Gnt.util.isEmpty(data)){
				showInfo("至少输入一个查询条件！");
				return;
			}
			
			var store = hjscspGrid.store;
			
			store.removeAll();
			
			store.baseParams = data;
			
			applyParam(store);
			
			store.load({params:{start:0, limit:20}});
			
			//改变颜色
    store.on('load',function(s,records){
    	var girdcount=0;
    	s.each(function(r){
    		
    		if(0 == r.get("ryzt")){
    			
    		}else if(1 == r.get("ryzt")){
    			hjscspGrid.getView().getRow(girdcount).style.backgroundColor="red";
    		}else{
    			hjscspGrid.getView().getRow(girdcount).style.backgroundColor="green";
    		}
    		
    		girdcount ++ ;
    	});
    });
    Ext.getCmp('card').getLayout().setActiveItem(1);
},
scope: this  
});
	 */
	
	
});