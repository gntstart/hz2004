package com.hzjc.hz2004.vo;

import com.hzjc.hz2004.po.PoHJSP_HJBLSPSQB;
import com.hzjc.hz2004.po.PoHJSP_HJSPLSB;
import com.hzjc.hz2004.po.PoXT_SPDZB;
import com.hzjc.wsstruts.vo.*;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.*;

/**
 * 户籍补录审批审批信息获取返回信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */

public class VoHjblspspxxHqFhxx {

  //户籍补录审批信息
  private Long spywid; //审批业务ID
  private String gmsfhm; //公民身份号码
  private String xm; //姓名
  private String cym; //曾用名
  private String xb; //性别
  private String mz; //民族
  private String csrq; //出生日期
  private String cssj; //出生时间
  private String csdgjdq; //出生地国家（地区）
  private String csdssxq; //出生地省市县（区）
  private String csdxz; //出生地详址
  private String jggjdq; //籍贯国家（地区）
  private String jgssxq; //籍贯省市县（区）
  private String jhryxm; //监护人一姓名
  private String jhrygmsfhm; //监护人一公民身份号码
  private String jhryjhgx; //监护人一监护关系
  private String jhrexm; //监护人二姓名
  private String jhregmsfhm; //监护人二公民身份号码
  private String jhrejhgx; //监护人二监护关系
  private String fqgmsfhm; //父亲公民身份号码
  private String fqxm; //父亲姓名
  private String mqgmsfhm; //母亲公民身份号码
  private String mqxm; //母亲姓名
  private String pogmsfhm; //配偶公民身份号码
  private String poxm; //配偶姓名
  private String zjxy; //宗教信仰
  private String whcd; //文化程度
  private String hyzk; //婚姻状况
  private String byzk; //兵役状况
  private String sg; //身高
  private String xx; //血型
  private String zy; //职业
  private String zylb; //职业类别
  private String fwcs; //服务处所
  private String xxjb; //信息级别
  private String hb; //户别
  private String cszmbh; //出生证明编号
  private String ssssxq; //所属省市县区
  private String sspcs; //所属派出所
  private String ssjwh; //所属居委会
  private String ssjlx; //所属街路巷
  private String ssxzjd; //所属乡镇街道
  private String ssjwzrq; //所属警务责任区
  private String ssmlph; //所属门楼牌号
  private String ssxz; //所属详址
  private Long sshhid; //所属户号ID
  private String sshh; //所属户号
  private String sshlx; //所属户类型
  private String yhzgx; //与户主关系
  private String rlhbz; //入立户标志
  private String blyy; //补录原因
  private Long djrid; //登记人ID
  private String djsj; //登记时间
  private Long xydzid; //下一动作ID
  private Long spmbid; //审批模板ID
  private String spjg; //审批结果
  private String lsbz; //落实标志
  private Long rynbid; //人员内部ID
  private Long hjywid; //户籍业务ID
  private String spsm; //审批说明
  private Long ywxl; //业务序列

  //审批流水信息
  private Long splsid; //审批流水ID
  //private Long spywid; //审批业务ID
  //private String splx;//审批类型
  private Long dzid; //动作ID
  private String czjg; //操作结果
  private String czyj; //操作意见
  private Long czrid; //操作人ID
  private String czsj; //操作时间

  //审批动作信息
  //private Long dzid; //动作ID
  private String dzmc; //动作名称
  private String ms; //描述
  private String qybz; //启用标志

  //下一动作_审批动作信息
  private String xydz_dzmc; //动作名称
  private String xydz_ms; //描述
  private String xydz_qybz; //启用标志

  public VoHjblspspxxHqFhxx() {
  }

  public VoHjblspspxxHqFhxx(PoHJSP_HJBLSPSQB poHjblspsqxx,
                            PoHJSP_HJSPLSB poHjsplsxx,
                            PoXT_SPDZB poSpdzxx, PoXT_SPDZB poXyspdzxx) {
    try {
      if (poHjblspsqxx != null) {
        BeanUtils.copyProperties(this, poHjblspsqxx);
      }
      if (poHjsplsxx != null) {
        BeanUtils.copyProperties(this, poHjsplsxx);
      }
      if (poSpdzxx != null) {
        BeanUtils.copyProperties(this, poSpdzxx);
      }
      if (poXyspdzxx != null) {
        this.setXydz_dzmc(poXyspdzxx.getDzmc());
        this.setXydz_ms(poXyspdzxx.getMs());
        this.setXydz_qybz(poXyspdzxx.getQybz());
      }
    }
    catch (InvocationTargetException ex) {
    }
    catch (IllegalAccessException ex) {
    }

  }

  public String getCzjg() {
    return czjg;
  }

  public void setCzjg(String czjg) {
    this.czjg = czjg;
  }

  public Long getCzrid() {
    return czrid;
  }

  public void setCzrid(Long czrid) {
    this.czrid = czrid;
  }

  public String getCzsj() {
    return czsj;
  }

  public void setCzsj(String czsj) {
    this.czsj = czsj;
  }

  public String getCzyj() {
    return czyj;
  }

