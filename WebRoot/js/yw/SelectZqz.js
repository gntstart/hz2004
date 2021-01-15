/**
 * 标准地址选择,必须先加载commFrames.js
 */
Gnt.ux.SelectZqz = Ext.extend(Ext.Window, {
	title:'准迁(移)证打印',
	closeAction:'hide',
	modal:true,
	width:600,
	height:400,
	margins:'5',
	layout:'border',
	pageSize:30,
	resetData:function(){
		this.grid10031.store.removeAll();
		this.hxx = null;
	},
	initComponent : function(){
		if(!Gnt.loadSjpzb("10031",function(){}))
			return;
		
		var returnUrl = this.returnUrl;
		if(!returnUrl || returnUrl=="") returnUrl = "yw/common/queryRyxx.json";
		this.returnUrl = returnUrl;
		
		Gnt.ux.Dict.loadDict(['CS_ZQZJLX'],function(){});
		
		this.grid10031 = new Gnt.ux.SjpzGrid({
			title: '迁入人员列表',
			region : 'center',
			url: returnUrl,//url:'yw/common/queryRyxx.json',//
			pzlb: '10031',
			pkname: 'ryid',
			width:'55%',
			showPaging:false,
			listeners:{
				rowclick:function(g, rowIndex, e){
					var win = this.findParentByType("zqz_window");
					
				}
			}
		});
		this.rightPanel = new Ext.Panel({
			region : 'east',
			width:'45%',
			items:[
				{
					region:'north',
		        	height:200,
		            border:false,
		            frame:false,
		            items : [
		            	{
			    	    	height:80,
			    	    	border:false,
			    	    	frame:false
			    	    },{
			    	    	autoHeight: true,
				            layout:'column',
		            		border:false,
		            		frame:false,
				            items: [
				            	{
				            		columnWidth: .7,
				            		border:false,
				            		frame:false,
				            		bodyStyle : 'padding:0 0 0 10',
				            		html:'共有准迁证页数	:	'
				            	},{
				            		id:'zqzPageId',
				            		columnWidth: .3,
				            		border:false,
				            		frame:false,
				            		html:'0'
				            	}
				            ]
		            	},{
			    	    	height:10,
			    	    	border:false,
			    	    	frame:false
			    	    },{
			    	    	autoHeight: true,
				            layout:'column',
		            		border:false,
		            		frame:false,
				            items: [
				            	{
				            		columnWidth: .7,
				            		border:false,
				            		frame:false,
				            		bodyStyle : 'padding:0 0 0 10',
				            		html:'当前打印页数	:	'
				            	},{
				            		id:'zqzPrintId',
				            		columnWidth: .3,
				            		border:false,
				            		frame:false,
				            		html:'0'
				            	}
				            ]
		            	}
		            ]
				},{
	            	id:'douthId',
	            	title: '打印设置',
	            	region:'south',
	            	autoHeight : true, 
		            xtype: 'fieldset',
		            labelWidth : 80,
		            items: [{
						id:'zjlx',
						region:'center',
						layout : 'form',
						//bodyStyle : 'padding:0 0 0 10',
						labelWidth : 60,
						border:false,
						frame:false,
						items : [{
							xtype : 'dict_combox',
							dict:"VisionType=CS_ZQZJLX",
//							VisionType:'CS_ZQZJLX',
							value:1,
							ignore:false,
							anchor : '80%',
							//width:200,
							name : 'zjlx',
							hiddenName: 'zjlx',
							fieldLabel : '证件类型'
						}]
					},{
		            		id:'zqzbh',
		            		labelWidth : 60,
	            			xtype : 'textfield',
	            			border:false,
	            			autoCreate: {tag: 'input', type: 'text', size: '20', autocomplete: 'off', maxlength: '8'},
	            			frame:false,
	            			fieldLabel:"准迁证编号",
	            			//width:'100%',
	            			anchor : '80%',
//	            			grow:true,
	            			name:'zjhm'
		            },{
		            	border:false,
	            		frame:false,
	            		id:"tips",
	            		bodyStyle:"padding:0 0 0 10",
	            		html:'<font color="red">电子准迁证已保存，请关闭！</font>'
	            }]
				}
			],
			buttons:[{
		        text:'打印',
				minWidth:75,
				id:'zqzBtn',
				handler:function(){
					//alert(Ext.getCmp('zjlx').items.items[0].value);
					var zjlx = Ext.getCmp('zjlx').items.items[0].value;
					var zqzbh = Ext.getCmp('zqzbh').getValue();
					if(zqzbh==""){
						alert("准迁证边编号不能为空！");
						return;
					}
					var reg = /^\d{8}$/;
					//var reg =/^[0-9]*$/;
					if (!reg.test(zqzbh)) {
						alert("请输入8位纯数字");
						return false;
					}
					var win = this.findParentByType("zqz_window");
					var store=win.grid10031.store;
					var array = new Array(Math.ceil(store.getCount()/4));
					if(store.getCount()>0){
						for(var j=0;j<array.length;j++){
							var objTemp=[];
							for(var i=0;i<store.getCount();i++){
								var spywid = store.getAt(i).json.spywid;
								var zqid = store.getAt(i).json.zqid;
								var xm = store.getAt(i).json.xm;
								var spsqzid = store.getAt(i).json.spsqzid;
								
								if((i/4)<(j+1)&&(i/4)>=j){
									var obj={};
									obj.spywid=spywid;
									obj.zqid = zqid;
									obj.xm = xm;
									obj.spsqzid = spsqzid;
									objTemp.push(obj);
								}
							}
							var o={};
							o.directTable="zqz";
							o.zqzbh=zqzbh;
							o.zjlx=zjlx;
							o.obj=objTemp;
							array[j]=o;
						}
						printfunction(0,array,zjlx);
						if(getQueryParam("hzywid")){
							Gnt.dealHzyw(getQueryParam("hzywid"));
						}
					}else{
						alert("未查询到有效数据！");
						return;
					}
					
				}
		    },{
		        text:'关闭',
				minWidth:75,
				handler:function(){
					var win = this.findParentByType("zqz_window");
					win.hide();
					jumpToYzsqy();
				}
		    }]
		});
		
		this.items = [this.grid10031,this.rightPanel];
		
		Gnt.ux.SelectZqz.superclass.initComponent.call(this);
	}
    
});
Ext.reg('zqz_window', Gnt.ux.SelectZqz);
var zqzWindow = new Gnt.ux.SelectZqz({
	//returnUrl:'yw/common/queryRyxx.json',
	returnUrl:'yw/common/queryZqzList.json',
	callback: function(zjlx,zqzbh,array){
		CreateFormPage1('zqz',zjlx,Ext.util.JSON.encode(array),0,0,0,zqzbh);
	}
}); 

