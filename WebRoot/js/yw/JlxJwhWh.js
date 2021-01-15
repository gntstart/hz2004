var Selres =null;
var jwhdmTemp = null;
var submitType=null;
Gnt.ux.JlxJwhWh= Ext.extend(Ext.Window, {
	title:'街路巷居委会对照维护',
	closeAction:'hide',
	modal:true,
	width:650,
	height:500,
	margins:'5',
	layout:'border',
	html:'',
	show:function(){
		Gnt.ux.JlxJwhWh.superclass.show.call(this);
	},
	setSelRes:function(res,param,length){
		this.panel1.getForm().setValues({
			jwhdm:res.data.dm,
			jwhmc:res.data.mc
		});
		jwhdmTemp = res.data.dm;
		var grid = this.jwhxx1Grid;
		var  jwhxx1store = grid.store;
		jwhxx1store.baseParams =  param;
		jwhxx1store.load();
//		jwhxx1store.on("load",function(store) {  
//			if(jwhxx1store.data.length > 0){
//				grid.fireEvent("rowclick",grid,0);
//				grid.getSelectionModel().selectRange(0,0);
//			}
//			
//		},grid);
		this.panel3.items.items[0].body.update("已有街路巷    共有记录"+length);	
//		this.panel4.items.items[0].items.setValues({
//			jlxdm:"",
//			jlxmc:"",
//			jlxh:""			
//		});
		Ext.getCmp('xzjddmAdd').setValue("");
		Ext.getCmp('jlxdm').setValue("");
		Ext.getCmp('jlxmc').setValue("");
		Ext.getCmp('jlxh').setValue("");
	},
	resetData:function(){
		//this.fs.getForm().reset();
	},
	initComponent : function(){
		this.panel1 = new Ext.form.FormPanel({
	    	height: 50,
	    	region: 'north',
	    	anchor:'100% 100%',
	    	buttonAlign:'right',
	    	labelAlign:'right',
	    	frame:true,
	    	border:false,
	    	fileUpload: true, 
	    	margins:'0',
	        layout:'form',
	        labelWidth:100,
	       	items:[{
	        		layout:'column',
	    			frame:false,
	    			border:false,
	        		defaults:{
	        			border:false,
	        			frame:false
	        		},
	            	items:[{
			                columnWidth:0.5,
		    	           	layout: 'form',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								id:'jwhdm',
								name:'jwhdm',
								allowBlank:false,
								disabled:true,
								fieldLabel:'居委会代码'
							}]
						},{
			                columnWidth:0.5,
		    	           	layout: 'form',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'jwhmc',
								allowBlank:true,
								disabled:true,
								fieldLabel:'居委会名称',
			                    listeners: {}
							}]
						}
				]
			}]
		});
		this.panel2 = new Ext.Panel({
			height: 200,
			width:'50%',
	    	region: 'west',
	    	frame:true,
	    	
	    	border:false,
	    	items:[
	    		{
	    			width: 80,
					html:'本市街路巷',
					bodyStyle: 'padding:10px 0px 10px 0px ',
					border:false,
					frame: false
	    		},{
	            	title: '选择要增加的街路巷',
//					columnWidth: 3,
		            xtype: 'fieldset',
		            autoHeight: true,
		            layout:'column',
	    			frame:true,
	    			border:true,
	    			bodyStyle: 'padding:10px 0px 10px 0px ',	
	        		defaults:{
	        			border:false,
	        			frame:false
	        		},
		            items: [{
		            	columnWidth:1,
		            	xtype:'form',
		            	items: [{
							xtype:'search_combox',
							anchor:'100%',
							searchUrl:'dict/utils/searchXxb?visiontype=JLXXXB',
							fields:["code","name"],
							valueField: "code",
							displayField: "name",
							allowBlank:true,
							fieldLabel:'街道',
							id:'xzjddmAdd',
							hiddenName:'xzjddm'
						}]
		            },{
		            	columnWidth:0.8,
	    	           	layout: 'form',
	        	       	items: [{
							border:false,
							frame: false
						}]
					},{
						columnWidth:0.2,
	    	           	layout: 'form',
	        	       	items: [new Ext.Button({
		        			text:'增加',
		        			//minWidth:60,
		        			buttonAlign:'right',
		        			handler:function(){
		        				if(!Ext.getCmp('xzjddmAdd').getValue()){
		        					alert("请选择正确的街路巷！");
		        					return;
		        				}
		        				Ext.getCmp('jlxdm').setValue(Ext.getCmp('xzjddmAdd').getValue());
		    	        		Ext.getCmp('jlxmc').setValue(Ext.getCmp('xzjddmAdd').getRawValue());
		    	        		Ext.getCmp('jlxh').setValue("");
		    	        		submitType =1;
//		        				var param = {
//		        						jlxdm:Ext.getCmp('xzjddmAdd').getValue(),
//		        						jlxhlx:1,
//		        						jwhdm:jwhdmTemp
//		        				};
//		        				//提交数据
//		        				Gnt.submit(
//		        						param, 
//		        						"gl/xtmbgl/jwhxxwh/addJlx", 
//		        						"正在增加街路巷信息，请稍后...", 
//		        						function(jsonData,params){
//		        							showInfo("街路巷信息增加成功！");
//		        							//	this.jwhxx1Grid.store.reload(); 
//		        						}
//		        				);
		        			}
		        		})]
					}]
				}
	    	]
		});
		var jwh1csm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
		var jwh1colModel = new Ext.grid.ColumnModel([
			{
				header: "czid",
		        dataIndex: "czid",
		        sortable: true,
		        hidden:true,
				width: 120
			},
			{
				header: "街路巷代码",
		        dataIndex: "jlxdm",
		        sortable: true,
				width: 120
			},{
		        header: "街路巷名称",
		        dataIndex: "jlxmc",
		        sortable: true,
				width: 120
		    },{
				header: "门楼牌号",
		        dataIndex: "jlxh",
		        sortable: true,
				width: 120
			}
		]);
	 	var myuser1Store = new Ext.data.JsonStore({
	 		proxy: new Ext.data.HttpProxy({
	 			url: 'gl/xtmbgl/jwhxxwh/getJlxByJwhdm',
				method: "POST",
				disableCaching: true
		    }),
	        root: 'list',
	        totalProperty:'totalCount',
	        fields: [
	        	"czid",
	        	"jlxdm",
				"jlxmc",
				"jlxh"
	        ],
	        listeners:{
				loadexception:function(mystore,options,response,error){
					if(error){
						var json = Ext.decode(response.responseText);
						Ext.Msg.alert("提示",json.messages[0]);
					}else{
						Ext.Msg.alert("提示",response.responseText);
					}
				}
	        }
	    });	
	 	
		this.jwhxx1Grid = new Ext.grid.GridPanel({
	        store: myuser1Store,
	        region: 'center',
	        view:new Ext.grid.GridView({
					//forceFit:true,
					//autoFill:true,
					enableRowBody:true
			}),
			stripeRows: true,
	        cm: jwh1colModel,
	        sm:jwh1csm,
	        height:260,
			loadMask: {msg:'正在加载数据，请稍侯……'},
			bodyStyle:'width:100%',
	        border:true,
	        anchor:'100% 100%',
		    margins: '0 0 0 0',
			cmargins: '0 0 0 0',        
	        frame:false,
			iconCls:'icon-grid',
	        title:'',
	        listeners:{
	        	rowclick:function(g, rowIndex, e){
	        		Selres = g.store.getAt(rowIndex);
	        		Ext.getCmp('jlxdm').setValue(Selres.data.jlxdm);
	        		Ext.getCmp('jlxmc').setValue(Selres.data.jlxmc);
	        		Ext.getCmp('jlxh').setValue(Selres.data.jlxh);
	        		submitType =2;
//	        		this.panel4.getForm().setValues({
//		    			jlxdm:Selres.data.jlxdm,
//		    			jlxmc:Selres.data.jlxmc,
//		    			mlph:Selres.data.mlph
//		    		});
	        		
	        	},
				rowdblclick:function(g, rowIndex, e){
					var Selres = g.store.getAt(rowIndex);
					Ext.getCmp('jlxdm').setValue(Selres.data.jlxdm);
	        		Ext.getCmp('jlxmc').setValue(Selres.data.jlxmc);
	        		Ext.getCmp('jlxh').setValue(Selres.data.jlxh);
	        		submitType =2;
				}
			},
	        bbar: new Ext.PagingToolbar({
					pageSize: 20,
					store: myuser1Store,
					displayInfo: true
			})
	    });
		this.panel3 = new Ext.Panel({
			height: 200,
			width:'50%',
	    	region: 'center',
	    	frame:true,
	    	border:false,
	    	items:[
	    		{
	    			width: 150,
					html:'本市街路巷',
					bodyStyle: 'padding:0px 0px 5px 0px ',
					border:false,
					frame: false
	    		},this.jwhxx1Grid
	    	]
		});
		this.panel4 = new Ext.Panel({
			height: 100,
	    	region: 'south',
	    	frame:true,
	    	border:false,
	    	items:[
	    		{
	            	title: '街路巷信息修改',
//					columnWidth: 3,
		            xtype: 'fieldset',
		            autoHeight: true,
		            layout:'column',
	    			frame:true,
	    			border:true,
	        		defaults:{
	        			border:false,
	        			frame:false
	        		},
		            items: [{
				            	columnWidth:0.5,
				            	xtype:'form',
				            	items: [{
				    	           	layout: 'form',
				    	           	bodyStyle:'padding:5 0 0 0',
				        	       	items: [{
										xtype:'textfield',
										anchor:'100%',
										id:'jlxdm',
										name:'jlxdm',
										allowBlank:false,
										disabled:true,
										fieldLabel:'街路巷代码'
									}]
								}]
				            },{
				                columnWidth:0.5,
			    	           	layout: 'form',
			    	           	bodyStyle:'padding:5 0 0 0',
			        	       	items: [{
									xtype:'textfield',
									anchor:'100%',
									id:'jlxmc',
									name:'jlxmc',
									allowBlank:false,
									disabled:true,
									fieldLabel:'街路巷名称'
								}]
							},{
				                columnWidth:1,
			    	           	layout: 'form',
			    	           	bodyStyle:'padding:5 0 0 0',
			        	       	items: [{
									xtype:'textfield',
									anchor:'100%',
									id:'jlxh',
									name:'jlxh',
									allowBlank:true,
//									disabled:true,
									fieldLabel:'门楼牌号'
								}]
							}]
				}
	    	]
		});
		this.items = [this.panel1,this.panel2,this.panel3,this.panel4];
		
		Gnt.ux.JlxJwhWh.superclass.initComponent.call(this);
	},
	   buttons:[{
	        text:'确定',
			minWidth:75,
			handler:function(){
				var win = this.findParentByType("jlxJwh_window");
				var grid = win.panel3.items.items[1];
				Selres;
				//var grid = win.items.get(2);
//				if(grid.store.getCount()<=0){
//					showErr("请先查询并选择要修改的街路巷信息！");
//					return;
//				}
				var data =null;
				if(submitType==1){//新增的街路巷
					data = {
						jwhdm:jwhdmTemp,
						qybz:1,
						jlxhlx:1,
						jlxdm:Ext.getCmp('jlxdm').getValue(),
						jlxmc:Ext.getCmp('jlxmc').getValue(),
						jlxh:Ext.getCmp('jlxh').getValue()
					}
				}else if(submitType==2){//点击已有街路巷进行修改
					data = {
							czid:Selres.data.czid,
							jlxh:Ext.getCmp('jlxh').getValue()
					}
				}


				win.hide();
				if(win.callback)
					win.callback(submitType,data);
			}
	    },{
	        text:'关闭',
			minWidth:75,
			handler:function(){
				var win = this.findParentByType("jlxJwh_window");
				win.hide();
			}
	    }]
	
	
});
Ext.reg('jlxJwh_window', Gnt.ux.JlxJwhWh);
