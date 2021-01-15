<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.hzjc.hz2004.base.SystemConfig" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ path+"/";
%>
<!DOCTYPE HTML>
<html>
  <head>
  	<!-- Include Ext and app-specific scripts: -->
   	<Script Lanaguage="JavaScript">
   		
   		
   	
   		var baseurl = '<%=basePath%>';
   	</Script>
    	<base href="<%=basePath%>">
    	<title>常口B/S版本</title>
	<link rel="stylesheet" type="text/css" href="css/report_style.css">
	<link rel="stylesheet" type="text/css" href="js/ext/css/ext-all.css">
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.getSystemConfig("default_css")%>">
	
  </head>
  <body>
  
  <OBJECT ID="ImageCtrl" style="position:fixed;left:10px" WIDTH=130 HEIGHT=160 classid="CLSID:A4E862CA-1750-453B-A2FE-97006D5246D4" codebase="<%=basePath%>js/lodop/ImageCtl.CAB#version=1,0,0,13">
    <PARAM NAME="_Version" VALUE="65536">
    <PARAM NAME="_ExtentX" VALUE="12806">
    <PARAM NAME="_ExtentY" VALUE="1747">
    <PARAM NAME="_StockProps" VALUE="0">
</OBJECT><br><br><br><br><br><br><br><br>

<br>
   <a href="<%=basePath%>js/lodop/jcrk_setup.exe">下载照片采集控件</a><br>
    <a href="<%=basePath%>js/lodop/yjsz.zip">浏览器一键设置</a><br>
    <a href="<%=basePath%>js/lodop/CellWeb53.exe">下载报表控件</a><br>

	<script type="text/javascript" src="js/ext/ext.js"></script>
	
  </body>
</html>
