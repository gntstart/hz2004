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
*数据包编号，每次POST之后递增，只有一条记录
*/
@Entity
@Table(name="POST_SJBBH" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoPOST_SJBBH implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*编号ID
	*/
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	private String	bhid;
	private String	bhsj;
	private Long	bh;

	public PoPOST_SJBBH(){}

	public String getBhid(){
		return this.bhid;
	}

	public void setBhid(String bhid){
		this.bhid=bhid;
	}

	public String getBhsj(){
		return this.bhsj;
	}

	public void setBhsj(String bhsj){
		this.bhsj=bhsj;
	}

	public Long getBh(){
		return this.bh;
	}

	public void setBh(Long bh){
		this.bh=bh;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}