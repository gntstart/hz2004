package com.hzjc.hz2004.vo;

import com.hzjc.hz2004.po.*;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.*;

/**
 * <p>Title: Hz2004</p>
 * <p>Description: 常住人口管理系统Hz2004版</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 浙江金铖华元新技术有限公司</p>
 * @author kgb_hz@126.com,kgb@primetech.com.cn
 * @version 1.0
 */

public class VoSlxxHqFhxx {

  /**
   *
   */
  public VoSlxxHqFhxx() {
  }

  /**
   *
   * @param poZJYW_SLXXB
   * @param poHJXX_CZRKJBXX
   * @param poZJLS_SFZYWCZB
   */
  public VoSlxxHqFhxx(PoZJYW_SLXXB poZJYW_SLXXB,
                      PoHJXX_CZRKJBXXB poHJXX_CZRKJBXX,
                      PoZJLS_SFZYWCZB poZJLS_SFZYWCZB,
                      PoHJXX_MLPXXXXB poHJXX_MLPXXXXB) {
    try {
      if (poZJYW_SLXXB != null) {
        BeanUtils.copyProperties(this, poZJYW_SLXXB);
      }
      if (poHJXX_CZRKJBXX != null) {
        BeanUtils.copyProperties(this, poHJXX_CZRKJBXX);
        this.setRyzpid(poHJXX_CZRKJBXX.getZpid());
      }
      if (poZJLS_SFZYWCZB != null) {
        BeanUtils.copyProperties(this, poZJLS_SFZYWCZB);
      }
      if (poHJXX_MLPXXXXB != null) {
        BeanUtils.copyProperties(this, poHJXX_MLPXXXXB);
      }
    }
    catch (InvocationTargetException ex) {
    }
    catch (IllegalAccessException ex) {
    }
  }

  //受理信息
  private Long nbslid;
  private String slh;
  private Long rynbid;
  private Long zpid;
  private String qfjg;
  private String yxqxqsrq;
  private String yxqxjzrq;
  private String zz;
  private String slyy;
  private String zzlx;
  private String lqfs;
  private String sflx;
  private Long sfje;
  private String slzt;
  private Long zjywid;

  //常住人口基本信息
  //private Long rynbid;
  private Long ryid;
  private Long hhnbid;
  private Long mlpnbid;
  private String gmsfhm;
  private String xm;
  private String cym;
  private String xmpy;
  private String cympy;
  private String xb;
  private String mz;
  private String csrq;
  private String cssj;
  private String csdgjdq;
  private String csdssxq;
  private String csdxz;
  private String jhryxm;
  private String jhrygmsfhm;
  private String jhryjhgx;
  private String jhrexm;
  private String jhregmsfhm;
  private String jhrejhgx;
  private String fqxm;
  private String fqgmsfhm;
  private String mqxm;
  private String mqgmsfhm;
  private String poxm;
  private String pogmsfhm;
  private String jggjdq;
  private String jgssxq;
  private String zjxy;
  private String whcd;
  private String hyzk;
  private String byzk;
  private String sg;
  private String xx;
  private String zy;
  private String zylb;
  private String fwcs;
  private String xxjb;
  private String rylb;
  private String hb;
  private String yhzgx;
  private String ryzt;
  private Long lxdbid;
  private String bz;
  private String jlbz;
  private String qysj;
  private String jssj;
  private Long ryzpid; //常住人口人员照片

  //身份证业务操作表信息
  //private Long zjywid;
  private String ywbz;
  private Long czyid;
  private String czsj;
  private String cxbz;
  private String cxsj;
  private Long cxrid;
  private Long cxzjywid;

  //门楼牌详细信息
  private String mlph;
  private String mlxz;
  private String pcs;
  private String zrq;
  private String xzjd;
  private String jcwh;
  private String jlrq;
  private String jllb;
  private Long jlrid;
  private Long cjhjywid;
  private String ccrq;
  private String cxlb;
  private Long cdrid;
  private Long cchjywid;
  private String mlpzt;
  //private Long lxdbid;
  //private String jlbz;
  //private String qysj;
  //private String jssj;
  private String pxh;
  //private Long mlpnbid;
  private Long mlpid;
  private String ssxq;
  private String jlx;

  public String getByzk() {
    return byzk;
  }

