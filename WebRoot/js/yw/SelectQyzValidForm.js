/**
 * 通用form
 */
var flagvalid =null;
var	arrayvalid =null;
var	numvalid=null;
var datavalid =null;
Gnt.ux.QyzvalidjForm = Ext.extend(Ext.form.FormPanel, {
	buttonAlign : 'right',
	labelAlign : 'right',
	layout : 'form',
	labelWidth : 80,
	region:'center',
	initComponent : function(){
		Gnt.ux.Dict.loadDict(['CS_SPJG'],function(){});
		var map=[];
		var date=new Date;
		var year=date.getFullYear(); 
		for(var i = year;i>year-50;i--){
			var obj=[];
			obj[0] = i;
			obj[1] = i;
			map.push(obj);
		}
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
					id:'qyztips',
					hidden:true,
					bodyStyle:'padding:10px,width:100%',
					html: '<div>要换另外一张迁移证，请换迁移证号</DIV>'
				},{
						layout : 'form',
						columnWidth : 0.5,
						defaultType : 'textfield',
						bodyStyle : 'width:100%',
						items : [{
							id:'yznf_new',
		            		xtype : "combo",
		            		store : map,
	            			border:false,
	            			frame:false,
	            			fieldLabel:"印制年份",
	            			width:60,
	            			//editable:false,
	            			triggerAction:"all",
	            			maxHeight : 80,
	            			anchor : '99%'
		            	}]
					}, {
						layout: 'form',
						columnWidth: 0.5,
						defaultType: 'textfield',
						bodyStyle:'width:100%',	            
						items: [ {
							id:'qyzbh_new',
							name: 'qyzbh_new',
							anchor:'99%',
							xtype: 'textfield',
							autoCreate: {tag: 'input', type: 'text', size: '20', autocomplete: 'off', maxlength: '8'},
							fieldLabel: '迁移证编号'
						}]
					}],
					buttons: [{
						id: 'btn-query',
						name: 'btn-query',
			            text: '确定',
						handler:function(){
							Ext.getCmp('qyzbh').setValue(Ext.getCmp('qyzbh_new').getValue());
							Ext.getCmp('yznf').setValue(Ext.getCmp('yznf_new').getValue());
							validFunction(Ext.getCmp('yznf_new').getValue(),Ext.getCmp('qyzbh_new').getValue(),flagvalid,arrayvalid,numvalid,datavalid);
							Ext.getCmp('qyztips').hide();
							qyzWin.hide();
						}
			        },{
						id: 'btn-query',
						name: 'btn-query',
			            text: '取消',
						handler:function(){
							Ext.getCmp('qyzbh').setValue(Ext.getCmp('qyzbh_new').getValue());
							Ext.getCmp('yznf').setValue(Ext.getCmp('yznf_new').getValue());
							Ext.getCmp('qyztips').hide();
			        		qyzWin.hide();
			        		
						}
			        }]
		}];
		
		Gnt.ux.QyzvalidjForm.superclass.initComponent.call(this);
	}
});
Ext.reg('spdj_form', Gnt.ux.QyzvalidjForm);
var spdj_form = new Gnt.ux.QyzvalidjForm({
	callback: function(zjlx,zqzbh,array){
	}
});
var qyzWin = new Ext.Window({
	title:'迁移证信息设定',
	closeAction:'hide',
	modal:true,
	width:500,
	height:120,
	layout:'fit',
	items:spdj_form,
	listeners:{
		show:function(){
		}
	}
});
function qyzValid(yznf,qyzbh,flag,array,num,data){
	Ext.getCmp('qyzbh_new').setValue(addPreZero(parseInt(parseInt(qyzbh,10)+1)));
	Ext.getCmp('yznf_new').setValue(yznf);
	flagvalid =flag;
	arrayvalid =array;
	numvalid=num;
	datavalid =data;
	if(num>0){
		Ext.getCmp('qyztips').show();
	}
	qyzWin.show();

}
function addPreZero(num){  return ('0000000'+num).slice(-8); } 
