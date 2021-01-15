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
*户信息表
*/
@Entity
@Table(name="JTTDLSB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoJTTDLSB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*户号内部ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long    jttdlsbid;
	private Long	hhnbid;
	private Long	hhid;
	private String	hh;
	private Long	slrid;
	private String	sldw;
	private String	slsj;
	private String	bgq;
	private String	bgh;
	public PoJTTDLSB(){}

	public Long getHhnbid(){
		return this.hhnbid;
	}

	public void setHhnbid(Long hhnbid){
		this.hhnbid=hhnbid;
	}

	public Long getHhid(){
		return this.hhid;
	}

	public void setHhid(Long hhid){
		this.hhid=hhid;
	}

	public Long getJttdlsbid() {
		return jttdlsbid;
	}

	public void setJttdlsbid(Long jttdlsbid) {
		this.jttdlsbid = jttdlsbid;
	}

	public String getHh() {
		return hh;
	}

	public void setHh(String hh) {
		this.hh = hh;
	}

	public Long getSlrid() {
		return slrid;
	}

	public void setSlrid(Long slrid) {
		this.slrid = slrid;
	}

	public String getSldw() {
		return sldw;
	}

	public void setSldw(String sldw) {
		this.sldw = sldw;
	}

	public String getSlsj() {
		return slsj;
	}

	public void setSlsj(String slsj) {
		this.slsj = slsj;
	}

	public String getBgq() {
		return bgq;
	}

	public void setBgq(String bgq) {
		this.bgq = bgq;
	}

	public String getBgh() {
		return bgh;
	}

	public void setBgh(String bgh) {
		this.bgh = bgh;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}