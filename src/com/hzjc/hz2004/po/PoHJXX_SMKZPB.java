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
*市民卡照片表
*/
@Entity
@Table(name="HJXX_SMKZPB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHJXX_SMKZPB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*照片流水ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	zplsid;
	private String	slh;
	private String	gmsfhm;
	private byte[]	zp;
	private String	bcsj;
	private Long	yhid;
	private String	ipdz;
	private String	yhdlm;
	private String	yhdw;
	private String	yhxm;

	public PoHJXX_SMKZPB(){}

	public Long getZplsid(){
		return this.zplsid;
	}

	public void setZplsid(Long zplsid){
		this.zplsid=zplsid;
	}

	public String getSlh(){
		return this.slh;
	}

	public void setSlh(String slh){
		this.slh=slh;
	}

	public String getGmsfhm(){
		return this.gmsfhm;
	}

	public void setGmsfhm(String gmsfhm){
		this.gmsfhm=gmsfhm;
	}

	public byte[] getZp(){
		return this.zp;
	}

	public void setZp(byte[] zp){
		this.zp=zp;
	}

	public String getBcsj(){
		return this.bcsj;
	}

	public void setBcsj(String bcsj){
		this.bcsj=bcsj;
	}

	public Long getYhid(){
		return this.yhid;
	}

	public void setYhid(Long yhid){
		this.yhid=yhid;
	}

	public String getIpdz(){
		return this.ipdz;
	}

	public void setIpdz(String ipdz){
		this.ipdz=ipdz;
	}

	public String getYhdlm(){
		return this.yhdlm;
	}

	public void setYhdlm(String yhdlm){
		this.yhdlm=yhdlm;
	}

	public String getYhdw(){
		return this.yhdw;
	}

	public void setYhdw(String yhdw){
		this.yhdw=yhdw;
	}

	public String getYhxm(){
		return this.yhxm;
	}

	public void setYhxm(String yhxm){
		this.yhxm=yhxm;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}