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
*业务报表模板信息表
*/
@Entity
@Table(name="XT_YWBBMBXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_YWBBMBXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*业务报表ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	ywbbid;
	private String	ywbblb;
	private String	bbmc;
	private byte[]	bbmb;
	private String	jlsj;
	private Long	jlrid;
	private String	xgsj;
	private Long	xgrid;
	//private String	dqbm;

	public PoXT_YWBBMBXXB(){}

	public Long getYwbbid(){
		return this.ywbbid;
	}

	public void setYwbbid(Long ywbbid){
		this.ywbbid=ywbbid;
	}

	public String getYwbblb(){
		return this.ywbblb;
	}

	public void setYwbblb(String ywbblb){
		this.ywbblb=ywbblb;
	}

	public String getBbmc(){
		return this.bbmc;
	}

	public void setBbmc(String bbmc){
		this.bbmc=bbmc;
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

	public Long getJlrid(){
		return this.jlrid;
	}

	public void setJlrid(Long jlrid){
		this.jlrid=jlrid;
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

	/*public String getDqbm(){
		return this.dqbm;
	}

	public void setDqbm(String dqbm){
		this.dqbm=dqbm;
	}*/

	public static long getSerialversionuid() {			return serialVersionUID;	}}