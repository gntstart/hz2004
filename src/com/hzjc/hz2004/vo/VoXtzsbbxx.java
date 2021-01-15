package com.hzjc.hz2004.vo;

import com.hzjc.hz2004.po.PoXT_ZSBBMBXXB;
import com.hzjc.hz2004.po.PoXT_ZSBBXXB;
import com.hzjc.hz2004.po.PoXT_YHXXB;
import com.hzjc.wsstruts.vo.*;
import org.apache.commons.beanutils.BeanUtils;
import com.hzjc.util.StringUtils;
import java.lang.reflect.*;
import java.sql.Blob;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 制试报表操作VO</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public class VoXtzsbbxx
    extends DefaultVO {

  //制试报表信息
  private Long zsbbid;
  private Long zsbbmbid;
  private String bbmc;
  private String bbsjmb;
  private String jlsj;
  private Long scrid;
  private String xgsj;
  private Long xgrid;

  //制试报表模板信息
  //private Long zsbbmbid;
  private String zsbblb;
  private String bbmbmc;
  //用户信息---创建人信息
  private String scryhdlm;
  private String scryhxm;
  //用户信息---修改人信息
  private String xgryhdlm;
  private String xgryhxm;

  /**
   *
   * @param poXT_MBLCXXB
   * @param poXT_SPDZB
   * @param poXT_SPMBXXB
   */
  public VoXtzsbbxx(){
  }
  public VoXtzsbbxx(VoXT_ZSBBXXB voXT_ZSBBXXB, PoXT_ZSBBMBXXB poXT_ZSBBMBXXB,
                    PoXT_YHXXB poXTYHXXBA, PoXT_YHXXB poXTYHXXBB) {

    try {
      BeanUtils.copyProperties(this, voXT_ZSBBXXB);
      BeanUtils.copyProperties(this, poXT_ZSBBMBXXB);
      this.jlsj = StringUtils.formatDateBy(StringUtils.strToDate(voXT_ZSBBXXB.
          getJlsj(), "yyyyMMddHHmmss"), "yyyy-MM-dd HH:mm:ss");
      this.xgsj = StringUtils.formatDateBy(StringUtils.strToDate(voXT_ZSBBXXB.
          getXgsj(), "yyyyMMddHHmmss"), "yyyy-MM-dd HH:mm:ss");
      this.setScryhdlm(poXTYHXXBA.getYhdlm());
      this.setScryhxm(poXTYHXXBA.getYhxm());
      if (poXTYHXXBB != null) {
        this.setXgryhdlm(poXTYHXXBB.getYhdlm());
        this.setXgryhdlm(poXTYHXXBB.getYhxm());
      }
    }
    catch (InvocationTargetException ex) {
    }
    catch (IllegalAccessException ex) {
    }

  }

  //制试报表
  public void setZsbbid(Long zsbbid) {
    this.zsbbid = zsbbid;
  }

  public Long getZsbbid() {
    return zsbbid;
  }

  public void setZsbbmbid(Long zsbbmbid) {
    this.zsbbmbid = zsbbmbid;
  }

  public Long getZsbbmbid() {
    return zsbbmbid;
  }

  public void setBbmc(String bbmc) {
    this.bbmc = bbmc;
  }

  public String getBbmc() {
    return bbmc;
  }

  public void setBbsjmb(String bbsjmb) {
    this.bbsjmb = bbsjmb;
  }

  public String getBbsjmb() {
    return bbsjmb;
  }

  public void setJlsj(String jlsj) {
    this.jlsj = jlsj;
  }

  public String getJlsj() {
    return jlsj;
  }

  public void setScrid(Long scrid) {
    this.scrid = scrid;
  }

  public Long getScrid() {
    return scrid;
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

  //报表模板
  public void setZsbblb(String zsbblb) {
    this.zsbblb = zsbblb;
  }

  public String getZsbblb() {
    return zsbblb;
  }

  public void setBbmbmc(String bbmc) {
    this.bbmc = bbmc;
  }

  public String getBbmbmc() {
    return bbmc;
  }

  //用户信息
  public void setScryhdlm(String yhdlm) {
    this.scryhdlm = yhdlm;
  }

  public String getScryhdlm() {
    return scryhdlm;
  }

  public void setScryhxm(String yhxm) {
    this.scryhxm = yhxm;
  }

  public String getScryhxm() {
    return scryhxm;
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
