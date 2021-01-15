/**
 * 标准地址选择,必须先加载commFrames.js
 */
Gnt.ux.SelectCphbbg = Ext.extend(Ext.Window, {
	title:'成批户别变更',
	closeAction:'hide',
	modal:true,
	width:300,
	height:250,
	margins:'5',
	layout:'fit',
	initComponent : function(){
		Gnt.ux.Dict.loadDict(['CS_HBBGLB','CS_HB','CS_JTHZL'],function(){});
		
		var myPanel = new Ext.form.FormPanel({
	    	height: 100,
	    	region: 'north',
	    	anchor:'100% 100%',
	    	buttonAlign:'right',
	    	labelAlign:'right',
	    	frame:true,
	    	border:false,
	    	fileUpload: true, 
	    	margins:'0',
	        layout:'form',
	        labelAlign: 'top',
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
	        	       					anchor:'100%',
										xtype:'dict_combox',
										dict:'VisionType=CS_HB',
										name:'bdhhbmc',
										maxLength:40,
										hiddenName:'bdhhb',
										allowBlank:false,
										fieldLabel:'变更后户别',
										listeners: {
											select:function(obj){
												var win = this.findParentByType("cphbbg_window");
												if(win.myPanel.getForm().isValid()){
													win.myPanel.buttons[0].enable();
												}else{
													win.myPanel.buttons[0].disable();
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
										dict:'VisionType=CS_JTHZL',
										value:"1",
										name:'jthzlmc',
										maxLength:40,
										hiddenName:'jthzl',
										fieldLabel:'集体户种类'
									}]
								},{
					                columnWidth:1,
				    	           	layout: 'form',
				    	           	bodyStyle:'padding:5 0 0 0',
				        	       	items: [{
	        	       					anchor:'100%',
										xtype:'dict_combox',
										dict:'VisionType=CS_HBBGLB',
										name:'bdyymc',
										maxLength:40,
										hiddenName:'bdyy',
										allowBlank:false,
										fieldLabel:'变动原因',
										listeners: {
											select:function(obj){
												var win = this.findParentByType("cphbbg_window");
												if(win.myPanel.getForm().isValid()){
													win.myPanel.buttons[0].enable();
												}else{
													win.myPanel.buttons[0].disable();
												}
											}
										}
									}]
								}
				]
			}
		],
	    	buttons:[{
	    	 			text:'确定',
	    	 			minWidth:75,
	    	 			disabled:true,
	    	 			handler:function(){
	    	 				var win = this.findParentByType("cphbbg_window");
	    	 				var data = win.myPanel.getForm().getValues();
	    	 				if(data.bdhhb=="7" && !data.jthzl){
	    	 					showErr("居民集体户，必须选择集体户种类！");
	    	 					return;
	    	 				}
	    	 				
	    					if(win.callback){
	    						win.callback(win.myPanel.getForm().getValues());
	    					}
	    					win.myPanel.getForm().reset();
	    					win.hide();
	    	 			}
	    	 		},{
	    	 			text:'取消',
	    	 			minWidth:75,
	    	 			handler:function(){
	    					var win = this.findParentByType("cphbbg_window");
	    					win.myPanel.getForm().reset();
	    					win.hide();
	    	 			}
	    	 		}
	    	 	]
		});
		this.myPanel = myPanel;
		
		this.items = [myPanel];
		Gnt.ux.SelectCphbbg.superclass.initComponent.call(this);
	}
});
Ext.reg('cphbbg_window', Gnt.ux.SelectCphbbg);
