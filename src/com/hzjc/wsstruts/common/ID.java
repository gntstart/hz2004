package com.hzjc.wsstruts.common;

/**
*
* <p>Title: Hz2004</p>
* <p>Description: 常住人口二代证Hz2004版</p>
* <p>Copyright: Copyright (c) 2004</p>
* <p>Company: HZJC</p>
* @author Kansan Ku(kgb_hz@126.com)
* @version 1.0
*/
/**
* 配置示例：
* <idgen name="com.hzjc.hz2004.po.PoHJHIS_LSBGGZXXB" type="sequence">
*   SID_HJHIS_LSBGGZXXB
* </idgen>
*/
public  class ID {
    private String name = ""; //配置的表对应的Po类名称classname
    private String type = ""; //配置的表取主键策略Type
    private String value = ""; //培植的表ID对应的SeqID的Value

    /**
     *
     * @param strName
     * @param strType
     * @param strValue
     */
    public ID(String strName, String strType, String strValue) {
      setName(strName);
      setType(strType);
      setValue(strValue);
    }

    public String getName() {
      return this.name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getType() {
      return this.type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public String getValue() {
      return this.value;
    }

    public void setValue(String value) {
      this.value = value;
    }
  }