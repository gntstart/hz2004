package com.hzjc.hz2004.service.xtlog;

import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.Page;

public interface XtrzbService {
    /**
     * 查询系统日志信息
     * 2018/11/09
     * zjm
     * @param params
     * @return
     */
    public Page getXtrzbInfo(ExtMap<String, Object> params);
}
