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
*信息变更流水表
*/
@Entity
@Table(name="XT_XXBGLSB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_XXBGLSB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*信息变更ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	xxbgid;
	private String	bgbm;
	private Long	bgid;
	private Long	yczrid;
	private String	bgrq;
	private Long	bgrid;
	private String	bgxm;
	private String	bgqnr;
	private String	bghnr;

	public PoXT_XXBGLSB(){}

	public Long getXxbgid(){
		return this.xxbgid;
	}

	public void setXxbgid(Long xxbgid){
		this.xxbgid=xxbgid;
	}

	public String getBgbm(){
		return this.bgbm;
	}

	public void setBgbm(String bgbm){
		this.bgbm=bgbm;
	}

	public Long getBgid(){
		return this.bgid;
	}

	public void setBgid(Long bgid){
		this.bgid=bgid;
	}

	public Long getYczrid(){
		return this.yczrid;
	}

	public void setYczrid(Long yczrid){
		this.yczrid=yczrid;
	}

	public String getBgrq(){
		return this.bgrq;
	}

	public void setBgrq(String bgrq){
		this.bgrq=bgrq;
	}

	public Long getBgrid(){
		return this.bgrid;
	}

	public void setBgrid(Long bgrid){
		this.bgrid=bgrid;
	}

	public String getBgxm(){
		return this.bgxm;
	}

	public void setBgxm(String bgxm){
		this.bgxm=bgxm;
	}

	public String getBgqnr(){
		return this.bgqnr;
	}

	public void setBgqnr(String bgqnr){
		this.bgqnr=bgqnr;
	}

	public String getBghnr(){
		return this.bghnr;
	}

	public void setBghnr(String bghnr){
		this.bghnr=bghnr;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}