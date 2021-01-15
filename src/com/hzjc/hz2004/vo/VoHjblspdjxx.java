package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.*;

/**
 * 户籍补录审批登记信息VO
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: GNT Corp.</p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoHjblspdjxx
    extends DefaultVO {

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
  private String sspcs; //派出所
  private String ssjwh; //居(村)委会
  private String ssjlx; //所属街路巷
  private String ssxzjd; //所属乡镇街道
  private String sszrq; //所属警务责任区
  private String ssmlph; //所属门楼牌号
  private String ssxz; //所属详址
  private Long sshhid; //所属户号ID
  private String sshh; //所属户号
  private String sshlx; //所属户类型
  private String yhzgx; //与户主关系
  //private String rlhbz; //入立户标志
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

  //户证业务ID，特别处理
    private String hzywid;

    private String bzdz;
    private String bzdzid;
    private String bzdzx;
    private String bzdzy;
    private String bzdzst;
    private String jthzl;
	private String	sbsj;
	private String	sbryxm;
	private String	sbrgmsfhm;
	
    public String getSbsj() {
		return sbsj;
	}
	public void setSbsj(String sbsj) {
		this.sbsj = sbsj;
	}
	public String getSbryxm() {
		return sbryxm;
	}
	public void setSbryxm(String sbryxm) {
		this.sbryxm = sbryxm;
	}
	public String getSbrgmsfhm() {
		return sbrgmsfhm;
	}
	public void setSbrgmsfhm(String sbrgmsfhm) {
		this.sbrgmsfhm = sbrgmsfhm;
	}
	public String getBzdzst() {
    return bzdzst;
   }
   public void setBzdzst(String bzdzst) {
     this.bzdzst = bzdzst;
   }

    public String getBzdzy() {
    return bzdzy;
   }
   public void setBzdzy(String bzdzy) {
     this.bzdzy = bzdzy;
   }

    public String getBzdzx() {
    return bzdzx;
   }
   public void setBzdzx(String bzdzx) {
     this.bzdzx = bzdzx;
   }

    public void setBzdz(String bzdz) {
      this.bzdz = bzdz;
    }

    public String getBzdz() {
      return bzdz;
    }

    public void setBzdzid(String bzdzid) {
      this.bzdzid = bzdzid;
    }

    public String getBzdzid() {
      return bzdzid;
  }

    public String getHzywid() {
      return hzywid;
    }

    public void setHzywid(String hzywid) {
      this.hzywid = hzywid;
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

  public String getSg() {
    return sg;
  }

  public void setSg(String sg) {
    this.sg = sg;
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

  public String getZjxy() {
    return zjxy;
  }

  public void setZjxy(String zjxy) {
    this.zjxy = zjxy;
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

  public String getSszrq() {
    return sszrq;
  }

  public void setSszrq(String sszrq) {
    this.sszrq = sszrq;
  }

  public String getHjbllb() {
    return hjbllb;
  }

  public void setHjbllb(String hjbllb) {
    this.hjbllb = hjbllb;
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
