<%@page import="com.hzjc.hz2004.base.login.LoginFilter"%>
<%@page trimDirectiveWhitespaces="true" %>
<%@page import="java.util.*"%>
<%@page import="java.net.*"%>
<%@page import="com.hzjc.hz2004.base.*,com.hzjc.hz2004.util.*,com.hzjc.hz2004.base.login.*,com.hzjc.util.*,com.hzjc.hz2004.po.PoXT_YHSJFWB,com.hzjc.hz2004.po.PoPERSON_DY_SET,com.hzjc.hz2004.po.PoXT_XTGNB"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ path+"/";
	String sysDate = StringUtils.getServiceDate();
	String dkqlx = "2";//读卡器默认为2 华视
	int ryxxsdFlag = 0;
	int ryxxjsFlag = 0;
	AuthToken authToken = BaseContext.getUser();
	if(authToken!=null){
		PoPERSON_DY_SET poPERSON_DY_SET = authToken.getPersonDySet();
		if(poPERSON_DY_SET!=null){
			dkqlx = poPERSON_DY_SET.getDkqlx();
		}
		if(authToken.isAdmin()){
			ryxxsdFlag = 1;
			ryxxjsFlag = 1;
		}else{
			List<PoXT_XTGNB>  xtgnb = authToken.getXtgn();
			for(PoXT_XTGNB gn:xtgnb){
				if(gn.getGnbh().equals("F0000")){//人员信息锁定
					ryxxsdFlag = 1;
				}else if(gn.getGnbh().equals("F0001")){//人员信息解锁
					ryxxjsFlag = 1;
				}
			}
		}
	}
	response.setHeader("Cache-Control","no-store");  
	response.setHeader("Pragrma","no-cache");  
	response.setDateHeader("Expires",0); 
%>
<Script Lanaguage="JavaScript">
	var xmdz = '<%=basePath%>';
	var sysDate = '<%=sysDate%>';
	/** 
		2018.10.25
		æ°å¢	åæ°
		è¿åºå°çå¸å¿åº - å®å¾½ç
		è¿å¥ä¸å¡/è¿å¥å®¡æ¹ä½¿ç¨
	*/
	var sysQcdq = "34";
	var dkqlx = '<%=dkqlx%>';
	var ryxxsdFlag = '<%=ryxxsdFlag%>';
	var ryxxjsFlag = '<%=ryxxjsFlag%>';
	var baseurl = '<%=basePath%>';
	function forbidBackSpace(e) {
		   var ev = e || window.event; //获取event对象 
		   var obj = ev.target || ev.srcElement; //获取事件源 
		   var t = obj.type || obj.getAttribute('type'); //获取事件源类型 
		   //获取作为判断条件的事件类型 
		   var vReadOnly = obj.readOnly;
		   var vDisabled = obj.disabled;
		   //处理undefined值情况 
		   vReadOnly = (vReadOnly == undefined) ? false : vReadOnly;
		   vDisabled = (vDisabled == undefined) ? true : vDisabled;
		   //当敲Backspace键时，事件源类型为密码或单行、多行文本的， 
		   //并且readOnly属性为true或disabled属性为true的，则退格键失效 
		   var flag1 = ev.keyCode == 8 && (t == "password" || t == "text" || t == "textarea") && (vReadOnly == true || vDisabled == true);
		   //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效 
		   var flag2 = ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea";
		   //判断 
		   if (flag2 || flag1) return false;
		};
		//禁止后退键 作用于Firefox、Opera
		document.onkeypress = forbidBackSpace;
		//禁止后退键  作用于IE、Chrome
		document.onkeydown = forbidBackSpace;
	 
</Script>