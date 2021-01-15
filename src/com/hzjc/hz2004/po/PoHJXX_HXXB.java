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
*户信息表
*/
@Entity
@Table(name="HJXX_HXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHJXX_HXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*户号内部ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	hhnbid;
	private Long	hhid;
	private Long	mlpnbid;
	private String	hh;
	private String	hlx;
	private String	jhlb;
	private String	chlb;
	private String	jhsj;
	private String	chsj;
	private Long	cjhjywid;
	private Long	cchjywid;
	private String	bdfw;
	private String	bdyy;
	private String	hhzt;
	private Long	lxdbid;
	private String	jlbz;
	private String	qysj;
	private String	jssj;
	private String	cxbz;
	private String	bzdz;
	private String	bzdzid;
	private String	bzdzx;
	private String	bzdzy;
	private String	bzdzst;
	private String	jttdbz;//add by zjm 20200414 集体土地标志 1 有效 0无效
	private Long    jttdlsbid;//add by zjm 20200421 集体土地历史表的id，用来关联取出受理人
	public PoHJXX_HXXB(){}

	public Long getHhnbid(){
		return this.hhnbid;
	}

	public void setHhnbid(Long hhnbid){
		this.hhnbid=hhnbid;
	}

	public Long getHhid(){
		return this.hhid;
	}

	public void setHhid(Long hhid){
		this.hhid=hhid;
	}

	public Long getMlpnbid(){
		return this.mlpnbid;
	}

	public void setMlpnbid(Long mlpnbid){
		this.mlpnbid=mlpnbid;
	}

	public String getHh(){
		return this.hh;
	}

	public void setHh(String hh){
		this.hh=hh;
	}

	public String getHlx(){
		return this.hlx;
	}

	public void setHlx(String hlx){
		this.hlx=hlx;
	}

	public String getJhlb(){
		return this.jhlb;
	}

	public void setJhlb(String jhlb){
		this.jhlb=jhlb;
	}

	public String getChlb(){
		return this.chlb;
	}

	public void setChlb(String chlb){
		this.chlb=chlb;
	}

	public String getJhsj(){
		return this.jhsj;
	}

	public void setJhsj(String jhsj){
		this.jhsj=jhsj;
	}

	public String getChsj(){
		return this.chsj;
	}

	public void setChsj(String chsj){
		this.chsj=chsj;
	}

	public Long getCjhjywid(){
		return this.cjhjywid;
	}

	public void setCjhjywid(Long cjhjywid){
		this.cjhjywid=cjhjywid;
	}

	public Long getCchjywid(){
		return this.cchjywid;
	}

	public void setCchjywid(Long cchjywid){
		this.cchjywid=cchjywid;
	}

	public String getBdfw(){
		return this.bdfw;
	}

	public void setBdfw(String bdfw){
		this.bdfw=bdfw;
	}

	public String getBdyy(){
		return this.bdyy;
	}

	public void setBdyy(String bdyy){
		this.bdyy=bdyy;
	}

	public String getHhzt(){
		return this.hhzt;
	}

	public void setHhzt(String hhzt){
		this.hhzt=hhzt;
	}

	public Long getLxdbid(){
		return this.lxdbid;
	}

	public void setLxdbid(Long lxdbid){
		this.lxdbid=lxdbid;
	}

	public String getJlbz(){
		return this.jlbz;
	}

	public void setJlbz(String jlbz){
		this.jlbz=jlbz;
	}

	public String getQysj(){
		return this.qysj;
	}

	public void setQysj(String qysj){
		this.qysj=qysj;
	}

	public String getJssj(){
		return this.jssj;
	}

	public void setJssj(String jssj){
		this.jssj=jssj;
	}

	public String getCxbz(){
		return this.cxbz;
	}

	public void setCxbz(String cxbz){
		this.cxbz=cxbz;
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

	public String getJttdbz() {
		return jttdbz;
	}

	public void setJttdbz(String jttdbz) {
		this.jttdbz = jttdbz;
	}

	public Long getJttdlsbid() {
		return jttdlsbid;
	}

	public void setJttdlsbid(Long jttdlsbid) {
		this.jttdlsbid = jttdlsbid;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}