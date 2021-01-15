/**
 */
var changeFlag = false;
var zpCount = 0;

Gnt.ux.SelectZpSkim = Ext.extend(Ext.Window, {
	title:'照片显示保存',
	closeAction:'hide',
	closable: false,
	resizable:false,
	modal:true,
	width:450,
	height:600,
	margins:'5',
	layout:'border',
	pageSize:30,
//	autoScroll:true,
	show:function(){
		if(zpCount == 0){
			showInfo("此人无照片信息！");
		}else{
			Gnt.ux.SelectZpSkim.superclass.show.call(this);
		}
	},
	resetData:function(){
		
	},
	initComponent : function(){
		
		var win = this;
		zpCount = 0;
		
		var tb = new Ext.Toolbar({
			frame:false,
		 	border:false,
			region : 'north',
			items:[
				{
			        iconCls:'icon-close',
			    	tooltip :'关闭',
		        	handler : function(dp, date){
		        		
//		        		for(var i = 0; i < zpCount; i++){
//		        			win.zpPanel.items.get(i).items.get(5).items.get(0).setHeight(480);
//		        			win.zpPanel.items.get(i).items.get(5).items.get(0).setWidth("100%");
//		        		}
//		    			
//		    			changeFlag = false;
		    			
    	 				win.hide();
		        	}
			    },"-"
			    ,{
			    	iconCls:'icon-save',
			    	tooltip :'保存所有照片',
			    	handler : function(dp, date){
			    		chart.save({
				            type: 'image/png',
				            filename:'testfile'
				        });
			    	}
			    }
			]
		});
		
		this.zpPanel = new Ext.Panel({
			region : 'center',
			tbar: tb,
//			autoScroll:true,
			bodyStyle :'overflow-x:hidden;overflow-y:scroll',//只显示垂直滚动条
//			width:'100%',
			layout : 'column',
			//xtype : 'fieldset',
			title : '',
			margins:'5'
		});
		
		/**
		 * 查询出照片后动态加载代码
		 * @param id
		 * @returns
		 */
		var rynbid = this.rynbid;
		if(!rynbid || rynbid==""){
			
		}
		var zpid = this.zpid;
		var gmsfhm = this.gmsfhm;
		var xm = this.xm;
		this.rynbid = rynbid;
		this.zpid = zpid;
		this.xm = xm;
		this.gmsfhm = gmsfhm;
		/*
		this.zpGrid = new Gnt.ux.SjpzGrid({
			url: "cx/hjjbxx/ckxx/queryZp",
			pzlb: '10021',
			pkname: 'zpid'
		});
		*/
//		var myStore = new Ext.data.JsonStore({
//	 		proxy: new Ext.data.HttpProxy({
//	 			url: 'cx/hjjbxx/ckxx/queryZp',
//				method: "POST",
//				disableCaching: true
//		    }),
//	        root: 'list',
//	        id:'id',
//	        totalProperty:'totalCount',
//	        fields: ["zpid","ryid","gmsfhm","xm","zp","lrrq"]
//	    });
		
		var subdata = {
			rynbid: this.rynbid,
			zpid:this.zpid,
			xm:this.xm,
			gmsfhm:this.gmsfhm
		};
		
		Gnt.submit(
			subdata, 
			"yw/fjgl/fjsh/queryZp", 
			"正在处理，请稍后...", 
			function(jsonData){
				if(jsonData && jsonData.list && jsonData.list.length > 0){
					for(var i = 0; i < jsonData.list.length; i++){
						var rec = jsonData.list[i];
						
						win.zpPanel.add({
							frame:false,
							border:false,
							layout : 'column',
							columnWidth:1,
							defaults : {
								frame:false,
								border:false,
								columnWidth:.5,
								bodyStyle : 'padding:5px 5px 0px 5px'
							},
							items:[
								{
									xtype:'hidden',
									name:'zpId',
									value:rec.zpid
				    	       	}
								,{
									layout: 'form',
									columnWidth:.35,
									labelWidth : 40,
									items:[{
										fieldLabel:'姓名',
										value:rec.xm,
										anchor:'100%',
										disabled:true,
										xtype : 'textfield'
									}]
								}
								,{
									layout: 'form',
									columnWidth:.6,
									labelWidth : 80,
									items:[{
										fieldLabel:'公民身份号码',
										value:rec.gmsfhm,
										anchor:'100%',
										disabled:true,
										xtype : 'textfield'
									}]
								}
								,{
									layout: 'form',
									columnWidth:.96,
									items:[{
										title: '',
										height:480,
//										bodyStyle:'padding:10px 10px 10px 10px',
										html: '<div ><img src="data:image/jpg;base64,' + rec.zp + '" width="100%" /></DIV>'
									}]
								}
							]
						});
						
						zpCount ++;
						
					}
				}
				
				var autoShow = win.autoShow;
				if(autoShow){
					win.show();
				}
				
				
				
			}
		);
		
		/*
		for(var i = 0; i < 5; i++){
			this.zpPanel.add(item);
			zpCount ++;
		}
		*/
		
		this.items = [this.zpPanel];
		
		Gnt.ux.SelectZpSkim.superclass.initComponent.call(this);
		
		
	}
});
Ext.reg('zpSkim_window', Gnt.ux.SelectZpSkim);

