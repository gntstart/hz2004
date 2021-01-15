package com.hzjc.hz2004.po.entity;

import java.io.Serializable;

public class Vhxx implements Serializable {
    private String	xm; 	//--姓名，
    private String	gmsfzh;	//--公民身份证号

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getGmsfzh() {
        return gmsfzh;
    }

    public void setGmsfzh(String gmsfzh) {
        this.gmsfzh = gmsfzh;
    }
}
