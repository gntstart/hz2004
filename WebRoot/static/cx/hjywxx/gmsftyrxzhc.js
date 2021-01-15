var selRecord = null;
var mxfs = null;
var win = null;
var hzywjo;
Ext.onReady(function(){
	Ext.QuickTips.init();
	var hzywid = getQueryParam("hzywid");
	var pch = getQueryParam("pch");
	var ywlb = getQueryParam("ywlb");
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_XB','CS_JTGX','DWXXB'],function(){});
	Ext.Msg.minWidth = 100;
	var myMask = new Ext.LoadMask(Ext.getBody(), {msg:"正在保存数据，请等待..."});
	
 	var myuserStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'cx/hjywxx/gmsftyrxzhc/queryGmsftyrxzhc',//yw/hjyw/hzywcl/queryHzywcl
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        id:'id',
        totalProperty:'totalCount',
        fields: [
			"gmsfhm1",
			"xm1",
			"gmsfhm2",
			"istyr",
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
						id:'gmsfhm1',
						autoCreate: {tag: 'input', type: 'text', size: '20', autocomplete: 'off', maxlength: '18'},
						name:'gmsfhm1',
						fieldLabel:'身份证一'
					}]
				},{
		           	columnWidth:.25,
	    	        layout: 'form',
	    	        labelWidth:120,
	    	        bodyStyle:'padding:0 0 0 0',
	        	    items: [{
						xtype:'textfield',
						anchor:'100%',
						id:'gmsfhm2',
						autoCreate: {tag: 'input', type: 'text', size: '20', autocomplete: 'off', maxlength: '18'},
						name:'gmsfhm2',
						fieldLabel:'身份证二'
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
					selRecord = null;
					var p =  fs.getForm().getValues();
					delete p.zzid;
					if(p.gmsfhm1&&p.gmsfhm2){
						myuserStore.baseParams = p;
						myuserStore.load({params:{start:0, limit:50}})
					}else{
						alert("申请人和被申请人号码不得为空!");
					}
					
				}
			}),
			new Ext.Button({
				text:'公民是否同一人的协助核查证明',
				minWidth:75,
				handler:function(){
					if(selRecord){
						 var arrayTemp=[];
	                	 var o={};
						 o.directTable="sftyrxzhczm";
						 o.gmsfhm1=selRecord.data.gmsfhm1;
						 o.gmsfhm2=selRecord.data.gmsfhm2;
						 o.xm1=selRecord.data.xm1;
						 o.rynbid = selRecord.data.rynbid;
						 if(hzywjo){
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
	        header: "身份证一",
	        dataIndex: "gmsfhm1",
	        sortable: true,
			width: 150
	    },{
	        header: "姓名一",
	        dataIndex: "xm1",	
	        sortable: true,
			width: 100
	    },{
	        header: "身份证二",
	        dataIndex: "gmsfhm2",	
	        sortable: true,
			width:150,
			renderer:function(value){
				return Gnt.ux.Dict.getDictLable('CS_XB', value);;
			}
	    },{
	        header: "是否同一人",
	        dataIndex: "istyr",
	        sortable: true,
			width: 100,
			renderer:function(value){
				return value=="1"?"是":"否";
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
					Ext.getCmp('gmsfhm1').setValue(hzywjo.bsqrsfz);
					Ext.getCmp('gmsfhm2').setValue(hzywjo.sqrsfz);
					Ext.getCmp("qyeryBtn").handler();
				}
				
			}
		});
	}
});