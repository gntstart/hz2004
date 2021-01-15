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
*常住人口基本信息表
*/
@Entity
@Table(name="T_DSJJ_WP_CSYXZMXX" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoT_DSJJ_WP_CSYXZMXX implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*人员内部ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private String	zj;//主键
	private String	etxm;//儿童姓名
	private String	etxb;//儿童性别
	private String	csrq;//出生日期
	private String	lrsj;//录入时间
	private String	mqxm;//母亲姓名
	private String	mqsfzh;//母亲身份证号
	private String	fqxm;//父亲姓名
	private String	qfrq;//签发日期
	private String	fqsfzh;//父亲身份证号
	private String	cszbh;//出生证编号
	private String	etldate;
	private String	createdate;
	
	public PoT_DSJJ_WP_CSYXZMXX(){}

	public String getZj() {
		return zj;
	}

	public void setZj(String zj) {
		this.zj = zj;
	}

	public String getEtxm() {
		return etxm;
	}

	public void setEtxm(String etxm) {
		this.etxm = etxm;
	}

	public String getEtxb() {
		return etxb;
	}

	public void setEtxb(String etxb) {
		this.etxb = etxb;
	}

	public String getCsrq() {
		return csrq;
	}

	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}

	public String getLrsj() {
		return lrsj;
	}

	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
	}

	public String getMqxm() {
		return mqxm;
	}

	public void setMqxm(String mqxm) {
		this.mqxm = mqxm;
	}

	public String getMqsfzh() {
		return mqsfzh;
	}

	public void setMqsfzh(String mqsfzh) {
		this.mqsfzh = mqsfzh;
	}

	public String getFqxm() {
		return fqxm;
	}

	public void setFqxm(String fqxm) {
		this.fqxm = fqxm;
	}

	public String getQfrq() {
		return qfrq;
	}

	public void setQfrq(String qfrq) {
		this.qfrq = qfrq;
	}

	public String getFqsfzh() {
		return fqsfzh;
	}

	public void setFqsfzh(String fqsfzh) {
		this.fqsfzh = fqsfzh;
	}

	public String getCszbh() {
		return cszbh;
	}

	public void setCszbh(String cszbh) {
		this.cszbh = cszbh;
	}

	public String getEtldate() {
		return etldate;
	}

	public void setEtldate(String etldate) {
		this.etldate = etldate;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}