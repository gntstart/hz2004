var dlkl='';
var qrkl='';
Gnt.ux.SelectYhWhAdd= Ext.extend(Ext.Window, {
	title:'用户信息新增',
	closeAction:'hide',
	modal:true,
	width:520,
	height:450,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(){
		Gnt.ux.SelectYhWhAdd.superclass.show.call(this);
	},
	setSelRes:function(){
		this.firstPanel.getForm().setValues({
			yhdlm: '',
			jyh: '',
			dwdm:'',
			yhxm: '',
			yhxb: '',
			gmsfhm:'',
			yhzw: '',
			dlkl: '',
			qrkl:'',
			yhmj:''
		});
		Ext.getCmp('czmj1').setValue();
		Ext.getCmp('czmj2').setValue();
		Ext.getCmp('czmj3').setValue();
		Ext.getCmp('czmj4').setValue();
	},
	resetData:function(){
		this.fs.getForm().reset();
	},
	initComponent : function(){
		this.firstPanel = new Ext.form.FormPanel({
	    	height: 80,
	    	region : 'center',
//	    	width:300,
	    	//anchor:'60% 60%',
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
		        		layout:'column',
		    			frame:false,
		    			border:false,
		        		defaults:{
		        			border:false,
		        			frame:false
		        		},
		            	items:[{
				                columnWidth:1,
			    	           	layout: 'form',
			    	           	bodyStyle:'padding:5 0 0 0',
			        	       	items: [{
									xtype:'textfield',
									anchor:'100%',
									name:'yhdlm',
									allowBlank:false,
									fieldLabel:'用户登录名'
								}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'jyh',
								allowBlank:false,
								fieldLabel:'警员号'
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
		        	       		xtype:'search_combox',
								anchor:'100%',
								searchUrl:'dict/utils/searchXxb?visiontype=DWXXB&whflg=1',
								fields:["code","name"],
								valueField: "code",
								displayField: "name",
//								id:'xzqhbdm',
								allowBlank:false,
								fieldLabel:'单位代码',
								hiddenName:'dwdm'
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'yhxm',
								fieldLabel:'用户姓名'
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
		        	       		anchor:'100%',
								xtype:'dict_combox',
								dict:'VisionType=CS_XB',
								name:'xbmc',
								maxLength:40,
								hiddenName:'yhxb',
								allowBlank:false,
								fieldLabel:'用户性别'
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'gmsfhm',
								fieldLabel:'身份号码'
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
		        	       		anchor:'100%',
								xtype:'dict_combox',
								dict:'VisionType=CS_YHZW',
								name:'yhzwmc',
								maxLength:40,
								hiddenName:'yhzw',
								allowBlank:false,
								fieldLabel:'用户职务'
							}]
						},{
			                columnWidth:0.5,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
		        	       		xtype:'textfield',
								anchor:'100%',
								name:'dlkl',
								allowBlank:false,
								fieldLabel:'登录口令',
								autoCreate: {tag: 'input', type: 'text', size: '20', autocomplete: 'off', maxlength: '8'},
	                            enableKeyEvents:true,
	                            listeners:{
                                     keyup:function(src, evt){
				                        	var win = this.findParentByType("addyhwh_window");
				                        	var lengthT = src.getValue().length;
			                        		var temp ="";
			                        		
				                        	if(src.getValue()&&evt.keyCode!=8){
				                        		dlkl= dlkl+src.getValue().substring(lengthT-1,lengthT);
				                        		for(var i = 0;i<lengthT;i++){
				                        			temp="*"+temp;
				                        		}
				                        		win.cardPanel.items.items[0].getForm().findField("dlkl").setValue(temp);
				                        	}else if(src.getValue()&&evt.keyCode==8){
				                        		if(lengthT>0){
				                        			temp.substring(0,lengthT-1);
				                        			dlkl= dlkl.substring(0,lengthT);
				                        		}
				                        	}else{
				                        		dlkl='';
				                        	}
                                      }
	                            }
							}]
						},{
			                columnWidth:0.5,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'qrkl',
								allowBlank:false,
								autoCreate: {tag: 'input', type: 'text', size: '20', autocomplete: 'off', maxlength: '8'},
								fieldLabel:'口令确认',
	                            enableKeyEvents:true,
	                            listeners:{
                                     keyup:function(src, evt){
				                        	var win = this.findParentByType("addyhwh_window");
				                        	var lengthT = src.getValue().length;
			                        		var temp ="";
			                        		
				                        	if(src.getValue()&&evt.keyCode!=8){
				                        		dlkl= dlkl+src.getValue().substring(lengthT-1,lengthT);
				                        		for(var i = 0;i<lengthT;i++){
				                        			temp="*"+temp;
				                        		}
				                        		win.cardPanel.items.items[0].getForm().findField("qrkl").setValue(temp);
				                        	}else if(src.getValue()&&evt.keyCode==8){
				                        		if(lengthT>0){
				                        			temp.substring(0,lengthT-1);
				                        			dlkl= dlkl.substring(0,lengthT);
				                        		}
				                        	}else{
				                        		dlkl='';
				                        	}
                                      }
	                            }
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
		        	       		anchor:'100%',
								xtype:'dict_combox',
								dict:'VisionType=CS_YHMJ',
								name:'yhmjmc',
								maxLength:40,
								hiddenName:'yhmj',
								allowBlank:false,
								fieldLabel:'用户密级'
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:0 0 0 0',
		        	       	items: [{
		        	       		xtype: 'fieldset',
		    					autoHeight: true,
		    					title: '操作密级',
								anchor:'100%',
								layout:'column',
								defaults:{
									xtype:'checkbox',
					            	columnWidth: .5,
					            	fieldLabel: ''
					            },
					            items: [{
					            	id:'czmj1',
					            	boxLabel: '系统管理员',
					            	name:'fw',
					            	inputValue : "1"
					            },{
					            	id:'czmj2',
					            	boxLabel: '上报数据专管员',
					            	name:'fw',
					            	inputValue : "2"
					            },{
					            	id:'czmj3',
					            	boxLabel: '制证专管员',
					            	name:'fw',
					            	inputValue : "3"
					            },{
					            	id:'czmj4',
					            	boxLabel: '一般用户',
					            	name:'fw',
					            	inputValue : "4"
					            }]
							}]
						}
					]
				}]
			}]
		});
		this.secondPanel = new Ext.Panel({
			anchor:'100% 100%',
			layout:'border',
			frame:true,
	    	border:false,
			items:[{
				region:'north',
				title:'用户角色'
			},{
				frame:true,
		    	border:false,
		    	layout:'column',
		    	region:'center',
        		defaults:{
        			border:false,
        			frame:false
        		},
        		items:[
        			{
    	        		layout:'column',
    	    			frame:false,
    	    			border:false,
    	        		defaults:{
    	        			border:true,
    	        			frame:false
    	        		},
        				items:[
        					{
        	            	title: '已分配角色',
        	            	columnWidth:0.5,
        		            xtype: 'fieldset',
        		            autoHeight : true, 
        		            labelWidth : 80,
        		            items: [
        		            	{
        	            			xtype : 'textarea',
        	            			border:false,
        	            			frame:false,
        	            			fieldLabel:"",
        	            			labelSeparator: '',
        	            			anchor : '100% 100%',
        	            			name:'yfpjs'
        		            	}
        		            ]
        				},{
        	            	title: '未分配角色',
        	            	columnWidth:0.4,
        		            xtype: 'fieldset',
        		            autoHeight : true, 
        		            labelWidth : 80,
        		            items: [
        		            	{
        	            			xtype : 'textarea',
        	            			border:false,
        	            			frame:false,
        	            			fieldLabel:"",
        	            			labelSeparator: '',
        	            			anchor : '100% 100%',
        	            			name:'wfpjs'
        		            	}
        		            ]
        				}]
        			}
        			]
			}
			],
			buttons:[{
		        text:'打印',
				minWidth:75,
				handler:function(){}
		    },{
		        text:'关闭',
				minWidth:75,
				handler:function(){}
		    }]
		});	
		var csm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
		var colModel = new Ext.grid.ColumnModel([
			{
				header: "辖区类型",
		        dataIndex: "xqlx",
		        sortable: true,
				width: 120,
				renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
					return Gnt.ux.Dict.getDictLable('CS_XQLX', value);
				}
			},{
				header: "数据范围",
		        dataIndex: "sjfw",
		        sortable: true,
				width: 240
			}
		]);
	 	var myuserStore = new Ext.data.JsonStore({
	 		proxy: new Ext.data.HttpProxy({
	 			url: 'XXXXX',//yw/hjyw/hzywcl/queryHzywcl
				method: "POST",
				disableCaching: true
		    }),
	        root: 'list',
	        id:'id',
	        totalProperty:'totalCount',
	        fields: [
	        	"xqlx",
				"sjfw"
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
		var yhsjGrid = new Ext.grid.GridPanel({
	        store: myuserStore,
	        region: 'center',
	        view:new Ext.grid.GridView({
					//forceFit:true,
					//autoFill:true,
					enableRowBody:true
			}),
			stripeRows: true,
	        cm: colModel,
	        sm:csm,
			loadMask: {msg:'正在加载数据，请稍侯……'},
			bodyStyle:'width:80%',
	        border:true,
	        anchor:'100% 60%',
		    margins: '0 0 0 0',
			cmargins: '0 0 0 0',        
	        frame:false,
			iconCls:'icon-grid',
	        title:'',
	        listeners:{
	        	rowclick:function(g, rowIndex, e){
	        		selectedSelRes = g.store.getAt(rowIndex);
	        	},
				rowdblclick:function(g, rowIndex, e){
					selectedSelRes = g.store.getAt(rowIndex);
				}
			},
	        bbar: new Ext.PagingToolbar({
					pageSize: 20,
					store: myuserStore,
					displayInfo: true
			})
	    });
		this.thirdPanel = new Ext.form.FormPanel({
	    	height: 80,
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
	    			anchor:'100% 40%',
	        		defaults:{
	        			border:false,
	        			frame:false
	        		},
	            	items:[{
		        		layout:'column',
		    			frame:false,
		    			border:false,
		        		defaults:{
		        			border:false,
		        			frame:false
		        		},
		            	items:[{
							columnWidth:1,
				           	layout: 'form',
				           	bodyStyle:'padding:5 0 0 0',
			            	items:[{
			            		xtype : 'dict_combox',
								dict:"VisionType=CS_XQLX",
								border:false,
		            			frame:false,
		            			name:'xqlx',
		            			fieldLabel:"辖区类型",
		            			maxHeight : 80,
		            			anchor : '100%'}
							]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								id:'ssxqadd',
				            	layout:'border',
			    	       		anchor:'100%',
								xtype:'tree_comboBox',
								searchUrl:'dict/utils/searchXzqhNew',
								fields:["mc","dm","dwList"],
								valueField: "dm",
								displayField: "mc",
								treeUrl:'dict/utils/searchTreeXzqh',
								name:'ssxq',
//								maxLength:30,
								allowBlank:true,
								fieldLabel:'省市县区',
								listeners:{
									select:function(combo, res, eOpts ){
										var win = this.findParentByType("addsjfw_window");
										var data = res.data;
										var f = combo.findParentByType("form").getForm().findField("pcs");
										f.setValue("");
										f.store.removeAll();
										if(data.dwList && data.dwList.length>0){
										var list = new Array();
											for(var i=0;i<data.dwList.length;i++){
												list[i] = new Array();
												list[i][0]=data.dwList[i].dm;
												list[i][1]=data.dwList[i].mc;
												list[i][2]=data.dwList[i];
											}
											f.store.loadData(list);
										}
									}
								}
		        	       	}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
		        	       		id:'zrqId',
		        	       		xtype:'dict_combox',
								dict:'VisionType=_BLANK',//VisionType=_BLANK  //dict/utils/searchPcs
								anchor:'100%',
								name:'pcs',
								hiddenName:'pcs',
								fieldLabel:'派出所',
								listeners:{
									select:function(combo, res, eOpts ){
										var stroe = new Ext.data.JsonStore({
									        url: 'dict/utils/searchXxb?visiontype=JWHXXB',
									        combox:this,
									        root: 'list',
									        id: this.valueField,
									        totalProperty:'totalCount',
									        fields: this.fields?this.fields:[this.valueField,this.displayField]
										});
										
										var f = combo.findParentByType("form").getForm().findField("jwh");
										f.setValue("");
										f.store.removeAll();
										stroe.load({params:{'search_code':res.data.code}});
										
										stroe.on("load",function(store) {  
											if(store.data.length > 0){
												var list = new Array();
												for(var i=0;i<store.data.length;i++){
													var map =store.data.items;
													list[i] = new Array();
													list[i][0]=map[i].data.code;
													list[i][1]=map[i].data.name;
													list[i][2]=map[i];
												}
												f.store.loadData(list);
											}
										});
									}
								}
							}]
						},{
							columnWidth:0.7,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
		        	       		id:'jwhId',
		        	       		xtype:'dict_combox',
								dict:'VisionType=_BLANK',//VisionType=_BLANK  //dict/utils/searchPcs
								//fields:["mc","dm"],
								anchor:'100%',
								name:'jwh',
								hiddenName:'jwh',
								fieldLabel:'居委会'
			            	}]
						},{
							columnWidth:0.9,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [new Ext.Button({
			        			text:'增加',
			        			minWidth:80,
			        			handler:function(){
			        				
			        			}
			        		})]
						}
					]
				}]
			},yhsjGrid]
		});
	    var i=0;
	    var navHandler = function(direction){
	 
	        if (direction == -1){
	            i--;
	            if (i < 0) { i = 0;}
	        }
	        if (direction == 1){
	            i++;
	            if (i > 2) { i = 2; return false;}
	        }
	        cardPanel.getLayout().setActiveItem(i);
	    };
	    var cardPanel = new Ext.Panel({
	        layout: 'card',
	        //title:'注册向导',
	        activeItem: 0,
	        bodyStyle:'padding:5px',
	        defaults:{
	            border:false
	        },
	        bbar: [ {
	            id: 'move-prev',
	            text: '上一页',
	            minWidth:75,
	            handler:navHandler.createDelegate(this,[-1])
	        }, {
	            id: 'move-next',
	            text: '下一页',
	            minWidth:75,
	            handler:navHandler.createDelegate(this,[1])
	        }, new Ext.Button({
	        	   text:'确定',
	        	   border:true,
		            frame:true,
					minWidth:75,
					handler:function(){
    	 				var rootWin = this.findParentByType("addyhwh_window");
    	 				var form = rootWin.items.get(0);
    	 				if(!form.getForm().isValid()){
    						showErr("单位信息必须填写！");
    						return;
    					}
    	 				var lhdz = form.getForm().getValues();
    	 				lhdz.dwjb = Ext.getCmp('newcdwjb').getValue();
    	 				if(rootWin.callback){
							rootWin.callback('lh', lhdz);
						}
						rootWin.hide();
    	 			
					}
	           }), new Ext.Button({
	        	   text:'取消',
	        	   minWidth:75,
					border:true,
		            frame:true,
					handler:function(){
						var win = this.findParentByType("addyhwh_window");
    	 				win.hide();
					}
	           })],
	        items: [this.firstPanel,this.secondPanel,this.thirdPanel]
	    });
	    this.cardPanel = cardPanel;
		this.items = cardPanel;
		//this.items = [this.cardPanel];//this.centerPanel,this.rightPanel
		
		Gnt.ux.SelectYhWhAdd.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('addyhwh_window', Gnt.ux.SelectYhWhAdd);
