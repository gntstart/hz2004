var selectedSelRes = null;
Gnt.ux.SelectMessageDialog= Ext.extend(Ext.Window, {
	title:'消息',
	closeAction:'hide',
	modal:true,
	width:400,
	height:350,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(user){
		Gnt.ux.SelectMessageDialog.superclass.show.call(this);
	},
	setSelRes:function(){
		grid = this.firstPanel;
		var store = grid.store;
		store.baseParams = {
				yhid:user.yhid
		};
		store.load({params:{start:0, limit:20}});	
		store.on("load",function(store) {  
		if(store.data.length > 0){
			curIndex = 0;
			grid.fireEvent("rowclick",grid,curIndex);
			grid.getSelectionModel().selectRange(curIndex,curIndex);
		}
	},grid); 
	},
	resetData:function(){
		//this.fs.getForm().reset();
	},
	initComponent : function(){
		var that = this;
		var messagecsm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
		var messagecolModel = new Ext.grid.ColumnModel([
			{
				header: "接收时间",
		        dataIndex: "jssj",
		        sortable: true,
				width: 120
			},{
		        header: "发送人姓名",
		        dataIndex: "fsryhxm",
		        sortable: true,
				width: 80
		    },{
		        header: "接收人姓名",
		        dataIndex: "jsryhxm",
		        sortable: true,
				width: 80
		    },{
				header: "是否已读",
		        dataIndex: "readflag",
		        sortable: true,
				width: 50,
				renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
					return value==1?"已读":"未读";
				}
			},{
				header: "消息",
		        dataIndex: "message",
		        sortable: true,
				width: 120
			},{
		        header: "消息Id",
		        dataIndex: "messageid",
		        sortable: true,
		        hidden:true,
				width: 120
		    },{
		    	header: "接收人id",
		        dataIndex: "fsryhid",
		        sortable: true,
		        hidden:true,
				width: 120
		    }
		]);
	 	var messageStore = new Ext.data.JsonStore({
	 		proxy: new Ext.data.HttpProxy({
	 			url: 'yw/common/queryMessage',//yw/hjyw/hzywcl/queryHzywcl
				method: "POST",
				disableCaching: true
		    }),
	        root: 'list',
	        id:'id',
	        totalProperty:'totalCount',
	        fields: [
	        	"jssj",
				"fsryhxm",
				"jsryhxm",
				"readflag",
				"message",
				"messageid",
				"fsryhid"
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
		var messageGrid = new Ext.grid.GridPanel({
	        store: messageStore,
	        region: 'center',
	        view:new Ext.grid.GridView({
					//forceFit:true,
					//autoFill:true,
					enableRowBody:true
			}),
			stripeRows: true,
	        cm: messagecolModel,
	        sm:messagecsm,
			loadMask: {msg:'正在加载数据，请稍侯……'},
			bodyStyle:'width:80%',
	        border:true,
	        anchor:'100% 100%',
		    margins: '0 0 0 0',
			cmargins: '0 0 0 0',        
	        frame:false,
			iconCls:'icon-grid',
	        title:'',
	        listeners:{
	        	rowclick:function(g, rowIndex, e){
	        		selectedSelRes = g.store.getAt(rowIndex);
	        	},
				rowdblclick:function(g, rowIndex, e){
					selectedSelRes = g.store.getAt(rowIndex);
					//navHandler.createDelegate(this,[1]);
					showDialog(selectedSelRes);
//					cardPanel.getLayout().setActiveItem(1);
//					Ext.getCmp("delMessage").hide();
//		        	Ext.getCmp("addMessage").show();
		        	
				}
			},
	        bbar: new Ext.PagingToolbar({
					pageSize: 20,
					store: messageStore,
					displayInfo: true
			})
	    });
		
		this.firstPanel = messageGrid;
		this.secondPanel = new Ext.Panel({
			anchor:'100% 100%',
			layout:'border',
			frame:true,
	    	border:false,
			items:[{
				region:'north',
				title:'',
				items:[{
					html:''
				},{
					id:'jssj',
					html:''
				}]
			},{
				frame:true,
		    	border:false,
		    	layout:'column',
		    	region:'center',
        		defaults:{
        			border:false,
        			frame:false
        		},
        		items:[
        			{
    	            	title: '',
    	            	columnWidth:1,
    		            xtype: 'fieldset',
    		            items: [
    		            	{
		            			xtype : 'htmleditor',
		            			id:'xxzw',
		            			width:500,
		            			height:200,
		            			border:false,
		            			frame:false,
		            			fieldLabel:"消息正文",
		            			bodyStyle : 'padding:0px 0px 0px -20px',
		            			name:'xxzw'
							
    		            	}
    		            ]
    				}
        			]
			}
			]
		});	
	    var i=0;
	    var navHandler = function(direction){
	 
	        if (direction == -1){
	        	Ext.getCmp("delMessage").show();
	        	Ext.getCmp("addMessage").hide(); 
	            i--;
	            if (i < 0) { i = 0;}
	        }
	        if (direction == 1){
	        	Ext.getCmp("delMessage").hide();
	        	Ext.getCmp("addMessage").show();
	        	this.secondPanel.items.items[0].items.items[0].body.update("发送人姓名:"+selectedSelRes.data.fsryhxm);
	            i++;
	            if (i > 1) { i = 1; return false;}
	        }
	        cardPanel.getLayout().setActiveItem(i);
	    };
	    
	    var showDialog = function(selectedSelRes){
	    	that.secondPanel.items.items[0].items.items[0].body.update("发送人姓名:"+selectedSelRes.data.fsryhxm);
	    	that.secondPanel.items.items[0].items.items[1].body.update("接收时间:"+transfer(selectedSelRes.data.jssj));
	    	//that.secondPanel.items.items[1].items.items[0].body.update(selectedSelRes.data.message);
	    	Ext.getCmp("xxzw").setValue(selectedSelRes.data.message);
	    	Ext.getCmp("xxzw").disable();
	    	Ext.getCmp("jssj").show();
			Ext.getCmp("delMessage").hide();
        	Ext.getCmp("addMessage").show();
	        cardPanel.getLayout().setActiveItem(1);
	        //更新为已读状态
	        Gnt.submit(
					{
						messageid:selectedSelRes.data.messageid}, 
						"yw/common/updateMessage", 
						"正在更新信息状态，请稍后...", 
						function(jsonData,params){
							that.firstPanel.store.reload(); 
						}
			);
	    };
	    var cardPanel = new Ext.Panel({
	        layout: 'card',
	        activeItem: 0,
	        bodyStyle:'padding:5px',
	        defaults:{
	            border:false
	        },
	        bbar: [ /*{
	            id: 'move-prev',
	            text: '所有消息',
	            minWidth:75,
	            handler:navHandler.createDelegate(this,[-1])
	        }, {
	            id: 'move-next',
	            text: '回复消息',
	            minWidth:75,
	            handler:navHandler.createDelegate(this,[1])
	        }*/],
	        items: [this.firstPanel,this.secondPanel]
	    });
	    this.cardPanel = cardPanel;
		this.items = cardPanel;
		Gnt.ux.SelectMessageDialog.superclass.initComponent.call(this);
	},
	 buttons:[{
	        text:'所有消息',
			minWidth:75,
			handler:function(){
				var win = this.findParentByType("message_dialog");
				Ext.getCmp("delMessage").show();
	        	Ext.getCmp("addMessage").hide();
	        	win.cardPanel.getLayout().setActiveItem(0);
			}
	    },{
	        text:'回复消息',
			minWidth:75,
			handler:function(){
				var win = this.findParentByType("message_dialog");
				Ext.getCmp("delMessage").hide();
	        	Ext.getCmp("addMessage").show();
	        	Ext.getCmp("jssj").hide();
	        	Ext.getCmp("xxzw").enable();
	        	Ext.getCmp("xxzw").setValue();
	        	win.secondPanel.items.items[0].items.items[0].body.update("发送人姓名:"+selectedSelRes.data.fsryhxm);
	        	win.cardPanel.getLayout().setActiveItem(1);
			}
	    },{
	        text:'删除',
			minWidth:75,
			id:'delMessage',
			handler:function(){
				var win = this.findParentByType("message_dialog");
				if(selectedSelRes){
					if(window.confirm('Delete record?')){
						Gnt.submit(
								{
									messageid:selectedSelRes.data.messageid}, 
									"yw/common/deleteMessage", 
									"正在删除信息，请稍后...", 
									function(jsonData,params){
										showInfo("信息删除成功！");
										win.firstPanel.store.reload(); 
									}
						);
					}
				}else{
					alert("请选中一条有效的数据!");
				}
			
			}
	    },{
	        text:'发送',
			minWidth:75,
			hidden:true,
			id:'addMessage',
			handler:function(){
				var win = this.findParentByType("message_dialog");
				
				//alert(win.secondPanel.items.items[1].items.items[0].items.items[0].getValue());
				var data ={
						fsryhid:user.yhid,
						fsryhxm:user.glyxm,
						message:win.secondPanel.items.items[1].items.items[0].items.items[0].getValue(),
						jsryhid:selectedSelRes.data.fsryhid,
						jsryhxm:selectedSelRes.data.fsryhxm
				};
				
				if(selectedSelRes){
					Gnt.submit(
							data, 
								"yw/common/xxhf", 
								"正在回复信息，请稍后...", 
								function(jsonData,params){
									showInfo("消息回复成功！",function(){
										win.firstPanel.store.reload(); 
										win.cardPanel.getLayout().setActiveItem(0);
										Ext.getCmp("delMessage").show();
							        	Ext.getCmp("addMessage").hide(); 
									});
								}
					);
				}else{
					alert("请选中一条有效的数据!");
				}
			
			}
	    },{
	        text:'关闭',
			minWidth:75,
			handler:function(){
				var win = this.findParentByType("message_dialog");
				win.hide();
			}
	    }]
	
	
});
Ext.reg('message_dialog', Gnt.ux.SelectMessageDialog);
function transfer(jssj){
	if(jssj&&jssj.length==14){
		var yyyy = jssj.substring(0,4);
		var mm = jssj.substring(4,6);
		var dd = jssj.substring(6,8);
		var hh = jssj.substring(8,10);
		var ff = jssj.substring(10,12);
		var ss = jssj.substring(12,14);
		return yyyy+"/"+mm+"/"+dd+" "+hh+":"+ff+":"+ss;
	}else{
		return "";
	}
}
