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
*单位信息表
*/
@Entity
@Table(name="XT_DWXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_DWXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*代码
	*/
	@GenericGenerator(name = "generator", strategy = "assigned") 
	//@GenericGenerator(name = "generator"/*, strategy = "uuid.hex"*/)
	@Id
	@GeneratedValue(generator = "generator")
	private String	dm;
	private String	mc;
	private String  dhhm;//单位表增加电话号码字段  20210220
	private String	zwpy;
	private String	wbpy;
	private String	dwjgdm;
	private String	qhdm;
	private String	dwjb;
	private String	bz;
	private String	dz;
	private String	qybz;
	private String	bdlx;
	private String	bdsj;
	private String	fjjgdm;
	private String	fjjgmc;
	private byte[]	zp;//照片
	public PoXT_DWXXB(){}

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

	public String getDwjgdm(){
		return this.dwjgdm;
	}

	public void setDwjgdm(String dwjgdm){
		this.dwjgdm=dwjgdm;
	}

	public String getQhdm(){
		return this.qhdm;
	}

	public void setQhdm(String qhdm){
		this.qhdm=qhdm;
	}

	public String getDwjb(){
		return this.dwjb;
	}

	public void setDwjb(String dwjb){
		this.dwjb=dwjb;
	}

	public String getDz() {
		return dz;
	}

	public void setDz(String dz) {
		this.dz = dz;
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

	public String getFjjgdm(){
		return this.fjjgdm;
	}

	public void setFjjgdm(String fjjgdm){
		this.fjjgdm=fjjgdm;
	}

	public String getFjjgmc(){
		return this.fjjgmc;
	}

	public void setFjjgmc(String fjjgmc){
		this.fjjgmc=fjjgmc;
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

	public static long getSerialversionuid() {			return serialVersionUID;	}}