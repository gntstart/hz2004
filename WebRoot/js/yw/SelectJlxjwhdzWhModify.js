Gnt.ux.SelectJlxjwhdzWhModify= Ext.extend(Ext.Window, {
	title:'街路巷信息修改',
	closeAction:'hide',
	modal:true,
	width:400,
	height:330,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(selRes){
		this.data = selRes.data;
		Gnt.ux.SelectJlxjwhdzWhModify.superclass.show.call(this);
	},
	setSelRes:function(selRes){
		this.selRes = selRes;
		if(selRes){
			this.fs.getForm().setValues({
				jlxdm: selRes.data.jlxdm,
				jwhdm: selRes.data.jwhdm,
				jlxhlx: selRes.data.jlxhlx,
				jlxh: selRes.data.jlxh
			});
		}
	},
	resetData:function(){
		this.fs.getForm().reset();
	},
	initComponent : function(){
		
		var fs = new Ext.form.FormPanel({
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
									xtype:'search_combox',
									anchor:'100%',
									searchUrl:'dict/utils/searchXxb?visiontype=JLXXXB',
									fields:["code","name"],
									valueField: "code",
									displayField: "name",
									allowBlank:false,
									disabled:true,
									fieldLabel:'街路巷代码',
									hiddenName:'jlxdm'
//									hiddenName:'xzqhbdm',
//									name:'xzqhbmc'
								}]
							},{

				                columnWidth:1,
			    	           	layout: 'form',
			    	           	bodyStyle:'padding:5 0 0 0',
			        	       	items: [{
									xtype:'search_combox',
									anchor:'100%',
									searchUrl:'dict/utils/searchXxb?visiontype=JWHXXB',
									fields:["code","name"],
									valueField: "code",
									displayField: "name",
									allowBlank:false,
									disabled:true,
									fieldLabel:'居委会代码',
									hiddenName:'jwhdm'
								}]
							},{
								columnWidth:1,
			    	           	layout: 'form',
			    	           	bodyStyle:'padding:5 0 0 0',
			        	       	items: [{
			        	       		xtype : "combo",
			        	       		store : [['0','单值'],['1','全值'],['2','区间双号'],['3','区间单号'],['4','区间全值']],
			        	       		border:false,
			        	       		frame:false,
			        	       		name:'jlxhlx',
			        	       		id:'jlxhlxM',
			        	       		fieldLabel:"街路巷号类型",
			        	       		width:60,
			        	       		//editable:false,
			        	       		triggerAction:"all",
			        	       		maxHeight : 80,
			        	       		anchor : '100%'
			        	       	}]
		            		},{
				                columnWidth:1,
			    	           	layout: 'form',
			    	           	bodyStyle:'padding:0 0 0 0',
			        	       	items: [{
									xtype:'textfield',
									anchor:'100%',
									name:'jlxh',
//									allowBlank:false,
									fieldLabel:'门楼牌号'
			        	       	}]
							},{
				                columnWidth:1,
			    	           	layout: 'form',
			    	           	bodyStyle:'padding:5 0 0 60',
			        	       	items: [{
			        	       		border:false,
									frame: false,
									anchor:'100%',
									html:'备注:(门楼牌号输入方式：)</br>1、街路巷号类型为单值时,输入单个数值.例如10</br>2、街路巷号类型为全值时,不需要输入信息</br>'+
										'3、街路巷号类型为区间值时(包括区间双值，区间单值，区间全值),输入<区间开始值,区间结束值>,例如<1,100>,如果是区间单值,表示1至100号间全部单号归属特定居委会'
			        	       	}]
							}
						]
					}]
				}]
			}],
	    	buttons:[
	    	 		{
	    	 			text:'确定',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var rootWin = this.findParentByType("modifyjlxjwhdzwh_window");
	    	 				var form = rootWin.items.get(0);
	    	 				if(!form.getForm().isValid()){
	    						showErr("街路巷居委会信息必须填写！");
	    						return;
	    					}
	    	 				var lhdz = form.getForm().getValues();
//							if(rootWin.moreData){
//	    	 					for(o in rootWin.moreData)
//	    	 						lhdz[o] = rootWin.moreData[o];
//	    	 				}
	    	 				lhdz.czid = rootWin.data.czid;
	    	 				lhdz.jlxdm = rootWin.data.jlxdm;
	    	 				lhdz.jwhdm = rootWin.data.jwhdm;
	    	 				if(!lhdz.jlxh){
	    	 					lhdz.jlxh='';
	    	 				}
	    	 				lhdz.jlxhlx = Ext.getCmp('jlxhlxM').getValue();
	    	 				if(rootWin.callback){
								rootWin.callback('lh', lhdz);
							}
							rootWin.hide();
	    	 			}
	    	 		},{
	    	 			text:'取消',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var win = this.findParentByType("modifyjlxjwhdzwh_window");
	    	 				win.hide();
	    	 			}
	    	 		}
	    	 	]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.SelectJlxjwhdzWhModify.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('modifyjlxjwhdzwh_window', Gnt.ux.SelectJlxjwhdzWhModify);
