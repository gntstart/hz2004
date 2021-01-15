var selRes = null;
var curIndex = -1;

Ext.onReady(function(){
	Ext.QuickTips.init();
//	
//	//本业务需要加载的配置
//	if(!Gnt.loadSjpzb("10016,20016,10019,10002,10024",function(){}))
//		return;
//	
//	//本业务需要加载的字典
//	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
//	
//	//迁出人员选择
//	var p1 = new Gnt.ux.SelectRyxxPanel({
//		select:function(list){
//			curIndex = 0;
//			
//			//点击确认迁出后，执行此方法
//			Ext.getCmp('card').getLayout().setActiveItem(1);
//			//界面更新到下一步
//			//v.remove(v.getComponent(0));
//			//v.add(p2);
//			v.doLayout();
//			
//			//户成员初始化
//			var store = hcyGrid.store;
//			store.removeAll();
//			
//			//初始化迁出登记store
//			var newres = new Array();
//			var res = [];
//			var qcdbdfw = "";
//			
//			for(var i=0;i<list.length;i++){
//				var data = list[i];
//				var rr = new store.reader.recordType(data, data.rynbid);
//				store.add([rr]);
//				
//				if(data._sel=='1'){
//					//迁出人员，那么初始化迁出登记信息
//					if(qcdbdfw=="" && data.qcdbdfw){
//						qcdbdfw = data.qcdbdfw;
//					}
//					
//					var pk = data[qcdjStore.pkname];
//					var r = new qcdjStore.reader.recordType({}, pk);
//					r.set(qcdjStore.pkname, pk);
//					//"kdqqy_zqz","kdqqy_qrdz","kdqqy_qyldyy","kdqqy_qczxlb"
//					if(data.kdqqy_zqz) r.set("zqzbh",data.kdqqy_zqz);
//					if(data.kdqqy_qrdz) r.set("qwdxz",data.kdqqy_qrdz);
//					if(data.kdqqy_qrdqh) r.set("qwdssxq",data.kdqqy_qrdqh);
//					if(data.kdqqy_qyldyy) r.set("qyldyy",data.kdqqy_qyldyy);
//					if(data.kdqqy_qczxlb) r.set("qclb",data.kdqqy_qczxlb);
//					r.set("qcrq",Ext.util.Format.date(new Date(),'Ymd'));
//					if(qcdbdfw!=""){
//						r.set("bdfw",qcdbdfw);
//					}
//					
//					newres.push(r);
//				}
//			}
//
//			if(newres.length>0)
//				qcdjStore.add(newres);
//			
//			(function(){
//				hcyGrid.fireEvent("rowclick",hcyGrid,0);
//				hcyGrid.getSelectionModel().selectRange(0,0);
//			}).defer(200);
//			
//			form10024.getForm().findField("qcrq").setMinValue(Ext.util.Format.date(new Date(),'Ymd'));
//			if(list.length==1){
//				Ext.getCmp('prvBtn').disable();
//				Ext.getCmp('nextBtn').disable();
//			}
//		}
//	});
//	
//	var hcyGrid = new Gnt.ux.SjpzGrid({
//		pkname: 'rynbid',
//		pzlb: '10019',
//		region:'west',
//    	title: '户成员列表',
//    	width:200,
//    	showPaging:false,
//		listeners:{
//			rowclick:function(g, rowIndex, e){
//    			selRes = g.store.getAt(rowIndex);
//    			curIndex = rowIndex;
//    			
//				if(curIndex==(hcyGrid.store.getCount()-1)){
//					Ext.getCmp('nextBtn').disable();
//				}else{
//					Ext.getCmp('nextBtn').enable();
//				}
//
//				if(curIndex==0){
//					Ext.getCmp('prvBtn').disable();
//				}else{
//					Ext.getCmp('prvBtn').enable();
//				}
//				
//    			//人员基本资料更新
//    			form10019.getForm().reset();
//    			form10019.getForm().loadRecord(selRes);
//    			
//    			//迁出登记信息初始化
//    			form10024.getForm().reset();
//    			
//    			if(selRes.data._sel=="1"){
//    				//为迁出登记form赋值
//    				form10024.setVisible(true);
//    				var pkvalue = selRes.data[form10024.bindStore.pkname];
//    				var re = form10024.bindStore.getById(pkvalue);
//    				if(re){
//    					form10024.getForm().loadRecord(re);
//    				}else{
//    					alert("警告：迁出登记信息" + pkvalue + "不存在！");
//    				}
//    			}else{
//    				//不是迁出人员，隐藏
//    				form10024.setVisible(false);
//    			}
//    		}
//		}
//	});
//	
//	var form10019 = new Gnt.ux.SjpzForm({
//		closable: false,
//		region:'north',
//		height:330,
//		pzlb: '10019',
//		labelWidth :  120,
//		//bindStore:hcyGrid.store,
//		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
//		bindViewGrid:hcyGrid,
//		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
//		changeDictCust:function(cmb,res){
//			//当选择人后，为其它域赋值
//			if(cmb.name=="fqxm"){
//				this.getForm().setValues({fqgmsfhm:res.data.data});
//				selRes.set("fqgmsfhm",res.data.data);
//			}
//			if(cmb.name=="mqxm"){
//				this.getForm().setValues({mqgmsfhm:res.data.data});
//				selRes.set("mqgmsfhm",res.data.data);
//			}
//			if(cmb.name=="jhryxm"){
//				this.getForm().setValues({jhrygmsfhm:res.data.data});
//				selRes.set("jhrygmsfhm",res.data.data);
//			}
//			if(cmb.name=="jhrexm"){
//				this.getForm().setValues({jhregmsfhm:res.data.data});
//				selRes.set("jhregmsfhm",res.data.data);
//			}
//			if(cmb.name=="poxm"){
//				this.getForm().setValues({pogmsfhm:res.data.data});
//				selRes.set("pogmsfhm",res.data.data);
//			}
//			
//			return;
//		},
//        title: '户成员信息'
//	});
//	
//	var form10002 = new Gnt.ux.SjpzForm({
//		closable: false,
//		region:'north',
//		height:60,
//		pzlb: '10002',
//		labelWidth :  120,
//		//选择人的来源
//		bindSelectRyStore: hcyGrid.store,
//		changeDictCust:function(cmb,res){
//			//当选择人后，为其它域赋值
//			this.getForm().setValues({sbrgmsfhm:res.data.data});
//			return;
//		},
//        title: '申报人信息'
//	});
//
//	//迁出登记store
//	var qcdjStore = new Gnt.store.SjpzStore({
//		pzlb:'10024',
//		pkname:'rynbid'
//	});
//	
//	//典型：bindStore和bindViewGrid不一样
//	var form10024 = new Gnt.ux.SjpzForm({
//		closable: false,
//		region:'center',
//		height:60,
//		pzlb: '10024',
//		labelWidth :  120,
//		//绑定到store，输入域改动的时候，自动赋值
//		bindStore: qcdjStore,
//		//绑定bindViewGrid，当bindViewGrid点击的时候，触发记录移动
//		bindViewGrid: hcyGrid,
//        title: '迁出登记信息'
//	});
//	
//	var p2 = new Ext.Panel({
//		layout:'border',
//		items:[hcyGrid,{
//	        region:'center',
//	        //禁止横向滚动条
//	        layout:'border',
//	        border:false,
//	        frame:false,
//	        items:[form10019,{
//	        	 layout:'border',
//	        	 region:'center',
//	             border:false,
//	             frame:false,
//	             items:[form10002,form10024]
//	        }]
//	    },{
//	    	 region:'east',
//	    	 width:150,
//	    	 layout:'table',
//	    	 align:'center',
//	    	 layoutConfig: {
//	    		columns: 1
//	    	 },
//	    	 margins:'5',
//	    	 defaults: {
//	    			width:'100%',
//	    			margins:'15'
//	    	 },
//	    	 items: [{
//	    		 		title: '',
//	    		 		height:'100%',
//	    				html: '<div id="_PHOTO_ID">照片</DIV>',
//	    				width:150,
//	    				height:150,
//	    				rowspan: 1,
//	    				colspan:1
//	    	    },{
//	    	    		html:'',
//	    	    		bodyStyle:'padding:10px 25px 0px 20px',
//		   	    	 	layout:'table',
//		   	    	 	align:'center',
//		   	    	 	border:false,
//		   	    	 	frame:false,
//		   	    	 	layoutConfig: {
//		   	    	 		columns: 1
//		   	    	 	},
//		   	    	 	items:[
//							new Ext.Button({
//								text:'照片采集',
//								minWidth:100,
//								width:150,
//								handler:function(){
//									
//								}
//							}),{
//				    	    	height:3,
//				    	    	border:false,
//				    	    	frame:false
//				    	    },new Ext.Button({
//								text:'照片删除',
//								disabled:true,
//								minWidth:100,
//								handler:function(){
//									
//								}
//							}),{
//				    	    	height:3,
//				    	    	border:false,
//				    	    	frame:false
//				    	    },new Ext.Button({
//								text:'详细信息',
//								minWidth:100,
//								handler:function(){
//									
//								}
//				    	    }),{
//				    	    	height:3,
//				    	    	border:false,
//				    	    	frame:false
//				    	    },new Ext.Button({
//								text:'取消修改',
//								minWidth:100,
//								handler:function(){
//									
//								}
//				    	    }),{
//				    	    	height:3,
//				    	    	border:false,
//				    	    	frame:false
//				    	    },new Ext.Button({
//								text:'同第一人',
//								minWidth:100,
//								handler:function(){
//									
//								}
//				    	    }),{
//				    	    	height:3,
//				    	    	border:false,
//				    	    	frame:false
//				    	    },new Ext.Button({
//								text:'下一个',
//								minWidth:100,
//								id:'nextBtn',
//								handler:function(){
//									curIndex++;
//									hcyGrid.fireEvent("rowclick",hcyGrid,curIndex);
//									hcyGrid.getSelectionModel().selectRange(curIndex,curIndex);
//								}
//				    	    }),{
//				    	    	height:3,
//				    	    	border:false,
//				    	    	frame:false
//				    	    },new Ext.Button({
//								text:'上一个',
//								disabled:true,
//								minWidth:100,
//								id:'prvBtn',
//								handler:function(){
//									curIndex--;
//									hcyGrid.fireEvent("rowclick",hcyGrid,curIndex);
//									hcyGrid.getSelectionModel().selectRange(curIndex,curIndex);
//								}
//				    	    }),{
//				    	    	height:3,
//				    	    	border:false,
//				    	    	frame:false
//				    	    },new Ext.Button({
//								text:'准迁证核查',
//								minWidth:100,
//								handler:function(){
//									
//								}
//				    	    }),{
//				    	    	height:50,
//				    	    	border:false,
//				    	    	frame:false
//				    	    },new Ext.Button({
//								text:'保存',
//								minWidth:100,
//								handler:function(){
//									save();
//								}
//				    	    }),{
//				    	    	height:3,
//				    	    	border:false,
//				    	    	frame:false
//				    	    },new Ext.Button({
//								text:'新办理',
//								minWidth:100,
//								handler:function(){
//									showQuestion("数据未保存，确定放弃办理吗？", function(btnType){
//										if(btnType=="yes"){
//											Ext.getCmp('card').getLayout().setActiveItem(0);
//											v.doLayout();
//											p1.newYewu();
//										}
//									});
//								}
//				    	    }),{
//				    	    	height:3,
//				    	    	border:false,
//				    	    	frame:false
//				    	    },new Ext.Button({
//								text:'关闭',
//								minWidth:100,
//								handler:function(){
//									showQuestion("数据未保存，确定关闭吗？", function(btnType){
//										if(btnType=="yes"){
//											if(window.parent.closeWorkSpace){
//												window.parent.closeWorkSpace();
//											}
//										}
//									});
//								}
//				    	    })
//			    	 ]
//	    	    }
//	    	 ]
//	    }]
//	});
//	
//	function save(){
//			if(!form10019.checkInput())
//				return;
//			
//			if(!form10024.checkInput())
//				return;
//			
//			if(!form10002.getForm().isValid()){
//				showErr("申报人信息必须填写！");
//				return;
//			}
//		
//			//判断非迁出户必须存在户主
//			var exists = false;
//			var icount = 0;
//			hcyGrid.store.each(function(r){
//				var data = r.data;
//				/*
//				if(data._sel=="1"){
//					//迁出成员
//				}else{
//					icount++;
//					if(data.yhzgx=="01" || data.yhzgx=="02" || data.yhzgx=="03")
//						exists = true;
//				}
//				*/
//				icount++;
//				if(data.yhzgx=="01" || data.yhzgx=="02" || data.yhzgx=="03")
//					exists = true;
//			});
//			
//			if(!exists && icount>0){
//				showErr("户主已迁出，请选择新户主！");
//				return;
//			}
//			
//			var rs = hcyGrid.store.getModifiedRecords();
//			var store = bggzGrid.store;
//			store.removeAll();
//			
//			if(rs!=null){
//					str = "";
//					for(var i=0;i<rs.length;i++){
//						var obj = rs[i].getChanges();
//						//str += "记录ID:" + rs[i].id + ":<BR>";
//						//"tmpid", "rynbid","xm","bggzxm_mc","bggzxm", "bggzqnr","bggzhnr","bggzlb"
//						for(f in obj){
//							var data = {
//									rynbid: rs[i].data.rynbid,
//									xm: rs[i].data.xm,
//									bggzxm:f,
//									bggzxm_mc:form10019.getFieldLabel(f),
//									bggzqnr: rs[i].modified[f],
//									bggzhnr:obj[f],
//									bggzlb:'91'
//								};
//							var r = new store.reader.recordType(data);
//							store.add([r]);
//						}
//					}
//					if(store.getCount()>0){
//						bggzWin.show();
//					}else{
//						submitData();
//					}
//					
//					return;
//			}
//	}
//	
//	var bggzGrid = new Gnt.ux.BggzGrid({title:''});
//	var bggzWin = new Ext.Window({
//		title:'确认变更更正项目',
//		closeAction:'hide',
//		modal:true,
//		width:600,
//		height:300,
//		margins:'5',
//		layout:'fit',
//		items:bggzGrid,
//		buttons:[
//					new Ext.Button({
//							text:'确定',
//							minWidth:75,
//							handler:function(){
//								submitData();
//							}
//					}),
//					new Ext.Button({
//						text:'取消',
//						minWidth:75,
//						handler:function(){
//							bggzWin.hide();
//						}
//				})
//			]
//	});
//
//	//提交数据
//	function submitData(){
//		var subdata = {
//				bggzxx:new Array(),
//				sbjbxx:{},
//				ryList:new Array(),
//				qczxxx:new Array()
//		};
//		
//		//人员内部id和上笔户籍业务的关系
//		var map = {};
//		hcyGrid.store.each(
//				function(rec){
//					map[rec.data.rynbid] = rec.data.cjhjywid;
//				},	hcyGrid
//		);
//		
//		//变更更正信息
//		var bggzmap = {};
//		var store = bggzGrid.store;
//		if(store.getCount()>0){
//			for(var index=0;index<store.getCount();index++){
//				var bggzdata = store.getAt(index).data;
//				bggzdata.sbhjywid = map[bggzdata.rynbid];
//				bggzdata.flag='1';
//				
//				if(!bggzmap[bggzdata.rynbid]){
//					bggzmap[bggzdata.rynbid] = {
//							rynbid: bggzdata.rynbid,
//							sbhjywid: bggzdata.sbhjywid,
//							bggzxxList:new Array()
//					}
//				}
//				bggzmap[bggzdata.rynbid].bggzxxList.push(bggzdata);
//			}
//			
//			for(rynbid in bggzmap){
//				subdata.bggzxx.push(bggzmap[rynbid]);
//			}
//		}
//		
//		//申报人信息
//		subdata.sbjbxx = form10002.getForm().getValues();
//		
//		//迁出注销信息
//		for(var index=0;index<qcdjStore.getCount();index++){
//			var data = qcdjStore.getAt(index).data;
//			data.sbhjywid = map[data.rynbid];
//			
//			subdata.qczxxx.push(data);
//		}
//		
//		subdata.sbjbxx = Ext.encode(subdata.sbjbxx);
//		subdata.bggzxx = Ext.encode(subdata.bggzxx);
//		subdata.qczxxx = Ext.encode(subdata.qczxxx);
//		
//		Gnt.submit(
//			subdata, 
//			"yw/qczx/saveQczx", 
//			"正在处理迁出信息，请稍后...", 
//			function(jsonData,params){
//				showInfo("迁出成功！",function(){
//					//关闭当前窗口
//					if(parent.closeActiveWorkSpace)
//						parent.closeActiveWorkSpace(jsonData);
//				});
//			}
//		);
//	}
//	
//	var v = new Ext.Viewport({
//        layout:'fit',
//        id:'vp',
//        border:false,
//        items: {
//        	layout:'card',
//        	border:false,
//        	id:'card',
//        	frame:false,
//        	activeItem: 0,
//        	xtype: 'panel',
//    	    //bodyStyle: 'padding:2px',
//        	items:[p1,p2]
//        }
//    });
});