  public String getBz() {
    return bz;
  }

  public String getCsdgjdq() {
    return csdgjdq;
  }

  public String getCsdssxq() {
    return csdssxq;
  }

  public String getCsdxz() {
    return csdxz;
  }

  public void setByzk(String byzk) {
    this.byzk = byzk;
  }

  public void setBz(String bz) {
    this.bz = bz;
  }

  public void setCsdgjdq(String csdgjdq) {
    this.csdgjdq = csdgjdq;
  }

  public void setCsdssxq(String csdssxq) {
    this.csdssxq = csdssxq;
  }

  public String getCsrq() {
    return csrq;
  }

  public String getCssj() {
    return cssj;
  }

  public String getCxbz() {
    return cxbz;
  }

  public Long getCxrid() {
    return cxrid;
  }

  public String getCxsj() {
    return cxsj;
  }

  public Long getCxzjywid() {
    return cxzjywid;
  }

  public String getCym() {
    return cym;
  }

  public String getCympy() {
    return cympy;
  }

  public String getCzsj() {
    return czsj;
  }

  public Long getCzyid() {
    return czyid;
  }

  public String getFqgmsfhm() {
    return fqgmsfhm;
  }

  public String getFqxm() {
    return fqxm;
  }

  public String getFwcs() {
    return fwcs;
  }

  public String getGmsfhm() {
    return gmsfhm;
  }

  public String getHb() {
    return hb;
  }

  public Long getHhnbid() {
    return hhnbid;
  }

  public String getHyzk() {
    return hyzk;
  }

  public String getJggjdq() {
    return jggjdq;
  }

  public String getJgssxq() {
    return jgssxq;
  }

  public String getJhregmsfhm() {
    return jhregmsfhm;
  }

  public String getJhrejhgx() {
    return jhrejhgx;
  }

  public String getJhrexm() {
    return jhrexm;
  }

  public String getJhrygmsfhm() {
    return jhrygmsfhm;
  }

  public String getJhryjhgx() {
    return jhryjhgx;
  }

  public String getJhryxm() {
    return jhryxm;
  }

  public String getJlbz() {
    return jlbz;
  }

  public String getLqfs() {
    return lqfs;
  }

  public String getJssj() {
    return jssj;
  }

  public Long getMlpnbid() {
    return mlpnbid;
  }

  public Long getLxdbid() {
    return lxdbid;
  }

  public String getMqgmsfhm() {
    return mqgmsfhm;
  }

  public String getMqxm() {
    return mqxm;
  }

  public String getMz() {
    return mz;
  }

  public Long getNbslid() {
    return nbslid;
  }

  public String getPogmsfhm() {
    return pogmsfhm;
  }

  public String getPoxm() {
    return poxm;
  }

  public String getQfjg() {
    return qfjg;
  }

  public String getQysj() {
    return qysj;
  }

  public Long getRyid() {
    return ryid;
  }

  public String getRylb() {
    return rylb;
  }

  public Long getRynbid() {
    return rynbid;
  }

  public String getRyzt() {
    return ryzt;
  }

  public Long getSfje() {
    return sfje;
  }

  public String getSflx() {
    return sflx;
  }

  public String getSg() {
    return sg;
  }

  public String getSlh() {
    return slh;
  }

  public String getSlyy() {
    return slyy;
  }

  public String getSlzt() {
    return slzt;
  }

  public String getWhcd() {
    return whcd;
  }

  public String getXb() {
    return xb;
  }

  public String getXm() {
    return xm;
  }

  public String getXmpy() {
    return xmpy;
  }

  public String getXx() {
    return xx;
  }

  public String getXxjb() {
    return xxjb;
  }

  public String getYhzgx() {
    return yhzgx;
  }

  public String getYwbz() {
    return ywbz;
  }

  public String getYxqxjzrq() {
    return yxqxjzrq;
  }

  public String getYxqxqsrq() {
    return yxqxqsrq;
  }

  public String getZjxy() {
    return zjxy;
  }

  public Long getZjywid() {
    return zjywid;
  }

  public Long getZpid() {
    return zpid;
  }

  public String getZy() {
    return zy;
  }

  public String getZylb() {
    return zylb;
  }

  public String getZz() {
    return zz;
  }

  public String getZzlx() {
    return zzlx;
  }