  public void setCzyj(String czyj) {
    this.czyj = czyj;
  }

  public Long getDzid() {
    return dzid;
  }

  public void setDzid(Long dzid) {
    this.dzid = dzid;
  }

  public String getDzmc() {
    return dzmc;
  }

  public void setDzmc(String dzmc) {
    this.dzmc = dzmc;
  }

  public String getMs() {
    return ms;
  }

  public void setMs(String ms) {
    this.ms = ms;
  }

  public String getQybz() {
    return qybz;
  }

  public void setQybz(String qybz) {
    this.qybz = qybz;
  }

  public Long getSplsid() {
    return splsid;
  }

  public void setSplsid(Long splsid) {
    this.splsid = splsid;
  }

  public void setXydz_dzmc(String xydz_dzmc) {
    this.xydz_dzmc = xydz_dzmc;
  }

  public String getXydz_dzmc() {
    return xydz_dzmc;
  }

  public String getXydz_ms() {
    return xydz_ms;
  }

  public void setXydz_ms(String xydz_ms) {
    this.xydz_ms = xydz_ms;
  }

  public String getXydz_qybz() {
    return xydz_qybz;
  }

  public void setXydz_qybz(String xydz_qybz) {
    this.xydz_qybz = xydz_qybz;
  }

  public String getBlyy() {
    return blyy;
  }

  public void setBlyy(String blyy) {
    this.blyy = blyy;
  }

  public String getByzk() {
    return byzk;
  }

  public void setByzk(String byzk) {
    this.byzk = byzk;
  }

  public String getCsdgjdq() {
    return csdgjdq;
  }

  public void setCsdgjdq(String csdgjdq) {
    this.csdgjdq = csdgjdq;
  }

  public String getCsdssxq() {
    return csdssxq;
  }

  public void setCsdssxq(String csdssxq) {
    this.csdssxq = csdssxq;
  }

  public String getCsdxz() {
    return csdxz;
  }

  public void setCsdxz(String csdxz) {
    this.csdxz = csdxz;
  }

  public String getCsrq() {
    return csrq;
  }

  public void setCsrq(String csrq) {
    this.csrq = csrq;
  }

  public String getCssj() {
    return cssj;
  }

  public void setCssj(String cssj) {
    this.cssj = cssj;
  }

  public String getCszmbh() {
    return cszmbh;
  }

  public void setCszmbh(String cszmbh) {
    this.cszmbh = cszmbh;
  }

  public String getCym() {
    return cym;
  }

  public void setCym(String cym) {
    this.cym = cym;
  }

  public Long getDjrid() {
    return djrid;
  }

  public void setDjrid(Long djrid) {
    this.djrid = djrid;
  }

  public String getDjsj() {
    return djsj;
  }

  public void setDjsj(String djsj) {
    this.djsj = djsj;
  }

  public String getFqgmsfhm() {
    return fqgmsfhm;
  }

  public void setFqgmsfhm(String fqgmsfhm) {
    this.fqgmsfhm = fqgmsfhm;
  }

  public String getFqxm() {
    return fqxm;
  }

  public void setFqxm(String fqxm) {
    this.fqxm = fqxm;
  }

  public String getFwcs() {
    return fwcs;
  }

  public void setFwcs(String fwcs) {
    this.fwcs = fwcs;
  }

  public String getGmsfhm() {
    return gmsfhm;
  }

  public void setGmsfhm(String gmsfhm) {
    this.gmsfhm = gmsfhm;
  }

  public String getHb() {
    return hb;
  }

  public void setHb(String hb) {
    this.hb = hb;
  }

  public Long getHjywid() {
    return hjywid;
  }

  public void setHjywid(Long hjywid) {
    this.hjywid = hjywid;
  }

  public String getHyzk() {
    return hyzk;
  }

  public void setHyzk(String hyzk) {
    this.hyzk = hyzk;
  }

  public String getJggjdq() {
    return jggjdq;
  }

  public void setJggjdq(String jggjdq) {
    this.jggjdq = jggjdq;
  }

  public String getJgssxq() {
    return jgssxq;
  }

  public void setJgssxq(String jgssxq) {
    this.jgssxq = jgssxq;
  }

  public String getJhregmsfhm() {
    return jhregmsfhm;
  }

  public void setJhregmsfhm(String jhregmsfhm) {
    this.jhregmsfhm = jhregmsfhm;
  }

  public String getJhrejhgx() {
    return jhrejhgx;
  }

  public void setJhrejhgx(String jhrejhgx) {
    this.jhrejhgx = jhrejhgx;
  }

  public String getJhrexm() {
    return jhrexm;
  }

  public void setJhrexm(String jhrexm) {
    this.jhrexm = jhrexm;
  }

  public String getJhrygmsfhm() {
    return jhrygmsfhm;
  }

  public void setJhrygmsfhm(String jhrygmsfhm) {
    this.jhrygmsfhm = jhrygmsfhm;
  }

  public String getJhryjhgx() {
    return jhryjhgx;
  }

  public void setJhryjhgx(String jhryjhgx) {
    this.jhryjhgx = jhryjhgx;
  }

