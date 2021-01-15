package com.hzjc.hz2004.base.sort;


import java.util.Comparator;

import com.hzjc.hz2004.base.bean.SysCode;
import com.hzjc.hz2004.util.CommonUtil;


/**
 * 实体排序接口类。
 * @author sh
 */
public class EntitySorter implements Comparator<Object>{
	boolean ascending;	//排序方式：true升序，false降序
	
	public EntitySorter(boolean ascending){
		this.ascending = ascending;
	}
	
	public int compare(Object o1, Object o2){
		int ire=0;
		
		if(o1 instanceof SysCode){
			SysCode b1 = (SysCode)o1;
			SysCode b2 = (SysCode)o2;
			
			double d1 = 999d;
			double d2 = 999d;
			
			if(CommonUtil.isNotEmpty(b1.getCode()))
				d1 = Double.parseDouble(b1.getCode());
			
			if(CommonUtil.isNotEmpty(b2.getCode()))
				d2 = Double.parseDouble(b2.getCode());
			
			if(d1!=d2){
				if(ascending)
					ire = d2>d1?1:-1;
				else
					ire = d1>d2?1:-1;
			}else{
				return 0;
			}
			
			return ire;
		}

		if(o1 instanceof String){
			String s1 = (String)o1;
			String s2 = (String)o2;
			ire = s1.compareTo(s2);
			if(ascending)
				ire = 0 - ire;
		}
		return ire;
	}
}