  public void setZzlx(String zzlx) {
    this.zzlx = zzlx;
  }

  public void setZz(String zz) {
    this.zz = zz;
  }

  public void setZylb(String zylb) {
    this.zylb = zylb;
  }

  public void setZy(String zy) {
    this.zy = zy;
  }

  public void setZpid(Long zpid) {
    this.zpid = zpid;
  }

  public void setZjywid(Long zjywid) {
    this.zjywid = zjywid;
  }

  public void setZjxy(String zjxy) {
    this.zjxy = zjxy;
  }

  public void setYxqxqsrq(String yxqxqsrq) {
    this.yxqxqsrq = yxqxqsrq;
  }

  public void setYwbz(String ywbz) {
    this.ywbz = ywbz;
  }

  public void setYxqxjzrq(String yxqxjzrq) {
    this.yxqxjzrq = yxqxjzrq;
  }

  public void setYhzgx(String yhzgx) {
    this.yhzgx = yhzgx;
  }

  public void setXxjb(String xxjb) {
    this.xxjb = xxjb;
  }

  public void setXx(String xx) {
    this.xx = xx;
  }

  public void setXmpy(String xmpy) {
    this.xmpy = xmpy;
  }

  public void setXm(String xm) {
    this.xm = xm;
  }

  public void setXb(String xb) {
    this.xb = xb;
  }

  public void setWhcd(String whcd) {
    this.whcd = whcd;
  }

  public void setSlzt(String slzt) {
    this.slzt = slzt;
  }

  public void setSlyy(String slyy) {
    this.slyy = slyy;
  }

  public void setSlh(String slh) {
    this.slh = slh;
  }

  public void setSg(String sg) {
    this.sg = sg;
  }

  public void setSflx(String sflx) {
    this.sflx = sflx;
  }

  public void setSfje(Long sfje) {
    this.sfje = sfje;
  }

  public void setRyzt(String ryzt) {
    this.ryzt = ryzt;
  }

