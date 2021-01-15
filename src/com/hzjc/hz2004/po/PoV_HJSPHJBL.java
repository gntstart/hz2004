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
@Table(name="V_HJSPHJBL" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoV_HJSPHJBL implements com.hzjc.wsstruts.po.PO
{

	/*
	*接口ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
    private Long	spywid;
	private Long	dzid;
	private Long	spmbid;
	private String	jhryxm;
	private String	jhrygmsfhm;
	private String	jhryjhgx;
	private String	jhrexm;
	private String	jhregmsfhm;
	private String	jhrejhgx;
	private String gmsfhm;
	private String xm;
	private String	cym;
	private String	xb;
	private String	mz;
	private String	csrq;
	private String	cssj;
	private String	csdgjdq;
	private String	csdssxq;
	private String	csdxz;
	private String	jggjdq;
	private String	jgssxq;
	private String	fqxm;
	private String	fqgmsfhm;
	private String	mqxm;
	private String	mqgmsfhm;
	private String	poxm;
	private String	pogmsfhm;
	private String	zjxy;
	private String	whcd;
	private String	hyzk;
	private String	byzk;
	private String	sg;
	private String	xx;
	private String	zy;
	private String	zylb;
	private String	fwcs;
	private String	xxjb;
	private String	hb;
	private String	cszmbh;
	private String	ssssxq;
	private String	sspcs;
	private String	ssjwh;
	private String	ssjlx;
	private String	ssxzjd;
	private String	ssjwzrq;
	private String	ssmlph;
	private String	ssxz;
	private Long	sshhid;
	private String	sshh;
	private String	sshlx;
	private String	yhzgx;
	private String	rlhbz;
	private String	blyy;
	private Long	djrid;
	private String	djsj;
	private Long	xydzid;
	private String	spjg;
	private String	lsbz;
	private Long	rynbid;
	private Long	hjywid;
	private String	spsm;
	private Long	ywxl;
	private String	xmx;
	private String	xmm;
	private String	jgxz;
	private String	jhryzjzl;
	private String	jhryzjhm;
	private String	jhrywwx;
	private String	jhrywwm;
	private String	jhrylxdh;
	private String	jhrezjzl;
	private String	jhrezjhm;
	private String	jhrewwx;
	private String	jhrewwm;
	private String	jhrelxdh;
	private String	fqzjzl;
	private String	fqzjhm;
	private String	fqwwx;
	private String	fqwwm;
	private String	mqzjzl;
	private String	mqzjhm;
	private String	mqwwx;
	private String	mqwwm;
	private String	pozjzl;
	private String	pozjhm;
	private String	powwx;
	private String	powwm;
	private String	qyldyy;
	private String	sbrjtgx;
	private String	bzdz;
	private String	bzdzid;
	private String	bzdzx;
	private String	bzdzy;
	private String	bzdzst;
	private String	cxfldm;
	private String	jthzl;
	private String	fwjb;
	private String	dzmc;
	private String	ms;
	private String	qybz;
	
	public Long getSpywid() {
		return spywid;
	}
	public void setSpywid(Long spywid) {
		this.spywid = spywid;
	}
	public Long getRynbid() {
		return rynbid;
	}
	public void setRynbid(Long rynbid) {
		this.rynbid = rynbid;
	}
	public Long getDzid() {
		return dzid;
	}
	public void setDzid(Long dzid) {
		this.dzid = dzid;
	}
	public Long getXydzid() {
		return xydzid;
	}
	public void setXydzid(Long xydzid) {
		this.xydzid = xydzid;
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
	public Long getHjywid() {
		return hjywid;
	}
	public void setHjywid(Long hjywid) {
		this.hjywid = hjywid;
	}
	public Long getSpmbid() {
		return spmbid;
	}
	public void setSpmbid(Long spmbid) {
		this.spmbid = spmbid;
	}
	public String getSpsm() {
		return spsm;
	}
	public void setSpsm(String spsm) {
		this.spsm = spsm;
	}
	public String getFqxm() {
		return fqxm;
	}
	public void setFqxm(String fqxm) {
		this.fqxm = fqxm;
	}
	public String getFqgmsfhm() {
		return fqgmsfhm;
	}
	public void setFqgmsfhm(String fqgmsfhm) {
		this.fqgmsfhm = fqgmsfhm;
	}
	public String getMqxm() {
		return mqxm;
	}
	public void setMqxm(String mqxm) {
		this.mqxm = mqxm;
	}
	public String getMqgmsfhm() {
		return mqgmsfhm;
	}
	public void setMqgmsfhm(String mqgmsfhm) {
		this.mqgmsfhm = mqgmsfhm;
	}
	public String getPoxm() {
		return poxm;
	}
	public void setPoxm(String poxm) {
		this.poxm = poxm;
	}
	public String getPogmsfhm() {
		return pogmsfhm;
	}
	public void setPogmsfhm(String pogmsfhm) {
		this.pogmsfhm = pogmsfhm;
	}
	public String getJggjdq() {
		return jggjdq;
	}
	public void setJggjdq(String jggjdq) {
		this.jggjdq = jggjdq;
	}
	public String getJgssxq() {
		return jgssxq;
	}
	public void setJgssxq(String jgssxq) {
		this.jgssxq = jgssxq;
	}
	public String getZjxy() {
		return zjxy;
	}
	public void setZjxy(String zjxy) {
		this.zjxy = zjxy;
	}
	public String getWhcd() {
		return whcd;
	}
	public void setWhcd(String whcd) {
		this.whcd = whcd;
	}
	public String getHyzk() {
		return hyzk;
	}
	public void setHyzk(String hyzk) {
		this.hyzk = hyzk;
	}
	public String getByzk() {
		return byzk;
	}
	public void setByzk(String byzk) {
		this.byzk = byzk;
	}
	public String getSg() {
		return sg;
	}
	public void setSg(String sg) {
		this.sg = sg;
	}
	public String getXx() {
		return xx;
	}
	public void setXx(String xx) {
		this.xx = xx;
	}
	public String getZy() {
		return zy;
	}
	public void setZy(String zy) {
		this.zy = zy;
	}
	public String getZylb() {
		return zylb;
	}
	public void setZylb(String zylb) {
		this.zylb = zylb;
	}
	public String getFwcs() {
		return fwcs;
	}
	public void setFwcs(String fwcs) {
		this.fwcs = fwcs;
	}
	public String getXxjb() {
		return xxjb;
	}
	public void setXxjb(String xxjb) {
		this.xxjb = xxjb;
	}
	public String getCszmbh() {
		return cszmbh;
	}
	public void setCszmbh(String cszmbh) {
		this.cszmbh = cszmbh;
	}
	public String getHb() {
		return hb;
	}
	public void setHb(String hb) {
		this.hb = hb;
	}
	public String getYhzgx() {
		return yhzgx;
	}
	public void setYhzgx(String yhzgx) {
		this.yhzgx = yhzgx;
	}
	public String getGmsfhm() {
		return gmsfhm;
	}
	public void setGmsfhm(String gmsfhm) {
		this.gmsfhm = gmsfhm;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getCym() {
		return cym;
	}
	public void setCym(String cym) {
		this.cym = cym;
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
	public String getCssj() {
		return cssj;
	}
	public void setCssj(String cssj) {
		this.cssj = cssj;
	}
	public String getCsdgjdq() {
		return csdgjdq;
	}
	public void setCsdgjdq(String csdgjdq) {
		this.csdgjdq = csdgjdq;
	}
	public String getCsdssxq() {
		return csdssxq;
	}
	public void setCsdssxq(String csdssxq) {
		this.csdssxq = csdssxq;
	}
	public String getCsdxz() {
		return csdxz;
	}
	public void setCsdxz(String csdxz) {
		this.csdxz = csdxz;
	}
	public String getJhryxm() {
		return jhryxm;
	}
	public void setJhryxm(String jhryxm) {
		this.jhryxm = jhryxm;
	}
	public String getJhrygmsfhm() {
		return jhrygmsfhm;
	}
	public void setJhrygmsfhm(String jhrygmsfhm) {
		this.jhrygmsfhm = jhrygmsfhm;
	}
	public String getJhryjhgx() {
		return jhryjhgx;
	}
	public void setJhryjhgx(String jhryjhgx) {
		this.jhryjhgx = jhryjhgx;
	}
	public String getJhrexm() {
		return jhrexm;
	}
	public void setJhrexm(String jhrexm) {
		this.jhrexm = jhrexm;
	}
	public String getJhregmsfhm() {
		return jhregmsfhm;
	}
	public void setJhregmsfhm(String jhregmsfhm) {
		this.jhregmsfhm = jhregmsfhm;
	}
	public String getJhrejhgx() {
		return jhrejhgx;
	}
	public void setJhrejhgx(String jhrejhgx) {
		this.jhrejhgx = jhrejhgx;
	}
	public String getXmx() {
		return xmx;
	}
	public void setXmx(String xmx) {
		this.xmx = xmx;
	}
	public String getXmm() {
		return xmm;
	}
	public void setXmm(String xmm) {
		this.xmm = xmm;
	}
	public String getJgxz() {
		return jgxz;
	}
	public void setJgxz(String jgxz) {
		this.jgxz = jgxz;
	}
	public String getJhryzjzl() {
		return jhryzjzl;
	}
	public void setJhryzjzl(String jhryzjzl) {
		this.jhryzjzl = jhryzjzl;
	}
	public String getJhryzjhm() {
		return jhryzjhm;
	}
	public void setJhryzjhm(String jhryzjhm) {
		this.jhryzjhm = jhryzjhm;
	}
	public String getJhrywwx() {
		return jhrywwx;
	}
	public void setJhrywwx(String jhrywwx) {
		this.jhrywwx = jhrywwx;
	}
	public String getJhrywwm() {
		return jhrywwm;
	}
	public void setJhrywwm(String jhrywwm) {
		this.jhrywwm = jhrywwm;
	}
	public String getJhrylxdh() {
		return jhrylxdh;
	}
	public void setJhrylxdh(String jhrylxdh) {
		this.jhrylxdh = jhrylxdh;
	}
	public String getJhrezjzl() {
		return jhrezjzl;
	}
	public void setJhrezjzl(String jhrezjzl) {
		this.jhrezjzl = jhrezjzl;
	}
	public String getJhrezjhm() {
		return jhrezjhm;
	}
	public void setJhrezjhm(String jhrezjhm) {
		this.jhrezjhm = jhrezjhm;
	}
	public String getJhrewwx() {
		return jhrewwx;
	}
	public void setJhrewwx(String jhrewwx) {
		this.jhrewwx = jhrewwx;
	}
	public String getJhrewwm() {
		return jhrewwm;
	}
	public void setJhrewwm(String jhrewwm) {
		this.jhrewwm = jhrewwm;
	}
	public String getJhrelxdh() {
		return jhrelxdh;
	}
	public void setJhrelxdh(String jhrelxdh) {
		this.jhrelxdh = jhrelxdh;
	}
	public String getFqzjzl() {
		return fqzjzl;
	}
	public void setFqzjzl(String fqzjzl) {
		this.fqzjzl = fqzjzl;
	}
	public String getFqzjhm() {
		return fqzjhm;
	}
	public void setFqzjhm(String fqzjhm) {
		this.fqzjhm = fqzjhm;
	}
	public String getFqwwx() {
		return fqwwx;
	}
	public void setFqwwx(String fqwwx) {
		this.fqwwx = fqwwx;
	}
	public String getFqwwm() {
		return fqwwm;
	}
	public void setFqwwm(String fqwwm) {
		this.fqwwm = fqwwm;
	}
	public String getMqzjzl() {
		return mqzjzl;
	}
	public void setMqzjzl(String mqzjzl) {
		this.mqzjzl = mqzjzl;
	}
	public String getMqzjhm() {
		return mqzjhm;
	}
	public void setMqzjhm(String mqzjhm) {
		this.mqzjhm = mqzjhm;
	}
	public String getMqwwx() {
		return mqwwx;
	}
	public void setMqwwx(String mqwwx) {
		this.mqwwx = mqwwx;
	}
	public String getMqwwm() {
		return mqwwm;
	}
	public void setMqwwm(String mqwwm) {
		this.mqwwm = mqwwm;
	}
	public String getPozjzl() {
		return pozjzl;
	}
	public void setPozjzl(String pozjzl) {
		this.pozjzl = pozjzl;
	}
	public String getPozjhm() {
		return pozjhm;
	}
	public void setPozjhm(String pozjhm) {
		this.pozjhm = pozjhm;
	}
	public String getPowwx() {
		return powwx;
	}
	public void setPowwx(String powwx) {
		this.powwx = powwx;
	}
	public String getPowwm() {
		return powwm;
	}
	public void setPowwm(String powwm) {
		this.powwm = powwm;
	}
	public String getQyldyy() {
		return qyldyy;
	}
	public void setQyldyy(String qyldyy) {
		this.qyldyy = qyldyy;
	}
	public String getBzdz() {
		return bzdz;
	}
	public void setBzdz(String bzdz) {
		this.bzdz = bzdz;
	}
	public String getBzdzid() {
		return bzdzid;
	}
	public void setBzdzid(String bzdzid) {
		this.bzdzid = bzdzid;
	}
	public String getBzdzx() {
		return bzdzx;
	}
	public void setBzdzx(String bzdzx) {
		this.bzdzx = bzdzx;
	}
	public String getBzdzy() {
		return bzdzy;
	}
	public void setBzdzy(String bzdzy) {
		this.bzdzy = bzdzy;
	}
	public String getBzdzst() {
		return bzdzst;
	}
	public void setBzdzst(String bzdzst) {
		this.bzdzst = bzdzst;
	}
	public String getCxfldm() {
		return cxfldm;
	}
	public void setCxfldm(String cxfldm) {
		this.cxfldm = cxfldm;
	}
	public String getJthzl() {
		return jthzl;
	}
	public void setJthzl(String jthzl) {
		this.jthzl = jthzl;
	}
	public String getFwjb() {
		return fwjb;
	}
	public void setFwjb(String fwjb) {
		this.fwjb = fwjb;
	}
	public String getDzmc() {
		return dzmc;
	}
	public void setDzmc(String dzmc) {
		this.dzmc = dzmc;
	}
	public String getMs() {
		return ms;
	}
	public void setMs(String ms) {
		this.ms = ms;
	}
	public String getQybz() {
		return qybz;
	}
	public void setQybz(String qybz) {
		this.qybz = qybz;
	}
	public String getSsssxq() {
		return ssssxq;
	}
	public void setSsssxq(String ssssxq) {
		this.ssssxq = ssssxq;
	}
	public String getSspcs() {
		return sspcs;
	}
	public void setSspcs(String sspcs) {
		this.sspcs = sspcs;
	}
	public String getSsjwh() {
		return ssjwh;
	}
	public void setSsjwh(String ssjwh) {
		this.ssjwh = ssjwh;
	}
	public String getSsjlx() {
		return ssjlx;
	}
	public void setSsjlx(String ssjlx) {
		this.ssjlx = ssjlx;
	}
	public String getSsxzjd() {
		return ssxzjd;
	}
	public void setSsxzjd(String ssxzjd) {
		this.ssxzjd = ssxzjd;
	}
	public String getSsjwzrq() {
		return ssjwzrq;
	}
	public void setSsjwzrq(String ssjwzrq) {
		this.ssjwzrq = ssjwzrq;
	}
	public String getSsmlph() {
		return ssmlph;
	}
	public void setSsmlph(String ssmlph) {
		this.ssmlph = ssmlph;
	}
	public String getSsxz() {
		return ssxz;
	}
	public void setSsxz(String ssxz) {
		this.ssxz = ssxz;
	}
	public Long getSshhid() {
		return sshhid;
	}
	public void setSshhid(Long sshhid) {
		this.sshhid = sshhid;
	}
	public String getSshh() {
		return sshh;
	}
	public void setSshh(String sshh) {
		this.sshh = sshh;
	}
	public String getSshlx() {
		return sshlx;
	}
	public void setSshlx(String sshlx) {
		this.sshlx = sshlx;
	}
	public String getRlhbz() {
		return rlhbz;
	}
	public void setRlhbz(String rlhbz) {
		this.rlhbz = rlhbz;
	}
	public String getBlyy() {
		return blyy;
	}
	public void setBlyy(String blyy) {
		this.blyy = blyy;
	}
	public Long getDjrid() {
		return djrid;
	}
	public void setDjrid(Long djrid) {
		this.djrid = djrid;
	}
	public String getDjsj() {
		return djsj;
	}
	public void setDjsj(String djsj) {
		this.djsj = djsj;
	}
	public Long getYwxl() {
		return ywxl;
	}
	public void setYwxl(Long ywxl) {
		this.ywxl = ywxl;
	}
	public String getSbrjtgx() {
		return sbrjtgx;
	}
	public void setSbrjtgx(String sbrjtgx) {
		this.sbrjtgx = sbrjtgx;
	}
}
