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
@Table(name="V_HJSCSPXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoV_HJSCSPXXB implements com.hzjc.wsstruts.po.PO
{

	private Long	spywid;
	private Long	rynbid;
	/*
	 *接口ID
	 */
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	splsid;
	private Long	spmbid;
	private Long	bscryid;
	private String	csyy;
	private Long	xydzid;
	private String	spjg;
	private String	lsbz;
	private Long	hjywid;
	private String	sqsj;
	private Long	sqyhid;
	private String	spsm;
	private String	hjscsm;
	private Long	nbsfzid;
	private String	qfjg;
	private String	yxqxqsrq;
	private String	yxqxjzrq;
	private String	swzxrq;
	private String	fqxm;
	private String	fqgmsfhm;
	private String	mqxm;
	private String	mqgmsfhm;
	private String	poxm;
	private String	pogmsfhm;
	private String	jggjdq;
	private String	jgssxq;
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
	private String	hsql;
	private String	hyql;
	private String	hgjdqql;
	private String	hssxqql;
	private String	hxzql;
	private String	hslbz;
	private String	hylbz;
	private String	hgjdqlbz;
	private String	hsssqlbz;
	private String	hxzlbz;
	private String	swrq;
	private String	swzxlb;
	private String	qcrq;
	private String	qczxlb;
	private String	qwdgjdq;
	private String	qwdssxq;
	private String	qwdxz;
	private String	cszmbh;
	private String	cszqfrq;
	private String	hylb;
	private String	qtssxq;
	private String	qtzz;
	private String	rylb;
	private String	hb;
	private String	yhzgx;
	private String	ryzt;
	private String	rysdzt;
	private Long	lxdbid;
	private String	bz;
	private String	jlbz;
	private String	ywnr;
	private Long	cjhjywid;
	private Long	cchjywid;
	private String	qysj;
	private String	jssj;
	private String	cxbz;
	private Long	ryid;
	private Long	hhnbid;
	private Long	mlpnbid;
	private Long	zpid;
	private String	gmsfhm;
	private String	xm;
	private String	cym;
	private String	xmpy;
	private String	cympy;
	private String	xb;
	private String	mz;
	private String	csrq;
	private String	cssj;
	private String	csdgjdq;
	private String	csdssxq;
	private String	csdxz;
	private String	dhhm;
	private String	jhryxm;
	private String	jhrygmsfhm;
	private String	jhryjhgx;
	private String	jhrexm;
	private String	jhregmsfhm;
	private String	jhrejhgx;
	private String	zjlb;
	private String	ssxq;
	private String	jlx;
	private String	mlph;
	private String	mlxz;
	private String	pcs;
	private String	zrq;
	private String	xzjd;
	private String	jcwh;
	private String	pxh;
	private String	hh;
	private String	hlx;
	private String	bdfw;
	private Long	hhid;
	private Long	mlpid;
	private String	xxqysj;
	private String	dhhm2;
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
	private String	yhkxzmc;
	private String	yhkxzsj;
	private String	bzdz;
	private String	bzdzid;
	private String	bzdzx;
	private String	bzdzy;
	private String	bzdzst;
	private String	cxfldm;
	private String	nyzyrklhczyydm;
	private String	lzd_cxfldm;
	private String	kdqqy_qrdqbm;
	private String	kdqqy_qrywid;
	private String	kdqqy_qrywlx;
	private String	kdqqy_qrfkzt;
	private String	kdqqy_qrfksj;
	private String	jthzl;
	private String	splx;
	private Long	dzid;
	private String	czjg;
	private String	czyj;
	private Long	czrid;
	private String	czsj;	
	private String	xydz_dzmc;
	private String	dzmc;

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
	public Long getSpywid() {
		return spywid;
	}
	public void setSpywid(Long spywid) {
		this.spywid = spywid;
	}
	public Long getSplsid() {
		return splsid;
	}
	public void setSplsid(Long splsid) {
		this.splsid = splsid;
	}
	public Long getSpmbid() {
		return spmbid;
	}
	public void setSpmbid(Long spmbid) {
		this.spmbid = spmbid;
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
	public String getFqgmsfhm() {
		return fqgmsfhm;
	}
	public void setFqgmsfhm(String fqgmsfhm) {
		this.fqgmsfhm = fqgmsfhm;
	}
	public String getFqxm() {
		return fqxm;
	}
	public void setFqxm(String fqxm) {
		this.fqxm = fqxm;
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
	public String getHb() {
		return hb;
	}
	public void setHb(String hb) {
		this.hb = hb;
	}
	public String getCszmbh() {
		return cszmbh;
	}
	public void setCszmbh(String cszmbh) {
		this.cszmbh = cszmbh;
	}
	public String getYhzgx() {
		return yhzgx;
	}
	public void setYhzgx(String yhzgx) {
		this.yhzgx = yhzgx;
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
	public Long getRynbid() {
		return rynbid;
	}
	public void setRynbid(Long rynbid) {
		this.rynbid = rynbid;
	}
	public Long getHjywid() {
		return hjywid;
	}
	public void setHjywid(Long hjywid) {
		this.hjywid = hjywid;
	}
	public String getSpsm() {
		return spsm;
	}
	public void setSpsm(String spsm) {
		this.spsm = spsm;
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
	public Long getBscryid() {
		return bscryid;
	}
	public void setBscryid(Long bscryid) {
		this.bscryid = bscryid;
	}
	public String getCsyy() {
		return csyy;
	}
	public void setCsyy(String csyy) {
		this.csyy = csyy;
	}
	public String getSqsj() {
		return sqsj;
	}
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	public Long getSqyhid() {
		return sqyhid;
	}
	public void setSqyhid(Long sqyhid) {
		this.sqyhid = sqyhid;
	}
	public String getHjscsm() {
		return hjscsm;
	}
	public void setHjscsm(String hjscsm) {
		this.hjscsm = hjscsm;
	}
	public Long getNbsfzid() {
		return nbsfzid;
	}
	public void setNbsfzid(Long nbsfzid) {
		this.nbsfzid = nbsfzid;
	}
	public String getQfjg() {
		return qfjg;
	}
	public void setQfjg(String qfjg) {
		this.qfjg = qfjg;
	}
	public String getYxqxqsrq() {
		return yxqxqsrq;
	}
	public void setYxqxqsrq(String yxqxqsrq) {
		this.yxqxqsrq = yxqxqsrq;
	}
	public String getYxqxjzrq() {
		return yxqxjzrq;
	}
	public void setYxqxjzrq(String yxqxjzrq) {
		this.yxqxjzrq = yxqxjzrq;
	}
	public String getSwzxrq() {
		return swzxrq;
	}
	public void setSwzxrq(String swzxrq) {
		this.swzxrq = swzxrq;
	}
	public String getHsql() {
		return hsql;
	}
	public void setHsql(String hsql) {
		this.hsql = hsql;
	}
	public String getHyql() {
		return hyql;
	}
	public void setHyql(String hyql) {
		this.hyql = hyql;
	}
	public String getHgjdqql() {
		return hgjdqql;
	}
	public void setHgjdqql(String hgjdqql) {
		this.hgjdqql = hgjdqql;
	}
	public String getHssxqql() {
		return hssxqql;
	}
	public void setHssxqql(String hssxqql) {
		this.hssxqql = hssxqql;
	}
	public String getHxzql() {
		return hxzql;
	}
	public void setHxzql(String hxzql) {
		this.hxzql = hxzql;
	}
	public String getHslbz() {
		return hslbz;
	}
	public void setHslbz(String hslbz) {
		this.hslbz = hslbz;
	}
	public String getHylbz() {
		return hylbz;
	}
	public void setHylbz(String hylbz) {
		this.hylbz = hylbz;
	}
	public String getHgjdqlbz() {
		return hgjdqlbz;
	}
	public void setHgjdqlbz(String hgjdqlbz) {
		this.hgjdqlbz = hgjdqlbz;
	}
	public String getHsssqlbz() {
		return hsssqlbz;
	}
	public void setHsssqlbz(String hsssqlbz) {
		this.hsssqlbz = hsssqlbz;
	}
	public String getHxzlbz() {
		return hxzlbz;
	}
	public void setHxzlbz(String hxzlbz) {
		this.hxzlbz = hxzlbz;
	}
	public String getSwrq() {
		return swrq;
	}
	public void setSwrq(String swrq) {
		this.swrq = swrq;
	}
	public String getSwzxlb() {
		return swzxlb;
	}
	public void setSwzxlb(String swzxlb) {
		this.swzxlb = swzxlb;
	}
	public String getQcrq() {
		return qcrq;
	}
	public void setQcrq(String qcrq) {
		this.qcrq = qcrq;
	}
	public String getQczxlb() {
		return qczxlb;
	}
	public void setQczxlb(String qczxlb) {
		this.qczxlb = qczxlb;
	}
	public String getQwdgjdq() {
		return qwdgjdq;
	}
	public void setQwdgjdq(String qwdgjdq) {
		this.qwdgjdq = qwdgjdq;
	}
	public String getQwdssxq() {
		return qwdssxq;
	}
	public void setQwdssxq(String qwdssxq) {
		this.qwdssxq = qwdssxq;
	}
	public String getQwdxz() {
		return qwdxz;
	}
	public void setQwdxz(String qwdxz) {
		this.qwdxz = qwdxz;
	}
	public String getCszqfrq() {
		return cszqfrq;
	}
	public void setCszqfrq(String cszqfrq) {
		this.cszqfrq = cszqfrq;
	}
	public String getHylb() {
		return hylb;
	}
	public void setHylb(String hylb) {
		this.hylb = hylb;
	}
	public String getQtssxq() {
		return qtssxq;
	}
	public void setQtssxq(String qtssxq) {
		this.qtssxq = qtssxq;
	}
	public String getQtzz() {
		return qtzz;
	}
	public void setQtzz(String qtzz) {
		this.qtzz = qtzz;
	}
	public String getRylb() {
		return rylb;
	}
	public void setRylb(String rylb) {
		this.rylb = rylb;
	}
	public String getRyzt() {
		return ryzt;
	}
	public void setRyzt(String ryzt) {
		this.ryzt = ryzt;
	}
	public String getRysdzt() {
		return rysdzt;
	}
	public void setRysdzt(String rysdzt) {
		this.rysdzt = rysdzt;
	}
	public Long getLxdbid() {
		return lxdbid;
	}
	public void setLxdbid(Long lxdbid) {
		this.lxdbid = lxdbid;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getJlbz() {
		return jlbz;
	}
	public void setJlbz(String jlbz) {
		this.jlbz = jlbz;
	}
	public String getYwnr() {
		return ywnr;
	}
	public void setYwnr(String ywnr) {
		this.ywnr = ywnr;
	}
	public Long getCjhjywid() {
		return cjhjywid;
	}
	public void setCjhjywid(Long cjhjywid) {
		this.cjhjywid = cjhjywid;
	}
	public Long getCchjywid() {
		return cchjywid;
	}
	public void setCchjywid(Long cchjywid) {
		this.cchjywid = cchjywid;
	}
	public String getQysj() {
		return qysj;
	}
	public void setQysj(String qysj) {
		this.qysj = qysj;
	}
	public String getJssj() {
		return jssj;
	}
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	public String getCxbz() {
		return cxbz;
	}
	public void setCxbz(String cxbz) {
		this.cxbz = cxbz;
	}
	public Long getRyid() {
		return ryid;
	}
	public void setRyid(Long ryid) {
		this.ryid = ryid;
	}
	public Long getHhnbid() {
		return hhnbid;
	}
	public void setHhnbid(Long hhnbid) {
		this.hhnbid = hhnbid;
	}
	public Long getMlpnbid() {
		return mlpnbid;
	}
	public void setMlpnbid(Long mlpnbid) {
		this.mlpnbid = mlpnbid;
	}
	public Long getZpid() {
		return zpid;
	}
	public void setZpid(Long zpid) {
		this.zpid = zpid;
	}
	public String getXmpy() {
		return xmpy;
	}
	public void setXmpy(String xmpy) {
		this.xmpy = xmpy;
	}
	public String getCympy() {
		return cympy;
	}
	public void setCympy(String cympy) {
		this.cympy = cympy;
	}
	public String getDhhm() {
		return dhhm;
	}
	public void setDhhm(String dhhm) {
		this.dhhm = dhhm;
	}
	public String getZjlb() {
		return zjlb;
	}
	public void setZjlb(String zjlb) {
		this.zjlb = zjlb;
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
	public String getPxh() {
		return pxh;
	}
	public void setPxh(String pxh) {
		this.pxh = pxh;
	}
	public String getHh() {
		return hh;
	}
	public void setHh(String hh) {
		this.hh = hh;
	}
	public String getHlx() {
		return hlx;
	}
	public void setHlx(String hlx) {
		this.hlx = hlx;
	}
	public String getBdfw() {
		return bdfw;
	}
	public void setBdfw(String bdfw) {
		this.bdfw = bdfw;
	}
	public Long getHhid() {
		return hhid;
	}
	public void setHhid(Long hhid) {
		this.hhid = hhid;
	}
	public Long getMlpid() {
		return mlpid;
	}
	public void setMlpid(Long mlpid) {
		this.mlpid = mlpid;
	}
	public String getXxqysj() {
		return xxqysj;
	}
	public void setXxqysj(String xxqysj) {
		this.xxqysj = xxqysj;
	}
	public String getDhhm2() {
		return dhhm2;
	}
	public void setDhhm2(String dhhm2) {
		this.dhhm2 = dhhm2;
	}
	public String getYhkxzmc() {
		return yhkxzmc;
	}
	public void setYhkxzmc(String yhkxzmc) {
		this.yhkxzmc = yhkxzmc;
	}
	public String getYhkxzsj() {
		return yhkxzsj;
	}
	public void setYhkxzsj(String yhkxzsj) {
		this.yhkxzsj = yhkxzsj;
	}
	public String getNyzyrklhczyydm() {
		return nyzyrklhczyydm;
	}
	public void setNyzyrklhczyydm(String nyzyrklhczyydm) {
		this.nyzyrklhczyydm = nyzyrklhczyydm;
	}
	public String getLzd_cxfldm() {
		return lzd_cxfldm;
	}
	public void setLzd_cxfldm(String lzd_cxfldm) {
		this.lzd_cxfldm = lzd_cxfldm;
	}
	public String getKdqqy_qrdqbm() {
		return kdqqy_qrdqbm;
	}
	public void setKdqqy_qrdqbm(String kdqqy_qrdqbm) {
		this.kdqqy_qrdqbm = kdqqy_qrdqbm;
	}
	public String getKdqqy_qrywid() {
		return kdqqy_qrywid;
	}
	public void setKdqqy_qrywid(String kdqqy_qrywid) {
		this.kdqqy_qrywid = kdqqy_qrywid;
	}
	public String getKdqqy_qrywlx() {
		return kdqqy_qrywlx;
	}
	public void setKdqqy_qrywlx(String kdqqy_qrywlx) {
		this.kdqqy_qrywlx = kdqqy_qrywlx;
	}
	public String getKdqqy_qrfkzt() {
		return kdqqy_qrfkzt;
	}
	public void setKdqqy_qrfkzt(String kdqqy_qrfkzt) {
		this.kdqqy_qrfkzt = kdqqy_qrfkzt;
	}
	public String getKdqqy_qrfksj() {
		return kdqqy_qrfksj;
	}
	public void setKdqqy_qrfksj(String kdqqy_qrfksj) {
		this.kdqqy_qrfksj = kdqqy_qrfksj;
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
	public String getSplx() {
		return splx;
	}
	public void setSplx(String splx) {
		this.splx = splx;
	}
	public Long getDzid() {
		return dzid;
	}
	public void setDzid(Long dzid) {
		this.dzid = dzid;
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
	public String getDzmc() {
		return dzmc;
	}
	public void setDzmc(String dzmc) {
		this.dzmc = dzmc;
	}
	public String getXydz_dzmc() {
		return xydz_dzmc;
	}
	public void setXydz_dzmc(String xydz_dzmc) {
		this.xydz_dzmc = xydz_dzmc;
	}
}
