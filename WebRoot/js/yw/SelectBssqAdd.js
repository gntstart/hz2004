var addType =1;
Gnt.ux.SelectBssqAdd= Ext.extend(Ext.Window, {
	title:'本市市区增加',
	closeAction:'hide',
	modal:true,
	width:400,
	height:300,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(){
		Gnt.ux.SelectBssqAdd.superclass.show.call(this);
	},
	setSelRes:function(){
		if(addType==1){
			Ext.getCmp('xzqhd2').hide();
			Ext.getCmp('xzqhd3').hide();
		}else if(addType==2){
			Ext.getCmp('xzqhd2').show();
			Ext.getCmp('xzqhd3').show();
		}
		
		Ext.getCmp('sqbzadd').setValue();
		Ext.getCmp('gxbzadd').setValue();
		Ext.getCmp('xzqhdmadd1').setValue();
		Ext.getCmp('xzqhdmadd2').setValue();
		Ext.getCmp('xzqhdmadd3').setValue();
	},
	resetData:function(){
		
	},
	initComponent : function(){
		this.rightPanel = new Ext.Panel({
			region : 'east',
			width:'40%',
			items:[
				{
	    	    	height:20,
	    	    	border:false,
	    	    	frame:false
	    	    },
				{
	            	id:'centerId',
	            	title: '增加方式',
	            	region:'center',
		            xtype: 'fieldset',
		            layout:'column',
					height:50,
            		bodyStyle : 'padding:0 0 0 5',
					defaults:{
		            	columnWidth: 1,
		            	fieldLabel: '',
		            	name: 'fs'
		            },
		            items: [{
		            	id:'r11',
		            	xtype:'radio',
		            	columnWidth: 0.5,
		            	boxLabel: '1-单一市区',
		            	name:'fs',
		            	inputValue : "1",
		            	checked:true,
		            	listeners:{
		            		'check' : function(checkbox, checked){ 
		                         if(checked){//只有在点击时触发
		                        	 addType = 1;
		                        	 Ext.getCmp('xzqhd1').show();
		                        	 Ext.getCmp('xzqhd2').hide();
		                        	 Ext.getCmp('xzqhd3').hide();
		                         }  
		                    }
		                //监听事件
			            }
		            },{
			            	id:'r12',
			            	xtype:'radio',
			            	columnWidth: 0.5,
			            	boxLabel: '2-连续市区',
			            	name:'fs',
			            	inputValue : "2",
			            	listeners:{
			            		'check' : function(checkbox, checked){ 
			                         if(checked){//只有在点击时触发
			                        	 addType = 2;
			                        	 Ext.getCmp('xzqhd1').hide();
			                        	 Ext.getCmp('xzqhd2').show();
			                        	 Ext.getCmp('xzqhd3').show();
			                         }  
			                    }
			                //监听事件
				            }
			            }]
				},{
	            	id:'southId',
	            	anchor:'100% 100%',
	    	    	buttonAlign:'right',
	    	    	labelAlign:'right',
	    	    	frame:true,
	    	    	border:false,
	    	    	fileUpload: true, 
	    	    	margins:'0',
	    	        labelWidth:100,
					layout:'form',
		            items: [{
		                columnWidth:1,
	    	           	layout: 'form',
	    	           	bodyStyle:'padding:5 0 0 0',
	        	       	items: [{
	        	       		xtype : "combo",
		            		store : [['0','0-市区外'],['1','1-市区内']],
	            			border:false,
	            			frame:false,
	            			id:'sqbzadd',
	            			name:'sqbz',
	            			allowBlank:false,
	            			fieldLabel:"市区标志",
	            			triggerAction:"all",
	            			maxHeight : 80,
	            			anchor : '100%'
						}]
					},{
		                columnWidth:1,
	    	           	layout: 'form',
	    	           	bodyStyle:'padding:5 0 0 0',
	        	       	items: [{
	        	       		xtype : "combo",
		            		store : [['0','0-非管辖区'],['1','1-管辖区']],
	            			border:false,
	            			frame:false,
	            			id:'gxbzadd',
	            			name:'gxbz',
	            			allowBlank:false,
	            			fieldLabel:"管辖标志",
	            			triggerAction:"all",
	            			maxHeight : 80,
	            			anchor : '100%'
						}]
					},{
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
		    	           	id:'xzqhd1',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'search_combox',
								anchor:'100%',
								searchUrl:'dict/utils/searchXzqh.json?qybz=1',
								fields:["code","name"],
								valueField: "code",
								displayField: "name",
								//	allowBlank:false,
								id:'xzqhdmadd1',
								fieldLabel:'行政区划码',
								hiddenName:'xzqhdm1'
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	//hidden:true,
		    	           	id:'xzqhd2',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'search_combox',
								anchor:'100%',
								searchUrl:'dict/utils/searchXzqh.json?qybz=1',
								fields:["code","name"],
								valueField: "code",
								displayField: "name",
								//allowBlank:false,
								fieldLabel:'起始区划码',
								id:'xzqhdmadd2',
								hiddenName:'xzqhdm2'
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	id:'xzqhd3',
		    	           	//hidden:true,
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'search_combox',
								anchor:'100%',
								searchUrl:'dict/utils/searchXzqh.json?qybz=1',
								fields:["code","name"],
								valueField: "code",
								displayField: "name",
								//allowBlank:false,
								fieldLabel:'结束区划码',
								id:'xzqhdmadd3',
								hiddenName:'xzqhdm3'
							}]
						}
					]
				}]
				}
			],
			buttons:[{
		        text:'确定',
				minWidth:75,
				handler:function(){
					var rootWin = this.findParentByType("addbssqWindow");
					//var form = rootWin.items.get(0);
	 				if(!(Ext.getCmp('sqbzadd').getValue()&&Ext.getCmp('gxbzadd').getValue()&&((addType==1&&Ext.getCmp('xzqhdmadd1').getValue()||(addType==2&&Ext.getCmp('xzqhdmadd2').getValue()&&Ext.getCmp('xzqhdmadd3').getValue()))))){
						showErr("本市市区信息必须填写完整！");
						return;
					}
					//alert(Ext.getCmp('sqbzadd').getValue());
					//alert(Ext.getCmp('gxbzadd').getValue());
					var bssqObj={};
					bssqObj.sqbz=Ext.getCmp('sqbzadd').getValue();
					bssqObj.gxbz=Ext.getCmp('gxbzadd').getValue();
					bssqObj.addType = addType;
					bssqObj.xzqhdmadd1 = Ext.getCmp('xzqhdmadd1').getValue();
					bssqObj.xzqhdmadd2 = Ext.getCmp('xzqhdmadd2').getValue();
					bssqObj.xzqhdmadd3 = Ext.getCmp('xzqhdmadd3').getValue();
					//alert(Ext.getCmp('xzqhdmadd1').getValue());
					if(rootWin.callback){
						rootWin.callback('bssqdata', bssqObj);
					}
					rootWin.hide();
				}
		    },{
		        text:'关闭',
				minWidth:75,
				handler:function(){
					var win = this.findParentByType("addbssqWindow");
					
					win.hide();
				}
		    }]
		});
		this.items = [this.rightPanel];
		Gnt.ux.SelectBssqAdd.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('addbssqWindow', Gnt.ux.SelectBssqAdd);
