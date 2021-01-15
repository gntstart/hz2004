package com.hzjc.hz2004.po.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "POST_SFZSL_DWPZ")
public class PostSfzslDwpz implements com.hzjc.wsstruts.po.PO {
    private static final long serialVersionUID = 1L;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @Id
    @GeneratedValue(generator = "generator")
    private String pzid;
    private String dqbm;
    private String pcsbm;
    private String pcsmc;
    private String zhxzsj;

    public String getPzid() {
        return pzid;
    }

    public void setPzid(String pzid) {
        this.pzid = pzid;
    }

    public String getDqbm() {
        return dqbm;
    }

    public void setDqbm(String dqbm) {
        this.dqbm = dqbm;
    }

    public String getPcsbm() {
        return pcsbm;
    }

    public void setPcsbm(String pcsbm) {
        this.pcsbm = pcsbm;
    }

    public String getPcsmc() {
        return pcsmc;
    }

    public void setPcsmc(String pcsmc) {
        this.pcsmc = pcsmc;
    }

    public String getZhxzsj() {
        return zhxzsj;
    }

    public void setZhxzsj(String zhxzsj) {
        this.zhxzsj = zhxzsj;
    }
}
