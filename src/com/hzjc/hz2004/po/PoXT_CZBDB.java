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
*操作变动表
*/
@Entity
@Table(name="XT_CZBDB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_CZBDB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*变动ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	bdid;
	private String	bdsj;
	private String	bdlx;
	private String	bdbm;
	private String	bdbzjm;
	private Long	bdbid;
	private String	scsj;
	private String	scbz;

	public PoXT_CZBDB(){}

	public Long getBdid(){
		return this.bdid;
	}

	public void setBdid(Long bdid){
		this.bdid=bdid;
	}

	public String getBdsj(){
		return this.bdsj;
	}

	public void setBdsj(String bdsj){
		this.bdsj=bdsj;
	}

	public String getBdlx(){
		return this.bdlx;
	}

	public void setBdlx(String bdlx){
		this.bdlx=bdlx;
	}

	public String getBdbm(){
		return this.bdbm;
	}

	public void setBdbm(String bdbm){
		this.bdbm=bdbm;
	}

	public String getBdbzjm(){
		return this.bdbzjm;
	}

	public void setBdbzjm(String bdbzjm){
		this.bdbzjm=bdbzjm;
	}

	public Long getBdbid(){
		return this.bdbid;
	}

	public void setBdbid(Long bdbid){
		this.bdbid=bdbid;
	}

	public String getScsj(){
		return this.scsj;
	}

	public void setScsj(String scsj){
		this.scsj=scsj;
	}

	public String getScbz(){
		return this.scbz;
	}

	public void setScbz(String scbz){
		this.scbz=scbz;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}