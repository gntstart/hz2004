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
*制式报表模板信息表
*/
@Entity
@Table(name="XT_ZSBBMBXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_ZSBBMBXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*制式报表模板ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	zsbbmbid;
	private String	zsbblb;
	private String	bbmbmc;
	private byte[]	bbmb;
	private String	jlsj;
	private Long	scrid;
	private String	xgsj;
	private Long	xgrid;

	public PoXT_ZSBBMBXXB(){}

	public Long getZsbbmbid(){
		return this.zsbbmbid;
	}

	public void setZsbbmbid(Long zsbbmbid){
		this.zsbbmbid=zsbbmbid;
	}

	public String getZsbblb(){
		return this.zsbblb;
	}

	public void setZsbblb(String zsbblb){
		this.zsbblb=zsbblb;
	}

	public String getBbmbmc(){
		return this.bbmbmc;
	}

	public void setBbmbmc(String bbmbmc){
		this.bbmbmc=bbmbmc;
	}

	public byte[] getBbmb(){
		return this.bbmb;
	}

	public void setBbmb(byte[] bbmb){
		this.bbmb=bbmb;
	}

	public String getJlsj(){
		return this.jlsj;
	}

	public void setJlsj(String jlsj){
		this.jlsj=jlsj;
	}

	public Long getScrid(){
		return this.scrid;
	}

	public void setScrid(Long scrid){
		this.scrid=scrid;
	}

	public String getXgsj(){
		return this.xgsj;
	}

	public void setXgsj(String xgsj){
		this.xgsj=xgsj;
	}

	public Long getXgrid(){
		return this.xgrid;
	}

	public void setXgrid(Long xgrid){
		this.xgrid=xgrid;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}