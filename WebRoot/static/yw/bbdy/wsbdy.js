 var date=new Date;
 var year=date.getFullYear(); 
 var save_type=1;
 var tj_type=2;
 var ksd=null;
 var jsd=null;
 var dwdm=null;
 var dwmc=null;
 var dataList=null;
 var sdate = (year.toString()+"/01");
 var edate = (year.toString()+"/12");
 var flag=null;
 var month=null;
 var dindex=0;
 var storeval=null;
 var zdy=0;
 var selstore=null;
Ext.onReady(function(){
	Ext.QuickTips.init();

	var pcsStore = new Ext.data.JsonStore({
		proxy: new Ext.data.HttpProxy({
			url : 'yw/wsbdy/querypcs',
			method: "POST",
			disableCaching: true
	    }),
		root : 'list',
		//id : 'csid',
		totalProperty : 'totalCount',
		fields : ["dm", "mc"],
		listeners : {			
			beforeload : function(store, options) {
			},
			load : function() {
				Ext.getCmp("qsws").setValue("001");
				Ext.getCmp("jsws").setValue("999");
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
	
	var monthsStore = new Ext.data.JsonStore({
		proxy: new Ext.data.HttpProxy({
			url : 'yw/wsbdy/getmonths',
			method: "POST",
			disableCaching: true
	    }),
		root : 'list',
		//id : 'csid',
		totalProperty : 'totalCount',
		fields : ["wssj","ksd","jsd"],
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
	
	var wsStore = new Ext.data.JsonStore({
		proxy: new Ext.data.HttpProxy({
			url : 'yw/wsbdy/queryws',
			method: "POST",
			disableCaching: true
	    }),
		root : 'list',
		//id : 'csid',
		totalProperty : 'totalCount',
		fields : ["xm","xb","csrq","gmsfhm","sxh","sldw"],
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
	
	var csm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
	var colModel = new Ext.grid.ColumnModel([
		csm,{
			header: "代码",
	        dataIndex: "dm",
	        sortable: true,
			width: 120
		},{
	        header: "名称",
	        dataIndex: "mc",
	        sortable: true,
	        hidden:false,
			width: 120
	    }
	]);
	
	var wsbModel = new Ext.grid.ColumnModel([
	         {
	            header: "出生日期",
	            dataIndex: "csrq",
	            sortable: true,
	            width: 120
	          },{
	            header: "顺序号",
	            dataIndex: "sxh",
	            sortable: true,
	            width: 120
	          },{
		         header: "号码所属人姓名",
		         dataIndex: "xm",
		         sortable: true,
		         width: 120
		      },{
			      header: "性别",
			      dataIndex: "xb",
			      sortable: true,
			      width: 120
			   },{
				  header: "公民身份证号码",
				  dataIndex: "gmsfhm",
				  sortable: true,
				  width: 120
				 },{
					  header: "单位",
					  dataIndex: "sldw",
					  sortable: true,
					  width: 120
					 }
	     ]);
	
	
	var wsdModel=new Ext.grid.ColumnModel([	                                       
        {
          header: "月份",
          dataIndex: "wssj",
           sortable: true,
          width: 120
        }                          
	   ]);
	
	var pcsGrid = new Ext.grid.GridPanel({
        store: pcsStore,
        region: 'center',
        hideHeaders : Boolean,
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
        /*anchor:'100% 100%',
	    margins: '0 0 0 0',
		cmargins: '0 0 0 0',        
		iconCls:'icon-grid',*/
        title:'',
        listeners:{
        	rowclick:function(g, rowIndex, e){
        		selectedSelRes = g.store.getAt(rowIndex);
        		dwdm=selectedSelRes.data.dm;
        		 var store=monthsGrid.store;
        		    var kssj=Ext.getCmp("kssj").value;
        			var jssj=Ext.getCmp("jssj").value;
        				store.baseParams = {
        						zdy:zdy,
        						dwdm:dwdm,
        						kssj:kssj,
        						jssj:jssj,
        						start:0,
        						limit:20
        					};
        				store.load();	
        				store.on("load",function(store) {  
        					if(store.data.length > 0){
        						curIndex = 0;
        						var selectedSelRes = store.getAt(0);
        						month=selectedSelRes.data.wssj;
        		        		ksd=selectedSelRes.data.ksd;
        		        		jsd=selectedSelRes.data.jsd;
        						monthsGrid.fireEvent("rowclick",monthsGrid,curIndex);
        						monthsGrid.getSelectionModel().selectRange(curIndex,curIndex);
        						
        					}
        				},monthsGrid);
        	},
			rowdblclick:function(g, rowIndex, e){
				selectedSelRes = g.store.getAt(rowIndex);
			}
		}/*,
        bbar: new Ext.PagingToolbar({
				pageSize: 20,
				store: pcsStore,
				displayInfo: true
		})*/
    });
	
	
	var wsGrid = new Ext.grid.GridPanel({
        store: wsStore,
        region: 'center',
        view:new Ext.grid.GridView({
				enableRowBody:true
		}),
		stripeRows: true,
        cm: wsbModel,
		loadMask: {msg:'正在加载数据，请稍侯……'},
        border:true,
        frame:false,
        bodyStyle:'overflow-y:auto;height:300px',
        anchor:'100% 100%',
	    margins: '0 0 0 0',
		cmargins: '0 0 0 0',        
		iconCls:'icon-grid',
        title:'',
        listeners:{
        	rowclick:function(g, rowIndex, e){
        		selectedSelRes = g.store.getAt(rowIndex);
        	},
			rowdblclick:function(g, rowIndex, e){
				selectedSelRes = g.store.getAt(rowIndex);
			}
		},
        bbar: new Ext.PagingToolbar({
				pageSize: 20,
				store: wsStore,
				displayInfo: true
		})
    });
	
	
	var monthsGrid = new Ext.grid.GridPanel({
        store: monthsStore,
        region: 'center',
        hideHeaders : Boolean,
        view:new Ext.grid.GridView({
				enableRowBody:true
		}),
		stripeRows: true,
        cm: wsdModel,
		loadMask: {msg:'正在加载数据，请稍侯……'},
        border:true,
        frame:false,
        bodyStyle:'overflow-y:auto;overflow-x:hidden;height:400px',
        /*anchor:'100% 100%',
	    margins: '0 0 0 0',
		cmargins: '0 0 0 0',        
		iconCls:'icon-grid',*/
        title:'',
        listeners:{
        	rowclick:function(g, rowIndex, e){ 
        		selectedSelRes = g.store.getAt(rowIndex);
        		 dindex=rowIndex;
        		 month=selectedSelRes.data.wssj;
        		 if(zdy==1){
        			 ksd=Ext.getCmp("qsws").getValue();
        			 jsd=Ext.getCmp("jsws").getValue();
        		 }else{
        			 ksd=selectedSelRes.data.ksd;
             		 jsd=selectedSelRes.data.jsd;
                     if(save_type==2){
                    	 ksd=parseInt(ksd)+1;
                     }
                    
        		 }
        		
        		var wsStore=wsGrid.store;
        		wsStore.baseParams = {
        				dwmc:dwmc,
        				dwdm:dwdm,
        				sex:save_type,
	        			csrq:month,
	        			start:0,
	        			limit:50
	        		};
        		wsStore.load();
        		wsStore.on("load",function(store) {
        			    storeval=store;
	        			if(store.data.length > 0 && flag=="wslist"){
	        				Ext.getCmp("dytj").setDisabled(true);
	        				Ext.getCmp("dylist").setDisabled(false);
	        			}else if(store.data.length > 0 && flag=="wstj"){
	        				Ext.getCmp("dytj").setDisabled(false);
	        				Ext.getCmp("dylist").setDisabled(false);
	        			}else{
	        				Ext.getCmp("dytj").setDisabled(true);
	        				Ext.getCmp("dylist").setDisabled(true);
	        			}
	        				curIndex = 0;
	        				dataList=store.data;
	        				if(save_type==1){
	        					if(parseInt(ksd)%2==0){
	                        		ksd=parseInt(ksd)+1;        
	                        	}
	        				}
	        				
	        				if(flag=="wstj"){
	        					tabs.setActiveTab(1);
	                			var cell=document.getElementById("Cell");	                			
	                			cell.ResetContent();
	                			
	                			var index=cell.GetCurSheet();
	                			//cell.SetSheetGridLineColor(index, black);
	                			cell.MergeCells(3,1,5,1);
	                        	cell.SetCellString(3,1,index,"居民身份证编码");
	                        	cell.SetRowHidden(0,1);
	                        	cell.SetColHidden(0,0);	                    
	                        	cell.SetCellString(1,2,index,"尾数");
	                        	
	        					if(tj_type==2){//按横向为日期统计	          
	                        	//if(dataList&&dataList.length>0){
	        				    cell.InsertRow(5,parseInt(jsd)/2,index);
	                        	cell.SetCols(33,index);              
	                        	for(var i=1;i<33;i++){
	                        		cell.SetCellString(i+1,2,index,i+"号");
	                        	}
	                     
	                        	for(var k=parseInt(ksd),l=3;k<=parseInt(jsd);k+=2,l++){
	                        		if(dataList&&dataList.length>0){      
	                        			var data=dataList.items;
	                        			for(var p=0;p<data.length;p++){
	                        				var csrq=data[p].data.csrq;
	                        				csrq=csrq.substring(6);	      
	                        				var sxh=data[p].data.sxh;
	                        				var xm=data[p].data.xm;	                        					                   
	                        		        if(parseInt(sxh)==k){
	                        		        	if(csrq=="01"){
	                        		        		cell.SetCellString(2,l,index,xm);
	                        		        	}
	                        		        	if(csrq=="02"){
	                        		        		cell.SetCellString(3,l,index,xm);
	                        		        	}
	                        		        	if(csrq=="03"){
	                        		        		cell.SetCellString(4,l,index,xm);
	                        		        	}
	                        		        	if(csrq=="04"){
	                        		        		cell.SetCellString(5,l,index,xm);
	                        		        	}
	                        		        	if(csrq=="05"){
	                        		        		cell.SetCellString(6,l,index,xm);
	                        		        	}
	                        		        	if(csrq=="06"){
	                        		        		cell.SetCellString(7,l,index,xm);
	                        		        	}
	                        		        	if(csrq=="07"){
	                        		        		
	                        		        		cell.SetCellString(8,l,index,xm);
	                        		        	}
	                        		        	if(csrq=="08"){
	                        		        		
	                        		        		cell.SetCellString(9,l,index,xm);
	                        		        	}
	                        		        	if(csrq=="09"){
	                        		        		cell.SetCellString(10,l,index,xm);
	                        		        	}
	                        		        	if(csrq=="10"){
	                        		        		cell.SetCellString(11,l,index,xm);
	                        		        	}
	                        		        	if(csrq=="11"){
	                        		        		cell.SetCellString(12,l,index,xm);
	                        		        	}
	                        		        	if(csrq=="12"){
	                        		        		cell.SetCellString(13,l,index,xm);
	                        		        	}
	                        		        	if(csrq=="13"){
	                        		        		cell.SetCellString(14,l,index,xm);
	                        		        	}
	                        		        	if(csrq=="14"){
	                        		        		cell.SetCellString(15,l,index,xm);
	                        		        	}
	                        		        	if(csrq=="15"){
	                        		        		cell.SetCellString(16,l,index,xm);
	                        		        	}
	                        		        	if(csrq=="16"){
	                        		        		cell.SetCellString(17,l,index,xm);
	                        		        	}
	                        		        	if(csrq=="17"){
	                        		        		cell.SetCellString(18,l,index,xm);
	                        		        	}
	                        		        	if(csrq=="18"){
	                        		        		cell.SetCellString(19,l,index,xm);
	                        		        	}
	                        		        	if(csrq=="19"){
	                        		        		cell.SetCellString(20,l,index,xm);
	                        		        	}
	                        		        	if(csrq=="20"){
	                        		        		cell.SetCellString(21,l,index,xm);
	                        		        	}
	                        		        	if(csrq=="21"){
	                        		        		cell.SetCellString(22,l,index,xm);
	                        		        	}
	                        		        	if(csrq=="22"){
	                        		        		cell.SetCellString(23,l,index,xm);
	                        		        	}
	                        		        	if(csrq=="23"){
	                        		        		cell.SetCellString(24,l,index,xm);
	                        		        	}
	                        		        	if(csrq=="24"){
	                        		        		cell.SetCellString(25,l,index,xm);
	                        		        	}
	                        		        	if(csrq=="25"){
	                        		        		cell.SetCellString(26,l,index,xm);
	                        		        	}
	                        		        	if(csrq=="26"){
	                        		        		cell.SetCellString(27,l,index,xm);
	                        		        	}
	                        		        	if(csrq=="27"){
	                        		        		cell.SetCellString(28,l,index,xm);
	                        		        	}
	                        		        	if(csrq=="28"){
	                        		        		cell.SetCellString(29,l,index,xm);
	                        		        	}
	                        		        	if(csrq=="30"){
	                        		        		cell.SetCellString(31,l,index,xm);
	                        		        	}
	                        		        	if(csrq=="31"){
	                        		        		cell.SetCellString(32,l,index,xm);
	                        		        	}
	                        		        }
	                        				
	                        			}
	                        		}
	                        		cell.D(1,l,index,k);
	                        		cell.SetCellAlign(1,l,index,1);
	                        	}
	                       
	                		}else{//按横向为尾数统计
	                			
	                			var collength=((parseInt(jsd)-parseInt(ksd)+1)/2)+2;
	                			cell.SetCols(jsd,index); 
	                        	
	                        	for(var m=1,k=parseInt(ksd);m<32;m++,k+=2){
	                        
	                        		
	                        		if(parseInt(m)>9){
	                        			cell.D(1,m+2,index,month+m);
	                        		}else{
	                        			cell.D(1,m+2,index,month+"0"+m);
	                        		}
	                        		
	                        		cell.SetCellAlign(1,m+2,index,1);
	                        	}
	                        	
	                        	for(var i=1,d=parseInt(ksd);i<jsd&&d<jsd;i++,d+=2){
	                        		if(dataList&&dataList.length>0){      
	                        			var data=dataList.items;
	                        			for(var p=0;p<data.length;p++){
	                        				var csrq=data[p].data.csrq;
	                        				csrq=csrq.substring(6);
	                        				var sxh=data[p].data.sxh;
	                        				var xm=data[p].data.xm;
	                        				if(parseInt(sxh)==d){
	                        		        if(csrq=="01"){	                        		   
	                        		        		cell.SetCellString(i+1,3,index,xm);
	                        		        	}
	                        		        if(csrq=="02"){	                        		   
                        		        		cell.SetCellString(i+1,4,index,xm);
                        		        	}
	                        		        if(csrq=="03"){	                        		   
                        		        		cell.SetCellString(i+1,5,index,xm);
                        		        	}
	                        		        if(csrq=="04"){	                        		   
                        		        		cell.SetCellString(i+1,6,index,xm);
                        		        	}
	                        		        if(csrq=="05"){	                        		   
                        		        		cell.SetCellString(i+1,7,index,xm);
                        		        	}
	                        		        if(csrq=="06"){	                        		   
                        		        		cell.SetCellString(i+1,8,index,xm);
                        		        	}
	                        		        if(csrq=="07"){	                        		   
                        		        		cell.SetCellString(i+1,9,index,xm);
                        		        	}
	                        		        if(csrq=="08"){	                        		   
                        		        		cell.SetCellString(i+1,10,index,xm);
                        		        	}
	                        		        if(csrq=="09"){	                        		   
                        		        		cell.SetCellString(i+1,11,index,xm);
                        		        	}
	                        		        if(csrq=="10"){	                        		   
                        		        		cell.SetCellString(i+1,12,index,xm);
                        		        	}
	                        		        if(csrq=="11"){	                        		   
                        		        		cell.SetCellString(i+1,13,index,xm);
                        		        	}
	                        		        if(csrq=="12"){                        		   
                        		        		cell.SetCellString(i+1,14,index,xm);
                        		        	}
	                        		        if(csrq=="13"){	                        		   
                        		        		cell.SetCellString(i+1,15,index,xm);
                        		        	}
	                        		        if(csrq=="14"){                        		   
                        		        		cell.SetCellString(i+1,16,index,xm);
                        		        	}
	                        		        if(csrq=="15"){                        		   
                        		        		cell.SetCellString(i+1,17,index,xm);
                        		        	}
	                        		        if(csrq=="16"){                        		   
                        		        		cell.SetCellString(i+1,18,index,xm);
                        		        	}
	                        		        if(csrq=="17"){	                        		   
                        		        		cell.SetCellString(i+1,19,index,xm);
                        		        	}
	                        		        if(csrq=="18"){	                        		   
                        		        		cell.SetCellString(i+1,20,index,xm);
                        		        	}
	                        		        if(csrq=="19"){	                        		   
                        		        		cell.SetCellString(i+1,21,index,xm);
                        		        	}
	                        		        if(csrq=="20"){	                        		   
                        		        		cell.SetCellString(i+1,22,index,xm);
                        		        	}
	                        		        if(csrq=="21"){	                        		   
                        		        		cell.SetCellString(i+1,23,index,xm);
                        		        	}
	                        		        if(csrq=="22"){                        		   
                        		        		cell.SetCellString(i+1,24,index,xm);
                        		        	}
	                        		        if(csrq=="23"){                         		   
                        		        		cell.SetCellString(i+1,25,index,xm);
                        		        	}
	                        		        if(csrq=="24"){ 	                        		   
                        		        		cell.SetCellString(i+1,26,index,xm);
                        		        	}
	                        		        if(csrq=="25"){ 	                        		   
                        		        		cell.SetCellString(i+1,27,index,xm);
                        		        	}
	                        		        if(csrq=="26"){                         		   
                        		        		cell.SetCellString(i+1,28,index,xm);
                        		        	}
	                        		        if(csrq=="27"){ 	                        		   
                        		        		cell.SetCellString(i+1,29,index,xm);
                        		        	}
	                        		        if(csrq=="28"){ 	                        		   
                        		        		cell.SetCellString(i+1,30,index,xm);
                        		        	}
	                        		        if(csrq=="29"){ 	                        		   
                        		        		cell.SetCellString(i+1,31,index,xm);
                        		        	}
	                        		        if(csrq=="30"){ 	                        		   
                        		        		cell.SetCellString(i+1,32,index,xm);
                        		        	}
	                        		        if(csrq=="31"){ 	                        		   
                        		        		cell.SetCellString(i+1,33,index,xm);
                        		        	}
	                        		        }
	                        				
	                        			}
	                        		}
	                        		cell.SetCellString(i+1,2,index,d);
	                        	}
	                			
	                		}
	        			}
	        				wsGrid.fireEvent("rowclick",wsGrid,dindex);
	        				wsGrid.getSelectionModel().selectRange(dindex,dindex);
	        		
	        		},wsGrid);
        	},
			rowdblclick:function(g, rowIndex, e){			
        			tabs.setActiveTab(0);
				selectedSelRes = g.store.getAt(rowIndex);
				var month=selectedSelRes.data.wssj;
        		var wsStore=wsGrid.store;
        		wsStore.baseParams = {
        				dwmc:dwmc,
        				dwdm:dwdm,
        				sex:save_type,
        				csrq:month,
	        			start:0,
	        			limit:50
	        		};
        		wsStore.load();
        		wsStore.on("load",function(store) {  
	        			if(store.data.length > 0){
	        				curIndex = 0;
	        				wsGrid.fireEvent("rowclick",wsGrid,curIndex);
	        				wsGrid.getSelectionModel().selectRange(curIndex,curIndex);
	        				
	        			}
	        		},wsGrid);
			}
		}/*,
        bbar: new Ext.PagingToolbar({
				pageSize: 20,
				store: storeMonths,
				displayInfo: true
		})*/
    });
	
	
	
	

	var srtj = new Ext.form.FormPanel({
		id:'formid',
		layout : 'form',
		border:false,
		frame:false,
		height : 300,
		fileUpload: true,
		style : 'margin:5 0 0 5',
		items : [{
		layout : 'column',
		border:false,
		 frame:false,
		bodyStyle : 'padding:200 0 0 0',
		items : [{
		height : 280,
		layout : 'form',
		 border:false,
		 frame:false,
		bodyStyle : 'padding:0 0 0 0',
		//Width : 200,
		defaults : {
		anchor : '90%'
		},
		labelWidth : 20,
		items : [{
	    	 id:'item2',
	    	 title:'',
	    	 xtype: 'fieldset',
	    	 layout : 'column',
	    	 bodyStyle : 'padding:3 0 0 0',
	    	 border:true,
	    	 hidden:false,
	    	 width:200,
	    	 height:50,
	    	 items:[{
	            	xtype:'radio',
	            	labelSeparator: '',
	            	checked:true,
	            	boxLabel: '男表',
	            	name:'sex',
	            	inputValue : "1",
	            	listeners:{
	            		'check' : function(checkbox, checked){ 
	                         if(checked){
	                        	save_type="1";
	                         }  
	                    }
		            }
	            	},{
	            	   border:false,
	            	   frame:false,
	            	   width:70
	            	},{
		            	xtype:'radio',
		            	labelSeparator: '',
		            	name:'sex',
		            	boxLabel: '女表',
		            	inputValue : "2",
		            	listeners:{
		            		'check' : function(checkbox, checked){ 
		                         if(checked){
		                        	save_type="2";
		                         }  
		                    }
			            }
		            	}]
	     },{
	    	 id:'item1',
	    	 title:'条件日期',
	    	 xtype: 'fieldset',
	    	 layout : 'form',
	    	 border:true,
	    	 hidden:false,
	    	 height:80,
	    	 items:[
	        	{
	            fieldLabel:'从',
				id:'kssj',
				anchor:'100%',
				disabled:false,
				xtype : 'datefield',
				width:200,
				format: "Y/m",
				value:sdate
			    },
			    {fieldLabel:'到',
					id:'jssj',
					anchor:'100%',
					disabled:false,
					xtype : 'datefield',
					width:200,
					format: "Y/m",
					value:edate
				    }]
	     }]
		}]
		}]
		});
	
var p1=new Ext.Panel({
	layout:'border',
	defaults: {
	    collapsible: true,
	    split: true,
	    bodyStyle: 'padding:15px'
	},
	items: [{
	    title: '',
	    region: 'south',
	    layout:'column',
	    height: 100,
	    frame:true,
	    border:false,
	    items:[{
	    	title: '',
            xtype: 'fieldset',
        	frame:false,
    	    border:true,
        	height: 70,
        	bodyStyle: 'padding:15 0 0 0 ',
			layout:'table',
	    	items: [
				{
	            	id:'c1',
	            	xtype:'checkbox',
	            	boxLabel: '自定义尾数打印',
	            	checked:false,
	            	name:'qr',
	            	inputValue : "1", 
	            	//columnWidth: 0.6,
    				listeners:{
    					'check': function(obj,checked){
    						if(checked){
    							zdy=1;
    						}else{
    							zdy=0;
    						}
    					}
    				}
	            },{
	    			width:10,
					border:false,
					frame: false
	    		},{
	    			layout:'form',
	    			border:false,
	    			bodyStyle: 'padding:12 0 0 0 ',
	    			frame:false,
	    			labelAlign :'right',
	    			items:[{
	    				xtype: 'textfield',
				    	fieldLabel: '派出所代码',
			        	id : 'pcs_dm',
			        	name: 'dm',	
			        	width:100
	    			}]
			    	
			    },{
	    			layout:'form',
	    			border:false,
	    			frame:false,
	    			bodyStyle: 'padding:12 0 0 0 ',
	    			labelAlign :'right',
	    			items:[{
	    				xtype: 'textfield',
				    	fieldLabel: '起始尾数',
			        	id : 'qsws',
			        	name: 'qsws',
			        	//value:'001',
			        	width:80
	    			}]
			    	
			    },{
	    			layout:'form',
	    			border:false,
	    			frame:false,
	    			bodyStyle: 'padding:12 0 0 0 ',
	    			labelAlign :'right',
	    			items:[{
	    				xtype: 'textfield',
				    	fieldLabel: '结束尾数',
			        	id : 'jsws',
			        	name: 'jsws',
			        	//value:'999',
			        	width:80
	    			}]
			    	
			    },{
	    			width:10,
					border:false,
					frame: false
	    		},{
					border:false,
					frame: false,
					bodyStyle:'margin:0px 0px 0px 50px',
	    		    items:[new Ext.Button({
	    		    	id:'queryId',
	        			text:'下一步',
	        			minWidth:60,
	        			handler:function(){
	        				var kssj=Ext.getCmp("kssj").value;
        					var jssj=Ext.getCmp("jssj").value;
	        				if(zdy==1){//自定义尾数打印
	        					 ksd=Ext.getCmp("qsws").getValue();
	                			 jsd=Ext.getCmp("jsws").getValue();
	        					dwdm=Ext.getCmp("pcs_dm").getValue();
	        					if(dwdm==''){
	        						Ext.Msg.alert("请设置尾数表打印的派出所代码!");
	        						Ext.getCmp('pcs_dm').focus(false, 100);
	        						return;
	        					}
	        					var store=monthsGrid.store;
		        				store.baseParams = {
		        						ksd:ksd,
		        						jsd:jsd,
		        						zdy:zdy,
		        						dwdm:dwdm,
		        						kssj:kssj,
		        						jssj:jssj,
		         						start:0,
		         						limit:20
		        					};
		        				store.load();
		        				store.on("load",function(store) {	
		        					var isok=store.data.items[0].json.flag;		       
		        					if(isok!=undefined){	      
		        						if(isok=="不存在"){		        							
			        						Ext.Msg.alert("派出所代码不存在");
			        						//Ext.getCmp('pcs_dm').setValue('');
			        						Ext.getCmp('pcs_dm').focus(false, 100);
			        						return;
			        					}
		        					}else{
			                			 var wsstore=wsGrid.store;
					        			 wsstore.removeAll();
					        			 dwmc=Gnt.ux.Dict.getDictLable('DWXXB', dwdm);
					        			 Ext.getCmp("pcsid").setValue(dwdm+dwmc);
					        				/*Gnt.submit(
					    							{
					    								dwdm:dwdm}, 
					    								"yw/wsbdy/getpcsxx", 
					    								"正在查询派出所信息，请稍后...", 
					    								function(jsonData,params){
					    									//showInfo("成功！");
					    									var data=jsonData.list;
					    									dwdm=data[0].dm;
					    									dwmc=data[0].mc;
					    									Ext.getCmp("pcsid").setValue(dwdm+dwmc);
					    								}
					    							);*/
					        			 
		        						Ext.getCmp('card').getLayout().setActiveItem(1);
		        					}
		        					
		        				});
		        				
		        				
	        				}else{
	        				if(pcsGrid.getSelectionModel().getCount() >=1){
	        					 dwdm=pcsGrid.getSelectionModel().getSelections()[0].data.dm;
	        					 dwmc=pcsGrid.getSelectionModel().getSelections()[0].data.mc;
	        					 var len=pcsGrid.getSelectionModel().getCount();
	        					 var selist=new Array();
	        					 for(var i=0;i<len;i++){
	        						 selist[i] = new Array();
	        						var dm=pcsGrid.getSelectionModel().getSelections()[i].data.dm;
	        						 var mc=pcsGrid.getSelectionModel().getSelections()[i].data.mc;
	        						 selist[i][0]=dm;
	        						 selist[i][1]=dm+mc;
	        					 }
	        					 	        				 
	        					var store=monthsGrid.store;
		        				store.baseParams = {
		        						zdy:zdy,
		        						dwdm:dwdm,
		        						kssj:kssj,
		        						jssj:jssj,
		         						start:0,
		         						limit:20
		        					};
		        				store.load();
		        				var wsstore=wsGrid.store;
		        				wsstore.removeAll();
		        				Ext.getCmp("pcsid").store.loadData(selist);
		        				Ext.getCmp("pcsid").setValue(selist[0][1]);
		        				Ext.getCmp('card').getLayout().setActiveItem(1);
	        					
	        				}else{
	        					showInfo("请首先选择要查看尾数的派出所");
	        					return;
	        				}
	        			}
	        				
	        				//Ext.getCmp('card').getLayout().setActiveItem(1);
	        			}
	        		})]
	    		},{
	    			width:10,
					border:false,
					frame: false
	    		},{
					border:false,
					frame: false,
	    		    items:[new Ext.Button({
	        			text:'关闭',
	        			minWidth:60,
	        			handler:function(){
	        				window.parent.closeWorkSpace();
	        			}
	        		})]
	    		}
	    		 
	    	]
			
		}]
	},{
	    title: '',
	    region:'east',
	    margins: '5 0 0 0',
	    cmargins: '5 0 0 0',
	    border:true,
	    frame:false,
	    width: 250,
	    layout : 'fit',
	   items:[srtj]
	},{
	    title: '',
	    collapsible: false,
	    border:false,
	    frame:false,
	    layout:'border',
	    region:'center',
	    margins: '5 0 0 0',
	    items:[{
			id: "panelHtmlId" ,
			html: '<div class="text" style="text-align:center;">尾数表打印</div>',
			height: 60,
			region:'north',
			border:false
		},pcsGrid
	]
	}]
});



var tabs = new Ext.TabPanel({ 
	id:'parentTabs',
	region:'center',
    activeTab: 0,
    defaults:{	
    	autoScroll: false  	
    }, 
    items: [{   
        title: '尾数表列表',
        region: 'center',
        layout:'border',
        collapsible: false,
        id:'wslist',
	      border:false,
          frame:false,
          height:400,
          anchor:'100% 100%',
    	  margins: '0 0 0 0',
          cmargins: '0 0 0 0',
		  items:[wsGrid]							
    }, {   
        title: '尾数表统计',
        id:'wstj',           
	      border:false,
          frame:false,
           width:300,
           height:390,
          anchor:'100% 100%',
    	  margins: '0 0 0 0',
          cmargins: '0 0 0 0',
          //codebase=\""+basePath+"js/lodop/cellweb5.cab#version=5,2,5,804\" 
		  html:"<OBJECT id=\"Cell\"  WIDTH=100% HEIGHT=100%    classid=\"clsid:3F166327-8030-4881-8BD2-EA25350E574A\"/>"							      
   }],
 listeners:{
    'tabchange':function (t, n) {
    	 flag=n.getId();
        if(flag=="wstj"){
        	monthsGrid.fireEvent("rowclick",monthsGrid,dindex);
        	monthsGrid.getSelectionModel().selectRange(dindex,dindex);
        }
        if(flag=="wslist" && storeval!=null){
        	monthsGrid.fireEvent("rowclick",monthsGrid,dindex);
        	monthsGrid.getSelectionModel().selectRange(dindex,dindex);
        }
    }
 }
});

var wsbbdy_window=new Gnt.ux.wsbbdywin({
	callback: function(){
	
	}
});


var p2=new Ext.Panel({
	layout:'border',
	defaults: {
	    collapsible: true,
	    split: true,
	    bodyStyle: 'padding:0px'
	},
	items: [{
	    title: '',
	    region: 'south',
	    layout:'table',
	    height: 80,
	    //cmargins: '0 0 0 0',
	    border:false,
	    frame:false,
	    bodyStyle:'margin:0px 0px 0px 20px',
	    //anchor:'100% 100%',
	    items:[{
	    	title: '统计格式',
            xtype: 'fieldset',
        	frame:false,
    	    border:true,
    	    width:200,
    	    height:50,
			layout:'column',
			items:[{
            	xtype:'radio',
            	labelSeparator: '',
            	checked:false,
            	boxLabel: '按横向为尾数段统计',
            	name:'tjtype',
            	inputValue : "1",
            	listeners:{
            		'check' : function(checkbox, checked){ 
                         if(checked){
                        	tj_type="1";           
                        	            		                                   
                        	monthsGrid.fireEvent("rowclick",monthsGrid,dindex);
                        	monthsGrid.getSelectionModel().selectRange(dindex,dindex);
                        	
                         }  
                    }
	            }
            	},{
	            	xtype:'radio',
	            	labelSeparator: '',
	            	name:'tjtype',
	            	checked:true,
	            	boxLabel: '按横向为日期统计',
	            	inputValue : "2",
	            	listeners:{
	            		'check' : function(checkbox, checked){ 
	                         if(checked){
	                        	tj_type="2";
	                        	monthsGrid.fireEvent("rowclick",monthsGrid,dindex);
	                        	monthsGrid.getSelectionModel().selectRange(dindex,dindex);
	                        	
	                         }  
	                    }
		            }
	            	}]
			},{
				title: '派出所',
	            xtype: 'fieldset',
	            layout:'form',
				border:false,
				frame:false,
				bodyStyle:'padding:15 15 0 0',
				height:50,
				width:300,
				labelAlign :'left',
				//columnWidth:1,
				//margin:'10px',
				items:[{
						xtype:'dict_combox',
						dict:'VisionType=_BLANK',
			        	id : 'pcsid',		        			             
						anchor:'100%',	
						store : selstore,
						fieldLabel:'',
						labelSeparator: '',
						listeners:{
							select:function(combo, res, eOpts ){
								//alert(res.data.code);
								var kssj=Ext.getCmp("kssj").value;
	        					var jssj=Ext.getCmp("jssj").value;
	        					dwdm=res.data.code;
	        					if(dwdm==''){
	        						res.data.code=Ext.getCmp("pcs_dm").getValue();
	        						
	        					}
	        					dwmc=res.data.name.substring(9);
	        					var store=monthsGrid.store;
		        				store.baseParams = {
		        						zdy:zdy,
		        						dwdm:res.data.code,
		        						kssj:kssj,
		        						jssj:jssj,
		         						start:0,
		         						limit:20
		        					};
		        				store.load();
		        				
							}
						}
					
				}]		
			}
			,{
				border:false,
				frame:false,
				buttons:[
new Ext.Button({
	id:'dylist',
	text:'打印列表',
	minWidth:60,
	handler:function(){
		var data=wsGrid.store.data.items;
		wsbbdy_window.show();
		wsbbdy_window.setSelRes(data,0);
		tabs.setActiveTab(0);
	}
}),new Ext.Button({
	id:'dytj',
	text:'打印统计',
	minWidth:60,
	handler:function(){
		
		var celltj=document.getElementById("Cell");
		var data=celltj.SaveToBase64Str();
		wsbbdy_window.show();
		wsbbdy_window.setSelRes(data,1);
		tabs.setActiveTab(0);	
	}
}),new Ext.Button({
				id:'return',
				text:'返回',
				minWidth:60,
				handler:function(){
					Ext.getCmp('card').getLayout().setActiveItem(0);
				}
			})
	    ]}]
	  
	},{
		xtype: 'fieldset',
	    title: '尾数段',
	    border:true,
	    frame:true,
	    region:'west',
	    margins: '0 0 0 0',
	    cmargins: '0 0 0 0',
	    width: 140,
	    bodyStyle:'overflow-y:auto;overflow-x:hidden',
	    items:[monthsGrid]
	},{
		xtype: 'fieldset',
	    title: '居民身份证编码尾数表',
	    bodyStyle:'padding:0 0 0 0',
	    collapsible: false,
	    border:true,
	    frame:false,
	    region:'center',
	    //margins: '0 0 0 0',
	   // cmargins: '0 0 0 0',
	    items:[{
	    	title:'',
	    	region:'center',
	    	//anchor:'100% 100%',
	    	height:400,
	    	border:false,
	    	frame:false,
	    	bodyStyle:'overflow-y:auto;overflow-x:hidden',
	    	items:[tabs]
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
	        	items:[p1,p2]
	        }
	    });
	 Gnt.initCellWeb('wstj','Cell');	
	 
	
	 pcsStore.load({params:{start:0, limit:50}});
	   	
		
		
	
});