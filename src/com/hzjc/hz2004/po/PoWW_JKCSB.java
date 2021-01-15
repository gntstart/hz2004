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
*外围接口参数表
*/
@Entity
@Table(name="WW_JKCSB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoWW_JKCSB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*参数ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	csid;
	private String	jklb;
	private String	jklbmc;
	private String	jklmc;
	private String	jkcs;
	private String	jglx;
	private String	cjsj;
	private String	qybz;
	private String	bz;

	public PoWW_JKCSB(){}

	public Long getCsid(){
		return this.csid;
	}

	public void setCsid(Long csid){
		this.csid=csid;
	}

	public String getJklb(){
		return this.jklb;
	}

	public void setJklb(String jklb){
		this.jklb=jklb;
	}

	public String getJklbmc(){
		return this.jklbmc;
	}

	public void setJklbmc(String jklbmc){
		this.jklbmc=jklbmc;
	}

	public String getJklmc(){
		return this.jklmc;
	}

	public void setJklmc(String jklmc){
		this.jklmc=jklmc;
	}

	public String getJkcs(){
		return this.jkcs;
	}

	public void setJkcs(String jkcs){
		this.jkcs=jkcs;
	}

	public String getJglx(){
		return this.jglx;
	}

	public void setJglx(String jglx){
		this.jglx=jglx;
	}

	public String getCjsj(){
		return this.cjsj;
	}

	public void setCjsj(String cjsj){
		this.cjsj=cjsj;
	}

	public String getQybz(){
		return this.qybz;
	}

	public void setQybz(String qybz){
		this.qybz=qybz;
	}

	public String getBz(){
		return this.bz;
	}

	public void setBz(String bz){
		this.bz=bz;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}