var selectYw = 1;
var queryFlag = null;
var qybz = 1;
var errorFlag =true;
var selectedSelRes=null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10019,20021",function(){}))
		return;
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});

	//数据grid
	var xtrzGrid = new Gnt.ux.SjpzGrid({
		pkname: 'xtrzid',
		pzlb: '50007',
		url:'gl/xtrz/getXtrzbInfo',
		region:'center',
		height:500,
		title: '',
		showPaging:true,

		listeners:{
			rowclick:function(grid, rowIndex, e){
				selectedSelRes = grid.store.getAt(rowIndex);
			},
			rowdblclick:function(grid,row){
				selRes = grid.store.getAt(row);
				NewForm50007.getForm().loadRecord(selRes);
				Gnt.toFormReadyonly(NewForm50007);
				Ext.getCmp('card').getLayout().setActiveItem(2);
				v.doLayout();
			}
		}
	});

	//查询from
	var form50007 = new Gnt.ux.SjpzForm({
		pzlb: '50007',
		url: 'yw/queryRyxx',
		title:'',
		cols:2,
		formType:'query'
	});


	//详情信息
	var NewForm50007 = new Gnt.ux.SjpzForm({
		closable: false,
		region:'center',
		/*height:500,*/
		pzlb: '50007',
		labelWidth : 120,
		cols:2,
		changeDictCust:function(cmb,res){

			return;
		},
		title: '详情信息',
		buttons:[
			new Ext.Button({
			id:'close',
			text:'关闭',
			minWidth:60,
			handler:function(){
				window.parent.closeWorkSpace();
			}
			}),new Ext.Button({
				id:'return',
				text:'返回',
				minWidth:60,
				handler:function(){
					Ext.getCmp('card').getLayout().setActiveItem(1);
				}
			})]
	});

	var p1 = new Ext.Panel({
		layout:'border',
		newYewu: function(){

		},
		items:[
			{
				id:"centerId",
				border:false,
				frame: false,
				region:'center',
				layout:'border',
//	        	height: 300,
				items:[{
					id: "panelHtmlId" ,
					html: '<div class="text" style="text-align:center;">系统日志查询</div>',
					height: 80,
					region:'north',
					bodyStyle:'padding:15px'
				},
					form50007
				]
			},
			{
				id:"southId",
				region:'south',
				height:50,
				border:false,
				frame:false,
				items:[{
					border:false,
					frame: false,
					region:'south',
					height: 40,
					bodyStyle: 'padding:10px 0px 0px 0px ',
					layout:'table',
					layoutConfig: {
						columns: 14
					},
					items: [
						{
							width:10,
							border:false,
							frame: false
						},/*{
							border:false,
							frame: false,
							items:[{
								id:'c11',
								xtype:'checkbox',
								boxLabel : "显示记录总数",
								name : "jlzs",
								inputValue : "1"
							}]
						},*/{
							width:10,
							border:false,
							frame: false
						},{
							border:false,
							frame: false,
							items:[new Ext.Button({
								id:'preId',
								text:'上一步',
								disabled:true,
								minWidth:60,
								handler:function(){
									Ext.getCmp('preId').setDisabled(true);
									Ext.getCmp('nexId').setDisabled(false);
									Ext.getCmp('nextId').setVisible(false);
									Ext.getCmp('centerId').setVisible(true);
								}
							})]
						},{
							width:10,
							border:false,
							frame: false
						},{
							border:false,
							frame: false,
							items:[new Ext.Button({
								id:'nexId',
								text:'下一步',
								minWidth:60,
								handler:function(){
									Ext.getCmp('preId').setDisabled(false);
									Ext.getCmp('nexId').setDisabled(true);
									Ext.getCmp('nextId').setVisible(true);
									Ext.getCmp('centerId').setVisible(true);
								}
							})]
						},{
							width:10,
							border:false,
							frame: false
						},{
							border:false,
							frame: false,
							items:[new Ext.Button({
								id:'queryId',
								text:'查询',
								minWidth:60,
								handler:function(){
									var data = form50007.getForm().getValues();

									var store = xtrzGrid.store;
									store.removeAll();
									store.baseParams = data;
									store.load({params:{start:0, limit:20}});
									store.on("load",function(store) {
										if(store.data.length > 0){
											Ext.getCmp('card').getLayout().setActiveItem(1);
											curIndex = 0;
											xtrzGrid.fireEvent("rowclick",xtrzGrid,curIndex);
											xtrzGrid.getSelectionModel().selectRange(curIndex,curIndex);
										}else{
											alert("没有符合条件的数据！");
										}
									},xtrzGrid);
								}
							})]
						},{
							width:10,
							border:false,
							frame: false
						},{
							border:false,
							frame: false,
							items:[new Ext.Button({
								text:'关闭',
								minWidth:60,
								handler:function(){
									window.parent.closeWorkSpace();
								}
							})]
						},{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							height:40
						},{
							frame:false,
							border:false,
							id:'_READ_CARD_ID',
							xtype:'panel',
							html:'',
							width:10
						}
					]

				}]
			}
		]
	});

	var log_window=new Gnt.ux.xtlogwin({
		callback: function(){

		}
	});

	var zdsz_dialog = new Gnt.ux.ZdszDialog({
		pzlb : xtrzGrid.pzlb,
		callback: function(zdKeyArray,zdValueArray){
			if(zdKeyArray.length>0&&zdValueArray.length>0&&zdKeyArray.length==zdValueArray.length){
				var list = [];
				xtrzGrid.store.each(function(r){
					var data = r.data;
					list.push(data);
				});
				log_window.show();
				log_window.setSelRes(list,"lg",zdKeyArray,zdValueArray);
			}else{
				alert("设置字段方法出错了！")
			}
		}
	});

	var p2 = new Ext.Panel({
		layout:'border',
		items:[
			xtrzGrid
		],
		buttons:[
			{
				text:'设置字段',
				minWidth:60,
				handler:function(){
					zdsz_dialog.show();
				}
			},new Ext.Button({
				id:'return',
				text:'打印',
				minWidth:60,
				handler:function(){

					var list = [];
					xtrzGrid.store.each(function(r){
						var data = r.data;
						list.push(data);
					});
					log_window.show();
					log_window.setSelRes(list,"lg");
				}
			}),
			new Ext.Button({
				id:'close',
				text:'关闭',
				minWidth:60,
				handler:function(){
					window.parent.closeWorkSpace();
				}
			}),
			new Ext.Button({
				id:'return',
				text:'返回',
				minWidth:60,
				handler:function(){
					Ext.getCmp('card').getLayout().setActiveItem(0);
				}
			})
		]
	});
	//定义分页面板
	var p3 = new Ext.Panel({
		layout:'border',
		newYewu: function(){

		},
		items:[
			NewForm50007
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
			items:[p1,p2,p3]
		}
	});
});