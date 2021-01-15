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
*包文数据接发统计表
*/
@Entity
@Table(name="RPT_BWSJJFTJB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoRPT_BWSJJFTJB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*操作日期
	*/
	@Id
	private String	f0;
	/*
	*节点编号
	*/
	@Id
	private String	f1;
	/*
	*包文类型
	*/
	@Id
	private String	f2;
	private Long	f3;
	private Long	f4;
	private String	f5;

	public PoRPT_BWSJJFTJB(){}

	public String getF0(){
		return this.f0;
	}

	public void setF0(String f0){
		this.f0=f0;
	}

	public String getF1(){
		return this.f1;
	}

	public void setF1(String f1){
		this.f1=f1;
	}

	public String getF2(){
		return this.f2;
	}

	public void setF2(String f2){
		this.f2=f2;
	}

	public Long getF3(){
		return this.f3;
	}

	public void setF3(Long f3){
		this.f3=f3;
	}

	public Long getF4(){
		return this.f4;
	}

	public void setF4(Long f4){
		this.f4=f4;
	}

	public String getF5(){
		return this.f5;
	}

	public void setF5(String f5){
		this.f5=f5;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}