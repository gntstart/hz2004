package  com.hzjc.hz2004.po.notable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="OLD_HJYW_SWZXXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoOLD_HJYW_SWZXXXB implements com.hzjc.wsstruts.po.PO
{

	private static final long serialVersionUID = 1L;

	/*
	*死亡注销ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	swzxid;
	private Long	rynbid;
	private String	swrq;
	private String	swnl;
	private String	swzmbh;
	private String	swzxlb;
	private Long	hjywid;
	private String	cxbz;
	private String	cxsj;
	private Long	cxrid;
	private Long	cxhjywid;
	private Long	tbbz;
	private String	bwbh;
	private String	sbsj;
	private String	sbryxm;
	private String	sbrgmsfhm;
	private String	slsj;
	private String	sldw;
	private Long	slrid;
	private String	ywlx;
	private Long	czsm;
	private String	xm;
	private String	gmsfhm;
	private String	mz;
	private String	xb;
	private String	csrq;
	private String	cssj;
	private String	csdssxq;
	private Long	ryid;
	private Long	hhnbid;
	private String	ssxq;
	private String	jlx;
	private String	mlph;
	private String	mlxz;
	private String	pcs;
	private String	zrq;
	private String	xzjd;
	private String	jcwh;
	private String	pxh;
	private Long	mlpnbid;
	private String	hb;
	private String	yhzgx;
	private String	hzxm;
	private String	hzgmsfhm;
	private Long	hhid;
	private Long	mlpid;
	
	public PoOLD_HJYW_SWZXXXB(){}

	public Long getSwzxid(){
		return this.swzxid;
	}

	public void setSwzxid(Long swzxid){
		this.swzxid=swzxid;
	}

	public Long getRynbid(){
		return this.rynbid;
	}

	public void setRynbid(Long rynbid){
		this.rynbid=rynbid;
	}

	public String getSwrq(){
		return this.swrq;
	}

	public void setSwrq(String swrq){
		this.swrq=swrq;
	}

	public String getSwnl(){
		return this.swnl;
	}

	public void setSwnl(String swnl){
		this.swnl=swnl;
	}

	public String getSwzmbh(){
		return this.swzmbh;
	}

	public void setSwzmbh(String swzmbh){
		this.swzmbh=swzmbh;
	}

	public String getSwzxlb(){
		return this.swzxlb;
	}

	public void setSwzxlb(String swzxlb){
		this.swzxlb=swzxlb;
	}

	public Long getHjywid(){
		return this.hjywid;
	}

	public void setHjywid(Long hjywid){
		this.hjywid=hjywid;
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

	public Long getCxhjywid(){
		return this.cxhjywid;
	}

	public void setCxhjywid(Long cxhjywid){
		this.cxhjywid=cxhjywid;
	}

	public Long getTbbz(){
		return this.tbbz;
	}

	public void setTbbz(Long tbbz){
		this.tbbz=tbbz;
	}

	public String getBwbh(){
		return this.bwbh;
	}

	public void setBwbh(String bwbh){
		this.bwbh=bwbh;
	}

	public String getSbsj(){
		return this.sbsj;
	}

	public void setSbsj(String sbsj){
		this.sbsj=sbsj;
	}

	public String getSbryxm(){
		return this.sbryxm;
	}

	public void setSbryxm(String sbryxm){
		this.sbryxm=sbryxm;
	}

	public String getSbrgmsfhm(){
		return this.sbrgmsfhm;
	}

	public void setSbrgmsfhm(String sbrgmsfhm){
		this.sbrgmsfhm=sbrgmsfhm;
	}

	public String getSlsj(){
		return this.slsj;
	}

	public void setSlsj(String slsj){
		this.slsj=slsj;
	}

	public String getSldw(){
		return this.sldw;
	}

	public void setSldw(String sldw){
		this.sldw=sldw;
	}

	public Long getSlrid(){
		return this.slrid;
	}

	public void setSlrid(Long slrid){
		this.slrid=slrid;
	}

	public String getYwlx(){
		return this.ywlx;
	}

	public void setYwlx(String ywlx){
		this.ywlx=ywlx;
	}

	public Long getCzsm(){
		return this.czsm;
	}

	public void setCzsm(Long czsm){
		this.czsm=czsm;
	}

	public String getXm(){
		return this.xm;
	}

	public void setXm(String xm){
		this.xm=xm;
	}

	public String getGmsfhm(){
		return this.gmsfhm;
	}

	public void setGmsfhm(String gmsfhm){
		this.gmsfhm=gmsfhm;
	}

	public String getMz(){
		return this.mz;
	}

	public void setMz(String mz){
		this.mz=mz;
	}

	public String getXb(){
		return this.xb;
	}

	public void setXb(String xb){
		this.xb=xb;
	}

	public String getCsrq(){
		return this.csrq;
	}

	public void setCsrq(String csrq){
		this.csrq=csrq;
	}

	public String getCssj(){
		return this.cssj;
	}

	public void setCssj(String cssj){
		this.cssj=cssj;
	}

	public String getCsdssxq(){
		return this.csdssxq;
	}

	public void setCsdssxq(String csdssxq){
		this.csdssxq=csdssxq;
	}

	public Long getRyid(){
		return this.ryid;
	}

	public void setRyid(Long ryid){
		this.ryid=ryid;
	}

	public Long getHhnbid(){
		return this.hhnbid;
	}

	public void setHhnbid(Long hhnbid){
		this.hhnbid=hhnbid;
	}

	public String getSsxq(){
		return this.ssxq;
	}

	public void setSsxq(String ssxq){
		this.ssxq=ssxq;
	}

	public String getJlx(){
		return this.jlx;
	}

	public void setJlx(String jlx){
		this.jlx=jlx;
	}

	public String getMlph(){
		return this.mlph;
	}

	public void setMlph(String mlph){
		this.mlph=mlph;
	}

	public String getMlxz(){
		return this.mlxz;
	}

	public void setMlxz(String mlxz){
		this.mlxz=mlxz;
	}

	public String getPcs(){
		return this.pcs;
	}

	public void setPcs(String pcs){
		this.pcs=pcs;
	}

	public String getZrq(){
		return this.zrq;
	}

	public void setZrq(String zrq){
		this.zrq=zrq;
	}

	public String getXzjd(){
		return this.xzjd;
	}

	public void setXzjd(String xzjd){
		this.xzjd=xzjd;
	}

	public String getJcwh(){
		return this.jcwh;
	}

	public void setJcwh(String jcwh){
		this.jcwh=jcwh;
	}

	public String getPxh(){
		return this.pxh;
	}

	public void setPxh(String pxh){
		this.pxh=pxh;
	}

	public Long getMlpnbid(){
		return this.mlpnbid;
	}

	public void setMlpnbid(Long mlpnbid){
		this.mlpnbid=mlpnbid;
	}

	public String getHb(){
		return this.hb;
	}

	public void setHb(String hb){
		this.hb=hb;
	}

	public String getYhzgx(){
		return this.yhzgx;
	}

	public void setYhzgx(String yhzgx){
		this.yhzgx=yhzgx;
	}

	public String getHzxm(){
		return this.hzxm;
	}

	public void setHzxm(String hzxm){
		this.hzxm=hzxm;
	}

	public String getHzgmsfhm(){
		return this.hzgmsfhm;
	}

	public void setHzgmsfhm(String hzgmsfhm){
		this.hzgmsfhm=hzgmsfhm;
	}

	public Long getHhid(){
		return this.hhid;
	}

	public void setHhid(Long hhid){
		this.hhid=hhid;
	}

	public Long getMlpid(){
		return this.mlpid;
	}

	public void setMlpid(Long mlpid){
		this.mlpid=mlpid;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}
