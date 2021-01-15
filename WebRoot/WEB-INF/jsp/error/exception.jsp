<%@ page isErrorPage="true" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file = "../config.jsp"%>
<%
	AuthToken u = BaseContext.getUser();
%>
<!DOCTYPE HTML>
<html>
  <head>
    	<base href="<%=basePath%>">
    	<title>常口B/S版本</title>
	<link rel="stylesheet" type="text/css" href="css/report_style.css">
	<link rel="stylesheet" type="text/css" href="js/ext/css/ext-all.css">
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.getSystemConfig("default_css")%>">
  </head>
  <body>
   	对不起，出错了！
   	<hr/>
   	<% exception.printStackTrace(response.getWriter()); %>
  </body>
</html>
