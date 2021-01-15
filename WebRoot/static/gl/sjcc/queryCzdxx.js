var selRes = null;
var curIndex = -1;
var selectmlpnbid = null;

Ext.onReady(function(){
	
	Ext.QuickTips.init();
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("20002",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	
	var czdxxGrid = new Gnt.ux.SjpzGrid({
		pkname: 'mlpnbid',
		region:'center',
        height:430,
		pzlb: '20002',
		title: '',
		url: 'gl/sjcc/queryyhdghz',
		showPaging:true,
		listeners:{
			rowclick:function(g, rowIndex, e){
    			selRes = g.store.getAt(rowIndex);
    			curIndex = rowIndex;
    			
    			selectmlpnbid = selRes.data.mlpnbid;
				
    			//人员基本资料更新
				//form20008.getForm().reset();
				//form20008.getForm().loadRecord(selRes);
    			
    		}
		}
	});
	
/*	var form20008 = new Gnt.ux.SjpzForm({
		closable: false,
		region:'center',
		height:500,
		pzlb: '20008',
		labelWidth : 120,
		cols:2,
//		bindStore:xblryGrid.store,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:czdxxGrid,
		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
		changeDictCust:function(cmb,res){
			
			return;
		},
		title: ''
	});*/
	
	var sjcx_window=new Gnt.ux.sjcxwin({
		callback: function(){
		
		}
	});
	
	var p3 = new Ext.Panel({
		layout:'border',
		//items:[czdxxGrid,form20008,{
			items:[{region:'north',
				height:40, 
		        border:false,
		        frame:false,
		        buttonAlign:'left',
		        buttons:[ new Ext.Button({
		        	text:'打印',
		        	minWidth:100,
		        	handler:function(){
		        		var data=czdxxGrid.store.data.items;
		        		sjcx_window.show();
		        		sjcx_window.setSelRes(data,"czdxx");
		        	}
		        })]
		        },czdxxGrid,{
			region:'south',
			//width: '100%', 
			height:40,
	        border:false,
	        frame:false,
	        buttons:[	        		     		        
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
		        		parent.Ext.getCmp('card').getLayout().setActiveItem(0);
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
	var store = czdxxGrid.store;
	store.baseParams = {
			config_key:'queryyhdghz',
			type:'czdxx',
			pcs:getQueryParam('pcs')
		};
	store.load({params:{start:0, limit:20}});
	
	/**
		等加载完毕后设置选中
	 */
	store.on("load",function(store) {  
		if(store.data.length > 0){
			curIndex = 0;
			czdxxGrid.fireEvent("rowclick",czdxxGrid,curIndex);
			czdxxGrid.getSelectionModel().selectRange(curIndex,curIndex);
		}
		
		//Gnt.toFormReadyonly(form20008);
		
	},czdxxGrid); 
	
});