  public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }

  public void setRylb(String rylb) {
    this.rylb = rylb;
  }

  public void setRyid(Long ryid) {
    this.ryid = ryid;
  }

  public void setQysj(String qysj) {
    this.qysj = qysj;
  }

  public void setQfjg(String qfjg) {
    this.qfjg = qfjg;
  }

  public void setPoxm(String poxm) {
    this.poxm = poxm;
  }

  public void setPogmsfhm(String pogmsfhm) {
    this.pogmsfhm = pogmsfhm;
  }

  public void setNbslid(Long nbslid) {
    this.nbslid = nbslid;
  }

  public void setMz(String mz) {
    this.mz = mz;
  }

  public void setMqxm(String mqxm) {
    this.mqxm = mqxm;
  }

  public void setMqgmsfhm(String mqgmsfhm) {
    this.mqgmsfhm = mqgmsfhm;
  }

  public void setMlpnbid(Long mlpnbid) {
    this.mlpnbid = mlpnbid;
  }

  public void setLxdbid(Long lxdbid) {
    this.lxdbid = lxdbid;
  }

  public void setLqfs(String lqfs) {
    this.lqfs = lqfs;
  }

  public void setJssj(String jssj) {
    this.jssj = jssj;
  }

  public void setJlbz(String jlbz) {
    this.jlbz = jlbz;
  }

  public void setJhryxm(String jhryxm) {
    this.jhryxm = jhryxm;
  }

  public void setJhryjhgx(String jhryjhgx) {
    this.jhryjhgx = jhryjhgx;
  }

  public void setJhrygmsfhm(String jhrygmsfhm) {
    this.jhrygmsfhm = jhrygmsfhm;
  }

  public void setJhrexm(String jhrexm) {
    this.jhrexm = jhrexm;
  }

  public void setJhrejhgx(String jhrejhgx) {
    this.jhrejhgx = jhrejhgx;
  }

  public void setJhregmsfhm(String jhregmsfhm) {
    this.jhregmsfhm = jhregmsfhm;
  }

  public void setJgssxq(String jgssxq) {
    this.jgssxq = jgssxq;
  }

  public void setJggjdq(String jggjdq) {
    this.jggjdq = jggjdq;
  }

  public void setHyzk(String hyzk) {
    this.hyzk = hyzk;
  }

  public void setHhnbid(Long hhnbid) {
    this.hhnbid = hhnbid;
  }

  public void setHb(String hb) {
    this.hb = hb;
  }

  public void setGmsfhm(String gmsfhm) {
    this.gmsfhm = gmsfhm;
  }

  public void setFwcs(String fwcs) {
    this.fwcs = fwcs;
  }

  public void setFqxm(String fqxm) {
    this.fqxm = fqxm;
  }

  public void setFqgmsfhm(String fqgmsfhm) {
    this.fqgmsfhm = fqgmsfhm;
  }

  public void setCzyid(Long czyid) {
    this.czyid = czyid;
  }

  public void setCzsj(String czsj) {
    this.czsj = czsj;
  }

  public void setCympy(String cympy) {
    this.cympy = cympy;
  }

  public void setCym(String cym) {
    this.cym = cym;
  }

  public void setCxzjywid(Long cxzjywid) {
    this.cxzjywid = cxzjywid;
  }

  public void setCxsj(String cxsj) {
    this.cxsj = cxsj;
  }

  public void setCxrid(Long cxrid) {
    this.cxrid = cxrid;
  }

  public void setCxbz(String cxbz) {
    this.cxbz = cxbz;
  }

  public void setCssj(String cssj) {
    this.cssj = cssj;
  }

  public void setCsrq(String csrq) {
    this.csrq = csrq;
  }

  public void setCsdxz(String csdxz) {
    this.csdxz = csdxz;
  }

  public Long getCchjywid() {
    return cchjywid;
  }

  public String getCcrq() {
    return ccrq;
  }

  public Long getCdrid() {
    return cdrid;
  }

  public Long getCjhjywid() {
    return cjhjywid;
  }

  public void setCchjywid(Long cchjywid) {
    this.cchjywid = cchjywid;
  }

  public void setCcrq(String ccrq) {
    this.ccrq = ccrq;
  }

  public void setCdrid(Long cdrid) {
    this.cdrid = cdrid;
  }

  public void setCjhjywid(Long cjhjywid) {
    this.cjhjywid = cjhjywid;
  }

  public String getCxlb() {
    return cxlb;
  }

  public void setCxlb(String cxlb) {
    this.cxlb = cxlb;
  }

  public String getJcwh() {
    return jcwh;
  }

  public void setJcwh(String jcwh) {
    this.jcwh = jcwh;
  }

  public void setJllb(String jllb) {
    this.jllb = jllb;
  }

  public void setJlrid(Long jlrid) {
    this.jlrid = jlrid;
  }

  public void setJlrq(String jlrq) {
    this.jlrq = jlrq;
  }

  public void setJlx(String jlx) {
    this.jlx = jlx;
  }

  public String getJlx() {
    return jlx;
  }

  public String getJlrq() {
    return jlrq;
  }

  public Long getJlrid() {
    return jlrid;
  }

  public String getJllb() {
    return jllb;
  }

  public String getMlph() {
    return mlph;
  }

  public Long getMlpid() {
    return mlpid;
  }

  public void setMlph(String mlph) {
    this.mlph = mlph;
  }

  public void setMlpid(Long mlpid) {
    this.mlpid = mlpid;
  }

  public void setMlpzt(String mlpzt) {
    this.mlpzt = mlpzt;
  }

  public void setMlxz(String mlxz) {
    this.mlxz = mlxz;
  }

  public String getMlpzt() {
    return mlpzt;
  }

  public String getMlxz() {
    return mlxz;
  }

  public String getPcs() {
    return pcs;
  }

  public String getPxh() {
    return pxh;
  }

  public void setPxh(String pxh) {
    this.pxh = pxh;
  }

  public void setPcs(String pcs) {
    this.pcs = pcs;
  }

  public String getSsxq() {
    return ssxq;
  }

  public String getXzjd() {
    return xzjd;
  }

  public void setXzjd(String xzjd) {
    this.xzjd = xzjd;
  }

  public void setSsxq(String ssxq) {
    this.ssxq = ssxq;
  }

  public String getZrq() {
    return zrq;
  }

  public void setZrq(String zrq) {
    this.zrq = zrq;
  }

  public Long getRyzpid() {
    return ryzpid;
  }

  public void setRyzpid(Long ryzpid) {
    this.ryzpid = ryzpid;
  }

}