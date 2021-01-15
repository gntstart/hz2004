var selBxszd= null;
var selXszd = null;
var selPxzd = null;
var selBxszdRow = null;
var selXszdRow = null;
var selPxzdRow = null;
//查询户信息 -》查询人信息 -》选择人信息
Gnt.ux.ZdycxPanel = Ext.extend(Ext.Panel, {
	margins:'5px 5px 5px 5px',
	region : 'center',
	autoScroll : true,
	title : '',
	buttonAlign : 'left',
	labelAlign : 'right',
	frame: false,
	border:  false,
	initComponent: function() {
		var that = this;
		var bxszdcsm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
		var bxszdcolModel = new Ext.grid.ColumnModel([
			{
				header: "数据配置id",
		        dataIndex: "sjpzid",
		        sortable: true,
		        hidden:true,
				width: 40
			},{
				header: "",
		        dataIndex: "pzlb",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "pzmc",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "id",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "fieldname",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "不显示字段",
		        dataIndex: "displayname",
		        sortable: true,
				width: 150
			},{
				header: "",
		        dataIndex: "fieldtype",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "fieldtypename",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "dsname",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "readonly",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "visibletype",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "fieldlength",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "displayfieldlength",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "ischinese",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "mbmod",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "allowselfinput",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "codefield",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "pyfield",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "namefield",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "partmask",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "lsfield",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "showls",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "enablecopy",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "enabledefaultfilter",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "enterdropdown",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "enteruniqueexit",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "usetablefiltered",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "useforsfz",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "conditionoperator",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "conditionfield",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "conditiontype",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "groups",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "bdlx",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "vtype",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "pxid",
		        sortable: true,
				width: 40,
				hidden:true
			}
		]);
		var bxszdStore = new Ext.data.JsonStore({
	 		proxy: new Ext.data.HttpProxy({
	 			url: "dict/utils/querySjpzbGz?flag=" +1,
				method: "POST",
				disableCaching: true
		    }),
	        root: 'list',
	        id:'sjpzid',
	        totalProperty:'totalCount',
	        fields: [ "sjpzid", "pzlb", "pzmc", "id", "fieldname",
				"displayname", "fieldtype", "fieldtypename", "dsname",
				"readonly", "visibletype", "fieldlength",
				"displayfieldlength", "ischinese", "mbmod",
				"allowselfinput", "codefield", "pyfield", "namefield",
				"partmask", "lsfield", "showls", "enablecopy",
				"enabledefaultfilter", "enterdropdown", "enteruniqueexit",
				"usetablefiltered", "useforsfz", "conditionoperator",
				"conditionfield", "conditiontype", "groups", "bdlx",
				"vtype","pxid" ],
	        listeners:{
				loadexception:function(mystore,options,response,error){
					if(error){
						var json = Ext.decode(response.responseText);
						Ext.Msg.alert("提示",json.messages[0]);
					}else{
						Ext.Msg.alert("提示",response.responseText);
					}
				}
	        }
	    });
		that.bxszdGrid =new Ext.grid.GridPanel({
	        store: bxszdStore,
	        region: 'center',
	        view:new Ext.grid.GridView({
					//forceFit:true,
					//autoFill:true,
					enableRowBody:true
			}),
			stripeRows: true,
	        cm: bxszdcolModel,
	        sm:bxszdcsm,
			loadMask: {msg:'正在加载数据，请稍侯……'},
			bodyStyle:'width:80%',
	        border:true,
	        anchor:'100% 100%',
		    margins: '0 0 0 0',
			cmargins: '0 0 0 0',        
	        frame:false,
			iconCls:'icon-grid',
	        title:'',
	        listeners:{
	        	rowclick:function(g, rowIndex, e){
	        		selBxszd = g.store.getAt(rowIndex);
	        		selBxszdRow= rowIndex;
	        		Ext.getCmp('ydsjBtn1').enable();
	        	},
				rowdblclick:function(g, rowIndex, e){
					selBxszd = g.store.getAt(rowIndex);
					selBxszdRow= rowIndex;
					Ext.getCmp('ydsjBtn1').enable();
					var win = this.findParentByType("zdycx_panel");
					var xszdStore = win.items.items[1].items.items[0].items.items[0].items.items[0].store;
					xszdStore.add(selBxszd);
					bxszdStore.remove(selBxszd);
				}
			}
	    });
		
		var xszdcsm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
		var xszdcolModel = new Ext.grid.ColumnModel([
			{
				header: "数据配置id",
		        dataIndex: "sjpzid",
		        sortable: true,
		        hidden:true,
				width: 40
			},{
				header: "",
		        dataIndex: "pzlb",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "pzmc",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "id",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "fieldname",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "显示字段",
		        dataIndex: "displayname",
		        sortable: true,
				width: 150
			},{
				header: "",
		        dataIndex: "fieldtype",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "fieldtypename",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "dsname",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "readonly",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "visibletype",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "fieldlength",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "displayfieldlength",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "ischinese",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "mbmod",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "allowselfinput",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "codefield",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "pyfield",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "namefield",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "partmask",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "lsfield",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "showls",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "enablecopy",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "enabledefaultfilter",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "enterdropdown",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "enteruniqueexit",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "usetablefiltered",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "useforsfz",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "conditionoperator",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "conditionfield",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "conditiontype",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "groups",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "bdlx",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "vtype",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "pxid",
		        sortable: true,
				width: 40,
				hidden:true
			}
		]);
		var xszdStore = new Ext.data.JsonStore({
	 		proxy: new Ext.data.HttpProxy({
	 			url: "dict/utils/querySjpzbGz?flag=" +2,
				method: "POST",
				disableCaching: true
		    }),
	        root: 'list',
	        id:'pxid',
	        totalProperty:'totalCount',
	        fields: [ "sjpzid", "pzlb", "pzmc", "id", "fieldname",
				"displayname", "fieldtype", "fieldtypename", "dsname",
				"readonly", "visibletype", "fieldlength",
				"displayfieldlength", "ischinese", "mbmod",
				"allowselfinput", "codefield", "pyfield", "namefield",
				"partmask", "lsfield", "showls", "enablecopy",
				"enabledefaultfilter", "enterdropdown", "enteruniqueexit",
				"usetablefiltered", "useforsfz", "conditionoperator",
				"conditionfield", "conditiontype", "groups", "bdlx",
				"vtype","pxid" ],
	        listeners:{
				loadexception:function(mystore,options,response,error){
					if(error){
						var json = Ext.decode(response.responseText);
						Ext.Msg.alert("提示",json.messages[0]);
					}else{
						Ext.Msg.alert("提示",response.responseText);
					}
				}
	        }
	    });
		that.xszdGrid =new Ext.grid.GridPanel({
	        store: xszdStore,
	        region: 'center',
	        view:new Ext.grid.GridView({
					//forceFit:true,
					//autoFill:true,
					enableRowBody:true
			}),
			stripeRows: true,
	        cm: xszdcolModel,
	        sm:xszdcsm,
			loadMask: {msg:'正在加载数据，请稍侯……'},
			bodyStyle:'width:80%',
	        border:true,
	        anchor:'100% 100%',
		    margins: '0 0 0 0',
			cmargins: '0 0 0 0',        
	        frame:false,
			iconCls:'icon-grid',
	        title:'',
	        listeners:{
	        	rowclick:function(g, rowIndex, e){
	        		selXszd = g.store.getAt(rowIndex);
	        		selXszdRow = rowIndex;
					Ext.getCmp('ydsjBtn3').enable();
					Ext.getCmp('ydsjBtn8').enable();
					if(rowIndex==0){
						Ext.getCmp('ydsjBtn10').disable();
						Ext.getCmp('ydsjBtn11').enable();
					}else if((rowIndex+1)==g.store.data.length){
						Ext.getCmp('ydsjBtn10').enable();
						Ext.getCmp('ydsjBtn11').disable();
					}else{
						Ext.getCmp('ydsjBtn10').enable();
						Ext.getCmp('ydsjBtn11').enable();
					}
	        	},
				rowdblclick:function(g, rowIndex, e){
					selXszd = g.store.getAt(rowIndex);
					selXszdRow = rowIndex;
					Ext.getCmp('ydsjBtn3').enable();
					Ext.getCmp('ydsjBtn8').enable();
					var win = this.findParentByType("zdycx_panel");
					var xszdStore = win.items.items[1].items.items[0].items.items[0].items.items[0].store;
					var bxszdStore = win.items.items[0].items.items[0].items.items[0].store;//win.bxszdGrid.store;
					bxszdStore.add(selXszd);
					xszdStore.remove(selXszd);
				}
			}
	    });
		bxszdStore.baseParams = {
				pzlb:that.pzlb,
				flag: 1
		};
		bxszdStore.load({params:{start:0, limit:9999}});
		xszdStore.baseParams = {
				pzlb:that.pzlb,
				flag: 2
		};
		xszdStore.load({params:{start:0, limit:9999}});
		
		var pxzdcsm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
		var pxzdcolModel = new Ext.grid.ColumnModel([
			{
				header: "数据配置id",
		        dataIndex: "sjpzid",
		        sortable: true,
		        hidden:true,
				width: 40
			},{
				header: "",
		        dataIndex: "pzlb",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "pzmc",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "id",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "fieldname",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "排序字段",
		        dataIndex: "displayname",
		        sortable: true,
				width: 150
			},{
				header: "",
		        dataIndex: "fieldtype",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "fieldtypename",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "dsname",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "readonly",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "visibletype",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "fieldlength",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "displayfieldlength",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "ischinese",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "mbmod",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "allowselfinput",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "codefield",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "pyfield",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "namefield",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "partmask",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "lsfield",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "showls",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "enablecopy",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "enabledefaultfilter",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "enterdropdown",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "enteruniqueexit",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "usetablefiltered",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "useforsfz",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "conditionoperator",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "conditionfield",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "conditiontype",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "groups",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "bdlx",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "vtype",
		        sortable: true,
				width: 40,
				hidden:true
			},{
				header: "",
		        dataIndex: "pxid",
		        sortable: true,
				width: 40,
				hidden:true
			}
		]);
		var pxzdStore = new Ext.data.JsonStore({
	 		proxy: new Ext.data.HttpProxy({
	 			url: "dict/utils/querySjpzbGz?flag=" +3,
				method: "POST",
				disableCaching: true
		    }),
	        root: 'list',
	        id:'sjpzid',
	        totalProperty:'totalCount',
	        fields: [ "sjpzid", "pzlb", "pzmc", "id", "fieldname",
				"displayname", "fieldtype", "fieldtypename", "dsname",
				"readonly", "visibletype", "fieldlength",
				"displayfieldlength", "ischinese", "mbmod",
				"allowselfinput", "codefield", "pyfield", "namefield",
				"partmask", "lsfield", "showls", "enablecopy",
				"enabledefaultfilter", "enterdropdown", "enteruniqueexit",
				"usetablefiltered", "useforsfz", "conditionoperator",
				"conditionfield", "conditiontype", "groups", "bdlx",
				"vtype","pxid" ],
	        listeners:{
				loadexception:function(mystore,options,response,error){
					if(error){
						var json = Ext.decode(response.responseText);
						Ext.Msg.alert("提示",json.messages[0]);
					}else{
						Ext.Msg.alert("提示",response.responseText);
					}
				}
	        }
	    });

		that.pxzdGrid =new Ext.grid.GridPanel({
	        store:pxzdStore,
	        region:'center',
	        view:new Ext.grid.GridView({
					//forceFit:true,
					//autoFill:true,
					enableRowBody:true
			}),
			stripeRows: true,
	        cm:pxzdcolModel,
	        sm:pxzdcsm,
			loadMask: {msg:'正在加载数据，请稍侯……'},
			bodyStyle:'width:80%',
	        border:true,
	        anchor:'100% 100%',
		    margins: '0 0 0 0',
			cmargins: '0 0 0 0',        
	        frame:false,
			iconCls:'icon-grid',
	        title:'',
	        listeners:{
	        	rowclick:function(g, rowIndex, e){
	        		selPxzd = g.store.getAt(rowIndex);
					selPxzdRow = rowIndex;
					Ext.getCmp('ydsjBtn9').enable();
					if(rowIndex==0){
						Ext.getCmp('ydsjBtn12').disable();
						Ext.getCmp('ydsjBtn13').enable();
					}else if((rowIndex+1)==g.store.data.length){
						Ext.getCmp('ydsjBtn12').enable();
						Ext.getCmp('ydsjBtn13').disable();
					}else{
						Ext.getCmp('ydsjBtn12').enable();
						Ext.getCmp('ydsjBtn13').enable();
					}
	        	},
				rowdblclick:function(g, rowIndex, e){
					
				}
			}
	    });
		that.items = [
		        {
		        	layout:'border',
		        	region:'center',
		        	//height: 600,
		        	items:[
		        		{
		        			layout : 'fit',
	  		        		region:'west',
	  		        		width:'80%',
	  		        		height:'80%',
//	  		        		anchor: '100% 80%',
	  		        		title:'',
	  		        		bodyStyle : 'overflow-y:auto;overflow-x:hidden',
	  		        		items:[
	  		        		        that.bxszdGrid
	  		        		]
	        	      },{
	  		        		region:'center',
	  		        		width:'20%',
	  		        		layout:'table',
	  				    	 align:'center',
	  				    	 layoutConfig: {
	  				    		columns: 1
	  				    	 },
	  				    	bodyStyle:'padding:15px',
	  		        		items:[{
					    	    	height:20,
					    	    	border:false,
					    	    	frame:false
	  		        			},new Ext.Button({
	  		        				text:'>',
	  		        				id:'ydsjBtn1',
	  		        				minWidth:40,
	  		        				handler:function(){
	  		        					if(selBxszd){
	  		        						var win = this.findParentByType("zdycx_panel");
		  									var xszdStore = win.items.items[1].items.items[0].items.items[0].items.items[0].store;
		  									if(!xszdStore.getById(selBxszd.data.sjpzid)){
		  										var rr = new xszdStore.reader.recordType(selBxszd.data,selBxszd.data.sjpzid);
		  										var tempRowIndex = bxszdStore.data.length;
		  										xszdStore.add([rr]);
		  										bxszdStore.remove(selBxszd);
		  										if(tempRowIndex==(selBxszdRow+1)){
		  											win.bxszdGrid.fireEvent("rowclick",win.bxszdGrid,selBxszdRow-1);
		  											win.bxszdGrid.getSelectionModel().selectRange(selBxszdRow,selBxszdRow);
		  										}else{
		  											win.bxszdGrid.fireEvent("rowclick",win.bxszdGrid,selBxszdRow);
			  										win.bxszdGrid.getSelectionModel().selectRange(selBxszdRow,selBxszdRow);
		  										}
		  									}
	  		        					}
	  		        				}
	  		        			}),{
					    	    	height:10,
					    	    	border:false,
					    	    	frame:false
					    	    },new Ext.Button({
	  		        				text:'>>',
	  		        				id:'ydsjBtn2',
	  		        				minWidth:40,
	  		        				handler:function(){
	  		        					var win = this.findParentByType("zdycx_panel");
	  									var xszdStore = win.items.items[1].items.items[0].items.items[0].items.items[0].store;
	  									var bxszdStore = win.items.items[0].items.items[0].items.items[0].store;
	  									bxszdStore.each(function(r){
	  										xszdStore.add(r);
	  										bxszdStore.remove(r);
	  		        					});
	  									Ext.getCmp('ydsjBtn1').disable();
	  									Ext.getCmp('ydsjBtn2').disable();
	  									Ext.getCmp('ydsjBtn3').disable();
	  									Ext.getCmp('ydsjBtn4').enable();
	  		        				}
	  		        			}),{
					    	    	height:10,
					    	    	border:false,
					    	    	frame:false
					    	    },
	  		        			new Ext.Button({
	  		        				text:'<',
	  		        				id:'ydsjBtn3',
	  		        				minWidth:40,
	  		        				handler:function(){
	  		        					if(selXszd){
	  		        						var win = this.findParentByType("zdycx_panel");
	  		        						var xszdStore = win.items.items[1].items.items[0].items.items[0].items.items[0].store;
		  									var bxszdStore = win.items.items[0].items.items[0].items.items[0].store;//win.bxszdGrid.store;
		  									if(!bxszdStore.getById(selXszd.data.sjpzid)){
		  										var rr = new xszdStore.reader.recordType(selXszd.data,selXszd.data.sjpzid);
		  										var tempRowIndex = xszdStore.data.length;
		  										bxszdStore.add([rr]);
		  										xszdStore.remove(selXszd);
		  										bxszdStore.sort('sjpzid', 'ASC');
		  										if(tempRowIndex==(selXszdRow+1)){
		  											win.xszdGrid.fireEvent("rowclick",win.xszdGrid,selXszdRow-1);
		  											win.xszdGrid.getSelectionModel().selectRange(selXszdRow,selXszdRow);
		  										}else{
		  											win.xszdGrid.fireEvent("rowclick",win.xszdGrid,selXszdRow);
			  										win.xszdGrid.getSelectionModel().selectRange(selXszdRow,selXszdRow);
		  										}
		  									}
	  		        					}
	  		        				}
		  		        		}),{
					    	    	height:10,
					    	    	border:false,
					    	    	frame:false
					    	    },
		  		        		new Ext.Button({
	  		        				text:'<<',
	  		        				id:'ydsjBtn4',
	  		        				minWidth:40,
	  		        				handler:function(){
	  		        					var win = this.findParentByType("zdycx_panel");
	  									var bxszdStore = win.items.items[0].items.items[0].items.items[0].store;//win.bxszdGrid.store;
	  									var xszdStore = win.items.items[1].items.items[0].items.items[0].items.items[0].store;
	  									xszdStore.each(function(r){
	  										bxszdStore.add(r);
	  										xszdStore.remove(r);
	  		        					});
	  									bxszdStore.sort('sjpzid', 'ASC');
	  									Ext.getCmp('ydsjBtn1').disable();
	  									Ext.getCmp('ydsjBtn2').enable();
	  									Ext.getCmp('ydsjBtn3').disable();
	  									Ext.getCmp('ydsjBtn4').disable();
	  		        				}
		  		        		}),
		  		        		{
					    	    	height:120,
					    	    	border:false,
					    	    	frame:false
	  		        			},
	  		        			new Ext.Button({
	  		        				text:'>',
	  		        				id:'ydsjBtn5',
	  		        				minWidth:40,
	  		        				handler:function(){
	  		        					if(selBxszd){
	  		        						var win = this.findParentByType("zdycx_panel");
		  									var pxzdstore = win.items.items[1].items.items[0].items.items[2].items.items[0].store;
		  									if(!pxzdstore.getById(selBxszd.data.sjpzid)){
		  										var rr = new pxzdStore.reader.recordType(selBxszd.data,selBxszd.data.sjpzid);
		  										pxzdstore.add([rr]);
		  									}
	  		        					}
	  		        				}
	  		        			}),
	  		        			{
					    	    	height:5,
					    	    	border:false,
					    	    	frame:false
					    	    },
					    	    new Ext.Button({
	  		        				text:'<',
	  		        				id:'ydsjBtn6',
	  		        				minWidth:40,
	  		        				handler:function(){
	  		        					if(selPxzd){
	  		        						var win = this.findParentByType("zdycx_panel");
		  									var pxzdstore = win.items.items[1].items.items[0].items.items[2].items.items[0].store;
		  									if(pxzdstore.getById(selPxzd.data.sjpzid)){
		  										var removeRow = pxzdstore.getAt(selPxzdRow);
		  										pxzdstore.remove(removeRow);
		  									}
	  		        					}
	  		        				}
	  		        			}),
	  		        			{
					    	    	height:10,
					    	    	border:false,
					    	    	frame:false
					    	    },
					    	    new Ext.Button({
	  		        				text:'<<',
	  		        				id:'ydsjBtn7',
	  		        				minWidth:40,
	  		        				handler:function(){
	  		        					var win = this.findParentByType("zdycx_panel");
	  		        					var pxzdstore =win.pxzdGrid.store;
	  		        					pxzdstore.each(function(r){
	  		        						pxzdstore.remove(r);
	  		        					});
	  		        				}
	  		        			}),
	  		        			{
					    	    	height:5,
					    	    	border:false,
					    	    	frame:false
					    	    }
	  		        		]
		 			    }
		        	]
		        },{
 			    	layout:'border',
		        	region:'east',
		        	border:false,
	    	    	frame:false,
		        	width:'70%',
	        		items:[{
	        			layout:'anchor',
	        			region:'center',
        				items:[
        					{
		 			    	layout:'fit',
	  		        		region:'north',
	  		        		//height:'40%',
	  		        		anchor: '100% 40%',
	  		        		title:'',
	  		        		bodyStyle : 'overflow-y:auto;overflow-x:hidden',
	  		        		items:[that.xszdGrid]
		 			    },{
		 			    	layout:'border',
	  		        		region:'center',
	  		        		layout:'table',
	  		        		//height:'20%',
	  		        		anchor: '100% 10%',
	  				    	layoutConfig: {
	  				    		columns: 5
	  				    	},
	  				    	bodyStyle:'padding:10px',
	  		        		items:[{
				    	    	//width:80,
				    	    	border:false,
				    	    	frame:false,
				    	    	html:''
  		        			},
	  		        			new Ext.Button({
	  		        				text:'↓',
	  		        				id:'ydsjBtn8',
	  		        				minWidth:40,
	  		        				disabled:true,
	  		        				handler:function(){
	  		        					if(selXszd){
	  		        						var win = this.findParentByType("zdycx_panel");
		  									var pxzdstore = win.items.items[1].items.items[0].items.items[2].items.items[0].store;
		  									if(!pxzdstore.getById(selXszd.data.sjpzid)){
		  										var rr = new pxzdStore.reader.recordType(selXszd.data,selXszd.data.sjpzid);
		  										pxzdstore.add([rr]);
		  									}
	  		        					}
	  		        				}
	  		        			}),{
					    	    	width:10,
					    	    	border:false,
					    	    	frame:false
	  		        			},new Ext.Button({
	  		        				text:'↑',
	  		        				id:'ydsjBtn9',
	  		        				disabled:true,
	  		        				minWidth:40,
	  		        				handler:function(){
	  		        					if(selPxzd){
	  		        						var win = this.findParentByType("zdycx_panel");
		  									var pxzdstore = win.items.items[1].items.items[0].items.items[2].items.items[0].store;
		  									if(pxzdstore.getById(selPxzd.data.sjpzid)){
		  										var removeRow = pxzdstore.getAt(selPxzdRow);
		  										pxzdstore.remove(removeRow);
		  									}
		  									if(pxzdstore.data.length==0){
		  										Ext.getCmp('ydsjBtn9').disable();
		  									}
	  		        					}
	  		        				}
	  		        			})
	  		        		]
		 			    },{
		 			    	layout:'fit',
	  		        		region:'south',
	  		        		border:false,
			    	    	frame:false,
	  		        		anchor: '100% 40%',
	  		        		bodyStyle : 'overflow-y:auto;overflow-x:hidden',
	  		        		items:[
	  		        			that.pxzdGrid
	  		        		]
		 			    }
	        		]
	        		},{
					layout:'fit',
					region:'east',
					width:'75%',
					items:[{
	 			    	layout:'fit',
  		        		region:'center',
  		        		layout:'table',
  		        		bodyStyle:'padding:10px',
  				    	layoutConfig: {
  				    		columns: 1
  				    	},
  				    	
  		        		items:[
  		        			{
				    	    	height:40,
				    	    	border:false,
				    	    	frame:false
				    	    },
				    	    new Ext.Button({
  		        				text:'↑',
  		        				id:'ydsjBtn10',
  		        				disabled:true,
  		        				minWidth:40,
  		        				handler:function(){
  		        					var win = this.findParentByType("zdycx_panel");
	        						var xszdStore = win.items.items[1].items.items[0].items.items[0].items.items[0].store;
  									if(selXszd&&selXszdRow>0){
  										dqSelRes = xszdStore.getAt(selXszdRow);
  										preSelRes = xszdStore.getAt(selXszdRow-1);
  										var tempPxid = null;
  										tempPxid = dqSelRes.data.pxid;
  										dqSelRes.data.pxid=preSelRes.data.pxid;
  										preSelRes.data.pxid=tempPxid;
  										xszdStore.sort('pxid', 'ASC');
  										win.xszdGrid.getSelectionModel().selectRange(selXszdRow-1,selXszdRow-1);
  										win.xszdGrid.fireEvent("rowclick",win.xszdGrid,selXszdRow-1)
  									}
  		        				}
  		        			}),	
  		        			{
				    	    	height:10,
				    	    	border:false,
				    	    	frame:false
				    	    },
				    	    new Ext.Button({
  		        				text:'↓',
  		        				id:'ydsjBtn11',
  		        				disabled:true,
  		        				minWidth:40,
  		        				handler:function(){
  		        					var win = this.findParentByType("zdycx_panel");
	        						var xszdStore = win.items.items[1].items.items[0].items.items[0].items.items[0].store;
  									if(selXszd&&selXszdRow<xszdStore.data.length){
  										dqSelRes = xszdStore.getAt(selXszdRow);
  										nextSelRes = xszdStore.getAt(selXszdRow+1);
  										var tempPxid = null;
  										tempPxid = nextSelRes.data.pxid;
  										nextSelRes.data.pxid=dqSelRes.data.pxid;
  										dqSelRes.data.pxid=tempPxid;
  										xszdStore.sort('pxid', 'ASC');
  										win.xszdGrid.getSelectionModel().selectRange(selXszdRow+1,selXszdRow+1);
  										win.xszdGrid.fireEvent("rowclick",win.xszdGrid,selXszdRow+1)
  									}
  		        				}
  		        			}),{
				    	    	height:200,
				    	    	border:false,
				    	    	frame:false
				    	    },
				    	    new Ext.Button({
  		        				text:'↑',
  		        				id:'ydsjBtn12',
  		        				disabled:true,
  		        				minWidth:40,
  		        				handler:function(){
  		        					var win = this.findParentByType("zdycx_panel");
	        						var pxzdStore = win.pxzdGrid.store;
  									if(selPxzd&&selPxzdRow>0){
  										dqSelRes = pxzdStore.getAt(selPxzdRow);
  										preSelRes = pxzdStore.getAt(selPxzdRow-1);
  										var tempPxid = null;
  										tempPxid = dqSelRes.data.pxid;
  										dqSelRes.data.pxid=preSelRes.data.pxid;
  										preSelRes.data.pxid=tempPxid;
  										pxzdStore.sort('pxid', 'ASC');
  										win.pxzdGrid.getSelectionModel().selectRange(selPxzdRow-1,selPxzdRow-1);
  										win.pxzdGrid.fireEvent("rowclick",win.pxzdGrid,selPxzdRow-1)
  									}
  		        				
  		        				}
  		        			}),
  		        			{
				    	    	height:10,
				    	    	border:false,
				    	    	frame:false
				    	    },
				    	    new Ext.Button({
  		        				text:'↓',
  		        				id:'ydsjBtn13',
  		        				disabled:true,
  		        				minWidth:40,
  		        				handler:function(){
  		        					var win = this.findParentByType("zdycx_panel");
	        						var pxzdStore = win.pxzdGrid.store;
  									if(selPxzd&&selPxzdRow<pxzdStore.data.length){
  										dqSelRes = pxzdStore.getAt(selPxzdRow);
  										nextSelRes = pxzdStore.getAt(selPxzdRow+1);
  										var tempPxid = null;
  										tempPxid = nextSelRes.data.pxid;
  										nextSelRes.data.pxid=dqSelRes.data.pxid;
  										dqSelRes.data.pxid=tempPxid;
  										pxzdStore.sort('pxid', 'ASC');
  										win.pxzdGrid.getSelectionModel().selectRange(selPxzdRow+1,selPxzdRow+1);
  										win.pxzdGrid.fireEvent("rowclick",win.pxzdGrid,selPxzdRow+1)
  									}
  		        				}
  		        			})
  		        		]
	 			    }]
				}
	        			
	        ]
 		},{
 			layout:'fit',
 			region:'south',
        	border:false,
	    	frame:false,
	    	height:40
	    	
 		}];
		
		Gnt.ux.ZdycxPanel.superclass.initComponent.call(this);
	}
});

Ext.reg('zdycx_panel', Gnt.ux.ZdycxPanel);
function sort(type,index,store){//type:交换方式，index：当前需要交换的行，store：被交换的数据源
	alert("交换相连的两行数据!");
};
