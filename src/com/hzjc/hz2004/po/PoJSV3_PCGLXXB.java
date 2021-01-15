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
@Table(name="JSV3_PCGLXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoJSV3_PCGLXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*批次ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	pcid;
	private String	pch;
	private String	pclx;
	private String	dsdm;
	private String	dsmc;
	private String	ywqssj;
	private String	ywjzsj;
	private String	pcjd;
	private String	pczt;
	private String	djsj;
	private String	jssj;
	private Long	ywzs;
	private Long	bwzs;
	private String	pcqbzjd;

	public PoJSV3_PCGLXXB(){}

	public Long getPcid(){
		return this.pcid;
	}

	public void setPcid(Long pcid){
		this.pcid=pcid;
	}

	public String getPch(){
		return this.pch;
	}

	public void setPch(String pch){
		this.pch=pch;
	}

	public String getPclx(){
		return this.pclx;
	}

	public void setPclx(String pclx){
		this.pclx=pclx;
	}

	public String getDsdm(){
		return this.dsdm;
	}

	public void setDsdm(String dsdm){
		this.dsdm=dsdm;
	}

	public String getDsmc(){
		return this.dsmc;
	}

	public void setDsmc(String dsmc){
		this.dsmc=dsmc;
	}

	public String getYwqssj(){
		return this.ywqssj;
	}

	public void setYwqssj(String ywqssj){
		this.ywqssj=ywqssj;
	}

	public String getYwjzsj(){
		return this.ywjzsj;
	}

	public void setYwjzsj(String ywjzsj){
		this.ywjzsj=ywjzsj;
	}

	public String getPcjd(){
		return this.pcjd;
	}

	public void setPcjd(String pcjd){
		this.pcjd=pcjd;
	}

	public String getPczt(){
		return this.pczt;
	}

	public void setPczt(String pczt){
		this.pczt=pczt;
	}

	public String getDjsj(){
		return this.djsj;
	}

	public void setDjsj(String djsj){
		this.djsj=djsj;
	}

	public String getJssj(){
		return this.jssj;
	}

	public void setJssj(String jssj){
		this.jssj=jssj;
	}

	public Long getYwzs(){
		return this.ywzs;
	}

	public void setYwzs(Long ywzs){
		this.ywzs=ywzs;
	}

	public Long getBwzs(){
		return this.bwzs;
	}

	public void setBwzs(Long bwzs){
		this.bwzs=bwzs;
	}

	public String getPcqbzjd(){
		return this.pcqbzjd;
	}

	public void setPcqbzjd(String pcqbzjd){
		this.pcqbzjd=pcqbzjd;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}