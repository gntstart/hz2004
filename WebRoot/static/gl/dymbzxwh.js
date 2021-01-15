var selRecord = null;
var mxfs = null;
var win = null;

Ext.onReady(function(){
	Ext.QuickTips.init();
	Ext.Msg.minWidth = 100;
	var myMask = new Ext.LoadMask(Ext.getBody(), {msg:"正在保存数据，请等待..."});
	
	var sfbz = new Ext.data.SimpleStore({
		id:0,
		fields:[{name: 'code', mapping: 0},{name: 'name', mapping: 1}],
	   	data:[['0','有效'],['1','无效']]
	});
	
 	var myuserStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'gl/dymbzxwh/queryDymbcl',//yw/hjyw/hzywcl/queryHzywcl
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        id:'id',
        totalProperty:'totalCount',
        fields: [
			"id",
			"lodopId",
			"lodopName",
			"nr",
			"zxbz",
			"cjr",
			"cjsj",
			"xgr",
			"xgsj"
        ],
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
    
	var fs = new Ext.form.FormPanel({
		id:'form1',
    	title:'',
    	anchor:'100% 100%',
    	buttonAlign:'right',
    	labelAlign:'right',
    	frame:true,
    	border:false,
        layout:'form',
        labelWidth:75,
       	items:[{
        		layout:'column',
    			frame:false,
    			border:false,
        		defaults:{
        			border:false,
        			frame:false
        		},
            	items:[{
		           	columnWidth:.20,
	    	        layout: 'form',
	    	        bodyStyle:'padding:0 0 0 0',
	        	    items: [{
						xtype:'datefield',
						anchor:'100%',
						format:'Ymd',
						name:'cjsj_start',
						fieldLabel:'创建时间起'
					}]
				},{
		           	columnWidth:.20,
	    	        layout: 'form',
	    	        bodyStyle:'padding:0 0 0 0',
	        	    items: [{
						xtype:'datefield',
						format:'Ymd',
						anchor:'100%',
						name:'cjsj_end',
						fieldLabel:'创建时间止'
					}]
				},{
		           	columnWidth:.20,
	    	        layout: 'form',
	    	        bodyStyle:'padding:0 0 0 0',
	        	    items: [{
						xtype:'datefield',
						anchor:'100%',
						format:'Ymd',
						name:'xgsj_start',
						fieldLabel:'修改时间起'
					}]
				},{
		           	columnWidth:.20,
	    	        layout: 'form',
	    	        bodyStyle:'padding:0 0 0 0',
	        	    items: [{
						xtype:'datefield',
						format:'Ymd',
						anchor:'100%',
						name:'xgsj_end',
						fieldLabel:'修改时间止'
					}]
				},{
	                columnWidth:.20,
    	           	layout: 'form',
    	           	bodyStyle:'padding:0 0 0 0',
        	       	items: [{
						hiddenName:'zxbz',
						anchor:'100%',
						xtype:'combo',
						fieldLabel:'状态',
						mode: 'local',
            			triggerAction: 'all',
						valueField:"code",
      					displayField:"name",
						selectOnFocus:true,
						emptyText : '请选择',
						typeAhead: true,  
						editable:false,
						forceSelection: true,
			    		forceSelection: true, 
    					blankText:'请选择',
            			lazyRender:true,
            			value:'0',
            			store:sfbz
					}]
				}]
		}],
		buttons:[
			new Ext.Button({
				text:'条件清空',
				minWidth:100,
				handler:function(){
					Ext.getCmp('form1').form.reset();
				}
			}),new Ext.Button({
				text:'模板新增',
				minWidth:100,
				handler:function(){
					var url = basePath + 'gl/dymbzxwh/addDymbcl';
					if(parent && parent.openWorkSpace){
						parent.openWorkSpace(url,  "新增模板", "_hzywcl_" + id);
					}else{
						window.location.href = url;
					}
				}
			}),new Ext.Button({
				text:'模板删除',
				minWidth:100,
				handler:function(){
					if(selRecord){
						if(window.confirm('请确认是否要删除该打印模板！')){
							Gnt.submit(
									{
										lodopId : selRecord.data.lodopId,
										id : selRecord.data.id
									}, 
									"gl/dymbzxwh/delDymb", 
									"正在进行打印模板删除，请稍后...", 
									function(jsonData,params){
										showInfo("模板删除成功！",function(){
											myuserStore.reload();
										});
									}
								);
						}else{
							return;
						}
					}else{
						alert("请选中需要删除的模板!");
					}
				}
			}),
			new Ext.Button({
				text:'查询',
				minWidth:75,
				handler:function(){
					var p =  fs.getForm().getValues();
					delete p.zzid;
					myuserStore.baseParams = p;
					myuserStore.load({params:{start:0, limit:50}})
				}
			})
		]
	});
	
	var csm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var colModel = new Ext.grid.ColumnModel([
		csm,{
			header: "业务ID",
	        dataIndex: "id",
	        sortable: true,
			width: 120
		},{
	        header: "模板Id",
	        dataIndex: "lodopId",
	        sortable: true,
	        hidden:true,
			width: 120
	    },{
	        header: "模板名称",
	        dataIndex: "lodopName",
	        sortable: true,
			width: 180
	    },{
	        header: "内容",
	        dataIndex: "nr",	
	        sortable: true,
			width:500
	    },{
	        header: "注销标志",
	        dataIndex: "zxbz",	
	        sortable: true,
			width: 60,
			renderer:function(value){
				return value;
			}
	    },{
	        header: "创建人",
	        dataIndex: "cjr",
	        sortable: true,
			width: 70,
			renderer:function(value){
				return value;
			}
	    },{
	        header: "创建时间",
	        dataIndex: "cjsj",	
	        sortable: true,
			width: 100
	    },{
	        header: "修改人",
	        dataIndex: "xgr",	
	        sortable: true,
			width: 70
	    },{
	        header: "修改时间",
	        dataIndex: "xgsj",
	        sortable: true,
			width: 100
	    }
	]);
	
	var grid = new Ext.grid.GridPanel({
        store: myuserStore,
        region: 'center',
        view:new Ext.grid.GridView({
				//forceFit:true,
				//autoFill:true,
				enableRowBody:true
		}),
		stripeRows: true,
        cm: colModel,
        sm:csm,
		loadMask: {msg:'正在加载数据，请稍侯……'},
		bodyStyle:'width:100%',
        border:true,
        anchor:'100% 100%',
	    margins: '0 0 0 0',
		cmargins: '0 0 0 0',        
        frame:false,
		iconCls:'icon-grid',
        title:'',
        listeners:{
        	rowclick:function(g, rowIndex, e){
        		selRecord = g.store.getAt(rowIndex);
        	},
			rowdblclick:function(g, rowIndex, e){
				selRecord = g.store.getAt(rowIndex);
				var data = selRecord.data;
				var lodopId = data.lodopId;
				var id = data.id;
				if(data.zxbz==1){
					alert("请选择状态有效的模板进行双击查看！");
					return;
				}
				var url = basePath + 'gl/dymbzxwh/queryDymbcl?lodopId=' + lodopId + "&id=" + id;
				if(parent && parent.openWorkSpace){
					parent.openWorkSpace(url,  " 模板" + "【" + data.lodopId + "】", "_hzywcl_" + id);
				}else{
					window.location.href = url;
				}
			}
		},
        bbar: new Ext.PagingToolbar({
				pageSize: 50,
				store: myuserStore,
				displayInfo: true
		})
    });
    
    new Ext.Viewport({
	    layout: 'border',
	    items: [{
	    	height:80,
	        region: 'north',
	        border: false,
	        closable:true,
	        margins: '5 5 5 5',
	        layout:'fit',
	        items:[fs]
	    },{
	        region: 'center',
	        html: '',
	        width:200,
	        border: false,
	        closable:true,
	        layout:'fit',
	        margins: '0 5 0 5',
	        items:[grid]
	    }]
	});
    
	/**
		对接,需要自动跳转到业务页面
	 */
	if(getQueryParam("ywid")){
		myuserStore.baseParams = {
			ywid:getQueryParam("ywid")
		};
		
		myuserStore.load({params:{start:0, limit:50}});
		
		myuserStore.on('load',function(s,records){
			grid.fireEvent("rowdblclick",grid,0);
		});
	}else{
		myuserStore.baseParams = {
				clbs:'0'
		};
		myuserStore.load({params:{zxbz:0,start:0, limit:50}})
	}
	
});