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
*投递信息表
*/
@Entity
@Table(name="ZJYW_TDXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoZJYW_TDXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*投递ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	tdid;
	private Long	rynbid;
	private Long	nbslid;
	private String	sjrxm;
	private String	sjrlxdh;
	private String	sjryb;
	private String	sjrtxdz;
	private Long	zjywid;
	private String	cxbz;
	private String	cxsj;
	private Long	cxrid;
	private Long	cxzjywid;

	public PoZJYW_TDXXB(){}

	public Long getTdid(){
		return this.tdid;
	}

	public void setTdid(Long tdid){
		this.tdid=tdid;
	}

	public Long getRynbid(){
		return this.rynbid;
	}

	public void setRynbid(Long rynbid){
		this.rynbid=rynbid;
	}

	public Long getNbslid(){
		return this.nbslid;
	}

	public void setNbslid(Long nbslid){
		this.nbslid=nbslid;
	}

	public String getSjrxm(){
		return this.sjrxm;
	}

	public void setSjrxm(String sjrxm){
		this.sjrxm=sjrxm;
	}

	public String getSjrlxdh(){
		return this.sjrlxdh;
	}

	public void setSjrlxdh(String sjrlxdh){
		this.sjrlxdh=sjrlxdh;
	}

	public String getSjryb(){
		return this.sjryb;
	}

	public void setSjryb(String sjryb){
		this.sjryb=sjryb;
	}

	public String getSjrtxdz(){
		return this.sjrtxdz;
	}

	public void setSjrtxdz(String sjrtxdz){
		this.sjrtxdz=sjrtxdz;
	}

	public Long getZjywid(){
		return this.zjywid;
	}

	public void setZjywid(Long zjywid){
		this.zjywid=zjywid;
	}

	public String getCxbz(){
		return this.cxbz;
	}

	public void setCxbz(String cxbz){
		this.cxbz=cxbz;
	}

	public String getCxsj(){
		return this.cxsj;
	}

	public void setCxsj(String cxsj){
		this.cxsj=cxsj;
	}

	public Long getCxrid(){
		return this.cxrid;
	}

	public void setCxrid(Long cxrid){
		this.cxrid=cxrid;
	}

	public Long getCxzjywid(){
		return this.cxzjywid;
	}

	public void setCxzjywid(Long cxzjywid){
		this.cxzjywid=cxzjywid;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}