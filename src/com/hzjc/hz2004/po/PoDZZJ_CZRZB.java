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
*操作日志表
*/
@Entity
@Table(name="DZZJ_CZRZB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoDZZJ_CZRZB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*日志ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	rzid;
	private String	czsj;
	private Long	czyid;
	private String	czdz;
	private String	czip;
	private Long	bczyid;

	public PoDZZJ_CZRZB(){}

	public Long getRzid(){
		return this.rzid;
	}

	public void setRzid(Long rzid){
		this.rzid=rzid;
	}

	public String getCzsj(){
		return this.czsj;
	}

	public void setCzsj(String czsj){
		this.czsj=czsj;
	}

	public Long getCzyid(){
		return this.czyid;
	}

	public void setCzyid(Long czyid){
		this.czyid=czyid;
	}

	public String getCzdz(){
		return this.czdz;
	}

	public void setCzdz(String czdz){
		this.czdz=czdz;
	}

	public String getCzip(){
		return this.czip;
	}

	public void setCzip(String czip){
		this.czip=czip;
	}

	public Long getBczyid(){
		return this.bczyid;
	}

	public void setBczyid(Long bczyid){
		this.bczyid=bczyid;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}