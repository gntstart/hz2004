<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file = "../../config.jsp"%>
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
    	<title>迁移证打印</title>
    	<link rel="stylesheet" type="text/css" href="js/ext/css/ext-all.css">
    	<link rel="stylesheet" type="text/css" href="js/ext/css/xtheme-gray.css">
    	<link rel="stylesheet" type="text/css" href="css/common.css">
 		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
		<meta HTTP-EQUIV="pragma" CONTENT="no-cache">
 		<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
 		<meta HTTP-EQUIV="expires" CONTENT="0">	   	
  </head>
  <body>
    <script type="text/javascript" src="js/ext/ext.js?v=<%=Math.random()%>"></script>
    <script type="text/javascript" src="js/commFrames.js?v=<%=Math.random()%>"></script>
    <script type="text/javascript" src="js/SjpzStore.js?v=<%=Math.random()%>"></script>
    <script type="text/javascript" src="js/SjpzGrid.js?v=<%=Math.random()%>"></script>
    <script type="text/javascript" src="js/SjpzForm.js?v=<%=Math.random()%>"></script>
    <script type="text/javascript" src="js/yw/SelectRyxxPanel.js?v=<%=Math.random()%>"></script>
    <script type="text/javascript" src="js/yw/BggzStore.js?v=<%=Math.random()%>"></script>
    <script type="text/javascript" src="js/yw/BggzGrid.js?v=<%=Math.random()%>"></script>
    <script type="text/javascript" src="js/yw/SelectQyz.js?v=<%=Math.random()%>"></script>
	<script type="text/javascript" src="js/yw/SelectQyzValidForm.js?v=<%=Math.random()%>"></script>
    <script type="text/javascript" src="static/yw/bbdy/qyzdy.js?v=<%=Math.random()%>"></script>
    <script type="text/javascript" src="js/lodop/CheckActivX.js?v=<%=Math.random()%>"></script>
    <object id="LODOP" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0></object>
    <div id="div1"></div>
  </body>
</html>
