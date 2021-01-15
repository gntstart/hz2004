<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file = "../config.jsp"%>
<%
	AuthToken u = BaseContext.getUser();
	response.setHeader("Cache-Control","no-store");  
	response.setHeader("Pragrma","no-cache");  
	response.setDateHeader("Expires",0); 
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">

<html>
  <head>
		<base href="<%=basePath%>">
    	<title>人口总览</title>
		<style type="text/css">
			#main {
			    /* border:1px solid #D3D3D3; */
			}
			#header {
			    color:white;
			    text-align:center;
			}
			#nav {
			    line-height:30px;
			    height:500px;
			    width:30%;
			    float:left;
			    padding:5px; 
			}
			#section {
			    width:68%;
			    float:right;
			    padding:5px; 
			}
			#section div{
				
				padding:1px;
				color:white;
			}
			#section div h4{
				padding:1px;
				color:white;
			}
			#section div a{
				width:35%;
				color:white;
			}
			.southPart {
			/* height: 1000px; */
			width: 100%;
			}
			.right{
			float:right; 
			width:45%; 
			height:200px;
			}
			.mid{
			float:right;
			width:200px; 
			height:200px;
			}
			.left{
			float:left; 
			width:45%; 
			height:200px;
			}

			#footer {
			    background-color:black;
			    color:white;
			    clear:both;
			    text-align:center;
			    padding:5px; 
			}
			.div-empty{ border:1px solid #333333;width:100%; height:200px}
		</style>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
		<meta HTTP-EQUIV="pragma" CONTENT="no-cache">
 		<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
 		<meta HTTP-EQUIV="expires" CONTENT="0">	
  </head>
  <body  style="background-color:#333333">
  <div id="main">
   	<div id="header">
	<h2 id="biaoti"></h2>
	</div>
   	<div id="nav">
		<div id="chart_panel1" style="width:350px;height:500px"></div>
		<div id="chart_panel8" style="width:350px;height:200px"></div>
		<div id='chart_panel3' style="width:350px;height:200px"></div>
	</div>
	<div id="section">
		<div><h4>人口总览</h4></div>
		
		<div>总人口数：<a id='zrks'>0</a>总流入数：<a id='zlrs'>0</a><br>
		总户数：<a id='zhs'>0</a>总流出数：<a id='zlcs'>0</a></div>
		<div class='div-empty'/>
		<div class='left' id="chart_panel2" style="width:45%;height:200px;padding:5px;"></div>
		<div class="right"id="chart_panel6" style="width:45%;height:200px;padding:5px;"></div>
		
		<div class='southPart' >
			<div id="chart_panel9" style="width:100%;height:200px"></div>
			<div id='chart_panel7' style="width:100%;height:200px"></div>
			<div id="chart_panel4" style="width:100%;height:200px"></div>
			<div id='chart_panel5' style="width:100%;height:200px;"></div>
		</div>
	</div>   
  </div>
	<script type="text/javascript">
			var user = {
	   				usercode:'<%=u.getUser().getYhdlm()%>',
	   				dqbm:'<%=u.getUser().getDqbm()%>'
	   			};
	</script>
   	<script type="text/javascript" src="js/ext/ext.js?v=<%=Math.random()%>"></script>
	<script type="text/javascript" src="js/commFrames.js?v=<%=Math.random()%>"></script>
	<script type="text/javascript" src="js/jquery-1.7.2-min.js?v=<%=Math.random()%>"></script>
	<script type="text/javascript" src="js/echarts/echarts.js?v=<%=Math.random()%>"></script>
	<script type="text/javascript" src="static/cx/echartsZl.js?v=<%=Math.random()%>"></script>
	<!-- <script type="text/javascript" src="../js/dark.js?v=<%=Math.random()%>" charset="utf-8"></script> -->
   </body>
  
</html>
