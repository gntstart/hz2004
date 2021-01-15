var charts1;
var charts2;
var charts3;
var charts4;
var charts5;
var charts6;
var charts7;
var charts8;
var charts9;
var dataList;
Ext.onReady(function(){
	Ext.QuickTips.init();
	var option1 = {
	        title: {
	            text: '安徽省行政分布'
	        },
	        tooltip: {
	            trigger: 'item',
	            formatter: '{b}<br/>{c} 人口'
	        },
	        toolbox: {
	            show: true,
	            orient: 'vertical',
	            left: 'right',
	            top: 'center'
	        },
	        
	        visualMap: {
	            min: 800,
	            max: 50000,
	            text:['High','Low'],
	            realtime: false,
	            calculable: true,
	            inRange: {
	                color: ['lightskyblue','yellow', 'orangered']
	            }
	        },
	        series: [
	            {
	                name: '安徽16市人口分布',
	                type: 'map',
	                mapType: 'AH', // 自定义扩展图表类型
	                itemStyle:{
	                    normal:{label:{show:true}},
	                    emphasis:{label:{show:true}}
	                },
	                data:[
	                	 {name: '合肥市', value: 20057.34},
	                     {name: '芜湖市', value: 15477.48},
	                     {name: '蚌埠市', value: 31686.1},
	                     {name: '淮南市', value: 6992.6},
	                     {name: '马鞍山市', value: 44045.49},
	                     {name: '淮北市', value: 40689.64},
	                     {name: '铜陵市', value: 37659.78},
	                     {name: '安庆市', value: 45180.97},
	                     {name: '黄山市', value: 55204.26},
	                     {name: '阜阳市', value: 4918.26},
	                     {name: '宿州市', value: 5881.84},
	                     {name: '滁州市', value: 21900.9},
	                     {name: '六安市', value: 4178.01},
	                     {name: '宣城市', value: 2227.92},
	                     {name: '池州市', value: 2180.98},
	                     {name: '亳州市', value: 3368}
	                ]
	            }
	        ]
	    }; 
	var option2 = {
			title: {
	            text: '总人口性别饼图',
	            x:'center'
	        },
		    tooltip: {
		        trigger: 'item',
		        formatter: "{b}: {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        x: 'left',
		        data:['男','女']
		    },
		    series: [
		        {
		            name:'性别饼图',
		            type:'pie',
		            avoidLabelOverlap: false,
		            label: {
		                normal: {
		                    show: false,
		                    position: 'center'
		                },
		                emphasis: {
		                    show: true,
		                    textStyle: {
		                        fontSize: '30',
		                        fontWeight: 'bold'
		                    }
		                }
		            },
		            labelLine: {
		                normal: {
		                    show: false
		                }
		            },
		            data:[
//		                {value:335, name:'男'},
//		                {value:310, name:'女'}
		            ]
		        }
		    ]
		};
	var option3 = {
			title: {
	            text: '流入流出饼图',
	            x:'center'
	        },
		    tooltip: {
		        trigger: 'item',
		        formatter: "{b}: {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        x: 'left',
		        data:['流入','流出']
		    },
		    series: [
		        {
		            name:'流入流出饼图',
		            type:'pie',
		            avoidLabelOverlap: false,
		            label: {
		                normal: {
		                    show: false,
		                    position: 'center'
		                },
		                emphasis: {
		                    show: true,
		                    textStyle: {
		                        fontSize: '30',
		                        fontWeight: 'bold'
		                    }
		                }
		            },
		            labelLine: {
		                normal: {
		                    show: false
		                }
		            },
		            data:[
//		                {value:335, name:'流入'},
//		                {value:310, name:'流出'}
		            ]
		        }
		    ]
		};
	var option4 =  {
			title: {
	            text: '人口流入',
	            x:'left'
	        },
	        legend: {
	            data: ['出生', '补录','迁入']
	        },
		    tooltip: {},
		    xAxis: [
		        {
		            type: 'category',
		            axisLabel: {  
			        	   interval:0,  
			        	   rotate:40  
			        },
		            axisTick: {show: false},
		            data: [/*'合肥市', '芜湖市', '蚌埠市', '淮南市', '马鞍山市','淮北市', '铜陵市', '安庆市', '黄山市', '阜阳市','宿州市', '滁州市', '六安市', '宣城市', '池州市', '亳州市'*/]
		        }
		    ],
		    yAxis: [
		        {
		            type: 'value'
		        }
		    ],
		    series: [
//		        {
//		            name: '出生',
//		            type: 'bar',
//		            label: labelOption,
//		            data: [220, 182, 191, 234, 290,320, 332, 301, 334,150, 232, 201, 154, 190,330,550]
//		        },
//		        {
//		            name: '迁入',
//		            type: 'bar',
//		            label: labelOption,
//		            data: [150, 232, 201, 154, 190, 334, 390,880,330,550, 290,320, 332,115,852,147]
//		        },
//		        {
//		            name: '补录',
//		            type: 'bar',
//		            label: labelOption,
//		            data: [98,77,101,99,40,290,320,332,115,852,147,201,154,190,352,682]
//		        }
		    ]
		};

	var labelOption = {
		    normal: {
		        show: false,
		        formatter: '{c}  {name|{a}}',
		        fontSize: 16,
		        rich: {
		            name: {
		                textBorderColor: '#fff'
		            }
		        }
		    }
		};
	var option5 =   {
			title: {
	            text: '人口流出',
	            x:'left'
	        },
	        legend: {
	            data: ['死亡','迁出','删除']
	        },
		    tooltip: {},
		    xAxis: [
		        {
		            type: 'category',
		            axisLabel: {  
			        	   interval:0,  
			        	   rotate:40  
			        },
		            axisTick: {show: false},
		            data: [/*'合肥市', '芜湖市', '蚌埠市', '淮南市', '马鞍山市','淮北市', '铜陵市', '安庆市', '黄山市', '阜阳市','宿州市', '滁州市', '六安市', '宣城市', '池州市', '亳州市'*/]
		        }
		    ],
		    yAxis: [
		        {
		            type: 'value'
		        }
		    ],
		    series: [/*
		        {
		            name: '死亡',
		            type: 'bar',
		            barGap: 0,
		            label: labelOption,
		            data: [320, 332, 301, 334, 390,320, 332, 301, 334, 390,320, 332, 301, 334, 390,880]
		        },
		        {
		            name: '注销',
		            type: 'bar',
		            label: labelOption,
		            data: [220, 182, 191, 234, 290,320, 332, 301, 334,150, 232, 201, 154, 190,330,550]
		        },
		        {
		            name: '迁出',
		            type: 'bar',
		            label: labelOption,
		            data: [150, 232, 201, 154, 190, 334, 390,880,330,550, 290,320, 332,115,852,147]
		        },
		        {
		            name: '删除',
		            type: 'bar',
		            label: labelOption,
		            data: [98,77,101,99,40,290,320,332,115,852,147,201,154,190,352,682]
		        }
		    */]
		};

	var option6 = {
			title: {
	            text: '全省(市)人口年龄分布',
	            x:'center'
	        },
		    tooltip: {
		        trigger: 'item',
		        formatter: "{b}: {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        x: 'left',
		        data:['0~20','21~40','41~60','61~80','80以上']
		    },
		    series: [
		        {
		            name:'人口年龄分布饼图',
		            type:'pie',
		            avoidLabelOverlap: false,
		            label: {
		                normal: {
		                    show: false,
		                    position: 'center'
		                },
		                emphasis: {
		                    show: true,
		                    textStyle: {
		                        fontSize: '30',
		                        fontWeight: 'bold'
		                    }
		                }
		            },
		            labelLine: {
		                normal: {
		                    show: false
		                }
		            },
		            data:[
//		                {value:335, name:'0~20'},
//		                {value:310, name:'21~40'},
//		                {value:335, name:'41~60'},
//		                {value:335, name:'61~80'},
//		                {value:310, name:'80以上'}
		            ]
		        }
		    ]
		};

	var option7 =  {
			title: {
	            text: '流入人口学历分布',
	            x:'center'
	        },
	        tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis : [
		        {
		            type : 'category',
			        axisLabel: {  
			        	   interval:0,  
			        	   rotate:40  
			        },
		            data : ['文盲或半文盲', '小学', '初中', '高中', '技校', '中专', '大专', '本科','研究生及以上','其他'],
		            axisTick: {
		                alignWithLabel: true
		            }
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value'
		        }
		    ],
		    series : [
		        {
		            name:'流入人口学历分布',
		            type:'bar',
		            barWidth: '60%',
		            data:[/* 20, 150, 680, 70, 110, 130,521,891,1950*/]
		        }
		    ]
		};

	var option8 = {
			title: {
	            text: '迁入本省与外省对比饼图',
	            x:'center'
	        },
		    tooltip: {
		        trigger: 'item',
		        formatter: "{b}: {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        x: 'left',
		        y:'15%',
		        data:['本省','外省']
		    },
		    series: [
		        {
		            name:'性别饼图',
		            type:'pie',
		            avoidLabelOverlap: false,
		            label: {
		                normal: {
		                    show: false,
		                    position: 'center'
		                },
		                emphasis: {
		                    show: true,
		                    textStyle: {
		                        fontSize: '30',
		                        fontWeight: 'bold'
		                    }
		                }
		            },
		            labelLine: {
		                normal: {
		                    show: false
		                }
		            },
		            data:[
//		                {value:335, name:'男'},
//		                {value:310, name:'女'}
		            ]
		        }
		    ]
		};
	
	var option9 = {
			title: {
	            text: '迁入省份柱状图',
	            x:'center'
	        },
	        tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis : [
		        {
		            type : 'category',
			        axisLabel: {  
			        	   interval:0,  
			        	   rotate:40,
			        	   textStyle:{
			        		   fontSize:10
			        	   }
			        },
		            data : [],
		            axisTick: {
		                alignWithLabel: true
		            }
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value'
		        }
		    ],
		    series : [
		        {
		            name:'迁入人数分布',
		            type:'bar',
		            barWidth: '60%',
		            itemStyle: {
			            normal: {
			                // 随机显示
			                color:function(d){return "#"+Math.floor(Math.random()*(256*256*256-1)).toString(16);}
			              
			                // 定制显示（按顺序）
//			                color: function(params) { 
//			                    var colorList = ['#C33531','#EFE42A','#64BD3D','#EE9201','#29AAE3', '#B74AE5','#0AAF9F','#E89589','#16A085','#4A235A','#C39BD3 ','#F9E79F','#BA4A00','#ECF0F1','#616A6B','#EAF2F8','#4A235A','#3498DB' ]; 
//			                    return colorList[params.dataIndex] 
//			                }
			            }
			        },
		            data:[/* 20, 150, 680, 70, 110, 130,521,891,1950*/]
		        }
		        
		    ]
		};
//	{
//			title: {
//	            text: '迁入省份饼图',
//	            x:'center'
//	        },
//		    tooltip: {
//		        trigger: 'item',
//		        formatter: "{b}: {c} ({d}%)"
//		    },
//		    legend: {
//		        orient: 'vertical',
//		        x: 'left',
//		        data:[]
//		    },
//		    color: [
//	        	"#fc97af",
//	            "#87f7cf",
//	        	"#f7f494",
//	            "#72ccff",
//	            "#f7c5a0",
//	            "#d4a4eb",
//	            "#d2f5a6",
//	            "#76f2f2",
//	            '#ff7f50', '#87cefa', '#da70d6', '#32cd32', '#6495ed', 
//	            '#ff69b4', '#ba55d3', '#cd5c5c', '#ffa500', '#40e0d0', 
//	            '#1e90ff', '#ff6347', '#7b68ee', '#00fa9a', '#ffd700', 
//	            '#6b8e23', '#ff00ff', '#3cb371', '#b8860b', '#30e0e0',
//	            "#dd6b66",
//	            "#759aa0",
//	            "#e69d87",
//	            "#8dc1a9",
//	            "#ea7e53",
//	            "#eedd78",
//	            "#73a373",
//	            "#73b9bc",
//	            "#7289ab",
//	            "#91ca8c",
//	            "#f49f42",
//	            "#bf444c",
//	            "#d88273",
//	            "#f6efa6",
//	            "#9b8bba",
//	            "#e098c7",
//	            "#8fd3e8",
//	            "#71669e",
//	            "#cc70af",
//	            "#7cb4cc",
//	            "#cceffa",
//				"#ff715e",
//	            "#ffaf51",
//	            "#ffee51",
//	            "#8c6ac4",
//	            "#715c87",
//				"#797fba"
//	        ],
//		    series: [
//		        {
//		            name:'性别饼图',
//		            type:'pie',
//		            avoidLabelOverlap: false,
//		            label: {
//		                normal: {
//		                    show: false,
//		                    position: 'center'
//		                },
//		                emphasis: {
//		                    show: true,
//		                    textStyle: {
//		                        fontSize: '30',
//		                        fontWeight: 'bold'
//		                    }
//		                }
//		            },
//		            labelLine: {
//		                normal: {
//		                    show: false
//		                }
//		            },
//		            data:[
//		            ]
//		        }
//		    ]
//		};

	$.ajax({
	        url: xmdz+'js/echarts/34.json',
	        async: false,
	        success: function (data) {
	        	echarts.registerMap('AH',data);
	        	charts1 = echarts.init(document.getElementById('chart_panel1'),'dark');
	        	queryEchartsData();
	        }
	    });
	function queryEchartsData(){
		Gnt.submit(
				{}, 
				"cx/echartsZl/queryEchartsData", 
				"数据加载中，请稍后...", 
				function(jsonData,params){
					if(jsonData&&jsonData.list){
						dataList = jsonData.list;
						
						var xzqhdata= ['合肥市', '芜湖市', '蚌埠市', '淮南市', '马鞍山市','淮北市', '铜陵市', '安庆市', '黄山市', '阜阳市','宿州市', '滁州市', '六安市', '宣城市', '池州市', '亳州市'];
						var mapdata = [];
						for(var i =0; i< xzqhdata.length ;i++){
							var obj={
									name:'',
									value:''
							};
							var zrksCount = 0;
							var flag = false;
							obj.name = xzqhdata[i];
							for(var y =0&&!flag; y< dataList.length ;y++){
								if(xzqhdata[i]==dataList[y].ssqhmc){
									zrksCount = parseInt(zrksCount) +parseInt(dataList[y].zrks);
									flag = true;
								}
								
							}
							if(!flag){
								obj.value = 0;
								
							}else{
								obj.value = zrksCount;
							}
							mapdata.push(obj);
						}
						charts1.on('click', function(params){
							var xzqh = params.data.name;
							var list = new Array();
							for(var i =0; i< dataList.length ;i++){
								if(dataList[i].ssqhmc==xzqh){
									list.push(dataList[i]);
								}
							}
							drawEchartS(list,xzqh);
						});
						option1.series[0].data = mapdata;
						charts1.setOption(option1);
						drawEchartS(dataList,Gnt.ux.Dict.getDictLable('XZQHB',user.dqbm+"00"));//右侧echarts填充数据
					}
				}
		);
				
	}
	function drawEchartS(list,xzqhmc){
		var echartsTitleName = '安徽省';
		document.getElementById('biaoti').innerText=xzqhmc+"户政统一服务平台—人口数据分析";
		var zrks = 0;//总人口数
		var zhs = 0;//总户数
		var zlrs = 0;//总流入数
		var zlcs = 0;//总流出数
		var nxrk = 0;//男性人口
		var nvrk = 0;//女性人口
		var cslr = 0;//出生流入
		var bllr = 0;//补录流入
		var qrlr = 0;//迁入流入
		var swlc = 0;//死亡流出
		var qclc = 0;//迁出流出
		var sclc = 0;//删除流出
		var nl_level1 = 0;//年龄分级1 0~20
		var nl_level2 = 0;//年龄分级2 21~40
		var nl_level3 = 0;//年龄分级3 41~60
		var nl_level4 = 0;//年龄分级4 61~80
		var nl_level5 = 0;//年龄分级5 80以上
		var lrxl_level1 = 0;//流入学历1 文盲或半文盲
		var lrxl_level2 = 0;//流入学历2 小学
		var lrxl_level3 = 0;//流入学历3 初中
		var lrxl_level4 = 0;//流入学历4 高中
		var lrxl_level5 = 0;//流入学历5 技校
		var lrxl_level6 = 0;//流入学历6 中专
		var lrxl_level7 = 0;//流入学历7 大专
		var lrxl_level8 = 0;//流入学历8 本科
		var lrxl_level9 = 0;//流入学历9 研究生已以上
		var lrxl_level10 = 0;//其他
		var lrXzqh= new Array();
		var lrCsXzqh= new Array();
		var lrQrXzqh= new Array();
		var lrBlXzqh= new Array();
		
		var lcSwXzqh= new Array();
		var lcQcqh= new Array();
		var lcScXzqh= new Array();
		var benshen = 0;
		var waishen = 0;
		var qrsfArray= new Array();
		var qrsfCountArray= new Array();
		for(var i = 0;i<list.length;i++){
			var listTemp = list[i];
			var tempXzqh = xzqhmc?listTemp.qhmc:listTemp.ssqhmc;
			zrks = parseInt(zrks)+parseInt(listTemp.zrks);
			zhs = parseInt(zhs)+parseInt(listTemp.zhs);
			zlrs = parseInt(zlrs)+parseInt(listTemp.cslr)+parseInt(listTemp.bllr)+parseInt(listTemp.qrlr);
			zlcs = parseInt(zlcs)+parseInt(listTemp.swlc)+parseInt(listTemp.qclc)+parseInt(listTemp.sclc);
			nxrk = parseInt(nxrk)+parseInt(listTemp.nxrk); 
			nvrk = parseInt(nvrk)+parseInt(listTemp.nvrk);
			nl_level1 = parseInt(nl_level1)+parseInt(listTemp.nl_level1);
			nl_level2 = parseInt(nl_level2)+parseInt(listTemp.nl_level2);
			nl_level3 = parseInt(nl_level3)+parseInt(listTemp.nl_level3);
			nl_level4 = parseInt(nl_level4)+parseInt(listTemp.nl_level4);
			nl_level5 = parseInt(nl_level5)+parseInt(listTemp.nl_level5);
			lrxl_level1 = parseInt(lrxl_level1)+parseInt(listTemp.lrxl_level1);
			lrxl_level2 = parseInt(lrxl_level2)+parseInt(listTemp.lrxl_level2);
			lrxl_level3 = parseInt(lrxl_level3)+parseInt(listTemp.lrxl_level3);
			lrxl_level4 = parseInt(lrxl_level4)+parseInt(listTemp.lrxl_level4);
			lrxl_level5 = parseInt(lrxl_level5)+parseInt(listTemp.lrxl_level5);
			lrxl_level6 = parseInt(lrxl_level6)+parseInt(listTemp.lrxl_level6);
			lrxl_level7 = parseInt(lrxl_level7)+parseInt(listTemp.lrxl_level7);
			lrxl_level8 = parseInt(lrxl_level8)+parseInt(listTemp.lrxl_level8);
			lrxl_level9 = parseInt(lrxl_level9)+parseInt(listTemp.lrxl_level9);
			lrxl_level10 = parseInt(lrxl_level10)+parseInt(listTemp.lrxl_level10);
			if(lrXzqh.indexOf(tempXzqh)>=0){
				lrCsXzqh[lrXzqh.indexOf(listTemp.ssqhmc)] = parseInt(lrCsXzqh[lrXzqh.indexOf(tempXzqh)])+parseInt(listTemp.cslr);
				lrQrXzqh[lrXzqh.indexOf(listTemp.ssqhmc)] = parseInt(lrQrXzqh[lrXzqh.indexOf(tempXzqh)])+parseInt(listTemp.bllr);
				lrBlXzqh[lrXzqh.indexOf(listTemp.ssqhmc)] = parseInt(lrBlXzqh[lrXzqh.indexOf(tempXzqh)])+parseInt(listTemp.qrlr);
				
				lcSwXzqh[lrXzqh.indexOf(listTemp.ssqhmc)] = parseInt(lcSwXzqh[lrXzqh.indexOf(tempXzqh)])+parseInt(listTemp.swlc);
				lcQcqh[lrXzqh.indexOf(listTemp.ssqhmc)] = parseInt(lcQcqh[lrXzqh.indexOf(tempXzqh)])+parseInt(listTemp.qclc);
				lcScXzqh[lrXzqh.indexOf(listTemp.ssqhmc)] = parseInt(lcScXzqh[lrXzqh.indexOf(tempXzqh)])+parseInt(listTemp.sclc);
			}else{
				lrXzqh.push(tempXzqh);
				lrCsXzqh.push(listTemp.cslr);
				lrQrXzqh.push(listTemp.bllr);
				lrBlXzqh.push(listTemp.qrlr);
				
				lcSwXzqh.push(listTemp.swlc);
				lcQcqh.push(listTemp.qclc);
				lcScXzqh.push(listTemp.sclc);
			}
			if(listTemp.xzqhList&&listTemp.xzqhList.length>0){
				var xzqhlist = listTemp.xzqhList;
				for(var y = 0;y<xzqhlist.length;y++){
					var xzqhTemp = xzqhlist[y];
					if(xzqhTemp.qcdsdm =='340000'){
						benshen = xzqhTemp.count;
					}else{
						waishen = parseInt(waishen) + parseInt(xzqhTemp.count);
						if(qrsfArray.indexOf(xzqhTemp.qcdsmc)>=0){
							var obj =qrsfCountArray[qrsfArray.indexOf(xzqhTemp.qcdsmc)];
							obj = parseInt(obj) + parseInt(xzqhTemp.count);
							qrsfCountArray[qrsfArray.indexOf(xzqhTemp.qcdsmc)] = obj;
						}else{
							var obj =xzqhTemp.count;
							qrsfArray.push(xzqhTemp.qcdsmc);
							qrsfCountArray.push(obj);
							
						}
					}
					
				}
			}
		}
		document.getElementById('zrks').innerText=zrks;
		document.getElementById('zhs').innerText=zhs;
		document.getElementById('zlrs').innerText=zlrs;
		document.getElementById('zlcs').innerText=zlcs;
		if(charts2 != null && charts2 != "" && charts2 != undefined) {
			charts2.dispose();
		}
		charts2 = echarts.init(document.getElementById('chart_panel2'),'dark');
		option2.title.text= (xzqhmc?"":echartsTitleName)+'总人口性别饼图';
		option2.series[0].data=[{value:nxrk, name:'男'},{value:nvrk, name:'女'}];
		charts2.setOption(option2);
		if(charts3 != null && charts3 != "" && charts3 != undefined) {
			charts3.dispose();
		}
    	//全省流入流出饼图
    	charts3 = echarts.init(document.getElementById('chart_panel3'),'dark');
		option3.title.text= (xzqhmc?"":echartsTitleName)+'当年'+'流入流出饼图';
		option3.series[0].data=[{value:zlrs, name:'流入'},{value:zlcs, name:'流出'}];
		charts3.setOption(option3);
		if(charts6 != null && charts6 != "" && charts6 != undefined) {
			charts6.dispose();
		}
		//年龄分布柱状图
		charts6 = echarts.init(document.getElementById('chart_panel6'),'dark');
		option6.title.text= (xzqhmc?"":echartsTitleName)+'人口年龄分布';//人口年龄分布
		option6.series[0].data=[{value:nl_level1, name:'0~20'},{value:nl_level2, name:'21~40'},{value:nl_level3, name:'41~60'},{value:nl_level4, name:'61~80'},{value:nl_level5, name:'80以上'}];
		charts6.setOption(option6);
		if(charts7 != null && charts7 != "" && charts7 != undefined) {
			charts7.dispose();
		}
		//文化程度分布柱状图
		charts7 = echarts.init(document.getElementById('chart_panel7'),'dark');
		option7.title.text= (xzqhmc?"":echartsTitleName)+'流入人口学历分布';
		option7.series[0].data=[lrxl_level1,lrxl_level2,lrxl_level3,lrxl_level4,lrxl_level5,lrxl_level6,lrxl_level7,lrxl_level8,lrxl_level9,lrxl_level10];
		charts7.setOption(option7);
		if(charts4 != null && charts4 != "" && charts4 != undefined) {
			charts4.dispose();
		}
		//流入柱状图
		charts4 = echarts.init(document.getElementById('chart_panel4'),'dark');
		option4.title.text= (xzqhmc?"":echartsTitleName)+'当年'+'人口流入';
		option4.xAxis[0].data=lrXzqh
		option4.series=[
	        {
	            name: '出生',
	            type: 'bar',
	            label: labelOption,
	            data: lrCsXzqh
	        },
	        {
	            name: '迁入',
	            type: 'bar',
	            label: labelOption,
	            data: lrQrXzqh
	        },
	        {
	            name: '补录',
	            type: 'bar',
	            label: labelOption,
	            data: lrBlXzqh
	        }
	    ];
		charts4.setOption(option4);
		if(charts5 != null && charts5 != "" && charts5 != undefined) {
			charts5.dispose();
		}
		//流出柱状图
		charts5 = echarts.init(document.getElementById('chart_panel5'),'dark');
		option5.title.text= (xzqhmc?"":echartsTitleName)+'当年'+'人口流出';
		option5.xAxis[0].data=lrXzqh;
		option5.series=[
	        {
	            name: '死亡',
	            type: 'bar',
	            barGap: 0,
	            label: labelOption,
	            data: lcSwXzqh
	        },
	        {
	            name: '迁出',
	            type: 'bar',
	            label: labelOption,
	            data: lcQcqh
	        },
	        {
	            name: '删除',
	            type: 'bar',
	            label: labelOption,
	            data: lcScXzqh
	        }
	    ]
		charts5.setOption(option5);
		if(charts8 != null && charts8 != "" && charts8 != undefined) {
			charts8.dispose();
		}
		charts8 = echarts.init(document.getElementById('chart_panel8'),'dark');
		option8.title.text= (xzqhmc?"":echartsTitleName)+'当年'+'迁入本省与外省对比饼图';
		option8.series[0].data=[{value:benshen, name:'本省'},{value:waishen, name:'外省'}];
		charts8.setOption(option8);
		charts9 = echarts.init(document.getElementById('chart_panel9'),'dark');
		option9.title.text= (xzqhmc?"":echartsTitleName)+'当年'+'迁入省份柱状图';
//		option9.legend.data = qrsfArray;
//		option9.series[0].data=qrsfCountArray;
		option9.xAxis[0].data = qrsfArray;
		option9.series[0].data=qrsfCountArray;
		
		charts9.setOption(option9);
	}	
//	var url = xmdz+'echarts/echartsZlNew.jsp';
//	var objShell= new ActiveXObject("WScript.Shell");
//	objShell.Run("cmd.exe /c start chrome \""+url+"\" ",0,true);
	//window.parent.parent.closeWorkSpace();
});
