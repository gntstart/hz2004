var selectRecod = null
Gnt.ux.SelectYwxx = Ext.extend(Ext.Window, {
	title:'业务信息',
	closeAction:'hide',
	modal:true,
	width:600,
	height:300,
	margins:'5',
	layout:'border',
	pageSize:30,
	show:function(){
		Gnt.ux.SelectYwxx.superclass.show.call(this);
	},
	setData:function(hjywid){
		var form = this.form20040
		var ywxxGrid = new Gnt.ux.SjpzGrid({
		pkname: 'hjywid',
		pzlb: '20040',
		url:this.returnUrl,
		region:'center',
		height:500,
		title: '',
		showPaging:true,
		loadCallback: function(res, store, bind_grid){
			Ext.Msg.hide();
			
			if(res.length>0){
				
			}else{
				showInfo("未查到有关信息！");
			}
		},
		listeners:{
			rowclick:function(g, rowIndex, e){},
			rowdblclick:function(g, rowIndex, e){}
		}
	});
	var store = ywxxGrid.store;
	store.baseParams = {
			hjywid:hjywid,
			//config_key:'queryPoHJXX_CZRKJBXXB4',
			start:0,
			limit:20
	};
	store.load();
	store.on("load",function(store) {
		if(store.data.length > 0){
			form.getForm().loadRecord(store.getAt(0));
			Gnt.toFormReadyonly(form);
		}
	},ywxxGrid); 
	},
	resetData:function(){
		
	},
	initComponent : function(){
		if(!Gnt.loadSjpzb("20040",function(){}))
			return;
		var returnUrl = this.returnUrl;
		//alert(returnUrl);
		if(!returnUrl || returnUrl=="") returnUrl = "cx/hjywxx/bggzcx/getYwxx.json";
		this.returnUrl = returnUrl;
		var form20040 = new Gnt.ux.SjpzForm({
			closable: false,
			pzlb: '20040',
			region:'center',
			labelWidth :  120,
			cols:2,
			title: ''
		});
		this.form20040 = form20040;
		this.items = form20040;
		
		Gnt.ux.SelectYwxx.superclass.initComponent.call(this);
	}
    
});
Ext.reg('ywxx_window', Gnt.ux.SelectYwxx);
