package com.hzjc.hz2004.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.hzjc.hz2004.base.controller.BaseController;
import com.hzjc.hz2004.base.login.AuthToken;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.base.login.HSession;
import com.hzjc.hz2004.base.login.LoginFilter;
import com.hzjc.hz2004.service.LoginService;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.util.MemcachedUtil;

@Controller
@RequestMapping("/")
public class LoginController extends BaseController{
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value = { "/login/index" }, method = RequestMethod.GET)
	public String index() {
		return "login";
	}
	
	@RequestMapping(value = {"",  "/"}, method = RequestMethod.GET)
	public ModelAndView index2() {
		if(BaseContext.getBaseUser()!=null){
			return super.viewRedirect("/index");
		}
		
		return super.viewRedirect("/login/index");
	}
	
	@RequestMapping(value = { "/login/out" }, method = RequestMethod.GET)
	public String out() {
		loginService.logout();
		return "login";
	}
	
    @RequestMapping(value = "/login/login", method = {RequestMethod.POST})
    public ModelAndView loginUser(Model model, String usercode,String pwd){
    	String strUserId = BaseContext.getContext().getRequest().getParameter("usercode");
    	String strPassword = BaseContext.getContext().getRequest().getParameter("pwd");
    	String strYzm = BaseContext.getContext().getRequest().getParameter("yzm");
    	String strIp = LoginFilter.getIpAddr(BaseContext.getContext().getRequest());
    	CommonUtil.getParameterMap(BaseContext.getContext().getRequest());
    	AuthToken auth = loginService.loginAuthToken(strUserId, strPassword, strYzm, strIp);
    	if(auth.getUser().getYhdlm().equalsIgnoreCase("hzadmin")){
    		auth.setAdmin(true);
    	}
    	
		//缓存用户和会话ID的关系
		String user_sid = "_sid_" +  auth.getUser().getYhdlm();
		String oldkey = (String)MemcachedUtil.get(user_sid);
		if(oldkey!=null)
			MemcachedUtil.delete(oldkey);
		
    	HSession.saveUser(auth);
    	
		String key = HSession.getUserSessionKey(auth.getSid()); 
		MemcachedUtil.set(user_sid,  key);
		
        return toJson(auth);
    }
}
