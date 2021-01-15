/**
 * 通用form
 */
var directTablevalid =null;
var	arrayvalid =null;
var	numvalid=null;
var datavalid =null;
Gnt.ux.ZqzvalidjForm = Ext.extend(Ext.form.FormPanel, {
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
			bodyStyle : 'padding:5px 20px 0px 0px',
			defaults : {
				frame:false,
				border:false,
				labelWidth : this.labelWidth?this.labelWidth:100,
				bodyStyle : 'padding:0px 0px 0px 0px'
			},
			items : [
				{
					layout : 'form',
					columnWidth : 1,
					defaultType : 'textfield',
					id:'zqztips',
					hidden:true,
					bodyStyle:'padding:10px,width:100%',
					html: '<div>要换另外一张准迁证，请换准迁证号</DIV>'
				},{
						layout: 'form',
						columnWidth: 1,
						defaultType: 'textfield',
						bodyStyle:'width:100%',	            
						items: [ {
							id:'zqzbh_new',
							name: 'zqzbh_new',
							anchor:'99%',
							xtype: 'textfield',
							autoCreate: {tag: 'input', type: 'text', size: '20', autocomplete: 'off', maxlength: '8'},
							fieldLabel: '准迁证编号'
						}]
					}],
					buttons: [{
						id: 'btn-query',
						name: 'btn-query',
			            text: '确定',
						handler:function(){
							Ext.getCmp('zqzbh').setValue(Ext.getCmp('zqzbh_new').getValue());
							arrayvalid[numvalid].zqzbh =Ext.getCmp('zqzbh_new').getValue();
							validzqzbh(directTablevalid,arrayvalid[numvalid].zjlx,Ext.util.JSON.encode(arrayvalid[numvalid].obj),numvalid,arrayvalid,arrayvalid[numvalid].zqzbh,arrayvalid[numvalid].obj.spywid);
							Ext.getCmp('zqztips').hide();
							xzWin.hide();
						}
			        },{
						id: 'btn-query',
						name: 'btn-query',
			            text: '取消',
						handler:function(){
							Ext.getCmp('zqzbh').setValue(Ext.getCmp('zqzbh_new').getValue());
							Ext.getCmp('zqztips').hide();
			        		xzWin.hide();
			        		
						}
			        }]
		}];
		
		Gnt.ux.ZqzvalidjForm.superclass.initComponent.call(this);
	}
});
Ext.reg('spdj_form', Gnt.ux.ZqzvalidjForm);
var spdj_form = new Gnt.ux.ZqzvalidjForm({
	callback: function(zjlx,zqzbh,array){
	}
});
var xzWin = new Ext.Window({
	title:'准迁证信息设定',
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
function zqzValid(directTable,zqzbh,array,num,data){
	Ext.getCmp('zqzbh_new').setValue(addPreZero(parseInt(parseInt(zqzbh,10)+1)));
	directTablevalid =directTable ;
	arrayvalid =array;
	numvalid=num;
	datavalid =data;
	if(num>0){
		Ext.getCmp('zqztips').show();
	}
	xzWin.show();

}
function addPreZero(num){  return ('0000000'+num).slice(-8); } 
