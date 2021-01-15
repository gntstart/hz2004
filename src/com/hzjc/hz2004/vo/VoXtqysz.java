package com.hzjc.hz2004.vo;

import com.hzjc.hz2004.po.PoXT_YHXXB;
import com.hzjc.hz2004.po.PoXT_QYSZB;
import com.hzjc.hz2004.po.PoXT_XZQHB;
import com.hzjc.wsstruts.vo.*;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.*;
import com.hzjc.util.StringUtils;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 迁移设置操作VO</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public class VoXtqysz
    extends DefaultVO {

  //迁移设置
  private Long qyid;
  private String qhdma;
  private String qhdmb;
  private String cjsj;
  private Long cjrid;
  private String xgsj;
  private Long xgrid;
  private String qybz;

  //用户信息---创建人信息
  private String cjryhdlm;
  private String cjryhxm;
  //用户信息---修改人信息
  private String xgryhdlm;
  private String xgryhxm;

  //行政区划a
  private String dma;
  private String mca;
  //行政区划b
  private String dmb;
  private String mcb;

  /**
   *
   * @param poXT_MBLCXXB
   * @param poXT_SPDZB
   * @param poXT_SPMBXXB
   */
  public VoXtqysz() {
  }

  public VoXtqysz(PoXT_QYSZB poXTQYSZB,
                  PoXT_XZQHB poXTXZQHBA, PoXT_XZQHB poXTXZQHBB,
                  PoXT_YHXXB poXT_YHXXBA, PoXT_YHXXB poXT_YHXXBB) {

    try {
      BeanUtils.copyProperties(this, poXTQYSZB);
      this.cjsj = StringUtils.formatDateBy(StringUtils.strToDate(poXTQYSZB.
          getCjsj(), "yyyyMMddHHmmss"), "yyyy-MM-dd HH:mm:ss");
      this.xgsj = StringUtils.formatDateBy(StringUtils.strToDate(poXTQYSZB.
          getXgsj(), "yyyyMMddHHmmss"), "yyyy-MM-dd HH:mm:ss");
      BeanUtils.copyProperties(this, poXT_YHXXBA);
      this.setCjryhdlm(poXT_YHXXBA.getYhdlm());
      this.setCjryhxm(poXT_YHXXBA.getYhxm());
      if (poXT_YHXXBB != null) {
        this.setXgryhdlm(poXT_YHXXBB.getYhdlm());
        this.setXgryhxm(poXT_YHXXBB.getYhxm());
      }
      if (poXTXZQHBA != null) {
        this.setDma(poXTXZQHBA.getDm());
        this.setMca(poXTXZQHBA.getMc());
        this.setDmb(poXTXZQHBB.getDm());
        this.setMcb(poXTXZQHBB.getMc());
      }
    }
    catch (InvocationTargetException ex) {
    }
    catch (IllegalAccessException ex) {
    }

  }

  //用户信息
  public void setCjryhdlm(String yhdlm) {
    this.cjryhdlm = yhdlm;
  }

  public String getCjryhdlm() {
    return cjryhdlm;
  }

  public void setCjryhxm(String yhxm) {
    this.cjryhxm = yhxm;
  }

  public String getCjryhxm() {
    return cjryhxm;
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

  //迁移设置
  public void setQyid(Long qyid) {
    this.qyid = qyid;
  }

  public Long getQyid() {
    return qyid;
  }

  public void setQhdma(String qhdma) {
    this.qhdma = qhdma;
  }

  public String getQhdma() {
    return qhdma;
  }

  public void setQhdmb(String qhdmb) {
    this.qhdmb = qhdmb;
  }

  public String getQhdmb() {
    return qhdmb;
  }

  public void setCjsj(String cjsj) {
    this.cjsj = cjsj;
  }

  public String getCjsj() {
    return cjsj;
  }

  public void setCjrid(Long cjrid) {
    this.cjrid = cjrid;
  }

  public Long getCjrid() {
    return cjrid;
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

  public void setQybz(String qybz) {
    this.qybz = qybz;
  }

  public String getQybz() {
    return qybz;
  }

  //行政区划 a
  public void setDma(String dma) {
    this.dma = dma;
  }

  public String getDma() {
    return dma;
  }

  public void setMca(String mca) {
    this.mca = mca;
  }

  public String getMca() {
    return mca;
  }

  //行政区划 b
  public void setDmb(String dmb) {
    this.dmb = dmb;
  }

  public String getDmb() {
    return dmb;
  }

  public void setMcb(String mcb) {
    this.mcb = mcb;
  }

  public String getMcb() {
    return mcb;
  }

}
