package com.hzjc.hz2004.base.bean;

import java.util.List;
import java.util.Map;

public class Dictcode{
    private List<Code> codelist;
    private Map<String,String> smap;
    
    public List<Code> getCodelist() {
        return codelist;
    }
    public void setCodelist(List<Code> codelist) {
        this.codelist = codelist;
    }
    public Map<String, String> getSmap() {
        return smap;
    }
    public void setSmap(Map<String, String> smap) {
        this.smap = smap;
    }
    
}
