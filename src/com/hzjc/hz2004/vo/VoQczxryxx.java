package com.hzjc.hz2004.vo;

import java.lang.reflect.*;

import org.apache.commons.beanutils.*;
import com.hzjc.hz2004.po.*;
import com.hzjc.wsstruts.vo.*;

/**
 * 迁出注销人员信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoQczxryxx
    extends DefaultVO {

  public VoQczxryxx() {
  }

  public VoQczxryxx(PoHJXX_CZRKJBXXB poRyxx, PoHJXX_MLPXXXXB poDxx,
                    PoHJYW_QCZXXXB poQczxxx) {

    try {
      BeanUtils.copyProperties(this, poQczxxx);
      BeanUtils.copyProperties(this, poDxx);
      BeanUtils.copyProperties(this, poRyxx);

      //跨地市迁移
      this.setKdqqy_qrdqbm(poQczxxx.getKdqqy_qrdqbm());
    }
    catch (InvocationTargetException ex) {
    }
    catch (IllegalAccessException ex) {
    }

  }

  //人信息
  private Long rynbid; //人员内部ID
  private Long ryid; //人员ID
  private Long hhnbid; //户号内部ID
  private Long mlpnbid; //门（楼）牌内部ID
  private Long zpid; //照片ID
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
  private String dhhm; //电话号码
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
  private String hsql; //何时迁来
  private String hyql; //何因迁来
  private String hgjdqql; //何国家（地区）迁来
  private String hssxqql; //何省市县（区）迁来
  private String hxzql; //何详址迁来
  private String hslbz; //何时来本址
  private String hylbz; //何因来本址
  private String hgjdqlbz; //何国家（地区）来本址
  private String hsssqlbz; //何省市县（区）来本址
  private String hxzlbz; //何详址来本址
  private String swrq; //死亡日期
  private String swzxlb; //死亡注销类别
  private String qcrq; //迁出日期
  private String qczxlb; //迁出注销类别
  //private String qwdgjdq; //迁往地国家（地区）
  //private String qwdssxq; //迁往地省市县（区）
  //private String qwdxz; //迁往地详址
  private String cszmbh; //出生证明编号
  private String cszqfrq; //出生证签发日期
  private String hylb; //行业类别
  private String qtssxq; //其他省市县（区）
  private String qtzz; //其他住址
  private String rylb; //人员类别
  private String hb; //户别
  private String yhzgx; //与户主关系
  private String ryzt; //人员状态
  private String rysdzt; //人员锁定状态
  private Long lxdbid; //离线DBID
  private String bz; //备注
  private String jlbz; //记录标志
  private String ywnr; //业务内容
  private Long cjhjywid; //创建户籍业务ID
  private Long cchjywid; //撤除户籍业务ID
  private String qysj; //起用时间
  private String jssj; //结束时间
  private String cxbz; //冲销标志
  private Long nbsfzid; //内部身份证ID
  private String qfjg; //签发机关
  private String yxqxqsrq; //有效期限起始日期
  private String yxqxjzrq; //有效期限截止日期
  private String swzxrq; //死亡注销日期

  //迁出注销信息
  private Long qczxid; //迁出注销ID
  private String qclb; //迁出类别
  private String qwdgjdq; //迁往地国家（地区）
  private String qwdssxq; //迁往地省市县（区）
  private String qwdxz; //迁往地详址
  private String qyzbh; //迁移证编号
  private String zqzbh; //准迁证编号
  private String bdfw; //变动范围
  private Long hjywid; //户籍业务ID

  //地信息
  private Long mlpid; //门（楼）牌ID
  private String ssxq; //省市县（区）
  private String jlx; //街路巷
  private String mlph; //门（楼）牌号
  private String mlxz; //门（楼）详址
  private String pcs; //派出所
  private String zrq; //责任区
  private String xzjd; //乡镇（街道）
  private String jcwh; //居（村）委会
  private String mlpzt; //门（楼）牌状态
  private String pxh; //排序号
  private String cdlb; //撤地类别
  private String jdlb; //建地类别
  private String jdsj; //建地时间
  private String cdsj; //撤地时间
  private String sbrjtgx;//与持证人关系
  //跨地市迁移标志
  private String kdqqy_qrdqbm;

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

  public String getBz() {
    return bz;
  }

  public void setBz(String bz) {
    this.bz = bz;
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

  public Long getHhnbid() {
    return hhnbid;
  }

  public void setHhnbid(Long hhnbid) {
    this.hhnbid = hhnbid;
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

  public String getJcwh() {
    return jcwh;
  }

  public void setJcwh(String jcwh) {
    this.jcwh = jcwh;
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

  public String getQclb() {
    return qclb;
  }

  public void setQclb(String qclb) {
    this.qclb = qclb;
  }

  public Long getQczxid() {
    return qczxid;
  }

  public void setQczxid(Long qczxid) {
    this.qczxid = qczxid;
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

  public String getQyzbh() {
    return qyzbh;
  }

  public void setQyzbh(String qyzbh) {
    this.qyzbh = qyzbh;
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

  public String getZqzbh() {
    return zqzbh;
  }

  public void setZqzbh(String zqzbh) {
    this.zqzbh = zqzbh;
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

  public String getXm() {
    return xm;
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

  public String getCdsj() {
    return cdsj;
  }

  public void setCdsj(String cdsj) {
    this.cdsj = cdsj;
  }

  public Long getCjhjywid() {
    return cjhjywid;
  }

  public void setCjhjywid(Long cjhjywid) {
    this.cjhjywid = cjhjywid;
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

  public String getCxbz() {
    return cxbz;
  }

  public void setCxbz(String cxbz) {
    this.cxbz = cxbz;
  }

  public String getDhhm() {
    return dhhm;
  }

  public void setDhhm(String dhhm) {
    this.dhhm = dhhm;
  }

  public String getHgjdqlbz() {
    return hgjdqlbz;
  }

  public void setHgjdqlbz(String hgjdqlbz) {
    this.hgjdqlbz = hgjdqlbz;
  }

  public String getHgjdqql() {
    return hgjdqql;
  }

  public void setHgjdqql(String hgjdqql) {
    this.hgjdqql = hgjdqql;
  }

  public String getHslbz() {
    return hslbz;
  }

  public void setHslbz(String hslbz) {
    this.hslbz = hslbz;
  }

  public String getHsql() {
    return hsql;
  }

  public void setHsql(String hsql) {
    this.hsql = hsql;
  }

  public String getHsssqlbz() {
    return hsssqlbz;
  }

  public void setHsssqlbz(String hsssqlbz) {
    this.hsssqlbz = hsssqlbz;
  }

  public String getHssxqql() {
    return hssxqql;
  }

  public void setHssxqql(String hssxqql) {
    this.hssxqql = hssxqql;
  }

  public String getHxzlbz() {
    return hxzlbz;
  }

  public void setHxzlbz(String hxzlbz) {
    this.hxzlbz = hxzlbz;
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

  public void setHylb(String hylb) {
    this.hylb = hylb;
  }

  public String getHylbz() {
    return hylbz;
  }

  public void setHylbz(String hylbz) {
    this.hylbz = hylbz;
  }

  public String getHyql() {
    return hyql;
  }

  public void setHyql(String hyql) {
    this.hyql = hyql;
  }

  public String getJdlb() {
    return jdlb;
  }

  public void setJdsj(String jdsj) {
    this.jdsj = jdsj;
  }

  public String getJdsj() {
    return jdsj;
  }

  public void setJdlb(String jdlb) {
    this.jdlb = jdlb;
  }

  public Long getNbsfzid() {
    return nbsfzid;
  }

  public void setNbsfzid(Long nbsfzid) {
    this.nbsfzid = nbsfzid;
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

  public String getQfjg() {
    return qfjg;
  }

  public void setQfjg(String qfjg) {
    this.qfjg = qfjg;
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

  public String getRysdzt() {
    return rysdzt;
  }

  public void setRysdzt(String rysdzt) {
    this.rysdzt = rysdzt;
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

  public String getSwzxrq() {
    return swzxrq;
  }

  public void setSwzxrq(String swzxrq) {
    this.swzxrq = swzxrq;
  }

  public String getYwnr() {
    return ywnr;
  }

  public void setYxqxjzrq(String yxqxjzrq) {
    this.yxqxjzrq = yxqxjzrq;
  }

  public void setYwnr(String ywnr) {
    this.ywnr = ywnr;
  }

  public String getYxqxjzrq() {
    return yxqxjzrq;
  }

  public String getYxqxqsrq() {
    return yxqxqsrq;
  }

  public String getKdqqy_qrdqbm() {
    return kdqqy_qrdqbm;
  }

  public void setYxqxqsrq(String yxqxqsrq) {
    this.yxqxqsrq = yxqxqsrq;
  }

  public void setKdqqy_qrdqbm(String kdqqy_qrdqbm) {
    this.kdqqy_qrdqbm = kdqqy_qrdqbm;
  }

  public String getSbrjtgx() {
	return sbrjtgx;
  }
	
  public void setSbrjtgx(String sbrjtgx) {
	this.sbrjtgx = sbrjtgx;
  }

}
