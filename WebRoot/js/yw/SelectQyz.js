/**
 * 标准地址选择,必须先加载commFrames.js
 */
var win = null;
var store = null;
var selectRecod = null
var ckdaFlag = false;
var qclb = null;//保存业务完毕后传过来的qclb字段
Gnt.ux.SelectQyz = Ext.extend(Ext.Window, {
	title:'迁移证打印',
	closeAction:'hide',
	modal:true,
	width:600,
	height:400,
	margins:'5',
	layout:'border',
	pageSize:30,
	resetData:function(){
		this.grid20004.store.removeAll();
		this.hxx = null;
	},
	initComponent : function(){
		if(!Gnt.loadSjpzb("20004",function(){}))
			return;
		Gnt.ux.Dict.loadDict(['CS_JTGX'],function(){});
		var returnUrl = this.returnUrl;
		if(!returnUrl || returnUrl=="") returnUrl = "yw/common/queryRyxx.json";
		this.returnUrl = returnUrl;
		var ri = 0;
		var map=[];
		var date=new Date;
		var year=date.getFullYear(); 
		for(var i = year;i>year-50;i--){
			var obj=[];
			obj[0] = i;
			obj[1] = i;
			map.push(obj);
		}
		var csm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
		var colModel = new Ext.grid.ColumnModel([
			{
				header: "姓名",
		        dataIndex: "xm",
		        sortable: true,
				width: 60
			},{
		        header: "与持证人关系",
		        dataIndex: "sbrjtgx",
		        sortable: true,
				width: 80,
				renderer:function(value, cellmeta, record, rowIndex,columnIndex, store){
//					if(rowIndex==0){
//						record.set("sbrjtgx",'01');
//						return Gnt.ux.Dict.getDictLable("CS_JTGX",record.get("sbrjtgx"))
//					}else{
//						return Gnt.ux.Dict.getDictLable("CS_JTGX", value);
//					}
					return Gnt.ux.Dict.getDictLable("CS_JTGX", value);
				}
		    },{
		        header: "公民身份号码",
		        dataIndex: "gmsfhm",
		        sortable: true,
				width: 120
		    },{
				header: "性别",
		        dataIndex: "xb",
		        sortable: true,
				width: 40,
				renderer:function(value){
					return Gnt.ux.Dict.getDictLable("CS_XB", value)
				}
			},{
				header: "出生日期",
		        dataIndex: "csrq",
		        sortable: true,
				width: 80
			},{
				header: "人员内部ID",
		        dataIndex: "rynbid",
		        hidden:true
			},{
				header: "户籍业务ID",
		        dataIndex: "hjywid",
		        hidden:true
			},{
				header: "迁出注销ID",
		        dataIndex: "qczxid",
		        hidden:true
			}
		]);
	 	var myuserStore = new Ext.data.JsonStore({
	 		proxy: new Ext.data.HttpProxy({
	 			url: returnUrl,
				method: "POST",
				disableCaching: true
		    }),
	        root: 'list',
	        id:'id',
	        totalProperty:'totalCount',
	        fields: [
	        	"xm",
				"sbrjtgx",
				"gmsfhm",
				"xb",
				"csrq",
				"rynbid",
				"hjywid",
				"qczxid"
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
	 	
	 	this.grid20004 = new Ext.grid.GridPanel({
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
			bodyStyle:'width:80%',
	        border:true,
	        anchor:'100% 100%',
		    margins: '0 0 0 0',
			cmargins: '0 0 0 0',        
	        frame:false,
			iconCls:'icon-grid',
	        title:'',
	        listeners:{
	        	rowclick:function(g, rowIndex, e){
					selectRecod = g.store.getAt(rowIndex);
					var win = this.findParentByType("qyz_window");
					ri = rowIndex;
					Ext.getCmp('yczrgxSel').setValue(g.store.getAt(rowIndex).data.sbrjtgx);
				}
			}/*,
	        bbar: new Ext.PagingToolbar({
					pageSize: 20,
					store: myuserStore,
					displayInfo: true
			})*/
	    });
//		this.grid20004 = new Gnt.ux.SjpzGrid({
//			title: '迁出人员列表',
//			region : 'center',
//			url: returnUrl,
//			pzlb: '20004',
//			pkname: 'rynbid',
//			width:300,
//			showPaging:false,
//			listeners:{
//				rowclick:function(g, rowIndex, e){
//					selectRecod = g.store.getAt(rowIndex);
//					var win = this.findParentByType("qyz_window");
//					ri = rowIndex;
//					Ext.getCmp('yczrgxSel').setValue(g.store.getAt(rowIndex).data.sbrjtgx);
//				}
//			}
//		});
		this.rightPanel = new Ext.Panel({
			region : 'east',
			width:'40%',
			items:[
				{
	    	    	height:20,
	    	    	border:false,
	    	    	frame:false
	    	    },
				{
	            	id:'centerId',
	            	title: '打印方式',
	            	region:'center',
		            xtype: 'fieldset',
		            layout:'column',
					height:80,
            		bodyStyle : 'padding:0 10 0 10',
					defaults:{
		            	columnWidth: 1,
		            	xtype:'radio',
		            	fieldLabel: '',
		            	name: 'fs'
		            },
		            items: [{
		            	id:'qyz11',
		            	boxLabel: '一户一证',
		            	name:'fs',
		            	inputValue : "1",
		            	checked:true,
		            	listeners:{
		            		'check' : function(checkbox, checked){ 
		                         if(checked){//只有在点击时触发
		                        	 var store = this.findParentByType("qyz_window").grid20004.store;
		                        	 var r = {};
		                        	 store.each(function (record) {//遍历数据
		                        		var json = record.json;
		                        		record.set('sbrjtgx', json.sbrjtgx);//选中一户一证，将所有持证人关系改回原先的值
		                        	 });
		                        	 var raw = store.getAt(0);
		                        	 Ext.getCmp('yczrgxSel').setValue(raw.data.sbrjtgx);
		                         }  
		                    }
		                //监听事件
			            }
		            },{
		            	id:'qyz12',
		            	boxLabel: '一人一证',
		            	name:'fs',
		            	inputValue : "2",
		            	listeners:{
		            		'check' : function(checkbox, checked){ 
		                         if(checked){//只有在点击时触发
		                        	 var store = this.findParentByType("qyz_window").grid20004.store;
		                        	 var r = {};
		                        	 store.each(function (record) {//遍历数据
		                        		 record.set('sbrjtgx', '01');//选中一人一证，将所有持证人关系设为本人
		                        	 });
		                        	 Ext.getCmp('yczrgxSel').setValue('01');
		                         }  
		                    }
		                //监听事件
			            }
		            }]
				},{
	            	id:'southId',
	            	title: '打印设置',
	            	region:'south',
		            xtype: 'fieldset',
		            autoHeight : true, 
		            labelWidth : 80,
		            items: [
		            	{
							region:'center',
							layout : 'form',
							bodyStyle : 'padding:0 0 0 0',
							border:false,
							frame:false,
							items : [{
								id:'yczrgxSel',
								xtype : 'dict_combox',
								dict:"VisionType=CS_JTGX&def=01&ignore=false",
								width:60,
								value:'01',
								anchor : '98%',
								name : '_text_yczrgx',
								hiddenName: 'yczrgx',
								//id:'zjlx',
								fieldLabel : '与持证人关系',
								listeners:{
									select:function(combo,record,index){
										this.findParentByType("qyz_window").grid20004.store.getAt(ri).set("sbrjtgx",Ext.getCmp('yczrgxSel').getValue());
						            }
					            }
							}]
						
		            	},{
		            		id:'qyzbh',
	            			xtype : 'textfield',
	            			border:false,
	            			frame:false,
	            			autoCreate: {tag: 'input', type: 'text', size: '20', autocomplete: 'off', maxlength: '8'},
	            			fieldLabel:"迁移证编号",
	            			width:60,
	            			anchor : '98%',
	//            			grow:true,
	            			name:'zjhm'
		            	},{
		            		id:'yznf',
		            		xtype : "combo",
		            		store : map,
	            			border:false,
	            			frame:false,
	            			fieldLabel:"印制年份",
	            			width:60,
	            			//editable:false,
	            			triggerAction:"all",
	            			maxHeight : 80,
	            			anchor : '98%'
		            	}
		            ]
				}
			],
			buttons:[{
		        text:'打印',
				minWidth:75,
				handler:function(){
					if(map&&map.length>0){
						if(!Ext.getCmp('yznf').getRawValue()){
							alert("印制年份必需填写正确！");
							return;
						}else{
							if(Ext.getCmp('yznf').getRawValue()>map[0][0]||Ext.getCmp('yznf').getRawValue()<map[map.length-1][0]){
								alert("印制年份请输入范围为！"+map[map.length-1][0]+"至"+map[0][0]);
								return;
							}
						}
					}else{
						alert("印制年份加载失败！");
					}
					if(!Ext.getCmp('qyzbh').getValue()){
						alert("迁移证编号必填！");
						return;
					}
					var reg =/^\d{8}$/;
					if (!reg.test(Ext.getCmp('qyzbh').getValue())) {
						alert("请输入8位纯数字");
						return false;
					}
					win = this.findParentByType("qyz_window");
					store = this.findParentByType("qyz_window").grid20004.store
					store.sort('sbrjtgx', 'ASC');
					//add by zjm 20191203 增加排完序后首个必须为本人
					var resFirst =store.getAt(0);
					if(!resFirst.get('sbrjtgx')){
						alert("与持证人关系不能为空!");
						return ;
					}
					if(!(resFirst.get('sbrjtgx')=='01'||resFirst.get('sbrjtgx')=='02'||resFirst.get('sbrjtgx')=='03')){
						alert("首个与持证人关系必须为本人");
						return ;
					}
					var data=store.data;
					var length=data.length;
					var rynbidT="";
					var flag = false;
					var array =[];
					var qyzbh =Ext.getCmp('qyzbh').getValue();
					var yznf = Ext.getCmp('yznf').getRawValue();
					if(Ext.getCmp('qyz11').getValue()&&!Ext.getCmp('qyz12').getValue()){
						var brCount = 0;
						for (var i=0;i<Math.ceil(length/4);i++){//一户一证
							var o1={};
							var arrayTemp =[];
							for(var j=i*4;j<(i+1)*4&&j<length;j++){
								var o={};
								var res =store.getAt(j);
								o.rynbid=res.get('rynbid');
								o.qyzbh = qyzbh;
								o.sbrjtgx = res.get('sbrjtgx');
								o.qczxid = res.get('qczxid');
								if(o.sbrjtgx=='01'||o.sbrjtgx=='02'||o.sbrjtgx=='03'){
									brCount++;
								}else{
									flag= true;
								}
								o.xm = res.get('xm');
								o.directTable="qyz2";
								o.hjywid=res.get('hjywid');
								o.yznf=yznf;//Ext.getCmp('yznf').getRawValue();
								arrayTemp.push(o);
							}
							var arrayTemp1 =[];
							o1.directTable="qyz2";
							arrayTemp1.push(arrayTemp);
							o1.qyzbh=qyzbh;
							o1.data=arrayTemp;
							//arrayTemp1 =arrayTemp;
							o1.yznf=yznf;
							array.push(o1);
						}
						if(brCount>1){
							alert("本人最多只能有一个!");
							return;
						}
//						for(var i =0;i<array.length;i++){
//							validFunction(Ext.getCmp('yznf').getRawValue(),Ext.getCmp('qyzbh').getValue(),2,array[i],data);
//						}
						//CreateFormPage1('qyz2',array,'qyz',0,0,0,0);
						//win.callback('qyz2',Ext.encode(array),length);
					}else if(!Ext.getCmp('qyz11').getValue()&&Ext.getCmp('qyz12').getValue()){
						if(flag){
							alert("选择一人一证时，与持证人关系只能选择本人，不得修改！");
							return;
						}
						//selectRecod
						var o={};
						o.rynbid=selectRecod.data.rynbid;
						o.qyzbh = qyzbh;//Ext.getCmp('qyzbh').getValue();
						o.sbrjtgx = selectRecod.data.sbrjtgx;   
						if(o.sbrjtgx!='01'){
							flag= true;
						}
						o.xm = selectRecod.data.xm;
						o.directTable="qyz1";
						o.hjywid=selectRecod.data.hjywid;
						o.qczxid = selectRecod.data.qczxid;
						o.yznf=yznf;//Ext.getCmp('yznf').getRawValue();
						array.push(o);
//						for (var i=0;i<length;i++){//一人一证
//							var o={};
//							o.rynbid=store.getAt(i).get('rynbid');
//							o.qyzbh = qyzbh;//Ext.getCmp('qyzbh').getValue();
//							o.sbrjtgx = store.getAt(i).get('sbrjtgx');
//							if(o.sbrjtgx!='01'){
//								flag= true;
//							}
//							o.xm = store.getAt(i).get('xm');
//							o.directTable="qyz1";
//							o.hjywid=store.getAt(i).get('hjywid');
//							o.yznf=yznf;//Ext.getCmp('yznf').getRawValue();
//							array.push(o);
//						}
						//validFunction(Ext.getCmp('yznf').getRawValue(),Ext.getCmp('qyzbh').getValue(),1,array,data);
					}
					printfunction(0,array,data);
					if(getQueryParam("hzywid")&&getQueryParam("ywlb")=='12'){
						Gnt.dealHzyw(getQueryParam("hzywid"));
					}
					
				}
		    },{
		        text:'关闭',
				minWidth:75,
				handler:function(){
					var win = this.findParentByType("qyz_window");
					
					win.hide();
				}
		    }]
		});
		
		this.items = [this.grid20004,this.rightPanel];
		
		Gnt.ux.SelectQyz.superclass.initComponent.call(this);
	}
    
});
Ext.reg('qyz_window', Gnt.ux.SelectQyz);
var qyzWindow = new Gnt.ux.SelectQyz({
	returnUrl:'yw/common/queryQyzList.json',
	callback: function(type,array,num,index){
		if(type=='qyz2'){
			CreateFormPage1(type,array,'qyz',0,0,0,0);
		}else if(type=='qyz1'){
			CreateFormPage1(type,array,'qyz',num,array,0,index);
		}
		
	}
});	
qyzWindow.on("hide",function(){
	if(ckdaFlag){
		alert("请补扫迁移证!");
		jumpToCkda(getQueryParam("hzywid"))
//		if(!(qclb=='0481'||qclb=='0482'||qclb=='0483'||qclb=='0484'||qclb=='0485'||qclb=='0485'||qclb=='0486'
//			||qclb=='0487'||qclb=='0488'||qclb=='0489'||qclb=='0490')){
//			alert("请补扫迁移证!");
//			jumpToCkda(getQueryParam("hzywid"))
//		}
	}
});
function qyzPrint(selectHjywid,ywfalg){
	var grid =qyzWindow.grid20004;
	if(ywfalg){
		ckdaFlag = true;
	}
	var store = grid.store;
	store.baseParams = {
			hjywid:selectHjywid,
			start:0,
			limit:20
		};
	store.load();
	store.on('load',function(s,records){
		if(Ext.getCmp('qyz11').getValue()){
			Ext.getCmp('yczrgxSel').setValue(store.getAt(0).data.sbrjtgx);
		}else if(Ext.getCmp('qyz12').getValue()){
	       	 store.each(function (records) {//遍历数据
	       		 records.set('sbrjtgx', '01');//选中一人一证，将所有持证人关系设为本人
	       	 });
	       	 Ext.getCmp('yczrgxSel').setValue('01');
		}
		Gnt.submit(
				{}, 
				"yw/common/queryDyBh",
				"获取打印编号，请稍后...", 
				function(jsonData,params){
					var list = jsonData.list;
					if(list[0].qyzbh){
						Ext.getCmp('qyzbh').setValue(addPreZero(Number(list[0].qyzbh)+1));
					}else{
						Ext.getCmp('qyzbh').setValue("");
					}
					if(list[0].yznf){
						Ext.getCmp('yznf').setValue(list[0].yznf);
					}else{
						Ext.getCmp('yznf').setValue("");
					}
				}
		);
		grid.fireEvent("rowclick",grid,0);
		grid.getSelectionModel().selectRange(0,0);
		qclb = store.getAt(0).data.qclb;
		
	});
	qyzWindow.show();
}
function jumpToCkda(){
	if(getQueryParam("hzywid")){
		Gnt.ux.Dict.getKzcs('10029',function(pz, user){
			var url = pz.bz;
			if(url.indexOf("?")<0)
				url += "?";
			else
				url += "&";
			url += "yhsfz="+user.gmsfhm+"&tokey="+user.tokey+"&sbid=" + getQueryParam("sbid");
			window.open(url);
		
			
		});
	}else{
		Gnt.ux.Dict.getKzcs('10029',function(pz, user){
			var url = pz.bz;
			if(url.indexOf("?")<0)
				url += "?";
			else
				url += "&";
			url += "yhsfz="+user.gmsfhm+"&tokey="+user.tokey;
			window.open(url);
		});
	}
	
}
function validFunction(yznf,qyzbh,flag,array,num,data){
	Gnt.submit(
			{yznf : yznf,qyzbh : qyzbh}, 
			"yw/lodop/validQyzbh", 
			"", 
			function(jsonData,params){
				if(jsonData.message>0){
					if(flag=='2'){
						alert("迁移证号码("+qyzbh+")印制年份("+yznf+")已被使用,请使用新号码？");
						qyzValid(yznf,qyzbh,flag,array,num,data);
					}else if(flag=='1'){
						if(window.confirm("迁移证号码("+qyzbh+")印制年份("+yznf+")已被使用,是否确定继续使用此号码？")){
							//printfunction(0,array,data);
							CreateFormPage1('qyz1',Ext.encode(array),"qyz1",0,array,0,qyzbh);
						}else{
							qyzValid(yznf,qyzbh,flag,array,num,data);
						}
					}
					
				}else{
					if(flag=='2'){
						CreateFormPage1('qyz2',Ext.util.JSON.encode(array),'qyz',num,data,0,qyzbh);
					}else if(flag=='1'){
						CreateFormPage1('qyz1',Ext.encode(array),"qyz1",0,array,0,qyzbh);
					}
				}
			}
	);
	
	
//	Ext.Ajax.request({
//		url: 'yw/lodop/validQyzbh.json',
//		method:'POST',
//		//async:false, 
//		params: {
//			yznf : yznf,
//			qyzbh : qyzbh
//		},
//		success: function(result,resp){
//			var jsonData = Ext.util.JSON.decode(result.responseText);
//			//callback(lodopId,jsonData,num,array,data);
//			if(jsonData.message>0){
//				alert("迁移证号码("+qyzbh+")印制年份("+yznf+")已被使用,请使用新号码？");
//				qyzValid(yznf,qyzbh,flag,array,num,data);
//			}else{
//				if(flag=='2'){
//					CreateFormPage1('qyz2',Ext.util.JSON.encode(array),'qyz',num,array,0,0);
//				}else if(flag=='1'){
//					printfunction(0,array,data)
//				}
//			}
//			alert(22222);
//			
//		},
//		failure: function(){
//			alert("验证迁移证编码方法报错啦！");
//			return false;
//		}
//	});
}