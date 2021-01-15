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

@Entity
@Table(name="SYS_USER" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoSYS_USER implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	private String	user_id;
	private String	password;

	public PoSYS_USER(){}

	public String getUser_id(){
		return this.user_id;
	}

	public void setUser_id(String user_id){
		this.user_id=user_id;
	}

	public String getPassword(){
		return this.password;
	}

	public void setPassword(String password){
		this.password=password;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}