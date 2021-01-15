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

@Entity
@Table(name="T_RK_INFO" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoT_RK_INFO implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	id;
	private String	ssxq;
	private String	gmsfhm;
	private String	xm;
	private String	ckbdjg;
	private String	zzbdjg;

	public PoT_RK_INFO(){}

	public Long getId(){
		return this.id;
	}

	public void setId(Long id){
		this.id=id;
	}

	public String getSsxq(){
		return this.ssxq;
	}

	public void setSsxq(String ssxq){
		this.ssxq=ssxq;
	}

	public String getGmsfhm(){
		return this.gmsfhm;
	}

	public void setGmsfhm(String gmsfhm){
		this.gmsfhm=gmsfhm;
	}

	public String getXm(){
		return this.xm;
	}

	public void setXm(String xm){
		this.xm=xm;
	}

	public String getCkbdjg(){
		return this.ckbdjg;
	}

	public void setCkbdjg(String ckbdjg){
		this.ckbdjg=ckbdjg;
	}

	public String getZzbdjg(){
		return this.zzbdjg;
	}

	public void setZzbdjg(String zzbdjg){
		this.zzbdjg=zzbdjg;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}