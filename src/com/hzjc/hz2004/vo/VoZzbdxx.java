package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.*;

/**
 * 住址变动信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoZzbdxx
    extends DefaultVO {

  //迁入审批信息
  private Long spywid; //审批业务ID
  private Long spsqzid; //审批申请子ID

  private Long rynbid; //人员内部ID
  private String zzbdhhb; //住址变动后户别
  private String zzbdlb; //住址变动类别
  private String qyzbh; //迁移证编号
  private String bdfw; //变动范围
  private String yhzgx; //与户主关系
  private String zzbdrq; //住址变动日期
  private Long sbhjywid; //上笔户籍业务ID
  private String nyzyrklhczyydm;
  private String cxsxtz_pdbz;
  private String jjqx_pdbz;
  private String zczjyhjzwnys_pdbz;
  private String jthzl;
  private String  sbrjtgx;

  public String getBdfw() {
    return bdfw;
  }

  public void setBdfw(String bdfw) {
    this.bdfw = bdfw;
  }

  public void setQyzbh(String qyzbh) {
    this.qyzbh = qyzbh;
  }

  public String getQyzbh() {
    return qyzbh;
  }

  public Long getRynbid() {
    return rynbid;
  }

  public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }

  public void setSbhjywid(Long sbhjywid) {
    this.sbhjywid = sbhjywid;
  }

  public Long getSbhjywid() {
    return sbhjywid;
  }

  public String getZzbdlb() {
    return zzbdlb;
  }

  public void setZzbdlb(String zzbdlb) {
    this.zzbdlb = zzbdlb;
  }

  public String getYhzgx() {
    return yhzgx;
  }

  public void setYhzgx(String yhzgx) {
    this.yhzgx = yhzgx;
  }

  public String getZzbdrq() {
    return zzbdrq;
  }

  public void setZzbdrq(String zzbdrq) {
    this.zzbdrq = zzbdrq;
  }

  public String getZzbdhhb() {
    return zzbdhhb;
  }

  public void setZzbdhhb(String zzbdhhb) {
    this.zzbdhhb = zzbdhhb;
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
  public String getSbrjtgx() {
    return sbrjtgx;
  }

  public String getCxsxtz_pdbz() {
    return cxsxtz_pdbz;
  }

  public String getJjqx_pdbz() {
    return jjqx_pdbz;
  }

  public String getNyzyrklhczyydm() {
    return nyzyrklhczyydm;
  }

  public String getZczjyhjzwnys_pdbz() {
    return zczjyhjzwnys_pdbz;
  }

  public String getJthzl() {
    return jthzl;
  }

  public void setSbrjtgx(String sbrjtgx) {
    this.sbrjtgx = sbrjtgx;
   }

  public void setCxsxtz_pdbz(String cxsxtz_pdbz) {
    this.cxsxtz_pdbz = cxsxtz_pdbz;
  }

  public void setJjqx_pdbz(String jjqx_pdbz) {
    this.jjqx_pdbz = jjqx_pdbz;
  }

  public void setNyzyrklhczyydm(String nyzyrklhczyydm) {
    this.nyzyrklhczyydm = nyzyrklhczyydm;
  }

  public void setZczjyhjzwnys_pdbz(String zczjyhjzwnys_pdbz) {
    this.zczjyhjzwnys_pdbz = zczjyhjzwnys_pdbz;
  }

  public void setJthzl(String jthzl) {
    this.jthzl = jthzl;
  }

}
