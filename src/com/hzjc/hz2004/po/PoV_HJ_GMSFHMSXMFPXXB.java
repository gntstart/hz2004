package  com.hzjc.hz2004.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
*业务接口对照表
*/
@Entity
@Table(name="V_HJ_GMSFHMSXMFPXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoV_HJ_GMSFHMSXMFPXXB implements com.hzjc.wsstruts.po.PO
{
	/*
	*对照ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id  
  private Long fpid;
  private Long ryid;
  private String xm;
  private String xb;
  private String csrq;
  private String gmsfhm;
  private String xzqh;
  private String dwdm;
  private String sxh;
  private String czlb;
  private Long hjywid;
  //private String ywbz;
  //private String ywlx;
  //private Long czsm;
  private String sbsj;
  private String sbryxm;
  private String sbrgmsfhm;
  private String slsj;
  private String sldw;
  private Long slrid;

  public void setFpid(Long fpid) {
    this.fpid = fpid;
  }

  public Long getFpid() {
    return fpid;
  }

  public void setRyid(Long ryid) {
    this.ryid = ryid;
  }

  public Long getRyid() {
    return ryid;
  }

  public void setXm(String xm) {
    this.xm = xm;
  }

  public String getXm() {
    return xm;
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

  public void setGmsfhm(String gmsfhm) {
    this.gmsfhm = gmsfhm;
  }

  public String getGmsfhm() {
    return gmsfhm;
  }

  public void setXzqh(String xzqh) {
    this.xzqh = xzqh;
  }

  public String getXzqh() {
    return xzqh;
  }

  public void setDwdm(String dwdm) {
    this.dwdm = dwdm;
  }

  public String getDwdm() {
    return dwdm;
  }

  public void setSxh(String sxh) {
    this.sxh = sxh;
  }

  public String getSxh() {
    return sxh;
  }

  public void setCzlb(String czlb) {
    this.czlb = czlb;
  }

  public String getCzlb() {
    return czlb;
  }

  public void setHjywid(Long hjywid) {
    this.hjywid = hjywid;
  }

  public Long getHjywid() {
    return hjywid;
  }
  /*
  public void setYwbz(String ywbz) {
    this.ywbz = ywbz;
  }

  public String getYwbz() {
    return ywbz;
  }

  public void setYwlx(String ywlx) {
    this.ywlx = ywlx;
  }

  public String getYwlx() {
    return ywlx;
  }

  public void setCzsm(Long czsm) {
    this.czsm = czsm;
  }

  public Long getCzsm() {
    return czsm;
  }
  */
  public void setSbsj(String sbsj) {
    this.sbsj = sbsj;
  }

  public String getSbsj() {
    return sbsj;
  }

  public void setSbryxm(String sbryxm) {
    this.sbryxm = sbryxm;
  }

  public String getSbryxm() {
    return sbryxm;
  }

  public void setSbrgmsfhm(String sbrgmsfhm) {
    this.sbrgmsfhm = sbrgmsfhm;
  }

  public String getSbrgmsfhm() {
    return sbrgmsfhm;
  }

  public void setSlsj(String slsj) {
    this.slsj = slsj;
  }

  public String getSlsj() {
    return slsj;
  }

  public void setSldw(String sldw) {
    this.sldw = sldw;
  }

  public String getSldw() {
    return sldw;
  }

  public void setSlrid(Long slrid) {
    this.slrid = slrid;
  }

  public Long getSlrid() {
    return slrid;
  }

}
