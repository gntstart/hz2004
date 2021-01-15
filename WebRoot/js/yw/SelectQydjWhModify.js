Gnt.ux.SelectQydjWhModify= Ext.extend(Ext.Window, {
	title:'冻结状态修改',
	closeAction:'hide',
	modal:true,
	width:300,
	height:200,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(hhnbid,type){
		this.type = type;
		this.hhnbid = hhnbid;
		
		Gnt.ux.SelectQydjWhModify.superclass.show.call(this);
	},
	setSelRes:function(selRes,type){
		this.selRes = selRes;
		if(type=='single'){
			if(selRes){
				this.fs.getForm().setValues({
					djzt: selRes.data.djzt
				});
			}
		}else if(type=='piliang'){
			this.fs.getForm().setValues({
				djzt: ''
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
		                columnWidth:1,
	    	           	layout: 'form',
	    	           	bodyStyle:'padding:5 0 0 0',
	        	       	items: [{
							name:'rynbid',
							hidden:true
						}]
					},{
		                columnWidth:1,
	    	           	layout: 'form',
	    	           	bodyStyle:'padding:5 0 0 0',
	        	       	items: [{
	       					anchor:'100%',
							xtype:'dict_combox',
							dict:'VisionType=CS_DJZT',
							name:'djzt',
							maxLength:40,
							hiddenName:'djzt',
							allowBlank:false,
							fieldLabel:'冻结状态'
						}]
					}
				]
			}],
	    	buttons:[
	    	 		{
	    	 			text:'确定',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var rootWin = this.findParentByType("modifyQydjwh_window");
	    	 				var form = rootWin.items.get(0);
	    	 				if(!form.getForm().isValid()){
	    						showErr("数据字典必须填写！");
	    						return;
	    					}
	    	 				var lhdz = form.getForm().getValues();
	    	 				if(rootWin.type=='single'){
	    	 					lhdz.rynbid=rootWin.data.rynbid;
	    	 				}else if(rootWin.type=='piliang'){
	    	 					var temp = {};
	    	 					temp = rootWin.selRes;
	    	 					temp.djztType = lhdz.djzt;
	    	 					lhdz = temp;
	    	 					if(rootWin.selRes.gmsfhm){
	    	 						lhdz.hhnbid=rootWin.hhnbid;
	    	 					}
	    	 					
	    	 				}else{
	    	 					alert('土地标志类型不匹配！');
	    	 					return;
	    	 				}
	    	 				
//							if(rootWin.moreData){
//	    	 					for(o in rootWin.moreData)
//	    	 						lhdz[o] = rootWin.moreData[o];
//	    	 				}
	    	 				if(rootWin.callback){
	    	 					rootWin.callback(rootWin.type, lhdz);
							}
							rootWin.hide();
	    	 			}
	    	 		},{
	    	 			text:'取消',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var win = this.findParentByType("modifyQydjwh_window");
	    	 				win.hide();
	    	 			}
	    	 		}
	    	 	]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.SelectQydjWhModify.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('modifyQydjwh_window', Gnt.ux.SelectQydjWhModify);
