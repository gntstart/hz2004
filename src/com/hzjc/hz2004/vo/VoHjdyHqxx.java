package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 户籍打印返回信息Vo
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT Corp. 2004</p>
 * <p>Company: </p>
 * @author hb
 * @version 1.0
 */

public class VoHjdyHqxx
    extends DefaultVO {
  private Long rynbid; //人员内部ID
  private String dylb; //打印类别
  private String zjbh; //证件编号
  private String yznf; //印制年份

  public Long getRynbid() {
    return rynbid;
  }

  public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }

  public String getDylb() {
    return dylb;
  }

  public void setDylb(String dylb) {
    this.dylb = dylb;
  }

  public String getZjbh() {
    return zjbh;
  }

  public void setZjbh(String zjbh) {
    this.zjbh = zjbh;
  }

  public String getYznf() {
    return yznf;
  }

  public void setYznf(String yznf) {
    this.yznf = yznf;
  }

}
