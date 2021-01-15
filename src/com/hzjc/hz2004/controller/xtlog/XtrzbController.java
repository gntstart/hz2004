package com.hzjc.hz2004.controller.xtlog;

import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.controller.BaseController;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.service.xtlog.XtrzbService;
import com.hzjc.hz2004.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("gl/xtrz")
public class XtrzbController  extends BaseController {

    @Autowired
    XtrzbService xtrzbService;

    @RequestMapping(value = { "/xtlog", "" }, method = RequestMethod.GET)
    public String xtRzbLogQuery(){
        return "/gl/xtlog";
    }

    @RequestMapping(value = { "/getXtrzbInfo" }, method = RequestMethod.POST)
    public ModelAndView getXtrzbInfo() {

        ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
        return toJson(xtrzbService.getXtrzbInfo(params));
    }
}
