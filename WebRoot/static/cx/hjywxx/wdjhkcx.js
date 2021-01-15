var selectYw = 1;
var queryFlag = null;
var qybz = 1;
var errorFlag =true;
var selectedSelRes=null;
var hzywjo;
Ext.onReady(function(){
	Ext.QuickTips.init();
	var hzywid = getQueryParam("hzywid");
	var pch = getQueryParam("pch");
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("20000,20021",function(){}))
		return;
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
 	
	var wdjhkGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		//region:'north',
		pzlb: '20000',
		title: '',
		url: 'cx/hjywxx/wdjhkcx/getWdjhkcx',
		region:'center',
		//height:500,
		title: '',
		showPaging:true,
		loadCallback: function(res, store, bind_grid){
			Ext.Msg.hide();
			
			if(res.length>0){
				//Ext.getCmp('card').getLayout().setActiveItem(1);
			}else{
				showInfo("未查到满足条件的数据！");
			}
		},
		listeners:{
			rowclick:function(g, rowIndex, e){
				
			},
			rowdblclick:function(grid,row){
				
			}
		}
	});
	
	var p1_1 = new Ext.form.FormPanel({
		id:'form1',
    	title:'',
    	region:'north',
    	anchor:'100% 100%',
    	buttonAlign:'left',
    	labelAlign:'right',
    	height: 75,
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
	        	    	xtype:'textfield',
						anchor:'100%',
						id:'gmsfhm',
						autoCreate: {tag: 'input', type: 'text', size: '18', autocomplete: 'off', maxlength: '18'},
						name:'gmsfhm',
						fieldLabel:'身份证'
					}]
				},{
		           	columnWidth:.20,
	    	        layout: 'form',
	    	        bodyStyle:'padding:0 0 0 0',
	        	    items: [{
	        	    	xtype:'textfield',
						anchor:'100%',
						id:'xm',
						name:'xm',
						fieldLabel:'姓名'
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
				id:'huInfo',
					disabled:true,	
				text:'未登记户口证明',
				minWidth:60,
				handler:function(){
					var data = p1_1.getForm().getValues();
					if(data.gmsfhm){
						if(wdjhkGrid.store.data.length == 0){
							
							var array=[];
							var o={};
							o.xm=data.xm;
							o.gmsfhm=data.gmsfhm;
							o.directTable="wdjhk";
							if(hzywjo){
								 o.hzywid = hzywid;
							}
							array.push(o);
							printfunction(0,array);
							if(hzywid){
    							Gnt.ux.Dict.getKzcs('10036', function(pz, user) {
    								if(pz.kzz==1){
    									pjq('ES',user.ip,hzywid);
    								}
    							});
    						}
//							if(wdjhkGrid.getSelectionModel().getCount() ==1){
//								var re = wdjhkGrid.getSelectionModel().getSelected();
//								var array=[];
//								var o={};
//								o.xm=re.data.xm;
//								o.directTable="wdjhk";
//								array.push(o);
//								printfunction(0,array);
//				 				
//							}else{
//								alert("请选择需要打印的人!");
//							}
						}
					}else{
						alert("身份证为必填项!");
					}
				}
			}),new Ext.Button({
				text:'查询',
				minWidth:75,
				id:'qyeryBtn',
				handler:function(){
					var p =  p1_1.getForm().getValues();
					delete p.zzid;
					if(isIDCard(p1_1.getForm().getValues().gmsfhm)){
						var store = wdjhkGrid.store;
	    				store.removeAll();
	    				store.baseParams = p;
	    				store.load({params:{start:0, limit:20}});
	    				store.on('load',function(s,records){
	       					if(records.length==0){
	       						Ext.getCmp("huInfo").enable();
	       					}else{
	       						Ext.getCmp("huInfo").disable();
	       					}
	       				});
					}
					
				}
			})
		]
	});	
    
	
	var p1 = new Ext.Panel({
		layout:'border',
		items:[
		       p1_1,
		       wdjhkGrid
		]
	});
	//定义分页面板
	
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
					Ext.getCmp('xm').setValue(hzywjo.bsqrxm);
					Ext.getCmp("qyeryBtn").handler();
				}
				
			}
		});
	}
});