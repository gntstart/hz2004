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
*业务接口对照表
*/
@Entity
@Table(name="WW_YWJKDZB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoWW_YWJKDZB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*对照ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	dzid;
	private String	bdywbh;
	private String	bdywmc;
	private Long	csid;
	private String	fhlx;
	private String	qybz;
	private String	cjsj;

	public PoWW_YWJKDZB(){}

	public Long getDzid(){
		return this.dzid;
	}

	public void setDzid(Long dzid){
		this.dzid=dzid;
	}

	public String getBdywbh(){
		return this.bdywbh;
	}

	public void setBdywbh(String bdywbh){
		this.bdywbh=bdywbh;
	}

	public String getBdywmc(){
		return this.bdywmc;
	}

	public void setBdywmc(String bdywmc){
		this.bdywmc=bdywmc;
	}

	public Long getCsid(){
		return this.csid;
	}

	public void setCsid(Long csid){
		this.csid=csid;
	}

	public String getFhlx(){
		return this.fhlx;
	}

	public void setFhlx(String fhlx){
		this.fhlx=fhlx;
	}

	public String getQybz(){
		return this.qybz;
	}

	public void setQybz(String qybz){
		this.qybz=qybz;
	}

	public String getCjsj(){
		return this.cjsj;
	}

	public void setCjsj(String cjsj){
		this.cjsj=cjsj;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}