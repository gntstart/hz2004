var selRecord = null;

Ext.onReady(function(){
	Ext.QuickTips.init();
	var pch = getQueryParam("pch");
 	var myuserStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'cx/hjjbxx/hjzmlscx/getHjzmls',
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        url: 'cx/hjjbxx/hjzmlscx/getHjzmls',
        id:'id',
        totalProperty:'totalCount',
        fields: [
			"id",
			"sqrxm",
			"sqrsfz",
			"sqrlxdh",
			"sqrlxdz",
			"ywlb",
			"bsqrxm",
			"bsqrsfz",
			"czdw",
			"czyh",
			"czsj",
			"lhbs",
			"lhsfz",
			"lhdz",
			"sbzt",
			"clbs",
			"clsj",
			"pch",
			"ywlbm",
			"nbgxm",
			"cym",
			"csyxzmbh",
			"bsqrxb",
			"bsqrmz",
			"bsqrcsrq",
			"btkrgx",
			"bsqrsjhm",
			"zxyy",
			"qcdbm",
			"kdqqy_qrdqbm",
			"kdqqy_qrywid",
			"kdqqy_qrywlx",
			"kdqqy_qrfkzt",
			"kdqqy_qrfksj",
			"kdqqy_zqzbh",
			"kdqqy_qrdqh",
			"kdqqy_qyzbh",
			"kdqqy_lscxfldm",
			"kdqqy_qyldyy",
			"kdqqy_qclb",
			"sfzqr",
			"sbid",
			"czdw_mc",
			"sbid",
			"rkzp",
			"pj"
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
    
	
	var csm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var colModel = new Ext.grid.ColumnModel([
		{
			header: "业务ID",
	        dataIndex: "id",
	        sortable: true,
			width: 50
		},{
	        header: "业务名称",
	        dataIndex: "ywlbm",	
	        sortable: true,
			width: 180
	    },{
	        header: "状态",
	        dataIndex: "clbs",
	        sortable: true,
			width: 50,
			renderer:function(value){
//				var r = sfbz.getById(value);
//				if(r)
//					return r.data.name;
				
				return value;
			}
	    },{
	        header: "批次号",
	        dataIndex: "pch",	
	        sortable: true,
			width: 110
	    },{
	        header: "申报人",
	        dataIndex: "sqrxm",	
	        sortable: true,
			width: 70
	    },{
	        header: "申报人身份证",
	        dataIndex: "sqrsfz",
	        sortable: true,
			width: 150
	    },{
	        header: "被申请人姓名",
	        dataIndex: "bsqrxm",
	        sortable: true,
			width: 70
	    },{
	        header: "被申请人身份证",
	        dataIndex: "bsqrsfz",
	        sortable: true,
			width: 150
	    },{
	        header: "操作单位",
	        dataIndex: "czdw_mc",
	        sortable: true,
			width: 200
	    },{
	        header: "操作人",
	        dataIndex: "czyh",
	        sortable: true,
			width: 60
	    },{
	        header: "注销原因",
	        dataIndex: "zxyy",	
	        sortable: true,
			width: 110
	    },{
	        header: "落户标志",
	        dataIndex: "lhbz",
	        sortable: true,
			width: 60
	    },{
	        header: "落户身份证",
	        dataIndex: "lhsfz",
	        sortable: true,
			width: 150
	    },{
	        header: "立户地址",
	        dataIndex: "lhdz",
	        sortable: true,
			width: 200
	    },{
	        header: "申报状态",
	        dataIndex: "sbzt",
	        sortable: true,
			width: 60
	    },{
	        header: "sbId",
	        dataIndex: "sbid",
	        hidden:true
	    },{
	        header: "评价结果",
	        dataIndex: "pj",
	        sortable: true,
			width: 60,
			renderer:function(value){
				return Gnt.ux.Dict.getDictLable('CS_PJ',value);
			}
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
				Ext.getCmp("hjzmdyId").enable();
			}
		},
        bbar: new Ext.PagingToolbar({
				pageSize: 50,
				store: myuserStore,
				displayInfo: true
		})
    });
	var p1 = new Ext.Panel({
		layout:'border',
		region: 'center',
		items:[grid
		],
		buttons:[
			new Ext.Button({
				id:'hjzmdyId',
				text:'户籍证明打印',
				minWidth:60,
				disabled:true,
				handler:function(){
					if(selRecord){
						var arrayTemp=[];
	               	 	var o={};
						o.directTable="hjzmNew";
						o.xm =selRecord.data.bsqrxm;
						o.gmsfhm =selRecord.data.bsqrsfz;
						o.hzywid = selRecord.data.id;
						arrayTemp.push(o);
						printfunction(0,arrayTemp);
						
						grid.store.reload(); 
					}else{
						alert("请至少选择一条数据！");
					}
				}
			}),
			new Ext.Button({
				id:'dyAllId',
				text:'全部打印',
				minWidth:60,
				disabled:true,
				handler:function(){
					var arrayTemp=[];
					var params = {
					};
					Ext.apply(params, grid.store.baseParams);
					Ext.apply(params, {pageSize:grid.store.totalLength});
					Gnt.submit(
							params, 
							grid.store.url, 
							"正在全部处理信息，请稍后...", 
							function(jsonData,params){
        						var list= jsonData.list;
        						for(var index=0;index<list.length;index++){
        							var data = list[index];
        							data.clbz = 1;
        							var o={};
        							o.directTable="hjzmNew";
        							o.xm =data.bsqrxm;
        							o.hzywid = data.id;
        							o.gmsfhm = data.bsqrsfz;  
        							arrayTemp.push(o);
        						}
        						printfunction(0,arrayTemp);
        		               	grid.store.reload(); 
							}
					);
					
				}
			}),
			new Ext.Button({
				id:'closeId',
				text:'关闭',
				minWidth:60,
				handler:function(){
					window.parent.closeWorkSpace();
				}
			})
		]
	});  
	var v = new Ext.Viewport({
        layout:'fit',
        id:'vp',
        border:false,
        items: {
        	layout:'card',
        	border:false,
        	id:'card',
        	frame:false,
        	activeItem: 0,
        	xtype: 'panel',
        	items:[p1]
        }
    });
    
	/**
		对接,需要自动跳转到业务页面
		第三方页面集成接口.doc	确认ywid
	 */
	if(getQueryParam("pch")){
		myuserStore.baseParams = {
				pch:getQueryParam("pch")
		};
		
		myuserStore.load({params:{start:0, limit:50}});
		
		myuserStore.on('load',function(s,records){
			if(records.length>0){//数据库查询到符合条件的数据，点击第一条
				grid.fireEvent("rowclick",grid,0);
				grid.getSelectionModel().selectRange(0,0);
//				alert(1111);
				Ext.getCmp("hjzmdyId").enable();
				Ext.getCmp("dyAllId").enable();
			}else{//没有符合条件的数据
				alert("没有查到符合条件的数据，请确认是否办结！");
			}
			
		});
	}/*else{
		myuserStore.baseParams = {
			clbs:'0'
		};
		myuserStore.load({params:{start:0, limit:50}})
	}*/
	
});