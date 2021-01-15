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
*临时身份证受理历史表
*/
@Entity
@Table(name="LSSFZ_SLLSB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoLSSFZ_SLLSB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*临时受理历史ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	lssllsbid;
	private Long	lsslid;
	private Long	ryid;
	private String	bgqkh;
	private String	bghkh;
	private Long	czyid;
	private String	czsj;
	private String	czyip;

	public PoLSSFZ_SLLSB(){}

	public Long getLssllsbid(){
		return this.lssllsbid;
	}

	public void setLssllsbid(Long lssllsbid){
		this.lssllsbid=lssllsbid;
	}

	public Long getLsslid(){
		return this.lsslid;
	}

	public void setLsslid(Long lsslid){
		this.lsslid=lsslid;
	}

	public Long getRyid(){
		return this.ryid;
	}

	public void setRyid(Long ryid){
		this.ryid=ryid;
	}

	public String getBgqkh(){
		return this.bgqkh;
	}

	public void setBgqkh(String bgqkh){
		this.bgqkh=bgqkh;
	}

	public String getBghkh(){
		return this.bghkh;
	}

	public void setBghkh(String bghkh){
		this.bghkh=bghkh;
	}

	public Long getCzyid(){
		return this.czyid;
	}

	public void setCzyid(Long czyid){
		this.czyid=czyid;
	}

	public String getCzsj(){
		return this.czsj;
	}

	public void setCzsj(String czsj){
		this.czsj=czsj;
	}

	public String getCzyip(){
		return this.czyip;
	}

	public void setCzyip(String czyip){
		this.czyip=czyip;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}