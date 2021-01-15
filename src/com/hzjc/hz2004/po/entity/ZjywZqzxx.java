package com.hzjc.hz2004.po.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * 浙江准迁证接收参数实体类
 *
 */
@Entity
@Table(name = "zjyw_zqzxx")
public class ZjywZqzxx implements com.hzjc.wsstruts.po.PO{
    private static final long serialVersionUID = 1L;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Id
    //业务流水号
    private String ywlsh;
    //返回存储标记
    private String postid;
    //准迁证编号
    private String zqzbh;
    //申请人_公民身份号码
    private String sqr_gmsfhm;
    //申请人_姓名
    private String sqr_xm;
    //申请人_住址_省市县（区）
    private String sqr_zz_ssxqdm;
    //申请人_住址_区划内详细地址
    private String sqr_zz_qhnxxdz;
    //申请人_户口登记机关_公安机关机构代码
    private String sqr_hkdjjg_gajgjgdm;
    //申请人_户口登记机关_公安机关名称
    private String sqr_hkdjjg_gajgmc;
    //迁出地_省市县（区）
    private String qcd_ssxqdm;
    //迁出地_区划内详细地址
    private String qcd_qhnxxdz;
    //迁出地_户口登记机关_公安机关机构代码
    private String qcd_hkdjjg_gajgjgdm;
    //迁出地_户口登记机关_公安机关名称
    private String qcd_hkdjjg_gajgmc;
    //迁入地_省市县（区）
    private String qrd_ssxqdm;
    //迁入地_区划内详细地址
    private String qrd_qhnxxdz;
    //迁入地_户口登记机关_公安机关机构代码
    private String qrd_hkdjjg_gajgjgdm;
    //迁入地_户口登记机关_公安机关名称
    private String qrd_hkdjjg_gajgmc;
    //签发机关_公安机关机构代码
    private String qfjg_gajgjgdm;
    //签发机关_公安机关名称
    private String qfjg_gajgmc;
    //承办人_姓名
    private String cbr_xm;
    //签发日期
    private String qfrq;
    //备注
    private String bz;
    //迁移（流动）原因
    private String qyldyydm;
    //有效期截止日期
    private String yxqjzrq;
    //区域范围
    private String qyfwdm;
    //受理单位_公安机关机构代码
    private String sldw_gajgjgdm;
    //受理单位_公安机关名称
    private String sldw_gajgmc;
    //受理人_姓名
    private String slr_xm;
    //受理时间
    private String slsj;
    //数据归属单位代码
    private String sjgsdwdm;
    //数据归属单位名称
    private String sjgsdwmc;


    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getYwlsh() {
        return ywlsh;
    }

    public void setYwlsh(String ywlsh) {
        this.ywlsh = ywlsh;
    }

    public String getZqzbh() {
        return zqzbh;
    }

    public void setZqzbh(String zqzbh) {
        this.zqzbh = zqzbh;
    }

    public String getSqr_gmsfhm() {
        return sqr_gmsfhm;
    }

    public void setSqr_gmsfhm(String sqr_gmsfhm) {
        this.sqr_gmsfhm = sqr_gmsfhm;
    }

    public String getSqr_xm() {
        return sqr_xm;
    }

    public void setSqr_xm(String sqr_xm) {
        this.sqr_xm = sqr_xm;
    }

    public String getSqr_zz_ssxqdm() {
        return sqr_zz_ssxqdm;
    }

    public void setSqr_zz_ssxqdm(String sqr_zz_ssxqdm) {
        this.sqr_zz_ssxqdm = sqr_zz_ssxqdm;
    }

    public String getSqr_zz_qhnxxdz() {
        return sqr_zz_qhnxxdz;
    }

    public void setSqr_zz_qhnxxdz(String sqr_zz_qhnxxdz) {
        this.sqr_zz_qhnxxdz = sqr_zz_qhnxxdz;
    }

    public String getSqr_hkdjjg_gajgjgdm() {
        return sqr_hkdjjg_gajgjgdm;
    }

    public void setSqr_hkdjjg_gajgjgdm(String sqr_hkdjjg_gajgjgdm) {
        this.sqr_hkdjjg_gajgjgdm = sqr_hkdjjg_gajgjgdm;
    }

    public String getSqr_hkdjjg_gajgmc() {
        return sqr_hkdjjg_gajgmc;
    }

    public void setSqr_hkdjjg_gajgmc(String sqr_hkdjjg_gajgmc) {
        this.sqr_hkdjjg_gajgmc = sqr_hkdjjg_gajgmc;
    }

    public String getQcd_ssxqdm() {
        return qcd_ssxqdm;
    }

