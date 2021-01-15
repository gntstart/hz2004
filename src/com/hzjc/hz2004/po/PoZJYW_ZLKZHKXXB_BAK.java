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
@Table(name="ZJYW_ZLKZHKXXB_BAK" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoZJYW_ZLKZHKXXB_BAK implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	zlkzhkid;
	private Long	rynbid;
	private Long	nbslid;
	private String	zzxxcwlb;
	private String	cwms;
	private String	jydw;
	private String	jyrxm;
	private String	jyrq;
	private String	cldw;
	private String	clqk;
	private String	clrq;
	private Long	zjywid;
	private String	cxbz;
	private String	cxsj;
	private Long	cxrid;
	private Long	cxzjywid;

	public PoZJYW_ZLKZHKXXB_BAK(){}

	public Long getZlkzhkid(){
		return this.zlkzhkid;
	}

	public void setZlkzhkid(Long zlkzhkid){
		this.zlkzhkid=zlkzhkid;
	}

	public Long getRynbid(){
		return this.rynbid;
	}

	public void setRynbid(Long rynbid){
		this.rynbid=rynbid;
	}

	public Long getNbslid(){
		return this.nbslid;
	}

	public void setNbslid(Long nbslid){
		this.nbslid=nbslid;
	}

	public String getZzxxcwlb(){
		return this.zzxxcwlb;
	}

	public void setZzxxcwlb(String zzxxcwlb){
		this.zzxxcwlb=zzxxcwlb;
	}

	public String getCwms(){
		return this.cwms;
	}

	public void setCwms(String cwms){
		this.cwms=cwms;
	}

	public String getJydw(){
		return this.jydw;
	}

	public void setJydw(String jydw){
		this.jydw=jydw;
	}

	public String getJyrxm(){
		return this.jyrxm;
	}

	public void setJyrxm(String jyrxm){
		this.jyrxm=jyrxm;
	}

	public String getJyrq(){
		return this.jyrq;
	}

	public void setJyrq(String jyrq){
		this.jyrq=jyrq;
	}

	public String getCldw(){
		return this.cldw;
	}

	public void setCldw(String cldw){
		this.cldw=cldw;
	}

	public String getClqk(){
		return this.clqk;
	}

	public void setClqk(String clqk){
		this.clqk=clqk;
	}

	public String getClrq(){
		return this.clrq;
	}

	public void setClrq(String clrq){
		this.clrq=clrq;
	}

	public Long getZjywid(){
		return this.zjywid;
	}

	public void setZjywid(Long zjywid){
		this.zjywid=zjywid;
	}

	public String getCxbz(){
		return this.cxbz;
	}

	public void setCxbz(String cxbz){
		this.cxbz=cxbz;
	}

	public String getCxsj(){
		return this.cxsj;
	}

	public void setCxsj(String cxsj){
		this.cxsj=cxsj;
	}

	public Long getCxrid(){
		return this.cxrid;
	}

	public void setCxrid(Long cxrid){
		this.cxrid=cxrid;
	}

	public Long getCxzjywid(){
		return this.cxzjywid;
	}

	public void setCxzjywid(Long cxzjywid){
		this.cxzjywid=cxzjywid;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}