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
*系统日志表
*/
@Entity
@Table(name="XT_XTRZB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_XTRZB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*日志ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	rzid;
	private String	czkssj;
	private String	czjssj;
	private Long	zxsj;
	private Long	czyid;
	private String	khdip;
	private String	rzlx;
	private String	rznr;
	private String	ywbz;
	private String	reg_id;
	private String	organization;
	private String	organization_id;
	private String	operate_type;
	private String	operate_result;
	private String	error_code;
	private String	operate_name;
	private String	resource_type;
	private String	resource_name;
	private String	czyxm;

	public PoXT_XTRZB(){}

	public Long getRzid(){
		return this.rzid;
	}

	public void setRzid(Long rzid){
		this.rzid=rzid;
	}

	public String getCzkssj(){
		return this.czkssj;
	}

	public void setCzkssj(String czkssj){
		this.czkssj=czkssj;
	}

	public String getCzjssj(){
		return this.czjssj;
	}

	public void setCzjssj(String czjssj){
		this.czjssj=czjssj;
	}

	public Long getZxsj(){
		return this.zxsj;
	}

	public void setZxsj(Long zxsj){
		this.zxsj=zxsj;
	}

	public Long getCzyid(){
		return this.czyid;
	}

	public void setCzyid(Long czyid){
		this.czyid=czyid;
	}

	public String getKhdip(){
		return this.khdip;
	}

	public void setKhdip(String khdip){
		this.khdip=khdip;
	}

	public String getRzlx(){
		return this.rzlx;
	}

	public void setRzlx(String rzlx){
		this.rzlx=rzlx;
	}

	public String getRznr(){
		return this.rznr;
	}

	public void setRznr(String rznr){
		this.rznr=rznr;
	}

	public String getYwbz(){
		return this.ywbz;
	}

	public void setYwbz(String ywbz){
		this.ywbz=ywbz;
	}

	public String getReg_id(){
		return this.reg_id;
	}

	public void setReg_id(String reg_id){
		this.reg_id=reg_id;
	}

	public String getOrganization(){
		return this.organization;
	}

	public void setOrganization(String organization){
		this.organization=organization;
	}

	public String getOrganization_id(){
		return this.organization_id;
	}

	public void setOrganization_id(String organization_id){
		this.organization_id=organization_id;
	}

	public String getOperate_type(){
		return this.operate_type;
	}

	public void setOperate_type(String operate_type){
		this.operate_type=operate_type;
	}

	public String getOperate_result(){
		return this.operate_result;
	}

	public void setOperate_result(String operate_result){
		this.operate_result=operate_result;
	}

	public String getError_code(){
		return this.error_code;
	}

	public void setError_code(String error_code){
		this.error_code=error_code;
	}

	public String getOperate_name(){
		return this.operate_name;
	}

	public void setOperate_name(String operate_name){
		this.operate_name=operate_name;
	}

	public String getResource_type(){
		return this.resource_type;
	}

	public void setResource_type(String resource_type){
		this.resource_type=resource_type;
	}

	public String getResource_name(){
		return this.resource_name;
	}

	public void setResource_name(String resource_name){
		this.resource_name=resource_name;
	}

	public String getCzyxm(){
		return this.czyxm;
	}

	public void setCzyxm(String czyxm){
		this.czyxm=czyxm;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}