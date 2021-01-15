package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 全户变更信息VO
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoQhbgxx
    extends DefaultVO {

  private Long hhnbid; //户号内部ID
  private String jlx; //街路巷
  private String mlph; //门楼牌号
  private String mlxz; //门楼详址
  private String hlx; //户类型
  private String jcwh;//居委会  add by zjm 20200824 户政跳转过来有居委会，需要增加属性接收

  public VoQhbgxx() {
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
    this.hlx = hlx != null ? hlx.trim() : hlx;
  }

  public String getMlph() {
    return mlph;
  }

  public void setMlph(String mlph) {
    this.mlph = mlph != null ? mlph.trim() : mlph;
  }

  public String getMlxz() {
    return mlxz;
  }

  public void setMlxz(String mlxz) {
    this.mlxz = mlxz != null ? mlxz.trim() : mlxz;
  }

  public String getJlx() {
    return jlx;
  }

  public void setJlx(String jlx) {
    this.jlx = jlx != null ? jlx.trim() : jlx;
  }

	public String getJcwh() {
		return jcwh;
	}
	
	public void setJcwh(String jcwh) {
		this.jcwh = jcwh;
	}

}