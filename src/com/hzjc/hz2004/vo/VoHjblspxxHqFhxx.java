package com.hzjc.hz2004.vo;

import java.lang.reflect.*;

import org.apache.commons.beanutils.*;
import com.hzjc.hz2004.po.*;
import com.hzjc.wsstruts.vo.*;

public class VoHjblspxxHqFhxx
    extends DefaultVO {

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

  //审批动作信息
  private Long dzid; //动作ID
  private String dzmc; //动作名称
  private String ms; //描述
  private String qybz; //启用标志

  //前台需要
  private String hjbllb; //户籍补录类别


  private String xmx;
  private String xmm;
  private String jgxz;
  private String jhryzjzl;
  private String jhryzjhm;
  private String jhrywwx;
  private String jhrywwm;
  private String jhrylxdh;
  private String jhrezjzl;
  private String jhrezjhm;
  private String jhrewwx;
  private String jhrewwm;
  private String jhrelxdh;
  private String fqzjzl;
  private String fqzjhm;
  private String fqwwx;
  private String fqwwm;
  private String mqzjzl;
  private String mqzjhm;
  private String mqwwx;
  private String mqwwm;
  private String pozjzl;
  private String pozjhm;
  private String powwx;
  private String powwm;
  private String qyldyy;
  private String  sbrjtgx;
  private String jthzl;

  public VoHjblspxxHqFhxx() {
  }

  public VoHjblspxxHqFhxx(PoHJSP_HJBLSPSQB poHjblspxx, PoXT_SPDZB poSpdzxx) {
    try {
      BeanUtils.copyProperties(this, poHjblspxx);
      if (poSpdzxx != null) {
        BeanUtils.copyProperties(this, poSpdzxx);
      }
    }
    catch (InvocationTargetException ex) {
    }
    catch (IllegalAccessException ex) {
    }

    this.setHjbllb(this.getBlyy());
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

  public String getMs() {
    return ms;
  }

  public void setMs(String ms) {
    this.ms = ms;
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

  public String getQybz() {
    return qybz;
  }

  public void setQybz(String qybz) {
    this.qybz = qybz;
  }

  public String getRlhbz() {
    return rlhbz;
  }

  public void setRlhbz(String rlhbz) {
    this.rlhbz = rlhbz;
  }

  public void setLsbz(String lsbz) {
    this.lsbz = lsbz;
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

  public void setSg(String sg) {
    this.sg = sg;
  }

  public String getSpjg() {
    return spjg;
  }

  public Long getSpmbid() {
    return spmbid;
  }

  public void setSpmbid(Long spmbid) {
    this.spmbid = spmbid;
  }

  public Long getSpywid() {
    return spywid;
  }

  public void setSpywid(Long spywid) {
    this.spywid = spywid;
  }

  public String getSshh() {
    return sshh;
  }

  public void setSshh(String sshh) {
    this.sshh = sshh;
  }

  public Long getSshhid() {
    return sshhid;
  }

  public void setSshhid(Long sshhid) {
    this.sshhid = sshhid;
  }

  public String getSshlx() {
    return sshlx;
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

  public void setSspcs(String sspcs) {
    this.sspcs = sspcs;
  }

  public String getSsssxq() {
    return ssssxq;
  }

  public void setSsssxq(String ssssxq) {
    this.ssssxq = ssssxq;
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

  public void setXx(String xx) {
    this.xx = xx;
  }

  public void setXxjb(String xxjb) {
    this.xxjb = xxjb;
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

  public String getZy() {
    return zy;
  }

  public void setZjxy(String zjxy) {
    this.zjxy = zjxy;
  }

  public void setZy(String zy) {
    this.zy = zy;
  }

  public String getZjxy() {
    return zjxy;
  }

  public String getZylb() {
    return zylb;
  }

  public void setZylb(String zylb) {
    this.zylb = zylb;
  }

  public String getXxjb() {
    return xxjb;
  }

  public void setSpjg(String spjg) {
    this.spjg = spjg;
  }

  public String getHjbllb() {
    return hjbllb;
  }

  public void setHjbllb(String hjbllb) {
    this.hjbllb = hjbllb;
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


  public String getXmx() {
          return xmx;
  }
  public void setXmx(String xmx) {
          this.xmx = xmx;
  }
  public String getXmm() {
          return xmm;
  }
  public void setXmm(String xmm) {
          this.xmm = xmm;
  }
  public String getJgxz() {
          return jgxz;
  }
  public void setJgxz(String jgxz) {
          this.jgxz = jgxz;
  }
  public String getJhryzjzl() {
          return jhryzjzl;
  }
  public void setJhryzjzl(String jhryzjzl) {
          this.jhryzjzl = jhryzjzl;
  }
  public String getJhryzjhm() {
          return jhryzjhm;
  }
  public void setJhryzjhm(String jhryzjhm) {
          this.jhryzjhm = jhryzjhm;
  }
  public String getJhrywwx() {
          return jhrywwx;
  }
  public void setJhrywwx(String jhrywwx) {
          this.jhrywwx = jhrywwx;
  }
  public String getJhrywwm() {
          return jhrywwm;
  }
  public void setJhrywwm(String jhrywwm) {
          this.jhrywwm = jhrywwm;
  }
  public String getJhrylxdh() {
          return jhrylxdh;
  }
  public void setJhrylxdh(String jhrylxdh) {
          this.jhrylxdh = jhrylxdh;
  }
  public String getJhrezjzl() {
          return jhrezjzl;
  }
  public void setJhrezjzl(String jhrezjzl) {
          this.jhrezjzl = jhrezjzl;
  }
  public String getJhrezjhm() {
          return jhrezjhm;
  }
  public void setJhrezjhm(String jhrezjhm) {
          this.jhrezjhm = jhrezjhm;
  }
  public String getJhrewwx() {
          return jhrewwx;
  }
  public void setJhrewwx(String jhrewwx) {
          this.jhrewwx = jhrewwx;
  }
  public String getJhrewwm() {
          return jhrewwm;
  }
  public void setJhrewwm(String jhrewwm) {
          this.jhrewwm = jhrewwm;
  }
  public String getJhrelxdh() {
          return jhrelxdh;
  }
  public void setJhrelxdh(String jhrelxdh) {
          this.jhrelxdh = jhrelxdh;
  }
  public String getFqzjzl() {
          return fqzjzl;
  }
  public void setFqzjzl(String fqzjzl) {
          this.fqzjzl = fqzjzl;
  }
  public String getFqzjhm() {
          return fqzjhm;
  }
  public void setFqzjhm(String fqzjhm) {
          this.fqzjhm = fqzjhm;
  }
  public String getFqwwx() {
          return fqwwx;
  }
  public void setFqwwx(String fqwwx) {
          this.fqwwx = fqwwx;
  }
  public String getFqwwm() {
          return fqwwm;
  }
  public void setFqwwm(String fqwwm) {
          this.fqwwm = fqwwm;
  }
  public String getMqzjzl() {
          return mqzjzl;
  }
  public void setMqzjzl(String mqzjzl) {
          this.mqzjzl = mqzjzl;
  }
  public String getMqzjhm() {
          return mqzjhm;
  }
  public void setMqzjhm(String mqzjhm) {
          this.mqzjhm = mqzjhm;
  }
  public String getMqwwx() {
          return mqwwx;
  }
  public void setMqwwx(String mqwwx) {
          this.mqwwx = mqwwx;
  }
  public String getMqwwm() {
          return mqwwm;
  }
  public void setMqwwm(String mqwwm) {
          this.mqwwm = mqwwm;
  }
  public String getPozjzl() {
          return pozjzl;
  }
  public void setPozjzl(String pozjzl) {
          this.pozjzl = pozjzl;
  }
  public String getPozjhm() {
          return pozjhm;
  }
  public void setPozjhm(String pozjhm) {
          this.pozjhm = pozjhm;
  }
  public String getPowwx() {
          return powwx;
  }
  public void setPowwx(String powwx) {
          this.powwx = powwx;
  }
  public String getPowwm() {
          return powwm;
  }
  public void setPowwm(String powwm) {
          this.powwm = powwm;
  }
  public String getQyldyy() {
          return qyldyy;
  }
  public void setQyldyy(String qyldyy) {
          this.qyldyy = qyldyy;
  }
  public String getSbrjtgx() {
          return sbrjtgx;
  }

  public String getJthzl() {
    return jthzl;
  }

  public void setSbrjtgx(String sbrjtgx) {
          this.sbrjtgx = sbrjtgx;
  }

  public void setJthzl(String jthzl) {
    this.jthzl = jthzl;
  }

}
