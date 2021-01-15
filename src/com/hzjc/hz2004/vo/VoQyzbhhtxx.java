package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 迁移证编号回填信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoQyzbhhtxx
    extends DefaultVO {
  public VoQyzbhhtxx() {
  }

  private Long dysx; //打印顺序(1是持证人)
  private Long hjywid; //户籍业务ID
  private Long rynbid; //人员内部ID
  private String qyzbh; //迁移证编号
  private String yczrgx; //与持证人关系
  private String qfrq; //签发日期(迁移证的签发日期)(持证人填)
  private String yxqxjzrq; //有效期限截止日期(持证人填)
  //add hb 20060828
  private String yznf;//印制年份

  public Long getHjywid() {
    return hjywid;
  }

  public void setHjywid(Long hjywid) {
    this.hjywid = hjywid;
  }

  public String getQyzbh() {
    return qyzbh;
  }

  public void setQyzbh(String qyzbh) {
    this.qyzbh = qyzbh;
  }

  public Long getRynbid() {
    return rynbid;
  }

  public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }

  public Long getDysx() {
    return dysx;
  }

  public void setDysx(Long dysx) {
    this.dysx = dysx;
  }

  public String getYczrgx() {
    return yczrgx;
  }

  public void setYczrgx(String yczrgx) {
    this.yczrgx = yczrgx;
  }

  public String getQfrq() {
    return qfrq;
  }

  public void setQfrq(String qfrq) {
    this.qfrq = qfrq;
  }

  public String getYxqxjzrq() {
    return yxqxjzrq;
  }

  public void setYxqxjzrq(String yxqxjzrq) {
    this.yxqxjzrq = yxqxjzrq;
  }

  public String getYznf() {
    return yznf;
  }

  public void setYznf(String yznf) {
    this.yznf = yznf;
  }

}
