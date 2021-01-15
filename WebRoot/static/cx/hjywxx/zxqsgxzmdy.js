var selRecord = null;
var mxfs = null;
var win = null;
var hzywjo = null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	var hzywid = getQueryParam("hzywid");
	var pch = getQueryParam("pch");
	var ywlb = getQueryParam("ywlb");
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_XB','CS_JTGX','DWXXB'],function(){});
	Ext.Msg.minWidth = 100;
	var myMask = new Ext.LoadMask(Ext.getBody(), {msg:"正在保存数据，请等待..."});
	
	var sfbz = new Ext.data.SimpleStore({
		id:0,
		fields:[{name: 'code', mapping: 0},{name: 'name', mapping: 1}],
	   	data:[['0','有效'],['1','无效']]
	});
	
 	var myuserStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'cx/hjywxx/zxqsgxzmdy/queryZxqsgx',//yw/hjyw/hzywcl/queryHzywcl
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        id:'id',
        totalProperty:'totalCount',
        fields: [
			"zxqsgxid",
			"xm1",
			"xb1",
			"csrq1",
			"gmsfhm1",
			"xm2",
			"xb2",
			"csrq2",
			"gmsfhm2",
			"zxqsgx",
			"pcs",
			"rynbid"
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
		           	columnWidth:.25,
	    	        layout: 'form',
	    	        labelWidth:100,
	    	        bodyStyle:'padding:0 0 0 0',
	        	    items: [{
						xtype:'textfield',
						anchor:'100%',
						vtype:'Sfzh',
						autoCreate: {tag: 'input', type: 'text', size: '20', autocomplete: 'off', maxlength: '18'},
						name:'gmsfhm1',
						fieldLabel:'户主身份证'
					}]
				},{
		           	columnWidth:.25,
	    	        layout: 'form',
	    	        labelWidth:120,
	    	        bodyStyle:'padding:0 0 0 0',
	        	    items: [{
						xtype:'textfield',
						anchor:'100%',
						vtype:'Sfzh',
						autoCreate: {tag: 'input', type: 'text', size: '20', autocomplete: 'off', maxlength: '18'},
						name:'gmsfhm2',
						fieldLabel:'证明人身份号码'
					}]
				},{
		           	columnWidth:.25,
	    	        layout: 'form',
	    	        labelWidth:120,
	    	        bodyStyle:'padding:0 0 0 0',
	        	    items: [{
						xtype:'textfield',
						anchor:'100%',
						name:'zsqsgx',
						fieldLabel:'直属亲戚关系'
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
			}),
			new Ext.Button({
				text:'查询',
				id:'qyeryBtn',
				minWidth:75,
				handler:function(){
					var p =  fs.getForm().getValues();
					if(!fs.getForm().isValid()){
						showErr("表单验证不通过！");
						return;
					}else{
						if(p.gmsfhm1&&p.gmsfhm2&&p.zsqsgx){
							myuserStore.baseParams = p;
							myuserStore.load({params:{start:0, limit:50}})
						}else{
							alert("表单字段不得为空!");
						}
					}
					
					
				}
			}),
			new Ext.Button({
				text:'打印直系亲属关系证明',
				minWidth:75,
				handler:function(){
					if(selRecord){
						 var arrayTemp=[];
	                	 var o={};
						 o.directTable="zxqsgxzm";
						 o.zxqsgxid =selRecord.data.zxqsgxid;
						 o.xm1=selRecord.data.xm1;
						 o.xb1=selRecord.data.xb1;
						 o.csrq1=selRecord.data.csrq1;
						 o.gmsfhm1=selRecord.data.gmsfhm1;
						 o.xm2=selRecord.data.xm2;
						 o.xb2=selRecord.data.xb2;
						 o.csrq2=selRecord.data.csrq2;
						 o.gmsfhm2=selRecord.data.gmsfhm2;
						 o.zxqsgx=selRecord.data.zxqsgx;
						 o.pcs=selRecord.data.pcs;
						 o.rynbid = selRecord.data.rynbid;
						 if(hzywjo&&ywlb=='15'){
							 o.ywlb ='15';
							 o.hzywid = hzywid;
						 }
						 arrayTemp.push(o);
						 printfunction(0,arrayTemp);
						 if(hzywid){
 							Gnt.ux.Dict.getKzcs('10036', function(pz, user) {
 								if(pz.kzz==1){
 									pjq('ES',user.ip,hzywid);
 								}
 							});
 						}
					}else{
						alert("请先选中要打印的条目，再点击打印按钮!");
					}
				}
			})
		]
	});
	
	var csm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var colModel = new Ext.grid.ColumnModel([
		{
			header: "证明号",
	        dataIndex: "zxqsgxid",
	        sortable: true,
			width: 100
		},{
	        header: "户主姓名",
	        dataIndex: "xm1",
	        sortable: true,
			width: 100
	    },{
	        header: "户主性别",
	        dataIndex: "xb1",	
	        sortable: true,
			width:100,
			renderer:function(value){
				return Gnt.ux.Dict.getDictLable('CS_XB', value);;
			}
	    },{
	        header: "户主出生日期",
	        dataIndex: "csrq1",	
	        sortable: true,
			width: 100
	    },{
	        header: "户主身份证号码",
	        dataIndex: "gmsfhm1",
	        sortable: true,
			width: 100
	    },{
	        header: "证明人姓名",
	        dataIndex: "xm2",	
	        sortable: true,
			width: 100
	    },{
	        header: "证明人性别",
	        dataIndex: "xb2",	
	        sortable: true,
			width: 100,
			renderer:function(value){
				return Gnt.ux.Dict.getDictLable('CS_XB', value);;
			}
	    },{
	        header: "证明人出生日期",
	        dataIndex: "csrq2",
	        sortable: true,
			width: 100
	    },{
	        header: "证明人身份证号码",
	        dataIndex: "gmsfhm2",
	        sortable: true,
			width: 100
	    },{
	        header: "直系亲属关系",
	        dataIndex: "zxqsgx",
	        sortable: true,
			width: 100,
			renderer:function(value){
				return Gnt.ux.Dict.getDictLable('CS_JTGX', value);;
			}
	    },{
	        header: "办证派出所",
	        dataIndex: "pcs",
	        sortable: true,
			width: 100,
			renderer:function(value){
				return Gnt.ux.Dict.getDictLable('DWXXB', value);;
			}
	    },{
	        header: "人员内部Id",
	        dataIndex: "rynbid",
	        sortable: true,
	        hidden:true,
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
	//户政业务处理
	if(hzywid && hzywid!=""){
		Gnt.makeHzyw({
			hzywid: hzywid,
			pch:pch,
			cmpids: [],
			callback:function(jo, jolist){
				//成功处理返回，合并数据
				hzywjo_list =[];
				hzywjo_list.push(jo);
				Ext.each(jolist,function(d){
					hzywjo_list.push(d);
				});
				hzywjo = jo;
				if(hzywjo){
					var hzForm =  fs.getForm();
					hzForm.setValues({gmsfhm1:hzywjo.bsqrsfz});
					hzForm.setValues({gmsfhm2:hzywjo.sqrsfz});
					Ext.getCmp("qyeryBtn").handler();
				}
				
			}
		});
	}
});