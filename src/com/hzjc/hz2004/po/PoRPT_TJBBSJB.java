package com.hzjc.hz2004.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**统计报表数据表
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name="RPT_TJBBSJB" )
public class PoRPT_TJBBSJB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long  tjbbid;
	private  String jcwh;
	private String tjny;
	private Integer hs;
	private Integer rs_nan;
	private Integer rs_nv;
	private Integer fnyrks;
	private Integer wlczrs;
	private Integer ysbsyxrs;//18岁以下人数
	private Integer  ysbdsswsrs;//18到35岁人数
	private Integer  sswdlssrs;//35到60岁人数
	private Integer  lssysrs;//60岁以上人数
	private Integer   nldyybrs_nan;//年龄大于100人数（男）
	private Integer nldyybrs_nv;//年龄大于100人数（女）
	private String czsj;//
	private Long czyid;
	private String czyip;
	public Long getTjbbid() {
		return tjbbid;
	}
	public void setTjbbid(Long tjbbid) {
		this.tjbbid = tjbbid;
	}
	public String getJcwh() {
		return jcwh;
	}
	public void setJcwh(String jcwh) {
		this.jcwh = jcwh;
	}
	public String getTjny() {
		return tjny;
	}
	public void setTjny(String tjny) {
		this.tjny = tjny;
	}
	public Integer getHs() {
		return hs;
	}
	public void setHs(Integer hs) {
		this.hs = hs;
	}
	
	public Integer getRs_nv() {
		return rs_nv;
	}
	public void setRs_nv(Integer rs_nv) {
		this.rs_nv = rs_nv;
	}
	public Integer getFnyrks() {
		return fnyrks;
	}
	public void setFnyrks(Integer fnyrks) {
		this.fnyrks = fnyrks;
	}
	public Integer getWlczrs() {
		return wlczrs;
	}
	public void setWlczrs(Integer wlczrs) {
		this.wlczrs = wlczrs;
	}
	public Integer getYsbsyxrs() {
		return ysbsyxrs;
	}
	public void setYsbsyxrs(Integer ysbsyxrs) {
		this.ysbsyxrs = ysbsyxrs;
	}
	public Integer getYsbdsswsrs() {
		return ysbdsswsrs;
	}
	public void setYsbdsswsrs(Integer ysbdsswsrs) {
		this.ysbdsswsrs = ysbdsswsrs;
	}
	public Integer getSswdlssrs() {
		return sswdlssrs;
	}
	public void setSswdlssrs(Integer sswdlssrs) {
		this.sswdlssrs = sswdlssrs;
	}
	public Integer getLssysrs() {
		return lssysrs;
	}
	public void setLssysrs(Integer lssysrs) {
		this.lssysrs = lssysrs;
	}
	
	
	public Integer getRs_nan() {
		return rs_nan;
	}
	public void setRs_nan(Integer rs_nan) {
		this.rs_nan = rs_nan;
	}
	public Integer getNldyybrs_nan() {
		return nldyybrs_nan;
	}
	public void setNldyybrs_nan(Integer nldyybrs_nan) {
		this.nldyybrs_nan = nldyybrs_nan;
	}
	public Integer getNldyybrs_nv() {
		return nldyybrs_nv;
	}
	public void setNldyybrs_nv(Integer nldyybrs_nv) {
		this.nldyybrs_nv = nldyybrs_nv;
	}
	public String getCzsj() {
		return czsj;
	}
	public void setCzsj(String czsj) {
		this.czsj = czsj;
	}
	public Long getCzyid() {
		return czyid;
	}
	public void setCzyid(Long czyid) {
		this.czyid = czyid;
	}
	public String getCzyip() {
		return czyip;
	}
	public void setCzyip(String czyip) {
		this.czyip = czyip;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}