var selRes = null;
var curIndex = -1;
var selectRynbid = null;
var selectHhnbid = null;

Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10016,20016,10019,10002,10014,10010",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	
	//人员选择
	var p1 = new Gnt.ux.SelectRyxxPanel({
		returnBtnText:'确定',
		returnBtnMsg:'必须选择登记人员',
		select:function(list){
			curIndex = 0;
			
			//点击确认后，执行此方法
			//界面更新到下一步
			Ext.getCmp('card').getLayout().setActiveItem(1);
			v.doLayout();
			
			//户成员初始化
			var store = hcyGrid.store;
			store.removeAll();
			
			for(var i=0;i<list.length;i++){
				var data = list[i];
				var rr = new store.reader.recordType(data, data.rynbid);
				
				if(data._sel=='1'){
					/**
						只需要选中的记录
					 */
					store.add([rr]);
					
				}
			}
			
			Gnt.toFormReadyonly(form10010);
			
			(function(){
				hcyGrid.fireEvent("rowclick",hcyGrid,0);
				hcyGrid.getSelectionModel().selectRange(0,0);
			}).defer(200);
			
		}
	});
	
	
	var xzdWindow = new Gnt.ux.SelectXzddj({
		callback: function(xzdz){
			
			var subdata = {
				xzdxx:new Array()
			};
			
			var rydata = hcyGrid.getSelectionModel().getSelected().data;
			
			var data = {
				rynbid:rydata.rynbid,
				xm:rydata.xm,
				xb:rydata.xb,
				csrq:rydata.csrq,
				gmsfhm:rydata.gmsfhm,
//				rhflzt:'',
				rhfl_ssxq:xzdz.ssxq,
				rhfl_jlx:xzdz.jlx,
//				rhfl_mlph:'',
				rhfl_mlxz:xzdz.mlxz,
				rhfl_pcs:xzdz.dwdm,
				rhfl_xzjd:xzdz.xzjd,
				rhfl_jcwh:xzdz.jcwh
//				rhfl_zrq:'',
//				slrid:user.usercode,
//				slsj:'',
//				sldw:user.dwCode
			};
			
			subdata.xzdxx.push(data);
			
			subdata.xzdxx = Ext.encode(subdata.xzdxx);
			log_code = "F2003";
			Gnt.submit(
				subdata, 
				"yw/hjyw/xzddj/processRhflyw", 
				"正在处理现住地登记信息，请稍后...", 
				function(jsonData,params){
					queryXzdxx(rydata.rynbid);
				}
			);
			
		}
	});
	
	var xzdzxWindow = new Gnt.ux.SelectXzdzx({
		callback: function(zxyy){
			
			var subdata = {
				xzdxx:new Array()
			};
			var xzddata = xzdGrid.getSelectionModel().getSelected().data;
			
			xzddata.rhflzt = "2";
			xzddata.zxyy = zxyy;
			
			subdata.xzdxx.push(xzddata);
			
			subdata.xzdxx = Ext.encode(subdata.xzdxx);
			log_code = "F2004";
			Gnt.submit(
				subdata, 
				"yw/hjyw/xzddj/processRhflyw", 
				"正在注销现住地登记信息，请稍后...", 
				function(jsonData,params){
					queryXzdxx(xzddata.rynbid);
				}
			);
			
		}
	});
	
	var hcyGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		pzlb: '10019',
		title: '户成员列表',
		region:'west',
		width:200,
		showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				
				selectRynbid = selRes.data.rynbid;
    			selectHhnbid = selRes.data.hhnbid;
    			
    			var store = xzdGrid.store;
    			store.baseParams = {
    				pzlb: store.pzlb,
    				rynbids:selectRynbid,
    				start:0,
    				limit:20
    			};
    			store.load();
    			
    			store.on("load",function(store) {
					if(store.data.length > 0){
						xzdGrid.fireEvent("rowclick",xzdGrid,0);
						xzdGrid.getSelectionModel().selectRange(0,0);
					}else{
						form10010.getForm().reset();
					}
				},xzdGrid);
    			
				Gnt.photo.setPhoto(selRes, false);
				
			}
		}
	});
	
	var xzdGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rhflid',
		pzlb: '10010',
		region:'center',
		/*height:330,*/
    	title: '现住地信息列表',
    	url: 'yw/common/queryXzdxx.json',
    	showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){
    			selRes = g.store.getAt(rowIndex);
    			curIndex = rowIndex;
				
    			//现住地信息更新
    			form10010.getForm().reset();
    			form10010.getForm().loadRecord(selRes);
    			
    		}
		}
	});
	
	var form10010 = new Gnt.ux.SjpzForm({
		title: '现住地信息编辑',
		closable: false,
//		region:'north',
		pzlb: '10010',
		labelWidth :  120,
		cols:2,
		//选择人的来源
		bindSelectRyStore: xzdGrid.store,
		changeDictCust:function(cmb,res){
			//当选择人后，为其它域赋值
			this.getForm().setValues({sbrgmsfhm:res.data.data});
			return;
		}
	});
	
	var p2 = new Ext.Panel({
		layout:'border',
		items:[hcyGrid,{
	        region:'center',
       	 	layout:'border',
            border:false,
            frame:false,
	        items:[xzdGrid,{
	        	 layout:'border',
	        	 region:'south',
	        	 height:300,
	             border:false,
	             frame:false,
	             items:[form10010]
	        }]
	    },{
	    	 region:'east',
	    	 width:150,
	    	 layout:'table',
	    	 align:'center',
	    	 layoutConfig: {
	    		columns: 1
	    	 },
	    	 margins:'5',
	    	 defaults: {
	    			width:'100%',
	    			margins:'15'
	    	 },
	    	 items: [{
	    		 		title: '',
	    		 		height:'100%',
	    				html: '<div id="_PHOTO_ID">照片</DIV>',
	    				width:150,
	    				height:150,
	    				rowspan: 1,
	    				colspan:1
	    	    },{
	    	    		html:'',
	    	    		bodyStyle:'padding:10px 25px 0px 20px',
		   	    	 	layout:'table',
		   	    	 	align:'center',
		   	    	 	border:false,
		   	    	 	frame:false,
		   	    	 	layoutConfig: {
		   	    	 		columns: 1
		   	    	 	},
		   	    	 	items:[
							new Ext.Button({
								text:'详细信息',
								minWidth:100,
								handler:function(){
									czr={
											ryid:null,
											rynbid:selectRynbid,
											hhnbid:selectHhnbid
										}
									Gnt.toRyxx(czr);
								}
				    	    }),{
				    	    	height:10,
				    	    	border:false,
				    	    	frame:false
				    	    },new Ext.Button({
								text:'新增',
								minWidth:100,
								handler:function(){
									xzdWindow.show();
								}
				    	    }),{
				    	    	height:10,
				    	    	border:false,
				    	    	frame:false
				    	    },new Ext.Button({
								text:'注销',
								minWidth:100,
								handler:function(){
									if(selRes.data.rhflzt==2){
										alert("该人已注销，不允许再做注销操作！")
									}else{
										xzdzxWindow.show();
									}
								}
				    	    }),{
				    	    	height:200,
				    	    	border:false,
				    	    	frame:false
				    	    },new Ext.Button({
								text:'新办理',
								minWidth:100,
								handler:function(){
									showQuestion("数据未保存，确定放弃办理吗？", function(btnType){
										if(btnType=="yes"){
											/*
											Ext.getCmp('card').getLayout().setActiveItem(0);
											v.doLayout();
											p1.newYewu();
											*/
											window.location.reload();
										}
									});
								}
				    	    }),{
				    	    	height:3,
				    	    	border:false,
				    	    	frame:false
				    	    },new Ext.Button({
								text:'关闭',
								minWidth:100,
								handler:function(){
//									showQuestion("数据未保存，确定关闭吗？", function(btnType){
//										if(btnType=="yes"){
//											if(window.parent.closeWorkSpace){
//												window.parent.closeWorkSpace();
//											}
//										}
//									});
									window.parent.closeWorkSpace();
								}
				    	    })
			    	 ]
	    	    }
	    	 ]
	    }]
	});
	
	function queryXzdxx(rynbid){
		var store = xzdGrid.store;
		store.baseParams = {
			pzlb: store.pzlb,
			rynbids:rynbid,
			start:0,
			limit:20
		};
		store.load();
		
		store.on("load",function(store) {
			if(store.data.length > 0){
				xzdGrid.fireEvent("rowclick",xzdGrid,0);
				xzdGrid.getSelectionModel().selectRange(0,0);
			}else{
				form10010.getForm().reset();
			}
		},xzdGrid);
		
	}
	
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
    	    //bodyStyle: 'padding:2px',
        	items:[p1,p2]
        }
    });
});