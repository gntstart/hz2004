var hhnbidTemp;
var rynbidTemp;
var SelectedArray2=[];
var arrayTemp=[];
var array5=[];
var data;
Gnt.ux.selectBggzDialog = Ext.extend(Ext.Window, {
	title:'变更更正打印选择弹出框',
	closeAction:'hide',
	modal:true,
	width:300,
	height:300,
	layout:'border',
	bgpk: 'bgzid',
	getData: function(){
		var data = {
				"bgset": this.bgset,
				"bg":{}
		}
		var grid = this["grid1"];
		var res = grid.getSelectionModel().getSelections();
		if(res.length>0){
			data.bg["list"] = [];
			Ext.each(res,function(r){
				data.bg["list"].push(r.data);
			});
		}
		return data;
	},
	setDataStore: function(store){
		//设置人员选择数据
		var list = [];
		store.each(function(r){
			var data = r.data;
			list.push(data);
		});
		
		this.setDataList(list);
	},
	setDataList:function(list){
		var store = this["grid1"].store;
		store.removeAll();
		for(var i=0;i<list.length;i++){
			var rr = new store.reader.recordType(list[i]);
			store.add([rr]);
		}
	},
	//人员设置复选数据
	bgset:{},
	createCheckbox:function(config){
		var datatype = config.datatype;
		if(!datatype) datatype = "printset";
		
		if(datatype == "printset"){
			config.listeners = {
					check:function(chk,value){
						var p = chk.findParentByType("bggz_dialog");
						p.printset[chk.name].checked = value;
					}
			};
			var s = this.printset[config.name];
			if(s){
				Ext.apply(config, s);
			}
		}else{
			config.listeners = {
					check:function(chk,value){
						var p = chk.findParentByType("bggz_dialog");
						p.bgset[chk.name].checked = value;
						var chkName= chk.name;
						if(chkName=='bgset_1'){
							if(p.bgset[chkName].checked){
//								funSelected(chkName.slice(6,7),true);
							}else{
//								funSelected(chkName.slice(6,7),false);
							}
						}
					}
			};
			var s = this.bgset[config.name];
			if(s){
				Ext.apply(config, s);
			}
		}
		
		return config;
	},
	createGrid:function(config){
	 	var store = new Ext.data.JsonStore({
	        root: 'list',
	        totalProperty:'totalCount',
	        fields: [
				"bggzxm",
				"bghnr",
				"bglb",
				"bgqnr",
				"bgrq",
				"bgzid",
				"spywid"
	        ]
	    });
		var csm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
		var colModel = new Ext.grid.ColumnModel([
			csm,{
		        header: "变更更正项目",
		        dataIndex: "bggzxm",
		        sortable: true,
				width: 150
		    },{
		        header: "变更后内容",
		        dataIndex: "bghnr",
		        hidden:true,
		        sortable: true,
				width: 100
		    },{
		        header: "变更类别",
		        dataIndex: "bglb",
		        hidden:true,
		        sortable: true,
				width: 100
		    },{
		        header: "变更前内容",
		        dataIndex: "bgqnr",
		        hidden:true,
		        sortable: true,
				width: 100
		    },{
		        header: "变更日期",
		        dataIndex: "bgrq",
		        hidden:true,
		        sortable: true,
				width: 100
		    },{
		        header: "变更更正Id",
		        dataIndex: "bgzid",
		        hidden:true,
		        sortable: true,
				width: 100
		    },{
		        header: "审批业务Id",
		        dataIndex: "spywid",
		        hidden:true,
		        sortable: true,
				width: 100
		    }
		]);
		var grid = new Ext.grid.GridPanel({
	        store: store,
	        region : 'center',
//	        view:new Ext.grid.GridView({
//				forceFit:true,
//				autoFill:true,
//				enableRowBody:true
//			}),
//			height:80,
//			stripeRows: true,
//			hideHeaders : true, 
	        cm: colModel,
	        sm:csm,
			loadMask: {msg:'正在加载数据，请稍侯……'},
			bodyStyle:'width:100%',
	        border:false,
	        anchor:'100% 80%',
		    margins: '0 0 0 0',
			cmargins: '0 0 0 0',
	        frame:false,
			iconCls:'icon-grid',
	        title:'',
	        listeners:{
				rowdblclick:function(g, rowIndex, e){
				}
			}
	    });	
		
		grid.config = config;
		grid.name = config.name;
		this[config.name] = grid;
		
		return grid;
	},
	initComponent : function(){
		Ext.applyIf(this.bgset, {
			"bgset_1":{
				checked: false,
	        	disabled: false
			}
		});
		
		var me = this;
//		var h = this.height-300;
		
		
		var myGrid = this.createGrid({name: 'grid1'});
		
		
		this.items = [{
			region : 'north',
			layout:'column',
			border:false,
			frame:false,
			height: 30,
			bodyStyle:'padding:10px',
			html: '<div id="_PHOTO_ID">请选择需要打印审批表的项目</DIV>'
			/*items:me.createCheckbox({
		    	   columnWidth: 1,
					xtype:'checkbox',
					name: 'bgset_1',
					datatype: 'bgset',
					boxLabel: '请选择需要打印审批表的项目',
					listeners:{
						'rowselect':function(sm,rowIndex,record){
							
						},
						'rowdeselect':function(sm,rowIndex,record){
							
						}
					}
		       })*/
		},myGrid];
		
		
		Gnt.ux.selectBggzDialog.superclass.initComponent.call(this);
	},
    buttons:[{
        text:'确定',
		minWidth:75,
		handler:function(){
			var win = this.findParentByType("bggz_dialog");
			var grid = win['grid1'];
			var selectedRes  = grid.getSelectionModel().getSelections();
			arrayTemp=[];
			for(var i = 0;i<selectedRes.length;i++){
				var o=selectedRes[i].data;
				o.directTable="bgspb";
				arrayTemp.push(o);
			}
			if(win.callback){
				win.callback(arrayTemp,arrayTemp);
			}
			

		}
    },{
        text:'关闭',
		minWidth:75,
		handler:function(){
			var win = this.findParentByType("bggz_dialog");
			win.hide();
		}
    }],
    listeners:{'hide':{fn:function(){
//		this["grid1"].store.removeAll();
	}}}
});
Ext.reg('bggz_dialog', Gnt.ux.selectBggzDialog);

function funSelected(index,flag){
	if(flag){
		dyWindow["grid"+index].getSelectionModel().selectAll();
	}else{
		dyWindow["grid"+index].getSelectionModel().clearSelections();
	}
}

