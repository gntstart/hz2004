/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.8
 * Generated at: 2018-09-11 06:24:11 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp.yw.hjyw;

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

public final class qcyw_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/WEB-INF/jsp/yw/hjyw/../../config.jsp", Long.valueOf(1535076584134L));
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
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
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

      out.write("<Script Lanaguage=\"JavaScript\">\r\n");
      out.write("\tvar xmdz = '");
      out.print(basePath);
      out.write("';\r\n");
      out.write("</Script>");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("  <head>\r\n");
      out.write("  \t<base href=\"");
      out.print(basePath);
      out.write("\">\r\n");
      out.write("    \t<title>迁出业务</title>\r\n");
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
      out.write("    <script type=\"text/javascript\" src=\"js/yw/SelectRyxxPanel.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"js/yw/BggzDialog.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"js/yw/PrintDialog.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"js/yw/SelectZqz.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"js/yw/SelectQyz.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"js/lodop/CheckActivX.js\"></script>\r\n");
      out.write("    <object id=\"LODOP\" classid=\"clsid:2105C259-1E0C-4534-8141-A753534CB4CA\" width=0 height=0></object>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"static/yw/hjyw/qcyw.js\"></script>\r\n");
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
