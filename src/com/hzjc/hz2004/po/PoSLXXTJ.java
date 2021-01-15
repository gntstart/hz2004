package com.hzjc.hz2004.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
*户关联关系表
*/
public class PoSLXXTJ implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String	slzt;
	private Integer	num;
	public String getSlzt() {
		return slzt;
	}
	public void setSlzt(String slzt) {
		this.slzt = slzt;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	
}