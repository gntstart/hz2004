package com.hzjc.hz2004.vo;

import com.hzjc.hz2004.po.*;
import com.hzjc.wsstruts.vo.DefaultVO;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.*;
import com.hzjc.hz2004.base.login.AuthToken;

/**
 * 人户地信息VO
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoRhdxx
    extends DefaultVO {

  public VoRhdxx() {
  }

  public VoRhdxx(PoHJXX_CZRKJBXXB poRyxx, PoHJXX_HXXB poHxx,
                 PoHJXX_MLPXXXXB poMlpxxxx, AuthToken userinfo) {
    try {
      if (poMlpxxxx != null) {
        BeanUtils.copyProperties(this, poMlpxxxx);
      }
      if (poHxx != null) {
        BeanUtils.copyProperties(this, poHxx);
      }
      if (poRyxx != null) {
        BeanUtils.copyProperties(this, poRyxx);
      }
      if (userinfo != null) {
          this.setYhid(userinfo.getUser().getYhid());
          this.setYhdlm(userinfo.getYhdlm());
          this.setJyh(userinfo.getUser().getJyh());
          this.setDwdm(userinfo.getUser().getDwdm());
          this.setYhxm(userinfo.getUser().getYhxm());
          this.setYhxb(userinfo.getUser().getYhxb());
          this.setYhzw(userinfo.getUser().getYhzw());
          this.setYhmj(userinfo.getUser().getYhmj());
          this.setCzmj(userinfo.getUser().getCzmj());
          this.setYhzt(userinfo.getUser().getYhzt());
      }
    }
    catch (InvocationTargetException ex) {
    }
    catch (IllegalAccessException ex) {
    }

  }

  public VoRhdxx(PoHJXX_CZRKJBXXB poRyxxnew, PoHJXX_HXXB poHxx,
                 PoHJXX_MLPXXXXB poMlpxxxx, AuthToken userinfo,
                 PoHJXX_CZRKJBXXB poRyxxold) {
    try {
      if (poMlpxxxx != null) {
        BeanUtils.copyProperties(this, poMlpxxxx);
      }
      if (poHxx != null) {
        BeanUtils.copyProperties(this, poHxx);
      }
      if (poRyxxnew != null) {
        BeanUtils.copyProperties(this, poRyxxnew);
      }
      if (userinfo != null) {
          this.setYhid(userinfo.getUser().getYhid());
          this.setYhdlm(userinfo.getUser().getYhdlm());
          this.setJyh(userinfo.getUser().getJyh());
          this.setDwdm(userinfo.getUser().getDwdm());
          this.setYhxm(userinfo.getUser().getYhxm());
          this.setYhxb(userinfo.getUser().getYhxb());
          this.setYhzw(userinfo.getUser().getYhzw());
          this.setYhmj(userinfo.getUser().getYhmj());
          this.setCzmj(userinfo.getUser().getCzmj());
          this.setYhzt(userinfo.getUser().getYhzt());
      }
      if (poRyxxold != null) {
        this.setBdqhb(poRyxxold.getHb());
        this.setBdhhb(poRyxxnew.getHb());

      }
    }
    catch (InvocationTargetException ex) {
    }
    catch (IllegalAccessException ex) {
    }
  }

  //操作员信息
  private Long yhid; //用户ID
  private String yhdlm; //用户登录名
  private String jyh; //警员号
  private String dwdm; //单位代码
  private String yhxm; //用户姓名
  private String yhxb; //用户性别
  private String yhzw; //用户职务
  private String yhmj; //用户密级
  private String czmj; //操作密级
  private String yhzt; //用户状态

  //审批相关
  private String qrlb; //迁入类别
  private String hbbglb; //户别变更类别
  private String hjbllb; //户籍补录类别
  private String hjsclb; //户籍删除类别
  private String bdqhb; //变动前户别
  private String bdhhb; //变动后户别

  //人信息
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
  private String rysdzt;
  private String hsql;
  private String hyql;
  private String hgjdqql;
  private String hssxqql;
  private String hxzql;
  private String swrq;
  private String swzxlb;
  private String qcrq;
  private String qczxlb;
  private String qwdgjdq;
  private String qwdssxq;
  private String qwdxz;
  private Long cjhjywid;
  private Long cchjywid;
  private String dhhm;
  private String cszmbh;
  private String cszqfrq;
  private String qtzz;
  private String hylb;
  private String qtssxq;
  private String ywnr;
  private Long rynbid;
  private Long ryid;
  private Long hhnbid;
  private Long mlpnbid;
  private Long zpid;
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

  //户信息
  private String chlb;
  private String ywlx;
  private String jhlb;
  //private Long hhnbid;
  private Long hhid;
  //private Long mlpnbid;
  private String hh;
  private String hlx;
  //private Long cjhjywid;
  //private Long cchjywid;
  private String hhzt;
  //private Long lxdbid;
  //private String jlbz;
  //private String qysj;
  //private String jssj;

  //地信息
  private String cdlb;
  private String jdlb;
  //private Long mlpnbid;
  private Long mlpid;
  private String ssxq;
  private String jlx;
  private String mlph;
  private String mlxz;
  private String pcs;
  private String zrq;
  private String xzjd;
  private String jcwh;
  //private Long cjhjywid;
  //private Long cchjywid;
  private String mlpzt;
  //private Long lxdbid;
  //private String jlbz;
  //private String qysj;
  //private String jssj;
  private String pxh;

  public String getByzk() {
    return byzk;
  }

  public void setByzk(String byzk) {
    this.byzk = byzk;
  }

  public String getBz() {
    return bz;
  }

  public void setBz(String bz) {
    this.bz = bz;
  }

  public Long getCchjywid() {
    return cchjywid;
  }

  public void setCchjywid(Long cchjywid) {
    this.cchjywid = cchjywid;
  }

  public String getCdlb() {
    return cdlb;
  }

  public void setCdlb(String cdlb) {
    this.cdlb = cdlb;
  }

  public String getChlb() {
    return chlb;
  }

  public void setChlb(String chlb) {
    this.chlb = chlb;
  }

  public Long getCjhjywid() {
    return cjhjywid;
  }

  public void setCjhjywid(Long cjhjywid) {
    this.cjhjywid = cjhjywid;
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

  public String getCszqfrq() {
    return cszqfrq;
  }

  public void setCszqfrq(String cszqfrq) {
    this.cszqfrq = cszqfrq;
  }

  public String getCym() {
    return cym;
  }

  public void setCym(String cym) {
    this.cym = cym;
  }

  public String getCympy() {
    return cympy;
  }

  public void setCympy(String cympy) {
    this.cympy = cympy;
  }

  public String getDhhm() {
    return dhhm;
  }

  public void setDhhm(String dhhm) {
    this.dhhm = dhhm;
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

  public String getHgjdqql() {
    return hgjdqql;
  }

  public void setHgjdqql(String hgjdqql) {
    this.hgjdqql = hgjdqql;
  }

  public String getHh() {
    return hh;
  }

  public void setHh(String hh) {
    this.hh = hh;
  }

  public Long getHhid() {
    return hhid;
  }

  public Long getHhnbid() {
    return hhnbid;
  }

  public void setHhnbid(Long hhnbid) {
    this.hhnbid = hhnbid;
  }

  public String getHhzt() {
    return hhzt;
  }

  public void setHhzt(String hhzt) {
    this.hhzt = hhzt;
  }

  public String getHlx() {
    return hlx;
  }

  public void setHlx(String hlx) {
    this.hlx = hlx;
  }

  public String getHsql() {
    return hsql;
  }

  public void setHsql(String hsql) {
    this.hsql = hsql;
  }

  public String getHssxqql() {
    return hssxqql;
  }

  public void setHssxqql(String hssxqql) {
    this.hssxqql = hssxqql;
  }

  public String getHxzql() {
    return hxzql;
  }

  public void setHxzql(String hxzql) {
    this.hxzql = hxzql;
  }

  public String getHylb() {
    return hylb;
  }

  public void setHyql(String hyql) {
    this.hyql = hyql;
  }

  public void setHylb(String hylb) {
    this.hylb = hylb;
  }

  public String getHyql() {
    return hyql;
  }

  public String getHyzk() {
    return hyzk;
  }

  public void setHyzk(String hyzk) {
    this.hyzk = hyzk;
  }

  public String getJcwh() {
    return jcwh;
  }

  public void setJcwh(String jcwh) {
    this.jcwh = jcwh;
  }

  public String getJdlb() {
    return jdlb;
  }

  public void setJdlb(String jdlb) {
    this.jdlb = jdlb;
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

  public String getJhlb() {
    return jhlb;
  }

  public void setJhlb(String jhlb) {
    this.jhlb = jhlb;
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

  public String getJlbz() {
    return jlbz;
  }

  public void setJlbz(String jlbz) {
    this.jlbz = jlbz;
  }

  public String getJlx() {
    return jlx;
  }

  public void setJlx(String jlx) {
    this.jlx = jlx;
  }

  public String getJssj() {
    return jssj;
  }

  public void setJssj(String jssj) {
    this.jssj = jssj;
  }

  public Long getLxdbid() {
    return lxdbid;
  }

  public void setLxdbid(Long lxdbid) {
    this.lxdbid = lxdbid;
  }

  public String getMlph() {
    return mlph;
  }

  public void setMlph(String mlph) {
    this.mlph = mlph;
  }

  public Long getMlpid() {
    return mlpid;
  }

  public void setMlpid(Long mlpid) {
    this.mlpid = mlpid;
  }

  public Long getMlpnbid() {
    return mlpnbid;
  }

  public void setMlpnbid(Long mlpnbid) {
    this.mlpnbid = mlpnbid;
  }

  public String getMlpzt() {
    return mlpzt;
  }

  public void setMlpzt(String mlpzt) {
    this.mlpzt = mlpzt;
  }

  public String getMlxz() {
    return mlxz;
  }

  public void setMlxz(String mlxz) {
    this.mlxz = mlxz;
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

  public String getPcs() {
    return pcs;
  }

  public void setPcs(String pcs) {
    this.pcs = pcs;
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

  public String getPxh() {
    return pxh;
  }

  public void setPxh(String pxh) {
    this.pxh = pxh;
  }

  public String getQcrq() {
    return qcrq;
  }

  public void setQcrq(String qcrq) {
    this.qcrq = qcrq;
  }

  public String getQczxlb() {
    return qczxlb;
  }

  public void setQczxlb(String qczxlb) {
    this.qczxlb = qczxlb;
  }

  public String getQtssxq() {
    return qtssxq;
  }

  public void setQtssxq(String qtssxq) {
    this.qtssxq = qtssxq;
  }

  public String getQtzz() {
    return qtzz;
  }

  public void setQtzz(String qtzz) {
    this.qtzz = qtzz;
  }

  public String getQwdgjdq() {
    return qwdgjdq;
  }

  public void setQwdgjdq(String qwdgjdq) {
    this.qwdgjdq = qwdgjdq;
  }

  public String getQwdssxq() {
    return qwdssxq;
  }

  public void setQwdssxq(String qwdssxq) {
    this.qwdssxq = qwdssxq;
  }

  public String getQwdxz() {
    return qwdxz;
  }

  public void setQwdxz(String qwdxz) {
    this.qwdxz = qwdxz;
  }

  public String getQysj() {
    return qysj;
  }

  public void setQysj(String qysj) {
    this.qysj = qysj;
  }

  public Long getRyid() {
    return ryid;
  }

  public void setRyid(Long ryid) {
    this.ryid = ryid;
  }

  public String getRylb() {
    return rylb;
  }

  public void setRylb(String rylb) {
    this.rylb = rylb;
  }

  public Long getRynbid() {
    return rynbid;
  }

  public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }

  public String getRysdzt() {
    return rysdzt;
  }

  public void setRysdzt(String rysdzt) {
    this.rysdzt = rysdzt;
  }

  public String getRyzt() {
    return ryzt;
  }

  public void setRyzt(String ryzt) {
    this.ryzt = ryzt;
  }

  public String getSg() {
    return sg;
  }

  public void setSg(String sg) {
    this.sg = sg;
  }

  public String getSsxq() {
    return ssxq;
  }

  public void setSsxq(String ssxq) {
    this.ssxq = ssxq;
  }

  public String getSwrq() {
    return swrq;
  }

  public void setSwrq(String swrq) {
    this.swrq = swrq;
  }

  public String getSwzxlb() {
    return swzxlb;
  }

  public void setSwzxlb(String swzxlb) {
    this.swzxlb = swzxlb;
  }

  public String getWhcd() {
    return whcd;
  }

  public void setXb(String xb) {
    this.xb = xb;
  }

  public String getXb() {
    return xb;
  }

  public String getXm() {
    return xm;
  }

  public void setXm(String xm) {
    this.xm = xm;
  }

  public String getXmpy() {
    return xmpy;
  }

  public void setXmpy(String xmpy) {
    this.xmpy = xmpy;
  }

  public String getXx() {
    return xx;
  }

  public void setXx(String xx) {
    this.xx = xx;
  }

  public String getXxjb() {
    return xxjb;
  }

  public void setXxjb(String xxjb) {
    this.xxjb = xxjb;
  }

  public String getXzjd() {
    return xzjd;
  }

  public void setXzjd(String xzjd) {
    this.xzjd = xzjd;
  }

  public String getYhzgx() {
    return yhzgx;
  }

  public void setYhzgx(String yhzgx) {
    this.yhzgx = yhzgx;
  }

  public String getYwlx() {
    return ywlx;
  }

  public void setYwlx(String ywlx) {
    this.ywlx = ywlx;
  }

  public String getYwnr() {
    return ywnr;
  }

  public void setYwnr(String ywnr) {
    this.ywnr = ywnr;
  }

  public String getZjxy() {
    return zjxy;
  }

  public void setZjxy(String zjxy) {
    this.zjxy = zjxy;
  }

  public Long getZpid() {
    return zpid;
  }

  public void setZpid(Long zpid) {
    this.zpid = zpid;
  }

  public String getZrq() {
    return zrq;
  }

  public void setZrq(String zrq) {
    this.zrq = zrq;
  }

  public String getZy() {
    return zy;
  }

  public void setZy(String zy) {
    this.zy = zy;
  }

  public String getZylb() {
    return zylb;
  }

  public void setZylb(String zylb) {
    this.zylb = zylb;
  }

  public void setWhcd(String whcd) {
    this.whcd = whcd;
  }

  public String getHbbglb() {
    return hbbglb;
  }

  public void setHbbglb(String hbbglb) {
    this.hbbglb = hbbglb;
  }

  public String getHjbllb() {
    return hjbllb;
  }

  public void setHjbllb(String hjbllb) {
    this.hjbllb = hjbllb;
  }

  public String getHjsclb() {
    return hjsclb;
  }

  public void setHjsclb(String hjsclb) {
    this.hjsclb = hjsclb;
  }

  public String getQrlb() {
    return qrlb;
  }

  public void setQrlb(String qrlb) {
    this.qrlb = qrlb;
  }

  public void setHhid(Long hhid) {
    this.hhid = hhid;
  }

  public String getCzmj() {
    return czmj;
  }

  public void setCzmj(String czmj) {
    this.czmj = czmj;
  }

  public String getDwdm() {
    return dwdm;
  }

  public void setDwdm(String dwdm) {
    this.dwdm = dwdm;
  }

  public String getJyh() {
    return jyh;
  }

  public void setJyh(String jyh) {
    this.jyh = jyh;
  }

  public String getYhdlm() {
    return yhdlm;
  }

  public void setYhdlm(String yhdlm) {
    this.yhdlm = yhdlm;
  }

  public Long getYhid() {
    return yhid;
  }

  public void setYhid(Long yhid) {
    this.yhid = yhid;
  }

  public String getYhmj() {
    return yhmj;
  }

  public void setYhmj(String yhmj) {
    this.yhmj = yhmj;
  }

  public String getYhxb() {
    return yhxb;
  }

  public void setYhxb(String yhxb) {
    this.yhxb = yhxb;
  }

  public String getYhxm() {
    return yhxm;
  }

  public void setYhxm(String yhxm) {
    this.yhxm = yhxm;
  }

  public String getYhzt() {
    return yhzt;
  }

  public void setYhzt(String yhzt) {
    this.yhzt = yhzt;
  }

  public String getYhzw() {
    return yhzw;
  }

  public void setYhzw(String yhzw) {
    this.yhzw = yhzw;
  }

  public String getBdqhb() {
    return bdqhb;
  }

  public void setBdqhb(String bdqhb) {
    this.bdqhb = bdqhb;
  }

  public String getBdhhb() {
    return bdhhb;
  }

  public void setBdhhb(String bdhhb) {
    this.bdhhb = bdhhb;
  }


}
