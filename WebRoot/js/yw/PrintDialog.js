//要使用配置，请确保使用之前加载，否则会出现错误提示
//if(!Gnt.loadSjpzb("20000",function(){}))
//		return;
//	
/**
 * 
 * 必须先加载commFrames.js
 * 
  var d = new Gnt.ux.PrintDialog({
										name:'test',
										rypk:'ywid',
										printset: {
											printset_1: {
									        	checked: false,
									        	disabled: true
											}
										},
										ryset:{
											ryset_1:{
									        	checked: false,
									        	disabled: true
											}
										},
										callback:function(data){
												//回调
										}
									});
d.setDataStore(qrdjGrid.store);
d.setDataList([
	rynbid:'1',
	xm:'测试1',
	gmsfhm:'xxxxx1'
],[
	rynbid:'2',
	xm:'测试2',
	gmsfhm:'xxxxx2'
],[
	rynbid:'3',
	xm:'测试3',
	gmsfhm:'xxxxx3'
]);
d.show();


 */
var hhnbidTemp;
var rynbidTemp;
var SelectedArray2=[];
var arrayTemp=[];
var array5=[];
var data;
var hzywjo=null;
var syCount;//打印首页数
var nyCount;//打印内页数
var sfxxbid;//收费表id
var person_dy_obj={};
Gnt.ux.PrintDialog = Ext.extend(Ext.Window, {
	title:'户籍打印',
	closeAction:'hide',
	modal:true,
	width:700,
	height:495,
	margins:'5',
	layout:'border',
	rypk: 'rynbid',
	getData: function(){
		var data = {
				"ryset": this.ryset,
				"printset": this.printset,
				"ry":{}
		}
		for(var index=1;index<=5;index++){
			var grid = this["grid" + index];
			var res = grid.getSelectionModel().getSelections();
			if(res.length>0){
				data.ry["list" + index] = [];
				Ext.each(res,function(r){
					data.ry["list" + index].push(r.data);
				});
			}
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
		for(var i=0;i<list.length;i++){
			for(var index=1;index<=5;index++){
				var store = this["grid" + index].store;
				var pkvalue = list[i][this.rypk];
				
				var rr = new store.reader.recordType(list[i], pkvalue);
				store.add([rr]);
			}
		}
	},
	//人员设置复选数据
	ryset:{},
	//打印设置复选数据
	printset:{
//		printset_1: {
//        	checked: person_dy_obj.dyyl_dysz==1?true:false,
//        	disabled: false
//		},printset_2: {
//	    	checked: person_dy_obj.tcdysz_dysz==1?true:false,
//	    	disabled: false
//		},printset_3: {
//	    	checked: person_dy_obj.dyzp_cbsz==1?true:false,
//	    	disabled: false
//		},printset_4: {
//	    	checked: person_dy_obj.jth_syksz==1?true:false,
//    	    disabled: false
//		},printset_5: {
//	    	checked: person_dy_obj.hkbsy_hkbsz==1?true:false,
//	    	disabled: false
//		},printset_6: {
//        	checked: person_dy_obj.hkbbm_hkbsz==1?true:false,
//        	disabled: false
//		},printset_7: {
//	    	checked: person_dy_obj.jthfshksy_hkbsz==1?true:false,
//	    	disabled: false
//		},printset_8: {
//	    	checked: person_dy_obj.jthfshky_hkbsz==1?true:false,
//	    	disabled: false
//		},printset_9: {
//	    	checked: person_dy_obj.csyy_hkbsz==1?true:false,
//    	   disabled: false
//		},printset_10: {
//	    	checked: person_dy_obj.dyzp_hjzmsz==1?true:false,
//	    	disabled: false
//		},printset_11: {
//        	checked: person_dy_obj.dydw_hjzmsz==1?true:false,
//        	disabled: false
//		},printset_12: {
//	    	checked: person_dy_obj.hcyxx_hjzmsz==1?true:false,
//	    	disabled: false
//		},printset_13: {
//	    	checked: person_dy_obj.dyhh_hjzmsz==1?true:false,
//	    	disabled: false
//		},printset_14: {
//	    	checked: person_dy_obj.bdyy_hjzmsz==1?true:false,
//    	   disabled: false
//		},printset_15: {
//	    	checked: person_dy_obj.dyhyzk_hjzmsz==1?true:false,
//	    	disabled: false
//		},printset_16: {
//        	checked: person_dy_obj.bdxx_hjzmsz==1?true:false,
//        	disabled: false
//		},printset_17: {
//	    	checked: person_dy_obj.dybyqk_hjzmsz==1?true:false,
//	    	disabled: false
//		},printset_18: {
//	    	checked: person_dy_obj.zxryxx_hjzmsz==1?true:false,
//	    	disabled: false
//		},printset_19: {
//	    	checked: person_dy_obj.dywhcd_hjzmsz==1?true:false,
//    	   disabled: false
//		}
	},
//	resetGrid:function(){
//		var win = this.findParentByType("print_dialog");
//		for(var index=1;index<=5;index++){
//			this["grid" + index].store.removeAll();
//		}
//		
//	},
	createCheckbox:function(config){
		var datatype = config.datatype;
		if(!datatype) datatype = "printset";
		
		if(datatype == "printset"){
			config.listeners = {
					check:function(chk,value){
						var p = chk.findParentByType("print_dialog");
						p.printset[chk.name].checked = value;
						if(chk.name=='printset_1'){
							if(p.printset[chk.name].checked){
								
							}else{
								
							}
						}
					}
			};
			var s = this.printset[config.name];
			if(s){
				Ext.apply(config, s);
			}
		}else{
			config.listeners = {
					check:function(chk,value){
						var p = chk.findParentByType("print_dialog");
						p.ryset[chk.name].checked = value;
						var chkName= chk.name;
						if(chkName=='ryset_1'||chkName=='ryset_2'||chkName=='ryset_3'||chkName=='ryset_4'||chkName=='ryset_5'){
							if(p.ryset[chkName].checked){
								funSelected(chkName.slice(6,7),true);
							}else{
								funSelected(chkName.slice(6,7),false);
							}
						}
					}
			};
//			var s = this.ryset[config.name];
//			if(s){
//				Ext.apply(config, s);
//			}
		}
		
		return config;
	},
	createGrid:function(config){
		var pk = this.rypk;
		if(!pk){
			pk = "rynbid";
		}
		this.rypk = pk;
		
	 	var store = new Ext.data.JsonStore({
	        root: 'list',
	        id: pk,
	        totalProperty:'totalCount',
	        fields: [
	            pk,
				"xm",
				"gmsfhm"
	        ]
	    });
	
		var csm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
		var colModel = new Ext.grid.ColumnModel([
			csm,{
		        header: "姓名",
		        dataIndex: "xm",
		        sortable: true,
				width: 100
		    }
		]);
		var grid = new Ext.grid.GridPanel({
	        store: store,
	        region : 'center',
	        columnWidth: 1,
	        view:new Ext.grid.GridView({
					forceFit:true,
					autoFill:true,
					enableRowBody:true
			}),
			stripeRows: true,
			hideHeaders : true, 
	        cm: colModel,
	        sm:csm,
			loadMask: {msg:'正在加载数据，请稍侯……'},
			bodyStyle:'width:100%',
	        border:true,
	        anchor:'100% 80%',
		    margins: '0 0 0 0',
			cmargins: '0 0 0 0',
	        frame:false,
			iconCls:'icon-grid',
	        title:'',
	        
//	        bbar: new Ext.PagingToolbar({
//				pageSize: 99999,
//				store: store,
//				displayInfo: true
//	        }),
	        
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
		var that = this;
		Gnt.submit(
				{}, 
				"yw/common/getPersonDyset.json", 
				"正在查询个人打印设置，请稍后...", 
				function(jsonData,params){
					if(jsonData&&jsonData.list&&jsonData.list[0]){
						person_dy_obj = jsonData.list[0];
						Ext.applyIf(that.printset, {
								printset_1: {
					        	checked: person_dy_obj.dyyl_dysz==1?true:false,
					        	disabled: false
							},printset_2: {
						    	checked: person_dy_obj.tcdysz_dysz==1?true:false,
						    	disabled: false
							},printset_3: {
						    	checked: person_dy_obj.dyzp_cbsz==1?true:false,
						    	disabled: false
							},printset_4: {
						    	checked: person_dy_obj.jth_syksz==1?true:false,
					    	    disabled: false
							},printset_5: {
						    	checked: person_dy_obj.hkbsy_hkbsz==1?true:false,
						    	disabled: false
							},printset_6: {
					        	checked: person_dy_obj.hkbbm_hkbsz==1?true:false,
					        	disabled: false
							},printset_7: {
						    	checked: person_dy_obj.jthfshksy_hkbsz==1?true:false,
						    	disabled: false
							},printset_8: {
						    	checked: person_dy_obj.jthfshky_hkbsz==1?true:false,
						    	disabled: false
							},printset_9: {
						    	checked: person_dy_obj.csyy_hkbsz==1?true:false,
					    	   disabled: false
							},printset_10: {
						    	checked: person_dy_obj.dyzp_hjzmsz==1?true:false,
						    	disabled: false
							},printset_11: {
					        	checked: person_dy_obj.dydw_hjzmsz==1?true:false,
					        	disabled: false
							},printset_12: {
						    	checked: person_dy_obj.hcyxx_hjzmsz==1?true:false,
						    	disabled: false
							},printset_13: {
						    	checked: person_dy_obj.dyhh_hjzmsz==1?true:false,
						    	disabled: false
							},printset_14: {
						    	checked: person_dy_obj.bdyy_hjzmsz==1?true:false,
					    	   disabled: false
							},printset_15: {
						    	checked: person_dy_obj.dyhyzk_hjzmsz==1?true:false,
						    	disabled: false
							},printset_16: {
					        	checked: person_dy_obj.bdxx_hjzmsz==1?true:false,
					        	disabled: false
							},printset_17: {
						    	checked: person_dy_obj.dybyqk_hjzmsz==1?true:false,
						    	disabled: false
							},printset_18: {
						    	checked: person_dy_obj.zxryxx_hjzmsz==1?true:false,
						    	disabled: false
							},printset_19: {
						    	checked: person_dy_obj.dywhcd_hjzmsz==1?true:false,
					    	   disabled: false
							},printset_20: {
						    	checked: true,
								disabled: false
							},printset_21: {
								checked: true,
								disabled: false
							},printset_22: {
								checked: true,
								disabled: false
							},printset_23: {
								checked: true,
								disabled: false
							},printset_24: {
								checked: true,
								disabled: false
							},printset_25: {
								checked: true,
								disabled: false
							},printset_26: {
								checked: true,
								disabled: false
							},printset_27: {
								checked: true,
								disabled: false
							}
						});
						Ext.applyIf(that.ryset, {
							"ryset_1":{
								checked: false,
					        	disabled: false
							},"ryset_2":{
								checked: false,
					        	disabled: false
							},"ryset_3":{
								checked: false,
					        	disabled: false
							},"ryset_4":{
								checked: false,
					        	disabled: false
							},"ryset_5":{
								checked: false,
					        	disabled: false
							}
						});
						
						var me = that;
						var h = that.height-325;
						
						that.items = [{
							region : 'south',
							height: 250,
							title:'打印设置',
							layout:'column',
							border:false,
							frame:false,
							items:[{
									columnWidth: .30,
									layout:'column',
									border:false,
									frame:false,
									bodyStyle:'padding:5px',
									items:[
											{
												columnWidth: 1,
									            xtype: 'fieldset',
									            title: '打印设置',
									            autoHeight: true,
									            layout:'column',
												bodyStyle:'padding:0 0 0 10px',
									            defaults:{
									            	xtype:'checkbox',
									            	columnWidth: 1,
									            	fieldLabel: '',
									            	checked:true,
									            	name: 'set1'
									            },
									            items: [
									                    me.createCheckbox({
											                boxLabel: '需要进行打印预览',
											                id:'printset_1',
											                name:'printset_1'
														}),
														me.createCheckbox({
											                boxLabel: '打印时弹出打印设置',
											                name:'printset_2'
														})
												]
										},{
											columnWidth: 1,
								            xtype: 'fieldset',
								            title: '常表设置',
								            autoHeight: true,
								            layout:'column',
											bodyStyle:'padding:0 0 0 10px',
								            defaults:{
								            	xtype:'checkbox',
								            	columnWidth: 1,
								            	fieldLabel: '',
								            	checked:true,
								            	name: 'set1-2'
								            },
								            items: [
								                    me.createCheckbox({
										                boxLabel: '打印照片',
										                name:'printset_3'
													})
											]
										},{
											columnWidth: 1,
								            xtype: 'fieldset',
								            title: '索引卡设置',
								            autoHeight: true,
								            layout:'column',
											bodyStyle:'padding:0 0 0 10px',
								            defaults:{
								            	xtype:'checkbox',
								            	columnWidth: 1,
								            	fieldLabel: '',
								            	checked:true,
								            	name: 'set1-3'
								            },
								            items: [
								                    me.createCheckbox({
										                boxLabel: '集体户',
										                name:'printset_4'
													})
											]
										}
									]
								},{
									columnWidth: .70,
									//bodyStyle:'padding:5px',
									layout:'column',
									border:false,
									frame:false,
//									defaults:{
//						            	columnWidth: 0.5
//						            },
									items:[{
										columnWidth: .40,
										layout:'fit',
										border:false,
										frame:false,
										bodyStyle:'padding:5px 5px 0px 5px',
										items:[{
											xtype: 'fieldset',
								            title: '户口簿设置',
								            autoHeight: true,
								            layout:'column',
								            defaults:{
								            	xtype:'checkbox',
								            	columnWidth: 1,
								            	fieldLabel: '',
								            	checked:false,
								            	name: 'set2'
								            },
								            items: [
								                    me.createCheckbox({
										                boxLabel: '打印户口薄首页',
										                name:'printset_5'
													}),me.createCheckbox({
										                boxLabel: '打印户口薄背面',
										                name:'printset_6'
													}),me.createCheckbox({
										                boxLabel: '集体户方式户口首页',
										                name:'printset_7'
													}),me.createCheckbox({
										                boxLabel: '集体户方式户口页',
										                name:'printset_8'
													}),me.createCheckbox({
										                boxLabel: '打印出生原因',
										                name:'printset_9'//,
										                //checked:true
													})
											]}]
							        },{
										columnWidth: 0.6,
										layout:'fit',
										border:false,
										frame:false,
										bodyStyle:'padding:5px 5px 0px 5px ',
										items:[{
								            xtype: 'fieldset',
								            title: '户籍证明设置',
								            autoHeight: true,
								            layout:'column',
								            defaults:{
								            	xtype:'checkbox',
								            	columnWidth: .5,
								            	fieldLabel: '',
								            	checked:true,
								            	name: 'set3'
								            },
								            items: [
								                    me.createCheckbox({
										                boxLabel: '打印照片',
										                name:'printset_10'
													}),me.createCheckbox({
										                boxLabel: '打印单位',
										                name:'printset_11'
													}),me.createCheckbox({
										                boxLabel: '打印户成员信息',
										                name:'printset_12'
													}),me.createCheckbox({
										                boxLabel: '打印户号',
										                name:'printset_13'
													}),me.createCheckbox({
										                boxLabel: '打印变动原因',
										                name:'printset_14'
													}),me.createCheckbox({
										                boxLabel: '打印婚姻状况',
										                name:'printset_15'
													}),me.createCheckbox({
										                boxLabel: '打印变动信息',
										                name:'printset_16'
													}),me.createCheckbox({
										                boxLabel: '打印兵役情况',
										                name:'printset_17'
													}),me.createCheckbox({
										                boxLabel: '打印注销人员信息',
										                checked:false,
										                name:'printset_18'
													}),me.createCheckbox({
										                boxLabel: '打印文化程度',
										                name:'printset_19'
													})
											]
								        }]
									},{
							        	columnWidth: 1.0,
										layout:'fit',
										border:false,
										frame:false,
										bodyStyle:'padding:0px 5px 5px 5px ',
										items:[{
											xtype: 'fieldset',
								            title: '户籍证明(律师用)设置',
								            autoHeight: true,
								            layout:'column',
								            defaults:{
								            	xtype:'checkbox',
								            	columnWidth: .33,
								            	fieldLabel: '',
								            	checked:true,
								            	name: 'set4'
								            },
								            items: [
								                    me.createCheckbox({
										                boxLabel: '打印姓名',
										                name:'printset_20'
													}),me.createCheckbox({
										                boxLabel: '打印性别',
										                name:'printset_21'
													}),me.createCheckbox({
										                boxLabel: '打印民族',
										                name:'printset_22'
													}),me.createCheckbox({
										                boxLabel: '打印出生日期',
										                name:'printset_23'
													}),me.createCheckbox({
										                boxLabel: '打印户口所在地',
										                name:'printset_24'
													}),me.createCheckbox({
										                boxLabel: '打印公民身份号码',
										                name:'printset_25'
													}),me.createCheckbox({
										                boxLabel: '打印照片',
										                name:'printset_26'
													}),me.createCheckbox({
										                boxLabel: '打印所属派出所',
										                name:'printset_27'
													})
											]
										}]
							        }/*{
										layout:'column',
										bodyStyle:'padding:5px',
										border:false,
										frame:false,
										
										//width:200,
										items:[{
								            xtype: 'fieldset',
								            title: '户口簿设置',
								            autoHeight: true,
								            layout:'column',
								            defaults:{
								            	xtype:'checkbox',
								            	columnWidth: 0.4,
								            	fieldLabel: '',
								            	checked:false,
								            	name: 'set2'
								            },
								            items: [
								                    me.createCheckbox({
										                boxLabel: '打印户口薄首页',
										                name:'printset_5'
													}),me.createCheckbox({
										                boxLabel: '打印户口薄背面',
										                name:'printset_6'
													}),me.createCheckbox({
										                boxLabel: '集体户方式户口首页',
										                name:'printset_7'
													}),me.createCheckbox({
										                boxLabel: '集体户方式户口页',
										                name:'printset_8'
													}),me.createCheckbox({
										                boxLabel: '打印出生原因',
										                name:'printset_9'//,
										                //checked:true
													})
											]
								        },{
								            xtype: 'fieldset',
								            title: '户籍证明(律师用)设置',
								            autoHeight: true,
								            layout:'column',
								            defaults:{
								            	xtype:'checkbox',
								            	columnWidth: .6,
								            	fieldLabel: '',
								            	checked:true,
								            	name: 'set4'
								            },
								            items: [
								                    me.createCheckbox({
										                boxLabel: '打印姓名',
										                name:'printset_20'
													}),me.createCheckbox({
										                boxLabel: '打印性别',
										                name:'printset_21'
													}),me.createCheckbox({
										                boxLabel: '打印民族',
										                name:'printset_22'
													}),me.createCheckbox({
										                boxLabel: '打印出生日期',
										                name:'printset_23'
													}),me.createCheckbox({
										                boxLabel: '打印户口所在地',
										                name:'printset_24'
													}),me.createCheckbox({
										                boxLabel: '打印公民身份号码',
										                name:'printset_25'
													}),me.createCheckbox({
										                boxLabel: '打印照片',
										                name:'printset_26'
													}),me.createCheckbox({
										                boxLabel: '打印所属派出所',
										                name:'printset_27'
													})
											]
								        },{
											columnWidth: .40,
											layout:'fit',
											border:false,
											frame:false,
											bodyStyle:'padding:5px',
											items:[{
									            xtype: 'fieldset',
									            title: '户籍证明设置',
									            autoHeight: true,
									            layout:'column',
									            defaults:{
									            	xtype:'checkbox',
									            	columnWidth: .5,
									            	fieldLabel: '',
									            	checked:true,
									            	name: 'set3'
									            },
									            items: [
									                    me.createCheckbox({
											                boxLabel: '打印照片',
											                name:'printset_10'
														}),me.createCheckbox({
											                boxLabel: '打印单位',
											                name:'printset_11'
														}),me.createCheckbox({
											                boxLabel: '打印户成员信息',
											                name:'printset_12'
														}),me.createCheckbox({
											                boxLabel: '打印户号',
											                name:'printset_13'
														}),me.createCheckbox({
											                boxLabel: '打印变动原因',
											                name:'printset_14'
														}),me.createCheckbox({
											                boxLabel: '打印婚姻状况',
											                name:'printset_15'
														}),me.createCheckbox({
											                boxLabel: '打印变动信息',
											                name:'printset_16'
														}),me.createCheckbox({
											                boxLabel: '打印兵役情况',
											                name:'printset_17'
														}),me.createCheckbox({
											                boxLabel: '打印注销人员信息',
											                checked:false,
											                name:'printset_18'
														}),me.createCheckbox({
											                boxLabel: '打印文化程度',
											                name:'printset_19'
														})
												]
									        }]
										}]
									}*/]
								}
							]
						},{
							region : 'center',
							title:'',
							id:'xq',
							layout:'column',
							items:[
								{
									columnWidth: .20,
									height: h,
									frame:false,
							    	border:false,
									layout:'border',
									items:[
									      {
									    	   region : 'north',
									    	   height: 28,
									    	   layout:'fit',
									    	   frame:false,
									    	   border:false,
											   margins: '2 2 2 5',
									    	   items:me.createCheckbox({
									    		   id: 'czrkId',
										    	   columnWidth: 1,
													xtype:'checkbox',
													id : 'ryset_1',
													name: 'ryset_1',
													datatype: 'ryset',
													boxLabel: '常住人口登记表',
													listeners:{
														'rowselect':function(sm,rowIndex,record){
															
														},
														'rowdeselect':function(sm,rowIndex,record){
															
														}
													}
										       })
									    },that.createGrid({name: 'grid1'})
									]
								},{
									columnWidth: .20,
									height: h,
									frame:false,
							    	border:false,
									layout:'border',
									items:[
									      {
									    	   region : 'north',
									    	   height: 28,
									    	   layout:'fit',
									    	   frame:false,
									    	   border:false,
											   margins: '2 2 2 5',
									    	   items:me.createCheckbox({
										    	   columnWidth: 1,
													xtype:'checkbox',
													id : 'ryset_2',
													name: 'ryset_2',
													datatype: 'ryset',
													boxLabel: '居民户口薄'
										       })
									    },that.createGrid({name: 'grid2'})
									]
							    }, {
									columnWidth: .20,
									height: h,
									frame:false,
							    	border:false,
									layout:'border',
									items:[
									      {
									    	   region : 'north',
									    	   id : 'hjzm',
									    	   height: 28,
									    	   layout:'fit',
									    	   frame:false,
									    	   border:false,
											   margins: '2 2 2 5',
									    	   items:me.createCheckbox({
										    	   columnWidth: 1,
													xtype:'checkbox',
													id : 'ryset_3',
													name:'ryset_3',
													datatype: 'ryset',
													boxLabel: '户籍证明'
										       })
									    },that.createGrid({name: 'grid3'})
									]
							    },
							    {
									columnWidth: .20,
									height: h,
									frame:false,
							    	border:false,
									layout:'border',
									items:[
									      {
									    	   region : 'north',
									    	   id : 'djksyb',
									    	   height: 28,
									    	   layout:'fit',
									    	   frame:false,
									    	   border:false,
											   margins: '2 2 2 5',
									    	   items:me.createCheckbox({
										    	   columnWidth: 1,
													xtype:'checkbox',
													id : 'ryset_4',
													name:'ryset_4',
													datatype: 'ryset',
													boxLabel: '登记卡索引表'
										       })
									    },that.createGrid({name: 'grid4'})
									]
							    },
							    {
									columnWidth: .20,
									height: h,
									frame:false,
							    	border:false,
									layout:'border',
									items:[
									      {
									    	   region : 'north',
									    	   id : 'zdydy',
									    	   height: 28,
									    	   layout:'fit',
									    	   frame:false,
									    	   border:false,
											   margins: '2 2 2 5',
									    	   items:me.createCheckbox({
										    	   columnWidth: 1,
													xtype:'checkbox',
													id : 'ryset_5',
													name:'ryset_5',
													datatype: 'ryset',
													boxLabel: '自定义打印'
										       })
									    },that.createGrid({name: 'grid5'})
									]
							    }
							]
						}];
						
						Gnt.ux.PrintDialog.superclass.initComponent.call(that);
					}
				}
		);
	
	},
    buttons:[{
        text:'全户打印',
		minWidth:75,
		handler:function(){
			var win = this.findParentByType("print_dialog");
			for(var i =1;i<=5;i++){
				var grid = win["grid" + i];
				grid.store.removeAll();
			}
			var data = win.getData();
			//this.findParentByType("print_dialog").ryset.ryset_1.checked=true;
			//this.findParentByType("print_dialog").ryset.ryset_3.checked=true;
			//this.findParentByType("print_dialog").ryset.ryset_2.checked=true;
			win.quanhuPrint(hhnbidTemp);
			//win.hide();
		}
    },{
        text:'确定',
		minWidth:75,
		handler:function(){
			var win = this.findParentByType("print_dialog");
			data = win.getData();
			var oldDyst ={
					dyyl_dysz:person_dy_obj.dyyl_dysz,
					tcdysz_dysz:person_dy_obj.tcdysz_dysz,
					dyzp_cbsz:person_dy_obj.dyzp_cbsz,	
					jth_syksz:person_dy_obj.jth_syksz,	
					hkbsy_hkbsz:person_dy_obj.hkbsy_hkbsz,	
					hkbbm_hkbsz:person_dy_obj.hkbbm_hkbsz,	
					jthfshksy_hkbsz:person_dy_obj.jthfshksy_hkbsz,	
					jthfshky_hkbsz:person_dy_obj.jthfshky_hkbsz,	
					csyy_hkbsz:person_dy_obj.csyy_hkbsz,	
					dyzp_hjzmsz:person_dy_obj.dyzp_hjzmsz,	
					hcyxx_hjzmsz:person_dy_obj.hcyxx_hjzmsz,	
					bdyy_hjzmsz:person_dy_obj.bdyy_hjzmsz,	
					bdxx_hjzmsz:person_dy_obj.bdxx_hjzmsz,	
					zxryxx_hjzmsz:person_dy_obj.zxryxx_hjzmsz,	
					dydw_hjzmsz:person_dy_obj.dydw_hjzmsz,	
					dyhh_hjzmsz:person_dy_obj.dyhh_hjzmsz,
					dyhyzk_hjzmsz:person_dy_obj.dyhyzk_hjzmsz,	
					dybyqk_hjzmsz:person_dy_obj.dybyqk_hjzmsz,	
					dywhcd_hjzmsz:person_dy_obj.dywhcd_hjzmsz
			};
			var newPrintset = win.printset;		
			var newDyst ={
					dyyl_dysz:newPrintset.printset_1.checked?1:0,
					tcdysz_dysz:newPrintset.printset_2.checked?1:0,
					dyzp_cbsz:newPrintset.printset_3.checked?1:0,	
					jth_syksz:newPrintset.printset_4.checked?1:0,	
					hkbsy_hkbsz:0,	
					hkbbm_hkbsz:newPrintset.printset_6.checked?1:0,	
					jthfshksy_hkbsz:newPrintset.printset_7.checked?1:0,	
					jthfshky_hkbsz:newPrintset.printset_8.checked?1:0,	
					csyy_hkbsz:newPrintset.printset_9.checked?1:0,	
					dyzp_hjzmsz:newPrintset.printset_10.checked?1:0,
					hcyxx_hjzmsz:newPrintset.printset_12.checked?1:0,	
					bdyy_hjzmsz:newPrintset.printset_14.checked?1:0,	
					bdxx_hjzmsz:newPrintset.printset_16.checked?1:0,	
					zxryxx_hjzmsz:newPrintset.printset_18.checked?1:0,	
					dydw_hjzmsz:newPrintset.printset_11.checked?1:0,	
					dyhh_hjzmsz:newPrintset.printset_13.checked?1:0,
					dyhyzk_hjzmsz:newPrintset.printset_15.checked?1:0,	
					dybyqk_hjzmsz:newPrintset.printset_17.checked?1:0,	
					dywhcd_hjzmsz:newPrintset.printset_19.checked?1:0	
			};
			if(!Compare(oldDyst,newDyst)){//相等说明没有改变，不需要保存进数据库  add by zjm 20190326
				Gnt.submit(
						newDyst, 
						"gl/hjdywh/saveDysz", 
						"正在保存新的打印设置，请稍后...", 
						function(jsonData,params){
							getData(win);
					}
				);
			}else{
				getData(win);
			}
		}
    },{
        text:'关闭',
		minWidth:75,
		handler:function(){
			var win = this.findParentByType("print_dialog");
			for(var index=1;index<=5;index++){
				win["grid" + index].store.removeAll();
			}
			win.hide();
		}
    }],
    listeners:{'hide':{fn:function(){
    	for(var index=1;index<=5;index++){
    		this["grid" + index].store.removeAll();
    	}
	}}}
});
Ext.reg('print_dialog', Gnt.ux.PrintDialog);

function funSelected(index,flag){
	if(flag){
		dyWindow["grid"+index].getSelectionModel().selectAll();
	}else{
		dyWindow["grid"+index].getSelectionModel().clearSelections();
	}
}
var dyWindow = new Gnt.ux.PrintDialog({
	//选择立户信息回调
	quanhuPrint: function(selectHhnbid){
		//dyWindow["grid1"].setHeight(250);
		dyGrid.store.load(
				{params:{
					type:1,
					hhnbid:selectHhnbid, 
					rynbid:selectRynbid
					}
				});
	},
	callback: function(arrayTemp,data){
		printfunction(0,arrayTemp,data);
//		Gnt.ux.Dict.getKzcs('10030', function(config, userObj, kzdata){
//			if(config&&config.kzz=='1'){
//				//查询户打印次数
//			 	   Gnt.submit(
//			 	 			 {rynbid:arrayTemp[0].rynbid}, 
//			 					"yw/common/queryDycsByhh", 
//			 					"按户号查询打印次数，请稍后...", 
//			 					function(jsonData,params){
//				 	 				if(jsonData.list){
//				 	 					if(jsonData.list[0]>0){
//				 	 						if(sfFlag){//是否收费标志
//				 	 							var je=0;
//				 	 							var dyObj = null;
//				 	 							for(var i = 0;i<arrayTemp.length;i++){
//				 	 								if(arrayTemp[i].directTable=="jmhkb_sy"){
//				 	 									je=parseInt(je)+parseInt(user.personSet.hjsysf);
//				 	 									dyObj = arrayTemp[i];
//				 	 								}else if(arrayTemp[i].directTable=="zqz"){
//				 	 									je=parseInt(je)+parseInt(user.personSet.zqzsf);
//				 	 									dyObj = arrayTemp[i];
//				 	 								}else if(arrayTemp[i].directTable=="qyz1"||arrayTemp[i].directTable=="qyz2"){
//				 	 									je=parseInt(je)+parseInt(user.personSet.qyzsf);
//				 	 									dyObj = arrayTemp[i];
//				 	 								}
//				 	 							}
////				 	 							alert(je);
////				 	 							return;
//				 	 							sffsWin.show();
//				 	 							sffs_form.reset(je,dyObj,arrayTemp,data);
//				 	 							//Ext.MessageBox.confirm('提示','打印费共计<b style="color:red">'+je+'¥</b>,'+'是否在线收费？',goDelete);
//				 	 							//printfunction(0,arrayTemp,data);
//				 	 						}else{
//				 	 							printfunction(0,arrayTemp,data);
//				 	 						}
//				 	 						
//				 	 					}else{
//				 	 						printfunction(0,arrayTemp,data);
//				 	 					}
//				 					}
//				 	 			}
//			 	   );
//			}else{
//				printfunction(0,arrayTemp,data);
//			}
//		});
		
	}
});	

var dyGrid = new Gnt.ux.SjpzGrid({
	pkname: 'rynbid',
	pzlb: '20000',
	region:'center',
	url: 'yw/common/lodop.json',
	showPaging:false,
	title:'原户成员列表',
	//加载成功，回调函数
	loadCallback:function(res, store, grid){
					//dyWindow.test(store);
		dyWindow.setDataStore(store);
		
		var ryset =dyWindow.ryset;
		var rysetTemp;
		for(var j=1;j<=5;j++){
			rysetTemp="ryset_"+j;
			if(Ext.getCmp(rysetTemp).getValue()){
				dyWindow["grid"+j].getSelectionModel().selectAll();
				//dyWindow["grid"+j].enable();
			}else{
				//dyWindow["grid"+j].disable();
			}
//			if(Ext.getCmp(rysetTemp).enable()){
//				//dyWindow["grid"+j].getSelectionModel().selectAll();
//				dyWindow["grid"+j].getSelectionModel().selectAll();
//			}else{
//				//dyWindow["grid"+j].disable();
//			}
//			if(Ext.getCmp(rysetTemp).disabled){
//				dyWindow["grid"+j].disable();
//			}
//			var o =ryset[rysetTemp];
//			if(o.checked){
//				dyWindow["grid"+j].getSelectionModel().selectAll();
//			}
		}
//		dyWindow.setDataStore(store);
//		dyWindow["grid1"].getSelectionModel().selectAll();
//		dyWindow["grid2"].getSelectionModel().selectAll();
//		dyWindow["grid3"].getSelectionModel().selectAll();
//		dyWindow["grid4"].getSelectionModel().selectAll();
//		dyWindow["grid5"].getSelectionModel().selectAll();
	}
});

function printRk(type,selectHhnbid,selectRynbid,array1,array2,array3,jo){// array1 按钮亮否  array2 按钮是否选中   array3 grid隐藏
//	if((selectHhnbid==null&&selectRynbid==null)||(selectHhnbid==""&&selectRynbid=="")){
	hzywjo = jo;
	if(!selectHhnbid && !selectRynbid){
		alert("没有人员信息!!");
		return;
	}
	SelectedArray2 = array2;
	dyWindow.show();
	hhnbidTemp=selectHhnbid;
	rynbidTemp=selectRynbid;
	dyGrid.store.load({params:{type:type,hhnbid:selectHhnbid, rynbid:selectRynbid}});
	for(var index=1;index<=array1.length;index++){
		if(array1[(index-1)]){
			Ext.getCmp('ryset_'+index).enable();
		}else{
			Ext.getCmp('ryset_'+index).disable();
		}
	}
	for(var index=1;index<=array2.length;index++){
		if(array2[(index-1)]){
			Ext.getCmp('ryset_'+index).setValue(true);
		}else{
			Ext.getCmp('ryset_'+index).setValue(false);
		}
	}
	for(var index=1;index<=array3.length;index++){
		if(array3[(index-1)]){
			dyWindow["grid"+index].enable();
		}else{
			dyWindow["grid"+index].disable();
		}
	}
}
function printMore(flag,type,hnbid,store,array1,array2,array3){
	var selectRynbid='';
	hhnbidTemp=selectHhnbid;
	if(store){
		
		store.each(function(r){
			var data = r.data;
			if(data.rynbid){
				if(flag){
					if(data._sel=='1'){
						selectRynbid += data.rynbid + ",";
					}
				}else{
					selectRynbid += data.rynbid + ",";
				}
			}else{
				showInfo(store.pzlb + "不存在rynbid字段！");
				return false;
			}
		});
		
	}
	
	if(selectRynbid.length > 0){
		selectRynbid = selectRynbid.substr(0,selectRynbid.length - 1);
	}else{
		selectRynbid = "null";
	}
	
	printRk(type,hnbid,selectRynbid,array1,array2,array3);
}
var hkxzzmFlag = false;
var hkzmlsFlag = false;
function addArray5(selectedId,selectedValue){
	for(var i=0;i<array5.length;i++){
		var o={};
		o.directTable="zdydy";
		o.modulId=selectedId;
		o.modulValue=selectedValue;
		o.xm=array5[i].xm;
		o.rynbid =array5[i].rynbid
		arrayTemp.push(o);
	}
	printfunction(0,arrayTemp,data);
}
//比较俩对象是否相等方法 
function Compare(objA, objB) {
    if(!isObj(objA) || !isObj(objB)) return false; //判断类型是否正确
    if(getLength(objA) != getLength(objB)) return false; //判断长度是否一致
    return CompareObj(objA, objB, true); //默认为true
};

function CompareObj(objA, objB, flag) {
    for(var key in objA) {
        if(!flag) //跳出整个循环
            break;
        if(!objB.hasOwnProperty(key)) {
            flag = false;
            break;
        }
        if(!isArray(objA[key])) { //子级不是数组时,比较属性值
            if(objB[key] != objA[key]) {
                flag = false;
                break;
            }
        } else {
            if(!isArray(objB[key])) {
                flag = false;
                break;
            }
            var oA = objA[key],
                oB = objB[key];
            if(oA.length != oB.length) {
                flag = false;
                break;
            }
            for(var k in oA) {
                if(!flag) //这里跳出循环是为了不让递归继续
                    break;
                flag = CompareObj(oA[k], oB[k], flag);
            }
        }
    }
    return flag;
};
function isObj(object) {
    return object && typeof(object) == 'object' && Object.prototype.toString.call(object).toLowerCase() == "[object object]";
};
function getLength(object) {
    var count = 0;
    for(var i in object) count++;
    return count;
};
function isArray(object) {
    return object && typeof(object) == 'object' && object.constructor == Array;
};
function getData(win){
	data = win.getData();
	var array1=data.ry.list1;
	var array2=data.ry.list2;
	var array3=data.ry.list3;
	var array4=data.ry.list4;
	array5=data.ry.list5;
	arrayTemp=[];
	var directTable="";
	var rynbid="";
	var xm="";
	var arrayLength4=0;
	var rynbidTotal="";
	var obj = data.printset;
	if(array1){
		for(var i=0;i<array1.length;i++){
			var o={};
			o.directTable="czrkdjb";
			o.xm=array1[i].xm;
			o.rynbid =array1[i].rynbid;
			arrayTemp.push(o);
		}
	}
	if(array2){
		var o={};
		var o1={};
		var o2={};
		var o4={};
		o.xm=array2[0].xm;
		o1.xm=array2[0].xm;
		o.rynbid =array2[0].rynbid;
		o.gmsfhm = array2[0].gmsfhm;
		o1.rynbid =array2[0].rynbid;
		syCount = 0;
		nyCount = 0;
		if(obj.printset_5.checked){
			o.directTable="jmhkb_sy";
			syCount ++; 
//			if(hzywjo){
//				o.hzywjo = hzywjo;
//			}
			arrayTemp.push(o);
			//打印次数计算和首页打印捆绑一起
//			o4.xm=array2[0].xm;
//			o4.rynbid =array2[0].rynbid;
//			o4.directTable="jmhkb_dycs";
//			arrayTemp.push(o4);
		}
		if(obj.printset_6.checked){
			o1.directTable="jmhkb_bm";
//			if(hzywjo){
//				o1.hzywjo = hzywjo;
//			}
			arrayTemp.push(o1);
		}
		for(var i=0;i<array2.length;i++){
			var o3={};
			o2.xm=array2[i].xm;
			o3.xm=array2[i].xm;
			o2.rynbid =array2[i].rynbid;
			o3.rynbid =array2[i].rynbid;
			if(obj.printset_7.checked){
				o2.directTable="jmhkb_jthfs_sy";
				arrayTemp.push(o2);
			}
			o3.directTable="jmhkb_ny";
			nyCount ++; 
			if(hzywjo&&(hzywjo.gmsfhm==array2[i].gmsfhm)){
				o3.hzywjo = hzywjo;
			}
			arrayTemp.push(o3);
		}
		
	}
	if(array3&&!hkxzzmFlag&&hkzmlsFlag){
		for(var i=0;i<array3.length;i++){
			var o={};
			o.directTable="hjzmx";
			o.xm=array3[i].xm;
			if(hzywjo&&(hzywjo.gmsfhm==array3[i].gmsfhm)){
				o.hzywjo = hzywjo;
			}
			o.rynbid =array3[i].rynbid;
			arrayTemp.push(o);
		}
	}else if(array3&&!hkxzzmFlag){
		for(var i=0;i<array3.length;i++){
			var o={};
			o.directTable="hjzm";
			o.xm=array3[i].xm;
			if(hzywjo&&(hzywjo.gmsfhm==array3[i].gmsfhm)){
				o.hzywjo = hzywjo;
			}
			o.rynbid =array3[i].rynbid;
			arrayTemp.push(o);
		}
	}else if(array3&&hkxzzmFlag){
		for(var i=0;i<array3.length;i++){
			var o={};
			o.directTable="hkxzzm";
			o.xm=array3[i].xm;
			if(hzywjo&&(hzywjo.gmsfhm==array3[i].gmsfhm)){
				o.hzywjo = hzywjo;
			}
			o.rynbid =array3[i].rynbid;
			arrayTemp.push(o);
		}
	}
	if(array4){
		arrayLength4=array4.length;
		var o={};
		for(var i=0;i<array4.length;i++){
			rynbidTotal =rynbidTotal+array4[i].rynbid+";"
		}
		o.directTable="djksyb";
		o.rynbid =arrayLength4;//长度临时存放
		o.xm=rynbidTotal.slice(0,rynbidTotal.length-1);//临时存放所选人员的id
		arrayTemp.push(o);
	}
	if(array5){
		zdyWin.show();
//		for(var i=0;i<array5.length;i++){
//			var o={};
//			o.directTable="zdy";
//			o.xm=array5[i].xm;
//			o.rynbid =array5[i].rynbid
//			arrayTemp.push(o);
//		}
	}else{
		if(arrayTemp.length==0){
			Ext.Msg.alert("提醒","请至少选择一个人,再点击!"); 
			return;
		}
		if(win.callback){
			win.callback(arrayTemp,data,syCount,nyCount);
		}
	}
	//win.hide();
}
function updateSfxxb(sfxxbid,type,bzxjfyy){
	Gnt.submit(
			{
				sfxxbid:sfxxbid,
				type:type,
				bzxjfyy:bzxjfyy
			}, 
			"yw/common/bjfyySave", 
			type=='bzx'?"正在保存不在线缴费原因，请稍后...":"更新收费信息表,请稍后...", 
			function(jsonData,params){
				//updateSfxxb(sfxxbid,'bzx',data.bzxjfyy);
			}
	);
}
