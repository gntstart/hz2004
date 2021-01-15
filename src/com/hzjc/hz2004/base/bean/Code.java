package com.hzjc.hz2004.base.bean;

public class Code implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private String code;
    private String name;
    private String pyt;
    
    public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Code(){
    	
    }

    public Code(String code,String name,String pyt){
    	this.code = code;
    	this.name = name;
    	this.pyt = pyt;
    }
    
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPyt() {
        return pyt;
    }
    public void setPyt(String pyt) {
        this.pyt = pyt;
    }
}