package com.hzjc.hz2004.vo;

import com.hzjc.hz2004.po.PoXT_XTCSB;
import com.hzjc.hz2004.po.PoXT_YWQXKZB;
import com.hzjc.hz2004.po.PoXT_XTGNB;
import com.hzjc.wsstruts.vo.*;
import org.apache.commons.beanutils.BeanUtils;
import com.hzjc.wsstruts.exception.*;
import java.lang.reflect.*;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 业务权限信息操作VO</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public class VoXtywqxkz
    extends DefaultVO {

  //行政区划信息

  private String xqlxmc;
  private String sjfwxzmc;
  private String yhdtcxmc;
  private String gnmc;

  //业务权限控制信息
  private Long qxkzid;
  private String ywid;
  private String ywmc;
  private String xqlx;
  private String sjfwxz;
  private String yhdtcx;

  public VoXtywqxkz() {
  }

  public VoXtywqxkz(PoXT_YWQXKZB poXtywqxkzb, PoXT_XTCSB poXtcsba,
                    PoXT_XTCSB poXtcsbb,
                    PoXT_XTCSB poXtcsbc, PoXT_XTGNB poXtgnb) {
    try {
      BeanUtils.copyProperties(this, poXtywqxkzb);
      this.setXqlxmc(poXtcsba.getMc());
      this.setSjfwxzmc(poXtcsbb.getMc());
      this.setYhdtcxmc(poXtcsbc.getMc());
      this.setGnmc(poXtgnb.getGnmc());
    }
    catch (InvocationTargetException ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (IllegalAccessException ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
  }

  public void setXqlxmc(String xqlxmc) {
    this.xqlxmc = xqlxmc;
  }

  public String getXqlxmc() {
    return xqlxmc;
  }

  public void setSjfwxzmc(String sjfwxzmc) {
    this.sjfwxzmc = sjfwxzmc;
  }

  public String getSjfwxzmc() {
    return sjfwxzmc;
  }

  public void setYhdtcxmc(String yhdtcxmc) {
    this.yhdtcxmc = yhdtcxmc;
  }

  public String getYhdtcxmc() {
    return yhdtcxmc;
  }

  public void setGnmc(String gnmc) {
    this.gnmc = gnmc;
  }

  public String getGnmc() {
    return gnmc;
  }

  //业务权限控制

  public void setQxkzid(Long qxkzid) {
    this.qxkzid = qxkzid;
  }

  public Long getQxkzid() {
    return qxkzid;
  }

  public void setYwid(String ywid) {
    this.ywid = ywid;
  }

  public String getYwid() {
    return ywid;
  }

  public void setYwmc(String ywmc) {
    this.ywmc = ywmc;
  }

  public String getYwmc() {
    return ywmc;
  }

  public void setXqlx(String xqlx) {
    this.xqlx = xqlx;
  }

  public String getXqlx() {
    return xqlx;
  }

  public void setSjfwxz(String sjfwxz) {
    this.sjfwxz = sjfwxz;
  }

  public String getSjfwxz() {
    return sjfwxz;
  }

  public void setYhdtcx(String yhdtcx) {
    this.yhdtcx = yhdtcx;
  }

  public String getYhdtcx() {
    return yhdtcx;
  }

}
