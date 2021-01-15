/**
 * 审批材料
 * 
 */
Gnt.store.SpclStore = function(c){
	Gnt.store.SpclStore.superclass.constructor.call(this, Ext.apply(c, {
			root: 'list',
			id:'spclid',
			url: 'yw/spgl/hjsp/querySpcl.json',
			totalProperty:'totalCount',
			fields: ["spclid", "spywid","splx","clbh"]
    }));
};
Ext.extend(Gnt.store.SpclStore, Ext.data.JsonStore);

/**
 * 常住人口store，必须先加载commFrames.js,BggzStore.js
 * 
 * BggzDialog.js替代
 * 
 */
Gnt.ux.SpclGrid = Ext.extend(Ext.grid.GridPanel, {
	title:'审批材料',
	height:200,
	stripeRows: true,
    loadMask: {msg:'正在加载数据，请稍侯……'},
    border:false,
    margins:'0 0 0 5',
	frame:false,
    border:true,
    frame:true,
    getPostParams:function(){
    	return {};
    },
    loadData:function(splx, spywid){
    	this.store.removeAll();
    	this.store.baseParams = {
    			splx: splx,
    			spywid: spywid
    	};
		this.store.load({params:{start:0, limit:this.pagesize}});
    },
	initComponent: function() {
			Gnt.ux.Dict.loadDict(['CS_HJSPCL'],function(){});
		
			this.store=new Gnt.store.SpclStore({});
			this.cm=new Ext.grid.ColumnModel([{
					    header: "审批材料类型",
					    dataIndex: "clbh",	
					    sortable: true,
					    width: 140,
					    renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
							return Gnt.ux.Dict.getDictLable('CS_HJSPCL', value);
					    }
				 }
			 ]);
		
			this.view = new Ext.grid.GridView({
				forceFit:true,
				autoFill:false,
				enableRowBody:true
		    });

			Gnt.ux.SpclGrid.superclass.initComponent.call(this);
	}
});
Ext.reg('SpclGrid', Gnt.ux.SpclGrid);
