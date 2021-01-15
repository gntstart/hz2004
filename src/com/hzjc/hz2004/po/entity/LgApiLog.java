package com.hzjc.hz2004.po.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
*API调用日志
*/
@Entity
@Table(name="zzj_api_log" )
public class LgApiLog implements com.hzjc.wsstruts.po.PO{
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
	/*
	*KEY
	*/
	private String	logid;
	/*
	*旅馆编码
	*/
	private String	lgbm;
	/*
	*调用时间
	*/
	private Date	logsj;
	/*
	*API名称
	*/
	private String	apiname;
	/*
	*影响记录�?
	*/
	private Long	jls;
	/*
	*备注
	*/
	private String	bz;
	/*
	*调用耗时（单位毫秒）
	*/
	private Long	hs;
	/*
	*是否成功
	*/
	private String	sfcg;

	private String ip;

	public LgApiLog(){}


	public LgApiLog(String logid,
			String lgbm,
			Date logsj,
			String apiname,
			Long jls,
			String bz,
			Long hs,
			String sfcg,String ip)
	{
		this.logid=logid;
		this.lgbm=lgbm;
		this.logsj=logsj;
		this.apiname=apiname;
		this.jls=jls;
		this.bz=bz;
		this.hs=hs;
		this.sfcg=sfcg;
		this.ip = ip;
	}

	public String getLogid(){
		return this.logid;
	}

	public void setLogid(String logid){
		this.logid=logid;
	}

	public String getLgbm(){
		return this.lgbm;
	}

	public void setLgbm(String lgbm){
		this.lgbm=lgbm;
	}

	public Date getLogsj(){
		return this.logsj;
	}

	public void setLogsj(Date logsj){
		this.logsj=logsj;
	}

	public String getApiname(){
		return this.apiname;
	}

	public void setApiname(String apiname){
		this.apiname=apiname;
	}

	public Long getJls(){
		return this.jls;
	}

	public void setJls(Long jls){
		this.jls=jls;
	}

	public String getBz(){
		return this.bz;
	}

	public void setBz(String bz){
		this.bz=bz;
	}

	public Long getHs(){
		return this.hs;
	}

	public void setHs(Long hs){
		this.hs=hs;
	}

	public String getSfcg(){
		return this.sfcg;
	}

	public void setSfcg(String sfcg){
		this.sfcg=sfcg;
	}

	public String getLayerOooo(){
		return "";
	}

	public void setLayerOooo(String layerOoooo){
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}