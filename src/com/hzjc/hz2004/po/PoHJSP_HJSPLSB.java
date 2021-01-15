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
*户籍审批流水表
*/
@Entity
@Table(name="HJSP_HJSPLSB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHJSP_HJSPLSB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*审批流水ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	splsid;
	private Long	spywid;
	private String	splx;
	private Long	dzid;
	private String	czjg;
	private String	czyj;
	private Long	czrid;
	private String	czsj;

	public PoHJSP_HJSPLSB(){}

	public Long getSplsid(){
		return this.splsid;
	}

	public void setSplsid(Long splsid){
		this.splsid=splsid;
	}

	public Long getSpywid(){
		return this.spywid;
	}

	public void setSpywid(Long spywid){
		this.spywid=spywid;
	}

	public String getSplx(){
		return this.splx;
	}

	public void setSplx(String splx){
		this.splx=splx;
	}

	public Long getDzid(){
		return this.dzid;
	}

	public void setDzid(Long dzid){
		this.dzid=dzid;
	}

	public String getCzjg(){
		return this.czjg;
	}

	public void setCzjg(String czjg){
		this.czjg=czjg;
	}

	public String getCzyj(){
		return this.czyj;
	}

	public void setCzyj(String czyj){
		this.czyj=czyj;
	}

	public Long getCzrid(){
		return this.czrid;
	}

	public void setCzrid(Long czrid){
		this.czrid=czrid;
	}

	public String getCzsj(){
		return this.czsj;
	}

	public void setCzsj(String czsj){
		this.czsj=czsj;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}