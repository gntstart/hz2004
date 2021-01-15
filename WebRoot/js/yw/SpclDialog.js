Gnt.ux.SpclDialog = Ext.extend(Ext.Window, {
	title:'选择审批信息',
	closeAction:'hide',
	modal:true,
	width:500,
	height:320,
	margins:'5',
	layout:'border',
	reset:function(){
		this.mxfs.getForm().reset();
	},
	buttons:[
				new Ext.Button({
						text:'确定',
						minWidth:75,
						handler:function(){
							var win = this.findParentByType("spcl_dialog");
							var data = win.mxfs.getForm().getValues();
							
							win.hide();
							if(win.callback){
								win.callback(data);
							}
						}
				}),
				new Ext.Button({
					text:'取消',
					minWidth:75,
					handler:function(){
						var win = this.findParentByType("spcl_dialog");
						win.hide();
					}
			})
	],
	show:function(){
		//不显示，直接回调
		this.callback({
			
		});
	},
	initComponent : function(){
		mxfs = new Ext.form.FormPanel({
			title : '',
			anchor : '100% 100%',
			buttonAlign : 'right',
			labelAlign : 'right',
			frame : true,
			width:500,
			border : true,
			layout : 'form',
			labelWidth : 95,
			bodyStyle : 'padding:0 0 0 0',
			items : [ {
						layout : 'column',
						frame : false,
						border : false,
						defaults : {
							border : false,
							frame : false
						},
						items : [{
									columnWidth : 1,
									layout : 'form',
									bodyStyle : 'padding:0 0 0 0',
									items : [
										new Ext.form.CheckboxGroup({
											anchor : '100%',
											height: 120,
											fieldLabel: '选择审批材料',
											allowBlank:false,
											columns: 1,
											items:[ 
										       {boxLabel: '户口簿', name: 'cl' ,inputValue:'1000',height:25},
										       {boxLabel: '身份证', name: 'cl',inputValue:'1001',height:25},
										       {boxLabel: '准迁证', name: 'cl',inputValue:'1002',height:25},
										       {boxLabel: '迁移证', name: 'cl' ,inputValue:'1003',height:25}
											]
										})
									]
								},{
									columnWidth : 1,
									layout : 'form',
									bodyStyle : 'padding:0 0 0 0',
									items : [{
												xtype : 'textarea',
												allowBlank : false,
												anchor : '100%',
												height: 50,
												name : 'fy',
												fieldLabel : '附言'
											}]
							}]
				}]
		});
		
		this.mxfs = mxfs;
		this.items = [{
			layout:'fit',
			region:'center',
			items:mxfs
		}];
		
		Gnt.ux.SpclDialog.superclass.initComponent.call(this);
	}
});
Ext.reg('spcl_dialog', Gnt.ux.SpclDialog);