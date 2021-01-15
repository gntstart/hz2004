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
@Table(name="HJXX_JWHZPLSB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHJXX_JWHZPLSB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	jwhzpid;//居委会代码
	private byte[]	zp;//照片
	private Long	czrid;//操作人
	private String	czsj;//操作时间
	private String  zpType;//照片类型1会章，2签名
	private String  jwhdm;//居委会代码
	@Transient
	private String zpstr;
	public String getZpstr() {
		return zpstr;
	}

	public void setZpstr(String zpstr) {
		this.zpstr = zpstr;
	}
	public PoHJXX_JWHZPLSB(){}
	
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

	public Long getJwhzpid() {
		return jwhzpid;
	}

	public void setJwhzpid(Long jwhzpid) {
		this.jwhzpid = jwhzpid;
	}

	public byte[] getZp() {
		return zp;
	}

	public void setZp(byte[] zp) {
		this.zp = zp;
	}

	public String getZpType() {
		return zpType;
	}

	public void setZpType(String zpType) {
		this.zpType = zpType;
	}

	public String getJwhdm() {
		return jwhdm;
	}

	public void setJwhdm(String jwhdm) {
		this.jwhdm = jwhdm;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}