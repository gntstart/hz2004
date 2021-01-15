package com.hzjc.hz2004.po.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
*API映射，记录第三方ID和住宿流水号的关
*/
@Entity
@Table(name="zzj_Api_ys" )
public class ApiYs  implements com.hzjc.wsstruts.po.PO{
private static final long serialVersionUID = 1L;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	
/*
	*单位层次
	*/
	private String	logid;
	/*
	*旅馆编码
	*/
	private String	lg;
	/*
	*内外宾标志：1 内宾�? 外宾
	*/
	private String	nwbbz;
	/*
	*对方主键
	*/
	private String	postid;
	/*
	*入住流水�?
	*/
	private String	lkbm;
	/*
	*创建时间
	*/
	private Date	cjsj;

	public ApiYs(){}


	

	public String getLogid(){
		return this.logid;
	}

	public void setLogid(String logid){
		this.logid=logid;
	}

	public String getLg(){
		return this.lg;
	}

	public void setLg(String lg){
		this.lg=lg;
	}

	public String getNwbbz(){
		return this.nwbbz;
	}

	public void setNwbbz(String nwbbz){
		this.nwbbz=nwbbz;
	}

	public String getPostid(){
		return this.postid;
	}

	public void setPostid(String postid){
		this.postid=postid;
	}

	public String getLkbm(){
		return this.lkbm;
	}

	public void setLkbm(String lkbm){
		this.lkbm=lkbm;
	}

	public Date getCjsj(){
		return this.cjsj;
	}

	public void setCjsj(Date cjsj){
		this.cjsj=cjsj;
	}

	public String getLayerOooo(){
		return "";
	}

	public void setLayerOooo(String layerOoooo){
	}
}