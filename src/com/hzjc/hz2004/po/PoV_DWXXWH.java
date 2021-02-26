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
@Table(name="V_DWXXWH" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoV_DWXXWH implements com.hzjc.wsstruts.po.PO
{

	/*
	*接口ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private String dm;
	private	String xzqhbdm;
	private Long	csb1csid;
	private Long	csb2csid;
	private	String	mc;
	private String 	dhhm;//add by zjm 20210220修改单位信息视图，增加电话号码字段
	private	String	zwpy;
	private	String	wbpy;
	private	String	dwjgdm;
	private	String	qhdm;
	private	String	dwjb;
	private	String	dz;
	private	String	bz;
	private	String	qybz;
	private	String	bdlx;
	private	String	bdsj;
	private	String	fjjgdm;
	private	String	fjjgmc;
	private	String	xzqhbmc;
	private	String	xzqhbzwpy;
	private	String	xzqhbwbpy;
	private	String	xzqhbbz;
	private	String	xzqhbqybz;
	private	String	xzqhbbdlx;
	private	String	xzqhbbdsj;
	private	String	csb1cslb;
	private	String	csb1dm;
	private	String	csb1mc;
	private	String	csb1zwpy;
	private	String	csb1kzbzb;
	private	String	csb1kzbzc;
	private	String	csb1kzbzd;
	private	String	csb1kzbze;
	private	String	csb1kzbzf;
	private	String	csb1kzbzg;
	private	String	csb1xgbz;
	private	String	csb1bdlx;
	private	String	csb1bdsj;
	private	String	csb2cslb;
	private	String	csb2dm;
	private	String	csb2mc;
	private	String	csb2zwpy;
	private	String	csb2kzbzb; 
	private	String	csb2kzbzc;
	private	String	csb2kzbzd;
	private	String	csb2kzbze;
	private	String	csb2kzbzf;
	private	String	csb2kzbzg;
	private	String	csb2xgbz;
	private	String	csb2bdlx;
	private	String	csb2bdsj;
	private byte[]	zp;//照片
	
	public String getdm() {
		return dm;
	}
	public void setdm(String dm) {
		this.dm = dm;
	}
	public String getXzqhbdm() {
		return xzqhbdm;
	}
	public void setXzqhbdm(String xzqhbdm) {
		this.xzqhbdm = xzqhbdm;
	}
	public Long getCsb1csid() {
		return csb1csid;
	}
	public void setCsb1csid(Long csb1csid) {
		this.csb1csid = csb1csid;
	}
	public Long getCsb2csid() {
		return csb2csid;
	}
	public void setCsb2csid(Long csb2csid) {
		this.csb2csid = csb2csid;
	}
	public String getmc() {
		return mc;
	}
	public void setmc(String mc) {
		this.mc = mc;
	}
	public String getzwpy() {
		return zwpy;
	}
	public void setzwpy(String zwpy) {
		this.zwpy = zwpy;
	}
	public String getwbpy() {
		return wbpy;
	}
	public void setwbpy(String wbpy) {
		this.wbpy = wbpy;
	}
	public String getDwjgdm() {
		return dwjgdm;
	}
	public void setDwjgdm(String dwjgdm) {
		this.dwjgdm = dwjgdm;
	}
	public String getQhdm() {
		return qhdm;
	}
	public void setQhdm(String qhdm) {
		this.qhdm = qhdm;
	}
	public String getDwjb() {
		return dwjb;
	}
	public void setDwjb(String dwjb) {
		this.dwjb = dwjb;
	}
	
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public String getZwpy() {
		return zwpy;
	}
	public void setZwpy(String zwpy) {
		this.zwpy = zwpy;
	}
	public String getWbpy() {
		return wbpy;
	}
	public void setWbpy(String wbpy) {
		this.wbpy = wbpy;
	}
	public String getDz() {
		return dz;
	}
	public void setDz(String dz) {
		this.dz = dz;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getQybz() {
		return qybz;
	}
	public void setQybz(String qybz) {
		this.qybz = qybz;
	}
	public String getBdlx() {
		return bdlx;
	}
	public void setBdlx(String bdlx) {
		this.bdlx = bdlx;
	}
	public String getBdsj() {
		return bdsj;
	}
	public void setBdsj(String bdsj) {
		this.bdsj = bdsj;
	}
	public String getbz() {
		return bz;
	}
	public void setbz(String bz) {
		this.bz = bz;
	}
	public String getqybz() {
		return qybz;
	}
	public void setqybz(String qybz) {
		this.qybz = qybz;
	}
	public String getbdlx() {
		return bdlx;
	}
	public void setbdlx(String bdlx) {
		this.bdlx = bdlx;
	}
	public String getbdsj() {
		return bdsj;
	}
	public void setbdsj(String bdsj) {
		this.bdsj = bdsj;
	}
	public String getFjjgdm() {
		return fjjgdm;
	}
	public void setFjjgdm(String fjjgdm) {
		this.fjjgdm = fjjgdm;
	}
	public String getFjjgmc() {
		return fjjgmc;
	}
	public void setFjjgmc(String fjjgmc) {
		this.fjjgmc = fjjgmc;
	}
	public String getXzqhbmc() {
		return xzqhbmc;
	}
	public void setXzqhbmc(String xzqhbmc) {
		this.xzqhbmc = xzqhbmc;
	}
	public String getXzqhbzwpy() {
		return xzqhbzwpy;
	}
	public void setXzqhbzwpy(String xzqhbzwpy) {
		this.xzqhbzwpy = xzqhbzwpy;
	}
	public String getXzqhbwbpy() {
		return xzqhbwbpy;
	}
	public void setXzqhbwbpy(String xzqhbwbpy) {
		this.xzqhbwbpy = xzqhbwbpy;
	}
	public String getXzqhbbz() {
		return xzqhbbz;
	}
	public void setXzqhbbz(String xzqhbbz) {
		this.xzqhbbz = xzqhbbz;
	}
	public String getXzqhbqybz() {
		return xzqhbqybz;
	}
	public void setXzqhbqybz(String xzqhbqybz) {
		this.xzqhbqybz = xzqhbqybz;
	}
	public String getXzqhbbdlx() {
		return xzqhbbdlx;
	}
	public void setXzqhbbdlx(String xzqhbbdlx) {
		this.xzqhbbdlx = xzqhbbdlx;
	}
	public String getXzqhbbdsj() {
		return xzqhbbdsj;
	}
	public void setXzqhbbdsj(String xzqhbbdsj) {
		this.xzqhbbdsj = xzqhbbdsj;
	}
	public String getCsb1cslb() {
		return csb1cslb;
	}
	public void setCsb1cslb(String csb1cslb) {
		this.csb1cslb = csb1cslb;
	}
	public String getCsb1dm() {
		return csb1dm;
	}
	public void setCsb1dm(String csb1dm) {
		this.csb1dm = csb1dm;
	}
	public String getCsb1mc() {
		return csb1mc;
	}
	public void setCsb1mc(String csb1mc) {
		this.csb1mc = csb1mc;
	}
	public String getCsb1zwpy() {
		return csb1zwpy;
	}
	public void setCsb1zwpy(String csb1zwpy) {
		this.csb1zwpy = csb1zwpy;
	}
	public String getCsb1kzbzb() {
		return csb1kzbzb;
	}
	public void setCsb1kzbzb(String csb1kzbzb) {
		this.csb1kzbzb = csb1kzbzb;
	}
	public String getCsb1kzbzc() {
		return csb1kzbzc;
	}
	public void setCsb1kzbzc(String csb1kzbzc) {
		this.csb1kzbzc = csb1kzbzc;
	}
	public String getCsb1kzbzd() {
		return csb1kzbzd;
	}
	public void setCsb1kzbzd(String csb1kzbzd) {
		this.csb1kzbzd = csb1kzbzd;
	}
	public String getCsb1kzbze() {
		return csb1kzbze;
	}
	public void setCsb1kzbze(String csb1kzbze) {
		this.csb1kzbze = csb1kzbze;
	}
	public String getCsb1kzbzf() {
		return csb1kzbzf;
	}
	public void setCsb1kzbzf(String csb1kzbzf) {
		this.csb1kzbzf = csb1kzbzf;
	}
	public String getCsb1kzbzg() {
		return csb1kzbzg;
	}
	public void setCsb1kzbzg(String csb1kzbzg) {
		this.csb1kzbzg = csb1kzbzg;
	}
	public String getCsb1xgbz() {
		return csb1xgbz;
	}
	public void setCsb1xgbz(String csb1xgbz) {
		this.csb1xgbz = csb1xgbz;
	}
	public String getCsb1bdlx() {
		return csb1bdlx;
	}
	public void setCsb1bdlx(String csb1bdlx) {
		this.csb1bdlx = csb1bdlx;
	}
	public String getCsb1bdsj() {
		return csb1bdsj;
	}
	public void setCsb1bdsj(String csb1bdsj) {
		this.csb1bdsj = csb1bdsj;
	}
	public String getCsb2cslb() {
		return csb2cslb;
	}
	public void setCsb2cslb(String csb2cslb) {
		this.csb2cslb = csb2cslb;
	}
	public String getCsb2dm() {
		return csb2dm;
	}
	public void setCsb2dm(String csb2dm) {
		this.csb2dm = csb2dm;
	}
	public String getCsb2mc() {
		return csb2mc;
	}
	public void setCsb2mc(String csb2mc) {
		this.csb2mc = csb2mc;
	}
	public String getCsb2zwpy() {
		return csb2zwpy;
	}
	public void setCsb2zwpy(String csb2zwpy) {
		this.csb2zwpy = csb2zwpy;
	}
	public String getCsb2kzbzb() {
		return csb2kzbzb;
	}
	public void setCsb2kzbzb(String csb2kzbzb) {
		this.csb2kzbzb = csb2kzbzb;
	}
	public String getCsb2kzbzc() {
		return csb2kzbzc;
	}
	public void setCsb2kzbzc(String csb2kzbzc) {
		this.csb2kzbzc = csb2kzbzc;
	}
	public String getCsb2kzbzd() {
		return csb2kzbzd;
	}
	public void setCsb2kzbzd(String csb2kzbzd) {
		this.csb2kzbzd = csb2kzbzd;
	}
	public String getCsb2kzbze() {
		return csb2kzbze;
	}
	public void setCsb2kzbze(String csb2kzbze) {
		this.csb2kzbze = csb2kzbze;
	}
	public String getCsb2kzbzf() {
		return csb2kzbzf;
	}
	public void setCsb2kzbzf(String csb2kzbzf) {
		this.csb2kzbzf = csb2kzbzf;
	}
	public String getCsb2kzbzg() {
		return csb2kzbzg;
	}
	public void setCsb2kzbzg(String csb2kzbzg) {
		this.csb2kzbzg = csb2kzbzg;
	}
	public String getCsb2xgbz() {
		return csb2xgbz;
	}
	public void setCsb2xgbz(String csb2xgbz) {
		this.csb2xgbz = csb2xgbz;
	}
	public String getCsb2bdlx() {
		return csb2bdlx;
	}
	public void setCsb2bdlx(String csb2bdlx) {
		this.csb2bdlx = csb2bdlx;
	}
	public String getCsb2bdsj() {
		return csb2bdsj;
	}
	public void setCsb2bdsj(String csb2bdsj) {
		this.csb2bdsj = csb2bdsj;
	}
	public byte[] getZp() {
		return zp;
	}
	public void setZp(byte[] zp) {
		this.zp = zp;
	}
	public String getDhhm() {
		return dhhm;
	}
	public void setDhhm(String dhhm) {
		this.dhhm = dhhm;
	} 
}
