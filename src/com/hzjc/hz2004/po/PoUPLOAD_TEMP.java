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
@Table(name="UPLOAD_TEMP" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoUPLOAD_TEMP implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	uploadzpid;//临时照片表主键
	private byte[]	zp;//照片流
	private Long	czrid;//操作人
	private String	czsj;//操作时间
	@Transient
	private String zpstr;
	public String getZpstr() {
		return zpstr;
	}

	public void setZpstr(String zpstr) {
		this.zpstr = zpstr;
	}
	public PoUPLOAD_TEMP(){}
	
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

	public byte[] getZp() {
		return zp;
	}

	public void setZp(byte[] zp) {
		this.zp = zp;
	}

	public Long getUploadzpid() {
		return uploadzpid;
	}

	public void setUploadzpid(Long uploadzpid) {
		this.uploadzpid = uploadzpid;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}