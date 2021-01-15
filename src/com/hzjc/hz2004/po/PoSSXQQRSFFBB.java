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
*省市县区迁入身份分布表
*/
@Entity
@Table(name="SSXQQRSFFBB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoSSXQQRSFFBB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private long sffbbId;//id
	private String	qhdm;//区划代码
	private String	qcdsdm;//迁出地省代码
	private String  qcdsmc;//迁出地省名称
	private Integer  count;//迁出人数
	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public long getSffbbId() {
		return sffbbId;
	}

	public void setSffbbId(long sffbbId) {
		this.sffbbId = sffbbId;
	}

	public String getQhdm() {
		return qhdm;
	}

	public void setQhdm(String qhdm) {
		this.qhdm = qhdm;
	}

	public String getQcdsdm() {
		return qcdsdm;
	}

	public void setQcdsdm(String qcdsdm) {
		this.qcdsdm = qcdsdm;
	}

	public String getQcdsmc() {
		return qcdsmc;
	}

	public void setQcdsmc(String qcdsmc) {
		this.qcdsmc = qcdsmc;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}