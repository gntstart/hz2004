package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 拍照信息Vo
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: GNT Corp.</p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoPzxx
    extends DefaultVO {

  private String gmsfhm; //公民身份号码
  private String slh; //受理号
  private String zp; //照片数据
  private String pzxlh; //拍照序列号

  public String getGmsfhm() {
    return gmsfhm;
  }

  public void setGmsfhm(String gmsfhm) {
    this.gmsfhm = gmsfhm;
  }

  public void setSlh(String slh) {
    this.slh = slh;
  }

  public String getSlh() {
    return slh;
  }

  public String getZp() {
    return zp;
  }

  public void setZp(String zp) {
    this.zp = zp;
  }

  public String getPzxlh() {
    return pzxlh;
  }

  public void setPzxlh(String pzxlh) {
    this.pzxlh = pzxlh;
  }

}