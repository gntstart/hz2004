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
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
  	<base href="<%=basePath%>">
    	<title>住址变动</title>
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
   				yhdlm:'<%=u.getUser().getYhdlm()%>',
   				gmsfhm:'<%=u.getUser().getGmsfhm()%>',
   				flag:'<%=u.getUser().getFlag()%>',
   				personSet:{
   					dyyl_dysz:'<%=u.getPersonDySet().getDyyl_dysz()%>',
   					tcdysz_dysz:'<%=u.getPersonDySet().getTcdysz_dysz()%>',
   					dyzp_cbsz:'<%=u.getPersonDySet().getDyzp_cbsz()%>',
   					jth_syksz:'<%=u.getPersonDySet().getJth_syksz()%>',
   					hkbsy_hkbsz:'<%=u.getPersonDySet().getHkbsy_hkbsz()%>',
   					hkbbm_hkbsz:'<%=u.getPersonDySet().getHkbbm_hkbsz()%>',
   					jthfshksy_hkbsz:'<%=u.getPersonDySet().getJthfshksy_hkbsz()%>',
   					jthfshky_hkbsz:'<%=u.getPersonDySet().getJthfshky_hkbsz()%>',
   					csyy_hkbsz:'<%=u.getPersonDySet().getCsyy_hkbsz()%>',
   					dyzp_hjzmsz:'<%=u.getPersonDySet().getDyzp_hjzmsz()%>',
   					hcyxx_hjzmsz:'<%=u.getPersonDySet().getHcyxx_hjzmsz()%>',
   					bdyy_hjzmsz:'<%=u.getPersonDySet().getBdyy_hjzmsz()%>',
   					bdxx_hjzmsz:'<%=u.getPersonDySet().getBdxx_hjzmsz()%>',
   					zxryxx_hjzmsz:'<%=u.getPersonDySet().getZxryxx_hjzmsz()%>',
   					dydw_hjzmsz:'<%=u.getPersonDySet().getDydw_hjzmsz()%>',
   					dyhh_hjzmsz:'<%=u.getPersonDySet().getDyhh_hjzmsz()%>',
   					dyhyzk_hjzmsz:'<%=u.getPersonDySet().getDyhyzk_hjzmsz()%>',
   					dybyqk_hjzmsz:'<%=u.getPersonDySet().getDybyqk_hjzmsz()%>',
   					dywhcd_hjzmsz:'<%=u.getPersonDySet().getDywhcd_hjzmsz()%>',
   					hjsysf:'<%=u.getPersonDySet().getHjsysf()%>',
   					zqzsf:'<%=u.getPersonDySet().getZqzsf()%>',
   					qyzsf:'<%=u.getPersonDySet().getQyzsf()%>'
   				}
   			}
   		var basePath = '<%=basePath%>';
   	</Script>
    <script type="text/javascript" src="js/ext/ext.js?v=<%=Math.random()%>"></script>
    <script type="text/javascript" src="js/jquery.min.js?v=<%=Math.random()%>"></script>
    <script type="text/javascript" src="js/jQuery.XDomainRequest.js?v=<%=Math.random()%>"></script>
    <script type="text/javascript" src="js/pjq.js?v=<%=Math.random()%>"></script>
    <script type="text/javascript" src="js/commFrames.js?v=<%=Math.random()%>"></script>
    <script type="text/javascript" src="js/SjpzStore.js?v=<%=Math.random()%>"></script>
    <script type="text/javascript" src="js/SjpzGrid.js?v=<%=Math.random()%>"></script>
    <script type="text/javascript" src="js/SjpzForm.js?v=<%=Math.random()%>"></script>
    <script type="text/javascript" src="js/valid.js?v=<%=Math.random()%>"></script>
    <script type="text/javascript" src="js/yw/SelectRh.js?v=<%=Math.random()%>"></script>
    <script type="text/javascript" src="js/yw/SelectBzdz.js?v=<%=Math.random()%>"></script>
    <script type="text/javascript" src="js/yw/SelectRyxxPanel.js?v=<%=Math.random()%>"></script>
    <script type="text/javascript" src="js/yw/BggzStore.js?v=<%=Math.random()%>"></script>
    <script type="text/javascript" src="js/yw/BggzGrid.js?v=<%=Math.random()%>"></script>
    <script type="text/javascript" src="js/yw/PrintDialog.js?v=<%=Math.random()%>"></script>
    <script type="text/javascript" src="js/yw/SelectZdydyForm.js?v=<%=Math.random()%>"></script>
    <script type="text/javascript" src="js/yw/SelectQyz.js?v=<%=Math.random()%>"></script>
    <script type="text/javascript" src="js/lodop/CheckActivX.js?v=<%=Math.random()%>"></script>
    <script type="text/javascript" src="js/readCard/readCard.js?v=<%=Math.random()%>"></script>
    <script type="text/javascript" src="static/yw/hjyw/zzbd.js?v=<%=Math.random()%>"></script>
    <object id="LODOP" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0></object>
    <div id="div1"></div>
  </body>
</html>
