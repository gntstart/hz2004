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
*业务记录数、包文记录数对帐
*/
@Entity
@Table(name="SB_YWBWDZ" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoSB_YWBWDZ implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*业务类型
	*/
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	private String	ywlx;
	private Long	ywjls;
	private Long	bwjls;

	public PoSB_YWBWDZ(){}

	public String getYwlx(){
		return this.ywlx;
	}

	public void setYwlx(String ywlx){
		this.ywlx=ywlx;
	}

	public Long getYwjls(){
		return this.ywjls;
	}

	public void setYwjls(Long ywjls){
		this.ywjls=ywjls;
	}

	public Long getBwjls(){
		return this.bwjls;
	}

	public void setBwjls(Long bwjls){
		this.bwjls=bwjls;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}