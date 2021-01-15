var mbcontent=null;
var bbscData=null;
var name=null;
var pname=null;
var day=null;
var date = new Date();
var startDate=null;
var endDate=null;
var mc=user.dwmc;
var save_type=1;//保存方式
var firstDay = Ext.util.Format.date(new Date(date.getFullYear(), date.getMonth(), 1),'Ymd');
var lastDay = Ext.util.Format.date(new Date(date.getFullYear(), date.getMonth() + 1, 0),'Ymd');
Ext.onReady(function(){
	 
	
	var p1 = new Ext.form.FormPanel({
		id:'formid',
		layout : 'form',
		height : 500,
		fileUpload: true,
		style : 'margin:5 0 0 5',
		items : [{
		layout : 'column',
		bodyStyle : 'padding:20 0 0 0',
		items : [{
		height : 300,
		title:'条件输入',
		layout : 'form',
		bodyStyle : 'padding:0 0 0 40',
		xtype: 'fieldset',
		columnWidth : 0.4,
		defaults : {
		anchor : '90%'
		},
		labelWidth : 80,
		items : [{
			        id:'item1',
			        layout : 'form',
			        border:false,
			        hidden:false,
			        items:[ {
						anchor:'80%',
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
			        	},
			        	{fieldLabel:'日期',
						id:'sj',
						anchor:'80%',
						disabled:false,
						xtype : 'datefield',
						width:200,
						format: "Ymd"
					    },
					    {
					    	border:false,
					        frame:false,
					        bodyStyle : 'padding:20 0 0 0'
					    },
					    {
					    	fieldLabel:'填报人',
							id:'tbr',
							anchor:'80%',
							disabled:false,
							xtype : 'textfield',
							width:200	
					    },
					    {
					    	xtype: 'textfield',
				        	fieldLabel: '输出到文件',
				        	id : 'path1',
				        	name: 'bcpath',
				        	value:'d:/',
							width:200	
					    },
					    {
			        		id:'radio1',
			            	xtype:'radio',
			            	labelSeparator: '',
			            	checked:true,
			            	boxLabel: 'excel文件',
			            	name:'dy',
			            	inputValue : "1",
			            	listeners:{
			            		'check' : function(checkbox, checked){ 
			                         if(checked){
			                        	save_type="1";
			                         }  
			                    }
				            }
			            	},
				            {
				        		id:'radio2',
				            	xtype:'radio',
				            	labelSeparator: '',
				            	boxLabel: 'pdf文件',
				            	name:'dy',
				            	inputValue : "2",
				            	listeners:{
				            		'check' : function(checkbox, checked){ 
				                         if(checked){
				                        	save_type="2";
				                         }  
				                    }
					            }
				            	},
					            {
					        		id:'radio3',
					            	xtype:'radio',
					            	labelSeparator: '',
					            	boxLabel: 'cvs文件',
					            	name:'dy',
					            	inputValue : "3",
					            	listeners:{
					            		'check' : function(checkbox, checked){ 
					                         if(checked){
					                        	save_type="3";
					                         }  
					                    }
						            }
					            	},
						            {
						        		id:'radio4',
						            	xtype:'radio',
						            	labelSeparator: '',
						            	boxLabel: 'cell文件',
						            	name:'dy',
						            	inputValue : "4",
						            	listeners:{
						            		'check' : function(checkbox, checked){ 
						                         if(checked){
						                        	save_type="4";
						                         }  
						                    }
							            }
						            	}]
			     },{
			    	 id:'item2',
			    	 layout : 'form',
			    	 border:false,
			    	 hidden:true,
			    	 items:[ {
			 			anchor:'80%',
						xtype:'search_combox',
						searchUrl:'dict/utils/searchPcs.json',
						fields:["mc","dm","qhdm","pcsList"],
						valueField: "dm",
						displayField: "mc",
						hiddenName:'jlx',
						id:'pcs2',
						name:'pcsmc',
						maxLength:30,
						allowBlank:true,
						fieldLabel:'派出所'
			        	},
			        	{fieldLabel:'开始日期',
						id:'kssj',
						anchor:'80%',
						disabled:false,
						xtype : 'datefield',
						width:200,
						format: "Ymd"
					    },
					    {fieldLabel:'结束日期',
							id:'jssj',
							anchor:'80%',
							disabled:false,
							xtype : 'datefield',
							width:200,
							format: "Ymd"
						    },
					    {
					    	border:false,
					        frame:false,
					        bodyStyle : 'padding:20 0 0 0'
					    },
					    {
					    	fieldLabel:'填报人',
							id:'tbr2',
							anchor:'80%',
							disabled:false,
							xtype : 'textfield',
							width:200	
					    },
					    {
					    	xtype: 'textfield',
				        	fieldLabel: '输出到文件',
				        	id : 'path2',
				        	name: 'bcpath',
				        	value:'d:/',
							width:200	
					    },
					    {
			        		//id:'radio1',
			            	xtype:'radio',
			            	labelSeparator: '',
			            	checked:true,
			            	boxLabel: 'excel文件',
			            	name:'dy',
			            	inputValue : "1",
			            	listeners:{
			            		'check' : function(checkbox, checked){ 
			                         if(checked){
			                        	save_type="1";
			                         }  
			                    }
				            }
			            	},
				            {
				        		//id:'radio2',
				            	xtype:'radio',
				            	labelSeparator: '',
				            	boxLabel: 'pdf文件',
				            	name:'dy',
				            	inputValue : "2",
				            	listeners:{
				            		'check' : function(checkbox, checked){ 
				                         if(checked){
				                        	save_type="2";
				                         }  
				                    }
					            }
				            	},
					            {
					        		//id:'radio3',
					            	xtype:'radio',
					            	labelSeparator: '',
					            	boxLabel: 'cvs文件',
					            	name:'dy',
					            	inputValue : "3",
					            	listeners:{
					            		'check' : function(checkbox, checked){ 
					                         if(checked){
					                        	save_type="3";
					                         }  
					                    }
						            }
					            	},
						            {
						        		//id:'radio4',
						            	xtype:'radio',
						            	labelSeparator: '',
						            	boxLabel: 'cell文件',
						            	name:'dy',
						            	inputValue : "4",
						            	listeners:{
						            		'check' : function(checkbox, checked){ 
						                         if(checked){
						                        	save_type="4";
						                         }  
						                    }
							            }
						            	}]
			     },{
			    	 id:'item3',
			    	 layout : 'form',
			    	 border:false,
			    	 hidden:true,
			    	 items:[ {
			 			anchor:'80%',
						xtype:'search_combox',
						searchUrl:'dict/utils/searchPcs.json',
						fields:["mc","dm","qhdm","pcsList"],
						valueField: "dm",
						displayField: "mc",
						hiddenName:'jlx',
						id:'pcs3',
						name:'pcsmc',
						maxLength:30,
						allowBlank:true,
						fieldLabel:'派出所'
			        	},
			        	{fieldLabel:'年份',
						id:'yearid',
						anchor:'80%',
						disabled:false,
						xtype : 'textfield',
						width:200
					    },
					    {fieldLabel:'月份',
							id:'monthid',
							anchor:'80%',
							disabled:false,
							xtype : 'textfield',
							width:200
						    },
					    {
					    	border:false,
					        frame:false,
					        bodyStyle : 'padding:20 0 0 0'
					    },
					    {
					    	fieldLabel:'填报人',
							id:'tbr3',
							anchor:'80%',
							disabled:false,
							xtype : 'textfield',
							width:200	
					    },
					    {
					    	xtype: 'textfield',
				        	fieldLabel: '输出到文件',
				        	id : 'path3',
				        	name: 'bcpath',
				        	value:'d:/',
							width:200	
					    },
					    {
			        		//id:'radio1',
			            	xtype:'radio',
			            	labelSeparator: '',
			            	checked:true,
			            	boxLabel: 'excel文件',
			            	name:'dy',
			            	inputValue : "1",
			            	listeners:{
			            		'check' : function(checkbox, checked){ 
			                         if(checked){
			                        	save_type="1";
			                         }  
			                    }
				            }
			            	},
				            {
				        		//id:'radio2',
				            	xtype:'radio',
				            	labelSeparator: '',
				            	boxLabel: 'pdf文件',
				            	name:'dy',
				            	inputValue : "2",
				            	listeners:{
				            		'check' : function(checkbox, checked){ 
				                         if(checked){
				                        	save_type="2";
				                         }  
				                    }
					            }
				            	},
					            {
					        		//id:'radio3',
					            	xtype:'radio',
					            	labelSeparator: '',
					            	boxLabel: 'cvs文件',
					            	name:'dy',
					            	inputValue : "3",
					            	listeners:{
					            		'check' : function(checkbox, checked){ 
					                         if(checked){
					                        	save_type="3";
					                         }  
					                    }
						            }
					            	},
						            {
						        		//id:'radio4',
						            	xtype:'radio',
						            	labelSeparator: '',
						            	boxLabel: 'cell文件',
						            	name:'dy',
						            	inputValue : "4",
						            	listeners:{
						            		'check' : function(checkbox, checked){ 
						                         if(checked){
						                        	save_type="4";
						                         }  
						                    }
							            }
						            	}]
			     },{
			    	 id:'item4',
			    	 layout : 'form',
			    	 border:false,
			    	 hidden:true,
			    	 items:[ {
			 			anchor:'80%',
						xtype:'search_combox',
						searchUrl:'dict/utils/searchPcs.json',
						fields:["mc","dm","qhdm","pcsList"],
						valueField: "dm",
						displayField: "mc",
						hiddenName:'jlx',
						id:'pcs4',
						name:'pcsmc',
						maxLength:30,
						allowBlank:true,
						fieldLabel:'派出所'
			        	},			       					   
					    {
					    	border:false,
					        frame:false,
					        bodyStyle : 'padding:20 0 0 0'
					    },
					    {
					    	fieldLabel:'填报人',
							id:'tbr4',
							anchor:'80%',
							disabled:false,
							xtype : 'textfield',
							width:200	
					    },
					    {
					    	xtype: 'textfield',
				        	fieldLabel: '输出到文件',
				        	id : 'path4',
				        	name: 'bcpath',
				        	value:'d:/',
							width:200	
					    },
					    {
			        		//id:'radio1',
			            	xtype:'radio',
			            	labelSeparator: '',
			            	checked:true,
			            	boxLabel: 'excel文件',
			            	name:'dy',
			            	inputValue : "1",
			            	listeners:{
			            		'check' : function(checkbox, checked){ 
			                         if(checked){
			                        	save_type="1";
			                         }  
			                    }
				            }
			            	},
				            {
				        		//id:'radio2',
				            	xtype:'radio',
				            	labelSeparator: '',
				            	boxLabel: 'pdf文件',
				            	name:'dy',
				            	inputValue : "2",
				            	listeners:{
				            		'check' : function(checkbox, checked){ 
				                         if(checked){
				                        	save_type="2";
				                         }  
				                    }
					            }
				            	},
					            {
					        		//id:'radio3',
					            	xtype:'radio',
					            	labelSeparator: '',
					            	boxLabel: 'cvs文件',
					            	name:'dy',
					            	inputValue : "3",
					            	listeners:{
					            		'check' : function(checkbox, checked){ 
					                         if(checked){
					                        	save_type="3";
					                         }  
					                    }
						            }
					            	},
						            {
						        		//id:'radio4',
						            	xtype:'radio',
						            	labelSeparator: '',
						            	boxLabel: 'cell文件',
						            	name:'dy',
						            	inputValue : "4",
						            	listeners:{
						            		'check' : function(checkbox, checked){ 
						                         if(checked){
						                        	save_type="4";
						                         }  
						                    }
							            }
						            	}]
			     }
		        
		    
			],
			buttonAlign:'center',
			buttons:[
			new Ext.Button({
	              id:'startsave',
	               text:'开始',
	              minWidth:60,
	            handler:function(){ 
		var cellout=document.getElementById("Cellout");
		var cellin=document.getElementById("Cellin");
		var tabs=Ext.getCmp("parentTab");
		var year=null;
		var month=null;
		var pcs_dm=null;
		var pl_tjsj=null;
		var tbr=null;
		var save_path=null;//保存路径
		var procName=null;
		if(pname==null){
			Ext.Msg.alert("请先选择报表!");
			return;
		}
		if(pname=="百岁年龄"||pname=="人口底数"){
			 pcs_dm=Ext.getCmp("pcs").value;
			 pl_tjsj=Ext.getCmp("sj").value;
			 tbr=Ext.getCmp("tbr").getValue();
			 save_path=Ext.getCmp("path1").getValue();
			var pcs_mc= Ext.getCmp("formid").getForm().items.map.pcs.lastSelectionText;
			//alert(pcs_mc);
			 if(pl_tjsj==null){
				 Ext.Msg.alert("日期不能为空!");
				 return;
			 }
			 if(save_path==''){
				 Ext.Msg.alert("保存路径不能为空!");
				 return;
			 }
			if(pcs_dm==null){
				Ext.Msg.alert("派出所不能为空");
				return;
			}
			if(pname=="人口底数"){
				procName="{call RPT.P_RPT_HJRKDSTJ(2,"+pcs_dm+","+startDate+",?)}";
			}else{
			    procName="{call RPT.P_RPT_HJBSNLTJ(2,"+pcs_dm+","+pl_tjsj+",?)}";
			}
			
			bbdysumit(procName,mbcontent,tbr,pl_tjsj,pcs_mc,startDate,endDate,tabs,year,month,save_type,save_path);
		}else if(pname=="常住人口派出所四变月报表"){
			 pcs_dm=Ext.getCmp("pcs3").value;
			 year=Ext.getCmp("yearid").getValue();
			 month=Ext.getCmp("monthid").getValue();
			 tbr=Ext.getCmp("tbr3").getValue();
			 save_path=Ext.getCmp("path3").getValue();
			var pcs_mc= Ext.getCmp("formid").getForm().items.map.pcs3.lastSelectionText;
			 if(year==null){
				 Ext.Msg.alert("年份不能为空!");
				 return;
			 }
			 if(month==null){
				 Ext.Msg.alert("月份不能为空!");
				 return;
			 }
			 if(save_path==''){
				 Ext.Msg.alert("保存路径不能为空!");
				 return;
			 }
			if(pcs_dm==null){
				Ext.Msg.alert("派出所不能为空");
				return;
			}
			
			 procName="{call RPT.P_RPT_HJCZSBYBB("+year+","+month+","+pcs_dm+",?)}";
			bbdysumit(procName,mbcontent,tbr,pl_tjsj,pcs_mc,startDate,endDate,tabs,year,month,save_type,save_path);
		}else if(pname=="办证人数统计"){
			 pcs_dm=Ext.getCmp("pcs4").value;
			 tbr=Ext.getCmp("tbr4").getValue();
			 save_path=Ext.getCmp("path4").getValue();
			var pcs_mc= Ext.getCmp("formid").getForm().items.map.pcs4.lastSelectionText;
			 if(save_path==''){
				 Ext.Msg.alert("保存路径不能为空!");
				 return;
			 }
			if(pcs_dm==null){
				Ext.Msg.alert("派出所不能为空");
				return;
			}
			
			 procName="{call RPT.P_RPT_BZRSTJ(2,"+pcs_dm+",?)}";
			bbdysumit(procName,mbcontent,tbr,pl_tjsj,pcs_mc,startDate,endDate,tabs,year,month,save_type,save_path);
		}else{
			pcs_dm=Ext.getCmp("pcs2").value;
			 tbr=Ext.getCmp("tbr2").getValue();
			 startDate=Ext.getCmp("kssj").value;
			 endDate=Ext.getCmp("jssj").value;
			 save_path=Ext.getCmp("path2").getValue();
			var pcs_mc= Ext.getCmp("formid").getForm().items.map.pcs2.lastSelectionText;
			if(save_path==''){
				 Ext.Msg.alert("保存路径不能为空!");
				 return;
			 }
			 if(startDate==null){
					Ext.Msg.alert("开始日期不能为空");
					return;
				}
			 if(endDate==null){
					Ext.Msg.alert("结束日期不能为空");
					return;
				}
			if(pcs_dm==null){
				Ext.Msg.alert("派出所不能为空");
				return;
			}

			if(pname=="城镇人口增减情况统计年报表(58表)"){
				 procName="{call RPT.P_RPT_HJFNYRKZJQKTJ(2,"+startDate+","+endDate+","+pcs_dm+",?)}";
			}
			if(pname=="户籍业务变动信息统计表"){
				procName="{call RPT.P_RPT_HJBDYWXXTJ(2,"+startDate+","+endDate+","+pcs_dm+",?)}";
			}
			if(pname=="人口变动情况"){
				var proc1="{call RPT.P_RPT_HJRKBDQKTJ1(2,"+pcs_dm+","+startDate+","+endDate+",?)}";
				var proc2="{call RPT.P_RPT_HJRKBDQKTJ2(2,"+pcs_dm+","+startDate+","+endDate+",?)}";
				var proc3="{call RPT.P_RPT_HJRKBDQKTJ3(2,"+pcs_dm+","+startDate+","+endDate+",?)}";			 
				procName=proc1+"&"+proc2+"&"+proc3;
			}
			if(pname=="人口及其变动情况统计报表20栏(57表)"){
				procName="{call RPT.P_RPT_HJRKJQBDQKTJ(2,"+startDate+","+endDate+","+pcs_dm+",?)}";
			}
			if(pname=="人口及其变动情况统计报表20栏(原20栏)"){
				procName="{call RPT.P_RPT_HJRKJQBDQKTJ20(2,"+startDate+","+endDate+","+pcs_dm+",?)}";
			}
			if(pname=="省内迁移情况统计报表"){
				procName="{call RPT.P_RPT_HJSNQYQKTJ(2,"+pcs_dm+","+startDate+","+endDate+",?)}";
			}
			bbdysumit(procName,mbcontent,tbr,pl_tjsj,pcs_mc,startDate,endDate,tabs,year,month,save_type,save_path);
		}
		
		
		
		
	}
})   
			  ]
			
		}]
		}]
		});
	
			
	
	
		
	
	var tabs = new Ext.TabPanel({ 
		id:'parentTab',
		region:'center',
		deferredRender:false,
        activeTab: 0,
        defaults:{	
        	autoScroll: false  	
        }, 
        items: [{   
            title: '报表条件',
            id:'bbtj',
		      border:false,
	          frame:false,
	          width:300,
	          anchor:'100% 100%',
	    	  margins: '0 0 0 0',
	          cmargins: '0 0 0 0',
	          //codebase=\""+basePath+"js/lodop/cellweb5.cab#version=5,2,5,804\"
			  html:"<OBJECT id=\"Cellin\"  WIDTH=100% HEIGHT=100%    classid=\"clsid:3F166327-8030-4881-8BD2-EA25350E574A\" />"							
        }, {   
            title: '报表输出',
            id:'bbsc',           
		      border:false,
	          frame:false,
	          width:300,
	          anchor:'100% 100%',
	    	  margins: '0 0 0 0',
	          cmargins: '0 0 0 0',
			  html:"<OBJECT id=\"Cellout\"  WIDTH=100% HEIGHT=100%    classid=\"clsid:3F166327-8030-4881-8BD2-EA25350E574A\" />"
       },{
    	   title: '批量统计',
           id:'pltj',
           border:false,
	       frame:false,
	       items:[p1]
	      
       }
         ],
     listeners:{
        'tabchange':function (t, n) {  	
           // alert(n.getId());
            if(n.getId()=="bbsc"){ 
            	var cellout=document.getElementById("Cellout");	
            	if(mbcontent!=null){
            		cellout.ReadFromBase64Str(mbcontent);
    			    cellout.DeleteSheet(0,1);
    			    cellout.DeleteSheet(1,1);
            	}
            	
    			    //this.hideTabStripItem(2);
    			    
            }
            
        }
     }
    });
	
	
	   var tree = new Ext.tree.TreePanel({
	   	 	autoScroll:true,
	   	 	root:root,
	   	 	//rootVisible:false,
	    	width:200,
	    	height:400,
	   	 	listeners:{
	   	 		//点击叶子节点时，利用新的分页容器构造iframe打开文件
	   	 		click:function(node,e){
	   	 			//如果是最终节点，那么打开选择的文件
	   	 			if(node.leaf){
	   	 				//获取报表模板内容
	   	 				Ext.getCmp("savebb").setDisabled(false);
	   	 				var ywbbmb = node.attributes.data.url;
	   	 				//获取报表id
	   	 				var ywbbid = node.attributes.data.code;
	   	 				 name = node.text;
	   	 				 pname=node.parentNode.text;
	   	 				 changitem(pname);
	   	 			    tabs.setActiveTab(0);
	   	 			    if(name.indexOf("派出所")==-1){
	   	 			      tabs.hideTabStripItem(2);
	   	 			    }else{
	   	 			      tabs.unhideTabStripItem(2);
	   	 			     
	   	 			    }
	   	 			    var cellin=document.getElementById("Cellin");
	   	 		  	    cellin.Login("浙江金铖华元信息技术有限公司自定义报表-WEB版","","13040351","1340-1275-0110-0004");

	   	 			   // var cellout=document.getElementById("Cellout");
	   	 			   // cellout.Login("浙江金铖华元信息技术有限公司自定义报表-WEB版","","13040351","1340-1275-0110-0004");

	   	 			    cellin.ReadFromBase64Str(ywbbmb);
	   	 			    mbcontent=ywbbmb;
	   	 			    
	   	 			    var tj_index=cellin.GetSheetIndex("条件区");
	   	 			    var tjStr=cellin.GetCellString(1,1,tj_index);
	   	 			    var tjy_index=cellin.GetSheetIndex("条件页");
	   	 			    var tjyStr=cellin.GetCellString(1,1,tjy_index);
	   	 			    var tjparam=null;
	   	 			    var objstrs="";
	   	 			    if(tjStr=="分局"||tjStr=="派出所"||tjyStr=="分局"||tjyStr=="派出所"){
	   	 			    	if(tjStr=="分局"||tjyStr=="分局"){
	   	 			    	  tjparam="ssxq";
	   	 			    	}
	   	 			        if(tjStr=="派出所"||tjyStr=="派出所"){
	   	 			    	  tjparam="pcs";
	   	 			    	}
	   	 			        
	   	 			        var index=cellin.GetSheetIndex("定义区");
	   	 			        if(index==-1){
	   	 			           index=cellin.GetSheetIndex("定义页");
	   	 			        }
	   	 			     
	   	 			      
	   	 			      var sqlStr=cellin.GetCellString(1,3,index);//下拉框条件语句
	   	 				Gnt.submit(
	   	 					{sqlstr:sqlStr,tjtype:tjparam,ywbbmc:name}, 
	   	 					"yw/ywbbdy/querypcs",
	   	 					"正在加载，请稍后...", 
	   	 					function(jsonData,params){
	   	 						//showInfo("加载成功!");
	   	 					if(jsonData.list && jsonData.list.length>0){
	   	 						var list=jsonData.list;
	   	 						for(var i=0;i<list.length;i++){
	   	 							var data=list[i];
	   	 						    objstrs +=data[0]+"-"+data[1]+"\n";
	   	 						 cellin.SetDroplistCell(2,1,0,objstrs,2);
	   	 						}
	   	 					 }
	   	 					}
	   	 			    );
	   	 			  
	   	 			      //传入sqlstr获取后台返回结果，将结果集拼成如下：
	   	 			      //var objstr="11\n22\n33\n44\n55\n66";//下拉结果集
   	 			          //cellin.SetDroplistCell(2,1,0,objstrs,2);//设置单元格下拉并绑定数据集
	   	 			    }
	   	 			      	 			      	 			    
	   	 			    //cellin.DeleteSheet(1,2);//删除第二第三sheet
	   	 			   cellin.SetSheetVisible (1,false);
	   	 			   cellin.SetSheetVisible (2,false);//隐藏第三sheet
	   	 			   //cellin.SetCurSheet(0);指定当前的表页,根据页签下标
	   	 			   var isok=cellin.SetCurSheetEx("条件区");//指定当前的表页,根据页签名
                       if(isok=="undefined"){
                    	   cellin.SetCurSheetEx("条件页");
                       }
	   	 			   
	   	 			   var date=new Date();
					   date=Ext.util.Format.date(date,'Ymd');
	   	 			   if(name=="派出所百岁年龄统计报表"||name=="区县百岁年龄统计报表"||name=="区县人口底数统计报表"||name=="派出所人口底数统计报表"){
	   	 				  cellin.SetCellDouble(2,2,tj_index,date);
	   	 			   }
	   	 			   if(name=="地区百岁年龄统计报表"||name=="地区人口底数统计报表"){
	   	 				  cellin.SetCellDouble(2,1,tj_index,date);
	   	 			   }
	   	 			   if(pname=="常住人口派出所四变月报表"){   	 		
	   	 				 var date=Ext.util.Format.date(new Date(),'Y-m-d');
	   	 				 var data=date.split("-");
	   	 				cellin.SetCellDouble(2,2,tj_index,data[0]);
	   	 			    cellin.SetCellDouble(2,3,tj_index,data[1]);
	   	 			    day=data[2];
	   	 			   }
	   	 			   if(pname=="城镇人口增减情况统计年报表(58表)"||pname=="户籍业务变动信息统计表"||pname=="人口变动情况"
	   	 				   ||pname=="人口及其变动情况统计报表20栏(57表)"){ 
	   	 			      if(name.indexOf("地区")!=-1){
	   	 			    	cellin.SetCellDouble(2,1,tj_index,firstDay);
	   	 			        cellin.SetCellDouble(2,2,tj_index,lastDay);
	   	 			      }else{
	   	 			    	cellin.SetCellDouble(2,2,tj_index,firstDay);
	   	 			        cellin.SetCellDouble(2,3,tj_index,lastDay);
	   	 			      }
	   	 			      
	   	 			   }
	   	 			   if(pname=="省内迁移情况统计报表"){
	   	 				if(name.indexOf("地区")!=-1){
	   	 			    	cellin.SetCellDouble(2,1,0,firstDay);
	   	 			        cellin.SetCellDouble(2,2,0,lastDay);
	   	 			      }else{
	   	 			    	cellin.SetCellDouble(2,2,0,firstDay);
	   	 			        cellin.SetCellDouble(2,3,0,lastDay);
	   	 			      }  
	   	 			   }
	   	 			   if(pname=="人口及其变动情况统计报表20栏(原20栏)"){
	   	 				 cellin.SetCellDouble(2,2,tj_index,firstDay);
   	 			         cellin.SetCellDouble(2,3,tj_index,lastDay);
	   	 			   }
	   	 			   /*	if(name=="地区百岁年龄统计报表"){
	   	 			   	 Ext.getCmp("pltj").setVisible(false);
	   	 			   	}*/			   
	   	 			}
	   	 		} 
	   	 	}
		});

	  
	 	
	   var borderPanel = new Ext.Panel({
		 layout:'border',
		 defaults: {
		     collapsible: true,
		     split: false,
		     bodyStyle: 'padding:15px'
		 },
		 items: [{
			 region:'north',
        	 height:40,
        	 margins:'0px',
           	 cmargins:'0px',
           	 bodyStyle:'padding:0px',
        	 frame:false,
        	 border:false,
        	 layout:'fit',
	        	buttons:[
new Ext.Button({
	id:'savebb',
	text:'报表生成',
	disabled:true,
	minWidth:60,
	handler:function(){
		var cellin=document.getElementById("Cellin");
		var pulic_date=Ext.util.Format.date(new Date(),'Y-m-d');
		pulic_date=pulic_date.split("-");
		var p_year=pulic_date[0];
		var p_month=pulic_date[1];
		var p_day=pulic_date[2];
		var rpttype=null;
		var procName=null;
		var year=null;
		var month=null;
		var tbr=null;
		if(name.indexOf("派出所")!=-1){
			rpttype="2";
		}
		if(name.indexOf("地区")!=-1){
			rpttype="0";
		}
		if(name.indexOf("区县")!=-1){
			rpttype="1";
		}
		if(pname=="百岁年龄"){
			var tjsj=null;
			if(rpttype=="0"){
				 tbr=cellin.GetCellString(2,2,0);
				 tjsj=cellin.GetCellDouble2(2,1,0);
				procName="{call RPT.P_RPT_HJBSNLTJ("+rpttype+",'',"+tjsj+",?)}";
			}else{
				tbr=cellin.GetCellString(2,3,0);
				var pcs=cellin.GetCellString(2,1,0);
				var pcsdm=pcs.split("-");//派出所代码
				 tjsj=cellin.GetCellDouble2(2,2,0);
				/*if(pcs==""){
					alert("派出所为必须查询条件!");
					return;
				}*/
				
				procName="{call RPT.P_RPT_HJBSNLTJ("+rpttype+","+pcsdm[0]+","+tjsj+",?)}";
			}
			if(tjsj==""){
				alert("时间为必须查询条件!");
				return;
			}
		}
		if(pname=="常住人口派出所四变月报表"){
			tbr=cellin.GetCellString(2,4,0);
			var pcs=cellin.GetCellString(2,1,0);
			var pcsdm=pcs.split("-");//派出所代码
			 year=cellin.GetCellDouble(2,2,0);
			 month=cellin.GetCellDouble(2,3,0);
			 procName="{call RPT.P_RPT_HJCZSBYBB("+year+","+month+","+pcsdm[0]+",?)}";
			 /*if(pcs==""){
					alert("派出所为必须查询条件!");
					return;
				}*/
		}
		//
		
		if(pname=="城镇人口增减情况统计年报表(58表)"){
			
			if(name.indexOf("地区")!=-1){
				tbr=cellin.GetCellString(2,3,0);
				 startDate=cellin.GetCellDouble(2,1,0);
				 endDate=cellin.GetCellDouble(2,2,0);
				 procName="{call RPT.P_RPT_HJFNYRKZJQKTJ("+rpttype+","+startDate+","+endDate+",'',?)}"; 
			}else{
				tbr=cellin.GetCellString(2,4,0);
				 startDate=cellin.GetCellDouble(2,2,0);
				 endDate=cellin.GetCellDouble(2,3,0);
				 var pcs=cellin.GetCellString(2,1,0);
				 var pcsdm=pcs.split("-");//派出所代码
				 procName="{call RPT.P_RPT_HJFNYRKZJQKTJ("+rpttype+","+startDate+","+endDate+","+pcsdm[0]+",?)}"; 
				 
			}
			
			
		}
		
		if(pname=="户籍业务变动信息统计表"){
			if(name.indexOf("地区")!=-1){
				 tbr=cellin.GetCellString(2,3,0);
				 startDate=cellin.GetCellDouble(2,1,0);
				 endDate=cellin.GetCellDouble(2,2,0);
				 procName="{call RPT.P_RPT_HJBDYWXXTJ("+rpttype+","+startDate+","+endDate+",'',?)}"; 
			}else{
				 tbr=cellin.GetCellString(2,4,0);
				 startDate=cellin.GetCellDouble(2,2,0);
				 endDate=cellin.GetCellDouble(2,3,0);
				 var pcs=cellin.GetCellString(2,1,0);
				 var pcsdm=pcs.split("-");//派出所代码
				 procName="{call RPT.P_RPT_HJBDYWXXTJ("+rpttype+","+startDate+","+endDate+","+pcsdm[0]+",?)}"; 
				 
			}
			
			
		}
		
		if(pname=="人口变动情况"){
			if(name.indexOf("地区")!=-1){
				 tbr=cellin.GetCellString(2,3,0);
				 startDate=cellin.GetCellDouble(2,1,0);
				 endDate=cellin.GetCellDouble(2,2,0);
				 var proc1="{call RPT.P_RPT_HJRKBDQKTJ1("+rpttype+",'',"+startDate+","+endDate+",?)}";
				 var proc2="{call RPT.P_RPT_HJRKBDQKTJ2("+rpttype+",'',"+startDate+","+endDate+",?)}";
				 var proc3="{call RPT.P_RPT_HJRKBDQKTJ3("+rpttype+",'',"+startDate+","+endDate+",?)}";
				 //procName="{call RPT.P_RPT_HJRKBDQKTJ1("+rpttype+",'',"+startDate+","+endDate+",?)}"; 
				 procName=proc1+"&"+proc2+"&"+proc3;
			}else{
				 tbr=cellin.GetCellString(2,4,0);
				 startDate=cellin.GetCellDouble(2,2,0);
				 endDate=cellin.GetCellDouble(2,3,0);
				 var pcs=cellin.GetCellString(2,1,0);
				 var pcsdm=pcs.split("-");//派出所代码
				 var proc1="{call RPT.P_RPT_HJRKBDQKTJ1("+rpttype+","+pcsdm[0]+","+startDate+","+endDate+",?)}";
				 var proc2="{call RPT.P_RPT_HJRKBDQKTJ2("+rpttype+","+pcsdm[0]+","+startDate+","+endDate+",?)}";
				 var proc3="{call RPT.P_RPT_HJRKBDQKTJ3("+rpttype+","+pcsdm[0]+","+startDate+","+endDate+",?)}";
				// procName="{call RPT.P_RPT_HJRKBDQKTJ1("+rpttype+","+pcsdm[0]+","+startDate+","+endDate+",?)}"; 
				 procName=proc1+"&"+proc2+"&"+proc3;
			}
			
			
		}
		
		if(pname=="人口底数"){
			if(name.indexOf("地区")!=-1){
				 tbr=cellin.GetCellString(2,2,0);
				 startDate=cellin.GetCellDouble(2,1,0);	
				 procName="{call RPT.P_RPT_HJRKDSTJ("+rpttype+",'',"+startDate+",?)}"; 
			}else{
				 tbr=cellin.GetCellString(2,3,0);
				 startDate=cellin.GetCellDouble(2,2,0);
				 var pcs=cellin.GetCellString(2,1,0);
				 var pcsdm=pcs.split("-");//派出所代码
				 procName="{call RPT.P_RPT_HJRKDSTJ("+rpttype+","+pcsdm[0]+","+startDate+",?)}"; 
				 
			}
		}
		
		if(pname=="人口及其变动情况统计报表20栏(57表)"){
			if(name.indexOf("地区")!=-1){
				 tbr=cellin.GetCellString(2,3,0);
				 startDate=cellin.GetCellDouble(2,1,0);
				 endDate=cellin.GetCellDouble(2,2,0);
				 procName="{call RPT.P_RPT_HJRKJQBDQKTJ("+rpttype+","+startDate+","+endDate+",'',?)}"; 
			}else{
				 tbr=cellin.GetCellString(2,4,0);
				 startDate=cellin.GetCellDouble(2,2,0);
				 endDate=cellin.GetCellDouble(2,3,0);
				 var pcs=cellin.GetCellString(2,1,0);
				 var pcsdm=pcs.split("-");//派出所代码
				 procName="{call RPT.P_RPT_HJRKJQBDQKTJ("+rpttype+","+startDate+","+endDate+","+pcsdm[0]+",?)}"; 
				 
			}
			
			
		}
		
		if(pname=="人口及其变动情况统计报表20栏(原20栏)"){	
			     tbr=cellin.GetCellString(2,4,0);
				 startDate=cellin.GetCellDouble(2,2,0);
				 endDate=cellin.GetCellDouble(2,3,0);
				 var pcs=cellin.GetCellString(2,1,0);
				 var pcsdm=pcs.split("-");//派出所代码
				 procName="{call RPT.P_RPT_HJRKJQBDQKTJ20("+rpttype+","+startDate+","+endDate+","+pcsdm[0]+",?)}"; 				 						
			
		}
		
		if(pname=="省内迁移情况统计报表"){
			if(name.indexOf("地区")!=-1){
				 tbr=cellin.GetCellString(2,3,0);
				 startDate=cellin.GetCellDouble(2,1,0);
				 endDate=cellin.GetCellDouble(2,2,0);
				 procName="{call RPT.P_RPT_HJSNQYQKTJ("+rpttype+",'',"+startDate+","+endDate+",?)}"; 
			}else{
				 tbr=cellin.GetCellString(2,4,0);
				 startDate=cellin.GetCellDouble(2,2,0);
				 endDate=cellin.GetCellDouble(2,3,0);
				 var pcs=cellin.GetCellString(2,1,0);
				 var pcsdm=pcs.split("-");//派出所代码
				 procName="{call RPT.P_RPT_HJSNQYQKTJ("+rpttype+","+pcsdm[0]+","+startDate+","+endDate+",?)}"; 
				 
			}
			
			
		}
		
		if(pname=="办证人数统计"){
			if(name.indexOf("地区")!=-1){
				 tbr=cellin.GetCellString(2,1,0);
				 procName="{call RPT.P_RPT_BZRSTJ("+rpttype+",'',?)}"; 
			}else{
				 tbr=cellin.GetCellString(2,2,0);
				 var pcs=cellin.GetCellString(2,1,0);
				 var pcsdm=pcs.split("-");//派出所代码
				 procName="{call RPT.P_RPT_BZRSTJ("+rpttype+","+pcsdm[0]+",?)}"; 
				 
			}
		}
		
		if(pcs==""){
			alert("派出所为必须查询条件!");
			return;
		}
		Gnt.submit(
					{procName:procName}, 
					"yw/ywbbdy/querybbsc",
					"正在加载，请稍后...", 
					function(jsonData,params){
						//showInfo("加载成功!");
					if(jsonData.list && jsonData.list.length>0){
						var list=jsonData.list;
						bbscData=list;
						 
						//cellin.SetSheetVisible (1,true);//设置隐藏页
						//cellin.SetSheetVisible (0,false);
						//cellin.SetCurSheet(1);//设置当前展示sheet页，根据页签下标
						tabs.setActiveTab(1);
						var cellout=document.getElementById("Cellout");
						cellout.ReadFromBase64Str(mbcontent);
						cellout.DeleteSheet(0,1);
	    			    cellout.DeleteSheet(1,1);
	    			    cellout.SetCurSheetEx("输出区");//指定当前的表页,根据页签名
						var index=cellout.GetSheetIndex("输出区");
						if(index==-1){
							index=cellin.GetSheetIndex("输出页");
						}
						if(pname=="百岁年龄"){
							cellout.SetCellString(4,6,index,"填表人:"+tbr);
							cellout.SetCellString(5,6,index,p_year+"年"+p_month+"月"+p_day+"日");
							if(list.length>1){
								var len=cellout.GetRows(index);								
								if(len>7){
									cellout.DeleteRow(5,len-7,index);//删除原始数据
								}
								cellout.InsertRow(5,list.length-1,index);
								
							}
							
							//cellin.SetRangeData(index,2,5,list,0);//批量填充还有问题
							for(var i=0;i<list.length;i++){
								var data=list[i];    			    		                          
								var num=data.a2;//合计
	                            var sex1=data.a3;//男
	                            var sex2=data.a4;//女
	                            var age=data.code;//年龄
	                            cellout.SetCellString(2,5+i,index,age);
	                            cellout.D(3,5+i,index,num);
	                            cellout.D(4,5+i,index,sex1);
	                            cellout.D(5,5+i,index,sex2);   			    		  		
							}
							if(pcs!=null){
								var array=pcs.split("-");
								cellout.SetCellString(3,3,index,array[1]);
							}else{
								cellout.SetCellString(3,3,index,mc);
							}	
							cellout.SetCellString(2,2,index,"时间段 "+tjsj);
							
						}
						
						if(pname=="常住人口派出所四变月报表"){
							cellout.SetCellString(17,8,index,tbr);
							cellout.SetCellString(34,8,index,p_year+"年"+p_month+"月"+p_day+"日");
							if(list.length>1){
								var len=cellout.GetRows(index);
								if(len>9){
									cellout.DeleteRow(7,len-9,index);//删除原始数据
								}
								
								cellout.InsertRow(8,list.length-1,index);
							}
							for(var i=0;i<list.length;i++){
								var data=list[i];
								cellout.SetCellString(2,7+i,index,data.a1);
								for(var j=0;j<40;j++){
									var a="a"+(2+j);
									if(data[a]!=0&&data[a]!=null&&data[a]!=""){
										cellout.D(3+j,7+i,index,data[a]);
									}
									
								}
																		  		
							}
							if(pcs!=null){
								var array=pcs.split("-");
								cellout.SetCellString(3,3,index,array[1]);
							}else{
								cellout.SetCellString(3,3,index,mc);
							}	
							cellout.SetCellString(2,2,index,"时间段 "+year+month);
						}
						
						
						if(pname=="城镇人口增减情况统计年报表(58表)"){
							cellout.SetCellString(18,12,index,tbr);
							cellout.SetCellString(24,12,index,p_year+"年"+p_month+"月"+p_day+"日");
							if(list.length>1){	
								var len=cellout.GetRows(index);								
								if(len>13){
									cellout.DeleteRow(11,len-13,index);//删除原始数据
								}
								cellout.InsertRow(11,list.length-1,index);
							}
							for(var i=0;i<list.length;i++){
								var data=list[i];
								cellout.SetCellString(2,11+i,index,data.a1);
								for(var j=0;j<26;j++){
									var a="a"+(2+j);
									if(data[a]!=0&&data[a]!=null&&data[a]!=""){
										cellout.D(3+j,11+i,index,data[a]);
									}
									
								}
																		  		
							}
							if(pcs!=null){
								var array=pcs.split("-");
								cellout.SetCellString(3,5,index,array[1]);
							}else{
								cellout.SetCellString(3,5,index,mc);
							}	
							cellout.SetCellString(2,3,index,"统计时段 "+startDate+"至"+endDate);
							
						}
						
						
						if(pname=="户籍业务变动信息统计表"){
							cellout.SetCellString(25,13,index,tbr);
							cellout.D(4,13,index,p_year);
							cellout.D(6,13,index,p_month);
							cellout.D(8,13,index,p_day);
							if(list.length>1){	
								var len=cellout.GetRows(index);
								if(len>15){
									cellout.DeleteRow(12,len-15,index);//删除原始数据
								}
								cellout.InsertRow(12,list.length-1,index);
							}
							for(var i=0;i<list.length;i++){
								var data=list[i];
								cellout.SetCellString(2,12+i,index,data.a1);
								for(var j=0;j<45;j++){
									var a="a"+(2+j);
									if(data[a]!=0&&data[a]!=null&&data[a]!=""){
										cellout.D(3+j,12+i,index,data[a]);
									}
									
								}
																		  		
							}
							if(pcs!=null){
								var array=pcs.split("-");
								cellout.SetCellString(3,4,index,array[1]);
							}else{
								cellout.SetCellString(3,4,index,mc);
							}	
							cellout.SetCellString(2,3,index,"统计时段 "+startDate+"至"+endDate);
						}
						
						if(pname=="人口变动情况"){
							cellout.SetCellString(14,24,index,p_year+"年"+p_month+"月"+p_day+"日");
							for(var i=0;i<list.length;i++){
								var data=list[i];
								if(i==0){
									for(var k=0;k<data.length;k++){
										var data1=data[k];
										for(var j=0;j<14;j++){
											var a="a"+(2+j);
											if(data1[a]!=0&&data1[a]!=null&&data1[a]!=""){												
												if(k>1&&j<13){
													cellout.D(4+j,6+k,index,data1[a]);
												}else{
													cellout.D(3+j,6+k,index,data1[a]);
												}
											}
											
										}
									}
								}
								
								if(i==1){
									for(var k=0;k<data.length;k++){
										var data2=data[k];
										for(var j=0;j<14;j++){
											var a="a"+(2+j);
											if(data2[a]!=0&&data2[a]!=null&&data2[a]!=""){																			
												cellout.D(4+j,13+k,index,data2[a]);						
											}
											
										}
									}
								}
								
								if(i==2){
									for(var k=0;k<data.length;k++){
										var data3=data[k];
										for(var j=0;j<11;j++){
											var a="a"+(2+j);
											if(data3[a]!=0&&data3[a]!=null&&data3[a]!=""){																			
												cellout.D(3+j,23+k,index,data3[a]);						
											}
											
										}
									}
								}
							
																		  		
							}
							if(pcs!=null){
								var array=pcs.split("-");
								cellout.SetCellString(4,3,index,array[1]);
							}else{
								cellout.SetCellString(4,3,index,mc);
							}	
							cellout.SetCellString(2,2,index,"统计日期 "+startDate+"至"+endDate);
						}
						
						
						if(pname=="人口底数"){
							cellout.SetCellString(5,6,index,tbr);
							cellout.SetCellString(7,6,index,p_year+"年"+p_month+"月"+p_day+"日");
							if(list.length>1){
								var len=cellout.GetRows(index);
								if(len>7){
									cellout.DeleteRow(5,len-7,index);//删除原始数据
								}
								
								cellout.InsertRow(5,list.length-1,index);
							}
							for(var i=0;i<list.length;i++){
								var data=list[i];
								cellout.D(2,5+i,index,i+1);
								cellout.SetCellString(3,5+i,index,data.a1);
								for(var j=0;j<4;j++){
									var a="a"+(2+j);
									if(data[a]!=0&&data[a]!=null&&data[a]!=""){
										cellout.D(4+j,5+i,index,data[a]);
									}
								}
								
									
								
																		  		
							}
							if(pcs!=null){
								var array=pcs.split("-");
								cellout.SetCellString(3,3,index,array[1]);
							}else{
								cellout.SetCellString(3,3,index,mc);
							}	
							cellout.SetCellString(4,2,index,"统计日期 "+startDate);
						}
						
						
						if(pname=="人口及其变动情况统计报表20栏(57表)"){
							cellout.SetCellString(11,11,index,tbr);
							cellout.SetCellString(19,11,index,p_year+"年"+p_month+"月"+p_day+"日");
							if(list.length>1){
								var len=cellout.GetRows(index);								
								if(len>12){
									cellout.DeleteRow(10,len-12,index);//删除原始数据
								}
								
								cellout.InsertRow(10,list.length-1,index);
							}
							for(var i=0;i<list.length;i++){
								var data=list[i];
								cellout.SetCellString(2,10+i,index,data.a1);
								for(var j=0;j<20;j++){
									var a="a"+(2+j);
									if(data[a]!=0&&data[a]!=null&&data[a]!=""){
										cellout.D(3+j,10+i,index,data[a]);
									}
									
								}
																		  		
							}
							if(pcs!=null){
								var array=pcs.split("-");
								cellout.SetCellString(3,5,index,array[1]);
							}else{
								cellout.SetCellString(3,5,index,mc);
							}	
							cellout.SetCellString(5,2,index,"统计时段 "+startDate+"至"+endDate);
						}
						
						
						if(pname=="人口及其变动情况统计报表20栏(原20栏)"){
							cellout.SetCellString(11,11,index,tbr);
							cellout.SetCellString(25,11,index,p_year+"年"+p_month+"月"+p_day+"日");
							if(list.length>1){
								var len=cellout.GetRows(index);								
								if(len>12){
									cellout.DeleteRow(10,len-12,index);//删除原始数据
								}
								
								cellout.InsertRow(10,list.length-1,index);
							}
							for(var i=0;i<list.length;i++){
								var data=list[i];
								cellout.SetCellString(2,10+i,index,data.a1);
								for(var j=0;j<25;j++){
									var a="a"+(2+j);
									if(data[a]!=0&&data[a]!=null&&data[a]!=""){
										cellout.D(3+j,10+i,index,data[a]);
									}
									
								}
																		  		
							}
							if(pcs!=null){
								var array=pcs.split("-");
								cellout.SetCellString(3,5,index,array[1]);
							}else{
								cellout.SetCellString(3,5,index,mc);
							}	
							cellout.SetCellString(2,2,index,"统计时段 "+startDate+"至"+endDate);
						}
						
						
						if(pname=="省内迁移情况统计报表"){
							cellout.SetCurSheetEx("输出页");//指定当前的表页,根据页签名
							var index_scy=cellout.GetSheetIndex("输出页");
							cellout.SetCellString(9,8,index_scy,tbr);
							if(rpttype=="1"){
								cellout.SetCellString(24,8,index_scy,p_year+"年"+p_month+"月"+p_day+"日");
							}else{
								cellout.SetCellString(19,8,index_scy,p_year+"年"+p_month+"月"+p_day+"日");
							}				
							if(list.length>1){
								var len=cellout.GetRows(index_scy);
								if(len>9){
									cellout.DeleteRow(7,len-9,index_scy);//删除原始数据
								}
								
								cellout.InsertRow(7,list.length-1,index_scy);
							}
							for(var i=0;i<list.length;i++){
								var data=list[i];
								cellout.SetCellString(2,7+i,index_scy,data.a1);
								for(var j=0;j<34;j++){
									var a="a"+(2+j);
									if(data[a]!=0&&data[a]!=null&&data[a]!=""){
										cellout.D(3+j,7+i,index_scy,data[a]);
									}
									
								}
																		  		
							}
							if(pcs!=null){
								var array=pcs.split("-");
								cellout.SetCellString(3,3,index_scy,array[1]);
							}else{
								cellout.SetCellString(3,3,index_scy,mc);
							}	
							cellout.SetCellString(2,2,index_scy,"统计时段 "+startDate+"至"+endDate);
						}
						
						if(pname=="办证人数统计"){
							cellout.SetCellString(6,5,index,tbr);
							cellout.SetCellString(7,5,index,p_year+"年"+p_month+"月"+p_day+"日");
							if(list.length>1){
								var len=cellout.GetRows(index);
								if(len>6){
									cellout.DeleteRow(4,len-6,index);//删除原始数据
								}
								
								cellout.InsertRow(4,list.length-1,index);
							}
							for(var i=0;i<list.length;i++){
								var data=list[i];
								cellout.D(2,4+i,index,i+1);
								cellout.SetCellString(3,4+i,index,data.a1);
								for(var j=0;j<3;j++){
									var a="a"+(2+j);
									if(data[a]!=0&&data[a]!=null&&data[a]!=""){
										cellout.D(4+j,4+i,index,data[a]);
									}
								}
																													  		
							}
							if(pcs!=null){
								var array=pcs.split("-");
								cellout.SetCellString(3,2,index,array[1]);
							}else{
								cellout.SetCellString(3,2,index,mc);
							}
						}
					
					 }else{
						 showInfo("没有查到数据!");
					 }
					}
			    );
		
	}
}),
new Ext.Button({
	id:'add',
	text:'报表保存',
	minWidth:60,	
	handler:function(){ 
		var cellin=document.getElementById("Cellout");
		cellin.Login("浙江金铖华元信息技术有限公司自定义报表-WEB版","","13040351","1340-1275-0110-0004");
		var index=cellin.GetSheetIndex("输出区");
		if(index==-1){
			index=cellin.GetSheetIndex("输出页");
		}
		cellin.SaveSheet(index);
	}
}),
new Ext.Button({
	id:'dy',
	text:'打印预览',
	minWidth:60,	
	handler:function(){
		var cellin=document.getElementById("Cellout");
		cellin.Login("浙江金铖华元信息技术有限公司自定义报表-WEB版","","13040351","1340-1275-0110-0004");
		var index=cellin.GetSheetIndex("输出区");
		if(index==-1){
			index=cellin.GetSheetIndex("输出页");
		}
		cellin.PrintPreview(1,index);
	}
}),
new Ext.Button({
	id:'bbprint',
	text:'报表打印',
	minWidth:60,	
	handler:function(){
		var cellin=document.getElementById("Cellout");
		cellin.Login("浙江金铖华元信息技术有限公司自定义报表-WEB版","","13040351","1340-1275-0110-0004");
		var index=cellin.GetSheetIndex("输出区");
		if(index==-1){
			index=cellin.GetSheetIndex("输出页");
		}
		cellin.PrintSheet(1,index);
	}
}),
new Ext.Button({
	id:'close',
	text:'关闭',
	minWidth:60,	
	handler:function(){
	
		window.parent.closeWorkSpace();
	}
})]
		 },{
		     title: '报表图表资源树',
		     region:'west',
		     //split:true,
	         //collapseMode:'mini',
	         //useSplitTips:true,
	         //splitTip: "拖动来改变尺寸.",
	         //collapsibleSplitTip:'拖动来改变尺寸. 双击隐藏',
	         margins:{left:0, top: 0, right: 0, bottom:0},
	         width: 250,
		     layout:'fit',
	         collapsible: false,
	         bodyStyle:'overflow:scroll',
	         items:[tree]
		 },{
			 region:'center',
			 layout:'border',
			 collapsible: false,
	         border:false,
	         frame:false,
	         //bodyStyle:'overflow:scroll;overflow-x:hidden',
	         margins:'0',			     
		     items:[tabs]
		    	 
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
		
	 Gnt.initCellWeb('bbtj','Cellin'); 
	 Gnt.initCellWeb('bbsc','Cellin'); 
	
});