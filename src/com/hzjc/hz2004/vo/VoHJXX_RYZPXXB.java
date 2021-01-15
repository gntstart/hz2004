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

public class VoHJXX_RYZPXXB
    extends DefaultVO {

  public VoHJXX_RYZPXXB() {
  }

  /**
   * PO-->VO(将PO的值转换装载到VO中)
   * @param po
   */
  public VoHJXX_RYZPXXB(PoHJXX_RYZPXXB po) {
    //继承基类处理
    super(po);
    //////////////////////////////////////////////////////////////////
    //byte[]-->String(对于byte[]字节流的字段要特殊处理:Base64编码后赋给VO)
    ///////////////////////////////////////////////////////////////////
    if (po.getZp() != null) {
      this.setZp(new String(encodeBytes(po.getZp())));
    }
  }

  /**
   *
   * @return
   */
  public PoHJXX_RYZPXXB toPoHJXX_RYZPXXB() {
    return toPoHJXX_RYZPXXB(null);
  }

  /**
   *
   * @param poRyzp
   * @return
   */
  public PoHJXX_RYZPXXB toPoHJXX_RYZPXXB(PoHJXX_RYZPXXB poRyzp) {
    if (poRyzp == null) {
      poRyzp = new PoHJXX_RYZPXXB();
    }
    poRyzp.setGmsfhm(this.getGmsfhm());
    poRyzp.setLrrq(this.getLrrq());
    poRyzp.setXm(this.getXm());
    poRyzp.setZpid(this.getZpid());
    poRyzp.setRyid(this.getRyid());
    poRyzp.setZp(decodeBytes(this.getZp() == null ? null :
                             this.getZp().getBytes()));
    ////////////////////////////////////////////////////////////////////
    return poRyzp;
  }

  private Long zpid;
  private String gmsfhm;
  private String xm;
  private String lrrq;
  private String zp; //经过BASE64进行编码的字符串数据
  private Long ryid;

  public String getGmsfhm() {
    return gmsfhm;
  }

  public String getLrrq() {
    return lrrq;
  }

  public String getXm() {
    return xm;
  }

  public String getZp() {
    return zp;
  }

  public Long getZpid() {
    return zpid;
  }

  public void setZpid(Long zpid) {
    this.zpid = zpid;
  }

  public void setZp(String zp) {
    this.zp = zp;
  }

  public void setXm(String xm) {
    this.xm = xm;
  }

  public void setLrrq(String lrrq) {
    this.lrrq = lrrq;
  }

  public void setGmsfhm(String gmsfhm) {
    this.gmsfhm = gmsfhm;
  }

  public Long getRyid() {
    return ryid;
  }

  public void setRyid(Long ryid) {
    this.ryid = ryid;
  }

}