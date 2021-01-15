var selRes = null;
var curIndex = -1;
var selectRynbid = null;

Ext.onReady(function(){
	
	Ext.QuickTips.init();
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10010",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	
	var sbxxGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rhflid',
		region:'north',
        height:200,
		pzlb: '10010',
		title: '',
		url: 'cx/hjjbxx/ckxx/getXxxx.json',
		showPaging:true,
		listeners:{
			rowclick:function(g, rowIndex, e){
    			selRes = g.store.getAt(rowIndex);
    			curIndex = rowIndex;
    			
    			selectRynbid = selRes.data.rynbid;
				
    			//人员基本资料更新
				form10010.getForm().reset();
				form10010.getForm().loadRecord(selRes);
    			
    		}
		}
	});
	
	var form10010 = new Gnt.ux.SjpzForm({
		closable: false,
		region:'center',
		/*height:500,*/
		pzlb: '10010',
		labelWidth : 120,
		cols:2,
//		bindStore:xblryGrid.store,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:sbxxGrid,
		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
		changeDictCust:function(cmb,res){
			
			return;
		},
		title: ''
	});
	
	var jsryz_dialog = new Gnt.ux.JsryzlDialog({
		callback: function(type,data){
			//解锁后续操作
			var subdata = {
        			rynbid:getQueryParam("rynbid"),
        			rysdzt:"0",
        			jssdyy:data.jsryzlyy
        		};
        		
        		Gnt.submit(
        			subdata, 
        			"cx/hjjbxx/ckxx/processCzrkzt", 
        			"正在解锁人员资料，请稍后...", 
        			function(jsonData,params){
        				showInfo("人员(ID=" + getQueryParam("ryid") + ",身份证=" + getQueryParam("gmsfhm") + ") 成功解锁!");
        			}
        		);
		}
	});	
	var sdryz_dialog = new Gnt.ux.SdryzlDialog({
		callback: function(type,data){
			//锁定后续操作
			var subdata = {
        			rynbid:getQueryParam("rynbid"),
        			rysdzt:"6",
        			jssdyy:data.sdryzlyy
        		};
        		
        		Gnt.submit(
        			subdata, 
        			"cx/hjjbxx/ckxx/processCzrkzt", 
        			"正在锁定人员资料，请稍后...", 
        			function(jsonData,params){
        				showInfo("人员(ID=" + getQueryParam("ryid") + ",身份证=" + getQueryParam("gmsfhm") + ") 成功锁定!");
        			}
        		);
		}
	});
	
	var p3 = new Ext.Panel({
		layout:'border',
		items:[sbxxGrid,form10010,{
			region:'south',
			width: '100%', 
	        border:false,
	        frame:false,
	        buttons:[
	        	new Ext.Button({
		        	text:'标准地址',
		        	minWidth:100,
		        	handler:function(){
		        		if(getQueryParam("bzdzFlag")){
		        			Gnt.submit(
		        					{}, 
		        					"cx/hffcxxcx/getMdFive.json",
		        					"查询md5信息，请稍后...", 
		        					function(jsonData,params){
		        						if(jsonData&&jsonData.message){
		        							Gnt.ux.Dict.getKzcs('10016', function(config, user, kzdata){
		        								var url = config.bz;
		        								url += "?id=" +  encodeURI(jsonData.message);
		        								url += "&dlmj=" + encodeURI(user.yhdlm);
		        								url += "&yhsfzh=" + encodeURI(user.gmsfhm);
		        								window.open(url);
		        							});
		        						}
		        					}
		        			);
		        		}else{
		        			alert("门楼牌没有标准地址信息!");
		        		}
		        	}
		        }),
		        new Ext.Button({
		        	text:'解锁人员资料',
		        	minWidth:100,
		        	disabled:ryxxjsFlag>0?false:true,
		        	handler:function(){
		        		jsryz_dialog.show();
		        	}
		        }),
		        new Ext.Button({
		        	text:'锁定人员资料',
		        	minWidth:100,
		        	disabled:ryxxsdFlag>0?false:true,
		        	handler:function(){
		        		sdryz_dialog.show();
		        	}
		        }),
		        new Ext.Button({
		        	text:'关闭',
		        	minWidth:100,
		        	handler:function(){
		        		window.parent.parent.closeWorkSpace();
		        	}
		        }),
		        new Ext.Button({
		        	text:'返回',
		        	/*icon: "images/user.gif",
		        	cls: "x-btn-text-icon",*/
		        	minWidth:100,
		        	handler:function(){
		        		parent.Ext.getCmp('card').getLayout().setActiveItem(1);
		        	}
		        })
	        ]
	    
	    }]
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
	
	var store = sbxxGrid.store;
	store.baseParams = {
			ryid:getQueryParam("ryid"),
//			rynbid:'3407000001001474234',
			config_key:'queryRhflxx'
		};
	store.load();
	
	/**
		等加载完毕后设置选中
	 */
	store.on("load",function(store) {  
		if(store.data.length > 0){
			curIndex = 0;
			sbxxGrid.fireEvent("rowclick",sbxxGrid,curIndex);
			sbxxGrid.getSelectionModel().selectRange(curIndex,curIndex);
		}
		
		Gnt.toFormReadyonly(form10010);
		
	},sbxxGrid); 
	Gnt.ux.Dict.getKzcs('10035', function(pz, user) {
		if(pz.kzz==1){
			var tempGmsfhm = (user&&user.gmsfhm)?user.gmsfhm.substring(0,6)+'********'+user.gmsfhm.substring(14):"无";
			watermark.load({ watermark_txt: '姓名：'+user.xm+'          ip:'+user.ip+'        身份证号：'+ tempGmsfhm});
			PointerEventsPolyfill.initialize({});
		}
		
	});
});