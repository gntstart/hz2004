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
*身份证异地受理确认
*/
@Entity
@Table(name="YW_SFZYDSLQR" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoYW_SFZYDSLQR implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*异地确认序列号
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	czrkydqrid;
	private String	czrkslh;
	private String	czrkgmsfhm;
	private String	czrkxm;
	private String	czrksldw;
	private String	czrksldwdm;
	private String	czrkhkdjjg;
	private String	czrkhkdjjgdm;
	private String	czrkslqrxx;
	private Long	czrkzfbz;

	public PoYW_SFZYDSLQR(){}

	public Long getCzrkydqrid(){
		return this.czrkydqrid;
	}

	public void setCzrkydqrid(Long czrkydqrid){
		this.czrkydqrid=czrkydqrid;
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

	public String getCzrkhkdjjg(){
		return this.czrkhkdjjg;
	}

	public void setCzrkhkdjjg(String czrkhkdjjg){
		this.czrkhkdjjg=czrkhkdjjg;
	}

	public String getCzrkhkdjjgdm(){
		return this.czrkhkdjjgdm;
	}

	public void setCzrkhkdjjgdm(String czrkhkdjjgdm){
		this.czrkhkdjjgdm=czrkhkdjjgdm;
	}

	public String getCzrkslqrxx(){
		return this.czrkslqrxx;
	}

	public void setCzrkslqrxx(String czrkslqrxx){
		this.czrkslqrxx=czrkslqrxx;
	}

	public Long getCzrkzfbz(){
		return this.czrkzfbz;
	}

	public void setCzrkzfbz(Long czrkzfbz){
		this.czrkzfbz=czrkzfbz;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}