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
*维护数据对帐表
*/
@Entity
@Table(name="RPT_WHSJDZ" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoRPT_WHSJDZ implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

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
	private String	f4;
	private String	f5;
	private String	f6;
	private String	f7;
	private Long	f8;

	public PoRPT_WHSJDZ(){}

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

	public String getF4(){
		return this.f4;
	}

	public void setF4(String f4){
		this.f4=f4;
	}

	public String getF5(){
		return this.f5;
	}

	public void setF5(String f5){
		this.f5=f5;
	}

	public String getF6(){
		return this.f6;
	}

	public void setF6(String f6){
		this.f6=f6;
	}

	public String getF7(){
		return this.f7;
	}

	public void setF7(String f7){
		this.f7=f7;
	}

	public Long getF8(){
		return this.f8;
	}

	public void setF8(Long f8){
		this.f8=f8;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}