package com.hzjc.hz2004.util;

import org.springframework.web.util.UrlPathHelper;
import com.hzjc.hz2004.base.BaseInterceptor;
import javax.servlet.http.HttpServletRequest;

public class HttpUtil {
    //private  static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    private static final UrlPathHelper urlPathHelper = new UrlPathHelper();

    /**
     * 是否是 json 请求
     * @param request
     * @return
     */
    public static boolean isRequestJson(HttpServletRequest request){
        String uri=urlPathHelper.getRequestUri(request);
        if(CommonUtil.isEmpty(uri)){
            uri="";
        }
        String accept= request.getHeader("Accept");
        return uri.endsWith(BaseInterceptor._format_json_)
        		||(CommonUtil.isNotEmpty(accept)&&accept.contains("application/json")
        				||(CommonUtil.isNotEmpty(accept)&&accept.contains("json")));
    }
}
