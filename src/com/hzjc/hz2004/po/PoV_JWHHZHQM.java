package com.hzjc.hz2004.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
*户关联关系表
*/
@Entity
@Table(name="JWHHZHQM" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoV_JWHHZHQM implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*关联ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	private String	dwdm;//单位代码
	private String	jlx;//街路巷
	@Id
	private String	jcwh;//居委会
	private String	jlxhlx;//
	private Long	hzzpid;//会章照片id
	private Long	qmzpid;//签名照片id
	public String getDwdm() {
		return dwdm;
	}

	public void setDwdm(String dwdm) {
		this.dwdm = dwdm;
	}

	public String getJlx() {
		return jlx;
	}

	public void setJlx(String jlx) {
		this.jlx = jlx;
	}

	public String getJcwh() {
		return jcwh;
	}

	public void setJcwh(String jcwh) {
		this.jcwh = jcwh;
	}

	public String getJlxhlx() {
		return jlxhlx;
	}

	public void setJlxhlx(String jlxhlx) {
		this.jlxhlx = jlxhlx;
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

	public PoV_JWHHZHQM() {
		super();
	}

	public PoV_JWHHZHQM(String dwdm, String jlx, String jcwh, String jlxhlx,Long hzzpid,Long qmzpid) {
		super();
		this.dwdm = dwdm;
		this.jlx = jlx;
		this.jcwh = jcwh;
		this.jlxhlx = jlxhlx;
		this.hzzpid = hzzpid;
		this.qmzpid = qmzpid;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}