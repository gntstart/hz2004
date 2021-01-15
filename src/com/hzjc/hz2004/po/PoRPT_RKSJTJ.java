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
*人口数据统计表
*/
@Entity
@Table(name="RPT_RKSJTJ" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoRPT_RKSJTJ implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*地市代码
	*/
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	private String	dsdm;
	private String	f0;
	private Long	f1;
	private Long	f2;
	private Long	f3;
	private Double	f4;

	public PoRPT_RKSJTJ(){}

	public String getDsdm(){
		return this.dsdm;
	}

	public void setDsdm(String dsdm){
		this.dsdm=dsdm;
	}

	public String getF0(){
		return this.f0;
	}

	public void setF0(String f0){
		this.f0=f0;
	}

	public Long getF1(){
		return this.f1;
	}

	public void setF1(Long f1){
		this.f1=f1;
	}

	public Long getF2(){
		return this.f2;
	}

	public void setF2(Long f2){
		this.f2=f2;
	}

	public Long getF3(){
		return this.f3;
	}

	public void setF3(Long f3){
		this.f3=f3;
	}

	public Double getF4(){
		return this.f4;
	}

	public void setF4(Double f4){
		this.f4=f4;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}