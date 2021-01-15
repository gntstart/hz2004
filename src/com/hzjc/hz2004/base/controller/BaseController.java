package com.hzjc.hz2004.base.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.Page;
import com.hzjc.hz2004.base.annotation.WtxException;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.util.ExceptionUtil;
import com.hzjc.hz2004.util.HttpUtil;
import com.hzjc.wsstruts.vo.VoPage;
import com.hzjc.wsstruts.vo.VoQueryResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Map;

/**
 * 公共controller父类
 * 负责页面跳转和jsp异常处理
 * @author 刘继凤
 */
public class BaseController implements HandlerExceptionResolver {
    protected  final Logger logger = LoggerFactory.getLogger(getClass());
    //controller映射的路径
    protected  String path = null;
    public BaseController(){
        RequestMapping annotation = getClass().getAnnotation(RequestMapping.class);
        if(annotation!=null){
            path = annotation.value()[0];
        }
        if(null==path)
            path="";
        if(path.startsWith("/")){
            path=path.substring(1);
        }

    }
    
    //返回数据存放位置
    public static final String dataKey="data";
    //jsonview的名称
    public static final String ToJsonViewName="to_json_view_name";

    /**
     * 根目录自动映射目录下的jsp文件
     * @param page
     * @return
     */
    @RequestMapping(value = {"/{page}"})
    public ModelAndView index(@PathVariable String page) {
        return view(page);
    }


    protected ModelAndView toJson(String _message) {
        return this.toJson(true,_message);
    }
    protected ModelAndView toJson(boolean _success,String _message) {
        return toJsonObject(new JSONModel(_success, _message));
    }
    protected ModelAndView toJson() {
        return toJsonObject(new JSONModel());
    }
    protected ModelAndView toJson(Object jm) {
        return toJson(jm,null);
    }
    protected ModelAndView toJson(Object jm,String message) {
        return toJson(jm,message,null,null);
    }
    protected ModelAndView toJson(Object jm, String message, JSONModel.MessageType messageType){
        return this.toJson(jm,message,null,messageType);
    }
    protected ModelAndView toJson(Object jm, String message, String dateformat){
        return this.toJson(jm,message,dateformat,null);
    }
    protected ModelAndView toJson(Object jm, String message,String dateformat, JSONModel.MessageType messageType) {
        if(jm==null){
            return toJson();
        }
        JSONModel jsonModel;
        if(jm instanceof JSONModel){
            jsonModel=(JSONModel)jm;
        }else{
            jsonModel=new JSONModel();
            jsonModel.setEntity(jm);
        }


        if(CommonUtil.isNotEmpty(message)) {
            jsonModel.setMessage(message);
        }
        if(CommonUtil.isNotEmpty(dateformat)) {
            jsonModel.setDateFormat(dateformat);
        }

        if(messageType!=null){
            jsonModel.setMessageType(messageType);
        }
        return toJsonObject(jsonModel);
    }
    /**
     * 
     * json返回只返回obj对象
     * @author   刘继凤  E-mail:liujifeng#liujifeng.net
     * @param obj
     * @return 
     * Date:    2016年10月30日 上午11:05:28
     */
    protected ModelAndView toJsonObject(Object obj) {
    	 if(null==obj)return toJson();
         return view(ToJsonViewName,obj);
	}

    /**
     * 客户端跳转页面
     * @param viewName
     * @return
     */
    protected  ModelAndView viewRedirect(String viewName){
        if(viewName.startsWith("/")){
            viewName= viewName.substring(1);
        }
        return new ModelAndView("redirect:/"+viewName);
    }

    /**
     * 服务器跳转页面
     * @param viewName
     * @return
     */
    protected  ModelAndView viewForward(String viewName){
        if(viewName.startsWith("/")){
            viewName= viewName.substring(1);
        }
        return new ModelAndView("forward:/"+viewName);
    }
    protected ModelAndView view(String viewName) {
        if(CommonUtil.isEmpty(viewName)){
            viewName="index";
        }
        if(viewName.startsWith("/")){
           viewName= viewName.substring(1);
        }
        if(CommonUtil.isNotEmpty(path)) {
            if(!path.endsWith("/")){
                path+="/";
            }
            viewName = path + viewName;
        }
        ModelAndView modelAndView=new ModelAndView(viewName);
        return modelAndView;
    }
    private ModelAndView view(String viewName,Object obj) {
        ModelAndView modelAndView=new ModelAndView(viewName);
        modelAndView.addObject(dataKey, obj);
        return modelAndView;
    }
    /** 异常处理 */
//    @ExceptionHandler
//    public ModelAndView exceptionHandler(HttpServletRequest request, HttpServletResponse response,Object handler, RuntimeException ex) {
//
//    }
    /** 异常处理 */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        /**
         * json请求
         */
        if(HttpUtil.isRequestJson(request))
            throw  ExceptionUtil.wearException(ex);
        
        logger.error("jsp异常:",ex);
        /**
         * jsp页面异常处理
         */
        String exceptionPage="error/exception";
        if(handler instanceof HandlerMethod){
            HandlerMethod method = (HandlerMethod)handler;
            /**
             * 获取异常跳转页面
             */
            WtxException wtxException=method.getMethodAnnotation(WtxException.class);
            if(wtxException!=null){
                exceptionPage=wtxException.value();
            }
        }
        ModelAndView view=new ModelAndView(exceptionPage);
        /**
         * 处理参数
         */
        Map<String,String> params = CommonUtil.getParameterMap(request);
        view.addAllObjects(params);
        /**
         * 写入异常
         */
        view.addObject("ex",ex);
        view.addObject("wtx_exception",ExceptionUtil.getMessage(ex));
        return view;
    }
    
    public VoPage toVoPage(ExtMap<String, Object> params){
		int pageIndex = params.getInteger("pageIndex");
		int pageSize = params.getInteger("pageSize");
		if(pageIndex<=1)
			pageIndex = 1;
		
		VoPage voPage = new VoPage();
		voPage.setPagesize(pageSize);
		voPage.setPageindex(pageIndex);
		voPage.setPageoffset( (pageIndex-1) * pageSize);
		
		return voPage;
    }
    
    public Page toPage(VoQueryResult re){
    	Page p = new Page();
    	p.setList(re.getPagelist());
    	p.setTotalCount(new Long(re.getRecordcount()).intValue());
    	if(p.getList()==null){
    		p.setList(new ArrayList<>());
    	}
    	
    	return p;
    }
}
