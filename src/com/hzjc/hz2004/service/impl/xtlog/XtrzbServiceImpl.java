package com.hzjc.hz2004.service.impl.xtlog;

import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.Page;
import com.hzjc.hz2004.base.ServiceImpl;
import com.hzjc.hz2004.service.xtlog.XtrzbService;
import org.springframework.stereotype.Service;

@Service
public class XtrzbServiceImpl extends ServiceImpl implements XtrzbService {

    /**
     * 系统日志信息查询
     * @param params 参数map
     * @return
     */
    @Override
    public Page getXtrzbInfo(ExtMap<String, Object> params) {
        return  super.getPageRecords("/conf/segment/common", "queryXtrzbxx", params);
    }
}
