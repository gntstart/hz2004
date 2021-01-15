package  com.hzjc.hz2004.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="V_ZJ_SJXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoV_ZJ_SJXXB implements com.hzjc.wsstruts.po.PO
{
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
  private Long sjslid;
  private Long nbsfzid;
  private String sjyy;
  private Long zjywid;
  private String cxbz;
  private String cxsj;
  private Long cxrid;
  private Long cxzjywid;
  private String gmsfhm;
  private String xm;
  private String xb;
  private String mz;
  private String csrq;
  private String csdssxq;
  private Long zpid;
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
  private Long ryid;
  private String qfjg;
  private String yxqxqsrq;
  private String yxqxjzrq;
  private String slyy;
  private String bzyy;
  private String zz;
  private String zz1;
  private String zz2;
  private String zz3;
  private String zz4;
  private String ktglh;
  private String zjlb;
  private String zjzt;

  public void setSjslid(Long sjslid) {
    this.sjslid = sjslid;
  }

  public Long getSjslid() {
    return sjslid;
  }

  public void setNbsfzid(Long nbsfzid) {
    this.nbsfzid = nbsfzid;
  }

  public Long getNbsfzid() {
    return nbsfzid;
  }

  public void setSjyy(String sjyy) {
    this.sjyy = sjyy;
  }

  public String getSjyy() {
    return sjyy;
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

  public void setZpid(Long zpid) {
    this.zpid = zpid;
  }

  public Long getZpid() {
    return zpid;
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

  public void setRyid(Long ryid) {
    this.ryid = ryid;
  }

  public Long getRyid() {
    return ryid;
  }

  public void setQfjg(String qfjg) {
    this.qfjg = qfjg;
  }

  public String getQfjg() {
    return qfjg;
  }

  public void setYxqxqsrq(String yxqxqsrq) {
    this.yxqxqsrq = yxqxqsrq;
  }

  public String getYxqxqsrq() {
    return yxqxqsrq;
  }

  public void setYxqxjzrq(String yxqxjzrq) {
    this.yxqxjzrq = yxqxjzrq;
  }

  public String getYxqxjzrq() {
    return yxqxjzrq;
  }

  public void setSlyy(String slyy) {
    this.slyy = slyy;
  }

  public String getSlyy() {
    return slyy;
  }

  public void setBzyy(String bzyy) {
    this.bzyy = bzyy;
  }

  public String getBzyy() {
    return bzyy;
  }

  public void setZz(String zz) {
    this.zz = zz;
  }

  public String getZz() {
    return zz;
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

  public void setZz4(String zz4) {
    this.zz4 = zz4;
  }

  public String getZz4() {
    return zz4;
  }

  public void setKtglh(String ktglh) {
    this.ktglh = ktglh;
  }

  public String getKtglh() {
    return ktglh;
  }

  public void setZjlb(String zjlb) {
    this.zjlb = zjlb;
  }

  public String getZjlb() {
    return zjlb;
  }

  public void setZjzt(String zjzt) {
    this.zjzt = zjzt;
  }

  public String getZjzt() {
    return zjzt;
  }

}
