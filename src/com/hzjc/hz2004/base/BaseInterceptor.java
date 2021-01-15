package com.hzjc.hz2004.base;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;
import com.hzjc.hz2004.base.controller.BaseController;
import com.hzjc.hz2004.base.controller.JSONModel;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.util.ExceptionUtil;
import com.hzjc.hz2004.util.HttpUtil;
import com.hzjc.hz2004.util.JSONUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

/**
 * 基础拦截器
 * 处理json返回和异常处理
 * @author 刘继凤
 *
 */
public class BaseInterceptor implements HandlerInterceptor {
    //请求json扩展名
    public static final String _format_json_=".json";
    protected  final Logger logger = Logger.getLogger(getClass());
    protected static final UrlPathHelper urlPathHelper = new UrlPathHelper();
    /**
     * 异常返回参数名称
     */
    public static  final  String wtx_exception="wtx_exception";
    @Override
    public boolean preHandle(HttpServletRequest request,  HttpServletResponse response, Object handler) throws Exception {
      //  String  uri=urlPathHelper.getRequestUri(request);
        //logger.info("拦截开始-" +uri );
//        if(request.getAttribute("ex")!=null){
//            return false;
//        }
        return true;// 继续流程
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            //如果是jsonView 写回json数据
            //如果请求要返回json视图
            String viewname=modelAndView.getViewName();
            if(HttpUtil.isRequestJson(request)
                    || BaseController.ToJsonViewName.equals(viewname)){
                Object obj=modelAndView.getModel().get(BaseController.dataKey);
                String json=null;
                if(obj!=null){
                    if(obj instanceof JSONModel) {
                        json = obj.toString();
                    }else if(obj instanceof String) {
                        json = (String)obj;
                    }else{
                        json=JSONUtil.toJSON(obj);
                    }
                }else{
                    json=new JSONModel(true,"").toString();
                }
                writer(response,request,json);
                //已处理完成清空modelview
                modelAndView.clear();
            }else{
               // String viewname
                if(CommonUtil.isNotEmpty(viewname)){
                    //重定向到一个绝对路径
                    if(viewname.startsWith("redirect:/http")){
                        String url=viewname.substring(10);
                        Map<String,Object> params=modelAndView.getModelMap();
                        if(params.size()>0){
                            StringBuffer sb=new StringBuffer(url);
                            int num=0;
                            for (Iterator<?> iter = params.keySet().iterator(); iter.hasNext();) {
                                String name = (String) iter.next();
                                String value = params.get(name)+"";
                                if(num==0&&url.indexOf("?")<0){
                                    sb.append("?");
                                }else{
                                    sb.append("&");
                                }
                                sb.append(name).append("=").append(value);
                                num++;
                            }
                            url=sb.toString();
                        }
                        response.sendRedirect(url);
                        //已处理完成清空modelview
                        modelAndView.clear();
                    }else if(viewname.startsWith("forward:/http")){
                        String url=viewname.substring(9);
                        Map<String,Object> params=modelAndView.getModelMap();
                        String  httpmethod=params.get("httpmethod")+"";
                        if(CommonUtil.isEmpty(httpmethod)){
                            httpmethod="get";
                        }
                        this.postUrl(request,response,url, params);
                        //已处理完成清空modelview
                        modelAndView.clear();
                    }
                }
            }
        }
    }
    private void postUrl(HttpServletRequest req, HttpServletResponse resp,String callback_url,Map<String,Object> params) throws Exception{
        resp.setContentType("text/html;charSet=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"utf-8\" />");
        out.println("<title>正在跳转...</title>");
        out.println("</head>");
        out.println("<body>");
        String  httpmethod=params.get("httpmethod")+"";
        if(CommonUtil.isEmpty(httpmethod)){
            httpmethod="post";
        }
        params.remove("httpmethod");
        out.println("<form name=\"submitForm\" action=\"" + callback_url + "\" method=\""+httpmethod+"\">");
        /**
         * 处理参数
         */
        for (Iterator<?> iter = params.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String value = params.get(name)+"";
            out.println("<input type=\"hidden\" name=\""+name+"\" value=\"" + value + "\"/>");
        }
        out.println("</form>");
        out.println("<script>window.document.submitForm.submit();</script> ");
        out.println("</body>");
        out.println("</html>");
        out.flush();
        out.close();
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        if (ex != null) {
            String  uri=urlPathHelper.getRequestUri(request);
            logger.error("\n异常返回:"+uri, ex);
            /**
             * json异常处理
             */
            if(HttpUtil.isRequestJson(request)){
            	Throwable rootex = ExceptionUtil.getRootThrowable(ex);
            	
            	JSONModel json = new JSONModel(false, ExceptionUtil.getMessage(ex));
            	//异常不能返回List，否则ext无法抛出异常
            	json.setList(null);
            	if(rootex!=null && rootex instanceof com.hzjc.wsstruts.exception.ServiceException){
            		//错误类型编码，处理审批限制类错误：WSErrCode.508 - WSErrCode.514
            		com.hzjc.wsstruts.exception.ServiceException e = (com.hzjc.wsstruts.exception.ServiceException)rootex;
            		json.setErrcode(e.getErrcode());
            		//对应的审批模板ID：3407000001000000063
            		if(json.getMessage().indexOf("审批模板ID：")>0){
            			String mbid = json.getMessage().substring(json.getMessage().lastIndexOf("：")+1);
            			json.setSpmbid(mbid);
            		}
            	}
                writer(response,request, json.toString());
                return;
            }

            /**
             * jsp页面异常处理
             */

        }
    }
    //json写入前台
    protected void writer(HttpServletResponse response,HttpServletRequest request, String json) {
        PrintWriter out = null;
        try {
            response.setContentType("application/json");
            //上传图片导致Request Headers改变，Accept属性，表示不接收application/json的数据，
            //response.setContentType("text/html");
            // 设置页面不缓存
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setCharacterEncoding(Constants.CHARSET_UTF8);

            out = response.getWriter();
            out.print(json);
            out.flush();
            //String  uri=urlPathHelper.getRequestUri(request);
          //  logger.info("\n"+uri+"返回json数据:"+json);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            out.close();
        }
    }
}