    public void setQcd_ssxqdm(String qcd_ssxqdm) {
        this.qcd_ssxqdm = qcd_ssxqdm;
    }

    public String getQcd_qhnxxdz() {
        return qcd_qhnxxdz;
    }

    public void setQcd_qhnxxdz(String qcd_qhnxxdz) {
        this.qcd_qhnxxdz = qcd_qhnxxdz;
    }

    public String getQcd_hkdjjg_gajgjgdm() {
        return qcd_hkdjjg_gajgjgdm;
    }

    public void setQcd_hkdjjg_gajgjgdm(String qcd_hkdjjg_gajgjgdm) {
        this.qcd_hkdjjg_gajgjgdm = qcd_hkdjjg_gajgjgdm;
    }

    public String getQcd_hkdjjg_gajgmc() {
        return qcd_hkdjjg_gajgmc;
    }

    public void setQcd_hkdjjg_gajgmc(String qcd_hkdjjg_gajgmc) {
        this.qcd_hkdjjg_gajgmc = qcd_hkdjjg_gajgmc;
    }

    public String getQrd_ssxqdm() {
        return qrd_ssxqdm;
    }

    public void setQrd_ssxqdm(String qrd_ssxqdm) {
        this.qrd_ssxqdm = qrd_ssxqdm;
    }

    public String getQrd_qhnxxdz() {
        return qrd_qhnxxdz;
    }

    public void setQrd_qhnxxdz(String qrd_qhnxxdz) {
        this.qrd_qhnxxdz = qrd_qhnxxdz;
    }

    public String getQrd_hkdjjg_gajgjgdm() {
        return qrd_hkdjjg_gajgjgdm;
    }

    public void setQrd_hkdjjg_gajgjgdm(String qrd_hkdjjg_gajgjgdm) {
        this.qrd_hkdjjg_gajgjgdm = qrd_hkdjjg_gajgjgdm;
    }

    public String getQrd_hkdjjg_gajgmc() {
        return qrd_hkdjjg_gajgmc;
    }

    public void setQrd_hkdjjg_gajgmc(String qrd_hkdjjg_gajgmc) {
        this.qrd_hkdjjg_gajgmc = qrd_hkdjjg_gajgmc;
    }

    public String getQfjg_gajgjgdm() {
        return qfjg_gajgjgdm;
    }

    public void setQfjg_gajgjgdm(String qfjg_gajgjgdm) {
        this.qfjg_gajgjgdm = qfjg_gajgjgdm;
    }

    public String getQfjg_gajgmc() {
        return qfjg_gajgmc;
    }

    public void setQfjg_gajgmc(String qfjg_gajgmc) {
        this.qfjg_gajgmc = qfjg_gajgmc;
    }

    public String getCbr_xm() {
        return cbr_xm;
    }

    public void setCbr_xm(String cbr_xm) {
        this.cbr_xm = cbr_xm;
    }

    public String getQfrq() {
        return qfrq;
    }

    public void setQfrq(String qfrq) {
        this.qfrq = qfrq;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getQyldyydm() {
        return qyldyydm;
    }

    public void setQyldyydm(String qyldyydm) {
        this.qyldyydm = qyldyydm;
    }

    public String getYxqjzrq() {
        return yxqjzrq;
    }

    public void setYxqjzrq(String yxqjzrq) {
        this.yxqjzrq = yxqjzrq;
    }

    public String getQyfwdm() {
        return qyfwdm;
    }

    public void setQyfwdm(String qyfwdm) {
        this.qyfwdm = qyfwdm;
    }

    public String getSldw_gajgjgdm() {
        return sldw_gajgjgdm;
    }

    public void setSldw_gajgjgdm(String sldw_gajgjgdm) {
        this.sldw_gajgjgdm = sldw_gajgjgdm;
    }

    public String getSldw_gajgmc() {
        return sldw_gajgmc;
    }

    public void setSldw_gajgmc(String sldw_gajgmc) {
        this.sldw_gajgmc = sldw_gajgmc;
    }

    public String getSlr_xm() {
        return slr_xm;
    }

    public void setSlr_xm(String slr_xm) {
        this.slr_xm = slr_xm;
    }

    public String getSlsj() {
        return slsj;
    }

    public void setSlsj(String slsj) {
        this.slsj = slsj;
    }

    public String getSjgsdwdm() {
        return sjgsdwdm;
    }

    public void setSjgsdwdm(String sjgsdwdm) {
        this.sjgsdwdm = sjgsdwdm;
    }

    public String getSjgsdwmc() {
        return sjgsdwmc;
    }

    public void setSjgsdwmc(String sjgsdwmc) {
        this.sjgsdwmc = sjgsdwmc;
    }
}
