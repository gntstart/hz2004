package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.*;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.*;
import com.hzjc.hz2004.po.PoXT_YWBBMBXXB;
import com.hzjc.hz2004.po.PoXT_YHXXB;
import com.hzjc.util.StringUtils;
import java.sql.Blob;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 业务报表操作VO</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public class VoXtywbb
    extends DefaultVO {
  //业务报表
  private Long ywbbid;
  private String ywbblb;
  private String bbmc;
  private String bbmb;
  private String jlsj;
  private Long jlrid;
  private String xgsj;
  private Long xgrid;
  private String bbzt;
  //用户信息---创建人信息
  private String jlryhdlm;
  private String jlryhxm;
  //用户信息---修改人信息
  private String xgryhdlm;
  private String xgryhxm;

  public VoXtywbb() {
  }

  public VoXtywbb(VoXT_YWBBMBXXB voXT_YWBBMBXXB, PoXT_YHXXB poXTYHXXBA,
                  PoXT_YHXXB poXTYHXXBB) {
    try {
      BeanUtils.copyProperties(this, voXT_YWBBMBXXB);
      this.jlsj = StringUtils.formatDateBy(StringUtils.strToDate(voXT_YWBBMBXXB.
          getJlsj(), "yyyyMMddHHmmss"), "yyyy-MM-dd HH:mm:ss");
      this.xgsj = StringUtils.formatDateBy(StringUtils.strToDate(voXT_YWBBMBXXB.
          getXgsj(), "yyyyMMddHHmmss"), "yyyy-MM-dd HH:mm:ss");
      this.setJlryhdlm(poXTYHXXBA.getYhdlm());
      this.setJlryhxm(poXTYHXXBA.getYhxm());
      if (poXTYHXXBB != null) {
        this.setXgryhdlm(poXTYHXXBB.getYhdlm());
        this.setXgryhxm(poXTYHXXBB.getYhxm());
      }
    }
    catch (InvocationTargetException ex) {
    }
    catch (IllegalAccessException ex) {
    }
  }

  public void setYwbbid(Long ywbbid) {
    this.ywbbid = ywbbid;
  }

  public Long getYwbbid() {
    return ywbbid;
  }

  public void setYwbblb(String ywbblb) {
    this.ywbblb = ywbblb;
  }

  public String getYwbblb() {
    return ywbblb;
  }

  public void setBbmc(String bbmc) {
    this.bbmc = bbmc;
  }

  public String getBbmc() {
    return bbmc;
  }

  public void setBbmb(String bbmb) {
    this.bbmb = bbmb;
  }

  public String getBbmb() {
    return bbmb;
  }

  public void setJlsj(String jlsj) {
    this.jlsj = jlsj;
  }

  public String getJlsj() {
    return jlsj;
  }

  public void setJlrid(Long jlrid) {
    this.jlrid = jlrid;
  }

  public Long getJlrid() {
    return jlrid;
  }

  public void setXgsj(String xgsj) {
    this.xgsj = xgsj;
  }

  public String getXgsj() {
    return xgsj;
  }

  public void setXgrid(Long xgrid) {
    this.xgrid = xgrid;
  }

  public Long getXgrid() {
    return xgrid;
  }

  public void setBbzt(String bbzt) {
    this.bbzt = bbzt;
  }

  public String getBbzt() {
    return bbzt;
  }

  //用户信息
  public void setJlryhdlm(String yhdlm) {
    this.jlryhdlm = yhdlm;
  }

  public String getJlryhdlm() {
    return jlryhdlm;
  }

  public void setJlryhxm(String yhxm) {
    this.jlryhxm = yhxm;
  }

  public String getJlryhxm() {
    return jlryhxm;
  }

  //修改人
  public void setXgryhdlm(String yhdlm) {
    this.xgryhdlm = yhdlm;
  }

  public String getXgryhdlm() {
    return xgryhdlm;
  }

  public void setXgryhxm(String yhxm) {
    this.xgryhxm = yhxm;
  }

  public String getXgryhxm() {
    return xgryhxm;
  }

}
