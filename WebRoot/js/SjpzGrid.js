/**
 * 依据配置创建GRID，必须先加载commFrames.js
 * 
 使用之前，必须确保配置已经被加载，
 使用样例如下：
 var grid20016 = new Gnt.ux.SjpzGrid({
		pkname: 'hhnbid',		//必须参数
		pzlb: '20016',			//必须参数
		url: 'yw/queryRyxx',	//可选，但是通常必须提供，否则无法加载数据
		title:'标题',
		height:500,
		renderTo:'div1'
	});
 */
Gnt.ux.SjpzGrid = Ext.extend(Ext.grid.GridPanel, {
	title:'JSPZ-GRID',
	height:200,
	stripeRows: true,
    loadMask: {msg:'正在加载数据，请稍侯……'},
    border:false,
    margins:'0 0 0 5',
	frame:false,
    border:true,
    frame:true,
    getFieldLabel:function(colname){
		if(this.store.fieldMap && this.store.fieldMap[colname])
			return this.store.fieldMap[colname];
		
		return colname;
    },
    getPostParams:function(){
    	return {};
    },
    loadData:function(){
    	var data = this.getPostParams();
    	
    	this.store.baseParams = data;
		this.store.load({params:{start:0, limit:20}});
    },
    constructor:function(config){ 
		config.make_Highlight=function(rowIndex){
			if(this.last_Highlight){
				this.last_Highlight.set("_sel_2", "0");
			}
			
			if(rowIndex!=undefined){
    			var re = this.store.getAt(rowIndex);
    			re.set("_sel_2", "1");
    			
    			this.last_Highlight = re;
			}
		}
		
		//调用父亲的构造方法
    	if(config.listeners && config.listeners.rowclick){
    		config.rowclick_bak = config.listeners.rowclick;
    		config.listeners.rowclick = function(g, rowIndex, e){
    			if(g.rowclick_before){
    				g.rowclick_before(g, rowIndex, e);
    			}
    			
    			if(g.rowclick_bak)
    				g.rowclick_bak(g, rowIndex, e);
    			
    			//事件之后，做一些事情
    			if(g.rowclick_after){
    				g.rowclick_after(g, rowIndex, e);
    			}
    			
    			g.make_Highlight();
    		}
    	}
    	
    	Ext.grid.GridPanel.superclass.constructor.call(this,config); 
    },
	initComponent: function() {
		this.store = new Gnt.store.SjpzStore({
			pzlb: this.pzlb,
			pkname: this.pkname,
			bind_grid: this,
			loadCallback: this.loadCallback?this.loadCallback:function(){},
			url: this.url
		});
		
		var ary = Gnt.ux.Dict.getSjpzData(this.pzlb);
		var cm = new Array();
		if(this.checkboxFlag){
			cm.push(new Ext.grid.CheckboxSelectionModel({singleSelect:true}));
			this.sm=new Ext.grid.CheckboxSelectionModel ({ singleSelect: false })
		}
		if(ary instanceof Array){
			for(var i=0;i<ary.length;i++){
				var data = ary[i];
				var visibletype = data.visibletype;
				var dsname = data.dsname;
				if(visibletype && visibletype.length>=3 && visibletype.substring(2,3)=='1'){
					var item = null;
					var size = parseInt(data.fieldlength);
					if(size<40) size = 40;
					size = size *2;
					if(data.fieldname.indexOf("gmsfhm")>=0){
						size = 180;
					}
					if(data.fieldname.indexOf("hh")>=0){
						size = 100;
					}
					
					item = {
							 header: data.displayname,
							 dataIndex: data.fieldname,	
							 sortable: true,
							 width: size
					}
					
					if(dsname && dsname!=""){
						var renderer = "item.renderer=function(value, cellmeta, record, rowIndex,columnIndex, store){"
							+ "if(store.bind_grid && store.bind_grid.callCellmeta){"
							+	"	var css = store.bind_grid.callCellmeta(record);"
							+ "	if(css && css.length!=''){"
							+ "		cellmeta.attr = \"style='color:\" + css + \";'\";"
							+ "	}"
							+ "}"
							+ "if(record.data._sel == '1') cellmeta.css='changeplus';"
							+ "if(record.data._sel_2 == '1') cellmeta.css='changeplusGreen';"
							+ "	return Gnt.ux.Dict.getDictLable('" + dsname.toUpperCase() + "', value, cellmeta, record, rowIndex,columnIndex, store);"
							+ "}";
						
						eval(renderer);
					}else{
						item.renderer=function(value, cellmeta, record, rowIndex,columnIndex, store){
							if(store.bind_grid && store.bind_grid.callCellmeta){
								var css = store.bind_grid.callCellmeta(record);
								if(css && css.length!=""){
									//cellmeta.css = css; 这里设置背景，下面设置字体
									cellmeta.attr = "style='color:" + css + ";'";
								}
							}
							
							if(record.data._sel == '1')
					    		cellmeta.css='changeplus';
							
							if(record.data._sel_2 == '1')
					    		cellmeta.css='changeplusGreen';
							
							return value;
						};
					}
					
					cm.push(item);
				}
			}
		}
		this.cm=new Ext.grid.ColumnModel(cm);
		
		if(this.showPaging==undefined || this.showPaging==true){
			var that = this;
			var toolbar = new Ext.Toolbar({
				border: false,
				style: 'border: 0px;',
				items:['-',{
					text : '&nbsp;&nbsp;导出&nbsp;&nbsp;',
					id:'butedit',
					cls: 'button2',
					handler : function() {
//						document.location.href = "../util/upfile?importType=_nbzhcx";
							var ddcStore =that.store;
							var config=ddcStore.bind_grid.colModel.config;
							var zdyValueKey = that.zdyValueKey?that.zdyValueKey:that.store.fields.keys;//that.store.fields.keys
							var header=[];
							var shuxing = [];
							Ext.each(config,function(r){
								header.push(r.header);
								shuxing.push(r.dataIndex);
							});
							var dcParams = {};
							Ext.apply(dcParams, ddcStore.baseParams);
							Ext.apply(dcParams, {pageSize:ddcStore.totalLength});
							var urlLink = '';
							for (var sProp in dcParams) {
								 if(dcParams[sProp]){
									 var link = '&' + sProp + "=" +encodeURI(dcParams[sProp]);
						                urlLink += link;
								 }
							}
//				            Ext.each(dcParams, function(item, key) {
//				                var link = '&' + item + "=" + key;
//				                urlLink += link;
//				            })
							
							
//							dcParams.header = header;
//							dcParams.shuxing = shuxing;
//							Ext.Ajax.request({
//								url: "yw/common/downExcelZip",
//								method:'POST',
//								//async:false, 
//								params: dcParams,
//								success: function(result,resp){
//									
//								},
//								failure: function(){
//									Gnt.MsgBox.showError("导出信息出错!");
//									return false;
//								}
//							});
//							
//							return;
							
				            urlLink = "yw/common/downExcelZip?type=" +that.type;//urlLink.substr(1)+'&shuxing='+shuxing+"&header="+encodeURI(header)
				            urlLink.replace(' ', '');
//				            if(parent && parent.openWorkSpace){
//								parent.openWorkSpace(urlLink, "下载专用页面", "kdsqccl");
//							}else{
////								window.location.href = url;
//								window.open(url);
//							}
//				            return;
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
		        		     return;   
							/*
							 * 改写导出方式，将一次性导出改为每次导出分页的数据，
							 * 减少浏览器压力，崩溃  
							 * 20201014  
							*/
							var totalDc = ddcStore.totalLength;
							return;
							var pageSize = 20;
							/*if(totalDc>10000){
								pageSize = 10000;
							}else*/ if(totalDc>5000){
								pageSize = 5000;
							}else if(totalDc>2000){
								pageSize = 2000;
							}else if(totalDc>1000){
								pageSize = 1000;
							}else if(totalDc>500){
								pageSize = 500;
							}else if(totalDc>100){
								pageSize = 100;
							}else if(totalDc>50){
								pageSize = 50;
							}
							var  mo = Math.floor(totalDc/pageSize);
							var  yu = totalDc%pageSize;
							var pcSize =  yu>0?mo+1:mo;
							
							//return;
							zdydy_window.show();
							zdydy_window.hide();
							Ext.Msg.wait("正在查询导出信息，请稍后...");
							
							dachuInita(0,pcSize,header,shuxing,zdyValueKey,that.pxary,pageSize,ddcStore);
							
							
//							for(var i = 0;i<pcSize;i++){
//								var dcParams = {};
//								Ext.apply(dcParams, ddcStore.baseParams);
//								Ext.apply(dcParams, {pageindex:i});
//								dachuInit(header,shuxing,zdyValueKey,that.pxary,i,20,dcParams,ddcStore.url);
								//dcParams.pageSize = ddcStore.totalLength;
//								Gnt.submit(
//										dcParams, 
//										ddcStore.url, 
//										"正在查询导出信息，请稍后...", 
//										function(jsonData,params){
//											alert(i);
//			        						//zdydy_window.setSelRes(header,shuxing,zdyValueKey,jsonData.list,that.pxary,i,20);
//										}
//								);
//							}
							
						}
					},
					'-'	
				]
			});
			this.bbar=[];
			if(this.dcFlag){
				this.bbar.push(toolbar);
			}
			this.bbar.push(new Ext.PagingToolbar({
				pageSize: this.pageSize?this.pageSize:20,
						style:'alight: right;width: 400px; border: 0px;',
						store: this.store,
						displayInfo: true
					}));
		}
		//这个必须放store定义后，否则不可预料
		this.view=new Ext.grid.GridView({
			forceFit:false,
			autoFill:false,
			enableRowBody:true
	    });
		
		Gnt.ux.SjpzGrid.superclass.initComponent.call(this);
	}
});
Ext.reg('sjpz_grid', Gnt.ux.SjpzGrid);
function dachuPcInit(i,pcSize,header,shuxing,zdyValueKey,pxary,pageSize,dcParams,ddcStore){
	
	Ext.Ajax.request({
		url: ddcStore.url,
		method:'POST',
		//async:false, 
		params: dcParams,
		success: function(result,resp){
			var jsonData = Ext.util.JSON.decode(result.responseText);
			Ext.Msg.wait("正在导出第"+(i*pageSize+1)+"条至"+(i*pageSize+1+jsonData.list.length)+"条数据信息，请稍后...");
			zdydy_window.setSelRes(header,shuxing,zdyValueKey,jsonData.list,pxary,i,pageSize);
			i ++ ;
			return dachuInita(i,pcSize,header,shuxing,zdyValueKey,pxary,pageSize,ddcStore);
		},
		failure: function(){
			Gnt.MsgBox.showError("导出信息出错!");
			return false;
		}
	});
	
//	Gnt.submit(
//	dcParams, 
//	ddcStore.url, 
//	"正在查询导出信息，请稍后...", 
//	function(jsonData,params){
//		zdydy_window.setSelRes(header,shuxing,zdyValueKey,jsonData.list,pxary,i,pageSize);
//		i ++ ;
//		return dachuInita(i,pcSize,header,shuxing,zdyValueKey,pxary,pageSize,ddcStore);
//	}
//	);
}
function dachuInita(i,pcSize,header,shuxing,zdyValueKey,pxary,pageSize,ddcStore){
	if(i < pcSize){
		var dcParams = {};
		Ext.apply(dcParams, ddcStore.baseParams);
		Ext.apply(dcParams, {pageIndex:i+1});
		dcParams.pageSize=pageSize;
		dachuPcInit(i,pcSize,header,shuxing,zdyValueKey,pxary,pageSize,dcParams,ddcStore);
	}else{
		Ext.Msg.hide();
		zdydy_window.show();
	}
}