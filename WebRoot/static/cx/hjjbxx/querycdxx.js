var selRes = null;
var curIndex = -1;
var selectRynbid = null;
var nbslid = null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	nbslid = getQueryParam("nbslid");
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("30017",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	var slxxGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		//region:'north',
		pzlb: '30017',
		title: '',
		url: 'cx/edzjxx/edzslcx/getEdzslxx',
		showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){
				
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
			}
		}
	});
	
	var form30017= new Gnt.ux.SjpzForm({
		closable: false,
		region:'center',
		pzlb: '30017',
		height:500,
		width:"85%",
		labelWidth : 120,
		cols:2,
//		bindStore:xblryGrid.store,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:slxxGrid,
		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
		changeDictCust:function(cmb,res){
			
			return;
		},
		title: ''
	});
	
	var bzls_window = new Gnt.ux.BzlsDialog({
		//选择立户信息回调
		callback: function(optype, dwxxModify){
			
		}
	});
	
	var p3 = new Ext.Panel({
		layout:'border',
		items:[form30017,{
	    	title: '',
		    collapsible: false,
		    region:'east',
		    height:100,
		    border:false,
		    frame:false,
		    width:"15%",
		    margins: '0 0 0 0',
		    bodyStyle:'padding:10px 10px 10px 10px',
		    layout:'table',
	    	 layoutConfig: {
	    		columns: 1
	    	 },
	    	 items:[{
					id:'picImage',
					title: '',
					//height:'100%',
					bodyStyle:'padding:10px 10px 10px 10px',
					html: '无照片',
					width:150,
					height:150,
					rowspan: 1,
					colspan:1
				}]
	    	 
	    
	    }],
	    buttons:[
				new Ext.Button({
					id:'bbhg',
					text:'包不合格',
					disabled:true,
					minWidth:80,
					handler:function(){
						if(selRes){
							Gnt.submit(
									selRes.data,//{slh:selRes.data.slh}, 
									"cx/edzjxx/edzslcx/zjdbzf.json", 
									"正在证件包不合格业务，请稍后...", 
									function(jsonData,params){
										if(jsonData.list){
											showInfo("包不合格业务成功！");
										}
										
									}
							);
						}else{
							alert("请先选中一条有效的数据!");
						}
					}
				}),
				new Ext.Button({
					id:'xxzf',
					text:'信息作废',
					disabled:true,
					minWidth:80,
					handler:function(){
						if(selRes){
							Gnt.submit(
									selRes.data,//{slh:selRes.data.slh}, 
									"cx/edzjxx/edzslcx/zjslzf.json", 
									"正在证件信息作废业务，请稍后...", 
									function(jsonData,params){
										if(jsonData.list){
											showInfo("信息作废业务成功！");
										}
										
									}
							);
						}else{
							alert("请先选中一条有效的数据!");
						}
						
					}
				}),new Ext.Button({
					id:'cxbz',
					text:'重新办证',
					disabled:true,
					minWidth:80,
					handler:function(){
						if(selRes){
	        				var url = basePath + "yw/edzzjgl/zjsl?jumpToZjsl="+"cxbz"+"&ryid="+selRes.data.ryid;			      
	        				if(parent && parent.openWorkSpace){
	        					parent.openWorkSpace(url,  "证件受理", "zjsl");
	        				}else{
	        					window.location.href = url;
	        				}
	        			
						}else{
							alert("请先选中一条有效的数据!");
						}
					}
				}),
				new Ext.Button({
					id:'bzls',
					text:'办证历史',
					minWidth:80,
					handler:function(){
						bzls_window.show();
						bzls_window.setSelRes(selRes);
					}
				}),
				new Ext.Button({
					text:'关闭',
					minWidth:80,
					handler:function(){
						window.parent.parent.closeWorkSpace();
					}
				}),
				new Ext.Button({
					text:'返回',
					minWidth:80,
					handler:function(){
						parent.Ext.getCmp('card').getLayout().setActiveItem(1);
						//Ext.getCmp('card').getLayout().setActiveItem(0);
					}
				})
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
	
	if(getQueryParam("rynbid")&& getQueryParam("rynbid")!=""){
//		Ext.getCmp("queryId").handler();
		var store = slxxGrid.store;
		store.baseParams = {
			rynbid:getQueryParam("rynbid"),
			slh:getQueryParam("slh"),
			start:0,
			limit:20
		};
		store.load();
		
		//Ext.getCmp('card').getLayout().setActiveItem(0);
		//v.doLayout();
		store.on("load",function(store){
			if(store.data.length > 0){
				var pkvalue = store.getAt(0).data[form30017.bindStore.pkname];
				
				slxxGrid.fireEvent("rowclick",slxxGrid,0);
				var re = form30017.bindStore.getById(pkvalue);
				if(re){
					form30017.getForm().loadRecord(re);
					var slzt = re.data.slzt;
					if(slzt=='11'||slzt=='13'||slzt=='16'||slzt=='31'||slzt=='71'||slzt=='73'||slzt=='89'
						||slzt=='92'||slzt=='93'||slzt=='94'||slzt=='95'||slzt=='96'||slzt=='97'||slzt=='98'){
						Ext.getCmp("bbhg").disable();
						Ext.getCmp("xxzf").disable();
						Ext.getCmp("cxbz").disable();
					}else if(slzt=='00'||slzt=='01'||slzt=='12'||slzt=='17'||slzt=='25'||slzt=='30'){
						Ext.getCmp("bbhg").disable();
						Ext.getCmp("xxzf").enable();
						Ext.getCmp("cxbz").enable();
					}else if(slzt=='02'){
						Ext.getCmp("bbhg").disable();
						Ext.getCmp("xxzf").disable();
						Ext.getCmp("cxbz").enable();
					}else if(slzt=='18'||slzt=='24'){
						Ext.getCmp("bbhg").enable();
						Ext.getCmp("xxzf").disable();
						Ext.getCmp("cxbz").disable();
					}
					var zpid = re.data.zpid;
					if(zpid &&　zpid != 0){
						Ext.getCmp('picImage').body.update("<IMG SRC='yw/common/img/render/" + zpid + "' width='100%' height='100%' />");
						//Ext.getCmp('picImage1').body.update("<IMG SRC='yw/common/img/render/" + zpid + "' width='100%' height='100%' />");
					}else{
						Ext.getCmp('picImage').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
						//Ext.getCmp('picImage1').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
					}
				}
				Gnt.toFormReadyonly(form30017);
				
			}
		})
    }else{
    	Ext.getCmp('nextId').setVisible(false);
    }
	
	
});