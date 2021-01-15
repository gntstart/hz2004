package com.hzjc.hz2004.po;

import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
*照片临时表
*/
@Entity
@Table(name="HJXX_ZPLSB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHJXX_ZPLSB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	zplsid;
	private String	slh;
	private String	gmsfhm;
	private Long	zpid;
	private Long	ryid;
	private Long	rynbid;
	private byte[]	zp;
	private String	bcsj;
	private Long	czrid;
	private String	jlbz;
	private Long	bdrid;
	private String	bdrdwdm;
	private String	bdsj;
	@Transient
	private String zpstr;
	public String getZpstr() {
		return zpstr;
	}

	public void setZpstr(String zpstr) {
		this.zpstr = zpstr;
	}
	public PoHJXX_ZPLSB(){}

	public Long getZplsid(){
		return this.zplsid;
	}

	public void setZplsid(Long zplsid){
		this.zplsid=zplsid;
	}

	public String getSlh(){
		return this.slh;
	}

	public void setSlh(String slh){
		this.slh=slh;
	}

	public String getGmsfhm(){
		return this.gmsfhm;
	}

	public void setGmsfhm(String gmsfhm){
		this.gmsfhm=gmsfhm;
	}

	public Long getZpid(){
		return this.zpid;
	}

	public void setZpid(Long zpid){
		this.zpid=zpid;
	}

	public Long getRyid(){
		return this.ryid;
	}

	public void setRyid(Long ryid){
		this.ryid=ryid;
	}

	public Long getRynbid(){
		return this.rynbid;
	}

	public void setRynbid(Long rynbid){
		this.rynbid=rynbid;
	}

	public byte[] getZp(){
		return this.zp;
	}

	public void setZp(byte[] zp){
		this.zp=zp;
	}

	public String getBcsj(){
		return this.bcsj;
	}

	public void setBcsj(String bcsj){
		this.bcsj=bcsj;
	}

	public Long getCzrid(){
		return this.czrid;
	}

	public void setCzrid(Long czrid){
		this.czrid=czrid;
	}

	public String getJlbz(){
		return this.jlbz;
	}

	public void setJlbz(String jlbz){
		this.jlbz=jlbz;
	}

	public Long getBdrid(){
		return this.bdrid;
	}

	public void setBdrid(Long bdrid){
		this.bdrid=bdrid;
	}

	public String getBdrdwdm(){
		return this.bdrdwdm;
	}

	public void setBdrdwdm(String bdrdwdm){
		this.bdrdwdm=bdrdwdm;
	}

	public String getBdsj(){
		return this.bdsj;
	}

	public void setBdsj(String bdsj){
		this.bdsj=bdsj;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}