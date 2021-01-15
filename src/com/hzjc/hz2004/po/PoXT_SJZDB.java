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
*数据字典表
*/
@Entity
@Table(name="XT_SJZDB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_SJZDB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*字典ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	zdid;
	private String	zdmc;
	private String	zdhy;
	private String	zdlx;
	private String	zdcd;
	private String	zdmj;
	private String	bdlx;
	private String	bdsj;

	public PoXT_SJZDB(){}

	public Long getZdid(){
		return this.zdid;
	}

	public void setZdid(Long zdid){
		this.zdid=zdid;
	}

	public String getZdmc(){
		return this.zdmc;
	}

	public void setZdmc(String zdmc){
		this.zdmc=zdmc;
	}

	public String getZdhy(){
		return this.zdhy;
	}

	public void setZdhy(String zdhy){
		this.zdhy=zdhy;
	}

	public String getZdlx(){
		return this.zdlx;
	}

	public void setZdlx(String zdlx){
		this.zdlx=zdlx;
	}

	public String getZdcd(){
		return this.zdcd;
	}

	public void setZdcd(String zdcd){
		this.zdcd=zdcd;
	}

	public String getZdmj(){
		return this.zdmj;
	}

	public void setZdmj(String zdmj){
		this.zdmj=zdmj;
	}

	public String getBdlx(){
		return this.bdlx;
	}

	public void setBdlx(String bdlx){
		this.bdlx=bdlx;
	}

	public String getBdsj(){
		return this.bdsj;
	}

	public void setBdsj(String bdsj){
		this.bdsj=bdsj;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}