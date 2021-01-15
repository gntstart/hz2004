package com.hzjc.hz2004.vo;

import com.hzjc.hz2004.po.PoYDZJ_SDZP;
import com.hzjc.wsstruts.vo.DefaultVO;

public class VoYdzjsbxx
    extends DefaultVO {

  public VoYdzjsbxx() {

  }

  public VoYdzjsbxx(PoYDZJ_SDZP po) {
    //继承基类处理
    super(po);
    //////////////////////////////////////////////////////////////////
    //byte[]-->String(对于byte[]字节流的字段要特殊处理:Base64编码后赋给VO)
    ///////////////////////////////////////////////////////////////////
    if (po.getSdzp() != null) {
      this.setZp(new String(encodeBytes(po.getSdzp())));
    }
  }

  public byte[] toBytezp(String zp) {
    byte[] bzp = decodeBytes(zp == null ? null : zp.getBytes());
    return bzp;
  }

  public PoYDZJ_SDZP toPoYDZJ_SDZP() {
    return toPoYDZJ_SDZP(null);
  }

  public PoYDZJ_SDZP toPoYDZJ_SDZP(PoYDZJ_SDZP poRyzp) {
    if (poRyzp == null) {
      poRyzp = new PoYDZJ_SDZP();
    }
    poRyzp.setGmsfhm(this.getGmsfhm());
    poRyzp.setXm(this.getXm());
    poRyzp.setZpid(this.getZpid());
    poRyzp.setLrsj(this.getLrsj());
    poRyzp.setSdzp(decodeBytes(this.getZp() == null ? null :
                               this.getZp().getBytes()));
    return poRyzp;
  }

  private String ssxq;
  private String gmsfhm;
  private String xm;
  private String cym;
  private String xb;
  private String csrq;
  private String mz;
  private String zz;
  private String slyy;
  private String zzlx;
  private String lzfs;
  private String sjrxm;
  private String sjrlxdh;
  private String sjryb;
  private String sjrtxdz;
  private Long zzfy;
  private String zp;
  private String bz;

  private Long zpid;
  private String lrsj;

  public void setSsxq(String ssxq) {
    this.ssxq = ssxq;
  }

  public String getSsxq() {
    return ssxq;
  }

  public void setGmsfhm(String gmsfhm) {
    this.gmsfhm = gmsfhm;
  }

  public String getGmsfhm() {
    return gmsfhm;
  }

  public void setXm(String xm) {
    this.xm = xm;
  }

  public String getXm() {
    return xm;
  }

  public void setCym(String cym) {
    this.cym = cym;
  }

  public String getCym() {
    return cym;
  }

  public void setXb(String xb) {
    this.xb = xb;
  }

  public String getXb() {
    return xb;
  }

  public void setCsrq(String csrq) {
    this.csrq = csrq;
  }

  public String getCsrq() {
    return csrq;
  }

  public void setMz(String mz) {
    this.mz = mz;
  }

  public String getMz() {
    return mz;
  }

  public void setZz(String zz) {
    this.zz = zz;
  }

  public String getZz() {
    return zz;
  }

  public void setSlyy(String slyy) {
    this.slyy = slyy;
  }

  public String getSlyy() {
    return slyy;
  }

  public void setZzlx(String zzlx) {
    this.zzlx = zzlx;
  }

  public String getZzlx() {
    return zzlx;
  }

  public void setLzfs(String lzfs) {
    this.lzfs = lzfs;
  }

  public String getLzfs() {
    return lzfs;
  }

  public void setSjrxm(String sjrxm) {
    this.sjrxm = sjrxm;
  }

  public String getSjrxm() {
    return sjrxm;
  }

  public void setSjrlxdh(String sjrlxdh) {
    this.sjrlxdh = sjrlxdh;
  }

  public String getSjrlxdh() {
    return sjrlxdh;
  }

  public void setSjryb(String sjryb) {
    this.sjryb = sjryb;
  }

  public String getSjryb() {
    return sjryb;
  }

  public void setSjrtxdz(String sjrtxdz) {
    this.sjrtxdz = sjrtxdz;
  }

  public String getSjrtxdz() {
    return sjrtxdz;
  }

  public void setZzfy(Long zzfy) {
    this.zzfy = zzfy;
  }

  public Long getZzfy() {
    return zzfy;
  }

  public void setZp(String zp) {
    this.zp = zp;
  }

  public String getZp() {
    return zp;
  }

  public void setBz(String bz) {
    this.bz = bz;
  }

  public String getBz() {
    return bz;
  }

  public void setZpid(Long zpid) {
    this.zpid = zpid;
  }

  public Long getZpid() {
    return zpid;
  }

  public void setLrsj(String lrsj) {
    this.lrsj = lrsj;
  }

  public String getLrsj() {
    return lrsj;
  }

}
