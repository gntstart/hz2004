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
*身份证异地受理
*/
@Entity
@Table(name="YW_SFZYDSL" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoYW_SFZYDSL implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*异地受理序列号
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	czrkydslid;
	private String	czrkslh;
	private String	czrkgmsfhm;
	private String	czrkxm;
	private String	czrkxb;
	private String	czrkmz;
	private String	czrkcsrq;
	private String	czrkzjqfjg;
	private String	czrkyxqxqsrq;
	private String	czrkyxqxjzrq;
	private byte[]	czrkxp;
	private String	czrkzzssxq;
	private String	czrkzzxz;
	private String	czrkslrq;
	private String	czrksldw;
	private String	czrksldwdm;
	private String	czrkslyy;
	private String	czrkslr;
	private String	czrkzzlx;
	private String	czrklzfs;
	private String	czrksjrxm;
	private String	czrksjrlxdh;
	private String	czrksjryb;
	private String	czrksjrtxdz;
	private String	czrksdrq;
	private String	czrkzfsj;
	private String	czrkzfjg;
	private String	czrkzfjgdm;

	public PoYW_SFZYDSL(){}

	public Long getCzrkydslid(){
		return this.czrkydslid;
	}

	public void setCzrkydslid(Long czrkydslid){
		this.czrkydslid=czrkydslid;
	}

	public String getCzrkslh(){
		return this.czrkslh;
	}

	public void setCzrkslh(String czrkslh){
		this.czrkslh=czrkslh;
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

	public String getCzrkxb(){
		return this.czrkxb;
	}

	public void setCzrkxb(String czrkxb){
		this.czrkxb=czrkxb;
	}

	public String getCzrkmz(){
		return this.czrkmz;
	}

	public void setCzrkmz(String czrkmz){
		this.czrkmz=czrkmz;
	}

	public String getCzrkcsrq(){
		return this.czrkcsrq;
	}

	public void setCzrkcsrq(String czrkcsrq){
		this.czrkcsrq=czrkcsrq;
	}

	public String getCzrkzjqfjg(){
		return this.czrkzjqfjg;
	}

	public void setCzrkzjqfjg(String czrkzjqfjg){
		this.czrkzjqfjg=czrkzjqfjg;
	}

	public String getCzrkyxqxqsrq(){
		return this.czrkyxqxqsrq;
	}

	public void setCzrkyxqxqsrq(String czrkyxqxqsrq){
		this.czrkyxqxqsrq=czrkyxqxqsrq;
	}

	public String getCzrkyxqxjzrq(){
		return this.czrkyxqxjzrq;
	}

	public void setCzrkyxqxjzrq(String czrkyxqxjzrq){
		this.czrkyxqxjzrq=czrkyxqxjzrq;
	}

	public byte[] getCzrkxp(){
		return this.czrkxp;
	}

	public void setCzrkxp(byte[] czrkxp){
		this.czrkxp=czrkxp;
	}

	public String getCzrkzzssxq(){
		return this.czrkzzssxq;
	}

	public void setCzrkzzssxq(String czrkzzssxq){
		this.czrkzzssxq=czrkzzssxq;
	}

	public String getCzrkzzxz(){
		return this.czrkzzxz;
	}

	public void setCzrkzzxz(String czrkzzxz){
		this.czrkzzxz=czrkzzxz;
	}

	public String getCzrkslrq(){
		return this.czrkslrq;
	}

	public void setCzrkslrq(String czrkslrq){
		this.czrkslrq=czrkslrq;
	}

	public String getCzrksldw(){
		return this.czrksldw;
	}

	public void setCzrksldw(String czrksldw){
		this.czrksldw=czrksldw;
	}

	public String getCzrksldwdm(){
		return this.czrksldwdm;
	}

	public void setCzrksldwdm(String czrksldwdm){
		this.czrksldwdm=czrksldwdm;
	}

	public String getCzrkslyy(){
		return this.czrkslyy;
	}

	public void setCzrkslyy(String czrkslyy){
		this.czrkslyy=czrkslyy;
	}

	public String getCzrkslr(){
		return this.czrkslr;
	}

	public void setCzrkslr(String czrkslr){
		this.czrkslr=czrkslr;
	}

	public String getCzrkzzlx(){
		return this.czrkzzlx;
	}

	public void setCzrkzzlx(String czrkzzlx){
		this.czrkzzlx=czrkzzlx;
	}

	public String getCzrklzfs(){
		return this.czrklzfs;
	}

	public void setCzrklzfs(String czrklzfs){
		this.czrklzfs=czrklzfs;
	}

	public String getCzrksjrxm(){
		return this.czrksjrxm;
	}

	public void setCzrksjrxm(String czrksjrxm){
		this.czrksjrxm=czrksjrxm;
	}

	public String getCzrksjrlxdh(){
		return this.czrksjrlxdh;
	}

	public void setCzrksjrlxdh(String czrksjrlxdh){
		this.czrksjrlxdh=czrksjrlxdh;
	}

	public String getCzrksjryb(){
		return this.czrksjryb;
	}

	public void setCzrksjryb(String czrksjryb){
		this.czrksjryb=czrksjryb;
	}

	public String getCzrksjrtxdz(){
		return this.czrksjrtxdz;
	}

	public void setCzrksjrtxdz(String czrksjrtxdz){
		this.czrksjrtxdz=czrksjrtxdz;
	}

	public String getCzrksdrq(){
		return this.czrksdrq;
	}

	public void setCzrksdrq(String czrksdrq){
		this.czrksdrq=czrksdrq;
	}

	public String getCzrkzfsj(){
		return this.czrkzfsj;
	}

	public void setCzrkzfsj(String czrkzfsj){
		this.czrkzfsj=czrkzfsj;
	}

	public String getCzrkzfjg(){
		return this.czrkzfjg;
	}

	public void setCzrkzfjg(String czrkzfjg){
		this.czrkzfjg=czrkzfjg;
	}

	public String getCzrkzfjgdm(){
		return this.czrkzfjgdm;
	}

	public void setCzrkzfjgdm(String czrkzfjgdm){
		this.czrkzfjgdm=czrkzfjgdm;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}