package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;
import com.hzjc.hz2004.po.PoDZZJ_CZRZB;
import com.hzjc.hz2004.po.PoDZZJ_CZYXXB;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.*;

/**
 * 住址追加操作日志返回信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoZzzjRzxx
    extends DefaultVO {

  private Long rzid;
  private String czsj;
  private Long czyid;
  private String czdz;
  private String czip;
  private Long bczyid;
  private String czydlm;
  private String czyxm;
  private String czymm;
  private String czydwdm;
  private String czysfhm;
  private String czysfzglh;
  private String sbglh;
  private String czyzw1;
  private String czyzw2;
  private String zcsj;
  private String zxsj;
  private String czyzt;
  private String ktglhdn;
  private String bczydlm;
  private String bczyxm;
  private String bczymm;
  private String bczydwdm;
  private String bczysfhm;
  private String bczysfzglh;
  private String bsbglh;
  private String bczyzw1;
  private String bczyzw2;
  private String bzcsj;
  private String bzxsj;
  private String bczyzt;
  private String bktglhdn;
  public VoZzzjRzxx() {

  }

  public VoZzzjRzxx(PoDZZJ_CZRZB poDZZJ_CZRZB, PoDZZJ_CZYXXB poDZZJ_CZYXXB,
                    PoDZZJ_CZYXXB poDZZJ_BCZYXXB) {
    try {
      BeanUtils.copyProperties(this, poDZZJ_CZRZB);
      BeanUtils.copyProperties(this, poDZZJ_CZYXXB);
      this.setBczydlm(poDZZJ_BCZYXXB.getCzydlm());
      this.setBczydwdm(poDZZJ_BCZYXXB.getCzydwdm());
      this.setBczymm(poDZZJ_BCZYXXB.getCzymm());
      this.setBczysfhm(poDZZJ_BCZYXXB.getCzysfhm());
      this.setBczysfzglh(poDZZJ_BCZYXXB.getCzysfzglh());
      this.setBczyxm(poDZZJ_BCZYXXB.getCzyxm());
      this.setBczyzt(poDZZJ_BCZYXXB.getCzyzt());
      this.setBczyzw1(poDZZJ_BCZYXXB.getCzyzw1());
      this.setBczyzw2(poDZZJ_BCZYXXB.getCzyzw2());
      this.setBktglhdn(poDZZJ_BCZYXXB.getKtglhdn());
      this.setBzcsj(poDZZJ_BCZYXXB.getZcsj());
      this.setBzxsj(poDZZJ_BCZYXXB.getZxsj());
    }
    catch (InvocationTargetException ex) {
    }
    catch (IllegalAccessException ex) {
    }

  }

  public void setRzid(Long rzid) {
    this.rzid = rzid;
  }

  public Long getRzid() {
    return rzid;
  }

  public void setCzsj(String czsj) {
    this.czsj = czsj;
  }

  public String getCzsj() {
    return czsj;
  }

  public void setCzyid(Long czyid) {
    this.czyid = czyid;
  }

  public Long getCzyid() {
    return czyid;
  }

  public void setCzdz(String czdz) {
    this.czdz = czdz;
  }

  public String getCzdz() {
    return czdz;
  }

  public void setCzip(String czip) {
    this.czip = czip;
  }

  public String getCzip() {
    return czip;
  }

  public void setBczyid(Long bczyid) {
    this.bczyid = bczyid;
  }

  public Long getBczyid() {
    return bczyid;
  }

  public void setCzydlm(String czydlm) {
    this.czydlm = czydlm;
  }

  public String getCzydlm() {
    return czydlm;
  }

  public void setCzyxm(String czyxm) {
    this.czyxm = czyxm;
  }

  public String getCzyxm() {
    return czyxm;
  }

  public void setCzymm(String czymm) {
    this.czymm = czymm;
  }

  public String getCzymm() {
    return czymm;
  }

  public void setCzydwdm(String czydwdm) {
    this.czydwdm = czydwdm;
  }

  public String getCzydwdm() {
    return czydwdm;
  }

  public void setCzysfhm(String czysfhm) {
    this.czysfhm = czysfhm;
  }

  public String getCzysfhm() {
    return czysfhm;
  }

  public void setCzysfzglh(String czysfzglh) {
    this.czysfzglh = czysfzglh;
  }

  public String getCzysfzglh() {
    return czysfzglh;
  }

  public void setSbglh(String sbglh) {
    this.sbglh = sbglh;
  }

  public String getSbglh() {
    return sbglh;
  }

  public void setCzyzw1(String czyzw1) {
    this.czyzw1 = czyzw1;
  }

  public String getCzyzw1() {
    return czyzw1;
  }

  public void setCzyzw2(String czyzw2) {
    this.czyzw2 = czyzw2;
  }

  public String getCzyzw2() {
    return czyzw2;
  }

  public void setZcsj(String zcsj) {
    this.zcsj = zcsj;
  }

  public String getZcsj() {
    return zcsj;
  }

  public void setZxsj(String zxsj) {
    this.zxsj = zxsj;
  }

  public String getZxsj() {
    return zxsj;
  }

  public void setCzyzt(String czyzt) {
    this.czyzt = czyzt;
  }

  public String getCzyzt() {
    return czyzt;
  }

  public String getKtglhdn() {
    return ktglhdn;
  }

  public void setKtglhdn(String ktglhdn) {
    this.ktglhdn = ktglhdn;
  }

  public void setBczydlm(String czydlm) {
    this.czydlm = czydlm;
  }

  public String getBczydlm() {
    return czydlm;
  }

  public void setBczyxm(String czyxm) {
    this.czyxm = czyxm;
  }

  public String getBczyxm() {
    return czyxm;
  }

  public void setBczymm(String czymm) {
    this.czymm = czymm;
  }

  public String getBczymm() {
    return czymm;
  }

  public void setBczydwdm(String czydwdm) {
    this.czydwdm = czydwdm;
  }

  public String getBczydwdm() {
    return czydwdm;
  }

  public void setBczysfhm(String czysfhm) {
    this.czysfhm = czysfhm;
  }

  public String getBczysfhm() {
    return czysfhm;
  }

  public void setBczysfzglh(String czysfzglh) {
    this.czysfzglh = czysfzglh;
  }

  public String getBczysfzglh() {
    return czysfzglh;
  }

  public void setBsbglh(String sbglh) {
    this.sbglh = sbglh;
  }

  public String getBsbglh() {
    return sbglh;
  }

  public void setBczyzw1(String czyzw1) {
    this.czyzw1 = czyzw1;
  }

  public String getBczyzw1() {
    return czyzw1;
  }

  public void setBczyzw2(String czyzw2) {
    this.czyzw2 = czyzw2;
  }

  public String getBczyzw2() {
    return czyzw2;
  }

  public void setBzcsj(String zcsj) {
    this.zcsj = zcsj;
  }

  public String getBzcsj() {
    return zcsj;
  }

  public void setBzxsj(String zxsj) {
    this.zxsj = zxsj;
  }

  public String getBzxsj() {
    return zxsj;
  }

  public void setBczyzt(String czyzt) {
    this.czyzt = czyzt;
  }

  public String getBczyzt() {
    return czyzt;
  }

  public String getBktglhdn() {
    return ktglhdn;
  }

  public void setBktglhdn(String ktglhdn) {
    this.ktglhdn = ktglhdn;
  }

}
