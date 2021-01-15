package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 户成员关系调整信息_增强
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoHcygxtzxxEx
    extends DefaultVO {

  private int flag; //是否需要修改常住人口信息表中的对应记录(0-不修改/1-修改)
  private Long hhnbid; //户号内部ID
  private Long ryid; //人员ID
  private String old_yhzgx; //原与户主关系
  private String hcybdlx; //户成员变动类型
  private String hcybdlb; //户成员变动类别
  private Long rynbid; //人员内部ID
  private String yhzgx; //与户主关系
  private Long sbhjywid; //上笔户籍业务ID
  private String xm; //姓名
  private String gmsfhm; //公民身份号码
  private Long new_rynbid; //新的人员内部ID

  public String getHcybdlx() {
    return hcybdlx;
  }

  public Long getRynbid() {
    return rynbid;
  }

  public String getYhzgx() {
    return yhzgx;
  }

  public void setYhzgx(String yhzgx) {
    this.yhzgx = yhzgx;
  }

  public void setHcybdlx(String hcybdlx) {
    this.hcybdlx = hcybdlx;
  }

  public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }

  public int getFlag() {
    return flag;
  }

  public void setFlag(int flag) {
    this.flag = flag;
  }

  public String getOld_yhzgx() {
    return old_yhzgx;
  }

  public void setOld_yhzgx(String old_yhzgx) {
    this.old_yhzgx = old_yhzgx;
  }

  public Long getSbhjywid() {
    return sbhjywid;
  }

  public void setSbhjywid(Long sbhjywid) {
    this.sbhjywid = sbhjywid;
  }

  public String getGmsfhm() {
    return gmsfhm;
  }

  public void setGmsfhm(String gmsfhm) {
    this.gmsfhm = gmsfhm;
  }

  public String getXm() {
    return xm;
  }

  public void setXm(String xm) {
    this.xm = xm;
  }

  public Long getNew_rynbid() {
    return new_rynbid;
  }

  public void setNew_rynbid(Long new_rynbid) {
    this.new_rynbid = new_rynbid;
  }

  public Long getHhnbid() {
    return hhnbid;
  }

  public void setHhnbid(Long hhnbid) {
    this.hhnbid = hhnbid;
  }

  public String getHcybdlb() {
    return hcybdlb;
  }

  public void setHcybdlb(String hcybdlb) {
    this.hcybdlb = hcybdlb;
  }

  public Long getRyid() {
    return ryid;
  }

  public void setRyid(Long ryid) {
    this.ryid = ryid;
  }

}
