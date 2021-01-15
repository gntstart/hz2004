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
import java.sql.Timestamp;

/**
*常住人口打印历史表
*/
@Entity
@Table(name="HJXX_CZRKJBXXB_DYLS" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHJXX_CZRKJBXXB_DYLS implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*KEY
	*/
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	private String	lsid;
	private Long	hhnbid;
	private Timestamp	dysj;
	private String	dyyh;

	public PoHJXX_CZRKJBXXB_DYLS(){}

	public String getLsid(){
		return this.lsid;
	}

	public void setLsid(String lsid){
		this.lsid=lsid;
	}

	public Long getHhnbid(){
		return this.hhnbid;
	}

	public void setHhnbid(Long hhnbid){
		this.hhnbid=hhnbid;
	}

	public Timestamp getDysj(){
		return this.dysj;
	}

	public void setDysj(Timestamp dysj){
		this.dysj=dysj;
	}

	public String getDyyh(){
		return this.dyyh;
	}

	public void setDyyh(String dyyh){
		this.dyyh=dyyh;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}