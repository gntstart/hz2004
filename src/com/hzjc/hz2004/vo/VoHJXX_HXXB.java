package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;
import com.hzjc.hz2004.po.PoHJXX_RYZPXXB;

/**
 * <p>Title: Hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author Kansan Ku(kgb_hz@126.com)
 * @version 1.0
 */

public class VoHJXX_HXXB
    extends DefaultVO {

  public VoHJXX_HXXB() {
  }

  /**
   * PO-->VO(将PO的值转换装载到VO中)
   * @param po
   */
  public VoHJXX_HXXB(PoHJXX_RYZPXXB po) {
    //继承基类处理
    super(po);
  }

  private Long hh;
  private Long hhid;
  private Long hhnbid;
  private String hlx;
  private String pcs;
  private String xzjd;
  private String jlx;
  private String jcwh; 
  private String jttdbz;
  private String mlph;
  private String mlxz;
  private Long slrid;
  private String slsj;
  private String sldw;
public Long getHh() {
	return hh;
}

public void setHh(Long hh) {
	this.hh = hh;
}

public Long getHhid() {
	return hhid;
}

public void setHhid(Long hhid) {
	this.hhid = hhid;
}

public Long getHhnbid() {
	return hhnbid;
}

public void setHhnbid(Long hhnbid) {
	this.hhnbid = hhnbid;
}

public String getHlx() {
	return hlx;
}

public void setHlx(String hlx) {
	this.hlx = hlx;
}

public String getPcs() {
	return pcs;
}

public void setPcs(String pcs) {
	this.pcs = pcs;
}

public String getXzjd() {
	return xzjd;
}

public void setXzjd(String xzjd) {
	this.xzjd = xzjd;
}

public String getJlx() {
	return jlx;
}

public void setJlx(String jlx) {
	this.jlx = jlx;
}

public String getJcwh() {
	return jcwh;
}

public void setJcwh(String jcwh) {
	this.jcwh = jcwh;
}

public String getJttdbz() {
	return jttdbz;
}

public void setJttdbz(String jttdbz) {
	this.jttdbz = jttdbz;
}

public String getMlph() {
	return mlph;
}

public void setMlph(String mlph) {
	this.mlph = mlph;
}

public String getMlxz() {
	return mlxz;
}

public void setMlxz(String mlxz) {
	this.mlxz = mlxz;
}

public Long getSlrid() {
	return slrid;
}

public void setSlrid(Long slrid) {
	this.slrid = slrid;
}

public String getSlsj() {
	return slsj;
}

public void setSlsj(String slsj) {
	this.slsj = slsj;
}

public String getSldw() {
	return sldw;
}

public void setSldw(String sldw) {
	this.sldw = sldw;
}

}