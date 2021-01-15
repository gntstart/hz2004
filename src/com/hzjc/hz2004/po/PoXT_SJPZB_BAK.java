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
*数据配置表
*/
@Entity
@Table(name="XT_SJPZB_BAK" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_SJPZB_BAK implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*数据配置编号
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	sjpzid;
	private String	pzlb;
	private String	pzmc;
	private String	id;
	private String	fieldname;
	private String	displayname;
	private String	fieldtype;
	private String	fieldtypename;
	private String	dsname;
	private String	readonly;
	private String	visibletype;
	private String	fieldlength;
	private String	displayfieldlength;
	private String	ischinese;
	private String	mbmod;
	private String	allowselfinput;
	private String	codefield;
	private String	pyfield;
	private String	namefield;
	private String	partmask;
	private String	lsfield;
	private String	showls;
	private String	enablecopy;
	private String	enabledefaultfilter;
	private String	enterdropdown;
	private String	enteruniqueexit;
	private String	usetablefiltered;
	private String	useforsfz;
	private String	conditionoperator;
	private String	conditionfield;
	private String	conditiontype;
	private String	groups;
	private String	bdlx;
	private String	bdsj;

	public PoXT_SJPZB_BAK(){}

	public Long getSjpzid(){
		return this.sjpzid;
	}

	public void setSjpzid(Long sjpzid){
		this.sjpzid=sjpzid;
	}

	public String getPzlb(){
		return this.pzlb;
	}

	public void setPzlb(String pzlb){
		this.pzlb=pzlb;
	}

	public String getPzmc(){
		return this.pzmc;
	}

	public void setPzmc(String pzmc){
		this.pzmc=pzmc;
	}

	public String getId(){
		return this.id;
	}

	public void setId(String id){
		this.id=id;
	}

	public String getFieldname(){
		return this.fieldname;
	}

	public void setFieldname(String fieldname){
		this.fieldname=fieldname;
	}

	public String getDisplayname(){
		return this.displayname;
	}

	public void setDisplayname(String displayname){
		this.displayname=displayname;
	}

	public String getFieldtype(){
		return this.fieldtype;
	}

	public void setFieldtype(String fieldtype){
		this.fieldtype=fieldtype;
	}

	public String getFieldtypename(){
		return this.fieldtypename;
	}

	public void setFieldtypename(String fieldtypename){
		this.fieldtypename=fieldtypename;
	}

	public String getDsname(){
		return this.dsname;
	}

	public void setDsname(String dsname){
		this.dsname=dsname;
	}

	public String getReadonly(){
		return this.readonly;
	}

	public void setReadonly(String readonly){
		this.readonly=readonly;
	}

	public String getVisibletype(){
		return this.visibletype;
	}

	public void setVisibletype(String visibletype){
		this.visibletype=visibletype;
	}

	public String getFieldlength(){
		return this.fieldlength;
	}

	public void setFieldlength(String fieldlength){
		this.fieldlength=fieldlength;
	}

	public String getDisplayfieldlength(){
		return this.displayfieldlength;
	}

	public void setDisplayfieldlength(String displayfieldlength){
		this.displayfieldlength=displayfieldlength;
	}

	public String getIschinese(){
		return this.ischinese;
	}

	public void setIschinese(String ischinese){
		this.ischinese=ischinese;
	}

	public String getMbmod(){
		return this.mbmod;
	}

	public void setMbmod(String mbmod){
		this.mbmod=mbmod;
	}

	public String getAllowselfinput(){
		return this.allowselfinput;
	}

	public void setAllowselfinput(String allowselfinput){
		this.allowselfinput=allowselfinput;
	}

	public String getCodefield(){
		return this.codefield;
	}

	public void setCodefield(String codefield){
		this.codefield=codefield;
	}

	public String getPyfield(){
		return this.pyfield;
	}

	public void setPyfield(String pyfield){
		this.pyfield=pyfield;
	}

	public String getNamefield(){
		return this.namefield;
	}

	public void setNamefield(String namefield){
		this.namefield=namefield;
	}

	public String getPartmask(){
		return this.partmask;
	}

	public void setPartmask(String partmask){
		this.partmask=partmask;
	}

	public String getLsfield(){
		return this.lsfield;
	}

	public void setLsfield(String lsfield){
		this.lsfield=lsfield;
	}

	public String getShowls(){
		return this.showls;
	}

	public void setShowls(String showls){
		this.showls=showls;
	}

	public String getEnablecopy(){
		return this.enablecopy;
	}

	public void setEnablecopy(String enablecopy){
		this.enablecopy=enablecopy;
	}

	public String getEnabledefaultfilter(){
		return this.enabledefaultfilter;
	}

	public void setEnabledefaultfilter(String enabledefaultfilter){
		this.enabledefaultfilter=enabledefaultfilter;
	}

	public String getEnterdropdown(){
		return this.enterdropdown;
	}

	public void setEnterdropdown(String enterdropdown){
		this.enterdropdown=enterdropdown;
	}

	public String getEnteruniqueexit(){
		return this.enteruniqueexit;
	}

	public void setEnteruniqueexit(String enteruniqueexit){
		this.enteruniqueexit=enteruniqueexit;
	}

	public String getUsetablefiltered(){
		return this.usetablefiltered;
	}

	public void setUsetablefiltered(String usetablefiltered){
		this.usetablefiltered=usetablefiltered;
	}

	public String getUseforsfz(){
		return this.useforsfz;
	}

	public void setUseforsfz(String useforsfz){
		this.useforsfz=useforsfz;
	}

	public String getConditionoperator(){
		return this.conditionoperator;
	}

	public void setConditionoperator(String conditionoperator){
		this.conditionoperator=conditionoperator;
	}

	public String getConditionfield(){
		return this.conditionfield;
	}

	public void setConditionfield(String conditionfield){
		this.conditionfield=conditionfield;
	}

	public String getConditiontype(){
		return this.conditiontype;
	}

	public void setConditiontype(String conditiontype){
		this.conditiontype=conditiontype;
	}

	public String getGroups(){
		return this.groups;
	}

	public void setGroups(String groups){
		this.groups=groups;
	}

	public String getBdlx(){
		return this.bdlx;
	}

	public void setBdlx(String bdlx){
		this.bdlx=bdlx;
	}

	public String getBdsj(){
		return this.bdsj;
	}

	public void setBdsj(String bdsj){
		this.bdsj=bdsj;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}