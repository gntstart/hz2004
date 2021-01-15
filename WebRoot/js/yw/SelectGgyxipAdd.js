var addType =1;
Gnt.ux.SelectGgyxipAdd= Ext.extend(Ext.Window, {
	title:'公共允许IP增加',
	closeAction:'hide',
	modal:true,
	width:400,
	height:250,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(type){
		this.type = type;
		Gnt.ux.SelectGgyxipAdd.superclass.show.call(this);
	},
	setSelRes:function(){
		Ext.getCmp('r11').setValue(true);
		Ext.getCmp('r12').setValue(false);
		Ext.getCmp('ipdizhi1').setValue();
		Ext.getCmp('ipdizhi2').setValue();
		Ext.getCmp('ipdizhi3').setValue();
//		this.fs.getForm().setValues({
//			mbmc: '',
//			mbdj: ''
//		});
	},
	resetData:function(){
		
	},
	initComponent : function(){
		this.rightPanel = new Ext.Panel({
			region : 'east',
			//width:'40%',
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
		            	boxLabel: '1-单IP地址',
		            	name:'fs',
		            	inputValue : "1",
		            	checked:true,
		            	listeners:{
		            		'check' : function(checkbox, checked){ 
		                         if(checked){//只有在点击时触发
		                        	 addType = 1;
		                        	 Ext.getCmp('addType1').show();
		                        	 Ext.getCmp('addType2').hide();
		                         }  
		                    }
		                //监听事件
			            }
		            },{
			            	id:'r12',
			            	xtype:'radio',
			            	boxLabel: '2-多IP地址',
			            	columnWidth: 0.5,
			            	name:'fs',
			            	inputValue : "2",
			            	listeners:{
			            		'check' : function(checkbox, checked){ 
			                         if(checked){//只有在点击时触发
			                        	 addType = 2;
			                        	 Ext.getCmp('addType1').hide();
			                        	 Ext.getCmp('addType2').show();
			                         }  
			                    }
			                //监听事件
				            }
			            }]
				},{
	            	id:'southId',
	            	title: '',
	            	region:'south',
		            border:false,
					frame:false,
					width:'100%',
					//height:20,
		            autoHeight : true, 
		            items: [
		            	{
		            		xtype: 'fieldset',
		            		border:false,
		 					frame:false,
		 					id:'addType1',
		 					items:[{id:'ipdizhi1',
		            			xtype : 'textfield',
		            			border:false,
		            			frame:false,
		            			fieldLabel:"IP地址",
		            			autoCreate: {tag: 'input', type: 'text', size: '20', autocomplete: 'off', maxlength: '19'},
		            			anchor : '98%',
		            			name:'ipdizhi1'
		 					}]
		            		
		            	},{
		            		xtype: 'fieldset',
		            		border:false,
		 					frame:false,
		 					id:'addType2',
		 					hidden:true,
		 					items:[{id:'ipdizhi2',
		            			xtype : 'textfield',
		            			border:false,
		            			frame:false,
		            			fieldLabel:"起始IP地址",
		            			autoCreate: {tag: 'input', type: 'text', size: '20', autocomplete: 'off', maxlength: '19'},
		            			anchor : '98%',
		            			name:'ipdizhi2'
		            		},{
			            		id:'ipdizhi3',
		            			xtype : 'textfield',
		            			border:false,
		            			frame:false,
		            			fieldLabel:"结束IP地址",
		            			autoCreate: {tag: 'input', type: 'text', size: '20', autocomplete: 'off', maxlength: '19'},
		            			anchor : '98%',
		            			name:'ipdizhi3'
			            	}]
		            	}
		            ]
				}
			],
			buttons:[{
		        text:'确定',
				minWidth:75,
				handler:function(){
					var rootWin = this.findParentByType("addggyxipWindow");
					var ipdzArray= [];
					if(addType==1){
						ipdzArray=[Ext.getCmp('ipdizhi1').getValue()];
					}else if(addType==2){
						ipdzArray=Ext.getCmp('ipdizhi2').getValue()+","+Ext.getCmp('ipdizhi3').getValue();
					}
					var lhdz = {};
					lhdz.ipdzArray = ipdzArray;
					if(rootWin.type=='1'){
						lhdz.yhid = user.yhid;	
					}else if(rootWin.type=='2'){
						lhdz.yhid = selectedYhSelRes.data.yhid;	
					}
					if(rootWin.callback){
						rootWin.callback('backdata', lhdz);
					}
					rootWin.hide();
				}
		    },{
		        text:'关闭',
				minWidth:75,
				handler:function(){
					var win = this.findParentByType("addggyxipWindow");
					
					win.hide();
				}
		    }]
		});
		
		this.items = [this.rightPanel];
		Gnt.ux.SelectGgyxipAdd.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('addggyxipWindow', Gnt.ux.SelectGgyxipAdd);
