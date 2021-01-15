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
	   	data:[['0','未处理'],['1','已处理'],['2','已删除']]
	});
	
	var form50008 = new Gnt.ux.SjpzForm({
		pzlb: '50008',
		url: 'yw/queryRyxx',
		region:'center',
		title:'',
		cols:2,
//		height:300,
		formType:'query'
	});
	
 	var myuserStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'yw/hjyw/hzywcl/queryHzywcl',
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
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
		           	columnWidth:.33,
	    	        layout: 'form',
	    	        bodyStyle:'padding:0 0 0 0',
	        	    items: [{
						xtype:'datefield',
						anchor:'100%',
						format:'Y-m-d',
						name:'cjsj1',
						fieldLabel:'创建时间起'
					}]
				},{
		           	columnWidth:.33,
	    	        layout: 'form',
	    	        bodyStyle:'padding:0 0 0 0',
	        	    items: [{
						xtype:'datefield',
						format:'Y-m-d',
						anchor:'100%',
						name:'cjsj2',
						fieldLabel:'创建时间止'
					}]
				},{
	                columnWidth:.34,
    	           	layout: 'form',
    	           	bodyStyle:'padding:0 0 0 0',
        	       	items: [{
						hiddenName:'clbs',
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
				text:'删除',
				minWidth:75,
				handler:function(){
					var res = grid.getSelectionModel().getSelections();
					var ids = "";
					var isdel = true;
					Ext.each(res,function(r){
						ids += "," + r.data.id;
						if(r.data.clbs!='0'){
							isdel = false;
						}
					});
					
					if(!isdel){
						showErr("只能删除未处理状态记录！");
						return;
					}
					
					if(ids==""){
						showErr("必须选择需要删除的记录！");
						return;
					}
					
					showInput("确定删除选择记录，请输入删除原因！", "", function(value){
						Gnt.submit(
								{
									hzywid: ids,
									zxyy: value,
									charset:'utf-8'
								}, 
								"yw/hjyw/hzywcl/updateHzyw", 
								"正在删除数据，请稍后...", 
								function(jsonData,params){
									showInfo("删除成功！",function(){
										//关闭当前窗口
										Ext.each(res,function(r){
											grid.store.remove(r);
										});
									});
								}
							);
					});
				}
			}),
			new Ext.Button({
				text:'查询',
				minWidth:75,
				handler:function(){
					var p =  fs.getForm().getValues();
					p.log_code = "";
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
			width: 50
		},{
	        header: "操作时间",
	        dataIndex: "czsj",
	        sortable: true,
			width: 120
	    },{
	        header: "业务名称",
	        dataIndex: "ywlbm",	
	        sortable: true,
			width: 180
	    },{
	        header: "业务编码",
	        dataIndex: "ywlb",	
	        sortable: true,
			width: 60,
			renderer:function(value){
				return value;
			}
	    },{
	        header: "状态",
	        dataIndex: "clbs",
	        sortable: true,
			width: 50,
			renderer:function(value){
				var r = sfbz.getById(value);
				if(r)
					return r.data.name;
				
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
			rowdblclick:function(g, rowIndex, e){
				selRecord = g.store.getAt(rowIndex);
				var data = selRecord.data;
				
				//迁入业务
				var id = data.id;
				var ywlx = data.ywlx;
				var ywlbm = data.ywlbm;
				var pch = data.pch;
				if(!pch || pch=="null"){
					pch = "";
				}
				
				if(data.ywlb=='1'){
					//出生登记
					var url = basePath + 'yw/hjyw/csyw?hzywid=' + id + "&pch=" + pch;
					if(!getQueryParam("ywid")){
						parent.openWorkSpace(url,  ywlbm + "【" + data.bsqrxm + "】", "_hzywcl_" + id);
					}else{
						window.location.href = url;
					}
					return;
				}
				
				if(data.ywlb=='2'){
					//迁出注销,OK
					var url = basePath + 'yw/hjyw/qczx?hzywid=' + id + "&pch=" + pch+"&sbid=" + data.sbid;
					if(!getQueryParam("ywid")){
						parent.openWorkSpace(url,  ywlbm + "【" + data.bsqrxm + "】", "_hzywcl_" + id);
					}else{
						window.location.href = url;
					}
					return;
				}
				
				if(data.ywlb=='3'){
					//迁入和迁入审批业务,OK
					var url = '';
					if(data.sbzt=='1'){
						//迁入审批登记
						url = basePath + 'yw/spgl/qrsp?hzywid=' + id + "&pch=" + pch;
					}else{
						//迁入业务
						url = basePath +  'yw/hjyw/qryw?hzywid=' + id + "&pch=" + pch;
					}

					if(!getQueryParam("ywid")){
						parent.openWorkSpace(url,  ywlbm + "【" + data.bsqrxm + "】", "_hzywcl_" + id);
					}else{
						window.location.href = url;
					}
					return;
				}
				
				if(data.ywlb=='4'){
					//户籍删除, OK
					url = basePath + 'yw/hjyw/hjsc?hzywid=' + id + "&pch=" + pch;
					if(!getQueryParam("ywid")){
						parent.openWorkSpace(url,  ywlbm + "【" + data.bsqrxm + "】", "_hzywcl_" + id);
					}else{
						window.location.href = url;
					}
					return;
				}
				
				if(data.ywlb=='5'){
					//变更更正，OK
					url = basePath + 'yw/hjyw/bggz?hzywid=' + id + "&pch=" + pch;
					if(!getQueryParam("ywid")){
						parent.openWorkSpace(url,  ywlbm + "【" + data.bsqrxm + "】", "_hzywcl_" + id);
					}else{
						window.location.href = url;
					}
					return;
				}
				
				if(data.ywlb=='6'){
					//辅项变更，OK
					url = basePath + 'yw/hjyw/fxbg?hzywid=' + id + "&pch=" + pch;
					if(!getQueryParam("ywid")){
						parent.openWorkSpace(url,  ywlbm + "【" + data.bsqrxm + "】", "_hzywcl_" + id);
					}else{
						window.location.href = url;
					}
					return;
				}
				
				if(data.ywlb=='7'){
					//死亡注销, OK
					url = basePath + 'yw/hjyw/swzx?hzywid=' + id + "&pch=" + pch;
					if(!getQueryParam("ywid")){
						parent.openWorkSpace(url,  ywlbm + "【" + data.bsqrxm + "】", "_hzywcl_" + id);
					}else{
						window.location.href = url;
					}
					return;
				}
				
				if(data.ywlb=='8'){
					//住址变动，OK
					url = basePath + 'yw/hjyw/zzbd?hzywid=' + id + "&pch=" + pch;
					if(!getQueryParam("ywid")){
						parent.openWorkSpace(url,  ywlbm + "【" + data.bsqrxm + "】", "_hzywcl_" + id);
					}else{
						window.location.href = url;
					}
					return;
				}
				
				if(data.ywlb=='9'){
					//户籍补录，OK
					url = basePath + 'yw/hjyw/hjbl?hzywid=' + id + "&pch=" + pch;
					if(!getQueryParam("ywid")){
						parent.openWorkSpace(url,  ywlbm + "【" + data.bsqrxm + "】", "_hzywcl_" + id);
					}else{
						window.location.href = url;
					}
					return;
				}
				
				if(data.ywlb=='10'){
					//户口本打印
					url = basePath + 'cx/hjjbxx/ckxx?hzywid=' + id + "&pch=" + pch + "&ywlb=" + data.ywlb;
					if(!getQueryParam("ywid")){
						parent.openWorkSpace(url,  ywlbm + "【" + data.bsqrxm + "】", "_hzywcl_" + id);
					}else{
						window.location.href = url;
					}
					return;
				}
				
				if(data.ywlb=='11'){
					//准迁证打印
					url = basePath + 'cx/spcx/qrspcx?hzywid=' + id + "&pch=" + pch;
					if(!getQueryParam("ywid")){
						parent.openWorkSpace(url,  ywlbm + "【" + data.bsqrxm + "】", "_hzywcl_" + id);
					}else{
						window.location.href = url;
					}
					return;
				}
				
				if(data.ywlb=='12'){
					//迁移证打印
					url = basePath + 'cx/hjywxx/qczxcx?hzywid=' + id + "&pch=" + pch+ "&ywlb=" + data.ywlb;
					if(!getQueryParam("ywid")){
						parent.openWorkSpace(url,  ywlbm + "【" + data.bsqrxm + "】", "_hzywcl_" + id);
					}else{
						window.location.href = url;
					}
					return;
				}
				
				if(data.ywlb=='13'){
					//户籍证明打印
					url = basePath + 'cx/hjjbxx/ckxx?hzywid=' + id + "&pch=" + pch + "&ywlb=" + data.ywlb;
					if(!getQueryParam("ywid")){
						parent.openWorkSpace(url,  ywlbm + "【" + data.bsqrxm + "】", "_hzywcl_" + id);
					}else{
						window.location.href = url;
					}
					return;
				}
				if(data.ywlb=='14'){
					//死亡证明打印
					url = basePath + 'cx/hjywxx/swzxcx?hzywid=' + id + "&pch=" + pch + "&ywlb=" + data.ywlb;
					if(!getQueryParam("ywid")){
						parent.openWorkSpace(url,  ywlbm + "【" + data.bsqrxm + "】", "_hzywcl_" + id);
					}else{
						window.location.href = url;
					}
					return;
				}
				if(data.ywlb=='15'){
					//直系亲属关系证明打印
					url = basePath + 'cx/hjywxx/zxqsgxzmdy?hzywid=' + id + "&pch=" + pch + "&ywlb=" + data.ywlb;
					if(!getQueryParam("ywid")){
						parent.openWorkSpace(url,  ywlbm + "【" + data.bsqrxm + "】", "_hzywcl_" + id);
					}else{
						window.location.href = url;
					}
					return;
				}
				if(data.ywlb=='16'){
					//全户变更，add by zjm 20190802
					url = basePath + 'yw/hjyw/bggz?hzywid=' + id + "&pch=" + pch;
					if(!getQueryParam("ywid")){
						parent.openWorkSpace(url,  ywlbm + "【" + data.bsqrxm + "】", "_hzywcl_" + id);
					}else{
						window.location.href = url;
					}
					return;
				}
				if(data.ywlb=='17'){
					//全项变更，add by zjm 20191118
					url = basePath + 'yw/hjyw/qxbg?hzywid=' + id + "&pch=" + pch;
					if(!getQueryParam("ywid")){
						parent.openWorkSpace(url,  ywlbm + "【" + data.bsqrxm + "】", "_hzywcl_" + id);
					}else{
						window.location.href = url;
					}
					return;
				}
				if(data.ywlb=='18'){
					//分户证明，add by zjm 20191015
					url = basePath + 'cx/hjywxx/fhcx?hzywid=' + id + "&pch=" + pch;
					if(!getQueryParam("ywid")){
						parent.openWorkSpace(url,  ywlbm + "【" + data.bsqrxm + "】", "_hzywcl_" + id);
					}else{
						window.location.href = url;
					}
					return;
				}
				if(data.ywlb=='19'){
					//户口登记项目内容变更更正证明，add by zjm 20191015
					url = basePath + 'cx/hjywxx/bggzcx?hzywid=' + id + "&pch=" + pch;
					if(!getQueryParam("ywid")){
						parent.openWorkSpace(url,  ywlbm + "【" + data.bsqrxm + "】", "_hzywcl_" + id);
					}else{
						window.location.href = url;
					}
					return;
				}
				if(data.ywlb=='20'){
					//提供香港、澳门、台湾定居注销户口证明，add by zjm 20191015
					url = basePath + 'cx/hjywxx/qczxcx?hzywid=' + id + "&pch=" + pch + "&ywlb=" + data.ywlb;
					if(!getQueryParam("ywid")){
						parent.openWorkSpace(url,  ywlbm + "【" + data.bsqrxm + "】", "_hzywcl_" + id);
					}else{
						window.location.href = url;
					}
					return;
				}
				if(data.ywlb=='21'){
					//提供在国外定居或加入外国国籍注销户口证明，add by zjm 20191015
					url = basePath + 'cx/hjywxx/qczxcx?hzywid=' + id + "&pch=" + pch + "&ywlb=" + data.ywlb;
					if(!getQueryParam("ywid")){
						parent.openWorkSpace(url,  ywlbm + "【" + data.bsqrxm + "】", "_hzywcl_" + id);
					}else{
						window.location.href = url;
					}
					return;
				}
				if(data.ywlb=='22'){
					//公民是否同一人的协助核查证明，add by zjm 20191015
					url = basePath + 'cx/hjywxx/gmsftyrxzhc?hzywid=' + id + "&pch=" + pch;
					if(!getQueryParam("ywid")){
						parent.openWorkSpace(url,  ywlbm + "【" + data.bsqrxm + "】", "_hzywcl_" + id);
					}else{
						window.location.href = url;
					}
					return;
				}
				if(data.ywlb=='23'){
					//当事人未登记户口的证明，add by zjm 20191015
					url = basePath + 'cx/hjywxx/wdjhkcx?hzywid=' + id + "&pch=" + pch;
					if(!getQueryParam("ywid")){
						parent.openWorkSpace(url,  ywlbm + "【" + data.bsqrxm + "】", "_hzywcl_" + id);
					}else{
						window.location.href = url;
					}
					return;
				}
				if(data.ywlb=='24'){
					//迁出注销证明打印
					url = basePath + 'cx/hjywxx/qczxcx?hzywid=' + id + "&pch=" + pch+ "&ywlb=" + data.ywlb;
					if(!getQueryParam("ywid")){
						parent.openWorkSpace(url,  ywlbm + "【" + data.bsqrxm + "】", "_hzywcl_" + id);
					}else{
						window.location.href = url;
					}
					return;
				}
				if(data.ywlb=='25'){
					//户口性质证明打印
					url = basePath + 'cx/hjjbxx/ckxx?hzywid=' + id + "&pch=" + pch + "&ywlb=" + data.ywlb;
					if(!getQueryParam("ywid")){
						parent.openWorkSpace(url,  ywlbm + "【" + data.bsqrxm + "】", "_hzywcl_" + id);
					}else{
						window.location.href = url;
					}
					return;
				}
				if(data.ywlb=='26'){
					//户籍证明打印
					url = basePath + 'cx/hjjbxx/hjzmlscx?pch=' + pch ;
					if(!getQueryParam("ywid")){
						parent.openWorkSpace(url,  ywlbm + "【" + data.bsqrxm + "】", "_hzywcl_" + id);
					}else{
						window.location.href = url;
					}
					return;
				}
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
		items:[{
			id:"northId",
			region:'north',
			layout:'border',
			title:'查询条件<span class="text" style="text-align:center;display:inline-block;width:95%;">(无条件输入时查本辖区内所有信息)</span>',
//			width:'100%',
			height:150,
			items:[form50008,
				{
					id:"northEastId",
					region:'east',
					width:'100',
					html:'',
					bodyStyle:'padding:10px 5px 10px 5px',
					layout:'table',
					align:'center',
					border:false,
					frame:false,
					buttonAlign: 'center',
					layoutConfig: {
						columns: 1
					},
					items:[
						new Ext.Button({
							id:'queryId',
			    			text:'查询',
			    			minWidth:80,
			    			handler:function(){
			    				var data = form50008.getForm().getValues();
		        				data.log_code = "";
		        				var store = grid.store;
		        				store.removeAll();
		        				store.baseParams = data;
		        				store.load({params:{start:0, limit:50}});
		        				
			    			}
						})
						,{
							height:10,
							border:false,
							frame:false
						}
						,new Ext.Button({
							text:'删除',
							minWidth:75,
							handler:function(){
								var res = grid.getSelectionModel().getSelections();
								var ids = "";
								var isdel = true;
								Ext.each(res,function(r){
									ids += "," + r.data.id;
									if(r.data.clbs!='0'){
										isdel = false;
									}
								});
								
								if(!isdel){
									showErr("只能删除未处理状态记录！");
									return;
								}
								
								if(ids==""){
									showErr("必须选择需要删除的记录！");
									return;
								}
								
								showInput("确定删除选择记录，请输入删除原因！", "", function(value){
									Gnt.submit(
											{
												hzywid: ids,
												zxyy: value,
												charset:'utf-8'
											}, 
											"yw/hjyw/hzywcl/updateHzyw", 
											"正在删除数据，请稍后...", 
											function(jsonData,params){
												showInfo("删除成功！",function(){
													//关闭当前窗口
													Ext.each(res,function(r){
														grid.store.remove(r);
													});
												});
											}
										);
								});
							}
						}),{
							height:10,
							border:false,
							frame:false
						}
						,new Ext.Button({
							text:'办结',
							minWidth:75,
							handler:function(){
								var res = grid.getSelectionModel().getSelections();
								var ids = "";
								var isdel = true;
								Ext.each(res,function(r){
									ids += "," + r.data.id;
									if(r.data.clbs!='0'){
										isdel = false;
									}
								});
								
								if(!isdel){
									showErr("只能办结未处理状态记录！");
									return;
								}
								
								if(ids==""){
									showErr("必须选择需要办结的记录！");
									return;
								}
								
								Gnt.submit(
								{
									hzywid: ids,
									charset:'utf-8'
								}, 
								"yw/hjyw/hzywcl/updateHzywBj", 
								"正在办结数据，请稍后...", 
								function(jsonData,params){
									showInfo("办结成功！",function(){
										//关闭当前窗口
										Ext.each(res,function(r){
											grid.store.remove(r);
										});
									});
								});
							}
						})
						
					]
				}
			]
			
		}
			,grid
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
//    new Ext.Viewport({
//	    layout: 'border',
//	    items: [{
//	    	height:280,
//	        region: 'north',
//	        border: false,
//	        closable:true,
////	        layout:'fit',
//	        items:[{
//	        	region: 'center',
//	        	items:[form50008]
//	        },
//				{
//				id:"northEastId",
//				region:'east',
//				width:'400',
//				html:'',
//				bodyStyle:'padding:10px 5px 10px 5px',
//				layout:'table',
//				align:'right',
//				border:false,
//				frame:false,
//				buttonAlign: 'right',
//				layoutConfig: {
//					columns: 1
//				},
//				items:[
//					new Ext.Button({
//						id:'queryId',
//		    			text:'查询',
//		    			minWidth:80,
//		    			handler:function(){
//		    				
//		    				var data = cxForm.getForm().getValues();
//	        				data.log_code = "F2008";
//	        				var store = ywGrid.store;
//	        				
//	        				store.removeAll();
//	        				
//	        				store.baseParams = data;
//	        				
//	        				store.load({params:{start:0, limit:20}});
//	        				
//	        				Ext.getCmp('dzxgId').setDisabled(false);
//	        				
//		    			}
//					})
//				]
//			}
//		]
//	    },{
//	        region: 'center',
//	        html: '',
//	        width:200,
//	        border: false,
//	        closable:true,
//	        layout:'fit',
//	        margins: '0 5 0 5',
//	        items:[grid]
//	    }]
//	});
    
	/**
		对接,需要自动跳转到业务页面
		第三方页面集成接口.doc	确认ywid
	 */
	if(getQueryParam("ywid")){
		myuserStore.baseParams = {
			ywid:getQueryParam("ywid")
		};
		
		myuserStore.load({params:{start:0, limit:50}});
		
		myuserStore.on('load',function(s,records){
			if(records.length>0){//数据库查询到符合条件的数据，点击第一条
				grid.fireEvent("rowdblclick",grid,0);
			}else{//没有符合条件的数据
				alert("没有查到符合条件的数据，请确认是否办结！");
			}
			
		});
	}else{
		myuserStore.baseParams = {
			clbs:'0'
		};
		myuserStore.load({params:{start:0, limit:50}})
	}
	
});