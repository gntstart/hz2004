package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.*;
import org.apache.commons.logging.LogFactory;
import com.hzjc.hz2004.service.impl.DateDownload4QxServiceImpl;
import org.apache.commons.logging.Log;
import com.hzjc.hz2004.service.DateDownload4QxService;

/**
 * <p>Title: </p>
 *
 * <p>Description: 常住人口二代证Hz2004版</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class VoDateDownloadxx extends DefaultVO {

    //日志处理
    protected static Log _log = LogFactory.getLog(VoDateDownloadxx.class);

    protected String Cxdctj; //查询导出条件
    protected String Dczdlb; //导出定段列表
    protected String Mwjdx; //每文件的大小


    /**
     * 这几个属性本身不该在这个类内的,但考虑到MVC的框架应该严格区分控制和服务,
     * 所以也就加入了这几个属性,控制器可以清楚的了解业务流程,而不进行琐碎的业务处理
     */
    protected String Yhid; //用户登录名
    protected String YhidTwo; //真正的用户ID
    protected String ip; //用户IP
    protected String dw; //用户所在单位
    protected DateDownload4QxService service; //权限服务


    public VoDateDownloadxx() {
    }

    public void setCxdctj(String Cxdctj) {
        this.Cxdctj = Cxdctj;
    }

    public void setDczdlb(String Dczdlb) {
        this.Dczdlb = Dczdlb;
    }

    public void setMwjdx(String Mwjdx) {
        this.Mwjdx = Mwjdx;
    }

    public void setYhid(String Yhid) {
        this.Yhid = Yhid;
    }

    public void setYhidTwo(String YhidTwo) {
        this.YhidTwo = YhidTwo;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setDw(String dw) {
        this.dw = dw;
    }

    public void setService(DateDownload4QxService service) {
        this.service = service;
    }

    public String getCxdctj() {
        return Cxdctj;
    }

    public String getDczdlb() {
        return Dczdlb;
    }

    public String getMwjdx() {
        return Mwjdx;
    }

    public String getYhid() {
        return Yhid;
    }

    public String getYhidTwo() {
        return YhidTwo;
    }

    public String getIp() {
        return ip;
    }

    public String getDw() {
        return dw;
    }

    public DateDownload4QxService getService() {
        return service;
    }

}
