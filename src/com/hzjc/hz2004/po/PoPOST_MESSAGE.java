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
*用户登录后，检查该表是否有当前用户的信息，有多少条，弹出多少次确认窗口
*/
@Entity
@Table(name="POST_MESSAGE" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoPOST_MESSAGE implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	msgid;
	private String	yhdlm;
	private String	txxx;
	private String	cjsj;

	public PoPOST_MESSAGE(){}

	public Long getMsgid(){
		return this.msgid;
	}

	public void setMsgid(Long msgid){
		this.msgid=msgid;
	}

	public String getYhdlm(){
		return this.yhdlm;
	}

	public void setYhdlm(String yhdlm){
		this.yhdlm=yhdlm;
	}

	public String getTxxx(){
		return this.txxx;
	}

	public void setTxxx(String txxx){
		this.txxx=txxx;
	}

	public String getCjsj(){
		return this.cjsj;
	}

	public void setCjsj(String cjsj){
		this.cjsj=cjsj;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}