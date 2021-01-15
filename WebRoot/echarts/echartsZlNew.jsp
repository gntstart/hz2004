<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file = "../WEB-INF/jsp/config.jsp"%>
<html>
  <head>
		<meta charset="utf-8">
	    <title>人口总览</title>
	    <meta name="renderer" content="webkit">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	    <!-- <link rel="stylesheet" href="../css/index.css" media="all"> -->
	    <link rel="stylesheet" type="text/css" href="../css/index.css"  media="all">
	    <script src="../js/jquery.min.js"></script>
	    <script src="../js/echarts.min.js"></script>
	    <script src="../js/anhui.js"></script>
	    <script src="../js/shine.js"></script>
	    <script src="../js/macarons.js"></script>
	    <script src="../js/infographic.js"></script>
	    <script src="../js/dark.js"></script>
	    <script src="../js/roma.js"></script>
	    <script src="../js/common.js"></script>
	    <script type="text/javascript" src="../js/ext/ext.js"></script>
		<script type="text/javascript" src="../js/commFrames.js"></script>
		<script type="text/javascript" src="../static/echartsZlNew.js"></script>
  </head>
	<body>
		<div class="sne-layout">
	    <div class="sne-layout-box-top">
	        <div class="sne-layout-box-top-left"></div>
	        <div class="sne-layout-box-top-middle">
	            <span style="display:inline-block;float:right;width:250px;margin-top:20px;font-size:15px;color:yellow;" id="timego"></span>
	            <div class="sne-layout-box-top-title" id="title_head">安徽户政统一服务平台—人口数据分析
	
	            </div>
	            <div class="sne-layout-box-top-menu" id="title_menu" style="display:none;">
	                <div class="sne-layout-box-top-menu-item">
	                    <a href="echartsZlNew.jsp?theme=1">macarons</a>
	                    <a href="echartsZlNew.jsp?theme=2">shine</a>
	                    <a href="echartsZlNew.jsp?theme=3">dark</a>
	                    <a href="echartsZlNew.jsp?theme=4">infographic</a>
	                    <a href="echartsZlNew.jsp?theme=5">roma</a>
	                </div>
	            </div>
	        </div>
	        <div class="sne-layout-box-top-right"></div>
	    </div>
	    <div class="sne-layout-box-middle">
	        <div class="sne-layout-box-middle-left">
	            <div class="sne-layout-box-middle-left-img"></div>
	        </div>
	        <div class="sne-layout-box-middle-middle">
	            <!--正文内容开始-->
	            <!--左-->
	            <div class="sne-content-layout1">
	                <!--box1 start-->
	                <div class="sne-content-box">
	                    <div class="sne-content-box-top1">
	                        <div class="sne-sne-content-box-l1"></div>
	                        <div class="sne-sne-content-box-l2 h19"></div>
	                        <div class="sne-sne-content-box-l3"></div>
	                    </div>
	                    <div  class="sne-content-box-top2">
	                        <div class="sne-sne-content-box-m1">人口总览
	                            <!--<a href="#" class="sne-alink fr">详情</a>-->
	                        </div>
	                        <div class="sne-sne-content-box-m2 ">
	                            <div style="position: relative;">
	                                <div style="position: absolute;width: 168px;top: 10px;left: -30px;right: 0;bottom: 0;margin: 0 auto;text-align: left;">
	                                    <div style="color: #bfc4d7;font-size: 20px;line-height: 20px;">
	                                    	
	                                    </div>
	                                    <div style="color: #bfc4d7;font-size: 14px;line-height: 14px;">
	                                    	总人口数：
	                                    	<a style="color: #deda00;font-size: 18px;line-height: 18px;" id="data_1_div_zrkstotal">
	                                    		0
	                                    	</a>
	                                    </div>
	                                    <div style="color: #bfc4d7;font-size: 14px;line-height: 14px;">
	                                    	 总户数：
	                                    	<a style="color: #deda00;font-size: 18px;line-height: 18px;" id="data_1_div_zhstotal">
	                                    		0
	                                    	</a>
	                                    </div>
	                                    <div style="color: #bfc4d7;font-size: 14px;line-height: 14px;">
	                                    	总流入数：
	                                    	<a style="color: #deda00;font-size: 18px;line-height: 18px;" id="data_1_div_zlrstotal">
	                                    		0
	                                    	</a>
	                                    </div>
	                                    <div style="color: #bfc4d7;font-size: 14px;line-height: 14px;">
	                                    	总流出数：
	                                    	<a style="color: #deda00;font-size: 18px;line-height: 18px;" id="data_1_div_zlcstotal">
	                                    		0
	                                    	</a>
	                                    </div>
	                                </div>
	                                <!-- <div   id="data_1_div" class="liujfchart" style="height: 118px;width:271px;margin-left: -10px;"></div> -->
	                            </div>                        
	                            <!--loading-->
	                            <!--<div class="sne-loading" >-->
	                                <!--<img src="../images/loading-small.gif" alt="">-->
	                            <!--</div>-->
	                            <!--echart 1-->
	                            <!--<img src="../images/echart_1.png" alt="" >-->
	                            <!-- <div id="data_1_div" class="liujfchart" style="height: 230px;width:280px;margin-left: -10px;"></div> -->
	                        </div>
	                        <div class="sne-sne-content-box-m3"></div>
	                    </div>
	                    <div class="sne-content-box-top3">
	                        <div class="sne-sne-content-box-r1"></div>
	                        <div class="sne-sne-content-box-r2 h19"> </div>
	                        <div class="sne-sne-content-box-r3"></div>
	                    </div>
	                </div>
	                <!--box1 end-->
	                <!--box1 start-->
	                <div class="sne-content-box">
	                    <div class="sne-content-box-top1">
	                        <div class="sne-sne-content-box-l1"></div>
	                        <div class="sne-sne-content-box-l2 h82"></div>
	                        <div class="sne-sne-content-box-l3"></div>
	                    </div>
	                    <div  class="sne-content-box-top2">
	                        <div class="sne-sne-content-box-m1">总人口性别占比       
	                            <!--<a href="#" class="sne-alink fr">详情</a>-->
	                        </div>
	                        <div class="sne-sne-content-box-m2">
	                            <!--echart 3-->
	                            <!--<img src="../images/echart_3.png" alt="">-->
	                            <div   id="data_4_div" class="liujfchart2" style="height: 165px;width:241px;"></div>
	                        </div>
	                        <div class="sne-sne-content-box-m3"></div>
	                    </div>
	                    <div class="sne-content-box-top3">
	                        <div class="sne-sne-content-box-r1"></div>
	                        <div class="sne-sne-content-box-r2 h82"></div>
	                        <div class="sne-sne-content-box-r3"></div>
	                    </div>
	                </div>
	                <!--box1 end-->
	                <!--box1 start-->
	                <div class="sne-content-box">
	                    <div class="sne-content-box-top1">
	                        <div class="sne-sne-content-box-l1"></div>
	                        <div class="sne-sne-content-box-l2 h170"></div>
	                        <div class="sne-sne-content-box-l3"></div>
	                    </div>
	                    <div  class="sne-content-box-top2">
	                        <div class="sne-sne-content-box-m1">人口年龄分布
	                            <!--<a href="#" class="sne-alink fr">详情</a>-->
	                        </div>
	                        <div class="sne-sne-content-box-m2"><!--  h300 -->
	                            <!--echart 7-->
	                            <div style="position: relative;">
	                                <div style="position: absolute;width: 68px;top: 110px;left: 30px;right: 0;bottom: 0;margin: 0 auto;text-align: center;">
	                                    <div style="color: #deda00;font-size: 18px;line-height: 18px;" id="data_7_div_total">0</div>
	                                    <div style="color: #bfc4d7;font-size: 14px;line-height: 14px;">累计</div>
	                                </div>
	                                <!--echart 4-->
	                                <!--<img src="../images/echart_4.png" alt="">-->
	                                <div   id="data_7_div" class="liujfchart2" style="height:252px;width:330px;"></div>
	                            </div>
	
	                           <!--  <div   id="data_7_div" class="liujfchart2" style="height:335px;width:360px;"></div> -->
	                        </div>
	                        <div class="sne-sne-content-box-m3"></div>
	                    </div>
	                    <div class="sne-content-box-top3">
	                        <div class="sne-sne-content-box-r1"></div>
	                        <div class="sne-sne-content-box-r2 h170"></div>
	                        <div class="sne-sne-content-box-r3"></div>
	                    </div>
	                </div>
	                <!--box1 end-->
	               <!--box1 start-->
	                <div class="sne-content-box">
	                    <div class="sne-content-box-top1">
	                        <div class="sne-sne-content-box-l1"></div>
	                        <div class="sne-sne-content-box-l2 h142"></div>
	                        <div class="sne-sne-content-box-l3"></div>
	                    </div>
	                    <div  class="sne-content-box-top2">
	                        <div class="sne-sne-content-box-m1">流入人口学历分布
	                            <!--<a href="#" class="sne-alink fr">详情</a>-->
	                        </div>
	                        <div class="sne-sne-content-box-m2 h224">
	                            <!--echart 9-->
	                            <!--<img src="../images/echart_9.png" alt="">-->
	                            <div id="data_9_div" class="liujfchart" style="height: 224px;width:331px;"></div>
	                        </div>
	                        <div class="sne-sne-content-box-m3"></div>
	                    </div>
	                    <div class="sne-content-box-top3">
	                        <div class="sne-sne-content-box-r1"></div>
	                        <div class="sne-sne-content-box-r2 h142"></div>
	                        <div class="sne-sne-content-box-r3"></div>
	                    </div>
	                </div>
	                <!--box1 end-->                
	            </div>
	            <!--中-->
	            <div class="sne-content-layout2">
	                <!--box1 start-->
	                <div class="sne-content-box">
	                    <div class="sne-content-box-top1">
	                        <div class="sne-sne-content-box-l1"></div>
	                        <div class="sne-sne-content-box-l2 h464"></div>
	                        <div class="sne-sne-content-box-l3"></div>
	                    </div>
	                    <div  class="sne-content-box-top2">
	                        <div class="sne-sne-content-box-m1">全省行政分布
	                            <!--<a href="#" class="sne-alink fr">详情</a>-->
	                        </div>
	                        <div class="sne-sne-content-box-m2">
	                            <!--<div class="sne-radio">-->
	                                <!--<input id="item1" type="radio" name="item" value="行政案件" >-->
	                                <!--<label for="item1"></label>-->
	                                <!--<span>行政案件</span>-->
	                            <!--</div>-->
	                            <!--<div class="sne-radio">-->
	                                <!--<input id="item2" type="radio" name="item" value="行政案件" >-->
	                                <!--<label for="item2"></label>-->
	                                <!--<span>行政案件</span>-->
	                            <!--</div>-->
	                            <!--<div class="sne-radio">-->
	                                <!--<input id="item3" type="radio" name="item" value="当场处罚" >-->
	                                <!--<label for="item3"></label>-->
	                                <!--<span>当场处罚</span>-->
	                            <!--</div>-->
	                            <!--<div class="sne-radio">-->
	                                <!--<input id="item4" type="radio" name="item" value="民事调解" >-->
	                                <!--<label for="item4"></label>-->
	                                <!--<span>民事调解</span>-->
	                            <!--</div>-->
	                            <!--<div class="sne-radio">-->
	                                <!--<input id="item5" type="radio" name="item" value="一般民事纠纷" >-->
	                                <!--<label for="item5"></label>-->
	                                <!--<span>一般民事纠纷</span>-->
	                            <!--</div>-->
	                            <!--<div class="sne-radio">-->
	                                <!--<input id="item6" type="radio" name="item" value="公民求助" >-->
	                                <!--<label for="item6"></label>-->
	                                <!--<span>公民求助</span>-->
	                            <!--</div>-->
	                            <!--<div class="sne-radio">-->
	                                <!--<input id="item7" type="radio" name="item" value="其它" >-->
	                                <!--<label for="item7"></label>-->
	                                <!--<span>其它</span>-->
	                            <!--</div>-->
	                            <!--<div class="sne-radio">-->
	                                <!--<input id="item8" type="radio" name="item" value="所有" checked>-->
	                                <!--<label for="item8"></label>-->
	                                <!--<span>所有</span>-->
	                            <!--</div>-->
	                            <!--echart 1 map-->
	
	                            <div   id="data_map_div" style="height: 546px;width:340px;"></div>
	                            <!--<img src="../images/echart_1_map.png" alt="">-->
	                        </div>
	                        <div class="sne-sne-content-box-m3"></div>
	                    </div>
	                    <div class="sne-content-box-top3">
	                        <div class="sne-sne-content-box-r1"></div>
	                        <div class="sne-sne-content-box-r2 h464"></div>
	                        <div class="sne-sne-content-box-r3"></div>
	                    </div>
	                </div>
	                <!--box1 end-->
	                 <!--box1 start-->
	                <div class="sne-content-box">
	                    <div class="sne-content-box-top1">
	                        <div class="sne-sne-content-box-l1"></div>
	                        <div class="sne-sne-content-box-l2 h36"></div>
	                        <div class="sne-sne-content-box-l3"></div>
	                    </div>
	                    <div  class="sne-content-box-top2">
	                        <div class="sne-sne-content-box-m1">当年迁入本省与外省占比分析
	                            <!--<a href="#" class="sne-alink fr">详情</a>-->
	                        </div>
	                        <div class="sne-sne-content-box-m2">
	                            <!--echart 5-->
	                            <!--<img src="../images/echart_5.png" alt="">-->
	                            <div style="position: relative;">
	                                <div style="position: absolute;width: 68px;top: 50px;left: 10px;right: 0;bottom: 0;margin: 0 auto;text-align: center;">
	                                    <div style="color: #deda00;font-size: 18px;line-height: 18px;" id="data_5_div_total"></div>
	                                    <div style="color: #bfc4d7;font-size: 14px;line-height: 14px;"></div>
	                                </div>
	                                <!--echart 4-->
	                                <!--<img src="../images/echart_4.png" alt="">-->
	                                <div   id="data_5_div" class="liujfchart2" style="height: 118px;width:271px;"></div>
	                            </div>
	                        </div>
	                        <div class="sne-sne-content-box-m3"></div>
	                    </div>
	                    <div class="sne-content-box-top3">
	                        <div class="sne-sne-content-box-r1"></div>
	                        <div class="sne-sne-content-box-r2 h36"></div>
	                        <div class="sne-sne-content-box-r3"></div>
	                    </div>
	                </div>
	                <!--box1 end-->
	                <!--box1 start-->
	                <div class="sne-content-box">
	                    <div class="sne-content-box-top1">
	                        <div class="sne-sne-content-box-l1"></div>
	                        <div class="sne-sne-content-box-l2 h36"></div>
	                        <div class="sne-sne-content-box-l3"></div>
	                    </div>
	                    <div  class="sne-content-box-top2">
	                        <div class="sne-sne-content-box-m1">当年流入流出占比
	                            <!--<a href="#" class="sne-alink fr">详情</a>-->
	                        </div>
	                        <div class="sne-sne-content-box-m2">
	                            <!--echart 12-->
	                            <!--<img src="../images/echart_12.png" alt="">-->
	                             <div style="position: relative;">
	                                <div style="position: absolute;width: 68px;top: 50px;left: 10px;right: 0;bottom: 0;margin: 0 auto;text-align: center;">
	                                    <div style="color: #deda00;font-size: 18px;line-height: 18px;" id="data_3_div_total"></div>
	                                    <div style="color: #bfc4d7;font-size: 14px;line-height: 14px;"></div>
	                                </div>
	                                <!--echart 4-->
	                                <!--<img src="../images/echart_4.png" alt="">-->
	                                <div id="data_3_div"  class="liujfchart2" style="height: 118px;width:271px;"></div>
	                            </div>
	                           
	                        </div>
	                        <div class="sne-sne-content-box-m3"></div>
	                    </div>
	                    <div class="sne-content-box-top3">
	                        <div class="sne-sne-content-box-r1"></div>
	                        <div class="sne-sne-content-box-r2 h36"></div>
	                        <div class="sne-sne-content-box-r3"></div>
	                    </div>
	                </div>
	                <!--box1 end-->
	            </div>
	            <!--右-->
	            <div class="sne-content-layout3">
	               <!--box1 start-->
	                <div class="sne-content-box">
	                    <div class="sne-content-box-top1">
	                        <div class="sne-sne-content-box-l1"></div>
	                        <div class="sne-sne-content-box-l2 h178"></div>
	                        <div class="sne-sne-content-box-l3"></div>
	                    </div>
	                    <div  class="sne-content-box-top2">
	                        <div class="sne-sne-content-box-m1">当年流出人口
	                            <!--<a href="#" class="sne-alink fr">详情</a>-->
	                        </div>
	                        <div class="sne-sne-content-box-m2 h260">
	                            <!--echart 12-->
	                            <!--<img src="../images/echart_12.png" alt="">-->
	                            <div id="data_12_div"  class="liujfdiv liujfchart" style="height: 260px;width:690px;"></div>
	                        </div>
	                        <div class="sne-sne-content-box-m3"></div>
	                    </div>
	                    <div class="sne-content-box-top3">
	                        <div class="sne-sne-content-box-r1"></div>
	                        <div class="sne-sne-content-box-r2 h178"></div>
	                        <div class="sne-sne-content-box-r3"></div>
	                    </div>
	                </div>
	                <!--box1 end-->
	                 <!--box1 start-->
	                <div class="sne-content-box">
	                    <div class="sne-content-box-top1">
	                        <div class="sne-sne-content-box-l1"></div>
	                        <div class="sne-sne-content-box-l2 h178"></div>
	                        <div class="sne-sne-content-box-l3"></div>
	                    </div>
	                    <div  class="sne-content-box-top2">
	                        <div class="sne-sne-content-box-m1">当前迁入省份柱状图
	                            <!--<a href="#" class="sne-alink fr">详情</a>-->
	                        </div>
	                        <div class="sne-sne-content-box-m2 h260">
	                            <!--echart 8-->
	                            <!--<img src="../images/echart_8.png" alt="">-->
	                            <div id="data_8_div" class="liujfdiv liujfchart" style="height: 260px;width:690px;"></div>
	                        </div>
	                        <div class="sne-sne-content-box-m3"></div>
	                    </div>
	                    <div class="sne-content-box-top3">
	                        <div class="sne-sne-content-box-r1"></div>
	                        <div class="sne-sne-content-box-r2 h178"></div>
	                        <div class="sne-sne-content-box-r3"></div>
	                    </div>
	                </div>
	                <!--box1 end-->
	                <!--box1 start-->
	                <div class="sne-content-box">
	                    <div class="sne-content-box-top1">
	                        <div class="sne-sne-content-box-l1"></div>
	                        <div class="sne-sne-content-box-l2 h178"></div>
	                        <div class="sne-sne-content-box-l3"></div>
	                    </div>
	                    <div  class="sne-content-box-top2">
	                        <div class="sne-sne-content-box-m1">当年流入人口
	                            <!--<a href="#" class="sne-alink fr">详情</a>-->
	                        </div>
	                        <div class="sne-sne-content-box-m2 h260">
	                            <!--echart 9-->
	                            <!--<img src="../images/echart_9.png" alt="">-->
	                            <div id="data_11_div" class="liujfdiv liujfchart" style="height: 260px;width:690px;"></div>
	                        </div>
	                        <div class="sne-sne-content-box-m3"></div>
	                    </div>
	                    <div class="sne-content-box-top3">
	                        <div class="sne-sne-content-box-r1"></div>
	                        <div class="sne-sne-content-box-r2 h178"></div>
	                        <div class="sne-sne-content-box-r3"></div>
	                    </div>
	                </div>
					<!--box1 end-->
	               
	                <!--<div style="width:100%;">-->
	                    <!--<div style="width:49.5%;display:inline-block ;float:left;">-->
	                        <!--&lt;!&ndash;box1 start&ndash;&gt;-->
	                        <!--<div class="sne-content-box">-->
	                            <!--<div class="sne-content-box-top1">-->
	                                <!--<div class="sne-sne-content-box-l1"></div>-->
	                                <!--<div class="sne-sne-content-box-l2 h75"></div>-->
	                                <!--<div class="sne-sne-content-box-l3"></div>-->
	                            <!--</div>-->
	                            <!--<div  class="sne-content-box-top2">-->
	                                <!--<div class="sne-sne-content-box-m1">案件审核情况-->
	                                    <!--&lt;!&ndash;<a href="#" class="sne-alink fr">详情</a>&ndash;&gt;-->
	                                <!--</div>-->
	                                <!--<div class="sne-sne-content-box-m2 h157">-->
	                                    <!--&lt;!&ndash;echart 10&ndash;&gt;-->
	                                    <!--&lt;!&ndash;<img src="../images/echart_10.png" alt="">&ndash;&gt;-->
	                                    <!--<div id="data_10_div" class="liujfdiv2 liujfchart" style="height: 157px;width:330px;"></div>-->
	                                <!--</div>-->
	                                <!--<div class="sne-sne-content-box-m3"></div>-->
	                            <!--</div>-->
	                            <!--<div class="sne-content-box-top3">-->
	                                <!--<div class="sne-sne-content-box-r1"></div>-->
	                                <!--<div class="sne-sne-content-box-r2 h75"></div>-->
	                                <!--<div class="sne-sne-content-box-r3"></div>-->
	                            <!--</div>-->
	                        <!--</div>-->
	                        <!--&lt;!&ndash;box1 end&ndash;&gt;-->
	
	
	
	                    <!--</div>-->
	                    <!--<div  style="width:49.5%;display:inline-block ;float:right;">-->
	                        <!--&lt;!&ndash;box1 start&ndash;&gt;-->
	                        <!--<div class="sne-content-box">-->
	                            <!--<div class="sne-content-box-top1">-->
	                                <!--<div class="sne-sne-content-box-l1"></div>-->
	                                <!--<div class="sne-sne-content-box-l2 h75"></div>-->
	                                <!--<div class="sne-sne-content-box-l3"></div>-->
	                            <!--</div>-->
	                            <!--<div  class="sne-content-box-top2">-->
	                                <!--<div class="sne-sne-content-box-m1">民警执法资格情况-->
	                                    <!--&lt;!&ndash;<a href="#" class="sne-alink fr">详情</a>&ndash;&gt;-->
	                                <!--</div>-->
	                                <!--<div class="sne-sne-content-box-m2 h157">-->
	                                    <!--&lt;!&ndash;echart 13&ndash;&gt;-->
	                                    <!--&lt;!&ndash;<img src="../images/echart_13.png" alt="">&ndash;&gt;-->
	                                    <!--<div id="data_13_div" class="liujfdiv2 liujfchart" style="height: 157px;width:330px;"></div>-->
	                                <!--</div>-->
	                                <!--<div class="sne-sne-content-box-m3"></div>-->
	                            <!--</div>-->
	                            <!--<div class="sne-content-box-top3">-->
	                                <!--<div class="sne-sne-content-box-r1"></div>-->
	                                <!--<div class="sne-sne-content-box-r2 h75"></div>-->
	                                <!--<div class="sne-sne-content-box-r3"></div>-->
	                            <!--</div>-->
	                        <!--</div>-->
	                        <!--&lt;!&ndash;box1 end&ndash;&gt;-->
	
		
		
		                    <!--</div>-->
		                <!--</div>-->
		            </div>
		        </div>
		        <div class="sne-layout-box-middle-right"></div>
		    </div>
		    <div class="sne-layout-box-bottom">
		        <div class="sne-layout-box-bottom-left"></div>
		        <div class="sne-layout-box-bottom-middle">
		        </div>
		        <div class="sne-layout-box-bottom-right"></div>
		    </div>
		
		</div>
	</body>
  
</html>
