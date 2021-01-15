<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file = "config.jsp"%>
<%-- <%@ include file = "../cx/echarts_zl.jsp"%> --%>
<%
	AuthToken u = BaseContext.getUser();
	response.setHeader("Cache-Control","no-store");  
	response.setHeader("Pragrma","no-cache");  
	response.setDateHeader("Expires",0); 
%>
<!DOCTYPE HTML>
<html>
  <head>
    	<base href="<%=basePath%>">
    	<title>常口B/S版本</title>
	<link rel="stylesheet" type="text/css" href="css/report_style.css">
	<link rel="stylesheet" type="text/css" href="js/ext/css/ext-all.css">
	<link rel="stylesheet" type="text/css" href="<%=SystemConfig.getSystemConfig("default_css")%>">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
		<meta HTTP-EQUIV="pragma" CONTENT="no-cache">
 		<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
 		<meta HTTP-EQUIV="expires" CONTENT="0">		
	<style type="text/css">
	.imgfile {
	    background-image:url(images/i1.gif) !important;
	}
	.icon-exit {
	    background-image:url(images/exit.gif) !important;
	}
	.otherfile {
	    background-image:url(images/3.gif) !important;
	}
	.icon-expand-all {
	    background-image: url(images/expand-all.gif) !important;
	}
	.icon-collapse-all {
	    background-image: url(images/collapse-all.gif) !important;
	}
	.icon-expand-members {
	    background-image: url(images/expand-members.gif) !important;
	}
	.icon-hide-inherited {
	    background-image: url(images/vty16.gif) !important;
	}
	.icon-home {
	    background-image: url(images/dq.gif) !important;
	}
	
	#word-status .x-status-text {
	    color: #777;
	}
	#word-status .x-status-text-panel .spacer {
	    width: 60px;
	    font-size:0;
	    line-height:0;
	}
	</style>
  </head>
  <body>
   	<Script Lanaguage="JavaScript">
   		var user = {
   				usercode:'<%=u.getUser().getYhdlm()%>',
   				glyxm:'<%=u.getUser().getYhxm()%>',
   				yhid:'<%=u.getUser().getYhid()%>',
   				dwdm:'<%=u.getUser().getDwdm()%>',
   				glylx:'1',
   				dlsj:'<%=u.getLastaccesstime()%>'
   			}
   		
   		var root = <%=com.hzjc.menu.Menu.getMenu()%>;
   		var baseurl = '<%=basePath%>';
   		window.onbeforeunload = function() //author: meizz    
   	    {    
   			var n = window.event.screenX - window.screenLeft;
   			var b = n > document.documentElement.scrollWidth-20;
   	        if(!b &&window.event.clientY < 0|| window.event.altKey)    
   	         {    
   	      	  document.location.href = baseurl+"login/out"; 
   	         }   
   	    }
   	</Script>
	<script type="text/javascript" src="js/ext/ext.js?v=<%=Math.random()%>"></script>
	<script type="text/javascript" src="js/commFrames.js?v=<%=Math.random()%>"></script>
	<script type="text/javascript" src="js/echarts/echarts.js?v=<%=Math.random()%>"></script>
	<script type="text/javascript" src="static/index.js?v=<%=Math.random()%>"></script>
	<script type="text/javascript" src="js/yw/SelectMessageDialog.js?v=<%=Math.random()%>"></script>
   </body>
  
</html>
