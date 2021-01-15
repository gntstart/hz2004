package com.hzjc.hz2004.vo;

/**
 * 迁移证编号回填返回信息Vo
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoQyzbhhtfhxx {
  public VoQyzbhhtfhxx() {
  }

  private Long rynbid; //人员内部ID
  private Long qczxid; //迁出注销ID
  private Long zzbdid; //住址变动ID
  public Long getQczxid() {
    return qczxid;
  }

  public void setQczxid(Long qczxid) {
    this.qczxid = qczxid;
  }

  public Long getRynbid() {
    return rynbid;
  }

  public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }

  public Long getZzbdid() {
    return zzbdid;
  }

  public void setZzbdid(Long zzbdid) {
    this.zzbdid = zzbdid;
  }

}