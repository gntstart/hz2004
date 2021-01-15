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
*常住人口信息数据报送情况统计表
*/
@Entity
@Table(name="RPT_SJBSTJB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoRPT_SJBSTJB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*区县代码
	*/
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	private String	f0;
	private String	f1;
	private Long	f2;
	private Long	f3;
	private Long	f4;
	private Long	f5;
	private Long	f6;
	private Long	f7;
	private Double	f8;
	private String	f9;
	private String	f10;
	private String	f11;
	private String	f12;
	private String	f13;
	private String	f14;
	private String	f15;

	public PoRPT_SJBSTJB(){}

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

	public Long getF4(){
		return this.f4;
	}

	public void setF4(Long f4){
		this.f4=f4;
	}

	public Long getF5(){
		return this.f5;
	}

	public void setF5(Long f5){
		this.f5=f5;
	}

	public Long getF6(){
		return this.f6;
	}

	public void setF6(Long f6){
		this.f6=f6;
	}

	public Long getF7(){
		return this.f7;
	}

	public void setF7(Long f7){
		this.f7=f7;
	}

	public Double getF8(){
		return this.f8;
	}

	public void setF8(Double f8){
		this.f8=f8;
	}

	public String getF9(){
		return this.f9;
	}

	public void setF9(String f9){
		this.f9=f9;
	}

	public String getF10(){
		return this.f10;
	}

	public void setF10(String f10){
		this.f10=f10;
	}

	public String getF11(){
		return this.f11;
	}

	public void setF11(String f11){
		this.f11=f11;
	}

	public String getF12(){
		return this.f12;
	}

	public void setF12(String f12){
		this.f12=f12;
	}

	public String getF13(){
		return this.f13;
	}

	public void setF13(String f13){
		this.f13=f13;
	}

	public String getF14(){
		return this.f14;
	}

	public void setF14(String f14){
		this.f14=f14;
	}

	public String getF15(){
		return this.f15;
	}

	public void setF15(String f15){
		this.f15=f15;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}