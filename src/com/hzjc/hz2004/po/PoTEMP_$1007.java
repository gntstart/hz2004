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

@Entity
@Table(name="TEMP_$1007" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoTEMP_$1007 implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long	nbslid;
	@Id
	private String	zt$;
	private Timestamp	jlcjsj$;

	public PoTEMP_$1007(){}

	public Long getNbslid(){
		return this.nbslid;
	}

	public void setNbslid(Long nbslid){
		this.nbslid=nbslid;
	}

	public String getZt$(){
		return this.zt$;
	}

	public void setZt$(String zt$){
		this.zt$=zt$;
	}

	public Timestamp getJlcjsj$(){
		return this.jlcjsj$;
	}

	public void setJlcjsj$(Timestamp jlcjsj$){
		this.jlcjsj$=jlcjsj$;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}