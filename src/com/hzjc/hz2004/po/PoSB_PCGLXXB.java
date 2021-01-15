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
@Table(name="SB_PCGLXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoSB_PCGLXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*批次ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	pcid;
	private String	pch;
	private String	dsdm;
	private String	dsmc;
	private Long	djrid;
	private String	djrxm;
	private String	djsj;
	private String	ywqssj;
	private String	ywjzsj;
	private String	pczt;
	private String	dbjssj;
	private String	dzbscsj;

	public PoSB_PCGLXXB(){}

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

	public Long getDjrid(){
		return this.djrid;
	}

	public void setDjrid(Long djrid){
		this.djrid=djrid;
	}

	public String getDjrxm(){
		return this.djrxm;
	}

	public void setDjrxm(String djrxm){
		this.djrxm=djrxm;
	}

	public String getDjsj(){
		return this.djsj;
	}

	public void setDjsj(String djsj){
		this.djsj=djsj;
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

	public String getPczt(){
		return this.pczt;
	}

	public void setPczt(String pczt){
		this.pczt=pczt;
	}

	public String getDbjssj(){
		return this.dbjssj;
	}

	public void setDbjssj(String dbjssj){
		this.dbjssj=dbjssj;
	}

	public String getDzbscsj(){
		return this.dzbscsj;
	}

	public void setDzbscsj(String dzbscsj){
		this.dzbscsj=dzbscsj;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}