package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;
import com.hzjc.hz2004.base.encode.Base64;
import com.hzjc.hz2004.po.PoDW_DY_SET;
import com.hzjc.hz2004.po.PoHJXX_RYZPXXB;
/**
 * 拍照照片删除返回信息Vo
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: GNT Corp.</p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoDW_DY_SET
    extends DefaultVO {

	private String	dwdm;//单位代码
	private String	cbtzd;//重办通知单信息
	private String	kzlzrq;//快证领证日期
	private String	mzlzrq;//慢证领证日期
	private String	pcslxdh;//派出所联系电话
	private String	dkqckh;//读卡器串口号
	private String	mndk;//模拟读卡
	private Integer	ywlimit;//每次进行审核、签发的数据数量(40-1000)
	private String	xkjdk;//使用写卡机具读卡
	private String	pcsmc;//派出所名称-快证接收信息配置
	private String	pcsyb;//派出所邮编-快证接收信息配置
	private String	pcsdz;//派出所地址-快证接收信息配置
	private String	pcsdh;//派出所联系电话-快证接收信息配置
	private String	lxdh_ydbz;//联系电话-异地办证信息配置
	private String	lzrq_ydbz;//领证日期-异地办证信息配置
	public VoDW_DY_SET() {
	}
	public String getDwdm() {
		return dwdm;
	}
	public void setDwdm(String dwdm) {
		this.dwdm = dwdm;
	}
	public String getCbtzd() {
		return cbtzd;
	}
	public void setCbtzd(String cbtzd) {
		this.cbtzd = cbtzd;
	}
	public String getKzlzrq() {
		return kzlzrq;
	}
	public void setKzlzrq(String kzlzrq) {
		this.kzlzrq = kzlzrq;
	}
	public String getMzlzrq() {
		return mzlzrq;
	}
	public void setMzlzrq(String mzlzrq) {
		this.mzlzrq = mzlzrq;
	}
	public String getPcslxdh() {
		return pcslxdh;
	}
	public void setPcslxdh(String pcslxdh) {
		this.pcslxdh = pcslxdh;
	}
	public String getDkqckh() {
		return dkqckh;
	}
	public void setDkqckh(String dkqckh) {
		this.dkqckh = dkqckh;
	}
	public String getMndk() {
		return mndk;
	}
	public void setMndk(String mndk) {
		this.mndk = mndk;
	}
	public Integer getYwlimit() {
		return ywlimit;
	}
	public void setYwlimit(Integer ywlimit) {
		this.ywlimit = ywlimit;
	}
	public String getXkjdk() {
		return xkjdk;
	}
	public void setXkjdk(String xkjdk) {
		this.xkjdk = xkjdk;
	}
	public String getPcsmc() {
		return pcsmc;
	}
	public void setPcsmc(String pcsmc) {
		this.pcsmc = pcsmc;
	}
	public String getPcsyb() {
		return pcsyb;
	}
	public void setPcsyb(String pcsyb) {
		this.pcsyb = pcsyb;
	}
	public String getPcsdz() {
		return pcsdz;
	}
	public void setPcsdz(String pcsdz) {
		this.pcsdz = pcsdz;
	}
	public String getPcsdh() {
		return pcsdh;
	}
	public void setPcsdh(String pcsdh) {
		this.pcsdh = pcsdh;
	}
	public String getLxdh_ydbz() {
		return lxdh_ydbz;
	}
	public void setLxdh_ydbz(String lxdh_ydbz) {
		this.lxdh_ydbz = lxdh_ydbz;
	}
	public String getLzrq_ydbz() {
		return lzrq_ydbz;
	}
	public void setLzrq_ydbz(String lzrq_ydbz) {
		this.lzrq_ydbz = lzrq_ydbz;
	}

}





