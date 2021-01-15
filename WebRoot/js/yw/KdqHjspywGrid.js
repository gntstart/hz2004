/**
 * 常住人口store，必须先加载commFrames.js,CzrkStore.js
 */
Gnt.ux.KdqHjspywGrid = Ext.extend(Ext.grid.GridPanel, {
	title:'人员基本信息',
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
    loadData:function(data, success_callback, error_callback){
    	this.store.success_callback = success_callback;
    	this.store.error_callback = error_callback;

    	this.store.baseParams = data;
		this.store.load({params:{start:0, limit:this.pagesize}});
    },
	initComponent: function() {
		this.store=new Gnt.store.KdqHjspywStore({
			error_callback:function(){
			},
			success_callback:function(){
				
			},
			url:this.url
	    });
		
		if(!this.cm){
			this.cm=new Ext.grid.ColumnModel([{
				    header: "姓名",
				    dataIndex: "xm",	
				    sortable: true,
				    width: 80
				 },{
				     header: "性别",
				     dataIndex: "xb",	
				     sortable: true,
				     width: 40,
				     renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
							return Gnt.ux.Dict.getDictLable('CS_XB', value);
					 }
				 },{
					    header: "公民身份号码",
					    dataIndex: "gmsfhm",	
					    sortable: true,
					    width: 120
				 },{
					    header: "申请人",
					    dataIndex: "sqrxm",	
					    sortable: true,
					    width: 120
				 },{
					    header: "申请人身份证",
					    dataIndex: "sqrgmsfhm",	
					    sortable: true,
					    width: 80
				 },{
					    header: "受理日期",
					    dataIndex: "slrq",	
					    sortable: true,
					    width: 120
				 },{
					    header: "迁出地区",
					    dataIndex: "kdqqy_qcdqbm",	
					    sortable: true,
					    width: 80
				 }
			 ]);
		}
		
		if(this.showPaging==undefined || this.showPaging==true){
			this.bbar=new Ext.PagingToolbar({
				pageSize: this.pagesize,
				store: this.store,
				displayInfo: true
			});
		}
		
		//这个必须放store定义后，否则不可预料
		if(!this.view){
			this.view = new Ext.grid.GridView({
				forceFit:true,
				autoFill:false,
				enableRowBody:true
		    });
		}
		
		Gnt.ux.KdqHjspywGrid.superclass.initComponent.call(this);
	}
});
Ext.reg('hjspyw_grid', Gnt.ux.KdqHjspywGrid);
