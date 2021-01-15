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
*消息信息表
*/
@Entity
@Table(name="JSSDLOGXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoJSSDLOGXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*人员内部ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	jssdlogxxbid;//解锁锁定日志表Id
	private Long    czyid;//操作人
	private String	czsj;//操作时间
	private String	jssdyy;//解锁锁定原因
	private String	ip;//IP
	private String	dz;//动作
	private Long	rynbid;//人员内部id
	private Long	ryid;//人员id
	private String	xm;//姓名
	private String	gmsfhm;//公民身份号码
	public PoJSSDLOGXXB(){}

	public Long getJssdlogxxbid() {
		return jssdlogxxbid;
	}


	public void setJssdlogxxbid(Long jssdlogxxbid) {
		this.jssdlogxxbid = jssdlogxxbid;
	}


	public Long getCzyid() {
		return czyid;
	}

	

	public void setCzyid(Long czyid) {
		this.czyid = czyid;
	}


	public String getCzsj() {
		return czsj;
	}


	public void setCzsj(String czsj) {
		this.czsj = czsj;
	}


	public String getJssdyy() {
		return jssdyy;
	}


	public void setJssdyy(String jssdyy) {
		this.jssdyy = jssdyy;
	}


	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}


	public String getDz() {
		return dz;
	}


	public void setDz(String dz) {
		this.dz = dz;
	}

	public Long getRynbid() {
		return rynbid;
	}

	public void setRynbid(Long rynbid) {
		this.rynbid = rynbid;
	}

	public Long getRyid() {
		return ryid;
	}

	public void setRyid(Long ryid) {
		this.ryid = ryid;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getGmsfhm() {
		return gmsfhm;
	}

	public void setGmsfhm(String gmsfhm) {
		this.gmsfhm = gmsfhm;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}