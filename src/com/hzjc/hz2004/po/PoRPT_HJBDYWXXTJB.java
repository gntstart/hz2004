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
*户籍变动业务信息统计表
*/
@Entity
@Table(name="RPT_HJBDYWXXTJB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoRPT_HJBDYWXXTJB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*单位代码
	*/
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	private String	code;
	private String	a1;
	private Long	a2;
	private Long	a3;
	private Long	a4;
	private Long	a5;
	private Long	a6;
	private Long	a7;
	private Long	a8;
	private Long	a9;
	private Long	a10;
	private Long	a11;
	private Long	a12;
	private Long	a13;
	private Long	a14;
	private Long	a15;
	private Long	a16;
	private Long	a17;
	private Long	a18;
	private Long	a19;
	private Long	a20;
	private Long	a21;
	private Long	a22;
	private Long	a23;
	private Long	a24;
	private Long	a25;
	private Long	a26;
	private Long	a27;
	private Long	a28;
	private Long	a29;
	private Long	a30;
	private Long	a31;
	private Long	a32;
	private Long	a33;
	private Long	a34;
	private Long	a35;
	private Long	a36;
	private Long	a37;
	private Long	a38;
	private Long	a39;
	private Long	a40;
	private Long	a41;
	private Long	a42;
	private Long	a43;
	private Long	a44;
	private Long	a45;
	private Long	a46;

	public PoRPT_HJBDYWXXTJB(){}

	public String getCode(){
		return this.code;
	}

	public void setCode(String code){
		this.code=code;
	}

	public String getA1(){
		return this.a1;
	}

	public void setA1(String a1){
		this.a1=a1;
	}

	public Long getA2(){
		return this.a2;
	}

	public void setA2(Long a2){
		this.a2=a2;
	}

	public Long getA3(){
		return this.a3;
	}

	public void setA3(Long a3){
		this.a3=a3;
	}

	public Long getA4(){
		return this.a4;
	}

	public void setA4(Long a4){
		this.a4=a4;
	}

	public Long getA5(){
		return this.a5;
	}

	public void setA5(Long a5){
		this.a5=a5;
	}

	public Long getA6(){
		return this.a6;
	}

	public void setA6(Long a6){
		this.a6=a6;
	}

	public Long getA7(){
		return this.a7;
	}

	public void setA7(Long a7){
		this.a7=a7;
	}

	public Long getA8(){
		return this.a8;
	}

	public void setA8(Long a8){
		this.a8=a8;
	}

	public Long getA9(){
		return this.a9;
	}

	public void setA9(Long a9){
		this.a9=a9;
	}

	public Long getA10(){
		return this.a10;
	}

	public void setA10(Long a10){
		this.a10=a10;
	}

	public Long getA11(){
		return this.a11;
	}

	public void setA11(Long a11){
		this.a11=a11;
	}

	public Long getA12(){
		return this.a12;
	}

	public void setA12(Long a12){
		this.a12=a12;
	}

	public Long getA13(){
		return this.a13;
	}

	public void setA13(Long a13){
		this.a13=a13;
	}

	public Long getA14(){
		return this.a14;
	}

	public void setA14(Long a14){
		this.a14=a14;
	}

	public Long getA15(){
		return this.a15;
	}

	public void setA15(Long a15){
		this.a15=a15;
	}

	public Long getA16(){
		return this.a16;
	}

	public void setA16(Long a16){
		this.a16=a16;
	}

	public Long getA17(){
		return this.a17;
	}

	public void setA17(Long a17){
		this.a17=a17;
	}

	public Long getA18(){
		return this.a18;
	}

	public void setA18(Long a18){
		this.a18=a18;
	}

	public Long getA19(){
		return this.a19;
	}

	public void setA19(Long a19){
		this.a19=a19;
	}

	public Long getA20(){
		return this.a20;
	}

	public void setA20(Long a20){
		this.a20=a20;
	}

	public Long getA21(){
		return this.a21;
	}

	public void setA21(Long a21){
		this.a21=a21;
	}

	public Long getA22(){
		return this.a22;
	}

	public void setA22(Long a22){
		this.a22=a22;
	}

	public Long getA23(){
		return this.a23;
	}

	public void setA23(Long a23){
		this.a23=a23;
	}

	public Long getA24(){
		return this.a24;
	}

	public void setA24(Long a24){
		this.a24=a24;
	}

	public Long getA25(){
		return this.a25;
	}

	public void setA25(Long a25){
		this.a25=a25;
	}

	public Long getA26(){
		return this.a26;
	}

	public void setA26(Long a26){
		this.a26=a26;
	}

	public Long getA27(){
		return this.a27;
	}

	public void setA27(Long a27){
		this.a27=a27;
	}

	public Long getA28(){
		return this.a28;
	}

	public void setA28(Long a28){
		this.a28=a28;
	}

	public Long getA29(){
		return this.a29;
	}

	public void setA29(Long a29){
		this.a29=a29;
	}

	public Long getA30(){
		return this.a30;
	}

	public void setA30(Long a30){
		this.a30=a30;
	}

	public Long getA31(){
		return this.a31;
	}

	public void setA31(Long a31){
		this.a31=a31;
	}

	public Long getA32(){
		return this.a32;
	}

	public void setA32(Long a32){
		this.a32=a32;
	}

	public Long getA33(){
		return this.a33;
	}

	public void setA33(Long a33){
		this.a33=a33;
	}

	public Long getA34(){
		return this.a34;
	}

	public void setA34(Long a34){
		this.a34=a34;
	}

	public Long getA35(){
		return this.a35;
	}

	public void setA35(Long a35){
		this.a35=a35;
	}

	public Long getA36(){
		return this.a36;
	}

	public void setA36(Long a36){
		this.a36=a36;
	}

	public Long getA37(){
		return this.a37;
	}

	public void setA37(Long a37){
		this.a37=a37;
	}

	public Long getA38(){
		return this.a38;
	}

	public void setA38(Long a38){
		this.a38=a38;
	}

	public Long getA39(){
		return this.a39;
	}

	public void setA39(Long a39){
		this.a39=a39;
	}

	public Long getA40(){
		return this.a40;
	}

	public void setA40(Long a40){
		this.a40=a40;
	}

	public Long getA41(){
		return this.a41;
	}

	public void setA41(Long a41){
		this.a41=a41;
	}

	public Long getA42(){
		return this.a42;
	}

	public void setA42(Long a42){
		this.a42=a42;
	}

	public Long getA43(){
		return this.a43;
	}

	public void setA43(Long a43){
		this.a43=a43;
	}

	public Long getA44(){
		return this.a44;
	}

	public void setA44(Long a44){
		this.a44=a44;
	}

	public Long getA45(){
		return this.a45;
	}

	public void setA45(Long a45){
		this.a45=a45;
	}

	public Long getA46(){
		return this.a46;
	}

	public void setA46(Long a46){
		this.a46=a46;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}