package  com.hzjc.hz2004.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
*分检结果日志表
*/
@Entity
@Table(name="V_ZJ_YDZSLXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoV_ZJ_YDZSLXXB implements com.hzjc.wsstruts.po.PO
{
	/*
	*分检结果ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id	
  private Long bzslid;
  private Long rynbid;
  private Long zpid;
  private String zz1;
  private String zz2;
  private String zz3;
  private String qfrq;
  private String yxqx;
  private String bzlb;
  private String bzyy;
  private String blnf;
  private String blbz;
  private String dkdy;
  private String tkzd;
  private Long zjywid;
  private String cxbz;
  private String cxsj;
  private Long cxrid;
  private Long cxzjywid;
  private Long ryid;
  private String gmsfhm;
  private String xm;
  private String xb;
  private String mz;
  private String csrq;
  private String csdssxq;
  private Long mlpnbid;
  private String ssxq;
  private String jlx;
  private String mlph;
  private String mlxz;
  private String pcs;
  private String zrq;
  private String xzjd;
  private String jcwh;
  private String pxh;
  private String ywbz;
  private Long czyid;
  private String czsj;

  public void setBzslid(Long bzslid) {
    this.bzslid = bzslid;
  }

  public Long getBzslid() {
    return bzslid;
  }

  public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }

  public Long getRynbid() {
    return rynbid;
  }

  public void setZpid(Long zpid) {
    this.zpid = zpid;
  }

  public Long getZpid() {
    return zpid;
  }

  public void setZz1(String zz1) {
    this.zz1 = zz1;
  }

  public String getZz1() {
    return zz1;
  }

  public void setZz2(String zz2) {
    this.zz2 = zz2;
  }

  public String getZz2() {
    return zz2;
  }

  public void setZz3(String zz3) {
    this.zz3 = zz3;
  }

  public String getZz3() {
    return zz3;
  }

  public void setQfrq(String qfrq) {
    this.qfrq = qfrq;
  }

  public String getQfrq() {
    return qfrq;
  }

  public void setYxqx(String yxqx) {
    this.yxqx = yxqx;
  }

  public String getYxqx() {
    return yxqx;
  }

  public void setBzlb(String bzlb) {
    this.bzlb = bzlb;
  }

  public String getBzlb() {
    return bzlb;
  }

  public void setBzyy(String bzyy) {
    this.bzyy = bzyy;
  }

  public String getBzyy() {
    return bzyy;
  }

  public void setBlnf(String blnf) {
    this.blnf = blnf;
  }

  public String getBlnf() {
    return blnf;
  }

  public void setBlbz(String blbz) {
    this.blbz = blbz;
  }

  public String getBlbz() {
    return blbz;
  }

  public void setDkdy(String dkdy) {
    this.dkdy = dkdy;
  }

  public String getDkdy() {
    return dkdy;
  }

  public void setTkzd(String tkzd) {
    this.tkzd = tkzd;
  }

  public String getTkzd() {
    return tkzd;
  }

  public void setZjywid(Long zjywid) {
    this.zjywid = zjywid;
  }

  public Long getZjywid() {
    return zjywid;
  }

  public void setCxbz(String cxbz) {
    this.cxbz = cxbz;
  }

  public String getCxbz() {
    return cxbz;
  }

  public void setCxsj(String cxsj) {
    this.cxsj = cxsj;
  }

  public String getCxsj() {
    return cxsj;
  }

  public void setCxrid(Long cxrid) {
    this.cxrid = cxrid;
  }

  public Long getCxrid() {
    return cxrid;
  }

  public void setCxzjywid(Long cxzjywid) {
    this.cxzjywid = cxzjywid;
  }

  public Long getCxzjywid() {
    return cxzjywid;
  }

  public void setRyid(Long ryid) {
    this.ryid = ryid;
  }

  public Long getRyid() {
    return ryid;
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

  public void setXb(String xb) {
    this.xb = xb;
  }

  public String getXb() {
    return xb;
  }

  public void setMz(String mz) {
    this.mz = mz;
  }

  public String getMz() {
    return mz;
  }

  public void setCsrq(String csrq) {
    this.csrq = csrq;
  }

  public String getCsrq() {
    return csrq;
  }

  public void setCsdssxq(String csdssxq) {
    this.csdssxq = csdssxq;
  }

  public String getCsdssxq() {
    return csdssxq;
  }

  public void setMlpnbid(Long mlpnbid) {
    this.mlpnbid = mlpnbid;
  }

  public Long getMlpnbid() {
    return mlpnbid;
  }

  public void setSsxq(String ssxq) {
    this.ssxq = ssxq;
  }

  public String getSsxq() {
    return ssxq;
  }

  public void setJlx(String jlx) {
    this.jlx = jlx;
  }

  public String getJlx() {
    return jlx;
  }

  public void setMlph(String mlph) {
    this.mlph = mlph;
  }

  public String getMlph() {
    return mlph;
  }

  public void setMlxz(String mlxz) {
    this.mlxz = mlxz;
  }

  public String getMlxz() {
    return mlxz;
  }

  public void setPcs(String pcs) {
    this.pcs = pcs;
  }

  public String getPcs() {
    return pcs;
  }

  public void setZrq(String zrq) {
    this.zrq = zrq;
  }

  public String getZrq() {
    return zrq;
  }

  public void setXzjd(String xzjd) {
    this.xzjd = xzjd;
  }

  public String getXzjd() {
    return xzjd;
  }

  public void setJcwh(String jcwh) {
    this.jcwh = jcwh;
  }

  public String getJcwh() {
    return jcwh;
  }

  public void setPxh(String pxh) {
    this.pxh = pxh;
  }

  public String getPxh() {
    return pxh;
  }

  public void setYwbz(String ywbz) {
    this.ywbz = ywbz;
  }

  public String getYwbz() {
    return ywbz;
  }

  public void setCzyid(Long czyid) {
    this.czyid = czyid;
  }

  public Long getCzyid() {
    return czyid;
  }

  public void setCzsj(String czsj) {
    this.czsj = czsj;
  }

  public String getCzsj() {
    return czsj;
  }

}
