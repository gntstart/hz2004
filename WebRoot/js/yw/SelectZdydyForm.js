/**
 * 通用form
 */
Gnt.ux.ZdyModulForm = Ext.extend(Ext.form.FormPanel, {
	buttonAlign : 'right',
	labelAlign : 'right',
	layout : 'form',
	labelWidth : 80,
	region:'center',
	initComponent : function(){
		this.items = [{
			layout : 'column',
			frame : false,
			border : false,
			//奇怪，重点所在，否则columnWidth不起作用
			bodyStyle : 'padding:10px 20px 0px 0px',
			defaults : {
				frame:false,
				border:false,
				labelWidth : this.labelWidth?this.labelWidth:100,
				bodyStyle : 'padding:0px 0px 0px 0px'
			},
			items : [
					{
						layout : 'form',
						columnWidth : 1.0,
						defaultType : 'textfield',
						bodyStyle : 'width:100%',
						items : [{
							id:'moduleType',
		            		xtype : "combo",
		            		store : [['czrkdjbqt','带边框常表B5（加条形码）'],['czrkdjbq','常表人口登记表全打'],['czrkdjbt','常住表套打（加条形码）'],['czrkdjbbmtd','常表背面套打'],
		            			['czrkdjbbmtdnv','常表背面套打_女'],['czrkdjbbmtdnan','常表背面套打_男'],['czrkdjbbmzd','常表背面整打'],['czrkdjbtbb','常表套打自定义'],['cbtdqcwb','常表套打全程网办'],['cbqdqcwb','常表全打全程网办']],
	            			border:false,
	            			frame:false,
	            			fieldLabel:"请选择要打印的报表",
	            			labelWidth:150,
	            			//width:'100%',
	            			//editable:false,
	            			triggerAction:"all",
	            			maxHeight : 200,
	            			anchor : '100%'
		            	}]
					}],
					buttons: [{
						id: 'btn-query',
						name: 'btn-query',
			            text: '确定',
						handler:function(){
							if(Ext.getCmp('moduleType').getValue()){
								zdyWin.hide();
								addArray5(Ext.getCmp('moduleType').getValue(),Ext.getCmp('moduleType').getRawValue());
							}else{
								alert("请选择需要的模板项,再点击确认按钮!");
								return;
							}
							
						}
			        },{
						id: 'btn-query',
						name: 'btn-query',
			            text: '取消',
						handler:function(){
							zdyWin.hide();
						}
			        }]
		}];
		
		Gnt.ux.ZdyModulForm.superclass.initComponent.call(this);
	}
});
Ext.reg('spdj_form', Gnt.ux.ZdyModulForm);
var spdj_form = new Gnt.ux.ZdyModulForm({
	callback: function(zjlx,zqzbh,array){
	}
});
var zdyWin = new Ext.Window({
	title:'查询报表打印',
	closeAction:'hide',
	modal:true,
	width:300,
	height:120,
	layout:'fit',
	items:spdj_form,
	listeners:{
		show:function(){
		}
	}
});

