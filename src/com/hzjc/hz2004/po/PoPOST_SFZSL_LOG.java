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
*身份证异地受理日志
*/
@Entity
@Table(name="POST_SFZSL_LOG" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoPOST_SFZSL_LOG implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*ID
	*/
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	private String	loadid;
	private String	dqbm;
	private String	pcsbm;
	private String	pcsmc;
	private String	loaddate;
	private Long	loadnum;

	public PoPOST_SFZSL_LOG(){}

	public String getLoadid(){
		return this.loadid;
	}

	public void setLoadid(String loadid){
		this.loadid=loadid;
	}

	public String getDqbm(){
		return this.dqbm;
	}

	public void setDqbm(String dqbm){
		this.dqbm=dqbm;
	}

	public String getPcsbm(){
		return this.pcsbm;
	}

	public void setPcsbm(String pcsbm){
		this.pcsbm=pcsbm;
	}

	public String getPcsmc(){
		return this.pcsmc;
	}

	public void setPcsmc(String pcsmc){
		this.pcsmc=pcsmc;
	}

	public String getLoaddate(){
		return this.loaddate;
	}

	public void setLoaddate(String loaddate){
		this.loaddate=loaddate;
	}

	public Long getLoadnum(){
		return this.loadnum;
	}

	public void setLoadnum(Long loadnum){
		this.loadnum=loadnum;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}