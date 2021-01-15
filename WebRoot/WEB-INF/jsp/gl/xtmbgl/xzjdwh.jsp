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
    	<title>乡镇街道维护</title>
    	<link rel="stylesheet" type="text/css" href="js/ext/css/ext-all.css">
    	<link rel="stylesheet" type="text/css" href="js/ext/css/xtheme-gray.css">
    	<link rel="stylesheet" type="text/css" href="css/common.css">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
		<meta HTTP-EQUIV="pragma" CONTENT="no-cache">
 		<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
 		<meta HTTP-EQUIV="expires" CONTENT="0">	    	
  </head>
  <body>
 	<Script Lanaguage="JavaScript">
   		var user = {
   				usercode:'<%=u.getUser().getYhdlm()%>',
   				dwCode:'<%=u.getOrganize().getDm()%>',
   				userCzmj:'<%=u.getUser().getCzmj()%>',
   				isadmin:'<%=u.isAdmin()%>'
   			}
   		var basePath = '<%=basePath%>';
   	</Script>
    <script type="text/javascript" src="js/ext/ext.js?v=<%=Math.random()%>"></script>
    <script type="text/javascript" src="js/commFrames.js?v=<%=Math.random()%>"></script>
    <script type="text/javascript" src="js/SjpzStore.js?v=<%=Math.random()%>"></script>
    <script type="text/javascript" src="js/SjpzGrid.js?v=<%=Math.random()%>"></script>
    <script type="text/javascript" src="js/SjpzForm.js?v=<%=Math.random()%>"></script>
    <script type="text/javascript" src="js/yw/SelectRyxxPanel.js?v=<%=Math.random()%>"></script>
    <script type="text/javascript" src="js/yw/SelectXzjdWhModify.js?v=<%=Math.random()%>"></script>
    <script type="text/javascript" src="js/yw/SelectXzjdWhAdd.js?v=<%=Math.random()%>"></script>
    <script type="text/javascript" src="js/yw/BggzStore.js?v=<%=Math.random()%>"></script>
    <script type="text/javascript" src="js/yw/BggzGrid.js?v=<%=Math.random()%>"></script>
    <script type="text/javascript" src="static/gl/xtmbgl/xzjdwh.js?v=<%=Math.random()%>"></script>
    <script type="text/javascript" src="static/gl/xtmbgl/xtmbglform.js?v=<%=Math.random()%>"></script>
    <div id="div1"></div>
  </body>
</html>
