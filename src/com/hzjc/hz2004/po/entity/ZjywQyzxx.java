package com.hzjc.hz2004.po.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 浙江迁移证接收参数实体类
 */
@Entity
@Table(name = "zjyw_qyzxx")
public class ZjywQyzxx implements com.hzjc.wsstruts.po.PO {
    private static final long serialVersionUID = 1L;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Id
    //迁移证编号
    private String qyzbh;
    //持证人_公民身份号码
    private String czr_gmsfhm;
    //持证人_姓名
    private String czr_xm;
    //原住址_省市县（区）
    private String yzz_ssxqdm;
    //原住址_区划内详细地址
    private String yzz_qhnxxdz;
    //原住址_城乡分类
    private String yzz_cxfldm;
    //去往地_省市县（区）
    private String qwd_ssxqdm;
    //去往地_区划内详细地址
    private String qwd_qhnxxdz;
    //签发机关_公安机关机构代码
    private String qfjg_gajgjgdm;
    //签发机关_公安机关名称
    private String qfjg_gajgmc;
    //签发日期
    private String qfrq;
    //有效期截止日期
    private String yxqjzrq;
    //承办人_姓名
    private String cbr_xm;
    //备注
    private String bz;
    //准迁证编号
    private String zqzbh;
    //区域范围
    private String qyfwdm;
    //迁受理单位_公安机关机构代码
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
    //数据流水
    private String ywlsh;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getQyzbh() {
        return qyzbh;
    }

    public void setQyzbh(String qyzbh) {
        this.qyzbh = qyzbh;
    }

    public String getCzr_gmsfhm() {
        return czr_gmsfhm;
    }

    public void setCzr_gmsfhm(String czr_gmsfhm) {
        this.czr_gmsfhm = czr_gmsfhm;
    }

    public String getCzr_xm() {
        return czr_xm;
    }

    public void setCzr_xm(String czr_xm) {
        this.czr_xm = czr_xm;
    }

    public String getYzz_ssxqdm() {
        return yzz_ssxqdm;
    }

    public void setYzz_ssxqdm(String yzz_ssxqdm) {
        this.yzz_ssxqdm = yzz_ssxqdm;
    }

    public String getYzz_qhnxxdz() {
        return yzz_qhnxxdz;
    }

    public void setYzz_qhnxxdz(String yzz_qhnxxdz) {
        this.yzz_qhnxxdz = yzz_qhnxxdz;
    }

    public String getYzz_cxfldm() {
        return yzz_cxfldm;
    }

    public void setYzz_cxfldm(String yzz_cxfldm) {
        this.yzz_cxfldm = yzz_cxfldm;
    }

    public String getQwd_ssxqdm() {
        return qwd_ssxqdm;
    }

    public void setQwd_ssxqdm(String qwd_ssxqdm) {
        this.qwd_ssxqdm = qwd_ssxqdm;
    }

    public String getQwd_qhnxxdz() {
        return qwd_qhnxxdz;
    }

    public void setQwd_qhnxxdz(String qwd_qhnxxdz) {
        this.qwd_qhnxxdz = qwd_qhnxxdz;
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

    public String getQfrq() {
        return qfrq;
    }

    public void setQfrq(String qfrq) {
        this.qfrq = qfrq;
    }

    public String getYxqjzrq() {
        return yxqjzrq;
    }

    public void setYxqjzrq(String yxqjzrq) {
        this.yxqjzrq = yxqjzrq;
    }

    public String getCbr_xm() {
        return cbr_xm;
    }

    public void setCbr_xm(String cbr_xm) {
        this.cbr_xm = cbr_xm;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getZqzbh() {
        return zqzbh;
    }

    public void setZqzbh(String zqzbh) {
        this.zqzbh = zqzbh;
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

    public String getYwlsh() {
        return ywlsh;
    }

    public void setYwlsh(String ywlsh) {
        this.ywlsh = ywlsh;
    }
}
