package com.hzjc.hz2004.po;

import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
*户籍补录审批申请表
*/
@Entity
@Table(name="HJSP_HJBLSPSQB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHJSP_HJBLSPSQB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*审批业务ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	spywid;
	private String	gmsfhm;
	private String	xm;
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
	private String	jhryxm;
	private String	jhrygmsfhm;
	private String	jhryjhgx;
	private String	jhrexm;
	private String	jhregmsfhm;
	private String	jhrejhgx;
	private String	fqgmsfhm;
	private String	fqxm;
	private String	mqgmsfhm;
	private String	mqxm;
	private String	pogmsfhm;
	private String	poxm;
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
	private String	spsm;
	private Long	djrid;
	private String	djsj;
	private Long	xydzid;
	private Long	spmbid;
	private String	spjg;
	private String	lsbz;
	private Long	rynbid;
	private Long	hjywid;
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
	private String	sbsj;
	private String	sbryxm;
	private String	sbrgmsfhm;
	public PoHJSP_HJBLSPSQB(){}

	public String getSbsj() {
		return sbsj;
	}

	public void setSbsj(String sbsj) {
		this.sbsj = sbsj;
	}

	public String getSbryxm() {
		return sbryxm;
	}

	public void setSbryxm(String sbryxm) {
		this.sbryxm = sbryxm;
	}

	public String getSbrgmsfhm() {
		return sbrgmsfhm;
	}

	public void setSbrgmsfhm(String sbrgmsfhm) {
		this.sbrgmsfhm = sbrgmsfhm;
	}

	public Long getSpywid(){
		return this.spywid;
	}

	public void setSpywid(Long spywid){
		this.spywid=spywid;
	}

	public String getGmsfhm(){
		return this.gmsfhm;
	}

	public void setGmsfhm(String gmsfhm){
		this.gmsfhm=gmsfhm;
	}

	public String getXm(){
		return this.xm;
	}

	public void setXm(String xm){
		this.xm=xm;
	}

	public String getCym(){
		return this.cym;
	}

	public void setCym(String cym){
		this.cym=cym;
	}

	public String getXb(){
		return this.xb;
	}

	public void setXb(String xb){
		this.xb=xb;
	}

	public String getMz(){
		return this.mz;
	}

	public void setMz(String mz){
		this.mz=mz;
	}

	public String getCsrq(){
		return this.csrq;
	}

	public void setCsrq(String csrq){
		this.csrq=csrq;
	}

	public String getCssj(){
		return this.cssj;
	}

	public void setCssj(String cssj){
		this.cssj=cssj;
	}

	public String getCsdgjdq(){
		return this.csdgjdq;
	}

	public void setCsdgjdq(String csdgjdq){
		this.csdgjdq=csdgjdq;
	}

	public String getCsdssxq(){
		return this.csdssxq;
	}

	public void setCsdssxq(String csdssxq){
		this.csdssxq=csdssxq;
	}

	public String getCsdxz(){
		return this.csdxz;
	}

	public void setCsdxz(String csdxz){
		this.csdxz=csdxz;
	}

	public String getJggjdq(){
		return this.jggjdq;
	}

	public void setJggjdq(String jggjdq){
		this.jggjdq=jggjdq;
	}

	public String getJgssxq(){
		return this.jgssxq;
	}

	public void setJgssxq(String jgssxq){
		this.jgssxq=jgssxq;
	}

	public String getJhryxm(){
		return this.jhryxm;
	}

	public void setJhryxm(String jhryxm){
		this.jhryxm=jhryxm;
	}

	public String getJhrygmsfhm(){
		return this.jhrygmsfhm;
	}

	public void setJhrygmsfhm(String jhrygmsfhm){
		this.jhrygmsfhm=jhrygmsfhm;
	}

	public String getJhryjhgx(){
		return this.jhryjhgx;
	}

	public void setJhryjhgx(String jhryjhgx){
		this.jhryjhgx=jhryjhgx;
	}

	public String getJhrexm(){
		return this.jhrexm;
	}

	public void setJhrexm(String jhrexm){
		this.jhrexm=jhrexm;
	}

	public String getJhregmsfhm(){
		return this.jhregmsfhm;
	}

	public void setJhregmsfhm(String jhregmsfhm){
		this.jhregmsfhm=jhregmsfhm;
	}

	public String getJhrejhgx(){
		return this.jhrejhgx;
	}

	public void setJhrejhgx(String jhrejhgx){
		this.jhrejhgx=jhrejhgx;
	}

	public String getFqgmsfhm(){
		return this.fqgmsfhm;
	}

	public void setFqgmsfhm(String fqgmsfhm){
		this.fqgmsfhm=fqgmsfhm;
	}

	public String getFqxm(){
		return this.fqxm;
	}

	public void setFqxm(String fqxm){
		this.fqxm=fqxm;
	}

	public String getMqgmsfhm(){
		return this.mqgmsfhm;
	}

	public void setMqgmsfhm(String mqgmsfhm){
		this.mqgmsfhm=mqgmsfhm;
	}

	public String getMqxm(){
		return this.mqxm;
	}

	public void setMqxm(String mqxm){
		this.mqxm=mqxm;
	}

	public String getPogmsfhm(){
		return this.pogmsfhm;
	}

	public void setPogmsfhm(String pogmsfhm){
		this.pogmsfhm=pogmsfhm;
	}

	public String getPoxm(){
		return this.poxm;
	}

	public void setPoxm(String poxm){
		this.poxm=poxm;
	}

	public String getZjxy(){
		return this.zjxy;
	}

	public void setZjxy(String zjxy){
		this.zjxy=zjxy;
	}

	public String getWhcd(){
		return this.whcd;
	}

	public void setWhcd(String whcd){
		this.whcd=whcd;
	}

	public String getHyzk(){
		return this.hyzk;
	}

	public void setHyzk(String hyzk){
		this.hyzk=hyzk;
	}

	public String getByzk(){
		return this.byzk;
	}

	public void setByzk(String byzk){
		this.byzk=byzk;
	}

	public String getSg(){
		return this.sg;
	}

	public void setSg(String sg){
		this.sg=sg;
	}

	public String getXx(){
		return this.xx;
	}

	public void setXx(String xx){
		this.xx=xx;
	}

	public String getZy(){
		return this.zy;
	}

	public void setZy(String zy){
		this.zy=zy;
	}

	public String getZylb(){
		return this.zylb;
	}

	public void setZylb(String zylb){
		this.zylb=zylb;
	}

	public String getFwcs(){
		return this.fwcs;
	}

	public void setFwcs(String fwcs){
		this.fwcs=fwcs;
	}

	public String getXxjb(){
		return this.xxjb;
	}

	public void setXxjb(String xxjb){
		this.xxjb=xxjb;
	}

	public String getHb(){
		return this.hb;
	}

	public void setHb(String hb){
		this.hb=hb;
	}

	public String getCszmbh(){
		return this.cszmbh;
	}

	public void setCszmbh(String cszmbh){
		this.cszmbh=cszmbh;
	}

	public String getSsssxq(){
		return this.ssssxq;
	}

	public void setSsssxq(String ssssxq){
		this.ssssxq=ssssxq;
	}

	public String getSspcs(){
		return this.sspcs;
	}

	public void setSspcs(String sspcs){
		this.sspcs=sspcs;
	}

	public String getSsjwh(){
		return this.ssjwh;
	}

	public void setSsjwh(String ssjwh){
		this.ssjwh=ssjwh;
	}

	public String getSsjlx(){
		return this.ssjlx;
	}

	public void setSsjlx(String ssjlx){
		this.ssjlx=ssjlx;
	}

	public String getSsxzjd(){
		return this.ssxzjd;
	}

	public void setSsxzjd(String ssxzjd){
		this.ssxzjd=ssxzjd;
	}

	public String getSsjwzrq(){
		return this.ssjwzrq;
	}

	public void setSsjwzrq(String ssjwzrq){
		this.ssjwzrq=ssjwzrq;
	}

	public String getSsmlph(){
		return this.ssmlph;
	}

	public void setSsmlph(String ssmlph){
		this.ssmlph=ssmlph;
	}

	public String getSsxz(){
		return this.ssxz;
	}

	public void setSsxz(String ssxz){
		this.ssxz=ssxz;
	}

	public Long getSshhid(){
		return this.sshhid;
	}

	public void setSshhid(Long sshhid){
		this.sshhid=sshhid;
	}

	public String getSshh(){
		return this.sshh;
	}

	public void setSshh(String sshh){
		this.sshh=sshh;
	}

	public String getSshlx(){
		return this.sshlx;
	}

	public void setSshlx(String sshlx){
		this.sshlx=sshlx;
	}

	public String getYhzgx(){
		return this.yhzgx;
	}

	public void setYhzgx(String yhzgx){
		this.yhzgx=yhzgx;
	}

	public String getRlhbz(){
		return this.rlhbz;
	}

	public void setRlhbz(String rlhbz){
		this.rlhbz=rlhbz;
	}

	public String getBlyy(){
		return this.blyy;
	}

	public void setBlyy(String blyy){
		this.blyy=blyy;
	}

	public String getSpsm(){
		return this.spsm;
	}

	public void setSpsm(String spsm){
		this.spsm=spsm;
	}

	public Long getDjrid(){
		return this.djrid;
	}

	public void setDjrid(Long djrid){
		this.djrid=djrid;
	}

	public String getDjsj(){
		return this.djsj;
	}

	public void setDjsj(String djsj){
		this.djsj=djsj;
	}

	public Long getXydzid(){
		return this.xydzid;
	}

	public void setXydzid(Long xydzid){
		this.xydzid=xydzid;
	}

	public Long getSpmbid(){
		return this.spmbid;
	}

	public void setSpmbid(Long spmbid){
		this.spmbid=spmbid;
	}

	public String getSpjg(){
		return this.spjg;
	}

	public void setSpjg(String spjg){
		this.spjg=spjg;
	}

	public String getLsbz(){
		return this.lsbz;
	}

	public void setLsbz(String lsbz){
		this.lsbz=lsbz;
	}

	public Long getRynbid(){
		return this.rynbid;
	}

	public void setRynbid(Long rynbid){
		this.rynbid=rynbid;
	}

	public Long getHjywid(){
		return this.hjywid;
	}

	public void setHjywid(Long hjywid){
		this.hjywid=hjywid;
	}

	public Long getYwxl(){
		return this.ywxl;
	}

	public void setYwxl(Long ywxl){
		this.ywxl=ywxl;
	}

	public String getXmx(){
		return this.xmx;
	}

	public void setXmx(String xmx){
		this.xmx=xmx;
	}

	public String getXmm(){
		return this.xmm;
	}

	public void setXmm(String xmm){
		this.xmm=xmm;
	}

	public String getJgxz(){
		return this.jgxz;
	}

	public void setJgxz(String jgxz){
		this.jgxz=jgxz;
	}

	public String getJhryzjzl(){
		return this.jhryzjzl;
	}

	public void setJhryzjzl(String jhryzjzl){
		this.jhryzjzl=jhryzjzl;
	}

	public String getJhryzjhm(){
		return this.jhryzjhm;
	}

	public void setJhryzjhm(String jhryzjhm){
		this.jhryzjhm=jhryzjhm;
	}

	public String getJhrywwx(){
		return this.jhrywwx;
	}

	public void setJhrywwx(String jhrywwx){
		this.jhrywwx=jhrywwx;
	}

	public String getJhrywwm(){
		return this.jhrywwm;
	}

	public void setJhrywwm(String jhrywwm){
		this.jhrywwm=jhrywwm;
	}

	public String getJhrylxdh(){
		return this.jhrylxdh;
	}

	public void setJhrylxdh(String jhrylxdh){
		this.jhrylxdh=jhrylxdh;
	}

	public String getJhrezjzl(){
		return this.jhrezjzl;
	}

	public void setJhrezjzl(String jhrezjzl){
		this.jhrezjzl=jhrezjzl;
	}

	public String getJhrezjhm(){
		return this.jhrezjhm;
	}

	public void setJhrezjhm(String jhrezjhm){
		this.jhrezjhm=jhrezjhm;
	}

	public String getJhrewwx(){
		return this.jhrewwx;
	}

	public void setJhrewwx(String jhrewwx){
		this.jhrewwx=jhrewwx;
	}

	public String getJhrewwm(){
		return this.jhrewwm;
	}

	public void setJhrewwm(String jhrewwm){
		this.jhrewwm=jhrewwm;
	}

	public String getJhrelxdh(){
		return this.jhrelxdh;
	}

	public void setJhrelxdh(String jhrelxdh){
		this.jhrelxdh=jhrelxdh;
	}

	public String getFqzjzl(){
		return this.fqzjzl;
	}

	public void setFqzjzl(String fqzjzl){
		this.fqzjzl=fqzjzl;
	}

	public String getFqzjhm(){
		return this.fqzjhm;
	}

	public void setFqzjhm(String fqzjhm){
		this.fqzjhm=fqzjhm;
	}

	public String getFqwwx(){
		return this.fqwwx;
	}

	public void setFqwwx(String fqwwx){
		this.fqwwx=fqwwx;
	}

	public String getFqwwm(){
		return this.fqwwm;
	}

	public void setFqwwm(String fqwwm){
		this.fqwwm=fqwwm;
	}

	public String getMqzjzl(){
		return this.mqzjzl;
	}

	public void setMqzjzl(String mqzjzl){
		this.mqzjzl=mqzjzl;
	}

	public String getMqzjhm(){
		return this.mqzjhm;
	}

	public void setMqzjhm(String mqzjhm){
		this.mqzjhm=mqzjhm;
	}

	public String getMqwwx(){
		return this.mqwwx;
	}

	public void setMqwwx(String mqwwx){
		this.mqwwx=mqwwx;
	}

	public String getMqwwm(){
		return this.mqwwm;
	}

	public void setMqwwm(String mqwwm){
		this.mqwwm=mqwwm;
	}

	public String getPozjzl(){
		return this.pozjzl;
	}

	public void setPozjzl(String pozjzl){
		this.pozjzl=pozjzl;
	}

	public String getPozjhm(){
		return this.pozjhm;
	}

	public void setPozjhm(String pozjhm){
		this.pozjhm=pozjhm;
	}

	public String getPowwx(){
		return this.powwx;
	}

	public void setPowwx(String powwx){
		this.powwx=powwx;
	}

	public String getPowwm(){
		return this.powwm;
	}

	public void setPowwm(String powwm){
		this.powwm=powwm;
	}

	public String getQyldyy(){
		return this.qyldyy;
	}

	public void setQyldyy(String qyldyy){
		this.qyldyy=qyldyy;
	}

	public String getSbrjtgx(){
		return this.sbrjtgx;
	}

	public void setSbrjtgx(String sbrjtgx){
		this.sbrjtgx=sbrjtgx;
	}

	public String getBzdz(){
		return this.bzdz;
	}

	public void setBzdz(String bzdz){
		this.bzdz=bzdz;
	}

	public String getBzdzid(){
		return this.bzdzid;
	}

	public void setBzdzid(String bzdzid){
		this.bzdzid=bzdzid;
	}

	public String getBzdzx(){
		return this.bzdzx;
	}

	public void setBzdzx(String bzdzx){
		this.bzdzx=bzdzx;
	}

	public String getBzdzy(){
		return this.bzdzy;
	}

	public void setBzdzy(String bzdzy){
		this.bzdzy=bzdzy;
	}

	public String getBzdzst(){
		return this.bzdzst;
	}

	public void setBzdzst(String bzdzst){
		this.bzdzst=bzdzst;
	}

	public String getCxfldm(){
		return this.cxfldm;
	}

	public void setCxfldm(String cxfldm){
		this.cxfldm=cxfldm;
	}

	public String getJthzl(){
		return this.jthzl;
	}

	public void setJthzl(String jthzl){
		this.jthzl=jthzl;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}