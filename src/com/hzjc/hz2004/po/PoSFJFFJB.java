package com.hzjc.hz2004.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
*收费缴费附件表
*/
@Entity
@Table(name="SFJFFJB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoSFJFFJB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*人员内部ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long sfjffjbid;//Id
	private Long sfxxbid;//收费信息表主键
	private byte[]	sfqd;//照片
	private byte[]	jfqd;//照片
	private Long    czrid;//操作人
	private String  czsj;//打印时间
	private Long    gxrid;//更新人ID
	private String  gxsj;//更新时间	
	public PoSFJFFJB(){}
	
	public Long getSfjffjbid() {
		return sfjffjbid;
	}

	public void setSfjffjbid(Long sfjffjbid) {
		this.sfjffjbid = sfjffjbid;
	}

	public Long getSfxxbid() {
		return sfxxbid;
	}

	public void setSfxxbid(Long sfxxbid) {
		this.sfxxbid = sfxxbid;
	}
	public byte[] getSfqd() {
		return sfqd;
	}

	public void setSfqd(byte[] sfqd) {
		this.sfqd = sfqd;
	}

	public byte[] getJfqd() {
		return jfqd;
	}

	public void setJfqd(byte[] jfqd) {
		this.jfqd = jfqd;
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

	public Long getGxrid() {
		return gxrid;
	}

	public void setGxrid(Long gxrid) {
		this.gxrid = gxrid;
	}

	public String getGxsj() {
		return gxsj;
	}

	public void setGxsj(String gxsj) {
		this.gxsj = gxsj;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}