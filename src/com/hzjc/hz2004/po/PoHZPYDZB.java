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
@Table(name="HZPYDZB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHZPYDZB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	private String	pyid;
	private String	hz;
	private String	py;

	public PoHZPYDZB(){}

	public String getPyid(){
		return this.pyid;
	}

	public void setPyid(String pyid){
		this.pyid=pyid;
	}

	public String getHz(){
		return this.hz;
	}

	public void setHz(String hz){
		this.hz=hz;
	}

	public String getPy(){
		return this.py;
	}

	public void setPy(String py){
		this.py=py;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}