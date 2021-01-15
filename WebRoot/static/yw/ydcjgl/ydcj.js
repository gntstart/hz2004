var zplsid=null;
var zpxx=null;
var htmlStrStart = '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="cx/hjjbxx/ckxx/queryXxxx?goto=';
var htmlStrEnd = '"></iframe>';
//'?rynbid='+ getRynbid(rynbid) + "&goto=queryHdxx&hhnbid=" + getQueryParam("hhnbid") + '"> </iframe>';

var selectRynbid = null;
var selectHhnbidselectHhnbid = null;

Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("30039",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	
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
	
	
	var form30039 = new Gnt.ux.SjpzForm({
		pzlb: '30039',
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
						var data = form30039.getForm().getValues();
						if(Gnt.util.isEmpty(data)){
    						showInfo("请输入公民身份号码");
    						return;
    					}
						if(data.csrq==""){
							
						}
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
	     						Ext.getCmp('pic').items.items[0].body.update("<div id='_PHOTO_ID'/><IMG SRC='yw/ydcjgl/img/render/" + zplsid + "' width='100%' height='100%' />");
	     					}
	     				});
					/*	Gnt.submit(
		   	 					{gmsfhm:gmsfhm},
		   	 					"yw/ydcjgl/getzpxx",
		   	 					"正在加载，请稍后...", 
		   	 					function(jsonData,params){
		   	 						//showInfo("加载成功!");
		   	 					if(jsonData.list && jsonData.list.length>0){
		   	 						
		   	 					Gnt.photo.setPhoto(null, true);
		   	 						var data=jsonData.list[0];
		   	 						 zplsid=data.zplsid;
		   	 					
		   	 					if(zplsid &&　zplsid != 0){
		   	 						
		   	 					  Ext.getCmp('pic').items.items[0].body.update("<div id='_PHOTO_ID'/><IMG SRC='yw/ydcjgl/img/render/" + zplsid + "' width='100%' height='100%' />");
			   	 				}else{
			   	 				
			   	 					Ext.getCmp('pic').items.items[0].body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
			   	 				}
		   	 						
		   	 					 }
		   	 					}
		   	 			    );*/
					}
				}),{
			    	height:3,
			    	border:false,
			    	frame:false
			    },new Ext.Button({
					text:'删除照片',
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
						Ext.getCmp('pic').items.items[0].body.update("<div id='_PHOTO_ID'/><IMG SRC='yw/ydcjgl/img/render/" + zplsid + "' width='100%' height='100%' />");
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
					text:'委托书',
					minWidth:100,
					handler:function(){
						var arrayTemp=[];
						var o={};
						o.directTable="wts";
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
		    items:[form30039]
		}]
	});
	
	var colModel = new Ext.grid.ColumnModel([
	    {
 			header: "统计报表id",
 	        dataIndex: "tjbbid",
 	        sortable: true,
 			width: 200,
 			hidden:true
 		},{
 	        header: "居委会",
 	        dataIndex: "jcwh",
 	        sortable: true,
 			width: 200
 	    },{
 	        header: "统计年月",
 	        dataIndex: "tjny",
 	        sortable: true,
 			width: 120
 	    },{
 	        header: "户数",
 	        dataIndex: "hs",
 	        sortable: true,
 			width: 100
 	    },{
 	        header: "人数（男）",
 	        dataIndex: "rs_nan",
 	        sortable: true,
 			width: 100
 	    },{
	        header: "人数（女）",
	        dataIndex: "rs_nv",
	        sortable: true,
			width: 100
	    },{
	        header: "非农业人口数",
	        dataIndex: "fnyrks",
	        sortable: true,
			width: 100
	    },{
	        header: "未落常住人数",
	        dataIndex: "wlczrs",
	        sortable: true,
			width: 100
	    },{
	        header: "18岁以下人数",
	        dataIndex: "ysbsyxrs",
	        sortable: true,
			width: 100
	    },{
	        header: "18到35岁人数",
	        dataIndex: "ysbdsswsrs",
	        sortable: true,
			width: 100
	    },{
	        header: "35到60岁人数",
	        dataIndex: "sswdlssrs",
	        sortable: true,
			width: 100
	    },{
	        header: "60岁以上人数",
	        dataIndex: "lssysrs",
	        sortable: true,
			width: 100
	    },{
	        header: "年龄大于100人数（男）",
	        dataIndex: "nldyybrs_nan",
	        sortable: true,
			width: 100
	    },{
	        header: "年龄大于100人数（女）",
	        dataIndex: "nldyybrs_nv",
	        sortable: true,
			width: 100
	    }
	    
	    
 	]);
 	var myuserStore = new Ext.data.JsonStore({
  		proxy: new Ext.data.HttpProxy({
  			url: 'gl/bbgl/dssj/queryTjbbsjb',
 			method: "POST",
 			disableCaching: true
 	    }),
         root: 'list',
         id:'id',
         totalProperty:'totalCount',
         fields: [
 			"tjbbid",
 			"jcwh",
 			"tjny",
 			"hs",
 			"rs_nan",
 			"rs_nv",
 			"fnyrks",
 			"wlczrs",
 			"ysbsyxrs",
 			"ysbdsswsrs",
 			"sswdlssrs",
 			"lssysrs",
 			"nldyybrs_nan",
 			"nldyybrs_nv",
 			"czsj",
 			"czyid",
 			"czyip"
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
 	var dssjGrid = new Ext.grid.GridPanel({
         store: myuserStore,
         region: 'center',
         view:new Ext.grid.GridView({
 				//forceFit:true,
 				//autoFill:true,
 				enableRowBody:true
 		}),
 		stripeRows: true,
         cm: colModel,
         //sm:csm,
 		loadMask: {msg:'正在加载数据，请稍侯……'},
 		bodyStyle:'width:100%',
         border:true,
         anchor:'100% 100%',
 	    margins: '0 0 0 0',
 		cmargins: '0 0 0 0',        
         frame:false,
 		iconCls:'icon-grid',
         title:'',
         listeners:{
 			rowdblclick:function(g, rowIndex, e){
 			}
 		},
         bbar: new Ext.PagingToolbar({
 				pageSize: 50,
 				store: myuserStore,
 				displayInfo: true
 		})
     });
	
	
	

    	var p2 = new Ext.Panel({
    		layout:'border',
    		newYewu: function(){
    			
    		},
    		items:[
    			dssjGrid
    		]
    		,buttons:[
    			{
    				text:'关闭',
    				minWidth:60,
    				handler:function(){
    					window.parent.closeWorkSpace();
    				}
    			},{
    				text:'返回',
    				minWidth:60,
    				handler:function(){
    					Ext.getCmp('card').getLayout().setActiveItem(0);
    				}
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
	//Gnt.photo.setPhoto(null, true);
	
	
	
});