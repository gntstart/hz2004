var zplsid=null;
var zpxx=null;
var selSbxxid = null;
var htmlStrStart = '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="cx/hjjbxx/ckxx/queryXxxx?goto=';
var htmlStrEnd = '"></iframe>';
//'?rynbid='+ getRynbid(rynbid) + "&goto=queryHdxx&hhnbid=" + getQueryParam("hhnbid") + '"> </iframe>';

var selectRynbid = null;
var selectHhnbidselectHhnbid = null;

Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("30036",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX','CS_EDZZZLX'],function(){});
	
	/**
		点击下一步后显示的内容
		暂时用户成员信息代替
	 */
/*	var hcyGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		pzlb: '10019',
    	region:'south',
		height:150,
    	title: '',
		url: 'yw/common/queryRyxx.json',
    	showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){
				
			}
		}
	});*/
	
	var shxxStore = new Ext.data.JsonStore({
		proxy: new Ext.data.HttpProxy({
			url : 'yw/ydcjgl/getzpxx',
			method: "POST",
			disableCaching: true
	    }),
		root : 'list',
		//id : 'nbslid',
		totalProperty : 'totalCount',
		fields : [
		           "zplsid",
		           "zpid",
		           "zp"
		          ],
		listeners : {			
			beforeload : function(store, options) {	
				
			},
			load : function() {
			
			},
			loadexception : function(mystore, options, response, error) {
				if (error) {
					var json = Ext.decode(response.responseText);
					if (json.messages)
						Ext.Msg.alert("提示", json.messages[0]);
				} else {
					Ext.Msg.alert("提示", response.responseText);
				}
			}
		}
	});
	
	
	var form30036 = new Gnt.ux.SjpzForm({
		pzlb: '30036',//30036
		url: 'gl/bbgl/dssj/queryTjbbsjb',
		title:'',
		cols:2,
		formType:'query'
	});
	
	var p1 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[{
			id:'pic',
		    title: '',
		    region:'east',
		    margins: '5 0 0 0',
		    cmargins: '5 5 0 0',
		    width: 200,
		    layout:'table',
	    	layoutConfig: {
	    		columns: 1
	    	 },
			items:[
			{
				//id:'_PHOTO_ID',
				title: '',
				height:'100%',
				bodyStyle:'padding:10px 10px 10px 10px',
				html: '<div id="_PHOTO_ID">照片</DIV>',
				width:150,
				height:180,
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
					text:'拍照获取',
					minWidth:100,
					handler:function(){
						var data = form30036.getForm().getValues();
						if(valid(data,1)){
							var gmsfhm=data.gmsfhm;
							var store=shxxStore;
		     				store.baseParams = {
		     						gmsfhm:gmsfhm
		     					};
		     				store.load();
		     				store.on("load",function(store){
		     					if(store.data.length>0){
		     						zpxx=store.data.items[0];
		     						zplsid=zpxx.data.zplsid;
		     						zpxx.data.zp=null;
		     						Gnt.photo.setPhoto(zpxx, true,true);
		     						//Ext.getCmp('pic').items.items[0].body.update("<div id='_PHOTO_ID'/><IMG SRC='yw/ydcjgl/img/render/" + zplsid + "' width='100%' height='100%' />");
		     					}else{
		     						alert("没有找到照片信息！");
		     					}
		     				});
						}
						
					}
				}),{
			    	height:3,
			    	border:false,
			    	frame:false
			    },new Ext.Button({
					text:'照片删除',
					minWidth:100,
					handler:function(){
						Gnt.photo.setPhoto(null, true);
						Gnt.photo.changeImageCallback("");
					}
				}),{
			    	height:3,
			    	border:false,
			    	frame:false
			    },new Ext.Button({
					text:'照片采集',
					id: 'zpcjImgBtn',
					minWidth:100,
					handler:function(){
						Gnt.photo.setPhoto(null, true);
						Gnt.photo.OpenEdit();
					}
				}),{
			    	height:100,
			    	border:false,
			    	frame:false
			    },{
			    	height:3,
			    	border:false,
			    	frame:false
			    },new Ext.Button({
					text:'申请单',
					minWidth:100,
					handler:function(){
						var arrayTemp=[];
						var o={};
						o.directTable="sldjb";
						arrayTemp.push(o);
						printfunction(0,arrayTemp);
					}
			    }),{
			    	height:3,
			    	border:false,
			    	frame:false
			    },new Ext.Button({
					text:'保存',
					minWidth:100,
					handler:function(){
						var data = form30036.getForm().getValues();
						data.zp = Gnt.photo.temp;
						if(valid(data,2)){
							Ext.getCmp('xmShow').setValue(data.xm);
							Ext.getCmp('sfhmShow').setValue(data.gmsfhm);
							var subdata = {
									voYdzjsbxx: data
								};
							for(o in subdata){
								if(subdata[o]){
									subdata[o] = Ext.encode(subdata[o]);
								}
							}
							log_code = "F1601";
							Gnt.submit(
									subdata, 
									"yw/ydbz/ydbzsl/addYdbzslxx", 
									"正在处理证件受理业务信息，请稍后...", 
									function(jsonData,params){
										showInfo("证件受理业务成功！",function(){
											selSbxxid=jsonData.list[0];
											Ext.getCmp('card').getLayout().setActiveItem(1);
										});
									}
							);
						}
					}
			    }),{
			    	height:3,
			    	border:false,
			    	frame:false
			    },new Ext.Button({
					text:'关闭',
					minWidth:100,
					handler:function(){
						window.parent.closeWorkSpace();
					}
			    })
		 ]
		}]
		},{
		    title: '',
		    collapsible: false,
		    border:false,
		    frame:false,
		    region:'center',
		    margins: '5 0 0 0',
		    items:[form30036]
		}]
	});
	
    	var p2 = new Ext.Panel({
    		layout:'border',
    		newYewu: function(){
    			
    		},
    		items:[{
		    	   region:'north',
		    	   title:'',
		    	   frame:false,
				   borde:false,
				   margins:'0',
			       layout:'form',
			       bodyStyle:'padding:5 0 0 50',
			       labelWidth:100,
	    	       items:[{
		        		layout:'column',
		    			frame:false,
		    			border:false,
		        		defaults:{
		        			border:false,
		        			frame:false
		        		},
		            	items:[{
			        		layout:'column',
			    			frame:false,
			    			border:false,
			        		defaults:{
			        			border:false,
			        			frame:false
			        		},
			            	items:[{
					                columnWidth:1,
				    	           	layout: 'form',
				    	           	bodyStyle:'padding:5 0 0 0',
				        	       	items: [{
										xtype:'textfield',
										anchor:'100%',
										id:'xmShow',
										name:'xm',
										disabled:true,
										fieldLabel:'姓名'
									}]
							},{
				                columnWidth:1,
			    	           	layout: 'form',
			    	           	bodyStyle:'padding:5 0 0 0',
			        	       	items: [{
									xtype:'textfield',
									anchor:'100%',
									id:'sfhmShow',
									name:'sfhm',
									disabled:true,
									fieldLabel:'身份号码'
								}]
							}
						]
					}]
				}]
		       },{
					   region:'center',
					   height:100,
					   html: '',
					   frame:false,
					   borde:false,
			    	   html: '<center><br/><br/><h2>证件受理业务办理完成！</h2></center>'
				},{
				   region:'south',
				   height:100,
				   html: '',
				   frame:false,
				   borde:false,
		    	   buttons:[
		    		   new Ext.Button({
							text:'继续受理',
							minWidth:100,
							width:150,
							handler:function(){
								window.location.reload();
							}
	    	            }),new Ext.Button({
							text:'领取单',
							minWidth:100,
							width:150,
							handler:function(){
								var arrayTemp=[];
								var o={};
								o.directTable="ydslsldjb";
								o.sbxxid =selSbxxid;
								arrayTemp.push(o);
								printfunction(0,arrayTemp);
							}
	    	            }),new Ext.Button({
							text:'关闭',
							minWidth:100,
							width:150,
							handler:function(){
								window.parent.closeWorkSpace();
							}
	    	            })
	    	       ]
		       }
		    ]
    	});
	
	
	
	//释放iframe占用资源
	function fixIFrame(o, p){
		var iFrame = p.getEl().dom; 
		if (iFrame.src) {
			iFrame.src = "javascript:false"; 
		}
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
        	items:[p1,p2]
        }
    });
	form30036.fieldSetValue(form30036.getForm().findField("zzlx"),1);
	
	function  valid(data,type){
		if(Gnt.util.isEmpty(data)){
			showInfo("请输入公民身份号码");
			return false;
		}
		if(data.xb==""){
			alert("请输入性别!");
			return false;
		}
		if(data.csrq>getyyyyMMdd()){
			alert("出生日期不能大于当前天数!");
			form30036.fieldSetValue(form30036.getForm().findField("csrq"),"");
			return false;
		}
		if(data.csrq==""){
			alert("请输入出生日期!");
			return false;
		}
		
		if(type==2){
			if(data.gmsfhm==""){
				showInfo("请输入公民身份号码");
				return false;
			}
			if(data.xb==""){
				alert("请输入性别!");
				return false;
			}
			if(data.csrq==""){
				alert("请输入出生日期!");
				return false;
			}
			if(data.ssxq==""){
				alert("省市县区不能为空!");
				return false;
			}
			if(data.xm==""){
				alert("姓名不能为空!");
				return false;
			}
			if(data.mz==""){
				alert("民族不能为空!");
				return false;
			}	
			if(data.zz==""){
				alert("住址不能为空!");
				return false;
			}	
			if(data.slyy==""){
				alert("申领原因不能为空!");
				return false;
			}
			if(data.lzfs==""){
				alert("领证方式不能为空!");
				return false;
			}
			if(typeof data.zp == "undefined" || data.zp == null || data.zp == ""){
				alert("照片不能为空!");
				return false;
			}
			if(data.ssxq.endWith('0000')){
				alert("不能选择省级区县代码！");
				return false;
			}
		}
		return true;
	}
  /*  if(getQueryParam("ai")){
    	Ext.getCmp('card').getLayout().setActiveItem(getQueryParam("ai"));
    	tabs.setActiveTab(0);
    }else{
    	Ext.getCmp('nextId').setVisible(false);
    }
	if(getQueryParam("jumpToCzrkcx")&& getQueryParam("jumpToCzrkcx")!=""){
		queryFlag = true;
		Ext.getCmp("queryId").handler();
	}    
	//户政业务处理
	if(hzywid && hzywid!=""){
		Gnt.makeHzyw({
			hzywid: hzywid,
			pch:pch,
			cmpids: ['queryId'],
			callback:function(jo, jolist){
				//成功处理返回，合并数据
				hzywjo_list =[];
				hzywjo_list.push(jo);
				Ext.each(jolist,function(d){
					hzywjo_list.push(d);
				});
				hzywjo = jo;
				
				form20000.getForm().findField("gmsfhm").setValue(jo.bsqrsfz);
				
				Ext.getCmp("queryId").handler();
				
			}
		});
	}*/
	
	Gnt.photo.setPhoto(null, true);
	function getyyyyMMdd(){
	    var d = new Date();
	    var curr_date = d.getDate();
	    var curr_month = d.getMonth() + 1; 
	    var curr_year = d.getFullYear();
	    String(curr_month).length < 2 ? (curr_month = "0" + curr_month): curr_month;
	    String(curr_date).length < 2 ? (curr_date = "0" + curr_date): curr_date;
	    var yyyyMMdd = curr_year + "" + curr_month +""+ curr_date;
	    return yyyyMMdd;
	}  
});