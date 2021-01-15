//var baseUrl="../json/";
//function getDate() {
//    var today = new Date();
//    var date = today.getFullYear() + "年" + twoDigits(today.getMonth() + 1) + "月" + twoDigits(today.getDate()) + "日";
//    var week = " 星期" + "日一二三四五六 ".charAt(today.getDay());
//    var time = twoDigits(today.getHours()) + ": " + twoDigits(today.getMinutes()) + ": " + twoDigits(today.getSeconds());
//    $("#timego").html(date +" "+time+" "+week);
//}
//function twoDigits(val) {
//    if (val < 10) return "0" + val;
//    return val;
//}
// $(function(){
//     setInterval(getDate, 1000);
//     //浏览器当前窗口可视区域宽度
//     var pagewidth=$(window).width();
//     $(".liujfdiv").css('width', (pagewidth-900)+'px');
//     $(".liujfdiv2").css('width', ((pagewidth-940)/2)+'px');
//     var param=wtx.getGetParam();
//     function initEcharts(id){
//         var theme=['macarons','shine','dark','infographic','roma'];
//         var index=Number(param.theme?param.theme:"0");
//         if(index<1||index>theme.length){
//             index=1;
//         }
//         return  echarts.init(document.getElementById(id),theme[index-1]);
//     }
//     $("#title_head").add($("#title_menu")).mouseover(function(){
//         $("#title_menu").show();
//     }).mouseout(function(){
//         $("#title_menu").hide();
//     });
//     /* 地图 需要哪个省市的地图直接引用js 将下面的name以及mapType修改为对应省市名称*/
//	var chart1_map = initEcharts('data_map_div');//地图
//	var chart4 = initEcharts('data_4_div');//总人口性别占比
//	var chart7 = initEcharts('data_7_div');//人口年龄分布
//	var chart5 = initEcharts('data_5_div');//当年迁入本省与外省占比分析
//	var chart3 = initEcharts('data_3_div');//当年流入流出占比
//	var chart8 = initEcharts('data_8_div');//当前迁入省份柱状图
//	var chart9 = initEcharts('data_9_div');//流入人口学历分布
//	var chart11 = initEcharts('data_11_div');//当年流入人口
//	var chart12 = initEcharts('data_12_div');//当年人口流出
//	var zrks = 0;//总人口数
//	var zhs = 0;//总户数
//	var zlrs = 0;//总流入数
//	var zlcs = 0;//总流出数
//	var nxrk = 0;//男性人口
//	var nvrk = 0;//女性人口
//	var cslr = 0;//出生流入
//	var bllr = 0;//补录流入
//	var qrlr = 0;//迁入流入
//	var swlc = 0;//死亡流出
//	var qclc = 0;//迁出流出
//	var sclc = 0;//删除流出
//	var nl_level1 = 0;//年龄分级1 0~20
//	var nl_level2 = 0;//年龄分级2 21~40
//	var nl_level3 = 0;//年龄分级3 41~60
//	var nl_level4 = 0;//年龄分级4 61~80
//	var nl_level5 = 0;//年龄分级5 80以上
//	var lrxl_level1 = 0;//流入学历1 文盲或半文盲
//	var lrxl_level2 = 0;//流入学历2 小学
//	var lrxl_level3 = 0;//流入学历3 初中
//	var lrxl_level4 = 0;//流入学历4 高中
//	var lrxl_level5 = 0;//流入学历5 技校
//	var lrxl_level6 = 0;//流入学历6 中专
//	var lrxl_level7 = 0;//流入学历7 大专
//	var lrxl_level8 = 0;//流入学历8 本科
//	var lrxl_level9 = 0;//流入学历9 研究生已以上
//	var lrxl_level10 = 0;//其他
//	var lrXzqh= new Array();
//	var lrCsXzqh= new Array();
//	var lrQrXzqh= new Array();
//	var lrBlXzqh= new Array();
//	
//	var lcSwXzqh= new Array();
//	var lcQcqh= new Array();
//	var lcScXzqh= new Array();
//	var benshen = 0;
//	var waishen = 0;
//	var qrsfArray= new Array();
//	var qrsfCountArray= new Array();
//     Gnt.submit(
//				{}, 
//				"echartsZlNew/queryEchartsData", 
//				"数据加载中，请稍后...", 
//				function(jsonData,params){
//					if(jsonData&&jsonData.list){
//						dataList = jsonData.list;
//						
//						var xzqhdata= ['合肥市', '芜湖市', '蚌埠市', '淮南市', '马鞍山市','淮北市', '铜陵市', '安庆市', '黄山市', '阜阳市','宿州市', '滁州市', '六安市', '宣城市', '池州市', '亳州市'];
//						var mapdata = [];
//						for(var i =0; i< xzqhdata.length ;i++){
//							var obj={
//									name:'',
//									value:''
//							};
//							var zrksCount = 0;
//							var flag = false;
//							obj.name = xzqhdata[i];
//							for(var y =0&&!flag; y< dataList.length ;y++){
//								if(xzqhdata[i]==dataList[y].ssqhmc){
//									zrksCount = parseInt(zrksCount) +parseInt(dataList[y].zrks);
//									flag = true;
//								}
//								
//							}
//							if(!flag){
//								obj.value = 0;
//								
//							}else{
//								obj.value = zrksCount;
//							}
//							mapdata.push(obj);
//						}
//						initData(dataList);
//						if(zrks>0){
//							$("#data_1_div_zrkstotal").text(zrks);
//						}
//						if(zhs>0){
//							$("#data_1_div_zhstotal").text(zhs);
//						}
//						if(zlrs>0){
//							$("#data_1_div_zlrstotal").text(zlrs);
//						}
//						if(zlcs>0){
//							$("#data_1_div_zlcstotal").text(zlcs);
//						}
//						chart1_map.showLoading();
//						chart4.showLoading();
//						chart7.showLoading();
//						chart5.showLoading();
//						chart3.showLoading();
//						chart8.showLoading();
//						chart9.showLoading();
//						chart11.showLoading();
//						chart12.showLoading();
//						GenerateChart1_map(mapdata);
//						GenerateChart4();
//						GenerateChart7();
//						GenerateChart5();
//						GenerateChart3();
//						GenerateChart8();
//						GenerateChart9();
//						GenerateChart11();
//						GenerateChart12();
//						//drawEchartS(dataList,Gnt.ux.Dict.getDictLable('XZQHB',user.dqbm+"00"));//右侧echarts填充数据
//					}
//				}
//		);
//     //function GenerateChart1_map(mapdata){};
//     //function GenerateChart1(mapdata){};
//     function GenerateChart1_map(mapdata){
//         chart1_map.hideLoading();
//         // {"合肥","芜湖","蚌埠","淮南","马鞍山","淮北","铜陵","安庆","黄山","滁州","阜阳","宿州","六安","亳州","池州","宣城"};
//         var geoCoordMap = {
//             '合肥市':[117.23401,31.831042],
//             '芜湖市':[118.336974,31.159538],
//             '蚌埠市':[117.393392,33.221134],
//             '淮南市':[116.903889,32.43281],
//             '马鞍山市':[118.513697,31.674732],
//             '淮北市':[116.805597,33.861239],
//             '安庆市':[116.182238,30.512136],
//             '黄山市':[118.340928,29.722243],
//             '滁州市':[118.339493,32.309508],
//             '阜阳市':[115.816646,32.896],
//             '宿州市':[117.475485,33.652395],
//             '六安市':[116.07692,31.501594],
//             '亳州市':[116.28821,33.351134],
//             '铜陵市':[117.626053,30.950341],
//             '池州市':[117.50057,30.269862],
//             '宣城市':[118.867927,30.600912]
//         }
//         var datamap={};
//         for(var i=0;i<mapdata.length;i++){
//             var d=mapdata[i];
//             datamap[d["name"]]=d["value"];
//         }
//         var data=[
//             {name:'合肥市',
//                 itemStyle: {
//                     normal: {
//                         areaColor: '#44b5ef',
//                         borderColor: '#edce00'
//                     }
//                 }
//             },
//             {name:'芜湖市',
//                 itemStyle: {
//                     normal: {
//                         // areaColor: '#44b5ef',
//                         // borderColor: '#0abcee'
//                         areaColor: '#17abd4',
//                         borderColor: '#30ade3'
//                     }
//                 }
//             },
//             {name:'蚌埠市',
//                 itemStyle: {
//                     normal: {
//                         areaColor: '#44b5ef',
//                         borderColor: '#1ca9f2'
//                     }
//                 }
//             },
//             {name:'淮南市',
//                 itemStyle: {
//                     normal: {
//                         // areaColor: '#ffd910',
//                         // borderColor: '#88ddf5'
//                         areaColor: '#17abd4',
//                         borderColor: '#30ade3'
//                     }
//                 }
//             },
//             {name:'马鞍山市',
//                 itemStyle: {
//                     normal: {
//                         areaColor: '#12d5ff',
//                         borderColor: '#30ade3'
//                     }
//                 }
//             },
//             {name:'淮北市',
//                 itemStyle: {
//                     normal: {
//                         areaColor: '#17abd4',
//                         borderColor: '#30ade3'
//                         // areaColor: '#ffd910',
//                         // borderColor: '#30ade3'
//                     }
//                 }
//             },
//             {name:'铜陵市',
//                 itemStyle: {
//                     normal: {
//                         // areaColor: '#44b5ef',
//                         // borderColor: '#30ade3'
//                         areaColor: '#89ddf5',
//                         borderColor: '#30ade3'
//                     }
//                 }
//             },
//             {name:'安庆市',
//                 itemStyle: {
//                     normal: {
//                         // areaColor: '#ffd910',
//                         // borderColor: '#30ade3'
//                         areaColor: '#17abd4',
//                         borderColor: '#30ade3'
//                     }
//                 }
//             },
//             {name:'黄山市',
//                 itemStyle: {
//                     normal: {
//                         areaColor: '#17abd4',
//                         borderColor: '#30ade3'
//                     }
//                 }
//             },
//             {name:'滁州市',
//                 itemStyle: {
//                     normal: {
//                         areaColor: '#58caea',
//                         borderColor: '#1ca9f2'
//                     }
//                 }
//             },
//             {name:'阜阳市',
//                 itemStyle: {
//                     normal: {
//                         areaColor: '#44b5ef',
//                         borderColor: '#88ddf5'
//                     }
//                 }
//             },
//             {name:'宿州市',
//                 itemStyle: {
//                     normal: {
//                         areaColor: '#89ddf5',
//                         borderColor: '#30ade3'
//                         // areaColor: '#ffa312',
//                         // borderColor: '#30ade3'
//                     }
//                 }
//             },
//             {name:'六安市',
//                 itemStyle: {
//                     normal: {
//                         areaColor: '#89ddf5',
//                         borderColor: '#30ade3'
//                     }
//                 }
//             },
//             {name:'亳州市',
//                 itemStyle: {
//                     normal: {
//                         areaColor: '#89ddf5',
//                         borderColor: '#30ade3'
//                     }
//                 }
//             },
//             {name:'池州市',
//                 itemStyle: {
//                     normal: {
//                         areaColor: '#44b5ef',
//                         borderColor: '#30ade3'
//                     }
//                 }
//             },
//             {name:'宣城市',
//                 itemStyle: {
//                     normal: {
//                         areaColor: '#89ddf5',
//                         borderColor: '#30ade3'
//                     }
//                 }
//             }
//         ];
//         var max = 480, min = 9; // todo
//         var maxSize4Pin = 100, minSize4Pin = 20;
//         for(var i=0;i<data.length;i++){
//             var value=datamap[data[i].name];
//             if(!value){
//                 value=0;
//             }
//             data[i].value=value;
//             if(value>max){
//                 max=value;
//             }
//             if(value<min){
//                 min=value;
//             }
//         }
//          maxSize4Pin = 100, minSize4Pin = 20;
//         var convertData = function (data) {
//             var res = [];
//             for (var i = 0; i < data.length; i++) {
//                 var geoCoord = geoCoordMap[data[i].name];
//                 if (geoCoord) {
//                     res.push({
//                         name: data[i].name,
//                         value: geoCoord.concat(data[i].value)
//                     });
//                 }
//             }
//             return res;
//         };
//         var option = {
//             // tooltip : {
//             //     trigger: 'item',
//             //     formatter: '{b}:{c}'
//             // },
//             tooltip: {
//                 trigger: 'item',
//                 formatter: function (params) {
//                     if(typeof(params.value)[2] == "undefined"){
//                         return params.name + ' : ' + params.value;
//                     }else{
//                         return params.name + ' : ' + params.value[2];
//                     }
//                 }
//             },
//             legend: {
//                 orient: 'vertical',
//                 y: 'bottom',
//                 x: 'right',
//                 data: ['合计'],
//                 textStyle: {
//                     color: '#fff'
//                 }
//             },
//             geo: {
//                 show: true,
//                 map: '安徽',
//                 top:'8%',
//                 zoom:1.2,
//                 x:'10%',
//                 label: {
//                     normal: {
//                         show: false
//                     },
//                     emphasis: {
//                         show: false,
//                     }
//                 },
//                 roam: false,
//                 itemStyle: {
//                     normal: {
//                         areaColor: '#031525',
//                         borderColor: '#3B5077',
//                     },
//                     emphasis: {
//                         areaColor: '#2B91B7',
//                     }
//                 }
//             },
//             series : [  {
//                 name: 'credit_pm2.5',
//                 type: 'scatter',
//                 coordinateSystem: 'geo',
//                 data: convertData(data),
//                 symbolSize: function (val) {
//                     return val[2]*480/max/10/2;
//                 },
//                 // label: {
//                 //     normal: {
//                 //         formatter: '{b}',
//                 //         position: 'right',
//                 //         show: true
//                 //     },
//                 //     emphasis: {
//                 //         show: true
//                 //     }
//                 // },
//                 itemStyle: {
//                     normal: {
//                         color: '#ebff00'
//                     }
//                 }
//             },{
//                 name: '点',
//                 type: 'scatter',
//                 coordinateSystem: 'geo',
//                 symbol: 'pin',
//                 symbolSize: function (val) {
//                     var a = (maxSize4Pin - minSize4Pin) / (max - min);
//                     var b = minSize4Pin - a*min;
//                     b = maxSize4Pin - a*max;
//                     return (a*val[2]+b)/2 ;
//                 },
//                 label: {
//                     normal: {
//                         show: true,
//                         formatter: function (params) {
//                             if(typeof(params.value)[2] == "undefined"){
//                                 return params.value;
//                             }else{
//                                 return params.value[2];
//                             }
//                         },
//                         textStyle: {
//                             color: '#fff',
//                             fontSize: 9,
//                         }
//                     }
//                 },
//                 itemStyle: {
//                     normal: {
//                         color: '#F62157', //标志颜色
//                     }
//                 },
//                 zlevel: 6,
//                 data: convertData(data),
//             },
//                 {
//                     name: 'Top 5',
//                     type: 'effectScatter',
//                     coordinateSystem: 'geo',
//                     data: convertData(data.sort(function (a, b) {
//                         return b.value - a.value;
//                     }).slice(0, 5)),
//                     symbolSize: function (val) {
//                         return val[2]*480/max/10/2 ;
//                     },
//                     showEffectOn: 'render',
//                     rippleEffect: {
//                         brushType: 'stroke'
//                     },
//                     hoverAnimation: true,
//                     // label: {
//                     //     normal: {
//                     //         formatter: '{b}',
//                     //         position: 'right',
//                     //         show: false
//                     //     }
//                     // },
//                     itemStyle: {
//                         normal: {
//                             color: '#ebff00',
//                             shadowBlur: 10,
//                             shadowColor: '#ebff00'
//                         }
//                     },
//                     zlevel: 1
//                 },{
//                 name: '安徽',
//                 type: 'map',
//                 mapType: '安徽',
//                 roam: false,
//                 top:'8%',
//                 zoom:1.2,
//                 x:'10%',
//                // selectedMode : 'single',//multiple多选
//                 itemStyle:{
//                     normal:{
//                         label:{
//                             show:true,
//                             position: 'left',
//                             textStyle: {
//                                 color: "#231816"
//                             }
//                         },
//                         areaStyle:{color:'#B1D0EC'},
//                         color:'#B1D0EC',
//                         borderColor:'#dadfde'//区块的边框颜色
//                     },
//                     emphasis:{//鼠标hover样式
//                         label:{
//                             show:true,
//                             position: 'left',
//                             textStyle:{
//                                 color:'#fa4f04'
//                             }
//                         },
//                         areaStyle:{color:'red'}
//                     }
//                 },
//                     data:data
//
//             }]
//         };
//         chart1_map.on('click', function(params){
//        	 alert(params.data.name);
////				var xzqh = params.data.name;
////				var list = new Array();
////				for(var i =0; i< dataList.length ;i++){
////					if(dataList[i].ssqhmc==xzqh){
////						list.push(dataList[i]);
////					}
////				}
////				drawEchartS(list,xzqh);
//			});
//         chart1_map.setOption(option);
//
//     };
//     function initData(list){
// 		for(var i = 0;i<list.length;i++){
//			var listTemp = list[i];
//			var tempXzqh = listTemp.qhmc;
//			zrks = parseInt(zrks)+parseInt(listTemp.zrks);
//			zhs = parseInt(zhs)+parseInt(listTemp.zhs);
//			zlrs = parseInt(zlrs)+parseInt(listTemp.cslr)+parseInt(listTemp.bllr)+parseInt(listTemp.qrlr);
//			zlcs = parseInt(zlcs)+parseInt(listTemp.swlc)+parseInt(listTemp.qclc)+parseInt(listTemp.sclc);
//			nxrk = parseInt(nxrk)+parseInt(listTemp.nxrk); 
//			nvrk = parseInt(nvrk)+parseInt(listTemp.nvrk);
//			nl_level1 = parseInt(nl_level1)+parseInt(listTemp.nl_level1);
//			nl_level2 = parseInt(nl_level2)+parseInt(listTemp.nl_level2);
//			nl_level3 = parseInt(nl_level3)+parseInt(listTemp.nl_level3);
//			nl_level4 = parseInt(nl_level4)+parseInt(listTemp.nl_level4);
//			nl_level5 = parseInt(nl_level5)+parseInt(listTemp.nl_level5);
//			lrxl_level1 = parseInt(lrxl_level1)+parseInt(listTemp.lrxl_level1);
//			lrxl_level2 = parseInt(lrxl_level2)+parseInt(listTemp.lrxl_level2);
//			lrxl_level3 = parseInt(lrxl_level3)+parseInt(listTemp.lrxl_level3);
//			lrxl_level4 = parseInt(lrxl_level4)+parseInt(listTemp.lrxl_level4);
//			lrxl_level5 = parseInt(lrxl_level5)+parseInt(listTemp.lrxl_level5);
//			lrxl_level6 = parseInt(lrxl_level6)+parseInt(listTemp.lrxl_level6);
//			lrxl_level7 = parseInt(lrxl_level7)+parseInt(listTemp.lrxl_level7);
//			lrxl_level8 = parseInt(lrxl_level8)+parseInt(listTemp.lrxl_level8);
//			lrxl_level9 = parseInt(lrxl_level9)+parseInt(listTemp.lrxl_level9);
//			lrxl_level10 = parseInt(lrxl_level10)+parseInt(listTemp.lrxl_level10);
//			if(lrXzqh.indexOf(tempXzqh)>=0){
//				lrCsXzqh[lrXzqh.indexOf(listTemp.ssqhmc)] = parseInt(lrCsXzqh[lrXzqh.indexOf(tempXzqh)])+parseInt(listTemp.cslr);
//				lrQrXzqh[lrXzqh.indexOf(listTemp.ssqhmc)] = parseInt(lrQrXzqh[lrXzqh.indexOf(tempXzqh)])+parseInt(listTemp.bllr);
//				lrBlXzqh[lrXzqh.indexOf(listTemp.ssqhmc)] = parseInt(lrBlXzqh[lrXzqh.indexOf(tempXzqh)])+parseInt(listTemp.qrlr);
//				
//				lcSwXzqh[lrXzqh.indexOf(listTemp.ssqhmc)] = parseInt(lcSwXzqh[lrXzqh.indexOf(tempXzqh)])+parseInt(listTemp.swlc);
//				lcQcqh[lrXzqh.indexOf(listTemp.ssqhmc)] = parseInt(lcQcqh[lrXzqh.indexOf(tempXzqh)])+parseInt(listTemp.qclc);
//				lcScXzqh[lrXzqh.indexOf(listTemp.ssqhmc)] = parseInt(lcScXzqh[lrXzqh.indexOf(tempXzqh)])+parseInt(listTemp.sclc);
//			}else{
//				lrXzqh.push(tempXzqh);
//				lrCsXzqh.push(listTemp.cslr);
//				lrQrXzqh.push(listTemp.bllr);
//				lrBlXzqh.push(listTemp.qrlr);
//				
//				lcSwXzqh.push(listTemp.swlc);
//				lcQcqh.push(listTemp.qclc);
//				lcScXzqh.push(listTemp.sclc);
//			}
//			if(listTemp.xzqhList&&listTemp.xzqhList.length>0){
//				var xzqhlist = listTemp.xzqhList;
//				for(var y = 0;y<xzqhlist.length;y++){
//					var xzqhTemp = xzqhlist[y];
//					if(xzqhTemp.qcdsdm =='340000'){
//						benshen = parseInt(benshen) + parseInt(xzqhTemp.count) ;
//					}else{
//						waishen = parseInt(waishen) + parseInt(xzqhTemp.count);
//						if(qrsfArray.indexOf(xzqhTemp.qcdsmc)>=0){
//							var obj =qrsfCountArray[qrsfArray.indexOf(xzqhTemp.qcdsmc)];
//							obj = parseInt(obj) + parseInt(xzqhTemp.count);
//							qrsfCountArray[qrsfArray.indexOf(xzqhTemp.qcdsmc)] = obj;
//						}else{
//							var obj =xzqhTemp.count;
//							qrsfArray.push(xzqhTemp.qcdsmc);
//							qrsfCountArray.push(obj);
//							
//						}
//					}
//					
//				}
//			}
//		}
//     };
//     function GenerateChart4(){
//    	chart4.hideLoading();
//		var option2 = {
//				title: {
//		            text: '',
//		            x:'center'
//		        },
//			    tooltip: {
//			        trigger: 'item',
//			        formatter: "{b}: {c} ({d}%)"
//			    },
//			    legend: {
//			        orient: 'vertical',
//			        x: 'left',
//			        data:['男','女'],
//                    textStyle : {
//                        fontSize : '10',
//                        color:'#ffffff'
//                    }
//
//			    },
//			    series: [
//			        {
//			            name:'性别饼图',
//			            type:'pie',
//			            avoidLabelOverlap: false,
//			            label: {
//			                normal: {
//			                    show: false,
//			                    position: 'center'
//			                },
//			                emphasis: {
//			                    show: true,
//			                    textStyle: {
//			                        fontSize: '30',
//			                        fontWeight: 'bold'
//			                    }
//			                }
//			            },
//			            labelLine: {
//			                normal: {
//			                    show: false
//			                }
//			            },
//			            data:[
////    			                {value:335, name:'男'},
////    			                {value:310, name:'女'}
//			            ]
//			        }
//			    ]
//			};
//    		option2.series[0].data=[{value:nxrk, name:'男'},{value:nvrk, name:'女'}];
//    		chart4.setOption(option2);
//     }
//     function GenerateChart7(){
//    	chart7.hideLoading();
//        var option6 = {
//                tooltip : {
//                    trigger: 'item',
//                    formatter: "{b}:{c}({d}%)"
//                },
//                series : [
//                    {
//                        type: 'pie',
//                        // radius : [10, 70],
//                        // center : ['75%', '50%'],
//                        minAngle: 2,           　　 //最小的扇区角度（0 ~ 360），用于防止某个值过小导致扇区太小影响交互
//                        avoidLabelOverlap: true,   //是否启用防止标签重叠策略
//                        roseType : 'area',
//                        data: [],
//                        label:{
//                            align: 'left',
//                            normal:{
//                                textStyle : {
//                                    fontSize : '10',
//                                    color:'#ffffff'
//                                }
//                            }
//                        }
//                    }
//                ]
//            };
//    	 option6.series[0].data=[{value:nl_level1, name:'0~20'},{value:nl_level2, name:'21~40'},{value:nl_level3, name:'41~60'},{value:nl_level4, name:'61~80'},{value:nl_level5, name:'80以上'}];
//    	 chart7.setOption(option6);
//     }
//     function GenerateChart5(){
//    	chart5.hideLoading();
//		var option8 = {
//				title: {
//		            text: '',
//		            x:'center'
//		        },
//			    tooltip: {
//			        trigger: 'item',
//			        formatter: "{b}: {c} ({d}%)"
//			    },
//			    legend: {
//			        orient: 'vertical',
//			        x: 'left',
//			        y:'15%',
//			        data:['本省','外省'],
//                    textStyle : {
//                        fontSize : '10',
//                        color:'#ffffff'
//                    }
//			    },
//			    series: [
//			        {
//			            name:'性别饼图',
//			            type:'pie',
//			            avoidLabelOverlap: false,
//			            label: {
//			                normal: {
//			                    show: false,
//			                    position: 'center'
//			                },
//			                emphasis: {
//			                    show: true,
//			                    textStyle: {
//			                        fontSize: '30',
//			                        fontWeight: 'bold'
//			                    }
//			                }
//			            },
//			            labelLine: {
//			                normal: {
//			                    show: false
//			                }
//			            },
//			            data:[
////    			                {value:335, name:'男'},
////    			                {value:310, name:'女'}
//			            ]
//			        }
//			    ]
//			};
//		option8.series[0].data=[{value:benshen, name:'本省'},{value:waishen, name:'外省'}];
//		chart5.setOption(option8);
//     }
//     function GenerateChart3(){
//    	chart3.hideLoading();
//		var option3 = {
//				title: {
//		            text: '',
//		            x:'center'
//		        },
//			    tooltip: {
//			        trigger: 'item',
//			        formatter: "{b}: {c} ({d}%)"
//			    },
//			    legend: {
//			        orient: 'vertical',
//			        x: 'left',
//			        data:['流入','流出'],
//                    textStyle : {
//                        fontSize : '10',
//                        color:'#ffffff'
//                    }
//			    },
//			    series: [
//			        {
//			            name:'流入流出饼图',
//			            type:'pie',
//			            avoidLabelOverlap: false,
//			            label: {
//			                normal: {
//			                    show: false,
//			                    position: 'center'
//			                },
//			                emphasis: {
//			                    show: true,
//			                    textStyle: {
//			                        fontSize: '30',
//			                        fontWeight: 'bold'
//			                    }
//			                }
//			            },
//			            labelLine: {
//			                normal: {
//			                    show: false
//			                }
//			            },
//			            data:[
////    			                {value:335, name:'流入'},
////    			                {value:310, name:'流出'}
//			            ]
//			        }
//			    ]
//		};
//		option3.series[0].data=[{value:zlrs, name:'流入'},{value:zlcs, name:'流出'}];
//		chart3.setOption(option3);
//     }
//     function GenerateChart8(){
//    	chart8.hideLoading();
//		var option9 = {
//				title: {
//		            text: '',
//		            x:'center'
//		        },
//		        tooltip : {
//			        trigger: 'axis',
//			        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
//			            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
//			        }
//			    },
//			    dataZoom:{
//			        show: true,
//			        realtime: true,
//			        textStyle:{
//		        		   fontSize:10,
//		        		   color:'#ffffff'
//		        	},
//			        y: 36,
//			        height: 20,
//			        start: 20,
//			        end: 80
//			    },
//			    grid: {
//			        left: '3%',
//			        right: '4%',
//			        bottom: '3%',
//			        containLabel: true
//			    },
//			    xAxis : [
//			        {
//			            type : 'category',
//				        axisLabel: {  
//				        	   interval:0,  
//				        	   rotate:40,
//				        	   textStyle:{
//				        		   fontSize:10,
//				        		   color:'#ffffff'
//				        	   }
//				        },
//			            data : [],
//			            axisTick: {
//			                alignWithLabel: true
//			            }
//			        }
//			    ],
//			    yAxis : [
//			        {
//			            type : 'value',
//			            splitLine:{show: false},
//			            axisLabel : {
//	                        fontSize : '10',
//	                        color:'#ffffff'
//	                    }
//			        }
//			    ],
//			    series : [
//			        {
//			            name:'迁入人数分布',
//			            type:'bar',
//			            barWidth: '60%',
////			            itemStyle: {
////				            normal: {
////				                // 随机显示
////				                color:function(d){return "#"+Math.floor(Math.random()*(256*256*256-1)).toString(16);}
////				              
////				                // 定制显示（按顺序）
//////    				                color: function(params) { 
//////    				                    var colorList = ['#C33531','#EFE42A','#64BD3D','#EE9201','#29AAE3', '#B74AE5','#0AAF9F','#E89589','#16A085','#4A235A','#C39BD3 ','#F9E79F','#BA4A00','#ECF0F1','#616A6B','#EAF2F8','#4A235A','#3498DB' ]; 
//////    				                    return colorList[params.dataIndex] 
//////    				                }
////				            }
////				        },
//			            data:[/* 20, 150, 680, 70, 110, 130,521,891,1950*/]
//			        }
//			        
//			    ]
//		};
//		option9.xAxis[0].data = qrsfArray;
//		option9.series[0].data=qrsfCountArray;
//		chart8.setOption(option9);
//     }
//     function GenerateChart9(){
//    	chart9.hideLoading();
//		var option7 =  {
//				title: {
//		            text: '',
//		            x:'center'
//		        },
//		        tooltip : {
//			        trigger: 'axis',
//			        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
//			            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
//			        }
//			    },
//			    grid: {
//			        left: '3%',
//			        right: '4%',
//			        bottom: '3%',
//			        containLabel: true
//			    },
////			    xAxis: {
////                    type: 'category',
////                    boundaryGap: false,
////                    splitLine:{show: false},//去除网格线
////                    axisLabel: {
////                        show: true,
////                        // interval:0,
////                        // rotate:50,
////                        textStyle: {
////                            ontSize: '10',
////                            color: '#ffffff' //这里用参数代替了
////                        }
////                    },
////                    data: ['文盲或半文盲', '小学', '初中', '高中', '技校', '中专', '大专', '本科','研究生及以上','其他']
////                },
//			    xAxis : [
//			        {
//			            type : 'category',
//				        axisLabel: {  
//				        	   show: true,
//				        	   interval:0,  
//				        	   rotate:40  ,
//				        	   color:'#ffffff'
//				        },
//			            data : ['文盲或半文盲', '小学', '初中', '高中', '技校', '中专', '大专', '本科','研究生及以上','其他'],
//			            axisTick: {
//			                alignWithLabel: true
//			            }
//			        }
//			    ],
//			    yAxis : [
//			        {
//			            type : 'value',
//			            splitLine:{show: false},
//			            show: true,
//			            axisLabel : {
//	                        fontSize : '10',
//	                        color:'#ffffff'
//	                    }
//			        }
//			    ],
//			    series : [
//			        {
//			            name:'流入人口学历分布',
//			            type:'bar',
//			            barWidth: '60%',
//			            data:[/* 20, 150, 680, 70, 110, 130,521,891,1950*/]
//			        }
//			    ]
//			};
//		option7.series[0].data=[lrxl_level1,lrxl_level2,lrxl_level3,lrxl_level4,lrxl_level5,lrxl_level6,lrxl_level7,lrxl_level8,lrxl_level9,lrxl_level10];
//		chart9.setOption(option7);
//     }
//     function GenerateChart11(){
//    	chart11.hideLoading();
////		var option4 =  {
////				title: {
////		            text: '',
////		            x:'left'
////		        },
////		        legend: {
////		            data: ['出生', '补录','迁入'],
////                    textStyle : {
////                        fontSize : '10',
////                        color:'#ffffff'
////                    }
////		        },
////			    tooltip: {},
////			    xAxis: [
////			        {
////			            type: 'category',
////			            axisLabel: {  
////				        	   interval:0,  
////				        	   rotate:40,
////				        	   color:'#ffffff'
////				        },
////				        splitLine:{show: false},//去除网格线
////			            axisTick: {show: false},
////			            data: [/*'合肥市', '芜湖市', '蚌埠市', '淮南市', '马鞍山市','淮北市', '铜陵市', '安庆市', '黄山市', '阜阳市','宿州市', '滁州市', '六安市', '宣城市', '池州市', '亳州市'*/]
////			        }
////			    ],
////			    yAxis: [
////			        {
////			            type: 'value',
////			            splitLine:{show: false},
////			            axisLabel : {
////	                        fontSize : '10',
////	                        color:'#ffffff'
////	                    }
////			        }
////			    ],
////			    series: [
//////    			        {
//////    			            name: '出生',
//////    			            type: 'bar',
//////    			            label: labelOption,
//////    			            data: [220, 182, 191, 234, 290,320, 332, 301, 334,150, 232, 201, 154, 190,330,550]
//////    			        },
//////    			        {
//////    			            name: '迁入',
//////    			            type: 'bar',
//////    			            label: labelOption,
//////    			            data: [150, 232, 201, 154, 190, 334, 390,880,330,550, 290,320, 332,115,852,147]
//////    			        },
//////    			        {
//////    			            name: '补录',
//////    			            type: 'bar',
//////    			            label: labelOption,
//////    			            data: [98,77,101,99,40,290,320,332,115,852,147,201,154,190,352,682]
//////    			        }
////			    ]
////		};
//    	//var xAxisdata=lrXzqh;
//
//    	var option4 = {
//                tooltip : {
//                    trigger: 'axis'
//                },
//                legend: {
//                    icon: "circle",   //  字段控制形状  类型包括 circle，rect,line，roundRect，triangle，diamond，pin，arrow，none
//                    data:['出生','迁入','补录'],
//                    x:'right',
//                    textStyle: {
//                        fontSize: '10',
//                        color: '#ffffff' //这里用参数代替了
//                    }
//                },
//                calculable : true,
//                grid: {
//                    top: '5%',
//                    left: '4%',
//                    right: '4%',
//                    bottom: '0%',
//                    containLabel: true
//                },
//                xAxis : [
//                    {
//                        type : 'category',
//                        data : [],
//                        splitLine:{show: false},//去除网格线
//                        axisLabel: {
//                            show: true,
//                            interval:0,
//                           // rotate:40,
//                            textStyle: {
//                                fontSize: '10',
//                                color: '#ffffff' //这里用参数代替了
//                            }
//                        }
//                    }
//                ],
//                yAxis : [
//                    {
//                        type : 'value',
//                        splitLine:{show: false},//去除网格线
//                        axisLabel: {
//                            show: true,
//                            textStyle: {
//                                fontSize: '10',
//                                color: '#ffffff' //这里用参数代替了
//                            }
//                        }
//                    }
//                ],
//                series:[]
//
//            };
//		option4.xAxis[0].data=lrXzqh
//		option4.series=[
//	        {
//	            name: '出生',
//	            type: 'line',
//	            label: labelOption,
//	            data: lrCsXzqh
//	        },
//	        {
//	            name: '迁入',
//	            type: 'line',
//	            label: labelOption,
//	            data: lrQrXzqh
//	        },
//	        {
//	            name: '补录',
//	            type: 'line',
//	            label: labelOption,
//	            data: lrBlXzqh
//	        }
//	    ];
//		chart11.setOption(option4);
//     } 
//     function GenerateChart12(){
//    	chart12.hideLoading();
//		var option5 = {
//                tooltip : {
//                    trigger: 'axis'
//                },
//                legend: {
//                    icon: "circle",   //  字段控制形状  类型包括 circle，rect,line，roundRect，triangle，diamond，pin，arrow，none
//                    data:['死亡','迁出','删除'],
//                    x:'right',
//                    textStyle: {
//                        fontSize: '10',
//                        color: '#ffffff' //这里用参数代替了
//                    }
//                },
//                calculable : true,
//                grid: {
//                    top: '5%',
//                    left: '4%',
//                    right: '4%',
//                    bottom: '0%',
//                    containLabel: true
//                },
//                xAxis : [
//                    {
//                        type : 'category',
//                        data : [],
//                        splitLine:{show: false},//去除网格线
//                        axisLabel: {
//                            show: true,
//                            interval:0,
//                           // rotate:40,
//                            textStyle: {
//                                fontSize: '10',
//                                color: '#ffffff' //这里用参数代替了
//                            }
//                        }
//                    }
//                ],
//                yAxis : [
//                    {
//                        type : 'value',
//                        splitLine:{show: false},//去除网格线
//                        axisLabel: {
//                            show: true,
//                            textStyle: {
//                                fontSize: '10',
//                                color: '#ffffff' //这里用参数代替了
//                            }
//                        }
//                    }
//                ],
//                series:[]
//
//            };
//		option5.xAxis[0].data=lrXzqh;
//		option5.series=[
//	        {
//	            name: '死亡',
//	            type: 'line',
//	            label: labelOption,
//	            data: lcSwXzqh
//	        },
//	        {
//	            name: '迁出',
//	            type: 'line',
//	            label: labelOption,
//	            data: lcQcqh
//	        },
//	        {
//	            name: '删除',
//	            type: 'line',
//	            label: labelOption,
//	            data: lcScXzqh
//	        }
//	    ]
//		chart12.setOption(option5);
//     }      
// 	 var labelOption = {
//		    normal: {
//		        show: false,
//		        formatter: '{c}  {name|{a}}',
//		        fontSize: 16,
//		        rich: {
//		            name: {
//		                textBorderColor: '#fff'
//		            }
//		        }
//		    }
//		};
// }); 
var url = xmdz+'echarts/echartsZlNew.jsp';//?autoLogin=1
var objShell= new ActiveXObject("WScript.Shell");
objShell.Run("cmd.exe /c start chrome \""+url+"\" ",0,true);
window.parent.parent.closeWorkSpace();