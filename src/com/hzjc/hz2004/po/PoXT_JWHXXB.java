package com.hzjc.hz2004.po;

import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
*居委会信息表
*/
@Entity
@Table(name="XT_JWHXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_JWHXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*代码
	*/
	@GenericGenerator(name = "generator", strategy = "assigned") 
	//@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	private String	dm;
	private String	mc;
	private String	zwpy;
	private String	wbpy;
	private String	dwdm;
	private String	xzjddm;
	private String	bz;
	private String	qybz;
	private String	bdlx;
	private String	bdsj;
	private String	cxfldm;
	private String	cxflmc;
	private String	tjdm;
	private String	tjmc;
	private String	xdm;
	private String	dzys;
	private String	dzysmc;
	private Long hzzpid;//会章照片id
	private Long qmzpid;//签名照片id
	private Long czrid;//操作人id
	@Transient
	private String dwdm_name;		//冗余单位代码名称
	@Transient
	private String xzjddm_name;	//冗余乡镇街道名称
	@Transient
	private String dwdm_bz;			//冗余单位名称
	@Transient
	private String ssxq;			//冗余省市县区
	@Transient
	private String	qhdm2;
	@Transient
	private String	qhdm2_mc;
	@Transient
	private List<PoXT_JLXXXB> jlxList;
	public PoXT_JWHXXB(){}

	public List<PoXT_JLXXXB> getJlxList() {
		return jlxList;
	}

	public void setJlxList(List<PoXT_JLXXXB> jlxList) {
		this.jlxList = jlxList;
	}

	public String getDwdm_name() {
		return dwdm_name;
	}

	public void setDwdm_name(String dwdm_name) {
		this.dwdm_name = dwdm_name;
	}

	public String getXzjddm_name() {
		return xzjddm_name;
	}

	public void setXzjddm_name(String xzjddm_name) {
		this.xzjddm_name = xzjddm_name;
	}

	public String getDwdm_bz() {
		return dwdm_bz;
	}

	public void setDwdm_bz(String dwdm_bz) {
		this.dwdm_bz = dwdm_bz;
	}

	public String getSsxq() {
		return ssxq;
	}

	public void setSsxq(String ssxq) {
		this.ssxq = ssxq;
	}

	public String getDm(){
		return this.dm;
	}

	public void setDm(String dm){
		this.dm=dm;
	}

	public String getMc(){
		return this.mc;
	}

	public void setMc(String mc){
		this.mc=mc;
	}

	public String getZwpy(){
		return this.zwpy;
	}

	public void setZwpy(String zwpy){
		this.zwpy=zwpy;
	}

	public String getWbpy(){
		return this.wbpy;
	}

	public void setWbpy(String wbpy){
		this.wbpy=wbpy;
	}

	public String getDwdm(){
		return this.dwdm;
	}

	public void setDwdm(String dwdm){
		this.dwdm=dwdm;
	}

	public String getXzjddm(){
		return this.xzjddm;
	}

	public void setXzjddm(String xzjddm){
		this.xzjddm=xzjddm;
	}

	public String getBz(){
		return this.bz;
	}

	public void setBz(String bz){
		this.bz=bz;
	}

	public String getQybz(){
		return this.qybz;
	}

	public void setQybz(String qybz){
		this.qybz=qybz;
	}

	public String getBdlx(){
		return this.bdlx;
	}

	public void setBdlx(String bdlx){
		this.bdlx=bdlx;
	}

	public String getBdsj(){
		return this.bdsj;
	}

	public void setBdsj(String bdsj){
		this.bdsj=bdsj;
	}

	public String getCxfldm(){
		return this.cxfldm;
	}

	public void setCxfldm(String cxfldm){
		this.cxfldm=cxfldm;
	}

	public String getCxflmc(){
		return this.cxflmc;
	}

	public void setCxflmc(String cxflmc){
		this.cxflmc=cxflmc;
	}

	public String getTjdm(){
		return this.tjdm;
	}

	public void setTjdm(String tjdm){
		this.tjdm=tjdm;
	}

	public String getTjmc(){
		return this.tjmc;
	}

	public void setTjmc(String tjmc){
		this.tjmc=tjmc;
	}

	public String getXdm(){
		return this.xdm;
	}

	public void setXdm(String xdm){
		this.xdm=xdm;
	}

	public String getDzys(){
		return this.dzys;
	}

	public void setDzys(String dzys){
		this.dzys=dzys;
	}

	public String getDzysmc(){
		return this.dzysmc;
	}

	public void setDzysmc(String dzysmc){
		this.dzysmc=dzysmc;
	}

	public Long getHzzpid() {
		return hzzpid;
	}

	public void setHzzpid(Long hzzpid) {
		this.hzzpid = hzzpid;
	}

	public Long getQmzpid() {
		return qmzpid;
	}

	public void setQmzpid(Long qmzpid) {
		this.qmzpid = qmzpid;
	}

	public Long getCzrid() {
		return czrid;
	}

	public void setCzrid(Long czrid) {
		this.czrid = czrid;
	}

	public String getQhdm2() {
		return qhdm2;
	}

	public void setQhdm2(String qhdm2) {
		this.qhdm2 = qhdm2;
	}

	public String getQhdm2_mc() {
		return qhdm2_mc;
	}

	public void setQhdm2_mc(String qhdm2_mc) {
		this.qhdm2_mc = qhdm2_mc;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}