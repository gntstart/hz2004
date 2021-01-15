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
*外围接口日志表
*/
@Entity
@Table(name="WW_RZB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoWW_RZB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*日志ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	rzid;
	private String	ywbz;
	private String	rznr;
	private String	ywjg;
	private String	czsj;
	private Long	czrid;
	private String	czrdw;
	private String	czrip;

	public PoWW_RZB(){}

	public Long getRzid(){
		return this.rzid;
	}

	public void setRzid(Long rzid){
		this.rzid=rzid;
	}

	public String getYwbz(){
		return this.ywbz;
	}

	public void setYwbz(String ywbz){
		this.ywbz=ywbz;
	}

	public String getRznr(){
		return this.rznr;
	}

	public void setRznr(String rznr){
		this.rznr=rznr;
	}

	public String getYwjg(){
		return this.ywjg;
	}

	public void setYwjg(String ywjg){
		this.ywjg=ywjg;
	}

	public String getCzsj(){
		return this.czsj;
	}

	public void setCzsj(String czsj){
		this.czsj=czsj;
	}

	public Long getCzrid(){
		return this.czrid;
	}

	public void setCzrid(Long czrid){
		this.czrid=czrid;
	}

	public String getCzrdw(){
		return this.czrdw;
	}

	public void setCzrdw(String czrdw){
		this.czrdw=czrdw;
	}

	public String getCzrip(){
		return this.czrip;
	}

	public void setCzrip(String czrip){
		this.czrip=czrip;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}