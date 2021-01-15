var selectedSelRes=null;
var selectedYhSelRes ='';
var selectedYhid='';
var selectedyhipyxszSelRes = '';
var selectedSjfwSelRes ='';
var selecteddtqxSelRes = '';
var selectedyhdzqxSelRes ='';
var qybz  = 1;
Ext.onReady(function(){
	Ext.QuickTips.init();
	Gnt.ux.Dict.loadDict(['CS_YHZW','CS_XB','CS_YHMJ','CS_YHZT','CS_XQLX'],function(){});
	var yhcsm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var yhcolModel = new Ext.grid.ColumnModel([
		{
			header: "yhID",
	        dataIndex: "yhid",
	        sortable: true,
	        hidden:true,
			width: 120
		},{
			header: "用户登录名",
	        dataIndex: "yhdlm",
	        sortable: true,
			width: 60
		},{
			header: "警员号",
	        dataIndex: "jyh",
	        sortable: true,
			width: 60
		},{
			header: "单位名称",
	        dataIndex: "dwmc",
	        sortable: true,
			width: 120
		},{
			header: "单位代码",
	        dataIndex: "dwdm",
	        sortable: true,
			width: 120,
			hidden:true
		},{
			header: "登录口令",
	        dataIndex: "dlkl",
	        sortable: true,
			width: 120,
			hidden:true
		},{
			header: "用户姓名",
	        dataIndex: "yhxm",
	        sortable: true,
			width: 80
		},{
			header: "用户性别",
	        dataIndex: "yhxb",
	        sortable: true,
			width: 60,
			renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
				return Gnt.ux.Dict.getDictLable('CS_XB', value);
			}
		},{
			header: "用户职务",
	        dataIndex: "yhzw",
	        sortable: true,
			width: 120,
			renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
				return Gnt.ux.Dict.getDictLable('CS_YHZW', value);
			}
		},{
			header: "用户密级",
	        dataIndex: "yhmj",
	        sortable: true,
			width: 60,
			renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
				return Gnt.ux.Dict.getDictLable('CS_YHMJ', value);
			}
		},{
			header: "操作密级",
	        dataIndex: "czmj",
	        sortable: true,
			width: 60
		},{
			header: "用户状态",
	        dataIndex: "yhzt",
	        sortable: true,
			width: 60,
			renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
				return Gnt.ux.Dict.getDictLable('CS_YHZT', value);
			}
		},{
			header: "公民身份号码",
	        dataIndex: "gmsfhm",
	        sortable: true,
			width: 120
		},{
			header: "区划代码",
	        dataIndex: "qhdm",
	        sortable: true,
	        hidden:true,
			width: 120
		}
	]);
 	var yhStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'gl/yhgl/queryYh',
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        totalProperty:'totalCount',
        fields: [
        	"yhid",
        	"yhdlm",
			"jyh",
			"dwmc",
			"dwdm",
			"dlkl",
			"yhxm",
			"yhxb",
			"yhzw",
			"yhmj",
			"czmj",
			"yhzt",
			"gmsfhm",
			"qhdm"
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
	var yhGrid = new Ext.grid.GridPanel({
        store: yhStore,
        region: 'center',
        view:new Ext.grid.GridView({
				//forceFit:true,
				//autoFill:true,
				enableRowBody:true
		}),
		stripeRows: true,
        cm: yhcolModel,
        sm:yhcsm,
		loadMask: {msg:'正在加载数据，请稍侯……'},
		//bodyStyle:'width:50%',
        border:true,
        frame:false,
        //anchor:'50% 50%',
	    margins: '0 0 0 0',
		cmargins: '0 0 0 0',        
		iconCls:'icon-grid',
        title:'',
        listeners:{
        	rowclick:function(g, rowIndex, e){
        		selectedYhSelRes = g.store.getAt(rowIndex);
        		if(selectedYhSelRes){
        			selectedYhid = selectedYhSelRes.data.yhid;
        			Ext.getCmp('p2yhname').setValue(selectedYhSelRes.data.yhxm);
        			Ext.getCmp('p3yhname').setValue(selectedYhSelRes.data.yhxm);
        			Ext.getCmp('p4yhname').setValue(selectedYhSelRes.data.yhxm);
        			Ext.getCmp('p5yhname').setValue(selectedYhSelRes.data.yhxm);
        			Ext.getCmp('p6yhname').setValue(selectedYhSelRes.data.yhxm);
        			//initdata(selectedYhid);
        		}
        	},
			rowdblclick:function(g, rowIndex, e){
				selectedYhSelRes = g.store.getAt(rowIndex);
			}
		}
    });
	var yhipyxszcsm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var yhipyxszcolModel = new Ext.grid.ColumnModel([
		{
	        header: "IP允许id",
	        dataIndex: "ipyxid",
	        sortable: true,
	        hidden:true,
			width: 120
	    },{
	        header: "用户姓名",
	        dataIndex: "yhxm",	
	        sortable: true,
			width:120
	    },{
	        header: "用户登录名",
	        dataIndex: "yhdlm",	
	        sortable: true,
			width:120
	    },{
	        header: "允许IP地址",
	        dataIndex: "ipdz",	
	        sortable: true,
			width:120
	    },{
	        header: "创建人姓名",
	        dataIndex: "cjrxm",
	        sortable: true,
			width: 120
	    },{
	        header: "创建时间",
	        dataIndex: "cjsj",	
	        sortable: true,
			width:120
	    }
	]);
 	var yhipyxszStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'gl/xtkzgl/ggyxipset/getGgyxipsetInfo',//yw/hjyw/hzywcl/queryHzywcl
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        id:'id',
        totalProperty:'totalCount',
        fields: [
        	"ipyxid",
        	"yhxm",
        	"yhdlm",
        	"ipdz",
        	"cjrxm",
        	"cjsj"
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
	var yhipyxszGrid = new Ext.grid.GridPanel({
        store: yhipyxszStore,
        region: 'center',
        view:new Ext.grid.GridView({
				//forceFit:true,
				//autoFill:true,
				enableRowBody:true
		}),
		stripeRows: true,
        cm: yhipyxszcolModel,
        sm:yhipyxszcsm,
		loadMask: {msg:'正在加载数据，请稍侯……'},
        border:true,
        height:450,
        anchor:'50% 80%',
	    margins: '0 0 0 0',
		cmargins: '0 0 0 0',        
        frame:false,
		iconCls:'icon-grid',
        title:'',
        listeners:{
        	rowclick:function(g, rowIndex, e){
        		selectedyhipyxszSelRes = g.store.getAt(rowIndex);
        	},
			rowdblclick:function(g, rowIndex, e){
				selectedyhipyxszSelRes = g.store.getAt(rowIndex);
			}
		}/*,
        bbar: new Ext.PagingToolbar({
				pageSize: 20,
				store: yhipyxszStore,
				displayInfo: true
		})*/
    });
	var userfwcsm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var userfwcolModel = new Ext.grid.ColumnModel([
		{
			header: "数据范围ID",
	        dataIndex: "sjfwid",
	        hidden:true,
	        sortable: true,
			width: 120
		},{
			header: "用户登录名",
	        dataIndex: "yhdlm",
	        sortable: true,
			width: 120
		},{
			header: "用户姓名",
	        dataIndex: "yhxm",
	        sortable: true,
			width: 120
		},{
			header: "辖区类型",
	        dataIndex: "xqlx",
	        sortable: true,
			width: 120,
			renderer:function(value){
				return Gnt.ux.Dict.getDictLable('CS_XQLX', value);
			}
		},{
			header: "数据范围",
	        dataIndex: "sjfw",
	        sortable: true,
			width: 250
		}
	   
	]);
 	var userfwStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'gl/xtkzgl/cxsjfwwh/getYhsjfwInfo',
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        totalProperty:'totalCount',
        fields: [
        	"sjfwid",
        	"yhdlm",
        	"yhxm",
        	"xqlx",
			"sjfw"
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
	var userfwInfoGrid = new Ext.grid.GridPanel({
        store: userfwStore,
        region: 'center',
        width:300,
		stripeRows: true,
        cm: userfwcolModel,
        sm:userfwcsm,
		loadMask: {msg:'正在加载数据，请稍侯……'},
        border:true,
        frame:true,
		iconCls:'icon-grid',
        title:'',
        listeners:{
        	rowclick:function(g, rowIndex, e){
        		selectedSjfwSelRes = g.store.getAt(rowIndex);
        	},
			rowdblclick:function(g, rowIndex, e){
				selectedSjfwSelRes = g.store.getAt(rowIndex);
			}
		}/*,
        bbar: new Ext.PagingToolbar({
			pageSize: 20,
			store: userfwStore,
			displayInfo: true
	})*/
    });
	var userdtqxcsm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var userdtqxcolModel = new Ext.grid.ColumnModel([
		{
			header: "等同权限ID",
	        dataIndex: "dtqxid",
	        hidden:true,
	        sortable: true,
			width: 120
		},{
			header: "用户登录名",
	        dataIndex: "yhdlm",
	        sortable: true,
			width: 120
		},{
			header: "用户姓名",
	        dataIndex: "yhxm",
	        sortable: true,
			width: 120
		},{
			header: "等同用户登录名",
	        dataIndex: "dtyhdlm",
	        sortable: true,
			width: 120
		},{
			header: "等同用户姓名",
	        dataIndex: "dtyhxm",
	        sortable: true,
			width: 250
		}
	   
	]);
 	var userdtqxStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'gl/xtkzgl/cxsjfwwh/getYhdtqxInfo',
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        totalProperty:'totalCount',
        fields: [
        	"dtqxid",
        	"yhdlm",
        	"yhxm",
        	"dtyhdlm",
			"dtyhxm"
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
	var userdtqxInfoGrid = new Ext.grid.GridPanel({
        store: userdtqxStore,
        region: 'center',
        width:300,
		stripeRows: true,
        cm: userdtqxcolModel,
        sm:userdtqxcsm,
		loadMask: {msg:'正在加载数据，请稍侯……'},
        border:true,
        frame:true,
		iconCls:'icon-grid',
        title:'',
        listeners:{
        	rowclick:function(g, rowIndex, e){
        		selecteddtqxSelRes = g.store.getAt(rowIndex);
        	},
			rowdblclick:function(g, rowIndex, e){
				selecteddtqxSelRes = g.store.getAt(rowIndex);
			}
		}/*,
        bbar: new Ext.PagingToolbar({
			pageSize: 20,
			store: userdtqxStore,
			displayInfo: true
	})*/
    });
	
	var yhdzqxcsm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var yhdzqxcolModel = new Ext.grid.ColumnModel([
		{
			header: "动作ID",
	        dataIndex: "dzqxid",
	        hidden:true,
	        sortable: true,
			width: 120
		},{
			header: "用户登录名",
	        dataIndex: "yhdlm",
	        sortable: true,
			width: 120
		},{
			header: "用户姓名",
	        dataIndex: "yhxm",
	        sortable: true,
			width: 120
		},{
			header: "动作名称",
	        dataIndex: "dzmc",
	        sortable: true,
			width: 120
		},{
			header: "动作描述",
	        dataIndex: "ms",
	        sortable: true,
			width: 120
		}
	   
	]);
 	var yhdzqxStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'gl/yhgl/getXtyhdzqxxx',
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        totalProperty:'totalCount',
        fields: [
        	"dzqxid",
        	"yhdlm",
        	"yhxm",
        	"dzmc",
			"ms"
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
	var yhdzqxGrid = new Ext.grid.GridPanel({
        store: yhdzqxStore,
        region: 'center',
        width:300,
		stripeRows: true,
        cm: yhdzqxcolModel,
        sm:yhdzqxcsm,
		loadMask: {msg:'正在加载数据，请稍侯……'},
        border:true,
        frame:true,
		iconCls:'icon-grid',
        title:'',
        listeners:{
        	rowclick:function(g, rowIndex, e){
        		selectedyhdzqxSelRes = g.store.getAt(rowIndex);
        	},
			rowdblclick:function(g, rowIndex, e){
				selectedyhdzqxSelRes = g.store.getAt(rowIndex);
			}
		}/*,
        bbar: new Ext.PagingToolbar({
			pageSize: 20,
			store: yhdzqxStore,
			displayInfo: true
	})*/
    });
	var modifyyhwh_window = new Gnt.ux.SelectYhWhModify({
		//选择立户信息回调
		callback: function(optype, jsxxModify){
			//提交数据
			Gnt.submit(
					jsxxModify, 
					"gl/jsgl/modifyJs", 
					"正在修改角色信息，请稍后...", 
					function(jsonData,params){
						showInfo("角色信息修改成功！");
						yhGrid.store.reload(); 
					}
			);
		}
	});
	
	var addyhwh_window = new Gnt.ux.SelectYhWhAdd({
		//选择立户信息回调
		callback: function(optype, jsxxAdd){
			//提交数据
			Gnt.submit(
					jsxxAdd, 
					"gl/jsgl/addJs", 
					"正在新增角色信息，请稍后...", 
					function(jsonData,params){
						showInfo("角色信息新增成功！");
						yhGrid.store.reload(); 
					}
			);
		}
	});
	var addggyxipWindow = new Gnt.ux.SelectGgyxipAdd({
		//选择立户信息回调
		callback: function(optype, ipdzArray){
			ipdzArray.yhid =selectedYhid;
			//提交数据
			Gnt.submit(
					ipdzArray, 
					"gl/xtkzgl/ggyxipset/addGgyxip", 
					"正在增加公共允许IP，请稍后...", 
					function(jsonData,params){
						showInfo("用户允许IP增加成功！");
						yhipyxszGrid.store.reload(); 
					}
			);
		}
	});
	var addsjfw_window = new Gnt.ux.SelectSjfwAdd({
		//选择立户信息回调
		callback: function(optype, ywblxzAdd){
			//提交数据
			Gnt.submit(
					ywblxzAdd, 
					"gl/xtkzgl/cxsjfwwh/addYhsjfwInfo", 
					"正在增加数据范围信息，请稍后...", 
					function(jsonData,params){
						showInfo("数据范围增加成功！");
						userfwStore.reload(); 
					}
			);
		}
	});
	var adddtqx_window = new Gnt.ux.SelectDtqxAdd({
		//选择立户信息回调
		callback: function(optype, dtqxAdd){
			//提交数据
			Gnt.submit(
					dtqxAdd, 
					"gl/xtkzgl/cxsjfwwh/addYhdtqxInfo", 
					"正在增加等同权限信息，请稍后...", 
					function(jsonData,params){
						showInfo("等同权限信息增加成功！");
						userdtqxInfoGrid.store.reload(); 
					}
			);
		}
	});	
	var addyhdzqxWindow = new Gnt.ux.SelectYhdzqxAdd({
		//选择立户信息回调
		callback: function(optype,yhdzqxAdd){
			//提交数据
			Gnt.submit(
					yhdzqxAdd, 
					"gl/yhgl/addYhdzqx",
					"正在增加动作权限信息，请稍后...", 
					function(jsonData,params){
						showInfo("动作权限信息增加成功！");
						yhdzqxStore.reload(); 
					}
			);
		}
	});
	var p1 = new Ext.Panel({
        title: '用户信息',   
        collapsible: false,
        layout:'border',
        border:false,
        frame:false,
        anchor:'100% 100%',
  	    margins: '0 0 0 0',
  		cmargins: '0 0 0 0',
          items:[{
        	  region:'north',
        	  border:false,
              frame:true,
              height:40,
              //bodyStyle: 'padding:5px',
              layout:'column',
              defaults:{
      			border:false,
	       		frame:false
      		},
              items:[
      			{
    				layout: 'form',
    				columnWidth: .2,
    				labelAlign:'right',
    				labelWidth:80,
    				items:[{
    					xtype:'textfield',
    					anchor:'100%',
    					id:'dlmQuery',
    					fieldLabel:"用户登录名"
    				}]
    			},{
    				layout: 'form',
    				columnWidth: .05
    			},{
    				layout: 'form',
    				columnWidth: .2,
    				labelAlign:'right',
    				labelWidth:80,
    				items:[{
    					xtype:'search_combox',
        				anchor:'100%',
        				searchUrl:'dict/utils/searchXzqh.json?qybz=1',
        				id:'qhdmQuery',
        				width: 150,
        				fieldLabel:'所属区划',
        				hiddenName:'xzqhbdm'
    				}]
    			},{
    				layout: 'form',
    				columnWidth: .05
    			},{
    				layout: 'form',
    				columnWidth: .1,
    				items:[new Ext.Button({
            			text:'查询',
            			minWidth:80,
            			handler:function(){
            				if(Ext.getCmp('dlmQuery').getValue()&&!Ext.getCmp('qhdmQuery').getValue()){
            					var store  =  yhGrid.store;
            					if(store.data.length > 0){
            						for(var i= 0;i<store.data.length;i++){
            							var res = store.getAt(i);
            							var a = Ext.getCmp('dlmQuery').getValue();
            							if(a){
            								var c = res.data.yhdlm;
            								if(c.toUpperCase().indexOf(a.toUpperCase())!=-1){
            									yhGrid.fireEvent("rowclick",yhGrid,i);
            									yhGrid.getSelectionModel().selectRange(i,i);
            									yhGrid.getView().focusRow(i);
            									return;
            								}
            							}
            						}
            					}
            				}else{
            					var store = yhGrid.store;
        						store.baseParams = {
        								qhdm:Ext.getCmp('qhdmQuery').getValue(),
        								qybz:qybz
        						};
        						store.load({params:{start:0, limit:9999}});	
        						store.on("load",function(store) {  
        							if(store.data.length > 0){
        								curIndex = 0;
        								yhGrid.fireEvent("rowclick",yhGrid,curIndex);
        								yhGrid.getSelectionModel().selectRange(curIndex,curIndex);
        							}
        						},yhGrid);
            				}
            			}
            		})]
    			}]
          },yhGrid]  
    });
	
	var p2 = new Ext.Panel({
        title: '用户角色权限设置',   
        collapsible: false,
        layout:'border',
        border:false,
        frame:false,
        anchor:'100% 100%',
  	    margins: '0 0 0 0',
  		cmargins: '0 0 0 0',
          items:[{
        	  region:'north',
        	  border:false,
              frame:true,
              height:40,
              //bodyStyle: 'padding:5px',
              layout:'column',
              defaults:{
      			border:false,
	       		frame:false
      		},
              items:[
      			{
    				layout: 'form',
    				columnWidth: .2,
    				labelAlign:'right',
    				labelWidth:80,
    				items:[{
    					xtype:'textfield',
    					anchor:'100%',
    					id:'p2yhname',
    					disabled:true,
    					fieldLabel:"用户姓名"
    				}]
    			},{
    				layout: 'form',
    				columnWidth: .05
    			},{
    				layout: 'form',
    				columnWidth: .1,
    				items:[new Ext.Button({
            			text:'取消操作',
            			minWidth:80,
            			handler:function(){}
            		})]
    			},{
    				layout: 'form',
    				columnWidth: .1,
    				items:[new Ext.Button({
            			text:'取消操作',
            			minWidth:80,
            			handler:function(){}
            		})]
    			}]
          },{
        	  region:'center',
      		layout:'column',
      		height:'85%',
      		//autoHeight : true, 
      		items:[{
      			title: '角色已分配权限',
              	region:'center',
                  xtype: 'fieldset',
                  frame:false,
                  height:'100%',
                  columnWidth: .25,
                  autoHeight : true, 
      			items:[{
      				html:'ssssss'
      				//items:[mbdzGrid]
      				
      			}]
      		},{
      			title: '角色未分配的权限',
              	region:'center',
                  xtype: 'fieldset',
                  frame:false,
                  columnWidth: .75,
                  autoHeight : true, 
                  style:'margin-left:10px',
                  items:[{
      				anchor:'100% 100%'/*,
      				items:[mbspGrid]*/
      			}]
      		}]
          }]  
    });
	var p3 = new Ext.Panel({
        title: '用户Ip允许设置',   
        collapsible: false,
        layout:'border',
        border:false,
        frame:false,
        anchor:'100% 100%',
  	    margins: '0 0 0 0',
  		cmargins: '0 0 0 0',
          items:[{
        	  region:'north',
        	  border:false,
              frame:true,
              height:40,
              //bodyStyle: 'padding:5px',
              layout:'column',
              defaults:{
      			border:false,
	       		frame:false
      		},
              items:[
      			{
    				layout: 'form',
    				columnWidth: .2,
    				labelAlign:'right',
    				labelWidth:80,
    				items:[{
    					xtype:'textfield',
    					anchor:'100%',
    					id:'p3yhname',
    					disabled:true,
    					fieldLabel:"用户姓名"
    				}]
    			},{
    				layout: 'form',
    				columnWidth: .05
    			},{
    				layout: 'form',
    				columnWidth: .1,
    				items:[new Ext.Button({
            			text:'增加用户IP',
            			minWidth:80,
            			handler:function(){
            				addggyxipWindow.show(2);
							addggyxipWindow.setSelRes();
            			}
            		})]
    			},{
    				layout: 'form',
    				columnWidth: .1,
    				items:[new Ext.Button({
            			text:'删除用户IP',
            			minWidth:80,
            			handler:function(){
							if(window.confirm('是否确定要删除【'+selectedyhipyxszSelRes.data.yhxm+'】【'+selectedyhipyxszSelRes.data.ipdz+'】?')){
								Gnt.submit(
								{
									ipyxid:selectedyhipyxszSelRes.data.ipyxid}, 
									"gl/xtkzgl/ggyxipset/delGgyxip", 
									"正在删除公共允许IP，请稍后...", 
									function(jsonData,params){
										showInfo("用户允许IP删除成功！");
										yhipyxszStore.store.reload(); 
									}
								);
							}
            			}
            		})]
    			}]
          },yhipyxszGrid]  
    });
	var p4 = new Ext.Panel({
        title: '用户数据范围设置',   
        collapsible: false,
        layout:'border',
        border:false,
        frame:false,
        anchor:'100% 100%',
  	    margins: '0 0 0 0',
  		cmargins: '0 0 0 0',
          items:[{
        	  region:'north',
        	  border:false,
              frame:true,
              height:40,
              layout:'column',
              defaults:{
      			border:false,
	       		frame:false
      		},
              items:[
      			{
    				layout: 'form',
    				columnWidth: .2,
    				labelAlign:'right',
    				labelWidth:80,
    				items:[{
    					xtype:'textfield',
    					anchor:'100%',
    					id:'p4yhname',
    					disabled:true,
    					fieldLabel:"用户姓名"
    				}]
    			},{
    				layout: 'form',
    				columnWidth: .05
    			},{
    				layout: 'form',
    				columnWidth: .15,
    				items:[new Ext.Button({
            			text:'增加用户数据范围',
            			minWidth:80,
            			handler:function(){
            				addsjfw_window.show(2,selectedYhSelRes);
            				addsjfw_window.setSelRes();
            			}
            		})]
    			},{
    				layout: 'form',
    				columnWidth: .15,
    				items:[new Ext.Button({
            			text:'删除用户数据范围',
            			minWidth:80,
            			handler:function(){
            				if(selectedSjfwSelRes){
            					if(window.confirm('是否确定要删除【'+selectedSjfwSelRes.data.yhxm+'】【'+selectedSjfwSelRes.data.sjfw+'】?')){
        							Gnt.submit(
        							{
        								sjfwid:selectedSjfwSelRes.data.sjfwid}, 
        								"gl/xtkzgl/cxsjfwwh/delYhsjfwInfo", 
        								"正在删除数据范围，请稍后...", 
        								function(jsonData,params){
        									showInfo("数据范围删除成功！");
        									userfwStore.reload(); 
        								}
        							);
        						}
            				}else{
            					alert("请选中一条有效的数据，再点击此按钮！");
            				}
            			}
            		})]
    			}]
          },userfwInfoGrid]  
    });
	var p5 = new Ext.Panel({
        title: '用户等同权限设置',   
        collapsible: false,
        layout:'border',
        border:false,
        frame:false,
        anchor:'100% 100%',
  	    margins: '0 0 0 0',
  		cmargins: '0 0 0 0',
          items:[{
        	  region:'north',
        	  border:false,
              frame:true,
              height:40,
              layout:'column',
              defaults:{
      			border:false,
	       		frame:false
      		},
              items:[
      			{
    				layout: 'form',
    				columnWidth: .2,
    				labelAlign:'right',
    				labelWidth:80,
    				items:[{
    					xtype:'textfield',
    					anchor:'100%',
    					id:'p5yhname',
    					disabled:true,
    					fieldLabel:"用户姓名"
    				}]
    			},{
    				layout: 'form',
    				columnWidth: .05
    			},{
    				layout: 'form',
    				columnWidth: .15,
    				items:[new Ext.Button({
            			text:'增加用户等同权限',
            			minWidth:80,
            			handler:function(){
            				adddtqx_window.show(2,selectedYhSelRes);
    						adddtqx_window.setSelRes();
            			}
            		})]
    			},{
    				layout: 'form',
    				columnWidth: .15,
    				items:[new Ext.Button({
            			text:'删除用户等同权限',
            			minWidth:80,
            			handler:function(){
            				if(selecteddtqxSelRes){
            					if(window.confirm('是否确定要删除【'+selecteddtqxSelRes.data.yhxm+'】的等同用户【'+selecteddtqxSelRes.data.dtyhxm+'】?')){
        							Gnt.submit(
        							{
        								dtqxid:selecteddtqxSelRes.data.dtqxid,
        								yhid:selecteddtqxSelRes.data.yhid}, 
        								"gl/xtkzgl/cxsjfwwh/delYhdtqxInfo", 
        								"正在删除等同用户信息，请稍后...", 
        								function(jsonData,params){
        									showInfo("等同用户信息删除成功！");
        									userdtqxStore.reload(); 
        								}
        							);
        						}
            				}else{
            					alert("请选中一条有效的数据，再点击此按钮！");
            				}
            			}
            		})]
    			}]
          },userdtqxInfoGrid]  
    });
	var p6 = new Ext.Panel({
        title: '用户动作权限设置',   
        collapsible: false,
        layout:'border',
        border:false,
        frame:false,
        anchor:'100% 100%',
  	    margins: '0 0 0 0',
  		cmargins: '0 0 0 0',
          items:[{
        	  region:'north',
        	  border:false,
              frame:true,
              height:40,
              layout:'column',
              defaults:{
      			border:false,
	       		frame:false
      		},
              items:[
      			{
    				layout: 'form',
    				columnWidth: .2,
    				labelAlign:'right',
    				labelWidth:80,
    				items:[{
    					xtype:'textfield',
    					anchor:'100%',
    					id:'p6yhname',
    					disabled:true,
    					fieldLabel:"用户姓名"
    				}]
    			},{
    				layout: 'form',
    				columnWidth: .05
    			},{
    				layout: 'form',
    				columnWidth: .15,
    				items:[new Ext.Button({
            			text:'增加用户动作权限',
            			minWidth:80,
            			handler:function(){
            				addyhdzqxWindow.show(selectedYhSelRes);
            				addyhdzqxWindow.setSelRes(selectedYhSelRes);
            			}
            		})]
    			},{
    				layout: 'form',
    				columnWidth: .15,
    				items:[new Ext.Button({
            			text:'删除用户动作权限',
            			minWidth:80,
            			handler:function(){
            				if(selectedyhdzqxSelRes){
            					if(window.confirm('是否确定要删除【'+selectedyhdzqxSelRes.data.yhxm+'】的动作【'+selectedyhdzqxSelRes.data.dzmc+'】?')){
        							Gnt.submit(
        							{
        								dzqxid:selectedyhdzqxSelRes.data.dzqxid,
        								yhid:selectedyhdzqxSelRes.data.yhid}, 
        								"gl/yhgl/delYhdzqxInfo", 
        								"正在删除用户动作权限信息，请稍后...", 
        								function(jsonData,params){
        									showInfo("用户动作权限信息删除成功！");
        									yhdzqxStore.reload(); 
        								}
        							);
        						}
            				}else{
            					alert('请选中药删除的数据，再点击删除！');
            				}
            			}
            		})]
    			}]
          },yhdzqxGrid]  
    });
	  var tabs = new Ext.TabPanel({   		  
          region: 'center',
          margins: '3 3 3 0',   
          activeTab: 0,   
          defaults: {   
             autoScroll: false   
          },  
          items: [p1,p2,p3,p4,p5,p6],
          listeners:{
        	  tabchange:function(tp,p){
        		  if(p.title=='用户角色权限设置'){//p2
        			  alert("p2");
        		  }else if(p.title=='用户Ip允许设置'){//p3
        			  //alert("p3");
        			  initP3(selectedYhid);
        		  }else if(p.title=='用户数据范围设置'){//p4
        			  //alert("p4");
        			  initP4(selectedYhid);
        		  }else if(p.title=='用户等同权限设置'){//p5
        			  //alert("p5");
        			  initP5(selectedYhid);
        		  }else if(p.title=='用户动作权限设置'){//p6
        			  //alert("p6");
        			  initP6(selectedYhid);
        		  }
        	  }
          }
     });
	var p_1 = new Ext.Panel({
		xtype: 'panel',
		border:false,
		frame: false,
    	region:'north',
    	height: 45,
    	bodyStyle: 'padding:10px',
		layout:'table',
		layoutConfig: {
        	columns: 30
        },
        items:[
			{
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
    		},{
				border:false,
				frame: false,
				items:[new Ext.Button({
            		text:'增加用户',
					minWidth:80,
					handler:function(){
						addyhwh_window.show(selectedSelRes);
						addyhwh_window.setSelRes(selectedSelRes);
					}
            	})]
			},{
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
    		},{
    			frame:false,
				border:false,
				items:[new Ext.Button({
            		text:'修改用户',
					minWidth:80,
					handler:function(){
						if(selectedSelRes){
							modifyyhwh_window.show(selectedSelRes);
							modifyyhwh_window.setSelRes(selectedSelRes);
						}else{
							alert("请选中一条有效的数据，再点击修改！");
						}
					}
            	})]
			},{
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
    		},{
				border:false,
				frame: false,
    		    items:[new Ext.Button({
            		text:'删除用户',
					minWidth:80,
					handler:function(){
						if(!selectedSelRes){
							alert("请至少选择一条有效的数据!");
							return;
						}else{
							if(window.confirm('是否确定要删除角色名为【'+selectedSelRes.data.jsmc+'】的用户信息?')){
								Gnt.submit(
								{
									jsid:selectedSelRes.data.jsid}, 
									"gl/jsgl/delJs", 
									"正在删除角色信息，请稍后...", 
									function(jsonData,params){
										showInfo("角色信息删除成功！");
										yhGrid.store.reload(); 
									}
								);
							}
						}

					}
            	})]
    		},{
				frame:false,
				border:false,
				xtype:'panel',
				html:'',
				width:10
    		},{
				border:false,
				frame: false,
    		    items:[new Ext.Button({
            		text:'关闭窗体',
					minWidth:80,
					handler:function(){
						window.parent.closeWorkSpace();
					}
            	})]
    		}]
    });	  
	 	
	 var borderPanel = new Ext.Panel({
	        layout: 'border',
	        tltle: '',
	        //width: 1000,
	       // height: 800,
	        defaults: {
	            collapsible: true, // 支持该区域的展开和折叠
	            split: false, // 支持用户拖放改变该区域的大小
	            bodyStyle: 'padding:15px'
	        },
	        items: [p_1, {
	            title: '',
	            region: 'center',
	            collapsible: false,
	            layout:'border',
				border:false,
	            frame:false,	      
	            items:[tabs]	            
	        }]
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
	        	items:[borderPanel]
	        }
	    });
		
	 
	var csstore = yhGrid.store;
	csstore.load({params:{qybz:qybz,start:0, limit:9999}});
	csstore.on("load",function(store) {  
		if(csstore.data.length > 0){
			curIndex = 0;
			yhGrid.fireEvent("rowclick",yhGrid,curIndex);
			yhGrid.getSelectionModel().selectRange(curIndex,curIndex);
		}
	},yhGrid);
//	function initdata(yhid){
//		initP3(yhid);
//		initP4(yhid);
//		initP5(yhid);
//		initP6(yhid);
//	}
	
	function initP3(yhid){
		yhipyxszStore.load({params:{yhid:yhid,start:0, limit:9999}});
		yhipyxszStore.on("load",function(store) {
			if(yhipyxszStore.data.length > 0){
				yhipyxszGrid.fireEvent("rowclick",yhipyxszGrid,0);
				yhipyxszGrid.getSelectionModel().selectRange(0,0);
			}
		},yhipyxszGrid);
	}
	function initP4(yhid){
		userfwStore.removeAll();
		userfwStore.load({params:{yhid:yhid,start:0, limit:9999}});
		userfwStore.on("load",function(store) { 
			if(userfwStore.data.length > 0){
				userfwInfoGrid.fireEvent("rowclick",userfwInfoGrid,0);
				userfwInfoGrid.getSelectionModel().selectRange(0,0);
			}
		},userfwInfoGrid);
	}
	function initP5(yhid){
		var p5store = userdtqxInfoGrid.store;
		userdtqxStore.load({params:{yhid:yhid,start:0, limit:9999}});
		userdtqxStore.on("load",function(store) {  
			if(userdtqxStore.data.length > 0){
				userdtqxInfoGrid.fireEvent("rowclick",userdtqxInfoGrid,0);
				userdtqxInfoGrid.getSelectionModel().selectRange(0,0);
			}
		},userdtqxInfoGrid);
	}
	function initP6(yhid){
		yhdzqxStore.load({params:{yhid:yhid,start:0, limit:9999}});
		yhdzqxStore.on("load",function(store) {
			if(yhdzqxStore.data.length > 0){
				yhdzqxGrid.fireEvent("rowclick",yhdzqxGrid,0);
				yhdzqxGrid.getSelectionModel().selectRange(0,0);
			}
		},yhdzqxGrid);
	}
});