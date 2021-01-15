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
import java.sql.Timestamp;

/**
*上报日志信息表，只记录存在上报数据的日志
*/
@Entity
@Table(name="POST_LOG" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoPOST_LOG implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*自定义，非自动UUID编号
	*/
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	private String	logid;
	private Timestamp	logsj;
	private Long	postnum;
	private String	xkz;
	private String	info;
	private String	remsg;
	private String	sfcg;
	private String	errmsg;
	private Long	hs;

	public PoPOST_LOG(){}

	public String getLogid(){
		return this.logid;
	}

	public void setLogid(String logid){
		this.logid=logid;
	}

	public Timestamp getLogsj(){
		return this.logsj;
	}

	public void setLogsj(Timestamp logsj){
		this.logsj=logsj;
	}

	public Long getPostnum(){
		return this.postnum;
	}

	public void setPostnum(Long postnum){
		this.postnum=postnum;
	}

	public String getXkz(){
		return this.xkz;
	}

	public void setXkz(String xkz){
		this.xkz=xkz;
	}

	public String getInfo(){
		return this.info;
	}

	public void setInfo(String info){
		this.info=info;
	}

	public String getRemsg(){
		return this.remsg;
	}

	public void setRemsg(String remsg){
		this.remsg=remsg;
	}

	public String getSfcg(){
		return this.sfcg;
	}

	public void setSfcg(String sfcg){
		this.sfcg=sfcg;
	}

	public String getErrmsg(){
		return this.errmsg;
	}

	public void setErrmsg(String errmsg){
		this.errmsg=errmsg;
	}

	public Long getHs(){
		return this.hs;
	}

	public void setHs(Long hs){
		this.hs=hs;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}