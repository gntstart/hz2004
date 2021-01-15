package com.hzjc.hz2004.util;

public enum LodopEnum {
    PRINTSET_1("printset_1 ", 1), PRINTSET_2("printset_2", 2), PRINTSET_3("printset_3", 3), PRINTSET_4("printset_4", 4),
    PRINTSET_5("printset_5 ", 1), PRINTSET_6("printset_6", 2), PRINTSET_7("printset_7", 3), PRINTSET_8("printset_8", 4),
    PRINTSET_9("printset_9 ", 1), PRINTSET_10("printset_10", 2), PRINTSET_11("printset_11", 3), PRINTSET_12("printset_12", 4),
    PRINTSET_13("printset_13 ", 1), PRINTSET_14("printset_14", 2), PRINTSET_15("printset_15", 3), PRINTSET_16("printset_16", 4),
    PRINTSET_17("printset_17 ", 1), PRINTSET_18("printset_18", 2), PRINTSET_19("printset_19", 3), PRINTSET_20("printset_20", 4);
	//    打印设置     
	
	//需要进行打印预览    1
	//打印时弹出打印设置 2
	
	//常表设置   
	
	//打印照片      3
	
	//索引卡设置 
	
	//集体户      4
	
	//户口簿设置
	
	//打印户口薄首页     5
	//打印户口薄背面     6
	//集体户方式户口首页    7
	//集体户方式户口页     8
	//打印出生原因      9
	
	//户籍证明设置    
	
	//打印照片        10
	//打印单位        11
	//打印户成员信息   12
	//打印户号       13
	//打印变动原因     14
	//打印婚姻状况     15
	//打印变动信息     16
	//打印兵役情况      17
	//打印注销人员信息    18
	//打印文化程度       19
	
    // 成员变量  
    private String name;  
    private int index;  
    // 构造方法  
    private LodopEnum(String name, int index) {  
        this.name = name;  
        this.index = index;  
    }  
    //覆盖方法  
    @Override  
    public String toString() {  
        return this.name;  
    } 
}
