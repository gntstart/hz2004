/**
 * 标准地址选择,必须先加载commFrames.js
 */
var win = null;
var store = null;
var selectRecod = null
Gnt.ux.BzlsDialog = Ext.extend(Ext.Window, {
	title:'受理信息操作日志信息',
	closeAction:'hide',
	modal:true,
	width:600,
	height:550,
	margins:'5',
	layout:'border',
	pageSize:30,
	resetData:function(){
		this.grid30028.store.removeAll();
	},
	setSelRes:function(selRes){
		var grid = this.grid30028;
		var store = grid.store;
		store.baseParams = {
			slh:selRes.data.slh,
			start:0,
			limit:20
		};
		store.load();
		store.on("load",function(store) {  
			if(store.data.length > 0){
				curIndex = 0;
				grid.fireEvent("rowclick",grid,curIndex);
				grid.getSelectionModel().selectRange(curIndex,curIndex);
			}
		},grid); 
	},
	initComponent : function(){
		if(!Gnt.loadSjpzb("30028",function(){}))
			return;
		var returnUrl = this.returnUrl;
		if(!returnUrl || returnUrl=="") returnUrl = "cx/edzjxx/edzslcx/getEdzsllsxxlist.json";
		this.returnUrl = returnUrl;
		this.grid30028 = new Gnt.ux.SjpzGrid({
			title:'',
			region : 'north',
			url: returnUrl,
			pzlb: '30028',
			pkname: 'zjywid',
			height:200,
			showPaging:true,
			listeners:{
				rowclick:function(g, rowIndex, e){
					selectRecod = g.store.getAt(rowIndex);
					var win = this.findParentByType("bzls_window");
					var ywbz = selectRecod.data.ywbz;
					if(ywbz=='F3301'){
						//显示数据
						Gnt.submit(
								{slh:selRes.data.slh}, 
								"cx/edzjxx/edzslcx/getEdzsllsxxInfo.json", 
								"正在查询办证历史信息，请稍后...", 
								function(jsonData,params){
									if(jsonData.list){
										var res= {};
										res.data =jsonData.list[0];
										win.downForm.getForm().loadRecord(res);
										Gnt.toFormReadyonly(win.downForm);
									}
									
								}
						);
					}else{
						//清除form
						var res= {};
						res.data={};
						win.downForm.getForm().reset();	
					}
				}
			}
		});
		this.downForm = new Gnt.ux.SjpzForm({
			closable: false,
			/*region:'north',*/
			//height:500,
			pzlb: '30020',
			readOnly:true,
			labelWidth : 120,
			cols:2,
			changeDictCust:function(cmb,res){
				
				return;
			},
			title: ''
		});
		this.rightPanel = new Ext.Panel({
			region:'south',
			buttons:[{
		        text:'关闭',
				minWidth:75,
				handler:function(){
					var win = this.findParentByType("bzls_window");
					win.hide();
				}
		    }]
		});		
		this.items = [this.grid30028,this.downForm,this.rightPanel];
		
		Gnt.ux.BzlsDialog.superclass.initComponent.call(this);
	}
    
});
Ext.reg('bzls_window', Gnt.ux.BzlsDialog);
