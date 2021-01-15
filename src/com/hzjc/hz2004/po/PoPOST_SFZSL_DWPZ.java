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
*异地身份证受理查询配置
*/
@Entity
@Table(name="POST_SFZSL_DWPZ" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoPOST_SFZSL_DWPZ implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*配置ID，格式：DQBM+PCSBM
	*/
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	private String	pzid;
	private String	dqbm;
	private String	pcsbm;
	private String	pcsmc;
	private String	zhxzsj;

	public PoPOST_SFZSL_DWPZ(){}

	public String getPzid(){
		return this.pzid;
	}

	public void setPzid(String pzid){
		this.pzid=pzid;
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

	public String getZhxzsj(){
		return this.zhxzsj;
	}

	public void setZhxzsj(String zhxzsj){
		this.zhxzsj=zhxzsj;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}