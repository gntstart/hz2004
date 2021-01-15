/**
 * 通用form
 */
Gnt.ux.SpdjForm = Ext.extend(Ext.form.FormPanel, {
	buttonAlign : 'right',
	labelAlign : 'right',
	layout : 'form',
	labelWidth : 120,
	region:'center',
	initComponent : function(){
		Gnt.ux.Dict.loadDict(['CS_SPJG'],function(){});
		
		this.items = [{
			layout : 'column',
			frame : false,
			border : false,
			//奇怪，重点所在，否则columnWidth不起作用
			bodyStyle : 'padding:5px 20px 0px 0px',
			defaults : {
				frame:false,
				border:false,
				labelWidth : this.labelWidth?this.labelWidth:100,
				bodyStyle : 'padding:0px 0px 0px 0px'
			},
			items : [
					{
						columnWidth : 0.5,
						layout : 'form',
						bodyStyle : 'padding:5 0 0 10',
						items : [{
							xtype : 'dict_combox',
							dict:"VisionType=CS_SPJG&def=1&ignore=false",
							anchor : '100%',
							name : '_text_czjg',
							hiddenName: 'czjg',
							allowBlank: false,
							fieldLabel : '<spen style="color:red">*审批结果</span>'
						}]
					}, {
						columnWidth : 1,
						layout : 'form',
						bodyStyle : 'padding:5 0 0 10',
						items : [{
									xtype : 'textarea',
									height:80,
									anchor : '100%',
									name : 'czyj',
									/**
										2018.11.07
										修改	刁杰
									 */
//									emptyText: '限50字',
									fieldLabel : '审批意见(限50字)'
						}]
				}]
		}];
		
		Gnt.ux.SpdjForm.superclass.initComponent.call(this);
	}
});
Ext.reg('spdj_form', Gnt.ux.SpdjForm);