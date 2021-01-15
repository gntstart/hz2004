Gnt.ux.SelectJttdWhModify= Ext.extend(Ext.Window, {
	title:'集体土地标识修改',
	closeAction:'hide',
	modal:true,
	width:300,
	height:200,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(selRes,type){
		this.type = type;
		this.data = selRes.data;
		
		Gnt.ux.SelectJttdWhModify.superclass.show.call(this);
	},
	setSelRes:function(selRes,type){
		this.selRes = selRes;
		if(type=='single'){
			if(selRes){
				this.fs.getForm().setValues({
					hhnbid: selRes.data.hhnbid,
					jttdbz: selRes.data.jttdbz=='2'?selRes.data.jttdbz:''
				});
			}
		}else if(type=='piliang'){
			if(selRes){
				this.fs.getForm().setValues({
					hhnbid: '',
					jttdbz: ''
				});
			}
		}
		
	},
	resetData:function(){
		this.fs.getForm().reset();
	},
	initComponent : function(){
		var jttdStore = new Ext.data.SimpleStore({
			id:0,
			fields:[{name: 'code', mapping: 0},{name: 'name', mapping: 1}],
		   	data:[['1','公共户'],['2','集体土地']]
		});
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
							name:'hhnbid',
							hidden:true
						}]
					},{
		                columnWidth:1,
	    	           	layout: 'form',
	    	           	bodyStyle:'padding:5 0 0 0',
	        	       	items: [{
							hiddenName:'jttdbz',
							anchor:'100%',
							xtype:'combo',
							fieldLabel:'状态',
							mode: 'local',
	            			triggerAction: 'all',
							valueField:"code",
	      					displayField:"name",
							selectOnFocus:true,
							emptyText : '请选择',
							typeAhead: true,  
							editable:false,
							forceSelection: true,
				    		forceSelection: true, 
	    					blankText:'请选择',
	            			lazyRender:true,
	            			store:[['','请选择'],['2','集体土地']]
						}]
					}
				]
			}],
	    	buttons:[
	    	 		{
	    	 			text:'确定',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var rootWin = this.findParentByType("modifyJttdwh_window");
	    	 				var form = rootWin.items.get(0);
	    	 				var lhdz = form.getForm().getValues();
	    	 				if(rootWin.type=='single'){
	    	 					lhdz.hhnbid=rootWin.data.hhnbid;
	    	 					if(lhdz.jttdbz==rootWin.data.jttdbz){
	    	 						alert("土地标识未发生变动，请确认！");
	    	 						return;
	    	 					}
	    	 				}else if(rootWin.type=='piliang'){
	    	 					var temp = {};
	    	 					temp = rootWin.data;
	    	 					temp.jttdbzType = lhdz.jttdbz;
	    	 					lhdz = temp;
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
	    	 				var win = this.findParentByType("modifyJttdwh_window");
	    	 				win.hide();
	    	 			}
	    	 		}
	    	 	]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.SelectJttdWhModify.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('modifyJttdwh_window', Gnt.ux.SelectJttdWhModify);
