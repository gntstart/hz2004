package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 准迁证编号回填信息VO
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: GNT Corp.</p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoZqzbhhtxx
    extends DefaultVO {

  private Long zqid; //准迁ID
  private String zqzjlx; //准迁证件类型
  private String zjbh; //证件编号

  public Long getZqid() {
    return zqid;
  }

  public void setZqid(Long zqid) {
    this.zqid = zqid;
  }

  public String getZjbh() {
    return zjbh;
  }

  public void setZjbh(String zjbh) {
    this.zjbh = zjbh;
  }

  public String getZqzjlx() {
    return zqzjlx;
  }

  public void setZqzjlx(String zqzjlx) {
    this.zqzjlx = zqzjlx;
  }

}