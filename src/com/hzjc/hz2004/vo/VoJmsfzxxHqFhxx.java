package com.hzjc.hz2004.vo;

import com.hzjc.hz2004.po.*;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.*;

/**
 * <p>Title: Hz2004</p>
 * <p>Description: 常住人口管理系统Hz2004版</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 浙江金铖新技术有限公司</p>
 * @author kgb_hz@126.com,kgb@primetech.com.cn
 * @version 1.0
 */

public class VoJmsfzxxHqFhxx {
  /**
   *
   */
  public VoJmsfzxxHqFhxx() {
  }

  /**
   *
   * @param poZJXX_JMSFZXXB
   * @param poHJXX_MLPXXXXB
   */
  public VoJmsfzxxHqFhxx(PoZJXX_JMSFZXXB poZJXX_JMSFZXXB,
                         PoHJXX_MLPXXXXB poHJXX_MLPXXXXB, Long lZpid) {
    try {
      if(poZJXX_JMSFZXXB != null){
        BeanUtils.copyProperties(this, poZJXX_JMSFZXXB);
      }
      if(poHJXX_MLPXXXXB != null){
        BeanUtils.copyProperties(this, poHJXX_MLPXXXXB);
      }
      this.setZpid(lZpid);
    }
    catch (InvocationTargetException ex) {
    }
    catch (IllegalAccessException ex) {
    }
  }

  //居民身份证信息PO
  private Long nbsfzid; //内部身份证ID
  private Long ryid; //	人员ID
  private Long zpid; //	照片ID
  private String gmsfhm; //公民身份号码
  private String xm; //	姓名
  private String xb; //	性别
  private String mz; //	民族
  private String csrq; //出生日期
  private String qfjg; //签发机关
  private String qfrq; //签发日期
  private String yxqx; //有效期限
  private String yxqxqsrq; //有效期限起始日期
  private String yxqxjzrq; //有效期限截止日期
  private String slyy; //申领原因
  private String bzyy; //办证原因
  private String sjyy; //收交原因
  private String zz; //	住址
  private String zz1; //住址1
  private String zz2; //住址2
  private String zz3; //住址3
  private String zz4; //住址4
  private String ktglh; //卡体管理号
  private String zjlb; //证件类别
  private String zjzt; //证件状态

  //门楼牌信息PO
  private Long mlpnbid; //门（楼）牌内部ID
  private Long mlpid; //门（楼）牌ID
  private String ssxq; //省市县（区）
  private String jlx; //街路巷
  private String mlph; //门（楼）牌号
  private String mlxz; //门（楼）详址
  private String pcs; //派出所
  private String zrq; //责任区
  private String xzjd; //乡镇（街道）
  private String jcwh; //居（村）委会
  private String jlrq; //建立日期
  private String jllb; //建立类别
  private Long jlrid; //建立人ID
  private Long cjhjywid; //创建户籍业务ID
  private String ccrq; //撤除日期
  private String cxlb; //撤消类别
  private Long cdrid; //撤地人ID
  private Long cchjywid; //撤除户籍业务ID
  private String mlpzt; //门（楼）牌状态
  private Long lxdbid; //离线DBID
  private String jlbz; //记录标志
  private String qysj; //起用时间
  private String jssj; //结束时间
  private String pxh; //排序号

