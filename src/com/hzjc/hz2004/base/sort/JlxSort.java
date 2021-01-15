package com.hzjc.hz2004.base.sort;

import java.util.Comparator;

import com.hzjc.hz2004.po.PoXT_JLXXXB;

public class JlxSort implements Comparator<PoXT_JLXXXB>{
	public int compare(PoXT_JLXXXB o1, PoXT_JLXXXB o2) {
		if(o2==null)
			return -1;
		
		return o1.getDm().compareTo(o2.getDm());
	}
}
