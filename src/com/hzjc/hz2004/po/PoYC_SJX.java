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
*数据项异常
*/
@Entity
@Table(name="YC_SJX" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoYC_SJX implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*数据异常序列号
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	czrksjycid;
	private String	czrkssssxq;
	private String	czrkgmsfhm;
	private String	czrkxm;
	private String	czrkysjbwjm;
	private String	czrkjhsjyclx;
	private String	czrkjcdw;
	private String	czrkjcrq;
	private String	czrkxjgajgmc;
	private String	czrkxjgajgjgdm;
	private String	czrkpcsmc;
	private String	czrkpcsjgdm;
	private String	czrkxxtqsj;
	private String	czrkryid;
	private String	czrkcwms;
	private String	czrkcxcsbs;
	private Long	zfbz;

	public PoYC_SJX(){}

	public Long getCzrksjycid(){
		return this.czrksjycid;
	}

	public void setCzrksjycid(Long czrksjycid){
		this.czrksjycid=czrksjycid;
	}

	public String getCzrkssssxq(){
		return this.czrkssssxq;
	}

	public void setCzrkssssxq(String czrkssssxq){
		this.czrkssssxq=czrkssssxq;
	}

	public String getCzrkgmsfhm(){
		return this.czrkgmsfhm;
	}

	public void setCzrkgmsfhm(String czrkgmsfhm){
		this.czrkgmsfhm=czrkgmsfhm;
	}

	public String getCzrkxm(){
		return this.czrkxm;
	}

	public void setCzrkxm(String czrkxm){
		this.czrkxm=czrkxm;
	}

	public String getCzrkysjbwjm(){
		return this.czrkysjbwjm;
	}

	public void setCzrkysjbwjm(String czrkysjbwjm){
		this.czrkysjbwjm=czrkysjbwjm;
	}

	public String getCzrkjhsjyclx(){
		return this.czrkjhsjyclx;
	}

	public void setCzrkjhsjyclx(String czrkjhsjyclx){
		this.czrkjhsjyclx=czrkjhsjyclx;
	}

	public String getCzrkjcdw(){
		return this.czrkjcdw;
	}

	public void setCzrkjcdw(String czrkjcdw){
		this.czrkjcdw=czrkjcdw;
	}

	public String getCzrkjcrq(){
		return this.czrkjcrq;
	}

	public void setCzrkjcrq(String czrkjcrq){
		this.czrkjcrq=czrkjcrq;
	}

	public String getCzrkxjgajgmc(){
		return this.czrkxjgajgmc;
	}

	public void setCzrkxjgajgmc(String czrkxjgajgmc){
		this.czrkxjgajgmc=czrkxjgajgmc;
	}

	public String getCzrkxjgajgjgdm(){
		return this.czrkxjgajgjgdm;
	}

	public void setCzrkxjgajgjgdm(String czrkxjgajgjgdm){
		this.czrkxjgajgjgdm=czrkxjgajgjgdm;
	}

	public String getCzrkpcsmc(){
		return this.czrkpcsmc;
	}

	public void setCzrkpcsmc(String czrkpcsmc){
		this.czrkpcsmc=czrkpcsmc;
	}

	public String getCzrkpcsjgdm(){
		return this.czrkpcsjgdm;
	}

	public void setCzrkpcsjgdm(String czrkpcsjgdm){
		this.czrkpcsjgdm=czrkpcsjgdm;
	}

	public String getCzrkxxtqsj(){
		return this.czrkxxtqsj;
	}

	public void setCzrkxxtqsj(String czrkxxtqsj){
		this.czrkxxtqsj=czrkxxtqsj;
	}

	public String getCzrkryid(){
		return this.czrkryid;
	}

	public void setCzrkryid(String czrkryid){
		this.czrkryid=czrkryid;
	}

	public String getCzrkcwms(){
		return this.czrkcwms;
	}

	public void setCzrkcwms(String czrkcwms){
		this.czrkcwms=czrkcwms;
	}

	public String getCzrkcxcsbs(){
		return this.czrkcxcsbs;
	}

	public void setCzrkcxcsbs(String czrkcxcsbs){
		this.czrkcxcsbs=czrkcxcsbs;
	}

	public Long getZfbz(){
		return this.zfbz;
	}

	public void setZfbz(Long zfbz){
		this.zfbz=zfbz;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}