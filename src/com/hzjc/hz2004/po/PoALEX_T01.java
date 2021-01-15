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

@Entity
@Table(name="ALEX_T01" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoALEX_T01 implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	id;
	private Long	a;
	private Long	b;
	private Long	c;
	private String	name;

	public PoALEX_T01(){}

	public Long getId(){
		return this.id;
	}

	public void setId(Long id){
		this.id=id;
	}

	public Long getA(){
		return this.a;
	}

	public void setA(Long a){
		this.a=a;
	}

	public Long getB(){
		return this.b;
	}

	public void setB(Long b){
		this.b=b;
	}

	public Long getC(){
		return this.c;
	}

	public void setC(Long c){
		this.c=c;
	}

	public String getName(){
		return this.name;
	}

	public void setName(String name){
		this.name=name;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}