  public String getBzyy() {
    return bzyy;
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

  public String getCsrq() {
    return csrq;
  }

  public String getCxlb() {
    return cxlb;
  }

  public String getGmsfhm() {
    return gmsfhm;
  }

  public String getJcwh() {
    return jcwh;
  }

  public String getJlbz() {
    return jlbz;
  }

  public String getJllb() {
    return jllb;
  }

  public Long getJlrid() {
    return jlrid;
  }

  public String getJlrq() {
    return jlrq;
  }

  public String getJlx() {
    return jlx;
  }

  public String getJssj() {
    return jssj;
  }

  public String getKtglh() {
    return ktglh;
  }

  public Long getLxdbid() {
    return lxdbid;
  }

  public String getMlph() {
    return mlph;
  }

  public Long getMlpid() {
    return mlpid;
  }

  public Long getMlpnbid() {
    return mlpnbid;
  }

  public String getMlpzt() {
    return mlpzt;
  }

  public String getMlxz() {
    return mlxz;
  }

  public String getMz() {
    return mz;
  }

  public Long getNbsfzid() {
    return nbsfzid;
  }

  public String getPcs() {
    return pcs;
  }

  public String getPxh() {
    return pxh;
  }

  public String getQfjg() {
    return qfjg;
  }

  public String getQfrq() {
    return qfrq;
  }

  public String getQysj() {
    return qysj;
  }

  public Long getRyid() {
    return ryid;
  }

  public String getSjyy() {
    return sjyy;
  }

  public String getSlyy() {
    return slyy;
  }

  public String getSsxq() {
    return ssxq;
  }

  public String getXb() {
    return xb;
  }

  public String getXm() {
    return xm;
  }

  public String getXzjd() {
    return xzjd;
  }

  public String getYxqx() {
    return yxqx;
  }

  public String getYxqxjzrq() {
    return yxqxjzrq;
  }

  public String getYxqxqsrq() {
    return yxqxqsrq;
  }

  public String getZjlb() {
    return zjlb;
  }

  public String getZjzt() {
    return zjzt;
  }

  public Long getZpid() {
    return zpid;
  }

  public String getZrq() {
    return zrq;
  }

  public String getZz() {
    return zz;
  }

  public String getZz1() {
    return zz1;
  }

  public String getZz2() {
    return zz2;
  }

  public String getZz3() {
    return zz3;
  }

  public String getZz4() {
    return zz4;
  }

  public void setZz4(String zz4) {
    this.zz4 = zz4;
  }

  public void setZz3(String zz3) {
    this.zz3 = zz3;
  }

  public void setZz2(String zz2) {
    this.zz2 = zz2;
  }

  public void setZz1(String zz1) {
    this.zz1 = zz1;
  }

  public void setZz(String zz) {
    this.zz = zz;
  }

  public void setZrq(String zrq) {
    this.zrq = zrq;
  }

  public void setZpid(Long zpid) {
    this.zpid = zpid;
  }

  public void setZjzt(String zjzt) {
    this.zjzt = zjzt;
  }

  public void setZjlb(String zjlb) {
    this.zjlb = zjlb;
  }

  public void setYxqxqsrq(String yxqxqsrq) {
    this.yxqxqsrq = yxqxqsrq;
  }

  public void setYxqxjzrq(String yxqxjzrq) {
    this.yxqxjzrq = yxqxjzrq;
  }

  public void setYxqx(String yxqx) {
    this.yxqx = yxqx;
  }

  public void setXzjd(String xzjd) {
    this.xzjd = xzjd;
  }

  public void setXm(String xm) {
    this.xm = xm;
  }

  public void setXb(String xb) {
    this.xb = xb;
  }

  public void setSsxq(String ssxq) {
    this.ssxq = ssxq;
  }

  public void setSlyy(String slyy) {
    this.slyy = slyy;
  }

  public void setSjyy(String sjyy) {
    this.sjyy = sjyy;
  }

  public void setRyid(Long ryid) {
    this.ryid = ryid;
  }

  public void setQysj(String qysj) {
    this.qysj = qysj;
  }

  public void setQfrq(String qfrq) {
    this.qfrq = qfrq;
  }

  public void setQfjg(String qfjg) {
    this.qfjg = qfjg;
  }

  public void setPxh(String pxh) {
    this.pxh = pxh;
  }

  public void setPcs(String pcs) {
    this.pcs = pcs;
  }

  public void setNbsfzid(Long nbsfzid) {
    this.nbsfzid = nbsfzid;
  }

  public void setMz(String mz) {
    this.mz = mz;
  }

  public void setMlxz(String mlxz) {
    this.mlxz = mlxz;
  }

  public void setMlpzt(String mlpzt) {
    this.mlpzt = mlpzt;
  }

  public void setMlpnbid(Long mlpnbid) {
    this.mlpnbid = mlpnbid;
  }

  public void setMlpid(Long mlpid) {
    this.mlpid = mlpid;
  }

  public void setMlph(String mlph) {
    this.mlph = mlph;
  }

  public void setLxdbid(Long lxdbid) {
    this.lxdbid = lxdbid;
  }

  public void setKtglh(String ktglh) {
    this.ktglh = ktglh;
  }

  public void setJssj(String jssj) {
    this.jssj = jssj;
  }

  public void setJlx(String jlx) {
    this.jlx = jlx;
  }

  public void setJlrq(String jlrq) {
    this.jlrq = jlrq;
  }

  public void setJlrid(Long jlrid) {
    this.jlrid = jlrid;
  }

  public void setJllb(String jllb) {
    this.jllb = jllb;
  }

  public void setJlbz(String jlbz) {
    this.jlbz = jlbz;
  }

  public void setJcwh(String jcwh) {
    this.jcwh = jcwh;
  }

  public void setGmsfhm(String gmsfhm) {
    this.gmsfhm = gmsfhm;
  }

  public void setCxlb(String cxlb) {
    this.cxlb = cxlb;
  }

  public void setCsrq(String csrq) {
    this.csrq = csrq;
  }

  public void setCjhjywid(Long cjhjywid) {
    this.cjhjywid = cjhjywid;
  }

  public void setCdrid(Long cdrid) {
    this.cdrid = cdrid;
  }

  public void setCcrq(String ccrq) {
    this.ccrq = ccrq;
  }

  public void setCchjywid(Long cchjywid) {
    this.cchjywid = cchjywid;
  }

  public void setBzyy(String bzyy) {
    this.bzyy = bzyy;
  }

}