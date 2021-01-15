/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.75
 * Generated at: 2018-01-01 02:57:11 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.gnt.rest.query.RxbdUtils;
import java.util.*;
import java.io.*;
import com.gnt.rest.query.*;

public final class rxbd_005f1ton_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
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

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");

	response.setHeader("Pragma", "No-cache");    
	response.setHeader("Cache-Control", "no-cache");    
	response.setDateHeader("Expires", 0);    

	 List<DictMap> returnList = null;
	String zp = null;
	for(Object key:request.getParameterMap().keySet()){
		if(key.equals("zp")){
			zp = request.getParameter(key.toString());
			try{
				returnList = RxbdUtils.get1ToN(zp,request,response);
			}catch(Exception e){
				out.println(com.gnt.rest.util.CommonUtil.getExceptionMesssage(e));
				return;
			}
			break;
		}
	}
	
	/*
    <ROW index=\"1\">" +
    	"<RXXSD>人像相似度	rxxsd</RXXSD>" +
    	"<RXID>人像ID</RXID>" +
    	"<XP>" +getPic("/conf/example/1.png") + "</XP>" +
    	"<CXXH>查询序号</CXXH>" +
    	"<XM>姓名</XM>" +
    	"<GMSFHM>身份证</GMSFHM>" +
    	"<SJGSDW>数据归属单位节点</SJGSDW>" +
    	"<BDMBKBH>库编号节点</BDMBKBH>" +
    </ROW>" +
    	*/
    	if(returnList==null || returnList.size()==0){
    		out.println("没有找到相似度的人像数据！");
    		return;
    	}
    	
    	request.getSession().removeAttribute("rxbd_1ton");
    	request.getSession().setAttribute("rxbd_1ton", returnList);
    	
    	String url = RxbdUtils.config.get("SJWEB_QUERY.URL");
    	if(url!=null && url.indexOf("?")<0){
    		url += "?";
    	}

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gb2312\" />\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("body{background-color:#FFFFFF}\r\n");
      out.write(".div1{width:auto; height:400px; border:1px; background-color:#D4E1F2; border:#A2BAD6 solid 1px;}\r\n");
      out.write(".div2_title{border-bottom:1px; width:auto; height:30px; line-height:35px; border-bottom:#A2BAD6 solid 1px;}\r\n");
      out.write("strong{margin-left:10px; font-size:12px; color:#324160;}\r\n");
      out.write("\r\n");
      out.write(".div_datainfo{width:99.2%; height:340px; margin:5px; background-color:#FFFFFF; margin-bottom:0; border:#A2BAD6 solid 1px;}\r\n");
      out.write("table{width:100%; height:auto; font-size:12px; border-collapse:collapse;}\r\n");
      out.write("td{border-right:#C8B9AE solid 1px;border-bottom:#C8B9AE solid 1px; padding:10px 10px 6px;vertical-align: top;}\r\n");
      out.write(".last_td{ border-right:0;}\r\n");
      out.write("thead tr{width:100%; height:30px; background-color:#EFF0F2;}\r\n");
      out.write(".id{background-color:#EFF0F2;}\r\n");
      out.write("a{text-decoration:none; color:#FF3399; cursor:pointer;}\r\n");
      out.write("\r\n");
      out.write(".footer{width:99.2%; height:20px; margin-left:5px;}\r\n");
      out.write("</style>\r\n");
      out.write("<title>无标题文档</title>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("<div class=\"div1\">\r\n");
      out.write("<div class=\"div2_title\">人像比对结果：</div>\r\n");
      out.write("<div class=\"div_datainfo\">\r\n");
      out.write("<table cellpadding=\"0\" cellspacing=\"0\">\r\n");
      out.write("  <thead>\r\n");
      out.write("  \t<tr align=\"center\">\r\n");
      out.write("    <td >内容</td>\r\n");
      out.write("    <td>原图</td>\r\n");
      out.write("    ");

    	for(int i=1;i<returnList.size();i++)
    		out.println("<td>结果" +  i + "</td>");
    
      out.write("\r\n");
      out.write("    </tr>\r\n");
      out.write("  </thead>\r\n");
      out.write("  <tr align=\"center\" height=\"20\">\r\n");
      out.write("    <td class=\"id\">照片</td>\r\n");
      out.write("    ");

    	for(int i=0;i<returnList.size();i++)
    		out.println("<td><img src='rxbd_1ton/" + i + "'  width=\"102\" height=\"126\" /></td>");
    
      out.write("\r\n");
      out.write("    </tr>\r\n");
      out.write(" <tr align=\"center\" height=\"20\">\r\n");
      out.write("   <td class=\"id\">相似度</td>\r\n");
      out.write("    ");

    	for(int i=0;i<returnList.size();i++)
    		out.println("<td>" + returnList.get(i).get("RXXSD") + "</td>");
    
      out.write("\r\n");
      out.write("    </tr>\r\n");
      out.write("   <tr align=\"center\" height=\"20\">\r\n");
      out.write("     <td class=\"id\">身份证号码</td>\r\n");
      out.write("    ");

    	for(int i=0;i<returnList.size();i++)
    		out.println("<td>" + returnList.get(i).get("GMSFHM") + "</td>");
    
      out.write("\r\n");
      out.write("    </tr>\r\n");
      out.write(" <tr align=\"center\" height=\"20\">\r\n");
      out.write("   <td class=\"id\">姓名</td>\r\n");
      out.write("    ");

    	for(int i=0;i<returnList.size();i++)
    		out.println("<td>" + returnList.get(i).get("XM") + "</td>");
    
      out.write("\r\n");
      out.write("    </tr>\r\n");
      out.write(" <tr align=\"center\" height=\"20\">\r\n");
      out.write("   <td class=\"id\">关联查询</td>\r\n");

    	for(int i=0;i<returnList.size();i++){
    		if(url!=null){
    			out.println("<td><A target=_target href='" + url + "&sfz=" + returnList.get(i).get("GMSFHM") + "'>查询</a></td>");
    		}else{
    			out.println("<td>&ndsp;</td>");
    		}
    	}

      out.write("\r\n");
      out.write("    </tr>\r\n");
      out.write("</table>\r\n");
      out.write("</div>\r\n");
      out.write("<div class=\"footer\"></div>\r\n");
      out.write("</div>\r\n");
      out.write("</body>\r\n");
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
