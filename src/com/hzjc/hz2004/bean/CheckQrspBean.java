package com.hzjc.hz2004.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hzjc.hz2004.po.PoHJSP_HJSPSQB;
import com.hzjc.hz2004.po.PoHJXX_CZRKJBXXB;

/**
 * 迁入审批核查，一次性返回
 * @author Administrator
 *
 */
public class CheckQrspBean  implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private Map<String, List<PoHJXX_CZRKJBXXB>> chMap = new HashMap<>();
	private Map<String, List<PoHJSP_HJSPSQB>> spMap = new HashMap<>();
	private Map<String, String> xmMap = new HashMap<>();
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Map<String, String> getXmMap() {
		return xmMap;
	}

	public void setXmMap(Map<String, String> xmMap) {
		this.xmMap = xmMap;
	}

	public Map<String, List<PoHJXX_CZRKJBXXB>> getChMap() {
		return chMap;
	}

	public void setChMap(Map<String, List<PoHJXX_CZRKJBXXB>> chMap) {
		this.chMap = chMap;
	}

	public Map<String, List<PoHJSP_HJSPSQB>> getSpMap() {
		return spMap;
	}

	public void setSpMap(Map<String, List<PoHJSP_HJSPSQB>> spMap) {
		this.spMap = spMap;
	}
	
	
}
