var selRecord=null;
var hzywjo;
Ext.onReady(function(){
	Ext.QuickTips.init();
	var hzywid = getQueryParam("hzywid");
	var pch = getQueryParam("pch");
	var myuserStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'cx/hjywxx/fhcx/getFhcx',//yw/hjyw/hzywcl/queryHzywcl
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        id:'id',
        totalProperty:'totalCount',
        fields: [
			"gmsfhm",
			"xm",
			"isfh"
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
						id:'gmsfhm',
						autoCreate: {tag: 'input', type: 'text', size: '20', autocomplete: 'off', maxlength: '18'},
						name:'gmsfhm',
						fieldLabel:'身份证'
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
					if(p.gmsfhm){
						myuserStore.baseParams = p;
						myuserStore.load({params:{start:0, limit:50}})
					}else{
						alert("身份证号码不得为空!");
					}
					
				}
			}),
			new Ext.Button({
				text:'分户证明',
				minWidth:75,
				handler:function(){
					if(selRecord){
						if(selRecord.data.isfh!=1){
							alert("不存在分户，不需要打印分户证明！")
							return;
						}
						
						 var arrayTemp=[];
	                	 var o={};
						 o.directTable="fhzm";
						 o.gmsfhm=selRecord.data.gmsfhm;
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
	        header: "身份证",
	        dataIndex: "gmsfhm",
	        sortable: true,
			width: 150
	    },{
	        header: "姓名",
	        dataIndex: "xm",	
	        sortable: true,
			width: 100
	    },{
	        header: "是否分户",
	        dataIndex: "isfh",
	        sortable: true,
			width: 100,
			renderer:function(value){
				return value=="1"?"是":"否";
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
					Ext.getCmp('gmsfhm').setValue(hzywjo.bsqrsfz);
					Ext.getCmp("qyeryBtn").handler();
				}
				
			}
		});
	}
});