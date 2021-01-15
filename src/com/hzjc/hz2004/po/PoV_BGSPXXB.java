package  com.hzjc.hz2004.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
*办证绿色通道人员信息
*/
@Entity
@Table(name="V_BGSPXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoV_BGSPXXB implements com.hzjc.wsstruts.po.PO
{

	/*
	*接口ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
  private Long	spywid;
  private Long zpid;
  private String xm;
  private String xb;
  private String mz;
  private String csrq;
  private String gmsfhm;
  private String spjg;
  private String lsbz;
  private String ssxq;
  private String jlx;
  private String mlph;
  private String mlxz;
  private String pcs;
  private String zrq;
  private String xzjd;
  private String jcwh;
  private Long	bgryid;
  private Long	spmbid;
  private Long	xydzid;
  private String	dzmc;
  private Long	bghrynbid;
  private Long	hjywid;
  private String	pxh;
  private Long	splsid;
  private String	splx;
  private String	czjg;
  private String	czyj;
  private Long	czrid;
  private String	czsj;
  private String	xydz_dzmc;
  private String	spsm;
  private Long	dzid;
  public Long getZpid() {
	return zpid;
}
public void setZpid(Long zpid) {
	this.zpid = zpid;
}
public String getXm() {
	return xm;
}
public void setXm(String xm) {
	this.xm = xm;
}
public String getXb() {
	return xb;
}
public void setXb(String xb) {
	this.xb = xb;
}
public String getMz() {
	return mz;
}
public void setMz(String mz) {
	this.mz = mz;
}
public String getCsrq() {
	return csrq;
}
public void setCsrq(String csrq) {
	this.csrq = csrq;
}
public String getGmsfhm() {
	return gmsfhm;
}
public void setGmsfhm(String gmsfhm) {
	this.gmsfhm = gmsfhm;
}
public String getSpjg() {
	return spjg;
}
public void setSpjg(String spjg) {
	this.spjg = spjg;
}
public String getLsbz() {
	return lsbz;
}
public void setLsbz(String lsbz) {
	this.lsbz = lsbz;
}
public String getSsxq() {
	return ssxq;
}
public void setSsxq(String ssxq) {
	this.ssxq = ssxq;
}
public String getJlx() {
	return jlx;
}
public void setJlx(String jlx) {
	this.jlx = jlx;
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
public String getPcs() {
	return pcs;
}
public void setPcs(String pcs) {
	this.pcs = pcs;
}
public String getZrq() {
	return zrq;
}
public void setZrq(String zrq) {
	this.zrq = zrq;
}
public String getXzjd() {
	return xzjd;
}
public void setXzjd(String xzjd) {
	this.xzjd = xzjd;
}
public String getJcwh() {
	return jcwh;
}
public void setJcwh(String jcwh) {
	this.jcwh = jcwh;
}
public Long getBgryid() {
	return bgryid;
}
public void setBgryid(Long bgryid) {
	this.bgryid = bgryid;
}
public Long getSpmbid() {
	return spmbid;
}
public void setSpmbid(Long spmbid) {
	this.spmbid = spmbid;
}
public Long getXydzid() {
	return xydzid;
}
public void setXydzid(Long xydzid) {
	this.xydzid = xydzid;
}
public String getDzmc() {
	return dzmc;
}
public void setDzmc(String dzmc) {
	this.dzmc = dzmc;
}
public Long getBghrynbid() {
	return bghrynbid;
}
public void setBghrynbid(Long bghrynbid) {
	this.bghrynbid = bghrynbid;
}
public Long getHjywid() {
	return hjywid;
}
public void setHjywid(Long hjywid) {
	this.hjywid = hjywid;
}
public String getPxh() {
	return pxh;
}
public void setPxh(String pxh) {
	this.pxh = pxh;
}
public Long getSplsid() {
	return splsid;
}
public void setSplsid(Long splsid) {
	this.splsid = splsid;
}
public String getSplx() {
	return splx;
}
public void setSplx(String splx) {
	this.splx = splx;
}
public String getCzjg() {
	return czjg;
}
public void setCzjg(String czjg) {
	this.czjg = czjg;
}
public String getCzyj() {
	return czyj;
}
public void setCzyj(String czyj) {
	this.czyj = czyj;
}
public Long getCzrid() {
	return czrid;
}
public void setCzrid(Long czrid) {
	this.czrid = czrid;
}
public String getCzsj() {
	return czsj;
}
public void setCzsj(String czsj) {
	this.czsj = czsj;
}
public String getXydz_dzmc() {
	return xydz_dzmc;
}
public void setXydz_dzmc(String xydz_dzmc) {
	this.xydz_dzmc = xydz_dzmc;
}
public String getSpsm() {
	return spsm;
}
public void setSpsm(String spsm) {
	this.spsm = spsm;
}
public Long getDzid() {
	return dzid;
}
public void setDzid(Long dzid) {
	this.dzid = dzid;
}
public Long getSpywid() {
		return spywid;
}
public void setSpywid(Long spywid) {
	this.spywid = spywid;
}
}
