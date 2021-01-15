var selRes = null;
var curIndex = -1;
var selectRynbid = null;
Ext.onReady(function(){
	
	Ext.QuickTips.init();
	
	var hzywid = getQueryParam("hzywid");
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("20004",function(){}))
		return;
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	var rkxxGrid = new Gnt.ux.SjpzGrid({
		id:'nextId',
		pkname: 'qczxid',
		pzlb: '20004',
		region:'center',
		title: '',
		url: 'yw/bbdy/sbdy/getSbXxxx.json',
		showPaging:true,
		listeners:{
			rowclick:function(g, rowIndex, e){}
		}
	});
	
	var sbdy_window=new Gnt.ux.sbdywin({
		callback: function(){
		
		}
	});

	var zdsz_dialog = new Gnt.ux.ZdszDialog({
		pzlb : rkxxGrid.pzlb,
		callback: function(zdKeyArray,zdValueArray,pxzdValue){
			if(zdKeyArray.length>0&&zdValueArray.length>0&&zdKeyArray.length==zdValueArray.length){
//				var data = rkxxGrid.store.data.items;
//				sbdy_window.show();
//				sbdy_window.setSelRes(data,"qc",zdKeyArray,zdValueArray);
				var param = rkxxGrid.store.baseParams;
				if(pxzdValue){
					param.pxzdValue = pxzdValue
				}
				param.config_key='querySbqcxx';
				Gnt.submit(
						param, 
						"yw/bbdy/sbdy/getSbXxxxDaochu.json", 
						"导出数据准备中，请稍后...", 
						function(jsonData,params){
							var dcdata = jsonData.list;
							sbdy_window.show();
							sbdy_window.setSelRes(dcdata,"qc",zdKeyArray,zdValueArray,dictMap);
						}
				);
			}else{
				alert("设置字段方法出错了！")
			}
		}
	});
	
	var p3 = new Ext.Panel({
		layout:'border',
		items:[rkxxGrid],
		buttons:[
			{
				text:'设置字段',
				minWidth:60,
				handler:function(){
					zdsz_dialog.show();
				}
			},{
				text:'导出',
				minWidth:60,
				handler:function(){	
					urlLink = "yw/common/downExcelZip?type="+rkxxGrid.type;
					Ext.Msg.wait("正在查询导出信息，请稍后...");
					 var elemIF = document.createElement("iframe");
        		        elemIF.src = urlLink;//"yw/common/downZp?dcParams="+Ext.encode('111');
        		        elemIF.style.display = "none";
        		        elemIF.setAttribute('async', false);
        		        document.body.appendChild(elemIF);
        		        var timer = setInterval(function () {
        		            var iframeDoc = elemIF.contentDocument || elemIF.contentWindow.document;
        		            // Check if loading is complete
        		            if (iframeDoc.readyState == 'complete' || iframeDoc.readyState == 'interactive') {
        		                // do something
        		            	Ext.Msg.hide();
        		                clearInterval(timer);
        		                return;
        		            }
        		        }, 500);
//					var param = rkxxGrid.store.baseParams;
//					param.config_key='querySbqcxx';
//					Gnt.submit(
//						param, 
//						"yw/bbdy/sbdy/getSbXxxxDaochu.json", 
//						"导出数据准备中，请稍后...", 
//						function(jsonData,params){
//							var dcdata = jsonData.list;
//							sbdy_window.show();
//							sbdy_window.setSelRes(dcdata,"qc",null,null,dictMap);
//						}
//					);
				}
			},{
				text:'返回',
				minWidth:60,
				handler:function(){
					parent.Ext.getCmp('card').getLayout().setActiveItem(0);
				}
			},{
				text:'关闭',
				minWidth:60,
				handler:function(){
					window.parent.parent.closeWorkSpace();
				}
			}
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
    	    //bodyStyle: 'padding:2px',
        	items:[p3]
        }
    });
	var pzlbData = Gnt.ux.Dict.getSjpzData(rkxxGrid.pzlb);
	var dictMap = new Map();
	Ext.each(pzlbData,function(r){
		if(r.dsname){
			dictMap.set(r.fieldname,r.dsname);
		}
	});
	Ext.Msg.wait("正在执行查询，请稍后...");
	var store = rkxxGrid.store;
	if(getQueryParam("pcs")!=null&&getQueryParam("pcs")!=""){
		store.baseParams = {
				pcs:getQueryParam("pcs"),
				slsj:getQueryParam("slsj"),
				qhtz:getQueryParam("qhtz"),
				sldw:getQueryParam("sldw"),
				slrid:getQueryParam("slrid"),
				config_key:'querySbqcxx'/*,
				start:0,
				limit:20*/
			};		
	}else if(getQueryParam("jwh")!=null&&getQueryParam("jwh")!=""){
		store.baseParams = {
				jwh:getQueryParam("jwh"),
				slsj:getQueryParam("slsj"),
				qhtz:getQueryParam("qhtz"),
				sldw:getQueryParam("sldw"),
				slrid:getQueryParam("slrid"),
				config_key:'querySbqcxx'/*,
				start:0,
				limit:20*/
			};		
	}else{
		store.baseParams = {
				slsj:getQueryParam("slsj"),
				qhtz:getQueryParam("qhtz"),
				sldw:getQueryParam("sldw"),
				slrid:getQueryParam("slrid"),
				config_key:'querySbqcxx'/*,
				start:0,
				limit:20*/
			};
	}
	var header=[];
	var shuxing = [];
	var arrTemp = Gnt.ux.Dict.getSjpzData(rkxxGrid.pzlb);
	var zdyValueKey = Gnt.zdyValueKeyTemp(arrTemp);
	rkxxGrid.type = 'queryQcxx';
	var config=rkxxGrid.colModel.config;
	Ext.each(config,function(r){
		header.push(r.header);
		shuxing.push(r.dataIndex);
	});
	store.baseParams.header = encodeURI(header);
	store.baseParams.shuxing = encodeURI(shuxing);
	store.baseParams.zdyValueKey = Ext.encode(zdyValueKey);
	store.baseParams.daochuFlag = 'queryQcxx';
	store.load();
	Ext.Msg.hide();
	
});