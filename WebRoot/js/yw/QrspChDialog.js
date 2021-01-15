/*
 * 	迁入审批登记：
	同人：人员状态不能是0，否则提示“此人已经存在，不允许回迁！”，如果人员状态不是0，那么ryid选择原来的（迁入业务，不再弹出）。
	市内同人：人员状态不限制，ryid选择原来的（迁入业务，继续弹出提示）。
	重号：人员状态不限制，ryid=0（迁入业务，继续弹出提示）。
	
	Gnt.submit(
			{
				sfzh: "340702192212111018,340702192004281013,340702192308171024",
				xm: "测试,测试2,测试3"
			}, 
			"yw/spgl/qrsp/checkQrspdjyw",
			"校验数据，请稍后...", 
			function(jsonData,params){
				var data = jsonData.list[0];
				var chListxm = [];
				if(data.chMap){
					for(sfzh in data.chMap){
						var xm = data.xmMap[sfzh];
						chListxm.push({
							xm: xm,
							gmsfhm: sfzh
						});
					}
				}
				
				if(chListxm.length>0){
					var d = new Gnt.ux.QrspChDialog({
						callback:function(re){
							alert(Ext.encode(re));
						}
					});
					
					d.setData({
						chMap: data.chMap,
						chListxm: chListxm
					});
					d.show();
				}
			}
	);
 * */
