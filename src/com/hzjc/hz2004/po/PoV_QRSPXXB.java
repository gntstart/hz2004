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
@Table(name="V_QRSPXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoV_QRSPXXB implements com.hzjc.wsstruts.po.PO
{

	/*
	*接口ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	spywid;
	private String	sqrgmsfhm;
	private Long	spmbid;
	private String	sqrxm;
	private String	sqrzzssxq;	
	private String	splx;
	private String	slrq;
	private String	xm;
	private Long	ryid;
	private String	xb;
	private String	gmsfhm;
	private String	csrq;
	private String	mz;
	private String	jhny;
	private String	yjrclx;
	private String	yrdwjcyrysj;
	private String	lxdh;
	private String	byxx;
	private String	whcd;
	private String	bysj;
	private String	zyzl;
	private String	byzsh;
	private String	zyjszc;
	private String	jszsh;
	private String	jsfzjg;
	private String	fmzlmc;
	private String	fmzlh;
	private String	zlfzjg;
	private String	hb;
	private String	qrhhb;
	private String	zzssxq;
	private String	zzxz;
	private String	hkszddjjg;
	private String	qrdqx;
	private String	qrdpcs;
	private String	qrdjwzrq;
	private String	qrdxzjd;
	private String	qrdjwh;
	private String	qrdjlx;
	private String	qrdmlph;
	private String	qrdz;
	private String	qrdhkdjjg;
	private Long	qrdhhid;
	private String	qrdhlx;
	private String	rlhbz;
	private String	sqqrly;
	private String	sqrzzxz;
	private String	bz;
	private Long	djrid;
	private String	djsj;
	private Long	xydzid;
	private String	spjg;
	private String	sqrhkdjjg;
	private String	lsbz;
	private Long	rynbid;
	private Long	hjywid;
	private String	ysqrgx;
	private String	spsm;
	private String	bzdz;
	private String	bzdzid;
	private String	bzdzx;
	private String	bzdzy;
	private String	bzdzst;
	private String	cxfldm;
	private String	nyzyrklhczyydm;
	private String	kdqqy_qchjywid;
	private String	kdqqy_qcdqbm;
	private String	kdqqy_fksj;
	private String	kdqqy_fkzt;
	private String	kdqqy_fknr;
	private String	kdqqy_czyxm;
	private String	kdqqy_czydw;
	private String	kdqqy_qyzbh;
	private String	kdqqy_lscxfldm;	
	private Long	splsid;
	private Long	dzid;
	private String	czjg;
	private String	czyj;
	private Long	czrid;
	private String	czsj;
	private String	dzmc;
	private String	xydz_dzmc;
	public Long getSpywid() {
		return spywid;
	}
	public void setSpywid(Long spywid) {
		this.spywid = spywid;
	}
	public String getSqrgmsfhm() {
		return sqrgmsfhm;
	}
	public void setSqrgmsfhm(String sqrgmsfhm) {
		this.sqrgmsfhm = sqrgmsfhm;
	}
	public Long getSpmbid() {
		return spmbid;
	}
	public void setSpmbid(Long spmbid) {
		this.spmbid = spmbid;
	}
	public String getSqrxm() {
		return sqrxm;
	}
	public void setSqrxm(String sqrxm) {
		this.sqrxm = sqrxm;
	}
	public String getSqrzzssxq() {
		return sqrzzssxq;
	}
	public void setSqrzzssxq(String sqrzzssxq) {
		this.sqrzzssxq = sqrzzssxq;
	}
	public String getSplx() {
		return splx;
	}
	public void setSplx(String splx) {
		this.splx = splx;
	}
	public String getSlrq() {
		return slrq;
	}
	public void setSlrq(String slrq) {
		this.slrq = slrq;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public Long getRyid() {
		return ryid;
	}
	public void setRyid(Long ryid) {
		this.ryid = ryid;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getGmsfhm() {
		return gmsfhm;
	}
	public void setGmsfhm(String gmsfhm) {
		this.gmsfhm = gmsfhm;
	}
	public String getCsrq() {
		return csrq;
	}
	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}
	public String getMz() {
		return mz;
	}
	public void setMz(String mz) {
		this.mz = mz;
	}
	public String getJhny() {
		return jhny;
	}
	public void setJhny(String jhny) {
		this.jhny = jhny;
	}
	public String getYjrclx() {
		return yjrclx;
	}
	public void setYjrclx(String yjrclx) {
		this.yjrclx = yjrclx;
	}
	public String getYrdwjcyrysj() {
		return yrdwjcyrysj;
	}
	public void setYrdwjcyrysj(String yrdwjcyrysj) {
		this.yrdwjcyrysj = yrdwjcyrysj;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getByxx() {
		return byxx;
	}
	public void setByxx(String byxx) {
		this.byxx = byxx;
	}
	public String getWhcd() {
		return whcd;
	}
	public void setWhcd(String whcd) {
		this.whcd = whcd;
	}
	public String getBysj() {
		return bysj;
	}
	public void setBysj(String bysj) {
		this.bysj = bysj;
	}
	public String getZyzl() {
		return zyzl;
	}
	public void setZyzl(String zyzl) {
		this.zyzl = zyzl;
	}
	public String getByzsh() {
		return byzsh;
	}
	public void setByzsh(String byzsh) {
		this.byzsh = byzsh;
	}
	public String getZyjszc() {
		return zyjszc;
	}
	public void setZyjszc(String zyjszc) {
		this.zyjszc = zyjszc;
	}
	public String getJszsh() {
		return jszsh;
	}
	public void setJszsh(String jszsh) {
		this.jszsh = jszsh;
	}
	public String getJsfzjg() {
		return jsfzjg;
	}
	public void setJsfzjg(String jsfzjg) {
		this.jsfzjg = jsfzjg;
	}
	public String getFmzlmc() {
		return fmzlmc;
	}
	public void setFmzlmc(String fmzlmc) {
		this.fmzlmc = fmzlmc;
	}
	public String getFmzlh() {
		return fmzlh;
	}
	public void setFmzlh(String fmzlh) {
		this.fmzlh = fmzlh;
	}
	public String getZlfzjg() {
		return zlfzjg;
	}
	public void setZlfzjg(String zlfzjg) {
		this.zlfzjg = zlfzjg;
	}
	public String getHb() {
		return hb;
	}
	public void setHb(String hb) {
		this.hb = hb;
	}
	public String getQrhhb() {
		return qrhhb;
	}
	public void setQrhhb(String qrhhb) {
		this.qrhhb = qrhhb;
	}
	public String getZzssxq() {
		return zzssxq;
	}
	public void setZzssxq(String zzssxq) {
		this.zzssxq = zzssxq;
	}
	public String getZzxz() {
		return zzxz;
	}
	public void setZzxz(String zzxz) {
		this.zzxz = zzxz;
	}
	public String getHkszddjjg() {
		return hkszddjjg;
	}
	public void setHkszddjjg(String hkszddjjg) {
		this.hkszddjjg = hkszddjjg;
	}
	public String getQrdqx() {
		return qrdqx;
	}
	public void setQrdqx(String qrdqx) {
		this.qrdqx = qrdqx;
	}
	public String getQrdpcs() {
		return qrdpcs;
	}
	public void setQrdpcs(String qrdpcs) {
		this.qrdpcs = qrdpcs;
	}
	public String getQrdjwzrq() {
		return qrdjwzrq;
	}
	public void setQrdjwzrq(String qrdjwzrq) {
		this.qrdjwzrq = qrdjwzrq;
	}
	public String getQrdxzjd() {
		return qrdxzjd;
	}
	public void setQrdxzjd(String qrdxzjd) {
		this.qrdxzjd = qrdxzjd;
	}
	public String getQrdjwh() {
		return qrdjwh;
	}
	public void setQrdjwh(String qrdjwh) {
		this.qrdjwh = qrdjwh;
	}
	public String getQrdjlx() {
		return qrdjlx;
	}
	public void setQrdjlx(String qrdjlx) {
		this.qrdjlx = qrdjlx;
	}
	public String getQrdmlph() {
		return qrdmlph;
	}
	public void setQrdmlph(String qrdmlph) {
		this.qrdmlph = qrdmlph;
	}
	public String getQrdz() {
		return qrdz;
	}
	public void setQrdz(String qrdz) {
		this.qrdz = qrdz;
	}
	public String getQrdhkdjjg() {
		return qrdhkdjjg;
	}
	public void setQrdhkdjjg(String qrdhkdjjg) {
		this.qrdhkdjjg = qrdhkdjjg;
	}
	public Long getQrdhhid() {
		return qrdhhid;
	}
	public void setQrdhhid(Long qrdhhid) {
		this.qrdhhid = qrdhhid;
	}
	public String getQrdhlx() {
		return qrdhlx;
	}
	public void setQrdhlx(String qrdhlx) {
		this.qrdhlx = qrdhlx;
	}
	public String getRlhbz() {
		return rlhbz;
	}
	public void setRlhbz(String rlhbz) {
		this.rlhbz = rlhbz;
	}
	public String getSqqrly() {
		return sqqrly;
	}
	public void setSqqrly(String sqqrly) {
		this.sqqrly = sqqrly;
	}
	public String getSqrzzxz() {
		return sqrzzxz;
	}
	public void setSqrzzxz(String sqrzzxz) {
		this.sqrzzxz = sqrzzxz;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
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
	public String getSqrhkdjjg() {
		return sqrhkdjjg;
	}
	public void setSqrhkdjjg(String sqrhkdjjg) {
		this.sqrhkdjjg = sqrhkdjjg;
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
	public String getYsqrgx() {
		return ysqrgx;
	}
	public void setYsqrgx(String ysqrgx) {
		this.ysqrgx = ysqrgx;
	}
	public String getSpsm() {
		return spsm;
	}
	public void setSpsm(String spsm) {
		this.spsm = spsm;
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
	public String getNyzyrklhczyydm() {
		return nyzyrklhczyydm;
	}
	public void setNyzyrklhczyydm(String nyzyrklhczyydm) {
		this.nyzyrklhczyydm = nyzyrklhczyydm;
	}
	public String getKdqqy_qchjywid() {
		return kdqqy_qchjywid;
	}
	public void setKdqqy_qchjywid(String kdqqy_qchjywid) {
		this.kdqqy_qchjywid = kdqqy_qchjywid;
	}
	public String getKdqqy_qcdqbm() {
		return kdqqy_qcdqbm;
	}
	public void setKdqqy_qcdqbm(String kdqqy_qcdqbm) {
		this.kdqqy_qcdqbm = kdqqy_qcdqbm;
	}
	public String getKdqqy_fksj() {
		return kdqqy_fksj;
	}
	public void setKdqqy_fksj(String kdqqy_fksj) {
		this.kdqqy_fksj = kdqqy_fksj;
	}
	public String getKdqqy_fkzt() {
		return kdqqy_fkzt;
	}
	public void setKdqqy_fkzt(String kdqqy_fkzt) {
		this.kdqqy_fkzt = kdqqy_fkzt;
	}
	public String getKdqqy_fknr() {
		return kdqqy_fknr;
	}
	public void setKdqqy_fknr(String kdqqy_fknr) {
		this.kdqqy_fknr = kdqqy_fknr;
	}
	public String getKdqqy_czyxm() {
		return kdqqy_czyxm;
	}
	public void setKdqqy_czyxm(String kdqqy_czyxm) {
		this.kdqqy_czyxm = kdqqy_czyxm;
	}
	public String getKdqqy_czydw() {
		return kdqqy_czydw;
	}
	public void setKdqqy_czydw(String kdqqy_czydw) {
		this.kdqqy_czydw = kdqqy_czydw;
	}
	public String getKdqqy_qyzbh() {
		return kdqqy_qyzbh;
	}
	public void setKdqqy_qyzbh(String kdqqy_qyzbh) {
		this.kdqqy_qyzbh = kdqqy_qyzbh;
	}
	public String getKdqqy_lscxfldm() {
		return kdqqy_lscxfldm;
	}
	public void setKdqqy_lscxfldm(String kdqqy_lscxfldm) {
		this.kdqqy_lscxfldm = kdqqy_lscxfldm;
	}
	public Long getSplsid() {
		return splsid;
	}
	public void setSplsid(Long splsid) {
		this.splsid = splsid;
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
