package com.hzjc.hz2004.controller.hzpt;

import com.hzjc.hz2004.base.controller.BaseController;
import com.hzjc.hz2004.service.hzpt.ReceiveStateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.annotation.Resource;

@Controller
@RequestMapping("/hzpt")
public class RecevieStateController extends BaseController {
    @Resource
    private ReceiveStateService receiveStateService;

    @RequestMapping(value = "/updateReceiveState", method = RequestMethod.GET)
    public void updateReceiveState(String WX_CODE,String cktable){
         receiveStateService.updataReceiveState(WX_CODE,cktable);
    }
}
