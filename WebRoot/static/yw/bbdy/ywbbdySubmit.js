function bbdysumit(procName,mbcontent,tbr,tjsj,pcs,startDate,endDate,tabs,year,month,save_type,save_path){
	var pulic_date=Ext.util.Format.date(new Date(),'Y-m-d');
	pulic_date=pulic_date.split("-");
	var p_year=pulic_date[0];
	var p_month=pulic_date[1];
	var p_day=pulic_date[2];
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
						//var array=pcs.split("-");
						cellout.SetCellString(3,3,index,pcs);
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
						//var array=pcs.split("-");
						cellout.SetCellString(3,3,index,pcs);
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
						//var array=pcs.split("-");
						cellout.SetCellString(3,5,index,pcs);
					}	
					cellout.SetCellString(3,2,index,"统计时段 "+startDate+"至"+endDate);
					
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
						//var array=pcs.split("-");
						cellout.SetCellString(3,4,index,pcs);
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
						//var array=pcs.split("-");
						cellout.SetCellString(4,3,index,pcs);
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
						//var array=pcs.split("-");
						cellout.SetCellString(3,3,index,pcs);
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
						//var array=pcs.split("-");
						cellout.SetCellString(3,5,index,pcs);
					}	
					cellout.SetCellString(5,2,index,"统计时段 "+startDate+"至"+endDate);
				}
				
				
				if(pname=="人口及其变动情况统计报表22栏(原20栏)"){
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
						//var array=pcs.split("-");
						cellout.SetCellString(3,5,index,pcs);
					}	
					cellout.SetCellString(2,2,index,"统计时段 "+startDate+"至"+endDate);
				}
				
				
				if(pname=="省内迁移情况统计报表"){
					cellout.SetCurSheetEx("输出页");//指定当前的表页,根据页签名
					var index_scy=cellout.GetSheetIndex("输出页");
					cellout.SetCellString(9,8,index_scy,tbr);
						cellout.SetCellString(19,8,index_scy,p_year+"年"+p_month+"月"+p_day+"日");				
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
						//var array=pcs.split("-");
						cellout.SetCellString(3,3,index_scy,pcs);
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
						//var array=pcs.split("-");
						cellout.SetCellString(3,2,index,pcs);
					}
				}
				
				if(save_type==1){	
					cellout.ExportExcelFile(save_path+"\\"+pname+"-"+pcs+".xls",index);
				}else if(save_type==2){
					cellout.Login("浙江金铖华元信息技术有限公司自定义报表-WEB版","","13040351","1340-1275-0110-0004");
					cellout.ExportPdfFile(save_path+"\\"+pname+"-"+pcs+".pdf",0,0,1);
				}else if(save_type==3){
					cellout.ExportCSVFile(save_path+"\\"+pname+"-"+pcs+".cvs",index);
				}else{
					//cellout.SaveFile(pcs,0);
					cellout.SaveAsFile(save_path+"\\"+pname+"-"+pcs,0);
				}
			
			 }else{
				 showInfo("没有查到数据!");
			 }
			}
			
	    );
}

function changitem(pname){
	 if(pname=="常住人口派出所四变月报表"){   	 		
		Ext.getCmp("item2").setVisible(false);
		Ext.getCmp("item1").setVisible(false);
		Ext.getCmp("item3").setVisible(true);
		Ext.getCmp("item4").setVisible(false);
	 }else if(pname=="百岁年龄"||pname=="人口底数"){
		 Ext.getCmp("item2").setVisible(false);
		 Ext.getCmp("item3").setVisible(false);
		 Ext.getCmp("item1").setVisible(true);
		 Ext.getCmp("item4").setVisible(false);
	 }else if(pname=="办证人数统计"){
		 Ext.getCmp("item2").setVisible(false);
		 Ext.getCmp("item3").setVisible(false);
		 Ext.getCmp("item1").setVisible(false);
		 Ext.getCmp("item4").setVisible(true);
	 }else{
		 Ext.getCmp("item2").setVisible(true);
		 Ext.getCmp("item3").setVisible(false);
		 Ext.getCmp("item1").setVisible(false);
		 Ext.getCmp("item4").setVisible(false);
	 }
	 
}
