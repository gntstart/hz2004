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
*通告信息表
*/
@Entity
@Table(name="XX_TG" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXX_TG implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*通告时间
	*/
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	private String	tgsj;
	private String	tgbt;
	private String	tgnr;

	public PoXX_TG(){}

	public String getTgsj(){
		return this.tgsj;
	}

	public void setTgsj(String tgsj){
		this.tgsj=tgsj;
	}

	public String getTgbt(){
		return this.tgbt;
	}

	public void setTgbt(String tgbt){
		this.tgbt=tgbt;
	}

	public String getTgnr(){
		return this.tgnr;
	}

	public void setTgnr(String tgnr){
		this.tgnr=tgnr;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}