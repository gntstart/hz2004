var selRes = null;
var curIndex = -1;
var selectRynbid = null;

Ext.onReady(function(){
	
	Ext.QuickTips.init();
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("20020",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	
	var hxxGrid = new Gnt.ux.SjpzGrid({
		pkname: 'hhnbid',
		//region:'north',
		pzlb: '20001',
		title: '',
		url: 'cx/hjjbxx/hxxcx/getHxx',
		showPaging:false
	});
	
	var form20020 = new Gnt.ux.SjpzForm({
		closable: false,
		/*region:'north',
		height:500,*/
		pzlb: '20001',
		labelWidth : 120,
		cols:2,
//		bindStore:xblryGrid.store,
		//当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
		bindViewGrid:hxxGrid,
		//当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如迁出业务中的迁出登记没有grid
		changeDictCust:function(cmb,res){
			
			return;
		},
		title: ''
	});
	
	
	var p3 = new Ext.Panel({
		layout:'border',
		items:[{
	    	id:'centerId',
	        region:'center',
	        //禁止横向滚动条
	        border:false,
	        frame:false,
	        items:[{
					border:false,
					frame: false,
		        	region:'south',
		        	height: 40,
		        	bodyStyle: 'padding:10px 0px 0px 0px ',
					layout:'column',
			    	items: [{
		    			columnWidth: .01,
						border:false,
						frame: false
		    		},{
						border:false,
						frame: false,
		    		    items:[new Ext.Button({
		    		    	id:'fzId',
		        			text:'户成员信息',
		        			minWidth:80,
		        			handler:function(){
		        				alert(form20020.getForm().getValues().hhnbid);
		        			}
		        		})]
		    		},{
		    			columnWidth: .01,
						border:false,
						frame: false
		    		},{
						border:false,
						frame: false,
		    		    items:[new Ext.Button({
		        			text:'户成员变动信息',
		        			minWidth:60,
		        			handler:function(){
		        				
		        			}
		        		})]
		    		}]
	        },form20020]
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
	
	var store = hxxGrid.store;
	store.baseParams = {
			rynbid:getQueryParam("rynbid"),
			ryid:getQueryParam("ryid"),
//			rynbid:'3407000001001474234',
			config_key:'queryPoHJXX_CZRKJBXXB4',
			start:0,
			limit:20
		};
	store.load();
	
	
	store.on("load",function(store) {
		if(store.data.length > 0){
			curIndex = 0;
			/*
			hxxGrid.fireEvent("rowclick",hxxGrid,curIndex);
			hxxGrid.getSelectionModel().selectRange(curIndex,curIndex);
			*/
			
			/**
				0 : 正常
				1 : 死亡
				2 : 迁出
				3 : 服兵役
				4 : 出国境定居
				6 : 失踪
				7 : 删除
				8 : 注销
				9 : 其它
			 */
//			alert(store.getAt(0).get("ryzt"));
			if(store.getCount() > 0){
//				if(hxxGrid.getSelectionModel().getCount() ==1){
					if(0 == store.getAt(0).get("ryzt")){
						Ext.getCmp('ryztId').body.update("人员状态<br />正常");
					}else if(1 == store.getAt(0).get("ryzt")){
						Ext.getCmp('ryztId').body.update("人员状态<br />死亡");
					}else if(2 == store.getAt(0).get("ryzt")){
						Ext.getCmp('ryztId').body.update("人员状态<br />迁出");
					}else if(3 == store.getAt(0).get("ryzt")){
						Ext.getCmp('ryztId').body.update("人员状态<br />服兵役");
					}else if(4 == store.getAt(0).get("ryzt")){
						Ext.getCmp('ryztId').body.update("人员状态<br />出国境定居");
					}else if(6 == store.getAt(0).get("ryzt")){
						Ext.getCmp('ryztId').body.update("人员状态<br />失踪");
					}else if(7 == store.getAt(0).get("ryzt")){
						Ext.getCmp('ryztId').body.update("人员状态<br />删除");
					}else if(8 == store.getAt(0).get("ryzt")){
						Ext.getCmp('ryztId').body.update("人员状态<br />注销");
					}else if(9 == store.getAt(0).get("ryzt")){
						Ext.getCmp('ryztId').body.update("人员状态<br />其它");
					}
//				}
			}
			
			/**
				往表单设值
			 */
			var pkvalue = store.getAt(0).data[form20020.bindStore.pkname];
			var re = form20020.bindStore.getById(pkvalue);
			if(re){
				form20020.getForm().loadRecord(re);
			}
			
			
		}
	},hxxGrid); 
	
});