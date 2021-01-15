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
import java.util.Date;

/**
*HZ2004.XT_YHHHXXB表的修改量临时表，用于触发器新增和修改量抽取
*/
@Entity
@Table(name="ETL$_XT_YHHHXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoETL$_XT_YHHHXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	private String	etl_queue_id;
	private Long	yhhhid;
	private Date	etl_queue_cjsj;
	private String	etl_mode;

	public PoETL$_XT_YHHHXXB(){}

	public String getEtl_queue_id(){
		return this.etl_queue_id;
	}

	public void setEtl_queue_id(String etl_queue_id){
		this.etl_queue_id=etl_queue_id;
	}

	public Long getYhhhid(){
		return this.yhhhid;
	}

	public void setYhhhid(Long yhhhid){
		this.yhhhid=yhhhid;
	}

	public Date getEtl_queue_cjsj(){
		return this.etl_queue_cjsj;
	}

	public void setEtl_queue_cjsj(Date etl_queue_cjsj){
		this.etl_queue_cjsj=etl_queue_cjsj;
	}

	public String getEtl_mode(){
		return this.etl_mode;
	}

	public void setEtl_mode(String etl_mode){
		this.etl_mode=etl_mode;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}