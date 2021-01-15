/**
 * 单条地址更改窗口,必须先加载commFrames.js
 */
Gnt.ux.DzggDialog = Ext.extend(Ext.Window, {
	title:'单条地址更改',
	closeAction:'hide',
	modal:true,
	width:800,
	height:210,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(record){
		Gnt.ux.DzggDialog.superclass.show.call(this);
		
		this.dtggForm.getForm().loadRecord(record);
		
		this.moreData = record.data;
		
		this.dtggForm.cols = 1;
//		this.dtggForm.getForm().findField("mlph").columnWidth = 2;
//		this.dtggForm.getForm().findField("mlxz").columnWidth = 2;
		
//		this.dtggForm.getForm().items.items[1].columnWidth = 2;
		
		SetReadOnly(this.dtggForm, 'xzjd', true);
		SetReadOnly(this.dtggForm, 'pcs', true);
		SetReadOnly(this.dtggForm, 'ssxq', true);
		
	},
	resetData:function(){
		this.dtggForm.getForm().reset();
	},
	initComponent : function(){
		
		var dtggForm = new Gnt.ux.SjpzForm({
			pzlb: '20002',
			title:'',
			region:'center',
//			height:180,
			cols:2,
			formType:'query',
			labelWidth :  120,
			buttonAlign: 'center',
			buttons:[
				new Ext.Button({
					text:'保存',
					minWidth:80,
					disabled:true,
					handler:function(){
						var win = this.findParentByType("dtdzgg_window");
    	 				var data = win.dtggForm.getForm().getValues();
    	 				
//    	 				data.pcs.setValue(win.moreData.pcs);
    	 				data.pcs = win.moreData.pcs;
    	 				data.ssxq = win.moreData.ssxq;
    	 				data.xzjd = win.moreData.xzjd;
    	 				
    					if(win.callback){
    						win.callback(data);
    					}
    					win.dtggForm.getForm().reset();
    					win.hide();
    					
					}
				})
				,new Ext.Button({
	    			text:'关闭',
	    			minWidth:80,
	    			handler:function(){
	    				var win = this.findParentByType("dtdzgg_window");
    	 				win.hide();
	    			}
				})
			],
	        isModify:function(flag){
	        	var win = this.findParentByType("dtdzgg_window");
	        	if(flag){
	        		win.dtggForm.buttons[0].setDisabled(false);
	        	}else{
	        		
	        	}
			}
		});
		
//		alert(	dtggForm.grid);
	
		
//		chGrid.colModel.moveColumn(14,2);
		var moreData = null;
		
		this.dtggForm = dtggForm;
		this.items = dtggForm;
		
		Gnt.ux.DzggDialog.superclass.initComponent.call(this);
	}
	
});
Ext.reg('dtdzgg_window', Gnt.ux.DzggDialog);