Gnt.ux.QrspChDialog = Ext.extend(Ext.Window, {
	title:'迁入审批重号确认',
	closeAction:'close',
	modal:true,
	width:950,
	height:500,
	margins:'5',
	layout:'border',
	setData:function(data){
		var me = this;
		this.data = data;
		
		var chListxm = data.chListxm;
		var xmstore = this.grid_xm.store;
		var chstore = this.grid_ch.store;
		xmstore.removeAll();
		chstore.removeAll();
		
		Ext.each(chListxm,function(item){
			//人员信息
			var rydata = data.chMap[item.gmsfhm][0];
			
			var rdata = {
					xm: item.xm,
					gmsfhm: item.gmsfhm,
					chsfhm: item.gmsfhm,
					chxm: item.xm,
					bchryid: rydata.ryid,
					ryzt: rydata.ryzt
			};
			
			var rr = new xmstore.reader.recordType(rdata, rdata.gmsfhm);
			xmstore.add([rr]);
		});
		
		(function(){
			me.grid_xm.fireEvent("rowclick",this.grid_xm,0);
			me.grid_xm.getSelectionModel().selectRange(0,0);
		}).defer(250);
	},
	initComponent : function(){
		//Qry_常住人口基本信息表
		if(!Gnt.loadSjpzb("20000",function(){}))
			return;
		
	 	var store = new Ext.data.JsonStore({
	        root: 'list',
	        id:'gmsfhm',
	        totalProperty:'totalCount',
	        fields: [
				"xm",
				"gmsfhm",
				"chsfhm",
				"chxm",
				"bchryid",
				"ryzt"
	        ]
	    });
	 	var csm = new Ext.grid.CheckboxSelectionModel({singleSelect:true});
		var colModel = new Ext.grid.ColumnModel([
			{
		        header: "姓名",
		        dataIndex: "xm",
		        sortable: true,
				width: 60
		    },{
		        header: "身份证号码",
		        dataIndex: "gmsfhm",	
		        sortable: true,
				width: 100
		    }
		]);
		
		var grid = new Ext.grid.GridPanel({
	        store: store,
	        title:'重号人员',
	        region:'west',
	    	width:200,
	        view:new Ext.grid.GridView({
					forceFit:true,
					autoFill:true,
					enableRowBody:true
			}),
			stripeRows: true,
	        cm: colModel,
	        sm: csm,
			bodyStyle:'width:100%',
	        border:true,
	        anchor:'100% 100%',
		    margins: '0 0 0 0',
			cmargins: '0 0 0 0',        
	        frame:false,
			iconCls:'icon-grid',
	        listeners:{
				rowclick:function(g, rowIndex, e){
					var win = this.findParentByType("qrspch_dialog");
					var res = win.grid_xm.store.getAt(rowIndex);
					var list = win.data.chMap[res.data.gmsfhm];
					
					var store = win.grid_ch.store;
					store.removeAll();
					
					Ext.each(list, function(item){
						var rr = new store.reader.recordType(item, item.rynbid);
						store.add([rr]);
					});
					
					win.selRes = res;
					if(res.data.ryid){
						win.mxfs.getForm().setValues({
							chk: res.data.chk
						});
					}else{
						win.mxfs.getForm().reset();
					}
					win.tabs.activate("tab1");
				}
			}
	    });	
	 	this.grid_xm = grid;
	 	
		var chGrid = new Gnt.ux.SjpzGrid({
			pkname: 'rynbid',
			pzlb: '20000',
			region:'center',
	    	title: '',
	    	showPaging:false,
	    	width: 200,
			listeners:{
				rowclick:function(g, rowIndex, e){
		   			var win = this.findParentByType("qrspch_dialog");
		   			var res = win.grid_ch.store.getAt(rowIndex);
//		   			win.form_ch.getForm().setValues(res.data);
//		   			Gnt.toFormReadyonly(win.form_ch);
	    		}
			}
		});
		this.grid_ch = chGrid;
		
		var chForm = new Gnt.ux.SjpzForm({
			pkname: 'rynbid',
			closable: false,
			pzlb: '20000',
			height:300,
			width:400,
			labelWidth : 160,
			cols:2,
			bindViewGrid: chGrid,
			changeDictCust:function(cmb,res){
				return;
			},
	        title: ''
		});
		this.form_ch = chForm;
		
		var tabs = new Ext.TabPanel({
	        activeTab: 0,
	        plain:true,
	        region:'center',
	        initconfig:{
	        	
	        },
	        defaults:{
	        		autoScroll: true,
	        		frame: false,
	        		layout:'fit',
	        		shim: true
	        },
	        listeners: {
				//必须，否则queryYhFs里面的表单元素无法显示
			    tabchange: function(tab, p){
			    	if(!tab.initconfig[p.id]){
			    		p.doLayout();
			    		tab.initconfig[p.id] = "1";
			    	}
			    	
			    	if(p.id=="tab2"){
			    		//表单
			    		var win = this.findParentByType("qrspch_dialog");
			    		
			    		var r = win.grid_ch.getSelectionModel().getSelected();
			    		if(!r){
			    			r = win.grid_ch.store.getAt(0);
			    		}
			    		
			   			win.form_ch.getForm().setValues(r.data);
			   			Gnt.toFormReadyonly(win.form_ch);
			    	}
			    }   
			},
	        items:[ {
                	title: '重号人员列表',
                	layout:'fit',
                	id:'tab1',
                	items: chGrid
	        	},{
	        		title: '人员基本明细',
	        		items:chForm,
	        		id:'tab2',
	        		layout:'fit'
            }]
		});
		this.tabs = tabs;
		
		var items = [];
		items.push(
				{
	                columnWidth:.33,
    	           	layout: 'form',
    	           	bodyStyle:'padding:0 0 0 0',
        	       	items: [{
        	       		height:50,
						xtype:'radio',
						allowBlank:false,
						name:'chk',
						fieldLabel:'重号',
						inputValue:'0',
						listeners:{
							check : function( chk, checked ){
								if(checked){
									var win = this.findParentByType("qrspch_dialog");
									win.selRes.data.ryid='0';
									win.selRes.data.hhnbid = '重号';
									win.selRes.data.chk = chk.inputValue;
								}
							}
						}
					}]
				}
		);
		if(this.issp!="0"){
			items.push({
                columnWidth:.33,
	           	layout: 'form',
	           	bodyStyle:'padding:0 0 0 0',
    	       	items: [{
					xtype:'radio',
					height:50,
					allowBlank:false,
					name:'chk',
					fieldLabel:'市内同人',
					inputValue:'1',
					listeners:{
						check : function( chk, checked ){
							if(checked){
								var win = this.findParentByType("qrspch_dialog");
								win.selRes.data.ryid=win.selRes.data.bchryid;
								win.selRes.data.hhnbid = '市内同人';
								win.selRes.data.chk = chk.inputValue;
							}
						}
					}
				}]
			});
		}
		
		items.push({
            columnWidth:.34,
           	layout: 'form',
           	bodyStyle:'padding:0 0 0 0',
	       	items: [{
				xtype:'radio',
				height:50,
				allowBlank:false,
				name:'chk',
				//disabled:true,
				fieldLabel:'同人',
				inputValue:'2',
				listeners:{
					check : function( chk, checked ){
						if(checked){
							var win = this.findParentByType("qrspch_dialog");
							if(win.selRes.data.ryzt=='0'){
								showInfo("此人已经存在，不允许回迁！");
								win.selRes.data.ryid= "";
								chk.setValue(false);
								return;
							}else{
								win.selRes.data.ryid= win.selRes.data.bchryid;
								win.selRes.data.hhnbid = '同人';
								win.selRes.data.chk = chk.inputValue;
							}
						}
					}
				}
	       	}]
		});
		
		var mxfs = new Ext.form.FormPanel({
	    	title:'重号/同人判断',
	    	anchor:'100% 100%',
	    	buttonAlign:'right',
	    	region:'south',
	    	height:100,
	    	labelAlign:'right',
	    	frame:true,
	    	border:true,
	        layout:'form',
	        labelWidth:75,
	       	items:[{
	        		layout:'column',
	    			frame:false,
	    			border:false,
	        		defaults:{
	        			border:false,
	        			frame:false
	        		},
	            	items:items
	       		}]
		});
		this.mxfs = mxfs;
		
		this.items = [
		      grid,tabs,mxfs
		]
		
		//不要用new Button，否则多次弹出可能存在按钮无法显示的BUG
		this.buttons = [
		 				{
							text:'确定',
							minWidth:75,
							handler:function(){
								var win = this.findParentByType("qrspch_dialog");
								var store = win.grid_xm.store;
								var re = [];
								var map = {};
								for(i=0;i<store.getCount();i++){
									var data = store.getAt(i).data;
									if(!data.ryid || data.ryid==""){
										showInfo("[" + data.xm + "]必须选择重号，同人设置！")
										return;
									}else{
										re.push(data);
										map[data.gmsfhm] = data.ryid;
									}
								}

								if(win.callback){
									win.callback(re, map);
								}
								win.hide();
							}
					},
					{
						text:'取消',
						minWidth:75,
						handler:function(){
							var win = this.findParentByType("qrspch_dialog");
							win.hide();
						}
				}
		];
		
		/**
			2018.10.31
			新增	刁杰
			BugFree 209
			动态的将第十五列(人员状态)移动到第三列
		 */
		chGrid.colModel.moveColumn(14,2);
		
		Gnt.ux.QrspChDialog.superclass.initComponent.call(this);
	}
});
Ext.reg('qrspch_dialog', Gnt.ux.QrspChDialog);