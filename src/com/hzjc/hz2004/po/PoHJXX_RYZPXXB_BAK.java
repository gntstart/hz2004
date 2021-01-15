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
*人员照片信息表
*/
@Entity
@Table(name="HJXX_RYZPXXB_BAK" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHJXX_RYZPXXB_BAK implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	private String	logid;
	private Long	yhid;
	private String	scsj;
	private Long	zpid;
	private Long	ryid;
	private String	gmsfhm;
	private String	xm;
	private byte[]	zp;
	private String	lrrq;

	public PoHJXX_RYZPXXB_BAK(){}

	public String getLogid(){
		return this.logid;
	}

	public void setLogid(String logid){
		this.logid=logid;
	}

	public Long getYhid(){
		return this.yhid;
	}

	public void setYhid(Long yhid){
		this.yhid=yhid;
	}

	public String getScsj(){
		return this.scsj;
	}

	public void setScsj(String scsj){
		this.scsj=scsj;
	}

	public Long getZpid(){
		return this.zpid;
	}

	public void setZpid(Long zpid){
		this.zpid=zpid;
	}

	public Long getRyid(){
		return this.ryid;
	}

	public void setRyid(Long ryid){
		this.ryid=ryid;
	}

	public String getGmsfhm(){
		return this.gmsfhm;
	}

	public void setGmsfhm(String gmsfhm){
		this.gmsfhm=gmsfhm;
	}

	public String getXm(){
		return this.xm;
	}

	public void setXm(String xm){
		this.xm=xm;
	}

	public byte[] getZp(){
		return this.zp;
	}

	public void setZp(byte[] zp){
		this.zp=zp;
	}

	public String getLrrq(){
		return this.lrrq;
	}

	public void setLrrq(String lrrq){
		this.lrrq=lrrq;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}