zqzWindow.on("hide",function(){
	jumpToYzsqy()
});

function zqzPrint(selectSpywid){
	Ext.getCmp('tips').hide();
	var store = zqzWindow.grid10031.store;
	store.baseParams = {
			spywid:selectSpywid,
			start:0,
			limit:20
		};
	store.load();
	//Ext.getCmp('zjlx').setValue(1);
	store.on("load",function(store) {
		Ext.getCmp('zqzPageId').body.update(parseInt(store.getCount()/4)+1);//准迁证页数
		Ext.getCmp('zqzPrintId').body.update(parseInt(store.getCount()/4)+1);//当前打印页数
		var zjbh=store.getAt(0).data.zjbh;
		var kdqqy_qcdqbm =store.getAt(0).data.kdqqy_qcdqbm;
		if(!(typeof zjbh == "undefined" || zjbh == null || zjbh == "")&&!(typeof kdqqy_qcdqbm == "undefined" || kdqqy_qcdqbm == null || kdqqy_qcdqbm == "")){
			Ext.getCmp('zqzbh').setValue(zjbh);
			Ext.getCmp("zqzbh").disable();
			Ext.getCmp('zqzBtn').disable();
			Ext.getCmp('tips').show();
		}else{
			Gnt.submit(
					{}, 
					"yw/common/queryDyBh",
					"获取打印编号，请稍后...", 
					function(jsonData,params){
						var list = jsonData.list;
						if(list[0].zqzbh){
							Ext.getCmp('zqzbh').setValue(addPreZero(Number(list[0].zqzbh)+1));
						}else{
							Ext.getCmp('zqzbh').setValue("");
						}
						Ext.getCmp("zqzbh").enable();
						Ext.getCmp('tips').hide();
					}
			);
		}
		
	});
	zqzWindow.show();
}
var dqbm = null;
var u = null;
Gnt.ux.Dict.getUser(function(user){
	u = user;
	dqbm = user.dwdm.substr(0,4);
});
function jumpToYzsqy(){
	  var store = zqzWindow.grid10031.store;
      var zjbh = store.getAt(0).data.zjbh;
		var kdqqy_qcdqbm =store.getAt(0).data.kdqqy_qcdqbm;
		if(!(typeof zjbh == "undefined" || zjbh == null || zjbh == "")&&!(typeof kdqqy_qcdqbm == "undefined" || kdqqy_qcdqbm == null || kdqqy_qcdqbm == "")){
			if(dqbm==null){
				showErr("位置地区编码，请检查是否已经登录！");
				return;
			}
			
			var params1 = "authToken=" + u.authToken + "&sfzh=" + u.gmsfhm + "&yhxm=" + encodeURIComponent(u.xm) + "&yhid=" + u.yhid + "&dwdm=" + u.dwdm + "&yhdlm=" + u.yhdlm;
			var all_qxglurl = null;
			Gnt.submit(
					{}, 
					"util/other/service/queryConfig", 
					"获取配置，请稍后...", 
					function(jsonData,params){
						if(jsonData && jsonData.list && jsonData.list.length>0){
							kds_config = jsonData.list[0];
							all_qxglurl = kds_config.all_qxglurl;
							if(all_qxglurl.substring(all_qxglurl.length-1,all_qxglurl.length)!="/"){
								all_qxglurl += "/";
							}
							var url = all_qxglurl + "yw/qc_new.jsp?formdq=" 
							+ kdqqy_qcdqbm + "&todq=" + dqbm + "&spywid=" + store.getAt(0).data.spywid + "&" + params1;
							if(parent && parent.openWorkSpace){
								parent.openWorkSpace(url, "跨地市迁出处理", "kdsqccl");
							}else{
//								window.location.href = url;
								window.open(url);
							}
						}else{
							showErr("获取跨地市配置失败！");
						}
					},function(){
					},
					false
			);
			
//			var url = "yw/hjyw/yzsydqc";
//			if(parent.openWorkSpace)
//				parent.openWorkSpace(url,  "一站式异地迁出", url);
//			else
//				window.open(url);
		}


}
