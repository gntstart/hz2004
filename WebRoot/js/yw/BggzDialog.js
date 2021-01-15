/**
 * 
 * 变更更正业务通用
 * 
 */
Gnt.store.BggzStore = function(c){
	Gnt.store.BggzStore.superclass.constructor.call(this, Ext.apply(c, {
			root: 'list',
			id:'tmpid',
			totalProperty:'totalCount',
			fields: ["tmpid", "rynbid","xm","bggzxm_mc","bggzxm", "bggzqnr","bggzqnrTrans","bggzhnr","bggzhnrTrans","bggzlb"],
			listeners:{
				loadexception:function(mystore,options,response,error){
					if(error){
						var json = Ext.decode(response.responseText);
						if(json.messages)
							Ext.Msg.alert("提示",json.messages[0]);
						else
							Ext.Msg.alert("提示",error.message);
					}else{
						Ext.Msg.alert("提示",response.responseText);
					}
				}
			}
    }));
};
Ext.extend(Gnt.store.BggzStore, Ext.data.JsonStore);

/**
 * 常住人口store，必须先加载commFrames.js,BggzStore.js
 */
Gnt.ux.BggzGrid = Ext.extend(Ext.grid.EditorGridPanel, {
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
    loadData:function(data){
    	this.store.baseParams = data;
		this.store.load({params:{start:0, limit:this.pagesize}});
    },
    listeners:{
    	rowclick:function(g, rowIndex, e){
    		var win = g.findParentByType("bggz_dialog");
    		var r = g.store.getAt(rowIndex);

    		var fname = r.data.bggzxm;
    		var field = g.bggzlxEditor;

    		/*
    		 * 
				81	变更信息级别
				
				91	变更其他项目
				92	更正其他项目
    		 */
    		field.filter =function(data){
    			if(fname=="xxjb"){
    				if(data[0]=='81'){
						return true;
					}else{
						return false;
					}
    			}
    			
    			//jgssxq
    			if(fname=="jgssxq"){
    				if(data[0]=='71' || data[0]=='72'){
						return true;
					}else{
						return false;
					}
    			}
    			
    			//csdssxq
    			if(fname=="csdssxq"){
    				if(data[0]=='61' || data[0]=='69'){
						return true;
					}else{
						return false;
					}
    			}
    			
    			if(fname=="mz"){
    				if(data[0]=='51' || data[0]=='52'){
						return true;
					}else{
						return false;
					}
    			}
    			
    			if(fname=="csrq"){
    				if(data[0]=='41' || data[0]=='49'){
						return true;
					}else{
						return false;
					}
    			}
    			
    			if(fname=="xb"){
    				if(data[0]=='31' || data[0]=='32'){
						return true;
					}else{
						return false;
					}
    			}
    			
    			if(fname=="cym"){
    				if(data[0]=='23'){
						return true;
					}else{
						return false;
					}
    			}
    			
    			if(fname=="xm"){
    				if(data[0]=='21' || data[0]=='22'){
						return true;
					}else{
						return false;
					}
    			}
    			
    			if(fname=="gmsfhm"){
    				if(data[0]=='11' || data[0]=='12' || data[0]=='19'){
						return true;
					}else{
						return false;
					}
    			}
    			
				if(data[0]=='91' || data[0]=='92'){
					return true;
				}else{
					return false;
				}
				
    			return false;
    		};
    		field.reloadDict();
    		
    		g.startEditing(rowIndex,4);
		}
    },
	initComponent: function() {
		this.store=new Gnt.store.BggzStore({
			url:this.url
	    });
		
		this.clicksToEdit = 1;
		this.sm = new Ext.grid.CheckboxSelectionModel();
		this.bggzlxEditor = new Gnt.ux.DictComboBox({
	    	dict:"VisionType=CS_BGGZLB"
	    });
		
		if(!this.cm){
			this.cm=new Ext.grid.ColumnModel([{
				    header: "姓名",
				    dataIndex: "xm",	
				    sortable: true,
				    width: 80
				 },{
				     header: "变更更正项目",
				     dataIndex: "bggzxm_mc",	
				     sortable: true,
				     width: 80
				 },{
					    header: "变更前内容",
					    dataIndex: "bggzqnr",	
					    sortable: true,
					    hidden:true,
					    width: 120
				 },{
					    header: "变更前内容",
					    dataIndex: "bggzqnrTrans",	
					    sortable: true,
					    width: 120
				 },
				 {
					    header: "变更后内容",
					    dataIndex: "bggzhnr",	
					    sortable: true,
					    hidden:true,
					    width: 120
				 },
				 {
					    header: "变更后内容",
					    dataIndex: "bggzhnrTrans",	
					    sortable: true,
					    width: 120
				 },{
					    header: "变更更正类别",
					    dataIndex: "bggzlb",	
					    sortable: true,
					    width: 140,
					    editor:this.bggzlxEditor,
					    renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
							return Gnt.ux.Dict.getDictLable('CS_BGGZLB', value);
					    }
				 }
			 ]);
		}
		
		//这个必须放store定义后，否则不可预料
		if(!this.view){
			this.view = new Ext.grid.GridView({
				forceFit:true,
				autoFill:false,
				enableRowBody:true
		    });
		}
		
		Gnt.ux.BggzGrid.superclass.initComponent.call(this);
	}
});
Ext.reg('BggzGrid', Gnt.ux.BggzGrid);

Gnt.ux.BggzDialog = Ext.extend(Ext.Window, {
	title:'确认变更更正项目',
	closeAction:'hide',
	modal:true,
	width:600,
	height:300,
	margins:'5',
	layout:'fit',
	buttons:[
				new Ext.Button({
						text:'确定',
						minWidth:75,
						handler:function(){
							var win = this.findParentByType("bggz_dialog");
							var voBggzxx = Gnt.yw.getBggzSubmitdata( (win.hcyGrid?win.hcyGrid:hcyGrid), win.grid.store);
							if(win.submitData){
								win.submitData(voBggzxx);
							}else{
								submitData(voBggzxx);
							}
							
							win.hide();
						}
				}),
				new Ext.Button({
					text:'取消',
					minWidth:75,
					handler:function(){
						var win = this.findParentByType("bggz_dialog");
						win.hide();
					}
			})
	],
	initComponent : function(){
		this.grid = new Gnt.ux.BggzGrid({title:''});
		this.items = this.grid;
		
		Gnt.ux.BggzDialog.superclass.initComponent.call(this);
	}
});
Ext.reg('bggz_dialog', Gnt.ux.BggzDialog);