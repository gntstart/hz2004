var date=Ext.util.Format.date(new Date(),"m");
var qsDate=null;
if(date==1){
	qsDate=(Ext.util.Format.date(new Date(),"Y")-1)+"12";	
}else{
	qsDate=(Ext.util.Format.date(new Date(),"Ym")-1);	
}
var syyf="";
var byyf="";
Ext.onReady(function(){
	Ext.QuickTips.init();
/*	var bblbStore = new Ext.data.JsonStore({
		proxy: new Ext.data.HttpProxy({
			url : 'gl/ywbb/querybblb',
			method: "POST",
			disableCaching: true
	    }),
		root : 'list',
		id : 'csid',
		totalProperty : 'totalCount',
		fields : ["csid", "cslb", "dm", "mc"
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
	
	
	

	
	var colJFModel = new Ext.grid.ColumnModel([{
		header : "报表类别",
		width : 80,
		sortable : true,
		locked : false,
		dataIndex : 'dm'
	}, {
		header : "类别名称",
		width : 200,
		sortable : true,
		dataIndex : 'mc'
	}]);
	
	

	

	
	var grid = new Ext.grid.GridPanel({
		store : bblbStore,
		region : 'center',
		stripeRows : true,
		cm : colJFModel,
		loadMask : {
			msg : '正在加载数据，请稍侯……'
		},
		//bodyStyle : 'width:100%',
		border : true,
		//anchor : '100% 100%',
		//margins : '0 0 0 0',
		//cmargins : '0 0 0 0',
		frame : false,
		iconCls : 'icon-grid',
		title : '',
		   listeners:{
	        	rowclick:function(g, rowIndex, e){	        		
	        		selectedSelRes = g.store.getAt(rowIndex);
	        		ywbblb=selectedSelRes.data.dm;
	        		var store = ywbbxxGrid.store;	        		
	        		store.baseParams = {
	        			ywbblb:selectedSelRes.data.dm,
	        			start:0,
	        			limit:50
	        		};
	        		store.load();
	        		store.on("load",function(store) {  
	        			if(csstore.data.length > 0){
	        				curIndex = 0;
	        				ywbbxxGrid.fireEvent("rowclick",ywbbxxGrid,0);
	        				ywbbxxGrid.getSelectionModel().selectRange(curIndex,curIndex);
	        				
	        			}
	        		},ywbbxxGrid);
	        	},
				rowdblclick:function(g, rowIndex, e){
					selectedSelRes = g.store.getAt(rowIndex);
	        		var store = ywbbxxGrid.store;
	        		store.baseParams = {
	        		    ywbblb:selectedSelRes.data.dm,
	        			start:0,
	        			limit:50
	        		};
	        		store.load();
	        		store.on("load",function(store) {  
	        			if(csstore.data.length > 0){
	        				curIndex = 0;
	        				ywbbxxGrid.fireEvent("rowclick",ywbbxxGrid,0);
	        				ywbbxxGrid.getSelectionModel().selectRange(curIndex,curIndex);
	        				
	        			}
	        		},ywbbxxGrid);
				}
			}
	});
	
	
	var ywbbwhadd_win=new Gnt.ux.Selectywbbwhadd({//新增弹窗
		callback: function(ywbbmc,bbmbcontent){
			//提交数据
			Gnt.submit(
					{bbmc:ywbbmc,bblb:ywbblb,bbmbcontent:bbmbcontent}, 
					"gl/ywbb/addbblbxx", 
					"正在新增业务报表，请稍后...", 
					function(jsonData,params){
						showInfo("业务报表信息新增成功！");
						ywbbxxGrid.store.reload(); 
					}
			);
		}
	});
	
	var ywbbwhup_win=new Gnt.ux.Selectywbbwhup({//修改弹窗
		callback: function(ywbbmc,bbmbcontent){
			//提交数据
			Gnt.submit(
					{bbmc:ywbbmc,bblb:ywbblb,ywbbid:ywbbid,bbmb:bbmbcontent}, 
					"gl/ywbb/updatebblbxx", 
					"正在修改业务报表，请稍后...", 
					function(jsonData,params){
						showInfo("业务报表信息修改成功！");
						ywbbxxGrid.store.reload(); 
					}
			);
		}
	});*/
	 	
	 var borderPanel = new Ext.Panel({
		 layout:'border',
		 defaults: {
		     collapsible: true,
		     split: true,
		     bodyStyle: 'padding:15px'
		 },
		 items: [{
		     title: '',
		     collapsible: false,
		     region: 'south',
		     height: 60,
		     cmargins: '5 5 0 0',
		     layout:'table',
		     labelAlign :'left',
		     items:[
		      new Ext.Button({		   
		      text:'全部选取',
		       minWidth:60,
		     handler:function(){
		        Ext.getCmp("c1").setValue(true);
		        Ext.getCmp("c2").setValue(true);
		    }
           }),{width:10,border:false,frame:false},new Ext.Button({		
		      text:'全部清除',
		       minWidth:60,
		     handler:function(){
		    	 Ext.getCmp("c1").setValue(false);
			     Ext.getCmp("c2").setValue(false);
		    }
           })]
		 },{
		     title: '请选择生成人口底数的月份:',
		     collapsible: false,
		     region:'west',
		     margins: '5 0 0 0',
		     cmargins: '5 5 0 0',
		     width: 190,
		     layout:'table',
		     //border:false,
		    // frame:false,
		     layoutConfig: {columns: 1},
		     items:[{
		    	 layout:'form',
		            	id:'c1',
		            	xtype:'checkbox',
		            	labelWidth:90,
		            	border:false,
		            	frame:false,
		            	labelSeparator: '',
		            	boxLabel: qsDate,
		            	checked:false,
	    				listeners:{
	    					'check': function(obj,checked){
	    						if(checked){
	    							syyf=qsDate;
	    						}else{
	    							syyf="";
	    						}
	    					}
	    				}
		            
		     },
		     {
		    	 layout:'form',
		            	id:'c2',
		            	xtype:'checkbox',
		            	labelWidth:90,
		            	border:false,
		            	frame:false,
		            	labelSeparator: '',
		            	boxLabel: Ext.util.Format.date(new Date(),"Ym"),
		            	checked:false,
	    				listeners:{
	    					'check': function(obj,checked){
	    						if(checked){
	    							byyf=Ext.util.Format.date(new Date(),"Ym");
	    						}else{
	    							byyf="";
	    						}
	    					}
	    				}
		            
		     }]
		 },{
		     title: '',
		     collapsible: false,
		     region:'center',
		     margins: '5 0 0 0',
		    // layout:'table',
		     //layoutConfig: {columns: 1},
		     items:[{
		            border:false,
		            frame:false,
		            layout:'form',
		            height:390,
		            labelAlign :'right',
		            bodyStyle :'overflow-x:hidden;overflow-y:scroll', 
		            items:[
                       {border:false,frame:false,html:'说明一: 打勾的月份为没有统计的月份,需要统计,如果'},
                       {border:false,frame:false,width:10},{border:false,frame:false,html:'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;需要重新统计某月的底数，则要重新勾选该月份'},
                       {border:false,frame:false,width:10},{border:false,frame:false,html:'说明二: 一般情况下,只统计本月之前月份的底数,特殊'},
                       {border:false,frame:false,width:10},{border:false,frame:false,html:'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;情况下,如年中需要做年度报表,可以统计本月底数。'},
                       {border:false,frame:false,width:10},{border:false,frame:false,html:'说明三: 如果由于特殊原因在本月没有完成,统计了本月'},
                       {border:false,frame:false,width:10},{border:false,frame:false,html:'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;底数,则需要再下个月初重新统计本月底数。'}
                       
                      
		            ]
		            
		          
		 },{
			 border:false,
			 frame:false,
			 layout:'table',
			// layoutConfig: {columns: 1},
			 //bodyStyle:'padding:90px 0 0 600px',
			 buttonAlign:'right',
	            buttons:[
	                     new Ext.Button({		   
	             		      text:'底数生成',
	             		       minWidth:60,
	             		     handler:function(){
	             		    	 if(byyf!=""){
	             		    		 if(confirm("是否确定生成本月"+byyf+"的底数，本月的底数一般只有在做年终报表业务停办的时候,才会生成?")){
	             		    			if(confirm("再次确定是否生成本月"+byyf+"的底数，本月的底数一般只有在做年终报表业务停办的时候，才会生成？")){
	             		    				if(syyf!=""){
	             		    					var array=new Array(syyf,byyf);
	             		    					for(var i=0;i<array.length;i++){
	             		    						var tjsj=array[i];	           
	             		    						Gnt.submit(
	 	             		    	   	 				   {tjsj:tjsj}, 
	 	             		    	   	 					"gl/bbgl/dssj/queryDssc",
	 	             		    	   	 					"正在生成"+tjsj+"的人口底数，请稍后...", 
	 	             		    	   	 					function(jsonData,params){
	 	             		    	   	 						showInfo("底数生成成功!");
	 	             		    	   	 					if(jsonData.list && jsonData.list.length>0){
	 	             		    	   	 						var list=jsonData.list;
	 	             		    	   	 						
	 	             		    	   	 					 }
	 	             		    	   	 					}
	 	             			        	   	 		 );
	             		    					}
	             		    					
	             		    				}else{
	             		    					var tjsj=byyf;
	             		    					Gnt.submit(
	 	             		    	   	 				   {tjsj:tjsj}, 
	 	             		    	   	 					"gl/bbgl/dssj/queryDssc",
	 	             		    	   	 					"正在生成"+tjsj+"的人口底数，请稍后...", 
	 	             		    	   	 					function(jsonData,params){
	 	             		    	   	 						showInfo("底数生成成功!");
	 	             		    	   	 					if(jsonData.list && jsonData.list.length>0){
	 	             		    	   	 						var list=jsonData.list;
	 	             		    	   	 						
	 	             		    	   	 					 }
	 	             		    	   	 					}
	 	             			        	   	 		 );
	             		    				}
	             		    			}else{
	             		    				return;
	             		    			}
	             		    		 }else{
	             		    			return;
	             		    		 }
	             		    		 
	             		    	 }else if(syyf!=""){
	             		    		 var tjsj=syyf;
	             		    		Gnt.submit(
       		    	   	 				   {tjsj:tjsj}, 
       		    	   	 					"gl/bbgl/dssj/queryDssc",
       		    	   	 					"正在生成"+tjsj+"的人口底数，请稍后...", 
       		    	   	 					function(jsonData,params){
       		    	   	 						showInfo("底数生成成功!");
       		    	   	 					if(jsonData.list && jsonData.list.length>0){
       		    	   	 						var list=jsonData.list;
       		    	   	 						
       		    	   	 					 }
       		    	   	 					}
       			        	   	 		 );
	             		    	 }
	             		    
	             		      
	             		       
	             		    }
	                        }),//{width:10,border:false,frame:false},
	                        new Ext.Button({		   
	               		      text:'关 闭',
	               		       minWidth:60,
	               		     handler:function(){
	               		  	window.parent.closeWorkSpace();
	               		    }
	                          })
	                     ]
		 }]
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
	        	items:[borderPanel]
	        }
	    });
		
});
