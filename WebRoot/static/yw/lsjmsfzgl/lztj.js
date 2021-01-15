var zpid=null;
var selectRynbid=null;
var selectRyid=null;
var sfzhm=null;
var tjlx="ssxq";
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	
	
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_EDZSLZT','CS_EDZSLYY','CS_EDZLZFS','CS_EDZZZLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	
	var csm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var colModel=new Ext.grid.ColumnModel([	                                       
        {
          header: "单位",
          dataIndex: "dwxx",
          sortable: true,
          width: 150,
          renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
        	  	if(value=="sum"){
        	  		return "总数";
	       	    }
         	    if(tjlx=="ssxq"){
         	    	return Gnt.ux.Dict.getDictLable('XZQHB', value);
         	    }
         	    if(tjlx=="pcs"){
         	    	return Gnt.ux.Dict.getDictLable('DWXXB', value);
         	    }
         	    if(tjlx=="jcwh"){
         	    	return Gnt.ux.Dict.getDictLable('JWHXXB', value);
         	    }
         	    if(tjlx=="xzjd"){
         	    	return Gnt.ux.Dict.getDictLable('XZJDXXB', value);
         	    }
         	    
          }
        },{
            header: "未打印",
            dataIndex: "wdy",
            sortable: true,
            width: 120
         },{
              header: "已打印",          
              dataIndex: "ydy",
              sortable: true,
              width: 120
           },{
               header: "已作废",          
               dataIndex: "zf",
               sortable: true,
               width: 120
            },
            {
                header: "合计",          
                dataIndex: "num",
                sortable: true,
                width: 120
             }
	   ]);
	var lztjStore = new Ext.data.JsonStore({
		proxy: new Ext.data.HttpProxy({
			url : 'yw/lsjmsfzgl/querylztj',
			method: "POST",
			disableCaching: true
	    }),
		root : 'list',
		id : 'csid',
		totalProperty : 'totalCount',
		fields : ["dwxx","wdy","ydy","zf","num"],
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
   	
   	var lztjGrid = new Ext.grid.GridPanel({
           store: lztjStore,
           region: 'center',
           height:400,
           //hideHeaders : Boolean,
           view:new Ext.grid.GridView({
   				enableRowBody:true
   		}),
   		stripeRows: true,
   		cm: colModel,
        sm:csm,
		loadMask: {msg:'正在加载数据，请稍侯……'},
   		//bodyStyle:'width:50%',
           border:true,
           frame:false,
           title:'',
           listeners:{
           	rowclick:function(g, rowIndex, e){
           		selectedSelRes = g.store.getAt(rowIndex);
           		
           				
           	},
   			rowdblclick:function(g, rowIndex, e){
   				selectedSelRes = g.store.getAt(rowIndex);
   			}
   		}
       });

	
var p1=new Ext.Panel({
	layout:'border',
	defaults: {
	    //collapsible: true,
	    //split: true,
	    bodyStyle: 'padding:15px'
	},
	items: [{
	    title: '',
	    region: 'south',
	    border:false,
	    frame:false,
	    height: 70,
	    cmargins: '0 0 0 0',
	  //  layout:'table',
	   // items:[{
            buttonAlign:'center',
	  	    buttons:[
	  	    	new Ext.Button({
	                  text:'查询',
	                  minWidth:100,
	                 handler:function(){
	                	 var store=lztjGrid.store;
	                	 var ssxq=Ext.getCmp("qx").getValue();
	                	 var pcs=Ext.getCmp("pcs").getValue();
	                	 var xzjd=Ext.getCmp("xz").getValue();
	      
	      				store.baseParams = {
	      						tjlx:tjlx,
	      						ssxq:ssxq,
	      						pcs:pcs,
	      						xzjd:xzjd		
	      					};
	      				store.load();
	      				Ext.getCmp('card').getLayout().setActiveItem(1);
	                 }
	            }),
	            new Ext.Button({
        			text:'关闭',
        			minWidth:100,
        			handler:function(){
        				window.parent.closeWorkSpace();
        			}
        		})
	  	    ]
	   // }]  
	},{
	    title: '',
	    collapsible: false,
	    region:'center',
	    border:false,
	    frame:false,
	    margins: '0 0 0 0',
	    height:400,
	    items:[
{
	// id:'item2',
	 title:'请选择统计的范围',
	 xtype: 'fieldset',
	 layout : 'form',
	 labelWidth:50,
	 
	 layoutConfig: {
	    		columns: 1
	    	 },
	 bodyStyle : 'padding:40px 0 0 120px',
	 border:true,
	 hidden:false,
	 width:'60%',
	 height:200,
	 items:[{
			//anchor:'86.5%',
			width:200,
			xtype:'search_combox',
			searchUrl:'dict/utils/searchXzqh.json?qybz=1',
			fields:["code","name"],
			valueField: "code",
			displayField: "name",
			id:'qx',
			name:'qxmc',
			maxLength:30,
			allowBlank:true,
			fieldLabel:'区县'
        	},{height:10,border:false,frame:false},{
    			//anchor:'86.5%',
    			width:200,
    			xtype:'search_combox',
    			searchUrl:'dict/utils/searchPcs.json',
    			fields:["mc","dm","qhdm","pcsList"],
    			valueField: "dm",
    			displayField: "mc",
    			hiddenName:'jlx',
    			id:'pcs',
    			name:'pcsmc',
    			maxLength:30,
    			allowBlank:true,
    			fieldLabel:'派出所'
            	},{height:10,border:false,frame:false},{
        			//anchor:'86.5%',
        			width:200,
        			xtype:'search_combox',
        			searchUrl:'dict/utils/searchXxb?visiontype=XZJDXXB',
        			hiddenName:'xzjd',
        			id:'xz',
        			maxLength:30,
        			allowBlank:true,
        			fieldLabel:'乡镇'
                	}]
 },{height:30,border:false,frame:false},
 {
    	// id:'item2',
    	 title:'选择统计单位',
    	 xtype: 'fieldset',
    	 layout : 'table',
    	 
    	 layoutConfig: {
 	    		columns: 8
 	    	 },
    	 bodyStyle : 'padding:5 5 5 5',
    	 border:true,
    	 hidden:false,
    	 width:'60%',
    	 height:60,
    	 items:[{
            	xtype:'radio',
            	labelSeparator: '',
            	checked:true,
            	boxLabel: '按区县统计',
            	name:'tjtype',
            	inputValue : "1",
            	listeners:{
            		'check' : function(checkbox, checked){ 
                         if(checked){
                        	 tjlx="ssxq";
                         }  
                    }
	            }
            	},{width:30,border:false,frame:false},{
	            	xtype:'radio',
	            	labelSeparator: '',
	            	name:'tjtype',
	            	boxLabel: '按派出所统计',
	            	inputValue : "2",
	            	listeners:{
	            		'check' : function(checkbox, checked){ 
	                         if(checked){
	                        	 tjlx="pcs";
	                         }  
	                    }
		            }
	            	},{width:30,border:false,frame:false},{
		            	xtype:'radio',
		            	labelSeparator: '',
		            	name:'tjtype',
		            	boxLabel: '按乡镇统计',
		            	inputValue : "3",
		            	listeners:{
		            		'check' : function(checkbox, checked){ 
		                         if(checked){
		                        	 tjlx="xzjd";
		                         }  
		                    }
			            }
		            	},{width:30,border:false,frame:false},{
			            	xtype:'radio',
			            	labelSeparator: '',
			            	name:'tjtype',
			            	boxLabel: '按居委会统计',
			            	inputValue : "4",
			            	listeners:{
			            		'check' : function(checkbox, checked){ 
			                         if(checked){
			                        	 tjlx="jcwh";
			                         }  
			                    }
				            }
			            	}]
     }  
	          
	           ]
	}]
});

var tjdy_window=new Gnt.ux.tjdywin({
	callback: function(){
	
	}
});


var p2=new Ext.Panel({
	layout:'border',
	defaults: {
	    bodyStyle: 'padding:0'
	},
	items: [{
	    title: '',
	    region: 'south',
	    border:false,
	    frame:false,
	    height: 40,
	    cmargins: '0 0 0 0',
            buttonAlign:'center',
	  	    buttons:[
				new Ext.Button({
				    text:'预览',
				    minWidth:100,
				    handler:function(){
					   var data=lztjGrid.store.data.items;
					   tjdy_window.show();
					   tjdy_window.setSelRes(data,tjlx);
					   Ext.getCmp("dyyl").handler();
				    }
				}),
				new Ext.Button({
				    text:'打印设置',
				    minWidth:100,
				   handler:function(){
					   var data=lztjGrid.store.data.items;
					   tjdy_window.show();
					   tjdy_window.setSelRes(data,tjlx);
					   Ext.getCmp("dysz").handler();
				   }
				}),
				new Ext.Button({
	                  text:'打印',
	                  minWidth:100,
	                 handler:function(){
	                	 var data=lztjGrid.store.data.items;
	                	 tjdy_window.show();
	                	 tjdy_window.setSelRes(data,tjlx);
	                 }
	            }),
	  	    	new Ext.Button({
	                  text:'关闭',
	                  minWidth:100,
	                 handler:function(){
	                	 window.parent.closeWorkSpace();
	                 }
	            }),
	            new Ext.Button({
        			text:'返回',
        			minWidth:100,
        			handler:function(){
        				Ext.getCmp('card').getLayout().setActiveItem(0);
        			}
        		})
	  	    ] 
	},lztjGrid]
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
	        	items:[p1,p2]
	        }
	    });
		
	 
		
		
	
});