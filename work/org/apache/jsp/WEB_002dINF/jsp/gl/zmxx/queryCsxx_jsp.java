/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.8
 * Generated at: 2020-06-11 08:52:01 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp.gl.zmxx;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import com.hzjc.hz2004.base.login.LoginFilter;
import java.util.*;
import java.net.*;
import com.hzjc.hz2004.base.*;
import com.hzjc.hz2004.util.*;
import com.hzjc.hz2004.base.login.*;
import com.hzjc.util.*;
import com.hzjc.hz2004.po.PoXT_YHSJFWB;
import com.hzjc.hz2004.po.PoPERSON_DY_SET;
import com.hzjc.hz2004.po.PoXT_XTGNB;

public final class queryCsxx_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/WEB-INF/jsp/gl/zmxx/../../config.jsp", Long.valueOf(1591061446418L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("com.hzjc.hz2004.base");
    _jspx_imports_packages.add("java.util");
    _jspx_imports_packages.add("java.net");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("com.hzjc.hz2004.base.login");
    _jspx_imports_packages.add("com.hzjc.hz2004.util");
    _jspx_imports_packages.add("com.hzjc.util");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("com.hzjc.hz2004.po.PoXT_YHSJFWB");
    _jspx_imports_classes.add("com.hzjc.hz2004.po.PoXT_XTGNB");
    _jspx_imports_classes.add("com.hzjc.hz2004.po.PoPERSON_DY_SET");
    _jspx_imports_classes.add("com.hzjc.hz2004.base.login.LoginFilter");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;


	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ path+"/";
	String sysDate = StringUtils.getServiceDate();
	String dkqlx = "2";//è¯»å¡å¨é»è®¤ä¸º2 åè§
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
				if(gn.getGnbh().equals("F0000")){//äººåä¿¡æ¯éå®
					ryxxsdFlag = 1;
				}else if(gn.getGnbh().equals("F0001")){//äººåä¿¡æ¯è§£é
					ryxxjsFlag = 1;
				}
			}
		}
	}

      out.write("<Script Lanaguage=\"JavaScript\">\r\n");
      out.write("\tvar xmdz = '");
      out.print(basePath);
      out.write("';\r\n");
      out.write("\tvar sysDate = '");
      out.print(sysDate);
      out.write("';\r\n");
      out.write("\t/** \r\n");
      out.write("\t\t2018.10.25\r\n");
      out.write("\t\tÃ¦ÂÂ°Ã¥Â¢Â\tÃ¥ÂÂÃ¦ÂÂ°\r\n");
      out.write("\t\tÃ¨Â¿ÂÃ¥ÂÂºÃ¥ÂÂ°Ã§ÂÂÃ¥Â¸ÂÃ¥ÂÂ¿Ã¥ÂÂº - Ã¥Â®ÂÃ¥Â¾Â½Ã§ÂÂ\r\n");
      out.write("\t\tÃ¨Â¿ÂÃ¥ÂÂ¥Ã¤Â¸ÂÃ¥ÂÂ¡/Ã¨Â¿ÂÃ¥ÂÂ¥Ã¥Â®Â¡Ã¦ÂÂ¹Ã¤Â½Â¿Ã§ÂÂ¨\r\n");
      out.write("\t*/\r\n");
      out.write("\tvar sysQcdq = \"34\";\r\n");
      out.write("\tvar dkqlx = '");
      out.print(dkqlx);
      out.write("';\r\n");
      out.write("\tvar ryxxsdFlag = '");
      out.print(ryxxsdFlag);
      out.write("';\r\n");
      out.write("\tvar ryxxjsFlag = '");
      out.print(ryxxjsFlag);
      out.write("';\r\n");
      out.write("\tvar baseurl = '");
      out.print(basePath);
      out.write("';\r\n");
      out.write("\tfunction forbidBackSpace(e) {\r\n");
      out.write("\t\t   var ev = e || window.event; //è·åeventå¯¹è±¡ \r\n");
      out.write("\t\t   var obj = ev.target || ev.srcElement; //è·åäºä»¶æº \r\n");
      out.write("\t\t   var t = obj.type || obj.getAttribute('type'); //è·åäºä»¶æºç±»å \r\n");
      out.write("\t\t   //è·åä½ä¸ºå¤æ­æ¡ä»¶çäºä»¶ç±»å \r\n");
      out.write("\t\t   var vReadOnly = obj.readOnly;\r\n");
      out.write("\t\t   var vDisabled = obj.disabled;\r\n");
      out.write("\t\t   //å¤çundefinedå¼æåµ \r\n");
      out.write("\t\t   vReadOnly = (vReadOnly == undefined) ? false : vReadOnly;\r\n");
      out.write("\t\t   vDisabled = (vDisabled == undefined) ? true : vDisabled;\r\n");
      out.write("\t\t   //å½æ²Backspaceé®æ¶ï¼äºä»¶æºç±»åä¸ºå¯ç æåè¡ãå¤è¡ææ¬çï¼ \r\n");
      out.write("\t\t   //å¹¶ä¸readOnlyå±æ§ä¸ºtrueædisabledå±æ§ä¸ºtrueçï¼åéæ ¼é®å¤±æ \r\n");
      out.write("\t\t   var flag1 = ev.keyCode == 8 && (t == \"password\" || t == \"text\" || t == \"textarea\") && (vReadOnly == true || vDisabled == true);\r\n");
      out.write("\t\t   //å½æ²Backspaceé®æ¶ï¼äºä»¶æºç±»åéå¯ç æåè¡ãå¤è¡ææ¬çï¼åéæ ¼é®å¤±æ \r\n");
      out.write("\t\t   var flag2 = ev.keyCode == 8 && t != \"password\" && t != \"text\" && t != \"textarea\";\r\n");
      out.write("\t\t   //å¤æ­ \r\n");
      out.write("\t\t   if (flag2 || flag1) return false;\r\n");
      out.write("\t\t};\r\n");
      out.write("\t\t//ç¦æ­¢åéé® ä½ç¨äºFirefoxãOpera\r\n");
      out.write("\t\tdocument.onkeypress = forbidBackSpace;\r\n");
      out.write("\t\t//ç¦æ­¢åéé®  ä½ç¨äºIEãChrome\r\n");
      out.write("\t\tdocument.onkeydown = forbidBackSpace;\r\n");
      out.write("\t \r\n");
      out.write("</Script>");

	AuthToken u = BaseContext.getUser();

      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("  <head>\r\n");
      out.write("  \t<base href=\"");
      out.print(basePath);
      out.write("\">\r\n");
      out.write("    \t<title>出生信息</title>\r\n");
      out.write("    \t<link rel=\"stylesheet\" type=\"text/css\" href=\"js/ext/css/ext-all.css\">\r\n");
      out.write("    \t<link rel=\"stylesheet\" type=\"text/css\" href=\"js/ext/css/xtheme-gray.css\">\r\n");
      out.write("    \t<link rel=\"stylesheet\" type=\"text/css\" href=\"css/common.css\">\r\n");
      out.write("  </head>\r\n");
      out.write("  <body>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"js/ext/ext.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"js/commFrames.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"js/SjpzStore.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"js/SjpzGrid.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"js/SjpzForm.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"static/gl/zmxx/queryCsxx.js\"></script>\r\n");
      out.write("    <div id=\"div1\"></div>\r\n");
      out.write("  </body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