  public String getJhryxm() {
    return jhryxm;
  }

  public void setJhryxm(String jhryxm) {
    this.jhryxm = jhryxm;
  }

  public String getLsbz() {
    return lsbz;
  }

  public void setLsbz(String lsbz) {
    this.lsbz = lsbz;
  }

  public String getMqgmsfhm() {
    return mqgmsfhm;
  }

  public void setMqgmsfhm(String mqgmsfhm) {
    this.mqgmsfhm = mqgmsfhm;
  }

  public String getMqxm() {
    return mqxm;
  }

  public void setMqxm(String mqxm) {
    this.mqxm = mqxm;
  }

  public String getMz() {
    return mz;
  }

  public void setMz(String mz) {
    this.mz = mz;
  }

  public String getPogmsfhm() {
    return pogmsfhm;
  }

  public void setPogmsfhm(String pogmsfhm) {
    this.pogmsfhm = pogmsfhm;
  }

  public String getPoxm() {
    return poxm;
  }

  public void setPoxm(String poxm) {
    this.poxm = poxm;
  }

  public String getRlhbz() {
    return rlhbz;
  }

  public void setRlhbz(String rlhbz) {
    this.rlhbz = rlhbz;
  }

  public Long getRynbid() {
    return rynbid;
  }

  public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }

  public String getSg() {
    return sg;
  }

  public void setSpjg(String spjg) {
    this.spjg = spjg;
  }

  public String getSpjg() {
    return spjg;
  }

  public void setSg(String sg) {
    this.sg = sg;
  }

  public void setSpmbid(Long spmbid) {
    this.spmbid = spmbid;
  }

  public Long getSpmbid() {
    return spmbid;
  }

  public Long getSpywid() {
    return spywid;
  }

  public void setSpywid(Long spywid) {
    this.spywid = spywid;
  }

  public void setSshh(String sshh) {
    this.sshh = sshh;
  }

  public String getSshh() {
    return sshh;
  }

  public Long getSshhid() {
    return sshhid;
  }

  public String getSshlx() {
    return sshlx;
  }

  public void setSshhid(Long sshhid) {
    this.sshhid = sshhid;
  }

  public void setSshlx(String sshlx) {
    this.sshlx = sshlx;
  }

  public String getSsjlx() {
    return ssjlx;
  }

  public void setSsjlx(String ssjlx) {
    this.ssjlx = ssjlx;
  }

  public String getSsjwh() {
    return ssjwh;
  }

  public void setSsjwh(String ssjwh) {
    this.ssjwh = ssjwh;
  }

  public String getSsjwzrq() {
    return ssjwzrq;
  }

  public void setSsjwzrq(String ssjwzrq) {
    this.ssjwzrq = ssjwzrq;
  }

  public String getSsmlph() {
    return ssmlph;
  }

  public void setSsmlph(String ssmlph) {
    this.ssmlph = ssmlph;
  }

  public String getSspcs() {
    return sspcs;
  }

  public void setSsssxq(String ssssxq) {
    this.ssssxq = ssssxq;
  }

  public String getSsssxq() {
    return ssssxq;
  }

  public void setSspcs(String sspcs) {
    this.sspcs = sspcs;
  }

  public String getSsxz() {
    return ssxz;
  }

  public void setSsxz(String ssxz) {
    this.ssxz = ssxz;
  }

  public String getSsxzjd() {
    return ssxzjd;
  }

  public void setSsxzjd(String ssxzjd) {
    this.ssxzjd = ssxzjd;
  }

  public String getWhcd() {
    return whcd;
  }

  public void setWhcd(String whcd) {
    this.whcd = whcd;
  }

  public String getXb() {
    return xb;
  }

  public void setXb(String xb) {
    this.xb = xb;
  }

  public String getXm() {
    return xm;
  }

  public void setXm(String xm) {
    this.xm = xm;
  }

  public String getXx() {
    return xx;
  }

  public void setXxjb(String xxjb) {
    this.xxjb = xxjb;
  }

  public String getXxjb() {
    return xxjb;
  }

  public void setXx(String xx) {
    this.xx = xx;
  }

  public Long getXydzid() {
    return xydzid;
  }

  public void setXydzid(Long xydzid) {
    this.xydzid = xydzid;
  }

  public String getYhzgx() {
    return yhzgx;
  }

  public void setYhzgx(String yhzgx) {
    this.yhzgx = yhzgx;
  }

  public String getZjxy() {
    return zjxy;
  }

  public void setZjxy(String zjxy) {
    this.zjxy = zjxy;
  }

  public String getZylb() {
    return zylb;
  }

  public void setZy(String zy) {
    this.zy = zy;
  }

  public void setZylb(String zylb) {
    this.zylb = zylb;
  }

  public String getZy() {
    return zy;
  }

  public String getSpsm() {
    return spsm;
  }

  public void setSpsm(String spsm) {
    this.spsm = spsm;
  }

  public Long getYwxl() {
    return ywxl;
  }

  public void setYwxl(Long ywxl) {
    this.ywxl = ywxl;
  }

}
