package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.*;

/**
 * 迁入登记信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoQrdjxx
    extends DefaultVO {

  private Long sys_bh; //系统编号

  //迁入审批信息
  private Long spywid; //审批业务ID
  private Long spsqzid; //审批申请子ID

  //回迁信息
  private Long ryid; //人员ID
  private Long sbhjywid; //上笔户籍业务ID

  //人员信息
  private String gmsfhm; //公民身份号码
  private String xm; //姓名
  private String cym; //曾用名
  private String xmpy; //姓名拼音
  private String cympy; //曾用名拼音
  private String xb; //性别
  private String mz; //民族
  private String csrq; //出生日期
  private String cssj; //出生时间
  private String csdgjdq; //出生地国家（地区）
  private String csdssxq; //出生地省市县（区）
  private String csdxz; //出生地详址
  private String jhryxm; //监护人一姓名
  private String jhrygmsfhm; //监护人一公民身份号码
  private String jhryjhgx; //监护人一监护关系
  private String jhrexm; //监护人二姓名
  private String jhregmsfhm; //监护人二公民身份号码
  private String jhrejhgx; //监护人二监护关系
  private String fqxm; //父亲姓名
  private String fqgmsfhm; //父亲公民身份号码
  private String mqxm; //母亲姓名
  private String mqgmsfhm; //母亲公民身份号码
  private String poxm; //配偶姓名
  private String pogmsfhm; //配偶公民身份号码
  private String jggjdq; //籍贯国家（地区）
  private String jgssxq; //籍贯省市县（区）
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
  private String rylb; //人员类别
  private String hb; //户别
  private String yhzgx; //与户主关系
  private String dhhm; //电话号码
  private String cszmbh; //出生证明编号
  private String cszqfrq; //出生证签发日期
  private String qtzz; //其他住址
  private String hylb; //行业类别
  private String qtssxq; //其他省市县（区）
  private String bz; //备注
  private String qrqhb; //迁入前户别

  //迁入登记信息
  private String qrrq; //迁入日期
  private String qrlb; //迁入类别
  private String qcdgjdq; //迁出地国家（地区）
  private String qcdssxq; //迁出地省市县（区）
  private String qcdxz; //迁出地详址
  private String qyzbh; //迁移证编号
  private String zqzbh; //准迁证编号
  private String bdfw; //变动范围

  //证件信息
  private String qfjg; //签发机关
  private String zjlb; //证件类别
  private String yxqxqsrq; //有效期限起始日期
  private String yxqxjzrq; //有效期限截止日期

  //照片信息
  private String zp; //照片

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
  private String qyzhyxx; //迁移证核验信息
  private String cxfldm;
  private String hjdz_cxfldm;
private String nyzyrklhczyydm;
private String zyjszc_pdbz;
private String jndj_pdbz;
private String ncjdzzyxbys_pdbz;
private String jjqx_pdbz;
private String zczjyhjzwnys_pdbz;
private String ncjsbtcxy_pdbz;
  private String jthzl;
    public void setCxfldm(String cxfldm) {
      this.cxfldm = cxfldm;
    }

    public String getCxfldm() {
      return cxfldm;
  }
  public String getQyzhyxx() {
    return qyzhyxx;
  }

  public void setQyzhyxx(String qyzhyxx) {
    this.qyzhyxx = qyzhyxx;
  }


  public String getBdfw() {
    return bdfw;
  }

  public void setBdfw(String bdfw) {
    this.bdfw = bdfw;
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

  public String getQcdgjdq() {
    return qcdgjdq;
  }

  public void setQcdgjdq(String qcdgjdq) {
    this.qcdgjdq = qcdgjdq;
  }

  public String getQcdssxq() {
    return qcdssxq;
  }

  public void setQcdssxq(String qcdssxq) {
    this.qcdssxq = qcdssxq;
  }

  public String getQcdxz() {
    return qcdxz;
  }

  public void setQcdxz(String qcdxz) {
    this.qcdxz = qcdxz;
  }

  public String getQrlb() {
    return qrlb;
  }

  public void setQrlb(String qrlb) {
    this.qrlb = qrlb;
  }

  public String getQrrq() {
    return qrrq;
  }

  public void setQrrq(String qrrq) {
    this.qrrq = qrrq;
  }

  public String getQyzbh() {
    return qyzbh;
  }

  public void setQyzbh(String qyzbh) {
    this.qyzbh = qyzbh;
  }

  public String getRylb() {
    return rylb;
  }

  public void setRylb(String rylb) {
    this.rylb = rylb;
  }

  public String getSg() {
    return sg;
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

  public String getYhzgx() {
    return yhzgx;
  }

  public void setYhzgx(String yhzgx) {
    this.yhzgx = yhzgx;
  }

  public String getYxqxjzrq() {
    return yxqxjzrq;
  }

  public void setYxqxjzrq(String yxqxjzrq) {
    this.yxqxjzrq = yxqxjzrq;
  }

  public String getYxqxqsrq() {
    return yxqxqsrq;
  }

  public void setYxqxqsrq(String yxqxqsrq) {
    this.yxqxqsrq = yxqxqsrq;
  }

  public String getZjxy() {
    return zjxy;
  }

  public void setZjxy(String zjxy) {
    this.zjxy = zjxy;
  }

  public String getZqzbh() {
    return zqzbh;
  }

  public void setZqzbh(String zqzbh) {
    this.zqzbh = zqzbh;
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

  public Long getRyid() {
    return ryid;
  }

  public void setRyid(Long ryid) {
    this.ryid = ryid;
  }

  public String getZp() {
    return zp;
  }

  public void setZp(String zp) {
    this.zp = zp;
  }

  public Long getSbhjywid() {
    return sbhjywid;
  }

  public void setSbhjywid(Long sbhjywid) {
    this.sbhjywid = sbhjywid;
  }

  public String getQfjg() {
    return qfjg;
  }

  public void setQfjg(String qfjg) {
    this.qfjg = qfjg;
  }

  public Long getSys_bh() {
    return sys_bh;
  }

  public void setSys_bh(Long sys_bh) {
    this.sys_bh = sys_bh;
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

  public String getDhhm() {
    return dhhm;
  }

  public void setDhhm(String dhhm) {
    this.dhhm = dhhm;
  }

  public String getHylb() {
    return hylb;
  }

  public void setHylb(String hylb) {
    this.hylb = hylb;
  }

  public String getQtzz() {
    return qtzz;
  }

  public void setQtzz(String qtzz) {
    this.qtzz = qtzz;
  }

  public void setSg(String sg) {
    this.sg = sg;
  }

  public String getQrqhb() {
    return qrqhb;
  }

  public void setQrqhb(String qrqhb) {
    this.qrqhb = qrqhb;
  }

  public String getBz() {
    return bz;
  }

  public void setBz(String bz) {
    this.bz = bz;
  }

  public String getQtssxq() {
    return qtssxq;
  }

  public void setQtssxq(String qtssxq) {
    this.qtssxq = qtssxq;
  }

  public Long getSpsqzid() {
    return spsqzid;
  }

  public void setSpsqzid(Long spsqzid) {
    this.spsqzid = spsqzid;
  }

  public Long getSpywid() {
    return spywid;
  }

  public void setSpywid(Long spywid) {
    this.spywid = spywid;
  }

  public String getZjlb() {
    return zjlb;
  }

  public void setZjlb(String zjlb) {
    this.zjlb = zjlb;
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

  public String getHjdz_cxfldm() {
    return hjdz_cxfldm;
  }

  public String getJjqx_pdbz() {
    return jjqx_pdbz;
  }

  public String getJndj_pdbz() {
    return jndj_pdbz;
  }

  public String getNcjdzzyxbys_pdbz() {
    return ncjdzzyxbys_pdbz;
  }

  public String getNcjsbtcxy_pdbz() {
    return ncjsbtcxy_pdbz;
  }

  public String getNyzyrklhczyydm() {
    return nyzyrklhczyydm;
  }

  public String getZczjyhjzwnys_pdbz() {
    return zczjyhjzwnys_pdbz;
  }

  public String getZyjszc_pdbz() {
    return zyjszc_pdbz;
  }

  public String getJthzl() {
    return jthzl;
  }

  public void setSbrjtgx(String sbrjtgx) {
          this.sbrjtgx = sbrjtgx;
  }

  public void setHjdz_cxfldm(String hjdz_cxfldm) {
    this.hjdz_cxfldm = hjdz_cxfldm;
  }

  public void setJjqx_pdbz(String jjqx_pdbz) {
    this.jjqx_pdbz = jjqx_pdbz;
  }

  public void setJndj_pdbz(String jndj_pdbz) {
    this.jndj_pdbz = jndj_pdbz;
  }

  public void setNyzyrklhczyydm(String nyzyrklhczyydm) {
    this.nyzyrklhczyydm = nyzyrklhczyydm;
  }

  public void setNcjsbtcxy_pdbz(String ncjsbtcxy_pdbz) {
    this.ncjsbtcxy_pdbz = ncjsbtcxy_pdbz;
  }

  public void setNcjdzzyxbys_pdbz(String ncjdzzyxbys_pdbz) {
    this.ncjdzzyxbys_pdbz = ncjdzzyxbys_pdbz;
  }

  public void setZczjyhjzwnys_pdbz(String zczjyhjzwnys_pdbz) {
    this.zczjyhjzwnys_pdbz = zczjyhjzwnys_pdbz;
  }

  public void setZyjszc_pdbz(String zyjszc_pdbz) {
    this.zyjszc_pdbz = zyjszc_pdbz;
  }

  public void setJthzl(String jthzl) {
    this.jthzl = jthzl;
  }

}
