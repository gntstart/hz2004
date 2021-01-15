package  com.hzjc.hz2004.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
*办证绿色通道人员信息
*/
@Entity
@Table(name="V_MBSPLC" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoV_MBSPLC implements com.hzjc.wsstruts.po.PO
{

	/*
	*接口ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	mblcid;
	private String	dzz;
	private Long	xgdzid;
	private String	dzbz;
	private Long	dzid;
	private Long	spmbid;
	private String	mbmc;
	private String	mbdj;
	private Long	cjrid;
	private String	cjsj;
	private Long	xgrid;
	private String	xgsj;
	private String	qybz;
	private String	dqsys;
	private String	dzmc;
	private String	xgdzmc;
	public Long getMblcid() {
		return mblcid;
	}
	public void setMblcid(Long mblcid) {
		this.mblcid = mblcid;
	}
	public String getDzz() {
		return dzz;
	}
	public void setDzz(String dzz) {
		this.dzz = dzz;
	}
	public Long getXgdzid() {
		return xgdzid;
	}
	public void setXgdzid(Long xgdzid) {
		this.xgdzid = xgdzid;
	}
	public String getDzbz() {
		return dzbz;
	}
	public void setDzbz(String dzbz) {
		this.dzbz = dzbz;
	}
	public Long getDzid() {
		return dzid;
	}
	public void setDzid(Long dzid) {
		this.dzid = dzid;
	}
	public Long getSpmbid() {
		return spmbid;
	}
	public void setSpmbid(Long spmbid) {
		this.spmbid = spmbid;
	}
	public String getMbmc() {
		return mbmc;
	}
	public void setMbmc(String mbmc) {
		this.mbmc = mbmc;
	}
	public String getMbdj() {
		return mbdj;
	}
	public void setMbdj(String mbdj) {
		this.mbdj = mbdj;
	}
	public Long getCjrid() {
		return cjrid;
	}
	public void setCjrid(Long cjrid) {
		this.cjrid = cjrid;
	}
	public String getCjsj() {
		return cjsj;
	}
	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}
	public Long getXgrid() {
		return xgrid;
	}
	public void setXgrid(Long xgrid) {
		this.xgrid = xgrid;
	}
	public String getXgsj() {
		return xgsj;
	}
	public void setXgsj(String xgsj) {
		this.xgsj = xgsj;
	}
	public String getQybz() {
		return qybz;
	}
	public void setQybz(String qybz) {
		this.qybz = qybz;
	}
	public String getDqsys() {
		return dqsys;
	}
	public void setDqsys(String dqsys) {
		this.dqsys = dqsys;
	}
	public String getDzmc() {
		return dzmc;
	}
	public void setDzmc(String dzmc) {
		this.dzmc = dzmc;
	}
	public String getXgdzmc() {
		return xgdzmc;
	}
	public void setXgdzmc(String xgdzmc) {
		this.xgdzmc = xgdzmc;
	}
	
}
