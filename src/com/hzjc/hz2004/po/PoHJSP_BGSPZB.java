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
*变更审批子表
*/
@Entity
@Table(name="HJSP_BGSPZB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHJSP_BGSPZB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*变更子ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	bgzid;
	private Long	spywid;
	private String	bggzxm;
	private String	bgqnr;
	private String	bghnr;
	private String	bglb;
	private String	bgrq;

	public PoHJSP_BGSPZB(){}

	public Long getBgzid(){
		return this.bgzid;
	}

	public void setBgzid(Long bgzid){
		this.bgzid=bgzid;
	}

	public Long getSpywid(){
		return this.spywid;
	}

	public void setSpywid(Long spywid){
		this.spywid=spywid;
	}

	public String getBggzxm(){
		return this.bggzxm;
	}

	public void setBggzxm(String bggzxm){
		this.bggzxm=bggzxm;
	}

	public String getBgqnr(){
		return this.bgqnr;
	}

	public void setBgqnr(String bgqnr){
		this.bgqnr=bgqnr;
	}

	public String getBghnr(){
		return this.bghnr;
	}

	public void setBghnr(String bghnr){
		this.bghnr=bghnr;
	}

	public String getBglb(){
		return this.bglb;
	}

	public void setBglb(String bglb){
		this.bglb=bglb;
	}

	public String getBgrq(){
		return this.bgrq;
	}

	public void setBgrq(String bgrq){
		this.bgrq=bgrq;
	}

	public PoHJSP_BGSPZB(Long bgzid, Long spywid, String bggzxm, String bgqnr, String bghnr, String bglb, String bgrq) {
		super();
		this.bgzid = bgzid;
		this.spywid = spywid;
		this.bggzxm = bggzxm;
		this.bgqnr = bgqnr;
		this.bghnr = bghnr;
		this.bglb = bglb;
		this.bgrq = bgrq;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}